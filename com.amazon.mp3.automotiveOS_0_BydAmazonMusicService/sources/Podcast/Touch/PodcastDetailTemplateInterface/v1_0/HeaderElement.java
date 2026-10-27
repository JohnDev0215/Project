package Podcast.Touch.PodcastDetailTemplateInterface.v1_0;

import SOACoreInterface.v1_0.SOAObject;
import SOATemplateListInterface.v1_0.TemplateElement;
import java.util.Arrays;

/* JADX INFO: loaded from: classes.dex */
public class HeaderElement extends TemplateElement {
    private static final int classNameHashCode = internalHashCodeCompute("Podcast.Touch.PodcastDetailTemplateInterface.v1_0.HeaderElement");
    private String image;
    private String label;
    private String primaryText;
    private String secondaryText;

    public static class Builder extends TemplateElement.Builder {
        protected String image;
        protected String label;
        protected String primaryText;
        protected String secondaryText;

        public HeaderElement build() {
            HeaderElement headerElement = new HeaderElement();
            populate(headerElement);
            return headerElement;
        }

        protected void populate(HeaderElement headerElement) {
            super.populate((TemplateElement) headerElement);
            headerElement.setPrimaryText(this.primaryText);
            headerElement.setSecondaryText(this.secondaryText);
            headerElement.setImage(this.image);
            headerElement.setLabel(this.label);
        }

        public Builder withImage(String str) {
            this.image = str;
            return this;
        }

        public Builder withLabel(String str) {
            this.label = str;
            return this;
        }

        public Builder withPrimaryText(String str) {
            this.primaryText = str;
            return this;
        }

        public Builder withSecondaryText(String str) {
            this.secondaryText = str;
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
        if (!(sOAObject instanceof HeaderElement)) {
            return 1;
        }
        HeaderElement headerElement = (HeaderElement) sOAObject;
        String primaryText = getPrimaryText();
        String primaryText2 = headerElement.getPrimaryText();
        if (primaryText != primaryText2) {
            if (primaryText == null) {
                return -1;
            }
            if (primaryText2 == null) {
                return 1;
            }
            int iCompareTo = primaryText.compareTo(primaryText2);
            if (iCompareTo != 0) {
                return iCompareTo;
            }
        }
        String secondaryText = getSecondaryText();
        String secondaryText2 = headerElement.getSecondaryText();
        if (secondaryText != secondaryText2) {
            if (secondaryText == null) {
                return -1;
            }
            if (secondaryText2 == null) {
                return 1;
            }
            int iCompareTo2 = secondaryText.compareTo(secondaryText2);
            if (iCompareTo2 != 0) {
                return iCompareTo2;
            }
        }
        String image = getImage();
        String image2 = headerElement.getImage();
        if (image != image2) {
            if (image == null) {
                return -1;
            }
            if (image2 == null) {
                return 1;
            }
            int iCompareTo3 = image.compareTo(image2);
            if (iCompareTo3 != 0) {
                return iCompareTo3;
            }
        }
        String label = getLabel();
        String label2 = headerElement.getLabel();
        if (label != label2) {
            if (label == null) {
                return -1;
            }
            if (label2 == null) {
                return 1;
            }
            int iCompareTo4 = label.compareTo(label2);
            if (iCompareTo4 != 0) {
                return iCompareTo4;
            }
        }
        return super.compareTo(sOAObject);
    }

    @Override // SOATemplateListInterface.v1_0.TemplateElement, SOACoreInterface.v1_0.SOAObject
    public boolean equals(Object obj) {
        if (!(obj instanceof HeaderElement)) {
            return false;
        }
        HeaderElement headerElement = (HeaderElement) obj;
        return super.equals(obj) && internalEqualityCheck(getPrimaryText(), headerElement.getPrimaryText()) && internalEqualityCheck(getSecondaryText(), headerElement.getSecondaryText()) && internalEqualityCheck(getImage(), headerElement.getImage()) && internalEqualityCheck(getLabel(), headerElement.getLabel());
    }

    public String getImage() {
        return this.image;
    }

    public String getLabel() {
        return this.label;
    }

    public String getPrimaryText() {
        return this.primaryText;
    }

    public String getSecondaryText() {
        return this.secondaryText;
    }

    @Override // SOATemplateListInterface.v1_0.TemplateElement, SOACoreInterface.v1_0.SOAObject
    public int hashCode() {
        return internalHashCodeCompute(Integer.valueOf(super.hashCode()), Integer.valueOf(classNameHashCode), getPrimaryText(), getSecondaryText(), getImage(), getLabel());
    }

    public void setImage(String str) {
        this.image = str;
    }

    public void setLabel(String str) {
        this.label = str;
    }

    public void setPrimaryText(String str) {
        this.primaryText = str;
    }

    public void setSecondaryText(String str) {
        this.secondaryText = str;
    }
}
