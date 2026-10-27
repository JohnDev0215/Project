package com.amazon.podcast.views.latestTemplate;

import Podcast.Touch.EpisodeRowItemsInterface.v1_0.EpisodeRowItemElement;
import Podcast.Touch.LatestTemplateInterface.v2_0.AddEpisodeRowItemsMethod;
import Podcast.Touch.LatestTemplateInterface.v2_0.FeaturedItemWriteCacheElement;
import Podcast.Touch.LatestTemplateInterface.v2_0.LatestTemplate;
import SOACoreInterface.v1_0.Method;
import android.content.res.Resources;
import android.view.View;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import com.amazon.podcast.BootstrapMethods;
import com.amazon.podcast.Handlers;
import com.amazon.podcast.MethodsDispatcher;
import com.amazon.podcast.Podcast;
import com.amazon.podcast.R$id;
import com.amazon.podcast.R$layout;
import com.amazon.podcast.R$string;
import com.amazon.podcast.caches.Caches;
import com.amazon.podcast.deeplinks.DeeplinkMethods;
import com.amazon.podcast.featuregating.PodcastFeatureGating;
import com.amazon.podcast.metrics.UiMetricAttributes$ActionType;
import com.amazon.podcast.metrics.UiMetricAttributes$ContentName;
import com.amazon.podcast.metrics.UiMetricAttributes$PageType;
import com.amazon.podcast.metrics.UiMetricMethods;
import com.amazon.podcast.views.ActionBarView;
import com.amazon.podcast.views.BaseView;
import com.amazon.podcast.views.EmberTextView;
import com.amazon.podcast.views.MessageView;
import com.amazon.podcast.views.TemplateContext;
import com.amazon.podcast.views.UiMetrics;
import com.amazon.podcast.views.episodeRowItemsListView.EpisodeRowItemView;
import com.amazon.podcast.views.episodeRowItemsListView.EpisodeRowItemsListView;
import com.google.android.gms.common.util.CollectionUtils;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/* JADX INFO: loaded from: classes5.dex */
public final class LatestTemplateViewV2 extends BaseView<LatestTemplate> {
    private static final String TAG;
    private static final Logger logger;
    private ActionBarView actionBarView;
    private EpisodeRowItemsListView episodeRowItemsListView;
    private EpisodeRowItemView featuredSingleRowItem;
    private EmberTextView featuredSingleRowItemLabel;
    private EmberTextView headerText;
    private View latestTemplateView;
    private LiveData<FeaturedItemWriteCacheElement> liveFeaturedPinnedElement;
    private MessageView messageView;
    private List<Method> onEndOfList;
    private List<Method> onViewed;

    static {
        String simpleName = LatestTemplateViewV2.class.getSimpleName();
        TAG = simpleName;
        logger = LoggerFactory.getLogger(simpleName);
    }

    public LatestTemplateViewV2(final TemplateContext templateContext) {
        super(templateContext);
        init();
    }

