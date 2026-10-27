package com.amazon.avod.content.image;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import com.amazon.avod.content.ContentException;
import com.amazon.avod.content.urlvending.ContentUrlSelector;
import com.amazon.avod.media.downloadservice.SaveCallback;
import com.amazon.avod.media.framework.error.MediaException;
import com.amazon.avod.util.DLog;
import com.google.common.base.Preconditions;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.Locale;
import java.util.concurrent.TimeUnit;
import javax.annotation.Nonnull;

/* JADX INFO: loaded from: classes2.dex */
public class ImageDownloadSaveCallback implements SaveCallback {
    private final long mChunkIndex;
    private ContentUrlSelector mContentUrlSelector;
    private final ImageDownloadAttribute mImageDownloadAttribute;
    private final ImageFileManager mImageFileManager;
    private final int mImageHorizontalResolution;
    private final ImageQualityLevelInterface mImageQualityLevel;
    private final ImageStreamIndexInterface mImageStreamIndex;
    private final String mImageUrl;
    private final long mManifestAvailabilityStartTimeMillis;
    private final String mPeriodId;
    private int mThumbnailIndex;
    private final TrickplayManifest mTrickplayManifest;

    public ImageDownloadSaveCallback(@Nonnull ImageFileManager imageFileManager, @Nonnull TrickplayManifest trickplayManifest, long j, @Nonnull ImageDownloadAttribute imageDownloadAttribute, @Nonnull ImageQualityLevelInterface imageQualityLevelInterface, long j2, int i, @Nonnull String str, @Nonnull String str2, @Nonnull ImageStreamIndexInterface imageStreamIndexInterface, int i2, @Nonnull ContentUrlSelector contentUrlSelector) {
        this.mImageFileManager = (ImageFileManager) Preconditions.checkNotNull(imageFileManager, "imageFileManager");
        this.mTrickplayManifest = (TrickplayManifest) Preconditions.checkNotNull(trickplayManifest, "trickplayManifest");
        this.mManifestAvailabilityStartTimeMillis = j;
        this.mImageDownloadAttribute = (ImageDownloadAttribute) Preconditions.checkNotNull(imageDownloadAttribute, "downloadAttribute");
        this.mImageQualityLevel = (ImageQualityLevelInterface) Preconditions.checkNotNull(imageQualityLevelInterface, "imageQualityLevel");
        this.mChunkIndex = j2;
        this.mThumbnailIndex = i;
        this.mPeriodId = (String) Preconditions.checkNotNull(str, "periodId");
        this.mImageUrl = (String) Preconditions.checkNotNull(str2);
        this.mImageStreamIndex = (ImageStreamIndexInterface) Preconditions.checkNotNull(imageStreamIndexInterface);
        this.mImageHorizontalResolution = i2;
        this.mContentUrlSelector = (ContentUrlSelector) Preconditions.checkNotNull(contentUrlSelector);
    }

