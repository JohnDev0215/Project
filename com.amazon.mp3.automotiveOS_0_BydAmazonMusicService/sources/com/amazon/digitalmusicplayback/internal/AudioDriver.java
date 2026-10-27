package com.amazon.digitalmusicplayback.internal;

import com.amazon.digitalmusicplayback.AudioFormat;
import com.amazon.digitalmusicplayback.ErrorCode;
import com.amazon.digitalmusicplayback.OutputDeviceAttributes;
import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicBoolean;

/* JADX INFO: loaded from: classes2.dex */
public abstract class AudioDriver {
    public abstract OutputDeviceAttributes getOutputDeviceAttributes();

    public abstract ArrayList<AudioFormat> getSupportedFormats();

    public abstract double getVolume();

    public abstract ErrorCode init(AudioDataSource audioDataSource, AudioDriverEventsListener audioDriverEventsListener);

    public abstract boolean isHiResOutputEnabled();

    public abstract ErrorCode setExclusiveMode(boolean z);

    public abstract ErrorCode setHiResOutputEnabled(boolean z);

    public abstract ErrorCode setOutputDevice(String str);

    public abstract ErrorCode setVolume(double d);

    public abstract ErrorCode start();

    public abstract ErrorCode stop();

    private static final class CppProxy extends AudioDriver {
        static final /* synthetic */ boolean $assertionsDisabled = false;
        private final AtomicBoolean destroyed = new AtomicBoolean(false);
        private final long nativeRef;

        private native void nativeDestroy(long j);

        private native OutputDeviceAttributes native_getOutputDeviceAttributes(long j);

        private native ArrayList<AudioFormat> native_getSupportedFormats(long j);

        private native double native_getVolume(long j);

        private native ErrorCode native_init(long j, AudioDataSource audioDataSource, AudioDriverEventsListener audioDriverEventsListener);

        private native boolean native_isHiResOutputEnabled(long j);

        private native ErrorCode native_setExclusiveMode(long j, boolean z);

        private native ErrorCode native_setHiResOutputEnabled(long j, boolean z);

        private native ErrorCode native_setOutputDevice(long j, String str);

        private native ErrorCode native_setVolume(long j, double d);

        private native ErrorCode native_start(long j);

        private native ErrorCode native_stop(long j);

        private CppProxy(long j) {
            if (j == 0) {
                throw new RuntimeException("nativeRef is zero");
            }
            this.nativeRef = j;
        }

        public void _djinni_private_destroy() {
            if (this.destroyed.getAndSet(true)) {
                return;
            }
            nativeDestroy(this.nativeRef);
        }

        protected void finalize() throws Throwable {
            _djinni_private_destroy();
            super.finalize();
        }

        @Override // com.amazon.digitalmusicplayback.internal.AudioDriver
        public ErrorCode init(AudioDataSource audioDataSource, AudioDriverEventsListener audioDriverEventsListener) {
            return native_init(this.nativeRef, audioDataSource, audioDriverEventsListener);
        }

        @Override // com.amazon.digitalmusicplayback.internal.AudioDriver
        public ErrorCode start() {
            return native_start(this.nativeRef);
        }

        @Override // com.amazon.digitalmusicplayback.internal.AudioDriver
        public ErrorCode stop() {
            return native_stop(this.nativeRef);
        }

        @Override // com.amazon.digitalmusicplayback.internal.AudioDriver
        public ArrayList<AudioFormat> getSupportedFormats() {
            return native_getSupportedFormats(this.nativeRef);
        }

        @Override // com.amazon.digitalmusicplayback.internal.AudioDriver
        public ErrorCode setVolume(double d) {
            return native_setVolume(this.nativeRef, d);
        }

        @Override // com.amazon.digitalmusicplayback.internal.AudioDriver
        public double getVolume() {
            return native_getVolume(this.nativeRef);
        }

        @Override // com.amazon.digitalmusicplayback.internal.AudioDriver
        public ErrorCode setOutputDevice(String str) {
            return native_setOutputDevice(this.nativeRef, str);
        }

        @Override // com.amazon.digitalmusicplayback.internal.AudioDriver
        public OutputDeviceAttributes getOutputDeviceAttributes() {
            return native_getOutputDeviceAttributes(this.nativeRef);
        }

        @Override // com.amazon.digitalmusicplayback.internal.AudioDriver
        public ErrorCode setExclusiveMode(boolean z) {
            return native_setExclusiveMode(this.nativeRef, z);
        }

        @Override // com.amazon.digitalmusicplayback.internal.AudioDriver
        public ErrorCode setHiResOutputEnabled(boolean z) {
            return native_setHiResOutputEnabled(this.nativeRef, z);
        }

        @Override // com.amazon.digitalmusicplayback.internal.AudioDriver
        public boolean isHiResOutputEnabled() {
            return native_isHiResOutputEnabled(this.nativeRef);
        }
    }
}
