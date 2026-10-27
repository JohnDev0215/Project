package com.amazon.mp3.providers;

import android.app.PendingIntent;
import android.content.Context;
import com.amazon.mp3.auto.AutoUiEventUtil;
import com.amazon.mp3.library.item.AbstractItem;
import com.amazon.mp3.library.item.factory.CirrusSourceLibraryItemFactory;
import com.amazon.mp3.playback.playbackcontrol.ActionValidatedPlaybackController;
import com.amazon.mp3.playback.playbackcontrol.PlaybackControllerProvider;
import com.amazon.mp3.prime.PrimePlaylistTracksTable;
import com.amazon.music.media.playback.ControlSource;
import com.amazon.music.media.playback.Playback;
import com.amazon.music.metrics.mts.event.types.ExperienceMode;
import com.amazon.music.metrics.mts.event.types.uiinteraction.ActionType;
import com.amazon.music.metrics.mts.event.types.uiinteraction.PageType;
import com.amazon.music.views.library.deeplinks.ContentMetadataOnClickListener;
import com.amazon.music.views.library.metadata.ContentMetadata;
import com.amazon.music.views.library.metadata.TrackMetadata;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: PlayQueueClickListener.kt */
/* JADX INFO: loaded from: classes4.dex */
@Metadata(d1 = {"\u0000R\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010 \n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J \u0010\u000f\u001a\u00020\u00102\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\u0005\u001a\u00020\u00062\b\u0010\u0007\u001a\u0004\u0018\u00010\bJ\u0010\u0010\u0011\u001a\u00020\u00102\u0006\u0010\u0012\u001a\u00020\u0013H\u0016J(\u0010\u0014\u001a\u00020\u00102\f\u0010\u0015\u001a\b\u0012\u0004\u0012\u00020\u00130\u00162\u0006\u0010\u0017\u001a\u00020\u00182\b\u0010\u0019\u001a\u0004\u0018\u00010\u001aH\u0016R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u0007\u001a\u0004\u0018\u00010\bX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\nX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\fX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\u000eX\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u001b"}, d2 = {"Lcom/amazon/mp3/providers/PlayQueueClickListener;", "Lcom/amazon/music/views/library/deeplinks/ContentMetadataOnClickListener;", "context", "Landroid/content/Context;", "(Landroid/content/Context;)V", "actionType", "Lcom/amazon/music/metrics/mts/event/types/uiinteraction/ActionType;", "experienceMode", "Lcom/amazon/music/metrics/mts/event/types/ExperienceMode;", "pageType", "Lcom/amazon/music/metrics/mts/event/types/uiinteraction/PageType;", "playbackController", "Lcom/amazon/mp3/playback/playbackcontrol/ActionValidatedPlaybackController;", "sendMetrics", "", "configMetricsArgs", "", "onContentClicked", "contentMetadata", "Lcom/amazon/music/views/library/metadata/ContentMetadata;", "onContentListClicked", "contentMetadataList", "", "positionToStart", "", "contentListTitle", "", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class PlayQueueClickListener implements ContentMetadataOnClickListener {
    private ActionType actionType;
    private final Context context;
    private ExperienceMode experienceMode;
    private PageType pageType;
    private final ActionValidatedPlaybackController playbackController;
    private boolean sendMetrics;

    public PlayQueueClickListener(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        this.context = context;
        this.playbackController = PlaybackControllerProvider.INSTANCE.getController(ControlSource.APP_UI);
        this.pageType = PageType.UNKNOWN;
        this.actionType = ActionType.PLAY_SONG;
    }

    @Override // com.amazon.music.views.library.deeplinks.ContentMetadataOnClickListener
    public void onContentClicked(ContentMetadata contentMetadata) {
        Intrinsics.checkNotNullParameter(contentMetadata, "contentMetadata");
        if (contentMetadata instanceof TrackMetadata) {
            AbstractItem item = new CirrusSourceLibraryItemFactory(this.context).getItem(contentMetadata.getUri());
            boolean z = false;
            if (item != null && item.isAvailable()) {
                z = true;
            }
            if (z || !Intrinsics.areEqual(((TrackMetadata) contentMetadata).getSource(), "")) {
                TrackMetadata trackMetadata = (TrackMetadata) contentMetadata;
                if (Intrinsics.areEqual(PrimePlaylistTracksTable.AssetType.VIDEO.name(), trackMetadata.getAssetType())) {
                    try {
                        Playback.getInstance().getPlaybackConfig().getClickAction().send();
                    } catch (PendingIntent.CanceledException e) {
                        e.printStackTrace();
                    }
                }
                Integer rowNumber = trackMetadata.getRowNumber();
                if (rowNumber == null) {
                    return;
                }
                this.playbackController.skipToIndex(rowNumber.intValue());
                this.playbackController.play();
                if (this.sendMetrics) {
                    AutoUiEventUtil.sendUiClickEvent(this.actionType, this.pageType, this.experienceMode, contentMetadata.getViewId());
                }
            }
        }
    }

    @Override // com.amazon.music.views.library.deeplinks.ContentMetadataOnClickListener
    public void onContentListClicked(List<? extends ContentMetadata> contentMetadataList, int positionToStart, String contentListTitle) {
        Intrinsics.checkNotNullParameter(contentMetadataList, "contentMetadataList");
        if (contentMetadataList.size() == 1) {
            onContentClicked(contentMetadataList.get(0));
        }
    }

    public final void configMetricsArgs(PageType pageType, ActionType actionType, ExperienceMode experienceMode) {
        Intrinsics.checkNotNullParameter(pageType, "pageType");
        Intrinsics.checkNotNullParameter(actionType, "actionType");
        this.sendMetrics = true;
        this.pageType = pageType;
        this.actionType = actionType;
        this.experienceMode = experienceMode;
    }
}
