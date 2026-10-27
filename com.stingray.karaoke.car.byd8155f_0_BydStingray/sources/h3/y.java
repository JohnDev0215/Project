package h3;

import A.C0006c;
import A.s1;
import B2.C0073m;
import B2.L;
import K.L0;
import android.content.Context;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Typeface;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.media.AudioRouting;
import android.media.AudioTrack;
import android.media.LoudnessCodecController;
import android.media.MediaCodec;
import android.net.Uri;
import android.os.Handler;
import android.os.Looper;
import android.os.ParcelFileDescriptor;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.View;
import android.view.ViewGroup;
import androidx.recyclerview.widget.RecyclerView;
import com.bumptech.glide.load.ImageHeaderParser$ImageType;
import d5.AbstractC1095q;
import d5.C1077F;
import d5.K;
import d5.M;
import d5.d0;
import d5.s0;
import e2.AbstractC1199g;
import e2.C1207o;
import e2.C1208p;
import e2.C1215x;
import h2.AbstractC1343A;
import h2.AbstractC1346c;
import h4.InterfaceC1359a;
import io.sentry.V1;
import io.sentry.android.core.EnumC1445v;
import java.io.EOFException;
import java.io.FileDescriptor;
import java.io.FileInputStream;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import k1.AbstractC1598c;
import k3.A0;
import k3.C1650j0;
import l1.InterfaceC1733d;
import l3.C1751g;
import l3.C1764u;
import n0.InterfaceC1889r;
import n4.C1958d;
import p0.C2071b;
import p1.C2074b;
import p1.InterfaceC2075c;
import p2.C2090e;
import r.C2266y;
import r4.C2290c;
import s3.C2404x;
import s3.U;
import u.AbstractC2456D;
import u.AbstractC2471k;
import u.C2472l;
import u.C2478r;
import u2.T;
import v.AbstractC2530a;
import v5.C2552b;
import v5.EnumC2551a;
import w5.AbstractC2600b;
import w5.C2602d;
import w5.C2603e;
import x.AbstractC2641j;
import x1.AbstractC2671j;
import x3.AbstractC2673a;
import y0.C2729g;

/* JADX INFO: loaded from: classes.dex */
public final class y implements D, s4.a {
    public final /* synthetic */ int l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public Object f15499m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public Object f15500n;
    public Object o;

    public /* synthetic */ y(Object obj, Object obj2, Object obj3, int i7) {
        this.l = i7;
        this.f15499m = obj;
        this.f15500n = obj2;
        this.o = obj3;
    }

    public static y F(Context context, AttributeSet attributeSet, int[] iArr, int i7) {
        return new y(context, context.obtainStyledAttributes(attributeSet, iArr, i7, 0));
    }

    private final void N() {
    }

    private final void O() {
    }

    public static q2.f g(C1215x c1215x) {
        L0 l02 = new L0(5);
        Uri uri = c1215x.f14362b;
        A2.c cVar = new A2.c(uri == null ? null : uri.toString(), c1215x.f14364f, l02);
        K k10 = c1215x.c;
        M mC = k10.l;
        if (mC == null) {
            mC = k10.c();
            k10.l = mC;
        }
        s0 s0VarS = mC.iterator();
        while (s0VarS.hasNext()) {
            Map.Entry entry = (Map.Entry) s0VarS.next();
            String str = (String) entry.getKey();
            String str2 = (String) entry.getValue();
            str.getClass();
            str2.getClass();
            synchronized (((HashMap) cVar.f312e)) {
                ((HashMap) cVar.f312e).put(str, str2);
            }
        }
        HashMap map = new HashMap();
        UUID uuid = AbstractC1199g.f14099a;
        l9.n nVar = new l9.n(15);
        UUID uuid2 = c1215x.f14361a;
        uuid2.getClass();
        boolean z6 = c1215x.d;
        boolean z10 = c1215x.f14363e;
        int[] iArrZ = AbstractC2673a.Z(c1215x.f14365g);
        int length = iArrZ.length;
        for (int i7 = 0; i7 < length; i7++) {
            int i10 = iArrZ[i7];
            AbstractC1346c.c(i10 == 2 || i10 == 1);
        }
        q2.f fVar = new q2.f(uuid2, cVar, map, z6, (int[]) iArrZ.clone(), z10, nVar);
        byte[] bArr = c1215x.f14366h;
        byte[] bArrCopyOf = bArr != null ? Arrays.copyOf(bArr, bArr.length) : null;
        AbstractC1346c.j(fVar.f21194m.isEmpty());
        fVar.f21202v = bArrCopyOf;
        return fVar;
    }

