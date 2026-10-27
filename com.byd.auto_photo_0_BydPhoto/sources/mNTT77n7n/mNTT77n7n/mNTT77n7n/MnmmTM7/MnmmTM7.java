package mNTT77n7n.mNTT77n7n.mNTT77n7n.MnmmTM7;

import android.app.CarModeSkinManager;
import com.byd.dilink.DiLink;
import com.byd.dilink.anotation.Platform;
import com.byd.dilink.app.IDiLinkCarModeSkinManager;
import com.byd.foundation.annotation.ServiceImpl;

/* JADX INFO: loaded from: classes2.dex */
@Platform(versions = {DiLink.VERSION_NAME_DiR_RUI})
@ServiceImpl(service = IDiLinkCarModeSkinManager.class, singleton = true)
public class MnmmTM7 implements IDiLinkCarModeSkinManager {
    @Override // com.byd.dilink.app.IDiLinkCarModeSkinManager
    public String getSkinPathById(int i) {
        return CarModeSkinManager.getInstance().getSkinPathById(i);
    }

    @Override // com.byd.dilink.app.IDiLinkCarModeSkinManager
    public boolean isFse() {
        return CarModeSkinManager.isFse();
    }
}
