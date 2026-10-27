package p0;

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
import p0.f;
import ts.car.service.carplay.data.config.CarPlayConfig;

/* JADX INFO: compiled from: AccessibilityNodeInfoCompat.java */
/* JADX INFO: loaded from: classes.dex */
public class c {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public static int f6964a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final AccessibilityNodeInfo f6965b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public int f6966c = -1;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public int f6967d = -1;

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
        public static final a f6968a = new a(1, null);

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public static final a f6969b = new a(2, null);

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        public static final a f6970c = new a(4, null);

        /* JADX INFO: renamed from: d, reason: collision with root package name */
        public static final a f6971d = new a(8, null);

        /* JADX INFO: renamed from: e, reason: collision with root package name */
        public static final a f6972e = new a(16, null);

        /* JADX INFO: renamed from: f, reason: collision with root package name */
        public static final a f6973f = new a(32, null);

        /* JADX INFO: renamed from: g, reason: collision with root package name */
        public static final a f6974g = new a(64, null);

        /* JADX INFO: renamed from: h, reason: collision with root package name */
        public static final a f6975h = new a(128, null);

        /* JADX INFO: renamed from: i, reason: collision with root package name */
        public static final a f6976i = new a(256, null, f.b.class);

        /* JADX INFO: renamed from: j, reason: collision with root package name */
        public static final a f6977j = new a(512, null, f.b.class);

        /* JADX INFO: renamed from: k, reason: collision with root package name */
        public static final a f6978k = new a(1024, null, f.c.class);

        /* JADX INFO: renamed from: l, reason: collision with root package name */
        public static final a f6979l = new a(2048, null, f.c.class);

        /* JADX INFO: renamed from: m, reason: collision with root package name */
        public static final a f6980m = new a(4096, null);

        /* JADX INFO: renamed from: n, reason: collision with root package name */
        public static final a f6981n = new a(8192, null);

        /* JADX INFO: renamed from: o, reason: collision with root package name */
        public static final a f6982o = new a(CarPlayConfig.IapFeature.IAP_FEATURE_ACC_WIFI_INFO_SHARING, null);

        /* JADX INFO: renamed from: p, reason: collision with root package name */
        public static final a f6983p = new a(CarPlayConfig.IapFeature.IAP_FEATURE_ROUTE_GUIDANCE, null);

        /* JADX INFO: renamed from: q, reason: collision with root package name */
        public static final a f6984q = new a(CarPlayConfig.IapFeature.IAP_FEATURE_EAP_OVER_IAP2, null);

        /* JADX INFO: renamed from: r, reason: collision with root package name */
        public static final a f6985r = new a(CarPlayConfig.IapFeature.IAP_FEATURE_APP_LAUNCH, null, f.g.class);

        /* JADX INFO: renamed from: s, reason: collision with root package name */
        public static final a f6986s = new a(CarPlayConfig.IapFeature.IAP_FEATURE_LIST_UPDATES, null);

        /* JADX INFO: renamed from: t, reason: collision with root package name */
        public static final a f6987t = new a(CarPlayConfig.IapFeature.IAP_FEATURE_WIRELESS_CARPLAY_UPDATE, null);

        /* JADX INFO: renamed from: u, reason: collision with root package name */
        public static final a f6988u = new a(CarPlayConfig.IapFeature.IAP_FEATURE_DEVICE_TRANSPORT_NOTIFY, null);

        /* JADX INFO: renamed from: v, reason: collision with root package name */
        public static final a f6989v = new a(2097152, null, f.h.class);

        /* JADX INFO: renamed from: w, reason: collision with root package name */
        public static final a f6990w;

        /* JADX INFO: renamed from: x, reason: collision with root package name */
        public static final a f6991x;

        /* JADX INFO: renamed from: y, reason: collision with root package name */
        public static final a f6992y;

        /* JADX INFO: renamed from: z, reason: collision with root package name */
        public static final a f6993z;
        public final Object L;
        public final int M;
        public final Class<? extends f.a> N;
        public final f O;

