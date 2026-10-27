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
    protected final C0006a f405b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    protected final Context f406c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    protected ActionMenuView f407d;
    protected ActionMenuPresenter e;
    protected int f;
    protected b.g.k.x g;
    private boolean h;
    private boolean i;

    /* JADX INFO: renamed from: androidx.appcompat.widget.a$a, reason: collision with other inner class name */
    /* JADX INFO: compiled from: AbsActionBarView.java */
    protected class C0006a implements b.g.k.y {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        private boolean f408a = false;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        int f409b;

        protected C0006a() {
        }

        @Override // b.g.k.y
        public void a(View view) {
            if (this.f408a) {
                return;
            }
            a aVar = a.this;
            aVar.g = null;
            a.super.setVisibility(this.f409b);
        }

        @Override // b.g.k.y
        public void b(View view) {
            a.super.setVisibility(0);
            this.f408a = false;
        }

        @Override // b.g.k.y
        public void c(View view) {
            this.f408a = true;
        }

        public C0006a d(b.g.k.x xVar, int i) {
            a.this.g = xVar;
            this.f409b = i;
            return this;
        }
    }

    a(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    protected static int d(int i, int i2, boolean z) {
        return z ? i - i2 : i + i2;
    }

    protected int c(View view, int i, int i2, int i3) {
        view.measure(View.MeasureSpec.makeMeasureSpec(i, Integer.MIN_VALUE), i2);
        return Math.max(0, (i - view.getMeasuredWidth()) - i3);
    }

    protected int e(View view, int i, int i2, int i3, boolean z) {
        int measuredWidth = view.getMeasuredWidth();
        int measuredHeight = view.getMeasuredHeight();
        int i4 = i2 + ((i3 - measuredHeight) / 2);
        if (z) {
            view.layout(i - measuredWidth, i4, i, measuredHeight + i4);
        } else {
            view.layout(i, i4, i + measuredWidth, measuredHeight + i4);
        }
        return z ? -measuredWidth : measuredWidth;
    }

    public b.g.k.x f(int i, long j) {
        b.g.k.x xVar = this.g;
        if (xVar != null) {
            xVar.b();
        }
        if (i != 0) {
            b.g.k.x xVarA = b.g.k.t.c(this).a(0.0f);
            xVarA.d(j);
            xVarA.f(this.f405b.d(xVarA, i));
            return xVarA;
        }
        if (getVisibility() != 0) {
            setAlpha(0.0f);
        }
        b.g.k.x xVarA2 = b.g.k.t.c(this).a(1.0f);
        xVarA2.d(j);
        xVarA2.f(this.f405b.d(xVarA2, i));
        return xVarA2;
    }

    public int getAnimatedVisibility() {
        return this.g != null ? this.f405b.f409b : getVisibility();
    }

    public int getContentHeight() {
        return this.f;
    }

    @Override // android.view.View
    protected void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
        TypedArray typedArrayObtainStyledAttributes = getContext().obtainStyledAttributes(null, b.a.j.f1431a, b.a.a.f1400c, 0);
        setContentHeight(typedArrayObtainStyledAttributes.getLayoutDimension(b.a.j.j, 0));
        typedArrayObtainStyledAttributes.recycle();
        ActionMenuPresenter actionMenuPresenter = this.e;
        if (actionMenuPresenter != null) {
            actionMenuPresenter.J(configuration);
        }
    }

    @Override // android.view.View
    public boolean onHoverEvent(MotionEvent motionEvent) {
        int actionMasked = motionEvent.getActionMasked();
        if (actionMasked == 9) {
            this.i = false;
        }
        if (!this.i) {
            boolean zOnHoverEvent = super.onHoverEvent(motionEvent);
            if (actionMasked == 9 && !zOnHoverEvent) {
                this.i = true;
            }
        }
        if (actionMasked == 10 || actionMasked == 3) {
            this.i = false;
        }
        return true;
    }

    @Override // android.view.View
    public boolean onTouchEvent(MotionEvent motionEvent) {
        int actionMasked = motionEvent.getActionMasked();
        if (actionMasked == 0) {
            this.h = false;
        }
        if (!this.h) {
            boolean zOnTouchEvent = super.onTouchEvent(motionEvent);
            if (actionMasked == 0 && !zOnTouchEvent) {
                this.h = true;
            }
        }
        if (actionMasked == 1 || actionMasked == 3) {
            this.h = false;
        }
        return true;
    }

    public abstract void setContentHeight(int i);

    @Override // android.view.View
    public void setVisibility(int i) {
        if (i != getVisibility()) {
            b.g.k.x xVar = this.g;
            if (xVar != null) {
                xVar.b();
            }
            super.setVisibility(i);
        }
    }

    a(Context context, AttributeSet attributeSet, int i) {
        int i2;
        super(context, attributeSet, i);
        this.f405b = new C0006a();
        TypedValue typedValue = new TypedValue();
        if (!context.getTheme().resolveAttribute(b.a.a.f1398a, typedValue, true) || (i2 = typedValue.resourceId) == 0) {
            this.f406c = context;
        } else {
            this.f406c = new ContextThemeWrapper(context, i2);
        }
    }
}
