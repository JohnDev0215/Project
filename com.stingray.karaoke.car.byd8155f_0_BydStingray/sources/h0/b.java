package H0;

import A.I0;
import A7.m;
import A9.k;
import B5.K;
import B8.D;
import B8.InterfaceC0089a;
import B8.InterfaceC0091c;
import B8.t;
import B8.u;
import B8.v;
import E8.l;
import F8.AbstractC0287w;
import N0.q;
import N7.s;
import P.C;
import Q7.AbstractC0669w;
import Q7.InterfaceC0671y;
import Q7.N;
import T.C0746i0;
import T7.z;
import V7.c;
import W7.AbstractC0839c;
import Z7.w;
import a.AbstractC0904a;
import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.PorterDuff;
import android.graphics.Shader;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.LayerDrawable;
import android.media.MediaCrypto;
import android.media.MediaFormat;
import android.os.Bundle;
import android.util.SparseArray;
import android.util.SparseBooleanArray;
import android.view.Menu;
import android.view.Surface;
import c9.C1046g;
import com.stingray.drivemodedetector.R;
import d5.C1078G;
import d5.I;
import d5.d0;
import d5.i0;
import e2.C1208p;
import e2.S;
import e2.W;
import e2.c0;
import e2.f0;
import g1.AbstractC1274a;
import h2.AbstractC1343A;
import h3.y;
import h8.AbstractC1386c;
import h8.C1384a;
import h8.C1385b;
import h8.C1389f;
import h8.C1391h;
import h8.o;
import h8.p;
import j2.C1538a;
import j8.C1562g;
import j8.C1567l;
import j8.C1574t;
import j8.C1579y;
import j8.EnumC1564i;
import j8.G;
import j8.Q;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.IOException;
import java.lang.annotation.Annotation;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import k3.A1;
import k3.q1;
import k3.w1;
import k3.z1;
import l8.AbstractC1787e;
import l8.InterfaceC1788f;
import m0.d;
import m7.i;
import n7.AbstractC1985B;
import n7.AbstractC1999n;
import n7.AbstractC2000o;
import n8.e;
import n8.f;
import o8.C2057b;
import o8.C2058c;
import o8.C2061f;
import p8.AbstractC2136k;
import p8.C2132g;
import p8.C2138m;
import r.C2258q;
import r.l0;
import r.q0;
import s2.n;
import t8.C2451d;
import t8.g;
import t8.h;
import t8.j;
import t8.x;
import u2.C2492C;
import w8.C2608c;
import x.AbstractC2641j;
import x3.AbstractC2673a;
import z7.InterfaceC2772a;
import z7.InterfaceC2785n;

/* JADX INFO: loaded from: classes.dex */
public final class b implements InterfaceC0089a, InterfaceC0091c {
    public final /* synthetic */ int l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final Object f2765m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public Object f2766n;
    public Object o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public Object f2767p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public Object f2768q;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public Object f2769r;

    public b(I0 i02) {
        this.l = 0;
        d dVar = d.f18881e;
        this.f2765m = i02;
        this.f2769r = dVar;
        this.f2766n = null;
        this.o = null;
        this.f2767p = null;
        this.f2768q = null;
    }

    public static LayerDrawable A(l0 l0Var, Context context, int i7) {
        BitmapDrawable bitmapDrawable;
        BitmapDrawable bitmapDrawable2;
        BitmapDrawable bitmapDrawable3;
        int dimensionPixelSize = context.getResources().getDimensionPixelSize(i7);
        Drawable drawableC = l0Var.c(context, R.drawable.abc_star_black_48dp);
        Drawable drawableC2 = l0Var.c(context, R.drawable.abc_star_half_black_48dp);
        if ((drawableC instanceof BitmapDrawable) && drawableC.getIntrinsicWidth() == dimensionPixelSize && drawableC.getIntrinsicHeight() == dimensionPixelSize) {
            bitmapDrawable = (BitmapDrawable) drawableC;
            bitmapDrawable2 = new BitmapDrawable(bitmapDrawable.getBitmap());
        } else {
            Bitmap bitmapCreateBitmap = Bitmap.createBitmap(dimensionPixelSize, dimensionPixelSize, Bitmap.Config.ARGB_8888);
            Canvas canvas = new Canvas(bitmapCreateBitmap);
            drawableC.setBounds(0, 0, dimensionPixelSize, dimensionPixelSize);
            drawableC.draw(canvas);
            bitmapDrawable = new BitmapDrawable(bitmapCreateBitmap);
            bitmapDrawable2 = new BitmapDrawable(bitmapCreateBitmap);
        }
        bitmapDrawable2.setTileModeX(Shader.TileMode.REPEAT);
        if ((drawableC2 instanceof BitmapDrawable) && drawableC2.getIntrinsicWidth() == dimensionPixelSize && drawableC2.getIntrinsicHeight() == dimensionPixelSize) {
            bitmapDrawable3 = (BitmapDrawable) drawableC2;
        } else {
            Bitmap bitmapCreateBitmap2 = Bitmap.createBitmap(dimensionPixelSize, dimensionPixelSize, Bitmap.Config.ARGB_8888);
            Canvas canvas2 = new Canvas(bitmapCreateBitmap2);
            drawableC2.setBounds(0, 0, dimensionPixelSize, dimensionPixelSize);
            drawableC2.draw(canvas2);
            bitmapDrawable3 = new BitmapDrawable(bitmapCreateBitmap2);
        }
        LayerDrawable layerDrawable = new LayerDrawable(new Drawable[]{bitmapDrawable, bitmapDrawable3, bitmapDrawable2});
        layerDrawable.setId(0, android.R.id.background);
        layerDrawable.setId(1, android.R.id.secondaryProgress);
        layerDrawable.setId(2, android.R.id.progress);
        return layerDrawable;
    }

