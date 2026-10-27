package spi;

import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.SystemClock;
import android.util.AttributeSet;
import android.util.Log;
import android.util.SparseArray;
import android.util.SparseIntArray;
import android.view.View;
import android.view.ViewGroup;
import android.view.accessibility.AccessibilityNodeInfo;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.android.car.ui.R$bool;
import com.android.car.ui.R$drawable;
import com.android.car.ui.R$integer;
import com.android.car.ui.R$styleable;
import com.android.car.ui.rotary.a;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

/* JADX INFO: compiled from: FocusAreaHelper.java */
/* JADX INFO: loaded from: classes2.dex */
public final class y3 {
    public static final List<Integer> D = Arrays.asList(17, 66, 33, 130);
    public static final List<Integer> E = Arrays.asList(1, 16777216, 33554432);
    public View A;
    public final a3 B;
    public boolean C;

    @NonNull
    public final ViewGroup a;
    public boolean b;
    public boolean c;
    public boolean d;
    public Drawable e;
    public Drawable f;
    public int g;
    public int h;
    public int i;
    public int j;
    public int k;
    public int l;
    public int m;
    public int n;
    public boolean o;
    public int p;

    @Nullable
    public View q;
    public boolean r;
    public final SparseIntArray s;
    public SparseArray<View> t;
    public final SparseIntArray u;
    public SparseArray<o4> v;
    public boolean w;
    public com.android.car.ui.rotary.a x;
    public boolean y;
    public o4 z;

