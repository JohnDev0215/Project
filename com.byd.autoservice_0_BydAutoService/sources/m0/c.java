package m0;

import android.R;
import android.graphics.Rect;
import android.os.Build;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.TextUtils;
import android.text.style.ClickableSpan;
import android.util.Log;
import android.util.SparseArray;
import android.view.View;
import android.view.accessibility.AccessibilityNodeInfo;
import androidx.core.R$id;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import m0.f;

/* JADX INFO: compiled from: AccessibilityNodeInfoCompat.java */
/* JADX INFO: loaded from: classes.dex */
public class c {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static int f3798a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final AccessibilityNodeInfo f3799b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public int f3800c = -1;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public int f3801d = -1;

    /* JADX INFO: compiled from: AccessibilityNodeInfoCompat.java */
    public static class a {
        public static final a A;
        public static final a B;
        public static final a C;
        public static final a D;
        public static final a E;
        public static final a F;
        public static final a G;
        public static final a H;
        public static final a I;
        public static final a J;
        public static final a K;

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public static final a f3802a = new a(1, null);

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public static final a f3803b = new a(2, null);

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public static final a f3804c = new a(4, null);

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public static final a f3805d = new a(8, null);

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        public static final a f3806e = new a(16, null);

        /* JADX INFO: renamed from: f, reason: collision with root package name */
        public static final a f3807f = new a(32, null);

        /* JADX INFO: renamed from: g, reason: collision with root package name */
        public static final a f3808g = new a(64, null);

        /* JADX INFO: renamed from: h, reason: collision with root package name */
        public static final a f3809h = new a(128, null);

        /* JADX INFO: renamed from: i, reason: collision with root package name */
        public static final a f3810i = new a(256, null, f.b.class);

        /* JADX INFO: renamed from: j, reason: collision with root package name */
        public static final a f3811j = new a(512, null, f.b.class);

        /* JADX INFO: renamed from: k, reason: collision with root package name */
        public static final a f3812k = new a(1024, null, f.c.class);

        /* JADX INFO: renamed from: l, reason: collision with root package name */
        public static final a f3813l = new a(2048, null, f.c.class);

        /* JADX INFO: renamed from: m, reason: collision with root package name */
        public static final a f3814m = new a(4096, null);

        /* JADX INFO: renamed from: n, reason: collision with root package name */
        public static final a f3815n = new a(8192, null);

        /* JADX INFO: renamed from: o, reason: collision with root package name */
        public static final a f3816o = new a(16384, null);

        /* JADX INFO: renamed from: p, reason: collision with root package name */
        public static final a f3817p = new a(32768, null);

        /* JADX INFO: renamed from: q, reason: collision with root package name */
        public static final a f3818q = new a(65536, null);

        /* JADX INFO: renamed from: r, reason: collision with root package name */
        public static final a f3819r = new a(131072, null, f.g.class);

        /* JADX INFO: renamed from: s, reason: collision with root package name */
        public static final a f3820s = new a(262144, null);

        /* JADX INFO: renamed from: t, reason: collision with root package name */
        public static final a f3821t = new a(524288, null);

        /* JADX INFO: renamed from: u, reason: collision with root package name */
        public static final a f3822u = new a(1048576, null);

        /* JADX INFO: renamed from: v, reason: collision with root package name */
        public static final a f3823v = new a(2097152, null, f.h.class);

        /* JADX INFO: renamed from: w, reason: collision with root package name */
        public static final a f3824w;

        /* JADX INFO: renamed from: x, reason: collision with root package name */
        public static final a f3825x;

        /* JADX INFO: renamed from: y, reason: collision with root package name */
        public static final a f3826y;

        /* JADX INFO: renamed from: z, reason: collision with root package name */
        public static final a f3827z;
        public final Object L;
        public final int M;
        public final Class<? extends f.a> N;
        public final f O;

