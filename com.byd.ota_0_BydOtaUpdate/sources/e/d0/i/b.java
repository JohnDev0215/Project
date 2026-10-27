package e.d0.i;

import f.q;
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
    public static final e.d0.i.a[] f3436a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final Map<ByteString, Integer> f3437b;

    /* JADX INFO: compiled from: Hpack.java */
    public static final class a {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final List<e.d0.i.a> f3438a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final f.e f3439b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public final int f3440c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public int f3441d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        public e.d0.i.a[] f3442e;

        /* JADX INFO: renamed from: f, reason: collision with root package name */
        public int f3443f;

        /* JADX INFO: renamed from: g, reason: collision with root package name */
        public int f3444g;

        /* JADX INFO: renamed from: h, reason: collision with root package name */
        public int f3445h;

        public a(int i2, q qVar) {
            this(i2, i2, qVar);
        }

        public final void a() {
            int i2 = this.f3441d;
            int i3 = this.f3445h;
            if (i2 < i3) {
                if (i2 == 0) {
                    b();
                } else {
                    d(i3 - i2);
                }
            }
        }

        public final void b() {
            Arrays.fill(this.f3442e, (Object) null);
            this.f3443f = this.f3442e.length - 1;
            this.f3444g = 0;
            this.f3445h = 0;
        }

        public final int c(int i2) {
            return this.f3443f + 1 + i2;
        }

        public final int d(int i2) {
            int i3;
            int i4 = 0;
            if (i2 > 0) {
                int length = this.f3442e.length;
                while (true) {
                    length--;
                    i3 = this.f3443f;
                    if (length < i3 || i2 <= 0) {
                        break;
                    }
                    e.d0.i.a[] aVarArr = this.f3442e;
                    i2 -= aVarArr[length].f3435i;
                    this.f3445h -= aVarArr[length].f3435i;
                    this.f3444g--;
                    i4++;
                }
                e.d0.i.a[] aVarArr2 = this.f3442e;
                System.arraycopy(aVarArr2, i3 + 1, aVarArr2, i3 + 1 + i4, this.f3444g);
                this.f3443f += i4;
            }
            return i4;
        }

        public List<e.d0.i.a> e() {
            ArrayList arrayList = new ArrayList(this.f3438a);
            this.f3438a.clear();
            return arrayList;
        }

        public final ByteString f(int i2) {
            return h(i2) ? b.f3436a[i2].f3433g : this.f3442e[c(i2 - b.f3436a.length)].f3433g;
        }

        public final void g(int i2, e.d0.i.a aVar) {
            this.f3438a.add(aVar);
            int i3 = aVar.f3435i;
            if (i2 != -1) {
                i3 -= this.f3442e[c(i2)].f3435i;
            }
            int i4 = this.f3441d;
            if (i3 > i4) {
                b();
                return;
            }
            int iD = d((this.f3445h + i3) - i4);
            if (i2 == -1) {
                int i5 = this.f3444g + 1;
                e.d0.i.a[] aVarArr = this.f3442e;
                if (i5 > aVarArr.length) {
                    e.d0.i.a[] aVarArr2 = new e.d0.i.a[aVarArr.length * 2];
                    System.arraycopy(aVarArr, 0, aVarArr2, aVarArr.length, aVarArr.length);
                    this.f3443f = this.f3442e.length - 1;
                    this.f3442e = aVarArr2;
                }
                int i6 = this.f3443f;
                this.f3443f = i6 - 1;
                this.f3442e[i6] = aVar;
                this.f3444g++;
            } else {
                this.f3442e[i2 + c(i2) + iD] = aVar;
            }
            this.f3445h += i3;
        }

        public final boolean h(int i2) {
            return i2 >= 0 && i2 <= b.f3436a.length - 1;
        }

        public final int i() throws IOException {
            return this.f3439b.readByte() & 255;
        }

        public ByteString j() throws IOException {
            int i2 = i();
            boolean z = (i2 & 128) == 128;
            int iM = m(i2, 127);
            return z ? ByteString.of(i.f().c(this.f3439b.C(iM))) : this.f3439b.o(iM);
        }

        public void k() throws IOException {
            while (!this.f3439b.z()) {
                int i2 = this.f3439b.readByte() & 255;
                if (i2 == 128) {
                    throw new IOException("index == 0");
                }
                if ((i2 & 128) == 128) {
                    l(m(i2, 127) - 1);
                } else if (i2 == 64) {
                    o();
                } else if ((i2 & 64) == 64) {
                    n(m(i2, 63) - 1);
                } else if ((i2 & 32) == 32) {
                    int iM = m(i2, 31);
                    this.f3441d = iM;
                    if (iM < 0 || iM > this.f3440c) {
                        throw new IOException("Invalid dynamic table size update " + this.f3441d);
                    }
                    a();
                } else if (i2 == 16 || i2 == 0) {
                    q();
                } else {
                    p(m(i2, 15) - 1);
                }
            }
        }

        public final void l(int i2) throws IOException {
            if (h(i2)) {
                this.f3438a.add(b.f3436a[i2]);
                return;
            }
            int iC = c(i2 - b.f3436a.length);
            if (iC >= 0) {
                e.d0.i.a[] aVarArr = this.f3442e;
                if (iC <= aVarArr.length - 1) {
                    this.f3438a.add(aVarArr[iC]);
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
            g(-1, new e.d0.i.a(f(i2), j()));
        }

        public final void o() throws IOException {
            g(-1, new e.d0.i.a(b.a(j()), j()));
        }

        public final void p(int i2) throws IOException {
            this.f3438a.add(new e.d0.i.a(f(i2), j()));
        }

        public final void q() throws IOException {
            this.f3438a.add(new e.d0.i.a(b.a(j()), j()));
        }

        public a(int i2, int i3, q qVar) {
            this.f3438a = new ArrayList();
            this.f3442e = new e.d0.i.a[8];
            this.f3443f = r0.length - 1;
            this.f3444g = 0;
            this.f3445h = 0;
            this.f3440c = i2;
            this.f3441d = i3;
            this.f3439b = f.k.b(qVar);
        }
    }

    /* JADX INFO: renamed from: e.d0.i.b$b, reason: collision with other inner class name */
    /* JADX INFO: compiled from: Hpack.java */
    public static final class C0082b {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final f.c f3446a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final boolean f3447b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public int f3448c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public boolean f3449d;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        public int f3450e;

        /* JADX INFO: renamed from: f, reason: collision with root package name */
        public int f3451f;

        /* JADX INFO: renamed from: g, reason: collision with root package name */
        public e.d0.i.a[] f3452g;

        /* JADX INFO: renamed from: h, reason: collision with root package name */
        public int f3453h;

        /* JADX INFO: renamed from: i, reason: collision with root package name */
        public int f3454i;
        public int j;

        public C0082b(f.c cVar) {
            this(4096, true, cVar);
        }

        public final void a() {
            int i2 = this.f3451f;
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
            Arrays.fill(this.f3452g, (Object) null);
            this.f3453h = this.f3452g.length - 1;
            this.f3454i = 0;
            this.j = 0;
        }

        public final int c(int i2) {
            int i3;
            int i4 = 0;
            if (i2 > 0) {
                int length = this.f3452g.length;
                while (true) {
                    length--;
                    i3 = this.f3453h;
                    if (length < i3 || i2 <= 0) {
                        break;
                    }
                    e.d0.i.a[] aVarArr = this.f3452g;
                    i2 -= aVarArr[length].f3435i;
                    this.j -= aVarArr[length].f3435i;
                    this.f3454i--;
                    i4++;
                }
                e.d0.i.a[] aVarArr2 = this.f3452g;
                System.arraycopy(aVarArr2, i3 + 1, aVarArr2, i3 + 1 + i4, this.f3454i);
                e.d0.i.a[] aVarArr3 = this.f3452g;
                int i5 = this.f3453h;
                Arrays.fill(aVarArr3, i5 + 1, i5 + 1 + i4, (Object) null);
                this.f3453h += i4;
            }
            return i4;
        }

        public final void d(e.d0.i.a aVar) {
            int i2 = aVar.f3435i;
            int i3 = this.f3451f;
            if (i2 > i3) {
                b();
                return;
            }
            c((this.j + i2) - i3);
            int i4 = this.f3454i + 1;
            e.d0.i.a[] aVarArr = this.f3452g;
            if (i4 > aVarArr.length) {
                e.d0.i.a[] aVarArr2 = new e.d0.i.a[aVarArr.length * 2];
                System.arraycopy(aVarArr, 0, aVarArr2, aVarArr.length, aVarArr.length);
                this.f3453h = this.f3452g.length - 1;
                this.f3452g = aVarArr2;
            }
            int i5 = this.f3453h;
            this.f3453h = i5 - 1;
            this.f3452g[i5] = aVar;
            this.f3454i++;
            this.j += i2;
        }

        public void e(int i2) {
            this.f3450e = i2;
            int iMin = Math.min(i2, 16384);
            int i3 = this.f3451f;
            if (i3 == iMin) {
                return;
            }
            if (iMin < i3) {
                this.f3448c = Math.min(this.f3448c, iMin);
            }
            this.f3449d = true;
            this.f3451f = iMin;
            a();
        }

        public void f(ByteString byteString) throws IOException {
            if (!this.f3447b || i.f().e(byteString) >= byteString.size()) {
                h(byteString.size(), 127, 0);
                this.f3446a.i(byteString);
                return;
            }
            f.c cVar = new f.c();
            i.f().d(byteString, cVar);
            ByteString byteStringR = cVar.R();
            h(byteStringR.size(), 127, 128);
            this.f3446a.i(byteStringR);
        }

        /* JADX WARN: Removed duplicated region for block: B:22:0x0069  */
        /* JADX WARN: Removed duplicated region for block: B:26:0x0070  */
        /* JADX WARN: Removed duplicated region for block: B:37:0x00a7  */
        /* JADX WARN: Removed duplicated region for block: B:38:0x00af  */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        public void g(java.util.List<e.d0.i.a> r14) throws java.io.IOException {
            /*
                Method dump skipped, instruction units count: 235
                To view this dump change 'Code comments level' option to 'DEBUG'
            */
            throw new UnsupportedOperationException("Method not decompiled: e.d0.i.b.C0082b.g(java.util.List):void");
        }

        public void h(int i2, int i3, int i4) {
            if (i2 < i3) {
                this.f3446a.A(i2 | i4);
                return;
            }
            this.f3446a.A(i4 | i3);
            int i5 = i2 - i3;
            while (i5 >= 128) {
                this.f3446a.A(128 | (i5 & 127));
                i5 >>>= 7;
            }
            this.f3446a.A(i5);
        }

        public C0082b(int i2, boolean z, f.c cVar) {
            this.f3448c = Integer.MAX_VALUE;
            this.f3452g = new e.d0.i.a[8];
            this.f3453h = r0.length - 1;
            this.f3454i = 0;
            this.j = 0;
            this.f3450e = i2;
            this.f3451f = i2;
            this.f3447b = z;
            this.f3446a = cVar;
        }
    }

    static {
        ByteString byteString = e.d0.i.a.f3429c;
        ByteString byteString2 = e.d0.i.a.f3430d;
        ByteString byteString3 = e.d0.i.a.f3431e;
        ByteString byteString4 = e.d0.i.a.f3428b;
        f3436a = new e.d0.i.a[]{new e.d0.i.a(e.d0.i.a.f3432f, ""), new e.d0.i.a(byteString, "GET"), new e.d0.i.a(byteString, "POST"), new e.d0.i.a(byteString2, "/"), new e.d0.i.a(byteString2, "/index.html"), new e.d0.i.a(byteString3, "http"), new e.d0.i.a(byteString3, "https"), new e.d0.i.a(byteString4, "200"), new e.d0.i.a(byteString4, "204"), new e.d0.i.a(byteString4, "206"), new e.d0.i.a(byteString4, "304"), new e.d0.i.a(byteString4, "400"), new e.d0.i.a(byteString4, "404"), new e.d0.i.a(byteString4, "500"), new e.d0.i.a("accept-charset", ""), new e.d0.i.a("accept-encoding", "gzip, deflate"), new e.d0.i.a("accept-language", ""), new e.d0.i.a("accept-ranges", ""), new e.d0.i.a("accept", ""), new e.d0.i.a("access-control-allow-origin", ""), new e.d0.i.a("age", ""), new e.d0.i.a("allow", ""), new e.d0.i.a("authorization", ""), new e.d0.i.a("cache-control", ""), new e.d0.i.a("content-disposition", ""), new e.d0.i.a("content-encoding", ""), new e.d0.i.a("content-language", ""), new e.d0.i.a("content-length", ""), new e.d0.i.a("content-location", ""), new e.d0.i.a("content-range", ""), new e.d0.i.a("content-type", ""), new e.d0.i.a("cookie", ""), new e.d0.i.a("date", ""), new e.d0.i.a("etag", ""), new e.d0.i.a("expect", ""), new e.d0.i.a("expires", ""), new e.d0.i.a("from", ""), new e.d0.i.a("host", ""), new e.d0.i.a("if-match", ""), new e.d0.i.a("if-modified-since", ""), new e.d0.i.a("if-none-match", ""), new e.d0.i.a("if-range", ""), new e.d0.i.a("if-unmodified-since", ""), new e.d0.i.a("last-modified", ""), new e.d0.i.a("link", ""), new e.d0.i.a("location", ""), new e.d0.i.a("max-forwards", ""), new e.d0.i.a("proxy-authenticate", ""), new e.d0.i.a("proxy-authorization", ""), new e.d0.i.a("range", ""), new e.d0.i.a("referer", ""), new e.d0.i.a("refresh", ""), new e.d0.i.a("retry-after", ""), new e.d0.i.a("server", ""), new e.d0.i.a("set-cookie", ""), new e.d0.i.a("strict-transport-security", ""), new e.d0.i.a("transfer-encoding", ""), new e.d0.i.a("user-agent", ""), new e.d0.i.a("vary", ""), new e.d0.i.a("via", ""), new e.d0.i.a("www-authenticate", "")};
        f3437b = b();
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
        LinkedHashMap linkedHashMap = new LinkedHashMap(f3436a.length);
        int i2 = 0;
        while (true) {
            e.d0.i.a[] aVarArr = f3436a;
            if (i2 >= aVarArr.length) {
                return Collections.unmodifiableMap(linkedHashMap);
            }
            if (!linkedHashMap.containsKey(aVarArr[i2].f3433g)) {
                linkedHashMap.put(aVarArr[i2].f3433g, Integer.valueOf(i2));
            }
            i2++;
        }
    }
}
