package com.airbnb.lottie;

import android.graphics.Rect;
import com.airbnb.lottie.model.layer.Layer;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/* JADX INFO: compiled from: LottieComposition.java */
/* JADX INFO: loaded from: classes.dex */
public class d {

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private Map<String, List<Layer>> f2441c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private Map<String, g> f2442d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private Map<String, com.airbnb.lottie.model.b> f2443e;
    private List<com.airbnb.lottie.model.g> f;
    private b.d.h<com.airbnb.lottie.model.c> g;
    private b.d.d<Layer> h;
    private List<Layer> i;
    private Rect j;
    private float k;
    private float l;
    private float m;
    private boolean n;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final n f2439a = new n();

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final HashSet<String> f2440b = new HashSet<>();
    private int o = 0;

    public void a(String str) {
        com.airbnb.lottie.u.d.c(str);
        this.f2440b.add(str);
    }

    public Rect b() {
        return this.j;
    }

    public b.d.h<com.airbnb.lottie.model.c> c() {
        return this.g;
    }

    public float d() {
        return (long) ((e() / this.m) * 1000.0f);
    }

    public float e() {
        return this.l - this.k;
    }

    public float f() {
        return this.l;
    }

    public Map<String, com.airbnb.lottie.model.b> g() {
        return this.f2443e;
    }

    public float h() {
        return this.m;
    }

    public Map<String, g> i() {
        return this.f2442d;
    }

    public List<Layer> j() {
        return this.i;
    }

    public com.airbnb.lottie.model.g k(String str) {
        this.f.size();
        for (int i = 0; i < this.f.size(); i++) {
            com.airbnb.lottie.model.g gVar = this.f.get(i);
            if (gVar.a(str)) {
                return gVar;
            }
        }
        return null;
    }

    public int l() {
        return this.o;
    }

    public n m() {
        return this.f2439a;
    }

    public List<Layer> n(String str) {
        return this.f2441c.get(str);
    }

    public float o() {
        return this.k;
    }

    public boolean p() {
        return this.n;
    }

    public void q(int i) {
        this.o += i;
    }

    public void r(Rect rect, float f, float f2, float f3, List<Layer> list, b.d.d<Layer> dVar, Map<String, List<Layer>> map, Map<String, g> map2, b.d.h<com.airbnb.lottie.model.c> hVar, Map<String, com.airbnb.lottie.model.b> map3, List<com.airbnb.lottie.model.g> list2) {
        this.j = rect;
        this.k = f;
        this.l = f2;
        this.m = f3;
        this.i = list;
        this.h = dVar;
        this.f2441c = map;
        this.f2442d = map2;
        this.g = hVar;
        this.f2443e = map3;
        this.f = list2;
    }

    public Layer s(long j) {
        return this.h.f(j);
    }

    public void t(boolean z) {
        this.n = z;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder("LottieComposition:\n");
        Iterator<Layer> it = this.i.iterator();
        while (it.hasNext()) {
            sb.append(it.next().w("\t"));
        }
        return sb.toString();
    }

    public void u(boolean z) {
        this.f2439a.b(z);
    }
}
