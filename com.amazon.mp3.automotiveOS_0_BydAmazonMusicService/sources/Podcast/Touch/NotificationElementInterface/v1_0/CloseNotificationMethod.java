package Podcast.Touch.NotificationElementInterface.v1_0;

import SOACoreInterface.v1_0.Method;
import SOACoreInterface.v1_0.Queue;
import SOACoreInterface.v1_0.SOAObject;
import java.util.Arrays;

/* JADX INFO: loaded from: classes.dex */
public class CloseNotificationMethod extends Method {
    private static final int classNameHashCode = internalHashCodeCompute("Podcast.Touch.NotificationElementInterface.v1_0.CloseNotificationMethod");
    private String id;

    public static class Builder extends Method.Builder {
        protected String id;

        public CloseNotificationMethod build() {
            CloseNotificationMethod closeNotificationMethod = new CloseNotificationMethod();
            populate(closeNotificationMethod);
            return closeNotificationMethod;
        }

        protected void populate(CloseNotificationMethod closeNotificationMethod) {
            super.populate((Method) closeNotificationMethod);
            closeNotificationMethod.setId(this.id);
        }

        @Override // SOACoreInterface.v1_0.Method.Builder
        public Builder withForced(Boolean bool) {
            super.withForced(bool);
            return this;
        }

        public Builder withId(String str) {
            this.id = str;
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
        if (!(sOAObject instanceof CloseNotificationMethod)) {
            return 1;
        }
        String id = getId();
        String id2 = ((CloseNotificationMethod) sOAObject).getId();
        if (id != id2) {
            if (id == null) {
                return -1;
            }
            if (id2 == null) {
                return 1;
            }
            int iCompareTo = id.compareTo(id2);
            if (iCompareTo != 0) {
                return iCompareTo;
            }
        }
        return super.compareTo(sOAObject);
    }

    @Override // SOACoreInterface.v1_0.Method, SOACoreInterface.v1_0.SOAObject
    public boolean equals(Object obj) {
        if (obj instanceof CloseNotificationMethod) {
            return super.equals(obj) && internalEqualityCheck(getId(), ((CloseNotificationMethod) obj).getId());
        }
        return false;
    }

    public String getId() {
        return this.id;
    }

    @Override // SOACoreInterface.v1_0.Method, SOACoreInterface.v1_0.SOAObject
    public int hashCode() {
        return internalHashCodeCompute(Integer.valueOf(super.hashCode()), Integer.valueOf(classNameHashCode), getId());
    }

    public void setId(String str) {
        this.id = str;
    }
}
