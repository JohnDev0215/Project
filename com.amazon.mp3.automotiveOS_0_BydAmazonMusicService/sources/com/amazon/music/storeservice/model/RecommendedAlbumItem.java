package com.amazon.music.storeservice.model;

import com.amazon.musicensembleservice.PlaymodeEligibility;
import java.util.Arrays;
import java.util.List;

/* JADX INFO: loaded from: classes4.dex */
public class RecommendedAlbumItem implements Comparable<RecommendedAlbumItem> {
    private static final int classNameHashCode = internalHashCodeCompute("com.amazon.music.storeservice.model.RecommendedAlbumItem");
    private String albumArtImageUrl;
    private String albumName;
    private String artistAsin;
    private String artistName;
    private String asin;
    private Double averageOverallRating;
    private List<String> contentEncoding;
    private Boolean isFree;
    private Boolean isFreeOnDemandPlayable;
    private Boolean isMusicSubscription;
    private Boolean isPrime;
    private Boolean isSonicRush;
    private Boolean isSonicRushOnDemandPlayable;
    private Justification justification;
    private Long originalReleaseDate;
    private PlaymodeEligibility playmodeEligibility;
    private Integer totalNumberOfTracks;
    private Integer totalReviewCount;

    private static boolean internalEqualityCheck(Object obj, Object obj2) {
        return obj == obj2 || (obj != null && obj.equals(obj2));
    }

    private static int internalHashCodeCompute(Object... objArr) {
        return Arrays.hashCode(objArr);
    }

