package F0;

import F1.C0243b;
import N0.C0411f;
import android.graphics.Rect;
import android.graphics.RectF;
import android.hardware.bydauto.statistic.BYDAutoStatisticDevice;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Parcelable;
import android.os.SystemClock;
import android.os.Trace;
import android.view.View;
import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityManager;
import android.view.accessibility.AccessibilityNodeInfo;
import androidx.compose.ui.platform.AndroidComposeView;
import com.stingray.drivemodedetector.R;
import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.List;
import n7.AbstractC1996k;
import n7.AbstractC1998m;
import n7.AbstractC1999n;
import u.AbstractC2468h;
import u.AbstractC2469i;
import u.C2459G;
import u.C2466f;
import u.C2474n;
import u.C2475o;
import u.C2476p;
import u.C2477q;
import x3.AbstractC2673a;
import z7.InterfaceC2786o;

/* JADX INFO: loaded from: classes.dex */
public final class M extends C0243b {

    /* JADX INFO: renamed from: N, reason: collision with root package name */
    public static final C2475o f2052N;

    /* JADX INFO: renamed from: A, reason: collision with root package name */
    public C2476p f2053A;
    public final C2477q B;

    /* JADX INFO: renamed from: C, reason: collision with root package name */
    public final C2474n f2054C;

    /* JADX INFO: renamed from: D, reason: collision with root package name */
    public final C2474n f2055D;

    /* JADX INFO: renamed from: E, reason: collision with root package name */
    public final String f2056E;

    /* JADX INFO: renamed from: F, reason: collision with root package name */
    public final String f2057F;

    /* JADX INFO: renamed from: G, reason: collision with root package name */
    public final l3.P f2058G;

    /* JADX INFO: renamed from: H, reason: collision with root package name */
    public final C2476p f2059H;

    /* JADX INFO: renamed from: I, reason: collision with root package name */
    public P0 f2060I;

    /* JADX INFO: renamed from: J, reason: collision with root package name */
    public boolean f2061J;

    /* JADX INFO: renamed from: K, reason: collision with root package name */
    public final A2.l f2062K;

    /* JADX INFO: renamed from: L, reason: collision with root package name */
    public final ArrayList f2063L;

    /* JADX INFO: renamed from: M, reason: collision with root package name */
    public final J f2064M;
    public final AndroidComposeView d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public int f2065e = Integer.MIN_VALUE;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public final J f2066f = new J(this, 0);

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public final AccessibilityManager f2067g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public long f2068h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public final A f2069i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public final B f2070j;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    public List f2071k;
    public final Handler l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    public final F f2072m;

    /* JADX INFO: renamed from: n, reason: collision with root package name */
    public int f2073n;
    public G1.e o;

    /* JADX INFO: renamed from: p, reason: collision with root package name */
    public boolean f2074p;

    /* JADX INFO: renamed from: q, reason: collision with root package name */
    public final C2476p f2075q;

    /* JADX INFO: renamed from: r, reason: collision with root package name */
    public final C2476p f2076r;

    /* JADX INFO: renamed from: s, reason: collision with root package name */
    public final C2459G f2077s;

    /* JADX INFO: renamed from: t, reason: collision with root package name */
    public final C2459G f2078t;

    /* JADX INFO: renamed from: u, reason: collision with root package name */
    public int f2079u;

    /* JADX INFO: renamed from: v, reason: collision with root package name */
    public Integer f2080v;

    /* JADX INFO: renamed from: w, reason: collision with root package name */
    public final C2466f f2081w;

    /* JADX INFO: renamed from: x, reason: collision with root package name */
    public final U8.l f2082x;

    /* JADX INFO: renamed from: y, reason: collision with root package name */
    public boolean f2083y;

    /* JADX INFO: renamed from: z, reason: collision with root package name */
    public H f2084z;

    static {
        int[] iArr = {R.id.accessibility_custom_action_0, R.id.accessibility_custom_action_1, R.id.accessibility_custom_action_2, R.id.accessibility_custom_action_3, R.id.accessibility_custom_action_4, R.id.accessibility_custom_action_5, R.id.accessibility_custom_action_6, R.id.accessibility_custom_action_7, R.id.accessibility_custom_action_8, R.id.accessibility_custom_action_9, R.id.accessibility_custom_action_10, R.id.accessibility_custom_action_11, R.id.accessibility_custom_action_12, R.id.accessibility_custom_action_13, R.id.accessibility_custom_action_14, R.id.accessibility_custom_action_15, R.id.accessibility_custom_action_16, R.id.accessibility_custom_action_17, R.id.accessibility_custom_action_18, R.id.accessibility_custom_action_19, R.id.accessibility_custom_action_20, R.id.accessibility_custom_action_21, R.id.accessibility_custom_action_22, R.id.accessibility_custom_action_23, R.id.accessibility_custom_action_24, R.id.accessibility_custom_action_25, R.id.accessibility_custom_action_26, R.id.accessibility_custom_action_27, R.id.accessibility_custom_action_28, R.id.accessibility_custom_action_29, R.id.accessibility_custom_action_30, R.id.accessibility_custom_action_31};
        int i7 = AbstractC2468h.f22601a;
        C2475o c2475o = new C2475o(32);
        int i10 = c2475o.f22614b;
        if (i10 < 0) {
            StringBuilder sbJ = M5.b.j("Index ", i10, " must be in 0..");
            sbJ.append(c2475o.f22614b);
            throw new IndexOutOfBoundsException(sbJ.toString());
        }
        int i11 = i10 + 32;
        c2475o.b(i11);
        int[] iArr2 = c2475o.f22613a;
        int i12 = c2475o.f22614b;
        if (i10 != i12) {
            AbstractC1996k.C(i11, i10, i12, iArr2, iArr2);
        }
        AbstractC1996k.F(i10, 0, 12, iArr, iArr2);
        c2475o.f22614b += 32;
        f2052N = c2475o;
    }

