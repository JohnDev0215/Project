package f.k0.k;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import okio.Buffer;
import okio.BufferedSource;
import okio.ByteString;
import okio.Okio;
import okio.Source;

/* JADX INFO: compiled from: Hpack.java */
/* JADX INFO: loaded from: classes.dex */
final class d {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    static final c[] f5290a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    static final Map<ByteString, Integer> f5291b;

    /* JADX INFO: compiled from: Hpack.java */
    static final class a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        private final List<c> f5292a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        private final BufferedSource f5293b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        private final int f5294c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        private int f5295d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        c[] f5296e;

        /* JADX INFO: renamed from: f, reason: collision with root package name */
        int f5297f;

        /* JADX INFO: renamed from: g, reason: collision with root package name */
        int f5298g;

        /* JADX INFO: renamed from: h, reason: collision with root package name */
        int f5299h;

        a(int i2, Source source) {
            this(i2, i2, source);
        }

        private void a() {
            int i2 = this.f5295d;
            int i3 = this.f5299h;
            if (i2 < i3) {
                if (i2 == 0) {
                    b();
                } else {
                    d(i3 - i2);
                }
            }
        }

        private void b() {
            Arrays.fill(this.f5296e, (Object) null);
            this.f5297f = this.f5296e.length - 1;
            this.f5298g = 0;
            this.f5299h = 0;
        }

        private int c(int i2) {
            return this.f5297f + 1 + i2;
        }

        private int d(int i2) {
            int i3;
            int i4 = 0;
            if (i2 > 0) {
                int length = this.f5296e.length;
                while (true) {
                    length--;
                    i3 = this.f5297f;
                    if (length < i3 || i2 <= 0) {
                        break;
                    }
                    c[] cVarArr = this.f5296e;
                    i2 -= cVarArr[length].f5289i;
                    this.f5299h -= cVarArr[length].f5289i;
                    this.f5298g--;
                    i4++;
                }
                c[] cVarArr2 = this.f5296e;
                System.arraycopy(cVarArr2, i3 + 1, cVarArr2, i3 + 1 + i4, this.f5298g);
                this.f5297f += i4;
            }
            return i4;
        }

        private ByteString f(int i2) throws IOException {
            if (h(i2)) {
                return d.f5290a[i2].f5287g;
            }
            int iC = c(i2 - d.f5290a.length);
            if (iC >= 0) {
                c[] cVarArr = this.f5296e;
                if (iC < cVarArr.length) {
                    return cVarArr[iC].f5287g;
                }
            }
            throw new IOException("Header index too large " + (i2 + 1));
        }

        private void g(int i2, c cVar) {
            this.f5292a.add(cVar);
            int i3 = cVar.f5289i;
            if (i2 != -1) {
                i3 -= this.f5296e[c(i2)].f5289i;
            }
            int i4 = this.f5295d;
            if (i3 > i4) {
                b();
                return;
            }
            int iD = d((this.f5299h + i3) - i4);
            if (i2 == -1) {
                int i5 = this.f5298g + 1;
                c[] cVarArr = this.f5296e;
                if (i5 > cVarArr.length) {
                    c[] cVarArr2 = new c[cVarArr.length * 2];
                    System.arraycopy(cVarArr, 0, cVarArr2, cVarArr.length, cVarArr.length);
                    this.f5297f = this.f5296e.length - 1;
                    this.f5296e = cVarArr2;
                }
                int i6 = this.f5297f;
                this.f5297f = i6 - 1;
                this.f5296e[i6] = cVar;
                this.f5298g++;
            } else {
                this.f5296e[i2 + c(i2) + iD] = cVar;
            }
            this.f5299h += i3;
        }

        private boolean h(int i2) {
            return i2 >= 0 && i2 <= d.f5290a.length - 1;
        }

        private int i() {
            return this.f5293b.P() & 255;
        }

        private void l(int i2) throws IOException {
            if (h(i2)) {
                this.f5292a.add(d.f5290a[i2]);
                return;
            }
            int iC = c(i2 - d.f5290a.length);
            if (iC >= 0) {
                c[] cVarArr = this.f5296e;
                if (iC < cVarArr.length) {
                    this.f5292a.add(cVarArr[iC]);
                    return;
                }
            }
            throw new IOException("Header index too large " + (i2 + 1));
        }

        private void n(int i2) {
            g(-1, new c(f(i2), j()));
        }

        private void o() {
            g(-1, new c(d.a(j()), j()));
        }

        private void p(int i2) throws IOException {
            this.f5292a.add(new c(f(i2), j()));
        }

        private void q() throws IOException {
            this.f5292a.add(new c(d.a(j()), j()));
        }

        public List<c> e() {
            ArrayList arrayList = new ArrayList(this.f5292a);
            this.f5292a.clear();
            return arrayList;
        }

