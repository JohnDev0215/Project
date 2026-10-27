package com.amazon.podcast.views.followsTemplate;

import Podcast.FollowInterface.v1_0.FollowsTemplate;
import Podcast.Touch.HorizontalRowTemplateInterface.v1_0.HorizontalItemElement;
import Podcast.Touch.VerticalGridTemplateInterface.v1_0.VerticalItemElement;
import Podcast.Touch.WidgetsInterface.v1_0.DescriptiveShowcaseElement;
import SOACoreInterface.v1_0.Method;
import android.content.res.Resources;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.ImageView;
import android.widget.ScrollView;
import androidx.constraintlayout.widget.Group;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.amazon.music.metrics.mts.event.types.pagelatencyinfo.LatencyTrackingLeg;
import com.amazon.podcast.BootstrapMethods;
import com.amazon.podcast.Handlers;
import com.amazon.podcast.Podcast;
import com.amazon.podcast.R$dimen;
import com.amazon.podcast.R$id;
import com.amazon.podcast.R$integer;
import com.amazon.podcast.R$layout;
import com.amazon.podcast.R$string;
import com.amazon.podcast.bookmark.Bookmark;
import com.amazon.podcast.deeplinks.DeeplinkMethods;
import com.amazon.podcast.featuregating.PodcastFeatureGating;
import com.amazon.podcast.follow.Follow;
import com.amazon.podcast.followsRecommendedShows.FollowsSuggestedShowsCreateElements;
import com.amazon.podcast.followsRecommendedShows.FollowsSuggestedShowsQueries;
import com.amazon.podcast.metrics.UiMetricAttributes$ActionType;
import com.amazon.podcast.metrics.UiMetricAttributes$ContentName;
import com.amazon.podcast.metrics.UiMetricAttributes$EntityIdType;
import com.amazon.podcast.metrics.UiMetricAttributes$EntityType;
import com.amazon.podcast.metrics.UiMetricAttributes$PageType;
import com.amazon.podcast.metrics.UiMetricMethods;
import com.amazon.podcast.metrics.latency.LatencyMetricAttributes$PageType;
import com.amazon.podcast.metrics.latency.LatencyMetricInterface;
import com.amazon.podcast.redhoodPlus.MetricsEntityIds;
import com.amazon.podcast.views.ActionBarView;
import com.amazon.podcast.views.BaseView;
import com.amazon.podcast.views.EmberTextView;
import com.amazon.podcast.views.MessageView;
import com.amazon.podcast.views.PTCWidgetView;
import com.amazon.podcast.views.TemplateContext;
import com.amazon.podcast.views.UiMetrics;
import com.amazon.podcast.views.descriptiveShowcase.DescriptiveShowcaseElementView;
import com.google.android.gms.common.util.CollectionUtils;
import com.squareup.picasso.Picasso;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/* JADX INFO: loaded from: classes5.dex */
public final class FollowsTemplateView extends BaseView<FollowsTemplate> {
    private static final String TAG;
    private static final Logger logger;
    private ActionBarView actionBarView;
    private FollowItemsAdapter adapter;
    private ImageView background;
    private Group backgroundOverlay;
    private EmberTextView button;
    private int columns;
    private UiMetricAttributes$ContentName contentName;
    private DescriptiveShowcaseElementView descriptiveShowcaseWidget;
    private View followsTemplateView;
    private boolean isLatencyPublished;
    private final boolean isRedhoodPlusMetricsEnabled;
    private GridLayoutManager layoutManager;
    private MessageView messageView;
    private List<Method> onViewed;
    private UiMetricAttributes$PageType pageType;
    private PTCWidgetView ptcWidget;
    private RecyclerView recyclerView;
    private ScrollView scrollView;
    private boolean shouldCreateDescriptiveShowcaseOnViewed;
    private int verticalItemDimension;

    @Override // com.amazon.podcast.views.ViewBinder
    public void handleTemplateMethod(final String ownerId, final Method method) {
    }

    static {
        String simpleName = FollowsTemplateView.class.getSimpleName();
        TAG = simpleName;
        logger = LoggerFactory.getLogger(simpleName);
    }

