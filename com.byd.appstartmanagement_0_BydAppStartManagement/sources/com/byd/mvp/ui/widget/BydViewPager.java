package com.byd.mvp.ui.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.animation.Interpolator;
import android.widget.Scroller;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.viewpager.widget.ViewPager;
import java.lang.reflect.Field;

/* JADX INFO: loaded from: classes2.dex */
public class BydViewPager extends ViewPager {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public boolean f713b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public float f714c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public float f715d;
    public a e;

    public class a extends Scroller {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public boolean f716a;

        /* JADX INFO: renamed from: com.byd.mvp.ui.widget.BydViewPager$a$a, reason: collision with other inner class name */
        public class InterpolatorC0034a implements Interpolator {

            /* JADX INFO: renamed from: a, reason: collision with root package name */
            public final /* synthetic */ BydViewPager f718a;

            public InterpolatorC0034a(BydViewPager bydViewPager) {
                this.f718a = bydViewPager;
            }

            @Override // android.animation.TimeInterpolator
            public float getInterpolation(float f) {
                float f2 = f - 1.0f;
                return (f2 * f2 * f2 * f2 * f2) + 1.0f;
            }
        }

        public a(BydViewPager bydViewPager, Context context) {
            this(context, new InterpolatorC0034a(bydViewPager));
        }

        public void a(boolean z) {
            this.f716a = z;
        }

        @Override // android.widget.Scroller
        public void startScroll(int i, int i2, int i3, int i4, int i5) {
            if (this.f716a) {
                super.startScroll(i, i2, i3, i4, 0);
            } else {
                super.startScroll(i, i2, i3, i4, i5);
            }
        }

        public a(Context context, Interpolator interpolator) {
            super(context, interpolator);
        }
    }

    public BydViewPager(@NonNull Context context) {
        super(context);
        this.f713b = true;
        b(context);
    }

    public final void a() {
        this.e = new a(this, getContext());
        try {
            Field declaredField = ViewPager.class.getDeclaredField("mScroller");
            declaredField.setAccessible(true);
            declaredField.set(this, this.e);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (NoSuchFieldException e2) {
            e2.printStackTrace();
        }
    }

    public final void b(Context context) {
        a();
    }

    @Override // android.view.ViewGroup, android.view.View
    public boolean dispatchTouchEvent(MotionEvent motionEvent) {
        int currentItem = getCurrentItem();
        int action = motionEvent.getAction();
        if (action == 0) {
            this.f714c = motionEvent.getX();
            this.f715d = motionEvent.getY();
            getParent().requestDisallowInterceptTouchEvent(true);
        } else if (action == 2) {
            float x = motionEvent.getX();
            if (Math.abs(x - this.f714c) <= Math.abs(motionEvent.getY() - this.f715d)) {
                getParent().requestDisallowInterceptTouchEvent(false);
            } else if (currentItem == getAdapter().getCount() - 1 && this.f714c >= x) {
                getParent().requestDisallowInterceptTouchEvent(false);
            } else if (currentItem != 0 || this.f714c >= x) {
                getParent().requestDisallowInterceptTouchEvent(true);
            } else {
                getParent().requestDisallowInterceptTouchEvent(false);
            }
        }
        return super.dispatchTouchEvent(motionEvent);
    }

    @Override // androidx.viewpager.widget.ViewPager, android.view.ViewGroup
    public boolean onInterceptTouchEvent(MotionEvent motionEvent) {
        if (this.f713b) {
            return super.onInterceptTouchEvent(motionEvent);
        }
        return false;
    }

    @Override // androidx.viewpager.widget.ViewPager, android.view.View
    public boolean onTouchEvent(MotionEvent motionEvent) {
        if (this.f713b) {
            return super.onTouchEvent(motionEvent);
        }
        return false;
    }

    @Override // androidx.viewpager.widget.ViewPager
    public void setCurrentItem(int i, boolean z) {
        if (Math.abs(getCurrentItem() - i) <= 1) {
            this.e.a(false);
            super.setCurrentItem(i, z);
        } else {
            this.e.a(true);
            super.setCurrentItem(i, z);
            this.e.a(false);
        }
    }

    public void setScrollEnable(boolean z) {
        this.f713b = z;
    }

    public BydViewPager(@NonNull Context context, @Nullable AttributeSet attributeSet) {
        super(context, attributeSet);
        this.f713b = true;
        b(context);
    }
}
