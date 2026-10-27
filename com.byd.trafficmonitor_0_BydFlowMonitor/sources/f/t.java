package f;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;
import okio.ByteString;

/* JADX INFO: compiled from: MultipartBody.java */
/* JADX INFO: loaded from: classes.dex */
public final class t extends x {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final s f5381a = s.c("multipart/mixed");

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final s f5382b = s.c("multipart/alternative");

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public static final s f5383c = s.c("multipart/digest");

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public static final s f5384d = s.c("multipart/parallel");

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public static final s f5385e = s.c("multipart/form-data");

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public static final byte[] f5386f = {58, 32};

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public static final byte[] f5387g = {13, 10};

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public static final byte[] f5388h = {45, 45};

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final ByteString f5389i;
    public final s j;
    public final s k;
    public final List<b> l;
    public long m = -1;

    /* JADX INFO: compiled from: MultipartBody.java */
    public static final class a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final ByteString f5390a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public s f5391b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public final List<b> f5392c;

        public a() {
            this(UUID.randomUUID().toString());
        }

        public a a(String str, String str2) {
            return c(b.b(str, str2));
        }

        public a b(String str, String str2, x xVar) {
            return c(b.c(str, str2, xVar));
        }

        public a c(b bVar) {
            Objects.requireNonNull(bVar, "part == null");
            this.f5392c.add(bVar);
            return this;
        }

        public t d() {
            if (this.f5392c.isEmpty()) {
                throw new IllegalStateException("Multipart body must have at least one part.");
            }
            return new t(this.f5390a, this.f5391b, this.f5392c);
        }

        public a(String str) {
            this.f5391b = t.f5381a;
            this.f5392c = new ArrayList();
            this.f5390a = ByteString.encodeUtf8(str);
        }
    }

    /* JADX INFO: compiled from: MultipartBody.java */
    public static final class b {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final q f5393a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final x f5394b;

        public b(q qVar, x xVar) {
            this.f5393a = qVar;
            this.f5394b = xVar;
        }

        public static b a(q qVar, x xVar) {
            Objects.requireNonNull(xVar, "body == null");
            if (qVar != null && qVar.a("Content-Type") != null) {
                throw new IllegalArgumentException("Unexpected header: Content-Type");
            }
            if (qVar == null || qVar.a("Content-Length") == null) {
                return new b(qVar, xVar);
            }
            throw new IllegalArgumentException("Unexpected header: Content-Length");
        }

        public static b b(String str, String str2) {
            return c(str, null, x.create((s) null, str2));
        }

        public static b c(String str, String str2, x xVar) {
            Objects.requireNonNull(str, "name == null");
            StringBuilder sb = new StringBuilder("form-data; name=");
            t.a(sb, str);
            if (str2 != null) {
                sb.append("; filename=");
                t.a(sb, str2);
            }
            return a(q.e("Content-Disposition", sb.toString()), xVar);
        }
    }

    public t(ByteString byteString, s sVar, List<b> list) {
        this.f5389i = byteString;
        this.j = sVar;
        this.k = s.c(sVar + "; boundary=" + byteString.utf8());
        this.l = f.b0.c.n(list);
    }

    public static StringBuilder a(StringBuilder sb, String str) {
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
        return sb;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final long b(g.d dVar, boolean z) throws IOException {
        g.c cVar;
        if (z) {
            dVar = new g.c();
            cVar = dVar;
        } else {
            cVar = 0;
        }
        int size = this.l.size();
        long j = 0;
        for (int i2 = 0; i2 < size; i2++) {
            b bVar = this.l.get(i2);
            q qVar = bVar.f5393a;
            x xVar = bVar.f5394b;
            dVar.c(f5388h);
            dVar.h(this.f5389i);
            dVar.c(f5387g);
            if (qVar != null) {
                int iF = qVar.f();
                for (int i3 = 0; i3 < iF; i3++) {
                    dVar.A(qVar.c(i3)).c(f5386f).A(qVar.g(i3)).c(f5387g);
                }
            }
            s sVarContentType = xVar.contentType();
            if (sVarContentType != null) {
                dVar.A("Content-Type: ").A(sVarContentType.toString()).c(f5387g);
            }
            long jContentLength = xVar.contentLength();
            if (jContentLength != -1) {
                dVar.A("Content-Length: ").C(jContentLength).c(f5387g);
            } else if (z) {
                cVar.O();
                return -1L;
            }
            byte[] bArr = f5387g;
            dVar.c(bArr);
            if (z) {
                j += jContentLength;
            } else {
                xVar.writeTo(dVar);
            }
            dVar.c(bArr);
        }
        byte[] bArr2 = f5388h;
        dVar.c(bArr2);
        dVar.h(this.f5389i);
        dVar.c(bArr2);
        dVar.c(f5387g);
        if (!z) {
            return j;
        }
        long jF0 = j + cVar.f0();
        cVar.O();
        return jF0;
    }

    @Override // f.x
    public long contentLength() throws IOException {
        long j = this.m;
        if (j != -1) {
            return j;
        }
        long jB = b(null, true);
        this.m = jB;
        return jB;
    }

    @Override // f.x
    public s contentType() {
        return this.k;
    }

    @Override // f.x
    public void writeTo(g.d dVar) throws IOException {
        b(dVar, false);
    }
}
