package Podcast.JumpBackInInterface.v1_0;

import SOACacheInterface.v1_0.WriteCacheElement;
import SOACoreInterface.v1_0.Method;
import SOACoreInterface.v1_0.SOAObject;
import java.util.Arrays;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public class JumpBackInWriteCacheElement extends WriteCacheElement {
    private static final int classNameHashCode = internalHashCodeCompute("Podcast.JumpBackInInterface.v1_0.JumpBackInWriteCacheElement");
    private List<JumpBackInCacheItemElement> elements;

    public static class Builder extends WriteCacheElement.Builder {
        protected List<JumpBackInCacheItemElement> elements;

        @Override // SOACacheInterface.v1_0.WriteCacheElement.Builder
        public JumpBackInWriteCacheElement build() {
            JumpBackInWriteCacheElement jumpBackInWriteCacheElement = new JumpBackInWriteCacheElement();
            populate(jumpBackInWriteCacheElement);
            return jumpBackInWriteCacheElement;
        }

        protected void populate(JumpBackInWriteCacheElement jumpBackInWriteCacheElement) {
            super.populate((WriteCacheElement) jumpBackInWriteCacheElement);
            jumpBackInWriteCacheElement.setElements(this.elements);
        }

        public Builder withElements(List<JumpBackInCacheItemElement> list) {
            this.elements = list;
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
        if (!(sOAObject instanceof JumpBackInWriteCacheElement)) {
            return 1;
        }
        List<JumpBackInCacheItemElement> elements = getElements();
        List<JumpBackInCacheItemElement> elements2 = ((JumpBackInWriteCacheElement) sOAObject).getElements();
        if (elements != elements2) {
            if (elements == null) {
                return -1;
            }
            if (elements2 == null) {
                return 1;
            }
            if (elements instanceof Comparable) {
                int iCompareTo = ((Comparable) elements).compareTo(elements2);
                if (iCompareTo != 0) {
                    return iCompareTo;
                }
            } else if (!elements.equals(elements2)) {
                int iHashCode = elements.hashCode();
                int iHashCode2 = elements2.hashCode();
                if (iHashCode < iHashCode2) {
                    return -1;
                }
                if (iHashCode > iHashCode2) {
                    return 1;
                }
            }
        }
        return super.compareTo(sOAObject);
    }

    @Override // SOACacheInterface.v1_0.WriteCacheElement, SOACoreInterface.v1_0.SOAObject
    public boolean equals(Object obj) {
        if (obj instanceof JumpBackInWriteCacheElement) {
            return super.equals(obj) && internalEqualityCheck(getElements(), ((JumpBackInWriteCacheElement) obj).getElements());
        }
        return false;
    }

    public List<JumpBackInCacheItemElement> getElements() {
        return this.elements;
    }

    @Override // SOACacheInterface.v1_0.WriteCacheElement, SOACoreInterface.v1_0.SOAObject
    public int hashCode() {
        return internalHashCodeCompute(Integer.valueOf(super.hashCode()), Integer.valueOf(classNameHashCode), getElements());
    }

    public void setElements(List<JumpBackInCacheItemElement> list) {
        this.elements = list;
    }
}
