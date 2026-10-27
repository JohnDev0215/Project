package com.amazon.alexa.voice.superbowl.plugins.system;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Locale;
import java.util.Set;

/* JADX INFO: loaded from: classes2.dex */
public final class SuperbowlLocale {
    private static final Set<Locale> ACCEPTED_LOCALES = new HashSet(Arrays.asList(new Locale("en", "US"), new Locale("en", "CA"), new Locale("en", "GB"), new Locale("en", "IN"), new Locale("de", "DE"), new Locale("ja", "JP")));

    private SuperbowlLocale() {
        throw new IllegalStateException("No instances, dude!");
    }

    public static Locale from(Locale locale) {
        return getAcceptedLocales().contains(locale) ? locale : new Locale("en", "US");
    }

    public static Set<Locale> getAcceptedLocales() {
        return ACCEPTED_LOCALES;
    }
}
