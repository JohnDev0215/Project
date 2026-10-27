package GGW.GGW.GGW.W4H;

import android.app.Application;
import com.byd.dilink.UnsupportedTargetPlatformException;
import com.byd.dilink.anotation.Platform;
import com.byd.dilink.app.IDiLinkUiStyleManager;
import com.byd.foundation.annotation.ServiceImpl;

/* JADX INFO: loaded from: classes.dex */
@Platform(versions = {"RUI"})
@ServiceImpl(service = IDiLinkUiStyleManager.class, singleton = true)
public class gh44 implements IDiLinkUiStyleManager {
    @Override // com.byd.dilink.app.IDiLinkUiStyleManager
    public void init(Application application) {
        throw new UnsupportedTargetPlatformException("Di6.0 没有 init 这个函数！");
    }

    @Override // com.byd.dilink.app.IDiLinkUiStyleManager
    public void init(Application application, String str) {
        throw new UnsupportedTargetPlatformException("Di6.0 没有 init 这个函数！");
    }
}