    public static boolean F(C2492C c2492c, Object obj, boolean z6, int i7, int i10, int i11) {
        if (!c2492c.f22663a.equals(obj)) {
            return false;
        }
        int i12 = c2492c.f22664b;
        return (z6 && i12 == i7 && c2492c.c == i10) || (!z6 && i12 == -1 && c2492c.f22665e == i11);
    }

    public static void M(Drawable drawable, int i7, PorterDuff.Mode mode) {
        Drawable drawableMutate = drawable.mutate();
        if (mode == null) {
            mode = C2258q.f21546b;
        }
        drawableMutate.setColorFilter(C2258q.b(i7, mode));
    }

    public static c O(u uVar) {
        N n10 = (N) uVar.d;
        o oVar = n10 instanceof o ? (o) n10 : null;
        if (oVar != null) {
            return oVar.l;
        }
        return null;
    }

    public static l2.o a(DataInputStream dataInputStream) throws IOException {
        int i7 = dataInputStream.readInt();
        HashMap map = new HashMap();
        for (int i10 = 0; i10 < i7; i10++) {
            String utf = dataInputStream.readUTF();
            int i11 = dataInputStream.readInt();
            if (i11 < 0) {
                throw new IOException(android.hardware.bydauto.ac.tsimpl.a.e(i11, "Invalid value size: "));
            }
            int iMin = Math.min(i11, 10485760);
            byte[] bArrCopyOf = AbstractC1343A.f15198f;
            int i12 = 0;
            while (i12 != i11) {
                int i13 = i12 + iMin;
                bArrCopyOf = Arrays.copyOf(bArrCopyOf, i13);
                dataInputStream.readFully(bArrCopyOf, i12, iMin);
                iMin = Math.min(i11 - i13, 10485760);
                i12 = i13;
            }
            map.put(utf, bArrCopyOf);
        }
        return new l2.o(map);
    }

    public static void c(l2.o oVar, DataOutputStream dataOutputStream) throws IOException {
        Set<Map.Entry> setEntrySet = oVar.f18491b.entrySet();
        dataOutputStream.writeInt(setEntrySet.size());
        for (Map.Entry entry : setEntrySet) {
            dataOutputStream.writeUTF((String) entry.getKey());
            byte[] bArr = (byte[]) entry.getValue();
            dataOutputStream.writeInt(bArr.length);
            dataOutputStream.write(bArr);
        }
    }

    public static final g d(b bVar, C2061f c2061f, Object obj) {
        g gVarB = h.f22500a.b(obj, (z) bVar.o);
        if (gVarB != null) {
            return gVarB;
        }
        String str = "Unsupported annotation argument: " + c2061f;
        m.f(str, "message");
        return new j(str);
    }

    public static void h(int i7, Menu menu) {
        int i10;
        int iC = AbstractC2641j.c(i7);
        int iC2 = AbstractC2641j.c(i7);
        if (iC2 == 0) {
            i10 = android.R.string.copy;
        } else if (iC2 == 1) {
            i10 = android.R.string.paste;
        } else if (iC2 == 2) {
            i10 = android.R.string.cut;
        } else {
            if (iC2 != 3) {
                throw new k();
            }
            i10 = android.R.string.selectAll;
        }
        menu.add(0, iC, AbstractC2641j.c(i7), i10).setShowAsAction(1);
    }

    public static void j(Menu menu, int i7, InterfaceC2772a interfaceC2772a) {
        if (interfaceC2772a != null && menu.findItem(AbstractC2641j.c(i7)) == null) {
            h(i7, menu);
        } else {
            if (interfaceC2772a != null || menu.findItem(AbstractC2641j.c(i7)) == null) {
                return;
            }
            menu.removeItem(AbstractC2641j.c(i7));
        }
    }

    public static boolean l(int[] iArr, int i7) {
        for (int i10 : iArr) {
            if (i10 == i7) {
                return true;
            }
        }
        return false;
    }

    public static ColorStateList p(Context context, int i7) {
        int iC = q0.c(context, R.attr.colorControlHighlight);
        int iB = q0.b(context, R.attr.colorButtonNormal);
        int[] iArr = q0.f21549b;
        int[] iArr2 = q0.d;
        int iF = y1.a.f(iC, i7);
        return new ColorStateList(new int[][]{iArr, iArr2, q0.c, q0.f21551f}, new int[]{iB, iF, y1.a.f(iC, i7), i7});
    }

    public static /* synthetic */ List s(b bVar, K k10, p pVar, boolean z6, Boolean bool, boolean z10, int i7) {
        boolean z11 = (i7 & 4) != 0 ? false : z6;
        if ((i7 & 16) != 0) {
            bool = null;
        }
        return bVar.q(k10, pVar, z11, false, bool, (i7 & 32) != 0 ? false : z10);
    }

