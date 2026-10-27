package Y4;

import N1.C0420a;
import android.graphics.Matrix;
import android.graphics.Path;
import android.graphics.PointF;
import android.graphics.RectF;
import android.os.Handler;
import e2.f0;
import h2.AbstractC1343A;
import h2.x;
import java.util.ArrayList;
import java.util.BitSet;
import java.util.HashMap;
import java.util.HashSet;
import java.util.IdentityHashMap;
import java.util.Iterator;
import java.util.concurrent.CopyOnWriteArrayList;
import k2.E;
import n2.D;
import n2.M;
import n2.Q;
import n2.S;
import n2.T;
import n2.Z;
import u2.AbstractC2498a;
import u2.C2496G;
import u2.C2517u;
import u2.C2520x;
import u2.InterfaceC2490A;
import u2.InterfaceC2493D;
import u2.c0;

/* JADX INFO: loaded from: classes.dex */
public final class m {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public boolean f10758a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final Object f10759b;
    public final Object c;
    public final Object d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final Object f10760e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final Object f10761f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public final Object f10762g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public final Object f10763h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final Object f10764i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public final Object f10765j;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    public Object f10766k;
    public Object l;

    public m() {
        this.f10759b = new t[4];
        this.c = new Matrix[4];
        this.d = new Matrix[4];
        this.f10760e = new PointF();
        this.f10761f = new Path();
        this.f10762g = new Path();
        this.f10765j = new t();
        this.f10766k = new float[2];
        this.l = new float[2];
        this.f10763h = new Path();
        this.f10764i = new Path();
        this.f10758a = true;
        for (int i7 = 0; i7 < 4; i7++) {
            ((t[]) this.f10759b)[i7] = new t();
            ((Matrix[]) this.c)[i7] = new Matrix();
            ((Matrix[]) this.d)[i7] = new Matrix();
        }
    }

    public f0 a(int i7, ArrayList arrayList, c0 c0Var) {
        if (!arrayList.isEmpty()) {
            this.f10766k = c0Var;
            for (int i10 = i7; i10 < arrayList.size() + i7; i10++) {
                T t10 = (T) arrayList.get(i10 - i7);
                ArrayList arrayList2 = (ArrayList) this.c;
                if (i10 > 0) {
                    T t11 = (T) arrayList2.get(i10 - 1);
                    t10.d = t11.f19582a.o.f22896e.o() + t11.d;
                    t10.f19584e = false;
                    t10.c.clear();
                } else {
                    t10.d = 0;
                    t10.f19584e = false;
                    t10.c.clear();
                }
                int iO = t10.f19582a.o.f22896e.o();
                for (int i11 = i10; i11 < arrayList2.size(); i11++) {
                    ((T) arrayList2.get(i11)).d += iO;
                }
                arrayList2.add(i10, t10);
                ((HashMap) this.f10760e).put(t10.f19583b, t10);
                if (this.f10758a) {
                    g(t10);
                    if (((IdentityHashMap) this.d).isEmpty()) {
                        ((HashSet) this.f10763h).add(t10);
                    } else {
                        S s6 = (S) ((HashMap) this.f10762g).get(t10);
                        if (s6 != null) {
                            s6.f19580a.c(s6.f19581b);
                        }
                    }
                }
            }
        }
        return c();
    }

