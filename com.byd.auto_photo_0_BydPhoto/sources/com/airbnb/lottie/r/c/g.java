package com.airbnb.lottie.r.c;

import android.graphics.Path;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public class g {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final List<a<com.airbnb.lottie.t.k.l, Path>> f767a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final List<a<Integer, Integer>> f768b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private final List<com.airbnb.lottie.t.k.g> f769c;

    public g(List<com.airbnb.lottie.t.k.g> list) {
        this.f769c = list;
        this.f767a = new ArrayList(list.size());
        this.f768b = new ArrayList(list.size());
        for (int i = 0; i < list.size(); i++) {
            this.f767a.add(list.get(i).b().a());
            this.f768b.add(list.get(i).c().a());
        }
    }

    public List<a<com.airbnb.lottie.t.k.l, Path>> a() {
        return this.f767a;
    }

    public List<com.airbnb.lottie.t.k.g> b() {
        return this.f769c;
    }

    public List<a<Integer, Integer>> c() {
        return this.f768b;
    }
}
