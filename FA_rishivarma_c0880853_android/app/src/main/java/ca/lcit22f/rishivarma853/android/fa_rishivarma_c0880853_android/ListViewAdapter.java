package ca.lcit22f.rishivarma853.android.fa_rishivarma_c0880853_android;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class ListViewAdapter extends BaseAdapter {
    private final Context context; //context
    private final ArrayList<Product> productsArrayList; //data source of the list adapter

    //public constructor
    public ListViewAdapter(Context context, ArrayList<Product> products) {
        this.context = context;
        this.productsArrayList = products;
    }

    public int getCount() {
        return productsArrayList.size(); //returns total of items in the list
    }

    public Product getItem(int position) {
        return productsArrayList.get(position); //returns list item at the specified position
    }

    public long getItemId(int position) {
        return position;
    }

    public View getView(int position, View convertView, ViewGroup parent) {
        // inflate the layout for each list row
        if (convertView == null) {
            convertView = LayoutInflater.from(context).
                    inflate(R.layout.list_item_layout, null, true);
        }

        // get current item to be displayed
        Product currentProduct = productsArrayList.get(position);

        // get the TextView for item name and item description
        TextView labelProductName = convertView.findViewById(R.id.lbl_product_name);
        TextView labelProductDescription = convertView.findViewById(R.id.lbl_product_description);

        //sets the text for item name and item description from the current item object
        labelProductName.setText(currentProduct.getName());
        labelProductDescription.setText(currentProduct.getDescription());

        // returns the view for the current row
        return convertView;
    }
}
