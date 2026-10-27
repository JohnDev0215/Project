package RR9QRrr.RR9QRrr.RR9QRrr.r9R9RrrQr;

import android.app.Application;
import android.app.UiStyleManager;
import com.byd.dilink.DiLink;
import com.byd.dilink.UnsupportedTargetPlatformException;
import com.byd.dilink.anotation.Platform;
import com.byd.dilink.app.IDiLinkUiStyleManager;
import com.byd.foundation.annotation.ServiceImpl;

/* JADX INFO: loaded from: classes.dex */
@Platform(versions = {DiLink.VERSION_NAME_RCS})
@ServiceImpl(service = IDiLinkUiStyleManager.class, singleton = true)
public class q99R9q implements IDiLinkUiStyleManager {
    @Override // com.byd.dilink.app.IDiLinkUiStyleManager
    public void init(Application application) {
        UiStyleManager.init(application);
    }

    @Override // com.byd.dilink.app.IDiLinkUiStyleManager
    public void init(Application application, String str) {
        throw new UnsupportedTargetPlatformException("rcs 没有 init(Application application, String uiStylePrefix) 方法");
    }
}
