package com.amazon.music.storeservice.model;

import java.util.Arrays;

/* JADX INFO: loaded from: classes4.dex */
public class AlbumPurchaseOptionsData implements Comparable<AlbumPurchaseOptionsData> {
    private static final int classNameHashCode = internalHashCodeCompute("com.amazon.music.storeservice.model.AlbumPurchaseOptionsData");
    private String asin;
    private String currency;
    private String digitalAsin;
    private Boolean displayAlbumOnlyButton;
    private Boolean displayBuyButton;
    private Boolean displayPreOrderButton;
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
    public int compareTo(@Deprecated AlbumPurchaseOptionsData albumPurchaseOptionsData) {
        if (albumPurchaseOptionsData == null) {
            return -1;
        }
        if (albumPurchaseOptionsData == this) {
            return 0;
        }
        String displayStringBinding = getDisplayStringBinding();
        String displayStringBinding2 = albumPurchaseOptionsData.getDisplayStringBinding();
        if (displayStringBinding != displayStringBinding2) {
            if (displayStringBinding == null) {
                return -1;
            }
            if (displayStringBinding2 == null) {
                return 1;
            }
            int iCompareTo = displayStringBinding.compareTo(displayStringBinding2);
            if (iCompareTo != 0) {
                return iCompareTo;
            }
        }
        String displayPrice = getDisplayPrice();
        String displayPrice2 = albumPurchaseOptionsData.getDisplayPrice();
        if (displayPrice != displayPrice2) {
            if (displayPrice == null) {
                return -1;
            }
            if (displayPrice2 == null) {
                return 1;
            }
            int iCompareTo2 = displayPrice.compareTo(displayPrice2);
            if (iCompareTo2 != 0) {
                return iCompareTo2;
            }
        }
        String digitalAsin = getDigitalAsin();
        String digitalAsin2 = albumPurchaseOptionsData.getDigitalAsin();
        if (digitalAsin != digitalAsin2) {
            if (digitalAsin == null) {
                return -1;
            }
            if (digitalAsin2 == null) {
                return 1;
            }
            int iCompareTo3 = digitalAsin.compareTo(digitalAsin2);
            if (iCompareTo3 != 0) {
                return iCompareTo3;
            }
        }
        String currency = getCurrency();
        String currency2 = albumPurchaseOptionsData.getCurrency();
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
        Boolean boolIsDisplayAlbumOnlyButton = isDisplayAlbumOnlyButton();
        Boolean boolIsDisplayAlbumOnlyButton2 = albumPurchaseOptionsData.isDisplayAlbumOnlyButton();
        if (boolIsDisplayAlbumOnlyButton != boolIsDisplayAlbumOnlyButton2) {
            if (boolIsDisplayAlbumOnlyButton == null) {
                return -1;
            }
            if (boolIsDisplayAlbumOnlyButton2 == null) {
                return 1;
            }
            int iCompareTo5 = boolIsDisplayAlbumOnlyButton.compareTo(boolIsDisplayAlbumOnlyButton2);
            if (iCompareTo5 != 0) {
                return iCompareTo5;
            }
        }
        Boolean boolIsIsAlreadyPurchased = isIsAlreadyPurchased();
        Boolean boolIsIsAlreadyPurchased2 = albumPurchaseOptionsData.isIsAlreadyPurchased();
        if (boolIsIsAlreadyPurchased != boolIsIsAlreadyPurchased2) {
            if (boolIsIsAlreadyPurchased == null) {
                return -1;
            }
            if (boolIsIsAlreadyPurchased2 == null) {
                return 1;
            }
            int iCompareTo6 = boolIsIsAlreadyPurchased.compareTo(boolIsIsAlreadyPurchased2);
            if (iCompareTo6 != 0) {
                return iCompareTo6;
            }
        }
        Boolean boolIsDisplayBuyButton = isDisplayBuyButton();
        Boolean boolIsDisplayBuyButton2 = albumPurchaseOptionsData.isDisplayBuyButton();
        if (boolIsDisplayBuyButton != boolIsDisplayBuyButton2) {
            if (boolIsDisplayBuyButton == null) {
                return -1;
            }
            if (boolIsDisplayBuyButton2 == null) {
                return 1;
            }
            int iCompareTo7 = boolIsDisplayBuyButton.compareTo(boolIsDisplayBuyButton2);
            if (iCompareTo7 != 0) {
                return iCompareTo7;
            }
        }
        String asin = getAsin();
        String asin2 = albumPurchaseOptionsData.getAsin();
        if (asin != asin2) {
            if (asin == null) {
                return -1;
            }
            if (asin2 == null) {
                return 1;
            }
            int iCompareTo8 = asin.compareTo(asin2);
            if (iCompareTo8 != 0) {
                return iCompareTo8;
            }
        }
        Boolean boolIsDisplayPreOrderButton = isDisplayPreOrderButton();
        Boolean boolIsDisplayPreOrderButton2 = albumPurchaseOptionsData.isDisplayPreOrderButton();
        if (boolIsDisplayPreOrderButton != boolIsDisplayPreOrderButton2) {
            if (boolIsDisplayPreOrderButton == null) {
                return -1;
            }
            if (boolIsDisplayPreOrderButton2 == null) {
                return 1;
            }
            int iCompareTo9 = boolIsDisplayPreOrderButton.compareTo(boolIsDisplayPreOrderButton2);
            if (iCompareTo9 != 0) {
                return iCompareTo9;
            }
        }
        Double price = getPrice();
        Double price2 = albumPurchaseOptionsData.getPrice();
        if (price != price2) {
            if (price == null) {
                return -1;
            }
            if (price2 == null) {
                return 1;
            }
            int iCompareTo10 = price.compareTo(price2);
            if (iCompareTo10 != 0) {
                return iCompareTo10;
            }
        }
        return 0;
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof AlbumPurchaseOptionsData)) {
            return false;
        }
        AlbumPurchaseOptionsData albumPurchaseOptionsData = (AlbumPurchaseOptionsData) obj;
        return internalEqualityCheck(getDisplayStringBinding(), albumPurchaseOptionsData.getDisplayStringBinding()) && internalEqualityCheck(getDisplayPrice(), albumPurchaseOptionsData.getDisplayPrice()) && internalEqualityCheck(getDigitalAsin(), albumPurchaseOptionsData.getDigitalAsin()) && internalEqualityCheck(getCurrency(), albumPurchaseOptionsData.getCurrency()) && internalEqualityCheck(isDisplayAlbumOnlyButton(), albumPurchaseOptionsData.isDisplayAlbumOnlyButton()) && internalEqualityCheck(isIsAlreadyPurchased(), albumPurchaseOptionsData.isIsAlreadyPurchased()) && internalEqualityCheck(isDisplayBuyButton(), albumPurchaseOptionsData.isDisplayBuyButton()) && internalEqualityCheck(getAsin(), albumPurchaseOptionsData.getAsin()) && internalEqualityCheck(isDisplayPreOrderButton(), albumPurchaseOptionsData.isDisplayPreOrderButton()) && internalEqualityCheck(getPrice(), albumPurchaseOptionsData.getPrice());
    }

    public String getAsin() {
        return this.asin;
    }

    public String getCurrency() {
        return this.currency;
    }

    public String getDigitalAsin() {
        return this.digitalAsin;
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
        return internalHashCodeCompute(Integer.valueOf(classNameHashCode), getDisplayStringBinding(), getDisplayPrice(), getDigitalAsin(), getCurrency(), isDisplayAlbumOnlyButton(), isIsAlreadyPurchased(), isDisplayBuyButton(), getAsin(), isDisplayPreOrderButton(), getPrice());
    }

    public Boolean isDisplayAlbumOnlyButton() {
        return this.displayAlbumOnlyButton;
    }

    public Boolean isDisplayBuyButton() {
        return this.displayBuyButton;
    }

    public Boolean isDisplayPreOrderButton() {
        return this.displayPreOrderButton;
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

    public void setDigitalAsin(String str) {
        this.digitalAsin = str;
    }

    public void setDisplayAlbumOnlyButton(Boolean bool) {
        this.displayAlbumOnlyButton = bool;
    }

    public void setDisplayBuyButton(Boolean bool) {
        this.displayBuyButton = bool;
    }

    public void setDisplayPreOrderButton(Boolean bool) {
        this.displayPreOrderButton = bool;
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
