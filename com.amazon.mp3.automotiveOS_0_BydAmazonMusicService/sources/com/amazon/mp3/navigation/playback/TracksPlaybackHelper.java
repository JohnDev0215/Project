package com.amazon.mp3.navigation.playback;

import android.content.Context;
import android.net.Uri;
import androidx.annotation.WorkerThread;
import com.amazon.mp3.casting.CastingUtil;
import com.amazon.mp3.cloudqueue.NoOpCloudQueueClientHandler;
import com.amazon.mp3.external.api.uri.QuerySongByAsin;
import com.amazon.mp3.library.fragment.PrimeBrowseSongsPlaybackLoader;
import com.amazon.mp3.library.item.MusicTrack;
import com.amazon.mp3.library.provider.MediaProvider;
import com.amazon.mp3.playback.PlaybackError;
import com.amazon.mp3.playback.PlaybackListener;
import com.amazon.mp3.playback.start.StartPlaybackUtil;
import com.amazon.mp3.prime.TrackRequester;
import com.amazon.mp3.prime.browse.metadata.PrimeTrack;
import com.amazon.mp3.prime.cta.PrimeCollectionTask;
import com.amazon.mp3.util.Log;
import com.amazon.music.media.playback.ControlSource;
import com.amazon.music.media.playback.util.Clock;
import com.amazon.music.metrics.mts.event.types.PlaybackPageType;
import java.util.List;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt__CollectionsJVMKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: TracksPlaybackHelper.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(d1 = {"\u0000T\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0005\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u0000 \u001f2\u00020\u0001:\u0001\u001fB?\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\b\b\u0002\u0010\b\u001a\u00020\t\u0012\n\b\u0002\u0010\n\u001a\u0004\u0018\u00010\u000b\u0012\b\b\u0002\u0010\f\u001a\u00020\r¢\u0006\u0002\u0010\u000eJ\u0010\u0010\u0012\u001a\u00020\u00132\u0006\u0010\u0014\u001a\u00020\u0015H\u0007J,\u0010\u0012\u001a\u00020\u00162\f\u0010\u0017\u001a\b\u0012\u0004\u0012\u00020\u00190\u00182\b\b\u0002\u0010\u001a\u001a\u00020\u00132\n\b\u0002\u0010\u001b\u001a\u0004\u0018\u00010\u0015H\u0007J*\u0010\u001c\u001a\u00020\u00162\f\u0010\u001d\u001a\b\u0012\u0004\u0012\u00020\u001e0\u00182\n\b\u0002\u0010\u001b\u001a\u0004\u0018\u00010\u00152\b\b\u0002\u0010\u001a\u001a\u00020\u0013R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\rX\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\n\u001a\u0004\u0018\u00010\u000bX\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004¢\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u0011\u0010\u000f\u001a\u00020\r¢\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0011¨\u0006 "}, d2 = {"Lcom/amazon/mp3/navigation/playback/TracksPlaybackHelper;", "", "context", "Landroid/content/Context;", "playbackMode", "Lcom/amazon/mp3/prime/cta/PrimeCollectionTask$Task;", "playbackPageType", "Lcom/amazon/music/metrics/mts/event/types/PlaybackPageType;", "controlSource", "Lcom/amazon/music/media/playback/ControlSource;", "playbackListener", "Lcom/amazon/mp3/playback/PlaybackListener;", "isUserInitiated", "", "(Landroid/content/Context;Lcom/amazon/mp3/prime/cta/PrimeCollectionTask$Task;Lcom/amazon/music/metrics/mts/event/types/PlaybackPageType;Lcom/amazon/music/media/playback/ControlSource;Lcom/amazon/mp3/playback/PlaybackListener;Z)V", "shuffle", "getShuffle", "()Z", "loadPlayback", "", "asin", "", "", "tracks", "", "Lcom/amazon/mp3/prime/browse/metadata/PrimeTrack;", "startingPosition", "queueTitle", "startPlayback", "result", "Lcom/amazon/mp3/library/item/MusicTrack;", "Companion", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
public final class TracksPlaybackHelper {

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static final String TAG = TracksPlaybackHelper.class.getSimpleName();
    private static int maxQueueSize;
    private final Context context;
    private final ControlSource controlSource;
    private final boolean isUserInitiated;
    private final PlaybackListener playbackListener;
    private final PrimeCollectionTask.Task playbackMode;
    private final PlaybackPageType playbackPageType;
    private final boolean shuffle;

    public TracksPlaybackHelper(Context context, PrimeCollectionTask.Task playbackMode, PlaybackPageType playbackPageType, ControlSource controlSource, PlaybackListener playbackListener, boolean z) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(playbackMode, "playbackMode");
        Intrinsics.checkNotNullParameter(playbackPageType, "playbackPageType");
        Intrinsics.checkNotNullParameter(controlSource, "controlSource");
        this.context = context;
        this.playbackMode = playbackMode;
        this.playbackPageType = playbackPageType;
        this.controlSource = controlSource;
        this.playbackListener = playbackListener;
        this.isUserInitiated = z;
        this.shuffle = PrimeCollectionTask.Task.PLAYBACK_SHUFFLE == playbackMode;
    }

    /* JADX WARN: Illegal instructions before constructor call */
    public /* synthetic */ TracksPlaybackHelper(Context context, PrimeCollectionTask.Task task, PlaybackPageType playbackPageType, ControlSource APP_UI, PlaybackListener playbackListener, boolean z, int i, DefaultConstructorMarker defaultConstructorMarker) {
        PrimeCollectionTask.Task task2 = (i & 2) != 0 ? PrimeCollectionTask.Task.PLAYBACK_FULLSCREEN : task;
        if ((i & 8) != 0) {
            APP_UI = ControlSource.APP_UI;
            Intrinsics.checkNotNullExpressionValue(APP_UI, "APP_UI");
        }
        this(context, task2, playbackPageType, APP_UI, (i & 16) != 0 ? null : playbackListener, (i & 32) != 0 ? true : z);
    }

    /* JADX INFO: compiled from: TracksPlaybackHelper.kt */
    @Metadata(d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0006\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u0016\u0010\u0006\u001a\n \b*\u0004\u0018\u00010\u00070\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u001e\u0010\n\u001a\u00020\u00042\u0006\u0010\t\u001a\u00020\u0004@BX\u0086\u000e¢\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\f¨\u0006\r"}, d2 = {"Lcom/amazon/mp3/navigation/playback/TracksPlaybackHelper$Companion;", "", "()V", "MAX_QUEUE_SIZE_CASTING", "", "MAX_QUEUE_SIZE_LOCAL", "TAG", "", "kotlin.jvm.PlatformType", "<set-?>", "maxQueueSize", "getMaxQueueSize", "()I", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final int getMaxQueueSize() {
            return TracksPlaybackHelper.maxQueueSize;
        }
    }

    static {
        maxQueueSize = CastingUtil.isCastingToAlexa() ? 5 : 50;
    }

    public final boolean getShuffle() {
        return this.shuffle;
    }

    public static /* synthetic */ void loadPlayback$default(TracksPlaybackHelper tracksPlaybackHelper, List list, int i, String str, int i2, Object obj) {
        if ((i2 & 2) != 0) {
            i = 0;
        }
        if ((i2 & 4) != 0) {
            str = null;
        }
        tracksPlaybackHelper.loadPlayback(list, i, str);
    }

    @WorkerThread
    public final void loadPlayback(final List<? extends PrimeTrack> tracks, int startingPosition, String queueTitle) {
        Intrinsics.checkNotNullParameter(tracks, "tracks");
        List<MusicTrack> listLoadInBackground = new PrimeBrowseSongsPlaybackLoader(this.context, new PrimeBrowseSongsPlaybackLoader.SongsCollection() { // from class: com.amazon.mp3.navigation.playback.TracksPlaybackHelper$loadPlayback$songsCollection$1
            @Override // com.amazon.mp3.library.fragment.PrimeBrowseSongsPlaybackLoader.SongsCollection
            public int getCount() {
                return tracks.size();
            }

            @Override // com.amazon.mp3.library.fragment.PrimeBrowseSongsPlaybackLoader.SongsCollection
            public PrimeTrack getItem(int position) {
                return tracks.get(position);
            }
        }).loadInBackground();
        if (listLoadInBackground == null) {
            return;
        }
        startPlayback(listLoadInBackground, queueTitle, startingPosition);
    }

    @WorkerThread
    public final int loadPlayback(String asin) {
        PlaybackListener playbackListener;
        PlaybackListener playbackListener2;
        Intrinsics.checkNotNullParameter(asin, "asin");
        try {
            MusicTrack trackFromAsin = new TrackRequester(this.context).getTrackFromAsin(asin);
            if (trackFromAsin == null && (playbackListener2 = this.playbackListener) != null) {
                PlaybackListener.DefaultImpls.onPlaybackFailure$default(playbackListener2, PlaybackError.SERVICE_REQUEST_ERROR, null, 2, null);
            }
            Uri uriFindCollectionUriByAsin = new QuerySongByAsin(this.context).findCollectionUriByAsin(asin);
            if (uriFindCollectionUriByAsin == null && (playbackListener = this.playbackListener) != null) {
                PlaybackListener.DefaultImpls.onPlaybackFailure$default(playbackListener, PlaybackError.DATABASE_NO_CONTENT, null, 2, null);
            }
            if (trackFromAsin != null && uriFindCollectionUriByAsin != null) {
                startPlayback$default(this, CollectionsKt__CollectionsJVMKt.listOf(trackFromAsin), trackFromAsin.getTitle(), 0, 4, null);
                return 1;
            }
            Log.error(TAG, Intrinsics.stringPlus("error on fetching track with asin ", asin));
            return 3;
        } catch (Exception e) {
            Log.error(TAG, "Fail to execute play task", e);
            return 3;
        }
    }

    public static /* synthetic */ void startPlayback$default(TracksPlaybackHelper tracksPlaybackHelper, List list, String str, int i, int i2, Object obj) {
        if ((i2 & 2) != 0) {
            str = null;
        }
        if ((i2 & 4) != 0) {
            i = 0;
        }
        tracksPlaybackHelper.startPlayback(list, str, i);
    }

    public final void startPlayback(final List<? extends MusicTrack> result, String queueTitle, final int startingPosition) {
        Intrinsics.checkNotNullParameter(result, "result");
        Uri contentUri = MediaProvider.PrimeBrowseTracksCollection.FirstTrack.getContentUri(MediaProvider.PrimeBrowseTracksCollection.ContentSource.RECOMMENDED, result.get(startingPosition).getAsin());
        Context context = this.context;
        Intrinsics.checkNotNullExpressionValue(contentUri, "contentUri");
        boolean z = this.shuffle;
        StartPlaybackUtil.startMusicTracksPlayback(context, contentUri, queueTitle, result, (14336 & 16) != 0 ? 0 : startingPosition, (14336 & 32) != 0 ? false : z, (14336 & 64) != 0 ? true : this.playbackMode == PrimeCollectionTask.Task.PLAYBACK_FULLSCREEN, this.playbackPageType, (14336 & 256) != 0 ? false : true, this.controlSource, (14336 & 1024) != 0 ? null : null, (14336 & 2048) != 0 ? false : false, (14336 & 4096) != 0 ? null : this.playbackListener, (14336 & 8192) != 0 ? null : null, this.isUserInitiated, new NoOpCloudQueueClientHandler() { // from class: com.amazon.mp3.navigation.playback.TracksPlaybackHelper.startPlayback.1
            @Override // com.amazon.mp3.cloudqueue.NoOpCloudQueueClientHandler
            public void handlePlayback() {
                new TracksLoadedHelper(TracksPlaybackHelper.this.context, startingPosition, TracksPlaybackHelper.this.getShuffle(), Clock.now(), TracksPlaybackHelper.this.playbackMode, TracksPlaybackHelper.this.playbackPageType).onLoadedFinishedHelper(result);
            }
        });
    }
}
