package com.amazon.cirrus.libraryservice.v3;

import com.amazon.cirrus.shared.model.CirrusBaseRequestV2;
import com.amazon.musicidentityservice.shared.model.MusicRequestIdentityContext;
import java.util.Arrays;
import java.util.List;

/* JADX INFO: loaded from: classes2.dex */
public class ReportClientActionsRequest extends CirrusBaseRequestV2 {
    private static final int classNameHashCode = internalHashCodeCompute("com.amazon.cirrus.libraryservice.v3.ReportClientActionsRequest");
    private List<ClientAction> clientActionList;
    private MusicRequestIdentityContext musicRequestIdentityContext;
    private String musicRequestIdentityContextToken;

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
        if (!(cirrusBaseRequestV2 instanceof ReportClientActionsRequest)) {
            return 1;
        }
        ReportClientActionsRequest reportClientActionsRequest = (ReportClientActionsRequest) cirrusBaseRequestV2;
        String musicRequestIdentityContextToken = getMusicRequestIdentityContextToken();
        String musicRequestIdentityContextToken2 = reportClientActionsRequest.getMusicRequestIdentityContextToken();
        if (musicRequestIdentityContextToken != musicRequestIdentityContextToken2) {
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
        MusicRequestIdentityContext musicRequestIdentityContext = getMusicRequestIdentityContext();
        MusicRequestIdentityContext musicRequestIdentityContext2 = reportClientActionsRequest.getMusicRequestIdentityContext();
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
        List<ClientAction> clientActionList = getClientActionList();
        List<ClientAction> clientActionList2 = reportClientActionsRequest.getClientActionList();
        if (clientActionList != clientActionList2) {
            if (clientActionList == null) {
                return -1;
            }
            if (clientActionList2 == null) {
                return 1;
            }
            if (clientActionList instanceof Comparable) {
                int iCompareTo3 = ((Comparable) clientActionList).compareTo(clientActionList2);
                if (iCompareTo3 != 0) {
                    return iCompareTo3;
                }
            } else if (!clientActionList.equals(clientActionList2)) {
                int iHashCode = clientActionList.hashCode();
                int iHashCode2 = clientActionList2.hashCode();
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
        if (!(obj instanceof ReportClientActionsRequest)) {
            return false;
        }
        ReportClientActionsRequest reportClientActionsRequest = (ReportClientActionsRequest) obj;
        return super.equals(obj) && internalEqualityCheck(getMusicRequestIdentityContextToken(), reportClientActionsRequest.getMusicRequestIdentityContextToken()) && internalEqualityCheck(getMusicRequestIdentityContext(), reportClientActionsRequest.getMusicRequestIdentityContext()) && internalEqualityCheck(getClientActionList(), reportClientActionsRequest.getClientActionList());
    }

    public List<ClientAction> getClientActionList() {
        return this.clientActionList;
    }

    public MusicRequestIdentityContext getMusicRequestIdentityContext() {
        return this.musicRequestIdentityContext;
    }

    public String getMusicRequestIdentityContextToken() {
        return this.musicRequestIdentityContextToken;
    }

    @Override // com.amazon.cirrus.shared.model.CirrusBaseRequestV2
    public int hashCode() {
        return internalHashCodeCompute(Integer.valueOf(super.hashCode()), Integer.valueOf(classNameHashCode), getMusicRequestIdentityContextToken(), getMusicRequestIdentityContext(), getClientActionList());
    }

    public void setClientActionList(List<ClientAction> list) {
        this.clientActionList = list;
    }

    public void setMusicRequestIdentityContext(MusicRequestIdentityContext musicRequestIdentityContext) {
        this.musicRequestIdentityContext = musicRequestIdentityContext;
    }

    public void setMusicRequestIdentityContextToken(String str) {
        this.musicRequestIdentityContextToken = str;
    }
}
