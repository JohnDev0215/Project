package jViVV55i.jViVV55i.jViVV55i.jjjJj5.IIJjVVVV;

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

/* JADX INFO: loaded from: classes2.dex */
public class b implements IDiLinkVolumeInfo {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private VolumeInfo f3072a;

    public b(VolumeInfo volumeInfo) {
        if (volumeInfo == null && DiLink.isNotSelfCheckInnerTest()) {
            throw new IllegalArgumentException("volumeInfo is null");
        }
        this.f3072a = volumeInfo;
    }

    public VolumeInfo a() {
        return this.f3072a;
    }

    @Override // com.byd.dilink.os.storage.IDiLinkVolumeInfo
    public Intent buildBrowseIntent() {
        return this.f3072a.buildBrowseIntent();
    }

    @Override // com.byd.dilink.os.storage.IDiLinkVolumeInfo
    public Intent buildBrowseIntentForUser(int i) {
        return this.f3072a.buildBrowseIntentForUser(i);
    }

    @Override // com.byd.dilink.os.storage.IDiLinkVolumeInfo
    public StorageVolume buildStorageVolume(Context context, int i, boolean z) {
        return this.f3072a.buildStorageVolume(context, i, z);
    }

    public boolean equals(@Nullable Object obj) {
        if (obj == null || !(obj instanceof b)) {
            return false;
        }
        return this.f3072a.equals(((b) obj).a());
    }

    @Override // com.byd.dilink.os.storage.IDiLinkVolumeInfo
    public String getDescription() {
        return this.f3072a.getDescription();
    }

    @Override // com.byd.dilink.os.storage.IDiLinkVolumeInfo
    public IDiLinkDiskInfo getDisk() {
        return new a(this.f3072a.getDisk());
    }

    @Override // com.byd.dilink.os.storage.IDiLinkVolumeInfo
    public int getDiskFlags() {
        return this.f3072a.getDiskFlags();
    }

    @Override // com.byd.dilink.os.storage.IDiLinkVolumeInfo
    public String getDiskId() {
        return this.f3072a.getDiskId();
    }

    @Override // com.byd.dilink.os.storage.IDiLinkVolumeInfo
    public String getFsUuid() {
        return this.f3072a.getFsUuid();
    }

    @Override // com.byd.dilink.os.storage.IDiLinkVolumeInfo
    public String getId() {
        return this.f3072a.getId();
    }

    @Override // com.byd.dilink.os.storage.IDiLinkVolumeInfo
    public File getInternalPath() {
        return this.f3072a.getInternalPath();
    }

    @Override // com.byd.dilink.os.storage.IDiLinkVolumeInfo
    public File getInternalPathForUser(int i) {
        return this.f3072a.getInternalPathForUser(i);
    }

    @Override // com.byd.dilink.os.storage.IDiLinkVolumeInfo
    public int getMountUserId() {
        return this.f3072a.getMountUserId();
    }

    @Override // com.byd.dilink.os.storage.IDiLinkVolumeInfo
    public String getNormalizedFsUuid() {
        return this.f3072a.getNormalizedFsUuid();
    }

    @Override // com.byd.dilink.os.storage.IDiLinkVolumeInfo
    public File getPath() {
        return this.f3072a.getPath();
    }

    @Override // com.byd.dilink.os.storage.IDiLinkVolumeInfo
    public File getPathForUser(int i) {
        return this.f3072a.getPathForUser(i);
    }

    @Override // com.byd.dilink.os.storage.IDiLinkVolumeInfo
    public int getState() {
        return this.f3072a.getState();
    }

    @Override // com.byd.dilink.os.storage.IDiLinkVolumeInfo
    public int getStateDescription() {
        return this.f3072a.getStateDescription();
    }

    @Override // com.byd.dilink.os.storage.IDiLinkVolumeInfo
    public int getType() {
        return this.f3072a.getType();
    }

    public int hashCode() {
        return this.f3072a.hashCode();
    }

    @Override // com.byd.dilink.os.storage.IDiLinkVolumeInfo
    public boolean isMountedReadable() {
        return this.f3072a.isMountedReadable();
    }

    @Override // com.byd.dilink.os.storage.IDiLinkVolumeInfo
    public boolean isMountedWritable() {
        return this.f3072a.isMountedWritable();
    }

    @Override // com.byd.dilink.os.storage.IDiLinkVolumeInfo
    public boolean isPrimary() {
        return this.f3072a.isPrimary();
    }

    @Override // com.byd.dilink.os.storage.IDiLinkVolumeInfo
    public boolean isPrimaryEmulatedForUser(int i) {
        throw new UnsupportedTargetPlatformException("Di3.0 不存在此接口！");
    }

    @Override // com.byd.dilink.os.storage.IDiLinkVolumeInfo
    public boolean isPrimaryPhysical() {
        return this.f3072a.isPrimaryPhysical();
    }

    @Override // com.byd.dilink.os.storage.IDiLinkVolumeInfo
    public boolean isVisible() {
        return this.f3072a.isVisible();
    }

    @Override // com.byd.dilink.os.storage.IDiLinkVolumeInfo
    public boolean isVisibleForRead(int i) {
        return this.f3072a.isVisibleForRead(i);
    }

    @Override // com.byd.dilink.os.storage.IDiLinkVolumeInfo
    public boolean isVisibleForUser(int i) {
        return this.f3072a.isVisibleForUser(i);
    }

    @Override // com.byd.dilink.os.storage.IDiLinkVolumeInfo
    public boolean isVisibleForWrite(int i) {
        return this.f3072a.isVisibleForWrite(i);
    }

    @NonNull
    public String toString() {
        return super.toString();
    }
}
