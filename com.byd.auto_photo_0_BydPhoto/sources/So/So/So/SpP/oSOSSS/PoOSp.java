package So.So.So.SpP.oSOSSS;

import android.os.storage.DiskInfo;
import com.byd.dilink.DiLink;
import com.byd.dilink.anotation.Platform;
import com.byd.dilink.os.storage.IDiLinkDiskInfo;
import com.byd.dilink.os.storage.IDiLinkDiskInfoManager;
import com.byd.foundation.annotation.ServiceImpl;

/* JADX INFO: loaded from: classes.dex */
@Platform(versions = {DiLink.VERSION_NAME_FSE_RUI, DiLink.VERSION_NAME_FSE_5_0, DiLink.VERSION_NAME_FSE_5_1})
@ServiceImpl(service = IDiLinkDiskInfoManager.class, singleton = true)
public class PoOSp implements IDiLinkDiskInfoManager {
    @Override // com.byd.dilink.os.storage.IDiLinkDiskInfoManager
    public IDiLinkDiskInfo create(String str, int i) {
        return new a(new DiskInfo(str, i));
    }
}
