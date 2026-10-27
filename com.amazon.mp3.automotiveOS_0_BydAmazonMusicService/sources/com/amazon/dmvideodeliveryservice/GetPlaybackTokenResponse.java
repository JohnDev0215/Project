package com.amazon.dmvideodeliveryservice;

import java.util.Arrays;
import java.util.Date;

/* JADX INFO: loaded from: classes2.dex */
public class GetPlaybackTokenResponse implements Comparable<GetPlaybackTokenResponse> {
    private static final int classNameHashCode = internalHashCodeCompute("com.amazon.dmvideodeliveryservice.GetPlaybackTokenResponse");
    private Date expiration;
    private String playbackToken;

    private static boolean internalEqualityCheck(Object obj, Object obj2) {
        return obj == obj2 || (obj != null && obj.equals(obj2));
    }

    private static int internalHashCodeCompute(Object... objArr) {
        return Arrays.hashCode(objArr);
    }

    @Override // java.lang.Comparable
    @Deprecated
    public int compareTo(@Deprecated GetPlaybackTokenResponse getPlaybackTokenResponse) {
        if (getPlaybackTokenResponse == null) {
            return -1;
        }
        if (getPlaybackTokenResponse == this) {
            return 0;
        }
        String playbackToken = getPlaybackToken();
        String playbackToken2 = getPlaybackTokenResponse.getPlaybackToken();
        if (playbackToken != playbackToken2) {
            if (playbackToken == null) {
                return -1;
            }
            if (playbackToken2 == null) {
                return 1;
            }
            int iCompareTo = playbackToken.compareTo(playbackToken2);
            if (iCompareTo != 0) {
                return iCompareTo;
            }
        }
        Date expiration = getExpiration();
        Date expiration2 = getPlaybackTokenResponse.getExpiration();
        if (expiration != expiration2) {
            if (expiration == null) {
                return -1;
            }
            if (expiration2 == null) {
                return 1;
            }
            int iCompareTo2 = expiration.compareTo(expiration2);
            if (iCompareTo2 != 0) {
                return iCompareTo2;
            }
        }
        return 0;
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof GetPlaybackTokenResponse)) {
            return false;
        }
        GetPlaybackTokenResponse getPlaybackTokenResponse = (GetPlaybackTokenResponse) obj;
        return internalEqualityCheck(getPlaybackToken(), getPlaybackTokenResponse.getPlaybackToken()) && internalEqualityCheck(getExpiration(), getPlaybackTokenResponse.getExpiration());
    }

    public Date getExpiration() {
        return this.expiration;
    }

    public String getPlaybackToken() {
        return this.playbackToken;
    }

    public int hashCode() {
        return internalHashCodeCompute(Integer.valueOf(classNameHashCode), getPlaybackToken(), getExpiration());
    }

    public void setExpiration(Date date) {
        this.expiration = date;
    }

    public void setPlaybackToken(String str) {
        this.playbackToken = str;
    }
}