    @Override // java.lang.Comparable
    @Deprecated
    public int compareTo(@Deprecated RecommendedAlbumItem recommendedAlbumItem) {
        if (recommendedAlbumItem == null) {
            return -1;
        }
        if (recommendedAlbumItem == this) {
            return 0;
        }
        String artistAsin = getArtistAsin();
        String artistAsin2 = recommendedAlbumItem.getArtistAsin();
        if (artistAsin != artistAsin2) {
            if (artistAsin == null) {
                return -1;
            }
            if (artistAsin2 == null) {
                return 1;
            }
            int iCompareTo = artistAsin.compareTo(artistAsin2);
            if (iCompareTo != 0) {
                return iCompareTo;
            }
        }
        Boolean boolIsIsSonicRush = isIsSonicRush();
        Boolean boolIsIsSonicRush2 = recommendedAlbumItem.isIsSonicRush();
        if (boolIsIsSonicRush != boolIsIsSonicRush2) {
            if (boolIsIsSonicRush == null) {
                return -1;
            }
            if (boolIsIsSonicRush2 == null) {
                return 1;
            }
            int iCompareTo2 = boolIsIsSonicRush.compareTo(boolIsIsSonicRush2);
            if (iCompareTo2 != 0) {
                return iCompareTo2;
            }
        }
        Double averageOverallRating = getAverageOverallRating();
        Double averageOverallRating2 = recommendedAlbumItem.getAverageOverallRating();
        if (averageOverallRating != averageOverallRating2) {
            if (averageOverallRating == null) {
                return -1;
            }
            if (averageOverallRating2 == null) {
                return 1;
            }
            int iCompareTo3 = averageOverallRating.compareTo(averageOverallRating2);
            if (iCompareTo3 != 0) {
                return iCompareTo3;
            }
        }
        Integer totalNumberOfTracks = getTotalNumberOfTracks();
        Integer totalNumberOfTracks2 = recommendedAlbumItem.getTotalNumberOfTracks();
        if (totalNumberOfTracks != totalNumberOfTracks2) {
            if (totalNumberOfTracks == null) {
                return -1;
            }
            if (totalNumberOfTracks2 == null) {
                return 1;
            }
            int iCompareTo4 = totalNumberOfTracks.compareTo(totalNumberOfTracks2);
            if (iCompareTo4 != 0) {
                return iCompareTo4;
            }
        }
        Boolean boolIsIsFree = isIsFree();
        Boolean boolIsIsFree2 = recommendedAlbumItem.isIsFree();
        if (boolIsIsFree != boolIsIsFree2) {
            if (boolIsIsFree == null) {
                return -1;
            }
            if (boolIsIsFree2 == null) {
                return 1;
            }
            int iCompareTo5 = boolIsIsFree.compareTo(boolIsIsFree2);
            if (iCompareTo5 != 0) {
                return iCompareTo5;
            }
        }
        Integer totalReviewCount = getTotalReviewCount();
        Integer totalReviewCount2 = recommendedAlbumItem.getTotalReviewCount();
        if (totalReviewCount != totalReviewCount2) {
            if (totalReviewCount == null) {
                return -1;
            }
            if (totalReviewCount2 == null) {
                return 1;
            }
            int iCompareTo6 = totalReviewCount.compareTo(totalReviewCount2);
            if (iCompareTo6 != 0) {
                return iCompareTo6;
            }
        }
        Boolean boolIsIsMusicSubscription = isIsMusicSubscription();
        Boolean boolIsIsMusicSubscription2 = recommendedAlbumItem.isIsMusicSubscription();
        if (boolIsIsMusicSubscription != boolIsIsMusicSubscription2) {
            if (boolIsIsMusicSubscription == null) {
                return -1;
            }
            if (boolIsIsMusicSubscription2 == null) {
                return 1;
            }
            int iCompareTo7 = boolIsIsMusicSubscription.compareTo(boolIsIsMusicSubscription2);
            if (iCompareTo7 != 0) {
                return iCompareTo7;
            }
        }
        String albumArtImageUrl = getAlbumArtImageUrl();
        String albumArtImageUrl2 = recommendedAlbumItem.getAlbumArtImageUrl();
        if (albumArtImageUrl != albumArtImageUrl2) {
            if (albumArtImageUrl == null) {
                return -1;
            }
            if (albumArtImageUrl2 == null) {
                return 1;
            }
            int iCompareTo8 = albumArtImageUrl.compareTo(albumArtImageUrl2);
            if (iCompareTo8 != 0) {
                return iCompareTo8;
            }
        }
        String asin = getAsin();
        String asin2 = recommendedAlbumItem.getAsin();
        if (asin != asin2) {
            if (asin == null) {
                return -1;
            }
            if (asin2 == null) {
                return 1;
            }
            int iCompareTo9 = asin.compareTo(asin2);
            if (iCompareTo9 != 0) {
                return iCompareTo9;
            }
        }
        Boolean boolIsIsSonicRushOnDemandPlayable = isIsSonicRushOnDemandPlayable();
        Boolean boolIsIsSonicRushOnDemandPlayable2 = recommendedAlbumItem.isIsSonicRushOnDemandPlayable();
        if (boolIsIsSonicRushOnDemandPlayable != boolIsIsSonicRushOnDemandPlayable2) {
            if (boolIsIsSonicRushOnDemandPlayable == null) {
                return -1;
            }
            if (boolIsIsSonicRushOnDemandPlayable2 == null) {
                return 1;
            }
            int iCompareTo10 = boolIsIsSonicRushOnDemandPlayable.compareTo(boolIsIsSonicRushOnDemandPlayable2);
            if (iCompareTo10 != 0) {
                return iCompareTo10;
            }
        }
        Boolean boolIsIsFreeOnDemandPlayable = isIsFreeOnDemandPlayable();
        Boolean boolIsIsFreeOnDemandPlayable2 = recommendedAlbumItem.isIsFreeOnDemandPlayable();
        if (boolIsIsFreeOnDemandPlayable != boolIsIsFreeOnDemandPlayable2) {
            if (boolIsIsFreeOnDemandPlayable == null) {
                return -1;
            }
            if (boolIsIsFreeOnDemandPlayable2 == null) {
                return 1;
            }
            int iCompareTo11 = boolIsIsFreeOnDemandPlayable.compareTo(boolIsIsFreeOnDemandPlayable2);
            if (iCompareTo11 != 0) {
                return iCompareTo11;
            }
        }
        String artistName = getArtistName();
        String artistName2 = recommendedAlbumItem.getArtistName();
        if (artistName != artistName2) {
            if (artistName == null) {
                return -1;
            }
            if (artistName2 == null) {
                return 1;
            }
            int iCompareTo12 = artistName.compareTo(artistName2);
            if (iCompareTo12 != 0) {
                return iCompareTo12;
            }
        }
        List<String> contentEncoding = getContentEncoding();
        List<String> contentEncoding2 = recommendedAlbumItem.getContentEncoding();
        if (contentEncoding != contentEncoding2) {
            if (contentEncoding == null) {
                return -1;
            }
            if (contentEncoding2 == null) {
                return 1;
            }
            if (contentEncoding instanceof Comparable) {
                int iCompareTo13 = ((Comparable) contentEncoding).compareTo(contentEncoding2);
                if (iCompareTo13 != 0) {
                    return iCompareTo13;
                }
            } else if (!contentEncoding.equals(contentEncoding2)) {
                int iHashCode = contentEncoding.hashCode();
                int iHashCode2 = contentEncoding2.hashCode();
                if (iHashCode < iHashCode2) {
                    return -1;
                }
                if (iHashCode > iHashCode2) {
                    return 1;
                }
            }
        }
        Justification justification = getJustification();
        Justification justification2 = recommendedAlbumItem.getJustification();
        if (justification != justification2) {
            if (justification == null) {
                return -1;
            }
            if (justification2 == null) {
                return 1;
            }
            int iCompareTo14 = justification.compareTo(justification2);
            if (iCompareTo14 != 0) {
                return iCompareTo14;
            }
        }
        Boolean boolIsIsPrime = isIsPrime();
        Boolean boolIsIsPrime2 = recommendedAlbumItem.isIsPrime();
        if (boolIsIsPrime != boolIsIsPrime2) {
            if (boolIsIsPrime == null) {
                return -1;
            }
            if (boolIsIsPrime2 == null) {
                return 1;
            }
            int iCompareTo15 = boolIsIsPrime.compareTo(boolIsIsPrime2);
            if (iCompareTo15 != 0) {
                return iCompareTo15;
            }
        }
        Long originalReleaseDate = getOriginalReleaseDate();
        Long originalReleaseDate2 = recommendedAlbumItem.getOriginalReleaseDate();
        if (originalReleaseDate != originalReleaseDate2) {
            if (originalReleaseDate == null) {
                return -1;
            }
            if (originalReleaseDate2 == null) {
                return 1;
            }
            int iCompareTo16 = originalReleaseDate.compareTo(originalReleaseDate2);
            if (iCompareTo16 != 0) {
                return iCompareTo16;
            }
        }
        PlaymodeEligibility playmodeEligibility = getPlaymodeEligibility();
        PlaymodeEligibility playmodeEligibility2 = recommendedAlbumItem.getPlaymodeEligibility();
        if (playmodeEligibility != playmodeEligibility2) {
            if (playmodeEligibility == null) {
                return -1;
            }
            if (playmodeEligibility2 == null) {
                return 1;
            }
            int iCompareTo17 = playmodeEligibility.compareTo(playmodeEligibility2);
            if (iCompareTo17 != 0) {
                return iCompareTo17;
            }
        }
        String albumName = getAlbumName();
        String albumName2 = recommendedAlbumItem.getAlbumName();
        if (albumName != albumName2) {
            if (albumName == null) {
                return -1;
            }
            if (albumName2 == null) {
                return 1;
            }
            int iCompareTo18 = albumName.compareTo(albumName2);
            if (iCompareTo18 != 0) {
                return iCompareTo18;
            }
        }
        return 0;
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof RecommendedAlbumItem)) {
            return false;
        }
        RecommendedAlbumItem recommendedAlbumItem = (RecommendedAlbumItem) obj;
        return internalEqualityCheck(getArtistAsin(), recommendedAlbumItem.getArtistAsin()) && internalEqualityCheck(isIsSonicRush(), recommendedAlbumItem.isIsSonicRush()) && internalEqualityCheck(getAverageOverallRating(), recommendedAlbumItem.getAverageOverallRating()) && internalEqualityCheck(getTotalNumberOfTracks(), recommendedAlbumItem.getTotalNumberOfTracks()) && internalEqualityCheck(isIsFree(), recommendedAlbumItem.isIsFree()) && internalEqualityCheck(getTotalReviewCount(), recommendedAlbumItem.getTotalReviewCount()) && internalEqualityCheck(isIsMusicSubscription(), recommendedAlbumItem.isIsMusicSubscription()) && internalEqualityCheck(getAlbumArtImageUrl(), recommendedAlbumItem.getAlbumArtImageUrl()) && internalEqualityCheck(getAsin(), recommendedAlbumItem.getAsin()) && internalEqualityCheck(isIsSonicRushOnDemandPlayable(), recommendedAlbumItem.isIsSonicRushOnDemandPlayable()) && internalEqualityCheck(isIsFreeOnDemandPlayable(), recommendedAlbumItem.isIsFreeOnDemandPlayable()) && internalEqualityCheck(getArtistName(), recommendedAlbumItem.getArtistName()) && internalEqualityCheck(getContentEncoding(), recommendedAlbumItem.getContentEncoding()) && internalEqualityCheck(getJustification(), recommendedAlbumItem.getJustification()) && internalEqualityCheck(isIsPrime(), recommendedAlbumItem.isIsPrime()) && internalEqualityCheck(getOriginalReleaseDate(), recommendedAlbumItem.getOriginalReleaseDate()) && internalEqualityCheck(getPlaymodeEligibility(), recommendedAlbumItem.getPlaymodeEligibility()) && internalEqualityCheck(getAlbumName(), recommendedAlbumItem.getAlbumName());
    }

    public String getAlbumArtImageUrl() {
        return this.albumArtImageUrl;
    }

    public String getAlbumName() {
        return this.albumName;
    }

    public String getArtistAsin() {
        return this.artistAsin;
    }

    public String getArtistName() {
        return this.artistName;
    }

    public String getAsin() {
        return this.asin;
    }

    public Double getAverageOverallRating() {
        return this.averageOverallRating;
    }

    public List<String> getContentEncoding() {
        return this.contentEncoding;
    }

    public Justification getJustification() {
        return this.justification;
    }

    public Long getOriginalReleaseDate() {
        return this.originalReleaseDate;
    }

    public PlaymodeEligibility getPlaymodeEligibility() {
        return this.playmodeEligibility;
    }

    public Integer getTotalNumberOfTracks() {
        return this.totalNumberOfTracks;
    }

    public Integer getTotalReviewCount() {
        return this.totalReviewCount;
    }

    public int hashCode() {
        return internalHashCodeCompute(Integer.valueOf(classNameHashCode), getArtistAsin(), isIsSonicRush(), getAverageOverallRating(), getTotalNumberOfTracks(), isIsFree(), getTotalReviewCount(), isIsMusicSubscription(), getAlbumArtImageUrl(), getAsin(), isIsSonicRushOnDemandPlayable(), isIsFreeOnDemandPlayable(), getArtistName(), getContentEncoding(), getJustification(), isIsPrime(), getOriginalReleaseDate(), getPlaymodeEligibility(), getAlbumName());
    }

    public Boolean isIsFree() {
        return this.isFree;
    }

    public Boolean isIsFreeOnDemandPlayable() {
        return this.isFreeOnDemandPlayable;
    }

    public Boolean isIsMusicSubscription() {
        return this.isMusicSubscription;
    }

    public Boolean isIsPrime() {
        return this.isPrime;
    }

    public Boolean isIsSonicRush() {
        return this.isSonicRush;
    }

    public Boolean isIsSonicRushOnDemandPlayable() {
        return this.isSonicRushOnDemandPlayable;
    }

    public void setAlbumArtImageUrl(String str) {
        this.albumArtImageUrl = str;
    }

    public void setAlbumName(String str) {
        this.albumName = str;
    }

    public void setArtistAsin(String str) {
        this.artistAsin = str;
    }

    public void setArtistName(String str) {
        this.artistName = str;
    }

    public void setAsin(String str) {
        this.asin = str;
    }

    public void setAverageOverallRating(Double d) {
        this.averageOverallRating = d;
    }

    public void setContentEncoding(List<String> list) {
        this.contentEncoding = list;
    }

    public void setIsFree(Boolean bool) {
        this.isFree = bool;
    }

    public void setIsFreeOnDemandPlayable(Boolean bool) {
        this.isFreeOnDemandPlayable = bool;
    }

    public void setIsMusicSubscription(Boolean bool) {
        this.isMusicSubscription = bool;
    }

    public void setIsPrime(Boolean bool) {
        this.isPrime = bool;
    }

    public void setIsSonicRush(Boolean bool) {
        this.isSonicRush = bool;
    }

    public void setIsSonicRushOnDemandPlayable(Boolean bool) {
        this.isSonicRushOnDemandPlayable = bool;
    }

    public void setJustification(Justification justification) {
        this.justification = justification;
    }

    public void setOriginalReleaseDate(Long l) {
        this.originalReleaseDate = l;
    }

    public void setPlaymodeEligibility(PlaymodeEligibility playmodeEligibility) {
        this.playmodeEligibility = playmodeEligibility;
    }

    public void setTotalNumberOfTracks(Integer num) {
        this.totalNumberOfTracks = num;
    }

    public void setTotalReviewCount(Integer num) {
        this.totalReviewCount = num;
    }
}
