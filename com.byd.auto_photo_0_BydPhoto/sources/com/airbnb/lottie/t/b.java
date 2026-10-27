package com.airbnb.lottie.t;

import androidx.annotation.ColorInt;
import androidx.annotation.RestrictTo;

/* JADX INFO: loaded from: classes.dex */
@RestrictTo({RestrictTo.Scope.LIBRARY})
public class b {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final String f782a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final String f783b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final double f784c;
    public final a d;
    public final int e;
    public final double f;
    public final double g;

    @ColorInt
    public final int h;

    @ColorInt
    public final int i;
    public final double j;
    public final boolean k;

    public enum a {
        LEFT_ALIGN,
        RIGHT_ALIGN,
        CENTER
    }

    public b(String str, String str2, double d, a aVar, int i, double d2, double d3, @ColorInt int i2, @ColorInt int i3, double d4, boolean z) {
        this.f782a = str;
        this.f783b = str2;
        this.f784c = d;
        this.d = aVar;
        this.e = i;
        this.f = d2;
        this.g = d3;
        this.h = i2;
        this.i = i3;
        this.j = d4;
        this.k = z;
    }

    public int hashCode() {
        int iHashCode = (((((int) (((double) (((this.f782a.hashCode() * 31) + this.f783b.hashCode()) * 31)) + this.f784c)) * 31) + this.d.ordinal()) * 31) + this.e;
        long jDoubleToLongBits = Double.doubleToLongBits(this.f);
        return (((iHashCode * 31) + ((int) (jDoubleToLongBits ^ (jDoubleToLongBits >>> 32)))) * 31) + this.h;
    }
}
