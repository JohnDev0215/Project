package com.amazon.cirrus.libraryservice.v3;

import com.amazon.cirrus.shared.model.CirrusBaseRequestV2;
import com.amazon.musicidentityservice.shared.model.MusicRequestIdentityContext;
import java.util.Arrays;
import java.util.List;

/* JADX INFO: loaded from: classes2.dex */
public class GetTracksByIdRequest extends CirrusBaseRequestV2 {
    private static final int classNameHashCode = internalHashCodeCompute("com.amazon.cirrus.libraryservice.v3.GetTracksByIdRequest");
    private List<String> attributeList;
    private boolean batchExceptions;
    private MusicRequestIdentityContext musicRequestIdentityContext;
    private List<String> trackIdList;

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
        if (!(cirrusBaseRequestV2 instanceof GetTracksByIdRequest)) {
            return 1;
        }
        GetTracksByIdRequest getTracksByIdRequest = (GetTracksByIdRequest) cirrusBaseRequestV2;
        List<String> trackIdList = getTrackIdList();
        List<String> trackIdList2 = getTracksByIdRequest.getTrackIdList();
        if (trackIdList != trackIdList2) {
            if (trackIdList == null) {
                return -1;
            }
            if (trackIdList2 == null) {
                return 1;
            }
            if (trackIdList instanceof Comparable) {
                int iCompareTo = ((Comparable) trackIdList).compareTo(trackIdList2);
                if (iCompareTo != 0) {
                    return iCompareTo;
                }
            } else if (!trackIdList.equals(trackIdList2)) {
                int iHashCode = trackIdList.hashCode();
                int iHashCode2 = trackIdList2.hashCode();
                if (iHashCode < iHashCode2) {
                    return -1;
                }
                if (iHashCode > iHashCode2) {
                    return 1;
                }
            }
        }
        if (!isBatchExceptions() && getTracksByIdRequest.isBatchExceptions()) {
            return -1;
        }
        if (isBatchExceptions() && !getTracksByIdRequest.isBatchExceptions()) {
            return 1;
        }
        MusicRequestIdentityContext musicRequestIdentityContext = getMusicRequestIdentityContext();
        MusicRequestIdentityContext musicRequestIdentityContext2 = getTracksByIdRequest.getMusicRequestIdentityContext();
        if (musicRequestIdentityContext != musicRequestIdentityContext2) {
            if (musicRequestIdentityContext == null) {
                return -1;
            }
            if (musicRequestIdentityContext2 == null) {
                return 1;
            }
            int iCompareTo2 = musicRequestIdentityContext.compareTo(musicRequestIdentityContext2);
            if (iCompareTo2 != 0) {
                return iCompareTo2;
            }
        }
        List<String> attributeList = getAttributeList();
        List<String> attributeList2 = getTracksByIdRequest.getAttributeList();
        if (attributeList != attributeList2) {
            if (attributeList == null) {
                return -1;
            }
            if (attributeList2 == null) {
                return 1;
            }
            if (attributeList instanceof Comparable) {
                int iCompareTo3 = ((Comparable) attributeList).compareTo(attributeList2);
                if (iCompareTo3 != 0) {
                    return iCompareTo3;
                }
            } else if (!attributeList.equals(attributeList2)) {
                int iHashCode3 = attributeList.hashCode();
                int iHashCode4 = attributeList2.hashCode();
                if (iHashCode3 < iHashCode4) {
                    return -1;
                }
                if (iHashCode3 > iHashCode4) {
                    return 1;
                }
            }
        }
        return super.compareTo(cirrusBaseRequestV2);
    }

    @Override // com.amazon.cirrus.shared.model.CirrusBaseRequestV2
    public boolean equals(Object obj) {
        if (!(obj instanceof GetTracksByIdRequest)) {
            return false;
        }
        GetTracksByIdRequest getTracksByIdRequest = (GetTracksByIdRequest) obj;
        return super.equals(obj) && internalEqualityCheck(getTrackIdList(), getTracksByIdRequest.getTrackIdList()) && internalEqualityCheck(Boolean.valueOf(isBatchExceptions()), Boolean.valueOf(getTracksByIdRequest.isBatchExceptions())) && internalEqualityCheck(getMusicRequestIdentityContext(), getTracksByIdRequest.getMusicRequestIdentityContext()) && internalEqualityCheck(getAttributeList(), getTracksByIdRequest.getAttributeList());
    }

    public List<String> getAttributeList() {
        return this.attributeList;
    }

    public MusicRequestIdentityContext getMusicRequestIdentityContext() {
        return this.musicRequestIdentityContext;
    }

    public List<String> getTrackIdList() {
        return this.trackIdList;
    }

    @Override // com.amazon.cirrus.shared.model.CirrusBaseRequestV2
    public int hashCode() {
        return internalHashCodeCompute(Integer.valueOf(super.hashCode()), Integer.valueOf(classNameHashCode), getTrackIdList(), Boolean.valueOf(isBatchExceptions()), getMusicRequestIdentityContext(), getAttributeList());
    }

    public boolean isBatchExceptions() {
        return this.batchExceptions;
    }
}