    public static C2492C v(W w10, I i7, C2492C c2492c, c0 c0Var) {
        f0 f0VarT0 = w10.t0();
        int iQ = w10.Q();
        Object objL = f0VarT0.p() ? null : f0VarT0.l(iQ);
        int iB = (w10.s() || f0VarT0.p()) ? -1 : f0VarT0.f(iQ, c0Var, false).b(AbstractC1343A.H(w10.N0()) - c0Var.f14061e);
        for (int i10 = 0; i10 < i7.size(); i10++) {
            C2492C c2492c2 = (C2492C) i7.get(i10);
            if (F(c2492c2, objL, w10.s(), w10.a0(), w10.k0(), iB)) {
                return c2492c2;
            }
        }
        if (i7.isEmpty() && c2492c != null) {
            if (F(c2492c, objL, w10.s(), w10.a0(), w10.k0(), iB)) {
                return c2492c;
            }
        }
        return null;
    }

    public static p y(AbstractC2136k abstractC2136k, InterfaceC1788f interfaceC1788f, C0746i0 c0746i0, int i7, boolean z6) {
        m.f(abstractC2136k, "proto");
        m.f(interfaceC1788f, "nameResolver");
        m.f(c0746i0, "typeTable");
        A7.k.p(i7, "kind");
        if (abstractC2136k instanceof C1567l) {
            C2132g c2132g = n8.h.f20171a;
            e eVarA = n8.h.a((C1567l) abstractC2136k, interfaceC1788f, c0746i0);
            if (eVarA == null) {
                return null;
            }
            return AbstractC1985B.e(eVarA);
        }
        if (abstractC2136k instanceof C1579y) {
            C2132g c2132g2 = n8.h.f20171a;
            e eVarC = n8.h.c((C1579y) abstractC2136k, interfaceC1788f, c0746i0);
            if (eVarC == null) {
                return null;
            }
            return AbstractC1985B.e(eVarC);
        }
        if (!(abstractC2136k instanceof G)) {
            return null;
        }
        C2138m c2138m = m8.k.d;
        m.e(c2138m, "propertySignature");
        m8.e eVar = (m8.e) AbstractC1386c.h(abstractC2136k, c2138m);
        if (eVar == null) {
            return null;
        }
        int iC = AbstractC2641j.c(i7);
        if (iC == 1) {
            return AbstractC1386c.j((G) abstractC2136k, interfaceC1788f, c0746i0, true, true, z6);
        }
        if (iC == 2) {
            if ((eVar.f19291m & 4) != 4) {
                return null;
            }
            m8.c cVar = eVar.f19293p;
            m.e(cVar, "signature.getter");
            return new p(interfaceC1788f.n(cVar.f19281n).concat(interfaceC1788f.n(cVar.o)));
        }
        if (iC != 3 || (eVar.f19291m & 8) != 8) {
            return null;
        }
        m8.c cVar2 = eVar.f19294q;
        m.e(cVar2, "signature.setter");
        return new p(interfaceC1788f.n(cVar2.f19281n).concat(interfaceC1788f.n(cVar2.o)));
    }

    public c B(K k10, boolean z6, boolean z10, Boolean bool, boolean z11) {
        u uVar;
        m.f(k10, "container");
        EnumC1564i enumC1564i = EnumC1564i.INTERFACE;
        V7.b bVar = (V7.b) this.f2765m;
        N n10 = (N) k10.d;
        if (z6) {
            if (bool == null) {
                throw new IllegalStateException(("isConst should not be null for property (container=" + k10 + ')').toString());
            }
            if (k10 instanceof u) {
                u uVar2 = (u) k10;
                if (uVar2.f813h == enumC1564i) {
                    return n7.z.f(bVar, uVar2.f812g.d(C2061f.e("DefaultImpls")), (f) this.f2769r);
                }
            }
            if (bool.booleanValue() && (k10 instanceof v)) {
                C1391h c1391h = n10 instanceof C1391h ? (C1391h) n10 : null;
                C2608c c2608c = c1391h != null ? c1391h.f15611m : null;
                if (c2608c != null) {
                    String strE = c2608c.e();
                    m.e(strE, "facadeClassName.internalName");
                    return n7.z.f(bVar, C2057b.j(new C2058c(Q8.o.w0(strE, '/', '.'))), (f) this.f2769r);
                }
            }
        }
        if (z10 && (k10 instanceof u)) {
            u uVar3 = (u) k10;
            if (uVar3.f813h == EnumC1564i.COMPANION_OBJECT && (uVar = uVar3.f811f) != null) {
                EnumC1564i enumC1564i2 = EnumC1564i.CLASS;
                EnumC1564i enumC1564i3 = uVar.f813h;
                if (enumC1564i3 == enumC1564i2 || enumC1564i3 == EnumC1564i.ENUM_CLASS || (z11 && (enumC1564i3 == enumC1564i || enumC1564i3 == EnumC1564i.ANNOTATION_CLASS))) {
                    return O(uVar);
                }
            }
        }
        if (!(k10 instanceof v) || !(n10 instanceof C1391h)) {
            return null;
        }
        m.d(n10, "null cannot be cast to non-null type org.jetbrains.kotlin.load.kotlin.JvmPackagePartSource");
        C1391h c1391h2 = (C1391h) n10;
        c cVar = c1391h2.f15612n;
        return cVar == null ? n7.z.f(bVar, c1391h2.a(), (f) this.f2769r) : cVar;
    }

