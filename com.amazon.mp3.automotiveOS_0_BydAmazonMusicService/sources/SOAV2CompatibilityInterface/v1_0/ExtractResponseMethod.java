package SOAV2CompatibilityInterface.v1_0;

import SOACoreInterface.v1_0.Method;
import SOACoreInterface.v1_0.Queue;
import SOACoreInterface.v1_0.SOAObject;
import java.util.Arrays;

/* JADX INFO: loaded from: classes.dex */
public class ExtractResponseMethod extends Method {
    private static final int classNameHashCode = internalHashCodeCompute("SOAV2CompatibilityInterface.v1_0.ExtractResponseMethod");
    private String content;

    public static class Builder extends Method.Builder {
        protected String content;

        public ExtractResponseMethod build() {
            ExtractResponseMethod extractResponseMethod = new ExtractResponseMethod();
            populate(extractResponseMethod);
            return extractResponseMethod;
        }

        protected void populate(ExtractResponseMethod extractResponseMethod) {
            super.populate((Method) extractResponseMethod);
            extractResponseMethod.setContent(this.content);
        }

        public Builder withContent(String str) {
            this.content = str;
            return this;
        }

        @Override // SOACoreInterface.v1_0.Method.Builder
        public Builder withForced(Boolean bool) {
            super.withForced(bool);
            return this;
        }

        @Override // SOACoreInterface.v1_0.Method.Builder
        public Builder withQueue(Queue queue) {
            super.withQueue(queue);
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
    @Override // SOACoreInterface.v1_0.Method, SOACoreInterface.v1_0.SOAObject, java.lang.Comparable
    @Deprecated
    public int compareTo(@Deprecated SOAObject sOAObject) {
        if (sOAObject == null) {
            return -1;
        }
        if (sOAObject == this) {
            return 0;
        }
        if (!(sOAObject instanceof ExtractResponseMethod)) {
            return 1;
        }
        String content = getContent();
        String content2 = ((ExtractResponseMethod) sOAObject).getContent();
        if (content != content2) {
            if (content == null) {
                return -1;
            }
            if (content2 == null) {
                return 1;
            }
            int iCompareTo = content.compareTo(content2);
            if (iCompareTo != 0) {
                return iCompareTo;
            }
        }
        return super.compareTo(sOAObject);
    }

    @Override // SOACoreInterface.v1_0.Method, SOACoreInterface.v1_0.SOAObject
    public boolean equals(Object obj) {
        if (obj instanceof ExtractResponseMethod) {
            return super.equals(obj) && internalEqualityCheck(getContent(), ((ExtractResponseMethod) obj).getContent());
        }
        return false;
    }

    public String getContent() {
        return this.content;
    }

    @Override // SOACoreInterface.v1_0.Method, SOACoreInterface.v1_0.SOAObject
    public int hashCode() {
        return internalHashCodeCompute(Integer.valueOf(super.hashCode()), Integer.valueOf(classNameHashCode), getContent());
    }

    public void setContent(String str) {
        this.content = str;
    }
}
