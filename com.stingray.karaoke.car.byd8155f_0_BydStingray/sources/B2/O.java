package B2;

import B5.C0083g;
import B8.C0092d;
import B8.InterfaceC0093e;
import F0.AbstractC0207i;
import F1.C0247f;
import F1.InterfaceC0244c;
import F1.InterfaceC0246e;
import F1.W;
import F1.X;
import F1.Y;
import F8.AbstractC0287w;
import K.AbstractC0349k0;
import K.T;
import K.U;
import K7.Z;
import K7.c0;
import K7.f0;
import K7.r0;
import Q7.AbstractC0669w;
import Q7.C0661n;
import Q7.InterfaceC0652e;
import Q7.InterfaceC0659l;
import Q7.InterfaceC0665s;
import Q7.InterfaceC0666t;
import T7.AbstractC0789h;
import T7.C0790i;
import T7.Q;
import android.R;
import android.content.ClipData;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Parcel;
import android.view.ContentInfo;
import android.view.KeyEvent;
import android.view.View;
import android.view.Window;
import android.view.accessibility.AccessibilityNodeInfo;
import android.view.inputmethod.InputMethodManager;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.locks.Lock;
import n0.AbstractC1858H;
import n0.C1855E;
import n0.C1880i;
import n0.InterfaceC1866P;
import o8.C2057b;
import o8.C2058c;
import o8.C2061f;
import w0.C2586a;
import x3.AbstractC2673a;

/* JADX INFO: loaded from: classes.dex */
public class O implements B5.t, InterfaceC0093e, E8.n, InterfaceC0244c, InterfaceC0246e, InterfaceC0665s, I3.g, T, InterfaceC1866P, InterfaceC0659l {
    public final /* synthetic */ int l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public Object f527m;

    public /* synthetic */ O(Object obj, int i7) {
        this.l = i7;
        this.f527m = obj;
    }

    public static K3.e Y(K3.i iVar, Throwable th) {
        Drawable drawable;
        if (th instanceof K3.l) {
            K3.c cVar = iVar.f3563z;
            drawable = cVar.l;
            K3.c cVar2 = N3.c.f4574a;
            if (drawable == null) {
                drawable = cVar.f3521k;
            }
        } else {
            drawable = iVar.f3563z.f3521k;
            K3.c cVar3 = N3.c.f4574a;
        }
        return new K3.e(drawable, iVar, th);
    }

    public static K3.p d0(F3.j jVar, K3.i iVar, I3.a aVar, I3.b bVar) {
        BitmapDrawable bitmapDrawable = new BitmapDrawable(iVar.f3542a.getResources(), bVar.f2932a);
        B3.f fVar = B3.f.l;
        Map map = bVar.f2933b;
        Object obj = map.get("coil#disk_cache_key");
        String str = obj instanceof String ? (String) obj : null;
        Object obj2 = map.get("coil#is_sampled");
        Boolean bool = obj2 instanceof Boolean ? (Boolean) obj2 : null;
        boolean zBooleanValue = bool != null ? bool.booleanValue() : false;
        Bitmap.Config config = N3.d.f4575a;
        return new K3.p(bitmapDrawable, iVar, fVar, aVar, str, zBooleanValue, jVar != null && jVar.f2404a);
    }

    public static O e0(int i7, int i10, int i11) {
        return new O(AccessibilityNodeInfo.CollectionInfo.obtain(i7, i10, false, i11), 13);
    }

    public static O f0(boolean z6, int i7, int i10, int i11, int i12) {
        return new O(AccessibilityNodeInfo.CollectionItemInfo.obtain(i7, i10, i11, i12, false, z6), 14);
    }

    @Override // F1.InterfaceC0246e
    public ContentInfo A() {
        return (ContentInfo) this.f527m;
    }

    @Override // Q7.InterfaceC0659l
    public Object B(C0790i c0790i, Object obj) {
        return C(c0790i, obj);
    }

    @Override // Q7.InterfaceC0659l
    public Object C(InterfaceC0666t interfaceC0666t, Object obj) {
        return new K7.G((K7.E) this.f527m, interfaceC0666t);
    }

