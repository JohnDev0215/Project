package Podcast.Touch.DetailTemplateInterface.v1_0;

import Podcast.EpisodeOptionsInterface.v1_0.EpisodeOptionsElement;
import SOACoreInterface.v1_0.Method;
import SOACoreInterface.v1_0.SOAObject;
import SOATemplateListInterface.v1_0.TemplateElement;
import java.util.Arrays;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public class FeaturedSingleItemElement extends TemplateElement {
    private static final int classNameHashCode = internalHashCodeCompute("Podcast.Touch.DetailTemplateInterface.v1_0.FeaturedSingleItemElement");
    private String bookmarkId;
    private DownloadElement download;
    private EpisodeOptionsElement episodeOptions;
    private String image;
    private Boolean isDisabled;
    private String label;
    private List<Method> onImageSelected;
    private List<Method> onItemSelected;
    private List<Method> onPlaySelected;
    private String primaryText;
    private String secondaryText;
    private Long totalDurationMilliseconds;

    public static class Builder extends TemplateElement.Builder {
        protected String bookmarkId;
        protected DownloadElement download;
        protected EpisodeOptionsElement episodeOptions;
        protected String image;
        protected Boolean isDisabled;
        protected String label;
        protected List<Method> onImageSelected;
        protected List<Method> onItemSelected;
        protected List<Method> onPlaySelected;
        protected String primaryText;
        protected String secondaryText;
        protected Long totalDurationMilliseconds;

        public FeaturedSingleItemElement build() {
            FeaturedSingleItemElement featuredSingleItemElement = new FeaturedSingleItemElement();
            populate(featuredSingleItemElement);
            return featuredSingleItemElement;
        }

        protected void populate(FeaturedSingleItemElement featuredSingleItemElement) {
            super.populate((TemplateElement) featuredSingleItemElement);
            featuredSingleItemElement.setDownload(this.download);
            featuredSingleItemElement.setEpisodeOptions(this.episodeOptions);
            featuredSingleItemElement.setTotalDurationMilliseconds(this.totalDurationMilliseconds);
            featuredSingleItemElement.setSecondaryText(this.secondaryText);
            featuredSingleItemElement.setImage(this.image);
            featuredSingleItemElement.setOnItemSelected(this.onItemSelected);
            featuredSingleItemElement.setOnPlaySelected(this.onPlaySelected);
            featuredSingleItemElement.setOnImageSelected(this.onImageSelected);
            featuredSingleItemElement.setBookmarkId(this.bookmarkId);
            featuredSingleItemElement.setIsDisabled(this.isDisabled);
            featuredSingleItemElement.setPrimaryText(this.primaryText);
            featuredSingleItemElement.setLabel(this.label);
        }

        public Builder withBookmarkId(String str) {
            this.bookmarkId = str;
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

        public Builder withTotalDurationMilliseconds(Long l) {
            this.totalDurationMilliseconds = l;
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
        if (!(sOAObject instanceof FeaturedSingleItemElement)) {
            return 1;
        }
        FeaturedSingleItemElement featuredSingleItemElement = (FeaturedSingleItemElement) sOAObject;
        DownloadElement download = getDownload();
        DownloadElement download2 = featuredSingleItemElement.getDownload();
        if (download != download2) {
            if (download == null) {
                return -1;
            }
            if (download2 == null) {
                return 1;
            }
            int iCompareTo = download.compareTo(download2);
            if (iCompareTo != 0) {
                return iCompareTo;
            }
        }
        EpisodeOptionsElement episodeOptions = getEpisodeOptions();
        EpisodeOptionsElement episodeOptions2 = featuredSingleItemElement.getEpisodeOptions();
        if (episodeOptions != episodeOptions2) {
            if (episodeOptions == null) {
                return -1;
            }
            if (episodeOptions2 == null) {
                return 1;
            }
            int iCompareTo2 = episodeOptions.compareTo(episodeOptions2);
            if (iCompareTo2 != 0) {
                return iCompareTo2;
            }
        }
        Long totalDurationMilliseconds = getTotalDurationMilliseconds();
        Long totalDurationMilliseconds2 = featuredSingleItemElement.getTotalDurationMilliseconds();
        if (totalDurationMilliseconds != totalDurationMilliseconds2) {
            if (totalDurationMilliseconds == null) {
                return -1;
            }
            if (totalDurationMilliseconds2 == null) {
                return 1;
            }
            int iCompareTo3 = totalDurationMilliseconds.compareTo(totalDurationMilliseconds2);
            if (iCompareTo3 != 0) {
                return iCompareTo3;
            }
        }
        String secondaryText = getSecondaryText();
        String secondaryText2 = featuredSingleItemElement.getSecondaryText();
        if (secondaryText != secondaryText2) {
            if (secondaryText == null) {
                return -1;
            }
            if (secondaryText2 == null) {
                return 1;
            }
            int iCompareTo4 = secondaryText.compareTo(secondaryText2);
            if (iCompareTo4 != 0) {
                return iCompareTo4;
            }
        }
        String image = getImage();
        String image2 = featuredSingleItemElement.getImage();
        if (image != image2) {
            if (image == null) {
                return -1;
            }
            if (image2 == null) {
                return 1;
            }
            int iCompareTo5 = image.compareTo(image2);
            if (iCompareTo5 != 0) {
                return iCompareTo5;
            }
        }
        List<Method> onItemSelected = getOnItemSelected();
        List<Method> onItemSelected2 = featuredSingleItemElement.getOnItemSelected();
        if (onItemSelected != onItemSelected2) {
            if (onItemSelected == null) {
                return -1;
            }
            if (onItemSelected2 == null) {
                return 1;
            }
            if (onItemSelected instanceof Comparable) {
                int iCompareTo6 = ((Comparable) onItemSelected).compareTo(onItemSelected2);
                if (iCompareTo6 != 0) {
                    return iCompareTo6;
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
        List<Method> onPlaySelected = getOnPlaySelected();
        List<Method> onPlaySelected2 = featuredSingleItemElement.getOnPlaySelected();
        if (onPlaySelected != onPlaySelected2) {
            if (onPlaySelected == null) {
                return -1;
            }
            if (onPlaySelected2 == null) {
                return 1;
            }
            if (onPlaySelected instanceof Comparable) {
                int iCompareTo7 = ((Comparable) onPlaySelected).compareTo(onPlaySelected2);
                if (iCompareTo7 != 0) {
                    return iCompareTo7;
                }
            } else if (!onPlaySelected.equals(onPlaySelected2)) {
                int iHashCode3 = onPlaySelected.hashCode();
                int iHashCode4 = onPlaySelected2.hashCode();
                if (iHashCode3 < iHashCode4) {
                    return -1;
                }
                if (iHashCode3 > iHashCode4) {
                    return 1;
                }
            }
        }
        List<Method> onImageSelected = getOnImageSelected();
        List<Method> onImageSelected2 = featuredSingleItemElement.getOnImageSelected();
        if (onImageSelected != onImageSelected2) {
            if (onImageSelected == null) {
                return -1;
            }
            if (onImageSelected2 == null) {
                return 1;
            }
            if (onImageSelected instanceof Comparable) {
                int iCompareTo8 = ((Comparable) onImageSelected).compareTo(onImageSelected2);
                if (iCompareTo8 != 0) {
                    return iCompareTo8;
                }
            } else if (!onImageSelected.equals(onImageSelected2)) {
                int iHashCode5 = onImageSelected.hashCode();
                int iHashCode6 = onImageSelected2.hashCode();
                if (iHashCode5 < iHashCode6) {
                    return -1;
                }
                if (iHashCode5 > iHashCode6) {
                    return 1;
                }
            }
        }
        String bookmarkId = getBookmarkId();
        String bookmarkId2 = featuredSingleItemElement.getBookmarkId();
        if (bookmarkId != bookmarkId2) {
            if (bookmarkId == null) {
                return -1;
            }
            if (bookmarkId2 == null) {
                return 1;
            }
            int iCompareTo9 = bookmarkId.compareTo(bookmarkId2);
            if (iCompareTo9 != 0) {
                return iCompareTo9;
            }
        }
        Boolean boolIsIsDisabled = isIsDisabled();
        Boolean boolIsIsDisabled2 = featuredSingleItemElement.isIsDisabled();
        if (boolIsIsDisabled != boolIsIsDisabled2) {
            if (boolIsIsDisabled == null) {
                return -1;
            }
            if (boolIsIsDisabled2 == null) {
                return 1;
            }
            int iCompareTo10 = boolIsIsDisabled.compareTo(boolIsIsDisabled2);
            if (iCompareTo10 != 0) {
                return iCompareTo10;
            }
        }
        String primaryText = getPrimaryText();
        String primaryText2 = featuredSingleItemElement.getPrimaryText();
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
        String label = getLabel();
        String label2 = featuredSingleItemElement.getLabel();
        if (label != label2) {
            if (label == null) {
                return -1;
            }
            if (label2 == null) {
                return 1;
            }
            int iCompareTo12 = label.compareTo(label2);
            if (iCompareTo12 != 0) {
                return iCompareTo12;
            }
        }
        return super.compareTo(sOAObject);
    }

    @Override // SOATemplateListInterface.v1_0.TemplateElement, SOACoreInterface.v1_0.SOAObject
    public boolean equals(Object obj) {
        if (!(obj instanceof FeaturedSingleItemElement)) {
            return false;
        }
        FeaturedSingleItemElement featuredSingleItemElement = (FeaturedSingleItemElement) obj;
        return super.equals(obj) && internalEqualityCheck(getDownload(), featuredSingleItemElement.getDownload()) && internalEqualityCheck(getEpisodeOptions(), featuredSingleItemElement.getEpisodeOptions()) && internalEqualityCheck(getTotalDurationMilliseconds(), featuredSingleItemElement.getTotalDurationMilliseconds()) && internalEqualityCheck(getSecondaryText(), featuredSingleItemElement.getSecondaryText()) && internalEqualityCheck(getImage(), featuredSingleItemElement.getImage()) && internalEqualityCheck(getOnItemSelected(), featuredSingleItemElement.getOnItemSelected()) && internalEqualityCheck(getOnPlaySelected(), featuredSingleItemElement.getOnPlaySelected()) && internalEqualityCheck(getOnImageSelected(), featuredSingleItemElement.getOnImageSelected()) && internalEqualityCheck(getBookmarkId(), featuredSingleItemElement.getBookmarkId()) && internalEqualityCheck(isIsDisabled(), featuredSingleItemElement.isIsDisabled()) && internalEqualityCheck(getPrimaryText(), featuredSingleItemElement.getPrimaryText()) && internalEqualityCheck(getLabel(), featuredSingleItemElement.getLabel());
    }

    public String getBookmarkId() {
        return this.bookmarkId;
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

    public List<Method> getOnPlaySelected() {
        return this.onPlaySelected;
    }

    public String getPrimaryText() {
        return this.primaryText;
    }

    public String getSecondaryText() {
        return this.secondaryText;
    }

    public Long getTotalDurationMilliseconds() {
        return this.totalDurationMilliseconds;
    }

    @Override // SOATemplateListInterface.v1_0.TemplateElement, SOACoreInterface.v1_0.SOAObject
    public int hashCode() {
        return internalHashCodeCompute(Integer.valueOf(super.hashCode()), Integer.valueOf(classNameHashCode), getDownload(), getEpisodeOptions(), getTotalDurationMilliseconds(), getSecondaryText(), getImage(), getOnItemSelected(), getOnPlaySelected(), getOnImageSelected(), getBookmarkId(), isIsDisabled(), getPrimaryText(), getLabel());
    }

    public Boolean isIsDisabled() {
        return this.isDisabled;
    }

    public void setBookmarkId(String str) {
        this.bookmarkId = str;
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

    public void setOnPlaySelected(List<Method> list) {
        this.onPlaySelected = list;
    }

    public void setPrimaryText(String str) {
        this.primaryText = str;
    }

    public void setSecondaryText(String str) {
        this.secondaryText = str;
    }

    public void setTotalDurationMilliseconds(Long l) {
        this.totalDurationMilliseconds = l;
    }
}
