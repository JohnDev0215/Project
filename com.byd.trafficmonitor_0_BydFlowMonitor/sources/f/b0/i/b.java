package f.b0.i;

import g.p;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import okio.ByteString;

/* JADX INFO: compiled from: Hpack.java */
/* JADX INFO: loaded from: classes.dex */
public final class b {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final f.b0.i.a[] f5128a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final Map<ByteString, Integer> f5129b;

    /* JADX INFO: compiled from: Hpack.java */
    public static final class a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final List<f.b0.i.a> f5130a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final g.e f5131b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public final int f5132c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public int f5133d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        public f.b0.i.a[] f5134e;

        /* JADX INFO: renamed from: f, reason: collision with root package name */
        public int f5135f;

        /* JADX INFO: renamed from: g, reason: collision with root package name */
        public int f5136g;

        /* JADX INFO: renamed from: h, reason: collision with root package name */
        public int f5137h;

        public a(int i2, p pVar) {
            this(i2, i2, pVar);
        }

        public final void a() {
            int i2 = this.f5133d;
            int i3 = this.f5137h;
            if (i2 < i3) {
                if (i2 == 0) {
                    b();
                } else {
                    d(i3 - i2);
                }
            }
        }

        public final void b() {
            Arrays.fill(this.f5134e, (Object) null);
            this.f5135f = this.f5134e.length - 1;
            this.f5136g = 0;
            this.f5137h = 0;
        }

        public final int c(int i2) {
            return this.f5135f + 1 + i2;
        }

        public final int d(int i2) {
            int i3;
            int i4 = 0;
            if (i2 > 0) {
                int length = this.f5134e.length;
                while (true) {
                    length--;
                    i3 = this.f5135f;
                    if (length < i3 || i2 <= 0) {
                        break;
                    }
                    f.b0.i.a[] aVarArr = this.f5134e;
                    i2 -= aVarArr[length].f5127i;
                    this.f5137h -= aVarArr[length].f5127i;
                    this.f5136g--;
                    i4++;
                }
                f.b0.i.a[] aVarArr2 = this.f5134e;
                System.arraycopy(aVarArr2, i3 + 1, aVarArr2, i3 + 1 + i4, this.f5136g);
                this.f5135f += i4;
            }
            return i4;
        }

        public List<f.b0.i.a> e() {
            ArrayList arrayList = new ArrayList(this.f5130a);
            this.f5130a.clear();
            return arrayList;
        }

        public final ByteString f(int i2) {
            return h(i2) ? b.f5128a[i2].f5125g : this.f5134e[c(i2 - b.f5128a.length)].f5125g;
        }

        public final void g(int i2, f.b0.i.a aVar) {
            this.f5130a.add(aVar);
            int i3 = aVar.f5127i;
            if (i2 != -1) {
                i3 -= this.f5134e[c(i2)].f5127i;
            }
            int i4 = this.f5133d;
            if (i3 > i4) {
                b();
                return;
            }
            int iD = d((this.f5137h + i3) - i4);
            if (i2 == -1) {
                int i5 = this.f5136g + 1;
                f.b0.i.a[] aVarArr = this.f5134e;
                if (i5 > aVarArr.length) {
                    f.b0.i.a[] aVarArr2 = new f.b0.i.a[aVarArr.length * 2];
                    System.arraycopy(aVarArr, 0, aVarArr2, aVarArr.length, aVarArr.length);
                    this.f5135f = this.f5134e.length - 1;
                    this.f5134e = aVarArr2;
                }
                int i6 = this.f5135f;
                this.f5135f = i6 - 1;
                this.f5134e[i6] = aVar;
                this.f5136g++;
            } else {
                this.f5134e[i2 + c(i2) + iD] = aVar;
            }
            this.f5137h += i3;
        }

        public final boolean h(int i2) {
            return i2 >= 0 && i2 <= b.f5128a.length - 1;
        }

        public final int i() throws IOException {
            return this.f5131b.N() & 255;
        }

