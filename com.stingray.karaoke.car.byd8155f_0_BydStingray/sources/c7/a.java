package C7;

import A.C0013f0;
import A7.m;
import C0.C0102i;
import C0.K;
import D.AbstractC0127i;
import D.AbstractC0133o;
import D.AbstractC0139v;
import D.C0119a;
import D.C0124f;
import D.C0141x;
import D.S;
import D.T;
import E.D;
import E.G;
import E0.AbstractC0156f;
import E0.C0158h;
import E0.C0159i;
import E0.C0160j;
import E0.InterfaceC0161k;
import E0.InterfaceC0163m;
import E0.d0;
import F0.AbstractC0227s0;
import F0.C0221p;
import F0.C0223q;
import F0.U0;
import F0.r;
import F1.J;
import F8.A;
import F8.AbstractC0268c;
import F8.AbstractC0287w;
import F8.C0269d;
import F8.O;
import F8.W;
import F8.Y;
import H4.s;
import H7.H;
import H7.InterfaceC0318c;
import H7.InterfaceC0321f;
import H7.w;
import N0.C0406a;
import N0.C0411f;
import N1.C0420a;
import N6.f;
import N7.i;
import N7.n;
import N7.o;
import O7.d;
import O7.e;
import Q.c1;
import Q5.B2;
import Q5.C0549b;
import Q5.C0627u2;
import Q5.C0638x1;
import Q5.C0641y0;
import Q5.F0;
import Q5.G2;
import Q5.L;
import Q5.M;
import Q5.T0;
import Q5.T2;
import Q7.InterfaceC0652e;
import Q7.InterfaceC0654g;
import Q7.Q;
import R7.b;
import R7.h;
import R7.j;
import S0.InterfaceC0702n;
import S5.t;
import T.AbstractC0750k0;
import T.C0735d;
import T.C0753m;
import T.C0754m0;
import T.C0761q;
import T.C0773y;
import T.InterfaceC0744h0;
import T.X;
import U8.l;
import V8.N;
import X5.B;
import X5.C;
import X5.C0888v;
import X5.C0893z;
import X5.D0;
import X5.E;
import X5.F;
import a.AbstractC0904a;
import android.app.Activity;
import android.content.Context;
import android.content.res.Configuration;
import android.graphics.drawable.Drawable;
import android.view.View;
import androidx.compose.ui.platform.AndroidCompositionLocals_androidKt;
import androidx.lifecycle.InterfaceC0977o;
import androidx.lifecycle.f0;
import androidx.lifecycle.h0;
import androidx.lifecycle.k0;
import androidx.lifecycle.l0;
import b2.AbstractC0992b;
import b2.C0991a;
import b8.C1011f;
import com.google.accompanist.drawablepainter.DrawablePainter;
import com.stingray.drivemodedetector.DriveMode;
import com.stingray.drivemodedetector.R;
import com.stingray.karaoke.car.player.LeadVocalState;
import com.stingray.karaoke.car.player.PlayerStatus;
import com.stingray.karaoke.car.ui.player.PlayerSideMenuRoute;
import com.stingray.karaoke.car.ui.player.SideMenuType;
import com.stingray.karaoke.car.utils.FontSize;
import com.stingray.karaoke.car.utils.LayoutMode;
import com.stingray.kca.model.KaraokeItem;
import com.stingray.kca.model.KaraokeItemChildType;
import com.stingray.kca.model.KaraokeItemType;
import com.stingray.shared.qrcode.QRCodeGenerator$QrShape;
import d2.C1064b;
import g1.AbstractC1274a;
import h2.AbstractC1347d;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import k6.AbstractC1692D;
import l0.InterfaceC1716g;
import l3.P;
import m6.c;
import m7.q;
import n0.AbstractC1861K;
import n0.C1891t;
import n3.C1930E;
import n3.C1945j;
import n6.C1966G;
import n6.C1967H;
import n6.C1972e;
import n7.AbstractC1985B;
import n7.AbstractC1998m;
import n7.AbstractC1999n;
import n7.AbstractC2000o;
import n7.u;
import n7.v;
import n7.z;
import o8.C2058c;
import o8.C2060e;
import o8.C2061f;
import org.xmlpull.v1.XmlPullParserException;
import q7.InterfaceC2189c;
import r0.AbstractC2268a;
import r7.EnumC2303a;
import s7.AbstractC2410c;
import s8.InterfaceC2414b;
import t8.g;
import t8.k;
import v8.AbstractC2564d;
import x.AbstractC2641j;
import x3.AbstractC2673a;
import y0.C2722B;
import z7.InterfaceC2772a;
import z7.InterfaceC2782k;
import z7.InterfaceC2785n;

/* JADX INFO: loaded from: classes.dex */
public abstract class a {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final /* synthetic */ int f1014a = 0;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final /* synthetic */ int f1015b = 0;

    public static boolean A(l lVar) {
        return lVar.o(null, false);
    }

    public static final int B(AbstractC0287w abstractC0287w) {
        m.f(abstractC0287w, "<this>");
        b bVarE = abstractC0287w.k().e(n.f4858q);
        if (bVarE == null) {
            return 0;
        }
        g gVar = (g) z.o(o.d, bVarE.c());
        m.d(gVar, "null cannot be cast to non-null type org.jetbrains.kotlin.resolve.constants.IntValue");
        return ((Number) ((k) gVar).f22499a).intValue();
    }

    public static final A C(i iVar, h hVar, AbstractC0287w abstractC0287w, List list, ArrayList arrayList, AbstractC0287w abstractC0287w2, boolean z6) {
        InterfaceC0652e interfaceC0652eJ;
        int i7 = 0;
        ArrayList arrayList2 = new ArrayList(list.size() + arrayList.size() + (abstractC0287w != null ? 1 : 0) + 1);
        ArrayList arrayList3 = new ArrayList(AbstractC2000o.J(list, 10));
        Iterator it = list.iterator();
        while (it.hasNext()) {
            arrayList3.add(AbstractC1274a.w((AbstractC0287w) it.next()));
        }
        arrayList2.addAll(arrayList3);
        O8.l.a(arrayList2, abstractC0287w != null ? AbstractC1274a.w(abstractC0287w) : null);
        Iterator it2 = arrayList.iterator();
        int i10 = 0;
        while (true) {
            boolean zHasNext = it2.hasNext();
            h iVar2 = R7.g.f7535a;
            if (!zHasNext) {
                arrayList2.add(AbstractC1274a.w(abstractC0287w2));
                int size = list.size() + arrayList.size() + (abstractC0287w == null ? 0 : 1);
                if (z6) {
                    interfaceC0652eJ = iVar.u(size);
                } else {
                    C2061f c2061f = o.f4868a;
                    interfaceC0652eJ = iVar.j("Function" + size);
                }
                if (abstractC0287w != null) {
                    C2058c c2058c = n.f4857p;
                    if (!hVar.k(c2058c)) {
                        ArrayList arrayListM0 = AbstractC1998m.m0(hVar, new j(iVar, c2058c, v.l));
                        hVar = arrayListM0.isEmpty() ? iVar2 : new R7.i(i7, arrayListM0);
                    }
                }
                if (!list.isEmpty()) {
                    int size2 = list.size();
                    C2058c c2058c2 = n.f4858q;
                    if (!hVar.k(c2058c2)) {
                        ArrayList arrayListM02 = AbstractC1998m.m0(hVar, new j(iVar, c2058c2, z.r(new m7.i(o.d, new k(size2)))));
                        if (!arrayListM02.isEmpty()) {
                            iVar2 = new R7.i(i7, arrayListM02);
                        }
                        hVar = iVar2;
                    }
                }
                return C0269d.q(AbstractC0268c.w(hVar), interfaceC0652eJ, arrayList2);
            }
            Object next = it2.next();
            int i11 = i10 + 1;
            if (i10 < 0) {
                AbstractC1999n.I();
                throw null;
            }
            arrayList2.add(AbstractC1274a.w((AbstractC0287w) next));
            i10 = i11;
        }
    }

    public static f0 D(Class cls) throws InvocationTargetException {
        try {
            Object objNewInstance = cls.getDeclaredConstructor(null).newInstance(null);
            m.e(objNewInstance, "{\n            modelClass…).newInstance()\n        }");
            return (f0) objNewInstance;
        } catch (IllegalAccessException e10) {
            throw new RuntimeException("Cannot create an instance of " + cls, e10);
        } catch (InstantiationException e11) {
            throw new RuntimeException("Cannot create an instance of " + cls, e11);
        } catch (NoSuchMethodException e12) {
            throw new RuntimeException("Cannot create an instance of " + cls, e12);
        }
    }

    public static int E(int i7, int i10, boolean z6, String str) {
        while (i7 < i10) {
            char cCharAt = str.charAt(i7);
            if (((cCharAt < ' ' && cCharAt != '\t') || cCharAt >= 127 || ('0' <= cCharAt && cCharAt < ':') || (('a' <= cCharAt && cCharAt < '{') || (('A' <= cCharAt && cCharAt < '[') || cCharAt == ':'))) == (!z6)) {
                return i7;
            }
            i7++;
        }
        return i10;
    }

    public static final boolean F(int i7, int i10) {
        return i7 == i10;
    }

    public static final C2061f G(AbstractC0287w abstractC0287w) {
        String str;
        b bVarE = abstractC0287w.k().e(n.f4859r);
        if (bVarE == null) {
            return null;
        }
        Object objS0 = AbstractC1998m.s0(bVarE.c().values());
        t8.v vVar = objS0 instanceof t8.v ? (t8.v) objS0 : null;
        if (vVar != null && (str = (String) vVar.f22499a) != null) {
            if (!C2061f.f(str)) {
                str = null;
            }
            if (str != null) {
                return C2061f.e(str);
            }
        }
        return null;
    }

    /* JADX WARN: Code restructure failed: missing block: B:53:0x0047, code lost:
    
        if (r12 != r1) goto L18;
     */
    /* JADX WARN: Code restructure failed: missing block: B:54:0x0047, code lost:
    
        if (r12 != r1) goto L18;
     */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:34:0x008a  */
    /* JADX WARN: Removed duplicated region for block: B:35:0x008b  */
    /* JADX WARN: Removed duplicated region for block: B:36:0x008d A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:41:0x0099  */
    /* JADX WARN: Removed duplicated region for block: B:46:0x00b2 A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:47:0x00b3  */
    /* JADX WARN: Removed duplicated region for block: B:48:0x00b4 A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:49:0x0063 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:7:0x0013  */
    /* JADX WARN: Type inference failed for: r10v0, types: [y0.B] */
    /* JADX WARN: Type inference failed for: r10v1 */
    /* JADX WARN: Type inference failed for: r10v10, types: [q7.h] */
    /* JADX WARN: Type inference failed for: r10v12, types: [q7.h] */
    /* JADX WARN: Type inference failed for: r10v13 */
    /* JADX WARN: Type inference failed for: r10v14 */
    /* JADX WARN: Type inference failed for: r10v2, types: [q7.h] */
    /* JADX WARN: Type inference failed for: r10v3, types: [q7.h] */
    /* JADX WARN: Type inference failed for: r10v4, types: [java.lang.Object, y0.B] */
    /* JADX WARN: Type inference failed for: r10v5 */
    /* JADX WARN: Type inference failed for: r10v6 */
    /* JADX WARN: Type inference failed for: r10v7 */
    /* JADX WARN: Type inference failed for: r10v8 */
    /* JADX WARN: Type inference failed for: r11v0, types: [z7.n] */
    /* JADX WARN: Type inference failed for: r11v1 */
    /* JADX WARN: Type inference failed for: r11v10 */
    /* JADX WARN: Type inference failed for: r11v11 */
    /* JADX WARN: Type inference failed for: r11v12 */
    /* JADX WARN: Type inference failed for: r11v13 */
    /* JADX WARN: Type inference failed for: r11v2, types: [z7.n] */
    /* JADX WARN: Type inference failed for: r11v3, types: [z7.n] */
    /* JADX WARN: Type inference failed for: r11v4, types: [z7.n] */
    /* JADX WARN: Type inference failed for: r11v5 */
    /* JADX WARN: Type inference failed for: r11v7, types: [z7.n] */
    /* JADX WARN: Type inference failed for: r11v9, types: [z7.n] */
    /* JADX WARN: Type inference failed for: r12v10, types: [q7.h] */
    /* JADX WARN: Type inference failed for: r12v12 */
    /* JADX WARN: Type inference failed for: r12v14 */
    /* JADX WARN: Type inference failed for: r2v0, types: [int] */
    /* JADX WARN: Type inference failed for: r2v1 */
    /* JADX WARN: Type inference failed for: r2v11, types: [y0.B] */
    /* JADX WARN: Type inference failed for: r2v12, types: [y0.B] */
    /* JADX WARN: Type inference failed for: r2v13 */
    /* JADX WARN: Type inference failed for: r2v2, types: [y0.B] */
    /* JADX WARN: Type inference failed for: r2v3, types: [y0.B] */
    /* JADX WARN: Type inference failed for: r2v6 */
    /* JADX WARN: Type inference failed for: r2v8 */
    /* JADX WARN: Type inference failed for: r2v9 */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static final java.lang.Object H(y0.C2722B r10, z7.InterfaceC2785n r11, s7.AbstractC2410c r12) throws java.lang.Throwable {
        /*
            boolean r0 = r12 instanceof A.C0015g0
            if (r0 == 0) goto L13
            r0 = r12
            A.g0 r0 = (A.C0015g0) r0
            int r1 = r0.f179p
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L13
            int r1 = r1 - r2
            r0.f179p = r1
            goto L18
        L13:
            A.g0 r0 = new A.g0
            r0.<init>(r12)
        L18:
            java.lang.Object r12 = r0.o
            r7.a r1 = r7.EnumC2303a.l
            int r2 = r0.f179p
            r3 = 0
            m7.q r4 = m7.q.f19267a
            r5 = 3
            r6 = 2
            r7 = 1
            if (r2 == 0) goto L56
            if (r2 == r7) goto L4c
            if (r2 == r6) goto L3e
            if (r2 != r5) goto L36
            q7.h r10 = r0.f178n
            z7.n r11 = r0.f177m
            y0.B r2 = r0.l
            h8.AbstractC1386c.u(r12)
            goto L47
        L36:
            java.lang.IllegalStateException r10 = new java.lang.IllegalStateException
            java.lang.String r11 = "call to 'resume' before 'invoke' with coroutine"
            r10.<init>(r11)
            throw r10
        L3e:
            q7.h r10 = r0.f178n
            z7.n r11 = r0.f177m
            y0.B r2 = r0.l
            h8.AbstractC1386c.u(r12)     // Catch: java.util.concurrent.CancellationException -> L4a
        L47:
            r12 = r10
            r10 = r2
            goto L5d
        L4a:
            r12 = move-exception
            goto L93
        L4c:
            q7.h r10 = r0.f178n
            z7.n r11 = r0.f177m
            y0.B r2 = r0.l
            h8.AbstractC1386c.u(r12)     // Catch: java.util.concurrent.CancellationException -> L4a
            goto L74
        L56:
            h8.AbstractC1386c.u(r12)
            q7.h r12 = r0.getContext()
        L5d:
            boolean r2 = S8.AbstractC0727y.t(r12)
            if (r2 == 0) goto Lb4
            r0.l = r10     // Catch: java.util.concurrent.CancellationException -> L8e
            r0.f177m = r11     // Catch: java.util.concurrent.CancellationException -> L8e
            r0.f178n = r12     // Catch: java.util.concurrent.CancellationException -> L8e
            r0.f179p = r7     // Catch: java.util.concurrent.CancellationException -> L8e
            java.lang.Object r2 = r11.invoke(r10, r0)     // Catch: java.util.concurrent.CancellationException -> L8e
            if (r2 != r1) goto L72
            return r1
        L72:
            r2 = r10
            r10 = r12
        L74:
            r0.l = r2     // Catch: java.util.concurrent.CancellationException -> L4a
            r0.f177m = r11     // Catch: java.util.concurrent.CancellationException -> L4a
            r0.f178n = r10     // Catch: java.util.concurrent.CancellationException -> L4a
            r0.f179p = r6     // Catch: java.util.concurrent.CancellationException -> L4a
            A.d0 r12 = new A.d0     // Catch: java.util.concurrent.CancellationException -> L4a
            r12.<init>(r3)     // Catch: java.util.concurrent.CancellationException -> L4a
            java.lang.Object r12 = r2.H0(r12, r0)     // Catch: java.util.concurrent.CancellationException -> L4a
            r7.a r8 = r7.EnumC2303a.l     // Catch: java.util.concurrent.CancellationException -> L4a
            if (r12 != r8) goto L8a
            goto L8b
        L8a:
            r12 = r4
        L8b:
            if (r12 != r1) goto L47
            return r1
        L8e:
            r2 = move-exception
            r9 = r2
            r2 = r10
            r10 = r12
            r12 = r9
        L93:
            boolean r8 = S8.AbstractC0727y.t(r10)
            if (r8 == 0) goto Lb3
            r0.l = r2
            r0.f177m = r11
            r0.f178n = r10
            r0.f179p = r5
            A.d0 r12 = new A.d0
            r12.<init>(r3)
            java.lang.Object r12 = r2.H0(r12, r0)
            r7.a r8 = r7.EnumC2303a.l
            if (r12 != r8) goto Laf
            goto Lb0
        Laf:
            r12 = r4
        Lb0:
            if (r12 != r1) goto L47
            return r1
        Lb3:
            throw r12
        Lb4:
            return r4
        */
        throw new UnsupportedOperationException("Method not decompiled: C7.a.H(y0.B, z7.n, s7.c):java.lang.Object");
    }

