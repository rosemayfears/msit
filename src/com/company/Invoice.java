package com.company;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.NoSuchElementException;
import java.util.Random;
import java.util.Scanner;
public class Invoice {
    public String userName;
    public String itemName;
    public int itemQuantity;
    public double itemTotal;
    static double subTotal;
    static double subTotalTax;
    static final double taxRate = 0.0625;

    public void setSubTotal(double subTotal) {
        this.subTotal = subTotal;
    }

    public double getSubTotal() {
        return subTotal;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserName() {
        //Scanner myUserName = new Scanner(System.in);
        //this.userName = myUserName.nextLine();
        return userName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemQuantity(int itemQuantity) {
        this.itemQuantity = itemQuantity;
    }

    public int getItemQuantity() {
        return itemQuantity;
    }

    public void setItemTotal(double itemTotal) {
        this.itemTotal = itemTotal;
    }

    public double getItemTotal() {
        return itemTotal;
    }


    static class Ret{
        int inQuantity;
        String inName;
        double inPrice;
        public Ret(String inName, double inPrice, int inQuantity){
            this.inName = inName;
            this.inQuantity = inQuantity;
            this.inPrice = inPrice;
        }
        public Ret(){

        }
    }
    public static Ret getNextEntry(Scanner input, int T ) {
        Ret retValue = new Ret();
        switch (T){
            case 0: // String input
            {
                try {
                    String name = input.next();
                    retValue.inName = name;
                } catch (NoSuchElementException e) {
                    return null;
                }
            }
                break;
            case 1: // Integer input
            {
                try {
                    int itemQty = input.nextInt();
                    retValue.inQuantity = itemQty;
                } catch (NoSuchElementException e) {
                    return null;
                }
            }
                break;
            case 2: // Double input
            {
                try {
                    double itemPrice = input.nextDouble();
                    retValue.inPrice = itemPrice;
                } catch (NoSuchElementException e) {
                    return null;
                }
            }
                break;
        }
        return retValue;
    }
    public static Ret requestInputWithMsg( String msg, int T){
        Scanner input = new Scanner(System.in);
        input.useDelimiter("\n");
        System.out.println(msg);
        return getNextEntry(input,T);
    }
    public static int generateInvNumber(){
        Random randInv = new Random();
        int invNumber;
        int lowerbound = 10000;
        int upperbound = 99999;

        invNumber = randInv.nextInt(upperbound-lowerbound + lowerbound);
        return invNumber;
    }
    public static void displayInvoiceHeader(String Name){
        int invoiceNum;
        String itemsName = "Item";
        String itemsQuantity = "Quantity";
        String itemsPrice = "Price";
        String itemsTotal = "Total";
        String no = "No.";
        LocalDateTime date = LocalDateTime.now();
        DateTimeFormatter formatDate = DateTimeFormatter.ofPattern("MM-dd-yyyy");
        String finalDate = "Date:"+ date.format(formatDate);
        System.out.println("Invoice for " + Name);
        invoiceNum = generateInvNumber();
        System.out.println( String.format("%-2s%-40s%-38s",no,invoiceNum,finalDate));
        System.out.println(String.format("%-30s%-10s%-10s%-10s",itemsName,itemsQuantity,itemsPrice,itemsTotal));
    }
    public static void displayInvoiceItems(String iName, int iQuantity, double itemPrice){
        String passedName = iName;
        int passedQty = iQuantity;
        double passedPrice = itemPrice;
        double itemsTotal = passedQty * passedPrice;
        subTotal = subTotal + itemsTotal;
        //BigDecimal tmpValue = BigDecimal.valueOf(itemsTotal).setScale(2, RoundingMode.HALF_UP);
        itemsTotal = roundValues(itemsTotal);
        passedPrice = roundValues(passedPrice);
        System.out.println(String.format("%-30s%-10s%-10s%-10s",passedName,passedQty,passedPrice,itemsTotal));

    }
    public static double roundValues(double iValue){
        double inputValue = iValue;
        BigDecimal tmp = BigDecimal.valueOf(inputValue).setScale(2, RoundingMode.HALF_UP);
        return tmp.doubleValue();
    }
    public static void displayTotals(){

        String invSubTotalText = "Subtotal";
        String salesTaxText = "6.25% sales tax";
        String invTotalText = "Total";

        Double subTotalTax = subTotal * taxRate;
        Double invoiceTotal = subTotal + subTotalTax;

        subTotal = roundValues(subTotal);
        subTotalTax = roundValues(subTotalTax);
        invoiceTotal = roundValues(invoiceTotal);

        System.out.println(String.format("%-50s%-10s",invSubTotalText,subTotal));
        System.out.println(String.format("%-50s%-10s",salesTaxText,subTotalTax));
        System.out.println(String.format("%-50s%-10s",invTotalText,invoiceTotal ));

    }
}
