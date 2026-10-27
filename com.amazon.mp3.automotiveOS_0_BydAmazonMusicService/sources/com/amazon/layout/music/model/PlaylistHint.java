package com.amazon.layout.music.model;

import com.amazon.musicensembleservice.PlaymodeEligibility;
import java.util.Arrays;
import java.util.List;

/* JADX INFO: loaded from: classes3.dex */
public class PlaylistHint extends Hint {
    private static final int classNameHashCode = internalHashCodeCompute("com.amazon.layout.music.model.PlaylistHint");
    private String albumArtImageUrl;
    private String asin;
    private List<String> contentTypes;
    private String curatedBy;
    private String description;
    private Boolean isMusicSubscription;
    private Boolean isPrime;
    private Boolean isRecommended;
    private Boolean isSonicRush;
    private Boolean isSonicRushOnDemandPlayable;
    private String languagesOfPerformance;
    private PlaymodeEligibility playmodeEligibility;
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
        if (!(hint instanceof PlaylistHint)) {
            return 1;
        }
        PlaylistHint playlistHint = (PlaylistHint) hint;
        Boolean boolIsIsSonicRush = isIsSonicRush();
        Boolean boolIsIsSonicRush2 = playlistHint.isIsSonicRush();
        if (boolIsIsSonicRush != boolIsIsSonicRush2) {
            if (boolIsIsSonicRush == null) {
                return -1;
            }
            if (boolIsIsSonicRush2 == null) {
                return 1;
            }
            int iCompareTo = boolIsIsSonicRush.compareTo(boolIsIsSonicRush2);
            if (iCompareTo != 0) {
                return iCompareTo;
            }
        }
        List<String> contentTypes = getContentTypes();
        List<String> contentTypes2 = playlistHint.getContentTypes();
        if (contentTypes != contentTypes2) {
            if (contentTypes == null) {
                return -1;
            }
            if (contentTypes2 == null) {
                return 1;
            }
            if (contentTypes instanceof Comparable) {
                int iCompareTo2 = ((Comparable) contentTypes).compareTo(contentTypes2);
                if (iCompareTo2 != 0) {
                    return iCompareTo2;
                }
            } else if (!contentTypes.equals(contentTypes2)) {
                int iHashCode = contentTypes.hashCode();
                int iHashCode2 = contentTypes2.hashCode();
                if (iHashCode < iHashCode2) {
                    return -1;
                }
                if (iHashCode > iHashCode2) {
                    return 1;
                }
            }
        }
        String languagesOfPerformance = getLanguagesOfPerformance();
        String languagesOfPerformance2 = playlistHint.getLanguagesOfPerformance();
        if (languagesOfPerformance != languagesOfPerformance2) {
            if (languagesOfPerformance == null) {
                return -1;
            }
            if (languagesOfPerformance2 == null) {
                return 1;
            }
            int iCompareTo3 = languagesOfPerformance.compareTo(languagesOfPerformance2);
            if (iCompareTo3 != 0) {
                return iCompareTo3;
            }
        }
        String curatedBy = getCuratedBy();
        String curatedBy2 = playlistHint.getCuratedBy();
        if (curatedBy != curatedBy2) {
            if (curatedBy == null) {
                return -1;
            }
            if (curatedBy2 == null) {
                return 1;
            }
            int iCompareTo4 = curatedBy.compareTo(curatedBy2);
            if (iCompareTo4 != 0) {
                return iCompareTo4;
            }
        }
        Boolean boolIsIsMusicSubscription = isIsMusicSubscription();
        Boolean boolIsIsMusicSubscription2 = playlistHint.isIsMusicSubscription();
        if (boolIsIsMusicSubscription != boolIsIsMusicSubscription2) {
            if (boolIsIsMusicSubscription == null) {
                return -1;
            }
            if (boolIsIsMusicSubscription2 == null) {
                return 1;
            }
            int iCompareTo5 = boolIsIsMusicSubscription.compareTo(boolIsIsMusicSubscription2);
            if (iCompareTo5 != 0) {
                return iCompareTo5;
            }
        }
        Long timeStamp = getTimeStamp();
        Long timeStamp2 = playlistHint.getTimeStamp();
        if (timeStamp != timeStamp2) {
            if (timeStamp == null) {
                return -1;
            }
            if (timeStamp2 == null) {
                return 1;
            }
            int iCompareTo6 = timeStamp.compareTo(timeStamp2);
            if (iCompareTo6 != 0) {
                return iCompareTo6;
            }
        }
        Boolean boolIsIsRecommended = isIsRecommended();
        Boolean boolIsIsRecommended2 = playlistHint.isIsRecommended();
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
        Boolean boolIsIsPrime = isIsPrime();
        Boolean boolIsIsPrime2 = playlistHint.isIsPrime();
        if (boolIsIsPrime != boolIsIsPrime2) {
            if (boolIsIsPrime == null) {
                return -1;
            }
            if (boolIsIsPrime2 == null) {
                return 1;
            }
            int iCompareTo8 = boolIsIsPrime.compareTo(boolIsIsPrime2);
            if (iCompareTo8 != 0) {
                return iCompareTo8;
            }
        }
        Boolean boolIsIsSonicRushOnDemandPlayable = isIsSonicRushOnDemandPlayable();
        Boolean boolIsIsSonicRushOnDemandPlayable2 = playlistHint.isIsSonicRushOnDemandPlayable();
        if (boolIsIsSonicRushOnDemandPlayable != boolIsIsSonicRushOnDemandPlayable2) {
            if (boolIsIsSonicRushOnDemandPlayable == null) {
                return -1;
            }
            if (boolIsIsSonicRushOnDemandPlayable2 == null) {
                return 1;
            }
            int iCompareTo9 = boolIsIsSonicRushOnDemandPlayable.compareTo(boolIsIsSonicRushOnDemandPlayable2);
            if (iCompareTo9 != 0) {
                return iCompareTo9;
            }
        }
        String description = getDescription();
        String description2 = playlistHint.getDescription();
        if (description != description2) {
            if (description == null) {
                return -1;
            }
            if (description2 == null) {
                return 1;
            }
            int iCompareTo10 = description.compareTo(description2);
            if (iCompareTo10 != 0) {
                return iCompareTo10;
            }
        }
        PlaymodeEligibility playmodeEligibility = getPlaymodeEligibility();
        PlaymodeEligibility playmodeEligibility2 = playlistHint.getPlaymodeEligibility();
        if (playmodeEligibility != playmodeEligibility2) {
            if (playmodeEligibility == null) {
                return -1;
            }
            if (playmodeEligibility2 == null) {
                return 1;
            }
            int iCompareTo11 = playmodeEligibility.compareTo(playmodeEligibility2);
            if (iCompareTo11 != 0) {
                return iCompareTo11;
            }
        }
        String albumArtImageUrl = getAlbumArtImageUrl();
        String albumArtImageUrl2 = playlistHint.getAlbumArtImageUrl();
        if (albumArtImageUrl != albumArtImageUrl2) {
            if (albumArtImageUrl == null) {
                return -1;
            }
            if (albumArtImageUrl2 == null) {
                return 1;
            }
            int iCompareTo12 = albumArtImageUrl.compareTo(albumArtImageUrl2);
            if (iCompareTo12 != 0) {
                return iCompareTo12;
            }
        }
        String title = getTitle();
        String title2 = playlistHint.getTitle();
        if (title != title2) {
            if (title == null) {
                return -1;
            }
            if (title2 == null) {
                return 1;
            }
            int iCompareTo13 = title.compareTo(title2);
            if (iCompareTo13 != 0) {
                return iCompareTo13;
            }
        }
        String asin = getAsin();
        String asin2 = playlistHint.getAsin();
        if (asin != asin2) {
            if (asin == null) {
                return -1;
            }
            if (asin2 == null) {
                return 1;
            }
            int iCompareTo14 = asin.compareTo(asin2);
            if (iCompareTo14 != 0) {
                return iCompareTo14;
            }
        }
        String recentlyPlayedEntityType = getRecentlyPlayedEntityType();
        String recentlyPlayedEntityType2 = playlistHint.getRecentlyPlayedEntityType();
        if (recentlyPlayedEntityType != recentlyPlayedEntityType2) {
            if (recentlyPlayedEntityType == null) {
                return -1;
            }
            if (recentlyPlayedEntityType2 == null) {
                return 1;
            }
            int iCompareTo15 = recentlyPlayedEntityType.compareTo(recentlyPlayedEntityType2);
            if (iCompareTo15 != 0) {
                return iCompareTo15;
            }
        }
        return super.compareTo(hint);
    }

    @Override // com.amazon.layout.music.model.Hint
    public boolean equals(Object obj) {
        if (!(obj instanceof PlaylistHint)) {
            return false;
        }
        PlaylistHint playlistHint = (PlaylistHint) obj;
        return super.equals(obj) && internalEqualityCheck(isIsSonicRush(), playlistHint.isIsSonicRush()) && internalEqualityCheck(getContentTypes(), playlistHint.getContentTypes()) && internalEqualityCheck(getLanguagesOfPerformance(), playlistHint.getLanguagesOfPerformance()) && internalEqualityCheck(getCuratedBy(), playlistHint.getCuratedBy()) && internalEqualityCheck(isIsMusicSubscription(), playlistHint.isIsMusicSubscription()) && internalEqualityCheck(getTimeStamp(), playlistHint.getTimeStamp()) && internalEqualityCheck(isIsRecommended(), playlistHint.isIsRecommended()) && internalEqualityCheck(isIsPrime(), playlistHint.isIsPrime()) && internalEqualityCheck(isIsSonicRushOnDemandPlayable(), playlistHint.isIsSonicRushOnDemandPlayable()) && internalEqualityCheck(getDescription(), playlistHint.getDescription()) && internalEqualityCheck(getPlaymodeEligibility(), playlistHint.getPlaymodeEligibility()) && internalEqualityCheck(getAlbumArtImageUrl(), playlistHint.getAlbumArtImageUrl()) && internalEqualityCheck(getTitle(), playlistHint.getTitle()) && internalEqualityCheck(getAsin(), playlistHint.getAsin()) && internalEqualityCheck(getRecentlyPlayedEntityType(), playlistHint.getRecentlyPlayedEntityType());
    }

    public String getAlbumArtImageUrl() {
        return this.albumArtImageUrl;
    }

    public String getAsin() {
        return this.asin;
    }

    public List<String> getContentTypes() {
        return this.contentTypes;
    }

    public String getCuratedBy() {
        return this.curatedBy;
    }

    public String getDescription() {
        return this.description;
    }

    public String getLanguagesOfPerformance() {
        return this.languagesOfPerformance;
    }

    public PlaymodeEligibility getPlaymodeEligibility() {
        return this.playmodeEligibility;
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
        return internalHashCodeCompute(Integer.valueOf(super.hashCode()), Integer.valueOf(classNameHashCode), isIsSonicRush(), getContentTypes(), getLanguagesOfPerformance(), getCuratedBy(), isIsMusicSubscription(), getTimeStamp(), isIsRecommended(), isIsPrime(), isIsSonicRushOnDemandPlayable(), getDescription(), getPlaymodeEligibility(), getAlbumArtImageUrl(), getTitle(), getAsin(), getRecentlyPlayedEntityType());
    }

    public Boolean isIsMusicSubscription() {
        return this.isMusicSubscription;
    }

    public Boolean isIsPrime() {
        return this.isPrime;
    }

    public Boolean isIsRecommended() {
        return this.isRecommended;
    }

    public Boolean isIsSonicRush() {
        return this.isSonicRush;
    }

    public Boolean isIsSonicRushOnDemandPlayable() {
        return this.isSonicRushOnDemandPlayable;
    }

    public void setAlbumArtImageUrl(String str) {
        this.albumArtImageUrl = str;
    }

    public void setAsin(String str) {
        this.asin = str;
    }

    public void setContentTypes(List<String> list) {
        this.contentTypes = list;
    }

    public void setCuratedBy(String str) {
        this.curatedBy = str;
    }

    public void setDescription(String str) {
        this.description = str;
    }

    public void setIsMusicSubscription(Boolean bool) {
        this.isMusicSubscription = bool;
    }

    public void setIsPrime(Boolean bool) {
        this.isPrime = bool;
    }

    public void setIsRecommended(Boolean bool) {
        this.isRecommended = bool;
    }

    public void setIsSonicRush(Boolean bool) {
        this.isSonicRush = bool;
    }

    public void setIsSonicRushOnDemandPlayable(Boolean bool) {
        this.isSonicRushOnDemandPlayable = bool;
    }

    public void setLanguagesOfPerformance(String str) {
        this.languagesOfPerformance = str;
    }

    public void setPlaymodeEligibility(PlaymodeEligibility playmodeEligibility) {
        this.playmodeEligibility = playmodeEligibility;
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
