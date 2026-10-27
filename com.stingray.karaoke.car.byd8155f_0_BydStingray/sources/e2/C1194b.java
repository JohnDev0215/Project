package e2;

import h2.AbstractC1343A;
import java.util.Arrays;

/* JADX INFO: renamed from: e2.b, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C1194b {

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public static final C1194b f14037f = new C1194b(new C1193a[0], 0, -9223372036854775807L, 0);

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public static final C1193a f14038g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public static final String f14039h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public static final String f14040i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public static final String f14041j;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    public static final String f14042k;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final int f14043a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final long f14044b;
    public final long c;
    public final int d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final C1193a[] f14045e;

    static {
        C1193a c1193a = new C1193a(0L, -1, -1, new int[0], new F[0], new long[0], 0L, false);
        int[] iArr = c1193a.f14031f;
        int length = iArr.length;
        int iMax = Math.max(0, length);
        int[] iArrCopyOf = Arrays.copyOf(iArr, iMax);
        Arrays.fill(iArrCopyOf, length, iMax, 0);
        long[] jArr = c1193a.f14032g;
        int length2 = jArr.length;
        int iMax2 = Math.max(0, length2);
        long[] jArrCopyOf = Arrays.copyOf(jArr, iMax2);
        Arrays.fill(jArrCopyOf, length2, iMax2, -9223372036854775807L);
        f14038g = new C1193a(c1193a.f14028a, 0, c1193a.c, iArrCopyOf, (F[]) Arrays.copyOf(c1193a.f14030e, 0), jArrCopyOf, c1193a.f14033h, c1193a.f14034i);
        int i7 = AbstractC1343A.f15195a;
        f14039h = Integer.toString(1, 36);
        f14040i = Integer.toString(2, 36);
        f14041j = Integer.toString(3, 36);
        f14042k = Integer.toString(4, 36);
    }

    public C1194b(C1193a[] c1193aArr, long j3, long j10, int i7) {
        this.f14044b = j3;
        this.c = j10;
        this.f14043a = c1193aArr.length + i7;
        this.f14045e = c1193aArr;
        this.d = i7;
    }

    public final C1193a a(int i7) {
        int i10 = this.d;
        return i7 < i10 ? f14038g : this.f14045e[i7 - i10];
    }

    public final boolean b(int i7) {
        if (i7 == this.f14043a - 1) {
            C1193a c1193aA = a(i7);
            if (c1193aA.f14034i && c1193aA.f14028a == Long.MIN_VALUE && c1193aA.f14029b == -1) {
                return true;
            }
        }
        return false;
    }

    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || C1194b.class != obj.getClass()) {
            return false;
        }
        C1194b c1194b = (C1194b) obj;
        int i7 = AbstractC1343A.f15195a;
        return this.f14043a == c1194b.f14043a && this.f14044b == c1194b.f14044b && this.c == c1194b.c && this.d == c1194b.d && Arrays.equals(this.f14045e, c1194b.f14045e);
    }

    public final int hashCode() {
        return Arrays.hashCode(this.f14045e) + (((((((this.f14043a * 961) + ((int) this.f14044b)) * 31) + ((int) this.c)) * 31) + this.d) * 31);
    }

    public final String toString() {
        StringBuilder sb = new StringBuilder("AdPlaybackState(adsId=null, adResumePositionUs=");
        sb.append(this.f14044b);
        sb.append(", adGroups=[");
        int i7 = 0;
        while (true) {
            C1193a[] c1193aArr = this.f14045e;
            if (i7 >= c1193aArr.length) {
                sb.append("])");
                return sb.toString();
            }
            sb.append("adGroup(timeUs=");
            sb.append(c1193aArr[i7].f14028a);
            sb.append(", ads=[");
            for (int i10 = 0; i10 < c1193aArr[i7].f14031f.length; i10++) {
                sb.append("ad(state=");
                int i11 = c1193aArr[i7].f14031f[i10];
                if (i11 == 0) {
                    sb.append('_');
                } else if (i11 == 1) {
                    sb.append('R');
                } else if (i11 == 2) {
                    sb.append('S');
                } else if (i11 == 3) {
                    sb.append('P');
                } else if (i11 != 4) {
                    sb.append('?');
                } else {
                    sb.append('!');
                }
                sb.append(", durationUs=");
                sb.append(c1193aArr[i7].f14032g[i10]);
                sb.append(')');
                if (i10 < c1193aArr[i7].f14031f.length - 1) {
                    sb.append(", ");
                }
            }
            sb.append("])");
            if (i7 < c1193aArr.length - 1) {
                sb.append(", ");
            }
            i7++;
        }
    }
}
