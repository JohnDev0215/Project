package com.byd.dilink.os.storage;

import android.content.Context;
import android.content.Intent;
import android.os.storage.StorageVolume;
import java.io.File;

/* JADX INFO: loaded from: classes.dex */
public interface IDiLinkVolumeInfo {
    Intent buildBrowseIntent();

    Intent buildBrowseIntentForUser(int i);

    StorageVolume buildStorageVolume(Context context, int i, boolean z);

    String getDescription();

    IDiLinkDiskInfo getDisk();

    int getDiskFlags();

    String getDiskId();

    String getFsUuid();

    String getId();

    File getInternalPath();

    File getInternalPathForUser(int i);

    int getMountUserId();

    String getNormalizedFsUuid();

    File getPath();

    File getPathForUser(int i);

    int getState();

    int getStateDescription();

    int getType();

    boolean isMountedReadable();

    boolean isMountedWritable();

    boolean isPrimary();

    boolean isPrimaryEmulatedForUser(int i);

    boolean isPrimaryPhysical();

    boolean isVisible();

    boolean isVisibleForRead(int i);

    boolean isVisibleForUser(int i);

    boolean isVisibleForWrite(int i);
}