    /* JADX WARN: Type inference failed for: r2v4, types: [F0.A] */
    /* JADX WARN: Type inference failed for: r2v5, types: [F0.B] */
    public M(AndroidComposeView androidComposeView) {
        this.d = androidComposeView;
        Object systemService = androidComposeView.getContext().getSystemService("accessibility");
        A7.m.d(systemService, "null cannot be cast to non-null type android.view.accessibility.AccessibilityManager");
        AccessibilityManager accessibilityManager = (AccessibilityManager) systemService;
        this.f2067g = accessibilityManager;
        this.f2068h = 100L;
        this.f2069i = new AccessibilityManager.AccessibilityStateChangeListener() { // from class: F0.A
            @Override // android.view.accessibility.AccessibilityManager.AccessibilityStateChangeListener
            public final void onAccessibilityStateChanged(boolean z6) {
                M m10 = this.f1989a;
                m10.f2071k = z6 ? m10.f2067g.getEnabledAccessibilityServiceList(-1) : n7.u.l;
            }
        };
        this.f2070j = new AccessibilityManager.TouchExplorationStateChangeListener() { // from class: F0.B
            @Override // android.view.accessibility.AccessibilityManager.TouchExplorationStateChangeListener
            public final void onTouchExplorationStateChanged(boolean z6) {
                M m10 = this.f2007a;
                m10.f2071k = m10.f2067g.getEnabledAccessibilityServiceList(-1);
            }
        };
        this.f2071k = accessibilityManager.getEnabledAccessibilityServiceList(-1);
        this.l = new Handler(Looper.getMainLooper());
        this.f2072m = new F(this, 0);
        this.f2073n = Integer.MIN_VALUE;
        this.f2075q = new C2476p();
        this.f2076r = new C2476p();
        this.f2077s = new C2459G(0);
        this.f2078t = new C2459G(0);
        this.f2079u = -1;
        this.f2081w = new C2466f(null);
        this.f2082x = AbstractC2673a.a(1, 6, null);
        this.f2083y = true;
        C2476p c2476p = AbstractC2469i.f22602a;
        A7.m.d(c2476p, "null cannot be cast to non-null type androidx.collection.IntObjectMap<V of androidx.collection.IntObjectMapKt.intObjectMapOf>");
        this.f2053A = c2476p;
        this.B = new C2477q();
        this.f2054C = new C2474n();
        this.f2055D = new C2474n();
        this.f2056E = "android.view.accessibility.extra.EXTRA_DATA_TEST_TRAVERSALBEFORE_VAL";
        this.f2057F = "android.view.accessibility.extra.EXTRA_DATA_TEST_TRAVERSALAFTER_VAL";
        this.f2058G = new l3.P(16);
        this.f2059H = new C2476p();
        L0.o oVarA = androidComposeView.getSemanticsOwner().a();
        A7.m.d(c2476p, "null cannot be cast to non-null type androidx.collection.IntObjectMap<V of androidx.collection.IntObjectMapKt.intObjectMapOf>");
        this.f2060I = new P0(oVarA, c2476p);
        androidComposeView.addOnAttachStateChangeListener(new C(this, 0));
        this.f2062K = new A2.l(this, 2);
        this.f2063L = new ArrayList();
        this.f2064M = new J(this, 1);
    }

    /* JADX WARN: Type inference failed for: r2v0, types: [A7.o, z7.a] */
    /* JADX WARN: Type inference failed for: r3v2, types: [A7.o, z7.a] */
    public static final boolean B(L0.h hVar, float f10) {
        ?? r22 = hVar.f3884a;
        return (f10 < 0.0f && ((Number) r22.a()).floatValue() > 0.0f) || (f10 > 0.0f && ((Number) r22.a()).floatValue() < ((Number) hVar.f3885b.a()).floatValue());
    }

    /* JADX WARN: Type inference failed for: r0v0, types: [A7.o, z7.a] */
    /* JADX WARN: Type inference failed for: r3v1, types: [A7.o, z7.a] */
    public static final boolean C(L0.h hVar) {
        ?? r02 = hVar.f3884a;
        float fFloatValue = ((Number) r02.a()).floatValue();
        boolean z6 = hVar.c;
        return (fFloatValue > 0.0f && !z6) || (((Number) r02.a()).floatValue() < ((Number) hVar.f3885b.a()).floatValue() && z6);
    }

    /* JADX WARN: Type inference failed for: r0v0, types: [A7.o, z7.a] */
    /* JADX WARN: Type inference failed for: r2v0, types: [A7.o, z7.a] */
    public static final boolean D(L0.h hVar) {
        ?? r02 = hVar.f3884a;
        float fFloatValue = ((Number) r02.a()).floatValue();
        float fFloatValue2 = ((Number) hVar.f3885b.a()).floatValue();
        boolean z6 = hVar.c;
        return (fFloatValue < fFloatValue2 && !z6) || (((Number) r02.a()).floatValue() > 0.0f && z6);
    }

    public static /* synthetic */ void I(M m10, int i7, int i10, Integer num, int i11) {
        if ((i11 & 4) != 0) {
            num = null;
        }
        m10.H(i7, i10, num, null);
    }

