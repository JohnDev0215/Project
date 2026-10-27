package com.amazon.mp3.amplifyqueue.model;

import androidx.core.util.ObjectsCompat;

/* JADX INFO: loaded from: classes3.dex */
public final class SingleActionStrategy {
    private final SingleActionStrategyActionTypeEnum actionType;
    private final Boolean booleanAction;
    private final UpsellAction upsellAction;

    public interface ActionTypeStep {
    }

    public interface BuildStep {
    }

    public static class Builder implements ActionTypeStep, BuildStep {
    }

    public final class CopyOfBuilder extends Builder {
    }

    public SingleActionStrategyActionTypeEnum getActionType() {
        return this.actionType;
    }

    public UpsellAction getUpsellAction() {
        return this.upsellAction;
    }

    public Boolean getBooleanAction() {
        return this.booleanAction;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || SingleActionStrategy.class != obj.getClass()) {
            return false;
        }
        SingleActionStrategy singleActionStrategy = (SingleActionStrategy) obj;
        return ObjectsCompat.equals(getActionType(), singleActionStrategy.getActionType()) && ObjectsCompat.equals(getUpsellAction(), singleActionStrategy.getUpsellAction()) && ObjectsCompat.equals(getBooleanAction(), singleActionStrategy.getBooleanAction());
    }

    public int hashCode() {
        StringBuilder sb = new StringBuilder();
        sb.append(getActionType());
        sb.append(getUpsellAction());
        sb.append(getBooleanAction());
        return sb.toString().hashCode();
    }
}
