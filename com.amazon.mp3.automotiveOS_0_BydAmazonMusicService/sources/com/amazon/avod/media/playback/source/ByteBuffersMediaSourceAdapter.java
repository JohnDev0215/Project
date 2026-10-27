package com.amazon.avod.media.playback.source;

import com.amazon.avod.media.playback.MediaDefaultConfiguration;
import com.amazon.avod.media.playback.util.TrackAdapter;
import com.amazon.avod.media.playback.util.TrackAdaptionStatus;
import com.amazon.avod.playback.PlaybackException;
import com.amazon.avod.playback.renderer.shared.SampleMetadata;
import com.amazon.avod.playback.sampling.SampleCodecData;
import com.amazon.avod.playback.sampling.SampleEncryptionInfo;
import com.amazon.avod.playback.sampling.SampleHolder;
import com.amazon.avod.playback.sampling.SampleType;
import com.amazon.avod.util.DLog;
import com.google.common.base.Preconditions;
import java.nio.ByteBuffer;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.concurrent.TimeUnit;
import javax.annotation.Nonnull;
import javax.annotation.concurrent.ThreadSafe;

/* JADX INFO: loaded from: classes2.dex */
@ThreadSafe
public class ByteBuffersMediaSourceAdapter implements MediaSource {
    private final long mBufferedTimeThrottleMicros;
    private final MediaDefaultConfiguration mConfig;
    private boolean mHasReadEndOfStream;
    private SampleCodecData mLastSubmittedCodecData;
    private volatile long mLastSubmittedTimeInNanoseconds;
    private final Object mMutex;
    private Queue<SampleMetadata> mSampleQueue;
    private final boolean mSkipSendingAudioTrackHeaderAsStandaloneSample;
    private final TrackAdapter mTrackAdapter;
    private final TrackAdaptionStatus mTrackAdaptionStatus;

    @Override // com.amazon.avod.media.playback.source.MediaSource
    public long getBufferedBytes() {
        return 0L;
    }

    @Override // com.amazon.avod.media.playback.source.MediaSource
    public long getCapacityBytes() {
        return 0L;
    }

    public ByteBuffersMediaSourceAdapter(boolean z, @Nonnull TrackAdapter trackAdapter) {
        this(z, trackAdapter, MediaDefaultConfiguration.getInstance());
    }

    public ByteBuffersMediaSourceAdapter(boolean z, @Nonnull TrackAdapter trackAdapter, @Nonnull MediaDefaultConfiguration mediaDefaultConfiguration) {
        this.mMutex = new Object();
        this.mSampleQueue = new ArrayDeque();
        this.mLastSubmittedTimeInNanoseconds = -1L;
        this.mLastSubmittedCodecData = null;
        this.mHasReadEndOfStream = false;
        this.mTrackAdaptionStatus = new TrackAdaptionStatus();
        this.mSkipSendingAudioTrackHeaderAsStandaloneSample = z;
        this.mTrackAdapter = (TrackAdapter) Preconditions.checkNotNull(trackAdapter, "trackAdapter");
        MediaDefaultConfiguration mediaDefaultConfiguration2 = (MediaDefaultConfiguration) Preconditions.checkNotNull(mediaDefaultConfiguration, "config");
        this.mConfig = mediaDefaultConfiguration2;
        this.mBufferedTimeThrottleMicros = mediaDefaultConfiguration2.getBufferedTimeThrottle().getTotalMicroseconds();
    }

    private ByteBuffer getSampleByteBuffer(SampleHolder sampleHolder) {
        ByteBuffer data = sampleHolder.getData();
        if (!sampleHolder.getNeedsBacking()) {
            return data;
        }
        ByteBuffer byteBufferAllocate = ByteBuffer.allocate(data.capacity());
        data.rewind();
        byteBufferAllocate.put(data);
        byteBufferAllocate.rewind();
        return byteBufferAllocate;
    }

