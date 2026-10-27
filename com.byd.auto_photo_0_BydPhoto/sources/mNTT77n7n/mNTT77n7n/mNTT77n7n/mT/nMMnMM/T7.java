package mNTT77n7n.mNTT77n7n.mNTT77n7n.mT.nMMnMM;

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
@Platform(versions = {DiLink.VERSION_NAME_DiR_RUI})
@ServiceImpl(service = IDiLinkVolumeInfoManager.class, singleton = true)
public class T7 implements IDiLinkVolumeInfoManager {
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
