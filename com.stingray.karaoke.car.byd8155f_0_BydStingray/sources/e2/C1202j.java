package e2;

import h2.AbstractC1343A;
import java.util.Objects;

/* JADX INFO: renamed from: e2.j, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C1202j {

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public static final C1202j f14122e = new E5.z(0).c();

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public static final String f14123f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public static final String f14124g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public static final String f14125h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public static final String f14126i;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final int f14127a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final int f14128b;
    public final int c;
    public final String d;

    static {
        int i7 = AbstractC1343A.f15195a;
        f14123f = Integer.toString(0, 36);
        f14124g = Integer.toString(1, 36);
        f14125h = Integer.toString(2, 36);
        f14126i = Integer.toString(3, 36);
    }

    public C1202j(E5.z zVar) {
        this.f14127a = zVar.f1841b;
        this.f14128b = zVar.c;
        this.c = zVar.d;
        this.d = (String) zVar.f1842e;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof C1202j)) {
            return false;
        }
        C1202j c1202j = (C1202j) obj;
        if (this.f14127a == c1202j.f14127a && this.f14128b == c1202j.f14128b && this.c == c1202j.c) {
            int i7 = AbstractC1343A.f15195a;
            if (Objects.equals(this.d, c1202j.d)) {
                return true;
            }
        }
        return false;
    }

    public final int hashCode() {
        int i7 = (((((527 + this.f14127a) * 31) + this.f14128b) * 31) + this.c) * 31;
        String str = this.d;
        return i7 + (str == null ? 0 : str.hashCode());
    }
}
