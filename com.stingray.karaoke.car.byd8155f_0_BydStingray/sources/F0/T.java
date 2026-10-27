package F0;

import E0.AbstractC0156f;
import E0.InterfaceC0163m;
import a.AbstractC0904a;
import android.graphics.Rect;
import android.graphics.Region;
import android.os.Binder;
import android.os.Parcelable;
import android.util.Size;
import android.util.SizeF;
import android.util.SparseArray;
import android.view.View;
import e0.InterfaceC1176p;
import java.io.Serializable;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import m0.AbstractC1792a;
import n0.AbstractC1858H;
import n0.AbstractC1861K;
import n0.C1855E;
import n0.C1856F;
import n0.C1857G;
import n0.C1880i;
import n0.InterfaceC1859I;
import u.AbstractC2469i;
import u.C2476p;
import z7.InterfaceC2782k;

/* JADX INFO: loaded from: classes.dex */
public abstract class T implements V0 {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final Class[] f2110a = {Serializable.class, Parcelable.class, String.class, SparseArray.class, Binder.class, Size.class, SizeF.class};

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final m0.d f2111b = new m0.d(0.0f, 0.0f, 10.0f, 10.0f);

    public static final String A(int i7) {
        if (L0.g.a(i7, 0)) {
            return "android.widget.Button";
        }
        if (L0.g.a(i7, 1)) {
            return "android.widget.CheckBox";
        }
        if (L0.g.a(i7, 3)) {
            return "android.widget.RadioButton";
        }
        if (L0.g.a(i7, 5)) {
            return "android.widget.ImageView";
        }
        if (L0.g.a(i7, 6)) {
            return "android.widget.Spinner";
        }
        return null;
    }

    public static void B(View view) {
        try {
            if (!Z0.f2153D) {
                Z0.f2153D = true;
                Z0.B = (Method) Class.class.getDeclaredMethod("getDeclaredMethod", String.class, new Class[0].getClass()).invoke(View.class, "updateDisplayListIfDirty", new Class[0]);
                Z0.f2152C = (Field) Class.class.getDeclaredMethod("getDeclaredField", String.class).invoke(View.class, "mRecreateDisplayList");
                Method method = Z0.B;
                if (method != null) {
                    method.setAccessible(true);
                }
                Field field = Z0.f2152C;
                if (field != null) {
                    field.setAccessible(true);
                }
            }
            Field field2 = Z0.f2152C;
            if (field2 != null) {
                field2.setBoolean(view, true);
            }
            Method method2 = Z0.B;
            if (method2 != null) {
                method2.invoke(view, null);
            }
        } catch (Throwable unused) {
            Z0.f2154E = true;
        }
    }

    public static final boolean i(L0.o oVar) {
        L0.j jVarI = oVar.i();
        return !jVarI.l.containsKey(L0.r.f3947i);
    }

    public static final boolean l(L0.o oVar) {
        return oVar.c.f1465D == a1.k.f11038m;
    }

    public static final boolean m(Object obj) {
        if (obj instanceof InterfaceC1176p) {
            InterfaceC1176p interfaceC1176p = (InterfaceC1176p) obj;
            if (interfaceC1176p.f() != T.S.f8222n && interfaceC1176p.f() != T.S.f8224q && interfaceC1176p.f() != T.S.o) {
                return false;
            }
            Object value = interfaceC1176p.getValue();
            if (value == null) {
                return true;
            }
            return m(value);
        }
        if ((obj instanceof m7.c) && (obj instanceof Serializable)) {
            return false;
        }
        Class[] clsArr = f2110a;
        for (int i7 = 0; i7 < 7; i7++) {
            if (clsArr[i7].isInstance(obj)) {
                return true;
            }
        }
        return false;
    }

    public static final int n(float f10) {
        return ((int) (f10 >= 0.0f ? Math.ceil(f10) : Math.floor(f10))) * (-1);
    }

