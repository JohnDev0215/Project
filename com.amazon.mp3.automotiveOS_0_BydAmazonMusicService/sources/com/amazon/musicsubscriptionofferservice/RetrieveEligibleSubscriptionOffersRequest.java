package com.amazon.musicsubscriptionofferservice;

import com.amazon.musicidentityservice.shared.model.MusicRequestIdentityContext;
import java.util.Arrays;

/* JADX INFO: loaded from: classes5.dex */
public class RetrieveEligibleSubscriptionOffersRequest implements Comparable<RetrieveEligibleSubscriptionOffersRequest> {
    private static final int classNameHashCode = internalHashCodeCompute("com.amazon.musicsubscriptionofferservice.RetrieveEligibleSubscriptionOffersRequest");
    private String clientId;
    private String customerId;
    private CustomerPreferenceRequest customerPreferenceRequest;
    private String deviceId;
    private String deviceType;
    private Query filterQuery;
    private String ipAddress;
    private MusicRequestIdentityContext musicRequestIdentityContext;
    private String musicTerritory;
    private RequestConfig requestConfig;

    private static boolean internalEqualityCheck(Object obj, Object obj2) {
        return obj == obj2 || (obj != null && obj.equals(obj2));
    }

    private static int internalHashCodeCompute(Object... objArr) {
        return Arrays.hashCode(objArr);
    }