        ByteString j() {
            int i2 = i();
            boolean z = (i2 & 128) == 128;
            int iM = m(i2, 127);
            return z ? ByteString.m(k.f().c(this.f5293b.L(iM))) : this.f5293b.q(iM);
        }

        void k() throws IOException {
            while (!this.f5293b.I()) {
                int iP = this.f5293b.P() & 255;
                if (iP == 128) {
                    throw new IOException("index == 0");
                }
                if ((iP & 128) == 128) {
                    l(m(iP, 127) - 1);
                } else if (iP == 64) {
                    o();
                } else if ((iP & 64) == 64) {
                    n(m(iP, 63) - 1);
                } else if ((iP & 32) == 32) {
                    int iM = m(iP, 31);
                    this.f5295d = iM;
                    if (iM < 0 || iM > this.f5294c) {
                        throw new IOException("Invalid dynamic table size update " + this.f5295d);
                    }
                    a();
                } else if (iP == 16 || iP == 0) {
                    q();
                } else {
                    p(m(iP, 15) - 1);
                }
            }
        }

        int m(int i2, int i3) {
            int i4 = i2 & i3;
            if (i4 < i3) {
                return i4;
            }
            int i5 = 0;
            while (true) {
                int i6 = i();
                if ((i6 & 128) == 0) {
                    return i3 + (i6 << i5);
                }
                i3 += (i6 & 127) << i5;
                i5 += 7;
            }
        }

