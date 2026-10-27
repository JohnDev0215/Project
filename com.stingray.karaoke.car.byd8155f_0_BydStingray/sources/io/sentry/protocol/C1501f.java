package io.sentry.protocol;

import h2.AbstractC1347d;
import io.sentry.C0;
import io.sentry.C1489n1;
import io.sentry.InterfaceC1485m0;
import io.sentry.K;
import java.io.IOException;
import java.util.Arrays;
import java.util.Date;
import java.util.TimeZone;
import java.util.concurrent.ConcurrentHashMap;

/* JADX INFO: renamed from: io.sentry.protocol.f, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C1501f implements InterfaceC1485m0 {

    /* JADX INFO: renamed from: A, reason: collision with root package name */
    public Boolean f16689A;
    public Long B;

    /* JADX INFO: renamed from: C, reason: collision with root package name */
    public Long f16690C;

    /* JADX INFO: renamed from: D, reason: collision with root package name */
    public Long f16691D;

    /* JADX INFO: renamed from: E, reason: collision with root package name */
    public Long f16692E;

    /* JADX INFO: renamed from: F, reason: collision with root package name */
    public Integer f16693F;

    /* JADX INFO: renamed from: G, reason: collision with root package name */
    public Integer f16694G;

    /* JADX INFO: renamed from: H, reason: collision with root package name */
    public Float f16695H;

    /* JADX INFO: renamed from: I, reason: collision with root package name */
    public Integer f16696I;

    /* JADX INFO: renamed from: J, reason: collision with root package name */
    public Date f16697J;

    /* JADX INFO: renamed from: K, reason: collision with root package name */
    public TimeZone f16698K;

    /* JADX INFO: renamed from: L, reason: collision with root package name */
    public String f16699L;

    /* JADX INFO: renamed from: M, reason: collision with root package name */
    public String f16700M;

    /* JADX INFO: renamed from: N, reason: collision with root package name */
    public String f16701N;

    /* JADX INFO: renamed from: O, reason: collision with root package name */
    public String f16702O;

    /* JADX INFO: renamed from: P, reason: collision with root package name */
    public Float f16703P;

    /* JADX INFO: renamed from: Q, reason: collision with root package name */
    public Integer f16704Q;

    /* JADX INFO: renamed from: R, reason: collision with root package name */
    public Double f16705R;

    /* JADX INFO: renamed from: S, reason: collision with root package name */
    public String f16706S;

    /* JADX INFO: renamed from: T, reason: collision with root package name */
    public ConcurrentHashMap f16707T;
    public String l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public String f16708m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public String f16709n;
    public String o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public String f16710p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public String f16711q;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public String[] f16712r;

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    public Float f16713s;

    /* JADX INFO: renamed from: t, reason: collision with root package name */
    public Boolean f16714t;

    /* JADX INFO: renamed from: u, reason: collision with root package name */
    public Boolean f16715u;

    /* JADX INFO: renamed from: v, reason: collision with root package name */
    public EnumC1500e f16716v;

    /* JADX INFO: renamed from: w, reason: collision with root package name */
    public Boolean f16717w;

    /* JADX INFO: renamed from: x, reason: collision with root package name */
    public Long f16718x;

    /* JADX INFO: renamed from: y, reason: collision with root package name */
    public Long f16719y;

    /* JADX INFO: renamed from: z, reason: collision with root package name */
    public Long f16720z;

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || C1501f.class != obj.getClass()) {
            return false;
        }
        C1501f c1501f = (C1501f) obj;
        return h6.e.i(this.l, c1501f.l) && h6.e.i(this.f16708m, c1501f.f16708m) && h6.e.i(this.f16709n, c1501f.f16709n) && h6.e.i(this.o, c1501f.o) && h6.e.i(this.f16710p, c1501f.f16710p) && h6.e.i(this.f16711q, c1501f.f16711q) && Arrays.equals(this.f16712r, c1501f.f16712r) && h6.e.i(this.f16713s, c1501f.f16713s) && h6.e.i(this.f16714t, c1501f.f16714t) && h6.e.i(this.f16715u, c1501f.f16715u) && this.f16716v == c1501f.f16716v && h6.e.i(this.f16717w, c1501f.f16717w) && h6.e.i(this.f16718x, c1501f.f16718x) && h6.e.i(this.f16719y, c1501f.f16719y) && h6.e.i(this.f16720z, c1501f.f16720z) && h6.e.i(this.f16689A, c1501f.f16689A) && h6.e.i(this.B, c1501f.B) && h6.e.i(this.f16690C, c1501f.f16690C) && h6.e.i(this.f16691D, c1501f.f16691D) && h6.e.i(this.f16692E, c1501f.f16692E) && h6.e.i(this.f16693F, c1501f.f16693F) && h6.e.i(this.f16694G, c1501f.f16694G) && h6.e.i(this.f16695H, c1501f.f16695H) && h6.e.i(this.f16696I, c1501f.f16696I) && h6.e.i(this.f16697J, c1501f.f16697J) && h6.e.i(this.f16699L, c1501f.f16699L) && h6.e.i(this.f16700M, c1501f.f16700M) && h6.e.i(this.f16701N, c1501f.f16701N) && h6.e.i(this.f16702O, c1501f.f16702O) && h6.e.i(this.f16703P, c1501f.f16703P) && h6.e.i(this.f16704Q, c1501f.f16704Q) && h6.e.i(this.f16705R, c1501f.f16705R) && h6.e.i(this.f16706S, c1501f.f16706S);
    }

    public final int hashCode() {
        return (Arrays.hashCode(new Object[]{this.l, this.f16708m, this.f16709n, this.o, this.f16710p, this.f16711q, this.f16713s, this.f16714t, this.f16715u, this.f16716v, this.f16717w, this.f16718x, this.f16719y, this.f16720z, this.f16689A, this.B, this.f16690C, this.f16691D, this.f16692E, this.f16693F, this.f16694G, this.f16695H, this.f16696I, this.f16697J, this.f16698K, this.f16699L, this.f16700M, this.f16701N, this.f16702O, this.f16703P, this.f16704Q, this.f16705R, this.f16706S}) * 31) + Arrays.hashCode(this.f16712r);
    }

    @Override // io.sentry.InterfaceC1485m0
    public final void serialize(C0 c02, K k10) throws IOException {
        C1489n1 c1489n1 = (C1489n1) c02;
        c1489n1.i();
        if (this.l != null) {
            c1489n1.w("name");
            c1489n1.L(this.l);
        }
        if (this.f16708m != null) {
            c1489n1.w("manufacturer");
            c1489n1.L(this.f16708m);
        }
        if (this.f16709n != null) {
            c1489n1.w("brand");
            c1489n1.L(this.f16709n);
        }
        if (this.o != null) {
            c1489n1.w("family");
            c1489n1.L(this.o);
        }
        if (this.f16710p != null) {
            c1489n1.w("model");
            c1489n1.L(this.f16710p);
        }
        if (this.f16711q != null) {
            c1489n1.w("model_id");
            c1489n1.L(this.f16711q);
        }
        if (this.f16712r != null) {
            c1489n1.w("archs");
            c1489n1.I(k10, this.f16712r);
        }
        if (this.f16713s != null) {
            c1489n1.w("battery_level");
            c1489n1.K(this.f16713s);
        }
        if (this.f16714t != null) {
            c1489n1.w("charging");
            c1489n1.J(this.f16714t);
        }
        if (this.f16715u != null) {
            c1489n1.w("online");
            c1489n1.J(this.f16715u);
        }
        if (this.f16716v != null) {
            c1489n1.w("orientation");
            c1489n1.I(k10, this.f16716v);
        }
        if (this.f16717w != null) {
            c1489n1.w("simulator");
            c1489n1.J(this.f16717w);
        }
        if (this.f16718x != null) {
            c1489n1.w("memory_size");
            c1489n1.K(this.f16718x);
        }
        if (this.f16719y != null) {
            c1489n1.w("free_memory");
            c1489n1.K(this.f16719y);
        }
        if (this.f16720z != null) {
            c1489n1.w("usable_memory");
            c1489n1.K(this.f16720z);
        }
        if (this.f16689A != null) {
            c1489n1.w("low_memory");
            c1489n1.J(this.f16689A);
        }
        if (this.B != null) {
            c1489n1.w("storage_size");
            c1489n1.K(this.B);
        }
        if (this.f16690C != null) {
            c1489n1.w("free_storage");
            c1489n1.K(this.f16690C);
        }
        if (this.f16691D != null) {
            c1489n1.w("external_storage_size");
            c1489n1.K(this.f16691D);
        }
        if (this.f16692E != null) {
            c1489n1.w("external_free_storage");
            c1489n1.K(this.f16692E);
        }
        if (this.f16693F != null) {
            c1489n1.w("screen_width_pixels");
            c1489n1.K(this.f16693F);
        }
        if (this.f16694G != null) {
            c1489n1.w("screen_height_pixels");
            c1489n1.K(this.f16694G);
        }
        if (this.f16695H != null) {
            c1489n1.w("screen_density");
            c1489n1.K(this.f16695H);
        }
        if (this.f16696I != null) {
            c1489n1.w("screen_dpi");
            c1489n1.K(this.f16696I);
        }
        if (this.f16697J != null) {
            c1489n1.w("boot_time");
            c1489n1.I(k10, this.f16697J);
        }
        if (this.f16698K != null) {
            c1489n1.w("timezone");
            c1489n1.I(k10, this.f16698K);
        }
        if (this.f16699L != null) {
            c1489n1.w("id");
            c1489n1.L(this.f16699L);
        }
        if (this.f16700M != null) {
            c1489n1.w("language");
            c1489n1.L(this.f16700M);
        }
        if (this.f16702O != null) {
            c1489n1.w("connection_type");
            c1489n1.L(this.f16702O);
        }
        if (this.f16703P != null) {
            c1489n1.w("battery_temperature");
            c1489n1.K(this.f16703P);
        }
        if (this.f16701N != null) {
            c1489n1.w("locale");
            c1489n1.L(this.f16701N);
        }
        if (this.f16704Q != null) {
            c1489n1.w("processor_count");
            c1489n1.K(this.f16704Q);
        }
        if (this.f16705R != null) {
            c1489n1.w("processor_frequency");
            c1489n1.K(this.f16705R);
        }
        if (this.f16706S != null) {
            c1489n1.w("cpu_description");
            c1489n1.L(this.f16706S);
        }
        ConcurrentHashMap concurrentHashMap = this.f16707T;
        if (concurrentHashMap != null) {
            for (String str : concurrentHashMap.keySet()) {
                AbstractC1347d.t(this.f16707T, str, c1489n1, str, k10);
            }
        }
        c1489n1.n();
    }
}
