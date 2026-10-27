package com.amazon.music.storeservice.model;

import java.util.Arrays;
import java.util.List;

/* JADX INFO: loaded from: classes4.dex */
public class MusicStoreRecommendedAlbumItem implements Comparable<MusicStoreRecommendedAlbumItem> {
    private static final int classNameHashCode = internalHashCodeCompute("com.amazon.music.storeservice.model.MusicStoreRecommendedAlbumItem");
    private String albumArtImageUrl;
    private String albumName;
    private String artistAsin;
    private String artistName;
    private String asin;
    private Double averageOverallRating;
    private List<String> contentEncoding;
    private String currency;
    private List<String> deliveryRestrictionList;
    private Boolean isFree;
    private Boolean isFreeOnDemandPlayable;
    private Boolean isMusicSubscription;
    private Boolean isPrime;
    private Long originalReleaseDate;
    private Double price;
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
    public int compareTo(@Deprecated MusicStoreRecommendedAlbumItem musicStoreRecommendedAlbumItem) {
        if (musicStoreRecommendedAlbumItem == null) {
            return -1;
        }
        if (musicStoreRecommendedAlbumItem == this) {
            return 0;
        }
        String currency = getCurrency();
        String currency2 = musicStoreRecommendedAlbumItem.getCurrency();
        if (currency != currency2) {
            if (currency == null) {
                return -1;
            }
            if (currency2 == null) {
                return 1;
            }
            int iCompareTo = currency.compareTo(currency2);
            if (iCompareTo != 0) {
                return iCompareTo;
            }
        }
        Boolean boolIsIsPrime = isIsPrime();
        Boolean boolIsIsPrime2 = musicStoreRecommendedAlbumItem.isIsPrime();
        if (boolIsIsPrime != boolIsIsPrime2) {
            if (boolIsIsPrime == null) {
                return -1;
            }
            if (boolIsIsPrime2 == null) {
                return 1;
            }
            int iCompareTo2 = boolIsIsPrime.compareTo(boolIsIsPrime2);
            if (iCompareTo2 != 0) {
                return iCompareTo2;
            }
        }
        Integer totalNumberOfTracks = getTotalNumberOfTracks();
        Integer totalNumberOfTracks2 = musicStoreRecommendedAlbumItem.getTotalNumberOfTracks();
        if (totalNumberOfTracks != totalNumberOfTracks2) {
            if (totalNumberOfTracks == null) {
                return -1;
            }
            if (totalNumberOfTracks2 == null) {
                return 1;
            }
            int iCompareTo3 = totalNumberOfTracks.compareTo(totalNumberOfTracks2);
            if (iCompareTo3 != 0) {
                return iCompareTo3;
            }
        }
        Double averageOverallRating = getAverageOverallRating();
        Double averageOverallRating2 = musicStoreRecommendedAlbumItem.getAverageOverallRating();
        if (averageOverallRating != averageOverallRating2) {
            if (averageOverallRating == null) {
                return -1;
            }
            if (averageOverallRating2 == null) {
                return 1;
            }
            int iCompareTo4 = averageOverallRating.compareTo(averageOverallRating2);
            if (iCompareTo4 != 0) {
                return iCompareTo4;
            }
        }
        Boolean boolIsIsFreeOnDemandPlayable = isIsFreeOnDemandPlayable();
        Boolean boolIsIsFreeOnDemandPlayable2 = musicStoreRecommendedAlbumItem.isIsFreeOnDemandPlayable();
        if (boolIsIsFreeOnDemandPlayable != boolIsIsFreeOnDemandPlayable2) {
            if (boolIsIsFreeOnDemandPlayable == null) {
                return -1;
            }
            if (boolIsIsFreeOnDemandPlayable2 == null) {
                return 1;
            }
            int iCompareTo5 = boolIsIsFreeOnDemandPlayable.compareTo(boolIsIsFreeOnDemandPlayable2);
            if (iCompareTo5 != 0) {
                return iCompareTo5;
            }
        }
        String asin = getAsin();
        String asin2 = musicStoreRecommendedAlbumItem.getAsin();
        if (asin != asin2) {
            if (asin == null) {
                return -1;
            }
            if (asin2 == null) {
                return 1;
            }
            int iCompareTo6 = asin.compareTo(asin2);
            if (iCompareTo6 != 0) {
                return iCompareTo6;
            }
        }
        String artistName = getArtistName();
        String artistName2 = musicStoreRecommendedAlbumItem.getArtistName();
        if (artistName != artistName2) {
            if (artistName == null) {
                return -1;
            }
            if (artistName2 == null) {
                return 1;
            }
            int iCompareTo7 = artistName.compareTo(artistName2);
            if (iCompareTo7 != 0) {
                return iCompareTo7;
            }
        }
        String albumArtImageUrl = getAlbumArtImageUrl();
        String albumArtImageUrl2 = musicStoreRecommendedAlbumItem.getAlbumArtImageUrl();
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
        Double price = getPrice();
        Double price2 = musicStoreRecommendedAlbumItem.getPrice();
        if (price != price2) {
            if (price == null) {
                return -1;
            }
            if (price2 == null) {
                return 1;
            }
            int iCompareTo9 = price.compareTo(price2);
            if (iCompareTo9 != 0) {
                return iCompareTo9;
            }
        }
        Long originalReleaseDate = getOriginalReleaseDate();
        Long originalReleaseDate2 = musicStoreRecommendedAlbumItem.getOriginalReleaseDate();
        if (originalReleaseDate != originalReleaseDate2) {
            if (originalReleaseDate == null) {
                return -1;
            }
            if (originalReleaseDate2 == null) {
                return 1;
            }
            int iCompareTo10 = originalReleaseDate.compareTo(originalReleaseDate2);
            if (iCompareTo10 != 0) {
                return iCompareTo10;
            }
        }
        String albumName = getAlbumName();
        String albumName2 = musicStoreRecommendedAlbumItem.getAlbumName();
        if (albumName != albumName2) {
            if (albumName == null) {
                return -1;
            }
            if (albumName2 == null) {
                return 1;
            }
            int iCompareTo11 = albumName.compareTo(albumName2);
            if (iCompareTo11 != 0) {
                return iCompareTo11;
            }
        }
        Boolean boolIsIsFree = isIsFree();
        Boolean boolIsIsFree2 = musicStoreRecommendedAlbumItem.isIsFree();
        if (boolIsIsFree != boolIsIsFree2) {
            if (boolIsIsFree == null) {
                return -1;
            }
            if (boolIsIsFree2 == null) {
                return 1;
            }
            int iCompareTo12 = boolIsIsFree.compareTo(boolIsIsFree2);
            if (iCompareTo12 != 0) {
                return iCompareTo12;
            }
        }
        List<String> contentEncoding = getContentEncoding();
        List<String> contentEncoding2 = musicStoreRecommendedAlbumItem.getContentEncoding();
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
        Integer totalReviewCount = getTotalReviewCount();
        Integer totalReviewCount2 = musicStoreRecommendedAlbumItem.getTotalReviewCount();
        if (totalReviewCount != totalReviewCount2) {
            if (totalReviewCount == null) {
                return -1;
            }
            if (totalReviewCount2 == null) {
                return 1;
            }
            int iCompareTo14 = totalReviewCount.compareTo(totalReviewCount2);
            if (iCompareTo14 != 0) {
                return iCompareTo14;
            }
        }
        String artistAsin = getArtistAsin();
        String artistAsin2 = musicStoreRecommendedAlbumItem.getArtistAsin();
        if (artistAsin != artistAsin2) {
            if (artistAsin == null) {
                return -1;
            }
            if (artistAsin2 == null) {
                return 1;
            }
            int iCompareTo15 = artistAsin.compareTo(artistAsin2);
            if (iCompareTo15 != 0) {
                return iCompareTo15;
            }
        }
        List<String> deliveryRestrictionList = getDeliveryRestrictionList();
        List<String> deliveryRestrictionList2 = musicStoreRecommendedAlbumItem.getDeliveryRestrictionList();
        if (deliveryRestrictionList != deliveryRestrictionList2) {
            if (deliveryRestrictionList == null) {
                return -1;
            }
            if (deliveryRestrictionList2 == null) {
                return 1;
            }
            if (deliveryRestrictionList instanceof Comparable) {
                int iCompareTo16 = ((Comparable) deliveryRestrictionList).compareTo(deliveryRestrictionList2);
                if (iCompareTo16 != 0) {
                    return iCompareTo16;
                }
            } else if (!deliveryRestrictionList.equals(deliveryRestrictionList2)) {
                int iHashCode3 = deliveryRestrictionList.hashCode();
                int iHashCode4 = deliveryRestrictionList2.hashCode();
                if (iHashCode3 < iHashCode4) {
                    return -1;
                }
                if (iHashCode3 > iHashCode4) {
                    return 1;
                }
            }
        }
        Boolean boolIsIsMusicSubscription = isIsMusicSubscription();
        Boolean boolIsIsMusicSubscription2 = musicStoreRecommendedAlbumItem.isIsMusicSubscription();
        if (boolIsIsMusicSubscription != boolIsIsMusicSubscription2) {
            if (boolIsIsMusicSubscription == null) {
                return -1;
            }
            if (boolIsIsMusicSubscription2 == null) {
                return 1;
            }
            int iCompareTo17 = boolIsIsMusicSubscription.compareTo(boolIsIsMusicSubscription2);
            if (iCompareTo17 != 0) {
                return iCompareTo17;
            }
        }
        return 0;
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof MusicStoreRecommendedAlbumItem)) {
            return false;
        }
        MusicStoreRecommendedAlbumItem musicStoreRecommendedAlbumItem = (MusicStoreRecommendedAlbumItem) obj;
        return internalEqualityCheck(getCurrency(), musicStoreRecommendedAlbumItem.getCurrency()) && internalEqualityCheck(isIsPrime(), musicStoreRecommendedAlbumItem.isIsPrime()) && internalEqualityCheck(getTotalNumberOfTracks(), musicStoreRecommendedAlbumItem.getTotalNumberOfTracks()) && internalEqualityCheck(getAverageOverallRating(), musicStoreRecommendedAlbumItem.getAverageOverallRating()) && internalEqualityCheck(isIsFreeOnDemandPlayable(), musicStoreRecommendedAlbumItem.isIsFreeOnDemandPlayable()) && internalEqualityCheck(getAsin(), musicStoreRecommendedAlbumItem.getAsin()) && internalEqualityCheck(getArtistName(), musicStoreRecommendedAlbumItem.getArtistName()) && internalEqualityCheck(getAlbumArtImageUrl(), musicStoreRecommendedAlbumItem.getAlbumArtImageUrl()) && internalEqualityCheck(getPrice(), musicStoreRecommendedAlbumItem.getPrice()) && internalEqualityCheck(getOriginalReleaseDate(), musicStoreRecommendedAlbumItem.getOriginalReleaseDate()) && internalEqualityCheck(getAlbumName(), musicStoreRecommendedAlbumItem.getAlbumName()) && internalEqualityCheck(isIsFree(), musicStoreRecommendedAlbumItem.isIsFree()) && internalEqualityCheck(getContentEncoding(), musicStoreRecommendedAlbumItem.getContentEncoding()) && internalEqualityCheck(getTotalReviewCount(), musicStoreRecommendedAlbumItem.getTotalReviewCount()) && internalEqualityCheck(getArtistAsin(), musicStoreRecommendedAlbumItem.getArtistAsin()) && internalEqualityCheck(getDeliveryRestrictionList(), musicStoreRecommendedAlbumItem.getDeliveryRestrictionList()) && internalEqualityCheck(isIsMusicSubscription(), musicStoreRecommendedAlbumItem.isIsMusicSubscription());
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

    public String getCurrency() {
        return this.currency;
    }

    public List<String> getDeliveryRestrictionList() {
        return this.deliveryRestrictionList;
    }

    public Long getOriginalReleaseDate() {
        return this.originalReleaseDate;
    }

    public Double getPrice() {
        return this.price;
    }

    public Integer getTotalNumberOfTracks() {
        return this.totalNumberOfTracks;
    }

    public Integer getTotalReviewCount() {
        return this.totalReviewCount;
    }

    public int hashCode() {
        return internalHashCodeCompute(Integer.valueOf(classNameHashCode), getCurrency(), isIsPrime(), getTotalNumberOfTracks(), getAverageOverallRating(), isIsFreeOnDemandPlayable(), getAsin(), getArtistName(), getAlbumArtImageUrl(), getPrice(), getOriginalReleaseDate(), getAlbumName(), isIsFree(), getContentEncoding(), getTotalReviewCount(), getArtistAsin(), getDeliveryRestrictionList(), isIsMusicSubscription());
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

    public void setCurrency(String str) {
        this.currency = str;
    }

    public void setDeliveryRestrictionList(List<String> list) {
        this.deliveryRestrictionList = list;
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

    public void setOriginalReleaseDate(Long l) {
        this.originalReleaseDate = l;
    }

    public void setPrice(Double d) {
        this.price = d;
    }

    public void setTotalNumberOfTracks(Integer num) {
        this.totalNumberOfTracks = num;
    }

    public void setTotalReviewCount(Integer num) {
        this.totalReviewCount = num;
    }
}
