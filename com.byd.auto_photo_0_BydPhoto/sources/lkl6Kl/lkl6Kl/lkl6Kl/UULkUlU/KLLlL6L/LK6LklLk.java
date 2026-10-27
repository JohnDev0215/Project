package lkl6Kl.lkl6Kl.lkl6Kl.UULkUlU.KLLlL6L;

import android.os.storage.DiskInfo;
import com.byd.dilink.DiLink;
import com.byd.dilink.anotation.Platform;
import com.byd.dilink.os.storage.IDiLinkDiskInfo;
import com.byd.dilink.os.storage.IDiLinkDiskInfoManager;
import com.byd.foundation.annotation.ServiceImpl;

/* JADX INFO: loaded from: classes2.dex */
@Platform(versions = {DiLink.VERSION_NAME_4_0})
@ServiceImpl(service = IDiLinkDiskInfoManager.class, singleton = true)
public class LK6LklLk implements IDiLinkDiskInfoManager {
    @Override // com.byd.dilink.os.storage.IDiLinkDiskInfoManager
    public IDiLinkDiskInfo create(String str, int i) {
        return new b(new DiskInfo(str, i));
    }
}