    public void b(k kVar, float f10, RectF rectF, C0420a c0420a, Path path) {
        Matrix[] matrixArr;
        Matrix[] matrixArr2;
        t[] tVarArr;
        int i7;
        float[] fArr;
        int i10;
        m mVar = this;
        path.rewind();
        Path path2 = (Path) mVar.f10761f;
        path2.rewind();
        Path path3 = (Path) mVar.f10762g;
        path3.rewind();
        path3.addRect(rectF, Path.Direction.CW);
        int i11 = 0;
        while (true) {
            matrixArr = (Matrix[]) mVar.d;
            matrixArr2 = (Matrix[]) mVar.c;
            tVarArr = (t[]) mVar.f10759b;
            i7 = 4;
            fArr = (float[]) mVar.f10766k;
            if (i11 >= 4) {
                break;
            }
            c cVar = i11 != 1 ? i11 != 2 ? i11 != 3 ? kVar.f10751f : kVar.f10750e : kVar.f10753h : kVar.f10752g;
            com.bumptech.glide.c cVar2 = i11 != 1 ? i11 != 2 ? i11 != 3 ? kVar.f10749b : kVar.f10748a : kVar.d : kVar.c;
            t tVar = tVarArr[i11];
            cVar2.getClass();
            cVar2.b0(tVar, f10, cVar.a(rectF));
            int i12 = i11 + 1;
            float f11 = i12 * 90;
            matrixArr2[i11].reset();
            PointF pointF = (PointF) mVar.f10760e;
            if (i11 == 1) {
                i10 = i12;
                pointF.set(rectF.right, rectF.bottom);
            } else if (i11 == 2) {
                i10 = i12;
                pointF.set(rectF.left, rectF.bottom);
            } else if (i11 != 3) {
                i10 = i12;
                pointF.set(rectF.right, rectF.top);
            } else {
                i10 = i12;
                pointF.set(rectF.left, rectF.top);
            }
            matrixArr2[i11].setTranslate(pointF.x, pointF.y);
            matrixArr2[i11].preRotate(f11);
            t tVar2 = tVarArr[i11];
            fArr[0] = tVar2.f10777b;
            fArr[1] = tVar2.c;
            matrixArr2[i11].mapPoints(fArr);
            matrixArr[i11].reset();
            matrixArr[i11].setTranslate(fArr[0], fArr[1]);
            matrixArr[i11].preRotate(f11);
            i11 = i10;
        }
        int i13 = 0;
        while (i13 < i7) {
            t tVar3 = tVarArr[i13];
            tVar3.getClass();
            fArr[0] = 0.0f;
            fArr[1] = tVar3.f10776a;
            matrixArr2[i13].mapPoints(fArr);
            if (i13 == 0) {
                path.moveTo(fArr[0], fArr[1]);
            } else {
                path.lineTo(fArr[0], fArr[1]);
            }
            tVarArr[i13].b(matrixArr2[i13], path);
            if (c0420a != null) {
                t tVar4 = tVarArr[i13];
                Matrix matrix = matrixArr2[i13];
                g gVar = (g) c0420a.l;
                BitSet bitSet = gVar.o;
                tVar4.getClass();
                bitSet.set(i13, false);
                tVar4.a(tVar4.f10778e);
                gVar.f10725m[i13] = new n(new ArrayList(tVar4.f10780g), new Matrix(matrix));
            }
            int i14 = i13 + 1;
            int i15 = i14 % 4;
            t tVar5 = tVarArr[i13];
            fArr[0] = tVar5.f10777b;
            fArr[1] = tVar5.c;
            matrixArr2[i13].mapPoints(fArr);
            t tVar6 = tVarArr[i15];
            tVar6.getClass();
            float[] fArr2 = (float[]) mVar.l;
            fArr2[0] = 0.0f;
            fArr2[1] = tVar6.f10776a;
            matrixArr2[i15].mapPoints(fArr2);
            float fMax = Math.max(((float) Math.hypot(fArr[0] - fArr2[0], fArr[1] - fArr2[1])) - 0.001f, 0.0f);
            t tVar7 = tVarArr[i13];
            fArr[0] = tVar7.f10777b;
            fArr[1] = tVar7.c;
            matrixArr2[i13].mapPoints(fArr);
            if (i13 == 1 || i13 == 3) {
                Math.abs(rectF.centerX() - fArr[0]);
            } else {
                Math.abs(rectF.centerY() - fArr[1]);
            }
            t tVar8 = (t) mVar.f10765j;
            tVar8.d(0.0f, 270.0f, 0.0f);
            (i13 != 1 ? i13 != 2 ? i13 != 3 ? kVar.f10755j : kVar.f10754i : kVar.l : kVar.f10756k).getClass();
            tVar8.c(fMax, 0.0f);
            Path path4 = (Path) mVar.f10763h;
            path4.reset();
            tVar8.b(matrixArr[i13], path4);
            if (mVar.f10758a && (mVar.f(path4, i13) || mVar.f(path4, i15))) {
                path4.op(path4, path3, Path.Op.DIFFERENCE);
                fArr[0] = 0.0f;
                fArr[1] = tVar8.f10776a;
                matrixArr[i13].mapPoints(fArr);
                path2.moveTo(fArr[0], fArr[1]);
                tVar8.b(matrixArr[i13], path2);
            } else {
                tVar8.b(matrixArr[i13], path);
            }
            if (c0420a != null) {
                Matrix matrix2 = matrixArr[i13];
                g gVar2 = (g) c0420a.l;
                gVar2.o.set(i13 + 4, false);
                tVar8.a(tVar8.f10778e);
                gVar2.f10726n[i13] = new n(new ArrayList(tVar8.f10780g), new Matrix(matrix2));
            }
            i7 = 4;
            mVar = this;
            i13 = i14;
        }
        path.close();
        path2.close();
        if (path2.isEmpty()) {
            return;
        }
        path.op(path2, Path.Op.UNION);
    }

    public f0 c() {
        ArrayList arrayList = (ArrayList) this.c;
        if (arrayList.isEmpty()) {
            return f0.f14097a;
        }
        int iO = 0;
        for (int i7 = 0; i7 < arrayList.size(); i7++) {
            T t10 = (T) arrayList.get(i7);
            t10.d = iO;
            iO += t10.f19582a.o.f22896e.o();
        }
        return new Z(arrayList, (c0) this.f10766k);
    }

    public void d() {
        Iterator it = ((HashSet) this.f10763h).iterator();
        while (it.hasNext()) {
            T t10 = (T) it.next();
            if (t10.c.isEmpty()) {
                S s6 = (S) ((HashMap) this.f10762g).get(t10);
                if (s6 != null) {
                    s6.f19580a.c(s6.f19581b);
                }
                it.remove();
            }
        }
    }

