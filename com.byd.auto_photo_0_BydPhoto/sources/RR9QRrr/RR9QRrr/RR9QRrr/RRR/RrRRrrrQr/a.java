package RR9QRrr.RR9QRrr.RR9QRrr.RRR.RrRRrrrQr;

import android.os.storage.DiskInfo;
import com.byd.dilink.DiLink;
import com.byd.dilink.UnsupportedTargetPlatformException;
import com.byd.dilink.os.storage.IDiLinkDiskInfo;

/* JADX INFO: loaded from: classes.dex */
public class a implements IDiLinkDiskInfo {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private DiskInfo f108a;

    public a(DiskInfo diskInfo) {
        if (diskInfo == null && DiLink.isNotSelfCheckInnerTest()) {
            throw new IllegalArgumentException("diskInfo is null");
        }
        this.f108a = diskInfo;
    }

    public DiskInfo a() {
        return this.f108a;
    }

    @Override // com.byd.dilink.os.storage.IDiLinkDiskInfo
    public String getDescription() {
        return this.f108a.getDescription();
    }

    @Override // com.byd.dilink.os.storage.IDiLinkDiskInfo
    public int getFlags() {
        throw new UnsupportedTargetPlatformException("rcs不存在此接口");
    }

    @Override // com.byd.dilink.os.storage.IDiLinkDiskInfo
    public String getId() {
        return this.f108a.getId();
    }

    @Override // com.byd.dilink.os.storage.IDiLinkDiskInfo
    public String getShortDescription() {
        return this.f108a.getShortDescription();
    }

    @Override // com.byd.dilink.os.storage.IDiLinkDiskInfo
    public boolean isAdoptable() {
        return this.f108a.isAdoptable();
    }

    @Override // com.byd.dilink.os.storage.IDiLinkDiskInfo
    public boolean isDefaultPrimary() {
        return this.f108a.isDefaultPrimary();
    }

    @Override // com.byd.dilink.os.storage.IDiLinkDiskInfo
    public boolean isSd() {
        return this.f108a.isSd();
    }

    @Override // com.byd.dilink.os.storage.IDiLinkDiskInfo
    public boolean isStubVisible() {
        throw new UnsupportedTargetPlatformException("rcs 没有 isStubVisible 方法！");
    }

    @Override // com.byd.dilink.os.storage.IDiLinkDiskInfo
    public boolean isUsb() {
        return this.f108a.isUsb();
    }
}
