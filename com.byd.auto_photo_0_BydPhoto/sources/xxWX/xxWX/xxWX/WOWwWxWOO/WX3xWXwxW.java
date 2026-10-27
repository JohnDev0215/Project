package xxWX.xxWX.xxWX.WOWwWxWOO;

import com.byd.dilink.DiLink;
import com.byd.dilink.UnsupportedTargetPlatformException;
import com.byd.dilink.anotation.Platform;
import com.byd.dilink.app.IDiLinkCarModeSkinManager;
import com.byd.foundation.annotation.ServiceImpl;

/* JADX INFO: loaded from: classes2.dex */
@Platform(versions = {DiLink.VERSION_NAME_3_0_3_0})
@ServiceImpl(service = IDiLinkCarModeSkinManager.class, singleton = true)
public class WX3xWXwxW implements IDiLinkCarModeSkinManager {
    @Override // com.byd.dilink.app.IDiLinkCarModeSkinManager
    public String getSkinPathById(int i) {
        throw new UnsupportedTargetPlatformException("Di3.0 没有CarModeSkinManager类!");
    }

    @Override // com.byd.dilink.app.IDiLinkCarModeSkinManager
    public boolean isFse() {
        throw new UnsupportedTargetPlatformException("Di3.0 没有CarModeSkinManager类!");
    }
}
