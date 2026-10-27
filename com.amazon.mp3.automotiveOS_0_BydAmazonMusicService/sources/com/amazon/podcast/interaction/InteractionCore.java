package com.amazon.podcast.interaction;

import Podcast.DeeplinkInterface.v1_0.InvokeDeeplinkMethod;
import Podcast.DeeplinkInterface.v1_0.NavigateToDeeplinkMethod;
import SOACoreInterface.v1_0.Method;
import SOAInteractionInterface.v1_0.GlobalInvokeRemoteSkillMethod;
import SOAInteractionInterface.v1_0.InvokeRemoteSkillMethod;
import android.content.Context;
import com.amazon.music.platform.providers.AuthenticationProvider;
import com.amazon.music.platform.providers.BuildInfoProvider;
import com.amazon.music.platform.providers.DeviceInfoProvider;
import com.amazon.podcast.ClientStates;
import com.amazon.podcast.MethodsDispatcher;
import com.amazon.podcast.NavigationHandler;
import com.amazon.podcast.bootstrap.UserInfoProvider;
import com.amazon.soa.core.Engine;
import com.amazon.soa.core.MethodCallback;
import com.amazon.soa.core.OnMethodExecutedListener;
import java.util.HashSet;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/* JADX INFO: loaded from: classes5.dex */
public final class InteractionCore implements MethodCallback, MethodsDispatcher {
    private final AuthenticationProvider authProvider;
    private final BuildInfoProvider buildInfoProvider;
    private final ClientStates clientStates;
    private final Context context;
    private final DeviceInfoProvider deviceInfoProvider;
    private final Engine engine;
    private final ExecutorService executorService = Executors.newCachedThreadPool();
    private final UserInfoProvider userInfoProvider;

    public InteractionCore(final Engine engine, final Context context, final ClientStates clientStates, final UserInfoProvider userInfoProvider, final AuthenticationProvider authProvider, final DeviceInfoProvider deviceInfoProvider, final BuildInfoProvider buildInfoProvider) {
        this.engine = engine;
        this.context = context;
        HashSet hashSet = new HashSet();
        hashSet.add(InvokeRemoteSkillMethod.class.getCanonicalName());
        hashSet.add(InvokeDeeplinkMethod.class.getCanonicalName());
        HashSet hashSet2 = new HashSet();
        hashSet2.add(GlobalInvokeRemoteSkillMethod.class.getCanonicalName());
        hashSet2.add(NavigateToDeeplinkMethod.class.getCanonicalName());
        engine.registerMethods(hashSet, hashSet2, this);
        this.clientStates = clientStates;
        this.userInfoProvider = userInfoProvider;
        this.authProvider = authProvider;
        this.deviceInfoProvider = deviceInfoProvider;
        this.buildInfoProvider = buildInfoProvider;
    }

    @Override // com.amazon.soa.core.MethodCallback
    public void executeMethod(final Method method, final String ownerId, final OnMethodExecutedListener onMethodExecutedListener) {
        if (method instanceof NavigateToDeeplinkMethod) {
            navigateToDeeplink((NavigateToDeeplinkMethod) method, onMethodExecutedListener);
        } else {
            invokeRemoteSkill(ownerId, method, this, onMethodExecutedListener);
        }
    }

    @Override // com.amazon.podcast.MethodsDispatcher
    public void dispatch(final String ownerId, final List<Method> methods) {
        this.engine.handleMethods(ownerId, methods);
    }

    private void invokeRemoteSkill(final String ownerId, final Method method, final MethodsDispatcher dispatcher, final OnMethodExecutedListener listener) {
        this.executorService.submit(new InvokeRemoteSkillRunnable(ownerId, method, this.deviceInfoProvider, this.buildInfoProvider, this.clientStates, this.userInfoProvider, this.authProvider, dispatcher, listener));
    }

    private void navigateToDeeplink(final NavigateToDeeplinkMethod method, final OnMethodExecutedListener listener) {
        new NavigationHandler(this.context).navigateToDeeplink(method.getUrl());
        listener.onMethodExecuted(method);
    }
}
