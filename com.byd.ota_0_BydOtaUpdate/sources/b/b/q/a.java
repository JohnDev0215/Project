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
    public b.f.m.w E3;
    public boolean F3;
    public boolean G3;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public final C0010a f985c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final Context f986d;
    public ActionMenuView s;
    public ActionMenuPresenter x;
    public int y;

    /* JADX INFO: renamed from: b.b.q.a$a, reason: collision with other inner class name */
    /* JADX INFO: compiled from: AbsActionBarView.java */
    public class C0010a implements b.f.m.x {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public boolean f987a = false;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public int f988b;

        public C0010a() {
        }

        @Override // b.f.m.x
        public void a(View view) {
            if (this.f987a) {
                return;
            }
            a aVar = a.this;
            aVar.E3 = null;
            a.super.setVisibility(this.f988b);
        }

        @Override // b.f.m.x
        public void b(View view) {
            a.super.setVisibility(0);
            this.f987a = false;
        }

        @Override // b.f.m.x
        public void c(View view) {
            this.f987a = true;
        }

        public C0010a d(b.f.m.w wVar, int i2) {
            a.this.E3 = wVar;
            this.f988b = i2;
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

    public b.f.m.w f(int i2, long j) {
        b.f.m.w wVar = this.E3;
        if (wVar != null) {
            wVar.b();
        }
        if (i2 != 0) {
            b.f.m.w wVarA = b.f.m.s.b(this).a(0.0f);
            wVarA.d(j);
            wVarA.f(this.f985c.d(wVarA, i2));
            return wVarA;
        }
        if (getVisibility() != 0) {
            setAlpha(0.0f);
        }
        b.f.m.w wVarA2 = b.f.m.s.b(this).a(1.0f);
        wVarA2.d(j);
        wVarA2.f(this.f985c.d(wVarA2, i2));
        return wVarA2;
    }

    public int getAnimatedVisibility() {
        return this.E3 != null ? this.f985c.f988b : getVisibility();
    }

    public int getContentHeight() {
        return this.y;
    }

    @Override // android.view.View
    public void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
        TypedArray typedArrayObtainStyledAttributes = getContext().obtainStyledAttributes(null, b.b.j.f771a, b.b.a.f706c, 0);
        setContentHeight(typedArrayObtainStyledAttributes.getLayoutDimension(b.b.j.j, 0));
        typedArrayObtainStyledAttributes.recycle();
        ActionMenuPresenter actionMenuPresenter = this.x;
        if (actionMenuPresenter != null) {
            actionMenuPresenter.G(configuration);
        }
    }

    @Override // android.view.View
    public boolean onHoverEvent(MotionEvent motionEvent) {
        int actionMasked = motionEvent.getActionMasked();
        if (actionMasked == 9) {
            this.G3 = false;
        }
        if (!this.G3) {
            boolean zOnHoverEvent = super.onHoverEvent(motionEvent);
            if (actionMasked == 9 && !zOnHoverEvent) {
                this.G3 = true;
            }
        }
        if (actionMasked == 10 || actionMasked == 3) {
            this.G3 = false;
        }
        return true;
    }

    @Override // android.view.View
    public boolean onTouchEvent(MotionEvent motionEvent) {
        int actionMasked = motionEvent.getActionMasked();
        if (actionMasked == 0) {
            this.F3 = false;
        }
        if (!this.F3) {
            boolean zOnTouchEvent = super.onTouchEvent(motionEvent);
            if (actionMasked == 0 && !zOnTouchEvent) {
                this.F3 = true;
            }
        }
        if (actionMasked == 1 || actionMasked == 3) {
            this.F3 = false;
        }
        return true;
    }

    public abstract void setContentHeight(int i2);

    @Override // android.view.View
    public void setVisibility(int i2) {
        if (i2 != getVisibility()) {
            b.f.m.w wVar = this.E3;
            if (wVar != null) {
                wVar.b();
            }
            super.setVisibility(i2);
        }
    }

    public a(Context context, AttributeSet attributeSet, int i2) {
        int i3;
        super(context, attributeSet, i2);
        this.f985c = new C0010a();
        TypedValue typedValue = new TypedValue();
        if (!context.getTheme().resolveAttribute(b.b.a.f704a, typedValue, true) || (i3 = typedValue.resourceId) == 0) {
            this.f986d = context;
        } else {
            this.f986d = new ContextThemeWrapper(context, i3);
        }
    }
}
