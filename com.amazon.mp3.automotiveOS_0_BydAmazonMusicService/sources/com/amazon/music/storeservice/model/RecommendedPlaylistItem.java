package com.amazon.music.storeservice.model;

import com.amazon.musicensembleservice.PlaymodeEligibility;
import java.util.Arrays;
import java.util.List;

/* JADX INFO: loaded from: classes4.dex */
public class RecommendedPlaylistItem implements Comparable<RecommendedPlaylistItem> {
    private static final int classNameHashCode = internalHashCodeCompute("com.amazon.music.storeservice.model.RecommendedPlaylistItem");
    private String albumArtImageUrl;
    private String asin;
    private List<String> authorNames;
    private Double averageOverallRating;
    private String curatedBy;
    private String description;
    private Integer duration;
    private Boolean isFree;
    private Boolean isFreeOnDemandPlayable;
    private Boolean isMusicSubscription;
    private Boolean isPrime;
    private Boolean isSonicRush;
    private Boolean isSonicRushGolden;
    private Boolean isSonicRushOnDemandPlayable;
    private Justification justification;
    private PlaymodeEligibility playmodeEligibility;
    private String title;
    private Integer totalReviewCount;
    private Integer trackCount;

    private static boolean internalEqualityCheck(Object obj, Object obj2) {
        return obj == obj2 || (obj != null && obj.equals(obj2));
    }

    private static int internalHashCodeCompute(Object... objArr) {
        return Arrays.hashCode(objArr);
    }

