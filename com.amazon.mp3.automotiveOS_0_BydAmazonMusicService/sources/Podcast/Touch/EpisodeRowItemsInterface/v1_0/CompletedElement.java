package Podcast.Touch.EpisodeRowItemsInterface.v1_0;

import SOACoreInterface.v1_0.SOAObject;
import SOATemplateListInterface.v1_0.TemplateElement;
import java.util.Arrays;

/* JADX INFO: loaded from: classes.dex */
public class CompletedElement extends TemplateElement {
    private static final int classNameHashCode = internalHashCodeCompute("Podcast.Touch.EpisodeRowItemsInterface.v1_0.CompletedElement");
    private String id;

    public static class Builder extends TemplateElement.Builder {
        protected String id;

        public CompletedElement build() {
            CompletedElement completedElement = new CompletedElement();
            populate(completedElement);
            return completedElement;
        }

        protected void populate(CompletedElement completedElement) {
            super.populate((TemplateElement) completedElement);
            completedElement.setId(this.id);
        }

        public Builder withId(String str) {
            this.id = str;
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
        if (!(sOAObject instanceof CompletedElement)) {
            return 1;
        }
        String id = getId();
        String id2 = ((CompletedElement) sOAObject).getId();
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

    @Override // SOATemplateListInterface.v1_0.TemplateElement, SOACoreInterface.v1_0.SOAObject
    public boolean equals(Object obj) {
        if (obj instanceof CompletedElement) {
            return super.equals(obj) && internalEqualityCheck(getId(), ((CompletedElement) obj).getId());
        }
        return false;
    }

    public String getId() {
        return this.id;
    }

    @Override // SOATemplateListInterface.v1_0.TemplateElement, SOACoreInterface.v1_0.SOAObject
    public int hashCode() {
        return internalHashCodeCompute(Integer.valueOf(super.hashCode()), Integer.valueOf(classNameHashCode), getId());
    }

    public void setId(String str) {
        this.id = str;
    }
}
