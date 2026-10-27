package com.amazon.mp3.amplifyqueue.model;

import androidx.core.util.ObjectsCompat;

/* JADX INFO: loaded from: classes3.dex */
public final class CompositeActionStrategy {
    private final CompositeActionStrategyActionTypeEnum actionType;
    private final SkipLimitIOTAction skipLimitIOTAction;

    public interface ActionTypeStep {
    }

    public interface BuildStep {
    }

    public static class Builder implements ActionTypeStep, BuildStep {
    }

    public final class CopyOfBuilder extends Builder {
    }

    public CompositeActionStrategyActionTypeEnum getActionType() {
        return this.actionType;
    }

    public SkipLimitIOTAction getSkipLimitIotAction() {
        return this.skipLimitIOTAction;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || CompositeActionStrategy.class != obj.getClass()) {
            return false;
        }
        CompositeActionStrategy compositeActionStrategy = (CompositeActionStrategy) obj;
        return ObjectsCompat.equals(getActionType(), compositeActionStrategy.getActionType()) && ObjectsCompat.equals(getSkipLimitIotAction(), compositeActionStrategy.getSkipLimitIotAction());
    }

    public int hashCode() {
        StringBuilder sb = new StringBuilder();
        sb.append(getActionType());
        sb.append(getSkipLimitIotAction());
        return sb.toString().hashCode();
    }
}
