package com.amazon.digitalmusicplayback.internal;

import com.amazon.digitalmusicplayback.AudioFormat;
import com.amazon.digitalmusicplayback.OutputDeviceAttributes;
import java.util.ArrayList;
import java.util.concurrent.atomic.AtomicBoolean;

/* JADX INFO: loaded from: classes2.dex */
public abstract class AudioDriverEventsListener {
    public abstract void audioDriverFormatChanged(ArrayList<AudioFormat> arrayList);

    public abstract void audioDriverMetric(String str, String str2, String str3, String str4, Double d, Double d2, Double d3, Double d4);

    public abstract void audioDriverOutputDeviceChanged(OutputDeviceAttributes outputDeviceAttributes);

    public abstract void audioDriverPlaybackStalled(boolean z);

    public abstract void audioDriverSessionInterrupted(boolean z);

    public abstract void audioDriverVolumeChanged(float f);

    private static final class CppProxy extends AudioDriverEventsListener {
        static final /* synthetic */ boolean $assertionsDisabled = false;
        private final AtomicBoolean destroyed = new AtomicBoolean(false);
        private final long nativeRef;

        private native void nativeDestroy(long j);

        private native void native_audioDriverFormatChanged(long j, ArrayList<AudioFormat> arrayList);

        private native void native_audioDriverMetric(long j, String str, String str2, String str3, String str4, Double d, Double d2, Double d3, Double d4);

        private native void native_audioDriverOutputDeviceChanged(long j, OutputDeviceAttributes outputDeviceAttributes);

        private native void native_audioDriverPlaybackStalled(long j, boolean z);

        private native void native_audioDriverSessionInterrupted(long j, boolean z);

        private native void native_audioDriverVolumeChanged(long j, float f);

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

        @Override // com.amazon.digitalmusicplayback.internal.AudioDriverEventsListener
        public void audioDriverVolumeChanged(float f) {
            native_audioDriverVolumeChanged(this.nativeRef, f);
        }

        @Override // com.amazon.digitalmusicplayback.internal.AudioDriverEventsListener
        public void audioDriverSessionInterrupted(boolean z) {
            native_audioDriverSessionInterrupted(this.nativeRef, z);
        }

        @Override // com.amazon.digitalmusicplayback.internal.AudioDriverEventsListener
        public void audioDriverFormatChanged(ArrayList<AudioFormat> arrayList) {
            native_audioDriverFormatChanged(this.nativeRef, arrayList);
        }

        @Override // com.amazon.digitalmusicplayback.internal.AudioDriverEventsListener
        public void audioDriverOutputDeviceChanged(OutputDeviceAttributes outputDeviceAttributes) {
            native_audioDriverOutputDeviceChanged(this.nativeRef, outputDeviceAttributes);
        }

        @Override // com.amazon.digitalmusicplayback.internal.AudioDriverEventsListener
        public void audioDriverPlaybackStalled(boolean z) {
            native_audioDriverPlaybackStalled(this.nativeRef, z);
        }

        @Override // com.amazon.digitalmusicplayback.internal.AudioDriverEventsListener
        public void audioDriverMetric(String str, String str2, String str3, String str4, Double d, Double d2, Double d3, Double d4) {
            native_audioDriverMetric(this.nativeRef, str, str2, str3, str4, d, d2, d3, d4);
        }
    }
}
