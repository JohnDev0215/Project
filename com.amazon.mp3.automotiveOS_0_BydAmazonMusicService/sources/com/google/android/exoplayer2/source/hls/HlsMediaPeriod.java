package com.google.android.exoplayer2.source.hls;

import android.os.Handler;
import android.text.TextUtils;
import com.google.android.exoplayer2.Format;
import com.google.android.exoplayer2.source.AdaptiveMediaSourceEventListener;
import com.google.android.exoplayer2.source.CompositeSequenceableLoader;
import com.google.android.exoplayer2.source.MediaPeriod;
import com.google.android.exoplayer2.source.SampleStream;
import com.google.android.exoplayer2.source.TrackGroup;
import com.google.android.exoplayer2.source.TrackGroupArray;
import com.google.android.exoplayer2.source.hls.HlsSampleStreamWrapper;
import com.google.android.exoplayer2.source.hls.playlist.HlsMasterPlaylist;
import com.google.android.exoplayer2.source.hls.playlist.HlsPlaylistTracker;
import com.google.android.exoplayer2.upstream.Allocator;
import com.google.android.exoplayer2.util.Assertions;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.IdentityHashMap;
import java.util.List;

/* JADX INFO: loaded from: classes6.dex */
public final class HlsMediaPeriod implements MediaPeriod, HlsSampleStreamWrapper.Callback, HlsPlaylistTracker.PlaylistEventListener {
    private final Allocator allocator;
    private MediaPeriod.Callback callback;
    private final HlsDataSourceFactory dataSourceFactory;
    private final AdaptiveMediaSourceEventListener.EventDispatcher eventDispatcher;
    private final boolean ignoreResolutionInManifest;
    private final int minLoadableRetryCount;
    private int pendingPrepareCount;
    private final HlsPlaylistTracker playlistTracker;
    private CompositeSequenceableLoader sequenceableLoader;
    private TrackGroupArray trackGroups;
    private final IdentityHashMap<SampleStream, Integer> streamWrapperIndices = new IdentityHashMap<>();
    private final TimestampAdjusterProvider timestampAdjusterProvider = new TimestampAdjusterProvider();
    private final Handler continueLoadingHandler = new Handler();
    private HlsSampleStreamWrapper[] sampleStreamWrappers = new HlsSampleStreamWrapper[0];
    private HlsSampleStreamWrapper[] enabledSampleStreamWrappers = new HlsSampleStreamWrapper[0];

    @Override // com.google.android.exoplayer2.source.MediaPeriod
    public long readDiscontinuity() {
        return -9223372036854775807L;
    }

    public HlsMediaPeriod(HlsPlaylistTracker hlsPlaylistTracker, HlsDataSourceFactory hlsDataSourceFactory, int i, AdaptiveMediaSourceEventListener.EventDispatcher eventDispatcher, Allocator allocator, boolean z) {
        this.playlistTracker = hlsPlaylistTracker;
        this.dataSourceFactory = hlsDataSourceFactory;
        this.minLoadableRetryCount = i;
        this.eventDispatcher = eventDispatcher;
        this.allocator = allocator;
        this.ignoreResolutionInManifest = z;
    }

    public void release() {
        this.playlistTracker.removeListener(this);
        this.continueLoadingHandler.removeCallbacksAndMessages(null);
        for (HlsSampleStreamWrapper hlsSampleStreamWrapper : this.sampleStreamWrappers) {
            hlsSampleStreamWrapper.release();
        }
    }

    @Override // com.google.android.exoplayer2.source.MediaPeriod
    public void prepare(MediaPeriod.Callback callback, long j) {
        this.callback = callback;
        this.playlistTracker.addListener(this);
        buildAndPrepareSampleStreamWrappers(j);
    }

    @Override // com.google.android.exoplayer2.source.MediaPeriod
    public void maybeThrowPrepareError() throws IOException {
        for (HlsSampleStreamWrapper hlsSampleStreamWrapper : this.sampleStreamWrappers) {
            hlsSampleStreamWrapper.maybeThrowPrepareError();
        }
    }

    @Override // com.google.android.exoplayer2.source.MediaPeriod
    public TrackGroupArray getTrackGroups() {
        return this.trackGroups;
    }

