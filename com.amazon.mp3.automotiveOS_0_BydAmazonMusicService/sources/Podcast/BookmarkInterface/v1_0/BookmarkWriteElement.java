package Podcast.BookmarkInterface.v1_0;

import SOAAppSyncInterface.v1_0.WriteElement;
import java.util.Arrays;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public class BookmarkWriteElement extends WriteElement {
    private static final int classNameHashCode = internalHashCodeCompute("Podcast.BookmarkInterface.v1_0.BookmarkWriteElement");
    private List<String> authors;
    private String description;
    private String image;
    private String podcastId;
    private String podcastImage;
    private String podcastTitle;
    private Long progressMilliSeconds;
    private String publishDate;
    private Integer seasonNumber;
    private String title;
    private Long totalDurationMilliseconds;

    public static class Builder extends WriteElement.Builder {
        protected List<String> authors;
        protected String description;
        protected String image;
        protected String podcastId;
        protected String podcastImage;
        protected String podcastTitle;
        protected Long progressMilliSeconds;
        protected String publishDate;
        protected Integer seasonNumber;
        protected String title;
        protected Long totalDurationMilliseconds;

        public BookmarkWriteElement build() {
            BookmarkWriteElement bookmarkWriteElement = new BookmarkWriteElement();
            populate(bookmarkWriteElement);
            return bookmarkWriteElement;
        }

        protected void populate(BookmarkWriteElement bookmarkWriteElement) {
            super.populate((WriteElement) bookmarkWriteElement);
            bookmarkWriteElement.setPodcastId(this.podcastId);
            bookmarkWriteElement.setTotalDurationMilliseconds(this.totalDurationMilliseconds);
            bookmarkWriteElement.setProgressMilliSeconds(this.progressMilliSeconds);
            bookmarkWriteElement.setTitle(this.title);
            bookmarkWriteElement.setPublishDate(this.publishDate);
            bookmarkWriteElement.setImage(this.image);
            bookmarkWriteElement.setAuthors(this.authors);
            bookmarkWriteElement.setPodcastImage(this.podcastImage);
            bookmarkWriteElement.setPodcastTitle(this.podcastTitle);
            bookmarkWriteElement.setDescription(this.description);
            bookmarkWriteElement.setSeasonNumber(this.seasonNumber);
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

        public Builder withProgressMilliSeconds(Long l) {
            this.progressMilliSeconds = l;
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
        if (!(writeElement instanceof BookmarkWriteElement)) {
            return 1;
        }
        BookmarkWriteElement bookmarkWriteElement = (BookmarkWriteElement) writeElement;
        String podcastId = getPodcastId();
        String podcastId2 = bookmarkWriteElement.getPodcastId();
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
        Long totalDurationMilliseconds = getTotalDurationMilliseconds();
        Long totalDurationMilliseconds2 = bookmarkWriteElement.getTotalDurationMilliseconds();
        if (totalDurationMilliseconds != totalDurationMilliseconds2) {
            if (totalDurationMilliseconds == null) {
                return -1;
            }
            if (totalDurationMilliseconds2 == null) {
                return 1;
            }
            int iCompareTo2 = totalDurationMilliseconds.compareTo(totalDurationMilliseconds2);
            if (iCompareTo2 != 0) {
                return iCompareTo2;
            }
        }
        Long progressMilliSeconds = getProgressMilliSeconds();
        Long progressMilliSeconds2 = bookmarkWriteElement.getProgressMilliSeconds();
        if (progressMilliSeconds != progressMilliSeconds2) {
            if (progressMilliSeconds == null) {
                return -1;
            }
            if (progressMilliSeconds2 == null) {
                return 1;
            }
            int iCompareTo3 = progressMilliSeconds.compareTo(progressMilliSeconds2);
            if (iCompareTo3 != 0) {
                return iCompareTo3;
            }
        }
        String title = getTitle();
        String title2 = bookmarkWriteElement.getTitle();
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
        String publishDate = getPublishDate();
        String publishDate2 = bookmarkWriteElement.getPublishDate();
        if (publishDate != publishDate2) {
            if (publishDate == null) {
                return -1;
            }
            if (publishDate2 == null) {
                return 1;
            }
            int iCompareTo5 = publishDate.compareTo(publishDate2);
            if (iCompareTo5 != 0) {
                return iCompareTo5;
            }
        }
        String image = getImage();
        String image2 = bookmarkWriteElement.getImage();
        if (image != image2) {
            if (image == null) {
                return -1;
            }
            if (image2 == null) {
                return 1;
            }
            int iCompareTo6 = image.compareTo(image2);
            if (iCompareTo6 != 0) {
                return iCompareTo6;
            }
        }
        List<String> authors = getAuthors();
        List<String> authors2 = bookmarkWriteElement.getAuthors();
        if (authors != authors2) {
            if (authors == null) {
                return -1;
            }
            if (authors2 == null) {
                return 1;
            }
            if (authors instanceof Comparable) {
                int iCompareTo7 = ((Comparable) authors).compareTo(authors2);
                if (iCompareTo7 != 0) {
                    return iCompareTo7;
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
        String podcastImage2 = bookmarkWriteElement.getPodcastImage();
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
        String podcastTitle = getPodcastTitle();
        String podcastTitle2 = bookmarkWriteElement.getPodcastTitle();
        if (podcastTitle != podcastTitle2) {
            if (podcastTitle == null) {
                return -1;
            }
            if (podcastTitle2 == null) {
                return 1;
            }
            int iCompareTo9 = podcastTitle.compareTo(podcastTitle2);
            if (iCompareTo9 != 0) {
                return iCompareTo9;
            }
        }
        String description = getDescription();
        String description2 = bookmarkWriteElement.getDescription();
        if (description != description2) {
            if (description == null) {
                return -1;
            }
            if (description2 == null) {
                return 1;
            }
            int iCompareTo10 = description.compareTo(description2);
            if (iCompareTo10 != 0) {
                return iCompareTo10;
            }
        }
        Integer seasonNumber = getSeasonNumber();
        Integer seasonNumber2 = bookmarkWriteElement.getSeasonNumber();
        if (seasonNumber != seasonNumber2) {
            if (seasonNumber == null) {
                return -1;
            }
            if (seasonNumber2 == null) {
                return 1;
            }
            int iCompareTo11 = seasonNumber.compareTo(seasonNumber2);
            if (iCompareTo11 != 0) {
                return iCompareTo11;
            }
        }
        return super.compareTo(writeElement);
    }

    @Override // SOAAppSyncInterface.v1_0.WriteElement
    public boolean equals(Object obj) {
        if (!(obj instanceof BookmarkWriteElement)) {
            return false;
        }
        BookmarkWriteElement bookmarkWriteElement = (BookmarkWriteElement) obj;
        return super.equals(obj) && internalEqualityCheck(getPodcastId(), bookmarkWriteElement.getPodcastId()) && internalEqualityCheck(getTotalDurationMilliseconds(), bookmarkWriteElement.getTotalDurationMilliseconds()) && internalEqualityCheck(getProgressMilliSeconds(), bookmarkWriteElement.getProgressMilliSeconds()) && internalEqualityCheck(getTitle(), bookmarkWriteElement.getTitle()) && internalEqualityCheck(getPublishDate(), bookmarkWriteElement.getPublishDate()) && internalEqualityCheck(getImage(), bookmarkWriteElement.getImage()) && internalEqualityCheck(getAuthors(), bookmarkWriteElement.getAuthors()) && internalEqualityCheck(getPodcastImage(), bookmarkWriteElement.getPodcastImage()) && internalEqualityCheck(getPodcastTitle(), bookmarkWriteElement.getPodcastTitle()) && internalEqualityCheck(getDescription(), bookmarkWriteElement.getDescription()) && internalEqualityCheck(getSeasonNumber(), bookmarkWriteElement.getSeasonNumber());
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

    public Long getProgressMilliSeconds() {
        return this.progressMilliSeconds;
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
        return internalHashCodeCompute(Integer.valueOf(super.hashCode()), Integer.valueOf(classNameHashCode), getPodcastId(), getTotalDurationMilliseconds(), getProgressMilliSeconds(), getTitle(), getPublishDate(), getImage(), getAuthors(), getPodcastImage(), getPodcastTitle(), getDescription(), getSeasonNumber());
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

    public void setProgressMilliSeconds(Long l) {
        this.progressMilliSeconds = l;
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

    public void setTotalDurationMilliseconds(Long l) {
        this.totalDurationMilliseconds = l;
    }
}