    public ColorStateList C(Context context, int i7) {
        if (i7 == R.drawable.abc_edit_text_material) {
            return h6.f.h(context, R.color.abc_tint_edittext);
        }
        if (i7 == R.drawable.abc_switch_track_mtrl_alpha) {
            return h6.f.h(context, R.color.abc_tint_switch_track);
        }
        if (i7 != R.drawable.abc_switch_thumb_material) {
            if (i7 == R.drawable.abc_btn_default_mtrl_shape) {
                return p(context, q0.c(context, R.attr.colorButtonNormal));
            }
            if (i7 == R.drawable.abc_btn_borderless_material) {
                return p(context, 0);
            }
            if (i7 == R.drawable.abc_btn_colored_material) {
                return p(context, q0.c(context, R.attr.colorAccent));
            }
            if (i7 == R.drawable.abc_spinner_mtrl_am_alpha || i7 == R.drawable.abc_spinner_textfield_background_material) {
                return h6.f.h(context, R.color.abc_tint_spinner);
            }
            if (l((int[]) this.f2766n, i7)) {
                return q0.d(context, R.attr.colorControlNormal);
            }
            if (l((int[]) this.f2768q, i7)) {
                return h6.f.h(context, R.color.abc_tint_default);
            }
            if (l((int[]) this.f2769r, i7)) {
                return h6.f.h(context, R.color.abc_tint_btn_checkable);
            }
            if (i7 == R.drawable.abc_seekbar_thumb_material) {
                return h6.f.h(context, R.color.abc_tint_seek_thumb);
            }
            return null;
        }
        int[][] iArr = new int[3][];
        int[] iArr2 = new int[3];
        ColorStateList colorStateListD = q0.d(context, R.attr.colorSwitchThumbNormal);
        if (colorStateListD == null || !colorStateListD.isStateful()) {
            iArr[0] = q0.f21549b;
            iArr2[0] = q0.b(context, R.attr.colorSwitchThumbNormal);
            iArr[1] = q0.f21550e;
            iArr2[1] = q0.c(context, R.attr.colorControlActivated);
            iArr[2] = q0.f21551f;
            iArr2[2] = q0.c(context, R.attr.colorSwitchThumbNormal);
        } else {
            int[] iArr3 = q0.f21549b;
            iArr[0] = iArr3;
            iArr2[0] = colorStateListD.getColorForState(iArr3, 0);
            iArr[1] = q0.f21550e;
            iArr2[1] = q0.c(context, R.attr.colorControlActivated);
            iArr[2] = q0.f21551f;
            iArr2[2] = colorStateListD.getDefaultColor();
        }
        return new ColorStateList(iArr, iArr2);
    }

    public void D(long j3) {
        l2.m mVar;
        l2.m mVar2 = (l2.m) this.f2768q;
        mVar2.d(j3);
        l2.m mVar3 = (l2.m) this.f2769r;
        if (mVar3 != null) {
            mVar3.d(j3);
        }
        boolean zA = mVar2.a();
        SparseArray sparseArray = (SparseArray) this.f2766n;
        HashMap map = (HashMap) this.f2765m;
        if (zA || (mVar = (l2.m) this.f2769r) == null || !mVar.a()) {
            mVar2.f(map, sparseArray);
        } else {
            ((l2.m) this.f2769r).f(map, sparseArray);
            mVar2.c(map);
        }
        l2.m mVar4 = (l2.m) this.f2769r;
        if (mVar4 != null) {
            mVar4.h();
            this.f2769r = null;
        }
    }

    public boolean E(C2057b c2057b) {
        if (c2057b.f() == null || !m.a(c2057b.i().b(), "Container")) {
            return false;
        }
        c cVarF = n7.z.f((V7.b) this.f2765m, c2057b, (f) this.f2769r);
        if (cVarF != null) {
            LinkedHashSet linkedHashSet = M7.a.f4214a;
            Class cls = cVarF.f9548a;
            m.f(cls, "klass");
            Annotation[] declaredAnnotations = cls.getDeclaredAnnotations();
            m.e(declaredAnnotations, "klass.declaredAnnotations");
            boolean z6 = false;
            for (Annotation annotation : declaredAnnotations) {
                m.e(annotation, "annotation");
                if (AbstractC0839c.a(n7.z.i(n7.z.g(annotation))).equals(w.f10962b)) {
                    z6 = true;
                }
            }
            if (z6) {
                return true;
            }
        }
        return false;
    }

    public D G(C2057b c2057b, N n10, List list) {
        m.f(list, "result");
        return new D(this, AbstractC0669w.f((z) this.o, c2057b, (t) this.f2767p), c2057b, list, n10);
    }

    public D H(C2057b c2057b, V7.a aVar, List list) {
        m.f(list, "result");
        if (M7.a.f4214a.contains(c2057b)) {
            return null;
        }
        return G(c2057b, aVar, list);
    }

