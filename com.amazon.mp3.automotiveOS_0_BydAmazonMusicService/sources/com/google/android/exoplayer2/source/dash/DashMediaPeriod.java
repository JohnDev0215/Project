package com.google.android.exoplayer2.source.dash;

import android.util.Pair;
import android.util.SparseIntArray;
import com.google.android.exoplayer2.Format;
import com.google.android.exoplayer2.source.AdaptiveMediaSourceEventListener;
import com.google.android.exoplayer2.source.CompositeSequenceableLoader;
import com.google.android.exoplayer2.source.EmptySampleStream;
import com.google.android.exoplayer2.source.MediaPeriod;
import com.google.android.exoplayer2.source.SampleStream;
import com.google.android.exoplayer2.source.SequenceableLoader;
import com.google.android.exoplayer2.source.TrackGroup;
import com.google.android.exoplayer2.source.TrackGroupArray;
import com.google.android.exoplayer2.source.chunk.ChunkSampleStream;
import com.google.android.exoplayer2.source.dash.DashChunkSource;
import com.google.android.exoplayer2.source.dash.PlayerEmsgHandler;
import com.google.android.exoplayer2.source.dash.manifest.AdaptationSet;
import com.google.android.exoplayer2.source.dash.manifest.DashManifest;
import com.google.android.exoplayer2.source.dash.manifest.Descriptor;
import com.google.android.exoplayer2.source.dash.manifest.Representation;
import com.google.android.exoplayer2.trackselection.TrackSelection;
import com.google.android.exoplayer2.upstream.Allocator;
import com.google.android.exoplayer2.upstream.LoaderErrorThrower;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.IdentityHashMap;
import java.util.List;

/* JADX INFO: loaded from: classes6.dex */
final class DashMediaPeriod implements MediaPeriod, SequenceableLoader.Callback<ChunkSampleStream<DashChunkSource>>, ChunkSampleStream.ReleaseCallback<DashChunkSource> {
    private List<AdaptationSet> adaptationSets;
    private final Allocator allocator;
    private MediaPeriod.Callback callback;
    private final DashChunkSource.Factory chunkSourceFactory;
    private final long elapsedRealtimeOffset;
    private final AdaptiveMediaSourceEventListener.EventDispatcher eventDispatcher;
    final int id;
    private DashManifest manifest;
    private final LoaderErrorThrower manifestLoaderErrorThrower;
    private final int minLoadableRetryCount;
    private int periodIndex;
    private final PlayerEmsgHandler playerEmsgHandler;
    private final TrackGroupInfo[] trackGroupInfos;
    private final TrackGroupArray trackGroups;
    private ChunkSampleStream<DashChunkSource>[] sampleStreams = newSampleStreamArray(0);
    private final IdentityHashMap<ChunkSampleStream<DashChunkSource>, PlayerEmsgHandler.PlayerTrackEmsgHandler> trackEmsgHandlerBySampleStream = new IdentityHashMap<>();
    private CompositeSequenceableLoader sequenceableLoader = new CompositeSequenceableLoader(this.sampleStreams);

    @Override // com.google.android.exoplayer2.source.MediaPeriod
    public long readDiscontinuity() {
        return -9223372036854775807L;
    }

    public DashMediaPeriod(int i, DashManifest dashManifest, int i2, DashChunkSource.Factory factory, int i3, AdaptiveMediaSourceEventListener.EventDispatcher eventDispatcher, long j, LoaderErrorThrower loaderErrorThrower, Allocator allocator, PlayerEmsgHandler.PlayerEmsgCallback playerEmsgCallback) {
        this.id = i;
        this.manifest = dashManifest;
        this.periodIndex = i2;
        this.chunkSourceFactory = factory;
        this.minLoadableRetryCount = i3;
        this.eventDispatcher = eventDispatcher;
        this.elapsedRealtimeOffset = j;
        this.manifestLoaderErrorThrower = loaderErrorThrower;
        this.allocator = allocator;
        this.playerEmsgHandler = new PlayerEmsgHandler(dashManifest, playerEmsgCallback, allocator);
        List<AdaptationSet> list = dashManifest.getPeriod(i2).adaptationSets;
        this.adaptationSets = list;
        Pair<TrackGroupArray, TrackGroupInfo[]> pairBuildTrackGroups = buildTrackGroups(list);
        this.trackGroups = (TrackGroupArray) pairBuildTrackGroups.first;
        this.trackGroupInfos = (TrackGroupInfo[]) pairBuildTrackGroups.second;
    }

