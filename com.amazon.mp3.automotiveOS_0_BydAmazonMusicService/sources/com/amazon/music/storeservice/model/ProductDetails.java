package com.amazon.music.storeservice.model;

import java.util.Arrays;

/* JADX INFO: loaded from: classes4.dex */
public class ProductDetails implements Comparable<ProductDetails> {
    private static final int classNameHashCode = internalHashCodeCompute("com.amazon.music.storeservice.model.ProductDetails");
    private String copyright;
    private String label;
    private String merchantId;
    private String merchantName;
    private String primaryGenre;
    private String primaryGenreName;
    private String sellerOfRecordId;

    private static boolean internalEqualityCheck(Object obj, Object obj2) {
        return obj == obj2 || (obj != null && obj.equals(obj2));
    }

    private static int internalHashCodeCompute(Object... objArr) {
        return Arrays.hashCode(objArr);
    }

    @Override // java.lang.Comparable
    @Deprecated
    public int compareTo(@Deprecated ProductDetails productDetails) {
        if (productDetails == null) {
            return -1;
        }
        if (productDetails == this) {
            return 0;
        }
        String merchantName = getMerchantName();
        String merchantName2 = productDetails.getMerchantName();
        if (merchantName != merchantName2) {
            if (merchantName == null) {
                return -1;
            }
            if (merchantName2 == null) {
                return 1;
            }
            int iCompareTo = merchantName.compareTo(merchantName2);
            if (iCompareTo != 0) {
                return iCompareTo;
            }
        }
        String label = getLabel();
        String label2 = productDetails.getLabel();
        if (label != label2) {
            if (label == null) {
                return -1;
            }
            if (label2 == null) {
                return 1;
            }
            int iCompareTo2 = label.compareTo(label2);
            if (iCompareTo2 != 0) {
                return iCompareTo2;
            }
        }
        String sellerOfRecordId = getSellerOfRecordId();
        String sellerOfRecordId2 = productDetails.getSellerOfRecordId();
        if (sellerOfRecordId != sellerOfRecordId2) {
            if (sellerOfRecordId == null) {
                return -1;
            }
            if (sellerOfRecordId2 == null) {
                return 1;
            }
            int iCompareTo3 = sellerOfRecordId.compareTo(sellerOfRecordId2);
            if (iCompareTo3 != 0) {
                return iCompareTo3;
            }
        }
        String merchantId = getMerchantId();
        String merchantId2 = productDetails.getMerchantId();
        if (merchantId != merchantId2) {
            if (merchantId == null) {
                return -1;
            }
            if (merchantId2 == null) {
                return 1;
            }
            int iCompareTo4 = merchantId.compareTo(merchantId2);
            if (iCompareTo4 != 0) {
                return iCompareTo4;
            }
        }
        String primaryGenreName = getPrimaryGenreName();
        String primaryGenreName2 = productDetails.getPrimaryGenreName();
        if (primaryGenreName != primaryGenreName2) {
            if (primaryGenreName == null) {
                return -1;
            }
            if (primaryGenreName2 == null) {
                return 1;
            }
            int iCompareTo5 = primaryGenreName.compareTo(primaryGenreName2);
            if (iCompareTo5 != 0) {
                return iCompareTo5;
            }
        }
        String copyright = getCopyright();
        String copyright2 = productDetails.getCopyright();
        if (copyright != copyright2) {
            if (copyright == null) {
                return -1;
            }
            if (copyright2 == null) {
                return 1;
            }
            int iCompareTo6 = copyright.compareTo(copyright2);
            if (iCompareTo6 != 0) {
                return iCompareTo6;
            }
        }
        String primaryGenre = getPrimaryGenre();
        String primaryGenre2 = productDetails.getPrimaryGenre();
        if (primaryGenre != primaryGenre2) {
            if (primaryGenre == null) {
                return -1;
            }
            if (primaryGenre2 == null) {
                return 1;
            }
            int iCompareTo7 = primaryGenre.compareTo(primaryGenre2);
            if (iCompareTo7 != 0) {
                return iCompareTo7;
            }
        }
        return 0;
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof ProductDetails)) {
            return false;
        }
        ProductDetails productDetails = (ProductDetails) obj;
        return internalEqualityCheck(getMerchantName(), productDetails.getMerchantName()) && internalEqualityCheck(getLabel(), productDetails.getLabel()) && internalEqualityCheck(getSellerOfRecordId(), productDetails.getSellerOfRecordId()) && internalEqualityCheck(getMerchantId(), productDetails.getMerchantId()) && internalEqualityCheck(getPrimaryGenreName(), productDetails.getPrimaryGenreName()) && internalEqualityCheck(getCopyright(), productDetails.getCopyright()) && internalEqualityCheck(getPrimaryGenre(), productDetails.getPrimaryGenre());
    }

    public String getCopyright() {
        return this.copyright;
    }

    public String getLabel() {
        return this.label;
    }

    public String getMerchantId() {
        return this.merchantId;
    }

    public String getMerchantName() {
        return this.merchantName;
    }

    public String getPrimaryGenre() {
        return this.primaryGenre;
    }

    public String getPrimaryGenreName() {
        return this.primaryGenreName;
    }

    public String getSellerOfRecordId() {
        return this.sellerOfRecordId;
    }

    public int hashCode() {
        return internalHashCodeCompute(Integer.valueOf(classNameHashCode), getMerchantName(), getLabel(), getSellerOfRecordId(), getMerchantId(), getPrimaryGenreName(), getCopyright(), getPrimaryGenre());
    }

    public void setCopyright(String str) {
        this.copyright = str;
    }

    public void setLabel(String str) {
        this.label = str;
    }

    public void setMerchantId(String str) {
        this.merchantId = str;
    }

    public void setMerchantName(String str) {
        this.merchantName = str;
    }

    public void setPrimaryGenre(String str) {
        this.primaryGenre = str;
    }

    public void setPrimaryGenreName(String str) {
        this.primaryGenreName = str;
    }

    public void setSellerOfRecordId(String str) {
        this.sellerOfRecordId = str;
    }
}
