package com.amazon.avod.smoothstream.dash;

import com.amazon.avod.content.ContentException;
import com.amazon.avod.content.smoothstream.EventMessage;
import com.amazon.avod.content.smoothstream.Fragment;
import com.amazon.avod.media.framework.libraries.PlaybackNativeLibrariesLoader;
import com.amazon.avod.media.framework.memory.DirectBuffer;
import com.amazon.avod.playback.drm.EncryptedBufferRegion;
import com.amazon.avod.playback.sampling.SampleEncryptionInfo;
import com.amazon.avod.util.DLog;
import com.google.common.base.Preconditions;
import java.nio.ByteBuffer;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.annotation.concurrent.ThreadSafe;
import kotlin.UShort;

/* JADX INFO: loaded from: classes2.dex */
@ThreadSafe
public class Mp4FragmentJni {
    private final PlaybackNativeLibrariesLoader mLibraryLoader;
    private boolean mInitialized = false;
    private final Object mSyncObject = new Object();

    @Nullable
    private static native String getCodecPrivateData(ByteBuffer byteBuffer, int i, boolean z, int i2);

    @Nullable
    private static native EventMessage[] getEventMessages(ByteBuffer byteBuffer, int i, int i2);

    private static native long getFragmentPresentationTime(ByteBuffer byteBuffer, int i, int i2);

    private static native String getNativeCodeCompileTime();

    private static native String getNativeLibraryVersion();

    private static native long getPresentationTime(ByteBuffer byteBuffer, int i, int i2, int i3);

    @Nullable
    private static native ByteBuffer getPssh(ByteBuffer byteBuffer, int i, boolean z, int i2);

    private static native int getSampleCount(ByteBuffer byteBuffer, int i, int i2);

    @Nullable
    private static native ByteBuffer getSampleData(ByteBuffer byteBuffer, int i, int i2, int i3);

    private static native long getSampleDuration(ByteBuffer byteBuffer, int i, int i2, int i3);

    @Nullable
    private static native ByteBuffer getSampleEncryptionInfo(ByteBuffer byteBuffer, int i, int i2, int i3);

    private static native int getSampleSize(ByteBuffer byteBuffer, int i, int i2, int i3);

    private static native boolean isSampleRandomAccessPoint(ByteBuffer byteBuffer, int i, int i2, int i3);

    private static native void parse(ByteBuffer byteBuffer, int i, boolean z, int i2) throws ContentException;

    public void release() {
    }

    public void releaseFragment() {
    }

    public Mp4FragmentJni(@Nonnull PlaybackNativeLibrariesLoader playbackNativeLibrariesLoader) {
        this.mLibraryLoader = (PlaybackNativeLibrariesLoader) Preconditions.checkNotNull(playbackNativeLibrariesLoader);
    }

    public Fragment parse(@Nonnull DirectBuffer directBuffer, boolean z, int i, boolean z2) throws ContentException {
        DashFragment dashFragmentInitialize;
        Preconditions.checkNotNull(directBuffer, "DirectBuffer can not be null");
        synchronized (this.mSyncObject) {
            if (!this.mInitialized) {
                initialize();
            }
            ByteBuffer byteBufferForReading = directBuffer.getByteBufferForReading();
            parse(byteBufferForReading, byteBufferForReading.limit(), z, i);
            dashFragmentInitialize = new DashFragment(this, byteBufferForReading, z, i, directBuffer, z2).initialize();
        }
        return dashFragmentInitialize;
    }

    public Fragment parse(@Nonnull ByteBuffer byteBuffer, boolean z, int i, boolean z2) throws ContentException {
        DashFragment dashFragmentInitialize;
        Preconditions.checkNotNull(byteBuffer, "ByteBuffer can not be null");
        synchronized (this.mSyncObject) {
            if (!this.mInitialized) {
                initialize();
            }
            parse(byteBuffer, byteBuffer.limit(), z, i);
            dashFragmentInitialize = new DashFragment(this, byteBuffer, z, i, null, z2).initialize();
        }
        return dashFragmentInitialize;
    }

    public int getSampleCount(@Nonnull ByteBuffer byteBuffer, int i) {
        int sampleCount;
        synchronized (this.mSyncObject) {
            Preconditions.checkNotNull(byteBuffer, "Need to call parse function first.");
            sampleCount = getSampleCount(byteBuffer, byteBuffer.limit(), i);
        }
        return sampleCount;
    }

    public int getSampleSize(@Nonnull ByteBuffer byteBuffer, int i, int i2) {
        int sampleSize;
        synchronized (this.mSyncObject) {
            Preconditions.checkNotNull(byteBuffer, "Need to call parse function first.");
            sampleSize = getSampleSize(byteBuffer, byteBuffer.limit(), i, i2);
        }
        return sampleSize;
    }

    public long getSampleDuration(@Nonnull ByteBuffer byteBuffer, int i, int i2) {
        long sampleDuration;
        synchronized (this.mSyncObject) {
            Preconditions.checkNotNull(byteBuffer, "Need to call parse function first.");
            sampleDuration = getSampleDuration(byteBuffer, byteBuffer.limit(), i, i2);
        }
        return sampleDuration;
    }

    public long getFragmentPresentationTime(@Nonnull ByteBuffer byteBuffer, int i) {
        long fragmentPresentationTime;
        synchronized (this.mSyncObject) {
            Preconditions.checkNotNull(byteBuffer, "Need to call parse function first.");
            fragmentPresentationTime = getFragmentPresentationTime(byteBuffer, byteBuffer.limit(), i);
        }
        return fragmentPresentationTime;
    }