    @Override // com.amazon.avod.media.downloadservice.SaveCallback
    public void save(InputStream inputStream, int i) throws MediaException {
        long thumbnailTimecodeMillis = getThumbnailTimecodeMillis(this.mChunkIndex, 0, this.mImageQualityLevel);
        ImageFileManager imageFileManager = this.mImageFileManager;
        Locale locale = Locale.ENGLISH;
        File fileCreateFile = imageFileManager.createFile(String.format(locale, "%d_%d", Integer.valueOf(this.mImageQualityLevel.hashCode()), Long.valueOf(this.mChunkIndex)));
        this.mImageFileManager.saveToFile(inputStream, i, fileCreateFile);
        if (this.mImageQualityLevel.getTotalThumbnailPerChunk() == 1) {
            this.mTrickplayManifest.put(thumbnailTimecodeMillis, fileCreateFile.getAbsolutePath());
            return;
        }
        DLog.devf("ImageDownloader %s saveChunk periodId: %s chunkIndex: %s", this.mImageDownloadAttribute, this.mPeriodId, Long.valueOf(this.mChunkIndex));
        try {
            try {
                Bitmap bitmapDecodeFile = BitmapFactory.decodeFile(fileCreateFile.getAbsolutePath());
                if (bitmapDecodeFile == null) {
                    throw new ContentException(ContentException.ContentError.CDN_ERROR, "Failed to decode parent image.");
                }
                int downloadGranularityInThumbnails = this.mImageDownloadAttribute.getDownloadGranularityInThumbnails((int) TimeUnit.NANOSECONDS.toSeconds(this.mImageQualityLevel.getThumbnailDurationNanos()));
                int i2 = AnonymousClass1.$SwitchMap$com$amazon$avod$content$image$DownloadDirection[this.mImageDownloadAttribute.getDirection().ordinal()];
                if (i2 == 1) {
                    while (this.mThumbnailIndex < this.mImageQualityLevel.getTotalThumbnailPerChunk()) {
                        saveThumbnail(bitmapDecodeFile, this.mThumbnailIndex);
                        this.mThumbnailIndex += downloadGranularityInThumbnails;
                    }
                } else {
                    if (i2 != 2) {
                        throw new IllegalStateException(String.format(locale, "Unknown download direction: %s", this.mImageDownloadAttribute.getDirection()));
                    }
                    while (true) {
                        int i3 = this.mThumbnailIndex;
                        if (i3 < 0) {
                            break;
                        }
                        saveThumbnail(bitmapDecodeFile, i3);
                        this.mThumbnailIndex -= downloadGranularityInThumbnails;
                    }
                }
            } catch (IOException | IllegalArgumentException e) {
                throw new ContentException(ContentException.ContentError.CDN_ERROR, String.format(Locale.ENGLISH, "Failed to create child image tile: %s", e));
            }
        } finally {
            this.mImageFileManager.deleteFile(fileCreateFile);
        }
    }

