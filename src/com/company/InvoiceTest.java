package com.company;

class InvoiceTest {
    public static void main (String[] args) {
        String inputMsg1 = "Enter your name:";
        String inputMsg2 = "Input name of item ";
        String inputMsg3 = "Input quantity of item ";
        String inputMsg4 = "Input price of item ";
        String userName;
        String itemName;
        int itemQty;
        double itemPrice;
        int control = 0;
        Invoice.Ret[] invItems = new Invoice.Ret[3];

        Invoice.Ret value = Invoice.requestInputWithMsg(inputMsg1, 0);
        userName = value.inName; // Input User Name

        for (control=0;control<3; control=control+1){
            value = Invoice.requestInputWithMsg(inputMsg2 + (control+1), 0); // INput name of item 1
            itemName = value.inName;
            value = Invoice.requestInputWithMsg(inputMsg3 + (control+1), 1); // input item 1 qty
            itemQty = value.inQuantity;
            value = Invoice.requestInputWithMsg(inputMsg4 + (control+1), 2); //input item 1 price
            itemPrice = value.inPrice;
            Invoice.Ret holdValue = new Invoice.Ret(itemName, itemPrice, itemQty);
            invItems[control] = holdValue;

        }

        Invoice.displayInvoiceHeader(userName);
        control = 0;
        for (control=0;control<3; control=control+1){
            itemName = invItems[control].inName;
            itemQty = invItems[control].inQuantity;
            itemPrice = invItems[control].inPrice;
            Invoice.displayInvoiceItems(itemName, itemQty, itemPrice);
        }
        Invoice.displayTotals();
    }
}