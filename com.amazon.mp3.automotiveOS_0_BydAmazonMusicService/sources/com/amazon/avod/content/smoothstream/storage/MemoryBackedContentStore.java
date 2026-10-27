package com.amazon.avod.content.smoothstream.storage;

import com.amazon.avod.content.ContentException;
import com.amazon.avod.content.ContentSessionContext;
import com.amazon.avod.content.ContentSessionType;
import com.amazon.avod.content.MediaComponentContentStore;
import com.amazon.avod.content.PlayableContent;
import com.amazon.avod.content.config.DynamicMemoryStoreConfig;
import com.amazon.avod.content.config.SmoothStreamingPlaybackConfig;
import com.amazon.avod.content.downloading.FragmentValidator;
import com.amazon.avod.content.event.ContentManagementEventBus;
import com.amazon.avod.content.event.ContentStoreEvent;
import com.amazon.avod.content.event.RetriableContentEventError;
import com.amazon.avod.content.smoothstream.SmoothStreamingURI;
import com.amazon.avod.content.smoothstream.manifest.QualityLevel;
import com.amazon.avod.content.smoothstream.manifest.StreamIndex;
import com.amazon.avod.content.smoothstream.storage.AllocationInfo;
import com.amazon.avod.fileio.DiskUtils;
import com.amazon.avod.media.framework.error.MediaException;
import com.amazon.avod.media.framework.resources.ResourcePool;
import com.amazon.avod.media.framework.resources.SurgingResourcePool;
import com.amazon.avod.media.playback.MediaDefaultConfiguration;
import com.amazon.avod.media.playback.internal.config.MediaQualityConfig;
import com.amazon.avod.playback.capability.DeviceResources;
import com.amazon.avod.playbackclient.config.PlaybackConfig;
import com.amazon.avod.pmet.ContentStoreType;
import com.amazon.avod.util.DLog;
import com.amazon.avod.util.DataUnit;
import com.amazon.avod.util.Preconditions2;
import com.amazon.avod.util.URLUtils;
import com.google.common.base.Optional;
import com.google.common.base.Preconditions;
import com.google.common.collect.Maps;
import java.io.File;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.util.Iterator;
import java.util.Locale;
import java.util.Map;
import java.util.Set;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import javax.annotation.concurrent.NotThreadSafe;
import javax.inject.Provider;

/* JADX INFO: loaded from: classes2.dex */
@NotThreadSafe
public class MemoryBackedContentStore implements MediaComponentContentStore {
    private final AllocationInfo mAllocationInfo;
    private final MemoryAllocator mAllocator;
    private boolean mCheckHeapVersion;
    private boolean mCheckThreadInterrupt;
    private final PlaybackConfig mConfig;
    private ContentStoreType mContentStoreType;
    private final Map<PlayableContent, Integer> mContentToSessionCountMap;
    private boolean mCopyStreamCheckContentActive;
    private final DeviceResources mDeviceResources;
    private final DynamicMemoryStoreConfig mDynamicMemoryStoreConfig;
    private final FileBackedContentStore mFileBackedStore;
    private final MediaDefaultConfiguration mMediaDefaultConfiguration;
    private volatile boolean mMemoryAllocated;
    private final SmoothStreamingPlaybackConfig mPlaybackConfig;
    private ResourcePool<byte[]> mReadBufferPool;
    private final Object mSyncObj;

    @Override // com.amazon.avod.media.framework.MediaComponent
    public void initialize() {
    }

    @Override // com.amazon.avod.content.MediaComponentContentStore
    public void releaseFragmentReferences(ContentSessionType contentSessionType, File file, PlayableContent playableContent, String str, SmoothStreamingURI smoothStreamingURI) {
    }

    public MemoryBackedContentStore(@Nonnull MemoryAllocator memoryAllocator, @Nonnull FileBackedContentStore fileBackedContentStore) {
        this(memoryAllocator, fileBackedContentStore, SmoothStreamingPlaybackConfig.INSTANCE, new AllocationInfo(), DeviceResources.getInstance(), MediaDefaultConfiguration.getInstance(), DynamicMemoryStoreConfig.INSTANCE, PlaybackConfig.getInstance());
    }

    MemoryBackedContentStore(@Nonnull MemoryAllocator memoryAllocator, @Nonnull FileBackedContentStore fileBackedContentStore, @Nonnull SmoothStreamingPlaybackConfig smoothStreamingPlaybackConfig, @Nonnull AllocationInfo allocationInfo, @Nonnull DeviceResources deviceResources, @Nonnull MediaDefaultConfiguration mediaDefaultConfiguration, @Nonnull DynamicMemoryStoreConfig dynamicMemoryStoreConfig, @Nonnull PlaybackConfig playbackConfig) {
        this.mSyncObj = new Object();
        this.mMemoryAllocated = false;
        this.mContentToSessionCountMap = Maps.newHashMap();
        this.mContentStoreType = ContentStoreType.MEMSTORE;
        this.mAllocator = (MemoryAllocator) Preconditions.checkNotNull(memoryAllocator, "memoryAllocator");
        this.mPlaybackConfig = (SmoothStreamingPlaybackConfig) Preconditions.checkNotNull(smoothStreamingPlaybackConfig, "smoothStreamingPlaybackConfig");
        this.mFileBackedStore = (FileBackedContentStore) Preconditions.checkNotNull(fileBackedContentStore, "fileBackedStore");
        this.mAllocationInfo = (AllocationInfo) Preconditions.checkNotNull(allocationInfo, "allocationInfo");
        this.mDeviceResources = (DeviceResources) Preconditions.checkNotNull(deviceResources, "deviceResources");
        this.mMediaDefaultConfiguration = (MediaDefaultConfiguration) Preconditions.checkNotNull(mediaDefaultConfiguration, "mediaDefaultConfiguration");
        this.mDynamicMemoryStoreConfig = (DynamicMemoryStoreConfig) Preconditions.checkNotNull(dynamicMemoryStoreConfig, "dynamicMemoryStoreConfig");
        this.mConfig = (PlaybackConfig) Preconditions.checkNotNull(playbackConfig, "playbackConfig");
    }

