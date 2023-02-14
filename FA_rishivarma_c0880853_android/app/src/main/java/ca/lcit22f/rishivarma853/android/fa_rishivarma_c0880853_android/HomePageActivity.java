package ca.lcit22f.rishivarma853.android.fa_rishivarma_c0880853_android;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class HomePageActivity extends AppCompatActivity {

    TextView labelMessage;
    ListView listProducts;
    FloatingActionButton fabAddNewProduct;
    ArrayList<Product> productList;
    ListViewAdapter productListAdapter;

    public DatabaseHandler database;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);
        labelMessage = findViewById(R.id.lbl_message);
        listProducts = findViewById(R.id.list_products);
        fabAddNewProduct = findViewById(R.id.fab_add_new_product);
        database = new DatabaseHandler(HomePageActivity.this);
        productList = new ArrayList<Product>();
        productListAdapter = new ListViewAdapter(HomePageActivity.this, productList);
        listProducts.setAdapter(productListAdapter);
        int productCount = database.getProductsCount();
        if(productCount == 0) labelMessage.setVisibility(View.VISIBLE);
        else labelMessage.setVisibility(View.GONE);
        fabAddNewProduct.setOnClickListener(view -> {
            Intent intent = new Intent(HomePageActivity.this, ProductEntryPageActivity.class);
            startActivity(intent);
        });
        listProducts.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                int id = i + 1;
                Intent intent = new Intent(HomePageActivity.this, ProductDisplayPageActivity.class);
                intent.putExtra("ID", id);
                startActivity(intent);
            }
        });
    }

    protected void OnStart() {
        super.onStart();
        int productCount = database.getProductsCount();
        if(productCount == 0) labelMessage.setVisibility(View.VISIBLE);
        else labelMessage.setVisibility(View.GONE);
        productList = new ArrayList<>(database.getAllProducts());
        productListAdapter = new ListViewAdapter(HomePageActivity.this, productList);
        listProducts.setAdapter(productListAdapter);
    }
}