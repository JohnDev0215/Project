package com.amazon.music.storeservice.model;

import java.util.Arrays;
import java.util.List;

/* JADX INFO: loaded from: classes4.dex */
public class MusicStoreRecommendedTrackItem implements Comparable<MusicStoreRecommendedTrackItem> {
    private static final int classNameHashCode = internalHashCodeCompute("com.amazon.music.storeservice.model.MusicStoreRecommendedTrackItem");
    private String albumArtImageUrl;
    private String albumAsin;
    private Double albumAverageOverallRating;
    private String albumName;
    private Long albumOriginalReleaseDate;
    private String artistAsin;
    private String artistName;
    private String asin;
    private List<String> contentEncoding;
    private String currency;
    private List<String> deliveryRestrictionList;
    private Integer durationSeconds;
    private Boolean isFree;
    private Boolean isFreeOnDemandPlayable;
    private Boolean isMusicSubscription;
    private Boolean isPrime;
    private Double price;
    private String title;

    private static boolean internalEqualityCheck(Object obj, Object obj2) {
        return obj == obj2 || (obj != null && obj.equals(obj2));
    }

    private static int internalHashCodeCompute(Object... objArr) {
        return Arrays.hashCode(objArr);
    }

    @Override // java.lang.Comparable
    @Deprecated
    public int compareTo(@Deprecated MusicStoreRecommendedTrackItem musicStoreRecommendedTrackItem) {
        if (musicStoreRecommendedTrackItem == null) {
            return -1;
        }
        if (musicStoreRecommendedTrackItem == this) {
            return 0;
        }
        List<String> contentEncoding = getContentEncoding();
        List<String> contentEncoding2 = musicStoreRecommendedTrackItem.getContentEncoding();
        if (contentEncoding != contentEncoding2) {
            if (contentEncoding == null) {
                return -1;
            }
            if (contentEncoding2 == null) {
                return 1;
            }
            if (contentEncoding instanceof Comparable) {
                int iCompareTo = ((Comparable) contentEncoding).compareTo(contentEncoding2);
                if (iCompareTo != 0) {
                    return iCompareTo;
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
        String title = getTitle();
        String title2 = musicStoreRecommendedTrackItem.getTitle();
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
        Long albumOriginalReleaseDate = getAlbumOriginalReleaseDate();
        Long albumOriginalReleaseDate2 = musicStoreRecommendedTrackItem.getAlbumOriginalReleaseDate();
        if (albumOriginalReleaseDate != albumOriginalReleaseDate2) {
            if (albumOriginalReleaseDate == null) {
                return -1;
            }
            if (albumOriginalReleaseDate2 == null) {
                return 1;
            }
            int iCompareTo3 = albumOriginalReleaseDate.compareTo(albumOriginalReleaseDate2);
            if (iCompareTo3 != 0) {
                return iCompareTo3;
            }
        }
        String albumName = getAlbumName();
        String albumName2 = musicStoreRecommendedTrackItem.getAlbumName();
        if (albumName != albumName2) {
            if (albumName == null) {
                return -1;
            }
            if (albumName2 == null) {
                return 1;
            }
            int iCompareTo4 = albumName.compareTo(albumName2);
            if (iCompareTo4 != 0) {
                return iCompareTo4;
            }
        }
        String albumArtImageUrl = getAlbumArtImageUrl();
        String albumArtImageUrl2 = musicStoreRecommendedTrackItem.getAlbumArtImageUrl();
        if (albumArtImageUrl != albumArtImageUrl2) {
            if (albumArtImageUrl == null) {
                return -1;
            }
            if (albumArtImageUrl2 == null) {
                return 1;
            }
            int iCompareTo5 = albumArtImageUrl.compareTo(albumArtImageUrl2);
            if (iCompareTo5 != 0) {
                return iCompareTo5;
            }
        }
        String asin = getAsin();
        String asin2 = musicStoreRecommendedTrackItem.getAsin();
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
        String artistAsin = getArtistAsin();
        String artistAsin2 = musicStoreRecommendedTrackItem.getArtistAsin();
        if (artistAsin != artistAsin2) {
            if (artistAsin == null) {
                return -1;
            }
            if (artistAsin2 == null) {
                return 1;
            }
            int iCompareTo7 = artistAsin.compareTo(artistAsin2);
            if (iCompareTo7 != 0) {
                return iCompareTo7;
            }
        }
        Double price = getPrice();
        Double price2 = musicStoreRecommendedTrackItem.getPrice();
        if (price != price2) {
            if (price == null) {
                return -1;
            }
            if (price2 == null) {
                return 1;
            }
            int iCompareTo8 = price.compareTo(price2);
            if (iCompareTo8 != 0) {
                return iCompareTo8;
            }
        }
        Integer durationSeconds = getDurationSeconds();
        Integer durationSeconds2 = musicStoreRecommendedTrackItem.getDurationSeconds();
        if (durationSeconds != durationSeconds2) {
            if (durationSeconds == null) {
                return -1;
            }
            if (durationSeconds2 == null) {
                return 1;
            }
            int iCompareTo9 = durationSeconds.compareTo(durationSeconds2);
            if (iCompareTo9 != 0) {
                return iCompareTo9;
            }
        }
        String albumAsin = getAlbumAsin();
        String albumAsin2 = musicStoreRecommendedTrackItem.getAlbumAsin();
        if (albumAsin != albumAsin2) {
            if (albumAsin == null) {
                return -1;
            }
            if (albumAsin2 == null) {
                return 1;
            }
            int iCompareTo10 = albumAsin.compareTo(albumAsin2);
            if (iCompareTo10 != 0) {
                return iCompareTo10;
            }
        }
        String currency = getCurrency();
        String currency2 = musicStoreRecommendedTrackItem.getCurrency();
        if (currency != currency2) {
            if (currency == null) {
                return -1;
            }
            if (currency2 == null) {
                return 1;
            }
            int iCompareTo11 = currency.compareTo(currency2);
            if (iCompareTo11 != 0) {
                return iCompareTo11;
            }
        }
        Boolean boolIsIsPrime = isIsPrime();
        Boolean boolIsIsPrime2 = musicStoreRecommendedTrackItem.isIsPrime();
        if (boolIsIsPrime != boolIsIsPrime2) {
            if (boolIsIsPrime == null) {
                return -1;
            }
            if (boolIsIsPrime2 == null) {
                return 1;
            }
            int iCompareTo12 = boolIsIsPrime.compareTo(boolIsIsPrime2);
            if (iCompareTo12 != 0) {
                return iCompareTo12;
            }
        }
        Boolean boolIsIsMusicSubscription = isIsMusicSubscription();
        Boolean boolIsIsMusicSubscription2 = musicStoreRecommendedTrackItem.isIsMusicSubscription();
        if (boolIsIsMusicSubscription != boolIsIsMusicSubscription2) {
            if (boolIsIsMusicSubscription == null) {
                return -1;
            }
            if (boolIsIsMusicSubscription2 == null) {
                return 1;
            }
            int iCompareTo13 = boolIsIsMusicSubscription.compareTo(boolIsIsMusicSubscription2);
            if (iCompareTo13 != 0) {
                return iCompareTo13;
            }
        }
        Double albumAverageOverallRating = getAlbumAverageOverallRating();
        Double albumAverageOverallRating2 = musicStoreRecommendedTrackItem.getAlbumAverageOverallRating();
        if (albumAverageOverallRating != albumAverageOverallRating2) {
            if (albumAverageOverallRating == null) {
                return -1;
            }
            if (albumAverageOverallRating2 == null) {
                return 1;
            }
            int iCompareTo14 = albumAverageOverallRating.compareTo(albumAverageOverallRating2);
            if (iCompareTo14 != 0) {
                return iCompareTo14;
            }
        }
        List<String> deliveryRestrictionList = getDeliveryRestrictionList();
        List<String> deliveryRestrictionList2 = musicStoreRecommendedTrackItem.getDeliveryRestrictionList();
        if (deliveryRestrictionList != deliveryRestrictionList2) {
            if (deliveryRestrictionList == null) {
                return -1;
            }
            if (deliveryRestrictionList2 == null) {
                return 1;
            }
            if (deliveryRestrictionList instanceof Comparable) {
                int iCompareTo15 = ((Comparable) deliveryRestrictionList).compareTo(deliveryRestrictionList2);
                if (iCompareTo15 != 0) {
                    return iCompareTo15;
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
        Boolean boolIsIsFreeOnDemandPlayable = isIsFreeOnDemandPlayable();
        Boolean boolIsIsFreeOnDemandPlayable2 = musicStoreRecommendedTrackItem.isIsFreeOnDemandPlayable();
        if (boolIsIsFreeOnDemandPlayable != boolIsIsFreeOnDemandPlayable2) {
            if (boolIsIsFreeOnDemandPlayable == null) {
                return -1;
            }
            if (boolIsIsFreeOnDemandPlayable2 == null) {
                return 1;
            }
            int iCompareTo16 = boolIsIsFreeOnDemandPlayable.compareTo(boolIsIsFreeOnDemandPlayable2);
            if (iCompareTo16 != 0) {
                return iCompareTo16;
            }
        }
        String artistName = getArtistName();
        String artistName2 = musicStoreRecommendedTrackItem.getArtistName();
        if (artistName != artistName2) {
            if (artistName == null) {
                return -1;
            }
            if (artistName2 == null) {
                return 1;
            }
            int iCompareTo17 = artistName.compareTo(artistName2);
            if (iCompareTo17 != 0) {
                return iCompareTo17;
            }
        }
        Boolean boolIsIsFree = isIsFree();
        Boolean boolIsIsFree2 = musicStoreRecommendedTrackItem.isIsFree();
        if (boolIsIsFree != boolIsIsFree2) {
            if (boolIsIsFree == null) {
                return -1;
            }
            if (boolIsIsFree2 == null) {
                return 1;
            }
            int iCompareTo18 = boolIsIsFree.compareTo(boolIsIsFree2);
            if (iCompareTo18 != 0) {
                return iCompareTo18;
            }
        }
        return 0;
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof MusicStoreRecommendedTrackItem)) {
            return false;
        }
        MusicStoreRecommendedTrackItem musicStoreRecommendedTrackItem = (MusicStoreRecommendedTrackItem) obj;
        return internalEqualityCheck(getContentEncoding(), musicStoreRecommendedTrackItem.getContentEncoding()) && internalEqualityCheck(getTitle(), musicStoreRecommendedTrackItem.getTitle()) && internalEqualityCheck(getAlbumOriginalReleaseDate(), musicStoreRecommendedTrackItem.getAlbumOriginalReleaseDate()) && internalEqualityCheck(getAlbumName(), musicStoreRecommendedTrackItem.getAlbumName()) && internalEqualityCheck(getAlbumArtImageUrl(), musicStoreRecommendedTrackItem.getAlbumArtImageUrl()) && internalEqualityCheck(getAsin(), musicStoreRecommendedTrackItem.getAsin()) && internalEqualityCheck(getArtistAsin(), musicStoreRecommendedTrackItem.getArtistAsin()) && internalEqualityCheck(getPrice(), musicStoreRecommendedTrackItem.getPrice()) && internalEqualityCheck(getDurationSeconds(), musicStoreRecommendedTrackItem.getDurationSeconds()) && internalEqualityCheck(getAlbumAsin(), musicStoreRecommendedTrackItem.getAlbumAsin()) && internalEqualityCheck(getCurrency(), musicStoreRecommendedTrackItem.getCurrency()) && internalEqualityCheck(isIsPrime(), musicStoreRecommendedTrackItem.isIsPrime()) && internalEqualityCheck(isIsMusicSubscription(), musicStoreRecommendedTrackItem.isIsMusicSubscription()) && internalEqualityCheck(getAlbumAverageOverallRating(), musicStoreRecommendedTrackItem.getAlbumAverageOverallRating()) && internalEqualityCheck(getDeliveryRestrictionList(), musicStoreRecommendedTrackItem.getDeliveryRestrictionList()) && internalEqualityCheck(isIsFreeOnDemandPlayable(), musicStoreRecommendedTrackItem.isIsFreeOnDemandPlayable()) && internalEqualityCheck(getArtistName(), musicStoreRecommendedTrackItem.getArtistName()) && internalEqualityCheck(isIsFree(), musicStoreRecommendedTrackItem.isIsFree());
    }

    public String getAlbumArtImageUrl() {
        return this.albumArtImageUrl;
    }

    public String getAlbumAsin() {
        return this.albumAsin;
    }

    public Double getAlbumAverageOverallRating() {
        return this.albumAverageOverallRating;
    }

    public String getAlbumName() {
        return this.albumName;
    }

    public Long getAlbumOriginalReleaseDate() {
        return this.albumOriginalReleaseDate;
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

    public List<String> getContentEncoding() {
        return this.contentEncoding;
    }

    public String getCurrency() {
        return this.currency;
    }

    public List<String> getDeliveryRestrictionList() {
        return this.deliveryRestrictionList;
    }

    public Integer getDurationSeconds() {
        return this.durationSeconds;
    }

    public Double getPrice() {
        return this.price;
    }

    public String getTitle() {
        return this.title;
    }

    public int hashCode() {
        return internalHashCodeCompute(Integer.valueOf(classNameHashCode), getContentEncoding(), getTitle(), getAlbumOriginalReleaseDate(), getAlbumName(), getAlbumArtImageUrl(), getAsin(), getArtistAsin(), getPrice(), getDurationSeconds(), getAlbumAsin(), getCurrency(), isIsPrime(), isIsMusicSubscription(), getAlbumAverageOverallRating(), getDeliveryRestrictionList(), isIsFreeOnDemandPlayable(), getArtistName(), isIsFree());
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

    public void setAlbumAsin(String str) {
        this.albumAsin = str;
    }

    public void setAlbumAverageOverallRating(Double d) {
        this.albumAverageOverallRating = d;
    }

    public void setAlbumName(String str) {
        this.albumName = str;
    }

    public void setAlbumOriginalReleaseDate(Long l) {
        this.albumOriginalReleaseDate = l;
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

    public void setContentEncoding(List<String> list) {
        this.contentEncoding = list;
    }

    public void setCurrency(String str) {
        this.currency = str;
    }

    public void setDeliveryRestrictionList(List<String> list) {
        this.deliveryRestrictionList = list;
    }

    public void setDurationSeconds(Integer num) {
        this.durationSeconds = num;
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

    public void setPrice(Double d) {
        this.price = d;
    }

    public void setTitle(String str) {
        this.title = str;
    }
}
