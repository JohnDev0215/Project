package SOACoreInterface.v1_0;

import SOACoreInterface.v1_0.SOAObject;
import java.util.Arrays;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public class Response extends SOAObject {
    private static final int classNameHashCode = internalHashCodeCompute("SOACoreInterface.v1_0.Response");
    private List<Method> methods;

    public static class Builder extends SOAObject.Builder {
        protected List<Method> methods;

        public Response build() {
            Response response = new Response();
            populate(response);
            return response;
        }

        protected void populate(Response response) {
            super.populate((SOAObject) response);
            response.setMethods(this.methods);
        }

        public Builder withMethods(List<Method> list) {
            this.methods = list;
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
        if (!(sOAObject instanceof Response)) {
            return 1;
        }
        List<Method> methods = getMethods();
        List<Method> methods2 = ((Response) sOAObject).getMethods();
        if (methods != methods2) {
            if (methods == null) {
                return -1;
            }
            if (methods2 == null) {
                return 1;
            }
            if (methods instanceof Comparable) {
                int iCompareTo = ((Comparable) methods).compareTo(methods2);
                if (iCompareTo != 0) {
                    return iCompareTo;
                }
            } else if (!methods.equals(methods2)) {
                int iHashCode = methods.hashCode();
                int iHashCode2 = methods2.hashCode();
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

    @Override // SOACoreInterface.v1_0.SOAObject
    public boolean equals(Object obj) {
        if (obj instanceof Response) {
            return super.equals(obj) && internalEqualityCheck(getMethods(), ((Response) obj).getMethods());
        }
        return false;
    }

    public List<Method> getMethods() {
        return this.methods;
    }

    @Override // SOACoreInterface.v1_0.SOAObject
    public int hashCode() {
        return internalHashCodeCompute(Integer.valueOf(super.hashCode()), Integer.valueOf(classNameHashCode), getMethods());
    }

    public void setMethods(List<Method> list) {
        this.methods = list;
    }
}
