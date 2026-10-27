package SOACoreInterface.v1_0;

import SOACoreInterface.v1_0.SOAObject;
import java.util.Arrays;

/* JADX INFO: loaded from: classes.dex */
public class UserIdentity extends SOAObject {
    private static final int classNameHashCode = internalHashCodeCompute("SOACoreInterface.v1_0.UserIdentity");
    private String authentication;
    private String customerId;

    public static class Builder extends SOAObject.Builder {
        protected String authentication;
        protected String customerId;

        public UserIdentity build() {
            UserIdentity userIdentity = new UserIdentity();
            populate(userIdentity);
            return userIdentity;
        }

        protected void populate(UserIdentity userIdentity) {
            super.populate((SOAObject) userIdentity);
            userIdentity.setCustomerId(this.customerId);
            userIdentity.setAuthentication(this.authentication);
        }

        public Builder withAuthentication(String str) {
            this.authentication = str;
            return this;
        }

        public Builder withCustomerId(String str) {
            this.customerId = str;
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
        if (!(sOAObject instanceof UserIdentity)) {
            return 1;
        }
        UserIdentity userIdentity = (UserIdentity) sOAObject;
        String customerId = getCustomerId();
        String customerId2 = userIdentity.getCustomerId();
        if (customerId != customerId2) {
            if (customerId == null) {
                return -1;
            }
            if (customerId2 == null) {
                return 1;
            }
            int iCompareTo = customerId.compareTo(customerId2);
            if (iCompareTo != 0) {
                return iCompareTo;
            }
        }
        String authentication = getAuthentication();
        String authentication2 = userIdentity.getAuthentication();
        if (authentication != authentication2) {
            if (authentication == null) {
                return -1;
            }
            if (authentication2 == null) {
                return 1;
            }
            int iCompareTo2 = authentication.compareTo(authentication2);
            if (iCompareTo2 != 0) {
                return iCompareTo2;
            }
        }
        return super.compareTo(sOAObject);
    }

    @Override // SOACoreInterface.v1_0.SOAObject
    public boolean equals(Object obj) {
        if (!(obj instanceof UserIdentity)) {
            return false;
        }
        UserIdentity userIdentity = (UserIdentity) obj;
        return super.equals(obj) && internalEqualityCheck(getCustomerId(), userIdentity.getCustomerId()) && internalEqualityCheck(getAuthentication(), userIdentity.getAuthentication());
    }

    public String getAuthentication() {
        return this.authentication;
    }

    public String getCustomerId() {
        return this.customerId;
    }

    @Override // SOACoreInterface.v1_0.SOAObject
    public int hashCode() {
        return internalHashCodeCompute(Integer.valueOf(super.hashCode()), Integer.valueOf(classNameHashCode), getCustomerId(), getAuthentication());
    }

    public void setAuthentication(String str) {
        this.authentication = str;
    }

    public void setCustomerId(String str) {
        this.customerId = str;
    }
}
