package com.starcases.calc;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;

import java.util.List;

public class CalcController {
    @FXML void quitOnAction(ActionEvent event) { Platform.exit(); }
    @FXML void newOnAction(ActionEvent event) { calcModel.clearAll(); display(); }
    @FXML void copyOnAction(ActionEvent event) {  calcModel.copy(); }
    @FXML void aboutOnAction(ActionEvent event) {  about(); }

    @FXML protected void onNumber1ButtonClick() { calcModel.appendNumber("1"); display(); }
    @FXML protected void onNumber2ButtonClick() { calcModel.appendNumber("2"); display();}
    @FXML protected void onNumber3ButtonClick() { calcModel.appendNumber("3"); display();}
    @FXML protected void onNumber4ButtonClick() { calcModel.appendNumber("4"); display();}
    @FXML protected void onNumber5ButtonClick() { calcModel.appendNumber("5"); display();}
    @FXML protected void onNumber6ButtonClick() { calcModel.appendNumber("6"); display();}
    @FXML protected void onNumber7ButtonClick() { calcModel.appendNumber("7"); display();}
    @FXML protected void onNumber8ButtonClick() { calcModel.appendNumber("8"); display();}
    @FXML protected void onNumber9ButtonClick() { calcModel.appendNumber("9"); display();}
    @FXML protected void onNumber0ButtonClick() { calcModel.appendNumber("0"); display();}
    @FXML protected void onNumberDotButtonClick() { calcModel.appendNumber("."); display();}

    @FXML protected void onAddButtonClick() { calcModel.putBinaryOp("+"); display();}
    @FXML protected void onSubtractButtonClick() { calcModel.putBinaryOp("-"); display();}
    @FXML protected void onMultButtonClick() { calcModel.putBinaryOp("*"); display();}
    @FXML protected void onDivButtonClick() { calcModel.putBinaryOp("/"); display();}
    @FXML protected void onModButtonClick() { calcModel.putBinaryOp("%"); display();}
    @FXML protected void onExponentButtonClick() { calcModel.putBinaryOp("^"); display();}

    @FXML protected void onTanButtonClick() { calcModel.putUnaryOp("tan"); display();}
    @FXML protected void onSinButtonClick() { calcModel.putUnaryOp("sin"); display();}
    @FXML protected void onCosButtonClick() { calcModel.putUnaryOp("cos"); display();}
    @FXML protected void onAbsButtonClick() { calcModel.putUnaryOp("abs"); display();}

    @FXML protected void onSignButtonClick() { calcModel.updateSign(); display();}

    @FXML protected void onEnterButtonClick() { calcModel.handleEnter(); display();}
    @FXML protected void onClearButtonClick() {calcModel.clear(); display();}
    @FXML protected void onDelButtonClick() { calcModel.deleteNumeralOrOp(); display();}
    @FXML protected void onDropButtonClick() { calcModel.pop(); display();}

    @FXML private Label result;
    @FXML private GridPane stack;

    private void display()
    {
        result.setText(calcModel.getResult());

        final List<Wrapper> opsAndValues = calcModel.getOpsAndValues();
        final int stackCount = stack.getRowCount();

        stack.getChildren().clear();
        for (int col = 0; col < opsAndValues.size(); col++)
        {
            Wrapper wrapper = opsAndValues.get(col);
            if (wrapper instanceof DataWrapper dataWrapper) {
                Label l = new Label(dataWrapper.getData());
                stack.add(l,  col, 0);
            }
            else if (wrapper instanceof OpWrapper opWrapper)
            {
                Label l = new Label(opWrapper.getOp());
                stack.add(l,  col, 0);
            }
        }
    }

    private void about()
    {
        AboutDialog.display();
    }

    private final CalcModel calcModel = CalcModel.getModel();
}