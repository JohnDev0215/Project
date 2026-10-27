package com.airbnb.lottie.t;

import androidx.annotation.RestrictTo;
import com.airbnb.lottie.t.k.n;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
@RestrictTo({RestrictTo.Scope.LIBRARY})
public class d {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final List<n> f791a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final char f792b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private final double f793c;
    private final double d;
    private final String e;
    private final String f;

    public d(List<n> list, char c2, double d, double d2, String str, String str2) {
        this.f791a = list;
        this.f792b = c2;
        this.f793c = d;
        this.d = d2;
        this.e = str;
        this.f = str2;
    }

    public static int c(char c2, String str, String str2) {
        return ((((0 + c2) * 31) + str.hashCode()) * 31) + str2.hashCode();
    }

    public List<n> a() {
        return this.f791a;
    }

    public double b() {
        return this.d;
    }

    public int hashCode() {
        return c(this.f792b, this.f, this.e);
    }
}
