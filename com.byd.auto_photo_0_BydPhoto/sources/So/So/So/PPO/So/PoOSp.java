package So.So.So.PPO.So;

import com.byd.dilink.DiLink;
import com.byd.dilink.UnsupportedTargetPlatformException;
import com.byd.dilink.anotation.Platform;
import com.byd.dilink.hardware.camera.IDiLinkAVMCamera;
import com.byd.dilink.hardware.camera.IDiLinkAVMCameraManager;
import com.byd.dilink.hardware.watermark.IDiLinkWatermarkRender;
import com.byd.foundation.annotation.ServiceImpl;

/* JADX INFO: loaded from: classes.dex */
@Platform(versions = {DiLink.VERSION_NAME_FSE_RUI, DiLink.VERSION_NAME_FSE_5_0, DiLink.VERSION_NAME_FSE_5_1})
@ServiceImpl(service = IDiLinkAVMCameraManager.class, singleton = true)
public class PoOSp implements IDiLinkAVMCameraManager {
    @Override // com.byd.dilink.hardware.camera.IDiLinkAVMCameraManager
    public IDiLinkAVMCamera open(int i) {
        throw new UnsupportedTargetPlatformException("FSE 没有 AVMCamera！");
    }

    @Override // com.byd.dilink.hardware.camera.IDiLinkAVMCameraManager
    public IDiLinkAVMCamera open(int i, int i2) {
        throw new UnsupportedTargetPlatformException("FSE 没有 AVMCamera！");
    }

    @Override // com.byd.dilink.hardware.camera.IDiLinkAVMCameraManager
    public IDiLinkAVMCamera open(int i, IDiLinkWatermarkRender iDiLinkWatermarkRender) {
        throw new UnsupportedTargetPlatformException("FSE 没有 AVMCamera！");
    }

    @Override // com.byd.dilink.hardware.camera.IDiLinkAVMCameraManager
    public IDiLinkAVMCamera open(int i, IDiLinkWatermarkRender iDiLinkWatermarkRender, int i2) {
        throw new UnsupportedTargetPlatformException("FSE 没有 AVMCamera！");
    }
}
