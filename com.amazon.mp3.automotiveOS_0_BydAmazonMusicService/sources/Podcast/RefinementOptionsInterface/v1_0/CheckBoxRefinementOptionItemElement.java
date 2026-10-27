package Podcast.RefinementOptionsInterface.v1_0;

import SOACoreInterface.v1_0.Method;
import SOACoreInterface.v1_0.SOAObject;
import SOATemplateListInterface.v1_0.TemplateElement;
import java.util.Arrays;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public class CheckBoxRefinementOptionItemElement extends TemplateElement {
    private static final int classNameHashCode = internalHashCodeCompute("Podcast.RefinementOptionsInterface.v1_0.CheckBoxRefinementOptionItemElement");
    private String accessibilityLabel;
    private String id;
    private List<Method> onItemSelected;
    private String text;

    public static class Builder extends TemplateElement.Builder {
        protected String accessibilityLabel;
        protected String id;
        protected List<Method> onItemSelected;
        protected String text;

        public CheckBoxRefinementOptionItemElement build() {
            CheckBoxRefinementOptionItemElement checkBoxRefinementOptionItemElement = new CheckBoxRefinementOptionItemElement();
            populate(checkBoxRefinementOptionItemElement);
            return checkBoxRefinementOptionItemElement;
        }

        protected void populate(CheckBoxRefinementOptionItemElement checkBoxRefinementOptionItemElement) {
            super.populate((TemplateElement) checkBoxRefinementOptionItemElement);
            checkBoxRefinementOptionItemElement.setAccessibilityLabel(this.accessibilityLabel);
            checkBoxRefinementOptionItemElement.setId(this.id);
            checkBoxRefinementOptionItemElement.setText(this.text);
            checkBoxRefinementOptionItemElement.setOnItemSelected(this.onItemSelected);
        }

        public Builder withAccessibilityLabel(String str) {
            this.accessibilityLabel = str;
            return this;
        }

        public Builder withId(String str) {
            this.id = str;
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
        if (!(sOAObject instanceof CheckBoxRefinementOptionItemElement)) {
            return 1;
        }
        CheckBoxRefinementOptionItemElement checkBoxRefinementOptionItemElement = (CheckBoxRefinementOptionItemElement) sOAObject;
        String accessibilityLabel = getAccessibilityLabel();
        String accessibilityLabel2 = checkBoxRefinementOptionItemElement.getAccessibilityLabel();
        if (accessibilityLabel != accessibilityLabel2) {
            if (accessibilityLabel == null) {
                return -1;
            }
            if (accessibilityLabel2 == null) {
                return 1;
            }
            int iCompareTo = accessibilityLabel.compareTo(accessibilityLabel2);
            if (iCompareTo != 0) {
                return iCompareTo;
            }
        }
        String id = getId();
        String id2 = checkBoxRefinementOptionItemElement.getId();
        if (id != id2) {
            if (id == null) {
                return -1;
            }
            if (id2 == null) {
                return 1;
            }
            int iCompareTo2 = id.compareTo(id2);
            if (iCompareTo2 != 0) {
                return iCompareTo2;
            }
        }
        String text = getText();
        String text2 = checkBoxRefinementOptionItemElement.getText();
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
        List<Method> onItemSelected = getOnItemSelected();
        List<Method> onItemSelected2 = checkBoxRefinementOptionItemElement.getOnItemSelected();
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
        return super.compareTo(sOAObject);
    }

    @Override // SOATemplateListInterface.v1_0.TemplateElement, SOACoreInterface.v1_0.SOAObject
    public boolean equals(Object obj) {
        if (!(obj instanceof CheckBoxRefinementOptionItemElement)) {
            return false;
        }
        CheckBoxRefinementOptionItemElement checkBoxRefinementOptionItemElement = (CheckBoxRefinementOptionItemElement) obj;
        return super.equals(obj) && internalEqualityCheck(getAccessibilityLabel(), checkBoxRefinementOptionItemElement.getAccessibilityLabel()) && internalEqualityCheck(getId(), checkBoxRefinementOptionItemElement.getId()) && internalEqualityCheck(getText(), checkBoxRefinementOptionItemElement.getText()) && internalEqualityCheck(getOnItemSelected(), checkBoxRefinementOptionItemElement.getOnItemSelected());
    }

    public String getAccessibilityLabel() {
        return this.accessibilityLabel;
    }

    public String getId() {
        return this.id;
    }

    public List<Method> getOnItemSelected() {
        return this.onItemSelected;
    }

    public String getText() {
        return this.text;
    }

    @Override // SOATemplateListInterface.v1_0.TemplateElement, SOACoreInterface.v1_0.SOAObject
    public int hashCode() {
        return internalHashCodeCompute(Integer.valueOf(super.hashCode()), Integer.valueOf(classNameHashCode), getAccessibilityLabel(), getId(), getText(), getOnItemSelected());
    }

    public void setAccessibilityLabel(String str) {
        this.accessibilityLabel = str;
    }

    public void setId(String str) {
        this.id = str;
    }

    public void setOnItemSelected(List<Method> list) {
        this.onItemSelected = list;
    }

    public void setText(String str) {
        this.text = str;
    }
}