        static {
            int i8 = Build.VERSION.SDK_INT;
            f6990w = new a(i8 >= 23 ? AccessibilityNodeInfo.AccessibilityAction.ACTION_SHOW_ON_SCREEN : null, R.id.accessibilityActionShowOnScreen, null, null, null);
            f6991x = new a(i8 >= 23 ? AccessibilityNodeInfo.AccessibilityAction.ACTION_SCROLL_TO_POSITION : null, R.id.accessibilityActionScrollToPosition, null, null, f.e.class);
            f6992y = new a(i8 >= 23 ? AccessibilityNodeInfo.AccessibilityAction.ACTION_SCROLL_UP : null, R.id.accessibilityActionScrollUp, null, null, null);
            f6993z = new a(i8 >= 23 ? AccessibilityNodeInfo.AccessibilityAction.ACTION_SCROLL_LEFT : null, R.id.accessibilityActionScrollLeft, null, null, null);
            A = new a(i8 >= 23 ? AccessibilityNodeInfo.AccessibilityAction.ACTION_SCROLL_DOWN : null, R.id.accessibilityActionScrollDown, null, null, null);
            B = new a(i8 >= 23 ? AccessibilityNodeInfo.AccessibilityAction.ACTION_SCROLL_RIGHT : null, R.id.accessibilityActionScrollRight, null, null, null);
            C = new a(i8 >= 29 ? AccessibilityNodeInfo.AccessibilityAction.ACTION_PAGE_UP : null, R.id.accessibilityActionPageUp, null, null, null);
            D = new a(i8 >= 29 ? AccessibilityNodeInfo.AccessibilityAction.ACTION_PAGE_DOWN : null, R.id.accessibilityActionPageDown, null, null, null);
            E = new a(i8 >= 29 ? AccessibilityNodeInfo.AccessibilityAction.ACTION_PAGE_LEFT : null, R.id.accessibilityActionPageLeft, null, null, null);
            F = new a(i8 >= 29 ? AccessibilityNodeInfo.AccessibilityAction.ACTION_PAGE_RIGHT : null, R.id.accessibilityActionPageRight, null, null, null);
            G = new a(i8 >= 23 ? AccessibilityNodeInfo.AccessibilityAction.ACTION_CONTEXT_CLICK : null, R.id.accessibilityActionContextClick, null, null, null);
            H = new a(i8 >= 24 ? AccessibilityNodeInfo.AccessibilityAction.ACTION_SET_PROGRESS : null, R.id.accessibilityActionSetProgress, null, null, f.C0102f.class);
            I = new a(i8 >= 26 ? AccessibilityNodeInfo.AccessibilityAction.ACTION_MOVE_WINDOW : null, R.id.accessibilityActionMoveWindow, null, null, f.d.class);
            J = new a(i8 >= 28 ? AccessibilityNodeInfo.AccessibilityAction.ACTION_SHOW_TOOLTIP : null, R.id.accessibilityActionShowTooltip, null, null, null);
            K = new a(i8 >= 28 ? AccessibilityNodeInfo.AccessibilityAction.ACTION_HIDE_TOOLTIP : null, R.id.accessibilityActionHideTooltip, null, null, null);
        }

