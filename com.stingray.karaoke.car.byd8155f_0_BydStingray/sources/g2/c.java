package G2;

import B2.C0066f;
import B2.C0067g;
import B2.C0073m;
import B2.InterfaceC0068h;
import B2.InterfaceC0070j;
import B2.v;

/* JADX INFO: loaded from: classes.dex */
public final class c {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final C0066f f2652a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final InterfaceC0070j f2653b;
    public C0067g c;
    public final int d;

    public c(InterfaceC0068h interfaceC0068h, InterfaceC0070j interfaceC0070j, long j3, long j10, long j11, long j12, long j13, int i7) {
        this.f2653b = interfaceC0070j;
        this.d = i7;
        this.f2652a = new C0066f(interfaceC0068h, j3, j10, j11, j12, j13);
    }

    public static int a(int i7, byte[] bArr) {
        return (bArr[i7 + 3] & 255) | ((bArr[i7] & 255) << 24) | ((bArr[i7 + 1] & 255) << 16) | ((bArr[i7 + 2] & 255) << 8);
    }

    public static int c(C0073m c0073m, long j3, v vVar) {
        if (j3 == c0073m.o) {
            return 0;
        }
        vVar.f600a = j3;
        return 1;
    }

    /* JADX WARN: Code restructure failed: missing block: B:32:0x00d1, code lost:
    
        return c(r28, r8, r29);
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final int b(B2.C0073m r28, B2.v r29) {
        /*
            Method dump skipped, instruction units count: 210
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: G2.c.b(B2.m, B2.v):int");
    }

    public final void d(long j3) {
        C0067g c0067g = this.c;
        if (c0067g == null || c0067g.f570a != j3) {
            C0066f c0066f = this.f2652a;
            this.c = new C0067g(j3, c0066f.f566a.h(j3), c0066f.c, c0066f.d, c0066f.f568e, c0066f.f569f);
        }
    }
}
