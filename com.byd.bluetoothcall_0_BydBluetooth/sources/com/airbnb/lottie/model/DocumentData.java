package com.airbnb.lottie.model;

/* JADX INFO: loaded from: classes.dex */
public class DocumentData {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final String f2894a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final String f2895b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final double f2896c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final Justification f2897d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final int f2898e;
    public final double f;
    public final double g;
    public final int h;
    public final int i;
    public final double j;
    public final boolean k;

    public enum Justification {
        LEFT_ALIGN,
        RIGHT_ALIGN,
        CENTER
    }

    public DocumentData(String str, String str2, double d2, Justification justification, int i, double d3, double d4, int i2, int i3, double d5, boolean z) {
        this.f2894a = str;
        this.f2895b = str2;
        this.f2896c = d2;
        this.f2897d = justification;
        this.f2898e = i;
        this.f = d3;
        this.g = d4;
        this.h = i2;
        this.i = i3;
        this.j = d5;
        this.k = z;
    }

    public int hashCode() {
        int iHashCode = (((((int) (((double) (((this.f2894a.hashCode() * 31) + this.f2895b.hashCode()) * 31)) + this.f2896c)) * 31) + this.f2897d.ordinal()) * 31) + this.f2898e;
        long jDoubleToLongBits = Double.doubleToLongBits(this.f);
        return (((iHashCode * 31) + ((int) (jDoubleToLongBits ^ (jDoubleToLongBits >>> 32)))) * 31) + this.h;
    }
}
