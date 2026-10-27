package com.amazon.avod.content.image;

import android.content.Context;
import com.amazon.avod.acos.StorageHelper;
import com.amazon.avod.content.ContentSessionContext;
import com.amazon.avod.content.event.ContentManagementEventBus;
import com.amazon.avod.content.smoothstream.ImageDownloader;
import com.amazon.avod.content.smoothstream.manifest.StreamIndex;
import com.amazon.avod.media.playback.VideoSpecification;
import com.amazon.avod.playback.PlaybackEventReporter;
import com.amazon.avod.pmet.PlaybackPmetMetricReporter;
import com.amazon.avod.util.DLog;
import com.google.common.base.Preconditions;
import java.io.File;
import java.util.Locale;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;

/* JADX INFO: loaded from: classes2.dex */
public class ImageDownloaderFactory {
    private final ImageDownloaderConfig mConfig;
    private final StorageHelper mStorageHelper;

    /* synthetic */ ImageDownloaderFactory(AnonymousClass1 anonymousClass1) {
        this();
    }

    public static ImageDownloaderFactory getInstance() {
        return SingletonHolder.INSTANCE;
    }

    private static final class SingletonHolder {
        public static final ImageDownloaderFactory INSTANCE = new ImageDownloaderFactory(null);

        private SingletonHolder() {
        }
    }

    private ImageDownloaderFactory() {
        this.mStorageHelper = StorageHelper.getInstance();
        this.mConfig = ImageDownloaderConfig.getInstance();
    }

    @Nullable
    public ImageDownloader createImageDownloader(@Nonnull Context context, @Nonnull ContentSessionContext contentSessionContext, @Nonnull VideoSpecification videoSpecification, @Nonnull StreamIndex streamIndex, @Nonnull PlaybackEventReporter playbackEventReporter, @Nonnull ContentManagementEventBus contentManagementEventBus) {
        Preconditions.checkNotNull(context, "context");
        Preconditions.checkNotNull(contentSessionContext, "sessionContext");
        Preconditions.checkNotNull(videoSpecification, "videoSpecification");
        Preconditions.checkNotNull(streamIndex, "imageStreamIndex");
        File fileCreateLiveTrickplayRootDirectory = createLiveTrickplayRootDirectory(videoSpecification);
        ImageDownloaderReporter imageDownloaderReporter = new ImageDownloaderReporter(playbackEventReporter, contentManagementEventBus, PlaybackPmetMetricReporter.getInstance());
        ImageDownloadStrategy imageDownloadStrategy = this.mConfig.getImageDownloadStrategy(Math.abs(contentSessionContext.getState().getMediaTimeWindowEndNanos() - contentSessionContext.getState().getMediaTimeWindowStartNanos()));
        int i = AnonymousClass1.$SwitchMap$com$amazon$avod$content$image$ImageDownloadStrategy[imageDownloadStrategy.ordinal()];
        if (i == 1) {
            DLog.logf("Selecting MultipassImageDownloader");
            return new MultipassImageDownloader(context, contentSessionContext, streamIndex, fileCreateLiveTrickplayRootDirectory, imageDownloaderReporter);
        }
        if (i == 2) {
            DLog.logf("Selecting SerialImageDownloader");
            return new SerialImageDownloader(context, contentSessionContext, streamIndex, fileCreateLiveTrickplayRootDirectory, imageDownloaderReporter);
        }
        DLog.warnf("No live trickplay image downloaders enabled by config %s, returning null", imageDownloadStrategy.name());
        return null;
    }

    /* JADX INFO: renamed from: com.amazon.avod.content.image.ImageDownloaderFactory$1, reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$amazon$avod$content$image$ImageDownloadStrategy;

        static {
            int[] iArr = new int[ImageDownloadStrategy.values().length];
            $SwitchMap$com$amazon$avod$content$image$ImageDownloadStrategy = iArr;
            try {
                iArr[ImageDownloadStrategy.MULTIPASS.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$amazon$avod$content$image$ImageDownloadStrategy[ImageDownloadStrategy.SERIAL.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$com$amazon$avod$content$image$ImageDownloadStrategy[ImageDownloadStrategy.UNSUPPORTED.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
        }
    }

    @Nonnull
    private File createLiveTrickplayRootDirectory(@Nonnull VideoSpecification videoSpecification) {
        return new File(new File(this.mStorageHelper.getGeneralFileDir(), String.format(Locale.ENGLISH, "streaming-plugins/%s", videoSpecification.getTitleId())), "live_trickplay");
    }
}
