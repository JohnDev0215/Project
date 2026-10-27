package com.amazon.music.platform.model;

import java.util.Arrays;
import java.util.Date;

/* JADX INFO: loaded from: classes4.dex */
public class LibraryPlaylist extends Playlist {
    private static final int classNameHashCode = internalHashCodeCompute("com.amazon.music.platform.model.LibraryPlaylist");
    private Art bannerArt;
    private Date creationDate;
    private Art fourSquareArt;
    private Date lastModifiedDate;
    private String marketplaceId;
    private String playlistId;
    private Integer version;

    private static boolean internalEqualityCheck(Object obj, Object obj2) {
        return obj == obj2 || (obj != null && obj.equals(obj2));
    }

    private static int internalHashCodeCompute(Object... objArr) {
        return Arrays.hashCode(objArr);
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.amazon.music.platform.model.Playlist, com.amazon.music.platform.model.Document, java.lang.Comparable
    @Deprecated
    public int compareTo(@Deprecated Document document) {
        if (document == null) {
            return -1;
        }
        if (document == this) {
            return 0;
        }
        if (!(document instanceof LibraryPlaylist)) {
            return 1;
        }
        LibraryPlaylist libraryPlaylist = (LibraryPlaylist) document;
        Integer version = getVersion();
        Integer version2 = libraryPlaylist.getVersion();
        if (version != version2) {
            if (version == null) {
                return -1;
            }
            if (version2 == null) {
                return 1;
            }
            int iCompareTo = version.compareTo(version2);
            if (iCompareTo != 0) {
                return iCompareTo;
            }
        }
        Art bannerArt = getBannerArt();
        Art bannerArt2 = libraryPlaylist.getBannerArt();
        if (bannerArt != bannerArt2) {
            if (bannerArt == null) {
                return -1;
            }
            if (bannerArt2 == null) {
                return 1;
            }
            int iCompareTo2 = bannerArt.compareTo(bannerArt2);
            if (iCompareTo2 != 0) {
                return iCompareTo2;
            }
        }
        Date creationDate = getCreationDate();
        Date creationDate2 = libraryPlaylist.getCreationDate();
        if (creationDate != creationDate2) {
            if (creationDate == null) {
                return -1;
            }
            if (creationDate2 == null) {
                return 1;
            }
            int iCompareTo3 = creationDate.compareTo(creationDate2);
            if (iCompareTo3 != 0) {
                return iCompareTo3;
            }
        }
        Art fourSquareArt = getFourSquareArt();
        Art fourSquareArt2 = libraryPlaylist.getFourSquareArt();
        if (fourSquareArt != fourSquareArt2) {
            if (fourSquareArt == null) {
                return -1;
            }
            if (fourSquareArt2 == null) {
                return 1;
            }
            int iCompareTo4 = fourSquareArt.compareTo(fourSquareArt2);
            if (iCompareTo4 != 0) {
                return iCompareTo4;
            }
        }
        String playlistId = getPlaylistId();
        String playlistId2 = libraryPlaylist.getPlaylistId();
        if (playlistId != playlistId2) {
            if (playlistId == null) {
                return -1;
            }
            if (playlistId2 == null) {
                return 1;
            }
            int iCompareTo5 = playlistId.compareTo(playlistId2);
            if (iCompareTo5 != 0) {
                return iCompareTo5;
            }
        }
        String marketplaceId = getMarketplaceId();
        String marketplaceId2 = libraryPlaylist.getMarketplaceId();
        if (marketplaceId != marketplaceId2) {
            if (marketplaceId == null) {
                return -1;
            }
            if (marketplaceId2 == null) {
                return 1;
            }
            int iCompareTo6 = marketplaceId.compareTo(marketplaceId2);
            if (iCompareTo6 != 0) {
                return iCompareTo6;
            }
        }
        Date lastModifiedDate = getLastModifiedDate();
        Date lastModifiedDate2 = libraryPlaylist.getLastModifiedDate();
        if (lastModifiedDate != lastModifiedDate2) {
            if (lastModifiedDate == null) {
                return -1;
            }
            if (lastModifiedDate2 == null) {
                return 1;
            }
            int iCompareTo7 = lastModifiedDate.compareTo(lastModifiedDate2);
            if (iCompareTo7 != 0) {
                return iCompareTo7;
            }
        }
        return super.compareTo(document);
    }

    @Override // com.amazon.music.platform.model.Playlist, com.amazon.music.platform.model.Document
    public boolean equals(Object obj) {
        if (!(obj instanceof LibraryPlaylist)) {
            return false;
        }
        LibraryPlaylist libraryPlaylist = (LibraryPlaylist) obj;
        return super.equals(obj) && internalEqualityCheck(getVersion(), libraryPlaylist.getVersion()) && internalEqualityCheck(getBannerArt(), libraryPlaylist.getBannerArt()) && internalEqualityCheck(getCreationDate(), libraryPlaylist.getCreationDate()) && internalEqualityCheck(getFourSquareArt(), libraryPlaylist.getFourSquareArt()) && internalEqualityCheck(getPlaylistId(), libraryPlaylist.getPlaylistId()) && internalEqualityCheck(getMarketplaceId(), libraryPlaylist.getMarketplaceId()) && internalEqualityCheck(getLastModifiedDate(), libraryPlaylist.getLastModifiedDate());
    }

    public Art getBannerArt() {
        return this.bannerArt;
    }

    public Date getCreationDate() {
        return this.creationDate;
    }

    public Art getFourSquareArt() {
        return this.fourSquareArt;
    }

    public Date getLastModifiedDate() {
        return this.lastModifiedDate;
    }

    public String getMarketplaceId() {
        return this.marketplaceId;
    }

    public String getPlaylistId() {
        return this.playlistId;
    }

    public Integer getVersion() {
        return this.version;
    }

    @Override // com.amazon.music.platform.model.Playlist, com.amazon.music.platform.model.Document
    public int hashCode() {
        return internalHashCodeCompute(Integer.valueOf(super.hashCode()), Integer.valueOf(classNameHashCode), getVersion(), getBannerArt(), getCreationDate(), getFourSquareArt(), getPlaylistId(), getMarketplaceId(), getLastModifiedDate());
    }

    public void setBannerArt(Art art) {
        this.bannerArt = art;
    }

    public void setCreationDate(Date date) {
        this.creationDate = date;
    }

    public void setFourSquareArt(Art art) {
        this.fourSquareArt = art;
    }

    public void setLastModifiedDate(Date date) {
        this.lastModifiedDate = date;
    }

    public void setMarketplaceId(String str) {
        this.marketplaceId = str;
    }

    public void setPlaylistId(String str) {
        this.playlistId = str;
    }

    public void setVersion(Integer num) {
        this.version = num;
    }
}
