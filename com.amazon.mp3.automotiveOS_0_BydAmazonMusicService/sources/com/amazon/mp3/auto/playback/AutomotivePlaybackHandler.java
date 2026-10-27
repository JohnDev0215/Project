package com.amazon.mp3.auto.playback;

import android.content.Context;
import com.amazon.mp3.auto.AutoFlexEventUtil;
import com.amazon.mp3.auto.storage.PresetIdentifier;
import com.amazon.mp3.auto.storage.PresetIdentifierHelper;
import com.amazon.mp3.brush.v3.converters.BrushV3SimpleHorizontalTileModelConverter;
import com.amazon.mp3.playback.PlaybackError;
import com.amazon.mp3.playback.PlaybackListener;
import com.amazon.mp3.playback.presets.CarModeQueueManager;
import com.amazon.mp3.util.Log;
import com.amazon.mp3.util.PlaybackHandlerUtil;
import com.amazon.music.media.auto.provider.AutoMediaItem;
import com.amazon.music.metrics.mts.event.types.PlaybackPageType;
import com.amazon.music.metrics.mts.event.types.uiinteraction.PageType;
import com.amazon.music.views.library.metadata.AlbumMetadata;
import com.amazon.music.views.library.metadata.ArtistMetadata;
import com.amazon.music.views.library.metadata.ContentMetadata;
import com.amazon.music.views.library.metadata.PlaylistMetadata;
import com.amazon.music.views.library.metadata.StationMetadata;
import com.amazon.music.views.library.metadata.TrackMetadata;
import com.amazon.music.views.library.metadata.UserPlaylistMetadata;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt___CollectionsKt;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: AutomotivePlaybackHandler.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(bv = {}, d1 = {"\u0000H\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\u0018\u00002\u00020\u0001:\u0001\u001fB\u001f\u0012\u0006\u0010\u001c\u001a\u00020\u001b\u0012\u0006\u0010\u000b\u001a\u00020\n\u0012\u0006\u0010\u0010\u001a\u00020\u000f¢\u0006\u0004\b\u001d\u0010\u001eJ\u0012\u0010\u0005\u001a\u0004\u0018\u00010\u00042\u0006\u0010\u0003\u001a\u00020\u0002H\u0002J\u000e\u0010\t\u001a\u00020\b2\u0006\u0010\u0007\u001a\u00020\u0006R\u0017\u0010\u000b\u001a\u00020\n8\u0006¢\u0006\f\n\u0004\b\u000b\u0010\f\u001a\u0004\b\r\u0010\u000eR\u0017\u0010\u0010\u001a\u00020\u000f8\u0006¢\u0006\f\n\u0004\b\u0010\u0010\u0011\u001a\u0004\b\u0012\u0010\u0013R\u001c\u0010\u0016\u001a\n \u0015*\u0004\u0018\u00010\u00140\u00148\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0016\u0010\u0017R\u0014\u0010\u0019\u001a\u00020\u00188\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b\u0019\u0010\u001a¨\u0006 "}, d2 = {"Lcom/amazon/mp3/auto/playback/AutomotivePlaybackHandler;", "", "Lcom/amazon/music/views/library/metadata/ContentMetadata;", "contentMetadata", "Lcom/amazon/mp3/playback/PlaybackListener;", "setupPlaybackFailureListener", "Lcom/amazon/music/media/auto/provider/AutoMediaItem;", "autoMediaItem", "", "handleMediaBrowseRequest", "Lcom/amazon/music/metrics/mts/event/types/uiinteraction/PageType;", "pageType", "Lcom/amazon/music/metrics/mts/event/types/uiinteraction/PageType;", "getPageType", "()Lcom/amazon/music/metrics/mts/event/types/uiinteraction/PageType;", "Lcom/amazon/music/metrics/mts/event/types/PlaybackPageType;", "playbackPageType", "Lcom/amazon/music/metrics/mts/event/types/PlaybackPageType;", "getPlaybackPageType", "()Lcom/amazon/music/metrics/mts/event/types/PlaybackPageType;", "", "kotlin.jvm.PlatformType", "TAG", "Ljava/lang/String;", "Lcom/amazon/mp3/auto/playback/ContentMetadataPlaybackHandler;", "contentMetadataPlaybackHandler", "Lcom/amazon/mp3/auto/playback/ContentMetadataPlaybackHandler;", "Landroid/content/Context;", "context", "<init>", "(Landroid/content/Context;Lcom/amazon/music/metrics/mts/event/types/uiinteraction/PageType;Lcom/amazon/music/metrics/mts/event/types/PlaybackPageType;)V", "AutomotivePlaybackFailureListener", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0})
public final class AutomotivePlaybackHandler {
    private final String TAG;
    private final ContentMetadataPlaybackHandler contentMetadataPlaybackHandler;
    private final PageType pageType;
    private final PlaybackPageType playbackPageType;