    @Override // com.amazon.avod.media.playback.source.MediaSource
    public void submitSample(@Nonnull SampleHolder sampleHolder) throws PlaybackException {
        SampleMetadata sampleMetadataNewSampleMetaData;
        synchronized (this.mMutex) {
            long micros = TimeUnit.NANOSECONDS.toMicros(sampleHolder.getPresentationTime());
            SampleCodecData codecData = sampleHolder.getCodecData();
            ByteBuffer sampleByteBuffer = getSampleByteBuffer(sampleHolder);
            int length = 0;
            if (codecData != null && !codecData.equals(this.mLastSubmittedCodecData)) {
                byte[] rawBytes = codecData.getRawBytes();
                int i = AnonymousClass1.$SwitchMap$com$amazon$avod$playback$sampling$SampleType[sampleHolder.getType().ordinal()];
                if (i == 1) {
                    length = rawBytes.length;
                } else if (i == 2) {
                    if (this.mSkipSendingAudioTrackHeaderAsStandaloneSample) {
                        DLog.logf("skip sending standalone sample with audio track header");
                    } else {
                        this.mSampleQueue.add(SampleMetadata.newSampleMetaData(rawBytes.length, micros, 2, sampleHolder.getType(), sampleHolder.getBitrate(), rawBytes, null, sampleHolder.getFragmentUrl()));
                    }
                }
            }
            int i2 = length;
            SampleEncryptionInfo encryptionInfo = sampleHolder.getEncryptionInfo();
            if (encryptionInfo != null && encryptionInfo.getEncryptedRegions().length > 0) {
                sampleMetadataNewSampleMetaData = SampleMetadata.newSampleMetaDataWithEncryptionInfo(sampleHolder.getSize() + i2, micros, 0, sampleHolder.getType(), i2, encryptionInfo, sampleHolder.getBitrate(), i2 == 0 ? null : codecData.getRawBytes(), sampleByteBuffer, sampleHolder.getFragmentUrl());
            } else {
                sampleMetadataNewSampleMetaData = SampleMetadata.newSampleMetaData(sampleHolder.getSize() + i2, micros, 0, sampleHolder.getType(), sampleHolder.getBitrate(), i2 == 0 ? null : codecData.getRawBytes(), sampleByteBuffer, sampleHolder.getFragmentUrl());
            }
            this.mSampleQueue.add(sampleMetadataNewSampleMetaData);
            this.mLastSubmittedTimeInNanoseconds = sampleHolder.getPresentationTime();
            this.mLastSubmittedCodecData = sampleHolder.getCodecData();
            this.mHasReadEndOfStream = sampleHolder.getIsLastInStream();
        }
    }

