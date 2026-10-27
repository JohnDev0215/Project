package com.amazon.identity.auth.device;

/* JADX INFO: compiled from: DCP */
/* JADX INFO: loaded from: classes3.dex */
public final class fa {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final String f315a;
    public final String b;
    public final String c;

    public fa(String str, String str2) {
        this.f315a = e.a(str, str2);
        this.b = str;
        this.c = str2;
    }

    public static fa a(String str) {
        String strSubstring;
        String str2;
        int iIndexOf = str.indexOf(47);
        if (iIndexOf == -1) {
            str2 = null;
            strSubstring = str;
        } else {
            String strSubstring2 = str.substring(0, iIndexOf);
            strSubstring = str.substring(iIndexOf + 1);
            str2 = strSubstring2;
        }
        return new fa(str, str2, strSubstring);
    }

    public fa(String str, String str2, String str3) {
        this.f315a = str;
        this.b = str2;
        this.c = str3;
    }
}
