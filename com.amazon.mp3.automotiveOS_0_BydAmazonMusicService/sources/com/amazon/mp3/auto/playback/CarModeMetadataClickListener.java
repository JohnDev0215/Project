package com.amazon.mp3.auto.playback;

import android.content.Context;
import android.os.Bundle;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.loader.app.LoaderManager;
import com.amazon.mp3.AmazonApplication;
import com.amazon.mp3.artist.view.PrimeItemsTransformationUtil;
import com.amazon.mp3.auto.AutoFlexEventUtil;
import com.amazon.mp3.auto.AutoUiEventUtil;
import com.amazon.mp3.auto.carmode.CarModeAction;
import com.amazon.mp3.auto.carmode.CarModeNavigation;
import com.amazon.mp3.auto.storage.PresetIdentifier;
import com.amazon.mp3.auto.storage.PresetIdentifierHelper;
import com.amazon.mp3.brush.v3.converters.BrushV3SimpleHorizontalTileModelConverter;
import com.amazon.mp3.card.prime.StationUtils;
import com.amazon.mp3.cloudqueue.model.PlayableEntityIdentifier;
import com.amazon.mp3.haptics.HapticsUtil;
import com.amazon.mp3.playback.PlaybackError;
import com.amazon.mp3.playback.PlaybackListener;
import com.amazon.mp3.playback.harley.PlayQueueSequencer;
import com.amazon.mp3.playback.presets.CarModeQueueManager;
import com.amazon.mp3.prime.browse.metadata.PrimeTrack;
import com.amazon.mp3.prime.cta.PrimeCollectionTask;
import com.amazon.mp3.prime.upsell.nightwing.model.UpsellInformation;
import com.amazon.mp3.search.view.PrimeTracksLoadedCallbacks;
import com.amazon.mp3.util.ContentAccessUtil;
import com.amazon.mp3.util.Log;
import com.amazon.mp3.util.MetricsUtil;
import com.amazon.mp3.util.PlaybackHandlerUtil;
import com.amazon.mp3.util.UserSubscriptionUtil;
import com.amazon.music.find.utils.DeeplinksUtils;
import com.amazon.music.media.playback.ChangeReason;
import com.amazon.music.media.playback.ControlSource;
import com.amazon.music.media.playback.Playback;
import com.amazon.music.media.playback.util.Clock;
import com.amazon.music.metrics.mts.event.types.ExperienceMode;
import com.amazon.music.metrics.mts.event.types.PlaybackPageType;
import com.amazon.music.metrics.mts.event.types.pagelatencyinfo.PageLoadLatencyCode;
import com.amazon.music.metrics.mts.event.types.uiinteraction.ActionType;
import com.amazon.music.metrics.mts.event.types.uiinteraction.PageType;
import com.amazon.music.views.library.deeplinks.ContentMetadataOnClickListener;
import com.amazon.music.views.library.metadata.AlbumMetadata;
import com.amazon.music.views.library.metadata.ArtistMetadata;
import com.amazon.music.views.library.metadata.ContentMetadata;
import com.amazon.music.views.library.metadata.PlaylistMetadata;
import com.amazon.music.views.library.metadata.PodcastMetadata;
import com.amazon.music.views.library.metadata.StationMetadata;
import com.amazon.music.views.library.metadata.TrackMetadata;
import com.amazon.music.views.library.metadata.UserPlaylistMetadata;
import java.util.ArrayList;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt___CollectionsKt;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: CarModeMetadataClickListener.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(bv = {}, d1 = {"\u0000f\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\b\u0018\u00002\u00020\u0001:\u0001.B\u001f\u0012\u0006\u0010\u0016\u001a\u00020\u0015\u0012\u0006\u0010\u0019\u001a\u00020\u0018\u0012\u0006\u0010\u001c\u001a\u00020\u001b¢\u0006\u0004\b,\u0010-J\u001c\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00050\u00022\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00030\u0002H\u0002J \u0010\u000b\u001a\u00020\n2\b\u0010\b\u001a\u0004\u0018\u00010\u00072\f\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u00050\u0002H\u0002J\u0012\u0010\u000e\u001a\u0004\u0018\u00010\r2\u0006\u0010\f\u001a\u00020\u0003H\u0002J\u0010\u0010\u0010\u001a\u00020\u000f2\u0006\u0010\f\u001a\u00020\u0003H\u0016J(\u0010\u0013\u001a\u00020\u000f2\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00030\u00022\u0006\u0010\u0011\u001a\u00020\n2\b\u0010\u0012\u001a\u0004\u0018\u00010\u0007H\u0016J\u0006\u0010\u0014\u001a\u00020\u000fR\u0014\u0010\u0016\u001a\u00020\u00158\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0016\u0010\u0017R\u0014\u0010\u0019\u001a\u00020\u00188\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0019\u0010\u001aR\u0014\u0010\u001c\u001a\u00020\u001b8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u001c\u0010\u001dR\u001c\u0010\u001f\u001a\n \u001e*\u0004\u0018\u00010\u00070\u00078\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u001f\u0010 R\u0014\u0010\"\u001a\u00020!8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\"\u0010#R\u0014\u0010%\u001a\u00020$8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b%\u0010&R\u0014\u0010(\u001a\u00020'8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b(\u0010)R\u0018\u0010*\u001a\u0004\u0018\u00010\u00038\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b*\u0010+¨\u0006/"}, d2 = {"Lcom/amazon/mp3/auto/playback/CarModeMetadataClickListener;", "Lcom/amazon/music/views/library/deeplinks/ContentMetadataOnClickListener;", "", "Lcom/amazon/music/views/library/metadata/ContentMetadata;", "contentMetadataList", "Lcom/amazon/mp3/prime/browse/metadata/PrimeTrack;", "createTrackList", "", "asin", "tracks", "", "getStartPosition", "contentMetadata", "Lcom/amazon/mp3/playback/PlaybackListener;", "setupPlaybackFailureListener", "", "onContentClicked", "positionToStart", "contentListTitle", "onContentListClicked", "cleanup", "Landroidx/fragment/app/Fragment;", "fragment", "Landroidx/fragment/app/Fragment;", "Lcom/amazon/music/metrics/mts/event/types/uiinteraction/PageType;", "pageType", "Lcom/amazon/music/metrics/mts/event/types/uiinteraction/PageType;", "Lcom/amazon/music/metrics/mts/event/types/PlaybackPageType;", "playbackPageType", "Lcom/amazon/music/metrics/mts/event/types/PlaybackPageType;", "kotlin.jvm.PlatformType", "TAG", "Ljava/lang/String;", "Landroid/content/Context;", "context", "Landroid/content/Context;", "Lcom/amazon/mp3/playback/presets/CarModeQueueManager;", "carModeQueueManager", "Lcom/amazon/mp3/playback/presets/CarModeQueueManager;", "Lcom/amazon/mp3/auto/playback/ContentMetadataPlaybackHandler;", "contentMetadataPlaybackHandler", "Lcom/amazon/mp3/auto/playback/ContentMetadataPlaybackHandler;", "currentContentMetadata", "Lcom/amazon/music/views/library/metadata/ContentMetadata;", "<init>", "(Landroidx/fragment/app/Fragment;Lcom/amazon/music/metrics/mts/event/types/uiinteraction/PageType;Lcom/amazon/music/metrics/mts/event/types/PlaybackPageType;)V", "CarModePlaybackFailureListener", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0})
public final class CarModeMetadataClickListener implements ContentMetadataOnClickListener {
    private final String TAG;
    private final CarModeQueueManager carModeQueueManager;
    private final ContentMetadataPlaybackHandler contentMetadataPlaybackHandler;
    private final Context context;
    private ContentMetadata currentContentMetadata;
    private final Fragment fragment;
    private final PageType pageType;
    private final PlaybackPageType playbackPageType;