    public Object I(K k10, G g7, int i7, AbstractC0287w abstractC0287w, InterfaceC2785n interfaceC2785n) {
        Object objInvoke;
        x xVar;
        c cVarB = B(k10, true, true, AbstractC1787e.f18827A.c(g7.o), n8.h.d(g7));
        if (cVarB == null) {
            cVarB = k10 instanceof u ? O((u) k10) : null;
        }
        if (cVarB == null) {
            return null;
        }
        f fVar = (f) cVarB.f9549b.d;
        f fVar2 = C1389f.f15608e;
        m.f(fVar2, "version");
        p pVarY = y(g7, (InterfaceC1788f) k10.f670b, (C0746i0) k10.c, i7, fVar.a(fVar2.f18823b, fVar2.c, fVar2.d));
        if (pVarY == null || (objInvoke = interfaceC2785n.invoke(((E8.e) this.f2766n).b(cVarB), pVarY)) == null) {
            return null;
        }
        if (!s.a(abstractC0287w)) {
            return objInvoke;
        }
        g gVar = (g) objInvoke;
        if (gVar instanceof C2451d) {
            xVar = new x(((Number) ((C2451d) gVar).f22499a).byteValue());
        } else if (gVar instanceof t8.u) {
            xVar = new x(((Number) ((t8.u) gVar).f22499a).shortValue());
        } else if (gVar instanceof t8.k) {
            xVar = new x(((Number) ((t8.k) gVar).f22499a).intValue());
        } else {
            if (!(gVar instanceof t8.s)) {
                return gVar;
            }
            xVar = new x(((Number) ((t8.s) gVar).f22499a).longValue());
        }
        return xVar;
    }

    public List J(K k10, G g7, int i7) {
        Boolean boolC = AbstractC1787e.f18827A.c(g7.o);
        boolean zD = n8.h.d(g7);
        n7.u uVar = n7.u.l;
        if (i7 == 1) {
            p pVarJ = AbstractC1386c.j(g7, (InterfaceC1788f) k10.f670b, (C0746i0) k10.c, (40 & 8) != 0 ? false : false, (40 & 16) != 0 ? false : true, true);
            return pVarJ == null ? uVar : s(this, k10, pVarJ, true, boolC, zD, 8);
        }
        p pVarJ2 = AbstractC1386c.j(g7, (InterfaceC1788f) k10.f670b, (C0746i0) k10.c, (40 & 8) != 0 ? false : true, (40 & 16) != 0 ? false : false, true);
        if (pVarJ2 == null) {
            return uVar;
        }
        return Q8.h.C0(pVarJ2.f15622a, "$delegate", false) != (i7 == 3) ? uVar : q(k10, pVarJ2, true, true, boolC, zD);
    }

    public void K(String str) {
        HashMap map = (HashMap) this.f2765m;
        l2.k kVar = (l2.k) map.get(str);
        if (kVar != null && kVar.c.isEmpty() && kVar.d.isEmpty()) {
            map.remove(str);
            SparseBooleanArray sparseBooleanArray = (SparseBooleanArray) this.f2767p;
            int i7 = kVar.f18484a;
            boolean z6 = sparseBooleanArray.get(i7);
            ((l2.m) this.f2768q).g(kVar, z6);
            SparseArray sparseArray = (SparseArray) this.f2766n;
            if (z6) {
                sparseArray.remove(i7);
                sparseBooleanArray.delete(i7);
            } else {
                sparseArray.put(i7, null);
                ((SparseBooleanArray) this.o).put(i7, true);
            }
        }
    }

    public q L() {
        q qVar = new q();
        qVar.l = new LinkedHashMap();
        qVar.f4399m = (c9.w) this.f2765m;
        qVar.f4400n = (String) this.f2766n;
        qVar.f4401p = (AbstractC1274a) this.f2767p;
        Map map = (Map) this.f2768q;
        qVar.l = map.isEmpty() ? new LinkedHashMap() : n7.z.z(map);
        qVar.o = ((c9.u) this.o).q();
        return qVar;
    }

    public void N() {
        ((l2.m) this.f2768q).b((HashMap) this.f2765m);
        SparseBooleanArray sparseBooleanArray = (SparseBooleanArray) this.o;
        int size = sparseBooleanArray.size();
        for (int i7 = 0; i7 < size; i7++) {
            ((SparseArray) this.f2766n).remove(sparseBooleanArray.keyAt(i7));
        }
        sparseBooleanArray.clear();
        ((SparseBooleanArray) this.f2767p).clear();
    }

    public void P(f0 f0Var) {
        B5.u uVar = new B5.u(4);
        if (((I) this.f2766n).isEmpty()) {
            k(uVar, (C2492C) this.f2768q, f0Var);
            if (!AbstractC0904a.A((C2492C) this.f2769r, (C2492C) this.f2768q)) {
                k(uVar, (C2492C) this.f2769r, f0Var);
            }
            if (!AbstractC0904a.A((C2492C) this.f2767p, (C2492C) this.f2768q) && !AbstractC0904a.A((C2492C) this.f2767p, (C2492C) this.f2769r)) {
                k(uVar, (C2492C) this.f2767p, f0Var);
            }
        } else {
            for (int i7 = 0; i7 < ((I) this.f2766n).size(); i7++) {
                k(uVar, (C2492C) ((I) this.f2766n).get(i7), f0Var);
            }
            if (!((I) this.f2766n).contains((C2492C) this.f2767p)) {
                k(uVar, (C2492C) this.f2767p, f0Var);
            }
        }
        this.o = uVar.g();
    }

