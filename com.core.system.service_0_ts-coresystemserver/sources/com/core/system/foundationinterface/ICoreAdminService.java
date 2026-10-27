package com.core.system.foundationinterface;

import com.ts.car.core.annotation.FoundationInterface;

/* JADX INFO: loaded from: classes.dex */
@FoundationInterface
public interface ICoreAdminService {
    boolean addService(ICoreService iCoreService);

    boolean notifyServiceReady(ICoreService iCoreService);
}
