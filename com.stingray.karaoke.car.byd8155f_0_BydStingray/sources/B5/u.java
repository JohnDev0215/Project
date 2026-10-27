package B5;

import B2.C0069i;
import B2.C0073m;
import B2.InterfaceC0070j;
import F0.W0;
import G.C0297g;
import N1.C0420a;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.os.Handler;
import android.util.AttributeSet;
import android.util.SparseArray;
import android.view.MotionEvent;
import android.widget.ImageView;
import c5.C1026c;
import c5.C1030g;
import d1.C1059c;
import d5.AbstractC1074C;
import d5.C1077F;
import d5.M;
import d5.i0;
import h2.AbstractC1343A;
import h2.AbstractC1346c;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.CopyOnWriteArrayList;
import k3.C0;
import k3.C1679y0;
import k3.I0;
import k3.Q0;
import k3.RunnableC1667s0;
import n.AbstractC1850a;
import n2.RunnableC1918s;
import n7.AbstractC1996k;
import r.AbstractC2225M;
import r.C2258q;
import u.C2481u;
import u2.AbstractC2498a;
import u2.C2492C;
import u2.C2496G;
import u2.C2511n;
import u2.W;
import y0.C2730h;
import z2.C2759e;

/* JADX INFO: loaded from: classes.dex */
public class u implements G.K, InterfaceC0070j, h5.s {
    public final /* synthetic */ int l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public int f735m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public Object f736n;
    public Object o;

    public /* synthetic */ u(Object obj, int i7, Serializable serializable, int i10) {
        this.l = i10;
        this.f736n = obj;
        this.f735m = i7;
        this.o = serializable;
    }

    @Override // G.K
    public int a(Object obj) {
        C2481u c2481u = (C2481u) this.f736n;
        int iC = c2481u.c(obj);
        if (iC >= 0) {
            return c2481u.c[iC];
        }
        return -1;
    }

    public void b(AbstractC2498a abstractC2498a, long j3) {
        AbstractC1346c.i("Progressive media source must define an initial placeholder duration.", ((abstractC2498a instanceof W) && j3 == -9223372036854775807L) ? false : true);
        int i7 = this.f735m;
        this.f735m = i7 + 1;
        ((C1077F) this.f736n).a(new C2511n(abstractC2498a, i7, AbstractC1343A.H(j3)));
    }

    public void c(int i7, G.r rVar) {
        if (i7 < 0) {
            throw new IllegalArgumentException(android.hardware.bydauto.ac.tsimpl.a.e(i7, "size should be >=0, but was ").toString());
        }
        if (i7 == 0) {
            return;
        }
        C0297g c0297g = new C0297g(this.f735m, i7, rVar);
        this.f735m += i7;
        ((V.d) this.f736n).b(c0297g);
    }

    @Override // B2.InterfaceC0070j
    public C0069i d(C0073m c0073m, long j3) {
        long j10 = c0073m.o;
        int iMin = (int) Math.min(112800, c0073m.f588n - j10);
        h2.t tVar = (h2.t) this.o;
        tVar.E(iMin);
        c0073m.k(tVar.f15241a, 0, iMin, false);
        int i7 = tVar.c;
        long j11 = -1;
        long j12 = -1;
        long j13 = -9223372036854775807L;
        while (tVar.a() >= 188) {
            byte[] bArr = tVar.f15241a;
            int i10 = tVar.f15242b;
            while (i10 < i7 && bArr[i10] != 71) {
                i10++;
            }
            int i11 = i10 + 188;
            if (i11 > i7) {
                break;
            }
            long jK0 = io.sentry.config.a.k0(tVar, i10, this.f735m);
            if (jK0 != -9223372036854775807L) {
                long jB = ((h2.y) this.f736n).b(jK0);
                if (jB > j3) {
                    return j13 == -9223372036854775807L ? new C0069i(-1, jB, j10) : new C0069i(0, -9223372036854775807L, j10 + j12);
                }
                if (100000 + jB > j3) {
                    return new C0069i(0, -9223372036854775807L, j10 + ((long) i10));
                }
                j13 = jB;
                j12 = i10;
            }
            tVar.H(i11);
            j11 = i11;
        }
        return j13 != -9223372036854775807L ? new C0069i(-2, j13, j10 + j11) : C0069i.d;
    }

