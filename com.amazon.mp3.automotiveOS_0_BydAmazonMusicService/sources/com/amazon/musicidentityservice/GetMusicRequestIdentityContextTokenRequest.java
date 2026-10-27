package com.amazon.musicidentityservice;

import com.amazon.musicidentityservice.shared.model.MusicRequestIdentityHints;
import java.util.Arrays;

/* JADX INFO: loaded from: classes3.dex */
public class GetMusicRequestIdentityContextTokenRequest implements Comparable<GetMusicRequestIdentityContextTokenRequest> {
    private static final int classNameHashCode = internalHashCodeCompute("com.amazon.musicidentityservice.GetMusicRequestIdentityContextTokenRequest");
    private MusicRequestIdentityHints musicRequestIdentityHints;

    private static boolean internalEqualityCheck(Object obj, Object obj2) {
        return obj == obj2 || (obj != null && obj.equals(obj2));
    }

    private static int internalHashCodeCompute(Object... objArr) {
        return Arrays.hashCode(objArr);
    }

    @Override // java.lang.Comparable
    @Deprecated
    public int compareTo(@Deprecated GetMusicRequestIdentityContextTokenRequest getMusicRequestIdentityContextTokenRequest) {
        MusicRequestIdentityHints musicRequestIdentityHints;
        MusicRequestIdentityHints musicRequestIdentityHints2;
        if (getMusicRequestIdentityContextTokenRequest == null) {
            return -1;
        }
        if (getMusicRequestIdentityContextTokenRequest != this && (musicRequestIdentityHints = getMusicRequestIdentityHints()) != (musicRequestIdentityHints2 = getMusicRequestIdentityContextTokenRequest.getMusicRequestIdentityHints())) {
            if (musicRequestIdentityHints == null) {
                return -1;
            }
            if (musicRequestIdentityHints2 == null) {
                return 1;
            }
            int iCompareTo = musicRequestIdentityHints.compareTo(musicRequestIdentityHints2);
            if (iCompareTo != 0) {
                return iCompareTo;
            }
        }
        return 0;
    }

    public boolean equals(Object obj) {
        if (obj instanceof GetMusicRequestIdentityContextTokenRequest) {
            return internalEqualityCheck(getMusicRequestIdentityHints(), ((GetMusicRequestIdentityContextTokenRequest) obj).getMusicRequestIdentityHints());
        }
        return false;
    }

    public MusicRequestIdentityHints getMusicRequestIdentityHints() {
        return this.musicRequestIdentityHints;
    }

    public int hashCode() {
        return internalHashCodeCompute(Integer.valueOf(classNameHashCode), getMusicRequestIdentityHints());
    }

    public void setMusicRequestIdentityHints(MusicRequestIdentityHints musicRequestIdentityHints) {
        this.musicRequestIdentityHints = musicRequestIdentityHints;
    }
}
