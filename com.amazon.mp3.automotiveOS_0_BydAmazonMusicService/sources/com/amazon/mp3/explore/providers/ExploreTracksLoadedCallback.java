package com.amazon.mp3.explore.providers;

import android.annotation.SuppressLint;
import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import androidx.loader.app.LoaderManager;
import androidx.loader.content.Loader;
import com.amazon.mp3.casting.CastingUtil;
import com.amazon.mp3.cloudqueue.NoOpCloudQueueClientHandler;
import com.amazon.mp3.library.fragment.PrimeBrowseSongsPlaybackLoader;
import com.amazon.mp3.library.item.MusicTrack;
import com.amazon.mp3.library.provider.MediaProvider;
import com.amazon.mp3.playback.service.metrics.PlaybackMetricInformation;
import com.amazon.mp3.playback.start.StartPlaybackUtil;
import com.amazon.mp3.prime.browse.metadata.PrimeTrack;
import com.amazon.mp3.prime.cta.PrimeCollectionTask;
import com.amazon.music.media.playback.ControlSource;
import com.amazon.music.media.playback.util.ThreadUtils;
import com.amazon.music.metrics.mts.event.types.DirectedPlayStatus;
import com.amazon.music.metrics.mts.event.types.PlaybackPageType;
import java.util.List;
import java.util.Random;
import kotlin.Metadata;
import kotlin.collections.CollectionsKt___CollectionsKt;
import kotlin.jvm.internal.DefaultConstructorMarker;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: ExploreTracksLoadedCallback.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(bv = {}, d1 = {"\u0000l\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\t\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0006\u0018\u0000 92\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00030\u00020\u0001:\u00019B5\u0012\u0006\u0010\u0014\u001a\u00020\u0013\u0012\f\u0010\u0019\u001a\b\u0012\u0004\u0012\u00020\u00180\u0002\u0012\u0006\u0010\u001d\u001a\u00020\n\u0012\u0006\u0010\"\u001a\u00020!\u0012\u0006\u0010'\u001a\u00020&¢\u0006\u0004\b7\u00108J\u001e\u0010\u0007\u001a\u00020\u00062\u0014\u0010\u0005\u001a\u0010\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00030\u0002\u0018\u00010\u0004H\u0002J\u0016\u0010\t\u001a\u00020\u00062\f\u0010\b\u001a\b\u0012\u0004\u0012\u00020\u00030\u0002H\u0002J&\u0010\u000e\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00030\u00020\u00042\u0006\u0010\u000b\u001a\u00020\n2\b\u0010\r\u001a\u0004\u0018\u00010\fH\u0016J,\u0010\u0010\u001a\u00020\u00062\u0012\u0010\u0005\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00030\u00020\u00042\u000e\u0010\u000f\u001a\n\u0012\u0004\u0012\u00020\u0003\u0018\u00010\u0002H\u0017J\u001c\u0010\u0012\u001a\u00020\u00062\u0012\u0010\u0011\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00030\u00020\u0004H\u0016R\u0017\u0010\u0014\u001a\u00020\u00138\u0006¢\u0006\f\n\u0004\b\u0014\u0010\u0015\u001a\u0004\b\u0016\u0010\u0017R\u001d\u0010\u0019\u001a\b\u0012\u0004\u0012\u00020\u00180\u00028\u0006¢\u0006\f\n\u0004\b\u0019\u0010\u001a\u001a\u0004\b\u001b\u0010\u001cR\u0017\u0010\u001d\u001a\u00020\n8\u0006¢\u0006\f\n\u0004\b\u001d\u0010\u001e\u001a\u0004\b\u001f\u0010 R\u0017\u0010\"\u001a\u00020!8\u0006¢\u0006\f\n\u0004\b\"\u0010#\u001a\u0004\b$\u0010%R\u0017\u0010'\u001a\u00020&8\u0006¢\u0006\f\n\u0004\b'\u0010(\u001a\u0004\b)\u0010*R\u0014\u0010,\u001a\u00020+8\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b,\u0010-R\u0016\u0010/\u001a\u00020.8\u0002@\u0002X\u0082\u000e¢\u0006\u0006\n\u0004\b/\u00100R\u0014\u00102\u001a\u0002018\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b2\u00103R\u0016\u00105\u001a\u0004\u0018\u0001048\u0002X\u0082\u0004¢\u0006\u0006\n\u0004\b5\u00106¨\u0006:"}, d2 = {"Lcom/amazon/mp3/explore/providers/ExploreTracksLoadedCallback;", "Landroidx/loader/app/LoaderManager$LoaderCallbacks;", "", "Lcom/amazon/mp3/library/item/MusicTrack;", "Landroidx/loader/content/Loader;", "loader", "", "destroyLoader", "result", "onLoadedFinishedLegacy", "", "i", "Landroid/os/Bundle;", "bundle", "onCreateLoader", "trackList", "onLoadFinished", "listLoader", "onLoaderReset", "Landroid/content/Context;", "context", "Landroid/content/Context;", "getContext", "()Landroid/content/Context;", "Lcom/amazon/mp3/prime/browse/metadata/PrimeTrack;", "tracks", "Ljava/util/List;", "getTracks", "()Ljava/util/List;", "listPosition", "I", "getListPosition", "()I", "Lcom/amazon/music/metrics/mts/event/types/PlaybackPageType;", "playbackPageType", "Lcom/amazon/music/metrics/mts/event/types/PlaybackPageType;", "getPlaybackPageType", "()Lcom/amazon/music/metrics/mts/event/types/PlaybackPageType;", "", "startTime", "J", "getStartTime", "()J", "", "shuffle", "Z", "Lcom/amazon/mp3/prime/cta/PrimeCollectionTask$Task;", "playbackMode", "Lcom/amazon/mp3/prime/cta/PrimeCollectionTask$Task;", "Ljava/util/Random;", "random", "Ljava/util/Random;", "", "queueTitle", "Ljava/lang/String;", "<init>", "(Landroid/content/Context;Ljava/util/List;ILcom/amazon/music/metrics/mts/event/types/PlaybackPageType;J)V", "Companion", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0})
public final class ExploreTracksLoadedCallback implements LoaderManager.LoaderCallbacks<List<? extends MusicTrack>> {

