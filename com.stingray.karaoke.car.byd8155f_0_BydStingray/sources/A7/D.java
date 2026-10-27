package A7;

import a.AbstractC0904a;
import a4.AbstractC0927h;
import android.graphics.Matrix;
import android.graphics.Path;
import b4.C0995a;
import d5.AbstractC1095q;
import d5.C1078G;
import d5.I;
import d5.d0;
import h2.AbstractC1347d;
import h3.C1350a;
import io.sentry.AbstractC1461e0;
import io.sentry.C1458d0;
import io.sentry.C1470h0;
import io.sentry.C1473i0;
import io.sentry.C1476j0;
import io.sentry.C1479k0;
import io.sentry.C1482l0;
import io.sentry.InterfaceC1464f0;
import io.sentry.InterfaceC1467g0;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import n7.AbstractC2000o;
import v2.InterfaceC2543a;
import x.AbstractC2652s;
import x.C2617F;
import x.InterfaceC2616E;
import x.InterfaceC2653t;
import x3.AbstractC2673a;

/* JADX INFO: loaded from: classes.dex */
public final class D implements V3.e, InterfaceC2543a, InterfaceC2653t {
    public final ArrayList l;

    public D(int i7, boolean z6) {
        switch (i7) {
            case 2:
                this.l = new ArrayList();
                break;
            case 3:
            case 7:
            default:
                this.l = new ArrayList();
                break;
            case 4:
                this.l = new ArrayList();
                new HashMap();
                new HashMap();
                break;
            case 5:
                this.l = new ArrayList(20);
                break;
            case 6:
                this.l = new ArrayList();
                break;
            case 8:
                this.l = new ArrayList();
                break;
            case 9:
                this.l = new ArrayList();
                break;
            case 10:
                this.l = new ArrayList();
                break;
            case 11:
                this.l = new ArrayList();
                break;
            case 12:
                this.l = new ArrayList();
                break;
        }
    }

    @Override // V3.e
    public List F0() {
        return this.l;
    }

    @Override // V3.e
    public boolean I0() {
        ArrayList arrayList = this.l;
        return arrayList.size() == 1 && ((C0995a) arrayList.get(0)).c();
    }

    @Override // v2.InterfaceC2543a
    public I a(long j3) {
        int iR = r(j3);
        if (iR == 0) {
            C1078G c1078g = I.f13384m;
            return d0.f13415p;
        }
        Y2.a aVar = (Y2.a) this.l.get(iR - 1);
        long j10 = aVar.d;
        if (j10 == -9223372036854775807L || j3 < j10) {
            return aVar.f10629a;
        }
        C1078G c1078g2 = I.f13384m;
        return d0.f13415p;
    }

    @Override // v2.InterfaceC2543a
    public long b(long j3) {
        ArrayList arrayList = this.l;
        if (arrayList.isEmpty()) {
            return Long.MIN_VALUE;
        }
        if (j3 < ((Y2.a) arrayList.get(0)).f10630b) {
            return ((Y2.a) arrayList.get(0)).f10630b;
        }
        for (int i7 = 1; i7 < arrayList.size(); i7++) {
            Y2.a aVar = (Y2.a) arrayList.get(i7);
            if (j3 < aVar.f10630b) {
                long j10 = ((Y2.a) arrayList.get(i7 - 1)).d;
                long j11 = aVar.f10630b;
                return (j10 == -9223372036854775807L || j10 <= j3 || j10 >= j11) ? j11 : j10;
            }
        }
        long j12 = ((Y2.a) AbstractC1095q.l(arrayList)).d;
        if (j12 == -9223372036854775807L || j3 >= j12) {
            return Long.MIN_VALUE;
        }
        return j12;
    }

