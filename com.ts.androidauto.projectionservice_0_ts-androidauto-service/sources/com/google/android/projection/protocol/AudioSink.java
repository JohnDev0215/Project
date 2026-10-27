package com.google.android.projection.protocol;

import com.google.android.projection.proto.Protos;

/* JADX INFO: loaded from: classes.dex */
public class AudioSink implements CarServiceProvider, AudioSinkCallbacks {
    private static final int MAX_UNACKED_FRAMES = 50;
    private final AudioListener mAudioListener;
    private long mNativeAudioSink;

    public interface AudioListener {
        void onAudioData(int i, long j, byte[] bArr);

        void onCodecConfig(byte[] bArr);

        void onSetUp(int i);

        void onShutdown();

        void onStart(int i);

        void onStop(int i);
    }

    private native void nativeAckFrames(int i, int i2);

    private native void nativeAckFramesNew(int i, long[] jArr);

    private native void nativeAddSupportedConfiguration(int i, int i2, int i3);

    private native int nativeInit(int i, long j) throws IllegalStateException;

    private native void nativeSendUnderflowNotification(int i);

    private native void nativeSetAudioType(int i);

    private native void nativeSetCodecType(int i);

    private native void nativeSetMaxUnackedFrames(int i);

    private native void nativeShutdown();

    public AudioSink(AudioListener listener) {
        this.mAudioListener = listener;
    }

    @Override // com.google.android.projection.protocol.CarServiceProvider
    public boolean create(int serviceId, long nativeGalReceiver) {
        boolean result = nativeInit(serviceId, nativeGalReceiver) == 0;
        nativeSetMaxUnackedFrames(50);
        return result;
    }

    @Override // com.google.android.projection.protocol.NativeObject
    public long getNativeInstance() {
        return this.mNativeAudioSink;
    }

    @Override // com.google.android.projection.protocol.NativeObject
    public void destroy() {
        this.mAudioListener.onShutdown();
        nativeShutdown();
    }

    public void ackFrames(int sessionId, int numFrames) {
        nativeAckFrames(sessionId, numFrames);
    }

    public void ackFrames(int sessionId, long[] receiveTimestampNs) {
        nativeAckFramesNew(sessionId, receiveTimestampNs);
    }

    public void sendUnderflowNotification(int sessionId) {
        nativeSendUnderflowNotification(sessionId);
    }

    public void addSupportedConfiguration(int samplingRate, int numberOfBits, int numberOfChannels) {
        nativeAddSupportedConfiguration(samplingRate, numberOfBits, numberOfChannels);
    }

    public void setCodecType(Protos.MediaCodecType codecType) {
        nativeSetCodecType(codecType.getNumber());
    }

    public void setAudioType(Protos.AudioStreamType audioType) {
        nativeSetAudioType(audioType.getNumber());
    }

    @Override // com.google.android.projection.protocol.AudioSinkCallbacks
    public void dataAvailableCallback(int sessionId, long timestamp, byte[] data) {
        this.mAudioListener.onAudioData(sessionId, timestamp, data);
    }

    @Override // com.google.android.projection.protocol.AudioSinkCallbacks
    public int codecConfigCallback(byte[] data) {
        this.mAudioListener.onCodecConfig(data);
        return 0;
    }

    @Override // com.google.android.projection.protocol.AudioSinkCallbacks
    public int setupCallback(int mediaCodecType) {
        this.mAudioListener.onSetUp(mediaCodecType);
        return 0;
    }

    @Override // com.google.android.projection.protocol.AudioSinkCallbacks
    public void playbackStartCallback(int sessionId) {
        this.mAudioListener.onStart(sessionId);
    }

    @Override // com.google.android.projection.protocol.AudioSinkCallbacks
    public void playbackStopCallback(int sessionId) {
        this.mAudioListener.onStop(sessionId);
    }
}
