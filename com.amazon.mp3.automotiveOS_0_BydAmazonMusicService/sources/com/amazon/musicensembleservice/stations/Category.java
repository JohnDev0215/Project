package com.amazon.musicensembleservice.stations;

import com.amazon.musicensembleservice.ContentInfo;
import java.util.Arrays;
import java.util.List;

/* JADX INFO: loaded from: classes2.dex */
public class Category implements Comparable<Category> {
    private static final int classNameHashCode = internalHashCodeCompute("com.amazon.musicensembleservice.stations.Category");
    private String browseId;
    private String categoryId;
    private ContentInfo contentInfo;
    private String foregroundImageUrl;
    private List<String> stationMapIds;
    private String title;

    private static boolean internalEqualityCheck(Object obj, Object obj2) {
        return obj == obj2 || (obj != null && obj.equals(obj2));
    }

    private static int internalHashCodeCompute(Object... objArr) {
        return Arrays.hashCode(objArr);
    }

    @Override // java.lang.Comparable
    @Deprecated
    public int compareTo(@Deprecated Category category) {
        if (category == null) {
            return -1;
        }
        if (category == this) {
            return 0;
        }
        String browseId = getBrowseId();
        String browseId2 = category.getBrowseId();
        if (browseId != browseId2) {
            if (browseId == null) {
                return -1;
            }
            if (browseId2 == null) {
                return 1;
            }
            int iCompareTo = browseId.compareTo(browseId2);
            if (iCompareTo != 0) {
                return iCompareTo;
            }
        }
        ContentInfo contentInfo = getContentInfo();
        ContentInfo contentInfo2 = category.getContentInfo();
        if (contentInfo != contentInfo2) {
            if (contentInfo == null) {
                return -1;
            }
            if (contentInfo2 == null) {
                return 1;
            }
            int iCompareTo2 = contentInfo.compareTo(contentInfo2);
            if (iCompareTo2 != 0) {
                return iCompareTo2;
            }
        }
        String categoryId = getCategoryId();
        String categoryId2 = category.getCategoryId();
        if (categoryId != categoryId2) {
            if (categoryId == null) {
                return -1;
            }
            if (categoryId2 == null) {
                return 1;
            }
            int iCompareTo3 = categoryId.compareTo(categoryId2);
            if (iCompareTo3 != 0) {
                return iCompareTo3;
            }
        }
        String foregroundImageUrl = getForegroundImageUrl();
        String foregroundImageUrl2 = category.getForegroundImageUrl();
        if (foregroundImageUrl != foregroundImageUrl2) {
            if (foregroundImageUrl == null) {
                return -1;
            }
            if (foregroundImageUrl2 == null) {
                return 1;
            }
            int iCompareTo4 = foregroundImageUrl.compareTo(foregroundImageUrl2);
            if (iCompareTo4 != 0) {
                return iCompareTo4;
            }
        }
        List<String> stationMapIds = getStationMapIds();
        List<String> stationMapIds2 = category.getStationMapIds();
        if (stationMapIds != stationMapIds2) {
            if (stationMapIds == null) {
                return -1;
            }
            if (stationMapIds2 == null) {
                return 1;
            }
            if (stationMapIds instanceof Comparable) {
                int iCompareTo5 = ((Comparable) stationMapIds).compareTo(stationMapIds2);
                if (iCompareTo5 != 0) {
                    return iCompareTo5;
                }
            } else if (!stationMapIds.equals(stationMapIds2)) {
                int iHashCode = stationMapIds.hashCode();
                int iHashCode2 = stationMapIds2.hashCode();
                if (iHashCode < iHashCode2) {
                    return -1;
                }
                if (iHashCode > iHashCode2) {
                    return 1;
                }
            }
        }
        String title = getTitle();
        String title2 = category.getTitle();
        if (title != title2) {
            if (title == null) {
                return -1;
            }
            if (title2 == null) {
                return 1;
            }
            int iCompareTo6 = title.compareTo(title2);
            if (iCompareTo6 != 0) {
                return iCompareTo6;
            }
        }
        return 0;
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof Category)) {
            return false;
        }
        Category category = (Category) obj;
        return internalEqualityCheck(getBrowseId(), category.getBrowseId()) && internalEqualityCheck(getContentInfo(), category.getContentInfo()) && internalEqualityCheck(getCategoryId(), category.getCategoryId()) && internalEqualityCheck(getForegroundImageUrl(), category.getForegroundImageUrl()) && internalEqualityCheck(getStationMapIds(), category.getStationMapIds()) && internalEqualityCheck(getTitle(), category.getTitle());
    }

    public String getBrowseId() {
        return this.browseId;
    }

    public String getCategoryId() {
        return this.categoryId;
    }

    public ContentInfo getContentInfo() {
        return this.contentInfo;
    }

    public String getForegroundImageUrl() {
        return this.foregroundImageUrl;
    }

    public List<String> getStationMapIds() {
        return this.stationMapIds;
    }

    public String getTitle() {
        return this.title;
    }

    public int hashCode() {
        return internalHashCodeCompute(Integer.valueOf(classNameHashCode), getBrowseId(), getContentInfo(), getCategoryId(), getForegroundImageUrl(), getStationMapIds(), getTitle());
    }

    public void setBrowseId(String str) {
        this.browseId = str;
    }

    public void setCategoryId(String str) {
        this.categoryId = str;
    }

    public void setContentInfo(ContentInfo contentInfo) {
        this.contentInfo = contentInfo;
    }

    public void setForegroundImageUrl(String str) {
        this.foregroundImageUrl = str;
    }

    public void setStationMapIds(List<String> list) {
        this.stationMapIds = list;
    }

    public void setTitle(String str) {
        this.title = str;
    }
}