    public y3(@NonNull ViewGroup viewGroup, @Nullable AttributeSet attributeSet) {
        SparseIntArray sparseIntArray = new SparseIntArray();
        this.s = sparseIntArray;
        SparseIntArray sparseIntArray2 = new SparseIntArray();
        this.u = sparseIntArray2;
        boolean z = true;
        this.C = true;
        this.a = viewGroup;
        this.B = new a3(this, 4);
        Context context = viewGroup.getContext();
        Resources resources = context.getResources();
        this.c = resources.getBoolean(R$bool.car_ui_enable_focus_area_foreground_highlight);
        this.d = resources.getBoolean(R$bool.car_ui_enable_focus_area_background_highlight);
        this.e = resources.getDrawable(R$drawable.car_ui_focus_area_foreground_highlight, context.getTheme());
        this.f = resources.getDrawable(R$drawable.car_ui_focus_area_background_highlight, context.getTheme());
        this.y = resources.getBoolean(R$bool.car_ui_clear_focus_area_history_when_rotating);
        this.x = new com.android.car.ui.rotary.a(resources.getInteger(R$integer.car_ui_focus_history_cache_type), resources.getInteger(R$integer.car_ui_focus_history_expiration_period_ms), resources.getInteger(R$integer.car_ui_focus_area_history_cache_type), resources.getInteger(R$integer.car_ui_focus_area_history_expiration_period_ms));
        viewGroup.setImportantForAccessibility(1);
        viewGroup.setWillNotDraw(false);
        if (attributeSet == null) {
            return;
        }
        TypedArray typedArrayObtainStyledAttributes = context.obtainStyledAttributes(attributeSet, R$styleable.IFocusArea);
        try {
            this.p = typedArrayObtainStyledAttributes.getResourceId(R$styleable.IFocusArea_defaultFocus, -1);
            int dimensionPixelSize = typedArrayObtainStyledAttributes.getDimensionPixelSize(R$styleable.IFocusArea_highlightPaddingStart, -1);
            dimensionPixelSize = dimensionPixelSize == -1 ? typedArrayObtainStyledAttributes.getDimensionPixelSize(R$styleable.IFocusArea_highlightPaddingHorizontal, 0) : dimensionPixelSize;
            int dimensionPixelSize2 = typedArrayObtainStyledAttributes.getDimensionPixelSize(R$styleable.IFocusArea_highlightPaddingEnd, -1);
            dimensionPixelSize2 = dimensionPixelSize2 == -1 ? typedArrayObtainStyledAttributes.getDimensionPixelSize(R$styleable.IFocusArea_highlightPaddingHorizontal, 0) : dimensionPixelSize2;
            boolean z2 = viewGroup.getLayoutDirection() == 1;
            this.o = z2;
            this.g = z2 ? dimensionPixelSize2 : dimensionPixelSize;
            this.h = z2 ? dimensionPixelSize : dimensionPixelSize2;
            int dimensionPixelSize3 = typedArrayObtainStyledAttributes.getDimensionPixelSize(R$styleable.IFocusArea_highlightPaddingTop, -1);
            this.i = dimensionPixelSize3;
            if (dimensionPixelSize3 == -1) {
                this.i = typedArrayObtainStyledAttributes.getDimensionPixelSize(R$styleable.IFocusArea_highlightPaddingVertical, 0);
            }
            int dimensionPixelSize4 = typedArrayObtainStyledAttributes.getDimensionPixelSize(R$styleable.IFocusArea_highlightPaddingBottom, -1);
            this.j = dimensionPixelSize4;
            if (dimensionPixelSize4 == -1) {
                this.j = typedArrayObtainStyledAttributes.getDimensionPixelSize(R$styleable.IFocusArea_highlightPaddingVertical, 0);
            }
            int dimensionPixelSize5 = typedArrayObtainStyledAttributes.getDimensionPixelSize(R$styleable.IFocusArea_startBoundOffset, -1);
            dimensionPixelSize5 = dimensionPixelSize5 == -1 ? typedArrayObtainStyledAttributes.getDimensionPixelSize(R$styleable.IFocusArea_horizontalBoundOffset, dimensionPixelSize) : dimensionPixelSize5;
            int dimensionPixelSize6 = typedArrayObtainStyledAttributes.getDimensionPixelSize(R$styleable.IFocusArea_endBoundOffset, -1);
            dimensionPixelSize6 = dimensionPixelSize6 == -1 ? typedArrayObtainStyledAttributes.getDimensionPixelSize(R$styleable.IFocusArea_horizontalBoundOffset, dimensionPixelSize2) : dimensionPixelSize6;
            boolean z3 = this.o;
            this.k = z3 ? dimensionPixelSize6 : dimensionPixelSize5;
            if (!z3) {
                dimensionPixelSize5 = dimensionPixelSize6;
            }
            this.l = dimensionPixelSize5;
            int dimensionPixelSize7 = typedArrayObtainStyledAttributes.getDimensionPixelSize(R$styleable.IFocusArea_topBoundOffset, -1);
            this.m = dimensionPixelSize7;
            if (dimensionPixelSize7 == -1) {
                this.m = typedArrayObtainStyledAttributes.getDimensionPixelSize(R$styleable.IFocusArea_verticalBoundOffset, this.i);
            }
            int dimensionPixelSize8 = typedArrayObtainStyledAttributes.getDimensionPixelSize(R$styleable.IFocusArea_bottomBoundOffset, -1);
            this.n = dimensionPixelSize8;
            if (dimensionPixelSize8 == -1) {
                this.n = typedArrayObtainStyledAttributes.getDimensionPixelSize(R$styleable.IFocusArea_verticalBoundOffset, this.j);
            }
            int i = R$styleable.IFocusArea_nudgeLeftShortcut;
            if (typedArrayObtainStyledAttributes.hasValue(i)) {
                sparseIntArray.put(17, typedArrayObtainStyledAttributes.getResourceId(i, -1));
            }
            int i2 = R$styleable.IFocusArea_nudgeRightShortcut;
            if (typedArrayObtainStyledAttributes.hasValue(i2)) {
                sparseIntArray.put(66, typedArrayObtainStyledAttributes.getResourceId(i2, -1));
            }
            int i3 = R$styleable.IFocusArea_nudgeUpShortcut;
            if (typedArrayObtainStyledAttributes.hasValue(i3)) {
                sparseIntArray.put(33, typedArrayObtainStyledAttributes.getResourceId(i3, -1));
            }
            int i4 = R$styleable.IFocusArea_nudgeDownShortcut;
            if (typedArrayObtainStyledAttributes.hasValue(i4)) {
                sparseIntArray.put(130, typedArrayObtainStyledAttributes.getResourceId(i4, -1));
            }
            int resourceId = typedArrayObtainStyledAttributes.getResourceId(R$styleable.IFocusArea_nudgeShortcut, -1);
            int i5 = typedArrayObtainStyledAttributes.getInt(R$styleable.IFocusArea_nudgeShortcutDirection, -1);
            boolean z4 = resourceId == -1;
            if (i5 != -1) {
                z = false;
            }
            if (z ^ z4) {
                throw new IllegalStateException("nudgeShortcut and nudgeShortcutDirection must be specified together");
            }
            if (resourceId != -1) {
                if (sparseIntArray.size() > 0) {
                    throw new IllegalStateException("Don't use nudgeShortcut/nudgeShortcutDirection and nudge*Shortcut in the same focus area. Use nudge*Shortcut only.");
                }
                sparseIntArray.put(i5, resourceId);
            }
            int i6 = R$styleable.IFocusArea_nudgeLeft;
            if (typedArrayObtainStyledAttributes.hasValue(i6)) {
                sparseIntArray2.put(17, typedArrayObtainStyledAttributes.getResourceId(i6, -1));
            }
            int i7 = R$styleable.IFocusArea_nudgeRight;
            if (typedArrayObtainStyledAttributes.hasValue(i7)) {
                sparseIntArray2.put(66, typedArrayObtainStyledAttributes.getResourceId(i7, -1));
            }
            int i8 = R$styleable.IFocusArea_nudgeUp;
            if (typedArrayObtainStyledAttributes.hasValue(i8)) {
                sparseIntArray2.put(33, typedArrayObtainStyledAttributes.getResourceId(i8, -1));
            }
            int i9 = R$styleable.IFocusArea_nudgeDown;
            if (typedArrayObtainStyledAttributes.hasValue(i9)) {
                sparseIntArray2.put(130, typedArrayObtainStyledAttributes.getResourceId(i9, -1));
            }
            this.r = typedArrayObtainStyledAttributes.getBoolean(R$styleable.IFocusArea_defaultFocusOverridesHistory, false);
            this.w = typedArrayObtainStyledAttributes.getBoolean(R$styleable.IFocusArea_wrapAround, false);
        } finally {
            typedArrayObtainStyledAttributes.recycle();
        }
    }

