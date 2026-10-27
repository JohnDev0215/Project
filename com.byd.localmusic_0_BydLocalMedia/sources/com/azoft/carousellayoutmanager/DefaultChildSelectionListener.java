package com.azoft.carousellayoutmanager;

import android.view.View;
import androidx.recyclerview.widget.RecyclerView;

/* JADX INFO: loaded from: classes4.dex */
public class DefaultChildSelectionListener extends CarouselChildSelectionListener {
    private final OnCenterItemClickListener mOnCenterItemClickListener;

    public interface OnCenterItemClickListener {
        void onCenterItemClicked(RecyclerView recyclerView, CarouselLayoutManager carouselLayoutManager, View view);
    }

    protected DefaultChildSelectionListener(OnCenterItemClickListener onCenterItemClickListener, RecyclerView recyclerView, CarouselLayoutManager carouselLayoutManager) {
        super(recyclerView, carouselLayoutManager);
        this.mOnCenterItemClickListener = onCenterItemClickListener;
    }

    @Override // com.azoft.carousellayoutmanager.CarouselChildSelectionListener
    protected void onCenterItemClicked(RecyclerView recyclerView, CarouselLayoutManager carouselLayoutManager, View view) {
        this.mOnCenterItemClickListener.onCenterItemClicked(recyclerView, carouselLayoutManager, view);
    }

    @Override // com.azoft.carousellayoutmanager.CarouselChildSelectionListener
    protected void onBackItemClicked(RecyclerView recyclerView, CarouselLayoutManager carouselLayoutManager, View view) {
        recyclerView.smoothScrollToPosition(carouselLayoutManager.getPosition(view));
    }

    public static DefaultChildSelectionListener initCenterItemListener(OnCenterItemClickListener onCenterItemClickListener, RecyclerView recyclerView, CarouselLayoutManager carouselLayoutManager) {
        return new DefaultChildSelectionListener(onCenterItemClickListener, recyclerView, carouselLayoutManager);
    }
}
