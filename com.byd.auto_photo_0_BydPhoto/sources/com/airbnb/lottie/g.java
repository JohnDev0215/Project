package com.airbnb.lottie;

import android.graphics.Bitmap;
import androidx.annotation.Nullable;
import androidx.annotation.RestrictTo;

/* JADX INFO: loaded from: classes.dex */
public class g {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final int f706a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final int f707b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private final String f708c;
    private final String d;
    private final String e;

    @Nullable
    private Bitmap f;

    @RestrictTo({RestrictTo.Scope.LIBRARY})
    public g(int i, int i2, String str, String str2, String str3) {
        this.f706a = i;
        this.f707b = i2;
        this.f708c = str;
        this.d = str2;
        this.e = str3;
    }

    @Nullable
    public Bitmap a() {
        return this.f;
    }

    public String b() {
        return this.d;
    }

    public int c() {
        return this.f707b;
    }

    public String d() {
        return this.f708c;
    }

    public int e() {
        return this.f706a;
    }

    public void f(@Nullable Bitmap bitmap) {
        this.f = bitmap;
    }
}
