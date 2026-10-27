package k6;

import h5.x;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import p6.a0;
import p6.o;

/* JADX INFO: compiled from: Hpack.kt */
/* JADX INFO: loaded from: classes.dex */
public final class d {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private static final c[] f7289a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private static final Map<p6.h, Integer> f7290b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public static final d f7291c;

    static {
        d dVar = new d();
        f7291c = dVar;
        p6.h hVar = c.f7281f;
        p6.h hVar2 = c.f7282g;
        p6.h hVar3 = c.f7283h;
        p6.h hVar4 = c.f7280e;
        f7289a = new c[]{new c(c.f7284i, ""), new c(hVar, "GET"), new c(hVar, "POST"), new c(hVar2, "/"), new c(hVar2, "/index.html"), new c(hVar3, "http"), new c(hVar3, "https"), new c(hVar4, "200"), new c(hVar4, "204"), new c(hVar4, "206"), new c(hVar4, "304"), new c(hVar4, "400"), new c(hVar4, "404"), new c(hVar4, "500"), new c("accept-charset", ""), new c("accept-encoding", "gzip, deflate"), new c("accept-language", ""), new c("accept-ranges", ""), new c("accept", ""), new c("access-control-allow-origin", ""), new c("age", ""), new c("allow", ""), new c("authorization", ""), new c("cache-control", ""), new c("content-disposition", ""), new c("content-encoding", ""), new c("content-language", ""), new c("content-length", ""), new c("content-location", ""), new c("content-range", ""), new c("content-type", ""), new c("cookie", ""), new c("date", ""), new c("etag", ""), new c("expect", ""), new c("expires", ""), new c("from", ""), new c("host", ""), new c("if-match", ""), new c("if-modified-since", ""), new c("if-none-match", ""), new c("if-range", ""), new c("if-unmodified-since", ""), new c("last-modified", ""), new c("link", ""), new c("location", ""), new c("max-forwards", ""), new c("proxy-authenticate", ""), new c("proxy-authorization", ""), new c("range", ""), new c("referer", ""), new c("refresh", ""), new c("retry-after", ""), new c("server", ""), new c("set-cookie", ""), new c("strict-transport-security", ""), new c("transfer-encoding", ""), new c("user-agent", ""), new c("vary", ""), new c("via", ""), new c("www-authenticate", "")};
        f7290b = dVar.d();
    }

    private d() {
    }

    private final Map<p6.h, Integer> d() {
        c[] cVarArr = f7289a;
        LinkedHashMap linkedHashMap = new LinkedHashMap(cVarArr.length);
        int length = cVarArr.length;
        for (int i7 = 0; i7 < length; i7++) {
            c[] cVarArr2 = f7289a;
            if (!linkedHashMap.containsKey(cVarArr2[i7].f7287b)) {
                linkedHashMap.put(cVarArr2[i7].f7287b, Integer.valueOf(i7));
            }
        }
        Map<p6.h, Integer> mapUnmodifiableMap = Collections.unmodifiableMap(linkedHashMap);
        kotlin.jvm.internal.l.d(mapUnmodifiableMap, "Collections.unmodifiableMap(result)");
        return mapUnmodifiableMap;
    }

    public final p6.h a(p6.h name) throws IOException {
        kotlin.jvm.internal.l.e(name, "name");
        int iR = name.r();
        for (int i7 = 0; i7 < iR; i7++) {
            byte b7 = (byte) 65;
            byte b8 = (byte) 90;
            byte bD = name.d(i7);
            if (b7 <= bD && b8 >= bD) {
                throw new IOException("PROTOCOL_ERROR response malformed: mixed case name: " + name.u());
            }
        }
        return name;
    }

    public final Map<p6.h, Integer> b() {
        return f7290b;
    }

    public final c[] c() {
        return f7289a;
    }

    /* JADX INFO: compiled from: Hpack.kt */
    public static final class a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        private final List<c> f7292a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        private final p6.g f7293b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public c[] f7294c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        private int f7295d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        public int f7296e;

