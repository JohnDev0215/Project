package Podcast.FollowInterface.v1_0;

import SOAAppSyncInterface.v1_0.WriteElement;
import java.util.Arrays;

/* JADX INFO: loaded from: classes.dex */
public class FollowWriteElement extends WriteElement {
    private static final int classNameHashCode = internalHashCodeCompute("Podcast.FollowInterface.v1_0.FollowWriteElement");
    private String description;
    private boolean followed;
    private String image;
    private String podcastShowVariantId;
    private String publisher;
    private String title;

    public static class Builder extends WriteElement.Builder {
        protected String description;
        protected boolean followed;
        protected String image;
        protected String podcastShowVariantId;
        protected String publisher;
        protected String title;

        public FollowWriteElement build() {
            FollowWriteElement followWriteElement = new FollowWriteElement();
            populate(followWriteElement);
            return followWriteElement;
        }

        protected void populate(FollowWriteElement followWriteElement) {
            super.populate((WriteElement) followWriteElement);
            followWriteElement.setImage(this.image);
            followWriteElement.setFollowed(this.followed);
            followWriteElement.setTitle(this.title);
            followWriteElement.setPublisher(this.publisher);
            followWriteElement.setPodcastShowVariantId(this.podcastShowVariantId);
            followWriteElement.setDescription(this.description);
        }

        public Builder withDescription(String str) {
            this.description = str;
            return this;
        }

        public Builder withFollowed(boolean z) {
            this.followed = z;
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

        public Builder withPodcastShowVariantId(String str) {
            this.podcastShowVariantId = str;
            return this;
        }

        public Builder withPublisher(String str) {
            this.publisher = str;
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
        if (!(writeElement instanceof FollowWriteElement)) {
            return 1;
        }
        FollowWriteElement followWriteElement = (FollowWriteElement) writeElement;
        String image = getImage();
        String image2 = followWriteElement.getImage();
        if (image != image2) {
            if (image == null) {
                return -1;
            }
            if (image2 == null) {
                return 1;
            }
            int iCompareTo = image.compareTo(image2);
            if (iCompareTo != 0) {
                return iCompareTo;
            }
        }
        if (!isFollowed() && followWriteElement.isFollowed()) {
            return -1;
        }
        if (isFollowed() && !followWriteElement.isFollowed()) {
            return 1;
        }
        String title = getTitle();
        String title2 = followWriteElement.getTitle();
        if (title != title2) {
            if (title == null) {
                return -1;
            }
            if (title2 == null) {
                return 1;
            }
            int iCompareTo2 = title.compareTo(title2);
            if (iCompareTo2 != 0) {
                return iCompareTo2;
            }
        }
        String publisher = getPublisher();
        String publisher2 = followWriteElement.getPublisher();
        if (publisher != publisher2) {
            if (publisher == null) {
                return -1;
            }
            if (publisher2 == null) {
                return 1;
            }
            int iCompareTo3 = publisher.compareTo(publisher2);
            if (iCompareTo3 != 0) {
                return iCompareTo3;
            }
        }
        String podcastShowVariantId = getPodcastShowVariantId();
        String podcastShowVariantId2 = followWriteElement.getPodcastShowVariantId();
        if (podcastShowVariantId != podcastShowVariantId2) {
            if (podcastShowVariantId == null) {
                return -1;
            }
            if (podcastShowVariantId2 == null) {
                return 1;
            }
            int iCompareTo4 = podcastShowVariantId.compareTo(podcastShowVariantId2);
            if (iCompareTo4 != 0) {
                return iCompareTo4;
            }
        }
        String description = getDescription();
        String description2 = followWriteElement.getDescription();
        if (description != description2) {
            if (description == null) {
                return -1;
            }
            if (description2 == null) {
                return 1;
            }
            int iCompareTo5 = description.compareTo(description2);
            if (iCompareTo5 != 0) {
                return iCompareTo5;
            }
        }
        return super.compareTo(writeElement);
    }

    @Override // SOAAppSyncInterface.v1_0.WriteElement
    public boolean equals(Object obj) {
        if (!(obj instanceof FollowWriteElement)) {
            return false;
        }
        FollowWriteElement followWriteElement = (FollowWriteElement) obj;
        return super.equals(obj) && internalEqualityCheck(getImage(), followWriteElement.getImage()) && internalEqualityCheck(Boolean.valueOf(isFollowed()), Boolean.valueOf(followWriteElement.isFollowed())) && internalEqualityCheck(getTitle(), followWriteElement.getTitle()) && internalEqualityCheck(getPublisher(), followWriteElement.getPublisher()) && internalEqualityCheck(getPodcastShowVariantId(), followWriteElement.getPodcastShowVariantId()) && internalEqualityCheck(getDescription(), followWriteElement.getDescription());
    }

    public String getDescription() {
        return this.description;
    }

    public String getImage() {
        return this.image;
    }

    public String getPodcastShowVariantId() {
        return this.podcastShowVariantId;
    }

    public String getPublisher() {
        return this.publisher;
    }

    public String getTitle() {
        return this.title;
    }

    @Override // SOAAppSyncInterface.v1_0.WriteElement
    public int hashCode() {
        return internalHashCodeCompute(Integer.valueOf(super.hashCode()), Integer.valueOf(classNameHashCode), getImage(), Boolean.valueOf(isFollowed()), getTitle(), getPublisher(), getPodcastShowVariantId(), getDescription());
    }

    public boolean isFollowed() {
        return this.followed;
    }

    public void setDescription(String str) {
        this.description = str;
    }

    public void setFollowed(boolean z) {
        this.followed = z;
    }

    public void setImage(String str) {
        this.image = str;
    }

    public void setPodcastShowVariantId(String str) {
        this.podcastShowVariantId = str;
    }

    public void setPublisher(String str) {
        this.publisher = str;
    }

    public void setTitle(String str) {
        this.title = str;
    }
}