    @Override // F1.InterfaceC0244c
    public void D(Uri uri) {
        ((ContentInfo.Builder) this.f527m).setLinkUri(uri);
    }

    @Override // Q7.InterfaceC0665s
    public InterfaceC0665s E(AbstractC0287w abstractC0287w) {
        A7.m.f(abstractC0287w, "type");
        return this;
    }

    @Override // Q7.InterfaceC0665s
    public InterfaceC0665s F(int i7) {
        A7.k.p(i7, "modality");
        return this;
    }

    @Override // Q7.InterfaceC0659l
    public Object G(T7.z zVar, Object obj) {
        return null;
    }

    @Override // Q7.InterfaceC0665s
    public InterfaceC0665s H() {
        return this;
    }

    @Override // Q7.InterfaceC0659l
    public Object I(T7.y yVar, Object obj) {
        return null;
    }

    @Override // Q7.InterfaceC0665s
    public InterfaceC0665s J() {
        return this;
    }

    @Override // Q7.InterfaceC0659l
    public Object K(Q q4, Object obj) {
        return null;
    }

    @Override // Q7.InterfaceC0665s
    public InterfaceC0665s L(InterfaceC0652e interfaceC0652e) {
        A7.m.f(interfaceC0652e, "owner");
        return this;
    }

    @Override // Q7.InterfaceC0659l
    public Object M(T7.H h10, Object obj) {
        A7.m.f(h10, "descriptor");
        int i7 = (h10.f8794F != null ? 1 : 0) + (h10.f8795G != null ? 1 : 0);
        boolean z6 = h10.f8801r;
        K7.E e10 = (K7.E) this.f527m;
        if (z6) {
            if (i7 == 0) {
                return new K7.I(e10, h10);
            }
            if (i7 == 1) {
                return new K7.L(e10, h10);
            }
            if (i7 == 2) {
                return new K7.N(e10, h10);
            }
        } else {
            if (i7 == 0) {
                return new Z(e10, h10);
            }
            if (i7 == 1) {
                return new c0(e10, h10);
            }
            if (i7 == 2) {
                return new f0(e10, h10);
            }
        }
        throw new r0("Unsupported property: " + h10);
    }

    @Override // I3.g
    public void N(I3.a aVar, Bitmap bitmap, Map map) {
        ((C1.j) this.f527m).r(aVar, bitmap, map, com.bumptech.glide.d.B(bitmap));
    }

    @Override // Q7.InterfaceC0659l
    public Object O(T7.I i7, Object obj) {
        return C(i7, obj);
    }

    @Override // K.T
    public int P(KeyEvent keyEvent) {
        int i7 = 0;
        if (keyEvent.isShiftPressed() && keyEvent.isCtrlPressed()) {
            long jA = h6.r.a(keyEvent.getKeyCode());
            if (C2586a.a(jA, AbstractC0349k0.f3319i)) {
                i7 = 35;
            } else if (C2586a.a(jA, AbstractC0349k0.f3320j)) {
                i7 = 36;
            } else if (C2586a.a(jA, AbstractC0349k0.f3321k)) {
                i7 = 38;
            } else if (C2586a.a(jA, AbstractC0349k0.l)) {
                i7 = 37;
            }
        } else if (keyEvent.isCtrlPressed()) {
            long jA2 = h6.r.a(keyEvent.getKeyCode());
            if (C2586a.a(jA2, AbstractC0349k0.f3319i)) {
                i7 = 4;
            } else if (C2586a.a(jA2, AbstractC0349k0.f3320j)) {
                i7 = 3;
            } else if (C2586a.a(jA2, AbstractC0349k0.f3321k)) {
                i7 = 6;
            } else if (C2586a.a(jA2, AbstractC0349k0.l)) {
                i7 = 5;
            } else if (C2586a.a(jA2, AbstractC0349k0.c)) {
                i7 = 20;
            } else if (C2586a.a(jA2, AbstractC0349k0.f3328t)) {
                i7 = 23;
            } else if (C2586a.a(jA2, AbstractC0349k0.f3327s)) {
                i7 = 22;
            } else if (C2586a.a(jA2, AbstractC0349k0.f3318h)) {
                i7 = 43;
            }
        } else if (keyEvent.isShiftPressed()) {
            long jA3 = h6.r.a(keyEvent.getKeyCode());
            if (C2586a.a(jA3, AbstractC0349k0.o)) {
                i7 = 41;
            } else if (C2586a.a(jA3, AbstractC0349k0.f3324p)) {
                i7 = 42;
            }
        } else if (keyEvent.isAltPressed()) {
            long jA4 = h6.r.a(keyEvent.getKeyCode());
            if (C2586a.a(jA4, AbstractC0349k0.f3327s)) {
                i7 = 24;
            } else if (C2586a.a(jA4, AbstractC0349k0.f3328t)) {
                i7 = 25;
            }
        }
        return i7 == 0 ? ((U) this.f527m).P(keyEvent) : i7;
    }

