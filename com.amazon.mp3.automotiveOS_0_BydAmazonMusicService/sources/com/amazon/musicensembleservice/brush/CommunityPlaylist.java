package com.amazon.musicensembleservice.brush;

import com.amazon.musicensembleservice.PlaymodeEligibility;
import com.amazon.musicensembleservice.brush.MusicEntity;
import java.util.Arrays;
import java.util.List;

/* JADX INFO: loaded from: classes2.dex */
public class CommunityPlaylist extends MusicEntity {
    private static final int classNameHashCode = internalHashCodeCompute("com.amazon.musicensembleservice.brush.CommunityPlaylist");
    private String curator;
    private String description;
    private Long duration;
    private List<Image> images;
    private Boolean isOwned;
    private String playlistId;
    private PlaymodeEligibility playmodeEligibility;
    private String profileId;
    private String title;
    private Long tracksCount;

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
        if (!(entity instanceof CommunityPlaylist)) {
            return 1;
        }
        CommunityPlaylist communityPlaylist = (CommunityPlaylist) entity;
        Long tracksCount = getTracksCount();
        Long tracksCount2 = communityPlaylist.getTracksCount();
        if (tracksCount != tracksCount2) {
            if (tracksCount == null) {
                return -1;
            }
            if (tracksCount2 == null) {
                return 1;
            }
            int iCompareTo = tracksCount.compareTo(tracksCount2);
            if (iCompareTo != 0) {
                return iCompareTo;
            }
        }
        String title = getTitle();
        String title2 = communityPlaylist.getTitle();
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
        String curator = getCurator();
        String curator2 = communityPlaylist.getCurator();
        if (curator != curator2) {
            if (curator == null) {
                return -1;
            }
            if (curator2 == null) {
                return 1;
            }
            int iCompareTo3 = curator.compareTo(curator2);
            if (iCompareTo3 != 0) {
                return iCompareTo3;
            }
        }
        List<Image> images = getImages();
        List<Image> images2 = communityPlaylist.getImages();
        if (images != images2) {
            if (images == null) {
                return -1;
            }
            if (images2 == null) {
                return 1;
            }
            if (images instanceof Comparable) {
                int iCompareTo4 = ((Comparable) images).compareTo(images2);
                if (iCompareTo4 != 0) {
                    return iCompareTo4;
                }
            } else if (!images.equals(images2)) {
                int iHashCode = images.hashCode();
                int iHashCode2 = images2.hashCode();
                if (iHashCode < iHashCode2) {
                    return -1;
                }
                if (iHashCode > iHashCode2) {
                    return 1;
                }
            }
        }
        String description = getDescription();
        String description2 = communityPlaylist.getDescription();
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
        Long duration = getDuration();
        Long duration2 = communityPlaylist.getDuration();
        if (duration != duration2) {
            if (duration == null) {
                return -1;
            }
            if (duration2 == null) {
                return 1;
            }
            int iCompareTo6 = duration.compareTo(duration2);
            if (iCompareTo6 != 0) {
                return iCompareTo6;
            }
        }
        Boolean boolIsIsOwned = isIsOwned();
        Boolean boolIsIsOwned2 = communityPlaylist.isIsOwned();
        if (boolIsIsOwned != boolIsIsOwned2) {
            if (boolIsIsOwned == null) {
                return -1;
            }
            if (boolIsIsOwned2 == null) {
                return 1;
            }
            int iCompareTo7 = boolIsIsOwned.compareTo(boolIsIsOwned2);
            if (iCompareTo7 != 0) {
                return iCompareTo7;
            }
        }
        String profileId = getProfileId();
        String profileId2 = communityPlaylist.getProfileId();
        if (profileId != profileId2) {
            if (profileId == null) {
                return -1;
            }
            if (profileId2 == null) {
                return 1;
            }
            int iCompareTo8 = profileId.compareTo(profileId2);
            if (iCompareTo8 != 0) {
                return iCompareTo8;
            }
        }
        PlaymodeEligibility playmodeEligibility = getPlaymodeEligibility();
        PlaymodeEligibility playmodeEligibility2 = communityPlaylist.getPlaymodeEligibility();
        if (playmodeEligibility != playmodeEligibility2) {
            if (playmodeEligibility == null) {
                return -1;
            }
            if (playmodeEligibility2 == null) {
                return 1;
            }
            int iCompareTo9 = playmodeEligibility.compareTo(playmodeEligibility2);
            if (iCompareTo9 != 0) {
                return iCompareTo9;
            }
        }
        String playlistId = getPlaylistId();
        String playlistId2 = communityPlaylist.getPlaylistId();
        if (playlistId != playlistId2) {
            if (playlistId == null) {
                return -1;
            }
            if (playlistId2 == null) {
                return 1;
            }
            int iCompareTo10 = playlistId.compareTo(playlistId2);
            if (iCompareTo10 != 0) {
                return iCompareTo10;
            }
        }
        return super.compareTo(entity);
    }

    @Override // com.amazon.musicensembleservice.brush.MusicEntity, com.amazon.musicensembleservice.brush.Entity
    public boolean equals(Object obj) {
        if (!(obj instanceof CommunityPlaylist)) {
            return false;
        }
        CommunityPlaylist communityPlaylist = (CommunityPlaylist) obj;
        return super.equals(obj) && internalEqualityCheck(getTracksCount(), communityPlaylist.getTracksCount()) && internalEqualityCheck(getTitle(), communityPlaylist.getTitle()) && internalEqualityCheck(getCurator(), communityPlaylist.getCurator()) && internalEqualityCheck(getImages(), communityPlaylist.getImages()) && internalEqualityCheck(getDescription(), communityPlaylist.getDescription()) && internalEqualityCheck(getDuration(), communityPlaylist.getDuration()) && internalEqualityCheck(isIsOwned(), communityPlaylist.isIsOwned()) && internalEqualityCheck(getProfileId(), communityPlaylist.getProfileId()) && internalEqualityCheck(getPlaymodeEligibility(), communityPlaylist.getPlaymodeEligibility()) && internalEqualityCheck(getPlaylistId(), communityPlaylist.getPlaylistId());
    }

    public String getCurator() {
        return this.curator;
    }

    public String getDescription() {
        return this.description;
    }

    public Long getDuration() {
        return this.duration;
    }

    public List<Image> getImages() {
        return this.images;
    }

    public String getPlaylistId() {
        return this.playlistId;
    }

    public PlaymodeEligibility getPlaymodeEligibility() {
        return this.playmodeEligibility;
    }

    public String getProfileId() {
        return this.profileId;
    }

    public String getTitle() {
        return this.title;
    }

    public Long getTracksCount() {
        return this.tracksCount;
    }

    @Override // com.amazon.musicensembleservice.brush.MusicEntity, com.amazon.musicensembleservice.brush.Entity
    public int hashCode() {
        return internalHashCodeCompute(Integer.valueOf(super.hashCode()), Integer.valueOf(classNameHashCode), getTracksCount(), getTitle(), getCurator(), getImages(), getDescription(), getDuration(), isIsOwned(), getProfileId(), getPlaymodeEligibility(), getPlaylistId());
    }

    public Boolean isIsOwned() {
        return this.isOwned;
    }

    public void setCurator(String str) {
        this.curator = str;
    }

    public void setDescription(String str) {
        this.description = str;
    }

    public void setDuration(Long l) {
        this.duration = l;
    }

    public void setImages(List<Image> list) {
        this.images = list;
    }

    public void setIsOwned(Boolean bool) {
        this.isOwned = bool;
    }

    public void setPlaylistId(String str) {
        this.playlistId = str;
    }

    public void setPlaymodeEligibility(PlaymodeEligibility playmodeEligibility) {
        this.playmodeEligibility = playmodeEligibility;
    }

    public void setProfileId(String str) {
        this.profileId = str;
    }

    public void setTitle(String str) {
        this.title = str;
    }

    public void setTracksCount(Long l) {
        this.tracksCount = l;
    }
}
