package Podcast.PlaybackInterface.v1_0;

import Podcast.PlaybackInterface.v1_0.PlaybackElement;
import SOACoreInterface.v1_0.SOAObject;
import java.util.Arrays;

/* JADX INFO: loaded from: classes.dex */
public class EMPMediaMetadataElement extends PlaybackElement {
    private static final int classNameHashCode = internalHashCodeCompute("Podcast.PlaybackInterface.v1_0.EMPMediaMetadataElement");
    private Long album;
    private String albumId;
    private String artist;
    private String artistId;
    private String coverId;
    private String playbackSource;
    private String playbackSourceId;
    private Long trackDurationMilliseconds;
    private String trackId;
    private String trackName;
    private Integer trackNumber;

    public static class Builder extends PlaybackElement.Builder {
        protected Long album;
        protected String albumId;
        protected String artist;
        protected String artistId;
        protected String coverId;
        protected String playbackSource;
        protected String playbackSourceId;
        protected Long trackDurationMilliseconds;
        protected String trackId;
        protected String trackName;
        protected Integer trackNumber;

        public EMPMediaMetadataElement build() {
            EMPMediaMetadataElement eMPMediaMetadataElement = new EMPMediaMetadataElement();
            populate(eMPMediaMetadataElement);
            return eMPMediaMetadataElement;
        }

        protected void populate(EMPMediaMetadataElement eMPMediaMetadataElement) {
            super.populate((PlaybackElement) eMPMediaMetadataElement);
            eMPMediaMetadataElement.setTrackId(this.trackId);
            eMPMediaMetadataElement.setTrackDurationMilliseconds(this.trackDurationMilliseconds);
            eMPMediaMetadataElement.setArtist(this.artist);
            eMPMediaMetadataElement.setTrackNumber(this.trackNumber);
            eMPMediaMetadataElement.setAlbumId(this.albumId);
            eMPMediaMetadataElement.setCoverId(this.coverId);
            eMPMediaMetadataElement.setTrackName(this.trackName);
            eMPMediaMetadataElement.setPlaybackSourceId(this.playbackSourceId);
            eMPMediaMetadataElement.setPlaybackSource(this.playbackSource);
            eMPMediaMetadataElement.setAlbum(this.album);
            eMPMediaMetadataElement.setArtistId(this.artistId);
        }

        public Builder withAlbum(Long l) {
            this.album = l;
            return this;
        }

        public Builder withAlbumId(String str) {
            this.albumId = str;
            return this;
        }

        public Builder withArtist(String str) {
            this.artist = str;
            return this;
        }

        public Builder withArtistId(String str) {
            this.artistId = str;
            return this;
        }

        public Builder withCoverId(String str) {
            this.coverId = str;
            return this;
        }

        public Builder withPlaybackSource(String str) {
            this.playbackSource = str;
            return this;
        }

        public Builder withPlaybackSourceId(String str) {
            this.playbackSourceId = str;
            return this;
        }

        public Builder withTrackDurationMilliseconds(Long l) {
            this.trackDurationMilliseconds = l;
            return this;
        }

        public Builder withTrackId(String str) {
            this.trackId = str;
            return this;
        }

        public Builder withTrackName(String str) {
            this.trackName = str;
            return this;
        }

        public Builder withTrackNumber(Integer num) {
            this.trackNumber = num;
            return this;
        }
    }

    public static Builder builder() {
        return new Builder();
    }

    private static boolean internalEqualityCheck(Object obj, Object obj2) {
        return obj == obj2 || (obj != null && obj.equals(obj2));
    }

