package com.amazon.mp3.amplifyqueue.model;

import androidx.core.util.ObjectsCompat;

/* JADX INFO: loaded from: classes3.dex */
public final class SkipLimitIOTAction {
    private final SingleActionStrategy noSkipsRemainingAction;
    private final SingleActionStrategy skipsRemainingActionStrategy;

    public interface BuildStep {
    }

    public static class Builder implements NoSkipsRemainingActionStep, SkipsRemainingActionStrategyStep, BuildStep {
    }

    public final class CopyOfBuilder extends Builder {
    }

    public interface NoSkipsRemainingActionStep {
    }

    public interface SkipsRemainingActionStrategyStep {
    }

    public SingleActionStrategy getNoSkipsRemainingAction() {
        return this.noSkipsRemainingAction;
    }

    public SingleActionStrategy getSkipsRemainingActionStrategy() {
        return this.skipsRemainingActionStrategy;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || SkipLimitIOTAction.class != obj.getClass()) {
            return false;
        }
        SkipLimitIOTAction skipLimitIOTAction = (SkipLimitIOTAction) obj;
        return ObjectsCompat.equals(getNoSkipsRemainingAction(), skipLimitIOTAction.getNoSkipsRemainingAction()) && ObjectsCompat.equals(getSkipsRemainingActionStrategy(), skipLimitIOTAction.getSkipsRemainingActionStrategy());
    }

    public int hashCode() {
        StringBuilder sb = new StringBuilder();
        sb.append(getNoSkipsRemainingAction());
        sb.append(getSkipsRemainingActionStrategy());
        return sb.toString().hashCode();
    }
}
