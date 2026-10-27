package com.amazon.avod.playback.renderer.shared;

import com.amazon.avod.playback.PlaybackException;
import com.amazon.avod.util.DLog;
import com.google.common.base.Preconditions;
import java.nio.ByteBuffer;
import java.util.concurrent.Semaphore;

/* JADX INFO: loaded from: classes2.dex */
public abstract class NativeRendererJniBase {
    private static final Semaphore SINGLE_ACCESS_SEMAPHORE = new Semaphore(1, true);

    public abstract ByteBuffer allocateCyclicAudioBuffer(int i) throws OutOfMemoryError;

    public abstract ByteBuffer allocateCyclicVideoBuffer(int i) throws OutOfMemoryError;

    protected abstract int createRenderer(long j, long j2, int i, int i2, boolean z) throws PlaybackException, OutOfMemoryError;

    public abstract void flushRenderer() throws PlaybackException;

    protected abstract String getExpectedLibraryVersion();

    protected abstract String getNativeCodeCompileTime();

    protected abstract String getNativeLibraryVersion();

    public abstract ByteBuffer getStatisticsBuffer();

    public abstract int getWritableAudioRegion(int i) throws PlaybackException;

    public abstract int getWritableVideoRegion(int i) throws PlaybackException;

    public abstract void pauseRenderer() throws PlaybackException;

    public abstract void queueSample(boolean z, int i, int i2, int i3, int i4, long j, long j2, int i5) throws PlaybackException;

    public abstract void queueToSampleMetaDataList(SampleMetadata sampleMetadata, boolean z, int i, int i2, int i3, long j, long j2, int i4) throws PlaybackException;

    public abstract void resumeRenderer() throws PlaybackException;

    public abstract void setDrmHeader(byte[] bArr) throws PlaybackException, OutOfMemoryError;

    public abstract void setEndOfStream();

    public abstract void setVideoRegion(int i, int i2, int i3, int i4) throws PlaybackException;

    protected abstract void terminateRenderer();

    public abstract void waitForSetDrmHeader() throws PlaybackException;

    public static boolean acquireOwnership() {
        try {
            SINGLE_ACCESS_SEMAPHORE.acquire();
            return true;
        } catch (InterruptedException e) {
            DLog.exceptionf(e, "Acquiring the single-access semaphore", new Object[0]);
            Thread.currentThread().interrupt();
            return false;
        }
    }

    public static void releaseOwnership() {
        SINGLE_ACCESS_SEMAPHORE.release();
    }

    protected void checkNativeLibraryVersion(String str) {
        String nativeLibraryVersion = getNativeLibraryVersion();
        String expectedLibraryVersion = getExpectedLibraryVersion();
        DLog.logf("%s library: version %s, compile time %s", str, nativeLibraryVersion, getNativeCodeCompileTime());
        Preconditions.checkState(nativeLibraryVersion.equals(expectedLibraryVersion), "FATAL DEPLOYMENT ERROR: This AIV app is built to run with version %s of the lib%s.so library, but version %s was found on this device", expectedLibraryVersion, str, nativeLibraryVersion);
    }
}
