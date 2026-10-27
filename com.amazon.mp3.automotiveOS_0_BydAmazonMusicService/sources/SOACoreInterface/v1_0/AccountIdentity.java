package SOACoreInterface.v1_0;

import SOACoreInterface.v1_0.SOAObject;
import java.util.Arrays;
import java.util.Map;

/* JADX INFO: loaded from: classes.dex */
public class AccountIdentity extends SOAObject {
    private static final int classNameHashCode = internalHashCodeCompute("SOACoreInterface.v1_0.AccountIdentity");
    private Map<String, String> benefits;
    private Boolean explicitLanguageFilterEnabled;
    private String marketplaceId;
    private String musicTerritory;

    public static class Builder extends SOAObject.Builder {
        protected Map<String, String> benefits;
        protected Boolean explicitLanguageFilterEnabled;
        protected String marketplaceId;
        protected String musicTerritory;

        public AccountIdentity build() {
            AccountIdentity accountIdentity = new AccountIdentity();
            populate(accountIdentity);
            return accountIdentity;
        }

        protected void populate(AccountIdentity accountIdentity) {
            super.populate((SOAObject) accountIdentity);
            accountIdentity.setMusicTerritory(this.musicTerritory);
            accountIdentity.setMarketplaceId(this.marketplaceId);
            accountIdentity.setExplicitLanguageFilterEnabled(this.explicitLanguageFilterEnabled);
            accountIdentity.setBenefits(this.benefits);
        }

        public Builder withBenefits(Map<String, String> map) {
            this.benefits = map;
            return this;
        }

        public Builder withExplicitLanguageFilterEnabled(Boolean bool) {
            this.explicitLanguageFilterEnabled = bool;
            return this;
        }

        public Builder withMarketplaceId(String str) {
            this.marketplaceId = str;
            return this;
        }

        public Builder withMusicTerritory(String str) {
            this.musicTerritory = str;
            return this;
        }
    }

    public static Builder builder() {
        return new Builder();
    }

    private static boolean internalEqualityCheck(Object obj, Object obj2) {
        return obj == obj2 || (obj != null && obj.equals(obj2));
    }

    private static int internalHashCodeCompute(Object... objArr) {
        return Arrays.hashCode(objArr);
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // SOACoreInterface.v1_0.SOAObject, java.lang.Comparable
    @Deprecated
    public int compareTo(@Deprecated SOAObject sOAObject) {
        if (sOAObject == null) {
            return -1;
        }
        if (sOAObject == this) {
            return 0;
        }
        if (!(sOAObject instanceof AccountIdentity)) {
            return 1;
        }
        AccountIdentity accountIdentity = (AccountIdentity) sOAObject;
        String musicTerritory = getMusicTerritory();
        String musicTerritory2 = accountIdentity.getMusicTerritory();
        if (musicTerritory != musicTerritory2) {
            if (musicTerritory == null) {
                return -1;
            }
            if (musicTerritory2 == null) {
                return 1;
            }
            int iCompareTo = musicTerritory.compareTo(musicTerritory2);
            if (iCompareTo != 0) {
                return iCompareTo;
            }
        }
        String marketplaceId = getMarketplaceId();
        String marketplaceId2 = accountIdentity.getMarketplaceId();
        if (marketplaceId != marketplaceId2) {
            if (marketplaceId == null) {
                return -1;
            }
            if (marketplaceId2 == null) {
                return 1;
            }
            int iCompareTo2 = marketplaceId.compareTo(marketplaceId2);
            if (iCompareTo2 != 0) {
                return iCompareTo2;
            }
        }
        Boolean boolIsExplicitLanguageFilterEnabled = isExplicitLanguageFilterEnabled();
        Boolean boolIsExplicitLanguageFilterEnabled2 = accountIdentity.isExplicitLanguageFilterEnabled();
        if (boolIsExplicitLanguageFilterEnabled != boolIsExplicitLanguageFilterEnabled2) {
            if (boolIsExplicitLanguageFilterEnabled == null) {
                return -1;
            }
            if (boolIsExplicitLanguageFilterEnabled2 == null) {
                return 1;
            }
            int iCompareTo3 = boolIsExplicitLanguageFilterEnabled.compareTo(boolIsExplicitLanguageFilterEnabled2);
            if (iCompareTo3 != 0) {
                return iCompareTo3;
            }
        }
        Map<String, String> benefits = getBenefits();
        Map<String, String> benefits2 = accountIdentity.getBenefits();
        if (benefits != benefits2) {
            if (benefits == null) {
                return -1;
            }
            if (benefits2 == null) {
                return 1;
            }
            if (benefits instanceof Comparable) {
                int iCompareTo4 = ((Comparable) benefits).compareTo(benefits2);
                if (iCompareTo4 != 0) {
                    return iCompareTo4;
                }
            } else if (!benefits.equals(benefits2)) {
                int iHashCode = benefits.hashCode();
                int iHashCode2 = benefits2.hashCode();
                if (iHashCode < iHashCode2) {
                    return -1;
                }
                if (iHashCode > iHashCode2) {
                    return 1;
                }
            }
        }
        return super.compareTo(sOAObject);
    }

    @Override // SOACoreInterface.v1_0.SOAObject
    public boolean equals(Object obj) {
        if (!(obj instanceof AccountIdentity)) {
            return false;
        }
        AccountIdentity accountIdentity = (AccountIdentity) obj;
        return super.equals(obj) && internalEqualityCheck(getMusicTerritory(), accountIdentity.getMusicTerritory()) && internalEqualityCheck(getMarketplaceId(), accountIdentity.getMarketplaceId()) && internalEqualityCheck(isExplicitLanguageFilterEnabled(), accountIdentity.isExplicitLanguageFilterEnabled()) && internalEqualityCheck(getBenefits(), accountIdentity.getBenefits());
    }

    public Map<String, String> getBenefits() {
        return this.benefits;
    }

    public String getMarketplaceId() {
        return this.marketplaceId;
    }

    public String getMusicTerritory() {
        return this.musicTerritory;
    }

    @Override // SOACoreInterface.v1_0.SOAObject
    public int hashCode() {
        return internalHashCodeCompute(Integer.valueOf(super.hashCode()), Integer.valueOf(classNameHashCode), getMusicTerritory(), getMarketplaceId(), isExplicitLanguageFilterEnabled(), getBenefits());
    }

    public Boolean isExplicitLanguageFilterEnabled() {
        return this.explicitLanguageFilterEnabled;
    }

    public void setBenefits(Map<String, String> map) {
        this.benefits = map;
    }

    public void setExplicitLanguageFilterEnabled(Boolean bool) {
        this.explicitLanguageFilterEnabled = bool;
    }

    public void setMarketplaceId(String str) {
        this.marketplaceId = str;
    }

    public void setMusicTerritory(String str) {
        this.musicTerritory = str;
    }
}
