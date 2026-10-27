package com.amazon.music.storeservice.model;

import java.util.Arrays;
import java.util.List;

/* JADX INFO: loaded from: classes4.dex */
public class Purchase implements Comparable<Purchase> {
    private static final int classNameHashCode = internalHashCodeCompute("com.amazon.music.storeservice.model.Purchase");
    private String currency;
    private List<String> deliveryRestrictions;
    private Boolean owned;
    private String price;

    private static boolean internalEqualityCheck(Object obj, Object obj2) {
        return obj == obj2 || (obj != null && obj.equals(obj2));
    }

    private static int internalHashCodeCompute(Object... objArr) {
        return Arrays.hashCode(objArr);
    }

    @Override // java.lang.Comparable
    @Deprecated
    public int compareTo(@Deprecated Purchase purchase) {
        if (purchase == null) {
            return -1;
        }
        if (purchase == this) {
            return 0;
        }
        String currency = getCurrency();
        String currency2 = purchase.getCurrency();
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
        List<String> deliveryRestrictions = getDeliveryRestrictions();
        List<String> deliveryRestrictions2 = purchase.getDeliveryRestrictions();
        if (deliveryRestrictions != deliveryRestrictions2) {
            if (deliveryRestrictions == null) {
                return -1;
            }
            if (deliveryRestrictions2 == null) {
                return 1;
            }
            if (deliveryRestrictions instanceof Comparable) {
                int iCompareTo2 = ((Comparable) deliveryRestrictions).compareTo(deliveryRestrictions2);
                if (iCompareTo2 != 0) {
                    return iCompareTo2;
                }
            } else if (!deliveryRestrictions.equals(deliveryRestrictions2)) {
                int iHashCode = deliveryRestrictions.hashCode();
                int iHashCode2 = deliveryRestrictions2.hashCode();
                if (iHashCode < iHashCode2) {
                    return -1;
                }
                if (iHashCode > iHashCode2) {
                    return 1;
                }
            }
        }
        String price = getPrice();
        String price2 = purchase.getPrice();
        if (price != price2) {
            if (price == null) {
                return -1;
            }
            if (price2 == null) {
                return 1;
            }
            int iCompareTo3 = price.compareTo(price2);
            if (iCompareTo3 != 0) {
                return iCompareTo3;
            }
        }
        Boolean boolIsOwned = isOwned();
        Boolean boolIsOwned2 = purchase.isOwned();
        if (boolIsOwned != boolIsOwned2) {
            if (boolIsOwned == null) {
                return -1;
            }
            if (boolIsOwned2 == null) {
                return 1;
            }
            int iCompareTo4 = boolIsOwned.compareTo(boolIsOwned2);
            if (iCompareTo4 != 0) {
                return iCompareTo4;
            }
        }
        return 0;
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof Purchase)) {
            return false;
        }
        Purchase purchase = (Purchase) obj;
        return internalEqualityCheck(getCurrency(), purchase.getCurrency()) && internalEqualityCheck(getDeliveryRestrictions(), purchase.getDeliveryRestrictions()) && internalEqualityCheck(getPrice(), purchase.getPrice()) && internalEqualityCheck(isOwned(), purchase.isOwned());
    }

    public String getCurrency() {
        return this.currency;
    }

    public List<String> getDeliveryRestrictions() {
        return this.deliveryRestrictions;
    }

    public String getPrice() {
        return this.price;
    }

    public int hashCode() {
        return internalHashCodeCompute(Integer.valueOf(classNameHashCode), getCurrency(), getDeliveryRestrictions(), getPrice(), isOwned());
    }

    public Boolean isOwned() {
        return this.owned;
    }

    public void setCurrency(String str) {
        this.currency = str;
    }

    public void setDeliveryRestrictions(List<String> list) {
        this.deliveryRestrictions = list;
    }

    public void setOwned(Boolean bool) {
        this.owned = bool;
    }

    public void setPrice(String str) {
        this.price = str;
    }
}
