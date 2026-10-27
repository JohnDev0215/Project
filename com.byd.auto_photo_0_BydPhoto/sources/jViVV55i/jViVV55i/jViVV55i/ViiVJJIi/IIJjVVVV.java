package jViVV55i.jViVV55i.jViVV55i.ViiVJJIi;

import android.app.Application;
import com.byd.dilink.DiLink;
import com.byd.dilink.UnsupportedTargetPlatformException;
import com.byd.dilink.anotation.Platform;
import com.byd.dilink.app.IDiLinkUiStyleManager;
import com.byd.foundation.annotation.ServiceImpl;

/* JADX INFO: loaded from: classes2.dex */
@Platform(versions = {DiLink.VERSION_NAME_3_0, DiLink.VERSION_NAME_3_0_3_0})
@ServiceImpl(service = IDiLinkUiStyleManager.class, singleton = true)
public class IIJjVVVV implements IDiLinkUiStyleManager {
    @Override // com.byd.dilink.app.IDiLinkUiStyleManager
    public void init(Application application) {
        throw new UnsupportedTargetPlatformException("Di4.0 没有 UiStyleManager 类！");
    }

    @Override // com.byd.dilink.app.IDiLinkUiStyleManager
    public void init(Application application, String str) {
        throw new UnsupportedTargetPlatformException("Di4.0 没有 UiStyleManager 类！");
    }
}