    /* JADX INFO: renamed from: com.amazon.avod.content.image.ImageDownloadSaveCallback$1, reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$amazon$avod$content$image$DownloadDirection;

        static {
            int[] iArr = new int[DownloadDirection.values().length];
            $SwitchMap$com$amazon$avod$content$image$DownloadDirection = iArr;
            try {
                iArr[DownloadDirection.FORWARD.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$amazon$avod$content$image$DownloadDirection[DownloadDirection.BACKWARD.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
        }
    }

    private void saveThumbnail(Bitmap bitmap, int i) throws IOException, ContentException {
        DLog.devf("ImageDownloader %s saveThumbnail periodId: %s chunkIndex: %s thumbnailIndex: %s", this.mImageDownloadAttribute, this.mPeriodId, Long.valueOf(this.mChunkIndex), Integer.valueOf(this.mThumbnailIndex));
        long thumbnailTimecodeMillis = getThumbnailTimecodeMillis(this.mChunkIndex, i, this.mImageQualityLevel);
        if (!shouldSave(this.mImageQualityLevel.getThumbnailTimeInNanos(this.mChunkIndex, i))) {
            DLog.devf("ImageDownloader %s shouldn't save thumbnail periodId: %s chunkIndex: %s thumbnailIndex: %s", this.mImageDownloadAttribute, this.mPeriodId, Long.valueOf(this.mChunkIndex), Integer.valueOf(this.mThumbnailIndex));
            return;
        }
        if (isThumbnailDownloaded(thumbnailTimecodeMillis)) {
            DLog.devf("ImageDownloader %s thumbnail is downloaded periodId: %s chunkIndex: %s thumbnailIndex: %s", this.mImageDownloadAttribute, this.mPeriodId, Long.valueOf(this.mChunkIndex), Integer.valueOf(this.mThumbnailIndex));
            return;
        }
        int chunkNumCol = i / this.mImageQualityLevel.getChunkNumCol();
        int chunkNumCol2 = i % this.mImageQualityLevel.getChunkNumCol();
        Bitmap bitmapCreateBitmap = Bitmap.createBitmap(bitmap, this.mImageQualityLevel.getThumbnailWidthInPixels() * chunkNumCol2, this.mImageQualityLevel.getThumbnailHeightInPixels() * chunkNumCol, this.mImageQualityLevel.getThumbnailWidthInPixels(), this.mImageQualityLevel.getThumbnailHeightInPixels());
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        bitmapCreateBitmap.compress(Bitmap.CompressFormat.JPEG, 100, byteArrayOutputStream);
        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(byteArrayOutputStream.toByteArray());
        File fileCreateFile = this.mImageFileManager.createFile(String.format(Locale.ENGLISH, "%d_%d_%d_%d", Integer.valueOf(this.mImageQualityLevel.hashCode()), Long.valueOf(this.mChunkIndex), Integer.valueOf(chunkNumCol2), Integer.valueOf(chunkNumCol)));
        this.mImageFileManager.saveToFile(byteArrayInputStream, byteArrayOutputStream.toByteArray().length, fileCreateFile);
        byteArrayInputStream.close();
        this.mTrickplayManifest.put(thumbnailTimecodeMillis, fileCreateFile.getAbsolutePath());
    }

    private boolean shouldSave(long j) {
        ImageQualityLevelInterface imageQualityLevelInterfaceSelectQualityLevel;
        String periodIdWithTimestampNanos = this.mImageStreamIndex.getPeriodIdWithTimestampNanos(j);
        if (periodIdWithTimestampNanos == null) {
            return false;
        }
        if (periodIdWithTimestampNanos.equals(this.mPeriodId)) {
            return true;
        }
        ImageQualityLevelInterface[] imageQualityLevelInterfaceArrFilterImageQualities = ImageDownloadUtil.filterImageQualities(this.mImageStreamIndex.getSortedImageQualityLevels(periodIdWithTimestampNanos), this.mImageHorizontalResolution);
        int i = AnonymousClass1.$SwitchMap$com$amazon$avod$content$image$DownloadDirection[this.mImageDownloadAttribute.getDirection().ordinal()];
        if (i == 1) {
            imageQualityLevelInterfaceSelectQualityLevel = imageQualityLevelInterfaceArrFilterImageQualities[0];
        } else if (i == 2) {
            imageQualityLevelInterfaceSelectQualityLevel = ImageDownloadUtil.selectQualityLevel(imageQualityLevelInterfaceArrFilterImageQualities, this.mImageDownloadAttribute.getDownloadGranularitySeconds());
        } else {
            throw new IllegalStateException(String.format(Locale.ENGLISH, "Unknown download direction: %s", this.mImageDownloadAttribute.getDirection()));
        }
        String imageUrl = this.mImageStreamIndex.getImageUrl(this.mContentUrlSelector.getCurrentContentUrl().getUrl(), imageQualityLevelInterfaceSelectQualityLevel, (int) imageQualityLevelInterfaceSelectQualityLevel.getChunkIndexFromNanos(j), this.mPeriodId);
        DLog.devf("ImageDownloader %s save only if url matched: %s actual %s", this.mImageDownloadAttribute, this.mImageUrl, imageUrl);
        return imageUrl.equals(this.mImageUrl);
    }

    private boolean isThumbnailDownloaded(long j) {
        return this.mTrickplayManifest.getTimecodeToFilepathMap().get(Long.valueOf(j)) != null;
    }

    private long getThumbnailTimecodeMillis(long j, int i, @Nonnull ImageQualityLevelInterface imageQualityLevelInterface) {
        Preconditions.checkNotNull(imageQualityLevelInterface, "qualityLevel");
        return this.mManifestAvailabilityStartTimeMillis + TimeUnit.NANOSECONDS.toMillis(imageQualityLevelInterface.getThumbnailTimeInNanos(j, i));
    }
}
