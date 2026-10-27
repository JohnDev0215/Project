package com.google.android.exoplayer2.source.dash;

import android.os.Handler;
import android.os.Message;
import com.google.android.exoplayer2.Format;
import com.google.android.exoplayer2.FormatHolder;
import com.google.android.exoplayer2.ParserException;
import com.google.android.exoplayer2.extractor.ExtractorInput;
import com.google.android.exoplayer2.extractor.TrackOutput;
import com.google.android.exoplayer2.metadata.MetadataInputBuffer;
import com.google.android.exoplayer2.metadata.emsg.EventMessage;
import com.google.android.exoplayer2.metadata.emsg.EventMessageDecoder;
import com.google.android.exoplayer2.source.SampleQueue;
import com.google.android.exoplayer2.source.chunk.Chunk;
import com.google.android.exoplayer2.source.dash.manifest.DashManifest;
import com.google.android.exoplayer2.upstream.Allocator;
import com.google.android.exoplayer2.util.ParsableByteArray;
import com.google.android.exoplayer2.util.Util;
import java.io.IOException;
import java.util.Iterator;
import java.util.Map;
import java.util.TreeMap;

/* JADX INFO: loaded from: classes6.dex */
public final class PlayerEmsgHandler implements Handler.Callback {
    private final Allocator allocator;
    private boolean dynamicMediaPresentationEnded;
    private long expiredManifestPublishTimeUs;
    private boolean isWaitingForManifestRefresh;
    private DashManifest manifest;
    private final PlayerEmsgCallback playerEmsgCallback;
    private boolean released;
    private final TreeMap<Long, Long> manifestPublishTimeToExpiryTimeUs = new TreeMap<>();
    private final Handler handler = new Handler(this);
    private final EventMessageDecoder decoder = new EventMessageDecoder();
    private long lastLoadedChunkEndTimeUs = -9223372036854775807L;
    private long lastLoadedChunkEndTimeBeforeRefreshUs = -9223372036854775807L;

    public interface PlayerEmsgCallback {
        void onDashLiveMediaPresentationEndSignalEncountered();

        void onDashManifestPublishTimeExpired(long j);

        void onDashManifestRefreshRequested();
    }

    public PlayerEmsgHandler(DashManifest dashManifest, PlayerEmsgCallback playerEmsgCallback, Allocator allocator) {
        this.manifest = dashManifest;
        this.playerEmsgCallback = playerEmsgCallback;
        this.allocator = allocator;
    }

    public void updateManifest(DashManifest dashManifest) {
        this.isWaitingForManifestRefresh = false;
        this.expiredManifestPublishTimeUs = -9223372036854775807L;
        this.manifest = dashManifest;
        removePreviouslyExpiredManifestPublishTimeValues();
    }

    boolean maybeRefreshManifestBeforeLoadingNextChunk(long j) {
        Map.Entry<Long, Long> entryCeilingExpiryEntryForPublishTime;
        DashManifest dashManifest = this.manifest;
        boolean z = false;
        if (!dashManifest.dynamic) {
            return false;
        }
        if (this.isWaitingForManifestRefresh) {
            return true;
        }
        if (!this.dynamicMediaPresentationEnded && (entryCeilingExpiryEntryForPublishTime = ceilingExpiryEntryForPublishTime(dashManifest.publishTimeMs)) != null && entryCeilingExpiryEntryForPublishTime.getValue().longValue() < j) {
            this.expiredManifestPublishTimeUs = entryCeilingExpiryEntryForPublishTime.getKey().longValue();
            notifyManifestPublishTimeExpired();
            z = true;
        }
        if (z) {
            maybeNotifyDashManifestRefreshNeeded();
        }
        return z;
    }

    boolean maybeRefreshManifestOnLoadingError(Chunk chunk) {
        if (!this.manifest.dynamic) {
            return false;
        }
        if (this.isWaitingForManifestRefresh) {
            return true;
        }
        long j = this.lastLoadedChunkEndTimeUs;
        if (!(j != -9223372036854775807L && j < chunk.startTimeUs)) {
            return false;
        }
        maybeNotifyDashManifestRefreshNeeded();
        return true;
    }

