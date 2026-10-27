package io.gsonfire.gson;

import io.gsonfire.postprocessors.methodinvoker.MappedMethod;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;

/* JADX INFO: loaded from: classes.dex */
public final class FireExclusionStrategyComposite implements FireExclusionStrategy {
    private final Collection<FireExclusionStrategy> strategies;

    public FireExclusionStrategyComposite(FireExclusionStrategy... fireExclusionStrategyArr) {
        this(Arrays.asList(fireExclusionStrategyArr));
    }

    public FireExclusionStrategyComposite(Collection<FireExclusionStrategy> collection) {
        this.strategies = new ArrayList(collection);
    }

    @Override // io.gsonfire.gson.FireExclusionStrategy
    public boolean shouldSkipMethod(MappedMethod mappedMethod) {
        Iterator<FireExclusionStrategy> it = this.strategies.iterator();
        while (it.hasNext()) {
            if (it.next().shouldSkipMethod(mappedMethod)) {
                return true;
            }
        }
        return false;
    }
}