    /* JADX WARN: Removed duplicated region for block: B:13:0x0023  */
    @Override // v2.InterfaceC2543a
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public boolean c(Y2.a r10, long r11) {
        /*
            r9 = this;
            long r0 = r10.f10630b
            r2 = -9223372036854775807(0x8000000000000001, double:-4.9E-324)
            int r4 = (r0 > r2 ? 1 : (r0 == r2 ? 0 : -1))
            r5 = 0
            r6 = 1
            if (r4 == 0) goto Lf
            r4 = r6
            goto L10
        Lf:
            r4 = r5
        L10:
            h2.AbstractC1346c.c(r4)
            int r4 = (r0 > r11 ? 1 : (r0 == r11 ? 0 : -1))
            if (r4 > 0) goto L23
            long r7 = r10.d
            int r2 = (r7 > r2 ? 1 : (r7 == r2 ? 0 : -1))
            if (r2 == 0) goto L21
            int r2 = (r11 > r7 ? 1 : (r11 == r7 ? 0 : -1))
            if (r2 >= 0) goto L23
        L21:
            r2 = r6
            goto L24
        L23:
            r2 = r5
        L24:
            java.util.ArrayList r9 = r9.l
            int r3 = r9.size()
            int r3 = r3 - r6
        L2b:
            if (r3 < 0) goto L4e
            java.lang.Object r4 = r9.get(r3)
            Y2.a r4 = (Y2.a) r4
            long r7 = r4.f10630b
            int r4 = (r0 > r7 ? 1 : (r0 == r7 ? 0 : -1))
            if (r4 < 0) goto L3e
            int r3 = r3 + r6
            r9.add(r3, r10)
            return r2
        L3e:
            java.lang.Object r4 = r9.get(r3)
            Y2.a r4 = (Y2.a) r4
            long r7 = r4.f10630b
            int r4 = (r7 > r11 ? 1 : (r7 == r11 ? 0 : -1))
            if (r4 > 0) goto L4b
            r2 = r5
        L4b:
            int r3 = r3 + (-1)
            goto L2b
        L4e:
            r9.add(r5, r10)
            return r2
        */
        throw new UnsupportedOperationException("Method not decompiled: A7.D.c(Y2.a, long):boolean");
    }

    @Override // v2.InterfaceC2543a
    public void clear() {
        this.l.clear();
    }

    @Override // v2.InterfaceC2543a
    public long d(long j3) {
        ArrayList arrayList = this.l;
        if (arrayList.isEmpty() || j3 < ((Y2.a) arrayList.get(0)).f10630b) {
            return -9223372036854775807L;
        }
        for (int i7 = 1; i7 < arrayList.size(); i7++) {
            long j10 = ((Y2.a) arrayList.get(i7)).f10630b;
            if (j3 == j10) {
                return j10;
            }
            if (j3 < j10) {
                Y2.a aVar = (Y2.a) arrayList.get(i7 - 1);
                long j11 = aVar.d;
                return (j11 == -9223372036854775807L || j11 > j3) ? aVar.f10630b : j11;
            }
        }
        Y2.a aVar2 = (Y2.a) AbstractC1095q.l(arrayList);
        long j12 = aVar2.d;
        return (j12 == -9223372036854775807L || j3 < j12) ? aVar2.f10630b : j12;
    }

    @Override // v2.InterfaceC2543a
    public void e(long j3) {
        int iR = r(j3);
        if (iR > 0) {
            this.l.subList(0, iR).clear();
        }
    }

    public void f(Object obj) {
        this.l.add(obj);
    }

    public void g(String str, String str2) {
        m.f(str, "name");
        m.f(str2, "value");
        AbstractC0904a.t(str);
        AbstractC0904a.v(str2, str);
        i(str, str2);
    }

    @Override // x.InterfaceC2653t
    public InterfaceC2616E get(int i7) {
        return (C2617F) this.l.get(i7);
    }

    public void h(String str) {
        int iJ0 = Q8.h.J0(str, ':', 1, false, 4);
        if (iJ0 != -1) {
            String strSubstring = str.substring(0, iJ0);
            m.e(strSubstring, "this as java.lang.String…ing(startIndex, endIndex)");
            String strSubstring2 = str.substring(iJ0 + 1);
            m.e(strSubstring2, "this as java.lang.String).substring(startIndex)");
            i(strSubstring, strSubstring2);
            return;
        }
        if (str.charAt(0) != ':') {
            i("", str);
            return;
        }
        String strSubstring3 = str.substring(1);
        m.e(strSubstring3, "this as java.lang.String).substring(startIndex)");
        i("", strSubstring3);
    }

    public void i(String str, String str2) {
        m.f(str, "name");
        m.f(str2, "value");
        ArrayList arrayList = this.l;
        arrayList.add(str);
        arrayList.add(Q8.h.g1(str2).toString());
    }

