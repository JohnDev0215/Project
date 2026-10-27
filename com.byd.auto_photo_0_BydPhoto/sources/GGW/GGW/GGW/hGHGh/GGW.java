package GGW.GGW.GGW.hGHGh;

import android.media.MediaControl;
import com.byd.dilink.anotation.Platform;
import com.byd.dilink.media.IDiLinkMediaControl;
import com.byd.foundation.annotation.ServiceImpl;

/* JADX INFO: loaded from: classes.dex */
@Platform(isDecoupled = false, versions = {"RUI"})
@ServiceImpl(service = IDiLinkMediaControl.class, singleton = true)
public class GGW implements IDiLinkMediaControl {
    @Override // com.byd.dilink.media.IDiLinkMediaControl
    public boolean isInstrumentInfoApp(String str) {
        return MediaControl.isInstrumentInfoApp(str);
    }
}
