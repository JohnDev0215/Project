package com.google.android.exoplayer2.source.hls;

import com.google.android.exoplayer2.Format;
import com.google.android.exoplayer2.extractor.DefaultExtractorInput;
import com.google.android.exoplayer2.extractor.Extractor;
import com.google.android.exoplayer2.extractor.ExtractorInput;
import com.google.android.exoplayer2.extractor.mp3.Mp3Extractor;
import com.google.android.exoplayer2.extractor.ts.Ac3Extractor;
import com.google.android.exoplayer2.extractor.ts.AdtsExtractor;
import com.google.android.exoplayer2.metadata.Metadata;
import com.google.android.exoplayer2.metadata.id3.Id3Decoder;
import com.google.android.exoplayer2.metadata.id3.PrivFrame;
import com.google.android.exoplayer2.source.chunk.MediaChunk;
import com.google.android.exoplayer2.source.hls.playlist.HlsMasterPlaylist;
import com.google.android.exoplayer2.upstream.DataSource;
import com.google.android.exoplayer2.upstream.DataSpec;
import com.google.android.exoplayer2.util.ParsableByteArray;
import com.google.android.exoplayer2.util.TimestampAdjuster;
import com.google.android.exoplayer2.util.Util;
import java.io.EOFException;
import java.io.IOException;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/* JADX INFO: loaded from: classes6.dex */
final class HlsMediaChunk extends MediaChunk {
    private static final AtomicInteger UID_SOURCE = new AtomicInteger();
    private int bytesLoaded;
    public final int discontinuitySequenceNumber;
    private Extractor extractor;
    private HlsSampleStreamWrapper extractorOutput;
    public final HlsMasterPlaylist.HlsUrl hlsUrl;
    private final ParsableByteArray id3Data;
    private final Id3Decoder id3Decoder;
    private final DataSource initDataSource;
    private final DataSpec initDataSpec;
    private boolean initLoadCompleted;
    private int initSegmentBytesLoaded;
    private final boolean isEncrypted;
    private final boolean isMasterTimestampSource;
    private final boolean isPackedAudio;
    private final String lastPathSegment;
    private volatile boolean loadCanceled;
    private volatile boolean loadCompleted;
    private final List<Format> muxedCaptionFormats;
    private final boolean needNewExtractor;
    private final Extractor previousExtractor;
    private final boolean shouldSpliceIn;
    private final TimestampAdjuster timestampAdjuster;
    public final int uid;

    public HlsMediaChunk(DataSource dataSource, DataSpec dataSpec, DataSpec dataSpec2, HlsMasterPlaylist.HlsUrl hlsUrl, List<Format> list, int i, Object obj, long j, long j2, int i2, int i3, boolean z, TimestampAdjuster timestampAdjuster, HlsMediaChunk hlsMediaChunk, byte[] bArr, byte[] bArr2) {
        ParsableByteArray parsableByteArray;
        super(buildDataSource(dataSource, bArr, bArr2), dataSpec, hlsUrl.format, i, obj, j, j2, i2);
        this.discontinuitySequenceNumber = i3;
        this.initDataSpec = dataSpec2;
        this.hlsUrl = hlsUrl;
        this.muxedCaptionFormats = list;
        this.isMasterTimestampSource = z;
        this.timestampAdjuster = timestampAdjuster;
        this.isEncrypted = this.dataSource instanceof Aes128DataSource;
        String lastPathSegment = dataSpec.uri.getLastPathSegment();
        this.lastPathSegment = lastPathSegment;
        boolean z2 = lastPathSegment.endsWith(".aac") || lastPathSegment.endsWith(".ac3") || lastPathSegment.endsWith(".ec3") || lastPathSegment.endsWith(".mp3");
        this.isPackedAudio = z2;
        if (hlsMediaChunk != null && (parsableByteArray = hlsMediaChunk.id3Data) != null) {
            this.id3Decoder = hlsMediaChunk.id3Decoder;
            this.id3Data = parsableByteArray;
        } else {
            this.id3Decoder = z2 ? new Id3Decoder() : null;
            this.id3Data = z2 ? new ParsableByteArray(10) : null;
        }
        if (hlsMediaChunk != null) {
            this.previousExtractor = hlsMediaChunk.extractor;
            boolean z3 = hlsMediaChunk.hlsUrl != hlsUrl;
            this.shouldSpliceIn = z3;
            this.needNewExtractor = hlsMediaChunk.discontinuitySequenceNumber != i3 || z3;
        } else {
            this.previousExtractor = null;
            this.shouldSpliceIn = false;
            this.needNewExtractor = true;
        }
        this.initDataSource = dataSource;
        this.uid = UID_SOURCE.getAndIncrement();
    }

