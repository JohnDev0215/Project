package sst.sst.sst.StsT;

import android.app.Application;
import android.app.UiStyleManager;
import com.byd.dilink.DiLink;
import com.byd.dilink.UnsupportedTargetPlatformException;
import com.byd.dilink.anotation.Platform;
import com.byd.dilink.app.IDiLinkUiStyleManager;
import com.byd.foundation.annotation.ServiceImpl;

/* JADX INFO: loaded from: classes2.dex */
@Platform(versions = {DiLink.VERSION_NAME_RIES, DiLink.VERSION_NAME_RIES_5_0})
@ServiceImpl(service = IDiLinkUiStyleManager.class, singleton = true)
public class T1TsSsSTs implements IDiLinkUiStyleManager {
    @Override // com.byd.dilink.app.IDiLinkUiStyleManager
    public void init(Application application) {
        UiStyleManager.init(application);
    }

    @Override // com.byd.dilink.app.IDiLinkUiStyleManager
    public void init(Application application, String str) {
        throw new UnsupportedTargetPlatformException("ries 没有 init(Application application, String uiStylePrefix) 方法");
    }
}