    public void j(Object obj) {
        if (obj == null) {
            return;
        }
        boolean z6 = obj instanceof Object[];
        ArrayList arrayList = this.l;
        if (z6) {
            Object[] objArr = (Object[]) obj;
            if (objArr.length > 0) {
                arrayList.ensureCapacity(arrayList.size() + objArr.length);
                Collections.addAll(arrayList, objArr);
                return;
            }
            return;
        }
        if (obj instanceof Collection) {
            arrayList.addAll((Collection) obj);
            return;
        }
        if (obj instanceof Iterable) {
            Iterator it = ((Iterable) obj).iterator();
            while (it.hasNext()) {
                arrayList.add(it.next());
            }
        } else {
            if (!(obj instanceof Iterator)) {
                throw new UnsupportedOperationException("Don't know how to spread " + obj.getClass());
            }
            Iterator it2 = (Iterator) obj;
            while (it2.hasNext()) {
                arrayList.add(it2.next());
            }
        }
    }

    public void k(String str, String str2) {
        m.f(str, "name");
        m.f(str2, "value");
        if (str.length() <= 0) {
            throw new IllegalArgumentException("name is empty");
        }
        int length = str.length();
        for (int i7 = 0; i7 < length; i7++) {
            char cCharAt = str.charAt(i7);
            if ('!' > cCharAt || cCharAt >= 127) {
                throw new IllegalArgumentException(d9.b.i("Unexpected char %#04x at %d in header name: %s", Integer.valueOf(cCharAt), Integer.valueOf(i7), str).toString());
            }
        }
        i(str, str2);
    }

    public void l(Path path) {
        ArrayList arrayList = this.l;
        for (int size = arrayList.size() - 1; size >= 0; size--) {
            Q3.s sVar = (Q3.s) arrayList.get(size);
            Matrix matrix = AbstractC0927h.f11132a;
            if (sVar != null && !sVar.f6218a) {
                AbstractC0927h.a(path, sVar.d.i() / 100.0f, sVar.f6220e.i() / 100.0f, sVar.f6221f.i() / 360.0f);
            }
        }
    }

    public c9.u m() {
        return new c9.u((String[]) this.l.toArray(new String[0]));
    }

    public synchronized e4.k n(Class cls) {
        int size = this.l.size();
        for (int i7 = 0; i7 < size; i7++) {
            v4.d dVar = (v4.d) this.l.get(i7);
            if (dVar.f23007a.isAssignableFrom(cls)) {
                return dVar.f23008b;
            }
        }
        return null;
    }

    public String o(String str) {
        m.f(str, "name");
        ArrayList arrayList = this.l;
        int size = arrayList.size() - 2;
        int iN = n7.z.n(size, 0, -2);
        if (iN > size) {
            return null;
        }
        while (!str.equalsIgnoreCase((String) arrayList.get(size))) {
            if (size == iN) {
                return null;
            }
            size -= 2;
        }
        return (String) arrayList.get(size + 1);
    }

    public InterfaceC1467g0 p() {
        ArrayList arrayList = this.l;
        if (arrayList.isEmpty()) {
            return null;
        }
        return (InterfaceC1467g0) AbstractC1347d.e(1, arrayList);
    }

    public List q() {
        ArrayList arrayList;
        if (this.l.isEmpty()) {
            return Collections.emptyList();
        }
        synchronized (this.l) {
            arrayList = new ArrayList(this.l);
        }
        return arrayList;
    }

    public int r(long j3) {
        int i7 = 0;
        while (true) {
            ArrayList arrayList = this.l;
            if (i7 >= arrayList.size()) {
                return arrayList.size();
            }
            if (j3 < ((Y2.a) arrayList.get(i7)).f10630b) {
                return i7;
            }
            i7++;
        }
    }

    public synchronized ArrayList s(Class cls, Class cls2) {
        ArrayList arrayList = new ArrayList();
        if (cls2.isAssignableFrom(cls)) {
            arrayList.add(cls2);
            return arrayList;
        }
        for (s4.b bVar : this.l) {
            if ((bVar.f22365a.isAssignableFrom(cls) && cls2.isAssignableFrom(bVar.f22366b)) && !arrayList.contains(bVar.f22366b)) {
                arrayList.add(bVar.f22366b);
            }
        }
        return arrayList;
    }

    public boolean t() {
        if (this.l.size() == 1) {
            return true;
        }
        InterfaceC1467g0 interfaceC1467g0P = p();
        w();
        if (!(p() instanceof C1476j0)) {
            if (!(p() instanceof C1470h0)) {
                return false;
            }
            C1470h0 c1470h0 = (C1470h0) p();
            if (interfaceC1467g0P == null || c1470h0 == null) {
                return false;
            }
            c1470h0.f16545a.add(interfaceC1467g0P.getValue());
            return false;
        }
        C1476j0 c1476j0 = (C1476j0) p();
        w();
        C1473i0 c1473i0 = (C1473i0) p();
        if (c1476j0 == null || interfaceC1467g0P == null || c1473i0 == null) {
            return false;
        }
        c1473i0.f16554a.put(c1476j0.f16583a, interfaceC1467g0P.getValue());
        return false;
    }

