package Podcast.Desktop.LatestTemplateInterface.v1_0;

import Podcast.Desktop.TemplateMetadataInterface.v1_0.TemplateMetadataElement;
import Podcast.EpisodeOptionsInterface.v1_0.EpisodeOptionsElement;
import SOACoreInterface.v1_0.Method;
import SOACoreInterface.v1_0.SOAObject;
import SOATemplateListInterface.v1_0.TemplateElement;
import java.util.Arrays;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public class LatestItemElement extends TemplateElement {
    private static final int classNameHashCode = internalHashCodeCompute("Podcast.Desktop.LatestTemplateInterface.v1_0.LatestItemElement");
    private BookmarkElement bookmark;
    private String description;
    private DownloadElement download;
    private EpisodeOptionsElement episodeOptions;
    private String image;
    private Boolean isDisabled;
    private String label;
    private TemplateMetadataElement metadata;
    private List<Method> onImageSelected;
    private List<Method> onItemSelected;
    private List<Method> onPlaySelected;
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
        protected TemplateMetadataElement metadata;
        protected List<Method> onImageSelected;
        protected List<Method> onItemSelected;
        protected List<Method> onPlaySelected;
        protected String primaryText;
        protected String secondaryText;
        protected String tertiaryText;

        public LatestItemElement build() {
            LatestItemElement latestItemElement = new LatestItemElement();
            populate(latestItemElement);
            return latestItemElement;
        }

        protected void populate(LatestItemElement latestItemElement) {
            super.populate((TemplateElement) latestItemElement);
            latestItemElement.setEpisodeOptions(this.episodeOptions);
            latestItemElement.setDownload(this.download);
            latestItemElement.setOnPlaySelected(this.onPlaySelected);
            latestItemElement.setOnImageSelected(this.onImageSelected);
            latestItemElement.setSecondaryText(this.secondaryText);
            latestItemElement.setMetadata(this.metadata);
            latestItemElement.setOnItemSelected(this.onItemSelected);
            latestItemElement.setBookmark(this.bookmark);
            latestItemElement.setIsDisabled(this.isDisabled);
            latestItemElement.setTertiaryText(this.tertiaryText);
            latestItemElement.setPrimaryText(this.primaryText);
            latestItemElement.setImage(this.image);
            latestItemElement.setDescription(this.description);
            latestItemElement.setLabel(this.label);
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

        public Builder withMetadata(TemplateMetadataElement templateMetadataElement) {
            this.metadata = templateMetadataElement;
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
        if (!(sOAObject instanceof LatestItemElement)) {
            return 1;
        }
        LatestItemElement latestItemElement = (LatestItemElement) sOAObject;
        EpisodeOptionsElement episodeOptions = getEpisodeOptions();
        EpisodeOptionsElement episodeOptions2 = latestItemElement.getEpisodeOptions();
        if (episodeOptions != episodeOptions2) {
            if (episodeOptions == null) {
                return -1;
            }
            if (episodeOptions2 == null) {
                return 1;
            }
            int iCompareTo = episodeOptions.compareTo(episodeOptions2);
            if (iCompareTo != 0) {
                return iCompareTo;
            }
        }
        DownloadElement download = getDownload();
        DownloadElement download2 = latestItemElement.getDownload();
        if (download != download2) {
            if (download == null) {
                return -1;
            }
            if (download2 == null) {
                return 1;
            }
            int iCompareTo2 = download.compareTo(download2);
            if (iCompareTo2 != 0) {
                return iCompareTo2;
            }
        }
        List<Method> onPlaySelected = getOnPlaySelected();
        List<Method> onPlaySelected2 = latestItemElement.getOnPlaySelected();
        if (onPlaySelected != onPlaySelected2) {
            if (onPlaySelected == null) {
                return -1;
            }
            if (onPlaySelected2 == null) {
                return 1;
            }
            if (onPlaySelected instanceof Comparable) {
                int iCompareTo3 = ((Comparable) onPlaySelected).compareTo(onPlaySelected2);
                if (iCompareTo3 != 0) {
                    return iCompareTo3;
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
        List<Method> onImageSelected = getOnImageSelected();
        List<Method> onImageSelected2 = latestItemElement.getOnImageSelected();
        if (onImageSelected != onImageSelected2) {
            if (onImageSelected == null) {
                return -1;
            }
            if (onImageSelected2 == null) {
                return 1;
            }
            if (onImageSelected instanceof Comparable) {
                int iCompareTo4 = ((Comparable) onImageSelected).compareTo(onImageSelected2);
                if (iCompareTo4 != 0) {
                    return iCompareTo4;
                }
            } else if (!onImageSelected.equals(onImageSelected2)) {
                int iHashCode3 = onImageSelected.hashCode();
                int iHashCode4 = onImageSelected2.hashCode();
                if (iHashCode3 < iHashCode4) {
                    return -1;
                }
                if (iHashCode3 > iHashCode4) {
                    return 1;
                }
            }
        }
        String secondaryText = getSecondaryText();
        String secondaryText2 = latestItemElement.getSecondaryText();
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
        TemplateMetadataElement metadata = getMetadata();
        TemplateMetadataElement metadata2 = latestItemElement.getMetadata();
        if (metadata != metadata2) {
            if (metadata == null) {
                return -1;
            }
            if (metadata2 == null) {
                return 1;
            }
            int iCompareTo6 = metadata.compareTo(metadata2);
            if (iCompareTo6 != 0) {
                return iCompareTo6;
            }
        }
        List<Method> onItemSelected = getOnItemSelected();
        List<Method> onItemSelected2 = latestItemElement.getOnItemSelected();
        if (onItemSelected != onItemSelected2) {
            if (onItemSelected == null) {
                return -1;
            }
            if (onItemSelected2 == null) {
                return 1;
            }
            if (onItemSelected instanceof Comparable) {
                int iCompareTo7 = ((Comparable) onItemSelected).compareTo(onItemSelected2);
                if (iCompareTo7 != 0) {
                    return iCompareTo7;
                }
            } else if (!onItemSelected.equals(onItemSelected2)) {
                int iHashCode5 = onItemSelected.hashCode();
                int iHashCode6 = onItemSelected2.hashCode();
                if (iHashCode5 < iHashCode6) {
                    return -1;
                }
                if (iHashCode5 > iHashCode6) {
                    return 1;
                }
            }
        }
        BookmarkElement bookmark = getBookmark();
        BookmarkElement bookmark2 = latestItemElement.getBookmark();
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
        Boolean boolIsIsDisabled = isIsDisabled();
        Boolean boolIsIsDisabled2 = latestItemElement.isIsDisabled();
        if (boolIsIsDisabled != boolIsIsDisabled2) {
            if (boolIsIsDisabled == null) {
                return -1;
            }
            if (boolIsIsDisabled2 == null) {
                return 1;
            }
            int iCompareTo9 = boolIsIsDisabled.compareTo(boolIsIsDisabled2);
            if (iCompareTo9 != 0) {
                return iCompareTo9;
            }
        }
        String tertiaryText = getTertiaryText();
        String tertiaryText2 = latestItemElement.getTertiaryText();
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
        String primaryText = getPrimaryText();
        String primaryText2 = latestItemElement.getPrimaryText();
        if (primaryText != primaryText2) {
            if (primaryText == null) {
                return -1;
            }
            if (primaryText2 == null) {
                return 1;
            }
            int iCompareTo11 = primaryText.compareTo(primaryText2);
            if (iCompareTo11 != 0) {
                return iCompareTo11;
            }
        }
        String image = getImage();
        String image2 = latestItemElement.getImage();
        if (image != image2) {
            if (image == null) {
                return -1;
            }
            if (image2 == null) {
                return 1;
            }
            int iCompareTo12 = image.compareTo(image2);
            if (iCompareTo12 != 0) {
                return iCompareTo12;
            }
        }
        String description = getDescription();
        String description2 = latestItemElement.getDescription();
        if (description != description2) {
            if (description == null) {
                return -1;
            }
            if (description2 == null) {
                return 1;
            }
            int iCompareTo13 = description.compareTo(description2);
            if (iCompareTo13 != 0) {
                return iCompareTo13;
            }
        }
        String label = getLabel();
        String label2 = latestItemElement.getLabel();
        if (label != label2) {
            if (label == null) {
                return -1;
            }
            if (label2 == null) {
                return 1;
            }
            int iCompareTo14 = label.compareTo(label2);
            if (iCompareTo14 != 0) {
                return iCompareTo14;
            }
        }
        return super.compareTo(sOAObject);
    }

    @Override // SOATemplateListInterface.v1_0.TemplateElement, SOACoreInterface.v1_0.SOAObject
    public boolean equals(Object obj) {
        if (!(obj instanceof LatestItemElement)) {
            return false;
        }
        LatestItemElement latestItemElement = (LatestItemElement) obj;
        return super.equals(obj) && internalEqualityCheck(getEpisodeOptions(), latestItemElement.getEpisodeOptions()) && internalEqualityCheck(getDownload(), latestItemElement.getDownload()) && internalEqualityCheck(getOnPlaySelected(), latestItemElement.getOnPlaySelected()) && internalEqualityCheck(getOnImageSelected(), latestItemElement.getOnImageSelected()) && internalEqualityCheck(getSecondaryText(), latestItemElement.getSecondaryText()) && internalEqualityCheck(getMetadata(), latestItemElement.getMetadata()) && internalEqualityCheck(getOnItemSelected(), latestItemElement.getOnItemSelected()) && internalEqualityCheck(getBookmark(), latestItemElement.getBookmark()) && internalEqualityCheck(isIsDisabled(), latestItemElement.isIsDisabled()) && internalEqualityCheck(getTertiaryText(), latestItemElement.getTertiaryText()) && internalEqualityCheck(getPrimaryText(), latestItemElement.getPrimaryText()) && internalEqualityCheck(getImage(), latestItemElement.getImage()) && internalEqualityCheck(getDescription(), latestItemElement.getDescription()) && internalEqualityCheck(getLabel(), latestItemElement.getLabel());
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

    public TemplateMetadataElement getMetadata() {
        return this.metadata;
    }

    public List<Method> getOnImageSelected() {
        return this.onImageSelected;
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

    public String getTertiaryText() {
        return this.tertiaryText;
    }

    @Override // SOATemplateListInterface.v1_0.TemplateElement, SOACoreInterface.v1_0.SOAObject
    public int hashCode() {
        return internalHashCodeCompute(Integer.valueOf(super.hashCode()), Integer.valueOf(classNameHashCode), getEpisodeOptions(), getDownload(), getOnPlaySelected(), getOnImageSelected(), getSecondaryText(), getMetadata(), getOnItemSelected(), getBookmark(), isIsDisabled(), getTertiaryText(), getPrimaryText(), getImage(), getDescription(), getLabel());
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

    public void setMetadata(TemplateMetadataElement templateMetadataElement) {
        this.metadata = templateMetadataElement;
    }

    public void setOnImageSelected(List<Method> list) {
        this.onImageSelected = list;
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

    public void setTertiaryText(String str) {
        this.tertiaryText = str;
    }
}
