package com.azoft.carousellayoutmanager;

import android.view.View;
import androidx.recyclerview.widget.RecyclerView;

/* JADX INFO: loaded from: classes4.dex */
public abstract class CarouselChildSelectionListener {
    private final CarouselLayoutManager mCarouselLayoutManager;
    private final View.OnClickListener mOnClickListener = new View.OnClickListener() { // from class: com.azoft.carousellayoutmanager.CarouselChildSelectionListener.1
        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            if (CarouselChildSelectionListener.this.mRecyclerView.getChildViewHolder(view).getAdapterPosition() == CarouselChildSelectionListener.this.mCarouselLayoutManager.getCenterItemPosition()) {
                CarouselChildSelectionListener carouselChildSelectionListener = CarouselChildSelectionListener.this;
                carouselChildSelectionListener.onCenterItemClicked(carouselChildSelectionListener.mRecyclerView, CarouselChildSelectionListener.this.mCarouselLayoutManager, view);
            } else {
                CarouselChildSelectionListener carouselChildSelectionListener2 = CarouselChildSelectionListener.this;
                carouselChildSelectionListener2.onBackItemClicked(carouselChildSelectionListener2.mRecyclerView, CarouselChildSelectionListener.this.mCarouselLayoutManager, view);
            }
        }
    };
    private final RecyclerView mRecyclerView;

    protected abstract void onBackItemClicked(RecyclerView recyclerView, CarouselLayoutManager carouselLayoutManager, View view);

    protected abstract void onCenterItemClicked(RecyclerView recyclerView, CarouselLayoutManager carouselLayoutManager, View view);

    protected CarouselChildSelectionListener(RecyclerView recyclerView, CarouselLayoutManager carouselLayoutManager) {
        this.mRecyclerView = recyclerView;
        this.mCarouselLayoutManager = carouselLayoutManager;
        recyclerView.addOnChildAttachStateChangeListener(new RecyclerView.OnChildAttachStateChangeListener() { // from class: com.azoft.carousellayoutmanager.CarouselChildSelectionListener.2
            @Override // androidx.recyclerview.widget.RecyclerView.OnChildAttachStateChangeListener
            public void onChildViewAttachedToWindow(View view) {
                view.setOnClickListener(CarouselChildSelectionListener.this.mOnClickListener);
            }

            @Override // androidx.recyclerview.widget.RecyclerView.OnChildAttachStateChangeListener
            public void onChildViewDetachedFromWindow(View view) {
                view.setOnClickListener(null);
            }
        });
    }
}
