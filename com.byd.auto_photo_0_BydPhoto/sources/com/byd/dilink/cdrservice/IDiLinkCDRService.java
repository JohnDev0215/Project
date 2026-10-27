package com.byd.dilink.cdrservice;

import androidx.annotation.NonNull;

/* JADX INFO: loaded from: classes.dex */
public interface IDiLinkCDRService {
    boolean registerUser(@NonNull IDiLinkCDRUser iDiLinkCDRUser);

    int setCommand(int i, int i2);

    boolean unregisterUser(@NonNull IDiLinkCDRUser iDiLinkCDRUser);
}
