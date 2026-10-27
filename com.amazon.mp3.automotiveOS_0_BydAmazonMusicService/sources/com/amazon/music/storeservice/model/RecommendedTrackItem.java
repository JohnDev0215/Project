package com.amazon.music.storeservice.model;

import com.amazon.musicensembleservice.ParentalControls;
import com.amazon.musicensembleservice.PlaymodeEligibility;
import java.util.Arrays;
import java.util.List;

/* JADX INFO: loaded from: classes4.dex */
public class RecommendedTrackItem implements Comparable<RecommendedTrackItem> {
    private static final int classNameHashCode = internalHashCodeCompute("com.amazon.music.storeservice.model.RecommendedTrackItem");
    private String albumArtImageUrl;
    private String albumAsin;
    private Double albumAverageOverallRating;
    private String albumName;
    private Long albumOriginalReleaseDate;
    private String artistAsin;
    private String artistName;
    private String asin;
    private List<String> contentEncoding;
    private Integer durationSeconds;
    private Boolean isFree;
    private Boolean isFreeOnDemandPlayable;
    private Boolean isMusicSubscription;
    private Boolean isPrime;
    private Boolean isSonicRush;
    private Boolean isSonicRushOnDemandPlayable;
    private Justification justification;
    private ParentalControls parentalControls;
    private PlaymodeEligibility playmodeEligibility;
    private String title;

    private static boolean internalEqualityCheck(Object obj, Object obj2) {
        return obj == obj2 || (obj != null && obj.equals(obj2));
    }

    private static int internalHashCodeCompute(Object... objArr) {
        return Arrays.hashCode(objArr);
    }

