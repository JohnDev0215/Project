package com.amazon.layout.music.model;

import com.amazon.musicensembleservice.PlaymodeEligibility;
import java.util.Arrays;
import java.util.List;

/* JADX INFO: loaded from: classes3.dex */
public class AlbumHint extends Hint {
    private static final int classNameHashCode = internalHashCodeCompute("com.amazon.layout.music.model.AlbumHint");
    private String albumArtImageUrl;
    private String artistAsin;
    private String artistName;
    private String asin;
    private List<String> contentEncoding;
    private List<String> contentTypes;
    private Boolean isInLibrary;
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
        if (!(hint instanceof AlbumHint)) {
            return 1;
        }
        AlbumHint albumHint = (AlbumHint) hint;
        Boolean boolIsIsMusicSubscription = isIsMusicSubscription();
        Boolean boolIsIsMusicSubscription2 = albumHint.isIsMusicSubscription();
        if (boolIsIsMusicSubscription != boolIsIsMusicSubscription2) {
            if (boolIsIsMusicSubscription == null) {
                return -1;
            }
            if (boolIsIsMusicSubscription2 == null) {
                return 1;
            }
            int iCompareTo = boolIsIsMusicSubscription.compareTo(boolIsIsMusicSubscription2);
            if (iCompareTo != 0) {
                return iCompareTo;
            }
        }
        Boolean boolIsIsInLibrary = isIsInLibrary();
        Boolean boolIsIsInLibrary2 = albumHint.isIsInLibrary();
        if (boolIsIsInLibrary != boolIsIsInLibrary2) {
            if (boolIsIsInLibrary == null) {
                return -1;
            }
            if (boolIsIsInLibrary2 == null) {
                return 1;
            }
            int iCompareTo2 = boolIsIsInLibrary.compareTo(boolIsIsInLibrary2);
            if (iCompareTo2 != 0) {
                return iCompareTo2;
            }
        }
        Long timeStamp = getTimeStamp();
        Long timeStamp2 = albumHint.getTimeStamp();
        if (timeStamp != timeStamp2) {
            if (timeStamp == null) {
                return -1;
            }
            if (timeStamp2 == null) {
                return 1;
            }
            int iCompareTo3 = timeStamp.compareTo(timeStamp2);
            if (iCompareTo3 != 0) {
                return iCompareTo3;
            }
        }
        String artistAsin = getArtistAsin();
        String artistAsin2 = albumHint.getArtistAsin();
        if (artistAsin != artistAsin2) {
            if (artistAsin == null) {
                return -1;
            }
            if (artistAsin2 == null) {
                return 1;
            }
            int iCompareTo4 = artistAsin.compareTo(artistAsin2);
            if (iCompareTo4 != 0) {
                return iCompareTo4;
            }
        }
        Boolean boolIsIsSonicRush = isIsSonicRush();
        Boolean boolIsIsSonicRush2 = albumHint.isIsSonicRush();
        if (boolIsIsSonicRush != boolIsIsSonicRush2) {
            if (boolIsIsSonicRush == null) {
                return -1;
            }
            if (boolIsIsSonicRush2 == null) {
                return 1;
            }
            int iCompareTo5 = boolIsIsSonicRush.compareTo(boolIsIsSonicRush2);
            if (iCompareTo5 != 0) {
                return iCompareTo5;
            }
        }
        String title = getTitle();
        String title2 = albumHint.getTitle();
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
        Boolean boolIsIsSonicRushOnDemandPlayable = isIsSonicRushOnDemandPlayable();
        Boolean boolIsIsSonicRushOnDemandPlayable2 = albumHint.isIsSonicRushOnDemandPlayable();
        if (boolIsIsSonicRushOnDemandPlayable != boolIsIsSonicRushOnDemandPlayable2) {
            if (boolIsIsSonicRushOnDemandPlayable == null) {
                return -1;
            }
            if (boolIsIsSonicRushOnDemandPlayable2 == null) {
                return 1;
            }
            int iCompareTo7 = boolIsIsSonicRushOnDemandPlayable.compareTo(boolIsIsSonicRushOnDemandPlayable2);
            if (iCompareTo7 != 0) {
                return iCompareTo7;
            }
        }
        String artistName = getArtistName();
        String artistName2 = albumHint.getArtistName();
        if (artistName != artistName2) {
            if (artistName == null) {
                return -1;
            }
            if (artistName2 == null) {
                return 1;
            }
            int iCompareTo8 = artistName.compareTo(artistName2);
            if (iCompareTo8 != 0) {
                return iCompareTo8;
            }
        }
        String recentlyPlayedEntityType = getRecentlyPlayedEntityType();
        String recentlyPlayedEntityType2 = albumHint.getRecentlyPlayedEntityType();
        if (recentlyPlayedEntityType != recentlyPlayedEntityType2) {
            if (recentlyPlayedEntityType == null) {
                return -1;
            }
            if (recentlyPlayedEntityType2 == null) {
                return 1;
            }
            int iCompareTo9 = recentlyPlayedEntityType.compareTo(recentlyPlayedEntityType2);
            if (iCompareTo9 != 0) {
                return iCompareTo9;
            }
        }
        PlaymodeEligibility playmodeEligibility = getPlaymodeEligibility();
        PlaymodeEligibility playmodeEligibility2 = albumHint.getPlaymodeEligibility();
        if (playmodeEligibility != playmodeEligibility2) {
            if (playmodeEligibility == null) {
                return -1;
            }
            if (playmodeEligibility2 == null) {
                return 1;
            }
            int iCompareTo10 = playmodeEligibility.compareTo(playmodeEligibility2);
            if (iCompareTo10 != 0) {
                return iCompareTo10;
            }
        }
        String albumArtImageUrl = getAlbumArtImageUrl();
        String albumArtImageUrl2 = albumHint.getAlbumArtImageUrl();
        if (albumArtImageUrl != albumArtImageUrl2) {
            if (albumArtImageUrl == null) {
                return -1;
            }
            if (albumArtImageUrl2 == null) {
                return 1;
            }
            int iCompareTo11 = albumArtImageUrl.compareTo(albumArtImageUrl2);
            if (iCompareTo11 != 0) {
                return iCompareTo11;
            }
        }
        List<String> contentEncoding = getContentEncoding();
        List<String> contentEncoding2 = albumHint.getContentEncoding();
        if (contentEncoding != contentEncoding2) {
            if (contentEncoding == null) {
                return -1;
            }
            if (contentEncoding2 == null) {
                return 1;
            }
            if (contentEncoding instanceof Comparable) {
                int iCompareTo12 = ((Comparable) contentEncoding).compareTo(contentEncoding2);
                if (iCompareTo12 != 0) {
                    return iCompareTo12;
                }
            } else if (!contentEncoding.equals(contentEncoding2)) {
                int iHashCode = contentEncoding.hashCode();
                int iHashCode2 = contentEncoding2.hashCode();
                if (iHashCode < iHashCode2) {
                    return -1;
                }
                if (iHashCode > iHashCode2) {
                    return 1;
                }
            }
        }
        String asin = getAsin();
        String asin2 = albumHint.getAsin();
        if (asin != asin2) {
            if (asin == null) {
                return -1;
            }
            if (asin2 == null) {
                return 1;
            }
            int iCompareTo13 = asin.compareTo(asin2);
            if (iCompareTo13 != 0) {
                return iCompareTo13;
            }
        }
        Boolean boolIsIsRecommended = isIsRecommended();
        Boolean boolIsIsRecommended2 = albumHint.isIsRecommended();
        if (boolIsIsRecommended != boolIsIsRecommended2) {
            if (boolIsIsRecommended == null) {
                return -1;
            }
            if (boolIsIsRecommended2 == null) {
                return 1;
            }
            int iCompareTo14 = boolIsIsRecommended.compareTo(boolIsIsRecommended2);
            if (iCompareTo14 != 0) {
                return iCompareTo14;
            }
        }
        Boolean boolIsIsPrime = isIsPrime();
        Boolean boolIsIsPrime2 = albumHint.isIsPrime();
        if (boolIsIsPrime != boolIsIsPrime2) {
            if (boolIsIsPrime == null) {
                return -1;
            }
            if (boolIsIsPrime2 == null) {
                return 1;
            }
            int iCompareTo15 = boolIsIsPrime.compareTo(boolIsIsPrime2);
            if (iCompareTo15 != 0) {
                return iCompareTo15;
            }
        }
        String languagesOfPerformance = getLanguagesOfPerformance();
        String languagesOfPerformance2 = albumHint.getLanguagesOfPerformance();
        if (languagesOfPerformance != languagesOfPerformance2) {
            if (languagesOfPerformance == null) {
                return -1;
            }
            if (languagesOfPerformance2 == null) {
                return 1;
            }
            int iCompareTo16 = languagesOfPerformance.compareTo(languagesOfPerformance2);
            if (iCompareTo16 != 0) {
                return iCompareTo16;
            }
        }
        List<String> contentTypes = getContentTypes();
        List<String> contentTypes2 = albumHint.getContentTypes();
        if (contentTypes != contentTypes2) {
            if (contentTypes == null) {
                return -1;
            }
            if (contentTypes2 == null) {
                return 1;
            }
            if (contentTypes instanceof Comparable) {
                int iCompareTo17 = ((Comparable) contentTypes).compareTo(contentTypes2);
                if (iCompareTo17 != 0) {
                    return iCompareTo17;
                }
            } else if (!contentTypes.equals(contentTypes2)) {
                int iHashCode3 = contentTypes.hashCode();
                int iHashCode4 = contentTypes2.hashCode();
                if (iHashCode3 < iHashCode4) {
                    return -1;
                }
                if (iHashCode3 > iHashCode4) {
                    return 1;
                }
            }
        }
        return super.compareTo(hint);
    }

    @Override // com.amazon.layout.music.model.Hint
    public boolean equals(Object obj) {
        if (!(obj instanceof AlbumHint)) {
            return false;
        }
        AlbumHint albumHint = (AlbumHint) obj;
        return super.equals(obj) && internalEqualityCheck(isIsMusicSubscription(), albumHint.isIsMusicSubscription()) && internalEqualityCheck(isIsInLibrary(), albumHint.isIsInLibrary()) && internalEqualityCheck(getTimeStamp(), albumHint.getTimeStamp()) && internalEqualityCheck(getArtistAsin(), albumHint.getArtistAsin()) && internalEqualityCheck(isIsSonicRush(), albumHint.isIsSonicRush()) && internalEqualityCheck(getTitle(), albumHint.getTitle()) && internalEqualityCheck(isIsSonicRushOnDemandPlayable(), albumHint.isIsSonicRushOnDemandPlayable()) && internalEqualityCheck(getArtistName(), albumHint.getArtistName()) && internalEqualityCheck(getRecentlyPlayedEntityType(), albumHint.getRecentlyPlayedEntityType()) && internalEqualityCheck(getPlaymodeEligibility(), albumHint.getPlaymodeEligibility()) && internalEqualityCheck(getAlbumArtImageUrl(), albumHint.getAlbumArtImageUrl()) && internalEqualityCheck(getContentEncoding(), albumHint.getContentEncoding()) && internalEqualityCheck(getAsin(), albumHint.getAsin()) && internalEqualityCheck(isIsRecommended(), albumHint.isIsRecommended()) && internalEqualityCheck(isIsPrime(), albumHint.isIsPrime()) && internalEqualityCheck(getLanguagesOfPerformance(), albumHint.getLanguagesOfPerformance()) && internalEqualityCheck(getContentTypes(), albumHint.getContentTypes());
    }

    public String getAlbumArtImageUrl() {
        return this.albumArtImageUrl;
    }

    public String getArtistAsin() {
        return this.artistAsin;
    }

    public String getArtistName() {
        return this.artistName;
    }

    public String getAsin() {
        return this.asin;
    }

    public List<String> getContentEncoding() {
        return this.contentEncoding;
    }

    public List<String> getContentTypes() {
        return this.contentTypes;
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
        return internalHashCodeCompute(Integer.valueOf(super.hashCode()), Integer.valueOf(classNameHashCode), isIsMusicSubscription(), isIsInLibrary(), getTimeStamp(), getArtistAsin(), isIsSonicRush(), getTitle(), isIsSonicRushOnDemandPlayable(), getArtistName(), getRecentlyPlayedEntityType(), getPlaymodeEligibility(), getAlbumArtImageUrl(), getContentEncoding(), getAsin(), isIsRecommended(), isIsPrime(), getLanguagesOfPerformance(), getContentTypes());
    }

    public Boolean isIsInLibrary() {
        return this.isInLibrary;
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

    public void setArtistAsin(String str) {
        this.artistAsin = str;
    }

    public void setArtistName(String str) {
        this.artistName = str;
    }

    public void setAsin(String str) {
        this.asin = str;
    }

    public void setContentEncoding(List<String> list) {
        this.contentEncoding = list;
    }

    public void setContentTypes(List<String> list) {
        this.contentTypes = list;
    }

    public void setIsInLibrary(Boolean bool) {
        this.isInLibrary = bool;
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
