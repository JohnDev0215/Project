package com.amazon.mobilepushfrontend.appstate.external.api;

import com.amazon.CoralAndroidClient.ClientBase.ResultHandler;
import com.amazon.mobilepushfrontendappstateexternal.RegisterApplicationInstallRequest;
import com.amazon.mobilepushfrontendappstateexternal.UpdateApplicationInstallRequest;

/* JADX INFO: loaded from: classes3.dex */
public interface MobilePushFrontendExternalAppStateService {
    void registerApplicationInstallAsync(RegisterApplicationInstallRequest registerApplicationInstallRequest, ResultHandler resultHandler);

    void updateApplicationInstallAsync(UpdateApplicationInstallRequest updateApplicationInstallRequest, ResultHandler resultHandler);
}
