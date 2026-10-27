package Podcast.Touch.Certification.ListTemplateInterface.v1_0;

import SOACoreInterface.v1_0.Method;
import SOACoreInterface.v1_0.SOAObject;
import SOATemplateListInterface.v1_0.TemplateElement;
import java.util.Arrays;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public class ListItemElement extends TemplateElement {
    private static final int classNameHashCode = internalHashCodeCompute("Podcast.Touch.Certification.ListTemplateInterface.v1_0.ListItemElement");
    private List<Method> onItemSelected;
    private String primaryText;

    public static class Builder extends TemplateElement.Builder {
        protected List<Method> onItemSelected;
        protected String primaryText;

        public ListItemElement build() {
            ListItemElement listItemElement = new ListItemElement();
            populate(listItemElement);
            return listItemElement;
        }

        protected void populate(ListItemElement listItemElement) {
            super.populate((TemplateElement) listItemElement);
            listItemElement.setPrimaryText(this.primaryText);
            listItemElement.setOnItemSelected(this.onItemSelected);
        }

        public Builder withOnItemSelected(List<Method> list) {
            this.onItemSelected = list;
            return this;
        }

        public Builder withPrimaryText(String str) {
            this.primaryText = str;
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
        if (!(sOAObject instanceof ListItemElement)) {
            return 1;
        }
        ListItemElement listItemElement = (ListItemElement) sOAObject;
        String primaryText = getPrimaryText();
        String primaryText2 = listItemElement.getPrimaryText();
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
        List<Method> onItemSelected = getOnItemSelected();
        List<Method> onItemSelected2 = listItemElement.getOnItemSelected();
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
        return super.compareTo(sOAObject);
    }

    @Override // SOATemplateListInterface.v1_0.TemplateElement, SOACoreInterface.v1_0.SOAObject
    public boolean equals(Object obj) {
        if (!(obj instanceof ListItemElement)) {
            return false;
        }
        ListItemElement listItemElement = (ListItemElement) obj;
        return super.equals(obj) && internalEqualityCheck(getPrimaryText(), listItemElement.getPrimaryText()) && internalEqualityCheck(getOnItemSelected(), listItemElement.getOnItemSelected());
    }

    public List<Method> getOnItemSelected() {
        return this.onItemSelected;
    }

    public String getPrimaryText() {
        return this.primaryText;
    }

    @Override // SOATemplateListInterface.v1_0.TemplateElement, SOACoreInterface.v1_0.SOAObject
    public int hashCode() {
        return internalHashCodeCompute(Integer.valueOf(super.hashCode()), Integer.valueOf(classNameHashCode), getPrimaryText(), getOnItemSelected());
    }

    public void setOnItemSelected(List<Method> list) {
        this.onItemSelected = list;
    }

    public void setPrimaryText(String str) {
        this.primaryText = str;
    }
}
