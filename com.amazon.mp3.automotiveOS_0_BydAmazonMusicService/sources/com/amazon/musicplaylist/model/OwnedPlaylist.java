package com.amazon.musicplaylist.model;

import java.util.Arrays;
import java.util.List;

/* JADX INFO: loaded from: classes5.dex */
public class OwnedPlaylist extends AbstractPlaylist {
    private static final int classNameHashCode = internalHashCodeCompute("com.amazon.musicplaylist.model.OwnedPlaylist");
    private OwnedPlaylistMetadata metadata;
    private List<AbstractPlaylistTrackEntry> tracks;

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
        if (!(abstractPlaylist instanceof OwnedPlaylist)) {
            return 1;
        }
        OwnedPlaylist ownedPlaylist = (OwnedPlaylist) abstractPlaylist;
        List<AbstractPlaylistTrackEntry> tracks = getTracks();
        List<AbstractPlaylistTrackEntry> tracks2 = ownedPlaylist.getTracks();
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
        OwnedPlaylistMetadata metadata = getMetadata();
        OwnedPlaylistMetadata metadata2 = ownedPlaylist.getMetadata();
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
        if (!(obj instanceof OwnedPlaylist)) {
            return false;
        }
        OwnedPlaylist ownedPlaylist = (OwnedPlaylist) obj;
        return super.equals(obj) && internalEqualityCheck(getTracks(), ownedPlaylist.getTracks()) && internalEqualityCheck(getMetadata(), ownedPlaylist.getMetadata());
    }

    public OwnedPlaylistMetadata getMetadata() {
        return this.metadata;
    }

    public List<AbstractPlaylistTrackEntry> getTracks() {
        return this.tracks;
    }

    @Override // com.amazon.musicplaylist.model.AbstractPlaylist
    public int hashCode() {
        return internalHashCodeCompute(Integer.valueOf(super.hashCode()), Integer.valueOf(classNameHashCode), getTracks(), getMetadata());
    }

    public void setMetadata(OwnedPlaylistMetadata ownedPlaylistMetadata) {
        this.metadata = ownedPlaylistMetadata;
    }

    public void setTracks(List<AbstractPlaylistTrackEntry> list) {
        this.tracks = list;
    }
}
