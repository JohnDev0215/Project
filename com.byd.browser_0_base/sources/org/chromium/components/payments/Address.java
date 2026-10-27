package org.chromium.components.payments;

import android.os.Bundle;
import java.util.regex.Pattern;

/* JADX INFO: loaded from: classes3.dex */
public final class Address {
    public static Pattern k;
    public final String a;
    public final String[] b;
    public final String c;
    public final String d;
    public final String e;
    public final String f;
    public final String g;
    public final String h;
    public final String i;
    public final String j;

    public Address() {
        this.a = "";
        this.b = new String[0];
        this.c = "";
        this.d = "";
        this.e = "";
        this.f = "";
        this.g = "";
        this.h = "";
        this.i = "";
        this.j = "";
    }

    public static Address a(Bundle bundle) {
        return new Address(bundle.getString("countryCode", ""), bundle.getStringArray("addressLines"), bundle.getString("region", ""), bundle.getString("city", ""), bundle.getString("dependentLocality", ""), bundle.getString("postalCode", ""), bundle.getString("sortingCode", ""), bundle.getString("organization", ""), bundle.getString("recipient", ""), bundle.getString("phone", ""));
    }

    public Address(String str, String[] strArr, String str2, String str3, String str4, String str5, String str6, String str7, String str8, String str9) {
        this.a = str;
        this.b = strArr;
        this.c = str2;
        this.d = str3;
        this.e = str4;
        this.f = str5;
        this.g = str6;
        this.h = str7;
        this.i = str8;
        this.j = str9;
    }
}
