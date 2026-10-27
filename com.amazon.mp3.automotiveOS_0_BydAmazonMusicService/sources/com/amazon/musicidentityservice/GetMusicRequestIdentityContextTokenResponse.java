package com.amazon.musicidentityservice;

import java.util.Arrays;

/* JADX INFO: loaded from: classes3.dex */
public class GetMusicRequestIdentityContextTokenResponse implements Comparable<GetMusicRequestIdentityContextTokenResponse> {
    private static final int classNameHashCode = internalHashCodeCompute("com.amazon.musicidentityservice.GetMusicRequestIdentityContextTokenResponse");
    private String musicRequestIdentityContextToken;

    private static boolean internalEqualityCheck(Object obj, Object obj2) {
        return obj == obj2 || (obj != null && obj.equals(obj2));
    }

    private static int internalHashCodeCompute(Object... objArr) {
        return Arrays.hashCode(objArr);
    }

    @Override // java.lang.Comparable
    @Deprecated
    public int compareTo(@Deprecated GetMusicRequestIdentityContextTokenResponse getMusicRequestIdentityContextTokenResponse) {
        String musicRequestIdentityContextToken;
        String musicRequestIdentityContextToken2;
        if (getMusicRequestIdentityContextTokenResponse == null) {
            return -1;
        }
        if (getMusicRequestIdentityContextTokenResponse != this && (musicRequestIdentityContextToken = getMusicRequestIdentityContextToken()) != (musicRequestIdentityContextToken2 = getMusicRequestIdentityContextTokenResponse.getMusicRequestIdentityContextToken())) {
            if (musicRequestIdentityContextToken == null) {
                return -1;
            }
            if (musicRequestIdentityContextToken2 == null) {
                return 1;
            }
            int iCompareTo = musicRequestIdentityContextToken.compareTo(musicRequestIdentityContextToken2);
            if (iCompareTo != 0) {
                return iCompareTo;
            }
        }
        return 0;
    }

    public boolean equals(Object obj) {
        if (obj instanceof GetMusicRequestIdentityContextTokenResponse) {
            return internalEqualityCheck(getMusicRequestIdentityContextToken(), ((GetMusicRequestIdentityContextTokenResponse) obj).getMusicRequestIdentityContextToken());
        }
        return false;
    }

    public String getMusicRequestIdentityContextToken() {
        return this.musicRequestIdentityContextToken;
    }

    public int hashCode() {
        return internalHashCodeCompute(Integer.valueOf(classNameHashCode), getMusicRequestIdentityContextToken());
    }
}