    public CarModeMetadataClickListener(Fragment fragment, PageType pageType, PlaybackPageType playbackPageType) {
        Intrinsics.checkNotNullParameter(fragment, "fragment");
        Intrinsics.checkNotNullParameter(pageType, "pageType");
        Intrinsics.checkNotNullParameter(playbackPageType, "playbackPageType");
        this.fragment = fragment;
        this.pageType = pageType;
        this.playbackPageType = playbackPageType;
        this.TAG = CarModeMetadataClickListener.class.getSimpleName();
        Context contextRequireContext = fragment.requireContext();
        Intrinsics.checkNotNullExpressionValue(contextRequireContext, "fragment.requireContext()");
        this.context = contextRequireContext;
        CarModeQueueManager carModeQueueManager = new CarModeQueueManager(pageType);
        this.carModeQueueManager = carModeQueueManager;
        this.contentMetadataPlaybackHandler = new ContentMetadataPlaybackHandler(contextRequireContext, pageType, playbackPageType, ExperienceMode.CAR_MODE, carModeQueueManager, null, 32, null);
    }

    @Override // com.amazon.music.views.library.deeplinks.ContentMetadataOnClickListener
    public void onContentClicked(ContentMetadata contentMetadata) {
        Intrinsics.checkNotNullParameter(contentMetadata, "contentMetadata");
        HapticsUtil.INSTANCE.hapticFeedbackOnPlayback();
        if (Intrinsics.areEqual(contentMetadata, this.currentContentMetadata)) {
            return;
        }
        this.currentContentMetadata = contentMetadata;
        if (contentMetadata instanceof PlaylistMetadata ? true : contentMetadata instanceof AlbumMetadata ? true : contentMetadata instanceof StationMetadata ? true : contentMetadata instanceof ArtistMetadata ? true : contentMetadata instanceof UserPlaylistMetadata ? true : contentMetadata instanceof TrackMetadata) {
            ContentMetadataPlaybackHandler.handle$default(this.contentMetadataPlaybackHandler, contentMetadata, false, false, setupPlaybackFailureListener(contentMetadata), 6, null);
            return;
        }
        if (contentMetadata instanceof PodcastMetadata) {
            ContentMetadataPlaybackHandler.handle$default(this.contentMetadataPlaybackHandler, contentMetadata, false, false, setupPlaybackFailureListener(contentMetadata), 6, null);
            if (AmazonApplication.getCapabilities().isCarModePodcastEpisodeListEnabled()) {
                PodcastMetadata podcastMetadata = (PodcastMetadata) contentMetadata;
                if (DeeplinksUtils.isPodcastShow(podcastMetadata.getTarget()) && (this.context instanceof CarModeNavigation)) {
                    Bundle bundle = new Bundle();
                    bundle.putString("PODCAST_SHOW_ID", podcastMetadata.getPodcastShowCatalogId());
                    ((CarModeNavigation) this.context).navigateTo(CarModeAction.SHOW_PODCAST_EPISODE_LIST_PAGE, bundle);
                    MetricsUtil.emitLatencyStartLegMetric(PageLoadLatencyCode.CAR_MODE_PODCAST_EPISODE_LIST);
                    return;
                }
                return;
            }
            return;
        }
        Log.error(this.TAG, "Do not support playing " + contentMetadata + " with content type " + ((Object) contentMetadata.getClass().getSimpleName()) + '.');
        PlaybackHandlerUtil playbackHandlerUtil = PlaybackHandlerUtil.INSTANCE;
        String TAG = this.TAG;
        Intrinsics.checkNotNullExpressionValue(TAG, "TAG");
        playbackHandlerUtil.emitPlaybackFlexEvent("Flex_Event_Playback_Failed", TAG, Intrinsics.stringPlus("Unsupported content type ", contentMetadata.getClass().getSimpleName()));
    }

