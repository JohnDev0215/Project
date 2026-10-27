package com.google.android.flexbox;

import android.view.View;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: compiled from: FlexLine.java */
/* JADX INFO: loaded from: classes.dex */
public class b {

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    int f3947e;
    int f;
    int g;
    int h;
    int i;
    float j;
    float k;
    int l;
    int m;
    int o;
    int p;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    int f3943a = Integer.MAX_VALUE;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    int f3944b = Integer.MAX_VALUE;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    int f3945c = Integer.MIN_VALUE;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    int f3946d = Integer.MIN_VALUE;
    List<Integer> n = new ArrayList();

    b() {
    }

    public int a() {
        return this.g;
    }

    public int b() {
        return this.h;
    }

    public int c() {
        return this.h - this.i;
    }

    void d(View view, int i, int i2, int i3, int i4) {
        FlexItem flexItem = (FlexItem) view.getLayoutParams();
        this.f3943a = Math.min(this.f3943a, (view.getLeft() - flexItem.n()) - i);
        this.f3944b = Math.min(this.f3944b, (view.getTop() - flexItem.t()) - i2);
        this.f3945c = Math.max(this.f3945c, view.getRight() + flexItem.c() + i3);
        this.f3946d = Math.max(this.f3946d, view.getBottom() + flexItem.l() + i4);
    }
}
