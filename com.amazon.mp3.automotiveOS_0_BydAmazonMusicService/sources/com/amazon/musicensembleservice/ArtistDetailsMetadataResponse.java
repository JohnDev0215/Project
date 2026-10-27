package com.amazon.musicensembleservice;

import com.amazon.music.storeservice.model.AlbumItem;
import com.amazon.music.storeservice.model.ArtistItem;
import com.amazon.music.storeservice.model.GeneralMetadata;
import com.amazon.music.storeservice.model.TrackItem;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

/* JADX INFO: loaded from: classes2.dex */
public class ArtistDetailsMetadataResponse implements Comparable<ArtistDetailsMetadataResponse> {
    private static final int classNameHashCode = internalHashCodeCompute("com.amazon.musicensembleservice.ArtistDetailsMetadataResponse");
    private List<AlbumItem> albumList;
    private String artistAsin;
    private String artistName;
    private String globalArtistAsin;
    private GeneralMetadata metadata;
    private Map<String, String> nextTokenMap;
    private String requestedArtistAsin;
    private List<ArtistItem> similarArtistList;
    private List<TrackItem> trackList;

    private static boolean internalEqualityCheck(Object obj, Object obj2) {
        return obj == obj2 || (obj != null && obj.equals(obj2));
    }

    private static int internalHashCodeCompute(Object... objArr) {
        return Arrays.hashCode(objArr);
    }

