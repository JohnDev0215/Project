package com.amazon.musicensembleservice.brush;

import com.amazon.musicensembleservice.brush.CatalogEntity;
import java.util.Arrays;
import java.util.List;

/* JADX INFO: loaded from: classes2.dex */
public class Artist extends CatalogEntity {
    private static final int classNameHashCode = internalHashCodeCompute("com.amazon.musicensembleservice.brush.Artist");
    private String artistStationKey;
    private String contributorAsin;
    private String followerCountText;
    private Image heroImage;
    private Image image;
    private Boolean isFollowing;
    private String name;
    private String percentileRank;
    private List<RelatedEntityItem> relatedEntities;
    private ArtistRelationMetadata relationMetadata;
    private List<Role> roles;

    public static class Builder extends CatalogEntity.Builder {
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
    @Override // com.amazon.musicensembleservice.brush.CatalogEntity, com.amazon.musicensembleservice.brush.MusicEntity, com.amazon.musicensembleservice.brush.Entity, java.lang.Comparable
    @Deprecated
    public int compareTo(@Deprecated Entity entity) {
        if (entity == null) {
            return -1;
        }
        if (entity == this) {
            return 0;
        }
        if (!(entity instanceof Artist)) {
            return 1;
        }
        Artist artist = (Artist) entity;
        List<Role> roles = getRoles();
        List<Role> roles2 = artist.getRoles();
        if (roles != roles2) {
            if (roles == null) {
                return -1;
            }
            if (roles2 == null) {
                return 1;
            }
            if (roles instanceof Comparable) {
                int iCompareTo = ((Comparable) roles).compareTo(roles2);
                if (iCompareTo != 0) {
                    return iCompareTo;
                }
            } else if (!roles.equals(roles2)) {
                int iHashCode = roles.hashCode();
                int iHashCode2 = roles2.hashCode();
                if (iHashCode < iHashCode2) {
                    return -1;
                }
                if (iHashCode > iHashCode2) {
                    return 1;
                }
            }
        }
        ArtistRelationMetadata relationMetadata = getRelationMetadata();
        ArtistRelationMetadata relationMetadata2 = artist.getRelationMetadata();
        if (relationMetadata != relationMetadata2) {
            if (relationMetadata == null) {
                return -1;
            }
            if (relationMetadata2 == null) {
                return 1;
            }
            int iCompareTo2 = relationMetadata.compareTo(relationMetadata2);
            if (iCompareTo2 != 0) {
                return iCompareTo2;
            }
        }
        String percentileRank = getPercentileRank();
        String percentileRank2 = artist.getPercentileRank();
        if (percentileRank != percentileRank2) {
            if (percentileRank == null) {
                return -1;
            }
            if (percentileRank2 == null) {
                return 1;
            }
            int iCompareTo3 = percentileRank.compareTo(percentileRank2);
            if (iCompareTo3 != 0) {
                return iCompareTo3;
            }
        }
        String artistStationKey = getArtistStationKey();
        String artistStationKey2 = artist.getArtistStationKey();
        if (artistStationKey != artistStationKey2) {
            if (artistStationKey == null) {
                return -1;
            }
            if (artistStationKey2 == null) {
                return 1;
            }
            int iCompareTo4 = artistStationKey.compareTo(artistStationKey2);
            if (iCompareTo4 != 0) {
                return iCompareTo4;
            }
        }
        Image image = getImage();
        Image image2 = artist.getImage();
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
        String contributorAsin = getContributorAsin();
        String contributorAsin2 = artist.getContributorAsin();
        if (contributorAsin != contributorAsin2) {
            if (contributorAsin == null) {
                return -1;
            }
            if (contributorAsin2 == null) {
                return 1;
            }
            int iCompareTo6 = contributorAsin.compareTo(contributorAsin2);
            if (iCompareTo6 != 0) {
                return iCompareTo6;
            }
        }
        Boolean boolIsIsFollowing = isIsFollowing();
        Boolean boolIsIsFollowing2 = artist.isIsFollowing();
        if (boolIsIsFollowing != boolIsIsFollowing2) {
            if (boolIsIsFollowing == null) {
                return -1;
            }
            if (boolIsIsFollowing2 == null) {
                return 1;
            }
            int iCompareTo7 = boolIsIsFollowing.compareTo(boolIsIsFollowing2);
            if (iCompareTo7 != 0) {
                return iCompareTo7;
            }
        }
        String name = getName();
        String name2 = artist.getName();
        if (name != name2) {
            if (name == null) {
                return -1;
            }
            if (name2 == null) {
                return 1;
            }
            int iCompareTo8 = name.compareTo(name2);
            if (iCompareTo8 != 0) {
                return iCompareTo8;
            }
        }
        String followerCountText = getFollowerCountText();
        String followerCountText2 = artist.getFollowerCountText();
        if (followerCountText != followerCountText2) {
            if (followerCountText == null) {
                return -1;
            }
            if (followerCountText2 == null) {
                return 1;
            }
            int iCompareTo9 = followerCountText.compareTo(followerCountText2);
            if (iCompareTo9 != 0) {
                return iCompareTo9;
            }
        }
        Image heroImage = getHeroImage();
        Image heroImage2 = artist.getHeroImage();
        if (heroImage != heroImage2) {
            if (heroImage == null) {
                return -1;
            }
            if (heroImage2 == null) {
                return 1;
            }
            int iCompareTo10 = heroImage.compareTo(heroImage2);
            if (iCompareTo10 != 0) {
                return iCompareTo10;
            }
        }
        List<RelatedEntityItem> relatedEntities = getRelatedEntities();
        List<RelatedEntityItem> relatedEntities2 = artist.getRelatedEntities();
        if (relatedEntities != relatedEntities2) {
            if (relatedEntities == null) {
                return -1;
            }
            if (relatedEntities2 == null) {
                return 1;
            }
            if (relatedEntities instanceof Comparable) {
                int iCompareTo11 = ((Comparable) relatedEntities).compareTo(relatedEntities2);
                if (iCompareTo11 != 0) {
                    return iCompareTo11;
                }
            } else if (!relatedEntities.equals(relatedEntities2)) {
                int iHashCode3 = relatedEntities.hashCode();
                int iHashCode4 = relatedEntities2.hashCode();
                if (iHashCode3 < iHashCode4) {
                    return -1;
                }
                if (iHashCode3 > iHashCode4) {
                    return 1;
                }
            }
        }
        return super.compareTo(entity);
    }

