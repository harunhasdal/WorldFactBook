package com.scaleforj.worldfactbook;

import android.os.Bundle;
import android.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


import com.scaleforj.worldfactbook.model.CountriesContent;

/**
 * A fragment representing a single Country detail screen.
 * This fragment is either contained in a {@link CountryListActivity}
 * in two-pane mode (on tablets) or a {@link CountryDetailActivity}
 * on handsets.
 */
public class CountryDetailFragment extends Fragment {

    public static final String ARG_ITEM_ID = "item_id";

    private CountriesContent.Country mItem;

    public CountryDetailFragment() {
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if (getArguments().containsKey(ARG_ITEM_ID)) {
            mItem = CountriesContent.ITEM_MAP.get(getArguments().getString(ARG_ITEM_ID));
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_country_detail, container, false);

        if (mItem != null) {
            ((TextView) rootView.findViewById(R.id.country_name)).setText(mItem.name + " >");
            ((TextView) rootView.findViewById(R.id.country_background)).setText(mItem.background);
        }

        return rootView;
    }
}
