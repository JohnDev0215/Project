package Podcast.Touch.PTCTemplateInterface.v1_0;

import SOACacheInterface.v1_0.WriteCacheElement;
import SOACoreInterface.v1_0.Method;
import SOACoreInterface.v1_0.SOAObject;
import java.util.Arrays;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public class PTCWriteCacheElement extends WriteCacheElement {
    private static final int classNameHashCode = internalHashCodeCompute("Podcast.Touch.PTCTemplateInterface.v1_0.PTCWriteCacheElement");
    private Boolean didPlayTrailer;

    public static class Builder extends WriteCacheElement.Builder {
        protected Boolean didPlayTrailer;

        @Override // SOACacheInterface.v1_0.WriteCacheElement.Builder
        public PTCWriteCacheElement build() {
            PTCWriteCacheElement pTCWriteCacheElement = new PTCWriteCacheElement();
            populate(pTCWriteCacheElement);
            return pTCWriteCacheElement;
        }

        protected void populate(PTCWriteCacheElement pTCWriteCacheElement) {
            super.populate((WriteCacheElement) pTCWriteCacheElement);
            pTCWriteCacheElement.setDidPlayTrailer(this.didPlayTrailer);
        }

        public Builder withDidPlayTrailer(Boolean bool) {
            this.didPlayTrailer = bool;
            return this;
        }

        @Override // SOACacheInterface.v1_0.WriteCacheElement.Builder
        public Builder withOnInvalidated(List<Method> list) {
            super.withOnInvalidated(list);
            return this;
        }

        @Override // SOACacheInterface.v1_0.WriteCacheElement.Builder
        public /* bridge */ /* synthetic */ WriteCacheElement.Builder withOnInvalidated(List list) {
            return withOnInvalidated((List<Method>) list);
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
    @Override // SOACacheInterface.v1_0.WriteCacheElement, SOACoreInterface.v1_0.SOAObject, java.lang.Comparable
    @Deprecated
    public int compareTo(@Deprecated SOAObject sOAObject) {
        if (sOAObject == null) {
            return -1;
        }
        if (sOAObject == this) {
            return 0;
        }
        if (!(sOAObject instanceof PTCWriteCacheElement)) {
            return 1;
        }
        Boolean boolIsDidPlayTrailer = isDidPlayTrailer();
        Boolean boolIsDidPlayTrailer2 = ((PTCWriteCacheElement) sOAObject).isDidPlayTrailer();
        if (boolIsDidPlayTrailer != boolIsDidPlayTrailer2) {
            if (boolIsDidPlayTrailer == null) {
                return -1;
            }
            if (boolIsDidPlayTrailer2 == null) {
                return 1;
            }
            int iCompareTo = boolIsDidPlayTrailer.compareTo(boolIsDidPlayTrailer2);
            if (iCompareTo != 0) {
                return iCompareTo;
            }
        }
        return super.compareTo(sOAObject);
    }

    @Override // SOACacheInterface.v1_0.WriteCacheElement, SOACoreInterface.v1_0.SOAObject
    public boolean equals(Object obj) {
        if (obj instanceof PTCWriteCacheElement) {
            return super.equals(obj) && internalEqualityCheck(isDidPlayTrailer(), ((PTCWriteCacheElement) obj).isDidPlayTrailer());
        }
        return false;
    }

    @Override // SOACacheInterface.v1_0.WriteCacheElement, SOACoreInterface.v1_0.SOAObject
    public int hashCode() {
        return internalHashCodeCompute(Integer.valueOf(super.hashCode()), Integer.valueOf(classNameHashCode), isDidPlayTrailer());
    }

    public Boolean isDidPlayTrailer() {
        return this.didPlayTrailer;
    }

    public void setDidPlayTrailer(Boolean bool) {
        this.didPlayTrailer = bool;
    }
}
