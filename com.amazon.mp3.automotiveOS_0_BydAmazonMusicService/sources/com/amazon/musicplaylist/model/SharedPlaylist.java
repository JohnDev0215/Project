package com.amazon.musicplaylist.model;

import java.util.Arrays;
import java.util.List;

/* JADX INFO: loaded from: classes5.dex */
public class SharedPlaylist extends AbstractPlaylist {
    private static final int classNameHashCode = internalHashCodeCompute("com.amazon.musicplaylist.model.SharedPlaylist");
    private SharedPlaylistMetadata metadata;
    private List<CatalogPlaylistTrackEntry> tracks;

    private static boolean internalEqualityCheck(Object obj, Object obj2) {
        return obj == obj2 || (obj != null && obj.equals(obj2));
    }

    private static int internalHashCodeCompute(Object... objArr) {
        return Arrays.hashCode(objArr);
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.amazon.musicplaylist.model.AbstractPlaylist, java.lang.Comparable
    @Deprecated
    public int compareTo(@Deprecated AbstractPlaylist abstractPlaylist) {
        if (abstractPlaylist == null) {
            return -1;
        }
        if (abstractPlaylist == this) {
            return 0;
        }
        if (!(abstractPlaylist instanceof SharedPlaylist)) {
            return 1;
        }
        SharedPlaylist sharedPlaylist = (SharedPlaylist) abstractPlaylist;
        SharedPlaylistMetadata metadata = getMetadata();
        SharedPlaylistMetadata metadata2 = sharedPlaylist.getMetadata();
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
        List<CatalogPlaylistTrackEntry> tracks = getTracks();
        List<CatalogPlaylistTrackEntry> tracks2 = sharedPlaylist.getTracks();
        if (tracks != tracks2) {
            if (tracks == null) {
                return -1;
            }
            if (tracks2 == null) {
                return 1;
            }
            if (tracks instanceof Comparable) {
                int iCompareTo2 = ((Comparable) tracks).compareTo(tracks2);
                if (iCompareTo2 != 0) {
                    return iCompareTo2;
                }
            } else if (!tracks.equals(tracks2)) {
                int iHashCode = tracks.hashCode();
                int iHashCode2 = tracks2.hashCode();
                if (iHashCode < iHashCode2) {
                    return -1;
                }
                if (iHashCode > iHashCode2) {
                    return 1;
                }
            }
        }
        return super.compareTo(abstractPlaylist);
    }

    @Override // com.amazon.musicplaylist.model.AbstractPlaylist
    public boolean equals(Object obj) {
        if (!(obj instanceof SharedPlaylist)) {
            return false;
        }
        SharedPlaylist sharedPlaylist = (SharedPlaylist) obj;
        return super.equals(obj) && internalEqualityCheck(getMetadata(), sharedPlaylist.getMetadata()) && internalEqualityCheck(getTracks(), sharedPlaylist.getTracks());
    }

    public SharedPlaylistMetadata getMetadata() {
        return this.metadata;
    }

    public List<CatalogPlaylistTrackEntry> getTracks() {
        return this.tracks;
    }

    @Override // com.amazon.musicplaylist.model.AbstractPlaylist
    public int hashCode() {
        return internalHashCodeCompute(Integer.valueOf(super.hashCode()), Integer.valueOf(classNameHashCode), getMetadata(), getTracks());
    }

    public void setMetadata(SharedPlaylistMetadata sharedPlaylistMetadata) {
        this.metadata = sharedPlaylistMetadata;
    }

    public void setTracks(List<CatalogPlaylistTrackEntry> list) {
        this.tracks = list;
    }
}
