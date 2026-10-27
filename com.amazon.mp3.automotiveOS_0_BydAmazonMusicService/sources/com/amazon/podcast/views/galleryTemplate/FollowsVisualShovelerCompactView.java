package com.amazon.podcast.views.galleryTemplate;

import Podcast.Touch.VerticalItemElementInterface.v1_0.VerticalItemElement;
import Podcast.Touch.WidgetElementsInterface.v1_0.FollowsElement;
import SOACoreInterface.v1_0.Method;
import android.content.Context;
import android.content.res.Resources;
import android.view.View;
import android.widget.LinearLayout;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.amazon.podcast.MethodsDispatcher;
import com.amazon.podcast.Podcast;
import com.amazon.podcast.R$dimen;
import com.amazon.podcast.R$id;
import com.amazon.podcast.R$integer;
import com.amazon.podcast.R$layout;
import com.amazon.podcast.deeplinks.DeeplinkMethods;
import com.amazon.podcast.follow.Follow;
import com.amazon.podcast.metrics.UiMetricAttributes$ActionType;
import com.amazon.podcast.metrics.UiMetricAttributes$ContentName;
import com.amazon.podcast.metrics.UiMetricAttributes$EntityIdType;
import com.amazon.podcast.metrics.UiMetricAttributes$EntityType;
import com.amazon.podcast.metrics.UiMetricAttributes$PageType;
import com.amazon.podcast.metrics.UiMetricMethods;
import com.amazon.podcast.metrics.ui.UIContentViewCache.UIContentViewWidgetIndexCache;
import com.amazon.podcast.redhoodPlus.MetricsEntityIds;
import com.amazon.podcast.views.EmberTextView;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.lang3.StringUtils;

/* JADX INFO: loaded from: classes5.dex */
final class FollowsVisualShovelerCompactView extends LinearLayout implements ScrollableWidgetView {
    final UIContentViewWidgetIndexCache contentViewCache;
    private EmberTextView header;
    private boolean isFirstView;
    private final boolean isRedhoodPlusMetricsEnabled;
    private LifecycleOwner lifecycleOwner;
    private MethodsDispatcher methodsDispatcher;
    private List<Method> onContentFirstViewed;
    private List<Method> onViewed;
    private String ownerId;
    private RecyclerView recyclerView;
    private EmberTextView seeMoreButton;
    private int verticalItemDimensions;
    private View view;
    private Integer visibleVerticalItemCount;
    private String widgetId;

    public FollowsVisualShovelerCompactView(final Context context, final String ownerId, final MethodsDispatcher methodsDispatcher, final LifecycleOwner lifecycleOwner) {
        super(context);
        this.contentViewCache = UIContentViewWidgetIndexCache.INSTANCE;
        this.isFirstView = true;
        this.ownerId = ownerId;
        this.methodsDispatcher = methodsDispatcher;
        this.lifecycleOwner = lifecycleOwner;
        this.isRedhoodPlusMetricsEnabled = Podcast.getFeatureGatePreferences().isRedhoodPlusUXEnabled();
        init();
    }

    private void init() {
        View.inflate(getContext(), R$layout.podcast_visual_shoveler_compact, this);
        Resources resources = getResources();
        this.visibleVerticalItemCount = Integer.valueOf(resources.getInteger(R$integer.podcast_vertical_item_count));
        this.verticalItemDimensions = getVerticalItemDimension(resources);
        this.view = findViewById(R$id.visual_shoveler_compact_view);
        EmberTextView emberTextView = (EmberTextView) findViewById(R$id.visual_shoveler_compact_header);
        this.header = emberTextView;
        emberTextView.setTypeface(Podcast.getRuntimeStyleSheet().getHeadline4TypeFace());
        this.seeMoreButton = (EmberTextView) findViewById(R$id.visual_shoveler_see_all_button);
        this.recyclerView = (RecyclerView) findViewById(R$id.visual_shoveler_compact_recycler_view);
        this.recyclerView.setLayoutManager(new LinearLayoutManager(getContext(), 0, false));
        this.recyclerView.setAdapter(new FollowsVisualShovelerCompactAdapter(this.ownerId, this.methodsDispatcher, getResources(), this.verticalItemDimensions, this.lifecycleOwner, getContext()));
    }

