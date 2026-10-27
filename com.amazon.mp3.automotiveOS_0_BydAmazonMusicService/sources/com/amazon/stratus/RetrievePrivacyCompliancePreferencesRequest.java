package com.amazon.stratus;

import java.util.Arrays;
import java.util.List;

/* JADX INFO: loaded from: classes5.dex */
public class RetrievePrivacyCompliancePreferencesRequest extends StratusAnonymousRequest {
    private static final int classNameHashCode = internalHashCodeCompute("com.amazon.stratus.RetrievePrivacyCompliancePreferencesRequest");
    private String customerId;
    private List<String> keys;
    private String locale;
    private String marketplaceId;
    private String namespace;
    private String sessionId;

    private static boolean internalEqualityCheck(Object obj, Object obj2) {
        return obj == obj2 || (obj != null && obj.equals(obj2));
    }

    private static int internalHashCodeCompute(Object... objArr) {
        return Arrays.hashCode(objArr);
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.amazon.stratus.StratusAnonymousRequest, java.lang.Comparable
    @Deprecated
    public int compareTo(@Deprecated StratusAnonymousRequest stratusAnonymousRequest) {
        if (stratusAnonymousRequest == null) {
            return -1;
        }
        if (stratusAnonymousRequest == this) {
            return 0;
        }
        if (!(stratusAnonymousRequest instanceof RetrievePrivacyCompliancePreferencesRequest)) {
            return 1;
        }
        RetrievePrivacyCompliancePreferencesRequest retrievePrivacyCompliancePreferencesRequest = (RetrievePrivacyCompliancePreferencesRequest) stratusAnonymousRequest;
        List<String> keys = getKeys();
        List<String> keys2 = retrievePrivacyCompliancePreferencesRequest.getKeys();
        if (keys != keys2) {
            if (keys == null) {
                return -1;
            }
            if (keys2 == null) {
                return 1;
            }
            if (keys instanceof Comparable) {
                int iCompareTo = ((Comparable) keys).compareTo(keys2);
                if (iCompareTo != 0) {
                    return iCompareTo;
                }
            } else if (!keys.equals(keys2)) {
                int iHashCode = keys.hashCode();
                int iHashCode2 = keys2.hashCode();
                if (iHashCode < iHashCode2) {
                    return -1;
                }
                if (iHashCode > iHashCode2) {
                    return 1;
                }
            }
        }
        String marketplaceId = getMarketplaceId();
        String marketplaceId2 = retrievePrivacyCompliancePreferencesRequest.getMarketplaceId();
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
        String customerId = getCustomerId();
        String customerId2 = retrievePrivacyCompliancePreferencesRequest.getCustomerId();
        if (customerId != customerId2) {
            if (customerId == null) {
                return -1;
            }
            if (customerId2 == null) {
                return 1;
            }
            int iCompareTo3 = customerId.compareTo(customerId2);
            if (iCompareTo3 != 0) {
                return iCompareTo3;
            }
        }
        String sessionId = getSessionId();
        String sessionId2 = retrievePrivacyCompliancePreferencesRequest.getSessionId();
        if (sessionId != sessionId2) {
            if (sessionId == null) {
                return -1;
            }
            if (sessionId2 == null) {
                return 1;
            }
            int iCompareTo4 = sessionId.compareTo(sessionId2);
            if (iCompareTo4 != 0) {
                return iCompareTo4;
            }
        }
        String locale = getLocale();
        String locale2 = retrievePrivacyCompliancePreferencesRequest.getLocale();
        if (locale != locale2) {
            if (locale == null) {
                return -1;
            }
            if (locale2 == null) {
                return 1;
            }
            int iCompareTo5 = locale.compareTo(locale2);
            if (iCompareTo5 != 0) {
                return iCompareTo5;
            }
        }
        String namespace = getNamespace();
        String namespace2 = retrievePrivacyCompliancePreferencesRequest.getNamespace();
        if (namespace != namespace2) {
            if (namespace == null) {
                return -1;
            }
            if (namespace2 == null) {
                return 1;
            }
            int iCompareTo6 = namespace.compareTo(namespace2);
            if (iCompareTo6 != 0) {
                return iCompareTo6;
            }
        }
        return super.compareTo(stratusAnonymousRequest);
    }

    @Override // com.amazon.stratus.StratusAnonymousRequest
    public boolean equals(Object obj) {
        if (!(obj instanceof RetrievePrivacyCompliancePreferencesRequest)) {
            return false;
        }
        RetrievePrivacyCompliancePreferencesRequest retrievePrivacyCompliancePreferencesRequest = (RetrievePrivacyCompliancePreferencesRequest) obj;
        return super.equals(obj) && internalEqualityCheck(getKeys(), retrievePrivacyCompliancePreferencesRequest.getKeys()) && internalEqualityCheck(getMarketplaceId(), retrievePrivacyCompliancePreferencesRequest.getMarketplaceId()) && internalEqualityCheck(getCustomerId(), retrievePrivacyCompliancePreferencesRequest.getCustomerId()) && internalEqualityCheck(getSessionId(), retrievePrivacyCompliancePreferencesRequest.getSessionId()) && internalEqualityCheck(getLocale(), retrievePrivacyCompliancePreferencesRequest.getLocale()) && internalEqualityCheck(getNamespace(), retrievePrivacyCompliancePreferencesRequest.getNamespace());
    }

    public String getCustomerId() {
        return this.customerId;
    }

    public List<String> getKeys() {
        return this.keys;
    }

    public String getLocale() {
        return this.locale;
    }

    public String getMarketplaceId() {
        return this.marketplaceId;
    }

    public String getNamespace() {
        return this.namespace;
    }

    public String getSessionId() {
        return this.sessionId;
    }

    @Override // com.amazon.stratus.StratusAnonymousRequest
    public int hashCode() {
        return internalHashCodeCompute(Integer.valueOf(super.hashCode()), Integer.valueOf(classNameHashCode), getKeys(), getMarketplaceId(), getCustomerId(), getSessionId(), getLocale(), getNamespace());
    }

    public void setNamespace(String str) {
        this.namespace = str;
    }
}
