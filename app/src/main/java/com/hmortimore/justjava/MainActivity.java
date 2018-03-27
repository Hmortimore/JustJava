package com.hmortimore.justjava;

/**
 * IMPORTANT: Make sure you are using the correct package name.
 * This example uses the package name:
 * package com.example.android.justjava
 * If you get an error when copying this code into Android studio, update it to match the package name found
 * in the project's AndroidManifest.xml file.
 **/

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

/**
 * This app displays an order form to order coffee.
 */
public class MainActivity extends AppCompatActivity {
    int quantity = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    /**
     * This method is called when the order button is clicked.
     */
    public void submitOrder(View view) {
        // Figure out if user wants whipped cream
        CheckBox whippedCreamCheckBox = findViewById(R.id.whipped_Cream_checkbox);
        boolean hasWhippedCream = whippedCreamCheckBox.isChecked();

        // Figure out if user wants chocolate
        CheckBox chocolateCheckBox = findViewById(R.id.chocolate_checkbox);
        boolean hasChocolate = chocolateCheckBox.isChecked();

        // Allows user to edit and input their name
        EditText nameField = (EditText) findViewById(R.id.name_field);
        String name = nameField.getText().toString();

        // Calculate the price
        int price = calculatePrice(hasWhippedCream,hasChocolate);
        // Display order summary on the screen
        String priceMessage = createOrderSummary(name, price, hasWhippedCream, hasChocolate);

        displayMessage(priceMessage);
    }


    /**
     * Calculates the price of the order.
     * @oaram addChocolate is whether or not user wants chocolate
     * @param addWhippedCream is whether or not the user wants whipped cream
    /**
     */

    private int calculatePrice(boolean addWhippedCream, boolean addChocolate) {
        // Price of 1 cup of coffee
        int basePrice = 5;

        // Price if user wants whipped cream
        if (addWhippedCream) {
            basePrice = basePrice + 1;
        }
        // Price if user wants Chocolate
        if (addChocolate) {
            basePrice = basePrice + 2;
        }
        // Calculate the total order price by multiplying by quantity
        return quantity * basePrice;
    }

    /**
     * Create summary of the order.
     *
     * @param addWhippedCream is whether or not the user wants whipped cream topping
     * @param addChocolate is whether or not the user wants chocolate topping
     * @param price of the order
     * @return text summary
     */
    private String createOrderSummary(String name, int price, boolean addWhippedCream, boolean addChocolate) {
        String priceMessage = "\nName: " + name;
        priceMessage +="\nAdd Whipped Cream? " + addWhippedCream;
        priceMessage +="\nAdd Chocolate? " + addChocolate;
        priceMessage +="\nQuantity: " + quantity;
        priceMessage +="\nTotal: $" + price;
        priceMessage +="\nThank you!";
        return priceMessage;
    }


    /**
     * This method is called when the plus button is clicked.
     */
    public void increment(View view) {
        if (quantity == 100) {
            Toast.makeText(this, "You cannot have more than 100 coffees", Toast.LENGTH_SHORT);
            return;
        }
        quantity = quantity + 1;
        displayQuantity(quantity);

    }
    /**
     * This method is called when the minus button is clicked.
     */
    public void decrement(View view) {
        if (quantity == 1) {
            Toast.makeText(this, "You cannot have less than 1 coffee", Toast.LENGTH_SHORT);
            return;
        }
        quantity = quantity - 1;
        displayQuantity(quantity);

    }

    /**
     * This method displays the given quantity value on the screen.
     */
    private void displayQuantity(int number) {
        TextView quantityTextView = (TextView) findViewById(R.id.quantity_text_view);
        quantityTextView.setText("" + number);
    }

    /**
     * This method displays the given text on the screen.
     */
    private void displayMessage(String message) {
        TextView orderSummaryTextView = (TextView) findViewById(R.id.order_summary_text_view);
        orderSummaryTextView.setText(message);
    }

}

