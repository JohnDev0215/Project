package g.a.b.l0;

import java.security.SecureRandom;
import org.bouncycastle.crypto.prng.SP800SecureRandom;
import org.bouncycastle.crypto.prng.X931SecureRandom;

/* JADX INFO: loaded from: classes.dex */
public class a implements d {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final SecureRandom f5091a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final boolean f5092b;

    /* JADX INFO: renamed from: g.a.b.l0.a$a, reason: collision with other inner class name */
    public class C0092a implements c {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ int f5093a;

        public C0092a(int i2) {
            this.f5093a = i2;
        }

        @Override // g.a.b.l0.c
        public int entropySize() {
            return this.f5093a;
        }

        @Override // g.a.b.l0.c
        public byte[] getEntropy() {
            if (!(a.this.f5091a instanceof SP800SecureRandom) && !(a.this.f5091a instanceof X931SecureRandom)) {
                return a.this.f5091a.generateSeed((this.f5093a + 7) / 8);
            }
            byte[] bArr = new byte[(this.f5093a + 7) / 8];
            a.this.f5091a.nextBytes(bArr);
            return bArr;
        }
    }

    public a(SecureRandom secureRandom, boolean z) {
        this.f5091a = secureRandom;
        this.f5092b = z;
    }

    @Override // g.a.b.l0.d
    public c get(int i2) {
        return new C0092a(i2);
    }
}