    public static final float o(int i7, int i10, float[] fArr, float[] fArr2) {
        int i11 = i7 * 4;
        return (fArr[i11 + 3] * fArr2[12 + i10]) + (fArr[i11 + 2] * fArr2[8 + i10]) + (fArr[i11 + 1] * fArr2[4 + i10]) + (fArr[i11] * fArr2[i10]);
    }

    public static final C2476p p(L0.p pVar) {
        L0.o oVarA = pVar.a();
        C2476p c2476p = AbstractC2469i.f22602a;
        C2476p c2476p2 = new C2476p();
        E0.F f10 = oVarA.c;
        if (f10.W() && f10.V()) {
            m0.d dVarE = oVarA.e();
            q(new Region(Math.round(dVarE.f18882a), Math.round(dVarE.f18883b), Math.round(dVarE.c), Math.round(dVarE.d)), oVarA, c2476p2, oVarA, new Region());
        }
        return c2476p2;
    }

    public static final void q(Region region, L0.o oVar, C2476p c2476p, L0.o oVar2, Region region2) {
        E0.F f10;
        InterfaceC0163m interfaceC0163mG;
        boolean zW = oVar2.c.W();
        E0.F f11 = oVar2.c;
        boolean z6 = (zW && f11.V()) ? false : true;
        boolean zIsEmpty = region.isEmpty();
        int i7 = oVar.f3920g;
        int i10 = oVar2.f3920g;
        if (!zIsEmpty || i10 == i7) {
            if (!z6 || oVar2.f3918e) {
                L0.j jVar = oVar2.d;
                boolean z10 = jVar.f3909m;
                InterfaceC0163m interfaceC0163m = oVar2.f3916a;
                if (z10 && (interfaceC0163mG = AbstractC0904a.G(f11)) != null) {
                    interfaceC0163m = interfaceC0163mG;
                }
                g0.n nVar = ((g0.n) interfaceC0163m).l;
                Object obj = jVar.l.get(L0.i.f3888b);
                if (obj == null) {
                    obj = null;
                }
                boolean z11 = obj != null;
                boolean z12 = nVar.l.f14695x;
                m0.d dVar = m0.d.f18881e;
                if (z12) {
                    if (z11) {
                        E0.d0 d0VarR = AbstractC0156f.r(nVar, 8);
                        if (d0VarR.Q0().f14695x) {
                            C0.r rVarH = C0.c0.h(d0VarR);
                            m0.b bVar = d0VarR.f1641J;
                            if (bVar == null) {
                                bVar = new m0.b();
                                bVar.f18878a = 0.0f;
                                bVar.f18879b = 0.0f;
                                bVar.c = 0.0f;
                                bVar.d = 0.0f;
                                d0VarR.f1641J = bVar;
                            }
                            long jG0 = d0VarR.G0(d0VarR.P0());
                            bVar.f18878a = -m0.f.d(jG0);
                            bVar.f18879b = -m0.f.b(jG0);
                            bVar.c = m0.f.d(jG0) + d0VarR.h0();
                            bVar.d = m0.f.b(jG0) + d0VarR.f0();
                            while (true) {
                                if (d0VarR == rVarH) {
                                    dVar = new m0.d(bVar.f18878a, bVar.f18879b, bVar.c, bVar.d);
                                    break;
                                }
                                d0VarR.e1(bVar, false, true);
                                if (bVar.b()) {
                                    break;
                                }
                                d0VarR = d0VarR.f1650y;
                                A7.m.c(d0VarR);
                            }
                        }
                    } else {
                        E0.d0 d0VarR2 = AbstractC0156f.r(nVar, 8);
                        dVar = C0.c0.h(d0VarR2).o(d0VarR2, true);
                    }
                }
                int iRound = Math.round(dVar.f18882a);
                int iRound2 = Math.round(dVar.f18883b);
                int iRound3 = Math.round(dVar.c);
                int iRound4 = Math.round(dVar.d);
                region2.set(iRound, iRound2, iRound3, iRound4);
                if (i10 == i7) {
                    i10 = -1;
                }
                if (!region2.op(region, Region.Op.INTERSECT)) {
                    if (oVar2.f3918e) {
                        L0.o oVarJ = oVar2.j();
                        m0.d dVarE = (oVarJ == null || (f10 = oVarJ.c) == null || !f10.W()) ? f2111b : oVarJ.e();
                        c2476p.i(i10, new Q0(oVar2, new Rect(Math.round(dVarE.f18882a), Math.round(dVarE.f18883b), Math.round(dVarE.c), Math.round(dVarE.d))));
                        return;
                    } else {
                        if (i10 == -1) {
                            c2476p.i(i10, new Q0(oVar2, region2.getBounds()));
                            return;
                        }
                        return;
                    }
                }
                c2476p.i(i10, new Q0(oVar2, region2.getBounds()));
                List listH = L0.o.h(oVar2, true, 4);
                for (int size = listH.size() - 1; -1 < size; size--) {
                    q(region, oVar, c2476p, (L0.o) listH.get(size), region2);
                }
                if (t(oVar2)) {
                    region.op(iRound, iRound2, iRound3, iRound4, Region.Op.DIFFERENCE);
                }
            }
        }
    }

