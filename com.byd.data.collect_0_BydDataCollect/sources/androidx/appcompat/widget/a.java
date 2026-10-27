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

/* JADX INFO: compiled from: AbsActionBarView.java */
/* JADX INFO: loaded from: classes.dex */
abstract class a extends ViewGroup {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    protected final C0007a f566b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    protected final Context f567c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    protected ActionMenuView f568d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    protected ActionMenuPresenter f569e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    protected int f570f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    protected b.g.k.x f571g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    private boolean f572h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    private boolean f573i;

    /* JADX INFO: renamed from: androidx.appcompat.widget.a$a, reason: collision with other inner class name */
    /* JADX INFO: compiled from: AbsActionBarView.java */
    protected class C0007a implements b.g.k.y {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        private boolean f574a = false;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        int f575b;

        protected C0007a() {
        }

        @Override // b.g.k.y
        public void a(View view) {
            if (this.f574a) {
                return;
            }
            a aVar = a.this;
            aVar.f571g = null;
            a.super.setVisibility(this.f575b);
        }

        @Override // b.g.k.y
        public void b(View view) {
            a.super.setVisibility(0);
            this.f574a = false;
        }

        @Override // b.g.k.y
        public void c(View view) {
            this.f574a = true;
        }

        public C0007a d(b.g.k.x xVar, int i2) {
            a.this.f571g = xVar;
            this.f575b = i2;
            return this;
        }
    }

    a(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    protected static int d(int i2, int i3, boolean z) {
        return z ? i2 - i3 : i2 + i3;
    }

    protected int c(View view, int i2, int i3, int i4) {
        view.measure(View.MeasureSpec.makeMeasureSpec(i2, Integer.MIN_VALUE), i3);
        return Math.max(0, (i2 - view.getMeasuredWidth()) - i4);
    }

    protected int e(View view, int i2, int i3, int i4, boolean z) {
        int measuredWidth = view.getMeasuredWidth();
        int measuredHeight = view.getMeasuredHeight();
        int i5 = i3 + ((i4 - measuredHeight) / 2);
        if (z) {
            view.layout(i2 - measuredWidth, i5, i2, measuredHeight + i5);
        } else {
            view.layout(i2, i5, i2 + measuredWidth, measuredHeight + i5);
        }
        return z ? -measuredWidth : measuredWidth;
    }

    public b.g.k.x f(int i2, long j) {
        b.g.k.x xVar = this.f571g;
        if (xVar != null) {
            xVar.b();
        }
        if (i2 != 0) {
            b.g.k.x xVarA = b.g.k.t.c(this).a(0.0f);
            xVarA.d(j);
            xVarA.f(this.f566b.d(xVarA, i2));
            return xVarA;
        }
        if (getVisibility() != 0) {
            setAlpha(0.0f);
        }
        b.g.k.x xVarA2 = b.g.k.t.c(this).a(1.0f);
        xVarA2.d(j);
        xVarA2.f(this.f566b.d(xVarA2, i2));
        return xVarA2;
    }

    public int getAnimatedVisibility() {
        return this.f571g != null ? this.f566b.f575b : getVisibility();
    }

    public int getContentHeight() {
        return this.f570f;
    }

    @Override // android.view.View
    protected void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
        TypedArray typedArrayObtainStyledAttributes = getContext().obtainStyledAttributes(null, b.a.j.ActionBar, b.a.a.actionBarStyle, 0);
        setContentHeight(typedArrayObtainStyledAttributes.getLayoutDimension(b.a.j.ActionBar_height, 0));
        typedArrayObtainStyledAttributes.recycle();
        ActionMenuPresenter actionMenuPresenter = this.f569e;
        if (actionMenuPresenter != null) {
            actionMenuPresenter.J(configuration);
        }
    }

    @Override // android.view.View
    public boolean onHoverEvent(MotionEvent motionEvent) {
        int actionMasked = motionEvent.getActionMasked();
        if (actionMasked == 9) {
            this.f573i = false;
        }
        if (!this.f573i) {
            boolean zOnHoverEvent = super.onHoverEvent(motionEvent);
            if (actionMasked == 9 && !zOnHoverEvent) {
                this.f573i = true;
            }
        }
        if (actionMasked == 10 || actionMasked == 3) {
            this.f573i = false;
        }
        return true;
    }

    @Override // android.view.View
    public boolean onTouchEvent(MotionEvent motionEvent) {
        int actionMasked = motionEvent.getActionMasked();
        if (actionMasked == 0) {
            this.f572h = false;
        }
        if (!this.f572h) {
            boolean zOnTouchEvent = super.onTouchEvent(motionEvent);
            if (actionMasked == 0 && !zOnTouchEvent) {
                this.f572h = true;
            }
        }
        if (actionMasked == 1 || actionMasked == 3) {
            this.f572h = false;
        }
        return true;
    }

    public abstract void setContentHeight(int i2);

    @Override // android.view.View
    public void setVisibility(int i2) {
        if (i2 != getVisibility()) {
            b.g.k.x xVar = this.f571g;
            if (xVar != null) {
                xVar.b();
            }
            super.setVisibility(i2);
        }
    }

    a(Context context, AttributeSet attributeSet, int i2) {
        int i3;
        super(context, attributeSet, i2);
        this.f566b = new C0007a();
        TypedValue typedValue = new TypedValue();
        if (!context.getTheme().resolveAttribute(b.a.a.actionBarPopupTheme, typedValue, true) || (i3 = typedValue.resourceId) == 0) {
            this.f567c = context;
        } else {
            this.f567c = new ContextThemeWrapper(context, i3);
        }
    }
}
