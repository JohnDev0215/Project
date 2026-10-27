package com.amazon.music.curate.skyfire.network;

import CoreInterface.v1_0.ClientInformation;
import CoreInterface.v1_0.Method;
import InteractionInterface.v1_0.GlobalInvokeHttpSkillMethod;
import InteractionInterface.v1_0.ImmutableGlobalInvokeHttpSkillMethod;
import InteractionInterface.v1_0.ImmutableInvokeHttpSkillMethod;
import com.amazon.music.curate.data.database.AppDatabase;
import com.amazon.music.curate.provider.FeatureFlagProvider;
import com.amazon.music.curate.skyfire.core.MethodEngine;
import com.amazon.music.platform.providers.AuthenticationProvider;
import com.amazon.music.platform.providers.BuildInfoProvider;
import com.amazon.music.platform.providers.CustomerMetadataProvider;
import com.amazon.music.platform.providers.DeviceInfoProvider;
import com.amazon.music.skyfire.platform.MethodCallback;
import com.amazon.music.skyfire.platform.MethodsDispatcher;
import com.amazon.music.skyfire.platform.OnMethodExecutedListener;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/* JADX INFO: loaded from: classes4.dex */
public final class NetworkEngine implements MethodCallback<Method>, MethodsDispatcher<Method> {
    private final AppDatabase appDatabase;
    private final AuthenticationProvider authProvider;
    private final BuildInfoProvider buildInfoProvider;
    private final ClientInformationCache clientInformationCache;
    private final CustomerMetadataProvider customerMetadataProvider;
    private final DeviceInfoProvider deviceInfoProvider;
    private final MethodEngine engine;
    private final ExecutorService executorService = Executors.newCachedThreadPool();
    private final FeatureFlagProvider featureFlagProvider;

    @Override // com.amazon.music.skyfire.platform.MethodCallback
    public /* bridge */ /* synthetic */ void executeMethod(final Method method, final String ownerId, final OnMethodExecutedListener<Method> onMethodExecutedListener) {
        executeMethod2(method, ownerId, (OnMethodExecutedListener) onMethodExecutedListener);
    }

    public NetworkEngine(MethodEngine engine, ClientInformationCache clientInformationCache, AppDatabase appDatabase, BuildInfoProvider buildInfoProvider, DeviceInfoProvider deviceInfoProvider, FeatureFlagProvider featureFlagProvider, AuthenticationProvider authProvider, CustomerMetadataProvider customerMetadataProvider) {
        this.engine = engine;
        this.clientInformationCache = clientInformationCache;
        this.appDatabase = appDatabase;
        this.buildInfoProvider = buildInfoProvider;
        this.deviceInfoProvider = deviceInfoProvider;
        this.featureFlagProvider = featureFlagProvider;
        this.authProvider = authProvider;
        this.customerMetadataProvider = customerMetadataProvider;
        HashSet hashSet = new HashSet();
        hashSet.add(ImmutableInvokeHttpSkillMethod.class.getCanonicalName());
        HashSet hashSet2 = new HashSet();
        hashSet2.add(ImmutableGlobalInvokeHttpSkillMethod.class.getCanonicalName());
        engine.registerMethods(hashSet, hashSet2, this);
    }

    /* JADX INFO: renamed from: executeMethod, reason: avoid collision after fix types in other method */
    public void executeMethod2(final Method method, final String ownerId, final OnMethodExecutedListener onMethodExecutedListener) {
        invokeHttpSkill(ownerId, method, this, onMethodExecutedListener);
    }

    @Override // com.amazon.music.skyfire.platform.MethodsDispatcher
    public void dispatchMethods(final String ownerId, final List<? extends Method> methods) {
        this.engine.handleMethods(ownerId, methods);
    }

    private void invokeHttpSkill(final String ownerId, final Method method, final MethodsDispatcher dispatcher, final OnMethodExecutedListener listener) {
        Map<String, ClientInformation> mapClientInformationMap;
        if (method instanceof InteractionInterface.v1_0.InvokeHttpSkillMethod) {
            mapClientInformationMap = clientInformationMap((InteractionInterface.v1_0.InvokeHttpSkillMethod) method);
        } else {
            mapClientInformationMap = clientInformationMap((GlobalInvokeHttpSkillMethod) method);
        }
        this.executorService.submit(new InvokeHttpSkillRunnable(ownerId, method, mapClientInformationMap, dispatcher, listener, this.appDatabase, this.buildInfoProvider, this.deviceInfoProvider, this.featureFlagProvider, this.authProvider, this.customerMetadataProvider));
    }

    private Map<String, ClientInformation> clientInformationMap(final InteractionInterface.v1_0.InvokeHttpSkillMethod method) {
        return queryMap(method.clientInformation());
    }

    private Map<String, ClientInformation> clientInformationMap(final GlobalInvokeHttpSkillMethod method) {
        return queryMap(method.clientInformation());
    }

    private Map<String, ClientInformation> queryMap(final List<String> clientInformationList) {
        if (clientInformationList == null || clientInformationList.isEmpty()) {
            return null;
        }
        HashMap map = new HashMap();
        Iterator<String> it = clientInformationList.iterator();
        while (it.hasNext()) {
            ClientInformation clientInformation = this.clientInformationCache.get(it.next());
            if (clientInformation != null) {
                map.put(this.clientInformationCache.queryKey(clientInformation), clientInformation);
            }
        }
        return map;
    }
}
