package com.airbnb.lottie.r.c;

import android.graphics.Path;
import com.airbnb.lottie.model.content.Mask;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: compiled from: MaskKeyframeAnimation.java */
/* JADX INFO: loaded from: classes.dex */
public class g {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final List<a<com.airbnb.lottie.model.content.h, Path>> f2736a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final List<a<Integer, Integer>> f2737b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private final List<Mask> f2738c;

    public g(List<Mask> list) {
        this.f2738c = list;
        this.f2736a = new ArrayList(list.size());
        this.f2737b = new ArrayList(list.size());
        for (int i = 0; i < list.size(); i++) {
            this.f2736a.add(list.get(i).b().a());
            this.f2737b.add(list.get(i).c().a());
        }
    }

    public List<a<com.airbnb.lottie.model.content.h, Path>> a() {
        return this.f2736a;
    }

    public List<Mask> b() {
        return this.f2738c;
    }

    public List<a<Integer, Integer>> c() {
        return this.f2737b;
    }
}
