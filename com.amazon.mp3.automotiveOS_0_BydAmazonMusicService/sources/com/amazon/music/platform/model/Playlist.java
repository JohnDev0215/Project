package com.amazon.music.platform.model;

import java.util.Arrays;
import java.util.List;

/* JADX INFO: loaded from: classes4.dex */
public abstract class Playlist extends Document {
    private static final int classNameHashCode = internalHashCodeCompute("com.amazon.music.platform.model.Playlist");
    private Art artExtraLarge;
    private Art artFull;
    private Art artLarge;
    private Art artMedium;
    private Art artOriginal;
    private Art artSmall;
    private Art artTiny;
    private String asin;
    private List<String> contentTier;
    private Integer duration;
    private String isMusicSubscription;
    private String playlistType;
    private String primaryGenre;
    private String primeStatus;
    private String title;
    private Integer trackCount;

    private static boolean internalEqualityCheck(Object obj, Object obj2) {
        return obj == obj2 || (obj != null && obj.equals(obj2));
    }

    private static int internalHashCodeCompute(Object... objArr) {
        return Arrays.hashCode(objArr);
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.amazon.music.platform.model.Document, java.lang.Comparable
    @Deprecated
    public int compareTo(@Deprecated Document document) {
        if (document == null) {
            return -1;
        }
        if (document == this) {
            return 0;
        }
        if (!(document instanceof Playlist)) {
            return 1;
        }
        Playlist playlist = (Playlist) document;
        String primeStatus = getPrimeStatus();
        String primeStatus2 = playlist.getPrimeStatus();
        if (primeStatus != primeStatus2) {
            if (primeStatus == null) {
                return -1;
            }
            if (primeStatus2 == null) {
                return 1;
            }
            int iCompareTo = primeStatus.compareTo(primeStatus2);
            if (iCompareTo != 0) {
                return iCompareTo;
            }
        }
        String asin = getAsin();
        String asin2 = playlist.getAsin();
        if (asin != asin2) {
            if (asin == null) {
                return -1;
            }
            if (asin2 == null) {
                return 1;
            }
            int iCompareTo2 = asin.compareTo(asin2);
            if (iCompareTo2 != 0) {
                return iCompareTo2;
            }
        }
        String title = getTitle();
        String title2 = playlist.getTitle();
        if (title != title2) {
            if (title == null) {
                return -1;
            }
            if (title2 == null) {
                return 1;
            }
            int iCompareTo3 = title.compareTo(title2);
            if (iCompareTo3 != 0) {
                return iCompareTo3;
            }
        }
        List<String> contentTier = getContentTier();
        List<String> contentTier2 = playlist.getContentTier();
        if (contentTier != contentTier2) {
            if (contentTier == null) {
                return -1;
            }
            if (contentTier2 == null) {
                return 1;
            }
            if (contentTier instanceof Comparable) {
                int iCompareTo4 = ((Comparable) contentTier).compareTo(contentTier2);
                if (iCompareTo4 != 0) {
                    return iCompareTo4;
                }
            } else if (!contentTier.equals(contentTier2)) {
                int iHashCode = contentTier.hashCode();
                int iHashCode2 = contentTier2.hashCode();
                if (iHashCode < iHashCode2) {
                    return -1;
                }
                if (iHashCode > iHashCode2) {
                    return 1;
                }
            }
        }
        Integer trackCount = getTrackCount();
        Integer trackCount2 = playlist.getTrackCount();
        if (trackCount != trackCount2) {
            if (trackCount == null) {
                return -1;
            }
            if (trackCount2 == null) {
                return 1;
            }
            int iCompareTo5 = trackCount.compareTo(trackCount2);
            if (iCompareTo5 != 0) {
                return iCompareTo5;
            }
        }
        Art artMedium = getArtMedium();
        Art artMedium2 = playlist.getArtMedium();
        if (artMedium != artMedium2) {
            if (artMedium == null) {
                return -1;
            }
            if (artMedium2 == null) {
                return 1;
            }
            int iCompareTo6 = artMedium.compareTo(artMedium2);
            if (iCompareTo6 != 0) {
                return iCompareTo6;
            }
        }
        Art artSmall = getArtSmall();
        Art artSmall2 = playlist.getArtSmall();
        if (artSmall != artSmall2) {
            if (artSmall == null) {
                return -1;
            }
            if (artSmall2 == null) {
                return 1;
            }
            int iCompareTo7 = artSmall.compareTo(artSmall2);
            if (iCompareTo7 != 0) {
                return iCompareTo7;
            }
        }
        String primaryGenre = getPrimaryGenre();
        String primaryGenre2 = playlist.getPrimaryGenre();
        if (primaryGenre != primaryGenre2) {
            if (primaryGenre == null) {
                return -1;
            }
            if (primaryGenre2 == null) {
                return 1;
            }
            int iCompareTo8 = primaryGenre.compareTo(primaryGenre2);
            if (iCompareTo8 != 0) {
                return iCompareTo8;
            }
        }
        Art artTiny = getArtTiny();
        Art artTiny2 = playlist.getArtTiny();
        if (artTiny != artTiny2) {
            if (artTiny == null) {
                return -1;
            }
            if (artTiny2 == null) {
                return 1;
            }
            int iCompareTo9 = artTiny.compareTo(artTiny2);
            if (iCompareTo9 != 0) {
                return iCompareTo9;
            }
        }
        String isMusicSubscription = getIsMusicSubscription();
        String isMusicSubscription2 = playlist.getIsMusicSubscription();
        if (isMusicSubscription != isMusicSubscription2) {
            if (isMusicSubscription == null) {
                return -1;
            }
            if (isMusicSubscription2 == null) {
                return 1;
            }
            int iCompareTo10 = isMusicSubscription.compareTo(isMusicSubscription2);
            if (iCompareTo10 != 0) {
                return iCompareTo10;
            }
        }
        Integer duration = getDuration();
        Integer duration2 = playlist.getDuration();
        if (duration != duration2) {
            if (duration == null) {
                return -1;
            }
            if (duration2 == null) {
                return 1;
            }
            int iCompareTo11 = duration.compareTo(duration2);
            if (iCompareTo11 != 0) {
                return iCompareTo11;
            }
        }
        Art artFull = getArtFull();
        Art artFull2 = playlist.getArtFull();
        if (artFull != artFull2) {
            if (artFull == null) {
                return -1;
            }
            if (artFull2 == null) {
                return 1;
            }
            int iCompareTo12 = artFull.compareTo(artFull2);
            if (iCompareTo12 != 0) {
                return iCompareTo12;
            }
        }
        String playlistType = getPlaylistType();
        String playlistType2 = playlist.getPlaylistType();
        if (playlistType != playlistType2) {
            if (playlistType == null) {
                return -1;
            }
            if (playlistType2 == null) {
                return 1;
            }
            int iCompareTo13 = playlistType.compareTo(playlistType2);
            if (iCompareTo13 != 0) {
                return iCompareTo13;
            }
        }
        Art artLarge = getArtLarge();
        Art artLarge2 = playlist.getArtLarge();
        if (artLarge != artLarge2) {
            if (artLarge == null) {
                return -1;
            }
            if (artLarge2 == null) {
                return 1;
            }
            int iCompareTo14 = artLarge.compareTo(artLarge2);
            if (iCompareTo14 != 0) {
                return iCompareTo14;
            }
        }
        Art artExtraLarge = getArtExtraLarge();
        Art artExtraLarge2 = playlist.getArtExtraLarge();
        if (artExtraLarge != artExtraLarge2) {
            if (artExtraLarge == null) {
                return -1;
            }
            if (artExtraLarge2 == null) {
                return 1;
            }
            int iCompareTo15 = artExtraLarge.compareTo(artExtraLarge2);
            if (iCompareTo15 != 0) {
                return iCompareTo15;
            }
        }
        Art artOriginal = getArtOriginal();
        Art artOriginal2 = playlist.getArtOriginal();
        if (artOriginal != artOriginal2) {
            if (artOriginal == null) {
                return -1;
            }
            if (artOriginal2 == null) {
                return 1;
            }
            int iCompareTo16 = artOriginal.compareTo(artOriginal2);
            if (iCompareTo16 != 0) {
                return iCompareTo16;
            }
        }
        return super.compareTo(document);
    }

    @Override // com.amazon.music.platform.model.Document
    public boolean equals(Object obj) {
        if (!(obj instanceof Playlist)) {
            return false;
        }
        Playlist playlist = (Playlist) obj;
        return super.equals(obj) && internalEqualityCheck(getPrimeStatus(), playlist.getPrimeStatus()) && internalEqualityCheck(getAsin(), playlist.getAsin()) && internalEqualityCheck(getTitle(), playlist.getTitle()) && internalEqualityCheck(getContentTier(), playlist.getContentTier()) && internalEqualityCheck(getTrackCount(), playlist.getTrackCount()) && internalEqualityCheck(getArtMedium(), playlist.getArtMedium()) && internalEqualityCheck(getArtSmall(), playlist.getArtSmall()) && internalEqualityCheck(getPrimaryGenre(), playlist.getPrimaryGenre()) && internalEqualityCheck(getArtTiny(), playlist.getArtTiny()) && internalEqualityCheck(getIsMusicSubscription(), playlist.getIsMusicSubscription()) && internalEqualityCheck(getDuration(), playlist.getDuration()) && internalEqualityCheck(getArtFull(), playlist.getArtFull()) && internalEqualityCheck(getPlaylistType(), playlist.getPlaylistType()) && internalEqualityCheck(getArtLarge(), playlist.getArtLarge()) && internalEqualityCheck(getArtExtraLarge(), playlist.getArtExtraLarge()) && internalEqualityCheck(getArtOriginal(), playlist.getArtOriginal());
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

    public List<String> getContentTier() {
        return this.contentTier;
    }

    public Integer getDuration() {
        return this.duration;
    }

    public String getIsMusicSubscription() {
        return this.isMusicSubscription;
    }

    public String getPlaylistType() {
        return this.playlistType;
    }

    public String getPrimaryGenre() {
        return this.primaryGenre;
    }

    public String getPrimeStatus() {
        return this.primeStatus;
    }

    public String getTitle() {
        return this.title;
    }

    public Integer getTrackCount() {
        return this.trackCount;
    }

    @Override // com.amazon.music.platform.model.Document
    public int hashCode() {
        return internalHashCodeCompute(Integer.valueOf(super.hashCode()), Integer.valueOf(classNameHashCode), getPrimeStatus(), getAsin(), getTitle(), getContentTier(), getTrackCount(), getArtMedium(), getArtSmall(), getPrimaryGenre(), getArtTiny(), getIsMusicSubscription(), getDuration(), getArtFull(), getPlaylistType(), getArtLarge(), getArtExtraLarge(), getArtOriginal());
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

    public void setContentTier(List<String> list) {
        this.contentTier = list;
    }

    public void setDuration(Integer num) {
        this.duration = num;
    }

    public void setIsMusicSubscription(String str) {
        this.isMusicSubscription = str;
    }

    public void setPlaylistType(String str) {
        this.playlistType = str;
    }

    public void setPrimaryGenre(String str) {
        this.primaryGenre = str;
    }

    public void setPrimeStatus(String str) {
        this.primeStatus = str;
    }

    public void setTitle(String str) {
        this.title = str;
    }

    public void setTrackCount(Integer num) {
        this.trackCount = num;
    }
}
