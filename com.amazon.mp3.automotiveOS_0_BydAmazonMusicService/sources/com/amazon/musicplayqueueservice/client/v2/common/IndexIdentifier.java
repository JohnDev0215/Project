package com.amazon.musicplayqueueservice.client.v2.common;

import com.amazon.musicplayqueueservice.client.v2.common.AbstractIdentifier;
import java.util.Arrays;

/* JADX INFO: loaded from: classes5.dex */
public class IndexIdentifier extends AbstractIdentifier {
    private static final int classNameHashCode = internalHashCodeCompute("com.amazon.musicplayqueueservice.client.v2.common.IndexIdentifier");
    private int index;

    public static class Builder extends AbstractIdentifier.Builder {
        protected int index;

        public IndexIdentifier build() {
            IndexIdentifier indexIdentifier = new IndexIdentifier();
            populate(indexIdentifier);
            return indexIdentifier;
        }

        protected void populate(IndexIdentifier indexIdentifier) {
            super.populate((AbstractIdentifier) indexIdentifier);
            indexIdentifier.setIndex(this.index);
        }

        public Builder withIndex(int i) {
            this.index = i;
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
    @Override // com.amazon.musicplayqueueservice.client.v2.common.AbstractIdentifier, java.lang.Comparable
    @Deprecated
    public int compareTo(@Deprecated AbstractIdentifier abstractIdentifier) {
        if (abstractIdentifier == null) {
            return -1;
        }
        if (abstractIdentifier == this) {
            return 0;
        }
        if (!(abstractIdentifier instanceof IndexIdentifier)) {
            return 1;
        }
        IndexIdentifier indexIdentifier = (IndexIdentifier) abstractIdentifier;
        if (getIndex() < indexIdentifier.getIndex()) {
            return -1;
        }
        if (getIndex() > indexIdentifier.getIndex()) {
            return 1;
        }
        return super.compareTo(abstractIdentifier);
    }

    @Override // com.amazon.musicplayqueueservice.client.v2.common.AbstractIdentifier
    public boolean equals(Object obj) {
        if (obj instanceof IndexIdentifier) {
            return super.equals(obj) && internalEqualityCheck(Integer.valueOf(getIndex()), Integer.valueOf(((IndexIdentifier) obj).getIndex()));
        }
        return false;
    }

    public int getIndex() {
        return this.index;
    }

    @Override // com.amazon.musicplayqueueservice.client.v2.common.AbstractIdentifier
    public int hashCode() {
        return internalHashCodeCompute(Integer.valueOf(super.hashCode()), Integer.valueOf(classNameHashCode), Integer.valueOf(getIndex()));
    }

    public void setIndex(int i) {
        this.index = i;
    }
}