    @Override // B8.InterfaceC0091c
    public List b(K k10, AbstractC2136k abstractC2136k, int i7) {
        m.f(abstractC2136k, "proto");
        A7.k.p(i7, "kind");
        if (i7 == 2) {
            return J(k10, (G) abstractC2136k, 1);
        }
        p pVarY = y(abstractC2136k, (InterfaceC1788f) k10.f670b, (C0746i0) k10.c, i7, false);
        return pVarY == null ? n7.u.l : s(this, k10, pVarY, false, null, false, 60);
    }

    @Override // B8.InterfaceC0089a
    public Object e(K k10, G g7, AbstractC0287w abstractC0287w) {
        m.f(g7, "proto");
        return I(k10, g7, 2, abstractC0287w, C1385b.o);
    }

    @Override // B8.InterfaceC0091c
    public ArrayList f(u uVar) {
        m.f(uVar, "container");
        c cVarO = O(uVar);
        if (cVarO == null) {
            throw new IllegalStateException(("Class for loading annotations is not found: " + uVar.f812g.b()).toString());
        }
        ArrayList arrayList = new ArrayList(1);
        Class cls = cVarO.f9548a;
        m.f(cls, "klass");
        Annotation[] declaredAnnotations = cls.getDeclaredAnnotations();
        m.e(declaredAnnotations, "klass.declaredAnnotations");
        for (Annotation annotation : declaredAnnotations) {
            m.e(annotation, "annotation");
            Class clsI = n7.z.i(n7.z.g(annotation));
            D dH = H(AbstractC0839c.a(clsI), new V7.a(annotation), arrayList);
            if (dH != null) {
                io.sentry.config.a.j0(dH, annotation, clsI);
            }
        }
        return arrayList;
    }

    /* JADX WARN: Removed duplicated region for block: B:10:0x002d  */
    @Override // B8.InterfaceC0091c
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public java.util.List g(B5.K r9, p8.AbstractC2136k r10, int r11, int r12, j8.Z r13) {
        /*
            r8 = this;
            java.lang.String r13 = "callableProto"
            A7.m.f(r10, r13)
            java.lang.String r13 = "kind"
            A7.k.p(r11, r13)
            java.lang.Object r13 = r9.f670b
            l8.f r13 = (l8.InterfaceC1788f) r13
            java.lang.Object r0 = r9.c
            T.i0 r0 = (T.C0746i0) r0
            r1 = 0
            h8.p r11 = y(r10, r13, r0, r11, r1)
            if (r11 == 0) goto L93
            boolean r13 = r10 instanceof j8.C1579y
            r0 = 64
            r2 = 1
            if (r13 == 0) goto L2f
            j8.y r10 = (j8.C1579y) r10
            boolean r13 = r10.p()
            if (r13 != 0) goto L2d
            int r10 = r10.f17489n
            r10 = r10 & r0
            if (r10 != r0) goto L55
        L2d:
            r1 = r2
            goto L55
        L2f:
            boolean r13 = r10 instanceof j8.G
            if (r13 == 0) goto L41
            j8.G r10 = (j8.G) r10
            boolean r13 = r10.p()
            if (r13 != 0) goto L2d
            int r10 = r10.f17118n
            r10 = r10 & r0
            if (r10 != r0) goto L55
            goto L2d
        L41:
            boolean r13 = r10 instanceof j8.C1567l
            if (r13 == 0) goto L7b
            r10 = r9
            B8.u r10 = (B8.u) r10
            j8.i r13 = j8.EnumC1564i.ENUM_CLASS
            j8.i r3 = r10.f813h
            if (r3 != r13) goto L50
            r1 = 2
            goto L55
        L50:
            boolean r10 = r10.f814i
            if (r10 == 0) goto L55
            goto L2d
        L55:
            int r12 = r12 + r1
            h8.p r3 = new h8.p
            java.lang.StringBuilder r10 = new java.lang.StringBuilder
            r10.<init>()
            java.lang.String r11 = r11.f15622a
            r10.append(r11)
            r10.append(r0)
            r10.append(r12)
            java.lang.String r10 = r10.toString()
            r3.<init>(r10)
            r5 = 0
            r6 = 0
            r4 = 0
            r7 = 60
            r1 = r8
            r2 = r9
            java.util.List r8 = s(r1, r2, r3, r4, r5, r6, r7)
            return r8
        L7b:
            java.lang.UnsupportedOperationException r8 = new java.lang.UnsupportedOperationException
            java.lang.StringBuilder r9 = new java.lang.StringBuilder
            java.lang.String r11 = "Unsupported message: "
            r9.<init>(r11)
            java.lang.Class r10 = r10.getClass()
            r9.append(r10)
            java.lang.String r9 = r9.toString()
            r8.<init>(r9)
            throw r8
        L93:
            n7.u r8 = n7.u.l
            return r8
        */
        throw new UnsupportedOperationException("Method not decompiled: H0.b.g(B5.K, p8.k, int, int, j8.Z):java.util.List");
    }

    @Override // B8.InterfaceC0089a
    public Object i(K k10, G g7, AbstractC0287w abstractC0287w) {
        m.f(g7, "proto");
        return I(k10, g7, 3, abstractC0287w, C1385b.f15602n);
    }

    public void k(B5.u uVar, C2492C c2492c, f0 f0Var) {
        if (c2492c == null) {
            return;
        }
        if (f0Var.b(c2492c.f22663a) != -1) {
            uVar.s(c2492c, f0Var);
            return;
        }
        f0 f0Var2 = (f0) ((d5.K) this.o).get(c2492c);
        if (f0Var2 != null) {
            uVar.s(c2492c, f0Var2);
        }
    }

