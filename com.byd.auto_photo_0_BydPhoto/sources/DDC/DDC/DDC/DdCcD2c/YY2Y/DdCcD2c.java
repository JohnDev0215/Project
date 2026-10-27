package DDC.DDC.DDC.DdCcD2c.YY2Y;

import android.hardware.watermark.WatermarkNodeGroup;
import com.byd.dilink.DiLink;
import com.byd.dilink.anotation.Platform;
import com.byd.dilink.hardware.watermark.IDiLinkWatermarkNodeGroup;
import com.byd.dilink.hardware.watermark.IDiLinkWatermarkNodeGroupManager;
import com.byd.foundation.annotation.ServiceImpl;

/* JADX INFO: loaded from: classes.dex */
@Platform(versions = {DiLink.VERSION_NAME_5_1})
@ServiceImpl(service = IDiLinkWatermarkNodeGroupManager.class, singleton = true)
public class DdCcD2c implements IDiLinkWatermarkNodeGroupManager {
    @Override // com.byd.dilink.hardware.watermark.IDiLinkWatermarkNodeGroupManager
    public IDiLinkWatermarkNodeGroup create(int i) {
        WatermarkNodeGroup watermarkNodeGroupCreate = WatermarkNodeGroup.create(i);
        if (watermarkNodeGroupCreate == null) {
            return null;
        }
        return new b(watermarkNodeGroupCreate);
    }
}
