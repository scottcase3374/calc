package com.starcases.calc;

public class OpWrapper implements Wrapper
{
    public OpWrapper(String op)
    {
        this.op = op;
    }

    public String getOp()
    {
        return op;
    }

    @Override
    public String execute(CalcModel model)
    {
        String tmp = "";
        switch (op.trim()) {
            case "+": {
                final Wrapper y = model.pop();
                final Wrapper x = model.pop();
                if (x instanceof DataWrapper xx && y instanceof DataWrapper yy) {
                    tmp = Double.toString(Double.parseDouble(xx.execute(model)) + Double.parseDouble(yy.execute(model)));
                }
            }
            break;
            case "-": {
                final Wrapper y = model.pop();
                final Wrapper x = model.pop();
                if (x instanceof DataWrapper xx && y instanceof DataWrapper yy) {
                    tmp = Double.toString(Double.parseDouble(xx.execute(model)) - Double.parseDouble(yy.execute(model)));
                }
            }
            break;
            case "*": {
                final Wrapper y = model.pop();
                final Wrapper x = model.pop();
                if (x instanceof DataWrapper xx && y instanceof DataWrapper yy) {
                    tmp = Double.toString(Double.parseDouble(xx.execute(model)) * Double.parseDouble(yy.execute(model)));
                }
            }
            break;
            case "/": {
                final Wrapper y = model.pop();
                final Wrapper x = model.pop();
                if (x instanceof DataWrapper xx && y instanceof DataWrapper yy) {
                    tmp = Double.toString(Double.parseDouble(xx.execute(model)) / Double.parseDouble(yy.execute(model)));
                }
            }
            break;
            case "%": {
                final Wrapper y = model.pop();
                final Wrapper x = model.pop();
                if (x instanceof DataWrapper xx && y instanceof DataWrapper yy) {
                    tmp = Double.toString(Double.parseDouble(xx.execute(model)) % Double.parseDouble(yy.execute(model)));
                }
            }
            break;

            case "^": {
                final Wrapper y = model.pop();
                final Wrapper x = model.pop();
                if (x instanceof DataWrapper xx && y instanceof DataWrapper yy) {
                    tmp =   Double.toString(Math.pow(Double.parseDouble(xx.execute(model)), Double.parseDouble(yy.execute(model))));
                }
            }
            break;
            case "tan": {
                final Wrapper x = model.pop();
                if (x instanceof DataWrapper xx) {
                    tmp = Double.toString(Math.tan(Double.parseDouble(xx.execute(model))));
                }
            }
            break;
            case "abs": {
                final Wrapper x = model.pop();
                if (x instanceof DataWrapper xx) {
                    tmp = Double.toString(Math.abs(Double.parseDouble(xx.execute(model))));
                }
            }
            break;
            case "sin": {
                final Wrapper x = model.pop();
                if (x instanceof DataWrapper xx) {
                    tmp = Double.toString(Math.sin(Double.parseDouble(xx.execute(model))));
                }
            }
            break;
            case "cos":
            {
                final Wrapper x = model.pop();
                if (x instanceof DataWrapper xx ) {
                    tmp = Double.toString(Math.cos(Double.parseDouble(xx.execute(model))));
                }
            }
            break;
        }
        return tmp;
    }

    private final String op;
}
