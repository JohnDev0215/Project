package PVUVUPv.PVUVUPv.PVUVUPv.vPvP2U.VUUUVPvVV;

import com.byd.dilink.DiLink;
import com.byd.dilink.UnsupportedTargetPlatformException;
import com.byd.dilink.anotation.Platform;
import com.byd.dilink.hardware.watermark.IDiLinkWatermarkNode;
import com.byd.dilink.hardware.watermark.IDiLinkWatermarkNodeManager;
import com.byd.foundation.annotation.ServiceImpl;

/* JADX INFO: loaded from: classes.dex */
@Platform(versions = {DiLink.VERSION_NAME_RSE_5_0, DiLink.VERSION_NAME_RSE_RUI})
@ServiceImpl(service = IDiLinkWatermarkNodeManager.class, singleton = true)
public class vP implements IDiLinkWatermarkNodeManager {
    @Override // com.byd.dilink.hardware.watermark.IDiLinkWatermarkNodeManager
    public IDiLinkWatermarkNode create(int i) {
        throw new UnsupportedTargetPlatformException("RSE 没有 WatermarkNode 类！");
    }
}
