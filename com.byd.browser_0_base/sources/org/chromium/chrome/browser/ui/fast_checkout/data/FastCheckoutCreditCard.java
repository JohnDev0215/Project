package org.chromium.chrome.browser.ui.fast_checkout.data;

import android.content.Context;
import android.util.ArrayMap;
import org.chromium.base.annotations.CalledByNative;
import org.chromium.chrome.browser.ui.fast_checkout.R$drawable;
import org.chromium.chrome.browser.ui.fast_checkout.R$string;
import org.chromium.url.GURL;

/* JADX INFO: loaded from: classes2.dex */
public class FastCheckoutCreditCard {
    public static final ArrayMap s;
    public final String a;
    public final String b;
    public final boolean c;
    public final boolean d;
    public final String e;
    public final String f;
    public final String g;
    public final String h;
    public final String i;
    public final String j;
    public final String k;
    public final String l;
    public final String m;
    public final long n;
    public final String o;
    public final GURL p;
    public final int q;
    public final String r;

    static {
        ArrayMap arrayMap = new ArrayMap();
        arrayMap.put("americanExpressCC", Integer.valueOf(R$drawable.amex_card));
        arrayMap.put("dinersCC", Integer.valueOf(R$drawable.diners_card));
        arrayMap.put("discoverCC", Integer.valueOf(R$drawable.discover_card));
        arrayMap.put("eloCC", Integer.valueOf(R$drawable.elo_card));
        arrayMap.put("genericCC", Integer.valueOf(R$drawable.ic_credit_card_black));
        arrayMap.put("jcbCC", Integer.valueOf(R$drawable.jcb_card));
        arrayMap.put("masterCardCC", Integer.valueOf(R$drawable.mc_card));
        arrayMap.put("mirCC", Integer.valueOf(R$drawable.mir_card));
        arrayMap.put("troyCC", Integer.valueOf(R$drawable.troy_card));
        arrayMap.put("unionPayCC", Integer.valueOf(R$drawable.unionpay_card));
        arrayMap.put("visaCC", Integer.valueOf(R$drawable.visa_card));
        arrayMap.put("googlePay", Integer.valueOf(R$drawable.google_pay));
        s = arrayMap;
    }

    @CalledByNative
    public FastCheckoutCreditCard(String str, String str2, boolean z, boolean z2, String str3, String str4, String str5, String str6, String str7, String str8, String str9, String str10, String str11, long j, String str12, GURL gurl, int i, String str13) {
        this.a = str;
        this.b = str2;
        this.c = z;
        this.d = z2;
        this.e = str3;
        this.f = str4;
        this.g = str5;
        this.h = str6;
        this.i = str7;
        this.j = str8;
        this.k = str9;
        this.l = str10;
        this.m = str11;
        this.n = j;
        this.o = str12;
        this.p = gurl;
        this.q = i;
        this.r = str13;
    }

    public final String a(Context context) {
        return getMonth() + context.getResources().getString(R$string.autofill_expiration_date_separator) + getYear();
    }

    public final int b() {
        ArrayMap arrayMap = s;
        String str = this.k;
        return arrayMap.containsKey(str) ? ((Integer) arrayMap.get(str)).intValue() : R$drawable.ic_credit_card_black;
    }

    @CalledByNative
    public String getBasicCardIssuerNetwork() {
        return this.j;
    }

    @CalledByNative
    public String getBillingAddressId() {
        return this.l;
    }

    @CalledByNative
    public GURL getCardArtUrl() {
        return this.p;
    }

    @CalledByNative
    public String getGUID() {
        return this.a;
    }

    @CalledByNative
    public long getInstrumentId() {
        return this.n;
    }

    @CalledByNative
    public boolean getIsCached() {
        return this.d;
    }

    @CalledByNative
    public boolean getIsLocal() {
        return this.c;
    }

    @CalledByNative
    public String getMonth() {
        return this.h;
    }

    @CalledByNative
    public String getName() {
        return this.e;
    }

    @CalledByNative
    public String getNickname() {
        return this.o;
    }

    @CalledByNative
    public String getNumber() {
        return this.f;
    }

    @CalledByNative
    public String getOrigin() {
        return this.b;
    }

    @CalledByNative
    public String getProductDescription() {
        return this.r;
    }

    @CalledByNative
    public String getServerId() {
        return this.m;
    }

    @CalledByNative
    public int getVirtualCardEnrollmentState() {
        return this.q;
    }

    @CalledByNative
    public String getYear() {
        return this.i;
    }
}
