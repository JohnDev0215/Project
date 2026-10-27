package androidx.appcompat.widget;

import android.content.Context;
import android.content.res.Configuration;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.ContextThemeWrapper;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import androidx.appcompat.R;
import androidx.appcompat.view.ActionBarPolicy;
import androidx.appcompat.view.menu.MenuBuilder;
import defpackage.in3;
import defpackage.jn3;

/* JADX INFO: loaded from: classes.dex */
abstract class AbsActionBarView extends ViewGroup {
    public final VisibilityAnimListener e;
    public final Context f;
    public ActionMenuView g;
    public ActionMenuPresenter h;
    public int i;
    public in3 j;
    public boolean k;
    public boolean l;

    /* JADX INFO: renamed from: androidx.appcompat.widget.AbsActionBarView$1, reason: invalid class name */
    class AnonymousClass1 implements Runnable {
        @Override // java.lang.Runnable
        public final void run() {
            throw null;
        }
    }

    public class VisibilityAnimListener implements jn3 {
        public boolean a = false;
        public int b;

        public VisibilityAnimListener() {
        }

        @Override // defpackage.jn3
        public final void a() {
            if (this.a) {
                return;
            }
            AbsActionBarView absActionBarView = AbsActionBarView.this;
            absActionBarView.j = null;
            AbsActionBarView.super.setVisibility(this.b);
        }

        @Override // defpackage.jn3
        public final void b(View view) {
            this.a = true;
        }

        @Override // defpackage.jn3
        public final void c() {
            AbsActionBarView.super.setVisibility(0);
            this.a = false;
        }
    }

    public AbsActionBarView(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public static int c(View view, int i, int i2) {
        view.measure(View.MeasureSpec.makeMeasureSpec(i, Integer.MIN_VALUE), i2);
        return Math.max(0, (i - view.getMeasuredWidth()) - 0);
    }

    public static int d(int i, int i2, int i3, View view, boolean z) {
        int measuredWidth = view.getMeasuredWidth();
        int measuredHeight = view.getMeasuredHeight();
        int i4 = ((i3 - measuredHeight) / 2) + i2;
        if (z) {
            view.layout(i - measuredWidth, i4, i, measuredHeight + i4);
        } else {
            view.layout(i, i4, i + measuredWidth, measuredHeight + i4);
        }
        return z ? -measuredWidth : measuredWidth;
    }

    public int getAnimatedVisibility() {
        return this.j != null ? this.e.b : getVisibility();
    }

    public int getContentHeight() {
        return this.i;
    }

    @Override // android.view.View
    public final void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
        TypedArray typedArrayObtainStyledAttributes = getContext().obtainStyledAttributes(null, R.styleable.ActionBar, R.attr.actionBarStyle, 0);
        setContentHeight(typedArrayObtainStyledAttributes.getLayoutDimension(R.styleable.ActionBar_height, 0));
        typedArrayObtainStyledAttributes.recycle();
        ActionMenuPresenter actionMenuPresenter = this.h;
        if (actionMenuPresenter != null) {
            actionMenuPresenter.u = new ActionBarPolicy(actionMenuPresenter.f).a();
            MenuBuilder menuBuilder = actionMenuPresenter.g;
            if (menuBuilder != null) {
                menuBuilder.p(true);
            }
        }
    }

    @Override // android.view.View
    public final boolean onHoverEvent(MotionEvent motionEvent) {
        int actionMasked = motionEvent.getActionMasked();
        if (actionMasked == 9) {
            this.l = false;
        }
        if (!this.l) {
            boolean zOnHoverEvent = super.onHoverEvent(motionEvent);
            if (actionMasked == 9 && !zOnHoverEvent) {
                this.l = true;
            }
        }
        if (actionMasked == 10 || actionMasked == 3) {
            this.l = false;
        }
        return true;
    }

    @Override // android.view.View
    public final boolean onTouchEvent(MotionEvent motionEvent) {
        int actionMasked = motionEvent.getActionMasked();
        if (actionMasked == 0) {
            this.k = false;
        }
        if (!this.k) {
            boolean zOnTouchEvent = super.onTouchEvent(motionEvent);
            if (actionMasked == 0 && !zOnTouchEvent) {
                this.k = true;
            }
        }
        if (actionMasked == 1 || actionMasked == 3) {
            this.k = false;
        }
        return true;
    }

    public void setContentHeight(int i) {
        this.i = i;
        requestLayout();
    }

    @Override // android.view.View
    public void setVisibility(int i) {
        if (i != getVisibility()) {
            in3 in3Var = this.j;
            if (in3Var != null) {
                in3Var.b();
            }
            super.setVisibility(i);
        }
    }

    public AbsActionBarView(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.e = new VisibilityAnimListener();
        TypedValue typedValue = new TypedValue();
        if (!context.getTheme().resolveAttribute(R.attr.actionBarPopupTheme, typedValue, true) || typedValue.resourceId == 0) {
            this.f = context;
        } else {
            this.f = new ContextThemeWrapper(context, typedValue.resourceId);
        }
    }
}
