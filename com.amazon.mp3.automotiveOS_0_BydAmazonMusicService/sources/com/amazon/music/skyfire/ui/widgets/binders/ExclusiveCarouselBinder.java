package com.amazon.music.skyfire.ui.widgets.binders;

import CoreInterface.v1_0.Method;
import Touch.WidgetsInterface.v1_0.ExclusiveCarouselElement;
import Touch.WidgetsInterface.v1_0.ExclusiveCarouselSlideElement;
import android.view.ViewGroup;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.PagerSnapHelper;
import androidx.recyclerview.widget.RecyclerView;
import com.amazon.music.skyfire.ui.skyfire.DispatcherContext;
import com.amazon.music.skyfire.ui.utils.OnViewedTrigger;
import com.amazon.music.skyfire.ui.widgets.adapters.ExclusiveCarouselAdapter;
import com.amazon.music.skyfire.ui.widgets.decorators.PageDotsDecorator;
import com.amazon.music.skyfire.ui.widgets.views.ExclusiveCarouselView;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: ExclusiveCarouselBinder.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0006\u0018\u0000 \u00102\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00030\u0001:\u0001\u0010B\u0005¢\u0006\u0002\u0010\u0004J\u0018\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\u00022\u0006\u0010\b\u001a\u00020\u0003H\u0016J\u0012\u0010\t\u001a\u00020\u00032\b\u0010\n\u001a\u0004\u0018\u00010\u000bH\u0016J\u0010\u0010\f\u001a\u00020\u00062\u0006\u0010\r\u001a\u00020\u0003H\u0002J\u0018\u0010\u000e\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\u00022\u0006\u0010\b\u001a\u00020\u0003H\u0016J\u0018\u0010\u000f\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\u00022\u0006\u0010\b\u001a\u00020\u0003H\u0016¨\u0006\u0011"}, d2 = {"Lcom/amazon/music/skyfire/ui/widgets/binders/ExclusiveCarouselBinder;", "Lcom/amazon/music/skyfire/ui/widgets/binders/ElementBinder;", "LTouch/WidgetsInterface/v1_0/ExclusiveCarouselElement;", "Lcom/amazon/music/skyfire/ui/widgets/views/ExclusiveCarouselView;", "()V", "bind", "", "element", "view", "createView", "parent", "Landroid/view/ViewGroup;", "initCarousel", "carouselView", "onAttached", "onDetached", "Companion", "DMSFCoreAndroidUI_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class ExclusiveCarouselBinder extends ElementBinder<ExclusiveCarouselElement, ExclusiveCarouselView> {
    public static final int PAGE_INDICATOR_DURATION_SEC = 7;

    @Override // com.amazon.music.skyfire.ui.widgets.binders.ElementBinder
    public ExclusiveCarouselView createView(ViewGroup parent) {
        ExclusiveCarouselView exclusiveCarouselView = new ExclusiveCarouselView(getContext());
        initCarousel(exclusiveCarouselView);
        return exclusiveCarouselView;
    }

    @Override // com.amazon.music.skyfire.ui.widgets.binders.ElementBinder
    public void bind(ExclusiveCarouselElement element, ExclusiveCarouselView view) {
        Intrinsics.checkNotNullParameter(element, "element");
        Intrinsics.checkNotNullParameter(view, "view");
        RecyclerView recyclerView = view.getRecyclerView();
        RecyclerView.Adapter adapter = recyclerView.getAdapter();
        if (adapter == null) {
            throw new NullPointerException("null cannot be cast to non-null type com.amazon.music.skyfire.ui.widgets.adapters.ExclusiveCarouselAdapter");
        }
        ExclusiveCarouselAdapter exclusiveCarouselAdapter = (ExclusiveCarouselAdapter) adapter;
        List<ExclusiveCarouselSlideElement> listItems = element.items();
        Intrinsics.checkNotNullExpressionValue(listItems, "element.items()");
        exclusiveCarouselAdapter.resetItems(listItems);
        recyclerView.addItemDecoration(new PageDotsDecorator(getContext(), exclusiveCarouselAdapter.getItemCount()));
        view.setMarkerDurationSeconds(7);
        OnViewedTrigger.Companion companion = OnViewedTrigger.INSTANCE;
        DispatcherContext dispatcherContext = getDispatcherContext();
        List<Method> listOnViewed = element.onViewed();
        Intrinsics.checkNotNullExpressionValue(listOnViewed, "element.onViewed()");
        companion.emitOnViewed(dispatcherContext, listOnViewed);
    }

    @Override // com.amazon.music.skyfire.ui.widgets.binders.ElementBinder
    public void onAttached(ExclusiveCarouselElement element, ExclusiveCarouselView view) {
        Intrinsics.checkNotNullParameter(element, "element");
        Intrinsics.checkNotNullParameter(view, "view");
        view.startTimer();
    }

    @Override // com.amazon.music.skyfire.ui.widgets.binders.ElementBinder
    public void onDetached(ExclusiveCarouselElement element, ExclusiveCarouselView view) {
        Intrinsics.checkNotNullParameter(element, "element");
        Intrinsics.checkNotNullParameter(view, "view");
        view.stopTimer();
    }

    private final void initCarousel(ExclusiveCarouselView carouselView) {
        DispatcherContext dispatcherContext = getDispatcherContext();
        if (dispatcherContext == null) {
            return;
        }
        ExclusiveCarouselAdapter exclusiveCarouselAdapter = new ExclusiveCarouselAdapter(getStyleSheet(), dispatcherContext);
        RecyclerView recyclerView = carouselView.getRecyclerView();
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(recyclerView.getContext(), 0, false));
        recyclerView.setAdapter(exclusiveCarouselAdapter);
        new PagerSnapHelper().attachToRecyclerView(recyclerView);
    }
}
