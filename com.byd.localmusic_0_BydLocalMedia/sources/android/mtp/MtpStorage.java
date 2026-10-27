package android.mtp;

import android.os.storage.StorageVolume;

/* JADX INFO: loaded from: classes2.dex */
public class MtpStorage {
    private final String mDescription;
    private final long mMaxFileSize;
    private final String mPath;
    private final boolean mRemovable;
    private final int mStorageId;
    private final String mVolumeName;

    public MtpStorage(StorageVolume storageVolume, int i) {
        this.mStorageId = i;
        this.mPath = storageVolume.getPath();
        this.mDescription = storageVolume.getDescription(null);
        this.mRemovable = storageVolume.isRemovable();
        this.mMaxFileSize = storageVolume.getMaxFileSize();
        this.mVolumeName = storageVolume.getMediaStoreVolumeName();
    }

    public final int getStorageId() {
        return this.mStorageId;
    }

    public final String getPath() {
        return this.mPath;
    }

    public final String getDescription() {
        return this.mDescription;
    }

    public final boolean isRemovable() {
        return this.mRemovable;
    }

    public long getMaxFileSize() {
        return this.mMaxFileSize;
    }

    public String getVolumeName() {
        return this.mVolumeName;
    }
}
