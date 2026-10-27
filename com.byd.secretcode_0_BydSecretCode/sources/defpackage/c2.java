package defpackage;

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
public abstract class c2 extends ViewGroup {
    public final a b;
    public final Context c;
    public ActionMenuView d;
    public ActionMenuPresenter e;
    public int f;
    public x9 g;
    public boolean h;
    public boolean i;

    /* JADX INFO: compiled from: AbsActionBarView.java */
    public class a implements y9 {
        public boolean a = false;
        public int b;

        public a() {
        }

        @Override // defpackage.y9
        public void a(View view) {
            if (this.a) {
                return;
            }
            c2 c2Var = c2.this;
            c2Var.g = null;
            c2.super.setVisibility(this.b);
        }

        @Override // defpackage.y9
        public void b(View view) {
            c2.super.setVisibility(0);
            this.a = false;
        }

        @Override // defpackage.y9
        public void c(View view) {
            this.a = true;
        }

        public a d(x9 x9Var, int i) {
            c2.this.g = x9Var;
            this.b = i;
            return this;
        }
    }

    public c2(Context context, AttributeSet attributeSet) {
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

    public x9 f(int i, long j) {
        x9 x9Var = this.g;
        if (x9Var != null) {
            x9Var.b();
        }
        if (i != 0) {
            x9 x9VarA = t9.c(this).a(0.0f);
            x9VarA.d(j);
            x9VarA.f(this.b.d(x9VarA, i));
            return x9VarA;
        }
        if (getVisibility() != 0) {
            setAlpha(0.0f);
        }
        x9 x9VarA2 = t9.c(this).a(1.0f);
        x9VarA2.d(j);
        x9VarA2.f(this.b.d(x9VarA2, i));
        return x9VarA2;
    }

    public int getAnimatedVisibility() {
        return this.g != null ? this.b.b : getVisibility();
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
            x9 x9Var = this.g;
            if (x9Var != null) {
                x9Var.b();
            }
            super.setVisibility(i);
        }
    }

    public c2(Context context, AttributeSet attributeSet, int i) {
        int i2;
        super(context, attributeSet, i);
        this.b = new a();
        TypedValue typedValue = new TypedValue();
        if (!context.getTheme().resolveAttribute(R$attr.actionBarPopupTheme, typedValue, true) || (i2 = typedValue.resourceId) == 0) {
            this.c = context;
        } else {
            this.c = new ContextThemeWrapper(context, i2);
        }
    }
}
