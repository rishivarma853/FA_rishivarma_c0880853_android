package ca.lcit22f.rishivarma853.android.fa_rishivarma_c0880853_android;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class ProductUpdatePageActivity extends AppCompatActivity {

    TextView labelHeading;
    Button buttonUpdate, buttonCancel;
    EditText inputID, inputName, inputDescription, inputPrice;
    DatabaseHandler database;
    int id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_update_page);
        labelHeading = findViewById(R.id.lbl_heading);
        buttonUpdate = findViewById(R.id.btn_update);
        buttonCancel = findViewById(R.id.btn_cancel);
        inputID = findViewById(R.id.inp_id);
        inputName = findViewById(R.id.inp_name);
        inputDescription = findViewById(R.id.inp_description);
        inputPrice = findViewById(R.id.inp_price);
        database = new DatabaseHandler(ProductUpdatePageActivity.this);
        id = getIntent().getIntExtra("ID", 1);
        inputID.setText(String.valueOf(id));
        Product product = database.getProduct(id);
        inputName.setText(product.getName());
        inputDescription.setText(product.getDescription());
        inputPrice.setText(String.valueOf(product.getPrice()));
        buttonCancel.setOnClickListener(view -> {
            Toast.makeText(ProductUpdatePageActivity.this, "Product Not Updated", Toast.LENGTH_LONG).show();
            finish();
        });
        buttonUpdate.setOnClickListener(view -> {
            boolean valid = true;
            String name = inputName.getText().toString();
            if (name.equals("")) {
                valid = false;
                inputName.setError("Name Field is Empty");
            }
            String description = inputDescription.getText().toString();
            if (description.equals("")) {
                valid = false;
                inputName.setError("Description Field is Empty");
            }
            String priceString = inputPrice.getText().toString();
            if (priceString.equals("")) {
                valid = false;
                inputName.setError("Name Field is Empty");
            }
            if (valid) {
                int price = Integer.parseInt(priceString);
                Product newProduct = new Product(id, name, description, price);
                database.updateProduct(newProduct);
                Toast.makeText(ProductUpdatePageActivity.this, "Product Is Updated Successfully", Toast.LENGTH_LONG).show();
                finish();
            }
        });
    }
}