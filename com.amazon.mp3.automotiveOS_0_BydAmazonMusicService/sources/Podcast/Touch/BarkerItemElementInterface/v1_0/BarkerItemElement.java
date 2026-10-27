package Podcast.Touch.BarkerItemElementInterface.v1_0;

import SOACoreInterface.v1_0.Method;
import SOACoreInterface.v1_0.SOAObject;
import SOATemplateListInterface.v1_0.TemplateElement;
import java.util.Arrays;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public class BarkerItemElement extends TemplateElement {
    private static final int classNameHashCode = internalHashCodeCompute("Podcast.Touch.BarkerItemElementInterface.v1_0.BarkerItemElement");
    private String backgroundImageLarge;
    private String backgroundImageSmall;
    private List<Method> onItemSelected;
    private String title;

    public static class Builder extends TemplateElement.Builder {
        protected String backgroundImageLarge;
        protected String backgroundImageSmall;
        protected List<Method> onItemSelected;
        protected String title;

        public BarkerItemElement build() {
            BarkerItemElement barkerItemElement = new BarkerItemElement();
            populate(barkerItemElement);
            return barkerItemElement;
        }

        protected void populate(BarkerItemElement barkerItemElement) {
            super.populate((TemplateElement) barkerItemElement);
            barkerItemElement.setBackgroundImageSmall(this.backgroundImageSmall);
            barkerItemElement.setTitle(this.title);
            barkerItemElement.setOnItemSelected(this.onItemSelected);
            barkerItemElement.setBackgroundImageLarge(this.backgroundImageLarge);
        }

        public Builder withBackgroundImageLarge(String str) {
            this.backgroundImageLarge = str;
            return this;
        }

        public Builder withBackgroundImageSmall(String str) {
            this.backgroundImageSmall = str;
            return this;
        }

        public Builder withOnItemSelected(List<Method> list) {
            this.onItemSelected = list;
            return this;
        }

        public Builder withTitle(String str) {
            this.title = str;
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
        String title = getTitle();
        String title2 = barkerItemElement.getTitle();
        if (title != title2) {
            if (title == null) {
                return -1;
            }
            if (title2 == null) {
                return 1;
            }
            int iCompareTo2 = title.compareTo(title2);
            if (iCompareTo2 != 0) {
                return iCompareTo2;
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
                int iCompareTo3 = ((Comparable) onItemSelected).compareTo(onItemSelected2);
                if (iCompareTo3 != 0) {
                    return iCompareTo3;
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
            int iCompareTo4 = backgroundImageLarge.compareTo(backgroundImageLarge2);
            if (iCompareTo4 != 0) {
                return iCompareTo4;
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
        return super.equals(obj) && internalEqualityCheck(getBackgroundImageSmall(), barkerItemElement.getBackgroundImageSmall()) && internalEqualityCheck(getTitle(), barkerItemElement.getTitle()) && internalEqualityCheck(getOnItemSelected(), barkerItemElement.getOnItemSelected()) && internalEqualityCheck(getBackgroundImageLarge(), barkerItemElement.getBackgroundImageLarge());
    }

    public String getBackgroundImageLarge() {
        return this.backgroundImageLarge;
    }

    public String getBackgroundImageSmall() {
        return this.backgroundImageSmall;
    }

    public List<Method> getOnItemSelected() {
        return this.onItemSelected;
    }

    public String getTitle() {
        return this.title;
    }

    @Override // SOATemplateListInterface.v1_0.TemplateElement, SOACoreInterface.v1_0.SOAObject
    public int hashCode() {
        return internalHashCodeCompute(Integer.valueOf(super.hashCode()), Integer.valueOf(classNameHashCode), getBackgroundImageSmall(), getTitle(), getOnItemSelected(), getBackgroundImageLarge());
    }

    public void setBackgroundImageLarge(String str) {
        this.backgroundImageLarge = str;
    }

    public void setBackgroundImageSmall(String str) {
        this.backgroundImageSmall = str;
    }

    public void setOnItemSelected(List<Method> list) {
        this.onItemSelected = list;
    }

    public void setTitle(String str) {
        this.title = str;
    }
}
