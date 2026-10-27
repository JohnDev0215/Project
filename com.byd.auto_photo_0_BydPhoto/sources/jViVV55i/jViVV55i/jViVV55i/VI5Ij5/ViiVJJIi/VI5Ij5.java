package jViVV55i.jViVV55i.jViVV55i.VI5Ij5.ViiVJJIi;

import com.byd.dilink.DiLink;
import com.byd.dilink.UnsupportedTargetPlatformException;
import com.byd.dilink.anotation.Platform;
import com.byd.dilink.hardware.watermark.IDiLinkWatermarkNodeGroup;
import com.byd.dilink.hardware.watermark.IDiLinkWatermarkNodeGroupManager;
import com.byd.foundation.annotation.ServiceImpl;

/* JADX INFO: loaded from: classes2.dex */
@Platform(versions = {DiLink.VERSION_NAME_3_0, DiLink.VERSION_NAME_3_0_3_0})
@ServiceImpl(service = IDiLinkWatermarkNodeGroupManager.class, singleton = true)
public class VI5Ij5 implements IDiLinkWatermarkNodeGroupManager {
    @Override // com.byd.dilink.hardware.watermark.IDiLinkWatermarkNodeGroupManager
    public IDiLinkWatermarkNodeGroup create(int i) {
        throw new UnsupportedTargetPlatformException("Di3.0 没有 WatermarkNodeGroup 类！");
    }
}