        static {
            int i3 = Build.VERSION.SDK_INT;
            f3824w = new a(i3 >= 23 ? AccessibilityNodeInfo.AccessibilityAction.ACTION_SHOW_ON_SCREEN : null, R.id.accessibilityActionShowOnScreen, null, null, null);
            f3825x = new a(i3 >= 23 ? AccessibilityNodeInfo.AccessibilityAction.ACTION_SCROLL_TO_POSITION : null, R.id.accessibilityActionScrollToPosition, null, null, f.e.class);
            f3826y = new a(i3 >= 23 ? AccessibilityNodeInfo.AccessibilityAction.ACTION_SCROLL_UP : null, R.id.accessibilityActionScrollUp, null, null, null);
            f3827z = new a(i3 >= 23 ? AccessibilityNodeInfo.AccessibilityAction.ACTION_SCROLL_LEFT : null, R.id.accessibilityActionScrollLeft, null, null, null);
            A = new a(i3 >= 23 ? AccessibilityNodeInfo.AccessibilityAction.ACTION_SCROLL_DOWN : null, R.id.accessibilityActionScrollDown, null, null, null);
            B = new a(i3 >= 23 ? AccessibilityNodeInfo.AccessibilityAction.ACTION_SCROLL_RIGHT : null, R.id.accessibilityActionScrollRight, null, null, null);
            C = new a(i3 >= 29 ? AccessibilityNodeInfo.AccessibilityAction.ACTION_PAGE_UP : null, R.id.accessibilityActionPageUp, null, null, null);
            D = new a(i3 >= 29 ? AccessibilityNodeInfo.AccessibilityAction.ACTION_PAGE_DOWN : null, R.id.accessibilityActionPageDown, null, null, null);
            E = new a(i3 >= 29 ? AccessibilityNodeInfo.AccessibilityAction.ACTION_PAGE_LEFT : null, R.id.accessibilityActionPageLeft, null, null, null);
            F = new a(i3 >= 29 ? AccessibilityNodeInfo.AccessibilityAction.ACTION_PAGE_RIGHT : null, R.id.accessibilityActionPageRight, null, null, null);
            G = new a(i3 >= 23 ? AccessibilityNodeInfo.AccessibilityAction.ACTION_CONTEXT_CLICK : null, R.id.accessibilityActionContextClick, null, null, null);
            H = new a(i3 >= 24 ? AccessibilityNodeInfo.AccessibilityAction.ACTION_SET_PROGRESS : null, R.id.accessibilityActionSetProgress, null, null, f.C0037f.class);
            I = new a(i3 >= 26 ? AccessibilityNodeInfo.AccessibilityAction.ACTION_MOVE_WINDOW : null, R.id.accessibilityActionMoveWindow, null, null, f.d.class);
            J = new a(i3 >= 28 ? AccessibilityNodeInfo.AccessibilityAction.ACTION_SHOW_TOOLTIP : null, R.id.accessibilityActionShowTooltip, null, null, null);
            K = new a(i3 >= 28 ? AccessibilityNodeInfo.AccessibilityAction.ACTION_HIDE_TOOLTIP : null, R.id.accessibilityActionHideTooltip, null, null, null);
        }

        public a(int i3, CharSequence charSequence) {
            this(null, i3, charSequence, null, null);
        }

        public a a(CharSequence charSequence, f fVar) {
            return new a(null, this.M, charSequence, fVar, this.N);
        }

        public int b() {
            if (Build.VERSION.SDK_INT >= 21) {
                return ((AccessibilityNodeInfo.AccessibilityAction) this.L).getId();
            }
            return 0;
        }

        public CharSequence c() {
            if (Build.VERSION.SDK_INT >= 21) {
                return ((AccessibilityNodeInfo.AccessibilityAction) this.L).getLabel();
            }
            return null;
        }

        public boolean d(View view, Bundle bundle) {
            f.a aVarNewInstance;
            if (this.O == null) {
                return false;
            }
            f.a aVar = null;
            Class<? extends f.a> cls = this.N;
            if (cls != null) {
                try {
                    aVarNewInstance = cls.getDeclaredConstructor(new Class[0]).newInstance(new Object[0]);
                } catch (Exception e3) {
                    e = e3;
                }
                try {
                    aVarNewInstance.a(bundle);
                    aVar = aVarNewInstance;
                } catch (Exception e4) {
                    e = e4;
                    aVar = aVarNewInstance;
                    Class<? extends f.a> cls2 = this.N;
                    Log.e("A11yActionCompat", "Failed to execute command with argument class ViewCommandArgument: " + (cls2 == null ? "null" : cls2.getName()), e);
                }
            }
            return this.O.a(view, aVar);
        }