    public static final N0.H r(L0.j jVar) {
        InterfaceC2782k interfaceC2782k;
        ArrayList arrayList = new ArrayList();
        Object obj = jVar.l.get(L0.i.f3887a);
        if (obj == null) {
            obj = null;
        }
        L0.a aVar = (L0.a) obj;
        if (aVar == null || (interfaceC2782k = (InterfaceC2782k) aVar.f3876b) == null || !((Boolean) interfaceC2782k.b(arrayList)).booleanValue()) {
            return null;
        }
        return (N0.H) arrayList.get(0);
    }

    public static final boolean s(float[] fArr, float[] fArr2) {
        float f10 = fArr[0];
        float f11 = fArr[1];
        float f12 = fArr[2];
        float f13 = fArr[3];
        float f14 = fArr[4];
        float f15 = fArr[5];
        float f16 = fArr[6];
        float f17 = fArr[7];
        float f18 = fArr[8];
        float f19 = fArr[9];
        float f20 = fArr[10];
        float f21 = fArr[11];
        float f22 = fArr[12];
        float f23 = fArr[13];
        float f24 = fArr[14];
        float f25 = fArr[15];
        float f26 = (f10 * f15) - (f11 * f14);
        float f27 = (f10 * f16) - (f12 * f14);
        float f28 = (f10 * f17) - (f13 * f14);
        float f29 = (f11 * f16) - (f12 * f15);
        float f30 = (f11 * f17) - (f13 * f15);
        float f31 = (f12 * f17) - (f13 * f16);
        float f32 = (f18 * f23) - (f19 * f22);
        float f33 = (f18 * f24) - (f20 * f22);
        float f34 = (f18 * f25) - (f21 * f22);
        float f35 = (f19 * f24) - (f20 * f23);
        float f36 = (f19 * f25) - (f21 * f23);
        float f37 = (f20 * f25) - (f21 * f24);
        float f38 = (f31 * f32) + (((f29 * f34) + ((f28 * f35) + ((f26 * f37) - (f27 * f36)))) - (f30 * f33));
        if (f38 == 0.0f) {
            return false;
        }
        float f39 = 1.0f / f38;
        fArr2[0] = ((f17 * f35) + ((f15 * f37) - (f16 * f36))) * f39;
        fArr2[1] = (((f12 * f36) + ((-f11) * f37)) - (f13 * f35)) * f39;
        fArr2[2] = ((f25 * f29) + ((f23 * f31) - (f24 * f30))) * f39;
        fArr2[3] = (((f20 * f30) + ((-f19) * f31)) - (f21 * f29)) * f39;
        float f40 = -f14;
        fArr2[4] = (((f16 * f34) + (f40 * f37)) - (f17 * f33)) * f39;
        fArr2[5] = ((f13 * f33) + ((f37 * f10) - (f12 * f34))) * f39;
        float f41 = -f22;
        fArr2[6] = (((f24 * f28) + (f41 * f31)) - (f25 * f27)) * f39;
        fArr2[7] = ((f21 * f27) + ((f31 * f18) - (f20 * f28))) * f39;
        fArr2[8] = ((f17 * f32) + ((f14 * f36) - (f15 * f34))) * f39;
        fArr2[9] = (((f34 * f11) + ((-f10) * f36)) - (f13 * f32)) * f39;
        fArr2[10] = ((f25 * f26) + ((f22 * f30) - (f23 * f28))) * f39;
        fArr2[11] = (((f28 * f19) + ((-f18) * f30)) - (f21 * f26)) * f39;
        fArr2[12] = (((f15 * f33) + (f40 * f35)) - (f16 * f32)) * f39;
        fArr2[13] = ((f12 * f32) + ((f10 * f35) - (f11 * f33))) * f39;
        fArr2[14] = (((f23 * f27) + (f41 * f29)) - (f24 * f26)) * f39;
        fArr2[15] = ((f20 * f26) + ((f18 * f29) - (f19 * f27))) * f39;
        return true;
    }

