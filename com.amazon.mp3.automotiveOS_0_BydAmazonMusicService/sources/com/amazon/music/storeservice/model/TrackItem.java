package com.amazon.music.storeservice.model;

import com.amazon.musicensembleservice.ParentalControls;
import com.amazon.musicensembleservice.PlaymodeEligibility;
import java.util.Arrays;
import java.util.List;

/* JADX INFO: loaded from: classes4.dex */
public class TrackItem implements Comparable<TrackItem> {
    private static final int classNameHashCode = internalHashCodeCompute("com.amazon.music.storeservice.model.TrackItem");
    private Album album;
    private String albumArtistAsin;
    private String albumArtistName;
    private Artist artist;
    private String asin;
    private List<AssetQualityItem> assetQualities;
    private String assetType;
    private List<String> contentEncoding;
    private Integer discNum;
    private Integer duration;
    private String genreName;
    private String globalAsin;
    private Boolean hasLyrics;
    private Boolean isFree;
    private Boolean isFreeOnDemandPlayable;
    private Boolean isInLibrary;
    private Boolean isMusicSubscription;
    private Boolean isPreorderEligible;
    private Boolean isPrime;
    private Boolean isSonicRush;
    private Boolean isSonicRushOnDemandPlayable;
    private Boolean isTwitchRadio;
    private String isrc;
    private Integer itemIndex;
    private String languagesOfPerformance;
    private String objectId;
    private Long originalReleaseDate;
    private Boolean owned;
    private ParentalControls parentalControls;
    private String pid;
    private PlaymodeEligibility playmodeEligibility;
    private Double popularity;
    private String primaryArtistName;
    private Purchase purchase;
    private String requestedAsin;
    private Review reviews;
    private List<String> songWriters;
    private String spiritualCategory;
    private Long streetDate;
    private String title;
    private String trackNum;
    private TrackPurchaseOptionsData trackPurchaseOptionsData;
    private Double transformedPopularity;
    private String type;
    private VideoItem video;

    private static boolean internalEqualityCheck(Object obj, Object obj2) {
        return obj == obj2 || (obj != null && obj.equals(obj2));
    }

    private static int internalHashCodeCompute(Object... objArr) {
        return Arrays.hashCode(objArr);
    }

