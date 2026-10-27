package a.b.p;

import android.content.Context;
import android.content.res.Configuration;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.view.ContextThemeWrapper;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import androidx.appcompat.widget.ActionMenuView;

/* JADX INFO: loaded from: classes.dex */
public abstract class a extends ViewGroup {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public final C0003a f219a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public final Context f220b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public ActionMenuView f221c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public c f222d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public int f223e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public a.h.k.s f224f;
    public boolean g;
    public boolean h;

    /* JADX INFO: renamed from: a.b.p.a$a, reason: collision with other inner class name */
    public class C0003a implements a.h.k.t {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public boolean f225a = false;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        public int f226b;

        public C0003a() {
        }

        @Override // a.h.k.t
        public void a(View view) {
            if (this.f225a) {
                return;
            }
            a aVar = a.this;
            aVar.f224f = null;
            a.super.setVisibility(this.f226b);
        }

        @Override // a.h.k.t
        public void b(View view) {
            a.super.setVisibility(0);
            this.f225a = false;
        }

        @Override // a.h.k.t
        public void c(View view) {
            this.f225a = true;
        }
    }

    public a(Context context, AttributeSet attributeSet) {
        this(context, attributeSet, 0);
    }

    public a(Context context, AttributeSet attributeSet, int i) {
        int i2;
        super(context, attributeSet, i);
        this.f219a = new C0003a();
        TypedValue typedValue = new TypedValue();
        if (!context.getTheme().resolveAttribute(a.b.a.actionBarPopupTheme, typedValue, true) || (i2 = typedValue.resourceId) == 0) {
            this.f220b = context;
        } else {
            this.f220b = new ContextThemeWrapper(context, i2);
        }
    }

    public int c(View view, int i, int i2, int i3) {
        view.measure(View.MeasureSpec.makeMeasureSpec(i, Integer.MIN_VALUE), i2);
        return Math.max(0, (i - view.getMeasuredWidth()) - i3);
    }

    public int d(View view, int i, int i2, int i3, boolean z) {
        int measuredWidth = view.getMeasuredWidth();
        int measuredHeight = view.getMeasuredHeight();
        int i4 = ((i3 - measuredHeight) / 2) + i2;
        if (z) {
            view.layout(i - measuredWidth, i4, i, measuredHeight + i4);
        } else {
            view.layout(i, i4, i + measuredWidth, measuredHeight + i4);
        }
        return z ? -measuredWidth : measuredWidth;
    }

    public a.h.k.s e(int i, long j) {
        a.h.k.s sVar = this.f224f;
        if (sVar != null) {
            sVar.b();
        }
        if (i != 0) {
            a.h.k.s sVarB = a.h.k.q.b(this);
            sVarB.a(0.0f);
            sVarB.c(j);
            C0003a c0003a = this.f219a;
            a.this.f224f = sVarB;
            c0003a.f226b = i;
            View view = sVarB.f835a.get();
            if (view != null) {
                sVarB.e(view, c0003a);
            }
            return sVarB;
        }
        if (getVisibility() != 0) {
            setAlpha(0.0f);
        }
        a.h.k.s sVarB2 = a.h.k.q.b(this);
        sVarB2.a(1.0f);
        sVarB2.c(j);
        C0003a c0003a2 = this.f219a;
        a.this.f224f = sVarB2;
        c0003a2.f226b = i;
        View view2 = sVarB2.f835a.get();
        if (view2 != null) {
            sVarB2.e(view2, c0003a2);
        }
        return sVarB2;
    }

    public int getAnimatedVisibility() {
        return this.f224f != null ? this.f219a.f226b : getVisibility();
    }

    public int getContentHeight() {
        return this.f223e;
    }

    @Override // android.view.View
    public void onConfigurationChanged(Configuration configuration) {
        super.onConfigurationChanged(configuration);
        TypedArray typedArrayObtainStyledAttributes = getContext().obtainStyledAttributes(null, a.b.j.ActionBar, a.b.a.actionBarStyle, 0);
        setContentHeight(typedArrayObtainStyledAttributes.getLayoutDimension(a.b.j.ActionBar_height, 0));
        typedArrayObtainStyledAttributes.recycle();
        c cVar = this.f222d;
        if (cVar != null) {
            Configuration configuration2 = cVar.f139b.getResources().getConfiguration();
            int i = configuration2.screenWidthDp;
            int i2 = configuration2.screenHeightDp;
            cVar.p = (configuration2.smallestScreenWidthDp > 600 || i > 600 || (i > 960 && i2 > 720) || (i > 720 && i2 > 960)) ? 5 : (i >= 500 || (i > 640 && i2 > 480) || (i > 480 && i2 > 640)) ? 4 : i >= 360 ? 3 : 2;
            a.b.o.i.g gVar = cVar.f140c;
            if (gVar != null) {
                gVar.q(true);
            }
        }
    }

    @Override // android.view.View
    public boolean onHoverEvent(MotionEvent motionEvent) {
        int actionMasked = motionEvent.getActionMasked();
        if (actionMasked == 9) {
            this.h = false;
        }
        if (!this.h) {
            boolean zOnHoverEvent = super.onHoverEvent(motionEvent);
            if (actionMasked == 9 && !zOnHoverEvent) {
                this.h = true;
            }
        }
        if (actionMasked == 10 || actionMasked == 3) {
            this.h = false;
        }
        return true;
    }

    @Override // android.view.View
    public boolean onTouchEvent(MotionEvent motionEvent) {
        int actionMasked = motionEvent.getActionMasked();
        if (actionMasked == 0) {
            this.g = false;
        }
        if (!this.g) {
            boolean zOnTouchEvent = super.onTouchEvent(motionEvent);
            if (actionMasked == 0 && !zOnTouchEvent) {
                this.g = true;
            }
        }
        if (actionMasked == 1 || actionMasked == 3) {
            this.g = false;
        }
        return true;
    }

    public abstract void setContentHeight(int i);

    @Override // android.view.View
    public void setVisibility(int i) {
        if (i != getVisibility()) {
            a.h.k.s sVar = this.f224f;
            if (sVar != null) {
                sVar.b();
            }
            super.setVisibility(i);
        }
    }
}
