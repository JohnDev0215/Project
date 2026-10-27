package com.amazon.avod.content.smoothstream.storage.singlefile;

import com.amazon.avod.content.ContentException;
import com.amazon.avod.content.ContentSessionContext;
import com.amazon.avod.content.ContentSessionType;
import com.amazon.avod.content.MediaComponentContentStore;
import com.amazon.avod.content.PlayableContent;
import com.amazon.avod.content.downloading.FragmentValidator;
import com.amazon.avod.content.event.ContentManagementEventBus;
import com.amazon.avod.content.smoothstream.SmoothStreamingURI;
import com.amazon.avod.content.smoothstream.manifest.StreamIndex;
import com.amazon.avod.content.smoothstream.storage.FileBackedContentStore;
import com.amazon.avod.fileio.DiskUtils;
import com.amazon.avod.media.framework.memory.GrowableBuffer;
import com.amazon.avod.media.framework.resources.SurgingBufferPool;
import com.amazon.avod.pmet.ContentStoreType;
import com.amazon.avod.util.DLog;
import com.amazon.avod.util.URLUtils;
import com.google.common.base.Optional;
import com.google.common.base.Preconditions;
import com.google.common.collect.Maps;
import com.google.common.io.Closeables;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.ByteBuffer;
import java.util.Iterator;
import java.util.Map;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;

/* JADX INFO: loaded from: classes2.dex */
public class SingleFileContentStore implements MediaComponentContentStore {
    private final Map<PlayableContent, SingleFileDownloadContent> mDownloadContentMap;
    private final FileBackedContentStore mFileBackedContentStore;
    private final Map<PlayableContent, Integer> mSessionCountPerContentMap;
    private final SurgingBufferPool mSurgingBufferPool;

    @Override // com.amazon.avod.content.smoothstream.storage.SmoothStreamingContentStore
    public void releaseBuffer(ContentSessionType contentSessionType, ByteBuffer byteBuffer) {
    }

    @Override // com.amazon.avod.content.MediaComponentContentStore
    public void releaseFragmentReferences(ContentSessionType contentSessionType, File file, PlayableContent playableContent, String str, SmoothStreamingURI smoothStreamingURI) {
    }

    public SingleFileContentStore(@Nonnull FileBackedContentStore fileBackedContentStore) {
        this(fileBackedContentStore, Maps.newHashMap(), Maps.newHashMap(), new SurgingBufferPool(2));
    }

    SingleFileContentStore(@Nonnull FileBackedContentStore fileBackedContentStore, @Nonnull Map<PlayableContent, SingleFileDownloadContent> map, @Nonnull Map<PlayableContent, Integer> map2, @Nonnull SurgingBufferPool surgingBufferPool) {
        this.mFileBackedContentStore = (FileBackedContentStore) Preconditions.checkNotNull(fileBackedContentStore, "fileBackedContentStore");
        this.mDownloadContentMap = (Map) Preconditions.checkNotNull(map, "downloadContentMap");
        this.mSessionCountPerContentMap = (Map) Preconditions.checkNotNull(map2, "sessionCountPerContentMap");
        this.mSurgingBufferPool = (SurgingBufferPool) Preconditions.checkNotNull(surgingBufferPool, "surgingBufferPool");
    }

    @Override // com.amazon.avod.content.smoothstream.storage.SmoothStreamingContentStore
    public void begin(PlayableContent playableContent, ContentSessionType contentSessionType, ContentManagementEventBus contentManagementEventBus, @Nullable ContentSessionContext contentSessionContext) throws ContentException {
        this.mFileBackedContentStore.begin(playableContent, contentSessionType, contentManagementEventBus, contentSessionContext);
        synchronized (this.mDownloadContentMap) {
            Integer num = this.mSessionCountPerContentMap.get(playableContent);
            if (num == null) {
                this.mSessionCountPerContentMap.put(playableContent, 1);
            } else {
                this.mSessionCountPerContentMap.put(playableContent, Integer.valueOf(num.intValue() + 1));
            }
        }
    }

