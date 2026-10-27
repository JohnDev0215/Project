package com.google.android.exoplayer2.source.dash;

import android.os.SystemClock;
import com.google.android.exoplayer2.Format;
import com.google.android.exoplayer2.extractor.ChunkIndex;
import com.google.android.exoplayer2.extractor.Extractor;
import com.google.android.exoplayer2.extractor.SeekMap;
import com.google.android.exoplayer2.extractor.TrackOutput;
import com.google.android.exoplayer2.extractor.mkv.MatroskaExtractor;
import com.google.android.exoplayer2.extractor.mp4.FragmentedMp4Extractor;
import com.google.android.exoplayer2.extractor.rawcc.RawCcExtractor;
import com.google.android.exoplayer2.source.BehindLiveWindowException;
import com.google.android.exoplayer2.source.chunk.Chunk;
import com.google.android.exoplayer2.source.chunk.ChunkExtractorWrapper;
import com.google.android.exoplayer2.source.chunk.ChunkedTrackBlacklistUtil;
import com.google.android.exoplayer2.source.chunk.ContainerMediaChunk;
import com.google.android.exoplayer2.source.chunk.InitializationChunk;
import com.google.android.exoplayer2.source.chunk.MediaChunk;
import com.google.android.exoplayer2.source.chunk.SingleSampleMediaChunk;
import com.google.android.exoplayer2.source.dash.DashChunkSource;
import com.google.android.exoplayer2.source.dash.PlayerEmsgHandler;
import com.google.android.exoplayer2.source.dash.manifest.AdaptationSet;
import com.google.android.exoplayer2.source.dash.manifest.DashManifest;
import com.google.android.exoplayer2.source.dash.manifest.RangedUri;
import com.google.android.exoplayer2.source.dash.manifest.Representation;
import com.google.android.exoplayer2.trackselection.TrackSelection;
import com.google.android.exoplayer2.upstream.DataSource;
import com.google.android.exoplayer2.upstream.DataSpec;
import com.google.android.exoplayer2.upstream.HttpDataSource;
import com.google.android.exoplayer2.upstream.LoaderErrorThrower;
import com.google.android.exoplayer2.util.MimeTypes;
import com.visualon.OSMPUtils.voMimeTypes;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: loaded from: classes6.dex */
public class DefaultDashChunkSource implements DashChunkSource {
    private final String TAG = DashChunkSource.class.getSimpleName();
    private final int[] adaptationSetIndices;
    private final DataSource dataSource;
    private final long elapsedRealtimeOffsetMs;
    private IOException fatalError;
    private DashManifest manifest;
    private final LoaderErrorThrower manifestLoaderErrorThrower;
    private final int maxSegmentsPerLoad;
    private boolean missingLastSegment;
    private int periodIndex;
    private final PlayerEmsgHandler.PlayerTrackEmsgHandler playerTrackEmsgHandler;
    protected final RepresentationHolder[] representationHolders;
    private final TrackSelection trackSelection;
    private final int trackType;

    public static final class Factory implements DashChunkSource.Factory {
        private final DataSource.Factory dataSourceFactory;
        private final int maxSegmentsPerLoad;

        public Factory(DataSource.Factory factory) {
            this(factory, 1);
        }

        public Factory(DataSource.Factory factory, int i) {
            this.dataSourceFactory = factory;
            this.maxSegmentsPerLoad = i;
        }

        @Override // com.google.android.exoplayer2.source.dash.DashChunkSource.Factory
        public DashChunkSource createDashChunkSource(LoaderErrorThrower loaderErrorThrower, DashManifest dashManifest, int i, int[] iArr, TrackSelection trackSelection, int i2, long j, boolean z, boolean z2, PlayerEmsgHandler.PlayerTrackEmsgHandler playerTrackEmsgHandler) {
            return new DefaultDashChunkSource(loaderErrorThrower, dashManifest, i, iArr, trackSelection, i2, this.dataSourceFactory.createDataSource(), j, this.maxSegmentsPerLoad, z, z2, playerTrackEmsgHandler);
        }
    }

