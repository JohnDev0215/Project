package Podcast.FeatureGatingInterface.v1_0;

import SOACoreInterface.v1_0.Method;
import SOACoreInterface.v1_0.Queue;
import SOACoreInterface.v1_0.SOAObject;
import java.util.Arrays;
import java.util.Map;

/* JADX INFO: loaded from: classes.dex */
public class SetBooleanFeaturesMethod extends Method {
    private static final int classNameHashCode = internalHashCodeCompute("Podcast.FeatureGatingInterface.v1_0.SetBooleanFeaturesMethod");
    private Map<String, Boolean> features;

    public static class Builder extends Method.Builder {
        protected Map<String, Boolean> features;

        public SetBooleanFeaturesMethod build() {
            SetBooleanFeaturesMethod setBooleanFeaturesMethod = new SetBooleanFeaturesMethod();
            populate(setBooleanFeaturesMethod);
            return setBooleanFeaturesMethod;
        }

        protected void populate(SetBooleanFeaturesMethod setBooleanFeaturesMethod) {
            super.populate((Method) setBooleanFeaturesMethod);
            setBooleanFeaturesMethod.setFeatures(this.features);
        }

        public Builder withFeatures(Map<String, Boolean> map) {
            this.features = map;
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
        if (!(sOAObject instanceof SetBooleanFeaturesMethod)) {
            return 1;
        }
        Map<String, Boolean> features = getFeatures();
        Map<String, Boolean> features2 = ((SetBooleanFeaturesMethod) sOAObject).getFeatures();
        if (features != features2) {
            if (features == null) {
                return -1;
            }
            if (features2 == null) {
                return 1;
            }
            if (features instanceof Comparable) {
                int iCompareTo = ((Comparable) features).compareTo(features2);
                if (iCompareTo != 0) {
                    return iCompareTo;
                }
            } else if (!features.equals(features2)) {
                int iHashCode = features.hashCode();
                int iHashCode2 = features2.hashCode();
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

    @Override // SOACoreInterface.v1_0.Method, SOACoreInterface.v1_0.SOAObject
    public boolean equals(Object obj) {
        if (obj instanceof SetBooleanFeaturesMethod) {
            return super.equals(obj) && internalEqualityCheck(getFeatures(), ((SetBooleanFeaturesMethod) obj).getFeatures());
        }
        return false;
    }

    public Map<String, Boolean> getFeatures() {
        return this.features;
    }

    @Override // SOACoreInterface.v1_0.Method, SOACoreInterface.v1_0.SOAObject
    public int hashCode() {
        return internalHashCodeCompute(Integer.valueOf(super.hashCode()), Integer.valueOf(classNameHashCode), getFeatures());
    }

    public void setFeatures(Map<String, Boolean> map) {
        this.features = map;
    }
}
