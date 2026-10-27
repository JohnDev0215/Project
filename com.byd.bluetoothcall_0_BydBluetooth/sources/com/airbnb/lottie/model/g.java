package com.airbnb.lottie.model;

/* JADX INFO: compiled from: Marker.java */
/* JADX INFO: loaded from: classes.dex */
public class g {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private static String f2989a = "\r";

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final String f2990b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final float f2991c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final float f2992d;

    public g(String str, float f, float f2) {
        this.f2990b = str;
        this.f2992d = f2;
        this.f2991c = f;
    }

    public boolean a(String str) {
        if (this.f2990b.equalsIgnoreCase(str)) {
            return true;
        }
        if (this.f2990b.endsWith(f2989a)) {
            String str2 = this.f2990b;
            if (str2.substring(0, str2.length() - 1).equalsIgnoreCase(str)) {
                return true;
            }
        }
        return false;
    }
}
