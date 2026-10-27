package x3;

import A7.m;
import C0.C0102i;
import C0.c0;
import D.AbstractC0120b;
import D.AbstractC0127i;
import D.AbstractC0133o;
import D.AbstractC0139v;
import D.C0141x;
import D.C0142y;
import D.L;
import D.P;
import D.S;
import D.T;
import E0.C0158h;
import E0.C0159i;
import E0.C0160j;
import E0.InterfaceC0161k;
import F0.AbstractC0227s0;
import F0.C0223q;
import I5.u;
import I5.y;
import J.e;
import K.L0;
import L3.f;
import N0.K;
import N0.r;
import N7.i;
import P8.n;
import Q.c1;
import Q5.C0607p1;
import Q5.C0619s2;
import Q5.H;
import Q5.K0;
import Q5.M;
import Q5.M1;
import Q5.N1;
import Q5.O;
import Q5.O1;
import Q5.Y0;
import Q7.InterfaceC0650c;
import S0.A;
import S0.I;
import S0.J;
import S0.z;
import T.C0735d;
import T.C0753m;
import T.C0754m0;
import T.C0761q;
import T.C0773y;
import T.InterfaceC0744h0;
import T.X;
import U8.EnumC0810a;
import U8.l;
import U8.o;
import U8.p;
import U8.v;
import V8.N;
import X5.C0875o;
import Z7.g;
import a.AbstractC0904a;
import a1.k;
import android.content.Context;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.os.Bundle;
import android.os.Parcelable;
import androidx.compose.ui.platform.AndroidCompositionLocals_androidKt;
import androidx.versionedparcelable.ParcelImpl;
import com.stingray.drivemodedetector.DriveMode;
import com.stingray.drivemodedetector.R;
import com.stingray.karaoke.car.player.LeadVocalState;
import com.stingray.karaoke.car.player.PlayerStatus;
import com.stingray.karaoke.car.ui.viewmodel.base.LoadingInterface$LoadingState;
import com.stingray.karaoke.car.utils.FontSize;
import com.stingray.karaoke.car.utils.LayoutMode;
import com.stingray.kca.model.KaraokeItemSortTypeInfo;
import d0.h;
import g1.AbstractC1274a;
import g1.C1283j;
import g1.C1285l;
import g5.C1301b;
import h2.AbstractC1347d;
import h6.q;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import k6.AbstractC1692D;
import n0.AbstractC1861K;
import n0.C1853C;
import n0.C1886o;
import n0.C1891t;
import n6.C1966G;
import n7.AbstractC1998m;
import n7.AbstractC1999n;
import n7.AbstractC2000o;
import v8.AbstractC2564d;
import w.AbstractC2565A;
import x3.AbstractC2673a;
import z7.InterfaceC2772a;
import z7.InterfaceC2782k;
import z7.InterfaceC2785n;

/* JADX INFO: renamed from: x3.a, reason: case insensitive filesystem */
/* JADX INFO: loaded from: classes.dex */
public abstract class AbstractC2673a {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final /* synthetic */ int f23768a = 0;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final /* synthetic */ int f23769b = 0;
    public static final /* synthetic */ int c = 0;

    /* JADX WARN: Multi-variable type inference failed */
    public static long[] A(Serializable serializable) {
        if (!(serializable instanceof int[])) {
            if (serializable instanceof long[]) {
                return (long[]) serializable;
            }
            return null;
        }
        int[] iArr = (int[]) serializable;
        long[] jArr = new long[iArr.length];
        for (int i7 = 0; i7 < iArr.length; i7++) {
            jArr[i7] = iArr[i7];
        }
        return jArr;
    }

    public static final float B(float f10) {
        float fIntBitsToFloat = Float.intBitsToFloat(((int) ((((long) Float.floatToRawIntBits(f10)) & 8589934591L) / ((long) 3))) + 709952852);
        float f11 = fIntBitsToFloat - ((fIntBitsToFloat - (f10 / (fIntBitsToFloat * fIntBitsToFloat))) * 0.33333334f);
        return f11 - ((f11 - (f10 / (f11 * f11))) * 0.33333334f);
    }

    public static final int C(int i7, List list) {
        int size = list.size() - 1;
        int i10 = 0;
        while (i10 <= size) {
            int i11 = (i10 + size) >>> 1;
            r rVar = (r) list.get(i11);
            byte b10 = rVar.f4403b > i7 ? (byte) 1 : rVar.c <= i7 ? (byte) -1 : (byte) 0;
            if (b10 < 0) {
                i10 = i11 + 1;
            } else {
                if (b10 <= 0) {
                    return i11;
                }
                size = i11 - 1;
            }
        }
        return -(i10 + 1);
    }

    public static final int D(int i7, List list) {
        int size = list.size() - 1;
        int i10 = 0;
        while (i10 <= size) {
            int i11 = (i10 + size) >>> 1;
            r rVar = (r) list.get(i11);
            byte b10 = rVar.d > i7 ? (byte) 1 : rVar.f4404e <= i7 ? (byte) -1 : (byte) 0;
            if (b10 < 0) {
                i10 = i11 + 1;
            } else {
                if (b10 <= 0) {
                    return i11;
                }
                size = i11 - 1;
            }
        }
        return -(i10 + 1);
    }

    public static final int E(ArrayList arrayList, float f10) {
        if (f10 <= 0.0f) {
            return 0;
        }
        if (f10 >= ((r) AbstractC1998m.h0(arrayList)).f4406g) {
            return AbstractC1999n.C(arrayList);
        }
        int size = arrayList.size() - 1;
        int i7 = 0;
        while (i7 <= size) {
            int i10 = (i7 + size) >>> 1;
            r rVar = (r) arrayList.get(i10);
            byte b10 = rVar.f4405f > f10 ? (byte) 1 : rVar.f4406g <= f10 ? (byte) -1 : (byte) 0;
            if (b10 < 0) {
                i7 = i10 + 1;
            } else {
                if (b10 <= 0) {
                    return i10;
                }
                size = i10 - 1;
            }
        }
        return -(i7 + 1);
    }

    public static final void F(ArrayList arrayList, long j3, InterfaceC2782k interfaceC2782k) {
        int size = arrayList.size();
        for (int iC = C(K.e(j3), arrayList); iC < size; iC++) {
            r rVar = (r) arrayList.get(iC);
            if (rVar.f4403b >= K.d(j3)) {
                return;
            }
            if (rVar.f4403b != rVar.c) {
                interfaceC2782k.b(rVar);
            }
        }
    }

    public static int G(byte[] bArr) {
        boolean z6 = bArr.length >= 4;
        int length = bArr.length;
        if (!z6) {
            throw new IllegalArgumentException(com.bumptech.glide.c.r0("array too small: %s < %s", Integer.valueOf(length), 4));
        }
        return (bArr[3] & 255) | (bArr[0] << 24) | ((bArr[1] & 255) << 16) | ((bArr[2] & 255) << 8);
    }

    public static final float H(L l, C0761q c0761q) {
        m.f(l, "<this>");
        c0761q.Q(-1848796479);
        float fA = l.a();
        c0761q.p(false);
        return fA;
    }