    public static final f0 I(l0 l0Var, InterfaceC0318c interfaceC0318c, String str, h0 h0Var, AbstractC0992b abstractC0992b) {
        C0420a c0420a;
        m.f(l0Var, "<this>");
        m.f(interfaceC0318c, "modelClass");
        m.f(abstractC0992b, "extras");
        if (h0Var != null) {
            k0 k0VarF = l0Var.f();
            m.f(k0VarF, "store");
            c0420a = new C0420a(k0VarF, h0Var, abstractC0992b);
        } else {
            boolean z6 = l0Var instanceof InterfaceC0977o;
            if (z6) {
                k0 k0VarF2 = l0Var.f();
                h0 h0VarD = ((InterfaceC0977o) l0Var).d();
                m.f(k0VarF2, "store");
                m.f(h0VarD, "factory");
                c0420a = new C0420a(k0VarF2, h0VarD, abstractC0992b);
            } else {
                h0 h0VarD2 = z6 ? ((InterfaceC0977o) l0Var).d() : C1064b.f13334a;
                AbstractC0992b abstractC0992bE = z6 ? ((InterfaceC0977o) l0Var).e() : C0991a.f12202b;
                m.f(h0VarD2, "factory");
                m.f(abstractC0992bE, "extras");
                c0420a = new C0420a(l0Var.f(), h0VarD2, abstractC0992bE);
            }
        }
        if (str != null) {
            return ((P) c0420a.l).s(interfaceC0318c, str);
        }
        m.f(interfaceC0318c, "modelClass");
        String strA = interfaceC0318c.a();
        if (strA != null) {
            return ((P) c0420a.l).s(interfaceC0318c, "androidx.lifecycle.ViewModelProvider.DefaultKey:".concat(strA));
        }
        throw new IllegalArgumentException("Local and anonymous classes can not be ViewModels");
    }

    public static final List J(AbstractC0287w abstractC0287w) {
        m.f(abstractC0287w, "<this>");
        R(abstractC0287w);
        int iB = B(abstractC0287w);
        if (iB == 0) {
            return u.l;
        }
        List listSubList = abstractC0287w.o0().subList(0, iB);
        ArrayList arrayList = new ArrayList(AbstractC2000o.J(listSubList, 10));
        Iterator it = listSubList.iterator();
        while (it.hasNext()) {
            AbstractC0287w abstractC0287wB = ((O) it.next()).b();
            m.e(abstractC0287wB, "it.type");
            arrayList.add(abstractC0287wB);
        }
        return arrayList;
    }

    public static final e K(InterfaceC0654g interfaceC0654g) {
        if (!(interfaceC0654g instanceof InterfaceC0652e) || !i.G(interfaceC0654g)) {
            return null;
        }
        C2060e c2060eH = AbstractC2564d.h(interfaceC0654g);
        if (!c2060eH.d() || c2060eH.f20571a.isEmpty()) {
            return null;
        }
        io.sentry.hints.i iVar = e.f5181n;
        String strB = c2060eH.f().b();
        m.e(strB, "shortName().asString()");
        C2058c c2058cE = c2060eH.g().e();
        m.e(c2058cE, "toSafe().parent()");
        iVar.getClass();
        d dVarL = io.sentry.hints.i.l(strB, c2058cE);
        if (dVarL != null) {
            return dVarL.f5179a;
        }
        return null;
    }

    public static final J1.a L(View view) {
        J1.a aVar = (J1.a) view.getTag(R.id.pooling_container_listener_holder_tag);
        if (aVar != null) {
            return aVar;
        }
        J1.a aVar2 = new J1.a();
        view.setTag(R.id.pooling_container_listener_holder_tag, aVar2);
        return aVar2;
    }

    public static final AbstractC0287w M(AbstractC0287w abstractC0287w) {
        m.f(abstractC0287w, "<this>");
        R(abstractC0287w);
        if (abstractC0287w.k().e(n.f4857p) == null) {
            return null;
        }
        return ((O) abstractC0287w.o0().get(B(abstractC0287w))).b();
    }

    public static final Object N(E8.m mVar, w wVar) {
        m.f(mVar, "<this>");
        m.f(wVar, "p");
        return mVar.a();
    }

    public static final List O(AbstractC0287w abstractC0287w) {
        m.f(abstractC0287w, "<this>");
        R(abstractC0287w);
        List listO0 = abstractC0287w.o0();
        return listO0.subList(((!R(abstractC0287w) || abstractC0287w.k().e(n.f4857p) == null) ? 0 : 1) + B(abstractC0287w), listO0.size() - 1);
    }

    public static int P(long j3) {
        return (int) (j3 ^ (j3 >>> 32));
    }

    public static String Q(L8.e eVar, C1011f c1011f) {
        if (eVar.c(c1011f)) {
            return null;
        }
        return eVar.a();
    }

    public static final boolean R(AbstractC0287w abstractC0287w) {
        m.f(abstractC0287w, "<this>");
        InterfaceC0654g interfaceC0654gN = abstractC0287w.B0().n();
        if (interfaceC0654gN == null) {
            return false;
        }
        e eVarK = K(interfaceC0654gN);
        return eVarK == e.o || eVarK == e.f5182p;
    }

    public static final boolean S(AbstractC0287w abstractC0287w) {
        m.f(abstractC0287w, "<this>");
        InterfaceC0654g interfaceC0654gN = abstractC0287w.B0().n();
        return (interfaceC0654gN != null ? K(interfaceC0654gN) : null) == e.f5182p;
    }

