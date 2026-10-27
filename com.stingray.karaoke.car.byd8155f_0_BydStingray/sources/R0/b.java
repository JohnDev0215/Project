package R0;

import A7.m;
import C8.d;
import K.L0;
import android.hardware.bydauto.multimedia.BYDAutoMultimediaDevice;
import j9.p;
import j9.w;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashSet;
import n7.AbstractC1996k;
import r9.AbstractC2312b;
import r9.C2319i;
import r9.C2322l;
import r9.D;

/* JADX INFO: loaded from: classes.dex */
public final class b {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final /* synthetic */ int f7344a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public int f7345b;
    public int c;
    public int d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public int f7346e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final Object f7347f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public final Object f7348g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public Object f7349h;

    public b(int i7) {
        this.f7344a = 0;
        this.f7347f = new d(11);
        if (i7 <= 0) {
            throw new IllegalArgumentException("maxSize <= 0");
        }
        this.c = i7;
        this.f7348g = new HashMap(0, 0.75f);
        this.f7349h = new LinkedHashSet();
    }

    public int a(int i7) {
        int i10;
        int i11 = 0;
        if (i7 > 0) {
            int length = ((j9.b[]) this.f7349h).length;
            while (true) {
                length--;
                i10 = this.c;
                if (length < i10 || i7 <= 0) {
                    break;
                }
                j9.b bVar = ((j9.b[]) this.f7349h)[length];
                m.c(bVar);
                int i12 = bVar.c;
                i7 -= i12;
                this.f7346e -= i12;
                this.d--;
                i11++;
            }
            j9.b[] bVarArr = (j9.b[]) this.f7349h;
            System.arraycopy(bVarArr, i10 + 1, bVarArr, i10 + 1 + i11, this.d);
            this.c += i11;
        }
        return i11;
    }

    public Object b(Object obj) {
        synchronized (((d) this.f7347f)) {
            Object obj2 = ((HashMap) this.f7348g).get(obj);
            if (obj2 == null) {
                this.f7346e++;
                return null;
            }
            ((LinkedHashSet) this.f7349h).remove(obj);
            ((LinkedHashSet) this.f7349h).add(obj);
            this.d++;
            return obj2;
        }
    }

    public C2322l c(int i7) throws IOException {
        if (i7 >= 0) {
            j9.b[] bVarArr = j9.d.f17516a;
            if (i7 <= bVarArr.length - 1) {
                return bVarArr[i7].f17507a;
            }
        }
        int length = this.c + 1 + (i7 - j9.d.f17516a.length);
        if (length >= 0) {
            j9.b[] bVarArr2 = (j9.b[]) this.f7349h;
            if (length < bVarArr2.length) {
                j9.b bVar = bVarArr2[length];
                m.c(bVar);
                return bVar.f17507a;
            }
        }
        throw new IOException("Header index too large " + (i7 + 1));
    }

    public void d(j9.b bVar) {
        ((ArrayList) this.f7347f).add(bVar);
        int i7 = this.f7345b;
        int i10 = bVar.c;
        if (i10 > i7) {
            j9.b[] bVarArr = (j9.b[]) this.f7349h;
            AbstractC1996k.J(bVarArr, null, 0, bVarArr.length);
            this.c = ((j9.b[]) this.f7349h).length - 1;
            this.d = 0;
            this.f7346e = 0;
            return;
        }
        a((this.f7346e + i10) - i7);
        int i11 = this.d + 1;
        j9.b[] bVarArr2 = (j9.b[]) this.f7349h;
        if (i11 > bVarArr2.length) {
            j9.b[] bVarArr3 = new j9.b[bVarArr2.length * 2];
            System.arraycopy(bVarArr2, 0, bVarArr3, bVarArr2.length, bVarArr2.length);
            this.c = ((j9.b[]) this.f7349h).length - 1;
            this.f7349h = bVarArr3;
        }
        int i12 = this.c;
        this.c = i12 - 1;
        ((j9.b[]) this.f7349h)[i12] = bVar;
        this.d++;
        this.f7346e += i10;
    }

