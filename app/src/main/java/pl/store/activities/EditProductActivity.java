package pl.store.activities;

import android.content.Intent;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import pl.store.R;
import pl.store.database.DatabaseManager;
import pl.store.model.Product;

public class EditProductActivity extends AppCompatActivity {

    TextView productID;
    EditText etProductEditName, etProductEditPrice, etProductEditDesc;
    Spinner categoryEditSpinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.edit_product_activity);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle(" Edytuj produkt ");
        actionBar.setIcon(R.mipmap.geek_market_logo);
        actionBar.setDisplayUseLogoEnabled(true);
        actionBar.setDisplayShowHomeEnabled(true);
        actionBar.setDisplayHomeAsUpEnabled(true);

        productID = (TextView) findViewById(R.id.productID);
        etProductEditName = (EditText) findViewById(R.id.etProductEditName);
        etProductEditPrice = (EditText) findViewById(R.id.etProductEditPrice);
        etProductEditDesc = (EditText) findViewById(R.id.etProductEditDesc);
        categoryEditSpinner = (Spinner) findViewById(R.id.categoryEditSpinner);

        Intent intent = this.getIntent();
        Bundle bundle = intent.getExtras();
        Product itemToEdit = (Product) bundle.getSerializable("itemToEdit");

        productID.setText("Nr produktu: " + String.valueOf(itemToEdit.getId()));
        etProductEditName.setText(itemToEdit.getProductName());
        etProductEditPrice.setText(itemToEdit.getProductPrice().toString());
        etProductEditDesc.setText(itemToEdit.getProductDesc());
        categoryEditSpinner.setSelection(getSpinnerIndexBasedOfValue(categoryEditSpinner, itemToEdit.getProductCategory()));

    }

    public void btnEditSubmit(View v) {
        Product item = new Product();
        item.setProductName(etProductEditName.getText().toString().trim());
        item.setProductDesc(etProductEditDesc.getText().toString().trim());
        item.setProductCategory(categoryEditSpinner.getSelectedItem().toString().trim());
        item.setProductPrice(Double.parseDouble(etProductEditPrice.getText().toString().trim()));
        item.setId(Integer.parseInt(productID.getText().toString().trim().substring("Nr produktu: ".length())));

        DatabaseManager db = new DatabaseManager(this);
        db.open();
        db.updateItem(item);
        db.close();

        Toast.makeText(EditProductActivity.this, "Produkt zmieniony!", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(EditProductActivity.this, EditProductListActivity.class);
        finish();
        overridePendingTransition(0, 0);
        startActivity(intent);
        overridePendingTransition(0, 0);

    }

    private int getSpinnerIndexBasedOfValue(Spinner spinner, String myString)
    {
        int index = 0;

        for (int i=0;i<spinner.getCount();i++){
            if (spinner.getItemAtPosition(i).toString().equalsIgnoreCase(myString)){
                index = i;
                break;
            }
        }
        return index;
    }
}
