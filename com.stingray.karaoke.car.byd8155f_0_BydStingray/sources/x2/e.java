package x2;

import h2.AbstractC1343A;
import java.util.Arrays;

/* JADX INFO: loaded from: classes.dex */
public final class e {
    public int c;
    public int d;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final boolean f23725a = true;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final int f23726b = 65536;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public int f23727e = 0;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public a[] f23728f = new a[100];

    public final synchronized void a(int i7) {
        boolean z6 = i7 < this.c;
        this.c = i7;
        if (z6) {
            b();
        }
    }

    public final synchronized void b() {
        int iMax = Math.max(0, AbstractC1343A.f(this.c, this.f23726b) - this.d);
        int i7 = this.f23727e;
        if (iMax >= i7) {
            return;
        }
        Arrays.fill(this.f23728f, iMax, i7, (Object) null);
        this.f23727e = iMax;
    }
}