    public static CharSequence Q(CharSequence charSequence) {
        if (charSequence.length() != 0) {
            int i7 = 100000;
            if (charSequence.length() > 100000) {
                if (Character.isHighSurrogate(charSequence.charAt(BYDAutoStatisticDevice.MILEAGE_MAX)) && Character.isLowSurrogate(charSequence.charAt(100000))) {
                    i7 = 99999;
                }
                CharSequence charSequenceSubSequence = charSequence.subSequence(0, i7);
                A7.m.d(charSequenceSubSequence, "null cannot be cast to non-null type T of androidx.compose.ui.platform.AndroidComposeViewAccessibilityDelegateCompat.trimToSize");
                return charSequenceSubSequence;
            }
        }
        return charSequence;
    }

    public static boolean u(L0.o oVar) {
        Object obj = oVar.d.l.get(L0.r.B);
        if (obj == null) {
            obj = null;
        }
        M0.a aVar = (M0.a) obj;
        L0.u uVar = L0.r.f3955s;
        LinkedHashMap linkedHashMap = oVar.d.l;
        Object obj2 = linkedHashMap.get(uVar);
        if (obj2 == null) {
            obj2 = null;
        }
        L0.g gVar = (L0.g) obj2;
        boolean z6 = aVar != null;
        Object obj3 = linkedHashMap.get(L0.r.f3935A);
        if (((Boolean) (obj3 != null ? obj3 : null)) != null) {
            return gVar != null ? L0.g.a(gVar.f3883a, 4) : false ? z6 : true;
        }
        return z6;
    }

    public static C0411f w(L0.o oVar) {
        Object obj = oVar.d.l.get(L0.r.f3960x);
        if (obj == null) {
            obj = null;
        }
        C0411f c0411f = (C0411f) obj;
        Object obj2 = oVar.d.l.get(L0.r.f3957u);
        if (obj2 == null) {
            obj2 = null;
        }
        List list = (List) obj2;
        return c0411f == null ? list != null ? (C0411f) AbstractC1998m.a0(list) : null : c0411f;
    }

    public static String x(L0.o oVar) {
        C0411f c0411f;
        if (oVar == null) {
            return null;
        }
        L0.u uVar = L0.r.f3941a;
        L0.j jVar = oVar.d;
        LinkedHashMap linkedHashMap = jVar.l;
        if (linkedHashMap.containsKey(uVar)) {
            return w0.c.L((List) jVar.d(uVar), ",", null, 62);
        }
        L0.u uVar2 = L0.r.f3960x;
        if (linkedHashMap.containsKey(uVar2)) {
            Object obj = linkedHashMap.get(uVar2);
            if (obj == null) {
                obj = null;
            }
            C0411f c0411f2 = (C0411f) obj;
            if (c0411f2 != null) {
                return c0411f2.l;
            }
            return null;
        }
        Object obj2 = linkedHashMap.get(L0.r.f3957u);
        if (obj2 == null) {
            obj2 = null;
        }
        List list = (List) obj2;
        if (list == null || (c0411f = (C0411f) AbstractC1998m.a0(list)) == null) {
            return null;
        }
        return c0411f.l;
    }

    public final void A(E0.F f10) {
        if (this.f2081w.add(f10)) {
            this.f2082x.p(m7.q.f19267a);
        }
    }

    public final int E(int i7) {
        if (i7 == this.d.getSemanticsOwner().a().f3920g) {
            return -1;
        }
        return i7;
    }

