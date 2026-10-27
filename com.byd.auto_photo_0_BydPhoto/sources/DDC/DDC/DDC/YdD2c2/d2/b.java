package DDC.DDC.DDC.YdD2c2.d2;

import android.content.Context;
import android.content.Intent;
import android.os.storage.StorageVolume;
import android.os.storage.VolumeInfo;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.byd.dilink.DiLink;
import com.byd.dilink.os.storage.IDiLinkDiskInfo;
import com.byd.dilink.os.storage.IDiLinkVolumeInfo;
import java.io.File;

/* JADX INFO: loaded from: classes.dex */
public class b implements IDiLinkVolumeInfo {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private VolumeInfo f31a;

    public b(VolumeInfo volumeInfo) {
        if (volumeInfo == null && DiLink.isNotSelfCheckInnerTest()) {
            throw new IllegalArgumentException("volumeInfo is null");
        }
        this.f31a = volumeInfo;
    }

    public VolumeInfo a() {
        return this.f31a;
    }

    @Override // com.byd.dilink.os.storage.IDiLinkVolumeInfo
    public Intent buildBrowseIntent() {
        return this.f31a.buildBrowseIntent();
    }

    @Override // com.byd.dilink.os.storage.IDiLinkVolumeInfo
    public Intent buildBrowseIntentForUser(int i) {
        return this.f31a.buildBrowseIntentForUser(i);
    }

    @Override // com.byd.dilink.os.storage.IDiLinkVolumeInfo
    public StorageVolume buildStorageVolume(Context context, int i, boolean z) {
        return this.f31a.buildStorageVolume(context, i, z);
    }

    public boolean equals(@Nullable Object obj) {
        if (obj == null || !(obj instanceof b)) {
            return false;
        }
        return this.f31a.equals(((b) obj).a());
    }

    @Override // com.byd.dilink.os.storage.IDiLinkVolumeInfo
    public String getDescription() {
        return this.f31a.getDescription();
    }

    @Override // com.byd.dilink.os.storage.IDiLinkVolumeInfo
    public IDiLinkDiskInfo getDisk() {
        return new a(this.f31a.getDisk());
    }

    @Override // com.byd.dilink.os.storage.IDiLinkVolumeInfo
    public int getDiskFlags() {
        return this.f31a.getDiskFlags();
    }

    @Override // com.byd.dilink.os.storage.IDiLinkVolumeInfo
    public String getDiskId() {
        return this.f31a.getDiskId();
    }

    @Override // com.byd.dilink.os.storage.IDiLinkVolumeInfo
    public String getFsUuid() {
        return this.f31a.getFsUuid();
    }

    @Override // com.byd.dilink.os.storage.IDiLinkVolumeInfo
    public String getId() {
        return this.f31a.getId();
    }

    @Override // com.byd.dilink.os.storage.IDiLinkVolumeInfo
    public File getInternalPath() {
        return this.f31a.getInternalPath();
    }

    @Override // com.byd.dilink.os.storage.IDiLinkVolumeInfo
    public File getInternalPathForUser(int i) {
        return this.f31a.getInternalPathForUser(i);
    }

    @Override // com.byd.dilink.os.storage.IDiLinkVolumeInfo
    public int getMountUserId() {
        return this.f31a.getMountUserId();
    }

    @Override // com.byd.dilink.os.storage.IDiLinkVolumeInfo
    public String getNormalizedFsUuid() {
        return this.f31a.getNormalizedFsUuid();
    }

    @Override // com.byd.dilink.os.storage.IDiLinkVolumeInfo
    public File getPath() {
        return this.f31a.getPath();
    }

    @Override // com.byd.dilink.os.storage.IDiLinkVolumeInfo
    public File getPathForUser(int i) {
        return this.f31a.getPathForUser(i);
    }

    @Override // com.byd.dilink.os.storage.IDiLinkVolumeInfo
    public int getState() {
        return this.f31a.getState();
    }

    @Override // com.byd.dilink.os.storage.IDiLinkVolumeInfo
    public int getStateDescription() {
        return this.f31a.getStateDescription();
    }

    @Override // com.byd.dilink.os.storage.IDiLinkVolumeInfo
    public int getType() {
        return this.f31a.getType();
    }

    public int hashCode() {
        return this.f31a.hashCode();
    }

    @Override // com.byd.dilink.os.storage.IDiLinkVolumeInfo
    public boolean isMountedReadable() {
        return this.f31a.isMountedReadable();
    }

    @Override // com.byd.dilink.os.storage.IDiLinkVolumeInfo
    public boolean isMountedWritable() {
        return this.f31a.isMountedWritable();
    }

    @Override // com.byd.dilink.os.storage.IDiLinkVolumeInfo
    public boolean isPrimary() {
        return this.f31a.isPrimary();
    }

    @Override // com.byd.dilink.os.storage.IDiLinkVolumeInfo
    public boolean isPrimaryEmulatedForUser(int i) {
        return this.f31a.isPrimaryEmulatedForUser(i);
    }

    @Override // com.byd.dilink.os.storage.IDiLinkVolumeInfo
    public boolean isPrimaryPhysical() {
        return this.f31a.isPrimaryPhysical();
    }

    @Override // com.byd.dilink.os.storage.IDiLinkVolumeInfo
    public boolean isVisible() {
        return this.f31a.isVisible();
    }

    @Override // com.byd.dilink.os.storage.IDiLinkVolumeInfo
    public boolean isVisibleForRead(int i) {
        return this.f31a.isVisibleForRead(i);
    }

    @Override // com.byd.dilink.os.storage.IDiLinkVolumeInfo
    public boolean isVisibleForUser(int i) {
        return this.f31a.isVisibleForUser(i);
    }

    @Override // com.byd.dilink.os.storage.IDiLinkVolumeInfo
    public boolean isVisibleForWrite(int i) {
        return this.f31a.isVisibleForWrite(i);
    }

    @NonNull
    public String toString() {
        return super.toString();
    }
}