    public static final float I(L l, C0761q c0761q) {
        m.f(l, "<this>");
        c0761q.Q(-348932059);
        float fE = androidx.compose.foundation.layout.a.e(l, (k) c0761q.k(AbstractC0227s0.l));
        c0761q.p(false);
        return fE;
    }

    public static final float J(L l, C0761q c0761q) {
        m.f(l, "<this>");
        c0761q.Q(-1586656443);
        float f10 = androidx.compose.foundation.layout.a.f(l, (k) c0761q.k(AbstractC0227s0.l));
        c0761q.p(false);
        return f10;
    }

    public static final float K(L l, C0761q c0761q) {
        m.f(l, "<this>");
        c0761q.Q(1074879333);
        float fD = l.d();
        c0761q.p(false);
        return fD;
    }

    public static InterfaceC2676d L(Bundle bundle) {
        try {
            Bundle bundle2 = (Bundle) bundle.getParcelable("android.support.v4.media.session.SESSION_TOKEN2");
            if (bundle2 == null) {
                return null;
            }
            bundle2.setClassLoader(AbstractC2673a.class.getClassLoader());
            Parcelable parcelable = bundle2.getParcelable("a");
            if (parcelable instanceof ParcelImpl) {
                return ((ParcelImpl) parcelable).l;
            }
            throw new IllegalArgumentException("Invalid parcel");
        } catch (RuntimeException unused) {
            return null;
        }
    }

    public static boolean M(InterfaceC0650c interfaceC0650c) {
        m.f(interfaceC0650c, "callableMemberDescriptor");
        if (!g.d.contains(interfaceC0650c.getName())) {
            return false;
        }
        if (!AbstractC1998m.T(g.c, AbstractC2564d.c(interfaceC0650c)) || !interfaceC0650c.D0().isEmpty()) {
            if (!i.y(interfaceC0650c)) {
                return false;
            }
            Collection collectionQ = interfaceC0650c.q();
            m.e(collectionQ, "overriddenDescriptors");
            Collection<InterfaceC0650c> collection = collectionQ;
            if (collection.isEmpty()) {
                return false;
            }
            for (InterfaceC0650c interfaceC0650c2 : collection) {
                m.e(interfaceC0650c2, "it");
                if (M(interfaceC0650c2)) {
                }
            }
            return false;
        }
        return true;
    }

    public static boolean N(int i7) {
        int type = Character.getType(i7);
        return type == 23 || type == 20 || type == 22 || type == 30 || type == 29 || type == 24 || type == 21;
    }

    public static final float O(float f10, float f11, float f12) {
        return (f12 * f11) + ((1 - f12) * f10);
    }

    public static final int P(int i7, int i10, float f10) {
        return i7 + ((int) Math.round(((double) (i10 - i7)) * ((double) f10)));
    }

    public static final long Q(float f10, long j3) {
        return (Float.isNaN(f10) || f10 >= 1.0f) ? j3 : C1891t.b(C1891t.d(j3) * f10, j3);
    }

