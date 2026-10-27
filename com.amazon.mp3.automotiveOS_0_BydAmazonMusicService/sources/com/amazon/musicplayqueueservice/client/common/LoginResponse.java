package com.amazon.musicplayqueueservice.client.common;

import java.util.Arrays;

/* JADX INFO: loaded from: classes5.dex */
public class LoginResponse implements Comparable<LoginResponse> {
    private static final int classNameHashCode = internalHashCodeCompute("com.amazon.musicplayqueueservice.client.common.LoginResponse");
    private String identityId;
    private String token;
    private long tokenTimeToLiveInSeconds;

    private static boolean internalEqualityCheck(Object obj, Object obj2) {
        return obj == obj2 || (obj != null && obj.equals(obj2));
    }

    private static int internalHashCodeCompute(Object... objArr) {
        return Arrays.hashCode(objArr);
    }

    @Override // java.lang.Comparable
    @Deprecated
    public int compareTo(@Deprecated LoginResponse loginResponse) {
        if (loginResponse == null) {
            return -1;
        }
        if (loginResponse == this) {
            return 0;
        }
        String identityId = getIdentityId();
        String identityId2 = loginResponse.getIdentityId();
        if (identityId != identityId2) {
            if (identityId == null) {
                return -1;
            }
            if (identityId2 == null) {
                return 1;
            }
            int iCompareTo = identityId.compareTo(identityId2);
            if (iCompareTo != 0) {
                return iCompareTo;
            }
        }
        String token = getToken();
        String token2 = loginResponse.getToken();
        if (token != token2) {
            if (token == null) {
                return -1;
            }
            if (token2 == null) {
                return 1;
            }
            int iCompareTo2 = token.compareTo(token2);
            if (iCompareTo2 != 0) {
                return iCompareTo2;
            }
        }
        if (getTokenTimeToLiveInSeconds() < loginResponse.getTokenTimeToLiveInSeconds()) {
            return -1;
        }
        return getTokenTimeToLiveInSeconds() > loginResponse.getTokenTimeToLiveInSeconds() ? 1 : 0;
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof LoginResponse)) {
            return false;
        }
        LoginResponse loginResponse = (LoginResponse) obj;
        return internalEqualityCheck(getIdentityId(), loginResponse.getIdentityId()) && internalEqualityCheck(getToken(), loginResponse.getToken()) && internalEqualityCheck(Long.valueOf(getTokenTimeToLiveInSeconds()), Long.valueOf(loginResponse.getTokenTimeToLiveInSeconds()));
    }

    public String getIdentityId() {
        return this.identityId;
    }

    public String getToken() {
        return this.token;
    }

    public long getTokenTimeToLiveInSeconds() {
        return this.tokenTimeToLiveInSeconds;
    }

    public int hashCode() {
        return internalHashCodeCompute(Integer.valueOf(classNameHashCode), getIdentityId(), getToken(), Long.valueOf(getTokenTimeToLiveInSeconds()));
    }

    public void setIdentityId(String str) {
        this.identityId = str;
    }

    public void setToken(String str) {
        this.token = str;
    }

    public void setTokenTimeToLiveInSeconds(long j) {
        this.tokenTimeToLiveInSeconds = j;
    }
}
