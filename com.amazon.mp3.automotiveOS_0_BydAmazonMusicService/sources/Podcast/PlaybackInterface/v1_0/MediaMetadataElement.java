package Podcast.PlaybackInterface.v1_0;

import Podcast.PlaybackInterface.v1_0.PlaybackElement;
import SOACoreInterface.v1_0.Method;
import SOACoreInterface.v1_0.SOAObject;
import java.util.Arrays;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public class MediaMetadataElement extends PlaybackElement {
    private static final int classNameHashCode = internalHashCodeCompute("Podcast.PlaybackInterface.v1_0.MediaMetadataElement");
    private String artwork;
    private String description;
    private Long durationSeconds;
    private String logo;
    private String mediaCollectionId;
    private String mediaId;
    private List<Method> onAdditionalMetadataRequired;
    private List<Method> onTranscriptsRequired;
    private String podcastDeeplink;
    private String subtitle;
    private String title;

    public static class Builder extends PlaybackElement.Builder {
        protected String artwork;
        protected String description;
        protected Long durationSeconds;
        protected String logo;
        protected String mediaCollectionId;
        protected String mediaId;
        protected List<Method> onAdditionalMetadataRequired;
        protected List<Method> onTranscriptsRequired;
        protected String podcastDeeplink;
        protected String subtitle;
        protected String title;

        public MediaMetadataElement build() {
            MediaMetadataElement mediaMetadataElement = new MediaMetadataElement();
            populate(mediaMetadataElement);
            return mediaMetadataElement;
        }

        protected void populate(MediaMetadataElement mediaMetadataElement) {
            super.populate((PlaybackElement) mediaMetadataElement);
            mediaMetadataElement.setPodcastDeeplink(this.podcastDeeplink);
            mediaMetadataElement.setDescription(this.description);
            mediaMetadataElement.setLogo(this.logo);
            mediaMetadataElement.setMediaId(this.mediaId);
            mediaMetadataElement.setOnAdditionalMetadataRequired(this.onAdditionalMetadataRequired);
            mediaMetadataElement.setMediaCollectionId(this.mediaCollectionId);
            mediaMetadataElement.setArtwork(this.artwork);
            mediaMetadataElement.setDurationSeconds(this.durationSeconds);
            mediaMetadataElement.setSubtitle(this.subtitle);
            mediaMetadataElement.setTitle(this.title);
            mediaMetadataElement.setOnTranscriptsRequired(this.onTranscriptsRequired);
        }

        public Builder withArtwork(String str) {
            this.artwork = str;
            return this;
        }

        public Builder withDescription(String str) {
            this.description = str;
            return this;
        }

        public Builder withDurationSeconds(Long l) {
            this.durationSeconds = l;
            return this;
        }

        public Builder withLogo(String str) {
            this.logo = str;
            return this;
        }

        public Builder withMediaCollectionId(String str) {
            this.mediaCollectionId = str;
            return this;
        }

        public Builder withMediaId(String str) {
            this.mediaId = str;
            return this;
        }

        public Builder withOnAdditionalMetadataRequired(List<Method> list) {
            this.onAdditionalMetadataRequired = list;
            return this;
        }

        public Builder withOnTranscriptsRequired(List<Method> list) {
            this.onTranscriptsRequired = list;
            return this;
        }

        public Builder withPodcastDeeplink(String str) {
            this.podcastDeeplink = str;
            return this;
        }

        public Builder withSubtitle(String str) {
            this.subtitle = str;
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
    @Override // Podcast.PlaybackInterface.v1_0.PlaybackElement, SOACoreInterface.v1_0.SOAObject, java.lang.Comparable
    @Deprecated
    public int compareTo(@Deprecated SOAObject sOAObject) {
        if (sOAObject == null) {
            return -1;
        }
        if (sOAObject == this) {
            return 0;
        }
        if (!(sOAObject instanceof MediaMetadataElement)) {
            return 1;
        }
        MediaMetadataElement mediaMetadataElement = (MediaMetadataElement) sOAObject;
        String podcastDeeplink = getPodcastDeeplink();
        String podcastDeeplink2 = mediaMetadataElement.getPodcastDeeplink();
        if (podcastDeeplink != podcastDeeplink2) {
            if (podcastDeeplink == null) {
                return -1;
            }
            if (podcastDeeplink2 == null) {
                return 1;
            }
            int iCompareTo = podcastDeeplink.compareTo(podcastDeeplink2);
            if (iCompareTo != 0) {
                return iCompareTo;
            }
        }
        String description = getDescription();
        String description2 = mediaMetadataElement.getDescription();
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
        String logo = getLogo();
        String logo2 = mediaMetadataElement.getLogo();
        if (logo != logo2) {
            if (logo == null) {
                return -1;
            }
            if (logo2 == null) {
                return 1;
            }
            int iCompareTo3 = logo.compareTo(logo2);
            if (iCompareTo3 != 0) {
                return iCompareTo3;
            }
        }
        String mediaId = getMediaId();
        String mediaId2 = mediaMetadataElement.getMediaId();
        if (mediaId != mediaId2) {
            if (mediaId == null) {
                return -1;
            }
            if (mediaId2 == null) {
                return 1;
            }
            int iCompareTo4 = mediaId.compareTo(mediaId2);
            if (iCompareTo4 != 0) {
                return iCompareTo4;
            }
        }
        List<Method> onAdditionalMetadataRequired = getOnAdditionalMetadataRequired();
        List<Method> onAdditionalMetadataRequired2 = mediaMetadataElement.getOnAdditionalMetadataRequired();
        if (onAdditionalMetadataRequired != onAdditionalMetadataRequired2) {
            if (onAdditionalMetadataRequired == null) {
                return -1;
            }
            if (onAdditionalMetadataRequired2 == null) {
                return 1;
            }
            if (onAdditionalMetadataRequired instanceof Comparable) {
                int iCompareTo5 = ((Comparable) onAdditionalMetadataRequired).compareTo(onAdditionalMetadataRequired2);
                if (iCompareTo5 != 0) {
                    return iCompareTo5;
                }
            } else if (!onAdditionalMetadataRequired.equals(onAdditionalMetadataRequired2)) {
                int iHashCode = onAdditionalMetadataRequired.hashCode();
                int iHashCode2 = onAdditionalMetadataRequired2.hashCode();
                if (iHashCode < iHashCode2) {
                    return -1;
                }
                if (iHashCode > iHashCode2) {
                    return 1;
                }
            }
        }
        String mediaCollectionId = getMediaCollectionId();
        String mediaCollectionId2 = mediaMetadataElement.getMediaCollectionId();
        if (mediaCollectionId != mediaCollectionId2) {
            if (mediaCollectionId == null) {
                return -1;
            }
            if (mediaCollectionId2 == null) {
                return 1;
            }
            int iCompareTo6 = mediaCollectionId.compareTo(mediaCollectionId2);
            if (iCompareTo6 != 0) {
                return iCompareTo6;
            }
        }
        String artwork = getArtwork();
        String artwork2 = mediaMetadataElement.getArtwork();
        if (artwork != artwork2) {
            if (artwork == null) {
                return -1;
            }
            if (artwork2 == null) {
                return 1;
            }
            int iCompareTo7 = artwork.compareTo(artwork2);
            if (iCompareTo7 != 0) {
                return iCompareTo7;
            }
        }
        Long durationSeconds = getDurationSeconds();
        Long durationSeconds2 = mediaMetadataElement.getDurationSeconds();
        if (durationSeconds != durationSeconds2) {
            if (durationSeconds == null) {
                return -1;
            }
            if (durationSeconds2 == null) {
                return 1;
            }
            int iCompareTo8 = durationSeconds.compareTo(durationSeconds2);
            if (iCompareTo8 != 0) {
                return iCompareTo8;
            }
        }
        String subtitle = getSubtitle();
        String subtitle2 = mediaMetadataElement.getSubtitle();
        if (subtitle != subtitle2) {
            if (subtitle == null) {
                return -1;
            }
            if (subtitle2 == null) {
                return 1;
            }
            int iCompareTo9 = subtitle.compareTo(subtitle2);
            if (iCompareTo9 != 0) {
                return iCompareTo9;
            }
        }
        String title = getTitle();
        String title2 = mediaMetadataElement.getTitle();
        if (title != title2) {
            if (title == null) {
                return -1;
            }
            if (title2 == null) {
                return 1;
            }
            int iCompareTo10 = title.compareTo(title2);
            if (iCompareTo10 != 0) {
                return iCompareTo10;
            }
        }
        List<Method> onTranscriptsRequired = getOnTranscriptsRequired();
        List<Method> onTranscriptsRequired2 = mediaMetadataElement.getOnTranscriptsRequired();
        if (onTranscriptsRequired != onTranscriptsRequired2) {
            if (onTranscriptsRequired == null) {
                return -1;
            }
            if (onTranscriptsRequired2 == null) {
                return 1;
            }
            if (onTranscriptsRequired instanceof Comparable) {
                int iCompareTo11 = ((Comparable) onTranscriptsRequired).compareTo(onTranscriptsRequired2);
                if (iCompareTo11 != 0) {
                    return iCompareTo11;
                }
            } else if (!onTranscriptsRequired.equals(onTranscriptsRequired2)) {
                int iHashCode3 = onTranscriptsRequired.hashCode();
                int iHashCode4 = onTranscriptsRequired2.hashCode();
                if (iHashCode3 < iHashCode4) {
                    return -1;
                }
                if (iHashCode3 > iHashCode4) {
                    return 1;
                }
            }
        }
        return super.compareTo(sOAObject);
    }

    @Override // Podcast.PlaybackInterface.v1_0.PlaybackElement, SOACoreInterface.v1_0.SOAObject
    public boolean equals(Object obj) {
        if (!(obj instanceof MediaMetadataElement)) {
            return false;
        }
        MediaMetadataElement mediaMetadataElement = (MediaMetadataElement) obj;
        return super.equals(obj) && internalEqualityCheck(getPodcastDeeplink(), mediaMetadataElement.getPodcastDeeplink()) && internalEqualityCheck(getDescription(), mediaMetadataElement.getDescription()) && internalEqualityCheck(getLogo(), mediaMetadataElement.getLogo()) && internalEqualityCheck(getMediaId(), mediaMetadataElement.getMediaId()) && internalEqualityCheck(getOnAdditionalMetadataRequired(), mediaMetadataElement.getOnAdditionalMetadataRequired()) && internalEqualityCheck(getMediaCollectionId(), mediaMetadataElement.getMediaCollectionId()) && internalEqualityCheck(getArtwork(), mediaMetadataElement.getArtwork()) && internalEqualityCheck(getDurationSeconds(), mediaMetadataElement.getDurationSeconds()) && internalEqualityCheck(getSubtitle(), mediaMetadataElement.getSubtitle()) && internalEqualityCheck(getTitle(), mediaMetadataElement.getTitle()) && internalEqualityCheck(getOnTranscriptsRequired(), mediaMetadataElement.getOnTranscriptsRequired());
    }

    public String getArtwork() {
        return this.artwork;
    }

    public String getDescription() {
        return this.description;
    }

    public Long getDurationSeconds() {
        return this.durationSeconds;
    }

    public String getLogo() {
        return this.logo;
    }

    public String getMediaCollectionId() {
        return this.mediaCollectionId;
    }

    public String getMediaId() {
        return this.mediaId;
    }

    public List<Method> getOnAdditionalMetadataRequired() {
        return this.onAdditionalMetadataRequired;
    }

    public List<Method> getOnTranscriptsRequired() {
        return this.onTranscriptsRequired;
    }

    public String getPodcastDeeplink() {
        return this.podcastDeeplink;
    }

    public String getSubtitle() {
        return this.subtitle;
    }

    public String getTitle() {
        return this.title;
    }

    @Override // Podcast.PlaybackInterface.v1_0.PlaybackElement, SOACoreInterface.v1_0.SOAObject
    public int hashCode() {
        return internalHashCodeCompute(Integer.valueOf(super.hashCode()), Integer.valueOf(classNameHashCode), getPodcastDeeplink(), getDescription(), getLogo(), getMediaId(), getOnAdditionalMetadataRequired(), getMediaCollectionId(), getArtwork(), getDurationSeconds(), getSubtitle(), getTitle(), getOnTranscriptsRequired());
    }

    public void setArtwork(String str) {
        this.artwork = str;
    }

    public void setDescription(String str) {
        this.description = str;
    }

    public void setDurationSeconds(Long l) {
        this.durationSeconds = l;
    }

    public void setLogo(String str) {
        this.logo = str;
    }

    public void setMediaCollectionId(String str) {
        this.mediaCollectionId = str;
    }

    public void setMediaId(String str) {
        this.mediaId = str;
    }

    public void setOnAdditionalMetadataRequired(List<Method> list) {
        this.onAdditionalMetadataRequired = list;
    }

    public void setOnTranscriptsRequired(List<Method> list) {
        this.onTranscriptsRequired = list;
    }

    public void setPodcastDeeplink(String str) {
        this.podcastDeeplink = str;
    }

    public void setSubtitle(String str) {
        this.subtitle = str;
    }

    public void setTitle(String str) {
        this.title = str;
    }
}
