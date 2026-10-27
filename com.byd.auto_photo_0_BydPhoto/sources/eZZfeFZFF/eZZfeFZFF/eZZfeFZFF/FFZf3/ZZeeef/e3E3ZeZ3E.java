package eZZfeFZFF.eZZfeFZFF.eZZfeFZFF.FFZf3.ZZeeef;

import android.os.storage.DiskInfo;
import com.byd.dilink.DiLink;
import com.byd.dilink.anotation.Platform;
import com.byd.dilink.os.storage.IDiLinkDiskInfo;
import com.byd.dilink.os.storage.IDiLinkDiskInfoManager;
import com.byd.foundation.annotation.ServiceImpl;

/* JADX INFO: loaded from: classes2.dex */
@Platform(isDecoupled = true, versions = {DiLink.VERSION_NAME_5_1})
@ServiceImpl(service = IDiLinkDiskInfoManager.class, singleton = true)
public class e3E3ZeZ3E implements IDiLinkDiskInfoManager {
    @Override // com.byd.dilink.os.storage.IDiLinkDiskInfoManager
    public IDiLinkDiskInfo create(String str, int i) {
        return new b(new DiskInfo(str, i));
    }
}
