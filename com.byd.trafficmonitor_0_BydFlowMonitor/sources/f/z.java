package f;

import java.io.Closeable;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.util.Objects;

/* JADX INFO: compiled from: ResponseBody.java */
/* JADX INFO: loaded from: classes.dex */
public abstract class z implements Closeable {

    /* JADX INFO: compiled from: ResponseBody.java */
    public static class a extends z {

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final /* synthetic */ s f5455b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public final /* synthetic */ long f5456c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public final /* synthetic */ g.e f5457d;

        public a(s sVar, long j, g.e eVar) {
            this.f5455b = sVar;
            this.f5456c = j;
            this.f5457d = eVar;
        }

        @Override // f.z
        public long O() {
            return this.f5456c;
        }

        @Override // f.z
        public s P() {
            return this.f5455b;
        }

        @Override // f.z
        public g.e S() {
            return this.f5457d;
        }
    }

    public static z Q(s sVar, long j, g.e eVar) {
        Objects.requireNonNull(eVar, "source == null");
        return new a(sVar, j, eVar);
    }

    public static z R(s sVar, byte[] bArr) {
        return Q(sVar, bArr.length, new g.c().c(bArr));
    }

    public abstract long O();

    public abstract s P();

    public abstract g.e S();

    public final String T() throws IOException {
        g.e eVarS = S();
        try {
            return eVarS.L(f.b0.c.a(eVarS, z()));
        } finally {
            f.b0.c.c(eVarS);
        }
    }

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public void close() {
        f.b0.c.c(S());
    }

    public final InputStream w() {
        return S().M();
    }

    public final Charset z() {
        s sVarP = P();
        return sVarP != null ? sVarP.b(f.b0.c.j) : f.b0.c.j;
    }
}
