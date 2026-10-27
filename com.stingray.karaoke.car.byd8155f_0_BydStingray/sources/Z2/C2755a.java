package z2;

import java.util.Arrays;

/* JADX INFO: renamed from: z2.a, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public final class C2755a {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public long f24264a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public long f24265b;
    public long c;
    public long d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public long f24266e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public long f24267f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public final boolean[] f24268g = new boolean[15];

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public int f24269h;

    public final boolean a() {
        return this.d > 15 && this.f24269h == 0;
    }

    public final void b(long j3) {
        long j10 = this.d;
        if (j10 == 0) {
            this.f24264a = j3;
        } else if (j10 == 1) {
            long j11 = j3 - this.f24264a;
            this.f24265b = j11;
            this.f24267f = j11;
            this.f24266e = 1L;
        } else {
            long j12 = j3 - this.c;
            int i7 = (int) (j10 % 15);
            long jAbs = Math.abs(j12 - this.f24265b);
            boolean[] zArr = this.f24268g;
            if (jAbs <= 1000000) {
                this.f24266e++;
                this.f24267f += j12;
                if (zArr[i7]) {
                    zArr[i7] = false;
                    this.f24269h--;
                }
            } else if (!zArr[i7]) {
                zArr[i7] = true;
                this.f24269h++;
            }
        }
        this.d++;
        this.c = j3;
    }

    public final void c() {
        this.d = 0L;
        this.f24266e = 0L;
        this.f24267f = 0L;
        this.f24269h = 0;
        Arrays.fill(this.f24268g, false);
    }
}
