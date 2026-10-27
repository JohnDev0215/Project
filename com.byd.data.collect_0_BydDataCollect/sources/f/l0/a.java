package f.l0;

import f.e0;
import f.f0;
import f.g0;
import f.h0;
import f.k0.i.e;
import f.k0.l.f;
import f.m;
import f.w;
import f.y;
import f.z;
import java.io.EOFException;
import java.nio.charset.Charset;
import java.util.Objects;
import java.util.concurrent.TimeUnit;
import okio.Buffer;
import okio.BufferedSource;
import okio.GzipSource;

/* JADX INFO: compiled from: HttpLoggingInterceptor.java */
/* JADX INFO: loaded from: classes.dex */
public final class a implements y {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private static final Charset f5460a = Charset.forName("UTF-8");

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final b f5461b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private volatile EnumC0102a f5462c = EnumC0102a.NONE;

    /* JADX INFO: renamed from: f.l0.a$a, reason: collision with other inner class name */
    /* JADX INFO: compiled from: HttpLoggingInterceptor.java */
    public enum EnumC0102a {
        NONE,
        BASIC,
        HEADERS,
        BODY
    }

    /* JADX INFO: compiled from: HttpLoggingInterceptor.java */
    public interface b {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public static final b f5468a = new C0103a();

        /* JADX INFO: renamed from: f.l0.a$b$a, reason: collision with other inner class name */
        /* JADX INFO: compiled from: HttpLoggingInterceptor.java */
        final class C0103a implements b {
            C0103a() {
            }

            @Override // f.l0.a.b
            public void a(String str) {
                f.j().p(4, str, null);
            }
        }

        void a(String str);
    }

    public a(b bVar) {
        this.f5461b = bVar;
    }

    private boolean b(w wVar) {
        String strC = wVar.c("Content-Encoding");
        return (strC == null || strC.equalsIgnoreCase("identity") || strC.equalsIgnoreCase("gzip")) ? false : true;
    }

    static boolean c(Buffer buffer) {
        try {
            Buffer buffer2 = new Buffer();
            buffer.U(buffer2, 0L, buffer.g0() < 64 ? buffer.g0() : 64L);
            for (int i2 = 0; i2 < 16; i2++) {
                if (buffer2.I()) {
                    return true;
                }
                int iD0 = buffer2.d0();
                if (Character.isISOControl(iD0) && !Character.isWhitespace(iD0)) {
                    return false;
                }
            }
            return true;
        } catch (EOFException unused) {
            return false;
        }
    }

