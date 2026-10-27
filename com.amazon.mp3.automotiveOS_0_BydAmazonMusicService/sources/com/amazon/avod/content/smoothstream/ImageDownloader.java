package com.amazon.avod.content.smoothstream;

import com.amazon.avod.content.image.ImageDownloadStrategy;
import com.amazon.avod.content.image.TrickplayManifest;
import com.amazon.avod.media.TimeSpan;
import javax.annotation.Nonnull;

/* JADX INFO: loaded from: classes2.dex */
public interface ImageDownloader {

    public interface ImageDownloaderListener {
        void onStatusUpdated(boolean z, @Nonnull TimeSpan timeSpan, @Nonnull ImageDownloadStrategy imageDownloadStrategy);
    }

    void dispose();

    @Nonnull
    TrickplayManifest getTrickplayManifest();

    void start();

    void stop();

    default ImageDownloadStrategy getImageDownloaderStrategy() {
        return ImageDownloadStrategy.UNSUPPORTED;
    }
}