    public void updateManifest(DashManifest dashManifest, int i) {
        this.manifest = dashManifest;
        this.periodIndex = i;
        this.playerEmsgHandler.updateManifest(dashManifest);
        this.adaptationSets = dashManifest.getPeriod(i).adaptationSets;
        ChunkSampleStream<DashChunkSource>[] chunkSampleStreamArr = this.sampleStreams;
        if (chunkSampleStreamArr != null) {
            for (ChunkSampleStream<DashChunkSource> chunkSampleStream : chunkSampleStreamArr) {
                ((DashChunkSource) chunkSampleStream.getChunkSource()).updateManifest(dashManifest, i);
            }
            this.callback.onContinueLoadingRequested(this);
        }
    }

    public void release() {
        this.playerEmsgHandler.release();
        for (ChunkSampleStream<DashChunkSource> chunkSampleStream : this.sampleStreams) {
            chunkSampleStream.release(this);
        }
    }

    @Override // com.google.android.exoplayer2.source.chunk.ChunkSampleStream.ReleaseCallback
    public synchronized void onSampleStreamReleased(ChunkSampleStream<DashChunkSource> chunkSampleStream) {
        PlayerEmsgHandler.PlayerTrackEmsgHandler playerTrackEmsgHandlerRemove = this.trackEmsgHandlerBySampleStream.remove(chunkSampleStream);
        if (playerTrackEmsgHandlerRemove != null) {
            playerTrackEmsgHandlerRemove.release();
        }
    }

    @Override // com.google.android.exoplayer2.source.MediaPeriod
    public void prepare(MediaPeriod.Callback callback, long j) {
        this.callback = callback;
        callback.onPrepared(this);
    }

    @Override // com.google.android.exoplayer2.source.MediaPeriod
    public void maybeThrowPrepareError() throws IOException {
        this.manifestLoaderErrorThrower.maybeThrowError();
    }

    @Override // com.google.android.exoplayer2.source.MediaPeriod
    public TrackGroupArray getTrackGroups() {
        return this.trackGroups;
    }

