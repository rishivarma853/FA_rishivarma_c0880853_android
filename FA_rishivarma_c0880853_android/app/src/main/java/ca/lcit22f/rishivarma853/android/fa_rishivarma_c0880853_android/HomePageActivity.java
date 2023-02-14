package ca.lcit22f.rishivarma853.android.fa_rishivarma_c0880853_android;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class HomePageActivity extends AppCompatActivity {

    public DatabaseHandler database;
    TextView labelMessage;
    ListView listProducts;
    FloatingActionButton fabAddNewProduct;
    ArrayList<Product> productList = new ArrayList<>();
    ListViewAdapter productListAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_page);
        labelMessage = findViewById(R.id.lbl_message);
        listProducts = findViewById(R.id.list_products);
        fabAddNewProduct = findViewById(R.id.fab_add_new_product);
        database = new DatabaseHandler(HomePageActivity.this);
        productListAdapter = new ListViewAdapter(HomePageActivity.this, productList);
        listProducts.setAdapter(productListAdapter);
//        int productCount = database.getProductsCount();
//        if(productCount == 0) labelMessage.setVisibility(View.VISIBLE);
//        else labelMessage.setVisibility(View.GONE);
        fabAddNewProduct.setOnClickListener(view -> {
            Intent intent = new Intent(HomePageActivity.this, ProductEntryPageActivity.class);
            startActivity(intent);
        });
        listProducts.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                int id = productListAdapter.getItem(i).getID();
                Intent intent = new Intent(HomePageActivity.this, ProductDisplayPageActivity.class);
                intent.putExtra("ID", id);
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        System.out.println("In Resume");
        int productCount = database.getProductsCount();
        if (productCount == 0) labelMessage.setVisibility(View.VISIBLE);
        else labelMessage.setVisibility(View.GONE);
        productList.clear();
        productList.addAll(database.getAllProducts());
        productListAdapter.notifyDataSetChanged();

    }
}