package j0;

import android.R;
import android.graphics.Rect;
import android.os.Build;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.TextUtils;
import android.text.style.ClickableSpan;
import android.util.Log;
import android.view.View;
import android.view.accessibility.AccessibilityNodeInfo;
import j0.f;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/* JADX INFO: compiled from: AccessibilityNodeInfoCompat.java */
/* JADX INFO: loaded from: classes.dex */
public class c {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final AccessibilityNodeInfo f7110a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public int f7111b = -1;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private int f7112c = -1;

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
        public static final a L;
        public static final a M;
        public static final a N;
        public static final a O;
        public static final a P;
        public static final a Q;

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        public static final a f7113e = new a(1, null);

        /* JADX INFO: renamed from: f, reason: collision with root package name */
        public static final a f7114f = new a(2, null);

        /* JADX INFO: renamed from: g, reason: collision with root package name */
        public static final a f7115g = new a(4, null);

        /* JADX INFO: renamed from: h, reason: collision with root package name */
        public static final a f7116h = new a(8, null);

        /* JADX INFO: renamed from: i, reason: collision with root package name */
        public static final a f7117i = new a(16, null);

        /* JADX INFO: renamed from: j, reason: collision with root package name */
        public static final a f7118j = new a(32, null);

        /* JADX INFO: renamed from: k, reason: collision with root package name */
        public static final a f7119k = new a(64, null);

        /* JADX INFO: renamed from: l, reason: collision with root package name */
        public static final a f7120l = new a(128, null);

        /* JADX INFO: renamed from: m, reason: collision with root package name */
        public static final a f7121m = new a(256, (CharSequence) null, (Class<? extends f.a>) f.b.class);

        /* JADX INFO: renamed from: n, reason: collision with root package name */
        public static final a f7122n = new a(512, (CharSequence) null, (Class<? extends f.a>) f.b.class);

        /* JADX INFO: renamed from: o, reason: collision with root package name */
        public static final a f7123o = new a(1024, (CharSequence) null, (Class<? extends f.a>) f.c.class);

        /* JADX INFO: renamed from: p, reason: collision with root package name */
        public static final a f7124p = new a(2048, (CharSequence) null, (Class<? extends f.a>) f.c.class);

        /* JADX INFO: renamed from: q, reason: collision with root package name */
        public static final a f7125q = new a(4096, null);

        /* JADX INFO: renamed from: r, reason: collision with root package name */
        public static final a f7126r = new a(8192, null);

        /* JADX INFO: renamed from: s, reason: collision with root package name */
        public static final a f7127s = new a(16384, null);

        /* JADX INFO: renamed from: t, reason: collision with root package name */
        public static final a f7128t = new a(32768, null);

        /* JADX INFO: renamed from: u, reason: collision with root package name */
        public static final a f7129u = new a(65536, null);

        /* JADX INFO: renamed from: v, reason: collision with root package name */
        public static final a f7130v = new a(131072, (CharSequence) null, (Class<? extends f.a>) f.g.class);

        /* JADX INFO: renamed from: w, reason: collision with root package name */
        public static final a f7131w = new a(262144, null);

        /* JADX INFO: renamed from: x, reason: collision with root package name */
        public static final a f7132x = new a(524288, null);

        /* JADX INFO: renamed from: y, reason: collision with root package name */
        public static final a f7133y = new a(1048576, null);

        /* JADX INFO: renamed from: z, reason: collision with root package name */
        public static final a f7134z = new a(2097152, (CharSequence) null, (Class<? extends f.a>) f.h.class);

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final Object f7135a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        private final int f7136b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        private final Class<? extends f.a> f7137c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        protected final f f7138d;

