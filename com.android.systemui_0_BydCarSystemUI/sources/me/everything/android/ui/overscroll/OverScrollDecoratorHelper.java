package me.everything.android.ui.overscroll;

import android.view.View;
import android.widget.GridView;
import android.widget.HorizontalScrollView;
import android.widget.ListView;
import android.widget.ScrollView;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;
import me.everything.android.ui.overscroll.adapters.AbsListViewOverScrollDecorAdapter;
import me.everything.android.ui.overscroll.adapters.HorizontalScrollViewOverScrollDecorAdapter;
import me.everything.android.ui.overscroll.adapters.RecyclerViewOverScrollDecorAdapter;
import me.everything.android.ui.overscroll.adapters.ScrollViewOverScrollDecorAdapter;
import me.everything.android.ui.overscroll.adapters.StaticOverScrollDecorAdapter;
import me.everything.android.ui.overscroll.adapters.ViewPagerOverScrollDecorAdapter;

/* JADX INFO: loaded from: classes2.dex */
public class OverScrollDecoratorHelper {
    public static final int ORIENTATION_HORIZONTAL = 1;
    public static final int ORIENTATION_VERTICAL = 0;

    public static IOverScrollDecor setUpOverScroll(RecyclerView recyclerView, int i) {
        if (i == 0) {
            return new VerticalOverScrollBounceEffectDecorator(new RecyclerViewOverScrollDecorAdapter(recyclerView));
        }
        if (i == 1) {
            return new HorizontalOverScrollBounceEffectDecorator(new RecyclerViewOverScrollDecorAdapter(recyclerView));
        }
        throw new IllegalArgumentException("orientation");
    }

    public static IOverScrollDecor setUpOverScroll(ListView listView) {
        return new VerticalOverScrollBounceEffectDecorator(new AbsListViewOverScrollDecorAdapter(listView));
    }

    public static IOverScrollDecor setUpOverScroll(GridView gridView) {
        return new VerticalOverScrollBounceEffectDecorator(new AbsListViewOverScrollDecorAdapter(gridView));
    }

    public static IOverScrollDecor setUpOverScroll(ScrollView scrollView) {
        return new VerticalOverScrollBounceEffectDecorator(new ScrollViewOverScrollDecorAdapter(scrollView));
    }

    public static IOverScrollDecor setUpOverScroll(HorizontalScrollView horizontalScrollView) {
        return new HorizontalOverScrollBounceEffectDecorator(new HorizontalScrollViewOverScrollDecorAdapter(horizontalScrollView));
    }

    public static IOverScrollDecor setUpStaticOverScroll(View view, int i) {
        if (i == 0) {
            return new VerticalOverScrollBounceEffectDecorator(new StaticOverScrollDecorAdapter(view));
        }
        if (i == 1) {
            return new HorizontalOverScrollBounceEffectDecorator(new StaticOverScrollDecorAdapter(view));
        }
        throw new IllegalArgumentException("orientation");
    }

    public static IOverScrollDecor setUpOverScroll(ViewPager viewPager) {
        return new HorizontalOverScrollBounceEffectDecorator(new ViewPagerOverScrollDecorAdapter(viewPager));
    }
}