    @Override // com.amazon.musicensembleservice.brush.CatalogEntity, com.amazon.musicensembleservice.brush.MusicEntity, com.amazon.musicensembleservice.brush.Entity
    public boolean equals(Object obj) {
        if (!(obj instanceof Artist)) {
            return false;
        }
        Artist artist = (Artist) obj;
        return super.equals(obj) && internalEqualityCheck(getRoles(), artist.getRoles()) && internalEqualityCheck(getRelationMetadata(), artist.getRelationMetadata()) && internalEqualityCheck(getPercentileRank(), artist.getPercentileRank()) && internalEqualityCheck(getArtistStationKey(), artist.getArtistStationKey()) && internalEqualityCheck(getImage(), artist.getImage()) && internalEqualityCheck(getContributorAsin(), artist.getContributorAsin()) && internalEqualityCheck(isIsFollowing(), artist.isIsFollowing()) && internalEqualityCheck(getName(), artist.getName()) && internalEqualityCheck(getFollowerCountText(), artist.getFollowerCountText()) && internalEqualityCheck(getHeroImage(), artist.getHeroImage()) && internalEqualityCheck(getRelatedEntities(), artist.getRelatedEntities());
    }

    public String getArtistStationKey() {
        return this.artistStationKey;
    }

    public String getContributorAsin() {
        return this.contributorAsin;
    }

    public String getFollowerCountText() {
        return this.followerCountText;
    }

    public Image getHeroImage() {
        return this.heroImage;
    }

    public Image getImage() {
        return this.image;
    }

    public String getName() {
        return this.name;
    }

    public String getPercentileRank() {
        return this.percentileRank;
    }

    public List<RelatedEntityItem> getRelatedEntities() {
        return this.relatedEntities;
    }

    public ArtistRelationMetadata getRelationMetadata() {
        return this.relationMetadata;
    }

    public List<Role> getRoles() {
        return this.roles;
    }

    @Override // com.amazon.musicensembleservice.brush.CatalogEntity, com.amazon.musicensembleservice.brush.MusicEntity, com.amazon.musicensembleservice.brush.Entity
    public int hashCode() {
        return internalHashCodeCompute(Integer.valueOf(super.hashCode()), Integer.valueOf(classNameHashCode), getRoles(), getRelationMetadata(), getPercentileRank(), getArtistStationKey(), getImage(), getContributorAsin(), isIsFollowing(), getName(), getFollowerCountText(), getHeroImage(), getRelatedEntities());
    }

    public Boolean isIsFollowing() {
        return this.isFollowing;
    }

    public void setArtistStationKey(String str) {
        this.artistStationKey = str;
    }

    public void setContributorAsin(String str) {
        this.contributorAsin = str;
    }

    public void setFollowerCountText(String str) {
        this.followerCountText = str;
    }

    public void setHeroImage(Image image) {
        this.heroImage = image;
    }

    public void setImage(Image image) {
        this.image = image;
    }

    public void setIsFollowing(Boolean bool) {
        this.isFollowing = bool;
    }

    public void setName(String str) {
        this.name = str;
    }

    public void setPercentileRank(String str) {
        this.percentileRank = str;
    }

    public void setRelatedEntities(List<RelatedEntityItem> list) {
        this.relatedEntities = list;
    }

    public void setRelationMetadata(ArtistRelationMetadata artistRelationMetadata) {
        this.relationMetadata = artistRelationMetadata;
    }

    public void setRoles(List<Role> list) {
        this.roles = list;
    }
}
