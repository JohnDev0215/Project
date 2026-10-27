package com.byd.card.widget;

import android.widget.ScrollView;
import androidx.recyclerview.widget.RecyclerView;
import me.everything.android.ui.overscroll.OverScrollDecoratorHelper;
import me.everything.android.ui.overscroll.VerticalOverScrollBounceEffectDecorator;
import me.everything.android.ui.overscroll.adapters.RecyclerViewOverScrollDecorAdapter;
import me.everything.android.ui.overscroll.adapters.ScrollViewOverScrollDecorAdapter;

/* JADX INFO: loaded from: classes.dex */
public class ViewUtils {
    public static void setRecyclerViewOverScroll(RecyclerView recyclerView) {
        OverScrollDecoratorHelper.setUpOverScroll(recyclerView, 0);
        new VerticalOverScrollBounceEffectDecorator(new RecyclerViewOverScrollDecorAdapter(recyclerView), 1.0f, 1.0f, -2.0f);
    }

    public static void setScrollViewOverScroll(ScrollView scrollView) {
        OverScrollDecoratorHelper.setUpOverScroll(scrollView);
        new VerticalOverScrollBounceEffectDecorator(new ScrollViewOverScrollDecorAdapter(scrollView), 1.0f, 1.0f, -2.0f);
    }
}
