package com.amazon.mp3.explore.providers;

import android.content.Context;
import android.net.Uri;
import com.amazon.mp3.casting.CastingUtil;
import com.amazon.mp3.external.api.uri.QuerySongByAsin;
import com.amazon.mp3.library.item.MusicTrack;
import com.amazon.mp3.playback.harley.PlayQueueSequencer;
import com.amazon.mp3.playback.service.metrics.PlaybackMetricInformation;
import com.amazon.mp3.prime.PrimePlaylistTracksTable;
import com.amazon.mp3.prime.cta.PrimeCollectionTask;
import com.amazon.mp3.prime.cta.PrimeTrackTask;
import com.amazon.music.metrics.mts.event.types.PlaybackPageType;
import java.util.List;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: ExplorerOnUriReadyCallback.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000\\\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\n\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001BS\u0012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u0012\u0006\u0010\u0007\u001a\u00020\u0006\u0012\u0006\u0010\b\u001a\u00020\t\u0012\u0006\u0010\n\u001a\u00020\u000b\u0012\u0006\u0010\f\u001a\u00020\r\u0012\u0006\u0010\u000e\u001a\u00020\u000f\u0012\u0006\u0010\u0010\u001a\u00020\u0011\u0012\u0006\u0010\u0012\u001a\u00020\u0013¢\u0006\u0002\u0010\u0014J8\u0010\u0016\u001a\u00020\u00172\u0006\u0010\u0018\u001a\u00020\u00042\u0006\u0010\u0019\u001a\u00020\u000f2\u0006\u0010\f\u001a\u00020\r2\u0006\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u001a\u001a\u00020\u00132\u0006\u0010\u001b\u001a\u00020\u0001H\u0002J\t\u0010\u001c\u001a\u00020\u0017H\u0082\u0002J\u0016\u0010\u001d\u001a\u00020\u00062\u0006\u0010\u001e\u001a\u00020\u00062\u0006\u0010\u001f\u001a\u00020\u0006J\u0012\u0010 \u001a\u00020\u00172\b\u0010!\u001a\u0004\u0018\u00010\"H\u0016J\u0014\u0010#\u001a\u00020\u00172\n\u0010$\u001a\u00060%j\u0002`&H\u0016R\u000e\u0010\f\u001a\u00020\rX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u0010\u001a\u00020\u0011X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\u000fX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u0082\u0004¢\u0006\u0002\n\u0000R\u0014\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0012\u001a\u00020\u0013X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u000bX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0006X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0015\u001a\u00020\u0006X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006'"}, d2 = {"Lcom/amazon/mp3/explore/providers/ExplorerOnUriReadyCallback;", "Lcom/amazon/mp3/prime/cta/PrimeCollectionTask$OnUriReadyCallback;", "result", "", "Lcom/amazon/mp3/library/item/MusicTrack;", "fetchCount", "", "startingPosition", "playbackPageType", "Lcom/amazon/music/metrics/mts/event/types/PlaybackPageType;", "startTime", "", "context", "Landroid/content/Context;", "playbackMode", "Lcom/amazon/mp3/prime/cta/PrimeCollectionTask$Task;", "metricInformation", "Lcom/amazon/mp3/playback/service/metrics/PlaybackMetricInformation;", "shuffle", "", "(Ljava/util/List;IILcom/amazon/music/metrics/mts/event/types/PlaybackPageType;JLandroid/content/Context;Lcom/amazon/mp3/prime/cta/PrimeCollectionTask$Task;Lcom/amazon/mp3/playback/service/metrics/PlaybackMetricInformation;Z)V", "trackIndex", "addPrimeTrackWithTask", "", "track", "task", "refreshCollection", "callback", "next", "nextTrack", "index", "size", "onAlbumUriReady", "uri", "Landroid/net/Uri;", "onError", "e", "Ljava/lang/Exception;", "Lkotlin/Exception;", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class ExplorerOnUriReadyCallback implements PrimeCollectionTask.OnUriReadyCallback {
    private final Context context;
    private int fetchCount;
    private final PlaybackMetricInformation metricInformation;
    private final PrimeCollectionTask.Task playbackMode;
    private final PlaybackPageType playbackPageType;
    private final List<MusicTrack> result;
    private final boolean shuffle;
    private final long startTime;
    private final int startingPosition;
    private int trackIndex;

    public final int nextTrack(int index, int size) {
        int i = index + 1;
        if (i >= size || i < 0) {
            return 0;
        }
        return i;
    }

    /* JADX WARN: Multi-variable type inference failed */
    public ExplorerOnUriReadyCallback(List<? extends MusicTrack> result, int i, int i2, PlaybackPageType playbackPageType, long j, Context context, PrimeCollectionTask.Task playbackMode, PlaybackMetricInformation metricInformation, boolean z) {
        Intrinsics.checkNotNullParameter(result, "result");
        Intrinsics.checkNotNullParameter(playbackPageType, "playbackPageType");
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(playbackMode, "playbackMode");
        Intrinsics.checkNotNullParameter(metricInformation, "metricInformation");
        this.result = result;
        this.fetchCount = i;
        this.startingPosition = i2;
        this.playbackPageType = playbackPageType;
        this.startTime = j;
        this.context = context;
        this.playbackMode = playbackMode;
        this.metricInformation = metricInformation;
        this.shuffle = z;
        this.trackIndex = i2;
    }

    @Override // com.amazon.mp3.prime.cta.PrimeCollectionTask.OnUriReadyCallback
    public void onAlbumUriReady(Uri uri) {
        this.fetchCount--;
        next();
    }

    @Override // com.amazon.mp3.prime.cta.PrimeCollectionTask.OnUriReadyCallback
    public void onError(Exception e) {
        Intrinsics.checkNotNullParameter(e, "e");
        next();
    }

    private final void next() {
        if (this.fetchCount < 0) {
            PlayQueueSequencer.getInstance().setAddingItems(false);
            return;
        }
        PlayQueueSequencer.getInstance().setAddingItems(true);
        int size = this.result.size();
        int iNextTrack = nextTrack(this.trackIndex, size);
        int i = this.startingPosition;
        if (iNextTrack == i) {
            this.fetchCount = -1;
        }
        boolean z = this.fetchCount <= 0;
        int i2 = this.trackIndex;
        if (i2 == i) {
            addPrimeTrackWithTask(this.result.get(i2), this.playbackMode, this.context, this.metricInformation, CastingUtil.isCastingToAlexa(), this);
        } else if (this.shuffle) {
            addPrimeTrackWithTask(this.result.get(i2), PrimeCollectionTask.Task.ADD_TO_PLAYQUEUE_SHUFFLE_NO_NOTIFICATIONS, this.context, this.metricInformation, z, this);
        } else {
            addPrimeTrackWithTask(this.result.get(i2), PrimeCollectionTask.Task.ADD_TO_PLAYQUEUE_NO_NOTIFICATIONS, this.context, this.metricInformation, z, this);
        }
        this.trackIndex = nextTrack(this.trackIndex, size);
    }

    private final void addPrimeTrackWithTask(MusicTrack track, PrimeCollectionTask.Task task, Context context, PlaybackMetricInformation metricInformation, boolean refreshCollection, PrimeCollectionTask.OnUriReadyCallback callback) {
        PrimeTrackTask primeTrackTaskCreatePlaybackTask = PrimeTrackTask.createPlaybackTask(context, track.getAlbumAsin(), track.getAsin(), metricInformation, this.playbackPageType, task, new QuerySongByAsin(context), this.startTime, callback, refreshCollection);
        primeTrackTaskCreatePlaybackTask.setIsVideo(PrimePlaylistTracksTable.AssetType.VIDEO == track.getAssetType());
        primeTrackTaskCreatePlaybackTask.execute();
    }
}
