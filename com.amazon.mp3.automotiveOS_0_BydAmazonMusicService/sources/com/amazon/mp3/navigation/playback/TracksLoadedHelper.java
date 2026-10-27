package com.amazon.mp3.navigation.playback;

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
import com.amazon.mp3.util.Log;
import com.amazon.music.metrics.mts.event.types.DirectedPlayStatus;
import com.amazon.music.metrics.mts.event.types.PlaybackPageType;
import java.util.List;
import java.util.Random;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;
import kotlin.jvm.internal.Ref;

/* JADX INFO: compiled from: TracksLoadedHelper.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000V\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010 \n\u0002\b\u0002\u0018\u0000 \u001f2\u00020\u0001:\u0001\u001fB9\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\t\u0012\u0006\u0010\n\u001a\u00020\u000b\u0012\n\b\u0002\u0010\f\u001a\u0004\u0018\u00010\r¢\u0006\u0002\u0010\u000eJ:\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\u000b2\b\u0010\u0002\u001a\u0004\u0018\u00010\u00032\u0006\u0010\u0014\u001a\u00020\u00152\u0006\u0010\u0016\u001a\u00020\u00072\u0006\u0010\u0017\u001a\u00020\u0018H\u0002J\u0018\u0010\u0019\u001a\u00020\u00052\u0006\u0010\u001a\u001a\u00020\u00052\u0006\u0010\u001b\u001a\u00020\u0005H\u0002J\u0014\u0010\u001c\u001a\u00020\u00102\f\u0010\u001d\u001a\b\u0012\u0004\u0012\u00020\u00120\u001eR\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u000bX\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\f\u001a\u0004\u0018\u00010\rX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u0082\u0004¢\u0006\u0002\n\u0000¨\u0006 "}, d2 = {"Lcom/amazon/mp3/navigation/playback/TracksLoadedHelper;", "", "context", "Landroid/content/Context;", "listPosition", "", "shuffle", "", "startTime", "", "playbackMode", "Lcom/amazon/mp3/prime/cta/PrimeCollectionTask$Task;", "playbackPageType", "Lcom/amazon/music/metrics/mts/event/types/PlaybackPageType;", "(Landroid/content/Context;IZJLcom/amazon/mp3/prime/cta/PrimeCollectionTask$Task;Lcom/amazon/music/metrics/mts/event/types/PlaybackPageType;)V", "addPrimeTrackWithTask", "", "track", "Lcom/amazon/mp3/library/item/MusicTrack;", "task", "metricInformation", "Lcom/amazon/mp3/playback/service/metrics/PlaybackMetricInformation;", "refreshCollection", "callback", "Lcom/amazon/mp3/prime/cta/PrimeCollectionTask$OnUriReadyCallback;", "nextTrack", "index", "size", "onLoadedFinishedHelper", "result", "", "Companion", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class TracksLoadedHelper {
    private static final String TAG = TracksLoadedHelper.class.getSimpleName();
    private final Context context;
    private final int listPosition;
    private final PrimeCollectionTask.Task playbackMode;
    private final PlaybackPageType playbackPageType;
    private final boolean shuffle;
    private final long startTime;

    /* JADX INFO: Access modifiers changed from: private */
    public final int nextTrack(int index, int size) {
        int i = index + 1;
        if (i >= size) {
            return 0;
        }
        return i;
    }

    public TracksLoadedHelper(Context context, int i, boolean z, long j, PrimeCollectionTask.Task playbackMode, PlaybackPageType playbackPageType) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(playbackMode, "playbackMode");
        this.context = context;
        this.listPosition = i;
        this.shuffle = z;
        this.startTime = j;
        this.playbackMode = playbackMode;
        this.playbackPageType = playbackPageType;
    }

    public final void onLoadedFinishedHelper(final List<? extends MusicTrack> result) {
        int iNextInt;
        Intrinsics.checkNotNullParameter(result, "result");
        final PlaybackMetricInformation playbackMetricInformation = new PlaybackMetricInformation(DirectedPlayStatus.DIRECTED, this.playbackPageType);
        final int size = result.size();
        final Ref.IntRef intRef = new Ref.IntRef();
        if (this.shuffle) {
            iNextInt = new Random().nextInt(size);
        } else {
            iNextInt = this.listPosition;
        }
        intRef.element = iNextInt;
        final Ref.IntRef intRef2 = new Ref.IntRef();
        intRef2.element = intRef.element;
        final Ref.IntRef intRef3 = new Ref.IntRef();
        intRef3.element = Math.min(size, TracksPlaybackHelper.INSTANCE.getMaxQueueSize());
        new PrimeCollectionTask.OnUriReadyCallback() { // from class: com.amazon.mp3.navigation.playback.TracksLoadedHelper$onLoadedFinishedHelper$callback$1
            @Override // com.amazon.mp3.prime.cta.PrimeCollectionTask.OnUriReadyCallback
            public void onAlbumUriReady(Uri uri) {
                Ref.IntRef intRef4 = intRef3;
                intRef4.element--;
                next();
            }

            @Override // com.amazon.mp3.prime.cta.PrimeCollectionTask.OnUriReadyCallback
            public void onError(Exception e) {
                Intrinsics.checkNotNullParameter(e, "e");
                Log.error(TracksLoadedHelper.TAG, "start non-CQE callback failed", e);
                next();
            }

            private final void next() {
                if (intRef3.element < 0) {
                    PlayQueueSequencer.getInstance().setAddingItems(false);
                    return;
                }
                PlayQueueSequencer.getInstance().setAddingItems(true);
                int iNextTrack = this.nextTrack(intRef2.element, size);
                Ref.IntRef intRef4 = intRef;
                if (iNextTrack == intRef4.element) {
                    intRef3.element = -1;
                }
                boolean z = intRef3.element <= 0;
                int i = intRef2.element;
                if (i == intRef4.element) {
                    this.addPrimeTrackWithTask(result.get(i), this.playbackMode, this.context, playbackMetricInformation, !CastingUtil.isCastingToAlexa(), this);
                } else if (this.shuffle) {
                    this.addPrimeTrackWithTask(result.get(intRef2.element), PrimeCollectionTask.Task.ADD_TO_PLAYQUEUE_SHUFFLE_NO_NOTIFICATIONS, this.context, playbackMetricInformation, z, this);
                } else {
                    this.addPrimeTrackWithTask(result.get(intRef2.element), PrimeCollectionTask.Task.ADD_TO_PLAYQUEUE_NO_NOTIFICATIONS, this.context, playbackMetricInformation, z, this);
                }
                Ref.IntRef intRef5 = intRef2;
                intRef5.element = this.nextTrack(intRef5.element, size);
            }
        }.onAlbumUriReady(null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void addPrimeTrackWithTask(MusicTrack track, PrimeCollectionTask.Task task, Context context, PlaybackMetricInformation metricInformation, boolean refreshCollection, PrimeCollectionTask.OnUriReadyCallback callback) {
        PrimeTrackTask primeTrackTaskCreatePlaybackTask = PrimeTrackTask.createPlaybackTask(context, track.getAlbumAsin(), track.getAsin(), metricInformation, this.playbackPageType, task, new QuerySongByAsin(context), this.startTime, callback, refreshCollection);
        primeTrackTaskCreatePlaybackTask.setIsVideo(PrimePlaylistTracksTable.AssetType.VIDEO == track.getAssetType());
        primeTrackTaskCreatePlaybackTask.execute();
    }
}
