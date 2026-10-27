package com.amazon.nimblymusicservice;

import com.amazon.cirrus.shared.model.CirrusBaseRequestV2;
import java.util.Arrays;

/* JADX INFO: loaded from: classes5.dex */
public class BaseRequest extends MarketplaceIdRequest {
    private static final int classNameHashCode = internalHashCodeCompute("com.amazon.nimblymusicservice.BaseRequest");
    private String musicTerritory;

    private static boolean internalEqualityCheck(Object obj, Object obj2) {
        return obj == obj2 || (obj != null && obj.equals(obj2));
    }

    private static int internalHashCodeCompute(Object... objArr) {
        return Arrays.hashCode(objArr);
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.amazon.nimblymusicservice.MarketplaceIdRequest, com.amazon.cirrus.shared.model.CirrusBaseRequestV2, java.lang.Comparable
    @Deprecated
    public int compareTo(@Deprecated CirrusBaseRequestV2 cirrusBaseRequestV2) {
        if (cirrusBaseRequestV2 == null) {
            return -1;
        }
        if (cirrusBaseRequestV2 == this) {
            return 0;
        }
        if (!(cirrusBaseRequestV2 instanceof BaseRequest)) {
            return 1;
        }
        String musicTerritory = getMusicTerritory();
        String musicTerritory2 = ((BaseRequest) cirrusBaseRequestV2).getMusicTerritory();
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
        return super.compareTo(cirrusBaseRequestV2);
    }

    @Override // com.amazon.nimblymusicservice.MarketplaceIdRequest, com.amazon.cirrus.shared.model.CirrusBaseRequestV2
    public boolean equals(Object obj) {
        if (obj instanceof BaseRequest) {
            return super.equals(obj) && internalEqualityCheck(getMusicTerritory(), ((BaseRequest) obj).getMusicTerritory());
        }
        return false;
    }

    public String getMusicTerritory() {
        return this.musicTerritory;
    }

    @Override // com.amazon.nimblymusicservice.MarketplaceIdRequest, com.amazon.cirrus.shared.model.CirrusBaseRequestV2
    public int hashCode() {
        return internalHashCodeCompute(Integer.valueOf(super.hashCode()), Integer.valueOf(classNameHashCode), getMusicTerritory());
    }

    public void setMusicTerritory(String str) {
        this.musicTerritory = str;
    }
}
