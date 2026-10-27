package Podcast.Touch.PTCTemplateInterface.v1_0;

import SOACoreInterface.v1_0.Method;
import SOACoreInterface.v1_0.SOAObject;
import SOATemplateListInterface.v1_0.TemplateElement;
import java.util.Arrays;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public class PillItemElement extends TemplateElement {
    private static final int classNameHashCode = internalHashCodeCompute("Podcast.Touch.PTCTemplateInterface.v1_0.PillItemElement");
    private String id;
    private List<Method> onItemDeselected;
    private List<Method> onItemSelected;
    private String text;

    public static class Builder extends TemplateElement.Builder {
        protected String id;
        protected List<Method> onItemDeselected;
        protected List<Method> onItemSelected;
        protected String text;

        public PillItemElement build() {
            PillItemElement pillItemElement = new PillItemElement();
            populate(pillItemElement);
            return pillItemElement;
        }

        protected void populate(PillItemElement pillItemElement) {
            super.populate((TemplateElement) pillItemElement);
            pillItemElement.setText(this.text);
            pillItemElement.setOnItemDeselected(this.onItemDeselected);
            pillItemElement.setOnItemSelected(this.onItemSelected);
            pillItemElement.setId(this.id);
        }

        public Builder withId(String str) {
            this.id = str;
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
        String text = getText();
        String text2 = pillItemElement.getText();
        if (text != text2) {
            if (text == null) {
                return -1;
            }
            if (text2 == null) {
                return 1;
            }
            int iCompareTo = text.compareTo(text2);
            if (iCompareTo != 0) {
                return iCompareTo;
            }
        }
        List<Method> onItemDeselected = getOnItemDeselected();
        List<Method> onItemDeselected2 = pillItemElement.getOnItemDeselected();
        if (onItemDeselected != onItemDeselected2) {
            if (onItemDeselected == null) {
                return -1;
            }
            if (onItemDeselected2 == null) {
                return 1;
            }
            if (onItemDeselected instanceof Comparable) {
                int iCompareTo2 = ((Comparable) onItemDeselected).compareTo(onItemDeselected2);
                if (iCompareTo2 != 0) {
                    return iCompareTo2;
                }
            } else if (!onItemDeselected.equals(onItemDeselected2)) {
                int iHashCode = onItemDeselected.hashCode();
                int iHashCode2 = onItemDeselected2.hashCode();
                if (iHashCode < iHashCode2) {
                    return -1;
                }
                if (iHashCode > iHashCode2) {
                    return 1;
                }
            }
        }
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
                int iCompareTo3 = ((Comparable) onItemSelected).compareTo(onItemSelected2);
                if (iCompareTo3 != 0) {
                    return iCompareTo3;
                }
            } else if (!onItemSelected.equals(onItemSelected2)) {
                int iHashCode3 = onItemSelected.hashCode();
                int iHashCode4 = onItemSelected2.hashCode();
                if (iHashCode3 < iHashCode4) {
                    return -1;
                }
                if (iHashCode3 > iHashCode4) {
                    return 1;
                }
            }
        }
        String id = getId();
        String id2 = pillItemElement.getId();
        if (id != id2) {
            if (id == null) {
                return -1;
            }
            if (id2 == null) {
                return 1;
            }
            int iCompareTo4 = id.compareTo(id2);
            if (iCompareTo4 != 0) {
                return iCompareTo4;
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
        return super.equals(obj) && internalEqualityCheck(getText(), pillItemElement.getText()) && internalEqualityCheck(getOnItemDeselected(), pillItemElement.getOnItemDeselected()) && internalEqualityCheck(getOnItemSelected(), pillItemElement.getOnItemSelected()) && internalEqualityCheck(getId(), pillItemElement.getId());
    }

    public String getId() {
        return this.id;
    }

    public List<Method> getOnItemDeselected() {
        return this.onItemDeselected;
    }

    public List<Method> getOnItemSelected() {
        return this.onItemSelected;
    }

    public String getText() {
        return this.text;
    }

    @Override // SOATemplateListInterface.v1_0.TemplateElement, SOACoreInterface.v1_0.SOAObject
    public int hashCode() {
        return internalHashCodeCompute(Integer.valueOf(super.hashCode()), Integer.valueOf(classNameHashCode), getText(), getOnItemDeselected(), getOnItemSelected(), getId());
    }

    public void setId(String str) {
        this.id = str;
    }

    public void setOnItemDeselected(List<Method> list) {
        this.onItemDeselected = list;
    }

    public void setOnItemSelected(List<Method> list) {
        this.onItemSelected = list;
    }

    public void setText(String str) {
        this.text = str;
    }
}