    public int A() {
        return ((C2404x) this.f15499m).f22362a.getChildCount();
    }

    public String B() {
        V1 v12 = (V1) this.f15500n;
        io.sentry.protocol.t tVar = (io.sentry.protocol.t) this.f15499m;
        Boolean bool = (Boolean) this.o;
        if (bool == null) {
            return tVar + "-" + v12;
        }
        return tVar + "-" + v12 + "-" + (bool.booleanValue() ? "1" : "0");
    }

    public void C(View view) {
        ((ArrayList) this.o).add(view);
        C2404x c2404x = (C2404x) this.f15499m;
        U uG = RecyclerView.G(view);
        if (uG != null) {
            int i7 = uG.f22231q;
            View view2 = uG.f22219a;
            if (i7 != -1) {
                uG.f22230p = i7;
            } else {
                int[] iArr = F1.H.f2303a;
                uG.f22230p = view2.getImportantForAccessibility();
            }
            RecyclerView recyclerView = c2404x.f22362a;
            if (recyclerView.J()) {
                uG.f22231q = 4;
                recyclerView.B0.add(uG);
            } else {
                int[] iArr2 = F1.H.f2303a;
                view2.setImportantForAccessibility(4);
            }
        }
    }

    public void D(k2.h hVar, Uri uri, Map map, long j3, long j10, T t10) throws H2.d {
        boolean z6;
        C0073m c0073m = new C0073m(hVar, j3, j10);
        this.o = c0073m;
        if (((B2.r) this.f15500n) != null) {
            return;
        }
        B2.r[] rVarArrL = ((B2.u) this.f15499m).l(uri, map);
        C1077F c1077fV = d5.I.v(rVarArrL.length);
        boolean z10 = true;
        if (rVarArrL.length == 1) {
            this.f15500n = rVarArrL[0];
        } else {
            int length = rVarArrL.length;
            int i7 = 0;
            while (true) {
                if (i7 >= length) {
                    break;
                }
                B2.r rVar = rVarArrL[i7];
                try {
                    if (rVar.f(c0073m)) {
                        this.f15500n = rVar;
                        c0073m.f590q = 0;
                        break;
                    } else {
                        c1077fV.e(rVar.g());
                        z6 = ((B2.r) this.f15500n) != null || c0073m.o == j3;
                    }
                } catch (EOFException unused) {
                    z6 = ((B2.r) this.f15500n) != null || c0073m.o == j3;
                } catch (Throwable th) {
                    if (((B2.r) this.f15500n) == null && c0073m.o != j3) {
                        z10 = false;
                    }
                    AbstractC1346c.j(z10);
                    c0073m.f590q = 0;
                    throw th;
                }
                AbstractC1346c.j(z6);
                c0073m.f590q = 0;
                i7++;
            }
            if (((B2.r) this.f15500n) == null) {
                StringBuilder sb = new StringBuilder("None of the available extractors (");
                B2.q qVar = new B2.q(", ");
                Iterator it = AbstractC1095q.x(new o2.d(29), d5.I.x(rVarArrL)).iterator();
                StringBuilder sb2 = new StringBuilder();
                qVar.b(sb2, it);
                sb.append(sb2.toString());
                sb.append(") could read the stream.");
                String string = sb.toString();
                uri.getClass();
                d0 d0VarH = c1077fV.h();
                H2.d dVar = new H2.d(string, null, false, 1);
                d5.I.w(d0VarH);
                throw dVar;
            }
        }
        ((B2.r) this.f15500n).b(t10);
    }

    public boolean E(int i7, o1.d dVar, InterfaceC2075c interfaceC2075c) {
        int[] iArr = dVar.f20311q0;
        int i10 = iArr[0];
        C2074b c2074b = (C2074b) this.f15500n;
        c2074b.f20644a = i10;
        c2074b.f20645b = iArr[1];
        c2074b.c = dVar.r();
        c2074b.d = dVar.l();
        c2074b.f20650i = false;
        c2074b.f20651j = i7;
        boolean z6 = c2074b.f20644a == 3;
        boolean z10 = c2074b.f20645b == 3;
        boolean z11 = z6 && dVar.f20279X > 0.0f;
        boolean z12 = z10 && dVar.f20279X > 0.0f;
        int[] iArr2 = dVar.f20315u;
        if (z11 && iArr2[0] == 4) {
            c2074b.f20644a = 1;
        }
        if (z12 && iArr2[1] == 4) {
            c2074b.f20645b = 1;
        }
        interfaceC2075c.b(dVar, c2074b);
        dVar.S(c2074b.f20646e);
        dVar.N(c2074b.f20647f);
        dVar.f20261F = c2074b.f20649h;
        dVar.J(c2074b.f20648g);
        c2074b.f20651j = 0;
        return c2074b.f20650i;
    }

