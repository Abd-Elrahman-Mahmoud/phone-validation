package com.phone.validation.phonevalidation.configurations;

import com.phone.validation.phonevalidation.model.Country;

import java.util.ArrayList;

public class ContriesLoading {

    public static ArrayList<Country> countries = new ArrayList<>();

    static {
        countries.add(Country.builder().name("Cameroon").code("+237").regex("\\(237\\)\\ ?[2368]\\d{7,8}$").build());
        countries.add(Country.builder().name("Ethiopia").code("+251").regex("\\(251\\)\\ ?[1-59]\\d{8}$").build());
        countries.add(Country.builder().name("Morocco").code("+212").regex("\\(212\\)\\ ?[5-9]\\d{8}$").build());
        countries.add(Country.builder().name("Mozambique").code("+258").regex("\\(258\\)\\ ?[28]\\d{7,8}$").build());
        countries.add(Country.builder().name("Uganda").code("+256").regex("\\(256\\)\\ ?\\d{9}$").build());
    }

}
