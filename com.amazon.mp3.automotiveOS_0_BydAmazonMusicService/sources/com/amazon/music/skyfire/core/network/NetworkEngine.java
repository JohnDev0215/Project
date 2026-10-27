package com.amazon.music.skyfire.core.network;

import CoreInterface.v1_0.Method;
import com.amazon.music.skyfire.core.performance.SkillLatencyTracker;
import com.amazon.music.skyfire.platform.MethodsDispatcher;
import com.amazon.music.skyfire.platform.OnMethodExecutedListener;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/* JADX INFO: loaded from: classes4.dex */
enum NetworkEngine {
    INSTANCE;

    private static final ExecutorService executorService = Executors.newCachedThreadPool();

    public void sendNetworkRequest(String str, Method method, Authentication authentication, DeviceInfo deviceInfo, BuildInfo buildInfo, HttpSkillInfo httpSkillInfo, CapabilitiesInfo capabilitiesInfo, NetworkInterceptor networkInterceptor, SkillLatencyTracker skillLatencyTracker, MethodsDispatcher methodsDispatcher, ClientInformationCache clientInformationCache, List<String> list, OnMethodExecutedListener onMethodExecutedListener) {
        executorService.submit(new NetworkRunnable(str, method, authentication, deviceInfo, buildInfo, httpSkillInfo, capabilitiesInfo, networkInterceptor, skillLatencyTracker, methodsDispatcher, clientInformationCache, list, onMethodExecutedListener));
    }
}