    public long getPresentationTime(@Nonnull ByteBuffer byteBuffer, int i, int i2) {
        long presentationTime;
        synchronized (this.mSyncObject) {
            Preconditions.checkNotNull(byteBuffer, "Need to call parse function first.");
            presentationTime = getPresentationTime(byteBuffer, byteBuffer.limit(), i, i2);
        }
        return presentationTime;
    }

    @Nullable
    public ByteBuffer getSampleData(@Nonnull ByteBuffer byteBuffer, int i, int i2) {
        ByteBuffer sampleData;
        synchronized (this.mSyncObject) {
            Preconditions.checkNotNull(byteBuffer, "Need to call parse function first.");
            sampleData = getSampleData(byteBuffer, byteBuffer.limit(), i, i2);
        }
        return sampleData;
    }

    @Nullable
    public SampleEncryptionInfo getEncryptionInfo(@Nonnull ByteBuffer byteBuffer, int i, int i2) {
        SampleEncryptionInfo sampleEncryptionInfoCreateSampleEncryptionInfo;
        synchronized (this.mSyncObject) {
            Preconditions.checkNotNull(byteBuffer, "Need to call parse function first.");
            ByteBuffer sampleEncryptionInfo = getSampleEncryptionInfo(byteBuffer, byteBuffer.limit(), i, i2);
            sampleEncryptionInfoCreateSampleEncryptionInfo = sampleEncryptionInfo == null ? null : createSampleEncryptionInfo(sampleEncryptionInfo);
        }
        return sampleEncryptionInfoCreateSampleEncryptionInfo;
    }

    @Nullable
    public ByteBuffer getPssh(@Nonnull ByteBuffer byteBuffer, boolean z, int i) {
        ByteBuffer pssh;
        synchronized (this.mSyncObject) {
            Preconditions.checkNotNull(byteBuffer, "Need to call parse function first.");
            pssh = getPssh(byteBuffer, byteBuffer.limit(), z, i);
        }
        return pssh;
    }

    @Nullable
    public String getCodecPrivateData(@Nonnull ByteBuffer byteBuffer, boolean z, int i) {
        String codecPrivateData;
        synchronized (this.mSyncObject) {
            Preconditions.checkNotNull(byteBuffer, "Need to call parse function first.");
            codecPrivateData = getCodecPrivateData(byteBuffer, byteBuffer.limit(), z, i | 1);
        }
        return codecPrivateData;
    }

    @Nonnull
    public Collection<EventMessage> getEventMessages(@Nonnull ByteBuffer byteBuffer, int i) {
        synchronized (this.mSyncObject) {
            Preconditions.checkNotNull(byteBuffer, "Need to call parse function first.");
            EventMessage[] eventMessages = getEventMessages(byteBuffer, byteBuffer.limit(), i);
            if (eventMessages == null) {
                return Collections.emptyList();
            }
            return Arrays.asList(eventMessages);
        }
    }

    private void initialize() throws ContentException {
        if (!this.mLibraryLoader.waitForInitialization()) {
            throw new ContentException(ContentException.ContentError.ERROR_LOADING_NATIVE_LIBRARIES, "Failed to load the playback native libraries!");
        }
        String nativeLibraryVersion = getNativeLibraryVersion();
        DLog.logf("%s library: version %s, compile time %s", "AIVDash", nativeLibraryVersion, getNativeCodeCompileTime());
        Preconditions.checkState(nativeLibraryVersion.equals("1.8"), "FATAL DEPLOYMENT ERROR: This AIV app is built to run with version %s of the lib%s.so library, but version %s was found on this device", "1.8", "AIVDash", nativeLibraryVersion);
        this.mInitialized = true;
    }

    public static ByteBuffer getDirectByteBuffer(ByteBuffer byteBuffer) {
        if (byteBuffer == null || byteBuffer.isDirect()) {
            return byteBuffer;
        }
        ByteBuffer byteBufferAllocateDirect = ByteBuffer.allocateDirect(byteBuffer.capacity());
        byteBufferAllocateDirect.put(byteBuffer);
        return byteBufferAllocateDirect;
    }

    private static SampleEncryptionInfo createSampleEncryptionInfo(ByteBuffer byteBuffer) {
        byteBuffer.rewind();
        int iCapacity = byteBuffer.capacity();
        byte[] bArr = new byte[8];
        EncryptedBufferRegion[] encryptedBufferRegionArr = new EncryptedBufferRegion[0];
        byteBuffer.get(bArr);
        for (int i = 0; i < 4; i++) {
            byte b = bArr[i];
            int i2 = (8 - i) - 1;
            bArr[i] = bArr[i2];
            bArr[i2] = b;
        }
        if (iCapacity > 8) {
            int i3 = byteBuffer.getShort();
            encryptedBufferRegionArr = new EncryptedBufferRegion[i3];
            int i4 = 0;
            for (int i5 = 0; i5 < i3; i5++) {
                int i6 = byteBuffer.getShort() & UShort.MAX_VALUE;
                int i7 = byteBuffer.getInt();
                int i8 = i4 + i6;
                encryptedBufferRegionArr[i5] = new EncryptedBufferRegion(i8, i7);
                i4 = i8 + i7;
            }
        }
        return new SampleEncryptionInfo(encryptedBufferRegionArr, bArr, 8);
    }
}
