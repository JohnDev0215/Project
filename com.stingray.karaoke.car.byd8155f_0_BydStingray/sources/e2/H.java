package e2;

import android.net.Uri;
import android.os.Bundle;
import d5.C1078G;
import h2.AbstractC1343A;
import h2.AbstractC1346c;
import java.util.Objects;

/* JADX INFO: loaded from: classes.dex */
public final class H {

    /* JADX INFO: renamed from: A, reason: collision with root package name */
    public CharSequence f13884A;
    public Integer B;

    /* JADX INFO: renamed from: C, reason: collision with root package name */
    public Integer f13885C;

    /* JADX INFO: renamed from: D, reason: collision with root package name */
    public CharSequence f13886D;

    /* JADX INFO: renamed from: E, reason: collision with root package name */
    public CharSequence f13887E;

    /* JADX INFO: renamed from: F, reason: collision with root package name */
    public CharSequence f13888F;

    /* JADX INFO: renamed from: G, reason: collision with root package name */
    public Integer f13889G;

    /* JADX INFO: renamed from: H, reason: collision with root package name */
    public Bundle f13890H;

    /* JADX INFO: renamed from: I, reason: collision with root package name */
    public d5.I f13891I;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public CharSequence f13892a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public CharSequence f13893b;
    public CharSequence c;
    public CharSequence d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public CharSequence f13894e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public CharSequence f13895f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public CharSequence f13896g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public Long f13897h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public X f13898i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public X f13899j;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    public byte[] f13900k;
    public Integer l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public Uri f13901m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public Integer f13902n;
    public Integer o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public Integer f13903p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public Boolean f13904q;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public Boolean f13905r;

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    public Integer f13906s;

    /* JADX INFO: renamed from: t, reason: collision with root package name */
    public Integer f13907t;

    /* JADX INFO: renamed from: u, reason: collision with root package name */
    public Integer f13908u;

    /* JADX INFO: renamed from: v, reason: collision with root package name */
    public Integer f13909v;

    /* JADX INFO: renamed from: w, reason: collision with root package name */
    public Integer f13910w;

    /* JADX INFO: renamed from: x, reason: collision with root package name */
    public Integer f13911x;

    /* JADX INFO: renamed from: y, reason: collision with root package name */
    public CharSequence f13912y;

    /* JADX INFO: renamed from: z, reason: collision with root package name */
    public CharSequence f13913z;

    public H() {
        C1078G c1078g = d5.I.f13384m;
        this.f13891I = d5.d0.f13415p;
    }

    public final void a(int i7, byte[] bArr) {
        if (this.f13900k != null) {
            Integer numValueOf = Integer.valueOf(i7);
            int i10 = AbstractC1343A.f15195a;
            if (!numValueOf.equals(3) && Objects.equals(this.l, 3)) {
                return;
            }
        }
        this.f13900k = (byte[]) bArr.clone();
        this.l = Integer.valueOf(i7);
    }

    public final void b(byte[] bArr, Integer num) {
        this.f13900k = bArr == null ? null : (byte[]) bArr.clone();
        this.l = num;
    }

    public final void c(Long l) {
        AbstractC1346c.c(l == null || l.longValue() >= 0);
        this.f13897h = l;
    }

    public final void d(CharSequence charSequence) {
        this.f13886D = charSequence;
    }

    public final void e(Integer num) {
        this.f13908u = num;
    }

    public final void f(Integer num) {
        this.f13907t = num;
    }

    public final void g(Integer num) {
        this.f13906s = num;
    }
}
