package N0;

import B2.C0074n;
import a.AbstractC0904a;
import android.graphics.Matrix;
import android.graphics.Shader;
import android.text.Layout;
import java.util.ArrayList;
import java.util.List;
import n0.AbstractC1864N;
import n0.AbstractC1887p;
import n0.C1865O;
import n0.C1868S;
import n0.C1888q;
import n0.InterfaceC1889r;
import n7.AbstractC1998m;
import n7.AbstractC1999n;
import n7.AbstractC2004s;
import x3.AbstractC2673a;

/* JADX INFO: loaded from: classes.dex */
public final class o {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final q f4391a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final int f4392b;
    public final boolean c;
    public final float d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final float f4393e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final int f4394f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public final ArrayList f4395g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public final ArrayList f4396h;

    /* JADX WARN: Type inference failed for: r7v1, types: [java.lang.Object, java.util.List] */
    public o(q qVar, long j3, int i7, boolean z6) {
        boolean z10;
        int iG;
        this.f4391a = qVar;
        this.f4392b = i7;
        if (a1.a.j(j3) != 0 || a1.a.i(j3) != 0) {
            throw new IllegalArgumentException("Setting Constraints.minWidth and Constraints.minHeight is not supported, these should be the default zero values instead.");
        }
        ArrayList arrayList = new ArrayList();
        ArrayList arrayList2 = (ArrayList) qVar.l;
        int size = arrayList2.size();
        int i10 = 0;
        int i11 = 0;
        float f10 = 0.0f;
        while (i10 < size) {
            s sVar = (s) arrayList2.get(i10);
            V0.c cVar = sVar.f4407a;
            int iH = a1.a.h(j3);
            if (a1.a.c(j3)) {
                iG = a1.a.g(j3) - ((int) Math.ceil(f10));
                if (iG < 0) {
                    iG = 0;
                }
            } else {
                iG = a1.a.g(j3);
            }
            C0406a c0406a = new C0406a(cVar, this.f4392b - i11, z6, io.sentry.config.a.f(iH, iG, 5));
            float fB = c0406a.b() + f10;
            O0.x xVar = c0406a.d;
            int i12 = i11 + xVar.f5051g;
            ArrayList arrayList3 = arrayList2;
            arrayList.add(new r(c0406a, sVar.f4408b, sVar.c, i11, i12, f10, fB));
            if (xVar.d || (i12 == this.f4392b && i10 != AbstractC1999n.C((ArrayList) this.f4391a.l))) {
                z10 = true;
                f10 = fB;
                i11 = i12;
                break;
            } else {
                i10++;
                f10 = fB;
                i11 = i12;
                arrayList2 = arrayList3;
            }
        }
        z10 = false;
        this.f4393e = f10;
        this.f4394f = i11;
        this.c = z10;
        this.f4396h = arrayList;
        this.d = a1.a.h(j3);
        ArrayList arrayList4 = new ArrayList(arrayList.size());
        int size2 = arrayList.size();
        for (int i13 = 0; i13 < size2; i13++) {
            r rVar = (r) arrayList.get(i13);
            ?? r72 = rVar.f4402a.f4365f;
            ArrayList arrayList5 = new ArrayList(r72.size());
            int size3 = r72.size();
            for (int i14 = 0; i14 < size3; i14++) {
                m0.d dVar = (m0.d) r72.get(i14);
                arrayList5.add(dVar != null ? dVar.h(h6.e.a(0.0f, rVar.f4405f)) : null);
            }
            AbstractC2004s.L(arrayList4, arrayList5);
        }
        if (arrayList4.size() < ((List) this.f4391a.f4400n).size()) {
            int size4 = ((List) this.f4391a.f4400n).size() - arrayList4.size();
            ArrayList arrayList6 = new ArrayList(size4);
            for (int i15 = 0; i15 < size4; i15++) {
                arrayList6.add(null);
            }
            arrayList4 = AbstractC1998m.n0(arrayList4, arrayList6);
        }
        this.f4395g = arrayList4;
    }

    public static void g(o oVar, InterfaceC1889r interfaceC1889r, long j3, C1865O c1865o, Y0.j jVar, p0.e eVar) {
        interfaceC1889r.m();
        ArrayList arrayList = oVar.f4396h;
        int size = arrayList.size();
        for (int i7 = 0; i7 < size; i7++) {
            r rVar = (r) arrayList.get(i7);
            rVar.f4402a.f(interfaceC1889r, j3, c1865o, jVar, eVar, 3);
            interfaceC1889r.j(0.0f, rVar.f4402a.b());
        }
        interfaceC1889r.k();
    }

    public static void h(o oVar, InterfaceC1889r interfaceC1889r, AbstractC1887p abstractC1887p, float f10, C1865O c1865o, Y0.j jVar, p0.e eVar) {
        interfaceC1889r.m();
        ArrayList arrayList = oVar.f4396h;
        if (arrayList.size() <= 1 || (abstractC1887p instanceof C1868S)) {
            V0.i.a(oVar, interfaceC1889r, abstractC1887p, f10, c1865o, jVar, eVar, 3);
        } else if (abstractC1887p instanceof AbstractC1864N) {
            int size = arrayList.size();
            float fMax = 0.0f;
            float fB = 0.0f;
            for (int i7 = 0; i7 < size; i7++) {
                r rVar = (r) arrayList.get(i7);
                fB += rVar.f4402a.b();
                fMax = Math.max(fMax, rVar.f4402a.d());
            }
            Shader shaderB = ((AbstractC1864N) abstractC1887p).b(h6.j.f(fMax, fB));
            Matrix matrix = new Matrix();
            shaderB.getLocalMatrix(matrix);
            int size2 = arrayList.size();
            for (int i10 = 0; i10 < size2; i10++) {
                r rVar2 = (r) arrayList.get(i10);
                rVar2.f4402a.g(interfaceC1889r, new C1888q(shaderB), f10, c1865o, jVar, eVar, 3);
                C0406a c0406a = rVar2.f4402a;
                interfaceC1889r.j(0.0f, c0406a.b());
                matrix.setTranslate(0.0f, -c0406a.b());
                shaderB.setLocalMatrix(matrix);
            }
        }
        interfaceC1889r.k();
    }

