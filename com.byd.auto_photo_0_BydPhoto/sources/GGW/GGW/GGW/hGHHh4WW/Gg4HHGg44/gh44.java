package GGW.GGW.GGW.hGHHh4WW.Gg4HHGg44;

import android.hardware.watermark.WatermarkNode;
import com.byd.dilink.anotation.Platform;
import com.byd.dilink.hardware.watermark.IDiLinkWatermarkNode;
import com.byd.dilink.hardware.watermark.IDiLinkWatermarkNodeManager;
import com.byd.foundation.annotation.ServiceImpl;

/* JADX INFO: loaded from: classes.dex */
@Platform(versions = {"RUI"})
@ServiceImpl(service = IDiLinkWatermarkNodeManager.class, singleton = true)
public class gh44 implements IDiLinkWatermarkNodeManager {
    @Override // com.byd.dilink.hardware.watermark.IDiLinkWatermarkNodeManager
    public IDiLinkWatermarkNode create(int i) {
        WatermarkNode watermarkNodeCreate = WatermarkNode.create(i);
        if (watermarkNodeCreate == null) {
            return null;
        }
        return new b(watermarkNodeCreate);
    }
}
