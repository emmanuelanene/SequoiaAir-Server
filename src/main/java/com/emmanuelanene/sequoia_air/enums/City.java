package com.emmanuelanene.sequoia_air.enums;

import lombok.Getter;

@Getter
public enum City {

    /*
    Each constant (like LAGOS) is actually calling the constructor of the enum with a specific Country.
    Example: LAGOS(Country.NIGERIA) means “Create a City object called LAGOS and link it to Country.NIGERIA.”
    The semicolon ; after LEEDS(...) tells Java “the constant list has ended.”
     */
    // Nigeria
    LAGOS(Country.NIGERIA),
    ABUJA(Country.NIGERIA),

    // USA
    MIAMI(Country.USA),
    DALLAS(Country.USA),

    // UK
    LONDON(Country.UK),
    LEEDS(Country.UK);


    /*
    This is a variable inside the enum that stores which Country the city belongs to.
    final means once we set it, we can’t change it — makes sense, because a city can’t change its country.
     */
    private final Country country;


    /*
    Every time we create a City constant, Java runs this constructor.
    The country passed in (like Country.NIGERIA) gets stored in the private final Country country variable above.
    This is how Java “remembers” which country each city belongs to.

    ✅ In short:
    The enum creates special constant objects (LAGOS, MIAMI, etc.) that each store one piece of extra information — their country.
    You can later do:

        City.MIAMI.getCountry(); // returns Country.USA
     */
    City(Country country) {
        this.country = country;
    }

}


/*
SEE CITY FOR A MORE BASIC DESCRIPTION OF ENUMS AS CLASSES

Here when we say: DALLAS(Country.USA) - we are basically just defining a parameter to parse whenever DALLAS object is made. Here's what the code would originally look like if it were a class

public class City {
    public static final City LAGOS = new City("LAGOS", Country.NIGERIA);
    public static final City ABUJA = new City("ABUJA", Country.NIGERIA);
    public static final City MIAMI = new City("MIAMI", Country.USA);
    public static final City DALLAS = new City("DALLAS", Country.USA);
    public static final City LONDON = new City("LONDON", Country.UK);
    public static final City LEEDS = new City("LEEDS", Country.UK);

    private String name;
    private Country country;

    City(String name, Country country) {
        this.name = name;
        this.country = country;
    }

    public String getName() {
        return name;
    }

    public Country getCountry() {
        return country;
    }
}

This is basically what evry enum looks like under the hood
 */