        /* JADX INFO: renamed from: f, reason: collision with root package name */
        public int f7297f;

        /* JADX INFO: renamed from: g, reason: collision with root package name */
        private final int f7298g;

        /* JADX INFO: renamed from: h, reason: collision with root package name */
        private int f7299h;

        public a(a0 source, int i7, int i8) {
            kotlin.jvm.internal.l.e(source, "source");
            this.f7298g = i7;
            this.f7299h = i8;
            this.f7292a = new ArrayList();
            this.f7293b = o.b(source);
            this.f7294c = new c[8];
            this.f7295d = r2.length - 1;
        }

        private final void a() {
            int i7 = this.f7299h;
            int i8 = this.f7297f;
            if (i7 < i8) {
                if (i7 == 0) {
                    b();
                } else {
                    d(i8 - i7);
                }
            }
        }

        private final void b() {
            h5.k.k(this.f7294c, null, 0, 0, 6, null);
            this.f7295d = this.f7294c.length - 1;
            this.f7296e = 0;
            this.f7297f = 0;
        }

        private final int c(int i7) {
            return this.f7295d + 1 + i7;
        }

        private final int d(int i7) {
            int i8;
            int i9 = 0;
            if (i7 > 0) {
                int length = this.f7294c.length;
                while (true) {
                    length--;
                    i8 = this.f7295d;
                    if (length < i8 || i7 <= 0) {
                        break;
                    }
                    c cVar = this.f7294c[length];
                    kotlin.jvm.internal.l.c(cVar);
                    int i10 = cVar.f7286a;
                    i7 -= i10;
                    this.f7297f -= i10;
                    this.f7296e--;
                    i9++;
                }
                c[] cVarArr = this.f7294c;
                System.arraycopy(cVarArr, i8 + 1, cVarArr, i8 + 1 + i9, this.f7296e);
                this.f7295d += i9;
            }
            return i9;
        }

        private final p6.h f(int i7) throws IOException {
            if (h(i7)) {
                return d.f7291c.c()[i7].f7287b;
            }
            int iC = c(i7 - d.f7291c.c().length);
            if (iC >= 0) {
                c[] cVarArr = this.f7294c;
                if (iC < cVarArr.length) {
                    c cVar = cVarArr[iC];
                    kotlin.jvm.internal.l.c(cVar);
                    return cVar.f7287b;
                }
            }
            throw new IOException("Header index too large " + (i7 + 1));
        }

        private final void g(int i7, c cVar) {
            this.f7292a.add(cVar);
            int i8 = cVar.f7286a;
            if (i7 != -1) {
                c cVar2 = this.f7294c[c(i7)];
                kotlin.jvm.internal.l.c(cVar2);
                i8 -= cVar2.f7286a;
            }
            int i9 = this.f7299h;
            if (i8 > i9) {
                b();
                return;
            }
            int iD = d((this.f7297f + i8) - i9);
            if (i7 == -1) {
                int i10 = this.f7296e + 1;
                c[] cVarArr = this.f7294c;
                if (i10 > cVarArr.length) {
                    c[] cVarArr2 = new c[cVarArr.length * 2];
                    System.arraycopy(cVarArr, 0, cVarArr2, cVarArr.length, cVarArr.length);
                    this.f7295d = this.f7294c.length - 1;
                    this.f7294c = cVarArr2;
                }
                int i11 = this.f7295d;
                this.f7295d = i11 - 1;
                this.f7294c[i11] = cVar;
                this.f7296e++;
            } else {
                this.f7294c[i7 + c(i7) + iD] = cVar;
            }
            this.f7297f += i8;
        }

        private final boolean h(int i7) {
            return i7 >= 0 && i7 <= d.f7291c.c().length - 1;
        }

        private final int i() {
            return d6.b.b(this.f7293b.Y(), 255);
        }

