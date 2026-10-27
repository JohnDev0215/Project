package Podcast.Touch.CompactDetailTemplateInterface.v1_0;

import SOACoreInterface.v1_0.Method;
import SOACoreInterface.v1_0.SOAObject;
import SOATemplateListInterface.v1_0.TemplateElement;
import java.util.Arrays;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public class HeaderElement extends TemplateElement {
    private static final int classNameHashCode = internalHashCodeCompute("Podcast.Touch.CompactDetailTemplateInterface.v1_0.HeaderElement");
    private String image;
    private String label;
    private List<Method> onSecondaryTextSelected;
    private String primaryText;
    private String secondaryText;
    private String tertiaryText;

    public static class Builder extends TemplateElement.Builder {
        protected String image;
        protected String label;
        protected List<Method> onSecondaryTextSelected;
        protected String primaryText;
        protected String secondaryText;
        protected String tertiaryText;

        public HeaderElement build() {
            HeaderElement headerElement = new HeaderElement();
            populate(headerElement);
            return headerElement;
        }

        protected void populate(HeaderElement headerElement) {
            super.populate((TemplateElement) headerElement);
            headerElement.setPrimaryText(this.primaryText);
            headerElement.setLabel(this.label);
            headerElement.setSecondaryText(this.secondaryText);
            headerElement.setTertiaryText(this.tertiaryText);
            headerElement.setImage(this.image);
            headerElement.setOnSecondaryTextSelected(this.onSecondaryTextSelected);
        }

        public Builder withImage(String str) {
            this.image = str;
            return this;
        }

        public Builder withLabel(String str) {
            this.label = str;
            return this;
        }

        public Builder withOnSecondaryTextSelected(List<Method> list) {
            this.onSecondaryTextSelected = list;
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

        public Builder withTertiaryText(String str) {
            this.tertiaryText = str;
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
        String label = getLabel();
        String label2 = headerElement.getLabel();
        if (label != label2) {
            if (label == null) {
                return -1;
            }
            if (label2 == null) {
                return 1;
            }
            int iCompareTo2 = label.compareTo(label2);
            if (iCompareTo2 != 0) {
                return iCompareTo2;
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
            int iCompareTo3 = secondaryText.compareTo(secondaryText2);
            if (iCompareTo3 != 0) {
                return iCompareTo3;
            }
        }
        String tertiaryText = getTertiaryText();
        String tertiaryText2 = headerElement.getTertiaryText();
        if (tertiaryText != tertiaryText2) {
            if (tertiaryText == null) {
                return -1;
            }
            if (tertiaryText2 == null) {
                return 1;
            }
            int iCompareTo4 = tertiaryText.compareTo(tertiaryText2);
            if (iCompareTo4 != 0) {
                return iCompareTo4;
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
            int iCompareTo5 = image.compareTo(image2);
            if (iCompareTo5 != 0) {
                return iCompareTo5;
            }
        }
        List<Method> onSecondaryTextSelected = getOnSecondaryTextSelected();
        List<Method> onSecondaryTextSelected2 = headerElement.getOnSecondaryTextSelected();
        if (onSecondaryTextSelected != onSecondaryTextSelected2) {
            if (onSecondaryTextSelected == null) {
                return -1;
            }
            if (onSecondaryTextSelected2 == null) {
                return 1;
            }
            if (onSecondaryTextSelected instanceof Comparable) {
                int iCompareTo6 = ((Comparable) onSecondaryTextSelected).compareTo(onSecondaryTextSelected2);
                if (iCompareTo6 != 0) {
                    return iCompareTo6;
                }
            } else if (!onSecondaryTextSelected.equals(onSecondaryTextSelected2)) {
                int iHashCode = onSecondaryTextSelected.hashCode();
                int iHashCode2 = onSecondaryTextSelected2.hashCode();
                if (iHashCode < iHashCode2) {
                    return -1;
                }
                if (iHashCode > iHashCode2) {
                    return 1;
                }
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
        return super.equals(obj) && internalEqualityCheck(getPrimaryText(), headerElement.getPrimaryText()) && internalEqualityCheck(getLabel(), headerElement.getLabel()) && internalEqualityCheck(getSecondaryText(), headerElement.getSecondaryText()) && internalEqualityCheck(getTertiaryText(), headerElement.getTertiaryText()) && internalEqualityCheck(getImage(), headerElement.getImage()) && internalEqualityCheck(getOnSecondaryTextSelected(), headerElement.getOnSecondaryTextSelected());
    }

    public String getImage() {
        return this.image;
    }

    public String getLabel() {
        return this.label;
    }

    public List<Method> getOnSecondaryTextSelected() {
        return this.onSecondaryTextSelected;
    }

    public String getPrimaryText() {
        return this.primaryText;
    }

    public String getSecondaryText() {
        return this.secondaryText;
    }

    public String getTertiaryText() {
        return this.tertiaryText;
    }

    @Override // SOATemplateListInterface.v1_0.TemplateElement, SOACoreInterface.v1_0.SOAObject
    public int hashCode() {
        return internalHashCodeCompute(Integer.valueOf(super.hashCode()), Integer.valueOf(classNameHashCode), getPrimaryText(), getLabel(), getSecondaryText(), getTertiaryText(), getImage(), getOnSecondaryTextSelected());
    }

    public void setImage(String str) {
        this.image = str;
    }

    public void setLabel(String str) {
        this.label = str;
    }

    public void setOnSecondaryTextSelected(List<Method> list) {
        this.onSecondaryTextSelected = list;
    }

    public void setPrimaryText(String str) {
        this.primaryText = str;
    }

    public void setSecondaryText(String str) {
        this.secondaryText = str;
    }

    public void setTertiaryText(String str) {
        this.tertiaryText = str;
    }
}
