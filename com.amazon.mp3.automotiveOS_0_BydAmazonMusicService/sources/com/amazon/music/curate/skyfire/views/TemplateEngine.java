package com.amazon.music.curate.skyfire.views;

import CoreInterface.v1_0.Method;
import ExternalActionInterface.v1_0.ImmutableTriggerExternalActionMethod;
import TemplateContainerInterface.v1_0.BindManagedContainerMethod;
import TemplateContainerInterface.v1_0.BindStandaloneContainerMethod;
import TemplateContainerInterface.v1_0.CreateAndBindManagedContainerMethod;
import TemplateContainerInterface.v1_0.CreateAndBindStandaloneContainerMethod;
import TemplateContainerInterface.v1_0.CreateManagedContainerMethod;
import TemplateContainerInterface.v1_0.CreateStandaloneContainerMethod;
import TemplateContainerInterface.v1_0.ImmutableBindManagedContainerMethod;
import TemplateContainerInterface.v1_0.ImmutableBindStandaloneContainerMethod;
import TemplateContainerInterface.v1_0.ImmutableCreateAndBindManagedContainerMethod;
import TemplateContainerInterface.v1_0.ImmutableCreateAndBindStandaloneContainerMethod;
import TemplateContainerInterface.v1_0.ImmutableCreateManagedContainerMethod;
import TemplateContainerInterface.v1_0.ImmutableCreateStandaloneContainerMethod;
import TemplateListInterface.v1_0.ImmutableRemoveTemplateMethod;
import TemplateListInterface.v1_0.RemoveTemplateMethod;
import com.amazon.music.curate.Handlers;
import com.amazon.music.curate.skyfire.OwnerRegistration;
import com.amazon.music.curate.skyfire.bootstrap.CurateBootstrapProviders;
import com.amazon.music.curate.skyfire.core.MethodEngine;
import com.amazon.music.curate.skyfire.core.OwnerIdGenerator;
import com.amazon.music.skyfire.platform.MethodCallback;
import com.amazon.music.skyfire.platform.MethodsDispatcher;
import com.amazon.music.skyfire.platform.OnMethodExecutedListener;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

/* JADX INFO: loaded from: classes4.dex */
public final class TemplateEngine implements MethodCallback<Method>, MethodsDispatcher<Method>, OwnerRegistration {
    private final MethodEngine engine;
    private final Map<String, TemplateContainer> lookup = new HashMap();

    @Override // com.amazon.music.skyfire.platform.MethodCallback
    public /* bridge */ /* synthetic */ void executeMethod(final Method method, final String ownerId, final OnMethodExecutedListener<Method> onMethodExecutedListener) {
        executeMethod2(method, ownerId, (OnMethodExecutedListener) onMethodExecutedListener);
    }

    public TemplateEngine(final MethodEngine engine) {
        this.engine = engine;
        HashSet hashSet = new HashSet();
        hashSet.add(ImmutableCreateStandaloneContainerMethod.class.getCanonicalName());
        hashSet.add(ImmutableCreateManagedContainerMethod.class.getCanonicalName());
        hashSet.add(ImmutableCreateAndBindStandaloneContainerMethod.class.getCanonicalName());
        hashSet.add(ImmutableCreateAndBindManagedContainerMethod.class.getCanonicalName());
        hashSet.add(ImmutableBindStandaloneContainerMethod.class.getCanonicalName());
        hashSet.add(ImmutableBindManagedContainerMethod.class.getCanonicalName());
        hashSet.add(ImmutableTriggerExternalActionMethod.class.getCanonicalName());
        hashSet.add(ImmutableRemoveTemplateMethod.class.getCanonicalName());
        engine.registerMethods(hashSet, Collections.emptySet(), this);
    }

    public TemplateContainer createTemplateContainer(final CurateBootstrapProviders curateBootstrapProviders) {
        String strForTemplateContainer = OwnerIdGenerator.forTemplateContainer();
        TemplateContainer templateContainer = new TemplateContainer(strForTemplateContainer, curateBootstrapProviders, this, this);
        this.lookup.put(strForTemplateContainer, templateContainer);
        this.engine.registerOwner(strForTemplateContainer);
        return templateContainer;
    }

    @Override // com.amazon.music.curate.skyfire.OwnerRegistration
    public void registerOwner(final String ownerId) {
        this.engine.registerOwner(ownerId);
    }

    @Override // com.amazon.music.curate.skyfire.OwnerRegistration
    public void deregisterOwner(final String ownerId) {
        this.engine.deregisterOwner(ownerId);
    }

    /* JADX INFO: renamed from: executeMethod, reason: avoid collision after fix types in other method */
    public void executeMethod2(final Method method, final String ownerId, final OnMethodExecutedListener onMethodExecutedListener) {
        Handlers.INSTANCE.getForeground().post(new Runnable() { // from class: com.amazon.music.curate.skyfire.views.TemplateEngine.1
            @Override // java.lang.Runnable
            public void run() {
                TemplateEngine.this.executeMethodForeground(method, ownerId, onMethodExecutedListener);
            }
        });
    }

    @Override // com.amazon.music.skyfire.platform.MethodsDispatcher
    public void dispatchMethods(final String ownerId, final List<? extends Method> methods) {
        this.engine.handleMethods(ownerId, methods);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void executeMethodForeground(final Method method, final String ownerId, final OnMethodExecutedListener listener) {
        TemplateContainer value = null;
        for (Map.Entry<String, TemplateContainer> entry : this.lookup.entrySet()) {
            if (ownerId.startsWith(entry.getKey())) {
                value = entry.getValue();
            }
        }
        if (value == null) {
            listener.onMethodExecuted(method);
            return;
        }
        if (value.getCurateBootstrapProviders() == null) {
            listener.onMethodExecuted(method);
            return;
        }
        if (value.getCurateBootstrapProviders().isAtLeastOneProviderNull()) {
            listener.onMethodExecuted(method);
            return;
        }
        if (method instanceof CreateManagedContainerMethod) {
            value.createManagedContainerMethod((CreateManagedContainerMethod) method, listener);
            return;
        }
        if (method instanceof CreateStandaloneContainerMethod) {
            value.createStandaloneContainerMethod((CreateStandaloneContainerMethod) method, listener);
            return;
        }
        if (method instanceof CreateAndBindManagedContainerMethod) {
            value.createAndBindManagedContainerMethod((CreateAndBindManagedContainerMethod) method, listener);
            return;
        }
        if (method instanceof CreateAndBindStandaloneContainerMethod) {
            value.createAndBindStandaloneContainerMethod((CreateAndBindStandaloneContainerMethod) method, listener);
            return;
        }
        if (method instanceof BindStandaloneContainerMethod) {
            value.bindStandaloneContainerMethod(ownerId, (ImmutableBindStandaloneContainerMethod) method, listener);
            return;
        }
        if (method instanceof BindManagedContainerMethod) {
            value.bindManagedContainerMethod(ownerId, (ImmutableBindManagedContainerMethod) method, listener);
        } else if (method instanceof RemoveTemplateMethod) {
            value.removeTemplateMethod(ownerId, (ImmutableRemoveTemplateMethod) method, listener);
        } else {
            value.handleTemplateMethod(ownerId, method, listener);
        }
    }
}
