package Podcast.Touch.PTCTemplateInterface.v1_0;

import SOACoreInterface.v1_0.Method;
import SOACoreInterface.v1_0.SOAObject;
import SOATemplateListInterface.v1_0.TemplateElement;
import java.util.Arrays;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public class ListItemElement extends TemplateElement {
    private static final int classNameHashCode = internalHashCodeCompute("Podcast.Touch.PTCTemplateInterface.v1_0.ListItemElement");
    private String image;
    private List<Method> onImageSelected;
    private List<Method> onItemSelected;
    private String subtitle;
    private String title;

    public static class Builder extends TemplateElement.Builder {
        protected String image;
        protected List<Method> onImageSelected;
        protected List<Method> onItemSelected;
        protected String subtitle;
        protected String title;

        public ListItemElement build() {
            ListItemElement listItemElement = new ListItemElement();
            populate(listItemElement);
            return listItemElement;
        }

        protected void populate(ListItemElement listItemElement) {
            super.populate((TemplateElement) listItemElement);
            listItemElement.setOnImageSelected(this.onImageSelected);
            listItemElement.setTitle(this.title);
            listItemElement.setSubtitle(this.subtitle);
            listItemElement.setImage(this.image);
            listItemElement.setOnItemSelected(this.onItemSelected);
        }

        public Builder withImage(String str) {
            this.image = str;
            return this;
        }

        public Builder withOnImageSelected(List<Method> list) {
            this.onImageSelected = list;
            return this;
        }

        public Builder withOnItemSelected(List<Method> list) {
            this.onItemSelected = list;
            return this;
        }

        public Builder withSubtitle(String str) {
            this.subtitle = str;
            return this;
        }

        public Builder withTitle(String str) {
            this.title = str;
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
        List<Method> onImageSelected = getOnImageSelected();
        List<Method> onImageSelected2 = listItemElement.getOnImageSelected();
        if (onImageSelected != onImageSelected2) {
            if (onImageSelected == null) {
                return -1;
            }
            if (onImageSelected2 == null) {
                return 1;
            }
            if (onImageSelected instanceof Comparable) {
                int iCompareTo = ((Comparable) onImageSelected).compareTo(onImageSelected2);
                if (iCompareTo != 0) {
                    return iCompareTo;
                }
            } else if (!onImageSelected.equals(onImageSelected2)) {
                int iHashCode = onImageSelected.hashCode();
                int iHashCode2 = onImageSelected2.hashCode();
                if (iHashCode < iHashCode2) {
                    return -1;
                }
                if (iHashCode > iHashCode2) {
                    return 1;
                }
            }
        }
        String title = getTitle();
        String title2 = listItemElement.getTitle();
        if (title != title2) {
            if (title == null) {
                return -1;
            }
            if (title2 == null) {
                return 1;
            }
            int iCompareTo2 = title.compareTo(title2);
            if (iCompareTo2 != 0) {
                return iCompareTo2;
            }
        }
        String subtitle = getSubtitle();
        String subtitle2 = listItemElement.getSubtitle();
        if (subtitle != subtitle2) {
            if (subtitle == null) {
                return -1;
            }
            if (subtitle2 == null) {
                return 1;
            }
            int iCompareTo3 = subtitle.compareTo(subtitle2);
            if (iCompareTo3 != 0) {
                return iCompareTo3;
            }
        }
        String image = getImage();
        String image2 = listItemElement.getImage();
        if (image != image2) {
            if (image == null) {
                return -1;
            }
            if (image2 == null) {
                return 1;
            }
            int iCompareTo4 = image.compareTo(image2);
            if (iCompareTo4 != 0) {
                return iCompareTo4;
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
                int iCompareTo5 = ((Comparable) onItemSelected).compareTo(onItemSelected2);
                if (iCompareTo5 != 0) {
                    return iCompareTo5;
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
        return super.compareTo(sOAObject);
    }

    @Override // SOATemplateListInterface.v1_0.TemplateElement, SOACoreInterface.v1_0.SOAObject
    public boolean equals(Object obj) {
        if (!(obj instanceof ListItemElement)) {
            return false;
        }
        ListItemElement listItemElement = (ListItemElement) obj;
        return super.equals(obj) && internalEqualityCheck(getOnImageSelected(), listItemElement.getOnImageSelected()) && internalEqualityCheck(getTitle(), listItemElement.getTitle()) && internalEqualityCheck(getSubtitle(), listItemElement.getSubtitle()) && internalEqualityCheck(getImage(), listItemElement.getImage()) && internalEqualityCheck(getOnItemSelected(), listItemElement.getOnItemSelected());
    }

    public String getImage() {
        return this.image;
    }

    public List<Method> getOnImageSelected() {
        return this.onImageSelected;
    }

    public List<Method> getOnItemSelected() {
        return this.onItemSelected;
    }

    public String getSubtitle() {
        return this.subtitle;
    }

    public String getTitle() {
        return this.title;
    }

    @Override // SOATemplateListInterface.v1_0.TemplateElement, SOACoreInterface.v1_0.SOAObject
    public int hashCode() {
        return internalHashCodeCompute(Integer.valueOf(super.hashCode()), Integer.valueOf(classNameHashCode), getOnImageSelected(), getTitle(), getSubtitle(), getImage(), getOnItemSelected());
    }

    public void setImage(String str) {
        this.image = str;
    }

    public void setOnImageSelected(List<Method> list) {
        this.onImageSelected = list;
    }

    public void setOnItemSelected(List<Method> list) {
        this.onItemSelected = list;
    }

    public void setSubtitle(String str) {
        this.subtitle = str;
    }

    public void setTitle(String str) {
        this.title = str;
    }
}
