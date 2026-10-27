package com.amazon.mp3.amplifyqueue.model;

import androidx.core.util.ObjectsCompat;

/* JADX INFO: loaded from: classes3.dex */
public final class QueueEntityCapability {
    private final ActionStrategy displayable;
    private final SingleActionStrategy loopOne;
    private final ActionStrategy next;
    private final ActionStrategy previous;
    private final ActionStrategy rate;
    private final ActionStrategy scrubBackward;
    private final ActionStrategy scrubForward;

    public interface BuildStep {
    }

    public static class Builder implements BuildStep {
    }

    public final class CopyOfBuilder extends Builder {
    }

    public ActionStrategy getNext() {
        return this.next;
    }

    public ActionStrategy getPrevious() {
        return this.previous;
    }

    public ActionStrategy getScrubForward() {
        return this.scrubForward;
    }

    public ActionStrategy getScrubBackward() {
        return this.scrubBackward;
    }

    public ActionStrategy getRate() {
        return this.rate;
    }

    public ActionStrategy getDisplayable() {
        return this.displayable;
    }

    public SingleActionStrategy getLoopOne() {
        return this.loopOne;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || QueueEntityCapability.class != obj.getClass()) {
            return false;
        }
        QueueEntityCapability queueEntityCapability = (QueueEntityCapability) obj;
        return ObjectsCompat.equals(getNext(), queueEntityCapability.getNext()) && ObjectsCompat.equals(getPrevious(), queueEntityCapability.getPrevious()) && ObjectsCompat.equals(getScrubForward(), queueEntityCapability.getScrubForward()) && ObjectsCompat.equals(getScrubBackward(), queueEntityCapability.getScrubBackward()) && ObjectsCompat.equals(getRate(), queueEntityCapability.getRate()) && ObjectsCompat.equals(getDisplayable(), queueEntityCapability.getDisplayable()) && ObjectsCompat.equals(getLoopOne(), queueEntityCapability.getLoopOne());
    }

    public int hashCode() {
        StringBuilder sb = new StringBuilder();
        sb.append(getNext());
        sb.append(getPrevious());
        sb.append(getScrubForward());
        sb.append(getScrubBackward());
        sb.append(getRate());
        sb.append(getDisplayable());
        sb.append(getLoopOne());
        return sb.toString().hashCode();
    }
}
