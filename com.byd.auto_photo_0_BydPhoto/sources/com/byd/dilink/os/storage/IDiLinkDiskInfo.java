package com.byd.dilink.os.storage;

/* JADX INFO: loaded from: classes.dex */
public interface IDiLinkDiskInfo {
    String getDescription();

    int getFlags();

    String getId();

    String getShortDescription();

    boolean isAdoptable();

    boolean isDefaultPrimary();

    boolean isSd();

    boolean isStubVisible();

    boolean isUsb();
}