    private static int internalHashCodeCompute(Object... objArr) {
        return Arrays.hashCode(objArr);
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // Podcast.PlaybackInterface.v1_0.PlaybackElement, SOACoreInterface.v1_0.SOAObject, java.lang.Comparable
    @Deprecated
    public int compareTo(@Deprecated SOAObject sOAObject) {
        if (sOAObject == null) {
            return -1;
        }
        if (sOAObject == this) {
            return 0;
        }
        if (!(sOAObject instanceof EMPMediaMetadataElement)) {
            return 1;
        }
        EMPMediaMetadataElement eMPMediaMetadataElement = (EMPMediaMetadataElement) sOAObject;
        String trackId = getTrackId();
        String trackId2 = eMPMediaMetadataElement.getTrackId();
        if (trackId != trackId2) {
            if (trackId == null) {
                return -1;
            }
            if (trackId2 == null) {
                return 1;
            }
            int iCompareTo = trackId.compareTo(trackId2);
            if (iCompareTo != 0) {
                return iCompareTo;
            }
        }
        Long trackDurationMilliseconds = getTrackDurationMilliseconds();
        Long trackDurationMilliseconds2 = eMPMediaMetadataElement.getTrackDurationMilliseconds();
        if (trackDurationMilliseconds != trackDurationMilliseconds2) {
            if (trackDurationMilliseconds == null) {
                return -1;
            }
            if (trackDurationMilliseconds2 == null) {
                return 1;
            }
            int iCompareTo2 = trackDurationMilliseconds.compareTo(trackDurationMilliseconds2);
            if (iCompareTo2 != 0) {
                return iCompareTo2;
            }
        }
        String artist = getArtist();
        String artist2 = eMPMediaMetadataElement.getArtist();
        if (artist != artist2) {
            if (artist == null) {
                return -1;
            }
            if (artist2 == null) {
                return 1;
            }
            int iCompareTo3 = artist.compareTo(artist2);
            if (iCompareTo3 != 0) {
                return iCompareTo3;
            }
        }
        Integer trackNumber = getTrackNumber();
        Integer trackNumber2 = eMPMediaMetadataElement.getTrackNumber();
        if (trackNumber != trackNumber2) {
            if (trackNumber == null) {
                return -1;
            }
            if (trackNumber2 == null) {
                return 1;
            }
            int iCompareTo4 = trackNumber.compareTo(trackNumber2);
            if (iCompareTo4 != 0) {
                return iCompareTo4;
            }
        }
        String albumId = getAlbumId();
        String albumId2 = eMPMediaMetadataElement.getAlbumId();
        if (albumId != albumId2) {
            if (albumId == null) {
                return -1;
            }
            if (albumId2 == null) {
                return 1;
            }
            int iCompareTo5 = albumId.compareTo(albumId2);
            if (iCompareTo5 != 0) {
                return iCompareTo5;
            }
        }
        String coverId = getCoverId();
        String coverId2 = eMPMediaMetadataElement.getCoverId();
        if (coverId != coverId2) {
            if (coverId == null) {
                return -1;
            }
            if (coverId2 == null) {
                return 1;
            }
            int iCompareTo6 = coverId.compareTo(coverId2);
            if (iCompareTo6 != 0) {
                return iCompareTo6;
            }
        }
        String trackName = getTrackName();
        String trackName2 = eMPMediaMetadataElement.getTrackName();
        if (trackName != trackName2) {
            if (trackName == null) {
                return -1;
            }
            if (trackName2 == null) {
                return 1;
            }
            int iCompareTo7 = trackName.compareTo(trackName2);
            if (iCompareTo7 != 0) {
                return iCompareTo7;
            }
        }
        String playbackSourceId = getPlaybackSourceId();
        String playbackSourceId2 = eMPMediaMetadataElement.getPlaybackSourceId();
        if (playbackSourceId != playbackSourceId2) {
            if (playbackSourceId == null) {
                return -1;
            }
            if (playbackSourceId2 == null) {
                return 1;
            }
            int iCompareTo8 = playbackSourceId.compareTo(playbackSourceId2);
            if (iCompareTo8 != 0) {
                return iCompareTo8;
            }
        }
        String playbackSource = getPlaybackSource();
        String playbackSource2 = eMPMediaMetadataElement.getPlaybackSource();
        if (playbackSource != playbackSource2) {
            if (playbackSource == null) {
                return -1;
            }
            if (playbackSource2 == null) {
                return 1;
            }
            int iCompareTo9 = playbackSource.compareTo(playbackSource2);
            if (iCompareTo9 != 0) {
                return iCompareTo9;
            }
        }
        Long album = getAlbum();
        Long album2 = eMPMediaMetadataElement.getAlbum();
        if (album != album2) {
            if (album == null) {
                return -1;
            }
            if (album2 == null) {
                return 1;
            }
            int iCompareTo10 = album.compareTo(album2);
            if (iCompareTo10 != 0) {
                return iCompareTo10;
            }
        }
        String artistId = getArtistId();
        String artistId2 = eMPMediaMetadataElement.getArtistId();
        if (artistId != artistId2) {
            if (artistId == null) {
                return -1;
            }
            if (artistId2 == null) {
                return 1;
            }
            int iCompareTo11 = artistId.compareTo(artistId2);
            if (iCompareTo11 != 0) {
                return iCompareTo11;
            }
        }
        return super.compareTo(sOAObject);
    }

    @Override // Podcast.PlaybackInterface.v1_0.PlaybackElement, SOACoreInterface.v1_0.SOAObject
    public boolean equals(Object obj) {
        if (!(obj instanceof EMPMediaMetadataElement)) {
            return false;
        }
        EMPMediaMetadataElement eMPMediaMetadataElement = (EMPMediaMetadataElement) obj;
        return super.equals(obj) && internalEqualityCheck(getTrackId(), eMPMediaMetadataElement.getTrackId()) && internalEqualityCheck(getTrackDurationMilliseconds(), eMPMediaMetadataElement.getTrackDurationMilliseconds()) && internalEqualityCheck(getArtist(), eMPMediaMetadataElement.getArtist()) && internalEqualityCheck(getTrackNumber(), eMPMediaMetadataElement.getTrackNumber()) && internalEqualityCheck(getAlbumId(), eMPMediaMetadataElement.getAlbumId()) && internalEqualityCheck(getCoverId(), eMPMediaMetadataElement.getCoverId()) && internalEqualityCheck(getTrackName(), eMPMediaMetadataElement.getTrackName()) && internalEqualityCheck(getPlaybackSourceId(), eMPMediaMetadataElement.getPlaybackSourceId()) && internalEqualityCheck(getPlaybackSource(), eMPMediaMetadataElement.getPlaybackSource()) && internalEqualityCheck(getAlbum(), eMPMediaMetadataElement.getAlbum()) && internalEqualityCheck(getArtistId(), eMPMediaMetadataElement.getArtistId());
    }

    public Long getAlbum() {
        return this.album;
    }

    public String getAlbumId() {
        return this.albumId;
    }

    public String getArtist() {
        return this.artist;
    }

    public String getArtistId() {
        return this.artistId;
    }

    public String getCoverId() {
        return this.coverId;
    }

    public String getPlaybackSource() {
        return this.playbackSource;
    }

    public String getPlaybackSourceId() {
        return this.playbackSourceId;
    }

    public Long getTrackDurationMilliseconds() {
        return this.trackDurationMilliseconds;
    }

    public String getTrackId() {
        return this.trackId;
    }

    public String getTrackName() {
        return this.trackName;
    }

    public Integer getTrackNumber() {
        return this.trackNumber;
    }

    @Override // Podcast.PlaybackInterface.v1_0.PlaybackElement, SOACoreInterface.v1_0.SOAObject
    public int hashCode() {
        return internalHashCodeCompute(Integer.valueOf(super.hashCode()), Integer.valueOf(classNameHashCode), getTrackId(), getTrackDurationMilliseconds(), getArtist(), getTrackNumber(), getAlbumId(), getCoverId(), getTrackName(), getPlaybackSourceId(), getPlaybackSource(), getAlbum(), getArtistId());
    }

    public void setAlbum(Long l) {
        this.album = l;
    }

    public void setAlbumId(String str) {
        this.albumId = str;
    }

    public void setArtist(String str) {
        this.artist = str;
    }

    public void setArtistId(String str) {
        this.artistId = str;
    }

    public void setCoverId(String str) {
        this.coverId = str;
    }

    public void setPlaybackSource(String str) {
        this.playbackSource = str;
    }

    public void setPlaybackSourceId(String str) {
        this.playbackSourceId = str;
    }

    public void setTrackDurationMilliseconds(Long l) {
        this.trackDurationMilliseconds = l;
    }

    public void setTrackId(String str) {
        this.trackId = str;
    }

    public void setTrackName(String str) {
        this.trackName = str;
    }

    public void setTrackNumber(Integer num) {
        this.trackNumber = num;
    }
}