    @Override // com.amazon.avod.content.ContentStore
    public void releaseContent(ContentSessionType contentSessionType, File file, PlayableContent playableContent, @Nullable String str) {
        if (!this.mMemoryAllocated) {
            this.mFileBackedStore.releaseContent(contentSessionType, file, playableContent, str);
            return;
        }
        synchronized (this.mSyncObj) {
            if (str != null) {
                release(playableContent.hashCode(), str.hashCode());
            } else {
                release(playableContent.hashCode());
            }
        }
    }

    @Override // com.amazon.avod.content.ContentStore
    public void releaseContent(ContentSessionType contentSessionType, File file, PlayableContent playableContent, String str, Optional<DiskUtils.DeletionProgressListener> optional) {
        releaseContent(contentSessionType, file, playableContent, str);
    }

    @Override // com.amazon.avod.content.ContentStore
    public void deleteAllContent(ContentSessionType contentSessionType, File file) {
        if (this.mMemoryAllocated) {
            release(contentSessionType.hashCode());
        } else {
            this.mFileBackedStore.deleteAllContent(contentSessionType, file);
        }
    }

    @Override // com.amazon.avod.content.smoothstream.storage.SmoothStreamingContentStore
    public void begin(@Nonnull PlayableContent playableContent, @Nonnull ContentSessionType contentSessionType, @Nonnull ContentManagementEventBus contentManagementEventBus, @Nullable ContentSessionContext contentSessionContext) throws Throwable {
        int bytes;
        int bytes2;
        boolean z;
        int i;
        synchronized (this.mSyncObj) {
            try {
            } catch (Throwable th) {
                th = th;
            }
            try {
                DLog.logf("MemStore.begin() for content %s", playableContent);
                updateSessionCountForContent(playableContent, true);
                if (this.mMemoryAllocated) {
                    return;
                }
                this.mCheckHeapVersion = this.mDynamicMemoryStoreConfig.getCheckHeapVersion();
                this.mCheckThreadInterrupt = this.mDynamicMemoryStoreConfig.getCheckThreadInterrupt();
                this.mCopyStreamCheckContentActive = this.mDynamicMemoryStoreConfig.getCopyStreamCheckContentActive();
                if (this.mPlaybackConfig.shouldUseManifestContentStore() && contentSessionContext != null) {
                    DynamicMemoryStoreEstimator dynamicMemoryStoreEstimator = new DynamicMemoryStoreEstimator(contentSessionContext, MediaQualityConfig.INSTANCE, this.mPlaybackConfig, this.mDynamicMemoryStoreConfig, this.mConfig);
                    DataUnit dataUnit = DataUnit.MEGABYTES;
                    bytes = (int) dataUnit.toBytes(dynamicMemoryStoreEstimator.getNativeMemoryAllocationMinInMegabytes());
                    bytes2 = ((int) dataUnit.toBytes(this.mDynamicMemoryStoreConfig.getNativeMemoryAllocationGapBetweenMaxAndMinInMegabytes())) + bytes;
                } else {
                    DataUnit dataUnit2 = DataUnit.MEGABYTES;
                    int bytes3 = (int) dataUnit2.toBytes(this.mPlaybackConfig.getNativeMemoryAllocationMaxInMegabytes());
                    bytes = (int) dataUnit2.toBytes(this.mPlaybackConfig.getNativeMemoryAllocationMinInMegabytes());
                    bytes2 = bytes3;
                }
                int bytes4 = (int) DataUnit.MEGABYTES.toBytes(this.mPlaybackConfig.getNativeMemoryAllocationRetryFallbackInMegabytes());
                int heapSpillRatioInPercents = this.mPlaybackConfig.getHeapSpillRatioInPercents();
                float heapFragmentationHeadroomInPercents = this.mPlaybackConfig.getHeapFragmentationHeadroomInPercents() / 100.0f;
                boolean nativeMemoryAllocationReopenVramLibrary = this.mPlaybackConfig.getNativeMemoryAllocationReopenVramLibrary();
                Preconditions.checkState(bytes2 >= bytes, "The starting allocation size of %s is less than the minimum allocation size of %s", bytes2, bytes);
                Preconditions.checkState(bytes4 > 0, "The back-off size should be greater than 0");
                boolean zIsMemoryBackedContentStoreEnabled = this.mPlaybackConfig.isMemoryBackedContentStoreEnabled();
                if (bytes2 <= 0 || !zIsMemoryBackedContentStoreEnabled) {
                    z = true;
                    DLog.warnf("MemStore. In-memory store disabled. Using disk based content store instead.");
                    this.mContentStoreType = ContentStoreType.FILESTORE;
                    i = bytes2;
                } else {
                    i = bytes2;
                    boolean z2 = false;
                    while (!z2 && i >= bytes) {
                        try {
                            try {
                                int heapAllocatorFlags = this.mPlaybackConfig.getHeapAllocatorFlags();
                                DLog.logf("Memstore. Trying to initialize in-memory store with size %s with flags %s", Integer.valueOf(i), Integer.valueOf(heapAllocatorFlags));
                                this.mAllocator.begin(i, heapFragmentationHeadroomInPercents, heapSpillRatioInPercents, heapAllocatorFlags, nativeMemoryAllocationReopenVramLibrary);
                            } catch (OutOfMemoryError unused) {
                            }
                            try {
                                this.mDeviceResources.setNativeHeapMaxSizeInBytes(this.mAllocator.getHeapAllocatedSizeInBytes());
                                updateNativeAvailableSize();
                                z2 = true;
                            } catch (OutOfMemoryError unused2) {
                                z2 = true;
                                DLog.warnf("MemStore. Failed to initialize in-memory store with size %s.", Integer.valueOf(i));
                                i -= bytes4;
                            }
                        } catch (MediaException e) {
                            String str = String.format(Locale.US, "MemStore. Failed to initialize in-memory store with size %s", Integer.valueOf(i));
                            DLog.warnf(str);
                            throw new ContentException(ContentException.ContentError.LOW_MEMORY_ERROR, str, e);
                        }
                    }
                    if (z2) {
                        this.mReadBufferPool = new ReadBufferResourcePool(2);
                        this.mMemoryAllocated = true;
                        this.mContentStoreType = ContentStoreType.MEMSTORE;
                        z = true;
                    } else {
                        String str2 = String.format(Locale.US, "MemStore. Failed to allocate minimum required memory to initialize in-memory store. minSize:%s, memorySize:%s. Switching to file-backed content store.", Integer.valueOf(bytes), Integer.valueOf(i));
                        DLog.warnf(str2);
                        this.mContentStoreType = ContentStoreType.FILESTORE_FALLBACK;
                        z = true;
                        contentManagementEventBus.postEvent(new RetriableContentEventError(playableContent, contentSessionType, new ContentException(ContentException.ContentError.LOW_MEMORY_ERROR, str2), 1));
                    }
                }
                boolean z3 = z;
                contentManagementEventBus.postEvent(new ContentStoreEvent(playableContent, contentSessionType, this.mContentStoreType, i, bytes2, bytes, zIsMemoryBackedContentStoreEnabled));
                if (this.mContentStoreType == ContentStoreType.MEMSTORE) {
                    this.mMediaDefaultConfiguration.setIsMemStoreBasedRendererZeroMemCopyAllowed(z3);
                    return;
                }
                this.mMediaDefaultConfiguration.setIsMemStoreBasedRendererZeroMemCopyAllowed(false);
                this.mFileBackedStore.initialize();
                this.mFileBackedStore.begin(playableContent, contentSessionType, contentManagementEventBus, contentSessionContext);
            } catch (Throwable th2) {
                th = th2;
                throw th;
            }
        }
    }