    void onChunkLoadCompleted(Chunk chunk) {
        long j = this.lastLoadedChunkEndTimeUs;
        if (j != -9223372036854775807L || chunk.endTimeUs > j) {
            this.lastLoadedChunkEndTimeUs = chunk.endTimeUs;
        }
    }

    public static boolean isPlayerEmsgEvent(String str, String str2) {
        return "urn:mpeg:dash:event:2012".equals(str) && ("1".equals(str2) || "2".equals(str2) || "3".equals(str2));
    }

    public PlayerTrackEmsgHandler newPlayerTrackEmsgHandler() {
        return new PlayerTrackEmsgHandler(new SampleQueue(this.allocator));
    }

    public void release() {
        this.released = true;
        this.handler.removeCallbacksAndMessages(null);
    }

    @Override // android.os.Handler.Callback
    public boolean handleMessage(Message message) {
        if (this.released) {
            return true;
        }
        int i = message.what;
        if (i == 1) {
            handleMediaPresentationEndedMessageEncountered();
            return true;
        }
        if (i != 2) {
            return false;
        }
        ManifestExpiryEventInfo manifestExpiryEventInfo = (ManifestExpiryEventInfo) message.obj;
        handleManifestExpiredMessage(manifestExpiryEventInfo.eventTimeUs, manifestExpiryEventInfo.manifestPublishTimeMsInEmsg);
        return true;
    }

    private void handleManifestExpiredMessage(long j, long j2) {
        Long l = this.manifestPublishTimeToExpiryTimeUs.get(Long.valueOf(j2));
        if (l == null) {
            this.manifestPublishTimeToExpiryTimeUs.put(Long.valueOf(j2), Long.valueOf(j));
        } else if (l.longValue() > j) {
            this.manifestPublishTimeToExpiryTimeUs.put(Long.valueOf(j2), Long.valueOf(j));
        }
    }

    private void handleMediaPresentationEndedMessageEncountered() {
        this.dynamicMediaPresentationEnded = true;
        notifySourceMediaPresentationEnded();
    }

    private Map.Entry<Long, Long> ceilingExpiryEntryForPublishTime(long j) {
        return this.manifestPublishTimeToExpiryTimeUs.ceilingEntry(Long.valueOf(j));
    }

    private void removePreviouslyExpiredManifestPublishTimeValues() {
        Iterator<Map.Entry<Long, Long>> it = this.manifestPublishTimeToExpiryTimeUs.entrySet().iterator();
        while (it.hasNext()) {
            if (it.next().getKey().longValue() < this.manifest.publishTimeMs) {
                it.remove();
            }
        }
    }

    private void notifyManifestPublishTimeExpired() {
        this.playerEmsgCallback.onDashManifestPublishTimeExpired(this.expiredManifestPublishTimeUs);
    }

    private void notifySourceMediaPresentationEnded() {
        this.playerEmsgCallback.onDashLiveMediaPresentationEndSignalEncountered();
    }

