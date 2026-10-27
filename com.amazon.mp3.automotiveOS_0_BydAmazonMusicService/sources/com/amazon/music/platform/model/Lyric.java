package com.amazon.music.platform.model;

import java.util.Arrays;

/* JADX INFO: loaded from: classes4.dex */
public abstract class Lyric extends Document {
    private static final int classNameHashCode = internalHashCodeCompute("com.amazon.music.platform.model.Lyric");
    private String dmid;
    private String isrc;
    private String marketplaceId;
    private String trackAsin;
    private String trackTitle;

    private static boolean internalEqualityCheck(Object obj, Object obj2) {
        return obj == obj2 || (obj != null && obj.equals(obj2));
    }

    private static int internalHashCodeCompute(Object... objArr) {
        return Arrays.hashCode(objArr);
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.amazon.music.platform.model.Document, java.lang.Comparable
    @Deprecated
    public int compareTo(@Deprecated Document document) {
        if (document == null) {
            return -1;
        }
        if (document == this) {
            return 0;
        }
        if (!(document instanceof Lyric)) {
            return 1;
        }
        Lyric lyric = (Lyric) document;
        String marketplaceId = getMarketplaceId();
        String marketplaceId2 = lyric.getMarketplaceId();
        if (marketplaceId != marketplaceId2) {
            if (marketplaceId == null) {
                return -1;
            }
            if (marketplaceId2 == null) {
                return 1;
            }
            int iCompareTo = marketplaceId.compareTo(marketplaceId2);
            if (iCompareTo != 0) {
                return iCompareTo;
            }
        }
        String dmid = getDmid();
        String dmid2 = lyric.getDmid();
        if (dmid != dmid2) {
            if (dmid == null) {
                return -1;
            }
            if (dmid2 == null) {
                return 1;
            }
            int iCompareTo2 = dmid.compareTo(dmid2);
            if (iCompareTo2 != 0) {
                return iCompareTo2;
            }
        }
        String isrc = getIsrc();
        String isrc2 = lyric.getIsrc();
        if (isrc != isrc2) {
            if (isrc == null) {
                return -1;
            }
            if (isrc2 == null) {
                return 1;
            }
            int iCompareTo3 = isrc.compareTo(isrc2);
            if (iCompareTo3 != 0) {
                return iCompareTo3;
            }
        }
        String trackAsin = getTrackAsin();
        String trackAsin2 = lyric.getTrackAsin();
        if (trackAsin != trackAsin2) {
            if (trackAsin == null) {
                return -1;
            }
            if (trackAsin2 == null) {
                return 1;
            }
            int iCompareTo4 = trackAsin.compareTo(trackAsin2);
            if (iCompareTo4 != 0) {
                return iCompareTo4;
            }
        }
        String trackTitle = getTrackTitle();
        String trackTitle2 = lyric.getTrackTitle();
        if (trackTitle != trackTitle2) {
            if (trackTitle == null) {
                return -1;
            }
            if (trackTitle2 == null) {
                return 1;
            }
            int iCompareTo5 = trackTitle.compareTo(trackTitle2);
            if (iCompareTo5 != 0) {
                return iCompareTo5;
            }
        }
        return super.compareTo(document);
    }

    @Override // com.amazon.music.platform.model.Document
    public boolean equals(Object obj) {
        if (!(obj instanceof Lyric)) {
            return false;
        }
        Lyric lyric = (Lyric) obj;
        return super.equals(obj) && internalEqualityCheck(getMarketplaceId(), lyric.getMarketplaceId()) && internalEqualityCheck(getDmid(), lyric.getDmid()) && internalEqualityCheck(getIsrc(), lyric.getIsrc()) && internalEqualityCheck(getTrackAsin(), lyric.getTrackAsin()) && internalEqualityCheck(getTrackTitle(), lyric.getTrackTitle());
    }

    public String getDmid() {
        return this.dmid;
    }

    public String getIsrc() {
        return this.isrc;
    }

    public String getMarketplaceId() {
        return this.marketplaceId;
    }

    public String getTrackAsin() {
        return this.trackAsin;
    }

    public String getTrackTitle() {
        return this.trackTitle;
    }

    @Override // com.amazon.music.platform.model.Document
    public int hashCode() {
        return internalHashCodeCompute(Integer.valueOf(super.hashCode()), Integer.valueOf(classNameHashCode), getMarketplaceId(), getDmid(), getIsrc(), getTrackAsin(), getTrackTitle());
    }

    public void setDmid(String str) {
        this.dmid = str;
    }

    public void setIsrc(String str) {
        this.isrc = str;
    }

    public void setMarketplaceId(String str) {
        this.marketplaceId = str;
    }

    public void setTrackAsin(String str) {
        this.trackAsin = str;
    }

    public void setTrackTitle(String str) {
        this.trackTitle = str;
    }
}
