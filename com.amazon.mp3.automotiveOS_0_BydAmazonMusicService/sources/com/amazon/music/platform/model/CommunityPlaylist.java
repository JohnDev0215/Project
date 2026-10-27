package com.amazon.music.platform.model;

import java.util.Arrays;

/* JADX INFO: loaded from: classes4.dex */
public class CommunityPlaylist extends Playlist {
    private static final int classNameHashCode = internalHashCodeCompute("com.amazon.music.platform.model.CommunityPlaylist");
    private String curator;
    private String playlistId;
    private PlaymodeEligibility playmodeEligibility;

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
        if (!(document instanceof CommunityPlaylist)) {
            return 1;
        }
        CommunityPlaylist communityPlaylist = (CommunityPlaylist) document;
        PlaymodeEligibility playmodeEligibility = getPlaymodeEligibility();
        PlaymodeEligibility playmodeEligibility2 = communityPlaylist.getPlaymodeEligibility();
        if (playmodeEligibility != playmodeEligibility2) {
            if (playmodeEligibility == null) {
                return -1;
            }
            if (playmodeEligibility2 == null) {
                return 1;
            }
            int iCompareTo = playmodeEligibility.compareTo(playmodeEligibility2);
            if (iCompareTo != 0) {
                return iCompareTo;
            }
        }
        String curator = getCurator();
        String curator2 = communityPlaylist.getCurator();
        if (curator != curator2) {
            if (curator == null) {
                return -1;
            }
            if (curator2 == null) {
                return 1;
            }
            int iCompareTo2 = curator.compareTo(curator2);
            if (iCompareTo2 != 0) {
                return iCompareTo2;
            }
        }
        String playlistId = getPlaylistId();
        String playlistId2 = communityPlaylist.getPlaylistId();
        if (playlistId != playlistId2) {
            if (playlistId == null) {
                return -1;
            }
            if (playlistId2 == null) {
                return 1;
            }
            int iCompareTo3 = playlistId.compareTo(playlistId2);
            if (iCompareTo3 != 0) {
                return iCompareTo3;
            }
        }
        return super.compareTo(document);
    }

    @Override // com.amazon.music.platform.model.Playlist, com.amazon.music.platform.model.Document
    public boolean equals(Object obj) {
        if (!(obj instanceof CommunityPlaylist)) {
            return false;
        }
        CommunityPlaylist communityPlaylist = (CommunityPlaylist) obj;
        return super.equals(obj) && internalEqualityCheck(getPlaymodeEligibility(), communityPlaylist.getPlaymodeEligibility()) && internalEqualityCheck(getCurator(), communityPlaylist.getCurator()) && internalEqualityCheck(getPlaylistId(), communityPlaylist.getPlaylistId());
    }

    public String getCurator() {
        return this.curator;
    }

    public String getPlaylistId() {
        return this.playlistId;
    }

    public PlaymodeEligibility getPlaymodeEligibility() {
        return this.playmodeEligibility;
    }

    @Override // com.amazon.music.platform.model.Playlist, com.amazon.music.platform.model.Document
    public int hashCode() {
        return internalHashCodeCompute(Integer.valueOf(super.hashCode()), Integer.valueOf(classNameHashCode), getPlaymodeEligibility(), getCurator(), getPlaylistId());
    }

    public void setCurator(String str) {
        this.curator = str;
    }

    public void setPlaylistId(String str) {
        this.playlistId = str;
    }

    public void setPlaymodeEligibility(PlaymodeEligibility playmodeEligibility) {
        this.playmodeEligibility = playmodeEligibility;
    }
}