    public AutomotivePlaybackHandler(Context context, PageType pageType, PlaybackPageType playbackPageType) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(pageType, "pageType");
        Intrinsics.checkNotNullParameter(playbackPageType, "playbackPageType");
        this.pageType = pageType;
        this.playbackPageType = playbackPageType;
        this.TAG = AutomotivePlaybackHandler.class.getSimpleName();
        this.contentMetadataPlaybackHandler = new ContentMetadataPlaybackHandler(context, pageType, playbackPageType, null, new CarModeQueueManager(pageType), null, 32, null);
    }

    public final void handleMediaBrowseRequest(AutoMediaItem autoMediaItem) {
        Intrinsics.checkNotNullParameter(autoMediaItem, "autoMediaItem");
        Log.debug(this.TAG, "handleMediaBrowseRequest for " + autoMediaItem.getTitle() + ' ' + ((Object) autoMediaItem.getMetadata().getClass().getSimpleName()));
        PlaybackListener playbackListener = setupPlaybackFailureListener(autoMediaItem.getMetadata());
        List<ContentMetadata> metadataList = autoMediaItem.getMetadataList();
        if (!(metadataList == null || metadataList.isEmpty())) {
            List<ContentMetadata> metadataList2 = autoMediaItem.getMetadataList();
            if ((metadataList2 == null ? null : (ContentMetadata) CollectionsKt___CollectionsKt.first((List) metadataList2)) instanceof TrackMetadata) {
                List<ContentMetadata> metadataList3 = autoMediaItem.getMetadataList();
                if (metadataList3 == null) {
                    return;
                }
                ContentMetadataPlaybackHandler.handleTrackList$default(this.contentMetadataPlaybackHandler, metadataList3, false, false, autoMediaItem.getTrackIndex(), playbackListener, 6, null);
                return;
            }
        }
        ContentMetadata metadata = autoMediaItem.getMetadata();
        if (metadata instanceof PlaylistMetadata) {
            ContentMetadataPlaybackHandler.handle$default(this.contentMetadataPlaybackHandler, autoMediaItem.getMetadata(), this.pageType == PageType.AUTOMOTIVE_OS_BROWSE_HOME, false, playbackListener, 4, null);
            return;
        }
        if (metadata instanceof UserPlaylistMetadata ? true : metadata instanceof TrackMetadata ? true : metadata instanceof AlbumMetadata ? true : metadata instanceof StationMetadata ? true : metadata instanceof ArtistMetadata) {
            ContentMetadataPlaybackHandler.handle$default(this.contentMetadataPlaybackHandler, autoMediaItem.getMetadata(), false, false, playbackListener, 6, null);
            return;
        }
        Log.error(this.TAG, Intrinsics.stringPlus("Do not support playing content type ", autoMediaItem.getMetadata().getClass().getSimpleName()));
        PlaybackHandlerUtil playbackHandlerUtil = PlaybackHandlerUtil.INSTANCE;
        String TAG = this.TAG;
        Intrinsics.checkNotNullExpressionValue(TAG, "TAG");
        playbackHandlerUtil.emitPlaybackFlexEvent("Flex_Event_Playback_Failed", TAG, Intrinsics.stringPlus("Unsupported content type ", autoMediaItem.getMetadata().getClass().getSimpleName()));
    }

    private final PlaybackListener setupPlaybackFailureListener(ContentMetadata contentMetadata) {
        PresetIdentifier presetIdentifierAsPresetIdentifier = PresetIdentifierHelper.INSTANCE.asPresetIdentifier(contentMetadata);
        String widgetId = BrushV3SimpleHorizontalTileModelConverter.INSTANCE.getWidgetId(contentMetadata);
        if (presetIdentifierAsPresetIdentifier == null || widgetId == null) {
            return null;
        }
        return new AutomotivePlaybackFailureListener(presetIdentifierAsPresetIdentifier, widgetId);
    }

    /* JADX INFO: compiled from: AutomotivePlaybackHandler.kt */
    @Metadata(bv = {}, d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000e\n\u0002\b\u0007\u0018\u00002\u00020\u0001B\u0017\u0012\u0006\u0010\n\u001a\u00020\t\u0012\u0006\u0010\u000f\u001a\u00020\u000e¢\u0006\u0004\b\u0013\u0010\u0014J \u0010\b\u001a\u00020\u00072\u0006\u0010\u0003\u001a\u00020\u00022\u000e\u0010\u0006\u001a\n\u0018\u00010\u0004j\u0004\u0018\u0001`\u0005H\u0016R\u0017\u0010\n\u001a\u00020\t8\u0006¢\u0006\f\n\u0004\b\n\u0010\u000b\u001a\u0004\b\f\u0010\rR\u0017\u0010\u000f\u001a\u00020\u000e8\u0006¢\u0006\f\n\u0004\b\u000f\u0010\u0010\u001a\u0004\b\u0011\u0010\u0012¨\u0006\u0015"}, d2 = {"Lcom/amazon/mp3/auto/playback/AutomotivePlaybackHandler$AutomotivePlaybackFailureListener;", "Lcom/amazon/mp3/playback/PlaybackListener;", "Lcom/amazon/mp3/playback/PlaybackError;", "error", "Ljava/lang/Exception;", "Lkotlin/Exception;", "exception", "", "onPlaybackFailure", "Lcom/amazon/mp3/auto/storage/PresetIdentifier;", "identifier", "Lcom/amazon/mp3/auto/storage/PresetIdentifier;", "getIdentifier", "()Lcom/amazon/mp3/auto/storage/PresetIdentifier;", "", "widgetId", "Ljava/lang/String;", "getWidgetId", "()Ljava/lang/String;", "<init>", "(Lcom/amazon/mp3/auto/storage/PresetIdentifier;Ljava/lang/String;)V", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0})
    public static final class AutomotivePlaybackFailureListener implements PlaybackListener {
        private final PresetIdentifier identifier;
        private final String widgetId;

        public AutomotivePlaybackFailureListener(PresetIdentifier identifier, String widgetId) {
            Intrinsics.checkNotNullParameter(identifier, "identifier");
            Intrinsics.checkNotNullParameter(widgetId, "widgetId");
            this.identifier = identifier;
            this.widgetId = widgetId;
        }

        @Override // com.amazon.mp3.playback.PlaybackListener
        public void onPlaybackFailure(PlaybackError error, Exception exception) {
            Intrinsics.checkNotNullParameter(error, "error");
            AutoFlexEventUtil.INSTANCE.emitAutomotivePlaybackFailureFlexEvent(this.identifier.getEntityType().name(), this.identifier.getIdentifier(), this.widgetId, error.name());
        }
    }
}
