package com.byd.dilink.location;

import android.os.UserHandle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

/* JADX INFO: loaded from: classes.dex */
public interface IDiLinkLocationManager {
    boolean addLocationEnabledListener(@NonNull IDiLinkLocationEnabledListener iDiLinkLocationEnabledListener);

    boolean addLocationEnabledListener(@NonNull IDiLinkLocationEnabledListener iDiLinkLocationEnabledListener, String str, @Nullable String str2, String str3);

    long getLocationEnabledDeadTime();

    long getLocationEnabledDeadTimeForUser(int i);

    int getLocationEnabledTypeForUser();

    int getLocationEnabledTypeForUser(int i);

    boolean isAutomotiveGnssSuspended();

    boolean isLocationEnabledForUser(UserHandle userHandle);

    void removeLocationEnabledListener(@NonNull IDiLinkLocationEnabledListener iDiLinkLocationEnabledListener);

    void setAutomotiveGnssSuspended(boolean z);

    void setLocationEnabledTypeForUser(int i);

    void setLocationEnabledTypeForUser(int i, int i2);
}
