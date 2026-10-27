package okhttp3.logging;

import e.a0;
import e.b0;
import e.d0.j.e;
import e.i;
import e.s;
import e.t;
import e.u;
import e.y;
import e.z;
import f.c;
import java.io.EOFException;
import java.nio.charset.Charset;
import java.nio.charset.UnsupportedCharsetException;
import java.util.Objects;
import java.util.concurrent.TimeUnit;
import okhttp3.Protocol;

/* JADX INFO: loaded from: classes.dex */
public final class HttpLoggingInterceptor implements t {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final Charset f5940a = Charset.forName("UTF-8");

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final a f5941b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public volatile Level f5942c = Level.NONE;

    public enum Level {
        NONE,
        BASIC,
        HEADERS,
        BODY
    }

    public interface a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public static final a f5944a = new C0104a();

        /* JADX INFO: renamed from: okhttp3.logging.HttpLoggingInterceptor$a$a, reason: collision with other inner class name */
        public static class C0104a implements a {
            @Override // okhttp3.logging.HttpLoggingInterceptor.a
            public void a(String str) {
                e.h().l(4, str, null);
            }
        }

        void a(String str);
    }

    public HttpLoggingInterceptor(a aVar) {
        this.f5941b = aVar;
    }

    public static boolean c(c cVar) {
        try {
            c cVar2 = new c();
            cVar.K(cVar2, 0L, cVar.Y() < 64 ? cVar.Y() : 64L);
            for (int i2 = 0; i2 < 16; i2++) {
                if (cVar2.z()) {
                    return true;
                }
                int iV = cVar2.V();
                if (Character.isISOControl(iV) && !Character.isWhitespace(iV)) {
                    return false;
                }
            }
            return true;
        } catch (EOFException unused) {
            return false;
        }
    }

    @Override // e.t
    public a0 a(t.a aVar) throws Exception {
        boolean z;
        boolean z2;
        Level level = this.f5942c;
        y yVarB = aVar.b();
        if (level == Level.NONE) {
            return aVar.a(yVarB);
        }
        boolean z3 = level == Level.BODY;
        boolean z4 = z3 || level == Level.HEADERS;
        z zVarA = yVarB.a();
        boolean z5 = zVarA != null;
        i iVarC = aVar.c();
        String str = "--> " + yVarB.f() + ' ' + yVarB.h() + ' ' + (iVarC != null ? iVarC.a() : Protocol.HTTP_1_1);
        if (!z4 && z5) {
            str = str + " (" + zVarA.a() + "-byte body)";
        }
        this.f5941b.a(str);
        if (z4) {
            if (z5) {
                if (zVarA.b() != null) {
                    this.f5941b.a("Content-Type: " + zVarA.b());
                }
                if (zVarA.a() != -1) {
                    this.f5941b.a("Content-Length: " + zVarA.a());
                }
            }
            s sVarD = yVarB.d();
            int iF = sVarD.f();
            int i2 = 0;
            while (i2 < iF) {
                String strC = sVarD.c(i2);
                int i3 = iF;
                if ("Content-Type".equalsIgnoreCase(strC) || "Content-Length".equalsIgnoreCase(strC)) {
                    z2 = z4;
                } else {
                    z2 = z4;
                    this.f5941b.a(strC + ": " + sVarD.g(i2));
                }
                i2++;
                iF = i3;
                z4 = z2;
            }
            z = z4;
            if (!z3 || !z5) {
                this.f5941b.a("--> END " + yVarB.f());
            } else if (b(yVarB.d())) {
                this.f5941b.a("--> END " + yVarB.f() + " (encoded body omitted)");
            } else {
                c cVar = new c();
                zVarA.h(cVar);
                Charset charsetB = f5940a;
                u uVarB = zVarA.b();
                if (uVarB != null) {
                    charsetB = uVarB.b(charsetB);
                }
                this.f5941b.a("");
                if (c(cVar)) {
                    this.f5941b.a(cVar.T(charsetB));
                    this.f5941b.a("--> END " + yVarB.f() + " (" + zVarA.a() + "-byte body)");
                } else {
                    this.f5941b.a("--> END " + yVarB.f() + " (binary " + zVarA.a() + "-byte body omitted)");
                }
            }
        } else {
            z = z4;
        }
        long jNanoTime = System.nanoTime();
        try {
            a0 a0VarA = aVar.a(yVarB);
            long millis = TimeUnit.NANOSECONDS.toMillis(System.nanoTime() - jNanoTime);
            b0 b0VarC = a0VarA.c();
            long jV = b0VarC.v();
            String str2 = jV != -1 ? jV + "-byte" : "unknown-length";
            a aVar2 = this.f5941b;
            StringBuilder sb = new StringBuilder();
            sb.append("<-- ");
            sb.append(a0VarA.s());
            sb.append(' ');
            sb.append(a0VarA.L());
            sb.append(' ');
            sb.append(a0VarA.O().h());
            sb.append(" (");
            sb.append(millis);
            sb.append("ms");
            sb.append(z ? "" : ", " + str2 + " body");
            sb.append(')');
            aVar2.a(sb.toString());
            if (z) {
                s sVarJ = a0VarA.J();
                int iF2 = sVarJ.f();
                for (int i4 = 0; i4 < iF2; i4++) {
                    this.f5941b.a(sVarJ.c(i4) + ": " + sVarJ.g(i4));
                }
                if (!z3 || !e.d0.g.e.c(a0VarA)) {
                    this.f5941b.a("<-- END HTTP");
                } else if (b(a0VarA.J())) {
                    this.f5941b.a("<-- END HTTP (encoded body omitted)");
                } else {
                    f.e eVarK = b0VarC.K();
                    eVarK.b(Long.MAX_VALUE);
                    c cVarA = eVarK.a();
                    Charset charsetB2 = f5940a;
                    u uVarH = b0VarC.H();
                    if (uVarH != null) {
                        try {
                            charsetB2 = uVarH.b(charsetB2);
                        } catch (UnsupportedCharsetException unused) {
                            this.f5941b.a("");
                            this.f5941b.a("Couldn't decode the response body; charset is likely malformed.");
                            this.f5941b.a("<-- END HTTP");
                            return a0VarA;
                        }
                    }
                    if (!c(cVarA)) {
                        this.f5941b.a("");
                        this.f5941b.a("<-- END HTTP (binary " + cVarA.Y() + "-byte body omitted)");
                        return a0VarA;
                    }
                    if (jV != 0) {
                        this.f5941b.a("");
                        this.f5941b.a(cVarA.clone().T(charsetB2));
                    }
                    this.f5941b.a("<-- END HTTP (" + cVarA.Y() + "-byte body)");
                }
            }
            return a0VarA;
        } catch (Exception e2) {
            this.f5941b.a("<-- HTTP FAILED: " + e2);
            throw e2;
        }
    }

    public final boolean b(s sVar) {
        String strA = sVar.a("Content-Encoding");
        return (strA == null || strA.equalsIgnoreCase("identity")) ? false : true;
    }

    public HttpLoggingInterceptor d(Level level) {
        Objects.requireNonNull(level, "level == null. Use Level.NONE instead.");
        this.f5942c = level;
        return this;
    }
}