    @Override // java.lang.Comparable
    @Deprecated
    public int compareTo(@Deprecated RetrieveEligibleSubscriptionOffersRequest retrieveEligibleSubscriptionOffersRequest) {
        if (retrieveEligibleSubscriptionOffersRequest == null) {
            return -1;
        }
        if (retrieveEligibleSubscriptionOffersRequest == this) {
            return 0;
        }
        Query filterQuery = getFilterQuery();
        Query filterQuery2 = retrieveEligibleSubscriptionOffersRequest.getFilterQuery();
        if (filterQuery != filterQuery2) {
            if (filterQuery == null) {
                return -1;
            }
            if (filterQuery2 == null) {
                return 1;
            }
            int iCompareTo = filterQuery.compareTo(filterQuery2);
            if (iCompareTo != 0) {
                return iCompareTo;
            }
        }
        String ipAddress = getIpAddress();
        String ipAddress2 = retrieveEligibleSubscriptionOffersRequest.getIpAddress();
        if (ipAddress != ipAddress2) {
            if (ipAddress == null) {
                return -1;
            }
            if (ipAddress2 == null) {
                return 1;
            }
            int iCompareTo2 = ipAddress.compareTo(ipAddress2);
            if (iCompareTo2 != 0) {
                return iCompareTo2;
            }
        }
        String customerId = getCustomerId();
        String customerId2 = retrieveEligibleSubscriptionOffersRequest.getCustomerId();
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
        String deviceId = getDeviceId();
        String deviceId2 = retrieveEligibleSubscriptionOffersRequest.getDeviceId();
        if (deviceId != deviceId2) {
            if (deviceId == null) {
                return -1;
            }
            if (deviceId2 == null) {
                return 1;
            }
            int iCompareTo4 = deviceId.compareTo(deviceId2);
            if (iCompareTo4 != 0) {
                return iCompareTo4;
            }
        }
        String clientId = getClientId();
        String clientId2 = retrieveEligibleSubscriptionOffersRequest.getClientId();
        if (clientId != clientId2) {
            if (clientId == null) {
                return -1;
            }
            if (clientId2 == null) {
                return 1;
            }
            int iCompareTo5 = clientId.compareTo(clientId2);
            if (iCompareTo5 != 0) {
                return iCompareTo5;
            }
        }
        CustomerPreferenceRequest customerPreferenceRequest = getCustomerPreferenceRequest();
        CustomerPreferenceRequest customerPreferenceRequest2 = retrieveEligibleSubscriptionOffersRequest.getCustomerPreferenceRequest();
        if (customerPreferenceRequest != customerPreferenceRequest2) {
            if (customerPreferenceRequest == null) {
                return -1;
            }
            if (customerPreferenceRequest2 == null) {
                return 1;
            }
            int iCompareTo6 = customerPreferenceRequest.compareTo(customerPreferenceRequest2);
            if (iCompareTo6 != 0) {
                return iCompareTo6;
            }
        }
        String deviceType = getDeviceType();
        String deviceType2 = retrieveEligibleSubscriptionOffersRequest.getDeviceType();
        if (deviceType != deviceType2) {
            if (deviceType == null) {
                return -1;
            }
            if (deviceType2 == null) {
                return 1;
            }
            int iCompareTo7 = deviceType.compareTo(deviceType2);
            if (iCompareTo7 != 0) {
                return iCompareTo7;
            }
        }
        String musicTerritory = getMusicTerritory();
        String musicTerritory2 = retrieveEligibleSubscriptionOffersRequest.getMusicTerritory();
        if (musicTerritory != musicTerritory2) {
            if (musicTerritory == null) {
                return -1;
            }
            if (musicTerritory2 == null) {
                return 1;
            }
            int iCompareTo8 = musicTerritory.compareTo(musicTerritory2);
            if (iCompareTo8 != 0) {
                return iCompareTo8;
            }
        }
        MusicRequestIdentityContext musicRequestIdentityContext = getMusicRequestIdentityContext();
        MusicRequestIdentityContext musicRequestIdentityContext2 = retrieveEligibleSubscriptionOffersRequest.getMusicRequestIdentityContext();
        if (musicRequestIdentityContext != musicRequestIdentityContext2) {
            if (musicRequestIdentityContext == null) {
                return -1;
            }
            if (musicRequestIdentityContext2 == null) {
                return 1;
            }
            int iCompareTo9 = musicRequestIdentityContext.compareTo(musicRequestIdentityContext2);
            if (iCompareTo9 != 0) {
                return iCompareTo9;
            }
        }
        RequestConfig requestConfig = getRequestConfig();
        RequestConfig requestConfig2 = retrieveEligibleSubscriptionOffersRequest.getRequestConfig();
        if (requestConfig != requestConfig2) {
            if (requestConfig == null) {
                return -1;
            }
            if (requestConfig2 == null) {
                return 1;
            }
            int iCompareTo10 = requestConfig.compareTo(requestConfig2);
            if (iCompareTo10 != 0) {
                return iCompareTo10;
            }
        }
        return 0;
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof RetrieveEligibleSubscriptionOffersRequest)) {
            return false;
        }
        RetrieveEligibleSubscriptionOffersRequest retrieveEligibleSubscriptionOffersRequest = (RetrieveEligibleSubscriptionOffersRequest) obj;
        return internalEqualityCheck(getFilterQuery(), retrieveEligibleSubscriptionOffersRequest.getFilterQuery()) && internalEqualityCheck(getIpAddress(), retrieveEligibleSubscriptionOffersRequest.getIpAddress()) && internalEqualityCheck(getCustomerId(), retrieveEligibleSubscriptionOffersRequest.getCustomerId()) && internalEqualityCheck(getDeviceId(), retrieveEligibleSubscriptionOffersRequest.getDeviceId()) && internalEqualityCheck(getClientId(), retrieveEligibleSubscriptionOffersRequest.getClientId()) && internalEqualityCheck(getCustomerPreferenceRequest(), retrieveEligibleSubscriptionOffersRequest.getCustomerPreferenceRequest()) && internalEqualityCheck(getDeviceType(), retrieveEligibleSubscriptionOffersRequest.getDeviceType()) && internalEqualityCheck(getMusicTerritory(), retrieveEligibleSubscriptionOffersRequest.getMusicTerritory()) && internalEqualityCheck(getMusicRequestIdentityContext(), retrieveEligibleSubscriptionOffersRequest.getMusicRequestIdentityContext()) && internalEqualityCheck(getRequestConfig(), retrieveEligibleSubscriptionOffersRequest.getRequestConfig());
    }

    public String getClientId() {
        return this.clientId;
    }

    public String getCustomerId() {
        return this.customerId;
    }

    public CustomerPreferenceRequest getCustomerPreferenceRequest() {
        return this.customerPreferenceRequest;
    }

    public String getDeviceId() {
        return this.deviceId;
    }

    public String getDeviceType() {
        return this.deviceType;
    }

    public Query getFilterQuery() {
        return this.filterQuery;
    }

    public String getIpAddress() {
        return this.ipAddress;
    }

    public MusicRequestIdentityContext getMusicRequestIdentityContext() {
        return this.musicRequestIdentityContext;
    }

    public String getMusicTerritory() {
        return this.musicTerritory;
    }

    public RequestConfig getRequestConfig() {
        return this.requestConfig;
    }

    public int hashCode() {
        return internalHashCodeCompute(Integer.valueOf(classNameHashCode), getFilterQuery(), getIpAddress(), getCustomerId(), getDeviceId(), getClientId(), getCustomerPreferenceRequest(), getDeviceType(), getMusicTerritory(), getMusicRequestIdentityContext(), getRequestConfig());
    }

    public void setClientId(String str) {
        this.clientId = str;
    }

    public void setCustomerId(String str) {
        this.customerId = str;
    }

    public void setCustomerPreferenceRequest(CustomerPreferenceRequest customerPreferenceRequest) {
        this.customerPreferenceRequest = customerPreferenceRequest;
    }

    public void setDeviceId(String str) {
        this.deviceId = str;
    }

    public void setDeviceType(String str) {
        this.deviceType = str;
    }

    public void setFilterQuery(Query query) {
        this.filterQuery = query;
    }

    public void setIpAddress(String str) {
        this.ipAddress = str;
    }

    public void setMusicRequestIdentityContext(MusicRequestIdentityContext musicRequestIdentityContext) {
        this.musicRequestIdentityContext = musicRequestIdentityContext;
    }

    public void setMusicTerritory(String str) {
        this.musicTerritory = str;
    }

    public void setRequestConfig(RequestConfig requestConfig) {
        this.requestConfig = requestConfig;
    }
}