        private final void l(int i7) throws IOException {
            if (h(i7)) {
                this.f7292a.add(d.f7291c.c()[i7]);
                return;
            }
            int iC = c(i7 - d.f7291c.c().length);
            if (iC >= 0) {
                c[] cVarArr = this.f7294c;
                if (iC < cVarArr.length) {
                    List<c> list = this.f7292a;
                    c cVar = cVarArr[iC];
                    kotlin.jvm.internal.l.c(cVar);
                    list.add(cVar);
                    return;
                }
            }
            throw new IOException("Header index too large " + (i7 + 1));
        }

        private final void n(int i7) {
            g(-1, new c(f(i7), j()));
        }

        private final void o() {
            g(-1, new c(d.f7291c.a(j()), j()));
        }

        private final void p(int i7) throws IOException {
            this.f7292a.add(new c(f(i7), j()));
        }

        private final void q() throws IOException {
            this.f7292a.add(new c(d.f7291c.a(j()), j()));
        }

        public final List<c> e() {
            List<c> listJ = x.J(this.f7292a);
            this.f7292a.clear();
            return listJ;
        }

        public final p6.h j() {
            int i7 = i();
            boolean z6 = (i7 & 128) == 128;
            long jM = m(i7, 127);
            if (!z6) {
                return this.f7293b.n(jM);
            }
            p6.e eVar = new p6.e();
            k.f7474d.b(this.f7293b, jM, eVar);
            return eVar.b0();
        }

        public final void k() throws IOException {
            while (!this.f7293b.J()) {
                int iB = d6.b.b(this.f7293b.Y(), 255);
                if (iB == 128) {
                    throw new IOException("index == 0");
                }
                if ((iB & 128) == 128) {
                    l(m(iB, 127) - 1);
                } else if (iB == 64) {
                    o();
                } else if ((iB & 64) == 64) {
                    n(m(iB, 63) - 1);
                } else if ((iB & 32) == 32) {
                    int iM = m(iB, 31);
                    this.f7299h = iM;
                    if (iM < 0 || iM > this.f7298g) {
                        throw new IOException("Invalid dynamic table size update " + this.f7299h);
                    }
                    a();
                } else if (iB == 16 || iB == 0) {
                    q();
                } else {
                    p(m(iB, 15) - 1);
                }
            }
        }

        public final int m(int i7, int i8) {
            int i9 = i7 & i8;
            if (i9 < i8) {
                return i9;
            }
            int i10 = 0;
            while (true) {
                int i11 = i();
                if ((i11 & 128) == 0) {
                    return i8 + (i11 << i10);
                }
                i8 += (i11 & 127) << i10;
                i10 += 7;
            }
        }

