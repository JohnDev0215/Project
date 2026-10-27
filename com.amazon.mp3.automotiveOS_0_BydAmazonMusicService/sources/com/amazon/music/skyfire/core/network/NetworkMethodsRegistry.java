package com.amazon.music.skyfire.core.network;

import CoreInterface.v1_0.Method;
import InteractionInterface.v1_0.GlobalInvokeHttpSkillMethod;
import InteractionInterface.v1_0.ImmutableGlobalInvokeHttpSkillMethod;
import InteractionInterface.v1_0.ImmutableInvokeHttpSkillMethod;
import InteractionInterface.v1_0.InvokeHttpSkillMethod;
import com.amazon.music.skyfire.SkyFireApplication;
import com.amazon.music.skyfire.core.performance.SkillLatencyTracker;
import com.amazon.music.skyfire.platform.MethodCallback;
import com.amazon.music.skyfire.platform.MethodsDispatcher;
import com.amazon.music.skyfire.platform.OnMethodExecutedListener;
import java.util.HashSet;

/* JADX INFO: loaded from: classes4.dex */
public final class NetworkMethodsRegistry implements MethodCallback<Method> {
    private final Authentication authentication;
    private final BuildInfo buildInfo;
    private final CapabilitiesInfo capabilitiesInfo;
    private final ClientInformationCache clientInformationCache;
    private final DeviceInfo deviceInfo;
    private final HttpSkillInfo httpSkillInfo;
    private MethodsDispatcher methodsDispatcher;
    private final NetworkInterceptor networkInterceptor;
    private final SkillLatencyTracker skillLatencyTracker;
    private final SkyFireApplication skyFireApplication;

    @Override // com.amazon.music.skyfire.platform.MethodCallback
    public /* bridge */ /* synthetic */ void executeMethod(Method method, String str, OnMethodExecutedListener<Method> onMethodExecutedListener) {
        executeMethod2(method, str, (OnMethodExecutedListener) onMethodExecutedListener);
    }

    public NetworkMethodsRegistry(SkyFireApplication skyFireApplication, DeviceInfo deviceInfo, BuildInfo buildInfo, HttpSkillInfo httpSkillInfo, CapabilitiesInfo capabilitiesInfo, NetworkInterceptor networkInterceptor, SkillLatencyTracker skillLatencyTracker, Authentication authentication, ClientInformationCache clientInformationCache) {
        this.deviceInfo = deviceInfo;
        this.buildInfo = buildInfo;
        this.httpSkillInfo = httpSkillInfo;
        this.capabilitiesInfo = capabilitiesInfo;
        this.networkInterceptor = networkInterceptor;
        this.skillLatencyTracker = skillLatencyTracker;
        this.authentication = authentication;
        this.clientInformationCache = clientInformationCache;
        this.skyFireApplication = skyFireApplication;
    }

    public void registerMethods() {
        HashSet hashSet = new HashSet();
        hashSet.add(ImmutableInvokeHttpSkillMethod.class.getCanonicalName());
        HashSet hashSet2 = new HashSet();
        hashSet2.add(ImmutableGlobalInvokeHttpSkillMethod.class.getCanonicalName());
        this.skyFireApplication.registerMethods(hashSet, hashSet2, this);
    }

    /* JADX INFO: renamed from: executeMethod, reason: avoid collision after fix types in other method */
    public void executeMethod2(Method method, String str, OnMethodExecutedListener onMethodExecutedListener) {
        if (method instanceof InvokeHttpSkillMethod) {
            handleInvokeHttpSkillMethod((InvokeHttpSkillMethod) method, str, onMethodExecutedListener);
        } else if (method instanceof GlobalInvokeHttpSkillMethod) {
            handleGlobalInvokeHttpSkillMethod((GlobalInvokeHttpSkillMethod) method, str, onMethodExecutedListener);
        }
    }

    public void setMethodsDispatcher(MethodsDispatcher methodsDispatcher) {
        this.methodsDispatcher = methodsDispatcher;
    }

    private void handleInvokeHttpSkillMethod(InvokeHttpSkillMethod invokeHttpSkillMethod, String str, OnMethodExecutedListener onMethodExecutedListener) {
        NetworkEngine.INSTANCE.sendNetworkRequest(str, invokeHttpSkillMethod, this.authentication, this.deviceInfo, this.buildInfo, this.httpSkillInfo, this.capabilitiesInfo, this.networkInterceptor, this.skillLatencyTracker, this.methodsDispatcher, this.clientInformationCache, invokeHttpSkillMethod.clientInformation(), onMethodExecutedListener);
    }

    private void handleGlobalInvokeHttpSkillMethod(GlobalInvokeHttpSkillMethod globalInvokeHttpSkillMethod, String str, OnMethodExecutedListener onMethodExecutedListener) {
        NetworkEngine.INSTANCE.sendNetworkRequest(str, globalInvokeHttpSkillMethod, this.authentication, this.deviceInfo, this.buildInfo, this.httpSkillInfo, this.capabilitiesInfo, this.networkInterceptor, this.skillLatencyTracker, this.methodsDispatcher, this.clientInformationCache, globalInvokeHttpSkillMethod.clientInformation(), onMethodExecutedListener);
    }
}