    @Override // com.amazon.avod.content.smoothstream.storage.SmoothStreamingContentStore
    public void end(@Nonnull ContentSessionType contentSessionType, @Nonnull PlayableContent playableContent, @Nullable String str) throws ContentException {
        this.mFileBackedContentStore.end(contentSessionType, playableContent, str);
        synchronized (this.mDownloadContentMap) {
            Integer num = this.mSessionCountPerContentMap.get(playableContent);
            if (num == null) {
                DLog.warnf("Begin() for content %s has not been called before!", playableContent.getUniqueIdentifier());
                return;
            }
            this.mSessionCountPerContentMap.put(playableContent, Integer.valueOf(num.intValue() - 1));
            if (num.intValue() == 1) {
                SingleFileDownloadContent singleFileDownloadContentRemove = this.mDownloadContentMap.remove(playableContent);
                if (singleFileDownloadContentRemove == null) {
                    return;
                }
                Iterator<String> it = singleFileDownloadContentRemove.getDownloadContentStatus().getFileNames().iterator();
                while (it.hasNext()) {
                    try {
                        MediaFile mediaFile = singleFileDownloadContentRemove.getFileNameMediaFileMap().get(it.next());
                        if (mediaFile != null) {
                            mediaFile.close();
                        }
                    } catch (IOException e) {
                        throw new ContentException(ContentException.ContentError.DISK_ERROR, e.getMessage(), e);
                    }
                }
            }
        }
    }

    @Override // com.amazon.avod.content.smoothstream.storage.ManifestContentStore
    public boolean isManifestAvailable(ContentSessionType contentSessionType, File file, PlayableContent playableContent, String str) {
        return this.mFileBackedContentStore.isManifestAvailable(contentSessionType, file, playableContent, str);
    }

    @Override // com.amazon.avod.content.smoothstream.storage.ManifestContentStore
    @Nonnull
    public ByteBuffer loadManifest(ContentSessionType contentSessionType, File file, PlayableContent playableContent, String str) throws ContentException {
        return this.mFileBackedContentStore.loadManifest(contentSessionType, file, playableContent, str);
    }

    @Override // com.amazon.avod.content.smoothstream.storage.ManifestContentStore
    public void storeManifest(ContentSessionType contentSessionType, File file, PlayableContent playableContent, String str, InputStream inputStream, int i, boolean z) throws ContentException {
        this.mFileBackedContentStore.storeManifest(contentSessionType, file, playableContent, str, inputStream, i, z);
    }

    @Override // com.amazon.avod.content.smoothstream.storage.ManifestContentStore
    public boolean supportsCompressedManifests(@Nonnull ContentSessionType contentSessionType) {
        return this.mFileBackedContentStore.supportsCompressedManifests(contentSessionType);
    }

    @Override // com.amazon.avod.content.MediaComponentContentStore
    public boolean isFragmentAvailable(ContentSessionType contentSessionType, File file, PlayableContent playableContent, String str, SmoothStreamingURI smoothStreamingURI) {
        try {
            initializeIfNecessary(playableContent, smoothStreamingURI, file);
            MediaFileStatus mediaFileStatus = this.mDownloadContentMap.get(playableContent).getDownloadContentStatus().getMediaFileStatus(SingleFileStoreUtils.getFileNameFromSSU(smoothStreamingURI));
            if (mediaFileStatus == null) {
                return false;
            }
            if (smoothStreamingURI.getQualityLevel() == null) {
                smoothStreamingURI = smoothStreamingURI.getSmoothStreamingUriForQuality(smoothStreamingURI.getStream().getQualityLevelLessThanEqual(smoothStreamingURI.getChunkIndex(), mediaFileStatus.getBitrate()));
            }
            return mediaFileStatus.isByteRangeDownloaded(ByteRange.fromSmoothStreamingURI(smoothStreamingURI));
        } catch (ContentException e) {
            DLog.warnf(e.getMessage());
            return false;
        }
    }