        public ByteString j() throws IOException {
            int i2 = i();
            boolean z = (i2 & 128) == 128;
            int iM = m(i2, 127);
            return z ? ByteString.of(i.f().c(this.f5131b.G(iM))) : this.f5131b.n(iM);
        }

        public void k() throws IOException {
            while (!this.f5131b.D()) {
                int iN = this.f5131b.N() & 255;
                if (iN == 128) {
                    throw new IOException("index == 0");
                }
                if ((iN & 128) == 128) {
                    l(m(iN, 127) - 1);
                } else if (iN == 64) {
                    o();
                } else if ((iN & 64) == 64) {
                    n(m(iN, 63) - 1);
                } else if ((iN & 32) == 32) {
                    int iM = m(iN, 31);
                    this.f5133d = iM;
                    if (iM < 0 || iM > this.f5132c) {
                        throw new IOException("Invalid dynamic table size update " + this.f5133d);
                    }
                    a();
                } else if (iN == 16 || iN == 0) {
                    q();
                } else {
                    p(m(iN, 15) - 1);
                }
            }
        }

        public final void l(int i2) throws IOException {
            if (h(i2)) {
                this.f5130a.add(b.f5128a[i2]);
                return;
            }
            int iC = c(i2 - b.f5128a.length);
            if (iC >= 0) {
                f.b0.i.a[] aVarArr = this.f5134e;
                if (iC <= aVarArr.length - 1) {
                    this.f5130a.add(aVarArr[iC]);
                    return;
                }
            }
            throw new IOException("Header index too large " + (i2 + 1));
        }

        public int m(int i2, int i3) throws IOException {
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

        public final void n(int i2) throws IOException {
            g(-1, new f.b0.i.a(f(i2), j()));
        }

        public final void o() throws IOException {
            g(-1, new f.b0.i.a(b.a(j()), j()));
        }

        public final void p(int i2) throws IOException {
            this.f5130a.add(new f.b0.i.a(f(i2), j()));
        }

        public final void q() throws IOException {
            this.f5130a.add(new f.b0.i.a(b.a(j()), j()));
        }

        public a(int i2, int i3, p pVar) {
            this.f5130a = new ArrayList();
            this.f5134e = new f.b0.i.a[8];
            this.f5135f = r0.length - 1;
            this.f5136g = 0;
            this.f5137h = 0;
            this.f5132c = i2;
            this.f5133d = i3;
            this.f5131b = g.j.b(pVar);
        }
    }

    /* JADX INFO: renamed from: f.b0.i.b$b, reason: collision with other inner class name */
    /* JADX INFO: compiled from: Hpack.java */
    public static final class C0102b {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final g.c f5138a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final boolean f5139b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public int f5140c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public boolean f5141d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        public int f5142e;

        /* JADX INFO: renamed from: f, reason: collision with root package name */
        public int f5143f;

        /* JADX INFO: renamed from: g, reason: collision with root package name */
        public f.b0.i.a[] f5144g;

        /* JADX INFO: renamed from: h, reason: collision with root package name */
        public int f5145h;

        /* JADX INFO: renamed from: i, reason: collision with root package name */
        public int f5146i;
        public int j;

        public C0102b(g.c cVar) {
            this(4096, true, cVar);
        }

        public final void a() {
            int i2 = this.f5143f;
            int i3 = this.j;
            if (i2 < i3) {
                if (i2 == 0) {
                    b();
                } else {
                    c(i3 - i2);
                }
            }
        }

        public final void b() {
            Arrays.fill(this.f5144g, (Object) null);
            this.f5145h = this.f5144g.length - 1;
            this.f5146i = 0;
            this.j = 0;
        }

