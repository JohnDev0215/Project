package xxWX.xxWX.xxWX.xWOwOW;

import com.byd.dilink.DiLink;
import com.byd.dilink.UnsupportedTargetPlatformException;
import com.byd.dilink.anotation.Platform;
import com.byd.dilink.media.IDiLinkMediaControl;
import com.byd.foundation.annotation.ServiceImpl;

/* JADX INFO: loaded from: classes2.dex */
@Platform(isDecoupled = false, versions = {DiLink.VERSION_NAME_3_0_3_0})
@ServiceImpl(service = IDiLinkMediaControl.class, singleton = true)
public class xxWX implements IDiLinkMediaControl {
    @Override // com.byd.dilink.media.IDiLinkMediaControl
    public boolean isInstrumentInfoApp(String str) {
        throw new UnsupportedTargetPlatformException("Di3.0 没有这个接口!");
    }
}