        public /* synthetic */ a(a0 a0Var, int i7, int i8, int i9, kotlin.jvm.internal.g gVar) {
            this(a0Var, i7, (i9 & 4) != 0 ? i7 : i8);
        }
    }

    /* JADX INFO: compiled from: Hpack.kt */
    public static final class b {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        private int f7300a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        private boolean f7301b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public int f7302c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public c[] f7303d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        private int f7304e;

        /* JADX INFO: renamed from: f, reason: collision with root package name */
        public int f7305f;

        /* JADX INFO: renamed from: g, reason: collision with root package name */
        public int f7306g;

        /* JADX INFO: renamed from: h, reason: collision with root package name */
        public int f7307h;

        /* JADX INFO: renamed from: i, reason: collision with root package name */
        private final boolean f7308i;

        /* JADX INFO: renamed from: j, reason: collision with root package name */
        private final p6.e f7309j;

        public b(int i7, boolean z6, p6.e out) {
            kotlin.jvm.internal.l.e(out, "out");
            this.f7307h = i7;
            this.f7308i = z6;
            this.f7309j = out;
            this.f7300a = Integer.MAX_VALUE;
            this.f7302c = i7;
            this.f7303d = new c[8];
            this.f7304e = r2.length - 1;
        }

        private final void a() {
            int i7 = this.f7302c;
            int i8 = this.f7306g;
            if (i7 < i8) {
                if (i7 == 0) {
                    b();
                } else {
                    c(i8 - i7);
                }
            }
        }

        private final void b() {
            h5.k.k(this.f7303d, null, 0, 0, 6, null);
            this.f7304e = this.f7303d.length - 1;
            this.f7305f = 0;
            this.f7306g = 0;
        }

        private final int c(int i7) {
            int i8;
            int i9 = 0;
            if (i7 > 0) {
                int length = this.f7303d.length;
                while (true) {
                    length--;
                    i8 = this.f7304e;
                    if (length < i8 || i7 <= 0) {
                        break;
                    }
                    c cVar = this.f7303d[length];
                    kotlin.jvm.internal.l.c(cVar);
                    i7 -= cVar.f7286a;
                    int i10 = this.f7306g;
                    c cVar2 = this.f7303d[length];
                    kotlin.jvm.internal.l.c(cVar2);
                    this.f7306g = i10 - cVar2.f7286a;
                    this.f7305f--;
                    i9++;
                }
                c[] cVarArr = this.f7303d;
                System.arraycopy(cVarArr, i8 + 1, cVarArr, i8 + 1 + i9, this.f7305f);
                c[] cVarArr2 = this.f7303d;
                int i11 = this.f7304e;
                Arrays.fill(cVarArr2, i11 + 1, i11 + 1 + i9, (Object) null);
                this.f7304e += i9;
            }
            return i9;
        }

        private final void d(c cVar) {
            int i7 = cVar.f7286a;
            int i8 = this.f7302c;
            if (i7 > i8) {
                b();
                return;
            }
            c((this.f7306g + i7) - i8);
            int i9 = this.f7305f + 1;
            c[] cVarArr = this.f7303d;
            if (i9 > cVarArr.length) {
                c[] cVarArr2 = new c[cVarArr.length * 2];
                System.arraycopy(cVarArr, 0, cVarArr2, cVarArr.length, cVarArr.length);
                this.f7304e = this.f7303d.length - 1;
                this.f7303d = cVarArr2;
            }
            int i10 = this.f7304e;
            this.f7304e = i10 - 1;
            this.f7303d[i10] = cVar;
            this.f7305f++;
            this.f7306g += i7;
        }

        public final void e(int i7) {
            this.f7307h = i7;
            int iMin = Math.min(i7, 16384);
            int i8 = this.f7302c;
            if (i8 == iMin) {
                return;
            }
            if (iMin < i8) {
                this.f7300a = Math.min(this.f7300a, iMin);
            }
            this.f7301b = true;
            this.f7302c = iMin;
            a();
        }

        public final void f(p6.h data) {
            kotlin.jvm.internal.l.e(data, "data");
            if (this.f7308i) {
                k kVar = k.f7474d;
                if (kVar.d(data) < data.r()) {
                    p6.e eVar = new p6.e();
                    kVar.c(data, eVar);
                    p6.h hVarB0 = eVar.b0();
                    h(hVarB0.r(), 127, 128);
                    this.f7309j.Q(hVarB0);
                    return;
                }
            }
            h(data.r(), 127, 0);
            this.f7309j.Q(data);
        }

        /* JADX WARN: Removed duplicated region for block: B:22:0x007a  */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        public final void g(java.util.List<k6.c> r14) {
            /*
                Method dump skipped, instruction units count: 269
                To view this dump change 'Code comments level' option to 'DEBUG'
            */
            throw new UnsupportedOperationException("Method not decompiled: k6.d.b.g(java.util.List):void");
        }

        public final void h(int i7, int i8, int i9) {
            if (i7 < i8) {
                this.f7309j.M(i7 | i9);
                return;
            }
            this.f7309j.M(i9 | i8);
            int i10 = i7 - i8;
            while (i10 >= 128) {
                this.f7309j.M(128 | (i10 & 127));
                i10 >>>= 7;
            }
            this.f7309j.M(i10);
        }

        public /* synthetic */ b(int i7, boolean z6, p6.e eVar, int i8, kotlin.jvm.internal.g gVar) {
            this((i8 & 1) != 0 ? 4096 : i7, (i8 & 2) != 0 ? true : z6, eVar);
        }
    }
}
