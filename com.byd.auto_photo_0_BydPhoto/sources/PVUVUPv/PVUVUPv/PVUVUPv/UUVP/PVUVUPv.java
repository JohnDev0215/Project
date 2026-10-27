package PVUVUPv.PVUVUPv.PVUVUPv.UUVP;

import android.media.MediaControl;
import com.byd.dilink.DiLink;
import com.byd.dilink.anotation.Platform;
import com.byd.dilink.media.IDiLinkMediaControl;
import com.byd.foundation.annotation.ServiceImpl;

/* JADX INFO: loaded from: classes.dex */
@Platform(isDecoupled = false, versions = {DiLink.VERSION_NAME_RSE_5_0, DiLink.VERSION_NAME_RSE_RUI})
@ServiceImpl(service = IDiLinkMediaControl.class, singleton = true)
public class PVUVUPv implements IDiLinkMediaControl {
    @Override // com.byd.dilink.media.IDiLinkMediaControl
    public boolean isInstrumentInfoApp(String str) {
        return MediaControl.isInstrumentInfoApp(str);
    }
}