    /* JADX WARN: Removed duplicated region for block: B:62:0x00eb  */
    @Override // com.google.android.exoplayer2.source.MediaPeriod
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public long selectTracks(com.google.android.exoplayer2.trackselection.TrackSelection[] r22, boolean[] r23, com.google.android.exoplayer2.source.SampleStream[] r24, boolean[] r25, long r26) {
        /*
            Method dump skipped, instruction units count: 284
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.exoplayer2.source.hls.HlsMediaPeriod.selectTracks(com.google.android.exoplayer2.trackselection.TrackSelection[], boolean[], com.google.android.exoplayer2.source.SampleStream[], boolean[], long):long");
    }

    @Override // com.google.android.exoplayer2.source.MediaPeriod
    public void discardBuffer(long j) {
        for (HlsSampleStreamWrapper hlsSampleStreamWrapper : this.enabledSampleStreamWrappers) {
            hlsSampleStreamWrapper.discardBuffer(j);
        }
    }

    @Override // com.google.android.exoplayer2.source.MediaPeriod, com.google.android.exoplayer2.source.SequenceableLoader
    public boolean continueLoading(long j) {
        return this.sequenceableLoader.continueLoading(j);
    }

    @Override // com.google.android.exoplayer2.source.MediaPeriod, com.google.android.exoplayer2.source.SequenceableLoader
    public long getNextLoadPositionUs() {
        return this.sequenceableLoader.getNextLoadPositionUs();
    }

    @Override // com.google.android.exoplayer2.source.MediaPeriod, com.google.android.exoplayer2.source.SequenceableLoader
    public long getBufferedPositionUs() {
        return this.sequenceableLoader.getBufferedPositionUs();
    }

    @Override // com.google.android.exoplayer2.source.MediaPeriod
    public long seekToUs(long j) {
        HlsSampleStreamWrapper[] hlsSampleStreamWrapperArr = this.enabledSampleStreamWrappers;
        if (hlsSampleStreamWrapperArr.length > 0) {
            boolean zSeekToUs = hlsSampleStreamWrapperArr[0].seekToUs(j, false);
            int i = 1;
            while (true) {
                HlsSampleStreamWrapper[] hlsSampleStreamWrapperArr2 = this.enabledSampleStreamWrappers;
                if (i >= hlsSampleStreamWrapperArr2.length) {
                    break;
                }
                hlsSampleStreamWrapperArr2[i].seekToUs(j, zSeekToUs);
                i++;
            }
            if (zSeekToUs) {
                this.timestampAdjusterProvider.reset();
            }
        }
        return j;
    }

    @Override // com.google.android.exoplayer2.source.hls.HlsSampleStreamWrapper.Callback
    public void onPrepared() {
        int i = this.pendingPrepareCount - 1;
        this.pendingPrepareCount = i;
        if (i > 0) {
            return;
        }
        int i2 = 0;
        for (HlsSampleStreamWrapper hlsSampleStreamWrapper : this.sampleStreamWrappers) {
            i2 += hlsSampleStreamWrapper.getTrackGroups().length;
        }
        TrackGroup[] trackGroupArr = new TrackGroup[i2];
        int i3 = 0;
        for (HlsSampleStreamWrapper hlsSampleStreamWrapper2 : this.sampleStreamWrappers) {
            int i4 = hlsSampleStreamWrapper2.getTrackGroups().length;
            int i5 = 0;
            while (i5 < i4) {
                trackGroupArr[i3] = hlsSampleStreamWrapper2.getTrackGroups().get(i5);
                i5++;
                i3++;
            }
        }
        this.trackGroups = new TrackGroupArray(trackGroupArr);
        this.callback.onPrepared(this);
    }

    @Override // com.google.android.exoplayer2.source.hls.HlsSampleStreamWrapper.Callback
    public void onPlaylistRefreshRequired(HlsMasterPlaylist.HlsUrl hlsUrl) {
        this.playlistTracker.refreshPlaylist(hlsUrl);
    }

    @Override // com.google.android.exoplayer2.source.SequenceableLoader.Callback
    public void onContinueLoadingRequested(HlsSampleStreamWrapper hlsSampleStreamWrapper) {
        if (this.trackGroups == null) {
            return;
        }
        this.callback.onContinueLoadingRequested(this);
    }

    @Override // com.google.android.exoplayer2.source.hls.playlist.HlsPlaylistTracker.PlaylistEventListener
    public void onPlaylistChanged() {
        continuePreparingOrLoading();
    }

    @Override // com.google.android.exoplayer2.source.hls.playlist.HlsPlaylistTracker.PlaylistEventListener
    public void onPlaylistBlacklisted(HlsMasterPlaylist.HlsUrl hlsUrl, long j) {
        for (HlsSampleStreamWrapper hlsSampleStreamWrapper : this.sampleStreamWrappers) {
            hlsSampleStreamWrapper.onPlaylistBlacklisted(hlsUrl, j);
        }
        continuePreparingOrLoading();
    }

    private void buildAndPrepareSampleStreamWrappers(long j) {
        HlsMasterPlaylist masterPlaylist = this.playlistTracker.getMasterPlaylist();
        ArrayList arrayList = new ArrayList(masterPlaylist.variants);
        ArrayList arrayList2 = new ArrayList();
        ArrayList arrayList3 = new ArrayList();
        for (int i = 0; i < arrayList.size(); i++) {
            HlsMasterPlaylist.HlsUrl hlsUrl = (HlsMasterPlaylist.HlsUrl) arrayList.get(i);
            if ((!this.ignoreResolutionInManifest && hlsUrl.format.height > 0) || variantHasExplicitCodecWithPrefix(hlsUrl, "avc")) {
                arrayList2.add(hlsUrl);
            } else if (variantHasExplicitCodecWithPrefix(hlsUrl, "mp4a")) {
                arrayList3.add(hlsUrl);
            }
        }
        if (!arrayList2.isEmpty()) {
            arrayList = arrayList2;
        } else if (arrayList3.size() < arrayList.size()) {
            arrayList.removeAll(arrayList3);
        }
        List<HlsMasterPlaylist.HlsUrl> list = masterPlaylist.audios;
        List<HlsMasterPlaylist.HlsUrl> list2 = masterPlaylist.subtitles;
        HlsSampleStreamWrapper[] hlsSampleStreamWrapperArr = new HlsSampleStreamWrapper[list.size() + 1 + list2.size()];
        this.sampleStreamWrappers = hlsSampleStreamWrapperArr;
        this.pendingPrepareCount = hlsSampleStreamWrapperArr.length;
        Assertions.checkArgument(!arrayList.isEmpty());
        HlsMasterPlaylist.HlsUrl[] hlsUrlArr = new HlsMasterPlaylist.HlsUrl[arrayList.size()];
        arrayList.toArray(hlsUrlArr);
        HlsSampleStreamWrapper hlsSampleStreamWrapperBuildSampleStreamWrapper = buildSampleStreamWrapper(0, hlsUrlArr, masterPlaylist.muxedAudioFormat, masterPlaylist.muxedCaptionFormats, j);
        this.sampleStreamWrappers[0] = hlsSampleStreamWrapperBuildSampleStreamWrapper;
        hlsSampleStreamWrapperBuildSampleStreamWrapper.setIsTimestampMaster(true);
        hlsSampleStreamWrapperBuildSampleStreamWrapper.continuePreparing();
        int i2 = 0;
        int i3 = 1;
        while (i2 < list.size()) {
            HlsSampleStreamWrapper hlsSampleStreamWrapperBuildSampleStreamWrapper2 = buildSampleStreamWrapper(1, new HlsMasterPlaylist.HlsUrl[]{list.get(i2)}, null, Collections.emptyList(), j);
            this.sampleStreamWrappers[i3] = hlsSampleStreamWrapperBuildSampleStreamWrapper2;
            hlsSampleStreamWrapperBuildSampleStreamWrapper2.continuePreparing();
            i2++;
            i3++;
        }
        int i4 = 0;
        while (i4 < list2.size()) {
            HlsMasterPlaylist.HlsUrl hlsUrl2 = list2.get(i4);
            HlsSampleStreamWrapper hlsSampleStreamWrapperBuildSampleStreamWrapper3 = buildSampleStreamWrapper(3, new HlsMasterPlaylist.HlsUrl[]{hlsUrl2}, null, Collections.emptyList(), j);
            hlsSampleStreamWrapperBuildSampleStreamWrapper3.prepareSingleTrack(hlsUrl2.format);
            this.sampleStreamWrappers[i3] = hlsSampleStreamWrapperBuildSampleStreamWrapper3;
            i4++;
            i3++;
        }
        this.enabledSampleStreamWrappers = this.sampleStreamWrappers;
    }

    private HlsSampleStreamWrapper buildSampleStreamWrapper(int i, HlsMasterPlaylist.HlsUrl[] hlsUrlArr, Format format, List<Format> list, long j) {
        return new HlsSampleStreamWrapper(i, this, new HlsChunkSource(this.playlistTracker, hlsUrlArr, this.dataSourceFactory, this.timestampAdjusterProvider, list), this.allocator, j, format, this.minLoadableRetryCount, this.eventDispatcher);
    }

    private void continuePreparingOrLoading() {
        if (this.trackGroups != null) {
            this.callback.onContinueLoadingRequested(this);
            return;
        }
        for (HlsSampleStreamWrapper hlsSampleStreamWrapper : this.sampleStreamWrappers) {
            hlsSampleStreamWrapper.continuePreparing();
        }
    }

    private static boolean variantHasExplicitCodecWithPrefix(HlsMasterPlaylist.HlsUrl hlsUrl, String str) {
        String str2 = hlsUrl.format.codecs;
        if (TextUtils.isEmpty(str2)) {
            return false;
        }
        for (String str3 : str2.split("(\\s*,\\s*)|(\\s*$)")) {
            if (str3.startsWith(str)) {
                return true;
            }
        }
        return false;
    }
}
