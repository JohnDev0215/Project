package com.android.car.ui.rotary;

import android.content.Context;
import android.util.AttributeSet;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewParent;
import android.view.ViewTreeObserver;
import android.widget.FrameLayout;
import com.android.car.ui.rotary.NestedContainer;
import spi.n7;
import spi.t7;
import spi.tc;
import spi.yc;

/* JADX INFO: loaded from: classes2.dex */
public class NestedContainer extends FrameLayout {
    public boolean a;
    public final t7 b;

    /* JADX WARN: Type inference failed for: r2v1, types: [spi.t7] */
    public NestedContainer(Context context) {
        super(context);
        final int i = 3;
        this.b = new ViewTreeObserver.OnGlobalFocusChangeListener(this) { // from class: spi.t7
            public final /* synthetic */ NestedContainer b;

            {
                this.b = this;
            }

            /* JADX WARN: Failed to find 'out' block for switch in B:3:0x0002. Please report as an issue. */
            @Override // android.view.ViewTreeObserver.OnGlobalFocusChangeListener
            public final void onGlobalFocusChanged(View view, View view2) {
                switch (i) {
                }
                NestedContainer.a(this.b);
            }
        };
        b();
    }

    public static /* synthetic */ void a(NestedContainer nestedContainer) {
        if (nestedContainer.a) {
            if (nestedContainer.isFocused() || !nestedContainer.hasFocus()) {
                nestedContainer.a = false;
                nestedContainer.setDescendantFocusability(393216);
            }
        }
    }

    public final void b() {
        setDescendantFocusability(393216);
        setFocusable(true);
    }

    @Override // android.view.ViewGroup, android.view.View
    public final boolean dispatchKeyEvent(KeyEvent keyEvent) {
        if (super.dispatchKeyEvent(keyEvent)) {
            return true;
        }
        if (keyEvent.getKeyCode() == 23 && !this.a) {
            if (keyEvent.getAction() == 1) {
                setDescendantFocusability(262144);
                if (yc.c(this, new tc(this, 0), n7.j) != null) {
                    this.a = true;
                } else {
                    setDescendantFocusability(393216);
                }
            }
            return true;
        }
        if (keyEvent.getKeyCode() != 4 || !this.a) {
            return false;
        }
        if (keyEvent.getAction() == 1) {
            setDescendantFocusability(393216);
            requestFocus();
            this.a = false;
        }
        return true;
    }

    @Override // android.view.ViewGroup, android.view.ViewParent
    public final View focusSearch(View view, int i) {
        View viewFocusSearch = super.focusSearch(view, i);
        if (!this.a) {
            return viewFocusSearch;
        }
        boolean z = false;
        if (viewFocusSearch != null) {
            ViewParent parent = viewFocusSearch.getParent();
            while (true) {
                if (parent == null) {
                    break;
                }
                if (parent == this) {
                    z = true;
                    break;
                }
                parent = parent.getParent();
            }
        }
        if (z) {
            return viewFocusSearch;
        }
        return null;
    }

    @Override // android.view.ViewGroup, android.view.View
    public final void onAttachedToWindow() {
        super.onAttachedToWindow();
        getViewTreeObserver().addOnGlobalFocusChangeListener(this.b);
    }

    @Override // android.view.ViewGroup, android.view.View
    public final void onDetachedFromWindow() {
        this.a = false;
        getViewTreeObserver().removeOnGlobalFocusChangeListener(this.b);
        super.onDetachedFromWindow();
    }

    /* JADX WARN: Type inference failed for: r1v1, types: [spi.t7] */
    public NestedContainer(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        final int i = 1;
        this.b = new ViewTreeObserver.OnGlobalFocusChangeListener(this) { // from class: spi.t7
            public final /* synthetic */ NestedContainer b;

            {
                this.b = this;
            }

            /* JADX WARN: Failed to find 'out' block for switch in B:3:0x0002. Please report as an issue. */
            @Override // android.view.ViewTreeObserver.OnGlobalFocusChangeListener
            public final void onGlobalFocusChanged(View view, View view2) {
                switch (i) {
                }
                NestedContainer.a(this.b);
            }
        };
        b();
    }

    /* JADX WARN: Type inference failed for: r1v1, types: [spi.t7] */
    public NestedContainer(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        final int i2 = 0;
        this.b = new ViewTreeObserver.OnGlobalFocusChangeListener(this) { // from class: spi.t7
            public final /* synthetic */ NestedContainer b;

            {
                this.b = this;
            }

            /* JADX WARN: Failed to find 'out' block for switch in B:3:0x0002. Please report as an issue. */
            @Override // android.view.ViewTreeObserver.OnGlobalFocusChangeListener
            public final void onGlobalFocusChanged(View view, View view2) {
                switch (i2) {
                }
                NestedContainer.a(this.b);
            }
        };
        b();
    }

    /* JADX WARN: Type inference failed for: r1v1, types: [spi.t7] */
    public NestedContainer(Context context, AttributeSet attributeSet, int i, int i2) {
        super(context, attributeSet, i, i2);
        final int i3 = 2;
        this.b = new ViewTreeObserver.OnGlobalFocusChangeListener(this) { // from class: spi.t7
            public final /* synthetic */ NestedContainer b;

            {
                this.b = this;
            }

            /* JADX WARN: Failed to find 'out' block for switch in B:3:0x0002. Please report as an issue. */
            @Override // android.view.ViewTreeObserver.OnGlobalFocusChangeListener
            public final void onGlobalFocusChanged(View view, View view2) {
                switch (i3) {
                }
                NestedContainer.a(this.b);
            }
        };
        b();
    }
}
