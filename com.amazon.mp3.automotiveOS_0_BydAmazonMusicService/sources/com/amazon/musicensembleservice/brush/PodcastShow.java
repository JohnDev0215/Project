package com.amazon.musicensembleservice.brush;

import com.amazon.musicensembleservice.brush.MusicEntity;
import java.util.Arrays;
import java.util.List;

/* JADX INFO: loaded from: classes2.dex */
public class PodcastShow extends MusicEntity {
    private static final int classNameHashCode = internalHashCodeCompute("com.amazon.musicensembleservice.brush.PodcastShow");
    private List<String> availableUpsells;
    private String catalogId;
    private List<String> contentTraits;
    private Image image;
    private String playbackMode;
    private String podcastShowVariantId;
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
        if (!(entity instanceof PodcastShow)) {
            return 1;
        }
        PodcastShow podcastShow = (PodcastShow) entity;
        List<String> contentTraits = getContentTraits();
        List<String> contentTraits2 = podcastShow.getContentTraits();
        if (contentTraits != contentTraits2) {
            if (contentTraits == null) {
                return -1;
            }
            if (contentTraits2 == null) {
                return 1;
            }
            if (contentTraits instanceof Comparable) {
                int iCompareTo = ((Comparable) contentTraits).compareTo(contentTraits2);
                if (iCompareTo != 0) {
                    return iCompareTo;
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
        Image image = getImage();
        Image image2 = podcastShow.getImage();
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
        List<String> availableUpsells = getAvailableUpsells();
        List<String> availableUpsells2 = podcastShow.getAvailableUpsells();
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
        String subtitle = getSubtitle();
        String subtitle2 = podcastShow.getSubtitle();
        if (subtitle != subtitle2) {
            if (subtitle == null) {
                return -1;
            }
            if (subtitle2 == null) {
                return 1;
            }
            int iCompareTo4 = subtitle.compareTo(subtitle2);
            if (iCompareTo4 != 0) {
                return iCompareTo4;
            }
        }
        String title = getTitle();
        String title2 = podcastShow.getTitle();
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
        String catalogId = getCatalogId();
        String catalogId2 = podcastShow.getCatalogId();
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
        String podcastShowVariantId = getPodcastShowVariantId();
        String podcastShowVariantId2 = podcastShow.getPodcastShowVariantId();
        if (podcastShowVariantId != podcastShowVariantId2) {
            if (podcastShowVariantId == null) {
                return -1;
            }
            if (podcastShowVariantId2 == null) {
                return 1;
            }
            int iCompareTo7 = podcastShowVariantId.compareTo(podcastShowVariantId2);
            if (iCompareTo7 != 0) {
                return iCompareTo7;
            }
        }
        String playbackMode = getPlaybackMode();
        String playbackMode2 = podcastShow.getPlaybackMode();
        if (playbackMode != playbackMode2) {
            if (playbackMode == null) {
                return -1;
            }
            if (playbackMode2 == null) {
                return 1;
            }
            int iCompareTo8 = playbackMode.compareTo(playbackMode2);
            if (iCompareTo8 != 0) {
                return iCompareTo8;
            }
        }
        return super.compareTo(entity);
    }

    @Override // com.amazon.musicensembleservice.brush.MusicEntity, com.amazon.musicensembleservice.brush.Entity
    public boolean equals(Object obj) {
        if (!(obj instanceof PodcastShow)) {
            return false;
        }
        PodcastShow podcastShow = (PodcastShow) obj;
        return super.equals(obj) && internalEqualityCheck(getContentTraits(), podcastShow.getContentTraits()) && internalEqualityCheck(getImage(), podcastShow.getImage()) && internalEqualityCheck(getAvailableUpsells(), podcastShow.getAvailableUpsells()) && internalEqualityCheck(getSubtitle(), podcastShow.getSubtitle()) && internalEqualityCheck(getTitle(), podcastShow.getTitle()) && internalEqualityCheck(getCatalogId(), podcastShow.getCatalogId()) && internalEqualityCheck(getPodcastShowVariantId(), podcastShow.getPodcastShowVariantId()) && internalEqualityCheck(getPlaybackMode(), podcastShow.getPlaybackMode());
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

    public Image getImage() {
        return this.image;
    }

    public String getPlaybackMode() {
        return this.playbackMode;
    }

    public String getPodcastShowVariantId() {
        return this.podcastShowVariantId;
    }

    public String getSubtitle() {
        return this.subtitle;
    }

    public String getTitle() {
        return this.title;
    }

    @Override // com.amazon.musicensembleservice.brush.MusicEntity, com.amazon.musicensembleservice.brush.Entity
    public int hashCode() {
        return internalHashCodeCompute(Integer.valueOf(super.hashCode()), Integer.valueOf(classNameHashCode), getContentTraits(), getImage(), getAvailableUpsells(), getSubtitle(), getTitle(), getCatalogId(), getPodcastShowVariantId(), getPlaybackMode());
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

    public void setImage(Image image) {
        this.image = image;
    }

    public void setPlaybackMode(String str) {
        this.playbackMode = str;
    }

    public void setPodcastShowVariantId(String str) {
        this.podcastShowVariantId = str;
    }

    public void setSubtitle(String str) {
        this.subtitle = str;
    }

    public void setTitle(String str) {
        this.title = str;
    }
}
