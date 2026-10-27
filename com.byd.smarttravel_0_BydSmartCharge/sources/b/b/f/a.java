package b.b.f;

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
    public final C0006a f737b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final Context f738c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public ActionMenuView f739d;
    public ActionMenuPresenter e;
    public int f;
    public b.g.j.x g;
    public boolean h;
    public boolean i;

    /* JADX INFO: renamed from: b.b.f.a$a, reason: collision with other inner class name */
    /* JADX INFO: compiled from: AbsActionBarView.java */
    public class C0006a implements b.g.j.y {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public boolean f740a = false;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public int f741b;

        public C0006a() {
        }

        @Override // b.g.j.y
        public void a(View view) {
            if (this.f740a) {
                return;
            }
            a aVar = a.this;
            aVar.g = null;
            a.super.setVisibility(this.f741b);
        }

        @Override // b.g.j.y
        public void b(View view) {
            a.super.setVisibility(0);
            this.f740a = false;
        }

        @Override // b.g.j.y
        public void c(View view) {
            this.f740a = true;
        }

        public C0006a d(b.g.j.x xVar, int i) {
            a.this.g = xVar;
            this.f741b = i;
            return this;
        }
    }

    public a(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public static int d(int i, int i2, boolean z) {
        return z ? i - i2 : i + i2;
    }

    public int c(View view, int i, int i2, int i3) {
        view.measure(View.MeasureSpec.makeMeasureSpec(i, Integer.MIN_VALUE), i2);
        return Math.max(0, (i - view.getMeasuredWidth()) - i3);
    }

    public int e(View view, int i, int i2, int i3, boolean z) {
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

    public b.g.j.x f(int i, long j) {
        b.g.j.x xVar = this.g;
        if (xVar != null) {
            xVar.b();
        }
        if (i != 0) {
            b.g.j.x xVarA = b.g.j.t.c(this).a(0.0f);
            xVarA.d(j);
            xVarA.f(this.f737b.d(xVarA, i));
            return xVarA;
        }
        if (getVisibility() != 0) {
            setAlpha(0.0f);
        }
        b.g.j.x xVarA2 = b.g.j.t.c(this).a(1.0f);
        xVarA2.d(j);
        xVarA2.f(this.f737b.d(xVarA2, i));
        return xVarA2;
    }

    public int getAnimatedVisibility() {
        return this.g != null ? this.f737b.f741b : getVisibility();
    }

    public int getContentHeight() {
        return this.f;
    }

    @Override // android.view.View
    public void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
        TypedArray typedArrayObtainStyledAttributes = getContext().obtainStyledAttributes(null, R$styleable.ActionBar, R$attr.actionBarStyle, 0);
        setContentHeight(typedArrayObtainStyledAttributes.getLayoutDimension(R$styleable.ActionBar_height, 0));
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
            b.g.j.x xVar = this.g;
            if (xVar != null) {
                xVar.b();
            }
            super.setVisibility(i);
        }
    }

    public a(Context context, AttributeSet attributeSet, int i) {
        super(context, attributeSet, i);
        this.f737b = new C0006a();
        TypedValue typedValue = new TypedValue();
        if (!context.getTheme().resolveAttribute(R$attr.actionBarPopupTheme, typedValue, true) || typedValue.resourceId == 0) {
            this.f738c = context;
        } else {
            this.f738c = new ContextThemeWrapper(context, typedValue.resourceId);
        }
    }
}