    /* JADX WARN: Removed duplicated region for block: B:28:0x0088  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final void F(L0.o r21, F0.P0 r22) {
        /*
            r20 = this;
            r0 = r20
            r1 = r21
            r2 = r22
            r3 = 1
            int[] r4 = u.AbstractC2470j.f22603a
            u.q r4 = new u.q
            r4.<init>()
            r5 = 4
            java.util.List r6 = L0.o.h(r1, r3, r5)
            int r7 = r6.size()
            r9 = 0
        L18:
            E0.F r10 = r1.c
            if (r9 >= r7) goto L41
            java.lang.Object r11 = r6.get(r9)
            L0.o r11 = (L0.o) r11
            u.p r12 = r20.t()
            int r13 = r11.f3920g
            boolean r12 = r12.b(r13)
            if (r12 == 0) goto L3f
            u.q r12 = r2.f2105b
            int r11 = r11.f3920g
            boolean r12 = r12.c(r11)
            if (r12 != 0) goto L3c
            r0.A(r10)
            return
        L3c:
            r4.a(r11)
        L3f:
            int r9 = r9 + r3
            goto L18
        L41:
            u.q r2 = r2.f2105b
            int[] r6 = r2.f22620b
            long[] r2 = r2.f22619a
            int r7 = r2.length
            int r7 = r7 + (-2)
            if (r7 < 0) goto L8c
            r9 = 0
        L4d:
            r11 = r2[r9]
            long r13 = ~r11
            r15 = 7
            long r13 = r13 << r15
            long r13 = r13 & r11
            r15 = -9187201950435737472(0x8080808080808080, double:-2.937446524422997E-306)
            long r13 = r13 & r15
            int r13 = (r13 > r15 ? 1 : (r13 == r15 ? 0 : -1))
            if (r13 == 0) goto L88
            int r13 = r9 - r7
            int r13 = ~r13
            int r13 = r13 >>> 31
            r14 = 8
            int r13 = 8 - r13
            r15 = 0
        L67:
            if (r15 >= r13) goto L86
            r16 = 255(0xff, double:1.26E-321)
            long r16 = r11 & r16
            r18 = 128(0x80, double:6.3E-322)
            int r16 = (r16 > r18 ? 1 : (r16 == r18 ? 0 : -1))
            if (r16 >= 0) goto L83
            int r16 = r9 << 3
            int r16 = r16 + r15
            r8 = r6[r16]
            boolean r8 = r4.c(r8)
            if (r8 != 0) goto L83
            r0.A(r10)
            return
        L83:
            long r11 = r11 >> r14
            int r15 = r15 + r3
            goto L67
        L86:
            if (r13 != r14) goto L8c
        L88:
            if (r9 == r7) goto L8c
            int r9 = r9 + r3
            goto L4d
        L8c:
            java.util.List r1 = L0.o.h(r1, r3, r5)
            int r2 = r1.size()
            r8 = 0
        L95:
            if (r8 >= r2) goto Lbb
            java.lang.Object r4 = r1.get(r8)
            L0.o r4 = (L0.o) r4
            u.p r5 = r20.t()
            int r6 = r4.f3920g
            boolean r5 = r5.b(r6)
            if (r5 == 0) goto Lb9
            u.p r5 = r0.f2059H
            int r6 = r4.f3920g
            java.lang.Object r5 = r5.f(r6)
            A7.m.c(r5)
            F0.P0 r5 = (F0.P0) r5
            r0.F(r4, r5)
        Lb9:
            int r8 = r8 + r3
            goto L95
        Lbb:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: F0.M.F(L0.o, F0.P0):void");
    }

    public final boolean G(AccessibilityEvent accessibilityEvent) {
        if (!y()) {
            return false;
        }
        if (accessibilityEvent.getEventType() == 2048 || accessibilityEvent.getEventType() == 32768) {
            this.f2074p = true;
        }
        try {
            return ((Boolean) this.f2066f.b(accessibilityEvent)).booleanValue();
        } finally {
            this.f2074p = false;
        }
    }

    public final boolean H(int i7, int i10, Integer num, List list) {
        if (i7 == Integer.MIN_VALUE || !y()) {
            return false;
        }
        AccessibilityEvent accessibilityEventO = o(i7, i10);
        if (num != null) {
            accessibilityEventO.setContentChangeTypes(num.intValue());
        }
        if (list != null) {
            accessibilityEventO.setContentDescription(w0.c.L(list, ",", null, 62));
        }
        Trace.beginSection("sendEvent");
        try {
            return G(accessibilityEventO);
        } finally {
            Trace.endSection();
        }
    }

    public final void J(String str, int i7, int i10) {
        AccessibilityEvent accessibilityEventO = o(E(i7), 32);
        accessibilityEventO.setContentChangeTypes(i10);
        if (str != null) {
            accessibilityEventO.getText().add(str);
        }
        G(accessibilityEventO);
    }

    public final void K(int i7) {
        H h10 = this.f2084z;
        if (h10 != null) {
            L0.o oVar = h10.f2024a;
            if (i7 != oVar.f3920g) {
                return;
            }
            if (SystemClock.uptimeMillis() - h10.f2027f <= 1000) {
                AccessibilityEvent accessibilityEventO = o(E(oVar.f3920g), 131072);
                accessibilityEventO.setFromIndex(h10.d);
                accessibilityEventO.setToIndex(h10.f2026e);
                accessibilityEventO.setAction(h10.f2025b);
                accessibilityEventO.setMovementGranularity(h10.c);
                accessibilityEventO.getText().add(x(oVar));
                G(accessibilityEventO);
            }
        }
        this.f2084z = null;
    }

    /* JADX WARN: Removed duplicated region for block: B:220:0x0535  */
    /* JADX WARN: Removed duplicated region for block: B:236:0x05a8  */
    /* JADX WARN: Removed duplicated region for block: B:266:0x0622  */
    /* JADX WARN: Removed duplicated region for block: B:42:0x00cb  */
    /* JADX WARN: Removed duplicated region for block: B:49:0x00f5  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final void L(u.C2476p r40) {
        /*
            Method dump skipped, instruction units count: 1643
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: F0.M.L(u.p):void");
    }

    public final void M(E0.F f10, C2477q c2477q) {
        L0.j jVarQ;
        if (f10.V() && !this.d.getAndroidViewsHandler$ui_release().getLayoutNodeToHolder().containsKey(f10)) {
            E0.F f11 = null;
            if (!f10.f1469H.f(8)) {
                f10 = f10.G();
                while (true) {
                    if (f10 == null) {
                        f10 = null;
                        break;
                    } else if (f10.f1469H.f(8)) {
                        break;
                    } else {
                        f10 = f10.G();
                    }
                }
            }
            if (f10 == null || (jVarQ = f10.q()) == null) {
                return;
            }
            if (!jVarQ.f3909m) {
                E0.F fG = f10.G();
                while (true) {
                    if (fG != null) {
                        L0.j jVarQ2 = fG.q();
                        if (jVarQ2 != null && jVarQ2.f3909m) {
                            f11 = fG;
                            break;
                        }
                        fG = fG.G();
                    } else {
                        break;
                    }
                }
                if (f11 != null) {
                    f10 = f11;
                }
            }
            int i7 = f10.f1481m;
            if (c2477q.a(i7)) {
                I(this, E(i7), 2048, 1, 8);
            }
        }
    }

    /* JADX WARN: Type inference failed for: r0v13, types: [A7.o, z7.a] */
    /* JADX WARN: Type inference failed for: r0v18, types: [A7.o, z7.a] */
    /* JADX WARN: Type inference failed for: r0v8, types: [A7.o, z7.a] */
    /* JADX WARN: Type inference failed for: r2v1, types: [A7.o, z7.a] */
    public final void N(E0.F f10) {
        if (f10.V() && !this.d.getAndroidViewsHandler$ui_release().getLayoutNodeToHolder().containsKey(f10)) {
            int i7 = f10.f1481m;
            L0.h hVar = (L0.h) this.f2075q.f(i7);
            L0.h hVar2 = (L0.h) this.f2076r.f(i7);
            if (hVar == null && hVar2 == null) {
                return;
            }
            AccessibilityEvent accessibilityEventO = o(i7, 4096);
            if (hVar != null) {
                accessibilityEventO.setScrollX((int) ((Number) hVar.f3884a.a()).floatValue());
                accessibilityEventO.setMaxScrollX((int) ((Number) hVar.f3885b.a()).floatValue());
            }
            if (hVar2 != null) {
                accessibilityEventO.setScrollY((int) ((Number) hVar2.f3884a.a()).floatValue());
                accessibilityEventO.setMaxScrollY((int) ((Number) hVar2.f3885b.a()).floatValue());
            }
            G(accessibilityEventO);
        }
    }

