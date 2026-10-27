package a.h.k.x;

import a.h.k.x.d;
import android.R;
import android.graphics.Rect;
import android.os.Build;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.TextUtils;
import android.text.style.ClickableSpan;
import android.view.accessibility.AccessibilityNodeInfo;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public class b {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final AccessibilityNodeInfo f856a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public int f857b = -1;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public int f858c = -1;

    public static class a {

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        public static final a f859e;

        /* JADX INFO: renamed from: f, reason: collision with root package name */
        public static final a f860f;
        public static final a g;
        public static final a h;
        public static final a i;
        public static final a j;
        public static final a k;
        public static final a l;

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final Object f861a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public final int f862b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public final Class<? extends d.a> f863c;

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public final d f864d;

        static {
            new AccessibilityNodeInfo.AccessibilityAction(1, null);
            new AccessibilityNodeInfo.AccessibilityAction(2, null);
            new AccessibilityNodeInfo.AccessibilityAction(4, null);
            new AccessibilityNodeInfo.AccessibilityAction(8, null);
            f859e = new a(16, null);
            new AccessibilityNodeInfo.AccessibilityAction(32, null);
            new AccessibilityNodeInfo.AccessibilityAction(64, null);
            new AccessibilityNodeInfo.AccessibilityAction(128, null);
            new AccessibilityNodeInfo.AccessibilityAction(256, null);
            new AccessibilityNodeInfo.AccessibilityAction(512, null);
            new AccessibilityNodeInfo.AccessibilityAction(1024, null);
            new AccessibilityNodeInfo.AccessibilityAction(2048, null);
            f860f = new a(4096, null);
            g = new a(8192, null);
            new AccessibilityNodeInfo.AccessibilityAction(16384, null);
            new AccessibilityNodeInfo.AccessibilityAction(32768, null);
            new AccessibilityNodeInfo.AccessibilityAction(65536, null);
            new AccessibilityNodeInfo.AccessibilityAction(131072, null);
            h = new a(262144, null);
            i = new a(524288, null);
            j = new a(1048576, null);
            new AccessibilityNodeInfo.AccessibilityAction(2097152, null);
            if (AccessibilityNodeInfo.AccessibilityAction.ACTION_SHOW_ON_SCREEN == null) {
                new AccessibilityNodeInfo.AccessibilityAction(R.id.accessibilityActionShowOnScreen, null);
            }
            if (AccessibilityNodeInfo.AccessibilityAction.ACTION_SCROLL_TO_POSITION == null) {
                new AccessibilityNodeInfo.AccessibilityAction(R.id.accessibilityActionScrollToPosition, null);
            }
            k = new a(AccessibilityNodeInfo.AccessibilityAction.ACTION_SCROLL_UP, R.id.accessibilityActionScrollUp, null, null, null);
            if (AccessibilityNodeInfo.AccessibilityAction.ACTION_SCROLL_LEFT == null) {
                new AccessibilityNodeInfo.AccessibilityAction(R.id.accessibilityActionScrollLeft, null);
            }
            l = new a(AccessibilityNodeInfo.AccessibilityAction.ACTION_SCROLL_DOWN, R.id.accessibilityActionScrollDown, null, null, null);
            if (AccessibilityNodeInfo.AccessibilityAction.ACTION_SCROLL_RIGHT == null) {
                new AccessibilityNodeInfo.AccessibilityAction(R.id.accessibilityActionScrollRight, null);
            }
            if (AccessibilityNodeInfo.AccessibilityAction.ACTION_PAGE_UP == null) {
                new AccessibilityNodeInfo.AccessibilityAction(R.id.accessibilityActionPageUp, null);
            }
            if (AccessibilityNodeInfo.AccessibilityAction.ACTION_PAGE_DOWN == null) {
                new AccessibilityNodeInfo.AccessibilityAction(R.id.accessibilityActionPageDown, null);
            }
            if (AccessibilityNodeInfo.AccessibilityAction.ACTION_PAGE_LEFT == null) {
                new AccessibilityNodeInfo.AccessibilityAction(R.id.accessibilityActionPageLeft, null);
            }
            if (AccessibilityNodeInfo.AccessibilityAction.ACTION_PAGE_RIGHT == null) {
                new AccessibilityNodeInfo.AccessibilityAction(R.id.accessibilityActionPageRight, null);
            }
            if (AccessibilityNodeInfo.AccessibilityAction.ACTION_CONTEXT_CLICK == null) {
                new AccessibilityNodeInfo.AccessibilityAction(R.id.accessibilityActionContextClick, null);
            }
            if (AccessibilityNodeInfo.AccessibilityAction.ACTION_SET_PROGRESS == null) {
                new AccessibilityNodeInfo.AccessibilityAction(R.id.accessibilityActionSetProgress, null);
            }
            if (AccessibilityNodeInfo.AccessibilityAction.ACTION_MOVE_WINDOW == null) {
                new AccessibilityNodeInfo.AccessibilityAction(R.id.accessibilityActionMoveWindow, null);
            }
            if (AccessibilityNodeInfo.AccessibilityAction.ACTION_SHOW_TOOLTIP == null) {
                new AccessibilityNodeInfo.AccessibilityAction(R.id.accessibilityActionShowTooltip, null);
            }
            if (AccessibilityNodeInfo.AccessibilityAction.ACTION_HIDE_TOOLTIP == null) {
                new AccessibilityNodeInfo.AccessibilityAction(R.id.accessibilityActionHideTooltip, null);
            }
            if ((Build.VERSION.SDK_INT >= 30 ? AccessibilityNodeInfo.AccessibilityAction.ACTION_PRESS_AND_HOLD : null) == null) {
                new AccessibilityNodeInfo.AccessibilityAction(R.id.accessibilityActionPressAndHold, null);
            }
            if ((Build.VERSION.SDK_INT >= 30 ? AccessibilityNodeInfo.AccessibilityAction.ACTION_IME_ENTER : null) == null) {
                new AccessibilityNodeInfo.AccessibilityAction(R.id.accessibilityActionImeEnter, null);
            }
        }

        public a(int i2, CharSequence charSequence) {
            this(null, i2, null, null, null);
        }

        public a(Object obj, int i2, CharSequence charSequence, d dVar, Class<? extends d.a> cls) {
            this.f862b = i2;
            this.f864d = dVar;
            this.f861a = obj == null ? new AccessibilityNodeInfo.AccessibilityAction(i2, charSequence) : obj;
            this.f863c = cls;
        }

        public int a() {
            return ((AccessibilityNodeInfo.AccessibilityAction) this.f861a).getId();
        }

        public boolean equals(Object obj) {
            if (obj == null || !(obj instanceof a)) {
                return false;
            }
            a aVar = (a) obj;
            Object obj2 = this.f861a;
            return obj2 == null ? aVar.f861a == null : obj2.equals(aVar.f861a);
        }

        public int hashCode() {
            Object obj = this.f861a;
            if (obj != null) {
                return obj.hashCode();
            }
            return 0;
        }
    }

    /* JADX INFO: renamed from: a.h.k.x.b$b, reason: collision with other inner class name */
    public static class C0016b {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final Object f865a;

        public C0016b(Object obj) {
            this.f865a = obj;
        }

        public static C0016b a(int i, int i2, boolean z, int i3) {
            return new C0016b(AccessibilityNodeInfo.CollectionInfo.obtain(i, i2, z, i3));
        }
    }

    public static class c {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final Object f866a;

        public c(Object obj) {
            this.f866a = obj;
        }

        public static c a(int i, int i2, int i3, int i4, boolean z, boolean z2) {
            return new c(AccessibilityNodeInfo.CollectionItemInfo.obtain(i, i2, i3, i4, z, z2));
        }
    }

    public b(AccessibilityNodeInfo accessibilityNodeInfo) {
        this.f856a = accessibilityNodeInfo;
    }

    public static String c(int i) {
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

    public static ClickableSpan[] d(CharSequence charSequence) {
        if (charSequence instanceof Spanned) {
            return (ClickableSpan[]) ((Spanned) charSequence).getSpans(0, charSequence.length(), ClickableSpan.class);
        }
        return null;
    }

    public void a(a aVar) {
        this.f856a.addAction((AccessibilityNodeInfo.AccessibilityAction) aVar.f861a);
    }

    public final List<Integer> b(String str) {
        ArrayList<Integer> integerArrayList = this.f856a.getExtras().getIntegerArrayList(str);
        if (integerArrayList != null) {
            return integerArrayList;
        }
        ArrayList<Integer> arrayList = new ArrayList<>();
        this.f856a.getExtras().putIntegerArrayList(str, arrayList);
        return arrayList;
    }

    public CharSequence e() {
        return this.f856a.getContentDescription();
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || !(obj instanceof b)) {
            return false;
        }
        b bVar = (b) obj;
        AccessibilityNodeInfo accessibilityNodeInfo = this.f856a;
        if (accessibilityNodeInfo == null) {
            if (bVar.f856a != null) {
                return false;
            }
        } else if (!accessibilityNodeInfo.equals(bVar.f856a)) {
            return false;
        }
        return this.f858c == bVar.f858c && this.f857b == bVar.f857b;
    }

    public Bundle f() {
        return this.f856a.getExtras();
    }

    public CharSequence g() {
        if (!(!b("androidx.view.accessibility.AccessibilityNodeInfoCompat.SPANS_START_KEY").isEmpty())) {
            return this.f856a.getText();
        }
        List<Integer> listB = b("androidx.view.accessibility.AccessibilityNodeInfoCompat.SPANS_START_KEY");
        List<Integer> listB2 = b("androidx.view.accessibility.AccessibilityNodeInfoCompat.SPANS_END_KEY");
        List<Integer> listB3 = b("androidx.view.accessibility.AccessibilityNodeInfoCompat.SPANS_FLAGS_KEY");
        List<Integer> listB4 = b("androidx.view.accessibility.AccessibilityNodeInfoCompat.SPANS_ID_KEY");
        SpannableString spannableString = new SpannableString(TextUtils.substring(this.f856a.getText(), 0, this.f856a.getText().length()));
        for (int i = 0; i < listB.size(); i++) {
            spannableString.setSpan(new a.h.k.x.a(listB4.get(i).intValue(), this, f().getInt("androidx.view.accessibility.AccessibilityNodeInfoCompat.SPANS_ACTION_ID_KEY")), listB.get(i).intValue(), listB2.get(i).intValue(), listB3.get(i).intValue());
        }
        return spannableString;
    }

    public void h(Object obj) {
        this.f856a.setCollectionInfo(obj == null ? null : (AccessibilityNodeInfo.CollectionInfo) ((C0016b) obj).f865a);
    }

    public int hashCode() {
        AccessibilityNodeInfo accessibilityNodeInfo = this.f856a;
        if (accessibilityNodeInfo == null) {
            return 0;
        }
        return accessibilityNodeInfo.hashCode();
    }

    public void i(Object obj) {
        this.f856a.setCollectionItemInfo((AccessibilityNodeInfo.CollectionItemInfo) ((c) obj).f866a);
    }

    /* JADX WARN: Multi-variable type inference failed */
    /* JADX WARN: Type inference failed for: r2v10, types: [java.util.ArrayList] */
    /* JADX WARN: Type inference failed for: r2v8, types: [java.util.List] */
    /* JADX WARN: Type inference failed for: r2v9, types: [java.util.List] */
    public String toString() {
        ?? EmptyList;
        StringBuilder sb = new StringBuilder();
        sb.append(super.toString());
        Rect rect = new Rect();
        this.f856a.getBoundsInParent(rect);
        sb.append("; boundsInParent: " + rect);
        this.f856a.getBoundsInScreen(rect);
        sb.append("; boundsInScreen: " + rect);
        sb.append("; packageName: ");
        sb.append(this.f856a.getPackageName());
        sb.append("; className: ");
        sb.append(this.f856a.getClassName());
        sb.append("; text: ");
        sb.append(g());
        sb.append("; contentDescription: ");
        sb.append(e());
        sb.append("; viewId: ");
        sb.append(this.f856a.getViewIdResourceName());
        sb.append("; checkable: ");
        sb.append(this.f856a.isCheckable());
        sb.append("; checked: ");
        sb.append(this.f856a.isChecked());
        sb.append("; focusable: ");
        sb.append(this.f856a.isFocusable());
        sb.append("; focused: ");
        sb.append(this.f856a.isFocused());
        sb.append("; selected: ");
        sb.append(this.f856a.isSelected());
        sb.append("; clickable: ");
        sb.append(this.f856a.isClickable());
        sb.append("; longClickable: ");
        sb.append(this.f856a.isLongClickable());
        sb.append("; enabled: ");
        sb.append(this.f856a.isEnabled());
        sb.append("; password: ");
        sb.append(this.f856a.isPassword());
        sb.append("; scrollable: " + this.f856a.isScrollable());
        sb.append("; [");
        List<AccessibilityNodeInfo.AccessibilityAction> actionList = this.f856a.getActionList();
        if (actionList != null) {
            EmptyList = new ArrayList();
            int size = actionList.size();
            for (int i = 0; i < size; i++) {
                EmptyList.add(new a(actionList.get(i), 0, null, null, null));
            }
        } else {
            EmptyList = Collections.emptyList();
        }
        for (int i2 = 0; i2 < EmptyList.size(); i2++) {
            a aVar = (a) EmptyList.get(i2);
            String strC = c(aVar.a());
            if (strC.equals("ACTION_UNKNOWN") && ((AccessibilityNodeInfo.AccessibilityAction) aVar.f861a).getLabel() != null) {
                strC = ((AccessibilityNodeInfo.AccessibilityAction) aVar.f861a).getLabel().toString();
            }
            sb.append(strC);
            if (i2 != EmptyList.size() - 1) {
                sb.append(", ");
            }
        }
        sb.append("]");
        return sb.toString();
    }
}
