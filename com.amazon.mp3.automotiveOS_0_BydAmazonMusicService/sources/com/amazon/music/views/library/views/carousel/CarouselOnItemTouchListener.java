package com.amazon.music.views.library.views.carousel;

import android.view.MotionEvent;
import android.view.View;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: CarouselOnItemTouchListener.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0004\u0018\u00002\u00020\u0001B\u0017\b\u0016\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005¢\u0006\u0002\u0010\u0006J\u0018\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\fH\u0016J\u0010\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\bH\u0016J\u0018\u0010\u0010\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\n2\u0006\u0010\u0011\u001a\u00020\fH\u0016R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082.¢\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082.¢\u0006\u0002\n\u0000¨\u0006\u0012"}, d2 = {"Lcom/amazon/music/views/library/views/carousel/CarouselOnItemTouchListener;", "Landroidx/recyclerview/widget/RecyclerView$OnItemTouchListener;", "linearLayoutManager", "Landroidx/recyclerview/widget/LinearLayoutManager;", "carouselRecyclerView", "Lcom/amazon/music/views/library/views/carousel/CarouselRecyclerView;", "(Landroidx/recyclerview/widget/LinearLayoutManager;Lcom/amazon/music/views/library/views/carousel/CarouselRecyclerView;)V", "onInterceptTouchEvent", "", "recyclerView", "Landroidx/recyclerview/widget/RecyclerView;", "motionEvent", "Landroid/view/MotionEvent;", "onRequestDisallowInterceptTouchEvent", "", "p0", "onTouchEvent", "p1", "DMMViewLibrary_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class CarouselOnItemTouchListener implements RecyclerView.OnItemTouchListener {
    private CarouselRecyclerView carouselRecyclerView;
    private LinearLayoutManager linearLayoutManager;

    @Override // androidx.recyclerview.widget.RecyclerView.OnItemTouchListener
    public void onRequestDisallowInterceptTouchEvent(boolean p0) {
    }

    @Override // androidx.recyclerview.widget.RecyclerView.OnItemTouchListener
    public void onTouchEvent(RecyclerView p0, MotionEvent p1) {
        Intrinsics.checkNotNullParameter(p0, "p0");
        Intrinsics.checkNotNullParameter(p1, "p1");
    }

    public CarouselOnItemTouchListener(LinearLayoutManager linearLayoutManager, CarouselRecyclerView carouselRecyclerView) {
        Intrinsics.checkNotNullParameter(linearLayoutManager, "linearLayoutManager");
        Intrinsics.checkNotNullParameter(carouselRecyclerView, "carouselRecyclerView");
        this.linearLayoutManager = linearLayoutManager;
        this.carouselRecyclerView = carouselRecyclerView;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.OnItemTouchListener
    public boolean onInterceptTouchEvent(RecyclerView recyclerView, MotionEvent motionEvent) {
        Intrinsics.checkNotNullParameter(recyclerView, "recyclerView");
        Intrinsics.checkNotNullParameter(motionEvent, "motionEvent");
        CarouselRecyclerView carouselRecyclerView = null;
        if (motionEvent.getAction() == 0) {
            CarouselRecyclerView carouselRecyclerView2 = this.carouselRecyclerView;
            if (carouselRecyclerView2 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("carouselRecyclerView");
                carouselRecyclerView2 = null;
            }
            carouselRecyclerView2.pause$DMMViewLibrary_release();
        } else if (motionEvent.getAction() == 1) {
            CarouselRecyclerView carouselRecyclerView3 = this.carouselRecyclerView;
            if (carouselRecyclerView3 == null) {
                Intrinsics.throwUninitializedPropertyAccessException("carouselRecyclerView");
                carouselRecyclerView3 = null;
            }
            if (carouselRecyclerView3.getIsStarted()) {
                CarouselRecyclerView carouselRecyclerView4 = this.carouselRecyclerView;
                if (carouselRecyclerView4 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("carouselRecyclerView");
                    carouselRecyclerView4 = null;
                }
                carouselRecyclerView4.start$DMMViewLibrary_release();
            }
        }
        View viewFindChildViewUnder = recyclerView.findChildViewUnder(motionEvent.getX(), motionEvent.getY());
        LinearLayoutManager linearLayoutManager = this.linearLayoutManager;
        if (linearLayoutManager == null) {
            Intrinsics.throwUninitializedPropertyAccessException("linearLayoutManager");
            linearLayoutManager = null;
        }
        int iFindLastVisibleItemPosition = linearLayoutManager.findLastVisibleItemPosition();
        LinearLayoutManager linearLayoutManager2 = this.linearLayoutManager;
        if (linearLayoutManager2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("linearLayoutManager");
            linearLayoutManager2 = null;
        }
        int iFindFirstVisibleItemPosition = (iFindLastVisibleItemPosition - linearLayoutManager2.findFirstVisibleItemPosition()) / 2;
        LinearLayoutManager linearLayoutManager3 = this.linearLayoutManager;
        if (linearLayoutManager3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("linearLayoutManager");
            linearLayoutManager3 = null;
        }
        int iFindFirstVisibleItemPosition2 = iFindFirstVisibleItemPosition + linearLayoutManager3.findFirstVisibleItemPosition();
        LinearLayoutManager linearLayoutManager4 = this.linearLayoutManager;
        if (linearLayoutManager4 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("linearLayoutManager");
            linearLayoutManager4 = null;
        }
        View viewFindViewByPosition = linearLayoutManager4.findViewByPosition(iFindFirstVisibleItemPosition2);
        CarouselRecyclerView carouselRecyclerView5 = this.carouselRecyclerView;
        if (carouselRecyclerView5 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("carouselRecyclerView");
            carouselRecyclerView5 = null;
        }
        boolean zOnTouchEvent = carouselRecyclerView5.getGestureDetectorCompat().onTouchEvent(motionEvent);
        if (viewFindChildViewUnder == null || viewFindChildViewUnder == viewFindViewByPosition || !zOnTouchEvent) {
            return false;
        }
        CarouselRecyclerView carouselRecyclerView6 = this.carouselRecyclerView;
        if (carouselRecyclerView6 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("carouselRecyclerView");
        } else {
            carouselRecyclerView = carouselRecyclerView6;
        }
        carouselRecyclerView.postScrollViewToCenter$DMMViewLibrary_release(viewFindChildViewUnder);
        return true;
    }
}
