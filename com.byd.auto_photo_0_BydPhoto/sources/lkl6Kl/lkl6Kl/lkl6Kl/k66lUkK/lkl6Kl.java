package lkl6Kl.lkl6Kl.lkl6Kl.k66lUkK;

import android.media.MediaControl;
import com.byd.dilink.DiLink;
import com.byd.dilink.anotation.Platform;
import com.byd.dilink.media.IDiLinkMediaControl;
import com.byd.foundation.annotation.ServiceImpl;

/* JADX INFO: loaded from: classes2.dex */
@Platform(isDecoupled = false, versions = {DiLink.VERSION_NAME_4_0})
@ServiceImpl(service = IDiLinkMediaControl.class, singleton = true)
public class lkl6Kl implements IDiLinkMediaControl {
    @Override // com.byd.dilink.media.IDiLinkMediaControl
    public boolean isInstrumentInfoApp(String str) {
        return MediaControl.isInstrumentInfoApp(str);
    }
}
