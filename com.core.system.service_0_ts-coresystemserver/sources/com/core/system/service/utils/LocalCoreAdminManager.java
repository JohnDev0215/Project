package com.core.system.service.utils;

import android.content.Context;
import com.core.system.foundationinterface.ICoreAdminService;
import com.core.system.foundationinterface.ICoreService;
import com.core.system.service.CoreSystemServerImpl;
import com.ts.car.sdk.core.Logger;

/* JADX INFO: loaded from: classes.dex */
public class LocalCoreAdminManager implements ICoreAdminService {
    private Context mContext;
    private CoreSystemServerImpl mCoreSystemServerImpl;

    public LocalCoreAdminManager(Context context, CoreSystemServerImpl coreSystemServerImpl) {
        this.mContext = context;
        this.mCoreSystemServerImpl = coreSystemServerImpl;
    }

    @Override // com.core.system.foundationinterface.ICoreAdminService
    public boolean addService(ICoreService coreService) {
        if (coreService == null) {
            Logger.warning("add Service error, coreService is null!", new Exception());
            return false;
        }
        return this.mCoreSystemServerImpl.addServiceBinder(coreService.serviceName(), coreService.getServiceBinder());
    }

    @Override // com.core.system.foundationinterface.ICoreAdminService
    public boolean notifyServiceReady(ICoreService coreService) {
        if (coreService == null) {
            Logger.warning("notifyServiceReady error, coreService is null!", new Exception());
            return false;
        }
        return this.mCoreSystemServerImpl.notifyServiceReady(coreService.serviceName(), coreService.getServiceBinder());
    }
}
