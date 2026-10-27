package com.byd.dilink.hardware.camera;

import com.byd.dilink.hardware.watermark.IDiLinkWatermarkRender;

/* JADX INFO: loaded from: classes.dex */
public interface IDiLinkNormalCameraManager {
    IDiLinkNormalCamera open(int i);

    IDiLinkNormalCamera open(int i, int i2);

    IDiLinkNormalCamera open(int i, IDiLinkWatermarkRender iDiLinkWatermarkRender);

    IDiLinkNormalCamera open(int i, IDiLinkWatermarkRender iDiLinkWatermarkRender, int i2);
}
