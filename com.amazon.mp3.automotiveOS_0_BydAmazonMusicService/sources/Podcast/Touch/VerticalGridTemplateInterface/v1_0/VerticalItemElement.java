package Podcast.Touch.VerticalGridTemplateInterface.v1_0;

import SOACoreInterface.v1_0.Method;
import SOACoreInterface.v1_0.SOAObject;
import SOATemplateListInterface.v1_0.TemplateElement;
import java.util.Arrays;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public class VerticalItemElement extends TemplateElement {
    private static final int classNameHashCode = internalHashCodeCompute("Podcast.Touch.VerticalGridTemplateInterface.v1_0.VerticalItemElement");
    private String altText;
    private BookmarkElement bookmark;
    private String image;
    private Boolean isDisabled;
    private List<Method> onItemSelected;
    private String primaryText;
    private String secondaryText;

    public static class Builder extends TemplateElement.Builder {
        protected String altText;
        protected BookmarkElement bookmark;
        protected String image;
        protected Boolean isDisabled;
        protected List<Method> onItemSelected;
        protected String primaryText;
        protected String secondaryText;

        public VerticalItemElement build() {
            VerticalItemElement verticalItemElement = new VerticalItemElement();
            populate(verticalItemElement);
            return verticalItemElement;
        }

        protected void populate(VerticalItemElement verticalItemElement) {
            super.populate((TemplateElement) verticalItemElement);
            verticalItemElement.setImage(this.image);
            verticalItemElement.setPrimaryText(this.primaryText);
            verticalItemElement.setOnItemSelected(this.onItemSelected);
            verticalItemElement.setBookmark(this.bookmark);
            verticalItemElement.setIsDisabled(this.isDisabled);
            verticalItemElement.setSecondaryText(this.secondaryText);
            verticalItemElement.setAltText(this.altText);
        }

        public Builder withAltText(String str) {
            this.altText = str;
            return this;
        }

        public Builder withBookmark(BookmarkElement bookmarkElement) {
            this.bookmark = bookmarkElement;
            return this;
        }

        public Builder withImage(String str) {
            this.image = str;
            return this;
        }

        public Builder withIsDisabled(Boolean bool) {
            this.isDisabled = bool;
            return this;
        }

        public Builder withOnItemSelected(List<Method> list) {
            this.onItemSelected = list;
            return this;
        }

        public Builder withPrimaryText(String str) {
            this.primaryText = str;
            return this;
        }

        public Builder withSecondaryText(String str) {
            this.secondaryText = str;
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
        if (!(sOAObject instanceof VerticalItemElement)) {
            return 1;
        }
        VerticalItemElement verticalItemElement = (VerticalItemElement) sOAObject;
        String image = getImage();
        String image2 = verticalItemElement.getImage();
        if (image != image2) {
            if (image == null) {
                return -1;
            }
            if (image2 == null) {
                return 1;
            }
            int iCompareTo = image.compareTo(image2);
            if (iCompareTo != 0) {
                return iCompareTo;
            }
        }
        String primaryText = getPrimaryText();
        String primaryText2 = verticalItemElement.getPrimaryText();
        if (primaryText != primaryText2) {
            if (primaryText == null) {
                return -1;
            }
            if (primaryText2 == null) {
                return 1;
            }
            int iCompareTo2 = primaryText.compareTo(primaryText2);
            if (iCompareTo2 != 0) {
                return iCompareTo2;
            }
        }
        List<Method> onItemSelected = getOnItemSelected();
        List<Method> onItemSelected2 = verticalItemElement.getOnItemSelected();
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
        BookmarkElement bookmark = getBookmark();
        BookmarkElement bookmark2 = verticalItemElement.getBookmark();
        if (bookmark != bookmark2) {
            if (bookmark == null) {
                return -1;
            }
            if (bookmark2 == null) {
                return 1;
            }
            int iCompareTo4 = bookmark.compareTo(bookmark2);
            if (iCompareTo4 != 0) {
                return iCompareTo4;
            }
        }
        Boolean boolIsIsDisabled = isIsDisabled();
        Boolean boolIsIsDisabled2 = verticalItemElement.isIsDisabled();
        if (boolIsIsDisabled != boolIsIsDisabled2) {
            if (boolIsIsDisabled == null) {
                return -1;
            }
            if (boolIsIsDisabled2 == null) {
                return 1;
            }
            int iCompareTo5 = boolIsIsDisabled.compareTo(boolIsIsDisabled2);
            if (iCompareTo5 != 0) {
                return iCompareTo5;
            }
        }
        String secondaryText = getSecondaryText();
        String secondaryText2 = verticalItemElement.getSecondaryText();
        if (secondaryText != secondaryText2) {
            if (secondaryText == null) {
                return -1;
            }
            if (secondaryText2 == null) {
                return 1;
            }
            int iCompareTo6 = secondaryText.compareTo(secondaryText2);
            if (iCompareTo6 != 0) {
                return iCompareTo6;
            }
        }
        String altText = getAltText();
        String altText2 = verticalItemElement.getAltText();
        if (altText != altText2) {
            if (altText == null) {
                return -1;
            }
            if (altText2 == null) {
                return 1;
            }
            int iCompareTo7 = altText.compareTo(altText2);
            if (iCompareTo7 != 0) {
                return iCompareTo7;
            }
        }
        return super.compareTo(sOAObject);
    }

    @Override // SOATemplateListInterface.v1_0.TemplateElement, SOACoreInterface.v1_0.SOAObject
    public boolean equals(Object obj) {
        if (!(obj instanceof VerticalItemElement)) {
            return false;
        }
        VerticalItemElement verticalItemElement = (VerticalItemElement) obj;
        return super.equals(obj) && internalEqualityCheck(getImage(), verticalItemElement.getImage()) && internalEqualityCheck(getPrimaryText(), verticalItemElement.getPrimaryText()) && internalEqualityCheck(getOnItemSelected(), verticalItemElement.getOnItemSelected()) && internalEqualityCheck(getBookmark(), verticalItemElement.getBookmark()) && internalEqualityCheck(isIsDisabled(), verticalItemElement.isIsDisabled()) && internalEqualityCheck(getSecondaryText(), verticalItemElement.getSecondaryText()) && internalEqualityCheck(getAltText(), verticalItemElement.getAltText());
    }

    public String getAltText() {
        return this.altText;
    }

    public BookmarkElement getBookmark() {
        return this.bookmark;
    }

    public String getImage() {
        return this.image;
    }

    public List<Method> getOnItemSelected() {
        return this.onItemSelected;
    }

    public String getPrimaryText() {
        return this.primaryText;
    }

    public String getSecondaryText() {
        return this.secondaryText;
    }

    @Override // SOATemplateListInterface.v1_0.TemplateElement, SOACoreInterface.v1_0.SOAObject
    public int hashCode() {
        return internalHashCodeCompute(Integer.valueOf(super.hashCode()), Integer.valueOf(classNameHashCode), getImage(), getPrimaryText(), getOnItemSelected(), getBookmark(), isIsDisabled(), getSecondaryText(), getAltText());
    }

    public Boolean isIsDisabled() {
        return this.isDisabled;
    }

    public void setAltText(String str) {
        this.altText = str;
    }

    public void setBookmark(BookmarkElement bookmarkElement) {
        this.bookmark = bookmarkElement;
    }

    public void setImage(String str) {
        this.image = str;
    }

    public void setIsDisabled(Boolean bool) {
        this.isDisabled = bool;
    }

    public void setOnItemSelected(List<Method> list) {
        this.onItemSelected = list;
    }

    public void setPrimaryText(String str) {
        this.primaryText = str;
    }

    public void setSecondaryText(String str) {
        this.secondaryText = str;
    }
}
