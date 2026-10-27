package So.So.So.SpP.oSOSSS;

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
    private VolumeInfo f153a;

    public b(VolumeInfo volumeInfo) {
        if (volumeInfo == null && DiLink.isNotSelfCheckInnerTest()) {
            throw new IllegalArgumentException("volumeInfo is null");
        }
        this.f153a = volumeInfo;
    }

    public VolumeInfo a() {
        return this.f153a;
    }

    @Override // com.byd.dilink.os.storage.IDiLinkVolumeInfo
    public Intent buildBrowseIntent() {
        return this.f153a.buildBrowseIntent();
    }

    @Override // com.byd.dilink.os.storage.IDiLinkVolumeInfo
    public Intent buildBrowseIntentForUser(int i) {
        return this.f153a.buildBrowseIntentForUser(i);
    }

    @Override // com.byd.dilink.os.storage.IDiLinkVolumeInfo
    public StorageVolume buildStorageVolume(Context context, int i, boolean z) {
        return this.f153a.buildStorageVolume(context, i, z);
    }

    public boolean equals(@Nullable Object obj) {
        if (obj == null || !(obj instanceof b)) {
            return false;
        }
        return this.f153a.equals(((b) obj).a());
    }

    @Override // com.byd.dilink.os.storage.IDiLinkVolumeInfo
    public String getDescription() {
        return this.f153a.getDescription();
    }

    @Override // com.byd.dilink.os.storage.IDiLinkVolumeInfo
    public IDiLinkDiskInfo getDisk() {
        return new a(this.f153a.getDisk());
    }

    @Override // com.byd.dilink.os.storage.IDiLinkVolumeInfo
    public int getDiskFlags() {
        return this.f153a.getDiskFlags();
    }

    @Override // com.byd.dilink.os.storage.IDiLinkVolumeInfo
    public String getDiskId() {
        return this.f153a.getDiskId();
    }

    @Override // com.byd.dilink.os.storage.IDiLinkVolumeInfo
    public String getFsUuid() {
        return this.f153a.getFsUuid();
    }

    @Override // com.byd.dilink.os.storage.IDiLinkVolumeInfo
    public String getId() {
        return this.f153a.getId();
    }

    @Override // com.byd.dilink.os.storage.IDiLinkVolumeInfo
    public File getInternalPath() {
        return this.f153a.getInternalPath();
    }

    @Override // com.byd.dilink.os.storage.IDiLinkVolumeInfo
    public File getInternalPathForUser(int i) {
        return this.f153a.getInternalPathForUser(i);
    }

    @Override // com.byd.dilink.os.storage.IDiLinkVolumeInfo
    public int getMountUserId() {
        return this.f153a.getMountUserId();
    }

    @Override // com.byd.dilink.os.storage.IDiLinkVolumeInfo
    public String getNormalizedFsUuid() {
        return this.f153a.getNormalizedFsUuid();
    }

    @Override // com.byd.dilink.os.storage.IDiLinkVolumeInfo
    public File getPath() {
        return this.f153a.getPath();
    }

    @Override // com.byd.dilink.os.storage.IDiLinkVolumeInfo
    public File getPathForUser(int i) {
        return this.f153a.getPathForUser(i);
    }

    @Override // com.byd.dilink.os.storage.IDiLinkVolumeInfo
    public int getState() {
        return this.f153a.getState();
    }

    @Override // com.byd.dilink.os.storage.IDiLinkVolumeInfo
    public int getStateDescription() {
        return this.f153a.getStateDescription();
    }

    @Override // com.byd.dilink.os.storage.IDiLinkVolumeInfo
    public int getType() {
        return this.f153a.getType();
    }

    public int hashCode() {
        return this.f153a.hashCode();
    }

    @Override // com.byd.dilink.os.storage.IDiLinkVolumeInfo
    public boolean isMountedReadable() {
        return this.f153a.isMountedReadable();
    }

    @Override // com.byd.dilink.os.storage.IDiLinkVolumeInfo
    public boolean isMountedWritable() {
        return this.f153a.isMountedWritable();
    }

    @Override // com.byd.dilink.os.storage.IDiLinkVolumeInfo
    public boolean isPrimary() {
        return this.f153a.isPrimary();
    }

    @Override // com.byd.dilink.os.storage.IDiLinkVolumeInfo
    public boolean isPrimaryEmulatedForUser(int i) {
        return this.f153a.isPrimaryEmulatedForUser(i);
    }

    @Override // com.byd.dilink.os.storage.IDiLinkVolumeInfo
    public boolean isPrimaryPhysical() {
        return this.f153a.isPrimaryPhysical();
    }

    @Override // com.byd.dilink.os.storage.IDiLinkVolumeInfo
    public boolean isVisible() {
        return this.f153a.isVisible();
    }

    @Override // com.byd.dilink.os.storage.IDiLinkVolumeInfo
    public boolean isVisibleForRead(int i) {
        return this.f153a.isVisibleForRead(i);
    }

    @Override // com.byd.dilink.os.storage.IDiLinkVolumeInfo
    public boolean isVisibleForUser(int i) {
        return this.f153a.isVisibleForUser(i);
    }

    @Override // com.byd.dilink.os.storage.IDiLinkVolumeInfo
    public boolean isVisibleForWrite(int i) {
        return this.f153a.isVisibleForWrite(i);
    }

    @NonNull
    public String toString() {
        return super.toString();
    }
}
