package So.So.So.PPO.PopS8Sp;

import com.byd.dilink.DiLink;
import com.byd.dilink.UnsupportedTargetPlatformException;
import com.byd.dilink.anotation.Platform;
import com.byd.dilink.hardware.watermark.IDiLinkWatermarkNodeGroup;
import com.byd.dilink.hardware.watermark.IDiLinkWatermarkNodeGroupManager;
import com.byd.foundation.annotation.ServiceImpl;

/* JADX INFO: loaded from: classes.dex */
@Platform(versions = {DiLink.VERSION_NAME_FSE_RUI, DiLink.VERSION_NAME_FSE_5_0, DiLink.VERSION_NAME_FSE_5_1})
@ServiceImpl(service = IDiLinkWatermarkNodeGroupManager.class, singleton = true)
public class PPO implements IDiLinkWatermarkNodeGroupManager {
    @Override // com.byd.dilink.hardware.watermark.IDiLinkWatermarkNodeGroupManager
    public IDiLinkWatermarkNodeGroup create(int i) {
        throw new UnsupportedTargetPlatformException("FSE 没有 WatermarkNodeGroup 类！");
    }
}
