package com.amazon.music.views.library.views.carousel;

import android.content.Context;
import android.widget.FrameLayout;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.google.firebase.perf.plugin.util.AsmString;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: Carousel.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(bv = {}, d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\u0018\u00002\u00020\u0001B\u0011\b\u0016\u0012\u0006\u0010\u0018\u001a\u00020\u0017¢\u0006\u0004\b\u0019\u0010\u001aJ\b\u0010\u0003\u001a\u00020\u0002H\u0002J\u000e\u0010\u0006\u001a\u00020\u00022\u0006\u0010\u0005\u001a\u00020\u0004J\u0006\u0010\u0007\u001a\u00020\u0002J\u0006\u0010\b\u001a\u00020\u0002J\u000e\u0010\u000b\u001a\u00020\u00022\u0006\u0010\n\u001a\u00020\tJ\b\u0010\f\u001a\u0004\u0018\u00010\tJ\u000e\u0010\u000e\u001a\u00020\u00022\u0006\u0010\r\u001a\u00020\u0004J\u000e\u0010\u0010\u001a\u00020\u00022\u0006\u0010\u000f\u001a\u00020\u0004R\u0016\u0010\u0012\u001a\u00020\u00118\u0002@\u0002X\u0082.¢\u0006\u0006\n\u0004\b\u0012\u0010\u0013R\u0016\u0010\u0015\u001a\u00020\u00148\u0002@\u0002X\u0082.¢\u0006\u0006\n\u0004\b\u0015\u0010\u0016¨\u0006\u001b"}, d2 = {"Lcom/amazon/music/views/library/views/carousel/Carousel;", "Landroid/widget/FrameLayout;", "", "init", "", "numPrefetchViews", "setNumPrefetchViews", AsmString.METHOD_FIREBASE_PERF_TRACE_START, AsmString.METHOD_FIREBASE_PERF_TRACE_STOP, "Lcom/amazon/music/views/library/views/carousel/CarouselAdapter;", "carouselAdapter", "setAdapter", "getAdapter", "itemPadding", "setItemPadding", "stepTimeMilliseconds", "setStepTimeMilliseconds", "Lcom/amazon/music/views/library/views/carousel/CarouselRecyclerView;", "recyclerView", "Lcom/amazon/music/views/library/views/carousel/CarouselRecyclerView;", "Landroidx/recyclerview/widget/LinearLayoutManager;", "linearLayoutManager", "Landroidx/recyclerview/widget/LinearLayoutManager;", "Landroid/content/Context;", "context", "<init>", "(Landroid/content/Context;)V", "DMMViewLibrary_release"}, k = 1, mv = {1, 6, 0})
public final class Carousel extends FrameLayout {
    private LinearLayoutManager linearLayoutManager;
    private CarouselRecyclerView recyclerView;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public Carousel(Context context) {
        super(context);
        Intrinsics.checkNotNullParameter(context, "context");
        this.recyclerView = new CarouselRecyclerView(context);
        init();
    }

    private final void init() {
        this.linearLayoutManager = new LinearLayoutManager(getContext()) { // from class: com.amazon.music.views.library.views.carousel.Carousel.init.1
            @Override // androidx.recyclerview.widget.LinearLayoutManager
            protected int getExtraLayoutSpace(RecyclerView.State state) {
                Intrinsics.checkNotNullParameter(state, "state");
                CarouselRecyclerView carouselRecyclerView = Carousel.this.recyclerView;
                CarouselRecyclerView carouselRecyclerView2 = null;
                if (carouselRecyclerView == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("recyclerView");
                    carouselRecyclerView = null;
                }
                int width = carouselRecyclerView.getWidth();
                CarouselRecyclerView carouselRecyclerView3 = Carousel.this.recyclerView;
                if (carouselRecyclerView3 == null) {
                    Intrinsics.throwUninitializedPropertyAccessException("recyclerView");
                } else {
                    carouselRecyclerView2 = carouselRecyclerView3;
                }
                return width * carouselRecyclerView2.getNumPrefetchViews();
            }
        };
        CarouselRecyclerView carouselRecyclerView = this.recyclerView;
        CarouselRecyclerView carouselRecyclerView2 = null;
        if (carouselRecyclerView == null) {
            Intrinsics.throwUninitializedPropertyAccessException("recyclerView");
            carouselRecyclerView = null;
        }
        LinearLayoutManager linearLayoutManager = this.linearLayoutManager;
        if (linearLayoutManager == null) {
            Intrinsics.throwUninitializedPropertyAccessException("linearLayoutManager");
            linearLayoutManager = null;
        }
        carouselRecyclerView.setLayoutManager(linearLayoutManager);
        CarouselRecyclerView carouselRecyclerView3 = this.recyclerView;
        if (carouselRecyclerView3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("recyclerView");
            carouselRecyclerView3 = null;
        }
        LinearLayoutManager linearLayoutManager2 = this.linearLayoutManager;
        if (linearLayoutManager2 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("linearLayoutManager");
            linearLayoutManager2 = null;
        }
        carouselRecyclerView3.prepareAutoScrollingComponent$DMMViewLibrary_release(linearLayoutManager2);
        CarouselRecyclerView carouselRecyclerView4 = this.recyclerView;
        if (carouselRecyclerView4 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("recyclerView");
        } else {
            carouselRecyclerView2 = carouselRecyclerView4;
        }
        addView(carouselRecyclerView2, -1, -2);
    }

    public final void setNumPrefetchViews(int numPrefetchViews) {
        CarouselRecyclerView carouselRecyclerView = this.recyclerView;
        if (carouselRecyclerView == null) {
            Intrinsics.throwUninitializedPropertyAccessException("recyclerView");
            carouselRecyclerView = null;
        }
        carouselRecyclerView.setNumPrefetchViews(numPrefetchViews);
    }

    public final void start() {
        CarouselRecyclerView carouselRecyclerView = this.recyclerView;
        if (carouselRecyclerView == null) {
            Intrinsics.throwUninitializedPropertyAccessException("recyclerView");
            carouselRecyclerView = null;
        }
        carouselRecyclerView.start$DMMViewLibrary_release();
    }

    public final void stop() {
        CarouselRecyclerView carouselRecyclerView = this.recyclerView;
        if (carouselRecyclerView == null) {
            Intrinsics.throwUninitializedPropertyAccessException("recyclerView");
            carouselRecyclerView = null;
        }
        carouselRecyclerView.stop$DMMViewLibrary_release();
    }

    public final void setAdapter(CarouselAdapter carouselAdapter) {
        Intrinsics.checkNotNullParameter(carouselAdapter, "carouselAdapter");
        CarouselRecyclerView carouselRecyclerView = this.recyclerView;
        if (carouselRecyclerView == null) {
            Intrinsics.throwUninitializedPropertyAccessException("recyclerView");
            carouselRecyclerView = null;
        }
        carouselRecyclerView.setAdapter$DMMViewLibrary_release(carouselAdapter);
    }

    public final CarouselAdapter getAdapter() {
        CarouselRecyclerView carouselRecyclerView = this.recyclerView;
        CarouselRecyclerView carouselRecyclerView2 = null;
        if (carouselRecyclerView == null) {
            Intrinsics.throwUninitializedPropertyAccessException("recyclerView");
            carouselRecyclerView = null;
        }
        if (carouselRecyclerView.getAdapter() == null) {
            return null;
        }
        CarouselRecyclerView carouselRecyclerView3 = this.recyclerView;
        if (carouselRecyclerView3 == null) {
            Intrinsics.throwUninitializedPropertyAccessException("recyclerView");
        } else {
            carouselRecyclerView2 = carouselRecyclerView3;
        }
        RecyclerView.Adapter adapter = carouselRecyclerView2.getAdapter();
        if (adapter != null) {
            return (CarouselAdapter) adapter;
        }
        throw new NullPointerException("null cannot be cast to non-null type com.amazon.music.views.library.views.carousel.CarouselAdapter");
    }

    public final void setItemPadding(int itemPadding) {
        CarouselRecyclerView carouselRecyclerView = this.recyclerView;
        if (carouselRecyclerView == null) {
            Intrinsics.throwUninitializedPropertyAccessException("recyclerView");
            carouselRecyclerView = null;
        }
        carouselRecyclerView.setItemPadding$DMMViewLibrary_release(itemPadding);
    }

    public final void setStepTimeMilliseconds(int stepTimeMilliseconds) {
        CarouselRecyclerView carouselRecyclerView = this.recyclerView;
        if (carouselRecyclerView == null) {
            Intrinsics.throwUninitializedPropertyAccessException("recyclerView");
            carouselRecyclerView = null;
        }
        carouselRecyclerView.setStepTimeMilliseconds$DMMViewLibrary_release(stepTimeMilliseconds);
    }
}
