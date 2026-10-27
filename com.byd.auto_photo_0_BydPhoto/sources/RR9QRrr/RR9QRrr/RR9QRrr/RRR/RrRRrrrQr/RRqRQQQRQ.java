package RR9QRrr.RR9QRrr.RR9QRrr.RRR.RrRRrrrQr;

import android.os.storage.DiskInfo;
import android.os.storage.VolumeInfo;
import com.byd.dilink.DiLink;
import com.byd.dilink.UnsupportedTargetPlatformException;
import com.byd.dilink.anotation.Platform;
import com.byd.dilink.os.storage.IDiLinkDiskInfo;
import com.byd.dilink.os.storage.IDiLinkVolumeInfo;
import com.byd.dilink.os.storage.IDiLinkVolumeInfoManager;
import com.byd.foundation.annotation.ServiceImpl;

/* JADX INFO: loaded from: classes.dex */
@Platform(versions = {DiLink.VERSION_NAME_RCS})
@ServiceImpl(service = IDiLinkVolumeInfoManager.class, singleton = true)
public class RRqRQQQRQ implements IDiLinkVolumeInfoManager {
    @Override // com.byd.dilink.os.storage.IDiLinkVolumeInfoManager
    public IDiLinkVolumeInfo create(IDiLinkVolumeInfo iDiLinkVolumeInfo) {
        throw new UnsupportedTargetPlatformException("Di4.0 不支持此方法构建，请替换为create(String id, int type, IDiLinkDiskInfo disk, String partGuid)");
    }

    @Override // com.byd.dilink.os.storage.IDiLinkVolumeInfoManager
    public IDiLinkVolumeInfo create(String str, int i, IDiLinkDiskInfo iDiLinkDiskInfo, String str2) {
        return new b(iDiLinkDiskInfo == null ? new VolumeInfo(str, i, (DiskInfo) null, str2) : new VolumeInfo(str, i, ((a) iDiLinkDiskInfo).a(), str2));
    }
}
