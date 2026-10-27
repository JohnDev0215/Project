package com.airbnb.lottie.model;

/* JADX INFO: compiled from: Marker.java */
/* JADX INFO: loaded from: classes.dex */
public class g {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private static String f2605a = "\r";

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final String f2606b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final float f2607c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final float f2608d;

    public g(String str, float f, float f2) {
        this.f2606b = str;
        this.f2608d = f2;
        this.f2607c = f;
    }

    public boolean a(String str) {
        if (this.f2606b.equalsIgnoreCase(str)) {
            return true;
        }
        if (this.f2606b.endsWith(f2605a)) {
            String str2 = this.f2606b;
            if (str2.substring(0, str2.length() - 1).equalsIgnoreCase(str)) {
                return true;
            }
        }
        return false;
    }
}
