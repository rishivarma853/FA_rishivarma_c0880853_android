package ca.lcit22f.rishivarma853.android.fa_rishivarma_c0880853_android;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class ProductEntryPageActivity extends AppCompatActivity {

    TextView labelHeading;
    Button buttonAdd, buttonCancel;
    EditText inputID, inputName, inputDescription, inputPrice;

    DatabaseHandler database;

    int id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_entry_page);
        labelHeading = findViewById(R.id.lbl_heading);
        buttonAdd = findViewById(R.id.btn_add);
        buttonCancel = findViewById(R.id.btn_cancel);
        inputID = findViewById(R.id.inp_id);
        inputName = findViewById(R.id.inp_name);
        inputDescription = findViewById(R.id.inp_description);
        inputPrice = findViewById(R.id.inp_price);
        database = new DatabaseHandler(ProductEntryPageActivity.this);
        id = ((database.getProductsCount())+1);
        inputID.setFocusable(View.FOCUSABLE);
        inputID.setClickable(true);
        inputID.setCursorVisible(true);
        inputID.setText(String.valueOf(id));
        inputID.setFocusable(View.NOT_FOCUSABLE);
        inputID.setClickable(false);
        inputID.setCursorVisible(false);
        buttonCancel.setOnClickListener(view -> {
            Toast.makeText(ProductEntryPageActivity.this, "Product Not Added", Toast.LENGTH_LONG).show();
            finish();
        });
        buttonAdd.setOnClickListener(view -> {
            boolean valid = true;
            String name = inputName.getText().toString();
            if(name.equals("")) {
                valid = false;
                inputName.setError("Name Field is Empty");
            }
            String description = inputDescription.getText().toString();
            if(description.equals("")) {
                valid = false;
                inputName.setError("Description Field is Empty");
            }
            String priceString = inputPrice.getText().toString();
            if(priceString.equals("")) {
                valid = false;
                inputName.setError("Name Field is Empty");
            }
            if(valid) {
                int price = Integer.parseInt(priceString);
                Product product = new Product(id, name, description, price);
                database.addProduct(product);
                Toast.makeText(ProductEntryPageActivity.this, "Product Is Added Successfully", Toast.LENGTH_LONG).show();
                finish();
            }
        });
    }
}