    @Override // B2.InterfaceC0070j
    public void e() {
        byte[] bArr = AbstractC1343A.f15198f;
        h2.t tVar = (h2.t) this.o;
        tVar.getClass();
        tVar.F(bArr.length, bArr);
    }

    public void f() {
        c9.m mVar;
        ImageView imageView = (ImageView) this.f736n;
        Drawable drawable = imageView.getDrawable();
        if (drawable != null) {
            AbstractC2225M.a(drawable);
        }
        if (drawable == null || (mVar = (c9.m) this.o) == null) {
            return;
        }
        C2258q.d(drawable, mVar, imageView.getDrawableState());
    }

    public d5.K g() {
        i0 i0Var;
        d5.J j3 = (d5.J) this.o;
        if (j3 != null) {
            throw j3.a();
        }
        int i7 = this.f735m;
        Object[] objArrCopyOf = (Object[]) this.f736n;
        if (i7 == 0) {
            i0Var = i0.f13428r;
        } else if (i7 == 1) {
            Objects.requireNonNull(objArrCopyOf[0]);
            Objects.requireNonNull(objArrCopyOf[1]);
            i0Var = new i0(1, null, objArrCopyOf);
        } else {
            P8.n.r(i7, objArrCopyOf.length >> 1);
            Object objG = i0.g(objArrCopyOf, i7, M.t(i7), 0);
            if (objG instanceof Object[]) {
                Object[] objArr = (Object[]) objG;
                this.o = (d5.J) objArr[2];
                Object obj = objArr[0];
                int iIntValue = ((Integer) objArr[1]).intValue();
                objArrCopyOf = Arrays.copyOf(objArrCopyOf, iIntValue * 2);
                objG = obj;
                i7 = iIntValue;
            }
            i0Var = new i0(i7, objG, objArrCopyOf);
        }
        d5.J j10 = (d5.J) this.o;
        if (j10 == null) {
            return i0Var;
        }
        throw j10.a();
    }

    @Override // h5.s
    public void h(Throwable th) {
    }

    @Override // h5.s
    public void i(Object obj) {
        List list = (List) obj;
        I0 i02 = ((Q0) this.o).f17843f;
        Handler handler = i02.l;
        C1679y0 c1679y0 = (C1679y0) this.f736n;
        AbstractC1343A.K(handler, new C0(i02, c1679y0, new RunnableC1667s0(this.f735m, 1, this, list, c1679y0)));
    }

    public void j(int i7) {
        if (i7 < 0 || i7 >= this.f735m) {
            StringBuilder sbJ = M5.b.j("Index ", i7, ", size ");
            sbJ.append(this.f735m);
            throw new IndexOutOfBoundsException(sbJ.toString());
        }
    }

    public void k(h2.g gVar) {
        for (C2496G c2496g : (CopyOnWriteArrayList) this.o) {
            AbstractC1343A.K(c2496g.f22671a, new RunnableC1918s(gVar, 5, c2496g.f22672b));
        }
    }

