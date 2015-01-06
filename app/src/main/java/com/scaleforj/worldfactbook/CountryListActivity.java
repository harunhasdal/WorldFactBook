package com.scaleforj.worldfactbook;

import android.content.Intent;
import android.os.Bundle;
import android.app.Activity;

import com.scaleforj.worldfactbook.model.CountriesContent;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;


/**
 * An activity representing a list of Countries. This activity
 * has different presentations for handset and tablet-size devices. On
 * handsets, the activity presents a list of items, which when touched,
 * lead to a {@link CountryDetailActivity} representing
 * item details. On tablets, the activity presents the list of items and
 * item details side-by-side using two vertical panes.
 * <p>
 * The activity makes heavy use of fragments. The list of items is a
 * {@link CountryListFragment} and the item details
 * (if present) is a {@link CountryDetailFragment}.
 * <p>
 * This activity also implements the required
 * {@link CountryListFragment.Callbacks} interface
 * to listen for item selections.
 */
public class CountryListActivity extends Activity
        implements CountryListFragment.Callbacks {

    private boolean mTwoPane;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        try {
            loadData();
        } catch (Exception e) {
            e.printStackTrace();
        }
        setContentView(R.layout.activity_country_list);

        if (findViewById(R.id.country_detail_container) != null) {
            mTwoPane = true;
            ((CountryListFragment) getFragmentManager()
                    .findFragmentById(R.id.country_list))
                    .setActivateOnItemClick(true);
        }

    }

    private void loadData() throws Exception {

        InputStream is = getApplicationContext().getAssets().open("countriesContent.json");
        int size = is.available();
        byte[] buffer = new byte[size];
        is.read(buffer);
        is.close();
        JSONObject content = new JSONObject( new String(buffer, "UTF-8") );

        JSONArray countries = content.getJSONArray("countries");

        for(int i = 0; i < countries.length(); i ++){
            JSONObject country = countries.getJSONObject(i);
            CountriesContent.addItem(new CountriesContent.Country(country));
        }

    }

    /**
     * Callback method from {@link CountryListFragment.Callbacks}
     * indicating that the item with the given ID was selected.
     */
    @Override
    public void onItemSelected(String id) {
        if (mTwoPane) {
            Bundle arguments = new Bundle();
            arguments.putString(CountryDetailFragment.ARG_ITEM_ID, id);
            CountryDetailFragment fragment = new CountryDetailFragment();
            fragment.setArguments(arguments);
            getFragmentManager().beginTransaction()
                    .replace(R.id.country_detail_container, fragment)
                    .commit();

        } else {
            Intent detailIntent = new Intent(this, CountryDetailActivity.class);
            detailIntent.putExtra(CountryDetailFragment.ARG_ITEM_ID, id);
            startActivity(detailIntent);
        }
    }
}
