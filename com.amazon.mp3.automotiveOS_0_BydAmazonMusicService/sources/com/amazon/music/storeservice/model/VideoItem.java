package com.amazon.music.storeservice.model;

import com.amazon.musicensembleservice.ParentalControls;
import java.util.Arrays;
import java.util.List;

/* JADX INFO: loaded from: classes4.dex */
public class VideoItem implements Comparable<VideoItem> {
    private static final int classNameHashCode = internalHashCodeCompute("com.amazon.music.storeservice.model.VideoItem");
    private Album album;
    private Artist artist;
    private String asin;
    private List<AssetQualityItem> assetQualities;
    private String assetType;
    private Integer duration;
    private Boolean isFree;
    private Boolean isFreeOnDemandPlayable;
    private Boolean isInLibrary;
    private Boolean isMusicSubscription;
    private Boolean isPrime;
    private Boolean isSonicRush;
    private Integer itemIndex;
    private ParentalControls parentalControls;
    private String requestedAsin;
    private String title;
    private String trackNum;
    private String type;

    private static boolean internalEqualityCheck(Object obj, Object obj2) {
        return obj == obj2 || (obj != null && obj.equals(obj2));
    }

    private static int internalHashCodeCompute(Object... objArr) {
        return Arrays.hashCode(objArr);
    }

