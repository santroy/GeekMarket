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

public class DeleteProductActivity extends AppCompatActivity {
    ListView deleteProductViewList;
    ArrayAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.delete_product_activity);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle(" GeekMarket - Usuń produkt ");
        actionBar.setIcon(R.mipmap.geek_market_logo);
        actionBar.setDisplayUseLogoEnabled(true);
        actionBar.setDisplayShowHomeEnabled(true);
        actionBar.setDisplayHomeAsUpEnabled(true);

        deleteProductViewList = (ListView) findViewById(R.id.deleteProductViewList);

        try {

            final DatabaseManager db = new DatabaseManager(this);
            db.open();
            ArrayList<Product> products =  db.getData();
            db.close();
            adapter = new ArrayAdapter<Product>(this, android.R.layout.simple_list_item_1, products);
            deleteProductViewList.setAdapter(adapter);

            deleteProductViewList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                    Product itemToDelete = (Product) deleteProductViewList.getItemAtPosition(position);
                    db.open();
                    db.deleteItem(itemToDelete.getId());
                    db.close();

                    Toast.makeText(DeleteProductActivity.this, "Usunięto!", Toast.LENGTH_SHORT).show();
                    Intent intent = getIntent();
                    finish();
                    overridePendingTransition(0, 0);
                    startActivity(intent);
                    overridePendingTransition(0, 0);

                }
            });

        } catch (Exception e) {
            Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();
        }

    }
}
