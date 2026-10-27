package androidx.core.view.accessibility;

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
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.RestrictTo;
import androidx.core.R$id;
import androidx.core.view.accessibility.e;
import com.byd.mediacenter.api.media.MediaControl;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/* JADX INFO: compiled from: caradapter-bug */
/* JADX INFO: loaded from: classes.dex */
public class b {
    private static int d;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private final AccessibilityNodeInfo f549a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP_PREFIX})
    public int f550b = -1;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private int f551c = -1;

    /* JADX INFO: compiled from: caradapter-bug */
    public static class a {
        public static final a d;
        public static final a e;
        public static final a f;
        public static final a g;

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final Object f552a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        private final Class<? extends e.a> f553b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP_PREFIX})
        protected final e f554c;

        static {
            int i = Build.VERSION.SDK_INT;
            new a(1, null);
            new a(2, null);
            new a(4, null);
            new a(8, null);
            new a(16, null);
            new a(32, null);
            new a(64, null);
            new a(128, null);
            new a(MediaControl.ACTION_CUSTOM, null, e.b.class);
            new a(512, null, e.b.class);
            new a(1024, null, e.c.class);
            new a(2048, null, e.c.class);
            d = new a(4096, null);
            e = new a(8192, null);
            new a(16384, null);
            new a(32768, null);
            new a(65536, null);
            new a(131072, null, e.g.class);
            new a(262144, null);
            new a(524288, null);
            new a(1048576, null);
            new a(2097152, null, e.h.class);
            new a(i >= 23 ? AccessibilityNodeInfo.AccessibilityAction.ACTION_SHOW_ON_SCREEN : null, R.id.accessibilityActionShowOnScreen, null, null, null);
            new a(i >= 23 ? AccessibilityNodeInfo.AccessibilityAction.ACTION_SCROLL_TO_POSITION : null, R.id.accessibilityActionScrollToPosition, null, null, e.C0017e.class);
            f = new a(i >= 23 ? AccessibilityNodeInfo.AccessibilityAction.ACTION_SCROLL_UP : null, R.id.accessibilityActionScrollUp, null, null, null);
            new a(i >= 23 ? AccessibilityNodeInfo.AccessibilityAction.ACTION_SCROLL_LEFT : null, R.id.accessibilityActionScrollLeft, null, null, null);
            g = new a(i >= 23 ? AccessibilityNodeInfo.AccessibilityAction.ACTION_SCROLL_DOWN : null, R.id.accessibilityActionScrollDown, null, null, null);
            new a(i >= 23 ? AccessibilityNodeInfo.AccessibilityAction.ACTION_SCROLL_RIGHT : null, R.id.accessibilityActionScrollRight, null, null, null);
            new a(i >= 29 ? AccessibilityNodeInfo.AccessibilityAction.ACTION_PAGE_UP : null, R.id.accessibilityActionPageUp, null, null, null);
            new a(i >= 29 ? AccessibilityNodeInfo.AccessibilityAction.ACTION_PAGE_DOWN : null, R.id.accessibilityActionPageDown, null, null, null);
            new a(i >= 29 ? AccessibilityNodeInfo.AccessibilityAction.ACTION_PAGE_LEFT : null, R.id.accessibilityActionPageLeft, null, null, null);
            new a(i >= 29 ? AccessibilityNodeInfo.AccessibilityAction.ACTION_PAGE_RIGHT : null, R.id.accessibilityActionPageRight, null, null, null);
            new a(i >= 23 ? AccessibilityNodeInfo.AccessibilityAction.ACTION_CONTEXT_CLICK : null, R.id.accessibilityActionContextClick, null, null, null);
            new a(i >= 24 ? AccessibilityNodeInfo.AccessibilityAction.ACTION_SET_PROGRESS : null, R.id.accessibilityActionSetProgress, null, null, e.f.class);
            new a(i >= 26 ? AccessibilityNodeInfo.AccessibilityAction.ACTION_MOVE_WINDOW : null, R.id.accessibilityActionMoveWindow, null, null, e.d.class);
            new a(i >= 28 ? AccessibilityNodeInfo.AccessibilityAction.ACTION_SHOW_TOOLTIP : null, R.id.accessibilityActionShowTooltip, null, null, null);
            new a(i >= 28 ? AccessibilityNodeInfo.AccessibilityAction.ACTION_HIDE_TOOLTIP : null, R.id.accessibilityActionHideTooltip, null, null, null);
            new a(i >= 30 ? AccessibilityNodeInfo.AccessibilityAction.ACTION_PRESS_AND_HOLD : null, R.id.accessibilityActionPressAndHold, null, null, null);
            new a(i >= 30 ? AccessibilityNodeInfo.AccessibilityAction.ACTION_IME_ENTER : null, R.id.accessibilityActionImeEnter, null, null, null);
        }

        public a(int i, CharSequence charSequence) {
            this(null, i, charSequence, null, null);
        }

        public int a() {
            if (Build.VERSION.SDK_INT >= 21) {
                return ((AccessibilityNodeInfo.AccessibilityAction) this.f552a).getId();
            }
            return 0;
        }

        public CharSequence b() {
            if (Build.VERSION.SDK_INT >= 21) {
                return ((AccessibilityNodeInfo.AccessibilityAction) this.f552a).getLabel();
            }
            return null;
        }

        @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP_PREFIX})
        public boolean c(View view, Bundle bundle) {
            if (this.f554c == null) {
                return false;
            }
            e.a aVar = null;
            Class<? extends e.a> cls = this.f553b;
            if (cls != null) {
                try {
                    e.a aVarNewInstance = cls.getDeclaredConstructor(new Class[0]).newInstance(new Object[0]);
                    try {
                        aVarNewInstance.a(bundle);
                        aVar = aVarNewInstance;
                    } catch (Exception e2) {
                        e = e2;
                        aVar = aVarNewInstance;
                        Class<? extends e.a> cls2 = this.f553b;
                        Log.e("A11yActionCompat", "Failed to execute command with argument class ViewCommandArgument: " + (cls2 == null ? "null" : cls2.getName()), e);
                    }
                } catch (Exception e3) {
                    e = e3;
                }
            }
            return this.f554c.a(view, aVar);
        }

        public boolean equals(@Nullable Object obj) {
            if (obj == null || !(obj instanceof a)) {
                return false;
            }
            a aVar = (a) obj;
            Object obj2 = this.f552a;
            return obj2 == null ? aVar.f552a == null : obj2.equals(aVar.f552a);
        }

        public int hashCode() {
            Object obj = this.f552a;
            if (obj != null) {
                return obj.hashCode();
            }
            return 0;
        }

        a(Object obj) {
            this(obj, 0, null, null, null);
        }

        private a(int i, CharSequence charSequence, Class<? extends e.a> cls) {
            this(null, i, charSequence, null, cls);
        }

        a(Object obj, int i, CharSequence charSequence, e eVar, Class<? extends e.a> cls) {
            this.f554c = eVar;
            if (Build.VERSION.SDK_INT >= 21 && obj == null) {
                this.f552a = new AccessibilityNodeInfo.AccessibilityAction(i, charSequence);
            } else {
                this.f552a = obj;
            }
            this.f553b = cls;
        }
    }

    /* JADX INFO: renamed from: androidx.core.view.accessibility.b$b, reason: collision with other inner class name */
    /* JADX INFO: compiled from: caradapter-bug */
    public static class C0016b {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final Object f555a;

        C0016b(Object obj) {
            this.f555a = obj;
        }

        public static C0016b a(int i, int i2, boolean z, int i3) {
            int i4 = Build.VERSION.SDK_INT;
            return i4 >= 21 ? new C0016b(AccessibilityNodeInfo.CollectionInfo.obtain(i, i2, z, i3)) : i4 >= 19 ? new C0016b(AccessibilityNodeInfo.CollectionInfo.obtain(i, i2, z)) : new C0016b(null);
        }
    }

    /* JADX INFO: compiled from: caradapter-bug */
    public static class c {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final Object f556a;

        c(Object obj) {
            this.f556a = obj;
        }

        public static c a(int i, int i2, int i3, int i4, boolean z, boolean z2) {
            int i5 = Build.VERSION.SDK_INT;
            return i5 >= 21 ? new c(AccessibilityNodeInfo.CollectionItemInfo.obtain(i, i2, i3, i4, z, z2)) : i5 >= 19 ? new c(AccessibilityNodeInfo.CollectionItemInfo.obtain(i, i2, i3, i4, z)) : new c(null);
        }
    }

    private b(AccessibilityNodeInfo accessibilityNodeInfo) {
        this.f549a = accessibilityNodeInfo;
    }

    private void H(View view) {
        SparseArray<WeakReference<ClickableSpan>> sparseArrayR = r(view);
        if (sparseArrayR != null) {
            ArrayList arrayList = new ArrayList();
            for (int i = 0; i < sparseArrayR.size(); i++) {
                if (sparseArrayR.valueAt(i).get() == null) {
                    arrayList.add(Integer.valueOf(i));
                }
            }
            for (int i2 = 0; i2 < arrayList.size(); i2++) {
                sparseArrayR.remove(((Integer) arrayList.get(i2)).intValue());
            }
        }
    }

    private void I(int i, boolean z) {
        Bundle bundleO = o();
        if (bundleO != null) {
            int i2 = bundleO.getInt("androidx.view.accessibility.AccessibilityNodeInfoCompat.BOOLEAN_PROPERTY_KEY", 0) & (~i);
            if (!z) {
                i = 0;
            }
            bundleO.putInt("androidx.view.accessibility.AccessibilityNodeInfoCompat.BOOLEAN_PROPERTY_KEY", i | i2);
        }
    }

    public static b S(@NonNull AccessibilityNodeInfo accessibilityNodeInfo) {
        return new b(accessibilityNodeInfo);
    }

    private void c(ClickableSpan clickableSpan, Spanned spanned, int i) {
        f("androidx.view.accessibility.AccessibilityNodeInfoCompat.SPANS_START_KEY").add(Integer.valueOf(spanned.getSpanStart(clickableSpan)));
        f("androidx.view.accessibility.AccessibilityNodeInfoCompat.SPANS_END_KEY").add(Integer.valueOf(spanned.getSpanEnd(clickableSpan)));
        f("androidx.view.accessibility.AccessibilityNodeInfoCompat.SPANS_FLAGS_KEY").add(Integer.valueOf(spanned.getSpanFlags(clickableSpan)));
        f("androidx.view.accessibility.AccessibilityNodeInfoCompat.SPANS_ID_KEY").add(Integer.valueOf(i));
    }

    private void e() {
        if (Build.VERSION.SDK_INT >= 19) {
            this.f549a.getExtras().remove("androidx.view.accessibility.AccessibilityNodeInfoCompat.SPANS_START_KEY");
            this.f549a.getExtras().remove("androidx.view.accessibility.AccessibilityNodeInfoCompat.SPANS_END_KEY");
            this.f549a.getExtras().remove("androidx.view.accessibility.AccessibilityNodeInfoCompat.SPANS_FLAGS_KEY");
            this.f549a.getExtras().remove("androidx.view.accessibility.AccessibilityNodeInfoCompat.SPANS_ID_KEY");
        }
    }

    private List<Integer> f(String str) {
        if (Build.VERSION.SDK_INT < 19) {
            return new ArrayList();
        }
        ArrayList<Integer> integerArrayList = this.f549a.getExtras().getIntegerArrayList(str);
        if (integerArrayList != null) {
            return integerArrayList;
        }
        ArrayList<Integer> arrayList = new ArrayList<>();
        this.f549a.getExtras().putIntegerArrayList(str, arrayList);
        return arrayList;
    }

    private static String h(int i) {
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
            case MediaControl.ACTION_CUSTOM /* 256 */:
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
                            case R.id.accessibilityActionPressAndHold:
                                return "ACTION_PRESS_AND_HOLD";
                            default:
                                return "ACTION_UNKNOWN";
                        }
                }
        }
    }

    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP_PREFIX})
    public static ClickableSpan[] m(CharSequence charSequence) {
        if (charSequence instanceof Spanned) {
            return (ClickableSpan[]) ((Spanned) charSequence).getSpans(0, charSequence.length(), ClickableSpan.class);
        }
        return null;
    }

    private SparseArray<WeakReference<ClickableSpan>> p(View view) {
        SparseArray<WeakReference<ClickableSpan>> sparseArrayR = r(view);
        if (sparseArrayR != null) {
            return sparseArrayR;
        }
        SparseArray<WeakReference<ClickableSpan>> sparseArray = new SparseArray<>();
        view.setTag(R$id.tag_accessibility_clickable_spans, sparseArray);
        return sparseArray;
    }

    private SparseArray<WeakReference<ClickableSpan>> r(View view) {
        return (SparseArray) view.getTag(R$id.tag_accessibility_clickable_spans);
    }

    private boolean u() {
        return !f("androidx.view.accessibility.AccessibilityNodeInfoCompat.SPANS_START_KEY").isEmpty();
    }

    private int v(ClickableSpan clickableSpan, SparseArray<WeakReference<ClickableSpan>> sparseArray) {
        if (sparseArray != null) {
            for (int i = 0; i < sparseArray.size(); i++) {
                if (clickableSpan.equals(sparseArray.valueAt(i).get())) {
                    return sparseArray.keyAt(i);
                }
            }
        }
        int i2 = d;
        d = i2 + 1;
        return i2;
    }

    public boolean A() {
        return this.f549a.isFocusable();
    }

    public boolean B() {
        return this.f549a.isFocused();
    }

    public boolean C() {
        return this.f549a.isLongClickable();
    }

    public boolean D() {
        return this.f549a.isPassword();
    }

    public boolean E() {
        return this.f549a.isScrollable();
    }

    public boolean F() {
        return this.f549a.isSelected();
    }

    public boolean G(int i, Bundle bundle) {
        if (Build.VERSION.SDK_INT >= 16) {
            return this.f549a.performAction(i, bundle);
        }
        return false;
    }

    public void J(CharSequence charSequence) {
        this.f549a.setClassName(charSequence);
    }

    public void K(Object obj) {
        if (Build.VERSION.SDK_INT >= 19) {
            this.f549a.setCollectionInfo(obj == null ? null : (AccessibilityNodeInfo.CollectionInfo) ((C0016b) obj).f555a);
        }
    }

    public void L(Object obj) {
        if (Build.VERSION.SDK_INT >= 19) {
            this.f549a.setCollectionItemInfo(obj == null ? null : (AccessibilityNodeInfo.CollectionItemInfo) ((c) obj).f556a);
        }
    }

    public void M(boolean z) {
        if (Build.VERSION.SDK_INT >= 28) {
            this.f549a.setHeading(z);
        } else {
            I(2, z);
        }
    }

    public void N(@Nullable CharSequence charSequence) {
        int i = Build.VERSION.SDK_INT;
        if (i >= 28) {
            this.f549a.setPaneTitle(charSequence);
        } else if (i >= 19) {
            this.f549a.getExtras().putCharSequence("androidx.view.accessibility.AccessibilityNodeInfoCompat.PANE_TITLE_KEY", charSequence);
        }
    }

    public void O(boolean z) {
        if (Build.VERSION.SDK_INT >= 28) {
            this.f549a.setScreenReaderFocusable(z);
        } else {
            I(1, z);
        }
    }

    public void P(boolean z) {
        this.f549a.setScrollable(z);
    }

    public void Q(@Nullable CharSequence charSequence) {
        if (androidx.core.os.a.a()) {
            this.f549a.setStateDescription(charSequence);
        } else if (Build.VERSION.SDK_INT >= 19) {
            this.f549a.getExtras().putCharSequence("androidx.view.accessibility.AccessibilityNodeInfoCompat.STATE_DESCRIPTION_KEY", charSequence);
        }
    }

    public AccessibilityNodeInfo R() {
        return this.f549a;
    }

    public void a(int i) {
        this.f549a.addAction(i);
    }

    public void b(a aVar) {
        if (Build.VERSION.SDK_INT >= 21) {
            this.f549a.addAction((AccessibilityNodeInfo.AccessibilityAction) aVar.f552a);
        }
    }

    @RestrictTo({RestrictTo.Scope.LIBRARY_GROUP_PREFIX})
    public void d(CharSequence charSequence, View view) {
        int i = Build.VERSION.SDK_INT;
        if (i < 19 || i >= 26) {
            return;
        }
        e();
        H(view);
        ClickableSpan[] clickableSpanArrM = m(charSequence);
        if (clickableSpanArrM == null || clickableSpanArrM.length <= 0) {
            return;
        }
        o().putInt("androidx.view.accessibility.AccessibilityNodeInfoCompat.SPANS_ACTION_ID_KEY", R$id.accessibility_action_clickable_span);
        SparseArray<WeakReference<ClickableSpan>> sparseArrayP = p(view);
        for (int i2 = 0; clickableSpanArrM != null && i2 < clickableSpanArrM.length; i2++) {
            int iV = v(clickableSpanArrM[i2], sparseArrayP);
            sparseArrayP.put(iV, new WeakReference<>(clickableSpanArrM[i2]));
            c(clickableSpanArrM[i2], (Spanned) charSequence, iV);
        }
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof b)) {
            return false;
        }
        b bVar = (b) obj;
        AccessibilityNodeInfo accessibilityNodeInfo = this.f549a;
        if (accessibilityNodeInfo == null) {
            if (bVar.f549a != null) {
                return false;
            }
        } else if (!accessibilityNodeInfo.equals(bVar.f549a)) {
            return false;
        }
        return this.f551c == bVar.f551c && this.f550b == bVar.f550b;
    }

    public List<a> g() {
        List<AccessibilityNodeInfo.AccessibilityAction> actionList = Build.VERSION.SDK_INT >= 21 ? this.f549a.getActionList() : null;
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

    public int hashCode() {
        AccessibilityNodeInfo accessibilityNodeInfo = this.f549a;
        if (accessibilityNodeInfo == null) {
            return 0;
        }
        return accessibilityNodeInfo.hashCode();
    }

    public int i() {
        return this.f549a.getActions();
    }

    @Deprecated
    public void j(Rect rect) {
        this.f549a.getBoundsInParent(rect);
    }

    public void k(Rect rect) {
        this.f549a.getBoundsInScreen(rect);
    }

    public CharSequence l() {
        return this.f549a.getClassName();
    }

    public CharSequence n() {
        return this.f549a.getContentDescription();
    }

    public Bundle o() {
        return Build.VERSION.SDK_INT >= 19 ? this.f549a.getExtras() : new Bundle();
    }

    public CharSequence q() {
        return this.f549a.getPackageName();
    }

    public CharSequence s() {
        if (!u()) {
            return this.f549a.getText();
        }
        List<Integer> listF = f("androidx.view.accessibility.AccessibilityNodeInfoCompat.SPANS_START_KEY");
        List<Integer> listF2 = f("androidx.view.accessibility.AccessibilityNodeInfoCompat.SPANS_END_KEY");
        List<Integer> listF3 = f("androidx.view.accessibility.AccessibilityNodeInfoCompat.SPANS_FLAGS_KEY");
        List<Integer> listF4 = f("androidx.view.accessibility.AccessibilityNodeInfoCompat.SPANS_ID_KEY");
        SpannableString spannableString = new SpannableString(TextUtils.substring(this.f549a.getText(), 0, this.f549a.getText().length()));
        for (int i = 0; i < listF.size(); i++) {
            spannableString.setSpan(new androidx.core.view.accessibility.a(listF4.get(i).intValue(), this, o().getInt("androidx.view.accessibility.AccessibilityNodeInfoCompat.SPANS_ACTION_ID_KEY")), listF.get(i).intValue(), listF2.get(i).intValue(), listF3.get(i).intValue());
        }
        return spannableString;
    }

    public String t() {
        if (Build.VERSION.SDK_INT >= 18) {
            return this.f549a.getViewIdResourceName();
        }
        return null;
    }

    @NonNull
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(super.toString());
        Rect rect = new Rect();
        j(rect);
        sb.append("; boundsInParent: " + rect);
        k(rect);
        sb.append("; boundsInScreen: " + rect);
        sb.append("; packageName: ");
        sb.append(q());
        sb.append("; className: ");
        sb.append(l());
        sb.append("; text: ");
        sb.append(s());
        sb.append("; contentDescription: ");
        sb.append(n());
        sb.append("; viewId: ");
        sb.append(t());
        sb.append("; checkable: ");
        sb.append(w());
        sb.append("; checked: ");
        sb.append(x());
        sb.append("; focusable: ");
        sb.append(A());
        sb.append("; focused: ");
        sb.append(B());
        sb.append("; selected: ");
        sb.append(F());
        sb.append("; clickable: ");
        sb.append(y());
        sb.append("; longClickable: ");
        sb.append(C());
        sb.append("; enabled: ");
        sb.append(z());
        sb.append("; password: ");
        sb.append(D());
        sb.append("; scrollable: " + E());
        sb.append("; [");
        if (Build.VERSION.SDK_INT >= 21) {
            List<a> listG = g();
            for (int i = 0; i < listG.size(); i++) {
                a aVar = listG.get(i);
                String strH = h(aVar.a());
                if (strH.equals("ACTION_UNKNOWN") && aVar.b() != null) {
                    strH = aVar.b().toString();
                }
                sb.append(strH);
                if (i != listG.size() - 1) {
                    sb.append(", ");
                }
            }
        } else {
            int i2 = i();
            while (i2 != 0) {
                int iNumberOfTrailingZeros = 1 << Integer.numberOfTrailingZeros(i2);
                i2 &= ~iNumberOfTrailingZeros;
                sb.append(h(iNumberOfTrailingZeros));
                if (i2 != 0) {
                    sb.append(", ");
                }
            }
        }
        sb.append("]");
        return sb.toString();
    }

    public boolean w() {
        return this.f549a.isCheckable();
    }

    public boolean x() {
        return this.f549a.isChecked();
    }

    public boolean y() {
        return this.f549a.isClickable();
    }

    public boolean z() {
        return this.f549a.isEnabled();
    }
}
