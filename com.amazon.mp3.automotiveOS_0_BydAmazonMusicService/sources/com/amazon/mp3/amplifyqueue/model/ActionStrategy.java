package com.amazon.mp3.amplifyqueue.model;

import androidx.core.util.ObjectsCompat;

/* JADX INFO: loaded from: classes3.dex */
public final class ActionStrategy {
    private final CompositeActionStrategy compositeActionStrategy;
    private final SingleActionStrategy singleActionStrategy;
    private final ActionStrategyTypeEnum strategyType;

    public interface BuildStep {
    }

    public static class Builder implements StrategyTypeStep, BuildStep {
    }

    public final class CopyOfBuilder extends Builder {
    }

    public interface StrategyTypeStep {
    }

    public ActionStrategyTypeEnum getStrategyType() {
        return this.strategyType;
    }

    public SingleActionStrategy getSingleActionStrategy() {
        return this.singleActionStrategy;
    }

    public CompositeActionStrategy getCompositeActionStrategy() {
        return this.compositeActionStrategy;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || ActionStrategy.class != obj.getClass()) {
            return false;
        }
        ActionStrategy actionStrategy = (ActionStrategy) obj;
        return ObjectsCompat.equals(getStrategyType(), actionStrategy.getStrategyType()) && ObjectsCompat.equals(getSingleActionStrategy(), actionStrategy.getSingleActionStrategy()) && ObjectsCompat.equals(getCompositeActionStrategy(), actionStrategy.getCompositeActionStrategy());
    }

    public int hashCode() {
        StringBuilder sb = new StringBuilder();
        sb.append(getStrategyType());
        sb.append(getSingleActionStrategy());
        sb.append(getCompositeActionStrategy());
        return sb.toString().hashCode();
    }
}
