package Podcast.Touch.CardNowPlayingTemplateInterface.v1_0;

import SOACoreInterface.v1_0.Method;
import SOACoreInterface.v1_0.SOAObject;
import SOATemplateListInterface.v1_0.TemplateElement;
import java.util.Arrays;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public class SaveButtonElement extends TemplateElement {
    private static final int classNameHashCode = internalHashCodeCompute("Podcast.Touch.CardNowPlayingTemplateInterface.v1_0.SaveButtonElement");
    private String id;
    private List<Method> onSave;
    private List<Method> onUnsave;
    private String saveText;
    private String unsaveText;

    public static class Builder extends TemplateElement.Builder {
        protected String id;
        protected List<Method> onSave;
        protected List<Method> onUnsave;
        protected String saveText;
        protected String unsaveText;

        public SaveButtonElement build() {
            SaveButtonElement saveButtonElement = new SaveButtonElement();
            populate(saveButtonElement);
            return saveButtonElement;
        }

        protected void populate(SaveButtonElement saveButtonElement) {
            super.populate((TemplateElement) saveButtonElement);
            saveButtonElement.setSaveText(this.saveText);
            saveButtonElement.setId(this.id);
            saveButtonElement.setUnsaveText(this.unsaveText);
            saveButtonElement.setOnUnsave(this.onUnsave);
            saveButtonElement.setOnSave(this.onSave);
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

        public Builder withSaveText(String str) {
            this.saveText = str;
            return this;
        }

        public Builder withUnsaveText(String str) {
            this.unsaveText = str;
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
        if (!(sOAObject instanceof SaveButtonElement)) {
            return 1;
        }
        SaveButtonElement saveButtonElement = (SaveButtonElement) sOAObject;
        String saveText = getSaveText();
        String saveText2 = saveButtonElement.getSaveText();
        if (saveText != saveText2) {
            if (saveText == null) {
                return -1;
            }
            if (saveText2 == null) {
                return 1;
            }
            int iCompareTo = saveText.compareTo(saveText2);
            if (iCompareTo != 0) {
                return iCompareTo;
            }
        }
        String id = getId();
        String id2 = saveButtonElement.getId();
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
        String unsaveText = getUnsaveText();
        String unsaveText2 = saveButtonElement.getUnsaveText();
        if (unsaveText != unsaveText2) {
            if (unsaveText == null) {
                return -1;
            }
            if (unsaveText2 == null) {
                return 1;
            }
            int iCompareTo3 = unsaveText.compareTo(unsaveText2);
            if (iCompareTo3 != 0) {
                return iCompareTo3;
            }
        }
        List<Method> onUnsave = getOnUnsave();
        List<Method> onUnsave2 = saveButtonElement.getOnUnsave();
        if (onUnsave != onUnsave2) {
            if (onUnsave == null) {
                return -1;
            }
            if (onUnsave2 == null) {
                return 1;
            }
            if (onUnsave instanceof Comparable) {
                int iCompareTo4 = ((Comparable) onUnsave).compareTo(onUnsave2);
                if (iCompareTo4 != 0) {
                    return iCompareTo4;
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
        List<Method> onSave2 = saveButtonElement.getOnSave();
        if (onSave != onSave2) {
            if (onSave == null) {
                return -1;
            }
            if (onSave2 == null) {
                return 1;
            }
            if (onSave instanceof Comparable) {
                int iCompareTo5 = ((Comparable) onSave).compareTo(onSave2);
                if (iCompareTo5 != 0) {
                    return iCompareTo5;
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
        if (!(obj instanceof SaveButtonElement)) {
            return false;
        }
        SaveButtonElement saveButtonElement = (SaveButtonElement) obj;
        return super.equals(obj) && internalEqualityCheck(getSaveText(), saveButtonElement.getSaveText()) && internalEqualityCheck(getId(), saveButtonElement.getId()) && internalEqualityCheck(getUnsaveText(), saveButtonElement.getUnsaveText()) && internalEqualityCheck(getOnUnsave(), saveButtonElement.getOnUnsave()) && internalEqualityCheck(getOnSave(), saveButtonElement.getOnSave());
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

    public String getSaveText() {
        return this.saveText;
    }

    public String getUnsaveText() {
        return this.unsaveText;
    }

    @Override // SOATemplateListInterface.v1_0.TemplateElement, SOACoreInterface.v1_0.SOAObject
    public int hashCode() {
        return internalHashCodeCompute(Integer.valueOf(super.hashCode()), Integer.valueOf(classNameHashCode), getSaveText(), getId(), getUnsaveText(), getOnUnsave(), getOnSave());
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

    public void setSaveText(String str) {
        this.saveText = str;
    }

    public void setUnsaveText(String str) {
        this.unsaveText = str;
    }
}