        public final int c(int i2) {
            int i3;
            int i4 = 0;
            if (i2 > 0) {
                int length = this.f5144g.length;
                while (true) {
                    length--;
                    i3 = this.f5145h;
                    if (length < i3 || i2 <= 0) {
                        break;
                    }
                    f.b0.i.a[] aVarArr = this.f5144g;
                    i2 -= aVarArr[length].f5127i;
                    this.j -= aVarArr[length].f5127i;
                    this.f5146i--;
                    i4++;
                }
                f.b0.i.a[] aVarArr2 = this.f5144g;
                System.arraycopy(aVarArr2, i3 + 1, aVarArr2, i3 + 1 + i4, this.f5146i);
                f.b0.i.a[] aVarArr3 = this.f5144g;
                int i5 = this.f5145h;
                Arrays.fill(aVarArr3, i5 + 1, i5 + 1 + i4, (Object) null);
                this.f5145h += i4;
            }
            return i4;
        }

        public final void d(f.b0.i.a aVar) {
            int i2 = aVar.f5127i;
            int i3 = this.f5143f;
            if (i2 > i3) {
                b();
                return;
            }
            c((this.j + i2) - i3);
            int i4 = this.f5146i + 1;
            f.b0.i.a[] aVarArr = this.f5144g;
            if (i4 > aVarArr.length) {
                f.b0.i.a[] aVarArr2 = new f.b0.i.a[aVarArr.length * 2];
                System.arraycopy(aVarArr, 0, aVarArr2, aVarArr.length, aVarArr.length);
                this.f5145h = this.f5144g.length - 1;
                this.f5144g = aVarArr2;
            }
            int i5 = this.f5145h;
            this.f5145h = i5 - 1;
            this.f5144g[i5] = aVar;
            this.f5146i++;
            this.j += i2;
        }

        public void e(int i2) {
            this.f5142e = i2;
            int iMin = Math.min(i2, 16384);
            int i3 = this.f5143f;
            if (i3 == iMin) {
                return;
            }
            if (iMin < i3) {
                this.f5140c = Math.min(this.f5140c, iMin);
            }
            this.f5141d = true;
            this.f5143f = iMin;
            a();
        }

        public void f(ByteString byteString) throws IOException {
            if (!this.f5139b || i.f().e(byteString) >= byteString.size()) {
                h(byteString.size(), 127, 0);
                this.f5138a.h(byteString);
                return;
            }
            g.c cVar = new g.c();
            i.f().d(byteString, cVar);
            ByteString byteStringY = cVar.Y();
            h(byteStringY.size(), 127, 128);
            this.f5138a.h(byteStringY);
        }

        /* JADX WARN: Removed duplicated region for block: B:22:0x0069  */
        /* JADX WARN: Removed duplicated region for block: B:26:0x0070  */
        /* JADX WARN: Removed duplicated region for block: B:37:0x00a7  */
        /* JADX WARN: Removed duplicated region for block: B:38:0x00af  */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        public void g(java.util.List<f.b0.i.a> r14) throws java.io.IOException {
            /*
                Method dump skipped, instruction units count: 235
                To view this dump change 'Code comments level' option to 'DEBUG'
            */
            throw new UnsupportedOperationException("Method not decompiled: f.b0.i.b.C0102b.g(java.util.List):void");
        }

        public void h(int i2, int i3, int i4) {
            if (i2 < i3) {
                this.f5138a.E(i2 | i4);
                return;
            }
            this.f5138a.E(i4 | i3);
            int i5 = i2 - i3;
            while (i5 >= 128) {
                this.f5138a.E(128 | (i5 & 127));
                i5 >>>= 7;
            }
            this.f5138a.E(i5);
        }

        public C0102b(int i2, boolean z, g.c cVar) {
            this.f5140c = Integer.MAX_VALUE;
            this.f5144g = new f.b0.i.a[8];
            this.f5145h = r0.length - 1;
            this.f5146i = 0;
            this.j = 0;
            this.f5142e = i2;
            this.f5143f = i2;
            this.f5139b = z;
            this.f5138a = cVar;
        }
    }

