package com.amazon.musicensembleservice;

import com.amazon.music.storeservice.model.AlbumItem;
import com.amazon.music.storeservice.model.GeneralMetadata;
import com.amazon.music.storeservice.model.PlaylistItem;
import com.amazon.music.storeservice.model.TrackItem;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

/* JADX INFO: loaded from: classes2.dex */
public class GetTopMusicEntitiesResponse implements Comparable<GetTopMusicEntitiesResponse> {
    private static final int classNameHashCode = internalHashCodeCompute("com.amazon.musicensembleservice.GetTopMusicEntitiesResponse");
    private List<AlbumItem> albumList;
    private GeneralMetadata metadata;
    private Map<String, String> nextTokenMap;
    private List<PlaylistItem> playlistList;
    private GetTopMusicEntitiesTitles titles;
    private List<TrackItem> trackList;

    private static boolean internalEqualityCheck(Object obj, Object obj2) {
        return obj == obj2 || (obj != null && obj.equals(obj2));
    }

    private static int internalHashCodeCompute(Object... objArr) {
        return Arrays.hashCode(objArr);
    }

    @Override // java.lang.Comparable
    @Deprecated
    public int compareTo(@Deprecated GetTopMusicEntitiesResponse getTopMusicEntitiesResponse) {
        if (getTopMusicEntitiesResponse == null) {
            return -1;
        }
        if (getTopMusicEntitiesResponse == this) {
            return 0;
        }
        List<PlaylistItem> playlistList = getPlaylistList();
        List<PlaylistItem> playlistList2 = getTopMusicEntitiesResponse.getPlaylistList();
        if (playlistList != playlistList2) {
            if (playlistList == null) {
                return -1;
            }
            if (playlistList2 == null) {
                return 1;
            }
            if (playlistList instanceof Comparable) {
                int iCompareTo = ((Comparable) playlistList).compareTo(playlistList2);
                if (iCompareTo != 0) {
                    return iCompareTo;
                }
            } else if (!playlistList.equals(playlistList2)) {
                int iHashCode = playlistList.hashCode();
                int iHashCode2 = playlistList2.hashCode();
                if (iHashCode < iHashCode2) {
                    return -1;
                }
                if (iHashCode > iHashCode2) {
                    return 1;
                }
            }
        }
        GetTopMusicEntitiesTitles titles = getTitles();
        GetTopMusicEntitiesTitles titles2 = getTopMusicEntitiesResponse.getTitles();
        if (titles != titles2) {
            if (titles == null) {
                return -1;
            }
            if (titles2 == null) {
                return 1;
            }
            int iCompareTo2 = titles.compareTo(titles2);
            if (iCompareTo2 != 0) {
                return iCompareTo2;
            }
        }
        List<AlbumItem> albumList = getAlbumList();
        List<AlbumItem> albumList2 = getTopMusicEntitiesResponse.getAlbumList();
        if (albumList != albumList2) {
            if (albumList == null) {
                return -1;
            }
            if (albumList2 == null) {
                return 1;
            }
            if (albumList instanceof Comparable) {
                int iCompareTo3 = ((Comparable) albumList).compareTo(albumList2);
                if (iCompareTo3 != 0) {
                    return iCompareTo3;
                }
            } else if (!albumList.equals(albumList2)) {
                int iHashCode3 = albumList.hashCode();
                int iHashCode4 = albumList2.hashCode();
                if (iHashCode3 < iHashCode4) {
                    return -1;
                }
                if (iHashCode3 > iHashCode4) {
                    return 1;
                }
            }
        }
        GeneralMetadata metadata = getMetadata();
        GeneralMetadata metadata2 = getTopMusicEntitiesResponse.getMetadata();
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
        List<TrackItem> trackList = getTrackList();
        List<TrackItem> trackList2 = getTopMusicEntitiesResponse.getTrackList();
        if (trackList != trackList2) {
            if (trackList == null) {
                return -1;
            }
            if (trackList2 == null) {
                return 1;
            }
            if (trackList instanceof Comparable) {
                int iCompareTo5 = ((Comparable) trackList).compareTo(trackList2);
                if (iCompareTo5 != 0) {
                    return iCompareTo5;
                }
            } else if (!trackList.equals(trackList2)) {
                int iHashCode5 = trackList.hashCode();
                int iHashCode6 = trackList2.hashCode();
                if (iHashCode5 < iHashCode6) {
                    return -1;
                }
                if (iHashCode5 > iHashCode6) {
                    return 1;
                }
            }
        }
        Map<String, String> nextTokenMap = getNextTokenMap();
        Map<String, String> nextTokenMap2 = getTopMusicEntitiesResponse.getNextTokenMap();
        if (nextTokenMap != nextTokenMap2) {
            if (nextTokenMap == null) {
                return -1;
            }
            if (nextTokenMap2 == null) {
                return 1;
            }
            if (nextTokenMap instanceof Comparable) {
                int iCompareTo6 = ((Comparable) nextTokenMap).compareTo(nextTokenMap2);
                if (iCompareTo6 != 0) {
                    return iCompareTo6;
                }
            } else if (!nextTokenMap.equals(nextTokenMap2)) {
                int iHashCode7 = nextTokenMap.hashCode();
                int iHashCode8 = nextTokenMap2.hashCode();
                if (iHashCode7 < iHashCode8) {
                    return -1;
                }
                if (iHashCode7 > iHashCode8) {
                    return 1;
                }
            }
        }
        return 0;
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof GetTopMusicEntitiesResponse)) {
            return false;
        }
        GetTopMusicEntitiesResponse getTopMusicEntitiesResponse = (GetTopMusicEntitiesResponse) obj;
        return internalEqualityCheck(getPlaylistList(), getTopMusicEntitiesResponse.getPlaylistList()) && internalEqualityCheck(getTitles(), getTopMusicEntitiesResponse.getTitles()) && internalEqualityCheck(getAlbumList(), getTopMusicEntitiesResponse.getAlbumList()) && internalEqualityCheck(getMetadata(), getTopMusicEntitiesResponse.getMetadata()) && internalEqualityCheck(getTrackList(), getTopMusicEntitiesResponse.getTrackList()) && internalEqualityCheck(getNextTokenMap(), getTopMusicEntitiesResponse.getNextTokenMap());
    }

    public List<AlbumItem> getAlbumList() {
        return this.albumList;
    }

    public GeneralMetadata getMetadata() {
        return this.metadata;
    }

    public Map<String, String> getNextTokenMap() {
        return this.nextTokenMap;
    }

    public List<PlaylistItem> getPlaylistList() {
        return this.playlistList;
    }

    public GetTopMusicEntitiesTitles getTitles() {
        return this.titles;
    }

    public List<TrackItem> getTrackList() {
        return this.trackList;
    }

    public int hashCode() {
        return internalHashCodeCompute(Integer.valueOf(classNameHashCode), getPlaylistList(), getTitles(), getAlbumList(), getMetadata(), getTrackList(), getNextTokenMap());
    }

    public void setAlbumList(List<AlbumItem> list) {
        this.albumList = list;
    }

    public void setMetadata(GeneralMetadata generalMetadata) {
        this.metadata = generalMetadata;
    }

    public void setNextTokenMap(Map<String, String> map) {
        this.nextTokenMap = map;
    }

    public void setPlaylistList(List<PlaylistItem> list) {
        this.playlistList = list;
    }

    public void setTitles(GetTopMusicEntitiesTitles getTopMusicEntitiesTitles) {
        this.titles = getTopMusicEntitiesTitles;
    }

    public void setTrackList(List<TrackItem> list) {
        this.trackList = list;
    }
}
