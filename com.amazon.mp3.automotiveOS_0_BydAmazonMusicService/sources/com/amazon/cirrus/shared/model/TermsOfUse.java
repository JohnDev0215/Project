package com.amazon.cirrus.shared.model;

import java.util.Arrays;

/* JADX INFO: loaded from: classes2.dex */
public class TermsOfUse implements Comparable<TermsOfUse> {
    private static final int classNameHashCode = internalHashCodeCompute("com.amazon.cirrus.shared.model.TermsOfUse");
    private String marketplaceId;
    private String musicTerritory;
    private String type;
    private String version;

    private static boolean internalEqualityCheck(Object obj, Object obj2) {
        return obj == obj2 || (obj != null && obj.equals(obj2));
    }

    private static int internalHashCodeCompute(Object... objArr) {
        return Arrays.hashCode(objArr);
    }

    @Override // java.lang.Comparable
    @Deprecated
    public int compareTo(@Deprecated TermsOfUse termsOfUse) {
        if (termsOfUse == null) {
            return -1;
        }
        if (termsOfUse == this) {
            return 0;
        }
        String version = getVersion();
        String version2 = termsOfUse.getVersion();
        if (version != version2) {
            if (version == null) {
                return -1;
            }
            if (version2 == null) {
                return 1;
            }
            int iCompareTo = version.compareTo(version2);
            if (iCompareTo != 0) {
                return iCompareTo;
            }
        }
        String musicTerritory = getMusicTerritory();
        String musicTerritory2 = termsOfUse.getMusicTerritory();
        if (musicTerritory != musicTerritory2) {
            if (musicTerritory == null) {
                return -1;
            }
            if (musicTerritory2 == null) {
                return 1;
            }
            int iCompareTo2 = musicTerritory.compareTo(musicTerritory2);
            if (iCompareTo2 != 0) {
                return iCompareTo2;
            }
        }
        String marketplaceId = getMarketplaceId();
        String marketplaceId2 = termsOfUse.getMarketplaceId();
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
        String type = getType();
        String type2 = termsOfUse.getType();
        if (type != type2) {
            if (type == null) {
                return -1;
            }
            if (type2 == null) {
                return 1;
            }
            int iCompareTo4 = type.compareTo(type2);
            if (iCompareTo4 != 0) {
                return iCompareTo4;
            }
        }
        return 0;
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof TermsOfUse)) {
            return false;
        }
        TermsOfUse termsOfUse = (TermsOfUse) obj;
        return internalEqualityCheck(getVersion(), termsOfUse.getVersion()) && internalEqualityCheck(getMusicTerritory(), termsOfUse.getMusicTerritory()) && internalEqualityCheck(getMarketplaceId(), termsOfUse.getMarketplaceId()) && internalEqualityCheck(getType(), termsOfUse.getType());
    }

    public String getMarketplaceId() {
        return this.marketplaceId;
    }

    public String getMusicTerritory() {
        return this.musicTerritory;
    }

    public String getType() {
        return this.type;
    }

    public String getVersion() {
        return this.version;
    }

    public int hashCode() {
        return internalHashCodeCompute(Integer.valueOf(classNameHashCode), getVersion(), getMusicTerritory(), getMarketplaceId(), getType());
    }

    public void setMarketplaceId(String str) {
        this.marketplaceId = str;
    }

    public void setMusicTerritory(String str) {
        this.musicTerritory = str;
    }

    public void setType(String str) {
        this.type = str;
    }

    public void setVersion(String str) {
        this.version = str;
    }
}
