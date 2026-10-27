package com.amazon.music.storeservice.model;

import java.util.Arrays;

/* JADX INFO: loaded from: classes4.dex */
public class ArtistItem implements Comparable<ArtistItem> {
    private static final int classNameHashCode = internalHashCodeCompute("com.amazon.music.storeservice.model.ArtistItem");
    private String asin;
    private String contributorAsin;
    private String dmid;
    private String globalAsin;
    private String imageFull;
    private Integer itemIndex;
    private String name;
    private String requestedAsin;
    private String type;

    private static boolean internalEqualityCheck(Object obj, Object obj2) {
        return obj == obj2 || (obj != null && obj.equals(obj2));
    }

    private static int internalHashCodeCompute(Object... objArr) {
        return Arrays.hashCode(objArr);
    }

    @Override // java.lang.Comparable
    @Deprecated
    public int compareTo(@Deprecated ArtistItem artistItem) {
        if (artistItem == null) {
            return -1;
        }
        if (artistItem == this) {
            return 0;
        }
        String globalAsin = getGlobalAsin();
        String globalAsin2 = artistItem.getGlobalAsin();
        if (globalAsin != globalAsin2) {
            if (globalAsin == null) {
                return -1;
            }
            if (globalAsin2 == null) {
                return 1;
            }
            int iCompareTo = globalAsin.compareTo(globalAsin2);
            if (iCompareTo != 0) {
                return iCompareTo;
            }
        }
        String name = getName();
        String name2 = artistItem.getName();
        if (name != name2) {
            if (name == null) {
                return -1;
            }
            if (name2 == null) {
                return 1;
            }
            int iCompareTo2 = name.compareTo(name2);
            if (iCompareTo2 != 0) {
                return iCompareTo2;
            }
        }
        String requestedAsin = getRequestedAsin();
        String requestedAsin2 = artistItem.getRequestedAsin();
        if (requestedAsin != requestedAsin2) {
            if (requestedAsin == null) {
                return -1;
            }
            if (requestedAsin2 == null) {
                return 1;
            }
            int iCompareTo3 = requestedAsin.compareTo(requestedAsin2);
            if (iCompareTo3 != 0) {
                return iCompareTo3;
            }
        }
        String contributorAsin = getContributorAsin();
        String contributorAsin2 = artistItem.getContributorAsin();
        if (contributorAsin != contributorAsin2) {
            if (contributorAsin == null) {
                return -1;
            }
            if (contributorAsin2 == null) {
                return 1;
            }
            int iCompareTo4 = contributorAsin.compareTo(contributorAsin2);
            if (iCompareTo4 != 0) {
                return iCompareTo4;
            }
        }
        String dmid = getDmid();
        String dmid2 = artistItem.getDmid();
        if (dmid != dmid2) {
            if (dmid == null) {
                return -1;
            }
            if (dmid2 == null) {
                return 1;
            }
            int iCompareTo5 = dmid.compareTo(dmid2);
            if (iCompareTo5 != 0) {
                return iCompareTo5;
            }
        }
        Integer itemIndex = getItemIndex();
        Integer itemIndex2 = artistItem.getItemIndex();
        if (itemIndex != itemIndex2) {
            if (itemIndex == null) {
                return -1;
            }
            if (itemIndex2 == null) {
                return 1;
            }
            int iCompareTo6 = itemIndex.compareTo(itemIndex2);
            if (iCompareTo6 != 0) {
                return iCompareTo6;
            }
        }
        String asin = getAsin();
        String asin2 = artistItem.getAsin();
        if (asin != asin2) {
            if (asin == null) {
                return -1;
            }
            if (asin2 == null) {
                return 1;
            }
            int iCompareTo7 = asin.compareTo(asin2);
            if (iCompareTo7 != 0) {
                return iCompareTo7;
            }
        }
        String type = getType();
        String type2 = artistItem.getType();
        if (type != type2) {
            if (type == null) {
                return -1;
            }
            if (type2 == null) {
                return 1;
            }
            int iCompareTo8 = type.compareTo(type2);
            if (iCompareTo8 != 0) {
                return iCompareTo8;
            }
        }
        String imageFull = getImageFull();
        String imageFull2 = artistItem.getImageFull();
        if (imageFull != imageFull2) {
            if (imageFull == null) {
                return -1;
            }
            if (imageFull2 == null) {
                return 1;
            }
            int iCompareTo9 = imageFull.compareTo(imageFull2);
            if (iCompareTo9 != 0) {
                return iCompareTo9;
            }
        }
        return 0;
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof ArtistItem)) {
            return false;
        }
        ArtistItem artistItem = (ArtistItem) obj;
        return internalEqualityCheck(getGlobalAsin(), artistItem.getGlobalAsin()) && internalEqualityCheck(getName(), artistItem.getName()) && internalEqualityCheck(getRequestedAsin(), artistItem.getRequestedAsin()) && internalEqualityCheck(getContributorAsin(), artistItem.getContributorAsin()) && internalEqualityCheck(getDmid(), artistItem.getDmid()) && internalEqualityCheck(getItemIndex(), artistItem.getItemIndex()) && internalEqualityCheck(getAsin(), artistItem.getAsin()) && internalEqualityCheck(getType(), artistItem.getType()) && internalEqualityCheck(getImageFull(), artistItem.getImageFull());
    }

    public String getAsin() {
        return this.asin;
    }

    public String getContributorAsin() {
        return this.contributorAsin;
    }

    public String getDmid() {
        return this.dmid;
    }

    public String getGlobalAsin() {
        return this.globalAsin;
    }

    public String getImageFull() {
        return this.imageFull;
    }

    public Integer getItemIndex() {
        return this.itemIndex;
    }

    public String getName() {
        return this.name;
    }

    public String getRequestedAsin() {
        return this.requestedAsin;
    }

    public String getType() {
        return this.type;
    }

    public int hashCode() {
        return internalHashCodeCompute(Integer.valueOf(classNameHashCode), getGlobalAsin(), getName(), getRequestedAsin(), getContributorAsin(), getDmid(), getItemIndex(), getAsin(), getType(), getImageFull());
    }

    public void setAsin(String str) {
        this.asin = str;
    }

    public void setContributorAsin(String str) {
        this.contributorAsin = str;
    }

    public void setDmid(String str) {
        this.dmid = str;
    }

    public void setGlobalAsin(String str) {
        this.globalAsin = str;
    }

    public void setImageFull(String str) {
        this.imageFull = str;
    }

    public void setItemIndex(Integer num) {
        this.itemIndex = num;
    }

    public void setName(String str) {
        this.name = str;
    }

    public void setRequestedAsin(String str) {
        this.requestedAsin = str;
    }

    public void setType(String str) {
        this.type = str;
    }
}