        public boolean equals(Object obj) {
            if (obj == null || !(obj instanceof a)) {
                return false;
            }
            a aVar = (a) obj;
            Object obj2 = this.L;
            return obj2 == null ? aVar.L == null : obj2.equals(aVar.L);
        }

        public int hashCode() {
            Object obj = this.L;
            if (obj != null) {
                return obj.hashCode();
            }
            return 0;
        }

        public a(Object obj) {
            this(obj, 0, null, null, null);
        }

        public a(int i3, CharSequence charSequence, Class<? extends f.a> cls) {
            this(null, i3, charSequence, null, cls);
        }

        public a(Object obj, int i3, CharSequence charSequence, f fVar, Class<? extends f.a> cls) {
            this.M = i3;
            this.O = fVar;
            if (Build.VERSION.SDK_INT >= 21 && obj == null) {
                this.L = new AccessibilityNodeInfo.AccessibilityAction(i3, charSequence);
            } else {
                this.L = obj;
            }
            this.N = cls;
        }
    }

    /* JADX INFO: compiled from: AccessibilityNodeInfoCompat.java */
    public static class b {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final Object f3828a;

        public b(Object obj) {
            this.f3828a = obj;
        }

        public static b a(int i3, int i4, boolean z3) {
            return Build.VERSION.SDK_INT >= 19 ? new b(AccessibilityNodeInfo.CollectionInfo.obtain(i3, i4, z3)) : new b(null);
        }

        public static b b(int i3, int i4, boolean z3, int i5) {
            int i6 = Build.VERSION.SDK_INT;
            return i6 >= 21 ? new b(AccessibilityNodeInfo.CollectionInfo.obtain(i3, i4, z3, i5)) : i6 >= 19 ? new b(AccessibilityNodeInfo.CollectionInfo.obtain(i3, i4, z3)) : new b(null);
        }
    }

    /* JADX INFO: renamed from: m0.c$c, reason: collision with other inner class name */
    /* JADX INFO: compiled from: AccessibilityNodeInfoCompat.java */
    public static class C0036c {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final Object f3829a;

        public C0036c(Object obj) {
            this.f3829a = obj;
        }

        public static C0036c a(int i3, int i4, int i5, int i6, boolean z3, boolean z4) {
            int i7 = Build.VERSION.SDK_INT;
            return i7 >= 21 ? new C0036c(AccessibilityNodeInfo.CollectionItemInfo.obtain(i3, i4, i5, i6, z3, z4)) : i7 >= 19 ? new C0036c(AccessibilityNodeInfo.CollectionItemInfo.obtain(i3, i4, i5, i6, z3)) : new C0036c(null);
        }
    }

    /* JADX INFO: compiled from: AccessibilityNodeInfoCompat.java */
    public static class d {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final Object f3830a;

        public d(Object obj) {
            this.f3830a = obj;
        }

        public static d a(int i3, float f3, float f4, float f5) {
            return Build.VERSION.SDK_INT >= 19 ? new d(AccessibilityNodeInfo.RangeInfo.obtain(i3, f3, f4, f5)) : new d(null);
        }
    }

    public c(AccessibilityNodeInfo accessibilityNodeInfo) {
        this.f3799b = accessibilityNodeInfo;
    }

    public static c D0(AccessibilityNodeInfo accessibilityNodeInfo) {
        return new c(accessibilityNodeInfo);
    }

    public static c N() {
        return D0(AccessibilityNodeInfo.obtain());
    }

    public static c O(View view) {
        return D0(AccessibilityNodeInfo.obtain(view));
    }

    public static c P(c cVar) {
        return D0(AccessibilityNodeInfo.obtain(cVar.f3799b));
    }

