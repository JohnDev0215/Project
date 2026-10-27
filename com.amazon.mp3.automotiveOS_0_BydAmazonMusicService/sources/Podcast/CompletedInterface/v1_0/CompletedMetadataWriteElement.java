package Podcast.CompletedInterface.v1_0;

import SOAAppSyncInterface.v1_0.WriteElement;
import java.util.Arrays;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public class CompletedMetadataWriteElement extends WriteElement {
    private static final int classNameHashCode = internalHashCodeCompute("Podcast.CompletedInterface.v1_0.CompletedMetadataWriteElement");
    private List<String> authors;
    private String description;
    private String image;
    private String podcastId;
    private String podcastImage;
    private String podcastTitle;
    private String publishDate;
    private Integer seasonNumber;
    private String title;

    public static class Builder extends WriteElement.Builder {
        protected List<String> authors;
        protected String description;
        protected String image;
        protected String podcastId;
        protected String podcastImage;
        protected String podcastTitle;
        protected String publishDate;
        protected Integer seasonNumber;
        protected String title;

        public CompletedMetadataWriteElement build() {
            CompletedMetadataWriteElement completedMetadataWriteElement = new CompletedMetadataWriteElement();
            populate(completedMetadataWriteElement);
            return completedMetadataWriteElement;
        }

        protected void populate(CompletedMetadataWriteElement completedMetadataWriteElement) {
            super.populate((WriteElement) completedMetadataWriteElement);
            completedMetadataWriteElement.setDescription(this.description);
            completedMetadataWriteElement.setAuthors(this.authors);
            completedMetadataWriteElement.setPodcastImage(this.podcastImage);
            completedMetadataWriteElement.setSeasonNumber(this.seasonNumber);
            completedMetadataWriteElement.setImage(this.image);
            completedMetadataWriteElement.setPublishDate(this.publishDate);
            completedMetadataWriteElement.setPodcastId(this.podcastId);
            completedMetadataWriteElement.setPodcastTitle(this.podcastTitle);
            completedMetadataWriteElement.setTitle(this.title);
        }

        public Builder withAuthors(List<String> list) {
            this.authors = list;
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

        public Builder withPodcastId(String str) {
            this.podcastId = str;
            return this;
        }

        public Builder withPodcastImage(String str) {
            this.podcastImage = str;
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
        if (!(writeElement instanceof CompletedMetadataWriteElement)) {
            return 1;
        }
        CompletedMetadataWriteElement completedMetadataWriteElement = (CompletedMetadataWriteElement) writeElement;
        String description = getDescription();
        String description2 = completedMetadataWriteElement.getDescription();
        if (description != description2) {
            if (description == null) {
                return -1;
            }
            if (description2 == null) {
                return 1;
            }
            int iCompareTo = description.compareTo(description2);
            if (iCompareTo != 0) {
                return iCompareTo;
            }
        }
        List<String> authors = getAuthors();
        List<String> authors2 = completedMetadataWriteElement.getAuthors();
        if (authors != authors2) {
            if (authors == null) {
                return -1;
            }
            if (authors2 == null) {
                return 1;
            }
            if (authors instanceof Comparable) {
                int iCompareTo2 = ((Comparable) authors).compareTo(authors2);
                if (iCompareTo2 != 0) {
                    return iCompareTo2;
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
        String podcastImage = getPodcastImage();
        String podcastImage2 = completedMetadataWriteElement.getPodcastImage();
        if (podcastImage != podcastImage2) {
            if (podcastImage == null) {
                return -1;
            }
            if (podcastImage2 == null) {
                return 1;
            }
            int iCompareTo3 = podcastImage.compareTo(podcastImage2);
            if (iCompareTo3 != 0) {
                return iCompareTo3;
            }
        }
        Integer seasonNumber = getSeasonNumber();
        Integer seasonNumber2 = completedMetadataWriteElement.getSeasonNumber();
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
        String image = getImage();
        String image2 = completedMetadataWriteElement.getImage();
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
        String publishDate = getPublishDate();
        String publishDate2 = completedMetadataWriteElement.getPublishDate();
        if (publishDate != publishDate2) {
            if (publishDate == null) {
                return -1;
            }
            if (publishDate2 == null) {
                return 1;
            }
            int iCompareTo6 = publishDate.compareTo(publishDate2);
            if (iCompareTo6 != 0) {
                return iCompareTo6;
            }
        }
        String podcastId = getPodcastId();
        String podcastId2 = completedMetadataWriteElement.getPodcastId();
        if (podcastId != podcastId2) {
            if (podcastId == null) {
                return -1;
            }
            if (podcastId2 == null) {
                return 1;
            }
            int iCompareTo7 = podcastId.compareTo(podcastId2);
            if (iCompareTo7 != 0) {
                return iCompareTo7;
            }
        }
        String podcastTitle = getPodcastTitle();
        String podcastTitle2 = completedMetadataWriteElement.getPodcastTitle();
        if (podcastTitle != podcastTitle2) {
            if (podcastTitle == null) {
                return -1;
            }
            if (podcastTitle2 == null) {
                return 1;
            }
            int iCompareTo8 = podcastTitle.compareTo(podcastTitle2);
            if (iCompareTo8 != 0) {
                return iCompareTo8;
            }
        }
        String title = getTitle();
        String title2 = completedMetadataWriteElement.getTitle();
        if (title != title2) {
            if (title == null) {
                return -1;
            }
            if (title2 == null) {
                return 1;
            }
            int iCompareTo9 = title.compareTo(title2);
            if (iCompareTo9 != 0) {
                return iCompareTo9;
            }
        }
        return super.compareTo(writeElement);
    }

    @Override // SOAAppSyncInterface.v1_0.WriteElement
    public boolean equals(Object obj) {
        if (!(obj instanceof CompletedMetadataWriteElement)) {
            return false;
        }
        CompletedMetadataWriteElement completedMetadataWriteElement = (CompletedMetadataWriteElement) obj;
        return super.equals(obj) && internalEqualityCheck(getDescription(), completedMetadataWriteElement.getDescription()) && internalEqualityCheck(getAuthors(), completedMetadataWriteElement.getAuthors()) && internalEqualityCheck(getPodcastImage(), completedMetadataWriteElement.getPodcastImage()) && internalEqualityCheck(getSeasonNumber(), completedMetadataWriteElement.getSeasonNumber()) && internalEqualityCheck(getImage(), completedMetadataWriteElement.getImage()) && internalEqualityCheck(getPublishDate(), completedMetadataWriteElement.getPublishDate()) && internalEqualityCheck(getPodcastId(), completedMetadataWriteElement.getPodcastId()) && internalEqualityCheck(getPodcastTitle(), completedMetadataWriteElement.getPodcastTitle()) && internalEqualityCheck(getTitle(), completedMetadataWriteElement.getTitle());
    }

    public List<String> getAuthors() {
        return this.authors;
    }

    public String getDescription() {
        return this.description;
    }

    public String getImage() {
        return this.image;
    }

    public String getPodcastId() {
        return this.podcastId;
    }

    public String getPodcastImage() {
        return this.podcastImage;
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
        return internalHashCodeCompute(Integer.valueOf(super.hashCode()), Integer.valueOf(classNameHashCode), getDescription(), getAuthors(), getPodcastImage(), getSeasonNumber(), getImage(), getPublishDate(), getPodcastId(), getPodcastTitle(), getTitle());
    }

    public void setAuthors(List<String> list) {
        this.authors = list;
    }

    public void setDescription(String str) {
        this.description = str;
    }

    public void setImage(String str) {
        this.image = str;
    }

    public void setPodcastId(String str) {
        this.podcastId = str;
    }

    public void setPodcastImage(String str) {
        this.podcastImage = str;
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
