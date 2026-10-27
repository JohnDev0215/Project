package Podcast.Touch.PodcastDetailTemplateInterface.v2_0;

import SOACoreInterface.v1_0.Method;
import SOACoreInterface.v1_0.SOAObject;
import SOATemplateListInterface.v1_0.TemplateElement;
import java.util.Arrays;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public class AboutElement extends TemplateElement {
    private static final int classNameHashCode = internalHashCodeCompute("Podcast.Touch.PodcastDetailTemplateInterface.v2_0.AboutElement");
    private String description;
    private List<Method> onItemSelected;

    public static class Builder extends TemplateElement.Builder {
        protected String description;
        protected List<Method> onItemSelected;

        public AboutElement build() {
            AboutElement aboutElement = new AboutElement();
            populate(aboutElement);
            return aboutElement;
        }

        protected void populate(AboutElement aboutElement) {
            super.populate((TemplateElement) aboutElement);
            aboutElement.setOnItemSelected(this.onItemSelected);
            aboutElement.setDescription(this.description);
        }

        public Builder withDescription(String str) {
            this.description = str;
            return this;
        }

        public Builder withOnItemSelected(List<Method> list) {
            this.onItemSelected = list;
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
        if (!(sOAObject instanceof AboutElement)) {
            return 1;
        }
        AboutElement aboutElement = (AboutElement) sOAObject;
        List<Method> onItemSelected = getOnItemSelected();
        List<Method> onItemSelected2 = aboutElement.getOnItemSelected();
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
        String description = getDescription();
        String description2 = aboutElement.getDescription();
        if (description != description2) {
            if (description == null) {
                return -1;
            }
            if (description2 == null) {
                return 1;
            }
            int iCompareTo2 = description.compareTo(description2);
            if (iCompareTo2 != 0) {
                return iCompareTo2;
            }
        }
        return super.compareTo(sOAObject);
    }

    @Override // SOATemplateListInterface.v1_0.TemplateElement, SOACoreInterface.v1_0.SOAObject
    public boolean equals(Object obj) {
        if (!(obj instanceof AboutElement)) {
            return false;
        }
        AboutElement aboutElement = (AboutElement) obj;
        return super.equals(obj) && internalEqualityCheck(getOnItemSelected(), aboutElement.getOnItemSelected()) && internalEqualityCheck(getDescription(), aboutElement.getDescription());
    }

    public String getDescription() {
        return this.description;
    }

    public List<Method> getOnItemSelected() {
        return this.onItemSelected;
    }

    @Override // SOATemplateListInterface.v1_0.TemplateElement, SOACoreInterface.v1_0.SOAObject
    public int hashCode() {
        return internalHashCodeCompute(Integer.valueOf(super.hashCode()), Integer.valueOf(classNameHashCode), getOnItemSelected(), getDescription());
    }

    public void setDescription(String str) {
        this.description = str;
    }

    public void setOnItemSelected(List<Method> list) {
        this.onItemSelected = list;
    }
}
