package com.example.android.coolcalc;

import android.app.Activity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.ArrayList;

public class CalcActivity extends Activity {


    StringBuilder runningNumber = new StringBuilder(); // display running numbers & operations
    StringBuilder storeNum = new StringBuilder(); // get number btw operator
    ArrayList<Double> getNum = new ArrayList<Double>(); //store numbers
    ArrayList<String> getOp = new ArrayList<String>(); // store operators
    boolean firstLetter = false; // track first number enter
    boolean doubleOperator = false; // track double operator
    boolean resultSet = false; //track display result

    TextView resultView; // for displaying result

    /**
     * Load upon app start
     * @param savedInstanceState
     */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calc);

        //Assign variable to gui button by their id
        Button oneBtn  =  (Button)findViewById(R.id.oneBtn);
        Button twoBtn  =  (Button)findViewById(R.id.twoBtn);
        Button threeBtn  =  (Button)findViewById(R.id.threeBtn);
        Button fourBtn  =  (Button)findViewById(R.id.fourBtn);
        Button fiveBtn  =  (Button)findViewById(R.id.fiveBtn);
        Button sixBtn  =  (Button)findViewById(R.id.sixBtn);
        Button sevenBtn  =  (Button)findViewById(R.id.sevenBtn);
        Button eightBtn  =  (Button)findViewById(R.id.eightBtn);
        Button nineBtn  =  (Button)findViewById(R.id.nineBtn);
        Button clearBtn  =  (Button)findViewById(R.id.clearBtn);
        Button zeroBtn  =  (Button)findViewById(R.id.zeroBtn);

        //Assign variable to gui imagebutton by their id
        ImageButton addBtn  =  (ImageButton)findViewById(R.id.addBtn);
        ImageButton subBtn  =  (ImageButton)findViewById(R.id.subBtn);
        ImageButton multiBtn  =  (ImageButton)findViewById(R.id.multiBtn);
        ImageButton divideBtn  =  (ImageButton)findViewById(R.id.divideBtn);
        ImageButton calBtn  =  (ImageButton)findViewById(R.id.calBtn);


        //Assign variable to gui textview by their id
        resultView = (TextView)findViewById(R.id.resultView);

        //Clear display screen on initialization
        resultView.setText("");
        storeNum.setLength(0);

        //Add onclick listerner
        oneBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                numberPressed("1");
            }
        });

        twoBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                numberPressed("2");
            }
        });

        threeBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                numberPressed("3");
            }
        });

        fourBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                numberPressed("4");
            }
        });

        fiveBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                numberPressed("5");
            }
        });

        sixBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                numberPressed("6");
            }
        });

        sevenBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                numberPressed("7");
            }
        });

        eightBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                numberPressed("8");
            }
        });

        nineBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                numberPressed("9");
            }
        });

        zeroBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                numberPressed("0");
            }
        });


        clearBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                clearNumber();
            }
        });


        addBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                numberPressed("+");
            }
        });

        subBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                numberPressed("-");
            }
        });


        multiBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                numberPressed("*");

            }
        });


        divideBtn.setOnClickListener(new View.OnClickListener(){

            @Override
            public void onClick(View v){
                numberPressed("/");
            }
        });


        calBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v){
                processCal();
            }
        });
    }//onCreate()

    /**
     * Process all strings passes from action listener. Ensure first input is an numberic
     * before passing it to getNumAndOperator().
     * @param getDigit
     */
    void numberPressed(String getDigit){

        if(!firstLetter){
            if(TextUtils.isDigitsOnly(getDigit)) {
                getNumAndOperator(getDigit);
                firstLetter = true;
            }
            else
                resultView.setText("Enter Number");
        }
        else {
            getNumAndOperator(getDigit);
        }

    }//numberPressed

    /**
     * Check for non empty string and pass to addDigitAndDisplayText(), add to getOp
     * upon encounter the 4 operators.
     * @param getDigit
     */
    void getNumAndOperator(String getDigit){

        if(!getDigit.equals(""))
            if(getDigit.equals("/")){
                if( !doubleOperator) {
                    getOp.add("/");
                    addDigitAndDisplayText(getDigit);
                }
            }
            else if(getDigit.equals("*")){
                if( !doubleOperator) {
                    getOp.add("*");
                    addDigitAndDisplayText(getDigit);
                }
            }
            else if(getDigit.equals("+")){
                if( !doubleOperator) {
                    getOp.add("+");
                    addDigitAndDisplayText(getDigit);
                }

            }
            else if(getDigit.equals("-")){
                if( !doubleOperator) {
                    getOp.add("-");
                    addDigitAndDisplayText(getDigit);
                }
            }
            else{
                //if calBtn not press
                if(!resultSet) {
                    storeNum.append(getDigit);
                    doubleOperator = false;
                    resultSet = false;
                    displayText(getDigit);
                    //printArray();
                }
                else {
                    clearNumber();
                    storeNum.append(getDigit);
                    displayText(getDigit);
                    firstLetter = true;
                    //printArray();

                }
            }
    }//getNumAndOperation()

    /**
     * Call addDigit() and displayText(). Set doubleOperator to track
     * for double operator enter. Reset resultSet to allow continous
     * calculation.
     * @param getDigit
     */
    void addDigitAndDisplayText(String getDigit){
        addDigit();
        displayText(getDigit);
        doubleOperator = true;
        resultSet = false;
    }//addDigitAndDisplayText()

    /**
     * Append and display running input to screen
     * @param getDigit
     */

    void displayText(String getDigit) {
        if (!doubleOperator){
            //System.out.println("DT:" + getDigit);
            runningNumber.append(getDigit);
            resultView.setText(runningNumber.toString());
        }
    }//displayText

    /**
     * convert string to double and add to storeNum.
     */

    void addDigit(){
        if(storeNum.length() > 0 ) {
            getNum.add(Double.valueOf(storeNum.toString()));
            storeNum.setLength(0);
            //printArray();
        }
    }//addDigit()


    /**
     * Get the last set of numbers and pass to processOperator() to
     *  process multiple, divide, plus and substraction when user
     *  press the calBtn.
     */
    void processCal(){
        if(storeNum.length() > 0 ) {
            getNum.add(Double.valueOf(storeNum.toString()));
            storeNum.setLength(0);
            //System.out.println("storeNum Length:" + storeNum.length());
            //printArray();

            processOperator("*");
            processOperator("/");
            processOperator("+");
            processOperator("-");

            setResult();
        }
    }//processCall


    /**
     * Get the 1st and 2nd set of numbers to perform the respective
     * mathematical operation according to the operator.
     * @param operator
     */
    void processOperator(String operator) {

        while(getOp.contains(operator)){
            int pos = getOp.indexOf(operator);
            getOp.remove(pos);

            double first = getNum.get(pos);
            getNum.remove(pos);

            double sec = getNum.get(pos);
            getNum.remove(pos);
            double res;

            if(operator.equals("*"))
                 res = first*sec;
            else if(operator.equals("/"))
                 res = first/sec;
            else if(operator.equals("+"))
                res = first+sec;
            else
                res = first-sec;

            getNum.add(pos,res);

            //System.out.println("First:" + first+ " Second: "+sec+ " Operation: "+operation  + " result: " + res);
            //printArray();
        }
    }//processDivMulti()

    /**
     * Dsiply calculated result into resultView and running number
     */
    void setResult(){
        String result = String.format("%.5f",getNum.get(0));
        resultView.setText(result);
        runningNumber.setLength(0);
        runningNumber.append(result);
        resultSet = true;
    }//setResult()


    /**
     * print items in getNum and getOp
     */
    void printArray(){
        System.out.println(getNum);
        System.out.println(getOp);
    }//printArray


    /**
     * clear runningNumber and call clearStoringVars()
     */

    void clearNumber(){
        runningNumber.setLength(0);
        resultView.setText("");
        clearStoringVars();
    }//clearNumber


    /**
     * Clear all variables
     */

    void clearStoringVars() {
        storeNum.setLength(0);
        getNum.clear();
        getOp.clear();
        firstLetter = false;
        doubleOperator = false;
        resultSet = false;
    }//clearStoringVars()

}//CalcActivity()
