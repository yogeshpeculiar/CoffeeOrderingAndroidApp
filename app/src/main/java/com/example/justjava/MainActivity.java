package com.example.justjava;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    int number = 0;
    int billCost = 0;
    int toppingCost = 0;
    boolean isWhippedCreamChecked;
    boolean isChocolateChecked;
    ArrayList<String> orderSummary = new ArrayList<>();
    ArrayList<String> toppingList = new ArrayList<>();
    String toppingInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    public void onSubmitCalled(View view) {
        displayPrice();
        displayOrderSummary();
    }

    public void increment(View view) {
        if(number<100)
            number += 1;
        else {
            Toast.makeText(this.getApplicationContext(),"you have reached the maximum quantity",Toast.LENGTH_SHORT).show();
            return;
        }
        displayQuantity();
    }

    public void decrement(View view) {
        if (number > 0)
            number -= 1;
        else
        {
            Toast.makeText(this.getApplicationContext(),"value cannot be decremented further",Toast.LENGTH_SHORT).show();
            return;
        }
        displayQuantity();

    }

    public void displayQuantity() {

        TextView quantity = (TextView) findViewById(R.id.quantityTextView);
        quantity.setText(String.valueOf(number));

    }

    public void displayPrice() {
        TextView price = (TextView) findViewById(R.id.priceTextView);
        billCost = number * 5;
        if (isWhippedCreamChecked)
            billCost += (number * 3);
        if (isChocolateChecked)
            billCost += (number * 1);
        price.setText(String.valueOf(billCost));
    }

    public void onCheckWhippedCream(View view) {
        isWhippedCreamChecked = ((CheckBox) view).isChecked();
        if (((CheckBox) view).isChecked())
            toppingList.add("Whipped Cream");
        else
            toppingList.remove("Whipped Cream");
    }

    public void onCheckChocolate(View view) {
        isChocolateChecked = ((CheckBox) view).isChecked();
        if (isChocolateChecked)
            toppingList.add("Chocolate");
        else
            toppingList.remove("Chocolate");
    }

    public void displayOrderSummary() {
        orderSummary.clear();
        String quantityInfo = "QUANTITY :  " + number;
        orderSummary.add(quantityInfo);
        String priceInfo = "PRICE :  " + billCost;
        orderSummary.add(priceInfo);
        toppingInfo = "TOPPING :  " + String.valueOf(toppingList);
        orderSummary.add(String.valueOf(toppingInfo));
//        TextView orderSummaryReference = (TextView) findViewById(R.id.orderSummary);
//        orderSummaryReference.setText(String.valueOf(orderSummary));
        sendOrderSummaryAsMail();

    }
    public void sendOrderSummaryAsMail(){
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("text/plain");
        intent.putExtra(Intent.EXTRA_SUBJECT, "ur order summary!");
        intent.putExtra(Intent.EXTRA_TEXT,String.valueOf(orderSummary)+"hii");
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }



    }

}