    @Override // com.amazon.avod.content.smoothstream.storage.SmoothStreamingContentStore
    public void end(@Nonnull ContentSessionType contentSessionType, @Nonnull PlayableContent playableContent, @Nullable String str) throws ContentException {
        Preconditions.checkNotNull(contentSessionType, "sessionType");
        Preconditions.checkNotNull(playableContent, "content");
        synchronized (this.mSyncObj) {
            DLog.logf("MemStore.end() for content %s", playableContent);
            updateSessionCountForContent(playableContent, false);
            if (!this.mMemoryAllocated) {
                this.mFileBackedStore.end(contentSessionType, playableContent, str);
                return;
            }
            if (isContentActive(playableContent)) {
                DLog.logf("MemStore.end() skipping deallocation for content %s as active session/s exist.", playableContent);
                return;
            }
            if (str != null) {
                release(contentSessionType.hashCode(), playableContent.hashCode(), str.hashCode());
            } else {
                release(contentSessionType.hashCode(), playableContent.hashCode());
            }
            try {
                DLog.logf("MemStore.end() UsedSize/MaxSize: %d/%d", Integer.valueOf(this.mAllocator.getHeapUsedSizeInBytes()), Integer.valueOf(this.mAllocator.getHeapAllocatedSizeInBytes()));
            } catch (MediaException e) {
                DLog.exceptionf(e, "unable to access memory stats", new Object[0]);
            }
            int size = this.mContentToSessionCountMap.size();
            if (size > 0) {
                DLog.logf("MemStore.end() skipping heap release as %d active session/s exist.", Integer.valueOf(size));
                return;
            }
            DLog.logf("MemStore.end() freeing heap as no active sessions exist.");
            try {
                try {
                    this.mAllocator.end();
                } catch (MediaException e2) {
                    throw new ContentException(ContentException.ContentError.MEMORY_ACCESS_ERROR, e2);
                }
            } finally {
                this.mAllocationInfo.clear();
                this.mMemoryAllocated = false;
                this.mReadBufferPool = null;
                this.mMediaDefaultConfiguration.setIsMemStoreBasedRendererZeroMemCopyAllowed(false);
            }
        }
    }

    @Override // com.amazon.avod.content.smoothstream.storage.ManifestContentStore
    public boolean isManifestAvailable(ContentSessionType contentSessionType, File file, PlayableContent playableContent, String str) {
        boolean zContains;
        if (!this.mMemoryAllocated) {
            return this.mFileBackedStore.isManifestAvailable(contentSessionType, file, playableContent, str);
        }
        synchronized (this.mSyncObj) {
            zContains = this.mAllocationInfo.contains(contentSessionType.hashCode(), playableContent.hashCode(), str.hashCode(), 195269199);
        }
        return zContains;
    }

    @Override // com.amazon.avod.content.smoothstream.storage.ManifestContentStore
    @Nonnull
    public ByteBuffer loadManifest(ContentSessionType contentSessionType, File file, PlayableContent playableContent, String str) throws ContentException {
        ByteBuffer byteBuffer;
        if (!this.mMemoryAllocated) {
            return this.mFileBackedStore.loadManifest(contentSessionType, file, playableContent, str);
        }
        synchronized (this.mSyncObj) {
            byteBuffer = getByteBuffer(contentSessionType.hashCode(), playableContent.hashCode(), str.hashCode(), 195269199);
        }
        return byteBuffer;
    }

    @Override // com.amazon.avod.content.smoothstream.storage.ManifestContentStore
    public void storeManifest(ContentSessionType contentSessionType, File file, PlayableContent playableContent, String str, InputStream inputStream, int i, boolean z) throws ContentException {
        if (!this.mMemoryAllocated) {
            this.mFileBackedStore.storeManifest(contentSessionType, file, playableContent, str, inputStream, i, z);
        } else {
            Preconditions2.checkArgumentWeakly(!z, "compression not currently supported in memory content store");
            storeStream(inputStream, i, playableContent, contentSessionType.hashCode(), playableContent.hashCode(), str.hashCode(), 195269199);
        }
    }

    @Override // com.amazon.avod.content.smoothstream.storage.ManifestContentStore
    public boolean supportsCompressedManifests(@Nonnull ContentSessionType contentSessionType) {
        if (this.mMemoryAllocated) {
            return false;
        }
        return this.mFileBackedStore.supportsCompressedManifests(contentSessionType);
    }

    @Override // com.amazon.avod.content.smoothstream.storage.SmoothStreamingContentStore
    public boolean isFragmentAvailable(ContentSessionContext contentSessionContext, SmoothStreamingURI smoothStreamingURI) {
        if (!this.mMemoryAllocated) {
            return this.mFileBackedStore.isFragmentAvailable(contentSessionContext, smoothStreamingURI);
        }
        return isFragmentAvailable(contentSessionContext.getSessionType(), contentSessionContext.getStoragePath(), contentSessionContext.getContent(), contentSessionContext.getAudioVideoUrls().getEncodeId(), smoothStreamingURI);
    }

