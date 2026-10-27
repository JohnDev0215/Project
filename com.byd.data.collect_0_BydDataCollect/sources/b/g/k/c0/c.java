package b.g.k.c0;

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
import b.g.k.c0.f;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/* JADX INFO: compiled from: AccessibilityNodeInfoCompat.java */
/* JADX INFO: loaded from: classes.dex */
public class c {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private static int f2692a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final AccessibilityNodeInfo f2693b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public int f2694c = -1;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private int f2695d = -1;

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
        public static final a f2696a = new a(1, null);

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public static final a f2697b = new a(2, null);

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public static final a f2698c = new a(4, null);

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public static final a f2699d = new a(8, null);

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        public static final a f2700e = new a(16, null);

        /* JADX INFO: renamed from: f, reason: collision with root package name */
        public static final a f2701f = new a(32, null);

        /* JADX INFO: renamed from: g, reason: collision with root package name */
        public static final a f2702g = new a(64, null);

        /* JADX INFO: renamed from: h, reason: collision with root package name */
        public static final a f2703h = new a(128, null);

        /* JADX INFO: renamed from: i, reason: collision with root package name */
        public static final a f2704i = new a(256, null, f.b.class);
        public static final a j = new a(512, null, f.b.class);
        public static final a k = new a(1024, null, f.c.class);
        public static final a l = new a(2048, null, f.c.class);
        public static final a m = new a(4096, null);
        public static final a n = new a(8192, null);
        public static final a o = new a(16384, null);
        public static final a p = new a(32768, null);
        public static final a q = new a(65536, null);
        public static final a r = new a(131072, null, f.g.class);
        public static final a s = new a(262144, null);
        public static final a t = new a(524288, null);
        public static final a u = new a(1048576, null);
        public static final a v = new a(2097152, null, f.h.class);
        public static final a w;
        public static final a x;
        public static final a y;
        public static final a z;
        final Object L;
        private final int M;
        private final Class<? extends f.a> N;
        protected final f O;

        static {
            int i2 = Build.VERSION.SDK_INT;
            w = new a(i2 >= 23 ? AccessibilityNodeInfo.AccessibilityAction.ACTION_SHOW_ON_SCREEN : null, R.id.accessibilityActionShowOnScreen, null, null, null);
            x = new a(i2 >= 23 ? AccessibilityNodeInfo.AccessibilityAction.ACTION_SCROLL_TO_POSITION : null, R.id.accessibilityActionScrollToPosition, null, null, f.e.class);
            y = new a(i2 >= 23 ? AccessibilityNodeInfo.AccessibilityAction.ACTION_SCROLL_UP : null, R.id.accessibilityActionScrollUp, null, null, null);
            z = new a(i2 >= 23 ? AccessibilityNodeInfo.AccessibilityAction.ACTION_SCROLL_LEFT : null, R.id.accessibilityActionScrollLeft, null, null, null);
            A = new a(i2 >= 23 ? AccessibilityNodeInfo.AccessibilityAction.ACTION_SCROLL_DOWN : null, R.id.accessibilityActionScrollDown, null, null, null);
            B = new a(i2 >= 23 ? AccessibilityNodeInfo.AccessibilityAction.ACTION_SCROLL_RIGHT : null, R.id.accessibilityActionScrollRight, null, null, null);
            C = new a(i2 >= 29 ? AccessibilityNodeInfo.AccessibilityAction.ACTION_PAGE_UP : null, R.id.accessibilityActionPageUp, null, null, null);
            D = new a(i2 >= 29 ? AccessibilityNodeInfo.AccessibilityAction.ACTION_PAGE_DOWN : null, R.id.accessibilityActionPageDown, null, null, null);
            E = new a(i2 >= 29 ? AccessibilityNodeInfo.AccessibilityAction.ACTION_PAGE_LEFT : null, R.id.accessibilityActionPageLeft, null, null, null);
            F = new a(i2 >= 29 ? AccessibilityNodeInfo.AccessibilityAction.ACTION_PAGE_RIGHT : null, R.id.accessibilityActionPageRight, null, null, null);
            G = new a(i2 >= 23 ? AccessibilityNodeInfo.AccessibilityAction.ACTION_CONTEXT_CLICK : null, R.id.accessibilityActionContextClick, null, null, null);
            H = new a(i2 >= 24 ? AccessibilityNodeInfo.AccessibilityAction.ACTION_SET_PROGRESS : null, R.id.accessibilityActionSetProgress, null, null, f.C0048f.class);
            I = new a(i2 >= 26 ? AccessibilityNodeInfo.AccessibilityAction.ACTION_MOVE_WINDOW : null, R.id.accessibilityActionMoveWindow, null, null, f.d.class);
            J = new a(i2 >= 28 ? AccessibilityNodeInfo.AccessibilityAction.ACTION_SHOW_TOOLTIP : null, R.id.accessibilityActionShowTooltip, null, null, null);
            K = new a(i2 >= 28 ? AccessibilityNodeInfo.AccessibilityAction.ACTION_HIDE_TOOLTIP : null, R.id.accessibilityActionHideTooltip, null, null, null);
        }