    @Override // Q7.InterfaceC0665s
    public InterfaceC0665s Q() {
        return this;
    }

    @Override // F1.InterfaceC0244c
    public void R(int i7) {
        ((ContentInfo.Builder) this.f527m).setFlags(i7);
    }

    public void S(int i7, G1.e eVar, String str, Bundle bundle) {
    }

    public C3.g T() {
        B5.G gI;
        A2.c cVar = (A2.c) this.f527m;
        C3.e eVar = (C3.e) cVar.f312e;
        synchronized (eVar) {
            cVar.d(true);
            gI = eVar.i(((C3.b) cVar.c).f971a);
        }
        if (gI != null) {
            return new C3.g(gI);
        }
        return null;
    }

    public G1.e U(int i7) {
        return null;
    }

    public void V(byte b10) {
        ((Parcel) this.f527m).writeByte(b10);
    }

    public void W(float f10) {
        ((Parcel) this.f527m).writeFloat(f10);
    }

    public void X(long j3) {
        long jB = a1.m.b(j3);
        byte b10 = 0;
        if (!a1.n.a(jB, 0L)) {
            if (a1.n.a(jB, 4294967296L)) {
                b10 = 1;
            } else if (a1.n.a(jB, 8589934592L)) {
                b10 = 2;
            }
        }
        V(b10);
        if (a1.n.a(a1.m.b(j3), 0L)) {
            return;
        }
        W(a1.m.c(j3));
    }

    public G1.e Z(int i7) {
        return null;
    }

