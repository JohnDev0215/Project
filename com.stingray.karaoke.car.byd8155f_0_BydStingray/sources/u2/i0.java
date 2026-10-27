package u2;

import java.io.IOException;
import java.util.Arrays;

/* JADX INFO: loaded from: classes.dex */
public final class i0 implements x2.l {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final k2.m f22840a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final k2.C f22841b;
    public byte[] c;

    public i0(k2.m mVar, k2.h hVar) {
        C2516t.f22900a.getAndIncrement();
        this.f22840a = mVar;
        this.f22841b = new k2.C(hVar);
    }

    @Override // x2.l
    public final void a() {
        k2.C c = this.f22841b;
        c.f17631m = 0L;
        try {
            c.g(this.f22840a);
            int iN = 0;
            while (iN != -1) {
                int i7 = (int) c.f17631m;
                byte[] bArr = this.c;
                if (bArr == null) {
                    this.c = new byte[1024];
                } else if (i7 == bArr.length) {
                    this.c = Arrays.copyOf(bArr, bArr.length * 2);
                }
                byte[] bArr2 = this.c;
                iN = c.n(bArr2, i7, bArr2.length - i7);
            }
            try {
                c.close();
            } catch (IOException unused) {
            }
        } catch (Throwable th) {
            try {
                c.close();
            } catch (IOException unused2) {
            }
            throw th;
        }
    }

    @Override // x2.l
    public final void b() {
    }
}
