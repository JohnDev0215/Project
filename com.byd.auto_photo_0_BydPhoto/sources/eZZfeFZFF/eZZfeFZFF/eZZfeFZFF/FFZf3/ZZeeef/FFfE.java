package eZZfeFZFF.eZZfeFZFF.eZZfeFZFF.FFZf3.ZZeeef;

import android.os.storage.DiskInfo;
import android.os.storage.VolumeInfo;
import android.util.Log;
import com.byd.dilink.DiLink;
import com.byd.dilink.anotation.Platform;
import com.byd.dilink.os.storage.IDiLinkDiskInfo;
import com.byd.dilink.os.storage.IDiLinkVolumeInfo;
import com.byd.dilink.os.storage.IDiLinkVolumeInfoManager;
import com.byd.foundation.annotation.ServiceImpl;

/* JADX INFO: loaded from: classes2.dex */
@Platform(isDecoupled = true, versions = {DiLink.VERSION_NAME_5_1})
@ServiceImpl(service = IDiLinkVolumeInfoManager.class, singleton = true)
public class FFfE implements IDiLinkVolumeInfoManager {
    @Override // com.byd.dilink.os.storage.IDiLinkVolumeInfoManager
    public IDiLinkVolumeInfo create(IDiLinkVolumeInfo iDiLinkVolumeInfo) {
        a aVar = (a) iDiLinkVolumeInfo;
        if (aVar != null) {
            return new a(new VolumeInfo(aVar.a()));
        }
        Log.w("DiLinkVolumeInfoManager", "IDiLinkVolumeInfo is null");
        return null;
    }

    @Override // com.byd.dilink.os.storage.IDiLinkVolumeInfoManager
    public IDiLinkVolumeInfo create(String str, int i, IDiLinkDiskInfo iDiLinkDiskInfo, String str2) {
        return new a(iDiLinkDiskInfo == null ? new VolumeInfo(str, i, (DiskInfo) null, str2) : new VolumeInfo(str, i, ((b) iDiLinkDiskInfo).a(), str2));
    }
}
