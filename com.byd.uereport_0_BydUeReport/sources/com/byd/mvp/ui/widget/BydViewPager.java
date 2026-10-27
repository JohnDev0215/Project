package com.byd.mvp.ui.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.animation.Interpolator;
import android.widget.Scroller;
import androidx.viewpager.widget.ViewPager;
import java.lang.reflect.Field;

/* JADX INFO: loaded from: classes2.dex */
public class BydViewPager extends ViewPager {

    /* JADX INFO: renamed from: l0, reason: collision with root package name */
    public boolean f2862l0;

    /* JADX INFO: renamed from: m0, reason: collision with root package name */
    public float f2863m0;

    /* JADX INFO: renamed from: n0, reason: collision with root package name */
    public float f2864n0;

    /* JADX INFO: renamed from: o0, reason: collision with root package name */
    public a f2865o0;

    public class a extends Scroller {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public boolean f2866a;

        /* JADX INFO: renamed from: com.byd.mvp.ui.widget.BydViewPager$a$a, reason: collision with other inner class name */
        public class InterpolatorC0019a implements Interpolator {

            /* JADX INFO: renamed from: a, reason: collision with root package name */
            public final /* synthetic */ BydViewPager f2868a;

            public InterpolatorC0019a(BydViewPager bydViewPager) {
                this.f2868a = bydViewPager;
            }

            @Override // android.animation.TimeInterpolator
            public float getInterpolation(float f8) {
                float f9 = f8 - 1.0f;
                return (f9 * f9 * f9 * f9 * f9) + 1.0f;
            }
        }

        public a(BydViewPager bydViewPager, Context context) {
            this(context, new InterpolatorC0019a(bydViewPager));
        }

        public void a(boolean z7) {
            this.f2866a = z7;
        }

        @Override // android.widget.Scroller
        public void startScroll(int i8, int i9, int i10, int i11, int i12) {
            if (this.f2866a) {
                super.startScroll(i8, i9, i10, i11, 0);
            } else {
                super.startScroll(i8, i9, i10, i11, i12);
            }
        }

        public a(Context context, Interpolator interpolator) {
            super(context, interpolator);
        }
    }

    public BydViewPager(Context context) {
        super(context);
        this.f2862l0 = true;
        U(context);
    }

    @Override // androidx.viewpager.widget.ViewPager
    public void N(int i8, boolean z7) {
        if (Math.abs(getCurrentItem() - i8) <= 1) {
            this.f2865o0.a(false);
            super.N(i8, z7);
        } else {
            this.f2865o0.a(true);
            super.N(i8, z7);
            this.f2865o0.a(false);
        }
    }

    public final void T() {
        this.f2865o0 = new a(this, getContext());
        try {
            Field declaredField = ViewPager.class.getDeclaredField("q");
            declaredField.setAccessible(true);
            declaredField.set(this, this.f2865o0);
        } catch (IllegalAccessException e8) {
            e8.printStackTrace();
        } catch (NoSuchFieldException e9) {
            e9.printStackTrace();
        }
    }

    public final void U(Context context) {
        T();
    }

    @Override // android.view.ViewGroup, android.view.View
    public boolean dispatchTouchEvent(MotionEvent motionEvent) {
        int currentItem = getCurrentItem();
        int action = motionEvent.getAction();
        if (action == 0) {
            this.f2863m0 = motionEvent.getX();
            this.f2864n0 = motionEvent.getY();
            getParent().requestDisallowInterceptTouchEvent(true);
        } else if (action == 2) {
            float x7 = motionEvent.getX();
            if (Math.abs(x7 - this.f2863m0) <= Math.abs(motionEvent.getY() - this.f2864n0)) {
                getParent().requestDisallowInterceptTouchEvent(false);
            } else if (currentItem == getAdapter().e() - 1 && this.f2863m0 >= x7) {
                getParent().requestDisallowInterceptTouchEvent(false);
            } else if (currentItem != 0 || this.f2863m0 >= x7) {
                getParent().requestDisallowInterceptTouchEvent(true);
            } else {
                getParent().requestDisallowInterceptTouchEvent(false);
            }
        }
        return super.dispatchTouchEvent(motionEvent);
    }

    @Override // androidx.viewpager.widget.ViewPager, android.view.ViewGroup
    public boolean onInterceptTouchEvent(MotionEvent motionEvent) {
        if (this.f2862l0) {
            return super.onInterceptTouchEvent(motionEvent);
        }
        return false;
    }

    @Override // androidx.viewpager.widget.ViewPager, android.view.View
    public boolean onTouchEvent(MotionEvent motionEvent) {
        if (this.f2862l0) {
            return super.onTouchEvent(motionEvent);
        }
        return false;
    }

    public void setScrollEnable(boolean z7) {
        this.f2862l0 = z7;
    }

    public BydViewPager(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.f2862l0 = true;
        U(context);
    }
}
