package e2;

import h2.AbstractC1343A;
import java.util.Objects;

/* JADX INFO: loaded from: classes.dex */
public final class c0 {

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public static final String f14055h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public static final String f14056i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public static final String f14057j;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    public static final String f14058k;
    public static final String l;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public Object f14059a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public Object f14060b;
    public int c;
    public long d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public long f14061e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public boolean f14062f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public C1194b f14063g = C1194b.f14037f;

    static {
        int i7 = AbstractC1343A.f15195a;
        f14055h = Integer.toString(0, 36);
        f14056i = Integer.toString(1, 36);
        f14057j = Integer.toString(2, 36);
        f14058k = Integer.toString(3, 36);
        l = Integer.toString(4, 36);
    }

    public final long a(int i7, int i10) {
        C1193a c1193aA = this.f14063g.a(i7);
        if (c1193aA.f14029b != -1) {
            return c1193aA.f14032g[i10];
        }
        return -9223372036854775807L;
    }

    public final int b(long j3) {
        int i7;
        C1193a c1193aA;
        int i10;
        C1194b c1194b = this.f14063g;
        long j10 = this.d;
        c1194b.getClass();
        if (j3 == Long.MIN_VALUE) {
            return -1;
        }
        if (j10 != -9223372036854775807L && j3 >= j10) {
            return -1;
        }
        int i11 = c1194b.d;
        while (true) {
            i7 = c1194b.f14043a;
            if (i11 >= i7 || ((c1194b.a(i11).f14028a == Long.MIN_VALUE || c1194b.a(i11).f14028a > j3) && ((i10 = (c1193aA = c1194b.a(i11)).f14029b) == -1 || c1193aA.a(-1) < i10))) {
                break;
            }
            i11++;
        }
        if (i11 < i7) {
            return i11;
        }
        return -1;
    }

    public final int c(long j3) {
        C1194b c1194b = this.f14063g;
        long j10 = this.d;
        int i7 = c1194b.f14043a - 1;
        int i10 = i7 - (c1194b.b(i7) ? 1 : 0);
        while (i10 >= 0 && j3 != Long.MIN_VALUE) {
            C1193a c1193aA = c1194b.a(i10);
            long j11 = c1193aA.f14028a;
            if (j11 != Long.MIN_VALUE) {
                if (j3 >= j11) {
                    break;
                }
                i10--;
            } else {
                if (j10 != -9223372036854775807L && ((!c1193aA.f14034i || c1193aA.f14029b != -1) && j3 >= j10)) {
                    break;
                }
                i10--;
            }
        }
        if (i10 >= 0) {
            C1193a c1193aA2 = c1194b.a(i10);
            int i11 = c1193aA2.f14029b;
            if (i11 == -1) {
                return i10;
            }
            for (int i12 = 0; i12 < i11; i12++) {
                int i13 = c1193aA2.f14031f[i12];
                if (i13 == 0 || i13 == 1) {
                    return i10;
                }
            }
        }
        return -1;
    }

    public final long d(int i7) {
        return this.f14063g.a(i7).f14028a;
    }

    public final int e(int i7, int i10) {
        C1193a c1193aA = this.f14063g.a(i7);
        if (c1193aA.f14029b != -1) {
            return c1193aA.f14031f[i10];
        }
        return 0;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !c0.class.equals(obj.getClass())) {
            return false;
        }
        c0 c0Var = (c0) obj;
        Object obj2 = this.f14059a;
        Object obj3 = c0Var.f14059a;
        int i7 = AbstractC1343A.f15195a;
        return Objects.equals(obj2, obj3) && Objects.equals(this.f14060b, c0Var.f14060b) && this.c == c0Var.c && this.d == c0Var.d && this.f14061e == c0Var.f14061e && this.f14062f == c0Var.f14062f && Objects.equals(this.f14063g, c0Var.f14063g);
    }

    public final int f(int i7) {
        return this.f14063g.a(i7).a(-1);
    }

    public final boolean g(int i7) {
        C1194b c1194b = this.f14063g;
        return i7 == c1194b.f14043a - 1 && c1194b.b(i7);
    }

    public final boolean h(int i7) {
        return this.f14063g.a(i7).f14034i;
    }

    public final int hashCode() {
        Object obj = this.f14059a;
        int iHashCode = (217 + (obj == null ? 0 : obj.hashCode())) * 31;
        Object obj2 = this.f14060b;
        int iHashCode2 = (((iHashCode + (obj2 != null ? obj2.hashCode() : 0)) * 31) + this.c) * 31;
        long j3 = this.d;
        int i7 = (iHashCode2 + ((int) (j3 ^ (j3 >>> 32)))) * 31;
        long j10 = this.f14061e;
        return this.f14063g.hashCode() + ((((i7 + ((int) (j10 ^ (j10 >>> 32)))) * 31) + (this.f14062f ? 1 : 0)) * 31);
    }

    public final void i(Object obj, Object obj2, int i7, long j3, long j10, C1194b c1194b, boolean z6) {
        this.f14059a = obj;
        this.f14060b = obj2;
        this.c = i7;
        this.d = j3;
        this.f14061e = j10;
        this.f14063g = c1194b;
        this.f14062f = z6;
    }
}
