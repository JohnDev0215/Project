package com.amazon.music.storeservice.model;

import java.util.Arrays;

/* JADX INFO: loaded from: classes4.dex */
public class TrackPurchaseOptionsData implements Comparable<TrackPurchaseOptionsData> {
    private static final int classNameHashCode = internalHashCodeCompute("com.amazon.music.storeservice.model.TrackPurchaseOptionsData");
    private String asin;
    private String currency;
    private Boolean displayAlbumOnlyButton;
    private Boolean displayBuyButton;
    private String displayPrice;
    private String displayStringBinding;
    private Boolean isAlreadyPurchased;
    private Double price;

    private static boolean internalEqualityCheck(Object obj, Object obj2) {
        return obj == obj2 || (obj != null && obj.equals(obj2));
    }

    private static int internalHashCodeCompute(Object... objArr) {
        return Arrays.hashCode(objArr);
    }

    @Override // java.lang.Comparable
    @Deprecated
    public int compareTo(@Deprecated TrackPurchaseOptionsData trackPurchaseOptionsData) {
        if (trackPurchaseOptionsData == null) {
            return -1;
        }
        if (trackPurchaseOptionsData == this) {
            return 0;
        }
        Boolean boolIsDisplayAlbumOnlyButton = isDisplayAlbumOnlyButton();
        Boolean boolIsDisplayAlbumOnlyButton2 = trackPurchaseOptionsData.isDisplayAlbumOnlyButton();
        if (boolIsDisplayAlbumOnlyButton != boolIsDisplayAlbumOnlyButton2) {
            if (boolIsDisplayAlbumOnlyButton == null) {
                return -1;
            }
            if (boolIsDisplayAlbumOnlyButton2 == null) {
                return 1;
            }
            int iCompareTo = boolIsDisplayAlbumOnlyButton.compareTo(boolIsDisplayAlbumOnlyButton2);
            if (iCompareTo != 0) {
                return iCompareTo;
            }
        }
        Boolean boolIsIsAlreadyPurchased = isIsAlreadyPurchased();
        Boolean boolIsIsAlreadyPurchased2 = trackPurchaseOptionsData.isIsAlreadyPurchased();
        if (boolIsIsAlreadyPurchased != boolIsIsAlreadyPurchased2) {
            if (boolIsIsAlreadyPurchased == null) {
                return -1;
            }
            if (boolIsIsAlreadyPurchased2 == null) {
                return 1;
            }
            int iCompareTo2 = boolIsIsAlreadyPurchased.compareTo(boolIsIsAlreadyPurchased2);
            if (iCompareTo2 != 0) {
                return iCompareTo2;
            }
        }
        Boolean boolIsDisplayBuyButton = isDisplayBuyButton();
        Boolean boolIsDisplayBuyButton2 = trackPurchaseOptionsData.isDisplayBuyButton();
        if (boolIsDisplayBuyButton != boolIsDisplayBuyButton2) {
            if (boolIsDisplayBuyButton == null) {
                return -1;
            }
            if (boolIsDisplayBuyButton2 == null) {
                return 1;
            }
            int iCompareTo3 = boolIsDisplayBuyButton.compareTo(boolIsDisplayBuyButton2);
            if (iCompareTo3 != 0) {
                return iCompareTo3;
            }
        }
        String currency = getCurrency();
        String currency2 = trackPurchaseOptionsData.getCurrency();
        if (currency != currency2) {
            if (currency == null) {
                return -1;
            }
            if (currency2 == null) {
                return 1;
            }
            int iCompareTo4 = currency.compareTo(currency2);
            if (iCompareTo4 != 0) {
                return iCompareTo4;
            }
        }
        String displayStringBinding = getDisplayStringBinding();
        String displayStringBinding2 = trackPurchaseOptionsData.getDisplayStringBinding();
        if (displayStringBinding != displayStringBinding2) {
            if (displayStringBinding == null) {
                return -1;
            }
            if (displayStringBinding2 == null) {
                return 1;
            }
            int iCompareTo5 = displayStringBinding.compareTo(displayStringBinding2);
            if (iCompareTo5 != 0) {
                return iCompareTo5;
            }
        }
        String displayPrice = getDisplayPrice();
        String displayPrice2 = trackPurchaseOptionsData.getDisplayPrice();
        if (displayPrice != displayPrice2) {
            if (displayPrice == null) {
                return -1;
            }
            if (displayPrice2 == null) {
                return 1;
            }
            int iCompareTo6 = displayPrice.compareTo(displayPrice2);
            if (iCompareTo6 != 0) {
                return iCompareTo6;
            }
        }
        String asin = getAsin();
        String asin2 = trackPurchaseOptionsData.getAsin();
        if (asin != asin2) {
            if (asin == null) {
                return -1;
            }
            if (asin2 == null) {
                return 1;
            }
            int iCompareTo7 = asin.compareTo(asin2);
            if (iCompareTo7 != 0) {
                return iCompareTo7;
            }
        }
        Double price = getPrice();
        Double price2 = trackPurchaseOptionsData.getPrice();
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
        return 0;
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof TrackPurchaseOptionsData)) {
            return false;
        }
        TrackPurchaseOptionsData trackPurchaseOptionsData = (TrackPurchaseOptionsData) obj;
        return internalEqualityCheck(isDisplayAlbumOnlyButton(), trackPurchaseOptionsData.isDisplayAlbumOnlyButton()) && internalEqualityCheck(isIsAlreadyPurchased(), trackPurchaseOptionsData.isIsAlreadyPurchased()) && internalEqualityCheck(isDisplayBuyButton(), trackPurchaseOptionsData.isDisplayBuyButton()) && internalEqualityCheck(getCurrency(), trackPurchaseOptionsData.getCurrency()) && internalEqualityCheck(getDisplayStringBinding(), trackPurchaseOptionsData.getDisplayStringBinding()) && internalEqualityCheck(getDisplayPrice(), trackPurchaseOptionsData.getDisplayPrice()) && internalEqualityCheck(getAsin(), trackPurchaseOptionsData.getAsin()) && internalEqualityCheck(getPrice(), trackPurchaseOptionsData.getPrice());
    }

    public String getAsin() {
        return this.asin;
    }

    public String getCurrency() {
        return this.currency;
    }

    public String getDisplayPrice() {
        return this.displayPrice;
    }

    public String getDisplayStringBinding() {
        return this.displayStringBinding;
    }

    public Double getPrice() {
        return this.price;
    }

    public int hashCode() {
        return internalHashCodeCompute(Integer.valueOf(classNameHashCode), isDisplayAlbumOnlyButton(), isIsAlreadyPurchased(), isDisplayBuyButton(), getCurrency(), getDisplayStringBinding(), getDisplayPrice(), getAsin(), getPrice());
    }

    public Boolean isDisplayAlbumOnlyButton() {
        return this.displayAlbumOnlyButton;
    }

    public Boolean isDisplayBuyButton() {
        return this.displayBuyButton;
    }

    public Boolean isIsAlreadyPurchased() {
        return this.isAlreadyPurchased;
    }

    public void setAsin(String str) {
        this.asin = str;
    }

    public void setCurrency(String str) {
        this.currency = str;
    }

    public void setDisplayAlbumOnlyButton(Boolean bool) {
        this.displayAlbumOnlyButton = bool;
    }

    public void setDisplayBuyButton(Boolean bool) {
        this.displayBuyButton = bool;
    }

    public void setDisplayPrice(String str) {
        this.displayPrice = str;
    }

    public void setDisplayStringBinding(String str) {
        this.displayStringBinding = str;
    }

    public void setIsAlreadyPurchased(Boolean bool) {
        this.isAlreadyPurchased = bool;
    }

    public void setPrice(Double d) {
        this.price = d;
    }
}
