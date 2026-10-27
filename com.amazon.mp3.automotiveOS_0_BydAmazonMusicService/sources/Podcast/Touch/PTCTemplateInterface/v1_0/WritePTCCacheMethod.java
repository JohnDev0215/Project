package Podcast.Touch.PTCTemplateInterface.v1_0;

import SOACacheInterface.v1_0.WriteCacheElement;
import SOACacheInterface.v1_0.WriteCacheMethod;
import SOACoreInterface.v1_0.Queue;
import SOACoreInterface.v1_0.SOAObject;
import java.util.Arrays;

/* JADX INFO: loaded from: classes.dex */
public class WritePTCCacheMethod extends WriteCacheMethod {
    private static final int classNameHashCode = internalHashCodeCompute("Podcast.Touch.PTCTemplateInterface.v1_0.WritePTCCacheMethod");

    public static class Builder extends WriteCacheMethod.Builder {
        @Override // SOACacheInterface.v1_0.WriteCacheMethod.Builder
        public WritePTCCacheMethod build() {
            WritePTCCacheMethod writePTCCacheMethod = new WritePTCCacheMethod();
            populate(writePTCCacheMethod);
            return writePTCCacheMethod;
        }

        protected void populate(WritePTCCacheMethod writePTCCacheMethod) {
            super.populate((WriteCacheMethod) writePTCCacheMethod);
        }

        @Override // SOACacheInterface.v1_0.WriteCacheMethod.Builder
        public Builder withElement(WriteCacheElement writeCacheElement) {
            super.withElement(writeCacheElement);
            return this;
        }

        @Override // SOACacheInterface.v1_0.WriteCacheMethod.Builder, SOACoreInterface.v1_0.Method.Builder
        public Builder withForced(Boolean bool) {
            super.withForced(bool);
            return this;
        }

        @Override // SOACacheInterface.v1_0.WriteCacheMethod.Builder, SOACoreInterface.v1_0.Method.Builder
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
    @Override // SOACacheInterface.v1_0.WriteCacheMethod, SOACoreInterface.v1_0.Method, SOACoreInterface.v1_0.SOAObject, java.lang.Comparable
    @Deprecated
    public int compareTo(@Deprecated SOAObject sOAObject) {
        if (sOAObject == null) {
            return -1;
        }
        if (sOAObject == this) {
            return 0;
        }
        if (sOAObject instanceof WritePTCCacheMethod) {
            return super.compareTo(sOAObject);
        }
        return 1;
    }

    @Override // SOACacheInterface.v1_0.WriteCacheMethod, SOACoreInterface.v1_0.Method, SOACoreInterface.v1_0.SOAObject
    public boolean equals(Object obj) {
        if (obj instanceof WritePTCCacheMethod) {
            return super.equals(obj);
        }
        return false;
    }

    @Override // SOACacheInterface.v1_0.WriteCacheMethod, SOACoreInterface.v1_0.Method, SOACoreInterface.v1_0.SOAObject
    public int hashCode() {
        return internalHashCodeCompute(Integer.valueOf(super.hashCode()), Integer.valueOf(classNameHashCode));
    }
}
