package com.amazon.podcast.views.galleryTemplate;

import Podcast.Touch.VerticalItemElementInterface.v1_0.VerticalItemElement;
import Podcast.Touch.VisualShovelerCompactElementInterface.v1_0.VisualShovelerCompactElement;
import SOACoreInterface.v1_0.Method;
import android.content.Context;
import android.content.res.Resources;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;
import androidx.annotation.NonNull;
import androidx.lifecycle.LifecycleOwner;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.amazon.music.views.library.providers.SeeMoreProvider;
import com.amazon.podcast.MethodsDispatcher;
import com.amazon.podcast.Podcast;
import com.amazon.podcast.R$dimen;
import com.amazon.podcast.R$id;
import com.amazon.podcast.R$integer;
import com.amazon.podcast.R$layout;
import com.amazon.podcast.metrics.UiMetricAttributes$ContentName;
import com.amazon.podcast.metrics.UiMetricAttributes$PageType;
import com.amazon.podcast.metrics.ui.UIContentViewCache.UIContentViewWidgetIndexCache;
import com.amazon.podcast.views.EmberTextView;
import com.amazon.podcast.views.browseHomeWidgets.PodcastVisualShovelerCompactModel;
import com.amazon.podcast.views.verticalItemView.VerticalItemMethods;
import java.util.List;
import org.apache.commons.lang3.StringUtils;

/* JADX INFO: loaded from: classes5.dex */
public final class VisualShovelerCompactView extends LinearLayout implements ScrollableWidgetView {
    private UiMetricAttributes$ContentName contentName;
    final UIContentViewWidgetIndexCache contentViewCache;
    private Context context;
    private EmberTextView header;
    private boolean isFirstView;
    private LinearLayoutManager layoutManager;
    private LifecycleOwner lifecycleOwner;
    private MethodsDispatcher methodsDispatcher;
    private List<Method> onContentFirstViewed;
    private List<Method> onViewed;
    private String ownerId;
    private UiMetricAttributes$PageType pageType;
    private RecyclerView recyclerView;
    private int recyclerViewMinHeight;
    private Resources resources;
    private EmberTextView seeMoreButton;
    private int verticalItemDimensions;
    private Integer visibleVerticalItemCount;
    private String widgetId;

    public VisualShovelerCompactView(final Context context, final AttributeSet attrs) {
        super(context, attrs);
        this.contentViewCache = UIContentViewWidgetIndexCache.INSTANCE;
        this.isFirstView = true;
        this.recyclerViewMinHeight = 0;
    }

    public VisualShovelerCompactView(final Context context, final String ownerId, final MethodsDispatcher methodsDispatcher, final LifecycleOwner lifecycleOwner) {
        super(context);
        this.contentViewCache = UIContentViewWidgetIndexCache.INSTANCE;
        this.isFirstView = true;
        this.recyclerViewMinHeight = 0;
        this.ownerId = ownerId;
        this.methodsDispatcher = methodsDispatcher;
        this.lifecycleOwner = lifecycleOwner;
        init();
    }

    public VisualShovelerCompactView(final Context context, final String ownerId, final MethodsDispatcher methodsDispatcher, final LifecycleOwner lifecycleOwner, final UiMetricAttributes$PageType pageType) {
        super(context);
        this.contentViewCache = UIContentViewWidgetIndexCache.INSTANCE;
        this.isFirstView = true;
        this.recyclerViewMinHeight = 0;
        this.context = context;
        this.ownerId = ownerId;
        this.methodsDispatcher = methodsDispatcher;
        this.lifecycleOwner = lifecycleOwner;
        this.resources = context.getResources();
        this.pageType = pageType;
        this.contentName = VerticalItemMethods.getContentName(pageType);
        init();
    }

