package com.byd.dilink.hardware.watermark;

import androidx.annotation.NonNull;
import java.nio.ByteBuffer;

/* JADX INFO: loaded from: classes.dex */
public interface IDiLinkWatermarkNode {
    boolean disable();

    boolean enable();

    int getKey();

    boolean isEnable();

    void release();

    boolean render(@NonNull byte[] bArr, int i, int i2, int i3, long j);

    boolean renderDirect(@NonNull ByteBuffer byteBuffer, int i, int i2, int i3, long j);

    boolean setFontPath(@NonNull String str, int i, @NonNull String str2, @NonNull String str3);

    boolean setImgArea(int i, int i2, int i3, int i4);

    boolean setImgPath(int i, @NonNull String str);

    boolean setText(@NonNull String str);

    boolean setTextArea(int i, int i2, int i3, int i4);
}
