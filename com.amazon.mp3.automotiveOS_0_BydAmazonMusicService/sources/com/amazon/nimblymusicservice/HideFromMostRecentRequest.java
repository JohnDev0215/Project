package com.amazon.nimblymusicservice;

import com.amazon.cirrus.shared.model.CirrusBaseRequestV2;
import com.amazon.musicidentityservice.shared.model.MusicRequestIdentityContext;
import java.util.Arrays;
import java.util.List;

/* JADX INFO: loaded from: classes5.dex */
public class HideFromMostRecentRequest extends CirrusBaseRequestV2 {
    private static final int classNameHashCode = internalHashCodeCompute("com.amazon.nimblymusicservice.HideFromMostRecentRequest");
    private List<NimblyHideFromMostRecent> hiddenRecentsList;
    private MusicRequestIdentityContext musicRequestIdentityContext;

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
        if (!(cirrusBaseRequestV2 instanceof HideFromMostRecentRequest)) {
            return 1;
        }
        HideFromMostRecentRequest hideFromMostRecentRequest = (HideFromMostRecentRequest) cirrusBaseRequestV2;
        List<NimblyHideFromMostRecent> hiddenRecentsList = getHiddenRecentsList();
        List<NimblyHideFromMostRecent> hiddenRecentsList2 = hideFromMostRecentRequest.getHiddenRecentsList();
        if (hiddenRecentsList != hiddenRecentsList2) {
            if (hiddenRecentsList == null) {
                return -1;
            }
            if (hiddenRecentsList2 == null) {
                return 1;
            }
            if (hiddenRecentsList instanceof Comparable) {
                int iCompareTo = ((Comparable) hiddenRecentsList).compareTo(hiddenRecentsList2);
                if (iCompareTo != 0) {
                    return iCompareTo;
                }
            } else if (!hiddenRecentsList.equals(hiddenRecentsList2)) {
                int iHashCode = hiddenRecentsList.hashCode();
                int iHashCode2 = hiddenRecentsList2.hashCode();
                if (iHashCode < iHashCode2) {
                    return -1;
                }
                if (iHashCode > iHashCode2) {
                    return 1;
                }
            }
        }
        MusicRequestIdentityContext musicRequestIdentityContext = getMusicRequestIdentityContext();
        MusicRequestIdentityContext musicRequestIdentityContext2 = hideFromMostRecentRequest.getMusicRequestIdentityContext();
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
        return super.compareTo(cirrusBaseRequestV2);
    }

    @Override // com.amazon.cirrus.shared.model.CirrusBaseRequestV2
    public boolean equals(Object obj) {
        if (!(obj instanceof HideFromMostRecentRequest)) {
            return false;
        }
        HideFromMostRecentRequest hideFromMostRecentRequest = (HideFromMostRecentRequest) obj;
        return super.equals(obj) && internalEqualityCheck(getHiddenRecentsList(), hideFromMostRecentRequest.getHiddenRecentsList()) && internalEqualityCheck(getMusicRequestIdentityContext(), hideFromMostRecentRequest.getMusicRequestIdentityContext());
    }

    public List<NimblyHideFromMostRecent> getHiddenRecentsList() {
        return this.hiddenRecentsList;
    }

    public MusicRequestIdentityContext getMusicRequestIdentityContext() {
        return this.musicRequestIdentityContext;
    }

    @Override // com.amazon.cirrus.shared.model.CirrusBaseRequestV2
    public int hashCode() {
        return internalHashCodeCompute(Integer.valueOf(super.hashCode()), Integer.valueOf(classNameHashCode), getHiddenRecentsList(), getMusicRequestIdentityContext());
    }
}