        public a(int i2, CharSequence charSequence) {
            this(null, i2, charSequence, null, null);
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
            if (this.O == null) {
                return false;
            }
            f.a aVar = null;
            Class<? extends f.a> cls = this.N;
            if (cls != null) {
                try {
                    f.a aVarNewInstance = cls.getDeclaredConstructor(new Class[0]).newInstance(new Object[0]);
                    try {
                        aVarNewInstance.a(bundle);
                        aVar = aVarNewInstance;
                    } catch (Exception e2) {
                        e = e2;
                        aVar = aVarNewInstance;
                        Class<? extends f.a> cls2 = this.N;
                        Log.e("A11yActionCompat", "Failed to execute command with argument class ViewCommandArgument: " + (cls2 == null ? "null" : cls2.getName()), e);
                    }
                } catch (Exception e3) {
                    e = e3;
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

        a(Object obj) {
            this(obj, 0, null, null, null);
        }

        private a(int i2, CharSequence charSequence, Class<? extends f.a> cls) {
            this(null, i2, charSequence, null, cls);
        }

        a(Object obj, int i2, CharSequence charSequence, f fVar, Class<? extends f.a> cls) {
            this.M = i2;
            this.O = fVar;
            if (Build.VERSION.SDK_INT >= 21 && obj == null) {
                this.L = new AccessibilityNodeInfo.AccessibilityAction(i2, charSequence);
            } else {
                this.L = obj;
            }
            this.N = cls;
        }
    }

    /* JADX INFO: compiled from: AccessibilityNodeInfoCompat.java */
    public static class b {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final Object f2705a;

        b(Object obj) {
            this.f2705a = obj;
        }

        public static b a(int i2, int i3, boolean z) {
            return Build.VERSION.SDK_INT >= 19 ? new b(AccessibilityNodeInfo.CollectionInfo.obtain(i2, i3, z)) : new b(null);
        }

        public static b b(int i2, int i3, boolean z, int i4) {
            int i5 = Build.VERSION.SDK_INT;
            return i5 >= 21 ? new b(AccessibilityNodeInfo.CollectionInfo.obtain(i2, i3, z, i4)) : i5 >= 19 ? new b(AccessibilityNodeInfo.CollectionInfo.obtain(i2, i3, z)) : new b(null);
        }
    }

    /* JADX INFO: renamed from: b.g.k.c0.c$c, reason: collision with other inner class name */
    /* JADX INFO: compiled from: AccessibilityNodeInfoCompat.java */
    public static class C0047c {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final Object f2706a;

        C0047c(Object obj) {
            this.f2706a = obj;
        }

        public static C0047c a(int i2, int i3, int i4, int i5, boolean z, boolean z2) {
            int i6 = Build.VERSION.SDK_INT;
            return i6 >= 21 ? new C0047c(AccessibilityNodeInfo.CollectionItemInfo.obtain(i2, i3, i4, i5, z, z2)) : i6 >= 19 ? new C0047c(AccessibilityNodeInfo.CollectionItemInfo.obtain(i2, i3, i4, i5, z)) : new C0047c(null);
        }
    }

    /* JADX INFO: compiled from: AccessibilityNodeInfoCompat.java */
    public static class d {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final Object f2707a;

        d(Object obj) {
            this.f2707a = obj;
        }

        public static d a(int i2, float f2, float f3, float f4) {
            return Build.VERSION.SDK_INT >= 19 ? new d(AccessibilityNodeInfo.RangeInfo.obtain(i2, f2, f3, f4)) : new d(null);
        }
    }

    private c(AccessibilityNodeInfo accessibilityNodeInfo) {
        this.f2693b = accessibilityNodeInfo;
    }

    public static c C0(AccessibilityNodeInfo accessibilityNodeInfo) {
        return new c(accessibilityNodeInfo);
    }

    public static c N() {
        return C0(AccessibilityNodeInfo.obtain());
    }

    public static c O(View view) {
        return C0(AccessibilityNodeInfo.obtain(view));
    }

    public static c P(c cVar) {
        return C0(AccessibilityNodeInfo.obtain(cVar.f2693b));
    }

    private void T(View view) {
        SparseArray<WeakReference<ClickableSpan>> sparseArrayV = v(view);
        if (sparseArrayV != null) {
            ArrayList arrayList = new ArrayList();
            for (int i2 = 0; i2 < sparseArrayV.size(); i2++) {
                if (sparseArrayV.valueAt(i2).get() == null) {
                    arrayList.add(Integer.valueOf(i2));
                }
            }
            for (int i3 = 0; i3 < arrayList.size(); i3++) {
                sparseArrayV.remove(((Integer) arrayList.get(i3)).intValue());
            }
        }
    }

    private void V(int i2, boolean z) {
        Bundle bundleS = s();
        if (bundleS != null) {
            int i3 = bundleS.getInt("androidx.view.accessibility.AccessibilityNodeInfoCompat.BOOLEAN_PROPERTY_KEY", 0) & (~i2);
            if (!z) {
                i2 = 0;
            }
            bundleS.putInt("androidx.view.accessibility.AccessibilityNodeInfoCompat.BOOLEAN_PROPERTY_KEY", i2 | i3);
        }
    }

    private void e(ClickableSpan clickableSpan, Spanned spanned, int i2) {
        h("androidx.view.accessibility.AccessibilityNodeInfoCompat.SPANS_START_KEY").add(Integer.valueOf(spanned.getSpanStart(clickableSpan)));
        h("androidx.view.accessibility.AccessibilityNodeInfoCompat.SPANS_END_KEY").add(Integer.valueOf(spanned.getSpanEnd(clickableSpan)));
        h("androidx.view.accessibility.AccessibilityNodeInfoCompat.SPANS_FLAGS_KEY").add(Integer.valueOf(spanned.getSpanFlags(clickableSpan)));
        h("androidx.view.accessibility.AccessibilityNodeInfoCompat.SPANS_ID_KEY").add(Integer.valueOf(i2));
    }

    private void g() {
        if (Build.VERSION.SDK_INT >= 19) {
            this.f2693b.getExtras().remove("androidx.view.accessibility.AccessibilityNodeInfoCompat.SPANS_START_KEY");
            this.f2693b.getExtras().remove("androidx.view.accessibility.AccessibilityNodeInfoCompat.SPANS_END_KEY");
            this.f2693b.getExtras().remove("androidx.view.accessibility.AccessibilityNodeInfoCompat.SPANS_FLAGS_KEY");
            this.f2693b.getExtras().remove("androidx.view.accessibility.AccessibilityNodeInfoCompat.SPANS_ID_KEY");
        }
    }

    private List<Integer> h(String str) {
        if (Build.VERSION.SDK_INT < 19) {
            return new ArrayList();
        }
        ArrayList<Integer> integerArrayList = this.f2693b.getExtras().getIntegerArrayList(str);
        if (integerArrayList != null) {
            return integerArrayList;
        }
        ArrayList<Integer> arrayList = new ArrayList<>();
        this.f2693b.getExtras().putIntegerArrayList(str, arrayList);
        return arrayList;
    }

    private static String j(int i2) {
        if (i2 == 1) {
            return "ACTION_FOCUS";
        }
        if (i2 == 2) {
            return "ACTION_CLEAR_FOCUS";
        }
        switch (i2) {
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
                switch (i2) {
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
                        switch (i2) {
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

    private boolean l(int i2) {
        Bundle bundleS = s();
        return bundleS != null && (bundleS.getInt("androidx.view.accessibility.AccessibilityNodeInfoCompat.BOOLEAN_PROPERTY_KEY", 0) & i2) == i2;
    }

    public static ClickableSpan[] q(CharSequence charSequence) {
        if (charSequence instanceof Spanned) {
            return (ClickableSpan[]) ((Spanned) charSequence).getSpans(0, charSequence.length(), ClickableSpan.class);
        }
        return null;
    }

    private SparseArray<WeakReference<ClickableSpan>> t(View view) {
        SparseArray<WeakReference<ClickableSpan>> sparseArrayV = v(view);
        if (sparseArrayV != null) {
            return sparseArrayV;
        }
        SparseArray<WeakReference<ClickableSpan>> sparseArray = new SparseArray<>();
        view.setTag(b.g.b.tag_accessibility_clickable_spans, sparseArray);
        return sparseArray;
    }

    private SparseArray<WeakReference<ClickableSpan>> v(View view) {
        return (SparseArray) view.getTag(b.g.b.tag_accessibility_clickable_spans);
    }

    private boolean y() {
        return !h("androidx.view.accessibility.AccessibilityNodeInfoCompat.SPANS_START_KEY").isEmpty();
    }

    private int z(ClickableSpan clickableSpan, SparseArray<WeakReference<ClickableSpan>> sparseArray) {
        if (sparseArray != null) {
            for (int i2 = 0; i2 < sparseArray.size(); i2++) {
                if (clickableSpan.equals(sparseArray.valueAt(i2).get())) {
                    return sparseArray.keyAt(i2);
                }
            }
        }
        int i3 = f2692a;
        f2692a = i3 + 1;
        return i3;
    }

    public boolean A() {
        if (Build.VERSION.SDK_INT >= 16) {
            return this.f2693b.isAccessibilityFocused();
        }
        return false;
    }

    public void A0(boolean z) {
        if (Build.VERSION.SDK_INT >= 16) {
            this.f2693b.setVisibleToUser(z);
        }
    }

    public boolean B() {
        return this.f2693b.isCheckable();
    }

    public AccessibilityNodeInfo B0() {
        return this.f2693b;
    }

    public boolean C() {
        return this.f2693b.isChecked();
    }

    public boolean D() {
        return this.f2693b.isClickable();
    }

    public boolean E() {
        return this.f2693b.isEnabled();
    }

    public boolean F() {
        return this.f2693b.isFocusable();
    }

    public boolean G() {
        return this.f2693b.isFocused();
    }

    public boolean H() {
        return this.f2693b.isLongClickable();
    }

    public boolean I() {
        return this.f2693b.isPassword();
    }

    public boolean J() {
        return this.f2693b.isScrollable();
    }

    public boolean K() {
        return this.f2693b.isSelected();
    }

    public boolean L() {
        return Build.VERSION.SDK_INT >= 26 ? this.f2693b.isShowingHintText() : l(4);
    }

    public boolean M() {
        if (Build.VERSION.SDK_INT >= 16) {
            return this.f2693b.isVisibleToUser();
        }
        return false;
    }

    public boolean Q(int i2, Bundle bundle) {
        if (Build.VERSION.SDK_INT >= 16) {
            return this.f2693b.performAction(i2, bundle);
        }
        return false;
    }

    public void R() {
        this.f2693b.recycle();
    }

    public boolean S(a aVar) {
        if (Build.VERSION.SDK_INT >= 21) {
            return this.f2693b.removeAction((AccessibilityNodeInfo.AccessibilityAction) aVar.L);
        }
        return false;
    }

    public void U(boolean z) {
        if (Build.VERSION.SDK_INT >= 16) {
            this.f2693b.setAccessibilityFocused(z);
        }
    }

    @Deprecated
    public void W(Rect rect) {
        this.f2693b.setBoundsInParent(rect);
    }

    public void X(Rect rect) {
        this.f2693b.setBoundsInScreen(rect);
    }

    public void Y(boolean z) {
        if (Build.VERSION.SDK_INT >= 19) {
            this.f2693b.setCanOpenPopup(z);
        }
    }

    public void Z(boolean z) {
        this.f2693b.setCheckable(z);
    }

    public void a(int i2) {
        this.f2693b.addAction(i2);
    }

    public void a0(boolean z) {
        this.f2693b.setChecked(z);
    }

    public void b(a aVar) {
        if (Build.VERSION.SDK_INT >= 21) {
            this.f2693b.addAction((AccessibilityNodeInfo.AccessibilityAction) aVar.L);
        }
    }

    public void b0(CharSequence charSequence) {
        this.f2693b.setClassName(charSequence);
    }

    public void c(View view) {
        this.f2693b.addChild(view);
    }

    public void c0(boolean z) {
        this.f2693b.setClickable(z);
    }

    public void d(View view, int i2) {
        if (Build.VERSION.SDK_INT >= 16) {
            this.f2693b.addChild(view, i2);
        }
    }

    public void d0(Object obj) {
        if (Build.VERSION.SDK_INT >= 19) {
            this.f2693b.setCollectionInfo(obj == null ? null : (AccessibilityNodeInfo.CollectionInfo) ((b) obj).f2705a);
        }
    }

    public void e0(Object obj) {
        if (Build.VERSION.SDK_INT >= 19) {
            this.f2693b.setCollectionItemInfo(obj == null ? null : (AccessibilityNodeInfo.CollectionItemInfo) ((C0047c) obj).f2706a);
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
        AccessibilityNodeInfo accessibilityNodeInfo = this.f2693b;
        if (accessibilityNodeInfo == null) {
            if (cVar.f2693b != null) {
                return false;
            }
        } else if (!accessibilityNodeInfo.equals(cVar.f2693b)) {
            return false;
        }
        return this.f2695d == cVar.f2695d && this.f2694c == cVar.f2694c;
    }

    public void f(CharSequence charSequence, View view) {
        int i2 = Build.VERSION.SDK_INT;
        if (i2 < 19 || i2 >= 26) {
            return;
        }
        g();
        T(view);
        ClickableSpan[] clickableSpanArrQ = q(charSequence);
        if (clickableSpanArrQ == null || clickableSpanArrQ.length <= 0) {
            return;
        }
        s().putInt("androidx.view.accessibility.AccessibilityNodeInfoCompat.SPANS_ACTION_ID_KEY", b.g.b.accessibility_action_clickable_span);
        SparseArray<WeakReference<ClickableSpan>> sparseArrayT = t(view);
        for (int i3 = 0; i3 < clickableSpanArrQ.length; i3++) {
            int iZ = z(clickableSpanArrQ[i3], sparseArrayT);
            sparseArrayT.put(iZ, new WeakReference<>(clickableSpanArrQ[i3]));
            e(clickableSpanArrQ[i3], (Spanned) charSequence, iZ);
        }
    }

    public void f0(CharSequence charSequence) {
        this.f2693b.setContentDescription(charSequence);
    }

    public void g0(boolean z) {
        this.f2693b.setEnabled(z);
    }

    public void h0(CharSequence charSequence) {
        if (Build.VERSION.SDK_INT >= 21) {
            this.f2693b.setError(charSequence);
        }
    }

    public int hashCode() {
        AccessibilityNodeInfo accessibilityNodeInfo = this.f2693b;
        if (accessibilityNodeInfo == null) {
            return 0;
        }
        return accessibilityNodeInfo.hashCode();
    }

    public List<a> i() {
        List<AccessibilityNodeInfo.AccessibilityAction> actionList = Build.VERSION.SDK_INT >= 21 ? this.f2693b.getActionList() : null;
        if (actionList == null) {
            return Collections.emptyList();
        }
        ArrayList arrayList = new ArrayList();
        int size = actionList.size();
        for (int i2 = 0; i2 < size; i2++) {
            arrayList.add(new a(actionList.get(i2)));
        }
        return arrayList;
    }

    public void i0(boolean z) {
        this.f2693b.setFocusable(z);
    }

    public void j0(boolean z) {
        this.f2693b.setFocused(z);
    }

    public int k() {
        return this.f2693b.getActions();
    }

    public void k0(boolean z) {
        if (Build.VERSION.SDK_INT >= 28) {
            this.f2693b.setHeading(z);
        } else {
            V(2, z);
        }
    }

    public void l0(CharSequence charSequence) {
        int i2 = Build.VERSION.SDK_INT;
        if (i2 >= 26) {
            this.f2693b.setHintText(charSequence);
        } else if (i2 >= 19) {
            this.f2693b.getExtras().putCharSequence("androidx.view.accessibility.AccessibilityNodeInfoCompat.HINT_TEXT_KEY", charSequence);
        }
    }

    @Deprecated
    public void m(Rect rect) {
        this.f2693b.getBoundsInParent(rect);
    }

    public void m0(int i2) {
        if (Build.VERSION.SDK_INT >= 21) {
            this.f2693b.setMaxTextLength(i2);
        }
    }

    public void n(Rect rect) {
        this.f2693b.getBoundsInScreen(rect);
    }

    public void n0(CharSequence charSequence) {
        this.f2693b.setPackageName(charSequence);
    }

    public int o() {
        return this.f2693b.getChildCount();
    }

    public void o0(CharSequence charSequence) {
        int i2 = Build.VERSION.SDK_INT;
        if (i2 >= 28) {
            this.f2693b.setPaneTitle(charSequence);
        } else if (i2 >= 19) {
            this.f2693b.getExtras().putCharSequence("androidx.view.accessibility.AccessibilityNodeInfoCompat.PANE_TITLE_KEY", charSequence);
        }
    }

    public CharSequence p() {
        return this.f2693b.getClassName();
    }

    public void p0(View view) {
        this.f2694c = -1;
        this.f2693b.setParent(view);
    }

    public void q0(View view, int i2) {
        this.f2694c = i2;
        if (Build.VERSION.SDK_INT >= 16) {
            this.f2693b.setParent(view, i2);
        }
    }

    public CharSequence r() {
        return this.f2693b.getContentDescription();
    }

    public void r0(d dVar) {
        if (Build.VERSION.SDK_INT >= 19) {
            this.f2693b.setRangeInfo((AccessibilityNodeInfo.RangeInfo) dVar.f2707a);
        }
    }

    public Bundle s() {
        return Build.VERSION.SDK_INT >= 19 ? this.f2693b.getExtras() : new Bundle();
    }

    public void s0(CharSequence charSequence) {
        if (Build.VERSION.SDK_INT >= 19) {
            this.f2693b.getExtras().putCharSequence("AccessibilityNodeInfo.roleDescription", charSequence);
        }
    }

    public void t0(boolean z) {
        if (Build.VERSION.SDK_INT >= 28) {
            this.f2693b.setScreenReaderFocusable(z);
        } else {
            V(1, z);
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
            for (int i2 = 0; i2 < listI.size(); i2++) {
                a aVar = listI.get(i2);
                String strJ = j(aVar.b());
                if (strJ.equals("ACTION_UNKNOWN") && aVar.c() != null) {
                    strJ = aVar.c().toString();
                }
                sb.append(strJ);
                if (i2 != listI.size() - 1) {
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
        return this.f2693b.getPackageName();
    }

    public void u0(boolean z) {
        this.f2693b.setScrollable(z);
    }

    public void v0(boolean z) {
        this.f2693b.setSelected(z);
    }

    public CharSequence w() {
        if (!y()) {
            return this.f2693b.getText();
        }
        List<Integer> listH = h("androidx.view.accessibility.AccessibilityNodeInfoCompat.SPANS_START_KEY");
        List<Integer> listH2 = h("androidx.view.accessibility.AccessibilityNodeInfoCompat.SPANS_END_KEY");
        List<Integer> listH3 = h("androidx.view.accessibility.AccessibilityNodeInfoCompat.SPANS_FLAGS_KEY");
        List<Integer> listH4 = h("androidx.view.accessibility.AccessibilityNodeInfoCompat.SPANS_ID_KEY");
        SpannableString spannableString = new SpannableString(TextUtils.substring(this.f2693b.getText(), 0, this.f2693b.getText().length()));
        for (int i2 = 0; i2 < listH.size(); i2++) {
            spannableString.setSpan(new b.g.k.c0.a(listH4.get(i2).intValue(), this, s().getInt("androidx.view.accessibility.AccessibilityNodeInfoCompat.SPANS_ACTION_ID_KEY")), listH.get(i2).intValue(), listH2.get(i2).intValue(), listH3.get(i2).intValue());
        }
        return spannableString;
    }

    public void w0(boolean z) {
        if (Build.VERSION.SDK_INT >= 26) {
            this.f2693b.setShowingHintText(z);
        } else {
            V(4, z);
        }
    }

    public String x() {
        if (Build.VERSION.SDK_INT >= 18) {
            return this.f2693b.getViewIdResourceName();
        }
        return null;
    }

    public void x0(View view) {
        this.f2695d = -1;
        this.f2693b.setSource(view);
    }

    public void y0(View view, int i2) {
        this.f2695d = i2;
        if (Build.VERSION.SDK_INT >= 16) {
            this.f2693b.setSource(view, i2);
        }
    }

    public void z0(CharSequence charSequence) {
        this.f2693b.setText(charSequence);
    }
}
