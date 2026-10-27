package So.So.So.SpP.oSOSSS;

import android.os.storage.DiskInfo;
import android.os.storage.VolumeInfo;
import android.util.Log;
import com.byd.dilink.DiLink;
import com.byd.dilink.anotation.Platform;
import com.byd.dilink.os.storage.IDiLinkDiskInfo;
import com.byd.dilink.os.storage.IDiLinkVolumeInfo;
import com.byd.dilink.os.storage.IDiLinkVolumeInfoManager;
import com.byd.foundation.annotation.ServiceImpl;

/* JADX INFO: loaded from: classes.dex */
@Platform(versions = {DiLink.VERSION_NAME_FSE_RUI, DiLink.VERSION_NAME_FSE_5_0, DiLink.VERSION_NAME_FSE_5_1})
@ServiceImpl(service = IDiLinkVolumeInfoManager.class, singleton = true)
public class S88 implements IDiLinkVolumeInfoManager {
    @Override // com.byd.dilink.os.storage.IDiLinkVolumeInfoManager
    public IDiLinkVolumeInfo create(IDiLinkVolumeInfo iDiLinkVolumeInfo) {
        b bVar = (b) iDiLinkVolumeInfo;
        if (bVar != null) {
            return new b(new VolumeInfo(bVar.a()));
        }
        Log.w("DiLinkVolumeInfoManager", "IDiLinkVolumeInfo is null");
        return null;
    }

    @Override // com.byd.dilink.os.storage.IDiLinkVolumeInfoManager
    public IDiLinkVolumeInfo create(String str, int i, IDiLinkDiskInfo iDiLinkDiskInfo, String str2) {
        return new b(iDiLinkDiskInfo == null ? new VolumeInfo(str, i, (DiskInfo) null, str2) : new VolumeInfo(str, i, ((a) iDiLinkDiskInfo).a(), str2));
    }
}
