package r;

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

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public final C0111a f7478d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final Context f7479e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public ActionMenuView f7480f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public ActionMenuPresenter f7481g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public int f7482h;

    /* JADX INFO: renamed from: i, reason: collision with root package name */
    public o0.x f7483i;

    /* JADX INFO: renamed from: j, reason: collision with root package name */
    public boolean f7484j;

    /* JADX INFO: renamed from: k, reason: collision with root package name */
    public boolean f7485k;

    /* JADX INFO: renamed from: r.a$a, reason: collision with other inner class name */
    /* JADX INFO: compiled from: AbsActionBarView.java */
    public class C0111a implements o0.y {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public boolean f7486a = false;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public int f7487b;

        public C0111a() {
        }

        @Override // o0.y
        public void a(View view) {
            this.f7486a = true;
        }

        @Override // o0.y
        public void b(View view) {
            if (this.f7486a) {
                return;
            }
            a aVar = a.this;
            aVar.f7483i = null;
            a.super.setVisibility(this.f7487b);
        }

        @Override // o0.y
        public void c(View view) {
            a.super.setVisibility(0);
            this.f7486a = false;
        }

        public C0111a d(o0.x xVar, int i8) {
            a.this.f7483i = xVar;
            this.f7487b = i8;
            return this;
        }
    }

    public a(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public static int d(int i8, int i9, boolean z7) {
        return z7 ? i8 - i9 : i8 + i9;
    }

    public int c(View view, int i8, int i9, int i10) {
        view.measure(View.MeasureSpec.makeMeasureSpec(i8, Integer.MIN_VALUE), i9);
        return Math.max(0, (i8 - view.getMeasuredWidth()) - i10);
    }

    public int e(View view, int i8, int i9, int i10, boolean z7) {
        int measuredWidth = view.getMeasuredWidth();
        int measuredHeight = view.getMeasuredHeight();
        int i11 = i9 + ((i10 - measuredHeight) / 2);
        if (z7) {
            view.layout(i8 - measuredWidth, i11, i8, measuredHeight + i11);
        } else {
            view.layout(i8, i11, i8 + measuredWidth, measuredHeight + i11);
        }
        return z7 ? -measuredWidth : measuredWidth;
    }

    public o0.x f(int i8, long j8) {
        o0.x xVar = this.f7483i;
        if (xVar != null) {
            xVar.b();
        }
        if (i8 != 0) {
            o0.x xVarA = o0.t.c(this).a(0.0f);
            xVarA.d(j8);
            xVarA.f(this.f7478d.d(xVarA, i8));
            return xVarA;
        }
        if (getVisibility() != 0) {
            setAlpha(0.0f);
        }
        o0.x xVarA2 = o0.t.c(this).a(1.0f);
        xVarA2.d(j8);
        xVarA2.f(this.f7478d.d(xVarA2, i8));
        return xVarA2;
    }

    public int getAnimatedVisibility() {
        return this.f7483i != null ? this.f7478d.f7487b : getVisibility();
    }

    public int getContentHeight() {
        return this.f7482h;
    }

    @Override // android.view.View
    public void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
        TypedArray typedArrayObtainStyledAttributes = getContext().obtainStyledAttributes(null, R$styleable.ActionBar, R$attr.actionBarStyle, 0);
        setContentHeight(typedArrayObtainStyledAttributes.getLayoutDimension(R$styleable.ActionBar_height, 0));
        typedArrayObtainStyledAttributes.recycle();
        ActionMenuPresenter actionMenuPresenter = this.f7481g;
        if (actionMenuPresenter != null) {
            actionMenuPresenter.J(configuration);
        }
    }

    @Override // android.view.View
    public boolean onHoverEvent(MotionEvent motionEvent) {
        int actionMasked = motionEvent.getActionMasked();
        if (actionMasked == 9) {
            this.f7485k = false;
        }
        if (!this.f7485k) {
            boolean zOnHoverEvent = super.onHoverEvent(motionEvent);
            if (actionMasked == 9 && !zOnHoverEvent) {
                this.f7485k = true;
            }
        }
        if (actionMasked == 10 || actionMasked == 3) {
            this.f7485k = false;
        }
        return true;
    }

    @Override // android.view.View
    public boolean onTouchEvent(MotionEvent motionEvent) {
        int actionMasked = motionEvent.getActionMasked();
        if (actionMasked == 0) {
            this.f7484j = false;
        }
        if (!this.f7484j) {
            boolean zOnTouchEvent = super.onTouchEvent(motionEvent);
            if (actionMasked == 0 && !zOnTouchEvent) {
                this.f7484j = true;
            }
        }
        if (actionMasked == 1 || actionMasked == 3) {
            this.f7484j = false;
        }
        return true;
    }

    public abstract void setContentHeight(int i8);

    @Override // android.view.View
    public void setVisibility(int i8) {
        if (i8 != getVisibility()) {
            o0.x xVar = this.f7483i;
            if (xVar != null) {
                xVar.b();
            }
            super.setVisibility(i8);
        }
    }

    public a(Context context, AttributeSet attributeSet, int i8) {
        int i9;
        super(context, attributeSet, i8);
        this.f7478d = new C0111a();
        TypedValue typedValue = new TypedValue();
        if (!context.getTheme().resolveAttribute(R$attr.actionBarPopupTheme, typedValue, true) || (i9 = typedValue.resourceId) == 0) {
            this.f7479e = context;
        } else {
            this.f7479e = new ContextThemeWrapper(context, i9);
        }
    }
}
