package e;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;
import javax.annotation.Nullable;
import okio.ByteString;

/* JADX INFO: compiled from: MultipartBody.java */
/* JADX INFO: loaded from: classes.dex */
public final class v extends z {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final u f3642a = u.c("multipart/mixed");

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final u f3643b = u.c("multipart/alternative");

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public static final u f3644c = u.c("multipart/digest");

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public static final u f3645d = u.c("multipart/parallel");

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public static final u f3646e = u.c("multipart/form-data");

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public static final byte[] f3647f = {58, 32};

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public static final byte[] f3648g = {13, 10};

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public static final byte[] f3649h = {45, 45};

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final ByteString f3650i;
    public final u j;
    public final u k;
    public final List<b> l;
    public long m = -1;

    /* JADX INFO: compiled from: MultipartBody.java */
    public static final class a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final ByteString f3651a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public u f3652b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public final List<b> f3653c;

        public a() {
            this(UUID.randomUUID().toString());
        }

        public a a(String str, String str2) {
            return d(b.b(str, str2));
        }

        public a b(String str, @Nullable String str2, z zVar) {
            return d(b.c(str, str2, zVar));
        }

        public a c(@Nullable s sVar, z zVar) {
            return d(b.a(sVar, zVar));
        }

        public a d(b bVar) {
            Objects.requireNonNull(bVar, "part == null");
            this.f3653c.add(bVar);
            return this;
        }

        public v e() {
            if (this.f3653c.isEmpty()) {
                throw new IllegalStateException("Multipart body must have at least one part.");
            }
            return new v(this.f3651a, this.f3652b, this.f3653c);
        }

        public a f(u uVar) {
            Objects.requireNonNull(uVar, "type == null");
            if (uVar.d().equals("multipart")) {
                this.f3652b = uVar;
                return this;
            }
            throw new IllegalArgumentException("multipart != " + uVar);
        }

        public a(String str) {
            this.f3652b = v.f3642a;
            this.f3653c = new ArrayList();
            this.f3651a = ByteString.encodeUtf8(str);
        }
    }

    /* JADX INFO: compiled from: MultipartBody.java */
    public static final class b {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        @Nullable
        public final s f3654a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final z f3655b;

        public b(@Nullable s sVar, z zVar) {
            this.f3654a = sVar;
            this.f3655b = zVar;
        }

        public static b a(@Nullable s sVar, z zVar) {
            Objects.requireNonNull(zVar, "body == null");
            if (sVar != null && sVar.a("Content-Type") != null) {
                throw new IllegalArgumentException("Unexpected header: Content-Type");
            }
            if (sVar == null || sVar.a("Content-Length") == null) {
                return new b(sVar, zVar);
            }
            throw new IllegalArgumentException("Unexpected header: Content-Length");
        }

        public static b b(String str, String str2) {
            return c(str, null, z.d(null, str2));
        }

        public static b c(String str, @Nullable String str2, z zVar) {
            Objects.requireNonNull(str, "name == null");
            StringBuilder sb = new StringBuilder("form-data; name=");
            v.i(sb, str);
            if (str2 != null) {
                sb.append("; filename=");
                v.i(sb, str2);
            }
            return a(s.e("Content-Disposition", sb.toString()), zVar);
        }
    }

    public v(ByteString byteString, u uVar, List<b> list) {
        this.f3650i = byteString;
        this.j = uVar;
        this.k = u.c(uVar + "; boundary=" + byteString.utf8());
        this.l = e.d0.c.n(list);
    }

    public static StringBuilder i(StringBuilder sb, String str) {
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

    @Override // e.z
    public long a() throws IOException {
        long j = this.m;
        if (j != -1) {
            return j;
        }
        long j2 = j(null, true);
        this.m = j2;
        return j2;
    }

    @Override // e.z
    public u b() {
        return this.k;
    }

    @Override // e.z
    public void h(f.d dVar) throws IOException {
        j(dVar, false);
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final long j(@Nullable f.d dVar, boolean z) throws IOException {
        f.c cVar;
        if (z) {
            dVar = new f.c();
            cVar = dVar;
        } else {
            cVar = 0;
        }
        int size = this.l.size();
        long j = 0;
        for (int i2 = 0; i2 < size; i2++) {
            b bVar = this.l.get(i2);
            s sVar = bVar.f3654a;
            z zVar = bVar.f3655b;
            dVar.f(f3649h);
            dVar.i(this.f3650i);
            dVar.f(f3648g);
            if (sVar != null) {
                int iF = sVar.f();
                for (int i3 = 0; i3 < iF; i3++) {
                    dVar.w(sVar.c(i3)).f(f3647f).w(sVar.g(i3)).f(f3648g);
                }
            }
            u uVarB = zVar.b();
            if (uVarB != null) {
                dVar.w("Content-Type: ").w(uVarB.toString()).f(f3648g);
            }
            long jA = zVar.a();
            if (jA != -1) {
                dVar.w("Content-Length: ").y(jA).f(f3648g);
            } else if (z) {
                cVar.H();
                return -1L;
            }
            byte[] bArr = f3648g;
            dVar.f(bArr);
            if (z) {
                j += jA;
            } else {
                zVar.h(dVar);
            }
            dVar.f(bArr);
        }
        byte[] bArr2 = f3649h;
        dVar.f(bArr2);
        dVar.i(this.f3650i);
        dVar.f(bArr2);
        dVar.f(f3648g);
        if (!z) {
            return j;
        }
        long jY = j + cVar.Y();
        cVar.H();
        return jY;
    }
}