    private void init() {
        View.inflate(getContext(), R$layout.podcast_visual_shoveler_compact, this);
        if (Podcast.getRuntimeStyleSheet() == null) {
            Podcast.initializeRuntimeStyleSheet(this.context);
        }
        Resources resources = getResources();
        this.visibleVerticalItemCount = Integer.valueOf(resources.getInteger(R$integer.podcast_vertical_item_count));
        this.verticalItemDimensions = getVerticalItemDimension(resources);
        this.header = (EmberTextView) findViewById(R$id.visual_shoveler_compact_header);
        if (Podcast.getRuntimeStyleSheet() != null) {
            this.header.setTypeface(Podcast.getRuntimeStyleSheet().getHeadline4TypeFace());
        }
        this.seeMoreButton = (EmberTextView) findViewById(R$id.visual_shoveler_see_all_button);
        this.recyclerView = (RecyclerView) findViewById(R$id.visual_shoveler_compact_recycler_view);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getContext(), 0, false);
        this.layoutManager = linearLayoutManager;
        this.recyclerView.setLayoutManager(linearLayoutManager);
        this.recyclerView.setAdapter(new VisualShovelerCompactAdapter(this.ownerId, this.methodsDispatcher, getResources(), this.verticalItemDimensions, this.lifecycleOwner, getContext()));
    }

    public void bind(final VisualShovelerCompactElement element) {
        this.header.setText(element.getHeader());
        List<VerticalItemElement> items = element.getItems();
        if (this.visibleVerticalItemCount != null && items.size() <= this.visibleVerticalItemCount.intValue() + 1) {
            this.seeMoreButton.setVisibility(4);
        } else {
            this.seeMoreButton.setVisibility(0);
            this.seeMoreButton.setText(element.getSeeMore().getText());
            this.seeMoreButton.setOnClickListener(new View.OnClickListener() { // from class: com.amazon.podcast.views.galleryTemplate.VisualShovelerCompactView.1
                @Override // android.view.View.OnClickListener
                public void onClick(View view) {
                    VisualShovelerCompactView.this.methodsDispatcher.dispatch(VisualShovelerCompactView.this.ownerId, element.getSeeMore().getOnItemSelected());
                }
            });
        }
        VisualShovelerCompactAdapter visualShovelerCompactAdapter = (VisualShovelerCompactAdapter) this.recyclerView.getAdapter();
        this.widgetId = element.getId();
        visualShovelerCompactAdapter.bind(items);
        this.onViewed = element.getOnViewed();
        this.onContentFirstViewed = element.getOnContentFirstViewed();
        if (StringUtils.isBlank(this.widgetId)) {
            return;
        }
        this.contentViewCache.addWidget(this.widgetId);
        this.contentViewCache.updateNumItemsAccessible(this.widgetId, items.size());
        handleOnContentFirstViewed();
        this.recyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() { // from class: com.amazon.podcast.views.galleryTemplate.VisualShovelerCompactView.2
            @Override // androidx.recyclerview.widget.RecyclerView.OnScrollListener
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                int measuredHeight = recyclerView.getMeasuredHeight();
                if (measuredHeight > VisualShovelerCompactView.this.recyclerViewMinHeight) {
                    VisualShovelerCompactView.this.recyclerViewMinHeight = measuredHeight;
                    recyclerView.setMinimumHeight(VisualShovelerCompactView.this.recyclerViewMinHeight);
                }
            }
        });
    }

    public void bind(final VisualShovelerCompactElement element, final PodcastVisualShovelerCompactModel model, final SeeMoreProvider seeMoreProvider) {
        this.header.setText(element.getHeader());
        List<VerticalItemElement> items = element.getItems();
        if ((this.visibleVerticalItemCount != null && items.size() <= this.visibleVerticalItemCount.intValue() + 1) || element.getSeeMore() == null) {
            this.seeMoreButton.setVisibility(4);
        } else {
            this.seeMoreButton.setVisibility(0);
            this.seeMoreButton.setText(element.getSeeMore().getText());
            this.seeMoreButton.setOnClickListener(new View.OnClickListener() { // from class: com.amazon.podcast.views.galleryTemplate.VisualShovelerCompactView.3
                @Override // android.view.View.OnClickListener
                public void onClick(View view) {
                    VisualShovelerCompactView.this.onClickSeeMoreButton(element, model, seeMoreProvider);
                }
            });
        }
        VisualShovelerCompactAdapter visualShovelerCompactAdapter = (VisualShovelerCompactAdapter) this.recyclerView.getAdapter();
        this.widgetId = element.getId();
        visualShovelerCompactAdapter.bind(items);
        this.onViewed = element.getOnViewed();
        this.onContentFirstViewed = element.getOnContentFirstViewed();
        if (StringUtils.isBlank(this.widgetId)) {
            return;
        }
        this.contentViewCache.addWidget(this.widgetId);
        this.contentViewCache.updateNumItemsAccessible(this.widgetId, items.size());
        handleOnContentFirstViewed();
    }

    public void handleOnViewed() {
        this.methodsDispatcher.dispatch(this.ownerId, this.onViewed);
    }

    private void handleOnContentFirstViewed() {
        if (this.isFirstView) {
            addOnScrollListener(this.recyclerView, this.widgetId, this.methodsDispatcher, this.ownerId, this.onContentFirstViewed);
            handleFirstOnContentFirstViewed(this.recyclerView, this.widgetId, this.methodsDispatcher, this.ownerId, this.onContentFirstViewed);
            this.isFirstView = false;
        }
    }

    private int getVerticalItemDimension(final Resources resources) {
        int i = resources.getDisplayMetrics().widthPixels;
        int dimensionPixelSize = resources.getDimensionPixelSize(R$dimen.podcast_spacer_giant) * 3;
        int iIntValue = this.visibleVerticalItemCount.intValue();
        int i2 = R$dimen.gutter;
        int dimensionPixelSize2 = iIntValue * resources.getDimensionPixelSize(i2);
        int dimensionPixelSize3 = resources.getDimensionPixelSize(R$dimen.margin);
        int dimensionPixelSize4 = resources.getDimensionPixelSize(i2);
        int dimensionPixelSize5 = resources.getDimensionPixelSize(R$dimen.podcast_min_vertical_item_width);
        int iIntValue2 = (i - (((dimensionPixelSize2 + dimensionPixelSize3) + dimensionPixelSize4) + dimensionPixelSize)) / this.visibleVerticalItemCount.intValue();
        return iIntValue2 < dimensionPixelSize5 ? dimensionPixelSize5 : iIntValue2;
    }

    public void setOwnerId(final String ownerId) {
        this.ownerId = ownerId;
    }

    public void setMethodsDispatcher(final MethodsDispatcher methodsDispatcher) {
        this.methodsDispatcher = methodsDispatcher;
    }

    public void setLifeCycleOwner(final LifecycleOwner lifecycleOwner) {
        this.lifecycleOwner = lifecycleOwner;
    }

    public void initInflatedView() {
        init();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void onClickSeeMoreButton(final VisualShovelerCompactElement element, final PodcastVisualShovelerCompactModel model, final SeeMoreProvider seeMoreProvider) {
        if (seeMoreProvider == null || model == null) {
            this.methodsDispatcher.dispatch(this.ownerId, element.getSeeMore().getOnItemSelected());
        } else {
            seeMoreProvider.onSeeMoreClick(model.getSeeMoreUri(), model.getViewReferenceId(), null);
        }
    }
}
