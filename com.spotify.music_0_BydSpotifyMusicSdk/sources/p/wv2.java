package p;

import java.util.HashMap;
import java.util.Locale;

/* JADX INFO: loaded from: classes.dex */
public final class wv2 {
    public final Locale a;
    public final String b;
    public final String c;
    public final String d;
    public final String e;
    public final String f;
    public final int g;
    public final int h;
    public final int i;
    public final String j;
    public final String k;

    public wv2(Locale locale, String str, String str2, String str3, String str4, int i, int i2, int i3, String str5, String str6, int i4) {
        locale = (i4 & 1) != 0 ? null : locale;
        str = (i4 & 2) != 0 ? null : str;
        str2 = (i4 & 4) != 0 ? null : str2;
        str3 = (i4 & 8) != 0 ? null : str3;
        str4 = (i4 & 16) != 0 ? null : str4;
        String str7 = (i4 & 32) != 0 ? "from_token" : null;
        i = (i4 & 64) != 0 ? 24 : i;
        i2 = (i4 & 128) != 0 ? 0 : i2;
        i3 = (i4 & 256) != 0 ? 3 : i3;
        str5 = (i4 & 512) != 0 ? "car" : str5;
        str6 = (i4 & 1024) != 0 ? null : str6;
        v03.j(str5, "platform");
        this.a = locale;
        this.b = str;
        this.c = str2;
        this.d = str3;
        this.e = str4;
        this.f = str7;
        this.g = i;
        this.h = i2;
        this.i = i3;
        this.j = str5;
        this.k = str6;
    }

    public final HashMap a() {
        HashMap map = new HashMap(11);
        map.put("platform", this.j);
        map.put("content_limit", Integer.valueOf(this.i));
        map.put("limit", Integer.valueOf(Math.min(this.g, 50)));
        map.put("offset", Integer.valueOf(this.h));
        Locale locale = this.a;
        if (locale != null) {
            map.put("locale", d7.a("_", locale));
        }
        String str = this.f;
        if (str != null) {
            map.put("market", str);
        }
        String str2 = this.b;
        if (str2 != null) {
            map.put("country", str2);
        }
        String str3 = this.c;
        if (str3 != null) {
            map.put("types", str3);
        }
        String str4 = this.d;
        if (str4 != null) {
            map.put("signal", str4);
        }
        String str5 = this.e;
        if (str5 != null) {
            map.put("additional_types", str5);
        }
        String str6 = this.k;
        if (str6 != null) {
            map.put("timestamp", str6);
        }
        return map;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof wv2)) {
            return false;
        }
        wv2 wv2Var = (wv2) obj;
        return v03.b(this.a, wv2Var.a) && v03.b(this.b, wv2Var.b) && v03.b(this.c, wv2Var.c) && v03.b(this.d, wv2Var.d) && v03.b(this.e, wv2Var.e) && v03.b(this.f, wv2Var.f) && this.g == wv2Var.g && this.h == wv2Var.h && this.i == wv2Var.i && v03.b(this.j, wv2Var.j) && v03.b(this.k, wv2Var.k);
    }

    public final int hashCode() {
        Locale locale = this.a;
        int iHashCode = (locale == null ? 0 : locale.hashCode()) * 31;
        String str = this.b;
        int iHashCode2 = (iHashCode + (str == null ? 0 : str.hashCode())) * 31;
        String str2 = this.c;
        int iHashCode3 = (iHashCode2 + (str2 == null ? 0 : str2.hashCode())) * 31;
        String str3 = this.d;
        int iHashCode4 = (iHashCode3 + (str3 == null ? 0 : str3.hashCode())) * 31;
        String str4 = this.e;
        int iHashCode5 = (iHashCode4 + (str4 == null ? 0 : str4.hashCode())) * 31;
        String str5 = this.f;
        int iB = hf2.b(this.j, hf2.a(this.i, hf2.a(this.h, hf2.a(this.g, (iHashCode5 + (str5 == null ? 0 : str5.hashCode())) * 31, 31), 31), 31), 31);
        String str6 = this.k;
        return iB + (str6 != null ? str6.hashCode() : 0);
    }

    public final String toString() {
        StringBuilder sb = new StringBuilder("WebApiQueryParams(locale=");
        sb.append(this.a);
        sb.append(", country=");
        sb.append(this.b);
        sb.append(", types=");
        sb.append(this.c);
        sb.append(", signal=");
        sb.append(this.d);
        sb.append(", additionalTypes=");
        sb.append(this.e);
        sb.append(", market=");
        sb.append(this.f);
        sb.append(", limit=");
        sb.append(this.g);
        sb.append(", offset=");
        sb.append(this.h);
        sb.append(", contentLimit=");
        sb.append(this.i);
        sb.append(", platform=");
        sb.append(this.j);
        sb.append(", timestamp=");
        return hf2.d(sb, this.k, ')');
    }
}
