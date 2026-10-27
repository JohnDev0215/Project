package com.amazon.musicensembleservice.brush;

import java.util.Arrays;

/* JADX INFO: loaded from: classes2.dex */
public class PodcastBite extends MusicEntity {
    private static final int classNameHashCode = internalHashCodeCompute("com.amazon.musicensembleservice.brush.PodcastBite");
    private String audioUri;
    private String catalogId;
    private Integer durationSeconds;
    private Image image;
    private String podcastEpisodeCatalogId;
    private String podcastEpisodeTitle;
    private String podcastShowCatalogId;
    private String podcastShowTitle;
    private String title;

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
        if (!(entity instanceof PodcastBite)) {
            return 1;
        }
        PodcastBite podcastBite = (PodcastBite) entity;
        String podcastEpisodeCatalogId = getPodcastEpisodeCatalogId();
        String podcastEpisodeCatalogId2 = podcastBite.getPodcastEpisodeCatalogId();
        if (podcastEpisodeCatalogId != podcastEpisodeCatalogId2) {
            if (podcastEpisodeCatalogId == null) {
                return -1;
            }
            if (podcastEpisodeCatalogId2 == null) {
                return 1;
            }
            int iCompareTo = podcastEpisodeCatalogId.compareTo(podcastEpisodeCatalogId2);
            if (iCompareTo != 0) {
                return iCompareTo;
            }
        }
        Image image = getImage();
        Image image2 = podcastBite.getImage();
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
        String audioUri = getAudioUri();
        String audioUri2 = podcastBite.getAudioUri();
        if (audioUri != audioUri2) {
            if (audioUri == null) {
                return -1;
            }
            if (audioUri2 == null) {
                return 1;
            }
            int iCompareTo3 = audioUri.compareTo(audioUri2);
            if (iCompareTo3 != 0) {
                return iCompareTo3;
            }
        }
        String catalogId = getCatalogId();
        String catalogId2 = podcastBite.getCatalogId();
        if (catalogId != catalogId2) {
            if (catalogId == null) {
                return -1;
            }
            if (catalogId2 == null) {
                return 1;
            }
            int iCompareTo4 = catalogId.compareTo(catalogId2);
            if (iCompareTo4 != 0) {
                return iCompareTo4;
            }
        }
        String podcastEpisodeTitle = getPodcastEpisodeTitle();
        String podcastEpisodeTitle2 = podcastBite.getPodcastEpisodeTitle();
        if (podcastEpisodeTitle != podcastEpisodeTitle2) {
            if (podcastEpisodeTitle == null) {
                return -1;
            }
            if (podcastEpisodeTitle2 == null) {
                return 1;
            }
            int iCompareTo5 = podcastEpisodeTitle.compareTo(podcastEpisodeTitle2);
            if (iCompareTo5 != 0) {
                return iCompareTo5;
            }
        }
        Integer durationSeconds = getDurationSeconds();
        Integer durationSeconds2 = podcastBite.getDurationSeconds();
        if (durationSeconds != durationSeconds2) {
            if (durationSeconds == null) {
                return -1;
            }
            if (durationSeconds2 == null) {
                return 1;
            }
            int iCompareTo6 = durationSeconds.compareTo(durationSeconds2);
            if (iCompareTo6 != 0) {
                return iCompareTo6;
            }
        }
        String podcastShowTitle = getPodcastShowTitle();
        String podcastShowTitle2 = podcastBite.getPodcastShowTitle();
        if (podcastShowTitle != podcastShowTitle2) {
            if (podcastShowTitle == null) {
                return -1;
            }
            if (podcastShowTitle2 == null) {
                return 1;
            }
            int iCompareTo7 = podcastShowTitle.compareTo(podcastShowTitle2);
            if (iCompareTo7 != 0) {
                return iCompareTo7;
            }
        }
        String podcastShowCatalogId = getPodcastShowCatalogId();
        String podcastShowCatalogId2 = podcastBite.getPodcastShowCatalogId();
        if (podcastShowCatalogId != podcastShowCatalogId2) {
            if (podcastShowCatalogId == null) {
                return -1;
            }
            if (podcastShowCatalogId2 == null) {
                return 1;
            }
            int iCompareTo8 = podcastShowCatalogId.compareTo(podcastShowCatalogId2);
            if (iCompareTo8 != 0) {
                return iCompareTo8;
            }
        }
        String title = getTitle();
        String title2 = podcastBite.getTitle();
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
        return super.compareTo(entity);
    }

    @Override // com.amazon.musicensembleservice.brush.MusicEntity, com.amazon.musicensembleservice.brush.Entity
    public boolean equals(Object obj) {
        if (!(obj instanceof PodcastBite)) {
            return false;
        }
        PodcastBite podcastBite = (PodcastBite) obj;
        return super.equals(obj) && internalEqualityCheck(getPodcastEpisodeCatalogId(), podcastBite.getPodcastEpisodeCatalogId()) && internalEqualityCheck(getImage(), podcastBite.getImage()) && internalEqualityCheck(getAudioUri(), podcastBite.getAudioUri()) && internalEqualityCheck(getCatalogId(), podcastBite.getCatalogId()) && internalEqualityCheck(getPodcastEpisodeTitle(), podcastBite.getPodcastEpisodeTitle()) && internalEqualityCheck(getDurationSeconds(), podcastBite.getDurationSeconds()) && internalEqualityCheck(getPodcastShowTitle(), podcastBite.getPodcastShowTitle()) && internalEqualityCheck(getPodcastShowCatalogId(), podcastBite.getPodcastShowCatalogId()) && internalEqualityCheck(getTitle(), podcastBite.getTitle());
    }

    public String getAudioUri() {
        return this.audioUri;
    }

    public String getCatalogId() {
        return this.catalogId;
    }

    public Integer getDurationSeconds() {
        return this.durationSeconds;
    }

    public Image getImage() {
        return this.image;
    }

    public String getPodcastEpisodeCatalogId() {
        return this.podcastEpisodeCatalogId;
    }

    public String getPodcastEpisodeTitle() {
        return this.podcastEpisodeTitle;
    }

    public String getPodcastShowCatalogId() {
        return this.podcastShowCatalogId;
    }

    public String getPodcastShowTitle() {
        return this.podcastShowTitle;
    }

    public String getTitle() {
        return this.title;
    }

    @Override // com.amazon.musicensembleservice.brush.MusicEntity, com.amazon.musicensembleservice.brush.Entity
    public int hashCode() {
        return internalHashCodeCompute(Integer.valueOf(super.hashCode()), Integer.valueOf(classNameHashCode), getPodcastEpisodeCatalogId(), getImage(), getAudioUri(), getCatalogId(), getPodcastEpisodeTitle(), getDurationSeconds(), getPodcastShowTitle(), getPodcastShowCatalogId(), getTitle());
    }

    public void setAudioUri(String str) {
        this.audioUri = str;
    }

    public void setCatalogId(String str) {
        this.catalogId = str;
    }

    public void setDurationSeconds(Integer num) {
        this.durationSeconds = num;
    }

    public void setImage(Image image) {
        this.image = image;
    }

    public void setPodcastEpisodeCatalogId(String str) {
        this.podcastEpisodeCatalogId = str;
    }

    public void setPodcastEpisodeTitle(String str) {
        this.podcastEpisodeTitle = str;
    }

    public void setPodcastShowCatalogId(String str) {
        this.podcastShowCatalogId = str;
    }

    public void setPodcastShowTitle(String str) {
        this.podcastShowTitle = str;
    }

    public void setTitle(String str) {
        this.title = str;
    }
}
