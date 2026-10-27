package lkl6Kl.lkl6Kl.lkl6Kl.KLLlL6L;

import android.graphics.Bitmap;
import android.graphics.ColorSpace;
import com.byd.dilink.DiLink;
import com.byd.dilink.anotation.Platform;
import com.byd.dilink.app.am.IDiLinkGraphicBuffer;
import com.byd.dilink.graphics.IDiLinkGraphicBufferUtil;
import com.byd.foundation.annotation.ServiceImpl;
import lkl6Kl.lkl6Kl.lkl6Kl.K6.k66lUkK.a;

/* JADX INFO: loaded from: classes2.dex */
@Platform(versions = {DiLink.VERSION_NAME_4_0})
@ServiceImpl(service = IDiLinkGraphicBufferUtil.class, singleton = true)
public class lkl6Kl implements IDiLinkGraphicBufferUtil {
    @Override // com.byd.dilink.graphics.IDiLinkGraphicBufferUtil
    public Bitmap wrapHardwareBuffer(IDiLinkGraphicBuffer iDiLinkGraphicBuffer, ColorSpace colorSpace) {
        return Bitmap.wrapHardwareBuffer(((a) iDiLinkGraphicBuffer).a(), colorSpace);
    }
}
