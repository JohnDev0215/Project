package Podcast.Touch.EpisodeGroupItemsInterface.v1_0;

import SOACoreInterface.v1_0.SOAObject;
import SOATemplateListInterface.v1_0.TemplateElement;
import java.util.Arrays;

/* JADX INFO: loaded from: classes.dex */
public class HeaderRowItemElement extends TemplateElement {
    private static final int classNameHashCode = internalHashCodeCompute("Podcast.Touch.EpisodeGroupItemsInterface.v1_0.HeaderRowItemElement");
    private String headerTitle;
    private String headerType;

    public static class Builder extends TemplateElement.Builder {
        protected String headerTitle;
        protected String headerType;

        public HeaderRowItemElement build() {
            HeaderRowItemElement headerRowItemElement = new HeaderRowItemElement();
            populate(headerRowItemElement);
            return headerRowItemElement;
        }

        protected void populate(HeaderRowItemElement headerRowItemElement) {
            super.populate((TemplateElement) headerRowItemElement);
            headerRowItemElement.setHeaderTitle(this.headerTitle);
            headerRowItemElement.setHeaderType(this.headerType);
        }

        public Builder withHeaderTitle(String str) {
            this.headerTitle = str;
            return this;
        }

        public Builder withHeaderType(String str) {
            this.headerType = str;
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
        if (!(sOAObject instanceof HeaderRowItemElement)) {
            return 1;
        }
        HeaderRowItemElement headerRowItemElement = (HeaderRowItemElement) sOAObject;
        String headerTitle = getHeaderTitle();
        String headerTitle2 = headerRowItemElement.getHeaderTitle();
        if (headerTitle != headerTitle2) {
            if (headerTitle == null) {
                return -1;
            }
            if (headerTitle2 == null) {
                return 1;
            }
            int iCompareTo = headerTitle.compareTo(headerTitle2);
            if (iCompareTo != 0) {
                return iCompareTo;
            }
        }
        String headerType = getHeaderType();
        String headerType2 = headerRowItemElement.getHeaderType();
        if (headerType != headerType2) {
            if (headerType == null) {
                return -1;
            }
            if (headerType2 == null) {
                return 1;
            }
            int iCompareTo2 = headerType.compareTo(headerType2);
            if (iCompareTo2 != 0) {
                return iCompareTo2;
            }
        }
        return super.compareTo(sOAObject);
    }

    @Override // SOATemplateListInterface.v1_0.TemplateElement, SOACoreInterface.v1_0.SOAObject
    public boolean equals(Object obj) {
        if (!(obj instanceof HeaderRowItemElement)) {
            return false;
        }
        HeaderRowItemElement headerRowItemElement = (HeaderRowItemElement) obj;
        return super.equals(obj) && internalEqualityCheck(getHeaderTitle(), headerRowItemElement.getHeaderTitle()) && internalEqualityCheck(getHeaderType(), headerRowItemElement.getHeaderType());
    }

    public String getHeaderTitle() {
        return this.headerTitle;
    }

    public String getHeaderType() {
        return this.headerType;
    }

    @Override // SOATemplateListInterface.v1_0.TemplateElement, SOACoreInterface.v1_0.SOAObject
    public int hashCode() {
        return internalHashCodeCompute(Integer.valueOf(super.hashCode()), Integer.valueOf(classNameHashCode), getHeaderTitle(), getHeaderType());
    }

    public void setHeaderTitle(String str) {
        this.headerTitle = str;
    }

    public void setHeaderType(String str) {
        this.headerType = str;
    }
}
