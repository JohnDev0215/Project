package Podcast.FollowPromptInterface.v1_0;

import SOACoreInterface.v1_0.SOAObject;
import SOATemplateListInterface.v1_0.TemplateElement;
import java.util.Arrays;

/* JADX INFO: loaded from: classes.dex */
public class HidePromptPreferenceElement extends TemplateElement {
    private static final int classNameHashCode = internalHashCodeCompute("Podcast.FollowPromptInterface.v1_0.HidePromptPreferenceElement");
    private long updatedTime;

    public static class Builder extends TemplateElement.Builder {
        protected long updatedTime;

        public HidePromptPreferenceElement build() {
            HidePromptPreferenceElement hidePromptPreferenceElement = new HidePromptPreferenceElement();
            populate(hidePromptPreferenceElement);
            return hidePromptPreferenceElement;
        }

        protected void populate(HidePromptPreferenceElement hidePromptPreferenceElement) {
            super.populate((TemplateElement) hidePromptPreferenceElement);
            hidePromptPreferenceElement.setUpdatedTime(this.updatedTime);
        }

        public Builder withUpdatedTime(long j) {
            this.updatedTime = j;
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
    @Override // SOATemplateListInterface.v1_0.TemplateElement, SOACoreInterface.v1_0.SOAObject, java.lang.Comparable
    @Deprecated
    public int compareTo(@Deprecated SOAObject sOAObject) {
        if (sOAObject == null) {
            return -1;
        }
        if (sOAObject == this) {
            return 0;
        }
        if (!(sOAObject instanceof HidePromptPreferenceElement)) {
            return 1;
        }
        HidePromptPreferenceElement hidePromptPreferenceElement = (HidePromptPreferenceElement) sOAObject;
        if (getUpdatedTime() < hidePromptPreferenceElement.getUpdatedTime()) {
            return -1;
        }
        if (getUpdatedTime() > hidePromptPreferenceElement.getUpdatedTime()) {
            return 1;
        }
        return super.compareTo(sOAObject);
    }

    @Override // SOATemplateListInterface.v1_0.TemplateElement, SOACoreInterface.v1_0.SOAObject
    public boolean equals(Object obj) {
        if (obj instanceof HidePromptPreferenceElement) {
            return super.equals(obj) && internalEqualityCheck(Long.valueOf(getUpdatedTime()), Long.valueOf(((HidePromptPreferenceElement) obj).getUpdatedTime()));
        }
        return false;
    }

    public long getUpdatedTime() {
        return this.updatedTime;
    }

    @Override // SOATemplateListInterface.v1_0.TemplateElement, SOACoreInterface.v1_0.SOAObject
    public int hashCode() {
        return internalHashCodeCompute(Integer.valueOf(super.hashCode()), Integer.valueOf(classNameHashCode), Long.valueOf(getUpdatedTime()));
    }

    public void setUpdatedTime(long j) {
        this.updatedTime = j;
    }
}
