package com.amazon.music.explore.skyfire;

import CoreInterface.v1_0.Method;
import InteractionInterface.v1_0.ImmutableSetHttpSkillUrlMethod;
import InteractionInterface.v1_0.SetHttpSkillUrlMethod;
import com.amazon.music.skyfire.SkyFireApplication;
import com.amazon.music.skyfire.platform.MethodCallback;
import com.amazon.music.skyfire.platform.OnMethodExecutedListener;
import java.util.Collections;
import java.util.HashSet;

/* JADX INFO: loaded from: classes4.dex */
public class ExploreMethodsRegistry implements MethodCallback<Method> {
    private final ExploreHttpSkillInfo exploreHttpSkillInfo;
    private final SkyFireApplication skyFireApplication;

    @Override // com.amazon.music.skyfire.platform.MethodCallback
    public /* bridge */ /* synthetic */ void executeMethod(Method method, String str, OnMethodExecutedListener<Method> onMethodExecutedListener) {
        executeMethod2(method, str, (OnMethodExecutedListener) onMethodExecutedListener);
    }

    public ExploreMethodsRegistry(SkyFireApplication skyFireApplication, ExploreHttpSkillInfo exploreHttpSkillInfo) {
        this.skyFireApplication = skyFireApplication;
        this.exploreHttpSkillInfo = exploreHttpSkillInfo;
    }

    public void registerMethods() {
        HashSet hashSet = new HashSet();
        hashSet.add(ImmutableSetHttpSkillUrlMethod.class.getCanonicalName());
        this.skyFireApplication.registerMethods(Collections.emptySet(), hashSet, this);
    }

    /* JADX INFO: renamed from: executeMethod, reason: avoid collision after fix types in other method */
    public void executeMethod2(Method method, String str, OnMethodExecutedListener onMethodExecutedListener) {
        if (method instanceof SetHttpSkillUrlMethod) {
            this.exploreHttpSkillInfo.handleSetHttpSkillUrlMethod((SetHttpSkillUrlMethod) method);
            onMethodExecutedListener.onMethodExecuted(method);
        }
    }
}
