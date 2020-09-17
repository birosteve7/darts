/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package darts;

import java.text.Collator;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Locale;
/**
 *
 * @author István
 */
public class CountryList {
    class Country {
        public String name;

        Country(String name) {
            this.name = name;
        }

        public String toString() {
            return name.toUpperCase();
        }
    }
    
    public List<Country> getCountry(){   
        List<Country> countries = new ArrayList<Country>();
        Locale[] locales = Locale.getAvailableLocales();
        for (Locale locale : locales) {
            String name = locale.getDisplayCountry();

            if (!"".equals(name)) {
                countries.add(new Country(name));
            }
        }

        Collections.sort(countries, new CountryComparator());
        return countries;
    }

    class CountryComparator implements Comparator<Country> {
        private Comparator comparator;

        CountryComparator() {
        comparator = Collator.getInstance();
    }

        public int compare(Country o1, Country o2) {
            return comparator.compare(o1.name, o2.name);
        }
    }


}