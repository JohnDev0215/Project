package com.amazon.podcast.views.downloadsTemplate;

import Podcast.DownloadInterface.v1_0.DownloadsTemplate;
import Podcast.DownloadInterface.v1_0.RefinementBarElement;
import Podcast.EpisodeOptionsInterface.v1_0.EpisodeOptionsElement;
import Podcast.PlaybackInterface.v1_0.PauseMediaMethod;
import Podcast.PlaybackInterface.v1_0.ResumeMediaMethod;
import Podcast.RefinementOptionsInterface.v1_0.CheckBoxRefinementOptionElement;
import Podcast.RefinementOptionsInterface.v1_0.CheckBoxRefinementOptionItemElement;
import Podcast.RefinementOptionsInterface.v1_0.ToggleRefinementOptionElement;
import Podcast.Touch.EpisodeRowItemsInterface.v1_0.BookmarkElement;
import Podcast.Touch.EpisodeRowItemsInterface.v1_0.CompletedElement;
import Podcast.Touch.EpisodeRowItemsInterface.v1_0.DownloadElement;
import Podcast.Touch.EpisodeRowItemsInterface.v1_0.EpisodeRowItemElement;
import SOACoreInterface.v1_0.Method;
import UpsellInterface.v1_0.ContentTrait;
import android.content.Context;
import android.content.res.Resources;
import android.view.View;
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
import com.amazon.podcast.deeplinks.Deeplinks;
import com.amazon.podcast.downloads.Download;
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
import com.amazon.podcast.skills.EpisodeOptionsElements;
import com.amazon.podcast.skills.PlayDownloadedEpisodesSkill;
import com.amazon.podcast.skills.ShowDownloadedEpisodeSkill;
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
public final class DownloadsTemplateViewV2 extends BaseView<DownloadsTemplate> implements CheckBoxRefinementEventsListener {
    private static final String TAG;
    private static final Logger logger;
    private ActionBarView actionBarView;
    private Context context;
    private LiveData<List<Download>> downloadLiveData;
    private View downloadsTemplateView;
    private EpisodeRowItemsListView episodeRowItemsListView;
    private boolean isLatencyPublished;
    private boolean isRedhoodPlusMetricsEnabled;
    private LifecycleOwner lifecycleOwner;
    private LoadingView loadingView;
    private MessageView messageView;
    private List<Method> onViewed;
    private RefinementBarElement refinementBarElement;
    private RefinementBarItemView refinementSort;
    private RefinementBarItemView refinementUnplayed;
    private final TemplateContext templateContext;
    private DownloadRefinementPreferences userPreferences;

    @Override // com.amazon.podcast.views.ViewBinder
    public void handleTemplateMethod(final String ownerId, final Method method) {
    }

    @Override // com.amazon.podcast.views.ViewBinder
    public void onConfigurationChanged() {
    }

    static {
        String simpleName = DownloadsTemplateViewV2.class.getSimpleName();
        TAG = simpleName;
        logger = LoggerFactory.getLogger(simpleName);
    }

    public DownloadsTemplateViewV2(final TemplateContext templateContext) {
        super(templateContext);
        this.isLatencyPublished = false;
        this.templateContext = templateContext;
        init();
    }

    private void init() {
        this.context = getContext();
        this.lifecycleOwner = getLifecycleOwner();
        this.downloadsTemplateView = View.inflate(this.context, R$layout.podcast_downloads_template_view_v2, this);
        this.loadingView = (LoadingView) findViewById(R$id.podcast_downloads_template_v2_loading_view);
        this.refinementSort = (RefinementBarItemView) findViewById(R$id.podcast_downloads_template_v2_refinement_sort);
        this.refinementUnplayed = (RefinementBarItemView) findViewById(R$id.podcast_downloads_template_v2_refinement_unplayed);
        this.messageView = (MessageView) findViewById(R$id.podcast_downloads_template_v2_message_view);
        this.episodeRowItemsListView = (EpisodeRowItemsListView) findViewById(R$id.podcast_downloads_template_v2_episode_row_items_list_view);
        this.actionBarView = (ActionBarView) findViewById(R$id.action_bar_view);
        this.userPreferences = DownloadRefinementPreferences.INSTANCE;
        addOnViewedListener(this.downloadsTemplateView);
        this.isRedhoodPlusMetricsEnabled = Podcast.getFeatureGatePreferences().isRedhoodPlusMetricsEnabled();
    }

