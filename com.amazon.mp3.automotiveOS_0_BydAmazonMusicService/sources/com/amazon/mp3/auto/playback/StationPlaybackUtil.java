package com.amazon.mp3.auto.playback;

import android.content.Context;
import com.amazon.mp3.artist.view.PrimeItemsTransformationUtil;
import com.amazon.mp3.auto.VoiceUtil;
import com.amazon.mp3.card.prime.StationUtils;
import com.amazon.mp3.cloudqueue.model.PlayableEntityIdentifier;
import com.amazon.mp3.library.provider.MediaProvider;
import com.amazon.mp3.playback.PlaybackListener;
import com.amazon.mp3.util.Log;
import com.amazon.mp3.util.PlaybackHandlerUtil;
import com.amazon.music.metrics.mts.event.types.ExperienceMode;
import com.amazon.music.metrics.mts.event.types.PlaybackPageType;
import com.amazon.music.metrics.mts.event.types.uiinteraction.ActionType;
import com.amazon.music.metrics.mts.event.types.uiinteraction.PageType;
import com.amazon.music.views.library.metadata.ContentMetadata;
import com.amazon.music.views.library.metadata.StationMetadata;
import com.amazon.music.views.library.metadata.UserPlaylistMetadata;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: StationPlaybackUtil.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000L\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\bÆ\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002JN\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u000f2\b\u0010\u0010\u001a\u0004\u0018\u00010\u00112\b\b\u0002\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u0014\u001a\u00020\u00132\n\b\u0002\u0010\u0015\u001a\u0004\u0018\u00010\u0016JD\u0010\u0017\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\u00182\u0006\u0010\n\u001a\u00020\u000b2\u0006\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u000f2\n\b\u0002\u0010\u0010\u001a\u0004\u0018\u00010\u00112\b\b\u0002\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u0014\u001a\u00020\u0013R\u0016\u0010\u0003\u001a\n \u0005*\u0004\u0018\u00010\u00040\u0004X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\u0019"}, d2 = {"Lcom/amazon/mp3/auto/playback/StationPlaybackUtil;", "", "()V", "TAG", "", "kotlin.jvm.PlatformType", "playStation", "", "contentMetadata", "Lcom/amazon/music/views/library/metadata/StationMetadata;", "context", "Landroid/content/Context;", "playbackPageType", "Lcom/amazon/music/metrics/mts/event/types/PlaybackPageType;", "pageType", "Lcom/amazon/music/metrics/mts/event/types/uiinteraction/PageType;", "experienceMode", "Lcom/amazon/music/metrics/mts/event/types/ExperienceMode;", "isFullScreen", "", "isVoiceInitiated", "playbackListener", "Lcom/amazon/mp3/playback/PlaybackListener;", "startSFA", "Lcom/amazon/music/views/library/metadata/ContentMetadata;", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class StationPlaybackUtil {
    public static final StationPlaybackUtil INSTANCE = new StationPlaybackUtil();
    private static final String TAG = StationPlaybackUtil.class.getSimpleName();

    private StationPlaybackUtil() {
    }

    public final void startSFA(ContentMetadata contentMetadata, Context context, PlaybackPageType playbackPageType, PageType pageType, ExperienceMode experienceMode, boolean isFullScreen, boolean isVoiceInitiated) {
        Intrinsics.checkNotNullParameter(contentMetadata, "contentMetadata");
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(playbackPageType, "playbackPageType");
        Intrinsics.checkNotNullParameter(pageType, "pageType");
        PlaybackHandlerUtil playbackHandlerUtil = PlaybackHandlerUtil.INSTANCE;
        PlayableEntityIdentifier entityIdentifier = playbackHandlerUtil.getEntityIdentifier(contentMetadata);
        if (entityIdentifier == null) {
            Log.error(TAG, "Cannot start SFA for " + contentMetadata + " because of null identifier");
            return;
        }
        VoiceUtil voiceUtil = VoiceUtil.INSTANCE;
        voiceUtil.sendClickEvent(ActionType.START_STATION_FROM_ANYTHING, pageType, experienceMode, contentMetadata.getViewId(), isVoiceInitiated);
        if (contentMetadata instanceof UserPlaylistMetadata) {
            List<String> asinListSeedFromUserPlaylistLuid = StationUtils.getAsinListSeedFromUserPlaylistLuid(context, ((UserPlaylistMetadata) contentMetadata).getPlaylistId());
            if (asinListSeedFromUserPlaylistLuid == null || asinListSeedFromUserPlaylistLuid.isEmpty()) {
                Log.error(TAG, "get null or empty track ASINs");
                return;
            } else {
                StationUtils.startSFA(context, voiceUtil.getControlSource(isVoiceInitiated), StationUtils.convertAsinsToTracks(asinListSeedFromUserPlaylistLuid), playbackHandlerUtil.getIdentifierType(contentMetadata), null, isFullScreen, playbackPageType, null, isVoiceInitiated, true);
                return;
            }
        }
        StationUtils.startSFA(context, entityIdentifier.getIdentifier(), playbackHandlerUtil.getIdentifierType(contentMetadata), voiceUtil.getControlSource(isVoiceInitiated), null, true, isFullScreen, playbackPageType, null, isVoiceInitiated, true);
    }

    public final void playStation(StationMetadata contentMetadata, Context context, PlaybackPageType playbackPageType, PageType pageType, ExperienceMode experienceMode, boolean isFullScreen, boolean isVoiceInitiated, PlaybackListener playbackListener) {
        Intrinsics.checkNotNullParameter(contentMetadata, "contentMetadata");
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(playbackPageType, "playbackPageType");
        Intrinsics.checkNotNullParameter(pageType, "pageType");
        if (MediaProvider.Station.isStationFromAnything(contentMetadata.getUri())) {
            startSFA(contentMetadata, context, playbackPageType, pageType, experienceMode, isFullScreen, isVoiceInitiated);
            return;
        }
        VoiceUtil voiceUtil = VoiceUtil.INSTANCE;
        voiceUtil.sendClickEvent(ActionType.PLAY_STATION, pageType, experienceMode, contentMetadata.getViewId(), isVoiceInitiated);
        StationUtils.playStation(context, PrimeItemsTransformationUtil.convertToStation(contentMetadata), voiceUtil.getControlSource(isVoiceInitiated), (String) null, isFullScreen, playbackPageType, playbackListener, isVoiceInitiated, true);
    }
}
