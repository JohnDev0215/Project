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
    protected final C0007a f557b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    protected final Context f558c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    protected ActionMenuView f559d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    protected ActionMenuPresenter f560e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    protected int f561f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    protected b.g.k.x f562g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    private boolean f563h;
    private boolean i;

    /* JADX INFO: renamed from: androidx.appcompat.widget.a$a, reason: collision with other inner class name */
    /* JADX INFO: compiled from: AbsActionBarView.java */
    protected class C0007a implements b.g.k.y {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        private boolean f564a = false;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        int f565b;

        protected C0007a() {
        }

        @Override // b.g.k.y
        public void a(View view) {
            if (this.f564a) {
                return;
            }
            a aVar = a.this;
            aVar.f562g = null;
            a.super.setVisibility(this.f565b);
        }

        @Override // b.g.k.y
        public void b(View view) {
            a.super.setVisibility(0);
            this.f564a = false;
        }

        @Override // b.g.k.y
        public void c(View view) {
            this.f564a = true;
        }

        public C0007a d(b.g.k.x xVar, int i) {
            a.this.f562g = xVar;
            this.f565b = i;
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
        b.g.k.x xVar = this.f562g;
        if (xVar != null) {
            xVar.b();
        }
        if (i != 0) {
            b.g.k.x xVarA = b.g.k.t.c(this).a(0.0f);
            xVarA.d(j);
            xVarA.f(this.f557b.d(xVarA, i));
            return xVarA;
        }
        if (getVisibility() != 0) {
            setAlpha(0.0f);
        }
        b.g.k.x xVarA2 = b.g.k.t.c(this).a(1.0f);
        xVarA2.d(j);
        xVarA2.f(this.f557b.d(xVarA2, i));
        return xVarA2;
    }

    public int getAnimatedVisibility() {
        return this.f562g != null ? this.f557b.f565b : getVisibility();
    }

    public int getContentHeight() {
        return this.f561f;
    }

    @Override // android.view.View
    protected void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
        TypedArray typedArrayObtainStyledAttributes = getContext().obtainStyledAttributes(null, b.a.j.f2120a, b.a.a.f2060c, 0);
        setContentHeight(typedArrayObtainStyledAttributes.getLayoutDimension(b.a.j.j, 0));
        typedArrayObtainStyledAttributes.recycle();
        ActionMenuPresenter actionMenuPresenter = this.f560e;
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
            this.f563h = false;
        }
        if (!this.f563h) {
            boolean zOnTouchEvent = super.onTouchEvent(motionEvent);
            if (actionMasked == 0 && !zOnTouchEvent) {
                this.f563h = true;
            }
        }
        if (actionMasked == 1 || actionMasked == 3) {
            this.f563h = false;
        }
        return true;
    }

    public abstract void setContentHeight(int i);

    @Override // android.view.View
    public void setVisibility(int i) {
        if (i != getVisibility()) {
            b.g.k.x xVar = this.f562g;
            if (xVar != null) {
                xVar.b();
            }
            super.setVisibility(i);
        }
    }

    a(Context context, AttributeSet attributeSet, int i) {
        int i2;
        super(context, attributeSet, i);
        this.f557b = new C0007a();
        TypedValue typedValue = new TypedValue();
        if (!context.getTheme().resolveAttribute(b.a.a.f2058a, typedValue, true) || (i2 = typedValue.resourceId) == 0) {
            this.f558c = context;
        } else {
            this.f558c = new ContextThemeWrapper(context, i2);
        }
    }
}
