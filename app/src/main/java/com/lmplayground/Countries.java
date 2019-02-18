package com.lmplayground;

import android.content.Context;
import android.widget.ArrayAdapter;

public class Countries {
    private static final String[] COUNTRIES = new String[] {
            "Macau",
            "Macedonia",
            "Madagascar",
            "Malawi",
            "Malaysia",
            "Maldives",
            "Mali",
            "Malta",
            "Marshall Islands",
            "Mauritania",
            "Mauritius",
            "Mexico",
            "Micronesia",
            "Moldova",
            "Monaco",
            "Mongolia",
            "Montenegro",
            "Morocco",
            "Mozambique"
    };
    static ArrayAdapter<String> getAdapter(Context context){
        return new ArrayAdapter<>(context,
                android.R.layout.simple_dropdown_item_1line, COUNTRIES);

    }
}