    public final boolean O(L0.o oVar, int i7, int i10, boolean z6) {
        String strX;
        L0.j jVar = oVar.d;
        L0.u uVar = L0.i.f3892h;
        if (jVar.l.containsKey(uVar) && T.i(oVar)) {
            InterfaceC2786o interfaceC2786o = (InterfaceC2786o) ((L0.a) oVar.d.d(uVar)).f3876b;
            if (interfaceC2786o != null) {
                return ((Boolean) interfaceC2786o.g(Integer.valueOf(i7), Integer.valueOf(i10), Boolean.valueOf(z6))).booleanValue();
            }
            return false;
        }
        if ((i7 == i10 && i10 == this.f2079u) || (strX = x(oVar)) == null) {
            return false;
        }
        if (i7 < 0 || i7 != i10 || i10 > strX.length()) {
            i7 = -1;
        }
        this.f2079u = i7;
        boolean z10 = strX.length() > 0;
        int i11 = oVar.f3920g;
        G(p(E(i11), z10 ? Integer.valueOf(this.f2079u) : null, z10 ? Integer.valueOf(this.f2079u) : null, z10 ? Integer.valueOf(strX.length()) : null, strX));
        K(i11);
        return true;
    }

    /* JADX WARN: Removed duplicated region for block: B:28:0x00b8 A[EDGE_INSN: B:53:0x00b8->B:28:0x00b8 BREAK  A[LOOP:2: B:17:0x0051->B:27:0x00b6]] */
    /* JADX WARN: Removed duplicated region for block: B:30:0x00ce A[LOOP:1: B:8:0x002f->B:30:0x00ce, LOOP_END] */
    /* JADX WARN: Removed duplicated region for block: B:51:0x00d1 A[EDGE_INSN: B:51:0x00d1->B:31:0x00d1 BREAK  A[LOOP:1: B:8:0x002f->B:30:0x00ce], SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final java.util.ArrayList P(boolean r18, java.util.ArrayList r19) {
        /*
            Method dump skipped, instruction units count: 326
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: F0.M.P(boolean, java.util.ArrayList):java.util.ArrayList");
    }

    /* JADX WARN: Code restructure failed: missing block: B:47:0x0140, code lost:
    
        r28 = r13;
     */
    /* JADX WARN: Code restructure failed: missing block: B:48:0x014e, code lost:
    
        if (((r0 & ((~r0) << 6)) & (-9187201950435737472L)) == 0) goto L53;
     */
    /* JADX WARN: Code restructure failed: missing block: B:49:0x0150, code lost:
    
        r24 = -1;
     */
    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Removed duplicated region for block: B:18:0x0062  */
    /* JADX WARN: Removed duplicated region for block: B:28:0x0091  */
    /* JADX WARN: Type inference failed for: r12v19, types: [java.lang.String] */
    /* JADX WARN: Type inference failed for: r13v1 */
    /* JADX WARN: Type inference failed for: r13v2, types: [F0.M] */
    /* JADX WARN: Type inference failed for: r13v25 */
    /* JADX WARN: Type inference failed for: r13v3 */
    /* JADX WARN: Type inference failed for: r13v4 */
    /* JADX WARN: Type inference failed for: r13v5 */
    /* JADX WARN: Type inference failed for: r29v0, types: [F0.M] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final void R() {
        /*
            Method dump skipped, instruction units count: 594
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: F0.M.R():void");
    }

    @Override // F1.C0243b
    public final B2.O b(View view) {
        return this.f2072m;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public final void j(int i7, G1.e eVar, String str, Bundle bundle) {
        L0.o oVar;
        RectF rectF;
        Q0 q02 = (Q0) t().f(i7);
        if (q02 == null || (oVar = q02.f2107a) == null) {
            return;
        }
        String strX = x(oVar);
        boolean zA = A7.m.a(str, this.f2056E);
        AccessibilityNodeInfo accessibilityNodeInfo = eVar.f2646a;
        if (zA) {
            int iE = this.f2054C.e(i7);
            if (iE != -1) {
                accessibilityNodeInfo.getExtras().putInt(str, iE);
                return;
            }
            return;
        }
        if (A7.m.a(str, this.f2057F)) {
            int iE2 = this.f2055D.e(i7);
            if (iE2 != -1) {
                accessibilityNodeInfo.getExtras().putInt(str, iE2);
                return;
            }
            return;
        }
        L0.u uVar = L0.i.f3887a;
        L0.j jVar = oVar.d;
        LinkedHashMap linkedHashMap = jVar.l;
        E0.d0 d0Var = null;
        if (!linkedHashMap.containsKey(uVar) || bundle == null || !A7.m.a(str, "android.view.accessibility.extra.DATA_TEXT_CHARACTER_LOCATION_KEY")) {
            L0.u uVar2 = L0.r.f3956t;
            if (!linkedHashMap.containsKey(uVar2) || bundle == null || !A7.m.a(str, "androidx.compose.ui.semantics.testTag")) {
                if (A7.m.a(str, "androidx.compose.ui.semantics.id")) {
                    accessibilityNodeInfo.getExtras().putInt(str, oVar.f3920g);
                    return;
                }
                return;
            } else {
                Object obj = linkedHashMap.get(uVar2);
                String str2 = (String) (obj == null ? null : obj);
                if (str2 != null) {
                    accessibilityNodeInfo.getExtras().putCharSequence(str, str2);
                    return;
                }
                return;
            }
        }
        int i10 = bundle.getInt("android.view.accessibility.extra.DATA_TEXT_CHARACTER_LOCATION_ARG_START_INDEX", -1);
        int i11 = bundle.getInt("android.view.accessibility.extra.DATA_TEXT_CHARACTER_LOCATION_ARG_LENGTH", -1);
        if (i11 > 0 && i10 >= 0) {
            if (i10 < (strX != null ? strX.length() : Integer.MAX_VALUE)) {
                N0.H hR = T.r(jVar);
                if (hR == null) {
                    return;
                }
                ArrayList arrayList = new ArrayList();
                int i12 = 0;
                while (i12 < i11) {
                    int i13 = i10 + i12;
                    if (i13 >= hR.f4348a.f4340a.l.length()) {
                        arrayList.add(d0Var);
                    } else {
                        m0.d dVarB = hR.b(i13);
                        E0.d0 d0VarC = oVar.c();
                        long jN = 0;
                        if (d0VarC != null) {
                            if (!d0VarC.Q0().f14695x) {
                                d0VarC = d0Var;
                            }
                            if (d0VarC != null) {
                                jN = d0VarC.N(0L);
                            }
                        }
                        m0.d dVarH = dVarB.h(jN);
                        m0.d dVarE = oVar.e();
                        m0.d dVarD = dVarH.f(dVarE) ? dVarH.d(dVarE) : d0Var;
                        if (dVarD != 0) {
                            long jA = h6.e.a(dVarD.f18882a, dVarD.f18883b);
                            AndroidComposeView androidComposeView = this.d;
                            long jO = androidComposeView.o(jA);
                            long jO2 = androidComposeView.o(h6.e.a(dVarD.c, dVarD.d));
                            rectF = new RectF(m0.c.d(jO), m0.c.e(jO), m0.c.d(jO2), m0.c.e(jO2));
                        } else {
                            rectF = null;
                        }
                        arrayList.add(rectF);
                    }
                    i12++;
                    d0Var = null;
                }
                accessibilityNodeInfo.getExtras().putParcelableArray(str, (Parcelable[]) arrayList.toArray(new RectF[0]));
                return;
            }
        }
        h6.n.j("AccessibilityDelegate", "Invalid arguments for accessibility character locations");
    }

    public final Rect k(Q0 q02) {
        Rect rect = q02.f2108b;
        long jA = h6.e.a(rect.left, rect.top);
        AndroidComposeView androidComposeView = this.d;
        long jO = androidComposeView.o(jA);
        long jO2 = androidComposeView.o(h6.e.a(rect.right, rect.bottom));
        return new Rect((int) Math.floor(m0.c.d(jO)), (int) Math.floor(m0.c.e(jO)), (int) Math.ceil(m0.c.d(jO2)), (int) Math.ceil(m0.c.e(jO2)));
    }

    /* JADX WARN: Code restructure failed: missing block: B:52:0x005e, code lost:
    
        r11 = r2;
     */
    /* JADX WARN: Removed duplicated region for block: B:30:0x0078 A[Catch: all -> 0x0097, TRY_LEAVE, TryCatch #1 {all -> 0x0097, blocks: (B:24:0x005e, B:28:0x0070, B:30:0x0078, B:33:0x0083, B:35:0x0088, B:38:0x009b, B:40:0x00a2, B:41:0x00ab, B:23:0x004f), top: B:50:0x004f }] */
    /* JADX WARN: Removed duplicated region for block: B:44:0x00c9  */
    /* JADX WARN: Removed duplicated region for block: B:45:0x00cb  */
    /* JADX WARN: Removed duplicated region for block: B:7:0x0013  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public final java.lang.Object l(s7.AbstractC2410c r11) throws java.lang.Throwable {
        /*
            Method dump skipped, instruction units count: 217
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: F0.M.l(s7.c):java.lang.Object");
    }

    /* JADX WARN: Type inference failed for: r2v23, types: [A7.o, z7.a] */
    /* JADX WARN: Type inference failed for: r3v10, types: [A7.o, z7.a] */
    public final boolean m(int i7, long j3, boolean z6) {
        L0.u uVar;
        long[] jArr;
        Object[] objArr;
        long[] jArr2;
        Object[] objArr2;
        int i10;
        int i11 = 0;
        if (!A7.m.a(Looper.getMainLooper().getThread(), Thread.currentThread())) {
            return false;
        }
        C2476p c2476pT = t();
        if (!m0.c.b(j3, 9205357640488583168L) && m0.c.f(j3)) {
            if (z6) {
                uVar = L0.r.f3952p;
            } else {
                if (z6) {
                    throw new A9.k();
                }
                uVar = L0.r.o;
            }
            Object[] objArr3 = c2476pT.c;
            long[] jArr3 = c2476pT.f22615a;
            int length = jArr3.length - 2;
            if (length >= 0) {
                int i12 = 0;
                boolean z10 = false;
                while (true) {
                    long j10 = jArr3[i12];
                    if ((((~j10) << 7) & j10 & (-9187201950435737472L)) != -9187201950435737472L) {
                        int i13 = 8;
                        int i14 = 8 - ((~(i12 - length)) >>> 31);
                        int i15 = i11;
                        while (i15 < i14) {
                            if ((j10 & 255) < 128) {
                                Q0 q02 = (Q0) objArr3[(i12 << 3) + i15];
                                Rect rect = q02.f2108b;
                                jArr2 = jArr3;
                                objArr2 = objArr3;
                                if (m0.c.d(j3) >= ((float) rect.left) && m0.c.d(j3) < ((float) rect.right) && m0.c.e(j3) >= ((float) rect.top) && m0.c.e(j3) < ((float) rect.bottom)) {
                                    Object obj = q02.f2107a.d.l.get(uVar);
                                    if (obj == null) {
                                        obj = null;
                                    }
                                    L0.h hVar = (L0.h) obj;
                                    if (hVar != null) {
                                        boolean z11 = hVar.c;
                                        int i16 = z11 ? -i7 : i7;
                                        if (i7 == 0 && z11) {
                                            i16 = -1;
                                        }
                                        ?? r32 = hVar.f3884a;
                                        if (i16 >= 0 ? ((Number) r32.a()).floatValue() < ((Number) hVar.f3885b.a()).floatValue() : ((Number) r32.a()).floatValue() > 0.0f) {
                                            z10 = true;
                                        }
                                    }
                                }
                                i10 = 8;
                            } else {
                                jArr2 = jArr3;
                                objArr2 = objArr3;
                                i10 = i13;
                            }
                            j10 >>= i10;
                            i15++;
                            i13 = i10;
                            jArr3 = jArr2;
                            objArr3 = objArr2;
                        }
                        jArr = jArr3;
                        objArr = objArr3;
                        if (i14 != i13) {
                            break;
                        }
                    } else {
                        jArr = jArr3;
                        objArr = objArr3;
                    }
                    if (i12 == length) {
                        break;
                    }
                    i12++;
                    jArr3 = jArr;
                    objArr3 = objArr;
                    i11 = 0;
                }
                return z10;
            }
        }
        return false;
    }

