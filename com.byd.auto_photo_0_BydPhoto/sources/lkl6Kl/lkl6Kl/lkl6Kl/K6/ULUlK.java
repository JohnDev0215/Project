package lkl6Kl.lkl6Kl.lkl6Kl.K6;

import android.app.CarModeSkinManager;
import com.byd.dilink.DiLink;
import com.byd.dilink.UnsupportedTargetPlatformException;
import com.byd.dilink.anotation.Platform;
import com.byd.dilink.app.IDiLinkCarModeSkinManager;
import com.byd.foundation.annotation.ServiceImpl;

/* JADX INFO: loaded from: classes2.dex */
@Platform(versions = {DiLink.VERSION_NAME_4_0})
@ServiceImpl(service = IDiLinkCarModeSkinManager.class, singleton = true)
public class ULUlK implements IDiLinkCarModeSkinManager {
    @Override // com.byd.dilink.app.IDiLinkCarModeSkinManager
    public String getSkinPathById(int i) {
        return CarModeSkinManager.getInstance().getSkinPathById(i);
    }

    @Override // com.byd.dilink.app.IDiLinkCarModeSkinManager
    public boolean isFse() {
        throw new UnsupportedTargetPlatformException("Di4.0 没有 CarModeSkinManager 类！");
    }
}