    @Override // com.amazon.avod.content.smoothstream.storage.SmoothStreamingContentStore
    public boolean isFragmentAvailable(ContentSessionContext contentSessionContext, SmoothStreamingURI smoothStreamingURI) {
        return isFragmentAvailable(contentSessionContext.getSessionType(), contentSessionContext.getStoragePath(), contentSessionContext.getContent(), contentSessionContext.getAudioVideoUrls().getEncodeId(), smoothStreamingURI);
    }

    @Override // com.amazon.avod.content.smoothstream.storage.SmoothStreamingContentStore
    public boolean isAnyFragmentAvailable(ContentSessionContext contentSessionContext, SmoothStreamingURI smoothStreamingURI) {
        return isFragmentAvailable(contentSessionContext, smoothStreamingURI);
    }

    @Override // com.amazon.avod.content.MediaComponentContentStore
    public boolean isAnyFragmentAvailable(ContentSessionType contentSessionType, File file, PlayableContent playableContent, String str, SmoothStreamingURI smoothStreamingURI) {
        return isFragmentAvailable(contentSessionType, file, playableContent, str, smoothStreamingURI);
    }

    @Override // com.amazon.avod.content.MediaComponentContentStore
    public SmoothStreamingURI getBestQualityAvailable(ContentSessionType contentSessionType, File file, PlayableContent playableContent, String str, SmoothStreamingURI smoothStreamingURI) throws ContentException {
        initializeIfNecessary(playableContent, smoothStreamingURI, file);
        SingleFileDownloadContent singleFileDownloadContent = this.mDownloadContentMap.get(playableContent);
        if (singleFileDownloadContent == null) {
            throw new ContentException("No available bitrates.");
        }
        MediaFileStatus mediaFileStatus = singleFileDownloadContent.getDownloadContentStatus().getMediaFileStatus(SingleFileStoreUtils.getFileNameFromSSU(smoothStreamingURI));
        if (mediaFileStatus == null) {
            throw new ContentException("No available bitrates.");
        }
        return smoothStreamingURI.getSmoothStreamingUriForQuality(smoothStreamingURI.getStream().getQualityLevelLessThanEqual(smoothStreamingURI.getChunkIndex(), mediaFileStatus.getBitrate()));
    }

    @Override // com.amazon.avod.content.smoothstream.storage.SmoothStreamingContentStore
    public SmoothStreamingURI getBestQualityAvailable(ContentSessionContext contentSessionContext, SmoothStreamingURI smoothStreamingURI) throws ContentException {
        return getBestQualityAvailable(contentSessionContext.getSessionType(), contentSessionContext.getStoragePath(), contentSessionContext.getContent(), contentSessionContext.getAudioVideoUrls().getEncodeId(), smoothStreamingURI);
    }

    @Override // com.amazon.avod.content.MediaComponentContentStore
    public ByteBuffer loadFragment(ContentSessionType contentSessionType, File file, PlayableContent playableContent, String str, SmoothStreamingURI smoothStreamingURI) throws ContentException {
        initializeIfNecessary(playableContent, smoothStreamingURI, file);
        SingleFileDownloadContent singleFileDownloadContent = this.mDownloadContentMap.get(playableContent);
        String fileNameFromSSU = SingleFileStoreUtils.getFileNameFromSSU(smoothStreamingURI);
        try {
            return singleFileDownloadContent.getFileNameMediaFileMap().get(fileNameFromSSU).get(ByteRange.fromSmoothStreamingURI(smoothStreamingURI));
        } catch (IOException e) {
            DLog.warnf(e.getMessage());
            throw new ContentException(ContentException.ContentError.DISK_ERROR, e.getMessage(), e);
        }
    }

