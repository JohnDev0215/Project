package com.google.android.exoplayer2.source.hls.playlist;

import android.net.Uri;
import android.os.Handler;
import android.os.SystemClock;
import com.google.android.exoplayer2.C;
import com.google.android.exoplayer2.ParserException;
import com.google.android.exoplayer2.source.AdaptiveMediaSourceEventListener;
import com.google.android.exoplayer2.source.chunk.ChunkedTrackBlacklistUtil;
import com.google.android.exoplayer2.source.hls.HlsDataSourceFactory;
import com.google.android.exoplayer2.source.hls.playlist.HlsMasterPlaylist;
import com.google.android.exoplayer2.source.hls.playlist.HlsMediaPlaylist;
import com.google.android.exoplayer2.upstream.Loader;
import com.google.android.exoplayer2.upstream.ParsingLoadable;
import com.google.android.exoplayer2.util.UriUtil;
import java.io.IOException;
import java.util.ArrayList;
import java.util.IdentityHashMap;
import java.util.Iterator;
import java.util.List;

/* JADX INFO: loaded from: classes6.dex */
public final class HlsPlaylistTracker implements Loader.Callback<ParsingLoadable<HlsPlaylist>> {
    private final HlsDataSourceFactory dataSourceFactory;
    private final AdaptiveMediaSourceEventListener.EventDispatcher eventDispatcher;
    private final Uri initialPlaylistUri;
    private boolean isLive;
    private HlsMasterPlaylist masterPlaylist;
    private final int minRetryCount;
    private final ParsingLoadable.Parser<HlsPlaylist> playlistParser;
    private HlsMasterPlaylist.HlsUrl primaryHlsUrl;
    private final PrimaryPlaylistListener primaryPlaylistListener;
    private HlsMediaPlaylist primaryUrlSnapshot;
    private final List<PlaylistEventListener> listeners = new ArrayList();
    private final Loader initialPlaylistLoader = new Loader("HlsPlaylistTracker:MasterPlaylist");
    private final IdentityHashMap<HlsMasterPlaylist.HlsUrl, MediaPlaylistBundle> playlistBundles = new IdentityHashMap<>();
    private final Handler playlistRefreshHandler = new Handler();

    public interface PlaylistEventListener {
        void onPlaylistBlacklisted(HlsMasterPlaylist.HlsUrl hlsUrl, long j);

        void onPlaylistChanged();
    }

    public interface PrimaryPlaylistListener {
        void onPrimaryPlaylistRefreshed(HlsMediaPlaylist hlsMediaPlaylist);
    }

    public static final class PlaylistStuckException extends IOException {
        public final String url;

        private PlaylistStuckException(String str) {
            this.url = str;
        }
    }

    public static final class PlaylistResetException extends IOException {
        public final String url;

        private PlaylistResetException(String str) {
            this.url = str;
        }
    }

    public HlsPlaylistTracker(Uri uri, HlsDataSourceFactory hlsDataSourceFactory, AdaptiveMediaSourceEventListener.EventDispatcher eventDispatcher, int i, PrimaryPlaylistListener primaryPlaylistListener, ParsingLoadable.Parser<HlsPlaylist> parser) {
        this.initialPlaylistUri = uri;
        this.dataSourceFactory = hlsDataSourceFactory;
        this.eventDispatcher = eventDispatcher;
        this.minRetryCount = i;
        this.primaryPlaylistListener = primaryPlaylistListener;
        this.playlistParser = parser;
    }

    public void addListener(PlaylistEventListener playlistEventListener) {
        this.listeners.add(playlistEventListener);
    }

    public void removeListener(PlaylistEventListener playlistEventListener) {
        this.listeners.remove(playlistEventListener);
    }

    public void start() {
        this.initialPlaylistLoader.startLoading(new ParsingLoadable(this.dataSourceFactory.createDataSource(4), this.initialPlaylistUri, 4, this.playlistParser), this, this.minRetryCount);
    }

    public HlsMasterPlaylist getMasterPlaylist() {
        return this.masterPlaylist;
    }

    public HlsMediaPlaylist getPlaylistSnapshot(HlsMasterPlaylist.HlsUrl hlsUrl) {
        HlsMediaPlaylist playlistSnapshot = this.playlistBundles.get(hlsUrl).getPlaylistSnapshot();
        if (playlistSnapshot != null) {
            maybeSetPrimaryUrl(hlsUrl);
        }
        return playlistSnapshot;
    }