    @Override // com.amazon.avod.content.smoothstream.storage.SmoothStreamingContentStore
    public boolean isAnyFragmentAvailable(ContentSessionContext contentSessionContext, SmoothStreamingURI smoothStreamingURI) {
        if (!this.mMemoryAllocated) {
            return this.mFileBackedStore.isAnyFragmentAvailable(contentSessionContext, smoothStreamingURI);
        }
        return isAnyFragmentAvailable(contentSessionContext.getSessionType(), contentSessionContext.getStoragePath(), contentSessionContext.getContent(), contentSessionContext.getAudioVideoUrls().getEncodeId(), smoothStreamingURI);
    }

    @Override // com.amazon.avod.content.smoothstream.storage.SmoothStreamingContentStore
    public SmoothStreamingURI getBestQualityAvailable(ContentSessionContext contentSessionContext, SmoothStreamingURI smoothStreamingURI) throws ContentException {
        if (!this.mMemoryAllocated) {
            return this.mFileBackedStore.getBestQualityAvailable(contentSessionContext, smoothStreamingURI);
        }
        return getBestQualityAvailable(contentSessionContext.getSessionType(), contentSessionContext.getStoragePath(), contentSessionContext.getContent(), contentSessionContext.getAudioVideoUrls().getEncodeId(), smoothStreamingURI);
    }

    @Override // com.amazon.avod.content.smoothstream.storage.SmoothStreamingContentStore
    @Nonnull
    public ByteBuffer loadFragment(ContentSessionContext contentSessionContext, SmoothStreamingURI smoothStreamingURI) throws ContentException {
        if (!this.mMemoryAllocated) {
            return this.mFileBackedStore.loadFragment(contentSessionContext, smoothStreamingURI);
        }
        return loadFragment(contentSessionContext.getSessionType(), contentSessionContext.getStoragePath(), contentSessionContext.getContent(), contentSessionContext.getAudioVideoUrls().getEncodeId(), smoothStreamingURI);
    }

    @Override // com.amazon.avod.content.smoothstream.storage.SmoothStreamingContentStore
    public void storeFragment(ContentSessionContext contentSessionContext, SmoothStreamingURI smoothStreamingURI, InputStream inputStream, int i, @Nullable String str) throws ContentException {
        if (!this.mMemoryAllocated) {
            this.mFileBackedStore.storeFragment(contentSessionContext, smoothStreamingURI, inputStream, i, str);
        } else {
            storeFragment(contentSessionContext.getSessionType(), contentSessionContext.getStoragePath(), contentSessionContext.getContent(), contentSessionContext.getAudioVideoUrls().getEncodeId(), smoothStreamingURI, inputStream, i, str);
        }
    }

    @Override // com.amazon.avod.content.smoothstream.storage.SmoothStreamingContentStore
    public void validateAndStoreFragment(ContentSessionContext contentSessionContext, SmoothStreamingURI smoothStreamingURI, InputStream inputStream, int i, FragmentValidator fragmentValidator, @Nullable String str) throws Throwable {
        if (!this.mMemoryAllocated) {
            this.mFileBackedStore.validateAndStoreFragment(contentSessionContext, smoothStreamingURI, inputStream, i, fragmentValidator, str);
        } else {
            validateAndStoreFragment(contentSessionContext.getSessionType(), contentSessionContext.getStoragePath(), contentSessionContext.getContent(), contentSessionContext.getAudioVideoUrls().getEncodeId(), smoothStreamingURI, inputStream, i, fragmentValidator, str);
        }
    }

    @Override // com.amazon.avod.content.smoothstream.storage.SmoothStreamingContentStore
    public void releaseFragment(ContentSessionContext contentSessionContext, SmoothStreamingURI smoothStreamingURI) {
        if (!this.mMemoryAllocated) {
            this.mFileBackedStore.releaseFragment(contentSessionContext, smoothStreamingURI);
        } else {
            release(contentSessionContext.getSessionType().hashCode(), contentSessionContext.getContent().hashCode(), contentSessionContext.getAudioVideoUrls().getEncodeId().hashCode(), smoothStreamingURI.getStream().getIndex(), getChunkIndex(smoothStreamingURI));
        }
    }

    @Override // com.amazon.avod.content.smoothstream.storage.SmoothStreamingContentStore
    public void deleteErroredFragment(ContentSessionContext contentSessionContext, SmoothStreamingURI smoothStreamingURI) {
        if (!this.mMemoryAllocated) {
            this.mFileBackedStore.deleteErroredFragment(contentSessionContext, smoothStreamingURI);
        } else {
            releaseFragment(contentSessionContext, smoothStreamingURI);
        }
    }

    @Override // com.amazon.avod.content.smoothstream.storage.SmoothStreamingContentStore
    public void deleteErroredContent(ContentSessionType contentSessionType, File file, PlayableContent playableContent, @Nullable String str) {
        if (!this.mMemoryAllocated) {
            this.mFileBackedStore.deleteErroredContent(contentSessionType, file, playableContent, str);
        } else {
            releaseContent(contentSessionType, file, playableContent, str);
        }
    }

    @Override // com.amazon.avod.content.smoothstream.storage.SmoothStreamingContentStore
    @Nonnull
    public ByteBuffer loadStatFile(ContentSessionContext contentSessionContext, StreamIndex streamIndex) throws ContentException {
        if (!this.mMemoryAllocated) {
            return this.mFileBackedStore.loadStatFile(contentSessionContext, streamIndex);
        }
        return loadStatFile(contentSessionContext.getSessionType(), contentSessionContext.getStoragePath(), contentSessionContext.getContent(), contentSessionContext.getAudioVideoUrls().getEncodeId(), streamIndex);
    }

    @Override // com.amazon.avod.content.smoothstream.storage.SmoothStreamingContentStore
    public boolean isStatFileAvailable(ContentSessionContext contentSessionContext, StreamIndex streamIndex) {
        if (!this.mMemoryAllocated) {
            return this.mFileBackedStore.isStatFileAvailable(contentSessionContext, streamIndex);
        }
        return isStatFileAvailable(contentSessionContext.getSessionType(), contentSessionContext.getStoragePath(), contentSessionContext.getContent(), contentSessionContext.getAudioVideoUrls().getEncodeId(), streamIndex);
    }

