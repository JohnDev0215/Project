package g.a.b.z.i;

import g.a.a.c1;
import g.a.a.f;
import g.a.a.h1;
import g.a.a.m;
import g.a.a.y0;
import g.a.b.l;
import g.a.b.n;
import java.io.IOException;
import org.bouncycastle.crypto.DataLengthException;
import org.bouncycastle.crypto.OutputLengthException;

/* JADX INFO: loaded from: classes.dex */
public class c implements l {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final n f5220a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public m f5221b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public int f5222c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public byte[] f5223d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public byte[] f5224e;

    public c(n nVar) {
        this.f5220a = nVar;
    }

    @Override // g.a.b.l
    public void a(g.a.b.m mVar) {
        b bVar = (b) mVar;
        this.f5221b = bVar.a();
        this.f5222c = bVar.c();
        this.f5223d = bVar.d();
        this.f5224e = bVar.b();
    }

    @Override // g.a.b.l
    public int b(byte[] bArr, int i2, int i3) throws DataLengthException, IllegalArgumentException {
        boolean z;
        int i4 = i3;
        int i5 = i2;
        if (bArr.length - i4 < i5) {
            throw new OutputLengthException("output buffer too small");
        }
        long j = i4;
        int digestSize = this.f5220a.getDigestSize();
        if (j > 8589934591L) {
            throw new IllegalArgumentException("Output length too large");
        }
        long j2 = digestSize;
        int i6 = (int) (((j + j2) - 1) / j2);
        byte[] bArr2 = new byte[this.f5220a.getDigestSize()];
        int i7 = 0;
        int i8 = 0;
        int i9 = 1;
        while (i8 < i6) {
            n nVar = this.f5220a;
            byte[] bArr3 = this.f5223d;
            nVar.update(bArr3, i7, bArr3.length);
            f fVar = new f();
            f fVar2 = new f();
            fVar2.a(this.f5221b);
            fVar2.a(new y0(g.a.f.f.f(i9)));
            fVar.a(new c1(fVar2));
            if (this.f5224e != null) {
                z = true;
                fVar.a(new h1(true, i7, new y0(this.f5224e)));
            } else {
                z = true;
            }
            fVar.a(new h1(z, 2, new y0(g.a.f.f.f(this.f5222c))));
            try {
                byte[] bArrF = new c1(fVar).f("DER");
                this.f5220a.update(bArrF, 0, bArrF.length);
                this.f5220a.doFinal(bArr2, 0);
                if (i4 > digestSize) {
                    System.arraycopy(bArr2, 0, bArr, i5, digestSize);
                    i5 += digestSize;
                    i4 -= digestSize;
                } else {
                    System.arraycopy(bArr2, 0, bArr, i5, i4);
                }
                i9++;
                i8++;
                i7 = 0;
            } catch (IOException e2) {
                throw new IllegalArgumentException("unable to encode parameter info: " + e2.getMessage());
            }
        }
        this.f5220a.reset();
        return (int) j;
    }
}
