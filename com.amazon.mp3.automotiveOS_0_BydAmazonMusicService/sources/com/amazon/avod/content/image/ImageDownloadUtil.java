package com.amazon.avod.content.image;

import com.amazon.avod.util.DLog;
import com.google.common.base.Preconditions;
import com.google.common.base.Predicate;
import com.google.common.collect.FluentIterable;
import java.util.concurrent.TimeUnit;
import javax.annotation.Nonnull;

/* JADX INFO: loaded from: classes2.dex */
public final class ImageDownloadUtil {
    @Nonnull
    public static ImageQualityLevelInterface[] filterImageQualities(@Nonnull ImageQualityLevelInterface[] imageQualityLevelInterfaceArr, final int i) {
        Preconditions.checkNotNull(imageQualityLevelInterfaceArr, "imageQualities");
        ImageQualityLevelInterface[] imageQualityLevelInterfaceArr2 = (ImageQualityLevelInterface[]) FluentIterable.from(imageQualityLevelInterfaceArr).filter(new Predicate() { // from class: com.amazon.avod.content.image.ImageDownloadUtil$$ExternalSyntheticLambda0
            @Override // com.google.common.base.Predicate
            public final boolean apply(Object obj) {
                return ImageDownloadUtil.lambda$filterImageQualities$0(i, (ImageQualityLevelInterface) obj);
            }
        }).toArray(ImageQualityLevelInterface.class);
        if (imageQualityLevelInterfaceArr2.length > 0) {
            return imageQualityLevelInterfaceArr2;
        }
        DLog.warnf("ImageDownloader none with horizontal resolution == %s, returning original imageQuality list", Integer.valueOf(i));
        return imageQualityLevelInterfaceArr;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ boolean lambda$filterImageQualities$0(int i, ImageQualityLevelInterface imageQualityLevelInterface) {
        return imageQualityLevelInterface != null && imageQualityLevelInterface.getThumbnailWidthInPixels() == i;
    }

    @Nonnull
    public static ImageQualityLevelInterface selectQualityLevel(@Nonnull ImageQualityLevelInterface[] imageQualityLevelInterfaceArr, int i) {
        Preconditions.checkState(imageQualityLevelInterfaceArr.length > 0, "qualityLevels cannot be empty");
        for (int length = imageQualityLevelInterfaceArr.length - 1; length >= 0; length--) {
            if (TimeUnit.NANOSECONDS.toSeconds(imageQualityLevelInterfaceArr[length].getThumbnailDurationNanos()) <= i) {
                return imageQualityLevelInterfaceArr[length];
            }
        }
        DLog.warnf("ImageDownloader none with thumbnail duration <= %s s, returning the first image quality level", Integer.valueOf(i));
        return imageQualityLevelInterfaceArr[0];
    }
}
