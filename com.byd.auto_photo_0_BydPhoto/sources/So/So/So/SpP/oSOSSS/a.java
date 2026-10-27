package So.So.So.SpP.oSOSSS;

import android.os.storage.DiskInfo;
import com.byd.dilink.DiLink;
import com.byd.dilink.UnsupportedTargetPlatformException;
import com.byd.dilink.os.storage.IDiLinkDiskInfo;

/* JADX INFO: loaded from: classes.dex */
public class a implements IDiLinkDiskInfo {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private DiskInfo f152a;

    public a(DiskInfo diskInfo) {
        if (diskInfo == null && DiLink.isNotSelfCheckInnerTest()) {
            throw new IllegalArgumentException("diskInfo is null");
        }
        this.f152a = diskInfo;
    }

    public DiskInfo a() {
        return this.f152a;
    }

    @Override // com.byd.dilink.os.storage.IDiLinkDiskInfo
    public String getDescription() {
        return this.f152a.getDescription();
    }

    @Override // com.byd.dilink.os.storage.IDiLinkDiskInfo
    public int getFlags() {
        throw new UnsupportedTargetPlatformException("FSE 没有 getFlags 这个函数！");
    }

    @Override // com.byd.dilink.os.storage.IDiLinkDiskInfo
    public String getId() {
        return this.f152a.getId();
    }

    @Override // com.byd.dilink.os.storage.IDiLinkDiskInfo
    public String getShortDescription() {
        return this.f152a.getShortDescription();
    }

    @Override // com.byd.dilink.os.storage.IDiLinkDiskInfo
    public boolean isAdoptable() {
        return this.f152a.isAdoptable();
    }

    @Override // com.byd.dilink.os.storage.IDiLinkDiskInfo
    public boolean isDefaultPrimary() {
        return this.f152a.isDefaultPrimary();
    }

    @Override // com.byd.dilink.os.storage.IDiLinkDiskInfo
    public boolean isSd() {
        return this.f152a.isSd();
    }

    @Override // com.byd.dilink.os.storage.IDiLinkDiskInfo
    public boolean isStubVisible() {
        return this.f152a.isStubVisible();
    }

    @Override // com.byd.dilink.os.storage.IDiLinkDiskInfo
    public boolean isUsb() {
        return this.f152a.isUsb();
    }
}
