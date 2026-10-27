package com.amazon.music.skyfire.ui.widgets.binders;

import CoreInterface.v1_0.Method;
import Touch.WidgetsInterface.v1_0.SpotlightCarouselElement;
import android.view.ViewGroup;
import com.amazon.music.skyfire.ui.skyfire.DispatcherContext;
import com.amazon.music.skyfire.ui.utils.OnViewedTrigger;
import com.amazon.music.skyfire.ui.widgets.adapters.SpotlightCarouselAdapter;
import com.amazon.music.skyfire.ui.widgets.views.SpotlightCarouselView;
import com.amazon.music.skyfire.ui.widgets.views.spotlightCarousel.SpotlightCarouselViewPager;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: SpotlightCarouselBinder.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u000e\u0012\u0004\u0012\u00020\u0002\u0012\u0004\u0012\u00020\u00030\u0001B\u0005¢\u0006\u0002\u0010\u0004J\u0018\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\u00022\u0006\u0010\b\u001a\u00020\u0003H\u0016J\u0012\u0010\t\u001a\u00020\u00032\b\u0010\n\u001a\u0004\u0018\u00010\u000bH\u0016¨\u0006\f"}, d2 = {"Lcom/amazon/music/skyfire/ui/widgets/binders/SpotlightCarouselBinder;", "Lcom/amazon/music/skyfire/ui/widgets/binders/ElementBinder;", "LTouch/WidgetsInterface/v1_0/SpotlightCarouselElement;", "Lcom/amazon/music/skyfire/ui/widgets/views/SpotlightCarouselView;", "()V", "bind", "", "element", "view", "createView", "parent", "Landroid/view/ViewGroup;", "DMSFCoreAndroidUI_release"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class SpotlightCarouselBinder extends ElementBinder<SpotlightCarouselElement, SpotlightCarouselView> {
    @Override // com.amazon.music.skyfire.ui.widgets.binders.ElementBinder
    public SpotlightCarouselView createView(ViewGroup parent) {
        return new SpotlightCarouselView(getContext(), getStyleSheet());
    }

    @Override // com.amazon.music.skyfire.ui.widgets.binders.ElementBinder
    public void bind(SpotlightCarouselElement element, SpotlightCarouselView view) {
        Intrinsics.checkNotNullParameter(element, "element");
        Intrinsics.checkNotNullParameter(view, "view");
        if (view.isViewBound()) {
            return;
        }
        SpotlightCarouselAdapter spotlightCarouselAdapter = new SpotlightCarouselAdapter(getContext(), getOwnerId(), getMethodsDispatcher(), element.items());
        SpotlightCarouselViewPager viewPager = view.getViewPager();
        view.setImage(element.image());
        viewPager.setAdapter(spotlightCarouselAdapter);
        viewPager.setCurrentItem(spotlightCarouselAdapter.getFirstPage());
        if (element.items().size() > 1) {
            view.setLeftNavigationImage(element.leftNavigation().image());
            view.setRightNavigationImage(element.rightNavigation().image());
            view.setNavigationOnTouchListener(element, viewPager, getOwnerId(), getMethodsDispatcher());
            view.initPageIndicator(element.items().size());
            viewPager.setPagingEnabled(true);
            view.setPageChangeListener();
        }
        view.setIsViewBound(true);
        OnViewedTrigger.Companion companion = OnViewedTrigger.INSTANCE;
        DispatcherContext dispatcherContext = getDispatcherContext();
        List<Method> listOnViewed = element.onViewed();
        Intrinsics.checkNotNullExpressionValue(listOnViewed, "element.onViewed()");
        companion.emitOnViewed(dispatcherContext, listOnViewed);
    }
}
