package com.byd.dilink.os.storage;

import androidx.annotation.NonNull;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public interface IDiLinkStorageManager {
    boolean format(String str);

    @NonNull
    List<IDiLinkVolumeInfo> getVolumes();

    boolean partitionPublic(String str);
}
