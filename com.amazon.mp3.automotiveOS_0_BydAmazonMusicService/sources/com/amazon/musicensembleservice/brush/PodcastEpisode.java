package com.amazon.musicensembleservice.brush;

import com.amazon.musicensembleservice.brush.MusicEntity;
import java.util.Arrays;
import java.util.List;

/* JADX INFO: loaded from: classes2.dex */
public class PodcastEpisode extends MusicEntity {
    private static final int classNameHashCode = internalHashCodeCompute("com.amazon.musicensembleservice.brush.PodcastEpisode");
    private String audioUri;
    private String availabilityDate;
    private List<String> availableUpsells;
    private String catalogId;
    private List<String> contentTraits;
    private Long durationSeconds;
    private Image image;
    private String playbackMode;
    private String podcastEpisodeVariantId;
    private String podcastShowCatalogId;
    private Image podcastShowImage;
    private String podcastShowTitle;
    private String podcastShowVariantId;
    private String publishDate;
    private String subtitle;
    private String title;

    public static class Builder extends MusicEntity.Builder {
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
    @Override // com.amazon.musicensembleservice.brush.MusicEntity, com.amazon.musicensembleservice.brush.Entity, java.lang.Comparable
    @Deprecated
    public int compareTo(@Deprecated Entity entity) {
        if (entity == null) {
            return -1;
        }
        if (entity == this) {
            return 0;
        }
        if (!(entity instanceof PodcastEpisode)) {
            return 1;
        }
        PodcastEpisode podcastEpisode = (PodcastEpisode) entity;
        String audioUri = getAudioUri();
        String audioUri2 = podcastEpisode.getAudioUri();
        if (audioUri != audioUri2) {
            if (audioUri == null) {
                return -1;
            }
            if (audioUri2 == null) {
                return 1;
            }
            int iCompareTo = audioUri.compareTo(audioUri2);
            if (iCompareTo != 0) {
                return iCompareTo;
            }
        }
        String publishDate = getPublishDate();
        String publishDate2 = podcastEpisode.getPublishDate();
        if (publishDate != publishDate2) {
            if (publishDate == null) {
                return -1;
            }
            if (publishDate2 == null) {
                return 1;
            }
            int iCompareTo2 = publishDate.compareTo(publishDate2);
            if (iCompareTo2 != 0) {
                return iCompareTo2;
            }
        }
        String podcastShowTitle = getPodcastShowTitle();
        String podcastShowTitle2 = podcastEpisode.getPodcastShowTitle();
        if (podcastShowTitle != podcastShowTitle2) {
            if (podcastShowTitle == null) {
                return -1;
            }
            if (podcastShowTitle2 == null) {
                return 1;
            }
            int iCompareTo3 = podcastShowTitle.compareTo(podcastShowTitle2);
            if (iCompareTo3 != 0) {
                return iCompareTo3;
            }
        }
        Image podcastShowImage = getPodcastShowImage();
        Image podcastShowImage2 = podcastEpisode.getPodcastShowImage();
        if (podcastShowImage != podcastShowImage2) {
            if (podcastShowImage == null) {
                return -1;
            }
            if (podcastShowImage2 == null) {
                return 1;
            }
            int iCompareTo4 = podcastShowImage.compareTo(podcastShowImage2);
            if (iCompareTo4 != 0) {
                return iCompareTo4;
            }
        }
        String playbackMode = getPlaybackMode();
        String playbackMode2 = podcastEpisode.getPlaybackMode();
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
        String catalogId = getCatalogId();
        String catalogId2 = podcastEpisode.getCatalogId();
        if (catalogId != catalogId2) {
            if (catalogId == null) {
                return -1;
            }
            if (catalogId2 == null) {
                return 1;
            }
            int iCompareTo6 = catalogId.compareTo(catalogId2);
            if (iCompareTo6 != 0) {
                return iCompareTo6;
            }
        }
        Long durationSeconds = getDurationSeconds();
        Long durationSeconds2 = podcastEpisode.getDurationSeconds();
        if (durationSeconds != durationSeconds2) {
            if (durationSeconds == null) {
                return -1;
            }
            if (durationSeconds2 == null) {
                return 1;
            }
            int iCompareTo7 = durationSeconds.compareTo(durationSeconds2);
            if (iCompareTo7 != 0) {
                return iCompareTo7;
            }
        }
        String availabilityDate = getAvailabilityDate();
        String availabilityDate2 = podcastEpisode.getAvailabilityDate();
        if (availabilityDate != availabilityDate2) {
            if (availabilityDate == null) {
                return -1;
            }
            if (availabilityDate2 == null) {
                return 1;
            }
            int iCompareTo8 = availabilityDate.compareTo(availabilityDate2);
            if (iCompareTo8 != 0) {
                return iCompareTo8;
            }
        }
        String subtitle = getSubtitle();
        String subtitle2 = podcastEpisode.getSubtitle();
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
        List<String> availableUpsells = getAvailableUpsells();
        List<String> availableUpsells2 = podcastEpisode.getAvailableUpsells();
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
        String podcastShowCatalogId = getPodcastShowCatalogId();
        String podcastShowCatalogId2 = podcastEpisode.getPodcastShowCatalogId();
        if (podcastShowCatalogId != podcastShowCatalogId2) {
            if (podcastShowCatalogId == null) {
                return -1;
            }
            if (podcastShowCatalogId2 == null) {
                return 1;
            }
            int iCompareTo11 = podcastShowCatalogId.compareTo(podcastShowCatalogId2);
            if (iCompareTo11 != 0) {
                return iCompareTo11;
            }
        }
        List<String> contentTraits = getContentTraits();
        List<String> contentTraits2 = podcastEpisode.getContentTraits();
        if (contentTraits != contentTraits2) {
            if (contentTraits == null) {
                return -1;
            }
            if (contentTraits2 == null) {
                return 1;
            }
            if (contentTraits instanceof Comparable) {
                int iCompareTo12 = ((Comparable) contentTraits).compareTo(contentTraits2);
                if (iCompareTo12 != 0) {
                    return iCompareTo12;
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
        String podcastEpisodeVariantId = getPodcastEpisodeVariantId();
        String podcastEpisodeVariantId2 = podcastEpisode.getPodcastEpisodeVariantId();
        if (podcastEpisodeVariantId != podcastEpisodeVariantId2) {
            if (podcastEpisodeVariantId == null) {
                return -1;
            }
            if (podcastEpisodeVariantId2 == null) {
                return 1;
            }
            int iCompareTo13 = podcastEpisodeVariantId.compareTo(podcastEpisodeVariantId2);
            if (iCompareTo13 != 0) {
                return iCompareTo13;
            }
        }
        Image image = getImage();
        Image image2 = podcastEpisode.getImage();
        if (image != image2) {
            if (image == null) {
                return -1;
            }
            if (image2 == null) {
                return 1;
            }
            int iCompareTo14 = image.compareTo(image2);
            if (iCompareTo14 != 0) {
                return iCompareTo14;
            }
        }
        String podcastShowVariantId = getPodcastShowVariantId();
        String podcastShowVariantId2 = podcastEpisode.getPodcastShowVariantId();
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
        String title = getTitle();
        String title2 = podcastEpisode.getTitle();
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
        return super.compareTo(entity);
    }

    @Override // com.amazon.musicensembleservice.brush.MusicEntity, com.amazon.musicensembleservice.brush.Entity
    public boolean equals(Object obj) {
        if (!(obj instanceof PodcastEpisode)) {
            return false;
        }
        PodcastEpisode podcastEpisode = (PodcastEpisode) obj;
        return super.equals(obj) && internalEqualityCheck(getAudioUri(), podcastEpisode.getAudioUri()) && internalEqualityCheck(getPublishDate(), podcastEpisode.getPublishDate()) && internalEqualityCheck(getPodcastShowTitle(), podcastEpisode.getPodcastShowTitle()) && internalEqualityCheck(getPodcastShowImage(), podcastEpisode.getPodcastShowImage()) && internalEqualityCheck(getPlaybackMode(), podcastEpisode.getPlaybackMode()) && internalEqualityCheck(getCatalogId(), podcastEpisode.getCatalogId()) && internalEqualityCheck(getDurationSeconds(), podcastEpisode.getDurationSeconds()) && internalEqualityCheck(getAvailabilityDate(), podcastEpisode.getAvailabilityDate()) && internalEqualityCheck(getSubtitle(), podcastEpisode.getSubtitle()) && internalEqualityCheck(getAvailableUpsells(), podcastEpisode.getAvailableUpsells()) && internalEqualityCheck(getPodcastShowCatalogId(), podcastEpisode.getPodcastShowCatalogId()) && internalEqualityCheck(getContentTraits(), podcastEpisode.getContentTraits()) && internalEqualityCheck(getPodcastEpisodeVariantId(), podcastEpisode.getPodcastEpisodeVariantId()) && internalEqualityCheck(getImage(), podcastEpisode.getImage()) && internalEqualityCheck(getPodcastShowVariantId(), podcastEpisode.getPodcastShowVariantId()) && internalEqualityCheck(getTitle(), podcastEpisode.getTitle());
    }

    public String getAudioUri() {
        return this.audioUri;
    }

    public String getAvailabilityDate() {
        return this.availabilityDate;
    }

    public List<String> getAvailableUpsells() {
        return this.availableUpsells;
    }

    public String getCatalogId() {
        return this.catalogId;
    }

    public List<String> getContentTraits() {
        return this.contentTraits;
    }

    public Long getDurationSeconds() {
        return this.durationSeconds;
    }

    public Image getImage() {
        return this.image;
    }

    public String getPlaybackMode() {
        return this.playbackMode;
    }

    public String getPodcastEpisodeVariantId() {
        return this.podcastEpisodeVariantId;
    }

    public String getPodcastShowCatalogId() {
        return this.podcastShowCatalogId;
    }

    public Image getPodcastShowImage() {
        return this.podcastShowImage;
    }

    public String getPodcastShowTitle() {
        return this.podcastShowTitle;
    }

    public String getPodcastShowVariantId() {
        return this.podcastShowVariantId;
    }

    public String getPublishDate() {
        return this.publishDate;
    }

    public String getSubtitle() {
        return this.subtitle;
    }

    public String getTitle() {
        return this.title;
    }

    @Override // com.amazon.musicensembleservice.brush.MusicEntity, com.amazon.musicensembleservice.brush.Entity
    public int hashCode() {
        return internalHashCodeCompute(Integer.valueOf(super.hashCode()), Integer.valueOf(classNameHashCode), getAudioUri(), getPublishDate(), getPodcastShowTitle(), getPodcastShowImage(), getPlaybackMode(), getCatalogId(), getDurationSeconds(), getAvailabilityDate(), getSubtitle(), getAvailableUpsells(), getPodcastShowCatalogId(), getContentTraits(), getPodcastEpisodeVariantId(), getImage(), getPodcastShowVariantId(), getTitle());
    }

    public void setAudioUri(String str) {
        this.audioUri = str;
    }

    public void setAvailabilityDate(String str) {
        this.availabilityDate = str;
    }

    public void setAvailableUpsells(List<String> list) {
        this.availableUpsells = list;
    }

    public void setCatalogId(String str) {
        this.catalogId = str;
    }

    public void setContentTraits(List<String> list) {
        this.contentTraits = list;
    }

    public void setDurationSeconds(Long l) {
        this.durationSeconds = l;
    }

    public void setImage(Image image) {
        this.image = image;
    }

    public void setPlaybackMode(String str) {
        this.playbackMode = str;
    }

    public void setPodcastEpisodeVariantId(String str) {
        this.podcastEpisodeVariantId = str;
    }

    public void setPodcastShowCatalogId(String str) {
        this.podcastShowCatalogId = str;
    }

    public void setPodcastShowImage(Image image) {
        this.podcastShowImage = image;
    }

    public void setPodcastShowTitle(String str) {
        this.podcastShowTitle = str;
    }

    public void setPodcastShowVariantId(String str) {
        this.podcastShowVariantId = str;
    }

    public void setPublishDate(String str) {
        this.publishDate = str;
    }

    public void setSubtitle(String str) {
        this.subtitle = str;
    }

    public void setTitle(String str) {
        this.title = str;
    }
}
