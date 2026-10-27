package Podcast.SaveInterface.v1_0;

import SOAAppSyncInterface.v1_0.WriteElement;
import java.util.Arrays;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public class SaveMetadataWriteElement extends WriteElement {
    private static final int classNameHashCode = internalHashCodeCompute("Podcast.SaveInterface.v1_0.SaveMetadataWriteElement");
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
    private Integer seasonNumber;
    private String title;

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
        protected Integer seasonNumber;
        protected String title;

        public SaveMetadataWriteElement build() {
            SaveMetadataWriteElement saveMetadataWriteElement = new SaveMetadataWriteElement();
            populate(saveMetadataWriteElement);
            return saveMetadataWriteElement;
        }

        protected void populate(SaveMetadataWriteElement saveMetadataWriteElement) {
            super.populate((WriteElement) saveMetadataWriteElement);
            saveMetadataWriteElement.setPodcastId(this.podcastId);
            saveMetadataWriteElement.setImage(this.image);
            saveMetadataWriteElement.setAuthors(this.authors);
            saveMetadataWriteElement.setTitle(this.title);
            saveMetadataWriteElement.setAvailableUpsells(this.availableUpsells);
            saveMetadataWriteElement.setDescription(this.description);
            saveMetadataWriteElement.setPodcastEpisodeVariantId(this.podcastEpisodeVariantId);
            saveMetadataWriteElement.setPublishDate(this.publishDate);
            saveMetadataWriteElement.setSeasonNumber(this.seasonNumber);
            saveMetadataWriteElement.setPlaybackMode(this.playbackMode);
            saveMetadataWriteElement.setAvailabilityDate(this.availabilityDate);
            saveMetadataWriteElement.setPodcastTitle(this.podcastTitle);
            saveMetadataWriteElement.setPodcastImage(this.podcastImage);
            saveMetadataWriteElement.setContentTraits(this.contentTraits);
            saveMetadataWriteElement.setPodcastShowVariantId(this.podcastShowVariantId);
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

        public Builder withSeasonNumber(Integer num) {
            this.seasonNumber = num;
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
        if (!(writeElement instanceof SaveMetadataWriteElement)) {
            return 1;
        }
        SaveMetadataWriteElement saveMetadataWriteElement = (SaveMetadataWriteElement) writeElement;
        String podcastId = getPodcastId();
        String podcastId2 = saveMetadataWriteElement.getPodcastId();
        if (podcastId != podcastId2) {
            if (podcastId == null) {
                return -1;
            }
            if (podcastId2 == null) {
                return 1;
            }
            int iCompareTo = podcastId.compareTo(podcastId2);
            if (iCompareTo != 0) {
                return iCompareTo;
            }
        }
        String image = getImage();
        String image2 = saveMetadataWriteElement.getImage();
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
        List<String> authors = getAuthors();
        List<String> authors2 = saveMetadataWriteElement.getAuthors();
        if (authors != authors2) {
            if (authors == null) {
                return -1;
            }
            if (authors2 == null) {
                return 1;
            }
            if (authors instanceof Comparable) {
                int iCompareTo3 = ((Comparable) authors).compareTo(authors2);
                if (iCompareTo3 != 0) {
                    return iCompareTo3;
                }
            } else if (!authors.equals(authors2)) {
                int iHashCode = authors.hashCode();
                int iHashCode2 = authors2.hashCode();
                if (iHashCode < iHashCode2) {
                    return -1;
                }
                if (iHashCode > iHashCode2) {
                    return 1;
                }
            }
        }
        String title = getTitle();
        String title2 = saveMetadataWriteElement.getTitle();
        if (title != title2) {
            if (title == null) {
                return -1;
            }
            if (title2 == null) {
                return 1;
            }
            int iCompareTo4 = title.compareTo(title2);
            if (iCompareTo4 != 0) {
                return iCompareTo4;
            }
        }
        List<String> availableUpsells = getAvailableUpsells();
        List<String> availableUpsells2 = saveMetadataWriteElement.getAvailableUpsells();
        if (availableUpsells != availableUpsells2) {
            if (availableUpsells == null) {
                return -1;
            }
            if (availableUpsells2 == null) {
                return 1;
            }
            if (availableUpsells instanceof Comparable) {
                int iCompareTo5 = ((Comparable) availableUpsells).compareTo(availableUpsells2);
                if (iCompareTo5 != 0) {
                    return iCompareTo5;
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
        String description = getDescription();
        String description2 = saveMetadataWriteElement.getDescription();
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
        String podcastEpisodeVariantId = getPodcastEpisodeVariantId();
        String podcastEpisodeVariantId2 = saveMetadataWriteElement.getPodcastEpisodeVariantId();
        if (podcastEpisodeVariantId != podcastEpisodeVariantId2) {
            if (podcastEpisodeVariantId == null) {
                return -1;
            }
            if (podcastEpisodeVariantId2 == null) {
                return 1;
            }
            int iCompareTo7 = podcastEpisodeVariantId.compareTo(podcastEpisodeVariantId2);
            if (iCompareTo7 != 0) {
                return iCompareTo7;
            }
        }
        String publishDate = getPublishDate();
        String publishDate2 = saveMetadataWriteElement.getPublishDate();
        if (publishDate != publishDate2) {
            if (publishDate == null) {
                return -1;
            }
            if (publishDate2 == null) {
                return 1;
            }
            int iCompareTo8 = publishDate.compareTo(publishDate2);
            if (iCompareTo8 != 0) {
                return iCompareTo8;
            }
        }
        Integer seasonNumber = getSeasonNumber();
        Integer seasonNumber2 = saveMetadataWriteElement.getSeasonNumber();
        if (seasonNumber != seasonNumber2) {
            if (seasonNumber == null) {
                return -1;
            }
            if (seasonNumber2 == null) {
                return 1;
            }
            int iCompareTo9 = seasonNumber.compareTo(seasonNumber2);
            if (iCompareTo9 != 0) {
                return iCompareTo9;
            }
        }
        String playbackMode = getPlaybackMode();
        String playbackMode2 = saveMetadataWriteElement.getPlaybackMode();
        if (playbackMode != playbackMode2) {
            if (playbackMode == null) {
                return -1;
            }
            if (playbackMode2 == null) {
                return 1;
            }
            int iCompareTo10 = playbackMode.compareTo(playbackMode2);
            if (iCompareTo10 != 0) {
                return iCompareTo10;
            }
        }
        String availabilityDate = getAvailabilityDate();
        String availabilityDate2 = saveMetadataWriteElement.getAvailabilityDate();
        if (availabilityDate != availabilityDate2) {
            if (availabilityDate == null) {
                return -1;
            }
            if (availabilityDate2 == null) {
                return 1;
            }
            int iCompareTo11 = availabilityDate.compareTo(availabilityDate2);
            if (iCompareTo11 != 0) {
                return iCompareTo11;
            }
        }
        String podcastTitle = getPodcastTitle();
        String podcastTitle2 = saveMetadataWriteElement.getPodcastTitle();
        if (podcastTitle != podcastTitle2) {
            if (podcastTitle == null) {
                return -1;
            }
            if (podcastTitle2 == null) {
                return 1;
            }
            int iCompareTo12 = podcastTitle.compareTo(podcastTitle2);
            if (iCompareTo12 != 0) {
                return iCompareTo12;
            }
        }
        String podcastImage = getPodcastImage();
        String podcastImage2 = saveMetadataWriteElement.getPodcastImage();
        if (podcastImage != podcastImage2) {
            if (podcastImage == null) {
                return -1;
            }
            if (podcastImage2 == null) {
                return 1;
            }
            int iCompareTo13 = podcastImage.compareTo(podcastImage2);
            if (iCompareTo13 != 0) {
                return iCompareTo13;
            }
        }
        List<String> contentTraits = getContentTraits();
        List<String> contentTraits2 = saveMetadataWriteElement.getContentTraits();
        if (contentTraits != contentTraits2) {
            if (contentTraits == null) {
                return -1;
            }
            if (contentTraits2 == null) {
                return 1;
            }
            if (contentTraits instanceof Comparable) {
                int iCompareTo14 = ((Comparable) contentTraits).compareTo(contentTraits2);
                if (iCompareTo14 != 0) {
                    return iCompareTo14;
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
        String podcastShowVariantId = getPodcastShowVariantId();
        String podcastShowVariantId2 = saveMetadataWriteElement.getPodcastShowVariantId();
        if (podcastShowVariantId != podcastShowVariantId2) {
            if (podcastShowVariantId == null) {
                return -1;
            }
            if (podcastShowVariantId2 == null) {
                return 1;
            }
            int iCompareTo15 = podcastShowVariantId.compareTo(podcastShowVariantId2);
            if (iCompareTo15 != 0) {
                return iCompareTo15;
            }
        }
        return super.compareTo(writeElement);
    }

    @Override // SOAAppSyncInterface.v1_0.WriteElement
    public boolean equals(Object obj) {
        if (!(obj instanceof SaveMetadataWriteElement)) {
            return false;
        }
        SaveMetadataWriteElement saveMetadataWriteElement = (SaveMetadataWriteElement) obj;
        return super.equals(obj) && internalEqualityCheck(getPodcastId(), saveMetadataWriteElement.getPodcastId()) && internalEqualityCheck(getImage(), saveMetadataWriteElement.getImage()) && internalEqualityCheck(getAuthors(), saveMetadataWriteElement.getAuthors()) && internalEqualityCheck(getTitle(), saveMetadataWriteElement.getTitle()) && internalEqualityCheck(getAvailableUpsells(), saveMetadataWriteElement.getAvailableUpsells()) && internalEqualityCheck(getDescription(), saveMetadataWriteElement.getDescription()) && internalEqualityCheck(getPodcastEpisodeVariantId(), saveMetadataWriteElement.getPodcastEpisodeVariantId()) && internalEqualityCheck(getPublishDate(), saveMetadataWriteElement.getPublishDate()) && internalEqualityCheck(getSeasonNumber(), saveMetadataWriteElement.getSeasonNumber()) && internalEqualityCheck(getPlaybackMode(), saveMetadataWriteElement.getPlaybackMode()) && internalEqualityCheck(getAvailabilityDate(), saveMetadataWriteElement.getAvailabilityDate()) && internalEqualityCheck(getPodcastTitle(), saveMetadataWriteElement.getPodcastTitle()) && internalEqualityCheck(getPodcastImage(), saveMetadataWriteElement.getPodcastImage()) && internalEqualityCheck(getContentTraits(), saveMetadataWriteElement.getContentTraits()) && internalEqualityCheck(getPodcastShowVariantId(), saveMetadataWriteElement.getPodcastShowVariantId());
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

    @Override // SOAAppSyncInterface.v1_0.WriteElement
    public int hashCode() {
        return internalHashCodeCompute(Integer.valueOf(super.hashCode()), Integer.valueOf(classNameHashCode), getPodcastId(), getImage(), getAuthors(), getTitle(), getAvailableUpsells(), getDescription(), getPodcastEpisodeVariantId(), getPublishDate(), getSeasonNumber(), getPlaybackMode(), getAvailabilityDate(), getPodcastTitle(), getPodcastImage(), getContentTraits(), getPodcastShowVariantId());
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

    public void setSeasonNumber(Integer num) {
        this.seasonNumber = num;
    }

    public void setTitle(String str) {
        this.title = str;
    }
}
