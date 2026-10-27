package com.amazon.avod.content.smoothstream.storage;

import android.content.Context;
import com.amazon.avod.content.ContentException;
import com.amazon.avod.content.ContentSessionContext;
import com.amazon.avod.content.ContentSessionType;
import com.amazon.avod.content.MediaComponentContentStore;
import com.amazon.avod.content.PlayableContent;
import com.amazon.avod.content.config.FileBackedContentStoreConfig;
import com.amazon.avod.content.config.SmoothStreamingPlaybackConfig;
import com.amazon.avod.content.downloading.FragmentValidator;
import com.amazon.avod.content.event.ContentManagementEventBus;
import com.amazon.avod.content.smoothstream.SmoothStreamingURI;
import com.amazon.avod.content.smoothstream.manifest.QualityLevel;
import com.amazon.avod.content.smoothstream.manifest.StreamIndex;
import com.amazon.avod.fileio.DiskFullIOException;
import com.amazon.avod.fileio.DiskIOException;
import com.amazon.avod.fileio.DiskUtils;
import com.amazon.avod.media.framework.memory.GrowableBuffer;
import com.amazon.avod.media.framework.platform.FileSystem;
import com.amazon.avod.media.framework.resources.SurgingBufferPool;
import com.amazon.avod.media.framework.storage.DiskStorage;
import com.amazon.avod.media.framework.storage.FileLockManager;
import com.amazon.avod.media.framework.storage.InMemoryMountedFolder;
import com.amazon.avod.pmet.ContentStoreType;
import com.amazon.avod.util.DLog;
import com.amazon.avod.util.URLUtils;
import com.google.common.base.Optional;
import com.google.common.base.Preconditions;
import com.google.common.base.Strings;
import com.google.common.collect.ImmutableMap;
import com.google.common.io.Closeables;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Locale;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.locks.Lock;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;

/* JADX INFO: loaded from: classes2.dex */
public class FileBackedContentStore implements MediaComponentContentStore {
    private static final Pattern BITRATE_REGEX = Pattern.compile(String.format(Locale.US, "(\\d+)\\%s$", ".atv"));
    private final DiskStorage mDiskStorage;
    private final ExecutorService mExecutor;
    private final FileSystem mFileSystem;

    @Nonnull
    private final HashMap<String, ByteBuffer> mFragmentBufferMap;
    private final CountDownLatch mInitializationLatch;
    private final FileLockManager mLockManager;
    private final InMemoryMountedFolder mMountedFolder;
    private ImmutableMap<ContentSessionType, String> mPathMap;
    private final SmoothStreamingPlaybackConfig mPlaybackConfig;
    private final boolean mShouldDownloadDeleteEntireFolder;
    private final boolean mShouldStoreCompressedManifest;
    private boolean mStoredIsInMemoryDriveEnabled;
    private final SurgingBufferPool mSurgingBufferPool;

    @Override // com.amazon.avod.content.smoothstream.storage.SmoothStreamingContentStore
    public void deleteFragmentAtQuality(ContentSessionType contentSessionType, File file, PlayableContent playableContent, String str, Integer num, SmoothStreamingURI smoothStreamingURI) {
    }

    @Override // com.amazon.avod.content.smoothstream.storage.SmoothStreamingContentStore
    public void end(@Nonnull ContentSessionType contentSessionType, @Nonnull PlayableContent playableContent, @Nullable String str) {
    }

    @Override // com.amazon.avod.content.smoothstream.storage.SmoothStreamingContentStore
    public void releaseBuffer(ContentSessionType contentSessionType, ByteBuffer byteBuffer) {
    }

    public FileBackedContentStore(@Nonnull Context context, @Nonnull FileSystem fileSystem, @Nonnull ExecutorService executorService) {
        FileLockManager fileLockManager = new FileLockManager();
        DiskStorage diskStorage = new DiskStorage();
        CountDownLatch countDownLatch = new CountDownLatch(1);
        SmoothStreamingPlaybackConfig smoothStreamingPlaybackConfig = SmoothStreamingPlaybackConfig.INSTANCE;
        FileBackedContentStoreConfig fileBackedContentStoreConfig = FileBackedContentStoreConfig.INSTANCE;
        this(fileSystem, executorService, fileLockManager, diskStorage, countDownLatch, smoothStreamingPlaybackConfig, fileBackedContentStoreConfig.shouldStoreCompressedManifest(), fileBackedContentStoreConfig.shouldDownloadDeleteEntireFolder(), new InMemoryMountedFolder(context), new HashMap());
    }

