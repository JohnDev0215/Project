package RR9QRrr.RR9QRrr.RR9QRrr.RRrr9r9.RrRRrrrQr;

import com.byd.dilink.DiLink;
import com.byd.dilink.UnsupportedTargetPlatformException;
import com.byd.dilink.anotation.Platform;
import com.byd.dilink.hardware.watermark.IDiLinkWatermarkNode;
import com.byd.dilink.hardware.watermark.IDiLinkWatermarkNodeManager;
import com.byd.foundation.annotation.ServiceImpl;

/* JADX INFO: loaded from: classes.dex */
@Platform(versions = {DiLink.VERSION_NAME_RCS})
@ServiceImpl(service = IDiLinkWatermarkNodeManager.class, singleton = true)
public class q99R9q implements IDiLinkWatermarkNodeManager {
    @Override // com.byd.dilink.hardware.watermark.IDiLinkWatermarkNodeManager
    public IDiLinkWatermarkNode create(int i) {
        throw new UnsupportedTargetPlatformException("rcs 没有 WatermarkNode 类！");
    }
}