    /* JADX INFO: renamed from: Companion, reason: from kotlin metadata */
    public static final Companion INSTANCE = new Companion(null);
    private static final String TAG = ExploreTracksLoadedCallback.class.getSimpleName();
    private final Context context;
    private final int listPosition;
    private PrimeCollectionTask.Task playbackMode;
    private final PlaybackPageType playbackPageType;
    private final String queueTitle;
    private final Random random;
    private final boolean shuffle;
    private final long startTime;
    private final List<PrimeTrack> tracks;

    @Override // androidx.loader.app.LoaderManager.LoaderCallbacks
    public void onLoaderReset(Loader<List<? extends MusicTrack>> listLoader) {
        Intrinsics.checkNotNullParameter(listLoader, "listLoader");
    }

    /* JADX WARN: Multi-variable type inference failed */
    public ExploreTracksLoadedCallback(Context context, List<? extends PrimeTrack> tracks, int i, PlaybackPageType playbackPageType, long j) {
        Intrinsics.checkNotNullParameter(context, "context");
        Intrinsics.checkNotNullParameter(tracks, "tracks");
        Intrinsics.checkNotNullParameter(playbackPageType, "playbackPageType");
        this.context = context;
        this.tracks = tracks;
        this.listPosition = i;
        this.playbackPageType = playbackPageType;
        this.startTime = j;
        this.playbackMode = PrimeCollectionTask.Task.PLAYBACK_FULLSCREEN;
        this.random = new Random();
        this.shuffle = PrimeCollectionTask.Task.PLAYBACK_SHUFFLE == this.playbackMode;
    }

    @Override // androidx.loader.app.LoaderManager.LoaderCallbacks
    public /* bridge */ /* synthetic */ void onLoadFinished(Loader<List<? extends MusicTrack>> loader, List<? extends MusicTrack> list) {
        onLoadFinished2((Loader<List<MusicTrack>>) loader, list);
    }

    public final List<PrimeTrack> getTracks() {
        return this.tracks;
    }

    public final int getListPosition() {
        return this.listPosition;
    }

    @Override // androidx.loader.app.LoaderManager.LoaderCallbacks
    public Loader<List<? extends MusicTrack>> onCreateLoader(int i, Bundle bundle) {
        return new PrimeBrowseSongsPlaybackLoader(this.context, new PrimeBrowseSongsPlaybackLoader.SongsCollection() { // from class: com.amazon.mp3.explore.providers.ExploreTracksLoadedCallback$onCreateLoader$songsCollection$1
            @Override // com.amazon.mp3.library.fragment.PrimeBrowseSongsPlaybackLoader.SongsCollection
            public int getCount() {
                return this.this$0.getTracks().size();
            }

            @Override // com.amazon.mp3.library.fragment.PrimeBrowseSongsPlaybackLoader.SongsCollection
            public PrimeTrack getItem(int position) {
                return (PrimeTrack) CollectionsKt___CollectionsKt.getOrNull(this.this$0.getTracks(), position);
            }
        });
    }

