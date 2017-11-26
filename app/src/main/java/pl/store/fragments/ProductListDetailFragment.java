package pl.store.fragments;


import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import pl.store.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class ProductListDetailFragment extends Fragment {


    public ProductListDetailFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.product_list_detail_fragment, container, false);
    }

}
