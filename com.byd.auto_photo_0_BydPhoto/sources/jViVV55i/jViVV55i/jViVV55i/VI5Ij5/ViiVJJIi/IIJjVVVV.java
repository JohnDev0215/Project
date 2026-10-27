package jViVV55i.jViVV55i.jViVV55i.VI5Ij5.ViiVJJIi;

import com.byd.dilink.DiLink;
import com.byd.dilink.UnsupportedTargetPlatformException;
import com.byd.dilink.anotation.Platform;
import com.byd.dilink.hardware.watermark.IDiLinkWatermarkNode;
import com.byd.dilink.hardware.watermark.IDiLinkWatermarkNodeManager;
import com.byd.foundation.annotation.ServiceImpl;

/* JADX INFO: loaded from: classes2.dex */
@Platform(versions = {DiLink.VERSION_NAME_3_0, DiLink.VERSION_NAME_3_0_3_0})
@ServiceImpl(service = IDiLinkWatermarkNodeManager.class, singleton = true)
public class IIJjVVVV implements IDiLinkWatermarkNodeManager {
    @Override // com.byd.dilink.hardware.watermark.IDiLinkWatermarkNodeManager
    public IDiLinkWatermarkNode create(int i) {
        throw new UnsupportedTargetPlatformException("Di3.0 没有 WatermarkNode 类！");
    }
}
