package PVUVUPv.PVUVUPv.PVUVUPv.vv22vv.VPV;

import android.content.Context;
import android.content.Intent;
import android.os.storage.StorageVolume;
import android.os.storage.VolumeInfo;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import com.byd.dilink.DiLink;
import com.byd.dilink.UnsupportedTargetPlatformException;
import com.byd.dilink.os.storage.IDiLinkDiskInfo;
import com.byd.dilink.os.storage.IDiLinkVolumeInfo;
import java.io.File;

/* JADX INFO: loaded from: classes.dex */
public class b implements IDiLinkVolumeInfo {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private VolumeInfo f100a;

    public b(VolumeInfo volumeInfo) {
        if (volumeInfo == null && DiLink.isNotSelfCheckInnerTest()) {
            throw new IllegalArgumentException("volumeInfo is null");
        }
        this.f100a = volumeInfo;
    }

    public VolumeInfo a() {
        return this.f100a;
    }

    @Override // com.byd.dilink.os.storage.IDiLinkVolumeInfo
    public Intent buildBrowseIntent() {
        return this.f100a.buildBrowseIntent();
    }

    @Override // com.byd.dilink.os.storage.IDiLinkVolumeInfo
    public Intent buildBrowseIntentForUser(int i) {
        return this.f100a.buildBrowseIntentForUser(i);
    }

    @Override // com.byd.dilink.os.storage.IDiLinkVolumeInfo
    public StorageVolume buildStorageVolume(Context context, int i, boolean z) {
        return this.f100a.buildStorageVolume(context, i, z);
    }

    public boolean equals(@Nullable Object obj) {
        if (obj == null || !(obj instanceof b)) {
            return false;
        }
        return this.f100a.equals(((b) obj).a());
    }

    @Override // com.byd.dilink.os.storage.IDiLinkVolumeInfo
    public String getDescription() {
        return this.f100a.getDescription();
    }

    @Override // com.byd.dilink.os.storage.IDiLinkVolumeInfo
    public IDiLinkDiskInfo getDisk() {
        return new a(this.f100a.getDisk());
    }

    @Override // com.byd.dilink.os.storage.IDiLinkVolumeInfo
    public int getDiskFlags() {
        return this.f100a.getDiskFlags();
    }

    @Override // com.byd.dilink.os.storage.IDiLinkVolumeInfo
    public String getDiskId() {
        return this.f100a.getDiskId();
    }

    @Override // com.byd.dilink.os.storage.IDiLinkVolumeInfo
    public String getFsUuid() {
        return this.f100a.getFsUuid();
    }

    @Override // com.byd.dilink.os.storage.IDiLinkVolumeInfo
    public String getId() {
        return this.f100a.getId();
    }

    @Override // com.byd.dilink.os.storage.IDiLinkVolumeInfo
    public File getInternalPath() {
        return this.f100a.getInternalPath();
    }

    @Override // com.byd.dilink.os.storage.IDiLinkVolumeInfo
    public File getInternalPathForUser(int i) {
        return this.f100a.getInternalPathForUser(i);
    }

    @Override // com.byd.dilink.os.storage.IDiLinkVolumeInfo
    public int getMountUserId() {
        return this.f100a.getMountUserId();
    }

    @Override // com.byd.dilink.os.storage.IDiLinkVolumeInfo
    public String getNormalizedFsUuid() {
        return this.f100a.getNormalizedFsUuid();
    }

    @Override // com.byd.dilink.os.storage.IDiLinkVolumeInfo
    public File getPath() {
        return this.f100a.getPath();
    }

    @Override // com.byd.dilink.os.storage.IDiLinkVolumeInfo
    public File getPathForUser(int i) {
        return this.f100a.getPathForUser(i);
    }

    @Override // com.byd.dilink.os.storage.IDiLinkVolumeInfo
    public int getState() {
        return this.f100a.getState();
    }

    @Override // com.byd.dilink.os.storage.IDiLinkVolumeInfo
    public int getStateDescription() {
        return this.f100a.getStateDescription();
    }

    @Override // com.byd.dilink.os.storage.IDiLinkVolumeInfo
    public int getType() {
        return this.f100a.getType();
    }

    public int hashCode() {
        return this.f100a.hashCode();
    }

    @Override // com.byd.dilink.os.storage.IDiLinkVolumeInfo
    public boolean isMountedReadable() {
        return this.f100a.isMountedReadable();
    }

    @Override // com.byd.dilink.os.storage.IDiLinkVolumeInfo
    public boolean isMountedWritable() {
        return this.f100a.isMountedWritable();
    }

    @Override // com.byd.dilink.os.storage.IDiLinkVolumeInfo
    public boolean isPrimary() {
        return this.f100a.isPrimary();
    }

    @Override // com.byd.dilink.os.storage.IDiLinkVolumeInfo
    public boolean isPrimaryEmulatedForUser(int i) {
        throw new UnsupportedTargetPlatformException("RSE 不存在此接口！");
    }

    @Override // com.byd.dilink.os.storage.IDiLinkVolumeInfo
    public boolean isPrimaryPhysical() {
        return this.f100a.isPrimaryPhysical();
    }

    @Override // com.byd.dilink.os.storage.IDiLinkVolumeInfo
    public boolean isVisible() {
        return this.f100a.isVisible();
    }

    @Override // com.byd.dilink.os.storage.IDiLinkVolumeInfo
    public boolean isVisibleForRead(int i) {
        return this.f100a.isVisibleForRead(i);
    }

    @Override // com.byd.dilink.os.storage.IDiLinkVolumeInfo
    public boolean isVisibleForUser(int i) {
        return this.f100a.isVisibleForUser(i);
    }

    @Override // com.byd.dilink.os.storage.IDiLinkVolumeInfo
    public boolean isVisibleForWrite(int i) {
        return this.f100a.isVisibleForWrite(i);
    }

    @NonNull
    public String toString() {
        return super.toString();
    }
}