    public static int c(Bundle bundle) {
        if (bundle == null) {
            return -1;
        }
        return bundle.getInt("com.android.car.ui.utils.NUDGE_DIRECTION", -1);
    }

    public final void a(Canvas canvas) {
        if (this.c && this.b && !this.a.isInTouchMode()) {
            this.e.setBounds(this.a.getScrollX() + this.g, this.a.getScrollY() + this.i, (this.a.getWidth() + this.a.getScrollX()) - this.h, (this.a.getHeight() + this.a.getScrollY()) - this.j);
            this.e.draw(canvas);
        }
    }

    public final boolean b() {
        com.android.car.ui.rotary.a aVar = this.x;
        return yc.a(this.a, 1, aVar.a.a(SystemClock.uptimeMillis()), this.r, true);
    }

    public final boolean d(int i) {
        return E.contains(Integer.valueOf(i));
    }

    public final boolean e() {
        View rootView = this.a.getRootView();
        return yc.a(rootView, yc.i(rootView.findFocus()), null, false, true);
    }

    public final void f() {
        if (this.t != null) {
            return;
        }
        View rootView = this.a.getRootView();
        this.t = new SparseArray<>();
        Iterator<Integer> it = D.iterator();
        while (it.hasNext()) {
            int iIntValue = it.next().intValue();
            this.t.put(iIntValue, rootView.findViewById(this.s.get(iIntValue, -1)));
        }
    }

    public final void g() {
        this.a.getViewTreeObserver().addOnGlobalFocusChangeListener(this.B);
        if (this.C) {
            Activity activityD = yc.d(this.a.getContext());
            if (activityD != null && activityD.isInMultiWindowMode()) {
                this.C = false;
            }
        }
    }

    public final void h() {
        this.a.getViewTreeObserver().removeOnGlobalFocusChangeListener(this.B);
    }

    public final void i(Canvas canvas) {
        if (this.d && this.b && !this.a.isInTouchMode()) {
            this.f.setBounds(this.a.getScrollX() + this.g, this.a.getScrollY() + this.i, (this.a.getWidth() + this.a.getScrollX()) - this.h, (this.a.getHeight() + this.a.getScrollY()) - this.j);
            this.f.draw(canvas);
        }
    }

