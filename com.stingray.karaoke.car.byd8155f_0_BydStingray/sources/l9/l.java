package l9;

import A.C0022k;
import A.G0;
import A7.E;
import C0.C0102i;
import C0.J;
import C0.U;
import C0.V;
import C0.r;
import D.AbstractC0120b;
import D.AbstractC0127i;
import D.AbstractC0133o;
import D.AbstractC0139v;
import D.C0119a;
import D.C0124f;
import D.C0141x;
import D.C0142y;
import D.P;
import D.T;
import E0.C0158h;
import E0.C0159i;
import E0.C0160j;
import E0.InterfaceC0161k;
import F0.AbstractC0227s0;
import F0.D0;
import F8.AbstractC0287w;
import I5.s;
import I5.y;
import K.C0331b0;
import K.G;
import K.InterfaceC0355n0;
import N0.L;
import O.C0475f;
import O.InterfaceC0480k;
import O.K;
import Q.AbstractC0536v0;
import Q.C0538w0;
import Q.C0540x0;
import Q.EnumC0542y0;
import Q.c1;
import Q5.A;
import Q5.C0545a;
import Q5.C0600n2;
import Q5.C0607p1;
import Q5.C0608p2;
import Q5.C0641y0;
import Q5.F;
import Q5.M;
import Q5.O;
import Q7.InterfaceC0652e;
import Q7.InterfaceC0654g;
import Q7.InterfaceC0666t;
import Q7.N;
import S0.InterfaceC0702n;
import S0.p;
import T.C0730a0;
import T.C0732b0;
import T.C0735d;
import T.C0753m;
import T.C0754m0;
import T.C0761q;
import T.InterfaceC0744h0;
import T.Q0;
import T.R0;
import T.S;
import T.X;
import T7.Q;
import U5.B;
import U5.C;
import U5.D;
import X5.H0;
import a.AbstractC0904a;
import android.content.Context;
import android.graphics.RadialGradient;
import android.os.Build;
import android.os.Bundle;
import android.os.Parcelable;
import androidx.compose.ui.platform.AndroidCompositionLocals_androidKt;
import c8.C1038c;
import com.bumptech.glide.load.ImageHeaderParser$ImageType;
import com.stingray.drivemodedetector.R;
import com.stingray.karaoke.car.player.LeadVocalState;
import com.stingray.karaoke.car.player.PlayerStatus;
import com.stingray.karaoke.car.utils.FontSize;
import d8.C1111C;
import f8.InterfaceC1266b;
import g1.AbstractC1274a;
import g1.q;
import h2.AbstractC1347d;
import h5.v;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.Future;
import java.util.concurrent.atomic.AtomicReference;
import k.C1581a;
import l1.C1731b;
import n0.AbstractC1861K;
import n0.C1873b;
import n0.C1878g;
import n0.C1891t;
import n3.C1930E;
import n4.w;
import n7.AbstractC1998m;
import n7.AbstractC1999n;
import n7.AbstractC2000o;
import o8.C2061f;
import org.xmlpull.v1.XmlPullParserException;
import r0.AbstractC2268a;
import v8.AbstractC2564d;
import x.AbstractC2614C;
import x.AbstractC2637h;
import x.C0;
import x3.AbstractC2673a;
import z7.InterfaceC2772a;
import z7.InterfaceC2782k;
import z7.InterfaceC2785n;

/* JADX INFO: loaded from: classes.dex */
public abstract class l {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final /* synthetic */ int f18873a = 0;

    public static final void A(C1873b c1873b, long j3, float f10, long j10) {
        C1878g c1878gH = AbstractC1861K.h();
        List listD = AbstractC1999n.D(new C1891t(j10), new C1891t(C1891t.f19451j));
        AbstractC1861K.I(listD, null);
        float fD = m0.c.d(j3);
        float fE = m0.c.e(j3);
        int size = listD.size();
        int[] iArr = new int[size];
        for (int i7 = 0; i7 < size; i7++) {
            iArr[i7] = AbstractC1861K.E(((C1891t) listD.get(i7)).f19453a);
        }
        c1878gH.h(new RadialGradient(fD, fE, f10, iArr, (float[]) null, AbstractC1861K.D(0)));
        c1873b.d(f10, j3, c1878gH);
    }

    public static final void B(q qVar, List list) {
        int size = list.size();
        for (int i7 = 0; i7 < size; i7++) {
            J j3 = (J) list.get(i7);
            Object objA = androidx.compose.ui.layout.a.a(j3);
            if (objA == null) {
                j3.i();
                objA = new Y4.e(20);
            }
            C1731b c1731bB = qVar.b(objA.toString());
            if (c1731bB != null) {
                c1731bB.f18393f0 = j3;
                o1.d dVar = c1731bB.f18395g0;
                if (dVar != null) {
                    dVar.f20293g0 = j3;
                }
            }
            j3.i();
        }
    }

    public static void C(String str, boolean z6) {
        if (!z6) {
            throw new IllegalArgumentException(str);
        }
    }

    public static void D(int i7) {
        if (i7 < 0) {
            throw new IllegalArgumentException();
        }
    }

    public static void E(String str, long j3) {
        if (j3 >= 0) {
            return;
        }
        throw new IllegalArgumentException(str + " (" + j3 + ") must be >= 0");
    }

    public static void F(Object obj, String str) {
        if (obj == null) {
            throw new NullPointerException(str);
        }
    }