    @Override // Q7.InterfaceC0665s
    /* JADX INFO: renamed from: a, reason: collision with other method in class */
    public InterfaceC0666t mo0a() {
        return (H8.b) this.f527m;
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r8v0 */
    /* JADX WARN: Type inference failed for: r8v10 */
    /* JADX WARN: Type inference failed for: r8v4 */
    /* JADX WARN: Type inference failed for: r8v5 */
    /* JADX WARN: Type inference failed for: r8v6 */
    /* JADX WARN: Type inference failed for: r9v0 */
    /* JADX WARN: Type inference failed for: r9v1 */
    /* JADX WARN: Type inference failed for: r9v2 */
    public I3.b a0(K3.i iVar, I3.a aVar, L3.g gVar, L3.f fVar) {
        I3.b bVarX;
        L3.f fVar2;
        int i7;
        I3.b bVar;
        int i10;
        ?? Equals;
        ?? r92;
        I3.b bVar2;
        if (!iVar.f3552n.l) {
            return null;
        }
        I3.c cVar = (I3.c) ((z3.i) this.f527m).c.getValue();
        if (cVar != null) {
            bVarX = cVar.f2934a.x(aVar);
            if (bVarX == null) {
                C1.j jVar = cVar.f2935b;
                synchronized (jVar) {
                    try {
                        ArrayList arrayList = (ArrayList) ((LinkedHashMap) jVar.f944m).get(aVar);
                        bVar2 = null;
                        if (arrayList != null) {
                            int size = arrayList.size();
                            int i11 = 0;
                            while (true) {
                                if (i11 >= size) {
                                    break;
                                }
                                I3.f fVar3 = (I3.f) arrayList.get(i11);
                                Bitmap bitmap = (Bitmap) fVar3.f2940b.get();
                                I3.b bVar3 = bitmap != null ? new I3.b(bitmap, fVar3.c) : null;
                                if (bVar3 != null) {
                                    bVar2 = bVar3;
                                    break;
                                }
                                i11++;
                            }
                            int i12 = jVar.l;
                            jVar.l = i12 + 1;
                            if (i12 >= 10) {
                                jVar.h();
                            }
                        }
                    } catch (Throwable th) {
                        throw th;
                    }
                }
                bVarX = bVar2;
            }
        } else {
            bVarX = null;
        }
        if (bVarX != null) {
            Bitmap bitmap2 = bVarX.f2932a;
            Bitmap.Config config = bitmap2.getConfig();
            if (config == null) {
                config = Bitmap.Config.ARGB_8888;
            }
            if (config != Bitmap.Config.HARDWARE || iVar.f3550k) {
                Object obj = bVarX.f2933b.get("coil#is_sampled");
                Boolean bool = obj instanceof Boolean ? (Boolean) obj : null;
                boolean zBooleanValue = bool != null ? bool.booleanValue() : false;
                if (!A7.m.a(gVar, L3.g.c)) {
                    String str = (String) aVar.f2931m.get("coil#transformation_size");
                    if (str != null) {
                        bVar = bVarX;
                        Equals = str.equals(gVar.toString());
                    } else {
                        int width = bitmap2.getWidth();
                        int height = bitmap2.getHeight();
                        com.bumptech.glide.d dVar = gVar.f4005a;
                        int i13 = dVar instanceof L3.a ? ((L3.a) dVar).d : Integer.MAX_VALUE;
                        com.bumptech.glide.d dVar2 = gVar.f4006b;
                        if (dVar2 instanceof L3.a) {
                            i7 = ((L3.a) dVar2).d;
                            fVar2 = fVar;
                        } else {
                            fVar2 = fVar;
                            i7 = Integer.MAX_VALUE;
                        }
                        double dZ = AbstractC2673a.z(width, height, i13, i7, fVar2);
                        boolean zA = N3.c.a(iVar);
                        if (zA) {
                            bVar = bVarX;
                            double d = dZ > 1.0d ? 1.0d : dZ;
                            if (Math.abs(((double) i13) - (((double) width) * d)) > 1.0d && Math.abs(((double) i7) - (d * ((double) height))) > 1.0d) {
                                i10 = 1;
                            }
                            Equals = 1;
                        } else {
                            bVar = bVarX;
                            if (i13 == Integer.MIN_VALUE || i13 == Integer.MAX_VALUE) {
                                i10 = 1;
                            } else {
                                i10 = 1;
                                if (Math.abs(i13 - width) <= 1) {
                                }
                            }
                            if (i7 != Integer.MIN_VALUE && i7 != Integer.MAX_VALUE && Math.abs(i7 - height) > i10) {
                            }
                        }
                        Equals = (!(dZ == 1.0d || zA) || (dZ > 1.0d && zBooleanValue)) ? 0 : i10;
                    }
                } else if (zBooleanValue) {
                    bVar = bVarX;
                } else {
                    bVar = bVarX;
                    Equals = 1;
                }
                r92 = Equals;
            } else {
                bVar = bVarX;
                r92 = 0;
            }
            if (r92 != 0) {
                return bVar;
            }
        }
        return null;
    }

    @Override // I3.g
    public void b(int i7) {
    }

    public void b0() {
        View view = (View) this.f527m;
        if (view != null) {
            ((InputMethodManager) view.getContext().getSystemService("input_method")).hideSoftInputFromWindow(view.getWindowToken(), 0);
        }
    }

    @Override // Q7.InterfaceC0665s
    public InterfaceC0665s c() {
        return this;
    }

    public I3.a c0(K3.i iVar, Object obj, K3.m mVar, z3.c cVar) {
        String strA;
        Map linkedHashMap;
        iVar.getClass();
        List list = ((z3.i) this.f527m).f24401f.c;
        int size = list.size();
        int i7 = 0;
        while (true) {
            if (i7 >= size) {
                strA = null;
                break;
            }
            m7.i iVar2 = (m7.i) list.get(i7);
            G3.b bVar = (G3.b) iVar2.l;
            if (((Class) iVar2.f19259m).isAssignableFrom(obj.getClass())) {
                A7.m.d(bVar, "null cannot be cast to non-null type coil.key.Keyer<kotlin.Any>");
                strA = bVar.a(obj, mVar);
                if (strA != null) {
                    break;
                }
            }
            i7++;
        }
        if (strA == null) {
            return null;
        }
        Map map = iVar.f3561x.l;
        boolean zIsEmpty = map.isEmpty();
        n7.v vVar = n7.v.l;
        if (zIsEmpty) {
            linkedHashMap = vVar;
        } else {
            linkedHashMap = new LinkedHashMap();
            Iterator it = map.entrySet().iterator();
            if (it.hasNext()) {
                throw android.hardware.bydauto.ac.tsimpl.a.c((Map.Entry) it.next());
            }
        }
        iVar.f3545f.getClass();
        return linkedHashMap.isEmpty() ? new I3.a(strA, vVar) : new I3.a(strA, n7.z.z(linkedHashMap));
    }

    @Override // Q7.InterfaceC0665s
    public InterfaceC0665s d(T7.u uVar) {
        return this;
    }

    @Override // Q7.InterfaceC0659l
    public Object e(T7.J j3, Object obj) {
        return C(j3, obj);
    }

    @Override // B5.t
    public void f(C5.d dVar) {
        C0083g c0083g = (C0083g) this.f527m;
        c0083g.f699i.t("Running payload %s.", dVar);
        C0083g.f692z.post(new h5.t(1, c0083g, new B5.s(c0083g.f695e, dVar), false));
    }

    @Override // F1.InterfaceC0246e
    public int g() {
        return ((ContentInfo) this.f527m).getSource();
    }

    /* JADX WARN: Removed duplicated region for block: B:22:0x0042  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public K3.m g0(K3.i r18, L3.g r19) {
        /*
            r17 = this;
            r0 = r18
            r4 = r19
            n7.u r1 = r0.f3545f
            r1.getClass()
            android.graphics.Bitmap$Config r1 = android.graphics.Bitmap.Config.HARDWARE
            android.graphics.Bitmap$Config r2 = r0.d
            if (r2 != r1) goto L18
            if (r2 != r1) goto L18
            boolean r1 = r0.f3550k
            if (r1 != 0) goto L18
            android.graphics.Bitmap$Config r1 = android.graphics.Bitmap.Config.ARGB_8888
            r2 = r1
        L18:
            com.bumptech.glide.d r1 = r4.f4005a
            L3.b r3 = L3.b.d
            boolean r1 = r1.equals(r3)
            if (r1 != 0) goto L2f
            com.bumptech.glide.d r1 = r4.f4006b
            boolean r1 = r1.equals(r3)
            if (r1 == 0) goto L2b
            goto L2f
        L2b:
            L3.f r1 = r0.f3560w
        L2d:
            r5 = r1
            goto L32
        L2f:
            L3.f r1 = L3.f.f4003m
            goto L2d
        L32:
            boolean r1 = r0.l
            if (r1 == 0) goto L42
            n7.u r1 = r0.f3545f
            r1.getClass()
            android.graphics.Bitmap$Config r1 = android.graphics.Bitmap.Config.ALPHA_8
            if (r2 == r1) goto L42
            r1 = 1
        L40:
            r7 = r1
            goto L44
        L42:
            r1 = 0
            goto L40
        L44:
            K3.m r16 = new K3.m
            boolean r6 = N3.c.a(r18)
            K3.q r11 = r0.f3548i
            K3.o r12 = r0.f3561x
            K3.b r14 = r0.o
            K3.b r15 = r0.f3553p
            android.content.Context r1 = r0.f3542a
            r3 = 0
            boolean r8 = r0.f3551m
            r9 = 0
            c9.u r10 = r0.f3547h
            K3.b r13 = r0.f3552n
            r0 = r16
            r4 = r19
            r0.<init>(r1, r2, r3, r4, r5, r6, r7, r8, r9, r10, r11, r12, r13, r14, r15)
            return r16
        */
        throw new UnsupportedOperationException("Method not decompiled: B2.O.g0(K3.i, L3.g):K3.m");
    }