    public static final boolean t(L0.o oVar) {
        if (w(oVar)) {
            L0.j jVar = oVar.d;
            if (!jVar.f3909m) {
                Set setKeySet = jVar.l.keySet();
                if (!(setKeySet instanceof Collection) || !setKeySet.isEmpty()) {
                    Iterator it = setKeySet.iterator();
                    while (it.hasNext()) {
                        if (((L0.u) it.next()).c) {
                        }
                    }
                }
            }
            return true;
        }
        return false;
    }

    public static final boolean u(AbstractC1858H abstractC1858H, float f10, float f11, InterfaceC1859I interfaceC1859I, InterfaceC1859I interfaceC1859I2) {
        boolean zX;
        if (!(abstractC1858H instanceof C1856F)) {
            if (!(abstractC1858H instanceof C1857G)) {
                if (abstractC1858H instanceof C1855E) {
                    return v(((C1855E) abstractC1858H).f19375a, f10, f11, interfaceC1859I, interfaceC1859I2);
                }
                throw new A9.k();
            }
            m0.e eVar = ((C1857G) abstractC1858H).f19377a;
            float f12 = eVar.f18884a;
            if (f10 < f12) {
                return false;
            }
            float f13 = eVar.c;
            if (f10 >= f13) {
                return false;
            }
            float f14 = eVar.f18885b;
            if (f11 < f14) {
                return false;
            }
            float f15 = eVar.d;
            if (f11 >= f15) {
                return false;
            }
            long j3 = eVar.f18886e;
            float fB = AbstractC1792a.b(j3);
            long j10 = eVar.f18887f;
            if (AbstractC1792a.b(j10) + fB <= eVar.b()) {
                long j11 = eVar.f18889h;
                float fB2 = AbstractC1792a.b(j11);
                long j12 = eVar.f18888g;
                if (AbstractC1792a.b(j12) + fB2 <= eVar.b()) {
                    if (AbstractC1792a.c(j11) + AbstractC1792a.c(j3) <= eVar.a()) {
                        if (AbstractC1792a.c(j12) + AbstractC1792a.c(j10) <= eVar.a()) {
                            float fB3 = AbstractC1792a.b(j3) + f12;
                            float fC = AbstractC1792a.c(j3) + f14;
                            float fB4 = f13 - AbstractC1792a.b(j10);
                            float fC2 = f14 + AbstractC1792a.c(j10);
                            float fB5 = f13 - AbstractC1792a.b(j12);
                            float fC3 = f15 - AbstractC1792a.c(j12);
                            float fC4 = f15 - AbstractC1792a.c(j11);
                            float fB6 = AbstractC1792a.b(j11) + f12;
                            if (f10 < fB3 && f11 < fC) {
                                zX = x(f10, f11, eVar.f18886e, fB3, fC);
                            } else if (f10 < fB6 && f11 > fC4) {
                                zX = x(f10, f11, eVar.f18889h, fB6, fC4);
                            } else if (f10 > fB4 && f11 < fC2) {
                                zX = x(f10, f11, eVar.f18887f, fB4, fC2);
                            } else if (f10 > fB5 && f11 > fC3) {
                                zX = x(f10, f11, eVar.f18888g, fB5, fC3);
                            }
                            return zX;
                        }
                    }
                }
            }
            InterfaceC1859I interfaceC1859II = interfaceC1859I2 == null ? AbstractC1861K.i() : interfaceC1859I2;
            InterfaceC1859I.a(interfaceC1859II, eVar);
            return v(interfaceC1859II, f10, f11, interfaceC1859I, interfaceC1859I2);
        }
        m0.d dVar = ((C1856F) abstractC1858H).f19376a;
        if (dVar.f18882a > f10 || f10 >= dVar.c || dVar.f18883b > f11 || f11 >= dVar.d) {
            return false;
        }
        return true;
    }