    /* JADX WARN: Type inference failed for: r0v0, types: [java.lang.Object, java.util.List] */
    public void l(C2730h c2730h) {
        ?? r02 = c2730h.f24065a;
        int size = r02.size();
        int i7 = 0;
        while (true) {
            y0.t tVar = (y0.t) this.o;
            if (i7 >= size) {
                C0.r rVar = (C0.r) this.f736n;
                if (rVar == null) {
                    throw new IllegalStateException("layoutCoordinates not set");
                }
                long jN = rVar.N(0L);
                MotionEvent motionEventA = c2730h.a();
                if (motionEventA == null) {
                    throw new IllegalArgumentException("The PointerEvent receiver cannot have a null MotionEvent.");
                }
                int action = motionEventA.getAction();
                motionEventA.offsetLocation(-m0.c.d(jN), -m0.c.e(jN));
                if (motionEventA.getActionMasked() == 0) {
                    this.f735m = ((Boolean) ((C1059c) tVar.i()).b(motionEventA)).booleanValue() ? 2 : 3;
                } else {
                    ((C1059c) tVar.i()).b(motionEventA);
                }
                motionEventA.offsetLocation(m0.c.d(jN), m0.c.e(jN));
                motionEventA.setAction(action);
                if (this.f735m == 2) {
                    int size2 = r02.size();
                    for (int i10 = 0; i10 < size2; i10++) {
                        ((y0.q) r02.get(i10)).a();
                    }
                    t4.e eVar = c2730h.f24066b;
                    if (eVar == null) {
                        return;
                    }
                    eVar.f22462m = !tVar.c;
                    return;
                }
                return;
            }
            if (((y0.q) r02.get(i7)).b()) {
                if (this.f735m == 2) {
                    C0.r rVar2 = (C0.r) this.f736n;
                    if (rVar2 == null) {
                        throw new IllegalStateException("layoutCoordinates not set");
                    }
                    long jN2 = rVar2.N(0L);
                    MotionEvent motionEventA2 = c2730h.a();
                    if (motionEventA2 == null) {
                        throw new IllegalArgumentException("The PointerEvent receiver cannot have a null MotionEvent.");
                    }
                    int action2 = motionEventA2.getAction();
                    motionEventA2.setAction(3);
                    motionEventA2.offsetLocation(-m0.c.d(jN2), -m0.c.e(jN2));
                    ((C1059c) tVar.i()).b(motionEventA2);
                    motionEventA2.offsetLocation(m0.c.d(jN2), m0.c.e(jN2));
                    motionEventA2.setAction(action2);
                }
                this.f735m = 3;
                return;
            }
            i7++;
        }
    }

    public C0297g m(int i7) {
        j(i7);
        C0297g c0297g = (C0297g) this.o;
        if (c0297g != null) {
            int i10 = c0297g.f2561b;
            int i11 = c0297g.f2560a;
            if (i7 < i10 + i11 && i11 <= i7) {
                return c0297g;
            }
        }
        V.d dVar = (V.d) this.f736n;
        C0297g c0297g2 = (C0297g) dVar.l[G.E.e(i7, dVar)];
        this.o = c0297g2;
        return c0297g2;
    }

    public Object n(int i7) {
        SparseArray sparseArray;
        if (this.f735m == -1) {
            this.f735m = 0;
        }
        while (true) {
            int i10 = this.f735m;
            sparseArray = (SparseArray) this.f736n;
            if (i10 <= 0 || i7 >= sparseArray.keyAt(i10)) {
                break;
            }
            this.f735m--;
        }
        while (this.f735m < sparseArray.size() - 1 && i7 >= sparseArray.keyAt(this.f735m + 1)) {
            this.f735m++;
        }
        return sparseArray.valueAt(this.f735m);
    }

    public Object o(int i7) {
        int i10 = i7 - this.f735m;
        if (i10 >= 0) {
            Object[] objArr = (Object[]) this.o;
            if (i10 <= AbstractC1996k.Q(objArr)) {
                return objArr[i10];
            }
        }
        return null;
    }

    public int p() {
        int i7 = this.f735m;
        if (i7 != 2) {
            return i7 != 3 ? 0 : 512;
        }
        return 2048;
    }