    @Override // f.y
    public g0 a(y.a aVar) throws Exception {
        boolean z;
        long j;
        char c2;
        String string;
        boolean z2;
        EnumC0102a enumC0102a = this.f5462c;
        e0 e0VarA = aVar.a();
        if (enumC0102a == EnumC0102a.NONE) {
            return aVar.d(e0VarA);
        }
        boolean z3 = enumC0102a == EnumC0102a.BODY;
        boolean z4 = z3 || enumC0102a == EnumC0102a.HEADERS;
        f0 f0VarA = e0VarA.a();
        boolean z5 = f0VarA != null;
        m mVarB = aVar.b();
        StringBuilder sb = new StringBuilder();
        sb.append("--> ");
        sb.append(e0VarA.f());
        sb.append(' ');
        sb.append(e0VarA.i());
        sb.append(mVarB != null ? " " + mVarB.a() : "");
        String string2 = sb.toString();
        if (!z4 && z5) {
            string2 = string2 + " (" + f0VarA.a() + "-byte body)";
        }
        this.f5461b.a(string2);
        if (z4) {
            if (z5) {
                if (f0VarA.b() != null) {
                    this.f5461b.a("Content-Type: " + f0VarA.b());
                }
                if (f0VarA.a() != -1) {
                    this.f5461b.a("Content-Length: " + f0VarA.a());
                }
            }
            w wVarD = e0VarA.d();
            int iH = wVarD.h();
            int i2 = 0;
            while (i2 < iH) {
                String strE = wVarD.e(i2);
                int i3 = iH;
                if ("Content-Type".equalsIgnoreCase(strE) || "Content-Length".equalsIgnoreCase(strE)) {
                    z2 = z4;
                } else {
                    z2 = z4;
                    this.f5461b.a(strE + ": " + wVarD.i(i2));
                }
                i2++;
                iH = i3;
                z4 = z2;
            }
            z = z4;
            if (!z3 || !z5) {
                this.f5461b.a("--> END " + e0VarA.f());
            } else if (b(e0VarA.d())) {
                this.f5461b.a("--> END " + e0VarA.f() + " (encoded body omitted)");
            } else {
                Buffer buffer = new Buffer();
                f0VarA.j(buffer);
                Charset charsetB = f5460a;
                z zVarB = f0VarA.b();
                if (zVarB != null) {
                    charsetB = zVarB.b(charsetB);
                }
                this.f5461b.a("");
                if (c(buffer)) {
                    this.f5461b.a(buffer.N(charsetB));
                    this.f5461b.a("--> END " + e0VarA.f() + " (" + f0VarA.a() + "-byte body)");
                } else {
                    this.f5461b.a("--> END " + e0VarA.f() + " (binary " + f0VarA.a() + "-byte body omitted)");
                }
            }
        } else {
            z = z4;
        }
        long jNanoTime = System.nanoTime();
        try {
            g0 g0VarD = aVar.d(e0VarA);
            long millis = TimeUnit.NANOSECONDS.toMillis(System.nanoTime() - jNanoTime);
            h0 h0VarA = g0VarD.a();
            long j2 = h0VarA.j();
            String str = j2 != -1 ? j2 + "-byte" : "unknown-length";
            b bVar = this.f5461b;
            StringBuilder sb2 = new StringBuilder();
            sb2.append("<-- ");
            sb2.append(g0VarD.j());
            if (g0VarD.U().isEmpty()) {
                j = j2;
                string = "";
                c2 = ' ';
            } else {
                StringBuilder sb3 = new StringBuilder();
                j = j2;
                c2 = ' ';
                sb3.append(' ');
                sb3.append(g0VarD.U());
                string = sb3.toString();
            }
            sb2.append(string);
            sb2.append(c2);
            sb2.append(g0VarD.Y().i());
            sb2.append(" (");
            sb2.append(millis);
            sb2.append("ms");
            sb2.append(z ? "" : ", " + str + " body");
            sb2.append(')');
            bVar.a(sb2.toString());
            if (z) {
                w wVarS = g0VarD.S();
                int iH2 = wVarS.h();
                for (int i4 = 0; i4 < iH2; i4++) {
                    this.f5461b.a(wVarS.e(i4) + ": " + wVarS.i(i4));
                }
                if (!z3 || !e.c(g0VarD)) {
                    this.f5461b.a("<-- END HTTP");
                } else if (b(g0VarD.S())) {
                    this.f5461b.a("<-- END HTTP (encoded body omitted)");
                } else {
                    BufferedSource bufferedSourceS = h0VarA.S();
                    bufferedSourceS.c(Long.MAX_VALUE);
                    Buffer bufferB = bufferedSourceS.b();
                    Long l = null;
                    GzipSource gzipSource = null;
                    if ("gzip".equalsIgnoreCase(wVarS.c("Content-Encoding"))) {
                        Long lValueOf = Long.valueOf(bufferB.g0());
                        try {
                            GzipSource gzipSource2 = new GzipSource(bufferB.clone());
                            try {
                                bufferB = new Buffer();
                                bufferB.m(gzipSource2);
                                gzipSource2.close();
                                l = lValueOf;
                            } catch (Throwable th) {
                                th = th;
                                gzipSource = gzipSource2;
                                if (gzipSource != null) {
                                    gzipSource.close();
                                }
                                throw th;
                            }
                        } catch (Throwable th2) {
                            th = th2;
                        }
                    }
                    Charset charsetB2 = f5460a;
                    z zVarY = h0VarA.y();
                    if (zVarY != null) {
                        charsetB2 = zVarY.b(charsetB2);
                    }
                    if (!c(bufferB)) {
                        this.f5461b.a("");
                        this.f5461b.a("<-- END HTTP (binary " + bufferB.g0() + "-byte body omitted)");
                        return g0VarD;
                    }
                    if (j != 0) {
                        this.f5461b.a("");
                        this.f5461b.a(bufferB.clone().N(charsetB2));
                    }
                    if (l != null) {
                        this.f5461b.a("<-- END HTTP (" + bufferB.g0() + "-byte, " + l + "-gzipped-byte body)");
                    } else {
                        this.f5461b.a("<-- END HTTP (" + bufferB.g0() + "-byte body)");
                    }
                }
            }
            return g0VarD;
        } catch (Exception e2) {
            this.f5461b.a("<-- HTTP FAILED: " + e2);
            throw e2;
        }
    }

    public a d(EnumC0102a enumC0102a) {
        Objects.requireNonNull(enumC0102a, "level == null. Use Level.NONE instead.");
        this.f5462c = enumC0102a;
        return this;
    }
}
