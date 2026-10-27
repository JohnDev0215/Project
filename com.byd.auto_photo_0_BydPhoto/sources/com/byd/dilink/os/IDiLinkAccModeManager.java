package com.byd.dilink.os;

import android.os.IBinder;
import android.os.WorkSource;
import androidx.annotation.NonNull;

/* JADX INFO: loaded from: classes.dex */
public interface IDiLinkAccModeManager {
    void acquireAccLock(IBinder iBinder, int i, String str, String str2, WorkSource workSource, String str3);

    void addListener(IDiLinkAccModeListener iDiLinkAccModeListener);

    int getAccModeStatus();

    @NonNull
    IDiLinkAccLock newAccLock(int i, @NonNull String str);

    void releaseAccLock(IBinder iBinder, int i);

    void removeListener(IDiLinkAccModeListener iDiLinkAccModeListener);

    void requestSuspending(int i);

    void rmPkg2AccWhiteList(String str);

    void setPkg2AccWhiteList(String str);

    void validateAccLockParameters(int i, String str);
}
