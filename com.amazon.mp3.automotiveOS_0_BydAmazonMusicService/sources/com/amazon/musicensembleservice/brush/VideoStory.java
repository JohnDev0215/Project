package com.amazon.musicensembleservice.brush;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

/* JADX INFO: loaded from: classes2.dex */
public class VideoStory extends CatalogEntity {
    private static final int classNameHashCode = internalHashCodeCompute("com.amazon.musicensembleservice.brush.VideoStory");
    private List<AssetQuality> assetQualities;
    private List<String> contentTiers;
    private Long duration;
    private String genreName;
    private Image image;
    private Boolean isFeatured;
    private Date localOriginalReleaseDate;
    private ParentalControls parentalControls;
    private String title;

    private static boolean internalEqualityCheck(Object obj, Object obj2) {
        return obj == obj2 || (obj != null && obj.equals(obj2));
    }

    private static int internalHashCodeCompute(Object... objArr) {
        return Arrays.hashCode(objArr);
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.amazon.musicensembleservice.brush.CatalogEntity, com.amazon.musicensembleservice.brush.MusicEntity, com.amazon.musicensembleservice.brush.Entity, java.lang.Comparable
    @Deprecated
    public int compareTo(@Deprecated Entity entity) {
        if (entity == null) {
            return -1;
        }
        if (entity == this) {
            return 0;
        }
        if (!(entity instanceof VideoStory)) {
            return 1;
        }
        VideoStory videoStory = (VideoStory) entity;
        String genreName = getGenreName();
        String genreName2 = videoStory.getGenreName();
        if (genreName != genreName2) {
            if (genreName == null) {
                return -1;
            }
            if (genreName2 == null) {
                return 1;
            }
            int iCompareTo = genreName.compareTo(genreName2);
            if (iCompareTo != 0) {
                return iCompareTo;
            }
        }
        List<String> contentTiers = getContentTiers();
        List<String> contentTiers2 = videoStory.getContentTiers();
        if (contentTiers != contentTiers2) {
            if (contentTiers == null) {
                return -1;
            }
            if (contentTiers2 == null) {
                return 1;
            }
            if (contentTiers instanceof Comparable) {
                int iCompareTo2 = ((Comparable) contentTiers).compareTo(contentTiers2);
                if (iCompareTo2 != 0) {
                    return iCompareTo2;
                }
            } else if (!contentTiers.equals(contentTiers2)) {
                int iHashCode = contentTiers.hashCode();
                int iHashCode2 = contentTiers2.hashCode();
                if (iHashCode < iHashCode2) {
                    return -1;
                }
                if (iHashCode > iHashCode2) {
                    return 1;
                }
            }
        }
        Date localOriginalReleaseDate = getLocalOriginalReleaseDate();
        Date localOriginalReleaseDate2 = videoStory.getLocalOriginalReleaseDate();
        if (localOriginalReleaseDate != localOriginalReleaseDate2) {
            if (localOriginalReleaseDate == null) {
                return -1;
            }
            if (localOriginalReleaseDate2 == null) {
                return 1;
            }
            int iCompareTo3 = localOriginalReleaseDate.compareTo(localOriginalReleaseDate2);
            if (iCompareTo3 != 0) {
                return iCompareTo3;
            }
        }
        Boolean boolIsIsFeatured = isIsFeatured();
        Boolean boolIsIsFeatured2 = videoStory.isIsFeatured();
        if (boolIsIsFeatured != boolIsIsFeatured2) {
            if (boolIsIsFeatured == null) {
                return -1;
            }
            if (boolIsIsFeatured2 == null) {
                return 1;
            }
            int iCompareTo4 = boolIsIsFeatured.compareTo(boolIsIsFeatured2);
            if (iCompareTo4 != 0) {
                return iCompareTo4;
            }
        }
        List<AssetQuality> assetQualities = getAssetQualities();
        List<AssetQuality> assetQualities2 = videoStory.getAssetQualities();
        if (assetQualities != assetQualities2) {
            if (assetQualities == null) {
                return -1;
            }
            if (assetQualities2 == null) {
                return 1;
            }
            if (assetQualities instanceof Comparable) {
                int iCompareTo5 = ((Comparable) assetQualities).compareTo(assetQualities2);
                if (iCompareTo5 != 0) {
                    return iCompareTo5;
                }
            } else if (!assetQualities.equals(assetQualities2)) {
                int iHashCode3 = assetQualities.hashCode();
                int iHashCode4 = assetQualities2.hashCode();
                if (iHashCode3 < iHashCode4) {
                    return -1;
                }
                if (iHashCode3 > iHashCode4) {
                    return 1;
                }
            }
        }
        Image image = getImage();
        Image image2 = videoStory.getImage();
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
        String title = getTitle();
        String title2 = videoStory.getTitle();
        if (title != title2) {
            if (title == null) {
                return -1;
            }
            if (title2 == null) {
                return 1;
            }
            int iCompareTo7 = title.compareTo(title2);
            if (iCompareTo7 != 0) {
                return iCompareTo7;
            }
        }
        Long duration = getDuration();
        Long duration2 = videoStory.getDuration();
        if (duration != duration2) {
            if (duration == null) {
                return -1;
            }
            if (duration2 == null) {
                return 1;
            }
            int iCompareTo8 = duration.compareTo(duration2);
            if (iCompareTo8 != 0) {
                return iCompareTo8;
            }
        }
        ParentalControls parentalControls = getParentalControls();
        ParentalControls parentalControls2 = videoStory.getParentalControls();
        if (parentalControls != parentalControls2) {
            if (parentalControls == null) {
                return -1;
            }
            if (parentalControls2 == null) {
                return 1;
            }
            int iCompareTo9 = parentalControls.compareTo(parentalControls2);
            if (iCompareTo9 != 0) {
                return iCompareTo9;
            }
        }
        return super.compareTo(entity);
    }

    @Override // com.amazon.musicensembleservice.brush.CatalogEntity, com.amazon.musicensembleservice.brush.MusicEntity, com.amazon.musicensembleservice.brush.Entity
    public boolean equals(Object obj) {
        if (!(obj instanceof VideoStory)) {
            return false;
        }
        VideoStory videoStory = (VideoStory) obj;
        return super.equals(obj) && internalEqualityCheck(getGenreName(), videoStory.getGenreName()) && internalEqualityCheck(getContentTiers(), videoStory.getContentTiers()) && internalEqualityCheck(getLocalOriginalReleaseDate(), videoStory.getLocalOriginalReleaseDate()) && internalEqualityCheck(isIsFeatured(), videoStory.isIsFeatured()) && internalEqualityCheck(getAssetQualities(), videoStory.getAssetQualities()) && internalEqualityCheck(getImage(), videoStory.getImage()) && internalEqualityCheck(getTitle(), videoStory.getTitle()) && internalEqualityCheck(getDuration(), videoStory.getDuration()) && internalEqualityCheck(getParentalControls(), videoStory.getParentalControls());
    }

    public List<AssetQuality> getAssetQualities() {
        return this.assetQualities;
    }

    public List<String> getContentTiers() {
        return this.contentTiers;
    }

    public Long getDuration() {
        return this.duration;
    }

    public String getGenreName() {
        return this.genreName;
    }

    public Image getImage() {
        return this.image;
    }

    public Date getLocalOriginalReleaseDate() {
        return this.localOriginalReleaseDate;
    }

    public ParentalControls getParentalControls() {
        return this.parentalControls;
    }

    public String getTitle() {
        return this.title;
    }

    @Override // com.amazon.musicensembleservice.brush.CatalogEntity, com.amazon.musicensembleservice.brush.MusicEntity, com.amazon.musicensembleservice.brush.Entity
    public int hashCode() {
        return internalHashCodeCompute(Integer.valueOf(super.hashCode()), Integer.valueOf(classNameHashCode), getGenreName(), getContentTiers(), getLocalOriginalReleaseDate(), isIsFeatured(), getAssetQualities(), getImage(), getTitle(), getDuration(), getParentalControls());
    }

    public Boolean isIsFeatured() {
        return this.isFeatured;
    }

    public void setAssetQualities(List<AssetQuality> list) {
        this.assetQualities = list;
    }

    public void setContentTiers(List<String> list) {
        this.contentTiers = list;
    }

    public void setDuration(Long l) {
        this.duration = l;
    }

    public void setGenreName(String str) {
        this.genreName = str;
    }

    public void setImage(Image image) {
        this.image = image;
    }

    public void setIsFeatured(Boolean bool) {
        this.isFeatured = bool;
    }

    public void setLocalOriginalReleaseDate(Date date) {
        this.localOriginalReleaseDate = date;
    }

    public void setParentalControls(ParentalControls parentalControls) {
        this.parentalControls = parentalControls;
    }

    public void setTitle(String str) {
        this.title = str;
    }
}
