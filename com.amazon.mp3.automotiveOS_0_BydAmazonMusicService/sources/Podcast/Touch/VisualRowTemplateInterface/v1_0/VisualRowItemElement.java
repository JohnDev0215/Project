package Podcast.Touch.VisualRowTemplateInterface.v1_0;

import Podcast.EpisodeOptionsInterface.v1_0.EpisodeOptionsElement;
import SOACoreInterface.v1_0.Method;
import SOACoreInterface.v1_0.SOAObject;
import SOATemplateListInterface.v1_0.TemplateElement;
import java.util.Arrays;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public class VisualRowItemElement extends TemplateElement {
    private static final int classNameHashCode = internalHashCodeCompute("Podcast.Touch.VisualRowTemplateInterface.v1_0.VisualRowItemElement");
    private BookmarkElement bookmark;
    private String description;
    private DownloadElement download;
    private EpisodeOptionsElement episodeOptions;
    private String image;
    private Boolean isDisabled;
    private String label;
    private List<Method> onImageSelected;
    private List<Method> onItemSelected;
    private String primaryText;
    private String secondaryText;
    private String tertiaryText;

    public static class Builder extends TemplateElement.Builder {
        protected BookmarkElement bookmark;
        protected String description;
        protected DownloadElement download;
        protected EpisodeOptionsElement episodeOptions;
        protected String image;
        protected Boolean isDisabled;
        protected String label;
        protected List<Method> onImageSelected;
        protected List<Method> onItemSelected;
        protected String primaryText;
        protected String secondaryText;
        protected String tertiaryText;

        public VisualRowItemElement build() {
            VisualRowItemElement visualRowItemElement = new VisualRowItemElement();
            populate(visualRowItemElement);
            return visualRowItemElement;
        }

        protected void populate(VisualRowItemElement visualRowItemElement) {
            super.populate((TemplateElement) visualRowItemElement);
            visualRowItemElement.setPrimaryText(this.primaryText);
            visualRowItemElement.setImage(this.image);
            visualRowItemElement.setIsDisabled(this.isDisabled);
            visualRowItemElement.setLabel(this.label);
            visualRowItemElement.setDownload(this.download);
            visualRowItemElement.setDescription(this.description);
            visualRowItemElement.setOnImageSelected(this.onImageSelected);
            visualRowItemElement.setBookmark(this.bookmark);
            visualRowItemElement.setSecondaryText(this.secondaryText);
            visualRowItemElement.setTertiaryText(this.tertiaryText);
            visualRowItemElement.setOnItemSelected(this.onItemSelected);
            visualRowItemElement.setEpisodeOptions(this.episodeOptions);
        }

        public Builder withBookmark(BookmarkElement bookmarkElement) {
            this.bookmark = bookmarkElement;
            return this;
        }

        public Builder withDescription(String str) {
            this.description = str;
            return this;
        }

        public Builder withDownload(DownloadElement downloadElement) {
            this.download = downloadElement;
            return this;
        }

        public Builder withEpisodeOptions(EpisodeOptionsElement episodeOptionsElement) {
            this.episodeOptions = episodeOptionsElement;
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

        public Builder withLabel(String str) {
            this.label = str;
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

        public Builder withPrimaryText(String str) {
            this.primaryText = str;
            return this;
        }

        public Builder withSecondaryText(String str) {
            this.secondaryText = str;
            return this;
        }

        public Builder withTertiaryText(String str) {
            this.tertiaryText = str;
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
        if (!(sOAObject instanceof VisualRowItemElement)) {
            return 1;
        }
        VisualRowItemElement visualRowItemElement = (VisualRowItemElement) sOAObject;
        String primaryText = getPrimaryText();
        String primaryText2 = visualRowItemElement.getPrimaryText();
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
        String image = getImage();
        String image2 = visualRowItemElement.getImage();
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
        Boolean boolIsIsDisabled = isIsDisabled();
        Boolean boolIsIsDisabled2 = visualRowItemElement.isIsDisabled();
        if (boolIsIsDisabled != boolIsIsDisabled2) {
            if (boolIsIsDisabled == null) {
                return -1;
            }
            if (boolIsIsDisabled2 == null) {
                return 1;
            }
            int iCompareTo3 = boolIsIsDisabled.compareTo(boolIsIsDisabled2);
            if (iCompareTo3 != 0) {
                return iCompareTo3;
            }
        }
        String label = getLabel();
        String label2 = visualRowItemElement.getLabel();
        if (label != label2) {
            if (label == null) {
                return -1;
            }
            if (label2 == null) {
                return 1;
            }
            int iCompareTo4 = label.compareTo(label2);
            if (iCompareTo4 != 0) {
                return iCompareTo4;
            }
        }
        DownloadElement download = getDownload();
        DownloadElement download2 = visualRowItemElement.getDownload();
        if (download != download2) {
            if (download == null) {
                return -1;
            }
            if (download2 == null) {
                return 1;
            }
            int iCompareTo5 = download.compareTo(download2);
            if (iCompareTo5 != 0) {
                return iCompareTo5;
            }
        }
        String description = getDescription();
        String description2 = visualRowItemElement.getDescription();
        if (description != description2) {
            if (description == null) {
                return -1;
            }
            if (description2 == null) {
                return 1;
            }
            int iCompareTo6 = description.compareTo(description2);
            if (iCompareTo6 != 0) {
                return iCompareTo6;
            }
        }
        List<Method> onImageSelected = getOnImageSelected();
        List<Method> onImageSelected2 = visualRowItemElement.getOnImageSelected();
        if (onImageSelected != onImageSelected2) {
            if (onImageSelected == null) {
                return -1;
            }
            if (onImageSelected2 == null) {
                return 1;
            }
            if (onImageSelected instanceof Comparable) {
                int iCompareTo7 = ((Comparable) onImageSelected).compareTo(onImageSelected2);
                if (iCompareTo7 != 0) {
                    return iCompareTo7;
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
        BookmarkElement bookmark = getBookmark();
        BookmarkElement bookmark2 = visualRowItemElement.getBookmark();
        if (bookmark != bookmark2) {
            if (bookmark == null) {
                return -1;
            }
            if (bookmark2 == null) {
                return 1;
            }
            int iCompareTo8 = bookmark.compareTo(bookmark2);
            if (iCompareTo8 != 0) {
                return iCompareTo8;
            }
        }
        String secondaryText = getSecondaryText();
        String secondaryText2 = visualRowItemElement.getSecondaryText();
        if (secondaryText != secondaryText2) {
            if (secondaryText == null) {
                return -1;
            }
            if (secondaryText2 == null) {
                return 1;
            }
            int iCompareTo9 = secondaryText.compareTo(secondaryText2);
            if (iCompareTo9 != 0) {
                return iCompareTo9;
            }
        }
        String tertiaryText = getTertiaryText();
        String tertiaryText2 = visualRowItemElement.getTertiaryText();
        if (tertiaryText != tertiaryText2) {
            if (tertiaryText == null) {
                return -1;
            }
            if (tertiaryText2 == null) {
                return 1;
            }
            int iCompareTo10 = tertiaryText.compareTo(tertiaryText2);
            if (iCompareTo10 != 0) {
                return iCompareTo10;
            }
        }
        List<Method> onItemSelected = getOnItemSelected();
        List<Method> onItemSelected2 = visualRowItemElement.getOnItemSelected();
        if (onItemSelected != onItemSelected2) {
            if (onItemSelected == null) {
                return -1;
            }
            if (onItemSelected2 == null) {
                return 1;
            }
            if (onItemSelected instanceof Comparable) {
                int iCompareTo11 = ((Comparable) onItemSelected).compareTo(onItemSelected2);
                if (iCompareTo11 != 0) {
                    return iCompareTo11;
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
        EpisodeOptionsElement episodeOptions = getEpisodeOptions();
        EpisodeOptionsElement episodeOptions2 = visualRowItemElement.getEpisodeOptions();
        if (episodeOptions != episodeOptions2) {
            if (episodeOptions == null) {
                return -1;
            }
            if (episodeOptions2 == null) {
                return 1;
            }
            int iCompareTo12 = episodeOptions.compareTo(episodeOptions2);
            if (iCompareTo12 != 0) {
                return iCompareTo12;
            }
        }
        return super.compareTo(sOAObject);
    }

    @Override // SOATemplateListInterface.v1_0.TemplateElement, SOACoreInterface.v1_0.SOAObject
    public boolean equals(Object obj) {
        if (!(obj instanceof VisualRowItemElement)) {
            return false;
        }
        VisualRowItemElement visualRowItemElement = (VisualRowItemElement) obj;
        return super.equals(obj) && internalEqualityCheck(getPrimaryText(), visualRowItemElement.getPrimaryText()) && internalEqualityCheck(getImage(), visualRowItemElement.getImage()) && internalEqualityCheck(isIsDisabled(), visualRowItemElement.isIsDisabled()) && internalEqualityCheck(getLabel(), visualRowItemElement.getLabel()) && internalEqualityCheck(getDownload(), visualRowItemElement.getDownload()) && internalEqualityCheck(getDescription(), visualRowItemElement.getDescription()) && internalEqualityCheck(getOnImageSelected(), visualRowItemElement.getOnImageSelected()) && internalEqualityCheck(getBookmark(), visualRowItemElement.getBookmark()) && internalEqualityCheck(getSecondaryText(), visualRowItemElement.getSecondaryText()) && internalEqualityCheck(getTertiaryText(), visualRowItemElement.getTertiaryText()) && internalEqualityCheck(getOnItemSelected(), visualRowItemElement.getOnItemSelected()) && internalEqualityCheck(getEpisodeOptions(), visualRowItemElement.getEpisodeOptions());
    }

    public BookmarkElement getBookmark() {
        return this.bookmark;
    }

    public String getDescription() {
        return this.description;
    }

    public DownloadElement getDownload() {
        return this.download;
    }

    public EpisodeOptionsElement getEpisodeOptions() {
        return this.episodeOptions;
    }

    public String getImage() {
        return this.image;
    }

    public String getLabel() {
        return this.label;
    }

    public List<Method> getOnImageSelected() {
        return this.onImageSelected;
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

    public String getTertiaryText() {
        return this.tertiaryText;
    }

    @Override // SOATemplateListInterface.v1_0.TemplateElement, SOACoreInterface.v1_0.SOAObject
    public int hashCode() {
        return internalHashCodeCompute(Integer.valueOf(super.hashCode()), Integer.valueOf(classNameHashCode), getPrimaryText(), getImage(), isIsDisabled(), getLabel(), getDownload(), getDescription(), getOnImageSelected(), getBookmark(), getSecondaryText(), getTertiaryText(), getOnItemSelected(), getEpisodeOptions());
    }

    public Boolean isIsDisabled() {
        return this.isDisabled;
    }

    public void setBookmark(BookmarkElement bookmarkElement) {
        this.bookmark = bookmarkElement;
    }

    public void setDescription(String str) {
        this.description = str;
    }

    public void setDownload(DownloadElement downloadElement) {
        this.download = downloadElement;
    }

    public void setEpisodeOptions(EpisodeOptionsElement episodeOptionsElement) {
        this.episodeOptions = episodeOptionsElement;
    }

    public void setImage(String str) {
        this.image = str;
    }

    public void setIsDisabled(Boolean bool) {
        this.isDisabled = bool;
    }

    public void setLabel(String str) {
        this.label = str;
    }

    public void setOnImageSelected(List<Method> list) {
        this.onImageSelected = list;
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

    public void setTertiaryText(String str) {
        this.tertiaryText = str;
    }
}
