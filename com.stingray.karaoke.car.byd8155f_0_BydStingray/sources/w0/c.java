package w0;

import A7.m;
import B2.O;
import C0.C0102i;
import C0.K;
import D.AbstractC0127i;
import D.AbstractC0133o;
import D.AbstractC0139v;
import D.C0119a;
import D.C0124f;
import D.C0141x;
import D.L;
import D.P;
import E0.C0158h;
import E0.C0159i;
import E0.C0160j;
import E0.InterfaceC0161k;
import F0.AbstractC0227s0;
import F0.C0223q;
import F0.D0;
import F8.A;
import F8.AbstractC0287w;
import F8.Y;
import G6.i;
import G6.j;
import G6.k;
import H7.H;
import H7.InterfaceC0321f;
import K7.A0;
import K7.r0;
import N0.AbstractC0412g;
import N0.AbstractC0418m;
import N0.C0409d;
import N0.C0411f;
import P8.p;
import Q.AbstractC0535v;
import Q.c1;
import Q5.C0545a;
import Q5.C0569g;
import Q5.C0595m1;
import Q5.C0599n1;
import Q5.C0607p1;
import Q5.C0612q2;
import Q5.C0616s;
import Q5.C0634w1;
import Q5.C0641y0;
import Q5.K0;
import Q5.L0;
import Q5.M;
import Q5.M0;
import Q5.N0;
import Q5.T0;
import Q5.X0;
import Q7.AbstractC0662o;
import Q7.InterfaceC0650c;
import Q7.InterfaceC0652e;
import Q7.InterfaceC0654g;
import Q7.InterfaceC0656i;
import Q7.InterfaceC0657j;
import Q7.InterfaceC0666t;
import Q7.Q;
import Q7.U;
import Q8.h;
import R7.f;
import S0.I;
import T.C0735d;
import T.C0738e0;
import T.C0753m;
import T.C0754m0;
import T.C0761q;
import T.InterfaceC0744h0;
import T.Q0;
import T.R0;
import T.S;
import T.X;
import T7.u;
import V8.N;
import V8.f0;
import X5.AbstractC0851c;
import X5.C0869l;
import a.AbstractC0904a;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkCapabilities;
import android.os.Bundle;
import android.os.IBinder;
import android.os.Parcelable;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.text.style.AbsoluteSizeSpan;
import android.text.style.ForegroundColorSpan;
import android.text.style.RelativeSizeSpan;
import android.util.Size;
import android.util.SizeF;
import android.view.KeyEvent;
import android.widget.TextView;
import androidx.compose.ui.platform.AndroidCompositionLocals_androidKt;
import androidx.lifecycle.InterfaceC0977o;
import androidx.lifecycle.h0;
import androidx.lifecycle.l0;
import b2.C0991a;
import b6.g;
import c9.w;
import com.stingray.drivemodedetector.R;
import com.stingray.karaoke.car.ui.viewmodel.base.LoadingInterface$LoadingState;
import com.stingray.karaoke.car.utils.FontSize;
import com.stingray.karaoke.car.utils.LayoutMode;
import g0.l;
import g0.o;
import g1.AbstractC1274a;
import h.n;
import h2.AbstractC1347d;
import h6.r;
import h8.AbstractC1386c;
import i6.C1415d;
import java.io.IOException;
import java.io.Serializable;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.NoSuchElementException;
import java.util.Set;
import java.util.TreeSet;
import k6.AbstractC1692D;
import k6.C1696d;
import m7.q;
import n0.AbstractC1861K;
import n0.C1860J;
import n0.C1863M;
import n0.C1891t;
import n7.AbstractC1998m;
import n7.AbstractC1999n;
import n7.AbstractC2000o;
import n7.x;
import o8.C2061f;
import r9.C2319i;
import r9.C2322l;
import r9.D;
import v8.AbstractC2564d;
import w.AbstractC2565A;
import x.AbstractC2631e;
import x.AbstractC2637h;
import x3.AbstractC2673a;
import z7.InterfaceC2772a;
import z7.InterfaceC2782k;
import z7.InterfaceC2785n;

/* JADX INFO: loaded from: classes.dex */
public abstract class c {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static final /* synthetic */ int f23166a = 0;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public static final /* synthetic */ int f23167b = 0;
    public static final /* synthetic */ int c = 0;

    public static final void A(o oVar, Integer num, ArrayList arrayList, boolean z6, InterfaceC2772a interfaceC2772a, InterfaceC2785n interfaceC2785n, g gVar, Integer num2, C0612q2 c0612q2, L l, C0761q c0761q, int i7) {
        l lVar;
        o oVar2;
        boolean z10;
        float fJ;
        c0761q.S(-791466025);
        int i10 = i7 | 6;
        if ((i7 & 48) == 0) {
            i10 |= c0761q.f(num) ? 32 : 16;
        }
        if ((i7 & 384) == 0) {
            i10 |= c0761q.h(arrayList) ? 256 : 128;
        }
        if ((i7 & 3072) == 0) {
            i10 |= c0761q.g(z6) ? 2048 : 1024;
        }
        if ((i7 & 24576) == 0) {
            i10 |= c0761q.h(interfaceC2772a) ? 16384 : 8192;
        }
        if ((196608 & i7) == 0) {
            i10 |= c0761q.h(interfaceC2785n) ? 131072 : 65536;
        }
        if ((i7 & 1572864) == 0) {
            i10 |= c0761q.h(gVar) ? 1048576 : 524288;
        }
        if ((12582912 & i7) == 0) {
            i10 |= c0761q.f(num2) ? 8388608 : 4194304;
        }
        if ((100663296 & i7) == 0) {
            i10 |= c0761q.f(c0612q2) ? 67108864 : 33554432;
        }
        if ((805306368 & i7) == 0) {
            i10 |= c0761q.f(l) ? 536870912 : 268435456;
        }
        if ((306783379 & i10) == 306783378 && c0761q.x()) {
            c0761q.K();
            oVar2 = oVar;
        } else {
            l lVar2 = l.f14684a;
            if (arrayList.isEmpty()) {
                c0761q.Q(-148467835);
                float fK = AbstractC2673a.K(l, c0761q);
                float fH = AbstractC2673a.H(l, c0761q);
                float fI = AbstractC2673a.I(l, c0761q);
                c0761q.Q(-2082991691);
                if (c0761q.k(AbstractC1692D.f18238a) == LayoutMode.WIDE_ULTRA) {
                    z10 = false;
                    fJ = 0;
                } else {
                    z10 = false;
                    fJ = AbstractC2673a.J(l, c0761q);
                }
                c0761q.p(z10);
                o oVarN = androidx.compose.foundation.layout.a.n(lVar2, fJ, fK, fI, fH);
                String str = c0612q2.f7032a;
                if (str == null) {
                    str = "";
                }
                String str2 = c0612q2.f7033b;
                if (str2 == null) {
                    str2 = "";
                }
                String str3 = c0612q2.c;
                if (str3 == null) {
                    str3 = "";
                }
                lVar = lVar2;
                C7.a.b(oVarN, str, str2, str3, c0612q2.d, c0761q, 0, 0);
                c0761q.p(false);
            } else {
                lVar = lVar2;
                c0761q.Q(-147804869);
                int i11 = 1572864 | (i10 & 14) | (i10 & 112) | (i10 & 896) | (i10 & 7168) | (57344 & i10) | ((i10 >> 12) & 458752);
                int i12 = i10 << 6;
                com.bumptech.glide.c.B(lVar, num, arrayList, z6, interfaceC2772a, l, true, interfaceC2785n, gVar, num2, c0761q, i11 | (29360128 & i12) | (234881024 & i12) | (i12 & 1879048192), 0);
                c0761q.p(false);
            }
            oVar2 = lVar;
        }
        C0754m0 c0754m0R = c0761q.r();
        if (c0754m0R != null) {
            c0754m0R.d = new C0634w1(oVar2, num, arrayList, z6, interfaceC2772a, interfaceC2785n, gVar, num2, c0612q2, l, i7);
        }
    }

