package com.amazon.podcast.views.savesTemplate;

import Podcast.EpisodeOptionsInterface.v1_0.EpisodeOptionsElement;
import Podcast.PlaybackInterface.v1_0.PauseMediaMethod;
import Podcast.PlaybackInterface.v1_0.ResumeMediaMethod;
import Podcast.RefinementOptionsInterface.v1_0.CheckBoxRefinementOptionElement;
import Podcast.RefinementOptionsInterface.v1_0.CheckBoxRefinementOptionItemElement;
import Podcast.RefinementOptionsInterface.v1_0.ToggleRefinementOptionElement;
import Podcast.SaveInterface.v1_0.RefinementBarElement;
import Podcast.SaveInterface.v1_0.SavesTemplate;
import Podcast.Touch.EpisodeRowItemsInterface.v1_0.BookmarkElement;
import Podcast.Touch.EpisodeRowItemsInterface.v1_0.CompletedElement;
import Podcast.Touch.EpisodeRowItemsInterface.v1_0.DownloadElement;
import Podcast.Touch.EpisodeRowItemsInterface.v1_0.EpisodeRowItemElement;
import SOACoreInterface.v1_0.Method;
import UpsellInterface.v1_0.ContentTrait;
import android.content.Context;
import android.content.res.Resources;
import android.view.View;
import androidx.coordinatorlayout.widget.CoordinatorLayout;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import com.amazon.music.metrics.mts.event.types.pagelatencyinfo.LatencyTrackingLeg;
import com.amazon.podcast.BootstrapMethods;
import com.amazon.podcast.Handlers;
import com.amazon.podcast.Podcast;
import com.amazon.podcast.Queues;
import com.amazon.podcast.R$drawable;
import com.amazon.podcast.R$id;
import com.amazon.podcast.R$layout;
import com.amazon.podcast.R$string;
import com.amazon.podcast.deeplinks.DeeplinkMethods;
import com.amazon.podcast.deeplinks.Deeplinks;
import com.amazon.podcast.featuregating.PodcastFeatureGating;
import com.amazon.podcast.metrics.UiMetricAttributes$ActionType;
import com.amazon.podcast.metrics.UiMetricAttributes$ContentName;
import com.amazon.podcast.metrics.UiMetricAttributes$EntityIdType;
import com.amazon.podcast.metrics.UiMetricAttributes$EntityType;
import com.amazon.podcast.metrics.UiMetricAttributes$PageType;
import com.amazon.podcast.metrics.UiMetricMethods;
import com.amazon.podcast.metrics.latency.LatencyMetricAttributes$PageType;
import com.amazon.podcast.metrics.latency.LatencyMetricInterface;
import com.amazon.podcast.redhoodPlus.MetricsEntityIds;
import com.amazon.podcast.redhoodPlus.Playable;
import com.amazon.podcast.redhoodPlus.PlaybackModes;
import com.amazon.podcast.redhoodPlus.RedhoodPlusClientInterfaces;
import com.amazon.podcast.save.Save;
import com.amazon.podcast.skills.EpisodeOptionsElements;
import com.amazon.podcast.skills.ShowSavedEpisodeSkill;
import com.amazon.podcast.views.ActionBarView;
import com.amazon.podcast.views.BaseView;
import com.amazon.podcast.views.LoadingView;
import com.amazon.podcast.views.MessageView;
import com.amazon.podcast.views.TemplateContext;
import com.amazon.podcast.views.UiMetrics;
import com.amazon.podcast.views.episodeRowItemsListView.EpisodeRowItemsListView;
import com.amazon.podcast.views.refinements.CheckBoxRefinementEventsListener;
import com.amazon.podcast.views.refinements.CheckBoxRefinementOptions;
import com.amazon.podcast.views.refinements.RefinementBarItemView;
import com.google.android.gms.common.util.CollectionUtils;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/* JADX INFO: loaded from: classes5.dex */
public final class SavesTemplateViewV2 extends BaseView<SavesTemplate> implements CheckBoxRefinementEventsListener {
    private static final String TAG;
    private static final Logger logger;
    private ActionBarView actionBarView;
    private UiMetricAttributes$ContentName contentName;
    private Context context;
    private EpisodeRowItemsListView episodeRowItemsListView;
    private boolean isLatencyPublished;
    private boolean isRedhoodPlusMetricsEnabled;
    private LifecycleOwner lifecycleOwner;
    private LoadingView loadingView;
    private MessageView messageView;
    private List<Method> onViewed;
    private UiMetricAttributes$PageType pageType;
    private RefinementBarElement refinementBarElement;
    private RefinementBarItemView refinementSort;
    private RefinementBarItemView refinementUnplayed;
    private LiveData<List<Save>> saveLiveData;
    private SaveRefinementPreferences saveRefinementPreferences;
    private View savesTemplateView;
    private CoordinatorLayout savesTemplateViewCoordinatorLayout;
    private final TemplateContext templateContext;

