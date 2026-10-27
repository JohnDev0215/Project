package com.amazon.music.platform.model;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

/* JADX INFO: loaded from: classes4.dex */
public class CatalogVideo extends Document {
    private static final int classNameHashCode = internalHashCodeCompute("com.amazon.music.platform.model.CatalogVideo");
    private String albumArtistAsin;
    private String albumArtistName;
    private String albumAsin;
    private String albumName;
    private Date albumOriginalReleaseDate;
    private String albumPrimaryGenre;
    private Float albumRating;
    private Date albumReleaseDate;
    private List<String> alternativeName;
    private Art artExtraLarge;
    private Art artFull;
    private Art artLarge;
    private Art artMedium;
    private Art artOriginal;
    private Art artSmall;
    private Art artTiny;
    private List<RelatedArtist> artist;
    private String artistAsin;
    private String artistName;
    private String asin;
    private List<AssetQuality> assetQuality;
    private String composer;
    private List<String> contentTier;
    private Integer discNum;
    private String dmid;
    private Integer duration;
    private List<String> era;
    private List<String> genre;
    private String isMusicSubscription;
    private String isrc;
    private String lyricist;
    private List<String> mood;
    private String musicTerritory;
    private Date originalReleaseDate;
    private ParentalControls parentalControls;
    private String performer;
    private String primaryGenre;
    private String primeStatus;
    private String publisher;
    private Date singlePromotionDate;
    private String songWriter;
    private String title;
    private String titleSetAsin;
    private Integer trackNum;
    private List<String> version;

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
        if (!(document instanceof CatalogVideo)) {
            return 1;
        }
        CatalogVideo catalogVideo = (CatalogVideo) document;
        String publisher = getPublisher();
        String publisher2 = catalogVideo.getPublisher();
        if (publisher != publisher2) {
            if (publisher == null) {
                return -1;
            }
            if (publisher2 == null) {
                return 1;
            }
            int iCompareTo = publisher.compareTo(publisher2);
            if (iCompareTo != 0) {
                return iCompareTo;
            }
        }
        String primeStatus = getPrimeStatus();
        String primeStatus2 = catalogVideo.getPrimeStatus();
        if (primeStatus != primeStatus2) {
            if (primeStatus == null) {
                return -1;
            }
            if (primeStatus2 == null) {
                return 1;
            }
            int iCompareTo2 = primeStatus.compareTo(primeStatus2);
            if (iCompareTo2 != 0) {
                return iCompareTo2;
            }
        }
        Date singlePromotionDate = getSinglePromotionDate();
        Date singlePromotionDate2 = catalogVideo.getSinglePromotionDate();
        if (singlePromotionDate != singlePromotionDate2) {
            if (singlePromotionDate == null) {
                return -1;
            }
            if (singlePromotionDate2 == null) {
                return 1;
            }
            int iCompareTo3 = singlePromotionDate.compareTo(singlePromotionDate2);
            if (iCompareTo3 != 0) {
                return iCompareTo3;
            }
        }
        Art artFull = getArtFull();
        Art artFull2 = catalogVideo.getArtFull();
        if (artFull != artFull2) {
            if (artFull == null) {
                return -1;
            }
            if (artFull2 == null) {
                return 1;
            }
            int iCompareTo4 = artFull.compareTo(artFull2);
            if (iCompareTo4 != 0) {
                return iCompareTo4;
            }
        }
        Date originalReleaseDate = getOriginalReleaseDate();
        Date originalReleaseDate2 = catalogVideo.getOriginalReleaseDate();
        if (originalReleaseDate != originalReleaseDate2) {
            if (originalReleaseDate == null) {
                return -1;
            }
            if (originalReleaseDate2 == null) {
                return 1;
            }
            int iCompareTo5 = originalReleaseDate.compareTo(originalReleaseDate2);
            if (iCompareTo5 != 0) {
                return iCompareTo5;
            }
        }
        ParentalControls parentalControls = getParentalControls();
        ParentalControls parentalControls2 = catalogVideo.getParentalControls();
        if (parentalControls != parentalControls2) {
            if (parentalControls == null) {
                return -1;
            }
            if (parentalControls2 == null) {
                return 1;
            }
            int iCompareTo6 = parentalControls.compareTo(parentalControls2);
            if (iCompareTo6 != 0) {
                return iCompareTo6;
            }
        }
        Date albumReleaseDate = getAlbumReleaseDate();
        Date albumReleaseDate2 = catalogVideo.getAlbumReleaseDate();
        if (albumReleaseDate != albumReleaseDate2) {
            if (albumReleaseDate == null) {
                return -1;
            }
            if (albumReleaseDate2 == null) {
                return 1;
            }
            int iCompareTo7 = albumReleaseDate.compareTo(albumReleaseDate2);
            if (iCompareTo7 != 0) {
                return iCompareTo7;
            }
        }
        Integer trackNum = getTrackNum();
        Integer trackNum2 = catalogVideo.getTrackNum();
        if (trackNum != trackNum2) {
            if (trackNum == null) {
                return -1;
            }
            if (trackNum2 == null) {
                return 1;
            }
            int iCompareTo8 = trackNum.compareTo(trackNum2);
            if (iCompareTo8 != 0) {
                return iCompareTo8;
            }
        }
        Float albumRating = getAlbumRating();
        Float albumRating2 = catalogVideo.getAlbumRating();
        if (albumRating != albumRating2) {
            if (albumRating == null) {
                return -1;
            }
            if (albumRating2 == null) {
                return 1;
            }
            int iCompareTo9 = albumRating.compareTo(albumRating2);
            if (iCompareTo9 != 0) {
                return iCompareTo9;
            }
        }
        Art artMedium = getArtMedium();
        Art artMedium2 = catalogVideo.getArtMedium();
        if (artMedium != artMedium2) {
            if (artMedium == null) {
                return -1;
            }
            if (artMedium2 == null) {
                return 1;
            }
            int iCompareTo10 = artMedium.compareTo(artMedium2);
            if (iCompareTo10 != 0) {
                return iCompareTo10;
            }
        }
        Art artSmall = getArtSmall();
        Art artSmall2 = catalogVideo.getArtSmall();
        if (artSmall != artSmall2) {
            if (artSmall == null) {
                return -1;
            }
            if (artSmall2 == null) {
                return 1;
            }
            int iCompareTo11 = artSmall.compareTo(artSmall2);
            if (iCompareTo11 != 0) {
                return iCompareTo11;
            }
        }
        String primaryGenre = getPrimaryGenre();
        String primaryGenre2 = catalogVideo.getPrimaryGenre();
        if (primaryGenre != primaryGenre2) {
            if (primaryGenre == null) {
                return -1;
            }
            if (primaryGenre2 == null) {
                return 1;
            }
            int iCompareTo12 = primaryGenre.compareTo(primaryGenre2);
            if (iCompareTo12 != 0) {
                return iCompareTo12;
            }
        }
        String titleSetAsin = getTitleSetAsin();
        String titleSetAsin2 = catalogVideo.getTitleSetAsin();
        if (titleSetAsin != titleSetAsin2) {
            if (titleSetAsin == null) {
                return -1;
            }
            if (titleSetAsin2 == null) {
                return 1;
            }
            int iCompareTo13 = titleSetAsin.compareTo(titleSetAsin2);
            if (iCompareTo13 != 0) {
                return iCompareTo13;
            }
        }
        List<String> alternativeName = getAlternativeName();
        List<String> alternativeName2 = catalogVideo.getAlternativeName();
        if (alternativeName != alternativeName2) {
            if (alternativeName == null) {
                return -1;
            }
            if (alternativeName2 == null) {
                return 1;
            }
            if (alternativeName instanceof Comparable) {
                int iCompareTo14 = ((Comparable) alternativeName).compareTo(alternativeName2);
                if (iCompareTo14 != 0) {
                    return iCompareTo14;
                }
            } else if (!alternativeName.equals(alternativeName2)) {
                int iHashCode = alternativeName.hashCode();
                int iHashCode2 = alternativeName2.hashCode();
                if (iHashCode < iHashCode2) {
                    return -1;
                }
                if (iHashCode > iHashCode2) {
                    return 1;
                }
            }
        }
        String composer = getComposer();
        String composer2 = catalogVideo.getComposer();
        if (composer != composer2) {
            if (composer == null) {
                return -1;
            }
            if (composer2 == null) {
                return 1;
            }
            int iCompareTo15 = composer.compareTo(composer2);
            if (iCompareTo15 != 0) {
                return iCompareTo15;
            }
        }
        Date albumOriginalReleaseDate = getAlbumOriginalReleaseDate();
        Date albumOriginalReleaseDate2 = catalogVideo.getAlbumOriginalReleaseDate();
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
        List<String> version = getVersion();
        List<String> version2 = catalogVideo.getVersion();
        if (version != version2) {
            if (version == null) {
                return -1;
            }
            if (version2 == null) {
                return 1;
            }
            if (version instanceof Comparable) {
                int iCompareTo17 = ((Comparable) version).compareTo(version2);
                if (iCompareTo17 != 0) {
                    return iCompareTo17;
                }
            } else if (!version.equals(version2)) {
                int iHashCode3 = version.hashCode();
                int iHashCode4 = version2.hashCode();
                if (iHashCode3 < iHashCode4) {
                    return -1;
                }
                if (iHashCode3 > iHashCode4) {
                    return 1;
                }
            }
        }
        Integer duration = getDuration();
        Integer duration2 = catalogVideo.getDuration();
        if (duration != duration2) {
            if (duration == null) {
                return -1;
            }
            if (duration2 == null) {
                return 1;
            }
            int iCompareTo18 = duration.compareTo(duration2);
            if (iCompareTo18 != 0) {
                return iCompareTo18;
            }
        }
        String albumName = getAlbumName();
        String albumName2 = catalogVideo.getAlbumName();
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
        String albumArtistName = getAlbumArtistName();
        String albumArtistName2 = catalogVideo.getAlbumArtistName();
        if (albumArtistName != albumArtistName2) {
            if (albumArtistName == null) {
                return -1;
            }
            if (albumArtistName2 == null) {
                return 1;
            }
            int iCompareTo20 = albumArtistName.compareTo(albumArtistName2);
            if (iCompareTo20 != 0) {
                return iCompareTo20;
            }
        }
        String musicTerritory = getMusicTerritory();
        String musicTerritory2 = catalogVideo.getMusicTerritory();
        if (musicTerritory != musicTerritory2) {
            if (musicTerritory == null) {
                return -1;
            }
            if (musicTerritory2 == null) {
                return 1;
            }
            int iCompareTo21 = musicTerritory.compareTo(musicTerritory2);
            if (iCompareTo21 != 0) {
                return iCompareTo21;
            }
        }
        String albumPrimaryGenre = getAlbumPrimaryGenre();
        String albumPrimaryGenre2 = catalogVideo.getAlbumPrimaryGenre();
        if (albumPrimaryGenre != albumPrimaryGenre2) {
            if (albumPrimaryGenre == null) {
                return -1;
            }
            if (albumPrimaryGenre2 == null) {
                return 1;
            }
            int iCompareTo22 = albumPrimaryGenre.compareTo(albumPrimaryGenre2);
            if (iCompareTo22 != 0) {
                return iCompareTo22;
            }
        }
        Art artExtraLarge = getArtExtraLarge();
        Art artExtraLarge2 = catalogVideo.getArtExtraLarge();
        if (artExtraLarge != artExtraLarge2) {
            if (artExtraLarge == null) {
                return -1;
            }
            if (artExtraLarge2 == null) {
                return 1;
            }
            int iCompareTo23 = artExtraLarge.compareTo(artExtraLarge2);
            if (iCompareTo23 != 0) {
                return iCompareTo23;
            }
        }
        String artistAsin = getArtistAsin();
        String artistAsin2 = catalogVideo.getArtistAsin();
        if (artistAsin != artistAsin2) {
            if (artistAsin == null) {
                return -1;
            }
            if (artistAsin2 == null) {
                return 1;
            }
            int iCompareTo24 = artistAsin.compareTo(artistAsin2);
            if (iCompareTo24 != 0) {
                return iCompareTo24;
            }
        }
        String isrc = getIsrc();
        String isrc2 = catalogVideo.getIsrc();
        if (isrc != isrc2) {
            if (isrc == null) {
                return -1;
            }
            if (isrc2 == null) {
                return 1;
            }
            int iCompareTo25 = isrc.compareTo(isrc2);
            if (iCompareTo25 != 0) {
                return iCompareTo25;
            }
        }
        Art artLarge = getArtLarge();
        Art artLarge2 = catalogVideo.getArtLarge();
        if (artLarge != artLarge2) {
            if (artLarge == null) {
                return -1;
            }
            if (artLarge2 == null) {
                return 1;
            }
            int iCompareTo26 = artLarge.compareTo(artLarge2);
            if (iCompareTo26 != 0) {
                return iCompareTo26;
            }
        }
        String artistName = getArtistName();
        String artistName2 = catalogVideo.getArtistName();
        if (artistName != artistName2) {
            if (artistName == null) {
                return -1;
            }
            if (artistName2 == null) {
                return 1;
            }
            int iCompareTo27 = artistName.compareTo(artistName2);
            if (iCompareTo27 != 0) {
                return iCompareTo27;
            }
        }
        String asin = getAsin();
        String asin2 = catalogVideo.getAsin();
        if (asin != asin2) {
            if (asin == null) {
                return -1;
            }
            if (asin2 == null) {
                return 1;
            }
            int iCompareTo28 = asin.compareTo(asin2);
            if (iCompareTo28 != 0) {
                return iCompareTo28;
            }
        }
        List<String> contentTier = getContentTier();
        List<String> contentTier2 = catalogVideo.getContentTier();
        if (contentTier != contentTier2) {
            if (contentTier == null) {
                return -1;
            }
            if (contentTier2 == null) {
                return 1;
            }
            if (contentTier instanceof Comparable) {
                int iCompareTo29 = ((Comparable) contentTier).compareTo(contentTier2);
                if (iCompareTo29 != 0) {
                    return iCompareTo29;
                }
            } else if (!contentTier.equals(contentTier2)) {
                int iHashCode5 = contentTier.hashCode();
                int iHashCode6 = contentTier2.hashCode();
                if (iHashCode5 < iHashCode6) {
                    return -1;
                }
                if (iHashCode5 > iHashCode6) {
                    return 1;
                }
            }
        }
        List<String> era = getEra();
        List<String> era2 = catalogVideo.getEra();
        if (era != era2) {
            if (era == null) {
                return -1;
            }
            if (era2 == null) {
                return 1;
            }
            if (era instanceof Comparable) {
                int iCompareTo30 = ((Comparable) era).compareTo(era2);
                if (iCompareTo30 != 0) {
                    return iCompareTo30;
                }
            } else if (!era.equals(era2)) {
                int iHashCode7 = era.hashCode();
                int iHashCode8 = era2.hashCode();
                if (iHashCode7 < iHashCode8) {
                    return -1;
                }
                if (iHashCode7 > iHashCode8) {
                    return 1;
                }
            }
        }
        List<String> mood = getMood();
        List<String> mood2 = catalogVideo.getMood();
        if (mood != mood2) {
            if (mood == null) {
                return -1;
            }
            if (mood2 == null) {
                return 1;
            }
            if (mood instanceof Comparable) {
                int iCompareTo31 = ((Comparable) mood).compareTo(mood2);
                if (iCompareTo31 != 0) {
                    return iCompareTo31;
                }
            } else if (!mood.equals(mood2)) {
                int iHashCode9 = mood.hashCode();
                int iHashCode10 = mood2.hashCode();
                if (iHashCode9 < iHashCode10) {
                    return -1;
                }
                if (iHashCode9 > iHashCode10) {
                    return 1;
                }
            }
        }
        String title = getTitle();
        String title2 = catalogVideo.getTitle();
        if (title != title2) {
            if (title == null) {
                return -1;
            }
            if (title2 == null) {
                return 1;
            }
            int iCompareTo32 = title.compareTo(title2);
            if (iCompareTo32 != 0) {
                return iCompareTo32;
            }
        }
        String dmid = getDmid();
        String dmid2 = catalogVideo.getDmid();
        if (dmid != dmid2) {
            if (dmid == null) {
                return -1;
            }
            if (dmid2 == null) {
                return 1;
            }
            int iCompareTo33 = dmid.compareTo(dmid2);
            if (iCompareTo33 != 0) {
                return iCompareTo33;
            }
        }
        String songWriter = getSongWriter();
        String songWriter2 = catalogVideo.getSongWriter();
        if (songWriter != songWriter2) {
            if (songWriter == null) {
                return -1;
            }
            if (songWriter2 == null) {
                return 1;
            }
            int iCompareTo34 = songWriter.compareTo(songWriter2);
            if (iCompareTo34 != 0) {
                return iCompareTo34;
            }
        }
        Integer discNum = getDiscNum();
        Integer discNum2 = catalogVideo.getDiscNum();
        if (discNum != discNum2) {
            if (discNum == null) {
                return -1;
            }
            if (discNum2 == null) {
                return 1;
            }
            int iCompareTo35 = discNum.compareTo(discNum2);
            if (iCompareTo35 != 0) {
                return iCompareTo35;
            }
        }
        String lyricist = getLyricist();
        String lyricist2 = catalogVideo.getLyricist();
        if (lyricist != lyricist2) {
            if (lyricist == null) {
                return -1;
            }
            if (lyricist2 == null) {
                return 1;
            }
            int iCompareTo36 = lyricist.compareTo(lyricist2);
            if (iCompareTo36 != 0) {
                return iCompareTo36;
            }
        }
        String albumArtistAsin = getAlbumArtistAsin();
        String albumArtistAsin2 = catalogVideo.getAlbumArtistAsin();
        if (albumArtistAsin != albumArtistAsin2) {
            if (albumArtistAsin == null) {
                return -1;
            }
            if (albumArtistAsin2 == null) {
                return 1;
            }
            int iCompareTo37 = albumArtistAsin.compareTo(albumArtistAsin2);
            if (iCompareTo37 != 0) {
                return iCompareTo37;
            }
        }
        List<RelatedArtist> artist = getArtist();
        List<RelatedArtist> artist2 = catalogVideo.getArtist();
        if (artist != artist2) {
            if (artist == null) {
                return -1;
            }
            if (artist2 == null) {
                return 1;
            }
            if (artist instanceof Comparable) {
                int iCompareTo38 = ((Comparable) artist).compareTo(artist2);
                if (iCompareTo38 != 0) {
                    return iCompareTo38;
                }
            } else if (!artist.equals(artist2)) {
                int iHashCode11 = artist.hashCode();
                int iHashCode12 = artist2.hashCode();
                if (iHashCode11 < iHashCode12) {
                    return -1;
                }
                if (iHashCode11 > iHashCode12) {
                    return 1;
                }
            }
        }
        String isMusicSubscription = getIsMusicSubscription();
        String isMusicSubscription2 = catalogVideo.getIsMusicSubscription();
        if (isMusicSubscription != isMusicSubscription2) {
            if (isMusicSubscription == null) {
                return -1;
            }
            if (isMusicSubscription2 == null) {
                return 1;
            }
            int iCompareTo39 = isMusicSubscription.compareTo(isMusicSubscription2);
            if (iCompareTo39 != 0) {
                return iCompareTo39;
            }
        }
        String albumAsin = getAlbumAsin();
        String albumAsin2 = catalogVideo.getAlbumAsin();
        if (albumAsin != albumAsin2) {
            if (albumAsin == null) {
                return -1;
            }
            if (albumAsin2 == null) {
                return 1;
            }
            int iCompareTo40 = albumAsin.compareTo(albumAsin2);
            if (iCompareTo40 != 0) {
                return iCompareTo40;
            }
        }
        List<AssetQuality> assetQuality = getAssetQuality();
        List<AssetQuality> assetQuality2 = catalogVideo.getAssetQuality();
        if (assetQuality != assetQuality2) {
            if (assetQuality == null) {
                return -1;
            }
            if (assetQuality2 == null) {
                return 1;
            }
            if (assetQuality instanceof Comparable) {
                int iCompareTo41 = ((Comparable) assetQuality).compareTo(assetQuality2);
                if (iCompareTo41 != 0) {
                    return iCompareTo41;
                }
            } else if (!assetQuality.equals(assetQuality2)) {
                int iHashCode13 = assetQuality.hashCode();
                int iHashCode14 = assetQuality2.hashCode();
                if (iHashCode13 < iHashCode14) {
                    return -1;
                }
                if (iHashCode13 > iHashCode14) {
                    return 1;
                }
            }
        }
        List<String> genre = getGenre();
        List<String> genre2 = catalogVideo.getGenre();
        if (genre != genre2) {
            if (genre == null) {
                return -1;
            }
            if (genre2 == null) {
                return 1;
            }
            if (genre instanceof Comparable) {
                int iCompareTo42 = ((Comparable) genre).compareTo(genre2);
                if (iCompareTo42 != 0) {
                    return iCompareTo42;
                }
            } else if (!genre.equals(genre2)) {
                int iHashCode15 = genre.hashCode();
                int iHashCode16 = genre2.hashCode();
                if (iHashCode15 < iHashCode16) {
                    return -1;
                }
                if (iHashCode15 > iHashCode16) {
                    return 1;
                }
            }
        }
        Art artOriginal = getArtOriginal();
        Art artOriginal2 = catalogVideo.getArtOriginal();
        if (artOriginal != artOriginal2) {
            if (artOriginal == null) {
                return -1;
            }
            if (artOriginal2 == null) {
                return 1;
            }
            int iCompareTo43 = artOriginal.compareTo(artOriginal2);
            if (iCompareTo43 != 0) {
                return iCompareTo43;
            }
        }
        Art artTiny = getArtTiny();
        Art artTiny2 = catalogVideo.getArtTiny();
        if (artTiny != artTiny2) {
            if (artTiny == null) {
                return -1;
            }
            if (artTiny2 == null) {
                return 1;
            }
            int iCompareTo44 = artTiny.compareTo(artTiny2);
            if (iCompareTo44 != 0) {
                return iCompareTo44;
            }
        }
        String performer = getPerformer();
        String performer2 = catalogVideo.getPerformer();
        if (performer != performer2) {
            if (performer == null) {
                return -1;
            }
            if (performer2 == null) {
                return 1;
            }
            int iCompareTo45 = performer.compareTo(performer2);
            if (iCompareTo45 != 0) {
                return iCompareTo45;
            }
        }
        return super.compareTo(document);
    }

    @Override // com.amazon.music.platform.model.Document
    public boolean equals(Object obj) {
        if (!(obj instanceof CatalogVideo)) {
            return false;
        }
        CatalogVideo catalogVideo = (CatalogVideo) obj;
        return super.equals(obj) && internalEqualityCheck(getPublisher(), catalogVideo.getPublisher()) && internalEqualityCheck(getPrimeStatus(), catalogVideo.getPrimeStatus()) && internalEqualityCheck(getSinglePromotionDate(), catalogVideo.getSinglePromotionDate()) && internalEqualityCheck(getArtFull(), catalogVideo.getArtFull()) && internalEqualityCheck(getOriginalReleaseDate(), catalogVideo.getOriginalReleaseDate()) && internalEqualityCheck(getParentalControls(), catalogVideo.getParentalControls()) && internalEqualityCheck(getAlbumReleaseDate(), catalogVideo.getAlbumReleaseDate()) && internalEqualityCheck(getTrackNum(), catalogVideo.getTrackNum()) && internalEqualityCheck(getAlbumRating(), catalogVideo.getAlbumRating()) && internalEqualityCheck(getArtMedium(), catalogVideo.getArtMedium()) && internalEqualityCheck(getArtSmall(), catalogVideo.getArtSmall()) && internalEqualityCheck(getPrimaryGenre(), catalogVideo.getPrimaryGenre()) && internalEqualityCheck(getTitleSetAsin(), catalogVideo.getTitleSetAsin()) && internalEqualityCheck(getAlternativeName(), catalogVideo.getAlternativeName()) && internalEqualityCheck(getComposer(), catalogVideo.getComposer()) && internalEqualityCheck(getAlbumOriginalReleaseDate(), catalogVideo.getAlbumOriginalReleaseDate()) && internalEqualityCheck(getVersion(), catalogVideo.getVersion()) && internalEqualityCheck(getDuration(), catalogVideo.getDuration()) && internalEqualityCheck(getAlbumName(), catalogVideo.getAlbumName()) && internalEqualityCheck(getAlbumArtistName(), catalogVideo.getAlbumArtistName()) && internalEqualityCheck(getMusicTerritory(), catalogVideo.getMusicTerritory()) && internalEqualityCheck(getAlbumPrimaryGenre(), catalogVideo.getAlbumPrimaryGenre()) && internalEqualityCheck(getArtExtraLarge(), catalogVideo.getArtExtraLarge()) && internalEqualityCheck(getArtistAsin(), catalogVideo.getArtistAsin()) && internalEqualityCheck(getIsrc(), catalogVideo.getIsrc()) && internalEqualityCheck(getArtLarge(), catalogVideo.getArtLarge()) && internalEqualityCheck(getArtistName(), catalogVideo.getArtistName()) && internalEqualityCheck(getAsin(), catalogVideo.getAsin()) && internalEqualityCheck(getContentTier(), catalogVideo.getContentTier()) && internalEqualityCheck(getEra(), catalogVideo.getEra()) && internalEqualityCheck(getMood(), catalogVideo.getMood()) && internalEqualityCheck(getTitle(), catalogVideo.getTitle()) && internalEqualityCheck(getDmid(), catalogVideo.getDmid()) && internalEqualityCheck(getSongWriter(), catalogVideo.getSongWriter()) && internalEqualityCheck(getDiscNum(), catalogVideo.getDiscNum()) && internalEqualityCheck(getLyricist(), catalogVideo.getLyricist()) && internalEqualityCheck(getAlbumArtistAsin(), catalogVideo.getAlbumArtistAsin()) && internalEqualityCheck(getArtist(), catalogVideo.getArtist()) && internalEqualityCheck(getIsMusicSubscription(), catalogVideo.getIsMusicSubscription()) && internalEqualityCheck(getAlbumAsin(), catalogVideo.getAlbumAsin()) && internalEqualityCheck(getAssetQuality(), catalogVideo.getAssetQuality()) && internalEqualityCheck(getGenre(), catalogVideo.getGenre()) && internalEqualityCheck(getArtOriginal(), catalogVideo.getArtOriginal()) && internalEqualityCheck(getArtTiny(), catalogVideo.getArtTiny()) && internalEqualityCheck(getPerformer(), catalogVideo.getPerformer());
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

    public Date getAlbumOriginalReleaseDate() {
        return this.albumOriginalReleaseDate;
    }

    public String getAlbumPrimaryGenre() {
        return this.albumPrimaryGenre;
    }

    public Float getAlbumRating() {
        return this.albumRating;
    }

    public Date getAlbumReleaseDate() {
        return this.albumReleaseDate;
    }

    public List<String> getAlternativeName() {
        return this.alternativeName;
    }

    public Art getArtExtraLarge() {
        return this.artExtraLarge;
    }

    public Art getArtFull() {
        return this.artFull;
    }

    public Art getArtLarge() {
        return this.artLarge;
    }

    public Art getArtMedium() {
        return this.artMedium;
    }

    public Art getArtOriginal() {
        return this.artOriginal;
    }

    public Art getArtSmall() {
        return this.artSmall;
    }

    public Art getArtTiny() {
        return this.artTiny;
    }

    public List<RelatedArtist> getArtist() {
        return this.artist;
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

    public List<AssetQuality> getAssetQuality() {
        return this.assetQuality;
    }

    public String getComposer() {
        return this.composer;
    }

    public List<String> getContentTier() {
        return this.contentTier;
    }

    public Integer getDiscNum() {
        return this.discNum;
    }

    public String getDmid() {
        return this.dmid;
    }

    public Integer getDuration() {
        return this.duration;
    }

    public List<String> getEra() {
        return this.era;
    }

    public List<String> getGenre() {
        return this.genre;
    }

    public String getIsMusicSubscription() {
        return this.isMusicSubscription;
    }

    public String getIsrc() {
        return this.isrc;
    }

    public String getLyricist() {
        return this.lyricist;
    }

    public List<String> getMood() {
        return this.mood;
    }

    public String getMusicTerritory() {
        return this.musicTerritory;
    }

    public Date getOriginalReleaseDate() {
        return this.originalReleaseDate;
    }

    public ParentalControls getParentalControls() {
        return this.parentalControls;
    }

    public String getPerformer() {
        return this.performer;
    }

    public String getPrimaryGenre() {
        return this.primaryGenre;
    }

    public String getPrimeStatus() {
        return this.primeStatus;
    }

    public String getPublisher() {
        return this.publisher;
    }

    public Date getSinglePromotionDate() {
        return this.singlePromotionDate;
    }

    public String getSongWriter() {
        return this.songWriter;
    }

    public String getTitle() {
        return this.title;
    }

    public String getTitleSetAsin() {
        return this.titleSetAsin;
    }

    public Integer getTrackNum() {
        return this.trackNum;
    }

    public List<String> getVersion() {
        return this.version;
    }

    @Override // com.amazon.music.platform.model.Document
    public int hashCode() {
        return internalHashCodeCompute(Integer.valueOf(super.hashCode()), Integer.valueOf(classNameHashCode), getPublisher(), getPrimeStatus(), getSinglePromotionDate(), getArtFull(), getOriginalReleaseDate(), getParentalControls(), getAlbumReleaseDate(), getTrackNum(), getAlbumRating(), getArtMedium(), getArtSmall(), getPrimaryGenre(), getTitleSetAsin(), getAlternativeName(), getComposer(), getAlbumOriginalReleaseDate(), getVersion(), getDuration(), getAlbumName(), getAlbumArtistName(), getMusicTerritory(), getAlbumPrimaryGenre(), getArtExtraLarge(), getArtistAsin(), getIsrc(), getArtLarge(), getArtistName(), getAsin(), getContentTier(), getEra(), getMood(), getTitle(), getDmid(), getSongWriter(), getDiscNum(), getLyricist(), getAlbumArtistAsin(), getArtist(), getIsMusicSubscription(), getAlbumAsin(), getAssetQuality(), getGenre(), getArtOriginal(), getArtTiny(), getPerformer());
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

    public void setAlbumOriginalReleaseDate(Date date) {
        this.albumOriginalReleaseDate = date;
    }

    public void setAlbumPrimaryGenre(String str) {
        this.albumPrimaryGenre = str;
    }

    public void setAlbumRating(Float f) {
        this.albumRating = f;
    }

    public void setAlbumReleaseDate(Date date) {
        this.albumReleaseDate = date;
    }

    public void setAlternativeName(List<String> list) {
        this.alternativeName = list;
    }

    public void setArtExtraLarge(Art art) {
        this.artExtraLarge = art;
    }

    public void setArtFull(Art art) {
        this.artFull = art;
    }

    public void setArtLarge(Art art) {
        this.artLarge = art;
    }

    public void setArtMedium(Art art) {
        this.artMedium = art;
    }

    public void setArtOriginal(Art art) {
        this.artOriginal = art;
    }

    public void setArtSmall(Art art) {
        this.artSmall = art;
    }

    public void setArtTiny(Art art) {
        this.artTiny = art;
    }

    public void setArtist(List<RelatedArtist> list) {
        this.artist = list;
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

    public void setAssetQuality(List<AssetQuality> list) {
        this.assetQuality = list;
    }

    public void setComposer(String str) {
        this.composer = str;
    }

    public void setContentTier(List<String> list) {
        this.contentTier = list;
    }

    public void setDiscNum(Integer num) {
        this.discNum = num;
    }

    public void setDmid(String str) {
        this.dmid = str;
    }

    public void setDuration(Integer num) {
        this.duration = num;
    }

    public void setEra(List<String> list) {
        this.era = list;
    }

    public void setGenre(List<String> list) {
        this.genre = list;
    }

    public void setIsMusicSubscription(String str) {
        this.isMusicSubscription = str;
    }

    public void setIsrc(String str) {
        this.isrc = str;
    }

    public void setLyricist(String str) {
        this.lyricist = str;
    }

    public void setMood(List<String> list) {
        this.mood = list;
    }

    public void setMusicTerritory(String str) {
        this.musicTerritory = str;
    }

    public void setOriginalReleaseDate(Date date) {
        this.originalReleaseDate = date;
    }

    public void setParentalControls(ParentalControls parentalControls) {
        this.parentalControls = parentalControls;
    }

    public void setPerformer(String str) {
        this.performer = str;
    }

    public void setPrimaryGenre(String str) {
        this.primaryGenre = str;
    }

    public void setPrimeStatus(String str) {
        this.primeStatus = str;
    }

    public void setPublisher(String str) {
        this.publisher = str;
    }

    public void setSinglePromotionDate(Date date) {
        this.singlePromotionDate = date;
    }

    public void setSongWriter(String str) {
        this.songWriter = str;
    }

    public void setTitle(String str) {
        this.title = str;
    }

    public void setTitleSetAsin(String str) {
        this.titleSetAsin = str;
    }

    public void setTrackNum(Integer num) {
        this.trackNum = num;
    }

    public void setVersion(List<String> list) {
        this.version = list;
    }
}