    public final void j() {
        int i = this.p;
        if (i != -1) {
            this.q = this.a.requireViewById(i);
        }
    }

    public final void k(AccessibilityNodeInfo accessibilityNodeInfo) {
        Bundle extras = accessibilityNodeInfo.getExtras();
        extras.putInt("com.android.car.ui.utils.FOCUS_AREA_LEFT_BOUND_OFFSET", this.k);
        extras.putInt("com.android.car.ui.utils.FOCUS_AREA_RIGHT_BOUND_OFFSET", this.l);
        extras.putInt("com.android.car.ui.utils.FOCUS_AREA_TOP_BOUND_OFFSET", this.m);
        extras.putInt("com.android.car.ui.utils.FOCUS_AREA_BOTTOM_BOUND_OFFSET", this.n);
    }

    public final void l() {
        boolean z = this.a.getLayoutDirection() == 1;
        if (this.o != z) {
            this.o = z;
            int i = this.g;
            this.g = this.h;
            this.h = i;
            int i2 = this.k;
            this.k = this.l;
            this.l = i2;
        }
    }

    public final boolean m() {
        if (this.C) {
            return e();
        }
        return false;
    }

    public final boolean n(boolean z) {
        StringBuilder sbK = c.k("The window of Activity [");
        sbK.append(yc.d(this.a.getContext()));
        sbK.append(z ? "] gained" : "] lost");
        sbK.append(" focus");
        Log.d("FocusAreaHelper", sbK.toString());
        if (!z || !this.C || this.a.isInTouchMode()) {
            return false;
        }
        View rootView = this.a.getRootView();
        int i = yc.i(rootView.findFocus());
        if (i < 3) {
            yc.a(rootView, i, null, false, true);
        }
        return true;
    }

    public final boolean o(int i, Bundle bundle) {
        int iC;
        if (i == 1) {
            boolean zB = b();
            if (zB && this.z != null && (iC = c(bundle)) != -1) {
                o4 o4Var = this.z;
                o4 o4Var2 = (o4) this.a;
                long jUptimeMillis = SystemClock.uptimeMillis();
                if (o4Var.getHelper().x.a(iC, jUptimeMillis) == null) {
                    int i2 = 66;
                    if (iC != 17) {
                        if (iC == 33) {
                            i2 = 130;
                        } else if (iC == 66) {
                            i2 = 17;
                        } else {
                            if (iC != 130) {
                                throw new IllegalArgumentException("direction must be FOCUS_UP, FOCUS_DOWN, FOCUS_LEFT, or FOCUS_RIGHT.");
                            }
                            i2 = 33;
                        }
                    }
                    a.C0012a c0012a = o4Var2.getHelper().x.b;
                    if (c0012a.a != 1) {
                        c0012a.put(Integer.valueOf(i2), new a.b(o4Var, jUptimeMillis));
                    }
                }
            }
            return zB;
        }
        if (i == 16777216) {
            int iC2 = c(bundle);
            f();
            View view = this.t.get(iC2);
            if (view == null || view.isFocused()) {
                return false;
            }
            return yc.o(view);
        }
        if (i != 33554432) {
            return false;
        }
        int iC3 = c(bundle);
        long jUptimeMillis2 = SystemClock.uptimeMillis();
        if (this.v == null) {
            View rootView = this.a.getRootView();
            this.v = new SparseArray<>();
            Iterator<Integer> it = D.iterator();
            while (it.hasNext()) {
                int iIntValue = it.next().intValue();
                this.v.put(iIntValue, (o4) rootView.findViewById(this.u.get(iIntValue, -1)));
            }
        }
        o4 o4Var3 = this.v.get(iC3);
        boolean z = o4Var3 != null && o4Var3.getHelper().b();
        if (z) {
            return z;
        }
        o4 o4VarA = this.x.a(iC3, jUptimeMillis2);
        return o4VarA != null && o4VarA.getHelper().b();
    }

    public final boolean p() {
        if (this.C) {
            return e();
        }
        return false;
    }
}