    @Override // com.amazon.music.views.library.deeplinks.ContentMetadataOnClickListener
    public void onContentListClicked(List<? extends ContentMetadata> contentMetadataList, int positionToStart, String contentListTitle) {
        LoaderManager supportLoaderManager;
        Intrinsics.checkNotNullParameter(contentMetadataList, "contentMetadataList");
        HapticsUtil.INSTANCE.hapticFeedbackOnPlayback();
        this.currentContentMetadata = null;
        if (contentMetadataList.isEmpty()) {
            Log.error(this.TAG, "Unable to start playback for empty ContentMetadata list");
            return;
        }
        this.carModeQueueManager.setCurrentPreset(contentMetadataList);
        Playback.getInstance().getPlaybackController(ControlSource.APP_UI).stop(ChangeReason.NEW_SOURCE);
        PlayQueueSequencer.getInstance().getBackgroundExecutor().execute(new Runnable() { // from class: com.amazon.mp3.auto.playback.CarModeMetadataClickListener$$ExternalSyntheticLambda0
            @Override // java.lang.Runnable
            public final void run() {
                CarModeMetadataClickListener.m355onContentListClicked$lambda0();
            }
        });
        if (UserSubscriptionUtil.isNightwingOnly()) {
            ArrayList arrayList = new ArrayList(contentMetadataList.size());
            for (ContentMetadata contentMetadata : contentMetadataList) {
                PlayableEntityIdentifier entityIdentifier = PlaybackHandlerUtil.INSTANCE.getEntityIdentifier(contentMetadata);
                if (entityIdentifier == null) {
                    Log.warning(this.TAG, "Can't play unsupported content type: " + contentMetadata + '.');
                } else {
                    arrayList.add(entityIdentifier);
                }
            }
            StationUtils.startSFA((Context) this.fragment.getActivity(), (List<PlayableEntityIdentifier>) arrayList, contentListTitle, false, this.playbackPageType, (UpsellInformation) null);
            return;
        }
        List<PrimeTrack> listCreateTrackList = createTrackList(contentMetadataList);
        if (listCreateTrackList.isEmpty()) {
            Log.error(this.TAG, "No track is available to play");
            return;
        }
        int startPosition = getStartPosition(this.carModeQueueManager.getCurrentTrack(contentMetadataList), listCreateTrackList);
        AutoUiEventUtil.sendUiClickEvent(ActionType.PLAY, this.pageType, ExperienceMode.CAR_MODE, contentMetadataList.get(startPosition).getViewId());
        PrimeTracksLoadedCallbacks primeTracksLoadedCallbacks = new PrimeTracksLoadedCallbacks(this.fragment.getActivity(), listCreateTrackList, startPosition, this.playbackPageType, Clock.now(), PrimeCollectionTask.Task.PLAYBACK, contentListTitle, setupPlaybackFailureListener((ContentMetadata) CollectionsKt___CollectionsKt.first((List) contentMetadataList)));
        FragmentActivity activity = this.fragment.getActivity();
        if (activity == null || (supportLoaderManager = activity.getSupportLoaderManager()) == null) {
            return;
        }
        supportLoaderManager.initLoader(0, null, primeTracksLoadedCallbacks);
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: onContentListClicked$lambda-0, reason: not valid java name */
    public static final void m355onContentListClicked$lambda0() {
        PlayQueueSequencer.getInstance().clear();
    }

    private final List<PrimeTrack> createTrackList(List<? extends ContentMetadata> contentMetadataList) {
        int iMin = Math.min(PrimeTracksLoadedCallbacks.getMaxQueueSize(), contentMetadataList.size());
        ArrayList arrayList = new ArrayList();
        for (ContentMetadata contentMetadata : contentMetadataList) {
            if (arrayList.size() == iMin) {
                return arrayList;
            }
            if (contentMetadata instanceof TrackMetadata) {
                TrackMetadata trackMetadata = (TrackMetadata) contentMetadata;
                PrimeTrack track = PrimeItemsTransformationUtil.convertToPrimeTrack(trackMetadata);
                if (ContentAccessUtil.getCatalogContentUnavailableReason(track, ContentAccessUtil.ContentAccessOperation.STREAM) == null) {
                    Intrinsics.checkNotNullExpressionValue(track, "track");
                    arrayList.add(track);
                } else {
                    Log.warning(this.TAG, "User is not eligible to play track " + ((Object) trackMetadata.getTitle()) + ' ' + ((Object) trackMetadata.getAsin()));
                }
            } else {
                Log.warning(this.TAG, "Unable to add non track entity");
            }
        }
        return arrayList;
    }

    private final int getStartPosition(String asin, List<? extends PrimeTrack> tracks) {
        if (asin == null) {
            return 0;
        }
        for (int i = 0; i < tracks.size(); i++) {
            if (Intrinsics.areEqual(tracks.get(i).getAsin(), asin)) {
                return i;
            }
        }
        Log.error(this.TAG, "Could not find asin in the track list. Will start at beginning.");
        return 0;
    }

    public final void cleanup() {
        this.carModeQueueManager.cleanup();
    }

    private final PlaybackListener setupPlaybackFailureListener(ContentMetadata contentMetadata) {
        PresetIdentifier presetIdentifierAsPresetIdentifier = PresetIdentifierHelper.INSTANCE.asPresetIdentifier(contentMetadata);
        String widgetId = BrushV3SimpleHorizontalTileModelConverter.INSTANCE.getWidgetId(contentMetadata);
        if (presetIdentifierAsPresetIdentifier == null || widgetId == null) {
            return null;
        }
        return new CarModePlaybackFailureListener(presetIdentifierAsPresetIdentifier, widgetId);
    }

    /* JADX INFO: compiled from: CarModeMetadataClickListener.kt */
    @Metadata(bv = {}, d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000e\n\u0002\b\u0007\u0018\u00002\u00020\u0001B\u0017\u0012\u0006\u0010\n\u001a\u00020\t\u0012\u0006\u0010\u000f\u001a\u00020\u000e¢\u0006\u0004\b\u0013\u0010\u0014J \u0010\b\u001a\u00020\u00072\u0006\u0010\u0003\u001a\u00020\u00022\u000e\u0010\u0006\u001a\n\u0018\u00010\u0004j\u0004\u0018\u0001`\u0005H\u0016R\u0017\u0010\n\u001a\u00020\t8\u0006¢\u0006\f\n\u0004\b\n\u0010\u000b\u001a\u0004\b\f\u0010\rR\u0017\u0010\u000f\u001a\u00020\u000e8\u0006¢\u0006\f\n\u0004\b\u000f\u0010\u0010\u001a\u0004\b\u0011\u0010\u0012¨\u0006\u0015"}, d2 = {"Lcom/amazon/mp3/auto/playback/CarModeMetadataClickListener$CarModePlaybackFailureListener;", "Lcom/amazon/mp3/playback/PlaybackListener;", "Lcom/amazon/mp3/playback/PlaybackError;", "error", "Ljava/lang/Exception;", "Lkotlin/Exception;", "exception", "", "onPlaybackFailure", "Lcom/amazon/mp3/auto/storage/PresetIdentifier;", "identifier", "Lcom/amazon/mp3/auto/storage/PresetIdentifier;", "getIdentifier", "()Lcom/amazon/mp3/auto/storage/PresetIdentifier;", "", "widgetId", "Ljava/lang/String;", "getWidgetId", "()Ljava/lang/String;", "<init>", "(Lcom/amazon/mp3/auto/storage/PresetIdentifier;Ljava/lang/String;)V", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0})
    public static final class CarModePlaybackFailureListener implements PlaybackListener {
        private final PresetIdentifier identifier;
        private final String widgetId;

        public CarModePlaybackFailureListener(PresetIdentifier identifier, String widgetId) {
            Intrinsics.checkNotNullParameter(identifier, "identifier");
            Intrinsics.checkNotNullParameter(widgetId, "widgetId");
            this.identifier = identifier;
            this.widgetId = widgetId;
        }

        @Override // com.amazon.mp3.playback.PlaybackListener
        public void onPlaybackFailure(PlaybackError error, Exception exception) {
            Intrinsics.checkNotNullParameter(error, "error");
            AutoFlexEventUtil.INSTANCE.emitCarModePlaybackFailureFlexEvent(this.identifier.getEntityType().name(), this.identifier.getIdentifier(), this.widgetId, error.name());
        }
    }
}