    public boolean isSnapshotValid(HlsMasterPlaylist.HlsUrl hlsUrl) {
        return this.playlistBundles.get(hlsUrl).isSnapshotValid();
    }

    public void release() {
        this.initialPlaylistLoader.release();
        Iterator<MediaPlaylistBundle> it = this.playlistBundles.values().iterator();
        while (it.hasNext()) {
            it.next().release();
        }
        this.playlistRefreshHandler.removeCallbacksAndMessages(null);
        this.playlistBundles.clear();
    }

    public void maybeThrowPrimaryPlaylistRefreshError() throws IOException {
        this.initialPlaylistLoader.maybeThrowError();
        HlsMasterPlaylist.HlsUrl hlsUrl = this.primaryHlsUrl;
        if (hlsUrl != null) {
            maybeThrowPlaylistRefreshError(hlsUrl);
        }
    }

    public void maybeThrowPlaylistRefreshError(HlsMasterPlaylist.HlsUrl hlsUrl) throws IOException {
        this.playlistBundles.get(hlsUrl).maybeThrowPlaylistRefreshError();
    }

    public void refreshPlaylist(HlsMasterPlaylist.HlsUrl hlsUrl) {
        this.playlistBundles.get(hlsUrl).loadPlaylist();
    }

    public boolean isLive() {
        return this.isLive;
    }

    @Override // com.google.android.exoplayer2.upstream.Loader.Callback
    public void onLoadCompleted(ParsingLoadable<HlsPlaylist> parsingLoadable, long j, long j2) {
        HlsMasterPlaylist hlsMasterPlaylistCreateSingleVariantMasterPlaylist;
        HlsPlaylist result = parsingLoadable.getResult();
        boolean z = result instanceof HlsMediaPlaylist;
        if (z) {
            hlsMasterPlaylistCreateSingleVariantMasterPlaylist = HlsMasterPlaylist.createSingleVariantMasterPlaylist(result.baseUri);
        } else {
            hlsMasterPlaylistCreateSingleVariantMasterPlaylist = (HlsMasterPlaylist) result;
        }
        this.masterPlaylist = hlsMasterPlaylistCreateSingleVariantMasterPlaylist;
        this.primaryHlsUrl = hlsMasterPlaylistCreateSingleVariantMasterPlaylist.variants.get(0);
        ArrayList arrayList = new ArrayList();
        arrayList.addAll(hlsMasterPlaylistCreateSingleVariantMasterPlaylist.variants);
        arrayList.addAll(hlsMasterPlaylistCreateSingleVariantMasterPlaylist.audios);
        arrayList.addAll(hlsMasterPlaylistCreateSingleVariantMasterPlaylist.subtitles);
        createBundles(arrayList);
        MediaPlaylistBundle mediaPlaylistBundle = this.playlistBundles.get(this.primaryHlsUrl);
        if (!z) {
            mediaPlaylistBundle.loadPlaylist();
        } else {
            mediaPlaylistBundle.processLoadedPlaylist((HlsMediaPlaylist) result);
        }
        this.eventDispatcher.loadCompleted(parsingLoadable.dataSpec, 4, j, j2, parsingLoadable.bytesLoaded());
    }

    @Override // com.google.android.exoplayer2.upstream.Loader.Callback
    public void onLoadCanceled(ParsingLoadable<HlsPlaylist> parsingLoadable, long j, long j2, boolean z) {
        this.eventDispatcher.loadCanceled(parsingLoadable.dataSpec, 4, j, j2, parsingLoadable.bytesLoaded());
    }