    public void q(AttributeSet attributeSet, int i7) {
        int resourceId;
        ImageView imageView = (ImageView) this.f736n;
        Context context = imageView.getContext();
        int[] iArr = AbstractC1850a.f19354e;
        h3.y yVarF = h3.y.F(context, attributeSet, iArr, i7);
        Context context2 = imageView.getContext();
        int[] iArr2 = F1.H.f2303a;
        F1.E.b(imageView, context2, iArr, attributeSet, (TypedArray) yVarF.f15500n, i7, 0);
        try {
            Drawable drawable = imageView.getDrawable();
            TypedArray typedArray = (TypedArray) yVarF.f15500n;
            if (drawable == null && (resourceId = typedArray.getResourceId(1, -1)) != -1 && (drawable = h6.f.k(imageView.getContext(), resourceId)) != null) {
                imageView.setImageDrawable(drawable);
            }
            if (drawable != null) {
                AbstractC2225M.a(drawable);
            }
            if (typedArray.hasValue(2)) {
                imageView.setImageTintList(yVarF.p(2));
            }
            if (typedArray.hasValue(3)) {
                imageView.setImageTintMode(AbstractC2225M.b(typedArray.getInt(3, -1), null));
            }
            yVarF.G();
        } catch (Throwable th) {
            yVarF.G();
            throw th;
        }
    }

    public void r(C5.d dVar) {
        List list = (List) this.f736n;
        int size = list.size();
        t tVar = (t) this.o;
        int i7 = this.f735m;
        if (i7 >= size) {
            tVar.f(dVar);
            return;
        }
        u uVar = new u(i7 + 1, dVar, list, tVar);
        A6.a aVar = (A6.a) list.get(i7);
        aVar.getClass();
        if (dVar instanceof C5.i) {
            C5.i iVar = (C5.i) dVar;
            iVar.getClass();
            C5.h hVar = new C5.h(iVar, 0);
            hVar.f1007i = iVar.c("name");
            hVar.f1008j = (z) iVar.d(z.class, "properties");
            z zVar = (z) iVar.d(z.class, "properties");
            A7.m.e(zVar, "properties(...)");
            hVar.f1008j = Collections.unmodifiableMap(new LinkedHashMap(aVar.a(zVar)));
            dVar = hVar.a();
        } else if (dVar instanceof C5.j) {
            C5.j jVar = (C5.j) dVar;
            jVar.getClass();
            C5.h hVar2 = new C5.h(jVar, 1);
            hVar2.f1007i = jVar.c("event");
            hVar2.f1008j = (z) jVar.d(z.class, "properties");
            z zVar2 = (z) jVar.d(z.class, "properties");
            A7.m.e(zVar2, "properties(...)");
            hVar2.f1008j = Collections.unmodifiableMap(new LinkedHashMap(aVar.a(zVar2)));
            dVar = hVar2.a();
        }
        uVar.r(dVar);
    }

    public u s(Object obj, Object obj2) {
        int i7 = (this.f735m + 1) * 2;
        Object[] objArr = (Object[]) this.f736n;
        if (i7 > objArr.length) {
            this.f736n = Arrays.copyOf(objArr, AbstractC1074C.g(objArr.length, i7));
        }
        if (obj == null) {
            throw new NullPointerException("null key in entry: null=" + obj2);
        }
        if (obj2 == null) {
            throw new NullPointerException("null value in entry: " + obj + "=null");
        }
        Object[] objArr2 = (Object[]) this.f736n;
        int i10 = this.f735m;
        int i11 = i10 * 2;
        objArr2[i11] = obj;
        objArr2[i11 + 1] = obj2;
        this.f735m = i10 + 1;
        return this;
    }

    public List t(CharSequence charSequence) {
        charSequence.getClass();
        C0420a c0420a = (C0420a) this.o;
        c0420a.getClass();
        C1030g c1030g = new C1030g(c0420a, this, charSequence);
        ArrayList arrayList = new ArrayList();
        while (c1030g.hasNext()) {
            arrayList.add((String) c1030g.next());
        }
        return Collections.unmodifiableList(arrayList);
    }