    public void G() {
        ((TypedArray) this.f15500n).recycle();
    }

    public void H(MediaCodec mediaCodec) {
        LoudnessCodecController loudnessCodecController;
        if (!((HashSet) this.f15499m).remove(mediaCodec) || (loudnessCodecController = (LoudnessCodecController) this.o) == null) {
            return;
        }
        loudnessCodecController.removeMediaCodec(mediaCodec);
    }

    public void I(InterfaceC1889r interfaceC1889r) {
        ((C2071b) this.o).l.c = interfaceC1889r;
    }

    public void J(a1.b bVar) {
        ((C2071b) this.o).l.f20636a = bVar;
    }

    public void K(a1.k kVar) {
        ((C2071b) this.o).l.f20637b = kVar;
    }

    public void L(long j3) {
        ((C2071b) this.o).l.d = j3;
    }

    public void M(o1.e eVar, int i7, int i10, int i11) {
        eVar.getClass();
        int i12 = eVar.f20286c0;
        int i13 = eVar.f20287d0;
        eVar.f20286c0 = 0;
        eVar.f20287d0 = 0;
        eVar.S(i10);
        eVar.N(i11);
        if (i12 < 0) {
            eVar.f20286c0 = 0;
        } else {
            eVar.f20286c0 = i12;
        }
        if (i13 < 0) {
            eVar.f20287d0 = 0;
        } else {
            eVar.f20287d0 = i13;
        }
        o1.e eVar2 = (o1.e) this.o;
        eVar2.f20335u0 = i7;
        eVar2.Z();
    }

    public void P(View view) {
        if (((ArrayList) this.o).remove(view)) {
            C2404x c2404x = (C2404x) this.f15499m;
            U uG = RecyclerView.G(view);
            if (uG != null) {
                int i7 = uG.f22230p;
                RecyclerView recyclerView = c2404x.f22362a;
                if (recyclerView.J()) {
                    uG.f22231q = i7;
                    recyclerView.B0.add(uG);
                } else {
                    int[] iArr = F1.H.f2303a;
                    uG.f22219a.setImportantForAccessibility(i7);
                }
                uG.f22230p = 0;
            }
        }
    }

    public void Q(o1.e eVar) {
        ArrayList arrayList = (ArrayList) this.f15499m;
        arrayList.clear();
        int size = eVar.f20332r0.size();
        for (int i7 = 0; i7 < size; i7++) {
            o1.d dVar = (o1.d) eVar.f20332r0.get(i7);
            int[] iArr = dVar.f20311q0;
            if (iArr[0] == 3 || iArr[1] == 3) {
                arrayList.add(dVar);
            }
        }
        eVar.f20334t0.f20654b = true;
    }

    @Override // h3.D
    public void a(h2.y yVar, B2.t tVar, H h10) {
        this.f15500n = yVar;
        h10.a();
        h10.b();
        L lU = tVar.u(h10.d, 5);
        this.o = lU;
        lU.d((C1208p) this.f15499m);
    }

