package com.amplifyframework.core.category;

import androidx.annotation.NonNull;
import com.amplifyframework.core.InitializationResult;
import com.amplifyframework.core.InitializationStatus;
import com.amplifyframework.util.Immutable;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

/* JADX INFO: loaded from: classes5.dex */
public final class CategoryInitializationResult {
    private final InitializationStatus initializationStatus;
    private final Map<String, InitializationResult> pluginInitializationResults;

    interface PluginCriteria<T> {
        boolean appliesTo(T t);
    }

    private CategoryInitializationResult(@NonNull InitializationStatus initializationStatus, @NonNull Map<String, InitializationResult> map) {
        this.initializationStatus = initializationStatus;
        this.pluginInitializationResults = map;
    }

    @NonNull
    public static CategoryInitializationResult failure() {
        return new CategoryInitializationResult(InitializationStatus.FAILED, Collections.emptyMap());
    }

    @NonNull
    public static CategoryInitializationResult with(@NonNull Map<String, InitializationResult> map) {
        InitializationStatus initializationStatus;
        Objects.requireNonNull(map);
        if (anyFailed(map)) {
            initializationStatus = InitializationStatus.FAILED;
        } else {
            initializationStatus = InitializationStatus.SUCCEEDED;
        }
        return new CategoryInitializationResult(initializationStatus, map);
    }

    private static boolean anyFailed(@NonNull Map<String, InitializationResult> map) {
        Iterator<InitializationResult> it = map.values().iterator();
        while (it.hasNext()) {
            if (it.next().isFailure()) {
                return true;
            }
        }
        return false;
    }

    @NonNull
    public InitializationStatus getInitializationStatus() {
        return this.initializationStatus;
    }

    public boolean isSuccess() {
        return InitializationStatus.SUCCEEDED.equals(this.initializationStatus);
    }

    public boolean isFailure() {
        return InitializationStatus.FAILED.equals(this.initializationStatus);
    }

    @NonNull
    public Map<String, Throwable> getPluginInitializationFailures() {
        HashMap map = new HashMap();
        for (Map.Entry<String, InitializationResult> entry : this.pluginInitializationResults.entrySet()) {
            Throwable failure = entry.getValue().getFailure();
            String key = entry.getKey();
            if (failure != null) {
                map.put(key, failure);
            }
        }
        return Immutable.of(map);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ boolean lambda$getFailedPlugins$0(Map.Entry entry) {
        return ((InitializationResult) entry.getValue()).isFailure();
    }

    @NonNull
    public Set<String> getFailedPlugins() {
        return filterPluginResults(new PluginCriteria() { // from class: com.amplifyframework.core.category.CategoryInitializationResult$$ExternalSyntheticLambda0
            @Override // com.amplifyframework.core.category.CategoryInitializationResult.PluginCriteria
            public final boolean appliesTo(Object obj) {
                return CategoryInitializationResult.lambda$getFailedPlugins$0((Map.Entry) obj);
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ boolean lambda$getSuccessfulPlugins$1(Map.Entry entry) {
        return ((InitializationResult) entry.getValue()).isSuccess();
    }

    @NonNull
    public Set<String> getSuccessfulPlugins() {
        return filterPluginResults(new PluginCriteria() { // from class: com.amplifyframework.core.category.CategoryInitializationResult$$ExternalSyntheticLambda1
            @Override // com.amplifyframework.core.category.CategoryInitializationResult.PluginCriteria
            public final boolean appliesTo(Object obj) {
                return CategoryInitializationResult.lambda$getSuccessfulPlugins$1((Map.Entry) obj);
            }
        });
    }

    private Set<String> filterPluginResults(@NonNull PluginCriteria<Map.Entry<String, InitializationResult>> pluginCriteria) {
        HashSet hashSet = new HashSet();
        for (Map.Entry<String, InitializationResult> entry : this.pluginInitializationResults.entrySet()) {
            if (pluginCriteria.appliesTo(entry)) {
                hashSet.add(entry.getKey());
            }
        }
        return hashSet;
    }
}
