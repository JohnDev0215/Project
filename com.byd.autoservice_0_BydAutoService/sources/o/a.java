package o;

import android.content.Context;
import android.content.res.Configuration;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.ContextThemeWrapper;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import androidx.appcompat.R$attr;
import androidx.appcompat.R$styleable;
import androidx.appcompat.widget.ActionMenuPresenter;
import androidx.appcompat.widget.ActionMenuView;

/* JADX INFO: compiled from: AbsActionBarView.java */
/* JADX INFO: loaded from: classes.dex */
public abstract class a extends ViewGroup {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final C0042a f4219b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final Context f4220c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public ActionMenuView f4221d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public ActionMenuPresenter f4222e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public int f4223f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public l0.x f4224g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public boolean f4225h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public boolean f4226i;

    /* JADX INFO: renamed from: o.a$a, reason: collision with other inner class name */
    /* JADX INFO: compiled from: AbsActionBarView.java */
    public class C0042a implements l0.y {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public boolean f4227a = false;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public int f4228b;

        public C0042a() {
        }

        @Override // l0.y
        public void a(View view) {
            if (this.f4227a) {
                return;
            }
            a aVar = a.this;
            aVar.f4224g = null;
            a.super.setVisibility(this.f4228b);
        }

        @Override // l0.y
        public void b(View view) {
            a.super.setVisibility(0);
            this.f4227a = false;
        }

        @Override // l0.y
        public void c(View view) {
            this.f4227a = true;
        }

        public C0042a d(l0.x xVar, int i3) {
            a.this.f4224g = xVar;
            this.f4228b = i3;
            return this;
        }
    }

    public a(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public static int d(int i3, int i4, boolean z3) {
        return z3 ? i3 - i4 : i3 + i4;
    }

    public int c(View view, int i3, int i4, int i5) {
        view.measure(View.MeasureSpec.makeMeasureSpec(i3, Integer.MIN_VALUE), i4);
        return Math.max(0, (i3 - view.getMeasuredWidth()) - i5);
    }

    public int e(View view, int i3, int i4, int i5, boolean z3) {
        int measuredWidth = view.getMeasuredWidth();
        int measuredHeight = view.getMeasuredHeight();
        int i6 = i4 + ((i5 - measuredHeight) / 2);
        if (z3) {
            view.layout(i3 - measuredWidth, i6, i3, measuredHeight + i6);
        } else {
            view.layout(i3, i6, i3 + measuredWidth, measuredHeight + i6);
        }
        return z3 ? -measuredWidth : measuredWidth;
    }

    public l0.x f(int i3, long j3) {
        l0.x xVar = this.f4224g;
        if (xVar != null) {
            xVar.b();
        }
        if (i3 != 0) {
            l0.x xVarA = l0.t.c(this).a(0.0f);
            xVarA.d(j3);
            xVarA.f(this.f4219b.d(xVarA, i3));
            return xVarA;
        }
        if (getVisibility() != 0) {
            setAlpha(0.0f);
        }
        l0.x xVarA2 = l0.t.c(this).a(1.0f);
        xVarA2.d(j3);
        xVarA2.f(this.f4219b.d(xVarA2, i3));
        return xVarA2;
    }

    public int getAnimatedVisibility() {
        return this.f4224g != null ? this.f4219b.f4228b : getVisibility();
    }

    public int getContentHeight() {
        return this.f4223f;
    }

    @Override // android.view.View
    public void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
        TypedArray typedArrayObtainStyledAttributes = getContext().obtainStyledAttributes(null, R$styleable.ActionBar, R$attr.actionBarStyle, 0);
        setContentHeight(typedArrayObtainStyledAttributes.getLayoutDimension(R$styleable.ActionBar_height, 0));
        typedArrayObtainStyledAttributes.recycle();
        ActionMenuPresenter actionMenuPresenter = this.f4222e;
        if (actionMenuPresenter != null) {
            actionMenuPresenter.J(configuration);
        }
    }

    @Override // android.view.View
    public boolean onHoverEvent(MotionEvent motionEvent) {
        int actionMasked = motionEvent.getActionMasked();
        if (actionMasked == 9) {
            this.f4226i = false;
        }
        if (!this.f4226i) {
            boolean zOnHoverEvent = super.onHoverEvent(motionEvent);
            if (actionMasked == 9 && !zOnHoverEvent) {
                this.f4226i = true;
            }
        }
        if (actionMasked == 10 || actionMasked == 3) {
            this.f4226i = false;
        }
        return true;
    }

    @Override // android.view.View
    public boolean onTouchEvent(MotionEvent motionEvent) {
        int actionMasked = motionEvent.getActionMasked();
        if (actionMasked == 0) {
            this.f4225h = false;
        }
        if (!this.f4225h) {
            boolean zOnTouchEvent = super.onTouchEvent(motionEvent);
            if (actionMasked == 0 && !zOnTouchEvent) {
                this.f4225h = true;
            }
        }
        if (actionMasked == 1 || actionMasked == 3) {
            this.f4225h = false;
        }
        return true;
    }

    public abstract void setContentHeight(int i3);

    @Override // android.view.View
    public void setVisibility(int i3) {
        if (i3 != getVisibility()) {
            l0.x xVar = this.f4224g;
            if (xVar != null) {
                xVar.b();
            }
            super.setVisibility(i3);
        }
    }

    public a(Context context, AttributeSet attributeSet, int i3) {
        int i4;
        super(context, attributeSet, i3);
        this.f4219b = new C0042a();
        TypedValue typedValue = new TypedValue();
        if (!context.getTheme().resolveAttribute(R$attr.actionBarPopupTheme, typedValue, true) || (i4 = typedValue.resourceId) == 0) {
            this.f4220c = context;
        } else {
            this.f4220c = new ContextThemeWrapper(context, i4);
        }
    }
}
