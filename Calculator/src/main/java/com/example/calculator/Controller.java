package com.example.calculator;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.text.Text;

public class Controller {
    @FXML
    private Text answerField;

    @FXML
    private Text expr;
    @FXML
    private Button cleanUp;

    @FXML
    private Button deleteDigitSign;

    @FXML
    private Button divideNumber;

    @FXML
    private Button dotSign;

    @FXML
    private Button equalSign;

    @FXML
    private Button minusSign;

    @FXML
    private Button multiplyNumber;

    @FXML
    private Button numberEight;

    @FXML
    private Button numberFive;

    @FXML
    private Button numberFour;

    @FXML
    private Button numberNine;

    @FXML
    private Button numberOne;

    @FXML
    private Button numberSeven;

    @FXML
    private Button numberSix;

    @FXML
    private Button numberThree;

    @FXML
    private Button numberTwo;

    @FXML
    private Button numberZero;

    @FXML
    private Button plusSign;

    private String currNum = "0";
    private String firstNum = "";
    private String answer = "";
    private String operator;

    @FXML
    void initialize() {

        numberZero.setOnAction(event -> addNumber(0));
        numberOne.setOnAction(event -> addNumber(1));
        numberTwo.setOnAction(event -> addNumber(2));
        numberThree.setOnAction(event -> addNumber(3));
        numberFour.setOnAction(event -> addNumber(4));
        numberFive.setOnAction(event -> addNumber(5));
        numberSix.setOnAction(event -> addNumber(6));
        numberSeven.setOnAction(event -> addNumber(7));
        numberEight.setOnAction(event -> addNumber(8));
        numberNine.setOnAction(event -> addNumber(9));

        cleanUp.setOnAction(event -> {
            currNum = "0";
            answer = "0";
            firstNum = "0";
            answerField.setText("0");
            expr.setText("0");
        });

        plusSign.setOnAction(event -> setOperator("+"));
        minusSign.setOnAction(event -> setOperator("-"));
        multiplyNumber.setOnAction(event -> setOperator("*"));
        divideNumber.setOnAction(event -> setOperator("/"));

        deleteDigitSign.setOnAction(event -> {
            answer = answer.substring(0, answer.length() - 1);
            if (answer.length() > 11){
                answer = answer.substring(0, 11);
            }
            answerField.setText(answer);
        });

        dotSign.setOnAction(event -> {
            if (!hasDot())
                currNum += ".";
            updateAns();
        });

        equalSign.setOnAction(event -> getAnswer());
    }

    private void getAnswer(){
        if (currNum.equals("")) {
            updateAns();
        }
        expression();

        double first = Double.parseDouble(firstNum);
        double second = Double.parseDouble(currNum);

        switch (operator) {
            case "+" -> answer = Double.toString(first + second);
            case "-" -> answer = Double.toString(first - second);
            case "*" -> answer = Double.toString(first * second);
            case "/" -> answer = Double.toString(first / second);
        }
        answer = checkForInt(answer);

        if (answer.length() > 11){
            answer = answer.substring(0, 11);
        }
        answerField.setText(answer);
        firstNum = answer;
    }

    private String checkForInt(String str){
        if (str.endsWith(".0")) return str.substring(0, str.length() - 2);
        return str;
    }

    private void expression() {
        expr.setText(firstNum + " " + operator + " " + currNum);
    }

    private void setOperator(String operator){
        this.operator = operator;
        firstNum = answer.equals("")? currNum : answer;
        currNum = "";
        updateAns();
        expression();
    }

    private void addNumber(int num){
        if (currNum.equals("0")) {
            currNum = Integer.toString(num);
            updateAns();
            return;
        }
        currNum += num;
        updateAns();
    }

    private void updateAns(){
        answer = currNum;
        if (answer.length() > 11){
            answer = answer.substring(0, 11);
        }
        answerField.setText(answer);
    }

    private boolean hasDot(){
        for (char c: currNum.toCharArray())
            if (c == '.')
                return true;
        return false;
    }
}
