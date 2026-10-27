package com.android.server.telecom;

import android.os.RemoteException;
import com.android.internal.telecom.IInternalServiceRetriever;

/* JADX INFO: loaded from: classes.dex */
public class InternalServiceRetrieverAdapter {
    private final IInternalServiceRetriever mRetriever;

    public InternalServiceRetrieverAdapter(IInternalServiceRetriever iInternalServiceRetriever) {
        this.mRetriever = iInternalServiceRetriever;
    }

    public DeviceIdleControllerAdapter getDeviceIdleController() {
        try {
            return new DeviceIdleControllerAdapter(this.mRetriever.getDeviceIdleController());
        } catch (RemoteException e) {
            throw new IllegalStateException(e);
        }
    }
}
