package com.amazon.layout.music.model;

import com.amazon.musicensembleservice.ParentalControls;
import com.amazon.musicensembleservice.PlaymodeEligibility;
import java.util.Arrays;
import java.util.List;

/* JADX INFO: loaded from: classes3.dex */
public class TrackHint extends Hint {
    private static final int classNameHashCode = internalHashCodeCompute("com.amazon.layout.music.model.TrackHint");
    private String albumArtImageUrl;
    private String albumArtistAsin;
    private String albumArtistName;
    private String albumAsin;
    private String albumName;
    private String artistAsin;
    private String artistName;
    private String asin;
    private String assetType;
    private List<String> contentEncoding;
    private Integer discNumber;
    private Integer duration;
    private String genreName;
    private Boolean isMusicSubscription;
    private Boolean isPrime;
    private Boolean isRecommended;
    private Boolean isSonicRush;
    private Boolean isSonicRushOnDemandPlayable;
    private String languagesOfPerformance;
    private ParentalControls parentalControls;
    private String pid;
    private PlaymodeEligibility playmodeEligibility;
    private Double popularity;
    private String recentlyPlayedEntityType;
    private String title;
    private Integer trackNumber;

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
        if (!(hint instanceof TrackHint)) {
            return 1;
        }
        TrackHint trackHint = (TrackHint) hint;
        PlaymodeEligibility playmodeEligibility = getPlaymodeEligibility();
        PlaymodeEligibility playmodeEligibility2 = trackHint.getPlaymodeEligibility();
        if (playmodeEligibility != playmodeEligibility2) {
            if (playmodeEligibility == null) {
                return -1;
            }
            if (playmodeEligibility2 == null) {
                return 1;
            }
            int iCompareTo = playmodeEligibility.compareTo(playmodeEligibility2);
            if (iCompareTo != 0) {
                return iCompareTo;
            }
        }
        String asin = getAsin();
        String asin2 = trackHint.getAsin();
        if (asin != asin2) {
            if (asin == null) {
                return -1;
            }
            if (asin2 == null) {
                return 1;
            }
            int iCompareTo2 = asin.compareTo(asin2);
            if (iCompareTo2 != 0) {
                return iCompareTo2;
            }
        }
        String artistName = getArtistName();
        String artistName2 = trackHint.getArtistName();
        if (artistName != artistName2) {
            if (artistName == null) {
                return -1;
            }
            if (artistName2 == null) {
                return 1;
            }
            int iCompareTo3 = artistName.compareTo(artistName2);
            if (iCompareTo3 != 0) {
                return iCompareTo3;
            }
        }
        String recentlyPlayedEntityType = getRecentlyPlayedEntityType();
        String recentlyPlayedEntityType2 = trackHint.getRecentlyPlayedEntityType();
        if (recentlyPlayedEntityType != recentlyPlayedEntityType2) {
            if (recentlyPlayedEntityType == null) {
                return -1;
            }
            if (recentlyPlayedEntityType2 == null) {
                return 1;
            }
            int iCompareTo4 = recentlyPlayedEntityType.compareTo(recentlyPlayedEntityType2);
            if (iCompareTo4 != 0) {
                return iCompareTo4;
            }
        }
        String title = getTitle();
        String title2 = trackHint.getTitle();
        if (title != title2) {
            if (title == null) {
                return -1;
            }
            if (title2 == null) {
                return 1;
            }
            int iCompareTo5 = title.compareTo(title2);
            if (iCompareTo5 != 0) {
                return iCompareTo5;
            }
        }
        String albumArtImageUrl = getAlbumArtImageUrl();
        String albumArtImageUrl2 = trackHint.getAlbumArtImageUrl();
        if (albumArtImageUrl != albumArtImageUrl2) {
            if (albumArtImageUrl == null) {
                return -1;
            }
            if (albumArtImageUrl2 == null) {
                return 1;
            }
            int iCompareTo6 = albumArtImageUrl.compareTo(albumArtImageUrl2);
            if (iCompareTo6 != 0) {
                return iCompareTo6;
            }
        }
        Boolean boolIsIsPrime = isIsPrime();
        Boolean boolIsIsPrime2 = trackHint.isIsPrime();
        if (boolIsIsPrime != boolIsIsPrime2) {
            if (boolIsIsPrime == null) {
                return -1;
            }
            if (boolIsIsPrime2 == null) {
                return 1;
            }
            int iCompareTo7 = boolIsIsPrime.compareTo(boolIsIsPrime2);
            if (iCompareTo7 != 0) {
                return iCompareTo7;
            }
        }
        String genreName = getGenreName();
        String genreName2 = trackHint.getGenreName();
        if (genreName != genreName2) {
            if (genreName == null) {
                return -1;
            }
            if (genreName2 == null) {
                return 1;
            }
            int iCompareTo8 = genreName.compareTo(genreName2);
            if (iCompareTo8 != 0) {
                return iCompareTo8;
            }
        }
        Integer duration = getDuration();
        Integer duration2 = trackHint.getDuration();
        if (duration != duration2) {
            if (duration == null) {
                return -1;
            }
            if (duration2 == null) {
                return 1;
            }
            int iCompareTo9 = duration.compareTo(duration2);
            if (iCompareTo9 != 0) {
                return iCompareTo9;
            }
        }
        Boolean boolIsIsSonicRush = isIsSonicRush();
        Boolean boolIsIsSonicRush2 = trackHint.isIsSonicRush();
        if (boolIsIsSonicRush != boolIsIsSonicRush2) {
            if (boolIsIsSonicRush == null) {
                return -1;
            }
            if (boolIsIsSonicRush2 == null) {
                return 1;
            }
            int iCompareTo10 = boolIsIsSonicRush.compareTo(boolIsIsSonicRush2);
            if (iCompareTo10 != 0) {
                return iCompareTo10;
            }
        }
        String albumArtistName = getAlbumArtistName();
        String albumArtistName2 = trackHint.getAlbumArtistName();
        if (albumArtistName != albumArtistName2) {
            if (albumArtistName == null) {
                return -1;
            }
            if (albumArtistName2 == null) {
                return 1;
            }
            int iCompareTo11 = albumArtistName.compareTo(albumArtistName2);
            if (iCompareTo11 != 0) {
                return iCompareTo11;
            }
        }
        ParentalControls parentalControls = getParentalControls();
        ParentalControls parentalControls2 = trackHint.getParentalControls();
        if (parentalControls != parentalControls2) {
            if (parentalControls == null) {
                return -1;
            }
            if (parentalControls2 == null) {
                return 1;
            }
            int iCompareTo12 = parentalControls.compareTo(parentalControls2);
            if (iCompareTo12 != 0) {
                return iCompareTo12;
            }
        }
        Boolean boolIsIsRecommended = isIsRecommended();
        Boolean boolIsIsRecommended2 = trackHint.isIsRecommended();
        if (boolIsIsRecommended != boolIsIsRecommended2) {
            if (boolIsIsRecommended == null) {
                return -1;
            }
            if (boolIsIsRecommended2 == null) {
                return 1;
            }
            int iCompareTo13 = boolIsIsRecommended.compareTo(boolIsIsRecommended2);
            if (iCompareTo13 != 0) {
                return iCompareTo13;
            }
        }
        Integer trackNumber = getTrackNumber();
        Integer trackNumber2 = trackHint.getTrackNumber();
        if (trackNumber != trackNumber2) {
            if (trackNumber == null) {
                return -1;
            }
            if (trackNumber2 == null) {
                return 1;
            }
            int iCompareTo14 = trackNumber.compareTo(trackNumber2);
            if (iCompareTo14 != 0) {
                return iCompareTo14;
            }
        }
        String pid = getPid();
        String pid2 = trackHint.getPid();
        if (pid != pid2) {
            if (pid == null) {
                return -1;
            }
            if (pid2 == null) {
                return 1;
            }
            int iCompareTo15 = pid.compareTo(pid2);
            if (iCompareTo15 != 0) {
                return iCompareTo15;
            }
        }
        Boolean boolIsIsSonicRushOnDemandPlayable = isIsSonicRushOnDemandPlayable();
        Boolean boolIsIsSonicRushOnDemandPlayable2 = trackHint.isIsSonicRushOnDemandPlayable();
        if (boolIsIsSonicRushOnDemandPlayable != boolIsIsSonicRushOnDemandPlayable2) {
            if (boolIsIsSonicRushOnDemandPlayable == null) {
                return -1;
            }
            if (boolIsIsSonicRushOnDemandPlayable2 == null) {
                return 1;
            }
            int iCompareTo16 = boolIsIsSonicRushOnDemandPlayable.compareTo(boolIsIsSonicRushOnDemandPlayable2);
            if (iCompareTo16 != 0) {
                return iCompareTo16;
            }
        }
        String assetType = getAssetType();
        String assetType2 = trackHint.getAssetType();
        if (assetType != assetType2) {
            if (assetType == null) {
                return -1;
            }
            if (assetType2 == null) {
                return 1;
            }
            int iCompareTo17 = assetType.compareTo(assetType2);
            if (iCompareTo17 != 0) {
                return iCompareTo17;
            }
        }
        String languagesOfPerformance = getLanguagesOfPerformance();
        String languagesOfPerformance2 = trackHint.getLanguagesOfPerformance();
        if (languagesOfPerformance != languagesOfPerformance2) {
            if (languagesOfPerformance == null) {
                return -1;
            }
            if (languagesOfPerformance2 == null) {
                return 1;
            }
            int iCompareTo18 = languagesOfPerformance.compareTo(languagesOfPerformance2);
            if (iCompareTo18 != 0) {
                return iCompareTo18;
            }
        }
        String albumName = getAlbumName();
        String albumName2 = trackHint.getAlbumName();
        if (albumName != albumName2) {
            if (albumName == null) {
                return -1;
            }
            if (albumName2 == null) {
                return 1;
            }
            int iCompareTo19 = albumName.compareTo(albumName2);
            if (iCompareTo19 != 0) {
                return iCompareTo19;
            }
        }
        List<String> contentEncoding = getContentEncoding();
        List<String> contentEncoding2 = trackHint.getContentEncoding();
        if (contentEncoding != contentEncoding2) {
            if (contentEncoding == null) {
                return -1;
            }
            if (contentEncoding2 == null) {
                return 1;
            }
            if (contentEncoding instanceof Comparable) {
                int iCompareTo20 = ((Comparable) contentEncoding).compareTo(contentEncoding2);
                if (iCompareTo20 != 0) {
                    return iCompareTo20;
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
        String artistAsin = getArtistAsin();
        String artistAsin2 = trackHint.getArtistAsin();
        if (artistAsin != artistAsin2) {
            if (artistAsin == null) {
                return -1;
            }
            if (artistAsin2 == null) {
                return 1;
            }
            int iCompareTo21 = artistAsin.compareTo(artistAsin2);
            if (iCompareTo21 != 0) {
                return iCompareTo21;
            }
        }
        Boolean boolIsIsMusicSubscription = isIsMusicSubscription();
        Boolean boolIsIsMusicSubscription2 = trackHint.isIsMusicSubscription();
        if (boolIsIsMusicSubscription != boolIsIsMusicSubscription2) {
            if (boolIsIsMusicSubscription == null) {
                return -1;
            }
            if (boolIsIsMusicSubscription2 == null) {
                return 1;
            }
            int iCompareTo22 = boolIsIsMusicSubscription.compareTo(boolIsIsMusicSubscription2);
            if (iCompareTo22 != 0) {
                return iCompareTo22;
            }
        }
        String albumAsin = getAlbumAsin();
        String albumAsin2 = trackHint.getAlbumAsin();
        if (albumAsin != albumAsin2) {
            if (albumAsin == null) {
                return -1;
            }
            if (albumAsin2 == null) {
                return 1;
            }
            int iCompareTo23 = albumAsin.compareTo(albumAsin2);
            if (iCompareTo23 != 0) {
                return iCompareTo23;
            }
        }
        Integer discNumber = getDiscNumber();
        Integer discNumber2 = trackHint.getDiscNumber();
        if (discNumber != discNumber2) {
            if (discNumber == null) {
                return -1;
            }
            if (discNumber2 == null) {
                return 1;
            }
            int iCompareTo24 = discNumber.compareTo(discNumber2);
            if (iCompareTo24 != 0) {
                return iCompareTo24;
            }
        }
        String albumArtistAsin = getAlbumArtistAsin();
        String albumArtistAsin2 = trackHint.getAlbumArtistAsin();
        if (albumArtistAsin != albumArtistAsin2) {
            if (albumArtistAsin == null) {
                return -1;
            }
            if (albumArtistAsin2 == null) {
                return 1;
            }
            int iCompareTo25 = albumArtistAsin.compareTo(albumArtistAsin2);
            if (iCompareTo25 != 0) {
                return iCompareTo25;
            }
        }
        Double popularity = getPopularity();
        Double popularity2 = trackHint.getPopularity();
        if (popularity != popularity2) {
            if (popularity == null) {
                return -1;
            }
            if (popularity2 == null) {
                return 1;
            }
            int iCompareTo26 = popularity.compareTo(popularity2);
            if (iCompareTo26 != 0) {
                return iCompareTo26;
            }
        }
        return super.compareTo(hint);
    }

    @Override // com.amazon.layout.music.model.Hint
    public boolean equals(Object obj) {
        if (!(obj instanceof TrackHint)) {
            return false;
        }
        TrackHint trackHint = (TrackHint) obj;
        return super.equals(obj) && internalEqualityCheck(getPlaymodeEligibility(), trackHint.getPlaymodeEligibility()) && internalEqualityCheck(getAsin(), trackHint.getAsin()) && internalEqualityCheck(getArtistName(), trackHint.getArtistName()) && internalEqualityCheck(getRecentlyPlayedEntityType(), trackHint.getRecentlyPlayedEntityType()) && internalEqualityCheck(getTitle(), trackHint.getTitle()) && internalEqualityCheck(getAlbumArtImageUrl(), trackHint.getAlbumArtImageUrl()) && internalEqualityCheck(isIsPrime(), trackHint.isIsPrime()) && internalEqualityCheck(getGenreName(), trackHint.getGenreName()) && internalEqualityCheck(getDuration(), trackHint.getDuration()) && internalEqualityCheck(isIsSonicRush(), trackHint.isIsSonicRush()) && internalEqualityCheck(getAlbumArtistName(), trackHint.getAlbumArtistName()) && internalEqualityCheck(getParentalControls(), trackHint.getParentalControls()) && internalEqualityCheck(isIsRecommended(), trackHint.isIsRecommended()) && internalEqualityCheck(getTrackNumber(), trackHint.getTrackNumber()) && internalEqualityCheck(getPid(), trackHint.getPid()) && internalEqualityCheck(isIsSonicRushOnDemandPlayable(), trackHint.isIsSonicRushOnDemandPlayable()) && internalEqualityCheck(getAssetType(), trackHint.getAssetType()) && internalEqualityCheck(getLanguagesOfPerformance(), trackHint.getLanguagesOfPerformance()) && internalEqualityCheck(getAlbumName(), trackHint.getAlbumName()) && internalEqualityCheck(getContentEncoding(), trackHint.getContentEncoding()) && internalEqualityCheck(getArtistAsin(), trackHint.getArtistAsin()) && internalEqualityCheck(isIsMusicSubscription(), trackHint.isIsMusicSubscription()) && internalEqualityCheck(getAlbumAsin(), trackHint.getAlbumAsin()) && internalEqualityCheck(getDiscNumber(), trackHint.getDiscNumber()) && internalEqualityCheck(getAlbumArtistAsin(), trackHint.getAlbumArtistAsin()) && internalEqualityCheck(getPopularity(), trackHint.getPopularity());
    }

    public String getAlbumArtImageUrl() {
        return this.albumArtImageUrl;
    }

    public String getAlbumArtistAsin() {
        return this.albumArtistAsin;
    }

    public String getAlbumArtistName() {
        return this.albumArtistName;
    }

    public String getAlbumAsin() {
        return this.albumAsin;
    }

    public String getAlbumName() {
        return this.albumName;
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

    public String getAssetType() {
        return this.assetType;
    }

    public List<String> getContentEncoding() {
        return this.contentEncoding;
    }

    public Integer getDiscNumber() {
        return this.discNumber;
    }

    public Integer getDuration() {
        return this.duration;
    }

    public String getGenreName() {
        return this.genreName;
    }

    public String getLanguagesOfPerformance() {
        return this.languagesOfPerformance;
    }

    public ParentalControls getParentalControls() {
        return this.parentalControls;
    }

    public String getPid() {
        return this.pid;
    }

    public PlaymodeEligibility getPlaymodeEligibility() {
        return this.playmodeEligibility;
    }

    public Double getPopularity() {
        return this.popularity;
    }

    public String getRecentlyPlayedEntityType() {
        return this.recentlyPlayedEntityType;
    }

    public String getTitle() {
        return this.title;
    }

    public Integer getTrackNumber() {
        return this.trackNumber;
    }

    @Override // com.amazon.layout.music.model.Hint
    public int hashCode() {
        return internalHashCodeCompute(Integer.valueOf(super.hashCode()), Integer.valueOf(classNameHashCode), getPlaymodeEligibility(), getAsin(), getArtistName(), getRecentlyPlayedEntityType(), getTitle(), getAlbumArtImageUrl(), isIsPrime(), getGenreName(), getDuration(), isIsSonicRush(), getAlbumArtistName(), getParentalControls(), isIsRecommended(), getTrackNumber(), getPid(), isIsSonicRushOnDemandPlayable(), getAssetType(), getLanguagesOfPerformance(), getAlbumName(), getContentEncoding(), getArtistAsin(), isIsMusicSubscription(), getAlbumAsin(), getDiscNumber(), getAlbumArtistAsin(), getPopularity());
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

    public void setAlbumArtistAsin(String str) {
        this.albumArtistAsin = str;
    }

    public void setAlbumArtistName(String str) {
        this.albumArtistName = str;
    }

    public void setAlbumAsin(String str) {
        this.albumAsin = str;
    }

    public void setAlbumName(String str) {
        this.albumName = str;
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

    public void setAssetType(String str) {
        this.assetType = str;
    }

    public void setContentEncoding(List<String> list) {
        this.contentEncoding = list;
    }

    public void setDiscNumber(Integer num) {
        this.discNumber = num;
    }

    public void setDuration(Integer num) {
        this.duration = num;
    }

    public void setGenreName(String str) {
        this.genreName = str;
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

    public void setParentalControls(ParentalControls parentalControls) {
        this.parentalControls = parentalControls;
    }

    public void setPid(String str) {
        this.pid = str;
    }

    public void setPlaymodeEligibility(PlaymodeEligibility playmodeEligibility) {
        this.playmodeEligibility = playmodeEligibility;
    }

    public void setPopularity(Double d) {
        this.popularity = d;
    }

    public void setRecentlyPlayedEntityType(String str) {
        this.recentlyPlayedEntityType = str;
    }

    public void setTitle(String str) {
        this.title = str;
    }

    public void setTrackNumber(Integer num) {
        this.trackNumber = num;
    }
}
