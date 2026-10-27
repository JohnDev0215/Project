package com.amazon.avod.playback.renderer.tate;

import com.amazon.avod.media.playback.util.AudioTrackAdapter;
import com.amazon.avod.media.playback.util.TrackAdapter;
import com.amazon.avod.media.playback.util.TrackAdaptionStatus;
import com.amazon.avod.media.playback.util.VideoTrackAdapter;
import com.amazon.avod.playback.PlaybackException;
import com.amazon.avod.playback.renderer.shared.NativeRendererJniBase;
import com.amazon.avod.playback.renderer.shared.SampleMetadata;
import com.amazon.avod.util.DLog;
import com.google.common.base.Preconditions;
import com.visualon.OSMPUtils.voMimeTypes;
import java.nio.ByteBuffer;
import java.util.concurrent.LinkedBlockingDeque;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;

/* JADX INFO: loaded from: classes2.dex */
class OMXILRendererJni extends NativeRendererJniBase {
    private static final int BUFFER_ALIGNMENT = 4;
    private static final int END_OF_STREAM_FLAG = 1;
    private static final int FLAGS_NONE = 0;
    private int mAudioBufferRef;
    private AudioTrackAdapter mAudioTrackAdapater;
    private int mBehaviorFlags;
    private int mNativeContext;
    private int mStatisticsBufferRef;
    private final TrackAdaptionStatus mTrackAdaptionStatus = new TrackAdaptionStatus();
    private int mVideoBufferRef;
    private VideoTrackAdapter mVideoTrackAdapter;
    private static final Object mMutex = new Object();
    private static final LinkedBlockingDeque<SampleMetadata> mAudioSampleMetaDataQueue = new LinkedBlockingDeque<>();
    private static final LinkedBlockingDeque<SampleMetadata> mVideoSampleMetaDataQueue = new LinkedBlockingDeque<>();

    private static native void initNativeLibrary();

    private boolean isUseSampleMetadaListEnabled(int i) {
        return (i & 4) == 4;
    }

    @Override // com.amazon.avod.playback.renderer.shared.NativeRendererJniBase
    public native ByteBuffer allocateCyclicAudioBuffer(int i);

    @Override // com.amazon.avod.playback.renderer.shared.NativeRendererJniBase
    public native ByteBuffer allocateCyclicVideoBuffer(int i);

    public native int configureRenderer(int i, int i2, int i3, int i4, int i5, boolean z, int i6) throws OMXILPlaybackException;

    @Override // com.amazon.avod.playback.renderer.shared.NativeRendererJniBase
    public native int createRenderer(long j, long j2, int i, int i2, boolean z) throws OMXILPlaybackException;

    public native void flushOMXRenderer() throws OMXILPlaybackException;

    @Override // com.amazon.avod.playback.renderer.shared.NativeRendererJniBase
    protected String getExpectedLibraryVersion() {
        return "20";
    }

    @Override // com.amazon.avod.playback.renderer.shared.NativeRendererJniBase
    protected native String getNativeCodeCompileTime();

    @Override // com.amazon.avod.playback.renderer.shared.NativeRendererJniBase
    protected native String getNativeLibraryVersion();

    @Override // com.amazon.avod.playback.renderer.shared.NativeRendererJniBase
    public native ByteBuffer getStatisticsBuffer();

    @Override // com.amazon.avod.playback.renderer.shared.NativeRendererJniBase
    public native int getWritableAudioRegion(int i) throws OMXILPlaybackException;

    @Override // com.amazon.avod.playback.renderer.shared.NativeRendererJniBase
    public native int getWritableVideoRegion(int i) throws OMXILPlaybackException;

    @Override // com.amazon.avod.playback.renderer.shared.NativeRendererJniBase
    public native void pauseRenderer() throws OMXILPlaybackException;

    @Override // com.amazon.avod.playback.renderer.shared.NativeRendererJniBase
    public native void resumeRenderer() throws OMXILPlaybackException;

    @Override // com.amazon.avod.playback.renderer.shared.NativeRendererJniBase
    public native void setDrmHeader(byte[] bArr) throws OMXILPlaybackException;

    @Override // com.amazon.avod.playback.renderer.shared.NativeRendererJniBase
    public native void setEndOfStream();

    @Override // com.amazon.avod.playback.renderer.shared.NativeRendererJniBase
    public native void setVideoRegion(int i, int i2, int i3, int i4) throws OMXILPlaybackException;

    public native void submitSample(boolean z, int i, int i2, int i3, int i4, long j, long j2, int i5) throws OMXILPlaybackException;

    public native void submitToSampleMetaDataList(boolean z, int i, int i2, int i3, long j, long j2, int i4) throws OMXILPlaybackException;

    public native void terminateOMXRenderer();

    @Override // com.amazon.avod.playback.renderer.shared.NativeRendererJniBase
    public native void waitForSetDrmHeader() throws OMXILPlaybackException;

    OMXILRendererJni() {
    }