    @Override // com.amazon.avod.content.smoothstream.storage.SmoothStreamingContentStore
    public void storeStatFile(ContentSessionContext contentSessionContext, StreamIndex streamIndex, InputStream inputStream, int i) throws ContentException {
        if (!this.mMemoryAllocated) {
            this.mFileBackedStore.storeStatFile(contentSessionContext, streamIndex, inputStream, i);
        } else {
            storeStatFile(contentSessionContext.getSessionType(), contentSessionContext.getStoragePath(), contentSessionContext.getContent(), contentSessionContext.getAudioVideoUrls().getEncodeId(), streamIndex, inputStream, i);
        }
    }

    @Override // com.amazon.avod.content.MediaComponentContentStore
    public boolean isFragmentAvailable(ContentSessionType contentSessionType, File file, PlayableContent playableContent, String str, SmoothStreamingURI smoothStreamingURI) {
        boolean zContains;
        if (!this.mMemoryAllocated) {
            return this.mFileBackedStore.isFragmentAvailable(contentSessionType, file, playableContent, str, smoothStreamingURI);
        }
        synchronized (this.mSyncObj) {
            zContains = this.mAllocationInfo.contains(contentSessionType.hashCode(), playableContent.hashCode(), str.hashCode(), smoothStreamingURI.getStream().getIndex(), getChunkIndex(smoothStreamingURI), getBitrate(smoothStreamingURI.getQualityLevel()));
        }
        return zContains;
    }

    @Override // com.amazon.avod.content.MediaComponentContentStore
    public boolean isAnyFragmentAvailable(ContentSessionType contentSessionType, File file, PlayableContent playableContent, String str, SmoothStreamingURI smoothStreamingURI) {
        boolean z;
        if (!this.mMemoryAllocated) {
            return this.mFileBackedStore.isAnyFragmentAvailable(contentSessionType, file, playableContent, str, smoothStreamingURI);
        }
        synchronized (this.mSyncObj) {
            z = this.mAllocationInfo.getChildren(contentSessionType.hashCode(), playableContent.hashCode(), str.hashCode(), smoothStreamingURI.getStream().getIndex(), getChunkIndex(smoothStreamingURI)) != null;
        }
        return z;
    }

    @Override // com.amazon.avod.content.MediaComponentContentStore
    public SmoothStreamingURI getBestQualityAvailable(ContentSessionType contentSessionType, File file, PlayableContent playableContent, String str, SmoothStreamingURI smoothStreamingURI) throws ContentException {
        if (!this.mMemoryAllocated) {
            return this.mFileBackedStore.getBestQualityAvailable(contentSessionType, file, playableContent, str, smoothStreamingURI);
        }
        synchronized (this.mSyncObj) {
            Set<Integer> children = this.mAllocationInfo.getChildren(contentSessionType.hashCode(), playableContent.hashCode(), str.hashCode(), smoothStreamingURI.getStream().getIndex(), getChunkIndex(smoothStreamingURI));
            if (children == null) {
                return null;
            }
            Iterator<Integer> it = children.iterator();
            int iMax = Integer.MIN_VALUE;
            while (it.hasNext()) {
                iMax = Math.max(it.next().intValue(), iMax);
            }
            if (iMax == Integer.MIN_VALUE) {
                throw new ContentException("MemStore. No available bitrates.");
            }
            return smoothStreamingURI.getSmoothStreamingUriForQuality(smoothStreamingURI.getStream().getQualityLevelLessThanEqual(smoothStreamingURI.getChunkIndex(), iMax));
        }
    }

    @Override // com.amazon.avod.content.MediaComponentContentStore
    public ByteBuffer loadFragment(ContentSessionType contentSessionType, File file, PlayableContent playableContent, String str, SmoothStreamingURI smoothStreamingURI) throws ContentException {
        ByteBuffer byteBuffer;
        if (!this.mMemoryAllocated) {
            return this.mFileBackedStore.loadFragment(contentSessionType, file, playableContent, str, smoothStreamingURI);
        }
        synchronized (this.mSyncObj) {
            byteBuffer = getByteBuffer(contentSessionType.hashCode(), playableContent.hashCode(), str.hashCode(), smoothStreamingURI.getStream().getIndex(), getChunkIndex(smoothStreamingURI), getBitrate(smoothStreamingURI.getQualityLevel()));
        }
        return byteBuffer;
    }

    @Override // com.amazon.avod.content.smoothstream.storage.SmoothStreamingContentStore
    public void deleteFragmentAtQuality(ContentSessionType contentSessionType, File file, PlayableContent playableContent, String str, Integer num, SmoothStreamingURI smoothStreamingURI) {
        if (!this.mMemoryAllocated) {
            this.mFileBackedStore.deleteFragmentAtQuality(contentSessionType, file, playableContent, str, num, smoothStreamingURI);
        } else {
            release(contentSessionType.hashCode(), playableContent.hashCode(), str.hashCode(), smoothStreamingURI.getStream().getIndex(), getChunkIndex(smoothStreamingURI), smoothStreamingURI.getQualityLevel().getBitrate());
        }
    }

    @Override // com.amazon.avod.content.MediaComponentContentStore
    public void deleteFragmentAtAllQualities(ContentSessionType contentSessionType, File file, PlayableContent playableContent, String str, SmoothStreamingURI smoothStreamingURI) {
        if (!this.mMemoryAllocated) {
            this.mFileBackedStore.deleteFragmentAtAllQualities(contentSessionType, file, playableContent, str, smoothStreamingURI);
        } else {
            release(contentSessionType.hashCode(), playableContent.hashCode(), str.hashCode(), smoothStreamingURI.getStream().getIndex(), getChunkIndex(smoothStreamingURI));
        }
    }

    @Override // com.amazon.avod.content.MediaComponentContentStore
    public void storeFragment(ContentSessionType contentSessionType, File file, PlayableContent playableContent, String str, SmoothStreamingURI smoothStreamingURI, InputStream inputStream, int i, @Nullable String str2) throws ContentException {
        if (!this.mMemoryAllocated) {
            this.mFileBackedStore.storeFragment(contentSessionType, file, playableContent, str, smoothStreamingURI, inputStream, i, str2);
        } else {
            storeStream(inputStream, i, playableContent, contentSessionType.hashCode(), playableContent.hashCode(), str.hashCode(), smoothStreamingURI.getStream().getIndex(), getChunkIndex(smoothStreamingURI), getBitrate(smoothStreamingURI.getQualityLevel()));
        }
    }

