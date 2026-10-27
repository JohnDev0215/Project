package com.amazon.musicensembleservice;

import com.amazon.layout.music.model.Root;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

/* JADX INFO: loaded from: classes2.dex */
public class GetHomeResponse extends Root {
    private static final int classNameHashCode = internalHashCodeCompute("com.amazon.musicensembleservice.GetHomeResponse");
    private Map<String, String> campaignIds;
    private List<Error> errors;

    private static boolean internalEqualityCheck(Object obj, Object obj2) {
        return obj == obj2 || (obj != null && obj.equals(obj2));
    }

    private static int internalHashCodeCompute(Object... objArr) {
        return Arrays.hashCode(objArr);
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.amazon.layout.music.model.Root, java.lang.Comparable
    @Deprecated
    public int compareTo(@Deprecated Root root) {
        if (root == null) {
            return -1;
        }
        if (root == this) {
            return 0;
        }
        if (!(root instanceof GetHomeResponse)) {
            return 1;
        }
        GetHomeResponse getHomeResponse = (GetHomeResponse) root;
        List<Error> errors = getErrors();
        List<Error> errors2 = getHomeResponse.getErrors();
        if (errors != errors2) {
            if (errors == null) {
                return -1;
            }
            if (errors2 == null) {
                return 1;
            }
            if (errors instanceof Comparable) {
                int iCompareTo = ((Comparable) errors).compareTo(errors2);
                if (iCompareTo != 0) {
                    return iCompareTo;
                }
            } else if (!errors.equals(errors2)) {
                int iHashCode = errors.hashCode();
                int iHashCode2 = errors2.hashCode();
                if (iHashCode < iHashCode2) {
                    return -1;
                }
                if (iHashCode > iHashCode2) {
                    return 1;
                }
            }
        }
        Map<String, String> campaignIds = getCampaignIds();
        Map<String, String> campaignIds2 = getHomeResponse.getCampaignIds();
        if (campaignIds != campaignIds2) {
            if (campaignIds == null) {
                return -1;
            }
            if (campaignIds2 == null) {
                return 1;
            }
            if (campaignIds instanceof Comparable) {
                int iCompareTo2 = ((Comparable) campaignIds).compareTo(campaignIds2);
                if (iCompareTo2 != 0) {
                    return iCompareTo2;
                }
            } else if (!campaignIds.equals(campaignIds2)) {
                int iHashCode3 = campaignIds.hashCode();
                int iHashCode4 = campaignIds2.hashCode();
                if (iHashCode3 < iHashCode4) {
                    return -1;
                }
                if (iHashCode3 > iHashCode4) {
                    return 1;
                }
            }
        }
        return super.compareTo(root);
    }

    @Override // com.amazon.layout.music.model.Root
    public boolean equals(Object obj) {
        if (!(obj instanceof GetHomeResponse)) {
            return false;
        }
        GetHomeResponse getHomeResponse = (GetHomeResponse) obj;
        return super.equals(obj) && internalEqualityCheck(getErrors(), getHomeResponse.getErrors()) && internalEqualityCheck(getCampaignIds(), getHomeResponse.getCampaignIds());
    }

    public Map<String, String> getCampaignIds() {
        return this.campaignIds;
    }

    public List<Error> getErrors() {
        return this.errors;
    }

    @Override // com.amazon.layout.music.model.Root
    public int hashCode() {
        return internalHashCodeCompute(Integer.valueOf(super.hashCode()), Integer.valueOf(classNameHashCode), getErrors(), getCampaignIds());
    }

    public void setCampaignIds(Map<String, String> map) {
        this.campaignIds = map;
    }

    public void setErrors(List<Error> list) {
        this.errors = list;
    }
}