    @Override // java.lang.Comparable
    @Deprecated
    public int compareTo(@Deprecated TrackItem trackItem) {
        if (trackItem == null) {
            return -1;
        }
        if (trackItem == this) {
            return 0;
        }
        String title = getTitle();
        String title2 = trackItem.getTitle();
        if (title != title2) {
            if (title == null) {
                return -1;
            }
            if (title2 == null) {
                return 1;
            }
            int iCompareTo = title.compareTo(title2);
            if (iCompareTo != 0) {
                return iCompareTo;
            }
        }
        String isrc = getIsrc();
        String isrc2 = trackItem.getIsrc();
        if (isrc != isrc2) {
            if (isrc == null) {
                return -1;
            }
            if (isrc2 == null) {
                return 1;
            }
            int iCompareTo2 = isrc.compareTo(isrc2);
            if (iCompareTo2 != 0) {
                return iCompareTo2;
            }
        }
        String spiritualCategory = getSpiritualCategory();
        String spiritualCategory2 = trackItem.getSpiritualCategory();
        if (spiritualCategory != spiritualCategory2) {
            if (spiritualCategory == null) {
                return -1;
            }
            if (spiritualCategory2 == null) {
                return 1;
            }
            int iCompareTo3 = spiritualCategory.compareTo(spiritualCategory2);
            if (iCompareTo3 != 0) {
                return iCompareTo3;
            }
        }
        Review reviews = getReviews();
        Review reviews2 = trackItem.getReviews();
        if (reviews != reviews2) {
            if (reviews == null) {
                return -1;
            }
            if (reviews2 == null) {
                return 1;
            }
            int iCompareTo4 = reviews.compareTo(reviews2);
            if (iCompareTo4 != 0) {
                return iCompareTo4;
            }
        }
        String type = getType();
        String type2 = trackItem.getType();
        if (type != type2) {
            if (type == null) {
                return -1;
            }
            if (type2 == null) {
                return 1;
            }
            int iCompareTo5 = type.compareTo(type2);
            if (iCompareTo5 != 0) {
                return iCompareTo5;
            }
        }
        Artist artist = getArtist();
        Artist artist2 = trackItem.getArtist();
        if (artist != artist2) {
            if (artist == null) {
                return -1;
            }
            if (artist2 == null) {
                return 1;
            }
            int iCompareTo6 = artist.compareTo(artist2);
            if (iCompareTo6 != 0) {
                return iCompareTo6;
            }
        }
        Double popularity = getPopularity();
        Double popularity2 = trackItem.getPopularity();
        if (popularity != popularity2) {
            if (popularity == null) {
                return -1;
            }
            if (popularity2 == null) {
                return 1;
            }
            int iCompareTo7 = popularity.compareTo(popularity2);
            if (iCompareTo7 != 0) {
                return iCompareTo7;
            }
        }
        Boolean boolIsIsFree = isIsFree();
        Boolean boolIsIsFree2 = trackItem.isIsFree();
        if (boolIsIsFree != boolIsIsFree2) {
            if (boolIsIsFree == null) {
                return -1;
            }
            if (boolIsIsFree2 == null) {
                return 1;
            }
            int iCompareTo8 = boolIsIsFree.compareTo(boolIsIsFree2);
            if (iCompareTo8 != 0) {
                return iCompareTo8;
            }
        }
        Double transformedPopularity = getTransformedPopularity();
        Double transformedPopularity2 = trackItem.getTransformedPopularity();
        if (transformedPopularity != transformedPopularity2) {
            if (transformedPopularity == null) {
                return -1;
            }
            if (transformedPopularity2 == null) {
                return 1;
            }
            int iCompareTo9 = transformedPopularity.compareTo(transformedPopularity2);
            if (iCompareTo9 != 0) {
                return iCompareTo9;
            }
        }
        Integer duration = getDuration();
        Integer duration2 = trackItem.getDuration();
        if (duration != duration2) {
            if (duration == null) {
                return -1;
            }
            if (duration2 == null) {
                return 1;
            }
            int iCompareTo10 = duration.compareTo(duration2);
            if (iCompareTo10 != 0) {
                return iCompareTo10;
            }
        }
        String globalAsin = getGlobalAsin();
        String globalAsin2 = trackItem.getGlobalAsin();
        if (globalAsin != globalAsin2) {
            if (globalAsin == null) {
                return -1;
            }
            if (globalAsin2 == null) {
                return 1;
            }
            int iCompareTo11 = globalAsin.compareTo(globalAsin2);
            if (iCompareTo11 != 0) {
                return iCompareTo11;
            }
        }
        String albumArtistName = getAlbumArtistName();
        String albumArtistName2 = trackItem.getAlbumArtistName();
        if (albumArtistName != albumArtistName2) {
            if (albumArtistName == null) {
                return -1;
            }
            if (albumArtistName2 == null) {
                return 1;
            }
            int iCompareTo12 = albumArtistName.compareTo(albumArtistName2);
            if (iCompareTo12 != 0) {
                return iCompareTo12;
            }
        }
        VideoItem video = getVideo();
        VideoItem video2 = trackItem.getVideo();
        if (video != video2) {
            if (video == null) {
                return -1;
            }
            if (video2 == null) {
                return 1;
            }
            int iCompareTo13 = video.compareTo(video2);
            if (iCompareTo13 != 0) {
                return iCompareTo13;
            }
        }
        Album album = getAlbum();
        Album album2 = trackItem.getAlbum();
        if (album != album2) {
            if (album == null) {
                return -1;
            }
            if (album2 == null) {
                return 1;
            }
            int iCompareTo14 = album.compareTo(album2);
            if (iCompareTo14 != 0) {
                return iCompareTo14;
            }
        }
        String primaryArtistName = getPrimaryArtistName();
        String primaryArtistName2 = trackItem.getPrimaryArtistName();
        if (primaryArtistName != primaryArtistName2) {
            if (primaryArtistName == null) {
                return -1;
            }
            if (primaryArtistName2 == null) {
                return 1;
            }
            int iCompareTo15 = primaryArtistName.compareTo(primaryArtistName2);
            if (iCompareTo15 != 0) {
                return iCompareTo15;
            }
        }
        Boolean boolIsIsFreeOnDemandPlayable = isIsFreeOnDemandPlayable();
        Boolean boolIsIsFreeOnDemandPlayable2 = trackItem.isIsFreeOnDemandPlayable();
        if (boolIsIsFreeOnDemandPlayable != boolIsIsFreeOnDemandPlayable2) {
            if (boolIsIsFreeOnDemandPlayable == null) {
                return -1;
            }
            if (boolIsIsFreeOnDemandPlayable2 == null) {
                return 1;
            }
            int iCompareTo16 = boolIsIsFreeOnDemandPlayable.compareTo(boolIsIsFreeOnDemandPlayable2);
            if (iCompareTo16 != 0) {
                return iCompareTo16;
            }
        }
        Long originalReleaseDate = getOriginalReleaseDate();
        Long originalReleaseDate2 = trackItem.getOriginalReleaseDate();
        if (originalReleaseDate != originalReleaseDate2) {
            if (originalReleaseDate == null) {
                return -1;
            }
            if (originalReleaseDate2 == null) {
                return 1;
            }
            int iCompareTo17 = originalReleaseDate.compareTo(originalReleaseDate2);
            if (iCompareTo17 != 0) {
                return iCompareTo17;
            }
        }
        List<String> songWriters = getSongWriters();
        List<String> songWriters2 = trackItem.getSongWriters();
        if (songWriters != songWriters2) {
            if (songWriters == null) {
                return -1;
            }
            if (songWriters2 == null) {
                return 1;
            }
            if (songWriters instanceof Comparable) {
                int iCompareTo18 = ((Comparable) songWriters).compareTo(songWriters2);
                if (iCompareTo18 != 0) {
                    return iCompareTo18;
                }
            } else if (!songWriters.equals(songWriters2)) {
                int iHashCode = songWriters.hashCode();
                int iHashCode2 = songWriters2.hashCode();
                if (iHashCode < iHashCode2) {
                    return -1;
                }
                if (iHashCode > iHashCode2) {
                    return 1;
                }
            }
        }
        Boolean boolIsIsSonicRush = isIsSonicRush();
        Boolean boolIsIsSonicRush2 = trackItem.isIsSonicRush();
        if (boolIsIsSonicRush != boolIsIsSonicRush2) {
            if (boolIsIsSonicRush == null) {
                return -1;
            }
            if (boolIsIsSonicRush2 == null) {
                return 1;
            }
            int iCompareTo19 = boolIsIsSonicRush.compareTo(boolIsIsSonicRush2);
            if (iCompareTo19 != 0) {
                return iCompareTo19;
            }
        }
        String assetType = getAssetType();
        String assetType2 = trackItem.getAssetType();
        if (assetType != assetType2) {
            if (assetType == null) {
                return -1;
            }
            if (assetType2 == null) {
                return 1;
            }
            int iCompareTo20 = assetType.compareTo(assetType2);
            if (iCompareTo20 != 0) {
                return iCompareTo20;
            }
        }
        String genreName = getGenreName();
        String genreName2 = trackItem.getGenreName();
        if (genreName != genreName2) {
            if (genreName == null) {
                return -1;
            }
            if (genreName2 == null) {
                return 1;
            }
            int iCompareTo21 = genreName.compareTo(genreName2);
            if (iCompareTo21 != 0) {
                return iCompareTo21;
            }
        }
        PlaymodeEligibility playmodeEligibility = getPlaymodeEligibility();
        PlaymodeEligibility playmodeEligibility2 = trackItem.getPlaymodeEligibility();
        if (playmodeEligibility != playmodeEligibility2) {
            if (playmodeEligibility == null) {
                return -1;
            }
            if (playmodeEligibility2 == null) {
                return 1;
            }
            int iCompareTo22 = playmodeEligibility.compareTo(playmodeEligibility2);
            if (iCompareTo22 != 0) {
                return iCompareTo22;
            }
        }
        List<String> contentEncoding = getContentEncoding();
        List<String> contentEncoding2 = trackItem.getContentEncoding();
        if (contentEncoding != contentEncoding2) {
            if (contentEncoding == null) {
                return -1;
            }
            if (contentEncoding2 == null) {
                return 1;
            }
            if (contentEncoding instanceof Comparable) {
                int iCompareTo23 = ((Comparable) contentEncoding).compareTo(contentEncoding2);
                if (iCompareTo23 != 0) {
                    return iCompareTo23;
                }
            } else if (!contentEncoding.equals(contentEncoding2)) {
                int iHashCode3 = contentEncoding.hashCode();
                int iHashCode4 = contentEncoding2.hashCode();
                if (iHashCode3 < iHashCode4) {
                    return -1;
                }
                if (iHashCode3 > iHashCode4) {
                    return 1;
                }
            }
        }
        String pid = getPid();
        String pid2 = trackItem.getPid();
        if (pid != pid2) {
            if (pid == null) {
                return -1;
            }
            if (pid2 == null) {
                return 1;
            }
            int iCompareTo24 = pid.compareTo(pid2);
            if (iCompareTo24 != 0) {
                return iCompareTo24;
            }
        }
        Boolean boolIsOwned = isOwned();
        Boolean boolIsOwned2 = trackItem.isOwned();
        if (boolIsOwned != boolIsOwned2) {
            if (boolIsOwned == null) {
                return -1;
            }
            if (boolIsOwned2 == null) {
                return 1;
            }
            int iCompareTo25 = boolIsOwned.compareTo(boolIsOwned2);
            if (iCompareTo25 != 0) {
                return iCompareTo25;
            }
        }
        TrackPurchaseOptionsData trackPurchaseOptionsData = getTrackPurchaseOptionsData();
        TrackPurchaseOptionsData trackPurchaseOptionsData2 = trackItem.getTrackPurchaseOptionsData();
        if (trackPurchaseOptionsData != trackPurchaseOptionsData2) {
            if (trackPurchaseOptionsData == null) {
                return -1;
            }
            if (trackPurchaseOptionsData2 == null) {
                return 1;
            }
            int iCompareTo26 = trackPurchaseOptionsData.compareTo(trackPurchaseOptionsData2);
            if (iCompareTo26 != 0) {
                return iCompareTo26;
            }
        }
        ParentalControls parentalControls = getParentalControls();
        ParentalControls parentalControls2 = trackItem.getParentalControls();
        if (parentalControls != parentalControls2) {
            if (parentalControls == null) {
                return -1;
            }
            if (parentalControls2 == null) {
                return 1;
            }
            int iCompareTo27 = parentalControls.compareTo(parentalControls2);
            if (iCompareTo27 != 0) {
                return iCompareTo27;
            }
        }
        String requestedAsin = getRequestedAsin();
        String requestedAsin2 = trackItem.getRequestedAsin();
        if (requestedAsin != requestedAsin2) {
            if (requestedAsin == null) {
                return -1;
            }
            if (requestedAsin2 == null) {
                return 1;
            }
            int iCompareTo28 = requestedAsin.compareTo(requestedAsin2);
            if (iCompareTo28 != 0) {
                return iCompareTo28;
            }
        }
        Boolean boolIsIsMusicSubscription = isIsMusicSubscription();
        Boolean boolIsIsMusicSubscription2 = trackItem.isIsMusicSubscription();
        if (boolIsIsMusicSubscription != boolIsIsMusicSubscription2) {
            if (boolIsIsMusicSubscription == null) {
                return -1;
            }
            if (boolIsIsMusicSubscription2 == null) {
                return 1;
            }
            int iCompareTo29 = boolIsIsMusicSubscription.compareTo(boolIsIsMusicSubscription2);
            if (iCompareTo29 != 0) {
                return iCompareTo29;
            }
        }
        String trackNum = getTrackNum();
        String trackNum2 = trackItem.getTrackNum();
        if (trackNum != trackNum2) {
            if (trackNum == null) {
                return -1;
            }
            if (trackNum2 == null) {
                return 1;
            }
            int iCompareTo30 = trackNum.compareTo(trackNum2);
            if (iCompareTo30 != 0) {
                return iCompareTo30;
            }
        }
        Boolean boolIsIsPreorderEligible = isIsPreorderEligible();
        Boolean boolIsIsPreorderEligible2 = trackItem.isIsPreorderEligible();
        if (boolIsIsPreorderEligible != boolIsIsPreorderEligible2) {
            if (boolIsIsPreorderEligible == null) {
                return -1;
            }
            if (boolIsIsPreorderEligible2 == null) {
                return 1;
            }
            int iCompareTo31 = boolIsIsPreorderEligible.compareTo(boolIsIsPreorderEligible2);
            if (iCompareTo31 != 0) {
                return iCompareTo31;
            }
        }
        Boolean boolIsIsInLibrary = isIsInLibrary();
        Boolean boolIsIsInLibrary2 = trackItem.isIsInLibrary();
        if (boolIsIsInLibrary != boolIsIsInLibrary2) {
            if (boolIsIsInLibrary == null) {
                return -1;
            }
            if (boolIsIsInLibrary2 == null) {
                return 1;
            }
            int iCompareTo32 = boolIsIsInLibrary.compareTo(boolIsIsInLibrary2);
            if (iCompareTo32 != 0) {
                return iCompareTo32;
            }
        }
        Long streetDate = getStreetDate();
        Long streetDate2 = trackItem.getStreetDate();
        if (streetDate != streetDate2) {
            if (streetDate == null) {
                return -1;
            }
            if (streetDate2 == null) {
                return 1;
            }
            int iCompareTo33 = streetDate.compareTo(streetDate2);
            if (iCompareTo33 != 0) {
                return iCompareTo33;
            }
        }
        String albumArtistAsin = getAlbumArtistAsin();
        String albumArtistAsin2 = trackItem.getAlbumArtistAsin();
        if (albumArtistAsin != albumArtistAsin2) {
            if (albumArtistAsin == null) {
                return -1;
            }
            if (albumArtistAsin2 == null) {
                return 1;
            }
            int iCompareTo34 = albumArtistAsin.compareTo(albumArtistAsin2);
            if (iCompareTo34 != 0) {
                return iCompareTo34;
            }
        }
        Boolean boolIsIsSonicRushOnDemandPlayable = isIsSonicRushOnDemandPlayable();
        Boolean boolIsIsSonicRushOnDemandPlayable2 = trackItem.isIsSonicRushOnDemandPlayable();
        if (boolIsIsSonicRushOnDemandPlayable != boolIsIsSonicRushOnDemandPlayable2) {
            if (boolIsIsSonicRushOnDemandPlayable == null) {
                return -1;
            }
            if (boolIsIsSonicRushOnDemandPlayable2 == null) {
                return 1;
            }
            int iCompareTo35 = boolIsIsSonicRushOnDemandPlayable.compareTo(boolIsIsSonicRushOnDemandPlayable2);
            if (iCompareTo35 != 0) {
                return iCompareTo35;
            }
        }
        Purchase purchase = getPurchase();
        Purchase purchase2 = trackItem.getPurchase();
        if (purchase != purchase2) {
            if (purchase == null) {
                return -1;
            }
            if (purchase2 == null) {
                return 1;
            }
            int iCompareTo36 = purchase.compareTo(purchase2);
            if (iCompareTo36 != 0) {
                return iCompareTo36;
            }
        }
        Boolean boolIsIsPrime = isIsPrime();
        Boolean boolIsIsPrime2 = trackItem.isIsPrime();
        if (boolIsIsPrime != boolIsIsPrime2) {
            if (boolIsIsPrime == null) {
                return -1;
            }
            if (boolIsIsPrime2 == null) {
                return 1;
            }
            int iCompareTo37 = boolIsIsPrime.compareTo(boolIsIsPrime2);
            if (iCompareTo37 != 0) {
                return iCompareTo37;
            }
        }
        Integer discNum = getDiscNum();
        Integer discNum2 = trackItem.getDiscNum();
        if (discNum != discNum2) {
            if (discNum == null) {
                return -1;
            }
            if (discNum2 == null) {
                return 1;
            }
            int iCompareTo38 = discNum.compareTo(discNum2);
            if (iCompareTo38 != 0) {
                return iCompareTo38;
            }
        }
        String languagesOfPerformance = getLanguagesOfPerformance();
        String languagesOfPerformance2 = trackItem.getLanguagesOfPerformance();
        if (languagesOfPerformance != languagesOfPerformance2) {
            if (languagesOfPerformance == null) {
                return -1;
            }
            if (languagesOfPerformance2 == null) {
                return 1;
            }
            int iCompareTo39 = languagesOfPerformance.compareTo(languagesOfPerformance2);
            if (iCompareTo39 != 0) {
                return iCompareTo39;
            }
        }
        List<AssetQualityItem> assetQualities = getAssetQualities();
        List<AssetQualityItem> assetQualities2 = trackItem.getAssetQualities();
        if (assetQualities != assetQualities2) {
            if (assetQualities == null) {
                return -1;
            }
            if (assetQualities2 == null) {
                return 1;
            }
            if (assetQualities instanceof Comparable) {
                int iCompareTo40 = ((Comparable) assetQualities).compareTo(assetQualities2);
                if (iCompareTo40 != 0) {
                    return iCompareTo40;
                }
            } else if (!assetQualities.equals(assetQualities2)) {
                int iHashCode5 = assetQualities.hashCode();
                int iHashCode6 = assetQualities2.hashCode();
                if (iHashCode5 < iHashCode6) {
                    return -1;
                }
                if (iHashCode5 > iHashCode6) {
                    return 1;
                }
            }
        }
        Boolean boolIsIsTwitchRadio = isIsTwitchRadio();
        Boolean boolIsIsTwitchRadio2 = trackItem.isIsTwitchRadio();
        if (boolIsIsTwitchRadio != boolIsIsTwitchRadio2) {
            if (boolIsIsTwitchRadio == null) {
                return -1;
            }
            if (boolIsIsTwitchRadio2 == null) {
                return 1;
            }
            int iCompareTo41 = boolIsIsTwitchRadio.compareTo(boolIsIsTwitchRadio2);
            if (iCompareTo41 != 0) {
                return iCompareTo41;
            }
        }
        String objectId = getObjectId();
        String objectId2 = trackItem.getObjectId();
        if (objectId != objectId2) {
            if (objectId == null) {
                return -1;
            }
            if (objectId2 == null) {
                return 1;
            }
            int iCompareTo42 = objectId.compareTo(objectId2);
            if (iCompareTo42 != 0) {
                return iCompareTo42;
            }
        }
        Integer itemIndex = getItemIndex();
        Integer itemIndex2 = trackItem.getItemIndex();
        if (itemIndex != itemIndex2) {
            if (itemIndex == null) {
                return -1;
            }
            if (itemIndex2 == null) {
                return 1;
            }
            int iCompareTo43 = itemIndex.compareTo(itemIndex2);
            if (iCompareTo43 != 0) {
                return iCompareTo43;
            }
        }
        String asin = getAsin();
        String asin2 = trackItem.getAsin();
        if (asin != asin2) {
            if (asin == null) {
                return -1;
            }
            if (asin2 == null) {
                return 1;
            }
            int iCompareTo44 = asin.compareTo(asin2);
            if (iCompareTo44 != 0) {
                return iCompareTo44;
            }
        }
        Boolean boolIsHasLyrics = isHasLyrics();
        Boolean boolIsHasLyrics2 = trackItem.isHasLyrics();
        if (boolIsHasLyrics != boolIsHasLyrics2) {
            if (boolIsHasLyrics == null) {
                return -1;
            }
            if (boolIsHasLyrics2 == null) {
                return 1;
            }
            int iCompareTo45 = boolIsHasLyrics.compareTo(boolIsHasLyrics2);
            if (iCompareTo45 != 0) {
                return iCompareTo45;
            }
        }
        return 0;
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof TrackItem)) {
            return false;
        }
        TrackItem trackItem = (TrackItem) obj;
        return internalEqualityCheck(getTitle(), trackItem.getTitle()) && internalEqualityCheck(getIsrc(), trackItem.getIsrc()) && internalEqualityCheck(getSpiritualCategory(), trackItem.getSpiritualCategory()) && internalEqualityCheck(getReviews(), trackItem.getReviews()) && internalEqualityCheck(getType(), trackItem.getType()) && internalEqualityCheck(getArtist(), trackItem.getArtist()) && internalEqualityCheck(getPopularity(), trackItem.getPopularity()) && internalEqualityCheck(isIsFree(), trackItem.isIsFree()) && internalEqualityCheck(getTransformedPopularity(), trackItem.getTransformedPopularity()) && internalEqualityCheck(getDuration(), trackItem.getDuration()) && internalEqualityCheck(getGlobalAsin(), trackItem.getGlobalAsin()) && internalEqualityCheck(getAlbumArtistName(), trackItem.getAlbumArtistName()) && internalEqualityCheck(getVideo(), trackItem.getVideo()) && internalEqualityCheck(getAlbum(), trackItem.getAlbum()) && internalEqualityCheck(getPrimaryArtistName(), trackItem.getPrimaryArtistName()) && internalEqualityCheck(isIsFreeOnDemandPlayable(), trackItem.isIsFreeOnDemandPlayable()) && internalEqualityCheck(getOriginalReleaseDate(), trackItem.getOriginalReleaseDate()) && internalEqualityCheck(getSongWriters(), trackItem.getSongWriters()) && internalEqualityCheck(isIsSonicRush(), trackItem.isIsSonicRush()) && internalEqualityCheck(getAssetType(), trackItem.getAssetType()) && internalEqualityCheck(getGenreName(), trackItem.getGenreName()) && internalEqualityCheck(getPlaymodeEligibility(), trackItem.getPlaymodeEligibility()) && internalEqualityCheck(getContentEncoding(), trackItem.getContentEncoding()) && internalEqualityCheck(getPid(), trackItem.getPid()) && internalEqualityCheck(isOwned(), trackItem.isOwned()) && internalEqualityCheck(getTrackPurchaseOptionsData(), trackItem.getTrackPurchaseOptionsData()) && internalEqualityCheck(getParentalControls(), trackItem.getParentalControls()) && internalEqualityCheck(getRequestedAsin(), trackItem.getRequestedAsin()) && internalEqualityCheck(isIsMusicSubscription(), trackItem.isIsMusicSubscription()) && internalEqualityCheck(getTrackNum(), trackItem.getTrackNum()) && internalEqualityCheck(isIsPreorderEligible(), trackItem.isIsPreorderEligible()) && internalEqualityCheck(isIsInLibrary(), trackItem.isIsInLibrary()) && internalEqualityCheck(getStreetDate(), trackItem.getStreetDate()) && internalEqualityCheck(getAlbumArtistAsin(), trackItem.getAlbumArtistAsin()) && internalEqualityCheck(isIsSonicRushOnDemandPlayable(), trackItem.isIsSonicRushOnDemandPlayable()) && internalEqualityCheck(getPurchase(), trackItem.getPurchase()) && internalEqualityCheck(isIsPrime(), trackItem.isIsPrime()) && internalEqualityCheck(getDiscNum(), trackItem.getDiscNum()) && internalEqualityCheck(getLanguagesOfPerformance(), trackItem.getLanguagesOfPerformance()) && internalEqualityCheck(getAssetQualities(), trackItem.getAssetQualities()) && internalEqualityCheck(isIsTwitchRadio(), trackItem.isIsTwitchRadio()) && internalEqualityCheck(getObjectId(), trackItem.getObjectId()) && internalEqualityCheck(getItemIndex(), trackItem.getItemIndex()) && internalEqualityCheck(getAsin(), trackItem.getAsin()) && internalEqualityCheck(isHasLyrics(), trackItem.isHasLyrics());
    }

    public Album getAlbum() {
        return this.album;
    }

    public String getAlbumArtistAsin() {
        return this.albumArtistAsin;
    }

    public String getAlbumArtistName() {
        return this.albumArtistName;
    }

    public Artist getArtist() {
        return this.artist;
    }

    public String getAsin() {
        return this.asin;
    }

    public List<AssetQualityItem> getAssetQualities() {
        return this.assetQualities;
    }

    public String getAssetType() {
        return this.assetType;
    }

    public List<String> getContentEncoding() {
        return this.contentEncoding;
    }

    public Integer getDiscNum() {
        return this.discNum;
    }

    public Integer getDuration() {
        return this.duration;
    }

    public String getGenreName() {
        return this.genreName;
    }

    public String getGlobalAsin() {
        return this.globalAsin;
    }

    public String getIsrc() {
        return this.isrc;
    }

    public Integer getItemIndex() {
        return this.itemIndex;
    }

    public String getLanguagesOfPerformance() {
        return this.languagesOfPerformance;
    }

    public String getObjectId() {
        return this.objectId;
    }

    public Long getOriginalReleaseDate() {
        return this.originalReleaseDate;
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

    public String getPrimaryArtistName() {
        return this.primaryArtistName;
    }

    public Purchase getPurchase() {
        return this.purchase;
    }

    public String getRequestedAsin() {
        return this.requestedAsin;
    }

    public Review getReviews() {
        return this.reviews;
    }

    public List<String> getSongWriters() {
        return this.songWriters;
    }

    public String getSpiritualCategory() {
        return this.spiritualCategory;
    }

    public Long getStreetDate() {
        return this.streetDate;
    }

    public String getTitle() {
        return this.title;
    }

    public String getTrackNum() {
        return this.trackNum;
    }

    public TrackPurchaseOptionsData getTrackPurchaseOptionsData() {
        return this.trackPurchaseOptionsData;
    }

    public Double getTransformedPopularity() {
        return this.transformedPopularity;
    }

    public String getType() {
        return this.type;
    }

    public VideoItem getVideo() {
        return this.video;
    }

    public int hashCode() {
        return internalHashCodeCompute(Integer.valueOf(classNameHashCode), getTitle(), getIsrc(), getSpiritualCategory(), getReviews(), getType(), getArtist(), getPopularity(), isIsFree(), getTransformedPopularity(), getDuration(), getGlobalAsin(), getAlbumArtistName(), getVideo(), getAlbum(), getPrimaryArtistName(), isIsFreeOnDemandPlayable(), getOriginalReleaseDate(), getSongWriters(), isIsSonicRush(), getAssetType(), getGenreName(), getPlaymodeEligibility(), getContentEncoding(), getPid(), isOwned(), getTrackPurchaseOptionsData(), getParentalControls(), getRequestedAsin(), isIsMusicSubscription(), getTrackNum(), isIsPreorderEligible(), isIsInLibrary(), getStreetDate(), getAlbumArtistAsin(), isIsSonicRushOnDemandPlayable(), getPurchase(), isIsPrime(), getDiscNum(), getLanguagesOfPerformance(), getAssetQualities(), isIsTwitchRadio(), getObjectId(), getItemIndex(), getAsin(), isHasLyrics());
    }

    public Boolean isHasLyrics() {
        return this.hasLyrics;
    }

    public Boolean isIsFree() {
        return this.isFree;
    }

    public Boolean isIsFreeOnDemandPlayable() {
        return this.isFreeOnDemandPlayable;
    }

    public Boolean isIsInLibrary() {
        return this.isInLibrary;
    }

    public Boolean isIsMusicSubscription() {
        return this.isMusicSubscription;
    }

    public Boolean isIsPreorderEligible() {
        return this.isPreorderEligible;
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

    public Boolean isIsTwitchRadio() {
        return this.isTwitchRadio;
    }

    public Boolean isOwned() {
        return this.owned;
    }

    public void setAlbum(Album album) {
        this.album = album;
    }

    public void setAlbumArtistAsin(String str) {
        this.albumArtistAsin = str;
    }

    public void setAlbumArtistName(String str) {
        this.albumArtistName = str;
    }

    public void setArtist(Artist artist) {
        this.artist = artist;
    }

    public void setAsin(String str) {
        this.asin = str;
    }

    public void setAssetQualities(List<AssetQualityItem> list) {
        this.assetQualities = list;
    }

    public void setAssetType(String str) {
        this.assetType = str;
    }

    public void setContentEncoding(List<String> list) {
        this.contentEncoding = list;
    }

    public void setDiscNum(Integer num) {
        this.discNum = num;
    }

    public void setDuration(Integer num) {
        this.duration = num;
    }

    public void setGenreName(String str) {
        this.genreName = str;
    }

    public void setGlobalAsin(String str) {
        this.globalAsin = str;
    }

    public void setHasLyrics(Boolean bool) {
        this.hasLyrics = bool;
    }

    public void setIsFree(Boolean bool) {
        this.isFree = bool;
    }

    public void setIsFreeOnDemandPlayable(Boolean bool) {
        this.isFreeOnDemandPlayable = bool;
    }

    public void setIsInLibrary(Boolean bool) {
        this.isInLibrary = bool;
    }

    public void setIsMusicSubscription(Boolean bool) {
        this.isMusicSubscription = bool;
    }

    public void setIsPreorderEligible(Boolean bool) {
        this.isPreorderEligible = bool;
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

    public void setIsTwitchRadio(Boolean bool) {
        this.isTwitchRadio = bool;
    }

    public void setIsrc(String str) {
        this.isrc = str;
    }

    public void setItemIndex(Integer num) {
        this.itemIndex = num;
    }

    public void setLanguagesOfPerformance(String str) {
        this.languagesOfPerformance = str;
    }

    public void setObjectId(String str) {
        this.objectId = str;
    }

    public void setOriginalReleaseDate(Long l) {
        this.originalReleaseDate = l;
    }

    public void setOwned(Boolean bool) {
        this.owned = bool;
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

    public void setPrimaryArtistName(String str) {
        this.primaryArtistName = str;
    }

    public void setPurchase(Purchase purchase) {
        this.purchase = purchase;
    }

    public void setRequestedAsin(String str) {
        this.requestedAsin = str;
    }

    public void setReviews(Review review) {
        this.reviews = review;
    }

    public void setSongWriters(List<String> list) {
        this.songWriters = list;
    }

    public void setSpiritualCategory(String str) {
        this.spiritualCategory = str;
    }

    public void setStreetDate(Long l) {
        this.streetDate = l;
    }

    public void setTitle(String str) {
        this.title = str;
    }

    public void setTrackNum(String str) {
        this.trackNum = str;
    }

    public void setTrackPurchaseOptionsData(TrackPurchaseOptionsData trackPurchaseOptionsData) {
        this.trackPurchaseOptionsData = trackPurchaseOptionsData;
    }

    public void setTransformedPopularity(Double d) {
        this.transformedPopularity = d;
    }

    public void setType(String str) {
        this.type = str;
    }

    public void setVideo(VideoItem videoItem) {
        this.video = videoItem;
    }
}
