package a.g.k.c0;

import a.g.k.c0.f;
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
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public class c {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private static int f394a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private final AccessibilityNodeInfo f395b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public int f396c = -1;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private int f397d = -1;

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
        public static final a f398a = new a(1, null);

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public static final a f399b = new a(2, null);

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public static final a f400c = new a(4, null);

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public static final a f401d = new a(8, null);
        public static final a e = new a(16, null);
        public static final a f = new a(32, null);
        public static final a g = new a(64, null);
        public static final a h = new a(128, null);
        public static final a i = new a(256, null, f.b.class);
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
            H = new a(i2 >= 24 ? AccessibilityNodeInfo.AccessibilityAction.ACTION_SET_PROGRESS : null, R.id.accessibilityActionSetProgress, null, null, f.C0015f.class);
            I = new a(i2 >= 26 ? AccessibilityNodeInfo.AccessibilityAction.ACTION_MOVE_WINDOW : null, R.id.accessibilityActionMoveWindow, null, null, f.d.class);
            J = new a(i2 >= 28 ? AccessibilityNodeInfo.AccessibilityAction.ACTION_SHOW_TOOLTIP : null, R.id.accessibilityActionShowTooltip, null, null, null);
            K = new a(i2 >= 28 ? AccessibilityNodeInfo.AccessibilityAction.ACTION_HIDE_TOOLTIP : null, R.id.accessibilityActionHideTooltip, null, null, null);
        }

        public a(int i2, CharSequence charSequence) {
            this(null, i2, charSequence, null, null);
        }

        private a(int i2, CharSequence charSequence, Class<? extends f.a> cls) {
            this(null, i2, charSequence, null, cls);
        }

        a(Object obj) {
            this(obj, 0, null, null, null);
        }

        a(Object obj, int i2, CharSequence charSequence, f fVar, Class<? extends f.a> cls) {
            this.M = i2;
            this.O = fVar;
            if (Build.VERSION.SDK_INT >= 21 && obj == null) {
                obj = new AccessibilityNodeInfo.AccessibilityAction(i2, charSequence);
            }
            this.L = obj;
            this.N = cls;
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
                } catch (Exception e2) {
                    e = e2;
                }
                try {
                    aVarNewInstance.a(bundle);
                    aVar = aVarNewInstance;
                } catch (Exception e3) {
                    e = e3;
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
            Object obj2 = this.L;
            Object obj3 = ((a) obj).L;
            return obj2 == null ? obj3 == null : obj2.equals(obj3);
        }

        public int hashCode() {
            Object obj = this.L;
            if (obj != null) {
                return obj.hashCode();
            }
            return 0;
        }
    }

    public static class b {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final Object f402a;

        b(Object obj) {
            this.f402a = obj;
        }

        public static b a(int i, int i2, boolean z, int i3) {
            int i4 = Build.VERSION.SDK_INT;
            return i4 >= 21 ? new b(AccessibilityNodeInfo.CollectionInfo.obtain(i, i2, z, i3)) : i4 >= 19 ? new b(AccessibilityNodeInfo.CollectionInfo.obtain(i, i2, z)) : new b(null);
        }
    }

    /* JADX INFO: renamed from: a.g.k.c0.c$c, reason: collision with other inner class name */
    public static class C0014c {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final Object f403a;

        C0014c(Object obj) {
            this.f403a = obj;
        }

        public static C0014c a(int i, int i2, int i3, int i4, boolean z, boolean z2) {
            int i5 = Build.VERSION.SDK_INT;
            return i5 >= 21 ? new C0014c(AccessibilityNodeInfo.CollectionItemInfo.obtain(i, i2, i3, i4, z, z2)) : i5 >= 19 ? new C0014c(AccessibilityNodeInfo.CollectionItemInfo.obtain(i, i2, i3, i4, z)) : new C0014c(null);
        }
    }

    private c(AccessibilityNodeInfo accessibilityNodeInfo) {
        this.f395b = accessibilityNodeInfo;
    }

    public static c K() {
        return v0(AccessibilityNodeInfo.obtain());
    }

    public static c L(View view) {
        return v0(AccessibilityNodeInfo.obtain(view));
    }

    public static c M(c cVar) {
        return v0(AccessibilityNodeInfo.obtain(cVar.f395b));
    }

    private void Q(View view) {
        SparseArray<WeakReference<ClickableSpan>> sparseArrayU = u(view);
        if (sparseArrayU != null) {
            ArrayList arrayList = new ArrayList();
            for (int i = 0; i < sparseArrayU.size(); i++) {
                if (sparseArrayU.valueAt(i).get() == null) {
                    arrayList.add(Integer.valueOf(i));
                }
            }
            for (int i2 = 0; i2 < arrayList.size(); i2++) {
                sparseArrayU.remove(((Integer) arrayList.get(i2)).intValue());
            }
        }
    }

    private void S(int i, boolean z) {
        Bundle bundleR = r();
        if (bundleR != null) {
            int i2 = bundleR.getInt("androidx.view.accessibility.AccessibilityNodeInfoCompat.BOOLEAN_PROPERTY_KEY", 0) & (~i);
            if (!z) {
                i = 0;
            }
            bundleR.putInt("androidx.view.accessibility.AccessibilityNodeInfoCompat.BOOLEAN_PROPERTY_KEY", i | i2);
        }
    }

    private void d(ClickableSpan clickableSpan, Spanned spanned, int i) {
        g("androidx.view.accessibility.AccessibilityNodeInfoCompat.SPANS_START_KEY").add(Integer.valueOf(spanned.getSpanStart(clickableSpan)));
        g("androidx.view.accessibility.AccessibilityNodeInfoCompat.SPANS_END_KEY").add(Integer.valueOf(spanned.getSpanEnd(clickableSpan)));
        g("androidx.view.accessibility.AccessibilityNodeInfoCompat.SPANS_FLAGS_KEY").add(Integer.valueOf(spanned.getSpanFlags(clickableSpan)));
        g("androidx.view.accessibility.AccessibilityNodeInfoCompat.SPANS_ID_KEY").add(Integer.valueOf(i));
    }

    private void f() {
        if (Build.VERSION.SDK_INT >= 19) {
            this.f395b.getExtras().remove("androidx.view.accessibility.AccessibilityNodeInfoCompat.SPANS_START_KEY");
            this.f395b.getExtras().remove("androidx.view.accessibility.AccessibilityNodeInfoCompat.SPANS_END_KEY");
            this.f395b.getExtras().remove("androidx.view.accessibility.AccessibilityNodeInfoCompat.SPANS_FLAGS_KEY");
            this.f395b.getExtras().remove("androidx.view.accessibility.AccessibilityNodeInfoCompat.SPANS_ID_KEY");
        }
    }

    private List<Integer> g(String str) {
        if (Build.VERSION.SDK_INT < 19) {
            return new ArrayList();
        }
        ArrayList<Integer> integerArrayList = this.f395b.getExtras().getIntegerArrayList(str);
        if (integerArrayList != null) {
            return integerArrayList;
        }
        ArrayList<Integer> arrayList = new ArrayList<>();
        this.f395b.getExtras().putIntegerArrayList(str, arrayList);
        return arrayList;
    }

    private static String i(int i) {
        if (i == 1) {
            return "ACTION_FOCUS";
        }
        if (i == 2) {
            return "ACTION_CLEAR_FOCUS";
        }
        switch (i) {
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
                switch (i) {
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
                        switch (i) {
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

    private boolean k(int i) {
        Bundle bundleR = r();
        return bundleR != null && (bundleR.getInt("androidx.view.accessibility.AccessibilityNodeInfoCompat.BOOLEAN_PROPERTY_KEY", 0) & i) == i;
    }

    public static ClickableSpan[] p(CharSequence charSequence) {
        if (charSequence instanceof Spanned) {
            return (ClickableSpan[]) ((Spanned) charSequence).getSpans(0, charSequence.length(), ClickableSpan.class);
        }
        return null;
    }

    private SparseArray<WeakReference<ClickableSpan>> s(View view) {
        SparseArray<WeakReference<ClickableSpan>> sparseArrayU = u(view);
        if (sparseArrayU != null) {
            return sparseArrayU;
        }
        SparseArray<WeakReference<ClickableSpan>> sparseArray = new SparseArray<>();
        view.setTag(a.g.b.I, sparseArray);
        return sparseArray;
    }

    private SparseArray<WeakReference<ClickableSpan>> u(View view) {
        return (SparseArray) view.getTag(a.g.b.I);
    }

    public static c v0(AccessibilityNodeInfo accessibilityNodeInfo) {
        return new c(accessibilityNodeInfo);
    }

    private boolean x() {
        return !g("androidx.view.accessibility.AccessibilityNodeInfoCompat.SPANS_START_KEY").isEmpty();
    }

    private int y(ClickableSpan clickableSpan, SparseArray<WeakReference<ClickableSpan>> sparseArray) {
        if (sparseArray != null) {
            for (int i = 0; i < sparseArray.size(); i++) {
                if (clickableSpan.equals(sparseArray.valueAt(i).get())) {
                    return sparseArray.keyAt(i);
                }
            }
        }
        int i2 = f394a;
        f394a = i2 + 1;
        return i2;
    }

    public boolean A() {
        return this.f395b.isChecked();
    }

    public boolean B() {
        return this.f395b.isClickable();
    }

    public boolean C() {
        return this.f395b.isEnabled();
    }

    public boolean D() {
        return this.f395b.isFocusable();
    }

    public boolean E() {
        return this.f395b.isFocused();
    }

    public boolean F() {
        return this.f395b.isLongClickable();
    }

    public boolean G() {
        return this.f395b.isPassword();
    }

    public boolean H() {
        return this.f395b.isScrollable();
    }

    public boolean I() {
        return this.f395b.isSelected();
    }

    public boolean J() {
        return Build.VERSION.SDK_INT >= 26 ? this.f395b.isShowingHintText() : k(4);
    }

    public boolean N(int i, Bundle bundle) {
        if (Build.VERSION.SDK_INT >= 16) {
            return this.f395b.performAction(i, bundle);
        }
        return false;
    }

    public void O() {
        this.f395b.recycle();
    }

    public boolean P(a aVar) {
        if (Build.VERSION.SDK_INT >= 21) {
            return this.f395b.removeAction((AccessibilityNodeInfo.AccessibilityAction) aVar.L);
        }
        return false;
    }

    public void R(boolean z) {
        if (Build.VERSION.SDK_INT >= 16) {
            this.f395b.setAccessibilityFocused(z);
        }
    }

    @Deprecated
    public void T(Rect rect) {
        this.f395b.setBoundsInParent(rect);
    }

    public void U(Rect rect) {
        this.f395b.setBoundsInScreen(rect);
    }

    public void V(boolean z) {
        this.f395b.setCheckable(z);
    }

    public void W(boolean z) {
        this.f395b.setChecked(z);
    }

    public void X(CharSequence charSequence) {
        this.f395b.setClassName(charSequence);
    }

    public void Y(boolean z) {
        this.f395b.setClickable(z);
    }

    public void Z(Object obj) {
        if (Build.VERSION.SDK_INT >= 19) {
            this.f395b.setCollectionInfo(obj == null ? null : (AccessibilityNodeInfo.CollectionInfo) ((b) obj).f402a);
        }
    }

    public void a(int i) {
        this.f395b.addAction(i);
    }

    public void a0(Object obj) {
        if (Build.VERSION.SDK_INT >= 19) {
            this.f395b.setCollectionItemInfo(obj == null ? null : (AccessibilityNodeInfo.CollectionItemInfo) ((C0014c) obj).f403a);
        }
    }

    public void b(a aVar) {
        if (Build.VERSION.SDK_INT >= 21) {
            this.f395b.addAction((AccessibilityNodeInfo.AccessibilityAction) aVar.L);
        }
    }

    public void b0(CharSequence charSequence) {
        this.f395b.setContentDescription(charSequence);
    }

    public void c(View view, int i) {
        if (Build.VERSION.SDK_INT >= 16) {
            this.f395b.addChild(view, i);
        }
    }

    public void c0(boolean z) {
        this.f395b.setEnabled(z);
    }

    public void d0(CharSequence charSequence) {
        if (Build.VERSION.SDK_INT >= 21) {
            this.f395b.setError(charSequence);
        }
    }

    public void e(CharSequence charSequence, View view) {
        int i = Build.VERSION.SDK_INT;
        if (i < 19 || i >= 26) {
            return;
        }
        f();
        Q(view);
        ClickableSpan[] clickableSpanArrP = p(charSequence);
        if (clickableSpanArrP == null || clickableSpanArrP.length <= 0) {
            return;
        }
        r().putInt("androidx.view.accessibility.AccessibilityNodeInfoCompat.SPANS_ACTION_ID_KEY", a.g.b.f262a);
        SparseArray<WeakReference<ClickableSpan>> sparseArrayS = s(view);
        for (int i2 = 0; i2 < clickableSpanArrP.length; i2++) {
            int iY = y(clickableSpanArrP[i2], sparseArrayS);
            sparseArrayS.put(iY, new WeakReference<>(clickableSpanArrP[i2]));
            d(clickableSpanArrP[i2], (Spanned) charSequence, iY);
        }
    }

    public void e0(boolean z) {
        this.f395b.setFocusable(z);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof c)) {
            return false;
        }
        c cVar = (c) obj;
        AccessibilityNodeInfo accessibilityNodeInfo = this.f395b;
        if (accessibilityNodeInfo == null) {
            if (cVar.f395b != null) {
                return false;
            }
        } else if (!accessibilityNodeInfo.equals(cVar.f395b)) {
            return false;
        }
        return this.f397d == cVar.f397d && this.f396c == cVar.f396c;
    }

    public void f0(boolean z) {
        this.f395b.setFocused(z);
    }

    public void g0(boolean z) {
        if (Build.VERSION.SDK_INT >= 28) {
            this.f395b.setHeading(z);
        } else {
            S(2, z);
        }
    }

    public List<a> h() {
        List<AccessibilityNodeInfo.AccessibilityAction> actionList = Build.VERSION.SDK_INT >= 21 ? this.f395b.getActionList() : null;
        if (actionList == null) {
            return Collections.emptyList();
        }
        ArrayList arrayList = new ArrayList();
        int size = actionList.size();
        for (int i = 0; i < size; i++) {
            arrayList.add(new a(actionList.get(i)));
        }
        return arrayList;
    }

    public void h0(CharSequence charSequence) {
        int i = Build.VERSION.SDK_INT;
        if (i >= 26) {
            this.f395b.setHintText(charSequence);
        } else if (i >= 19) {
            this.f395b.getExtras().putCharSequence("androidx.view.accessibility.AccessibilityNodeInfoCompat.HINT_TEXT_KEY", charSequence);
        }
    }

    public int hashCode() {
        AccessibilityNodeInfo accessibilityNodeInfo = this.f395b;
        if (accessibilityNodeInfo == null) {
            return 0;
        }
        return accessibilityNodeInfo.hashCode();
    }

    public void i0(int i) {
        if (Build.VERSION.SDK_INT >= 21) {
            this.f395b.setMaxTextLength(i);
        }
    }

    public int j() {
        return this.f395b.getActions();
    }

    public void j0(CharSequence charSequence) {
        this.f395b.setPackageName(charSequence);
    }

    public void k0(CharSequence charSequence) {
        int i = Build.VERSION.SDK_INT;
        if (i >= 28) {
            this.f395b.setPaneTitle(charSequence);
        } else if (i >= 19) {
            this.f395b.getExtras().putCharSequence("androidx.view.accessibility.AccessibilityNodeInfoCompat.PANE_TITLE_KEY", charSequence);
        }
    }

    @Deprecated
    public void l(Rect rect) {
        this.f395b.getBoundsInParent(rect);
    }

    public void l0(View view) {
        this.f396c = -1;
        this.f395b.setParent(view);
    }

    public void m(Rect rect) {
        this.f395b.getBoundsInScreen(rect);
    }

    public void m0(View view, int i) {
        this.f396c = i;
        if (Build.VERSION.SDK_INT >= 16) {
            this.f395b.setParent(view, i);
        }
    }

    public int n() {
        return this.f395b.getChildCount();
    }

    public void n0(CharSequence charSequence) {
        if (Build.VERSION.SDK_INT >= 19) {
            this.f395b.getExtras().putCharSequence("AccessibilityNodeInfo.roleDescription", charSequence);
        }
    }

    public CharSequence o() {
        return this.f395b.getClassName();
    }

    public void o0(boolean z) {
        if (Build.VERSION.SDK_INT >= 28) {
            this.f395b.setScreenReaderFocusable(z);
        } else {
            S(1, z);
        }
    }

    public void p0(boolean z) {
        this.f395b.setScrollable(z);
    }

    public CharSequence q() {
        return this.f395b.getContentDescription();
    }

    public void q0(boolean z) {
        if (Build.VERSION.SDK_INT >= 26) {
            this.f395b.setShowingHintText(z);
        } else {
            S(4, z);
        }
    }

    public Bundle r() {
        return Build.VERSION.SDK_INT >= 19 ? this.f395b.getExtras() : new Bundle();
    }

    public void r0(View view, int i) {
        this.f397d = i;
        if (Build.VERSION.SDK_INT >= 16) {
            this.f395b.setSource(view, i);
        }
    }

    public void s0(CharSequence charSequence) {
        this.f395b.setText(charSequence);
    }

    public CharSequence t() {
        return this.f395b.getPackageName();
    }

    public void t0(boolean z) {
        if (Build.VERSION.SDK_INT >= 16) {
            this.f395b.setVisibleToUser(z);
        }
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(super.toString());
        Rect rect = new Rect();
        l(rect);
        sb.append("; boundsInParent: " + rect);
        m(rect);
        sb.append("; boundsInScreen: " + rect);
        sb.append("; packageName: ");
        sb.append(t());
        sb.append("; className: ");
        sb.append(o());
        sb.append("; text: ");
        sb.append(v());
        sb.append("; contentDescription: ");
        sb.append(q());
        sb.append("; viewId: ");
        sb.append(w());
        sb.append("; checkable: ");
        sb.append(z());
        sb.append("; checked: ");
        sb.append(A());
        sb.append("; focusable: ");
        sb.append(D());
        sb.append("; focused: ");
        sb.append(E());
        sb.append("; selected: ");
        sb.append(I());
        sb.append("; clickable: ");
        sb.append(B());
        sb.append("; longClickable: ");
        sb.append(F());
        sb.append("; enabled: ");
        sb.append(C());
        sb.append("; password: ");
        sb.append(G());
        sb.append("; scrollable: " + H());
        sb.append("; [");
        if (Build.VERSION.SDK_INT >= 21) {
            List<a> listH = h();
            for (int i = 0; i < listH.size(); i++) {
                a aVar = listH.get(i);
                String strI = i(aVar.b());
                if (strI.equals("ACTION_UNKNOWN") && aVar.c() != null) {
                    strI = aVar.c().toString();
                }
                sb.append(strI);
                if (i != listH.size() - 1) {
                    sb.append(", ");
                }
            }
        } else {
            int iJ = j();
            while (iJ != 0) {
                int iNumberOfTrailingZeros = 1 << Integer.numberOfTrailingZeros(iJ);
                iJ &= ~iNumberOfTrailingZeros;
                sb.append(i(iNumberOfTrailingZeros));
                if (iJ != 0) {
                    sb.append(", ");
                }
            }
        }
        sb.append("]");
        return sb.toString();
    }

    public AccessibilityNodeInfo u0() {
        return this.f395b;
    }

    public CharSequence v() {
        if (!x()) {
            return this.f395b.getText();
        }
        List<Integer> listG = g("androidx.view.accessibility.AccessibilityNodeInfoCompat.SPANS_START_KEY");
        List<Integer> listG2 = g("androidx.view.accessibility.AccessibilityNodeInfoCompat.SPANS_END_KEY");
        List<Integer> listG3 = g("androidx.view.accessibility.AccessibilityNodeInfoCompat.SPANS_FLAGS_KEY");
        List<Integer> listG4 = g("androidx.view.accessibility.AccessibilityNodeInfoCompat.SPANS_ID_KEY");
        SpannableString spannableString = new SpannableString(TextUtils.substring(this.f395b.getText(), 0, this.f395b.getText().length()));
        for (int i = 0; i < listG.size(); i++) {
            spannableString.setSpan(new a.g.k.c0.a(listG4.get(i).intValue(), this, r().getInt("androidx.view.accessibility.AccessibilityNodeInfoCompat.SPANS_ACTION_ID_KEY")), listG.get(i).intValue(), listG2.get(i).intValue(), listG3.get(i).intValue());
        }
        return spannableString;
    }

    public String w() {
        if (Build.VERSION.SDK_INT >= 18) {
            return this.f395b.getViewIdResourceName();
        }
        return null;
    }

    public boolean z() {
        return this.f395b.isCheckable();
    }
}
