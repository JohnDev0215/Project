package eZZfeFZFF.eZZfeFZFF.eZZfeFZFF.EEeZFfE;

import android.app.Application;
import android.app.UiStyleManager;
import com.byd.dilink.DiLink;
import com.byd.dilink.anotation.Platform;
import com.byd.dilink.app.IDiLinkUiStyleManager;
import com.byd.foundation.annotation.ServiceImpl;

/* JADX INFO: loaded from: classes2.dex */
@Platform(isDecoupled = true, versions = {DiLink.VERSION_NAME_5_1})
@ServiceImpl(service = IDiLinkUiStyleManager.class, singleton = true)
public class ZeZE implements IDiLinkUiStyleManager {
    @Override // com.byd.dilink.app.IDiLinkUiStyleManager
    public void init(Application application) {
        UiStyleManager.init(application);
    }

    @Override // com.byd.dilink.app.IDiLinkUiStyleManager
    public void init(Application application, String str) {
        UiStyleManager.init(application, str);
    }
}