    public final void a(long j3, float[] fArr) {
        i(K.e(j3));
        j(K.d(j3));
        A7.x xVar = new A7.x();
        xVar.l = 0;
        AbstractC2673a.F(this.f4396h, j3, new C0419n(j3, fArr, xVar, new A7.w()));
    }

    public final float b(int i7) {
        k(i7);
        ArrayList arrayList = this.f4396h;
        r rVar = (r) arrayList.get(AbstractC2673a.D(i7, arrayList));
        C0406a c0406a = rVar.f4402a;
        return c0406a.d.e(i7 - rVar.d) + rVar.f4405f;
    }

    public final int c(float f10) {
        ArrayList arrayList = this.f4396h;
        r rVar = (r) arrayList.get(AbstractC2673a.E(arrayList, f10));
        int i7 = rVar.c - rVar.f4403b;
        int i10 = rVar.d;
        if (i7 == 0) {
            return i10;
        }
        float f11 = f10 - rVar.f4405f;
        O0.x xVar = rVar.f4402a.d;
        return i10 + xVar.f5050f.getLineForVertical(((int) f11) - xVar.f5052h);
    }

    public final float d(int i7) {
        k(i7);
        ArrayList arrayList = this.f4396h;
        r rVar = (r) arrayList.get(AbstractC2673a.D(i7, arrayList));
        C0406a c0406a = rVar.f4402a;
        return c0406a.d.g(i7 - rVar.d) + rVar.f4405f;
    }

    public final int e(long j3) {
        ArrayList arrayList = this.f4396h;
        r rVar = (r) arrayList.get(AbstractC2673a.E(arrayList, m0.c.e(j3)));
        int i7 = rVar.c;
        int i10 = rVar.f4403b;
        if (i7 - i10 == 0) {
            return i10;
        }
        long jA = h6.e.a(m0.c.d(j3), m0.c.e(j3) - rVar.f4405f);
        C0406a c0406a = rVar.f4402a;
        int iE = (int) m0.c.e(jA);
        O0.x xVar = c0406a.d;
        int i11 = iE - xVar.f5052h;
        Layout layout = xVar.f5050f;
        int lineForVertical = layout.getLineForVertical(i11);
        return i10 + layout.getOffsetForHorizontal(lineForVertical, (xVar.b(lineForVertical) * (-1)) + m0.c.d(jA));
    }

    public final long f(m0.d dVar, int i7, C0074n c0074n) {
        long jA;
        long j3;
        ArrayList arrayList = this.f4396h;
        int iE = AbstractC2673a.E(arrayList, dVar.f18883b);
        float f10 = ((r) arrayList.get(iE)).f4406g;
        float f11 = dVar.d;
        if (f10 >= f11 || iE == AbstractC1999n.C(arrayList)) {
            r rVar = (r) arrayList.get(iE);
            return rVar.a(rVar.f4402a.c(dVar.h(h6.e.a(0.0f, -rVar.f4405f)), i7, c0074n), true);
        }
        int iE2 = AbstractC2673a.E(arrayList, f11);
        long jA2 = K.f4356b;
        while (true) {
            jA = K.f4356b;
            if (!K.a(jA2, jA) || iE > iE2) {
                break;
            }
            r rVar2 = (r) arrayList.get(iE);
            jA2 = rVar2.a(rVar2.f4402a.c(dVar.h(h6.e.a(0.0f, -rVar2.f4405f)), i7, c0074n), true);
            iE++;
        }
        if (K.a(jA2, jA)) {
            return jA;
        }
        while (true) {
            j3 = K.f4356b;
            if (!K.a(jA, j3) || iE > iE2) {
                break;
            }
            r rVar3 = (r) arrayList.get(iE2);
            jA = rVar3.a(rVar3.f4402a.c(dVar.h(h6.e.a(0.0f, -rVar3.f4405f)), i7, c0074n), true);
            iE2--;
        }
        return K.a(jA, j3) ? jA2 : AbstractC0904a.q((int) (jA2 >> 32), (int) (4294967295L & jA));
    }

    public final void i(int i7) {
        q qVar = this.f4391a;
        if (i7 < 0 || i7 >= ((C0411f) qVar.f4399m).l.length()) {
            StringBuilder sbJ = M5.b.j("offset(", i7, ") is out of bounds [0, ");
            sbJ.append(((C0411f) qVar.f4399m).l.length());
            sbJ.append(')');
            throw new IllegalArgumentException(sbJ.toString().toString());
        }
    }

    public final void j(int i7) {
        q qVar = this.f4391a;
        if (i7 < 0 || i7 > ((C0411f) qVar.f4399m).l.length()) {
            StringBuilder sbJ = M5.b.j("offset(", i7, ") is out of bounds [0, ");
            sbJ.append(((C0411f) qVar.f4399m).l.length());
            sbJ.append(']');
            throw new IllegalArgumentException(sbJ.toString().toString());
        }
    }

    public final void k(int i7) {
        int i10 = this.f4394f;
        if (i7 < 0 || i7 >= i10) {
            throw new IllegalArgumentException(("lineIndex(" + i7 + ") is out of bounds [0, " + i10 + ')').toString());
        }
    }
}
