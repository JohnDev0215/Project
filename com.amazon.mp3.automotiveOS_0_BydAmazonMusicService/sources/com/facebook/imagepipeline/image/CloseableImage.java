package com.facebook.imagepipeline.image;

import com.facebook.common.logging.FLog;
import java.io.Closeable;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import javax.annotation.Nullable;

/* JADX INFO: loaded from: classes3.dex */
public abstract class CloseableImage implements Closeable, ImageInfo {
    private static final Set<String> mImageExtrasList = new HashSet(Arrays.asList("encoded_size", "encoded_width", "encoded_height", "uri_source", "image_format", "bitmap_config", "is_rounded"));
    private Map<String, Object> mExtras = new HashMap();

    @Override // java.io.Closeable, java.lang.AutoCloseable
    public abstract void close();

    public abstract /* synthetic */ int getHeight();

    public abstract int getSizeInBytes();

    public abstract /* synthetic */ int getWidth();

    public abstract boolean isClosed();

    public boolean isStateful() {
        return false;
    }

    public QualityInfo getQualityInfo() {
        return ImmutableQualityInfo.FULL_QUALITY;
    }

    @Override // com.facebook.imagepipeline.image.HasImageMetadata
    public Map<String, Object> getExtras() {
        return this.mExtras;
    }

    public void setImageExtras(@Nullable Map<String, Object> extras) {
        if (extras == null) {
            return;
        }
        for (String str : mImageExtrasList) {
            Object obj = extras.get(str);
            if (obj != null) {
                this.mExtras.put(str, obj);
            }
        }
    }

    public void setImageExtra(String extra, Object value) {
        if (mImageExtrasList.contains(extra)) {
            this.mExtras.put(extra, value);
        }
    }

    protected void finalize() throws Throwable {
        if (isClosed()) {
            return;
        }
        FLog.w("CloseableImage", "finalize: %s %x still open.", getClass().getSimpleName(), Integer.valueOf(System.identityHashCode(this)));
        try {
            close();
        } finally {
            super.finalize();
        }
    }
}
