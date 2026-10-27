package sst.sst.sst.SttTst.tQSQQT1sT;

import android.os.storage.DiskInfo;
import com.byd.dilink.DiLink;
import com.byd.dilink.UnsupportedTargetPlatformException;
import com.byd.dilink.os.storage.IDiLinkDiskInfo;

/* JADX INFO: loaded from: classes2.dex */
public class a implements IDiLinkDiskInfo {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private DiskInfo f3225a;

    public a(DiskInfo diskInfo) {
        if (diskInfo == null && DiLink.isNotSelfCheckInnerTest()) {
            throw new IllegalArgumentException("diskInfo is null");
        }
        this.f3225a = diskInfo;
    }

    public DiskInfo a() {
        return this.f3225a;
    }

    @Override // com.byd.dilink.os.storage.IDiLinkDiskInfo
    public String getDescription() {
        return this.f3225a.getDescription();
    }

    @Override // com.byd.dilink.os.storage.IDiLinkDiskInfo
    public int getFlags() {
        throw new UnsupportedTargetPlatformException("ries不存在此接口");
    }

    @Override // com.byd.dilink.os.storage.IDiLinkDiskInfo
    public String getId() {
        return this.f3225a.getId();
    }

    @Override // com.byd.dilink.os.storage.IDiLinkDiskInfo
    public String getShortDescription() {
        return this.f3225a.getShortDescription();
    }

    @Override // com.byd.dilink.os.storage.IDiLinkDiskInfo
    public boolean isAdoptable() {
        return this.f3225a.isAdoptable();
    }

    @Override // com.byd.dilink.os.storage.IDiLinkDiskInfo
    public boolean isDefaultPrimary() {
        return this.f3225a.isDefaultPrimary();
    }

    @Override // com.byd.dilink.os.storage.IDiLinkDiskInfo
    public boolean isSd() {
        return this.f3225a.isSd();
    }

    @Override // com.byd.dilink.os.storage.IDiLinkDiskInfo
    public boolean isStubVisible() {
        return this.f3225a.isStubVisible();
    }

    @Override // com.byd.dilink.os.storage.IDiLinkDiskInfo
    public boolean isUsb() {
        return this.f3225a.isUsb();
    }
}
