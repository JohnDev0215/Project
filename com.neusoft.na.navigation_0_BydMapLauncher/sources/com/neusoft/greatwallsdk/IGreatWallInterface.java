package com.neusoft.greatwallsdk;

import android.os.RemoteException;
import android.view.Surface;

/* JADX INFO: loaded from: classes2.dex */
public interface IGreatWallInterface {
    void async_request(String str, RequestCallback requestCallback);

    void cancelListener(IGreatWallListener iGreatWallListener);

    void setListener(IGreatWallListener iGreatWallListener);

    void setSurface(Surface surface, SetSurfaceResultCallback setSurfaceResultCallback) throws RemoteException;

    String sync_request(String str);
}
