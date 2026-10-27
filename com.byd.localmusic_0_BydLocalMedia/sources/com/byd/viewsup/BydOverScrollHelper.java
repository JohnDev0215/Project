package com.byd.viewsup;

import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.GridView;
import android.widget.HorizontalScrollView;
import android.widget.ScrollView;
import com.byd.viewsup.adapters.AbsListViewOverScrollDecorAdapter;
import com.byd.viewsup.adapters.HorizontalScrollViewOverScrollDecorAdapter;
import com.byd.viewsup.adapters.IOverScrollDecoratorAdapter;
import com.byd.viewsup.adapters.NestedFragLayoutAdapter;
import com.byd.viewsup.adapters.NestedFrameLayoutScrollDecorAdapter;
import com.byd.viewsup.adapters.ScrollViewOverScrollDecorAdapter;
import com.byd.viewsup.adapters.StaticOverScrollDecorAdapter;

/* JADX INFO: loaded from: classes5.dex */
public class BydOverScrollHelper {
    public static final int ORIENTATION_HORIZONTAL = 1;
    public static final int ORIENTATION_VERTICAL = 0;
    public static final String TAG = "BydOverScrollHelper";

    public static IOverScrollDecor setUpOverScroll(IOverScrollDecoratorAdapter iOverScrollDecoratorAdapter, float f, long j, float f2) {
        Log.d(TAG, "setUpOverScroll");
        if (iOverScrollDecoratorAdapter instanceof NestedFragLayoutAdapter) {
            if (((NestedFragLayoutAdapter) iOverScrollDecoratorAdapter).isHorizontal()) {
                return new HorizontalOverScrollBounceEffectDecorator(iOverScrollDecoratorAdapter, f, j, f2);
            }
            return new VerticalOverScrollBounceEffectDecorator(iOverScrollDecoratorAdapter, f, j, f2);
        }
        return new VerticalOverScrollBounceEffectDecorator(iOverScrollDecoratorAdapter, f, j, f2);
    }

    public static IOverScrollDecor setUpOverScroll(IOverScrollDecoratorAdapter iOverScrollDecoratorAdapter, float f, long j) {
        if (iOverScrollDecoratorAdapter instanceof NestedFragLayoutAdapter) {
            if (((NestedFragLayoutAdapter) iOverScrollDecoratorAdapter).isHorizontal()) {
                return new HorizontalOverScrollBounceEffectDecorator(iOverScrollDecoratorAdapter, f, j);
            }
            return new VerticalOverScrollBounceEffectDecorator(iOverScrollDecoratorAdapter, f, j);
        }
        return new VerticalOverScrollBounceEffectDecorator(iOverScrollDecoratorAdapter, f, j);
    }

    public static IOverScrollDecor setUpOverScroll(HorizontalScrollView horizontalScrollView) {
        return new HorizontalOverScrollBounceEffectDecorator(new HorizontalScrollViewOverScrollDecorAdapter(horizontalScrollView));
    }

    private static IOverScrollDecor setUpOverScroll(ScrollView scrollView, float f, long j) {
        return new VerticalOverScrollBounceEffectDecorator(new ScrollViewOverScrollDecorAdapter(scrollView), f, j);
    }

    public static IOverScrollDecor setUpOverScroll(HorizontalScrollView horizontalScrollView, float f, long j) {
        return new HorizontalOverScrollBounceEffectDecorator(new HorizontalScrollViewOverScrollDecorAdapter(horizontalScrollView), f, j);
    }

    public static IOverScrollDecor setVerUpOverScroll(FrameLayout frameLayout, float f, long j) {
        return new VerticalOverScrollBounceEffectDecorator(new NestedFrameLayoutScrollDecorAdapter(frameLayout, false), f, j);
    }

    public static IOverScrollDecor setUpOverScroll(GridView gridView) {
        return new VerticalOverScrollBounceEffectDecorator(new AbsListViewOverScrollDecorAdapter(gridView));
    }

    public static IOverScrollDecor setUpStaticOverScroll(View view, int i) {
        if (i == 0) {
            return new VerticalOverScrollBounceEffectDecorator(new StaticOverScrollDecorAdapter(view, false), BydScrollerSupportedFrameLayout.dp2px(155.0f), 200L);
        }
        if (i == 1) {
            return new HorizontalOverScrollBounceEffectDecorator(new StaticOverScrollDecorAdapter(view, true), BydScrollerSupportedFrameLayout.dp2px(155.0f), 200L);
        }
        throw new IllegalArgumentException("orientation");
    }

    public static boolean isOverOnePage(View view) {
        View childAt;
        return (view instanceof ViewGroup) && (childAt = ((ViewGroup) view).getChildAt(0)) != null && childAt.getMeasuredHeight() > view.getHeight();
    }

    public static void setUpStaticOverScroll(final View view, final int i, boolean z) {
        if (z) {
            view.post(new Runnable() { // from class: com.byd.viewsup.-$$Lambda$BydOverScrollHelper$KObOudapYEvWosU_6TECh5iYW90
                @Override // java.lang.Runnable
                public final void run() {
                    BydOverScrollHelper.lambda$setUpStaticOverScroll$0(view, i);
                }
            });
        } else {
            setUpStaticOverScroll(view, i);
        }
    }

    static /* synthetic */ void lambda$setUpStaticOverScroll$0(View view, int i) {
        if (isOverOnePage(view)) {
            setUpStaticOverScroll(view, i);
        }
    }
}
