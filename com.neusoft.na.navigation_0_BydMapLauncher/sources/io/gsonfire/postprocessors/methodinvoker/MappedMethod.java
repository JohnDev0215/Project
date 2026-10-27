package io.gsonfire.postprocessors.methodinvoker;

import io.gsonfire.annotations.ExposeMethodResult;
import java.lang.reflect.Method;

/* JADX INFO: loaded from: classes.dex */
public final class MappedMethod {
    private final ExposeMethodResult.ConflictResolutionStrategy conflictResolutionStrategy;
    private final Method method;
    private final String serializedName;

    public Method getMethod() {
        return this.method;
    }

    public String getSerializedName() {
        return this.serializedName;
    }

    public ExposeMethodResult.ConflictResolutionStrategy getConflictResolutionStrategy() {
        return this.conflictResolutionStrategy;
    }

    public MappedMethod(Method method, String str, ExposeMethodResult.ConflictResolutionStrategy conflictResolutionStrategy) {
        this.method = method;
        this.serializedName = str;
        this.conflictResolutionStrategy = conflictResolutionStrategy;
    }
}
