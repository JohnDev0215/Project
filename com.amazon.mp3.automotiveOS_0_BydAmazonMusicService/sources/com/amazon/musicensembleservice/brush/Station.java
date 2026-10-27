package com.amazon.musicensembleservice.brush;

import com.amazon.musicensembleservice.brush.MusicEntity;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

/* JADX INFO: loaded from: classes2.dex */
public class Station extends MusicEntity {
    private static final int classNameHashCode = internalHashCodeCompute("com.amazon.musicensembleservice.brush.Station");
    private String assetType;
    private List<String> contentTiers;
    private Image image;
    private Map<String, ParentalControls> parentalControlsPerContentTier;
    private Seed seed;
    private String stationKey;
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
        if (!(entity instanceof Station)) {
            return 1;
        }
        Station station = (Station) entity;
        List<String> contentTiers = getContentTiers();
        List<String> contentTiers2 = station.getContentTiers();
        if (contentTiers != contentTiers2) {
            if (contentTiers == null) {
                return -1;
            }
            if (contentTiers2 == null) {
                return 1;
            }
            if (contentTiers instanceof Comparable) {
                int iCompareTo = ((Comparable) contentTiers).compareTo(contentTiers2);
                if (iCompareTo != 0) {
                    return iCompareTo;
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
        String stationKey = getStationKey();
        String stationKey2 = station.getStationKey();
        if (stationKey != stationKey2) {
            if (stationKey == null) {
                return -1;
            }
            if (stationKey2 == null) {
                return 1;
            }
            int iCompareTo2 = stationKey.compareTo(stationKey2);
            if (iCompareTo2 != 0) {
                return iCompareTo2;
            }
        }
        String assetType = getAssetType();
        String assetType2 = station.getAssetType();
        if (assetType != assetType2) {
            if (assetType == null) {
                return -1;
            }
            if (assetType2 == null) {
                return 1;
            }
            int iCompareTo3 = assetType.compareTo(assetType2);
            if (iCompareTo3 != 0) {
                return iCompareTo3;
            }
        }
        Image image = getImage();
        Image image2 = station.getImage();
        if (image != image2) {
            if (image == null) {
                return -1;
            }
            if (image2 == null) {
                return 1;
            }
            int iCompareTo4 = image.compareTo(image2);
            if (iCompareTo4 != 0) {
                return iCompareTo4;
            }
        }
        Seed seed = getSeed();
        Seed seed2 = station.getSeed();
        if (seed != seed2) {
            if (seed == null) {
                return -1;
            }
            if (seed2 == null) {
                return 1;
            }
            int iCompareTo5 = seed.compareTo(seed2);
            if (iCompareTo5 != 0) {
                return iCompareTo5;
            }
        }
        Map<String, ParentalControls> parentalControlsPerContentTier = getParentalControlsPerContentTier();
        Map<String, ParentalControls> parentalControlsPerContentTier2 = station.getParentalControlsPerContentTier();
        if (parentalControlsPerContentTier != parentalControlsPerContentTier2) {
            if (parentalControlsPerContentTier == null) {
                return -1;
            }
            if (parentalControlsPerContentTier2 == null) {
                return 1;
            }
            if (parentalControlsPerContentTier instanceof Comparable) {
                int iCompareTo6 = ((Comparable) parentalControlsPerContentTier).compareTo(parentalControlsPerContentTier2);
                if (iCompareTo6 != 0) {
                    return iCompareTo6;
                }
            } else if (!parentalControlsPerContentTier.equals(parentalControlsPerContentTier2)) {
                int iHashCode3 = parentalControlsPerContentTier.hashCode();
                int iHashCode4 = parentalControlsPerContentTier2.hashCode();
                if (iHashCode3 < iHashCode4) {
                    return -1;
                }
                if (iHashCode3 > iHashCode4) {
                    return 1;
                }
            }
        }
        String title = getTitle();
        String title2 = station.getTitle();
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
        return super.compareTo(entity);
    }

    @Override // com.amazon.musicensembleservice.brush.MusicEntity, com.amazon.musicensembleservice.brush.Entity
    public boolean equals(Object obj) {
        if (!(obj instanceof Station)) {
            return false;
        }
        Station station = (Station) obj;
        return super.equals(obj) && internalEqualityCheck(getContentTiers(), station.getContentTiers()) && internalEqualityCheck(getStationKey(), station.getStationKey()) && internalEqualityCheck(getAssetType(), station.getAssetType()) && internalEqualityCheck(getImage(), station.getImage()) && internalEqualityCheck(getSeed(), station.getSeed()) && internalEqualityCheck(getParentalControlsPerContentTier(), station.getParentalControlsPerContentTier()) && internalEqualityCheck(getTitle(), station.getTitle());
    }

    public String getAssetType() {
        return this.assetType;
    }

    public List<String> getContentTiers() {
        return this.contentTiers;
    }

    public Image getImage() {
        return this.image;
    }

    public Map<String, ParentalControls> getParentalControlsPerContentTier() {
        return this.parentalControlsPerContentTier;
    }

    public Seed getSeed() {
        return this.seed;
    }

    public String getStationKey() {
        return this.stationKey;
    }

    public String getTitle() {
        return this.title;
    }

    @Override // com.amazon.musicensembleservice.brush.MusicEntity, com.amazon.musicensembleservice.brush.Entity
    public int hashCode() {
        return internalHashCodeCompute(Integer.valueOf(super.hashCode()), Integer.valueOf(classNameHashCode), getContentTiers(), getStationKey(), getAssetType(), getImage(), getSeed(), getParentalControlsPerContentTier(), getTitle());
    }

    public void setAssetType(String str) {
        this.assetType = str;
    }

    public void setContentTiers(List<String> list) {
        this.contentTiers = list;
    }

    public void setImage(Image image) {
        this.image = image;
    }

    public void setParentalControlsPerContentTier(Map<String, ParentalControls> map) {
        this.parentalControlsPerContentTier = map;
    }

    public void setSeed(Seed seed) {
        this.seed = seed;
    }

    public void setStationKey(String str) {
        this.stationKey = str;
    }

    public void setTitle(String str) {
        this.title = str;
    }
}
