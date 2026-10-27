package com.airbnb.lottie;

import android.graphics.Rect;
import androidx.annotation.Nullable;
import androidx.annotation.RestrictTo;
import androidx.collection.LongSparseArray;
import androidx.collection.SparseArrayCompat;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/* JADX INFO: loaded from: classes.dex */
public class d {

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private Map<String, List<com.airbnb.lottie.t.l.d>> f662c;
    private Map<String, g> d;
    private Map<String, com.airbnb.lottie.t.c> e;
    private List<com.airbnb.lottie.t.h> f;
    private SparseArrayCompat<com.airbnb.lottie.t.d> g;
    private LongSparseArray<com.airbnb.lottie.t.l.d> h;
    private List<com.airbnb.lottie.t.l.d> i;
    private Rect j;
    private float k;
    private float l;
    private float m;
    private boolean n;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final n f660a = new n();

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final HashSet<String> f661b = new HashSet<>();
    private int o = 0;

    @RestrictTo({RestrictTo.Scope.LIBRARY})
    public void a(String str) {
        com.airbnb.lottie.w.d.b(str);
        this.f661b.add(str);
    }

    public Rect b() {
        return this.j;
    }

    public SparseArrayCompat<com.airbnb.lottie.t.d> c() {
        return this.g;
    }

    public float d() {
        return (long) ((e() / this.m) * 1000.0f);
    }

    public float e() {
        return this.l - this.k;
    }

    @RestrictTo({RestrictTo.Scope.LIBRARY})
    public float f() {
        return this.l;
    }

    public Map<String, com.airbnb.lottie.t.c> g() {
        return this.e;
    }

    public float h() {
        return this.m;
    }

    public Map<String, g> i() {
        return this.d;
    }

    public List<com.airbnb.lottie.t.l.d> j() {
        return this.i;
    }

    @Nullable
    public com.airbnb.lottie.t.h k(String str) {
        this.f.size();
        for (int i = 0; i < this.f.size(); i++) {
            com.airbnb.lottie.t.h hVar = this.f.get(i);
            if (hVar.a(str)) {
                return hVar;
            }
        }
        return null;
    }

    @RestrictTo({RestrictTo.Scope.LIBRARY})
    public int l() {
        return this.o;
    }

    public n m() {
        return this.f660a;
    }

    @Nullable
    @RestrictTo({RestrictTo.Scope.LIBRARY})
    public List<com.airbnb.lottie.t.l.d> n(String str) {
        return this.f662c.get(str);
    }

    @RestrictTo({RestrictTo.Scope.LIBRARY})
    public float o() {
        return this.k;
    }

    @RestrictTo({RestrictTo.Scope.LIBRARY})
    public boolean p() {
        return this.n;
    }

    @RestrictTo({RestrictTo.Scope.LIBRARY})
    public void q(int i) {
        this.o += i;
    }

    @RestrictTo({RestrictTo.Scope.LIBRARY})
    public void r(Rect rect, float f, float f2, float f3, List<com.airbnb.lottie.t.l.d> list, LongSparseArray<com.airbnb.lottie.t.l.d> longSparseArray, Map<String, List<com.airbnb.lottie.t.l.d>> map, Map<String, g> map2, SparseArrayCompat<com.airbnb.lottie.t.d> sparseArrayCompat, Map<String, com.airbnb.lottie.t.c> map3, List<com.airbnb.lottie.t.h> list2) {
        this.j = rect;
        this.k = f;
        this.l = f2;
        this.m = f3;
        this.i = list;
        this.h = longSparseArray;
        this.f662c = map;
        this.d = map2;
        this.g = sparseArrayCompat;
        this.e = map3;
        this.f = list2;
    }

    @RestrictTo({RestrictTo.Scope.LIBRARY})
    public com.airbnb.lottie.t.l.d s(long j) {
        return this.h.get(j);
    }

    @RestrictTo({RestrictTo.Scope.LIBRARY})
    public void t(boolean z) {
        this.n = z;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder("LottieComposition:\n");
        Iterator<com.airbnb.lottie.t.l.d> it = this.i.iterator();
        while (it.hasNext()) {
            sb.append(it.next().w("\t"));
        }
        return sb.toString();
    }

    public void u(boolean z) {
        this.f660a.b(z);
    }
}
