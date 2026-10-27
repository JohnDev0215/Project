package Podcast.SaveInterface.v1_0;

import SOAAppSyncInterface.v1_0.WriteElement;
import java.util.Arrays;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public class SaveWriteElement extends WriteElement {
    private static final int classNameHashCode = internalHashCodeCompute("Podcast.SaveInterface.v1_0.SaveWriteElement");
    private List<String> authors;
    private String availabilityDate;
    private List<String> availableUpsells;
    private List<String> contentTraits;
    private String description;
    private String image;
    private String playbackMode;
    private String podcastEpisodeVariantId;
    private String podcastId;
    private String podcastImage;
    private String podcastShowVariantId;
    private String podcastTitle;
    private String publishDate;
    private boolean saved;
    private Integer seasonNumber;
    private String title;
    private Long totalDurationMilliseconds;

    public static class Builder extends WriteElement.Builder {
        protected List<String> authors;
        protected String availabilityDate;
        protected List<String> availableUpsells;
        protected List<String> contentTraits;
        protected String description;
        protected String image;
        protected String playbackMode;
        protected String podcastEpisodeVariantId;
        protected String podcastId;
        protected String podcastImage;
        protected String podcastShowVariantId;
        protected String podcastTitle;
        protected String publishDate;
        protected boolean saved;
        protected Integer seasonNumber;
        protected String title;
        protected Long totalDurationMilliseconds;

        public SaveWriteElement build() {
            SaveWriteElement saveWriteElement = new SaveWriteElement();
            populate(saveWriteElement);
            return saveWriteElement;
        }

        protected void populate(SaveWriteElement saveWriteElement) {
            super.populate((WriteElement) saveWriteElement);
            saveWriteElement.setPodcastShowVariantId(this.podcastShowVariantId);
            saveWriteElement.setSaved(this.saved);
            saveWriteElement.setDescription(this.description);
            saveWriteElement.setContentTraits(this.contentTraits);
            saveWriteElement.setSeasonNumber(this.seasonNumber);
            saveWriteElement.setPodcastEpisodeVariantId(this.podcastEpisodeVariantId);
            saveWriteElement.setPodcastId(this.podcastId);
            saveWriteElement.setPublishDate(this.publishDate);
            saveWriteElement.setTotalDurationMilliseconds(this.totalDurationMilliseconds);
            saveWriteElement.setAvailabilityDate(this.availabilityDate);
            saveWriteElement.setAvailableUpsells(this.availableUpsells);
            saveWriteElement.setPodcastImage(this.podcastImage);
            saveWriteElement.setPlaybackMode(this.playbackMode);
            saveWriteElement.setImage(this.image);
            saveWriteElement.setAuthors(this.authors);
            saveWriteElement.setPodcastTitle(this.podcastTitle);
            saveWriteElement.setTitle(this.title);
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

        public Builder withSaved(boolean z) {
            this.saved = z;
            return this;
        }

        public Builder withSeasonNumber(Integer num) {
            this.seasonNumber = num;
            return this;
        }

        public Builder withTitle(String str) {
            this.title = str;
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
    @Override // SOAAppSyncInterface.v1_0.WriteElement, java.lang.Comparable
    @Deprecated
    public int compareTo(@Deprecated WriteElement writeElement) {
        if (writeElement == null) {
            return -1;
        }
        if (writeElement == this) {
            return 0;
        }
        if (!(writeElement instanceof SaveWriteElement)) {
            return 1;
        }
        SaveWriteElement saveWriteElement = (SaveWriteElement) writeElement;
        String podcastShowVariantId = getPodcastShowVariantId();
        String podcastShowVariantId2 = saveWriteElement.getPodcastShowVariantId();
        if (podcastShowVariantId != podcastShowVariantId2) {
            if (podcastShowVariantId == null) {
                return -1;
            }
            if (podcastShowVariantId2 == null) {
                return 1;
            }
            int iCompareTo = podcastShowVariantId.compareTo(podcastShowVariantId2);
            if (iCompareTo != 0) {
                return iCompareTo;
            }
        }
        if (!isSaved() && saveWriteElement.isSaved()) {
            return -1;
        }
        if (isSaved() && !saveWriteElement.isSaved()) {
            return 1;
        }
        String description = getDescription();
        String description2 = saveWriteElement.getDescription();
        if (description != description2) {
            if (description == null) {
                return -1;
            }
            if (description2 == null) {
                return 1;
            }
            int iCompareTo2 = description.compareTo(description2);
            if (iCompareTo2 != 0) {
                return iCompareTo2;
            }
        }
        List<String> contentTraits = getContentTraits();
        List<String> contentTraits2 = saveWriteElement.getContentTraits();
        if (contentTraits != contentTraits2) {
            if (contentTraits == null) {
                return -1;
            }
            if (contentTraits2 == null) {
                return 1;
            }
            if (contentTraits instanceof Comparable) {
                int iCompareTo3 = ((Comparable) contentTraits).compareTo(contentTraits2);
                if (iCompareTo3 != 0) {
                    return iCompareTo3;
                }
            } else if (!contentTraits.equals(contentTraits2)) {
                int iHashCode = contentTraits.hashCode();
                int iHashCode2 = contentTraits2.hashCode();
                if (iHashCode < iHashCode2) {
                    return -1;
                }
                if (iHashCode > iHashCode2) {
                    return 1;
                }
            }
        }
        Integer seasonNumber = getSeasonNumber();
        Integer seasonNumber2 = saveWriteElement.getSeasonNumber();
        if (seasonNumber != seasonNumber2) {
            if (seasonNumber == null) {
                return -1;
            }
            if (seasonNumber2 == null) {
                return 1;
            }
            int iCompareTo4 = seasonNumber.compareTo(seasonNumber2);
            if (iCompareTo4 != 0) {
                return iCompareTo4;
            }
        }
        String podcastEpisodeVariantId = getPodcastEpisodeVariantId();
        String podcastEpisodeVariantId2 = saveWriteElement.getPodcastEpisodeVariantId();
        if (podcastEpisodeVariantId != podcastEpisodeVariantId2) {
            if (podcastEpisodeVariantId == null) {
                return -1;
            }
            if (podcastEpisodeVariantId2 == null) {
                return 1;
            }
            int iCompareTo5 = podcastEpisodeVariantId.compareTo(podcastEpisodeVariantId2);
            if (iCompareTo5 != 0) {
                return iCompareTo5;
            }
        }
        String podcastId = getPodcastId();
        String podcastId2 = saveWriteElement.getPodcastId();
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
        String publishDate = getPublishDate();
        String publishDate2 = saveWriteElement.getPublishDate();
        if (publishDate != publishDate2) {
            if (publishDate == null) {
                return -1;
            }
            if (publishDate2 == null) {
                return 1;
            }
            int iCompareTo7 = publishDate.compareTo(publishDate2);
            if (iCompareTo7 != 0) {
                return iCompareTo7;
            }
        }
        Long totalDurationMilliseconds = getTotalDurationMilliseconds();
        Long totalDurationMilliseconds2 = saveWriteElement.getTotalDurationMilliseconds();
        if (totalDurationMilliseconds != totalDurationMilliseconds2) {
            if (totalDurationMilliseconds == null) {
                return -1;
            }
            if (totalDurationMilliseconds2 == null) {
                return 1;
            }
            int iCompareTo8 = totalDurationMilliseconds.compareTo(totalDurationMilliseconds2);
            if (iCompareTo8 != 0) {
                return iCompareTo8;
            }
        }
        String availabilityDate = getAvailabilityDate();
        String availabilityDate2 = saveWriteElement.getAvailabilityDate();
        if (availabilityDate != availabilityDate2) {
            if (availabilityDate == null) {
                return -1;
            }
            if (availabilityDate2 == null) {
                return 1;
            }
            int iCompareTo9 = availabilityDate.compareTo(availabilityDate2);
            if (iCompareTo9 != 0) {
                return iCompareTo9;
            }
        }
        List<String> availableUpsells = getAvailableUpsells();
        List<String> availableUpsells2 = saveWriteElement.getAvailableUpsells();
        if (availableUpsells != availableUpsells2) {
            if (availableUpsells == null) {
                return -1;
            }
            if (availableUpsells2 == null) {
                return 1;
            }
            if (availableUpsells instanceof Comparable) {
                int iCompareTo10 = ((Comparable) availableUpsells).compareTo(availableUpsells2);
                if (iCompareTo10 != 0) {
                    return iCompareTo10;
                }
            } else if (!availableUpsells.equals(availableUpsells2)) {
                int iHashCode3 = availableUpsells.hashCode();
                int iHashCode4 = availableUpsells2.hashCode();
                if (iHashCode3 < iHashCode4) {
                    return -1;
                }
                if (iHashCode3 > iHashCode4) {
                    return 1;
                }
            }
        }
        String podcastImage = getPodcastImage();
        String podcastImage2 = saveWriteElement.getPodcastImage();
        if (podcastImage != podcastImage2) {
            if (podcastImage == null) {
                return -1;
            }
            if (podcastImage2 == null) {
                return 1;
            }
            int iCompareTo11 = podcastImage.compareTo(podcastImage2);
            if (iCompareTo11 != 0) {
                return iCompareTo11;
            }
        }
        String playbackMode = getPlaybackMode();
        String playbackMode2 = saveWriteElement.getPlaybackMode();
        if (playbackMode != playbackMode2) {
            if (playbackMode == null) {
                return -1;
            }
            if (playbackMode2 == null) {
                return 1;
            }
            int iCompareTo12 = playbackMode.compareTo(playbackMode2);
            if (iCompareTo12 != 0) {
                return iCompareTo12;
            }
        }
        String image = getImage();
        String image2 = saveWriteElement.getImage();
        if (image != image2) {
            if (image == null) {
                return -1;
            }
            if (image2 == null) {
                return 1;
            }
            int iCompareTo13 = image.compareTo(image2);
            if (iCompareTo13 != 0) {
                return iCompareTo13;
            }
        }
        List<String> authors = getAuthors();
        List<String> authors2 = saveWriteElement.getAuthors();
        if (authors != authors2) {
            if (authors == null) {
                return -1;
            }
            if (authors2 == null) {
                return 1;
            }
            if (authors instanceof Comparable) {
                int iCompareTo14 = ((Comparable) authors).compareTo(authors2);
                if (iCompareTo14 != 0) {
                    return iCompareTo14;
                }
            } else if (!authors.equals(authors2)) {
                int iHashCode5 = authors.hashCode();
                int iHashCode6 = authors2.hashCode();
                if (iHashCode5 < iHashCode6) {
                    return -1;
                }
                if (iHashCode5 > iHashCode6) {
                    return 1;
                }
            }
        }
        String podcastTitle = getPodcastTitle();
        String podcastTitle2 = saveWriteElement.getPodcastTitle();
        if (podcastTitle != podcastTitle2) {
            if (podcastTitle == null) {
                return -1;
            }
            if (podcastTitle2 == null) {
                return 1;
            }
            int iCompareTo15 = podcastTitle.compareTo(podcastTitle2);
            if (iCompareTo15 != 0) {
                return iCompareTo15;
            }
        }
        String title = getTitle();
        String title2 = saveWriteElement.getTitle();
        if (title != title2) {
            if (title == null) {
                return -1;
            }
            if (title2 == null) {
                return 1;
            }
            int iCompareTo16 = title.compareTo(title2);
            if (iCompareTo16 != 0) {
                return iCompareTo16;
            }
        }
        return super.compareTo(writeElement);
    }

    @Override // SOAAppSyncInterface.v1_0.WriteElement
    public boolean equals(Object obj) {
        if (!(obj instanceof SaveWriteElement)) {
            return false;
        }
        SaveWriteElement saveWriteElement = (SaveWriteElement) obj;
        return super.equals(obj) && internalEqualityCheck(getPodcastShowVariantId(), saveWriteElement.getPodcastShowVariantId()) && internalEqualityCheck(Boolean.valueOf(isSaved()), Boolean.valueOf(saveWriteElement.isSaved())) && internalEqualityCheck(getDescription(), saveWriteElement.getDescription()) && internalEqualityCheck(getContentTraits(), saveWriteElement.getContentTraits()) && internalEqualityCheck(getSeasonNumber(), saveWriteElement.getSeasonNumber()) && internalEqualityCheck(getPodcastEpisodeVariantId(), saveWriteElement.getPodcastEpisodeVariantId()) && internalEqualityCheck(getPodcastId(), saveWriteElement.getPodcastId()) && internalEqualityCheck(getPublishDate(), saveWriteElement.getPublishDate()) && internalEqualityCheck(getTotalDurationMilliseconds(), saveWriteElement.getTotalDurationMilliseconds()) && internalEqualityCheck(getAvailabilityDate(), saveWriteElement.getAvailabilityDate()) && internalEqualityCheck(getAvailableUpsells(), saveWriteElement.getAvailableUpsells()) && internalEqualityCheck(getPodcastImage(), saveWriteElement.getPodcastImage()) && internalEqualityCheck(getPlaybackMode(), saveWriteElement.getPlaybackMode()) && internalEqualityCheck(getImage(), saveWriteElement.getImage()) && internalEqualityCheck(getAuthors(), saveWriteElement.getAuthors()) && internalEqualityCheck(getPodcastTitle(), saveWriteElement.getPodcastTitle()) && internalEqualityCheck(getTitle(), saveWriteElement.getTitle());
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

    public String getTitle() {
        return this.title;
    }

    public Long getTotalDurationMilliseconds() {
        return this.totalDurationMilliseconds;
    }

    @Override // SOAAppSyncInterface.v1_0.WriteElement
    public int hashCode() {
        return internalHashCodeCompute(Integer.valueOf(super.hashCode()), Integer.valueOf(classNameHashCode), getPodcastShowVariantId(), Boolean.valueOf(isSaved()), getDescription(), getContentTraits(), getSeasonNumber(), getPodcastEpisodeVariantId(), getPodcastId(), getPublishDate(), getTotalDurationMilliseconds(), getAvailabilityDate(), getAvailableUpsells(), getPodcastImage(), getPlaybackMode(), getImage(), getAuthors(), getPodcastTitle(), getTitle());
    }

    public boolean isSaved() {
        return this.saved;
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

    public void setSaved(boolean z) {
        this.saved = z;
    }

    public void setSeasonNumber(Integer num) {
        this.seasonNumber = num;
    }

    public void setTitle(String str) {
        this.title = str;
    }

    public void setTotalDurationMilliseconds(Long l) {
        this.totalDurationMilliseconds = l;
    }
}
