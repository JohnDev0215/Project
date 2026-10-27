package com.amazon.musicplayqueueservice.client.v2.track;

import com.amazon.musicplayqueueservice.client.v2.track.AbstractActionStrategy;
import java.util.Arrays;

/* JADX INFO: loaded from: classes5.dex */
public class BooleanActionStrategy extends AbstractActionStrategy {
    private static final int classNameHashCode = internalHashCodeCompute("com.amazon.musicplayqueueservice.client.v2.track.BooleanActionStrategy");
    private boolean actionAvailable;

    public static class Builder extends AbstractActionStrategy.Builder {
        protected boolean actionAvailable;

        public BooleanActionStrategy build() {
            BooleanActionStrategy booleanActionStrategy = new BooleanActionStrategy();
            populate(booleanActionStrategy);
            return booleanActionStrategy;
        }

        protected void populate(BooleanActionStrategy booleanActionStrategy) {
            super.populate((AbstractActionStrategy) booleanActionStrategy);
            booleanActionStrategy.setActionAvailable(this.actionAvailable);
        }

        public Builder withActionAvailable(boolean z) {
            this.actionAvailable = z;
            return this;
        }
    }

    public static Builder builder() {
        return new Builder();
    }

    private static boolean internalEqualityCheck(Object obj, Object obj2) {
        return obj == obj2 || (obj != null && obj.equals(obj2));
    }

    private static int internalHashCodeCompute(Object... objArr) {
        return Arrays.hashCode(objArr);
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.amazon.musicplayqueueservice.client.v2.track.AbstractActionStrategy, java.lang.Comparable
    @Deprecated
    public int compareTo(@Deprecated AbstractActionStrategy abstractActionStrategy) {
        if (abstractActionStrategy == null) {
            return -1;
        }
        if (abstractActionStrategy == this) {
            return 0;
        }
        if (!(abstractActionStrategy instanceof BooleanActionStrategy)) {
            return 1;
        }
        BooleanActionStrategy booleanActionStrategy = (BooleanActionStrategy) abstractActionStrategy;
        if (!isActionAvailable() && booleanActionStrategy.isActionAvailable()) {
            return -1;
        }
        if (!isActionAvailable() || booleanActionStrategy.isActionAvailable()) {
            return super.compareTo(abstractActionStrategy);
        }
        return 1;
    }

    @Override // com.amazon.musicplayqueueservice.client.v2.track.AbstractActionStrategy
    public boolean equals(Object obj) {
        if (obj instanceof BooleanActionStrategy) {
            return super.equals(obj) && internalEqualityCheck(Boolean.valueOf(isActionAvailable()), Boolean.valueOf(((BooleanActionStrategy) obj).isActionAvailable()));
        }
        return false;
    }

    @Override // com.amazon.musicplayqueueservice.client.v2.track.AbstractActionStrategy
    public int hashCode() {
        return internalHashCodeCompute(Integer.valueOf(super.hashCode()), Integer.valueOf(classNameHashCode), Boolean.valueOf(isActionAvailable()));
    }

    public boolean isActionAvailable() {
        return this.actionAvailable;
    }

    public void setActionAvailable(boolean z) {
        this.actionAvailable = z;
    }
}
