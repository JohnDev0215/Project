package android.location;

import android.os.Bundle;

/* JADX INFO: loaded from: classes2.dex */
public interface LocationListener {
    void onLocationChanged(Location location);

    default void onProviderDisabled(String str) {
    }

    default void onProviderEnabled(String str) {
    }

    @Deprecated
    default void onStatusChanged(String str, int i, Bundle bundle) {
    }
}