    public String toString() {
        switch (this.l) {
            case 11:
                StringBuilder sb = new StringBuilder();
                if (((c9.D) this.f736n) == c9.D.HTTP_1_0) {
                    sb.append("HTTP/1.0");
                } else {
                    sb.append("HTTP/1.1");
                }
                sb.append(' ');
                sb.append(this.f735m);
                sb.append(' ');
                sb.append((String) this.o);
                String string = sb.toString();
                A7.m.e(string, "StringBuilder().apply(builderAction).toString()");
                return string;
            default:
                return super.toString();
        }
    }

    public u(int i7, C5.d dVar, List list, t tVar) {
        this.l = 0;
        this.f735m = i7;
        this.f736n = list;
        this.o = tVar;
    }

    public u(y0.t tVar) {
        this.l = 17;
        this.o = tVar;
        this.f735m = 1;
    }

    public u(ImageView imageView) {
        this.l = 13;
        this.f735m = 0;
        this.f736n = imageView;
    }

    public u(u2.I i7) {
        this.l = 16;
        this.f736n = new SparseArray();
        this.o = i7;
        this.f735m = -1;
    }

    /* JADX WARN: Removed duplicated region for block: B:16:0x0080  */
    /* JADX WARN: Removed duplicated region for block: B:19:0x0094 A[LOOP:1: B:13:0x0072->B:19:0x0094, LOOP_END] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public u(G7.d r13, G.E r14) {
        /*
            Method dump skipped, instruction units count: 205
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: B5.u.<init>(G7.d, G.E):void");
    }

    public u(int i7, h2.y yVar) {
        this.l = 9;
        this.f735m = i7;
        this.f736n = yVar;
        this.o = new h2.t();
    }

    /* JADX WARN: 'this' call moved to the top of the method (can break code semantics) */
    public u(int i7, byte b10) {
        this(4);
        this.l = i7;
        switch (i7) {
            case 6:
                break;
            case 7:
                break;
            case 14:
                this.f736n = d5.I.u();
                break;
            default:
                this.f736n = new V.d(new C0297g[16]);
                break;
        }
    }

    public u(C0420a c0420a) {
        this.l = 5;
        this.o = c0420a;
        this.f736n = C1026c.l;
        this.f735m = Integer.MAX_VALUE;
    }

    public u(int i7, String str, int i10, ArrayList arrayList, byte[] bArr) {
        List listUnmodifiableList;
        this.l = 10;
        this.f735m = i10;
        if (arrayList == null) {
            listUnmodifiableList = Collections.emptyList();
        } else {
            listUnmodifiableList = Collections.unmodifiableList(arrayList);
        }
        this.f736n = listUnmodifiableList;
        this.o = bArr;
    }

    public u(ByteArrayInputStream byteArrayInputStream) throws IOException {
        this.l = 19;
        this.f735m = byteArrayInputStream.read();
        A4.b bVar = new A4.b(byteArrayInputStream);
        this.f736n = bVar;
        int i7 = bVar.f413b;
        byte[] bArr = new byte[i7];
        this.o = bArr;
        if (i7 > 0) {
            byteArrayInputStream.read(bArr);
        }
    }

    public u(CopyOnWriteArrayList copyOnWriteArrayList, int i7, C2492C c2492c) {
        this.l = 15;
        this.o = copyOnWriteArrayList;
        this.f735m = i7;
        this.f736n = c2492c;
    }

    public u(W0 w02) {
        this.l = 3;
        this.f736n = w02;
    }

    public u(int i7) {
        this.l = 6;
        this.f736n = new Object[i7 * 2];
        this.f735m = 0;
    }

    public u(A4.i iVar) {
        this.l = 8;
        this.o = B4.d.a(150, new e7.f(this, 2));
        this.f736n = iVar;
    }

    public u(Q0 q02, C1679y0 c1679y0, int i7) {
        this.l = 12;
        this.o = q02;
        this.f736n = c1679y0;
        this.f735m = i7;
    }

    public u(C2759e c2759e, s2.k kVar, int i7, long j3) {
        this.l = 18;
        this.o = c2759e;
        this.f736n = kVar;
        this.f735m = i7;
    }
}
