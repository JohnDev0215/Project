package Podcast.Touch.GalleryTemplateInterface.v1_0;

import SOACoreInterface.v1_0.Method;
import SOACoreInterface.v1_0.SOAObject;
import SOATemplateListInterface.v1_0.TemplateElement;
import java.util.Arrays;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public class BarkerItemElement extends TemplateElement {
    private static final int classNameHashCode = internalHashCodeCompute("Podcast.Touch.GalleryTemplateInterface.v1_0.BarkerItemElement");
    private String backgroundImageLarge;
    private String backgroundImageSmall;
    private String image;
    private String label;
    private List<Method> onItemSelected;
    private String primaryText;
    private String secondaryText;

    public static class Builder extends TemplateElement.Builder {
        protected String backgroundImageLarge;
        protected String backgroundImageSmall;
        protected String image;
        protected String label;
        protected List<Method> onItemSelected;
        protected String primaryText;
        protected String secondaryText;

        public BarkerItemElement build() {
            BarkerItemElement barkerItemElement = new BarkerItemElement();
            populate(barkerItemElement);
            return barkerItemElement;
        }

        protected void populate(BarkerItemElement barkerItemElement) {
            super.populate((TemplateElement) barkerItemElement);
            barkerItemElement.setBackgroundImageSmall(this.backgroundImageSmall);
            barkerItemElement.setImage(this.image);
            barkerItemElement.setSecondaryText(this.secondaryText);
            barkerItemElement.setOnItemSelected(this.onItemSelected);
            barkerItemElement.setBackgroundImageLarge(this.backgroundImageLarge);
            barkerItemElement.setLabel(this.label);
            barkerItemElement.setPrimaryText(this.primaryText);
        }

        public Builder withBackgroundImageLarge(String str) {
            this.backgroundImageLarge = str;
            return this;
        }

        public Builder withBackgroundImageSmall(String str) {
            this.backgroundImageSmall = str;
            return this;
        }

        public Builder withImage(String str) {
            this.image = str;
            return this;
        }

        public Builder withLabel(String str) {
            this.label = str;
            return this;
        }

        public Builder withOnItemSelected(List<Method> list) {
            this.onItemSelected = list;
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
        if (!(sOAObject instanceof BarkerItemElement)) {
            return 1;
        }
        BarkerItemElement barkerItemElement = (BarkerItemElement) sOAObject;
        String backgroundImageSmall = getBackgroundImageSmall();
        String backgroundImageSmall2 = barkerItemElement.getBackgroundImageSmall();
        if (backgroundImageSmall != backgroundImageSmall2) {
            if (backgroundImageSmall == null) {
                return -1;
            }
            if (backgroundImageSmall2 == null) {
                return 1;
            }
            int iCompareTo = backgroundImageSmall.compareTo(backgroundImageSmall2);
            if (iCompareTo != 0) {
                return iCompareTo;
            }
        }
        String image = getImage();
        String image2 = barkerItemElement.getImage();
        if (image != image2) {
            if (image == null) {
                return -1;
            }
            if (image2 == null) {
                return 1;
            }
            int iCompareTo2 = image.compareTo(image2);
            if (iCompareTo2 != 0) {
                return iCompareTo2;
            }
        }
        String secondaryText = getSecondaryText();
        String secondaryText2 = barkerItemElement.getSecondaryText();
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
        List<Method> onItemSelected = getOnItemSelected();
        List<Method> onItemSelected2 = barkerItemElement.getOnItemSelected();
        if (onItemSelected != onItemSelected2) {
            if (onItemSelected == null) {
                return -1;
            }
            if (onItemSelected2 == null) {
                return 1;
            }
            if (onItemSelected instanceof Comparable) {
                int iCompareTo4 = ((Comparable) onItemSelected).compareTo(onItemSelected2);
                if (iCompareTo4 != 0) {
                    return iCompareTo4;
                }
            } else if (!onItemSelected.equals(onItemSelected2)) {
                int iHashCode = onItemSelected.hashCode();
                int iHashCode2 = onItemSelected2.hashCode();
                if (iHashCode < iHashCode2) {
                    return -1;
                }
                if (iHashCode > iHashCode2) {
                    return 1;
                }
            }
        }
        String backgroundImageLarge = getBackgroundImageLarge();
        String backgroundImageLarge2 = barkerItemElement.getBackgroundImageLarge();
        if (backgroundImageLarge != backgroundImageLarge2) {
            if (backgroundImageLarge == null) {
                return -1;
            }
            if (backgroundImageLarge2 == null) {
                return 1;
            }
            int iCompareTo5 = backgroundImageLarge.compareTo(backgroundImageLarge2);
            if (iCompareTo5 != 0) {
                return iCompareTo5;
            }
        }
        String label = getLabel();
        String label2 = barkerItemElement.getLabel();
        if (label != label2) {
            if (label == null) {
                return -1;
            }
            if (label2 == null) {
                return 1;
            }
            int iCompareTo6 = label.compareTo(label2);
            if (iCompareTo6 != 0) {
                return iCompareTo6;
            }
        }
        String primaryText = getPrimaryText();
        String primaryText2 = barkerItemElement.getPrimaryText();
        if (primaryText != primaryText2) {
            if (primaryText == null) {
                return -1;
            }
            if (primaryText2 == null) {
                return 1;
            }
            int iCompareTo7 = primaryText.compareTo(primaryText2);
            if (iCompareTo7 != 0) {
                return iCompareTo7;
            }
        }
        return super.compareTo(sOAObject);
    }

    @Override // SOATemplateListInterface.v1_0.TemplateElement, SOACoreInterface.v1_0.SOAObject
    public boolean equals(Object obj) {
        if (!(obj instanceof BarkerItemElement)) {
            return false;
        }
        BarkerItemElement barkerItemElement = (BarkerItemElement) obj;
        return super.equals(obj) && internalEqualityCheck(getBackgroundImageSmall(), barkerItemElement.getBackgroundImageSmall()) && internalEqualityCheck(getImage(), barkerItemElement.getImage()) && internalEqualityCheck(getSecondaryText(), barkerItemElement.getSecondaryText()) && internalEqualityCheck(getOnItemSelected(), barkerItemElement.getOnItemSelected()) && internalEqualityCheck(getBackgroundImageLarge(), barkerItemElement.getBackgroundImageLarge()) && internalEqualityCheck(getLabel(), barkerItemElement.getLabel()) && internalEqualityCheck(getPrimaryText(), barkerItemElement.getPrimaryText());
    }

    public String getBackgroundImageLarge() {
        return this.backgroundImageLarge;
    }

    public String getBackgroundImageSmall() {
        return this.backgroundImageSmall;
    }

    public String getImage() {
        return this.image;
    }

    public String getLabel() {
        return this.label;
    }

    public List<Method> getOnItemSelected() {
        return this.onItemSelected;
    }

    public String getPrimaryText() {
        return this.primaryText;
    }

    public String getSecondaryText() {
        return this.secondaryText;
    }

    @Override // SOATemplateListInterface.v1_0.TemplateElement, SOACoreInterface.v1_0.SOAObject
    public int hashCode() {
        return internalHashCodeCompute(Integer.valueOf(super.hashCode()), Integer.valueOf(classNameHashCode), getBackgroundImageSmall(), getImage(), getSecondaryText(), getOnItemSelected(), getBackgroundImageLarge(), getLabel(), getPrimaryText());
    }

    public void setBackgroundImageLarge(String str) {
        this.backgroundImageLarge = str;
    }

    public void setBackgroundImageSmall(String str) {
        this.backgroundImageSmall = str;
    }

    public void setImage(String str) {
        this.image = str;
    }

    public void setLabel(String str) {
        this.label = str;
    }

    public void setOnItemSelected(List<Method> list) {
        this.onItemSelected = list;
    }

    public void setPrimaryText(String str) {
        this.primaryText = str;
    }

    public void setSecondaryText(String str) {
        this.secondaryText = str;
    }
}
