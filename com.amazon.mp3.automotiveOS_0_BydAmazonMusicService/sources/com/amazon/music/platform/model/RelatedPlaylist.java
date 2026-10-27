package com.amazon.music.platform.model;

import java.util.Arrays;

/* JADX INFO: loaded from: classes4.dex */
public class RelatedPlaylist implements Comparable<RelatedPlaylist> {
    private static final int classNameHashCode = internalHashCodeCompute("com.amazon.music.platform.model.RelatedPlaylist");
    private Art artExtraLarge;
    private Art artFull;
    private Art artLarge;
    private Art artMedium;
    private Art artOriginal;
    private Art artSmall;
    private Art artTiny;
    private String asin;
    private String blockRef;
    private EntityRelation entityRelation;
    private String playlistId;
    private PlaymodeEligibility playmodeEligibility;
    private String title;

    private static boolean internalEqualityCheck(Object obj, Object obj2) {
        return obj == obj2 || (obj != null && obj.equals(obj2));
    }

    private static int internalHashCodeCompute(Object... objArr) {
        return Arrays.hashCode(objArr);
    }

    @Override // java.lang.Comparable
    @Deprecated
    public int compareTo(@Deprecated RelatedPlaylist relatedPlaylist) {
        if (relatedPlaylist == null) {
            return -1;
        }
        if (relatedPlaylist == this) {
            return 0;
        }
        String title = getTitle();
        String title2 = relatedPlaylist.getTitle();
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
        Art artSmall = getArtSmall();
        Art artSmall2 = relatedPlaylist.getArtSmall();
        if (artSmall != artSmall2) {
            if (artSmall == null) {
                return -1;
            }
            if (artSmall2 == null) {
                return 1;
            }
            int iCompareTo2 = artSmall.compareTo(artSmall2);
            if (iCompareTo2 != 0) {
                return iCompareTo2;
            }
        }
        String asin = getAsin();
        String asin2 = relatedPlaylist.getAsin();
        if (asin != asin2) {
            if (asin == null) {
                return -1;
            }
            if (asin2 == null) {
                return 1;
            }
            int iCompareTo3 = asin.compareTo(asin2);
            if (iCompareTo3 != 0) {
                return iCompareTo3;
            }
        }
        Art artLarge = getArtLarge();
        Art artLarge2 = relatedPlaylist.getArtLarge();
        if (artLarge != artLarge2) {
            if (artLarge == null) {
                return -1;
            }
            if (artLarge2 == null) {
                return 1;
            }
            int iCompareTo4 = artLarge.compareTo(artLarge2);
            if (iCompareTo4 != 0) {
                return iCompareTo4;
            }
        }
        Art artExtraLarge = getArtExtraLarge();
        Art artExtraLarge2 = relatedPlaylist.getArtExtraLarge();
        if (artExtraLarge != artExtraLarge2) {
            if (artExtraLarge == null) {
                return -1;
            }
            if (artExtraLarge2 == null) {
                return 1;
            }
            int iCompareTo5 = artExtraLarge.compareTo(artExtraLarge2);
            if (iCompareTo5 != 0) {
                return iCompareTo5;
            }
        }
        String playlistId = getPlaylistId();
        String playlistId2 = relatedPlaylist.getPlaylistId();
        if (playlistId != playlistId2) {
            if (playlistId == null) {
                return -1;
            }
            if (playlistId2 == null) {
                return 1;
            }
            int iCompareTo6 = playlistId.compareTo(playlistId2);
            if (iCompareTo6 != 0) {
                return iCompareTo6;
            }
        }
        String blockRef = getBlockRef();
        String blockRef2 = relatedPlaylist.getBlockRef();
        if (blockRef != blockRef2) {
            if (blockRef == null) {
                return -1;
            }
            if (blockRef2 == null) {
                return 1;
            }
            int iCompareTo7 = blockRef.compareTo(blockRef2);
            if (iCompareTo7 != 0) {
                return iCompareTo7;
            }
        }
        Art artTiny = getArtTiny();
        Art artTiny2 = relatedPlaylist.getArtTiny();
        if (artTiny != artTiny2) {
            if (artTiny == null) {
                return -1;
            }
            if (artTiny2 == null) {
                return 1;
            }
            int iCompareTo8 = artTiny.compareTo(artTiny2);
            if (iCompareTo8 != 0) {
                return iCompareTo8;
            }
        }
        EntityRelation entityRelation = getEntityRelation();
        EntityRelation entityRelation2 = relatedPlaylist.getEntityRelation();
        if (entityRelation != entityRelation2) {
            if (entityRelation == null) {
                return -1;
            }
            if (entityRelation2 == null) {
                return 1;
            }
            int iCompareTo9 = entityRelation.compareTo(entityRelation2);
            if (iCompareTo9 != 0) {
                return iCompareTo9;
            }
        }
        Art artOriginal = getArtOriginal();
        Art artOriginal2 = relatedPlaylist.getArtOriginal();
        if (artOriginal != artOriginal2) {
            if (artOriginal == null) {
                return -1;
            }
            if (artOriginal2 == null) {
                return 1;
            }
            int iCompareTo10 = artOriginal.compareTo(artOriginal2);
            if (iCompareTo10 != 0) {
                return iCompareTo10;
            }
        }
        Art artMedium = getArtMedium();
        Art artMedium2 = relatedPlaylist.getArtMedium();
        if (artMedium != artMedium2) {
            if (artMedium == null) {
                return -1;
            }
            if (artMedium2 == null) {
                return 1;
            }
            int iCompareTo11 = artMedium.compareTo(artMedium2);
            if (iCompareTo11 != 0) {
                return iCompareTo11;
            }
        }
        PlaymodeEligibility playmodeEligibility = getPlaymodeEligibility();
        PlaymodeEligibility playmodeEligibility2 = relatedPlaylist.getPlaymodeEligibility();
        if (playmodeEligibility != playmodeEligibility2) {
            if (playmodeEligibility == null) {
                return -1;
            }
            if (playmodeEligibility2 == null) {
                return 1;
            }
            int iCompareTo12 = playmodeEligibility.compareTo(playmodeEligibility2);
            if (iCompareTo12 != 0) {
                return iCompareTo12;
            }
        }
        Art artFull = getArtFull();
        Art artFull2 = relatedPlaylist.getArtFull();
        if (artFull != artFull2) {
            if (artFull == null) {
                return -1;
            }
            if (artFull2 == null) {
                return 1;
            }
            int iCompareTo13 = artFull.compareTo(artFull2);
            if (iCompareTo13 != 0) {
                return iCompareTo13;
            }
        }
        return 0;
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof RelatedPlaylist)) {
            return false;
        }
        RelatedPlaylist relatedPlaylist = (RelatedPlaylist) obj;
        return internalEqualityCheck(getTitle(), relatedPlaylist.getTitle()) && internalEqualityCheck(getArtSmall(), relatedPlaylist.getArtSmall()) && internalEqualityCheck(getAsin(), relatedPlaylist.getAsin()) && internalEqualityCheck(getArtLarge(), relatedPlaylist.getArtLarge()) && internalEqualityCheck(getArtExtraLarge(), relatedPlaylist.getArtExtraLarge()) && internalEqualityCheck(getPlaylistId(), relatedPlaylist.getPlaylistId()) && internalEqualityCheck(getBlockRef(), relatedPlaylist.getBlockRef()) && internalEqualityCheck(getArtTiny(), relatedPlaylist.getArtTiny()) && internalEqualityCheck(getEntityRelation(), relatedPlaylist.getEntityRelation()) && internalEqualityCheck(getArtOriginal(), relatedPlaylist.getArtOriginal()) && internalEqualityCheck(getArtMedium(), relatedPlaylist.getArtMedium()) && internalEqualityCheck(getPlaymodeEligibility(), relatedPlaylist.getPlaymodeEligibility()) && internalEqualityCheck(getArtFull(), relatedPlaylist.getArtFull());
    }

    public Art getArtExtraLarge() {
        return this.artExtraLarge;
    }

    public Art getArtFull() {
        return this.artFull;
    }

    public Art getArtLarge() {
        return this.artLarge;
    }

    public Art getArtMedium() {
        return this.artMedium;
    }

    public Art getArtOriginal() {
        return this.artOriginal;
    }

    public Art getArtSmall() {
        return this.artSmall;
    }

    public Art getArtTiny() {
        return this.artTiny;
    }

    public String getAsin() {
        return this.asin;
    }

    public String getBlockRef() {
        return this.blockRef;
    }

    public EntityRelation getEntityRelation() {
        return this.entityRelation;
    }

    public String getPlaylistId() {
        return this.playlistId;
    }

    public PlaymodeEligibility getPlaymodeEligibility() {
        return this.playmodeEligibility;
    }

    public String getTitle() {
        return this.title;
    }

    public int hashCode() {
        return internalHashCodeCompute(Integer.valueOf(classNameHashCode), getTitle(), getArtSmall(), getAsin(), getArtLarge(), getArtExtraLarge(), getPlaylistId(), getBlockRef(), getArtTiny(), getEntityRelation(), getArtOriginal(), getArtMedium(), getPlaymodeEligibility(), getArtFull());
    }

    public void setArtExtraLarge(Art art) {
        this.artExtraLarge = art;
    }

    public void setArtFull(Art art) {
        this.artFull = art;
    }

    public void setArtLarge(Art art) {
        this.artLarge = art;
    }

    public void setArtMedium(Art art) {
        this.artMedium = art;
    }

    public void setArtOriginal(Art art) {
        this.artOriginal = art;
    }

    public void setArtSmall(Art art) {
        this.artSmall = art;
    }

    public void setArtTiny(Art art) {
        this.artTiny = art;
    }

    public void setAsin(String str) {
        this.asin = str;
    }

    public void setBlockRef(String str) {
        this.blockRef = str;
    }

    public void setEntityRelation(EntityRelation entityRelation) {
        this.entityRelation = entityRelation;
    }

    public void setPlaylistId(String str) {
        this.playlistId = str;
    }

    public void setPlaymodeEligibility(PlaymodeEligibility playmodeEligibility) {
        this.playmodeEligibility = playmodeEligibility;
    }

    public void setTitle(String str) {
        this.title = str;
    }
}
