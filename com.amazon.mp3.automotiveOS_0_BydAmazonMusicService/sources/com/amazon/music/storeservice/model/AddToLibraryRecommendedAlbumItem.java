package com.amazon.music.storeservice.model;

import java.util.Arrays;
import java.util.List;

/* JADX INFO: loaded from: classes4.dex */
public class AddToLibraryRecommendedAlbumItem implements Comparable<AddToLibraryRecommendedAlbumItem> {
    private static final int classNameHashCode = internalHashCodeCompute("com.amazon.music.storeservice.model.AddToLibraryRecommendedAlbumItem");
    private String albumArtImageUrl;
    private String albumName;
    private String artistAsin;
    private String artistName;
    private String asin;
    private List<String> contentEncoding;
    private String currency;
    private Boolean isFree;
    private Boolean isFreeOnDemandPlayable;
    private Boolean isMusicSubscription;
    private Boolean isPrime;
    private Double price;

    private static boolean internalEqualityCheck(Object obj, Object obj2) {
        return obj == obj2 || (obj != null && obj.equals(obj2));
    }

    private static int internalHashCodeCompute(Object... objArr) {
        return Arrays.hashCode(objArr);
    }

    @Override // java.lang.Comparable
    @Deprecated
    public int compareTo(@Deprecated AddToLibraryRecommendedAlbumItem addToLibraryRecommendedAlbumItem) {
        if (addToLibraryRecommendedAlbumItem == null) {
            return -1;
        }
        if (addToLibraryRecommendedAlbumItem == this) {
            return 0;
        }
        String asin = getAsin();
        String asin2 = addToLibraryRecommendedAlbumItem.getAsin();
        if (asin != asin2) {
            if (asin == null) {
                return -1;
            }
            if (asin2 == null) {
                return 1;
            }
            int iCompareTo = asin.compareTo(asin2);
            if (iCompareTo != 0) {
                return iCompareTo;
            }
        }
        String artistName = getArtistName();
        String artistName2 = addToLibraryRecommendedAlbumItem.getArtistName();
        if (artistName != artistName2) {
            if (artistName == null) {
                return -1;
            }
            if (artistName2 == null) {
                return 1;
            }
            int iCompareTo2 = artistName.compareTo(artistName2);
            if (iCompareTo2 != 0) {
                return iCompareTo2;
            }
        }
        Boolean boolIsIsMusicSubscription = isIsMusicSubscription();
        Boolean boolIsIsMusicSubscription2 = addToLibraryRecommendedAlbumItem.isIsMusicSubscription();
        if (boolIsIsMusicSubscription != boolIsIsMusicSubscription2) {
            if (boolIsIsMusicSubscription == null) {
                return -1;
            }
            if (boolIsIsMusicSubscription2 == null) {
                return 1;
            }
            int iCompareTo3 = boolIsIsMusicSubscription.compareTo(boolIsIsMusicSubscription2);
            if (iCompareTo3 != 0) {
                return iCompareTo3;
            }
        }
        String artistAsin = getArtistAsin();
        String artistAsin2 = addToLibraryRecommendedAlbumItem.getArtistAsin();
        if (artistAsin != artistAsin2) {
            if (artistAsin == null) {
                return -1;
            }
            if (artistAsin2 == null) {
                return 1;
            }
            int iCompareTo4 = artistAsin.compareTo(artistAsin2);
            if (iCompareTo4 != 0) {
                return iCompareTo4;
            }
        }
        Boolean boolIsIsPrime = isIsPrime();
        Boolean boolIsIsPrime2 = addToLibraryRecommendedAlbumItem.isIsPrime();
        if (boolIsIsPrime != boolIsIsPrime2) {
            if (boolIsIsPrime == null) {
                return -1;
            }
            if (boolIsIsPrime2 == null) {
                return 1;
            }
            int iCompareTo5 = boolIsIsPrime.compareTo(boolIsIsPrime2);
            if (iCompareTo5 != 0) {
                return iCompareTo5;
            }
        }
        Double price = getPrice();
        Double price2 = addToLibraryRecommendedAlbumItem.getPrice();
        if (price != price2) {
            if (price == null) {
                return -1;
            }
            if (price2 == null) {
                return 1;
            }
            int iCompareTo6 = price.compareTo(price2);
            if (iCompareTo6 != 0) {
                return iCompareTo6;
            }
        }
        Boolean boolIsIsFreeOnDemandPlayable = isIsFreeOnDemandPlayable();
        Boolean boolIsIsFreeOnDemandPlayable2 = addToLibraryRecommendedAlbumItem.isIsFreeOnDemandPlayable();
        if (boolIsIsFreeOnDemandPlayable != boolIsIsFreeOnDemandPlayable2) {
            if (boolIsIsFreeOnDemandPlayable == null) {
                return -1;
            }
            if (boolIsIsFreeOnDemandPlayable2 == null) {
                return 1;
            }
            int iCompareTo7 = boolIsIsFreeOnDemandPlayable.compareTo(boolIsIsFreeOnDemandPlayable2);
            if (iCompareTo7 != 0) {
                return iCompareTo7;
            }
        }
        String albumArtImageUrl = getAlbumArtImageUrl();
        String albumArtImageUrl2 = addToLibraryRecommendedAlbumItem.getAlbumArtImageUrl();
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
        List<String> contentEncoding = getContentEncoding();
        List<String> contentEncoding2 = addToLibraryRecommendedAlbumItem.getContentEncoding();
        if (contentEncoding != contentEncoding2) {
            if (contentEncoding == null) {
                return -1;
            }
            if (contentEncoding2 == null) {
                return 1;
            }
            if (contentEncoding instanceof Comparable) {
                int iCompareTo9 = ((Comparable) contentEncoding).compareTo(contentEncoding2);
                if (iCompareTo9 != 0) {
                    return iCompareTo9;
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
        Boolean boolIsIsFree = isIsFree();
        Boolean boolIsIsFree2 = addToLibraryRecommendedAlbumItem.isIsFree();
        if (boolIsIsFree != boolIsIsFree2) {
            if (boolIsIsFree == null) {
                return -1;
            }
            if (boolIsIsFree2 == null) {
                return 1;
            }
            int iCompareTo10 = boolIsIsFree.compareTo(boolIsIsFree2);
            if (iCompareTo10 != 0) {
                return iCompareTo10;
            }
        }
        String albumName = getAlbumName();
        String albumName2 = addToLibraryRecommendedAlbumItem.getAlbumName();
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
        String currency = getCurrency();
        String currency2 = addToLibraryRecommendedAlbumItem.getCurrency();
        if (currency != currency2) {
            if (currency == null) {
                return -1;
            }
            if (currency2 == null) {
                return 1;
            }
            int iCompareTo12 = currency.compareTo(currency2);
            if (iCompareTo12 != 0) {
                return iCompareTo12;
            }
        }
        return 0;
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof AddToLibraryRecommendedAlbumItem)) {
            return false;
        }
        AddToLibraryRecommendedAlbumItem addToLibraryRecommendedAlbumItem = (AddToLibraryRecommendedAlbumItem) obj;
        return internalEqualityCheck(getAsin(), addToLibraryRecommendedAlbumItem.getAsin()) && internalEqualityCheck(getArtistName(), addToLibraryRecommendedAlbumItem.getArtistName()) && internalEqualityCheck(isIsMusicSubscription(), addToLibraryRecommendedAlbumItem.isIsMusicSubscription()) && internalEqualityCheck(getArtistAsin(), addToLibraryRecommendedAlbumItem.getArtistAsin()) && internalEqualityCheck(isIsPrime(), addToLibraryRecommendedAlbumItem.isIsPrime()) && internalEqualityCheck(getPrice(), addToLibraryRecommendedAlbumItem.getPrice()) && internalEqualityCheck(isIsFreeOnDemandPlayable(), addToLibraryRecommendedAlbumItem.isIsFreeOnDemandPlayable()) && internalEqualityCheck(getAlbumArtImageUrl(), addToLibraryRecommendedAlbumItem.getAlbumArtImageUrl()) && internalEqualityCheck(getContentEncoding(), addToLibraryRecommendedAlbumItem.getContentEncoding()) && internalEqualityCheck(isIsFree(), addToLibraryRecommendedAlbumItem.isIsFree()) && internalEqualityCheck(getAlbumName(), addToLibraryRecommendedAlbumItem.getAlbumName()) && internalEqualityCheck(getCurrency(), addToLibraryRecommendedAlbumItem.getCurrency());
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

    public List<String> getContentEncoding() {
        return this.contentEncoding;
    }

    public String getCurrency() {
        return this.currency;
    }

    public Double getPrice() {
        return this.price;
    }

    public int hashCode() {
        return internalHashCodeCompute(Integer.valueOf(classNameHashCode), getAsin(), getArtistName(), isIsMusicSubscription(), getArtistAsin(), isIsPrime(), getPrice(), isIsFreeOnDemandPlayable(), getAlbumArtImageUrl(), getContentEncoding(), isIsFree(), getAlbumName(), getCurrency());
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

    public void setContentEncoding(List<String> list) {
        this.contentEncoding = list;
    }

    public void setCurrency(String str) {
        this.currency = str;
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
}
