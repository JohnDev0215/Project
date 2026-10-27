package com.byd.overseaappstore.ui.view.custom;

import android.content.Context;
import android.util.AttributeSet;
import android.util.SparseArray;
import android.view.MotionEvent;
import android.view.View;
import androidx.viewpager.widget.ViewPager;
import java.util.ArrayList;
import java.util.Collections;
import kotlin.Metadata;
import kotlin.Unit;
import kotlin.jvm.functions.Function1;
import kotlin.jvm.internal.Intrinsics;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

/* JADX INFO: loaded from: classes.dex */
@Metadata(bv = {}, d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u00002\u00020\u0001B\u0013\b\u0016\u0012\b\u0010\u000b\u001a\u0004\u0018\u00010\n¢\u0006\u0004\b\f\u0010\rB\u001d\b\u0016\u0012\b\u0010\u000b\u001a\u0004\u0018\u00010\n\u0012\b\u0010\u000f\u001a\u0004\u0018\u00010\u000e¢\u0006\u0004\b\f\u0010\u0010J)\u0010\t\u001a\u00020\u00072!\u0010\b\u001a\u001d\u0012\u0013\u0012\u00110\u0003¢\u0006\f\b\u0004\u0012\b\b\u0005\u0012\u0004\b\b(\u0006\u0012\u0004\u0012\u00020\u00070\u0002¨\u0006\u0011"}, d2 = {"Lcom/byd/overseaappstore/ui/view/custom/CustomViewPager;", "Landroidx/viewpager/widget/ViewPager;", "Lkotlin/Function1;", "", "Lkotlin/ParameterName;", "name", "action", "", "block", "setOnActionListener", "Landroid/content/Context;", "context", "<init>", "(Landroid/content/Context;)V", "Landroid/util/AttributeSet;", "attrs", "(Landroid/content/Context;Landroid/util/AttributeSet;)V", "app_flavor_8155f_proRelease"}, k = 1, mv = {1, 6, 0})
public final class CustomViewPager extends ViewPager {

    @NotNull
    public final ArrayList<Integer> a;

    @NotNull
    public final SparseArray<Integer> b;
    public float c;
    public float d;
    public Function1<? super Integer, Unit> e;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public CustomViewPager(@Nullable Context context) {
        super(context);
        Intrinsics.checkNotNull(context);
        this.a = new ArrayList<>();
        this.b = new SparseArray<>();
        this.c = -1.0f;
        this.d = -1.0f;
        setClipToPadding(false);
        setOverScrollMode(2);
    }

    public final int a(View view) {
        double d;
        double width;
        double d2;
        int[] iArr = new int[2];
        view.getLocationOnScreen(iArr);
        if (getResources().getConfiguration().orientation == 1) {
            d = iArr[0];
            width = view.getWidth();
            d2 = 1.1d;
        } else {
            d = iArr[0];
            width = view.getWidth();
            d2 = 1.8d;
        }
        return (int) ((width / d2) + d);
    }

    @Override // android.view.ViewGroup, android.view.View
    public final boolean dispatchTouchEvent(@Nullable MotionEvent motionEvent) {
        float rawX = motionEvent != null ? motionEvent.getRawX() : 0.0f;
        float rawY = motionEvent != null ? motionEvent.getRawY() : 0.0f;
        Integer numValueOf = motionEvent != null ? Integer.valueOf(motionEvent.getAction()) : null;
        if (numValueOf != null && numValueOf.intValue() == 0) {
            getParent().requestDisallowInterceptTouchEvent(true);
        } else if (numValueOf != null && numValueOf.intValue() == 2) {
            if (Math.abs(rawX - this.c) + 0.0f > Math.abs(rawY - this.d) + 0.0f) {
                getParent().requestDisallowInterceptTouchEvent(true);
            } else {
                getParent().requestDisallowInterceptTouchEvent(false);
            }
            this.c = rawX;
            this.d = rawY;
        }
        return super.dispatchTouchEvent(motionEvent);
    }

    @Override // androidx.viewpager.widget.ViewPager, android.view.ViewGroup
    public final int getChildDrawingOrder(int i, int i2) {
        if (i2 == 0 || this.b.size() != i) {
            this.a.clear();
            this.b.clear();
            int iA = a(this);
            for (int i3 = 0; i3 < i; i3++) {
                View childAt = getChildAt(i3);
                Intrinsics.checkNotNullExpressionValue(childAt, "getChildAt(i)");
                int iAbs = Math.abs(iA - a(childAt));
                if (this.b.get(iAbs) != null) {
                    iAbs++;
                }
                this.a.add(Integer.valueOf(iAbs));
                this.b.append(iAbs, Integer.valueOf(i3));
            }
            Collections.sort(this.a);
        }
        SparseArray<Integer> sparseArray = this.b;
        Integer num = this.a.get((i - 1) - i2);
        Intrinsics.checkNotNullExpressionValue(num, "childCenterXAbs[childCount - 1 - n]");
        Integer num2 = sparseArray.get(num.intValue());
        Intrinsics.checkNotNullExpressionValue(num2, "childIndex[childCenterXAbs[childCount - 1 - n]]");
        return num2.intValue();
    }

    @Override // androidx.viewpager.widget.ViewPager, android.view.ViewGroup
    public final boolean onInterceptTouchEvent(@Nullable MotionEvent motionEvent) {
        if (getCurrentItem() == 0 || getChildCount() == 0) {
            return false;
        }
        Function1<? super Integer, Unit> function1 = null;
        if ((motionEvent != null ? Integer.valueOf(motionEvent.getAction()) : null) != null && motionEvent.getAction() != 2) {
            Function1<? super Integer, Unit> function12 = this.e;
            if (function12 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("onActionListener");
            } else {
                function1 = function12;
            }
            function1.invoke(Integer.valueOf(motionEvent.getAction()));
        }
        return super.onInterceptTouchEvent(motionEvent);
    }

    @Override // androidx.viewpager.widget.ViewPager, android.view.View
    public final boolean onTouchEvent(@Nullable MotionEvent motionEvent) {
        if (getCurrentItem() == 0 || getChildCount() == 0) {
            return false;
        }
        Function1<? super Integer, Unit> function1 = null;
        if ((motionEvent != null ? Integer.valueOf(motionEvent.getAction()) : null) != null && motionEvent.getAction() != 2) {
            Function1<? super Integer, Unit> function12 = this.e;
            if (function12 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("onActionListener");
            } else {
                function1 = function12;
            }
            function1.invoke(Integer.valueOf(motionEvent.getAction()));
        }
        return super.onTouchEvent(motionEvent);
    }

    public final void setOnActionListener(@NotNull Function1<? super Integer, Unit> block) {
        Intrinsics.checkNotNullParameter(block, "block");
        this.e = block;
    }

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public CustomViewPager(@Nullable Context context, @Nullable AttributeSet attributeSet) {
        super(context, attributeSet);
        Intrinsics.checkNotNull(context);
        this.a = new ArrayList<>();
        this.b = new SparseArray<>();
        this.c = -1.0f;
        this.d = -1.0f;
        setClipToPadding(false);
        setOverScrollMode(2);
    }
}