    @Override // com.amazon.avod.content.MediaComponentContentStore
    public void validateAndStoreFragment(ContentSessionType contentSessionType, File file, PlayableContent playableContent, String str, SmoothStreamingURI smoothStreamingURI, InputStream inputStream, int i, FragmentValidator fragmentValidator, @Nullable String str2) throws Throwable {
        if (!this.mMemoryAllocated) {
            this.mFileBackedStore.validateAndStoreFragment(contentSessionType, file, playableContent, str, smoothStreamingURI, inputStream, i, fragmentValidator, str2);
            return;
        }
        synchronized (this.mSyncObj) {
            if (isContentActive(playableContent)) {
                storeAndValidateStream(inputStream, i, smoothStreamingURI.isAudio(), fragmentValidator, playableContent, URLUtils.nullWhenMalformed(smoothStreamingURI.getAbsoluteUrl(str2)), contentSessionType.hashCode(), playableContent.hashCode(), str.hashCode(), smoothStreamingURI.getStream().getIndex(), getChunkIndex(smoothStreamingURI), getBitrate(smoothStreamingURI.getQualityLevel()));
            } else {
                DLog.warnf("MemStore. Skipping storeAndValidateStream of stale content %s", playableContent.toString());
            }
        }
    }

    @Override // com.amazon.avod.content.MediaComponentContentStore
    public ByteBuffer loadStatFile(ContentSessionType contentSessionType, File file, PlayableContent playableContent, String str, StreamIndex streamIndex) throws ContentException {
        ByteBuffer byteBuffer;
        if (!this.mMemoryAllocated) {
            return this.mFileBackedStore.loadStatFile(contentSessionType, file, playableContent, str, streamIndex);
        }
        synchronized (this.mSyncObj) {
            byteBuffer = getByteBuffer(contentSessionType.hashCode(), playableContent.hashCode(), str.hashCode(), streamIndex.getIndex(), 1381927440);
        }
        return byteBuffer;
    }

    @Override // com.amazon.avod.content.MediaComponentContentStore
    public boolean isStatFileAvailable(ContentSessionType contentSessionType, File file, PlayableContent playableContent, String str, StreamIndex streamIndex) {
        boolean zContains;
        if (!this.mMemoryAllocated) {
            return this.mFileBackedStore.isStatFileAvailable(contentSessionType, file, playableContent, str, streamIndex);
        }
        synchronized (this.mSyncObj) {
            zContains = this.mAllocationInfo.contains(contentSessionType.hashCode(), playableContent.hashCode(), str.hashCode(), streamIndex.getIndex(), 1381927440);
        }
        return zContains;
    }

    @Override // com.amazon.avod.content.MediaComponentContentStore
    public void storeStatFile(ContentSessionType contentSessionType, File file, PlayableContent playableContent, String str, StreamIndex streamIndex, InputStream inputStream, int i) throws ContentException {
        if (!this.mMemoryAllocated) {
            this.mFileBackedStore.storeStatFile(contentSessionType, file, playableContent, str, streamIndex, inputStream, i);
        } else {
            storeStream(inputStream, i, playableContent, contentSessionType.hashCode(), playableContent.hashCode(), str.hashCode(), streamIndex.getIndex(), 1381927440);
        }
    }

    @Override // com.amazon.avod.content.smoothstream.storage.SmoothStreamingContentStore
    public void releaseBuffer(ContentSessionType contentSessionType, ByteBuffer byteBuffer) {
        if (!this.mMemoryAllocated) {
            this.mFileBackedStore.releaseBuffer(contentSessionType, byteBuffer);
            return;
        }
        if (byteBuffer != null) {
            try {
                synchronized (this.mSyncObj) {
                    this.mAllocator.unmap(byteBuffer);
                }
            } catch (MediaException e) {
                DLog.warnf("MemStore. Failed to unmap byte buffer with %s", e);
            }
        }
    }

    @Override // com.amazon.avod.content.smoothstream.storage.SmoothStreamingContentStore
    @Nonnull
    public ContentStoreType getContentStoreType(@Nonnull ContentSessionType contentSessionType) {
        return this.mContentStoreType;
    }

    private int getChunkIndex(SmoothStreamingURI smoothStreamingURI) {
        return smoothStreamingURI.getChunkIndex() == -1 ? smoothStreamingURI.hashCode() * (-1) : smoothStreamingURI.getChunkIndex();
    }

    private void release(@Nonnull int... iArr) {
        synchronized (this.mSyncObj) {
            this.mAllocationInfo.remove(new AllocationInfo.RemoveRunnable() { // from class: com.amazon.avod.content.smoothstream.storage.MemoryBackedContentStore.1
                @Override // com.amazon.avod.content.smoothstream.storage.AllocationInfo.RemoveRunnable
                public void run(long j) {
                    try {
                        MemoryBackedContentStore.this.mAllocator.free(j);
                        MemoryBackedContentStore.this.mDeviceResources.setNativeHeapUsedSizeInBytes(MemoryBackedContentStore.this.mAllocator.getHeapUsedSizeInBytes());
                        MemoryBackedContentStore.this.updateNativeAvailableSize();
                    } catch (MediaException e) {
                        DLog.errorf("MemStore. Failed to free native allocation with: %s", e);
                    }
                }
            }, iArr);
        }
    }