    public void bind(final LifecycleOwner lifecycleOwner, final FollowsElement followsElement) {
        this.widgetId = followsElement.getId();
        this.onViewed = followsElement.getOnViewed();
        this.onContentFirstViewed = followsElement.getOnContentFirstViewed();
        final boolean z = !StringUtils.isBlank(this.widgetId);
        Podcast.getAppSync().follow().readAll(getContext()).observe(lifecycleOwner, new Observer<List<Follow>>() { // from class: com.amazon.podcast.views.galleryTemplate.FollowsVisualShovelerCompactView.1
            @Override // androidx.lifecycle.Observer
            public void onChanged(List<Follow> follows) {
                if (follows == null || follows.isEmpty()) {
                    FollowsVisualShovelerCompactView.this.view.setVisibility(8);
                    return;
                }
                FollowsVisualShovelerCompactView.this.view.setVisibility(0);
                FollowsVisualShovelerCompactView.this.header.setText(followsElement.getHeader());
                List<VerticalItemElement> listVerticalItemElements = FollowsVisualShovelerCompactView.this.verticalItemElements(follows);
                if (FollowsVisualShovelerCompactView.this.visibleVerticalItemCount == null || listVerticalItemElements.size() > FollowsVisualShovelerCompactView.this.visibleVerticalItemCount.intValue() + 1) {
                    FollowsVisualShovelerCompactView.this.seeMoreButton.setVisibility(0);
                    FollowsVisualShovelerCompactView.this.seeMoreButton.setText(followsElement.getSeeMoreText());
                    FollowsVisualShovelerCompactView.this.seeMoreButton.setOnClickListener(new View.OnClickListener() { // from class: com.amazon.podcast.views.galleryTemplate.FollowsVisualShovelerCompactView.1.1
                        @Override // android.view.View.OnClickListener
                        public void onClick(View view) {
                            FollowsVisualShovelerCompactView.this.methodsDispatcher.dispatch(FollowsVisualShovelerCompactView.this.ownerId, followsElement.getOnSeeMoreSelected());
                        }
                    });
                } else {
                    FollowsVisualShovelerCompactView.this.seeMoreButton.setVisibility(4);
                }
                ((FollowsVisualShovelerCompactAdapter) FollowsVisualShovelerCompactView.this.recyclerView.getAdapter()).bind(listVerticalItemElements);
                if (z) {
                    FollowsVisualShovelerCompactView followsVisualShovelerCompactView = FollowsVisualShovelerCompactView.this;
                    followsVisualShovelerCompactView.contentViewCache.addWidget(followsVisualShovelerCompactView.widgetId);
                    FollowsVisualShovelerCompactView followsVisualShovelerCompactView2 = FollowsVisualShovelerCompactView.this;
                    followsVisualShovelerCompactView2.contentViewCache.updateNumItemsAccessible(followsVisualShovelerCompactView2.widgetId, follows.size());
                }
            }
        });
        if (z) {
            handleOnContentFirstViewed();
        }
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

    /* JADX INFO: Access modifiers changed from: private */
    public List<VerticalItemElement> verticalItemElements(final List<Follow> follows) {
        ArrayList arrayList = new ArrayList();
        int i = 0;
        for (Follow follow : follows) {
            ArrayList arrayList2 = new ArrayList();
            arrayList2.addAll(DeeplinkMethods.podcast(follow, getResources()));
            arrayList2.addAll(UiMetricMethods.onClicked(UiMetricAttributes$PageType.PODCASTS_HOME, UiMetricAttributes$ActionType.SELECT_PODCAST_SHOW, UiMetricAttributes$EntityType.PODCAST_SHOW, UiMetricAttributes$EntityIdType.PODCAST_SHOW_ID, MetricsEntityIds.podcastShow(this.isRedhoodPlusMetricsEnabled, follow.getId(), follow.getPodcastShowVariantId()), Integer.valueOf(i), UiMetricAttributes$ContentName.PODCASTS_FOLLOWS_SHOVELER));
            arrayList.add(VerticalItemElement.builder().withOnItemSelected(arrayList2).withImage(follow.getImage()).build());
            i++;
        }
        return arrayList;
    }
}
