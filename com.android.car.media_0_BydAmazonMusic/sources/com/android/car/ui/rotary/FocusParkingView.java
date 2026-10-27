package com.android.car.ui.rotary;

import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Rect;
import android.os.Bundle;
import android.os.SystemClock;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.view.ViewTreeObserver;
import android.view.inputmethod.InputMethodManager;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;
import com.android.car.ui.R$bool;
import com.android.car.ui.R$integer;
import com.android.car.ui.R$styleable;
import com.android.car.ui.rotary.FocusParkingView;
import com.android.car.ui.rotary.a;
import java.util.Objects;
import spi.a4;
import spi.b4;
import spi.c;
import spi.o4;
import spi.yc;

/* JADX INFO: loaded from: classes2.dex */
public class FocusParkingView extends View {

    @Nullable
    public a a;

    @Nullable
    public View b;
    public a.c c;

    @Nullable
    public ViewGroup d;
    public boolean e;
    public boolean f;
    public final a4 g;

    public interface a {
        void onDismiss();
    }

    /* JADX WARN: Type inference failed for: r0v1, types: [spi.a4] */
    public FocusParkingView(Context context) {
        super(context);
        this.e = true;
        final int i = 3;
        this.g = new ViewTreeObserver.OnGlobalFocusChangeListener(this) { // from class: spi.a4
            public final /* synthetic */ FocusParkingView b;

            {
                this.b = this;
            }

            /* JADX WARN: Failed to find 'out' block for switch in B:3:0x0002. Please report as an issue. */
            @Override // android.view.ViewTreeObserver.OnGlobalFocusChangeListener
            public final void onGlobalFocusChanged(View view, View view2) {
                switch (i) {
                }
                FocusParkingView.a(this.b, view2);
            }
        };
        b(context, null);
    }

    public static /* synthetic */ void a(FocusParkingView focusParkingView, View view) {
        Objects.requireNonNull(focusParkingView);
        if (view instanceof FocusParkingView) {
            view = null;
        }
        focusParkingView.d(view);
    }

    public final void b(Context context, @Nullable AttributeSet attributeSet) {
        if (attributeSet != null) {
            this.e = context.obtainStyledAttributes(attributeSet, R$styleable.FocusParkingView).getBoolean(R$styleable.FocusParkingView_shouldRestoreFocus, true);
        }
        setFocusable(1);
        setVisibility(0);
        setEnabled(true);
        setClickable(false);
        setAlpha(0.0f);
        setDefaultFocusHighlightEnabled(false);
        Resources resources = getResources();
        this.c = new a.c(resources.getInteger(R$integer.car_ui_focus_history_cache_type), resources.getInteger(R$integer.car_ui_focus_history_expiration_period_ms));
        this.f = resources.getBoolean(R$bool.car_ui_focus_area_default_focus_overrides_history);
    }

    public final boolean c(boolean z) {
        ViewGroup viewGroup;
        boolean z2 = false;
        if (z && isInTouchMode()) {
            return false;
        }
        View view = this.b;
        if (view != null && view.isFocused() && this.b.isAttachedToWindow()) {
            return true;
        }
        View view2 = this.b;
        if (view2 != null && !view2.isAttachedToWindow() && this.b.getParent() != null && (viewGroup = this.d) != null && viewGroup.isAttachedToWindow() && this.d.isShown()) {
            ViewGroup viewGroup2 = this.d;
            RecyclerView recyclerView = viewGroup2 instanceof RecyclerView ? (RecyclerView) viewGroup2 : null;
            if (viewGroup2.requestFocus()) {
                if (recyclerView != null && recyclerView.isComputingLayout()) {
                    getViewTreeObserver().addOnGlobalLayoutListener(new b4(this, recyclerView));
                }
                z2 = true;
            }
        }
        if (z2) {
            return true;
        }
        if (yc.a(getRootView(), 1, this.c.a(SystemClock.uptimeMillis()), this.f, true)) {
            return true;
        }
        return super.requestFocus(130, null);
    }

    public final void d(@Nullable View view) {
        View view2 = this.b;
        ViewGroup viewGroup = null;
        if (view2 != null) {
            a.c cVar = this.c;
            long jUptimeMillis = SystemClock.uptimeMillis();
            if (cVar.a != 1) {
                cVar.c = view2 != null ? new a.d(view2, jUptimeMillis) : null;
            }
        }
        this.b = view;
        if (view != null) {
            ViewParent parent = view.getParent();
            while (true) {
                if (parent == null || !(parent instanceof ViewGroup) || (parent instanceof o4)) {
                    break;
                }
                ViewGroup viewGroup2 = (ViewGroup) parent;
                if (yc.m(viewGroup2)) {
                    viewGroup = viewGroup2;
                    break;
                }
                parent = parent.getParent();
            }
        }
        this.d = viewGroup;
    }

    @Override // android.view.View
    public CharSequence getAccessibilityClassName() {
        return FocusParkingView.class.getName();
    }