    private final void init() {
        this.latestTemplateView = View.inflate(getContext(), R$layout.podcast_latest_template_view_v2, this);
        this.episodeRowItemsListView = (EpisodeRowItemsListView) findViewById(R$id.podcast_latest_template_v2_episode_row_items_list_view);
        this.featuredSingleRowItem = (EpisodeRowItemView) findViewById(R$id.podcast_featured_single_row_episode_item);
        EmberTextView emberTextView = (EmberTextView) findViewById(R$id.podcast_feature_single_row_item_label);
        this.featuredSingleRowItemLabel = emberTextView;
        emberTextView.setTypeface(Podcast.getRuntimeStyleSheet().getHeadline4TypeFace());
        ActionBarView actionBarView = (ActionBarView) findViewById(R$id.action_bar_view);
        this.actionBarView = actionBarView;
        actionBarView.bindBackButton(new View.OnClickListener() { // from class: com.amazon.podcast.views.latestTemplate.LatestTemplateViewV2.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                LatestTemplateViewV2.this.handleBackPressed();
            }
        });
        this.messageView = (MessageView) findViewById(R$id.podcast_latest_template_v2_message_view);
        EmberTextView emberTextView2 = (EmberTextView) findViewById(R$id.latest_template_v2_header_text);
        this.headerText = emberTextView2;
        emberTextView2.setTypeface(Podcast.getRuntimeStyleSheet().getHeadline4TypeFace());
        addOnViewedListener(this.latestTemplateView);
        EpisodeRowItemsListView episodeRowItemsListView = this.episodeRowItemsListView;
        String ownerId = getOwnerId();
        MethodsDispatcher methodsDispatcher = getMethodsDispatcher();
        LifecycleOwner lifecycleOwner = getLifecycleOwner();
        TemplateContext templateContext = getTemplateContext();
        UiMetricAttributes$PageType uiMetricAttributes$PageType = UiMetricAttributes$PageType.PODCASTS_LIBRARY_LATEST;
        episodeRowItemsListView.create(ownerId, methodsDispatcher, lifecycleOwner, templateContext, uiMetricAttributes$PageType);
        this.featuredSingleRowItem.bindMetadata(getOwnerId(), getMethodsDispatcher(), getLifecycleOwner(), getTemplateContext(), uiMetricAttributes$PageType);
    }

    @Override // com.amazon.podcast.views.ViewBinder
    public void bind(final LatestTemplate template, final boolean hideActionBar) {
        if (hideActionBar) {
            this.actionBarView.setVisibility(8);
        }
        bindOnViewed(template);
        this.onEndOfList = template.getOnEndOfList();
        if (template.getFeaturedPinnedElement() != null) {
            initiateFeaturedPinnedElementObserver();
        }
        List<EpisodeRowItemElement> items = template.getItems();
        if (items == null || items.isEmpty()) {
            showMessageView();
            return;
        }
        this.messageView.setVisibility(8);
        this.headerText.setText(template.getHeader());
        this.episodeRowItemsListView.bind(items, template.getOnEndOfList(), template.getOnViewed());
        this.episodeRowItemsListView.addFooter();
    }

    @Override // com.amazon.podcast.views.ViewBinder
    public void handleTemplateMethod(final String ownerId, final Method method) {
        if (method instanceof AddEpisodeRowItemsMethod) {
            AddEpisodeRowItemsMethod addEpisodeRowItemsMethod = (AddEpisodeRowItemsMethod) method;
            this.episodeRowItemsListView.addItems(addEpisodeRowItemsMethod.getOnEndOfList(), addEpisodeRowItemsMethod.getOnViewed(), addEpisodeRowItemsMethod.getItems());
        }
    }

    @Override // com.amazon.podcast.views.ViewBinder
    public void onConfigurationChanged() {
        this.episodeRowItemsListView.onConfigurationChanged();
    }

    @Override // com.amazon.podcast.views.UiMetrics
    public void handleOnViewed() {
        getMethodsDispatcher().dispatch(getOwnerId(), this.onViewed);
    }

    private void initiateFeaturedPinnedElementObserver() {
        LiveData<FeaturedItemWriteCacheElement> liveData = this.liveFeaturedPinnedElement;
        if (liveData != null) {
            liveData.removeObservers(getLifecycleOwner());
        }
        LiveData<FeaturedItemWriteCacheElement> liveData2 = Caches.INSTANCE.latestTemplateV2FeaturedItemCache().read();
        this.liveFeaturedPinnedElement = liveData2;
        liveData2.observe(getLifecycleOwner(), new Observer<FeaturedItemWriteCacheElement>() { // from class: com.amazon.podcast.views.latestTemplate.LatestTemplateViewV2.2
            @Override // androidx.lifecycle.Observer
            public void onChanged(FeaturedItemWriteCacheElement element) {
                if (element == null || element.getItem() == null) {
                    LatestTemplateViewV2.this.featuredSingleRowItemLabel.setVisibility(8);
                    LatestTemplateViewV2.this.featuredSingleRowItem.setVisibility(8);
                } else {
                    LatestTemplateViewV2.this.bindFeaturedPinnedItem(element);
                }
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void bindFeaturedPinnedItem(final FeaturedItemWriteCacheElement element) {
        Handlers.INSTANCE.getForeground().postDelayed(new Runnable() { // from class: com.amazon.podcast.views.latestTemplate.LatestTemplateViewV2.3
            @Override // java.lang.Runnable
            public void run() {
                String label = element.getItem().getLabel();
                if (!StringUtils.isBlank(label)) {
                    LatestTemplateViewV2.this.featuredSingleRowItemLabel.setText(label);
                    LatestTemplateViewV2.this.featuredSingleRowItemLabel.setVisibility(0);
                }
                LatestTemplateViewV2.this.featuredSingleRowItem.bind(element.getItem());
                LatestTemplateViewV2.this.featuredSingleRowItem.setVisibility(0);
            }
        }, 100L);
    }

    private void bindOnViewed(LatestTemplate template) {
        if (CollectionUtils.isEmpty(this.onViewed)) {
            this.onViewed = template.getOnViewed();
            if (UiMetrics.isOnScreen(this.latestTemplateView)) {
                handleOnViewed();
            }
        }
    }

    private void showMessageView() {
        final Resources resources = getResources();
        this.messageView.bind(resources.getString(R$string.podcast_latest_template_message_primary_text), resources.getString(R$string.podcast_latest_template_message_secondary_text));
        this.messageView.bindButton(resources.getString(R$string.podcast_explore), new View.OnClickListener() { // from class: com.amazon.podcast.views.latestTemplate.LatestTemplateViewV2.4
            @Override // android.view.View.OnClickListener
            public void onClick(final View view) {
                String strMarketplaceId = Podcast.getUserInfoProvider().marketplaceId();
                ArrayList arrayList = new ArrayList();
                boolean zIsBitesEnabled = Podcast.getFeatureGatePreferences().isBitesEnabled();
                boolean zIsEnabled = PodcastFeatureGating.PODCAST_BITES_FROM_LATEST.isEnabled();
                if (zIsBitesEnabled && zIsEnabled) {
                    arrayList.addAll(LatestTemplateViewV2.this.getBitesMethods());
                } else {
                    if (Podcast.getClientState() != null) {
                        Podcast.getClientState().setDeeplinkInfo(null);
                    }
                    arrayList.addAll(BootstrapMethods.onBrowseStarted(LatestTemplateViewV2.this.getContext(), strMarketplaceId, resources));
                    arrayList.addAll(UiMetricMethods.onClicked(UiMetricAttributes$PageType.PODCASTS_LIBRARY_LATEST, UiMetricAttributes$ActionType.GO_PODCASTS_HOME, UiMetricAttributes$ContentName.PODCASTS_LIBRARY_LATEST));
                }
                LatestTemplateViewV2.this.getMethodsDispatcher().dispatch(LatestTemplateViewV2.this.getOwnerId(), arrayList);
            }
        });
        this.messageView.setVisibility(0);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public List<Method> getBitesMethods() {
        ArrayList arrayList = new ArrayList();
        arrayList.addAll(DeeplinkMethods.bites(getResources(), true));
        arrayList.addAll(UiMetricMethods.onClicked(UiMetricAttributes$PageType.PODCASTS_LIBRARY_LATEST, UiMetricAttributes$ActionType.GO_PODCASTS_BITES, UiMetricAttributes$ContentName.PODCASTS_LIBRARY_LATEST));
        return arrayList;
    }
}
