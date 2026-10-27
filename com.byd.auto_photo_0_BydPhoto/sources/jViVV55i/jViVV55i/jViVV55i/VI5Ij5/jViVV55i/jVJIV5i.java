package jViVV55i.jViVV55i.jViVV55i.VI5Ij5.jViVV55i;

import com.byd.dilink.DiLink;
import com.byd.dilink.UnsupportedTargetPlatformException;
import com.byd.dilink.anotation.Platform;
import com.byd.dilink.hardware.camera.IDiLinkAVMCamera;
import com.byd.dilink.hardware.camera.IDiLinkAVMCameraManager;
import com.byd.dilink.hardware.watermark.IDiLinkWatermarkRender;
import com.byd.foundation.annotation.ServiceImpl;

/* JADX INFO: loaded from: classes2.dex */
@Platform(versions = {DiLink.VERSION_NAME_3_0, DiLink.VERSION_NAME_3_0_3_0})
@ServiceImpl(service = IDiLinkAVMCameraManager.class, singleton = true)
public class jVJIV5i implements IDiLinkAVMCameraManager {
    @Override // com.byd.dilink.hardware.camera.IDiLinkAVMCameraManager
    public IDiLinkAVMCamera open(int i) {
        throw new UnsupportedTargetPlatformException("Di3.0 没有 AVMCamera！");
    }

    @Override // com.byd.dilink.hardware.camera.IDiLinkAVMCameraManager
    public IDiLinkAVMCamera open(int i, int i2) {
        throw new UnsupportedTargetPlatformException("Di3.0 没有 AVMCamera！");
    }

    @Override // com.byd.dilink.hardware.camera.IDiLinkAVMCameraManager
    public IDiLinkAVMCamera open(int i, IDiLinkWatermarkRender iDiLinkWatermarkRender) {
        throw new UnsupportedTargetPlatformException("Di3.0 没有 AVMCamera！");
    }

    @Override // com.byd.dilink.hardware.camera.IDiLinkAVMCameraManager
    public IDiLinkAVMCamera open(int i, IDiLinkWatermarkRender iDiLinkWatermarkRender, int i2) {
        throw new UnsupportedTargetPlatformException("Di3.0 没有 AVMCamera！");
    }
}