    private int copyToDecoderBuffer(ByteBuffer byteBuffer, boolean z) {
        SampleMetadata sampleMetadataPoll;
        synchronized (mMutex) {
            if (z) {
                sampleMetadataPoll = mVideoSampleMetaDataQueue.poll();
            } else {
                sampleMetadataPoll = mAudioSampleMetaDataQueue.poll();
            }
            if (sampleMetadataPoll == null) {
                DLog.logf("%s sampleMetadata queue is empty", z ? voMimeTypes.VOBASE_TYPE_VIDEO : voMimeTypes.VOBASE_TYPE_AUDIO);
                return 0;
            }
            byteBuffer.clear();
            byte[] codecData = sampleMetadataPoll.getCodecData();
            if (codecData != null) {
                byteBuffer.put(codecData);
                padBufferForAlignment(byteBuffer, codecData.length, 4);
            }
            TrackAdapter trackAdapter = z ? this.mVideoTrackAdapter : this.mAudioTrackAdapater;
            ByteBuffer sampleData = sampleMetadataPoll.getSampleData();
            if (sampleData != null) {
                sampleData.rewind();
                trackAdapter.writeAdaptedSample(sampleData, byteBuffer, sampleMetadataPoll.getSize(), this.mTrackAdaptionStatus);
                if (this.mTrackAdaptionStatus.getError() != null) {
                    return 0;
                }
            }
            ByteBuffer rawEncryptionMetaData = sampleMetadataPoll.getRawEncryptionMetaData();
            if (rawEncryptionMetaData != null) {
                padBufferForAlignment(byteBuffer, sampleMetadataPoll.getSize(), 4);
                byteBuffer.put(rawEncryptionMetaData.array(), 0, sampleMetadataPoll.getRawEncryptionMetaDataLength());
            }
            sampleMetadataPoll.clearSampledata();
            int iPosition = byteBuffer.position();
            byteBuffer.clear();
            return iPosition;
        }
    }

    private void padBufferForAlignment(ByteBuffer byteBuffer, int i, int i2) {
        while (i % i2 != 0) {
            byteBuffer.put((byte) 0);
            i++;
        }
    }

    public void initialize(int i, @Nonnull VideoTrackAdapter videoTrackAdapter, @Nonnull AudioTrackAdapter audioTrackAdapter) throws PlaybackException {
        try {
            initNativeLibrary();
            this.mBehaviorFlags = i;
            checkNativeLibraryVersion(OMXILNativeLibrary.NATIVE_RENDERER_LIBRARY);
            this.mVideoTrackAdapter = (VideoTrackAdapter) Preconditions.checkNotNull(videoTrackAdapter, "videoTrackAdapter");
            this.mAudioTrackAdapater = (AudioTrackAdapter) Preconditions.checkNotNull(audioTrackAdapter, "audioTrackAdapter");
        } catch (UnsatisfiedLinkError e) {
            throw new PlaybackException(PlaybackException.PlaybackError.RENDERER_INITIALIZE_FAILED, "The native playback library (libAIVSecureRenderer.so) is missing or out of date. This is likely due to a build issue.", e);
        }
    }

    public void setBehaviorFlags(int i) {
        this.mBehaviorFlags = i;
    }

    @Override // com.amazon.avod.playback.renderer.shared.NativeRendererJniBase
    public void terminateRenderer() {
        if (isUseSampleMetadaListEnabled(this.mBehaviorFlags)) {
            flushSampleMetaDataQueue();
        }
        terminateOMXRenderer();
    }

    @Override // com.amazon.avod.playback.renderer.shared.NativeRendererJniBase
    public void flushRenderer() throws OMXILPlaybackException {
        if (isUseSampleMetadaListEnabled(this.mBehaviorFlags)) {
            flushSampleMetaDataQueue();
        }
        flushOMXRenderer();
    }

    @Override // com.amazon.avod.playback.renderer.shared.NativeRendererJniBase
    public void queueSample(boolean z, int i, int i2, int i3, int i4, long j, long j2, int i5) throws PlaybackException {
        if ((i4 & 1) == 0) {
            submitSample(z, i, i2, i3, i4, j, j2, i5);
        }
    }

    @Override // com.amazon.avod.playback.renderer.shared.NativeRendererJniBase
    public void queueToSampleMetaDataList(@Nullable SampleMetadata sampleMetadata, boolean z, int i, int i2, int i3, long j, long j2, int i4) throws PlaybackException {
        if ((i3 & 1) != 0 || sampleMetadata == null) {
            return;
        }
        if (z) {
            mVideoSampleMetaDataQueue.add(sampleMetadata);
        } else {
            mAudioSampleMetaDataQueue.add(sampleMetadata);
        }
        submitToSampleMetaDataList(z, i, i2, i3, j, j2, i4);
    }

    public void flushSampleMetaDataQueue() {
        synchronized (mMutex) {
            mVideoSampleMetaDataQueue.clear();
            mAudioSampleMetaDataQueue.clear();
        }
    }
}
