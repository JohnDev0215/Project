package io.gsonfire.gson;

import io.gsonfire.postprocessors.methodinvoker.MappedMethod;

/* JADX INFO: loaded from: classes.dex */
public interface FireExclusionStrategy {
    boolean shouldSkipMethod(MappedMethod mappedMethod);
}
