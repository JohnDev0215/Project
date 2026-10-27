package com.byd.dilink.hardware.watermark;

import androidx.annotation.NonNull;
import java.nio.ByteBuffer;

/* JADX INFO: loaded from: classes.dex */
public interface IDiLinkWatermarkRender {
    boolean addFont(@NonNull String str, int i, @NonNull String str2, @NonNull String str3);

    boolean addNode(@NonNull IDiLinkWatermarkNode iDiLinkWatermarkNode);

    boolean addNodeGroup(@NonNull IDiLinkWatermarkNodeGroup iDiLinkWatermarkNodeGroup);

    boolean lock();

    boolean render(@NonNull byte[] bArr, int i, int i2, int i3, long j);

    boolean renderForDirect(@NonNull ByteBuffer byteBuffer, int i, int i2, int i3, long j);

    boolean rmNode(@NonNull IDiLinkWatermarkNode iDiLinkWatermarkNode);

    boolean rmNodeGroup(@NonNull IDiLinkWatermarkNodeGroup iDiLinkWatermarkNodeGroup);

    boolean setNodeFont(@NonNull IDiLinkWatermarkNode iDiLinkWatermarkNode, @NonNull String str, int i, @NonNull String str2, @NonNull String str3);

    void unlock();
}
