package RR9QRrr.RR9QRrr.RR9QRrr.RRrr9r9.RR9QRrr;

import com.byd.dilink.DiLink;
import com.byd.dilink.UnsupportedTargetPlatformException;
import com.byd.dilink.anotation.Platform;
import com.byd.dilink.hardware.camera.IDiLinkAVMCamera;
import com.byd.dilink.hardware.camera.IDiLinkAVMCameraManager;
import com.byd.dilink.hardware.watermark.IDiLinkWatermarkRender;
import com.byd.foundation.annotation.ServiceImpl;

/* JADX INFO: loaded from: classes.dex */
@Platform(versions = {DiLink.VERSION_NAME_RCS})
@ServiceImpl(service = IDiLinkAVMCameraManager.class, singleton = true)
public class R9rQ9R implements IDiLinkAVMCameraManager {
    @Override // com.byd.dilink.hardware.camera.IDiLinkAVMCameraManager
    public IDiLinkAVMCamera open(int i) {
        throw new UnsupportedTargetPlatformException("rcs 没有 AVMCamera！");
    }

    @Override // com.byd.dilink.hardware.camera.IDiLinkAVMCameraManager
    public IDiLinkAVMCamera open(int i, int i2) {
        throw new UnsupportedTargetPlatformException("rcs 没有 AVMCamera！");
    }

    @Override // com.byd.dilink.hardware.camera.IDiLinkAVMCameraManager
    public IDiLinkAVMCamera open(int i, IDiLinkWatermarkRender iDiLinkWatermarkRender) {
        throw new UnsupportedTargetPlatformException("rcs 没有 AVMCamera！");
    }

    @Override // com.byd.dilink.hardware.camera.IDiLinkAVMCameraManager
    public IDiLinkAVMCamera open(int i, IDiLinkWatermarkRender iDiLinkWatermarkRender, int i2) {
        throw new UnsupportedTargetPlatformException("rcs 没有 AVMCamera！");
    }
}
