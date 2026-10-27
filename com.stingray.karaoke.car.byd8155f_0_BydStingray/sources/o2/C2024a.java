package o2;

import a.AbstractC0904a;
import e2.f0;
import java.util.Arrays;
import u2.C2492C;

/* JADX INFO: renamed from: o2.a, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C2024a {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final long f20396a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final f0 f20397b;
    public final int c;
    public final C2492C d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final long f20398e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final f0 f20399f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public final int f20400g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public final C2492C f20401h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final long f20402i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public final long f20403j;

    public C2024a(long j3, f0 f0Var, int i7, C2492C c2492c, long j10, f0 f0Var2, int i10, C2492C c2492c2, long j11, long j12) {
        this.f20396a = j3;
        this.f20397b = f0Var;
        this.c = i7;
        this.d = c2492c;
        this.f20398e = j10;
        this.f20399f = f0Var2;
        this.f20400g = i10;
        this.f20401h = c2492c2;
        this.f20402i = j11;
        this.f20403j = j12;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || C2024a.class != obj.getClass()) {
            return false;
        }
        C2024a c2024a = (C2024a) obj;
        return this.f20396a == c2024a.f20396a && this.c == c2024a.c && this.f20398e == c2024a.f20398e && this.f20400g == c2024a.f20400g && this.f20402i == c2024a.f20402i && this.f20403j == c2024a.f20403j && AbstractC0904a.A(this.f20397b, c2024a.f20397b) && AbstractC0904a.A(this.d, c2024a.d) && AbstractC0904a.A(this.f20399f, c2024a.f20399f) && AbstractC0904a.A(this.f20401h, c2024a.f20401h);
    }

    public final int hashCode() {
        return Arrays.hashCode(new Object[]{Long.valueOf(this.f20396a), this.f20397b, Integer.valueOf(this.c), this.d, Long.valueOf(this.f20398e), this.f20399f, Integer.valueOf(this.f20400g), this.f20401h, Long.valueOf(this.f20402i), Long.valueOf(this.f20403j)});
    }
}
