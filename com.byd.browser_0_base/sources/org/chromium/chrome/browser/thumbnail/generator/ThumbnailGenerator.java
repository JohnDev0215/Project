package org.chromium.chrome.browser.thumbnail.generator;

import android.graphics.Bitmap;
import org.chromium.base.annotations.CalledByNative;

/* JADX INFO: loaded from: classes2.dex */
public class ThumbnailGenerator {
    public long a;

    @CalledByNative
    public void onThumbnailRetrieved(String str, int i, Bitmap bitmap, ThumbnailGeneratorCallback thumbnailGeneratorCallback) {
        thumbnailGeneratorCallback.a(i, bitmap, str);
    }
}