    /* JADX WARN: Code restructure failed: missing block: B:47:0x00d6, code lost:
    
        throw new java.lang.IllegalStateException("map/keySet size inconsistency");
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public java.lang.Object e(java.lang.Object r6, java.lang.Object r7) {
        /*
            Method dump skipped, instruction units count: 220
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: R0.b.e(java.lang.Object, java.lang.Object):java.lang.Object");
    }

    public C2322l f() {
        int i7;
        D d = (D) this.f7348g;
        byte bK = d.k();
        byte[] bArr = d9.b.f13645a;
        int i10 = bK & 255;
        int i11 = 0;
        boolean z6 = (bK & 128) == 128;
        long jG = g(i10, 127);
        if (!z6) {
            return d.m(jG);
        }
        C2319i c2319i = new C2319i();
        int[] iArr = w.f17595a;
        m.f(d, BYDAutoMultimediaDevice.PARAM_SOURCE);
        L0 l02 = w.c;
        L0 l03 = l02;
        int i12 = 0;
        for (long j3 = 0; j3 < jG; j3++) {
            byte bK2 = d.k();
            byte[] bArr2 = d9.b.f13645a;
            i11 = (i11 << 8) | (bK2 & 255);
            i12 += 8;
            while (i12 >= 8) {
                L0[] l0Arr = (L0[]) l03.o;
                m.c(l0Arr);
                l03 = l0Arr[(i11 >>> (i12 - 8)) & 255];
                m.c(l03);
                if (((L0[]) l03.o) == null) {
                    c2319i.X(l03.f3180m);
                    i12 -= l03.f3181n;
                    l03 = l02;
                } else {
                    i12 -= 8;
                }
            }
        }
        while (i12 > 0) {
            L0[] l0Arr2 = (L0[]) l03.o;
            m.c(l0Arr2);
            L0 l04 = l0Arr2[(i11 << (8 - i12)) & 255];
            m.c(l04);
            if (((L0[]) l04.o) != null || (i7 = l04.f3181n) > i12) {
                break;
            }
            c2319i.X(l04.f3180m);
            i12 -= i7;
            l03 = l02;
        }
        return c2319i.F(c2319i.f21932m);
    }

    public int g(int i7, int i10) {
        int i11 = i7 & i10;
        if (i11 < i10) {
            return i11;
        }
        int i12 = 0;
        while (true) {
            byte bK = ((D) this.f7348g).k();
            byte[] bArr = d9.b.f13645a;
            int i13 = bK & 255;
            if ((bK & 128) == 0) {
                return i10 + (i13 << i12);
            }
            i10 += (bK & 127) << i12;
            i12 += 7;
        }
    }

    public Object h(Object obj) {
        Object objRemove;
        synchronized (((d) this.f7347f)) {
            objRemove = ((HashMap) this.f7348g).remove(obj);
            ((LinkedHashSet) this.f7349h).remove(obj);
            if (objRemove != null) {
                this.f7345b = i() - 1;
            }
        }
        return objRemove;
    }

    public int i() {
        int i7;
        synchronized (((d) this.f7347f)) {
            i7 = this.f7345b;
        }
        return i7;
    }

    public String toString() {
        String str;
        switch (this.f7344a) {
            case 0:
                synchronized (((d) this.f7347f)) {
                    try {
                        int i7 = this.d;
                        int i10 = this.f7346e + i7;
                        str = "LruCache[maxSize=" + this.c + ",hits=" + this.d + ",misses=" + this.f7346e + ",hitRate=" + (i10 != 0 ? (i7 * 100) / i10 : 0) + "%]";
                    } catch (Throwable th) {
                        throw th;
                    }
                }
                return str;
            default:
                return super.toString();
        }
    }

    public b(p pVar) {
        this.f7344a = 1;
        this.f7345b = 4096;
        this.f7347f = new ArrayList();
        this.f7348g = AbstractC2312b.c(pVar);
        this.f7349h = new j9.b[8];
        this.c = 7;
    }
}
