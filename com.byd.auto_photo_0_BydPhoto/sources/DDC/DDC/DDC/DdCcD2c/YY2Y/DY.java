package DDC.DDC.DDC.DdCcD2c.YY2Y;

import android.hardware.watermark.WatermarkNode;
import com.byd.dilink.DiLink;
import com.byd.dilink.anotation.Platform;
import com.byd.dilink.hardware.watermark.IDiLinkWatermarkNode;
import com.byd.dilink.hardware.watermark.IDiLinkWatermarkNodeManager;
import com.byd.foundation.annotation.ServiceImpl;

/* JADX INFO: loaded from: classes.dex */
@Platform(versions = {DiLink.VERSION_NAME_5_1})
@ServiceImpl(service = IDiLinkWatermarkNodeManager.class, singleton = true)
public class DY implements IDiLinkWatermarkNodeManager {
    @Override // com.byd.dilink.hardware.watermark.IDiLinkWatermarkNodeManager
    public IDiLinkWatermarkNode create(int i) {
        WatermarkNode watermarkNodeCreate = WatermarkNode.create(i);
        if (watermarkNodeCreate == null) {
            return null;
        }
        return new c(watermarkNodeCreate);
    }
}