    private void maybeNotifyDashManifestRefreshNeeded() {
        long j = this.lastLoadedChunkEndTimeBeforeRefreshUs;
        if (j == -9223372036854775807L || j != this.lastLoadedChunkEndTimeUs) {
            this.isWaitingForManifestRefresh = true;
            this.lastLoadedChunkEndTimeBeforeRefreshUs = this.lastLoadedChunkEndTimeUs;
            this.playerEmsgCallback.onDashManifestRefreshRequested();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static long getManifestPublishTimeMsInEmsg(EventMessage eventMessage) {
        try {
            return Util.parseXsDateTime(new String(eventMessage.messageData));
        } catch (ParserException unused) {
            return -9223372036854775807L;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static boolean isMessageSignalingMediaPresentationEnded(EventMessage eventMessage) {
        return eventMessage.presentationTimeUs == 0 && eventMessage.durationMs == 0;
    }

    public final class PlayerTrackEmsgHandler implements TrackOutput {
        private final SampleQueue sampleQueue;
        private final FormatHolder formatHolder = new FormatHolder();
        private final MetadataInputBuffer buffer = new MetadataInputBuffer();

        PlayerTrackEmsgHandler(SampleQueue sampleQueue) {
            this.sampleQueue = sampleQueue;
        }

        @Override // com.google.android.exoplayer2.extractor.TrackOutput
        public void format(Format format) {
            this.sampleQueue.format(format);
        }

        @Override // com.google.android.exoplayer2.extractor.TrackOutput
        public int sampleData(ExtractorInput extractorInput, int i, boolean z) throws InterruptedException, IOException {
            return this.sampleQueue.sampleData(extractorInput, i, z);
        }

        @Override // com.google.android.exoplayer2.extractor.TrackOutput
        public void sampleData(ParsableByteArray parsableByteArray, int i) {
            this.sampleQueue.sampleData(parsableByteArray, i);
        }

        @Override // com.google.android.exoplayer2.extractor.TrackOutput
        public void sampleMetadata(long j, int i, int i2, int i3, TrackOutput.CryptoData cryptoData) {
            this.sampleQueue.sampleMetadata(j, i, i2, i3, cryptoData);
            parseAndDiscardSamples();
        }

        public boolean maybeRefreshManifestBeforeLoadingNextChunk(long j) {
            return PlayerEmsgHandler.this.maybeRefreshManifestBeforeLoadingNextChunk(j);
        }

        public void onChunkLoadCompleted(Chunk chunk) {
            PlayerEmsgHandler.this.onChunkLoadCompleted(chunk);
        }

        public boolean maybeRefreshManifestOnLoadingError(Chunk chunk) {
            return PlayerEmsgHandler.this.maybeRefreshManifestOnLoadingError(chunk);
        }

        public void release() {
            this.sampleQueue.reset();
        }

        private void parseAndDiscardSamples() {
            while (this.sampleQueue.hasNextSample()) {
                MetadataInputBuffer metadataInputBufferDequeueSample = dequeueSample();
                if (metadataInputBufferDequeueSample != null) {
                    long j = metadataInputBufferDequeueSample.timeUs;
                    EventMessage eventMessage = (EventMessage) PlayerEmsgHandler.this.decoder.decode(metadataInputBufferDequeueSample).get(0);
                    if (PlayerEmsgHandler.isPlayerEmsgEvent(eventMessage.schemeIdUri, eventMessage.value)) {
                        parsePlayerEmsgEvent(j, eventMessage);
                    }
                }
            }
            this.sampleQueue.discardToRead();
        }

        private MetadataInputBuffer dequeueSample() {
            this.buffer.clear();
            if (this.sampleQueue.read(this.formatHolder, this.buffer, false, false, 0L) != -4) {
                return null;
            }
            this.buffer.flip();
            return this.buffer;
        }

        private void parsePlayerEmsgEvent(long j, EventMessage eventMessage) {
            long manifestPublishTimeMsInEmsg = PlayerEmsgHandler.getManifestPublishTimeMsInEmsg(eventMessage);
            if (manifestPublishTimeMsInEmsg == -9223372036854775807L) {
                return;
            }
            if (PlayerEmsgHandler.isMessageSignalingMediaPresentationEnded(eventMessage)) {
                onMediaPresentationEndedMessageEncountered();
            } else {
                onManifestExpiredMessageEncountered(j, manifestPublishTimeMsInEmsg);
            }
        }

        private void onMediaPresentationEndedMessageEncountered() {
            PlayerEmsgHandler.this.handler.sendMessage(PlayerEmsgHandler.this.handler.obtainMessage(1));
        }

        private void onManifestExpiredMessageEncountered(long j, long j2) {
            PlayerEmsgHandler.this.handler.sendMessage(PlayerEmsgHandler.this.handler.obtainMessage(2, new ManifestExpiryEventInfo(j, j2)));
        }
    }

    private static final class ManifestExpiryEventInfo {
        public final long eventTimeUs;
        public final long manifestPublishTimeMsInEmsg;

        public ManifestExpiryEventInfo(long j, long j2) {
            this.eventTimeUs = j;
            this.manifestPublishTimeMsInEmsg = j2;
        }
    }
}
