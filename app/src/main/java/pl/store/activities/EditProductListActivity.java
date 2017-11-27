package pl.store.activities;

import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

import pl.store.R;
import pl.store.database.DatabaseManager;
import pl.store.model.Product;

public class EditProductListActivity extends AppCompatActivity {

    ListView editProductViewList;
    ArrayAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.edit_product_list_activity);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle(" Edytuj produkt ");
        actionBar.setIcon(R.mipmap.geek_market_logo);
        actionBar.setDisplayUseLogoEnabled(true);
        actionBar.setDisplayShowHomeEnabled(true);
        actionBar.setDisplayHomeAsUpEnabled(true);

        editProductViewList = (ListView) findViewById(R.id.editProductViewList);

        try {

            final DatabaseManager db = new DatabaseManager(this);
            db.open();
            ArrayList<Product> products =  db.getData();
            db.close();
            adapter = new ArrayAdapter<Product>(this, android.R.layout.simple_list_item_1, products);
            editProductViewList.setAdapter(adapter);

            editProductViewList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    Product itemToEdit = (Product) editProductViewList.getItemAtPosition(position);
                    Bundle bundle = new Bundle();
                    bundle.putSerializable("itemToEdit", itemToEdit);
                    Intent intent = new Intent(EditProductListActivity.this, EditProductActivity.class);
                    intent.putExtras(bundle);
                    startActivity(intent);
                }
            });

        } catch (Exception e) {
            Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();
        }


    }
}
