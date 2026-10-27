package com.amazon.digitalmusicplayback.internal;

import com.amazon.digitalmusicplayback.AudioFormat;
import com.amazon.digitalmusicplayback.AudioSampleFormat;
import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicBoolean;

/* JADX INFO: loaded from: classes2.dex */
public abstract class AudioDataSource {
    public static final long NEED_DIFFERENT_FORMAT = -1;

    public abstract long fillAudioDataBuffer(long j, long j2, AudioSampleFormat audioSampleFormat, int i, long j3, long j4);

    public abstract int selectAudioDataFormat(ArrayList<AudioFormat> arrayList);

    private static final class CppProxy extends AudioDataSource {
        static final /* synthetic */ boolean $assertionsDisabled = false;
        private final AtomicBoolean destroyed = new AtomicBoolean(false);
        private final long nativeRef;

        private native void nativeDestroy(long j);

        private native long native_fillAudioDataBuffer(long j, long j2, long j3, AudioSampleFormat audioSampleFormat, int i, long j4, long j5);

        private native int native_selectAudioDataFormat(long j, ArrayList<AudioFormat> arrayList);

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

        @Override // com.amazon.digitalmusicplayback.internal.AudioDataSource
        public int selectAudioDataFormat(ArrayList<AudioFormat> arrayList) {
            return native_selectAudioDataFormat(this.nativeRef, arrayList);
        }

        @Override // com.amazon.digitalmusicplayback.internal.AudioDataSource
        public long fillAudioDataBuffer(long j, long j2, AudioSampleFormat audioSampleFormat, int i, long j3, long j4) {
            return native_fillAudioDataBuffer(this.nativeRef, j, j2, audioSampleFormat, i, j3, j4);
        }
    }
}
