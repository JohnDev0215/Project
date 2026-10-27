package com.amazon.music.platform.model;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

/* JADX INFO: loaded from: classes4.dex */
public abstract class Album extends Document {
    private static final int classNameHashCode = internalHashCodeCompute("com.amazon.music.platform.model.Album");
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
    private List<String> contentEncoding;
    private List<String> contentTier;
    private String isMusicSubscription;
    private String primaryGenre;
    private String primeStatus;
    private Date releaseDate;
    private String title;
    private Integer trackCount;

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
        if (!(document instanceof Album)) {
            return 1;
        }
        Album album = (Album) document;
        String artistAsin = getArtistAsin();
        String artistAsin2 = album.getArtistAsin();
        if (artistAsin != artistAsin2) {
            if (artistAsin == null) {
                return -1;
            }
            if (artistAsin2 == null) {
                return 1;
            }
            int iCompareTo = artistAsin.compareTo(artistAsin2);
            if (iCompareTo != 0) {
                return iCompareTo;
            }
        }
        Art artMedium = getArtMedium();
        Art artMedium2 = album.getArtMedium();
        if (artMedium != artMedium2) {
            if (artMedium == null) {
                return -1;
            }
            if (artMedium2 == null) {
                return 1;
            }
            int iCompareTo2 = artMedium.compareTo(artMedium2);
            if (iCompareTo2 != 0) {
                return iCompareTo2;
            }
        }
        Art artExtraLarge = getArtExtraLarge();
        Art artExtraLarge2 = album.getArtExtraLarge();
        if (artExtraLarge != artExtraLarge2) {
            if (artExtraLarge == null) {
                return -1;
            }
            if (artExtraLarge2 == null) {
                return 1;
            }
            int iCompareTo3 = artExtraLarge.compareTo(artExtraLarge2);
            if (iCompareTo3 != 0) {
                return iCompareTo3;
            }
        }
        Integer trackCount = getTrackCount();
        Integer trackCount2 = album.getTrackCount();
        if (trackCount != trackCount2) {
            if (trackCount == null) {
                return -1;
            }
            if (trackCount2 == null) {
                return 1;
            }
            int iCompareTo4 = trackCount.compareTo(trackCount2);
            if (iCompareTo4 != 0) {
                return iCompareTo4;
            }
        }
        String isMusicSubscription = getIsMusicSubscription();
        String isMusicSubscription2 = album.getIsMusicSubscription();
        if (isMusicSubscription != isMusicSubscription2) {
            if (isMusicSubscription == null) {
                return -1;
            }
            if (isMusicSubscription2 == null) {
                return 1;
            }
            int iCompareTo5 = isMusicSubscription.compareTo(isMusicSubscription2);
            if (iCompareTo5 != 0) {
                return iCompareTo5;
            }
        }
        List<String> contentEncoding = getContentEncoding();
        List<String> contentEncoding2 = album.getContentEncoding();
        if (contentEncoding != contentEncoding2) {
            if (contentEncoding == null) {
                return -1;
            }
            if (contentEncoding2 == null) {
                return 1;
            }
            if (contentEncoding instanceof Comparable) {
                int iCompareTo6 = ((Comparable) contentEncoding).compareTo(contentEncoding2);
                if (iCompareTo6 != 0) {
                    return iCompareTo6;
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
        List<String> contentTier = getContentTier();
        List<String> contentTier2 = album.getContentTier();
        if (contentTier != contentTier2) {
            if (contentTier == null) {
                return -1;
            }
            if (contentTier2 == null) {
                return 1;
            }
            if (contentTier instanceof Comparable) {
                int iCompareTo7 = ((Comparable) contentTier).compareTo(contentTier2);
                if (iCompareTo7 != 0) {
                    return iCompareTo7;
                }
            } else if (!contentTier.equals(contentTier2)) {
                int iHashCode3 = contentTier.hashCode();
                int iHashCode4 = contentTier2.hashCode();
                if (iHashCode3 < iHashCode4) {
                    return -1;
                }
                if (iHashCode3 > iHashCode4) {
                    return 1;
                }
            }
        }
        Art artTiny = getArtTiny();
        Art artTiny2 = album.getArtTiny();
        if (artTiny != artTiny2) {
            if (artTiny == null) {
                return -1;
            }
            if (artTiny2 == null) {
                return 1;
            }
            int iCompareTo8 = artTiny.compareTo(artTiny2);
            if (iCompareTo8 != 0) {
                return iCompareTo8;
            }
        }
        Date releaseDate = getReleaseDate();
        Date releaseDate2 = album.getReleaseDate();
        if (releaseDate != releaseDate2) {
            if (releaseDate == null) {
                return -1;
            }
            if (releaseDate2 == null) {
                return 1;
            }
            int iCompareTo9 = releaseDate.compareTo(releaseDate2);
            if (iCompareTo9 != 0) {
                return iCompareTo9;
            }
        }
        Art artLarge = getArtLarge();
        Art artLarge2 = album.getArtLarge();
        if (artLarge != artLarge2) {
            if (artLarge == null) {
                return -1;
            }
            if (artLarge2 == null) {
                return 1;
            }
            int iCompareTo10 = artLarge.compareTo(artLarge2);
            if (iCompareTo10 != 0) {
                return iCompareTo10;
            }
        }
        Art artFull = getArtFull();
        Art artFull2 = album.getArtFull();
        if (artFull != artFull2) {
            if (artFull == null) {
                return -1;
            }
            if (artFull2 == null) {
                return 1;
            }
            int iCompareTo11 = artFull.compareTo(artFull2);
            if (iCompareTo11 != 0) {
                return iCompareTo11;
            }
        }
        Art artOriginal = getArtOriginal();
        Art artOriginal2 = album.getArtOriginal();
        if (artOriginal != artOriginal2) {
            if (artOriginal == null) {
                return -1;
            }
            if (artOriginal2 == null) {
                return 1;
            }
            int iCompareTo12 = artOriginal.compareTo(artOriginal2);
            if (iCompareTo12 != 0) {
                return iCompareTo12;
            }
        }
        String asin = getAsin();
        String asin2 = album.getAsin();
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
        String primeStatus = getPrimeStatus();
        String primeStatus2 = album.getPrimeStatus();
        if (primeStatus != primeStatus2) {
            if (primeStatus == null) {
                return -1;
            }
            if (primeStatus2 == null) {
                return 1;
            }
            int iCompareTo14 = primeStatus.compareTo(primeStatus2);
            if (iCompareTo14 != 0) {
                return iCompareTo14;
            }
        }
        Art artSmall = getArtSmall();
        Art artSmall2 = album.getArtSmall();
        if (artSmall != artSmall2) {
            if (artSmall == null) {
                return -1;
            }
            if (artSmall2 == null) {
                return 1;
            }
            int iCompareTo15 = artSmall.compareTo(artSmall2);
            if (iCompareTo15 != 0) {
                return iCompareTo15;
            }
        }
        String title = getTitle();
        String title2 = album.getTitle();
        if (title != title2) {
            if (title == null) {
                return -1;
            }
            if (title2 == null) {
                return 1;
            }
            int iCompareTo16 = title.compareTo(title2);
            if (iCompareTo16 != 0) {
                return iCompareTo16;
            }
        }
        String artistName = getArtistName();
        String artistName2 = album.getArtistName();
        if (artistName != artistName2) {
            if (artistName == null) {
                return -1;
            }
            if (artistName2 == null) {
                return 1;
            }
            int iCompareTo17 = artistName.compareTo(artistName2);
            if (iCompareTo17 != 0) {
                return iCompareTo17;
            }
        }
        String primaryGenre = getPrimaryGenre();
        String primaryGenre2 = album.getPrimaryGenre();
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
        return super.compareTo(document);
    }

    @Override // com.amazon.music.platform.model.Document
    public boolean equals(Object obj) {
        if (!(obj instanceof Album)) {
            return false;
        }
        Album album = (Album) obj;
        return super.equals(obj) && internalEqualityCheck(getArtistAsin(), album.getArtistAsin()) && internalEqualityCheck(getArtMedium(), album.getArtMedium()) && internalEqualityCheck(getArtExtraLarge(), album.getArtExtraLarge()) && internalEqualityCheck(getTrackCount(), album.getTrackCount()) && internalEqualityCheck(getIsMusicSubscription(), album.getIsMusicSubscription()) && internalEqualityCheck(getContentEncoding(), album.getContentEncoding()) && internalEqualityCheck(getContentTier(), album.getContentTier()) && internalEqualityCheck(getArtTiny(), album.getArtTiny()) && internalEqualityCheck(getReleaseDate(), album.getReleaseDate()) && internalEqualityCheck(getArtLarge(), album.getArtLarge()) && internalEqualityCheck(getArtFull(), album.getArtFull()) && internalEqualityCheck(getArtOriginal(), album.getArtOriginal()) && internalEqualityCheck(getAsin(), album.getAsin()) && internalEqualityCheck(getPrimeStatus(), album.getPrimeStatus()) && internalEqualityCheck(getArtSmall(), album.getArtSmall()) && internalEqualityCheck(getTitle(), album.getTitle()) && internalEqualityCheck(getArtistName(), album.getArtistName()) && internalEqualityCheck(getPrimaryGenre(), album.getPrimaryGenre());
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

    public List<String> getContentEncoding() {
        return this.contentEncoding;
    }

    public List<String> getContentTier() {
        return this.contentTier;
    }

    public String getIsMusicSubscription() {
        return this.isMusicSubscription;
    }

    public String getPrimaryGenre() {
        return this.primaryGenre;
    }

    public String getPrimeStatus() {
        return this.primeStatus;
    }

    public Date getReleaseDate() {
        return this.releaseDate;
    }

    public String getTitle() {
        return this.title;
    }

    public Integer getTrackCount() {
        return this.trackCount;
    }

    @Override // com.amazon.music.platform.model.Document
    public int hashCode() {
        return internalHashCodeCompute(Integer.valueOf(super.hashCode()), Integer.valueOf(classNameHashCode), getArtistAsin(), getArtMedium(), getArtExtraLarge(), getTrackCount(), getIsMusicSubscription(), getContentEncoding(), getContentTier(), getArtTiny(), getReleaseDate(), getArtLarge(), getArtFull(), getArtOriginal(), getAsin(), getPrimeStatus(), getArtSmall(), getTitle(), getArtistName(), getPrimaryGenre());
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

    public void setContentEncoding(List<String> list) {
        this.contentEncoding = list;
    }

    public void setContentTier(List<String> list) {
        this.contentTier = list;
    }

    public void setIsMusicSubscription(String str) {
        this.isMusicSubscription = str;
    }

    public void setPrimaryGenre(String str) {
        this.primaryGenre = str;
    }

    public void setPrimeStatus(String str) {
        this.primeStatus = str;
    }

    public void setReleaseDate(Date date) {
        this.releaseDate = date;
    }

    public void setTitle(String str) {
        this.title = str;
    }

    public void setTrackCount(Integer num) {
        this.trackCount = num;
    }
}
