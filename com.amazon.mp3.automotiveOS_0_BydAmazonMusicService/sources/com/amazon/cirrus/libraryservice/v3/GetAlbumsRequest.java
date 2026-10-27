package com.amazon.cirrus.libraryservice.v3;

import com.amazon.cirrus.shared.model.CirrusBaseRequestV2;
import com.amazon.musicidentityservice.shared.model.MusicRequestIdentityContext;
import java.util.Arrays;

/* JADX INFO: loaded from: classes2.dex */
public class GetAlbumsRequest extends BaseGetRequest {
    private static final int classNameHashCode = internalHashCodeCompute("com.amazon.cirrus.libraryservice.v3.GetAlbumsRequest");
    private MusicRequestIdentityContext musicRequestIdentityContext;

    private static boolean internalEqualityCheck(Object obj, Object obj2) {
        return obj == obj2 || (obj != null && obj.equals(obj2));
    }

    private static int internalHashCodeCompute(Object... objArr) {
        return Arrays.hashCode(objArr);
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.amazon.cirrus.libraryservice.v3.BaseGetRequest, com.amazon.cirrus.shared.model.CirrusBaseRequestV2, java.lang.Comparable
    @Deprecated
    public int compareTo(@Deprecated CirrusBaseRequestV2 cirrusBaseRequestV2) {
        if (cirrusBaseRequestV2 == null) {
            return -1;
        }
        if (cirrusBaseRequestV2 == this) {
            return 0;
        }
        if (!(cirrusBaseRequestV2 instanceof GetAlbumsRequest)) {
            return 1;
        }
        MusicRequestIdentityContext musicRequestIdentityContext = getMusicRequestIdentityContext();
        MusicRequestIdentityContext musicRequestIdentityContext2 = ((GetAlbumsRequest) cirrusBaseRequestV2).getMusicRequestIdentityContext();
        if (musicRequestIdentityContext != musicRequestIdentityContext2) {
            if (musicRequestIdentityContext == null) {
                return -1;
            }
            if (musicRequestIdentityContext2 == null) {
                return 1;
            }
            int iCompareTo = musicRequestIdentityContext.compareTo(musicRequestIdentityContext2);
            if (iCompareTo != 0) {
                return iCompareTo;
            }
        }
        return super.compareTo(cirrusBaseRequestV2);
    }

    @Override // com.amazon.cirrus.libraryservice.v3.BaseGetRequest, com.amazon.cirrus.shared.model.CirrusBaseRequestV2
    public boolean equals(Object obj) {
        if (obj instanceof GetAlbumsRequest) {
            return super.equals(obj) && internalEqualityCheck(getMusicRequestIdentityContext(), ((GetAlbumsRequest) obj).getMusicRequestIdentityContext());
        }
        return false;
    }

    public MusicRequestIdentityContext getMusicRequestIdentityContext() {
        return this.musicRequestIdentityContext;
    }

    @Override // com.amazon.cirrus.libraryservice.v3.BaseGetRequest, com.amazon.cirrus.shared.model.CirrusBaseRequestV2
    public int hashCode() {
        return internalHashCodeCompute(Integer.valueOf(super.hashCode()), Integer.valueOf(classNameHashCode), getMusicRequestIdentityContext());
    }
}
