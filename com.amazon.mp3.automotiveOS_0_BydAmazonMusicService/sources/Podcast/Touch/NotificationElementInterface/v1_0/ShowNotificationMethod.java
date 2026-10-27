package Podcast.Touch.NotificationElementInterface.v1_0;

import SOACoreInterface.v1_0.Method;
import SOACoreInterface.v1_0.Queue;
import SOACoreInterface.v1_0.SOAObject;
import java.util.Arrays;

/* JADX INFO: loaded from: classes.dex */
public class ShowNotificationMethod extends Method {
    private static final int classNameHashCode = internalHashCodeCompute("Podcast.Touch.NotificationElementInterface.v1_0.ShowNotificationMethod");
    private NotificationElement notification;

    public static class Builder extends Method.Builder {
        protected NotificationElement notification;

        public ShowNotificationMethod build() {
            ShowNotificationMethod showNotificationMethod = new ShowNotificationMethod();
            populate(showNotificationMethod);
            return showNotificationMethod;
        }

        protected void populate(ShowNotificationMethod showNotificationMethod) {
            super.populate((Method) showNotificationMethod);
            showNotificationMethod.setNotification(this.notification);
        }

        @Override // SOACoreInterface.v1_0.Method.Builder
        public Builder withForced(Boolean bool) {
            super.withForced(bool);
            return this;
        }

        public Builder withNotification(NotificationElement notificationElement) {
            this.notification = notificationElement;
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
        if (!(sOAObject instanceof ShowNotificationMethod)) {
            return 1;
        }
        NotificationElement notification = getNotification();
        NotificationElement notification2 = ((ShowNotificationMethod) sOAObject).getNotification();
        if (notification != notification2) {
            if (notification == null) {
                return -1;
            }
            if (notification2 == null) {
                return 1;
            }
            int iCompareTo = notification.compareTo(notification2);
            if (iCompareTo != 0) {
                return iCompareTo;
            }
        }
        return super.compareTo(sOAObject);
    }

    @Override // SOACoreInterface.v1_0.Method, SOACoreInterface.v1_0.SOAObject
    public boolean equals(Object obj) {
        if (obj instanceof ShowNotificationMethod) {
            return super.equals(obj) && internalEqualityCheck(getNotification(), ((ShowNotificationMethod) obj).getNotification());
        }
        return false;
    }

    public NotificationElement getNotification() {
        return this.notification;
    }

    @Override // SOACoreInterface.v1_0.Method, SOACoreInterface.v1_0.SOAObject
    public int hashCode() {
        return internalHashCodeCompute(Integer.valueOf(super.hashCode()), Integer.valueOf(classNameHashCode), getNotification());
    }

    public void setNotification(NotificationElement notificationElement) {
        this.notification = notificationElement;
    }
}
