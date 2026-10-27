package Podcast.Touch.RibbonNotificationElementInterface.v1_0;

import Podcast.Touch.NotificationElementInterface.v1_0.NotificationElement;
import SOACoreInterface.v1_0.Method;
import SOACoreInterface.v1_0.SOAObject;
import java.util.Arrays;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public class RibbonNotificationElement extends NotificationElement {
    private static final int classNameHashCode = internalHashCodeCompute("Podcast.Touch.RibbonNotificationElementInterface.v1_0.RibbonNotificationElement");
    private RibbonCloseButtonElement closeButton;
    private String icon;
    private String id;
    private RibbonSecondaryButtonElement leftButton;
    private RibbonMessageElement message;
    private RibbonPrimaryButtonElement rightButton;
    private Integer timeoutSeconds;

    public static class Builder extends NotificationElement.Builder {
        protected RibbonCloseButtonElement closeButton;
        protected String icon;
        protected String id;
        protected RibbonSecondaryButtonElement leftButton;
        protected RibbonMessageElement message;
        protected RibbonPrimaryButtonElement rightButton;
        protected Integer timeoutSeconds;

        public RibbonNotificationElement build() {
            RibbonNotificationElement ribbonNotificationElement = new RibbonNotificationElement();
            populate(ribbonNotificationElement);
            return ribbonNotificationElement;
        }

        protected void populate(RibbonNotificationElement ribbonNotificationElement) {
            super.populate((NotificationElement) ribbonNotificationElement);
            ribbonNotificationElement.setIcon(this.icon);
            ribbonNotificationElement.setMessage(this.message);
            ribbonNotificationElement.setRightButton(this.rightButton);
            ribbonNotificationElement.setCloseButton(this.closeButton);
            ribbonNotificationElement.setLeftButton(this.leftButton);
            ribbonNotificationElement.setTimeoutSeconds(this.timeoutSeconds);
            ribbonNotificationElement.setId(this.id);
        }

        public Builder withCloseButton(RibbonCloseButtonElement ribbonCloseButtonElement) {
            this.closeButton = ribbonCloseButtonElement;
            return this;
        }

        public Builder withIcon(String str) {
            this.icon = str;
            return this;
        }

        public Builder withId(String str) {
            this.id = str;
            return this;
        }

        public Builder withLeftButton(RibbonSecondaryButtonElement ribbonSecondaryButtonElement) {
            this.leftButton = ribbonSecondaryButtonElement;
            return this;
        }

        public Builder withMessage(RibbonMessageElement ribbonMessageElement) {
            this.message = ribbonMessageElement;
            return this;
        }

        @Override // Podcast.Touch.NotificationElementInterface.v1_0.NotificationElement.Builder
        public /* bridge */ /* synthetic */ NotificationElement.Builder withOnItemSelected(List list) {
            return withOnItemSelected((List<Method>) list);
        }

        @Override // Podcast.Touch.NotificationElementInterface.v1_0.NotificationElement.Builder
        public Builder withOnItemSelected(List<Method> list) {
            super.withOnItemSelected(list);
            return this;
        }

        @Override // Podcast.Touch.NotificationElementInterface.v1_0.NotificationElement.Builder
        public /* bridge */ /* synthetic */ NotificationElement.Builder withOnViewed(List list) {
            return withOnViewed((List<Method>) list);
        }

        @Override // Podcast.Touch.NotificationElementInterface.v1_0.NotificationElement.Builder
        public Builder withOnViewed(List<Method> list) {
            super.withOnViewed(list);
            return this;
        }

        public Builder withRightButton(RibbonPrimaryButtonElement ribbonPrimaryButtonElement) {
            this.rightButton = ribbonPrimaryButtonElement;
            return this;
        }

        public Builder withTimeoutSeconds(Integer num) {
            this.timeoutSeconds = num;
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
    @Override // Podcast.Touch.NotificationElementInterface.v1_0.NotificationElement, SOATemplateListInterface.v1_0.TemplateElement, SOACoreInterface.v1_0.SOAObject, java.lang.Comparable
    @Deprecated
    public int compareTo(@Deprecated SOAObject sOAObject) {
        if (sOAObject == null) {
            return -1;
        }
        if (sOAObject == this) {
            return 0;
        }
        if (!(sOAObject instanceof RibbonNotificationElement)) {
            return 1;
        }
        RibbonNotificationElement ribbonNotificationElement = (RibbonNotificationElement) sOAObject;
        String icon = getIcon();
        String icon2 = ribbonNotificationElement.getIcon();
        if (icon != icon2) {
            if (icon == null) {
                return -1;
            }
            if (icon2 == null) {
                return 1;
            }
            int iCompareTo = icon.compareTo(icon2);
            if (iCompareTo != 0) {
                return iCompareTo;
            }
        }
        RibbonMessageElement message = getMessage();
        RibbonMessageElement message2 = ribbonNotificationElement.getMessage();
        if (message != message2) {
            if (message == null) {
                return -1;
            }
            if (message2 == null) {
                return 1;
            }
            int iCompareTo2 = message.compareTo(message2);
            if (iCompareTo2 != 0) {
                return iCompareTo2;
            }
        }
        RibbonPrimaryButtonElement rightButton = getRightButton();
        RibbonPrimaryButtonElement rightButton2 = ribbonNotificationElement.getRightButton();
        if (rightButton != rightButton2) {
            if (rightButton == null) {
                return -1;
            }
            if (rightButton2 == null) {
                return 1;
            }
            int iCompareTo3 = rightButton.compareTo(rightButton2);
            if (iCompareTo3 != 0) {
                return iCompareTo3;
            }
        }
        RibbonCloseButtonElement closeButton = getCloseButton();
        RibbonCloseButtonElement closeButton2 = ribbonNotificationElement.getCloseButton();
        if (closeButton != closeButton2) {
            if (closeButton == null) {
                return -1;
            }
            if (closeButton2 == null) {
                return 1;
            }
            int iCompareTo4 = closeButton.compareTo(closeButton2);
            if (iCompareTo4 != 0) {
                return iCompareTo4;
            }
        }
        RibbonSecondaryButtonElement leftButton = getLeftButton();
        RibbonSecondaryButtonElement leftButton2 = ribbonNotificationElement.getLeftButton();
        if (leftButton != leftButton2) {
            if (leftButton == null) {
                return -1;
            }
            if (leftButton2 == null) {
                return 1;
            }
            int iCompareTo5 = leftButton.compareTo(leftButton2);
            if (iCompareTo5 != 0) {
                return iCompareTo5;
            }
        }
        Integer timeoutSeconds = getTimeoutSeconds();
        Integer timeoutSeconds2 = ribbonNotificationElement.getTimeoutSeconds();
        if (timeoutSeconds != timeoutSeconds2) {
            if (timeoutSeconds == null) {
                return -1;
            }
            if (timeoutSeconds2 == null) {
                return 1;
            }
            int iCompareTo6 = timeoutSeconds.compareTo(timeoutSeconds2);
            if (iCompareTo6 != 0) {
                return iCompareTo6;
            }
        }
        String id = getId();
        String id2 = ribbonNotificationElement.getId();
        if (id != id2) {
            if (id == null) {
                return -1;
            }
            if (id2 == null) {
                return 1;
            }
            int iCompareTo7 = id.compareTo(id2);
            if (iCompareTo7 != 0) {
                return iCompareTo7;
            }
        }
        return super.compareTo(sOAObject);
    }

    @Override // Podcast.Touch.NotificationElementInterface.v1_0.NotificationElement, SOATemplateListInterface.v1_0.TemplateElement, SOACoreInterface.v1_0.SOAObject
    public boolean equals(Object obj) {
        if (!(obj instanceof RibbonNotificationElement)) {
            return false;
        }
        RibbonNotificationElement ribbonNotificationElement = (RibbonNotificationElement) obj;
        return super.equals(obj) && internalEqualityCheck(getIcon(), ribbonNotificationElement.getIcon()) && internalEqualityCheck(getMessage(), ribbonNotificationElement.getMessage()) && internalEqualityCheck(getRightButton(), ribbonNotificationElement.getRightButton()) && internalEqualityCheck(getCloseButton(), ribbonNotificationElement.getCloseButton()) && internalEqualityCheck(getLeftButton(), ribbonNotificationElement.getLeftButton()) && internalEqualityCheck(getTimeoutSeconds(), ribbonNotificationElement.getTimeoutSeconds()) && internalEqualityCheck(getId(), ribbonNotificationElement.getId());
    }

    public RibbonCloseButtonElement getCloseButton() {
        return this.closeButton;
    }

    public String getIcon() {
        return this.icon;
    }

    public String getId() {
        return this.id;
    }

    public RibbonSecondaryButtonElement getLeftButton() {
        return this.leftButton;
    }

    public RibbonMessageElement getMessage() {
        return this.message;
    }

    public RibbonPrimaryButtonElement getRightButton() {
        return this.rightButton;
    }

    public Integer getTimeoutSeconds() {
        return this.timeoutSeconds;
    }

    @Override // Podcast.Touch.NotificationElementInterface.v1_0.NotificationElement, SOATemplateListInterface.v1_0.TemplateElement, SOACoreInterface.v1_0.SOAObject
    public int hashCode() {
        return internalHashCodeCompute(Integer.valueOf(super.hashCode()), Integer.valueOf(classNameHashCode), getIcon(), getMessage(), getRightButton(), getCloseButton(), getLeftButton(), getTimeoutSeconds(), getId());
    }

    public void setCloseButton(RibbonCloseButtonElement ribbonCloseButtonElement) {
        this.closeButton = ribbonCloseButtonElement;
    }

    public void setIcon(String str) {
        this.icon = str;
    }

    public void setId(String str) {
        this.id = str;
    }

    public void setLeftButton(RibbonSecondaryButtonElement ribbonSecondaryButtonElement) {
        this.leftButton = ribbonSecondaryButtonElement;
    }

    public void setMessage(RibbonMessageElement ribbonMessageElement) {
        this.message = ribbonMessageElement;
    }

    public void setRightButton(RibbonPrimaryButtonElement ribbonPrimaryButtonElement) {
        this.rightButton = ribbonPrimaryButtonElement;
    }

    public void setTimeoutSeconds(Integer num) {
        this.timeoutSeconds = num;
    }
}
