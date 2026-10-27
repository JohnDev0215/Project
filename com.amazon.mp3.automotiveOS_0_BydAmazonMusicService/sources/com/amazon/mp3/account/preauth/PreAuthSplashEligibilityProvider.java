package com.amazon.mp3.account.preauth;

import android.content.Context;
import android.content.SharedPreferences;
import com.amazon.mp3.prime.browse.BrowseLanguageProvider;
import com.amazon.mp3.util.Log;
import com.amazon.music.marketplace.MusicTerritory;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;

/* JADX INFO: loaded from: classes3.dex */
public class PreAuthSplashEligibilityProvider {
    private static final List<String> PREAUTH_SPLASH_ALLOWLIST = new ArrayList(Arrays.asList(MusicTerritory.US.value, MusicTerritory.UK.value, MusicTerritory.GERMANY.value, MusicTerritory.JAPAN.value, MusicTerritory.FRANCE.value, MusicTerritory.ITALY.value, MusicTerritory.SPAIN.value, MusicTerritory.CANADA.value, MusicTerritory.AUSTRALIA.value, MusicTerritory.NEW_ZEALAND.value, MusicTerritory.MEXICO.value, MusicTerritory.BRAZIL.value));
    private static final String TAG = "PreAuthSplashEligibilityProvider";
    private final Context mContext;
    private final String mLocaleCountry;

    public PreAuthSplashEligibilityProvider(final Context context) {
        this.mContext = context;
        Locale locale = new BrowseLanguageProvider().getLocale(context);
        String country = locale.getCountry();
        this.mLocaleCountry = country;
        Log.info(TAG, "Initializing pre auth eligibility for Locale: %s, Country: %s", locale, country);
    }

    public boolean isEligibleForSplash(final boolean isUserRecognized) {
        if (isUserRecognized || hasReachedAuthPortal()) {
            return false;
        }
        return isLocaleEligible(this.mLocaleCountry);
    }

    private boolean isLocaleEligible(final String localeCountry) {
        return PREAUTH_SPLASH_ALLOWLIST.contains(localeCountry);
    }

    public boolean hasReachedAuthPortal() {
        return this.mContext.getSharedPreferences("preAuthSplashScreen", 0).getBoolean("isSplashActivityRestart", false);
    }

    public void setHasReachedAuthPortal(final boolean hasReachedAuthPortal) {
        SharedPreferences.Editor editorEdit = this.mContext.getSharedPreferences("preAuthSplashScreen", 0).edit();
        editorEdit.putBoolean("isSplashActivityRestart", hasReachedAuthPortal);
        editorEdit.apply();
    }

    public static void reset(final Context context) {
        context.getSharedPreferences("preAuthSplashScreen", 0).edit().clear().apply();
    }
}
