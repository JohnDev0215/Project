package com.amazon.podcast.views.podcastDetailTemplate;

import Podcast.EpisodeOptionsInterface.v1_0.EpisodeOptionsElement;
import Podcast.PlaybackInterface.v1_0.PauseMediaMethod;
import Podcast.PlaybackInterface.v1_0.ResumeMediaMethod;
import Podcast.RefinementOptionsInterface.v1_0.CheckBoxRefinementOptionElement;
import Podcast.RefinementOptionsInterface.v1_0.CheckBoxRefinementOptionItemElement;
import Podcast.RefinementOptionsInterface.v1_0.ToggleRefinementOptionElement;
import Podcast.Touch.EpisodeRowItemsInterface.v1_0.BadgeElement;
import Podcast.Touch.EpisodeRowItemsInterface.v1_0.BookmarkElement;
import Podcast.Touch.EpisodeRowItemsInterface.v1_0.ButtonElement;
import Podcast.Touch.EpisodeRowItemsInterface.v1_0.CompletedElement;
import Podcast.Touch.EpisodeRowItemsInterface.v1_0.DownloadElement;
import Podcast.Touch.EpisodeRowItemsInterface.v1_0.EpisodeRowItemElement;
import Podcast.Touch.NotificationElementInterface.v1_0.CloseNotificationMethod;
import Podcast.Touch.NotificationElementInterface.v1_0.NotificationElement;
import Podcast.Touch.NotificationElementInterface.v1_0.ShowNotificationMethod;
import Podcast.Touch.PodcastDetailTemplateInterface.v2_0.AddEpisodeRowItemsMethod;
import Podcast.Touch.PodcastDetailTemplateInterface.v2_0.FeaturedItemWriteCacheElement;
import Podcast.Touch.PodcastDetailTemplateInterface.v2_0.FeaturedPinnedElement;
import Podcast.Touch.PodcastDetailTemplateInterface.v2_0.PodcastDetailTemplate;
import Podcast.Touch.PodcastDetailTemplateInterface.v2_0.RefinementBarElement;
import Podcast.Touch.PodcastDetailTemplateInterface.v2_0.RefinementOptionsClientState;
import Podcast.Touch.PodcastDetailTemplateInterface.v2_0.ReplaceEpisodeRowItemsMethod;
import Podcast.Touch.PodcastDetailTemplateInterface.v2_0.ShowDownloadedEpisodesMethod;
import Podcast.Touch.RibbonNotificationElementInterface.v1_0.RibbonNotificationElement;
import SOACoreInterface.v1_0.Method;
import SOATemplateListInterface.v1_0.TemplateElement;
import UpsellInterface.v1_0.ContentTrait;
import android.content.Context;
import android.content.res.Resources;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.ViewTreeObserver;
import android.view.accessibility.AccessibilityEvent;
import android.view.accessibility.AccessibilityManager;
import android.widget.ImageView;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import com.amazon.podcast.Handlers;
import com.amazon.podcast.MethodsDispatcher;
import com.amazon.podcast.Podcast;
import com.amazon.podcast.Queues;
import com.amazon.podcast.R$drawable;
import com.amazon.podcast.R$id;
import com.amazon.podcast.R$layout;
import com.amazon.podcast.caches.Caches;
import com.amazon.podcast.completed.Completed;
import com.amazon.podcast.completed.CompletedQueryParameters;
import com.amazon.podcast.deeplinks.Deeplinks;
import com.amazon.podcast.downloads.Download;
import com.amazon.podcast.metrics.UiMetricAttributes$ActionType;
import com.amazon.podcast.metrics.UiMetricAttributes$ContainerType;
import com.amazon.podcast.metrics.UiMetricAttributes$ContentName;
import com.amazon.podcast.metrics.UiMetricAttributes$EntityIdType;
import com.amazon.podcast.metrics.UiMetricAttributes$EntityType;
import com.amazon.podcast.metrics.UiMetricAttributes$PageType;
import com.amazon.podcast.metrics.UiMetricMethods;
import com.amazon.podcast.redhoodPlus.MetricsEntityIds;
import com.amazon.podcast.redhoodPlus.Playable;
import com.amazon.podcast.redhoodPlus.PlaybackModes;
import com.amazon.podcast.redhoodPlus.RedhoodPlusClientInterfaces;
import com.amazon.podcast.skills.EpisodeOptionsElements;
import com.amazon.podcast.skills.PlayDownloadedEpisodesSkill;
import com.amazon.podcast.skills.ShowDownloadedEpisodeSkill;
import com.amazon.podcast.transformations.BlurTransformation;
import com.amazon.podcast.views.ActionBarView;
import com.amazon.podcast.views.BaseView;
import com.amazon.podcast.views.EmberTextView;
import com.amazon.podcast.views.LoadingView;
import com.amazon.podcast.views.RibbonNotificationView;
import com.amazon.podcast.views.TemplateContext;
import com.amazon.podcast.views.UiMetrics;
import com.amazon.podcast.views.episodeRowItemsListView.EpisodeRowItemView;
import com.amazon.podcast.views.episodeRowItemsListView.EpisodeRowItemsListView;
import com.amazon.podcast.views.refinements.CheckBoxRefinementEventsListener;
import com.amazon.podcast.views.refinements.CheckBoxRefinementOptions;
import com.amazon.podcast.views.refinements.RefinementBarItemView;
import com.google.android.gms.common.util.CollectionUtils;
import com.google.android.material.appbar.AppBarLayout;
import com.squareup.picasso.Picasso;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/* JADX INFO: loaded from: classes5.dex */
public final class PodcastDetailTemplateViewV0 extends BaseView<PodcastDetailTemplate> implements CheckBoxRefinementEventsListener {
    private static final String TAG;
    private static final Logger logger;
    private ActionBarView actionBarView;
    private AppBarLayout appBarLayout;
    private ImageView background;
    private LiveData<List<Completed>> completedLiveData;
    private View detailTemplateView;
    private CoordinatorLayout detailsPageView;
    private DownloadsFilter downloadsFilter;
    private LiveData<List<Download>> downloadsLiveData;
    private EpisodeRowItemsListView episodeRowItemsListView;
    private EpisodeRowItemView featuredSingleRowItem;
    private EmberTextView featuredSingleRowItemLabel;
    private HeaderViewV0 headerView;
    private LiveData<FeaturedItemWriteCacheElement> liveFeaturedPinnedItem;
    private LoadingView loadingView;
    private EmberTextView messageView;
    private List<Method> onViewed;
    private RefinementBarElement refinementBarElement;
    private RefinementBarItemView refinementDownload;
    private RefinementBarItemView refinementSeasons;
    private RefinementBarItemView refinementSort;
    private RefinementBarItemView refinementUnplayed;
    private RibbonNotificationView ribbonNotificationView;
    private final TemplateContext templateContext;
    private AppBarLayout toolbarLayout;
    private final BlurTransformation transformation;

