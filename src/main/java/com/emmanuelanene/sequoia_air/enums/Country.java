package com.emmanuelanene.sequoia_air.enums;

public enum Country {
    NIGERIA,
    USA,
    UK
}

/*
Enums are actually just customized classes. Here's what an enum would look like as a regular class

public class Country {
    public static final Country Nigeria = new Country("Nigeria");
    public static final Country USA = new Country("USA");
    public static final Country UK = new Country("UK");

    private String name;

    Country(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}

This is exactly what is happening under the hood whenver you define enums
 */