    @Override // android.view.View
    public final void onAttachedToWindow() {
        super.onAttachedToWindow();
        getViewTreeObserver().addOnGlobalFocusChangeListener(this.g);
        if (this.e) {
            Activity activityD = yc.d(getContext());
            if (activityD != null && activityD.isInMultiWindowMode()) {
                this.e = false;
            }
        }
    }

    @Override // android.view.View
    public final void onDetachedFromWindow() {
        getViewTreeObserver().removeOnGlobalFocusChangeListener(this.g);
        super.onDetachedFromWindow();
    }

    @Override // android.view.View
    public final void onMeasure(int i, int i2) {
        setMeasuredDimension(1, 1);
    }

    @Override // android.view.View
    public final void onWindowFocusChanged(boolean z) {
        StringBuilder sbK = c.k("The window of Activity [");
        sbK.append(yc.d(getContext()));
        sbK.append(z ? "] gained" : "] lost");
        sbK.append(" focus");
        Log.d("FocusParkingView", sbK.toString());
        if (!z) {
            super.requestFocus(130, null);
            d(null);
        } else if (isFocused() && this.e) {
            c(true);
        }
        super.onWindowFocusChanged(z);
    }

    @Override // android.view.View
    public final boolean performAccessibilityAction(int i, Bundle bundle) {
        if (i == 1) {
            if (hasFocus()) {
                return false;
            }
            return super.requestFocus(130, null);
        }
        if (i == 67108864) {
            return c(false);
        }
        if (i == 134217728) {
            return ((InputMethodManager) getContext().getSystemService(InputMethodManager.class)).hideSoftInputFromWindow(getWindowToken(), 0);
        }
        if (i != 268435456) {
            return super.performAccessibilityAction(i, bundle);
        }
        a aVar = this.a;
        if (aVar == null) {
            return false;
        }
        aVar.onDismiss();
        return true;
    }

    @Override // android.view.View
    public final boolean requestFocus(int i, Rect rect) {
        return !this.e ? super.requestFocus(i, rect) : c(true);
    }

    @Override // android.view.View
    public final boolean restoreDefaultFocus() {
        return !this.e ? super.restoreDefaultFocus() : c(true);
    }

    public void setOnPopupWindowDismiss(@Nullable a aVar) {
        this.a = aVar;
    }

    public void setShouldRestoreFocus(boolean z) {
        this.e = z;
    }

    /* JADX WARN: Type inference failed for: r0v1, types: [spi.a4] */
    public FocusParkingView(Context context, @Nullable AttributeSet attributeSet) {
        super(context, attributeSet);
        this.e = true;
        final int i = 2;
        this.g = new ViewTreeObserver.OnGlobalFocusChangeListener(this) { // from class: spi.a4
            public final /* synthetic */ FocusParkingView b;

            {
                this.b = this;
            }

            /* JADX WARN: Failed to find 'out' block for switch in B:3:0x0002. Please report as an issue. */
            @Override // android.view.ViewTreeObserver.OnGlobalFocusChangeListener
            public final void onGlobalFocusChanged(View view, View view2) {
                switch (i) {
                }
                FocusParkingView.a(this.b, view2);
            }
        };
        b(context, attributeSet);
    }

    /* JADX WARN: Type inference failed for: r4v2, types: [spi.a4] */
    public FocusParkingView(Context context, @Nullable AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.e = true;
        final int i2 = 0;
        this.g = new ViewTreeObserver.OnGlobalFocusChangeListener(this) { // from class: spi.a4
            public final /* synthetic */ FocusParkingView b;

            {
                this.b = this;
            }

            /* JADX WARN: Failed to find 'out' block for switch in B:3:0x0002. Please report as an issue. */
            @Override // android.view.ViewTreeObserver.OnGlobalFocusChangeListener
            public final void onGlobalFocusChanged(View view, View view2) {
                switch (i2) {
                }
                FocusParkingView.a(this.b, view2);
            }
        };
        b(context, attributeSet);
    }

    /* JADX WARN: Type inference failed for: r4v1, types: [spi.a4] */
    public FocusParkingView(Context context, @Nullable AttributeSet attributeSet, int i, int i2) {
        super(context, attributeSet, i, i2);
        final int i3 = 1;
        this.e = true;
        this.g = new ViewTreeObserver.OnGlobalFocusChangeListener(this) { // from class: spi.a4
            public final /* synthetic */ FocusParkingView b;

            {
                this.b = this;
            }

            /* JADX WARN: Failed to find 'out' block for switch in B:3:0x0002. Please report as an issue. */
            @Override // android.view.ViewTreeObserver.OnGlobalFocusChangeListener
            public final void onGlobalFocusChanged(View view, View view2) {
                switch (i3) {
                }
                FocusParkingView.a(this.b, view2);
            }
        };
        b(context, attributeSet);
    }
}
