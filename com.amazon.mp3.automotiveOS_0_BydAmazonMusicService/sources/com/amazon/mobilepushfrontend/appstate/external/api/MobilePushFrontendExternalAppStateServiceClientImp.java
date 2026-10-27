package com.amazon.mobilepushfrontend.appstate.external.api;

import com.amazon.CoralAndroidClient.ClientBase.ClientBase;
import com.amazon.CoralAndroidClient.ClientBase.ResultHandler;
import com.amazon.mobilepushfrontend.appstate.external.transform.RegisterApplicationInstallRequestMarshaller;
import com.amazon.mobilepushfrontend.appstate.external.transform.RegisterApplicationInstallUnmarshaller;
import com.amazon.mobilepushfrontend.appstate.external.transform.UpdateApplicationInstallRequestMarshaller;
import com.amazon.mobilepushfrontendappstateexternal.RegisterApplicationInstallRequest;
import com.amazon.mobilepushfrontendappstateexternal.UpdateApplicationInstallRequest;

/* JADX INFO: loaded from: classes3.dex */
public class MobilePushFrontendExternalAppStateServiceClientImp extends ClientBase implements MobilePushFrontendExternalAppStateService {
    @Override // com.amazon.mobilepushfrontend.appstate.external.api.MobilePushFrontendExternalAppStateService
    public void registerApplicationInstallAsync(RegisterApplicationInstallRequest registerApplicationInstallRequest, ResultHandler resultHandler) {
        invokeAsync(registerApplicationInstallRequest, new RegisterApplicationInstallRequestMarshaller(), new RegisterApplicationInstallUnmarshaller(), resultHandler);
    }

    @Override // com.amazon.mobilepushfrontend.appstate.external.api.MobilePushFrontendExternalAppStateService
    public void updateApplicationInstallAsync(UpdateApplicationInstallRequest updateApplicationInstallRequest, ResultHandler resultHandler) {
        invokeAsync(updateApplicationInstallRequest, new UpdateApplicationInstallRequestMarshaller(), null, resultHandler);
    }
}
