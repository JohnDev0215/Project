package Podcast.Touch.PTCTemplateInterface.v1_0;

import SOACacheInterface.v1_0.InvalidateCacheMethod;
import SOACoreInterface.v1_0.Queue;
import SOACoreInterface.v1_0.SOAObject;
import java.util.Arrays;

/* JADX INFO: loaded from: classes.dex */
public class InvalidatePTCCacheMethod extends InvalidateCacheMethod {
    private static final int classNameHashCode = internalHashCodeCompute("Podcast.Touch.PTCTemplateInterface.v1_0.InvalidatePTCCacheMethod");

    public static class Builder extends InvalidateCacheMethod.Builder {
        public InvalidatePTCCacheMethod build() {
            InvalidatePTCCacheMethod invalidatePTCCacheMethod = new InvalidatePTCCacheMethod();
            populate(invalidatePTCCacheMethod);
            return invalidatePTCCacheMethod;
        }

        protected void populate(InvalidatePTCCacheMethod invalidatePTCCacheMethod) {
            super.populate((InvalidateCacheMethod) invalidatePTCCacheMethod);
        }

        @Override // SOACacheInterface.v1_0.InvalidateCacheMethod.Builder, SOACoreInterface.v1_0.Method.Builder
        public Builder withForced(Boolean bool) {
            super.withForced(bool);
            return this;
        }

        @Override // SOACacheInterface.v1_0.InvalidateCacheMethod.Builder, SOACoreInterface.v1_0.Method.Builder
        public Builder withQueue(Queue queue) {
            super.withQueue(queue);
            return this;
        }
    }

    public static Builder builder() {
        return new Builder();
    }

    private static int internalHashCodeCompute(Object... objArr) {
        return Arrays.hashCode(objArr);
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // SOACacheInterface.v1_0.InvalidateCacheMethod, SOACoreInterface.v1_0.Method, SOACoreInterface.v1_0.SOAObject, java.lang.Comparable
    @Deprecated
    public int compareTo(@Deprecated SOAObject sOAObject) {
        if (sOAObject == null) {
            return -1;
        }
        if (sOAObject == this) {
            return 0;
        }
        if (sOAObject instanceof InvalidatePTCCacheMethod) {
            return super.compareTo(sOAObject);
        }
        return 1;
    }

    @Override // SOACacheInterface.v1_0.InvalidateCacheMethod, SOACoreInterface.v1_0.Method, SOACoreInterface.v1_0.SOAObject
    public boolean equals(Object obj) {
        if (obj instanceof InvalidatePTCCacheMethod) {
            return super.equals(obj);
        }
        return false;
    }

    @Override // SOACacheInterface.v1_0.InvalidateCacheMethod, SOACoreInterface.v1_0.Method, SOACoreInterface.v1_0.SOAObject
    public int hashCode() {
        return internalHashCodeCompute(Integer.valueOf(super.hashCode()), Integer.valueOf(classNameHashCode));
    }
}