        static {
            int i7 = Build.VERSION.SDK_INT;
            A = new a(AccessibilityNodeInfo.AccessibilityAction.ACTION_SHOW_ON_SCREEN, R.id.accessibilityActionShowOnScreen, null, null, null);
            B = new a(AccessibilityNodeInfo.AccessibilityAction.ACTION_SCROLL_TO_POSITION, R.id.accessibilityActionScrollToPosition, null, null, f.e.class);
            C = new a(AccessibilityNodeInfo.AccessibilityAction.ACTION_SCROLL_UP, R.id.accessibilityActionScrollUp, null, null, null);
            D = new a(AccessibilityNodeInfo.AccessibilityAction.ACTION_SCROLL_LEFT, R.id.accessibilityActionScrollLeft, null, null, null);
            E = new a(AccessibilityNodeInfo.AccessibilityAction.ACTION_SCROLL_DOWN, R.id.accessibilityActionScrollDown, null, null, null);
            F = new a(AccessibilityNodeInfo.AccessibilityAction.ACTION_SCROLL_RIGHT, R.id.accessibilityActionScrollRight, null, null, null);
            G = new a(i7 >= 29 ? AccessibilityNodeInfo.AccessibilityAction.ACTION_PAGE_UP : null, R.id.accessibilityActionPageUp, null, null, null);
            H = new a(i7 >= 29 ? AccessibilityNodeInfo.AccessibilityAction.ACTION_PAGE_DOWN : null, R.id.accessibilityActionPageDown, null, null, null);
            I = new a(i7 >= 29 ? AccessibilityNodeInfo.AccessibilityAction.ACTION_PAGE_LEFT : null, R.id.accessibilityActionPageLeft, null, null, null);
            J = new a(i7 >= 29 ? AccessibilityNodeInfo.AccessibilityAction.ACTION_PAGE_RIGHT : null, R.id.accessibilityActionPageRight, null, null, null);
            K = new a(AccessibilityNodeInfo.AccessibilityAction.ACTION_CONTEXT_CLICK, R.id.accessibilityActionContextClick, null, null, null);
            L = new a(AccessibilityNodeInfo.AccessibilityAction.ACTION_SET_PROGRESS, R.id.accessibilityActionSetProgress, null, null, f.C0091f.class);
            M = new a(AccessibilityNodeInfo.AccessibilityAction.ACTION_MOVE_WINDOW, R.id.accessibilityActionMoveWindow, null, null, f.d.class);
            N = new a(AccessibilityNodeInfo.AccessibilityAction.ACTION_SHOW_TOOLTIP, R.id.accessibilityActionShowTooltip, null, null, null);
            O = new a(AccessibilityNodeInfo.AccessibilityAction.ACTION_HIDE_TOOLTIP, R.id.accessibilityActionHideTooltip, null, null, null);
            P = new a(i7 >= 30 ? AccessibilityNodeInfo.AccessibilityAction.ACTION_PRESS_AND_HOLD : null, R.id.accessibilityActionPressAndHold, null, null, null);
            Q = new a(i7 >= 30 ? AccessibilityNodeInfo.AccessibilityAction.ACTION_IME_ENTER : null, R.id.accessibilityActionImeEnter, null, null, null);
        }

        public a(int i7, CharSequence charSequence) {
            this(null, i7, charSequence, null, null);
        }

        public a a(CharSequence charSequence, f fVar) {
            return new a(null, this.f7136b, charSequence, fVar, this.f7137c);
        }

        public int b() {
            return ((AccessibilityNodeInfo.AccessibilityAction) this.f7135a).getId();
        }

        public CharSequence c() {
            return ((AccessibilityNodeInfo.AccessibilityAction) this.f7135a).getLabel();
        }

        public boolean d(View view, Bundle bundle) {
            if (this.f7138d == null) {
                return false;
            }
            f.a aVar = null;
            Class<? extends f.a> cls = this.f7137c;
            if (cls != null) {
                try {
                    f.a aVarNewInstance = cls.getDeclaredConstructor(new Class[0]).newInstance(new Object[0]);
                    try {
                        aVarNewInstance.a(bundle);
                        aVar = aVarNewInstance;
                    } catch (Exception e7) {
                        e = e7;
                        aVar = aVarNewInstance;
                        Class<? extends f.a> cls2 = this.f7137c;
                        Log.e("A11yActionCompat", "Failed to execute command with argument class ViewCommandArgument: " + (cls2 == null ? "null" : cls2.getName()), e);
                    }
                } catch (Exception e8) {
                    e = e8;
                }
            }
            return this.f7138d.a(view, aVar);
        }

        public boolean equals(Object obj) {
            if (obj == null || !(obj instanceof a)) {
                return false;
            }
            a aVar = (a) obj;
            Object obj2 = this.f7135a;
            return obj2 == null ? aVar.f7135a == null : obj2.equals(aVar.f7135a);
        }

        public int hashCode() {
            Object obj = this.f7135a;
            if (obj != null) {
                return obj.hashCode();
            }
            return 0;
        }

