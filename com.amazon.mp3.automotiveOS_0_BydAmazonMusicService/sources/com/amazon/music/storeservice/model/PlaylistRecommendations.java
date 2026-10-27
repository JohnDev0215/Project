package com.amazon.music.storeservice.model;

import com.amazon.musicensembleservice.ContentInfo;
import java.util.Arrays;
import java.util.List;

/* JADX INFO: loaded from: classes4.dex */
public class PlaylistRecommendations extends BaseRecommendations {
    private static final int classNameHashCode = internalHashCodeCompute("com.amazon.music.storeservice.model.PlaylistRecommendations");
    private ContentInfo contentInfo;
    private List<RecommendedPlaylistItem> playlists;

    private static boolean internalEqualityCheck(Object obj, Object obj2) {
        return obj == obj2 || (obj != null && obj.equals(obj2));
    }

    private static int internalHashCodeCompute(Object... objArr) {
        return Arrays.hashCode(objArr);
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.amazon.music.storeservice.model.BaseRecommendations, java.lang.Comparable
    @Deprecated
    public int compareTo(@Deprecated BaseRecommendations baseRecommendations) {
        if (baseRecommendations == null) {
            return -1;
        }
        if (baseRecommendations == this) {
            return 0;
        }
        if (!(baseRecommendations instanceof PlaylistRecommendations)) {
            return 1;
        }
        PlaylistRecommendations playlistRecommendations = (PlaylistRecommendations) baseRecommendations;
        ContentInfo contentInfo = getContentInfo();
        ContentInfo contentInfo2 = playlistRecommendations.getContentInfo();
        if (contentInfo != contentInfo2) {
            if (contentInfo == null) {
                return -1;
            }
            if (contentInfo2 == null) {
                return 1;
            }
            int iCompareTo = contentInfo.compareTo(contentInfo2);
            if (iCompareTo != 0) {
                return iCompareTo;
            }
        }
        List<RecommendedPlaylistItem> playlists = getPlaylists();
        List<RecommendedPlaylistItem> playlists2 = playlistRecommendations.getPlaylists();
        if (playlists != playlists2) {
            if (playlists == null) {
                return -1;
            }
            if (playlists2 == null) {
                return 1;
            }
            if (playlists instanceof Comparable) {
                int iCompareTo2 = ((Comparable) playlists).compareTo(playlists2);
                if (iCompareTo2 != 0) {
                    return iCompareTo2;
                }
            } else if (!playlists.equals(playlists2)) {
                int iHashCode = playlists.hashCode();
                int iHashCode2 = playlists2.hashCode();
                if (iHashCode < iHashCode2) {
                    return -1;
                }
                if (iHashCode > iHashCode2) {
                    return 1;
                }
            }
        }
        return super.compareTo(baseRecommendations);
    }

    @Override // com.amazon.music.storeservice.model.BaseRecommendations
    public boolean equals(Object obj) {
        if (!(obj instanceof PlaylistRecommendations)) {
            return false;
        }
        PlaylistRecommendations playlistRecommendations = (PlaylistRecommendations) obj;
        return super.equals(obj) && internalEqualityCheck(getContentInfo(), playlistRecommendations.getContentInfo()) && internalEqualityCheck(getPlaylists(), playlistRecommendations.getPlaylists());
    }

    public ContentInfo getContentInfo() {
        return this.contentInfo;
    }

    public List<RecommendedPlaylistItem> getPlaylists() {
        return this.playlists;
    }

    @Override // com.amazon.music.storeservice.model.BaseRecommendations
    public int hashCode() {
        return internalHashCodeCompute(Integer.valueOf(super.hashCode()), Integer.valueOf(classNameHashCode), getContentInfo(), getPlaylists());
    }

    public void setContentInfo(ContentInfo contentInfo) {
        this.contentInfo = contentInfo;
    }

    public void setPlaylists(List<RecommendedPlaylistItem> list) {
        this.playlists = list;
    }
}