    @Override // com.amazon.avod.content.smoothstream.storage.SmoothStreamingContentStore
    @Nonnull
    public ByteBuffer loadFragment(ContentSessionContext contentSessionContext, SmoothStreamingURI smoothStreamingURI) throws ContentException {
        return loadFragment(contentSessionContext.getSessionType(), contentSessionContext.getStoragePath(), contentSessionContext.getContent(), contentSessionContext.getAudioVideoUrls().getEncodeId(), smoothStreamingURI);
    }

    @Override // com.amazon.avod.content.MediaComponentContentStore
    public void storeFragment(ContentSessionType contentSessionType, File file, PlayableContent playableContent, String str, SmoothStreamingURI smoothStreamingURI, InputStream inputStream, int i, @Nullable String str2) throws ContentException {
        initializeIfNecessary(playableContent, smoothStreamingURI, file);
        SingleFileDownloadContent singleFileDownloadContent = this.mDownloadContentMap.get(playableContent);
        String fileNameFromSSU = SingleFileStoreUtils.getFileNameFromSSU(smoothStreamingURI);
        ByteRange byteRangeFromSmoothStreamingURI = ByteRange.fromSmoothStreamingURI(smoothStreamingURI);
        MediaFile mediaFile = singleFileDownloadContent.getFileNameMediaFileMap().get(fileNameFromSSU);
        DownloadContentStatus downloadContentStatus = singleFileDownloadContent.getDownloadContentStatus();
        MediaFileStatus mediaFileStatus = downloadContentStatus.getMediaFileStatus(fileNameFromSSU);
        try {
            mediaFile.put(inputStream, byteRangeFromSmoothStreamingURI);
            synchronized (downloadContentStatus) {
                mediaFileStatus.markDownloaded(byteRangeFromSmoothStreamingURI);
                singleFileDownloadContent.getDownloadContentStatus().save();
            }
        } catch (IOException e) {
            DLog.warnf(e.getMessage());
            throw new ContentException(ContentException.ContentError.DISK_ERROR, e.getMessage(), e);
        }
    }

