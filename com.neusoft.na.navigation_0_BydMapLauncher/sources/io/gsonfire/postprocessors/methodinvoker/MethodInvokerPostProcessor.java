package io.gsonfire.postprocessors.methodinvoker;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import io.gsonfire.PostProcessor;
import io.gsonfire.annotations.ExposeMethodResult;
import io.gsonfire.gson.FireExclusionStrategy;
import io.gsonfire.gson.FireExclusionStrategyComposite;
import java.lang.reflect.InvocationTargetException;

/* JADX INFO: loaded from: classes.dex */
public final class MethodInvokerPostProcessor<T> implements PostProcessor<T> {
    private static MappedMethodInspector methodInspector = new MappedMethodInspector();
    private final FireExclusionStrategy serializationExclusionStrategy;

    @Override // io.gsonfire.PostProcessor
    public void postDeserialize(T t, JsonElement jsonElement, Gson gson) {
    }

    public MethodInvokerPostProcessor() {
        this(new FireExclusionStrategyComposite(new FireExclusionStrategy[0]));
    }

    public MethodInvokerPostProcessor(FireExclusionStrategy fireExclusionStrategy) {
        this.serializationExclusionStrategy = fireExclusionStrategy;
    }

    @Override // io.gsonfire.PostProcessor
    public void postSerialize(JsonElement jsonElement, T t, Gson gson) {
        if (jsonElement.isJsonObject()) {
            JsonObject asJsonObject = jsonElement.getAsJsonObject();
            for (MappedMethod mappedMethod : methodInspector.getAnnotatedMembers(t.getClass(), ExposeMethodResult.class)) {
                if (!this.serializationExclusionStrategy.shouldSkipMethod(mappedMethod)) {
                    try {
                        if (mappedMethod.getConflictResolutionStrategy() == ExposeMethodResult.ConflictResolutionStrategy.OVERWRITE || (mappedMethod.getConflictResolutionStrategy() == ExposeMethodResult.ConflictResolutionStrategy.SKIP && !asJsonObject.has(mappedMethod.getSerializedName()))) {
                            asJsonObject.add(mappedMethod.getSerializedName(), gson.toJsonTree(mappedMethod.getMethod().invoke(t, new Object[0])));
                        }
                    } catch (IllegalAccessException e) {
                        e.printStackTrace();
                    } catch (InvocationTargetException e2) {
                        e2.printStackTrace();
                    }
                }
            }
        }
    }
}