    public C1046g m() {
        C1046g c1046g = (C1046g) this.f2769r;
        if (c1046g != null) {
            return c1046g;
        }
        C1046g c1046g2 = C1046g.f12558n;
        C1046g c1046gR = AbstractC2673a.R((c9.u) this.o);
        this.f2769r = c1046gR;
        return c1046gR;
    }

    @Override // B8.InterfaceC0091c
    public List n(K k10, G g7) {
        m.f(g7, "proto");
        return J(k10, g7, 2);
    }

    @Override // B8.InterfaceC0091c
    public List o(K k10, AbstractC2136k abstractC2136k, int i7) {
        m.f(abstractC2136k, "proto");
        A7.k.p(i7, "kind");
        p pVarY = y(abstractC2136k, (InterfaceC1788f) k10.f670b, (C0746i0) k10.c, i7, false);
        return pVarY != null ? s(this, k10, new p(android.hardware.bydauto.ac.tsimpl.a.l(new StringBuilder(), pVarY.f15622a, "@0")), false, null, false, 60) : n7.u.l;
    }

    public List q(K k10, p pVar, boolean z6, boolean z10, Boolean bool, boolean z11) {
        List list;
        c cVarB = B(k10, z6, z10, bool, z11);
        if (cVarB == null) {
            cVarB = k10 instanceof u ? O((u) k10) : null;
        }
        n7.u uVar = n7.u.l;
        return (cVarB == null || (list = (List) ((C1384a) ((E8.e) this.f2766n).b(cVarB)).f15600a.get(pVar)) == null) ? uVar : list;
    }

    @Override // B8.InterfaceC0091c
    public ArrayList r(j8.W w10, InterfaceC1788f interfaceC1788f) {
        m.f(w10, "proto");
        m.f(interfaceC1788f, "nameResolver");
        Object objK = w10.k(m8.k.f19332h);
        m.e(objK, "proto.getExtension(JvmPr….typeParameterAnnotation)");
        Iterable<C1562g> iterable = (Iterable) objK;
        ArrayList arrayList = new ArrayList(AbstractC2000o.J(iterable, 10));
        for (C1562g c1562g : iterable) {
            m.e(c1562g, "it");
            arrayList.add(((M.t) this.f2768q).z(c1562g, interfaceC1788f));
        }
        return arrayList;
    }

    @Override // B8.InterfaceC0091c
    public List t(K k10, G g7) {
        m.f(g7, "proto");
        return J(k10, g7, 3);
    }

    public String toString() {
        switch (this.l) {
            case 1:
                StringBuilder sb = new StringBuilder("Request{method=");
                sb.append((String) this.f2766n);
                sb.append(", url=");
                sb.append((c9.w) this.f2765m);
                c9.u uVar = (c9.u) this.o;
                if (uVar.size() != 0) {
                    sb.append(", headers=[");
                    int i7 = 0;
                    for (Object obj : uVar) {
                        int i10 = i7 + 1;
                        if (i7 < 0) {
                            AbstractC1999n.I();
                            throw null;
                        }
                        i iVar = (i) obj;
                        String str = (String) iVar.l;
                        String str2 = (String) iVar.f19259m;
                        if (i7 > 0) {
                            sb.append(", ");
                        }
                        sb.append(str);
                        sb.append(':');
                        sb.append(str2);
                        i7 = i10;
                    }
                    sb.append(']');
                }
                Map map = (Map) this.f2768q;
                if (!map.isEmpty()) {
                    sb.append(", tags=");
                    sb.append(map);
                }
                sb.append('}');
                String string = sb.toString();
                m.e(string, "StringBuilder().apply(builderAction).toString()");
                return string;
            default:
                return super.toString();
        }
    }

    @Override // B8.InterfaceC0091c
    public List u(K k10, C1574t c1574t) {
        m.f(k10, "container");
        String strN = ((InterfaceC1788f) k10.f670b).n(c1574t.o);
        String strB = n8.b.b(((u) k10).f812g.c());
        m.f(strB, "desc");
        return s(this, k10, new p(strN + '#' + strB), false, null, false, 60);
    }

    @Override // B8.InterfaceC0091c
    public ArrayList w(Q q4, InterfaceC1788f interfaceC1788f) {
        m.f(q4, "proto");
        m.f(interfaceC1788f, "nameResolver");
        Object objK = q4.k(m8.k.f19330f);
        m.e(objK, "proto.getExtension(JvmProtoBuf.typeAnnotation)");
        Iterable<C1562g> iterable = (Iterable) objK;
        ArrayList arrayList = new ArrayList(AbstractC2000o.J(iterable, 10));
        for (C1562g c1562g : iterable) {
            m.e(c1562g, "it");
            arrayList.add(((M.t) this.f2768q).z(c1562g, interfaceC1788f));
        }
        return arrayList;
    }

    public l2.k x(String str) {
        return (l2.k) ((HashMap) this.f2765m).get(str);
    }

