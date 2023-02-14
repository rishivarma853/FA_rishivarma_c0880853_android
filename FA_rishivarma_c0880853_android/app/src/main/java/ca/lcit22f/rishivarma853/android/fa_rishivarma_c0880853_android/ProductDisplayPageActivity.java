package ca.lcit22f.rishivarma853.android.fa_rishivarma_c0880853_android;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class ProductDisplayPageActivity extends AppCompatActivity {

    TextView labelHeading;
    Button buttonUpdate, buttonDelete;
    EditText inputID, inputName, inputDescription, inputPrice;

    DatabaseHandler database;
    int id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_display_page);
        labelHeading = findViewById(R.id.lbl_heading);
        buttonUpdate = findViewById(R.id.btn_update);
        buttonDelete = findViewById(R.id.btn_delete);
        inputID = findViewById(R.id.inp_id);
        inputName = findViewById(R.id.inp_name);
        inputDescription = findViewById(R.id.inp_description);
        inputPrice = findViewById(R.id.inp_price);
        database = new DatabaseHandler(ProductDisplayPageActivity.this);
        id = getIntent().getIntExtra("ID", 1);
        buttonUpdate.setOnClickListener(view -> {
            Intent intent = new Intent(ProductDisplayPageActivity.this, ProductUpdatePageActivity.class);
            intent.putExtra("ID", id);
            startActivity(intent);
        });
        buttonDelete.setOnClickListener(view -> {
            Product product = database.getProduct(id);
            database.deleteProduct(product);
            Toast.makeText(ProductDisplayPageActivity.this, "Product is Deleted Successfully", Toast.LENGTH_LONG).show();
            finish();
        });
    }

    protected void onResume() {
        super.onResume();
        Product product = database.getProduct(id);
        inputID.setText(product.getID());
        inputName.setText(product.getName());
        inputDescription.setText(product.getDescription());
        inputPrice.setText(product.getPrice());
    }

}