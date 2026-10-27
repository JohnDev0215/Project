package DDC.DDC.DDC.YdD2c2.d2;

import android.os.storage.DiskInfo;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.byd.dilink.DiLink;
import com.byd.dilink.os.storage.IDiLinkDiskInfo;

/* JADX INFO: loaded from: classes.dex */
public class a implements IDiLinkDiskInfo {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private DiskInfo f30a;

    public a(DiskInfo diskInfo) {
        if (diskInfo == null && DiLink.isNotSelfCheckInnerTest()) {
            throw new IllegalArgumentException("diskInfo is null");
        }
        this.f30a = diskInfo;
    }

    public DiskInfo a() {
        return this.f30a;
    }

    @NonNull
    public Object clone() {
        return new a(this.f30a.clone());
    }

    public boolean equals(@Nullable Object obj) {
        if (obj == null || !(obj instanceof a)) {
            return false;
        }
        return this.f30a.equals(((a) obj).a());
    }

    @Override // com.byd.dilink.os.storage.IDiLinkDiskInfo
    public String getDescription() {
        return this.f30a.getDescription();
    }

    @Override // com.byd.dilink.os.storage.IDiLinkDiskInfo
    public int getFlags() {
        return this.f30a.getFlags();
    }

    @Override // com.byd.dilink.os.storage.IDiLinkDiskInfo
    public String getId() {
        return this.f30a.getId();
    }

    @Override // com.byd.dilink.os.storage.IDiLinkDiskInfo
    public String getShortDescription() {
        return this.f30a.getShortDescription();
    }

    public int hashCode() {
        return this.f30a.hashCode();
    }

    @Override // com.byd.dilink.os.storage.IDiLinkDiskInfo
    public boolean isAdoptable() {
        return this.f30a.isAdoptable();
    }

    @Override // com.byd.dilink.os.storage.IDiLinkDiskInfo
    public boolean isDefaultPrimary() {
        return this.f30a.isDefaultPrimary();
    }

    @Override // com.byd.dilink.os.storage.IDiLinkDiskInfo
    public boolean isSd() {
        return this.f30a.isSd();
    }

    @Override // com.byd.dilink.os.storage.IDiLinkDiskInfo
    public boolean isStubVisible() {
        return this.f30a.isStubVisible();
    }

    @Override // com.byd.dilink.os.storage.IDiLinkDiskInfo
    public boolean isUsb() {
        return this.f30a.isUsb();
    }

    @NonNull
    public String toString() {
        return this.f30a.toString();
    }
}
