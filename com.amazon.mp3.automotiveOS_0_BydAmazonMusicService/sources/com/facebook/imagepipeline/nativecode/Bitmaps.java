package com.facebook.imagepipeline.nativecode;

import android.graphics.Bitmap;
import com.facebook.common.internal.DoNotStrip;
import com.facebook.common.internal.Preconditions;

/* JADX INFO: loaded from: classes5.dex */
@DoNotStrip
public class Bitmaps {
    public static final /* synthetic */ int $r8$clinit = 0;

    @DoNotStrip
    private static native void nativeCopyBitmap(Bitmap dest, int destStride, Bitmap src, int srcStride, int rows);

    static {
        ImagePipelineNativeLoader.load();
    }

    @DoNotStrip
    public static void copyBitmap(Bitmap dest, Bitmap src) {
        Preconditions.checkArgument(Boolean.valueOf(src.getConfig() == dest.getConfig()));
        Preconditions.checkArgument(Boolean.valueOf(dest.isMutable()));
        Preconditions.checkArgument(Boolean.valueOf(dest.getWidth() == src.getWidth()));
        Preconditions.checkArgument(Boolean.valueOf(dest.getHeight() == src.getHeight()));
        nativeCopyBitmap(dest, dest.getRowBytes(), src, src.getRowBytes(), dest.getHeight());
    }
}
