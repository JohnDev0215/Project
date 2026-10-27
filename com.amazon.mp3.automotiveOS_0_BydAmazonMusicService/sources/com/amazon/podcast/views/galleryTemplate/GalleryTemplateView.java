package com.amazon.podcast.views.galleryTemplate;

import Podcast.Touch.GalleryTemplateInterface.v1_0.AddWidgetsMethod;
import Podcast.Touch.GalleryTemplateInterface.v1_0.EpisodeFeaturedPlayElement;
import Podcast.Touch.GalleryTemplateInterface.v1_0.GalleryHeaderElement;
import Podcast.Touch.GalleryTemplateInterface.v1_0.GalleryTemplate;
import Podcast.Touch.GalleryTemplateInterface.v1_0.PodcastFeaturedPlayElement;
import Podcast.Touch.GalleryTemplateInterface.v1_0.WidgetElement;
import SOACoreInterface.v1_0.Method;
import android.graphics.drawable.ColorDrawable;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.math.MathUtils;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.amazon.podcast.Podcast;
import com.amazon.podcast.R$color;
import com.amazon.podcast.R$id;
import com.amazon.podcast.R$layout;
import com.amazon.podcast.views.ActionBarView;
import com.amazon.podcast.views.BaseView;
import com.amazon.podcast.views.TemplateContext;
import com.amazon.podcast.views.UiMetrics;
import com.google.android.gms.common.util.CollectionUtils;
import com.google.android.material.appbar.AppBarLayout;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/* JADX INFO: loaded from: classes5.dex */
public final class GalleryTemplateView extends BaseView<GalleryTemplate> {
    private static final String TAG;
    private static final Logger logger;
    private float MAX_DRAWABLE_OPACITY;
    private float MAX_SCROLL_PERCENTAGE_FOR_OPACITY_UPDATE;
    private float MAX_TOOLBAR_OPACITY_VALUE;
    private float MIN_SCROLL_PERCENTAGE_FOR_OPACITY_UPDATE;
    private float MIN_TOOLBAR_OPACITY_VALUE;
    private ConstraintLayout actionBarLayout;
    private ActionBarView actionBarView;
    private WidgetsAdapter adapter;
    private AppBarLayout appBarView;
    private ImageView background;
    private ImageView baseBackground;
    private View emptyViewPlaceHolder;
    private ConstraintLayout galleryTemplateView;
    private View galleryView;
    private boolean isEndOfListTriggered;
    private LinearLayoutManager layoutManager;
    private List<Method> onEndOfList;
    private List<Method> onViewed;
    private RecyclerView recyclerView;
    private TemplateContext templateContext;

    static {
        String simpleName = GalleryTemplateView.class.getSimpleName();
        TAG = simpleName;
        logger = LoggerFactory.getLogger(simpleName);
    }

    public GalleryTemplateView(final TemplateContext templateContext) {
        super(templateContext);
        this.MIN_TOOLBAR_OPACITY_VALUE = 0.0f;
        this.MAX_TOOLBAR_OPACITY_VALUE = 0.85f;
        this.MIN_SCROLL_PERCENTAGE_FOR_OPACITY_UPDATE = 0.0f;
        this.MAX_SCROLL_PERCENTAGE_FOR_OPACITY_UPDATE = 0.1f;
        this.MAX_DRAWABLE_OPACITY = 255.0f;
        this.templateContext = templateContext;
        init();
    }