    @Override // com.google.android.exoplayer2.upstream.Loader.Callback
    public int onLoadError(ParsingLoadable<HlsPlaylist> parsingLoadable, long j, long j2, IOException iOException) {
        boolean z = iOException instanceof ParserException;
        this.eventDispatcher.loadError(parsingLoadable.dataSpec, 4, j, j2, parsingLoadable.bytesLoaded(), iOException, z);
        return z ? 3 : 0;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean maybeSelectNewPrimaryUrl() {
        List<HlsMasterPlaylist.HlsUrl> list = this.masterPlaylist.variants;
        int size = list.size();
        long jElapsedRealtime = SystemClock.elapsedRealtime();
        for (int i = 0; i < size; i++) {
            MediaPlaylistBundle mediaPlaylistBundle = this.playlistBundles.get(list.get(i));
            if (jElapsedRealtime > mediaPlaylistBundle.blacklistUntilMs) {
                this.primaryHlsUrl = mediaPlaylistBundle.playlistUrl;
                mediaPlaylistBundle.loadPlaylist();
                return true;
            }
        }
        return false;
    }

    private void maybeSetPrimaryUrl(HlsMasterPlaylist.HlsUrl hlsUrl) {
        if (hlsUrl == this.primaryHlsUrl || !this.masterPlaylist.variants.contains(hlsUrl)) {
            return;
        }
        HlsMediaPlaylist hlsMediaPlaylist = this.primaryUrlSnapshot;
        if (hlsMediaPlaylist == null || !hlsMediaPlaylist.hasEndTag) {
            this.primaryHlsUrl = hlsUrl;
            this.playlistBundles.get(hlsUrl).loadPlaylist();
        }
    }

    private void createBundles(List<HlsMasterPlaylist.HlsUrl> list) {
        int size = list.size();
        for (int i = 0; i < size; i++) {
            HlsMasterPlaylist.HlsUrl hlsUrl = list.get(i);
            this.playlistBundles.put(hlsUrl, new MediaPlaylistBundle(hlsUrl));
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void onPlaylistUpdated(HlsMasterPlaylist.HlsUrl hlsUrl, HlsMediaPlaylist hlsMediaPlaylist) {
        if (hlsUrl == this.primaryHlsUrl) {
            if (this.primaryUrlSnapshot == null) {
                this.isLive = !hlsMediaPlaylist.hasEndTag;
            }
            this.primaryUrlSnapshot = hlsMediaPlaylist;
            this.primaryPlaylistListener.onPrimaryPlaylistRefreshed(hlsMediaPlaylist);
        }
        int size = this.listeners.size();
        for (int i = 0; i < size; i++) {
            this.listeners.get(i).onPlaylistChanged();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void notifyPlaylistBlacklisting(HlsMasterPlaylist.HlsUrl hlsUrl, long j) {
        int size = this.listeners.size();
        for (int i = 0; i < size; i++) {
            this.listeners.get(i).onPlaylistBlacklisted(hlsUrl, j);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public HlsMediaPlaylist getLatestPlaylistSnapshot(HlsMediaPlaylist hlsMediaPlaylist, HlsMediaPlaylist hlsMediaPlaylist2) {
        if (hlsMediaPlaylist2.isNewerThan(hlsMediaPlaylist)) {
            return hlsMediaPlaylist2.copyWith(getLoadedPlaylistStartTimeUs(hlsMediaPlaylist, hlsMediaPlaylist2), getLoadedPlaylistDiscontinuitySequence(hlsMediaPlaylist, hlsMediaPlaylist2));
        }
        return hlsMediaPlaylist2.hasEndTag ? hlsMediaPlaylist.copyWithEndTag() : hlsMediaPlaylist;
    }

    private long getLoadedPlaylistStartTimeUs(HlsMediaPlaylist hlsMediaPlaylist, HlsMediaPlaylist hlsMediaPlaylist2) {
        if (hlsMediaPlaylist2.hasProgramDateTime) {
            return hlsMediaPlaylist2.startTimeUs;
        }
        HlsMediaPlaylist hlsMediaPlaylist3 = this.primaryUrlSnapshot;
        long j = hlsMediaPlaylist3 != null ? hlsMediaPlaylist3.startTimeUs : 0L;
        if (hlsMediaPlaylist == null) {
            return j;
        }
        int size = hlsMediaPlaylist.segments.size();
        HlsMediaPlaylist.Segment firstOldOverlappingSegment = getFirstOldOverlappingSegment(hlsMediaPlaylist, hlsMediaPlaylist2);
        if (firstOldOverlappingSegment != null) {
            return hlsMediaPlaylist.startTimeUs + firstOldOverlappingSegment.relativeStartTimeUs;
        }
        return size == hlsMediaPlaylist2.mediaSequence - hlsMediaPlaylist.mediaSequence ? hlsMediaPlaylist.getEndTimeUs() : j;
    }

    private int getLoadedPlaylistDiscontinuitySequence(HlsMediaPlaylist hlsMediaPlaylist, HlsMediaPlaylist hlsMediaPlaylist2) {
        HlsMediaPlaylist.Segment firstOldOverlappingSegment;
        if (hlsMediaPlaylist2.hasDiscontinuitySequence) {
            return hlsMediaPlaylist2.discontinuitySequence;
        }
        HlsMediaPlaylist hlsMediaPlaylist3 = this.primaryUrlSnapshot;
        int i = hlsMediaPlaylist3 != null ? hlsMediaPlaylist3.discontinuitySequence : 0;
        return (hlsMediaPlaylist == null || (firstOldOverlappingSegment = getFirstOldOverlappingSegment(hlsMediaPlaylist, hlsMediaPlaylist2)) == null) ? i : (hlsMediaPlaylist.discontinuitySequence + firstOldOverlappingSegment.relativeDiscontinuitySequence) - hlsMediaPlaylist2.segments.get(0).relativeDiscontinuitySequence;
    }

    private static HlsMediaPlaylist.Segment getFirstOldOverlappingSegment(HlsMediaPlaylist hlsMediaPlaylist, HlsMediaPlaylist hlsMediaPlaylist2) {
        int i = hlsMediaPlaylist2.mediaSequence - hlsMediaPlaylist.mediaSequence;
        List<HlsMediaPlaylist.Segment> list = hlsMediaPlaylist.segments;
        if (i < list.size()) {
            return list.get(i);
        }
        return null;
    }

    private final class MediaPlaylistBundle implements Loader.Callback<ParsingLoadable<HlsPlaylist>>, Runnable {
        private long blacklistUntilMs;
        private long earliestNextLoadTimeMs;
        private long lastSnapshotChangeMs;
        private long lastSnapshotLoadMs;
        private boolean loadPending;
        private final ParsingLoadable<HlsPlaylist> mediaPlaylistLoadable;
        private final Loader mediaPlaylistLoader = new Loader("HlsPlaylistTracker:MediaPlaylist");
        private IOException playlistError;
        private HlsMediaPlaylist playlistSnapshot;
        private final HlsMasterPlaylist.HlsUrl playlistUrl;

        public MediaPlaylistBundle(HlsMasterPlaylist.HlsUrl hlsUrl) {
            this.playlistUrl = hlsUrl;
            this.mediaPlaylistLoadable = new ParsingLoadable<>(HlsPlaylistTracker.this.dataSourceFactory.createDataSource(4), UriUtil.resolveToUri(HlsPlaylistTracker.this.masterPlaylist.baseUri, hlsUrl.url), 4, HlsPlaylistTracker.this.playlistParser);
        }

        public HlsMediaPlaylist getPlaylistSnapshot() {
            return this.playlistSnapshot;
        }

        public boolean isSnapshotValid() {
            int i;
            if (this.playlistSnapshot == null) {
                return false;
            }
            long jElapsedRealtime = SystemClock.elapsedRealtime();
            long jMax = Math.max(30000L, C.usToMs(this.playlistSnapshot.durationUs));
            HlsMediaPlaylist hlsMediaPlaylist = this.playlistSnapshot;
            return hlsMediaPlaylist.hasEndTag || (i = hlsMediaPlaylist.playlistType) == 2 || i == 1 || this.lastSnapshotLoadMs + jMax > jElapsedRealtime;
        }

        public void release() {
            this.mediaPlaylistLoader.release();
        }

        public void loadPlaylist() {
            this.blacklistUntilMs = 0L;
            if (this.loadPending || this.mediaPlaylistLoader.isLoading()) {
                return;
            }
            long jElapsedRealtime = SystemClock.elapsedRealtime();
            if (jElapsedRealtime < this.earliestNextLoadTimeMs) {
                this.loadPending = true;
                HlsPlaylistTracker.this.playlistRefreshHandler.postDelayed(this, this.earliestNextLoadTimeMs - jElapsedRealtime);
            } else {
                loadPlaylistImmediately();
            }
        }

        public void maybeThrowPlaylistRefreshError() throws IOException {
            this.mediaPlaylistLoader.maybeThrowError();
            IOException iOException = this.playlistError;
            if (iOException != null) {
                throw iOException;
            }
        }

        @Override // com.google.android.exoplayer2.upstream.Loader.Callback
        public void onLoadCompleted(ParsingLoadable<HlsPlaylist> parsingLoadable, long j, long j2) {
            HlsPlaylist result = parsingLoadable.getResult();
            if (result instanceof HlsMediaPlaylist) {
                processLoadedPlaylist((HlsMediaPlaylist) result);
                HlsPlaylistTracker.this.eventDispatcher.loadCompleted(parsingLoadable.dataSpec, 4, -1, this.playlistUrl.format, 0, null, -9223372036854775807L, -9223372036854775807L, j, j2, parsingLoadable.bytesLoaded());
            } else {
                this.playlistError = new ParserException("Loaded playlist has unexpected type.");
            }
        }

        @Override // com.google.android.exoplayer2.upstream.Loader.Callback
        public void onLoadCanceled(ParsingLoadable<HlsPlaylist> parsingLoadable, long j, long j2, boolean z) {
            HlsPlaylistTracker.this.eventDispatcher.loadCanceled(parsingLoadable.dataSpec, 4, -1, this.playlistUrl.format, 0, null, -9223372036854775807L, -9223372036854775807L, j, j2, parsingLoadable.bytesLoaded());
        }

        @Override // com.google.android.exoplayer2.upstream.Loader.Callback
        public int onLoadError(ParsingLoadable<HlsPlaylist> parsingLoadable, long j, long j2, IOException iOException) {
            boolean z = iOException instanceof ParserException;
            HlsPlaylistTracker.this.eventDispatcher.loadError(parsingLoadable.dataSpec, 4, -1, this.playlistUrl.format, 0, null, -9223372036854775807L, -9223372036854775807L, j, j2, parsingLoadable.bytesLoaded(), iOException, z);
            if (z) {
                return 3;
            }
            return ChunkedTrackBlacklistUtil.shouldBlacklist(iOException) ? blacklistPlaylist() : true ? 0 : 2;
        }

        @Override // java.lang.Runnable
        public void run() {
            this.loadPending = false;
            loadPlaylistImmediately();
        }

        private void loadPlaylistImmediately() {
            HlsPlaylistTracker.this.eventDispatcher.loadStarted(this.mediaPlaylistLoadable.dataSpec, 4, -1, this.playlistUrl.format, 0, null, -9223372036854775807L, -9223372036854775807L, this.mediaPlaylistLoader.startLoading(this.mediaPlaylistLoadable, this, HlsPlaylistTracker.this.minRetryCount));
        }

        /* JADX INFO: Access modifiers changed from: private */
        public void processLoadedPlaylist(HlsMediaPlaylist hlsMediaPlaylist) {
            HlsMediaPlaylist hlsMediaPlaylist2 = this.playlistSnapshot;
            long jElapsedRealtime = SystemClock.elapsedRealtime();
            this.lastSnapshotLoadMs = jElapsedRealtime;
            HlsMediaPlaylist latestPlaylistSnapshot = HlsPlaylistTracker.this.getLatestPlaylistSnapshot(hlsMediaPlaylist2, hlsMediaPlaylist);
            this.playlistSnapshot = latestPlaylistSnapshot;
            if (latestPlaylistSnapshot != hlsMediaPlaylist2) {
                this.playlistError = null;
                this.lastSnapshotChangeMs = jElapsedRealtime;
                HlsPlaylistTracker.this.onPlaylistUpdated(this.playlistUrl, latestPlaylistSnapshot);
            } else if (!latestPlaylistSnapshot.hasEndTag) {
                if (hlsMediaPlaylist.mediaSequence + hlsMediaPlaylist.segments.size() < this.playlistSnapshot.mediaSequence) {
                    this.playlistError = new PlaylistResetException(this.playlistUrl.url);
                } else if (jElapsedRealtime - this.lastSnapshotChangeMs > C.usToMs(r12.targetDurationUs) * 3.5d) {
                    this.playlistError = new PlaylistStuckException(this.playlistUrl.url);
                    blacklistPlaylist();
                }
            }
            HlsMediaPlaylist hlsMediaPlaylist3 = this.playlistSnapshot;
            long j = hlsMediaPlaylist3.targetDurationUs;
            if (hlsMediaPlaylist3 == hlsMediaPlaylist2) {
                j /= 2;
            }
            this.earliestNextLoadTimeMs = jElapsedRealtime + C.usToMs(j);
            if (this.playlistUrl != HlsPlaylistTracker.this.primaryHlsUrl || this.playlistSnapshot.hasEndTag) {
                return;
            }
            loadPlaylist();
        }

        private boolean blacklistPlaylist() {
            this.blacklistUntilMs = SystemClock.elapsedRealtime() + 60000;
            HlsPlaylistTracker.this.notifyPlaylistBlacklisting(this.playlistUrl, 60000L);
            return HlsPlaylistTracker.this.primaryHlsUrl == this.playlistUrl && !HlsPlaylistTracker.this.maybeSelectNewPrimaryUrl();
        }
    }
}
