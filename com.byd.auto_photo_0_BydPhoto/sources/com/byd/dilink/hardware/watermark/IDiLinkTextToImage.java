package com.byd.dilink.hardware.watermark;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Typeface;
import androidx.annotation.NonNull;

/* JADX INFO: loaded from: classes.dex */
public interface IDiLinkTextToImage {
    boolean textTo12File(@NonNull Context context, @NonNull Bitmap bitmap, @NonNull String str);

    boolean textToBmpFile(@NonNull Context context, @NonNull String str, @NonNull String str2, int i, int i2, int i3, int i4, int i5, @NonNull String str3);

    boolean textToNv21File(@NonNull Context context, @NonNull Bitmap bitmap, @NonNull String str);

    boolean textToRgbFile(@NonNull Context context, @NonNull Bitmap bitmap, @NonNull String str);

    boolean textToRgbFile(@NonNull Context context, @NonNull String str, @NonNull String str2, int i, int i2, int i3, int i4, int i5, @NonNull String str3);

    @NonNull
    Bitmap txtToBitmap(@NonNull Context context, @NonNull Typeface typeface, @NonNull String str, int i, int i2, int i3, int i4, int i5);
}
