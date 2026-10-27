package Podcast.Desktop.PodcastDetailTemplateInterface.v1_0;

import Podcast.EpisodeOptionsInterface.v1_0.EpisodeOptionsElement;
import SOACacheInterface.v1_0.WriteCacheElement;
import SOACoreInterface.v1_0.Method;
import SOACoreInterface.v1_0.SOAObject;
import java.util.Arrays;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public class FeaturedItemWriteCacheElement extends WriteCacheElement {
    private static final int classNameHashCode = internalHashCodeCompute("Podcast.Desktop.PodcastDetailTemplateInterface.v1_0.FeaturedItemWriteCacheElement");
    private BookmarkElement bookmark;
    private DownloadElement download;
    private String episodeId;
    private EpisodeOptionsElement episodeOptions;
    private String image;
    private Boolean isDisabled;
    private String label;
    private List<Method> onImageSelected;
    private List<Method> onItemSelected;
    private List<Method> onPlaySelected;
    private String podcastId;
    private String podcastImage;
    private String primaryText;
    private String secondaryText;
    private String tertiaryText;

    public static class Builder extends WriteCacheElement.Builder {
        protected BookmarkElement bookmark;
        protected DownloadElement download;
        protected String episodeId;
        protected EpisodeOptionsElement episodeOptions;
        protected String image;
        protected Boolean isDisabled;
        protected String label;
        protected List<Method> onImageSelected;
        protected List<Method> onItemSelected;
        protected List<Method> onPlaySelected;
        protected String podcastId;
        protected String podcastImage;
        protected String primaryText;
        protected String secondaryText;
        protected String tertiaryText;

        @Override // SOACacheInterface.v1_0.WriteCacheElement.Builder
        public FeaturedItemWriteCacheElement build() {
            FeaturedItemWriteCacheElement featuredItemWriteCacheElement = new FeaturedItemWriteCacheElement();
            populate(featuredItemWriteCacheElement);
            return featuredItemWriteCacheElement;
        }

        protected void populate(FeaturedItemWriteCacheElement featuredItemWriteCacheElement) {
            super.populate((WriteCacheElement) featuredItemWriteCacheElement);
            featuredItemWriteCacheElement.setSecondaryText(this.secondaryText);
            featuredItemWriteCacheElement.setPodcastImage(this.podcastImage);
            featuredItemWriteCacheElement.setLabel(this.label);
            featuredItemWriteCacheElement.setOnPlaySelected(this.onPlaySelected);
            featuredItemWriteCacheElement.setOnImageSelected(this.onImageSelected);
            featuredItemWriteCacheElement.setPodcastId(this.podcastId);
            featuredItemWriteCacheElement.setDownload(this.download);
            featuredItemWriteCacheElement.setImage(this.image);
            featuredItemWriteCacheElement.setOnItemSelected(this.onItemSelected);
            featuredItemWriteCacheElement.setBookmark(this.bookmark);
            featuredItemWriteCacheElement.setEpisodeId(this.episodeId);
            featuredItemWriteCacheElement.setPrimaryText(this.primaryText);
            featuredItemWriteCacheElement.setIsDisabled(this.isDisabled);
            featuredItemWriteCacheElement.setTertiaryText(this.tertiaryText);
            featuredItemWriteCacheElement.setEpisodeOptions(this.episodeOptions);
        }

        public Builder withBookmark(BookmarkElement bookmarkElement) {
            this.bookmark = bookmarkElement;
            return this;
        }

        public Builder withDownload(DownloadElement downloadElement) {
            this.download = downloadElement;
            return this;
        }

        public Builder withEpisodeId(String str) {
            this.episodeId = str;
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

        @Override // SOACacheInterface.v1_0.WriteCacheElement.Builder
        public Builder withOnInvalidated(List<Method> list) {
            super.withOnInvalidated(list);
            return this;
        }

        @Override // SOACacheInterface.v1_0.WriteCacheElement.Builder
        public /* bridge */ /* synthetic */ WriteCacheElement.Builder withOnInvalidated(List list) {
            return withOnInvalidated((List<Method>) list);
        }

        public Builder withOnItemSelected(List<Method> list) {
            this.onItemSelected = list;
            return this;
        }

        public Builder withOnPlaySelected(List<Method> list) {
            this.onPlaySelected = list;
            return this;
        }

        public Builder withPodcastId(String str) {
            this.podcastId = str;
            return this;
        }

        public Builder withPodcastImage(String str) {
            this.podcastImage = str;
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
    @Override // SOACacheInterface.v1_0.WriteCacheElement, SOACoreInterface.v1_0.SOAObject, java.lang.Comparable
    @Deprecated
    public int compareTo(@Deprecated SOAObject sOAObject) {
        if (sOAObject == null) {
            return -1;
        }
        if (sOAObject == this) {
            return 0;
        }
        if (!(sOAObject instanceof FeaturedItemWriteCacheElement)) {
            return 1;
        }
        FeaturedItemWriteCacheElement featuredItemWriteCacheElement = (FeaturedItemWriteCacheElement) sOAObject;
        String secondaryText = getSecondaryText();
        String secondaryText2 = featuredItemWriteCacheElement.getSecondaryText();
        if (secondaryText != secondaryText2) {
            if (secondaryText == null) {
                return -1;
            }
            if (secondaryText2 == null) {
                return 1;
            }
            int iCompareTo = secondaryText.compareTo(secondaryText2);
            if (iCompareTo != 0) {
                return iCompareTo;
            }
        }
        String podcastImage = getPodcastImage();
        String podcastImage2 = featuredItemWriteCacheElement.getPodcastImage();
        if (podcastImage != podcastImage2) {
            if (podcastImage == null) {
                return -1;
            }
            if (podcastImage2 == null) {
                return 1;
            }
            int iCompareTo2 = podcastImage.compareTo(podcastImage2);
            if (iCompareTo2 != 0) {
                return iCompareTo2;
            }
        }
        String label = getLabel();
        String label2 = featuredItemWriteCacheElement.getLabel();
        if (label != label2) {
            if (label == null) {
                return -1;
            }
            if (label2 == null) {
                return 1;
            }
            int iCompareTo3 = label.compareTo(label2);
            if (iCompareTo3 != 0) {
                return iCompareTo3;
            }
        }
        List<Method> onPlaySelected = getOnPlaySelected();
        List<Method> onPlaySelected2 = featuredItemWriteCacheElement.getOnPlaySelected();
        if (onPlaySelected != onPlaySelected2) {
            if (onPlaySelected == null) {
                return -1;
            }
            if (onPlaySelected2 == null) {
                return 1;
            }
            if (onPlaySelected instanceof Comparable) {
                int iCompareTo4 = ((Comparable) onPlaySelected).compareTo(onPlaySelected2);
                if (iCompareTo4 != 0) {
                    return iCompareTo4;
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
        List<Method> onImageSelected2 = featuredItemWriteCacheElement.getOnImageSelected();
        if (onImageSelected != onImageSelected2) {
            if (onImageSelected == null) {
                return -1;
            }
            if (onImageSelected2 == null) {
                return 1;
            }
            if (onImageSelected instanceof Comparable) {
                int iCompareTo5 = ((Comparable) onImageSelected).compareTo(onImageSelected2);
                if (iCompareTo5 != 0) {
                    return iCompareTo5;
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
        String podcastId = getPodcastId();
        String podcastId2 = featuredItemWriteCacheElement.getPodcastId();
        if (podcastId != podcastId2) {
            if (podcastId == null) {
                return -1;
            }
            if (podcastId2 == null) {
                return 1;
            }
            int iCompareTo6 = podcastId.compareTo(podcastId2);
            if (iCompareTo6 != 0) {
                return iCompareTo6;
            }
        }
        DownloadElement download = getDownload();
        DownloadElement download2 = featuredItemWriteCacheElement.getDownload();
        if (download != download2) {
            if (download == null) {
                return -1;
            }
            if (download2 == null) {
                return 1;
            }
            int iCompareTo7 = download.compareTo(download2);
            if (iCompareTo7 != 0) {
                return iCompareTo7;
            }
        }
        String image = getImage();
        String image2 = featuredItemWriteCacheElement.getImage();
        if (image != image2) {
            if (image == null) {
                return -1;
            }
            if (image2 == null) {
                return 1;
            }
            int iCompareTo8 = image.compareTo(image2);
            if (iCompareTo8 != 0) {
                return iCompareTo8;
            }
        }
        List<Method> onItemSelected = getOnItemSelected();
        List<Method> onItemSelected2 = featuredItemWriteCacheElement.getOnItemSelected();
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
        BookmarkElement bookmark2 = featuredItemWriteCacheElement.getBookmark();
        if (bookmark != bookmark2) {
            if (bookmark == null) {
                return -1;
            }
            if (bookmark2 == null) {
                return 1;
            }
            int iCompareTo10 = bookmark.compareTo(bookmark2);
            if (iCompareTo10 != 0) {
                return iCompareTo10;
            }
        }
        String episodeId = getEpisodeId();
        String episodeId2 = featuredItemWriteCacheElement.getEpisodeId();
        if (episodeId != episodeId2) {
            if (episodeId == null) {
                return -1;
            }
            if (episodeId2 == null) {
                return 1;
            }
            int iCompareTo11 = episodeId.compareTo(episodeId2);
            if (iCompareTo11 != 0) {
                return iCompareTo11;
            }
        }
        String primaryText = getPrimaryText();
        String primaryText2 = featuredItemWriteCacheElement.getPrimaryText();
        if (primaryText != primaryText2) {
            if (primaryText == null) {
                return -1;
            }
            if (primaryText2 == null) {
                return 1;
            }
            int iCompareTo12 = primaryText.compareTo(primaryText2);
            if (iCompareTo12 != 0) {
                return iCompareTo12;
            }
        }
        Boolean boolIsIsDisabled = isIsDisabled();
        Boolean boolIsIsDisabled2 = featuredItemWriteCacheElement.isIsDisabled();
        if (boolIsIsDisabled != boolIsIsDisabled2) {
            if (boolIsIsDisabled == null) {
                return -1;
            }
            if (boolIsIsDisabled2 == null) {
                return 1;
            }
            int iCompareTo13 = boolIsIsDisabled.compareTo(boolIsIsDisabled2);
            if (iCompareTo13 != 0) {
                return iCompareTo13;
            }
        }
        String tertiaryText = getTertiaryText();
        String tertiaryText2 = featuredItemWriteCacheElement.getTertiaryText();
        if (tertiaryText != tertiaryText2) {
            if (tertiaryText == null) {
                return -1;
            }
            if (tertiaryText2 == null) {
                return 1;
            }
            int iCompareTo14 = tertiaryText.compareTo(tertiaryText2);
            if (iCompareTo14 != 0) {
                return iCompareTo14;
            }
        }
        EpisodeOptionsElement episodeOptions = getEpisodeOptions();
        EpisodeOptionsElement episodeOptions2 = featuredItemWriteCacheElement.getEpisodeOptions();
        if (episodeOptions != episodeOptions2) {
            if (episodeOptions == null) {
                return -1;
            }
            if (episodeOptions2 == null) {
                return 1;
            }
            int iCompareTo15 = episodeOptions.compareTo(episodeOptions2);
            if (iCompareTo15 != 0) {
                return iCompareTo15;
            }
        }
        return super.compareTo(sOAObject);
    }

    @Override // SOACacheInterface.v1_0.WriteCacheElement, SOACoreInterface.v1_0.SOAObject
    public boolean equals(Object obj) {
        if (!(obj instanceof FeaturedItemWriteCacheElement)) {
            return false;
        }
        FeaturedItemWriteCacheElement featuredItemWriteCacheElement = (FeaturedItemWriteCacheElement) obj;
        return super.equals(obj) && internalEqualityCheck(getSecondaryText(), featuredItemWriteCacheElement.getSecondaryText()) && internalEqualityCheck(getPodcastImage(), featuredItemWriteCacheElement.getPodcastImage()) && internalEqualityCheck(getLabel(), featuredItemWriteCacheElement.getLabel()) && internalEqualityCheck(getOnPlaySelected(), featuredItemWriteCacheElement.getOnPlaySelected()) && internalEqualityCheck(getOnImageSelected(), featuredItemWriteCacheElement.getOnImageSelected()) && internalEqualityCheck(getPodcastId(), featuredItemWriteCacheElement.getPodcastId()) && internalEqualityCheck(getDownload(), featuredItemWriteCacheElement.getDownload()) && internalEqualityCheck(getImage(), featuredItemWriteCacheElement.getImage()) && internalEqualityCheck(getOnItemSelected(), featuredItemWriteCacheElement.getOnItemSelected()) && internalEqualityCheck(getBookmark(), featuredItemWriteCacheElement.getBookmark()) && internalEqualityCheck(getEpisodeId(), featuredItemWriteCacheElement.getEpisodeId()) && internalEqualityCheck(getPrimaryText(), featuredItemWriteCacheElement.getPrimaryText()) && internalEqualityCheck(isIsDisabled(), featuredItemWriteCacheElement.isIsDisabled()) && internalEqualityCheck(getTertiaryText(), featuredItemWriteCacheElement.getTertiaryText()) && internalEqualityCheck(getEpisodeOptions(), featuredItemWriteCacheElement.getEpisodeOptions());
    }

    public BookmarkElement getBookmark() {
        return this.bookmark;
    }

    public DownloadElement getDownload() {
        return this.download;
    }

    public String getEpisodeId() {
        return this.episodeId;
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

    public String getPodcastId() {
        return this.podcastId;
    }

    public String getPodcastImage() {
        return this.podcastImage;
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

    @Override // SOACacheInterface.v1_0.WriteCacheElement, SOACoreInterface.v1_0.SOAObject
    public int hashCode() {
        return internalHashCodeCompute(Integer.valueOf(super.hashCode()), Integer.valueOf(classNameHashCode), getSecondaryText(), getPodcastImage(), getLabel(), getOnPlaySelected(), getOnImageSelected(), getPodcastId(), getDownload(), getImage(), getOnItemSelected(), getBookmark(), getEpisodeId(), getPrimaryText(), isIsDisabled(), getTertiaryText(), getEpisodeOptions());
    }

    public Boolean isIsDisabled() {
        return this.isDisabled;
    }

    public void setBookmark(BookmarkElement bookmarkElement) {
        this.bookmark = bookmarkElement;
    }

    public void setDownload(DownloadElement downloadElement) {
        this.download = downloadElement;
    }

    public void setEpisodeId(String str) {
        this.episodeId = str;
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

    public void setPodcastId(String str) {
        this.podcastId = str;
    }

    public void setPodcastImage(String str) {
        this.podcastImage = str;
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
