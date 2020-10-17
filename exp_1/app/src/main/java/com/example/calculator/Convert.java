package com.example.calculator;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Convert {
    private int typeInput  = 0;
    private int typeOutput = 0;
    public double lengthConvert(String inputStr){
        double end = 0;
        //            米    厘米    毫米    千米
        double[][] rate={
                    {    1,  100,  1000,  0.001},
                    { 0.01,    1,    10, 0.00001},
                    {0.001,  0.1,     1,0.000001},
                    { 1000,10000,100000,       1}};
        end = Double.parseDouble(inputStr) * rate[typeInput][typeOutput];
        return end;
    }

    public double volumeConvert(String inputStr){
        double end = 0;
        //            厘米    分米    米    千米
        double[][] rate={
                {                 1,            0.001,   0.000001,  0.000000000000001},
                {              1000,                1,      0.001,     0.000000000001},
                {           1000000,             1000,          1,        0.000000001},
                {1000000000000000.0, 1000000000000.0 ,1000000000 ,                  1}};
        end = Double.parseDouble(inputStr) * rate[typeInput][typeOutput];
        return end;
    }

    public String baseConvert(String inputStr){
        int first = 2,second = 2;
        switch(this.getTypeInput()){
            case 0:first = 2;break;
            case 1:first = 8;break;
            case 2:first = 10;break;
            case 3:first = 16;break;
        }
        switch(this.getTypeOutput()){
            case 0:second = 2;break;
            case 1:second = 8;break;
            case 2:second = 10;break;
            case 3:second = 16;break;
        }
        try{
            int inputDecimal = Integer.parseInt(inputStr,first);
            return Integer.toString(inputDecimal,second);
        }catch(NumberFormatException e){
            return "Input Type Error";
        }
    }

    public int dateCalculate(String dbtime1, String dbtime2){
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        Date date1 = null;
        Date date2 = null;
        try {
            date1 = format.parse(dbtime1);
            date2 = format.parse(dbtime2);
        } catch (ParseException e) {
            e.printStackTrace();
        }

        int a = (int) ((date2.getTime() - date1.getTime()) / (1000*3600*24));
        return a;
    }

    public int getTypeInput() {
        return typeInput;
    }

    public void setTypeInput(int typeInput) {
        this.typeInput = typeInput;
    }

    public int getTypeOutput() {
        return typeOutput;
    }

    public void setTypeOutput(int typeOutput) {
        this.typeOutput = typeOutput;
    }
}
