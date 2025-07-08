package com.starcases.calc;

import javafx.scene.input.Clipboard;
import javafx.scene.input.ClipboardContent;

import java.util.ArrayList;
import java.util.List;

public class CalcModel
{
    public CalcModel clear()
    {
        result = "";
        resultType = Type.UNSET;
        return this;
    }

    public void updateSign()
    {
        if (resultType != Type.OP)
        {
            if (resultType == Type.UNSET || result.isEmpty())
            {
                result = "-";
                resultType = Type.DATA;
            }
            else {
                if (result.charAt(0) == '-')
                {
                    result = result.substring(1);
                }
                else
                {
                    result = "-" + result;
                }
            }
        }
    }

    public void appendNumber(String numeralOrDot)
    {
        if (resultType == Type.OP) {
            pushOpOrValue();
            handleEnter();
        }

        final String tmp = result + numeralOrDot;
        try
        {
            Double.parseDouble(tmp);
            result = tmp;
            resultType = Type.DATA;
        }
        catch(NumberFormatException e)
        {
            // do nothing
        }
    }

    public void putBinaryOp(String op)
    {
        if (resultType != Type.UNSET) {
            pushOpOrValue();
        }

        resultType = Type.OP;
        result = op;
    }

    public void putUnaryOp(String op)
    {
        if (resultType != Type.UNSET) {
            pushOpOrValue();
        }

        resultType = Type.OP;
        result = op;
    }

    public void deleteNumeralOrOp()
    {
        try
        {
            if (resultType == Type.OP)
            {
                result = "";
                resultType = Type.UNSET;
            }
            else {
                result = result.substring(0, result.length() - 1);
            }
        }
        catch(IndexOutOfBoundsException e)
        {
            result = "";
            resultType = Type.UNSET;
        }
    }

    public String getResult() {
        return result;
    }

    public void handleEnter()
    {
        if (resultType == Type.DATA)
        {
            // If only a '-' then delete it and unset type.
            if (result.length() == 1 && result.charAt(0) == '-') {
                deleteNumeralOrOp();
            }
        }
        pushOpOrValue();
    }

    private void pushOpOrValue()
    {
        switch(resultType)
        {
            case DATA:
                if (result.length() == 1 && result.charAt(0) == '-') {
                    clear();
                    break;
                }
                push(new DataWrapper(result + " "));
                clear();
                break;
            case OP:
                push(new OpWrapper(result + " "));
                clear();
                calc();
                break;

            case UNSET:
                // DO NOTHING
                break;

        }
        clear();
    }

    public void push(Wrapper opOrValue)
    {
        opsAndValues.add(opsAndValues.size(), opOrValue) ;
    }

    public Wrapper peek()
    {
        return opsAndValues.getLast();
    }

    public Wrapper pop()
    {
        return opsAndValues.removeLast();
    }

    public List<Wrapper> getOpsAndValues() {
        return opsAndValues;
    }

    public void calc()
    {
        if (!opsAndValues.isEmpty())
        {
            if (peek() instanceof OpWrapper opWrapper)
            {
                pop();

                result = opWrapper.execute(this);
                push(new DataWrapper(result + " "));
                clear();
            }
            else
            {
                // warning
            }
        }
    }

    public void clearAll()
    {
        clear();
        opsAndValues.clear();
    }

    public void copy()
    {
        if (!opsAndValues.isEmpty()) {
            Clipboard systemClipboard = Clipboard.getSystemClipboard();
            ClipboardContent content = new ClipboardContent();
            content.putString(opsAndValues.get(0).execute(this).trim());
            systemClipboard.setContent(content);
        }
    }

    public static CalcModel getModel()
    {
        return calcModel;
    }

    private enum Type { DATA, OP, UNSET }
    private Type resultType;
    private String result;

    private final List<Wrapper> opsAndValues = new ArrayList<>();

    private final static CalcModel calcModel = (new CalcModel()).clear();
}
