package org.chromium.chrome.browser.ui.fast_checkout.data;

import org.chromium.base.annotations.CalledByNative;

/* JADX INFO: loaded from: classes2.dex */
public class FastCheckoutAutofillProfile {
    public final String a;
    public final String b;
    public final String c;
    public final String d;
    public final String e;
    public final String f;
    public final String g;
    public final String h;
    public final String i;
    public final String j;
    public final String k;
    public final String l;
    public final String m;
    public final String n;
    public final String o;
    public final String p;

    @CalledByNative
    public FastCheckoutAutofillProfile(String str, String str2, boolean z, String str3, String str4, String str5, String str6, String str7, String str8, String str9, String str10, String str11, String str12, String str13, String str14, String str15, String str16) {
        this.a = str;
        this.b = str2;
        this.c = str3;
        this.d = str4;
        this.e = str5;
        this.f = str6;
        this.g = str7;
        this.h = str8;
        this.i = str9;
        this.j = str10;
        this.k = str11;
        this.l = str12;
        this.m = str13;
        this.n = str14;
        this.o = str15;
        this.p = str16;
    }

    @CalledByNative
    public String getCompanyName() {
        return this.e;
    }

    @CalledByNative
    public String getCountryCode() {
        return this.l;
    }

    @CalledByNative
    public String getDependentLocality() {
        return this.i;
    }

    @CalledByNative
    public String getEmailAddress() {
        return this.o;
    }

    @CalledByNative
    public String getFullName() {
        return this.d;
    }

    @CalledByNative
    public String getGUID() {
        return this.a;
    }

    @CalledByNative
    public String getHonorificPrefix() {
        return this.c;
    }

    @CalledByNative
    public String getLanguageCode() {
        return this.p;
    }

    @CalledByNative
    public String getLocality() {
        return this.h;
    }

    @CalledByNative
    public String getOrigin() {
        return this.b;
    }

    @CalledByNative
    public String getPhoneNumber() {
        return this.n;
    }

    @CalledByNative
    public String getPostalCode() {
        return this.j;
    }

    @CalledByNative
    public String getRegion() {
        return this.g;
    }

    @CalledByNative
    public String getSortingCode() {
        return this.k;
    }

    @CalledByNative
    public String getStreetAddress() {
        return this.f;
    }
}