    @Override // java.lang.Comparable
    @Deprecated
    public int compareTo(@Deprecated ArtistDetailsMetadataResponse artistDetailsMetadataResponse) {
        if (artistDetailsMetadataResponse == null) {
            return -1;
        }
        if (artistDetailsMetadataResponse == this) {
            return 0;
        }
        GeneralMetadata metadata = getMetadata();
        GeneralMetadata metadata2 = artistDetailsMetadataResponse.getMetadata();
        if (metadata != metadata2) {
            if (metadata == null) {
                return -1;
            }
            if (metadata2 == null) {
                return 1;
            }
            int iCompareTo = metadata.compareTo(metadata2);
            if (iCompareTo != 0) {
                return iCompareTo;
            }
        }
        String artistAsin = getArtistAsin();
        String artistAsin2 = artistDetailsMetadataResponse.getArtistAsin();
        if (artistAsin != artistAsin2) {
            if (artistAsin == null) {
                return -1;
            }
            if (artistAsin2 == null) {
                return 1;
            }
            int iCompareTo2 = artistAsin.compareTo(artistAsin2);
            if (iCompareTo2 != 0) {
                return iCompareTo2;
            }
        }
        String requestedArtistAsin = getRequestedArtistAsin();
        String requestedArtistAsin2 = artistDetailsMetadataResponse.getRequestedArtistAsin();
        if (requestedArtistAsin != requestedArtistAsin2) {
            if (requestedArtistAsin == null) {
                return -1;
            }
            if (requestedArtistAsin2 == null) {
                return 1;
            }
            int iCompareTo3 = requestedArtistAsin.compareTo(requestedArtistAsin2);
            if (iCompareTo3 != 0) {
                return iCompareTo3;
            }
        }
        List<AlbumItem> albumList = getAlbumList();
        List<AlbumItem> albumList2 = artistDetailsMetadataResponse.getAlbumList();
        if (albumList != albumList2) {
            if (albumList == null) {
                return -1;
            }
            if (albumList2 == null) {
                return 1;
            }
            if (albumList instanceof Comparable) {
                int iCompareTo4 = ((Comparable) albumList).compareTo(albumList2);
                if (iCompareTo4 != 0) {
                    return iCompareTo4;
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
        List<ArtistItem> similarArtistList = getSimilarArtistList();
        List<ArtistItem> similarArtistList2 = artistDetailsMetadataResponse.getSimilarArtistList();
        if (similarArtistList != similarArtistList2) {
            if (similarArtistList == null) {
                return -1;
            }
            if (similarArtistList2 == null) {
                return 1;
            }
            if (similarArtistList instanceof Comparable) {
                int iCompareTo5 = ((Comparable) similarArtistList).compareTo(similarArtistList2);
                if (iCompareTo5 != 0) {
                    return iCompareTo5;
                }
            } else if (!similarArtistList.equals(similarArtistList2)) {
                int iHashCode3 = similarArtistList.hashCode();
                int iHashCode4 = similarArtistList2.hashCode();
                if (iHashCode3 < iHashCode4) {
                    return -1;
                }
                if (iHashCode3 > iHashCode4) {
                    return 1;
                }
            }
        }
        String artistName = getArtistName();
        String artistName2 = artistDetailsMetadataResponse.getArtistName();
        if (artistName != artistName2) {
            if (artistName == null) {
                return -1;
            }
            if (artistName2 == null) {
                return 1;
            }
            int iCompareTo6 = artistName.compareTo(artistName2);
            if (iCompareTo6 != 0) {
                return iCompareTo6;
            }
        }
        String globalArtistAsin = getGlobalArtistAsin();
        String globalArtistAsin2 = artistDetailsMetadataResponse.getGlobalArtistAsin();
        if (globalArtistAsin != globalArtistAsin2) {
            if (globalArtistAsin == null) {
                return -1;
            }
            if (globalArtistAsin2 == null) {
                return 1;
            }
            int iCompareTo7 = globalArtistAsin.compareTo(globalArtistAsin2);
            if (iCompareTo7 != 0) {
                return iCompareTo7;
            }
        }
        Map<String, String> nextTokenMap = getNextTokenMap();
        Map<String, String> nextTokenMap2 = artistDetailsMetadataResponse.getNextTokenMap();
        if (nextTokenMap != nextTokenMap2) {
            if (nextTokenMap == null) {
                return -1;
            }
            if (nextTokenMap2 == null) {
                return 1;
            }
            if (nextTokenMap instanceof Comparable) {
                int iCompareTo8 = ((Comparable) nextTokenMap).compareTo(nextTokenMap2);
                if (iCompareTo8 != 0) {
                    return iCompareTo8;
                }
            } else if (!nextTokenMap.equals(nextTokenMap2)) {
                int iHashCode5 = nextTokenMap.hashCode();
                int iHashCode6 = nextTokenMap2.hashCode();
                if (iHashCode5 < iHashCode6) {
                    return -1;
                }
                if (iHashCode5 > iHashCode6) {
                    return 1;
                }
            }
        }
        List<TrackItem> trackList = getTrackList();
        List<TrackItem> trackList2 = artistDetailsMetadataResponse.getTrackList();
        if (trackList != trackList2) {
            if (trackList == null) {
                return -1;
            }
            if (trackList2 == null) {
                return 1;
            }
            if (trackList instanceof Comparable) {
                int iCompareTo9 = ((Comparable) trackList).compareTo(trackList2);
                if (iCompareTo9 != 0) {
                    return iCompareTo9;
                }
            } else if (!trackList.equals(trackList2)) {
                int iHashCode7 = trackList.hashCode();
                int iHashCode8 = trackList2.hashCode();
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
        if (!(obj instanceof ArtistDetailsMetadataResponse)) {
            return false;
        }
        ArtistDetailsMetadataResponse artistDetailsMetadataResponse = (ArtistDetailsMetadataResponse) obj;
        return internalEqualityCheck(getMetadata(), artistDetailsMetadataResponse.getMetadata()) && internalEqualityCheck(getArtistAsin(), artistDetailsMetadataResponse.getArtistAsin()) && internalEqualityCheck(getRequestedArtistAsin(), artistDetailsMetadataResponse.getRequestedArtistAsin()) && internalEqualityCheck(getAlbumList(), artistDetailsMetadataResponse.getAlbumList()) && internalEqualityCheck(getSimilarArtistList(), artistDetailsMetadataResponse.getSimilarArtistList()) && internalEqualityCheck(getArtistName(), artistDetailsMetadataResponse.getArtistName()) && internalEqualityCheck(getGlobalArtistAsin(), artistDetailsMetadataResponse.getGlobalArtistAsin()) && internalEqualityCheck(getNextTokenMap(), artistDetailsMetadataResponse.getNextTokenMap()) && internalEqualityCheck(getTrackList(), artistDetailsMetadataResponse.getTrackList());
    }

    public List<AlbumItem> getAlbumList() {
        return this.albumList;
    }

    public String getArtistAsin() {
        return this.artistAsin;
    }

    public String getArtistName() {
        return this.artistName;
    }

    public String getGlobalArtistAsin() {
        return this.globalArtistAsin;
    }

    public GeneralMetadata getMetadata() {
        return this.metadata;
    }

    public Map<String, String> getNextTokenMap() {
        return this.nextTokenMap;
    }

    public String getRequestedArtistAsin() {
        return this.requestedArtistAsin;
    }

    public List<ArtistItem> getSimilarArtistList() {
        return this.similarArtistList;
    }

    public List<TrackItem> getTrackList() {
        return this.trackList;
    }

    public int hashCode() {
        return internalHashCodeCompute(Integer.valueOf(classNameHashCode), getMetadata(), getArtistAsin(), getRequestedArtistAsin(), getAlbumList(), getSimilarArtistList(), getArtistName(), getGlobalArtistAsin(), getNextTokenMap(), getTrackList());
    }

    public void setAlbumList(List<AlbumItem> list) {
        this.albumList = list;
    }

    public void setArtistAsin(String str) {
        this.artistAsin = str;
    }

    public void setArtistName(String str) {
        this.artistName = str;
    }

    public void setGlobalArtistAsin(String str) {
        this.globalArtistAsin = str;
    }

    public void setMetadata(GeneralMetadata generalMetadata) {
        this.metadata = generalMetadata;
    }

    public void setNextTokenMap(Map<String, String> map) {
        this.nextTokenMap = map;
    }

    public void setRequestedArtistAsin(String str) {
        this.requestedArtistAsin = str;
    }

    public void setSimilarArtistList(List<ArtistItem> list) {
        this.similarArtistList = list;
    }

    public void setTrackList(List<TrackItem> list) {
        this.trackList = list;
    }
}
