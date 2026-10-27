package Podcast.Touch.PTCTemplateInterface.v1_0;

import SOACoreInterface.v1_0.Method;
import SOACoreInterface.v1_0.Queue;
import SOACoreInterface.v1_0.SOAObject;
import java.util.Arrays;

/* JADX INFO: loaded from: classes.dex */
public class SetPTCPreferencesMethod extends Method {
    private static final int classNameHashCode = internalHashCodeCompute("Podcast.Touch.PTCTemplateInterface.v1_0.SetPTCPreferencesMethod");
    private Boolean showPTCIngress;

    public static class Builder extends Method.Builder {
        protected Boolean showPTCIngress;

        public SetPTCPreferencesMethod build() {
            SetPTCPreferencesMethod setPTCPreferencesMethod = new SetPTCPreferencesMethod();
            populate(setPTCPreferencesMethod);
            return setPTCPreferencesMethod;
        }

        protected void populate(SetPTCPreferencesMethod setPTCPreferencesMethod) {
            super.populate((Method) setPTCPreferencesMethod);
            setPTCPreferencesMethod.setShowPTCIngress(this.showPTCIngress);
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

        public Builder withShowPTCIngress(Boolean bool) {
            this.showPTCIngress = bool;
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
        if (!(sOAObject instanceof SetPTCPreferencesMethod)) {
            return 1;
        }
        Boolean boolIsShowPTCIngress = isShowPTCIngress();
        Boolean boolIsShowPTCIngress2 = ((SetPTCPreferencesMethod) sOAObject).isShowPTCIngress();
        if (boolIsShowPTCIngress != boolIsShowPTCIngress2) {
            if (boolIsShowPTCIngress == null) {
                return -1;
            }
            if (boolIsShowPTCIngress2 == null) {
                return 1;
            }
            int iCompareTo = boolIsShowPTCIngress.compareTo(boolIsShowPTCIngress2);
            if (iCompareTo != 0) {
                return iCompareTo;
            }
        }
        return super.compareTo(sOAObject);
    }

    @Override // SOACoreInterface.v1_0.Method, SOACoreInterface.v1_0.SOAObject
    public boolean equals(Object obj) {
        if (obj instanceof SetPTCPreferencesMethod) {
            return super.equals(obj) && internalEqualityCheck(isShowPTCIngress(), ((SetPTCPreferencesMethod) obj).isShowPTCIngress());
        }
        return false;
    }

    @Override // SOACoreInterface.v1_0.Method, SOACoreInterface.v1_0.SOAObject
    public int hashCode() {
        return internalHashCodeCompute(Integer.valueOf(super.hashCode()), Integer.valueOf(classNameHashCode), isShowPTCIngress());
    }

    public Boolean isShowPTCIngress() {
        return this.showPTCIngress;
    }

    public void setShowPTCIngress(Boolean bool) {
        this.showPTCIngress = bool;
    }
}
