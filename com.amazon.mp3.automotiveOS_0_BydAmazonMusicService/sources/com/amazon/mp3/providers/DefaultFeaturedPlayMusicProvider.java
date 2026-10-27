package com.amazon.mp3.providers;

import android.net.Uri;
import androidx.fragment.app.Fragment;
import com.amazon.mp3.AmazonApplication;
import com.amazon.mp3.artist.view.PrimeItemsTransformationUtil;
import com.amazon.mp3.external.api.uri.QueryAlbumByAsin;
import com.amazon.mp3.haptics.HapticsUtil;
import com.amazon.mp3.playback.handler.StationPlaybackHandler;
import com.amazon.mp3.prime.cta.PrimeCollectionTask;
import com.amazon.mp3.task.GetPrimePlaylistTracksTask;
import com.amazon.music.deeplink.DeeplinksManager;
import com.amazon.music.deeplink.UnknownDeeplinkException;
import com.amazon.music.destination.AlbumDestination;
import com.amazon.music.metrics.MetricsHolder;
import com.amazon.music.metrics.mts.event.definition.uiinteraction.UiClickEvent;
import com.amazon.music.metrics.mts.event.types.PlaybackPageType;
import com.amazon.music.metrics.mts.event.types.uiinteraction.ActionType;
import com.amazon.music.metrics.mts.event.types.uiinteraction.InteractionType;
import com.amazon.music.router.UnknownDestinationException;
import com.amazon.music.views.library.metadata.AlbumMetadata;
import com.amazon.music.views.library.metadata.ContentMetadata;
import com.amazon.music.views.library.metadata.LiveEventMetadata;
import com.amazon.music.views.library.metadata.PlaylistMetadata;
import com.amazon.music.views.library.metadata.PodcastMetadata;
import com.amazon.music.views.library.metadata.StationMetadata;
import com.amazon.music.views.library.providers.FeaturedPlayMusicProvider;
import com.amazon.podcast.PodcastDeeplinks;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.util.concurrent.ExecutionException;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: DefaultFeaturedPlayMusicProvider.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\u001c\u0010\u0005\u001a\u00020\u00062\b\u0010\u0007\u001a\u0004\u0018\u00010\b2\b\u0010\t\u001a\u0004\u0018\u00010\nH\u0002J\u0010\u0010\u000b\u001a\u00020\u00062\u0006\u0010\f\u001a\u00020\rH\u0016R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u000e"}, d2 = {"Lcom/amazon/mp3/providers/DefaultFeaturedPlayMusicProvider;", "Lcom/amazon/music/views/library/providers/FeaturedPlayMusicProvider;", "mFragment", "Landroidx/fragment/app/Fragment;", "(Landroidx/fragment/app/Fragment;)V", "sendClickEvent", "", "actionType", "Lcom/amazon/music/metrics/mts/event/types/uiinteraction/ActionType;", "blockRef", "", "startPlayback", "contentMetadata", "Lcom/amazon/music/views/library/metadata/ContentMetadata;", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class DefaultFeaturedPlayMusicProvider implements FeaturedPlayMusicProvider {
    private final Fragment mFragment;

    public DefaultFeaturedPlayMusicProvider(Fragment mFragment) {
        Intrinsics.checkNotNullParameter(mFragment, "mFragment");
        this.mFragment = mFragment;
    }

    @Override // com.amazon.music.views.library.providers.FeaturedPlayMusicProvider
    public void startPlayback(ContentMetadata contentMetadata) throws ExecutionException, InterruptedException, MalformedURLException, UnknownDeeplinkException, URISyntaxException, UnsupportedEncodingException, UnknownDestinationException {
        Intrinsics.checkNotNullParameter(contentMetadata, "contentMetadata");
        HapticsUtil.INSTANCE.hapticFeedbackOnPlayback();
        if (contentMetadata instanceof PlaylistMetadata) {
            GetPrimePlaylistTracksTask.createPlaybackTask(this.mFragment.getContext(), ((PlaylistMetadata) contentMetadata).getAsin(), (String) null, PlaybackPageType.PRIME_BROWSE_PLAYLISTS_LIST, GetPrimePlaylistTracksTask.Task.PLAYBACK).execute(new Void[0]);
            sendClickEvent(ActionType.SELECT_PLAYLIST, contentMetadata.getViewId());
            return;
        }
        if (contentMetadata instanceof StationMetadata) {
            new StationPlaybackHandler(this.mFragment.getActivity(), PlaybackPageType.UNKNOWN).checkCanStartPlayback(PrimeItemsTransformationUtil.convertToStation((StationMetadata) contentMetadata), PrimeCollectionTask.Task.PLAYBACK);
            sendClickEvent(ActionType.PLAY_STATION, contentMetadata.getViewId());
            return;
        }
        if (contentMetadata instanceof AlbumMetadata) {
            AlbumDestination albumDestination = new AlbumDestination(((AlbumMetadata) contentMetadata).getAsin(), null, null, null, null);
            PrimeCollectionTask.createPlaybackTask(this.mFragment.getContext(), albumDestination.getCollectionAsin(), albumDestination.getTrackAsin(), PlaybackPageType.ALBUM_DETAIL, PrimeCollectionTask.Task.PLAYBACK, new QueryAlbumByAsin(this.mFragment.getContext()), (PrimeCollectionTask.OnUriReadyCallback) null).execute();
            sendClickEvent(ActionType.SELECT_ALBUM, contentMetadata.getViewId());
        } else if (contentMetadata instanceof LiveEventMetadata) {
            DeeplinksManager.get(AmazonApplication.getContext()).handle(contentMetadata.getUri());
            sendClickEvent(ActionType.GO_LIVE_EVENT, contentMetadata.getViewId());
        } else if (contentMetadata instanceof PodcastMetadata) {
            String target = ((PodcastMetadata) contentMetadata).getTarget();
            DeeplinksManager.get(AmazonApplication.getContext()).handle(Uri.parse(target));
            if (PodcastDeeplinks.isPodcastEpisodeDeeplink(target)) {
                sendClickEvent(ActionType.PLAY_PODCAST_EPISODE, contentMetadata.getViewId());
            } else if (PodcastDeeplinks.isPodcastDeeplink(target)) {
                sendClickEvent(ActionType.PLAY_PODCAST_SHOW, contentMetadata.getViewId());
            }
        }
    }

    private final void sendClickEvent(ActionType actionType, String blockRef) {
        MetricsHolder.getManager().handleEvent(UiClickEvent.builder(actionType).withBlockRef(blockRef).withInteractionType(InteractionType.TAP).withEventTime(System.currentTimeMillis()).build());
    }
}
