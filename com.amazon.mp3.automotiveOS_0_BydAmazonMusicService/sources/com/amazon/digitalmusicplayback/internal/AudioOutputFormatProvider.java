package com.amazon.digitalmusicplayback.internal;

import com.amazon.digitalmusicplayback.AudioFormat;
import java.util.concurrent.atomic.AtomicBoolean;

/* JADX INFO: loaded from: classes2.dex */
public abstract class AudioOutputFormatProvider {
    public abstract AudioFormat getAudioOutputFormat();

    public abstract int getOptimalBufferSize(AudioFormat audioFormat);

    public abstract boolean isBuiltInOutput();

    private static final class CppProxy extends AudioOutputFormatProvider {
        static final /* synthetic */ boolean $assertionsDisabled = false;
        private final AtomicBoolean destroyed = new AtomicBoolean(false);
        private final long nativeRef;

        private native void nativeDestroy(long j);

        private native AudioFormat native_getAudioOutputFormat(long j);

        private native int native_getOptimalBufferSize(long j, AudioFormat audioFormat);

        private native boolean native_isBuiltInOutput(long j);

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

        @Override // com.amazon.digitalmusicplayback.internal.AudioOutputFormatProvider
        public AudioFormat getAudioOutputFormat() {
            return native_getAudioOutputFormat(this.nativeRef);
        }

        @Override // com.amazon.digitalmusicplayback.internal.AudioOutputFormatProvider
        public int getOptimalBufferSize(AudioFormat audioFormat) {
            return native_getOptimalBufferSize(this.nativeRef, audioFormat);
        }

        @Override // com.amazon.digitalmusicplayback.internal.AudioOutputFormatProvider
        public boolean isBuiltInOutput() {
            return native_isBuiltInOutput(this.nativeRef);
        }
    }
}
