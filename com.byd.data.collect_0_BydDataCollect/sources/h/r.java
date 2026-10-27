package h;

import f.a0;
import f.e0;
import f.f0;
import f.u;
import f.w;
import f.x;
import f.z;
import java.util.regex.Pattern;
import javax.annotation.Nullable;
import okio.Buffer;
import okio.BufferedSink;

/* JADX INFO: compiled from: RequestBuilder.java */
/* JADX INFO: loaded from: classes.dex */
final class r {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private static final char[] f5656a = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'};

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private static final Pattern f5657b = Pattern.compile("(.*/)?(\\.|%2e|%2E){1,2}(/.*)?");

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private final String f5658c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private final f.x f5659d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    @Nullable
    private String f5660e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    @Nullable
    private x.a f5661f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    private final e0.a f5662g = new e0.a();

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    private final w.a f5663h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    @Nullable
    private z f5664i;
    private final boolean j;

    @Nullable
    private a0.a k;

    @Nullable
    private u.a l;

    @Nullable
    private f0 m;

    /* JADX INFO: compiled from: RequestBuilder.java */
    private static class a extends f0 {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        private final f0 f5665a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        private final z f5666b;

        a(f0 f0Var, z zVar) {
            this.f5665a = f0Var;
            this.f5666b = zVar;
        }

        @Override // f.f0
        public long a() {
            return this.f5665a.a();
        }

        @Override // f.f0
        public z b() {
            return this.f5666b;
        }

        @Override // f.f0
        public void j(BufferedSink bufferedSink) {
            this.f5665a.j(bufferedSink);
        }
    }

    r(String str, f.x xVar, @Nullable String str2, @Nullable f.w wVar, @Nullable z zVar, boolean z, boolean z2, boolean z3) {
        this.f5658c = str;
        this.f5659d = xVar;
        this.f5660e = str2;
        this.f5664i = zVar;
        this.j = z;
        if (wVar != null) {
            this.f5663h = wVar.f();
        } else {
            this.f5663h = new w.a();
        }
        if (z2) {
            this.l = new u.a();
        } else if (z3) {
            a0.a aVar = new a0.a();
            this.k = aVar;
            aVar.e(a0.f5000e);
        }
    }

    private static String i(String str, boolean z) {
        int length = str.length();
        int iCharCount = 0;
        while (iCharCount < length) {
            int iCodePointAt = str.codePointAt(iCharCount);
            if (iCodePointAt < 32 || iCodePointAt >= 127 || " \"<>^`{}|\\?#".indexOf(iCodePointAt) != -1 || (!z && (iCodePointAt == 47 || iCodePointAt == 37))) {
                Buffer buffer = new Buffer();
                buffer.v0(str, 0, iCharCount);
                j(buffer, str, iCharCount, length, z);
                return buffer.O();
            }
            iCharCount += Character.charCount(iCodePointAt);
        }
        return str;
    }

    private static void j(Buffer buffer, String str, int i2, int i3, boolean z) {
        Buffer buffer2 = null;
        while (i2 < i3) {
            int iCodePointAt = str.codePointAt(i2);
            if (!z || (iCodePointAt != 9 && iCodePointAt != 10 && iCodePointAt != 12 && iCodePointAt != 13)) {
                if (iCodePointAt < 32 || iCodePointAt >= 127 || " \"<>^`{}|\\?#".indexOf(iCodePointAt) != -1 || (!z && (iCodePointAt == 47 || iCodePointAt == 37))) {
                    if (buffer2 == null) {
                        buffer2 = new Buffer();
                    }
                    buffer2.w0(iCodePointAt);
                    while (!buffer2.I()) {
                        int iP = buffer2.P() & 255;
                        buffer.J(37);
                        char[] cArr = f5656a;
                        buffer.J(cArr[(iP >> 4) & 15]);
                        buffer.J(cArr[iP & 15]);
                    }
                } else {
                    buffer.w0(iCodePointAt);
                }
            }
            i2 += Character.charCount(iCodePointAt);
        }
    }

    void a(String str, String str2, boolean z) {
        if (z) {
            this.l.b(str, str2);
        } else {
            this.l.a(str, str2);
        }
    }

    void b(String str, String str2) {
        if (!"Content-Type".equalsIgnoreCase(str)) {
            this.f5663h.a(str, str2);
            return;
        }
        try {
            this.f5664i = z.c(str2);
        } catch (IllegalArgumentException e2) {
            throw new IllegalArgumentException("Malformed content type: " + str2, e2);
        }
    }

    void c(f.w wVar) {
        this.f5663h.b(wVar);
    }

    void d(f.w wVar, f0 f0Var) {
        this.k.b(wVar, f0Var);
    }

    void e(a0.b bVar) {
        this.k.c(bVar);
    }

    void f(String str, String str2, boolean z) {
        if (this.f5660e == null) {
            throw new AssertionError();
        }
        String strI = i(str2, z);
        String strReplace = this.f5660e.replace("{" + str + "}", strI);
        if (!f5657b.matcher(strReplace).matches()) {
            this.f5660e = strReplace;
            return;
        }
        throw new IllegalArgumentException("@Path parameters shouldn't perform path traversal ('.' or '..'): " + str2);
    }

    void g(String str, @Nullable String str2, boolean z) {
        String str3 = this.f5660e;
        if (str3 != null) {
            x.a aVarQ = this.f5659d.q(str3);
            this.f5661f = aVarQ;
            if (aVarQ == null) {
                throw new IllegalArgumentException("Malformed URL. Base: " + this.f5659d + ", Relative: " + this.f5660e);
            }
            this.f5660e = null;
        }
        if (z) {
            this.f5661f.a(str, str2);
        } else {
            this.f5661f.b(str, str2);
        }
    }

    <T> void h(Class<T> cls, @Nullable T t) {
        this.f5662g.i(cls, t);
    }

    e0.a k() {
        f.x xVarC;
        x.a aVar = this.f5661f;
        if (aVar != null) {
            xVarC = aVar.c();
        } else {
            xVarC = this.f5659d.C(this.f5660e);
            if (xVarC == null) {
                throw new IllegalArgumentException("Malformed URL. Base: " + this.f5659d + ", Relative: " + this.f5660e);
            }
        }
        f0 aVar2 = this.m;
        if (aVar2 == null) {
            u.a aVar3 = this.l;
            if (aVar3 != null) {
                aVar2 = aVar3.c();
            } else {
                a0.a aVar4 = this.k;
                if (aVar4 != null) {
                    aVar2 = aVar4.d();
                } else if (this.j) {
                    aVar2 = f0.f(null, new byte[0]);
                }
            }
        }
        z zVar = this.f5664i;
        if (zVar != null) {
            if (aVar2 != null) {
                aVar2 = new a(aVar2, zVar);
            } else {
                this.f5663h.a("Content-Type", zVar.toString());
            }
        }
        return this.f5662g.j(xVarC).c(this.f5663h.f()).d(this.f5658c, aVar2);
    }

    void l(f0 f0Var) {
        this.m = f0Var;
    }

    void m(Object obj) {
        this.f5660e = obj.toString();
    }
}
