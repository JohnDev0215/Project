package So.So.So.PPO.So;

import com.byd.dilink.DiLink;
import com.byd.dilink.UnsupportedTargetPlatformException;
import com.byd.dilink.anotation.Platform;
import com.byd.dilink.hardware.camera.IDiLinkNormalCamera;
import com.byd.dilink.hardware.camera.IDiLinkNormalCameraManager;
import com.byd.dilink.hardware.watermark.IDiLinkWatermarkRender;
import com.byd.foundation.annotation.ServiceImpl;

/* JADX INFO: loaded from: classes.dex */
@Platform(versions = {DiLink.VERSION_NAME_FSE_RUI, DiLink.VERSION_NAME_FSE_5_0, DiLink.VERSION_NAME_FSE_5_1})
@ServiceImpl(service = IDiLinkNormalCameraManager.class, singleton = true)
public class PPO implements IDiLinkNormalCameraManager {
    @Override // com.byd.dilink.hardware.camera.IDiLinkNormalCameraManager
    public IDiLinkNormalCamera open(int i) {
        throw new UnsupportedTargetPlatformException("FSE 没有 NormalCamera！");
    }

    @Override // com.byd.dilink.hardware.camera.IDiLinkNormalCameraManager
    public IDiLinkNormalCamera open(int i, int i2) {
        throw new UnsupportedTargetPlatformException("FSE 没有 NormalCamera！");
    }

    @Override // com.byd.dilink.hardware.camera.IDiLinkNormalCameraManager
    public IDiLinkNormalCamera open(int i, IDiLinkWatermarkRender iDiLinkWatermarkRender) {
        throw new UnsupportedTargetPlatformException("FSE 没有 NormalCamera！");
    }

    @Override // com.byd.dilink.hardware.camera.IDiLinkNormalCameraManager
    public IDiLinkNormalCamera open(int i, IDiLinkWatermarkRender iDiLinkWatermarkRender, int i2) {
        throw new UnsupportedTargetPlatformException("FSE 没有 NormalCamera！");
    }
}
