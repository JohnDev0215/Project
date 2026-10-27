package com.amazon.music.curate.skyfire.network;

import CoreInterface.v1_0.ClientInformation;
import CoreInterface.v1_0.Method;
import CoreInterface.v1_0.Response;
import InteractionInterface.v1_0.GlobalInvokeHttpSkillMethod;
import com.amazon.music.curate.data.database.AppDatabase;
import com.amazon.music.curate.provider.FeatureFlagProvider;
import com.amazon.music.platform.providers.AuthenticationProvider;
import com.amazon.music.platform.providers.BuildInfoProvider;
import com.amazon.music.platform.providers.CustomerMetadataProvider;
import com.amazon.music.platform.providers.DeviceInfoProvider;
import com.amazon.music.skyfire.platform.MethodsDispatcher;
import com.amazon.music.skyfire.platform.OnMethodExecutedListener;
import java.util.List;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/* JADX INFO: loaded from: classes4.dex */
final class InvokeHttpSkillRunnable implements Runnable {
    private static final String TAG = InvokeHttpSkillRunnable.class.getSimpleName();
    private final AppDatabase appDatabase;
    private final AuthenticationProvider authProvider;
    private final BuildInfoProvider buildInfoProvider;
    private final Map<String, ClientInformation> clientInformationMap;
    private final CustomerMetadataProvider customerMetadataProvider;
    private final DeviceInfoProvider deviceInfoProvider;
    private final FeatureFlagProvider featureFlagProvider;
    private final OnMethodExecutedListener listener;
    private final Logger logger = LoggerFactory.getLogger(TAG);
    private final Method method;
    private final MethodsDispatcher methodsDispatcher;
    private final String ownerId;

    public InvokeHttpSkillRunnable(String ownerId, Method method, Map<String, ClientInformation> clientInformationMap, MethodsDispatcher methodsDispatcher, OnMethodExecutedListener listener, AppDatabase appDatabase, BuildInfoProvider buildInfoProvider, DeviceInfoProvider deviceInfoProvider, FeatureFlagProvider featureFlagProvider, AuthenticationProvider authProvider, CustomerMetadataProvider customerMetadataProvider) {
        this.ownerId = ownerId;
        this.method = method;
        this.methodsDispatcher = methodsDispatcher;
        this.clientInformationMap = clientInformationMap;
        this.listener = listener;
        this.appDatabase = appDatabase;
        this.buildInfoProvider = buildInfoProvider;
        this.deviceInfoProvider = deviceInfoProvider;
        this.featureFlagProvider = featureFlagProvider;
        this.authProvider = authProvider;
        this.customerMetadataProvider = customerMetadataProvider;
    }

    @Override // java.lang.Runnable
    public void run() {
        String strUrl;
        List<Method> listBefore;
        List<Method> listAfter;
        List<Method> listOnError;
        List<Method> listOnSuccess;
        Method method = this.method;
        if (method instanceof InteractionInterface.v1_0.InvokeHttpSkillMethod) {
            InteractionInterface.v1_0.InvokeHttpSkillMethod invokeHttpSkillMethod = (InteractionInterface.v1_0.InvokeHttpSkillMethod) method;
            strUrl = invokeHttpSkillMethod.url();
            listBefore = invokeHttpSkillMethod.before();
            listAfter = invokeHttpSkillMethod.after();
            listOnError = invokeHttpSkillMethod.onError();
            listOnSuccess = invokeHttpSkillMethod.onSuccess();
        } else {
            GlobalInvokeHttpSkillMethod globalInvokeHttpSkillMethod = (GlobalInvokeHttpSkillMethod) method;
            strUrl = globalInvokeHttpSkillMethod.url();
            listBefore = globalInvokeHttpSkillMethod.before();
            listAfter = globalInvokeHttpSkillMethod.after();
            listOnError = globalInvokeHttpSkillMethod.onError();
            listOnSuccess = globalInvokeHttpSkillMethod.onSuccess();
        }
        String str = strUrl;
        List<Method> list = listAfter;
        List<Method> list2 = listOnError;
        this.methodsDispatcher.dispatchMethods(this.ownerId, listBefore);
        Response responseExecute = new InvokeHttpSkillTask(str, this.appDatabase, this.buildInfoProvider, this.deviceInfoProvider, this.featureFlagProvider, this.authProvider, this.customerMetadataProvider).execute();
        this.listener.onMethodExecuted(this.method);
        if (responseExecute == null || responseExecute.methods() == null || responseExecute.methods().isEmpty()) {
            this.methodsDispatcher.dispatchMethods(this.ownerId, list);
            this.methodsDispatcher.dispatchMethods(this.ownerId, list2);
            this.logger.warn("No response or error response from Skyfire for request: {} ", str);
            if (list2 != null) {
                FlexEventMetrics.sendEvent(FlexEventMetrics.CURATE_FLEX_LIBRARY_LOAD_DATABASE, "onServiceError", "");
                return;
            }
            return;
        }
        this.methodsDispatcher.dispatchMethods(this.ownerId, responseExecute.methods());
        this.methodsDispatcher.dispatchMethods(this.ownerId, list);
        this.methodsDispatcher.dispatchMethods(this.ownerId, listOnSuccess);
    }
}
