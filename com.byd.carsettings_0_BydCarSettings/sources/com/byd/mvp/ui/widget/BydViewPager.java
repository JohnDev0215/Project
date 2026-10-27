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

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public boolean f3782a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public float f3783b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public float f3784c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public a f3785d;

    public class a extends Scroller {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public boolean f3786a;

        /* JADX INFO: renamed from: com.byd.mvp.ui.widget.BydViewPager$a$a, reason: collision with other inner class name */
        public class InterpolatorC0100a implements Interpolator {

            /* JADX INFO: renamed from: a, reason: collision with root package name */
            public final /* synthetic */ BydViewPager f3788a;

            public InterpolatorC0100a(BydViewPager bydViewPager) {
                this.f3788a = bydViewPager;
            }

            @Override // android.animation.TimeInterpolator
            public float getInterpolation(float f2) {
                float f3 = f2 - 1.0f;
                return (f3 * f3 * f3 * f3 * f3) + 1.0f;
            }
        }

        public a(BydViewPager bydViewPager, Context context) {
            this(context, new InterpolatorC0100a(bydViewPager));
        }

        public void a(boolean z) {
            this.f3786a = z;
        }

        @Override // android.widget.Scroller
        public void startScroll(int i, int i2, int i3, int i4, int i5) {
            if (this.f3786a) {
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
        this.f3782a = true;
        b(context);
    }

    public final void a() {
        this.f3785d = new a(this, getContext());
        try {
            Field declaredField = ViewPager.class.getDeclaredField("mScroller");
            declaredField.setAccessible(true);
            declaredField.set(this, this.f3785d);
        } catch (IllegalAccessException e2) {
            e2.printStackTrace();
        } catch (NoSuchFieldException e3) {
            e3.printStackTrace();
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
            this.f3783b = motionEvent.getX();
            this.f3784c = motionEvent.getY();
            getParent().requestDisallowInterceptTouchEvent(true);
        } else if (action == 2) {
            float x = motionEvent.getX();
            if (Math.abs(x - this.f3783b) <= Math.abs(motionEvent.getY() - this.f3784c)) {
                getParent().requestDisallowInterceptTouchEvent(false);
            } else if (currentItem == getAdapter().getCount() - 1 && this.f3783b >= x) {
                getParent().requestDisallowInterceptTouchEvent(false);
            } else if (currentItem != 0 || this.f3783b >= x) {
                getParent().requestDisallowInterceptTouchEvent(true);
            } else {
                getParent().requestDisallowInterceptTouchEvent(false);
            }
        }
        return super.dispatchTouchEvent(motionEvent);
    }

    @Override // androidx.viewpager.widget.ViewPager, android.view.ViewGroup
    public boolean onInterceptTouchEvent(MotionEvent motionEvent) {
        if (this.f3782a) {
            return super.onInterceptTouchEvent(motionEvent);
        }
        return false;
    }

    @Override // androidx.viewpager.widget.ViewPager, android.view.View
    public boolean onTouchEvent(MotionEvent motionEvent) {
        if (this.f3782a) {
            return super.onTouchEvent(motionEvent);
        }
        return false;
    }

    @Override // androidx.viewpager.widget.ViewPager
    public void setCurrentItem(int i, boolean z) {
        if (Math.abs(getCurrentItem() - i) <= 1) {
            this.f3785d.a(false);
            super.setCurrentItem(i, z);
        } else {
            this.f3785d.a(true);
            super.setCurrentItem(i, z);
            this.f3785d.a(false);
        }
    }

    public void setScrollEnable(boolean z) {
        this.f3782a = z;
    }

    public BydViewPager(@NonNull Context context, @Nullable AttributeSet attributeSet) {
        super(context, attributeSet);
        this.f3782a = true;
        b(context);
    }
}