    public static final void B(C1415d c1415d, C0761q c0761q, int i7) {
        int i10;
        String str;
        c0761q.S(2101591694);
        if ((i7 & 6) == 0) {
            i10 = (c0761q.h(c1415d) ? 4 : 2) | i7;
        } else {
            i10 = i7;
        }
        if ((i10 & 3) == 2 && c0761q.x()) {
            c0761q.K();
        } else {
            boolean zBooleanValue = ((Boolean) c0761q.k(AbstractC1692D.f18239b)).booleanValue();
            c0761q.Q(-742743901);
            C0738e0 c0738e0 = c1415d.f15763K;
            if (h.M0((String) c0738e0.getValue())) {
                str = "";
            } else {
                String str2 = (String) c0738e0.getValue();
                str = str2 + " " + AbstractC2673a.X(m.a((String) c0738e0.getValue(), "1") ? R.string.Global_Song : R.string.Global_Songs, c0761q);
            }
            String str3 = str;
            c0761q.p(false);
            N n10 = c1415d.f12274x;
            X xR = com.bumptech.glide.c.R(n10, c0761q, 0);
            F6.b bVarD = W5.e.d(c1415d.f12234b.b(c1415d.d));
            C0607p1 c0607p1 = new C0607p1(c1415d, !((Boolean) com.bumptech.glide.c.R(c1415d.f12269F, c0761q, 0).getValue()).booleanValue(), str3, false, true, false, null);
            C0599n1 c0599n1 = null;
            c0607p1.o = ((Boolean) c1415d.f15767O.getValue()).booleanValue() ? new C0595m1(c1415d, zBooleanValue, bVarD) : null;
            if (((Boolean) c1415d.f15768P.getValue()).booleanValue() && !((Collection) ((f0) n10.l).getValue()).isEmpty()) {
                C0738e0 c0738e02 = c1415d.f15769Q;
                if (((Integer) c0738e02.getValue()) != null && ((Integer) c1415d.f15770R.getValue()) != null && ((Integer) c0738e02.getValue()) != null) {
                    c0599n1 = new C0599n1(c1415d, 0);
                }
            }
            c0607p1.f7019k = c0599n1;
            AbstractC0904a.l(null, c0607p1, b0.g.b(123553356, new G6.e(c1415d, 3, xR), c0761q), c0761q, 384, 1);
        }
        C0754m0 c0754m0R = c0761q.r();
        if (c0754m0R != null) {
            c0754m0R.d = new C0545a(i7, 9, c1415d);
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r13v2 */
    /* JADX WARN: Type inference failed for: r13v3, types: [boolean, int] */
    /* JADX WARN: Type inference failed for: r13v4 */
    public static final void C(o oVar, String str, int i7, C0761q c0761q, int i10) {
        int i11;
        int i12;
        int i13;
        X x8;
        int i14;
        ?? r13;
        X x9;
        int i15;
        b0.b bVar = AbstractC0851c.f10229a;
        m.f(oVar, "modifier");
        c0761q.S(-106537930);
        if ((i10 & 6) == 0) {
            i11 = (c0761q.f(oVar) ? 4 : 2) | i10;
        } else {
            i11 = i10;
        }
        if ((i10 & 48) == 0) {
            i11 |= c0761q.f(str) ? 32 : 16;
        }
        if ((i10 & 384) == 0) {
            i11 |= c0761q.d(1500) ? 256 : 128;
        }
        if ((i10 & 3072) == 0) {
            i11 |= 1024;
        }
        if ((i10 & 24576) == 0) {
            i11 |= c0761q.h(bVar) ? 16384 : 8192;
        }
        if ((i11 & 9363) == 9362 && c0761q.x()) {
            c0761q.K();
            i15 = i7;
        } else {
            c0761q.M();
            if ((i10 & 1) == 0 || c0761q.w()) {
                i12 = i11 & (-7169);
                i13 = 1500;
            } else {
                c0761q.K();
                i13 = i7;
                i12 = i11 & (-7169);
            }
            c0761q.q();
            c0761q.Q(1037803695);
            Object objG = c0761q.G();
            S s6 = C0753m.f8270a;
            S s10 = S.f8224q;
            if (objG == s6) {
                objG = C0735d.O(null, s10);
                c0761q.a0(objG);
            }
            X x10 = (X) objG;
            Object objE = M5.b.e(1037805583, c0761q, false);
            if (objE == s6) {
                objE = C0735d.O(null, s10);
                c0761q.a0(objE);
            }
            X x11 = (X) objE;
            Object objE2 = M5.b.e(1037807718, c0761q, false);
            if (objE2 == s6) {
                objE2 = C0735d.O(Boolean.TRUE, s10);
                c0761q.a0(objE2);
            }
            X x12 = (X) objE2;
            Object objE3 = M5.b.e(1037809671, c0761q, false);
            if (objE3 == s6) {
                objE3 = C0735d.O(Boolean.FALSE, s10);
                c0761q.a0(objE3);
            }
            X x13 = (X) objE3;
            c0761q.p(false);
            c0761q.Q(1037812094);
            boolean z6 = (i12 & 112) == 32;
            Object objG2 = c0761q.G();
            if (z6 || objG2 == s6) {
                x8 = x13;
                i14 = i13;
                r13 = 0;
                k kVar = new k(str, x13, x10, x12, x11, null);
                c0761q.a0(kVar);
                objG2 = kVar;
            } else {
                x8 = x13;
                i14 = i13;
                r13 = 0;
            }
            c0761q.p(r13);
            int i16 = i12 >> 3;
            C0735d.f(c0761q, str, (InterfaceC2785n) objG2);
            final Q0 q0B = AbstractC2637h.b(((Boolean) x8.getValue()).booleanValue() ? 0.0f : 1.0f, AbstractC2631e.q((((String) x10.getValue()) == null || ((String) x11.getValue()) == null) ? i14 : 1500, r13, null, 6), "palette_background_transition", null, c0761q, 3072, 20);
            c0761q.Q(1037839323);
            boolean zF = c0761q.f(q0B);
            Object objG3 = c0761q.G();
            if (zF || objG3 == s6) {
                final int i17 = 0;
                objG3 = new InterfaceC2782k() { // from class: G6.h
                    @Override // z7.InterfaceC2782k
                    public final Object b(Object obj) {
                        C1863M c1863m = (C1863M) obj;
                        switch (i17) {
                            case 0:
                                m.f(c1863m, "$this$graphicsLayer");
                                c1863m.a(((Number) q0B.getValue()).floatValue());
                                break;
                            default:
                                m.f(c1863m, "$this$graphicsLayer");
                                c1863m.a(1.0f - ((Number) q0B.getValue()).floatValue());
                                break;
                        }
                        return q.f19267a;
                    }
                };
                c0761q.a0(objG3);
            }
            c0761q.p(r13);
            o oVarA = androidx.compose.ui.graphics.a.a(oVar, (InterfaceC2782k) objG3);
            String str2 = (String) x10.getValue();
            c0761q.Q(1037840729);
            Object objG4 = c0761q.G();
            if (objG4 == s6) {
                x9 = x8;
                objG4 = new i(x9, 0);
                c0761q.a0(objG4);
            } else {
                x9 = x8;
            }
            c0761q.p(r13);
            int i18 = (i16 & 7168) | 384;
            bVar.e(oVarA, str2, (InterfaceC2772a) objG4, c0761q, Integer.valueOf(i18));
            c0761q.Q(1037843840);
            boolean zF2 = c0761q.f(q0B);
            Object objG5 = c0761q.G();
            if (zF2 || objG5 == s6) {
                final int i19 = 1;
                objG5 = new InterfaceC2782k() { // from class: G6.h
                    @Override // z7.InterfaceC2782k
                    public final Object b(Object obj) {
                        C1863M c1863m = (C1863M) obj;
                        switch (i19) {
                            case 0:
                                m.f(c1863m, "$this$graphicsLayer");
                                c1863m.a(((Number) q0B.getValue()).floatValue());
                                break;
                            default:
                                m.f(c1863m, "$this$graphicsLayer");
                                c1863m.a(1.0f - ((Number) q0B.getValue()).floatValue());
                                break;
                        }
                        return q.f19267a;
                    }
                };
                c0761q.a0(objG5);
            }
            c0761q.p(r13);
            o oVarA2 = androidx.compose.ui.graphics.a.a(oVar, (InterfaceC2782k) objG5);
            String str3 = (String) x11.getValue();
            c0761q.Q(1037845401);
            Object objG6 = c0761q.G();
            if (objG6 == s6) {
                objG6 = new i(x9, 1);
                c0761q.a0(objG6);
            }
            c0761q.p(r13);
            bVar.e(oVarA2, str3, (InterfaceC2772a) objG6, c0761q, Integer.valueOf(i18));
            i15 = i14;
        }
        C0754m0 c0754m0R = c0761q.r();
        if (c0754m0R != null) {
            c0754m0R.d = new j(oVar, str, i15, i10);
        }
    }

    public static void D(SpannableStringBuilder spannableStringBuilder, Object obj, int i7, int i10) {
        for (Object obj2 : spannableStringBuilder.getSpans(i7, i10, obj.getClass())) {
            if (spannableStringBuilder.getSpanStart(obj2) == i7 && spannableStringBuilder.getSpanEnd(obj2) == i10 && spannableStringBuilder.getSpanFlags(obj2) == 33) {
                spannableStringBuilder.removeSpan(obj2);
            }
        }
        spannableStringBuilder.setSpan(obj, i7, i10, 33);
    }

    public static int E(int i7, int i10) {
        return (i7 & 16777215) | (i10 << 24);
    }

    public static final Bundle F(m7.i... iVarArr) {
        Bundle bundle = new Bundle(iVarArr.length);
        for (m7.i iVar : iVarArr) {
            String str = (String) iVar.l;
            Object obj = iVar.f19259m;
            if (obj == null) {
                bundle.putString(str, null);
            } else if (obj instanceof Boolean) {
                bundle.putBoolean(str, ((Boolean) obj).booleanValue());
            } else if (obj instanceof Byte) {
                bundle.putByte(str, ((Number) obj).byteValue());
            } else if (obj instanceof Character) {
                bundle.putChar(str, ((Character) obj).charValue());
            } else if (obj instanceof Double) {
                bundle.putDouble(str, ((Number) obj).doubleValue());
            } else if (obj instanceof Float) {
                bundle.putFloat(str, ((Number) obj).floatValue());
            } else if (obj instanceof Integer) {
                bundle.putInt(str, ((Number) obj).intValue());
            } else if (obj instanceof Long) {
                bundle.putLong(str, ((Number) obj).longValue());
            } else if (obj instanceof Short) {
                bundle.putShort(str, ((Number) obj).shortValue());
            } else if (obj instanceof Bundle) {
                bundle.putBundle(str, (Bundle) obj);
            } else if (obj instanceof CharSequence) {
                bundle.putCharSequence(str, (CharSequence) obj);
            } else if (obj instanceof Parcelable) {
                bundle.putParcelable(str, (Parcelable) obj);
            } else if (obj instanceof boolean[]) {
                bundle.putBooleanArray(str, (boolean[]) obj);
            } else if (obj instanceof byte[]) {
                bundle.putByteArray(str, (byte[]) obj);
            } else if (obj instanceof char[]) {
                bundle.putCharArray(str, (char[]) obj);
            } else if (obj instanceof double[]) {
                bundle.putDoubleArray(str, (double[]) obj);
            } else if (obj instanceof float[]) {
                bundle.putFloatArray(str, (float[]) obj);
            } else if (obj instanceof int[]) {
                bundle.putIntArray(str, (int[]) obj);
            } else if (obj instanceof long[]) {
                bundle.putLongArray(str, (long[]) obj);
            } else if (obj instanceof short[]) {
                bundle.putShortArray(str, (short[]) obj);
            } else if (obj instanceof Object[]) {
                Class<?> componentType = obj.getClass().getComponentType();
                m.c(componentType);
                if (Parcelable.class.isAssignableFrom(componentType)) {
                    bundle.putParcelableArray(str, (Parcelable[]) obj);
                } else if (String.class.isAssignableFrom(componentType)) {
                    bundle.putStringArray(str, (String[]) obj);
                } else if (CharSequence.class.isAssignableFrom(componentType)) {
                    bundle.putCharSequenceArray(str, (CharSequence[]) obj);
                } else {
                    if (!Serializable.class.isAssignableFrom(componentType)) {
                        throw new IllegalArgumentException("Illegal value array type " + componentType.getCanonicalName() + " for key \"" + str + '\"');
                    }
                    bundle.putSerializable(str, (Serializable) obj);
                }
            } else if (obj instanceof Serializable) {
                bundle.putSerializable(str, (Serializable) obj);
            } else if (obj instanceof IBinder) {
                bundle.putBinder(str, (IBinder) obj);
            } else if (obj instanceof Size) {
                bundle.putSize(str, (Size) obj);
            } else {
                if (!(obj instanceof SizeF)) {
                    throw new IllegalArgumentException("Illegal value type " + obj.getClass().getCanonicalName() + " for key \"" + str + '\"');
                }
                bundle.putSizeF(str, (SizeF) obj);
            }
        }
        return bundle;
    }

    public static final Object G(Object obj, InterfaceC0650c interfaceC0650c) {
        AbstractC0287w abstractC0287wO;
        Class clsF0;
        return (((interfaceC0650c instanceof Q7.L) && r8.h.d((U) interfaceC0650c)) || (abstractC0287wO = O(interfaceC0650c)) == null || (clsF0 = f0(abstractC0287wO)) == null) ? obj : T(clsF0, interfaceC0650c).invoke(obj, null);
    }

    public static O7.g H(O7.c cVar, boolean z6) {
        String lowerCase;
        m.f(cVar, "functionClass");
        O7.g gVar = new O7.g(cVar, null, 1, z6);
        u uVarJ0 = cVar.J0();
        n7.u uVar = n7.u.l;
        ArrayList arrayList = new ArrayList();
        List list = cVar.f5178v;
        for (Object obj : list) {
            if (((Q) obj).e0() != 2) {
                break;
            }
            arrayList.add(obj);
        }
        p pVarE0 = AbstractC1998m.E0(arrayList);
        ArrayList arrayList2 = new ArrayList(AbstractC2000o.J(pVarE0, 10));
        Iterator it = pVarE0.iterator();
        while (true) {
            P8.b bVar = (P8.b) it;
            if (!bVar.f5459m.hasNext()) {
                gVar.h1(null, uVarJ0, uVar, uVar, arrayList2, ((Q) AbstractC1998m.h0(list)).j(), 4, AbstractC0662o.f7191e);
                gVar.f8891I = true;
                return gVar;
            }
            x xVar = (x) bVar.next();
            int i7 = xVar.f20159a;
            Q q4 = (Q) xVar.f20160b;
            String strB = q4.getName().b();
            m.e(strB, "typeParameter.name.asString()");
            if (strB.equals("T")) {
                lowerCase = "instance";
            } else if (strB.equals("E")) {
                lowerCase = "receiver";
            } else {
                lowerCase = strB.toLowerCase(Locale.ROOT);
                m.e(lowerCase, "this as java.lang.String).toLowerCase(Locale.ROOT)");
            }
            f fVar = R7.g.f7535a;
            C2061f c2061fE = C2061f.e(lowerCase);
            A aJ = q4.j();
            m.e(aJ, "typeParameter.defaultType");
            u uVar2 = uVarJ0;
            ArrayList arrayList3 = arrayList2;
            arrayList3.add(new T7.Q(gVar, null, i7, fVar, c2061fE, aJ, false, false, false, null, Q7.N.c));
            arrayList2 = arrayList3;
            uVarJ0 = uVar2;
        }
    }

    public static final L7.e I(L7.e eVar, InterfaceC0666t interfaceC0666t, boolean z6) {
        AbstractC0287w abstractC0287wP;
        AbstractC0287w abstractC0287wO;
        m.f(interfaceC0666t, "descriptor");
        if (!r8.h.a(interfaceC0666t)) {
            List listD0 = interfaceC0666t.D0();
            m.e(listD0, "descriptor.valueParameters");
            if (!listD0.isEmpty()) {
                Iterator it = listD0.iterator();
                while (it.hasNext()) {
                    AbstractC0287w abstractC0287wB = ((T7.Q) it.next()).b();
                    m.e(abstractC0287wB, "it.type");
                    if (r8.h.c(abstractC0287wB)) {
                        break;
                    }
                }
                abstractC0287wP = interfaceC0666t.p();
                return abstractC0287wP != null ? eVar : eVar;
            }
            abstractC0287wP = interfaceC0666t.p();
            if ((abstractC0287wP != null || !r8.h.c(abstractC0287wP)) && ((eVar instanceof L7.d) || (abstractC0287wO = O(interfaceC0666t)) == null || !r8.h.c(abstractC0287wO))) {
                return eVar;
            }
        }
        return new L7.u(eVar, interfaceC0666t, z6);
    }

    public static final float J(int i7, C0761q c0761q) {
        return ((Context) c0761q.k(AndroidCompositionLocals_androidKt.f11688b)).getResources().getDimension(i7) / ((a1.b) c0761q.k(AbstractC0227s0.f2259f)).getDensity();
    }

    public static final boolean K(int i7, int i10) {
        return i7 == i10;
    }

    public static String L(List list, String str, I i7, int i10) {
        if ((i10 & 1) != 0) {
            str = ", ";
        }
        if ((i10 & 32) != 0) {
            i7 = null;
        }
        StringBuilder sb = new StringBuilder();
        sb.append((CharSequence) "");
        int size = list.size();
        int i11 = 0;
        for (int i12 = 0; i12 < size; i12++) {
            Object obj = list.get(i12);
            i11++;
            if (i11 > 1) {
                sb.append((CharSequence) str);
            }
            if (i7 != null) {
                A7.k.q(obj);
                throw null;
            }
            if (obj != null ? obj instanceof CharSequence : true) {
                sb.append((CharSequence) obj);
            } else if (obj instanceof Character) {
                sb.append(((Character) obj).charValue());
            } else {
                sb.append((CharSequence) String.valueOf(obj));
            }
        }
        sb.append((CharSequence) "");
        return sb.toString();
    }

    public static final String M(Object obj) {
        return obj + " cannot be saved using the current SaveableStateRegistry. The default implementation only supports types which can be stored inside the Bundle. Please consider implementing a custom Saver for this class and pass it to rememberSaveable().";
    }

    public static N6.f N(n nVar, h0 h0Var) {
        I5.g gVar = (I5.g) ((N6.a) AbstractC1274a.I(N6.a.class, nVar));
        R6.b bVarA = gVar.a();
        O o = new O(gVar.f2952a, 22, gVar.f2953b);
        h0Var.getClass();
        return new N6.f(bVarA, h0Var, o);
    }

    public static final AbstractC0287w O(InterfaceC0650c interfaceC0650c) {
        u uVarK = interfaceC0650c.K();
        u uVarB = interfaceC0650c.B();
        if (uVarK != null) {
            return uVarK.b();
        }
        if (uVarB != null) {
            if (interfaceC0650c instanceof InterfaceC0656i) {
                return uVarB.b();
            }
            InterfaceC0657j interfaceC0657jO = interfaceC0650c.o();
            InterfaceC0652e interfaceC0652e = interfaceC0657jO instanceof InterfaceC0652e ? (InterfaceC0652e) interfaceC0657jO : null;
            if (interfaceC0652e != null) {
                return interfaceC0652e.j();
            }
        }
        return null;
    }

    public static final float P(List list, boolean z6, C0761q c0761q) {
        if (z6) {
            c0761q.Q(626717141);
            Iterator it = list.iterator();
            if (!it.hasNext()) {
                throw new NoSuchElementException();
            }
            a1.e eVar = new a1.e(Q((C0569g) it.next(), c0761q));
            while (it.hasNext()) {
                a1.e eVar2 = new a1.e(Q((C0569g) it.next(), c0761q));
                if (eVar.compareTo(eVar2) < 0) {
                    eVar = eVar2;
                }
            }
            c0761q.p(false);
            return eVar.l;
        }
        c0761q.Q(626783388);
        float size = 0;
        c0761q.Q(-949611430);
        Iterator it2 = list.iterator();
        while (it2.hasNext()) {
            size += Q((C0569g) it2.next(), c0761q);
        }
        c0761q.p(false);
        if (list.size() > 1) {
            size += (list.size() - 1) * m6.c.f19252a.S(c0761q);
        }
        c0761q.p(false);
        return size;
    }

    public static final float Q(C0569g c0569g, C0761q c0761q) {
        return m6.c.f19252a.T(c0761q) * (c0569g.f6809a.l ? 4.0f : 1.0f);
    }

    public static final long R(KeyEvent keyEvent) {
        return r.a(keyEvent.getKeyCode());
    }

    public static final int S(KeyEvent keyEvent) {
        int action = keyEvent.getAction();
        if (action != 0) {
            return action != 1 ? 0 : 1;
        }
        return 2;
    }

    public static final Method T(Class cls, InterfaceC0650c interfaceC0650c) {
        m.f(interfaceC0650c, "descriptor");
        try {
            Method declaredMethod = cls.getDeclaredMethod("unbox-impl", null);
            m.e(declaredMethod, "{\n        getDeclaredMet…LINE_CLASS_MEMBERS)\n    }");
            return declaredMethod;
        } catch (NoSuchMethodException unused) {
            throw new r0("No unbox method found in inline class: " + cls + " (calling " + interfaceC0650c + ')');
        }
    }

    public static final boolean U(C0411f c0411f) {
        int length = c0411f.l.length();
        List list = c0411f.o;
        if (list == null) {
            return false;
        }
        int size = list.size();
        for (int i7 = 0; i7 < size; i7++) {
            C0409d c0409d = (C0409d) list.get(i7);
            if ((c0409d.f4371a instanceof AbstractC0418m) && AbstractC0412g.c(0, length, c0409d.f4372b, c0409d.c)) {
                return true;
            }
        }
        return false;
    }

    public static String V(w wVar) {
        m.f(wVar, "url");
        C2322l c2322l = C2322l.o;
        return m6.b.p(wVar.f12624h).c("MD5").e();
    }

    public static final F4.c W(E4.b bVar, int i7, int i10) {
        m.f(bVar, "<this>");
        int i11 = i7 - 1;
        int i12 = i10 - 1;
        int i13 = i7 + 1;
        int i14 = i10 + 1;
        return new F4.c(X(bVar, i7, i10, i11, i12), X(bVar, i7, i10, i13, i12), X(bVar, i7, i10, i11, i10), X(bVar, i7, i10, i7, i12), X(bVar, i7, i10, i13, i10), X(bVar, i7, i10, i11, i14), X(bVar, i7, i10, i7, i14), X(bVar, i7, i10, i13, i14));
    }

    public static final boolean X(E4.b bVar, int i7, int i10, int i11, int i12) {
        Object objF;
        try {
            objF = Boolean.valueOf(bVar.c(i11, i12) == bVar.c(i7, i10));
        } catch (Throwable th) {
            objF = AbstractC1386c.f(th);
        }
        Object obj = Boolean.FALSE;
        if (objF instanceof m7.k) {
            objF = obj;
        }
        return ((Boolean) objF).booleanValue();
    }

    public static int Y(D d) throws IOException {
        C2319i c2319i;
        byte bK;
        try {
            d.I(1L);
            long j3 = 0;
            while (true) {
                long j10 = j3 + 1;
                boolean zL = d.l(j10);
                c2319i = d.f21897m;
                if (!zL) {
                    break;
                }
                bK = c2319i.k(j3);
                if ((bK < 48 || bK > 57) && (j3 != 0 || bK != 45)) {
                    break;
                }
                j3 = j10;
            }
            if (j3 == 0) {
                AbstractC1274a.y(16);
                String string = Integer.toString(bK, 16);
                m.e(string, "toString(...)");
                throw new NumberFormatException("Expected a digit or '-' but was 0x".concat(string));
            }
            long jG = c2319i.G();
            String strG = d.G(Long.MAX_VALUE);
            if (jG >= 0 && jG <= 2147483647L && strG.length() <= 0) {
                return (int) jG;
            }
            throw new IOException("expected an int but was \"" + jG + strG + '\"');
        } catch (NumberFormatException e10) {
            throw new IOException(e10.getMessage());
        }
    }

    /*  JADX ERROR: JadxRuntimeException in pass: ConstructorVisitor
        jadx.core.utils.exceptions.JadxRuntimeException: Can't remove SSA var: r8v0 ??, still in use, count: 1, list:
          (r8v0 ?? I:java.lang.Object) from 0x004f: INVOKE (r12v0 ?? I:T.q), (r8v0 ?? I:java.lang.Object) VIRTUAL call: T.q.a0(java.lang.Object):void A[MD:(java.lang.Object):void (m)]
        	at jadx.core.utils.InsnRemover.removeSsaVar(InsnRemover.java:162)
        	at jadx.core.utils.InsnRemover.unbindResult(InsnRemover.java:127)
        	at jadx.core.utils.InsnRemover.lambda$unbindInsns$1(InsnRemover.java:99)
        	at java.base/java.util.ArrayList.forEach(ArrayList.java:1596)
        	at jadx.core.utils.InsnRemover.unbindInsns(InsnRemover.java:98)
        	at jadx.core.utils.InsnRemover.perform(InsnRemover.java:73)
        	at jadx.core.dex.visitors.ConstructorVisitor.replaceInvoke(ConstructorVisitor.java:59)
        	at jadx.core.dex.visitors.ConstructorVisitor.visit(ConstructorVisitor.java:42)
        */
    public static final java.lang.Object Z(
    /*  JADX ERROR: JadxRuntimeException in pass: ConstructorVisitor
        jadx.core.utils.exceptions.JadxRuntimeException: Can't remove SSA var: r8v0 ??, still in use, count: 1, list:
          (r8v0 ?? I:java.lang.Object) from 0x004f: INVOKE (r12v0 ?? I:T.q), (r8v0 ?? I:java.lang.Object) VIRTUAL call: T.q.a0(java.lang.Object):void A[MD:(java.lang.Object):void (m)]
        	at jadx.core.utils.InsnRemover.removeSsaVar(InsnRemover.java:162)
        	at jadx.core.utils.InsnRemover.unbindResult(InsnRemover.java:127)
        	at jadx.core.utils.InsnRemover.lambda$unbindInsns$1(InsnRemover.java:99)
        	at java.base/java.util.ArrayList.forEach(ArrayList.java:1596)
        	at jadx.core.utils.InsnRemover.unbindInsns(InsnRemover.java:98)
        	at jadx.core.utils.InsnRemover.perform(InsnRemover.java:73)
        	at jadx.core.dex.visitors.ConstructorVisitor.replaceInvoke(ConstructorVisitor.java:59)
        */
    /*  JADX ERROR: Method generation error
        jadx.core.utils.exceptions.JadxRuntimeException: Code variable not set in r9v0 ??
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

    /* JADX WARN: Removed duplicated region for block: B:110:0x018e  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static final void a(g0.o r32, final Q5.C0595m1 r33, final java.util.List r34, final F0.C0223q r35, final java.lang.Integer r36, final boolean r37, final boolean r38, final boolean r39, T.C0761q r40, final int r41) {
        /*
            Method dump skipped, instruction units count: 953
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: w0.c.a(g0.o, Q5.m1, java.util.List, F0.q, java.lang.Integer, boolean, boolean, boolean, T.q, int):void");
    }

    public static final float a0(long j3, float f10, a1.b bVar) {
        float fC;
        long jB = a1.m.b(j3);
        if (a1.n.a(jB, 4294967296L)) {
            if (bVar.p() <= 1.05d) {
                return bVar.b0(j3);
            }
            fC = a1.m.c(j3) / a1.m.c(bVar.j0(f10));
        } else {
            if (!a1.n.a(jB, 8589934592L)) {
                return Float.NaN;
            }
            fC = a1.m.c(j3);
        }
        return fC * f10;
    }

    public static final void b(a1.b bVar, C0595m1 c0595m1, X x8, o oVar, C0761q c0761q) {
        c0761q.Q(-1698942262);
        c0761q.Q(700517008);
        boolean zF = c0761q.f(bVar);
        Object objG = c0761q.G();
        if (zF || objG == C0753m.f8270a) {
            objG = new Q5.Q0(bVar, x8, 1);
            c0761q.a0(objG);
        }
        c0761q.p(false);
        o oVarD = androidx.compose.ui.layout.a.d(oVar, (InterfaceC2782k) objG);
        m.c(c0595m1);
        u(oVarD, c0595m1, c0761q, 0);
        c0761q.p(false);
    }

    public static final void b0(Spannable spannable, long j3, int i7, int i10) {
        if (j3 != 16) {
            spannable.setSpan(new ForegroundColorSpan(AbstractC1861K.E(j3)), i7, i10, 33);
        }
    }

    public static final void c(List list, C0223q c0223q, Integer num, FontSize fontSize, o oVar, C0761q c0761q) {
        c0761q.Q(-597016897);
        m.c(list);
        m.c(c0223q);
        m.c(num);
        AbstractC2673a.l(oVar, list, num.intValue(), c0223q, fontSize, c0761q, 24576);
        c0761q.p(false);
    }

    public static final void c0(Spannable spannable, long j3, a1.b bVar, int i7, int i10) {
        long jB = a1.m.b(j3);
        if (a1.n.a(jB, 4294967296L)) {
            spannable.setSpan(new AbsoluteSizeSpan(C7.a.W(bVar.b0(j3)), false), i7, i10, 33);
        } else if (a1.n.a(jB, 8589934592L)) {
            spannable.setSpan(new RelativeSizeSpan(a1.m.c(j3)), i7, i10, 33);
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:105:0x012e  */
    /* JADX WARN: Removed duplicated region for block: B:110:0x013c  */
    /* JADX WARN: Removed duplicated region for block: B:113:0x0142  */
    /* JADX WARN: Removed duplicated region for block: B:120:0x0153  */
    /* JADX WARN: Removed duplicated region for block: B:127:0x016a  */
    /* JADX WARN: Removed duplicated region for block: B:133:0x018b  */
    /* JADX WARN: Removed duplicated region for block: B:148:0x01c9  */
    /* JADX WARN: Removed duplicated region for block: B:149:0x01cb  */
    /* JADX WARN: Removed duplicated region for block: B:150:0x01cd  */
    /* JADX WARN: Removed duplicated region for block: B:152:0x01d1  */
    /* JADX WARN: Removed duplicated region for block: B:153:0x01d3  */
    /* JADX WARN: Removed duplicated region for block: B:155:0x01d7  */
    /* JADX WARN: Removed duplicated region for block: B:156:0x01d9  */
    /* JADX WARN: Removed duplicated region for block: B:158:0x01dd  */
    /* JADX WARN: Removed duplicated region for block: B:159:0x01e0  */
    /* JADX WARN: Removed duplicated region for block: B:162:0x01e6  */
    /* JADX WARN: Removed duplicated region for block: B:165:0x01f6  */
    /* JADX WARN: Removed duplicated region for block: B:166:0x0205  */
    /* JADX WARN: Removed duplicated region for block: B:169:0x020f  */
    /* JADX WARN: Removed duplicated region for block: B:170:0x021f  */
    /* JADX WARN: Removed duplicated region for block: B:172:0x0223  */
    /* JADX WARN: Removed duplicated region for block: B:173:0x0225  */
    /* JADX WARN: Removed duplicated region for block: B:186:0x0299  */
    /* JADX WARN: Removed duplicated region for block: B:190:0x02bb  */
    /* JADX WARN: Removed duplicated region for block: B:193:0x02ea  */
    /* JADX WARN: Removed duplicated region for block: B:194:0x02ee  */
    /* JADX WARN: Removed duplicated region for block: B:199:0x030f  */
    /* JADX WARN: Removed duplicated region for block: B:202:0x031d  */
    /* JADX WARN: Removed duplicated region for block: B:203:0x031f  */
    /* JADX WARN: Removed duplicated region for block: B:210:0x0382  */
    /* JADX WARN: Removed duplicated region for block: B:213:0x03b5  */
    /* JADX WARN: Removed duplicated region for block: B:214:0x03b8  */
    /* JADX WARN: Removed duplicated region for block: B:218:0x040a  */
    /* JADX WARN: Removed duplicated region for block: B:220:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:26:0x0056  */
    /* JADX WARN: Removed duplicated region for block: B:28:0x005b  */
    /* JADX WARN: Removed duplicated region for block: B:37:0x0072  */
    /* JADX WARN: Removed duplicated region for block: B:39:0x0077  */
    /* JADX WARN: Removed duplicated region for block: B:48:0x008e  */
    /* JADX WARN: Removed duplicated region for block: B:53:0x009e  */
    /* JADX WARN: Removed duplicated region for block: B:56:0x00a6  */
    /* JADX WARN: Removed duplicated region for block: B:63:0x00ba  */
    /* JADX WARN: Removed duplicated region for block: B:66:0x00c4  */
    /* JADX WARN: Removed duplicated region for block: B:73:0x00d7  */
    /* JADX WARN: Removed duplicated region for block: B:81:0x00ed  */
    /* JADX WARN: Removed duplicated region for block: B:84:0x00f5  */
    /* JADX WARN: Removed duplicated region for block: B:92:0x010a  */
    /* JADX WARN: Removed duplicated region for block: B:95:0x0112  */
    /* JADX WARN: Removed duplicated region for block: B:96:0x0117  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static final void d(g0.o r42, boolean r43, boolean r44, boolean r45, final java.lang.String r46, N0.L r47, final java.lang.String r48, long r49, N0.L r51, java.lang.String r52, final java.util.List r53, final java.util.List r54, final boolean r55, T.C0761q r56, final int r57, final int r58, final int r59) {
        /*
            Method dump skipped, instruction units count: 1069
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: w0.c.d(g0.o, boolean, boolean, boolean, java.lang.String, N0.L, java.lang.String, long, N0.L, java.lang.String, java.util.List, java.util.List, boolean, T.q, int, int, int):void");
    }

    /* JADX WARN: Removed duplicated region for block: B:16:0x0032  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static final java.lang.Object d0(int r4, java.lang.Object r5, S0.G r6, S0.A r7, int r8) {
        /*
            boolean r0 = r5 instanceof android.graphics.Typeface
            if (r0 != 0) goto L5
            return r5
        L5:
            r0 = 1
            boolean r1 = S0.x.a(r4, r0)
            r2 = 0
            if (r1 != 0) goto L14
            r1 = 2
            boolean r1 = S0.x.a(r4, r1)
            if (r1 == 0) goto L32
        L14:
            S0.A r1 = r6.f7809b
            boolean r1 = A7.m.a(r1, r7)
            if (r1 != 0) goto L32
            S0.A r1 = S0.A.o
            int r3 = r7.compareTo(r1)
            if (r3 < 0) goto L32
            S0.A r3 = r6.f7809b
            int r3 = r3.l
            int r1 = r1.l
            int r1 = A7.m.g(r3, r1)
            if (r1 >= 0) goto L32
            r1 = r0
            goto L33
        L32:
            r1 = r2
        L33:
            boolean r3 = S0.x.a(r4, r0)
            if (r3 != 0) goto L40
            r3 = 3
            boolean r4 = S0.x.a(r4, r3)
            if (r4 == 0) goto L49
        L40:
            int r4 = r6.c
            boolean r4 = S0.w.a(r8, r4)
            if (r4 != 0) goto L49
            r2 = r0
        L49:
            if (r2 != 0) goto L4e
            if (r1 != 0) goto L4e
            return r5
        L4e:
            if (r1 == 0) goto L53
            int r4 = r7.l
            goto L57
        L53:
            S0.A r4 = r6.f7809b
            int r4 = r4.l
        L57:
            if (r2 == 0) goto L5e
            boolean r6 = S0.w.a(r8, r0)
            goto L64
        L5e:
            int r6 = r6.c
            boolean r6 = S0.w.a(r6, r0)
        L64:
            S0.K r7 = S0.K.f7813a
            android.graphics.Typeface r5 = (android.graphics.Typeface) r5
            android.graphics.Typeface r4 = r7.a(r5, r4, r6)
            return r4
        */
        throw new UnsupportedOperationException("Method not decompiled: w0.c.d0(int, java.lang.Object, S0.G, S0.A, int):java.lang.Object");
    }

    public static final void e(h6.d dVar, C0761q c0761q, int i7) {
        h6.d dVar2;
        S s6;
        boolean z6;
        boolean z10;
        h6.d dVar3;
        c0761q.S(550550640);
        if ((((i7 & 6) == 0 ? i7 | 2 : i7) & 3) == 2 && c0761q.x()) {
            c0761q.K();
            dVar3 = dVar;
        } else {
            c0761q.M();
            if ((i7 & 1) == 0 || c0761q.w()) {
                c0761q.R(1890788296);
                l0 l0VarA = c2.b.a(c0761q);
                if (l0VarA == null) {
                    throw new IllegalStateException("No ViewModelStoreOwner was provided via LocalViewModelStoreOwner");
                }
                N6.f fVarU = H.u(l0VarA, c0761q);
                c0761q.R(1729797275);
                androidx.lifecycle.f0 f0VarB0 = C7.a.b0(h6.d.class, l0VarA, fVarU, l0VarA instanceof InterfaceC0977o ? ((InterfaceC0977o) l0VarA).e() : C0991a.f12202b, c0761q);
                c0761q.p(false);
                c0761q.p(false);
                dVar2 = (h6.d) f0VarB0;
            } else {
                c0761q.K();
                dVar2 = dVar;
            }
            c0761q.q();
            l lVar = l.f14684a;
            o oVarC = androidx.compose.foundation.layout.c.c(lVar, 1.0f);
            K kE = AbstractC0133o.e(g0.b.l, false);
            int i10 = c0761q.f8304P;
            InterfaceC0744h0 interfaceC0744h0M = c0761q.m();
            o oVarD = g0.a.d(c0761q, oVarC);
            InterfaceC0161k.f1681b.getClass();
            C0159i c0159i = C0160j.f1678b;
            c0761q.U();
            if (c0761q.f8303O) {
                c0761q.l(c0159i);
            } else {
                c0761q.d0();
            }
            C0735d.V(c0761q, kE, C0160j.f1679e);
            C0735d.V(c0761q, interfaceC0744h0M, C0160j.d);
            C0158h c0158h = C0160j.f1680f;
            if (c0761q.f8303O || !m.a(c0761q.G(), Integer.valueOf(i10))) {
                AbstractC1347d.l(i10, c0761q, i10, c0158h);
            }
            C0735d.V(c0761q, oVarD, C0160j.c);
            o oVarA = androidx.compose.foundation.layout.b.f11474a.a(lVar, g0.b.f14663n);
            boolean zBooleanValue = ((Boolean) com.bumptech.glide.c.R(dVar2.l.f18251h, c0761q, 0).getValue()).booleanValue();
            C1696d c1696d = dVar2.l;
            boolean zBooleanValue2 = ((Boolean) com.bumptech.glide.c.R(c1696d.f18252i, c0761q, 0).getValue()).booleanValue();
            c0761q.Q(1165355798);
            boolean zH = c0761q.h(dVar2);
            Object objG = c0761q.G();
            S s10 = C0753m.f8270a;
            if (zH || objG == s10) {
                s6 = s10;
                C0223q c0223q = new C0223q(1, dVar2, h6.d.class, "setForceParked", "setForceParked(Z)V", 0, 22);
                c0761q.a0(c0223q);
                objG = c0223q;
            } else {
                s6 = s10;
            }
            InterfaceC0321f interfaceC0321f = (InterfaceC0321f) objG;
            c0761q.p(false);
            c0761q.Q(1165357953);
            boolean zH2 = c0761q.h(dVar2);
            Object objG2 = c0761q.G();
            if (zH2 || objG2 == s6) {
                z6 = zBooleanValue2;
                C0223q c0223q2 = new C0223q(1, dVar2, h6.d.class, "setForcedPassengerPresent", "setForcedPassengerPresent(Z)V", 0, 23);
                c0761q.a0(c0223q2);
                objG2 = c0223q2;
            } else {
                z6 = zBooleanValue2;
            }
            InterfaceC0321f interfaceC0321f2 = (InterfaceC0321f) objG2;
            c0761q.p(false);
            boolean zBooleanValue3 = ((Boolean) com.bumptech.glide.c.R(c1696d.f18253j, c0761q, 0).getValue()).booleanValue();
            boolean zBooleanValue4 = ((Boolean) com.bumptech.glide.c.R(dVar2.f15569m, c0761q, 0).getValue()).booleanValue();
            Boolean bool = (Boolean) com.bumptech.glide.c.R(dVar2.f15570n, c0761q, 0).getValue();
            c0761q.Q(1165368917);
            boolean zH3 = c0761q.h(dVar2);
            Object objG3 = c0761q.G();
            if (zH3 || objG3 == s6) {
                z10 = zBooleanValue;
                C0223q c0223q3 = new C0223q(1, dVar2, h6.d.class, "setIsDarkMode", "setIsDarkMode(Ljava/lang/Boolean;)V", 0, 24);
                c0761q.a0(c0223q3);
                objG3 = c0223q3;
            } else {
                z10 = zBooleanValue;
            }
            InterfaceC0321f interfaceC0321f3 = (InterfaceC0321f) objG3;
            c0761q.p(false);
            boolean zBooleanValue5 = ((Boolean) com.bumptech.glide.c.R(c1696d.f18249f, c0761q, 0).getValue()).booleanValue();
            c0761q.Q(1165373594);
            boolean zH4 = c0761q.h(dVar2);
            Object objG4 = c0761q.G();
            if (zH4 || objG4 == s6) {
                C0223q c0223q4 = new C0223q(1, dVar2, h6.d.class, "setShowDisplayInfo", "setShowDisplayInfo(Z)V", 0, 25);
                c0761q.a0(c0223q4);
                objG4 = c0223q4;
            }
            c0761q.p(false);
            f(oVarA, z10, z6, (InterfaceC2782k) interfaceC0321f, zBooleanValue3, (InterfaceC2782k) interfaceC0321f2, zBooleanValue4, bool, (InterfaceC2782k) interfaceC0321f3, zBooleanValue5, (InterfaceC2782k) ((InterfaceC0321f) objG4), c0761q, 0, 0);
            c0761q.p(true);
            dVar3 = dVar2;
        }
        C0754m0 c0754m0R = c0761q.r();
        if (c0754m0R != null) {
            c0754m0R.d = new R5.a(dVar3, i7, 1);
        }
    }

    public static final void e0(String str) {
        throw new IllegalArgumentException(str);
    }

    /* JADX WARN: Removed duplicated region for block: B:138:0x0210  */
    /* JADX WARN: Removed duplicated region for block: B:139:0x0213  */
    /* JADX WARN: Removed duplicated region for block: B:144:0x022f  */
    /* JADX WARN: Removed duplicated region for block: B:167:0x0261  */
    /* JADX WARN: Removed duplicated region for block: B:171:0x0270  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static final void f(g0.o r39, boolean r40, boolean r41, z7.InterfaceC2782k r42, boolean r43, z7.InterfaceC2782k r44, boolean r45, java.lang.Boolean r46, z7.InterfaceC2782k r47, boolean r48, z7.InterfaceC2782k r49, T.C0761q r50, int r51, int r52) {
        /*
            Method dump skipped, instruction units count: 1026
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: w0.c.f(g0.o, boolean, boolean, z7.k, boolean, z7.k, boolean, java.lang.Boolean, z7.k, boolean, z7.k, T.q, int, int):void");
    }

    public static final Class f0(AbstractC0287w abstractC0287w) {
        m.f(abstractC0287w, "<this>");
        Class clsG0 = g0(abstractC0287w.B0().n());
        if (clsG0 == null) {
            return null;
        }
        if (!Y.e(abstractC0287w)) {
            return clsG0;
        }
        A aF = r8.h.f(abstractC0287w);
        if (aF == null || Y.e(aF) || N7.i.E(aF)) {
            return null;
        }
        return clsG0;
    }

    public static final void g(o oVar, String str, L l, C0761q c0761q, int i7) {
        String strX;
        o oVar2;
        m.f(l, "contentPadding");
        c0761q.S(884817920);
        int i10 = i7 | 6;
        if ((i7 & 48) == 0) {
            i10 |= c0761q.f(str) ? 32 : 16;
        }
        if ((i7 & 384) == 0) {
            i10 |= c0761q.f(l) ? 256 : 128;
        }
        if ((i10 & 147) == 146 && c0761q.x()) {
            c0761q.K();
            oVar2 = oVar;
        } else {
            l lVar = l.f14684a;
            o oVarJ = androidx.compose.foundation.layout.a.j(androidx.compose.foundation.layout.c.c(lVar, 1.0f), l);
            g0.e eVar = g0.b.f14673y;
            C0119a c0119a = AbstractC0127i.f1076a;
            C0141x c0141xA = AbstractC0139v.a(new C0124f(m6.c.f19252a.G(c0761q)), eVar, c0761q, 48);
            int i11 = c0761q.f8304P;
            InterfaceC0744h0 interfaceC0744h0M = c0761q.m();
            o oVarD = g0.a.d(c0761q, oVarJ);
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
            if (c0761q.f8303O || !m.a(c0761q.G(), Integer.valueOf(i11))) {
                AbstractC1347d.l(i11, c0761q, i11, c0158h);
            }
            C0735d.V(c0761q, oVarD, C0160j.c);
            c0761q.Q(-1360123354);
            if (str == null) {
                Context context = (Context) c0761q.k(AndroidCompositionLocals_androidKt.f11688b);
                m.f(context, "context");
                Object systemService = context.getSystemService("connectivity");
                m.d(systemService, "null cannot be cast to non-null type android.net.ConnectivityManager");
                ConnectivityManager connectivityManager = (ConnectivityManager) systemService;
                NetworkCapabilities networkCapabilities = connectivityManager.getNetworkCapabilities(connectivityManager.getActiveNetwork());
                strX = AbstractC2673a.X((networkCapabilities != null && networkCapabilities.hasCapability(12) && networkCapabilities.hasCapability(16)) ? R.string.Error_Default : R.string.Error_NetworkError, c0761q);
            } else {
                strX = str;
            }
            c0761q.p(false);
            oVar2 = lVar;
            c1.b(strX, null, l6.d.c(c0761q).f18696b, h6.f.g(FontSize.H9, c0761q), null, null, null, 0L, null, new Y0.i(3), 0L, 0, false, 0, 0, null, ((O5.d) c0761q.k(O5.c.f5157b)).f5158a, c0761q, 0, 0, 65010);
            c0761q.p(true);
        }
        C0754m0 c0754m0R = c0761q.r();
        if (c0754m0R != null) {
            c0754m0R.d = new Q5.N(oVar2, str, l, i7, 0);
        }
    }

    public static final Class g0(InterfaceC0657j interfaceC0657j) {
        if (!(interfaceC0657j instanceof InterfaceC0652e) || !r8.h.b(interfaceC0657j)) {
            return null;
        }
        InterfaceC0652e interfaceC0652e = (InterfaceC0652e) interfaceC0657j;
        Class clsJ = A0.j(interfaceC0652e);
        if (clsJ != null) {
            return clsJ;
        }
        throw new r0("Class object for the class " + interfaceC0652e.getName() + " cannot be found (classId=" + AbstractC2564d.f((InterfaceC0654g) interfaceC0657j) + ')');
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:102:0x0126  */
    /* JADX WARN: Removed duplicated region for block: B:104:0x012b  */
    /* JADX WARN: Removed duplicated region for block: B:112:0x0145  */
    /* JADX WARN: Removed duplicated region for block: B:118:0x015b  */
    /* JADX WARN: Removed duplicated region for block: B:119:0x015d  */
    /* JADX WARN: Removed duplicated region for block: B:120:0x0160  */
    /* JADX WARN: Removed duplicated region for block: B:122:0x0164  */
    /* JADX WARN: Removed duplicated region for block: B:123:0x0167  */
    /* JADX WARN: Removed duplicated region for block: B:126:0x0173  */
    /* JADX WARN: Removed duplicated region for block: B:127:0x0175  */
    /* JADX WARN: Removed duplicated region for block: B:130:0x01fb  */
    /* JADX WARN: Removed duplicated region for block: B:131:0x01ff  */
    /* JADX WARN: Removed duplicated region for block: B:136:0x0220  */
    /* JADX WARN: Removed duplicated region for block: B:139:0x0234  */
    /* JADX WARN: Removed duplicated region for block: B:140:0x0240  */
    /* JADX WARN: Removed duplicated region for block: B:142:0x0243  */
    /* JADX WARN: Removed duplicated region for block: B:144:0x024f  */
    /* JADX WARN: Removed duplicated region for block: B:150:0x029a  */
    /* JADX WARN: Removed duplicated region for block: B:154:0x02d8  */
    /* JADX WARN: Removed duplicated region for block: B:156:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Type inference failed for: r11v1 */
    /* JADX WARN: Type inference failed for: r11v11 */
    /* JADX WARN: Type inference failed for: r11v2, types: [boolean, int] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static final void h(final g0.o r32, final java.lang.String r33, final java.lang.String r34, final z7.InterfaceC2772a r35, final z7.InterfaceC2772a r36, final Q5.C0599n1 r37, final Q5.C0595m1 r38, final java.util.List r39, final F0.C0223q r40, final java.lang.Integer r41, final boolean r42, final boolean r43, boolean r44, boolean r45, T.C0761q r46, final int r47, final int r48, final int r49) {
        /*
            Method dump skipped, instruction units count: 775
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: w0.c.h(g0.o, java.lang.String, java.lang.String, z7.a, z7.a, Q5.n1, Q5.m1, java.util.List, F0.q, java.lang.Integer, boolean, boolean, boolean, boolean, T.q, int, int, int):void");
    }

    public static final void h0(TextView textView, long j3, N0.L l, long j10, int i7, int i10, boolean z6) {
        N0.L lE = l.e(new N0.L(j3, !a1.m.a(j10, a1.m.c) ? j10 : l.f4358a.f4325b, null, null, null, 0L, i10, 0L, null, 16744444));
        textView.setTextColor(AbstractC1861K.E(j3));
        textView.setTextSize(1, a1.m.c(lE.f4358a.f4325b));
        textView.setMaxLines(i7);
        int i11 = 2;
        if (!Y0.i.a(i10, 1) && !Y0.i.a(i10, 5)) {
            if (Y0.i.a(i10, 2) || Y0.i.a(i10, 6)) {
                i11 = 3;
            } else if (Y0.i.a(i10, 3)) {
                i11 = 4;
            }
        }
        textView.setTextAlignment(i11);
        if (z6) {
            textView.setMovementMethod(null);
            textView.setLinksClickable(false);
            textView.setLinkTextColor(AbstractC1861K.E(j3));
        }
    }

    public static final void i(String str, String str2, LoadingInterface$LoadingState loadingInterface$LoadingState, InterfaceC2772a interfaceC2772a, C0761q c0761q, int i7) {
        int i10;
        m.f(str, "id");
        m.f(str2, "markdown");
        m.f(loadingInterface$LoadingState, "loadingState");
        m.f(interfaceC2772a, "onRetry");
        c0761q.S(2037860009);
        if ((i7 & 6) == 0) {
            i10 = (c0761q.f(str) ? 4 : 2) | i7;
        } else {
            i10 = i7;
        }
        if ((i7 & 48) == 0) {
            i10 |= c0761q.f(str2) ? 32 : 16;
        }
        if ((i7 & 384) == 0) {
            i10 |= c0761q.f(loadingInterface$LoadingState) ? 256 : 128;
        }
        if ((i7 & 3072) == 0) {
            i10 |= c0761q.h(interfaceC2772a) ? 2048 : 1024;
        }
        if ((i10 & 1171) == 1170 && c0761q.x()) {
            c0761q.K();
        } else {
            AbstractC1274a.h(str, false, true, false, false, b0.g.b(1009541762, new S5.j(loadingInterface$LoadingState, interfaceC2772a, str2, str), c0761q), c0761q, (i10 & 14) | 200064, 18);
        }
        C0754m0 c0754m0R = c0761q.r();
        if (c0754m0R != null) {
            c0754m0R.d = new G6.c(str, (Object) str2, (Object) loadingInterface$LoadingState, interfaceC2772a, i7, 7);
        }
    }

    public static Set i0(c9.u uVar) {
        int size = uVar.size();
        TreeSet treeSet = null;
        for (int i7 = 0; i7 < size; i7++) {
            if ("Vary".equalsIgnoreCase(uVar.l(i7))) {
                String strS = uVar.s(i7);
                if (treeSet == null) {
                    Comparator comparator = String.CASE_INSENSITIVE_ORDER;
                    m.e(comparator, "CASE_INSENSITIVE_ORDER");
                    treeSet = new TreeSet(comparator);
                }
                Iterator it = h.W0(strS, new char[]{','}).iterator();
                while (it.hasNext()) {
                    treeSet.add(h.g1((String) it.next()).toString());
                }
            }
        }
        return treeSet == null ? n7.w.l : treeSet;
    }

    /* JADX WARN: Removed duplicated region for block: B:103:0x0186  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static final void j(final g0.o r27, final java.lang.String r28, final long r29, final long r31, final int r33, final int r34, final N0.L r35, final java.lang.Integer r36, final boolean r37, final z7.InterfaceC2772a r38, T.C0761q r39, final int r40) {
        /*
            Method dump skipped, instruction units count: 1136
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: w0.c.j(g0.o, java.lang.String, long, long, int, int, N0.L, java.lang.Integer, boolean, z7.a, T.q, int):void");
    }

    /* JADX WARN: Removed duplicated region for block: B:102:0x026e  */
    /* JADX WARN: Removed duplicated region for block: B:103:0x0272  */
    /* JADX WARN: Removed duplicated region for block: B:108:0x028d  */
    /* JADX WARN: Removed duplicated region for block: B:112:0x02e9  */
    /* JADX WARN: Removed duplicated region for block: B:114:? A[RETURN, SYNTHETIC] */
    /* JADX WARN: Removed duplicated region for block: B:23:0x004a  */
    /* JADX WARN: Removed duplicated region for block: B:30:0x005a  */
    /* JADX WARN: Removed duplicated region for block: B:37:0x006e  */
    /* JADX WARN: Removed duplicated region for block: B:44:0x0081  */
    /* JADX WARN: Removed duplicated region for block: B:47:0x008f  */
    /* JADX WARN: Removed duplicated region for block: B:50:0x009f  */
    /* JADX WARN: Removed duplicated region for block: B:56:0x00ae  */
    /* JADX WARN: Removed duplicated region for block: B:69:0x00e3  */
    /* JADX WARN: Removed duplicated region for block: B:76:0x010b  */
    /* JADX WARN: Removed duplicated region for block: B:77:0x010d  */
    /* JADX WARN: Removed duplicated region for block: B:81:0x0175  */
    /* JADX WARN: Removed duplicated region for block: B:82:0x0179  */
    /* JADX WARN: Removed duplicated region for block: B:87:0x019a  */
    /* JADX WARN: Removed duplicated region for block: B:90:0x01ec  */
    /* JADX WARN: Removed duplicated region for block: B:91:0x01f0  */
    /* JADX WARN: Removed duplicated region for block: B:96:0x020b  */
    /* JADX WARN: Removed duplicated region for block: B:99:0x0242  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static final void k(final g0.o r32, g0.o r33, final java.lang.String r34, final long r35, final long r37, int r39, int r40, N0.L r41, java.lang.Integer r42, boolean r43, z7.InterfaceC2772a r44, final K6.n r45, final D.L r46, T.C0761q r47, final int r48, final int r49, final int r50) {
        /*
            Method dump skipped, instruction units count: 780
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: w0.c.k(g0.o, g0.o, java.lang.String, long, long, int, int, N0.L, java.lang.Integer, boolean, z7.a, K6.n, D.L, T.q, int, int, int):void");
    }

    public static final void l(o oVar, InterfaceC2772a interfaceC2772a, C0761q c0761q, int i7) {
        int i10;
        c0761q.S(332209181);
        if ((i7 & 6) == 0) {
            i10 = (c0761q.f(oVar) ? 4 : 2) | i7;
        } else {
            i10 = i7;
        }
        if ((i7 & 48) == 0) {
            i10 |= c0761q.h(interfaceC2772a) ? 32 : 16;
        }
        if ((i10 & 19) == 18 && c0761q.x()) {
            c0761q.K();
        } else {
            AbstractC0535v.a(l9.l.W(R.drawable.ic_back, c0761q, 0), null, com.bumptech.glide.c.Q(7, oVar, interfaceC2772a, false), l6.d.c(c0761q).f18696b, c0761q, 48, 0);
        }
        C0754m0 c0754m0R = c0761q.r();
        if (c0754m0R != null) {
            c0754m0R.d = new L0(oVar, interfaceC2772a, i7, 0);
        }
    }

    public static final void m(o oVar, InterfaceC2772a interfaceC2772a, C0761q c0761q, int i7) {
        int i10;
        c0761q.S(-530532551);
        if ((i7 & 6) == 0) {
            i10 = (c0761q.f(oVar) ? 4 : 2) | i7;
        } else {
            i10 = i7;
        }
        if ((i7 & 48) == 0) {
            i10 |= c0761q.h(interfaceC2772a) ? 32 : 16;
        }
        if ((i10 & 19) == 18 && c0761q.x()) {
            c0761q.K();
        } else {
            io.sentry.config.a.o(oVar, null, false, AbstractC2673a.X(R.string.Global_ClearAll, c0761q), l6.d.c(c0761q).f18696b, null, false, androidx.compose.foundation.layout.a.a(m6.c.f19252a.x(c0761q) * 1.5f, 2), ((O5.d) c0761q.k(O5.c.f5157b)).f5158a, h6.f.g(FontSize.Subtitle1, c0761q), 0.0f, interfaceC2772a, c0761q, i10 & 14, i10 & 112, 1126);
        }
        C0754m0 c0754m0R = c0761q.r();
        if (c0754m0R != null) {
            c0754m0R.d = new L0(oVar, interfaceC2772a, i7, 1);
        }
    }

    public static final void n(o oVar, InterfaceC2772a interfaceC2772a, C0761q c0761q, int i7) {
        int i10;
        c0761q.S(563169518);
        if ((i7 & 6) == 0) {
            i10 = (c0761q.f(oVar) ? 4 : 2) | i7;
        } else {
            i10 = i7;
        }
        if ((i7 & 48) == 0) {
            i10 |= c0761q.h(interfaceC2772a) ? 32 : 16;
        }
        if ((i10 & 19) == 18 && c0761q.x()) {
            c0761q.K();
        } else {
            AbstractC0535v.a(l9.l.W(R.drawable.ic_close, c0761q, 0), null, com.bumptech.glide.c.Q(7, oVar, interfaceC2772a, false), l6.d.c(c0761q).f18696b, c0761q, 48, 0);
        }
        C0754m0 c0754m0R = c0761q.r();
        if (c0754m0R != null) {
            c0754m0R.d = new L0(oVar, interfaceC2772a, i7, 3);
        }
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:84:0x016d  */
    /* JADX WARN: Removed duplicated region for block: B:85:0x0171  */
    /* JADX WARN: Removed duplicated region for block: B:88:0x0180  */
    /* JADX WARN: Removed duplicated region for block: B:90:0x018e  */
    /* JADX WARN: Type inference failed for: r10v17, types: [boolean, int] */
    /* JADX WARN: Type inference failed for: r10v21 */
    /* JADX WARN: Type inference failed for: r10v22 */
    /* JADX WARN: Type inference failed for: r10v23 */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public static final void o(final java.util.List r21, final boolean r22, final boolean r23, T.C0761q r24, final int r25) {
        /*
            Method dump skipped, instruction units count: 536
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: w0.c.o(java.util.List, boolean, boolean, T.q, int):void");
    }

    public static final void p(o oVar, InterfaceC2772a interfaceC2772a, C0761q c0761q, int i7) {
        int i10;
        c0761q.S(-176601658);
        if ((i7 & 6) == 0) {
            i10 = (c0761q.f(oVar) ? 4 : 2) | i7;
        } else {
            i10 = i7;
        }
        if ((i7 & 48) == 0) {
            i10 |= c0761q.h(interfaceC2772a) ? 32 : 16;
        }
        if ((i10 & 19) == 18 && c0761q.x()) {
            c0761q.K();
        } else {
            AbstractC0535v.a(l9.l.W(R.drawable.ic_down, c0761q, 0), null, com.bumptech.glide.c.Q(7, oVar, interfaceC2772a, false), l6.d.c(c0761q).f18696b, c0761q, 48, 0);
        }
        C0754m0 c0754m0R = c0761q.r();
        if (c0754m0R != null) {
            c0754m0R.d = new L0(oVar, interfaceC2772a, i7, 2);
        }
    }

    public static final void q(int i7, C0761q c0761q, o oVar, InterfaceC2772a interfaceC2772a, boolean z6) {
        int i10;
        long j3;
        c0761q.S(875593286);
        if ((i7 & 6) == 0) {
            i10 = (c0761q.f(oVar) ? 4 : 2) | i7;
        } else {
            i10 = i7;
        }
        if ((i7 & 48) == 0) {
            i10 |= c0761q.h(interfaceC2772a) ? 32 : 16;
        }
        if ((i7 & 384) == 0) {
            i10 |= c0761q.g(z6) ? 256 : 128;
        }
        if ((i10 & 147) == 146 && c0761q.x()) {
            c0761q.K();
        } else {
            if (z6) {
                c0761q.Q(-337592676);
                j3 = l6.d.c(c0761q).f18696b;
            } else {
                c0761q.Q(-337591812);
                j3 = l6.d.c(c0761q).f18700e;
            }
            c0761q.p(false);
            c0761q.Q(-337588858);
            boolean z10 = ((i10 & 896) == 256) | ((i10 & 112) == 32);
            Object objG = c0761q.G();
            if (z10 || objG == C0753m.f8270a) {
                objG = new M0(z6, interfaceC2772a, 0);
                c0761q.a0(objG);
            }
            c0761q.p(false);
            AbstractC0535v.a(l9.l.W(R.drawable.ic_list, c0761q, 0), null, com.bumptech.glide.c.Q(7, oVar, (InterfaceC2772a) objG, false), j3, c0761q, 48, 0);
        }
        C0754m0 c0754m0R = c0761q.r();
        if (c0754m0R != null) {
            c0754m0R.d = new N0(i7, oVar, interfaceC2772a, z6);
        }
    }

    public static final void r(o oVar, String str, C0761q c0761q, int i7) {
        int i10;
        c0761q.S(309670957);
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
        } else {
            c1.b(str, oVar, l6.d.c(c0761q).c, h6.f.g(FontSize.Body2, c0761q), null, null, null, 0L, null, new Y0.i(3), 0L, 0, false, 0, 0, null, ((O5.d) c0761q.k(O5.c.f5157b)).f5158a, c0761q, ((i10 >> 3) & 14) | ((i10 << 3) & 112), 0, 65008);
        }
        C0754m0 c0754m0R = c0761q.r();
        if (c0754m0R != null) {
            c0754m0R.d = new T0(oVar, str, i7, 0);
        }
    }

    public static final void s(o oVar, String str, long j3, N0.L l, C0761q c0761q, int i7) {
        int i10;
        c0761q.S(-1338521262);
        if ((i7 & 6) == 0) {
            i10 = (c0761q.f(oVar) ? 4 : 2) | i7;
        } else {
            i10 = i7;
        }
        if ((i7 & 48) == 0) {
            i10 |= c0761q.f(str) ? 32 : 16;
        }
        if ((i7 & 384) == 0) {
            i10 |= c0761q.e(j3) ? 256 : 128;
        }
        if ((i7 & 3072) == 0) {
            i10 |= c0761q.f(l) ? 2048 : 1024;
        }
        if ((i10 & 1171) == 1170 && c0761q.x()) {
            c0761q.K();
        } else {
            c0761q.M();
            if ((i7 & 1) != 0 && !c0761q.w()) {
                c0761q.K();
            }
            c0761q.q();
            c1.b(str, oVar, j3, h6.f.g(FontSize.H10, c0761q), null, null, null, 0L, null, new Y0.i(3), 0L, 2, false, 1, 0, null, l, c0761q, ((i10 >> 3) & 14) | ((i10 << 3) & 112) | (i10 & 896), ((i10 << 9) & 3670016) | 3120, 54768);
        }
        C0754m0 c0754m0R = c0761q.r();
        if (c0754m0R != null) {
            c0754m0R.d = new K0(oVar, str, j3, l, i7, 0);
        }
    }

    public static final void t(o oVar, String str, N0.L l, C0761q c0761q, int i7) {
        int i10;
        c0761q.S(1963108089);
        if ((i7 & 6) == 0) {
            i10 = (c0761q.f(oVar) ? 4 : 2) | i7;
        } else {
            i10 = i7;
        }
        if ((i7 & 48) == 0) {
            i10 |= c0761q.f(str) ? 32 : 16;
        }
        if ((i7 & 384) == 0) {
            i10 |= c0761q.f(l) ? 256 : 128;
        }
        if ((i10 & 147) == 146 && c0761q.x()) {
            c0761q.K();
        } else {
            c0761q.M();
            if ((i7 & 1) != 0 && !c0761q.w()) {
                c0761q.K();
            }
            c0761q.q();
            c1.b(str, oVar, l6.d.c(c0761q).f18696b, h6.f.g(FontSize.f13042H8, c0761q), null, null, null, 0L, null, new Y0.i(3), 0L, 2, false, 1, 0, null, l, c0761q, ((i10 >> 3) & 14) | ((i10 << 3) & 112), ((i10 << 12) & 3670016) | 3120, 54768);
        }
        C0754m0 c0754m0R = c0761q.r();
        if (c0754m0R != null) {
            c0754m0R.d = new C0641y0(i7, 1, oVar, str, l);
        }
    }

    public static final void u(o oVar, C0595m1 c0595m1, C0761q c0761q, int i7) {
        int i10;
        c0761q.S(-2126948458);
        if ((i7 & 6) == 0) {
            i10 = (c0761q.f(oVar) ? 4 : 2) | i7;
        } else {
            i10 = i7;
        }
        if ((i7 & 48) == 0) {
            i10 |= c0761q.h(c0595m1) ? 32 : 16;
        }
        if ((i10 & 19) == 18 && c0761q.x()) {
            c0761q.K();
        } else {
            String strX = AbstractC2673a.X(R.string.Global_SingAll, c0761q);
            long jG = h6.f.g(FontSize.Subtitle1, c0761q);
            m6.b bVar = m6.c.f19252a;
            float fO0 = bVar.o0(c0761q);
            float fX = bVar.x(c0761q);
            float fC = bVar.C(c0761q) * 0.75f;
            io.sentry.config.a.p(oVar, Integer.valueOf(R.drawable.ic_play), false, strX, 0L, null, new L(fX, fC, fX, fC), jG, fO0, c0595m1, c0761q, (i10 & 14) | ((i10 << 24) & 1879048192), 52);
        }
        C0754m0 c0754m0R = c0761q.r();
        if (c0754m0R != null) {
            c0754m0R.d = new M(oVar, c0595m1, i7, 1);
        }
    }

    public static final void v(final o oVar, final boolean z6, final String str, final InterfaceC2772a interfaceC2772a, final InterfaceC2772a interfaceC2772a2, final boolean z10, final boolean z11, C0761q c0761q, final int i7) {
        int i10;
        m.f(str, "companionUrl");
        m.f(interfaceC2772a, "onDismissNotification");
        m.f(interfaceC2772a2, "onPassengerIconClick");
        c0761q.S(331766759);
        if ((i7 & 6) == 0) {
            i10 = (c0761q.f(oVar) ? 4 : 2) | i7;
        } else {
            i10 = i7;
        }
        if ((i7 & 48) == 0) {
            i10 |= c0761q.g(z6) ? 32 : 16;
        }
        if ((i7 & 384) == 0) {
            i10 |= c0761q.f(str) ? 256 : 128;
        }
        if ((i7 & 3072) == 0) {
            i10 |= c0761q.h(interfaceC2772a) ? 2048 : 1024;
        }
        if ((i7 & 24576) == 0) {
            i10 |= c0761q.h(interfaceC2772a2) ? 16384 : 8192;
        }
        if ((196608 & i7) == 0) {
            i10 |= c0761q.g(z10) ? 131072 : 65536;
        }
        if ((1572864 & i7) == 0) {
            i10 |= c0761q.g(z11) ? 1048576 : 524288;
        }
        int i11 = i10;
        if ((599187 & i11) == 599186 && c0761q.x()) {
            c0761q.K();
        } else {
            androidx.compose.animation.a.c(z6, oVar, AbstractC2565A.b(null, 3), AbstractC2565A.c(null, 3), null, b0.g.b(-1974580033, new C0869l(str, interfaceC2772a, z11, z10, interfaceC2772a2), c0761q), c0761q, ((i11 >> 3) & 14) | 200064 | ((i11 << 3) & 112), 16);
        }
        C0754m0 c0754m0R = c0761q.r();
        if (c0754m0R != null) {
            c0754m0R.d = new InterfaceC2785n() { // from class: X5.k
                @Override // z7.InterfaceC2785n
                public final Object invoke(Object obj, Object obj2) {
                    ((Integer) obj2).getClass();
                    int iZ = C0735d.Z(i7 | 1);
                    boolean z12 = z10;
                    boolean z13 = z11;
                    w0.c.v(oVar, z6, str, interfaceC2772a, interfaceC2772a2, z12, z13, (C0761q) obj, iZ);
                    return m7.q.f19267a;
                }
            };
        }
    }

    public static final void w(o oVar, String str, String str2, String str3, boolean z6, boolean z10, boolean z11, boolean z12, InterfaceC2772a interfaceC2772a, InterfaceC2772a interfaceC2772a2, boolean z13, C0761q c0761q, int i7, int i10) {
        int i11;
        String str4;
        boolean z14;
        m.f(interfaceC2772a, "onMinimizePressed");
        c0761q.S(2130945390);
        if ((i7 & 6) == 0) {
            i11 = (c0761q.f(oVar) ? 4 : 2) | i7;
        } else {
            i11 = i7;
        }
        if ((i7 & 48) == 0) {
            i11 |= c0761q.f(str) ? 32 : 16;
        }
        if ((i7 & 384) == 0) {
            i11 |= c0761q.f(str2) ? 256 : 128;
        }
        int i12 = i11 | 3072;
        if ((i7 & 24576) == 0) {
            i12 |= c0761q.g(z6) ? 16384 : 8192;
        }
        if ((196608 & i7) == 0) {
            i12 |= c0761q.g(z10) ? 131072 : 65536;
        }
        if ((1572864 & i7) == 0) {
            i12 |= c0761q.g(z11) ? 1048576 : 524288;
        }
        if ((12582912 & i7) == 0) {
            i12 |= c0761q.g(z12) ? 8388608 : 4194304;
        }
        if ((100663296 & i7) == 0) {
            i12 |= c0761q.h(interfaceC2772a) ? 67108864 : 33554432;
        }
        if ((805306368 & i7) == 0) {
            i12 |= c0761q.h(interfaceC2772a2) ? 536870912 : 268435456;
        }
        int i13 = i10 | 6;
        if ((306783379 & i12) == 306783378 && (i13 & 3) == 2 && c0761q.x()) {
            c0761q.K();
            str4 = str3;
            z14 = z13;
        } else {
            ArrayList arrayList = new ArrayList();
            arrayList.add(new C0569g(X0.o, interfaceC2772a, true));
            ArrayList arrayList2 = new ArrayList();
            if (interfaceC2772a2 != null) {
                arrayList2.add(new C0569g(X0.f6643p, interfaceC2772a2, !z12));
            }
            R0 r02 = O5.c.f5157b;
            int i14 = i12 >> 9;
            d(oVar, z6, z10, z11, str, ((O5.d) c0761q.k(r02)).d, str2, l6.d.c(c0761q).f18696b, ((O5.d) c0761q.k(r02)).f5159b, null, arrayList, arrayList2, true, c0761q, (i14 & 7168) | (i14 & 896) | (i12 & 14) | (i14 & 112) | (57344 & (i12 << 9)) | ((i12 << 12) & 3670016) | ((i12 << 18) & 1879048192), (i13 << 6) & 896, 0);
            str4 = null;
            z14 = true;
        }
        C0754m0 c0754m0R = c0761q.r();
        if (c0754m0R != null) {
            c0754m0R.d = new Q5.R0(oVar, str, str2, str4, z6, z10, z11, z12, interfaceC2772a, interfaceC2772a2, z14, i7, i10);
        }
    }

    public static final void x(o oVar, boolean z6, InterfaceC2772a interfaceC2772a, boolean z10, FontSize fontSize, C0761q c0761q, int i7) {
        int i10;
        m.f(interfaceC2772a, "onPassengerClick");
        c0761q.S(-930626391);
        if ((i7 & 6) == 0) {
            i10 = (c0761q.f(oVar) ? 4 : 2) | i7;
        } else {
            i10 = i7;
        }
        if ((i7 & 48) == 0) {
            i10 |= c0761q.g(z6) ? 32 : 16;
        }
        if ((i7 & 384) == 0) {
            i10 |= c0761q.h(interfaceC2772a) ? 256 : 128;
        }
        if ((i7 & 3072) == 0) {
            i10 |= c0761q.g(z10) ? 2048 : 1024;
        }
        if ((i7 & 24576) == 0) {
            i10 |= c0761q.f(fontSize) ? 16384 : 8192;
        }
        if ((i10 & 9363) == 9362 && c0761q.x()) {
            c0761q.K();
        } else {
            D.S sB = P.b(AbstractC0127i.f1077b, g0.b.f14670v, c0761q, 54);
            int i11 = c0761q.f8304P;
            InterfaceC0744h0 interfaceC0744h0M = c0761q.m();
            o oVarD = g0.a.d(c0761q, oVar);
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
            if (c0761q.f8303O || !m.a(c0761q.G(), Integer.valueOf(i11))) {
                AbstractC1347d.l(i11, c0761q, i11, c0158h);
            }
            C0735d.V(c0761q, oVarD, C0160j.c);
            y(z6, interfaceC2772a, z10, fontSize, c0761q, (i10 >> 3) & 8190);
            c0761q.p(true);
        }
        C0754m0 c0754m0R = c0761q.r();
        if (c0754m0R != null) {
            c0754m0R.d = new Q5.A0(oVar, z6, interfaceC2772a, z10, fontSize, i7);
        }
    }

    public static final void y(boolean z6, InterfaceC2772a interfaceC2772a, boolean z10, FontSize fontSize, C0761q c0761q, int i7) {
        int i10;
        long j3;
        c0761q.S(1846861850);
        if ((i7 & 6) == 0) {
            i10 = (c0761q.g(z6) ? 4 : 2) | i7;
        } else {
            i10 = i7;
        }
        if ((i7 & 48) == 0) {
            i10 |= c0761q.h(interfaceC2772a) ? 32 : 16;
        }
        if ((i7 & 384) == 0) {
            i10 |= c0761q.g(z10) ? 256 : 128;
        }
        if ((i7 & 3072) == 0) {
            i10 |= c0761q.f(fontSize) ? 2048 : 1024;
        }
        if ((i10 & 1171) == 1170 && c0761q.x()) {
            c0761q.K();
        } else {
            if (z10) {
                c0761q.Q(1657446362);
                j3 = l6.d.c(c0761q).f18696b;
            } else {
                c0761q.Q(1657447226);
                j3 = l6.d.c(c0761q).f18700e;
            }
            c0761q.p(false);
            long j10 = j3;
            String strX = AbstractC2673a.X(R.string.Global_Passenger, c0761q);
            c0761q.Q(1657455480);
            boolean z11 = ((i10 & 896) == 256) | ((i10 & 112) == 32);
            Object objG = c0761q.G();
            if (z11 || objG == C0753m.f8270a) {
                objG = new M0(z10, interfaceC2772a, 2);
                c0761q.a0(objG);
            }
            c0761q.p(false);
            io.sentry.config.a.a(null, false, R.drawable.ic_mobile, j10, j10, false, strX, z6, 0.0f, 0.0f, fontSize, (InterfaceC2772a) objG, c0761q, (i10 << 21) & 29360128, (i10 >> 9) & 14, 803);
        }
        C0754m0 c0754m0R = c0761q.r();
        if (c0754m0R != null) {
            c0754m0R.d = new C0616s(z6, interfaceC2772a, z10, fontSize, i7);
        }
    }

    public static final void z(final o oVar, long j3, C0761q c0761q, final int i7) {
        int i10;
        final long j10;
        int i11;
        X x8;
        boolean z6;
        c0761q.S(-836408561);
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
        int i12 = i10 | 3072;
        if ((i12 & 1171) == 1170 && c0761q.x()) {
            c0761q.K();
            j10 = j3;
        } else {
            j10 = l6.e.f18746J0;
            c0761q.Q(-767707957);
            Object objG = c0761q.G();
            S s6 = C0753m.f8270a;
            if (objG == s6) {
                objG = C0735d.O(new a1.j(H.c(0, 0)), S.f8224q);
                c0761q.a0(objG);
            }
            X x9 = (X) objG;
            c0761q.p(false);
            a1.b bVar = (a1.b) c0761q.k(AbstractC0227s0.f2259f);
            K kE = AbstractC0133o.e(g0.b.l, false);
            int i13 = c0761q.f8304P;
            InterfaceC0744h0 interfaceC0744h0M = c0761q.m();
            o oVarD = g0.a.d(c0761q, oVar);
            InterfaceC0161k.f1681b.getClass();
            C0159i c0159i = C0160j.f1678b;
            c0761q.U();
            if (c0761q.f8303O) {
                c0761q.l(c0159i);
            } else {
                c0761q.d0();
            }
            C0735d.V(c0761q, kE, C0160j.f1679e);
            C0735d.V(c0761q, interfaceC0744h0M, C0160j.d);
            C0158h c0158h = C0160j.f1680f;
            if (c0761q.f8303O || !m.a(c0761q.G(), Integer.valueOf(i13))) {
                AbstractC1347d.l(i13, c0761q, i13, c0158h);
            }
            C0735d.V(c0761q, oVarD, C0160j.c);
            androidx.compose.foundation.layout.b bVar2 = androidx.compose.foundation.layout.b.f11474a;
            c0761q.Q(370249828);
            int i14 = (int) (((a1.j) x9.getValue()).f11037a >> 32);
            g0.g gVar = g0.b.f14664p;
            l lVar = l.f14684a;
            if (i14 > 0) {
                i11 = i12;
                float fP0 = bVar.p0((int) (((a1.j) x9.getValue()).f11037a >> 32));
                x8 = x9;
                float fP02 = bVar.p0((int) (((a1.j) x9.getValue()).f11037a & 4294967295L));
                float f10 = (2 * fP0) / fP02;
                o oVarA = androidx.compose.foundation.a.a(r.r(androidx.compose.foundation.layout.a.i(androidx.compose.foundation.layout.c.e(androidx.compose.foundation.layout.c.o(bVar2.a(lVar, gVar), fP0), 0.8f * fP02), 0.0f, fP02 * 0.35f, 1), Math.max(f10, 1.0f), Math.max(1 / f10, 1.0f)), new C1860J(AbstractC1999n.D(new C1891t(j10), new C1891t(C1891t.f19451j)), 9205357640488583168L, Float.POSITIVE_INFINITY, 0), null, 6);
                z6 = false;
                AbstractC0133o.a(oVarA, c0761q, 0);
            } else {
                i11 = i12;
                x8 = x9;
                z6 = false;
            }
            c0761q.p(z6);
            if (((Boolean) c0761q.k(D0.f2013a)).booleanValue()) {
                c0761q.Q(-1406267053);
                o oVarA2 = bVar2.a(lVar, gVar);
                c0761q.Q(370282817);
                Object objG2 = c0761q.G();
                if (objG2 == s6) {
                    objG2 = new R5.d(x8, 1);
                    c0761q.a0(objG2);
                }
                c0761q.p(false);
                o oVarD2 = androidx.compose.ui.layout.a.d(oVarA2, (InterfaceC2782k) objG2);
                int i15 = i11 >> 3;
                h6.q.a(l9.l.W(R.raw.background_trophy, c0761q, i15 & 14), "Trophy bird", oVarD2, null, C0102i.f903b, 0.0f, null, c0761q, (i15 & 112) | 24576, 104);
                c0761q.p(false);
            } else {
                X x10 = x8;
                c0761q.Q(-1405835409);
                o oVarA3 = bVar2.a(lVar, gVar);
                c0761q.Q(370297057);
                Object objG3 = c0761q.G();
                if (objG3 == s6) {
                    objG3 = new R5.d(x10, 2);
                    c0761q.a0(objG3);
                }
                c0761q.p(false);
                l9.l.a(androidx.compose.ui.layout.a.d(oVarA3, (InterfaceC2782k) objG3), c0761q, i11 & 1008);
                c0761q.p(false);
            }
            c0761q.p(true);
        }
        C0754m0 c0754m0R = c0761q.r();
        if (c0754m0R != null) {
            c0754m0R.d = new InterfaceC2785n() { // from class: U5.F
                @Override // z7.InterfaceC2785n
                public final Object invoke(Object obj, Object obj2) {
                    ((Integer) obj2).getClass();
                    int iZ = C0735d.Z(i7 | 1);
                    w0.c.z(oVar, j10, (C0761q) obj, iZ);
                    return m7.q.f19267a;
                }
            };
        }
    }
}