    public static String j(int i3) {
        if (i3 == 1) {
            return "ACTION_FOCUS";
        }
        if (i3 == 2) {
            return "ACTION_CLEAR_FOCUS";
        }
        switch (i3) {
            case 4:
                return "ACTION_SELECT";
            case 8:
                return "ACTION_CLEAR_SELECTION";
            case 16:
                return "ACTION_CLICK";
            case 32:
                return "ACTION_LONG_CLICK";
            case 64:
                return "ACTION_ACCESSIBILITY_FOCUS";
            case 128:
                return "ACTION_CLEAR_ACCESSIBILITY_FOCUS";
            case 256:
                return "ACTION_NEXT_AT_MOVEMENT_GRANULARITY";
            case 512:
                return "ACTION_PREVIOUS_AT_MOVEMENT_GRANULARITY";
            case 1024:
                return "ACTION_NEXT_HTML_ELEMENT";
            case 2048:
                return "ACTION_PREVIOUS_HTML_ELEMENT";
            case 4096:
                return "ACTION_SCROLL_FORWARD";
            case 8192:
                return "ACTION_SCROLL_BACKWARD";
            case 16384:
                return "ACTION_COPY";
            case 32768:
                return "ACTION_PASTE";
            case 65536:
                return "ACTION_CUT";
            case 131072:
                return "ACTION_SET_SELECTION";
            case 262144:
                return "ACTION_EXPAND";
            case 524288:
                return "ACTION_COLLAPSE";
            case 2097152:
                return "ACTION_SET_TEXT";
            case R.id.accessibilityActionMoveWindow:
                return "ACTION_MOVE_WINDOW";
            default:
                switch (i3) {
                    case R.id.accessibilityActionShowOnScreen:
                        return "ACTION_SHOW_ON_SCREEN";
                    case R.id.accessibilityActionScrollToPosition:
                        return "ACTION_SCROLL_TO_POSITION";
                    case R.id.accessibilityActionScrollUp:
                        return "ACTION_SCROLL_UP";
                    case R.id.accessibilityActionScrollLeft:
                        return "ACTION_SCROLL_LEFT";
                    case R.id.accessibilityActionScrollDown:
                        return "ACTION_SCROLL_DOWN";
                    case R.id.accessibilityActionScrollRight:
                        return "ACTION_SCROLL_RIGHT";
                    case R.id.accessibilityActionContextClick:
                        return "ACTION_CONTEXT_CLICK";
                    case R.id.accessibilityActionSetProgress:
                        return "ACTION_SET_PROGRESS";
                    default:
                        switch (i3) {
                            case R.id.accessibilityActionShowTooltip:
                                return "ACTION_SHOW_TOOLTIP";
                            case R.id.accessibilityActionHideTooltip:
                                return "ACTION_HIDE_TOOLTIP";
                            case R.id.accessibilityActionPageUp:
                                return "ACTION_PAGE_UP";
                            case R.id.accessibilityActionPageDown:
                                return "ACTION_PAGE_DOWN";
                            case R.id.accessibilityActionPageLeft:
                                return "ACTION_PAGE_LEFT";
                            case R.id.accessibilityActionPageRight:
                                return "ACTION_PAGE_RIGHT";
                            default:
                                return "ACTION_UNKNOWN";
                        }
                }
        }
    }

    public static ClickableSpan[] q(CharSequence charSequence) {
        if (charSequence instanceof Spanned) {
            return (ClickableSpan[]) ((Spanned) charSequence).getSpans(0, charSequence.length(), ClickableSpan.class);
        }
        return null;
    }

    public boolean A() {
        if (Build.VERSION.SDK_INT >= 16) {
            return this.f3799b.isAccessibilityFocused();
        }
        return false;
    }

    public void A0(CharSequence charSequence) {
        this.f3799b.setText(charSequence);
    }

    public boolean B() {
        return this.f3799b.isCheckable();
    }

    public void B0(boolean z3) {
        if (Build.VERSION.SDK_INT >= 16) {
            this.f3799b.setVisibleToUser(z3);
        }
    }

    public boolean C() {
        return this.f3799b.isChecked();
    }

    public AccessibilityNodeInfo C0() {
        return this.f3799b;
    }

    public boolean D() {
        return this.f3799b.isClickable();
    }

    public boolean E() {
        return this.f3799b.isEnabled();
    }

    public boolean F() {
        return this.f3799b.isFocusable();
    }

    public boolean G() {
        return this.f3799b.isFocused();
    }

    public boolean H() {
        return this.f3799b.isLongClickable();
    }

    public boolean I() {
        return this.f3799b.isPassword();
    }

    public boolean J() {
        return this.f3799b.isScrollable();
    }

    public boolean K() {
        return this.f3799b.isSelected();
    }

