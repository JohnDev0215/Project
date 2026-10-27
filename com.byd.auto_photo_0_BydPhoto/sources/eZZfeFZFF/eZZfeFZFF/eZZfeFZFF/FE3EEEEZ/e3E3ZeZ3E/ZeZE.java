package eZZfeFZFF.eZZfeFZFF.eZZfeFZFF.FE3EEEEZ.e3E3ZeZ3E;

import android.hardware.watermark.WatermarkNode;
import com.byd.dilink.DiLink;
import com.byd.dilink.anotation.Platform;
import com.byd.dilink.hardware.watermark.IDiLinkWatermarkNode;
import com.byd.dilink.hardware.watermark.IDiLinkWatermarkNodeManager;
import com.byd.foundation.annotation.ServiceImpl;

/* JADX INFO: loaded from: classes2.dex */
@Platform(isDecoupled = true, versions = {DiLink.VERSION_NAME_5_1})
@ServiceImpl(service = IDiLinkWatermarkNodeManager.class, singleton = true)
public class ZeZE implements IDiLinkWatermarkNodeManager {
    @Override // com.byd.dilink.hardware.watermark.IDiLinkWatermarkNodeManager
    public IDiLinkWatermarkNode create(int i) {
        WatermarkNode watermarkNodeCreate = WatermarkNode.create(i);
        if (watermarkNodeCreate == null) {
            return null;
        }
        return new c(watermarkNodeCreate);
    }
}
