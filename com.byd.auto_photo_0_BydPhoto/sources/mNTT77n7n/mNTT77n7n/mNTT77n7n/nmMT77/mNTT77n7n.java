package mNTT77n7n.mNTT77n7n.mNTT77n7n.nmMT77;

import android.media.MediaControl;
import com.byd.dilink.DiLink;
import com.byd.dilink.anotation.Platform;
import com.byd.dilink.media.IDiLinkMediaControl;
import com.byd.foundation.annotation.ServiceImpl;

/* JADX INFO: loaded from: classes2.dex */
@Platform(isDecoupled = false, versions = {DiLink.VERSION_NAME_DiR_RUI})
@ServiceImpl(service = IDiLinkMediaControl.class, singleton = true)
public class mNTT77n7n implements IDiLinkMediaControl {
    @Override // com.byd.dilink.media.IDiLinkMediaControl
    public boolean isInstrumentInfoApp(String str) {
        return MediaControl.isInstrumentInfoApp(str);
    }
}