    /* JADX WARN: Removed duplicated region for block: B:15:0x0048  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static c9.C1046g R(c9.u r25) {
        /*
            Method dump skipped, instruction units count: 526
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: x3.AbstractC2673a.R(c9.u):c9.g");
    }

    public static final h S(C0761q c0761q) {
        c0761q.Q(-796080049);
        h hVar = (h) w0.c.Z(new Object[0], h.d, d0.i.f13270n, c0761q, 3072, 4);
        hVar.c = (d0.k) c0761q.k(d0.m.f13274a);
        c0761q.p(false);
        return hVar;
    }

    public static final long T(long j3) {
        int iRound = Math.round(m0.c.d(j3));
        return (((long) Math.round(m0.c.e(j3))) & 4294967295L) | (((long) iRound) << 32);
    }

    public static int U(long j3) {
        if (j3 > 2147483647L) {
            return Integer.MAX_VALUE;
        }
        if (j3 < -2147483648L) {
            return Integer.MIN_VALUE;
        }
        return (int) j3;
    }

    public static final Typeface V(Typeface typeface, z zVar, Context context) {
        ThreadLocal threadLocal = J.f7812a;
        if (typeface == null) {
            return null;
        }
        ArrayList arrayList = zVar.f7862a;
        if (arrayList.isEmpty()) {
            return typeface;
        }
        ThreadLocal threadLocal2 = J.f7812a;
        Paint paint = (Paint) threadLocal2.get();
        if (paint == null) {
            paint = new Paint();
            threadLocal2.set(paint);
        }
        paint.setTypeface(typeface);
        paint.setFontVariationSettings(w0.c.L(arrayList, null, new I(AbstractC1274a.f(context), 0), 31));
        return paint.getTypeface();
    }

    public static G7.b W(G7.d dVar, int i7) {
        m.f(dVar, "<this>");
        p(i7 > 0, Integer.valueOf(i7));
        if (dVar.f2713n <= 0) {
            i7 = -i7;
        }
        return new G7.b(dVar.l, dVar.f2712m, i7);
    }

    public static final String X(int i7, C0761q c0761q) {
        c0761q.k(AndroidCompositionLocals_androidKt.f11687a);
        return ((Context) c0761q.k(AndroidCompositionLocals_androidKt.f11688b)).getResources().getString(i7);
    }

    public static final String Y(int i7, Object[] objArr, C0761q c0761q) {
        c0761q.k(AndroidCompositionLocals_androidKt.f11687a);
        return ((Context) c0761q.k(AndroidCompositionLocals_androidKt.f11688b)).getResources().getString(i7, Arrays.copyOf(objArr, objArr.length));
    }

    public static int[] Z(Collection collection) {
        if (collection instanceof C1301b) {
            C1301b c1301b = (C1301b) collection;
            return Arrays.copyOfRange(c1301b.l, c1301b.f14978m, c1301b.f14979n);
        }
        Object[] array = collection.toArray();
        int length = array.length;
        int[] iArr = new int[length];
        for (int i7 = 0; i7 < length; i7++) {
            Object obj = array[i7];
            obj.getClass();
            iArr[i7] = ((Number) obj).intValue();
        }
        return iArr;
    }

    public static l a(int i7, int i10, EnumC0810a enumC0810a) {
        l vVar;
        if ((i10 & 1) != 0) {
            i7 = 0;
        }
        if ((i10 & 2) != 0) {
            enumC0810a = EnumC0810a.l;
        }
        if (i7 != -2) {
            if (i7 == -1) {
                if (enumC0810a == EnumC0810a.l) {
                    return new v(1, EnumC0810a.f9223m, null);
                }
                throw new IllegalArgumentException("CONFLATED capacity cannot be used with non-default onBufferOverflow");
            }
            if (i7 == 0) {
                vVar = enumC0810a == EnumC0810a.l ? new l(0, null) : new v(1, enumC0810a, null);
            } else {
                if (i7 != Integer.MAX_VALUE) {
                    return enumC0810a == EnumC0810a.l ? new l(i7, null) : new v(i7, enumC0810a, null);
                }
                vVar = new l(Integer.MAX_VALUE, null);
            }
        } else if (enumC0810a == EnumC0810a.l) {
            p.f9268e.getClass();
            vVar = new l(o.f9267b, null);
        } else {
            vVar = new v(1, enumC0810a, null);
        }
        return vVar;
    }

    public static final E4.b a0(L0 l02) {
        int i7 = l02.f3181n;
        int i10 = l02.f3180m;
        if (i10 != i7) {
            throw new IllegalStateException("Non-square qr byte matrix");
        }
        E4.b bVar = new E4.b(i10);
        for (int i11 = 0; i11 < i10; i11++) {
            for (int i12 = 0; i12 < i10; i12++) {
                bVar.f(i11, i12, l02.g(i11, i12) == 1 ? E4.a.l : E4.a.f1744m);
            }
        }
        return bVar;
    }

    /* JADX WARN: Removed duplicated region for block: B:103:0x0205  */
    /* JADX WARN: Removed duplicated region for block: B:107:0x0211  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static final void b(final g0.o r28, final java.util.ArrayList r29, final int r30, final com.stingray.karaoke.car.utils.FontSize r31, boolean r32, final z7.InterfaceC2782k r33, T.C0761q r34, final int r35) {
        /*
            Method dump skipped, instruction units count: 662
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: x3.AbstractC2673a.b(g0.o, java.util.ArrayList, int, com.stingray.karaoke.car.utils.FontSize, boolean, z7.k, T.q, int):void");
    }

    public static G7.d b0(int i7, int i10) {
        if (i10 > Integer.MIN_VALUE) {
            return new G7.d(i7, i10 - 1, 1);
        }
        G7.d dVar = G7.d.o;
        return G7.d.o;
    }

    public static final void c(g0.o oVar, C0761q c0761q, int i7) {
        g0.o oVar2;
        boolean z6;
        boolean z10;
        c0761q.S(1838677125);
        if (((i7 | 6) & 3) == 2 && c0761q.x()) {
            c0761q.K();
            oVar2 = oVar;
        } else {
            oVar2 = g0.l.f14684a;
            C0773y c0773y = AbstractC1692D.f18238a;
            boolean zC = AbstractC1692D.c((LayoutMode) c0761q.k(c0773y));
            g0.o oVarB = androidx.compose.foundation.layout.c.b(androidx.compose.foundation.layout.c.d(oVar2, 0.85f), 1.0f);
            C0141x c0141xA = AbstractC0139v.a(AbstractC0127i.c, g0.b.f14672x, c0761q, 0);
            int i10 = c0761q.f8304P;
            InterfaceC0744h0 interfaceC0744h0M = c0761q.m();
            g0.o oVarD = g0.a.d(c0761q, oVarB);
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
            if (c0761q.f8303O || !m.a(c0761q.G(), Integer.valueOf(i10))) {
                AbstractC1347d.l(i10, c0761q, i10, c0158h3);
            }
            C0158h c0158h4 = C0160j.c;
            C0735d.V(c0761q, oVarD, c0158h4);
            C0142y c0142y = C0142y.f1119a;
            m6.b bVar = m6.c.f19252a;
            float f10 = 2;
            AbstractC0120b.b(c0761q, androidx.compose.foundation.layout.c.e(oVar2, (bVar.d0(c0761q) * f10) + (bVar.X(c0761q) * f10) + bVar.Y(c0761q)));
            g0.o oVarA = C0142y.a(c0142y, androidx.compose.foundation.layout.c.d(oVar2, 1.0f), 1.0f);
            C0.K kE = AbstractC0133o.e(g0.b.f14666r, false);
            int i11 = c0761q.f8304P;
            InterfaceC0744h0 interfaceC0744h0M2 = c0761q.m();
            g0.o oVarD2 = g0.a.d(c0761q, oVarA);
            c0761q.U();
            if (c0761q.f8303O) {
                c0761q.l(c0159i);
            } else {
                c0761q.d0();
            }
            C0735d.V(c0761q, kE, c0158h);
            C0735d.V(c0761q, interfaceC0744h0M2, c0158h2);
            if (c0761q.f8303O || !m.a(c0761q.G(), Integer.valueOf(i11))) {
                AbstractC1347d.l(i11, c0761q, i11, c0158h3);
            }
            C0735d.V(c0761q, oVarD2, c0158h4);
            androidx.compose.foundation.layout.b bVar2 = androidx.compose.foundation.layout.b.f11474a;
            g0.o oVarM = q.m(androidx.compose.foundation.layout.c.b(androidx.compose.foundation.layout.c.d(oVar2, 1.0f), 0.5f), e.a(bVar.e0(c0761q)));
            c0761q.Q(1699928306);
            c0761q.Q(-177139394);
            List listD = AbstractC1999n.D(new m7.i(Float.valueOf(0.0f), new C1891t(l6.d.c(c0761q).f18669A)), new m7.i(Float.valueOf(1.0f), new C1891t(l6.d.c(c0761q).B)));
            c0761q.p(false);
            m7.i[] iVarArr = (m7.i[]) listD.toArray(new m7.i[0]);
            C1853C c1853cN = C1886o.n((m7.i[]) Arrays.copyOf(iVarArr, iVarArr.length));
            c0761q.p(false);
            AbstractC0133o.a(androidx.compose.foundation.a.a(oVarM, c1853cN, null, 6), c0761q, 0);
            q.a(l9.l.W(zC ? R.drawable.pick_songs : R.drawable.pick_song_portrait, c0761q, 0), null, bVar2.a(androidx.compose.foundation.layout.c.t(androidx.compose.foundation.layout.c.b(oVar2, 1.0f)), g0.b.f14667s), null, C0102i.c, 0.0f, null, c0761q, 24624, 104);
            c0761q.p(true);
            if (zC) {
                c0761q.Q(-1817042447);
                c0761q.Q(1287625160);
                float f11 = 110;
                float f12 = 160;
                float f13 = 240;
                LayoutMode layoutMode = (LayoutMode) c0761q.k(c0773y);
                if (layoutMode == LayoutMode.WIDE_ULTRA) {
                    z10 = false;
                } else {
                    if (layoutMode == LayoutMode.NARROW_NORMAL || layoutMode == LayoutMode.NARROW_ULTRA || layoutMode == LayoutMode.SQUARE_ULTRA) {
                        z10 = false;
                    } else {
                        if (layoutMode != LayoutMode.WIDE_COMPACT && layoutMode != LayoutMode.NARROW_COMPACT && layoutMode != LayoutMode.SQUARE_COMPACT && layoutMode != LayoutMode.WIDE_NORMAL && layoutMode != LayoutMode.SQUARE_NORMAL) {
                            throw new A9.k();
                        }
                        z10 = false;
                        if (!A7.k.t(c0761q, 398348334, bVar, c0761q, false)) {
                            if (!A7.k.r(c0761q, 398349423, bVar, c0761q, false)) {
                            }
                        }
                        c0761q.p(z10);
                        AbstractC0120b.b(c0761q, androidx.compose.foundation.layout.c.e(oVar2, f11));
                        c0761q.p(z10);
                    }
                    f11 = f13;
                    c0761q.p(z10);
                    AbstractC0120b.b(c0761q, androidx.compose.foundation.layout.c.e(oVar2, f11));
                    c0761q.p(z10);
                }
                f11 = f12;
                c0761q.p(z10);
                AbstractC0120b.b(c0761q, androidx.compose.foundation.layout.c.e(oVar2, f11));
                c0761q.p(z10);
            } else {
                c0761q.Q(-1816924430);
                c0761q.Q(-521917568);
                float f14 = 190;
                float f15 = 260;
                float f16 = 350;
                LayoutMode layoutMode2 = (LayoutMode) c0761q.k(c0773y);
                if (layoutMode2 == LayoutMode.WIDE_ULTRA) {
                    z6 = false;
                } else {
                    if (layoutMode2 == LayoutMode.NARROW_NORMAL || layoutMode2 == LayoutMode.NARROW_ULTRA || layoutMode2 == LayoutMode.SQUARE_ULTRA) {
                        z6 = false;
                    } else {
                        if (layoutMode2 != LayoutMode.WIDE_COMPACT && layoutMode2 != LayoutMode.NARROW_COMPACT && layoutMode2 != LayoutMode.SQUARE_COMPACT && layoutMode2 != LayoutMode.WIDE_NORMAL && layoutMode2 != LayoutMode.SQUARE_NORMAL) {
                            throw new A9.k();
                        }
                        z6 = false;
                        if (!A7.k.t(c0761q, 398348334, bVar, c0761q, false)) {
                            if (!A7.k.r(c0761q, 398349423, bVar, c0761q, false)) {
                            }
                        }
                        c0761q.p(z6);
                        AbstractC0120b.b(c0761q, androidx.compose.foundation.layout.c.e(oVar2, f14));
                        c0761q.p(z6);
                    }
                    f14 = f16;
                    c0761q.p(z6);
                    AbstractC0120b.b(c0761q, androidx.compose.foundation.layout.c.e(oVar2, f14));
                    c0761q.p(z6);
                }
                f14 = f15;
                c0761q.p(z6);
                AbstractC0120b.b(c0761q, androidx.compose.foundation.layout.c.e(oVar2, f14));
                c0761q.p(z6);
            }
            c0761q.p(true);
        }
        C0754m0 c0754m0R = c0761q.r();
        if (c0754m0R != null) {
            c0754m0R.d = new K6.a(oVar2, i7, 2);
        }
    }

    public static final long d(int i7, int i10) {
        return (((long) i10) & 4294967295L) | (((long) i7) << 32);
    }

    public static final void e(int i7, C0761q c0761q, g0.o oVar, InterfaceC2782k interfaceC2782k) {
        int i10;
        m.f(interfaceC2782k, "lyricsViewFactory");
        c0761q.S(1857022140);
        if ((i7 & 6) == 0) {
            i10 = (c0761q.h(interfaceC2782k) ? 4 : 2) | i7;
        } else {
            i10 = i7;
        }
        if ((i7 & 48) == 0) {
            i10 |= c0761q.f(oVar) ? 32 : 16;
        }
        if ((i10 & 19) == 18 && c0761q.x()) {
            c0761q.K();
        } else {
            m6.b bVar = m6.c.f19252a;
            androidx.compose.ui.viewinterop.a.a(interfaceC2782k, androidx.compose.foundation.layout.a.l(oVar, bVar.F(c0761q), bVar.H(6, c0761q)), null, c0761q, i10 & 14, 4);
        }
        C0754m0 c0754m0R = c0761q.r();
        if (c0754m0R != null) {
            c0754m0R.d = new M(interfaceC2782k, oVar, i7);
        }
    }

    public static final void f(final g0.o oVar, final boolean z6, final PlayerStatus playerStatus, final int i7, final LeadVocalState leadVocalState, final boolean z10, final InterfaceC2772a interfaceC2772a, final long j3, final boolean z11, final DriveMode driveMode, final boolean z12, final N n10, final InterfaceC2772a interfaceC2772a2, final InterfaceC2782k interfaceC2782k, final InterfaceC2772a interfaceC2772a3, final InterfaceC2782k interfaceC2782k2, final InterfaceC2772a interfaceC2772a4, final boolean z13, final InterfaceC2772a interfaceC2772a5, final boolean z14, final InterfaceC2782k interfaceC2782k3, final int i10, final boolean z15, final boolean z16, final InterfaceC2772a interfaceC2772a6, final boolean z17, final boolean z18, final boolean z19, final boolean z20, final boolean z21, C0761q c0761q, final int i11, final int i12, final int i13) {
        int i14;
        int i15;
        int i16;
        m.f(oVar, "modifier");
        m.f(playerStatus, "playerStatus");
        m.f(leadVocalState, "leadVocalState");
        m.f(interfaceC2772a, "onChangeVocal");
        m.f(driveMode, "driveMode");
        m.f(n10, "progressMs");
        m.f(interfaceC2772a2, "onCompanion");
        m.f(interfaceC2782k, "onSliderValueChange");
        m.f(interfaceC2772a3, "onSliderValueChangeFinished");
        m.f(interfaceC2782k2, "onPlayback");
        m.f(interfaceC2772a4, "onLooping");
        m.f(interfaceC2772a5, "onLike");
        m.f(interfaceC2782k3, "onGameMode");
        m.f(interfaceC2772a6, "onChangePerfectPitch");
        c0761q.S(-1079997700);
        if ((i11 & 6) == 0) {
            i14 = i11 | (c0761q.f(oVar) ? 4 : 2);
        } else {
            i14 = i11;
        }
        if ((i11 & 48) == 0) {
            i14 |= c0761q.g(z6) ? 32 : 16;
        }
        if ((i11 & 384) == 0) {
            i14 |= c0761q.f(playerStatus) ? 256 : 128;
        }
        if ((i11 & 3072) == 0) {
            i14 |= c0761q.d(i7) ? 2048 : 1024;
        }
        if ((i11 & 24576) == 0) {
            i14 |= c0761q.f(leadVocalState) ? 16384 : 8192;
        }
        if ((i11 & 196608) == 0) {
            i14 |= c0761q.g(z10) ? 131072 : 65536;
        }
        if ((i11 & 1572864) == 0) {
            i14 |= c0761q.h(interfaceC2772a) ? 1048576 : 524288;
        }
        if ((i11 & 12582912) == 0) {
            i14 |= c0761q.e(j3) ? 8388608 : 4194304;
        }
        if ((i11 & 100663296) == 0) {
            i14 |= c0761q.g(z11) ? 67108864 : 33554432;
        }
        if ((i11 & 805306368) == 0) {
            i14 |= c0761q.f(driveMode) ? 536870912 : 268435456;
        }
        int i17 = i14;
        if ((i12 & 6) == 0) {
            i15 = i12 | (c0761q.g(z12) ? 4 : 2);
        } else {
            i15 = i12;
        }
        if ((i12 & 48) == 0) {
            i15 |= c0761q.h(n10) ? 32 : 16;
        }
        if ((i12 & 384) == 0) {
            i15 |= c0761q.h(interfaceC2772a2) ? 256 : 128;
        }
        if ((i12 & 3072) == 0) {
            i15 |= c0761q.h(interfaceC2782k) ? 2048 : 1024;
        }
        if ((i12 & 24576) == 0) {
            i15 |= c0761q.h(interfaceC2772a3) ? 16384 : 8192;
        }
        if ((i12 & 196608) == 0) {
            i15 |= c0761q.h(interfaceC2782k2) ? 131072 : 65536;
        }
        if ((i12 & 1572864) == 0) {
            i15 |= c0761q.h(interfaceC2772a4) ? 1048576 : 524288;
        }
        if ((i12 & 12582912) == 0) {
            i15 |= c0761q.g(z13) ? 8388608 : 4194304;
        }
        if ((i12 & 100663296) == 0) {
            i15 |= c0761q.h(interfaceC2772a5) ? 67108864 : 33554432;
        }
        if ((i12 & 805306368) == 0) {
            i15 |= c0761q.g(z14) ? 536870912 : 268435456;
        }
        if ((i13 & 6) == 0) {
            i16 = i13 | (c0761q.h(interfaceC2782k3) ? 4 : 2);
        } else {
            i16 = i13;
        }
        if ((i13 & 48) == 0) {
            i16 |= c0761q.d(i10) ? 32 : 16;
        }
        if ((i13 & 384) == 0) {
            i16 |= c0761q.g(z15) ? 256 : 128;
        }
        if ((i13 & 3072) == 0) {
            i16 |= c0761q.g(z16) ? 2048 : 1024;
        }
        if ((i13 & 24576) == 0) {
            i16 |= c0761q.h(interfaceC2772a6) ? 16384 : 8192;
        }
        if ((196608 & i13) == 0) {
            i16 |= c0761q.g(z17) ? 131072 : 65536;
        }
        if ((i13 & 1572864) == 0) {
            i16 |= c0761q.g(z18) ? 1048576 : 524288;
        }
        if ((i13 & 12582912) == 0) {
            i16 |= c0761q.g(z19) ? 8388608 : 4194304;
        }
        if ((i13 & 100663296) == 0) {
            i16 |= c0761q.g(z20) ? 67108864 : 33554432;
        }
        if ((i13 & 805306368) == 0) {
            i16 |= c0761q.g(z21) ? 536870912 : 268435456;
        }
        if ((i17 & 306783379) == 306783378 && (i15 & 306783379) == 306783378 && (i16 & 306783379) == 306783378 && c0761q.x()) {
            c0761q.K();
        } else {
            androidx.compose.animation.a.c(z11, oVar, AbstractC2565A.b(null, 3), AbstractC2565A.c(null, 3), null, b0.g.b(1253730260, new C0875o(FontSize.Body1, z15, z18, driveMode, z12, interfaceC2772a2, interfaceC2772a, z10, leadVocalState, z16, interfaceC2772a6, z20, z21, i10, interfaceC2782k3, n10, j3, z14, z19, interfaceC2782k, interfaceC2772a3, z6, z17, i7, playerStatus, z13, interfaceC2782k2, interfaceC2772a4, interfaceC2772a5), c0761q), c0761q, ((i17 >> 24) & 14) | 200064 | ((i17 << 3) & 112), 16);
        }
        C0754m0 c0754m0R = c0761q.r();
        if (c0754m0R != null) {
            c0754m0R.d = new InterfaceC2785n() { // from class: X5.m
                @Override // z7.InterfaceC2785n
                public final Object invoke(Object obj, Object obj2) {
                    ((Integer) obj2).getClass();
                    int iZ = C0735d.Z(i11 | 1);
                    int iZ2 = C0735d.Z(i12);
                    int iZ3 = C0735d.Z(i13);
                    boolean z22 = z20;
                    boolean z23 = z21;
                    AbstractC2673a.f(oVar, z6, playerStatus, i7, leadVocalState, z10, interfaceC2772a, j3, z11, driveMode, z12, n10, interfaceC2772a2, interfaceC2782k, interfaceC2772a3, interfaceC2782k2, interfaceC2772a4, z13, interfaceC2772a5, z14, interfaceC2782k3, i10, z15, z16, interfaceC2772a6, z17, z18, z19, z22, z23, (C0761q) obj, iZ, iZ2, iZ3);
                    return m7.q.f19267a;
                }
            };
        }
    }

    public static final void g(final g0.o oVar, final boolean z6, final InterfaceC2772a interfaceC2772a, final InterfaceC2772a interfaceC2772a2, final boolean z10, final LeadVocalState leadVocalState, final boolean z11, final boolean z12, final InterfaceC2772a interfaceC2772a3, final boolean z13, final boolean z14, final FontSize fontSize, C0761q c0761q, final int i7, final int i10) {
        int i11;
        int i12;
        m.f(interfaceC2772a, "onPassengerClick");
        m.f(interfaceC2772a2, "onChangeVocal");
        m.f(leadVocalState, "leadVocalState");
        m.f(interfaceC2772a3, "onChangePerfectPitch");
        m.f(fontSize, "fontSize");
        c0761q.S(1422901007);
        if ((i7 & 6) == 0) {
            i11 = (c0761q.f(oVar) ? 4 : 2) | i7;
        } else {
            i11 = i7;
        }
        if ((i7 & 48) == 0) {
            i11 |= c0761q.g(z6) ? 32 : 16;
        }
        if ((i7 & 384) == 0) {
            i11 |= c0761q.h(interfaceC2772a) ? 256 : 128;
        }
        if ((i7 & 3072) == 0) {
            i11 |= c0761q.h(interfaceC2772a2) ? 2048 : 1024;
        }
        if ((i7 & 24576) == 0) {
            i11 |= c0761q.g(z10) ? 16384 : 8192;
        }
        if ((196608 & i7) == 0) {
            i11 |= c0761q.f(leadVocalState) ? 131072 : 65536;
        }
        if ((1572864 & i7) == 0) {
            i11 |= c0761q.g(z11) ? 1048576 : 524288;
        }
        if ((i7 & 12582912) == 0) {
            i11 |= c0761q.g(z12) ? 8388608 : 4194304;
        }
        if ((i7 & 100663296) == 0) {
            i11 |= c0761q.h(interfaceC2772a3) ? 67108864 : 33554432;
        }
        if ((i7 & 805306368) == 0) {
            i11 |= c0761q.g(z13) ? 536870912 : 268435456;
        }
        if ((i10 & 6) == 0) {
            i12 = i10 | (c0761q.g(z14) ? 4 : 2);
        } else {
            i12 = i10;
        }
        if ((i10 & 48) == 0) {
            i12 |= c0761q.f(fontSize) ? 32 : 16;
        }
        if ((306783379 & i11) == 306783378 && (i12 & 19) == 18 && c0761q.x()) {
            c0761q.K();
        } else {
            S sB = P.b(AbstractC0127i.f1076a, g0.b.f14671w, c0761q, 48);
            int i13 = c0761q.f8304P;
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
            if (c0761q.f8303O || !m.a(c0761q.G(), Integer.valueOf(i13))) {
                AbstractC1347d.l(i13, c0761q, i13, c0158h);
            }
            C0735d.V(c0761q, oVarD, C0160j.c);
            g0.l lVar = g0.l.f14684a;
            int i14 = i11 >> 3;
            T5.h.a(lVar, z6, interfaceC2772a2, z10, leadVocalState, z11, z12, interfaceC2772a3, z13, true, fontSize, c0761q, (i11 & 112) | 805306374 | (i14 & 896) | (i14 & 7168) | (i14 & 57344) | (458752 & i14) | (3670016 & i14) | (29360128 & i14) | (i14 & 234881024), (i12 >> 3) & 14);
            AbstractC0120b.b(c0761q, T.b(lVar, 1.0f));
            int i15 = ((i11 >> 24) & 112) | 6 | (i11 & 896);
            int i16 = i12 << 9;
            w0.c.x(lVar, z13, interfaceC2772a, z14, fontSize, c0761q, i15 | (i16 & 7168) | (i16 & 57344));
            c0761q.p(true);
        }
        C0754m0 c0754m0R = c0761q.r();
        if (c0754m0R != null) {
            c0754m0R.d = new InterfaceC2785n() { // from class: T5.i
                @Override // z7.InterfaceC2785n
                public final Object invoke(Object obj, Object obj2) {
                    ((Integer) obj2).getClass();
                    int iZ = C0735d.Z(i7 | 1);
                    int iZ2 = C0735d.Z(i10);
                    boolean z15 = z14;
                    FontSize fontSize2 = fontSize;
                    AbstractC2673a.g(oVar, z6, interfaceC2772a, interfaceC2772a2, z10, leadVocalState, z11, z12, interfaceC2772a3, z13, z15, fontSize2, (C0761q) obj, iZ, iZ2);
                    return m7.q.f19267a;
                }
            };
        }
    }

    public static final void h(int i7, C0761q c0761q, g0.o oVar, boolean z6) {
        int i10;
        m.f(oVar, "modifier");
        c0761q.S(352708571);
        if ((i7 & 6) == 0) {
            i10 = (c0761q.g(z6) ? 4 : 2) | i7;
        } else {
            i10 = i7;
        }
        if ((i7 & 48) == 0) {
            i10 |= c0761q.f(oVar) ? 32 : 16;
        }
        if ((i10 & 19) == 18 && c0761q.x()) {
            c0761q.K();
        } else {
            androidx.compose.animation.a.c(z6, oVar, AbstractC2565A.b(null, 3), AbstractC2565A.c(null, 3), null, H.f6371a, c0761q, (i10 & 14) | 200064 | (i10 & 112), 16);
        }
        C0754m0 c0754m0R = c0761q.r();
        if (c0754m0R != null) {
            c0754m0R.d = new Y0(z6, oVar, i7);
        }
    }

    public static final void i(g0.o oVar, InterfaceC2772a interfaceC2772a, long j3, b0.b bVar, C0761q c0761q, int i7) {
        int i10;
        c0761q.S(1023040631);
        if ((i7 & 6) == 0) {
            i10 = (c0761q.f(oVar) ? 4 : 2) | i7;
        } else {
            i10 = i7;
        }
        if ((i7 & 48) == 0) {
            i10 |= c0761q.h(interfaceC2772a) ? 32 : 16;
        }
        if ((i7 & 384) == 0) {
            i10 |= c0761q.e(j3) ? 256 : 128;
        }
        if ((i7 & 3072) == 0) {
            i10 |= c0761q.h(bVar) ? 2048 : 1024;
        }
        if ((i10 & 1171) == 1170 && c0761q.x()) {
            c0761q.K();
        } else {
            e1.m.b(null, j3, interfaceC2772a, null, b0.g.b(1699129012, new u(oVar, 2, bVar), c0761q), c0761q, ((i10 << 3) & 896) | ((i10 >> 3) & 112) | 24576);
        }
        C0754m0 c0754m0R = c0761q.r();
        if (c0754m0R != null) {
            c0754m0R.d = new K0(oVar, interfaceC2772a, j3, bVar, i7, 2);
        }
    }

    public static final void j(g0.o oVar, String str, String str2, String str3, C1966G c1966g, boolean z6, String str4, InterfaceC2772a interfaceC2772a, InterfaceC2772a interfaceC2772a2, boolean z10, InterfaceC2772a interfaceC2772a3, InterfaceC2782k interfaceC2782k, InterfaceC2782k interfaceC2782k2, C0761q c0761q, int i7, int i10) {
        int i11;
        g0.o oVar2;
        m.f(str4, "encouragementMessage");
        m.f(interfaceC2772a, "onRetry");
        m.f(interfaceC2772a2, "onDismiss");
        m.f(interfaceC2772a3, "onAnimationEnd");
        m.f(interfaceC2782k, "playSoundFx");
        m.f(interfaceC2782k2, "stopSoundFx");
        c0761q.S(1130411675);
        int i12 = i7 | 6;
        if ((i7 & 48) == 0) {
            i12 |= c0761q.f(str) ? 32 : 16;
        }
        if ((i7 & 384) == 0) {
            i12 |= c0761q.f(str2) ? 256 : 128;
        }
        if ((i7 & 3072) == 0) {
            i12 |= c0761q.f(str3) ? 2048 : 1024;
        }
        if ((i7 & 24576) == 0) {
            i12 |= (32768 & i7) == 0 ? c0761q.f(c1966g) : c0761q.h(c1966g) ? 16384 : 8192;
        }
        if ((196608 & i7) == 0) {
            i12 |= c0761q.g(z6) ? 131072 : 65536;
        }
        if ((i7 & 1572864) == 0) {
            i12 |= c0761q.f(str4) ? 1048576 : 524288;
        }
        if ((i7 & 12582912) == 0) {
            i12 |= c0761q.h(interfaceC2772a) ? 8388608 : 4194304;
        }
        if ((i7 & 100663296) == 0) {
            i12 |= c0761q.h(interfaceC2772a2) ? 67108864 : 33554432;
        }
        if ((i7 & 805306368) == 0) {
            i12 |= c0761q.g(z10) ? 536870912 : 268435456;
        }
        if ((i10 & 6) == 0) {
            i11 = i10 | (c0761q.h(interfaceC2772a3) ? 4 : 2);
        } else {
            i11 = i10;
        }
        if ((i10 & 48) == 0) {
            i11 |= c0761q.h(interfaceC2782k) ? 32 : 16;
        }
        if ((i10 & 384) == 0) {
            i11 |= c0761q.h(interfaceC2782k2) ? 256 : 128;
        }
        int i13 = i11;
        if ((i12 & 306783379) == 306783378 && (i13 & 147) == 146 && c0761q.x()) {
            c0761q.K();
            oVar2 = oVar;
        } else {
            g0.l lVar = g0.l.f14684a;
            c0761q.Q(1720224443);
            Object objG = c0761q.G();
            T.S s6 = C0753m.f8270a;
            if (objG == s6) {
                objG = C0735d.O(null, T.S.f8224q);
                c0761q.a0(objG);
            }
            X x8 = (X) objG;
            c0761q.p(false);
            m7.q qVar = m7.q.f19267a;
            c0761q.Q(1720226723);
            boolean z11 = (i13 & 112) == 32;
            Object objG2 = c0761q.G();
            if (z11 || objG2 == s6) {
                objG2 = new S5.q(interfaceC2782k, x8, null);
                c0761q.a0(objG2);
            }
            c0761q.p(false);
            C0735d.f(c0761q, qVar, (InterfaceC2785n) objG2);
            c0761q.Q(1720234150);
            boolean z12 = (i13 & 896) == 256;
            Object objG3 = c0761q.G();
            if (z12 || objG3 == s6) {
                objG3 = new O(interfaceC2782k2, 3, x8);
                c0761q.a0(objG3);
            }
            c0761q.p(false);
            C0735d.d(qVar, (InterfaceC2782k) objG3, c0761q);
            l6.d.b(true, false, b0.g.b(95190727, new S5.r(lVar, str, str2, str3, c1966g, z6, str4, interfaceC2772a, interfaceC2772a2, z10, interfaceC2772a3, interfaceC2782k, interfaceC2782k2), c0761q), c0761q, 390, 2);
            oVar2 = lVar;
        }
        C0754m0 c0754m0R = c0761q.r();
        if (c0754m0R != null) {
            c0754m0R.d = new S5.p(oVar2, str, str2, str3, c1966g, z6, str4, interfaceC2772a, interfaceC2772a2, z10, interfaceC2772a3, interfaceC2782k, interfaceC2782k2, i7, i10, 0);
        }
    }

    public static final void k(String str, String str2, C0761q c0761q, int i7) {
        int i10;
        m.f(str2, "value");
        c0761q.S(-2047587624);
        if ((i7 & 6) == 0) {
            i10 = (c0761q.f(str) ? 4 : 2) | i7;
        } else {
            i10 = i7;
        }
        if ((i7 & 48) == 0) {
            i10 |= c0761q.f(str2) ? 32 : 16;
        }
        int i11 = i10;
        if ((i11 & 19) == 18 && c0761q.x()) {
            c0761q.K();
        } else {
            g0.l lVar = g0.l.f14684a;
            g0.o oVarK = androidx.compose.foundation.layout.a.k(h6.i.c(androidx.compose.foundation.layout.c.o(lVar, 300), 2, l6.d.c(c0761q).f18702f, AbstractC1861K.f19381a), m6.c.f19252a.F(c0761q));
            S sB = P.b(AbstractC0127i.f1076a, g0.b.f14669u, c0761q, 0);
            int i12 = c0761q.f8304P;
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
            if (c0761q.f8303O || !m.a(c0761q.G(), Integer.valueOf(i12))) {
                AbstractC1347d.l(i12, c0761q, i12, c0158h3);
            }
            C0158h c0158h4 = C0160j.c;
            C0735d.V(c0761q, oVarD, c0158h4);
            g0.o oVarB = T.b(lVar, 1.0f);
            C0141x c0141xA = AbstractC0139v.a(AbstractC0127i.c, g0.b.f14672x, c0761q, 0);
            int i13 = c0761q.f8304P;
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
            if (c0761q.f8303O || !m.a(c0761q.G(), Integer.valueOf(i13))) {
                AbstractC1347d.l(i13, c0761q, i13, c0158h3);
            }
            C0735d.V(c0761q, oVarD2, c0158h4);
            c1.b(str, null, l6.d.c(c0761q).f18696b, 0L, null, null, null, 0L, null, null, 0L, 0, false, 0, 0, null, new N0.L(0L, AbstractC0904a.I(20), A.f7803v, null, null, 0L, 0, 0L, null, 16777209), c0761q, i11 & 14, 1572864, 65530);
            c1.b(str2, null, l6.d.c(c0761q).f18696b, 0L, null, null, null, 0L, null, null, 0L, 0, false, 0, 0, null, new N0.L(C1891t.c, AbstractC0904a.I(16), null, null, null, 0L, 0, 0L, null, 16777212), c0761q, (i11 >> 3) & 14, 0, 65530);
            c0761q.p(true);
            c0761q.p(true);
        }
        C0754m0 c0754m0R = c0761q.r();
        if (c0754m0R != null) {
            c0754m0R.d = new M(str, str2, i7, 4);
        }
    }

    public static final void l(final g0.o oVar, final List list, final int i7, final C0223q c0223q, final FontSize fontSize, C0761q c0761q, final int i10) {
        int i11;
        m.f(list, "items");
        m.f(c0223q, "onSelectFilter");
        m.f(fontSize, "fontSize");
        c0761q.S(-462803122);
        if ((i10 & 6) == 0) {
            i11 = (c0761q.f(oVar) ? 4 : 2) | i10;
        } else {
            i11 = i10;
        }
        if ((i10 & 48) == 0) {
            i11 |= c0761q.h(list) ? 32 : 16;
        }
        if ((i10 & 384) == 0) {
            i11 |= c0761q.d(i7) ? 256 : 128;
        }
        if ((i10 & 3072) == 0) {
            i11 |= c0761q.h(c0223q) ? 2048 : 1024;
        }
        if ((i10 & 24576) == 0) {
            i11 |= c0761q.f(fontSize) ? 16384 : 8192;
        }
        if ((i11 & 9363) == 9362 && c0761q.x()) {
            c0761q.K();
        } else {
            ArrayList arrayList = new ArrayList(AbstractC2000o.J(list, 10));
            Iterator it = list.iterator();
            while (it.hasNext()) {
                String str = ((KaraokeItemSortTypeInfo) it.next()).f13133b;
                m.c(str);
                arrayList.add(str);
            }
            c0761q.Q(1172124233);
            boolean zH = ((i11 & 7168) == 2048) | c0761q.h(list);
            Object objG = c0761q.G();
            if (zH || objG == C0753m.f8270a) {
                objG = new O(c0223q, 0, list);
                c0761q.a0(objG);
            }
            c0761q.p(false);
            b(oVar, arrayList, i7, fontSize, false, (InterfaceC2782k) objG, c0761q, (i11 & 910) | ((i11 >> 3) & 7168));
        }
        C0754m0 c0754m0R = c0761q.r();
        if (c0754m0R != null) {
            c0754m0R.d = new InterfaceC2785n() { // from class: Q5.P
                @Override // z7.InterfaceC2785n
                public final Object invoke(Object obj, Object obj2) {
                    ((Integer) obj2).getClass();
                    int iZ = C0735d.Z(i10 | 1);
                    C0223q c0223q2 = c0223q;
                    FontSize fontSize2 = fontSize;
                    AbstractC2673a.l(oVar, list, i7, c0223q2, fontSize2, (C0761q) obj, iZ);
                    return m7.q.f19267a;
                }
            };
        }
    }

    public static final void m(g0.o oVar, LoadingInterface$LoadingState loadingInterface$LoadingState, InterfaceC2772a interfaceC2772a, L l, C0761q c0761q, int i7) {
        g0.o oVar2;
        c0761q.S(2056365346);
        int i10 = i7 | 6;
        if ((i7 & 48) == 0) {
            i10 |= c0761q.f(loadingInterface$LoadingState) ? 32 : 16;
        }
        if ((i7 & 384) == 0) {
            i10 |= c0761q.h(interfaceC2772a) ? 256 : 128;
        }
        if ((i7 & 3072) == 0) {
            i10 |= c0761q.f(l) ? 2048 : 1024;
        }
        if ((i10 & 1171) == 1170 && c0761q.x()) {
            c0761q.K();
            oVar2 = oVar;
        } else {
            oVar2 = g0.l.f14684a;
            g0.o oVarJ = androidx.compose.foundation.layout.a.j(androidx.compose.foundation.a.b(androidx.compose.foundation.layout.c.c(oVar2, 1.0f), l6.d.c(c0761q).f18704g, AbstractC1861K.f19381a), l);
            c0761q.Q(-1003410150);
            c0761q.Q(212064437);
            c0761q.p(false);
            a1.b bVar = (a1.b) c0761q.k(AbstractC0227s0.f2259f);
            Object objG = c0761q.G();
            Object obj = C0753m.f8270a;
            if (objG == obj) {
                objG = new g1.p(bVar);
                c0761q.a0(objG);
            }
            g1.p pVar = (g1.p) objG;
            Object objG2 = c0761q.G();
            if (objG2 == obj) {
                objG2 = new C1283j();
                c0761q.a0(objG2);
            }
            C1283j c1283j = (C1283j) objG2;
            Object objG3 = c0761q.G();
            if (objG3 == obj) {
                objG3 = C0735d.O(Boolean.FALSE, T.S.f8224q);
                c0761q.a0(objG3);
            }
            X x8 = (X) objG3;
            Object objG4 = c0761q.G();
            if (objG4 == obj) {
                objG4 = new C1285l(c1283j);
                c0761q.a0(objG4);
            }
            C1285l c1285l = (C1285l) objG4;
            Object objG5 = c0761q.G();
            if (objG5 == obj) {
                objG5 = C0735d.O(m7.q.f19267a, T.S.f8222n);
                c0761q.a0(objG5);
            }
            X x9 = (X) objG5;
            boolean zH = c0761q.h(pVar) | c0761q.d(257);
            Object objG6 = c0761q.G();
            if (zH || objG6 == obj) {
                Object m12 = new M1(x9, pVar, c1285l, x8, 2);
                c0761q.a0(m12);
                objG6 = m12;
            }
            C0.K k10 = (C0.K) objG6;
            Object objG7 = c0761q.G();
            if (objG7 == obj) {
                objG7 = new N1(x8, c1285l, 2);
                c0761q.a0(objG7);
            }
            InterfaceC2772a interfaceC2772a2 = (InterfaceC2772a) objG7;
            boolean zH2 = c0761q.h(pVar);
            Object objG8 = c0761q.G();
            if (zH2 || objG8 == obj) {
                objG8 = new O1(pVar, 2);
                c0761q.a0(objG8);
            }
            c0.a(L0.k.a(oVarJ, false, (InterfaceC2782k) objG8), b0.g.b(1200550679, new C0619s2(x9, c1283j, interfaceC2772a2, loadingInterface$LoadingState, interfaceC2772a), c0761q), k10, c0761q, 48);
            c0761q.p(false);
        }
        C0754m0 c0754m0R = c0761q.r();
        if (c0754m0R != null) {
            c0754m0R.d = new G6.c(oVar2, loadingInterface$LoadingState, interfaceC2772a, l, i7, 1);
        }
    }

    public static final void n(g0.o oVar, Z5.c cVar, C0761q c0761q, int i7) {
        m.f(cVar, "viewModel");
        c0761q.S(2107642968);
        int i10 = i7 | 6;
        if ((i7 & 48) == 0) {
            i10 |= c0761q.h(cVar) ? 32 : 16;
        }
        if ((i10 & 19) == 18 && c0761q.x()) {
            c0761q.K();
        } else {
            oVar = g0.l.f14684a;
            AbstractC0904a.l(oVar, new C0607p1(cVar, null, false, null, 222), b0.g.b(-941232362, new y(cVar, 14), c0761q), c0761q, (i10 & 14) | 384, 0);
        }
        C0754m0 c0754m0R = c0761q.r();
        if (c0754m0R != null) {
            c0754m0R.d = new M(oVar, cVar, i7, 3);
        }
    }

    public static List o(int... iArr) {
        return iArr.length == 0 ? Collections.emptyList() : new C1301b(0, iArr.length, iArr);
    }

    public static final void p(boolean z6, Number number) {
        if (z6) {
            return;
        }
        throw new IllegalArgumentException("Step must be positive, was: " + number + '.');
    }

    public static int q(long j3) {
        int i7 = (int) j3;
        n.o(j3, "Out of range: %s", ((long) i7) == j3);
        return i7;
    }

    public static float r(float f10, float f11) {
        return f10 < f11 ? f11 : f10;
    }

    public static float s(float f10, float f11) {
        return f10 > f11 ? f11 : f10;
    }

    public static double t(double d, double d10, double d11) {
        if (d10 <= d11) {
            return d < d10 ? d10 : d > d11 ? d11 : d;
        }
        throw new IllegalArgumentException("Cannot coerce value to an empty range: maximum " + d11 + " is less than minimum " + d10 + '.');
    }

    public static float u(float f10, float f11, float f12) {
        if (f11 <= f12) {
            return f10 < f11 ? f11 : f10 > f12 ? f12 : f10;
        }
        throw new IllegalArgumentException("Cannot coerce value to an empty range: maximum " + f12 + " is less than minimum " + f11 + '.');
    }

    public static int v(int i7, int i10, int i11) {
        if (i10 <= i11) {
            return i7 < i10 ? i10 : i7 > i11 ? i11 : i7;
        }
        throw new IllegalArgumentException("Cannot coerce value to an empty range: maximum " + i11 + " is less than minimum " + i10 + '.');
    }

    public static long w(long j3, long j10, long j11) {
        if (j10 <= j11) {
            return j3 < j10 ? j10 : j3 > j11 ? j11 : j3;
        }
        throw new IllegalArgumentException("Cannot coerce value to an empty range: maximum " + j11 + " is less than minimum " + j10 + '.');
    }

    public static Comparable x(Comparable comparable, G7.a aVar) {
        m.f(aVar, "range");
        if (aVar.a()) {
            throw new IllegalArgumentException("Cannot coerce value to an empty range: " + aVar + '.');
        }
        float f10 = aVar.f2710a;
        if (G7.a.b(comparable, Float.valueOf(f10)) && !G7.a.b(Float.valueOf(f10), comparable)) {
            return Float.valueOf(f10);
        }
        float f11 = aVar.f2711b;
        return (!G7.a.b(Float.valueOf(f11), comparable) || G7.a.b(comparable, Float.valueOf(f11))) ? comparable : Float.valueOf(f11);
    }

    public static Comparable y(Integer num, Integer num2, Integer num3) {
        if (num2 == null || num3 == null) {
            if (num2 != null && num.compareTo(num2) < 0) {
                return num2;
            }
            if (num3 != null && num.compareTo(num3) > 0) {
                return num3;
            }
        } else {
            if (num2.compareTo(num3) > 0) {
                throw new IllegalArgumentException("Cannot coerce value to an empty range: maximum " + num3 + " is less than minimum " + num2 + '.');
            }
            if (num.compareTo(num2) < 0) {
                return num2;
            }
            if (num.compareTo(num3) > 0) {
                return num3;
            }
        }
        return num;
    }

    public static final double z(int i7, int i10, int i11, int i12, f fVar) {
        double d = ((double) i11) / ((double) i7);
        double d10 = ((double) i12) / ((double) i10);
        int iOrdinal = fVar.ordinal();
        if (iOrdinal == 0) {
            return Math.max(d, d10);
        }
        if (iOrdinal == 1) {
            return Math.min(d, d10);
        }
        throw new A9.k();
    }
}
