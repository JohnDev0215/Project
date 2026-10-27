package com.android.networkstack.apishim.common;

import android.net.INetworkMonitorCallbacks;
import android.net.Uri;
import android.os.RemoteException;

/* JADX INFO: loaded from: classes.dex */
public interface CaptivePortalDataShim {
    long getByteLimit();

    long getExpiryTimeMillis();

    Uri getUserPortalUrl();

    Uri getVenueInfoUrl();

    boolean isCaptive();

    void notifyChanged(INetworkMonitorCallbacks iNetworkMonitorCallbacks) throws RemoteException;
}