    @Override // com.google.android.exoplayer2.source.MediaPeriod
    public long selectTracks(TrackSelection[] trackSelectionArr, boolean[] zArr, SampleStream[] sampleStreamArr, boolean[] zArr2, long j) {
        boolean z;
        SampleStream sampleStreamSelectEmbeddedTrack;
        TrackSelection trackSelection;
        HashMap map = new HashMap();
        for (int i = 0; i < trackSelectionArr.length; i++) {
            SampleStream sampleStream = sampleStreamArr[i];
            if (sampleStream instanceof ChunkSampleStream) {
                ChunkSampleStream chunkSampleStream = (ChunkSampleStream) sampleStream;
                TrackSelection trackSelection2 = trackSelectionArr[i];
                if (trackSelection2 == null || !zArr[i]) {
                    chunkSampleStream.release(this);
                    sampleStreamArr[i] = null;
                } else {
                    map.put(Integer.valueOf(this.trackGroups.indexOf(trackSelection2.getTrackGroup())), chunkSampleStream);
                }
            }
            if (sampleStreamArr[i] == null && (trackSelection = trackSelectionArr[i]) != null) {
                int iIndexOf = this.trackGroups.indexOf(trackSelection.getTrackGroup());
                TrackGroupInfo trackGroupInfo = this.trackGroupInfos[iIndexOf];
                if (trackGroupInfo.isPrimary) {
                    ChunkSampleStream<DashChunkSource> chunkSampleStreamBuildSampleStream = buildSampleStream(trackGroupInfo, trackSelectionArr[i], j);
                    map.put(Integer.valueOf(iIndexOf), chunkSampleStreamBuildSampleStream);
                    sampleStreamArr[i] = chunkSampleStreamBuildSampleStream;
                    zArr2[i] = true;
                }
            }
        }
        for (int i2 = 0; i2 < trackSelectionArr.length; i2++) {
            SampleStream sampleStream2 = sampleStreamArr[i2];
            if (((sampleStream2 instanceof ChunkSampleStream.EmbeddedSampleStream) || (sampleStream2 instanceof EmptySampleStream)) && (trackSelectionArr[i2] == null || !zArr[i2])) {
                releaseIfEmbeddedSampleStream(sampleStream2);
                sampleStreamArr[i2] = null;
            }
            TrackSelection trackSelection3 = trackSelectionArr[i2];
            if (trackSelection3 != null) {
                TrackGroupInfo trackGroupInfo2 = this.trackGroupInfos[this.trackGroups.indexOf(trackSelection3.getTrackGroup())];
                if (!trackGroupInfo2.isPrimary) {
                    ChunkSampleStream chunkSampleStream2 = (ChunkSampleStream) map.get(Integer.valueOf(trackGroupInfo2.primaryTrackGroupIndex));
                    SampleStream sampleStream3 = sampleStreamArr[i2];
                    if (chunkSampleStream2 == null) {
                        z = sampleStream3 instanceof EmptySampleStream;
                    } else {
                        z = (sampleStream3 instanceof ChunkSampleStream.EmbeddedSampleStream) && ((ChunkSampleStream.EmbeddedSampleStream) sampleStream3).parent == chunkSampleStream2;
                    }
                    if (!z) {
                        releaseIfEmbeddedSampleStream(sampleStream3);
                        if (chunkSampleStream2 == null) {
                            sampleStreamSelectEmbeddedTrack = new EmptySampleStream();
                        } else {
                            sampleStreamSelectEmbeddedTrack = chunkSampleStream2.selectEmbeddedTrack(j, trackGroupInfo2.trackType);
                        }
                        sampleStreamArr[i2] = sampleStreamSelectEmbeddedTrack;
                        zArr2[i2] = true;
                    }
                }
            }
        }
        this.sampleStreams = newSampleStreamArray(map.size());
        map.values().toArray(this.sampleStreams);
        this.sequenceableLoader = new CompositeSequenceableLoader(this.sampleStreams);
        return j;
    }