    @Override // com.amazon.podcast.views.ViewBinder
    public void bind(final DownloadsTemplate template, final boolean hideActionBar) {
        if (!this.isLatencyPublished) {
            LatencyMetricInterface.trackLatency(LatencyTrackingLeg.RENDERING, LatencyMetricAttributes$PageType.PODCASTS_LIBRARY_DOWNLOADS);
        }
        bindOnViewed(template);
        this.refinementBarElement = template.getRefinementBar();
        showDownloadedEpisodes();
        this.episodeRowItemsListView.create(getOwnerId(), getMethodsDispatcher(), this.lifecycleOwner, this.templateContext, UiMetricAttributes$PageType.PODCASTS_LIBRARY_DOWNLOADS);
    }

    @Override // com.amazon.podcast.views.refinements.CheckBoxRefinementEventsListener
    public void onResetSelected(final CheckBoxRefinementOptionElement element) {
        this.userPreferences.writeSortValue(this.context, element.getDefaultState());
        showDownloadedEpisodes();
        this.refinementSort.setSelected(false);
    }

    @Override // com.amazon.podcast.views.refinements.CheckBoxRefinementEventsListener
    public void onApply(final CheckBoxRefinementOptionItemElement item, final CheckBoxRefinementOptionElement element) {
        handleCheckBoxOptionItemSelection(item, element);
    }

    @Override // com.amazon.podcast.views.refinements.CheckBoxRefinementEventsListener
    public void onOptionItemSelectedForPopUp(final CheckBoxRefinementOptionItemElement item, final CheckBoxRefinementOptionElement element) {
        handleCheckBoxOptionItemSelection(item, element);
    }

