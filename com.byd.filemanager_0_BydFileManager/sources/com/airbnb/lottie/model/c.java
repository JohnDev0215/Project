package com.airbnb.lottie.model;

import com.airbnb.lottie.model.content.j;
import java.util.List;

/* JADX INFO: compiled from: FontCharacter.java */
/* JADX INFO: loaded from: classes.dex */
public class c {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final List<j> f2523a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final char f2524b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private final double f2525c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private final double f2526d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private final String f2527e;
    private final String f;

    public c(List<j> list, char c2, double d2, double d3, String str, String str2) {
        this.f2523a = list;
        this.f2524b = c2;
        this.f2525c = d2;
        this.f2526d = d3;
        this.f2527e = str;
        this.f = str2;
    }

    public static int c(char c2, String str, String str2) {
        return ((((0 + c2) * 31) + str.hashCode()) * 31) + str2.hashCode();
    }

    public List<j> a() {
        return this.f2523a;
    }

    public double b() {
        return this.f2526d;
    }

    public int hashCode() {
        return c(this.f2524b, this.f, this.f2527e);
    }
}
