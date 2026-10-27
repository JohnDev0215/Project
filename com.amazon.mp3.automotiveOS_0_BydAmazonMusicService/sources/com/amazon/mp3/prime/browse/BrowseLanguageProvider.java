package com.amazon.mp3.prime.browse;

import android.content.Context;
import com.amazon.mp3.util.Log;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Locale;

/* JADX INFO: loaded from: classes4.dex */
public class BrowseLanguageProvider {
    private static final List<Locale> SUPPORTED_LOCALES;
    private static final String TAG = "BrowseLanguageProvider";

    static {
        ArrayList arrayList = new ArrayList();
        arrayList.add(new Locale("en", "US"));
        arrayList.add(new Locale("en", "GB"));
        arrayList.add(new Locale("de", "DE"));
        arrayList.add(new Locale("it", "IT"));
        arrayList.add(new Locale("fr", "FR"));
        arrayList.add(new Locale("es", "ES"));
        arrayList.add(new Locale("es", "MX"));
        arrayList.add(new Locale("ja", "JP"));
        arrayList.add(new Locale("en", "IN"));
        arrayList.add(new Locale("en", "CA"));
        arrayList.add(new Locale("fr", "CA"));
        arrayList.add(new Locale("en", "AU"));
        arrayList.add(new Locale("en", "NZ"));
        arrayList.add(new Locale("pt", "BR"));
        arrayList.add(new Locale("es", "US"));
        arrayList.add(new Locale("hi", "IN"));
        arrayList.add(new Locale("ta", "IN"));
        arrayList.add(new Locale("bn", "IN"));
        arrayList.add(new Locale("ml", "IN"));
        arrayList.add(new Locale("mr", "IN"));
        arrayList.add(new Locale("pa", "IN"));
        arrayList.add(new Locale("te", "IN"));
        arrayList.add(new Locale("kn", "IN"));
        arrayList.add(new Locale("ar", "SA"));
        arrayList.add(new Locale("ar", "EG"));
        arrayList.add(new Locale("ar", "AE"));
        SUPPORTED_LOCALES = Collections.unmodifiableList(arrayList);
    }

    public boolean isLocaleSupported(final Locale locale) {
        return SUPPORTED_LOCALES.contains(locale);
    }

    public Locale getDeviceLocale(final Context context) {
        return context.getResources().getConfiguration().getLocales().get(0);
    }

    public Locale getLocale(final Context context) {
        Locale deviceLocale = getDeviceLocale(context);
        if (isLocaleSupported(deviceLocale)) {
            Log.debug(TAG, "Locale used %s", deviceLocale);
            return deviceLocale;
        }
        if (deviceLocale.getLanguage().equals("es")) {
            return new Locale("es", "MX");
        }
        String str = TAG;
        Locale locale = Locale.US;
        Log.debug(str, "Default locale used %s", locale);
        return locale;
    }
}
