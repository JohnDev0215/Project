package jViVV55i.jViVV55i.jViVV55i.jjjJj5.IIJjVVVV;

import android.os.storage.DiskInfo;
import com.byd.dilink.DiLink;
import com.byd.dilink.anotation.Platform;
import com.byd.dilink.os.storage.IDiLinkDiskInfo;
import com.byd.dilink.os.storage.IDiLinkDiskInfoManager;
import com.byd.foundation.annotation.ServiceImpl;

/* JADX INFO: loaded from: classes2.dex */
@Platform(versions = {DiLink.VERSION_NAME_3_0, DiLink.VERSION_NAME_3_0_3_0})
@ServiceImpl(service = IDiLinkDiskInfoManager.class, singleton = true)
public class jVJIV5i implements IDiLinkDiskInfoManager {
    @Override // com.byd.dilink.os.storage.IDiLinkDiskInfoManager
    public IDiLinkDiskInfo create(String str, int i) {
        return new a(new DiskInfo(str, i));
    }
}