    @Override // com.google.android.exoplayer2.source.MediaPeriod
    public void discardBuffer(long j) {
        for (ChunkSampleStream<DashChunkSource> chunkSampleStream : this.sampleStreams) {
            chunkSampleStream.discardEmbeddedTracksTo(j);
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
        for (ChunkSampleStream<DashChunkSource> chunkSampleStream : this.sampleStreams) {
            chunkSampleStream.seekToUs(j);
        }
        return j;
    }

    @Override // com.google.android.exoplayer2.source.SequenceableLoader.Callback
    public void onContinueLoadingRequested(ChunkSampleStream<DashChunkSource> chunkSampleStream) {
        this.callback.onContinueLoadingRequested(this);
    }

    private static Pair<TrackGroupArray, TrackGroupInfo[]> buildTrackGroups(List<AdaptationSet> list) {
        int i;
        String str;
        int i2;
        int i3;
        int[][] groupedAdaptationSetIndices = getGroupedAdaptationSetIndices(list);
        int length = groupedAdaptationSetIndices.length;
        boolean[] zArr = new boolean[length];
        boolean[] zArr2 = new boolean[length];
        int i4 = 0;
        int i5 = length;
        int i6 = 0;
        while (true) {
            i = 1;
            if (i6 >= length) {
                break;
            }
            if (hasEventMessageTrack(list, groupedAdaptationSetIndices[i6])) {
                zArr[i6] = true;
                i5++;
            }
            if (hasCea608Track(list, groupedAdaptationSetIndices[i6])) {
                zArr2[i6] = true;
                i5++;
            }
            i6++;
        }
        TrackGroup[] trackGroupArr = new TrackGroup[i5];
        TrackGroupInfo[] trackGroupInfoArr = new TrackGroupInfo[i5];
        int i7 = 0;
        int i8 = 0;
        while (i7 < length) {
            int[] iArr = groupedAdaptationSetIndices[i7];
            ArrayList arrayList = new ArrayList();
            int length2 = iArr.length;
            for (int i9 = i4; i9 < length2; i9++) {
                arrayList.addAll(list.get(iArr[i9]).representations);
            }
            int size = arrayList.size();
            Format[] formatArr = new Format[size];
            for (int i10 = i4; i10 < size; i10++) {
                formatArr[i10] = ((Representation) arrayList.get(i10)).format;
            }
            AdaptationSet adaptationSet = list.get(iArr[i4]);
            boolean z = zArr[i7];
            boolean z2 = zArr2[i7];
            trackGroupArr[i8] = new TrackGroup(formatArr);
            int i11 = i8 + 1;
            trackGroupInfoArr[i8] = new TrackGroupInfo(adaptationSet.type, iArr, i8, true, z, z2);
            if (z) {
                Format[] formatArr2 = new Format[i];
                formatArr2[0] = Format.createSampleFormat(adaptationSet.id + ":emsg", "application/x-emsg", null, -1, null);
                trackGroupArr[i11] = new TrackGroup(formatArr2);
                str = null;
                trackGroupInfoArr[i11] = new TrackGroupInfo(4, iArr, i8, false, false, false);
                i11++;
            } else {
                str = null;
            }
            if (z2) {
                trackGroupArr[i11] = new TrackGroup(Format.createTextSampleFormat(adaptationSet.id + ":cea608", "application/cea-608", 0, str));
                int i12 = i8;
                i2 = 1;
                i3 = 0;
                trackGroupInfoArr[i11] = new TrackGroupInfo(3, iArr, i12, false, false, false);
                i11++;
            } else {
                i2 = 1;
                i3 = 0;
            }
            i7++;
            i = i2;
            i8 = i11;
            i4 = i3;
        }
        return Pair.create(new TrackGroupArray(trackGroupArr), trackGroupInfoArr);
    }

    private static int[][] getGroupedAdaptationSetIndices(List<AdaptationSet> list) {
        int size = list.size();
        SparseIntArray sparseIntArray = new SparseIntArray(size);
        for (int i = 0; i < size; i++) {
            sparseIntArray.put(list.get(i).id, i);
        }
        int[][] iArr = new int[size][];
        boolean[] zArr = new boolean[size];
        int i2 = 0;
        for (int i3 = 0; i3 < size; i3++) {
            if (!zArr[i3]) {
                zArr[i3] = true;
                Descriptor descriptorFindAdaptationSetSwitchingProperty = findAdaptationSetSwitchingProperty(list.get(i3).supplementalProperties);
                if (descriptorFindAdaptationSetSwitchingProperty == null) {
                    iArr[i2] = new int[]{i3};
                    i2++;
                } else {
                    String[] strArrSplit = descriptorFindAdaptationSetSwitchingProperty.value.split(",");
                    int[] iArr2 = new int[strArrSplit.length + 1];
                    iArr2[0] = i3;
                    int i4 = 0;
                    while (i4 < strArrSplit.length) {
                        int i5 = sparseIntArray.get(Integer.parseInt(strArrSplit[i4]));
                        zArr[i5] = true;
                        i4++;
                        iArr2[i4] = i5;
                    }
                    iArr[i2] = iArr2;
                    i2++;
                }
            }
        }
        return i2 < size ? (int[][]) Arrays.copyOf(iArr, i2) : iArr;
    }

    private ChunkSampleStream<DashChunkSource> buildSampleStream(TrackGroupInfo trackGroupInfo, TrackSelection trackSelection, long j) {
        int[] iArrCopyOf = new int[2];
        boolean z = trackGroupInfo.hasEmbeddedEventMessageTrack;
        int i = 0;
        if (z) {
            iArrCopyOf[0] = 4;
            i = 1;
        }
        boolean z2 = trackGroupInfo.hasEmbeddedCea608Track;
        if (z2) {
            iArrCopyOf[i] = 3;
            i++;
        }
        if (i < 2) {
            iArrCopyOf = Arrays.copyOf(iArrCopyOf, i);
        }
        return new ChunkSampleStream<>(trackGroupInfo.trackType, iArrCopyOf, this.chunkSourceFactory.createDashChunkSource(this.manifestLoaderErrorThrower, this.manifest, this.periodIndex, trackGroupInfo.adaptationSetIndices, trackSelection, trackGroupInfo.trackType, this.elapsedRealtimeOffset, z, z2, (this.manifest.dynamic && z) ? this.playerEmsgHandler.newPlayerTrackEmsgHandler() : null), this, this.allocator, j, this.minLoadableRetryCount, this.eventDispatcher);
    }

    private static Descriptor findAdaptationSetSwitchingProperty(List<Descriptor> list) {
        for (int i = 0; i < list.size(); i++) {
            Descriptor descriptor = list.get(i);
            if ("urn:mpeg:dash:adaptation-set-switching:2016".equals(descriptor.schemeIdUri)) {
                return descriptor;
            }
        }
        return null;
    }

    private static boolean hasEventMessageTrack(List<AdaptationSet> list, int[] iArr) {
        for (int i : iArr) {
            List<Representation> list2 = list.get(i).representations;
            for (int i2 = 0; i2 < list2.size(); i2++) {
                if (!list2.get(i2).inbandEventStreams.isEmpty()) {
                    return true;
                }
            }
        }
        return false;
    }

    private static boolean hasCea608Track(List<AdaptationSet> list, int[] iArr) {
        for (int i : iArr) {
            List<Descriptor> list2 = list.get(i).accessibilityDescriptors;
            for (int i2 = 0; i2 < list2.size(); i2++) {
                if ("urn:scte:dash:cc:cea-608:2015".equals(list2.get(i2).schemeIdUri)) {
                    return true;
                }
            }
        }
        return false;
    }

    private static ChunkSampleStream<DashChunkSource>[] newSampleStreamArray(int i) {
        return new ChunkSampleStream[i];
    }

    private static void releaseIfEmbeddedSampleStream(SampleStream sampleStream) {
        if (sampleStream instanceof ChunkSampleStream.EmbeddedSampleStream) {
            ((ChunkSampleStream.EmbeddedSampleStream) sampleStream).release();
        }
    }

    private static final class TrackGroupInfo {
        public final int[] adaptationSetIndices;
        public final boolean hasEmbeddedCea608Track;
        public final boolean hasEmbeddedEventMessageTrack;
        public final boolean isPrimary;
        public final int primaryTrackGroupIndex;
        public final int trackType;

        public TrackGroupInfo(int i, int[] iArr, int i2, boolean z, boolean z2, boolean z3) {
            this.trackType = i;
            this.adaptationSetIndices = iArr;
            this.primaryTrackGroupIndex = i2;
            this.isPrimary = z;
            this.hasEmbeddedEventMessageTrack = z2;
            this.hasEmbeddedCea608Track = z3;
        }
    }
}
