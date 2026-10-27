package com.amazon.musicsubscriptionofferservice;

import java.util.Arrays;
import java.util.Date;

/* JADX INFO: loaded from: classes5.dex */
public class SalesPromotionInfo implements Comparable<SalesPromotionInfo> {
    private static final int classNameHashCode = internalHashCodeCompute("com.amazon.musicsubscriptionofferservice.SalesPromotionInfo");
    private DateRange effective;
    private Date expiryDate;
    private String promotionId;
    private String promotionTitle;
    private Integer residualUses;
    private String savingsMessage;
    private String subscriptionBillingMessage;

    private static boolean internalEqualityCheck(Object obj, Object obj2) {
        return obj == obj2 || (obj != null && obj.equals(obj2));
    }

    private static int internalHashCodeCompute(Object... objArr) {
        return Arrays.hashCode(objArr);
    }

    @Override // java.lang.Comparable
    @Deprecated
    public int compareTo(@Deprecated SalesPromotionInfo salesPromotionInfo) {
        if (salesPromotionInfo == null) {
            return -1;
        }
        if (salesPromotionInfo == this) {
            return 0;
        }
        DateRange effective = getEffective();
        DateRange effective2 = salesPromotionInfo.getEffective();
        if (effective != effective2) {
            if (effective == null) {
                return -1;
            }
            if (effective2 == null) {
                return 1;
            }
            int iCompareTo = effective.compareTo(effective2);
            if (iCompareTo != 0) {
                return iCompareTo;
            }
        }
        String savingsMessage = getSavingsMessage();
        String savingsMessage2 = salesPromotionInfo.getSavingsMessage();
        if (savingsMessage != savingsMessage2) {
            if (savingsMessage == null) {
                return -1;
            }
            if (savingsMessage2 == null) {
                return 1;
            }
            int iCompareTo2 = savingsMessage.compareTo(savingsMessage2);
            if (iCompareTo2 != 0) {
                return iCompareTo2;
            }
        }
        Integer residualUses = getResidualUses();
        Integer residualUses2 = salesPromotionInfo.getResidualUses();
        if (residualUses != residualUses2) {
            if (residualUses == null) {
                return -1;
            }
            if (residualUses2 == null) {
                return 1;
            }
            int iCompareTo3 = residualUses.compareTo(residualUses2);
            if (iCompareTo3 != 0) {
                return iCompareTo3;
            }
        }
        String subscriptionBillingMessage = getSubscriptionBillingMessage();
        String subscriptionBillingMessage2 = salesPromotionInfo.getSubscriptionBillingMessage();
        if (subscriptionBillingMessage != subscriptionBillingMessage2) {
            if (subscriptionBillingMessage == null) {
                return -1;
            }
            if (subscriptionBillingMessage2 == null) {
                return 1;
            }
            int iCompareTo4 = subscriptionBillingMessage.compareTo(subscriptionBillingMessage2);
            if (iCompareTo4 != 0) {
                return iCompareTo4;
            }
        }
        Date expiryDate = getExpiryDate();
        Date expiryDate2 = salesPromotionInfo.getExpiryDate();
        if (expiryDate != expiryDate2) {
            if (expiryDate == null) {
                return -1;
            }
            if (expiryDate2 == null) {
                return 1;
            }
            int iCompareTo5 = expiryDate.compareTo(expiryDate2);
            if (iCompareTo5 != 0) {
                return iCompareTo5;
            }
        }
        String promotionTitle = getPromotionTitle();
        String promotionTitle2 = salesPromotionInfo.getPromotionTitle();
        if (promotionTitle != promotionTitle2) {
            if (promotionTitle == null) {
                return -1;
            }
            if (promotionTitle2 == null) {
                return 1;
            }
            int iCompareTo6 = promotionTitle.compareTo(promotionTitle2);
            if (iCompareTo6 != 0) {
                return iCompareTo6;
            }
        }
        String promotionId = getPromotionId();
        String promotionId2 = salesPromotionInfo.getPromotionId();
        if (promotionId != promotionId2) {
            if (promotionId == null) {
                return -1;
            }
            if (promotionId2 == null) {
                return 1;
            }
            int iCompareTo7 = promotionId.compareTo(promotionId2);
            if (iCompareTo7 != 0) {
                return iCompareTo7;
            }
        }
        return 0;
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof SalesPromotionInfo)) {
            return false;
        }
        SalesPromotionInfo salesPromotionInfo = (SalesPromotionInfo) obj;
        return internalEqualityCheck(getEffective(), salesPromotionInfo.getEffective()) && internalEqualityCheck(getSavingsMessage(), salesPromotionInfo.getSavingsMessage()) && internalEqualityCheck(getResidualUses(), salesPromotionInfo.getResidualUses()) && internalEqualityCheck(getSubscriptionBillingMessage(), salesPromotionInfo.getSubscriptionBillingMessage()) && internalEqualityCheck(getExpiryDate(), salesPromotionInfo.getExpiryDate()) && internalEqualityCheck(getPromotionTitle(), salesPromotionInfo.getPromotionTitle()) && internalEqualityCheck(getPromotionId(), salesPromotionInfo.getPromotionId());
    }

    public DateRange getEffective() {
        return this.effective;
    }

    public Date getExpiryDate() {
        return this.expiryDate;
    }

    public String getPromotionId() {
        return this.promotionId;
    }

    public String getPromotionTitle() {
        return this.promotionTitle;
    }

    public Integer getResidualUses() {
        return this.residualUses;
    }

    public String getSavingsMessage() {
        return this.savingsMessage;
    }

    public String getSubscriptionBillingMessage() {
        return this.subscriptionBillingMessage;
    }

    public int hashCode() {
        return internalHashCodeCompute(Integer.valueOf(classNameHashCode), getEffective(), getSavingsMessage(), getResidualUses(), getSubscriptionBillingMessage(), getExpiryDate(), getPromotionTitle(), getPromotionId());
    }

    public void setEffective(DateRange dateRange) {
        this.effective = dateRange;
    }

    public void setExpiryDate(Date date) {
        this.expiryDate = date;
    }

    public void setPromotionId(String str) {
        this.promotionId = str;
    }

    public void setPromotionTitle(String str) {
        this.promotionTitle = str;
    }

    public void setResidualUses(Integer num) {
        this.residualUses = num;
    }

    public void setSavingsMessage(String str) {
        this.savingsMessage = str;
    }

    public void setSubscriptionBillingMessage(String str) {
        this.subscriptionBillingMessage = str;
    }
}
