package Podcast.UpsellInterface.v1_0;

import SOACoreInterface.v1_0.Method;
import SOACoreInterface.v1_0.Queue;
import SOACoreInterface.v1_0.SOAObject;
import java.util.Arrays;

/* JADX INFO: loaded from: classes.dex */
public class ShowUpsellMethod extends Method {
    private static final int classNameHashCode = internalHashCodeCompute("Podcast.UpsellInterface.v1_0.ShowUpsellMethod");
    private String redirectDeeplinkUrl;
    private String trigger;

    public static class Builder extends Method.Builder {
        protected String redirectDeeplinkUrl;
        protected String trigger;

        public ShowUpsellMethod build() {
            ShowUpsellMethod showUpsellMethod = new ShowUpsellMethod();
            populate(showUpsellMethod);
            return showUpsellMethod;
        }

        protected void populate(ShowUpsellMethod showUpsellMethod) {
            super.populate((Method) showUpsellMethod);
            showUpsellMethod.setTrigger(this.trigger);
            showUpsellMethod.setRedirectDeeplinkUrl(this.redirectDeeplinkUrl);
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

        public Builder withRedirectDeeplinkUrl(String str) {
            this.redirectDeeplinkUrl = str;
            return this;
        }

        public Builder withTrigger(String str) {
            this.trigger = str;
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
        if (!(sOAObject instanceof ShowUpsellMethod)) {
            return 1;
        }
        ShowUpsellMethod showUpsellMethod = (ShowUpsellMethod) sOAObject;
        String trigger = getTrigger();
        String trigger2 = showUpsellMethod.getTrigger();
        if (trigger != trigger2) {
            if (trigger == null) {
                return -1;
            }
            if (trigger2 == null) {
                return 1;
            }
            int iCompareTo = trigger.compareTo(trigger2);
            if (iCompareTo != 0) {
                return iCompareTo;
            }
        }
        String redirectDeeplinkUrl = getRedirectDeeplinkUrl();
        String redirectDeeplinkUrl2 = showUpsellMethod.getRedirectDeeplinkUrl();
        if (redirectDeeplinkUrl != redirectDeeplinkUrl2) {
            if (redirectDeeplinkUrl == null) {
                return -1;
            }
            if (redirectDeeplinkUrl2 == null) {
                return 1;
            }
            int iCompareTo2 = redirectDeeplinkUrl.compareTo(redirectDeeplinkUrl2);
            if (iCompareTo2 != 0) {
                return iCompareTo2;
            }
        }
        return super.compareTo(sOAObject);
    }

    @Override // SOACoreInterface.v1_0.Method, SOACoreInterface.v1_0.SOAObject
    public boolean equals(Object obj) {
        if (!(obj instanceof ShowUpsellMethod)) {
            return false;
        }
        ShowUpsellMethod showUpsellMethod = (ShowUpsellMethod) obj;
        return super.equals(obj) && internalEqualityCheck(getTrigger(), showUpsellMethod.getTrigger()) && internalEqualityCheck(getRedirectDeeplinkUrl(), showUpsellMethod.getRedirectDeeplinkUrl());
    }

    public String getRedirectDeeplinkUrl() {
        return this.redirectDeeplinkUrl;
    }

    public String getTrigger() {
        return this.trigger;
    }

    @Override // SOACoreInterface.v1_0.Method, SOACoreInterface.v1_0.SOAObject
    public int hashCode() {
        return internalHashCodeCompute(Integer.valueOf(super.hashCode()), Integer.valueOf(classNameHashCode), getTrigger(), getRedirectDeeplinkUrl());
    }

    public void setRedirectDeeplinkUrl(String str) {
        this.redirectDeeplinkUrl = str;
    }

    public void setTrigger(String str) {
        this.trigger = str;
    }
}
