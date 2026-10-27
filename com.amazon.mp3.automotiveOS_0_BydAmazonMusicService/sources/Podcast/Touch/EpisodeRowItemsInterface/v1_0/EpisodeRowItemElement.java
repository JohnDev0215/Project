package Podcast.Touch.EpisodeRowItemsInterface.v1_0;

import Podcast.EpisodeOptionsInterface.v1_0.EpisodeOptionsElement;
import SOACoreInterface.v1_0.Method;
import SOACoreInterface.v1_0.SOAObject;
import SOATemplateListInterface.v1_0.TemplateElement;
import java.util.Arrays;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public class EpisodeRowItemElement extends TemplateElement {
    private static final int classNameHashCode = internalHashCodeCompute("Podcast.Touch.EpisodeRowItemsInterface.v1_0.EpisodeRowItemElement");
    private BookmarkElement bookmark;
    private ButtonElement button;
    private CompletedElement completed;
    private String description;
    private BadgeElement descriptionBadge;
    private DownloadElement download;
    private EpisodeOptionsElement episodeOptions;
    private String image;
    private BadgeElement imageBadge;
    private String label;
    private String mediaId;
    private List<Method> onItemSelected;
    private List<Method> onPauseSelected;
    private List<Method> onPlaySelected;
    private List<Method> onResumeSelected;
    private String playbackMode;
    private String primaryText;
    private String secondaryText;
    private String tertiaryText;

    public static class Builder extends TemplateElement.Builder {
        protected BookmarkElement bookmark;
        protected ButtonElement button;
        protected CompletedElement completed;
        protected String description;
        protected BadgeElement descriptionBadge;
        protected DownloadElement download;
        protected EpisodeOptionsElement episodeOptions;
        protected String image;
        protected BadgeElement imageBadge;
        protected String label;
        protected String mediaId;
        protected List<Method> onItemSelected;
        protected List<Method> onPauseSelected;
        protected List<Method> onPlaySelected;
        protected List<Method> onResumeSelected;
        protected String playbackMode;
        protected String primaryText;
        protected String secondaryText;
        protected String tertiaryText;

        public EpisodeRowItemElement build() {
            EpisodeRowItemElement episodeRowItemElement = new EpisodeRowItemElement();
            populate(episodeRowItemElement);
            return episodeRowItemElement;
        }

        protected void populate(EpisodeRowItemElement episodeRowItemElement) {
            super.populate((TemplateElement) episodeRowItemElement);
            episodeRowItemElement.setDownload(this.download);
            episodeRowItemElement.setSecondaryText(this.secondaryText);
            episodeRowItemElement.setEpisodeOptions(this.episodeOptions);
            episodeRowItemElement.setLabel(this.label);
            episodeRowItemElement.setOnResumeSelected(this.onResumeSelected);
            episodeRowItemElement.setTertiaryText(this.tertiaryText);
            episodeRowItemElement.setDescriptionBadge(this.descriptionBadge);
            episodeRowItemElement.setPlaybackMode(this.playbackMode);
            episodeRowItemElement.setOnPlaySelected(this.onPlaySelected);
            episodeRowItemElement.setImageBadge(this.imageBadge);
            episodeRowItemElement.setButton(this.button);
            episodeRowItemElement.setBookmark(this.bookmark);
            episodeRowItemElement.setOnPauseSelected(this.onPauseSelected);
            episodeRowItemElement.setCompleted(this.completed);
            episodeRowItemElement.setPrimaryText(this.primaryText);
            episodeRowItemElement.setDescription(this.description);
            episodeRowItemElement.setOnItemSelected(this.onItemSelected);
            episodeRowItemElement.setMediaId(this.mediaId);
            episodeRowItemElement.setImage(this.image);
        }

        public Builder withBookmark(BookmarkElement bookmarkElement) {
            this.bookmark = bookmarkElement;
            return this;
        }

        public Builder withButton(ButtonElement buttonElement) {
            this.button = buttonElement;
            return this;
        }

        public Builder withCompleted(CompletedElement completedElement) {
            this.completed = completedElement;
            return this;
        }

        public Builder withDescription(String str) {
            this.description = str;
            return this;
        }

        public Builder withDescriptionBadge(BadgeElement badgeElement) {
            this.descriptionBadge = badgeElement;
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

        public Builder withImageBadge(BadgeElement badgeElement) {
            this.imageBadge = badgeElement;
            return this;
        }

        public Builder withLabel(String str) {
            this.label = str;
            return this;
        }

        public Builder withMediaId(String str) {
            this.mediaId = str;
            return this;
        }

        public Builder withOnItemSelected(List<Method> list) {
            this.onItemSelected = list;
            return this;
        }

        public Builder withOnPauseSelected(List<Method> list) {
            this.onPauseSelected = list;
            return this;
        }

        public Builder withOnPlaySelected(List<Method> list) {
            this.onPlaySelected = list;
            return this;
        }

        public Builder withOnResumeSelected(List<Method> list) {
            this.onResumeSelected = list;
            return this;
        }

        public Builder withPlaybackMode(String str) {
            this.playbackMode = str;
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
        if (!(sOAObject instanceof EpisodeRowItemElement)) {
            return 1;
        }
        EpisodeRowItemElement episodeRowItemElement = (EpisodeRowItemElement) sOAObject;
        DownloadElement download = getDownload();
        DownloadElement download2 = episodeRowItemElement.getDownload();
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
        String secondaryText = getSecondaryText();
        String secondaryText2 = episodeRowItemElement.getSecondaryText();
        if (secondaryText != secondaryText2) {
            if (secondaryText == null) {
                return -1;
            }
            if (secondaryText2 == null) {
                return 1;
            }
            int iCompareTo2 = secondaryText.compareTo(secondaryText2);
            if (iCompareTo2 != 0) {
                return iCompareTo2;
            }
        }
        EpisodeOptionsElement episodeOptions = getEpisodeOptions();
        EpisodeOptionsElement episodeOptions2 = episodeRowItemElement.getEpisodeOptions();
        if (episodeOptions != episodeOptions2) {
            if (episodeOptions == null) {
                return -1;
            }
            if (episodeOptions2 == null) {
                return 1;
            }
            int iCompareTo3 = episodeOptions.compareTo(episodeOptions2);
            if (iCompareTo3 != 0) {
                return iCompareTo3;
            }
        }
        String label = getLabel();
        String label2 = episodeRowItemElement.getLabel();
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
        List<Method> onResumeSelected = getOnResumeSelected();
        List<Method> onResumeSelected2 = episodeRowItemElement.getOnResumeSelected();
        if (onResumeSelected != onResumeSelected2) {
            if (onResumeSelected == null) {
                return -1;
            }
            if (onResumeSelected2 == null) {
                return 1;
            }
            if (onResumeSelected instanceof Comparable) {
                int iCompareTo5 = ((Comparable) onResumeSelected).compareTo(onResumeSelected2);
                if (iCompareTo5 != 0) {
                    return iCompareTo5;
                }
            } else if (!onResumeSelected.equals(onResumeSelected2)) {
                int iHashCode = onResumeSelected.hashCode();
                int iHashCode2 = onResumeSelected2.hashCode();
                if (iHashCode < iHashCode2) {
                    return -1;
                }
                if (iHashCode > iHashCode2) {
                    return 1;
                }
            }
        }
        String tertiaryText = getTertiaryText();
        String tertiaryText2 = episodeRowItemElement.getTertiaryText();
        if (tertiaryText != tertiaryText2) {
            if (tertiaryText == null) {
                return -1;
            }
            if (tertiaryText2 == null) {
                return 1;
            }
            int iCompareTo6 = tertiaryText.compareTo(tertiaryText2);
            if (iCompareTo6 != 0) {
                return iCompareTo6;
            }
        }
        BadgeElement descriptionBadge = getDescriptionBadge();
        BadgeElement descriptionBadge2 = episodeRowItemElement.getDescriptionBadge();
        if (descriptionBadge != descriptionBadge2) {
            if (descriptionBadge == null) {
                return -1;
            }
            if (descriptionBadge2 == null) {
                return 1;
            }
            int iCompareTo7 = descriptionBadge.compareTo(descriptionBadge2);
            if (iCompareTo7 != 0) {
                return iCompareTo7;
            }
        }
        String playbackMode = getPlaybackMode();
        String playbackMode2 = episodeRowItemElement.getPlaybackMode();
        if (playbackMode != playbackMode2) {
            if (playbackMode == null) {
                return -1;
            }
            if (playbackMode2 == null) {
                return 1;
            }
            int iCompareTo8 = playbackMode.compareTo(playbackMode2);
            if (iCompareTo8 != 0) {
                return iCompareTo8;
            }
        }
        List<Method> onPlaySelected = getOnPlaySelected();
        List<Method> onPlaySelected2 = episodeRowItemElement.getOnPlaySelected();
        if (onPlaySelected != onPlaySelected2) {
            if (onPlaySelected == null) {
                return -1;
            }
            if (onPlaySelected2 == null) {
                return 1;
            }
            if (onPlaySelected instanceof Comparable) {
                int iCompareTo9 = ((Comparable) onPlaySelected).compareTo(onPlaySelected2);
                if (iCompareTo9 != 0) {
                    return iCompareTo9;
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
        BadgeElement imageBadge = getImageBadge();
        BadgeElement imageBadge2 = episodeRowItemElement.getImageBadge();
        if (imageBadge != imageBadge2) {
            if (imageBadge == null) {
                return -1;
            }
            if (imageBadge2 == null) {
                return 1;
            }
            int iCompareTo10 = imageBadge.compareTo(imageBadge2);
            if (iCompareTo10 != 0) {
                return iCompareTo10;
            }
        }
        ButtonElement button = getButton();
        ButtonElement button2 = episodeRowItemElement.getButton();
        if (button != button2) {
            if (button == null) {
                return -1;
            }
            if (button2 == null) {
                return 1;
            }
            int iCompareTo11 = button.compareTo(button2);
            if (iCompareTo11 != 0) {
                return iCompareTo11;
            }
        }
        BookmarkElement bookmark = getBookmark();
        BookmarkElement bookmark2 = episodeRowItemElement.getBookmark();
        if (bookmark != bookmark2) {
            if (bookmark == null) {
                return -1;
            }
            if (bookmark2 == null) {
                return 1;
            }
            int iCompareTo12 = bookmark.compareTo(bookmark2);
            if (iCompareTo12 != 0) {
                return iCompareTo12;
            }
        }
        List<Method> onPauseSelected = getOnPauseSelected();
        List<Method> onPauseSelected2 = episodeRowItemElement.getOnPauseSelected();
        if (onPauseSelected != onPauseSelected2) {
            if (onPauseSelected == null) {
                return -1;
            }
            if (onPauseSelected2 == null) {
                return 1;
            }
            if (onPauseSelected instanceof Comparable) {
                int iCompareTo13 = ((Comparable) onPauseSelected).compareTo(onPauseSelected2);
                if (iCompareTo13 != 0) {
                    return iCompareTo13;
                }
            } else if (!onPauseSelected.equals(onPauseSelected2)) {
                int iHashCode5 = onPauseSelected.hashCode();
                int iHashCode6 = onPauseSelected2.hashCode();
                if (iHashCode5 < iHashCode6) {
                    return -1;
                }
                if (iHashCode5 > iHashCode6) {
                    return 1;
                }
            }
        }
        CompletedElement completed = getCompleted();
        CompletedElement completed2 = episodeRowItemElement.getCompleted();
        if (completed != completed2) {
            if (completed == null) {
                return -1;
            }
            if (completed2 == null) {
                return 1;
            }
            int iCompareTo14 = completed.compareTo(completed2);
            if (iCompareTo14 != 0) {
                return iCompareTo14;
            }
        }
        String primaryText = getPrimaryText();
        String primaryText2 = episodeRowItemElement.getPrimaryText();
        if (primaryText != primaryText2) {
            if (primaryText == null) {
                return -1;
            }
            if (primaryText2 == null) {
                return 1;
            }
            int iCompareTo15 = primaryText.compareTo(primaryText2);
            if (iCompareTo15 != 0) {
                return iCompareTo15;
            }
        }
        String description = getDescription();
        String description2 = episodeRowItemElement.getDescription();
        if (description != description2) {
            if (description == null) {
                return -1;
            }
            if (description2 == null) {
                return 1;
            }
            int iCompareTo16 = description.compareTo(description2);
            if (iCompareTo16 != 0) {
                return iCompareTo16;
            }
        }
        List<Method> onItemSelected = getOnItemSelected();
        List<Method> onItemSelected2 = episodeRowItemElement.getOnItemSelected();
        if (onItemSelected != onItemSelected2) {
            if (onItemSelected == null) {
                return -1;
            }
            if (onItemSelected2 == null) {
                return 1;
            }
            if (onItemSelected instanceof Comparable) {
                int iCompareTo17 = ((Comparable) onItemSelected).compareTo(onItemSelected2);
                if (iCompareTo17 != 0) {
                    return iCompareTo17;
                }
            } else if (!onItemSelected.equals(onItemSelected2)) {
                int iHashCode7 = onItemSelected.hashCode();
                int iHashCode8 = onItemSelected2.hashCode();
                if (iHashCode7 < iHashCode8) {
                    return -1;
                }
                if (iHashCode7 > iHashCode8) {
                    return 1;
                }
            }
        }
        String mediaId = getMediaId();
        String mediaId2 = episodeRowItemElement.getMediaId();
        if (mediaId != mediaId2) {
            if (mediaId == null) {
                return -1;
            }
            if (mediaId2 == null) {
                return 1;
            }
            int iCompareTo18 = mediaId.compareTo(mediaId2);
            if (iCompareTo18 != 0) {
                return iCompareTo18;
            }
        }
        String image = getImage();
        String image2 = episodeRowItemElement.getImage();
        if (image != image2) {
            if (image == null) {
                return -1;
            }
            if (image2 == null) {
                return 1;
            }
            int iCompareTo19 = image.compareTo(image2);
            if (iCompareTo19 != 0) {
                return iCompareTo19;
            }
        }
        return super.compareTo(sOAObject);
    }

    @Override // SOATemplateListInterface.v1_0.TemplateElement, SOACoreInterface.v1_0.SOAObject
    public boolean equals(Object obj) {
        if (!(obj instanceof EpisodeRowItemElement)) {
            return false;
        }
        EpisodeRowItemElement episodeRowItemElement = (EpisodeRowItemElement) obj;
        return super.equals(obj) && internalEqualityCheck(getDownload(), episodeRowItemElement.getDownload()) && internalEqualityCheck(getSecondaryText(), episodeRowItemElement.getSecondaryText()) && internalEqualityCheck(getEpisodeOptions(), episodeRowItemElement.getEpisodeOptions()) && internalEqualityCheck(getLabel(), episodeRowItemElement.getLabel()) && internalEqualityCheck(getOnResumeSelected(), episodeRowItemElement.getOnResumeSelected()) && internalEqualityCheck(getTertiaryText(), episodeRowItemElement.getTertiaryText()) && internalEqualityCheck(getDescriptionBadge(), episodeRowItemElement.getDescriptionBadge()) && internalEqualityCheck(getPlaybackMode(), episodeRowItemElement.getPlaybackMode()) && internalEqualityCheck(getOnPlaySelected(), episodeRowItemElement.getOnPlaySelected()) && internalEqualityCheck(getImageBadge(), episodeRowItemElement.getImageBadge()) && internalEqualityCheck(getButton(), episodeRowItemElement.getButton()) && internalEqualityCheck(getBookmark(), episodeRowItemElement.getBookmark()) && internalEqualityCheck(getOnPauseSelected(), episodeRowItemElement.getOnPauseSelected()) && internalEqualityCheck(getCompleted(), episodeRowItemElement.getCompleted()) && internalEqualityCheck(getPrimaryText(), episodeRowItemElement.getPrimaryText()) && internalEqualityCheck(getDescription(), episodeRowItemElement.getDescription()) && internalEqualityCheck(getOnItemSelected(), episodeRowItemElement.getOnItemSelected()) && internalEqualityCheck(getMediaId(), episodeRowItemElement.getMediaId()) && internalEqualityCheck(getImage(), episodeRowItemElement.getImage());
    }

    public BookmarkElement getBookmark() {
        return this.bookmark;
    }

    public ButtonElement getButton() {
        return this.button;
    }

    public CompletedElement getCompleted() {
        return this.completed;
    }

    public String getDescription() {
        return this.description;
    }

    public BadgeElement getDescriptionBadge() {
        return this.descriptionBadge;
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

    public BadgeElement getImageBadge() {
        return this.imageBadge;
    }

    public String getLabel() {
        return this.label;
    }

    public String getMediaId() {
        return this.mediaId;
    }

    public List<Method> getOnItemSelected() {
        return this.onItemSelected;
    }

    public List<Method> getOnPauseSelected() {
        return this.onPauseSelected;
    }

    public List<Method> getOnPlaySelected() {
        return this.onPlaySelected;
    }

    public List<Method> getOnResumeSelected() {
        return this.onResumeSelected;
    }

    public String getPlaybackMode() {
        return this.playbackMode;
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
        return internalHashCodeCompute(Integer.valueOf(super.hashCode()), Integer.valueOf(classNameHashCode), getDownload(), getSecondaryText(), getEpisodeOptions(), getLabel(), getOnResumeSelected(), getTertiaryText(), getDescriptionBadge(), getPlaybackMode(), getOnPlaySelected(), getImageBadge(), getButton(), getBookmark(), getOnPauseSelected(), getCompleted(), getPrimaryText(), getDescription(), getOnItemSelected(), getMediaId(), getImage());
    }

    public void setBookmark(BookmarkElement bookmarkElement) {
        this.bookmark = bookmarkElement;
    }

    public void setButton(ButtonElement buttonElement) {
        this.button = buttonElement;
    }

    public void setCompleted(CompletedElement completedElement) {
        this.completed = completedElement;
    }

    public void setDescription(String str) {
        this.description = str;
    }

    public void setDescriptionBadge(BadgeElement badgeElement) {
        this.descriptionBadge = badgeElement;
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

    public void setImageBadge(BadgeElement badgeElement) {
        this.imageBadge = badgeElement;
    }

    public void setLabel(String str) {
        this.label = str;
    }

    public void setMediaId(String str) {
        this.mediaId = str;
    }

    public void setOnItemSelected(List<Method> list) {
        this.onItemSelected = list;
    }

    public void setOnPauseSelected(List<Method> list) {
        this.onPauseSelected = list;
    }

    public void setOnPlaySelected(List<Method> list) {
        this.onPlaySelected = list;
    }

    public void setOnResumeSelected(List<Method> list) {
        this.onResumeSelected = list;
    }

    public void setPlaybackMode(String str) {
        this.playbackMode = str;
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
