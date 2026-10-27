package mNTT77n7n.mNTT77n7n.mNTT77n7n.mT.nMMnMM;

import android.os.storage.DiskInfo;
import com.byd.dilink.DiLink;
import com.byd.dilink.os.storage.IDiLinkDiskInfo;

/* JADX INFO: loaded from: classes2.dex */
public class a implements IDiLinkDiskInfo {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private DiskInfo f3161a;

    public a(DiskInfo diskInfo) {
        if (diskInfo == null && DiLink.isNotSelfCheckInnerTest()) {
            throw new IllegalArgumentException("diskInfo is null");
        }
        this.f3161a = diskInfo;
    }

    public DiskInfo a() {
        return this.f3161a;
    }

    @Override // com.byd.dilink.os.storage.IDiLinkDiskInfo
    public String getDescription() {
        return this.f3161a.getDescription();
    }

    @Override // com.byd.dilink.os.storage.IDiLinkDiskInfo
    public int getFlags() {
        return this.f3161a.getFlags();
    }

    @Override // com.byd.dilink.os.storage.IDiLinkDiskInfo
    public String getId() {
        return this.f3161a.getId();
    }

    @Override // com.byd.dilink.os.storage.IDiLinkDiskInfo
    public String getShortDescription() {
        return this.f3161a.getShortDescription();
    }

    @Override // com.byd.dilink.os.storage.IDiLinkDiskInfo
    public boolean isAdoptable() {
        return this.f3161a.isAdoptable();
    }

    @Override // com.byd.dilink.os.storage.IDiLinkDiskInfo
    public boolean isDefaultPrimary() {
        return this.f3161a.isDefaultPrimary();
    }

    @Override // com.byd.dilink.os.storage.IDiLinkDiskInfo
    public boolean isSd() {
        return this.f3161a.isSd();
    }

    @Override // com.byd.dilink.os.storage.IDiLinkDiskInfo
    public boolean isStubVisible() {
        return this.f3161a.isStubVisible();
    }

    @Override // com.byd.dilink.os.storage.IDiLinkDiskInfo
    public boolean isUsb() {
        return this.f3161a.isUsb();
    }
}
