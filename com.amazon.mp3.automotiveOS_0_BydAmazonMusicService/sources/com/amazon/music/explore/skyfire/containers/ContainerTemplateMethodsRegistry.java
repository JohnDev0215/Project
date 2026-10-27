package com.amazon.music.explore.skyfire.containers;

import CoreInterface.v1_0.Method;
import ExternalActionInterface.v1_0.ImmutableToggleButtonMethod;
import ExternalActionInterface.v1_0.ImmutableTriggerExternalActionMethod;
import ExternalActionInterface.v1_0.ImmutableTriggerExternalGenericPlaybackMethod;
import ExternalActionInterface.v1_0.ImmutableTriggerExternalPlaybackMethod;
import ExternalActionInterface.v1_0.TriggerExternalActionMethod;
import TemplateContainerInterface.v1_0.ImmutableBindManagedContainerMethod;
import TemplateContainerInterface.v1_0.ImmutableBindStandaloneContainerMethod;
import TemplateContainerInterface.v1_0.ImmutableCreateAndBindManagedContainerMethod;
import TemplateContainerInterface.v1_0.ImmutableCreateAndBindStandaloneContainerMethod;
import TemplateContainerInterface.v1_0.ImmutableCreateManagedContainerMethod;
import TemplateContainerInterface.v1_0.ImmutableCreateStandaloneContainerMethod;
import android.os.Looper;
import com.amazon.music.skyfire.SkyFireApplication;
import com.amazon.music.skyfire.core.OwnerRegistry;
import com.amazon.music.skyfire.core.template.Handlers;
import com.amazon.music.skyfire.platform.MethodCallback;
import com.amazon.music.skyfire.platform.OnMethodExecutedListener;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/* JADX INFO: loaded from: classes4.dex */
public class ContainerTemplateMethodsRegistry implements MethodCallback<Method> {
    private OwnerRegistry ownerRegistry;
    private final SkyFireApplication skyFireApplication;
    private final Map<String, ContainerMethodOwner> ownerMap = new HashMap();
    private final Set<String> containerMethods = getContainerMethods();

    @Override // com.amazon.music.skyfire.platform.MethodCallback
    public /* bridge */ /* synthetic */ void executeMethod(Method method, String str, OnMethodExecutedListener<Method> onMethodExecutedListener) {
        executeMethod2(method, str, (OnMethodExecutedListener) onMethodExecutedListener);
    }

    public ContainerTemplateMethodsRegistry(SkyFireApplication skyFireApplication) {
        this.skyFireApplication = skyFireApplication;
    }

    public void registerMethods() {
        this.skyFireApplication.registerMethods(Collections.emptySet(), this.containerMethods, this);
    }

    public void unRegisterMethods() {
        SkyFireApplication skyFireApplication = this.skyFireApplication;
        Set<String> set = this.containerMethods;
        skyFireApplication.deregisterMethods(set, set);
    }

    /* JADX INFO: renamed from: executeMethod, reason: avoid collision after fix types in other method */
    public void executeMethod2(final Method method, final String str, final OnMethodExecutedListener onMethodExecutedListener) {
        if (this.ownerRegistry.isOwnerAvailable(str) && this.ownerMap.containsKey(str)) {
            if (Looper.myLooper() == Looper.getMainLooper()) {
                lambda$executeMethod$0(str, method, onMethodExecutedListener);
            } else {
                Handlers.getForeground().post(new Runnable() { // from class: com.amazon.music.explore.skyfire.containers.ContainerTemplateMethodsRegistry$$ExternalSyntheticLambda0
                    @Override // java.lang.Runnable
                    public final void run() {
                        this.f$0.lambda$executeMethod$0(str, method, onMethodExecutedListener);
                    }
                });
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: handleMethodInternal, reason: merged with bridge method [inline-methods] */
    public void lambda$executeMethod$0(String str, Method method, OnMethodExecutedListener onMethodExecutedListener) {
        ContainerMethodOwner containerMethodOwner = this.ownerMap.get(str);
        if (containerMethodOwner != null) {
            containerMethodOwner.handleMethod(str, method);
        }
        onMethodExecutedListener.onMethodExecuted(method);
    }

    public void setOwnerRegistry(OwnerRegistry ownerRegistry) {
        this.ownerRegistry = ownerRegistry;
    }

    public void registerOwner(ContainerMethodOwner containerMethodOwner) {
        String ownerId = containerMethodOwner.getOwnerId();
        this.ownerMap.put(ownerId, containerMethodOwner);
        this.ownerRegistry.registerOwner(ownerId);
    }

    public void deregisterOwner(String str) {
        this.ownerMap.remove(str);
        this.ownerRegistry.unregisterOwner(str);
    }

    public Set<String> getContainerMethods() {
        HashSet hashSet = new HashSet();
        hashSet.add(ImmutableCreateManagedContainerMethod.class.getCanonicalName());
        hashSet.add(ImmutableCreateAndBindManagedContainerMethod.class.getCanonicalName());
        hashSet.add(ImmutableBindManagedContainerMethod.class.getCanonicalName());
        hashSet.add(ImmutableCreateStandaloneContainerMethod.class.getCanonicalName());
        hashSet.add(ImmutableCreateAndBindStandaloneContainerMethod.class.getCanonicalName());
        hashSet.add(ImmutableBindStandaloneContainerMethod.class.getCanonicalName());
        hashSet.add(TriggerExternalActionMethod.class.getCanonicalName());
        hashSet.add(ImmutableTriggerExternalActionMethod.class.getCanonicalName());
        hashSet.add(ImmutableTriggerExternalGenericPlaybackMethod.class.getCanonicalName());
        hashSet.add(ImmutableTriggerExternalPlaybackMethod.class.getCanonicalName());
        hashSet.add(ImmutableToggleButtonMethod.class.getCanonicalName());
        return Collections.unmodifiableSet(hashSet);
    }
}
