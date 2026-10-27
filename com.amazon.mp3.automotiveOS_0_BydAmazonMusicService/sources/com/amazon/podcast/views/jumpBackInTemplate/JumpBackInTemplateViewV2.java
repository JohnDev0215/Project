package com.amazon.podcast.views.jumpBackInTemplate;

import Podcast.JumpBackInInterface.v1_0.JumpBackInCacheItemElement;
import Podcast.JumpBackInInterface.v1_0.JumpBackInTemplate;
import Podcast.JumpBackInInterface.v1_0.JumpBackInWriteCacheElement;
import Podcast.PlaybackInterface.v1_0.PauseMediaMethod;
import Podcast.PlaybackInterface.v1_0.ResumeMediaMethod;
import Podcast.Touch.EpisodeRowItemsInterface.v1_0.BookmarkElement;
import Podcast.Touch.EpisodeRowItemsInterface.v1_0.CompletedElement;
import Podcast.Touch.EpisodeRowItemsInterface.v1_0.DownloadElement;
import Podcast.Touch.EpisodeRowItemsInterface.v1_0.EpisodeRowItemElement;
import SOACoreInterface.v1_0.Method;
import UpsellInterface.v1_0.ContentTrait;
import android.content.Context;
import android.util.DisplayMetrics;
import android.view.View;
import android.widget.ImageView;
import androidx.arch.core.util.Function;
import androidx.constraintlayout.widget.Group;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.Transformations;
import com.amazon.podcast.Podcast;
import com.amazon.podcast.Queues;
import com.amazon.podcast.R$id;
import com.amazon.podcast.R$layout;
import com.amazon.podcast.caches.Caches;
import com.amazon.podcast.deeplinks.DeeplinkMethods;
import com.amazon.podcast.deeplinks.Deeplinks;
import com.amazon.podcast.metrics.UiMetricAttributes$ActionType;
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
import com.amazon.podcast.skills.ShowJumpBackInEpisodeSkill;
import com.amazon.podcast.views.ActionBarView;
import com.amazon.podcast.views.BaseView;
import com.amazon.podcast.views.LoadingView;
import com.amazon.podcast.views.Strings;
import com.amazon.podcast.views.TemplateContext;
import com.amazon.podcast.views.UiMetrics;
import com.amazon.podcast.views.episodeRowItemsListView.EpisodeRowItemsListView;
import com.google.android.gms.common.util.CollectionUtils;
import com.squareup.picasso.Picasso;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/* JADX INFO: loaded from: classes5.dex */
public final class JumpBackInTemplateViewV2 extends BaseView<JumpBackInTemplate> {
    private ActionBarView actionBarView;
    private ImageView background;
    private Group backgroundOverlay;
    private EpisodeRowItemsListView episodeRowItemsListView;
    private final boolean isRedhoodPlusMetricsEnabled;
    private View jumpBackInTemplateView;
    private LiveData<List<JumpBackInCacheItemElement>> liveData;
    private LoadingView loadingView;
    private final Logger logger;
    private List<Method> onViewed;

    @Override // com.amazon.podcast.views.ViewBinder
    public void handleTemplateMethod(final String ownerId, final Method method) {
    }

    @Override // com.amazon.podcast.views.ViewBinder
    public void onConfigurationChanged() {
    }

    public JumpBackInTemplateViewV2(final TemplateContext templateContext) {
        super(templateContext);
        this.logger = LoggerFactory.getLogger("JumpBackInTemplateViewV2");
        this.isRedhoodPlusMetricsEnabled = Podcast.getFeatureGatePreferences().isRedhoodPlusMetricsEnabled();
        init();
    }

