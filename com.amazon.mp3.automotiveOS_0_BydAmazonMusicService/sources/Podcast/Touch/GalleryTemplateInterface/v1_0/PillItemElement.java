package Podcast.Touch.GalleryTemplateInterface.v1_0;

import SOACoreInterface.v1_0.Method;
import SOACoreInterface.v1_0.SOAObject;
import SOATemplateListInterface.v1_0.TemplateElement;
import java.util.Arrays;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public class PillItemElement extends TemplateElement {
    private static final int classNameHashCode = internalHashCodeCompute("Podcast.Touch.GalleryTemplateInterface.v1_0.PillItemElement");
    private List<Method> onItemSelected;
    private String text;

    public static class Builder extends TemplateElement.Builder {
        protected List<Method> onItemSelected;
        protected String text;

        public PillItemElement build() {
            PillItemElement pillItemElement = new PillItemElement();
            populate(pillItemElement);
            return pillItemElement;
        }

        protected void populate(PillItemElement pillItemElement) {
            super.populate((TemplateElement) pillItemElement);
            pillItemElement.setOnItemSelected(this.onItemSelected);
            pillItemElement.setText(this.text);
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
        if (!(sOAObject instanceof PillItemElement)) {
            return 1;
        }
        PillItemElement pillItemElement = (PillItemElement) sOAObject;
        List<Method> onItemSelected = getOnItemSelected();
        List<Method> onItemSelected2 = pillItemElement.getOnItemSelected();
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
        String text2 = pillItemElement.getText();
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
        return super.compareTo(sOAObject);
    }

    @Override // SOATemplateListInterface.v1_0.TemplateElement, SOACoreInterface.v1_0.SOAObject
    public boolean equals(Object obj) {
        if (!(obj instanceof PillItemElement)) {
            return false;
        }
        PillItemElement pillItemElement = (PillItemElement) obj;
        return super.equals(obj) && internalEqualityCheck(getOnItemSelected(), pillItemElement.getOnItemSelected()) && internalEqualityCheck(getText(), pillItemElement.getText());
    }

    public List<Method> getOnItemSelected() {
        return this.onItemSelected;
    }

    public String getText() {
        return this.text;
    }

    @Override // SOATemplateListInterface.v1_0.TemplateElement, SOACoreInterface.v1_0.SOAObject
    public int hashCode() {
        return internalHashCodeCompute(Integer.valueOf(super.hashCode()), Integer.valueOf(classNameHashCode), getOnItemSelected(), getText());
    }

    public void setOnItemSelected(List<Method> list) {
        this.onItemSelected = list;
    }

    public void setText(String str) {
        this.text = str;
    }
}
