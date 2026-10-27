package f;

import f.w;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;
import javax.annotation.Nullable;
import okio.Buffer;
import okio.BufferedSink;
import okio.ByteString;

/* JADX INFO: compiled from: MultipartBody.java */
/* JADX INFO: loaded from: classes.dex */
public final class a0 extends f0 {

    /* JADX INFO: renamed from: a */
    public static final z f4996a = z.c("multipart/mixed");

    /* JADX INFO: renamed from: b */
    public static final z f4997b = z.c("multipart/alternative");

    /* JADX INFO: renamed from: c */
    public static final z f4998c = z.c("multipart/digest");

    /* JADX INFO: renamed from: d */
    public static final z f4999d = z.c("multipart/parallel");

    /* JADX INFO: renamed from: e */
    public static final z f5000e = z.c("multipart/form-data");

    /* JADX INFO: renamed from: f */
    private static final byte[] f5001f = {58, 32};

    /* JADX INFO: renamed from: g */
    private static final byte[] f5002g = {13, 10};

    /* JADX INFO: renamed from: h */
    private static final byte[] f5003h = {45, 45};

    /* JADX INFO: renamed from: i */
    private final ByteString f5004i;
    private final z j;
    private final z k;
    private final List<b> l;
    private long m = -1;

    /* JADX INFO: compiled from: MultipartBody.java */
    public static final class a {

        /* JADX INFO: renamed from: a */
        private final ByteString f5005a;

        /* JADX INFO: renamed from: b */
        private z f5006b;

        /* JADX INFO: renamed from: c */
        private final List<b> f5007c;

        public a() {
            this(UUID.randomUUID().toString());
        }

        public a a(String str, String str2) {
            return c(b.c(str, str2));
        }

        public a b(@Nullable w wVar, f0 f0Var) {
            return c(b.b(wVar, f0Var));
        }

        public a c(b bVar) {
            Objects.requireNonNull(bVar, "part == null");
            this.f5007c.add(bVar);
            return this;
        }

        public a0 d() {
            if (this.f5007c.isEmpty()) {
                throw new IllegalStateException("Multipart body must have at least one part.");
            }
            return new a0(this.f5005a, this.f5006b, this.f5007c);
        }

        public a e(z zVar) {
            Objects.requireNonNull(zVar, "type == null");
            if (zVar.e().equals("multipart")) {
                this.f5006b = zVar;
                return this;
            }
            throw new IllegalArgumentException("multipart != " + zVar);
        }

        public a(String str) {
            this.f5006b = a0.f4996a;
            this.f5007c = new ArrayList();
            this.f5005a = ByteString.i(str);
        }
    }

    /* JADX INFO: compiled from: MultipartBody.java */
    public static final class b {

        /* JADX INFO: renamed from: a */
        @Nullable
        final w f5008a;

        /* JADX INFO: renamed from: b */
        final f0 f5009b;

        private b(@Nullable w wVar, f0 f0Var) {
            this.f5008a = wVar;
            this.f5009b = f0Var;
        }

        public static b b(@Nullable w wVar, f0 f0Var) {
            Objects.requireNonNull(f0Var, "body == null");
            if (wVar != null && wVar.c("Content-Type") != null) {
                throw new IllegalArgumentException("Unexpected header: Content-Type");
            }
            if (wVar == null || wVar.c("Content-Length") == null) {
                return new b(wVar, f0Var);
            }
            throw new IllegalArgumentException("Unexpected header: Content-Length");
        }

        public static b c(String str, String str2) {
            return d(str, null, f0.d(null, str2));
        }

        public static b d(String str, @Nullable String str2, f0 f0Var) {
            Objects.requireNonNull(str, "name == null");
            StringBuilder sb = new StringBuilder("form-data; name=");
            a0.k(sb, str);
            if (str2 != null) {
                sb.append("; filename=");
                a0.k(sb, str2);
            }
            return b(new w.a().e("Content-Disposition", sb.toString()).f(), f0Var);
        }

        public f0 a() {
            return this.f5009b;
        }
    }

    a0(ByteString byteString, z zVar, List<b> list) {
        this.f5004i = byteString;
        this.j = zVar;
        this.k = z.c(zVar + "; boundary=" + byteString.x());
        this.l = f.k0.e.s(list);
    }

    static void k(StringBuilder sb, String str) {
        sb.append('\"');
        int length = str.length();
        for (int i2 = 0; i2 < length; i2++) {
            char cCharAt = str.charAt(i2);
            if (cCharAt == '\n') {
                sb.append("%0A");
            } else if (cCharAt == '\r') {
                sb.append("%0D");
            } else if (cCharAt != '\"') {
                sb.append(cCharAt);
            } else {
                sb.append("%22");
            }
        }
        sb.append('\"');
    }

    /* JADX WARN: Multi-variable type inference failed */
    private long o(@Nullable BufferedSink bufferedSink, boolean z) {
        Buffer buffer;
        if (z) {
            bufferedSink = new Buffer();
            buffer = bufferedSink;
        } else {
            buffer = 0;
        }
        int size = this.l.size();
        long j = 0;
        for (int i2 = 0; i2 < size; i2++) {
            b bVar = this.l.get(i2);
            w wVar = bVar.f5008a;
            f0 f0Var = bVar.f5009b;
            bufferedSink.f(f5003h);
            bufferedSink.i(this.f5004i);
            bufferedSink.f(f5002g);
            if (wVar != null) {
                int iH = wVar.h();
                for (int i3 = 0; i3 < iH; i3++) {
                    bufferedSink.D(wVar.e(i3)).f(f5001f).D(wVar.i(i3)).f(f5002g);
                }
            }
            z zVarB = f0Var.b();
            if (zVarB != null) {
                bufferedSink.D("Content-Type: ").D(zVarB.toString()).f(f5002g);
            }
            long jA = f0Var.a();
            if (jA != -1) {
                bufferedSink.D("Content-Length: ").F(jA).f(f5002g);
            } else if (z) {
                buffer.R();
                return -1L;
            }
            byte[] bArr = f5002g;
            bufferedSink.f(bArr);
            if (z) {
                j += jA;
            } else {
                f0Var.j(bufferedSink);
            }
            bufferedSink.f(bArr);
        }
        byte[] bArr2 = f5003h;
        bufferedSink.f(bArr2);
        bufferedSink.i(this.f5004i);
        bufferedSink.f(bArr2);
        bufferedSink.f(f5002g);
        if (!z) {
            return j;
        }
        long jG0 = j + buffer.g0();
        buffer.R();
        return jG0;
    }

    @Override // f.f0
    public long a() {
        long j = this.m;
        if (j != -1) {
            return j;
        }
        long jO = o(null, true);
        this.m = jO;
        return jO;
    }

    @Override // f.f0
    public z b() {
        return this.k;
    }

    @Override // f.f0
    public void j(BufferedSink bufferedSink) {
        o(bufferedSink, false);
    }

    public List<b> l() {
        return this.l;
    }

    public int m() {
        return this.l.size();
    }

    public z n() {
        return this.j;
    }
}