    @Override // java.lang.Comparable
    @Deprecated
    public int compareTo(@Deprecated RecommendedTrackItem recommendedTrackItem) {
        if (recommendedTrackItem == null) {
            return -1;
        }
        if (recommendedTrackItem == this) {
            return 0;
        }
        Boolean boolIsIsSonicRushOnDemandPlayable = isIsSonicRushOnDemandPlayable();
        Boolean boolIsIsSonicRushOnDemandPlayable2 = recommendedTrackItem.isIsSonicRushOnDemandPlayable();
        if (boolIsIsSonicRushOnDemandPlayable != boolIsIsSonicRushOnDemandPlayable2) {
            if (boolIsIsSonicRushOnDemandPlayable == null) {
                return -1;
            }
            if (boolIsIsSonicRushOnDemandPlayable2 == null) {
                return 1;
            }
            int iCompareTo = boolIsIsSonicRushOnDemandPlayable.compareTo(boolIsIsSonicRushOnDemandPlayable2);
            if (iCompareTo != 0) {
                return iCompareTo;
            }
        }
        String albumArtImageUrl = getAlbumArtImageUrl();
        String albumArtImageUrl2 = recommendedTrackItem.getAlbumArtImageUrl();
        if (albumArtImageUrl != albumArtImageUrl2) {
            if (albumArtImageUrl == null) {
                return -1;
            }
            if (albumArtImageUrl2 == null) {
                return 1;
            }
            int iCompareTo2 = albumArtImageUrl.compareTo(albumArtImageUrl2);
            if (iCompareTo2 != 0) {
                return iCompareTo2;
            }
        }
        Boolean boolIsIsPrime = isIsPrime();
        Boolean boolIsIsPrime2 = recommendedTrackItem.isIsPrime();
        if (boolIsIsPrime != boolIsIsPrime2) {
            if (boolIsIsPrime == null) {
                return -1;
            }
            if (boolIsIsPrime2 == null) {
                return 1;
            }
            int iCompareTo3 = boolIsIsPrime.compareTo(boolIsIsPrime2);
            if (iCompareTo3 != 0) {
                return iCompareTo3;
            }
        }
        ParentalControls parentalControls = getParentalControls();
        ParentalControls parentalControls2 = recommendedTrackItem.getParentalControls();
        if (parentalControls != parentalControls2) {
            if (parentalControls == null) {
                return -1;
            }
            if (parentalControls2 == null) {
                return 1;
            }
            int iCompareTo4 = parentalControls.compareTo(parentalControls2);
            if (iCompareTo4 != 0) {
                return iCompareTo4;
            }
        }
        PlaymodeEligibility playmodeEligibility = getPlaymodeEligibility();
        PlaymodeEligibility playmodeEligibility2 = recommendedTrackItem.getPlaymodeEligibility();
        if (playmodeEligibility != playmodeEligibility2) {
            if (playmodeEligibility == null) {
                return -1;
            }
            if (playmodeEligibility2 == null) {
                return 1;
            }
            int iCompareTo5 = playmodeEligibility.compareTo(playmodeEligibility2);
            if (iCompareTo5 != 0) {
                return iCompareTo5;
            }
        }
        String asin = getAsin();
        String asin2 = recommendedTrackItem.getAsin();
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
        Double albumAverageOverallRating = getAlbumAverageOverallRating();
        Double albumAverageOverallRating2 = recommendedTrackItem.getAlbumAverageOverallRating();
        if (albumAverageOverallRating != albumAverageOverallRating2) {
            if (albumAverageOverallRating == null) {
                return -1;
            }
            if (albumAverageOverallRating2 == null) {
                return 1;
            }
            int iCompareTo7 = albumAverageOverallRating.compareTo(albumAverageOverallRating2);
            if (iCompareTo7 != 0) {
                return iCompareTo7;
            }
        }
        String title = getTitle();
        String title2 = recommendedTrackItem.getTitle();
        if (title != title2) {
            if (title == null) {
                return -1;
            }
            if (title2 == null) {
                return 1;
            }
            int iCompareTo8 = title.compareTo(title2);
            if (iCompareTo8 != 0) {
                return iCompareTo8;
            }
        }
        Boolean boolIsIsFreeOnDemandPlayable = isIsFreeOnDemandPlayable();
        Boolean boolIsIsFreeOnDemandPlayable2 = recommendedTrackItem.isIsFreeOnDemandPlayable();
        if (boolIsIsFreeOnDemandPlayable != boolIsIsFreeOnDemandPlayable2) {
            if (boolIsIsFreeOnDemandPlayable == null) {
                return -1;
            }
            if (boolIsIsFreeOnDemandPlayable2 == null) {
                return 1;
            }
            int iCompareTo9 = boolIsIsFreeOnDemandPlayable.compareTo(boolIsIsFreeOnDemandPlayable2);
            if (iCompareTo9 != 0) {
                return iCompareTo9;
            }
        }
        String artistName = getArtistName();
        String artistName2 = recommendedTrackItem.getArtistName();
        if (artistName != artistName2) {
            if (artistName == null) {
                return -1;
            }
            if (artistName2 == null) {
                return 1;
            }
            int iCompareTo10 = artistName.compareTo(artistName2);
            if (iCompareTo10 != 0) {
                return iCompareTo10;
            }
        }
        Boolean boolIsIsFree = isIsFree();
        Boolean boolIsIsFree2 = recommendedTrackItem.isIsFree();
        if (boolIsIsFree != boolIsIsFree2) {
            if (boolIsIsFree == null) {
                return -1;
            }
            if (boolIsIsFree2 == null) {
                return 1;
            }
            int iCompareTo11 = boolIsIsFree.compareTo(boolIsIsFree2);
            if (iCompareTo11 != 0) {
                return iCompareTo11;
            }
        }
        String artistAsin = getArtistAsin();
        String artistAsin2 = recommendedTrackItem.getArtistAsin();
        if (artistAsin != artistAsin2) {
            if (artistAsin == null) {
                return -1;
            }
            if (artistAsin2 == null) {
                return 1;
            }
            int iCompareTo12 = artistAsin.compareTo(artistAsin2);
            if (iCompareTo12 != 0) {
                return iCompareTo12;
            }
        }
        Boolean boolIsIsSonicRush = isIsSonicRush();
        Boolean boolIsIsSonicRush2 = recommendedTrackItem.isIsSonicRush();
        if (boolIsIsSonicRush != boolIsIsSonicRush2) {
            if (boolIsIsSonicRush == null) {
                return -1;
            }
            if (boolIsIsSonicRush2 == null) {
                return 1;
            }
            int iCompareTo13 = boolIsIsSonicRush.compareTo(boolIsIsSonicRush2);
            if (iCompareTo13 != 0) {
                return iCompareTo13;
            }
        }
        Boolean boolIsIsMusicSubscription = isIsMusicSubscription();
        Boolean boolIsIsMusicSubscription2 = recommendedTrackItem.isIsMusicSubscription();
        if (boolIsIsMusicSubscription != boolIsIsMusicSubscription2) {
            if (boolIsIsMusicSubscription == null) {
                return -1;
            }
            if (boolIsIsMusicSubscription2 == null) {
                return 1;
            }
            int iCompareTo14 = boolIsIsMusicSubscription.compareTo(boolIsIsMusicSubscription2);
            if (iCompareTo14 != 0) {
                return iCompareTo14;
            }
        }
        String albumName = getAlbumName();
        String albumName2 = recommendedTrackItem.getAlbumName();
        if (albumName != albumName2) {
            if (albumName == null) {
                return -1;
            }
            if (albumName2 == null) {
                return 1;
            }
            int iCompareTo15 = albumName.compareTo(albumName2);
            if (iCompareTo15 != 0) {
                return iCompareTo15;
            }
        }
        Long albumOriginalReleaseDate = getAlbumOriginalReleaseDate();
        Long albumOriginalReleaseDate2 = recommendedTrackItem.getAlbumOriginalReleaseDate();
        if (albumOriginalReleaseDate != albumOriginalReleaseDate2) {
            if (albumOriginalReleaseDate == null) {
                return -1;
            }
            if (albumOriginalReleaseDate2 == null) {
                return 1;
            }
            int iCompareTo16 = albumOriginalReleaseDate.compareTo(albumOriginalReleaseDate2);
            if (iCompareTo16 != 0) {
                return iCompareTo16;
            }
        }
        String albumAsin = getAlbumAsin();
        String albumAsin2 = recommendedTrackItem.getAlbumAsin();
        if (albumAsin != albumAsin2) {
            if (albumAsin == null) {
                return -1;
            }
            if (albumAsin2 == null) {
                return 1;
            }
            int iCompareTo17 = albumAsin.compareTo(albumAsin2);
            if (iCompareTo17 != 0) {
                return iCompareTo17;
            }
        }
        Justification justification = getJustification();
        Justification justification2 = recommendedTrackItem.getJustification();
        if (justification != justification2) {
            if (justification == null) {
                return -1;
            }
            if (justification2 == null) {
                return 1;
            }
            int iCompareTo18 = justification.compareTo(justification2);
            if (iCompareTo18 != 0) {
                return iCompareTo18;
            }
        }
        List<String> contentEncoding = getContentEncoding();
        List<String> contentEncoding2 = recommendedTrackItem.getContentEncoding();
        if (contentEncoding != contentEncoding2) {
            if (contentEncoding == null) {
                return -1;
            }
            if (contentEncoding2 == null) {
                return 1;
            }
            if (contentEncoding instanceof Comparable) {
                int iCompareTo19 = ((Comparable) contentEncoding).compareTo(contentEncoding2);
                if (iCompareTo19 != 0) {
                    return iCompareTo19;
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
        Integer durationSeconds = getDurationSeconds();
        Integer durationSeconds2 = recommendedTrackItem.getDurationSeconds();
        if (durationSeconds != durationSeconds2) {
            if (durationSeconds == null) {
                return -1;
            }
            if (durationSeconds2 == null) {
                return 1;
            }
            int iCompareTo20 = durationSeconds.compareTo(durationSeconds2);
            if (iCompareTo20 != 0) {
                return iCompareTo20;
            }
        }
        return 0;
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof RecommendedTrackItem)) {
            return false;
        }
        RecommendedTrackItem recommendedTrackItem = (RecommendedTrackItem) obj;
        return internalEqualityCheck(isIsSonicRushOnDemandPlayable(), recommendedTrackItem.isIsSonicRushOnDemandPlayable()) && internalEqualityCheck(getAlbumArtImageUrl(), recommendedTrackItem.getAlbumArtImageUrl()) && internalEqualityCheck(isIsPrime(), recommendedTrackItem.isIsPrime()) && internalEqualityCheck(getParentalControls(), recommendedTrackItem.getParentalControls()) && internalEqualityCheck(getPlaymodeEligibility(), recommendedTrackItem.getPlaymodeEligibility()) && internalEqualityCheck(getAsin(), recommendedTrackItem.getAsin()) && internalEqualityCheck(getAlbumAverageOverallRating(), recommendedTrackItem.getAlbumAverageOverallRating()) && internalEqualityCheck(getTitle(), recommendedTrackItem.getTitle()) && internalEqualityCheck(isIsFreeOnDemandPlayable(), recommendedTrackItem.isIsFreeOnDemandPlayable()) && internalEqualityCheck(getArtistName(), recommendedTrackItem.getArtistName()) && internalEqualityCheck(isIsFree(), recommendedTrackItem.isIsFree()) && internalEqualityCheck(getArtistAsin(), recommendedTrackItem.getArtistAsin()) && internalEqualityCheck(isIsSonicRush(), recommendedTrackItem.isIsSonicRush()) && internalEqualityCheck(isIsMusicSubscription(), recommendedTrackItem.isIsMusicSubscription()) && internalEqualityCheck(getAlbumName(), recommendedTrackItem.getAlbumName()) && internalEqualityCheck(getAlbumOriginalReleaseDate(), recommendedTrackItem.getAlbumOriginalReleaseDate()) && internalEqualityCheck(getAlbumAsin(), recommendedTrackItem.getAlbumAsin()) && internalEqualityCheck(getJustification(), recommendedTrackItem.getJustification()) && internalEqualityCheck(getContentEncoding(), recommendedTrackItem.getContentEncoding()) && internalEqualityCheck(getDurationSeconds(), recommendedTrackItem.getDurationSeconds());
    }

    public String getAlbumArtImageUrl() {
        return this.albumArtImageUrl;
    }

    public String getAlbumAsin() {
        return this.albumAsin;
    }

    public Double getAlbumAverageOverallRating() {
        return this.albumAverageOverallRating;
    }

    public String getAlbumName() {
        return this.albumName;
    }

    public Long getAlbumOriginalReleaseDate() {
        return this.albumOriginalReleaseDate;
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

    public Integer getDurationSeconds() {
        return this.durationSeconds;
    }

    public Justification getJustification() {
        return this.justification;
    }

    public ParentalControls getParentalControls() {
        return this.parentalControls;
    }

    public PlaymodeEligibility getPlaymodeEligibility() {
        return this.playmodeEligibility;
    }

    public String getTitle() {
        return this.title;
    }

    public int hashCode() {
        return internalHashCodeCompute(Integer.valueOf(classNameHashCode), isIsSonicRushOnDemandPlayable(), getAlbumArtImageUrl(), isIsPrime(), getParentalControls(), getPlaymodeEligibility(), getAsin(), getAlbumAverageOverallRating(), getTitle(), isIsFreeOnDemandPlayable(), getArtistName(), isIsFree(), getArtistAsin(), isIsSonicRush(), isIsMusicSubscription(), getAlbumName(), getAlbumOriginalReleaseDate(), getAlbumAsin(), getJustification(), getContentEncoding(), getDurationSeconds());
    }

    public Boolean isIsFree() {
        return this.isFree;
    }

    public Boolean isIsFreeOnDemandPlayable() {
        return this.isFreeOnDemandPlayable;
    }

    public Boolean isIsMusicSubscription() {
        return this.isMusicSubscription;
    }

    public Boolean isIsPrime() {
        return this.isPrime;
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

    public void setAlbumAsin(String str) {
        this.albumAsin = str;
    }

    public void setAlbumAverageOverallRating(Double d) {
        this.albumAverageOverallRating = d;
    }

    public void setAlbumName(String str) {
        this.albumName = str;
    }

    public void setAlbumOriginalReleaseDate(Long l) {
        this.albumOriginalReleaseDate = l;
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

    public void setDurationSeconds(Integer num) {
        this.durationSeconds = num;
    }

    public void setIsFree(Boolean bool) {
        this.isFree = bool;
    }

    public void setIsFreeOnDemandPlayable(Boolean bool) {
        this.isFreeOnDemandPlayable = bool;
    }

    public void setIsMusicSubscription(Boolean bool) {
        this.isMusicSubscription = bool;
    }

    public void setIsPrime(Boolean bool) {
        this.isPrime = bool;
    }

    public void setIsSonicRush(Boolean bool) {
        this.isSonicRush = bool;
    }

    public void setIsSonicRushOnDemandPlayable(Boolean bool) {
        this.isSonicRushOnDemandPlayable = bool;
    }

    public void setJustification(Justification justification) {
        this.justification = justification;
    }

    public void setParentalControls(ParentalControls parentalControls) {
        this.parentalControls = parentalControls;
    }

    public void setPlaymodeEligibility(PlaymodeEligibility playmodeEligibility) {
        this.playmodeEligibility = playmodeEligibility;
    }

    public void setTitle(String str) {
        this.title = str;
    }
}
