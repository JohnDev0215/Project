package b.f.m.b0;

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
import b.f.m.b0.f;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/* JADX INFO: compiled from: AccessibilityNodeInfoCompat.java */
/* JADX INFO: loaded from: classes.dex */
public class c {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static int f1757a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final AccessibilityNodeInfo f1758b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public int f1759c = -1;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public int f1760d = -1;

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
        public static final a f1761a = new a(1, null);

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public static final a f1762b = new a(2, null);

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public static final a f1763c = new a(4, null);

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public static final a f1764d = new a(8, null);

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        public static final a f1765e = new a(16, null);

        /* JADX INFO: renamed from: f, reason: collision with root package name */
        public static final a f1766f = new a(32, null);

        /* JADX INFO: renamed from: g, reason: collision with root package name */
        public static final a f1767g = new a(64, null);

        /* JADX INFO: renamed from: h, reason: collision with root package name */
        public static final a f1768h = new a(128, null);

        /* JADX INFO: renamed from: i, reason: collision with root package name */
        public static final a f1769i = new a(256, null, f.b.class);
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
        public final Object L;
        public final int M;
        public final Class<? extends f.a> N;
        public final f O;

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
            H = new a(i2 >= 24 ? AccessibilityNodeInfo.AccessibilityAction.ACTION_SET_PROGRESS : null, R.id.accessibilityActionSetProgress, null, null, f.C0031f.class);
            I = new a(i2 >= 26 ? AccessibilityNodeInfo.AccessibilityAction.ACTION_MOVE_WINDOW : null, R.id.accessibilityActionMoveWindow, null, null, f.d.class);
            J = new a(i2 >= 28 ? AccessibilityNodeInfo.AccessibilityAction.ACTION_SHOW_TOOLTIP : null, R.id.accessibilityActionShowTooltip, null, null, null);
            K = new a(i2 >= 28 ? AccessibilityNodeInfo.AccessibilityAction.ACTION_HIDE_TOOLTIP : null, R.id.accessibilityActionHideTooltip, null, null, null);
        }

        public a(int i2, CharSequence charSequence) {
            this(null, i2, charSequence, null, null);
        }

        public int a() {
            if (Build.VERSION.SDK_INT >= 21) {
                return ((AccessibilityNodeInfo.AccessibilityAction) this.L).getId();
            }
            return 0;
        }

        public CharSequence b() {
            if (Build.VERSION.SDK_INT >= 21) {
                return ((AccessibilityNodeInfo.AccessibilityAction) this.L).getLabel();
            }
            return null;
        }

        public boolean c(View view, Bundle bundle) {
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

        public a(int i2, CharSequence charSequence, Class<? extends f.a> cls) {
            this(null, i2, charSequence, null, cls);
        }

        public a(Object obj, int i2, CharSequence charSequence, f fVar, Class<? extends f.a> cls) {
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
        public final Object f1770a;

        public b(Object obj) {
            this.f1770a = obj;
        }

        public static b a(int i2, int i3, boolean z, int i4) {
            int i5 = Build.VERSION.SDK_INT;
            return i5 >= 21 ? new b(AccessibilityNodeInfo.CollectionInfo.obtain(i2, i3, z, i4)) : i5 >= 19 ? new b(AccessibilityNodeInfo.CollectionInfo.obtain(i2, i3, z)) : new b(null);
        }
    }

    /* JADX INFO: renamed from: b.f.m.b0.c$c, reason: collision with other inner class name */
    /* JADX INFO: compiled from: AccessibilityNodeInfoCompat.java */
    public static class C0030c {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final Object f1771a;

        public C0030c(Object obj) {
            this.f1771a = obj;
        }

        public static C0030c a(int i2, int i3, int i4, int i5, boolean z, boolean z2) {
            int i6 = Build.VERSION.SDK_INT;
            return i6 >= 21 ? new C0030c(AccessibilityNodeInfo.CollectionItemInfo.obtain(i2, i3, i4, i5, z, z2)) : i6 >= 19 ? new C0030c(AccessibilityNodeInfo.CollectionItemInfo.obtain(i2, i3, i4, i5, z)) : new C0030c(null);
        }
    }

    public c(AccessibilityNodeInfo accessibilityNodeInfo) {
        this.f1758b = accessibilityNodeInfo;
    }

    public static c J(c cVar) {
        return m0(AccessibilityNodeInfo.obtain(cVar.f1758b));
    }

    public static String i(int i2) {
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

    public static c m0(AccessibilityNodeInfo accessibilityNodeInfo) {
        return new c(accessibilityNodeInfo);
    }

    public static ClickableSpan[] n(CharSequence charSequence) {
        if (charSequence instanceof Spanned) {
            return (ClickableSpan[]) ((Spanned) charSequence).getSpans(0, charSequence.length(), ClickableSpan.class);
        }
        return null;
    }

    public boolean A() {
        return this.f1758b.isClickable();
    }

    public boolean B() {
        return this.f1758b.isEnabled();
    }

    public boolean C() {
        return this.f1758b.isFocusable();
    }

    public boolean D() {
        return this.f1758b.isFocused();
    }

    public boolean E() {
        return this.f1758b.isLongClickable();
    }

    public boolean F() {
        return this.f1758b.isPassword();
    }

    public boolean G() {
        return this.f1758b.isScrollable();
    }

    public boolean H() {
        return this.f1758b.isSelected();
    }

    public boolean I() {
        if (Build.VERSION.SDK_INT >= 16) {
            return this.f1758b.isVisibleToUser();
        }
        return false;
    }

    public boolean K(int i2, Bundle bundle) {
        if (Build.VERSION.SDK_INT >= 16) {
            return this.f1758b.performAction(i2, bundle);
        }
        return false;
    }

    public void L() {
        this.f1758b.recycle();
    }

    public boolean M(a aVar) {
        if (Build.VERSION.SDK_INT >= 21) {
            return this.f1758b.removeAction((AccessibilityNodeInfo.AccessibilityAction) aVar.L);
        }
        return false;
    }

    public final void N(View view) {
        SparseArray<WeakReference<ClickableSpan>> sparseArrayS = s(view);
        if (sparseArrayS != null) {
            ArrayList arrayList = new ArrayList();
            for (int i2 = 0; i2 < sparseArrayS.size(); i2++) {
                if (sparseArrayS.valueAt(i2).get() == null) {
                    arrayList.add(Integer.valueOf(i2));
                }
            }
            for (int i3 = 0; i3 < arrayList.size(); i3++) {
                sparseArrayS.remove(((Integer) arrayList.get(i3)).intValue());
            }
        }
    }

    public void O(boolean z) {
        if (Build.VERSION.SDK_INT >= 16) {
            this.f1758b.setAccessibilityFocused(z);
        }
    }

    public final void P(int i2, boolean z) {
        Bundle bundleP = p();
        if (bundleP != null) {
            int i3 = bundleP.getInt("androidx.view.accessibility.AccessibilityNodeInfoCompat.BOOLEAN_PROPERTY_KEY", 0) & (~i2);
            if (!z) {
                i2 = 0;
            }
            bundleP.putInt("androidx.view.accessibility.AccessibilityNodeInfoCompat.BOOLEAN_PROPERTY_KEY", i2 | i3);
        }
    }

    @Deprecated
    public void Q(Rect rect) {
        this.f1758b.setBoundsInParent(rect);
    }

    public void R(Rect rect) {
        this.f1758b.setBoundsInScreen(rect);
    }

    public void S(boolean z) {
        if (Build.VERSION.SDK_INT >= 19) {
            this.f1758b.setCanOpenPopup(z);
        }
    }

    public void T(CharSequence charSequence) {
        this.f1758b.setClassName(charSequence);
    }

    public void U(boolean z) {
        this.f1758b.setClickable(z);
    }

    public void V(Object obj) {
        if (Build.VERSION.SDK_INT >= 19) {
            this.f1758b.setCollectionInfo(obj == null ? null : (AccessibilityNodeInfo.CollectionInfo) ((b) obj).f1770a);
        }
    }

    public void W(Object obj) {
        if (Build.VERSION.SDK_INT >= 19) {
            this.f1758b.setCollectionItemInfo(obj == null ? null : (AccessibilityNodeInfo.CollectionItemInfo) ((C0030c) obj).f1771a);
        }
    }

    public void X(CharSequence charSequence) {
        this.f1758b.setContentDescription(charSequence);
    }

    public void Y(boolean z) {
        this.f1758b.setEnabled(z);
    }

    public void Z(boolean z) {
        this.f1758b.setFocusable(z);
    }

    public void a(int i2) {
        this.f1758b.addAction(i2);
    }

    public void a0(boolean z) {
        this.f1758b.setFocused(z);
    }

    public void b(a aVar) {
        if (Build.VERSION.SDK_INT >= 21) {
            this.f1758b.addAction((AccessibilityNodeInfo.AccessibilityAction) aVar.L);
        }
    }

    public void b0(boolean z) {
        if (Build.VERSION.SDK_INT >= 28) {
            this.f1758b.setHeading(z);
        } else {
            P(2, z);
        }
    }

    public void c(View view) {
        this.f1758b.addChild(view);
    }

    public void c0(boolean z) {
        this.f1758b.setLongClickable(z);
    }

    public final void d(ClickableSpan clickableSpan, Spanned spanned, int i2) {
        g("androidx.view.accessibility.AccessibilityNodeInfoCompat.SPANS_START_KEY").add(Integer.valueOf(spanned.getSpanStart(clickableSpan)));
        g("androidx.view.accessibility.AccessibilityNodeInfoCompat.SPANS_END_KEY").add(Integer.valueOf(spanned.getSpanEnd(clickableSpan)));
        g("androidx.view.accessibility.AccessibilityNodeInfoCompat.SPANS_FLAGS_KEY").add(Integer.valueOf(spanned.getSpanFlags(clickableSpan)));
        g("androidx.view.accessibility.AccessibilityNodeInfoCompat.SPANS_ID_KEY").add(Integer.valueOf(i2));
    }

    public void d0(CharSequence charSequence) {
        this.f1758b.setPackageName(charSequence);
    }

    public void e(CharSequence charSequence, View view) {
        int i2 = Build.VERSION.SDK_INT;
        if (i2 < 19 || i2 >= 26) {
            return;
        }
        f();
        N(view);
        ClickableSpan[] clickableSpanArrN = n(charSequence);
        if (clickableSpanArrN == null || clickableSpanArrN.length <= 0) {
            return;
        }
        p().putInt("androidx.view.accessibility.AccessibilityNodeInfoCompat.SPANS_ACTION_ID_KEY", b.f.c.f1541a);
        SparseArray<WeakReference<ClickableSpan>> sparseArrayQ = q(view);
        for (int i3 = 0; i3 < clickableSpanArrN.length; i3++) {
            int iW = w(clickableSpanArrN[i3], sparseArrayQ);
            sparseArrayQ.put(iW, new WeakReference<>(clickableSpanArrN[i3]));
            d(clickableSpanArrN[i3], (Spanned) charSequence, iW);
        }
    }

    public void e0(CharSequence charSequence) {
        int i2 = Build.VERSION.SDK_INT;
        if (i2 >= 28) {
            this.f1758b.setPaneTitle(charSequence);
        } else if (i2 >= 19) {
            this.f1758b.getExtras().putCharSequence("androidx.view.accessibility.AccessibilityNodeInfoCompat.PANE_TITLE_KEY", charSequence);
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
        AccessibilityNodeInfo accessibilityNodeInfo = this.f1758b;
        if (accessibilityNodeInfo == null) {
            if (cVar.f1758b != null) {
                return false;
            }
        } else if (!accessibilityNodeInfo.equals(cVar.f1758b)) {
            return false;
        }
        return this.f1760d == cVar.f1760d && this.f1759c == cVar.f1759c;
    }

    public final void f() {
        if (Build.VERSION.SDK_INT >= 19) {
            this.f1758b.getExtras().remove("androidx.view.accessibility.AccessibilityNodeInfoCompat.SPANS_START_KEY");
            this.f1758b.getExtras().remove("androidx.view.accessibility.AccessibilityNodeInfoCompat.SPANS_END_KEY");
            this.f1758b.getExtras().remove("androidx.view.accessibility.AccessibilityNodeInfoCompat.SPANS_FLAGS_KEY");
            this.f1758b.getExtras().remove("androidx.view.accessibility.AccessibilityNodeInfoCompat.SPANS_ID_KEY");
        }
    }

    public void f0(View view) {
        this.f1759c = -1;
        this.f1758b.setParent(view);
    }

    public final List<Integer> g(String str) {
        if (Build.VERSION.SDK_INT < 19) {
            return new ArrayList();
        }
        ArrayList<Integer> integerArrayList = this.f1758b.getExtras().getIntegerArrayList(str);
        if (integerArrayList != null) {
            return integerArrayList;
        }
        ArrayList<Integer> arrayList = new ArrayList<>();
        this.f1758b.getExtras().putIntegerArrayList(str, arrayList);
        return arrayList;
    }

    public void g0(boolean z) {
        if (Build.VERSION.SDK_INT >= 28) {
            this.f1758b.setScreenReaderFocusable(z);
        } else {
            P(1, z);
        }
    }

    public List<a> h() {
        List<AccessibilityNodeInfo.AccessibilityAction> actionList = Build.VERSION.SDK_INT >= 21 ? this.f1758b.getActionList() : null;
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

    public void h0(boolean z) {
        this.f1758b.setScrollable(z);
    }

    public int hashCode() {
        AccessibilityNodeInfo accessibilityNodeInfo = this.f1758b;
        if (accessibilityNodeInfo == null) {
            return 0;
        }
        return accessibilityNodeInfo.hashCode();
    }

    public void i0(boolean z) {
        this.f1758b.setSelected(z);
    }

    public int j() {
        return this.f1758b.getActions();
    }

    public void j0(View view) {
        this.f1760d = -1;
        this.f1758b.setSource(view);
    }

    @Deprecated
    public void k(Rect rect) {
        this.f1758b.getBoundsInParent(rect);
    }

    public void k0(boolean z) {
        if (Build.VERSION.SDK_INT >= 16) {
            this.f1758b.setVisibleToUser(z);
        }
    }

    public void l(Rect rect) {
        this.f1758b.getBoundsInScreen(rect);
    }

    public AccessibilityNodeInfo l0() {
        return this.f1758b;
    }

    public CharSequence m() {
        return this.f1758b.getClassName();
    }

    public CharSequence o() {
        return this.f1758b.getContentDescription();
    }

    public Bundle p() {
        return Build.VERSION.SDK_INT >= 19 ? this.f1758b.getExtras() : new Bundle();
    }

    public final SparseArray<WeakReference<ClickableSpan>> q(View view) {
        SparseArray<WeakReference<ClickableSpan>> sparseArrayS = s(view);
        if (sparseArrayS != null) {
            return sparseArrayS;
        }
        SparseArray<WeakReference<ClickableSpan>> sparseArray = new SparseArray<>();
        view.setTag(b.f.c.I, sparseArray);
        return sparseArray;
    }

    public CharSequence r() {
        return this.f1758b.getPackageName();
    }

    public final SparseArray<WeakReference<ClickableSpan>> s(View view) {
        return (SparseArray) view.getTag(b.f.c.I);
    }

    public CharSequence t() {
        if (!v()) {
            return this.f1758b.getText();
        }
        List<Integer> listG = g("androidx.view.accessibility.AccessibilityNodeInfoCompat.SPANS_START_KEY");
        List<Integer> listG2 = g("androidx.view.accessibility.AccessibilityNodeInfoCompat.SPANS_END_KEY");
        List<Integer> listG3 = g("androidx.view.accessibility.AccessibilityNodeInfoCompat.SPANS_FLAGS_KEY");
        List<Integer> listG4 = g("androidx.view.accessibility.AccessibilityNodeInfoCompat.SPANS_ID_KEY");
        SpannableString spannableString = new SpannableString(TextUtils.substring(this.f1758b.getText(), 0, this.f1758b.getText().length()));
        for (int i2 = 0; i2 < listG.size(); i2++) {
            spannableString.setSpan(new b.f.m.b0.a(listG4.get(i2).intValue(), this, p().getInt("androidx.view.accessibility.AccessibilityNodeInfoCompat.SPANS_ACTION_ID_KEY")), listG.get(i2).intValue(), listG2.get(i2).intValue(), listG3.get(i2).intValue());
        }
        return spannableString;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(super.toString());
        Rect rect = new Rect();
        k(rect);
        sb.append("; boundsInParent: " + rect);
        l(rect);
        sb.append("; boundsInScreen: " + rect);
        sb.append("; packageName: ");
        sb.append(r());
        sb.append("; className: ");
        sb.append(m());
        sb.append("; text: ");
        sb.append(t());
        sb.append("; contentDescription: ");
        sb.append(o());
        sb.append("; viewId: ");
        sb.append(u());
        sb.append("; checkable: ");
        sb.append(y());
        sb.append("; checked: ");
        sb.append(z());
        sb.append("; focusable: ");
        sb.append(C());
        sb.append("; focused: ");
        sb.append(D());
        sb.append("; selected: ");
        sb.append(H());
        sb.append("; clickable: ");
        sb.append(A());
        sb.append("; longClickable: ");
        sb.append(E());
        sb.append("; enabled: ");
        sb.append(B());
        sb.append("; password: ");
        sb.append(F());
        sb.append("; scrollable: " + G());
        sb.append("; [");
        if (Build.VERSION.SDK_INT >= 21) {
            List<a> listH = h();
            for (int i2 = 0; i2 < listH.size(); i2++) {
                a aVar = listH.get(i2);
                String strI = i(aVar.a());
                if (strI.equals("ACTION_UNKNOWN") && aVar.b() != null) {
                    strI = aVar.b().toString();
                }
                sb.append(strI);
                if (i2 != listH.size() - 1) {
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

    public String u() {
        if (Build.VERSION.SDK_INT >= 18) {
            return this.f1758b.getViewIdResourceName();
        }
        return null;
    }

    public final boolean v() {
        return !g("androidx.view.accessibility.AccessibilityNodeInfoCompat.SPANS_START_KEY").isEmpty();
    }

    public final int w(ClickableSpan clickableSpan, SparseArray<WeakReference<ClickableSpan>> sparseArray) {
        if (sparseArray != null) {
            for (int i2 = 0; i2 < sparseArray.size(); i2++) {
                if (clickableSpan.equals(sparseArray.valueAt(i2).get())) {
                    return sparseArray.keyAt(i2);
                }
            }
        }
        int i3 = f1757a;
        f1757a = i3 + 1;
        return i3;
    }

    public boolean x() {
        if (Build.VERSION.SDK_INT >= 16) {
            return this.f1758b.isAccessibilityFocused();
        }
        return false;
    }

    public boolean y() {
        return this.f1758b.isCheckable();
    }

    public boolean z() {
        return this.f1758b.isChecked();
    }
}