    private void storeStream(@Nonnull InputStream inputStream, int i, @Nonnull PlayableContent playableContent, @Nonnull int... iArr) throws ContentException {
        int heapVersion = this.mAllocator.getHeapVersion();
        ByteBuffer byteBufferAllocateByteBuffer = allocateByteBuffer(i, iArr);
        try {
            copyStream(inputStream, byteBufferAllocateByteBuffer, i, playableContent, heapVersion);
            synchronized (this.mSyncObj) {
                try {
                } catch (MediaException e) {
                    DLog.warnf("MemStore. Failed to unmap byte buffer with %s", e);
                }
                if (this.mMemoryAllocated) {
                    this.mAllocator.unmap(byteBufferAllocateByteBuffer);
                }
            }
        } catch (Throwable th) {
            synchronized (this.mSyncObj) {
                try {
                } catch (MediaException e2) {
                    DLog.warnf("MemStore. Failed to unmap byte buffer with %s", e2);
                }
                if (this.mMemoryAllocated) {
                    this.mAllocator.unmap(byteBufferAllocateByteBuffer);
                    throw th;
                }
                throw th;
            }
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:101:0x00fa A[EXC_TOP_SPLITTER, SYNTHETIC] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private void storeAndValidateStream(@javax.annotation.Nonnull java.io.InputStream r18, int r19, boolean r20, @javax.annotation.Nonnull com.amazon.avod.content.downloading.FragmentValidator r21, @javax.annotation.Nonnull com.amazon.avod.content.PlayableContent r22, @javax.annotation.Nullable java.net.URL r23, @javax.annotation.Nonnull int... r24) throws java.lang.Throwable {
        /*
            Method dump skipped, instruction units count: 333
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.amazon.avod.content.smoothstream.storage.MemoryBackedContentStore.storeAndValidateStream(java.io.InputStream, int, boolean, com.amazon.avod.content.downloading.FragmentValidator, com.amazon.avod.content.PlayableContent, java.net.URL, int[]):void");
    }

    @Nonnull
    private ByteBuffer allocateByteBuffer(int i, @Nonnull int... iArr) throws ContentException {
        ByteBuffer map;
        synchronized (this.mSyncObj) {
            release(iArr);
            try {
                long jAllocate = this.mAllocator.allocate(i);
                if (jAllocate != 0) {
                    map = this.mAllocator.map(jAllocate);
                    this.mDeviceResources.setNativeHeapUsedSizeInBytes(this.mAllocator.getHeapUsedSizeInBytes());
                    updateNativeAvailableSize();
                } else {
                    map = null;
                }
                if (map == null) {
                    if (this.mPlaybackConfig.shouldFatalOnNativeMemAllocFailures()) {
                        throw new ContentException(ContentException.ContentError.DISK_FULL, "MemStore. unable to allocate native buffer");
                    }
                    throw new ContentException(ContentException.ContentError.MEMORY_ACCESS_ERROR, "MemStore. unable to allocate native buffer");
                }
                this.mAllocationInfo.put(jAllocate, iArr);
            } catch (MediaException e) {
                throw new ContentException(ContentException.ContentError.MEMORY_ACCESS_ERROR, e, e.getUrl());
            }
        }
        return map;
    }

    @Nonnull
    ByteBuffer getByteBuffer(int... iArr) throws ContentException {
        long j = this.mAllocationInfo.get(iArr);
        if (j != 0) {
            try {
                ByteBuffer map = this.mAllocator.map(j);
                if (map != null) {
                    return map;
                }
                throw new ContentException(String.format(Locale.US, "MemStore. MemoryAllocater.map() JNI returned null, handle:%s", Long.valueOf(j)));
            } catch (MediaException e) {
                throw new ContentException(String.format(Locale.US, "MemStore. Failed to map the memory handle %s with %s", Long.valueOf(j), e));
            }
        }
        throw new ContentException("MemStore. Handle to allocate memory is invalid");
    }

    /* JADX WARN: Code restructure failed: missing block: B:28:0x005b, code lost:
    
        r9.put(r0, 0, r3);
     */
    /* JADX WARN: Removed duplicated region for block: B:22:0x0049 A[Catch: all -> 0x0060, TryCatch #0 {, blocks: (B:10:0x0024, B:12:0x0029, B:14:0x002f, B:15:0x0038, B:22:0x0049, B:24:0x004f, B:25:0x0058, B:27:0x005a, B:17:0x003a, B:19:0x003e, B:20:0x0047), top: B:45:0x0024 }] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private void copyStream(@javax.annotation.Nonnull java.io.InputStream r8, @javax.annotation.Nonnull java.nio.ByteBuffer r9, int r10, @javax.annotation.Nonnull com.amazon.avod.content.PlayableContent r11, int r12) throws com.amazon.avod.content.ContentException {
        /*
            r7 = this;
            com.amazon.avod.media.framework.resources.ResourcePool<byte[]> r0 = r7.mReadBufferPool
            java.lang.Object r0 = r0.requestResource()
            byte[] r0 = (byte[]) r0
            r9.rewind()     // Catch: java.lang.Throwable -> L6c java.io.IOException -> L6e java.nio.BufferOverflowException -> L7b
            r1 = 0
            r2 = r1
        Ld:
            if (r2 >= r10) goto L63
            int r3 = com.amazon.avod.content.smoothstream.storage.MemoryBackedContentStore.ReadBufferResourcePool.access$300()     // Catch: java.lang.Throwable -> L6c java.io.IOException -> L6e java.nio.BufferOverflowException -> L7b
            int r4 = r10 - r2
            int r3 = java.lang.Math.min(r3, r4)     // Catch: java.lang.Throwable -> L6c java.io.IOException -> L6e java.nio.BufferOverflowException -> L7b
            int r3 = r8.read(r0, r1, r3)     // Catch: java.lang.Throwable -> L6c java.io.IOException -> L6e java.nio.BufferOverflowException -> L7b
            r4 = -1
            if (r3 != r4) goto L21
            goto L63
        L21:
            java.lang.Object r4 = r7.mSyncObj     // Catch: java.lang.Throwable -> L6c java.io.IOException -> L6e java.nio.BufferOverflowException -> L7b
            monitor-enter(r4)     // Catch: java.lang.Throwable -> L6c java.io.IOException -> L6e java.nio.BufferOverflowException -> L7b
            boolean r5 = r7.mCopyStreamCheckContentActive     // Catch: java.lang.Throwable -> L60
            r6 = 1
            if (r5 == 0) goto L3a
            boolean r5 = r7.isContentActive(r11)     // Catch: java.lang.Throwable -> L60
            if (r5 != 0) goto L49
            java.lang.String r8 = "MemStore. Skipping copyStream for stale content %s"
            java.lang.Object[] r10 = new java.lang.Object[r6]     // Catch: java.lang.Throwable -> L60
            r10[r1] = r11     // Catch: java.lang.Throwable -> L60
            com.amazon.avod.util.DLog.warnf(r8, r10)     // Catch: java.lang.Throwable -> L60
            monitor-exit(r4)     // Catch: java.lang.Throwable -> L60
            goto L63
        L3a:
            boolean r5 = r7.mMemoryAllocated     // Catch: java.lang.Throwable -> L60
            if (r5 != 0) goto L49
            java.lang.String r8 = "MemStore. Skipping copyStream for stale content %s"
            java.lang.Object[] r10 = new java.lang.Object[r6]     // Catch: java.lang.Throwable -> L60
            r10[r1] = r11     // Catch: java.lang.Throwable -> L60
            com.amazon.avod.util.DLog.warnf(r8, r10)     // Catch: java.lang.Throwable -> L60
            monitor-exit(r4)     // Catch: java.lang.Throwable -> L60
            goto L63
        L49:
            boolean r5 = r7.isAllocatorValid(r12)     // Catch: java.lang.Throwable -> L60
            if (r5 != 0) goto L5a
            java.lang.String r8 = "MemStore. Skipping copyStream for destroyed thread or allocator; content %s"
            java.lang.Object[] r10 = new java.lang.Object[r6]     // Catch: java.lang.Throwable -> L60
            r10[r1] = r11     // Catch: java.lang.Throwable -> L60
            com.amazon.avod.util.DLog.warnf(r8, r10)     // Catch: java.lang.Throwable -> L60
            monitor-exit(r4)     // Catch: java.lang.Throwable -> L60
            goto L63
        L5a:
            monitor-exit(r4)     // Catch: java.lang.Throwable -> L60
            r9.put(r0, r1, r3)     // Catch: java.lang.Throwable -> L6c java.io.IOException -> L6e java.nio.BufferOverflowException -> L7b
            int r2 = r2 + r3
            goto Ld
        L60:
            r8 = move-exception
            monitor-exit(r4)     // Catch: java.lang.Throwable -> L60
            throw r8     // Catch: java.lang.Throwable -> L6c java.io.IOException -> L6e java.nio.BufferOverflowException -> L7b
        L63:
            r9.rewind()
            com.amazon.avod.media.framework.resources.ResourcePool<byte[]> r8 = r7.mReadBufferPool
            r8.releaseResource(r0)
            return
        L6c:
            r8 = move-exception
            goto L88
        L6e:
            r8 = move-exception
            com.amazon.avod.content.ContentException r10 = new com.amazon.avod.content.ContentException     // Catch: java.lang.Throwable -> L6c
            com.amazon.avod.content.ContentException$ContentError r11 = com.amazon.avod.content.ContentException.ContentError.NETWORK_ERROR     // Catch: java.lang.Throwable -> L6c
            java.lang.String r8 = r8.getMessage()     // Catch: java.lang.Throwable -> L6c
            r10.<init>(r11, r8)     // Catch: java.lang.Throwable -> L6c
            throw r10     // Catch: java.lang.Throwable -> L6c
        L7b:
            r8 = move-exception
            com.amazon.avod.content.ContentException r10 = new com.amazon.avod.content.ContentException     // Catch: java.lang.Throwable -> L6c
            com.amazon.avod.content.ContentException$ContentError r11 = com.amazon.avod.content.ContentException.ContentError.DISK_ERROR     // Catch: java.lang.Throwable -> L6c
            java.lang.String r8 = r8.getMessage()     // Catch: java.lang.Throwable -> L6c
            r10.<init>(r11, r8)     // Catch: java.lang.Throwable -> L6c
            throw r10     // Catch: java.lang.Throwable -> L6c
        L88:
            r9.rewind()
            com.amazon.avod.media.framework.resources.ResourcePool<byte[]> r9 = r7.mReadBufferPool
            r9.releaseResource(r0)
            throw r8
        */
        throw new UnsupportedOperationException("Method not decompiled: com.amazon.avod.content.smoothstream.storage.MemoryBackedContentStore.copyStream(java.io.InputStream, java.nio.ByteBuffer, int, com.amazon.avod.content.PlayableContent, int):void");
    }

    private int getBitrate(QualityLevel qualityLevel) {
        if (qualityLevel != null) {
            return qualityLevel.getBitrate();
        }
        return 0;
    }

    private static class ReadBufferResourcePool extends SurgingResourcePool<byte[]> {
        private static final int READ_BUFFER_SIZE_IN_BYTES = (int) DataUnit.KILOBYTES.toBytes(256.0f);

        public ReadBufferResourcePool(int i) {
            super(i, new Provider<byte[]>() { // from class: com.amazon.avod.content.smoothstream.storage.MemoryBackedContentStore.ReadBufferResourcePool.1
                @Override // javax.inject.Provider
                public byte[] get() {
                    return new byte[ReadBufferResourcePool.READ_BUFFER_SIZE_IN_BYTES];
                }
            });
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void updateNativeAvailableSize() {
        try {
            this.mDeviceResources.setNativeHeapAvailableSizeInBytes(this.mAllocator.getAvailableSizeInBytes());
        } catch (MediaException e) {
            DLog.warnf("MemStore. Couldn't update heap available size: %s", e.getMessage());
        }
    }

    private void updateSessionCountForContent(@Nonnull PlayableContent playableContent, boolean z) {
        Preconditions.checkState(Thread.holdsLock(this.mSyncObj));
        Integer num = this.mContentToSessionCountMap.get(playableContent);
        if (z) {
            if (num == null) {
                this.mContentToSessionCountMap.put(playableContent, 1);
                return;
            } else {
                this.mContentToSessionCountMap.put(playableContent, Integer.valueOf(num.intValue() + 1));
                return;
            }
        }
        if (num != null) {
            if (num.intValue() == 1) {
                this.mContentToSessionCountMap.remove(playableContent);
            } else {
                this.mContentToSessionCountMap.put(playableContent, Integer.valueOf(num.intValue() - 1));
            }
        }
    }

    private boolean isContentActive(@Nonnull PlayableContent playableContent) {
        Preconditions.checkState(Thread.holdsLock(this.mSyncObj));
        Integer num = this.mContentToSessionCountMap.get(playableContent);
        return num != null && num.intValue() > 0;
    }

    private boolean isAllocatorValid(int i) {
        return !(this.mCheckThreadInterrupt && Thread.currentThread().isInterrupted()) && (!this.mCheckHeapVersion || this.mAllocator.getHeapVersion() == i);
    }
}