    public void init(HlsSampleStreamWrapper hlsSampleStreamWrapper) {
        this.extractorOutput = hlsSampleStreamWrapper;
        hlsSampleStreamWrapper.init(this.uid, this.shouldSpliceIn);
    }

    @Override // com.google.android.exoplayer2.source.chunk.MediaChunk
    public boolean isLoadCompleted() {
        return this.loadCompleted;
    }

    @Override // com.google.android.exoplayer2.source.chunk.Chunk
    public long bytesLoaded() {
        return this.bytesLoaded;
    }

    @Override // com.google.android.exoplayer2.upstream.Loader.Loadable
    public void cancelLoad() {
        this.loadCanceled = true;
    }

    @Override // com.google.android.exoplayer2.upstream.Loader.Loadable
    public boolean isLoadCanceled() {
        return this.loadCanceled;
    }

    @Override // com.google.android.exoplayer2.upstream.Loader.Loadable
    public void load() throws InterruptedException, IOException {
        if (this.extractor == null && !this.isPackedAudio) {
            this.extractor = createExtractor();
        }
        maybeLoadInitData();
        if (this.loadCanceled) {
            return;
        }
        loadMedia();
    }

    private void maybeLoadInitData() throws InterruptedException, IOException {
        DataSpec dataSpec;
        if (this.previousExtractor == this.extractor || this.initLoadCompleted || (dataSpec = this.initDataSpec) == null) {
            return;
        }
        DataSpec dataSpecSubrange = dataSpec.subrange(this.initSegmentBytesLoaded);
        try {
            DataSource dataSource = this.initDataSource;
            DefaultExtractorInput defaultExtractorInput = new DefaultExtractorInput(dataSource, dataSpecSubrange.absoluteStreamPosition, dataSource.open(dataSpecSubrange));
            int i = 0;
            while (i == 0) {
                try {
                    if (this.loadCanceled) {
                        break;
                    } else {
                        i = this.extractor.read(defaultExtractorInput, null);
                    }
                } finally {
                    this.initSegmentBytesLoaded = (int) (defaultExtractorInput.getPosition() - this.initDataSpec.absoluteStreamPosition);
                }
            }
            Util.closeQuietly(this.dataSource);
            this.initLoadCompleted = true;
        } catch (Throwable th) {
            Util.closeQuietly(this.dataSource);
            throw th;
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:11:0x001c  */
    /* JADX WARN: Removed duplicated region for block: B:12:0x0022  */
    /* JADX WARN: Removed duplicated region for block: B:17:0x004a A[Catch: all -> 0x00c5, TryCatch #1 {all -> 0x00c5, blocks: (B:15:0x0038, B:17:0x004a, B:19:0x0057, B:21:0x0060, B:20:0x005e, B:22:0x0066, B:24:0x006a, B:26:0x006e, B:28:0x0074, B:30:0x007a, B:32:0x0087, B:34:0x0090, B:43:0x00b1, B:41:0x00a4, B:42:0x00b0, B:36:0x0097, B:38:0x009b), top: B:51:0x0038, inners: #0 }] */
    /* JADX WARN: Removed duplicated region for block: B:32:0x0087 A[Catch: all -> 0x00c5, TryCatch #1 {all -> 0x00c5, blocks: (B:15:0x0038, B:17:0x004a, B:19:0x0057, B:21:0x0060, B:20:0x005e, B:22:0x0066, B:24:0x006a, B:26:0x006e, B:28:0x0074, B:30:0x007a, B:32:0x0087, B:34:0x0090, B:43:0x00b1, B:41:0x00a4, B:42:0x00b0, B:36:0x0097, B:38:0x009b), top: B:51:0x0038, inners: #0 }] */
    /* JADX WARN: Removed duplicated region for block: B:34:0x0090 A[Catch: all -> 0x00c5, TRY_LEAVE, TryCatch #1 {all -> 0x00c5, blocks: (B:15:0x0038, B:17:0x004a, B:19:0x0057, B:21:0x0060, B:20:0x005e, B:22:0x0066, B:24:0x006a, B:26:0x006e, B:28:0x0074, B:30:0x007a, B:32:0x0087, B:34:0x0090, B:43:0x00b1, B:41:0x00a4, B:42:0x00b0, B:36:0x0097, B:38:0x009b), top: B:51:0x0038, inners: #0 }] */
    /* JADX WARN: Removed duplicated region for block: B:49:0x0097 A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private void loadMedia() throws java.lang.InterruptedException, java.io.IOException {
        /*
            Method dump skipped, instruction units count: 204
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.exoplayer2.source.hls.HlsMediaChunk.loadMedia():void");
    }

    private static boolean sniffQuietly(Extractor extractor, ExtractorInput extractorInput) throws InterruptedException, IOException {
        try {
            boolean zSniff = extractor.sniff(extractorInput);
            extractorInput.resetPeekPosition();
            return zSniff;
        } catch (EOFException unused) {
            extractorInput.resetPeekPosition();
            return false;
        } catch (Throwable th) {
            extractorInput.resetPeekPosition();
            throw th;
        }
    }

    private long peekId3PrivTimestamp(ExtractorInput extractorInput) throws InterruptedException, IOException {
        Metadata metadataDecode;
        extractorInput.resetPeekPosition();
        if (!extractorInput.peekFully(this.id3Data.data, 0, 10, true)) {
            return -9223372036854775807L;
        }
        this.id3Data.reset(10);
        if (this.id3Data.readUnsignedInt24() != Id3Decoder.ID3_TAG) {
            return -9223372036854775807L;
        }
        this.id3Data.skipBytes(3);
        int synchSafeInt = this.id3Data.readSynchSafeInt();
        int i = synchSafeInt + 10;
        if (i > this.id3Data.capacity()) {
            ParsableByteArray parsableByteArray = this.id3Data;
            byte[] bArr = parsableByteArray.data;
            parsableByteArray.reset(i);
            System.arraycopy(bArr, 0, this.id3Data.data, 0, 10);
        }
        if (!extractorInput.peekFully(this.id3Data.data, 10, synchSafeInt, true) || (metadataDecode = this.id3Decoder.decode(this.id3Data.data, synchSafeInt)) == null) {
            return -9223372036854775807L;
        }
        int length = metadataDecode.length();
        for (int i2 = 0; i2 < length; i2++) {
            Metadata.Entry entry = metadataDecode.get(i2);
            if (entry instanceof PrivFrame) {
                PrivFrame privFrame = (PrivFrame) entry;
                if ("com.apple.streaming.transportStreamTimestamp".equals(privFrame.owner)) {
                    System.arraycopy(privFrame.privateData, 0, this.id3Data.data, 0, 8);
                    this.id3Data.reset(8);
                    return this.id3Data.readLong();
                }
            }
        }
        return -9223372036854775807L;
    }

    private static DataSource buildDataSource(DataSource dataSource, byte[] bArr, byte[] bArr2) {
        return (bArr == null || bArr2 == null) ? dataSource : new Aes128DataSource(dataSource, bArr, bArr2);
    }

    /* JADX WARN: Removed duplicated region for block: B:36:0x00a8  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private com.google.android.exoplayer2.extractor.Extractor createExtractor() {
        /*
            r6 = this;
            com.google.android.exoplayer2.source.hls.playlist.HlsMasterPlaylist$HlsUrl r0 = r6.hlsUrl
            com.google.android.exoplayer2.Format r0 = r0.format
            java.lang.String r0 = r0.sampleMimeType
            java.lang.String r1 = "text/vtt"
            boolean r0 = r1.equals(r0)
            r1 = 0
            r2 = 1
            if (r0 != 0) goto L9a
            java.lang.String r0 = r6.lastPathSegment
            java.lang.String r3 = ".webvtt"
            boolean r0 = r0.endsWith(r3)
            if (r0 != 0) goto L9a
            java.lang.String r0 = r6.lastPathSegment
            java.lang.String r3 = ".vtt"
            boolean r0 = r0.endsWith(r3)
            if (r0 == 0) goto L26
            goto L9a
        L26:
            boolean r0 = r6.needNewExtractor
            if (r0 != 0) goto L2e
            com.google.android.exoplayer2.extractor.Extractor r0 = r6.previousExtractor
            goto La6
        L2e:
            java.lang.String r0 = r6.lastPathSegment
            java.lang.String r3 = ".mp4"
            boolean r0 = r0.endsWith(r3)
            if (r0 != 0) goto L92
            java.lang.String r0 = r6.lastPathSegment
            int r3 = r0.length()
            int r3 = r3 + (-4)
            java.lang.String r4 = ".m4"
            boolean r0 = r0.startsWith(r4, r3)
            if (r0 == 0) goto L49
            goto L92
        L49:
            r0 = 16
            java.util.List<com.google.android.exoplayer2.Format> r1 = r6.muxedCaptionFormats
            if (r1 == 0) goto L58
            int r3 = r1.size()
            if (r3 == 0) goto L58
            r0 = 48
            goto L5c
        L58:
            java.util.List r1 = java.util.Collections.emptyList()
        L5c:
            com.google.android.exoplayer2.Format r3 = r6.trackFormat
            java.lang.String r3 = r3.codecs
            boolean r4 = android.text.TextUtils.isEmpty(r3)
            if (r4 != 0) goto L82
            java.lang.String r4 = com.google.android.exoplayer2.util.MimeTypes.getAudioMediaMimeType(r3)
            java.lang.String r5 = "audio/mp4a-latm"
            boolean r4 = r5.equals(r4)
            if (r4 != 0) goto L74
            r0 = r0 | 2
        L74:
            java.lang.String r3 = com.google.android.exoplayer2.util.MimeTypes.getVideoMediaMimeType(r3)
            java.lang.String r4 = "video/avc"
            boolean r3 = r4.equals(r3)
            if (r3 != 0) goto L82
            r0 = r0 | 4
        L82:
            com.google.android.exoplayer2.extractor.ts.TsExtractor r3 = new com.google.android.exoplayer2.extractor.ts.TsExtractor
            com.google.android.exoplayer2.util.TimestampAdjuster r4 = r6.timestampAdjuster
            com.google.android.exoplayer2.extractor.ts.DefaultTsPayloadReaderFactory r5 = new com.google.android.exoplayer2.extractor.ts.DefaultTsPayloadReaderFactory
            r5.<init>(r0, r1)
            r0 = 2
            r3.<init>(r0, r4, r5)
            r1 = r2
            r0 = r3
            goto La6
        L92:
            com.google.android.exoplayer2.extractor.mp4.FragmentedMp4Extractor r0 = new com.google.android.exoplayer2.extractor.mp4.FragmentedMp4Extractor
            com.google.android.exoplayer2.util.TimestampAdjuster r3 = r6.timestampAdjuster
            r0.<init>(r1, r3)
            goto La5
        L9a:
            com.google.android.exoplayer2.source.hls.WebvttExtractor r0 = new com.google.android.exoplayer2.source.hls.WebvttExtractor
            com.google.android.exoplayer2.Format r1 = r6.trackFormat
            java.lang.String r1 = r1.language
            com.google.android.exoplayer2.util.TimestampAdjuster r3 = r6.timestampAdjuster
            r0.<init>(r1, r3)
        La5:
            r1 = r2
        La6:
            if (r1 == 0) goto Lad
            com.google.android.exoplayer2.source.hls.HlsSampleStreamWrapper r1 = r6.extractorOutput
            r0.init(r1)
        Lad:
            return r0
        */
        throw new UnsupportedOperationException("Method not decompiled: com.google.android.exoplayer2.source.hls.HlsMediaChunk.createExtractor():com.google.android.exoplayer2.extractor.Extractor");
    }

    private Extractor buildPackedAudioExtractor(long j) {
        Extractor ac3Extractor;
        if (this.lastPathSegment.endsWith(".aac")) {
            ac3Extractor = new AdtsExtractor(j);
        } else if (this.lastPathSegment.endsWith(".ac3") || this.lastPathSegment.endsWith(".ec3")) {
            ac3Extractor = new Ac3Extractor(j);
        } else if (this.lastPathSegment.endsWith(".mp3")) {
            ac3Extractor = new Mp3Extractor(0, j);
        } else {
            throw new IllegalArgumentException("Unknown extension for audio file: " + this.lastPathSegment);
        }
        ac3Extractor.init(this.extractorOutput);
        return ac3Extractor;
    }
}
