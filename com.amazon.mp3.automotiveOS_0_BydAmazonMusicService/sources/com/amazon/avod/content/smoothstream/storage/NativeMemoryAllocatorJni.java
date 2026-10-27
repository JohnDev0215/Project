package com.amazon.avod.content.smoothstream.storage;

import com.amazon.avod.content.ContentException;
import com.amazon.avod.media.framework.error.MediaException;
import com.amazon.avod.media.framework.libraries.PlaybackNativeLibrariesLoader;
import com.amazon.avod.util.DLog;
import com.google.common.base.Preconditions;
import java.nio.ByteBuffer;
import java.util.concurrent.atomic.AtomicInteger;
import javax.annotation.Nonnull;

/* JADX INFO: loaded from: classes2.dex */
public class NativeMemoryAllocatorJni implements MemoryAllocator {
    private static String EXPECTED_LIBRARY_VERSION = "2.5";
    private final AtomicInteger mHeapVersion = new AtomicInteger(0);
    private final PlaybackNativeLibrariesLoader mLibraryLoader;
    private long mNativeContext;

    private static native String getNativeCodeCompileTime();

    private static native String getNativeLibraryVersion();

    private static native void initNativeLibrary() throws MediaException;

    private native void initialize(int i, float f, int i2, int i3, boolean z) throws MediaException;

    private native void release() throws MediaException;

    @Override // com.amazon.avod.content.smoothstream.storage.MemoryAllocator
    public native long allocate(int i) throws MediaException;

    public native int debugCheckAllocator(boolean z);

    @Override // com.amazon.avod.content.smoothstream.storage.MemoryAllocator
    public native void free(long j) throws MediaException;

    @Override // com.amazon.avod.content.smoothstream.storage.MemoryAllocator
    public native int getAvailableSizeInBytes() throws MediaException;

    @Override // com.amazon.avod.content.smoothstream.storage.MemoryAllocator
    public native int getHeapAllocatedSizeInBytes() throws MediaException;

    @Override // com.amazon.avod.content.smoothstream.storage.MemoryAllocator
    public native int getHeapUsedSizeInBytes() throws MediaException;

    @Override // com.amazon.avod.content.smoothstream.storage.MemoryAllocator
    public native ByteBuffer map(long j) throws MediaException;

    @Override // com.amazon.avod.content.smoothstream.storage.MemoryAllocator
    public native void unmap(ByteBuffer byteBuffer) throws MediaException;

    public NativeMemoryAllocatorJni(@Nonnull PlaybackNativeLibrariesLoader playbackNativeLibrariesLoader) {
        this.mLibraryLoader = (PlaybackNativeLibrariesLoader) Preconditions.checkNotNull(playbackNativeLibrariesLoader);
    }

    @Override // com.amazon.avod.content.smoothstream.storage.MemoryAllocator
    public void begin(int i, float f, int i2, int i3, boolean z) throws MediaException {
        this.mHeapVersion.incrementAndGet();
        if (!this.mLibraryLoader.waitForInitialization()) {
            throw new ContentException(ContentException.ContentError.ERROR_LOADING_NATIVE_LIBRARIES, "Failed to load the playback native libraries!");
        }
        try {
            initNativeLibrary();
        } catch (MediaException e) {
            DLog.errorf("Native library initialization failed with: %s", e);
        }
        String nativeLibraryVersion = getNativeLibraryVersion();
        DLog.logf("%s library: version %s, compile time %s", "AIVMemoryAllocator", nativeLibraryVersion, getNativeCodeCompileTime());
        Preconditions.checkState(nativeLibraryVersion.equals(EXPECTED_LIBRARY_VERSION), "FATAL DEPLOYMENT ERROR: This AIV app is built to run with version %s of the lib%s.so library, but version %s was found on this device", EXPECTED_LIBRARY_VERSION, "AIVMemoryAllocator", nativeLibraryVersion);
        initialize(i, f, i2, i3, z);
    }

    @Override // com.amazon.avod.content.smoothstream.storage.MemoryAllocator
    public void end() throws MediaException {
        this.mHeapVersion.incrementAndGet();
        debugCheckAllocator(true);
        release();
    }

    @Override // com.amazon.avod.content.smoothstream.storage.MemoryAllocator
    public int getHeapVersion() {
        return this.mHeapVersion.get();
    }
}
