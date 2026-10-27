package com.android.providers.contacts;

import android.content.Context;
import android.location.Country;
import android.location.CountryDetector;
import android.location.CountryListener;
import android.os.Looper;
import java.util.Locale;

/* JADX INFO: loaded from: classes.dex */
public class CountryMonitor {
    private final Context mContext;
    private String mCurrentCountryIso;
    private final Runnable mUpdateCallback;

    public CountryMonitor(Context context) {
        this(context, null);
    }

    public CountryMonitor(Context context, Runnable runnable) {
        this.mContext = context;
        this.mUpdateCallback = runnable;
    }

    public synchronized String getCountryIso() {
        if (this.mCurrentCountryIso == null) {
            CountryDetector countryDetector = (CountryDetector) this.mContext.getSystemService("country_detector");
            Country countryDetectCountry = countryDetector != null ? countryDetector.detectCountry() : null;
            if (countryDetectCountry == null) {
                return Locale.getDefault().getCountry();
            }
            this.mCurrentCountryIso = countryDetectCountry.getCountryIso();
            countryDetector.addCountryListener(new CountryListener() { // from class: com.android.providers.contacts.CountryMonitor.1
                public void onCountryDetected(Country country) {
                    CountryMonitor.this.mCurrentCountryIso = country.getCountryIso();
                    if (CountryMonitor.this.mUpdateCallback != null) {
                        CountryMonitor.this.mUpdateCallback.run();
                    }
                }
            }, Looper.getMainLooper());
        }
        return this.mCurrentCountryIso;
    }
}
