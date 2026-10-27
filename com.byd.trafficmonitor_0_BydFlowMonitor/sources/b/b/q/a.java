package b.b.q;

import android.content.Context;
import android.content.res.Configuration;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.ContextThemeWrapper;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import androidx.appcompat.widget.ActionMenuPresenter;
import androidx.appcompat.widget.ActionMenuView;

/* JADX INFO: compiled from: AbsActionBarView.java */
/* JADX INFO: loaded from: classes.dex */
public abstract class a extends ViewGroup {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final C0019a f1688b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final Context f1689c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public ActionMenuView f1690d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public ActionMenuPresenter f1691e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public int f1692f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public b.h.m.x f1693g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public boolean f1694h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public boolean f1695i;

    /* JADX INFO: renamed from: b.b.q.a$a, reason: collision with other inner class name */
    /* JADX INFO: compiled from: AbsActionBarView.java */
    public class C0019a implements b.h.m.y {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public boolean f1696a = false;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public int f1697b;

        public C0019a() {
        }

        @Override // b.h.m.y
        public void a(View view) {
            if (this.f1696a) {
                return;
            }
            a aVar = a.this;
            aVar.f1693g = null;
            a.super.setVisibility(this.f1697b);
        }

        @Override // b.h.m.y
        public void b(View view) {
            a.super.setVisibility(0);
            this.f1696a = false;
        }

        @Override // b.h.m.y
        public void c(View view) {
            this.f1696a = true;
        }

        public C0019a d(b.h.m.x xVar, int i2) {
            a.this.f1693g = xVar;
            this.f1697b = i2;
            return this;
        }
    }

    public a(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public static int d(int i2, int i3, boolean z) {
        return z ? i2 - i3 : i2 + i3;
    }

    public int c(View view, int i2, int i3, int i4) {
        view.measure(View.MeasureSpec.makeMeasureSpec(i2, Integer.MIN_VALUE), i3);
        return Math.max(0, (i2 - view.getMeasuredWidth()) - i4);
    }

    public int e(View view, int i2, int i3, int i4, boolean z) {
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

    public b.h.m.x f(int i2, long j) {
        b.h.m.x xVar = this.f1693g;
        if (xVar != null) {
            xVar.b();
        }
        if (i2 != 0) {
            b.h.m.x xVarA = b.h.m.t.c(this).a(0.0f);
            xVarA.d(j);
            xVarA.f(this.f1688b.d(xVarA, i2));
            return xVarA;
        }
        if (getVisibility() != 0) {
            setAlpha(0.0f);
        }
        b.h.m.x xVarA2 = b.h.m.t.c(this).a(1.0f);
        xVarA2.d(j);
        xVarA2.f(this.f1688b.d(xVarA2, i2));
        return xVarA2;
    }

    public int getAnimatedVisibility() {
        return this.f1693g != null ? this.f1688b.f1697b : getVisibility();
    }

    public int getContentHeight() {
        return this.f1692f;
    }

    @Override // android.view.View
    public void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
        TypedArray typedArrayObtainStyledAttributes = getContext().obtainStyledAttributes(null, b.b.j.ActionBar, b.b.a.actionBarStyle, 0);
        setContentHeight(typedArrayObtainStyledAttributes.getLayoutDimension(b.b.j.ActionBar_height, 0));
        typedArrayObtainStyledAttributes.recycle();
        ActionMenuPresenter actionMenuPresenter = this.f1691e;
        if (actionMenuPresenter != null) {
            actionMenuPresenter.J(configuration);
        }
    }

    @Override // android.view.View
    public boolean onHoverEvent(MotionEvent motionEvent) {
        int actionMasked = motionEvent.getActionMasked();
        if (actionMasked == 9) {
            this.f1695i = false;
        }
        if (!this.f1695i) {
            boolean zOnHoverEvent = super.onHoverEvent(motionEvent);
            if (actionMasked == 9 && !zOnHoverEvent) {
                this.f1695i = true;
            }
        }
        if (actionMasked == 10 || actionMasked == 3) {
            this.f1695i = false;
        }
        return true;
    }

    @Override // android.view.View
    public boolean onTouchEvent(MotionEvent motionEvent) {
        int actionMasked = motionEvent.getActionMasked();
        if (actionMasked == 0) {
            this.f1694h = false;
        }
        if (!this.f1694h) {
            boolean zOnTouchEvent = super.onTouchEvent(motionEvent);
            if (actionMasked == 0 && !zOnTouchEvent) {
                this.f1694h = true;
            }
        }
        if (actionMasked == 1 || actionMasked == 3) {
            this.f1694h = false;
        }
        return true;
    }

    public abstract void setContentHeight(int i2);

    @Override // android.view.View
    public void setVisibility(int i2) {
        if (i2 != getVisibility()) {
            b.h.m.x xVar = this.f1693g;
            if (xVar != null) {
                xVar.b();
            }
            super.setVisibility(i2);
        }
    }

    public a(Context context, AttributeSet attributeSet, int i2) {
        int i3;
        super(context, attributeSet, i2);
        this.f1688b = new C0019a();
        TypedValue typedValue = new TypedValue();
        if (!context.getTheme().resolveAttribute(b.b.a.actionBarPopupTheme, typedValue, true) || (i3 = typedValue.resourceId) == 0) {
            this.f1689c = context;
        } else {
            this.f1689c = new ContextThemeWrapper(context, i3);
        }
    }
}
