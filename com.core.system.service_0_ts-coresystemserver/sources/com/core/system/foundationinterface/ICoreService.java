package com.core.system.foundationinterface;

import android.content.Context;
import android.os.IBinder;

/* JADX INFO: loaded from: classes.dex */
public interface ICoreService {
    IBinder getServiceBinder();

    void init(Context context, ICoreAdminService iCoreAdminService);

    void release(Context context, ICoreAdminService iCoreAdminService);

    String serviceName();
}
