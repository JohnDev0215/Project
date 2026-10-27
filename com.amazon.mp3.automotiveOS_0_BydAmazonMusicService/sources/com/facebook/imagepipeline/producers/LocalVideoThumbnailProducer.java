package com.facebook.imagepipeline.producers;

import android.content.ContentResolver;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.media.MediaMetadataRetriever;
import android.media.ThumbnailUtils;
import android.net.Uri;
import android.os.ParcelFileDescriptor;
import android.provider.DocumentsContract;
import android.provider.MediaStore;
import com.facebook.common.internal.ImmutableMap;
import com.facebook.common.references.CloseableReference;
import com.facebook.common.util.UriUtil;
import com.facebook.imagepipeline.bitmaps.SimpleBitmapReleaser;
import com.facebook.imagepipeline.image.CloseableImage;
import com.facebook.imagepipeline.image.CloseableStaticBitmap;
import com.facebook.imagepipeline.image.ImmutableQualityInfo;
import com.facebook.imagepipeline.request.ImageRequest;
import com.google.android.gms.common.internal.ImagesContract;
import com.visualon.OSMPUtils.voMimeTypes;
import java.io.FileNotFoundException;
import java.util.Map;
import java.util.concurrent.Executor;
import javax.annotation.Nullable;

/* JADX INFO: loaded from: classes5.dex */
public class LocalVideoThumbnailProducer implements Producer<CloseableReference<CloseableImage>> {
    private final ContentResolver mContentResolver;
    private final Executor mExecutor;

    public LocalVideoThumbnailProducer(Executor executor, ContentResolver contentResolver) {
        this.mExecutor = executor;
        this.mContentResolver = contentResolver;
    }