    @Override // com.amazon.podcast.views.ViewBinder
    public void onConfigurationChanged() {
    }

    static {
        String simpleName = PodcastDetailTemplateViewV0.class.getSimpleName();
        TAG = simpleName;
        logger = LoggerFactory.getLogger(simpleName);
    }

    public PodcastDetailTemplateViewV0(final TemplateContext templateContext) {
        super(templateContext);
        this.templateContext = templateContext;
        this.transformation = new BlurTransformation(templateContext.getContext());
        init();
    }

    private void init() {
        this.downloadsFilter = new DownloadsFilter(getContext(), getLifecycleOwner());
        this.detailTemplateView = View.inflate(getContext(), R$layout.podcast_detail_template_view_v0, this);
        setBackgroundColor(Podcast.getRuntimeStyleSheet().getBaseColor());
        this.loadingView = (LoadingView) findViewById(R$id.podcast_detail_template_v2_loading_view);
        EmberTextView emberTextView = (EmberTextView) findViewById(R$id.podcast_detail_template_v2_message_view);
        this.messageView = emberTextView;
        emberTextView.setTypeface(Podcast.getRuntimeStyleSheet().getHeadline4TypeFace());
        this.ribbonNotificationView = (RibbonNotificationView) findViewById(R$id.podcast_detail_template_ribbon_notification_view);
        this.refinementSort = (RefinementBarItemView) findViewById(R$id.podcast_detail_template_v2_refinement_sort);
        this.refinementDownload = (RefinementBarItemView) findViewById(R$id.podcast_detail_template_v2_refinement_download);
        this.refinementUnplayed = (RefinementBarItemView) findViewById(R$id.podcast_detail_template_v2_refinement_unplayed);
        this.refinementSeasons = (RefinementBarItemView) findViewById(R$id.podcast_detail_template_v2_refinement_seasons);
        this.episodeRowItemsListView = (EpisodeRowItemsListView) findViewById(R$id.podcast_detail_template_v2_episode_row_items_list_view);
        this.featuredSingleRowItem = (EpisodeRowItemView) findViewById(R$id.podcast_featured_single_row_episode_item);
        EmberTextView emberTextView2 = (EmberTextView) findViewById(R$id.podcast_feature_single_row_item_label);
        this.featuredSingleRowItemLabel = emberTextView2;
        emberTextView2.setTypeface(Podcast.getRuntimeStyleSheet().getHeadline4TypeFace());
        this.appBarLayout = (AppBarLayout) findViewById(R$id.main_appbar);
        this.background = (ImageView) findViewById(R$id.detail_template_view_v2_background);
        this.detailsPageView = (CoordinatorLayout) findViewById(R$id.details_page_view_v2);
        this.headerView = (HeaderViewV0) findViewById(R$id.detail_template_view_v2_header);
        this.toolbarLayout = (AppBarLayout) findViewById(R$id.app_bar_layout);
        ActionBarView actionBarView = (ActionBarView) findViewById(R$id.action_bar_view);
        this.actionBarView = actionBarView;
        actionBarView.bindBackButton(new View.OnClickListener() { // from class: com.amazon.podcast.views.podcastDetailTemplate.PodcastDetailTemplateViewV0.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                PodcastDetailTemplateViewV0.this.handleBackPressed();
            }
        });
        this.headerView.setOwnerId(getOwnerId());
        this.headerView.setMethodsDispatcher(getMethodsDispatcher());
        this.headerView.setLifeCyclceOwner(getLifecycleOwner());
        addOnViewedListener(this.detailTemplateView);
        EpisodeRowItemsListView episodeRowItemsListView = this.episodeRowItemsListView;
        String ownerId = getOwnerId();
        MethodsDispatcher methodsDispatcher = getMethodsDispatcher();
        LifecycleOwner lifecycleOwner = getLifecycleOwner();
        TemplateContext templateContext = getTemplateContext();
        UiMetricAttributes$PageType uiMetricAttributes$PageType = UiMetricAttributes$PageType.PODCAST_SHOW_DETAIL;
        episodeRowItemsListView.create(ownerId, methodsDispatcher, lifecycleOwner, templateContext, uiMetricAttributes$PageType);
        this.featuredSingleRowItem.bindMetadata(getOwnerId(), getMethodsDispatcher(), getLifecycleOwner(), getTemplateContext(), uiMetricAttributes$PageType);
    }

    @Override // com.amazon.podcast.views.ViewBinder
    public void bind(final PodcastDetailTemplate detailTemplate, final boolean hideActionBar) {
        this.refinementBarElement = detailTemplate.getRefinementBarElement();
        if (hideActionBar) {
            this.actionBarView.setVisibility(8);
        }
        DisplayMetrics displayMetrics = getResources().getDisplayMetrics();
        bindActionBar(detailTemplate);
        Picasso.get().load(detailTemplate.getBackgroundImage()).placeholder(R$drawable.artwork_placeholder).transform(this.transformation).resize(displayMetrics.widthPixels, displayMetrics.heightPixels).centerCrop().into(this.background);
        announceForAccessibility(detailTemplate.getHeader().getPrimaryText());
        this.headerView.bind(detailTemplate.getHeader());
        this.headerView.bindFollow(detailTemplate.getFollow());
        this.headerView.bindShare(detailTemplate.getShare());
        this.headerView.bindAboutButton(detailTemplate.getAbout());
        this.headerView.bindUpsellText(detailTemplate.getText());
        this.headerView.bindUpsellButton(detailTemplate.getButton());
        FeaturedPinnedElement featuredPinnedElement = detailTemplate.getFeaturedPinnedElement();
        if (featuredPinnedElement != null) {
            initiateFeaturedPinnedElementObserver(featuredPinnedElement);
        }
        bindFilterButton(this.refinementBarElement);
        bindRefinementBar(this.refinementBarElement);
        this.episodeRowItemsListView.bind(detailTemplate.getItems(), detailTemplate.getOnEndOfList(), detailTemplate.getOnViewed());
        bindEmptyItemsMessage(this.episodeRowItemsListView.getItems());
        bindOnViewed(detailTemplate);
    }

    @Override // com.amazon.podcast.views.ViewBinder
    public void handleTemplateMethod(final String ownerId, final Method method) {
        if (method instanceof AddEpisodeRowItemsMethod) {
            AddEpisodeRowItemsMethod addEpisodeRowItemsMethod = (AddEpisodeRowItemsMethod) method;
            this.episodeRowItemsListView.addItems(addEpisodeRowItemsMethod.getOnEndOfList(), addEpisodeRowItemsMethod.getOnViewed(), addEpisodeRowItemsMethod.getItems());
            bindEmptyItemsMessage(this.episodeRowItemsListView.getItems());
            this.loadingView.setVisibility(8);
            return;
        }
        if (method instanceof ReplaceEpisodeRowItemsMethod) {
            ReplaceEpisodeRowItemsMethod replaceEpisodeRowItemsMethod = (ReplaceEpisodeRowItemsMethod) method;
            this.episodeRowItemsListView.bind(replaceEpisodeRowItemsMethod.getItems(), replaceEpisodeRowItemsMethod.getOnEndOfList(), replaceEpisodeRowItemsMethod.getOnViewed());
            bindEmptyItemsMessage(this.episodeRowItemsListView.getItems());
            this.loadingView.setVisibility(8);
            RefinementOptionsClientState clientState = PodcastDetailClientState.INSTANCE.readClientState(getContext(), this.refinementBarElement.getClientStateName());
            unObserveCompletedEpisodes();
            if (clientState != null) {
                boolean zBooleanValue = clientState.isUnplayed() == null ? false : clientState.isUnplayed().booleanValue();
                boolean zBooleanValue2 = clientState.isDownload() != null ? clientState.isDownload().booleanValue() : false;
                if (!zBooleanValue || zBooleanValue2) {
                    return;
                }
                observeCompletedEpisodes(this.refinementBarElement.getPodcastId(), this.refinementBarElement.getClientStateName());
                return;
            }
            return;
        }
        if (method instanceof ShowDownloadedEpisodesMethod) {
            Handlers.INSTANCE.getForeground().post(new Runnable() { // from class: com.amazon.podcast.views.podcastDetailTemplate.PodcastDetailTemplateViewV0.2
                @Override // java.lang.Runnable
                public void run() {
                    PodcastDetailTemplateViewV0.this.showDownloadedEpisodes();
                }
            });
            return;
        }
        if (method instanceof ShowNotificationMethod) {
            NotificationElement notification = ((ShowNotificationMethod) method).getNotification();
            if (notification instanceof RibbonNotificationElement) {
                this.ribbonNotificationView.bind((RibbonNotificationElement) notification, getMethodsDispatcher(), getOwnerId());
                this.ribbonNotificationView.setVisibility(0);
                return;
            }
            return;
        }
        if (method instanceof CloseNotificationMethod) {
            this.ribbonNotificationView.setVisibility(8);
        }
    }

    @Override // com.amazon.podcast.views.UiMetrics
    public void handleOnViewed() {
        getMethodsDispatcher().dispatch(getOwnerId(), this.onViewed);
    }

    @Override // com.amazon.podcast.views.refinements.CheckBoxRefinementEventsListener
    public void onOptionItemSelectedForPopUp(final CheckBoxRefinementOptionItemElement item, final CheckBoxRefinementOptionElement element) {
        handleCheckBoxOptionItemSelection(item, element);
    }

    @Override // com.amazon.podcast.views.refinements.CheckBoxRefinementEventsListener
    public void onResetSelected(final CheckBoxRefinementOptionElement element) {
        if (isCheckBoxRefinementOptionSort(element)) {
            PodcastDetailClientState.INSTANCE.writeSortValue(getContext(), element.getDefaultState(), this.refinementBarElement.getClientStateName());
            this.refinementSort.setSelected(false);
        } else if (isCheckBoxRefinementOptionSeasons(element)) {
            PodcastDetailClientState.INSTANCE.writeSeasonNumber(getContext(), element.getDefaultState(), this.refinementBarElement.getClientStateName());
            this.refinementSeasons.setSelected(false);
        }
        applyChanges();
    }

    @Override // com.amazon.podcast.views.refinements.CheckBoxRefinementEventsListener
    public void onApply(final CheckBoxRefinementOptionItemElement item, final CheckBoxRefinementOptionElement element) {
        handleCheckBoxOptionItemSelection(item, element);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void bindEmptyItemsMessage(final List<TemplateElement> items) {
        if (CollectionUtils.isEmpty(items)) {
            this.messageView.setVisibility(0);
            this.episodeRowItemsListView.setVisibility(8);
        } else {
            this.messageView.setVisibility(8);
            this.episodeRowItemsListView.setVisibility(0);
        }
    }

    private void initiateFeaturedPinnedElementObserver(final FeaturedPinnedElement element) {
        LiveData<FeaturedItemWriteCacheElement> liveData = this.liveFeaturedPinnedItem;
        if (liveData != null) {
            liveData.removeObservers(getLifecycleOwner());
        }
        LiveData<FeaturedItemWriteCacheElement> liveData2 = Caches.INSTANCE.podcastDetailTemplateV2FeaturedItemCache().read(element.getId());
        this.liveFeaturedPinnedItem = liveData2;
        liveData2.observe(getLifecycleOwner(), new Observer<FeaturedItemWriteCacheElement>() { // from class: com.amazon.podcast.views.podcastDetailTemplate.PodcastDetailTemplateViewV0.3
            @Override // androidx.lifecycle.Observer
            public void onChanged(final FeaturedItemWriteCacheElement element2) {
                if (element2 == null || element2.getItem() == null) {
                    PodcastDetailTemplateViewV0.this.featuredSingleRowItemLabel.setVisibility(8);
                    PodcastDetailTemplateViewV0.this.featuredSingleRowItem.setVisibility(8);
                } else {
                    PodcastDetailTemplateViewV0.this.bindFeaturedPinnedItem(element2);
                }
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void bindFeaturedPinnedItem(final FeaturedItemWriteCacheElement element) {
        Handlers.INSTANCE.getForeground().postDelayed(new Runnable() { // from class: com.amazon.podcast.views.podcastDetailTemplate.PodcastDetailTemplateViewV0.4
            @Override // java.lang.Runnable
            public void run() {
                String label = element.getItem().getLabel();
                if (!StringUtils.isBlank(label)) {
                    PodcastDetailTemplateViewV0.this.featuredSingleRowItemLabel.setText(label);
                    PodcastDetailTemplateViewV0.this.featuredSingleRowItemLabel.setVisibility(0);
                }
                PodcastDetailTemplateViewV0.this.featuredSingleRowItem.bind(element.getItem());
                PodcastDetailTemplateViewV0.this.featuredSingleRowItem.setVisibility(0);
            }
        }, 500L);
    }

    private void handleCheckBoxOptionItemSelection(final CheckBoxRefinementOptionItemElement optionItem, final CheckBoxRefinementOptionElement optionElement) {
        if (isCheckBoxRefinementOptionSort(optionElement)) {
            PodcastDetailClientState.INSTANCE.writeSortValue(getContext(), optionItem.getId(), this.refinementBarElement.getClientStateName());
            if (!optionElement.getDefaultState().equals(optionItem.getId())) {
                this.refinementSort.setSelected(true);
            } else {
                this.refinementSort.setSelected(false);
            }
        } else if (isCheckBoxRefinementOptionSeasons(optionElement)) {
            PodcastDetailClientState.INSTANCE.writeSeasonNumber(getContext(), optionItem.getId(), this.refinementBarElement.getClientStateName());
            if (!optionElement.getDefaultState().equals(optionItem.getId())) {
                this.refinementSeasons.setSelected(true);
            } else {
                this.refinementSeasons.setSelected(false);
            }
        }
        applyChanges();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void applyChanges() {
        LiveData<List<Download>> liveData = this.downloadsLiveData;
        if (liveData != null) {
            liveData.removeObservers(getLifecycleOwner());
            this.downloadsLiveData = null;
        }
        if (this.refinementDownload.isSelected()) {
            this.loadingView.setVisibility(0);
            getMethodsDispatcher().dispatch(getOwnerId(), this.refinementBarElement.getOnChangeWithDownloadFilter());
        } else {
            this.loadingView.setVisibility(0);
            getMethodsDispatcher().dispatch(getOwnerId(), this.refinementBarElement.getOnChange());
        }
        this.appBarLayout.setExpanded(false);
    }

    private void bindActionBar(final PodcastDetailTemplate detailsTemplate) {
        this.actionBarView.bindMiniHeader(detailsTemplate.getBackgroundImage());
        this.actionBarView.hideFilterButton();
        this.appBarLayout.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() { // from class: com.amazon.podcast.views.podcastDetailTemplate.PodcastDetailTemplateViewV0$$ExternalSyntheticLambda0
            @Override // com.google.android.material.appbar.AppBarLayout.OnOffsetChangedListener, com.google.android.material.appbar.AppBarLayout.BaseOnOffsetChangedListener
            public final void onOffsetChanged(AppBarLayout appBarLayout, int i) {
                this.f$0.lambda$bindActionBar$0(appBarLayout, i);
            }
        });
        ViewTreeObserver viewTreeObserver = this.detailsPageView.getViewTreeObserver();
        if (viewTreeObserver == null) {
            return;
        }
        viewTreeObserver.addOnScrollChangedListener(new ViewTreeObserver.OnScrollChangedListener() { // from class: com.amazon.podcast.views.podcastDetailTemplate.PodcastDetailTemplateViewV0$$ExternalSyntheticLambda1
            @Override // android.view.ViewTreeObserver.OnScrollChangedListener
            public final void onScrollChanged() {
                this.f$0.lambda$bindActionBar$1();
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void lambda$bindActionBar$0(AppBarLayout appBarLayout, int i) {
        lambda$bindActionBar$1();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: actionBarListenerCallback, reason: merged with bridge method [inline-methods] */
    public void lambda$bindActionBar$1() {
        if (this.headerView.isHeaderImageViewOnScreen()) {
            this.actionBarView.showMiniHeader(false);
            this.toolbarLayout.setAlpha(1.0f);
        } else {
            this.actionBarView.showMiniHeader(true);
            this.toolbarLayout.setAlpha(1.0f);
        }
    }

    private void announceForAccessibility(String message) {
        AccessibilityManager accessibilityManager = (AccessibilityManager) getContext().getSystemService("accessibility");
        if (accessibilityManager != null) {
            AccessibilityEvent accessibilityEventObtain = AccessibilityEvent.obtain();
            accessibilityEventObtain.setEventType(16384);
            accessibilityEventObtain.getText().add(message);
            if (accessibilityManager.isEnabled()) {
                accessibilityManager.sendAccessibilityEvent(accessibilityEventObtain);
            }
        }
    }

    private void bindRefinementBar(final RefinementBarElement element) {
        if (element == null) {
            this.refinementSort.setVisibility(8);
            this.refinementDownload.setVisibility(8);
            this.refinementUnplayed.setVisibility(8);
            this.refinementSeasons.setVisibility(8);
            return;
        }
        this.refinementSort.setVisibility(0);
        this.refinementDownload.setVisibility(0);
        this.refinementUnplayed.setVisibility(0);
        this.refinementSeasons.setVisibility(0);
        RefinementOptionsClientState clientState = PodcastDetailClientState.INSTANCE.readClientState(getContext(), this.refinementBarElement.getClientStateName());
        bindRefinementSort(clientState, element.getSortingOptions());
        bindRefinementDownload(clientState, element.getDownloadFilter());
        bindRefinementUnPlayed(clientState, element.getUnplayedFilter());
        bindRefinementSeasons(clientState, element.getSeasonOptions());
    }

    private void bindFilterButton(final RefinementBarElement element) {
        if (element == null) {
            this.actionBarView.hideFilterButton();
        } else {
            this.actionBarView.showFilterButton();
            this.actionBarView.bindFilterButton(new View.OnClickListener() { // from class: com.amazon.podcast.views.podcastDetailTemplate.PodcastDetailTemplateViewV0.5
                @Override // android.view.View.OnClickListener
                public void onClick(View view) {
                    PodcastDetailTemplateViewV0.this.getMethodsDispatcher().dispatch(PodcastDetailTemplateViewV0.this.getOwnerId(), UiMetricMethods.onClicked(UiMetricAttributes$PageType.PODCAST_SHOW_DETAIL, UiMetricAttributes$ActionType.SELECT_PODCAST_FILTER, UiMetricAttributes$EntityType.PODCAST_SHOW, UiMetricAttributes$EntityIdType.PODCAST_SHOW_ID, element.getPodcastId(), (Integer) null, UiMetricAttributes$ContentName.PODCAST_REFINEMENT_BAR));
                    PodcastDetailTemplateViewV0.this.appBarLayout.setExpanded(false);
                }
            });
        }
    }

    private void bindRefinementSort(final RefinementOptionsClientState clientState, final CheckBoxRefinementOptionElement sort) {
        if (sort == null) {
            this.refinementSort.setVisibility(8);
            return;
        }
        if (clientState != null && clientState.getSort() != null && !clientState.getSort().equals(sort.getDefaultState())) {
            this.refinementSort.setSelected(true);
        }
        this.refinementSort.setText(sort.getText());
        this.refinementSort.setLeftIcon(R$drawable.ic_other_sort);
        this.refinementSort.setLeftIconVisibile(true);
        this.refinementSort.setRightIcon(R$drawable.ic_chevron_caretdown);
        this.refinementSort.setRightIconVisibile(true);
        this.refinementSort.setOnClickListener(new View.OnClickListener() { // from class: com.amazon.podcast.views.podcastDetailTemplate.PodcastDetailTemplateViewV0.6
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                String defaultState;
                PodcastDetailTemplateViewV0.this.getMethodsDispatcher().dispatch(PodcastDetailTemplateViewV0.this.getOwnerId(), sort.getOnItemSelected());
                RefinementOptionsClientState clientState2 = PodcastDetailClientState.INSTANCE.readClientState(PodcastDetailTemplateViewV0.this.getContext(), PodcastDetailTemplateViewV0.this.refinementBarElement.getClientStateName());
                if (clientState2 == null || clientState2.getSort() == null) {
                    defaultState = PodcastDetailTemplateViewV0.this.refinementBarElement.getSortingOptions().getDefaultState();
                } else {
                    defaultState = clientState2.getSort();
                }
                Context context = PodcastDetailTemplateViewV0.this.getContext();
                String ownerId = PodcastDetailTemplateViewV0.this.getOwnerId();
                RefinementBarItemView refinementBarItemView = PodcastDetailTemplateViewV0.this.refinementSort;
                FragmentManager fragmentManager = PodcastDetailTemplateViewV0.this.getTemplateContext().getFragmentManager();
                PodcastDetailTemplateViewV0 podcastDetailTemplateViewV0 = PodcastDetailTemplateViewV0.this;
                CheckBoxRefinementOptions.show(context, ownerId, defaultState, refinementBarItemView, fragmentManager, podcastDetailTemplateViewV0, podcastDetailTemplateViewV0.getMethodsDispatcher(), PodcastDetailTemplateViewV0.this.refinementBarElement.getSortingOptions());
            }
        });
    }

    private void bindRefinementDownload(final RefinementOptionsClientState clientState, final ToggleRefinementOptionElement download) {
        if (download == null) {
            this.refinementDownload.setVisibility(8);
            return;
        }
        if (clientState != null && clientState.isDownload() != null && clientState.isDownload().booleanValue()) {
            this.refinementDownload.setSelected(true);
        }
        this.refinementDownload.setText(download.getText());
        this.refinementDownload.setLeftIconVisibile(false);
        this.refinementDownload.setRightIconVisibile(false);
        this.refinementDownload.setOnClickListener(new View.OnClickListener() { // from class: com.amazon.podcast.views.podcastDetailTemplate.PodcastDetailTemplateViewV0.7
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                boolean z = !PodcastDetailTemplateViewV0.this.refinementDownload.isSelected();
                if (z) {
                    PodcastDetailTemplateViewV0.this.unObserveCompletedEpisodes();
                }
                PodcastDetailTemplateViewV0.this.refinementDownload.setSelected(z);
                PodcastDetailClientState.INSTANCE.writeDownloadFilterValue(PodcastDetailTemplateViewV0.this.getContext(), z, PodcastDetailTemplateViewV0.this.refinementBarElement.getClientStateName());
                PodcastDetailTemplateViewV0.this.getMethodsDispatcher().dispatch(PodcastDetailTemplateViewV0.this.getOwnerId(), download.getOnItemSelected());
                PodcastDetailTemplateViewV0.this.applyChanges();
            }
        });
    }

    private void bindRefinementUnPlayed(final RefinementOptionsClientState clientState, final ToggleRefinementOptionElement unplayed) {
        if (unplayed == null) {
            this.refinementUnplayed.setVisibility(8);
            return;
        }
        if (clientState != null && clientState.isUnplayed() != null && clientState.isUnplayed().booleanValue()) {
            this.refinementUnplayed.setSelected(true);
        }
        this.refinementUnplayed.setText(unplayed.getText());
        this.refinementUnplayed.setLeftIconVisibile(false);
        this.refinementUnplayed.setRightIconVisibile(false);
        this.refinementUnplayed.setOnClickListener(new View.OnClickListener() { // from class: com.amazon.podcast.views.podcastDetailTemplate.PodcastDetailTemplateViewV0.8
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                boolean z = !PodcastDetailTemplateViewV0.this.refinementUnplayed.isSelected();
                PodcastDetailTemplateViewV0.this.refinementUnplayed.setSelected(z);
                if (!z) {
                    PodcastDetailTemplateViewV0.this.unObserveCompletedEpisodes();
                }
                PodcastDetailClientState.INSTANCE.writeUnPlayedFilterValue(PodcastDetailTemplateViewV0.this.getContext(), z, PodcastDetailTemplateViewV0.this.refinementBarElement.getClientStateName());
                PodcastDetailTemplateViewV0.this.getMethodsDispatcher().dispatch(PodcastDetailTemplateViewV0.this.getOwnerId(), unplayed.getOnItemSelected());
                PodcastDetailTemplateViewV0.this.applyChanges();
            }
        });
    }

    private void bindRefinementSeasons(final RefinementOptionsClientState clientState, final CheckBoxRefinementOptionElement seasons) {
        if (seasons == null) {
            this.refinementSeasons.setVisibility(8);
            return;
        }
        if (clientState != null && clientState.getSeasonNumber() != null && !clientState.getSeasonNumber().equals(seasons.getDefaultState())) {
            this.refinementSeasons.setSelected(true);
        }
        this.refinementSeasons.setText(seasons.getText());
        this.refinementSeasons.setLeftIconVisibile(false);
        this.refinementSeasons.setRightIcon(R$drawable.ic_chevron_caretdown);
        this.refinementSeasons.setRightIconVisibile(true);
        this.refinementSeasons.setOnClickListener(new View.OnClickListener() { // from class: com.amazon.podcast.views.podcastDetailTemplate.PodcastDetailTemplateViewV0.9
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                String defaultState;
                PodcastDetailTemplateViewV0.this.getMethodsDispatcher().dispatch(PodcastDetailTemplateViewV0.this.getOwnerId(), seasons.getOnItemSelected());
                RefinementOptionsClientState clientState2 = PodcastDetailClientState.INSTANCE.readClientState(PodcastDetailTemplateViewV0.this.getContext(), PodcastDetailTemplateViewV0.this.refinementBarElement.getClientStateName());
                if (clientState2 == null || clientState2.getSeasonNumber() == null) {
                    defaultState = PodcastDetailTemplateViewV0.this.refinementBarElement.getSeasonOptions().getDefaultState();
                } else {
                    defaultState = clientState2.getSeasonNumber();
                }
                Context context = PodcastDetailTemplateViewV0.this.getContext();
                String ownerId = PodcastDetailTemplateViewV0.this.getOwnerId();
                RefinementBarItemView refinementBarItemView = PodcastDetailTemplateViewV0.this.refinementSeasons;
                FragmentManager fragmentManager = PodcastDetailTemplateViewV0.this.getTemplateContext().getFragmentManager();
                PodcastDetailTemplateViewV0 podcastDetailTemplateViewV0 = PodcastDetailTemplateViewV0.this;
                CheckBoxRefinementOptions.show(context, ownerId, defaultState, refinementBarItemView, fragmentManager, podcastDetailTemplateViewV0, podcastDetailTemplateViewV0.getMethodsDispatcher(), PodcastDetailTemplateViewV0.this.refinementBarElement.getSeasonOptions());
            }
        });
    }

    private void bindOnViewed(final PodcastDetailTemplate template) {
        if (CollectionUtils.isEmpty(this.onViewed)) {
            this.onViewed = template.getOnViewed();
            if (UiMetrics.isOnScreen(this.detailTemplateView)) {
                handleOnViewed();
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void showDownloadedEpisodes() {
        RefinementBarElement refinementBarElement = this.refinementBarElement;
        if (refinementBarElement == null) {
            return;
        }
        this.downloadsLiveData = this.downloadsFilter.getDownloadedEpisodesLiveData(this.refinementBarElement.getPodcastId(), this.refinementBarElement.getClientStateName(), refinementBarElement.getSortingOptions().getDefaultState());
        this.onViewed = UiMetricMethods.onContentViewed(UiMetricAttributes$PageType.PODCAST_SHOW_DETAIL, UiMetricAttributes$ContainerType.ITEM_LIST, UiMetricAttributes$ContentName.PODCAST_SHOW_DETAIL_DOWNLOADS);
        handleOnViewed();
        this.downloadsLiveData.observe(getLifecycleOwner(), new Observer<List<Download>>() { // from class: com.amazon.podcast.views.podcastDetailTemplate.PodcastDetailTemplateViewV0.10
            @Override // androidx.lifecycle.Observer
            public void onChanged(List<Download> downloads) {
                boolean zIsRedhoodPlusUXEnabled = Podcast.getFeatureGatePreferences().isRedhoodPlusUXEnabled();
                PodcastDetailTemplateViewV0 podcastDetailTemplateViewV0 = PodcastDetailTemplateViewV0.this;
                PodcastDetailTemplateViewV0.this.episodeRowItemsListView.bind(podcastDetailTemplateViewV0.episodeRowItemElements(podcastDetailTemplateViewV0.getResources(), downloads, zIsRedhoodPlusUXEnabled), Collections.emptyList(), PodcastDetailTemplateViewV0.this.onViewed);
                PodcastDetailTemplateViewV0 podcastDetailTemplateViewV02 = PodcastDetailTemplateViewV0.this;
                podcastDetailTemplateViewV02.bindEmptyItemsMessage(podcastDetailTemplateViewV02.episodeRowItemsListView.getItems());
                PodcastDetailTemplateViewV0.this.loadingView.setVisibility(8);
            }
        });
    }

    private void observeCompletedEpisodes(final String podcastId, final String clientStateName) {
        CompletedQueryParameters completedQueryParameters;
        RefinementOptionsClientState clientState = PodcastDetailClientState.INSTANCE.readClientState(getContext(), clientStateName);
        if (clientState == null) {
            completedQueryParameters = new CompletedQueryParameters(podcastId, null);
        } else {
            completedQueryParameters = new CompletedQueryParameters(podcastId, clientState.getSeasonNumber());
        }
        LiveData<List<Completed>> allLiveData = Podcast.getAppSync().completed().readAllLiveData(completedQueryParameters);
        this.completedLiveData = allLiveData;
        allLiveData.observe(getLifecycleOwner(), new Observer<List<Completed>>() { // from class: com.amazon.podcast.views.podcastDetailTemplate.PodcastDetailTemplateViewV0.11
            @Override // androidx.lifecycle.Observer
            public void onChanged(final List<Completed> completeds) {
                PodcastDetailTemplateViewV0.this.removeCompletedEpisodes(completeds);
            }
        });
    }

    public void removeCompletedEpisodes(final List<Completed> completedList) {
        EpisodeRowItemElement episodeRowItemElement;
        CompletedElement completed;
        List<TemplateElement> items = this.episodeRowItemsListView.getItems();
        HashSet hashSet = new HashSet();
        Iterator<Completed> it = completedList.iterator();
        while (it.hasNext()) {
            hashSet.add(it.next().getId());
        }
        ArrayList arrayList = new ArrayList();
        for (TemplateElement templateElement : items) {
            if ((templateElement instanceof EpisodeRowItemElement) && (completed = (episodeRowItemElement = (EpisodeRowItemElement) templateElement).getCompleted()) != null && hashSet.contains(completed.getId())) {
                arrayList.add(episodeRowItemElement);
            }
        }
        this.episodeRowItemsListView.removeItems(arrayList);
        bindEmptyItemsMessage(this.episodeRowItemsListView.getItems());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void unObserveCompletedEpisodes() {
        LiveData<List<Completed>> liveData = this.completedLiveData;
        if (liveData == null) {
            return;
        }
        liveData.removeObservers(getLifecycleOwner());
        this.completedLiveData = null;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public List<EpisodeRowItemElement> episodeRowItemElements(final Resources resources, final List<Download> downloads, final boolean isRedhoodPlusUXEnabled) {
        ArrayList arrayList = new ArrayList();
        Iterator<Download> it = downloads.iterator();
        int i = 0;
        while (it.hasNext()) {
            Download next = it.next();
            boolean zIsPlayable = Playable.isPlayable(next.getContentTraits(), next.getPlaybackMode(), next.getAvailabilityDate());
            String strNormalizeWithAvailabilityDate = PlaybackModes.normalizeWithAvailabilityDate(next.getContentTraits(), next.getAvailabilityDate(), next.getPlaybackMode());
            ArrayList arrayList2 = new ArrayList();
            if (next.getContentTraits() != null) {
                arrayList2.addAll(next.getContentTraits());
            }
            if (RedhoodPlusClientInterfaces.hasAvailabilityDatePassed(next.getAvailabilityDate())) {
                arrayList2.remove(ContentTrait.PRE_RELEASE);
            }
            ArrayList arrayList3 = new ArrayList();
            String strPodcastEpisode = MetricsEntityIds.podcastEpisode(Podcast.getFeatureGatePreferences().isRedhoodPlusMetricsEnabled(), next.getId(), next.getPodcastEpisodeVariantId());
            arrayList3.addAll(ShowDownloadedEpisodeSkill.prepareInvoke(resources, getContext(), next));
            UiMetricAttributes$PageType uiMetricAttributes$PageType = UiMetricAttributes$PageType.PODCAST_SHOW_DETAIL;
            UiMetricAttributes$ActionType uiMetricAttributes$ActionType = UiMetricAttributes$ActionType.SELECT_PODCAST_EPISODE;
            UiMetricAttributes$EntityType uiMetricAttributes$EntityType = UiMetricAttributes$EntityType.PODCAST_EPISODE;
            UiMetricAttributes$EntityIdType uiMetricAttributes$EntityIdType = UiMetricAttributes$EntityIdType.PODCAST_EPISODE_ID;
            Integer numValueOf = Integer.valueOf(i);
            UiMetricAttributes$ContentName uiMetricAttributes$ContentName = UiMetricAttributes$ContentName.PODCAST_SHOW_DETAIL_DOWNLOADS;
            arrayList3.addAll(UiMetricMethods.onClicked(uiMetricAttributes$PageType, uiMetricAttributes$ActionType, uiMetricAttributes$EntityType, uiMetricAttributes$EntityIdType, strPodcastEpisode, numValueOf, uiMetricAttributes$ContentName));
            CompletedElement completedElementBuild = CompletedElement.builder().withId(next.getId()).build();
            Iterator<Download> it2 = it;
            String displayDate = RedhoodPlusClientInterfaces.getDisplayDate(getResources(), next.getAvailableUpsells(), arrayList2, next.getPublishDate(), next.getAvailabilityDate());
            ArrayList arrayList4 = arrayList;
            List<Method> listUpsellMethodsForDownloadsWithOverrideIfStaleUpsells = RedhoodPlusClientInterfaces.upsellMethodsForDownloadsWithOverrideIfStaleUpsells(getContext(), Deeplinks.episode(next.getPodcastId(), next.getId(), next.getPodcastTitle(), next.getTitle()), next.getAvailableUpsells(), Podcast.getUserInfoProvider().benefits(), uiMetricAttributes$PageType, uiMetricAttributes$ContentName, strPodcastEpisode, uiMetricAttributes$EntityType, uiMetricAttributes$EntityIdType);
            ButtonElement buttonElementEpisodeRowItemUpsellButton = RedhoodPlusClientInterfaces.episodeRowItemUpsellButton(getContext(), arrayList2, listUpsellMethodsForDownloadsWithOverrideIfStaleUpsells);
            BadgeElement badgeElementEpisodeRowItemImageBadge = RedhoodPlusClientInterfaces.episodeRowItemImageBadge(getContext(), arrayList2, listUpsellMethodsForDownloadsWithOverrideIfStaleUpsells);
            BadgeElement badgeElementEpisodeRowItemDescriptionBadge = RedhoodPlusClientInterfaces.episodeRowItemDescriptionBadge(getContext(), arrayList2, listUpsellMethodsForDownloadsWithOverrideIfStaleUpsells);
            EpisodeOptionsElement episodeOptionsElementEpisodeOptionsElementWithHeader = null;
            EpisodeRowItemElement.Builder builderWithOnItemSelected = EpisodeRowItemElement.builder().withMediaId(next.getId()).withBookmark(bookmarkElement(next)).withDownload(zIsPlayable ? downloadElement(next) : null).withImage(next.getImage()).withPrimaryText(next.getTitle()).withDescription(next.getDescription()).withTertiaryText(displayDate).withOnItemSelected(arrayList3);
            if (zIsPlayable) {
                episodeOptionsElementEpisodeOptionsElementWithHeader = episodeOptionsElementWithHeader(next, uiMetricAttributes$PageType, resources);
            }
            arrayList4.add(builderWithOnItemSelected.withEpisodeOptions(episodeOptionsElementEpisodeOptionsElementWithHeader).withOnPlaySelected(onPlayMethods(next, i)).withOnPauseSelected(onPauseMethods(next, i)).withOnResumeSelected(onResumeMethods(next, i)).withCompleted(completedElementBuild).withPlaybackMode(strNormalizeWithAvailabilityDate).withButton(buttonElementEpisodeRowItemUpsellButton).withImageBadge(badgeElementEpisodeRowItemImageBadge).withDescriptionBadge(badgeElementEpisodeRowItemDescriptionBadge).build());
            i++;
            arrayList = arrayList4;
            it = it2;
        }
        return arrayList;
    }

    private List<Method> onPlayMethods(final Download download, final int index) {
        ArrayList arrayList = new ArrayList();
        arrayList.addAll(PlayDownloadedEpisodesSkill.prepareInvoke(download.getId()));
        arrayList.addAll(UiMetricMethods.onClicked(UiMetricAttributes$PageType.PODCAST_SHOW_DETAIL, UiMetricAttributes$ActionType.PLAY_PODCAST_EPISODE, UiMetricAttributes$EntityType.PODCAST_EPISODE, UiMetricAttributes$EntityIdType.PODCAST_EPISODE_ID, MetricsEntityIds.podcastEpisode(Podcast.getFeatureGatePreferences().isRedhoodPlusMetricsEnabled(), download.getId(), download.getPodcastEpisodeVariantId()), Integer.valueOf(index), UiMetricAttributes$ContentName.PODCAST_SHOW_DETAIL_DOWNLOADS));
        return arrayList;
    }

    private final List<Method> onPauseMethods(final Download download, final int index) {
        ArrayList arrayList = new ArrayList();
        arrayList.addAll(Collections.singletonList(PauseMediaMethod.builder().withQueue(Queues.playback()).withForced(Boolean.FALSE).build()));
        arrayList.addAll(UiMetricMethods.onClicked(UiMetricAttributes$PageType.PODCAST_SHOW_DETAIL, UiMetricAttributes$ActionType.PLAY_PAUSE_PODCAST_EPISODE, UiMetricAttributes$EntityType.PODCAST_EPISODE, UiMetricAttributes$EntityIdType.PODCAST_EPISODE_ID, MetricsEntityIds.podcastEpisode(Podcast.getFeatureGatePreferences().isRedhoodPlusMetricsEnabled(), download.getId(), download.getPodcastEpisodeVariantId()), Integer.valueOf(index), UiMetricAttributes$ContentName.PODCAST_SHOW_DETAIL_DOWNLOADS));
        return arrayList;
    }

    private final List<Method> onResumeMethods(final Download download, final int index) {
        ArrayList arrayList = new ArrayList();
        arrayList.addAll(Collections.singletonList(ResumeMediaMethod.builder().withQueue(Queues.playback()).withForced(Boolean.FALSE).build()));
        arrayList.addAll(UiMetricMethods.onClicked(UiMetricAttributes$PageType.PODCAST_SHOW_DETAIL, UiMetricAttributes$ActionType.PLAY_PODCAST_EPISODE, UiMetricAttributes$EntityType.PODCAST_EPISODE, UiMetricAttributes$EntityIdType.PODCAST_EPISODE_ID, MetricsEntityIds.podcastEpisode(Podcast.getFeatureGatePreferences().isRedhoodPlusMetricsEnabled(), download.getId(), download.getPodcastEpisodeVariantId()), Integer.valueOf(index), UiMetricAttributes$ContentName.PODCAST_SHOW_DETAIL_DOWNLOADS));
        return arrayList;
    }

    private BookmarkElement bookmarkElement(final Download download) {
        return BookmarkElement.builder().withId(download.getId()).withTotalDurationMilliseconds(Long.valueOf(download.getDurationMilliseconds())).build();
    }

    private DownloadElement downloadElement(final Download download) {
        return DownloadElement.builder().withId(download.getId()).withOnDownload(Collections.emptyList()).build();
    }

    private EpisodeOptionsElement episodeOptionsElementWithHeader(final Download download, final UiMetricAttributes$PageType pageType, final Resources resources) {
        return new EpisodeOptionsElements().episodeOptionsElementWithHeader(download, pageType, resources, Playable.isPlayable(download.getPlaybackMode()));
    }

    private boolean isCheckBoxRefinementOptionSort(final CheckBoxRefinementOptionElement element) {
        return this.refinementBarElement.getSortingOptions().getId().equals(element.getId());
    }

    private boolean isCheckBoxRefinementOptionSeasons(final CheckBoxRefinementOptionElement element) {
        if (this.refinementBarElement.getSeasonOptions() == null) {
            return false;
        }
        return this.refinementBarElement.getSeasonOptions().getId().equals(element.getId());
    }
}