    public final void n() {
        Trace.beginSection("sendAccessibilitySemanticsStructureChangeEvents");
        try {
            if (y()) {
                F(this.d.getSemanticsOwner().a(), this.f2060I);
            }
            Trace.endSection();
            Trace.beginSection("sendSemanticsPropertyChangeEvents");
            try {
                L(t());
                Trace.endSection();
                Trace.beginSection("updateSemanticsNodesCopyAndPanes");
                try {
                    R();
                } finally {
                }
            } finally {
            }
        } finally {
        }
    }

    public final AccessibilityEvent o(int i7, int i10) {
        Q0 q02;
        AccessibilityEvent accessibilityEventObtain = AccessibilityEvent.obtain(i10);
        accessibilityEventObtain.setEnabled(true);
        accessibilityEventObtain.setClassName("android.view.View");
        AndroidComposeView androidComposeView = this.d;
        accessibilityEventObtain.setPackageName(androidComposeView.getContext().getPackageName());
        accessibilityEventObtain.setSource(androidComposeView, i7);
        if (y() && (q02 = (Q0) t().f(i7)) != null) {
            accessibilityEventObtain.setPassword(q02.f2107a.d.l.containsKey(L0.r.f3936C));
        }
        return accessibilityEventObtain;
    }

    public final AccessibilityEvent p(int i7, Integer num, Integer num2, Integer num3, CharSequence charSequence) {
        AccessibilityEvent accessibilityEventO = o(i7, 8192);
        if (num != null) {
            accessibilityEventO.setFromIndex(num.intValue());
        }
        if (num2 != null) {
            accessibilityEventO.setToIndex(num2.intValue());
        }
        if (num3 != null) {
            accessibilityEventO.setItemCount(num3.intValue());
        }
        if (charSequence != null) {
            accessibilityEventO.getText().add(charSequence);
        }
        return accessibilityEventO;
    }

