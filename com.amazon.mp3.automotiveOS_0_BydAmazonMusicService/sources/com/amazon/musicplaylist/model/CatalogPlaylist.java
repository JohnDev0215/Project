package com.amazon.musicplaylist.model;

import java.util.Arrays;
import java.util.List;

/* JADX INFO: loaded from: classes5.dex */
public class CatalogPlaylist extends AbstractPlaylist {
    private static final int classNameHashCode = internalHashCodeCompute("com.amazon.musicplaylist.model.CatalogPlaylist");
    private CatalogPlaylistMetadata metadata;
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
        if (!(abstractPlaylist instanceof CatalogPlaylist)) {
            return 1;
        }
        CatalogPlaylist catalogPlaylist = (CatalogPlaylist) abstractPlaylist;
        List<CatalogPlaylistTrackEntry> tracks = getTracks();
        List<CatalogPlaylistTrackEntry> tracks2 = catalogPlaylist.getTracks();
        if (tracks != tracks2) {
            if (tracks == null) {
                return -1;
            }
            if (tracks2 == null) {
                return 1;
            }
            if (tracks instanceof Comparable) {
                int iCompareTo = ((Comparable) tracks).compareTo(tracks2);
                if (iCompareTo != 0) {
                    return iCompareTo;
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
        CatalogPlaylistMetadata metadata = getMetadata();
        CatalogPlaylistMetadata metadata2 = catalogPlaylist.getMetadata();
        if (metadata != metadata2) {
            if (metadata == null) {
                return -1;
            }
            if (metadata2 == null) {
                return 1;
            }
            int iCompareTo2 = metadata.compareTo(metadata2);
            if (iCompareTo2 != 0) {
                return iCompareTo2;
            }
        }
        return super.compareTo(abstractPlaylist);
    }

    @Override // com.amazon.musicplaylist.model.AbstractPlaylist
    public boolean equals(Object obj) {
        if (!(obj instanceof CatalogPlaylist)) {
            return false;
        }
        CatalogPlaylist catalogPlaylist = (CatalogPlaylist) obj;
        return super.equals(obj) && internalEqualityCheck(getTracks(), catalogPlaylist.getTracks()) && internalEqualityCheck(getMetadata(), catalogPlaylist.getMetadata());
    }

    public CatalogPlaylistMetadata getMetadata() {
        return this.metadata;
    }

    public List<CatalogPlaylistTrackEntry> getTracks() {
        return this.tracks;
    }

    @Override // com.amazon.musicplaylist.model.AbstractPlaylist
    public int hashCode() {
        return internalHashCodeCompute(Integer.valueOf(super.hashCode()), Integer.valueOf(classNameHashCode), getTracks(), getMetadata());
    }

    public void setMetadata(CatalogPlaylistMetadata catalogPlaylistMetadata) {
        this.metadata = catalogPlaylistMetadata;
    }

    public void setTracks(List<CatalogPlaylistTrackEntry> list) {
        this.tracks = list;
    }
}