    @Override // com.amazon.podcast.views.UiMetrics
    public void handleOnViewed() {
        getMethodsDispatcher().dispatch(getOwnerId(), this.onViewed);
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

    private void bindRefinementUnplayed(final ToggleRefinementOptionElement element) {
        if (element == null) {
            this.refinementUnplayed.setVisibility(8);
            return;
        }
        this.refinementUnplayed.setText(element.getText());
        this.refinementUnplayed.setLeftIconVisibile(false);
        this.refinementUnplayed.setRightIconVisibile(false);
        if (!this.userPreferences.readUnplayedFilterValue(this.context)) {
            this.refinementUnplayed.setSelected(false);
        } else {
            this.refinementUnplayed.setSelected(true);
        }
        this.refinementUnplayed.setOnClickListener(new View.OnClickListener() { // from class: com.amazon.podcast.views.downloadsTemplate.DownloadsTemplateViewV2.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                DownloadsTemplateViewV2.this.getMethodsDispatcher().dispatch(DownloadsTemplateViewV2.this.getOwnerId(), element.getOnItemSelected());
                boolean z = !DownloadsTemplateViewV2.this.refinementUnplayed.isSelected();
                DownloadsTemplateViewV2.this.refinementUnplayed.setSelected(z);
                DownloadsTemplateViewV2.this.userPreferences.writeUnPlayedFilterValue(DownloadsTemplateViewV2.this.context, z);
                DownloadsTemplateViewV2.this.showDownloadedEpisodes();
            }
        });
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
        String sortValue = this.userPreferences.readSortValue(this.context);
        if (StringUtils.isNotBlank(sortValue) && !defaultState.equals(sortValue)) {
            this.refinementSort.setSelected(true);
        }
        this.refinementSort.setOnClickListener(new View.OnClickListener() { // from class: com.amazon.podcast.views.downloadsTemplate.DownloadsTemplateViewV2.2
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                DownloadsTemplateViewV2.this.getMethodsDispatcher().dispatch(DownloadsTemplateViewV2.this.getOwnerId(), element.getOnItemSelected());
                String sortValue2 = DownloadsTemplateViewV2.this.userPreferences.readSortValue(DownloadsTemplateViewV2.this.context);
                if (sortValue2 == null) {
                    sortValue2 = DownloadsTemplateViewV2.this.refinementBarElement.getSortingOptions().getDefaultState();
                }
                Context context = DownloadsTemplateViewV2.this.context;
                String ownerId = DownloadsTemplateViewV2.this.getOwnerId();
                RefinementBarItemView refinementBarItemView = DownloadsTemplateViewV2.this.refinementSort;
                FragmentManager fragmentManager = DownloadsTemplateViewV2.this.getTemplateContext().getFragmentManager();
                DownloadsTemplateViewV2 downloadsTemplateViewV2 = DownloadsTemplateViewV2.this;
                CheckBoxRefinementOptions.show(context, ownerId, sortValue2, refinementBarItemView, fragmentManager, downloadsTemplateViewV2, downloadsTemplateViewV2.getMethodsDispatcher(), DownloadsTemplateViewV2.this.refinementBarElement.getSortingOptions());
                DownloadsTemplateViewV2.this.userPreferences.writeSortValue(DownloadsTemplateViewV2.this.context, sortValue2);
            }
        });
    }

    private void handleCheckBoxOptionItemSelection(final CheckBoxRefinementOptionItemElement item, final CheckBoxRefinementOptionElement element) {
        String id = item.getId();
        String defaultState = element.getDefaultState();
        this.userPreferences.writeSortValue(this.context, id);
        if (defaultState.equals(id)) {
            this.refinementSort.setSelected(false);
        } else {
            this.refinementSort.setSelected(true);
        }
        showDownloadedEpisodes();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void showDownloadedEpisodes() {
        LiveData<List<Download>> liveData = this.downloadLiveData;
        if (liveData != null) {
            liveData.removeObservers(this.lifecycleOwner);
            this.downloadLiveData = null;
        }
        this.loadingView.setVisibility(0);
        LiveData<List<Download>> downloadedEpisodes = new DownloadsRefinement(this.context, this.lifecycleOwner, this.refinementBarElement, this.userPreferences).getDownloadedEpisodes();
        this.downloadLiveData = downloadedEpisodes;
        downloadedEpisodes.observe(this.lifecycleOwner, new Observer<List<Download>>() { // from class: com.amazon.podcast.views.downloadsTemplate.DownloadsTemplateViewV2.3
            @Override // androidx.lifecycle.Observer
            public void onChanged(List<Download> downloads) {
                DownloadsTemplateViewV2.this.bindItems(downloads);
            }
        });
    }

    private void bindOnViewed(DownloadsTemplate template) {
        if (CollectionUtils.isEmpty(this.onViewed)) {
            this.onViewed = template.getOnViewed();
            if (UiMetrics.isOnScreen(this.downloadsTemplateView)) {
                handleOnViewed();
            }
        }
    }

    private boolean isRefinementSelected() {
        String defaultState = this.refinementBarElement.getSortingOptions().getDefaultState();
        String sortValue = this.userPreferences.readSortValue(this.context);
        return this.userPreferences.readUnplayedFilterValue(this.context) || !(StringUtils.isBlank(sortValue) || defaultState.equals(sortValue));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void bindItems(final List<Download> items) {
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
            View.OnClickListener onClickListener = new View.OnClickListener() { // from class: com.amazon.podcast.views.downloadsTemplate.DownloadsTemplateViewV2.4
                @Override // android.view.View.OnClickListener
                public void onClick(final View view) {
                    if (Podcast.getClientState() != null) {
                        Podcast.getClientState().setDeeplinkInfo(null);
                    }
                    String strMarketplaceId = Podcast.getUserInfoProvider().marketplaceId();
                    ArrayList arrayList = new ArrayList();
                    arrayList.addAll(BootstrapMethods.onBrowseStarted(DownloadsTemplateViewV2.this.context, strMarketplaceId, DownloadsTemplateViewV2.this.getResources()));
                    arrayList.addAll(UiMetricMethods.onClicked(UiMetricAttributes$PageType.PODCASTS_LIBRARY_DOWNLOADS, UiMetricAttributes$ActionType.GO_PODCASTS_HOME, UiMetricAttributes$ContentName.PODCASTS_LIBRARY_DOWNLOADS));
                    DownloadsTemplateViewV2.this.getMethodsDispatcher().dispatch(DownloadsTemplateViewV2.this.getOwnerId(), arrayList);
                }
            };
            String string2 = getResources().getString(R$string.podcast_downloads_template_message_primary_text);
            String string3 = getResources().getString(R$string.podcast_downloads_template_message_secondary_text);
            this.messageView.bindIcon(R$drawable.ic_podcast_download);
            this.messageView.bind(string2, string3);
            this.messageView.bindButton(getResources().getString(R$string.podcast_explore), onClickListener);
            this.messageView.setVisibility(0);
            this.episodeRowItemsListView.setVisibility(8);
            this.loadingView.setVisibility(8);
            publishLatencyMetricIfUnpublished();
            return;
        }
        bindRefinementBar(this.refinementBarElement);
        this.messageView.setVisibility(8);
        this.episodeRowItemsListView.setVisibility(0);
        Handlers.INSTANCE.runInBackground(new Runnable() { // from class: com.amazon.podcast.views.downloadsTemplate.DownloadsTemplateViewV2.5
            @Override // java.lang.Runnable
            public void run() {
                DownloadsTemplateViewV2.this.bindEpisodeRowItems(DownloadsTemplateViewV2.this.episodeRowItemElements(items));
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void bindEpisodeRowItems(final List<EpisodeRowItemElement> episodeRowItemElements) {
        Handlers.INSTANCE.getForeground().post(new Runnable() { // from class: com.amazon.podcast.views.downloadsTemplate.DownloadsTemplateViewV2.6
            @Override // java.lang.Runnable
            public void run() {
                DownloadsTemplateViewV2.this.episodeRowItemsListView.bind(episodeRowItemElements, Collections.EMPTY_LIST, DownloadsTemplateViewV2.this.onViewed);
                DownloadsTemplateViewV2.this.episodeRowItemsListView.addFooter();
                DownloadsTemplateViewV2.this.loadingView.setVisibility(8);
                DownloadsTemplateViewV2.this.publishLatencyMetricIfUnpublished();
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public List<EpisodeRowItemElement> episodeRowItemElements(final List<Download> downloads) {
        ArrayList arrayList = new ArrayList();
        int i = 0;
        for (Download download : downloads) {
            boolean zIsPlayable = Playable.isPlayable(download.getContentTraits(), download.getPlaybackMode(), download.getAvailabilityDate());
            String strNormalizeWithAvailabilityDate = PlaybackModes.normalizeWithAvailabilityDate(download.getContentTraits(), download.getAvailabilityDate(), download.getPlaybackMode());
            ArrayList arrayList2 = new ArrayList();
            String strPodcastEpisode = MetricsEntityIds.podcastEpisode(this.isRedhoodPlusMetricsEnabled, download.getId(), download.getPodcastEpisodeVariantId());
            arrayList2.addAll(ShowDownloadedEpisodeSkill.prepareInvoke(getResources(), getContext(), download));
            arrayList2.addAll(uiMetricClickMethods(strPodcastEpisode, UiMetricAttributes$ActionType.SELECT_PODCAST_EPISODE, i));
            ArrayList arrayList3 = new ArrayList();
            if (download.getContentTraits() != null) {
                arrayList3.addAll(download.getContentTraits());
            }
            if (RedhoodPlusClientInterfaces.hasAvailabilityDatePassed(download.getAvailabilityDate())) {
                arrayList3.remove(ContentTrait.PRE_RELEASE);
            }
            DownloadElement downloadElement = null;
            EpisodeRowItemElement.Builder builderWithBookmark = EpisodeRowItemElement.builder().withMediaId(download.getId()).withOnItemSelected(arrayList2).withPrimaryText(download.getTitle()).withSecondaryText(download.getPodcastTitle()).withDescription(download.getDescription()).withTertiaryText(RedhoodPlusClientInterfaces.getDisplayDate(getResources(), download.getAvailableUpsells(), arrayList3, download.getPublishDate(), download.getAvailabilityDate())).withImage(download.getImage()).withEpisodeOptions(zIsPlayable ? episodeOptionsElementWithHeader(download, UiMetricAttributes$PageType.PODCASTS_LIBRARY_DOWNLOADS, getResources()) : null).withBookmark(BookmarkElement.builder().withId(download.getId()).withTotalDurationMilliseconds(Long.valueOf(download.getDurationMilliseconds())).build());
            if (zIsPlayable) {
                downloadElement = downloadElement(download);
            }
            EpisodeRowItemElement.Builder builderWithPlaybackMode = builderWithBookmark.withDownload(downloadElement).withOnPlaySelected(onPlayMethods(download, strPodcastEpisode, i)).withOnPauseSelected(onPauseMethods(strPodcastEpisode, i)).withOnResumeSelected(onResumeMethods(strPodcastEpisode, i)).withCompleted(CompletedElement.builder().withId(download.getId()).build()).withPlaybackMode(strNormalizeWithAvailabilityDate);
            List<Method> listUpsellMethodsForDownloadsWithOverrideIfStaleUpsells = RedhoodPlusClientInterfaces.upsellMethodsForDownloadsWithOverrideIfStaleUpsells(this.context, Deeplinks.episode(download.getPodcastId(), download.getId(), download.getPodcastTitle(), download.getTitle()), download.getAvailableUpsells(), Podcast.getUserInfoProvider().benefits(), UiMetricAttributes$PageType.PODCASTS_JUMP_BACK_IN_EPISODES, UiMetricAttributes$ContentName.PODCASTS_JUMP_BACK_IN_EPISODES, strPodcastEpisode, UiMetricAttributes$EntityType.PODCAST_EPISODE, UiMetricAttributes$EntityIdType.PODCAST_EPISODE_ID);
            builderWithPlaybackMode.withButton(RedhoodPlusClientInterfaces.episodeRowItemUpsellButton(this.context, arrayList3, listUpsellMethodsForDownloadsWithOverrideIfStaleUpsells));
            builderWithPlaybackMode.withImageBadge(RedhoodPlusClientInterfaces.episodeRowItemImageBadge(this.context, arrayList3, listUpsellMethodsForDownloadsWithOverrideIfStaleUpsells));
            builderWithPlaybackMode.withDescriptionBadge(RedhoodPlusClientInterfaces.episodeRowItemDescriptionBadge(this.context, arrayList3, listUpsellMethodsForDownloadsWithOverrideIfStaleUpsells));
            arrayList.add(builderWithPlaybackMode.build());
            i++;
        }
        return arrayList;
    }

    private final List<Method> onPlayMethods(final Download download, final String entityId, final int index) {
        ArrayList arrayList = new ArrayList();
        arrayList.addAll(PlayDownloadedEpisodesSkill.prepareInvoke(download.getId()));
        arrayList.addAll(uiMetricClickMethods(entityId, UiMetricAttributes$ActionType.PLAY_PODCAST_EPISODE, index));
        return arrayList;
    }

    private final List<Method> onPauseMethods(final String entityId, final int index) {
        ArrayList arrayList = new ArrayList();
        arrayList.addAll(Collections.singletonList(PauseMediaMethod.builder().withQueue(Queues.playback()).withForced(Boolean.FALSE).build()));
        arrayList.addAll(uiMetricClickMethods(entityId, UiMetricAttributes$ActionType.PLAY_PAUSE_PODCAST_EPISODE, index));
        return arrayList;
    }

    private final List<Method> onResumeMethods(final String entityId, final int index) {
        ArrayList arrayList = new ArrayList();
        arrayList.addAll(Collections.singletonList(ResumeMediaMethod.builder().withQueue(Queues.playback()).withForced(Boolean.FALSE).build()));
        arrayList.addAll(uiMetricClickMethods(entityId, UiMetricAttributes$ActionType.PLAY_PODCAST_EPISODE, index));
        return arrayList;
    }

    private DownloadElement downloadElement(final Download download) {
        return DownloadElement.builder().withId(download.getId()).withOnDownload(Collections.emptyList()).build();
    }

    private EpisodeOptionsElement episodeOptionsElementWithHeader(final Download download, final UiMetricAttributes$PageType pageType, final Resources resources) {
        Podcast.getFeatureGatePreferences().isRedhoodPlusUXEnabled();
        return new EpisodeOptionsElements().episodeOptionsElementWithHeader(download, pageType, resources, Playable.isPlayable(download.getPlaybackMode()));
    }

    private List<Method> uiMetricClickMethods(final String entityId, final UiMetricAttributes$ActionType actionType, final int position) {
        return UiMetricMethods.onClicked(UiMetricAttributes$PageType.PODCASTS_LIBRARY_DOWNLOADS, actionType, UiMetricAttributes$EntityType.PODCAST_EPISODE, UiMetricAttributes$EntityIdType.PODCAST_EPISODE_ID, entityId, Integer.valueOf(position), UiMetricAttributes$ContentName.PODCASTS_LIBRARY_DOWNLOADS);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void publishLatencyMetricIfUnpublished() {
        if (this.isLatencyPublished) {
            return;
        }
        LatencyMetricInterface.trackLatency(LatencyTrackingLeg.LOAD_COMPLETE, LatencyMetricAttributes$PageType.PODCASTS_LIBRARY_DOWNLOADS);
        this.isLatencyPublished = true;
    }
}
