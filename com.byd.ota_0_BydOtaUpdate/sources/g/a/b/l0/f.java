package g.a.b.l0;

import g.a.b.n;
import g.a.b.t;
import java.security.SecureRandom;
import org.bouncycastle.crypto.prng.SP800SecureRandom;

/* JADX INFO: loaded from: classes.dex */
public class f {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final SecureRandom f5095a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final d f5096b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public byte[] f5097c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public int f5098d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public int f5099e;

    public static class a implements g.a.b.l0.b {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final t f5100a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final byte[] f5101b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public final byte[] f5102c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public final int f5103d;

        public a(t tVar, byte[] bArr, byte[] bArr2, int i2) {
            this.f5100a = tVar;
            this.f5101b = bArr;
            this.f5102c = bArr2;
            this.f5103d = i2;
        }

        @Override // g.a.b.l0.b
        public g.a.b.l0.h.c a(c cVar) {
            return new g.a.b.l0.h.a(this.f5100a, this.f5103d, cVar, this.f5102c, this.f5101b);
        }
    }

    public static class b implements g.a.b.l0.b {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final n f5104a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final byte[] f5105b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public final byte[] f5106c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public final int f5107d;

        public b(n nVar, byte[] bArr, byte[] bArr2, int i2) {
            this.f5104a = nVar;
            this.f5105b = bArr;
            this.f5106c = bArr2;
            this.f5107d = i2;
        }

        @Override // g.a.b.l0.b
        public g.a.b.l0.h.c a(c cVar) {
            return new g.a.b.l0.h.b(this.f5104a, this.f5107d, cVar, this.f5106c, this.f5105b);
        }
    }

    public f(d dVar) {
        this.f5098d = 256;
        this.f5099e = 256;
        this.f5095a = null;
        this.f5096b = dVar;
    }

    public f(SecureRandom secureRandom, boolean z) {
        this.f5098d = 256;
        this.f5099e = 256;
        this.f5095a = secureRandom;
        this.f5096b = new g.a.b.l0.a(secureRandom, z);
    }

    public SP800SecureRandom a(t tVar, byte[] bArr, boolean z) {
        return new SP800SecureRandom(this.f5095a, this.f5096b.get(this.f5099e), new a(tVar, bArr, this.f5097c, this.f5098d), z);
    }

    public SP800SecureRandom b(n nVar, byte[] bArr, boolean z) {
        return new SP800SecureRandom(this.f5095a, this.f5096b.get(this.f5099e), new b(nVar, bArr, this.f5097c, this.f5098d), z);
    }

    public f c(byte[] bArr) {
        this.f5097c = bArr;
        return this;
    }
}