    @Override // java.lang.Comparable
    @Deprecated
    public int compareTo(@Deprecated RecommendedPlaylistItem recommendedPlaylistItem) {
        if (recommendedPlaylistItem == null) {
            return -1;
        }
        if (recommendedPlaylistItem == this) {
            return 0;
        }
        Integer trackCount = getTrackCount();
        Integer trackCount2 = recommendedPlaylistItem.getTrackCount();
        if (trackCount != trackCount2) {
            if (trackCount == null) {
                return -1;
            }
            if (trackCount2 == null) {
                return 1;
            }
            int iCompareTo = trackCount.compareTo(trackCount2);
            if (iCompareTo != 0) {
                return iCompareTo;
            }
        }
        Integer totalReviewCount = getTotalReviewCount();
        Integer totalReviewCount2 = recommendedPlaylistItem.getTotalReviewCount();
        if (totalReviewCount != totalReviewCount2) {
            if (totalReviewCount == null) {
                return -1;
            }
            if (totalReviewCount2 == null) {
                return 1;
            }
            int iCompareTo2 = totalReviewCount.compareTo(totalReviewCount2);
            if (iCompareTo2 != 0) {
                return iCompareTo2;
            }
        }
        Boolean boolIsIsSonicRush = isIsSonicRush();
        Boolean boolIsIsSonicRush2 = recommendedPlaylistItem.isIsSonicRush();
        if (boolIsIsSonicRush != boolIsIsSonicRush2) {
            if (boolIsIsSonicRush == null) {
                return -1;
            }
            if (boolIsIsSonicRush2 == null) {
                return 1;
            }
            int iCompareTo3 = boolIsIsSonicRush.compareTo(boolIsIsSonicRush2);
            if (iCompareTo3 != 0) {
                return iCompareTo3;
            }
        }
        List<String> authorNames = getAuthorNames();
        List<String> authorNames2 = recommendedPlaylistItem.getAuthorNames();
        if (authorNames != authorNames2) {
            if (authorNames == null) {
                return -1;
            }
            if (authorNames2 == null) {
                return 1;
            }
            if (authorNames instanceof Comparable) {
                int iCompareTo4 = ((Comparable) authorNames).compareTo(authorNames2);
                if (iCompareTo4 != 0) {
                    return iCompareTo4;
                }
            } else if (!authorNames.equals(authorNames2)) {
                int iHashCode = authorNames.hashCode();
                int iHashCode2 = authorNames2.hashCode();
                if (iHashCode < iHashCode2) {
                    return -1;
                }
                if (iHashCode > iHashCode2) {
                    return 1;
                }
            }
        }
        String curatedBy = getCuratedBy();
        String curatedBy2 = recommendedPlaylistItem.getCuratedBy();
        if (curatedBy != curatedBy2) {
            if (curatedBy == null) {
                return -1;
            }
            if (curatedBy2 == null) {
                return 1;
            }
            int iCompareTo5 = curatedBy.compareTo(curatedBy2);
            if (iCompareTo5 != 0) {
                return iCompareTo5;
            }
        }
        Boolean boolIsIsSonicRushGolden = isIsSonicRushGolden();
        Boolean boolIsIsSonicRushGolden2 = recommendedPlaylistItem.isIsSonicRushGolden();
        if (boolIsIsSonicRushGolden != boolIsIsSonicRushGolden2) {
            if (boolIsIsSonicRushGolden == null) {
                return -1;
            }
            if (boolIsIsSonicRushGolden2 == null) {
                return 1;
            }
            int iCompareTo6 = boolIsIsSonicRushGolden.compareTo(boolIsIsSonicRushGolden2);
            if (iCompareTo6 != 0) {
                return iCompareTo6;
            }
        }
        Boolean boolIsIsFree = isIsFree();
        Boolean boolIsIsFree2 = recommendedPlaylistItem.isIsFree();
        if (boolIsIsFree != boolIsIsFree2) {
            if (boolIsIsFree == null) {
                return -1;
            }
            if (boolIsIsFree2 == null) {
                return 1;
            }
            int iCompareTo7 = boolIsIsFree.compareTo(boolIsIsFree2);
            if (iCompareTo7 != 0) {
                return iCompareTo7;
            }
        }
        Boolean boolIsIsMusicSubscription = isIsMusicSubscription();
        Boolean boolIsIsMusicSubscription2 = recommendedPlaylistItem.isIsMusicSubscription();
        if (boolIsIsMusicSubscription != boolIsIsMusicSubscription2) {
            if (boolIsIsMusicSubscription == null) {
                return -1;
            }
            if (boolIsIsMusicSubscription2 == null) {
                return 1;
            }
            int iCompareTo8 = boolIsIsMusicSubscription.compareTo(boolIsIsMusicSubscription2);
            if (iCompareTo8 != 0) {
                return iCompareTo8;
            }
        }
        Integer duration = getDuration();
        Integer duration2 = recommendedPlaylistItem.getDuration();
        if (duration != duration2) {
            if (duration == null) {
                return -1;
            }
            if (duration2 == null) {
                return 1;
            }
            int iCompareTo9 = duration.compareTo(duration2);
            if (iCompareTo9 != 0) {
                return iCompareTo9;
            }
        }
        Boolean boolIsIsFreeOnDemandPlayable = isIsFreeOnDemandPlayable();
        Boolean boolIsIsFreeOnDemandPlayable2 = recommendedPlaylistItem.isIsFreeOnDemandPlayable();
        if (boolIsIsFreeOnDemandPlayable != boolIsIsFreeOnDemandPlayable2) {
            if (boolIsIsFreeOnDemandPlayable == null) {
                return -1;
            }
            if (boolIsIsFreeOnDemandPlayable2 == null) {
                return 1;
            }
            int iCompareTo10 = boolIsIsFreeOnDemandPlayable.compareTo(boolIsIsFreeOnDemandPlayable2);
            if (iCompareTo10 != 0) {
                return iCompareTo10;
            }
        }
        Boolean boolIsIsPrime = isIsPrime();
        Boolean boolIsIsPrime2 = recommendedPlaylistItem.isIsPrime();
        if (boolIsIsPrime != boolIsIsPrime2) {
            if (boolIsIsPrime == null) {
                return -1;
            }
            if (boolIsIsPrime2 == null) {
                return 1;
            }
            int iCompareTo11 = boolIsIsPrime.compareTo(boolIsIsPrime2);
            if (iCompareTo11 != 0) {
                return iCompareTo11;
            }
        }
        Double averageOverallRating = getAverageOverallRating();
        Double averageOverallRating2 = recommendedPlaylistItem.getAverageOverallRating();
        if (averageOverallRating != averageOverallRating2) {
            if (averageOverallRating == null) {
                return -1;
            }
            if (averageOverallRating2 == null) {
                return 1;
            }
            int iCompareTo12 = averageOverallRating.compareTo(averageOverallRating2);
            if (iCompareTo12 != 0) {
                return iCompareTo12;
            }
        }
        Boolean boolIsIsSonicRushOnDemandPlayable = isIsSonicRushOnDemandPlayable();
        Boolean boolIsIsSonicRushOnDemandPlayable2 = recommendedPlaylistItem.isIsSonicRushOnDemandPlayable();
        if (boolIsIsSonicRushOnDemandPlayable != boolIsIsSonicRushOnDemandPlayable2) {
            if (boolIsIsSonicRushOnDemandPlayable == null) {
                return -1;
            }
            if (boolIsIsSonicRushOnDemandPlayable2 == null) {
                return 1;
            }
            int iCompareTo13 = boolIsIsSonicRushOnDemandPlayable.compareTo(boolIsIsSonicRushOnDemandPlayable2);
            if (iCompareTo13 != 0) {
                return iCompareTo13;
            }
        }
        PlaymodeEligibility playmodeEligibility = getPlaymodeEligibility();
        PlaymodeEligibility playmodeEligibility2 = recommendedPlaylistItem.getPlaymodeEligibility();
        if (playmodeEligibility != playmodeEligibility2) {
            if (playmodeEligibility == null) {
                return -1;
            }
            if (playmodeEligibility2 == null) {
                return 1;
            }
            int iCompareTo14 = playmodeEligibility.compareTo(playmodeEligibility2);
            if (iCompareTo14 != 0) {
                return iCompareTo14;
            }
        }
        String asin = getAsin();
        String asin2 = recommendedPlaylistItem.getAsin();
        if (asin != asin2) {
            if (asin == null) {
                return -1;
            }
            if (asin2 == null) {
                return 1;
            }
            int iCompareTo15 = asin.compareTo(asin2);
            if (iCompareTo15 != 0) {
                return iCompareTo15;
            }
        }
        String description = getDescription();
        String description2 = recommendedPlaylistItem.getDescription();
        if (description != description2) {
            if (description == null) {
                return -1;
            }
            if (description2 == null) {
                return 1;
            }
            int iCompareTo16 = description.compareTo(description2);
            if (iCompareTo16 != 0) {
                return iCompareTo16;
            }
        }
        String albumArtImageUrl = getAlbumArtImageUrl();
        String albumArtImageUrl2 = recommendedPlaylistItem.getAlbumArtImageUrl();
        if (albumArtImageUrl != albumArtImageUrl2) {
            if (albumArtImageUrl == null) {
                return -1;
            }
            if (albumArtImageUrl2 == null) {
                return 1;
            }
            int iCompareTo17 = albumArtImageUrl.compareTo(albumArtImageUrl2);
            if (iCompareTo17 != 0) {
                return iCompareTo17;
            }
        }
        Justification justification = getJustification();
        Justification justification2 = recommendedPlaylistItem.getJustification();
        if (justification != justification2) {
            if (justification == null) {
                return -1;
            }
            if (justification2 == null) {
                return 1;
            }
            int iCompareTo18 = justification.compareTo(justification2);
            if (iCompareTo18 != 0) {
                return iCompareTo18;
            }
        }
        String title = getTitle();
        String title2 = recommendedPlaylistItem.getTitle();
        if (title != title2) {
            if (title == null) {
                return -1;
            }
            if (title2 == null) {
                return 1;
            }
            int iCompareTo19 = title.compareTo(title2);
            if (iCompareTo19 != 0) {
                return iCompareTo19;
            }
        }
        return 0;
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof RecommendedPlaylistItem)) {
            return false;
        }
        RecommendedPlaylistItem recommendedPlaylistItem = (RecommendedPlaylistItem) obj;
        return internalEqualityCheck(getTrackCount(), recommendedPlaylistItem.getTrackCount()) && internalEqualityCheck(getTotalReviewCount(), recommendedPlaylistItem.getTotalReviewCount()) && internalEqualityCheck(isIsSonicRush(), recommendedPlaylistItem.isIsSonicRush()) && internalEqualityCheck(getAuthorNames(), recommendedPlaylistItem.getAuthorNames()) && internalEqualityCheck(getCuratedBy(), recommendedPlaylistItem.getCuratedBy()) && internalEqualityCheck(isIsSonicRushGolden(), recommendedPlaylistItem.isIsSonicRushGolden()) && internalEqualityCheck(isIsFree(), recommendedPlaylistItem.isIsFree()) && internalEqualityCheck(isIsMusicSubscription(), recommendedPlaylistItem.isIsMusicSubscription()) && internalEqualityCheck(getDuration(), recommendedPlaylistItem.getDuration()) && internalEqualityCheck(isIsFreeOnDemandPlayable(), recommendedPlaylistItem.isIsFreeOnDemandPlayable()) && internalEqualityCheck(isIsPrime(), recommendedPlaylistItem.isIsPrime()) && internalEqualityCheck(getAverageOverallRating(), recommendedPlaylistItem.getAverageOverallRating()) && internalEqualityCheck(isIsSonicRushOnDemandPlayable(), recommendedPlaylistItem.isIsSonicRushOnDemandPlayable()) && internalEqualityCheck(getPlaymodeEligibility(), recommendedPlaylistItem.getPlaymodeEligibility()) && internalEqualityCheck(getAsin(), recommendedPlaylistItem.getAsin()) && internalEqualityCheck(getDescription(), recommendedPlaylistItem.getDescription()) && internalEqualityCheck(getAlbumArtImageUrl(), recommendedPlaylistItem.getAlbumArtImageUrl()) && internalEqualityCheck(getJustification(), recommendedPlaylistItem.getJustification()) && internalEqualityCheck(getTitle(), recommendedPlaylistItem.getTitle());
    }

    public String getAlbumArtImageUrl() {
        return this.albumArtImageUrl;
    }

    public String getAsin() {
        return this.asin;
    }

    public List<String> getAuthorNames() {
        return this.authorNames;
    }

    public Double getAverageOverallRating() {
        return this.averageOverallRating;
    }

    public String getCuratedBy() {
        return this.curatedBy;
    }

    public String getDescription() {
        return this.description;
    }

    public Integer getDuration() {
        return this.duration;
    }

    public Justification getJustification() {
        return this.justification;
    }

    public PlaymodeEligibility getPlaymodeEligibility() {
        return this.playmodeEligibility;
    }

    public String getTitle() {
        return this.title;
    }

    public Integer getTotalReviewCount() {
        return this.totalReviewCount;
    }

    public Integer getTrackCount() {
        return this.trackCount;
    }

    public int hashCode() {
        return internalHashCodeCompute(Integer.valueOf(classNameHashCode), getTrackCount(), getTotalReviewCount(), isIsSonicRush(), getAuthorNames(), getCuratedBy(), isIsSonicRushGolden(), isIsFree(), isIsMusicSubscription(), getDuration(), isIsFreeOnDemandPlayable(), isIsPrime(), getAverageOverallRating(), isIsSonicRushOnDemandPlayable(), getPlaymodeEligibility(), getAsin(), getDescription(), getAlbumArtImageUrl(), getJustification(), getTitle());
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

    public Boolean isIsSonicRushGolden() {
        return this.isSonicRushGolden;
    }

    public Boolean isIsSonicRushOnDemandPlayable() {
        return this.isSonicRushOnDemandPlayable;
    }

    public void setAlbumArtImageUrl(String str) {
        this.albumArtImageUrl = str;
    }

    public void setAsin(String str) {
        this.asin = str;
    }

    public void setAuthorNames(List<String> list) {
        this.authorNames = list;
    }

    public void setAverageOverallRating(Double d) {
        this.averageOverallRating = d;
    }

    public void setCuratedBy(String str) {
        this.curatedBy = str;
    }

    public void setDescription(String str) {
        this.description = str;
    }

    public void setDuration(Integer num) {
        this.duration = num;
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

    public void setIsSonicRushGolden(Boolean bool) {
        this.isSonicRushGolden = bool;
    }

    public void setIsSonicRushOnDemandPlayable(Boolean bool) {
        this.isSonicRushOnDemandPlayable = bool;
    }

    public void setJustification(Justification justification) {
        this.justification = justification;
    }

    public void setPlaymodeEligibility(PlaymodeEligibility playmodeEligibility) {
        this.playmodeEligibility = playmodeEligibility;
    }

    public void setTitle(String str) {
        this.title = str;
    }

    public void setTotalReviewCount(Integer num) {
        this.totalReviewCount = num;
    }

    public void setTrackCount(Integer num) {
        this.trackCount = num;
    }
}
