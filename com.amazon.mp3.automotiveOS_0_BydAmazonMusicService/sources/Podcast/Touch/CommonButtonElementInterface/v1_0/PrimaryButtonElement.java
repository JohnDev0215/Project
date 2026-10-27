package Podcast.Touch.CommonButtonElementInterface.v1_0;

import SOACoreInterface.v1_0.Method;
import SOACoreInterface.v1_0.SOAObject;
import SOATemplateListInterface.v1_0.TemplateElement;
import java.util.Arrays;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public class PrimaryButtonElement extends TemplateElement {
    private static final int classNameHashCode = internalHashCodeCompute("Podcast.Touch.CommonButtonElementInterface.v1_0.PrimaryButtonElement");
    private boolean iconEnabled;
    private List<Method> onItemSelected;
    private String text;

    public static class Builder extends TemplateElement.Builder {
        protected boolean iconEnabled;
        protected List<Method> onItemSelected;
        protected String text;

        public PrimaryButtonElement build() {
            PrimaryButtonElement primaryButtonElement = new PrimaryButtonElement();
            populate(primaryButtonElement);
            return primaryButtonElement;
        }

        protected void populate(PrimaryButtonElement primaryButtonElement) {
            super.populate((TemplateElement) primaryButtonElement);
            primaryButtonElement.setOnItemSelected(this.onItemSelected);
            primaryButtonElement.setText(this.text);
            primaryButtonElement.setIconEnabled(this.iconEnabled);
        }

        public Builder withIconEnabled(boolean z) {
            this.iconEnabled = z;
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
        if (!(sOAObject instanceof PrimaryButtonElement)) {
            return 1;
        }
        PrimaryButtonElement primaryButtonElement = (PrimaryButtonElement) sOAObject;
        List<Method> onItemSelected = getOnItemSelected();
        List<Method> onItemSelected2 = primaryButtonElement.getOnItemSelected();
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
        String text = getText();
        String text2 = primaryButtonElement.getText();
        if (text != text2) {
            if (text == null) {
                return -1;
            }
            if (text2 == null) {
                return 1;
            }
            int iCompareTo2 = text.compareTo(text2);
            if (iCompareTo2 != 0) {
                return iCompareTo2;
            }
        }
        if (!isIconEnabled() && primaryButtonElement.isIconEnabled()) {
            return -1;
        }
        if (!isIconEnabled() || primaryButtonElement.isIconEnabled()) {
            return super.compareTo(sOAObject);
        }
        return 1;
    }

    @Override // SOATemplateListInterface.v1_0.TemplateElement, SOACoreInterface.v1_0.SOAObject
    public boolean equals(Object obj) {
        if (!(obj instanceof PrimaryButtonElement)) {
            return false;
        }
        PrimaryButtonElement primaryButtonElement = (PrimaryButtonElement) obj;
        return super.equals(obj) && internalEqualityCheck(getOnItemSelected(), primaryButtonElement.getOnItemSelected()) && internalEqualityCheck(getText(), primaryButtonElement.getText()) && internalEqualityCheck(Boolean.valueOf(isIconEnabled()), Boolean.valueOf(primaryButtonElement.isIconEnabled()));
    }

    public List<Method> getOnItemSelected() {
        return this.onItemSelected;
    }

    public String getText() {
        return this.text;
    }

    @Override // SOATemplateListInterface.v1_0.TemplateElement, SOACoreInterface.v1_0.SOAObject
    public int hashCode() {
        return internalHashCodeCompute(Integer.valueOf(super.hashCode()), Integer.valueOf(classNameHashCode), getOnItemSelected(), getText(), Boolean.valueOf(isIconEnabled()));
    }

    public boolean isIconEnabled() {
        return this.iconEnabled;
    }

    public void setIconEnabled(boolean z) {
        this.iconEnabled = z;
    }

    public void setOnItemSelected(List<Method> list) {
        this.onItemSelected = list;
    }

    public void setText(String str) {
        this.text = str;
    }
}