    public DefaultDashChunkSource(LoaderErrorThrower loaderErrorThrower, DashManifest dashManifest, int i, int[] iArr, TrackSelection trackSelection, int i2, DataSource dataSource, long j, int i3, boolean z, boolean z2, PlayerEmsgHandler.PlayerTrackEmsgHandler playerTrackEmsgHandler) {
        this.manifestLoaderErrorThrower = loaderErrorThrower;
        this.manifest = dashManifest;
        this.adaptationSetIndices = iArr;
        this.trackSelection = trackSelection;
        this.trackType = i2;
        this.dataSource = dataSource;
        this.periodIndex = i;
        this.elapsedRealtimeOffsetMs = j;
        this.maxSegmentsPerLoad = i3;
        this.playerTrackEmsgHandler = playerTrackEmsgHandler;
        long periodDurationUs = dashManifest.getPeriodDurationUs(i);
        ArrayList<Representation> representations = getRepresentations();
        this.representationHolders = new RepresentationHolder[trackSelection.length()];
        for (int i4 = 0; i4 < this.representationHolders.length; i4++) {
            this.representationHolders[i4] = new RepresentationHolder(periodDurationUs, representations.get(trackSelection.getIndexInTrackGroup(i4)), z, z2, playerTrackEmsgHandler);
        }
    }

    @Override // com.google.android.exoplayer2.source.dash.DashChunkSource
    public void updateManifest(DashManifest dashManifest, int i) {
        try {
            this.manifest = dashManifest;
            this.periodIndex = i;
            long periodDurationUs = dashManifest.getPeriodDurationUs(i);
            ArrayList<Representation> representations = getRepresentations();
            for (int i2 = 0; i2 < this.representationHolders.length; i2++) {
                this.representationHolders[i2].updateRepresentation(periodDurationUs, representations.get(this.trackSelection.getIndexInTrackGroup(i2)));
            }
        } catch (BehindLiveWindowException e) {
            this.fatalError = e;
        }
    }

    @Override // com.google.android.exoplayer2.source.chunk.ChunkSource
    public void maybeThrowError() throws IOException {
        IOException iOException = this.fatalError;
        if (iOException != null) {
            throw iOException;
        }
        this.manifestLoaderErrorThrower.maybeThrowError();
    }

