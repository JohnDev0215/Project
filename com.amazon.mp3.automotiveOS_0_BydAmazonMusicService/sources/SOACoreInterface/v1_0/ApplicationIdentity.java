package SOACoreInterface.v1_0;

import SOACoreInterface.v1_0.SOAObject;
import java.util.Arrays;

/* JADX INFO: loaded from: classes.dex */
public class ApplicationIdentity extends SOAObject {
    private static final int classNameHashCode = internalHashCodeCompute("SOACoreInterface.v1_0.ApplicationIdentity");
    private String version;

    public static class Builder extends SOAObject.Builder {
        protected String version;

        public ApplicationIdentity build() {
            ApplicationIdentity applicationIdentity = new ApplicationIdentity();
            populate(applicationIdentity);
            return applicationIdentity;
        }

        protected void populate(ApplicationIdentity applicationIdentity) {
            super.populate((SOAObject) applicationIdentity);
            applicationIdentity.setVersion(this.version);
        }

        public Builder withVersion(String str) {
            this.version = str;
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
    @Override // SOACoreInterface.v1_0.SOAObject, java.lang.Comparable
    @Deprecated
    public int compareTo(@Deprecated SOAObject sOAObject) {
        if (sOAObject == null) {
            return -1;
        }
        if (sOAObject == this) {
            return 0;
        }
        if (!(sOAObject instanceof ApplicationIdentity)) {
            return 1;
        }
        String version = getVersion();
        String version2 = ((ApplicationIdentity) sOAObject).getVersion();
        if (version != version2) {
            if (version == null) {
                return -1;
            }
            if (version2 == null) {
                return 1;
            }
            int iCompareTo = version.compareTo(version2);
            if (iCompareTo != 0) {
                return iCompareTo;
            }
        }
        return super.compareTo(sOAObject);
    }

    @Override // SOACoreInterface.v1_0.SOAObject
    public boolean equals(Object obj) {
        if (obj instanceof ApplicationIdentity) {
            return super.equals(obj) && internalEqualityCheck(getVersion(), ((ApplicationIdentity) obj).getVersion());
        }
        return false;
    }

    public String getVersion() {
        return this.version;
    }

    @Override // SOACoreInterface.v1_0.SOAObject
    public int hashCode() {
        return internalHashCodeCompute(Integer.valueOf(super.hashCode()), Integer.valueOf(classNameHashCode), getVersion());
    }

    public void setVersion(String str) {
        this.version = str;
    }
}
