package com.amazon.musicensembleservice.brush;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

/* JADX INFO: loaded from: classes2.dex */
public class PageResponse extends Page {
    private static final int classNameHashCode = internalHashCodeCompute("com.amazon.musicensembleservice.brush.PageResponse");
    private Map<String, String> campaignIds;
    private List<LogMessage> errors;
    private List<LogMessage> info;
    private List<LogMessage> warnings;

    private static boolean internalEqualityCheck(Object obj, Object obj2) {
        return obj == obj2 || (obj != null && obj.equals(obj2));
    }

    private static int internalHashCodeCompute(Object... objArr) {
        return Arrays.hashCode(objArr);
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.amazon.musicensembleservice.brush.Page, java.lang.Comparable
    @Deprecated
    public int compareTo(@Deprecated Page page) {
        if (page == null) {
            return -1;
        }
        if (page == this) {
            return 0;
        }
        if (!(page instanceof PageResponse)) {
            return 1;
        }
        PageResponse pageResponse = (PageResponse) page;
        List<LogMessage> errors = getErrors();
        List<LogMessage> errors2 = pageResponse.getErrors();
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
        List<LogMessage> info = getInfo();
        List<LogMessage> info2 = pageResponse.getInfo();
        if (info != info2) {
            if (info == null) {
                return -1;
            }
            if (info2 == null) {
                return 1;
            }
            if (info instanceof Comparable) {
                int iCompareTo2 = ((Comparable) info).compareTo(info2);
                if (iCompareTo2 != 0) {
                    return iCompareTo2;
                }
            } else if (!info.equals(info2)) {
                int iHashCode3 = info.hashCode();
                int iHashCode4 = info2.hashCode();
                if (iHashCode3 < iHashCode4) {
                    return -1;
                }
                if (iHashCode3 > iHashCode4) {
                    return 1;
                }
            }
        }
        List<LogMessage> warnings = getWarnings();
        List<LogMessage> warnings2 = pageResponse.getWarnings();
        if (warnings != warnings2) {
            if (warnings == null) {
                return -1;
            }
            if (warnings2 == null) {
                return 1;
            }
            if (warnings instanceof Comparable) {
                int iCompareTo3 = ((Comparable) warnings).compareTo(warnings2);
                if (iCompareTo3 != 0) {
                    return iCompareTo3;
                }
            } else if (!warnings.equals(warnings2)) {
                int iHashCode5 = warnings.hashCode();
                int iHashCode6 = warnings2.hashCode();
                if (iHashCode5 < iHashCode6) {
                    return -1;
                }
                if (iHashCode5 > iHashCode6) {
                    return 1;
                }
            }
        }
        Map<String, String> campaignIds = getCampaignIds();
        Map<String, String> campaignIds2 = pageResponse.getCampaignIds();
        if (campaignIds != campaignIds2) {
            if (campaignIds == null) {
                return -1;
            }
            if (campaignIds2 == null) {
                return 1;
            }
            if (campaignIds instanceof Comparable) {
                int iCompareTo4 = ((Comparable) campaignIds).compareTo(campaignIds2);
                if (iCompareTo4 != 0) {
                    return iCompareTo4;
                }
            } else if (!campaignIds.equals(campaignIds2)) {
                int iHashCode7 = campaignIds.hashCode();
                int iHashCode8 = campaignIds2.hashCode();
                if (iHashCode7 < iHashCode8) {
                    return -1;
                }
                if (iHashCode7 > iHashCode8) {
                    return 1;
                }
            }
        }
        return super.compareTo(page);
    }

    @Override // com.amazon.musicensembleservice.brush.Page
    public boolean equals(Object obj) {
        if (!(obj instanceof PageResponse)) {
            return false;
        }
        PageResponse pageResponse = (PageResponse) obj;
        return super.equals(obj) && internalEqualityCheck(getErrors(), pageResponse.getErrors()) && internalEqualityCheck(getInfo(), pageResponse.getInfo()) && internalEqualityCheck(getWarnings(), pageResponse.getWarnings()) && internalEqualityCheck(getCampaignIds(), pageResponse.getCampaignIds());
    }

    public Map<String, String> getCampaignIds() {
        return this.campaignIds;
    }

    public List<LogMessage> getErrors() {
        return this.errors;
    }

    public List<LogMessage> getInfo() {
        return this.info;
    }

    public List<LogMessage> getWarnings() {
        return this.warnings;
    }

    @Override // com.amazon.musicensembleservice.brush.Page
    public int hashCode() {
        return internalHashCodeCompute(Integer.valueOf(super.hashCode()), Integer.valueOf(classNameHashCode), getErrors(), getInfo(), getWarnings(), getCampaignIds());
    }

    public void setCampaignIds(Map<String, String> map) {
        this.campaignIds = map;
    }

    public void setErrors(List<LogMessage> list) {
        this.errors = list;
    }

    public void setInfo(List<LogMessage> list) {
        this.info = list;
    }

    public void setWarnings(List<LogMessage> list) {
        this.warnings = list;
    }
}
