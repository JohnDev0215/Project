package DDC.DDC.DDC.DdCcD2c.YY2Y;

import android.content.Context;
import android.hardware.watermark.WatermarkRender;
import com.byd.dilink.DiLink;
import com.byd.dilink.anotation.Platform;
import com.byd.dilink.hardware.watermark.IDiLinkWatermarkRender;
import com.byd.dilink.hardware.watermark.IDiLinkWatermarkRenderManager;
import com.byd.foundation.annotation.ServiceImpl;

/* JADX INFO: loaded from: classes.dex */
@Platform(versions = {DiLink.VERSION_NAME_5_1})
@ServiceImpl(service = IDiLinkWatermarkRenderManager.class, singleton = true)
public class DcDc2c22Y implements IDiLinkWatermarkRenderManager {
    @Override // com.byd.dilink.hardware.watermark.IDiLinkWatermarkRenderManager
    public IDiLinkWatermarkRender create(Context context) {
        WatermarkRender watermarkRenderCreate = WatermarkRender.create(context);
        if (watermarkRenderCreate == null) {
            return null;
        }
        return new a(watermarkRenderCreate);
    }
}
