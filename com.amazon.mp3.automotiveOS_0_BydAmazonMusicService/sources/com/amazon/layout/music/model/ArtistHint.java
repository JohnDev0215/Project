package com.amazon.layout.music.model;

import java.util.Arrays;

/* JADX INFO: loaded from: classes3.dex */
public class ArtistHint extends Hint {
    private static final int classNameHashCode = internalHashCodeCompute("com.amazon.layout.music.model.ArtistHint");
    private String artistImageUrl;
    private String asin;
    private Boolean isFollowed;
    private Boolean isRecommended;
    private String recentlyPlayedEntityType;
    private Long timeStamp;
    private String title;

    private static boolean internalEqualityCheck(Object obj, Object obj2) {
        return obj == obj2 || (obj != null && obj.equals(obj2));
    }

    private static int internalHashCodeCompute(Object... objArr) {
        return Arrays.hashCode(objArr);
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.amazon.layout.music.model.Hint, java.lang.Comparable
    @Deprecated
    public int compareTo(@Deprecated Hint hint) {
        if (hint == null) {
            return -1;
        }
        if (hint == this) {
            return 0;
        }
        if (!(hint instanceof ArtistHint)) {
            return 1;
        }
        ArtistHint artistHint = (ArtistHint) hint;
        Long timeStamp = getTimeStamp();
        Long timeStamp2 = artistHint.getTimeStamp();
        if (timeStamp != timeStamp2) {
            if (timeStamp == null) {
                return -1;
            }
            if (timeStamp2 == null) {
                return 1;
            }
            int iCompareTo = timeStamp.compareTo(timeStamp2);
            if (iCompareTo != 0) {
                return iCompareTo;
            }
        }
        String recentlyPlayedEntityType = getRecentlyPlayedEntityType();
        String recentlyPlayedEntityType2 = artistHint.getRecentlyPlayedEntityType();
        if (recentlyPlayedEntityType != recentlyPlayedEntityType2) {
            if (recentlyPlayedEntityType == null) {
                return -1;
            }
            if (recentlyPlayedEntityType2 == null) {
                return 1;
            }
            int iCompareTo2 = recentlyPlayedEntityType.compareTo(recentlyPlayedEntityType2);
            if (iCompareTo2 != 0) {
                return iCompareTo2;
            }
        }
        String title = getTitle();
        String title2 = artistHint.getTitle();
        if (title != title2) {
            if (title == null) {
                return -1;
            }
            if (title2 == null) {
                return 1;
            }
            int iCompareTo3 = title.compareTo(title2);
            if (iCompareTo3 != 0) {
                return iCompareTo3;
            }
        }
        Boolean boolIsIsFollowed = isIsFollowed();
        Boolean boolIsIsFollowed2 = artistHint.isIsFollowed();
        if (boolIsIsFollowed != boolIsIsFollowed2) {
            if (boolIsIsFollowed == null) {
                return -1;
            }
            if (boolIsIsFollowed2 == null) {
                return 1;
            }
            int iCompareTo4 = boolIsIsFollowed.compareTo(boolIsIsFollowed2);
            if (iCompareTo4 != 0) {
                return iCompareTo4;
            }
        }
        String artistImageUrl = getArtistImageUrl();
        String artistImageUrl2 = artistHint.getArtistImageUrl();
        if (artistImageUrl != artistImageUrl2) {
            if (artistImageUrl == null) {
                return -1;
            }
            if (artistImageUrl2 == null) {
                return 1;
            }
            int iCompareTo5 = artistImageUrl.compareTo(artistImageUrl2);
            if (iCompareTo5 != 0) {
                return iCompareTo5;
            }
        }
        String asin = getAsin();
        String asin2 = artistHint.getAsin();
        if (asin != asin2) {
            if (asin == null) {
                return -1;
            }
            if (asin2 == null) {
                return 1;
            }
            int iCompareTo6 = asin.compareTo(asin2);
            if (iCompareTo6 != 0) {
                return iCompareTo6;
            }
        }
        Boolean boolIsIsRecommended = isIsRecommended();
        Boolean boolIsIsRecommended2 = artistHint.isIsRecommended();
        if (boolIsIsRecommended != boolIsIsRecommended2) {
            if (boolIsIsRecommended == null) {
                return -1;
            }
            if (boolIsIsRecommended2 == null) {
                return 1;
            }
            int iCompareTo7 = boolIsIsRecommended.compareTo(boolIsIsRecommended2);
            if (iCompareTo7 != 0) {
                return iCompareTo7;
            }
        }
        return super.compareTo(hint);
    }

    @Override // com.amazon.layout.music.model.Hint
    public boolean equals(Object obj) {
        if (!(obj instanceof ArtistHint)) {
            return false;
        }
        ArtistHint artistHint = (ArtistHint) obj;
        return super.equals(obj) && internalEqualityCheck(getTimeStamp(), artistHint.getTimeStamp()) && internalEqualityCheck(getRecentlyPlayedEntityType(), artistHint.getRecentlyPlayedEntityType()) && internalEqualityCheck(getTitle(), artistHint.getTitle()) && internalEqualityCheck(isIsFollowed(), artistHint.isIsFollowed()) && internalEqualityCheck(getArtistImageUrl(), artistHint.getArtistImageUrl()) && internalEqualityCheck(getAsin(), artistHint.getAsin()) && internalEqualityCheck(isIsRecommended(), artistHint.isIsRecommended());
    }

    public String getArtistImageUrl() {
        return this.artistImageUrl;
    }

    public String getAsin() {
        return this.asin;
    }

    public String getRecentlyPlayedEntityType() {
        return this.recentlyPlayedEntityType;
    }

    public Long getTimeStamp() {
        return this.timeStamp;
    }

    public String getTitle() {
        return this.title;
    }

    @Override // com.amazon.layout.music.model.Hint
    public int hashCode() {
        return internalHashCodeCompute(Integer.valueOf(super.hashCode()), Integer.valueOf(classNameHashCode), getTimeStamp(), getRecentlyPlayedEntityType(), getTitle(), isIsFollowed(), getArtistImageUrl(), getAsin(), isIsRecommended());
    }

    public Boolean isIsFollowed() {
        return this.isFollowed;
    }

    public Boolean isIsRecommended() {
        return this.isRecommended;
    }

    public void setArtistImageUrl(String str) {
        this.artistImageUrl = str;
    }

    public void setAsin(String str) {
        this.asin = str;
    }

    public void setIsFollowed(Boolean bool) {
        this.isFollowed = bool;
    }

    public void setIsRecommended(Boolean bool) {
        this.isRecommended = bool;
    }

    public void setRecentlyPlayedEntityType(String str) {
        this.recentlyPlayedEntityType = str;
    }

    public void setTimeStamp(Long l) {
        this.timeStamp = l;
    }

    public void setTitle(String str) {
        this.title = str;
    }
}
