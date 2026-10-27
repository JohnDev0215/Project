package com.amazon.device.ads;

import android.content.SharedPreferences;

/* JADX INFO: loaded from: classes2.dex */
public class GDPRInfo {
    private static final String LOGTAG = "GDPRInfo";
    private MobileAdsLogger logger = new MobileAdsLoggerFactory().createMobileAdsLogger(LOGTAG);
    private SharedPreferences prefs;

    public GDPRInfo(SharedPreferences sharedPreferences) {
        this.prefs = sharedPreferences;
    }

    public String getGdprSubjectTcf2() {
        return this.prefs.getString("IABTCF_gdprApplies", null);
    }

    public String getGdprConsent_Tcf2() {
        return this.prefs.getString("IABTCF_TCString", null);
    }
}
