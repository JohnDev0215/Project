package com.amazon.nimblymusicservice;

import com.amazon.cirrus.shared.model.CirrusBaseRequestV2;
import com.amazon.musicidentityservice.shared.model.MusicRequestIdentityContext;
import java.util.Arrays;
import java.util.List;

/* JADX INFO: loaded from: classes5.dex */
public class HideFromRecentTrackActivityRequest extends CirrusBaseRequestV2 {
    private static final int classNameHashCode = internalHashCodeCompute("com.amazon.nimblymusicservice.HideFromRecentTrackActivityRequest");
    private MusicRequestIdentityContext musicRequestIdentityContext;
    private List<String> uniqueIdentifiers;

    private static boolean internalEqualityCheck(Object obj, Object obj2) {
        return obj == obj2 || (obj != null && obj.equals(obj2));
    }

    private static int internalHashCodeCompute(Object... objArr) {
        return Arrays.hashCode(objArr);
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.amazon.cirrus.shared.model.CirrusBaseRequestV2, java.lang.Comparable
    @Deprecated
    public int compareTo(@Deprecated CirrusBaseRequestV2 cirrusBaseRequestV2) {
        if (cirrusBaseRequestV2 == null) {
            return -1;
        }
        if (cirrusBaseRequestV2 == this) {
            return 0;
        }
        if (!(cirrusBaseRequestV2 instanceof HideFromRecentTrackActivityRequest)) {
            return 1;
        }
        HideFromRecentTrackActivityRequest hideFromRecentTrackActivityRequest = (HideFromRecentTrackActivityRequest) cirrusBaseRequestV2;
        MusicRequestIdentityContext musicRequestIdentityContext = getMusicRequestIdentityContext();
        MusicRequestIdentityContext musicRequestIdentityContext2 = hideFromRecentTrackActivityRequest.getMusicRequestIdentityContext();
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
        List<String> uniqueIdentifiers = getUniqueIdentifiers();
        List<String> uniqueIdentifiers2 = hideFromRecentTrackActivityRequest.getUniqueIdentifiers();
        if (uniqueIdentifiers != uniqueIdentifiers2) {
            if (uniqueIdentifiers == null) {
                return -1;
            }
            if (uniqueIdentifiers2 == null) {
                return 1;
            }
            if (uniqueIdentifiers instanceof Comparable) {
                int iCompareTo2 = ((Comparable) uniqueIdentifiers).compareTo(uniqueIdentifiers2);
                if (iCompareTo2 != 0) {
                    return iCompareTo2;
                }
            } else if (!uniqueIdentifiers.equals(uniqueIdentifiers2)) {
                int iHashCode = uniqueIdentifiers.hashCode();
                int iHashCode2 = uniqueIdentifiers2.hashCode();
                if (iHashCode < iHashCode2) {
                    return -1;
                }
                if (iHashCode > iHashCode2) {
                    return 1;
                }
            }
        }
        return super.compareTo(cirrusBaseRequestV2);
    }

    @Override // com.amazon.cirrus.shared.model.CirrusBaseRequestV2
    public boolean equals(Object obj) {
        if (!(obj instanceof HideFromRecentTrackActivityRequest)) {
            return false;
        }
        HideFromRecentTrackActivityRequest hideFromRecentTrackActivityRequest = (HideFromRecentTrackActivityRequest) obj;
        return super.equals(obj) && internalEqualityCheck(getMusicRequestIdentityContext(), hideFromRecentTrackActivityRequest.getMusicRequestIdentityContext()) && internalEqualityCheck(getUniqueIdentifiers(), hideFromRecentTrackActivityRequest.getUniqueIdentifiers());
    }

    public MusicRequestIdentityContext getMusicRequestIdentityContext() {
        return this.musicRequestIdentityContext;
    }

    public List<String> getUniqueIdentifiers() {
        return this.uniqueIdentifiers;
    }

    @Override // com.amazon.cirrus.shared.model.CirrusBaseRequestV2
    public int hashCode() {
        return internalHashCodeCompute(Integer.valueOf(super.hashCode()), Integer.valueOf(classNameHashCode), getMusicRequestIdentityContext(), getUniqueIdentifiers());
    }
}