    FileBackedContentStore(@Nonnull FileSystem fileSystem, @Nonnull ExecutorService executorService, @Nonnull FileLockManager fileLockManager, @Nonnull DiskStorage diskStorage, @Nonnull CountDownLatch countDownLatch, @Nonnull SmoothStreamingPlaybackConfig smoothStreamingPlaybackConfig, boolean z, boolean z2, @Nonnull InMemoryMountedFolder inMemoryMountedFolder, @Nonnull HashMap<String, ByteBuffer> map) {
        this.mStoredIsInMemoryDriveEnabled = false;
        this.mLockManager = (FileLockManager) Preconditions.checkNotNull(fileLockManager, "lockManager");
        this.mExecutor = (ExecutorService) Preconditions.checkNotNull(executorService, "executorService");
        this.mDiskStorage = (DiskStorage) Preconditions.checkNotNull(diskStorage, "diskStorage");
        this.mInitializationLatch = (CountDownLatch) Preconditions.checkNotNull(countDownLatch, "initializationLatch");
        this.mFileSystem = (FileSystem) Preconditions.checkNotNull(fileSystem, "fileSystem");
        this.mPlaybackConfig = (SmoothStreamingPlaybackConfig) Preconditions.checkNotNull(smoothStreamingPlaybackConfig, "playbackConfig");
        this.mShouldStoreCompressedManifest = z;
        this.mShouldDownloadDeleteEntireFolder = z2;
        this.mMountedFolder = (InMemoryMountedFolder) Preconditions.checkNotNull(inMemoryMountedFolder, "mountedFolder");
        this.mSurgingBufferPool = new SurgingBufferPool(2);
        this.mFragmentBufferMap = (HashMap) Preconditions.checkNotNull(map, "fragmentBufferMap");
    }

    @Override // com.amazon.avod.content.smoothstream.storage.SmoothStreamingContentStore
    public void begin(PlayableContent playableContent, ContentSessionType contentSessionType, ContentManagementEventBus contentManagementEventBus, @Nullable ContentSessionContext contentSessionContext) {
        updateTmpfsMount();
    }

    @Override // com.amazon.avod.media.framework.MediaComponent
    public void initialize() {
        this.mExecutor.execute(new Runnable() { // from class: com.amazon.avod.content.smoothstream.storage.FileBackedContentStore.1
            @Override // java.lang.Runnable
            public void run() {
                ImmutableMap.Builder builder = ImmutableMap.builder();
                for (ContentSessionType contentSessionType : ContentSessionType.values()) {
                    if (!contentSessionType.isDownload()) {
                        FileBackedContentStore fileBackedContentStore = FileBackedContentStore.this;
                        builder.put(contentSessionType, fileBackedContentStore.getContentStorePath(contentSessionType, fileBackedContentStore.mFileSystem));
                    }
                }
                FileBackedContentStore.this.updateTmpfsMount();
                FileBackedContentStore.this.mPathMap = builder.build();
                FileBackedContentStore.this.internalDeleteAllContent(ContentSessionType.STREAMING, null);
                FileBackedContentStore.this.mInitializationLatch.countDown();
            }
        });
    }

    protected void finalize() throws Throwable {
        try {
            this.mMountedFolder.unmount();
        } catch (Exception unused) {
        } catch (Throwable th) {
            super.finalize();
            throw th;
        }
        super.finalize();
    }

