package Podcast.Touch.ThumbnailNavigatorItemElementInterface.v1_0;

import SOACoreInterface.v1_0.Method;
import SOACoreInterface.v1_0.SOAObject;
import SOATemplateListInterface.v1_0.TemplateElement;
import java.util.Arrays;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public class ThumbnailNavigatorItemElement extends TemplateElement {
    private static final int classNameHashCode = internalHashCodeCompute("Podcast.Touch.ThumbnailNavigatorItemElementInterface.v1_0.ThumbnailNavigatorItemElement");
    private String image;
    private List<Method> onItemSelected;
    private String title;

    public static class Builder extends TemplateElement.Builder {
        protected String image;
        protected List<Method> onItemSelected;
        protected String title;

        public ThumbnailNavigatorItemElement build() {
            ThumbnailNavigatorItemElement thumbnailNavigatorItemElement = new ThumbnailNavigatorItemElement();
            populate(thumbnailNavigatorItemElement);
            return thumbnailNavigatorItemElement;
        }

        protected void populate(ThumbnailNavigatorItemElement thumbnailNavigatorItemElement) {
            super.populate((TemplateElement) thumbnailNavigatorItemElement);
            thumbnailNavigatorItemElement.setTitle(this.title);
            thumbnailNavigatorItemElement.setImage(this.image);
            thumbnailNavigatorItemElement.setOnItemSelected(this.onItemSelected);
        }

        public Builder withImage(String str) {
            this.image = str;
            return this;
        }

        public Builder withOnItemSelected(List<Method> list) {
            this.onItemSelected = list;
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
        if (!(sOAObject instanceof ThumbnailNavigatorItemElement)) {
            return 1;
        }
        ThumbnailNavigatorItemElement thumbnailNavigatorItemElement = (ThumbnailNavigatorItemElement) sOAObject;
        String title = getTitle();
        String title2 = thumbnailNavigatorItemElement.getTitle();
        if (title != title2) {
            if (title == null) {
                return -1;
            }
            if (title2 == null) {
                return 1;
            }
            int iCompareTo = title.compareTo(title2);
            if (iCompareTo != 0) {
                return iCompareTo;
            }
        }
        String image = getImage();
        String image2 = thumbnailNavigatorItemElement.getImage();
        if (image != image2) {
            if (image == null) {
                return -1;
            }
            if (image2 == null) {
                return 1;
            }
            int iCompareTo2 = image.compareTo(image2);
            if (iCompareTo2 != 0) {
                return iCompareTo2;
            }
        }
        List<Method> onItemSelected = getOnItemSelected();
        List<Method> onItemSelected2 = thumbnailNavigatorItemElement.getOnItemSelected();
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
        if (!(obj instanceof ThumbnailNavigatorItemElement)) {
            return false;
        }
        ThumbnailNavigatorItemElement thumbnailNavigatorItemElement = (ThumbnailNavigatorItemElement) obj;
        return super.equals(obj) && internalEqualityCheck(getTitle(), thumbnailNavigatorItemElement.getTitle()) && internalEqualityCheck(getImage(), thumbnailNavigatorItemElement.getImage()) && internalEqualityCheck(getOnItemSelected(), thumbnailNavigatorItemElement.getOnItemSelected());
    }

    public String getImage() {
        return this.image;
    }

    public List<Method> getOnItemSelected() {
        return this.onItemSelected;
    }

    public String getTitle() {
        return this.title;
    }

    @Override // SOATemplateListInterface.v1_0.TemplateElement, SOACoreInterface.v1_0.SOAObject
    public int hashCode() {
        return internalHashCodeCompute(Integer.valueOf(super.hashCode()), Integer.valueOf(classNameHashCode), getTitle(), getImage(), getOnItemSelected());
    }

    public void setImage(String str) {
        this.image = str;
    }

    public void setOnItemSelected(List<Method> list) {
        this.onItemSelected = list;
    }

    public void setTitle(String str) {
        this.title = str;
    }
}
