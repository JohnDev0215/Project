package com.amazon.music.platform.model;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

/* JADX INFO: loaded from: classes4.dex */
public abstract class Track extends Document {
    private static final int classNameHashCode = internalHashCodeCompute("com.amazon.music.platform.model.Track");
    private String albumArtistAsin;
    private String albumArtistName;
    private String albumAsin;
    private String albumName;
    private String albumPrimaryGenre;
    private Float albumRating;
    private Date albumReleaseDate;
    private Art artExtraLarge;
    private Art artFull;
    private Art artLarge;
    private Art artMedium;
    private Art artOriginal;
    private Art artSmall;
    private Art artTiny;
    private String artistAsin;
    private String artistName;
    private String asin;
    private String composer;
    private List<String> contentEncoding;
    private List<String> contentTier;
    private Integer discNum;
    private String dmid;
    private Integer duration;
    private String isMusicSubscription;
    private String isrc;
    private String lyricist;
    private String marketplaceId;
    private ParentalControls parentalControls;
    private String performer;
    private String primaryGenre;
    private String primeStatus;
    private String publisher;
    private String songWriter;
    private String title;
    private Integer trackNum;

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
        if (!(document instanceof Track)) {
            return 1;
        }
        Track track = (Track) document;
        String dmid = getDmid();
        String dmid2 = track.getDmid();
        if (dmid != dmid2) {
            if (dmid == null) {
                return -1;
            }
            if (dmid2 == null) {
                return 1;
            }
            int iCompareTo = dmid.compareTo(dmid2);
            if (iCompareTo != 0) {
                return iCompareTo;
            }
        }
        String songWriter = getSongWriter();
        String songWriter2 = track.getSongWriter();
        if (songWriter != songWriter2) {
            if (songWriter == null) {
                return -1;
            }
            if (songWriter2 == null) {
                return 1;
            }
            int iCompareTo2 = songWriter.compareTo(songWriter2);
            if (iCompareTo2 != 0) {
                return iCompareTo2;
            }
        }
        String albumArtistName = getAlbumArtistName();
        String albumArtistName2 = track.getAlbumArtistName();
        if (albumArtistName != albumArtistName2) {
            if (albumArtistName == null) {
                return -1;
            }
            if (albumArtistName2 == null) {
                return 1;
            }
            int iCompareTo3 = albumArtistName.compareTo(albumArtistName2);
            if (iCompareTo3 != 0) {
                return iCompareTo3;
            }
        }
        String title = getTitle();
        String title2 = track.getTitle();
        if (title != title2) {
            if (title == null) {
                return -1;
            }
            if (title2 == null) {
                return 1;
            }
            int iCompareTo4 = title.compareTo(title2);
            if (iCompareTo4 != 0) {
                return iCompareTo4;
            }
        }
        Integer duration = getDuration();
        Integer duration2 = track.getDuration();
        if (duration != duration2) {
            if (duration == null) {
                return -1;
            }
            if (duration2 == null) {
                return 1;
            }
            int iCompareTo5 = duration.compareTo(duration2);
            if (iCompareTo5 != 0) {
                return iCompareTo5;
            }
        }
        Art artLarge = getArtLarge();
        Art artLarge2 = track.getArtLarge();
        if (artLarge != artLarge2) {
            if (artLarge == null) {
                return -1;
            }
            if (artLarge2 == null) {
                return 1;
            }
            int iCompareTo6 = artLarge.compareTo(artLarge2);
            if (iCompareTo6 != 0) {
                return iCompareTo6;
            }
        }
        String asin = getAsin();
        String asin2 = track.getAsin();
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
        List<String> contentTier = getContentTier();
        List<String> contentTier2 = track.getContentTier();
        if (contentTier != contentTier2) {
            if (contentTier == null) {
                return -1;
            }
            if (contentTier2 == null) {
                return 1;
            }
            if (contentTier instanceof Comparable) {
                int iCompareTo8 = ((Comparable) contentTier).compareTo(contentTier2);
                if (iCompareTo8 != 0) {
                    return iCompareTo8;
                }
            } else if (!contentTier.equals(contentTier2)) {
                int iHashCode = contentTier.hashCode();
                int iHashCode2 = contentTier2.hashCode();
                if (iHashCode < iHashCode2) {
                    return -1;
                }
                if (iHashCode > iHashCode2) {
                    return 1;
                }
            }
        }
        Art artExtraLarge = getArtExtraLarge();
        Art artExtraLarge2 = track.getArtExtraLarge();
        if (artExtraLarge != artExtraLarge2) {
            if (artExtraLarge == null) {
                return -1;
            }
            if (artExtraLarge2 == null) {
                return 1;
            }
            int iCompareTo9 = artExtraLarge.compareTo(artExtraLarge2);
            if (iCompareTo9 != 0) {
                return iCompareTo9;
            }
        }
        String albumPrimaryGenre = getAlbumPrimaryGenre();
        String albumPrimaryGenre2 = track.getAlbumPrimaryGenre();
        if (albumPrimaryGenre != albumPrimaryGenre2) {
            if (albumPrimaryGenre == null) {
                return -1;
            }
            if (albumPrimaryGenre2 == null) {
                return 1;
            }
            int iCompareTo10 = albumPrimaryGenre.compareTo(albumPrimaryGenre2);
            if (iCompareTo10 != 0) {
                return iCompareTo10;
            }
        }
        String isrc = getIsrc();
        String isrc2 = track.getIsrc();
        if (isrc != isrc2) {
            if (isrc == null) {
                return -1;
            }
            if (isrc2 == null) {
                return 1;
            }
            int iCompareTo11 = isrc.compareTo(isrc2);
            if (iCompareTo11 != 0) {
                return iCompareTo11;
            }
        }
        String albumArtistAsin = getAlbumArtistAsin();
        String albumArtistAsin2 = track.getAlbumArtistAsin();
        if (albumArtistAsin != albumArtistAsin2) {
            if (albumArtistAsin == null) {
                return -1;
            }
            if (albumArtistAsin2 == null) {
                return 1;
            }
            int iCompareTo12 = albumArtistAsin.compareTo(albumArtistAsin2);
            if (iCompareTo12 != 0) {
                return iCompareTo12;
            }
        }
        Float albumRating = getAlbumRating();
        Float albumRating2 = track.getAlbumRating();
        if (albumRating != albumRating2) {
            if (albumRating == null) {
                return -1;
            }
            if (albumRating2 == null) {
                return 1;
            }
            int iCompareTo13 = albumRating.compareTo(albumRating2);
            if (iCompareTo13 != 0) {
                return iCompareTo13;
            }
        }
        Integer trackNum = getTrackNum();
        Integer trackNum2 = track.getTrackNum();
        if (trackNum != trackNum2) {
            if (trackNum == null) {
                return -1;
            }
            if (trackNum2 == null) {
                return 1;
            }
            int iCompareTo14 = trackNum.compareTo(trackNum2);
            if (iCompareTo14 != 0) {
                return iCompareTo14;
            }
        }
        String albumAsin = getAlbumAsin();
        String albumAsin2 = track.getAlbumAsin();
        if (albumAsin != albumAsin2) {
            if (albumAsin == null) {
                return -1;
            }
            if (albumAsin2 == null) {
                return 1;
            }
            int iCompareTo15 = albumAsin.compareTo(albumAsin2);
            if (iCompareTo15 != 0) {
                return iCompareTo15;
            }
        }
        Integer discNum = getDiscNum();
        Integer discNum2 = track.getDiscNum();
        if (discNum != discNum2) {
            if (discNum == null) {
                return -1;
            }
            if (discNum2 == null) {
                return 1;
            }
            int iCompareTo16 = discNum.compareTo(discNum2);
            if (iCompareTo16 != 0) {
                return iCompareTo16;
            }
        }
        Art artFull = getArtFull();
        Art artFull2 = track.getArtFull();
        if (artFull != artFull2) {
            if (artFull == null) {
                return -1;
            }
            if (artFull2 == null) {
                return 1;
            }
            int iCompareTo17 = artFull.compareTo(artFull2);
            if (iCompareTo17 != 0) {
                return iCompareTo17;
            }
        }
        String primaryGenre = getPrimaryGenre();
        String primaryGenre2 = track.getPrimaryGenre();
        if (primaryGenre != primaryGenre2) {
            if (primaryGenre == null) {
                return -1;
            }
            if (primaryGenre2 == null) {
                return 1;
            }
            int iCompareTo18 = primaryGenre.compareTo(primaryGenre2);
            if (iCompareTo18 != 0) {
                return iCompareTo18;
            }
        }
        String marketplaceId = getMarketplaceId();
        String marketplaceId2 = track.getMarketplaceId();
        if (marketplaceId != marketplaceId2) {
            if (marketplaceId == null) {
                return -1;
            }
            if (marketplaceId2 == null) {
                return 1;
            }
            int iCompareTo19 = marketplaceId.compareTo(marketplaceId2);
            if (iCompareTo19 != 0) {
                return iCompareTo19;
            }
        }
        String isMusicSubscription = getIsMusicSubscription();
        String isMusicSubscription2 = track.getIsMusicSubscription();
        if (isMusicSubscription != isMusicSubscription2) {
            if (isMusicSubscription == null) {
                return -1;
            }
            if (isMusicSubscription2 == null) {
                return 1;
            }
            int iCompareTo20 = isMusicSubscription.compareTo(isMusicSubscription2);
            if (iCompareTo20 != 0) {
                return iCompareTo20;
            }
        }
        Art artMedium = getArtMedium();
        Art artMedium2 = track.getArtMedium();
        if (artMedium != artMedium2) {
            if (artMedium == null) {
                return -1;
            }
            if (artMedium2 == null) {
                return 1;
            }
            int iCompareTo21 = artMedium.compareTo(artMedium2);
            if (iCompareTo21 != 0) {
                return iCompareTo21;
            }
        }
        String lyricist = getLyricist();
        String lyricist2 = track.getLyricist();
        if (lyricist != lyricist2) {
            if (lyricist == null) {
                return -1;
            }
            if (lyricist2 == null) {
                return 1;
            }
            int iCompareTo22 = lyricist.compareTo(lyricist2);
            if (iCompareTo22 != 0) {
                return iCompareTo22;
            }
        }
        Date albumReleaseDate = getAlbumReleaseDate();
        Date albumReleaseDate2 = track.getAlbumReleaseDate();
        if (albumReleaseDate != albumReleaseDate2) {
            if (albumReleaseDate == null) {
                return -1;
            }
            if (albumReleaseDate2 == null) {
                return 1;
            }
            int iCompareTo23 = albumReleaseDate.compareTo(albumReleaseDate2);
            if (iCompareTo23 != 0) {
                return iCompareTo23;
            }
        }
        String albumName = getAlbumName();
        String albumName2 = track.getAlbumName();
        if (albumName != albumName2) {
            if (albumName == null) {
                return -1;
            }
            if (albumName2 == null) {
                return 1;
            }
            int iCompareTo24 = albumName.compareTo(albumName2);
            if (iCompareTo24 != 0) {
                return iCompareTo24;
            }
        }
        String publisher = getPublisher();
        String publisher2 = track.getPublisher();
        if (publisher != publisher2) {
            if (publisher == null) {
                return -1;
            }
            if (publisher2 == null) {
                return 1;
            }
            int iCompareTo25 = publisher.compareTo(publisher2);
            if (iCompareTo25 != 0) {
                return iCompareTo25;
            }
        }
        Art artTiny = getArtTiny();
        Art artTiny2 = track.getArtTiny();
        if (artTiny != artTiny2) {
            if (artTiny == null) {
                return -1;
            }
            if (artTiny2 == null) {
                return 1;
            }
            int iCompareTo26 = artTiny.compareTo(artTiny2);
            if (iCompareTo26 != 0) {
                return iCompareTo26;
            }
        }
        String composer = getComposer();
        String composer2 = track.getComposer();
        if (composer != composer2) {
            if (composer == null) {
                return -1;
            }
            if (composer2 == null) {
                return 1;
            }
            int iCompareTo27 = composer.compareTo(composer2);
            if (iCompareTo27 != 0) {
                return iCompareTo27;
            }
        }
        String artistAsin = getArtistAsin();
        String artistAsin2 = track.getArtistAsin();
        if (artistAsin != artistAsin2) {
            if (artistAsin == null) {
                return -1;
            }
            if (artistAsin2 == null) {
                return 1;
            }
            int iCompareTo28 = artistAsin.compareTo(artistAsin2);
            if (iCompareTo28 != 0) {
                return iCompareTo28;
            }
        }
        List<String> contentEncoding = getContentEncoding();
        List<String> contentEncoding2 = track.getContentEncoding();
        if (contentEncoding != contentEncoding2) {
            if (contentEncoding == null) {
                return -1;
            }
            if (contentEncoding2 == null) {
                return 1;
            }
            if (contentEncoding instanceof Comparable) {
                int iCompareTo29 = ((Comparable) contentEncoding).compareTo(contentEncoding2);
                if (iCompareTo29 != 0) {
                    return iCompareTo29;
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
        String performer = getPerformer();
        String performer2 = track.getPerformer();
        if (performer != performer2) {
            if (performer == null) {
                return -1;
            }
            if (performer2 == null) {
                return 1;
            }
            int iCompareTo30 = performer.compareTo(performer2);
            if (iCompareTo30 != 0) {
                return iCompareTo30;
            }
        }
        Art artOriginal = getArtOriginal();
        Art artOriginal2 = track.getArtOriginal();
        if (artOriginal != artOriginal2) {
            if (artOriginal == null) {
                return -1;
            }
            if (artOriginal2 == null) {
                return 1;
            }
            int iCompareTo31 = artOriginal.compareTo(artOriginal2);
            if (iCompareTo31 != 0) {
                return iCompareTo31;
            }
        }
        ParentalControls parentalControls = getParentalControls();
        ParentalControls parentalControls2 = track.getParentalControls();
        if (parentalControls != parentalControls2) {
            if (parentalControls == null) {
                return -1;
            }
            if (parentalControls2 == null) {
                return 1;
            }
            int iCompareTo32 = parentalControls.compareTo(parentalControls2);
            if (iCompareTo32 != 0) {
                return iCompareTo32;
            }
        }
        String artistName = getArtistName();
        String artistName2 = track.getArtistName();
        if (artistName != artistName2) {
            if (artistName == null) {
                return -1;
            }
            if (artistName2 == null) {
                return 1;
            }
            int iCompareTo33 = artistName.compareTo(artistName2);
            if (iCompareTo33 != 0) {
                return iCompareTo33;
            }
        }
        String primeStatus = getPrimeStatus();
        String primeStatus2 = track.getPrimeStatus();
        if (primeStatus != primeStatus2) {
            if (primeStatus == null) {
                return -1;
            }
            if (primeStatus2 == null) {
                return 1;
            }
            int iCompareTo34 = primeStatus.compareTo(primeStatus2);
            if (iCompareTo34 != 0) {
                return iCompareTo34;
            }
        }
        Art artSmall = getArtSmall();
        Art artSmall2 = track.getArtSmall();
        if (artSmall != artSmall2) {
            if (artSmall == null) {
                return -1;
            }
            if (artSmall2 == null) {
                return 1;
            }
            int iCompareTo35 = artSmall.compareTo(artSmall2);
            if (iCompareTo35 != 0) {
                return iCompareTo35;
            }
        }
        return super.compareTo(document);
    }

    @Override // com.amazon.music.platform.model.Document
    public boolean equals(Object obj) {
        if (!(obj instanceof Track)) {
            return false;
        }
        Track track = (Track) obj;
        return super.equals(obj) && internalEqualityCheck(getDmid(), track.getDmid()) && internalEqualityCheck(getSongWriter(), track.getSongWriter()) && internalEqualityCheck(getAlbumArtistName(), track.getAlbumArtistName()) && internalEqualityCheck(getTitle(), track.getTitle()) && internalEqualityCheck(getDuration(), track.getDuration()) && internalEqualityCheck(getArtLarge(), track.getArtLarge()) && internalEqualityCheck(getAsin(), track.getAsin()) && internalEqualityCheck(getContentTier(), track.getContentTier()) && internalEqualityCheck(getArtExtraLarge(), track.getArtExtraLarge()) && internalEqualityCheck(getAlbumPrimaryGenre(), track.getAlbumPrimaryGenre()) && internalEqualityCheck(getIsrc(), track.getIsrc()) && internalEqualityCheck(getAlbumArtistAsin(), track.getAlbumArtistAsin()) && internalEqualityCheck(getAlbumRating(), track.getAlbumRating()) && internalEqualityCheck(getTrackNum(), track.getTrackNum()) && internalEqualityCheck(getAlbumAsin(), track.getAlbumAsin()) && internalEqualityCheck(getDiscNum(), track.getDiscNum()) && internalEqualityCheck(getArtFull(), track.getArtFull()) && internalEqualityCheck(getPrimaryGenre(), track.getPrimaryGenre()) && internalEqualityCheck(getMarketplaceId(), track.getMarketplaceId()) && internalEqualityCheck(getIsMusicSubscription(), track.getIsMusicSubscription()) && internalEqualityCheck(getArtMedium(), track.getArtMedium()) && internalEqualityCheck(getLyricist(), track.getLyricist()) && internalEqualityCheck(getAlbumReleaseDate(), track.getAlbumReleaseDate()) && internalEqualityCheck(getAlbumName(), track.getAlbumName()) && internalEqualityCheck(getPublisher(), track.getPublisher()) && internalEqualityCheck(getArtTiny(), track.getArtTiny()) && internalEqualityCheck(getComposer(), track.getComposer()) && internalEqualityCheck(getArtistAsin(), track.getArtistAsin()) && internalEqualityCheck(getContentEncoding(), track.getContentEncoding()) && internalEqualityCheck(getPerformer(), track.getPerformer()) && internalEqualityCheck(getArtOriginal(), track.getArtOriginal()) && internalEqualityCheck(getParentalControls(), track.getParentalControls()) && internalEqualityCheck(getArtistName(), track.getArtistName()) && internalEqualityCheck(getPrimeStatus(), track.getPrimeStatus()) && internalEqualityCheck(getArtSmall(), track.getArtSmall());
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

    public String getAlbumPrimaryGenre() {
        return this.albumPrimaryGenre;
    }

    public Float getAlbumRating() {
        return this.albumRating;
    }

    public Date getAlbumReleaseDate() {
        return this.albumReleaseDate;
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

    public String getArtistAsin() {
        return this.artistAsin;
    }

    public String getArtistName() {
        return this.artistName;
    }

    public String getAsin() {
        return this.asin;
    }

    public String getComposer() {
        return this.composer;
    }

    public List<String> getContentEncoding() {
        return this.contentEncoding;
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

    public String getIsMusicSubscription() {
        return this.isMusicSubscription;
    }

    public String getIsrc() {
        return this.isrc;
    }

    public String getLyricist() {
        return this.lyricist;
    }

    public String getMarketplaceId() {
        return this.marketplaceId;
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

    public String getSongWriter() {
        return this.songWriter;
    }

    public String getTitle() {
        return this.title;
    }

    public Integer getTrackNum() {
        return this.trackNum;
    }

    @Override // com.amazon.music.platform.model.Document
    public int hashCode() {
        return internalHashCodeCompute(Integer.valueOf(super.hashCode()), Integer.valueOf(classNameHashCode), getDmid(), getSongWriter(), getAlbumArtistName(), getTitle(), getDuration(), getArtLarge(), getAsin(), getContentTier(), getArtExtraLarge(), getAlbumPrimaryGenre(), getIsrc(), getAlbumArtistAsin(), getAlbumRating(), getTrackNum(), getAlbumAsin(), getDiscNum(), getArtFull(), getPrimaryGenre(), getMarketplaceId(), getIsMusicSubscription(), getArtMedium(), getLyricist(), getAlbumReleaseDate(), getAlbumName(), getPublisher(), getArtTiny(), getComposer(), getArtistAsin(), getContentEncoding(), getPerformer(), getArtOriginal(), getParentalControls(), getArtistName(), getPrimeStatus(), getArtSmall());
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

    public void setAlbumPrimaryGenre(String str) {
        this.albumPrimaryGenre = str;
    }

    public void setAlbumRating(Float f) {
        this.albumRating = f;
    }

    public void setAlbumReleaseDate(Date date) {
        this.albumReleaseDate = date;
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

    public void setArtistAsin(String str) {
        this.artistAsin = str;
    }

    public void setArtistName(String str) {
        this.artistName = str;
    }

    public void setAsin(String str) {
        this.asin = str;
    }

    public void setComposer(String str) {
        this.composer = str;
    }

    public void setContentEncoding(List<String> list) {
        this.contentEncoding = list;
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

    public void setIsMusicSubscription(String str) {
        this.isMusicSubscription = str;
    }

    public void setIsrc(String str) {
        this.isrc = str;
    }

    public void setLyricist(String str) {
        this.lyricist = str;
    }

    public void setMarketplaceId(String str) {
        this.marketplaceId = str;
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

    public void setSongWriter(String str) {
        this.songWriter = str;
    }

    public void setTitle(String str) {
        this.title = str;
    }

    public void setTrackNum(Integer num) {
        this.trackNum = num;
    }
}
