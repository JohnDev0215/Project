package com.amazon.avod.media.playback.source;

import com.amazon.avod.media.playback.util.CircularByteBuffer;
import com.amazon.avod.media.playback.util.TrackAdapter;
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
public class BufferingMediaSourceAdapter implements MediaSource {
    private CircularByteBuffer mBuffer;
    private final int mMaxSampleSizeBytes;
    private final boolean mSkipSendingAudioTrackHeaderAsStandaloneSample;
    private final TrackAdapter mTrackAdapter;
    private final Object mMutex = new Object();
    private Queue<SampleMetadata> mSampleQueue = new ArrayDeque();
    private volatile long mLastSubmittedTimeInNanoseconds = -1;
    private SampleCodecData mLastSubmittedCodecData = null;
    private boolean mHasReadEndOfStream = false;

    @Override // com.amazon.avod.media.playback.source.MediaSource
    public long getCapacityUs() {
        return 0L;
    }

    public BufferingMediaSourceAdapter(int i, int i2, boolean z, @Nonnull TrackAdapter trackAdapter) {
        this.mBuffer = CircularByteBuffer.allocate(i);
        this.mMaxSampleSizeBytes = i2;
        this.mTrackAdapter = (TrackAdapter) Preconditions.checkNotNull(trackAdapter, "trackAdapter");
        this.mSkipSendingAudioTrackHeaderAsStandaloneSample = z;
    }

    @Override // com.amazon.avod.media.playback.source.MediaSource
    public void submitSample(@Nonnull SampleHolder sampleHolder) throws PlaybackException {
        int i;
        SampleMetadata sampleMetadataNewSampleMetaData;
        int length;
        synchronized (this.mMutex) {
            long micros = TimeUnit.NANOSECONDS.toMicros(sampleHolder.getPresentationTime());
            SampleCodecData codecData = sampleHolder.getCodecData();
            if (codecData == null || codecData.equals(this.mLastSubmittedCodecData)) {
                i = 0;
            } else {
                byte[] rawBytes = codecData.getRawBytes();
                int i2 = AnonymousClass1.$SwitchMap$com$amazon$avod$playback$sampling$SampleType[sampleHolder.getType().ordinal()];
                if (i2 != 1) {
                    if (i2 == 2) {
                        if (this.mSkipSendingAudioTrackHeaderAsStandaloneSample) {
                            DLog.logf("skip sending standalone sample with audio track header");
                        } else {
                            this.mSampleQueue.add(SampleMetadata.newSampleMetaData(rawBytes.length, micros, 2, sampleHolder.getType(), sampleHolder.getBitrate(), sampleHolder.getFragmentUrl()));
                            this.mBuffer.put(rawBytes, 0, rawBytes.length);
                        }
                    }
                    length = 0;
                } else {
                    length = rawBytes.length;
                    this.mBuffer.put(rawBytes, 0, length);
                }
                i = length;
            }
            long jWriteAdaptedSample = this.mTrackAdapter.writeAdaptedSample(sampleHolder.getData(), this.mBuffer, sampleHolder.getSize());
            if (jWriteAdaptedSample <= 0) {
                throw new PlaybackException(PlaybackException.PlaybackError.SAMPLE_ADAPTION_FAILURE, String.format("Failed to adapt sample, bytes copied=%d, FragmentUrl: %s", Integer.valueOf((int) jWriteAdaptedSample), sampleHolder.getFragmentUrl()));
            }
            SampleEncryptionInfo encryptionInfo = sampleHolder.getEncryptionInfo();
            if (encryptionInfo != null && encryptionInfo.getEncryptedRegions().length > 0) {
                sampleMetadataNewSampleMetaData = SampleMetadata.newSampleMetaDataWithEncryptionInfo(i + sampleHolder.getSize(), micros, 0, sampleHolder.getType(), i, encryptionInfo, sampleHolder.getBitrate(), sampleHolder.getFragmentUrl());
            } else {
                sampleMetadataNewSampleMetaData = SampleMetadata.newSampleMetaData(i + sampleHolder.getSize(), micros, 0, sampleHolder.getType(), sampleHolder.getBitrate(), sampleHolder.getFragmentUrl());
            }
            this.mSampleQueue.add(sampleMetadataNewSampleMetaData);
            this.mLastSubmittedTimeInNanoseconds = sampleHolder.getPresentationTime();
            this.mLastSubmittedCodecData = sampleHolder.getCodecData();
            this.mHasReadEndOfStream = sampleHolder.getIsLastInStream();
        }
    }

    /* JADX INFO: renamed from: com.amazon.avod.media.playback.source.BufferingMediaSourceAdapter$1, reason: invalid class name */
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
            z = this.mBuffer.remaining() >= this.mMaxSampleSizeBytes;
        }
        return z;
    }

    @Override // com.amazon.avod.media.playback.source.MediaSource
    public long getCapacityBytes() {
        return this.mBuffer.capacity();
    }

    @Override // com.amazon.avod.media.playback.source.MediaSource
    public void dispose() {
        synchronized (this.mMutex) {
            this.mBuffer = null;
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
    public long getBufferedBytes() {
        long jPosition;
        synchronized (this.mMutex) {
            jPosition = this.mBuffer.position();
        }
        return jPosition;
    }

    @Override // com.amazon.avod.media.playback.source.MediaSource
    public void flush() {
        synchronized (this.mMutex) {
            this.mSampleQueue.clear();
            this.mBuffer.clear();
            this.mHasReadEndOfStream = false;
            this.mLastSubmittedTimeInNanoseconds = -1L;
            this.mLastSubmittedCodecData = null;
        }
    }

    @Override // com.amazon.avod.media.playback.source.MediaSource
    public boolean hasNext() {
        boolean z;
        synchronized (this.mMutex) {
            z = !this.mSampleQueue.isEmpty();
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
    public SampleMetadata readSampleData(@Nonnull ByteBuffer byteBuffer) {
        SampleMetadata sampleMetadataElement;
        synchronized (this.mMutex) {
            sampleMetadataElement = this.mSampleQueue.element();
            byteBuffer.clear();
            this.mBuffer.get(byteBuffer, sampleMetadataElement.getSize());
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