    public static final boolean v(InterfaceC1859I interfaceC1859I, float f10, float f11, InterfaceC1859I interfaceC1859I2, InterfaceC1859I interfaceC1859I3) {
        m0.d dVar = new m0.d(f10 - 0.005f, f11 - 0.005f, f10 + 0.005f, f11 + 0.005f);
        if (interfaceC1859I2 == null) {
            interfaceC1859I2 = AbstractC1861K.i();
        }
        InterfaceC1859I.b(interfaceC1859I2, dVar);
        if (interfaceC1859I3 == null) {
            interfaceC1859I3 = AbstractC1861K.i();
        }
        C1880i c1880i = (C1880i) interfaceC1859I3;
        c1880i.d(interfaceC1859I, interfaceC1859I2, 1);
        boolean zIsEmpty = c1880i.f19436a.isEmpty();
        c1880i.e();
        ((C1880i) interfaceC1859I2).e();
        return !zIsEmpty;
    }

    public static final boolean w(L0.o oVar) {
        E0.d0 d0VarC = oVar.c();
        if (d0VarC != null ? d0VarC.X0() : false) {
            return false;
        }
        return !oVar.d.l.containsKey(L0.r.f3950m);
    }

    public static final boolean x(float f10, float f11, long j3, float f12, float f13) {
        float f14 = f10 - f12;
        float f15 = f11 - f13;
        float fB = AbstractC1792a.b(j3);
        float fC = AbstractC1792a.c(j3);
        return ((f15 * f15) / (fC * fC)) + ((f14 * f14) / (fB * fB)) <= 1.0f;
    }

    public static final d1.i y(C0218n0 c0218n0, int i7) {
        Object next;
        Iterator<T> it = c0218n0.getLayoutNodeToHolder().entrySet().iterator();
        while (true) {
            if (!it.hasNext()) {
                next = null;
                break;
            }
            next = it.next();
            if (((E0.F) ((Map.Entry) next).getKey()).f1481m == i7) {
                break;
            }
        }
        Map.Entry entry = (Map.Entry) next;
        if (entry != null) {
            return (d1.i) entry.getValue();
        }
        return null;
    }

    public static final String z(Object obj) {
        return (obj.getClass().isAnonymousClass() ? obj.getClass().getName() : obj.getClass().getSimpleName()) + '@' + String.format("%07x", Arrays.copyOf(new Object[]{Integer.valueOf(System.identityHashCode(obj))}, 1));
    }
}
