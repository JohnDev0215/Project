package BA1aabBX.BA1aabBX.BA1aabBX.AbaA;

import android.media.MediaControl;
import com.byd.dilink.DiLink;
import com.byd.dilink.anotation.Platform;
import com.byd.dilink.media.IDiLinkMediaControl;
import com.byd.foundation.annotation.ServiceImpl;

/* JADX INFO: loaded from: classes.dex */
@Platform(isDecoupled = false, versions = {DiLink.VERSION_NAME_5_0})
@ServiceImpl(service = IDiLinkMediaControl.class, singleton = true)
public class BA1aabBX implements IDiLinkMediaControl {
    @Override // com.byd.dilink.media.IDiLinkMediaControl
    public boolean isInstrumentInfoApp(String str) {
        return MediaControl.isInstrumentInfoApp(str);
    }
}