    @SuppressLint({"CheckResult"})
    /* JADX INFO: renamed from: onLoadFinished, reason: avoid collision after fix types in other method */
    public void onLoadFinished2(Loader<List<MusicTrack>> loader, final List<? extends MusicTrack> trackList) {
        Intrinsics.checkNotNullParameter(loader, "loader");
        destroyLoader(loader);
        if (trackList == null) {
            return;
        }
        Uri contentUri = MediaProvider.PrimeBrowseTracksCollection.FirstTrack.getContentUri(MediaProvider.PrimeBrowseTracksCollection.ContentSource.RECOMMENDED, trackList.get(this.listPosition).getAsin());
        Context context = this.context;
        Intrinsics.checkNotNullExpressionValue(contentUri, "contentUri");
        String str = this.queueTitle;
        if (str == null) {
            str = "";
        }
        int i = this.listPosition;
        boolean z = this.playbackMode == PrimeCollectionTask.Task.PLAYBACK_FULLSCREEN;
        PlaybackPageType playbackPageType = this.playbackPageType;
        ControlSource APP_UI = ControlSource.APP_UI;
        Intrinsics.checkNotNullExpressionValue(APP_UI, "APP_UI");
        StartPlaybackUtil.startMusicTracksPlayback(context, contentUri, str, trackList, (14336 & 16) != 0 ? 0 : i, (14336 & 32) != 0 ? false : false, (14336 & 64) != 0 ? true : z, playbackPageType, (14336 & 256) != 0 ? false : false, APP_UI, (14336 & 1024) != 0 ? null : null, (14336 & 2048) != 0 ? false : false, (14336 & 4096) != 0 ? null : null, (14336 & 8192) != 0 ? null : null, true, new NoOpCloudQueueClientHandler() { // from class: com.amazon.mp3.explore.providers.ExploreTracksLoadedCallback.onLoadFinished.1
            @Override // com.amazon.mp3.cloudqueue.NoOpCloudQueueClientHandler
            public void handlePlayback() {
                ExploreTracksLoadedCallback.this.onLoadedFinishedLegacy(trackList);
            }
        });
    }

    private final void destroyLoader(final Loader<List<MusicTrack>> loader) {
        if (loader == null) {
            return;
        }
        ThreadUtils.postOnMainThread(new Runnable() { // from class: com.amazon.mp3.explore.providers.ExploreTracksLoadedCallback$$ExternalSyntheticLambda0
            @Override // java.lang.Runnable
            public final void run() {
                ExploreTracksLoadedCallback.m987destroyLoader$lambda0(loader);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: destroyLoader$lambda-0, reason: not valid java name */
    public static final void m987destroyLoader$lambda0(Loader loader) {
        Intrinsics.checkNotNullParameter(loader, "$loader");
        loader.stopLoading();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public final void onLoadedFinishedLegacy(List<? extends MusicTrack> result) {
        int iNextInt;
        PlaybackMetricInformation playbackMetricInformation = new PlaybackMetricInformation(DirectedPlayStatus.DIRECTED, this.playbackPageType);
        if (this.shuffle) {
            iNextInt = this.random.nextInt(result.size());
        } else {
            iNextInt = this.listPosition;
        }
        new ExplorerOnUriReadyCallback(result, Math.min(result.size(), INSTANCE.getMaxQueueSize()), iNextInt, this.playbackPageType, this.startTime, this.context, this.playbackMode, playbackMetricInformation, this.shuffle).onAlbumUriReady(null);
    }

    /* JADX INFO: compiled from: ExploreTracksLoadedCallback.kt */
    @Metadata(d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0005\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002¢\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0082T¢\u0006\u0002\n\u0000R\u0016\u0010\u0006\u001a\n \b*\u0004\u0018\u00010\u00070\u0007X\u0082\u0004¢\u0006\u0002\n\u0000R\u0011\u0010\t\u001a\u00020\u00048F¢\u0006\u0006\u001a\u0004\b\n\u0010\u000b¨\u0006\f"}, d2 = {"Lcom/amazon/mp3/explore/providers/ExploreTracksLoadedCallback$Companion;", "", "()V", "MAX_QUEUE_SIZE_CASTING", "", "MAX_QUEUE_SIZE_LOCAL", "TAG", "", "kotlin.jvm.PlatformType", "maxQueueSize", "getMaxQueueSize", "()I", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0}, xi = 48)
    public static final class Companion {
        public /* synthetic */ Companion(DefaultConstructorMarker defaultConstructorMarker) {
            this();
        }

        private Companion() {
        }

        public final int getMaxQueueSize() {
            return CastingUtil.isCastingToAlexa() ? 5 : 50;
        }
    }
}
