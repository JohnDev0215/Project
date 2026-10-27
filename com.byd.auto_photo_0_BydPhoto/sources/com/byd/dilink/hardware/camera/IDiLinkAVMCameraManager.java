package com.byd.dilink.hardware.camera;

import com.byd.dilink.hardware.watermark.IDiLinkWatermarkRender;

/* JADX INFO: loaded from: classes.dex */
public interface IDiLinkAVMCameraManager {
    IDiLinkAVMCamera open(int i);

    IDiLinkAVMCamera open(int i, int i2);

    IDiLinkAVMCamera open(int i, IDiLinkWatermarkRender iDiLinkWatermarkRender);

    IDiLinkAVMCamera open(int i, IDiLinkWatermarkRender iDiLinkWatermarkRender, int i2);
}
