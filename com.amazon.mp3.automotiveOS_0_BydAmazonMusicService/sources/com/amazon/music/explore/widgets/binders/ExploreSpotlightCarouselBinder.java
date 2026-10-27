package com.amazon.music.explore.widgets.binders;

import Touch.WidgetsInterface.v1_0.SpotlightCarouselElement;
import android.content.Context;
import com.amazon.music.explore.widgets.models.SpotlightCarouselModel;
import com.amazon.music.skyfire.ui.widgets.adapters.SpotlightCarouselAdapter;
import com.amazon.music.skyfire.ui.widgets.views.SpotlightCarouselView;
import com.amazon.music.skyfire.ui.widgets.views.spotlightCarousel.SpotlightCarouselViewPager;
import com.amazon.music.views.library.binders.UniversalBinder;
import com.amazon.music.views.library.styles.StyleSheet;
import kotlin.jvm.internal.Reflection;
import kotlin.reflect.KClass;

/* JADX INFO: loaded from: classes4.dex */
public class ExploreSpotlightCarouselBinder implements UniversalBinder<SpotlightCarouselView, SpotlightCarouselModel> {
    private final Context context;
    private final StyleSheet styleSheet;

    public ExploreSpotlightCarouselBinder(Context context, StyleSheet styleSheet) {
        this.context = context;
        this.styleSheet = styleSheet;
    }

    @Override // com.amazon.music.views.library.binders.UniversalBinder
    public KClass<SpotlightCarouselModel> getModelClass() {
        return Reflection.createKotlinClass(SpotlightCarouselModel.class);
    }

    @Override // com.amazon.music.views.library.binders.UniversalBinder
    public SpotlightCarouselView createView(Context context) {
        return new SpotlightCarouselView(context, this.styleSheet);
    }

    @Override // com.amazon.music.views.library.binders.UniversalBinder
    public void bind(SpotlightCarouselView spotlightCarouselView, SpotlightCarouselModel spotlightCarouselModel) {
        if (spotlightCarouselView.isViewBound()) {
            return;
        }
        SpotlightCarouselElement widget = spotlightCarouselModel.getWidget();
        SpotlightCarouselAdapter spotlightCarouselAdapter = new SpotlightCarouselAdapter(this.context, spotlightCarouselModel.getOwnerId(), spotlightCarouselModel.getMethodsDispatcher(), widget.items());
        SpotlightCarouselViewPager viewPager = spotlightCarouselView.getViewPager();
        spotlightCarouselView.setImage(widget.image());
        viewPager.setAdapter(spotlightCarouselAdapter);
        viewPager.setCurrentItem(spotlightCarouselAdapter.getFirstPage());
        ViewTriggerUtility.getInstance().triggerElementView(spotlightCarouselModel, widget.onViewed());
        if (widget.items().size() > 1) {
            spotlightCarouselView.setLeftNavigationImage(widget.leftNavigation().image());
            spotlightCarouselView.setRightNavigationImage(widget.rightNavigation().image());
            spotlightCarouselView.setNavigationOnTouchListener(widget, viewPager, spotlightCarouselModel.getOwnerId(), spotlightCarouselModel.getMethodsDispatcher());
            spotlightCarouselView.initPageIndicator(widget.items().size());
            viewPager.setPagingEnabled(true);
            spotlightCarouselView.setPageChangeListener();
        }
        spotlightCarouselView.setIsViewBound(true);
    }
}
