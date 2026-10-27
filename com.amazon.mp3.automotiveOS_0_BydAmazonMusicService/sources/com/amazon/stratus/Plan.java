package com.amazon.stratus;

import java.math.BigDecimal;
import java.util.Arrays;

/* JADX INFO: loaded from: classes5.dex */
public class Plan implements Comparable<Plan> {
    private static final int classNameHashCode = internalHashCodeCompute("com.amazon.stratus.Plan");
    private Period contractPeriod;
    private String currencyCode;
    private Plan defaultRolloverPlan;
    private Boolean isPromo;
    private String marketplaceId;
    private String planId;
    private BigDecimal price;

    private static boolean internalEqualityCheck(Object obj, Object obj2) {
        return obj == obj2 || (obj != null && obj.equals(obj2));
    }

    private static int internalHashCodeCompute(Object... objArr) {
        return Arrays.hashCode(objArr);
    }

    @Override // java.lang.Comparable
    @Deprecated
    public int compareTo(@Deprecated Plan plan) {
        if (plan == null) {
            return -1;
        }
        if (plan == this) {
            return 0;
        }
        Boolean boolIsIsPromo = isIsPromo();
        Boolean boolIsIsPromo2 = plan.isIsPromo();
        if (boolIsIsPromo != boolIsIsPromo2) {
            if (boolIsIsPromo == null) {
                return -1;
            }
            if (boolIsIsPromo2 == null) {
                return 1;
            }
            int iCompareTo = boolIsIsPromo.compareTo(boolIsIsPromo2);
            if (iCompareTo != 0) {
                return iCompareTo;
            }
        }
        BigDecimal price = getPrice();
        BigDecimal price2 = plan.getPrice();
        if (price != price2) {
            if (price == null) {
                return -1;
            }
            if (price2 == null) {
                return 1;
            }
            int iCompareTo2 = price.compareTo(price2);
            if (iCompareTo2 != 0) {
                return iCompareTo2;
            }
        }
        String marketplaceId = getMarketplaceId();
        String marketplaceId2 = plan.getMarketplaceId();
        if (marketplaceId != marketplaceId2) {
            if (marketplaceId == null) {
                return -1;
            }
            if (marketplaceId2 == null) {
                return 1;
            }
            int iCompareTo3 = marketplaceId.compareTo(marketplaceId2);
            if (iCompareTo3 != 0) {
                return iCompareTo3;
            }
        }
        Plan defaultRolloverPlan = getDefaultRolloverPlan();
        Plan defaultRolloverPlan2 = plan.getDefaultRolloverPlan();
        if (defaultRolloverPlan != defaultRolloverPlan2) {
            if (defaultRolloverPlan == null) {
                return -1;
            }
            if (defaultRolloverPlan2 == null) {
                return 1;
            }
            int iCompareTo4 = defaultRolloverPlan.compareTo(defaultRolloverPlan2);
            if (iCompareTo4 != 0) {
                return iCompareTo4;
            }
        }
        String planId = getPlanId();
        String planId2 = plan.getPlanId();
        if (planId != planId2) {
            if (planId == null) {
                return -1;
            }
            if (planId2 == null) {
                return 1;
            }
            int iCompareTo5 = planId.compareTo(planId2);
            if (iCompareTo5 != 0) {
                return iCompareTo5;
            }
        }
        Period contractPeriod = getContractPeriod();
        Period contractPeriod2 = plan.getContractPeriod();
        if (contractPeriod != contractPeriod2) {
            if (contractPeriod == null) {
                return -1;
            }
            if (contractPeriod2 == null) {
                return 1;
            }
            int iCompareTo6 = contractPeriod.compareTo(contractPeriod2);
            if (iCompareTo6 != 0) {
                return iCompareTo6;
            }
        }
        String currencyCode = getCurrencyCode();
        String currencyCode2 = plan.getCurrencyCode();
        if (currencyCode != currencyCode2) {
            if (currencyCode == null) {
                return -1;
            }
            if (currencyCode2 == null) {
                return 1;
            }
            int iCompareTo7 = currencyCode.compareTo(currencyCode2);
            if (iCompareTo7 != 0) {
                return iCompareTo7;
            }
        }
        return 0;
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof Plan)) {
            return false;
        }
        Plan plan = (Plan) obj;
        return internalEqualityCheck(isIsPromo(), plan.isIsPromo()) && internalEqualityCheck(getPrice(), plan.getPrice()) && internalEqualityCheck(getMarketplaceId(), plan.getMarketplaceId()) && internalEqualityCheck(getDefaultRolloverPlan(), plan.getDefaultRolloverPlan()) && internalEqualityCheck(getPlanId(), plan.getPlanId()) && internalEqualityCheck(getContractPeriod(), plan.getContractPeriod()) && internalEqualityCheck(getCurrencyCode(), plan.getCurrencyCode());
    }

    public Period getContractPeriod() {
        return this.contractPeriod;
    }

    public String getCurrencyCode() {
        return this.currencyCode;
    }

    public Plan getDefaultRolloverPlan() {
        return this.defaultRolloverPlan;
    }

    public String getMarketplaceId() {
        return this.marketplaceId;
    }

    public String getPlanId() {
        return this.planId;
    }

    public BigDecimal getPrice() {
        return this.price;
    }

    public int hashCode() {
        return internalHashCodeCompute(Integer.valueOf(classNameHashCode), isIsPromo(), getPrice(), getMarketplaceId(), getDefaultRolloverPlan(), getPlanId(), getContractPeriod(), getCurrencyCode());
    }

    public Boolean isIsPromo() {
        return this.isPromo;
    }

    public void setContractPeriod(Period period) {
        this.contractPeriod = period;
    }

    public void setCurrencyCode(String str) {
        this.currencyCode = str;
    }

    public void setDefaultRolloverPlan(Plan plan) {
        this.defaultRolloverPlan = plan;
    }

    public void setIsPromo(Boolean bool) {
        this.isPromo = bool;
    }

    public void setMarketplaceId(String str) {
        this.marketplaceId = str;
    }

    public void setPlanId(String str) {
        this.planId = str;
    }

    public void setPrice(BigDecimal bigDecimal) {
        this.price = bigDecimal;
    }
}
