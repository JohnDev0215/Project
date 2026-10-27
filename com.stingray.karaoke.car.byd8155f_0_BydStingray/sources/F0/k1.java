package F0;

import S8.AbstractC0727y;
import T.C0732b0;
import V8.InterfaceC0818g;
import W8.C0841a;
import X5.C0886u;
import android.os.Build;
import android.view.View;
import com.stingray.karaoke.scoring.MicrophoneCommand;
import com.stingray.karaoke.scoring.NoteResult;
import java.util.ArrayList;
import java.util.Iterator;
import k6.C1699g;
import n6.C1960A;
import n6.C1967H;
import n6.C1972e;
import n7.AbstractC1998m;
import n7.AbstractC1999n;
import q7.InterfaceC2189c;
import r7.EnumC2303a;
import t6.C2445f;
import v6.C2555c;
import v6.C2557e;
import v6.C2558f;
import v6.C2559g;
import x3.AbstractC2673a;
import z7.InterfaceC2772a;

/* JADX INFO: loaded from: classes.dex */
public final class k1 implements InterfaceC0818g {
    public final /* synthetic */ int l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final /* synthetic */ Object f2218m;

    public /* synthetic */ k1(Object obj, int i7) {
        this.l = i7;
        this.f2218m = obj;
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // V8.InterfaceC0818g
    public final Object f(Object obj, InterfaceC2189c interfaceC2189c) throws Throwable {
        Object objB;
        Object obj2;
        w6.b bVar;
        Iterator it;
        C2559g c2559g;
        C2559g c2559g2;
        C2559g c2559g3;
        w6.b bVar2;
        switch (this.l) {
            case 0:
                ((F0) this.f2218m).l.j(((Number) obj).floatValue());
                return m7.q.f19267a;
            case 1:
                M.t tVar = (M.t) this.f2218m;
                if (Build.VERSION.SDK_INT >= 34) {
                    M.i.f4142a.a(tVar.G(), (View) tVar.l);
                }
                return m7.q.f19267a;
            case 2:
                N1.Q q4 = (N1.Q) this.f2218m;
                boolean z6 = q4.f4492r.a() instanceof N1.T;
                m7.q qVar = m7.q.f19267a;
                return (z6 || (objB = N1.Q.b(q4, true, interfaceC2189c)) != EnumC2303a.l) ? qVar : objB;
            case 3:
                ((P5.p) this.f2218m).r(0, 0, true);
                return m7.q.f19267a;
            case 4:
                if (((Boolean) obj).booleanValue()) {
                    ((InterfaceC2772a) this.f2218m).a();
                }
                return m7.q.f19267a;
            case 5:
                C.j jVar = (C.j) obj;
                boolean z10 = jVar instanceof C.b;
                T.X x8 = (T.X) this.f2218m;
                if (z10) {
                    x8.setValue(Boolean.TRUE);
                } else if ((jVar instanceof C.c) || (jVar instanceof C.a)) {
                    x8.setValue(Boolean.FALSE);
                }
                return m7.q.f19267a;
            case 6:
                ((A7.z) this.f2218m).l = obj;
                throw new C0841a(this);
            case 7:
                C0886u c0886u = (C0886u) this.f2218m;
                if (((Boolean) c0886u.f10384a.f4655e0.getValue()).booleanValue()) {
                    c0886u.b();
                }
                return m7.q.f19267a;
            case 8:
                androidx.lifecycle.I i7 = (androidx.lifecycle.I) this.f2218m;
                i7.getClass();
                Object objF = AbstractC0727y.F(i7.f11924b, new androidx.lifecycle.H(i7, obj, null), interfaceC2189c);
                EnumC2303a enumC2303a = EnumC2303a.l;
                m7.q qVar2 = m7.q.f19267a;
                if (objF != enumC2303a) {
                    objF = qVar2;
                }
                return objF == enumC2303a ? objF : qVar2;
            case 9:
                j6.r rVar = (j6.r) obj;
                boolean z11 = rVar instanceof j6.o;
                b6.i iVar = (b6.i) this.f2218m;
                if (z11) {
                    iVar.f12245m.b(((j6.o) rVar).f17061a);
                } else if (rVar instanceof j6.p) {
                    iVar.f12246n.b(((j6.p) rVar).f17062a);
                } else {
                    if (!(rVar instanceof j6.q)) {
                        throw new A9.k();
                    }
                    iVar.o.b(((j6.q) rVar).f17063a);
                }
                return m7.q.f19267a;
            case 10:
                ((C1699g) this.f2218m).d = (String) obj;
                return m7.q.f19267a;
            case 11:
                m7.i iVar2 = (m7.i) obj;
                C1972e c1972e = (C1972e) this.f2218m;
                c1972e.getClass();
                if (iVar2.l != null && (obj2 = iVar2.f19259m) != null) {
                    x6.e eVar = new x6.e();
                    C2445f c2445f = c1972e.f20060e;
                    c2445f.getClass();
                    c2445f.f22483a = eVar;
                    t6.h hVar = (t6.h) iVar2.l;
                    if (hVar != null) {
                        c2445f.b(hVar, 8);
                    }
                    n6.w wVar = (n6.w) obj2;
                    n6.x xVar = c1972e.d;
                    C2558f c2558f = null;
                    xVar.c = null;
                    int i10 = wVar.f20142g;
                    xVar.f20145a = i10;
                    int i11 = wVar.f20143h;
                    xVar.f20146b = i11;
                    xVar.d = new G7.a(i10, i11);
                    int i12 = i11 - i10;
                    if (i12 < 24) {
                        int i13 = 24 - i12;
                        int i14 = i13 / 2;
                        xVar.f20145a = i10 - i14;
                        int i15 = i11 + i14;
                        xVar.f20146b = i15;
                        if (i13 % 2 != 0) {
                            xVar.f20146b = i15 + 1;
                        }
                    } else if (i12 % 2 != 0) {
                        xVar.f20146b = i11 + 1;
                    }
                    ArrayList arrayList = wVar.f20138a;
                    Iterator it2 = arrayList.iterator();
                    while (true) {
                        boolean zHasNext = it2.hasNext();
                        float f10 = wVar.f20141f;
                        int i16 = wVar.f20140e;
                        int size = 0;
                        if (zHasNext) {
                            C1960A c1960a = (C1960A) it2.next();
                            float f11 = (i16 * f10) / 60.0f;
                            float f12 = (c1960a.f19976a / f11) * 1000.0f;
                            long j3 = c1960a.f19977b;
                            float f13 = (j3 / f11) * 1000.0f;
                            c2445f.f22484b = (int) f12;
                            C2555c c2555cC = c2445f.c();
                            C2558f c2558f2 = (c2555cC == null || (bVar2 = c2555cC.f23025q) == null) ? c2558f : (C2558f) bVar2.get(0);
                            if (c2558f2 != null) {
                                Iterator it3 = c2558f2.o.l.iterator();
                                float f14 = 0.0f;
                                float f15 = Float.MAX_VALUE;
                                Object obj3 = c2558f;
                                Object obj4 = obj3;
                                while (it3.hasNext()) {
                                    C2557e c2557e = (C2557e) it3.next();
                                    if (((Boolean) c2557e.l.l).booleanValue()) {
                                        Iterator it4 = c2557e.f23030p.l.iterator();
                                        while (it4.hasNext()) {
                                            C2559g c2559g4 = (C2559g) it4.next();
                                            Iterator it5 = it4;
                                            float fMax = Math.max(f12, c2559g4.f23035m);
                                            Iterator it6 = it2;
                                            int i17 = c2559g4.l;
                                            Iterator it7 = it3;
                                            int i18 = c2559g4.f23035m;
                                            float fMin = Math.min(f13, i17 + i18) - fMax;
                                            if (fMin > f14) {
                                                f14 = fMin;
                                                obj3 = c2559g4;
                                            }
                                            float fAbs = Math.abs(i18 - f12);
                                            if (fAbs < f15) {
                                                f15 = fAbs;
                                                obj4 = c2559g4;
                                                it2 = it6;
                                                it3 = it7;
                                                it4 = it5;
                                            } else {
                                                it4 = it5;
                                                it2 = it6;
                                                it3 = it7;
                                            }
                                        }
                                    }
                                }
                                it = it2;
                                c2559g = obj3;
                                c2559g2 = obj4;
                            } else {
                                it = it2;
                                c2559g = 0;
                                c2559g2 = 0;
                            }
                            String strA = "";
                            if (c2559g != 0) {
                                C2559g c2559g5 = xVar.c;
                                if (c2559g5 == null || c2559g.f23035m != c2559g5.f23035m) {
                                    xVar.c = c2559g;
                                    strA = c2559g.a();
                                }
                            } else if (c2559g2 != 0 && ((c2559g3 = xVar.c) == null || c2559g2.f23035m != c2559g3.f23035m)) {
                                xVar.c = c2559g2;
                                strA = c2559g2.a();
                            }
                            A7.m.f(strA, "<set-?>");
                            c1960a.f19978e = strA;
                            c1960a.f19983j = xVar.a(c1960a.c);
                            long jY = C7.a.Y((33 / 1000.0f) * f11);
                            NoteResult noteResult = NoteResult.UNKNOWN;
                            c1960a.f19981h = noteResult;
                            c1960a.f19984k.clear();
                            long j10 = c1960a.f19976a + jY;
                            if (j10 >= j3) {
                                c1960a.f19984k.add(new m7.i(Long.valueOf(j3), noteResult));
                            } else {
                                while (true) {
                                    if (j10 <= j3) {
                                        long j11 = j10 + jY;
                                        if (j11 > j3) {
                                            c1960a.f19984k.add(new m7.i(Long.valueOf(j3), NoteResult.UNKNOWN));
                                        } else {
                                            c1960a.f19984k.add(new m7.i(Long.valueOf(j10), NoteResult.UNKNOWN));
                                            j10 = j11;
                                        }
                                    }
                                }
                            }
                            it2 = it;
                            c2558f = null;
                        } else {
                            int size2 = arrayList.size();
                            c1972e.f20076w.setValue(Long.valueOf(C7.a.Y(r3 * ((float) ((((-2.0d) * ((double) (AbstractC2673a.u(size2 / r5, 1.0f, 5.0f) - 1.0f))) / ((double) 4.0f)) + 5.0d)))));
                            ArrayList arrayList2 = new ArrayList();
                            int i19 = (int) (wVar.f20144i / ((i16 * f10) / 60.0f));
                            int i20 = c2445f.f22484b;
                            c2445f.f22484b = 0;
                            if (i19 >= 0) {
                                int i21 = 0;
                                while (true) {
                                    C2555c c2555cC2 = c2445f.c();
                                    C2558f c2558f3 = (c2555cC2 == null || (bVar = c2555cC2.f23025q) == null) ? null : (C2558f) bVar.get(0);
                                    c2445f.f22484b += 1000;
                                    if (c2558f3 != null) {
                                        Iterator it8 = c2558f3.o.l.iterator();
                                        while (it8.hasNext()) {
                                            for (C2559g c2559g6 : ((C2557e) it8.next()).f23030p.l) {
                                                int i22 = c2559g6.f23035m;
                                                n6.I i23 = (n6.I) AbstractC1998m.i0(arrayList2);
                                                if (i22 > (i23 != null ? i23.f20025a : 0)) {
                                                    arrayList2.add(new n6.I(c2559g6, new A7.F(wVar, 10)));
                                                }
                                            }
                                        }
                                    }
                                    if (i21 != i19) {
                                        i21++;
                                    }
                                }
                            }
                            c2445f.f22484b = i20;
                            c1972e.f20077x = arrayList2;
                            int i24 = c1972e.f20065j;
                            int i25 = c1972e.f20066k;
                            C1967H c1967h = c1972e.f20059b;
                            int i26 = 0;
                            for (Object obj5 : c1967h.f20003a.a()) {
                                int i27 = i26 + 1;
                                if (i26 < 0) {
                                    AbstractC1999n.I();
                                    throw null;
                                }
                                C1960A c1960a2 = (C1960A) obj5;
                                int i28 = (i26 / i25) + 1;
                                if (i28 > i24) {
                                    i28 = i24;
                                }
                                size += c1960a2.f19984k.size() * i28 * 10;
                                i26 = i27;
                            }
                            V8.f0 f0Var = c1967h.f20011k;
                            Integer numValueOf = Integer.valueOf(size);
                            f0Var.getClass();
                            f0Var.m(null, numValueOf);
                            C0732b0 c0732b0 = c1972e.f20078y;
                            c0732b0.j(c0732b0.i() + 1);
                        }
                    }
                }
                return m7.q.f19267a;
            default:
                if (((Boolean) obj).booleanValue()) {
                    n6.u uVar = (n6.u) this.f2218m;
                    if (uVar.f20133i) {
                        uVar.f20128b.U(MicrophoneCommand.StartReceiving);
                    }
                }
                return m7.q.f19267a;
        }
    }
}