    @Override // Q7.InterfaceC0665s
    public InterfaceC0665s h(List list) {
        return this;
    }

    public boolean h0(int i7, int i10, Bundle bundle) {
        return false;
    }

    @Override // Q7.InterfaceC0665s
    public InterfaceC0665s i(R7.h hVar) {
        A7.m.f(hVar, "additionalAnnotations");
        return this;
    }

    public void i0() {
        View viewFindViewById;
        View view = (View) this.f527m;
        if (view == null) {
            return;
        }
        if (view.isInEditMode() || view.onCheckIsTextEditor()) {
            view.requestFocus();
            viewFindViewById = view;
        } else {
            viewFindViewById = view.getRootView().findFocus();
        }
        if (viewFindViewById == null) {
            viewFindViewById = view.getRootView().findViewById(R.id.content);
        }
        if (viewFindViewById == null || !viewFindViewById.hasWindowFocus()) {
            return;
        }
        viewFindViewById.post(new A2.l(viewFindViewById, 3));
    }

    @Override // F1.InterfaceC0246e
    public int j() {
        return ((ContentInfo) this.f527m).getFlags();
    }

    /* JADX WARN: Removed duplicated region for block: B:14:0x0023  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public K3.m j0(K3.m r18) {
        /*
            r17 = this;
            r0 = r18
            android.graphics.Bitmap$Config r2 = r0.f3566b
            K3.b r1 = r0.o
            android.graphics.Bitmap$Config r3 = android.graphics.Bitmap.Config.HARDWARE
            boolean r3 = r1.l
            if (r3 == 0) goto L23
            r3 = r17
            java.lang.Object r3 = r3.f527m
            N3.h r3 = (N3.h) r3
            monitor-enter(r3)
            r3.a()     // Catch: java.lang.Throwable -> L20
            boolean r4 = r3.f4586p     // Catch: java.lang.Throwable -> L20
            monitor-exit(r3)
            if (r4 != 0) goto L23
            K3.b r1 = K3.b.o
            r3 = 1
        L1e:
            r15 = r1
            goto L25
        L20:
            r0 = move-exception
            monitor-exit(r3)     // Catch: java.lang.Throwable -> L20
            throw r0
        L23:
            r3 = 0
            goto L1e
        L25:
            if (r3 == 0) goto L49
            android.content.Context r1 = r0.f3565a
            android.graphics.ColorSpace r3 = r0.c
            L3.g r4 = r0.d
            L3.f r5 = r0.f3567e
            boolean r6 = r0.f3568f
            boolean r7 = r0.f3569g
            boolean r8 = r0.f3570h
            java.lang.String r9 = r0.f3571i
            c9.u r10 = r0.f3572j
            K3.q r11 = r0.f3573k
            K3.o r12 = r0.l
            K3.b r13 = r0.f3574m
            K3.b r14 = r0.f3575n
            K3.m r16 = new K3.m
            r0 = r16
            r0.<init>(r1, r2, r3, r4, r5, r6, r7, r8, r9, r10, r11, r12, r13, r14, r15)
            return r16
        L49:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: B2.O.j0(K3.m):K3.m");
    }

    @Override // F1.InterfaceC0246e
    public ClipData k() {
        return ((ContentInfo) this.f527m).getClip();
    }

    @Override // Q7.InterfaceC0659l
    public Object l(T7.B b10, Object obj) {
        return null;
    }

    @Override // E8.n
    public void lock() {
        ((Lock) this.f527m).lock();
    }

    @Override // Q7.InterfaceC0659l
    public Object m(D8.v vVar, Object obj) {
        return null;
    }

    @Override // F1.InterfaceC0244c
    public void n(Bundle bundle) {
        ((ContentInfo.Builder) this.f527m).setExtras(bundle);
    }

    @Override // Q7.InterfaceC0659l
    public Object o(T7.u uVar, Object obj) {
        return null;
    }

    @Override // Q7.InterfaceC0665s
    public InterfaceC0665s p() {
        return this;
    }

    @Override // Q7.InterfaceC0659l
    public Object q(T7.w wVar, Object obj) {
        return null;
    }

    @Override // Q7.InterfaceC0665s
    public InterfaceC0665s r() {
        return this;
    }

    @Override // Q7.InterfaceC0665s
    public InterfaceC0665s s(C2061f c2061f) {
        A7.m.f(c2061f, "name");
        return this;
    }

    @Override // Q7.InterfaceC0665s
    public InterfaceC0665s t(int i7) {
        A7.k.p(i7, "kind");
        return this;
    }

    public String toString() {
        switch (this.l) {
            case 9:
                return "ContentInfoCompat{" + ((ContentInfo) this.f527m) + "}";
            default:
                return super.toString();
        }
    }

    @Override // Q7.InterfaceC0665s
    public InterfaceC0665s u(C0661n c0661n) {
        A7.m.f(c0661n, "visibility");
        return this;
    }

    @Override // E8.n
    public void unlock() {
        ((Lock) this.f527m).unlock();
    }

    @Override // n0.InterfaceC1866P
    public AbstractC1858H v(long j3, a1.k kVar, a1.b bVar) {
        return new C1855E((C1880i) this.f527m);
    }

    @Override // B8.InterfaceC0093e
    public C0092d w(C2057b c2057b) {
        C0092d c0092dW;
        A7.m.f(c2057b, "classId");
        C2058c c2058cG = c2057b.g();
        A7.m.e(c2058cG, "classId.packageFqName");
        for (Q7.D d : AbstractC0669w.i((Q7.H) this.f527m, c2058cG)) {
            if ((d instanceof C8.c) && (c0092dW = ((C8.c) d).f1019u.w(c2057b)) != null) {
                return c0092dW;
            }
        }
        return null;
    }

    @Override // I3.g
    public I3.b x(I3.a aVar) {
        return null;
    }

    @Override // Q7.InterfaceC0665s
    public InterfaceC0665s y() {
        return this;
    }

    @Override // Q7.InterfaceC0659l
    public Object z(AbstractC0789h abstractC0789h, Object obj) {
        return null;
    }

    public /* synthetic */ O(Object obj, int i7, Object obj2) {
        this.l = i7;
        this.f527m = obj;
    }

