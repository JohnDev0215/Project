package Podcast.Touch.PTCTemplateInterface.v1_0;

import SOACoreInterface.v1_0.Method;
import SOACoreInterface.v1_0.SOAObject;
import SOATemplateListInterface.v1_0.TemplateElement;
import java.util.Arrays;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public class PositiveButtonElement extends TemplateElement {
    private static final int classNameHashCode = internalHashCodeCompute("Podcast.Touch.PTCTemplateInterface.v1_0.PositiveButtonElement");
    private String deselectedText;
    private List<Method> onItemDeselected;
    private List<Method> onItemSelected;
    private String selectedText;

    public static class Builder extends TemplateElement.Builder {
        protected String deselectedText;
        protected List<Method> onItemDeselected;
        protected List<Method> onItemSelected;
        protected String selectedText;

        public PositiveButtonElement build() {
            PositiveButtonElement positiveButtonElement = new PositiveButtonElement();
            populate(positiveButtonElement);
            return positiveButtonElement;
        }

        protected void populate(PositiveButtonElement positiveButtonElement) {
            super.populate((TemplateElement) positiveButtonElement);
            positiveButtonElement.setDeselectedText(this.deselectedText);
            positiveButtonElement.setOnItemSelected(this.onItemSelected);
            positiveButtonElement.setSelectedText(this.selectedText);
            positiveButtonElement.setOnItemDeselected(this.onItemDeselected);
        }

        public Builder withDeselectedText(String str) {
            this.deselectedText = str;
            return this;
        }

        public Builder withOnItemDeselected(List<Method> list) {
            this.onItemDeselected = list;
            return this;
        }

        public Builder withOnItemSelected(List<Method> list) {
            this.onItemSelected = list;
            return this;
        }

        public Builder withSelectedText(String str) {
            this.selectedText = str;
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
        if (!(sOAObject instanceof PositiveButtonElement)) {
            return 1;
        }
        PositiveButtonElement positiveButtonElement = (PositiveButtonElement) sOAObject;
        String deselectedText = getDeselectedText();
        String deselectedText2 = positiveButtonElement.getDeselectedText();
        if (deselectedText != deselectedText2) {
            if (deselectedText == null) {
                return -1;
            }
            if (deselectedText2 == null) {
                return 1;
            }
            int iCompareTo = deselectedText.compareTo(deselectedText2);
            if (iCompareTo != 0) {
                return iCompareTo;
            }
        }
        List<Method> onItemSelected = getOnItemSelected();
        List<Method> onItemSelected2 = positiveButtonElement.getOnItemSelected();
        if (onItemSelected != onItemSelected2) {
            if (onItemSelected == null) {
                return -1;
            }
            if (onItemSelected2 == null) {
                return 1;
            }
            if (onItemSelected instanceof Comparable) {
                int iCompareTo2 = ((Comparable) onItemSelected).compareTo(onItemSelected2);
                if (iCompareTo2 != 0) {
                    return iCompareTo2;
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
        String selectedText = getSelectedText();
        String selectedText2 = positiveButtonElement.getSelectedText();
        if (selectedText != selectedText2) {
            if (selectedText == null) {
                return -1;
            }
            if (selectedText2 == null) {
                return 1;
            }
            int iCompareTo3 = selectedText.compareTo(selectedText2);
            if (iCompareTo3 != 0) {
                return iCompareTo3;
            }
        }
        List<Method> onItemDeselected = getOnItemDeselected();
        List<Method> onItemDeselected2 = positiveButtonElement.getOnItemDeselected();
        if (onItemDeselected != onItemDeselected2) {
            if (onItemDeselected == null) {
                return -1;
            }
            if (onItemDeselected2 == null) {
                return 1;
            }
            if (onItemDeselected instanceof Comparable) {
                int iCompareTo4 = ((Comparable) onItemDeselected).compareTo(onItemDeselected2);
                if (iCompareTo4 != 0) {
                    return iCompareTo4;
                }
            } else if (!onItemDeselected.equals(onItemDeselected2)) {
                int iHashCode3 = onItemDeselected.hashCode();
                int iHashCode4 = onItemDeselected2.hashCode();
                if (iHashCode3 < iHashCode4) {
                    return -1;
                }
                if (iHashCode3 > iHashCode4) {
                    return 1;
                }
            }
        }
        return super.compareTo(sOAObject);
    }

    @Override // SOATemplateListInterface.v1_0.TemplateElement, SOACoreInterface.v1_0.SOAObject
    public boolean equals(Object obj) {
        if (!(obj instanceof PositiveButtonElement)) {
            return false;
        }
        PositiveButtonElement positiveButtonElement = (PositiveButtonElement) obj;
        return super.equals(obj) && internalEqualityCheck(getDeselectedText(), positiveButtonElement.getDeselectedText()) && internalEqualityCheck(getOnItemSelected(), positiveButtonElement.getOnItemSelected()) && internalEqualityCheck(getSelectedText(), positiveButtonElement.getSelectedText()) && internalEqualityCheck(getOnItemDeselected(), positiveButtonElement.getOnItemDeselected());
    }

    public String getDeselectedText() {
        return this.deselectedText;
    }

    public List<Method> getOnItemDeselected() {
        return this.onItemDeselected;
    }

    public List<Method> getOnItemSelected() {
        return this.onItemSelected;
    }

    public String getSelectedText() {
        return this.selectedText;
    }

    @Override // SOATemplateListInterface.v1_0.TemplateElement, SOACoreInterface.v1_0.SOAObject
    public int hashCode() {
        return internalHashCodeCompute(Integer.valueOf(super.hashCode()), Integer.valueOf(classNameHashCode), getDeselectedText(), getOnItemSelected(), getSelectedText(), getOnItemDeselected());
    }

    public void setDeselectedText(String str) {
        this.deselectedText = str;
    }

    public void setOnItemDeselected(List<Method> list) {
        this.onItemDeselected = list;
    }

    public void setOnItemSelected(List<Method> list) {
        this.onItemSelected = list;
    }

    public void setSelectedText(String str) {
        this.selectedText = str;
    }
}
