package com.amazon.mp3.playback.handler;

import android.content.Context;
import com.amazon.mp3.card.prime.StationUtils;
import com.amazon.mp3.haptics.HapticsUtil;
import com.amazon.mp3.prime.browse.CatalogContentPlaybackHandler;
import com.amazon.mp3.prime.cta.PrimeCollectionTask;
import com.amazon.mp3.search.model.Station;
import com.amazon.music.metrics.mts.event.types.PlaybackPageType;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: StationPlaybackHandler.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000*\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\u0018\u00002\b\u0012\u0004\u0012\u00020\u00020\u0001B\u0017\u0012\b\u0010\u0003\u001a\u0004\u0018\u00010\u0004\u0012\u0006\u0010\u0005\u001a\u00020\u0006¢\u0006\u0002\u0010\u0007J\u0018\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u00022\u0006\u0010\u000b\u001a\u00020\fH\u0016R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006\r"}, d2 = {"Lcom/amazon/mp3/playback/handler/StationPlaybackHandler;", "Lcom/amazon/mp3/prime/browse/CatalogContentPlaybackHandler;", "Lcom/amazon/mp3/search/model/Station;", "context", "Landroid/content/Context;", "playbackPageType", "Lcom/amazon/music/metrics/mts/event/types/PlaybackPageType;", "(Landroid/content/Context;Lcom/amazon/music/metrics/mts/event/types/PlaybackPageType;)V", "startPlayback", "", "station", "task", "Lcom/amazon/mp3/prime/cta/PrimeCollectionTask$Task;", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class StationPlaybackHandler extends CatalogContentPlaybackHandler<Station> {
    private final PlaybackPageType playbackPageType;

    /* JADX WARN: 'super' call moved to the top of the method (can break code semantics) */
    public StationPlaybackHandler(Context context, PlaybackPageType playbackPageType) {
        super(context);
        Intrinsics.checkNotNullParameter(playbackPageType, "playbackPageType");
        this.playbackPageType = playbackPageType;
    }

    @Override // com.amazon.mp3.prime.browse.CatalogContentPlaybackHandler
    public void startPlayback(Station station, PrimeCollectionTask.Task task) {
        Intrinsics.checkNotNullParameter(station, "station");
        Intrinsics.checkNotNullParameter(task, "task");
        Context context = this.mContext;
        if (context == null) {
            return;
        }
        HapticsUtil.INSTANCE.hapticFeedbackOnPlayback();
        StationUtils.playStation(context, station, true, isFullscreenPlayback(task), this.playbackPageType, null);
    }
}