        a(int i2, int i3, Source source) {
            this.f5292a = new ArrayList();
            this.f5296e = new c[8];
            this.f5297f = r0.length - 1;
            this.f5298g = 0;
            this.f5299h = 0;
            this.f5294c = i2;
            this.f5295d = i3;
            this.f5293b = Okio.c(source);
        }
    }

    /* JADX INFO: compiled from: Hpack.java */
    static final class b {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        private final Buffer f5300a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        private final boolean f5301b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        private int f5302c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        private boolean f5303d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        int f5304e;

        /* JADX INFO: renamed from: f, reason: collision with root package name */
        int f5305f;

        /* JADX INFO: renamed from: g, reason: collision with root package name */
        c[] f5306g;

        /* JADX INFO: renamed from: h, reason: collision with root package name */
        int f5307h;

        /* JADX INFO: renamed from: i, reason: collision with root package name */
        int f5308i;
        int j;

        b(Buffer buffer) {
            this(4096, true, buffer);
        }

        private void a() {
            int i2 = this.f5305f;
            int i3 = this.j;
            if (i2 < i3) {
                if (i2 == 0) {
                    b();
                } else {
                    c(i3 - i2);
                }
            }
        }

        private void b() {
            Arrays.fill(this.f5306g, (Object) null);
            this.f5307h = this.f5306g.length - 1;
            this.f5308i = 0;
            this.j = 0;
        }

        private int c(int i2) {
            int i3;
            int i4 = 0;
            if (i2 > 0) {
                int length = this.f5306g.length;
                while (true) {
                    length--;
                    i3 = this.f5307h;
                    if (length < i3 || i2 <= 0) {
                        break;
                    }
                    c[] cVarArr = this.f5306g;
                    i2 -= cVarArr[length].f5289i;
                    this.j -= cVarArr[length].f5289i;
                    this.f5308i--;
                    i4++;
                }
                c[] cVarArr2 = this.f5306g;
                System.arraycopy(cVarArr2, i3 + 1, cVarArr2, i3 + 1 + i4, this.f5308i);
                c[] cVarArr3 = this.f5306g;
                int i5 = this.f5307h;
                Arrays.fill(cVarArr3, i5 + 1, i5 + 1 + i4, (Object) null);
                this.f5307h += i4;
            }
            return i4;
        }

        private void d(c cVar) {
            int i2 = cVar.f5289i;
            int i3 = this.f5305f;
            if (i2 > i3) {
                b();
                return;
            }
            c((this.j + i2) - i3);
            int i4 = this.f5308i + 1;
            c[] cVarArr = this.f5306g;
            if (i4 > cVarArr.length) {
                c[] cVarArr2 = new c[cVarArr.length * 2];
                System.arraycopy(cVarArr, 0, cVarArr2, cVarArr.length, cVarArr.length);
                this.f5307h = this.f5306g.length - 1;
                this.f5306g = cVarArr2;
            }
            int i5 = this.f5307h;
            this.f5307h = i5 - 1;
            this.f5306g[i5] = cVar;
            this.f5308i++;
            this.j += i2;
        }

        void e(int i2) {
            this.f5304e = i2;
            int iMin = Math.min(i2, 16384);
            int i3 = this.f5305f;
            if (i3 == iMin) {
                return;
            }
            if (iMin < i3) {
                this.f5302c = Math.min(this.f5302c, iMin);
            }
            this.f5303d = true;
            this.f5305f = iMin;
            a();
        }

        void f(ByteString byteString) {
            if (!this.f5301b || k.f().e(byteString) >= byteString.s()) {
                h(byteString.s(), 127, 0);
                this.f5300a.i(byteString);
                return;
            }
            Buffer buffer = new Buffer();
            k.f().d(byteString, buffer);
            ByteString byteStringA0 = buffer.a0();
            h(byteStringA0.s(), 127, 128);
            this.f5300a.i(byteStringA0);
        }

        /* JADX WARN: Removed duplicated region for block: B:22:0x0069  */
        /* JADX WARN: Removed duplicated region for block: B:26:0x0070  */
        /* JADX WARN: Removed duplicated region for block: B:37:0x00a7  */
        /* JADX WARN: Removed duplicated region for block: B:38:0x00af  */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        void g(java.util.List<f.k0.k.c> r14) {
            /*
                Method dump skipped, instruction units count: 235
                To view this dump change 'Code comments level' option to 'DEBUG'
            */
            throw new UnsupportedOperationException("Method not decompiled: f.k0.k.d.b.g(java.util.List):void");
        }

        void h(int i2, int i3, int i4) {
            if (i2 < i3) {
                this.f5300a.J(i2 | i4);
                return;
            }
            this.f5300a.J(i4 | i3);
            int i5 = i2 - i3;
            while (i5 >= 128) {
                this.f5300a.J(128 | (i5 & 127));
                i5 >>>= 7;
            }
            this.f5300a.J(i5);
        }

        b(int i2, boolean z, Buffer buffer) {
            this.f5302c = Integer.MAX_VALUE;
            this.f5306g = new c[8];
            this.f5307h = r0.length - 1;
            this.f5308i = 0;
            this.j = 0;
            this.f5304e = i2;
            this.f5305f = i2;
            this.f5301b = z;
            this.f5300a = buffer;
        }
    }

    static {
        ByteString byteString = c.f5283c;
        ByteString byteString2 = c.f5284d;
        ByteString byteString3 = c.f5285e;
        ByteString byteString4 = c.f5282b;
        f5290a = new c[]{new c(c.f5286f, ""), new c(byteString, "GET"), new c(byteString, "POST"), new c(byteString2, "/"), new c(byteString2, "/index.html"), new c(byteString3, "http"), new c(byteString3, "https"), new c(byteString4, "200"), new c(byteString4, "204"), new c(byteString4, "206"), new c(byteString4, "304"), new c(byteString4, "400"), new c(byteString4, "404"), new c(byteString4, "500"), new c("accept-charset", ""), new c("accept-encoding", "gzip, deflate"), new c("accept-language", ""), new c("accept-ranges", ""), new c("accept", ""), new c("access-control-allow-origin", ""), new c("age", ""), new c("allow", ""), new c("authorization", ""), new c("cache-control", ""), new c("content-disposition", ""), new c("content-encoding", ""), new c("content-language", ""), new c("content-length", ""), new c("content-location", ""), new c("content-range", ""), new c("content-type", ""), new c("cookie", ""), new c("date", ""), new c("etag", ""), new c("expect", ""), new c("expires", ""), new c("from", ""), new c("host", ""), new c("if-match", ""), new c("if-modified-since", ""), new c("if-none-match", ""), new c("if-range", ""), new c("if-unmodified-since", ""), new c("last-modified", ""), new c("link", ""), new c("location", ""), new c("max-forwards", ""), new c("proxy-authenticate", ""), new c("proxy-authorization", ""), new c("range", ""), new c("referer", ""), new c("refresh", ""), new c("retry-after", ""), new c("server", ""), new c("set-cookie", ""), new c("strict-transport-security", ""), new c("transfer-encoding", ""), new c("user-agent", ""), new c("vary", ""), new c("via", ""), new c("www-authenticate", "")};
        f5291b = b();
    }

    static ByteString a(ByteString byteString) throws IOException {
        int iS = byteString.s();
        for (int i2 = 0; i2 < iS; i2++) {
            byte bJ = byteString.j(i2);
            if (bJ >= 65 && bJ <= 90) {
                throw new IOException("PROTOCOL_ERROR response malformed: mixed case name: " + byteString.x());
            }
        }
        return byteString;
    }

    private static Map<ByteString, Integer> b() {
        LinkedHashMap linkedHashMap = new LinkedHashMap(f5290a.length);
        int i2 = 0;
        while (true) {
            c[] cVarArr = f5290a;
            if (i2 >= cVarArr.length) {
                return Collections.unmodifiableMap(linkedHashMap);
            }
            if (!linkedHashMap.containsKey(cVarArr[i2].f5287g)) {
                linkedHashMap.put(cVarArr[i2].f5287g, Integer.valueOf(i2));
            }
            i2++;
        }
    }
}