    /* JADX WARN: Removed duplicated region for block: B:22:0x007f  */
    /* JADX WARN: Removed duplicated region for block: B:27:0x009b  */
    /* JADX WARN: Removed duplicated region for block: B:30:0x00ac  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void b(long r21, java.util.List r23, boolean r24) {
        /*
            Method dump skipped, instruction units count: 320
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: h3.y.b(long, java.util.List, boolean):void");
    }

    @Override // h3.D
    public void c(h2.t tVar) {
        long jD;
        long j3;
        AbstractC1346c.k((h2.y) this.f15500n);
        int i7 = AbstractC1343A.f15195a;
        h2.y yVar = (h2.y) this.f15500n;
        synchronized (yVar) {
            try {
                long j10 = yVar.c;
                jD = j10 != -9223372036854775807L ? j10 + yVar.f15250b : yVar.d();
            } finally {
            }
        }
        h2.y yVar2 = (h2.y) this.f15500n;
        synchronized (yVar2) {
            j3 = yVar2.f15250b;
        }
        if (jD == -9223372036854775807L || j3 == -9223372036854775807L) {
            return;
        }
        C1208p c1208p = (C1208p) this.f15499m;
        if (j3 != c1208p.f14320s) {
            C1207o c1207oA = c1208p.a();
            c1207oA.f14250r = j3;
            C1208p c1208p2 = new C1208p(c1207oA);
            this.f15499m = c1208p2;
            ((L) this.o).d(c1208p2);
        }
        int iA = tVar.a();
        ((L) this.o).a(tVar, iA, 0);
        ((L) this.o).b(jD, 1, iA, 0, null);
    }

    public void d(View view, int i7, boolean z6) {
        RecyclerView recyclerView = ((C2404x) this.f15499m).f22362a;
        int childCount = i7 < 0 ? recyclerView.getChildCount() : w(i7);
        ((s1) this.f15500n).t(childCount, z6);
        if (z6) {
            C(view);
        }
        recyclerView.addView(view, childCount);
        RecyclerView.G(view);
    }

    public void e(View view, int i7, ViewGroup.LayoutParams layoutParams, boolean z6) {
        RecyclerView recyclerView = ((C2404x) this.f15499m).f22362a;
        int childCount = i7 < 0 ? recyclerView.getChildCount() : w(i7);
        ((s1) this.f15500n).t(childCount, z6);
        if (z6) {
            C(view);
        }
        U uG = RecyclerView.G(view);
        if (uG != null) {
            if (!uG.k() && !uG.p()) {
                throw new IllegalArgumentException("Called attach on a child which is not detached: " + uG + recyclerView.w());
            }
            uG.f22226j &= -257;
        }
        recyclerView.attachViewToParent(view, childCount, layoutParams);
    }

    @Override // s4.a
    public g4.x f(g4.x xVar, e4.h hVar) {
        Drawable drawable = (Drawable) xVar.get();
        if (drawable instanceof BitmapDrawable) {
            return ((C1.j) this.f15500n).f(C1958d.c(((BitmapDrawable) drawable).getBitmap(), (InterfaceC1359a) this.f15499m), hVar);
        }
        if (drawable instanceof C2290c) {
            return ((s4.c) this.o).f(xVar, hVar);
        }
        return null;
    }

    public void h(B2.t tVar, H h10) {
        int i7 = 0;
        while (true) {
            L[] lArr = (L[]) this.f15500n;
            if (i7 >= lArr.length) {
                return;
            }
            h10.a();
            h10.b();
            L lU = tVar.u(h10.d, 3);
            C1208p c1208p = (C1208p) ((List) this.f15499m).get(i7);
            String str = c1208p.f14316n;
            AbstractC1346c.b("Invalid closed caption MIME type provided: " + str, "application/cea-608".equals(str) || "application/cea-708".equals(str));
            String str2 = c1208p.f14306a;
            if (str2 == null) {
                h10.b();
                str2 = h10.f15293e;
            }
            C1207o c1207o = new C1207o();
            c1207o.f14237a = str2;
            c1207o.f14246m = e2.L.l(str);
            c1207o.f14239e = c1208p.f14308e;
            c1207o.d = c1208p.d;
            c1207o.f14232G = c1208p.f14300H;
            c1207o.f14248p = c1208p.f14318q;
            lU.d(new C1208p(c1207o));
            lArr[i7] = lU;
            i7++;
        }
    }

    public Bitmap i(BitmapFactory.Options options) {
        switch (this.l) {
            case 11:
                return BitmapFactory.decodeStream(new A4.a(A4.c.c((ByteBuffer) this.f15499m)), null, options);
            case 12:
                n4.w wVar = (n4.w) ((com.bumptech.glide.load.data.i) this.f15499m).f12725m;
                wVar.reset();
                return BitmapFactory.decodeStream(wVar, null, options);
            default:
                return BitmapFactory.decodeFileDescriptor(((com.bumptech.glide.load.data.i) this.o).e().getFileDescriptor(), null, options);
        }
    }

    public void j(int i7) {
        U uG;
        int iW = w(i7);
        ((s1) this.f15500n).v(iW);
        RecyclerView recyclerView = ((C2404x) this.f15499m).f22362a;
        View childAt = recyclerView.getChildAt(iW);
        if (childAt != null && (uG = RecyclerView.G(childAt)) != null) {
            if (uG.k() && !uG.p()) {
                throw new IllegalArgumentException("called detach on an already detached child " + uG + recyclerView.w());
            }
            uG.a(256);
        }
        recyclerView.detachViewFromParent(iW);
    }

    public boolean k(t4.e eVar, boolean z6) {
        boolean z10;
        boolean z11;
        C0006c c0006c = (C0006c) this.f15500n;
        if (!c0006c.a((C2472l) eVar.f22463n, (C0.r) this.f15499m, eVar, z6)) {
            return false;
        }
        V.d dVar = c0006c.f158a;
        int i7 = dVar.f9279n;
        if (i7 > 0) {
            Object[] objArr = dVar.l;
            int i10 = 0;
            z10 = false;
            do {
                z10 = ((C2729g) objArr[i10]).h(eVar, z6) || z10;
                i10++;
            } while (i10 < i7);
        } else {
            z10 = false;
        }
        int i11 = dVar.f9279n;
        if (i11 > 0) {
            Object[] objArr2 = dVar.l;
            int i12 = 0;
            z11 = false;
            do {
                z11 = ((C2729g) objArr2[i12]).g(eVar) || z11;
                i12++;
            } while (i12 < i11);
        } else {
            z11 = false;
        }
        c0006c.c(eVar);
        return z11 || z10;
    }

    public float l(AbstractC1598c abstractC1598c) {
        if (!(abstractC1598c instanceof k1.i)) {
            if (abstractC1598c instanceof k1.e) {
                return ((k1.e) abstractC1598c).l();
            }
            return 0.0f;
        }
        String strD = ((k1.i) abstractC1598c).d();
        HashMap map = (HashMap) this.f15500n;
        if (map.containsKey(strD)) {
            return ((InterfaceC1733d) map.get(strD)).value();
        }
        HashMap map2 = (HashMap) this.f15499m;
        if (map2.containsKey(strD)) {
            return ((Integer) map2.get(strD)).floatValue();
        }
        return 0.0f;
    }

    public InterfaceC1889r m() {
        return ((C2071b) this.o).l.c;
    }

    public View n(int i7) {
        return ((C2404x) this.f15499m).f22362a.getChildAt(w(i7));
    }

    public int o() {
        return ((C2404x) this.f15499m).f22362a.getChildCount() - ((ArrayList) this.o).size();
    }

    public ColorStateList p(int i7) {
        int resourceId;
        ColorStateList colorStateListH;
        TypedArray typedArray = (TypedArray) this.f15500n;
        return (!typedArray.hasValue(i7) || (resourceId = typedArray.getResourceId(i7, 0)) == 0 || (colorStateListH = h6.f.h((Context) this.f15499m, resourceId)) == null) ? typedArray.getColorStateList(i7) : colorStateListH;
    }

    public long q() {
        C0073m c0073m = (C0073m) this.o;
        if (c0073m != null) {
            return c0073m.o;
        }
        return -1L;
    }

    public a1.b r() {
        return ((C2071b) this.o).l.f20636a;
    }

    public Drawable s(int i7) {
        int resourceId;
        TypedArray typedArray = (TypedArray) this.f15500n;
        return (!typedArray.hasValue(i7) || (resourceId = typedArray.getResourceId(i7, 0)) == 0) ? typedArray.getDrawable(i7) : h6.f.k((Context) this.f15499m, resourceId);
    }

    public Typeface t(int i7, int i10, C2266y c2266y) {
        int resourceId = ((TypedArray) this.f15500n).getResourceId(i7, 0);
        if (resourceId == 0) {
            return null;
        }
        if (((TypedValue) this.o) == null) {
            this.o = new TypedValue();
        }
        TypedValue typedValue = (TypedValue) this.o;
        ThreadLocal threadLocal = AbstractC2671j.f23708a;
        Context context = (Context) this.f15499m;
        if (context.isRestricted()) {
            return null;
        }
        return AbstractC2671j.b(context, resourceId, typedValue, i10, c2266y, true, false);
    }

    public String toString() {
        switch (this.l) {
            case 10:
                StringBuilder sb = new StringBuilder("NavDeepLinkRequest{");
                Uri uri = (Uri) this.f15499m;
                if (uri != null) {
                    sb.append(" uri=");
                    sb.append(String.valueOf(uri));
                }
                String str = (String) this.f15500n;
                if (str != null) {
                    sb.append(" action=");
                    sb.append(str);
                }
                String str2 = (String) this.o;
                if (str2 != null) {
                    sb.append(" mimetype=");
                    sb.append(str2);
                }
                sb.append(" }");
                String string = sb.toString();
                A7.m.e(string, "sb.toString()");
                return string;
            case 22:
                return ((s1) this.f15500n).toString() + ", hidden list:" + ((ArrayList) this.o).size();
            case 25:
                StringBuilder sb2 = new StringBuilder();
                C2603e c2603e = null;
                for (C2603e c2603e2 : (ArrayList) this.f15499m) {
                    if (c2603e != null) {
                        sb2.append(",");
                    }
                    sb2.append(c2603e2.toString());
                    c2603e = c2603e2;
                }
                return sb2.toString();
            default:
                return super.toString();
        }
    }

    public ImageHeaderParser$ImageType u() throws Throwable {
        switch (this.l) {
            case 11:
                return l9.l.S((ArrayList) this.f15500n, A4.c.c((ByteBuffer) this.f15499m));
            case 12:
                n4.w wVar = (n4.w) ((com.bumptech.glide.load.data.i) this.f15499m).f12725m;
                wVar.reset();
                return l9.l.R((ArrayList) this.o, wVar, (F.l) this.f15500n);
            default:
                com.bumptech.glide.load.data.i iVar = (com.bumptech.glide.load.data.i) this.o;
                F.l lVar = (F.l) this.f15499m;
                ArrayList arrayList = (ArrayList) this.f15500n;
                int size = arrayList.size();
                for (int i7 = 0; i7 < size; i7++) {
                    e4.d dVar = (e4.d) arrayList.get(i7);
                    n4.w wVar2 = null;
                    try {
                        FileDescriptor fileDescriptor = iVar.e().getFileDescriptor();
                        n4.w wVar3 = new n4.w(h6.j.i(new FileInputStream(fileDescriptor), fileDescriptor), lVar);
                        try {
                            ImageHeaderParser$ImageType imageHeaderParser$ImageTypeD = dVar.d(wVar3);
                            wVar3.g();
                            iVar.e();
                            if (imageHeaderParser$ImageTypeD != ImageHeaderParser$ImageType.UNKNOWN) {
                                return imageHeaderParser$ImageTypeD;
                            }
                        } catch (Throwable th) {
                            th = th;
                            wVar2 = wVar3;
                            if (wVar2 != null) {
                                wVar2.g();
                            }
                            iVar.e();
                            throw th;
                        }
                    } catch (Throwable th2) {
                        th = th2;
                    }
                }
                return ImageHeaderParser$ImageType.UNKNOWN;
        }
    }

    public a1.k v() {
        return ((C2071b) this.o).l.f20637b;
    }

    public int w(int i7) {
        if (i7 < 0) {
            return -1;
        }
        int childCount = ((C2404x) this.f15499m).f22362a.getChildCount();
        int i10 = i7;
        while (i10 < childCount) {
            s1 s1Var = (s1) this.f15500n;
            int iQ = i7 - (i10 - s1Var.q(i10));
            if (iQ == 0) {
                while (s1Var.s(i10)) {
                    i10++;
                }
                return i10;
            }
            i10 += iQ;
        }
        return -1;
    }

    public int x(C2552b c2552b) {
        int i7 = 0;
        for (C2603e c2603e : (ArrayList) this.f15499m) {
            EnumC2551a enumC2551a = c2603e.f23335a;
            int iA = enumC2551a.a(c2552b);
            int iA2 = iA + 4;
            int iOrdinal = enumC2551a.ordinal();
            int i10 = c2603e.d;
            if (iOrdinal == 1) {
                int i11 = ((i10 / 3) * 10) + iA2;
                int i12 = i10 % 3;
                iA2 = i11 + (i12 != 1 ? i12 == 2 ? 7 : 0 : 4);
            } else if (iOrdinal == 2) {
                iA2 = ((i10 / 2) * 11) + iA2 + (i10 % 2 != 1 ? 0 : 6);
            } else if (iOrdinal == 4) {
                iA2 += c2603e.a() * 8;
            } else if (iOrdinal == 5) {
                iA2 = iA + 12;
            } else if (iOrdinal == 6) {
                iA2 += i10 * 13;
            }
            i7 += iA2;
        }
        return i7;
    }

    public long y() {
        return ((C2071b) this.o).l.d;
    }

    public View z(int i7) {
        return ((C2404x) this.f15499m).f22362a.getChildAt(i7);
    }

    public y(C0.r rVar) {
        this.l = 26;
        this.f15499m = rVar;
        this.f15500n = new C0006c(2);
        C2478r c2478r = new C2478r();
        c2478r.f22622a = AbstractC2456D.f22584a;
        c2478r.f22623b = AbstractC2471k.f22604a;
        c2478r.c = AbstractC2530a.c;
        c2478r.c(AbstractC2456D.f(10));
        this.o = c2478r;
    }

    public y(List list) {
        this.l = 1;
        this.f15499m = list;
        this.f15500n = new L[list.size()];
        this.o = new i2.q(new G2.a(this, 9));
    }

    public y(String str) {
        this.l = 0;
        C1207o c1207o = new C1207o();
        c1207o.f14246m = e2.L.l(str);
        this.f15499m = new C1208p(c1207o);
    }

    public y(C2404x c2404x) {
        this.l = 22;
        this.f15499m = c2404x;
        this.f15500n = new s1();
        this.o = new ArrayList();
    }

    public y(Intent intent) {
        this.l = 10;
        Uri data = intent.getData();
        String action = intent.getAction();
        String type = intent.getType();
        this.f15499m = data;
        this.f15500n = action;
        this.o = type;
    }

    public y(B2.u uVar) {
        this.l = 24;
        this.f15499m = uVar;
    }

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public y(int i7) {
        this(new io.sentry.protocol.t((UUID) null), new V1(), null, 2);
        this.l = i7;
        switch (i7) {
            case 9:
                break;
            case 19:
                this.f15499m = new Object();
                break;
            case 21:
                s2.i iVar = s2.i.l;
                this.f15499m = new HashSet();
                this.f15500n = iVar;
                break;
            default:
                break;
        }
    }

    public y(C2071b c2071b) {
        this.l = 14;
        this.o = c2071b;
        this.f15499m = new C1764u(this, 10);
    }

    public y(Context context, TypedArray typedArray) {
        this.l = 20;
        this.f15499m = context;
        this.f15500n = typedArray;
    }

    public y(o1.e eVar) {
        this.l = 15;
        this.f15499m = new ArrayList();
        this.f15500n = new C2074b();
        this.o = eVar;
    }

    public y(byte[] bArr, h5.y yVar) {
        this.l = 6;
        this.f15499m = bArr;
        this.f15500n = null;
        this.o = yVar;
    }

    public y(Uri uri, h5.y yVar) {
        this.l = 6;
        this.f15499m = null;
        this.f15500n = uri;
        this.o = yVar;
    }

    public y(y yVar) {
        A2.c cVar;
        this.l = 2;
        io.sentry.protocol.t tVar = (io.sentry.protocol.t) yVar.f15499m;
        V1 v12 = (V1) yVar.f15500n;
        A2.c cVar2 = (A2.c) yVar.o;
        if (cVar2 != null) {
            cVar = new A2.c((HashMap) cVar2.c, (String) cVar2.d, cVar2.f311b, (io.sentry.K) cVar2.f312e);
        } else {
            cVar = null;
        }
        this(tVar, v12, cVar, 2);
    }

    public y(Context context) {
        this.l = 27;
        this.f15499m = context.getApplicationContext();
        this.f15500n = N3.c.f4574a;
        this.o = new N3.f();
    }

    public y(f2.d[] dVarArr) {
        this.l = 16;
        p2.z zVar = new p2.z();
        f2.g gVar = new f2.g();
        gVar.c = 1.0f;
        gVar.d = 1.0f;
        f2.b bVar = f2.b.f14547e;
        gVar.f14576e = bVar;
        gVar.f14577f = bVar;
        gVar.f14578g = bVar;
        gVar.f14579h = bVar;
        ByteBuffer byteBuffer = f2.d.f14550a;
        gVar.f14582k = byteBuffer;
        gVar.l = byteBuffer.asShortBuffer();
        gVar.f14583m = byteBuffer;
        gVar.f14575b = -1;
        f2.d[] dVarArr2 = new f2.d[dVarArr.length + 2];
        this.f15499m = dVarArr2;
        System.arraycopy(dVarArr, 0, dVarArr2, 0, dVarArr.length);
        this.f15500n = zVar;
        this.o = gVar;
        dVarArr2[dVarArr.length] = zVar;
        dVarArr2[dVarArr.length + 1] = gVar;
    }

    public y(A4.n nVar, ArrayList arrayList, F.l lVar) {
        this.l = 12;
        A4.h.c(lVar, "Argument must not be null");
        this.f15500n = lVar;
        A4.h.c(arrayList, "Argument must not be null");
        this.o = arrayList;
        this.f15499m = new com.bumptech.glide.load.data.i(nVar, lVar);
    }

    public y(ParcelFileDescriptor parcelFileDescriptor, ArrayList arrayList, F.l lVar) {
        this.l = 13;
        A4.h.c(lVar, "Argument must not be null");
        this.f15499m = lVar;
        A4.h.c(arrayList, "Argument must not be null");
        this.f15500n = arrayList;
        this.o = new com.bumptech.glide.load.data.i(parcelFileDescriptor);
    }

    public y(EnumC1445v enumC1445v) {
        this.l = 4;
        this.f15499m = enumC1445v;
        this.f15500n = null;
        this.o = null;
    }

    public y(EnumC1445v enumC1445v, byte[] bArr) {
        this.l = 4;
        this.f15499m = enumC1445v;
        this.f15500n = bArr;
        this.o = null;
    }

    public y(X1.a aVar, C2552b c2552b, C2602d c2602d) {
        EnumC2551a enumC2551a;
        int i7;
        int i10;
        int i11;
        int i12;
        this.l = 25;
        this.o = aVar;
        this.f15499m = new ArrayList();
        C2602d c2602d2 = c2602d;
        int i13 = 0;
        int i14 = 0;
        while (true) {
            enumC2551a = EnumC2551a.ECI;
            if (c2602d2 == null) {
                break;
            }
            int i15 = i13 + c2602d2.d;
            EnumC2551a enumC2551a2 = EnumC2551a.BYTE;
            EnumC2551a enumC2551a3 = c2602d2.f23331a;
            int i16 = c2602d2.c;
            C2602d c2602d3 = c2602d2.f23333e;
            boolean z6 = (enumC2551a3 == enumC2551a2 && c2602d3 == null && i16 != 0) || !(c2602d3 == null || i16 == c2602d3.c);
            i7 = z6 ? 1 : i14;
            if (c2602d3 == null || c2602d3.f23331a != enumC2551a3 || z6) {
                i11 = i7;
                ((ArrayList) this.f15499m).add(0, new C2603e(this, enumC2551a3, c2602d2.f23332b, i16, i15));
                i12 = 0;
            } else {
                i11 = i7;
                i12 = i15;
            }
            if (z6) {
                ((ArrayList) this.f15499m).add(0, new C2603e(this, enumC2551a, c2602d2.f23332b, c2602d2.c, 0));
            }
            i14 = i11;
            i13 = i12;
            c2602d2 = c2602d3;
        }
        if (aVar.f9971b) {
            C2603e c2603e = (C2603e) ((ArrayList) this.f15499m).get(0);
            if (c2603e != null && c2603e.f23335a != enumC2551a && i14 != 0) {
                ((ArrayList) this.f15499m).add(0, new C2603e(this, enumC2551a, 0, 0, 0));
            }
            ((ArrayList) this.f15499m).add(((C2603e) ((ArrayList) this.f15499m).get(0)).f23335a == enumC2551a ? 1 : 0, new C2603e(this, EnumC2551a.FNC1_FIRST_POSITION, 0, 0, 0));
        }
        int i17 = c2552b.f23017a;
        int i18 = 26;
        int iC = AbstractC2641j.c(i17 <= 9 ? 1 : i17 <= 26 ? 2 : 3);
        if (iC == 0) {
            i18 = 9;
        } else if (iC != 1) {
            i7 = 27;
            i18 = 40;
        } else {
            i7 = 10;
        }
        int iX = x(c2552b);
        while (true) {
            i10 = aVar.c;
            if (i17 >= i18 || AbstractC2600b.c(iX, C2552b.b(i17), i10)) {
                break;
            } else {
                i17++;
            }
        }
        while (i17 > i7 && AbstractC2600b.c(iX, C2552b.b(i17 - 1), i10)) {
            i17--;
        }
        this.f15500n = C2552b.b(i17);
    }

    public y(C1650j0 c1650j0) {
        this.l = 7;
        this.o = c1650j0;
        this.f15499m = new C1751g(this);
    }

    public y(AudioTrack audioTrack, C2090e c2090e) {
        this.l = 17;
        this.f15499m = audioTrack;
        this.f15500n = c2090e;
        this.o = new AudioRouting.OnRoutingChangedListener() { // from class: p2.t
            @Override // android.media.AudioRouting.OnRoutingChangedListener
            public final void onRoutingChanged(AudioRouting audioRouting) {
                h3.y yVar = this.f20774a;
                if (((t) yVar.o) == null || audioRouting.getRoutedDevice() == null) {
                    return;
                }
                ((C2090e) yVar.f15500n).b(audioRouting.getRoutedDevice());
            }
        };
        audioTrack.addOnRoutingChangedListener((p2.t) this.o, new Handler(Looper.myLooper()));
    }

    public y(p2.v vVar) {
        this.l = 18;
        this.o = vVar;
        this.f15499m = new Handler(Looper.myLooper());
        this.f15500n = new p2.u(this);
    }

    public y(A0 a02) {
        this.l = 8;
        this.f15500n = a02;
    }
}
