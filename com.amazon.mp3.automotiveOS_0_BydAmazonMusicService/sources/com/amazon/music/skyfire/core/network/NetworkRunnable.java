package com.amazon.music.skyfire.core.network;

import CoreInterface.v1_0.ClientInformation;
import CoreInterface.v1_0.Method;
import CoreInterface.v1_0.Response;
import InteractionInterface.v1_0.GlobalInvokeHttpSkillMethod;
import InteractionInterface.v1_0.InvokeHttpSkillMethod;
import com.amazon.music.skyfire.core.performance.SkillLatencyTracker;
import com.amazon.music.skyfire.platform.MethodsDispatcher;
import com.amazon.music.skyfire.platform.OnMethodExecutedListener;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

/* JADX INFO: loaded from: classes4.dex */
final class NetworkRunnable implements Runnable {
    private final Authentication authentication;
    private final BuildInfo buildInfo;
    private final CapabilitiesInfo capabilitiesInfo;
    private final ClientInformationCache clientInformationCache;
    private final List<String> clientInformationList;
    private final DeviceInfo deviceInfo;
    private final HttpSkillInfo httpSkillInfo;
    private final OnMethodExecutedListener listener;
    private final Method method;
    private final MethodsDispatcher methodsDispatcher;
    private final NetworkInterceptor networkInterceptor;
    private final String ownerId;
    private final SkillLatencyTracker skillLatencyTracker;

    public NetworkRunnable(String str, Method method, Authentication authentication, DeviceInfo deviceInfo, BuildInfo buildInfo, HttpSkillInfo httpSkillInfo, CapabilitiesInfo capabilitiesInfo, NetworkInterceptor networkInterceptor, SkillLatencyTracker skillLatencyTracker, MethodsDispatcher methodsDispatcher, ClientInformationCache clientInformationCache, List<String> list, OnMethodExecutedListener onMethodExecutedListener) {
        this.ownerId = str;
        this.method = method;
        this.authentication = authentication;
        this.deviceInfo = deviceInfo;
        this.buildInfo = buildInfo;
        this.httpSkillInfo = httpSkillInfo;
        this.capabilitiesInfo = capabilitiesInfo;
        this.networkInterceptor = networkInterceptor;
        this.skillLatencyTracker = skillLatencyTracker;
        this.methodsDispatcher = methodsDispatcher;
        this.clientInformationList = list;
        this.clientInformationCache = clientInformationCache;
        this.listener = onMethodExecutedListener;
    }

    @Override // java.lang.Runnable
    public void run() {
        String strUrl;
        List<Method> listBefore;
        List<Method> listAfter;
        List<Method> listOnError;
        List<Method> listOnSuccess;
        Method method = this.method;
        if (method instanceof InvokeHttpSkillMethod) {
            InvokeHttpSkillMethod invokeHttpSkillMethod = (InvokeHttpSkillMethod) method;
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
        List<Method> list = listAfter;
        List<Method> list2 = listOnError;
        this.methodsDispatcher.dispatchMethods(this.ownerId, listBefore);
        Response responseExecute = new NetworkTask(strUrl, queryMap(this.clientInformationCache, this.clientInformationList), this.authentication, this.deviceInfo, this.buildInfo, this.httpSkillInfo, this.capabilitiesInfo, this.networkInterceptor, this.skillLatencyTracker).execute();
        this.listener.onMethodExecuted(this.method);
        if (responseExecute == null) {
            this.methodsDispatcher.dispatchMethods(this.ownerId, list);
            this.methodsDispatcher.dispatchMethods(this.ownerId, list2);
        } else {
            this.methodsDispatcher.dispatchMethods(this.ownerId, responseExecute.methods());
            this.methodsDispatcher.dispatchMethods(this.ownerId, list);
            this.methodsDispatcher.dispatchMethods(this.ownerId, listOnSuccess);
        }
    }

    private Map<String, ClientInformation> queryMap(ClientInformationCache clientInformationCache, List<String> list) {
        HashMap map = new HashMap();
        if (list != null && !list.isEmpty()) {
            Iterator<String> it = list.iterator();
            while (it.hasNext()) {
                ClientInformation clientInformation = clientInformationCache.get(it.next());
                if (clientInformation != null) {
                    map.put(clientInformationCache.queryKey(clientInformation), clientInformation);
                }
            }
        }
        return map;
    }
}
