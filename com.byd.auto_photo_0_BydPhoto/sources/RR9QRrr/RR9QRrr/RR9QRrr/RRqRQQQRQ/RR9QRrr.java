package RR9QRrr.RR9QRrr.RR9QRrr.RRqRQQQRQ;

import RR9QRrr.RR9QRrr.RR9QRrr.r9R9RrrQr.RqqqQRRR9.a;
import android.graphics.Bitmap;
import android.graphics.ColorSpace;
import com.byd.dilink.DiLink;
import com.byd.dilink.anotation.Platform;
import com.byd.dilink.app.am.IDiLinkGraphicBuffer;
import com.byd.dilink.graphics.IDiLinkGraphicBufferUtil;
import com.byd.foundation.annotation.ServiceImpl;

/* JADX INFO: loaded from: classes.dex */
@Platform(versions = {DiLink.VERSION_NAME_RCS})
@ServiceImpl(service = IDiLinkGraphicBufferUtil.class, singleton = true)
public class RR9QRrr implements IDiLinkGraphicBufferUtil {
    @Override // com.byd.dilink.graphics.IDiLinkGraphicBufferUtil
    public Bitmap wrapHardwareBuffer(IDiLinkGraphicBuffer iDiLinkGraphicBuffer, ColorSpace colorSpace) {
        return Bitmap.wrapHardwareBuffer(((a) iDiLinkGraphicBuffer).a(), colorSpace);
    }
}
