package Podcast.JumpBackInInterface.v1_0;

import SOACoreInterface.v1_0.SOAObject;
import SOATemplateListInterface.v1_0.TemplateElement;
import java.util.Arrays;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public class JumpBackInCacheItemElement extends TemplateElement {
    private static final int classNameHashCode = internalHashCodeCompute("Podcast.JumpBackInInterface.v1_0.JumpBackInCacheItemElement");
    private List<String> authors;
    private String availabilityDate;
    private List<String> availableUpsells;
    private List<String> contentTraits;
    private String description;
    private String episodeId;
    private String image;
    private String playDeeplink;
    private String playbackMode;
    private String podcastEpisodeVariantId;
    private String podcastId;
    private String podcastImage;
    private String podcastShowVariantId;
    private String podcastTitle;
    private String publishDate;
    private Integer seasonNumber;
    private String showDeeplink;
    private String title;
    private Long totalDurationMilliseconds;

    public static class Builder extends TemplateElement.Builder {
        protected List<String> authors;
        protected String availabilityDate;
        protected List<String> availableUpsells;
        protected List<String> contentTraits;
        protected String description;
        protected String episodeId;
        protected String image;
        protected String playDeeplink;
        protected String playbackMode;
        protected String podcastEpisodeVariantId;
        protected String podcastId;
        protected String podcastImage;
        protected String podcastShowVariantId;
        protected String podcastTitle;
        protected String publishDate;
        protected Integer seasonNumber;
        protected String showDeeplink;
        protected String title;
        protected Long totalDurationMilliseconds;

        public JumpBackInCacheItemElement build() {
            JumpBackInCacheItemElement jumpBackInCacheItemElement = new JumpBackInCacheItemElement();
            populate(jumpBackInCacheItemElement);
            return jumpBackInCacheItemElement;
        }

        protected void populate(JumpBackInCacheItemElement jumpBackInCacheItemElement) {
            super.populate((TemplateElement) jumpBackInCacheItemElement);
            jumpBackInCacheItemElement.setPodcastImage(this.podcastImage);
            jumpBackInCacheItemElement.setAvailableUpsells(this.availableUpsells);
            jumpBackInCacheItemElement.setPodcastId(this.podcastId);
            jumpBackInCacheItemElement.setTotalDurationMilliseconds(this.totalDurationMilliseconds);
            jumpBackInCacheItemElement.setPlaybackMode(this.playbackMode);
            jumpBackInCacheItemElement.setAvailabilityDate(this.availabilityDate);
            jumpBackInCacheItemElement.setPublishDate(this.publishDate);
            jumpBackInCacheItemElement.setShowDeeplink(this.showDeeplink);
            jumpBackInCacheItemElement.setDescription(this.description);
            jumpBackInCacheItemElement.setSeasonNumber(this.seasonNumber);
            jumpBackInCacheItemElement.setPodcastEpisodeVariantId(this.podcastEpisodeVariantId);
            jumpBackInCacheItemElement.setImage(this.image);
            jumpBackInCacheItemElement.setTitle(this.title);
            jumpBackInCacheItemElement.setPodcastTitle(this.podcastTitle);
            jumpBackInCacheItemElement.setContentTraits(this.contentTraits);
            jumpBackInCacheItemElement.setEpisodeId(this.episodeId);
            jumpBackInCacheItemElement.setPlayDeeplink(this.playDeeplink);
            jumpBackInCacheItemElement.setAuthors(this.authors);
            jumpBackInCacheItemElement.setPodcastShowVariantId(this.podcastShowVariantId);
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

        public Builder withEpisodeId(String str) {
            this.episodeId = str;
            return this;
        }

        public Builder withImage(String str) {
            this.image = str;
            return this;
        }

        public Builder withPlayDeeplink(String str) {
            this.playDeeplink = str;
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

        public Builder withShowDeeplink(String str) {
            this.showDeeplink = str;
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
    @Override // SOATemplateListInterface.v1_0.TemplateElement, SOACoreInterface.v1_0.SOAObject, java.lang.Comparable
    @Deprecated
    public int compareTo(@Deprecated SOAObject sOAObject) {
        if (sOAObject == null) {
            return -1;
        }
        if (sOAObject == this) {
            return 0;
        }
        if (!(sOAObject instanceof JumpBackInCacheItemElement)) {
            return 1;
        }
        JumpBackInCacheItemElement jumpBackInCacheItemElement = (JumpBackInCacheItemElement) sOAObject;
        String podcastImage = getPodcastImage();
        String podcastImage2 = jumpBackInCacheItemElement.getPodcastImage();
        if (podcastImage != podcastImage2) {
            if (podcastImage == null) {
                return -1;
            }
            if (podcastImage2 == null) {
                return 1;
            }
            int iCompareTo = podcastImage.compareTo(podcastImage2);
            if (iCompareTo != 0) {
                return iCompareTo;
            }
        }
        List<String> availableUpsells = getAvailableUpsells();
        List<String> availableUpsells2 = jumpBackInCacheItemElement.getAvailableUpsells();
        if (availableUpsells != availableUpsells2) {
            if (availableUpsells == null) {
                return -1;
            }
            if (availableUpsells2 == null) {
                return 1;
            }
            if (availableUpsells instanceof Comparable) {
                int iCompareTo2 = ((Comparable) availableUpsells).compareTo(availableUpsells2);
                if (iCompareTo2 != 0) {
                    return iCompareTo2;
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
        String podcastId = getPodcastId();
        String podcastId2 = jumpBackInCacheItemElement.getPodcastId();
        if (podcastId != podcastId2) {
            if (podcastId == null) {
                return -1;
            }
            if (podcastId2 == null) {
                return 1;
            }
            int iCompareTo3 = podcastId.compareTo(podcastId2);
            if (iCompareTo3 != 0) {
                return iCompareTo3;
            }
        }
        Long totalDurationMilliseconds = getTotalDurationMilliseconds();
        Long totalDurationMilliseconds2 = jumpBackInCacheItemElement.getTotalDurationMilliseconds();
        if (totalDurationMilliseconds != totalDurationMilliseconds2) {
            if (totalDurationMilliseconds == null) {
                return -1;
            }
            if (totalDurationMilliseconds2 == null) {
                return 1;
            }
            int iCompareTo4 = totalDurationMilliseconds.compareTo(totalDurationMilliseconds2);
            if (iCompareTo4 != 0) {
                return iCompareTo4;
            }
        }
        String playbackMode = getPlaybackMode();
        String playbackMode2 = jumpBackInCacheItemElement.getPlaybackMode();
        if (playbackMode != playbackMode2) {
            if (playbackMode == null) {
                return -1;
            }
            if (playbackMode2 == null) {
                return 1;
            }
            int iCompareTo5 = playbackMode.compareTo(playbackMode2);
            if (iCompareTo5 != 0) {
                return iCompareTo5;
            }
        }
        String availabilityDate = getAvailabilityDate();
        String availabilityDate2 = jumpBackInCacheItemElement.getAvailabilityDate();
        if (availabilityDate != availabilityDate2) {
            if (availabilityDate == null) {
                return -1;
            }
            if (availabilityDate2 == null) {
                return 1;
            }
            int iCompareTo6 = availabilityDate.compareTo(availabilityDate2);
            if (iCompareTo6 != 0) {
                return iCompareTo6;
            }
        }
        String publishDate = getPublishDate();
        String publishDate2 = jumpBackInCacheItemElement.getPublishDate();
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
        String showDeeplink = getShowDeeplink();
        String showDeeplink2 = jumpBackInCacheItemElement.getShowDeeplink();
        if (showDeeplink != showDeeplink2) {
            if (showDeeplink == null) {
                return -1;
            }
            if (showDeeplink2 == null) {
                return 1;
            }
            int iCompareTo8 = showDeeplink.compareTo(showDeeplink2);
            if (iCompareTo8 != 0) {
                return iCompareTo8;
            }
        }
        String description = getDescription();
        String description2 = jumpBackInCacheItemElement.getDescription();
        if (description != description2) {
            if (description == null) {
                return -1;
            }
            if (description2 == null) {
                return 1;
            }
            int iCompareTo9 = description.compareTo(description2);
            if (iCompareTo9 != 0) {
                return iCompareTo9;
            }
        }
        Integer seasonNumber = getSeasonNumber();
        Integer seasonNumber2 = jumpBackInCacheItemElement.getSeasonNumber();
        if (seasonNumber != seasonNumber2) {
            if (seasonNumber == null) {
                return -1;
            }
            if (seasonNumber2 == null) {
                return 1;
            }
            int iCompareTo10 = seasonNumber.compareTo(seasonNumber2);
            if (iCompareTo10 != 0) {
                return iCompareTo10;
            }
        }
        String podcastEpisodeVariantId = getPodcastEpisodeVariantId();
        String podcastEpisodeVariantId2 = jumpBackInCacheItemElement.getPodcastEpisodeVariantId();
        if (podcastEpisodeVariantId != podcastEpisodeVariantId2) {
            if (podcastEpisodeVariantId == null) {
                return -1;
            }
            if (podcastEpisodeVariantId2 == null) {
                return 1;
            }
            int iCompareTo11 = podcastEpisodeVariantId.compareTo(podcastEpisodeVariantId2);
            if (iCompareTo11 != 0) {
                return iCompareTo11;
            }
        }
        String image = getImage();
        String image2 = jumpBackInCacheItemElement.getImage();
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
        String title = getTitle();
        String title2 = jumpBackInCacheItemElement.getTitle();
        if (title != title2) {
            if (title == null) {
                return -1;
            }
            if (title2 == null) {
                return 1;
            }
            int iCompareTo13 = title.compareTo(title2);
            if (iCompareTo13 != 0) {
                return iCompareTo13;
            }
        }
        String podcastTitle = getPodcastTitle();
        String podcastTitle2 = jumpBackInCacheItemElement.getPodcastTitle();
        if (podcastTitle != podcastTitle2) {
            if (podcastTitle == null) {
                return -1;
            }
            if (podcastTitle2 == null) {
                return 1;
            }
            int iCompareTo14 = podcastTitle.compareTo(podcastTitle2);
            if (iCompareTo14 != 0) {
                return iCompareTo14;
            }
        }
        List<String> contentTraits = getContentTraits();
        List<String> contentTraits2 = jumpBackInCacheItemElement.getContentTraits();
        if (contentTraits != contentTraits2) {
            if (contentTraits == null) {
                return -1;
            }
            if (contentTraits2 == null) {
                return 1;
            }
            if (contentTraits instanceof Comparable) {
                int iCompareTo15 = ((Comparable) contentTraits).compareTo(contentTraits2);
                if (iCompareTo15 != 0) {
                    return iCompareTo15;
                }
            } else if (!contentTraits.equals(contentTraits2)) {
                int iHashCode3 = contentTraits.hashCode();
                int iHashCode4 = contentTraits2.hashCode();
                if (iHashCode3 < iHashCode4) {
                    return -1;
                }
                if (iHashCode3 > iHashCode4) {
                    return 1;
                }
            }
        }
        String episodeId = getEpisodeId();
        String episodeId2 = jumpBackInCacheItemElement.getEpisodeId();
        if (episodeId != episodeId2) {
            if (episodeId == null) {
                return -1;
            }
            if (episodeId2 == null) {
                return 1;
            }
            int iCompareTo16 = episodeId.compareTo(episodeId2);
            if (iCompareTo16 != 0) {
                return iCompareTo16;
            }
        }
        String playDeeplink = getPlayDeeplink();
        String playDeeplink2 = jumpBackInCacheItemElement.getPlayDeeplink();
        if (playDeeplink != playDeeplink2) {
            if (playDeeplink == null) {
                return -1;
            }
            if (playDeeplink2 == null) {
                return 1;
            }
            int iCompareTo17 = playDeeplink.compareTo(playDeeplink2);
            if (iCompareTo17 != 0) {
                return iCompareTo17;
            }
        }
        List<String> authors = getAuthors();
        List<String> authors2 = jumpBackInCacheItemElement.getAuthors();
        if (authors != authors2) {
            if (authors == null) {
                return -1;
            }
            if (authors2 == null) {
                return 1;
            }
            if (authors instanceof Comparable) {
                int iCompareTo18 = ((Comparable) authors).compareTo(authors2);
                if (iCompareTo18 != 0) {
                    return iCompareTo18;
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
        String podcastShowVariantId = getPodcastShowVariantId();
        String podcastShowVariantId2 = jumpBackInCacheItemElement.getPodcastShowVariantId();
        if (podcastShowVariantId != podcastShowVariantId2) {
            if (podcastShowVariantId == null) {
                return -1;
            }
            if (podcastShowVariantId2 == null) {
                return 1;
            }
            int iCompareTo19 = podcastShowVariantId.compareTo(podcastShowVariantId2);
            if (iCompareTo19 != 0) {
                return iCompareTo19;
            }
        }
        return super.compareTo(sOAObject);
    }

    @Override // SOATemplateListInterface.v1_0.TemplateElement, SOACoreInterface.v1_0.SOAObject
    public boolean equals(Object obj) {
        if (!(obj instanceof JumpBackInCacheItemElement)) {
            return false;
        }
        JumpBackInCacheItemElement jumpBackInCacheItemElement = (JumpBackInCacheItemElement) obj;
        return super.equals(obj) && internalEqualityCheck(getPodcastImage(), jumpBackInCacheItemElement.getPodcastImage()) && internalEqualityCheck(getAvailableUpsells(), jumpBackInCacheItemElement.getAvailableUpsells()) && internalEqualityCheck(getPodcastId(), jumpBackInCacheItemElement.getPodcastId()) && internalEqualityCheck(getTotalDurationMilliseconds(), jumpBackInCacheItemElement.getTotalDurationMilliseconds()) && internalEqualityCheck(getPlaybackMode(), jumpBackInCacheItemElement.getPlaybackMode()) && internalEqualityCheck(getAvailabilityDate(), jumpBackInCacheItemElement.getAvailabilityDate()) && internalEqualityCheck(getPublishDate(), jumpBackInCacheItemElement.getPublishDate()) && internalEqualityCheck(getShowDeeplink(), jumpBackInCacheItemElement.getShowDeeplink()) && internalEqualityCheck(getDescription(), jumpBackInCacheItemElement.getDescription()) && internalEqualityCheck(getSeasonNumber(), jumpBackInCacheItemElement.getSeasonNumber()) && internalEqualityCheck(getPodcastEpisodeVariantId(), jumpBackInCacheItemElement.getPodcastEpisodeVariantId()) && internalEqualityCheck(getImage(), jumpBackInCacheItemElement.getImage()) && internalEqualityCheck(getTitle(), jumpBackInCacheItemElement.getTitle()) && internalEqualityCheck(getPodcastTitle(), jumpBackInCacheItemElement.getPodcastTitle()) && internalEqualityCheck(getContentTraits(), jumpBackInCacheItemElement.getContentTraits()) && internalEqualityCheck(getEpisodeId(), jumpBackInCacheItemElement.getEpisodeId()) && internalEqualityCheck(getPlayDeeplink(), jumpBackInCacheItemElement.getPlayDeeplink()) && internalEqualityCheck(getAuthors(), jumpBackInCacheItemElement.getAuthors()) && internalEqualityCheck(getPodcastShowVariantId(), jumpBackInCacheItemElement.getPodcastShowVariantId());
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

    public String getEpisodeId() {
        return this.episodeId;
    }

    public String getImage() {
        return this.image;
    }

    public String getPlayDeeplink() {
        return this.playDeeplink;
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

    public String getShowDeeplink() {
        return this.showDeeplink;
    }

    public String getTitle() {
        return this.title;
    }

    public Long getTotalDurationMilliseconds() {
        return this.totalDurationMilliseconds;
    }

    @Override // SOATemplateListInterface.v1_0.TemplateElement, SOACoreInterface.v1_0.SOAObject
    public int hashCode() {
        return internalHashCodeCompute(Integer.valueOf(super.hashCode()), Integer.valueOf(classNameHashCode), getPodcastImage(), getAvailableUpsells(), getPodcastId(), getTotalDurationMilliseconds(), getPlaybackMode(), getAvailabilityDate(), getPublishDate(), getShowDeeplink(), getDescription(), getSeasonNumber(), getPodcastEpisodeVariantId(), getImage(), getTitle(), getPodcastTitle(), getContentTraits(), getEpisodeId(), getPlayDeeplink(), getAuthors(), getPodcastShowVariantId());
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

    public void setEpisodeId(String str) {
        this.episodeId = str;
    }

    public void setImage(String str) {
        this.image = str;
    }

    public void setPlayDeeplink(String str) {
        this.playDeeplink = str;
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

    public void setShowDeeplink(String str) {
        this.showDeeplink = str;
    }

    public void setTitle(String str) {
        this.title = str;
    }

    public void setTotalDurationMilliseconds(Long l) {
        this.totalDurationMilliseconds = l;
    }
}
