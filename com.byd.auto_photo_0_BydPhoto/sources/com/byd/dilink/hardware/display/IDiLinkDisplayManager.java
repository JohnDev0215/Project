package com.byd.dilink.hardware.display;

import android.os.Handler;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

/* JADX INFO: loaded from: classes.dex */
public interface IDiLinkDisplayManager {
    @NonNull
    @Deprecated
    IDiLinkBacklightCtrl createBackLightCtrl(@NonNull String str);

    @NonNull
    IDiLinkBacklightCtrl createBackLightCtrl(@NonNull String str, @NonNull int i);

    @Deprecated
    int getPowerScreenStatus();

    int getPowerScreenStatus(int i);

    void registerOnekeyStatusListener(@NonNull IDiLinkOnekeyStatusListener iDiLinkOnekeyStatusListener, @Nullable Handler handler);

    void setBacklightMode(int i, int i2);

    void turnBacklightOff(int i, long j);

    @Deprecated
    void turnBacklightOff(long j);

    void turnBacklightOn(int i, long j);

    @Deprecated
    void turnBacklightOn(long j);

    void unregisterOnekeyStatusListener(@NonNull IDiLinkOnekeyStatusListener iDiLinkOnekeyStatusListener);
}
