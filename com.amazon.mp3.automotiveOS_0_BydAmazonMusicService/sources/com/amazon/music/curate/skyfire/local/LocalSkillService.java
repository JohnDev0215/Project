package com.amazon.music.curate.skyfire.local;

import CoreInterface.v1_0.Method;
import com.amazon.music.curate.Handlers;
import com.amazon.music.curate.skyfire.core.MethodEngine;
import com.amazon.music.curate.skyfire.dagger.DaggerCurateSkyfireApplicationComponent;
import com.amazon.music.skyfire.platform.MethodCallback;
import com.amazon.music.skyfire.platform.OnMethodExecutedListener;
import java.util.Collections;
import java.util.HashSet;

/* JADX INFO: loaded from: classes4.dex */
public final class LocalSkillService implements MethodCallback<Method> {
    private static final String OWNER_ID = "LocalSkillService";
    private final MethodEngine engine;

    @Override // com.amazon.music.skyfire.platform.MethodCallback
    public /* bridge */ /* synthetic */ void executeMethod(final Method method, final String ownerId, final OnMethodExecutedListener<Method> listener) {
        executeMethod2(method, ownerId, (OnMethodExecutedListener) listener);
    }

    public LocalSkillService(final MethodEngine engine) {
        this.engine = engine;
        HashSet hashSet = new HashSet();
        hashSet.add(InvokeLocalSkillMethod.class.getCanonicalName());
        engine.registerOwner(OWNER_ID);
        engine.registerMethods(hashSet, Collections.emptySet(), this);
    }

    /* JADX INFO: renamed from: executeMethod, reason: avoid collision after fix types in other method */
    public void executeMethod2(final Method method, final String ownerId, final OnMethodExecutedListener listener) {
        Handlers.INSTANCE.getBackground().post(new Runnable() { // from class: com.amazon.music.curate.skyfire.local.LocalSkillService.1
            @Override // java.lang.Runnable
            public void run() {
                LocalSkillService.this.executeInternal(method, ownerId, listener);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void executeInternal(final Method method, final String ownerId, final OnMethodExecutedListener listener) {
        if (method instanceof InvokeLocalSkillMethod) {
            invokeLocalSkillMethod((InvokeLocalSkillMethod) method, ownerId);
        }
        listener.onMethodExecuted(method);
    }

    private void invokeLocalSkillMethod(final InvokeLocalSkillMethod method, final String ownerId) {
        LocalSkillContext skillContext = method.getSkillContext();
        this.engine.handleMethods(ownerId, DaggerCurateSkyfireApplicationComponent.create().skillsByClass().get(skillContext.getClass()).invoke(skillContext));
    }
}
