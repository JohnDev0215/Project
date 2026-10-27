package com.airbnb.lottie.r.c;

import android.graphics.Path;
import com.airbnb.lottie.model.content.Mask;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: compiled from: MaskKeyframeAnimation.java */
/* JADX INFO: loaded from: classes.dex */
public class g {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final List<a<com.airbnb.lottie.model.content.h, Path>> f3116a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final List<a<Integer, Integer>> f3117b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private final List<Mask> f3118c;

    public g(List<Mask> list) {
        this.f3118c = list;
        this.f3116a = new ArrayList(list.size());
        this.f3117b = new ArrayList(list.size());
        for (int i = 0; i < list.size(); i++) {
            this.f3116a.add(list.get(i).b().a());
            this.f3117b.add(list.get(i).c().a());
        }
    }

    public List<a<com.airbnb.lottie.model.content.h, Path>> a() {
        return this.f3116a;
    }

    public List<Mask> b() {
        return this.f3118c;
    }

    public List<a<Integer, Integer>> c() {
        return this.f3117b;
    }
}
