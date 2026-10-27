package defpackage;

import android.text.TextUtils;

/* JADX INFO: compiled from: ColorState.java */
/* JADX INFO: loaded from: classes3.dex */
public final class dv {
    public final boolean a;
    public String b;
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

    public dv(String str, String str2, String str3, String str4, String str5, String str6, String str7, String str8, String str9, String str10, String str11, String str12) {
        this.c = str;
        this.d = str2;
        this.e = str3;
        this.f = str4;
        this.g = str5;
        this.h = str6;
        this.i = str7;
        this.j = str8;
        this.k = str9;
        this.l = str10;
        this.m = str11;
        this.n = str12;
        boolean z = TextUtils.isEmpty(str) && TextUtils.isEmpty(str2) && TextUtils.isEmpty(str3) && TextUtils.isEmpty(str4) && TextUtils.isEmpty(str5) && TextUtils.isEmpty(str6) && TextUtils.isEmpty(str7) && TextUtils.isEmpty(str8) && TextUtils.isEmpty(str9) && TextUtils.isEmpty(str10) && TextUtils.isEmpty(str11);
        this.a = z;
        if (z && !str12.startsWith("#")) {
            throw new lo2("Default color cannot be a reference, when only default color is available!");
        }
    }

    public static boolean a(String str) {
        return !TextUtils.isEmpty(str) && (!str.startsWith("#") || str.length() == 7 || str.length() == 9);
    }

    public static String b(String str) {
        if (str.startsWith("#")) {
            return str;
        }
        dv dvVar = vo2.i.a.get(str);
        if (dvVar == null || !dvVar.a) {
            return null;
        }
        return dvVar.n;
    }

    public dv(String str, String str2) {
        this.b = str;
        this.n = str2;
        this.a = true;
        if (!str2.startsWith("#")) {
            throw new lo2("Default color cannot be a reference, when only default color is available!");
        }
    }
}
