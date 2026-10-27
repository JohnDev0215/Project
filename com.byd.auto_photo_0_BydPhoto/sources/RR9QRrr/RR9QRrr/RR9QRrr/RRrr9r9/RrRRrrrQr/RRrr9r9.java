package RR9QRrr.RR9QRrr.RR9QRrr.RRrr9r9.RrRRrrrQr;

import com.byd.dilink.DiLink;
import com.byd.dilink.UnsupportedTargetPlatformException;
import com.byd.dilink.anotation.Platform;
import com.byd.dilink.hardware.watermark.IDiLinkWatermarkNodeGroup;
import com.byd.dilink.hardware.watermark.IDiLinkWatermarkNodeGroupManager;
import com.byd.foundation.annotation.ServiceImpl;

/* JADX INFO: loaded from: classes.dex */
@Platform(versions = {DiLink.VERSION_NAME_RCS})
@ServiceImpl(service = IDiLinkWatermarkNodeGroupManager.class, singleton = true)
public class RRrr9r9 implements IDiLinkWatermarkNodeGroupManager {
    @Override // com.byd.dilink.hardware.watermark.IDiLinkWatermarkNodeGroupManager
    public IDiLinkWatermarkNodeGroup create(int i) {
        throw new UnsupportedTargetPlatformException("rcs 没有 WatermarkNodeGroup 类！");
    }
}