    public final void q(L0.o oVar, ArrayList arrayList, C2476p c2476p) {
        boolean zL = T.l(oVar);
        Object obj = oVar.d.l.get(L0.r.l);
        if (obj == null) {
            obj = Boolean.FALSE;
        }
        boolean zBooleanValue = ((Boolean) obj).booleanValue();
        int i7 = oVar.f3920g;
        if ((zBooleanValue || z(oVar)) && t().c(i7)) {
            arrayList.add(oVar);
        }
        if (zBooleanValue) {
            c2476p.i(i7, P(zL, AbstractC1998m.A0(L0.o.h(oVar, false, 7))));
            return;
        }
        List listH = L0.o.h(oVar, false, 7);
        int size = listH.size();
        for (int i10 = 0; i10 < size; i10++) {
            q((L0.o) listH.get(i10), arrayList, c2476p);
        }
    }

    public final int r(L0.o oVar) {
        L0.j jVar = oVar.d;
        if (!jVar.l.containsKey(L0.r.f3941a)) {
            L0.u uVar = L0.r.f3961y;
            L0.j jVar2 = oVar.d;
            if (jVar2.l.containsKey(uVar)) {
                return (int) (((N0.K) jVar2.d(uVar)).f4357a & 4294967295L);
            }
        }
        return this.f2079u;
    }

    public final int s(L0.o oVar) {
        L0.j jVar = oVar.d;
        if (!jVar.l.containsKey(L0.r.f3941a)) {
            L0.u uVar = L0.r.f3961y;
            L0.j jVar2 = oVar.d;
            if (jVar2.l.containsKey(uVar)) {
                return (int) (((N0.K) jVar2.d(uVar)).f4357a >> 32);
            }
        }
        return this.f2079u;
    }