    public boolean L() {
        return Build.VERSION.SDK_INT >= 26 ? this.f3799b.isShowingHintText() : l(4);
    }

    public boolean M() {
        if (Build.VERSION.SDK_INT >= 16) {
            return this.f3799b.isVisibleToUser();
        }
        return false;
    }

    public boolean Q(int i3, Bundle bundle) {
        if (Build.VERSION.SDK_INT >= 16) {
            return this.f3799b.performAction(i3, bundle);
        }
        return false;
    }

    public void R() {
        this.f3799b.recycle();
    }

    public boolean S(a aVar) {
        if (Build.VERSION.SDK_INT >= 21) {
            return this.f3799b.removeAction((AccessibilityNodeInfo.AccessibilityAction) aVar.L);
        }
        return false;
    }

    public final void T(View view) {
        SparseArray<WeakReference<ClickableSpan>> sparseArrayV = v(view);
        if (sparseArrayV != null) {
            ArrayList arrayList = new ArrayList();
            for (int i3 = 0; i3 < sparseArrayV.size(); i3++) {
                if (sparseArrayV.valueAt(i3).get() == null) {
                    arrayList.add(Integer.valueOf(i3));
                }
            }
            for (int i4 = 0; i4 < arrayList.size(); i4++) {
                sparseArrayV.remove(((Integer) arrayList.get(i4)).intValue());
            }
        }
    }

    public void U(boolean z3) {
        if (Build.VERSION.SDK_INT >= 16) {
            this.f3799b.setAccessibilityFocused(z3);
        }
    }

    public final void V(int i3, boolean z3) {
        Bundle bundleS = s();
        if (bundleS != null) {
            int i4 = bundleS.getInt("androidx.view.accessibility.AccessibilityNodeInfoCompat.BOOLEAN_PROPERTY_KEY", 0) & (~i3);
            if (!z3) {
                i3 = 0;
            }
            bundleS.putInt("androidx.view.accessibility.AccessibilityNodeInfoCompat.BOOLEAN_PROPERTY_KEY", i3 | i4);
        }
    }

    @Deprecated
    public void W(Rect rect) {
        this.f3799b.setBoundsInParent(rect);
    }

    public void X(Rect rect) {
        this.f3799b.setBoundsInScreen(rect);
    }

    public void Y(boolean z3) {
        if (Build.VERSION.SDK_INT >= 19) {
            this.f3799b.setCanOpenPopup(z3);
        }
    }

    public void Z(boolean z3) {
        this.f3799b.setCheckable(z3);
    }

    public void a(int i3) {
        this.f3799b.addAction(i3);
    }

    public void a0(boolean z3) {
        this.f3799b.setChecked(z3);
    }

    public void b(a aVar) {
        if (Build.VERSION.SDK_INT >= 21) {
            this.f3799b.addAction((AccessibilityNodeInfo.AccessibilityAction) aVar.L);
        }
    }

    public void b0(CharSequence charSequence) {
        this.f3799b.setClassName(charSequence);
    }

    public void c(View view) {
        this.f3799b.addChild(view);
    }

    public void c0(boolean z3) {
        this.f3799b.setClickable(z3);
    }

    public void d(View view, int i3) {
        if (Build.VERSION.SDK_INT >= 16) {
            this.f3799b.addChild(view, i3);
        }
    }

    public void d0(Object obj) {
        if (Build.VERSION.SDK_INT >= 19) {
            this.f3799b.setCollectionInfo(obj == null ? null : (AccessibilityNodeInfo.CollectionInfo) ((b) obj).f3828a);
        }
    }

    public final void e(ClickableSpan clickableSpan, Spanned spanned, int i3) {
        h("androidx.view.accessibility.AccessibilityNodeInfoCompat.SPANS_START_KEY").add(Integer.valueOf(spanned.getSpanStart(clickableSpan)));
        h("androidx.view.accessibility.AccessibilityNodeInfoCompat.SPANS_END_KEY").add(Integer.valueOf(spanned.getSpanEnd(clickableSpan)));
        h("androidx.view.accessibility.AccessibilityNodeInfoCompat.SPANS_FLAGS_KEY").add(Integer.valueOf(spanned.getSpanFlags(clickableSpan)));
        h("androidx.view.accessibility.AccessibilityNodeInfoCompat.SPANS_ID_KEY").add(Integer.valueOf(i3));
    }