    @Override // com.amazon.podcast.views.ViewBinder
    public void handleTemplateMethod(final String ownerId, final Method method) {
    }

    @Override // com.amazon.podcast.views.ViewBinder
    public void onConfigurationChanged() {
    }

    static {
        String simpleName = SavesTemplateViewV2.class.getSimpleName();
        TAG = simpleName;
        logger = LoggerFactory.getLogger(simpleName);
    }

    public SavesTemplateViewV2(final TemplateContext templateContext) {
        super(templateContext);
        this.isLatencyPublished = false;
        this.templateContext = templateContext;
        init();
    }

    private View init() {
        this.context = getContext();
        this.lifecycleOwner = getLifecycleOwner();
        this.savesTemplateView = View.inflate(this.context, R$layout.podcast_saves_template_view_v2, this);
        this.savesTemplateViewCoordinatorLayout = (CoordinatorLayout) findViewById(R$id.saves_template_view_v2);
        this.loadingView = (LoadingView) findViewById(R$id.podcast_saves_template_v2_loading_view);
        this.refinementSort = (RefinementBarItemView) findViewById(R$id.podcast_saves_template_v2_refinement_sort);
        this.refinementUnplayed = (RefinementBarItemView) findViewById(R$id.podcast_saves_template_v2_refinement_unplayed);
        this.messageView = (MessageView) findViewById(R$id.podcast_saves_template_v2_message_view);
        this.episodeRowItemsListView = (EpisodeRowItemsListView) findViewById(R$id.podcast_saves_template_v2_episode_row_items_list_view);
        ActionBarView actionBarView = (ActionBarView) findViewById(R$id.action_bar_view);
        this.actionBarView = actionBarView;
        actionBarView.bindBackButton(new View.OnClickListener() { // from class: com.amazon.podcast.views.savesTemplate.SavesTemplateViewV2.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                SavesTemplateViewV2.this.handleBackPressed();
            }
        });
        this.saveRefinementPreferences = SaveRefinementPreferences.INSTANCE;
        addOnViewedListener(this.savesTemplateView);
        this.isRedhoodPlusMetricsEnabled = Podcast.getFeatureGatePreferences().isRedhoodPlusMetricsEnabled();
        return this.savesTemplateView;
    }

    @Override // com.amazon.podcast.views.ViewBinder
    public void bind(final SavesTemplate template, final boolean hideActionBar) {
        UiMetricAttributes$PageType uiMetricAttributes$PageType;
        UiMetricAttributes$ContentName uiMetricAttributes$ContentName;
        if (!this.isLatencyPublished) {
            LatencyMetricInterface.trackLatency(LatencyTrackingLeg.RENDERING, LatencyMetricAttributes$PageType.PODCASTS_LIBRARY_PLAYLIST);
        }
        if (hideActionBar) {
            this.actionBarView.setVisibility(8);
        }
        if (hideActionBar) {
            uiMetricAttributes$PageType = UiMetricAttributes$PageType.PODCASTS_LIBRARY_PLAYLIST;
        } else {
            uiMetricAttributes$PageType = UiMetricAttributes$PageType.PODCASTS_SAVED_EPISODES;
        }
        this.pageType = uiMetricAttributes$PageType;
        if (hideActionBar) {
            uiMetricAttributes$ContentName = UiMetricAttributes$ContentName.PODCASTS_LIBRARY_PLAYLIST;
        } else {
            uiMetricAttributes$ContentName = UiMetricAttributes$ContentName.PODCASTS_SAVED_EPISODES;
        }
        this.contentName = uiMetricAttributes$ContentName;
        this.actionBarView.bindTitle(template.getHeader());
        this.loadingView.setVisibility(0);
        bindOnViewed(template);
        this.refinementBarElement = template.getRefinementBar();
        this.episodeRowItemsListView.create(getOwnerId(), getMethodsDispatcher(), this.lifecycleOwner, this.templateContext, this.pageType);
        if (this.refinementBarElement == null) {
            showUnRefinedSavedEpisodes();
        } else {
            showRefinedSavedEpisodes();
        }
    }

    @Override // com.amazon.podcast.views.refinements.CheckBoxRefinementEventsListener
    public void onOptionItemSelectedForPopUp(final CheckBoxRefinementOptionItemElement item, final CheckBoxRefinementOptionElement element) {
        handleCheckBoxOptionItemSelection(item, element);
    }

    @Override // com.amazon.podcast.views.refinements.CheckBoxRefinementEventsListener
    public void onResetSelected(final CheckBoxRefinementOptionElement element) {
        this.saveRefinementPreferences.writeSortValue(this.context, element.getDefaultState());
        showRefinedSavedEpisodes();
        this.refinementSort.setSelected(false);
    }

    @Override // com.amazon.podcast.views.refinements.CheckBoxRefinementEventsListener
    public void onApply(final CheckBoxRefinementOptionItemElement item, final CheckBoxRefinementOptionElement element) {
        handleCheckBoxOptionItemSelection(item, element);
    }

    @Override // com.amazon.podcast.views.UiMetrics
    public void handleOnViewed() {
        getMethodsDispatcher().dispatch(getOwnerId(), this.onViewed);
    }

    private void handleCheckBoxOptionItemSelection(final CheckBoxRefinementOptionItemElement item, final CheckBoxRefinementOptionElement element) {
        String id = item.getId();
        String defaultState = element.getDefaultState();
        this.saveRefinementPreferences.writeSortValue(this.context, id);
        if (defaultState.equals(id)) {
            this.refinementSort.setSelected(false);
        } else {
            this.refinementSort.setSelected(true);
        }
        showRefinedSavedEpisodes();
    }

    private void showUnRefinedSavedEpisodes() {
        LiveData<List<Save>> all = Podcast.getAppSync().save().readAll(getContext());
        this.saveLiveData = all;
        all.observe(this.lifecycleOwner, new Observer<List<Save>>() { // from class: com.amazon.podcast.views.savesTemplate.SavesTemplateViewV2.2
            @Override // androidx.lifecycle.Observer
            public void onChanged(final List<Save> saves) {
                if (saves == null || saves.isEmpty()) {
                    SavesTemplateViewV2.this.bindEmptyState();
                    SavesTemplateViewV2.this.publishLatencyMetricIfUnpublished();
                } else {
                    SavesTemplateViewV2.this.messageView.setVisibility(4);
                    SavesTemplateViewV2.this.episodeRowItemsListView.setVisibility(0);
                    Handlers.INSTANCE.runInBackground(new Runnable() { // from class: com.amazon.podcast.views.savesTemplate.SavesTemplateViewV2.2.1
                        @Override // java.lang.Runnable
                        public void run() {
                            SavesTemplateViewV2.this.bindEpisodeRowItemsListView(SavesTemplateViewV2.this.episodeRowItemElements(saves));
                        }
                    });
                }
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void showRefinedSavedEpisodes() {
        if (this.loadingView.getVisibility() != 0) {
            this.loadingView.setVisibility(0);
        }
        LiveData<List<Save>> liveData = this.saveLiveData;
        if (liveData != null) {
            liveData.removeObservers(this.lifecycleOwner);
            this.saveLiveData = null;
        }
        LiveData<List<Save>> savedEpisodes = new SavesRefinement(this.context, this.lifecycleOwner, this.refinementBarElement, this.saveRefinementPreferences).getSavedEpisodes();
        this.saveLiveData = savedEpisodes;
        savedEpisodes.observe(this.lifecycleOwner, new Observer<List<Save>>() { // from class: com.amazon.podcast.views.savesTemplate.SavesTemplateViewV2.3
            @Override // androidx.lifecycle.Observer
            public void onChanged(final List<Save> saves) {
                SavesTemplateViewV2.this.bindItems(saves);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void bindItems(final List<Save> items) {
        if (items == null || items.size() == 0) {
            if (isRefinementSelected()) {
                bindRefinementBar(this.refinementBarElement);
                String string = getResources().getString(R$string.podcast_sort_filter_empty_message);
                this.messageView.hideButton();
                this.messageView.hideIcon();
                this.messageView.bind(string, null);
                this.messageView.setVisibility(0);
                this.episodeRowItemsListView.setVisibility(8);
                this.loadingView.setVisibility(8);
                publishLatencyMetricIfUnpublished();
                return;
            }
            bindRefinementBar(null);
            bindEmptyState();
            this.loadingView.setVisibility(8);
            publishLatencyMetricIfUnpublished();
            return;
        }
        bindRefinementBar(this.refinementBarElement);
        this.messageView.setVisibility(8);
        this.episodeRowItemsListView.setVisibility(0);
        Handlers.INSTANCE.runInBackground(new Runnable() { // from class: com.amazon.podcast.views.savesTemplate.SavesTemplateViewV2.4
            @Override // java.lang.Runnable
            public void run() {
                SavesTemplateViewV2.this.bindEpisodeRowItemsListView(SavesTemplateViewV2.this.episodeRowItemElements(items));
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void bindEpisodeRowItemsListView(final List<EpisodeRowItemElement> episodeRowItemElements) {
        Handlers.INSTANCE.getForeground().post(new Runnable() { // from class: com.amazon.podcast.views.savesTemplate.SavesTemplateViewV2.5
            @Override // java.lang.Runnable
            public void run() {
                SavesTemplateViewV2.this.episodeRowItemsListView.bind(episodeRowItemElements, Collections.EMPTY_LIST, SavesTemplateViewV2.this.onViewed);
                SavesTemplateViewV2.this.episodeRowItemsListView.addFooter();
                SavesTemplateViewV2.this.loadingView.setVisibility(8);
                SavesTemplateViewV2.this.publishLatencyMetricIfUnpublished();
            }
        });
    }

    private void bindOnViewed(final SavesTemplate template) {
        if (CollectionUtils.isEmpty(this.onViewed)) {
            this.onViewed = template.getOnViewed();
            if (UiMetrics.isOnScreen(this.savesTemplateView)) {
                handleOnViewed();
            }
        }
    }

    private boolean isRefinementSelected() {
        String defaultState = this.refinementBarElement.getSortingOptions().getDefaultState();
        String sortValue = this.saveRefinementPreferences.readSortValue(this.context);
        return this.saveRefinementPreferences.readUnplayedFilterValue(this.context) || !(StringUtils.isBlank(sortValue) || defaultState.equals(sortValue));
    }

    private void bindRefinementBar(final RefinementBarElement element) {
        if (element == null) {
            this.refinementSort.setVisibility(8);
            this.refinementUnplayed.setVisibility(8);
        } else {
            this.refinementSort.setVisibility(0);
            this.refinementUnplayed.setVisibility(0);
            bindRefinementSort(element.getSortingOptions());
            bindRefinementUnplayed(element.getUnplayedFilter());
        }
    }

    private void bindRefinementSort(final CheckBoxRefinementOptionElement element) {
        if (element == null || element.getOptions().isEmpty()) {
            this.refinementSort.setVisibility(8);
            return;
        }
        this.refinementSort.setText(element.getText());
        this.refinementSort.setLeftIcon(R$drawable.ic_other_sort);
        this.refinementSort.setLeftIconVisibile(true);
        this.refinementSort.setRightIcon(R$drawable.ic_chevron_caretdown);
        this.refinementSort.setRightIconVisibile(true);
        String defaultState = element.getDefaultState();
        String sortValue = this.saveRefinementPreferences.readSortValue(this.context);
        if (StringUtils.isNotBlank(sortValue) && !defaultState.equals(sortValue)) {
            this.refinementSort.setSelected(true);
        }
        this.refinementSort.setOnClickListener(new View.OnClickListener() { // from class: com.amazon.podcast.views.savesTemplate.SavesTemplateViewV2.6
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                SavesTemplateViewV2.this.getMethodsDispatcher().dispatch(SavesTemplateViewV2.this.getOwnerId(), element.getOnItemSelected());
                String sortValue2 = SavesTemplateViewV2.this.saveRefinementPreferences.readSortValue(SavesTemplateViewV2.this.context);
                if (sortValue2 == null) {
                    sortValue2 = SavesTemplateViewV2.this.refinementBarElement.getSortingOptions().getDefaultState();
                }
                Context context = SavesTemplateViewV2.this.context;
                String ownerId = SavesTemplateViewV2.this.getOwnerId();
                RefinementBarItemView refinementBarItemView = SavesTemplateViewV2.this.refinementSort;
                FragmentManager fragmentManager = SavesTemplateViewV2.this.getTemplateContext().getFragmentManager();
                SavesTemplateViewV2 savesTemplateViewV2 = SavesTemplateViewV2.this;
                CheckBoxRefinementOptions.show(context, ownerId, sortValue2, refinementBarItemView, fragmentManager, savesTemplateViewV2, savesTemplateViewV2.getMethodsDispatcher(), SavesTemplateViewV2.this.refinementBarElement.getSortingOptions());
                SavesTemplateViewV2.this.saveRefinementPreferences.writeSortValue(SavesTemplateViewV2.this.context, sortValue2);
            }
        });
    }

    private void bindRefinementUnplayed(final ToggleRefinementOptionElement element) {
        if (element == null) {
            this.refinementUnplayed.setVisibility(8);
            return;
        }
        this.refinementUnplayed.setText(element.getText());
        this.refinementUnplayed.setLeftIconVisibile(false);
        this.refinementUnplayed.setRightIconVisibile(false);
        if (!this.saveRefinementPreferences.readUnplayedFilterValue(this.context)) {
            this.refinementUnplayed.setSelected(false);
        } else {
            this.refinementUnplayed.setSelected(true);
        }
        this.refinementUnplayed.setOnClickListener(new View.OnClickListener() { // from class: com.amazon.podcast.views.savesTemplate.SavesTemplateViewV2.7
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                SavesTemplateViewV2.this.getMethodsDispatcher().dispatch(SavesTemplateViewV2.this.getOwnerId(), element.getOnItemSelected());
                boolean z = !SavesTemplateViewV2.this.refinementUnplayed.isSelected();
                SavesTemplateViewV2.this.refinementUnplayed.setSelected(z);
                SavesTemplateViewV2.this.saveRefinementPreferences.writeUnPlayedFilterValue(SavesTemplateViewV2.this.context, z);
                SavesTemplateViewV2.this.showRefinedSavedEpisodes();
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public List<EpisodeRowItemElement> episodeRowItemElements(final List<Save> saves) {
        ArrayList arrayList = new ArrayList();
        int i = 0;
        for (Save save : saves) {
            boolean zIsPlayable = Playable.isPlayable(save.getContentTraits(), save.getPlaybackMode(), save.getAvailabilityDate());
            String strNormalizeWithAvailabilityDate = PlaybackModes.normalizeWithAvailabilityDate(save.getContentTraits(), save.getAvailabilityDate(), save.getPlaybackMode());
            ArrayList arrayList2 = new ArrayList();
            if (save.getContentTraits() != null) {
                arrayList2.addAll(save.getContentTraits());
            }
            if (RedhoodPlusClientInterfaces.hasAvailabilityDatePassed(save.getAvailabilityDate())) {
                arrayList2.remove(ContentTrait.PRE_RELEASE);
            }
            String strPodcastEpisode = MetricsEntityIds.podcastEpisode(this.isRedhoodPlusMetricsEnabled, save.getId(), save.getPodcastEpisodeVariantId());
            ArrayList arrayList3 = new ArrayList();
            arrayList3.addAll(ShowSavedEpisodeSkill.prepareInvoke(getResources(), getContext(), save));
            arrayList3.addAll(uiMetricClickMethods(strPodcastEpisode, UiMetricAttributes$ActionType.SELECT_PODCAST_EPISODE, Integer.valueOf(i)));
            BookmarkElement bookmarkElementBuild = null;
            EpisodeRowItemElement.Builder builderWithDownload = EpisodeRowItemElement.builder().withMediaId(save.getId()).withOnItemSelected(arrayList3).withPrimaryText(save.getTitle()).withSecondaryText(save.getPodcastTitle()).withDescription(save.getDescription()).withTertiaryText(RedhoodPlusClientInterfaces.getDisplayDate(getResources(), save.getAvailableUpsells(), arrayList2, save.getPublishDate(), save.getAvailabilityDate())).withImage(save.getImage()).withEpisodeOptions(zIsPlayable ? episodeOptionsElementWithHeader(save, getResources(), true) : null).withDownload(zIsPlayable ? downloadElement(save, strPodcastEpisode, i) : null);
            if (zIsPlayable) {
                bookmarkElementBuild = BookmarkElement.builder().withId(save.getId()).withTotalDurationMilliseconds(Long.valueOf(save.getTotalDurationMilliseconds())).build();
            }
            EpisodeRowItemElement.Builder builderWithPlaybackMode = builderWithDownload.withBookmark(bookmarkElementBuild).withOnPlaySelected(onPlayMethods(save, strPodcastEpisode, i)).withOnPauseSelected(onPauseMethods(strPodcastEpisode, i)).withOnResumeSelected(onResumeMethods(strPodcastEpisode, i)).withCompleted(CompletedElement.builder().withId(save.getId()).build()).withPlaybackMode(strNormalizeWithAvailabilityDate);
            List<Method> listUpsellMethods = RedhoodPlusClientInterfaces.upsellMethods(this.context, Deeplinks.episode(save.getPodcastId(), save.getId(), save.getPodcastTitle(), save.getTitle()), save.getAvailableUpsells(), this.pageType, this.contentName, strPodcastEpisode, UiMetricAttributes$EntityType.PODCAST_EPISODE, UiMetricAttributes$EntityIdType.PODCAST_EPISODE_ID);
            builderWithPlaybackMode.withButton(RedhoodPlusClientInterfaces.episodeRowItemUpsellButton(this.context, arrayList2, listUpsellMethods));
            builderWithPlaybackMode.withImageBadge(RedhoodPlusClientInterfaces.episodeRowItemImageBadge(this.context, arrayList2, listUpsellMethods));
            builderWithPlaybackMode.withDescriptionBadge(RedhoodPlusClientInterfaces.episodeRowItemDescriptionBadge(this.context, arrayList2, listUpsellMethods));
            arrayList.add(builderWithPlaybackMode.build());
            i++;
        }
        return arrayList;
    }

    private final List<Method> onPlayMethods(final Save save, final String entityId, final int index) {
        ArrayList arrayList = new ArrayList();
        arrayList.addAll(DeeplinkMethods.savedEpisodePlay(save.getId(), getResources()));
        arrayList.addAll(uiMetricClickMethods(entityId, UiMetricAttributes$ActionType.PLAY_PODCAST_EPISODE, Integer.valueOf(index)));
        return arrayList;
    }

    private final List<Method> onPauseMethods(final String entityId, final int index) {
        ArrayList arrayList = new ArrayList();
        arrayList.addAll(Collections.singletonList(PauseMediaMethod.builder().withQueue(Queues.playback()).withForced(Boolean.FALSE).build()));
        arrayList.addAll(uiMetricClickMethods(entityId, UiMetricAttributes$ActionType.PLAY_PAUSE_PODCAST_EPISODE, Integer.valueOf(index)));
        return arrayList;
    }

    private final List<Method> onResumeMethods(final String entityId, final int index) {
        ArrayList arrayList = new ArrayList();
        arrayList.addAll(Collections.singletonList(ResumeMediaMethod.builder().withQueue(Queues.playback()).withForced(Boolean.FALSE).build()));
        arrayList.addAll(uiMetricClickMethods(entityId, UiMetricAttributes$ActionType.PLAY_PODCAST_EPISODE, Integer.valueOf(index)));
        return arrayList;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void bindEmptyState() {
        View.OnClickListener onClickListener = new View.OnClickListener() { // from class: com.amazon.podcast.views.savesTemplate.SavesTemplateViewV2.8
            @Override // android.view.View.OnClickListener
            public void onClick(final View view) {
                String strMarketplaceId = Podcast.getUserInfoProvider().marketplaceId();
                ArrayList arrayList = new ArrayList();
                boolean zIsBitesEnabled = Podcast.getFeatureGatePreferences().isBitesEnabled();
                boolean zIsEnabled = PodcastFeatureGating.PODCAST_BITES_FROM_PLAYLIST.isEnabled();
                if (zIsBitesEnabled && zIsEnabled) {
                    arrayList.addAll(SavesTemplateViewV2.this.getBitesMethods());
                } else {
                    if (Podcast.getClientState() != null) {
                        Podcast.getClientState().setDeeplinkInfo(null);
                    }
                    arrayList.addAll(BootstrapMethods.onBrowseStarted(SavesTemplateViewV2.this.getContext(), strMarketplaceId, SavesTemplateViewV2.this.getResources()));
                    arrayList.addAll(UiMetricMethods.onClicked(SavesTemplateViewV2.this.pageType, UiMetricAttributes$ActionType.GO_PODCASTS_HOME, SavesTemplateViewV2.this.contentName));
                }
                SavesTemplateViewV2.this.getMethodsDispatcher().dispatch(SavesTemplateViewV2.this.getOwnerId(), arrayList);
            }
        };
        this.messageView.bind(getResources().getString(R$string.podcast_saves_template_message_primary_text), getResources().getString(R$string.podcast_saves_template_message_secondary_text));
        this.messageView.bindButton(getResources().getString(R$string.podcast_explore), onClickListener);
        this.messageView.setVisibility(0);
        this.episodeRowItemsListView.setVisibility(8);
        this.loadingView.setVisibility(8);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public List<Method> getBitesMethods() {
        ArrayList arrayList = new ArrayList();
        arrayList.addAll(DeeplinkMethods.bites(getResources(), false));
        arrayList.addAll(UiMetricMethods.onClicked(this.pageType, UiMetricAttributes$ActionType.GO_PODCASTS_BITES, this.contentName));
        return arrayList;
    }

    private DownloadElement downloadElement(final Save save, final String entityId, final int index) {
        String id = save.getId();
        ArrayList arrayList = new ArrayList();
        arrayList.addAll(DeeplinkMethods.download(id));
        arrayList.addAll(uiMetricClickMethods(entityId, UiMetricAttributes$ActionType.DOWNLOAD_PODCAST_EPISODE, Integer.valueOf(index)));
        return DownloadElement.builder().withId(id).withOnDownload(arrayList).build();
    }

    private EpisodeOptionsElement episodeOptionsElementWithHeader(final Save saveObject, final Resources resources, final boolean isPlayable) {
        return new EpisodeOptionsElements().episodeOptionsElementWithHeader(saveObject, this.pageType, resources, isPlayable);
    }

    private List<Method> uiMetricClickMethods(final String entityId, final UiMetricAttributes$ActionType actionType, final Integer position) {
        return UiMetricMethods.onClicked(this.pageType, actionType, UiMetricAttributes$EntityType.PODCAST_EPISODE, UiMetricAttributes$EntityIdType.PODCAST_EPISODE_ID, entityId, position, this.contentName);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void publishLatencyMetricIfUnpublished() {
        if (this.isLatencyPublished) {
            return;
        }
        LatencyMetricInterface.trackLatency(LatencyTrackingLeg.LOAD_COMPLETE, LatencyMetricAttributes$PageType.PODCASTS_LIBRARY_PLAYLIST);
        this.isLatencyPublished = true;
    }
}