    @Override // java.lang.Comparable
    @Deprecated
    public int compareTo(@Deprecated VideoItem videoItem) {
        if (videoItem == null) {
            return -1;
        }
        if (videoItem == this) {
            return 0;
        }
        Album album = getAlbum();
        Album album2 = videoItem.getAlbum();
        if (album != album2) {
            if (album == null) {
                return -1;
            }
            if (album2 == null) {
                return 1;
            }
            int iCompareTo = album.compareTo(album2);
            if (iCompareTo != 0) {
                return iCompareTo;
            }
        }
        String title = getTitle();
        String title2 = videoItem.getTitle();
        if (title != title2) {
            if (title == null) {
                return -1;
            }
            if (title2 == null) {
                return 1;
            }
            int iCompareTo2 = title.compareTo(title2);
            if (iCompareTo2 != 0) {
                return iCompareTo2;
            }
        }
        String type = getType();
        String type2 = videoItem.getType();
        if (type != type2) {
            if (type == null) {
                return -1;
            }
            if (type2 == null) {
                return 1;
            }
            int iCompareTo3 = type.compareTo(type2);
            if (iCompareTo3 != 0) {
                return iCompareTo3;
            }
        }
        Boolean boolIsIsFree = isIsFree();
        Boolean boolIsIsFree2 = videoItem.isIsFree();
        if (boolIsIsFree != boolIsIsFree2) {
            if (boolIsIsFree == null) {
                return -1;
            }
            if (boolIsIsFree2 == null) {
                return 1;
            }
            int iCompareTo4 = boolIsIsFree.compareTo(boolIsIsFree2);
            if (iCompareTo4 != 0) {
                return iCompareTo4;
            }
        }
        String requestedAsin = getRequestedAsin();
        String requestedAsin2 = videoItem.getRequestedAsin();
        if (requestedAsin != requestedAsin2) {
            if (requestedAsin == null) {
                return -1;
            }
            if (requestedAsin2 == null) {
                return 1;
            }
            int iCompareTo5 = requestedAsin.compareTo(requestedAsin2);
            if (iCompareTo5 != 0) {
                return iCompareTo5;
            }
        }
        Artist artist = getArtist();
        Artist artist2 = videoItem.getArtist();
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
        String trackNum = getTrackNum();
        String trackNum2 = videoItem.getTrackNum();
        if (trackNum != trackNum2) {
            if (trackNum == null) {
                return -1;
            }
            if (trackNum2 == null) {
                return 1;
            }
            int iCompareTo7 = trackNum.compareTo(trackNum2);
            if (iCompareTo7 != 0) {
                return iCompareTo7;
            }
        }
        Boolean boolIsIsFreeOnDemandPlayable = isIsFreeOnDemandPlayable();
        Boolean boolIsIsFreeOnDemandPlayable2 = videoItem.isIsFreeOnDemandPlayable();
        if (boolIsIsFreeOnDemandPlayable != boolIsIsFreeOnDemandPlayable2) {
            if (boolIsIsFreeOnDemandPlayable == null) {
                return -1;
            }
            if (boolIsIsFreeOnDemandPlayable2 == null) {
                return 1;
            }
            int iCompareTo8 = boolIsIsFreeOnDemandPlayable.compareTo(boolIsIsFreeOnDemandPlayable2);
            if (iCompareTo8 != 0) {
                return iCompareTo8;
            }
        }
        ParentalControls parentalControls = getParentalControls();
        ParentalControls parentalControls2 = videoItem.getParentalControls();
        if (parentalControls != parentalControls2) {
            if (parentalControls == null) {
                return -1;
            }
            if (parentalControls2 == null) {
                return 1;
            }
            int iCompareTo9 = parentalControls.compareTo(parentalControls2);
            if (iCompareTo9 != 0) {
                return iCompareTo9;
            }
        }
        Boolean boolIsIsMusicSubscription = isIsMusicSubscription();
        Boolean boolIsIsMusicSubscription2 = videoItem.isIsMusicSubscription();
        if (boolIsIsMusicSubscription != boolIsIsMusicSubscription2) {
            if (boolIsIsMusicSubscription == null) {
                return -1;
            }
            if (boolIsIsMusicSubscription2 == null) {
                return 1;
            }
            int iCompareTo10 = boolIsIsMusicSubscription.compareTo(boolIsIsMusicSubscription2);
            if (iCompareTo10 != 0) {
                return iCompareTo10;
            }
        }
        Boolean boolIsIsInLibrary = isIsInLibrary();
        Boolean boolIsIsInLibrary2 = videoItem.isIsInLibrary();
        if (boolIsIsInLibrary != boolIsIsInLibrary2) {
            if (boolIsIsInLibrary == null) {
                return -1;
            }
            if (boolIsIsInLibrary2 == null) {
                return 1;
            }
            int iCompareTo11 = boolIsIsInLibrary.compareTo(boolIsIsInLibrary2);
            if (iCompareTo11 != 0) {
                return iCompareTo11;
            }
        }
        Integer duration = getDuration();
        Integer duration2 = videoItem.getDuration();
        if (duration != duration2) {
            if (duration == null) {
                return -1;
            }
            if (duration2 == null) {
                return 1;
            }
            int iCompareTo12 = duration.compareTo(duration2);
            if (iCompareTo12 != 0) {
                return iCompareTo12;
            }
        }
        Boolean boolIsIsPrime = isIsPrime();
        Boolean boolIsIsPrime2 = videoItem.isIsPrime();
        if (boolIsIsPrime != boolIsIsPrime2) {
            if (boolIsIsPrime == null) {
                return -1;
            }
            if (boolIsIsPrime2 == null) {
                return 1;
            }
            int iCompareTo13 = boolIsIsPrime.compareTo(boolIsIsPrime2);
            if (iCompareTo13 != 0) {
                return iCompareTo13;
            }
        }
        Boolean boolIsIsSonicRush = isIsSonicRush();
        Boolean boolIsIsSonicRush2 = videoItem.isIsSonicRush();
        if (boolIsIsSonicRush != boolIsIsSonicRush2) {
            if (boolIsIsSonicRush == null) {
                return -1;
            }
            if (boolIsIsSonicRush2 == null) {
                return 1;
            }
            int iCompareTo14 = boolIsIsSonicRush.compareTo(boolIsIsSonicRush2);
            if (iCompareTo14 != 0) {
                return iCompareTo14;
            }
        }
        String assetType = getAssetType();
        String assetType2 = videoItem.getAssetType();
        if (assetType != assetType2) {
            if (assetType == null) {
                return -1;
            }
            if (assetType2 == null) {
                return 1;
            }
            int iCompareTo15 = assetType.compareTo(assetType2);
            if (iCompareTo15 != 0) {
                return iCompareTo15;
            }
        }
        List<AssetQualityItem> assetQualities = getAssetQualities();
        List<AssetQualityItem> assetQualities2 = videoItem.getAssetQualities();
        if (assetQualities != assetQualities2) {
            if (assetQualities == null) {
                return -1;
            }
            if (assetQualities2 == null) {
                return 1;
            }
            if (assetQualities instanceof Comparable) {
                int iCompareTo16 = ((Comparable) assetQualities).compareTo(assetQualities2);
                if (iCompareTo16 != 0) {
                    return iCompareTo16;
                }
            } else if (!assetQualities.equals(assetQualities2)) {
                int iHashCode = assetQualities.hashCode();
                int iHashCode2 = assetQualities2.hashCode();
                if (iHashCode < iHashCode2) {
                    return -1;
                }
                if (iHashCode > iHashCode2) {
                    return 1;
                }
            }
        }
        String asin = getAsin();
        String asin2 = videoItem.getAsin();
        if (asin != asin2) {
            if (asin == null) {
                return -1;
            }
            if (asin2 == null) {
                return 1;
            }
            int iCompareTo17 = asin.compareTo(asin2);
            if (iCompareTo17 != 0) {
                return iCompareTo17;
            }
        }
        Integer itemIndex = getItemIndex();
        Integer itemIndex2 = videoItem.getItemIndex();
        if (itemIndex != itemIndex2) {
            if (itemIndex == null) {
                return -1;
            }
            if (itemIndex2 == null) {
                return 1;
            }
            int iCompareTo18 = itemIndex.compareTo(itemIndex2);
            if (iCompareTo18 != 0) {
                return iCompareTo18;
            }
        }
        return 0;
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof VideoItem)) {
            return false;
        }
        VideoItem videoItem = (VideoItem) obj;
        return internalEqualityCheck(getAlbum(), videoItem.getAlbum()) && internalEqualityCheck(getTitle(), videoItem.getTitle()) && internalEqualityCheck(getType(), videoItem.getType()) && internalEqualityCheck(isIsFree(), videoItem.isIsFree()) && internalEqualityCheck(getRequestedAsin(), videoItem.getRequestedAsin()) && internalEqualityCheck(getArtist(), videoItem.getArtist()) && internalEqualityCheck(getTrackNum(), videoItem.getTrackNum()) && internalEqualityCheck(isIsFreeOnDemandPlayable(), videoItem.isIsFreeOnDemandPlayable()) && internalEqualityCheck(getParentalControls(), videoItem.getParentalControls()) && internalEqualityCheck(isIsMusicSubscription(), videoItem.isIsMusicSubscription()) && internalEqualityCheck(isIsInLibrary(), videoItem.isIsInLibrary()) && internalEqualityCheck(getDuration(), videoItem.getDuration()) && internalEqualityCheck(isIsPrime(), videoItem.isIsPrime()) && internalEqualityCheck(isIsSonicRush(), videoItem.isIsSonicRush()) && internalEqualityCheck(getAssetType(), videoItem.getAssetType()) && internalEqualityCheck(getAssetQualities(), videoItem.getAssetQualities()) && internalEqualityCheck(getAsin(), videoItem.getAsin()) && internalEqualityCheck(getItemIndex(), videoItem.getItemIndex());
    }

    public Album getAlbum() {
        return this.album;
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

    public Integer getDuration() {
        return this.duration;
    }

    public Integer getItemIndex() {
        return this.itemIndex;
    }

    public ParentalControls getParentalControls() {
        return this.parentalControls;
    }

    public String getRequestedAsin() {
        return this.requestedAsin;
    }

    public String getTitle() {
        return this.title;
    }

    public String getTrackNum() {
        return this.trackNum;
    }

    public String getType() {
        return this.type;
    }

    public int hashCode() {
        return internalHashCodeCompute(Integer.valueOf(classNameHashCode), getAlbum(), getTitle(), getType(), isIsFree(), getRequestedAsin(), getArtist(), getTrackNum(), isIsFreeOnDemandPlayable(), getParentalControls(), isIsMusicSubscription(), isIsInLibrary(), getDuration(), isIsPrime(), isIsSonicRush(), getAssetType(), getAssetQualities(), getAsin(), getItemIndex());
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

    public Boolean isIsPrime() {
        return this.isPrime;
    }

    public Boolean isIsSonicRush() {
        return this.isSonicRush;
    }

    public void setAlbum(Album album) {
        this.album = album;
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

    public void setDuration(Integer num) {
        this.duration = num;
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

    public void setIsPrime(Boolean bool) {
        this.isPrime = bool;
    }

    public void setIsSonicRush(Boolean bool) {
        this.isSonicRush = bool;
    }

    public void setItemIndex(Integer num) {
        this.itemIndex = num;
    }

    public void setParentalControls(ParentalControls parentalControls) {
        this.parentalControls = parentalControls;
    }

    public void setRequestedAsin(String str) {
        this.requestedAsin = str;
    }

    public void setTitle(String str) {
        this.title = str;
    }

    public void setTrackNum(String str) {
        this.trackNum = str;
    }

    public void setType(String str) {
        this.type = str;
    }
}