    @Override // V3.e
    public R3.e t0() {
        ArrayList arrayList = this.l;
        return ((C0995a) arrayList.get(0)).c() ? new R3.h(1, arrayList) : new R3.k(arrayList);
    }

    public boolean u(InterfaceC1464f0 interfaceC1464f0) {
        Object objK = interfaceC1464f0.k();
        if (p() == null && objK != null) {
            this.l.add(new C1479k0(objK));
            return true;
        }
        if (p() instanceof C1476j0) {
            C1476j0 c1476j0 = (C1476j0) p();
            w();
            ((C1473i0) p()).f16554a.put(c1476j0.f16583a, objK);
            return false;
        }
        if (!(p() instanceof C1470h0)) {
            return false;
        }
        ((C1470h0) p()).f16545a.add(objK);
        return false;
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    public void v(final C1482l0 c1482l0) {
        boolean zT;
        int i7 = AbstractC1461e0.f16526a[((io.sentry.vendor.gson.stream.a) c1482l0.f16608m).P().ordinal()];
        ArrayList arrayList = this.l;
        io.sentry.vendor.gson.stream.a aVar = (io.sentry.vendor.gson.stream.a) c1482l0.f16608m;
        switch (i7) {
            case 1:
                aVar.b();
                arrayList.add(new C1470h0());
                zT = false;
                break;
            case 2:
                aVar.k();
                zT = t();
                break;
            case 3:
                c1482l0.g();
                arrayList.add(new C1473i0());
                zT = false;
                break;
            case 4:
                c1482l0.i();
                zT = t();
                break;
            case 5:
                arrayList.add(new C1476j0(aVar.I()));
                zT = false;
                break;
            case 6:
                final int i10 = 0;
                zT = u(new InterfaceC1464f0() { // from class: io.sentry.c0
                    @Override // io.sentry.InterfaceC1464f0
                    public final Object k() {
                        switch (i10) {
                            case 0:
                                return ((io.sentry.vendor.gson.stream.a) c1482l0.f16608m).N();
                            default:
                                return Boolean.valueOf(((io.sentry.vendor.gson.stream.a) c1482l0.f16608m).z());
                        }
                    }
                });
                break;
            case 7:
                zT = u(new C1458d0(this, 0, c1482l0));
                break;
            case 8:
                final int i11 = 1;
                zT = u(new InterfaceC1464f0() { // from class: io.sentry.c0
                    @Override // io.sentry.InterfaceC1464f0
                    public final Object k() {
                        switch (i11) {
                            case 0:
                                return ((io.sentry.vendor.gson.stream.a) c1482l0.f16608m).N();
                            default:
                                return Boolean.valueOf(((io.sentry.vendor.gson.stream.a) c1482l0.f16608m).z());
                        }
                    }
                });
                break;
            case 9:
                aVar.L();
                zT = u(new C1350a(7));
                break;
            case 10:
                zT = true;
                break;
            default:
                zT = false;
                break;
        }
        if (zT) {
            return;
        }
        v(c1482l0);
    }

    public void w() {
        ArrayList arrayList = this.l;
        if (arrayList.isEmpty()) {
            return;
        }
        arrayList.remove(arrayList.size() - 1);
    }

    public void x(String str) {
        m.f(str, "name");
        int i7 = 0;
        while (true) {
            ArrayList arrayList = this.l;
            if (i7 >= arrayList.size()) {
                return;
            }
            if (str.equalsIgnoreCase((String) arrayList.get(i7))) {
                arrayList.remove(i7);
                arrayList.remove(i7);
                i7 -= 2;
            }
            i7 += 2;
        }
    }

    public D(List list) {
        this.l = new ArrayList(list == null ? new ArrayList(0) : list);
    }

    public D(ArrayList arrayList) {
        this.l = arrayList;
    }

    public D(int i7) {
        this.l = new ArrayList(i7);
    }

    public D(float f10, float f11, AbstractC2652s abstractC2652s) {
        G7.d dVarB0 = AbstractC2673a.b0(0, abstractC2652s.b());
        ArrayList arrayList = new ArrayList(AbstractC2000o.J(dVarB0, 10));
        G7.c it = dVarB0.iterator();
        while (it.f2715n) {
            arrayList.add(new C2617F(f10, f11, abstractC2652s.a(it.b())));
        }
        this.l = arrayList;
    }
}
