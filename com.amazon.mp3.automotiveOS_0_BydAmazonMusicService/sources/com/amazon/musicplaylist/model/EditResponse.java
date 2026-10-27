package com.amazon.musicplaylist.model;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

/* JADX INFO: loaded from: classes5.dex */
public class EditResponse implements Comparable<EditResponse> {
    private static final int classNameHashCode = internalHashCodeCompute("com.amazon.musicplaylist.model.EditResponse");
    private Map<String, String> bannerArt;
    private String description;
    private Map<String, String> fourSquareArt;
    private List<String> socialSharingErrors;
    private String version;

    private static boolean internalEqualityCheck(Object obj, Object obj2) {
        return obj == obj2 || (obj != null && obj.equals(obj2));
    }

    private static int internalHashCodeCompute(Object... objArr) {
        return Arrays.hashCode(objArr);
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // java.lang.Comparable
    @Deprecated
    public int compareTo(@Deprecated EditResponse editResponse) {
        if (editResponse == null) {
            return -1;
        }
        if (editResponse == this) {
            return 0;
        }
        String version = getVersion();
        String version2 = editResponse.getVersion();
        if (version != version2) {
            if (version == null) {
                return -1;
            }
            if (version2 == null) {
                return 1;
            }
            int iCompareTo = version.compareTo(version2);
            if (iCompareTo != 0) {
                return iCompareTo;
            }
        }
        Map<String, String> fourSquareArt = getFourSquareArt();
        Map<String, String> fourSquareArt2 = editResponse.getFourSquareArt();
        if (fourSquareArt != fourSquareArt2) {
            if (fourSquareArt == null) {
                return -1;
            }
            if (fourSquareArt2 == null) {
                return 1;
            }
            if (fourSquareArt instanceof Comparable) {
                int iCompareTo2 = ((Comparable) fourSquareArt).compareTo(fourSquareArt2);
                if (iCompareTo2 != 0) {
                    return iCompareTo2;
                }
            } else if (!fourSquareArt.equals(fourSquareArt2)) {
                int iHashCode = fourSquareArt.hashCode();
                int iHashCode2 = fourSquareArt2.hashCode();
                if (iHashCode < iHashCode2) {
                    return -1;
                }
                if (iHashCode > iHashCode2) {
                    return 1;
                }
            }
        }
        String description = getDescription();
        String description2 = editResponse.getDescription();
        if (description != description2) {
            if (description == null) {
                return -1;
            }
            if (description2 == null) {
                return 1;
            }
            int iCompareTo3 = description.compareTo(description2);
            if (iCompareTo3 != 0) {
                return iCompareTo3;
            }
        }
        List<String> socialSharingErrors = getSocialSharingErrors();
        List<String> socialSharingErrors2 = editResponse.getSocialSharingErrors();
        if (socialSharingErrors != socialSharingErrors2) {
            if (socialSharingErrors == null) {
                return -1;
            }
            if (socialSharingErrors2 == null) {
                return 1;
            }
            if (socialSharingErrors instanceof Comparable) {
                int iCompareTo4 = ((Comparable) socialSharingErrors).compareTo(socialSharingErrors2);
                if (iCompareTo4 != 0) {
                    return iCompareTo4;
                }
            } else if (!socialSharingErrors.equals(socialSharingErrors2)) {
                int iHashCode3 = socialSharingErrors.hashCode();
                int iHashCode4 = socialSharingErrors2.hashCode();
                if (iHashCode3 < iHashCode4) {
                    return -1;
                }
                if (iHashCode3 > iHashCode4) {
                    return 1;
                }
            }
        }
        Map<String, String> bannerArt = getBannerArt();
        Map<String, String> bannerArt2 = editResponse.getBannerArt();
        if (bannerArt != bannerArt2) {
            if (bannerArt == null) {
                return -1;
            }
            if (bannerArt2 == null) {
                return 1;
            }
            if (bannerArt instanceof Comparable) {
                int iCompareTo5 = ((Comparable) bannerArt).compareTo(bannerArt2);
                if (iCompareTo5 != 0) {
                    return iCompareTo5;
                }
            } else if (!bannerArt.equals(bannerArt2)) {
                int iHashCode5 = bannerArt.hashCode();
                int iHashCode6 = bannerArt2.hashCode();
                if (iHashCode5 < iHashCode6) {
                    return -1;
                }
                if (iHashCode5 > iHashCode6) {
                    return 1;
                }
            }
        }
        return 0;
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof EditResponse)) {
            return false;
        }
        EditResponse editResponse = (EditResponse) obj;
        return internalEqualityCheck(getVersion(), editResponse.getVersion()) && internalEqualityCheck(getFourSquareArt(), editResponse.getFourSquareArt()) && internalEqualityCheck(getDescription(), editResponse.getDescription()) && internalEqualityCheck(getSocialSharingErrors(), editResponse.getSocialSharingErrors()) && internalEqualityCheck(getBannerArt(), editResponse.getBannerArt());
    }

    public Map<String, String> getBannerArt() {
        return this.bannerArt;
    }

    public String getDescription() {
        return this.description;
    }

    public Map<String, String> getFourSquareArt() {
        return this.fourSquareArt;
    }

    public List<String> getSocialSharingErrors() {
        return this.socialSharingErrors;
    }

    public String getVersion() {
        return this.version;
    }

    public int hashCode() {
        return internalHashCodeCompute(Integer.valueOf(classNameHashCode), getVersion(), getFourSquareArt(), getDescription(), getSocialSharingErrors(), getBannerArt());
    }

    public void setBannerArt(Map<String, String> map) {
        this.bannerArt = map;
    }

    public void setDescription(String str) {
        this.description = str;
    }

    public void setFourSquareArt(Map<String, String> map) {
        this.fourSquareArt = map;
    }

    public void setSocialSharingErrors(List<String> list) {
        this.socialSharingErrors = list;
    }

    public void setVersion(String str) {
        this.version = str;
    }
}
