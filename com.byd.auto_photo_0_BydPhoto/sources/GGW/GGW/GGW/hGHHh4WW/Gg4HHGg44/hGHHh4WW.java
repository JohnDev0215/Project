package GGW.GGW.GGW.hGHHh4WW.Gg4HHGg44;

import android.hardware.watermark.WatermarkNodeGroup;
import com.byd.dilink.anotation.Platform;
import com.byd.dilink.hardware.watermark.IDiLinkWatermarkNodeGroup;
import com.byd.dilink.hardware.watermark.IDiLinkWatermarkNodeGroupManager;
import com.byd.foundation.annotation.ServiceImpl;

/* JADX INFO: loaded from: classes.dex */
@Platform(versions = {"RUI"})
@ServiceImpl(service = IDiLinkWatermarkNodeGroupManager.class, singleton = true)
public class hGHHh4WW implements IDiLinkWatermarkNodeGroupManager {
    @Override // com.byd.dilink.hardware.watermark.IDiLinkWatermarkNodeGroupManager
    public IDiLinkWatermarkNodeGroup create(int i) {
        WatermarkNodeGroup watermarkNodeGroupCreate = WatermarkNodeGroup.create(i);
        if (watermarkNodeGroupCreate == null) {
            return null;
        }
        return new a(watermarkNodeGroupCreate);
    }
}