    static {
        ByteString byteString = f.b0.i.a.f5121c;
        ByteString byteString2 = f.b0.i.a.f5122d;
        ByteString byteString3 = f.b0.i.a.f5123e;
        ByteString byteString4 = f.b0.i.a.f5120b;
        f5128a = new f.b0.i.a[]{new f.b0.i.a(f.b0.i.a.f5124f, ""), new f.b0.i.a(byteString, "GET"), new f.b0.i.a(byteString, "POST"), new f.b0.i.a(byteString2, "/"), new f.b0.i.a(byteString2, "/index.html"), new f.b0.i.a(byteString3, "http"), new f.b0.i.a(byteString3, "https"), new f.b0.i.a(byteString4, "200"), new f.b0.i.a(byteString4, "204"), new f.b0.i.a(byteString4, "206"), new f.b0.i.a(byteString4, "304"), new f.b0.i.a(byteString4, "400"), new f.b0.i.a(byteString4, "404"), new f.b0.i.a(byteString4, "500"), new f.b0.i.a("accept-charset", ""), new f.b0.i.a("accept-encoding", "gzip, deflate"), new f.b0.i.a("accept-language", ""), new f.b0.i.a("accept-ranges", ""), new f.b0.i.a("accept", ""), new f.b0.i.a("access-control-allow-origin", ""), new f.b0.i.a("age", ""), new f.b0.i.a("allow", ""), new f.b0.i.a("authorization", ""), new f.b0.i.a("cache-control", ""), new f.b0.i.a("content-disposition", ""), new f.b0.i.a("content-encoding", ""), new f.b0.i.a("content-language", ""), new f.b0.i.a("content-length", ""), new f.b0.i.a("content-location", ""), new f.b0.i.a("content-range", ""), new f.b0.i.a("content-type", ""), new f.b0.i.a("cookie", ""), new f.b0.i.a("date", ""), new f.b0.i.a("etag", ""), new f.b0.i.a("expect", ""), new f.b0.i.a("expires", ""), new f.b0.i.a("from", ""), new f.b0.i.a("host", ""), new f.b0.i.a("if-match", ""), new f.b0.i.a("if-modified-since", ""), new f.b0.i.a("if-none-match", ""), new f.b0.i.a("if-range", ""), new f.b0.i.a("if-unmodified-since", ""), new f.b0.i.a("last-modified", ""), new f.b0.i.a("link", ""), new f.b0.i.a("location", ""), new f.b0.i.a("max-forwards", ""), new f.b0.i.a("proxy-authenticate", ""), new f.b0.i.a("proxy-authorization", ""), new f.b0.i.a("range", ""), new f.b0.i.a("referer", ""), new f.b0.i.a("refresh", ""), new f.b0.i.a("retry-after", ""), new f.b0.i.a("server", ""), new f.b0.i.a("set-cookie", ""), new f.b0.i.a("strict-transport-security", ""), new f.b0.i.a("transfer-encoding", ""), new f.b0.i.a("user-agent", ""), new f.b0.i.a("vary", ""), new f.b0.i.a("via", ""), new f.b0.i.a("www-authenticate", "")};
        f5129b = b();
    }

    public static ByteString a(ByteString byteString) throws IOException {
        int size = byteString.size();
        for (int i2 = 0; i2 < size; i2++) {
            byte b2 = byteString.getByte(i2);
            if (b2 >= 65 && b2 <= 90) {
                throw new IOException("PROTOCOL_ERROR response malformed: mixed case name: " + byteString.utf8());
            }
        }
        return byteString;
    }

    public static Map<ByteString, Integer> b() {
        LinkedHashMap linkedHashMap = new LinkedHashMap(f5128a.length);
        int i2 = 0;
        while (true) {
            f.b0.i.a[] aVarArr = f5128a;
            if (i2 >= aVarArr.length) {
                return Collections.unmodifiableMap(linkedHashMap);
            }
            if (!linkedHashMap.containsKey(aVarArr[i2].f5125g)) {
                linkedHashMap.put(aVarArr[i2].f5125g, Integer.valueOf(i2));
            }
            i2++;
        }
    }
}