    /* JADX WARN: Code restructure failed: missing block: B:23:0x0053, code lost:
    
        if (x3.AbstractC2673a.z(r9, r1, A7.m.a(r7, r2) ? r0.getWidth() : N3.d.d(r7.f4005a, r8), A7.m.a(r7, r2) ? r0.getHeight() : N3.d.d(r7.f4006b, r8), r8) == 1.0d) goto L24;
     */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static android.graphics.Bitmap G(android.graphics.drawable.Drawable r5, android.graphics.Bitmap.Config r6, L3.g r7, L3.f r8, boolean r9) {
        /*
            Method dump skipped, instruction units count: 236
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: l9.l.G(android.graphics.drawable.Drawable, android.graphics.Bitmap$Config, L3.g, L3.f, boolean):android.graphics.Bitmap");
    }

    public static final ArrayList H(List list, List list2, InterfaceC0666t interfaceC0666t) {
        A7.m.f(list2, "oldValueParameters");
        list.size();
        list2.size();
        ArrayList<m7.i> arrayListF0 = AbstractC1998m.F0(list, list2);
        ArrayList arrayList = new ArrayList(AbstractC2000o.J(arrayListF0, 10));
        for (m7.i iVar : arrayListF0) {
            AbstractC0287w abstractC0287w = (AbstractC0287w) iVar.l;
            Q q4 = (Q) iVar.f19259m;
            int i7 = q4.f8822r;
            R7.h hVarK = q4.k();
            C2061f name = q4.getName();
            A7.m.e(name, "oldParameter.name");
            boolean zD1 = q4.d1();
            AbstractC0287w abstractC0287wF = q4.f8826v != null ? AbstractC2564d.j(interfaceC0666t).l().f(abstractC0287w) : null;
            N nG = q4.g();
            A7.m.e(nG, "oldParameter.source");
            arrayList.add(new Q(interfaceC0666t, null, i7, hVarK, name, abstractC0287w, zD1, q4.f8824t, q4.f8825u, abstractC0287wF, nG));
        }
        return arrayList;
    }

    /* JADX WARN: Removed duplicated region for block: B:41:0x00bd  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static V7.c I(java.lang.Class r14) {
        /*
            Method dump skipped, instruction units count: 223
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: l9.l.I(java.lang.Class):V7.c");
    }

    public static final boolean J(int i7, int i10) {
        return i7 == i10;
    }

    public static final boolean K(int i7, int i10) {
        return i7 == i10;
    }

    public static N.b L(N.b bVar, a1.k kVar, L l, a1.b bVar2, InterfaceC0702n interfaceC0702n) {
        if (bVar != null && kVar == bVar.f4219a && A7.m.a(l, bVar.f4220b) && bVar2.getDensity() == bVar.c.l && interfaceC0702n == bVar.d) {
            return bVar;
        }
        N.b bVar3 = N.b.f4218h;
        if (bVar3 != null && kVar == bVar3.f4219a && A7.m.a(l, bVar3.f4220b) && bVar2.getDensity() == bVar3.c.l && interfaceC0702n == bVar3.d) {
            return bVar3;
        }
        N.b bVar4 = new N.b(kVar, P8.n.T(l, kVar), new a1.c(bVar2.getDensity(), bVar2.p()), interfaceC0702n);
        N.b.f4218h = bVar4;
        return bVar4;
    }

    public static final int M(L7.e eVar) {
        A7.m.f(eVar, "<this>");
        return eVar.r().size();
    }

    public static Object N(Future future) {
        Object obj;
        if (!future.isDone()) {
            throw new IllegalStateException(com.bumptech.glide.c.r0("Future was expected to be done: %s", future));
        }
        boolean z6 = false;
        while (true) {
            try {
                obj = future.get();
                break;
            } catch (InterruptedException unused) {
                z6 = true;
            } catch (Throwable th) {
                if (z6) {
                    Thread.currentThread().interrupt();
                }
                throw th;
            }
        }
        if (z6) {
            Thread.currentThread().interrupt();
        }
        return obj;
    }

    public static int O(ArrayList arrayList, InputStream inputStream, F.l lVar) throws IOException {
        if (inputStream == null) {
            return -1;
        }
        if (!inputStream.markSupported()) {
            inputStream = new w(inputStream, lVar);
        }
        inputStream.mark(5242880);
        int size = arrayList.size();
        for (int i7 = 0; i7 < size; i7++) {
            try {
                int iC = ((e4.d) arrayList.get(i7)).c(inputStream, lVar);
                if (iC != -1) {
                    return iC;
                }
            } finally {
                inputStream.reset();
            }
        }
        return -1;
    }

    public static Object P(String str, Bundle bundle) {
        if (Build.VERSION.SDK_INT >= 34) {
            return B1.c.a(str, bundle);
        }
        Parcelable parcelable = bundle.getParcelable(str);
        if (C1581a.class.isInstance(parcelable)) {
            return parcelable;
        }
        return null;
    }

    public static final C1111C Q(InterfaceC0652e interfaceC0652e) {
        InterfaceC0652e interfaceC0652e2;
        InterfaceC0654g interfaceC0654gN;
        A7.m.f(interfaceC0652e, "<this>");
        int i7 = AbstractC2564d.f23040a;
        Iterator it = interfaceC0652e.j().B0().o().iterator();
        while (true) {
            if (!it.hasNext()) {
                interfaceC0652e2 = null;
                break;
            }
            AbstractC0287w abstractC0287w = (AbstractC0287w) it.next();
            if (!N7.i.w(abstractC0287w)) {
                interfaceC0654gN = abstractC0287w.B0().n();
                if (r8.d.n(interfaceC0654gN, 1) || r8.d.n(interfaceC0654gN, 3)) {
                    break;
                }
            }
        }
        A7.m.d(interfaceC0654gN, "null cannot be cast to non-null type org.jetbrains.kotlin.descriptors.ClassDescriptor");
        interfaceC0652e2 = (InterfaceC0652e) interfaceC0654gN;
        if (interfaceC0652e2 == null) {
            return null;
        }
        y8.n nVarQ0 = interfaceC0652e2.q0();
        C1111C c1111c = nVarQ0 instanceof C1111C ? (C1111C) nVarQ0 : null;
        return c1111c == null ? Q(interfaceC0652e2) : c1111c;
    }

    public static ImageHeaderParser$ImageType R(ArrayList arrayList, InputStream inputStream, F.l lVar) throws IOException {
        if (inputStream == null) {
            return ImageHeaderParser$ImageType.UNKNOWN;
        }
        if (!inputStream.markSupported()) {
            inputStream = new w(inputStream, lVar);
        }
        inputStream.mark(5242880);
        int size = arrayList.size();
        for (int i7 = 0; i7 < size; i7++) {
            try {
                ImageHeaderParser$ImageType imageHeaderParser$ImageTypeD = ((e4.d) arrayList.get(i7)).d(inputStream);
                inputStream.reset();
                if (imageHeaderParser$ImageTypeD != ImageHeaderParser$ImageType.UNKNOWN) {
                    return imageHeaderParser$ImageTypeD;
                }
            } catch (Throwable th) {
                inputStream.reset();
                throw th;
            }
        }
        return ImageHeaderParser$ImageType.UNKNOWN;
    }

    public static ImageHeaderParser$ImageType S(ArrayList arrayList, ByteBuffer byteBuffer) {
        if (byteBuffer == null) {
            return ImageHeaderParser$ImageType.UNKNOWN;
        }
        int size = arrayList.size();
        for (int i7 = 0; i7 < size; i7++) {
            try {
                ImageHeaderParser$ImageType imageHeaderParser$ImageTypeA = ((e4.d) arrayList.get(i7)).a(byteBuffer);
                AtomicReference atomicReference = A4.c.f414a;
                if (imageHeaderParser$ImageTypeA != ImageHeaderParser$ImageType.UNKNOWN) {
                    return imageHeaderParser$ImageTypeA;
                }
            } catch (Throwable th) {
                AtomicReference atomicReference2 = A4.c.f414a;
                throw th;
            }
        }
        return ImageHeaderParser$ImageType.UNKNOWN;
    }

    public static v T(Object obj) {
        return obj == null ? v.f15564m : new v(obj);
    }

    public static final boolean U(K k10, boolean z6) {
        r rVarC;
        C0331b0 c0331b0 = k10.d;
        if (c0331b0 == null || (rVarC = c0331b0.c()) == null) {
            return false;
        }
        m0.d dVarO0 = io.sentry.config.a.o0(rVarC);
        long jI = k10.i(z6);
        float fD = m0.c.d(jI);
        if (dVarO0.f18882a > fD || fD > dVarO0.c) {
            return false;
        }
        float fE = m0.c.e(jI);
        return dVarO0.f18883b <= fE && fE <= dVarO0.d;
    }

    public static final io.sentry.internal.debugmeta.c V(InterfaceC2785n interfaceC2785n, InterfaceC2782k interfaceC2782k) {
        G0 g02 = new G0(interfaceC2785n);
        E.e(1, interfaceC2782k);
        io.sentry.internal.debugmeta.c cVar = d0.o.f13275a;
        return new io.sentry.internal.debugmeta.c(g02, 15, interfaceC2782k);
    }

    /* JADX WARN: Removed duplicated region for block: B:105:0x0314  */
    /* JADX WARN: Removed duplicated region for block: B:143:0x0436  */
    /* JADX WARN: Removed duplicated region for block: B:144:0x043b  */
    /* JADX WARN: Removed duplicated region for block: B:147:0x044b  */
    /* JADX WARN: Removed duplicated region for block: B:151:0x0454  */
    /* JADX WARN: Removed duplicated region for block: B:154:0x049d  */
    /* JADX WARN: Removed duplicated region for block: B:155:0x04a2  */
    /* JADX WARN: Removed duplicated region for block: B:161:0x04be  */
    /* JADX WARN: Removed duplicated region for block: B:170:0x04e3  */
    /* JADX WARN: Removed duplicated region for block: B:176:0x0500  */
    /* JADX WARN: Removed duplicated region for block: B:177:0x0503  */
    /* JADX WARN: Removed duplicated region for block: B:59:0x0172  */
    /* JADX WARN: Removed duplicated region for block: B:60:0x017b  */
    /* JADX WARN: Removed duplicated region for block: B:69:0x019b  */
    /*  JADX ERROR: JadxRuntimeException in pass: RegionMakerVisitor
        jadx.core.utils.exceptions.JadxRuntimeException: Not found exit edge by exit block: B:77:0x01ea
        	at jadx.core.dex.visitors.regions.maker.LoopRegionMaker.checkLoopExits(LoopRegionMaker.java:226)
        	at jadx.core.dex.visitors.regions.maker.LoopRegionMaker.makeLoopRegion(LoopRegionMaker.java:196)
        	at jadx.core.dex.visitors.regions.maker.LoopRegionMaker.process(LoopRegionMaker.java:63)
        	at jadx.core.dex.visitors.regions.maker.RegionMaker.traverse(RegionMaker.java:89)
        	at jadx.core.dex.visitors.regions.maker.RegionMaker.makeRegion(RegionMaker.java:66)
        	at jadx.core.dex.visitors.regions.maker.IfRegionMaker.process(IfRegionMaker.java:102)
        	at jadx.core.dex.visitors.regions.maker.RegionMaker.traverse(RegionMaker.java:106)
        	at jadx.core.dex.visitors.regions.maker.RegionMaker.makeRegion(RegionMaker.java:66)
        	at jadx.core.dex.visitors.regions.maker.IfRegionMaker.process(IfRegionMaker.java:96)
        	at jadx.core.dex.visitors.regions.maker.RegionMaker.traverse(RegionMaker.java:106)
        	at jadx.core.dex.visitors.regions.maker.RegionMaker.makeRegion(RegionMaker.java:66)
        	at jadx.core.dex.visitors.regions.maker.IfRegionMaker.process(IfRegionMaker.java:96)
        	at jadx.core.dex.visitors.regions.maker.RegionMaker.traverse(RegionMaker.java:106)
        	at jadx.core.dex.visitors.regions.maker.RegionMaker.makeRegion(RegionMaker.java:66)
        	at jadx.core.dex.visitors.regions.maker.IfRegionMaker.process(IfRegionMaker.java:96)
        	at jadx.core.dex.visitors.regions.maker.RegionMaker.traverse(RegionMaker.java:106)
        	at jadx.core.dex.visitors.regions.maker.RegionMaker.makeRegion(RegionMaker.java:66)
        	at jadx.core.dex.visitors.regions.maker.IfRegionMaker.process(IfRegionMaker.java:96)
        	at jadx.core.dex.visitors.regions.maker.RegionMaker.traverse(RegionMaker.java:106)
        	at jadx.core.dex.visitors.regions.maker.RegionMaker.makeRegion(RegionMaker.java:66)
        	at jadx.core.dex.visitors.regions.maker.IfRegionMaker.process(IfRegionMaker.java:96)
        	at jadx.core.dex.visitors.regions.maker.RegionMaker.traverse(RegionMaker.java:106)
        	at jadx.core.dex.visitors.regions.maker.RegionMaker.makeRegion(RegionMaker.java:66)
        	at jadx.core.dex.visitors.regions.maker.IfRegionMaker.process(IfRegionMaker.java:102)
        	at jadx.core.dex.visitors.regions.maker.RegionMaker.traverse(RegionMaker.java:106)
        	at jadx.core.dex.visitors.regions.maker.RegionMaker.makeRegion(RegionMaker.java:66)
        	at jadx.core.dex.visitors.regions.maker.RegionMaker.makeMthRegion(RegionMaker.java:48)
        	at jadx.core.dex.visitors.regions.RegionMakerVisitor.visit(RegionMakerVisitor.java:25)
        */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static final r0.AbstractC2268a W(int r64, T.C0761q r65, int r66) throws org.xmlpull.v1.XmlPullParserException, java.io.IOException {
        /*
            Method dump skipped, instruction units count: 1994
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: l9.l.W(int, T.q, int):r0.a");
    }

    public static void X(U u4, V v10, l1.k kVar) {
        if (kVar.o == 8) {
            return;
        }
        if (Float.isNaN(kVar.f18441f) && Float.isNaN(kVar.f18442g) && Float.isNaN(kVar.f18443h) && Float.isNaN(kVar.f18444i) && Float.isNaN(kVar.f18445j) && Float.isNaN(kVar.f18446k) && Float.isNaN(kVar.l) && Float.isNaN(kVar.f18447m) && Float.isNaN(kVar.f18448n)) {
            U.e(u4, v10, AbstractC2673a.d(kVar.f18439b - ((int) 0), kVar.c - ((int) 0)));
            return;
        }
        p pVar = new p(kVar, 18);
        int i7 = kVar.f18439b - ((int) 0);
        int i10 = kVar.c - ((int) 0);
        float f10 = Float.isNaN(kVar.f18446k) ? 0.0f : kVar.f18446k;
        u4.getClass();
        long jD = AbstractC2673a.d(i7, i10);
        U.a(u4, v10);
        v10.l0(a1.h.d(jD, v10.f873p), f10, pVar);
    }

    public static final C1038c Y(N0.q qVar, InterfaceC1266b interfaceC1266b) {
        A7.m.f(qVar, "<this>");
        A7.m.f(interfaceC1266b, "annotationsOwner");
        return new C1038c(qVar, interfaceC1266b, false);
    }

    public static String Z(int i7) {
        if (J(i7, 0)) {
            return "Blocking";
        }
        if (J(i7, 1)) {
            return "Optional";
        }
        if (J(i7, 2)) {
            return "Async";
        }
        return "Invalid(value=" + i7 + ')';
    }

    public static final void a(g0.o oVar, C0761q c0761q, int i7) {
        int i10;
        c0761q.S(804147577);
        if ((i7 & 6) == 0) {
            i10 = (c0761q.f(oVar) ? 4 : 2) | i7;
        } else {
            i10 = i7;
        }
        if ((i7 & 48) == 0) {
            i10 |= c0761q.d(R.raw.background_trophy) ? 32 : 16;
        }
        if ((i7 & 384) == 0) {
            i10 |= c0761q.f("Trophy bird") ? 256 : 128;
        }
        if ((i10 & 147) == 146 && c0761q.x()) {
            c0761q.K();
        } else if (((Boolean) c0761q.k(D0.f2013a)).booleanValue()) {
            c0761q.Q(-152659465);
            int i11 = i10 >> 3;
            h6.q.a(W(R.raw.background_trophy, c0761q, i11 & 14), "Trophy bird", oVar, null, C0102i.c, 0.0f, null, c0761q, (i11 & 112) | 24576 | ((i10 << 6) & 896), 104);
            c0761q.p(false);
        } else {
            c0761q.Q(-152420889);
            c0761q.Q(2073295417);
            boolean z6 = (i10 & 896) == 256;
            Object objG = c0761q.G();
            S s6 = C0753m.f8270a;
            if (z6 || objG == s6) {
                objG = new A3.c(10);
                c0761q.a0(objG);
            }
            InterfaceC2782k interfaceC2782k = (InterfaceC2782k) objG;
            c0761q.p(false);
            c0761q.Q(2073304695);
            boolean z10 = (i10 & 112) == 32;
            Object objG2 = c0761q.G();
            if (z10 || objG2 == s6) {
                objG2 = new A3.c(11);
                c0761q.a0(objG2);
            }
            c0761q.p(false);
            androidx.compose.ui.viewinterop.a.a(interfaceC2782k, oVar, (InterfaceC2782k) objG2, c0761q, (i10 << 3) & 112, 0);
            c0761q.p(false);
        }
        C0754m0 c0754m0R = c0761q.r();
        if (c0754m0R != null) {
            c0754m0R.d = new K6.a(oVar, i7, 0);
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r14v1 */
    /* JADX WARN: Type inference failed for: r14v2, types: [boolean, int] */
    /* JADX WARN: Type inference failed for: r14v4 */
    public static final void b(final g0.o oVar, final int i7, final float f10, final float f11, final boolean z6, final InterfaceC2782k interfaceC2782k, C0761q c0761q, final int i10) throws XmlPullParserException, IOException {
        int i11;
        Float f12;
        int i12;
        Object obj;
        int i13;
        Boolean bool;
        ?? r14;
        A7.m.f(interfaceC2782k, "playSoundFx");
        c0761q.S(-1093349031);
        if ((i10 & 6) == 0) {
            i11 = (c0761q.f(oVar) ? 4 : 2) | i10;
        } else {
            i11 = i10;
        }
        if ((i10 & 48) == 0) {
            i11 |= c0761q.d(i7) ? 32 : 16;
        }
        if ((i10 & 384) == 0) {
            i11 |= c0761q.c(f10) ? 256 : 128;
        }
        if ((i10 & 3072) == 0) {
            i11 |= c0761q.c(f11) ? 2048 : 1024;
        }
        if ((i10 & 24576) == 0) {
            i11 |= c0761q.g(z6) ? 16384 : 8192;
        }
        if ((196608 & i10) == 0) {
            i11 |= c0761q.h(interfaceC2782k) ? 131072 : 65536;
        }
        int i14 = i11;
        if ((i14 & 74899) == 74898 && c0761q.x()) {
            c0761q.K();
        } else {
            c0761q.Q(1193118411);
            Object objG = c0761q.G();
            Object obj2 = C0753m.f8270a;
            if (objG == obj2) {
                objG = C0735d.M(R.drawable.star_0);
                c0761q.a0(objG);
            }
            C0732b0 c0732b0 = (C0732b0) objG;
            Object objE = M5.b.e(1193120700, c0761q, false);
            S s6 = S.f8224q;
            if (objE == obj2) {
                objE = C0735d.O(Boolean.FALSE, s6);
                c0761q.a0(objE);
            }
            X x8 = (X) objE;
            Object objE2 = M5.b.e(1193122460, c0761q, false);
            if (objE2 == obj2) {
                objE2 = C0735d.O(Boolean.FALSE, s6);
                c0761q.a0(objE2);
            }
            X x9 = (X) objE2;
            c0761q.p(false);
            float fS = AbstractC2673a.s(f10, f11);
            int i15 = (int) fS;
            Float fValueOf = Float.valueOf(f10);
            c0761q.Q(1193129953);
            int i16 = i14 & 112;
            int i17 = i14 & 57344;
            boolean zD = (i16 == 32) | c0761q.d(i15) | c0761q.c(fS) | (i17 == 16384);
            Object objG2 = c0761q.G();
            if (zD || objG2 == obj2) {
                f12 = fValueOf;
                i12 = i16;
                obj = obj2;
                i13 = 16384;
                B b10 = new B(i7, i15, fS, z6, x8, c0732b0, null);
                c0761q.a0(b10);
                objG2 = b10;
            } else {
                f12 = fValueOf;
                i12 = i16;
                obj = obj2;
                i13 = 16384;
            }
            c0761q.p(false);
            C0735d.f(c0761q, f12, (InterfaceC2785n) objG2);
            Q0 q0B = AbstractC2637h.b(((Boolean) x9.getValue()).booleanValue() ? 1.5f : 1.0f, null, "AnimatedStar scale", null, c0761q, 3072, 22);
            Boolean bool2 = (Boolean) x8.getValue();
            bool2.getClass();
            c0761q.Q(1193166459);
            boolean z10 = ((i14 & 458752) == 131072) | (i17 == i13) | (i12 == 32);
            Object objG3 = c0761q.G();
            if (z10 || objG3 == obj) {
                bool = bool2;
                r14 = 0;
                C c = new C(z6, interfaceC2782k, i7, x8, x9, c0732b0, null);
                c0761q.a0(c);
                objG3 = c;
            } else {
                bool = bool2;
                r14 = 0;
            }
            c0761q.p(r14);
            C0735d.f(c0761q, bool, (InterfaceC2785n) objG3);
            AbstractC2268a abstractC2268aW = W(c0732b0.i(), c0761q, r14);
            String strE = android.hardware.bydauto.ac.tsimpl.a.e(i7, "Star ");
            float fFloatValue = ((Number) q0B.getValue()).floatValue();
            h6.q.a(abstractC2268aW, strE, h6.r.r(oVar, fFloatValue, fFloatValue), null, null, 0.0f, null, c0761q, 0, 120);
        }
        C0754m0 c0754m0R = c0761q.r();
        if (c0754m0R != null) {
            c0754m0R.d = new InterfaceC2785n() { // from class: U5.A
                @Override // z7.InterfaceC2785n
                public final Object invoke(Object obj3, Object obj4) throws XmlPullParserException, IOException {
                    ((Integer) obj4).getClass();
                    int iZ = C0735d.Z(i10 | 1);
                    boolean z11 = z6;
                    InterfaceC2782k interfaceC2782k2 = interfaceC2782k;
                    l9.l.b(oVar, i7, f10, f11, z11, interfaceC2782k2, (C0761q) obj3, iZ);
                    return m7.q.f19267a;
                }
            };
        }
    }

    public static final void c(final g0.o oVar, final float f10, final int i7, final boolean z6, final InterfaceC2782k interfaceC2782k, C0761q c0761q, final int i10) {
        int i11;
        A7.m.f(interfaceC2782k, "playSoundFx");
        c0761q.S(1440427683);
        if ((i10 & 6) == 0) {
            i11 = (c0761q.f(oVar) ? 4 : 2) | i10;
        } else {
            i11 = i10;
        }
        if ((i10 & 48) == 0) {
            i11 |= c0761q.c(f10) ? 32 : 16;
        }
        if ((i10 & 384) == 0) {
            i11 |= c0761q.d(i7) ? 256 : 128;
        }
        if ((i10 & 3072) == 0) {
            i11 |= c0761q.d(2500) ? 2048 : 1024;
        }
        if ((i10 & 24576) == 0) {
            i11 |= c0761q.g(z6) ? 16384 : 8192;
        }
        if ((196608 & i10) == 0) {
            i11 |= c0761q.h(interfaceC2782k) ? 131072 : 65536;
        }
        int i12 = i11;
        if ((74899 & i12) == 74898 && c0761q.x()) {
            c0761q.K();
        } else {
            c0761q.Q(108887001);
            Object objG = c0761q.G();
            S s6 = C0753m.f8270a;
            if (objG == s6) {
                objG = C0735d.O(Boolean.FALSE, S.f8224q);
                c0761q.a0(objG);
            }
            X x8 = (X) objG;
            c0761q.p(false);
            float fFloor = ((float) Math.floor(2 * f10)) / 2.0f;
            Q0 q0B = AbstractC2637h.b(((Boolean) x8.getValue()).booleanValue() ? 5 : 0.0f, new C0(i7, 2500, AbstractC2614C.c), "NumberRollingPopUpAnimation roll", null, c0761q, 3072, 20);
            m7.q qVar = m7.q.f19267a;
            c0761q.Q(108902684);
            Object objG2 = c0761q.G();
            if (objG2 == s6) {
                objG2 = new D(x8, null);
                c0761q.a0(objG2);
            }
            c0761q.p(false);
            C0735d.f(c0761q, qVar, (InterfaceC2785n) objG2);
            float fFloatValue = z6 ? ((Number) q0B.getValue()).floatValue() : 5;
            int i13 = i12 >> 3;
            d(oVar, fFloatValue, fFloor, z6, interfaceC2782k, c0761q, (i12 & 14) | (i13 & 7168) | (i13 & 57344));
        }
        C0754m0 c0754m0R = c0761q.r();
        if (c0754m0R != null) {
            c0754m0R.d = new InterfaceC2785n() { // from class: U5.x
                @Override // z7.InterfaceC2785n
                public final Object invoke(Object obj, Object obj2) {
                    ((Integer) obj2).getClass();
                    int iZ = C0735d.Z(i10 | 1);
                    boolean z10 = z6;
                    InterfaceC2782k interfaceC2782k2 = interfaceC2782k;
                    l9.l.c(oVar, f10, i7, z10, interfaceC2782k2, (C0761q) obj, iZ);
                    return m7.q.f19267a;
                }
            };
        }
    }

    public static final void d(final g0.o oVar, final float f10, final float f11, final boolean z6, final InterfaceC2782k interfaceC2782k, C0761q c0761q, final int i7) {
        A7.m.f(interfaceC2782k, "playSoundFx");
        c0761q.S(1104659241);
        int i10 = (i7 & 6) == 0 ? (c0761q.f(oVar) ? 4 : 2) | i7 : i7;
        if ((i7 & 48) == 0) {
            i10 |= c0761q.c(f10) ? 32 : 16;
        }
        if ((i7 & 384) == 0) {
            i10 |= c0761q.c(f11) ? 256 : 128;
        }
        if ((i7 & 3072) == 0) {
            i10 |= c0761q.g(z6) ? 2048 : 1024;
        }
        if ((i7 & 24576) == 0) {
            i10 |= c0761q.h(interfaceC2782k) ? 16384 : 8192;
        }
        if ((i10 & 9363) == 9362 && c0761q.x()) {
            c0761q.K();
        } else {
            D.S sB = P.b(AbstractC0127i.d, g0.b.f14669u, c0761q, 6);
            int i11 = c0761q.f8304P;
            InterfaceC0744h0 interfaceC0744h0M = c0761q.m();
            g0.o oVarD = g0.a.d(c0761q, oVar);
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
            if (c0761q.f8303O || !A7.m.a(c0761q.G(), Integer.valueOf(i11))) {
                AbstractC1347d.l(i11, c0761q, i11, c0158h);
            }
            C0735d.V(c0761q, oVarD, C0160j.c);
            c0761q.Q(1670053541);
            for (int i12 = 0; i12 < 5; i12++) {
                AbstractC0120b.a(T.b(androidx.compose.foundation.layout.a.d(g0.l.f14684a, 1.0f), 1.0f), null, false, b0.g.b(1898082501, new U5.E(i12, f10, f11, z6, interfaceC2782k), c0761q), c0761q, 3072, 6);
            }
            c0761q.p(false);
            c0761q.p(true);
        }
        C0754m0 c0754m0R = c0761q.r();
        if (c0754m0R != null) {
            c0754m0R.d = new InterfaceC2785n() { // from class: U5.z
                @Override // z7.InterfaceC2785n
                public final Object invoke(Object obj, Object obj2) {
                    ((Integer) obj2).getClass();
                    int iZ = C0735d.Z(i7 | 1);
                    boolean z10 = z6;
                    InterfaceC2782k interfaceC2782k2 = interfaceC2782k;
                    l9.l.d(oVar, f10, f11, z10, interfaceC2782k2, (C0761q) obj, iZ);
                    return m7.q.f19267a;
                }
            };
        }
    }

    public static final void e(String str, InterfaceC2772a interfaceC2772a, C0761q c0761q, int i7) {
        int i10;
        A7.m.f(str, "id");
        c0761q.S(-129413499);
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
            AbstractC1274a.h(str, true, false, false, false, b0.g.b(1841149342, new S5.m(1, str, interfaceC2772a), c0761q), c0761q, (i10 & 14) | 199728, 20);
        }
        C0754m0 c0754m0R = c0761q.r();
        if (c0754m0R != null) {
            c0754m0R.d = new S5.i(str, interfaceC2772a, i7, 1);
        }
    }

    public static final void f(g0.o oVar, String str, InterfaceC2772a interfaceC2772a, D.L l, C0761q c0761q, int i7) {
        int i10;
        int i11;
        int i12;
        boolean z6;
        c0761q.S(1446337728);
        if ((i7 & 6) == 0) {
            i10 = (c0761q.f(oVar) ? 4 : 2) | i7;
        } else {
            i10 = i7;
        }
        if ((i7 & 48) == 0) {
            i10 |= c0761q.f(str) ? 32 : 16;
        }
        if ((i7 & 384) == 0) {
            i10 |= c0761q.h(interfaceC2772a) ? 256 : 128;
        }
        if ((i7 & 3072) == 0) {
            i10 |= c0761q.f(l) ? 2048 : 1024;
        }
        int i13 = i10;
        if ((i13 & 1171) == 1170 && c0761q.x()) {
            c0761q.K();
        } else {
            g0.o oVarJ = androidx.compose.foundation.layout.a.j(oVar, l);
            g0.e eVar = g0.b.f14673y;
            C0141x c0141xA = AbstractC0139v.a(AbstractC0127i.f1079f, eVar, c0761q, 54);
            int i14 = c0761q.f8304P;
            InterfaceC0744h0 interfaceC0744h0M = c0761q.m();
            g0.o oVarD = g0.a.d(c0761q, oVarJ);
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
            if (c0761q.f8303O || !A7.m.a(c0761q.G(), Integer.valueOf(i14))) {
                AbstractC1347d.l(i14, c0761q, i14, c0158h3);
            }
            C0158h c0158h4 = C0160j.c;
            C0735d.V(c0761q, oVarD, c0158h4);
            g0.l lVar = g0.l.f14684a;
            g0.o oVarD2 = androidx.compose.foundation.layout.c.d(lVar, 0.9f);
            m6.b bVar = m6.c.f19252a;
            g0.o oVarK = androidx.compose.foundation.layout.a.k(oVarD2, bVar.E(c0761q));
            String strX = AbstractC2673a.X(R.string.PassengerPrompt_Title, c0761q);
            long j3 = l6.d.c(c0761q).f18696b;
            FontSize fontSize = FontSize.Subtitle1;
            long jG = h6.f.g(fontSize, c0761q);
            R0 r02 = O5.c.f5157b;
            c1.b(strX, oVarK, j3, jG, null, null, null, 0L, null, new Y0.i(3), 0L, 0, false, 0, 0, null, ((O5.d) c0761q.k(r02)).d, c0761q, 0, 0, 65008);
            c1.b(AbstractC2673a.X(R.string.PassengerPrompt_Text, c0761q), androidx.compose.foundation.layout.c.d(lVar, 0.8f), l6.d.c(c0761q).f18696b, h6.f.g(fontSize, c0761q), null, null, null, 0L, null, new Y0.i(3), 0L, 0, false, 0, 0, null, ((O5.d) c0761q.k(r02)).d, c0761q, 48, 0, 65008);
            g0.o oVarO = androidx.compose.foundation.layout.a.o(androidx.compose.foundation.layout.a.p(lVar), 0.0f, bVar.H(6, c0761q), 0.0f, 0.0f, 13);
            C0141x c0141xA2 = AbstractC0139v.a(new C0124f(bVar.H(6, c0761q)), eVar, c0761q, 48);
            int i15 = c0761q.f8304P;
            InterfaceC0744h0 interfaceC0744h0M2 = c0761q.m();
            g0.o oVarD3 = g0.a.d(c0761q, oVarO);
            c0761q.U();
            if (c0761q.f8303O) {
                c0761q.l(c0159i);
            } else {
                c0761q.d0();
            }
            C0735d.V(c0761q, c0141xA2, c0158h);
            C0735d.V(c0761q, interfaceC0744h0M2, c0158h2);
            if (c0761q.f8303O || !A7.m.a(c0761q.G(), Integer.valueOf(i15))) {
                AbstractC1347d.l(i15, c0761q, i15, c0158h3);
            }
            C0735d.V(c0761q, oVarD3, c0158h4);
            g0.o oVarD4 = androidx.compose.foundation.layout.c.d(lVar, 1.0f);
            String strX2 = AbstractC2673a.X(R.string.PassengerPrompt_Accept, c0761q);
            c0761q.Q(760323833);
            int i16 = i13 & 112;
            boolean z10 = i16 == 32;
            Object objG = c0761q.G();
            S s6 = C0753m.f8270a;
            if (z10 || objG == s6) {
                objG = new S5.e(str, 2);
                c0761q.a0(objG);
            }
            c0761q.p(false);
            io.sentry.config.a.p(oVarD4, null, false, strX2, 0L, null, null, 0L, 0.0f, (InterfaceC2772a) objG, c0761q, 6, 502);
            g0.o oVarD5 = androidx.compose.foundation.layout.c.d(lVar, 1.0f);
            String strX3 = AbstractC2673a.X(R.string.PassengerPrompt_Decline, c0761q);
            c0761q.Q(760331509);
            if ((i13 & 896) == 256) {
                i11 = i16;
                i12 = 32;
                z6 = true;
            } else {
                i11 = i16;
                i12 = 32;
                z6 = false;
            }
            boolean z11 = (i11 == i12) | z6;
            Object objG2 = c0761q.G();
            if (z11 || objG2 == s6) {
                objG2 = new S5.a(3, str, interfaceC2772a);
                c0761q.a0(objG2);
            }
            c0761q.p(false);
            io.sentry.config.a.o(oVarD5, null, false, strX3, 0L, null, false, null, null, 0L, 0.0f, (InterfaceC2772a) objG2, c0761q, 6, 0, 2038);
            c0761q.p(true);
            c0761q.p(true);
        }
        C0754m0 c0754m0R = c0761q.r();
        if (c0754m0R != null) {
            c0754m0R.d = new G6.c(oVar, str, interfaceC2772a, l, i7, 6);
        }
    }

    public static final void g(g0.o oVar, D.L l, C0761q c0761q, int i7) {
        g0.o oVar2;
        c0761q.S(-777444221);
        int i10 = i7 | 6;
        if ((i7 & 48) == 0) {
            i10 |= c0761q.f(l) ? 32 : 16;
        }
        if ((i10 & 19) == 18 && c0761q.x()) {
            c0761q.K();
            oVar2 = oVar;
        } else {
            g0.l lVar = g0.l.f14684a;
            g0.o oVarJ = androidx.compose.foundation.layout.a.j(h6.r.t(androidx.compose.foundation.layout.c.c(lVar, 1.0f), h6.r.q(c0761q)), l);
            C0141x c0141xA = AbstractC0139v.a(AbstractC0127i.c, g0.b.f14673y, c0761q, 48);
            int i11 = c0761q.f8304P;
            InterfaceC0744h0 interfaceC0744h0M = c0761q.m();
            g0.o oVarD = g0.a.d(c0761q, oVarJ);
            InterfaceC0161k.f1681b.getClass();
            C0159i c0159i = C0160j.f1678b;
            c0761q.U();
            if (c0761q.f8303O) {
                c0761q.l(c0159i);
            } else {
                c0761q.d0();
            }
            C0735d.V(c0761q, c0141xA, C0160j.f1679e);
            C0735d.V(c0761q, interfaceC0744h0M, C0160j.d);
            C0158h c0158h = C0160j.f1680f;
            if (c0761q.f8303O || !A7.m.a(c0761q.G(), Integer.valueOf(i11))) {
                AbstractC1347d.l(i11, c0761q, i11, c0158h);
            }
            C0735d.V(c0761q, oVarD, C0160j.c);
            C0142y c0142y = C0142y.f1119a;
            AbstractC0120b.b(c0761q, C0142y.a(c0142y, lVar, 1.0f));
            g0.o oVarD2 = androidx.compose.foundation.layout.c.d(lVar, 0.8f);
            String strX = AbstractC2673a.X(R.string.ContactUs_Email, c0761q);
            long j3 = l6.d.c(c0761q).f18696b;
            FontSize fontSize = FontSize.H9;
            long jG = h6.f.g(fontSize, c0761q);
            R0 r02 = O5.c.f5157b;
            c1.b(strX, oVarD2, j3, jG, null, null, null, 0L, null, new Y0.i(1), 0L, 0, false, 0, 0, null, ((O5.d) c0761q.k(r02)).d, c0761q, 48, 0, 65008);
            g0.o oVarD3 = androidx.compose.foundation.layout.c.d(lVar, 0.8f);
            String strX2 = AbstractC2673a.X(R.string.ContactUs_SupportEmail, c0761q);
            long j10 = l6.d.c(c0761q).f18696b;
            FontSize fontSize2 = FontSize.Subtitle1;
            c1.b(strX2, oVarD3, j10, h6.f.g(fontSize2, c0761q), null, null, null, 0L, null, new Y0.i(1), 0L, 0, false, 0, 0, null, ((O5.d) c0761q.k(r02)).f5158a, c0761q, 48, 0, 65008);
            m6.b bVar = m6.c.f19252a;
            AbstractC0120b.b(c0761q, androidx.compose.foundation.layout.c.e(lVar, bVar.H(6, c0761q)));
            c1.b(AbstractC2673a.X(R.string.Global_Or, c0761q), androidx.compose.foundation.layout.a.m(androidx.compose.foundation.layout.c.d(lVar, 0.8f), 0.0f, bVar.H(6, c0761q), 1), l6.d.c(c0761q).f18696b, h6.f.g(fontSize, c0761q), null, null, null, 0L, null, new Y0.i(1), 0L, 0, false, 0, 0, null, ((O5.d) c0761q.k(r02)).d, c0761q, 0, 0, 65008);
            AbstractC0120b.b(c0761q, androidx.compose.foundation.layout.c.e(lVar, bVar.H(6, c0761q)));
            c1.b(AbstractC2673a.X(R.string.ContactUs_Mail, c0761q), androidx.compose.foundation.layout.a.m(androidx.compose.foundation.layout.c.d(lVar, 0.8f), 0.0f, bVar.H(6, c0761q), 1), l6.d.c(c0761q).f18696b, h6.f.g(fontSize, c0761q), null, null, null, 0L, null, new Y0.i(1), 0L, 0, false, 0, 0, null, ((O5.d) c0761q.k(r02)).d, c0761q, 0, 0, 65008);
            c1.b(AbstractC2673a.X(R.string.ContactUs_Addresses, c0761q), androidx.compose.foundation.layout.c.d(lVar, 0.8f), l6.d.c(c0761q).f18696b, h6.f.g(fontSize2, c0761q), null, null, null, 0L, null, new Y0.i(1), 0L, 0, false, 0, 0, null, ((O5.d) c0761q.k(r02)).f5158a, c0761q, 48, 0, 65008);
            AbstractC0120b.b(c0761q, androidx.compose.foundation.layout.c.e(lVar, bVar.H(6, c0761q)));
            c1.b(AbstractC2673a.X(R.string.ContactUs_Privacy, c0761q), androidx.compose.foundation.layout.a.m(androidx.compose.foundation.layout.c.d(lVar, 0.8f), 0.0f, bVar.H(6, c0761q), 1), l6.d.c(c0761q).f18696b, h6.f.g(fontSize2, c0761q), null, null, null, 0L, null, new Y0.i(1), 0L, 0, false, 0, 0, null, ((O5.d) c0761q.k(r02)).f5158a, c0761q, 0, 0, 65008);
            oVar2 = lVar;
            AbstractC0120b.b(c0761q, C0142y.a(c0142y, oVar2, 3.0f));
            c0761q.p(true);
        }
        C0754m0 c0754m0R = c0761q.r();
        if (c0754m0R != null) {
            c0754m0R.d = new M(oVar2, l, i7, 0);
        }
    }

    public static final void h(h6.b bVar, C0761q c0761q, int i7) {
        int i10;
        c0761q.S(1604216302);
        if ((i7 & 6) == 0) {
            i10 = (c0761q.h(bVar) ? 4 : 2) | i7;
        } else {
            i10 = i7;
        }
        if ((i10 & 3) == 2 && c0761q.x()) {
            c0761q.K();
        } else {
            X xR = com.bumptech.glide.c.R(bVar.l.c, c0761q, 0);
            C0607p1 c0607p1 = new C0607p1(bVar, AbstractC2673a.X(R.string.Global_ContactUs, c0761q), false, null, 250);
            if (((Boolean) xR.getValue()).booleanValue()) {
                c0761q.Q(-847488993);
                AbstractC0904a.l(null, c0607p1, A.f6243a, c0761q, 384, 1);
                c0761q.p(false);
            } else {
                c0761q.Q(-847200941);
                AbstractC0904a.l(null, c0607p1, A.f6244b, c0761q, 384, 1);
                c0761q.p(false);
            }
        }
        C0754m0 c0754m0R = c0761q.r();
        if (c0754m0R != null) {
            c0754m0R.d = new C0545a(i7, 3, bVar);
        }
    }

    public static final void i(h6.d dVar, C0761q c0761q, int i7) {
        int i10;
        c0761q.S(-1290959194);
        if ((i7 & 6) == 0) {
            i10 = (c0761q.h(dVar) ? 4 : 2) | i7;
        } else {
            i10 = i7;
        }
        if ((i10 & 3) == 2 && c0761q.x()) {
            c0761q.K();
        } else {
            Boolean bool = (Boolean) com.bumptech.glide.c.R(dVar.f15570n, c0761q, 0).getValue();
            c0761q.Q(1146861485);
            boolean zO = bool == null ? h6.n.o(c0761q) : bool.booleanValue();
            c0761q.p(false);
            l6.d.b(zO, false, b0.g.b(1671671546, new s(2, dVar, zO), c0761q), c0761q, 384, 2);
        }
        C0754m0 c0754m0R = c0761q.r();
        if (c0754m0R != null) {
            c0754m0R.d = new R5.a(dVar, i7, 0);
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:176:0x02b1  */
    /* JADX WARN: Removed duplicated region for block: B:177:0x02b5  */
    /* JADX WARN: Removed duplicated region for block: B:182:0x02d0  */
    /* JADX WARN: Removed duplicated region for block: B:185:0x02f0  */
    /* JADX WARN: Removed duplicated region for block: B:186:0x02f3  */
    /* JADX WARN: Removed duplicated region for block: B:189:0x0334  */
    /* JADX WARN: Removed duplicated region for block: B:190:0x0338  */
    /* JADX WARN: Removed duplicated region for block: B:195:0x0353  */
    /* JADX WARN: Removed duplicated region for block: B:198:0x035b  */
    /* JADX WARN: Removed duplicated region for block: B:199:0x035e  */
    /* JADX WARN: Removed duplicated region for block: B:202:0x037d  */
    /* JADX WARN: Removed duplicated region for block: B:214:0x03dc  */
    /* JADX WARN: Removed duplicated region for block: B:215:0x03e0  */
    /* JADX WARN: Removed duplicated region for block: B:220:0x03fb  */
    /* JADX WARN: Removed duplicated region for block: B:223:0x0404  */
    /* JADX WARN: Removed duplicated region for block: B:224:0x0407  */
    /* JADX WARN: Removed duplicated region for block: B:227:0x0415  */
    /* JADX WARN: Removed duplicated region for block: B:228:0x0417  */
    /* JADX WARN: Removed duplicated region for block: B:234:0x0427  */
    /* JADX WARN: Removed duplicated region for block: B:237:0x0458  */
    /* JADX WARN: Removed duplicated region for block: B:252:0x04a3  */
    /* JADX WARN: Removed duplicated region for block: B:255:0x04c7  */
    /* JADX WARN: Removed duplicated region for block: B:256:0x04cd  */
    /* JADX WARN: Removed duplicated region for block: B:261:0x04e8  */
    /* JADX WARN: Removed duplicated region for block: B:264:0x04f0  */
    /* JADX WARN: Removed duplicated region for block: B:265:0x04f3  */
    /* JADX WARN: Removed duplicated region for block: B:268:0x0512  */
    /* JADX WARN: Removed duplicated region for block: B:274:0x054c  */
    /* JADX WARN: Removed duplicated region for block: B:275:0x054e  */
    /* JADX WARN: Removed duplicated region for block: B:281:0x055b  */
    /* JADX WARN: Removed duplicated region for block: B:284:0x0576  */
    /* JADX WARN: Removed duplicated region for block: B:285:0x0578  */
    /* JADX WARN: Removed duplicated region for block: B:289:0x0581  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static final void j(g0.o r54, final java.lang.String r55, final boolean r56, final z7.InterfaceC2782k r57, final boolean r58, final z7.InterfaceC2782k r59, final boolean r60, final z7.InterfaceC2782k r61, final boolean r62, final z7.InterfaceC2782k r63, final boolean r64, final z7.InterfaceC2772a r65, final z7.InterfaceC2782k r66, final boolean r67, final boolean r68, final z7.InterfaceC2782k r69, final boolean r70, final z7.InterfaceC2782k r71, final java.lang.String r72, final z7.InterfaceC2782k r73, final boolean r74, final z7.InterfaceC2782k r75, final D.L r76, T.C0761q r77, final int r78, final int r79, final int r80) {
        /*
            Method dump skipped, instruction units count: 1516
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: l9.l.j(g0.o, java.lang.String, boolean, z7.k, boolean, z7.k, boolean, z7.k, boolean, z7.k, boolean, z7.a, z7.k, boolean, boolean, z7.k, boolean, z7.k, java.lang.String, z7.k, boolean, z7.k, D.L, T.q, int, int, int):void");
    }

    public static final long k(float f10, float f11) {
        return (((long) Float.floatToRawIntBits(f11)) & 4294967295L) | (Float.floatToRawIntBits(f10) << 32);
    }

    /* JADX WARN: Removed duplicated region for block: B:120:0x0219  */
    /* JADX WARN: Removed duplicated region for block: B:121:0x021b  */
    /* JADX WARN: Removed duplicated region for block: B:150:0x0281  */
    /* JADX WARN: Removed duplicated region for block: B:151:0x0285  */
    /* JADX WARN: Removed duplicated region for block: B:180:0x02df  */
    /* JADX WARN: Removed duplicated region for block: B:181:0x02e3  */
    /* JADX WARN: Removed duplicated region for block: B:210:0x0372  */
    /* JADX WARN: Removed duplicated region for block: B:211:0x0376  */
    /* JADX WARN: Removed duplicated region for block: B:240:0x03e1  */
    /* JADX WARN: Removed duplicated region for block: B:241:0x03e3  */
    /* JADX WARN: Removed duplicated region for block: B:270:0x0443  */
    /* JADX WARN: Removed duplicated region for block: B:271:0x0445  */
    /* JADX WARN: Removed duplicated region for block: B:300:0x04eb  */
    /* JADX WARN: Removed duplicated region for block: B:301:0x04ed  */
    /* JADX WARN: Removed duplicated region for block: B:304:0x04fc  */
    /* JADX WARN: Removed duplicated region for block: B:308:0x0508  */
    /* JADX WARN: Removed duplicated region for block: B:312:0x0560  */
    /* JADX WARN: Removed duplicated region for block: B:313:0x0562  */
    /* JADX WARN: Removed duplicated region for block: B:316:0x0577 A[ADDED_TO_REGION] */
    /* JADX WARN: Removed duplicated region for block: B:319:0x0584  */
    /* JADX WARN: Removed duplicated region for block: B:322:0x05ba  */
    /* JADX WARN: Removed duplicated region for block: B:323:0x05bd  */
    /* JADX WARN: Removed duplicated region for block: B:326:0x05c5  */
    /* JADX WARN: Removed duplicated region for block: B:327:0x05c8  */
    /* JADX WARN: Removed duplicated region for block: B:330:0x05ce  */
    /* JADX WARN: Removed duplicated region for block: B:331:0x05d1  */
    /* JADX WARN: Removed duplicated region for block: B:337:0x05e5  */
    /* JADX WARN: Removed duplicated region for block: B:340:0x0614  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static final void l(final g0.o r38, final t6.h r39, final java.lang.String r40, final java.lang.String r41, final V8.N r42, final com.stingray.karaoke.car.player.LeadVocalState r43, final x6.e r44, boolean r45, T.C0761q r46, final int r47, final int r48) {
        /*
            Method dump skipped, instruction units count: 1613
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: l9.l.l(g0.o, t6.h, java.lang.String, java.lang.String, V8.N, com.stingray.karaoke.car.player.LeadVocalState, x6.e, boolean, T.q, int, int):void");
    }

    /* JADX WARN: Removed duplicated region for block: B:114:0x017c  */
    /* JADX WARN: Removed duplicated region for block: B:115:0x017e  */
    /* JADX WARN: Removed duplicated region for block: B:118:0x018d  */
    /* JADX WARN: Removed duplicated region for block: B:119:0x018f  */
    /* JADX WARN: Removed duplicated region for block: B:123:0x0198  */
    /* JADX WARN: Removed duplicated region for block: B:126:0x01a0  */
    /* JADX WARN: Removed duplicated region for block: B:128:0x01a4  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static final void m(g0.o r22, final java.util.List r23, final z7.InterfaceC2772a r24, final z7.InterfaceC2785n r25, final z7.InterfaceC2782k r26, final boolean r27, final z7.InterfaceC2782k r28, final z7.InterfaceC2772a r29, final z7.InterfaceC2772a r30, final z7.InterfaceC2772a r31, final D.L r32, T.C0761q r33, final int r34, final int r35) {
        /*
            Method dump skipped, instruction units count: 525
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: l9.l.m(g0.o, java.util.List, z7.a, z7.n, z7.k, boolean, z7.k, z7.a, z7.a, z7.a, D.L, T.q, int, int):void");
    }

    public static final void n(f6.o oVar, C0761q c0761q, int i7) {
        int i10;
        A7.m.f(oVar, "viewModel");
        c0761q.S(-262327960);
        if ((i7 & 6) == 0) {
            i10 = (c0761q.h(oVar) ? 4 : 2) | i7;
        } else {
            i10 = i7;
        }
        if ((i10 & 3) == 2 && c0761q.x()) {
            c0761q.K();
        } else {
            AbstractC0904a.l(null, new C0607p1(oVar, null, false, null, 254), b0.g.b(983764006, new y(oVar, 7), c0761q), c0761q, 384, 1);
        }
        C0754m0 c0754m0R = c0761q.r();
        if (c0754m0R != null) {
            c0754m0R.d = new C0545a(i7, 6, oVar);
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r8v0 */
    /* JADX WARN: Type inference failed for: r8v1, types: [boolean, int] */
    /* JADX WARN: Type inference failed for: r8v4 */
    public static final void o(g0.o oVar, String str, G6.b bVar, InterfaceC2782k interfaceC2782k, C0761q c0761q, int i7) {
        int i10;
        int i11;
        G6.b bVar2;
        ?? r82;
        G6.a aVar;
        G6.b bVar3;
        G6.b bVar4;
        c0761q.S(96223442);
        if ((i7 & 6) == 0) {
            i10 = (c0761q.f(oVar) ? 4 : 2) | i7;
        } else {
            i10 = i7;
        }
        if ((i7 & 48) == 0) {
            i10 |= c0761q.f(str) ? 32 : 16;
        }
        if ((i7 & 384) == 0) {
            i10 |= 128;
        }
        if ((i7 & 3072) == 0) {
            i10 |= c0761q.h(interfaceC2782k) ? 2048 : 1024;
        }
        if ((i10 & 1171) == 1170 && c0761q.x()) {
            c0761q.K();
            bVar4 = bVar;
        } else {
            c0761q.M();
            int i12 = i7 & 1;
            Object obj = C0753m.f8270a;
            if (i12 == 0 || c0761q.w()) {
                i11 = i10 & (-897);
                bVar2 = new G6.b();
            } else {
                c0761q.K();
                bVar2 = bVar;
                i11 = i10 & (-897);
            }
            c0761q.q();
            Context context = (Context) c0761q.k(AndroidCompositionLocals_androidKt.f11688b);
            c0761q.Q(1920062452);
            Object objG = c0761q.G();
            if (objG == obj) {
                objG = C0735d.O(null, S.f8224q);
                c0761q.a0(objG);
            }
            X x8 = (X) objG;
            c0761q.p(false);
            c0761q.Q(1920064987);
            boolean zH = c0761q.h(context) | ((i11 & 112) == 32) | ((i11 & 7168) == 2048);
            Object objG2 = c0761q.G();
            if (zH || objG2 == obj) {
                r82 = 0;
                G6.d dVar = new G6.d(context, str, x8, interfaceC2782k, null);
                c0761q.a0(dVar);
                objG2 = dVar;
            } else {
                r82 = 0;
            }
            c0761q.p(r82);
            C0735d.f(c0761q, str, (InterfaceC2785n) objG2);
            List list = (List) x8.getValue();
            if (list == 0) {
                bVar3 = bVar2;
            } else {
                float f10 = bVar2.c;
                if (list.isEmpty()) {
                    long j3 = C1891t.f19451j;
                    aVar = new G6.a(j3, j3, j3, j3, j3);
                } else {
                    aVar = new G6.a(C1891t.b(bVar2.d, ((C1891t) list.get(r82)).f19453a), C1891t.b(f10, ((C1891t) list.get(3 % list.size())).f19453a), C1891t.b(f10, ((C1891t) list.get(4 % list.size())).f19453a), C1891t.b(f10, ((C1891t) list.get(1 % list.size())).f19453a), C1891t.b(f10, ((C1891t) list.get(2 % list.size())).f19453a));
                }
                bVar3 = bVar2;
                AbstractC0120b.a(oVar, null, false, b0.g.b(737443129, new G6.e(bVar2, 0, aVar), c0761q), c0761q, (i11 & 14) | 3072, 6);
            }
            bVar4 = bVar3;
        }
        C0754m0 c0754m0R = c0761q.r();
        if (c0754m0R != null) {
            c0754m0R.d = new G6.c(oVar, str, bVar4, interfaceC2782k, i7, 0);
        }
    }

    public static final void p(final g0.o oVar, boolean z6, boolean z10, final boolean z11, final boolean z12, final boolean z13, final int i7, final PlayerStatus playerStatus, final boolean z14, final InterfaceC2772a interfaceC2772a, InterfaceC2772a interfaceC2772a2, final InterfaceC2772a interfaceC2772a3, InterfaceC2772a interfaceC2772a4, final InterfaceC2772a interfaceC2772a5, final InterfaceC2772a interfaceC2772a6, final InterfaceC2772a interfaceC2772a7, C0761q c0761q, final int i10, final int i11) {
        int i12;
        int i13;
        final InterfaceC2772a interfaceC2772a8;
        final InterfaceC2772a interfaceC2772a9;
        final boolean z15;
        final boolean z16;
        A7.m.f(oVar, "modifier");
        A7.m.f(playerStatus, "playerStatus");
        A7.m.f(interfaceC2772a, "onSkipPreviousClick");
        A7.m.f(interfaceC2772a3, "onPlayPauseClick");
        A7.m.f(interfaceC2772a5, "onSkipNextClick");
        A7.m.f(interfaceC2772a6, "onLoopingClick");
        A7.m.f(interfaceC2772a7, "onLikeClick");
        c0761q.S(1921325009);
        if ((i10 & 6) == 0) {
            i12 = (c0761q.f(oVar) ? 4 : 2) | i10;
        } else {
            i12 = i10;
        }
        if ((i10 & 3072) == 0) {
            i12 |= c0761q.g(z11) ? 2048 : 1024;
        }
        if ((i10 & 24576) == 0) {
            i12 |= c0761q.g(z12) ? 16384 : 8192;
        }
        if ((i10 & 196608) == 0) {
            i12 |= c0761q.g(z13) ? 131072 : 65536;
        }
        if ((i10 & 1572864) == 0) {
            i12 |= c0761q.d(i7) ? 1048576 : 524288;
        }
        if ((i10 & 12582912) == 0) {
            i12 |= c0761q.f(playerStatus) ? 8388608 : 4194304;
        }
        if ((i10 & 100663296) == 0) {
            i12 |= c0761q.g(z14) ? 67108864 : 33554432;
        }
        if ((i10 & 805306368) == 0) {
            i12 |= c0761q.h(interfaceC2772a) ? 536870912 : 268435456;
        }
        if ((i11 & 48) == 0) {
            i13 = i11 | (c0761q.h(interfaceC2772a3) ? 32 : 16);
        } else {
            i13 = i11;
        }
        if ((i11 & 3072) == 0) {
            i13 |= c0761q.h(interfaceC2772a5) ? 2048 : 1024;
        }
        if ((i11 & 24576) == 0) {
            i13 |= c0761q.h(interfaceC2772a6) ? 16384 : 8192;
        }
        if ((i11 & 196608) == 0) {
            i13 |= c0761q.h(interfaceC2772a7) ? 131072 : 65536;
        }
        if ((i12 & 306783235) == 306783234 && (i13 & 74769) == 74768 && c0761q.x()) {
            c0761q.K();
            z15 = z6;
            z16 = z10;
            interfaceC2772a8 = interfaceC2772a2;
            interfaceC2772a9 = interfaceC2772a4;
        } else {
            S s6 = C0753m.f8270a;
            c0761q.Q(-803502574);
            Object objG = c0761q.G();
            if (objG == s6) {
                objG = new A3.v(3);
                c0761q.a0(objG);
            }
            InterfaceC2772a interfaceC2772a10 = (InterfaceC2772a) objG;
            Object objE = M5.b.e(-803500270, c0761q, false);
            if (objE == s6) {
                objE = new A3.v(3);
                c0761q.a0(objE);
            }
            InterfaceC2772a interfaceC2772a11 = (InterfaceC2772a) objE;
            c0761q.p(false);
            int i14 = R.drawable.ic_loop_none;
            if (i7 != 0) {
                if (i7 == 1) {
                    i14 = R.drawable.ic_loop_song;
                } else if (i7 == 2) {
                    i14 = R.drawable.ic_loop_list;
                }
            }
            int i15 = i14;
            float fA0 = m6.c.f19252a.a0(c0761q);
            C0735d.a(AbstractC0227s0.l.a(a1.k.l), b0.g.b(-1970369775, new T5.e(oVar, fA0 * 0.75f, z14, z12, interfaceC2772a7, interfaceC2772a, fA0, playerStatus, interfaceC2772a3, z11, interfaceC2772a5, z13, i7, i15, interfaceC2772a6), c0761q), c0761q, 56);
            interfaceC2772a8 = interfaceC2772a10;
            interfaceC2772a9 = interfaceC2772a11;
            z15 = false;
            z16 = false;
        }
        C0754m0 c0754m0R = c0761q.r();
        if (c0754m0R != null) {
            c0754m0R.d = new InterfaceC2785n() { // from class: T5.a
                @Override // z7.InterfaceC2785n
                public final Object invoke(Object obj, Object obj2) {
                    ((Integer) obj2).getClass();
                    int iZ = C0735d.Z(i10 | 1);
                    int iZ2 = C0735d.Z(i11);
                    InterfaceC2772a interfaceC2772a12 = interfaceC2772a6;
                    InterfaceC2772a interfaceC2772a13 = interfaceC2772a7;
                    l9.l.p(oVar, z15, z16, z11, z12, z13, i7, playerStatus, z14, interfaceC2772a, interfaceC2772a8, interfaceC2772a3, interfaceC2772a9, interfaceC2772a5, interfaceC2772a12, interfaceC2772a13, (C0761q) obj, iZ, iZ2);
                    return m7.q.f19267a;
                }
            };
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:61:0x00b7  */
    /* JADX WARN: Removed duplicated region for block: B:62:0x00b9  */
    /* JADX WARN: Removed duplicated region for block: B:67:0x00c4  */
    /* JADX WARN: Removed duplicated region for block: B:70:0x00f6  */
    /* JADX WARN: Removed duplicated region for block: B:71:0x00fa  */
    /* JADX WARN: Removed duplicated region for block: B:76:0x011b  */
    /* JADX WARN: Removed duplicated region for block: B:79:0x012d  */
    /* JADX WARN: Removed duplicated region for block: B:81:0x0130  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static final void q(final g0.o r19, final int r20, final boolean r21, long r22, final z7.InterfaceC2772a r24, T.C0761q r25, final int r26, final int r27) {
        /*
            Method dump skipped, instruction units count: 374
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: l9.l.q(g0.o, int, boolean, long, z7.a, T.q, int, int):void");
    }

    public static final void r(g0.o oVar, boolean z6, boolean z10, PlayerStatus playerStatus, V8.N n10, InterfaceC2772a interfaceC2772a, InterfaceC2772a interfaceC2772a2, InterfaceC2772a interfaceC2772a3, C0761q c0761q, int i7) {
        int i10;
        A7.m.f(oVar, "modifier");
        A7.m.f(playerStatus, "playerStatus");
        A7.m.f(n10, "playerProgress");
        A7.m.f(interfaceC2772a, "onPlayPauseClick");
        A7.m.f(interfaceC2772a2, "onSkipNextClick");
        A7.m.f(interfaceC2772a3, "onSkipPreviousClick");
        c0761q.S(-895963801);
        if ((i7 & 6) == 0) {
            i10 = (c0761q.f(oVar) ? 4 : 2) | i7;
        } else {
            i10 = i7;
        }
        if ((i7 & 48) == 0) {
            i10 |= c0761q.g(z6) ? 32 : 16;
        }
        if ((i7 & 384) == 0) {
            i10 |= c0761q.g(z10) ? 256 : 128;
        }
        if ((i7 & 3072) == 0) {
            i10 |= c0761q.f(playerStatus) ? 2048 : 1024;
        }
        if ((i7 & 24576) == 0) {
            i10 |= c0761q.h(n10) ? 16384 : 8192;
        }
        if ((196608 & i7) == 0) {
            i10 |= c0761q.h(interfaceC2772a) ? 131072 : 65536;
        }
        if ((1572864 & i7) == 0) {
            i10 |= c0761q.h(interfaceC2772a2) ? 1048576 : 524288;
        }
        if ((12582912 & i7) == 0) {
            i10 |= c0761q.h(interfaceC2772a3) ? 8388608 : 4194304;
        }
        if ((i10 & 4793491) == 4793490 && c0761q.x()) {
            c0761q.K();
        } else {
            C0735d.a(AbstractC0227s0.l.a(a1.k.l), b0.g.b(-472588121, new T5.f(oVar, z10, interfaceC2772a3, n10, playerStatus, interfaceC2772a, z6, interfaceC2772a2), c0761q), c0761q, 56);
        }
        C0754m0 c0754m0R = c0761q.r();
        if (c0754m0R != null) {
            c0754m0R.d = new T5.b(oVar, z6, z10, playerStatus, n10, interfaceC2772a, interfaceC2772a2, interfaceC2772a3, i7);
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:106:0x01dc  */
    /* JADX WARN: Removed duplicated region for block: B:108:0x01ef  */
    /* JADX WARN: Removed duplicated region for block: B:109:0x01f3  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static final void s(g0.o r21, com.stingray.karaoke.car.player.PlayerStatus r22, java.lang.Float r23, boolean r24, z7.InterfaceC2772a r25, T.C0761q r26, int r27) {
        /*
            Method dump skipped, instruction units count: 551
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: l9.l.s(g0.o, com.stingray.karaoke.car.player.PlayerStatus, java.lang.Float, boolean, z7.a, T.q, int):void");
    }

    /*  JADX ERROR: JadxRuntimeException in pass: ConstructorVisitor
        jadx.core.utils.exceptions.JadxRuntimeException: Can't remove SSA var: r3v21 ??, still in use, count: 1, list:
          (r3v21 ?? I:java.lang.Object) from 0x06dd: INVOKE (r12v12 ?? I:T.q), (r3v21 ?? I:java.lang.Object) VIRTUAL call: T.q.a0(java.lang.Object):void A[MD:(java.lang.Object):void (m)]
        	at jadx.core.utils.InsnRemover.removeSsaVar(InsnRemover.java:162)
        	at jadx.core.utils.InsnRemover.unbindResult(InsnRemover.java:127)
        	at jadx.core.utils.InsnRemover.lambda$unbindInsns$1(InsnRemover.java:99)
        	at java.base/java.util.ArrayList.forEach(ArrayList.java:1596)
        	at jadx.core.utils.InsnRemover.unbindInsns(InsnRemover.java:98)
        	at jadx.core.utils.InsnRemover.perform(InsnRemover.java:73)
        	at jadx.core.dex.visitors.ConstructorVisitor.replaceInvoke(ConstructorVisitor.java:59)
        	at jadx.core.dex.visitors.ConstructorVisitor.visit(ConstructorVisitor.java:42)
        */
    public static final void t(
    /*  JADX ERROR: JadxRuntimeException in pass: ConstructorVisitor
        jadx.core.utils.exceptions.JadxRuntimeException: Can't remove SSA var: r3v21 ??, still in use, count: 1, list:
          (r3v21 ?? I:java.lang.Object) from 0x06dd: INVOKE (r12v12 ?? I:T.q), (r3v21 ?? I:java.lang.Object) VIRTUAL call: T.q.a0(java.lang.Object):void A[MD:(java.lang.Object):void (m)]
        	at jadx.core.utils.InsnRemover.removeSsaVar(InsnRemover.java:162)
        	at jadx.core.utils.InsnRemover.unbindResult(InsnRemover.java:127)
        	at jadx.core.utils.InsnRemover.lambda$unbindInsns$1(InsnRemover.java:99)
        	at java.base/java.util.ArrayList.forEach(ArrayList.java:1596)
        	at jadx.core.utils.InsnRemover.unbindInsns(InsnRemover.java:98)
        	at jadx.core.utils.InsnRemover.perform(InsnRemover.java:73)
        	at jadx.core.dex.visitors.ConstructorVisitor.replaceInvoke(ConstructorVisitor.java:59)
        */
    /*  JADX ERROR: Method generation error
        jadx.core.utils.exceptions.JadxRuntimeException: Code variable not set in r45v0 ??
        	at jadx.core.dex.instructions.args.SSAVar.getCodeVar(SSAVar.java:236)
        	at jadx.core.codegen.MethodGen.addMethodArguments(MethodGen.java:224)
        	at jadx.core.codegen.MethodGen.addDefinition(MethodGen.java:169)
        	at jadx.core.codegen.ClassGen.addMethodCode(ClassGen.java:407)
        	at jadx.core.codegen.ClassGen.addMethod(ClassGen.java:337)
        	at jadx.core.codegen.ClassGen.lambda$addInnerClsAndMethods$3(ClassGen.java:303)
        	at java.base/java.util.stream.ForEachOps$ForEachOp$OfRef.accept(ForEachOps.java:184)
        	at java.base/java.util.ArrayList.forEach(ArrayList.java:1596)
        	at java.base/java.util.stream.SortedOps$RefSortingSink.end(SortedOps.java:395)
        	at java.base/java.util.stream.Sink$ChainedReference.end(Sink.java:261)
        */

    public static final void u(final g0.o oVar, final String str, final int i7, final String str2, final String str3, final String str4, final boolean z6, final InterfaceC2782k interfaceC2782k, final InterfaceC2782k interfaceC2782k2, final P5.p pVar, final PlayerStatus playerStatus, final boolean z10, final boolean z11, final boolean z12, C0761q c0761q, final int i10, final int i11) {
        int i12;
        int i13;
        A7.m.f(str, "uniqueId");
        A7.m.f(interfaceC2782k, "onRowClicked");
        A7.m.f(interfaceC2782k2, "dismissAction");
        A7.m.f(playerStatus, "playerStatus");
        c0761q.S(-1511451436);
        if ((i10 & 6) == 0) {
            i12 = (c0761q.f(oVar) ? 4 : 2) | i10;
        } else {
            i12 = i10;
        }
        if ((i10 & 48) == 0) {
            i12 |= c0761q.f(str) ? 32 : 16;
        }
        if ((i10 & 384) == 0) {
            i12 |= c0761q.d(i7) ? 256 : 128;
        }
        if ((i10 & 3072) == 0) {
            i12 |= c0761q.f(str2) ? 2048 : 1024;
        }
        if ((i10 & 24576) == 0) {
            i12 |= c0761q.f(str3) ? 16384 : 8192;
        }
        if ((i10 & 196608) == 0) {
            i12 |= c0761q.f(str4) ? 131072 : 65536;
        }
        if ((i10 & 1572864) == 0) {
            i12 |= c0761q.g(z6) ? 1048576 : 524288;
        }
        if ((i10 & 12582912) == 0) {
            i12 |= c0761q.h(interfaceC2782k) ? 8388608 : 4194304;
        }
        if ((i10 & 100663296) == 0) {
            i12 |= c0761q.h(interfaceC2782k2) ? 67108864 : 33554432;
        }
        if ((i10 & 805306368) == 0) {
            i12 |= c0761q.f(pVar) ? 536870912 : 268435456;
        }
        int i14 = i12;
        if ((i11 & 6) == 0) {
            i13 = i11 | (c0761q.f(playerStatus) ? 4 : 2);
        } else {
            i13 = i11;
        }
        if ((i11 & 48) == 0) {
            i13 |= c0761q.g(z10) ? 32 : 16;
        }
        if ((i11 & 384) == 0) {
            i13 |= c0761q.g(z11) ? 256 : 128;
        }
        if ((i11 & 3072) == 0) {
            i13 |= c0761q.g(z12) ? 2048 : 1024;
        }
        if ((i14 & 306783379) == 306783378 && (i13 & 1171) == 1170 && c0761q.x()) {
            c0761q.K();
        } else {
            c0761q.Q(-1435666792);
            int i15 = i14 & 112;
            boolean z13 = ((i14 & 234881024) == 67108864) | (i15 == 32);
            Object objG = c0761q.G();
            S s6 = C0753m.f8270a;
            if (z13 || objG == s6) {
                objG = new O(interfaceC2782k2, 1, str);
                c0761q.a0(objG);
            }
            InterfaceC2782k interfaceC2782k3 = (InterfaceC2782k) objG;
            Object objE = M5.b.e(-1435660329, c0761q, false);
            if (objE == s6) {
                objE = new A3.c(21);
                c0761q.a0(objE);
            }
            InterfaceC2782k interfaceC2782k4 = (InterfaceC2782k) objE;
            c0761q.p(false);
            float f10 = AbstractC0536v0.f6061a;
            EnumC0542y0 enumC0542y0 = EnumC0542y0.f6072n;
            a1.b bVar = (a1.b) c0761q.k(AbstractC0227s0.f2259f);
            Object[] objArr = new Object[0];
            C0538w0 c0538w0 = C0538w0.f6063n;
            C0022k c0022k = new C0022k(bVar, interfaceC2782k3, interfaceC2782k4, 8);
            io.sentry.internal.debugmeta.c cVar = d0.o.f13275a;
            io.sentry.internal.debugmeta.c cVar2 = new io.sentry.internal.debugmeta.c(c0538w0, 15, c0022k);
            boolean zF = c0761q.f(enumC0542y0) | c0761q.f(bVar) | c0761q.f(interfaceC2782k3);
            Object objG2 = c0761q.G();
            if (zF || objG2 == s6) {
                objG2 = new G(enumC0542y0, bVar, interfaceC2782k3, interfaceC2782k4, 1);
                c0761q.a0(objG2);
            }
            C0540x0 c0540x0 = (C0540x0) w0.c.Z(objArr, cVar2, (InterfaceC2772a) objG2, c0761q, 0, 4);
            g0.o oVarN = h6.q.n(androidx.compose.foundation.layout.c.d(oVar, 1.0f));
            c0761q.Q(-1435655261);
            boolean z14 = (i15 == 32) | ((i14 & 29360128) == 8388608);
            Object objG3 = c0761q.G();
            if (z14 || objG3 == s6) {
                objG3 = new M5.d(interfaceC2782k, 4, str);
                c0761q.a0(objG3);
            }
            c0761q.p(false);
            AbstractC0536v0.a(c0540x0, b0.g.b(-492008202, new y(c0540x0, 13), c0761q), com.bumptech.glide.c.P(oVarN, false, null, null, (InterfaceC2772a) objG3, 15), false, true, false, b0.g.b(1069067857, new C0600n2(z6, str4, z12, z11, z10, pVar, playerStatus, i7, str2, str3), c0761q), c0761q, 1600560);
        }
        C0754m0 c0754m0R = c0761q.r();
        if (c0754m0R != null) {
            c0754m0R.d = new InterfaceC2785n() { // from class: Q5.d2
                @Override // z7.InterfaceC2785n
                public final Object invoke(Object obj, Object obj2) {
                    ((Integer) obj2).getClass();
                    int iZ = C0735d.Z(i10 | 1);
                    int iZ2 = C0735d.Z(i11);
                    String str5 = str2;
                    String str6 = str3;
                    String str7 = str4;
                    P5.p pVar2 = pVar;
                    boolean z15 = z11;
                    boolean z16 = z12;
                    l9.l.u(oVar, str, i7, str5, str6, str7, z6, interfaceC2782k, interfaceC2782k2, pVar2, playerStatus, z10, z15, z16, (C0761q) obj, iZ, iZ2);
                    return m7.q.f19267a;
                }
            };
        }
    }

    public static final void v(PlayerStatus playerStatus, H0 h02, C1930E c1930e, C0761q c0761q, int i7) {
        int i10;
        A7.m.f(playerStatus, "playerStatus");
        c0761q.S(-1249127140);
        if ((i7 & 6) == 0) {
            i10 = (c0761q.f(playerStatus) ? 4 : 2) | i7;
        } else {
            i10 = i7;
        }
        if ((i7 & 48) == 0) {
            i10 |= c0761q.h(h02) ? 32 : 16;
        }
        if ((i7 & 384) == 0) {
            i10 |= c0761q.h(c1930e) ? 256 : 128;
        }
        if ((i10 & 147) == 146 && c0761q.x()) {
            c0761q.K();
        } else {
            AbstractC0904a.l(null, new C0607p1(h02, null, false, null, 254), b0.g.b(-477023330, new C0608p2(h02, c1930e, playerStatus), c0761q), c0761q, 384, 1);
        }
        C0754m0 c0754m0R = c0761q.r();
        if (c0754m0R != null) {
            c0754m0R.d = new C0641y0(i7, 2, playerStatus, h02, c1930e);
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:32:0x0065  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static final void w(final g0.o r17, final float r18, boolean r19, T.C0761q r20, final int r21) {
        /*
            Method dump skipped, instruction units count: 321
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: l9.l.w(g0.o, float, boolean, T.q, int):void");
    }

    public static final void x(C0540x0 c0540x0, C0761q c0761q, int i7) {
        int i10;
        long j3;
        int i11 = 3;
        A7.m.f(c0540x0, "dismissState");
        c0761q.S(-1074711879);
        if ((i7 & 6) == 0) {
            i10 = ((i7 & 8) == 0 ? c0761q.f(c0540x0) : c0761q.h(c0540x0) ? 4 : 2) | i7;
        } else {
            i10 = i7;
        }
        if ((i10 & 3) == 2 && c0761q.x()) {
            c0761q.K();
        } else {
            R.w wVar = c0540x0.f6069b;
            EnumC0542y0 enumC0542y0 = (EnumC0542y0) wVar.f7330g.getValue();
            EnumC0542y0 enumC0542y02 = EnumC0542y0.f6072n;
            if (enumC0542y0 != enumC0542y02) {
                c0761q.Q(-736691277);
                j3 = l6.d.c(c0761q).f18722x;
                c0761q.p(false);
            } else {
                float fI = wVar.f7332i.i();
                EnumC0542y0 enumC0542y03 = EnumC0542y0.f6071m;
                if (fI != 0.0f) {
                    C0730a0 c0730a0 = wVar.f7332i;
                    if (!Float.isNaN(c0730a0.i())) {
                        enumC0542y02 = c0730a0.i() > 0.0f ? EnumC0542y0.l : enumC0542y03;
                    }
                }
                if (enumC0542y02 == enumC0542y03) {
                    c0761q.Q(-1362490061);
                    j3 = l6.d.c(c0761q).f18700e;
                    c0761q.p(false);
                } else {
                    c0761q.Q(-1362439035);
                    j3 = l6.d.c(c0761q).f18724z;
                    c0761q.p(false);
                }
            }
            Q0 q0A = w.L.a(j3, null, "SwipeBackground", c0761q, 384, 10);
            g0.l lVar = g0.l.f14684a;
            g0.o oVarB = androidx.compose.foundation.a.b(lVar, ((C1891t) q0A.getValue()).f19453a, AbstractC1861K.f19381a);
            m6.b bVar = m6.c.f19252a;
            g0.o oVarC = androidx.compose.foundation.layout.c.c(androidx.compose.foundation.layout.a.o(oVarB, 0.0f, 0.0f, bVar.F(c0761q), 0.0f, 11), 1.0f);
            C0.K kE = AbstractC0133o.e(g0.b.f14665q, false);
            int i12 = c0761q.f8304P;
            InterfaceC0744h0 interfaceC0744h0M = c0761q.m();
            g0.o oVarD = g0.a.d(c0761q, oVarC);
            InterfaceC0161k.f1681b.getClass();
            C0159i c0159i = C0160j.f1678b;
            c0761q.U();
            if (c0761q.f8303O) {
                c0761q.l(c0159i);
            } else {
                c0761q.d0();
            }
            C0158h c0158h = C0160j.f1679e;
            C0735d.V(c0761q, kE, c0158h);
            C0158h c0158h2 = C0160j.d;
            C0735d.V(c0761q, interfaceC0744h0M, c0158h2);
            C0158h c0158h3 = C0160j.f1680f;
            if (c0761q.f8303O || !A7.m.a(c0761q.G(), Integer.valueOf(i12))) {
                AbstractC1347d.l(i12, c0761q, i12, c0158h3);
            }
            C0158h c0158h4 = C0160j.c;
            C0735d.V(c0761q, oVarD, c0158h4);
            C0119a c0119a = AbstractC0127i.f1076a;
            C0141x c0141xA = AbstractC0139v.a(new C0124f(bVar.H(6, c0761q)), g0.b.f14672x, c0761q, 0);
            int i13 = c0761q.f8304P;
            InterfaceC0744h0 interfaceC0744h0M2 = c0761q.m();
            g0.o oVarD2 = g0.a.d(c0761q, lVar);
            c0761q.U();
            if (c0761q.f8303O) {
                c0761q.l(c0159i);
            } else {
                c0761q.d0();
            }
            C0735d.V(c0761q, c0141xA, c0158h);
            C0735d.V(c0761q, interfaceC0744h0M2, c0158h2);
            if (c0761q.f8303O || !A7.m.a(c0761q.G(), Integer.valueOf(i13))) {
                AbstractC1347d.l(i13, c0761q, i13, c0158h3);
            }
            C0735d.V(c0761q, oVarD2, c0158h4);
            l6.d.b(true, false, b0.g.b(1564757001, new F(i11), c0761q), c0761q, 390, 2);
            c0761q.p(true);
            c0761q.p(true);
        }
        C0754m0 c0754m0R = c0761q.r();
        if (c0754m0R != null) {
            c0754m0R.d = new C0545a(i7, 8, c0540x0);
        }
    }

    public static final void y(boolean z6, Y0.h hVar, K k10, C0761q c0761q, int i7) {
        int i10;
        c0761q.S(-1344558920);
        if ((i7 & 6) == 0) {
            i10 = (c0761q.g(z6) ? 4 : 2) | i7;
        } else {
            i10 = i7;
        }
        if ((i7 & 48) == 0) {
            i10 |= c0761q.f(hVar) ? 32 : 16;
        }
        if ((i7 & 384) == 0) {
            i10 |= c0761q.h(k10) ? 256 : 128;
        }
        if ((i10 & 147) == 146 && c0761q.x()) {
            c0761q.K();
        } else {
            int i11 = i10 & 14;
            boolean zF = (i11 == 4) | c0761q.f(k10);
            Object objG = c0761q.G();
            Object obj = C0753m.f8270a;
            if (zF || objG == obj) {
                objG = new O.J(k10, z6);
                c0761q.a0(objG);
            }
            InterfaceC0355n0 interfaceC0355n0 = (InterfaceC0355n0) objG;
            boolean zH = c0761q.h(k10) | (i11 == 4);
            Object objG2 = c0761q.G();
            if (zH || objG2 == obj) {
                objG2 = new O.L(k10, z6);
                c0761q.a0(objG2);
            }
            InterfaceC0480k interfaceC0480k = (InterfaceC0480k) objG2;
            boolean zF2 = N0.K.f(k10.j().f8482b);
            g0.l lVar = g0.l.f14684a;
            boolean zH2 = c0761q.h(interfaceC0355n0);
            Object objG3 = c0761q.G();
            if (zH2 || objG3 == obj) {
                objG3 = new O.M(interfaceC0355n0, null);
                c0761q.a0(objG3);
            }
            AbstractC0904a.n(interfaceC0480k, z6, hVar, zF2, 0L, y0.v.a(lVar, interfaceC0355n0, (InterfaceC2785n) objG3), c0761q, (i10 << 3) & 1008);
        }
        C0754m0 c0754m0R = c0761q.r();
        if (c0754m0R != null) {
            c0754m0R.d = new C0475f(z6, hVar, k10, i7);
        }
    }

    public static final void z(LeadVocalState leadVocalState, t6.h hVar, X x8, X x9, float f10) {
        int iMin = (int) f10;
        if (leadVocalState == LeadVocalState.LeadVocalOn || leadVocalState == LeadVocalState.DisabledLeadVocalOn) {
            iMin += hVar != null ? Math.min(hVar.f22492a, hVar.f22493b) : 0;
        }
        y6.f fVar = (y6.f) x8.getValue();
        if (fVar != null) {
            fVar.p(iMin);
        }
        y6.f fVar2 = (y6.f) x9.getValue();
        if (fVar2 != null) {
            fVar2.p(iMin);
        }
    }
}
