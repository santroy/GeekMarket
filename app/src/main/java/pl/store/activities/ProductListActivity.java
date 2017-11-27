package pl.store.activities;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.widget.TextView;
import android.widget.Toast;


import pl.store.database.DatabaseManager;
import pl.store.R;
import pl.store.fragments.ProductListFragment;
import pl.store.model.Product;

public class ProductListActivity extends AppCompatActivity implements ProductListFragment.ProductItemListener {

    TextView idResultField, descResultField, priceResultField, categoryResultField, nameResultField;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.product_list_activity);

        if(findViewById(R.id.layout_product_list_default) != null) {
            android.app.FragmentManager fragmentManager = getFragmentManager();
            fragmentManager.beginTransaction()
                    .hide(fragmentManager.findFragmentById(R.id.productListDetailFragment))
                    .show(fragmentManager.findFragmentById(R.id.productListFragment))
                    .commit();
        }

        if(findViewById(R.id.layout_product_list_land) != null) {
            android.app.FragmentManager fragmentManager = getFragmentManager();
            fragmentManager.beginTransaction()
                    .show(fragmentManager.findFragmentById(R.id.productListDetailFragment))
                    .show(fragmentManager.findFragmentById(R.id.productListFragment))
                    .commit();
        }

        ActionBar actionBar = getSupportActionBar();
        actionBar.setTitle(" Lista produktów ");
        actionBar.setIcon(R.mipmap.geek_market_logo);
        actionBar.setDisplayUseLogoEnabled(true);
        actionBar.setDisplayShowHomeEnabled(true);
        actionBar.setDisplayHomeAsUpEnabled(true);

        idResultField = (TextView) findViewById(R.id.idResultField);
        nameResultField = (TextView) findViewById(R.id.nameResultField);
        descResultField = (TextView) findViewById(R.id.descResultField);
        priceResultField = (TextView) findViewById(R.id.priceResultField);
        categoryResultField = (TextView) findViewById(R.id.categoryResultField);

//        try {
//            ArrayList<Product> products = new ArrayList<>();
//            DatabaseManager db = new DatabaseManager(this);
//            db.open();
//            products = db.getData();
//            db.close();
//        } catch (Exception e ) {
//           Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();
//        }

    }

    @Override
    public void onProductItemSelected(int index) {
        try {

            if(findViewById(R.id.layout_product_list_default) != null) {
                android.app.FragmentManager fragmentManager = getFragmentManager();
                fragmentManager.beginTransaction()
                        .show(fragmentManager.findFragmentById(R.id.productListDetailFragment))
                        .hide(fragmentManager.findFragmentById(R.id.productListFragment))
                        .addToBackStack(null)
                        .commit();
            }

            DatabaseManager db = new DatabaseManager(this);
            db.open();
            Product itemSelected = db.getItem(index);
            db.close();

            idResultField.setText("Numer  katalogowy: " + itemSelected.getId());
            nameResultField.setText("Nazwa: " + itemSelected.getProductName());
            descResultField.setText("Opis: " + itemSelected.getProductDesc());
            priceResultField.setText("Cena: " + itemSelected.getProductPrice().toString());
            categoryResultField.setText("Kategoria:  " + itemSelected.getProductCategory());


        } catch (Exception e) {
           Toast.makeText(this, e.getMessage(), Toast.LENGTH_SHORT).show();
        }


    }
}