    /* JADX INFO: renamed from: com.amazon.avod.media.playback.source.ByteBuffersMediaSourceAdapter$1, reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$amazon$avod$playback$sampling$SampleType;

        static {
            int[] iArr = new int[SampleType.values().length];
            $SwitchMap$com$amazon$avod$playback$sampling$SampleType = iArr;
            try {
                iArr[SampleType.VIDEO_SAMPLE.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$amazon$avod$playback$sampling$SampleType[SampleType.AUDIO_SAMPLE.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
        }
    }

    @Override // com.amazon.avod.media.playback.source.MediaSource
    public boolean hasCapacityForMoreSamples() {
        boolean z;
        synchronized (this.mMutex) {
            z = getBufferedTimeUs() <= this.mBufferedTimeThrottleMicros;
        }
        return z;
    }

    @Override // com.amazon.avod.media.playback.source.MediaSource
    public long getCapacityUs() {
        return this.mBufferedTimeThrottleMicros;
    }

    @Override // com.amazon.avod.media.playback.source.MediaSource
    public void dispose() {
        synchronized (this.mMutex) {
            this.mSampleQueue = null;
        }
    }

    @Override // com.amazon.avod.media.playback.source.MediaSource
    public long getBufferedTimeUs() {
        synchronized (this.mMutex) {
            if (!hasNext()) {
                return 0L;
            }
            return TimeUnit.NANOSECONDS.toMicros(this.mLastSubmittedTimeInNanoseconds) - this.mSampleQueue.peek().getPresentationTimeUs();
        }
    }

    @Override // com.amazon.avod.media.playback.source.MediaSource
    public void flush() {
        synchronized (this.mMutex) {
            this.mSampleQueue.clear();
            this.mHasReadEndOfStream = false;
            this.mLastSubmittedTimeInNanoseconds = -1L;
            this.mLastSubmittedCodecData = null;
        }
    }

    @Override // com.amazon.avod.media.playback.source.MediaSource
    public boolean hasNext() {
        boolean z;
        synchronized (this.mMutex) {
            Queue<SampleMetadata> queue = this.mSampleQueue;
            z = (queue == null || queue.isEmpty()) ? false : true;
        }
        return z;
    }

    @Override // com.amazon.avod.media.playback.source.MediaSource
    public boolean hasReadEndOfStream() {
        boolean z;
        synchronized (this.mMutex) {
            z = this.mHasReadEndOfStream;
        }
        return z;
    }

    @Override // com.amazon.avod.media.playback.source.MediaSource
    public SampleMetadata readSampleData(@Nonnull ByteBuffer byteBuffer) throws PlaybackException {
        SampleMetadata sampleMetadataElement;
        synchronized (this.mMutex) {
            sampleMetadataElement = this.mSampleQueue.element();
            byteBuffer.clear();
            byte[] codecData = sampleMetadataElement.getCodecData();
            if (codecData != null) {
                byteBuffer.put(codecData);
            }
            long presentationTimeUs = sampleMetadataElement.getPresentationTimeUs();
            ByteBuffer sampleData = sampleMetadataElement.getSampleData();
            if (sampleData != null) {
                sampleData.rewind();
                this.mTrackAdapter.writeAdaptedSample(sampleData, byteBuffer, sampleData.limit(), this.mTrackAdaptionStatus);
                if (this.mTrackAdaptionStatus.getError() != null) {
                    throw new PlaybackException(this.mTrackAdaptionStatus.getError(), String.format("Failed to adapt sample @ %d, Error: %d %d %d %d %d, FragmentUrl: %s", Long.valueOf(presentationTimeUs), Long.valueOf(this.mTrackAdaptionStatus.getFragmentBufferSize()), Long.valueOf(this.mTrackAdaptionStatus.getSrcBufferRemaining()), Long.valueOf(this.mTrackAdaptionStatus.getDstBufferRemaining()), Long.valueOf(this.mTrackAdaptionStatus.getBytesWritten()), Long.valueOf(this.mTrackAdaptionStatus.getNALSampleSize()), sampleMetadataElement.getFragmentUrl()));
                }
            }
            if (byteBuffer.position() != sampleMetadataElement.getSize()) {
                throw new PlaybackException(PlaybackException.PlaybackError.SAMPLE_ADAPTION_FAILURE, String.format("Failed to adapt sample completely, FragmentUrl: %s", sampleMetadataElement.getFragmentUrl()));
            }
            sampleMetadataElement.clearSampledata();
        }
        return sampleMetadataElement;
    }

    @Override // com.amazon.avod.media.playback.source.MediaSource
    public long getSampleTimeUs() {
        long presentationTimeUs;
        synchronized (this.mMutex) {
            presentationTimeUs = this.mSampleQueue.element().getPresentationTimeUs();
        }
        return presentationTimeUs;
    }

    @Override // com.amazon.avod.media.playback.source.MediaSource
    public void advance() {
        synchronized (this.mMutex) {
            this.mSampleQueue.remove();
        }
    }

    @Override // com.amazon.avod.media.playback.source.MediaSource
    public long getLastSubmittedTimeInNanos() {
        return this.mLastSubmittedTimeInNanoseconds;
    }
}