    /* JADX INFO: renamed from: com.amazon.avod.content.smoothstream.storage.FileBackedContentStore$2, reason: invalid class name */
    static /* synthetic */ class AnonymousClass2 {
        static final /* synthetic */ int[] $SwitchMap$com$amazon$avod$content$ContentSessionType;

        static {
            int[] iArr = new int[ContentSessionType.values().length];
            $SwitchMap$com$amazon$avod$content$ContentSessionType = iArr;
            try {
                iArr[ContentSessionType.LIVE_CACHE.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$amazon$avod$content$ContentSessionType[ContentSessionType.CONTENT_CACHE.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$com$amazon$avod$content$ContentSessionType[ContentSessionType.STREAMING.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$com$amazon$avod$content$ContentSessionType[ContentSessionType.STREAMING_CONTINUOUS.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
            try {
                $SwitchMap$com$amazon$avod$content$ContentSessionType[ContentSessionType.DOWNLOAD.ordinal()] = 5;
            } catch (NoSuchFieldError unused5) {
            }
            try {
                $SwitchMap$com$amazon$avod$content$ContentSessionType[ContentSessionType.PROGRESSIVE_PLAYBACK.ordinal()] = 6;
            } catch (NoSuchFieldError unused6) {
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public String getContentStorePath(ContentSessionType contentSessionType, FileSystem fileSystem) {
        String absolutePath;
        switch (AnonymousClass2.$SwitchMap$com$amazon$avod$content$ContentSessionType[contentSessionType.ordinal()]) {
            case 1:
            case 2:
                absolutePath = fileSystem.getGlobalSharedFileDir().getAbsolutePath();
                break;
            case 3:
            case 4:
                absolutePath = fileSystem.getFilesDir().getAbsolutePath();
                break;
            case 5:
            case 6:
                throw new UnsupportedOperationException(String.format(Locale.US, "Implicit shared file dir not supported for %s, explicit file dir must be specified", contentSessionType));
            default:
                DLog.errorf("Unexpected ContentSessionType %s, returning generalRootPath", contentSessionType);
                absolutePath = fileSystem.getFilesDir().getAbsolutePath();
                break;
        }
        return absolutePath + File.separator + contentSessionType.getDataPartition();
    }

    private void appendContentPath(ContentSessionType contentSessionType, File file, PlayableContent playableContent, String str, StringBuilder sb) {
        if (file != null) {
            sb.append(file.getAbsolutePath());
        } else {
            sb.append(getImplicitFilePath(contentSessionType));
            sb.append(File.separator);
            sb.append(playableContent.getUniqueIdentifier());
        }
        Optional<String> audioTrackId = playableContent.getAudioTrackId();
        if (audioTrackId.isPresent()) {
            sb.append(File.separator);
            sb.append(audioTrackId.get());
        }
        if (str != null) {
            sb.append(File.separator);
            sb.append(str);
        }
    }

    private void appendStreamPath(StreamIndex streamIndex, StringBuilder sb) {
        sb.append(File.separator);
        sb.append(streamIndex.getIndex());
    }

    private void appendChunkPath(SmoothStreamingURI smoothStreamingURI, StringBuilder sb) {
        sb.append(File.separator);
        sb.append(smoothStreamingURI.getChunkIndex() == -1 ? smoothStreamingURI.hashCode() * (-1) : smoothStreamingURI.getChunkIndex());
    }

    private String buildContentPath(ContentSessionType contentSessionType, File file, PlayableContent playableContent, String str) {
        StringBuilder sb = new StringBuilder();
        appendContentPath(contentSessionType, file, playableContent, str, sb);
        return sb.toString();
    }

    private String buildManifestPath(ContentSessionType contentSessionType, File file, PlayableContent playableContent, String str, boolean z) {
        StringBuilder sb = new StringBuilder();
        appendContentPath(contentSessionType, file, playableContent, str, sb);
        if (z) {
            sb.append(File.separator);
            sb.append("compressed");
        }
        sb.append(File.separator);
        sb.append("manif.atv");
        return sb.toString();
    }

    private String buildFragmentDirectoryPath(ContentSessionType contentSessionType, File file, PlayableContent playableContent, String str, SmoothStreamingURI smoothStreamingURI) {
        StringBuilder sb = new StringBuilder();
        appendContentPath(contentSessionType, file, playableContent, str, sb);
        appendStreamPath(smoothStreamingURI.getStream(), sb);
        appendChunkPath(smoothStreamingURI, sb);
        return sb.toString();
    }

    private String buildFragmentPath(ContentSessionType contentSessionType, File file, PlayableContent playableContent, String str, SmoothStreamingURI smoothStreamingURI) {
        StringBuilder sb = new StringBuilder();
        appendContentPath(contentSessionType, file, playableContent, str, sb);
        appendStreamPath(smoothStreamingURI.getStream(), sb);
        appendChunkPath(smoothStreamingURI, sb);
        sb.append(File.separator);
        sb.append(getBitrate(smoothStreamingURI.getQualityLevel()));
        sb.append(".atv");
        return sb.toString();
    }

    private String buildStatsFilePath(ContentSessionType contentSessionType, File file, PlayableContent playableContent, String str, StreamIndex streamIndex) {
        StringBuilder sb = new StringBuilder();
        appendContentPath(contentSessionType, file, playableContent, str, sb);
        appendStreamPath(streamIndex, sb);
        sb.append(File.separator);
        sb.append("stats.atv");
        return sb.toString();
    }

    /* JADX WARN: Removed duplicated region for block: B:9:0x002c  */
    @Override // com.amazon.avod.content.smoothstream.storage.ManifestContentStore
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public boolean isManifestAvailable(com.amazon.avod.content.ContentSessionType r9, java.io.File r10, com.amazon.avod.content.PlayableContent r11, java.lang.String r12) {
        /*
            r8 = this;
            java.util.concurrent.locks.Lock r0 = r8.acquireReadLock(r9, r10, r11, r12)
            boolean r1 = r8.mShouldStoreCompressedManifest     // Catch: java.lang.Throwable -> L33
            if (r1 == 0) goto L1a
            com.amazon.avod.media.framework.storage.DiskStorage r1 = r8.mDiskStorage     // Catch: java.lang.Throwable -> L33
            r7 = 1
            r2 = r8
            r3 = r9
            r4 = r10
            r5 = r11
            r6 = r12
            java.lang.String r2 = r2.buildManifestPath(r3, r4, r5, r6, r7)     // Catch: java.lang.Throwable -> L33
            boolean r1 = r1.contains(r2)     // Catch: java.lang.Throwable -> L33
            if (r1 != 0) goto L2c
        L1a:
            com.amazon.avod.media.framework.storage.DiskStorage r1 = r8.mDiskStorage     // Catch: java.lang.Throwable -> L33
            r7 = 0
            r2 = r8
            r3 = r9
            r4 = r10
            r5 = r11
            r6 = r12
            java.lang.String r9 = r2.buildManifestPath(r3, r4, r5, r6, r7)     // Catch: java.lang.Throwable -> L33
            boolean r9 = r1.contains(r9)     // Catch: java.lang.Throwable -> L33
            if (r9 == 0) goto L2e
        L2c:
            r9 = 1
            goto L2f
        L2e:
            r9 = 0
        L2f:
            r0.unlock()
            return r9
        L33:
            r9 = move-exception
            r0.unlock()
            throw r9
        */
        throw new UnsupportedOperationException("Method not decompiled: com.amazon.avod.content.smoothstream.storage.FileBackedContentStore.isManifestAvailable(com.amazon.avod.content.ContentSessionType, java.io.File, com.amazon.avod.content.PlayableContent, java.lang.String):boolean");
    }

    @Override // com.amazon.avod.content.smoothstream.storage.ManifestContentStore
    public ByteBuffer loadManifest(ContentSessionType contentSessionType, File file, PlayableContent playableContent, String str) throws ContentException {
        Lock lockAcquireReadLock = acquireReadLock(contentSessionType, file, playableContent, str);
        try {
            try {
                if (this.mShouldStoreCompressedManifest) {
                    String strBuildManifestPath = buildManifestPath(contentSessionType, file, playableContent, str, true);
                    if (this.mDiskStorage.contains(strBuildManifestPath)) {
                        return ByteBuffer.wrap(this.mDiskStorage.getByteArray(strBuildManifestPath, true));
                    }
                }
                return ByteBuffer.wrap(this.mDiskStorage.getByteArray(buildManifestPath(contentSessionType, file, playableContent, str, false), false));
            } catch (DiskIOException e) {
                throw new ContentException(ContentException.ContentError.DISK_ERROR, e.getMessage(), e);
            }
        } finally {
            lockAcquireReadLock.unlock();
        }
    }

    @Override // com.amazon.avod.content.smoothstream.storage.ManifestContentStore
    public void storeManifest(ContentSessionType contentSessionType, File file, PlayableContent playableContent, String str, InputStream inputStream, int i, boolean z) throws ContentException {
        Lock lockAcquireWriteLock = acquireWriteLock(contentSessionType, file, playableContent, str);
        try {
            try {
                try {
                    this.mDiskStorage.putInputStream(buildManifestPath(contentSessionType, file, playableContent, str, z), inputStream, i);
                } catch (DiskIOException e) {
                    throw new ContentException(ContentException.ContentError.DISK_ERROR, e.getMessage(), e);
                } catch (IOException e2) {
                    throw new ContentException(ContentException.ContentError.NETWORK_ERROR, e2.getMessage(), e2);
                }
            } catch (DiskFullIOException e3) {
                throw new ContentException(ContentException.ContentError.DISK_FULL, e3.getMessage(), e3);
            }
        } finally {
            lockAcquireWriteLock.unlock();
        }
    }

    @Override // com.amazon.avod.content.smoothstream.storage.ManifestContentStore
    public boolean supportsCompressedManifests(@Nonnull ContentSessionType contentSessionType) {
        return this.mShouldStoreCompressedManifest;
    }

    @Override // com.amazon.avod.content.smoothstream.storage.SmoothStreamingContentStore
    public boolean isFragmentAvailable(ContentSessionContext contentSessionContext, SmoothStreamingURI smoothStreamingURI) {
        return isFragmentAvailable(contentSessionContext.getSessionType(), contentSessionContext.getStoragePath(), contentSessionContext.getContent(), contentSessionContext.getAudioVideoUrls().getEncodeId(), smoothStreamingURI);
    }

    @Override // com.amazon.avod.content.MediaComponentContentStore
    public boolean isFragmentAvailable(ContentSessionType contentSessionType, File file, PlayableContent playableContent, String str, SmoothStreamingURI smoothStreamingURI) {
        Lock lockAcquireReadLock = acquireReadLock(contentSessionType, file, playableContent, str);
        Lock lockAcquireFragmentReadLock = acquireFragmentReadLock(contentSessionType, file, playableContent, str, smoothStreamingURI);
        try {
            return this.mDiskStorage.contains(buildFragmentPath(contentSessionType, file, playableContent, str, smoothStreamingURI));
        } finally {
            lockAcquireFragmentReadLock.unlock();
            lockAcquireReadLock.unlock();
        }
    }

    @Override // com.amazon.avod.content.smoothstream.storage.SmoothStreamingContentStore
    public boolean isAnyFragmentAvailable(ContentSessionContext contentSessionContext, SmoothStreamingURI smoothStreamingURI) {
        return isAnyFragmentAvailable(contentSessionContext.getSessionType(), contentSessionContext.getStoragePath(), contentSessionContext.getContent(), contentSessionContext.getAudioVideoUrls().getEncodeId(), smoothStreamingURI);
    }

    @Override // com.amazon.avod.content.MediaComponentContentStore
    public boolean isAnyFragmentAvailable(ContentSessionType contentSessionType, File file, PlayableContent playableContent, String str, SmoothStreamingURI smoothStreamingURI) {
        Lock lockAcquireReadLock = acquireReadLock(contentSessionType, file, playableContent, str);
        Lock lockAcquireFragmentReadLock = acquireFragmentReadLock(contentSessionType, file, playableContent, str, smoothStreamingURI);
        try {
            return !this.mDiskStorage.getChildrenOf(buildFragmentDirectoryPath(contentSessionType, file, playableContent, str, smoothStreamingURI)).isEmpty();
        } finally {
            lockAcquireFragmentReadLock.unlock();
            lockAcquireReadLock.unlock();
        }
    }

    @Override // com.amazon.avod.content.smoothstream.storage.SmoothStreamingContentStore
    public SmoothStreamingURI getBestQualityAvailable(ContentSessionContext contentSessionContext, SmoothStreamingURI smoothStreamingURI) throws ContentException {
        return getBestQualityAvailable(contentSessionContext.getSessionType(), contentSessionContext.getStoragePath(), contentSessionContext.getContent(), contentSessionContext.getAudioVideoUrls().getEncodeId(), smoothStreamingURI);
    }

    @Override // com.amazon.avod.content.MediaComponentContentStore
    public SmoothStreamingURI getBestQualityAvailable(ContentSessionType contentSessionType, File file, PlayableContent playableContent, String str, SmoothStreamingURI smoothStreamingURI) throws ContentException {
        Lock lockAcquireReadLock = acquireReadLock(contentSessionType, file, playableContent, str);
        Lock lockAcquireFragmentReadLock = acquireFragmentReadLock(contentSessionType, file, playableContent, str, smoothStreamingURI);
        try {
            Iterator<File> it = this.mDiskStorage.getChildrenOf(buildFragmentDirectoryPath(contentSessionType, file, playableContent, str, smoothStreamingURI)).iterator();
            int iMax = Integer.MIN_VALUE;
            while (it.hasNext()) {
                Matcher matcher = BITRATE_REGEX.matcher(it.next().getAbsolutePath());
                if (matcher.find()) {
                    iMax = Math.max(Integer.parseInt(matcher.group(1)), iMax);
                } else {
                    DLog.warnf("A file was returned that was not a fragment.");
                }
            }
            if (iMax == Integer.MIN_VALUE) {
                throw new ContentException("No available bitrates.");
            }
            return smoothStreamingURI.getSmoothStreamingUriForQuality(smoothStreamingURI.getStream().getQualityLevelLessThanEqual(smoothStreamingURI.getChunkIndex(), iMax));
        } finally {
            lockAcquireFragmentReadLock.unlock();
            lockAcquireReadLock.unlock();
        }
    }

    public int getFragmentSize(ContentSessionType contentSessionType, File file, PlayableContent playableContent, String str, SmoothStreamingURI smoothStreamingURI) {
        int sizeOf;
        Lock lockAcquireReadLock = acquireReadLock(contentSessionType, file, playableContent, str);
        Lock lockAcquireFragmentReadLock = acquireFragmentReadLock(contentSessionType, file, playableContent, str, smoothStreamingURI);
        try {
            sizeOf = this.mDiskStorage.getSizeOf(buildFragmentPath(contentSessionType, file, playableContent, str, smoothStreamingURI));
        } catch (DiskIOException unused) {
            sizeOf = 0;
        } catch (Throwable th) {
            lockAcquireFragmentReadLock.unlock();
            lockAcquireReadLock.unlock();
            throw th;
        }
        lockAcquireFragmentReadLock.unlock();
        lockAcquireReadLock.unlock();
        return sizeOf;
    }

    @Override // com.amazon.avod.content.smoothstream.storage.SmoothStreamingContentStore
    public ByteBuffer loadFragment(ContentSessionContext contentSessionContext, SmoothStreamingURI smoothStreamingURI) throws ContentException {
        return loadFragment(contentSessionContext.getSessionType(), contentSessionContext.getStoragePath(), contentSessionContext.getContent(), contentSessionContext.getAudioVideoUrls().getEncodeId(), smoothStreamingURI);
    }

    @Override // com.amazon.avod.content.MediaComponentContentStore
    public ByteBuffer loadFragment(ContentSessionType contentSessionType, File file, PlayableContent playableContent, String str, SmoothStreamingURI smoothStreamingURI) throws ContentException {
        Lock lockAcquireReadLock = acquireReadLock(contentSessionType, file, playableContent, str);
        Lock lockAcquireFragmentReadLock = acquireFragmentReadLock(contentSessionType, file, playableContent, str, smoothStreamingURI);
        try {
            try {
                int fragmentSize = getFragmentSize(contentSessionType, file, playableContent, str, smoothStreamingURI);
                ByteBuffer byteBufferAllocateDirect = ByteBuffer.allocateDirect(fragmentSize);
                int iLoadToBuffer = this.mDiskStorage.loadToBuffer(buildFragmentPath(contentSessionType, file, playableContent, str, smoothStreamingURI), byteBufferAllocateDirect);
                if (iLoadToBuffer != fragmentSize) {
                    throw new ContentException(ContentException.ContentError.DISK_ERROR, String.format(Locale.US, "Could not copy entire fragment to buffer, fragment size: %d, bytes copied to buffer: %d", Integer.valueOf(fragmentSize), Integer.valueOf(iLoadToBuffer)));
                }
                if (this.mPlaybackConfig.getIsFilebackedContentStoreMapNeeded() && contentSessionType == ContentSessionType.CONTENT_CACHE) {
                    this.mFragmentBufferMap.put(buildFragmentDirectoryPath(contentSessionType, file, playableContent, str, smoothStreamingURI), byteBufferAllocateDirect);
                }
                return byteBufferAllocateDirect;
            } catch (DiskIOException e) {
                throw new ContentException(ContentException.ContentError.DISK_ERROR, e.getMessage(), e);
            }
        } finally {
            lockAcquireFragmentReadLock.unlock();
            lockAcquireReadLock.unlock();
        }
    }

    @Override // com.amazon.avod.content.smoothstream.storage.SmoothStreamingContentStore
    public void storeFragment(ContentSessionContext contentSessionContext, SmoothStreamingURI smoothStreamingURI, InputStream inputStream, int i, @Nullable String str) throws ContentException {
        storeFragment(contentSessionContext.getSessionType(), contentSessionContext.getStoragePath(), contentSessionContext.getContent(), contentSessionContext.getAudioVideoUrls().getEncodeId(), smoothStreamingURI, inputStream, i, str);
    }

    @Override // com.amazon.avod.content.MediaComponentContentStore
    public void storeFragment(ContentSessionType contentSessionType, File file, PlayableContent playableContent, String str, SmoothStreamingURI smoothStreamingURI, InputStream inputStream, int i, @Nullable String str2) throws ContentException {
        Lock lockAcquireReadLock = acquireReadLock(contentSessionType, file, playableContent, str);
        Lock lockAcquireFragmentWriteLock = acquireFragmentWriteLock(contentSessionType, file, playableContent, str, smoothStreamingURI);
        try {
            try {
                try {
                    this.mDiskStorage.putInputStream(buildFragmentPath(contentSessionType, file, playableContent, str, smoothStreamingURI), inputStream, i);
                } catch (DiskIOException e) {
                    throw new ContentException(ContentException.ContentError.DISK_ERROR, e.getMessage(), e);
                } catch (IOException e2) {
                    throw new ContentException(ContentException.ContentError.NETWORK_ERROR, e2.getMessage(), e2);
                }
            } catch (DiskFullIOException e3) {
                throw new ContentException(ContentException.ContentError.DISK_FULL, e3.getMessage(), e3);
            }
        } finally {
            lockAcquireFragmentWriteLock.unlock();
            lockAcquireReadLock.unlock();
        }
    }

    @Override // com.amazon.avod.content.smoothstream.storage.SmoothStreamingContentStore
    public void validateAndStoreFragment(ContentSessionContext contentSessionContext, SmoothStreamingURI smoothStreamingURI, InputStream inputStream, int i, FragmentValidator fragmentValidator, @Nullable String str) throws Throwable {
        validateAndStoreFragment(contentSessionContext.getSessionType(), contentSessionContext.getStoragePath(), contentSessionContext.getContent(), contentSessionContext.getAudioVideoUrls().getEncodeId(), smoothStreamingURI, inputStream, i, fragmentValidator, str);
    }

    @Override // com.amazon.avod.content.MediaComponentContentStore
    public void validateAndStoreFragment(ContentSessionType contentSessionType, File file, PlayableContent playableContent, String str, SmoothStreamingURI smoothStreamingURI, InputStream inputStream, int i, FragmentValidator fragmentValidator, @Nullable String str2) throws Throwable {
        InputStream inputStreamOpenValidatedStream;
        GrowableBuffer growableBufferRequestResource = this.mSurgingBufferPool.requestResource();
        InputStream inputStream2 = null;
        try {
            inputStreamOpenValidatedStream = fragmentValidator.openValidatedStream(inputStream, i, smoothStreamingURI.isAudio(), growableBufferRequestResource, URLUtils.nullWhenMalformed(smoothStreamingURI.getAbsoluteUrl(str2)));
        } catch (Throwable th) {
            th = th;
        }
        try {
            storeFragment(contentSessionType, file, playableContent, str, smoothStreamingURI, inputStreamOpenValidatedStream, i, str2);
            this.mSurgingBufferPool.releaseResource(growableBufferRequestResource);
            Closeables.closeQuietly(inputStreamOpenValidatedStream);
        } catch (Throwable th2) {
            th = th2;
            inputStream2 = inputStreamOpenValidatedStream;
            this.mSurgingBufferPool.releaseResource(growableBufferRequestResource);
            Closeables.closeQuietly(inputStream2);
            throw th;
        }
    }

    @Override // com.amazon.avod.content.smoothstream.storage.SmoothStreamingContentStore
    public void deleteErroredFragment(ContentSessionContext contentSessionContext, SmoothStreamingURI smoothStreamingURI) {
        deleteFragmentAtAllQualities(contentSessionContext.getSessionType(), contentSessionContext.getStoragePath(), contentSessionContext.getContent(), contentSessionContext.getAudioVideoUrls().getEncodeId(), smoothStreamingURI);
    }

    @Override // com.amazon.avod.content.smoothstream.storage.SmoothStreamingContentStore
    public void releaseFragment(ContentSessionContext contentSessionContext, SmoothStreamingURI smoothStreamingURI) {
        deleteFragmentAtAllQualities(contentSessionContext.getSessionType(), contentSessionContext.getStoragePath(), contentSessionContext.getContent(), contentSessionContext.getAudioVideoUrls().getEncodeId(), smoothStreamingURI);
        releaseFragmentReferences(contentSessionContext.getSessionType(), contentSessionContext.getStoragePath(), contentSessionContext.getContent(), contentSessionContext.getAudioVideoUrls().getEncodeId(), smoothStreamingURI);
    }

    @Override // com.amazon.avod.content.MediaComponentContentStore
    public void releaseFragmentReferences(ContentSessionType contentSessionType, File file, PlayableContent playableContent, String str, SmoothStreamingURI smoothStreamingURI) {
        if (this.mPlaybackConfig.getIsFilebackedContentStoreMapNeeded()) {
            this.mFragmentBufferMap.remove(buildFragmentDirectoryPath(contentSessionType, file, playableContent, str, smoothStreamingURI));
        }
    }

    @Override // com.amazon.avod.content.MediaComponentContentStore
    public void deleteFragmentAtAllQualities(ContentSessionType contentSessionType, File file, PlayableContent playableContent, String str, SmoothStreamingURI smoothStreamingURI) {
        Lock lockAcquireReadLock = acquireReadLock(contentSessionType, file, playableContent, str);
        Lock lockAcquireFragmentWriteLock = acquireFragmentWriteLock(contentSessionType, file, playableContent, str, smoothStreamingURI);
        try {
            try {
                this.mDiskStorage.delete(buildFragmentDirectoryPath(contentSessionType, file, playableContent, str, smoothStreamingURI));
            } catch (DiskIOException e) {
                DLog.warnf("Deletion of fragment %s for content %s failed with exception %s (this is unexpected but non-fatal)", smoothStreamingURI, playableContent, e);
            }
        } finally {
            lockAcquireFragmentWriteLock.unlock();
            lockAcquireReadLock.unlock();
        }
    }

    @Override // com.amazon.avod.content.smoothstream.storage.SmoothStreamingContentStore
    public ByteBuffer loadStatFile(ContentSessionContext contentSessionContext, StreamIndex streamIndex) throws ContentException {
        return loadStatFile(contentSessionContext.getSessionType(), contentSessionContext.getStoragePath(), contentSessionContext.getContent(), contentSessionContext.getAudioVideoUrls().getEncodeId(), streamIndex);
    }

    @Override // com.amazon.avod.content.MediaComponentContentStore
    public ByteBuffer loadStatFile(ContentSessionType contentSessionType, File file, PlayableContent playableContent, String str, StreamIndex streamIndex) throws ContentException {
        Lock lockAcquireReadLock = acquireReadLock(contentSessionType, file, playableContent, str);
        try {
            try {
                return ByteBuffer.wrap(this.mDiskStorage.getByteArray(buildStatsFilePath(contentSessionType, file, playableContent, str, streamIndex), false));
            } catch (DiskIOException e) {
                throw new ContentException(ContentException.ContentError.DISK_ERROR, e.getMessage(), e);
            }
        } finally {
            lockAcquireReadLock.unlock();
        }
    }

    @Override // com.amazon.avod.content.smoothstream.storage.SmoothStreamingContentStore
    public boolean isStatFileAvailable(ContentSessionContext contentSessionContext, StreamIndex streamIndex) {
        return isStatFileAvailable(contentSessionContext.getSessionType(), contentSessionContext.getStoragePath(), contentSessionContext.getContent(), contentSessionContext.getAudioVideoUrls().getEncodeId(), streamIndex);
    }

    @Override // com.amazon.avod.content.MediaComponentContentStore
    public boolean isStatFileAvailable(ContentSessionType contentSessionType, File file, PlayableContent playableContent, String str, StreamIndex streamIndex) {
        Lock lockAcquireReadLock = acquireReadLock(contentSessionType, file, playableContent, str);
        try {
            return this.mDiskStorage.contains(buildStatsFilePath(contentSessionType, file, playableContent, str, streamIndex));
        } finally {
            lockAcquireReadLock.unlock();
        }
    }

    @Override // com.amazon.avod.content.smoothstream.storage.SmoothStreamingContentStore
    public void storeStatFile(ContentSessionContext contentSessionContext, StreamIndex streamIndex, InputStream inputStream, int i) throws ContentException {
        storeStatFile(contentSessionContext.getSessionType(), contentSessionContext.getStoragePath(), contentSessionContext.getContent(), contentSessionContext.getAudioVideoUrls().getEncodeId(), streamIndex, inputStream, i);
    }

    @Override // com.amazon.avod.content.MediaComponentContentStore
    public void storeStatFile(ContentSessionType contentSessionType, File file, PlayableContent playableContent, String str, StreamIndex streamIndex, InputStream inputStream, int i) throws ContentException {
        Lock lockAcquireWriteLock = acquireWriteLock(contentSessionType, file, playableContent, str);
        try {
            try {
                try {
                    try {
                        this.mDiskStorage.putInputStream(buildStatsFilePath(contentSessionType, file, playableContent, str, streamIndex), inputStream, i);
                    } catch (DiskIOException e) {
                        throw new ContentException(ContentException.ContentError.DISK_ERROR, e.getMessage(), e);
                    }
                } catch (DiskFullIOException e2) {
                    throw new ContentException(ContentException.ContentError.DISK_FULL, e2.getMessage(), e2);
                }
            } catch (IOException e3) {
                throw new ContentException(ContentException.ContentError.NETWORK_ERROR, e3.getMessage(), e3);
            }
        } finally {
            lockAcquireWriteLock.unlock();
        }
    }

    @Override // com.amazon.avod.content.ContentStore
    public void deleteAllContent(ContentSessionType contentSessionType, File file) {
        waitForInitialization();
        internalDeleteAllContent(contentSessionType, file);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void internalDeleteAllContent(ContentSessionType contentSessionType, File file) {
        String implicitFilePath;
        if (file != null) {
            implicitFilePath = file.getAbsolutePath();
        } else {
            implicitFilePath = getImplicitFilePath(contentSessionType);
        }
        for (File file2 : this.mDiskStorage.getChildrenOf(implicitFilePath)) {
            String name = file2.getName();
            if (!Strings.isNullOrEmpty(name)) {
                Lock writeLock = this.mLockManager.getWriteLock(name);
                writeLock.lock();
                DiskUtils.deleteFile(file2);
                writeLock.unlock();
            }
        }
    }

    private String getImplicitFilePath(@Nonnull ContentSessionType contentSessionType) {
        Preconditions.checkState(!contentSessionType.isDownload(), "Downloads require an explicit file path per title: %s", contentSessionType);
        return this.mPathMap.get(contentSessionType);
    }

    @Override // com.amazon.avod.content.ContentStore
    public void releaseContent(ContentSessionType contentSessionType, File file, PlayableContent playableContent, @Nullable String str) {
        releaseContent(contentSessionType, file, playableContent, str, Optional.absent());
    }

    @Override // com.amazon.avod.content.ContentStore
    public void releaseContent(ContentSessionType contentSessionType, File file, PlayableContent playableContent, String str, Optional<DiskUtils.DeletionProgressListener> optional) {
        SmoothStreamingPlaybackConfig smoothStreamingPlaybackConfig;
        if (this.mShouldDownloadDeleteEntireFolder && ContentSessionType.isDownloadSession(contentSessionType)) {
            playableContent = removeAudioTrackId(playableContent);
            str = null;
        }
        Lock lockAcquireWriteLock = acquireWriteLock(contentSessionType, file, playableContent, str);
        try {
            try {
                this.mDiskStorage.delete(buildContentPath(contentSessionType, file, playableContent, str), optional);
            } catch (DiskIOException e) {
                DLog.warnf("Deletion of content %s failed with exception %s (this is unexpected but non-fatal)", playableContent, e);
            }
            if (smoothStreamingPlaybackConfig.getIsFilebackedContentStoreMapNeeded() && ContentSessionType.isStreamingSession(contentSessionType)) {
                this.mFragmentBufferMap.clear();
            }
            lockAcquireWriteLock.unlock();
        } finally {
            if (this.mPlaybackConfig.getIsFilebackedContentStoreMapNeeded() && ContentSessionType.isStreamingSession(contentSessionType)) {
                this.mFragmentBufferMap.clear();
            }
            lockAcquireWriteLock.unlock();
        }
    }

    @Override // com.amazon.avod.content.smoothstream.storage.SmoothStreamingContentStore
    public void deleteErroredContent(ContentSessionType contentSessionType, File file, PlayableContent playableContent, @Nullable String str) {
        releaseContent(contentSessionType, file, playableContent, str);
    }

    @Override // com.amazon.avod.content.smoothstream.storage.SmoothStreamingContentStore
    @Nonnull
    public ContentStoreType getContentStoreType(@Nonnull ContentSessionType contentSessionType) {
        return ContentStoreType.FILESTORE;
    }

    private void waitForInitialization() {
        try {
            this.mInitializationLatch.await();
        } catch (InterruptedException unused) {
            DLog.warnf("Interrupted while waiting for FileBackedContentStore initialization (this is unexpected but non-fatal)");
            Thread.currentThread().interrupt();
        }
    }

    private Lock acquireReadLock(ContentSessionType contentSessionType, File file, PlayableContent playableContent, String str) {
        waitForInitialization();
        Lock readLock = this.mLockManager.getReadLock(buildContentPath(contentSessionType, file, playableContent, str));
        readLock.lock();
        return readLock;
    }

    private Lock acquireWriteLock(ContentSessionType contentSessionType, File file, PlayableContent playableContent, String str) {
        waitForInitialization();
        Lock writeLock = this.mLockManager.getWriteLock(buildContentPath(contentSessionType, file, playableContent, str));
        writeLock.lock();
        return writeLock;
    }

    private Lock acquireFragmentReadLock(ContentSessionType contentSessionType, File file, PlayableContent playableContent, String str, SmoothStreamingURI smoothStreamingURI) {
        waitForInitialization();
        Lock readLock = this.mLockManager.getReadLock(buildFragmentDirectoryPath(contentSessionType, file, playableContent, str, smoothStreamingURI));
        readLock.lock();
        return readLock;
    }

    private Lock acquireFragmentWriteLock(ContentSessionType contentSessionType, File file, PlayableContent playableContent, String str, SmoothStreamingURI smoothStreamingURI) {
        waitForInitialization();
        Lock writeLock = this.mLockManager.getWriteLock(buildFragmentDirectoryPath(contentSessionType, file, playableContent, str, smoothStreamingURI));
        writeLock.lock();
        return writeLock;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void updateTmpfsMount() {
        boolean zIsInMemoryDriveEnabled = this.mPlaybackConfig.isInMemoryDriveEnabled();
        if (this.mStoredIsInMemoryDriveEnabled != zIsInMemoryDriveEnabled) {
            if (zIsInMemoryDriveEnabled) {
                DLog.logf("Mounting tmpfs folder");
                this.mMountedFolder.mount(getContentStorePath(ContentSessionType.STREAMING, this.mFileSystem), this.mPlaybackConfig.getInMemoryDriveSizeInMb());
                this.mStoredIsInMemoryDriveEnabled = true;
            } else {
                DLog.logf("Un-mounting tmpfs folder");
                this.mMountedFolder.unmount();
                this.mStoredIsInMemoryDriveEnabled = false;
            }
        }
    }

    private int getBitrate(QualityLevel qualityLevel) {
        if (qualityLevel != null) {
            return qualityLevel.getBitrate();
        }
        return 0;
    }

    @Nonnull
    private PlayableContent removeAudioTrackId(@Nonnull PlayableContent playableContent) {
        Preconditions.checkNotNull(playableContent, "content");
        return new PlayableContent(playableContent.getTitleId(), playableContent.isTrailer(), Optional.absent());
    }
}
