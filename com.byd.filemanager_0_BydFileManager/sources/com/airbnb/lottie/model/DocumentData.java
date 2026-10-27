package com.airbnb.lottie.model;

/* JADX INFO: loaded from: classes.dex */
public class DocumentData {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final String f2510a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final String f2511b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final float f2512c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final Justification f2513d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final int f2514e;
    public final float f;
    public final float g;
    public final int h;
    public final int i;
    public final float j;
    public final boolean k;

    public enum Justification {
        LEFT_ALIGN,
        RIGHT_ALIGN,
        CENTER
    }

    public DocumentData(String str, String str2, float f, Justification justification, int i, float f2, float f3, int i2, int i3, float f4, boolean z) {
        this.f2510a = str;
        this.f2511b = str2;
        this.f2512c = f;
        this.f2513d = justification;
        this.f2514e = i;
        this.f = f2;
        this.g = f3;
        this.h = i2;
        this.i = i3;
        this.j = f4;
        this.k = z;
    }

    public int hashCode() {
        int iHashCode = (((((int) ((((this.f2510a.hashCode() * 31) + this.f2511b.hashCode()) * 31) + this.f2512c)) * 31) + this.f2513d.ordinal()) * 31) + this.f2514e;
        long jFloatToRawIntBits = Float.floatToRawIntBits(this.f);
        return (((iHashCode * 31) + ((int) (jFloatToRawIntBits ^ (jFloatToRawIntBits >>> 32)))) * 31) + this.h;
    }
}