    public void e(T t10) {
        if (t10.f19584e && t10.c.isEmpty()) {
            S s6 = (S) ((HashMap) this.f10762g).remove(t10);
            s6.getClass();
            M m10 = s6.f19581b;
            AbstractC2498a abstractC2498a = s6.f19580a;
            abstractC2498a.o(m10);
            Q q4 = s6.c;
            abstractC2498a.r(q4);
            abstractC2498a.q(q4);
            ((HashSet) this.f10763h).remove(t10);
        }
    }

    public boolean f(Path path, int i7) {
        Path path2 = (Path) this.f10764i;
        path2.reset();
        ((t[]) this.f10759b)[i7].b(((Matrix[]) this.c)[i7], path2);
        RectF rectF = new RectF();
        path.computeBounds(rectF, true);
        path2.computeBounds(rectF, true);
        path.op(path2, Path.Op.INTERSECT);
        path.computeBounds(rectF, true);
        if (rectF.isEmpty()) {
            return rectF.width() > 1.0f && rectF.height() > 1.0f;
        }
        return true;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r1v0, types: [n2.M, u2.D] */
    /* JADX WARN: Type inference fix 'apply assigned field type' failed
    java.lang.UnsupportedOperationException: ArgType.getObject(), call class: class jadx.core.dex.instructions.args.ArgType$UnknownArg
    	at jadx.core.dex.instructions.args.ArgType.getObject(ArgType.java:593)
    	at jadx.core.dex.attributes.nodes.ClassTypeVarsAttr.getTypeVarsMapFor(ClassTypeVarsAttr.java:35)
    	at jadx.core.dex.nodes.utils.TypeUtils.replaceClassGenerics(TypeUtils.java:177)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.insertExplicitUseCast(FixTypesVisitor.java:397)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.tryFieldTypeWithNewCasts(FixTypesVisitor.java:359)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.applyFieldType(FixTypesVisitor.java:309)
    	at jadx.core.dex.visitors.typeinference.FixTypesVisitor.visit(FixTypesVisitor.java:94)
     */
    public void g(T t10) {
        C2520x c2520x = t10.f19582a;
        ?? r12 = new InterfaceC2493D() { // from class: n2.M
            @Override // u2.InterfaceC2493D
            public final void a(AbstractC2498a abstractC2498a, f0 f0Var) {
                h2.x xVar = ((D) this.f19569a.f10761f).f19525t;
                xVar.d(2);
                xVar.e(22);
            }
        };
        Q q4 = new Q(this, t10);
        ((HashMap) this.f10762g).put(t10, new S(c2520x, r12, q4));
        Handler handler = new Handler(AbstractC1343A.q(), null);
        c2520x.getClass();
        B5.u uVar = c2520x.c;
        uVar.getClass();
        C2496G c2496g = new C2496G();
        c2496g.f22671a = handler;
        c2496g.f22672b = q4;
        ((CopyOnWriteArrayList) uVar.o).add(c2496g);
        Handler handler2 = new Handler(AbstractC1343A.q(), null);
        q2.k kVar = c2520x.d;
        kVar.getClass();
        q2.j jVar = new q2.j();
        jVar.f21207a = handler2;
        jVar.f21208b = q4;
        kVar.c.add(jVar);
        c2520x.k(r12, (E) this.l, (o2.l) this.f10759b);
    }

    public void h(InterfaceC2490A interfaceC2490A) {
        IdentityHashMap identityHashMap = (IdentityHashMap) this.d;
        T t10 = (T) identityHashMap.remove(interfaceC2490A);
        t10.getClass();
        t10.f19582a.n(interfaceC2490A);
        t10.c.remove(((C2517u) interfaceC2490A).l);
        if (!identityHashMap.isEmpty()) {
            d();
        }
        e(t10);
    }

    public void i(int i7, int i10) {
        for (int i11 = i10 - 1; i11 >= i7; i11--) {
            ArrayList arrayList = (ArrayList) this.c;
            T t10 = (T) arrayList.remove(i11);
            ((HashMap) this.f10760e).remove(t10.f19583b);
            int i12 = -t10.f19582a.o.f22896e.o();
            for (int i13 = i11; i13 < arrayList.size(); i13++) {
                ((T) arrayList.get(i13)).d += i12;
            }
            t10.f19584e = true;
            if (this.f10758a) {
                e(t10);
            }
        }
    }

    public m(D d, o2.e eVar, x xVar, o2.l lVar) {
        this.f10759b = lVar;
        this.f10761f = d;
        this.f10766k = new c0();
        this.d = new IdentityHashMap();
        this.f10760e = new HashMap();
        this.c = new ArrayList();
        this.f10764i = eVar;
        this.f10765j = xVar;
        this.f10762g = new HashMap();
        this.f10763h = new HashSet();
    }
}
