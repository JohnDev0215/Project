package BA1aabBX.BA1aabBX.BA1aabBX.bBB;

import android.app.CarModeSkinManager;
import com.byd.dilink.DiLink;
import com.byd.dilink.UnsupportedTargetPlatformException;
import com.byd.dilink.anotation.Platform;
import com.byd.dilink.app.IDiLinkCarModeSkinManager;
import com.byd.foundation.annotation.ServiceImpl;

/* JADX INFO: loaded from: classes.dex */
@Platform(versions = {DiLink.VERSION_NAME_5_0})
@ServiceImpl(service = IDiLinkCarModeSkinManager.class, singleton = true)
public class bBB implements IDiLinkCarModeSkinManager {
    @Override // com.byd.dilink.app.IDiLinkCarModeSkinManager
    public String getSkinPathById(int i) {
        return CarModeSkinManager.getInstance().getSkinPathById(i);
    }

    @Override // com.byd.dilink.app.IDiLinkCarModeSkinManager
    public boolean isFse() {
        throw new UnsupportedTargetPlatformException("Di5.0 没有 isFse 这个函数！");
    }
}
