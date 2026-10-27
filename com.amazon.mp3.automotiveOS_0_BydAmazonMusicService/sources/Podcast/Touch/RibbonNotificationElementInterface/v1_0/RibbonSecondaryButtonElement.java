package Podcast.Touch.RibbonNotificationElementInterface.v1_0;

import SOACoreInterface.v1_0.Method;
import SOACoreInterface.v1_0.SOAObject;
import SOATemplateListInterface.v1_0.TemplateElement;
import java.util.Arrays;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public class RibbonSecondaryButtonElement extends TemplateElement {
    private static final int classNameHashCode = internalHashCodeCompute("Podcast.Touch.RibbonNotificationElementInterface.v1_0.RibbonSecondaryButtonElement");
    private String icon;
    private List<Method> onItemSelected;
    private String text;

    public static class Builder extends TemplateElement.Builder {
        protected String icon;
        protected List<Method> onItemSelected;
        protected String text;

        public RibbonSecondaryButtonElement build() {
            RibbonSecondaryButtonElement ribbonSecondaryButtonElement = new RibbonSecondaryButtonElement();
            populate(ribbonSecondaryButtonElement);
            return ribbonSecondaryButtonElement;
        }

        protected void populate(RibbonSecondaryButtonElement ribbonSecondaryButtonElement) {
            super.populate((TemplateElement) ribbonSecondaryButtonElement);
            ribbonSecondaryButtonElement.setOnItemSelected(this.onItemSelected);
            ribbonSecondaryButtonElement.setIcon(this.icon);
            ribbonSecondaryButtonElement.setText(this.text);
        }

        public Builder withIcon(String str) {
            this.icon = str;
            return this;
        }

        public Builder withOnItemSelected(List<Method> list) {
            this.onItemSelected = list;
            return this;
        }

        public Builder withText(String str) {
            this.text = str;
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
        if (!(sOAObject instanceof RibbonSecondaryButtonElement)) {
            return 1;
        }
        RibbonSecondaryButtonElement ribbonSecondaryButtonElement = (RibbonSecondaryButtonElement) sOAObject;
        List<Method> onItemSelected = getOnItemSelected();
        List<Method> onItemSelected2 = ribbonSecondaryButtonElement.getOnItemSelected();
        if (onItemSelected != onItemSelected2) {
            if (onItemSelected == null) {
                return -1;
            }
            if (onItemSelected2 == null) {
                return 1;
            }
            if (onItemSelected instanceof Comparable) {
                int iCompareTo = ((Comparable) onItemSelected).compareTo(onItemSelected2);
                if (iCompareTo != 0) {
                    return iCompareTo;
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
        String icon = getIcon();
        String icon2 = ribbonSecondaryButtonElement.getIcon();
        if (icon != icon2) {
            if (icon == null) {
                return -1;
            }
            if (icon2 == null) {
                return 1;
            }
            int iCompareTo2 = icon.compareTo(icon2);
            if (iCompareTo2 != 0) {
                return iCompareTo2;
            }
        }
        String text = getText();
        String text2 = ribbonSecondaryButtonElement.getText();
        if (text != text2) {
            if (text == null) {
                return -1;
            }
            if (text2 == null) {
                return 1;
            }
            int iCompareTo3 = text.compareTo(text2);
            if (iCompareTo3 != 0) {
                return iCompareTo3;
            }
        }
        return super.compareTo(sOAObject);
    }

    @Override // SOATemplateListInterface.v1_0.TemplateElement, SOACoreInterface.v1_0.SOAObject
    public boolean equals(Object obj) {
        if (!(obj instanceof RibbonSecondaryButtonElement)) {
            return false;
        }
        RibbonSecondaryButtonElement ribbonSecondaryButtonElement = (RibbonSecondaryButtonElement) obj;
        return super.equals(obj) && internalEqualityCheck(getOnItemSelected(), ribbonSecondaryButtonElement.getOnItemSelected()) && internalEqualityCheck(getIcon(), ribbonSecondaryButtonElement.getIcon()) && internalEqualityCheck(getText(), ribbonSecondaryButtonElement.getText());
    }

    public String getIcon() {
        return this.icon;
    }

    public List<Method> getOnItemSelected() {
        return this.onItemSelected;
    }

    public String getText() {
        return this.text;
    }

    @Override // SOATemplateListInterface.v1_0.TemplateElement, SOACoreInterface.v1_0.SOAObject
    public int hashCode() {
        return internalHashCodeCompute(Integer.valueOf(super.hashCode()), Integer.valueOf(classNameHashCode), getOnItemSelected(), getIcon(), getText());
    }

    public void setIcon(String str) {
        this.icon = str;
    }

    public void setOnItemSelected(List<Method> list) {
        this.onItemSelected = list;
    }

    public void setText(String str) {
        this.text = str;
    }
}
