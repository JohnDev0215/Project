package com.google.android.projection.protocol;

import com.google.android.projection.proto.Protos;

/* JADX INFO: loaded from: classes.dex */
public class AudioSource implements CarServiceProvider, AudioSourceCallbacks {
    private final AudioSourceListener mAudioSourceListener;
    private final Protos.MediaCodecType mMediaCodecType;
    private long mNativeAudioSource;
    private volatile boolean mShuttingDown = false;

    public interface AudioSourceListener {
        void ackCallback(int i, int i2);

        int microphoneRequestCallback(boolean z, boolean z2, boolean z3, int i);
    }

    private native int nativeInit(int i, long j, int i2) throws IllegalStateException;

    private native void nativeSend(long j, byte[] bArr);

    private native void nativeSetConfig(int i, int i2, int i3);

    private native void nativeShutdown();

    public AudioSource(Protos.MediaCodecType mediaCodecType, AudioSourceListener audioSourceListener) {
        this.mMediaCodecType = mediaCodecType;
        this.mAudioSourceListener = audioSourceListener;
    }

    @Override // com.google.android.projection.protocol.NativeObject
    public long getNativeInstance() {
        return this.mNativeAudioSource;
    }

    @Override // com.google.android.projection.protocol.NativeObject
    public void destroy() {
        this.mShuttingDown = true;
        nativeShutdown();
    }

    @Override // com.google.android.projection.protocol.CarServiceProvider
    public boolean create(int serviceId, long nativeGalReceiver) {
        return nativeInit(serviceId, nativeGalReceiver, this.mMediaCodecType.getNumber()) == 0;
    }

    public void send(long timestamp, byte[] data) {
        if (this.mShuttingDown) {
            return;
        }
        nativeSend(timestamp, data);
    }

    @Override // com.google.android.projection.protocol.AudioSourceCallbacks
    public void ackCallback(int sessionId, int ack) {
        this.mAudioSourceListener.ackCallback(sessionId, ack);
    }

    @Override // com.google.android.projection.protocol.AudioSourceCallbacks
    public int microphoneRequestCallback(boolean open, boolean ancEnabled, boolean ecEnabled, int maxUnacked) {
        return this.mAudioSourceListener.microphoneRequestCallback(open, ancEnabled, ecEnabled, maxUnacked);
    }

    public void setConfig(int samplingRate, int numberOfBits, int numberOfChannels) {
        nativeSetConfig(samplingRate, numberOfBits, numberOfChannels);
    }
}
