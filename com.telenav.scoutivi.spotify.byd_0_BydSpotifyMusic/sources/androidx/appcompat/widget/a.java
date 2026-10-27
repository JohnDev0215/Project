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

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    protected final C0015a f983e;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    protected final Context f984g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    protected ActionMenuView f985h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    protected ActionMenuPresenter f986i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    protected int f987j;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    protected i0.y f988k;

    /* JADX INFO: renamed from: l, reason: collision with root package name */
    private boolean f989l;

    /* JADX INFO: renamed from: m, reason: collision with root package name */
    private boolean f990m;

    /* JADX INFO: renamed from: androidx.appcompat.widget.a$a, reason: collision with other inner class name */
    /* JADX INFO: compiled from: AbsActionBarView.java */
    protected class C0015a implements i0.z {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        private boolean f991a = false;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        int f992b;

        protected C0015a() {
        }

        @Override // i0.z
        public void a(View view) {
            if (this.f991a) {
                return;
            }
            a aVar = a.this;
            aVar.f988k = null;
            a.super.setVisibility(this.f992b);
        }

        @Override // i0.z
        public void b(View view) {
            a.super.setVisibility(0);
            this.f991a = false;
        }

        @Override // i0.z
        public void c(View view) {
            this.f991a = true;
        }

        public C0015a d(i0.y yVar, int i7) {
            a.this.f988k = yVar;
            this.f992b = i7;
            return this;
        }
    }

    a(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    protected static int d(int i7, int i8, boolean z6) {
        return z6 ? i7 - i8 : i7 + i8;
    }

    protected int c(View view, int i7, int i8, int i9) {
        view.measure(View.MeasureSpec.makeMeasureSpec(i7, Integer.MIN_VALUE), i8);
        return Math.max(0, (i7 - view.getMeasuredWidth()) - i9);
    }

    protected int e(View view, int i7, int i8, int i9, boolean z6) {
        int measuredWidth = view.getMeasuredWidth();
        int measuredHeight = view.getMeasuredHeight();
        int i10 = i8 + ((i9 - measuredHeight) / 2);
        if (z6) {
            view.layout(i7 - measuredWidth, i10, i7, measuredHeight + i10);
        } else {
            view.layout(i7, i10, i7 + measuredWidth, measuredHeight + i10);
        }
        return z6 ? -measuredWidth : measuredWidth;
    }

    public i0.y f(int i7, long j7) {
        i0.y yVar = this.f988k;
        if (yVar != null) {
            yVar.b();
        }
        if (i7 != 0) {
            i0.y yVarA = i0.u.d(this).a(0.0f);
            yVarA.d(j7);
            yVarA.f(this.f983e.d(yVarA, i7));
            return yVarA;
        }
        if (getVisibility() != 0) {
            setAlpha(0.0f);
        }
        i0.y yVarA2 = i0.u.d(this).a(1.0f);
        yVarA2.d(j7);
        yVarA2.f(this.f983e.d(yVarA2, i7));
        return yVarA2;
    }

    public int getAnimatedVisibility() {
        return this.f988k != null ? this.f983e.f992b : getVisibility();
    }

    public int getContentHeight() {
        return this.f987j;
    }

    @Override // android.view.View
    protected void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
        TypedArray typedArrayObtainStyledAttributes = getContext().obtainStyledAttributes(null, e.j.ActionBar, e.a.actionBarStyle, 0);
        setContentHeight(typedArrayObtainStyledAttributes.getLayoutDimension(e.j.ActionBar_height, 0));
        typedArrayObtainStyledAttributes.recycle();
        ActionMenuPresenter actionMenuPresenter = this.f986i;
        if (actionMenuPresenter != null) {
            actionMenuPresenter.F(configuration);
        }
    }

    @Override // android.view.View
    public boolean onHoverEvent(MotionEvent motionEvent) {
        int actionMasked = motionEvent.getActionMasked();
        if (actionMasked == 9) {
            this.f990m = false;
        }
        if (!this.f990m) {
            boolean zOnHoverEvent = super.onHoverEvent(motionEvent);
            if (actionMasked == 9 && !zOnHoverEvent) {
                this.f990m = true;
            }
        }
        if (actionMasked == 10 || actionMasked == 3) {
            this.f990m = false;
        }
        return true;
    }

    @Override // android.view.View
    public boolean onTouchEvent(MotionEvent motionEvent) {
        int actionMasked = motionEvent.getActionMasked();
        if (actionMasked == 0) {
            this.f989l = false;
        }
        if (!this.f989l) {
            boolean zOnTouchEvent = super.onTouchEvent(motionEvent);
            if (actionMasked == 0 && !zOnTouchEvent) {
                this.f989l = true;
            }
        }
        if (actionMasked == 1 || actionMasked == 3) {
            this.f989l = false;
        }
        return true;
    }

    public void setContentHeight(int i7) {
        this.f987j = i7;
        requestLayout();
    }

    @Override // android.view.View
    public void setVisibility(int i7) {
        if (i7 != getVisibility()) {
            i0.y yVar = this.f988k;
            if (yVar != null) {
                yVar.b();
            }
            super.setVisibility(i7);
        }
    }

    a(Context context, AttributeSet attributeSet, int i7) {
        super(context, attributeSet, i7);
        this.f983e = new C0015a();
        TypedValue typedValue = new TypedValue();
        if (!context.getTheme().resolveAttribute(e.a.actionBarPopupTheme, typedValue, true) || typedValue.resourceId == 0) {
            this.f984g = context;
        } else {
            this.f984g = new ContextThemeWrapper(context, typedValue.resourceId);
        }
    }
}
