package pl.store.fragments;


import android.app.ListFragment;
import android.content.Context;
import android.os.Bundle;
import android.app.Fragment;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

import pl.store.R;
import pl.store.database.DatabaseManager;
import pl.store.model.Product;

/**
 * A simple {@link Fragment} subclass.
 */
public class ProductListFragment extends ListFragment {

    private ProductItemListener productItemListener;


    public ProductListFragment() {
        // Required empty public constructor
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        ArrayList<Product> products;

        try {
            DatabaseManager db = new DatabaseManager(this.getActivity());
            db.open();
            products = db.getData();
            db.close();
            setListAdapter(new ArrayAdapter<Product>(this.getActivity(), android.R.layout.simple_list_item_1, products));
        } catch(Exception e) {
            Toast.makeText(this.getActivity(), e.getMessage(), Toast.LENGTH_SHORT).show();
        }

    }

    public interface ProductItemListener {
        public void onProductItemSelected(int index);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);

        try {
            productItemListener = (ProductItemListener) context;
        } catch(ClassCastException e) {
            throw new ClassCastException(context.toString() + "must implement the interface called ProductItemListener");
        }
    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        Product itemSelected = (Product) getListAdapter().getItem(position);
        productItemListener.onProductItemSelected(itemSelected.getId());

    }
}
