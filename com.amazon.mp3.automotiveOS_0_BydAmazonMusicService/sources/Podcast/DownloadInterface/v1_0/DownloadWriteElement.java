package Podcast.DownloadInterface.v1_0;

import SOAAppSyncInterface.v1_0.WriteElement;
import java.util.Arrays;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public class DownloadWriteElement extends WriteElement {
    private static final int classNameHashCode = internalHashCodeCompute("Podcast.DownloadInterface.v1_0.DownloadWriteElement");
    private String audioUri;
    private List<String> authors;
    private String availabilityDate;
    private List<String> availableUpsells;
    private List<String> contentTraits;
    private String description;
    private Long durationMilliseconds;
    private boolean explicit;
    private String image;
    private String playbackMode;
    private String podcastEpisodeVariantId;
    private String podcastId;
    private String podcastImage;
    private String podcastShowVariantId;
    private String podcastTitle;
    private String publishDate;
    private Integer seasonNumber;
    private String state;
    private String title;

    public static class Builder extends WriteElement.Builder {
        protected String audioUri;
        protected List<String> authors;
        protected String availabilityDate;
        protected List<String> availableUpsells;
        protected List<String> contentTraits;
        protected String description;
        protected Long durationMilliseconds;
        protected boolean explicit;
        protected String image;
        protected String playbackMode;
        protected String podcastEpisodeVariantId;
        protected String podcastId;
        protected String podcastImage;
        protected String podcastShowVariantId;
        protected String podcastTitle;
        protected String publishDate;
        protected Integer seasonNumber;
        protected String state;
        protected String title;

        public DownloadWriteElement build() {
            DownloadWriteElement downloadWriteElement = new DownloadWriteElement();
            populate(downloadWriteElement);
            return downloadWriteElement;
        }

        protected void populate(DownloadWriteElement downloadWriteElement) {
            super.populate((WriteElement) downloadWriteElement);
            downloadWriteElement.setPodcastTitle(this.podcastTitle);
            downloadWriteElement.setAudioUri(this.audioUri);
            downloadWriteElement.setAvailableUpsells(this.availableUpsells);
            downloadWriteElement.setPlaybackMode(this.playbackMode);
            downloadWriteElement.setTitle(this.title);
            downloadWriteElement.setPodcastShowVariantId(this.podcastShowVariantId);
            downloadWriteElement.setImage(this.image);
            downloadWriteElement.setPodcastImage(this.podcastImage);
            downloadWriteElement.setAuthors(this.authors);
            downloadWriteElement.setContentTraits(this.contentTraits);
            downloadWriteElement.setDescription(this.description);
            downloadWriteElement.setSeasonNumber(this.seasonNumber);
            downloadWriteElement.setState(this.state);
            downloadWriteElement.setPodcastEpisodeVariantId(this.podcastEpisodeVariantId);
            downloadWriteElement.setAvailabilityDate(this.availabilityDate);
            downloadWriteElement.setPodcastId(this.podcastId);
            downloadWriteElement.setDurationMilliseconds(this.durationMilliseconds);
            downloadWriteElement.setPublishDate(this.publishDate);
            downloadWriteElement.setExplicit(this.explicit);
        }

        public Builder withAudioUri(String str) {
            this.audioUri = str;
            return this;
        }

        public Builder withAuthors(List<String> list) {
            this.authors = list;
            return this;
        }

        public Builder withAvailabilityDate(String str) {
            this.availabilityDate = str;
            return this;
        }

        public Builder withAvailableUpsells(List<String> list) {
            this.availableUpsells = list;
            return this;
        }

        public Builder withContentTraits(List<String> list) {
            this.contentTraits = list;
            return this;
        }

        public Builder withDescription(String str) {
            this.description = str;
            return this;
        }

        public Builder withDurationMilliseconds(Long l) {
            this.durationMilliseconds = l;
            return this;
        }

        public Builder withExplicit(boolean z) {
            this.explicit = z;
            return this;
        }

        @Override // SOAAppSyncInterface.v1_0.WriteElement.Builder
        public Builder withId(String str) {
            super.withId(str);
            return this;
        }

        public Builder withImage(String str) {
            this.image = str;
            return this;
        }

        public Builder withPlaybackMode(String str) {
            this.playbackMode = str;
            return this;
        }

        public Builder withPodcastEpisodeVariantId(String str) {
            this.podcastEpisodeVariantId = str;
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

        public Builder withPodcastShowVariantId(String str) {
            this.podcastShowVariantId = str;
            return this;
        }

        public Builder withPodcastTitle(String str) {
            this.podcastTitle = str;
            return this;
        }

        public Builder withPublishDate(String str) {
            this.publishDate = str;
            return this;
        }

        public Builder withSeasonNumber(Integer num) {
            this.seasonNumber = num;
            return this;
        }

        public Builder withState(String str) {
            this.state = str;
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
    @Override // SOAAppSyncInterface.v1_0.WriteElement, java.lang.Comparable
    @Deprecated
    public int compareTo(@Deprecated WriteElement writeElement) {
        if (writeElement == null) {
            return -1;
        }
        if (writeElement == this) {
            return 0;
        }
        if (!(writeElement instanceof DownloadWriteElement)) {
            return 1;
        }
        DownloadWriteElement downloadWriteElement = (DownloadWriteElement) writeElement;
        String podcastTitle = getPodcastTitle();
        String podcastTitle2 = downloadWriteElement.getPodcastTitle();
        if (podcastTitle != podcastTitle2) {
            if (podcastTitle == null) {
                return -1;
            }
            if (podcastTitle2 == null) {
                return 1;
            }
            int iCompareTo = podcastTitle.compareTo(podcastTitle2);
            if (iCompareTo != 0) {
                return iCompareTo;
            }
        }
        String audioUri = getAudioUri();
        String audioUri2 = downloadWriteElement.getAudioUri();
        if (audioUri != audioUri2) {
            if (audioUri == null) {
                return -1;
            }
            if (audioUri2 == null) {
                return 1;
            }
            int iCompareTo2 = audioUri.compareTo(audioUri2);
            if (iCompareTo2 != 0) {
                return iCompareTo2;
            }
        }
        List<String> availableUpsells = getAvailableUpsells();
        List<String> availableUpsells2 = downloadWriteElement.getAvailableUpsells();
        if (availableUpsells != availableUpsells2) {
            if (availableUpsells == null) {
                return -1;
            }
            if (availableUpsells2 == null) {
                return 1;
            }
            if (availableUpsells instanceof Comparable) {
                int iCompareTo3 = ((Comparable) availableUpsells).compareTo(availableUpsells2);
                if (iCompareTo3 != 0) {
                    return iCompareTo3;
                }
            } else if (!availableUpsells.equals(availableUpsells2)) {
                int iHashCode = availableUpsells.hashCode();
                int iHashCode2 = availableUpsells2.hashCode();
                if (iHashCode < iHashCode2) {
                    return -1;
                }
                if (iHashCode > iHashCode2) {
                    return 1;
                }
            }
        }
        String playbackMode = getPlaybackMode();
        String playbackMode2 = downloadWriteElement.getPlaybackMode();
        if (playbackMode != playbackMode2) {
            if (playbackMode == null) {
                return -1;
            }
            if (playbackMode2 == null) {
                return 1;
            }
            int iCompareTo4 = playbackMode.compareTo(playbackMode2);
            if (iCompareTo4 != 0) {
                return iCompareTo4;
            }
        }
        String title = getTitle();
        String title2 = downloadWriteElement.getTitle();
        if (title != title2) {
            if (title == null) {
                return -1;
            }
            if (title2 == null) {
                return 1;
            }
            int iCompareTo5 = title.compareTo(title2);
            if (iCompareTo5 != 0) {
                return iCompareTo5;
            }
        }
        String podcastShowVariantId = getPodcastShowVariantId();
        String podcastShowVariantId2 = downloadWriteElement.getPodcastShowVariantId();
        if (podcastShowVariantId != podcastShowVariantId2) {
            if (podcastShowVariantId == null) {
                return -1;
            }
            if (podcastShowVariantId2 == null) {
                return 1;
            }
            int iCompareTo6 = podcastShowVariantId.compareTo(podcastShowVariantId2);
            if (iCompareTo6 != 0) {
                return iCompareTo6;
            }
        }
        String image = getImage();
        String image2 = downloadWriteElement.getImage();
        if (image != image2) {
            if (image == null) {
                return -1;
            }
            if (image2 == null) {
                return 1;
            }
            int iCompareTo7 = image.compareTo(image2);
            if (iCompareTo7 != 0) {
                return iCompareTo7;
            }
        }
        String podcastImage = getPodcastImage();
        String podcastImage2 = downloadWriteElement.getPodcastImage();
        if (podcastImage != podcastImage2) {
            if (podcastImage == null) {
                return -1;
            }
            if (podcastImage2 == null) {
                return 1;
            }
            int iCompareTo8 = podcastImage.compareTo(podcastImage2);
            if (iCompareTo8 != 0) {
                return iCompareTo8;
            }
        }
        List<String> authors = getAuthors();
        List<String> authors2 = downloadWriteElement.getAuthors();
        if (authors != authors2) {
            if (authors == null) {
                return -1;
            }
            if (authors2 == null) {
                return 1;
            }
            if (authors instanceof Comparable) {
                int iCompareTo9 = ((Comparable) authors).compareTo(authors2);
                if (iCompareTo9 != 0) {
                    return iCompareTo9;
                }
            } else if (!authors.equals(authors2)) {
                int iHashCode3 = authors.hashCode();
                int iHashCode4 = authors2.hashCode();
                if (iHashCode3 < iHashCode4) {
                    return -1;
                }
                if (iHashCode3 > iHashCode4) {
                    return 1;
                }
            }
        }
        List<String> contentTraits = getContentTraits();
        List<String> contentTraits2 = downloadWriteElement.getContentTraits();
        if (contentTraits != contentTraits2) {
            if (contentTraits == null) {
                return -1;
            }
            if (contentTraits2 == null) {
                return 1;
            }
            if (contentTraits instanceof Comparable) {
                int iCompareTo10 = ((Comparable) contentTraits).compareTo(contentTraits2);
                if (iCompareTo10 != 0) {
                    return iCompareTo10;
                }
            } else if (!contentTraits.equals(contentTraits2)) {
                int iHashCode5 = contentTraits.hashCode();
                int iHashCode6 = contentTraits2.hashCode();
                if (iHashCode5 < iHashCode6) {
                    return -1;
                }
                if (iHashCode5 > iHashCode6) {
                    return 1;
                }
            }
        }
        String description = getDescription();
        String description2 = downloadWriteElement.getDescription();
        if (description != description2) {
            if (description == null) {
                return -1;
            }
            if (description2 == null) {
                return 1;
            }
            int iCompareTo11 = description.compareTo(description2);
            if (iCompareTo11 != 0) {
                return iCompareTo11;
            }
        }
        Integer seasonNumber = getSeasonNumber();
        Integer seasonNumber2 = downloadWriteElement.getSeasonNumber();
        if (seasonNumber != seasonNumber2) {
            if (seasonNumber == null) {
                return -1;
            }
            if (seasonNumber2 == null) {
                return 1;
            }
            int iCompareTo12 = seasonNumber.compareTo(seasonNumber2);
            if (iCompareTo12 != 0) {
                return iCompareTo12;
            }
        }
        String state = getState();
        String state2 = downloadWriteElement.getState();
        if (state != state2) {
            if (state == null) {
                return -1;
            }
            if (state2 == null) {
                return 1;
            }
            int iCompareTo13 = state.compareTo(state2);
            if (iCompareTo13 != 0) {
                return iCompareTo13;
            }
        }
        String podcastEpisodeVariantId = getPodcastEpisodeVariantId();
        String podcastEpisodeVariantId2 = downloadWriteElement.getPodcastEpisodeVariantId();
        if (podcastEpisodeVariantId != podcastEpisodeVariantId2) {
            if (podcastEpisodeVariantId == null) {
                return -1;
            }
            if (podcastEpisodeVariantId2 == null) {
                return 1;
            }
            int iCompareTo14 = podcastEpisodeVariantId.compareTo(podcastEpisodeVariantId2);
            if (iCompareTo14 != 0) {
                return iCompareTo14;
            }
        }
        String availabilityDate = getAvailabilityDate();
        String availabilityDate2 = downloadWriteElement.getAvailabilityDate();
        if (availabilityDate != availabilityDate2) {
            if (availabilityDate == null) {
                return -1;
            }
            if (availabilityDate2 == null) {
                return 1;
            }
            int iCompareTo15 = availabilityDate.compareTo(availabilityDate2);
            if (iCompareTo15 != 0) {
                return iCompareTo15;
            }
        }
        String podcastId = getPodcastId();
        String podcastId2 = downloadWriteElement.getPodcastId();
        if (podcastId != podcastId2) {
            if (podcastId == null) {
                return -1;
            }
            if (podcastId2 == null) {
                return 1;
            }
            int iCompareTo16 = podcastId.compareTo(podcastId2);
            if (iCompareTo16 != 0) {
                return iCompareTo16;
            }
        }
        Long durationMilliseconds = getDurationMilliseconds();
        Long durationMilliseconds2 = downloadWriteElement.getDurationMilliseconds();
        if (durationMilliseconds != durationMilliseconds2) {
            if (durationMilliseconds == null) {
                return -1;
            }
            if (durationMilliseconds2 == null) {
                return 1;
            }
            int iCompareTo17 = durationMilliseconds.compareTo(durationMilliseconds2);
            if (iCompareTo17 != 0) {
                return iCompareTo17;
            }
        }
        String publishDate = getPublishDate();
        String publishDate2 = downloadWriteElement.getPublishDate();
        if (publishDate != publishDate2) {
            if (publishDate == null) {
                return -1;
            }
            if (publishDate2 == null) {
                return 1;
            }
            int iCompareTo18 = publishDate.compareTo(publishDate2);
            if (iCompareTo18 != 0) {
                return iCompareTo18;
            }
        }
        if (!isExplicit() && downloadWriteElement.isExplicit()) {
            return -1;
        }
        if (!isExplicit() || downloadWriteElement.isExplicit()) {
            return super.compareTo(writeElement);
        }
        return 1;
    }

    @Override // SOAAppSyncInterface.v1_0.WriteElement
    public boolean equals(Object obj) {
        if (!(obj instanceof DownloadWriteElement)) {
            return false;
        }
        DownloadWriteElement downloadWriteElement = (DownloadWriteElement) obj;
        return super.equals(obj) && internalEqualityCheck(getPodcastTitle(), downloadWriteElement.getPodcastTitle()) && internalEqualityCheck(getAudioUri(), downloadWriteElement.getAudioUri()) && internalEqualityCheck(getAvailableUpsells(), downloadWriteElement.getAvailableUpsells()) && internalEqualityCheck(getPlaybackMode(), downloadWriteElement.getPlaybackMode()) && internalEqualityCheck(getTitle(), downloadWriteElement.getTitle()) && internalEqualityCheck(getPodcastShowVariantId(), downloadWriteElement.getPodcastShowVariantId()) && internalEqualityCheck(getImage(), downloadWriteElement.getImage()) && internalEqualityCheck(getPodcastImage(), downloadWriteElement.getPodcastImage()) && internalEqualityCheck(getAuthors(), downloadWriteElement.getAuthors()) && internalEqualityCheck(getContentTraits(), downloadWriteElement.getContentTraits()) && internalEqualityCheck(getDescription(), downloadWriteElement.getDescription()) && internalEqualityCheck(getSeasonNumber(), downloadWriteElement.getSeasonNumber()) && internalEqualityCheck(getState(), downloadWriteElement.getState()) && internalEqualityCheck(getPodcastEpisodeVariantId(), downloadWriteElement.getPodcastEpisodeVariantId()) && internalEqualityCheck(getAvailabilityDate(), downloadWriteElement.getAvailabilityDate()) && internalEqualityCheck(getPodcastId(), downloadWriteElement.getPodcastId()) && internalEqualityCheck(getDurationMilliseconds(), downloadWriteElement.getDurationMilliseconds()) && internalEqualityCheck(getPublishDate(), downloadWriteElement.getPublishDate()) && internalEqualityCheck(Boolean.valueOf(isExplicit()), Boolean.valueOf(downloadWriteElement.isExplicit()));
    }

    public String getAudioUri() {
        return this.audioUri;
    }

    public List<String> getAuthors() {
        return this.authors;
    }

    public String getAvailabilityDate() {
        return this.availabilityDate;
    }

    public List<String> getAvailableUpsells() {
        return this.availableUpsells;
    }

    public List<String> getContentTraits() {
        return this.contentTraits;
    }

    public String getDescription() {
        return this.description;
    }

    public Long getDurationMilliseconds() {
        return this.durationMilliseconds;
    }

    public String getImage() {
        return this.image;
    }

    public String getPlaybackMode() {
        return this.playbackMode;
    }

    public String getPodcastEpisodeVariantId() {
        return this.podcastEpisodeVariantId;
    }

    public String getPodcastId() {
        return this.podcastId;
    }

    public String getPodcastImage() {
        return this.podcastImage;
    }

    public String getPodcastShowVariantId() {
        return this.podcastShowVariantId;
    }

    public String getPodcastTitle() {
        return this.podcastTitle;
    }

    public String getPublishDate() {
        return this.publishDate;
    }

    public Integer getSeasonNumber() {
        return this.seasonNumber;
    }

    public String getState() {
        return this.state;
    }

    public String getTitle() {
        return this.title;
    }

    @Override // SOAAppSyncInterface.v1_0.WriteElement
    public int hashCode() {
        return internalHashCodeCompute(Integer.valueOf(super.hashCode()), Integer.valueOf(classNameHashCode), getPodcastTitle(), getAudioUri(), getAvailableUpsells(), getPlaybackMode(), getTitle(), getPodcastShowVariantId(), getImage(), getPodcastImage(), getAuthors(), getContentTraits(), getDescription(), getSeasonNumber(), getState(), getPodcastEpisodeVariantId(), getAvailabilityDate(), getPodcastId(), getDurationMilliseconds(), getPublishDate(), Boolean.valueOf(isExplicit()));
    }

    public boolean isExplicit() {
        return this.explicit;
    }

    public void setAudioUri(String str) {
        this.audioUri = str;
    }

    public void setAuthors(List<String> list) {
        this.authors = list;
    }

    public void setAvailabilityDate(String str) {
        this.availabilityDate = str;
    }

    public void setAvailableUpsells(List<String> list) {
        this.availableUpsells = list;
    }

    public void setContentTraits(List<String> list) {
        this.contentTraits = list;
    }

    public void setDescription(String str) {
        this.description = str;
    }

    public void setDurationMilliseconds(Long l) {
        this.durationMilliseconds = l;
    }

    public void setExplicit(boolean z) {
        this.explicit = z;
    }

    public void setImage(String str) {
        this.image = str;
    }

    public void setPlaybackMode(String str) {
        this.playbackMode = str;
    }

    public void setPodcastEpisodeVariantId(String str) {
        this.podcastEpisodeVariantId = str;
    }

    public void setPodcastId(String str) {
        this.podcastId = str;
    }

    public void setPodcastImage(String str) {
        this.podcastImage = str;
    }

    public void setPodcastShowVariantId(String str) {
        this.podcastShowVariantId = str;
    }

    public void setPodcastTitle(String str) {
        this.podcastTitle = str;
    }

    public void setPublishDate(String str) {
        this.publishDate = str;
    }

    public void setSeasonNumber(Integer num) {
        this.seasonNumber = num;
    }

    public void setState(String str) {
        this.state = str;
    }

    public void setTitle(String str) {
        this.title = str;
    }
}
