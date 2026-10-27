package Podcast.Desktop.GalleryTemplateInterface.v1_0;

import Podcast.Desktop.TemplateMetadataInterface.v1_0.TemplateMetadataElement;
import SOACoreInterface.v1_0.Method;
import SOACoreInterface.v1_0.SOAObject;
import SOATemplateListInterface.v1_0.TemplateElement;
import java.util.Arrays;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public class VerticalItemElement extends TemplateElement {
    private static final int classNameHashCode = internalHashCodeCompute("Podcast.Desktop.GalleryTemplateInterface.v1_0.VerticalItemElement");
    private String altText;
    private BookmarkElement bookmark;
    private String image;
    private Boolean isDisabled;
    private TemplateMetadataElement metadata;
    private List<Method> onItemSelected;
    private List<Method> onPlaySelected;
    private String primaryText;
    private String secondaryText;

    public static class Builder extends TemplateElement.Builder {
        protected String altText;
        protected BookmarkElement bookmark;
        protected String image;
        protected Boolean isDisabled;
        protected TemplateMetadataElement metadata;
        protected List<Method> onItemSelected;
        protected List<Method> onPlaySelected;
        protected String primaryText;
        protected String secondaryText;

        public VerticalItemElement build() {
            VerticalItemElement verticalItemElement = new VerticalItemElement();
            populate(verticalItemElement);
            return verticalItemElement;
        }

        protected void populate(VerticalItemElement verticalItemElement) {
            super.populate((TemplateElement) verticalItemElement);
            verticalItemElement.setAltText(this.altText);
            verticalItemElement.setBookmark(this.bookmark);
            verticalItemElement.setPrimaryText(this.primaryText);
            verticalItemElement.setImage(this.image);
            verticalItemElement.setSecondaryText(this.secondaryText);
            verticalItemElement.setOnPlaySelected(this.onPlaySelected);
            verticalItemElement.setIsDisabled(this.isDisabled);
            verticalItemElement.setMetadata(this.metadata);
            verticalItemElement.setOnItemSelected(this.onItemSelected);
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

        public Builder withMetadata(TemplateMetadataElement templateMetadataElement) {
            this.metadata = templateMetadataElement;
            return this;
        }

        public Builder withOnItemSelected(List<Method> list) {
            this.onItemSelected = list;
            return this;
        }

        public Builder withOnPlaySelected(List<Method> list) {
            this.onPlaySelected = list;
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
        String altText = getAltText();
        String altText2 = verticalItemElement.getAltText();
        if (altText != altText2) {
            if (altText == null) {
                return -1;
            }
            if (altText2 == null) {
                return 1;
            }
            int iCompareTo = altText.compareTo(altText2);
            if (iCompareTo != 0) {
                return iCompareTo;
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
            int iCompareTo2 = bookmark.compareTo(bookmark2);
            if (iCompareTo2 != 0) {
                return iCompareTo2;
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
            int iCompareTo3 = primaryText.compareTo(primaryText2);
            if (iCompareTo3 != 0) {
                return iCompareTo3;
            }
        }
        String image = getImage();
        String image2 = verticalItemElement.getImage();
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
        String secondaryText = getSecondaryText();
        String secondaryText2 = verticalItemElement.getSecondaryText();
        if (secondaryText != secondaryText2) {
            if (secondaryText == null) {
                return -1;
            }
            if (secondaryText2 == null) {
                return 1;
            }
            int iCompareTo5 = secondaryText.compareTo(secondaryText2);
            if (iCompareTo5 != 0) {
                return iCompareTo5;
            }
        }
        List<Method> onPlaySelected = getOnPlaySelected();
        List<Method> onPlaySelected2 = verticalItemElement.getOnPlaySelected();
        if (onPlaySelected != onPlaySelected2) {
            if (onPlaySelected == null) {
                return -1;
            }
            if (onPlaySelected2 == null) {
                return 1;
            }
            if (onPlaySelected instanceof Comparable) {
                int iCompareTo6 = ((Comparable) onPlaySelected).compareTo(onPlaySelected2);
                if (iCompareTo6 != 0) {
                    return iCompareTo6;
                }
            } else if (!onPlaySelected.equals(onPlaySelected2)) {
                int iHashCode = onPlaySelected.hashCode();
                int iHashCode2 = onPlaySelected2.hashCode();
                if (iHashCode < iHashCode2) {
                    return -1;
                }
                if (iHashCode > iHashCode2) {
                    return 1;
                }
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
            int iCompareTo7 = boolIsIsDisabled.compareTo(boolIsIsDisabled2);
            if (iCompareTo7 != 0) {
                return iCompareTo7;
            }
        }
        TemplateMetadataElement metadata = getMetadata();
        TemplateMetadataElement metadata2 = verticalItemElement.getMetadata();
        if (metadata != metadata2) {
            if (metadata == null) {
                return -1;
            }
            if (metadata2 == null) {
                return 1;
            }
            int iCompareTo8 = metadata.compareTo(metadata2);
            if (iCompareTo8 != 0) {
                return iCompareTo8;
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
                int iCompareTo9 = ((Comparable) onItemSelected).compareTo(onItemSelected2);
                if (iCompareTo9 != 0) {
                    return iCompareTo9;
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
        if (!(obj instanceof VerticalItemElement)) {
            return false;
        }
        VerticalItemElement verticalItemElement = (VerticalItemElement) obj;
        return super.equals(obj) && internalEqualityCheck(getAltText(), verticalItemElement.getAltText()) && internalEqualityCheck(getBookmark(), verticalItemElement.getBookmark()) && internalEqualityCheck(getPrimaryText(), verticalItemElement.getPrimaryText()) && internalEqualityCheck(getImage(), verticalItemElement.getImage()) && internalEqualityCheck(getSecondaryText(), verticalItemElement.getSecondaryText()) && internalEqualityCheck(getOnPlaySelected(), verticalItemElement.getOnPlaySelected()) && internalEqualityCheck(isIsDisabled(), verticalItemElement.isIsDisabled()) && internalEqualityCheck(getMetadata(), verticalItemElement.getMetadata()) && internalEqualityCheck(getOnItemSelected(), verticalItemElement.getOnItemSelected());
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

    public TemplateMetadataElement getMetadata() {
        return this.metadata;
    }

    public List<Method> getOnItemSelected() {
        return this.onItemSelected;
    }

    public List<Method> getOnPlaySelected() {
        return this.onPlaySelected;
    }

    public String getPrimaryText() {
        return this.primaryText;
    }

    public String getSecondaryText() {
        return this.secondaryText;
    }

    @Override // SOATemplateListInterface.v1_0.TemplateElement, SOACoreInterface.v1_0.SOAObject
    public int hashCode() {
        return internalHashCodeCompute(Integer.valueOf(super.hashCode()), Integer.valueOf(classNameHashCode), getAltText(), getBookmark(), getPrimaryText(), getImage(), getSecondaryText(), getOnPlaySelected(), isIsDisabled(), getMetadata(), getOnItemSelected());
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

    public void setMetadata(TemplateMetadataElement templateMetadataElement) {
        this.metadata = templateMetadataElement;
    }

    public void setOnItemSelected(List<Method> list) {
        this.onItemSelected = list;
    }

    public void setOnPlaySelected(List<Method> list) {
        this.onPlaySelected = list;
    }

    public void setPrimaryText(String str) {
        this.primaryText = str;
    }

    public void setSecondaryText(String str) {
        this.secondaryText = str;
    }
}
