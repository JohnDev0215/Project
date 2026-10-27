package com.airbnb.lottie.t;

/* JADX INFO: loaded from: classes.dex */
public class h {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private static String f798a = "\r";

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final String f799b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final float f800c;
    public final float d;

    public h(String str, float f, float f2) {
        this.f799b = str;
        this.d = f2;
        this.f800c = f;
    }

    public boolean a(String str) {
        if (this.f799b.equalsIgnoreCase(str)) {
            return true;
        }
        if (this.f799b.endsWith(f798a)) {
            String str2 = this.f799b;
            if (str2.substring(0, str2.length() - 1).equalsIgnoreCase(str)) {
                return true;
            }
        }
        return false;
    }
}