    @Override // F1.InterfaceC0244c
    public C0247f a() {
        return new C0247f(new O(((ContentInfo.Builder) this.f527m).build()));
    }

    public O(K7.E e10) {
        this.l = 27;
        A7.m.f(e10, "container");
        this.f527m = e10;
    }

    public O(z3.i iVar, N3.h hVar) {
        this.l = 26;
        this.f527m = hVar;
    }

    public O(View view) {
        this.l = 11;
        if (Build.VERSION.SDK_INT >= 30) {
            F1.r rVar = new F1.r(view, 10);
            rVar.f2345n = view;
            this.f527m = rVar;
            return;
        }
        this.f527m = new O(view, 10);
    }

    public O(Lock lock) {
        this.l = 5;
        A7.m.f(lock, "lock");
        this.f527m = lock;
    }

    public O(int i7, byte b10) {
        this.l = i7;
        switch (i7) {
            case 15:
                this.f527m = new G1.f(this);
                break;
        }
    }

    public O(View view, Window window) {
        this.l = 12;
        if (Build.VERSION.SDK_INT >= 30) {
            new F1.r(view, 10).f2345n = view;
        }
        int i7 = Build.VERSION.SDK_INT;
        if (i7 >= 35) {
            this.f527m = new Y(window);
        } else if (i7 >= 30) {
            this.f527m = new X(window);
        } else {
            this.f527m = new W(window);
        }
    }

    public O(int i7) {
        this.l = 29;
        this.f527m = new R0.b(i7);
    }

    public O(ContentInfo contentInfo) {
        this.l = 9;
        contentInfo.getClass();
        this.f527m = AbstractC0207i.i(contentInfo);
    }

    public O(ClipData clipData, int i7) {
        this.l = 8;
        this.f527m = AbstractC0207i.g(clipData, i7);
    }
}
