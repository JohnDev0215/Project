package com.byd.dilink.hardware.watermark;

import android.content.Context;
import androidx.annotation.NonNull;

/* JADX INFO: loaded from: classes.dex */
public interface IDiLinkFontUtils {
    @NonNull
    String convertColorToString(int i);

    @NonNull
    String createPNGName(@NonNull String str, int i, int i2);

    @NonNull
    String createRGBAName(@NonNull String str, int i, int i2);

    boolean createTxtBmp(@NonNull Context context, @NonNull String str, int i, int i2, int i3, int i4, int i5);

    boolean createTxtRGBA(@NonNull Context context, @NonNull String str, @NonNull String str2, int i, int i2, int i3, int i4);

    boolean createTxtRGBA(@NonNull Context context, @NonNull String str, @NonNull String str2, int i, int i2, int i3, int i4, int i5);
}
