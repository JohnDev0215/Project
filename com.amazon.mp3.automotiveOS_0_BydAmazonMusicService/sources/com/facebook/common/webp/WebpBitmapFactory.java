package com.facebook.common.webp;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Rect;
import java.io.FileDescriptor;
import javax.annotation.Nullable;

/* JADX INFO: loaded from: classes5.dex */
public interface WebpBitmapFactory {

    public interface WebpErrorLogger {
    }

    @Nullable
    Bitmap decodeFileDescriptor(FileDescriptor fd, @Nullable Rect outPadding, @Nullable BitmapFactory.Options opts);

    void setBitmapCreator(final BitmapCreator bitmapCreator);

    void setWebpErrorLogger(WebpErrorLogger logger);
}