    @Override // com.amazon.avod.content.smoothstream.storage.SmoothStreamingContentStore
    public void storeFragment(ContentSessionContext contentSessionContext, SmoothStreamingURI smoothStreamingURI, InputStream inputStream, int i, @Nullable String str) throws ContentException {
        storeFragment(contentSessionContext.getSessionType(), contentSessionContext.getStoragePath(), contentSessionContext.getContent(), contentSessionContext.getAudioVideoUrls().getEncodeId(), smoothStreamingURI, inputStream, i, str);
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
            try {
                Closeables.close(inputStreamOpenValidatedStream, true);
            } catch (IOException unused) {
            }
        } catch (Throwable th2) {
            th = th2;
            inputStream2 = inputStreamOpenValidatedStream;
            this.mSurgingBufferPool.releaseResource(growableBufferRequestResource);
            try {
                Closeables.close(inputStream2, true);
            } catch (IOException unused2) {
            }
            throw th;
        }
    }

    @Override // com.amazon.avod.content.smoothstream.storage.SmoothStreamingContentStore
    public void validateAndStoreFragment(ContentSessionContext contentSessionContext, SmoothStreamingURI smoothStreamingURI, InputStream inputStream, int i, FragmentValidator fragmentValidator, @Nullable String str) throws Throwable {
        validateAndStoreFragment(contentSessionContext.getSessionType(), contentSessionContext.getStoragePath(), contentSessionContext.getContent(), contentSessionContext.getAudioVideoUrls().getEncodeId(), smoothStreamingURI, inputStream, i, fragmentValidator, str);
    }

    @Override // com.amazon.avod.content.MediaComponentContentStore
    public void deleteFragmentAtAllQualities(ContentSessionType contentSessionType, File file, PlayableContent playableContent, String str, SmoothStreamingURI smoothStreamingURI) {
        try {
            initializeIfNecessary(playableContent, smoothStreamingURI, file);
            String fileNameFromSSU = SingleFileStoreUtils.getFileNameFromSSU(smoothStreamingURI);
            DownloadContentStatus downloadContentStatus = this.mDownloadContentMap.get(playableContent).getDownloadContentStatus();
            MediaFileStatus mediaFileStatus = downloadContentStatus.getMediaFileStatus(fileNameFromSSU);
            ByteRange byteRangeFromSmoothStreamingURI = ByteRange.fromSmoothStreamingURI(smoothStreamingURI.getSmoothStreamingUriForQuality(smoothStreamingURI.getStream().getQualityLevelLessThanEqual(smoothStreamingURI.getChunkIndex(), mediaFileStatus.getBitrate())));
            synchronized (downloadContentStatus) {
                mediaFileStatus.markUnDownloaded(byteRangeFromSmoothStreamingURI);
            }
        } catch (ContentException e) {
            DLog.warnf("Failed to delete %s fragment at index %s with error %s", smoothStreamingURI.getStream().getType(), Integer.valueOf(smoothStreamingURI.getChunkIndex()), e.getMessage());
        }
    }

    @Override // com.amazon.avod.content.smoothstream.storage.SmoothStreamingContentStore
    public void releaseFragment(ContentSessionContext contentSessionContext, SmoothStreamingURI smoothStreamingURI) {
        deleteFragmentAtAllQualities(contentSessionContext.getSessionType(), contentSessionContext.getStoragePath(), contentSessionContext.getContent(), contentSessionContext.getAudioVideoUrls().getEncodeId(), smoothStreamingURI);
    }

    @Override // com.amazon.avod.content.smoothstream.storage.SmoothStreamingContentStore
    public void deleteErroredFragment(ContentSessionContext contentSessionContext, SmoothStreamingURI smoothStreamingURI) {
        deleteFragmentAtAllQualities(contentSessionContext.getSessionType(), contentSessionContext.getStoragePath(), contentSessionContext.getContent(), contentSessionContext.getAudioVideoUrls().getEncodeId(), smoothStreamingURI);
    }

    @Override // com.amazon.avod.content.smoothstream.storage.SmoothStreamingContentStore
    public void deleteFragmentAtQuality(ContentSessionType contentSessionType, File file, PlayableContent playableContent, String str, Integer num, SmoothStreamingURI smoothStreamingURI) {
        deleteFragmentAtAllQualities(contentSessionType, file, playableContent, str, smoothStreamingURI);
    }

    @Override // com.amazon.avod.content.smoothstream.storage.SmoothStreamingContentStore
    public void deleteErroredContent(ContentSessionType contentSessionType, File file, PlayableContent playableContent, @Nullable String str) {
        this.mFileBackedContentStore.deleteErroredContent(contentSessionType, file, playableContent, str);
    }

    @Override // com.amazon.avod.content.smoothstream.storage.SmoothStreamingContentStore
    @Nonnull
    public ByteBuffer loadStatFile(ContentSessionContext contentSessionContext, StreamIndex streamIndex) throws ContentException {
        return this.mFileBackedContentStore.loadStatFile(contentSessionContext, streamIndex);
    }

    @Override // com.amazon.avod.content.smoothstream.storage.SmoothStreamingContentStore
    public boolean isStatFileAvailable(ContentSessionContext contentSessionContext, StreamIndex streamIndex) {
        return this.mFileBackedContentStore.isStatFileAvailable(contentSessionContext, streamIndex);
    }

    @Override // com.amazon.avod.content.smoothstream.storage.SmoothStreamingContentStore
    public void storeStatFile(ContentSessionContext contentSessionContext, StreamIndex streamIndex, InputStream inputStream, int i) throws ContentException {
        this.mFileBackedContentStore.storeStatFile(contentSessionContext, streamIndex, inputStream, i);
    }

    @Override // com.amazon.avod.content.smoothstream.storage.SmoothStreamingContentStore
    @Nonnull
    public ContentStoreType getContentStoreType(@Nonnull ContentSessionType contentSessionType) {
        return this.mFileBackedContentStore.getContentStoreType(contentSessionType);
    }

    @Override // com.amazon.avod.content.ContentStore
    public void releaseContent(ContentSessionType contentSessionType, File file, PlayableContent playableContent, @Nullable String str) {
        this.mFileBackedContentStore.releaseContent(contentSessionType, file, playableContent, str);
    }

    @Override // com.amazon.avod.content.ContentStore
    public void releaseContent(ContentSessionType contentSessionType, File file, PlayableContent playableContent, String str, Optional<DiskUtils.DeletionProgressListener> optional) {
        this.mFileBackedContentStore.releaseContent(contentSessionType, file, playableContent, str, optional);
    }

    @Override // com.amazon.avod.content.ContentStore
    public void deleteAllContent(ContentSessionType contentSessionType, File file) {
        this.mFileBackedContentStore.deleteAllContent(contentSessionType, file);
    }

    @Override // com.amazon.avod.content.MediaComponentContentStore
    public ByteBuffer loadStatFile(ContentSessionType contentSessionType, File file, PlayableContent playableContent, String str, StreamIndex streamIndex) throws ContentException {
        return this.mFileBackedContentStore.loadStatFile(contentSessionType, file, playableContent, str, streamIndex);
    }

    @Override // com.amazon.avod.content.MediaComponentContentStore
    public boolean isStatFileAvailable(ContentSessionType contentSessionType, File file, PlayableContent playableContent, String str, StreamIndex streamIndex) {
        return this.mFileBackedContentStore.isStatFileAvailable(contentSessionType, file, playableContent, str, streamIndex);
    }

    @Override // com.amazon.avod.content.MediaComponentContentStore
    public void storeStatFile(ContentSessionType contentSessionType, File file, PlayableContent playableContent, String str, StreamIndex streamIndex, InputStream inputStream, int i) throws ContentException {
        this.mFileBackedContentStore.storeStatFile(contentSessionType, file, playableContent, str, streamIndex, inputStream, i);
    }

    @Override // com.amazon.avod.media.framework.MediaComponent
    public void initialize() {
        this.mFileBackedContentStore.initialize();
    }

    /* JADX WARN: Removed duplicated region for block: B:19:0x0091 A[Catch: all -> 0x00fc, DONT_GENERATE, TryCatch #2 {, blocks: (B:4:0x0003, B:6:0x0011, B:7:0x003c, B:9:0x0042, B:12:0x0053, B:17:0x0087, B:19:0x0091, B:21:0x0093, B:23:0x0099, B:25:0x009b, B:26:0x00e6, B:29:0x00e9, B:30:0x00fb, B:15:0x0074, B:16:0x0086), top: B:38:0x0003, inners: #0, #1 }] */
    /* JADX WARN: Removed duplicated region for block: B:21:0x0093 A[Catch: all -> 0x00fc, TryCatch #2 {, blocks: (B:4:0x0003, B:6:0x0011, B:7:0x003c, B:9:0x0042, B:12:0x0053, B:17:0x0087, B:19:0x0091, B:21:0x0093, B:23:0x0099, B:25:0x009b, B:26:0x00e6, B:29:0x00e9, B:30:0x00fb, B:15:0x0074, B:16:0x0086), top: B:38:0x0003, inners: #0, #1 }] */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    void initializeIfNecessary(com.amazon.avod.content.PlayableContent r10, com.amazon.avod.content.smoothstream.SmoothStreamingURI r11, java.io.File r12) throws com.amazon.avod.content.ContentException {
        /*
            Method dump skipped, instruction units count: 255
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.amazon.avod.content.smoothstream.storage.singlefile.SingleFileContentStore.initializeIfNecessary(com.amazon.avod.content.PlayableContent, com.amazon.avod.content.smoothstream.SmoothStreamingURI, java.io.File):void");
    }
}