    /* JADX WARN: Removed duplicated region for block: B:18:0x00a6  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static long T(int r14, java.lang.String r15) {
        /*
            Method dump skipped, instruction units count: 327
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: C7.a.T(int, java.lang.String):long");
    }

    /* JADX WARN: Removed duplicated region for block: B:35:0x008d  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static final F8.AbstractC0287w U(F8.AbstractC0287w r9, java.util.ArrayList r10) {
        /*
            java.util.List r0 = r9.o0()
            r0.size()
            r10.size()
            java.util.ArrayList r0 = new java.util.ArrayList
            r1 = 10
            int r1 = n7.AbstractC2000o.J(r10, r1)
            r0.<init>(r1)
            java.util.Iterator r10 = r10.iterator()
        L19:
            boolean r1 = r10.hasNext()
            r2 = 0
            if (r1 == 0) goto L96
            java.lang.Object r1 = r10.next()
            K8.d r1 = (K8.d) r1
            r1.getClass()
            G8.l r3 = G8.d.f2722a
            F8.w r4 = r1.f3862b
            F8.w r5 = r1.c
            r3.b(r4, r5)
            boolean r3 = A7.m.a(r4, r5)
            if (r3 != 0) goto L8d
            Q7.Q r1 = r1.f3861a
            int r3 = r1.e0()
            r6 = 2
            if (r3 != r6) goto L42
            goto L8d
        L42:
            boolean r3 = N7.i.D(r4)
            r7 = 1
            r8 = 3
            if (r3 == 0) goto L5e
            int r3 = r1.e0()
            if (r3 == r6) goto L5e
            F8.P r2 = new F8.P
            int r1 = r1.e0()
            if (r8 != r1) goto L59
            goto L5a
        L59:
            r7 = r8
        L5a:
            r2.<init>(r7, r5)
            goto L92
        L5e:
            if (r5 == 0) goto L87
            boolean r2 = N7.i.w(r5)
            if (r2 == 0) goto L79
            boolean r2 = r5.F0()
            if (r2 == 0) goto L79
            F8.P r2 = new F8.P
            int r1 = r1.e0()
            if (r6 != r1) goto L75
            r6 = r7
        L75:
            r2.<init>(r6, r4)
            goto L92
        L79:
            F8.P r2 = new F8.P
            int r1 = r1.e0()
            if (r8 != r1) goto L82
            goto L83
        L82:
            r7 = r8
        L83:
            r2.<init>(r7, r5)
            goto L92
        L87:
            r9 = 140(0x8c, float:1.96E-43)
            N7.i.a(r9)
            throw r2
        L8d:
            F8.P r2 = new F8.P
            r2.<init>(r4)
        L92:
            r0.add(r2)
            goto L19
        L96:
            r10 = 6
            F8.w r9 = F8.AbstractC0268c.o(r9, r0, r2, r10)
            return r9
        */
        throw new UnsupportedOperationException("Method not decompiled: C7.a.U(F8.w, java.util.ArrayList):F8.w");
    }

    public static int V(double d) {
        if (Double.isNaN(d)) {
            throw new IllegalArgumentException("Cannot round NaN value.");
        }
        if (d > 2.147483647E9d) {
            return Integer.MAX_VALUE;
        }
        if (d < -2.147483648E9d) {
            return Integer.MIN_VALUE;
        }
        return (int) Math.round(d);
    }

    public static int W(float f10) {
        if (Float.isNaN(f10)) {
            throw new IllegalArgumentException("Cannot round NaN value.");
        }
        return Math.round(f10);
    }

    public static final a1.i X(m0.d dVar) {
        return new a1.i(Math.round(dVar.f18882a), Math.round(dVar.f18883b), Math.round(dVar.c), Math.round(dVar.d));
    }

    public static long Y(double d) {
        if (Double.isNaN(d)) {
            throw new IllegalArgumentException("Cannot round NaN value.");
        }
        return Math.round(d);
    }

    public static final Object Z(InterfaceC0163m interfaceC0163m, m0.d dVar, AbstractC2410c abstractC2410c) {
        H.a aVar;
        Object objK0;
        boolean z6 = ((g0.n) interfaceC0163m).l.f14695x;
        q qVar = q.f19267a;
        if (!z6) {
            return qVar;
        }
        d0 d0VarS = AbstractC0156f.s(interfaceC0163m);
        if (((g0.n) interfaceC0163m).l.f14695x) {
            H.a jVar = (H.a) AbstractC0156f.j(interfaceC0163m, H.i.f2761A);
            if (jVar == null) {
                jVar = new H.j(interfaceC0163m);
            }
            aVar = jVar;
        } else {
            aVar = null;
        }
        return (aVar != null && (objK0 = aVar.k0(d0VarS, new B8.A(dVar, 11, d0VarS), abstractC2410c)) == EnumC2303a.l) ? objK0 : qVar;
    }

    public static final void a(final g0.o oVar, final C0411f c0411f, float f10, final String str, final InterfaceC2772a interfaceC2772a, final boolean z6, final boolean z10, C0761q c0761q, final int i7) {
        int i10;
        int i11;
        float F9;
        float f11;
        m.f(str, "parentId");
        c0761q.S(-366956779);
        if ((i7 & 6) == 0) {
            i10 = (c0761q.f(oVar) ? 4 : 2) | i7;
        } else {
            i10 = i7;
        }
        if ((i7 & 48) == 0) {
            i10 |= c0761q.f(c0411f) ? 32 : 16;
        }
        if ((i7 & 384) == 0) {
            i10 |= 128;
        }
        if ((i7 & 3072) == 0) {
            i10 |= c0761q.f(str) ? 2048 : 1024;
        }
        if ((i7 & 24576) == 0) {
            i10 |= c0761q.h(interfaceC2772a) ? 16384 : 8192;
        }
        if ((196608 & i7) == 0) {
            i10 |= c0761q.g(z6) ? 131072 : 65536;
        }
        if ((1572864 & i7) == 0) {
            i10 |= c0761q.g(z10) ? 1048576 : 524288;
        }
        if ((599187 & i10) == 599186 && c0761q.x()) {
            c0761q.K();
            f11 = f10;
        } else {
            c0761q.M();
            if ((i7 & 1) == 0 || c0761q.w()) {
                i11 = i10 & (-897);
                F9 = c.f19252a.F(c0761q);
            } else {
                c0761q.K();
                i11 = i10 & (-897);
                F9 = f10;
            }
            c0761q.q();
            c0761q.Q(1069742524);
            ArrayList arrayList = new ArrayList();
            int iHashCode = str.hashCode();
            if (iHashCode != 935580174) {
                if (iHashCode != 1169542268) {
                    if (iHashCode == 1770444560 && str.equals("{\"asset_group\":\"TopScores\"}")) {
                        arrayList.add(new T2("Empty_".concat(str), 1, L.c));
                    }
                } else if (str.equals("{\"asset_group\":\"FavoriteSongs\"}")) {
                    arrayList.add(new T2("Empty_".concat(str), 1, L.f6429a));
                }
            } else if (str.equals("{\"asset_group\":\"History\"}")) {
                arrayList.add(new T2("Empty_".concat(str), 1, L.f6430b));
            }
            c0761q.p(false);
            int i12 = i11 & 126;
            int i13 = i11 << 3;
            r(oVar, c0411f, null, F9, arrayList, interfaceC2772a, z6, z10, c0761q, i12 | (458752 & i13) | (3670016 & i13) | (i13 & 29360128), 4);
            f11 = F9;
        }
        C0754m0 c0754m0R = c0761q.r();
        if (c0754m0R != null) {
            final float f12 = f11;
            c0754m0R.d = new InterfaceC2785n() { // from class: Q5.x2
                @Override // z7.InterfaceC2785n
                public final Object invoke(Object obj, Object obj2) {
                    ((Integer) obj2).getClass();
                    int iZ = C0735d.Z(i7 | 1);
                    C0411f c0411f2 = c0411f;
                    boolean z11 = z6;
                    boolean z12 = z10;
                    C7.a.a(oVar, c0411f2, f12, str, interfaceC2772a, z11, z12, (C0761q) obj, iZ);
                    return m7.q.f19267a;
                }
            };
        }
    }

    public static final S1.d a0(String str) {
        m.f(str, "name");
        return new S1.d(str);
    }

    /* JADX WARN: Removed duplicated region for block: B:37:0x006d  */
    /* JADX WARN: Removed duplicated region for block: B:40:0x0073  */
    /* JADX WARN: Removed duplicated region for block: B:49:0x008c  */
    /* JADX WARN: Removed duplicated region for block: B:53:0x009b  */
    /* JADX WARN: Removed duplicated region for block: B:55:0x009e  */
    /* JADX WARN: Removed duplicated region for block: B:56:0x00a1  */
    /* JADX WARN: Removed duplicated region for block: B:58:0x00a5  */
    /* JADX WARN: Removed duplicated region for block: B:59:0x00a8  */
    /* JADX WARN: Removed duplicated region for block: B:62:0x00d1  */
    /* JADX WARN: Removed duplicated region for block: B:63:0x00d5  */
    /* JADX WARN: Removed duplicated region for block: B:68:0x00f6  */
    /* JADX WARN: Removed duplicated region for block: B:71:0x0135  */
    /* JADX WARN: Removed duplicated region for block: B:72:0x0139  */
    /* JADX WARN: Removed duplicated region for block: B:77:0x0154  */
    /* JADX WARN: Removed duplicated region for block: B:82:0x0281  */
    /* JADX WARN: Removed duplicated region for block: B:86:0x0291  */
    /* JADX WARN: Removed duplicated region for block: B:88:? A[RETURN, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static final void b(final g0.o r38, final java.lang.String r39, final java.lang.String r40, java.lang.String r41, z7.InterfaceC2772a r42, T.C0761q r43, final int r44, final int r45) {
        /*
            Method dump skipped, instruction units count: 676
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: C7.a.b(g0.o, java.lang.String, java.lang.String, java.lang.String, z7.a, T.q, int, int):void");
    }

    public static final f0 b0(Class cls, l0 l0Var, f fVar, AbstractC0992b abstractC0992b, C0761q c0761q) {
        c0761q.R(-1566358618);
        f0 f0VarI = I(l0Var, z.l(cls), null, fVar, abstractC0992b);
        c0761q.p(false);
        return f0VarI;
    }

    public static final void c(g0.o oVar, KaraokeItem karaokeItem, InterfaceC2782k interfaceC2782k, C0761q c0761q, int i7) {
        int i10;
        c0761q.S(1154227391);
        if ((i7 & 6) == 0) {
            i10 = (c0761q.f(oVar) ? 4 : 2) | i7;
        } else {
            i10 = i7;
        }
        if ((i7 & 48) == 0) {
            i10 |= c0761q.h(karaokeItem) ? 32 : 16;
        }
        if ((i7 & 384) == 0) {
            i10 |= c0761q.h(interfaceC2782k) ? 256 : 128;
        }
        if ((i10 & 147) == 146 && c0761q.x()) {
            c0761q.K();
        } else {
            c0761q.Q(-1137833629);
            boolean zH = ((i10 & 896) == 256) | c0761q.h(karaokeItem);
            Object objG = c0761q.G();
            if (zH || objG == C0753m.f8270a) {
                objG = new C0627u2(interfaceC2782k, karaokeItem, 0);
                c0761q.a0(objG);
            }
            c0761q.p(false);
            com.bumptech.glide.c.e(oVar, karaokeItem, (InterfaceC2772a) objG, c0761q, i10 & 126);
        }
        C0754m0 c0754m0R = c0761q.r();
        if (c0754m0R != null) {
            c0754m0R.d = new C0641y0(i7, 3, oVar, karaokeItem, interfaceC2782k);
        }
    }

    public static final void d(final g0.o oVar, final C0411f c0411f, float f10, final KaraokeItemChildType karaokeItemChildType, final List list, final InterfaceC2772a interfaceC2772a, final InterfaceC2782k interfaceC2782k, final boolean z6, final boolean z10, C0761q c0761q, final int i7) {
        int i10;
        int i11;
        float F9;
        final float f11;
        m.f(list, "karaokeItems");
        m.f(interfaceC2782k, "onTileClicked");
        c0761q.S(1457877157);
        if ((i7 & 6) == 0) {
            i10 = (c0761q.f(oVar) ? 4 : 2) | i7;
        } else {
            i10 = i7;
        }
        if ((i7 & 48) == 0) {
            i10 |= c0761q.f(c0411f) ? 32 : 16;
        }
        if ((i7 & 384) == 0) {
            i10 |= 128;
        }
        if ((i7 & 3072) == 0) {
            i10 |= c0761q.f(karaokeItemChildType) ? 2048 : 1024;
        }
        if ((i7 & 24576) == 0) {
            i10 |= c0761q.h(list) ? 16384 : 8192;
        }
        if ((196608 & i7) == 0) {
            i10 |= c0761q.h(interfaceC2772a) ? 131072 : 65536;
        }
        if ((1572864 & i7) == 0) {
            i10 |= c0761q.h(interfaceC2782k) ? 1048576 : 524288;
        }
        if ((12582912 & i7) == 0) {
            i10 |= c0761q.g(z6) ? 8388608 : 4194304;
        }
        if ((100663296 & i7) == 0) {
            i10 |= c0761q.g(z10) ? 67108864 : 33554432;
        }
        if ((38347923 & i10) == 38347922 && c0761q.x()) {
            c0761q.K();
            f11 = f10;
        } else {
            c0761q.M();
            if ((i7 & 1) == 0 || c0761q.w()) {
                i11 = i10 & (-897);
                F9 = c.f19252a.F(c0761q);
            } else {
                c0761q.K();
                F9 = f10;
                i11 = i10 & (-897);
            }
            c0761q.q();
            int i12 = i11 >> 3;
            r(oVar, c0411f, null, F9, AbstractC1998m.A0(com.bumptech.glide.c.V(list, karaokeItemChildType, true, false, interfaceC2782k, 0, c0761q, 40)), interfaceC2772a, z6, z10, c0761q, (i11 & 458878) | (3670016 & i12) | (i12 & 29360128), 4);
            f11 = F9;
        }
        C0754m0 c0754m0R = c0761q.r();
        if (c0754m0R != null) {
            c0754m0R.d = new InterfaceC2785n() { // from class: Q5.y2
                @Override // z7.InterfaceC2785n
                public final Object invoke(Object obj, Object obj2) {
                    ((Integer) obj2).getClass();
                    int iZ = C0735d.Z(i7 | 1);
                    C0411f c0411f2 = c0411f;
                    boolean z11 = z6;
                    boolean z12 = z10;
                    C7.a.d(oVar, c0411f2, f11, karaokeItemChildType, list, interfaceC2772a, interfaceC2782k, z11, z12, (C0761q) obj, iZ);
                    return m7.q.f19267a;
                }
            };
        }
    }

    public static final void e(final g0.o oVar, final List list, final D.L l, final InterfaceC2785n interfaceC2785n, final InterfaceC2782k interfaceC2782k, D d, String str, InterfaceC2785n interfaceC2785n2, C0761q c0761q, final int i7, final int i10) {
        int i11;
        D d10;
        String str2;
        InterfaceC2785n interfaceC2785n3;
        D dA;
        InterfaceC2785n interfaceC2785n4;
        D d11;
        int i12;
        C0124f c0124f;
        final String str3;
        final InterfaceC2785n interfaceC2785n5;
        m.f(list, "items");
        m.f(l, "contentPadding");
        m.f(interfaceC2785n, "onTileClicked");
        m.f(interfaceC2782k, "onMoreClicked");
        c0761q.S(1922287362);
        if ((i7 & 6) == 0) {
            i11 = (c0761q.f(oVar) ? 4 : 2) | i7;
        } else {
            i11 = i7;
        }
        if ((i7 & 48) == 0) {
            i11 |= c0761q.h(list) ? 32 : 16;
        }
        if ((i7 & 384) == 0) {
            i11 |= c0761q.f(l) ? 256 : 128;
        }
        if ((i7 & 3072) == 0) {
            i11 |= c0761q.h(interfaceC2785n) ? 2048 : 1024;
        }
        if ((i7 & 24576) == 0) {
            i11 |= c0761q.h(interfaceC2782k) ? 16384 : 8192;
        }
        if ((196608 & i7) == 0) {
            if ((i10 & 32) == 0) {
                d10 = d;
                int i13 = c0761q.f(d10) ? 131072 : 65536;
                i11 |= i13;
            } else {
                d10 = d;
            }
            i11 |= i13;
        } else {
            d10 = d;
        }
        int i14 = i10 & 64;
        if (i14 != 0) {
            i11 |= 1572864;
            str2 = str;
        } else {
            str2 = str;
            if ((i7 & 1572864) == 0) {
                i11 |= c0761q.f(str2) ? 1048576 : 524288;
            }
        }
        int i15 = 128 & i10;
        if (i15 != 0) {
            i11 |= 12582912;
            interfaceC2785n3 = interfaceC2785n2;
        } else {
            interfaceC2785n3 = interfaceC2785n2;
            if ((i7 & 12582912) == 0) {
                i11 |= c0761q.h(interfaceC2785n3) ? 8388608 : 4194304;
            }
        }
        if ((i11 & 4793491) == 4793490 && c0761q.x()) {
            c0761q.K();
            d11 = d10;
            interfaceC2785n5 = interfaceC2785n3;
            str3 = str2;
        } else {
            c0761q.M();
            if ((i7 & 1) == 0 || c0761q.w()) {
                if ((32 & i10) != 0) {
                    dA = G.a(0, 0, c0761q, 0, 3);
                    i11 &= -458753;
                } else {
                    dA = d10;
                }
                if (i14 != 0) {
                    str2 = "";
                }
                interfaceC2785n4 = i15 != 0 ? null : interfaceC2785n2;
                d11 = dA;
                i12 = i11;
            } else {
                c0761q.K();
                if ((i10 & 32) != 0) {
                    i11 &= -458753;
                }
                interfaceC2785n4 = interfaceC2785n2;
                i12 = i11;
                d11 = d10;
            }
            String str4 = str2;
            c0761q.q();
            C0119a c0119a = AbstractC0127i.f1076a;
            C0124f c0124f2 = new C0124f(c.f19252a.v0(c0761q));
            c0761q.Q(-782976202);
            boolean zH = ((57344 & i12) == 16384) | ((29360128 & i12) == 8388608) | c0761q.h(list) | ((3670016 & i12) == 1048576) | ((i12 & 7168) == 2048);
            Object objG = c0761q.G();
            if (zH || objG == C0753m.f8270a) {
                c0124f = c0124f2;
                F0 f02 = new F0(interfaceC2785n4, list, str4, interfaceC2785n, interfaceC2782k);
                c0761q.a0(f02);
                objG = f02;
            } else {
                c0124f = c0124f2;
            }
            c0761q.p(false);
            io.sentry.config.a.q(oVar, d11, l, false, c0124f, null, null, false, (InterfaceC2782k) objG, c0761q, (i12 & 14) | ((i12 >> 12) & 112) | (i12 & 896), 232);
            str3 = str4;
            interfaceC2785n5 = interfaceC2785n4;
        }
        C0754m0 c0754m0R = c0761q.r();
        if (c0754m0R != null) {
            final D d12 = d11;
            c0754m0R.d = new InterfaceC2785n() { // from class: Q5.v2
                @Override // z7.InterfaceC2785n
                public final Object invoke(Object obj, Object obj2) {
                    ((Integer) obj2).getClass();
                    int iZ = C0735d.Z(i7 | 1);
                    InterfaceC2785n interfaceC2785n6 = interfaceC2785n5;
                    C7.a.e(oVar, list, l, interfaceC2785n, interfaceC2782k, d12, str3, interfaceC2785n6, (C0761q) obj, iZ, i10);
                    return m7.q.f19267a;
                }
            };
        }
    }

    public static final void f(g0.o oVar, final long j3, final InterfaceC2772a interfaceC2772a, C0761q c0761q, final int i7, final int i10) {
        final g0.o oVar2;
        int i11;
        c0761q.S(132955046);
        int i12 = i10 & 1;
        if (i12 != 0) {
            i11 = i7 | 6;
            oVar2 = oVar;
        } else if ((i7 & 6) == 0) {
            oVar2 = oVar;
            i11 = (c0761q.f(oVar2) ? 4 : 2) | i7;
        } else {
            oVar2 = oVar;
            i11 = i7;
        }
        if ((i7 & 48) == 0) {
            i11 |= c0761q.e(j3) ? 32 : 16;
        }
        if ((i7 & 384) == 0) {
            i11 |= c0761q.h(interfaceC2772a) ? 256 : 128;
        }
        if ((i11 & 147) == 146 && c0761q.x()) {
            c0761q.K();
        } else {
            g0.o oVar3 = i12 != 0 ? g0.l.f14684a : oVar2;
            g0.o oVarM = h6.q.m(oVar3, J.e.f3041a);
            c0761q.Q(1389770631);
            boolean z6 = (i11 & 896) == 256;
            Object objG = c0761q.G();
            if (z6 || objG == C0753m.f8270a) {
                objG = new C0549b(interfaceC2772a, 12);
                c0761q.a0(objG);
            }
            c0761q.p(false);
            g0.o oVarQ = com.bumptech.glide.c.Q(7, oVarM, (InterfaceC2772a) objG, false);
            S sB = D.P.b(AbstractC0127i.f1076a, g0.b.f14669u, c0761q, 0);
            int i13 = c0761q.f8304P;
            InterfaceC0744h0 interfaceC0744h0M = c0761q.m();
            g0.o oVarD = g0.a.d(c0761q, oVarQ);
            InterfaceC0161k.f1681b.getClass();
            C0159i c0159i = C0160j.f1678b;
            c0761q.U();
            if (c0761q.f8303O) {
                c0761q.l(c0159i);
            } else {
                c0761q.d0();
            }
            C0735d.V(c0761q, sB, C0160j.f1679e);
            C0735d.V(c0761q, interfaceC0744h0M, C0160j.d);
            C0158h c0158h = C0160j.f1680f;
            if (c0761q.f8303O || !m.a(c0761q.G(), Integer.valueOf(i13))) {
                AbstractC1347d.l(i13, c0761q, i13, c0158h);
            }
            C0735d.V(c0761q, oVarD, C0160j.c);
            io.sentry.config.a.B(null, null, false, AbstractC2673a.X(R.string.Global_More, c0761q), j3, 0L, null, false, null, null, interfaceC2772a, c0761q, (i11 << 9) & 57344, (i11 >> 6) & 14);
            c0761q.p(true);
            oVar2 = oVar3;
        }
        C0754m0 c0754m0R = c0761q.r();
        if (c0754m0R != null) {
            c0754m0R.d = new InterfaceC2785n() { // from class: Q5.z2
                @Override // z7.InterfaceC2785n
                public final Object invoke(Object obj, Object obj2) {
                    ((Integer) obj2).getClass();
                    int iZ = C0735d.Z(i7 | 1);
                    InterfaceC2772a interfaceC2772a2 = interfaceC2772a;
                    C7.a.f(oVar2, j3, interfaceC2772a2, (C0761q) obj, iZ, i10);
                    return m7.q.f19267a;
                }
            };
        }
    }

    public static C0406a g(String str, N0.L l, long j3, a1.b bVar, InterfaceC0702n interfaceC0702n, u uVar, int i7, int i10) {
        int i11 = i10 & 32;
        u uVar2 = u.l;
        return new C0406a(new V0.c(str, l, i11 != 0 ? uVar2 : uVar, uVar2, interfaceC0702n, bVar), i7, false, j3);
    }

    public static final void h(final g0.o oVar, final String str, final String str2, final String str3, final boolean z6, final boolean z10, final boolean z11, final boolean z12, final boolean z13, final boolean z14, final InterfaceC2772a interfaceC2772a, final InterfaceC2772a interfaceC2772a2, final boolean z15, final InterfaceC2782k interfaceC2782k, final K5.v vVar, final t6.h hVar, final N n10, final long j3, final LeadVocalState leadVocalState, final boolean z16, final InterfaceC2772a interfaceC2772a3, final String str4, final boolean z17, final PlayerStatus playerStatus, final int i7, final DriveMode driveMode, final boolean z18, final boolean z19, final InterfaceC2782k interfaceC2782k2, final InterfaceC2772a interfaceC2772a4, final InterfaceC2782k interfaceC2782k3, final InterfaceC2772a interfaceC2772a5, final InterfaceC2772a interfaceC2772a6, final boolean z20, final InterfaceC2782k interfaceC2782k4, final boolean z21, final x6.e eVar, final C1972e c1972e, final C1967H c1967h, final boolean z22, final InterfaceC2782k interfaceC2782k5, final boolean z23, final boolean z24, final InterfaceC2772a interfaceC2772a7, final boolean z25, final InterfaceC2772a interfaceC2772a8, final InterfaceC2772a interfaceC2772a9, final InterfaceC2772a interfaceC2772a10, final int i10, final N n11, final boolean z26, final boolean z27, final boolean z28, final C1966G c1966g, final boolean z29, final InterfaceC2782k interfaceC2782k6, final InterfaceC2785n interfaceC2785n, final InterfaceC2782k interfaceC2782k7, final boolean z30, final boolean z31, final boolean z32, final boolean z33, C0761q c0761q, final int i11, final int i12, final int i13, final int i14, final int i15, final int i16, final int i17) {
        int i18;
        int i19;
        int i20;
        int i21;
        int i22;
        int i23;
        int i24;
        c0761q.S(1967113949);
        if ((i11 & 6) == 0) {
            i18 = (c0761q.f(oVar) ? 4 : 2) | i11;
        } else {
            i18 = i11;
        }
        if ((i11 & 48) == 0) {
            i18 |= c0761q.f(str) ? 32 : 16;
        }
        if ((i11 & 384) == 0) {
            i18 |= c0761q.f(str2) ? 256 : 128;
        }
        if ((i11 & 3072) == 0) {
            i18 |= c0761q.f(str3) ? 2048 : 1024;
        }
        if ((i11 & 24576) == 0) {
            i18 |= c0761q.g(z6) ? 16384 : 8192;
        }
        if ((i11 & 196608) == 0) {
            i18 |= c0761q.g(z10) ? 131072 : 65536;
        }
        if ((i11 & 1572864) == 0) {
            i18 |= c0761q.g(z11) ? 1048576 : 524288;
        }
        if ((i11 & 12582912) == 0) {
            i18 |= c0761q.g(z12) ? 8388608 : 4194304;
        }
        if ((i11 & 100663296) == 0) {
            i18 |= c0761q.g(z13) ? 67108864 : 33554432;
        }
        if ((i11 & 805306368) == 0) {
            i18 |= c0761q.g(z14) ? 536870912 : 268435456;
        }
        if ((i12 & 6) == 0) {
            i19 = i12 | (c0761q.h(interfaceC2772a) ? 4 : 2);
        } else {
            i19 = i12;
        }
        if ((i12 & 48) == 0) {
            i19 |= c0761q.h(interfaceC2772a2) ? 32 : 16;
        }
        if ((i12 & 3072) == 0) {
            i19 |= c0761q.h(interfaceC2782k) ? 2048 : 1024;
        }
        if ((i12 & 24576) == 0) {
            i19 |= c0761q.f(vVar) ? 16384 : 8192;
        }
        if ((i12 & 196608) == 0) {
            i19 |= c0761q.h(hVar) ? 131072 : 65536;
        }
        if ((i12 & 1572864) == 0) {
            i19 |= c0761q.h(n10) ? 1048576 : 524288;
        }
        if ((i12 & 12582912) == 0) {
            i19 |= c0761q.e(j3) ? 8388608 : 4194304;
        }
        if ((i12 & 100663296) == 0) {
            i19 |= c0761q.f(leadVocalState) ? 67108864 : 33554432;
        }
        if ((i12 & 805306368) == 0) {
            i19 |= c0761q.g(z16) ? 536870912 : 268435456;
        }
        if ((i13 & 6) == 0) {
            i20 = i13 | (c0761q.h(interfaceC2772a3) ? 4 : 2);
        } else {
            i20 = i13;
        }
        if ((i13 & 48) == 0) {
            i20 |= c0761q.f(str4) ? 32 : 16;
        }
        if ((i13 & 384) == 0) {
            i20 |= c0761q.g(z17) ? 256 : 128;
        }
        if ((i13 & 3072) == 0) {
            i20 |= c0761q.f(playerStatus) ? 2048 : 1024;
        }
        if ((i13 & 24576) == 0) {
            i20 |= c0761q.d(i7) ? 16384 : 8192;
        }
        if ((i13 & 196608) == 0) {
            i20 |= c0761q.f(driveMode) ? 131072 : 65536;
        }
        if ((i13 & 1572864) == 0) {
            i20 |= c0761q.g(z18) ? 1048576 : 524288;
        }
        if ((i13 & 12582912) == 0) {
            i20 |= c0761q.g(z19) ? 8388608 : 4194304;
        }
        if ((i13 & 100663296) == 0) {
            i20 |= c0761q.h(interfaceC2782k2) ? 67108864 : 33554432;
        }
        if ((i13 & 805306368) == 0) {
            i20 |= c0761q.h(interfaceC2772a4) ? 536870912 : 268435456;
        }
        if ((i14 & 6) == 0) {
            i21 = i14 | (c0761q.h(interfaceC2782k3) ? 4 : 2);
        } else {
            i21 = i14;
        }
        if ((i14 & 48) == 0) {
            i21 |= c0761q.h(interfaceC2772a5) ? 32 : 16;
        }
        if ((i14 & 384) == 0) {
            i21 |= c0761q.h(interfaceC2772a6) ? 256 : 128;
        }
        if ((i14 & 3072) == 0) {
            i21 |= c0761q.g(z20) ? 2048 : 1024;
        }
        if ((i14 & 24576) == 0) {
            i21 |= c0761q.h(interfaceC2782k4) ? 16384 : 8192;
        }
        if ((i14 & 196608) == 0) {
            i21 |= c0761q.g(z21) ? 131072 : 65536;
        }
        if ((i14 & 1572864) == 0) {
            i21 |= c0761q.h(eVar) ? 1048576 : 524288;
        }
        if ((i14 & 12582912) == 0) {
            i21 |= (i14 & 16777216) == 0 ? c0761q.f(c1972e) : c0761q.h(c1972e) ? 8388608 : 4194304;
        }
        if ((i14 & 100663296) == 0) {
            i21 |= (i14 & 134217728) == 0 ? c0761q.f(c1967h) : c0761q.h(c1967h) ? 67108864 : 33554432;
        }
        if ((i14 & 805306368) == 0) {
            i21 |= c0761q.g(z22) ? 536870912 : 268435456;
        }
        if ((i15 & 6) == 0) {
            i22 = i15 | (c0761q.h(interfaceC2782k5) ? 4 : 2);
        } else {
            i22 = i15;
        }
        if ((i15 & 48) == 0) {
            i22 |= c0761q.g(z23) ? 32 : 16;
        }
        if ((i15 & 384) == 0) {
            i22 |= c0761q.g(z24) ? 256 : 128;
        }
        if ((i15 & 3072) == 0) {
            i22 |= c0761q.h(interfaceC2772a7) ? 2048 : 1024;
        }
        if ((i15 & 24576) == 0) {
            i22 |= c0761q.g(z25) ? 16384 : 8192;
        }
        if ((i15 & 196608) == 0) {
            i22 |= c0761q.h(interfaceC2772a8) ? 131072 : 65536;
        }
        if ((i15 & 1572864) == 0) {
            i22 |= c0761q.h(interfaceC2772a9) ? 1048576 : 524288;
        }
        if ((i15 & 12582912) == 0) {
            i22 |= c0761q.h(interfaceC2772a10) ? 8388608 : 4194304;
        }
        if ((i15 & 100663296) == 0) {
            i22 |= c0761q.d(i10) ? 67108864 : 33554432;
        }
        if ((i15 & 805306368) == 0) {
            i22 |= c0761q.h(n11) ? 536870912 : 268435456;
        }
        if ((i16 & 6) == 0) {
            i23 = i16 | (c0761q.g(z26) ? 4 : 2);
        } else {
            i23 = i16;
        }
        if ((i16 & 48) == 0) {
            i23 |= c0761q.g(z27) ? 32 : 16;
        }
        if ((i16 & 384) == 0) {
            i23 |= c0761q.g(z28) ? 256 : 128;
        }
        if ((i16 & 3072) == 0) {
            i23 |= (i16 & 4096) == 0 ? c0761q.f(c1966g) : c0761q.h(c1966g) ? 2048 : 1024;
        }
        if ((i16 & 24576) == 0) {
            i23 |= c0761q.g(z29) ? 16384 : 8192;
        }
        if ((i16 & 196608) == 0) {
            i23 |= c0761q.h(interfaceC2782k6) ? 131072 : 65536;
        }
        if ((i16 & 1572864) == 0) {
            i23 |= c0761q.h(interfaceC2785n) ? 1048576 : 524288;
        }
        if ((i16 & 12582912) == 0) {
            i23 |= c0761q.h(interfaceC2782k7) ? 8388608 : 4194304;
        }
        if ((i16 & 100663296) == 0) {
            i23 |= c0761q.g(z30) ? 67108864 : 33554432;
        }
        if ((i16 & 805306368) == 0) {
            i23 |= c0761q.g(z31) ? 536870912 : 268435456;
        }
        if ((i17 & 6) == 0) {
            i24 = i17 | (c0761q.g(z32) ? 4 : 2);
        } else {
            i24 = i17;
        }
        if ((i17 & 48) == 0) {
            i24 |= c0761q.g(z33) ? 32 : 16;
        }
        if ((i18 & 306783379) == 306783378 && (i19 & 306783251) == 306783250 && (i20 & 306783379) == 306783378 && (i21 & 306783379) == 306783378 && (i22 & 306783379) == 306783378 && (i23 & 306783379) == 306783378 && (i24 & 19) == 18 && c0761q.x()) {
            c0761q.K();
        } else {
            l6.d.b(true, false, b0.g.b(133547785, new C0893z(oVar, interfaceC2772a, (InterfaceC1716g) c0761q.k(AbstractC0227s0.f2260g), AbstractC1692D.b((LayoutMode) c0761q.k(AbstractC1692D.f18238a)), z25, c1967h, z10, z6, z21, z22, z30, n11, interfaceC2785n, vVar, hVar, n10, leadVocalState, eVar, z11, str, str2, str3, z19, z12, z13, z18, interfaceC2772a6, interfaceC2782k, z14, str4, interfaceC2772a2, z33, z32, z17, playerStatus, i7, z16, interfaceC2772a3, j3, driveMode, interfaceC2782k2, interfaceC2772a4, interfaceC2782k3, interfaceC2772a5, z20, interfaceC2782k4, interfaceC2782k5, i10, z23, z24, interfaceC2772a7, z26, z27, z28, z31, c1966g, interfaceC2772a9, interfaceC2772a8, z29, interfaceC2782k6, interfaceC2782k7, c1972e, interfaceC2772a10), c0761q), c0761q, 390, 2);
        }
        C0754m0 c0754m0R = c0761q.r();
        if (c0754m0R != null) {
            c0754m0R.d = new InterfaceC2785n() { // from class: X5.w
                @Override // z7.InterfaceC2785n
                public final Object invoke(Object obj, Object obj2) {
                    ((Integer) obj2).getClass();
                    int iZ = C0735d.Z(i11 | 1);
                    int iZ2 = C0735d.Z(i12);
                    int iZ3 = C0735d.Z(i13);
                    int iZ4 = C0735d.Z(i14);
                    int iZ5 = C0735d.Z(i15);
                    int iZ6 = C0735d.Z(i16);
                    int iZ7 = C0735d.Z(i17);
                    boolean z34 = z32;
                    boolean z35 = z33;
                    C7.a.h(oVar, str, str2, str3, z6, z10, z11, z12, z13, z14, interfaceC2772a, interfaceC2772a2, z15, interfaceC2782k, vVar, hVar, n10, j3, leadVocalState, z16, interfaceC2772a3, str4, z17, playerStatus, i7, driveMode, z18, z19, interfaceC2782k2, interfaceC2772a4, interfaceC2782k3, interfaceC2772a5, interfaceC2772a6, z20, interfaceC2782k4, z21, eVar, c1972e, c1967h, z22, interfaceC2782k5, z23, z24, interfaceC2772a7, z25, interfaceC2772a8, interfaceC2772a9, interfaceC2772a10, i10, n11, z26, z27, z28, c1966g, z29, interfaceC2782k6, interfaceC2785n, interfaceC2782k7, z30, z31, z34, z35, (C0761q) obj, iZ, iZ2, iZ3, iZ4, iZ5, iZ6, iZ7);
                    return m7.q.f19267a;
                }
            };
        }
    }

    public static final void i(final g0.o oVar, final long j3, final long j10, final long j11, final FontSize fontSize, C0761q c0761q, final int i7) {
        int i10;
        m.f(fontSize, "fontSize");
        c0761q.S(-600911474);
        if ((i7 & 6) == 0) {
            i10 = (c0761q.f(oVar) ? 4 : 2) | i7;
        } else {
            i10 = i7;
        }
        if ((i7 & 48) == 0) {
            i10 |= c0761q.e(j3) ? 32 : 16;
        }
        if ((i7 & 384) == 0) {
            i10 |= c0761q.e(j10) ? 256 : 128;
        }
        if ((i7 & 3072) == 0) {
            i10 |= c0761q.e(j11) ? 2048 : 1024;
        }
        if ((i7 & 24576) == 0) {
            i10 |= c0761q.f(fontSize) ? 16384 : 8192;
        }
        if ((i10 & 9363) == 9362 && c0761q.x()) {
            c0761q.K();
        } else {
            c1.b(AbstractC1985B.l(j3) + " / " + AbstractC1985B.l(j10), oVar, j11, h6.f.g(fontSize, c0761q), null, null, null, 0L, null, null, 0L, 0, false, 0, 0, null, ((O5.d) c0761q.k(O5.c.f5157b)).f5158a, c0761q, ((i10 << 3) & 112) | ((i10 >> 3) & 896), 0, 65520);
        }
        C0754m0 c0754m0R = c0761q.r();
        if (c0754m0R != null) {
            c0754m0R.d = new InterfaceC2785n() { // from class: T5.j
                @Override // z7.InterfaceC2785n
                public final Object invoke(Object obj, Object obj2) {
                    ((Integer) obj2).getClass();
                    int iZ = C0735d.Z(i7 | 1);
                    long j12 = j11;
                    FontSize fontSize2 = fontSize;
                    C7.a.i(oVar, j3, j10, j12, fontSize2, (C0761q) obj, iZ);
                    return q.f19267a;
                }
            };
        }
    }

    public static final void j(g0.o oVar, D0 d02, C0761q c0761q, int i7) {
        g0.o oVar2;
        C1945j c1945j;
        C0158h c0158h;
        boolean z6;
        float f10;
        boolean z10;
        m.f(d02, "viewModel");
        c0761q.S(-1859966549);
        int i10 = i7 | 6;
        if ((i7 & 48) == 0) {
            i10 |= c0761q.h(d02) ? 32 : 16;
        }
        if ((i10 & 19) == 18 && c0761q.x()) {
            c0761q.K();
            oVar2 = oVar;
        } else {
            oVar2 = g0.l.f14684a;
            C1930E c1930eB = d02.f12234b.b(d02.d);
            c0761q.Q(-1146135354);
            Object objG = c0761q.G();
            Object obj = C0753m.f8270a;
            T.S s6 = T.S.f8224q;
            if (objG == obj) {
                objG = C0735d.O(null, s6);
                c0761q.a0(objG);
            }
            X x8 = (X) objG;
            Object objE = M5.b.e(-1146133002, c0761q, false);
            if (objE == obj) {
                objE = C0735d.O(PlayerSideMenuRoute.SongQueue, s6);
                c0761q.a0(objE);
            }
            X x9 = (X) objE;
            c0761q.p(false);
            boolean zC = AbstractC1692D.c((LayoutMode) c0761q.k(AbstractC1692D.f18238a));
            Context context = (Context) c0761q.k(AndroidCompositionLocals_androidKt.f11688b);
            m.d(context, "null cannot be cast to non-null type android.app.Activity");
            Object window = ((Activity) context).getWindow();
            X xR = com.bumptech.glide.c.R(d02.f10130Z, c0761q, 0);
            X xR2 = com.bumptech.glide.c.R(d02.f10142l0, c0761q, 0);
            X xW = C0735d.w(c1930eB.f19799D, null, null, c0761q, 48, 2);
            C1945j c1945j2 = (C1945j) xW.getValue();
            c0761q.Q(-1146101568);
            boolean zF = c0761q.f(xW) | c0761q.h(d02);
            Object objG2 = c0761q.G();
            if (zF || objG2 == obj) {
                c1945j = c1945j2;
                Object b10 = new B(xW, d02, x9, x8, null);
                c0761q.a0(b10);
                objG2 = b10;
            } else {
                c1945j = c1945j2;
            }
            c0761q.p(false);
            C0735d.f(c0761q, c1945j, (InterfaceC2785n) objG2);
            g0.o oVarB = androidx.compose.foundation.a.b(oVar2, l6.d.c(c0761q).f18704g, AbstractC1861K.f19381a);
            g0.g gVar = g0.b.l;
            K kE = AbstractC0133o.e(gVar, false);
            int i11 = c0761q.f8304P;
            InterfaceC0744h0 interfaceC0744h0M = c0761q.m();
            g0.o oVarD = g0.a.d(c0761q, oVarB);
            InterfaceC0161k.f1681b.getClass();
            InterfaceC2772a interfaceC2772a = C0160j.f1678b;
            c0761q.U();
            if (c0761q.f8303O) {
                c0761q.l(interfaceC2772a);
            } else {
                c0761q.d0();
            }
            C0158h c0158h2 = C0160j.f1679e;
            C0735d.V(c0761q, kE, c0158h2);
            C0158h c0158h3 = C0160j.d;
            C0735d.V(c0761q, interfaceC0744h0M, c0158h3);
            C0158h c0158h4 = C0160j.f1680f;
            if (c0761q.f8303O || !m.a(c0761q.G(), Integer.valueOf(i11))) {
                AbstractC1347d.l(i11, c0761q, i11, c0158h4);
            }
            C0158h c0158h5 = C0160j.c;
            C0735d.V(c0761q, oVarD, c0158h5);
            c0761q.Q(-2003993190);
            l6.d.b(true, false, b0.g.b(-1480645946, new C(d02, 2), c0761q), c0761q, 390, 2);
            c0761q.p(false);
            if (zC) {
                c0761q.Q(-965452657);
                S sB = D.P.b(AbstractC0127i.f1076a, g0.b.f14669u, c0761q, 0);
                int i12 = c0761q.f8304P;
                InterfaceC0744h0 interfaceC0744h0M2 = c0761q.m();
                g0.o oVarD2 = g0.a.d(c0761q, oVar2);
                c0761q.U();
                if (c0761q.f8303O) {
                    c0761q.l(interfaceC2772a);
                } else {
                    c0761q.d0();
                }
                C0735d.V(c0761q, sB, c0158h2);
                C0735d.V(c0761q, interfaceC0744h0M2, c0158h3);
                if (c0761q.f8303O || !m.a(c0761q.G(), Integer.valueOf(i12))) {
                    c0158h = c0158h4;
                    AbstractC1347d.l(i12, c0761q, i12, c0158h);
                } else {
                    c0158h = c0158h4;
                }
                C0735d.V(c0761q, oVarD2, c0158h5);
                g0.o oVarB2 = T.b(oVar2, 1.0f);
                K kE2 = AbstractC0133o.e(gVar, false);
                int i13 = c0761q.f8304P;
                InterfaceC0744h0 interfaceC0744h0M3 = c0761q.m();
                g0.o oVarD3 = g0.a.d(c0761q, oVarB2);
                c0761q.U();
                if (c0761q.f8303O) {
                    c0761q.l(interfaceC2772a);
                } else {
                    c0761q.d0();
                }
                C0735d.V(c0761q, kE2, c0158h2);
                C0735d.V(c0761q, interfaceC0744h0M3, c0158h3);
                if (c0761q.f8303O || !m.a(c0761q.G(), Integer.valueOf(i13))) {
                    AbstractC1347d.l(i13, c0761q, i13, c0158h);
                }
                C0735d.V(c0761q, oVarD3, c0158h5);
                C0773y c0773y = AbstractC1692D.d;
                AbstractC0750k0 abstractC0750k0 = AndroidCompositionLocals_androidKt.f11687a;
                float f11 = ((Configuration) c0761q.k(abstractC0750k0)).screenWidthDp;
                c0761q.Q(134084297);
                if (((Boolean) xR2.getValue()).booleanValue()) {
                    f10 = 1 - h6.f.f(R.dimen.split_view_percent, c0761q);
                    z6 = false;
                } else {
                    z6 = false;
                    f10 = 1.0f;
                }
                c0761q.p(z6);
                C0735d.a(c0773y.a(new k6.u((int) (f11 * f10), ((Configuration) c0761q.k(abstractC0750k0)).screenHeightDp)), b0.g.b(-10846377, new C(d02, 1), c0761q), c0761q, 56);
                c0761q.p(true);
                C0735d.a(c0773y.a(new k6.u((int) (h6.f.f(R.dimen.split_view_percent, c0761q) * ((Configuration) c0761q.k(abstractC0750k0)).screenWidthDp), ((Configuration) c0761q.k(abstractC0750k0)).screenHeightDp)), b0.g.b(1473668253, new X5.D(d02, c1930eB, 1), c0761q), c0761q, 56);
                z10 = true;
                c0761q.p(true);
                c0761q.p(false);
            } else {
                c0761q.Q(-965565280);
                k(d02, c0761q);
                l(d02, c1930eB, false, c0761q);
                c0761q.p(false);
                z10 = true;
            }
            c0761q.p(z10);
            q qVar = q.f19267a;
            c0761q.Q(-1146023872);
            boolean zH = c0761q.h(d02);
            Object objG3 = c0761q.G();
            if (zH || objG3 == obj) {
                objG3 = new E(d02, null);
                c0761q.a0(objG3);
            }
            c0761q.p(false);
            C0735d.f(c0761q, qVar, (InterfaceC2785n) objG3);
            PlayerStatus playerStatus = (PlayerStatus) xR.getValue();
            c0761q.Q(-1146021224);
            boolean zF2 = c0761q.f(xR) | c0761q.h(window);
            Object objG4 = c0761q.G();
            if (zF2 || objG4 == obj) {
                objG4 = new Q5.O(window, 9, xR);
                c0761q.a0(objG4);
            }
            c0761q.p(false);
            C0735d.d(playerStatus, (InterfaceC2782k) objG4, c0761q);
        }
        C0754m0 c0754m0R = c0761q.r();
        if (c0754m0R != null) {
            c0754m0R.d = new M(oVar2, d02, i7, 6);
        }
    }

    public static final void k(D0 d02, C0761q c0761q) {
        boolean z6;
        T.S s6;
        String str;
        String str2;
        String str3;
        g0.l lVar;
        InterfaceC0321f interfaceC0321f;
        InterfaceC0321f interfaceC0321f2;
        InterfaceC0321f interfaceC0321f3;
        InterfaceC0321f interfaceC0321f4;
        InterfaceC0321f interfaceC0321f5;
        InterfaceC0321f interfaceC0321f6;
        InterfaceC0321f interfaceC0321f7;
        InterfaceC0321f interfaceC0321f8;
        InterfaceC0321f interfaceC0321f9;
        InterfaceC0321f interfaceC0321f10;
        InterfaceC0321f interfaceC0321f11;
        InterfaceC0321f interfaceC0321f12;
        c0761q.Q(-1668569823);
        g0.l lVar2 = g0.l.f14684a;
        String str4 = (String) com.bumptech.glide.c.R(d02.f10145o0, c0761q, 0).getValue();
        String str5 = (String) com.bumptech.glide.c.R(d02.f10146p0, c0761q, 0).getValue();
        String str6 = (String) com.bumptech.glide.c.R(d02.f10147q0, c0761q, 0).getValue();
        boolean zBooleanValue = ((Boolean) com.bumptech.glide.c.R(d02.f10159x0, c0761q, 0).getValue()).booleanValue();
        boolean zBooleanValue2 = ((Boolean) com.bumptech.glide.c.R(d02.f10163z0, c0761q, 0).getValue()).booleanValue();
        boolean zBooleanValue3 = ((Boolean) com.bumptech.glide.c.R(d02.B0, c0761q, 0).getValue()).booleanValue();
        boolean zBooleanValue4 = ((Boolean) com.bumptech.glide.c.R(d02.f10108E0, c0761q, 0).getValue()).booleanValue();
        boolean zBooleanValue5 = ((Boolean) com.bumptech.glide.c.R(d02.f10104C0, c0761q, 0).getValue()).booleanValue();
        boolean zBooleanValue6 = ((Boolean) com.bumptech.glide.c.R(d02.f10135e0, c0761q, 0).getValue()).booleanValue();
        c0761q.Q(-1914528314);
        boolean zH = c0761q.h(d02);
        Object objG = c0761q.G();
        T.S s10 = C0753m.f8270a;
        if (zH || objG == s10) {
            z6 = zBooleanValue;
            s6 = s10;
            C0221p c0221p = new C0221p(0, d02, D0.class, "onToggleShowControls", "onToggleShowControls()V", 0, 26);
            c0761q.a0(c0221p);
            objG = c0221p;
        } else {
            z6 = zBooleanValue;
            s6 = s10;
        }
        InterfaceC0321f interfaceC0321f13 = (InterfaceC0321f) objG;
        c0761q.p(false);
        c0761q.Q(-1914526167);
        boolean zH2 = c0761q.h(d02);
        Object objG2 = c0761q.G();
        if (zH2 || objG2 == s6) {
            str = str6;
            F f10 = new F(0, d02, D0.class, "dismissDriveModeWarning", "dismissDriveModeWarning()V", 0, 1);
            c0761q.a0(f10);
            objG2 = f10;
        } else {
            str = str6;
        }
        InterfaceC0321f interfaceC0321f14 = (InterfaceC0321f) objG2;
        c0761q.p(false);
        boolean zBooleanValue7 = ((Boolean) com.bumptech.glide.c.R(d02.f10142l0, c0761q, 0).getValue()).booleanValue();
        c0761q.Q(-1914521312);
        boolean zH3 = c0761q.h(d02);
        Object objG3 = c0761q.G();
        if (zH3 || objG3 == s6) {
            str2 = str5;
            X5.G g7 = new X5.G(1, d02, D0.class, "toggleSideMenu", "toggleSideMenu(Lcom/stingray/karaoke/car/ui/player/SideMenuType;)V", 0, 1);
            c0761q.a0(g7);
            objG3 = g7;
        } else {
            str2 = str5;
        }
        InterfaceC0321f interfaceC0321f15 = (InterfaceC0321f) objG3;
        c0761q.p(false);
        K5.v vVar = (K5.v) com.bumptech.glide.c.R(d02.f10151t0, c0761q, 0).getValue();
        t6.h hVar = (t6.h) com.bumptech.glide.c.R(d02.f10127W, c0761q, 0).getValue();
        long jLongValue = ((Number) com.bumptech.glide.c.R(d02.f10137g0, c0761q, 0).getValue()).longValue();
        LeadVocalState leadVocalState = (LeadVocalState) com.bumptech.glide.c.R(d02.f10128X, c0761q, 0).getValue();
        boolean zBooleanValue8 = ((Boolean) com.bumptech.glide.c.R(d02.f10129Y, c0761q, 0).getValue()).booleanValue();
        c0761q.Q(-1914504865);
        boolean zH4 = c0761q.h(d02);
        Object objG4 = c0761q.G();
        if (zH4 || objG4 == s6) {
            str3 = str4;
            F f11 = new F(0, d02, D0.class, "onChangeVocal", "onChangeVocal()V", 0, 2);
            c0761q.a0(f11);
            objG4 = f11;
        } else {
            str3 = str4;
        }
        InterfaceC0321f interfaceC0321f16 = (InterfaceC0321f) objG4;
        c0761q.p(false);
        String str7 = (String) com.bumptech.glide.c.R(d02.f10131a0, c0761q, 0).getValue();
        boolean zBooleanValue9 = ((Boolean) com.bumptech.glide.c.R(d02.f10155v0, c0761q, 0).getValue()).booleanValue();
        PlayerStatus playerStatus = (PlayerStatus) com.bumptech.glide.c.R(d02.f10130Z, c0761q, 0).getValue();
        int iIntValue = ((Number) com.bumptech.glide.c.R(d02.f10132b0, c0761q, 0).getValue()).intValue();
        DriveMode driveMode = (DriveMode) com.bumptech.glide.c.R(d02.f10123S, c0761q, 0).getValue();
        boolean zBooleanValue10 = ((Boolean) com.bumptech.glide.c.R(d02.f10144n0, c0761q, 0).getValue()).booleanValue();
        boolean zBooleanValue11 = ((Boolean) com.bumptech.glide.c.R(d02.f10157w0, c0761q, 0).getValue()).booleanValue();
        c0761q.Q(-1914484421);
        boolean zH5 = c0761q.h(d02);
        Object objG5 = c0761q.G();
        if (zH5 || objG5 == s6) {
            lVar = lVar2;
            X5.G g10 = new X5.G(1, d02, D0.class, "onSeeking", "onSeeking(F)V", 0, 2);
            c0761q.a0(g10);
            objG5 = g10;
        } else {
            lVar = lVar2;
        }
        InterfaceC0321f interfaceC0321f17 = (InterfaceC0321f) objG5;
        c0761q.p(false);
        c0761q.Q(-1914482504);
        boolean zH6 = c0761q.h(d02);
        Object objG6 = c0761q.G();
        if (zH6 || objG6 == s6) {
            interfaceC0321f = interfaceC0321f17;
            F f12 = new F(0, d02, D0.class, "onSeek", "onSeek()V", 0, 3);
            c0761q.a0(f12);
            objG6 = f12;
        } else {
            interfaceC0321f = interfaceC0321f17;
        }
        InterfaceC0321f interfaceC0321f18 = (InterfaceC0321f) objG6;
        c0761q.p(false);
        c0761q.Q(-1914481220);
        boolean zH7 = c0761q.h(d02);
        Object objG7 = c0761q.G();
        if (zH7 || objG7 == s6) {
            interfaceC0321f2 = interfaceC0321f18;
            X5.G g11 = new X5.G(1, d02, D0.class, "onPlayback", "onPlayback(Lcom/stingray/karaoke/car/ui/player/PlaybackType;)V", 0, 3);
            c0761q.a0(g11);
            objG7 = g11;
        } else {
            interfaceC0321f2 = interfaceC0321f18;
        }
        InterfaceC0321f interfaceC0321f19 = (InterfaceC0321f) objG7;
        c0761q.p(false);
        c0761q.Q(-1914479845);
        boolean zH8 = c0761q.h(d02);
        Object objG8 = c0761q.G();
        if (zH8 || objG8 == s6) {
            interfaceC0321f3 = interfaceC0321f19;
            F f13 = new F(0, d02, D0.class, "onLooping", "onLooping()V", 0, 4);
            c0761q.a0(f13);
            objG8 = f13;
        } else {
            interfaceC0321f3 = interfaceC0321f19;
        }
        InterfaceC0321f interfaceC0321f20 = (InterfaceC0321f) objG8;
        c0761q.p(false);
        c0761q.Q(-1914478563);
        boolean zH9 = c0761q.h(d02);
        Object objG9 = c0761q.G();
        if (zH9 || objG9 == s6) {
            interfaceC0321f4 = interfaceC0321f20;
            F f14 = new F(0, d02, D0.class, "closePlayer", "closePlayer()V", 0, 5);
            c0761q.a0(f14);
            objG9 = f14;
        } else {
            interfaceC0321f4 = interfaceC0321f20;
        }
        InterfaceC0321f interfaceC0321f21 = (InterfaceC0321f) objG9;
        c0761q.p(false);
        boolean zBooleanValue12 = ((Boolean) d02.f10109F.getValue()).booleanValue();
        c0761q.Q(-1914475653);
        boolean zH10 = c0761q.h(d02);
        Object objG10 = c0761q.G();
        if (zH10 || objG10 == s6) {
            interfaceC0321f5 = interfaceC0321f21;
            C0223q c0223q = new C0223q(1, d02, D0.class, "onLikeTap", "onLikeTap(Lkotlin/jvm/functions/Function0;)V", 0, 28);
            c0761q.a0(c0223q);
            objG10 = c0223q;
        } else {
            interfaceC0321f5 = interfaceC0321f21;
        }
        InterfaceC0321f interfaceC0321f22 = (InterfaceC0321f) objG10;
        c0761q.p(false);
        boolean zBooleanValue13 = ((Boolean) com.bumptech.glide.c.R(d02.f10110F0, c0761q, 0).getValue()).booleanValue();
        boolean zBooleanValue14 = ((Boolean) com.bumptech.glide.c.R(d02.f10133c0, c0761q, 0).getValue()).booleanValue();
        boolean zBooleanValue15 = ((Boolean) com.bumptech.glide.c.R(d02.f10149s0, c0761q, 0).getValue()).booleanValue();
        boolean zBooleanValue16 = ((Boolean) com.bumptech.glide.c.R(d02.f10114J, c0761q, 0).getValue()).booleanValue();
        c0761q.Q(-1914458458);
        boolean zH11 = c0761q.h(d02);
        Object objG11 = c0761q.G();
        if (zH11 || objG11 == s6) {
            interfaceC0321f6 = interfaceC0321f22;
            C0221p c0221p2 = new C0221p(0, d02, D0.class, "onChangePerfectPitch", "onChangePerfectPitch()V", 0, 27);
            c0761q.a0(c0221p2);
            objG11 = c0221p2;
        } else {
            interfaceC0321f6 = interfaceC0321f22;
        }
        InterfaceC0321f interfaceC0321f23 = (InterfaceC0321f) objG11;
        c0761q.p(false);
        boolean zBooleanValue17 = ((Boolean) com.bumptech.glide.c.R(d02.f10139i0, c0761q, 0).getValue()).booleanValue();
        c0761q.Q(-1914449844);
        boolean zH12 = c0761q.h(d02);
        Object objG12 = c0761q.G();
        if (zH12 || objG12 == s6) {
            interfaceC0321f7 = interfaceC0321f23;
            C0221p c0221p3 = new C0221p(0, d02, D0.class, "onContinueScoringEndDialog", "onContinueScoringEndDialog()V", 0, 28);
            c0761q.a0(c0221p3);
            objG12 = c0221p3;
        } else {
            interfaceC0321f7 = interfaceC0321f23;
        }
        InterfaceC0321f interfaceC0321f24 = (InterfaceC0321f) objG12;
        c0761q.p(false);
        c0761q.Q(-1914447511);
        boolean zH13 = c0761q.h(d02);
        Object objG13 = c0761q.G();
        if (zH13 || objG13 == s6) {
            interfaceC0321f8 = interfaceC0321f24;
            C0221p c0221p4 = new C0221p(0, d02, D0.class, "onRetryScoringEndDialog", "onRetryScoringEndDialog()V", 0, 29);
            c0761q.a0(c0221p4);
            objG13 = c0221p4;
        } else {
            interfaceC0321f8 = interfaceC0321f24;
        }
        InterfaceC0321f interfaceC0321f25 = (InterfaceC0321f) objG13;
        c0761q.p(false);
        c0761q.Q(-1914445337);
        boolean zH14 = c0761q.h(d02);
        Object objG14 = c0761q.G();
        if (zH14 || objG14 == s6) {
            interfaceC0321f9 = interfaceC0321f25;
            F f15 = new F(0, d02, D0.class, "onReloadSongResources", "onReloadSongResources()V", 0, 0);
            c0761q.a0(f15);
            objG14 = f15;
        } else {
            interfaceC0321f9 = interfaceC0321f25;
        }
        InterfaceC0321f interfaceC0321f26 = (InterfaceC0321f) objG14;
        c0761q.p(false);
        int iIntValue2 = ((Number) com.bumptech.glide.c.R(d02.f10153u0, c0761q, 0).getValue()).intValue();
        boolean zBooleanValue18 = ((Boolean) com.bumptech.glide.c.R(d02.f10141k0, c0761q, 0).getValue()).booleanValue();
        V8.f0 f0Var = d02.f10134d0;
        boolean zBooleanValue19 = ((Boolean) com.bumptech.glide.c.R(f0Var, c0761q, 0).getValue()).booleanValue();
        C1966G c1966g = (C1966G) com.bumptech.glide.c.R(d02.f10116L, c0761q, 0).getValue();
        c0761q.Q(-1914429690);
        boolean z10 = ((Boolean) com.bumptech.glide.c.R(d02.f10148r0, c0761q, 0).getValue()).booleanValue() || ((Boolean) com.bumptech.glide.c.R(f0Var, c0761q, 0).getValue()).booleanValue();
        c0761q.p(false);
        boolean zBooleanValue20 = ((Boolean) com.bumptech.glide.c.R(d02.f10140j0, c0761q, 0).getValue()).booleanValue();
        c0761q.Q(-1914420526);
        boolean zH15 = c0761q.h(d02);
        Object objG15 = c0761q.G();
        if (zH15 || objG15 == s6) {
            interfaceC0321f10 = interfaceC0321f26;
            C0223q c0223q2 = new C0223q(1, d02, D0.class, "setShowScoringEndDialogAnimation", "setShowScoringEndDialogAnimation(Z)V", 0, 29);
            c0761q.a0(c0223q2);
            objG15 = c0223q2;
        } else {
            interfaceC0321f10 = interfaceC0321f26;
        }
        InterfaceC0321f interfaceC0321f27 = (InterfaceC0321f) objG15;
        c0761q.p(false);
        c0761q.Q(-1914418403);
        boolean zH16 = c0761q.h(d02);
        Object objG16 = c0761q.G();
        if (zH16 || objG16 == s6) {
            interfaceC0321f11 = interfaceC0321f27;
            r rVar = new r(2, d02, D0.class, "playSoundFx", "playSoundFx(Lcom/stingray/karaoke/scoring/utils/ScoringSoundFxType;Lcom/stingray/shared/soundfx/SoundConfig;)I", 0, 12);
            c0761q.a0(rVar);
            objG16 = rVar;
        } else {
            interfaceC0321f11 = interfaceC0321f27;
        }
        InterfaceC0321f interfaceC0321f28 = (InterfaceC0321f) objG16;
        c0761q.p(false);
        c0761q.Q(-1914416931);
        boolean zH17 = c0761q.h(d02);
        Object objG17 = c0761q.G();
        if (zH17 || objG17 == s6) {
            interfaceC0321f12 = interfaceC0321f28;
            X5.G g12 = new X5.G(1, d02, D0.class, "stopSoundFx", "stopSoundFx(Ljava/lang/Integer;)V", 0, 0);
            c0761q.a0(g12);
            objG17 = g12;
        } else {
            interfaceC0321f12 = interfaceC0321f28;
        }
        InterfaceC0321f interfaceC0321f29 = (InterfaceC0321f) objG17;
        c0761q.p(false);
        boolean zBooleanValue21 = ((Boolean) com.bumptech.glide.c.R(d02.f10120P, c0761q, 0).getValue()).booleanValue();
        boolean zBooleanValue22 = ((Boolean) com.bumptech.glide.c.R(d02.f10124T, c0761q, 0).getValue()).booleanValue();
        boolean zBooleanValue23 = ((Boolean) com.bumptech.glide.c.R(d02.f10125U, c0761q, 0).getValue()).booleanValue();
        boolean zBooleanValue24 = ((Boolean) com.bumptech.glide.c.R(d02.f10126V, c0761q, 0).getValue()).booleanValue();
        InterfaceC2772a interfaceC2772a = (InterfaceC2772a) interfaceC0321f13;
        InterfaceC2772a interfaceC2772a2 = (InterfaceC2772a) interfaceC0321f14;
        InterfaceC2782k interfaceC2782k = (InterfaceC2782k) interfaceC0321f15;
        InterfaceC2772a interfaceC2772a3 = (InterfaceC2772a) interfaceC0321f16;
        InterfaceC2782k interfaceC2782k2 = (InterfaceC2782k) interfaceC0321f;
        InterfaceC2772a interfaceC2772a4 = (InterfaceC2772a) interfaceC0321f2;
        InterfaceC2782k interfaceC2782k3 = (InterfaceC2782k) interfaceC0321f3;
        InterfaceC2772a interfaceC2772a5 = (InterfaceC2772a) interfaceC0321f4;
        InterfaceC2772a interfaceC2772a6 = (InterfaceC2772a) interfaceC0321f5;
        InterfaceC2782k interfaceC2782k4 = (InterfaceC2782k) interfaceC0321f6;
        c0761q.Q(-1914467249);
        boolean zH18 = c0761q.h(d02);
        Object objG18 = c0761q.G();
        if (zH18 || objG18 == s6) {
            objG18 = new C0888v(d02, 0);
            c0761q.a0(objG18);
        }
        c0761q.p(false);
        g0.l lVar3 = lVar;
        String str8 = str3;
        String str9 = str2;
        String str10 = str;
        boolean z11 = z6;
        h(lVar3, str8, str9, str10, z11, zBooleanValue2, zBooleanValue3, zBooleanValue4, zBooleanValue5, zBooleanValue6, interfaceC2772a, interfaceC2772a2, zBooleanValue7, interfaceC2782k, vVar, hVar, d02.f10136f0, jLongValue, leadVocalState, zBooleanValue8, interfaceC2772a3, str7, zBooleanValue9, playerStatus, iIntValue, driveMode, zBooleanValue10, zBooleanValue11, interfaceC2782k2, interfaceC2772a4, interfaceC2782k3, interfaceC2772a5, interfaceC2772a6, zBooleanValue12, interfaceC2782k4, zBooleanValue13, d02.f10111G, d02.f10162z, d02.B, zBooleanValue14, (InterfaceC2782k) objG18, zBooleanValue15, zBooleanValue16, (InterfaceC2772a) interfaceC0321f7, zBooleanValue17, (InterfaceC2772a) interfaceC0321f8, (InterfaceC2772a) interfaceC0321f9, (InterfaceC2772a) interfaceC0321f10, iIntValue2, d02.f10138h0, zBooleanValue18, z10, zBooleanValue19, c1966g, zBooleanValue20, (InterfaceC2782k) interfaceC0321f11, (InterfaceC2785n) interfaceC0321f12, (InterfaceC2782k) interfaceC0321f29, zBooleanValue21, zBooleanValue22, zBooleanValue23, zBooleanValue24, c0761q, 0, 0, 0, 150994944, 0, 4096, 0);
        c0761q.p(false);
    }

    public static final void l(D0 d02, C1930E c1930e, boolean z6, C0761q c0761q) {
        c0761q.Q(-807717231);
        PlayerStatus playerStatus = (PlayerStatus) com.bumptech.glide.c.R(d02.f10130Z, c0761q, 0).getValue();
        c0761q.Q(-1484754358);
        boolean zH = c0761q.h(d02);
        Object objG = c0761q.G();
        if (zH || objG == C0753m.f8270a) {
            objG = new C0888v(d02, 1);
            c0761q.a0(objG);
        }
        c0761q.p(false);
        H.f(playerStatus, (InterfaceC2782k) objG, (SideMenuType) com.bumptech.glide.c.R(d02.f10143m0, c0761q, 0).getValue(), c1930e, z6, c0761q, 24576);
        c0761q.p(false);
    }

    public static final void m(g0.o oVar, N n10, Long l, boolean z6, boolean z10, InterfaceC2782k interfaceC2782k, InterfaceC2772a interfaceC2772a, FontSize fontSize, C0761q c0761q, int i7) {
        int i10;
        m.f(oVar, "modifier");
        m.f(n10, "progressMs");
        m.f(interfaceC2782k, "onSliderValueChange");
        c0761q.S(-734846199);
        if ((i7 & 6) == 0) {
            i10 = (c0761q.f(oVar) ? 4 : 2) | i7;
        } else {
            i10 = i7;
        }
        if ((i7 & 48) == 0) {
            i10 |= c0761q.h(n10) ? 32 : 16;
        }
        if ((i7 & 384) == 0) {
            i10 |= c0761q.f(l) ? 256 : 128;
        }
        if ((i7 & 3072) == 0) {
            i10 |= c0761q.g(z6) ? 2048 : 1024;
        }
        if ((i7 & 24576) == 0) {
            i10 |= c0761q.g(z10) ? 16384 : 8192;
        }
        if ((196608 & i7) == 0) {
            i10 |= c0761q.h(interfaceC2782k) ? 131072 : 65536;
        }
        if ((1572864 & i7) == 0) {
            i10 |= c0761q.h(interfaceC2772a) ? 1048576 : 524288;
        }
        if ((12582912 & i7) == 0) {
            i10 |= c0761q.f(fontSize) ? 8388608 : 4194304;
        }
        if ((4793491 & i10) == 4793490 && c0761q.x()) {
            c0761q.K();
        } else {
            c0761q.Q(1585843628);
            float fLongValue = z6 ? ((Number) com.bumptech.glide.c.R(n10, c0761q, (i10 >> 3) & 14).getValue()).longValue() : 0.0f;
            c0761q.p(false);
            long jLongValue = 0;
            if (z6 && l.longValue() > 0) {
                jLongValue = l.longValue();
            }
            long j3 = jLongValue;
            c0761q.Q(1585850430);
            Object objG = c0761q.G();
            T.S s6 = C0753m.f8270a;
            if (objG == s6) {
                objG = new C.k();
                c0761q.a0(objG);
            }
            C.k kVar = (C.k) objG;
            Object objE = M5.b.e(1585852409, c0761q, false);
            if (objE == s6) {
                objE = C0735d.O(Boolean.FALSE, T.S.f8224q);
                c0761q.a0(objE);
            }
            X x8 = (X) objE;
            Object objE2 = M5.b.e(1585855027, c0761q, false);
            if (objE2 == s6) {
                objE2 = new T5.k(kVar, x8, null);
                c0761q.a0(objE2);
            }
            c0761q.p(false);
            C0735d.f(c0761q, kVar, (InterfaceC2785n) objE2);
            C0735d.a(AbstractC0227s0.l.a(a1.k.l), b0.g.b(-454776887, new T5.m(oVar, z6, z10, j3, fLongValue, interfaceC2782k, interfaceC2772a, kVar, fontSize, x8), c0761q), c0761q, 56);
        }
        C0754m0 c0754m0R = c0761q.r();
        if (c0754m0R != null) {
            c0754m0R.d = new T5.b(oVar, n10, l, z6, z10, interfaceC2782k, interfaceC2772a, fontSize, i7);
        }
    }

    public static final void n(g0.o oVar, String str, C0761q c0761q, int i7) {
        int i10;
        H4.v vVar;
        c0761q.S(-1819572737);
        if ((i7 & 6) == 0) {
            i10 = (c0761q.f(oVar) ? 4 : 2) | i7;
        } else {
            i10 = i7;
        }
        if ((i7 & 48) == 0) {
            i10 |= c0761q.f(str) ? 32 : 16;
        }
        if ((i10 & 19) == 18 && c0761q.x()) {
            c0761q.K();
        } else if (str != null && str.length() != 0) {
            int iE = AbstractC1861K.E(l6.d.c(c0761q).f18696b);
            long jE = AbstractC1861K.E(C1891t.f19451j);
            c0761q.Q(1884069665);
            QRCodeGenerator$QrShape qRCodeGenerator$QrShape = QRCodeGenerator$QrShape.Default;
            m.f("QR CODE: ".concat(str), "text");
            D4.d dVar = new D4.d(str);
            F4.d dVar2 = new F4.d();
            com.bumptech.glide.d.c(4278190080L);
            H4.n nVar = new H4.n(null, 0.0f, null, null, 63);
            D4.f fVar = new D4.f();
            H4.a aVar = new H4.a(new H4.f(com.bumptech.glide.d.c(jE)), 3);
            int i11 = H6.a.f2838a[qRCodeGenerator$QrShape.ordinal()];
            if (i11 == 1) {
                vVar = new H4.v(new s(0.0f), new H4.d(0.0f), new H4.l(0.0f), 18);
            } else {
                if (i11 != 2) {
                    throw new A9.k();
                }
                vVar = new H4.v(new s(0.5f), new H4.d(0.25f), new H4.l(0.25f), 18);
            }
            G4.b bVar = new G4.b(dVar, new G4.c(0.1f, dVar2, vVar, new H4.j(new H4.f(iE), new H4.f(iE), new H4.f(iE), 2), nVar, aVar, fVar), null);
            c0761q.p(false);
            Object obj = I4.c.f2942a;
            c0761q.R(1756822313);
            c0761q.R(-1791785024);
            boolean zF = c0761q.f(bVar);
            Object objG = c0761q.G();
            if (zF || objG == C0753m.f8270a) {
                Drawable drawableMutate = bVar.mutate();
                m.e(drawableMutate, "mutate(...)");
                objG = new DrawablePainter(drawableMutate);
                c0761q.a0(objG);
            }
            c0761q.p(false);
            c0761q.p(false);
            h6.q.a((AbstractC2268a) objG, null, oVar, null, C0102i.f903b, 0.0f, null, c0761q, ((i10 << 6) & 896) | 24624, 104);
        }
        C0754m0 c0754m0R = c0761q.r();
        if (c0754m0R != null) {
            c0754m0R.d = new T0(oVar, str, i7, 1);
        }
    }

    public static final void o(String str, InterfaceC2772a interfaceC2772a, C0761q c0761q, int i7) {
        int i10;
        m.f(str, "id");
        m.f(interfaceC2772a, "onConfirm");
        c0761q.S(-894021189);
        if ((i7 & 6) == 0) {
            i10 = (c0761q.f(str) ? 4 : 2) | i7;
        } else {
            i10 = i7;
        }
        if ((i7 & 48) == 0) {
            i10 |= c0761q.h(interfaceC2772a) ? 32 : 16;
        }
        if ((i10 & 19) == 18 && c0761q.x()) {
            c0761q.K();
        } else {
            AbstractC1274a.h(str, true, false, false, false, b0.g.b(63884738, new S5.m(2, str, interfaceC2772a), c0761q), c0761q, (i10 & 14) | 196656, 28);
        }
        C0754m0 c0754m0R = c0761q.r();
        if (c0754m0R != null) {
            c0754m0R.d = new S5.i(str, interfaceC2772a, i7, 2);
        }
    }

    public static final void p(String str, String str2, boolean z6, InterfaceC2782k interfaceC2782k, C0761q c0761q, int i7) {
        int i10;
        m.f(interfaceC2782k, "onSwitchChange");
        c0761q.S(-1449200447);
        if ((i7 & 6) == 0) {
            i10 = (c0761q.f(str) ? 4 : 2) | i7;
        } else {
            i10 = i7;
        }
        if ((i7 & 48) == 0) {
            i10 |= c0761q.f(str2) ? 32 : 16;
        }
        if ((i7 & 384) == 0) {
            i10 |= c0761q.g(z6) ? 256 : 128;
        }
        if ((i7 & 3072) == 0) {
            i10 |= c0761q.h(interfaceC2782k) ? 2048 : 1024;
        }
        if ((i10 & 1171) == 1170 && c0761q.x()) {
            c0761q.K();
        } else {
            g0.l lVar = g0.l.f14684a;
            g0.o oVarK = androidx.compose.foundation.layout.a.k(h6.i.c(androidx.compose.foundation.layout.c.o(lVar, 300), 2, l6.d.c(c0761q).f18702f, AbstractC1861K.f19381a), c.f19252a.F(c0761q));
            S sB = D.P.b(AbstractC0127i.f1076a, g0.b.f14669u, c0761q, 0);
            int i11 = c0761q.f8304P;
            InterfaceC0744h0 interfaceC0744h0M = c0761q.m();
            g0.o oVarD = g0.a.d(c0761q, oVarK);
            InterfaceC0161k.f1681b.getClass();
            C0159i c0159i = C0160j.f1678b;
            c0761q.U();
            if (c0761q.f8303O) {
                c0761q.l(c0159i);
            } else {
                c0761q.d0();
            }
            C0158h c0158h = C0160j.f1679e;
            C0735d.V(c0761q, sB, c0158h);
            C0158h c0158h2 = C0160j.d;
            C0735d.V(c0761q, interfaceC0744h0M, c0158h2);
            C0158h c0158h3 = C0160j.f1680f;
            if (c0761q.f8303O || !m.a(c0761q.G(), Integer.valueOf(i11))) {
                AbstractC1347d.l(i11, c0761q, i11, c0158h3);
            }
            C0158h c0158h4 = C0160j.c;
            C0735d.V(c0761q, oVarD, c0158h4);
            g0.o oVarB = T.b(lVar, 1.0f);
            C0141x c0141xA = AbstractC0139v.a(AbstractC0127i.c, g0.b.f14672x, c0761q, 0);
            int i12 = c0761q.f8304P;
            InterfaceC0744h0 interfaceC0744h0M2 = c0761q.m();
            g0.o oVarD2 = g0.a.d(c0761q, oVarB);
            c0761q.U();
            if (c0761q.f8303O) {
                c0761q.l(c0159i);
            } else {
                c0761q.d0();
            }
            C0735d.V(c0761q, c0141xA, c0158h);
            C0735d.V(c0761q, interfaceC0744h0M2, c0158h2);
            if (c0761q.f8303O || !m.a(c0761q.G(), Integer.valueOf(i12))) {
                AbstractC1347d.l(i12, c0761q, i12, c0158h3);
            }
            C0735d.V(c0761q, oVarD2, c0158h4);
            c1.b(str, null, l6.d.c(c0761q).f18696b, 0L, null, null, null, 0L, null, null, 0L, 0, false, 0, 0, null, new N0.L(0L, AbstractC0904a.I(20), S0.A.f7803v, null, null, 0L, 0, 0L, null, 16777209), c0761q, i10 & 14, 1572864, 65530);
            c1.b(str2, null, l6.d.c(c0761q).f18696b, 0L, null, null, null, 0L, null, null, 0L, 0, false, 0, 0, null, new N0.L(C1891t.c, AbstractC0904a.I(16), null, null, null, 0L, 0, 0L, null, 16777212), c0761q, (i10 >> 3) & 14, 0, 65530);
            c0761q.p(true);
            H.j(null, null, null, false, z6, interfaceC2782k, 0.0f, null, null, 0, c0761q, (i10 << 6) & 516096, 975);
            c0761q.p(true);
        }
        C0754m0 c0754m0R = c0761q.r();
        if (c0754m0R != null) {
            c0754m0R.d = new Q5.r(str, str2, z6, interfaceC2782k, i7);
        }
    }

    public static final void q(String str, String str2, String str3, boolean z6, boolean z10, int i7, int i10, InterfaceC2772a interfaceC2772a, InterfaceC2772a interfaceC2772a2, InterfaceC2772a interfaceC2772a3, C0761q c0761q, int i11) {
        int i12;
        m.f(str, "id");
        m.f(str2, "title");
        m.f(str3, "image");
        m.f(interfaceC2772a, "onSingNow");
        m.f(interfaceC2772a2, "onSingLast");
        m.f(interfaceC2772a3, "onResetQueue");
        c0761q.S(516610152);
        if ((i11 & 6) == 0) {
            i12 = (c0761q.f(str) ? 4 : 2) | i11;
        } else {
            i12 = i11;
        }
        if ((i11 & 48) == 0) {
            i12 |= c0761q.f(str2) ? 32 : 16;
        }
        if ((i11 & 384) == 0) {
            i12 |= c0761q.f(str3) ? 256 : 128;
        }
        if ((i11 & 3072) == 0) {
            i12 |= c0761q.g(z6) ? 2048 : 1024;
        }
        if ((i11 & 24576) == 0) {
            i12 |= c0761q.g(z10) ? 16384 : 8192;
        }
        if ((196608 & i11) == 0) {
            i12 |= c0761q.d(i7) ? 131072 : 65536;
        }
        if ((1572864 & i11) == 0) {
            i12 |= c0761q.d(i10) ? 1048576 : 524288;
        }
        if ((12582912 & i11) == 0) {
            i12 |= c0761q.h(interfaceC2772a) ? 8388608 : 4194304;
        }
        if ((100663296 & i11) == 0) {
            i12 |= c0761q.h(interfaceC2772a2) ? 67108864 : 33554432;
        }
        if ((805306368 & i11) == 0) {
            i12 |= c0761q.h(interfaceC2772a3) ? 536870912 : 268435456;
        }
        int i13 = i12;
        if ((i13 & 306783379) == 306783378 && c0761q.x()) {
            c0761q.K();
        } else {
            LayoutMode layoutMode = (LayoutMode) c0761q.k(AbstractC1692D.f18238a);
            AbstractC1274a.h(str, true, false, false, AbstractC1692D.b(layoutMode) || layoutMode == LayoutMode.WIDE_ULTRA, b0.g.b(1464822785, new t(str, str3, layoutMode, z6, z10, str2, i10, i7, interfaceC2772a, interfaceC2772a2, interfaceC2772a3), c0761q), c0761q, (i13 & 14) | 199728, 4);
        }
        C0754m0 c0754m0R = c0761q.r();
        if (c0754m0R != null) {
            c0754m0R.d = new S5.s(str, str2, str3, z6, z10, i7, i10, interfaceC2772a, interfaceC2772a2, interfaceC2772a3, i11);
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:55:0x0093  */
    /* JADX WARN: Removed duplicated region for block: B:57:0x0097  */
    /* JADX WARN: Removed duplicated region for block: B:66:0x00b1  */
    /* JADX WARN: Removed duplicated region for block: B:70:0x00c4  */
    /* JADX WARN: Removed duplicated region for block: B:80:0x00e4  */
    /* JADX WARN: Removed duplicated region for block: B:82:0x00e8  */
    /* JADX WARN: Removed duplicated region for block: B:85:0x00f3  */
    /* JADX WARN: Removed duplicated region for block: B:88:0x00fd  */
    /* JADX WARN: Removed duplicated region for block: B:92:0x0139  */
    /* JADX WARN: Removed duplicated region for block: B:94:? A[RETURN, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static final void r(final g0.o r24, final N0.C0411f r25, java.lang.Integer r26, float r27, final java.util.ArrayList r28, final z7.InterfaceC2772a r29, boolean r30, boolean r31, T.C0761q r32, final int r33, final int r34) {
        /*
            Method dump skipped, instruction units count: 338
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: C7.a.r(g0.o, N0.f, java.lang.Integer, float, java.util.ArrayList, z7.a, boolean, boolean, T.q, int, int):void");
    }

    public static final void s(final g0.o oVar, final C0411f c0411f, final int i7, final float f10, final KaraokeItem karaokeItem, final List list, final InterfaceC2772a interfaceC2772a, final InterfaceC2785n interfaceC2785n, final D.L l, C0761q c0761q, final int i10) throws XmlPullParserException, IOException {
        int i11;
        g0.l lVar;
        KaraokeItem karaokeItem2;
        g0.o oVar2;
        int i12;
        g0.l lVar2;
        c0761q.S(734094027);
        if ((i10 & 6) == 0) {
            i11 = (c0761q.f(oVar) ? 4 : 2) | i10;
        } else {
            i11 = i10;
        }
        if ((i10 & 48) == 0) {
            i11 |= c0761q.f(c0411f) ? 32 : 16;
        }
        if ((i10 & 384) == 0) {
            i11 |= c0761q.d(i7) ? 256 : 128;
        }
        if ((i10 & 3072) == 0) {
            i11 |= c0761q.c(f10) ? 2048 : 1024;
        }
        if ((i10 & 24576) == 0) {
            i11 |= c0761q.h(karaokeItem) ? 16384 : 8192;
        }
        if ((196608 & i10) == 0) {
            i11 |= c0761q.h(list) ? 131072 : 65536;
        }
        if ((1572864 & i10) == 0) {
            i11 |= c0761q.h(interfaceC2772a) ? 1048576 : 524288;
        }
        if ((12582912 & i10) == 0) {
            i11 |= c0761q.h(interfaceC2785n) ? 8388608 : 4194304;
        }
        if ((i10 & 100663296) == 0) {
            i11 |= c0761q.f(l) ? 67108864 : 33554432;
        }
        int i13 = i11;
        if ((i13 & 38347923) == 38347922 && c0761q.x()) {
            c0761q.K();
        } else {
            g0.o oVarO = androidx.compose.foundation.layout.a.o(oVar, 0.0f, AbstractC2673a.K(l, c0761q), 0.0f, AbstractC2673a.H(l, c0761q), 5);
            C0119a c0119a = AbstractC0127i.f1076a;
            m6.b bVar = c.f19252a;
            C0124f c0124f = new C0124f(bVar.H(6, c0761q));
            g0.e eVar = g0.b.f14672x;
            C0141x c0141xA = AbstractC0139v.a(c0124f, eVar, c0761q, 0);
            int i14 = c0761q.f8304P;
            InterfaceC0744h0 interfaceC0744h0M = c0761q.m();
            g0.o oVarD = g0.a.d(c0761q, oVarO);
            InterfaceC0161k.f1681b.getClass();
            C0159i c0159i = C0160j.f1678b;
            c0761q.U();
            if (c0761q.f8303O) {
                c0761q.l(c0159i);
            } else {
                c0761q.d0();
            }
            C0158h c0158h = C0160j.f1679e;
            C0735d.V(c0761q, c0141xA, c0158h);
            C0158h c0158h2 = C0160j.d;
            C0735d.V(c0761q, interfaceC0744h0M, c0158h2);
            C0158h c0158h3 = C0160j.f1680f;
            if (c0761q.f8303O || !m.a(c0761q.G(), Integer.valueOf(i14))) {
                AbstractC1347d.l(i14, c0761q, i14, c0158h3);
            }
            C0158h c0158h4 = C0160j.c;
            C0735d.V(c0761q, oVarD, c0158h4);
            g0.l lVar3 = g0.l.f14684a;
            g0.o oVarM = androidx.compose.foundation.layout.a.m(androidx.compose.foundation.a.b(androidx.compose.foundation.layout.c.d(lVar3, 1.0f), l6.d.c(c0761q).f18710j, AbstractC1861K.f19381a), 0.0f, bVar.H(6, c0761q), 1);
            S sB = D.P.b(new C0124f(bVar.F(c0761q)), g0.b.f14670v, c0761q, 48);
            int i15 = c0761q.f8304P;
            InterfaceC0744h0 interfaceC0744h0M2 = c0761q.m();
            g0.o oVarD2 = g0.a.d(c0761q, oVarM);
            c0761q.U();
            if (c0761q.f8303O) {
                c0761q.l(c0159i);
            } else {
                c0761q.d0();
            }
            C0735d.V(c0761q, sB, c0158h);
            C0735d.V(c0761q, interfaceC0744h0M2, c0158h2);
            if (c0761q.f8303O || !m.a(c0761q.G(), Integer.valueOf(i15))) {
                AbstractC1347d.l(i15, c0761q, i15, c0158h3);
            }
            C0735d.V(c0761q, oVarD2, c0158h4);
            c1.c(c0411f, androidx.compose.foundation.layout.a.o(T.b(lVar3, 1.0f), AbstractC2673a.J(l, c0761q), 0.0f, AbstractC2673a.I(l, c0761q), 0.0f, 10), l6.d.c(c0761q).f18696b, h6.f.g(FontSize.H9, c0761q), null, null, null, 0L, null, null, 0L, 2, false, 1, 0, null, null, ((O5.d) c0761q.k(O5.c.f5157b)).f5158a, c0761q, (i13 >> 3) & 14, 3120, 120816);
            c0761q.Q(-509709727);
            if (interfaceC2772a != null) {
                f(androidx.compose.foundation.layout.a.o(lVar3, 0.0f, 0.0f, AbstractC2673a.I(l, c0761q), 0.0f, 11), l6.d.c(c0761q).d, interfaceC2772a, c0761q, (i13 >> 12) & 896, 0);
            }
            c0761q.p(false);
            c0761q.p(true);
            int size = list.size();
            int i16 = size > i7 ? i7 : size;
            g0.o oVarO2 = androidx.compose.foundation.layout.a.o(androidx.compose.foundation.layout.c.r(lVar3), AbstractC2673a.J(l, c0761q), 0.0f, AbstractC2673a.I(l, c0761q), 0.0f, 10);
            C0141x c0141xA2 = AbstractC0139v.a(AbstractC0127i.c, eVar, c0761q, 0);
            int i17 = c0761q.f8304P;
            InterfaceC0744h0 interfaceC0744h0M3 = c0761q.m();
            g0.o oVarD3 = g0.a.d(c0761q, oVarO2);
            c0761q.U();
            if (c0761q.f8303O) {
                c0761q.l(c0159i);
            } else {
                c0761q.d0();
            }
            C0735d.V(c0761q, c0141xA2, c0158h);
            C0735d.V(c0761q, interfaceC0744h0M3, c0158h2);
            if (c0761q.f8303O || !m.a(c0761q.G(), Integer.valueOf(i17))) {
                AbstractC1347d.l(i17, c0761q, i17, c0158h3);
            }
            C0735d.V(c0761q, oVarD3, c0158h4);
            c0761q.Q(-509690855);
            int i18 = 0;
            while (i18 < i16) {
                KaraokeItemType karaokeItemType = ((KaraokeItem) list.get(i18)).f13109e;
                int i19 = karaokeItemType == null ? -1 : G2.f6370a[karaokeItemType.ordinal()];
                T.S s6 = C0753m.f8270a;
                if (i19 == 1) {
                    c0761q.Q(-509687859);
                    lVar = lVar3;
                    g0.o oVarM2 = androidx.compose.foundation.layout.a.m(lVar, 0.0f, 0.5f * f10, 1);
                    KaraokeItem karaokeItem3 = (KaraokeItem) list.get(i18);
                    c0761q.Q(-509682332);
                    boolean zH = ((i13 & 29360128) == 8388608) | c0761q.h(list) | c0761q.d(i18) | c0761q.h(karaokeItem);
                    Object objG = c0761q.G();
                    if (zH || objG == s6) {
                        karaokeItem2 = karaokeItem3;
                        oVar2 = oVarM2;
                        B2 b22 = new B2(interfaceC2785n, list, i18, karaokeItem, 0);
                        c0761q.a0(b22);
                        objG = b22;
                    } else {
                        karaokeItem2 = karaokeItem3;
                        oVar2 = oVarM2;
                    }
                    c0761q.p(false);
                    com.bumptech.glide.c.p(oVar2, karaokeItem2, (InterfaceC2772a) objG, c0761q, 0);
                    c0761q.p(false);
                    i12 = 1;
                } else if (i19 != 2) {
                    c0761q.Q(1380290642);
                    c0761q.p(false);
                    i12 = 1;
                    lVar = lVar3;
                } else {
                    c0761q.Q(1379880264);
                    if (karaokeItem.f13110f == KaraokeItemChildType.ARTIST) {
                        KaraokeItem karaokeItem4 = (KaraokeItem) list.get(i18);
                        c0761q.Q(-509669948);
                        boolean zH2 = ((i13 & 29360128) == 8388608) | c0761q.h(list) | c0761q.d(i18) | c0761q.h(karaokeItem);
                        Object objG2 = c0761q.G();
                        if (zH2 || objG2 == s6) {
                            lVar2 = lVar3;
                            B2 b23 = new B2(interfaceC2785n, list, i18, karaokeItem, 1);
                            c0761q.a0(b23);
                            objG2 = b23;
                        } else {
                            lVar2 = lVar3;
                        }
                        c0761q.p(false);
                        com.bumptech.glide.c.a(0, 1, c0761q, karaokeItem4, null, (InterfaceC2772a) objG2);
                    } else {
                        lVar2 = lVar3;
                    }
                    c0761q.p(false);
                    lVar = lVar2;
                    i12 = 1;
                }
                i18 += i12;
                lVar3 = lVar;
            }
            M5.b.n(c0761q, false, true, true);
        }
        C0754m0 c0754m0R = c0761q.r();
        if (c0754m0R != null) {
            c0754m0R.d = new InterfaceC2785n() { // from class: Q5.C2
                @Override // z7.InterfaceC2785n
                public final Object invoke(Object obj, Object obj2) throws XmlPullParserException, IOException {
                    ((Integer) obj2).getClass();
                    int iZ = C0735d.Z(i10 | 1);
                    C0411f c0411f2 = c0411f;
                    KaraokeItem karaokeItem5 = karaokeItem;
                    D.L l10 = l;
                    C7.a.s(oVar, c0411f2, i7, f10, karaokeItem5, list, interfaceC2772a, interfaceC2785n, l10, (C0761q) obj, iZ);
                    return m7.q.f19267a;
                }
            };
        }
    }

    public static final void t(g0.o oVar, Integer num, final List list, final String str, final D.L l, final InterfaceC2785n interfaceC2785n, final InterfaceC2782k interfaceC2782k, D d, C0761q c0761q, int i7) {
        int i10;
        Integer num2;
        int i11;
        C0124f c0124f;
        Integer num3;
        m.f(list, "items");
        m.f(interfaceC2785n, "onTileClicked");
        m.f(interfaceC2782k, "onMoreClicked");
        c0761q.S(1589784555);
        if ((i7 & 6) == 0) {
            i10 = (c0761q.f(oVar) ? 4 : 2) | i7;
        } else {
            i10 = i7;
        }
        int i12 = i10 | 48;
        if ((i7 & 384) == 0) {
            i12 |= c0761q.h(list) ? 256 : 128;
        }
        if ((i7 & 3072) == 0) {
            i12 |= c0761q.f(str) ? 2048 : 1024;
        }
        if ((i7 & 24576) == 0) {
            i12 |= c0761q.f(l) ? 16384 : 8192;
        }
        if ((196608 & i7) == 0) {
            i12 |= c0761q.h(interfaceC2785n) ? 131072 : 65536;
        }
        if ((1572864 & i7) == 0) {
            i12 |= c0761q.h(interfaceC2782k) ? 1048576 : 524288;
        }
        if ((12582912 & i7) == 0) {
            i12 |= c0761q.f(d) ? 8388608 : 4194304;
        }
        if ((i12 & 4793491) == 4793490 && c0761q.x()) {
            c0761q.K();
            num3 = num;
        } else {
            c0761q.M();
            if ((i7 & 1) == 0 || c0761q.w()) {
                num2 = null;
            } else {
                c0761q.K();
                num2 = num;
            }
            c0761q.q();
            m6.b bVar = c.f19252a;
            final float fH = bVar.H(6, c0761q) * 2;
            c0761q.Q(543308792);
            final int integer = num2 == null ? ((Context) c0761q.k(AndroidCompositionLocals_androidKt.f11688b)).getResources().getInteger(R.integer.number_of_tiles_in_vertical_swimlane) : num2.intValue();
            c0761q.p(false);
            D.L lC = androidx.compose.foundation.layout.a.c(0.0f, AbstractC2673a.K(l, c0761q), 0.0f, AbstractC2673a.H(l, c0761q), 5);
            C0119a c0119a = AbstractC0127i.f1076a;
            C0124f c0124f2 = new C0124f(bVar.v0(c0761q));
            c0761q.Q(543321902);
            boolean zH = c0761q.h(list) | ((i12 & 7168) == 2048) | c0761q.d(integer) | ((3670016 & i12) == 1048576) | c0761q.c(fH) | ((458752 & i12) == 131072) | ((57344 & i12) == 16384);
            Object objG = c0761q.G();
            if (zH || objG == C0753m.f8270a) {
                i11 = i12;
                c0124f = c0124f2;
                InterfaceC2782k interfaceC2782k2 = new InterfaceC2782k() { // from class: Q5.A2
                    @Override // z7.InterfaceC2782k
                    public final Object b(Object obj) {
                        E.k kVar = (E.k) obj;
                        A7.m.f(kVar, "$this$LazyColumn");
                        List list2 = list;
                        kVar.n(list2.size(), null, new C0558d0(5, list2), new b0.b(-632812321, new F2(list2, str, integer, interfaceC2782k, fH, interfaceC2785n, l), true));
                        return m7.q.f19267a;
                    }
                };
                c0761q.a0(interfaceC2782k2);
                objG = interfaceC2782k2;
            } else {
                i11 = i12;
                c0124f = c0124f2;
            }
            c0761q.p(false);
            io.sentry.config.a.q(oVar, d, lC, false, c0124f, null, null, false, (InterfaceC2782k) objG, c0761q, (i11 & 14) | ((i11 >> 18) & 112), 232);
            num3 = num2;
        }
        C0754m0 c0754m0R = c0761q.r();
        if (c0754m0R != null) {
            c0754m0R.d = new C0638x1(oVar, num3, list, str, l, interfaceC2785n, interfaceC2782k, d, i7);
        }
    }

    public static final K8.a u(AbstractC0287w abstractC0287w) {
        K8.d dVar;
        m.f(abstractC0287w, "type");
        if (AbstractC0268c.j(abstractC0287w)) {
            K8.a aVarU = u(AbstractC0268c.k(abstractC0287w));
            K8.a aVarU2 = u(AbstractC0268c.y(abstractC0287w));
            return new K8.a(AbstractC0268c.g(C0269d.j(AbstractC0268c.k((AbstractC0287w) aVarU.f3858a), AbstractC0268c.y((AbstractC0287w) aVarU2.f3858a)), abstractC0287w), AbstractC0268c.g(C0269d.j(AbstractC0268c.k((AbstractC0287w) aVarU.f3859b), AbstractC0268c.y((AbstractC0287w) aVarU2.f3859b)), abstractC0287w));
        }
        F8.K kB0 = abstractC0287w.B0();
        boolean z6 = true;
        if (abstractC0287w.B0() instanceof InterfaceC2414b) {
            m.d(kB0, "null cannot be cast to non-null type org.jetbrains.kotlin.resolve.calls.inference.CapturedTypeConstructor");
            O oA = ((InterfaceC2414b) kB0).a();
            AbstractC0287w abstractC0287wB = oA.b();
            m.e(abstractC0287wB, "typeProjection.type");
            AbstractC0287w abstractC0287wH = Y.h(abstractC0287wB, abstractC0287w.F0());
            int iC = AbstractC2641j.c(oA.a());
            if (iC == 1) {
                return new K8.a(abstractC0287wH, AbstractC1274a.L(abstractC0287w).n());
            }
            if (iC == 2) {
                return new K8.a(Y.h(AbstractC1274a.L(abstractC0287w).m(), abstractC0287w.F0()), abstractC0287wH);
            }
            throw new AssertionError("Only nontrivial projections should have been captured, not: " + oA);
        }
        if (abstractC0287w.o0().isEmpty() || abstractC0287w.o0().size() != kB0.p().size()) {
            return new K8.a(abstractC0287w, abstractC0287w);
        }
        ArrayList<K8.d> arrayList = new ArrayList();
        ArrayList arrayList2 = new ArrayList();
        List listO0 = abstractC0287w.o0();
        List listP = kB0.p();
        m.e(listP, "typeConstructor.parameters");
        for (m7.i iVar : AbstractC1998m.F0(listO0, listP)) {
            O o = (O) iVar.l;
            Q q4 = (Q) iVar.f19259m;
            m.e(q4, "typeParameter");
            int iE0 = q4.e0();
            if (iE0 == 0) {
                W.a(35);
                throw null;
            }
            if (o == null) {
                W.a(36);
                throw null;
            }
            W w10 = W.f2454b;
            int iC2 = AbstractC2641j.c(o.c() ? 3 : W.b(iE0, o.a()));
            if (iC2 == 0) {
                AbstractC0287w abstractC0287wB2 = o.b();
                m.e(abstractC0287wB2, "type");
                AbstractC0287w abstractC0287wB3 = o.b();
                m.e(abstractC0287wB3, "type");
                dVar = new K8.d(q4, abstractC0287wB2, abstractC0287wB3);
            } else if (iC2 == 1) {
                AbstractC0287w abstractC0287wB4 = o.b();
                m.e(abstractC0287wB4, "type");
                dVar = new K8.d(q4, abstractC0287wB4, AbstractC2564d.e(q4).n());
            } else {
                if (iC2 != 2) {
                    throw new A9.k();
                }
                A aM = AbstractC2564d.e(q4).m();
                AbstractC0287w abstractC0287wB5 = o.b();
                m.e(abstractC0287wB5, "type");
                dVar = new K8.d(q4, aM, abstractC0287wB5);
            }
            if (o.c()) {
                arrayList.add(dVar);
                arrayList2.add(dVar);
            } else {
                K8.a aVarU3 = u(dVar.f3862b);
                AbstractC0287w abstractC0287w2 = (AbstractC0287w) aVarU3.f3858a;
                AbstractC0287w abstractC0287w3 = (AbstractC0287w) aVarU3.f3859b;
                K8.a aVarU4 = u(dVar.c);
                AbstractC0287w abstractC0287w4 = (AbstractC0287w) aVarU4.f3858a;
                AbstractC0287w abstractC0287w5 = (AbstractC0287w) aVarU4.f3859b;
                Q q10 = dVar.f3861a;
                K8.d dVar2 = new K8.d(q10, abstractC0287w3, abstractC0287w4);
                K8.d dVar3 = new K8.d(q10, abstractC0287w2, abstractC0287w5);
                arrayList.add(dVar2);
                arrayList2.add(dVar3);
            }
        }
        if (arrayList.isEmpty()) {
            z6 = false;
        } else {
            for (K8.d dVar4 : arrayList) {
                dVar4.getClass();
                if (!G8.d.f2722a.b(dVar4.f3862b, dVar4.c)) {
                    break;
                }
            }
            z6 = false;
        }
        return new K8.a(z6 ? AbstractC1274a.L(abstractC0287w).m() : U(abstractC0287w, arrayList), U(abstractC0287w, arrayList2));
    }

    /* JADX WARN: Removed duplicated region for block: B:20:0x0058 A[RETURN] */
    /* JADX WARN: Removed duplicated region for block: B:23:0x0064  */
    /* JADX WARN: Removed duplicated region for block: B:7:0x0013  */
    /* JADX WARN: Type inference failed for: r8v4, types: [java.lang.Object, java.util.List] */
    /* JADX WARN: Type inference failed for: r8v9, types: [java.lang.Object, java.util.List] */
    /* JADX WARN: Unsupported multi-entry loop pattern (BACK_EDGE: B:19:0x0056 -> B:21:0x0059). Please report as a decompilation issue!!! */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static final java.lang.Object v(y0.z r7, s7.AbstractC2408a r8) throws java.lang.Throwable {
        /*
            boolean r0 = r8 instanceof A.C0011e0
            if (r0 == 0) goto L13
            r0 = r8
            A.e0 r0 = (A.C0011e0) r0
            int r1 = r0.f165n
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L13
            int r1 = r1 - r2
            r0.f165n = r1
            goto L18
        L13:
            A.e0 r0 = new A.e0
            r0.<init>(r8)
        L18:
            java.lang.Object r8 = r0.f164m
            r7.a r1 = r7.EnumC2303a.l
            int r2 = r0.f165n
            r3 = 1
            r4 = 0
            if (r2 == 0) goto L32
            if (r2 != r3) goto L2a
            y0.z r7 = r0.l
            h8.AbstractC1386c.u(r8)
            goto L59
        L2a:
            java.lang.IllegalStateException r7 = new java.lang.IllegalStateException
            java.lang.String r8 = "call to 'resume' before 'invoke' with coroutine"
            r7.<init>(r8)
            throw r7
        L32:
            h8.AbstractC1386c.u(r8)
            y0.B r8 = r7.f24110p
            y0.h r8 = r8.f24042D
            java.lang.Object r8 = r8.f24065a
            int r2 = r8.size()
            r5 = r4
        L40:
            if (r5 >= r2) goto L75
            java.lang.Object r6 = r8.get(r5)
            y0.q r6 = (y0.q) r6
            boolean r6 = r6.d
            if (r6 == 0) goto L72
        L4c:
            y0.i r8 = y0.EnumC2731i.f24068n
            r0.l = r7
            r0.f165n = r3
            java.lang.Object r8 = r7.a(r8, r0)
            if (r8 != r1) goto L59
            return r1
        L59:
            y0.h r8 = (y0.C2730h) r8
            java.lang.Object r8 = r8.f24065a
            int r2 = r8.size()
            r5 = r4
        L62:
            if (r5 >= r2) goto L75
            java.lang.Object r6 = r8.get(r5)
            y0.q r6 = (y0.q) r6
            boolean r6 = r6.d
            if (r6 == 0) goto L6f
            goto L4c
        L6f:
            int r5 = r5 + 1
            goto L62
        L72:
            int r5 = r5 + 1
            goto L40
        L75:
            m7.q r7 = m7.q.f19267a
            return r7
        */
        throw new UnsupportedOperationException("Method not decompiled: C7.a.v(y0.z, s7.a):java.lang.Object");
    }

    public static final Object w(C2722B c2722b, InterfaceC2785n interfaceC2785n, InterfaceC2189c interfaceC2189c) {
        Object objH0 = c2722b.H0(new C0013f0(interfaceC2189c.getContext(), interfaceC2785n, null), interfaceC2189c);
        return objH0 == EnumC2303a.l ? objH0 : q.f19267a;
    }

    /* JADX WARN: Removed duplicated region for block: B:7:0x0013  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static final java.lang.Object x(y0.C2722B r10, y0.q r11, s7.AbstractC2410c r12) throws java.lang.Throwable {
        /*
            boolean r0 = r12 instanceof P5.d
            if (r0 == 0) goto L13
            r0 = r12
            P5.d r0 = (P5.d) r0
            int r1 = r0.o
            r2 = -2147483648(0xffffffff80000000, float:-0.0)
            r3 = r1 & r2
            if (r3 == 0) goto L13
            int r1 = r1 - r2
            r0.o = r1
            goto L18
        L13:
            P5.d r0 = new P5.d
            r0.<init>(r12)
        L18:
            java.lang.Object r12 = r0.f5348n
            r7.a r1 = r7.EnumC2303a.l
            int r2 = r0.o
            r3 = 0
            r4 = 1
            if (r2 == 0) goto L34
            if (r2 != r4) goto L2c
            A7.z r10 = r0.f5347m
            y0.q r11 = r0.l
            h8.AbstractC1386c.u(r12)     // Catch: S8.p0 -> L76
            goto L7f
        L2c:
            java.lang.IllegalStateException r10 = new java.lang.IllegalStateException
            java.lang.String r11 = "call to 'resume' before 'invoke' with coroutine"
            r10.<init>(r11)
            throw r10
        L34:
            h8.AbstractC1386c.u(r12)
            A7.z r12 = new A7.z
            r12.<init>()
            A7.z r2 = new A7.z
            r2.<init>()
            r2.l = r11
            r10.getClass()
            E0.F r5 = E0.AbstractC0156f.t(r10)
            F0.W0 r5 = r5.f1466E
            long r5 = r5.c()
            P5.f r7 = new P5.f     // Catch: S8.p0 -> L75
            r7.<init>(r10, r2, r12, r3)     // Catch: S8.p0 -> L75
            r0.l = r11     // Catch: S8.p0 -> L75
            r0.f5347m = r12     // Catch: S8.p0 -> L75
            r0.o = r4     // Catch: S8.p0 -> L75
            r8 = 0
            int r10 = (r5 > r8 ? 1 : (r5 == r8 ? 0 : -1))
            if (r10 <= 0) goto L6d
            S8.q0 r10 = new S8.q0     // Catch: S8.p0 -> L75
            r10.<init>(r5, r0)     // Catch: S8.p0 -> L75
            java.lang.Object r10 = S8.AbstractC0727y.B(r10, r7)     // Catch: S8.p0 -> L75
            if (r10 != r1) goto L7f
            return r1
        L6d:
            S8.p0 r10 = new S8.p0     // Catch: S8.p0 -> L75
            java.lang.String r0 = "Timed out immediately"
            r10.<init>(r0, r3)     // Catch: S8.p0 -> L75
            throw r10     // Catch: S8.p0 -> L75
        L75:
            r10 = r12
        L76:
            java.lang.Object r10 = r10.l
            y0.q r10 = (y0.q) r10
            if (r10 != 0) goto L7e
            r3 = r11
            goto L7f
        L7e:
            r3 = r10
        L7f:
            return r3
        */
        throw new UnsupportedOperationException("Method not decompiled: C7.a.x(y0.B, y0.q, s7.c):java.lang.Object");
    }

    public static final S1.d y(String str) {
        m.f(str, "name");
        return new S1.d(str);
    }

    public static final void z(View view) {
        m.f(view, "<this>");
        P8.l lVarG = P8.n.G(new J(view, null));
        while (lVarG.hasNext()) {
            ArrayList arrayList = L((View) lVarG.next()).f3048a;
            for (int iC = AbstractC1999n.C(arrayList); -1 < iC; iC--) {
                ((U0) arrayList.get(iC)).f2141a.c();
            }
        }
    }
}