    private View init() {
        this.jumpBackInTemplateView = View.inflate(getContext(), R$layout.podcast_bookmarks_template_view_v2, this);
        setBackgroundColor(Podcast.getRuntimeStyleSheet().getBaseColor());
        this.episodeRowItemsListView = (EpisodeRowItemsListView) findViewById(R$id.podcast_bookmarks_template_v2_episode_row_items_list_view);
        this.background = (ImageView) findViewById(R$id.podcast_bookmarks_template_view_v2_background);
        this.backgroundOverlay = (Group) findViewById(R$id.background_overlay);
        LoadingView loadingView = (LoadingView) findViewById(R$id.podcast_bookmarks_template_v2_loading_view);
        this.loadingView = loadingView;
        loadingView.setVisibility(0);
        ActionBarView actionBarView = (ActionBarView) findViewById(R$id.action_bar_view);
        this.actionBarView = actionBarView;
        actionBarView.bindBackButton(new View.OnClickListener() { // from class: com.amazon.podcast.views.jumpBackInTemplate.JumpBackInTemplateViewV2.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                JumpBackInTemplateViewV2.this.handleBackPressed();
            }
        });
        addOnViewedListener(this.jumpBackInTemplateView);
        return this.jumpBackInTemplateView;
    }

    @Override // com.amazon.podcast.views.UiMetrics
    public void handleOnViewed() {
        getMethodsDispatcher().dispatch(getOwnerId(), this.onViewed);
    }

    @Override // com.amazon.podcast.views.ViewBinder
    public void bind(final JumpBackInTemplate template, final boolean hideActionBar) {
        if (hideActionBar) {
            this.actionBarView.setVisibility(8);
        }
        if (template != null) {
            this.loadingView.setVisibility(8);
        }
        this.actionBarView.bindTitle(template.getHeader());
        if (template.getBackgroundImage() != null) {
            DisplayMetrics displayMetrics = getResources().getDisplayMetrics();
            Picasso.get().load(template.getBackgroundImage()).resize(displayMetrics.widthPixels, displayMetrics.heightPixels).into(this.background);
            this.backgroundOverlay.setVisibility(0);
        }
        this.episodeRowItemsListView.create(getOwnerId(), getMethodsDispatcher(), getLifecycleOwner(), getTemplateContext(), UiMetricAttributes$PageType.PODCASTS_JUMP_BACK_IN_EPISODES);
        LiveData<List<JumpBackInCacheItemElement>> liveData = this.liveData;
        if (liveData != null) {
            liveData.removeObservers(getLifecycleOwner());
        }
        this.liveData = Transformations.map(Caches.INSTANCE.jumpBackInCache().read(), new Function() { // from class: com.amazon.podcast.views.jumpBackInTemplate.JumpBackInTemplateViewV2$$ExternalSyntheticLambda0
            @Override // androidx.arch.core.util.Function
            public final Object apply(Object obj) {
                return this.f$0.convertJumpBackInItems((JumpBackInWriteCacheElement) obj);
            }
        });
        bindOnViewed(template);
        this.liveData.observe(getLifecycleOwner(), new Observer<List<JumpBackInCacheItemElement>>() { // from class: com.amazon.podcast.views.jumpBackInTemplate.JumpBackInTemplateViewV2.2
            @Override // androidx.lifecycle.Observer
            public void onChanged(final List<JumpBackInCacheItemElement> jumpBackInCacheItemElements) {
                if (jumpBackInCacheItemElements == null || jumpBackInCacheItemElements.isEmpty()) {
                    return;
                }
                try {
                    JumpBackInTemplateViewV2.this.episodeRowItemsListView.bind(JumpBackInTemplateViewV2.this.convertToEpisodeRowItemElements(jumpBackInCacheItemElements), Collections.EMPTY_LIST, JumpBackInTemplateViewV2.this.onViewed);
                } catch (InterruptedException | ExecutionException e) {
                    JumpBackInTemplateViewV2.this.logger.error("Failed to build episode row items due to:" + e);
                }
            }
        });
    }

    private void bindOnViewed(JumpBackInTemplate template) {
        if (CollectionUtils.isEmpty(this.onViewed)) {
            this.onViewed = template.getOnViewed();
            if (UiMetrics.isOnScreen(this.jumpBackInTemplateView)) {
                handleOnViewed();
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public List<EpisodeRowItemElement> convertToEpisodeRowItemElements(final List<JumpBackInCacheItemElement> jumpBackInCacheItemElements) throws ExecutionException, InterruptedException {
        ArrayList arrayList = new ArrayList();
        int size = jumpBackInCacheItemElements.size();
        final Context context = getContext();
        ExecutorService executorServiceNewCachedThreadPool = Executors.newCachedThreadPool();
        ArrayList arrayList2 = new ArrayList(size);
        for (final int i = 0; i < size; i++) {
            arrayList2.add(executorServiceNewCachedThreadPool.submit(new Callable() { // from class: com.amazon.podcast.views.jumpBackInTemplate.JumpBackInTemplateViewV2.3
                @Override // java.util.concurrent.Callable
                public EpisodeRowItemElement call() {
                    JumpBackInCacheItemElement jumpBackInCacheItemElement = (JumpBackInCacheItemElement) jumpBackInCacheItemElements.get(i);
                    String strPodcastEpisode = MetricsEntityIds.podcastEpisode(JumpBackInTemplateViewV2.this.isRedhoodPlusMetricsEnabled, jumpBackInCacheItemElement.getEpisodeId(), jumpBackInCacheItemElement.getPodcastEpisodeVariantId());
                    ArrayList arrayList3 = new ArrayList();
                    arrayList3.addAll(ShowJumpBackInEpisodeSkill.prepareInvoke(JumpBackInTemplateViewV2.this.getResources(), JumpBackInTemplateViewV2.this.getContext(), jumpBackInCacheItemElement));
                    arrayList3.addAll(JumpBackInTemplateViewV2.this.uiMetricClickMethods(strPodcastEpisode, UiMetricAttributes$ActionType.SELECT_PODCAST_EPISODE, i));
                    EpisodeOptionsElements episodeOptionsElements = new EpisodeOptionsElements();
                    boolean zIsPlayable = Playable.isPlayable(jumpBackInCacheItemElement.getContentTraits(), jumpBackInCacheItemElement.getPlaybackMode(), jumpBackInCacheItemElement.getAvailabilityDate());
                    EpisodeRowItemElement.Builder builderWithCompleted = EpisodeRowItemElement.builder().withMediaId(jumpBackInCacheItemElement.getEpisodeId()).withBookmark(JumpBackInTemplateViewV2.this.bookmarkElement(jumpBackInCacheItemElement)).withDownload(zIsPlayable ? JumpBackInTemplateViewV2.this.downloadElement(jumpBackInCacheItemElement, strPodcastEpisode, i) : null).withImage(jumpBackInCacheItemElement.getPodcastImage()).withPrimaryText(jumpBackInCacheItemElement.getTitle()).withSecondaryText(jumpBackInCacheItemElement.getPodcastTitle()).withTertiaryText(Strings.getReadablePublishDate(jumpBackInCacheItemElement.getPublishDate(), JumpBackInTemplateViewV2.this.getResources())).withDescription(jumpBackInCacheItemElement.getDescription()).withOnItemSelected(arrayList3).withEpisodeOptions(zIsPlayable ? episodeOptionsElements.episodeOptionsElementWithHeader(jumpBackInCacheItemElement, UiMetricAttributes$PageType.PODCASTS_JUMP_BACK_IN_EPISODES, JumpBackInTemplateViewV2.this.getResources(), zIsPlayable) : null).withOnPlaySelected(JumpBackInTemplateViewV2.this.onPlayMethods(jumpBackInCacheItemElement, strPodcastEpisode, i)).withOnPauseSelected(JumpBackInTemplateViewV2.this.onPauseMethods(strPodcastEpisode, i)).withOnResumeSelected(JumpBackInTemplateViewV2.this.onResumeMethods(strPodcastEpisode, i)).withCompleted(CompletedElement.builder().withId(jumpBackInCacheItemElement.getEpisodeId()).build());
                    List<Method> listUpsellMethods = RedhoodPlusClientInterfaces.upsellMethods(context, Deeplinks.episode(jumpBackInCacheItemElement.getPodcastId(), jumpBackInCacheItemElement.getEpisodeId(), jumpBackInCacheItemElement.getPodcastTitle(), jumpBackInCacheItemElement.getTitle()), jumpBackInCacheItemElement.getAvailableUpsells(), UiMetricAttributes$PageType.PODCASTS_JUMP_BACK_IN_EPISODES, UiMetricAttributes$ContentName.PODCASTS_JUMP_BACK_IN_EPISODES, strPodcastEpisode, UiMetricAttributes$EntityType.PODCAST_EPISODE, UiMetricAttributes$EntityIdType.PODCAST_EPISODE_ID);
                    builderWithCompleted.withPlaybackMode(PlaybackModes.normalizeWithAvailabilityDate(jumpBackInCacheItemElement.getContentTraits(), jumpBackInCacheItemElement.getAvailabilityDate(), jumpBackInCacheItemElement.getPlaybackMode()));
                    ArrayList arrayList4 = new ArrayList();
                    if (jumpBackInCacheItemElement.getContentTraits() != null) {
                        arrayList4.addAll(jumpBackInCacheItemElement.getContentTraits());
                    }
                    if (RedhoodPlusClientInterfaces.hasAvailabilityDatePassed(jumpBackInCacheItemElement.getAvailabilityDate())) {
                        arrayList4.remove(ContentTrait.PRE_RELEASE);
                    }
                    builderWithCompleted.withButton(RedhoodPlusClientInterfaces.episodeRowItemUpsellButton(context, arrayList4, listUpsellMethods));
                    builderWithCompleted.withImageBadge(RedhoodPlusClientInterfaces.episodeRowItemImageBadge(context, arrayList4, listUpsellMethods));
                    builderWithCompleted.withDescriptionBadge(RedhoodPlusClientInterfaces.episodeRowItemDescriptionBadge(context, arrayList4, listUpsellMethods));
                    return builderWithCompleted.build();
                }
            }));
        }
        Iterator it = arrayList2.iterator();
        while (it.hasNext()) {
            arrayList.add((EpisodeRowItemElement) ((Future) it.next()).get());
        }
        executorServiceNewCachedThreadPool.shutdown();
        return arrayList;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final List<JumpBackInCacheItemElement> convertJumpBackInItems(final JumpBackInWriteCacheElement writeCacheElement) {
        if (writeCacheElement == null) {
            return Collections.emptyList();
        }
        return writeCacheElement.getElements();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final List<Method> onPlayMethods(final JumpBackInCacheItemElement jumpBackIn, final String entityId, final int position) {
        ArrayList arrayList = new ArrayList();
        arrayList.addAll(DeeplinkMethods.jumpBackInEpisodePlay(jumpBackIn.getEpisodeId(), jumpBackIn.getPlayDeeplink(), getResources()));
        arrayList.addAll(uiMetricClickMethods(entityId, UiMetricAttributes$ActionType.PLAY_PODCAST_EPISODE, position));
        return arrayList;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final List<Method> onPauseMethods(final String entityId, final int position) {
        ArrayList arrayList = new ArrayList();
        arrayList.addAll(Collections.singletonList(PauseMediaMethod.builder().withQueue(Queues.playback()).withForced(Boolean.FALSE).build()));
        arrayList.addAll(uiMetricClickMethods(entityId, UiMetricAttributes$ActionType.PLAY_PAUSE_PODCAST_EPISODE, position));
        return arrayList;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final List<Method> onResumeMethods(final String entityId, final int position) {
        ArrayList arrayList = new ArrayList();
        arrayList.addAll(Collections.singletonList(ResumeMediaMethod.builder().withQueue(Queues.playback()).withForced(Boolean.FALSE).build()));
        arrayList.addAll(uiMetricClickMethods(entityId, UiMetricAttributes$ActionType.PLAY_PODCAST_EPISODE, position));
        return arrayList;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public BookmarkElement bookmarkElement(final JumpBackInCacheItemElement jumpBackIn) {
        return BookmarkElement.builder().withId(jumpBackIn.getEpisodeId()).withTotalDurationMilliseconds(jumpBackIn.getTotalDurationMilliseconds()).build();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public DownloadElement downloadElement(final JumpBackInCacheItemElement jumpBackIn, final String entityId, final int position) {
        String episodeId = jumpBackIn.getEpisodeId();
        ArrayList arrayList = new ArrayList();
        arrayList.addAll(DeeplinkMethods.download(episodeId));
        arrayList.addAll(uiMetricClickMethods(entityId, UiMetricAttributes$ActionType.DOWNLOAD_PODCAST_EPISODE, position));
        return DownloadElement.builder().withId(episodeId).withOnDownload(arrayList).build();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public List<Method> uiMetricClickMethods(final String entityId, final UiMetricAttributes$ActionType actionType, final int position) {
        return UiMetricMethods.onClicked(UiMetricAttributes$PageType.PODCASTS_JUMP_BACK_IN_EPISODES, actionType, UiMetricAttributes$EntityType.PODCAST_EPISODE, UiMetricAttributes$EntityIdType.PODCAST_EPISODE_ID, entityId, Integer.valueOf(position), UiMetricAttributes$ContentName.PODCASTS_JUMP_BACK_IN_EPISODES);
    }
}
