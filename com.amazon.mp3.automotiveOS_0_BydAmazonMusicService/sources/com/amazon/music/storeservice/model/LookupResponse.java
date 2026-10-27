package com.amazon.music.storeservice.model;

import java.util.Arrays;
import java.util.List;

/* JADX INFO: loaded from: classes4.dex */
public class LookupResponse extends MuseResponse {
    private static final int classNameHashCode = internalHashCodeCompute("com.amazon.music.storeservice.model.LookupResponse");
    private List<AlbumItem> albumList;
    private List<ArtistItem> artistList;
    private GeneralMetadata metadata;
    private List<PlaylistItem> playlistList;
    private List<TrackItem> trackList;

    private static boolean internalEqualityCheck(Object obj, Object obj2) {
        return obj == obj2 || (obj != null && obj.equals(obj2));
    }

    private static int internalHashCodeCompute(Object... objArr) {
        return Arrays.hashCode(objArr);
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.amazon.music.storeservice.model.MuseResponse, java.lang.Comparable
    @Deprecated
    public int compareTo(@Deprecated MuseResponse museResponse) {
        if (museResponse == null) {
            return -1;
        }
        if (museResponse == this) {
            return 0;
        }
        if (!(museResponse instanceof LookupResponse)) {
            return 1;
        }
        LookupResponse lookupResponse = (LookupResponse) museResponse;
        List<AlbumItem> albumList = getAlbumList();
        List<AlbumItem> albumList2 = lookupResponse.getAlbumList();
        if (albumList != albumList2) {
            if (albumList == null) {
                return -1;
            }
            if (albumList2 == null) {
                return 1;
            }
            if (albumList instanceof Comparable) {
                int iCompareTo = ((Comparable) albumList).compareTo(albumList2);
                if (iCompareTo != 0) {
                    return iCompareTo;
                }
            } else if (!albumList.equals(albumList2)) {
                int iHashCode = albumList.hashCode();
                int iHashCode2 = albumList2.hashCode();
                if (iHashCode < iHashCode2) {
                    return -1;
                }
                if (iHashCode > iHashCode2) {
                    return 1;
                }
            }
        }
        List<TrackItem> trackList = getTrackList();
        List<TrackItem> trackList2 = lookupResponse.getTrackList();
        if (trackList != trackList2) {
            if (trackList == null) {
                return -1;
            }
            if (trackList2 == null) {
                return 1;
            }
            if (trackList instanceof Comparable) {
                int iCompareTo2 = ((Comparable) trackList).compareTo(trackList2);
                if (iCompareTo2 != 0) {
                    return iCompareTo2;
                }
            } else if (!trackList.equals(trackList2)) {
                int iHashCode3 = trackList.hashCode();
                int iHashCode4 = trackList2.hashCode();
                if (iHashCode3 < iHashCode4) {
                    return -1;
                }
                if (iHashCode3 > iHashCode4) {
                    return 1;
                }
            }
        }
        List<PlaylistItem> playlistList = getPlaylistList();
        List<PlaylistItem> playlistList2 = lookupResponse.getPlaylistList();
        if (playlistList != playlistList2) {
            if (playlistList == null) {
                return -1;
            }
            if (playlistList2 == null) {
                return 1;
            }
            if (playlistList instanceof Comparable) {
                int iCompareTo3 = ((Comparable) playlistList).compareTo(playlistList2);
                if (iCompareTo3 != 0) {
                    return iCompareTo3;
                }
            } else if (!playlistList.equals(playlistList2)) {
                int iHashCode5 = playlistList.hashCode();
                int iHashCode6 = playlistList2.hashCode();
                if (iHashCode5 < iHashCode6) {
                    return -1;
                }
                if (iHashCode5 > iHashCode6) {
                    return 1;
                }
            }
        }
        GeneralMetadata metadata = getMetadata();
        GeneralMetadata metadata2 = lookupResponse.getMetadata();
        if (metadata != metadata2) {
            if (metadata == null) {
                return -1;
            }
            if (metadata2 == null) {
                return 1;
            }
            int iCompareTo4 = metadata.compareTo(metadata2);
            if (iCompareTo4 != 0) {
                return iCompareTo4;
            }
        }
        List<ArtistItem> artistList = getArtistList();
        List<ArtistItem> artistList2 = lookupResponse.getArtistList();
        if (artistList != artistList2) {
            if (artistList == null) {
                return -1;
            }
            if (artistList2 == null) {
                return 1;
            }
            if (artistList instanceof Comparable) {
                int iCompareTo5 = ((Comparable) artistList).compareTo(artistList2);
                if (iCompareTo5 != 0) {
                    return iCompareTo5;
                }
            } else if (!artistList.equals(artistList2)) {
                int iHashCode7 = artistList.hashCode();
                int iHashCode8 = artistList2.hashCode();
                if (iHashCode7 < iHashCode8) {
                    return -1;
                }
                if (iHashCode7 > iHashCode8) {
                    return 1;
                }
            }
        }
        return super.compareTo(museResponse);
    }

    @Override // com.amazon.music.storeservice.model.MuseResponse
    public boolean equals(Object obj) {
        if (!(obj instanceof LookupResponse)) {
            return false;
        }
        LookupResponse lookupResponse = (LookupResponse) obj;
        return super.equals(obj) && internalEqualityCheck(getAlbumList(), lookupResponse.getAlbumList()) && internalEqualityCheck(getTrackList(), lookupResponse.getTrackList()) && internalEqualityCheck(getPlaylistList(), lookupResponse.getPlaylistList()) && internalEqualityCheck(getMetadata(), lookupResponse.getMetadata()) && internalEqualityCheck(getArtistList(), lookupResponse.getArtistList());
    }

    public List<AlbumItem> getAlbumList() {
        return this.albumList;
    }

    public List<ArtistItem> getArtistList() {
        return this.artistList;
    }

    public GeneralMetadata getMetadata() {
        return this.metadata;
    }

    public List<PlaylistItem> getPlaylistList() {
        return this.playlistList;
    }

    public List<TrackItem> getTrackList() {
        return this.trackList;
    }

    @Override // com.amazon.music.storeservice.model.MuseResponse
    public int hashCode() {
        return internalHashCodeCompute(Integer.valueOf(super.hashCode()), Integer.valueOf(classNameHashCode), getAlbumList(), getTrackList(), getPlaylistList(), getMetadata(), getArtistList());
    }

    public void setAlbumList(List<AlbumItem> list) {
        this.albumList = list;
    }

    public void setArtistList(List<ArtistItem> list) {
        this.artistList = list;
    }

    public void setMetadata(GeneralMetadata generalMetadata) {
        this.metadata = generalMetadata;
    }

    public void setPlaylistList(List<PlaylistItem> list) {
        this.playlistList = list;
    }

    public void setTrackList(List<TrackItem> list) {
        this.trackList = list;
    }
}