        public a(int i8, CharSequence charSequence) {
            this(null, i8, charSequence, null, null);
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
                    } catch (Exception e8) {
                        e = e8;
                        aVar = aVarNewInstance;
                        Class<? extends f.a> cls2 = this.N;
                        Log.e("A11yActionCompat", "Failed to execute command with argument class ViewCommandArgument: " + (cls2 == null ? "null" : cls2.getName()), e);
                    }
                } catch (Exception e9) {
                    e = e9;
                }
            }
            return this.O.perform(view, aVar);
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

        public a(int i8, CharSequence charSequence, Class<? extends f.a> cls) {
            this(null, i8, charSequence, null, cls);
        }

        public a(Object obj, int i8, CharSequence charSequence, f fVar, Class<? extends f.a> cls) {
            this.M = i8;
            this.O = fVar;
            if (Build.VERSION.SDK_INT >= 21 && obj == null) {
                this.L = new AccessibilityNodeInfo.AccessibilityAction(i8, charSequence);
            } else {
                this.L = obj;
            }
            this.N = cls;
        }
    }

    /* JADX INFO: compiled from: AccessibilityNodeInfoCompat.java */
    public static class b {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final Object f6994a;

        public b(Object obj) {
            this.f6994a = obj;
        }

        public static b a(int i8, int i9, boolean z7) {
            return Build.VERSION.SDK_INT >= 19 ? new b(AccessibilityNodeInfo.CollectionInfo.obtain(i8, i9, z7)) : new b(null);
        }

        public static b b(int i8, int i9, boolean z7, int i10) {
            int i11 = Build.VERSION.SDK_INT;
            return i11 >= 21 ? new b(AccessibilityNodeInfo.CollectionInfo.obtain(i8, i9, z7, i10)) : i11 >= 19 ? new b(AccessibilityNodeInfo.CollectionInfo.obtain(i8, i9, z7)) : new b(null);
        }
    }

    /* JADX INFO: renamed from: p0.c$c, reason: collision with other inner class name */
    /* JADX INFO: compiled from: AccessibilityNodeInfoCompat.java */
    public static class C0101c {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final Object f6995a;

        public C0101c(Object obj) {
            this.f6995a = obj;
        }

        public static C0101c a(int i8, int i9, int i10, int i11, boolean z7, boolean z8) {
            int i12 = Build.VERSION.SDK_INT;
            return i12 >= 21 ? new C0101c(AccessibilityNodeInfo.CollectionItemInfo.obtain(i8, i9, i10, i11, z7, z8)) : i12 >= 19 ? new C0101c(AccessibilityNodeInfo.CollectionItemInfo.obtain(i8, i9, i10, i11, z7)) : new C0101c(null);
        }
    }

    /* JADX INFO: compiled from: AccessibilityNodeInfoCompat.java */
    public static class d {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final Object f6996a;

        public d(Object obj) {
            this.f6996a = obj;
        }

        public static d a(int i8, float f8, float f9, float f10) {
            return Build.VERSION.SDK_INT >= 19 ? new d(AccessibilityNodeInfo.RangeInfo.obtain(i8, f8, f9, f10)) : new d(null);
        }
    }

    public c(AccessibilityNodeInfo accessibilityNodeInfo) {
        this.f6965b = accessibilityNodeInfo;
    }

    public static c F0(AccessibilityNodeInfo accessibilityNodeInfo) {
        return new c(accessibilityNodeInfo);
    }

    public static c O() {
        return F0(AccessibilityNodeInfo.obtain());
    }

    public static c P(View view) {
        return F0(AccessibilityNodeInfo.obtain(view));
    }

    public static c Q(c cVar) {
        return F0(AccessibilityNodeInfo.obtain(cVar.f6965b));
    }

    public static String j(int i8) {
        if (i8 == 1) {
            return "ACTION_FOCUS";
        }
        if (i8 == 2) {
            return "ACTION_CLEAR_FOCUS";
        }
        switch (i8) {
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
            case CarPlayConfig.IapFeature.IAP_FEATURE_ACC_WIFI_INFO_SHARING /* 16384 */:
                return "ACTION_COPY";
            case CarPlayConfig.IapFeature.IAP_FEATURE_ROUTE_GUIDANCE /* 32768 */:
                return "ACTION_PASTE";
            case CarPlayConfig.IapFeature.IAP_FEATURE_EAP_OVER_IAP2 /* 65536 */:
                return "ACTION_CUT";
            case CarPlayConfig.IapFeature.IAP_FEATURE_APP_LAUNCH /* 131072 */:
                return "ACTION_SET_SELECTION";
            case CarPlayConfig.IapFeature.IAP_FEATURE_LIST_UPDATES /* 262144 */:
                return "ACTION_EXPAND";
            case CarPlayConfig.IapFeature.IAP_FEATURE_WIRELESS_CARPLAY_UPDATE /* 524288 */:
                return "ACTION_COLLAPSE";
            case 2097152:
                return "ACTION_SET_TEXT";
            case R.id.accessibilityActionMoveWindow:
                return "ACTION_MOVE_WINDOW";
            default:
                switch (i8) {
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
                        switch (i8) {
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

    public final int A(ClickableSpan clickableSpan, SparseArray<WeakReference<ClickableSpan>> sparseArray) {
        if (sparseArray != null) {
            for (int i8 = 0; i8 < sparseArray.size(); i8++) {
                if (clickableSpan.equals(sparseArray.valueAt(i8).get())) {
                    return sparseArray.keyAt(i8);
                }
            }
        }
        int i9 = f6964a;
        f6964a = i9 + 1;
        return i9;
    }

    public void A0(View view) {
        this.f6967d = -1;
        this.f6965b.setSource(view);
    }

    public boolean B() {
        if (Build.VERSION.SDK_INT >= 16) {
            return this.f6965b.isAccessibilityFocused();
        }
        return false;
    }

    public void B0(View view, int i8) {
        this.f6967d = i8;
        if (Build.VERSION.SDK_INT >= 16) {
            this.f6965b.setSource(view, i8);
        }
    }

    public boolean C() {
        return this.f6965b.isCheckable();
    }

    public void C0(CharSequence charSequence) {
        this.f6965b.setText(charSequence);
    }

    public boolean D() {
        return this.f6965b.isChecked();
    }

    public void D0(boolean z7) {
        if (Build.VERSION.SDK_INT >= 16) {
            this.f6965b.setVisibleToUser(z7);
        }
    }

    public boolean E() {
        return this.f6965b.isClickable();
    }

    public AccessibilityNodeInfo E0() {
        return this.f6965b;
    }

    public boolean F() {
        return this.f6965b.isEnabled();
    }

    public boolean G() {
        return this.f6965b.isFocusable();
    }

    public boolean H() {
        return this.f6965b.isFocused();
    }

    public boolean I() {
        return this.f6965b.isLongClickable();
    }

    public boolean J() {
        return this.f6965b.isPassword();
    }

    public boolean K() {
        return this.f6965b.isScrollable();
    }

    public boolean L() {
        return this.f6965b.isSelected();
    }

    public boolean M() {
        return Build.VERSION.SDK_INT >= 26 ? this.f6965b.isShowingHintText() : l(4);
    }

    public boolean N() {
        if (Build.VERSION.SDK_INT >= 16) {
            return this.f6965b.isVisibleToUser();
        }
        return false;
    }

    public boolean R(int i8, Bundle bundle) {
        if (Build.VERSION.SDK_INT >= 16) {
            return this.f6965b.performAction(i8, bundle);
        }
        return false;
    }

    public void S() {
        this.f6965b.recycle();
    }

    public boolean T(a aVar) {
        if (Build.VERSION.SDK_INT >= 21) {
            return this.f6965b.removeAction((AccessibilityNodeInfo.AccessibilityAction) aVar.L);
        }
        return false;
    }

    public final void U(View view) {
        SparseArray<WeakReference<ClickableSpan>> sparseArrayW = w(view);
        if (sparseArrayW != null) {
            ArrayList arrayList = new ArrayList();
            for (int i8 = 0; i8 < sparseArrayW.size(); i8++) {
                if (sparseArrayW.valueAt(i8).get() == null) {
                    arrayList.add(Integer.valueOf(i8));
                }
            }
            for (int i9 = 0; i9 < arrayList.size(); i9++) {
                sparseArrayW.remove(((Integer) arrayList.get(i9)).intValue());
            }
        }
    }

    public void V(boolean z7) {
        if (Build.VERSION.SDK_INT >= 16) {
            this.f6965b.setAccessibilityFocused(z7);
        }
    }

    public final void W(int i8, boolean z7) {
        Bundle bundleS = s();
        if (bundleS != null) {
            int i9 = bundleS.getInt("androidx.view.accessibility.AccessibilityNodeInfoCompat.BOOLEAN_PROPERTY_KEY", 0) & (~i8);
            if (!z7) {
                i8 = 0;
            }
            bundleS.putInt("androidx.view.accessibility.AccessibilityNodeInfoCompat.BOOLEAN_PROPERTY_KEY", i8 | i9);
        }
    }

    @Deprecated
    public void X(Rect rect) {
        this.f6965b.setBoundsInParent(rect);
    }

    public void Y(Rect rect) {
        this.f6965b.setBoundsInScreen(rect);
    }

    public void Z(boolean z7) {
        if (Build.VERSION.SDK_INT >= 19) {
            this.f6965b.setCanOpenPopup(z7);
        }
    }

    public void a(int i8) {
        this.f6965b.addAction(i8);
    }

    public void a0(boolean z7) {
        this.f6965b.setCheckable(z7);
    }

    public void b(a aVar) {
        if (Build.VERSION.SDK_INT >= 21) {
            this.f6965b.addAction((AccessibilityNodeInfo.AccessibilityAction) aVar.L);
        }
    }

    public void b0(boolean z7) {
        this.f6965b.setChecked(z7);
    }

    public void c(View view) {
        this.f6965b.addChild(view);
    }

    public void c0(CharSequence charSequence) {
        this.f6965b.setClassName(charSequence);
    }

    public void d(View view, int i8) {
        if (Build.VERSION.SDK_INT >= 16) {
            this.f6965b.addChild(view, i8);
        }
    }

    public void d0(boolean z7) {
        this.f6965b.setClickable(z7);
    }

    public final void e(ClickableSpan clickableSpan, Spanned spanned, int i8) {
        h("androidx.view.accessibility.AccessibilityNodeInfoCompat.SPANS_START_KEY").add(Integer.valueOf(spanned.getSpanStart(clickableSpan)));
        h("androidx.view.accessibility.AccessibilityNodeInfoCompat.SPANS_END_KEY").add(Integer.valueOf(spanned.getSpanEnd(clickableSpan)));
        h("androidx.view.accessibility.AccessibilityNodeInfoCompat.SPANS_FLAGS_KEY").add(Integer.valueOf(spanned.getSpanFlags(clickableSpan)));
        h("androidx.view.accessibility.AccessibilityNodeInfoCompat.SPANS_ID_KEY").add(Integer.valueOf(i8));
    }

    public void e0(Object obj) {
        if (Build.VERSION.SDK_INT >= 19) {
            this.f6965b.setCollectionInfo(obj == null ? null : (AccessibilityNodeInfo.CollectionInfo) ((b) obj).f6994a);
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
        AccessibilityNodeInfo accessibilityNodeInfo = this.f6965b;
        if (accessibilityNodeInfo == null) {
            if (cVar.f6965b != null) {
                return false;
            }
        } else if (!accessibilityNodeInfo.equals(cVar.f6965b)) {
            return false;
        }
        return this.f6967d == cVar.f6967d && this.f6966c == cVar.f6966c;
    }

    public void f(CharSequence charSequence, View view) {
        int i8 = Build.VERSION.SDK_INT;
        if (i8 < 19 || i8 >= 26) {
            return;
        }
        g();
        U(view);
        ClickableSpan[] clickableSpanArrQ = q(charSequence);
        if (clickableSpanArrQ == null || clickableSpanArrQ.length <= 0) {
            return;
        }
        s().putInt("androidx.view.accessibility.AccessibilityNodeInfoCompat.SPANS_ACTION_ID_KEY", R$id.accessibility_action_clickable_span);
        SparseArray<WeakReference<ClickableSpan>> sparseArrayU = u(view);
        for (int i9 = 0; i9 < clickableSpanArrQ.length; i9++) {
            int iA = A(clickableSpanArrQ[i9], sparseArrayU);
            sparseArrayU.put(iA, new WeakReference<>(clickableSpanArrQ[i9]));
            e(clickableSpanArrQ[i9], (Spanned) charSequence, iA);
        }
    }

    public void f0(Object obj) {
        if (Build.VERSION.SDK_INT >= 19) {
            this.f6965b.setCollectionItemInfo(obj == null ? null : (AccessibilityNodeInfo.CollectionItemInfo) ((C0101c) obj).f6995a);
        }
    }

    public final void g() {
        if (Build.VERSION.SDK_INT >= 19) {
            this.f6965b.getExtras().remove("androidx.view.accessibility.AccessibilityNodeInfoCompat.SPANS_START_KEY");
            this.f6965b.getExtras().remove("androidx.view.accessibility.AccessibilityNodeInfoCompat.SPANS_END_KEY");
            this.f6965b.getExtras().remove("androidx.view.accessibility.AccessibilityNodeInfoCompat.SPANS_FLAGS_KEY");
            this.f6965b.getExtras().remove("androidx.view.accessibility.AccessibilityNodeInfoCompat.SPANS_ID_KEY");
        }
    }

    public void g0(CharSequence charSequence) {
        this.f6965b.setContentDescription(charSequence);
    }

    public final List<Integer> h(String str) {
        if (Build.VERSION.SDK_INT < 19) {
            return new ArrayList();
        }
        ArrayList<Integer> integerArrayList = this.f6965b.getExtras().getIntegerArrayList(str);
        if (integerArrayList != null) {
            return integerArrayList;
        }
        ArrayList<Integer> arrayList = new ArrayList<>();
        this.f6965b.getExtras().putIntegerArrayList(str, arrayList);
        return arrayList;
    }

    public void h0(boolean z7) {
        this.f6965b.setEnabled(z7);
    }

    public int hashCode() {
        AccessibilityNodeInfo accessibilityNodeInfo = this.f6965b;
        if (accessibilityNodeInfo == null) {
            return 0;
        }
        return accessibilityNodeInfo.hashCode();
    }

    public List<a> i() {
        List<AccessibilityNodeInfo.AccessibilityAction> actionList = Build.VERSION.SDK_INT >= 21 ? this.f6965b.getActionList() : null;
        if (actionList == null) {
            return Collections.emptyList();
        }
        ArrayList arrayList = new ArrayList();
        int size = actionList.size();
        for (int i8 = 0; i8 < size; i8++) {
            arrayList.add(new a(actionList.get(i8)));
        }
        return arrayList;
    }

    public void i0(CharSequence charSequence) {
        if (Build.VERSION.SDK_INT >= 21) {
            this.f6965b.setError(charSequence);
        }
    }

    public void j0(boolean z7) {
        this.f6965b.setFocusable(z7);
    }

    public int k() {
        return this.f6965b.getActions();
    }

    public void k0(boolean z7) {
        this.f6965b.setFocused(z7);
    }

    public final boolean l(int i8) {
        Bundle bundleS = s();
        return bundleS != null && (bundleS.getInt("androidx.view.accessibility.AccessibilityNodeInfoCompat.BOOLEAN_PROPERTY_KEY", 0) & i8) == i8;
    }

    public void l0(boolean z7) {
        if (Build.VERSION.SDK_INT >= 28) {
            this.f6965b.setHeading(z7);
        } else {
            W(2, z7);
        }
    }

    @Deprecated
    public void m(Rect rect) {
        this.f6965b.getBoundsInParent(rect);
    }

    public void m0(CharSequence charSequence) {
        int i8 = Build.VERSION.SDK_INT;
        if (i8 >= 26) {
            this.f6965b.setHintText(charSequence);
        } else if (i8 >= 19) {
            this.f6965b.getExtras().putCharSequence("androidx.view.accessibility.AccessibilityNodeInfoCompat.HINT_TEXT_KEY", charSequence);
        }
    }

    public void n(Rect rect) {
        this.f6965b.getBoundsInScreen(rect);
    }

    public void n0(boolean z7) {
        this.f6965b.setLongClickable(z7);
    }

    public int o() {
        return this.f6965b.getChildCount();
    }

    public void o0(int i8) {
        if (Build.VERSION.SDK_INT >= 21) {
            this.f6965b.setMaxTextLength(i8);
        }
    }

    public CharSequence p() {
        return this.f6965b.getClassName();
    }

    public void p0(int i8) {
        if (Build.VERSION.SDK_INT >= 16) {
            this.f6965b.setMovementGranularities(i8);
        }
    }

    public void q0(CharSequence charSequence) {
        this.f6965b.setPackageName(charSequence);
    }

    public CharSequence r() {
        return this.f6965b.getContentDescription();
    }

    public void r0(CharSequence charSequence) {
        int i8 = Build.VERSION.SDK_INT;
        if (i8 >= 28) {
            this.f6965b.setPaneTitle(charSequence);
        } else if (i8 >= 19) {
            this.f6965b.getExtras().putCharSequence("androidx.view.accessibility.AccessibilityNodeInfoCompat.PANE_TITLE_KEY", charSequence);
        }
    }

    public Bundle s() {
        return Build.VERSION.SDK_INT >= 19 ? this.f6965b.getExtras() : new Bundle();
    }

    public void s0(View view) {
        this.f6966c = -1;
        this.f6965b.setParent(view);
    }

    public int t() {
        if (Build.VERSION.SDK_INT >= 16) {
            return this.f6965b.getMovementGranularities();
        }
        return 0;
    }

    public void t0(View view, int i8) {
        this.f6966c = i8;
        if (Build.VERSION.SDK_INT >= 16) {
            this.f6965b.setParent(view, i8);
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
        sb.append(v());
        sb.append("; className: ");
        sb.append(p());
        sb.append("; text: ");
        sb.append(x());
        sb.append("; contentDescription: ");
        sb.append(r());
        sb.append("; viewId: ");
        sb.append(y());
        sb.append("; checkable: ");
        sb.append(C());
        sb.append("; checked: ");
        sb.append(D());
        sb.append("; focusable: ");
        sb.append(G());
        sb.append("; focused: ");
        sb.append(H());
        sb.append("; selected: ");
        sb.append(L());
        sb.append("; clickable: ");
        sb.append(E());
        sb.append("; longClickable: ");
        sb.append(I());
        sb.append("; enabled: ");
        sb.append(F());
        sb.append("; password: ");
        sb.append(J());
        sb.append("; scrollable: " + K());
        sb.append("; [");
        if (Build.VERSION.SDK_INT >= 21) {
            List<a> listI = i();
            for (int i8 = 0; i8 < listI.size(); i8++) {
                a aVar = listI.get(i8);
                String strJ = j(aVar.b());
                if (strJ.equals("ACTION_UNKNOWN") && aVar.c() != null) {
                    strJ = aVar.c().toString();
                }
                sb.append(strJ);
                if (i8 != listI.size() - 1) {
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

    public final SparseArray<WeakReference<ClickableSpan>> u(View view) {
        SparseArray<WeakReference<ClickableSpan>> sparseArrayW = w(view);
        if (sparseArrayW != null) {
            return sparseArrayW;
        }
        SparseArray<WeakReference<ClickableSpan>> sparseArray = new SparseArray<>();
        view.setTag(R$id.tag_accessibility_clickable_spans, sparseArray);
        return sparseArray;
    }

    public void u0(d dVar) {
        if (Build.VERSION.SDK_INT >= 19) {
            this.f6965b.setRangeInfo((AccessibilityNodeInfo.RangeInfo) dVar.f6996a);
        }
    }

    public CharSequence v() {
        return this.f6965b.getPackageName();
    }

    public void v0(CharSequence charSequence) {
        if (Build.VERSION.SDK_INT >= 19) {
            this.f6965b.getExtras().putCharSequence("AccessibilityNodeInfo.roleDescription", charSequence);
        }
    }

    public final SparseArray<WeakReference<ClickableSpan>> w(View view) {
        return (SparseArray) view.getTag(R$id.tag_accessibility_clickable_spans);
    }

    public void w0(boolean z7) {
        if (Build.VERSION.SDK_INT >= 28) {
            this.f6965b.setScreenReaderFocusable(z7);
        } else {
            W(1, z7);
        }
    }

    public CharSequence x() {
        if (!z()) {
            return this.f6965b.getText();
        }
        List<Integer> listH = h("androidx.view.accessibility.AccessibilityNodeInfoCompat.SPANS_START_KEY");
        List<Integer> listH2 = h("androidx.view.accessibility.AccessibilityNodeInfoCompat.SPANS_END_KEY");
        List<Integer> listH3 = h("androidx.view.accessibility.AccessibilityNodeInfoCompat.SPANS_FLAGS_KEY");
        List<Integer> listH4 = h("androidx.view.accessibility.AccessibilityNodeInfoCompat.SPANS_ID_KEY");
        SpannableString spannableString = new SpannableString(TextUtils.substring(this.f6965b.getText(), 0, this.f6965b.getText().length()));
        for (int i8 = 0; i8 < listH.size(); i8++) {
            spannableString.setSpan(new p0.a(listH4.get(i8).intValue(), this, s().getInt("androidx.view.accessibility.AccessibilityNodeInfoCompat.SPANS_ACTION_ID_KEY")), listH.get(i8).intValue(), listH2.get(i8).intValue(), listH3.get(i8).intValue());
        }
        return spannableString;
    }

    public void x0(boolean z7) {
        this.f6965b.setScrollable(z7);
    }

    public String y() {
        if (Build.VERSION.SDK_INT >= 18) {
            return this.f6965b.getViewIdResourceName();
        }
        return null;
    }

    public void y0(boolean z7) {
        this.f6965b.setSelected(z7);
    }

    public final boolean z() {
        return !h("androidx.view.accessibility.AccessibilityNodeInfoCompat.SPANS_START_KEY").isEmpty();
    }

    public void z0(boolean z7) {
        if (Build.VERSION.SDK_INT >= 26) {
            this.f6965b.setShowingHintText(z7);
        } else {
            W(4, z7);
        }
    }
}
