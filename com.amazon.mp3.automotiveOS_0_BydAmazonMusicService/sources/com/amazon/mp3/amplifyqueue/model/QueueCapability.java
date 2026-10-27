package com.amazon.mp3.amplifyqueue.model;

import androidx.core.util.ObjectsCompat;

/* JADX INFO: loaded from: classes3.dex */
public final class QueueCapability {
    private final ActionStrategy blockQueueMutations;
    private final ActionStrategy djMode;
    private final ActionStrategy hideUpcomingEntities;
    private final ActionStrategy loopAll;
    private final ActionStrategy shuffle;

    public interface BlockQueueMutationsStep {
    }

    public interface BuildStep {
    }

    public static class Builder implements HideUpcomingEntitiesStep, BlockQueueMutationsStep, ShuffleStep, LoopAllStep, BuildStep {
    }

    public final class CopyOfBuilder extends Builder {
    }

    public interface HideUpcomingEntitiesStep {
    }

    public interface LoopAllStep {
    }

    public interface ShuffleStep {
    }

    public ActionStrategy getHideUpcomingEntities() {
        return this.hideUpcomingEntities;
    }

    public ActionStrategy getBlockQueueMutations() {
        return this.blockQueueMutations;
    }

    public ActionStrategy getShuffle() {
        return this.shuffle;
    }

    public ActionStrategy getLoopAll() {
        return this.loopAll;
    }

    public ActionStrategy getDjMode() {
        return this.djMode;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || QueueCapability.class != obj.getClass()) {
            return false;
        }
        QueueCapability queueCapability = (QueueCapability) obj;
        return ObjectsCompat.equals(getHideUpcomingEntities(), queueCapability.getHideUpcomingEntities()) && ObjectsCompat.equals(getBlockQueueMutations(), queueCapability.getBlockQueueMutations()) && ObjectsCompat.equals(getShuffle(), queueCapability.getShuffle()) && ObjectsCompat.equals(getLoopAll(), queueCapability.getLoopAll()) && ObjectsCompat.equals(getDjMode(), queueCapability.getDjMode());
    }

    public int hashCode() {
        StringBuilder sb = new StringBuilder();
        sb.append(getHideUpcomingEntities());
        sb.append(getBlockQueueMutations());
        sb.append(getShuffle());
        sb.append(getLoopAll());
        sb.append(getDjMode());
        return sb.toString().hashCode();
    }
}
