package com.amazon.music.views.library.views.carousel;

import android.view.View;
import androidx.recyclerview.widget.LinearLayoutManager;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: StepScrollerRunnable.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\u0018\u00002\u00020\u0001B\u0017\b\u0016\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J\b\u0010\u0007\u001a\u00020\bH\u0016R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082.¢\u0006\u0002\n\u0000¨\u0006\t"}, d2 = {"Lcom/amazon/music/views/library/views/carousel/StepScrollerRunnable;", "Ljava/lang/Runnable;", "linearLayoutManager", "Landroidx/recyclerview/widget/LinearLayoutManager;", "carouselRecyclerView", "Lcom/amazon/music/views/library/views/carousel/CarouselRecyclerView;", "(Landroidx/recyclerview/widget/LinearLayoutManager;Lcom/amazon/music/views/library/views/carousel/CarouselRecyclerView;)V", "run", "", "DMMViewLibrary_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class StepScrollerRunnable implements Runnable {
    private CarouselRecyclerView carouselRecyclerView;
    private LinearLayoutManager linearLayoutManager;

    public StepScrollerRunnable(LinearLayoutManager linearLayoutManager, CarouselRecyclerView carouselRecyclerView) {
        Intrinsics.checkNotNullParameter(linearLayoutManager, "linearLayoutManager");
        Intrinsics.checkNotNullParameter(carouselRecyclerView, "carouselRecyclerView");
        this.linearLayoutManager = linearLayoutManager;
        this.carouselRecyclerView = carouselRecyclerView;
    }

    @Override // java.lang.Runnable
    public void run() {
        CarouselRecyclerView carouselRecyclerView = this.carouselRecyclerView;
        CarouselRecyclerView carouselRecyclerView2 = null;
        if (carouselRecyclerView == null) {
            Intrinsics.throwUninitializedPropertyAccessException("carouselRecyclerView");
            carouselRecyclerView = null;
        }
        if (carouselRecyclerView.getAdapter() != null) {
            if (this.linearLayoutManager == null) {
                Intrinsics.throwUninitializedPropertyAccessException("linearLayoutManager");
            }
            LinearLayoutManager linearLayoutManager = this.linearLayoutManager;
            if (linearLayoutManager == null) {
                Intrinsics.throwUninitializedPropertyAccessException("linearLayoutManager");
                linearLayoutManager = null;
            }
            int iFindFirstVisibleItemPosition = linearLayoutManager.findFirstVisibleItemPosition();
            LinearLayoutManager linearLayoutManager2 = this.linearLayoutManager;
            if (linearLayoutManager2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("linearLayoutManager");
                linearLayoutManager2 = null;
            }
            int iFindLastVisibleItemPosition = linearLayoutManager2.findLastVisibleItemPosition();
            if (iFindFirstVisibleItemPosition == iFindLastVisibleItemPosition) {
                iFindLastVisibleItemPosition++;
            }
            LinearLayoutManager linearLayoutManager3 = this.linearLayoutManager;
            if (linearLayoutManager3 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("linearLayoutManager");
                linearLayoutManager3 = null;
            }
            View viewFindViewByPosition = linearLayoutManager3.findViewByPosition(iFindLastVisibleItemPosition);
            CarouselRecyclerView carouselRecyclerView3 = this.carouselRecyclerView;
            if (carouselRecyclerView3 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("carouselRecyclerView");
            } else {
                carouselRecyclerView2 = carouselRecyclerView3;
            }
            carouselRecyclerView2.scrollViewToCenter$DMMViewLibrary_release(viewFindViewByPosition);
        }
    }
}