    public l2.k z(String str) {
        HashMap map = (HashMap) this.f2765m;
        l2.k kVar = (l2.k) map.get(str);
        if (kVar != null) {
            return kVar;
        }
        SparseArray sparseArray = (SparseArray) this.f2766n;
        int size = sparseArray.size();
        int i7 = 0;
        int iKeyAt = size == 0 ? 0 : sparseArray.keyAt(size - 1) + 1;
        if (iKeyAt < 0) {
            while (i7 < size && i7 == sparseArray.keyAt(i7)) {
                i7++;
            }
            iKeyAt = i7;
        }
        l2.k kVar2 = new l2.k(iKeyAt, str, l2.o.c);
        map.put(str, kVar2);
        sparseArray.put(iKeyAt, str);
        ((SparseBooleanArray) this.f2767p).put(iKeyAt, true);
        ((l2.m) this.f2768q).e(kVar2);
        return kVar2;
    }

    public b(c9.w wVar, String str, c9.u uVar, AbstractC1274a abstractC1274a, Map map) {
        this.l = 1;
        m.f(wVar, "url");
        m.f(str, "method");
        this.f2765m = wVar;
        this.f2766n = str;
        this.o = uVar;
        this.f2767p = abstractC1274a;
        this.f2768q = map;
    }

    public b(z zVar, t tVar, l lVar, V7.b bVar) {
        this.l = 2;
        this.l = 2;
        this.f2765m = bVar;
        this.f2766n = lVar.b(new S0.p(this, 21));
        this.o = zVar;
        this.f2767p = tVar;
        this.f2768q = new M.t((InterfaceC0671y) zVar, tVar);
        this.f2769r = f.f20166g;
    }

    public b(n nVar, MediaFormat mediaFormat, C1208p c1208p, Surface surface, MediaCrypto mediaCrypto, y yVar) {
        this.l = 8;
        this.f2765m = nVar;
        this.f2766n = mediaFormat;
        this.o = c1208p;
        this.f2767p = surface;
        this.f2768q = mediaCrypto;
        this.f2769r = yVar;
    }

    public b(C1538a c1538a, File file) {
        this.l = 5;
        this.f2765m = new HashMap();
        this.f2766n = new SparseArray();
        this.o = new SparseBooleanArray();
        this.f2767p = new SparseBooleanArray();
        l2.l lVar = new l2.l(c1538a);
        File file2 = new File(file, "cached_content_index.exi");
        C c = new C();
        c.f5203b = null;
        c.c = null;
        M.t tVar = new M.t();
        tVar.l = file2;
        tVar.f4151m = new File(file2.getPath() + ".bak");
        c.d = tVar;
        this.f2768q = lVar;
        this.f2769r = c;
    }

    public b(f9.c cVar) {
        this.l = 3;
        m.f(cVar, "taskRunner");
        this.f2765m = cVar;
        this.f2769r = j9.g.f17520a;
    }

    public b(c0 c0Var) {
        this.l = 6;
        this.f2765m = c0Var;
        C1078G c1078g = I.f13384m;
        this.f2766n = d0.f13415p;
        this.o = i0.f13428r;
    }

    public b(int i7) {
        this.l = i7;
        switch (i7) {
            case 7:
                this.f2765m = new int[]{R.drawable.abc_textfield_search_default_mtrl_alpha, R.drawable.abc_textfield_default_mtrl_alpha, R.drawable.abc_ab_share_pack_mtrl_alpha};
                this.f2766n = new int[]{R.drawable.abc_ic_commit_search_api_mtrl_alpha, R.drawable.abc_seekbar_tick_mark_material, R.drawable.abc_ic_menu_share_mtrl_alpha, R.drawable.abc_ic_menu_copy_mtrl_am_alpha, R.drawable.abc_ic_menu_cut_mtrl_alpha, R.drawable.abc_ic_menu_selectall_mtrl_alpha, R.drawable.abc_ic_menu_paste_mtrl_am_alpha};
                this.o = new int[]{R.drawable.abc_textfield_activated_mtrl_alpha, R.drawable.abc_textfield_search_activated_mtrl_alpha, R.drawable.abc_cab_background_top_mtrl_alpha, R.drawable.abc_text_cursor_material, R.drawable.abc_text_select_handle_left_mtrl, R.drawable.abc_text_select_handle_middle_mtrl, R.drawable.abc_text_select_handle_right_mtrl};
                this.f2767p = new int[]{R.drawable.abc_popup_background_mtrl_mult, R.drawable.abc_cab_background_internal_bg, R.drawable.abc_menu_hardkey_panel_mtrl_mult};
                this.f2768q = new int[]{R.drawable.abc_tab_indicator_material, R.drawable.abc_textfield_search_material};
                this.f2769r = new int[]{R.drawable.abc_btn_check_material, R.drawable.abc_btn_radio_material, R.drawable.abc_btn_check_material_anim, R.drawable.abc_btn_radio_material_anim};
                break;
            default:
                this.f2765m = q1.f18034F.l(w1.f18151g);
                this.f2766n = z1.f18172b;
                this.o = S.f13999b;
                this.f2767p = d0.f13415p;
                this.f2768q = Bundle.EMPTY;
                this.f2769r = null;
                break;
        }
    }

    public b(q1 q1Var, z1 z1Var, S s6, I i7, Bundle bundle, A1 a12) {
        this.l = 4;
        this.f2765m = q1Var;
        this.f2766n = z1Var;
        this.o = s6;
        this.f2767p = i7;
        this.f2768q = bundle == null ? Bundle.EMPTY : bundle;
        this.f2769r = a12;
    }
}
