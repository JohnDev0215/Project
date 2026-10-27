package com.airbnb.lottie.model;

/* JADX INFO: loaded from: classes.dex */
public class DocumentData {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final String f2446a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final String f2447b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final float f2448c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final Justification f2449d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final int f2450e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final float f2451f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public final float f2452g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public final int f2453h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final int f2454i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public final float f2455j;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    public final boolean f2456k;

    public enum Justification {
        LEFT_ALIGN,
        RIGHT_ALIGN,
        CENTER
    }

    public DocumentData(String str, String str2, float f8, Justification justification, int i8, float f9, float f10, int i9, int i10, float f11, boolean z7) {
        this.f2446a = str;
        this.f2447b = str2;
        this.f2448c = f8;
        this.f2449d = justification;
        this.f2450e = i8;
        this.f2451f = f9;
        this.f2452g = f10;
        this.f2453h = i9;
        this.f2454i = i10;
        this.f2455j = f11;
        this.f2456k = z7;
    }

    public int hashCode() {
        int iHashCode = (((((int) ((((this.f2446a.hashCode() * 31) + this.f2447b.hashCode()) * 31) + this.f2448c)) * 31) + this.f2449d.ordinal()) * 31) + this.f2450e;
        long jFloatToRawIntBits = Float.floatToRawIntBits(this.f2451f);
        return (((iHashCode * 31) + ((int) (jFloatToRawIntBits ^ (jFloatToRawIntBits >>> 32)))) * 31) + this.f2453h;
    }
}
