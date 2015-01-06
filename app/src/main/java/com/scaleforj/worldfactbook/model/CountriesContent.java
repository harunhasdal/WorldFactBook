package com.scaleforj.worldfactbook.model;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Helper class for providing sample name for user interfaces created by
 * Android template wizards.
 * <p>
 */
public class CountriesContent {

    /**
     * An array of country items.
     */
    public static List<Country> ITEMS = new ArrayList<Country>();

    /**
     * A map of sample country items, by ID.
     */
    public static Map<String, Country> ITEM_MAP = new HashMap<String, Country>();


    public static void addItem(Country item) {
        ITEMS.add(item);
        ITEM_MAP.put(item.id, item);
    }

    /**
     * A dummy item representing a piece of name.
     */
    public static class Country {
        public String id;
        public String name;
        public String background;
        public JSONObject geo;
        public JSONObject people;
        public JSONObject economy;

        public Country(JSONObject country) throws JSONException {
            this.id = country.getString("id");
            this.name = country.getString("name");
            this.background = country.getString("background");
            this.geo = country.getJSONObject("geo");
            this.people = country.getJSONObject("people");
            this.economy = country.getJSONObject("economy");
        }

        @Override
        public String toString() {
            return name;
        }
    }
}