    public void e0(Object obj) {
        if (Build.VERSION.SDK_INT >= 19) {
            this.f3799b.setCollectionItemInfo(obj == null ? null : (AccessibilityNodeInfo.CollectionItemInfo) ((C0036c) obj).f3829a);
        }
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof c)) {
            return false;
        }
        c cVar = (c) obj;
        AccessibilityNodeInfo accessibilityNodeInfo = this.f3799b;
        if (accessibilityNodeInfo == null) {
            if (cVar.f3799b != null) {
                return false;
            }
        } else if (!accessibilityNodeInfo.equals(cVar.f3799b)) {
            return false;
        }
        return this.f3801d == cVar.f3801d && this.f3800c == cVar.f3800c;
    }

    public void f(CharSequence charSequence, View view) {
        int i3 = Build.VERSION.SDK_INT;
        if (i3 < 19 || i3 >= 26) {
            return;
        }
        g();
        T(view);
        ClickableSpan[] clickableSpanArrQ = q(charSequence);
        if (clickableSpanArrQ == null || clickableSpanArrQ.length <= 0) {
            return;
        }
        s().putInt("androidx.view.accessibility.AccessibilityNodeInfoCompat.SPANS_ACTION_ID_KEY", R$id.accessibility_action_clickable_span);
        SparseArray<WeakReference<ClickableSpan>> sparseArrayT = t(view);
        for (int i4 = 0; i4 < clickableSpanArrQ.length; i4++) {
            int iZ = z(clickableSpanArrQ[i4], sparseArrayT);
            sparseArrayT.put(iZ, new WeakReference<>(clickableSpanArrQ[i4]));
            e(clickableSpanArrQ[i4], (Spanned) charSequence, iZ);
        }
    }

    public void f0(CharSequence charSequence) {
        this.f3799b.setContentDescription(charSequence);
    }

    public final void g() {
        if (Build.VERSION.SDK_INT >= 19) {
            this.f3799b.getExtras().remove("androidx.view.accessibility.AccessibilityNodeInfoCompat.SPANS_START_KEY");
            this.f3799b.getExtras().remove("androidx.view.accessibility.AccessibilityNodeInfoCompat.SPANS_END_KEY");
            this.f3799b.getExtras().remove("androidx.view.accessibility.AccessibilityNodeInfoCompat.SPANS_FLAGS_KEY");
            this.f3799b.getExtras().remove("androidx.view.accessibility.AccessibilityNodeInfoCompat.SPANS_ID_KEY");
        }
    }

    public void g0(boolean z3) {
        this.f3799b.setEnabled(z3);
    }

    public final List<Integer> h(String str) {
        if (Build.VERSION.SDK_INT < 19) {
            return new ArrayList();
        }
        ArrayList<Integer> integerArrayList = this.f3799b.getExtras().getIntegerArrayList(str);
        if (integerArrayList != null) {
            return integerArrayList;
        }
        ArrayList<Integer> arrayList = new ArrayList<>();
        this.f3799b.getExtras().putIntegerArrayList(str, arrayList);
        return arrayList;
    }

    public void h0(CharSequence charSequence) {
        if (Build.VERSION.SDK_INT >= 21) {
            this.f3799b.setError(charSequence);
        }
    }

    public int hashCode() {
        AccessibilityNodeInfo accessibilityNodeInfo = this.f3799b;
        if (accessibilityNodeInfo == null) {
            return 0;
        }
        return accessibilityNodeInfo.hashCode();
    }

    public List<a> i() {
        List<AccessibilityNodeInfo.AccessibilityAction> actionList = Build.VERSION.SDK_INT >= 21 ? this.f3799b.getActionList() : null;
        if (actionList == null) {
            return Collections.emptyList();
        }
        ArrayList arrayList = new ArrayList();
        int size = actionList.size();
        for (int i3 = 0; i3 < size; i3++) {
            arrayList.add(new a(actionList.get(i3)));
        }
        return arrayList;
    }

    public void i0(boolean z3) {
        this.f3799b.setFocusable(z3);
    }

    public void j0(boolean z3) {
        this.f3799b.setFocused(z3);
    }

    public int k() {
        return this.f3799b.getActions();
    }

    public void k0(boolean z3) {
        if (Build.VERSION.SDK_INT >= 28) {
            this.f3799b.setHeading(z3);
        } else {
            V(2, z3);
        }
    }

    public final boolean l(int i3) {
        Bundle bundleS = s();
        return bundleS != null && (bundleS.getInt("androidx.view.accessibility.AccessibilityNodeInfoCompat.BOOLEAN_PROPERTY_KEY", 0) & i3) == i3;
    }

    public void l0(CharSequence charSequence) {
        int i3 = Build.VERSION.SDK_INT;
        if (i3 >= 26) {
            this.f3799b.setHintText(charSequence);
        } else if (i3 >= 19) {
            this.f3799b.getExtras().putCharSequence("androidx.view.accessibility.AccessibilityNodeInfoCompat.HINT_TEXT_KEY", charSequence);
        }
    }

    @Deprecated
    public void m(Rect rect) {
        this.f3799b.getBoundsInParent(rect);
    }

    public void m0(boolean z3) {
        this.f3799b.setLongClickable(z3);
    }

    public void n(Rect rect) {
        this.f3799b.getBoundsInScreen(rect);
    }

    public void n0(int i3) {
        if (Build.VERSION.SDK_INT >= 21) {
            this.f3799b.setMaxTextLength(i3);
        }
    }

    public int o() {
        return this.f3799b.getChildCount();
    }

    public void o0(CharSequence charSequence) {
        this.f3799b.setPackageName(charSequence);
    }

    public CharSequence p() {
        return this.f3799b.getClassName();
    }

    public void p0(CharSequence charSequence) {
        int i3 = Build.VERSION.SDK_INT;
        if (i3 >= 28) {
            this.f3799b.setPaneTitle(charSequence);
        } else if (i3 >= 19) {
            this.f3799b.getExtras().putCharSequence("androidx.view.accessibility.AccessibilityNodeInfoCompat.PANE_TITLE_KEY", charSequence);
        }
    }

    public void q0(View view) {
        this.f3800c = -1;
        this.f3799b.setParent(view);
    }

    public CharSequence r() {
        return this.f3799b.getContentDescription();
    }

    public void r0(View view, int i3) {
        this.f3800c = i3;
        if (Build.VERSION.SDK_INT >= 16) {
            this.f3799b.setParent(view, i3);
        }
    }

    public Bundle s() {
        return Build.VERSION.SDK_INT >= 19 ? this.f3799b.getExtras() : new Bundle();
    }

    public void s0(d dVar) {
        if (Build.VERSION.SDK_INT >= 19) {
            this.f3799b.setRangeInfo((AccessibilityNodeInfo.RangeInfo) dVar.f3830a);
        }
    }

    public final SparseArray<WeakReference<ClickableSpan>> t(View view) {
        SparseArray<WeakReference<ClickableSpan>> sparseArrayV = v(view);
        if (sparseArrayV != null) {
            return sparseArrayV;
        }
        SparseArray<WeakReference<ClickableSpan>> sparseArray = new SparseArray<>();
        view.setTag(R$id.tag_accessibility_clickable_spans, sparseArray);
        return sparseArray;
    }

    public void t0(CharSequence charSequence) {
        if (Build.VERSION.SDK_INT >= 19) {
            this.f3799b.getExtras().putCharSequence("AccessibilityNodeInfo.roleDescription", charSequence);
        }
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(super.toString());
        Rect rect = new Rect();
        m(rect);
        sb.append("; boundsInParent: " + rect);
        n(rect);
        sb.append("; boundsInScreen: " + rect);
        sb.append("; packageName: ");
        sb.append(u());
        sb.append("; className: ");
        sb.append(p());
        sb.append("; text: ");
        sb.append(w());
        sb.append("; contentDescription: ");
        sb.append(r());
        sb.append("; viewId: ");
        sb.append(x());
        sb.append("; checkable: ");
        sb.append(B());
        sb.append("; checked: ");
        sb.append(C());
        sb.append("; focusable: ");
        sb.append(F());
        sb.append("; focused: ");
        sb.append(G());
        sb.append("; selected: ");
        sb.append(K());
        sb.append("; clickable: ");
        sb.append(D());
        sb.append("; longClickable: ");
        sb.append(H());
        sb.append("; enabled: ");
        sb.append(E());
        sb.append("; password: ");
        sb.append(I());
        sb.append("; scrollable: " + J());
        sb.append("; [");
        if (Build.VERSION.SDK_INT >= 21) {
            List<a> listI = i();
            for (int i3 = 0; i3 < listI.size(); i3++) {
                a aVar = listI.get(i3);
                String strJ = j(aVar.b());
                if (strJ.equals("ACTION_UNKNOWN") && aVar.c() != null) {
                    strJ = aVar.c().toString();
                }
                sb.append(strJ);
                if (i3 != listI.size() - 1) {
                    sb.append(", ");
                }
            }
        } else {
            int iK = k();
            while (iK != 0) {
                int iNumberOfTrailingZeros = 1 << Integer.numberOfTrailingZeros(iK);
                iK &= ~iNumberOfTrailingZeros;
                sb.append(j(iNumberOfTrailingZeros));
                if (iK != 0) {
                    sb.append(", ");
                }
            }
        }
        sb.append("]");
        return sb.toString();
    }

    public CharSequence u() {
        return this.f3799b.getPackageName();
    }

    public void u0(boolean z3) {
        if (Build.VERSION.SDK_INT >= 28) {
            this.f3799b.setScreenReaderFocusable(z3);
        } else {
            V(1, z3);
        }
    }

    public final SparseArray<WeakReference<ClickableSpan>> v(View view) {
        return (SparseArray) view.getTag(R$id.tag_accessibility_clickable_spans);
    }

    public void v0(boolean z3) {
        this.f3799b.setScrollable(z3);
    }

    public CharSequence w() {
        if (!y()) {
            return this.f3799b.getText();
        }
        List<Integer> listH = h("androidx.view.accessibility.AccessibilityNodeInfoCompat.SPANS_START_KEY");
        List<Integer> listH2 = h("androidx.view.accessibility.AccessibilityNodeInfoCompat.SPANS_END_KEY");
        List<Integer> listH3 = h("androidx.view.accessibility.AccessibilityNodeInfoCompat.SPANS_FLAGS_KEY");
        List<Integer> listH4 = h("androidx.view.accessibility.AccessibilityNodeInfoCompat.SPANS_ID_KEY");
        SpannableString spannableString = new SpannableString(TextUtils.substring(this.f3799b.getText(), 0, this.f3799b.getText().length()));
        for (int i3 = 0; i3 < listH.size(); i3++) {
            spannableString.setSpan(new m0.a(listH4.get(i3).intValue(), this, s().getInt("androidx.view.accessibility.AccessibilityNodeInfoCompat.SPANS_ACTION_ID_KEY")), listH.get(i3).intValue(), listH2.get(i3).intValue(), listH3.get(i3).intValue());
        }
        return spannableString;
    }

    public void w0(boolean z3) {
        this.f3799b.setSelected(z3);
    }

    public String x() {
        if (Build.VERSION.SDK_INT >= 18) {
            return this.f3799b.getViewIdResourceName();
        }
        return null;
    }

    public void x0(boolean z3) {
        if (Build.VERSION.SDK_INT >= 26) {
            this.f3799b.setShowingHintText(z3);
        } else {
            V(4, z3);
        }
    }

    public final boolean y() {
        return !h("androidx.view.accessibility.AccessibilityNodeInfoCompat.SPANS_START_KEY").isEmpty();
    }

    public void y0(View view) {
        this.f3801d = -1;
        this.f3799b.setSource(view);
    }

    public final int z(ClickableSpan clickableSpan, SparseArray<WeakReference<ClickableSpan>> sparseArray) {
        if (sparseArray != null) {
            for (int i3 = 0; i3 < sparseArray.size(); i3++) {
                if (clickableSpan.equals(sparseArray.valueAt(i3).get())) {
                    return sparseArray.keyAt(i3);
                }
            }
        }
        int i4 = f3798a;
        f3798a = i4 + 1;
        return i4;
    }

    public void z0(View view, int i3) {
        this.f3801d = i3;
        if (Build.VERSION.SDK_INT >= 16) {
            this.f3799b.setSource(view, i3);
        }
    }
}