    public FollowsTemplateView(final TemplateContext templateContext) {
        super(templateContext);
        this.isLatencyPublished = false;
        this.shouldCreateDescriptiveShowcaseOnViewed = true;
        this.isRedhoodPlusMetricsEnabled = Podcast.getFeatureGatePreferences().isRedhoodPlusMetricsEnabled();
        init();
    }

    private View init() {
        this.followsTemplateView = View.inflate(getContext(), R$layout.podcast_vertical_grid_template_view, this);
        this.recyclerView = (RecyclerView) findViewById(R$id.grid_template_recycler_view);
        this.messageView = (MessageView) findViewById(R$id.podcast_vertical_grid_message_view);
        this.ptcWidget = (PTCWidgetView) findViewById(R$id.podcast_vertical_grid_ptc);
        this.descriptiveShowcaseWidget = (DescriptiveShowcaseElementView) findViewById(R$id.podcast_descriptive_showcase_widget);
        this.scrollView = (ScrollView) findViewById(R$id.podcast_vertical_grid_ptc_scroll);
        this.background = (ImageView) findViewById(R$id.vertical_grid_template_view_background);
        this.backgroundOverlay = (Group) findViewById(R$id.background_overlay);
        EmberTextView emberTextView = (EmberTextView) findViewById(R$id.grid_template_button);
        this.button = emberTextView;
        emberTextView.setVisibility(8);
        this.verticalItemDimension = getVerticalItemDimension();
        this.columns = getResources().getInteger(R$integer.podcast_grid_template_columns);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(), this.columns);
        this.layoutManager = gridLayoutManager;
        gridLayoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() { // from class: com.amazon.podcast.views.followsTemplate.FollowsTemplateView.1
            @Override // androidx.recyclerview.widget.GridLayoutManager.SpanSizeLookup
            public int getSpanSize(final int position) {
                if (FollowsTemplateView.this.adapter.isFooterOrDescriptiveShowcase(position)) {
                    return FollowsTemplateView.this.layoutManager.getSpanCount();
                }
                return 1;
            }
        });
        this.recyclerView.setLayoutManager(this.layoutManager);
        this.recyclerView.setAdapter(new FollowItemsAdapter(getOwnerId(), getResources(), getMethodsDispatcher(), this.verticalItemDimension));
        ActionBarView actionBarView = (ActionBarView) findViewById(R$id.action_bar_view);
        this.actionBarView = actionBarView;
        actionBarView.bindBackButton(new View.OnClickListener() { // from class: com.amazon.podcast.views.followsTemplate.FollowsTemplateView.2
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                FollowsTemplateView.this.handleBackPressed();
            }
        });
        addOnViewedListener(this.followsTemplateView);
        return this.followsTemplateView;
    }

    @Override // com.amazon.podcast.views.ViewBinder
    public void bind(final FollowsTemplate template, final boolean hideActionBar) {
        UiMetricAttributes$PageType uiMetricAttributes$PageType;
        UiMetricAttributes$ContentName uiMetricAttributes$ContentName;
        if (!this.isLatencyPublished) {
            LatencyMetricInterface.trackLatency(LatencyTrackingLeg.RENDERING, LatencyMetricAttributes$PageType.PODCASTS_LIBRARY_SHOWS);
        }
        if (hideActionBar) {
            this.actionBarView.setVisibility(8);
        }
        if (hideActionBar) {
            uiMetricAttributes$PageType = UiMetricAttributes$PageType.PODCASTS_LIBRARY_SHOWS;
        } else {
            uiMetricAttributes$PageType = UiMetricAttributes$PageType.PODCASTS_FOLLOWED_SHOWS;
        }
        this.pageType = uiMetricAttributes$PageType;
        if (hideActionBar) {
            uiMetricAttributes$ContentName = UiMetricAttributes$ContentName.PODCASTS_LIBRARY_SHOWS;
        } else {
            uiMetricAttributes$ContentName = UiMetricAttributes$ContentName.PODCASTS_FOLLOWED_SHOWS;
        }
        this.contentName = uiMetricAttributes$ContentName;
        this.actionBarView.bindTitle(template.getHeader());
        if (template.getBackgroundImage() != null) {
            DisplayMetrics displayMetrics = getResources().getDisplayMetrics();
            Picasso.get().load(template.getBackgroundImage()).resize(displayMetrics.widthPixels, displayMetrics.heightPixels).into(this.background);
            this.backgroundOverlay.setVisibility(0);
        }
        this.adapter = (FollowItemsAdapter) this.recyclerView.getAdapter();
        bindOnViewed(template);
        if (PodcastFeatureGating.FOLLOW_ENHANCEMENTS_LIBRARY_ARCUS.isEnabled()) {
            bindWithDescriptiveShowcase(template);
        } else {
            bindFollows(template);
        }
    }

    private void bindFollows(final FollowsTemplate template) {
        Podcast.getAppSync().follow().readAll(getContext()).observe(getLifecycleOwner(), new Observer<List<Follow>>() { // from class: com.amazon.podcast.views.followsTemplate.FollowsTemplateView.3
            @Override // androidx.lifecycle.Observer
            public void onChanged(final List<Follow> follows) {
                if (follows == null || follows.isEmpty()) {
                    FollowsTemplateView.this.bindEmptyState();
                    FollowsTemplateView.this.publishLatencyMetricIfUnpublished();
                } else {
                    FollowsTemplateView.this.hideEmptyStateWidgets();
                    Handlers.INSTANCE.runInBackground(new Runnable() { // from class: com.amazon.podcast.views.followsTemplate.FollowsTemplateView.3.1
                        @Override // java.lang.Runnable
                        public void run() {
                            List listVerticalItems = FollowsTemplateView.this.verticalItems(follows);
                            AnonymousClass3 anonymousClass3 = AnonymousClass3.this;
                            FollowsTemplateView.this.bindVerticalItems(listVerticalItems, null, template);
                        }
                    });
                }
            }
        });
    }

    private void bindWithDescriptiveShowcase(final FollowsTemplate template) {
        FollowsSuggestedShowsQueries followsSuggestedShowsQueries = new FollowsSuggestedShowsQueries(getContext());
        bindAllElements(followsSuggestedShowsQueries.getAllFollowsForRecommendedShows(), followsSuggestedShowsQueries.getBookmarksForFollowsRecommendedShows(), template);
    }

    private void bindAllElements(final LiveData<List<Follow>> liveFollowData, final LiveData<List<Bookmark>> liveBookmarkData, final FollowsTemplate template) {
        if (liveFollowData == null || liveBookmarkData == null) {
            bindFollows(template);
        } else {
            liveFollowData.observe(getLifecycleOwner(), new Observer<List<Follow>>() { // from class: com.amazon.podcast.views.followsTemplate.FollowsTemplateView.4
                @Override // androidx.lifecycle.Observer
                public void onChanged(final List<Follow> follows) {
                    liveBookmarkData.observe(FollowsTemplateView.this.getLifecycleOwner(), new Observer<List<Bookmark>>() { // from class: com.amazon.podcast.views.followsTemplate.FollowsTemplateView.4.1
                        @Override // androidx.lifecycle.Observer
                        public void onChanged(List<Bookmark> bookmarks) {
                            List recommendedShows = FollowsTemplateView.this.getRecommendedShows(bookmarks, follows);
                            List allFollowedShows = FollowsTemplateView.this.getAllFollowedShows(follows);
                            DescriptiveShowcaseElement descriptiveShowcaseElementCreateDescriptiveShowcaseElement = FollowsTemplateView.this.shouldFollowsWidgetBeVisible(recommendedShows, allFollowedShows) ? FollowsSuggestedShowsCreateElements.createDescriptiveShowcaseElement(FollowsTemplateView.this.getContext(), FollowsTemplateView.this.shouldCreateDescriptiveShowcaseOnViewed, recommendedShows) : null;
                            FollowsTemplateView.this.shouldCreateDescriptiveShowcaseOnViewed = descriptiveShowcaseElementCreateDescriptiveShowcaseElement == null;
                            if (allFollowedShows == null || allFollowedShows.isEmpty()) {
                                FollowsTemplateView.this.bindEmptyState();
                                FollowsTemplateView.this.bindDescriptiveShowcaseElement(descriptiveShowcaseElementCreateDescriptiveShowcaseElement);
                                FollowsTemplateView.this.publishLatencyMetricIfUnpublished();
                            } else {
                                FollowsTemplateView.this.hideEmptyStateWidgets();
                                AnonymousClass4 anonymousClass4 = AnonymousClass4.this;
                                FollowsTemplateView.this.bindItemsInBackground(descriptiveShowcaseElementCreateDescriptiveShowcaseElement, allFollowedShows, template);
                            }
                        }
                    });
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean shouldFollowsWidgetBeVisible(final List<HorizontalItemElement> recommendedShows, final List<Follow> followedShows) {
        return recommendedShows.size() > 0 && followedShows.size() < 10 && PodcastFeatureGating.FOLLOW_ENHANCEMENTS_LIBRARY_WEBLAB.isEnabled();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void hideEmptyStateWidgets() {
        this.messageView.setVisibility(4);
        this.ptcWidget.setVisibility(4);
        this.descriptiveShowcaseWidget.setVisibility(8);
        this.scrollView.setVisibility(4);
        this.recyclerView.setVisibility(0);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void bindItemsInBackground(final DescriptiveShowcaseElement element, final List<Follow> followedShows, final FollowsTemplate template) {
        Handlers.INSTANCE.runInBackground(new Runnable() { // from class: com.amazon.podcast.views.followsTemplate.FollowsTemplateView.5
            @Override // java.lang.Runnable
            public void run() {
                FollowsTemplateView.this.bindVerticalItems(FollowsTemplateView.this.verticalItems(followedShows), element, template);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void bindDescriptiveShowcaseElement(final DescriptiveShowcaseElement element) {
        if (element == null) {
            this.descriptiveShowcaseWidget.setVisibility(8);
            return;
        }
        this.descriptiveShowcaseWidget.setOwnerId(getOwnerId());
        this.descriptiveShowcaseWidget.setMethodsDispatcher(getMethodsDispatcher());
        this.descriptiveShowcaseWidget.bind(element);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public List<Follow> getAllFollowedShows(List<Follow> follows) {
        if (follows == null) {
            return Collections.EMPTY_LIST;
        }
        ArrayList arrayList = new ArrayList();
        for (Follow follow : follows) {
            if (follow.isFollowed()) {
                arrayList.add(follow);
            }
        }
        return arrayList;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void bindVerticalItems(final List<VerticalItemElement> verticalRowItems, final DescriptiveShowcaseElement descriptiveShowcaseElement, final FollowsTemplate template) {
        Handlers.INSTANCE.getForeground().post(new Runnable() { // from class: com.amazon.podcast.views.followsTemplate.FollowsTemplateView.6
            @Override // java.lang.Runnable
            public void run() {
                FollowsTemplateView.this.adapter.bind(verticalRowItems, descriptiveShowcaseElement, template.getFooter());
                FollowsTemplateView.this.publishLatencyMetricIfUnpublished();
            }
        });
    }

    @Override // com.amazon.podcast.views.ViewBinder
    public void onConfigurationChanged() {
        this.columns = getResources().getInteger(R$integer.podcast_grid_template_columns);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(getContext(), this.columns);
        this.layoutManager = gridLayoutManager;
        this.recyclerView.setLayoutManager(gridLayoutManager);
    }

    @Override // com.amazon.podcast.views.UiMetrics
    public void handleOnViewed() {
        getMethodsDispatcher().dispatch(getOwnerId(), this.onViewed);
    }

    private void bindOnViewed(FollowsTemplate template) {
        if (CollectionUtils.isEmpty(this.onViewed)) {
            this.onViewed = template.getOnViewed();
            if (UiMetrics.isOnScreen(this.followsTemplateView)) {
                handleOnViewed();
            }
        }
    }

    private int getVerticalItemDimension() {
        Resources resources = getResources();
        int i = resources.getDisplayMetrics().widthPixels;
        int dimensionPixelSize = resources.getDimensionPixelSize(R$dimen.margin) * 2;
        int dimensionPixelSize2 = resources.getDimensionPixelSize(R$dimen.gutter);
        int integer = resources.getInteger(R$integer.podcast_grid_template_columns);
        return (i - (((integer * dimensionPixelSize2) + dimensionPixelSize) + dimensionPixelSize2)) / integer;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final List<VerticalItemElement> verticalItems(final List<Follow> follows) {
        ArrayList arrayList = new ArrayList();
        int i = 0;
        for (Follow follow : follows) {
            ArrayList arrayList2 = new ArrayList();
            arrayList2.addAll(DeeplinkMethods.podcast(follow, getResources()));
            arrayList2.addAll(UiMetricMethods.onClicked(this.pageType, UiMetricAttributes$ActionType.SELECT_PODCAST_SHOW, UiMetricAttributes$EntityType.PODCAST_SHOW, UiMetricAttributes$EntityIdType.PODCAST_SHOW_ID, MetricsEntityIds.podcastShow(this.isRedhoodPlusMetricsEnabled, follow.getId(), follow.getPodcastShowVariantId()), Integer.valueOf(i), this.contentName));
            arrayList.add(VerticalItemElement.builder().withOnItemSelected(arrayList2).withImage(follow.getImage()).withAltText(follow.getTitle()).build());
            i++;
        }
        return arrayList;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void bindEmptyState() {
        if (PodcastFeatureGating.TASTE_COLLECTION.isEnabled()) {
            final ArrayList arrayList = new ArrayList();
            arrayList.addAll(DeeplinkMethods.ptc(getResources()));
            arrayList.addAll(UiMetricMethods.onClicked(this.pageType, UiMetricAttributes$ActionType.GO_PODCASTS_TASTE, this.contentName));
            this.ptcWidget.bind(new View.OnClickListener() { // from class: com.amazon.podcast.views.followsTemplate.FollowsTemplateView.7
                @Override // android.view.View.OnClickListener
                public void onClick(final View view) {
                    FollowsTemplateView.this.getMethodsDispatcher().dispatch(FollowsTemplateView.this.getOwnerId(), arrayList);
                }
            }, true);
            this.ptcWidget.setVisibility(0);
            this.scrollView.setVisibility(0);
            this.messageView.setVisibility(8);
        } else {
            if (Podcast.getClientState() != null) {
                Podcast.getClientState().setDeeplinkInfo(null);
            }
            final List<Method> listOnBrowseStarted = BootstrapMethods.onBrowseStarted(getContext(), Podcast.getUserInfoProvider().marketplaceId(), getResources());
            listOnBrowseStarted.addAll(UiMetricMethods.onClicked(this.pageType, UiMetricAttributes$ActionType.GO_PODCASTS_HOME, this.contentName));
            View.OnClickListener onClickListener = new View.OnClickListener() { // from class: com.amazon.podcast.views.followsTemplate.FollowsTemplateView.8
                @Override // android.view.View.OnClickListener
                public void onClick(final View view) {
                    FollowsTemplateView.this.getMethodsDispatcher().dispatch(FollowsTemplateView.this.getOwnerId(), listOnBrowseStarted);
                }
            };
            this.messageView.bind(getResources().getString(R$string.podcast_follows_template_message_primary_text), getResources().getString(R$string.podcast_follows_template_message_secondary_text));
            this.messageView.bindButton(getResources().getString(R$string.podcast_explore), onClickListener);
            this.ptcWidget.setVisibility(8);
            this.scrollView.setVisibility(8);
            this.messageView.setVisibility(0);
        }
        this.recyclerView.setVisibility(8);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public List<HorizontalItemElement> getRecommendedShows(final List<Bookmark> bookmarks, final List<Follow> follows) {
        ArrayList arrayList = new ArrayList();
        if (bookmarks == null || follows == null) {
            return Collections.EMPTY_LIST;
        }
        HashSet hashSet = new HashSet();
        Iterator<Follow> it = follows.iterator();
        while (it.hasNext()) {
            hashSet.add(it.next().getId());
        }
        for (Bookmark bookmark : bookmarks) {
            if (arrayList.size() >= 3) {
                break;
            }
            if (!hashSet.contains(bookmark.getPodcastId())) {
                arrayList.add(FollowsSuggestedShowsCreateElements.createHorizontalItemElement(arrayList.size(), bookmark, getContext()));
            }
        }
        return arrayList;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void publishLatencyMetricIfUnpublished() {
        if (this.isLatencyPublished) {
            return;
        }
        LatencyMetricInterface.trackLatency(LatencyTrackingLeg.LOAD_COMPLETE, LatencyMetricAttributes$PageType.PODCASTS_LIBRARY_SHOWS);
        this.isLatencyPublished = true;
    }
}