    public final C2476p t() {
        if (this.f2083y) {
            this.f2083y = false;
            this.f2053A = T.p(this.d.getSemanticsOwner());
            if (y()) {
                C2474n c2474n = this.f2054C;
                c2474n.a();
                C2474n c2474n2 = this.f2055D;
                c2474n2.a();
                Q0 q02 = (Q0) t().f(-1);
                L0.o oVar = q02 != null ? q02.f2107a : null;
                A7.m.c(oVar);
                ArrayList arrayListP = P(T.l(oVar), AbstractC1999n.F(oVar));
                int iC = AbstractC1999n.C(arrayListP);
                int i7 = 1;
                if (1 <= iC) {
                    while (true) {
                        int i10 = ((L0.o) arrayListP.get(i7 - 1)).f3920g;
                        int i11 = ((L0.o) arrayListP.get(i7)).f3920g;
                        c2474n.g(i10, i11);
                        c2474n2.g(i11, i10);
                        if (i7 == iC) {
                            break;
                        }
                        i7++;
                    }
                }
            }
        }
        return this.f2053A;
    }

    public final String v(L0.o oVar) {
        Object string = oVar.d.l.get(L0.r.f3942b);
        String string2 = null;
        if (string == null) {
            string = null;
        }
        L0.u uVar = L0.r.B;
        L0.j jVar = oVar.d;
        LinkedHashMap linkedHashMap = jVar.l;
        Object obj = linkedHashMap.get(uVar);
        if (obj == null) {
            obj = null;
        }
        M0.a aVar = (M0.a) obj;
        Object obj2 = linkedHashMap.get(L0.r.f3955s);
        if (obj2 == null) {
            obj2 = null;
        }
        L0.g gVar = (L0.g) obj2;
        AndroidComposeView androidComposeView = this.d;
        if (aVar != null) {
            int iOrdinal = aVar.ordinal();
            if (iOrdinal == 0) {
                if ((gVar == null ? false : L0.g.a(gVar.f3883a, 2)) && string == null) {
                    string = androidComposeView.getContext().getResources().getString(R.string.state_on);
                }
            } else if (iOrdinal == 1) {
                if ((gVar == null ? false : L0.g.a(gVar.f3883a, 2)) && string == null) {
                    string = androidComposeView.getContext().getResources().getString(R.string.state_off);
                }
            } else if (iOrdinal == 2 && string == null) {
                string = androidComposeView.getContext().getResources().getString(R.string.indeterminate);
            }
        }
        Object obj3 = linkedHashMap.get(L0.r.f3935A);
        if (obj3 == null) {
            obj3 = null;
        }
        Boolean bool = (Boolean) obj3;
        if (bool != null) {
            boolean zBooleanValue = bool.booleanValue();
            if (!(gVar == null ? false : L0.g.a(gVar.f3883a, 4)) && string == null) {
                string = zBooleanValue ? androidComposeView.getContext().getResources().getString(R.string.selected) : androidComposeView.getContext().getResources().getString(R.string.not_selected);
            }
        }
        Object obj4 = linkedHashMap.get(L0.r.c);
        if (obj4 == null) {
            obj4 = null;
        }
        L0.f fVar = (L0.f) obj4;
        if (fVar != null) {
            if (fVar != L0.f.d) {
                if (string == null) {
                    G7.a aVar2 = fVar.f3882b;
                    float f10 = aVar2.f2711b;
                    float f11 = aVar2.f2710a;
                    float f12 = ((f10 - f11) > 0.0f ? 1 : ((f10 - f11) == 0.0f ? 0 : -1)) == 0 ? 0.0f : (fVar.f3881a - f11) / (aVar2.f2711b - f11);
                    if (f12 < 0.0f) {
                        f12 = 0.0f;
                    }
                    if (f12 > 1.0f) {
                        f12 = 1.0f;
                    }
                    if (!(f12 == 0.0f)) {
                        iV = (f12 == 1.0f ? 1 : 0) != 0 ? 100 : AbstractC2673a.v(Math.round(f12 * 100), 1, 99);
                    }
                    string = androidComposeView.getContext().getResources().getString(R.string.template_percent, Integer.valueOf(iV));
                }
            } else if (string == null) {
                string = androidComposeView.getContext().getResources().getString(R.string.in_progress);
            }
        }
        L0.u uVar2 = L0.r.f3960x;
        if (linkedHashMap.containsKey(uVar2)) {
            L0.j jVarI = new L0.o(oVar.f3916a, true, oVar.c, jVar).i();
            L0.u uVar3 = L0.r.f3941a;
            LinkedHashMap linkedHashMap2 = jVarI.l;
            Object obj5 = linkedHashMap2.get(uVar3);
            if (obj5 == null) {
                obj5 = null;
            }
            Collection collection = (Collection) obj5;
            if (collection == null || collection.isEmpty()) {
                Object obj6 = linkedHashMap2.get(L0.r.f3957u);
                if (obj6 == null) {
                    obj6 = null;
                }
                Collection collection2 = (Collection) obj6;
                if (collection2 == null || collection2.isEmpty()) {
                    Object obj7 = linkedHashMap2.get(uVar2);
                    if (obj7 == null) {
                        obj7 = null;
                    }
                    CharSequence charSequence = (CharSequence) obj7;
                    if (charSequence == null || charSequence.length() == 0) {
                        string2 = androidComposeView.getContext().getResources().getString(R.string.state_empty);
                    }
                }
            }
            string = string2;
        }
        return (String) string;
    }

    public final boolean y() {
        return this.f2067g.isEnabled() && !this.f2071k.isEmpty();
    }

    public final boolean z(L0.o oVar) {
        Object obj = oVar.d.l.get(L0.r.f3941a);
        if (obj == null) {
            obj = null;
        }
        List list = (List) obj;
        boolean z6 = ((list != null ? (String) AbstractC1998m.a0(list) : null) == null && w(oVar) == null && v(oVar) == null && !u(oVar)) ? false : true;
        if (T.w(oVar)) {
            if (oVar.d.f3909m) {
                return true;
            }
            if (oVar.m() && z6) {
                return true;
            }
        }
        return false;
    }
}