    private void init() {
        this.galleryView = View.inflate(getContext(), R$layout.podcast_gallery_template_view, this);
        ImageView imageView = (ImageView) findViewById(R$id.gallery_template_base_background);
        this.baseBackground = imageView;
        imageView.setBackgroundColor(Podcast.getRuntimeStyleSheet().getBaseColor());
        this.galleryTemplateView = (ConstraintLayout) findViewById(R$id.podcast_gallery_template_view);
        this.background = (ImageView) findViewById(R$id.gallery_template_view_background);
        this.recyclerView = (RecyclerView) findViewById(R$id.gallery_template_recycler_view);
        this.actionBarView = (ActionBarView) findViewById(R$id.action_bar_view);
        this.emptyViewPlaceHolder = findViewById(R$id.podcast_home_feature_play_placeholder);
        this.actionBarLayout = (ConstraintLayout) findViewById(R$id.podcast_action_bar_constraint_layout);
        this.actionBarView.bindBackButton(new View.OnClickListener() { // from class: com.amazon.podcast.views.galleryTemplate.GalleryTemplateView.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                GalleryTemplateView.this.handleBackPressed();
            }
        });
        this.appBarView = (AppBarLayout) findViewById(R$id.main_appbar);
        this.layoutManager = new LinearLayoutManager(getContext(), 1, false);
        this.adapter = new WidgetsAdapter(this.templateContext, getLifecycleOwner(), getOwnerId(), getResources(), getMethodsDispatcher());
        RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(-1, -2);
        layoutParams.setMargins(0, 0, 0, 0);
        this.actionBarLayout.setLayoutParams(layoutParams);
        this.recyclerView.setAdapter(this.adapter);
        this.recyclerView.setLayoutManager(this.layoutManager);
        addOnViewedListener(this.galleryView);
    }

    @Override // com.amazon.podcast.views.ViewBinder
    public void bind(final GalleryTemplate template, final boolean hideActionBar) {
        if (hideActionBar) {
            this.actionBarView.setVisibility(8);
        }
        this.adapter.setIsActionBarHidden(hideActionBar);
        bindOnViewed(template);
        this.onEndOfList = template.getOnEndOfList();
        this.isEndOfListTriggered = false;
        this.adapter = (WidgetsAdapter) this.recyclerView.getAdapter();
        if (template.getWidgets().size() > 0) {
            WidgetElement widgetElement = template.getWidgets().get(0);
            if ((widgetElement instanceof EpisodeFeaturedPlayElement) || (widgetElement instanceof PodcastFeaturedPlayElement)) {
                this.emptyViewPlaceHolder.setVisibility(8);
            } else {
                this.emptyViewPlaceHolder.setVisibility(0);
            }
        } else {
            this.emptyViewPlaceHolder.setVisibility(0);
        }
        GalleryHeaderElement headers = template.getHeaders();
        this.adapter.bind(template.getWidgets(), getHeader(headers), getSubheader(headers), template.getAbout());
        this.recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() { // from class: com.amazon.podcast.views.galleryTemplate.GalleryTemplateView.2
            @Override // androidx.recyclerview.widget.RecyclerView.OnScrollListener
            public void onScrolled(@NonNull final RecyclerView recyclerView, final int dx, final int dy) {
                super.onScrolled(recyclerView, dx, dy);
                if (GalleryTemplateView.this.layoutManager.getItemCount() - GalleryTemplateView.this.layoutManager.findLastVisibleItemPosition() <= 4 && !GalleryTemplateView.this.isEndOfListTriggered) {
                    GalleryTemplateView.this.getMethodsDispatcher().dispatch(GalleryTemplateView.this.getOwnerId(), GalleryTemplateView.this.onEndOfList);
                    GalleryTemplateView.this.isEndOfListTriggered = true;
                }
                if (dx != 0 || dy != 0) {
                    GalleryTemplateView.this.adapter.setIsLayoutComplete(true);
                } else {
                    GalleryTemplateView.this.adapter.setIsLayoutComplete(false);
                }
                GalleryTemplateView galleryTemplateView = GalleryTemplateView.this;
                galleryTemplateView.updateBackgroundOpacity(recyclerView, galleryTemplateView.appBarView);
            }
        });
        setNavBarTitle(headers);
    }

    @Override // com.amazon.podcast.views.ViewBinder
    public void handleTemplateMethod(final String ownerId, final Method method) {
        if (method instanceof AddWidgetsMethod) {
            addWidgetsMethod((AddWidgetsMethod) method);
        }
    }

    @Override // android.view.View
    protected void onWindowVisibilityChanged(final int visibility) {
        super.onWindowVisibilityChanged(visibility);
        WidgetsAdapter widgetsAdapter = this.adapter;
        if (widgetsAdapter == null || visibility == 0) {
            return;
        }
        widgetsAdapter.removeStateCallbacks();
    }

    @Override // com.amazon.podcast.views.ViewBinder
    public void onConfigurationChanged() {
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(), 1, false);
        this.layoutManager = linearLayoutManager;
        this.recyclerView.setLayoutManager(linearLayoutManager);
        WidgetsAdapter widgetsAdapter = new WidgetsAdapter(this.templateContext, getLifecycleOwner(), getOwnerId(), getResources(), getMethodsDispatcher());
        this.adapter = widgetsAdapter;
        this.recyclerView.setAdapter(widgetsAdapter);
    }

    @Override // com.amazon.podcast.views.UiMetrics
    public void handleOnViewed() {
        getMethodsDispatcher().dispatch(getOwnerId(), this.onViewed);
    }

    private void bindOnViewed(GalleryTemplate template) {
        if (CollectionUtils.isEmpty(this.onViewed)) {
            this.onViewed = template.getOnViewed();
            if (UiMetrics.isOnScreen(this.galleryView)) {
                handleOnViewed();
            }
        }
    }

    public void showActionBar() {
        this.actionBarView.setVisibility(0);
    }

    private void addWidgetsMethod(final AddWidgetsMethod method) {
        this.onEndOfList = method.getOnEndOfList();
        this.adapter.addItems(method.getWidgets());
        this.isEndOfListTriggered = false;
    }

    public void updateBackgroundOpacity(final RecyclerView recyclerView, final AppBarLayout appBarView) {
        float fClamp = MathUtils.clamp(getScrollPercentage(recyclerView), this.MIN_SCROLL_PERCENTAGE_FOR_OPACITY_UPDATE, this.MAX_SCROLL_PERCENTAGE_FOR_OPACITY_UPDATE);
        float f = this.MIN_SCROLL_PERCENTAGE_FOR_OPACITY_UPDATE;
        float f2 = (fClamp - f) / (this.MAX_SCROLL_PERCENTAGE_FOR_OPACITY_UPDATE - f);
        float f3 = this.MAX_TOOLBAR_OPACITY_VALUE;
        float f4 = this.MIN_TOOLBAR_OPACITY_VALUE;
        int iClamp = (int) (this.MAX_DRAWABLE_OPACITY * MathUtils.clamp(((f3 - f4) * f2) + f4, 0.0f, 1.0f));
        appBarView.getBackground().setAlpha(iClamp);
        ActionBar actionBar = getTemplateContext().getActionBarProvider().actionBar();
        if (actionBar != null) {
            ColorDrawable colorDrawable = new ColorDrawable();
            colorDrawable.setColor(getResources().getColor(R$color.background_dark_blue));
            colorDrawable.setAlpha(iClamp);
            actionBar.setBackgroundDrawable(colorDrawable);
        }
    }

    private float getScrollPercentage(final RecyclerView recyclerView) {
        float fComputeVerticalScrollOffset = recyclerView.computeVerticalScrollOffset();
        float fComputeVerticalScrollRange = recyclerView.computeVerticalScrollRange() - recyclerView.computeVerticalScrollExtent();
        if (fComputeVerticalScrollRange <= 0.0f) {
            return 1.0f;
        }
        return fComputeVerticalScrollOffset / fComputeVerticalScrollRange;
    }

    private void setNavBarTitle(final GalleryHeaderElement headers) {
        ViewTreeObserver viewTreeObserver = this.galleryTemplateView.getViewTreeObserver();
        if (viewTreeObserver == null) {
            return;
        }
        final Boolean showNavBarTextOnScroll = getShowNavBarTextOnScroll(headers);
        viewTreeObserver.addOnScrollChangedListener(new ViewTreeObserver.OnScrollChangedListener() { // from class: com.amazon.podcast.views.galleryTemplate.GalleryTemplateView$$ExternalSyntheticLambda0
            @Override // android.view.ViewTreeObserver.OnScrollChangedListener
            public final void onScrollChanged() {
                this.f$0.lambda$setNavBarTitle$0(showNavBarTextOnScroll, headers);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$setNavBarTitle$0(Boolean bool, GalleryHeaderElement galleryHeaderElement) {
        if (bool == null || !bool.booleanValue()) {
            this.actionBarView.bindTitle(getNavBarHeader(galleryHeaderElement));
        } else {
            showNavBarTextOnScroll(getNavBarHeader(galleryHeaderElement));
        }
    }

    private void showNavBarTextOnScroll(final String title) {
        if (isScrolledOverThreshold()) {
            this.actionBarView.bindTitle(title);
        } else {
            this.actionBarView.showTitle(false);
        }
    }

    private boolean isScrolledOverThreshold() {
        return this.recyclerView.computeVerticalScrollOffset() > this.actionBarView.getHeight();
    }

    private String getHeader(final GalleryHeaderElement headers) {
        if (headers == null) {
            return null;
        }
        return headers.getPageHeader();
    }

    private String getNavBarHeader(final GalleryHeaderElement headers) {
        if (headers == null) {
            return null;
        }
        return headers.getNavBarHeader();
    }

    private String getSubheader(final GalleryHeaderElement headers) {
        if (headers == null) {
            return null;
        }
        return headers.getPageSubheader();
    }

    private Boolean getShowNavBarTextOnScroll(final GalleryHeaderElement headers) {
        if (headers == null) {
            return null;
        }
        return headers.isShowNavBarTextOnScroll();
    }
}
