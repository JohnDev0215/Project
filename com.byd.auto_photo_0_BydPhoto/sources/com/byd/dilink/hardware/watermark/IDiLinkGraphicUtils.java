package com.byd.dilink.hardware.watermark;

import android.graphics.Bitmap;
import androidx.annotation.NonNull;
import java.io.File;

/* JADX INFO: loaded from: classes.dex */
public interface IDiLinkGraphicUtils {
    void ARGBToNv21(byte[] bArr, int[] iArr, int i, int i2);

    @NonNull
    byte[] BitmapToNv21(int i, int i2, @NonNull Bitmap bitmap);

    @NonNull
    Bitmap createBitmap(int i, int i2, float f, int i3);

    boolean freeBitmap(@NonNull Bitmap bitmap);

    boolean saveData(@NonNull byte[] bArr, @NonNull String str, @NonNull String str2, int i);

    boolean savePngFile(@NonNull Bitmap bitmap, @NonNull String str, @NonNull String str2);

    boolean saveYUVFile(@NonNull Bitmap bitmap, int i, int i2, @NonNull File file);
}
