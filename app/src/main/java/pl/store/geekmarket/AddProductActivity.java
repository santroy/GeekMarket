package pl.store.geekmarket;

import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.sql.SQLException;

import pl.store.database.DatabaseManager;
import pl.store.model.Product;

public class AddProductActivity extends AppCompatActivity {

    EditText etProductName, etProductPrice, etProductDesc;
    Spinner categorySpinner;
    Button btnAddProduct;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.add_product_activity);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle(" GeekMarket - Dodaj produkt ");
        actionBar.setIcon(R.mipmap.geek_market_logo);
        actionBar.setDisplayUseLogoEnabled(true);
        actionBar.setDisplayShowHomeEnabled(true);
        actionBar.setDisplayHomeAsUpEnabled(true);

        etProductName = (EditText) findViewById(R.id.etProductName);
        etProductPrice = (EditText) findViewById(R.id.etProductPrice);
        etProductDesc = (EditText) findViewById(R.id.etProductPrice);
        categorySpinner = (Spinner) findViewById(R.id.categorySpinner);
        btnAddProduct = (Button) findViewById(R.id.btnAddProduct);


    }

    public void btnSubmit(View v) {
        Product product = new Product();
        product.setProductName(etProductName.getText().toString().trim());
        product.setProductDesc(etProductDesc.getText().toString().trim());
        product.setProductPrice(Double.parseDouble(etProductPrice.getText().toString().trim()));
        product.setProductCategory(categorySpinner.getSelectedItem().toString().trim());

        try {
            DatabaseManager db = new DatabaseManager(this);
            db.open();
            db.createEntry(product);
            db.close();
            Toast.makeText(this, "Success!", Toast.LENGTH_SHORT).show();
        } catch(Exception e) {
            Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();
        }

    }
}