    /* JADX WARN: Removed duplicated region for block: B:39:0x00b7  */
    @Override // com.google.android.exoplayer2.source.chunk.ChunkSource
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void getNextChunk(com.google.android.exoplayer2.source.chunk.MediaChunk r18, long r19, long r21, com.google.android.exoplayer2.source.chunk.ChunkHolder r23) {
        /*
            Method dump skipped, instruction units count: 361
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.exoplayer2.source.dash.DefaultDashChunkSource.getNextChunk(com.google.android.exoplayer2.source.chunk.MediaChunk, long, long, com.google.android.exoplayer2.source.chunk.ChunkHolder):void");
    }

    @Override // com.google.android.exoplayer2.source.chunk.ChunkSource
    public void onChunkLoadCompleted(Chunk chunk) {
        SeekMap seekMap;
        if (chunk instanceof InitializationChunk) {
            RepresentationHolder representationHolder = this.representationHolders[this.trackSelection.indexOf(((InitializationChunk) chunk).trackFormat)];
            if (representationHolder.segmentIndex == null && (seekMap = representationHolder.extractorWrapper.getSeekMap()) != null) {
                representationHolder.segmentIndex = new DashWrappingSegmentIndex((ChunkIndex) seekMap);
            }
        }
        PlayerEmsgHandler.PlayerTrackEmsgHandler playerTrackEmsgHandler = this.playerTrackEmsgHandler;
        if (playerTrackEmsgHandler != null) {
            playerTrackEmsgHandler.onChunkLoadCompleted(chunk);
        }
    }

    @Override // com.google.android.exoplayer2.source.chunk.ChunkSource
    public boolean onChunkLoadError(Chunk chunk, boolean z, Exception exc) {
        RepresentationHolder representationHolder;
        int segmentCount;
        if (!z) {
            return false;
        }
        PlayerEmsgHandler.PlayerTrackEmsgHandler playerTrackEmsgHandler = this.playerTrackEmsgHandler;
        if (playerTrackEmsgHandler != null && playerTrackEmsgHandler.maybeRefreshManifestOnLoadingError(chunk)) {
            return true;
        }
        if (!this.manifest.dynamic && (chunk instanceof MediaChunk) && (exc instanceof HttpDataSource.InvalidResponseCodeException) && ((HttpDataSource.InvalidResponseCodeException) exc).responseCode == 404 && (segmentCount = (representationHolder = this.representationHolders[this.trackSelection.indexOf(chunk.trackFormat)]).getSegmentCount()) != -1 && segmentCount != 0) {
            if (((MediaChunk) chunk).getNextChunkIndex() > (representationHolder.getFirstSegmentNum() + segmentCount) - 1) {
                this.missingLastSegment = true;
                return true;
            }
        }
        TrackSelection trackSelection = this.trackSelection;
        return ChunkedTrackBlacklistUtil.maybeBlacklistTrack(trackSelection, trackSelection.indexOf(chunk.trackFormat), exc);
    }

    private ArrayList<Representation> getRepresentations() {
        List<AdaptationSet> list = this.manifest.getPeriod(this.periodIndex).adaptationSets;
        ArrayList<Representation> arrayList = new ArrayList<>();
        for (int i : this.adaptationSetIndices) {
            arrayList.addAll(list.get(i).representations);
        }
        return arrayList;
    }

    private long getNowUnixTimeUs() {
        long jCurrentTimeMillis;
        if (this.elapsedRealtimeOffsetMs != 0) {
            jCurrentTimeMillis = SystemClock.elapsedRealtime() + this.elapsedRealtimeOffsetMs;
        } else {
            jCurrentTimeMillis = System.currentTimeMillis();
        }
        return jCurrentTimeMillis * 1000;
    }

    protected static Chunk newInitializationChunk(RepresentationHolder representationHolder, DataSource dataSource, Format format, int i, Object obj, RangedUri rangedUri, RangedUri rangedUri2) {
        String str = representationHolder.representation.baseUrl;
        if (rangedUri == null || (rangedUri2 = rangedUri.attemptMerge(rangedUri2, str)) != null) {
            rangedUri = rangedUri2;
        }
        return new InitializationChunk(dataSource, new DataSpec(rangedUri.resolveUri(str), rangedUri.start, rangedUri.length, representationHolder.representation.getCacheKey()), format, i, obj, representationHolder.extractorWrapper);
    }

    protected static Chunk newMediaChunk(RepresentationHolder representationHolder, DataSource dataSource, int i, Format format, int i2, Object obj, int i3, int i4) {
        Representation representation = representationHolder.representation;
        long segmentStartTimeUs = representationHolder.getSegmentStartTimeUs(i3);
        RangedUri segmentUrl = representationHolder.getSegmentUrl(i3);
        String str = representation.baseUrl;
        if (representationHolder.extractorWrapper == null) {
            return new SingleSampleMediaChunk(dataSource, new DataSpec(segmentUrl.resolveUri(str), segmentUrl.start, segmentUrl.length, representation.getCacheKey()), format, i2, obj, segmentStartTimeUs, representationHolder.getSegmentEndTimeUs(i3), i3, i, format);
        }
        int i5 = 1;
        int i6 = 1;
        while (i5 < i4) {
            RangedUri rangedUriAttemptMerge = segmentUrl.attemptMerge(representationHolder.getSegmentUrl(i3 + i5), str);
            if (rangedUriAttemptMerge == null) {
                break;
            }
            i6++;
            i5++;
            segmentUrl = rangedUriAttemptMerge;
        }
        return new ContainerMediaChunk(dataSource, new DataSpec(segmentUrl.resolveUri(str), segmentUrl.start, segmentUrl.length, representation.getCacheKey()), format, i2, obj, segmentStartTimeUs, representationHolder.getSegmentEndTimeUs((i3 + i6) - 1), i3, i6, -representation.presentationTimeOffsetUs, representationHolder.extractorWrapper);
    }

    protected static final class RepresentationHolder {
        final ChunkExtractorWrapper extractorWrapper;
        private long periodDurationUs;
        public Representation representation;
        public DashSegmentIndex segmentIndex;
        private int segmentNumShift;

        RepresentationHolder(long j, Representation representation, boolean z, boolean z2, TrackOutput trackOutput) {
            Extractor fragmentedMp4Extractor;
            this.periodDurationUs = j;
            this.representation = representation;
            String str = representation.format.containerMimeType;
            if (mimeTypeIsRawText(str)) {
                this.extractorWrapper = null;
            } else {
                if ("application/x-rawcc".equals(str)) {
                    fragmentedMp4Extractor = new RawCcExtractor(representation.format);
                } else if (mimeTypeIsWebm(str)) {
                    fragmentedMp4Extractor = new MatroskaExtractor(1);
                } else {
                    int i = z ? 4 : 0;
                    fragmentedMp4Extractor = new FragmentedMp4Extractor(z2 ? i | 8 : i, null, null, trackOutput);
                }
                this.extractorWrapper = new ChunkExtractorWrapper(fragmentedMp4Extractor, representation.format);
            }
            this.segmentIndex = representation.getIndex();
        }

        void updateRepresentation(long j, Representation representation) throws BehindLiveWindowException {
            int segmentCount;
            DashSegmentIndex index = this.representation.getIndex();
            DashSegmentIndex index2 = representation.getIndex();
            this.periodDurationUs = j;
            this.representation = representation;
            if (index == null) {
                return;
            }
            this.segmentIndex = index2;
            if (index.isExplicit() && (segmentCount = index.getSegmentCount(this.periodDurationUs)) != 0) {
                int firstSegmentNum = (index.getFirstSegmentNum() + segmentCount) - 1;
                long timeUs = index.getTimeUs(firstSegmentNum) + index.getDurationUs(firstSegmentNum, this.periodDurationUs);
                int firstSegmentNum2 = index2.getFirstSegmentNum();
                long timeUs2 = index2.getTimeUs(firstSegmentNum2);
                if (timeUs == timeUs2) {
                    this.segmentNumShift += (firstSegmentNum + 1) - firstSegmentNum2;
                } else {
                    if (timeUs < timeUs2) {
                        throw new BehindLiveWindowException();
                    }
                    this.segmentNumShift += index.getSegmentNum(timeUs2, this.periodDurationUs) - firstSegmentNum2;
                }
            }
        }

        public int getFirstSegmentNum() {
            return this.segmentIndex.getFirstSegmentNum() + this.segmentNumShift;
        }

        public int getSegmentCount() {
            return this.segmentIndex.getSegmentCount(this.periodDurationUs);
        }

        public long getSegmentStartTimeUs(int i) {
            return this.segmentIndex.getTimeUs(i - this.segmentNumShift);
        }

        public long getSegmentEndTimeUs(int i) {
            return getSegmentStartTimeUs(i) + this.segmentIndex.getDurationUs(i - this.segmentNumShift, this.periodDurationUs);
        }

        public int getSegmentNum(long j) {
            return this.segmentIndex.getSegmentNum(j, this.periodDurationUs) + this.segmentNumShift;
        }

        public RangedUri getSegmentUrl(int i) {
            return this.segmentIndex.getSegmentUrl(i - this.segmentNumShift);
        }

        private static boolean mimeTypeIsWebm(String str) {
            return str.startsWith(voMimeTypes.VOVIDEO_WEBM) || str.startsWith(voMimeTypes.VOAUDIO_WEBM) || str.startsWith("application/webm");
        }

        private static boolean mimeTypeIsRawText(String str) {
            return MimeTypes.isText(str) || "application/ttml+xml".equals(str);
        }
    }
}
