package com.byd.dilink.os.storage;

/* JADX INFO: loaded from: classes.dex */
public interface IDiLinkVolumeInfoManager {
    IDiLinkVolumeInfo create(IDiLinkVolumeInfo iDiLinkVolumeInfo);

    IDiLinkVolumeInfo create(String str, int i, IDiLinkDiskInfo iDiLinkDiskInfo, String str2);
}
