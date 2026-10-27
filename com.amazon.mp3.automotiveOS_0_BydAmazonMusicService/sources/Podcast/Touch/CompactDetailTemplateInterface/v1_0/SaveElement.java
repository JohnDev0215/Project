package Podcast.Touch.CompactDetailTemplateInterface.v1_0;

import SOACoreInterface.v1_0.Method;
import SOACoreInterface.v1_0.SOAObject;
import SOATemplateListInterface.v1_0.TemplateElement;
import java.util.Arrays;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public class SaveElement extends TemplateElement {
    private static final int classNameHashCode = internalHashCodeCompute("Podcast.Touch.CompactDetailTemplateInterface.v1_0.SaveElement");
    private String id;
    private List<Method> onSave;
    private List<Method> onUnsave;

    public static class Builder extends TemplateElement.Builder {
        protected String id;
        protected List<Method> onSave;
        protected List<Method> onUnsave;

        public SaveElement build() {
            SaveElement saveElement = new SaveElement();
            populate(saveElement);
            return saveElement;
        }

        protected void populate(SaveElement saveElement) {
            super.populate((TemplateElement) saveElement);
            saveElement.setId(this.id);
            saveElement.setOnUnsave(this.onUnsave);
            saveElement.setOnSave(this.onSave);
        }

        public Builder withId(String str) {
            this.id = str;
            return this;
        }

        public Builder withOnSave(List<Method> list) {
            this.onSave = list;
            return this;
        }

        public Builder withOnUnsave(List<Method> list) {
            this.onUnsave = list;
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
        if (!(sOAObject instanceof SaveElement)) {
            return 1;
        }
        SaveElement saveElement = (SaveElement) sOAObject;
        String id = getId();
        String id2 = saveElement.getId();
        if (id != id2) {
            if (id == null) {
                return -1;
            }
            if (id2 == null) {
                return 1;
            }
            int iCompareTo = id.compareTo(id2);
            if (iCompareTo != 0) {
                return iCompareTo;
            }
        }
        List<Method> onUnsave = getOnUnsave();
        List<Method> onUnsave2 = saveElement.getOnUnsave();
        if (onUnsave != onUnsave2) {
            if (onUnsave == null) {
                return -1;
            }
            if (onUnsave2 == null) {
                return 1;
            }
            if (onUnsave instanceof Comparable) {
                int iCompareTo2 = ((Comparable) onUnsave).compareTo(onUnsave2);
                if (iCompareTo2 != 0) {
                    return iCompareTo2;
                }
            } else if (!onUnsave.equals(onUnsave2)) {
                int iHashCode = onUnsave.hashCode();
                int iHashCode2 = onUnsave2.hashCode();
                if (iHashCode < iHashCode2) {
                    return -1;
                }
                if (iHashCode > iHashCode2) {
                    return 1;
                }
            }
        }
        List<Method> onSave = getOnSave();
        List<Method> onSave2 = saveElement.getOnSave();
        if (onSave != onSave2) {
            if (onSave == null) {
                return -1;
            }
            if (onSave2 == null) {
                return 1;
            }
            if (onSave instanceof Comparable) {
                int iCompareTo3 = ((Comparable) onSave).compareTo(onSave2);
                if (iCompareTo3 != 0) {
                    return iCompareTo3;
                }
            } else if (!onSave.equals(onSave2)) {
                int iHashCode3 = onSave.hashCode();
                int iHashCode4 = onSave2.hashCode();
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
        if (!(obj instanceof SaveElement)) {
            return false;
        }
        SaveElement saveElement = (SaveElement) obj;
        return super.equals(obj) && internalEqualityCheck(getId(), saveElement.getId()) && internalEqualityCheck(getOnUnsave(), saveElement.getOnUnsave()) && internalEqualityCheck(getOnSave(), saveElement.getOnSave());
    }

    public String getId() {
        return this.id;
    }

    public List<Method> getOnSave() {
        return this.onSave;
    }

    public List<Method> getOnUnsave() {
        return this.onUnsave;
    }

    @Override // SOATemplateListInterface.v1_0.TemplateElement, SOACoreInterface.v1_0.SOAObject
    public int hashCode() {
        return internalHashCodeCompute(Integer.valueOf(super.hashCode()), Integer.valueOf(classNameHashCode), getId(), getOnUnsave(), getOnSave());
    }

    public void setId(String str) {
        this.id = str;
    }

    public void setOnSave(List<Method> list) {
        this.onSave = list;
    }

    public void setOnUnsave(List<Method> list) {
        this.onUnsave = list;
    }
}