        public a(int i7, CharSequence charSequence, f fVar) {
            this(null, i7, charSequence, fVar, null);
        }

        a(Object obj) {
            this(obj, 0, null, null, null);
        }

        private a(int i7, CharSequence charSequence, Class<? extends f.a> cls) {
            this(null, i7, charSequence, null, cls);
        }

        a(Object obj, int i7, CharSequence charSequence, f fVar, Class<? extends f.a> cls) {
            this.f7136b = i7;
            this.f7138d = fVar;
            if (obj == null) {
                this.f7135a = new AccessibilityNodeInfo.AccessibilityAction(i7, charSequence);
            } else {
                this.f7135a = obj;
            }
            this.f7137c = cls;
        }
    }

    /* JADX INFO: compiled from: AccessibilityNodeInfoCompat.java */
    public static class b {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final Object f7139a;

        b(Object obj) {
            this.f7139a = obj;
        }

        public static b a(int i7, int i8, boolean z6, int i9) {
            return new b(AccessibilityNodeInfo.CollectionInfo.obtain(i7, i8, z6, i9));
        }
    }

    /* JADX INFO: renamed from: j0.c$c, reason: collision with other inner class name */
    /* JADX INFO: compiled from: AccessibilityNodeInfoCompat.java */
    public static class C0090c {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final Object f7140a;

        C0090c(Object obj) {
            this.f7140a = obj;
        }

        public static C0090c a(int i7, int i8, int i9, int i10, boolean z6, boolean z7) {
            return new C0090c(AccessibilityNodeInfo.CollectionItemInfo.obtain(i7, i8, i9, i10, z6, z7));
        }
    }

    private c(AccessibilityNodeInfo accessibilityNodeInfo) {
        this.f7110a = accessibilityNodeInfo;
    }

    public static c E() {
        return p0(AccessibilityNodeInfo.obtain());
    }

    public static c F(View view) {
        return p0(AccessibilityNodeInfo.obtain(view));
    }

    public static c G(c cVar) {
        return p0(AccessibilityNodeInfo.obtain(cVar.f7110a));
    }

    private List<Integer> e(String str) {
        ArrayList<Integer> integerArrayList = this.f7110a.getExtras().getIntegerArrayList(str);
        if (integerArrayList != null) {
            return integerArrayList;
        }
        ArrayList<Integer> arrayList = new ArrayList<>();
        this.f7110a.getExtras().putIntegerArrayList(str, arrayList);
        return arrayList;
    }

    private static String g(int i7) {
        if (i7 == 1) {
            return "ACTION_FOCUS";
        }
        if (i7 == 2) {
            return "ACTION_CLEAR_FOCUS";
        }
        switch (i7) {
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
            case R.id.accessibilityActionImeEnter:
                return "ACTION_IME_ENTER";
            default:
                switch (i7) {
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
                        switch (i7) {
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
                            case R.id.accessibilityActionPressAndHold:
                                return "ACTION_PRESS_AND_HOLD";
                            default:
                                return "ACTION_UNKNOWN";
                        }
                }
        }
    }

    public static ClickableSpan[] m(CharSequence charSequence) {
        if (charSequence instanceof Spanned) {
            return (ClickableSpan[]) ((Spanned) charSequence).getSpans(0, charSequence.length(), ClickableSpan.class);
        }
        return null;
    }

    public static c p0(AccessibilityNodeInfo accessibilityNodeInfo) {
        return new c(accessibilityNodeInfo);
    }

    private boolean s() {
        return !e("androidx.view.accessibility.AccessibilityNodeInfoCompat.SPANS_START_KEY").isEmpty();
    }

    public boolean A() {
        return this.f7110a.isPassword();
    }

    public boolean B() {
        return this.f7110a.isScrollable();
    }

    public boolean C() {
        return this.f7110a.isSelected();
    }

    public boolean D() {
        return this.f7110a.isShowingHintText();
    }

    public boolean H(int i7, Bundle bundle) {
        return this.f7110a.performAction(i7, bundle);
    }

    public void I() {
        this.f7110a.recycle();
    }

    public boolean J(a aVar) {
        return this.f7110a.removeAction((AccessibilityNodeInfo.AccessibilityAction) aVar.f7135a);
    }

    public void K(boolean z6) {
        this.f7110a.setAccessibilityFocused(z6);
    }

    @Deprecated
    public void L(Rect rect) {
        this.f7110a.setBoundsInParent(rect);
    }

    public void M(Rect rect) {
        this.f7110a.setBoundsInScreen(rect);
    }

    public void N(boolean z6) {
        this.f7110a.setCheckable(z6);
    }

    public void O(boolean z6) {
        this.f7110a.setChecked(z6);
    }

    public void P(CharSequence charSequence) {
        this.f7110a.setClassName(charSequence);
    }

    public void Q(boolean z6) {
        this.f7110a.setClickable(z6);
    }

    public void R(Object obj) {
        this.f7110a.setCollectionInfo(obj == null ? null : (AccessibilityNodeInfo.CollectionInfo) ((b) obj).f7139a);
    }

    public void S(Object obj) {
        this.f7110a.setCollectionItemInfo(obj == null ? null : (AccessibilityNodeInfo.CollectionItemInfo) ((C0090c) obj).f7140a);
    }

    public void T(CharSequence charSequence) {
        this.f7110a.setContentDescription(charSequence);
    }

    public void U(boolean z6) {
        this.f7110a.setEnabled(z6);
    }

    public void V(CharSequence charSequence) {
        this.f7110a.setError(charSequence);
    }

    public void W(boolean z6) {
        this.f7110a.setFocusable(z6);
    }

    public void X(boolean z6) {
        this.f7110a.setFocused(z6);
    }

    public void Y(boolean z6) {
        this.f7110a.setHeading(z6);
    }

    public void Z(CharSequence charSequence) {
        this.f7110a.setHintText(charSequence);
    }

    public void a(int i7) {
        this.f7110a.addAction(i7);
    }

    public void a0(int i7) {
        this.f7110a.setMaxTextLength(i7);
    }

    public void b(a aVar) {
        this.f7110a.addAction((AccessibilityNodeInfo.AccessibilityAction) aVar.f7135a);
    }

    public void b0(CharSequence charSequence) {
        this.f7110a.setPackageName(charSequence);
    }

    public void c(View view, int i7) {
        this.f7110a.addChild(view, i7);
    }

    public void c0(CharSequence charSequence) {
        this.f7110a.setPaneTitle(charSequence);
    }

    public void d(CharSequence charSequence, View view) {
    }

    public void d0(View view) {
        this.f7111b = -1;
        this.f7110a.setParent(view);
    }

    public void e0(View view, int i7) {
        this.f7111b = i7;
        this.f7110a.setParent(view, i7);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof c)) {
            return false;
        }
        c cVar = (c) obj;
        AccessibilityNodeInfo accessibilityNodeInfo = this.f7110a;
        if (accessibilityNodeInfo == null) {
            if (cVar.f7110a != null) {
                return false;
            }
        } else if (!accessibilityNodeInfo.equals(cVar.f7110a)) {
            return false;
        }
        return this.f7112c == cVar.f7112c && this.f7111b == cVar.f7111b;
    }

    public List<a> f() {
        List<AccessibilityNodeInfo.AccessibilityAction> actionList = this.f7110a.getActionList();
        if (actionList == null) {
            return Collections.emptyList();
        }
        ArrayList arrayList = new ArrayList();
        int size = actionList.size();
        for (int i7 = 0; i7 < size; i7++) {
            arrayList.add(new a(actionList.get(i7)));
        }
        return arrayList;
    }

    public void f0(CharSequence charSequence) {
        this.f7110a.getExtras().putCharSequence("AccessibilityNodeInfo.roleDescription", charSequence);
    }

    public void g0(boolean z6) {
        this.f7110a.setScreenReaderFocusable(z6);
    }

    public int h() {
        return this.f7110a.getActions();
    }

    public void h0(boolean z6) {
        this.f7110a.setScrollable(z6);
    }

    public int hashCode() {
        AccessibilityNodeInfo accessibilityNodeInfo = this.f7110a;
        if (accessibilityNodeInfo == null) {
            return 0;
        }
        return accessibilityNodeInfo.hashCode();
    }

    @Deprecated
    public void i(Rect rect) {
        this.f7110a.getBoundsInParent(rect);
    }

    public void i0(boolean z6) {
        this.f7110a.setShowingHintText(z6);
    }

    public void j(Rect rect) {
        this.f7110a.getBoundsInScreen(rect);
    }

    public void j0(View view, int i7) {
        this.f7112c = i7;
        this.f7110a.setSource(view, i7);
    }

    public int k() {
        return this.f7110a.getChildCount();
    }

    public void k0(CharSequence charSequence) {
        if (d0.a.a()) {
            this.f7110a.setStateDescription(charSequence);
        } else {
            this.f7110a.getExtras().putCharSequence("androidx.view.accessibility.AccessibilityNodeInfoCompat.STATE_DESCRIPTION_KEY", charSequence);
        }
    }

    public CharSequence l() {
        return this.f7110a.getClassName();
    }

    public void l0(CharSequence charSequence) {
        this.f7110a.setText(charSequence);
    }

    public void m0(View view) {
        this.f7110a.setTraversalAfter(view);
    }

    public CharSequence n() {
        return this.f7110a.getContentDescription();
    }

    public void n0(boolean z6) {
        this.f7110a.setVisibleToUser(z6);
    }

    public Bundle o() {
        return this.f7110a.getExtras();
    }

    public AccessibilityNodeInfo o0() {
        return this.f7110a;
    }

    public CharSequence p() {
        return this.f7110a.getPackageName();
    }

    public CharSequence q() {
        if (!s()) {
            return this.f7110a.getText();
        }
        List<Integer> listE = e("androidx.view.accessibility.AccessibilityNodeInfoCompat.SPANS_START_KEY");
        List<Integer> listE2 = e("androidx.view.accessibility.AccessibilityNodeInfoCompat.SPANS_END_KEY");
        List<Integer> listE3 = e("androidx.view.accessibility.AccessibilityNodeInfoCompat.SPANS_FLAGS_KEY");
        List<Integer> listE4 = e("androidx.view.accessibility.AccessibilityNodeInfoCompat.SPANS_ID_KEY");
        SpannableString spannableString = new SpannableString(TextUtils.substring(this.f7110a.getText(), 0, this.f7110a.getText().length()));
        for (int i7 = 0; i7 < listE.size(); i7++) {
            spannableString.setSpan(new j0.a(listE4.get(i7).intValue(), this, o().getInt("androidx.view.accessibility.AccessibilityNodeInfoCompat.SPANS_ACTION_ID_KEY")), listE.get(i7).intValue(), listE2.get(i7).intValue(), listE3.get(i7).intValue());
        }
        return spannableString;
    }

    public String r() {
        return this.f7110a.getViewIdResourceName();
    }

    public boolean t() {
        return this.f7110a.isCheckable();
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(super.toString());
        Rect rect = new Rect();
        i(rect);
        sb.append("; boundsInParent: " + rect);
        j(rect);
        sb.append("; boundsInScreen: " + rect);
        sb.append("; packageName: ");
        sb.append(p());
        sb.append("; className: ");
        sb.append(l());
        sb.append("; text: ");
        sb.append(q());
        sb.append("; contentDescription: ");
        sb.append(n());
        sb.append("; viewId: ");
        sb.append(r());
        sb.append("; checkable: ");
        sb.append(t());
        sb.append("; checked: ");
        sb.append(u());
        sb.append("; focusable: ");
        sb.append(x());
        sb.append("; focused: ");
        sb.append(y());
        sb.append("; selected: ");
        sb.append(C());
        sb.append("; clickable: ");
        sb.append(v());
        sb.append("; longClickable: ");
        sb.append(z());
        sb.append("; enabled: ");
        sb.append(w());
        sb.append("; password: ");
        sb.append(A());
        sb.append("; scrollable: " + B());
        sb.append("; [");
        List<a> listF = f();
        for (int i7 = 0; i7 < listF.size(); i7++) {
            a aVar = listF.get(i7);
            String strG = g(aVar.b());
            if (strG.equals("ACTION_UNKNOWN") && aVar.c() != null) {
                strG = aVar.c().toString();
            }
            sb.append(strG);
            if (i7 != listF.size() - 1) {
                sb.append(", ");
            }
        }
        sb.append("]");
        return sb.toString();
    }

    public boolean u() {
        return this.f7110a.isChecked();
    }

    public boolean v() {
        return this.f7110a.isClickable();
    }

    public boolean w() {
        return this.f7110a.isEnabled();
    }

    public boolean x() {
        return this.f7110a.isFocusable();
    }

    public boolean y() {
        return this.f7110a.isFocused();
    }

    public boolean z() {
        return this.f7110a.isLongClickable();
    }
}
