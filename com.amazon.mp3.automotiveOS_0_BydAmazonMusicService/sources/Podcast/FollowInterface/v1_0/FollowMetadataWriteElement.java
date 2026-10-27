package Podcast.FollowInterface.v1_0;

import SOAAppSyncInterface.v1_0.WriteElement;
import java.util.Arrays;

/* JADX INFO: loaded from: classes.dex */
public class FollowMetadataWriteElement extends WriteElement {
    private static final int classNameHashCode = internalHashCodeCompute("Podcast.FollowInterface.v1_0.FollowMetadataWriteElement");
    private String description;
    private String image;
    private String podcastShowVariantId;
    private String publisher;
    private String title;

    public static class Builder extends WriteElement.Builder {
        protected String description;
        protected String image;
        protected String podcastShowVariantId;
        protected String publisher;
        protected String title;

        public FollowMetadataWriteElement build() {
            FollowMetadataWriteElement followMetadataWriteElement = new FollowMetadataWriteElement();
            populate(followMetadataWriteElement);
            return followMetadataWriteElement;
        }

        protected void populate(FollowMetadataWriteElement followMetadataWriteElement) {
            super.populate((WriteElement) followMetadataWriteElement);
            followMetadataWriteElement.setTitle(this.title);
            followMetadataWriteElement.setImage(this.image);
            followMetadataWriteElement.setPodcastShowVariantId(this.podcastShowVariantId);
            followMetadataWriteElement.setPublisher(this.publisher);
            followMetadataWriteElement.setDescription(this.description);
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
        if (!(writeElement instanceof FollowMetadataWriteElement)) {
            return 1;
        }
        FollowMetadataWriteElement followMetadataWriteElement = (FollowMetadataWriteElement) writeElement;
        String title = getTitle();
        String title2 = followMetadataWriteElement.getTitle();
        if (title != title2) {
            if (title == null) {
                return -1;
            }
            if (title2 == null) {
                return 1;
            }
            int iCompareTo = title.compareTo(title2);
            if (iCompareTo != 0) {
                return iCompareTo;
            }
        }
        String image = getImage();
        String image2 = followMetadataWriteElement.getImage();
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
        String podcastShowVariantId = getPodcastShowVariantId();
        String podcastShowVariantId2 = followMetadataWriteElement.getPodcastShowVariantId();
        if (podcastShowVariantId != podcastShowVariantId2) {
            if (podcastShowVariantId == null) {
                return -1;
            }
            if (podcastShowVariantId2 == null) {
                return 1;
            }
            int iCompareTo3 = podcastShowVariantId.compareTo(podcastShowVariantId2);
            if (iCompareTo3 != 0) {
                return iCompareTo3;
            }
        }
        String publisher = getPublisher();
        String publisher2 = followMetadataWriteElement.getPublisher();
        if (publisher != publisher2) {
            if (publisher == null) {
                return -1;
            }
            if (publisher2 == null) {
                return 1;
            }
            int iCompareTo4 = publisher.compareTo(publisher2);
            if (iCompareTo4 != 0) {
                return iCompareTo4;
            }
        }
        String description = getDescription();
        String description2 = followMetadataWriteElement.getDescription();
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
        if (!(obj instanceof FollowMetadataWriteElement)) {
            return false;
        }
        FollowMetadataWriteElement followMetadataWriteElement = (FollowMetadataWriteElement) obj;
        return super.equals(obj) && internalEqualityCheck(getTitle(), followMetadataWriteElement.getTitle()) && internalEqualityCheck(getImage(), followMetadataWriteElement.getImage()) && internalEqualityCheck(getPodcastShowVariantId(), followMetadataWriteElement.getPodcastShowVariantId()) && internalEqualityCheck(getPublisher(), followMetadataWriteElement.getPublisher()) && internalEqualityCheck(getDescription(), followMetadataWriteElement.getDescription());
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
        return internalHashCodeCompute(Integer.valueOf(super.hashCode()), Integer.valueOf(classNameHashCode), getTitle(), getImage(), getPodcastShowVariantId(), getPublisher(), getDescription());
    }

    public void setDescription(String str) {
        this.description = str;
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
