package Podcast.Desktop.TemplateMetadataInterface.v1_0;

import SOACoreInterface.v1_0.SOAObject;
import SOATemplateListInterface.v1_0.TemplateElement;
import java.util.Arrays;

/* JADX INFO: loaded from: classes.dex */
public class TemplateMetadataElement extends TemplateElement {
    private static final int classNameHashCode = internalHashCodeCompute("Podcast.Desktop.TemplateMetadataInterface.v1_0.TemplateMetadataElement");
    private String deeplink;
    private String id;

    public static class Builder extends TemplateElement.Builder {
        protected String deeplink;
        protected String id;

        public TemplateMetadataElement build() {
            TemplateMetadataElement templateMetadataElement = new TemplateMetadataElement();
            populate(templateMetadataElement);
            return templateMetadataElement;
        }

        protected void populate(TemplateMetadataElement templateMetadataElement) {
            super.populate((TemplateElement) templateMetadataElement);
            templateMetadataElement.setId(this.id);
            templateMetadataElement.setDeeplink(this.deeplink);
        }

        public Builder withDeeplink(String str) {
            this.deeplink = str;
            return this;
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
        if (!(sOAObject instanceof TemplateMetadataElement)) {
            return 1;
        }
        TemplateMetadataElement templateMetadataElement = (TemplateMetadataElement) sOAObject;
        String id = getId();
        String id2 = templateMetadataElement.getId();
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
        String deeplink = getDeeplink();
        String deeplink2 = templateMetadataElement.getDeeplink();
        if (deeplink != deeplink2) {
            if (deeplink == null) {
                return -1;
            }
            if (deeplink2 == null) {
                return 1;
            }
            int iCompareTo2 = deeplink.compareTo(deeplink2);
            if (iCompareTo2 != 0) {
                return iCompareTo2;
            }
        }
        return super.compareTo(sOAObject);
    }

    @Override // SOATemplateListInterface.v1_0.TemplateElement, SOACoreInterface.v1_0.SOAObject
    public boolean equals(Object obj) {
        if (!(obj instanceof TemplateMetadataElement)) {
            return false;
        }
        TemplateMetadataElement templateMetadataElement = (TemplateMetadataElement) obj;
        return super.equals(obj) && internalEqualityCheck(getId(), templateMetadataElement.getId()) && internalEqualityCheck(getDeeplink(), templateMetadataElement.getDeeplink());
    }

    public String getDeeplink() {
        return this.deeplink;
    }

    public String getId() {
        return this.id;
    }

    @Override // SOATemplateListInterface.v1_0.TemplateElement, SOACoreInterface.v1_0.SOAObject
    public int hashCode() {
        return internalHashCodeCompute(Integer.valueOf(super.hashCode()), Integer.valueOf(classNameHashCode), getId(), getDeeplink());
    }

    public void setDeeplink(String str) {
        this.deeplink = str;
    }

    public void setId(String str) {
        this.id = str;
    }
}