    @Override // com.facebook.imagepipeline.producers.Producer
    public void produceResults(final Consumer<CloseableReference<CloseableImage>> consumer, final ProducerContext producerContext) {
        final ProducerListener2 producerListener = producerContext.getProducerListener();
        final ImageRequest imageRequest = producerContext.getImageRequest();
        producerContext.putOriginExtra(ImagesContract.LOCAL, voMimeTypes.VOBASE_TYPE_VIDEO);
        final StatefulProducerRunnable<CloseableReference<CloseableImage>> statefulProducerRunnable = new StatefulProducerRunnable<CloseableReference<CloseableImage>>(consumer, producerListener, producerContext, "VideoThumbnailProducer") { // from class: com.facebook.imagepipeline.producers.LocalVideoThumbnailProducer.1
            /* JADX INFO: Access modifiers changed from: protected */
            @Override // com.facebook.imagepipeline.producers.StatefulProducerRunnable, com.facebook.common.executors.StatefulRunnable
            public void onSuccess(@Nullable CloseableReference<CloseableImage> result) {
                super.onSuccess(result);
                producerListener.onUltimateProducerReached(producerContext, "VideoThumbnailProducer", result != null);
                producerContext.putOriginExtra(ImagesContract.LOCAL);
            }

            @Override // com.facebook.imagepipeline.producers.StatefulProducerRunnable, com.facebook.common.executors.StatefulRunnable
            protected void onFailure(Exception e) {
                super.onFailure(e);
                producerListener.onUltimateProducerReached(producerContext, "VideoThumbnailProducer", false);
                producerContext.putOriginExtra(ImagesContract.LOCAL);
            }

            /* JADX INFO: Access modifiers changed from: protected */
            @Override // com.facebook.common.executors.StatefulRunnable
            @Nullable
            public CloseableReference<CloseableImage> getResult() throws Exception {
                String localFilePath;
                try {
                    localFilePath = LocalVideoThumbnailProducer.this.getLocalFilePath(imageRequest);
                } catch (IllegalArgumentException unused) {
                    localFilePath = null;
                }
                Bitmap bitmapCreateVideoThumbnail = localFilePath != null ? ThumbnailUtils.createVideoThumbnail(localFilePath, LocalVideoThumbnailProducer.calculateKind(imageRequest)) : LocalVideoThumbnailProducer.createThumbnailFromContentProvider(LocalVideoThumbnailProducer.this.mContentResolver, imageRequest.getSourceUri());
                if (bitmapCreateVideoThumbnail == null) {
                    return null;
                }
                CloseableStaticBitmap closeableStaticBitmap = new CloseableStaticBitmap(bitmapCreateVideoThumbnail, SimpleBitmapReleaser.getInstance(), ImmutableQualityInfo.FULL_QUALITY, 0);
                producerContext.setExtra("image_format", "thumbnail");
                closeableStaticBitmap.setImageExtras(producerContext.getExtras());
                return CloseableReference.of(closeableStaticBitmap);
            }

            /* JADX INFO: Access modifiers changed from: protected */
            @Override // com.facebook.imagepipeline.producers.StatefulProducerRunnable
            public Map<String, String> getExtraMapOnSuccess(@Nullable final CloseableReference<CloseableImage> result) {
                return ImmutableMap.of("createdThumbnail", String.valueOf(result != null));
            }

            /* JADX INFO: Access modifiers changed from: protected */
            @Override // com.facebook.common.executors.StatefulRunnable
            public void disposeResult(CloseableReference<CloseableImage> result) {
                CloseableReference.closeSafely(result);
            }
        };
        producerContext.addCallbacks(new BaseProducerContextCallbacks() { // from class: com.facebook.imagepipeline.producers.LocalVideoThumbnailProducer.2
            @Override // com.facebook.imagepipeline.producers.BaseProducerContextCallbacks, com.facebook.imagepipeline.producers.ProducerContextCallbacks
            public void onCancellationRequested() {
                statefulProducerRunnable.cancel();
            }
        });
        this.mExecutor.execute(statefulProducerRunnable);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static int calculateKind(ImageRequest imageRequest) {
        return (imageRequest.getPreferredWidth() > 96 || imageRequest.getPreferredHeight() > 96) ? 1 : 3;
    }

    /* JADX INFO: Access modifiers changed from: private */
    @Nullable
    public String getLocalFilePath(ImageRequest imageRequest) {
        Uri uri;
        String str;
        String[] strArr;
        Uri sourceUri = imageRequest.getSourceUri();
        if (UriUtil.isLocalFileUri(sourceUri)) {
            return imageRequest.getSourceFile().getPath();
        }
        if (UriUtil.isLocalContentUri(sourceUri)) {
            if ("com.android.providers.media.documents".equals(sourceUri.getAuthority())) {
                String documentId = DocumentsContract.getDocumentId(sourceUri);
                str = "_id=?";
                uri = MediaStore.Video.Media.EXTERNAL_CONTENT_URI;
                strArr = new String[]{documentId.split(":")[1]};
            } else {
                uri = sourceUri;
                str = null;
                strArr = null;
            }
            Cursor cursorQuery = this.mContentResolver.query(uri, new String[]{"_data"}, str, strArr, null);
            if (cursorQuery != null) {
                try {
                    if (cursorQuery.moveToFirst()) {
                        return cursorQuery.getString(cursorQuery.getColumnIndexOrThrow("_data"));
                    }
                } finally {
                    cursorQuery.close();
                }
            }
            if (cursorQuery != null) {
            }
        }
        return null;
    }

    /* JADX INFO: Access modifiers changed from: private */
    @Nullable
    public static Bitmap createThumbnailFromContentProvider(ContentResolver contentResolver, Uri uri) {
        try {
            ParcelFileDescriptor parcelFileDescriptorOpenFileDescriptor = contentResolver.openFileDescriptor(uri, "r");
            MediaMetadataRetriever mediaMetadataRetriever = new MediaMetadataRetriever();
            mediaMetadataRetriever.setDataSource(parcelFileDescriptorOpenFileDescriptor.getFileDescriptor());
            return mediaMetadataRetriever.getFrameAtTime(-1L);
        } catch (FileNotFoundException unused) {
            return null;
        }
    }
}
