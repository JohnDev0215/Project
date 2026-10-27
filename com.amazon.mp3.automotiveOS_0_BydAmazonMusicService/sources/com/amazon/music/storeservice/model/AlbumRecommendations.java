package com.amazon.music.storeservice.model;

import com.amazon.musicensembleservice.ContentInfo;
import java.util.Arrays;
import java.util.List;

/* JADX INFO: loaded from: classes4.dex */
public class AlbumRecommendations extends BaseRecommendations {
    private static final int classNameHashCode = internalHashCodeCompute("com.amazon.music.storeservice.model.AlbumRecommendations");
    private List<RecommendedAlbumItem> albums;
    private ContentInfo contentInfo;

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
        if (!(baseRecommendations instanceof AlbumRecommendations)) {
            return 1;
        }
        AlbumRecommendations albumRecommendations = (AlbumRecommendations) baseRecommendations;
        List<RecommendedAlbumItem> albums = getAlbums();
        List<RecommendedAlbumItem> albums2 = albumRecommendations.getAlbums();
        if (albums != albums2) {
            if (albums == null) {
                return -1;
            }
            if (albums2 == null) {
                return 1;
            }
            if (albums instanceof Comparable) {
                int iCompareTo = ((Comparable) albums).compareTo(albums2);
                if (iCompareTo != 0) {
                    return iCompareTo;
                }
            } else if (!albums.equals(albums2)) {
                int iHashCode = albums.hashCode();
                int iHashCode2 = albums2.hashCode();
                if (iHashCode < iHashCode2) {
                    return -1;
                }
                if (iHashCode > iHashCode2) {
                    return 1;
                }
            }
        }
        ContentInfo contentInfo = getContentInfo();
        ContentInfo contentInfo2 = albumRecommendations.getContentInfo();
        if (contentInfo != contentInfo2) {
            if (contentInfo == null) {
                return -1;
            }
            if (contentInfo2 == null) {
                return 1;
            }
            int iCompareTo2 = contentInfo.compareTo(contentInfo2);
            if (iCompareTo2 != 0) {
                return iCompareTo2;
            }
        }
        return super.compareTo(baseRecommendations);
    }

    @Override // com.amazon.music.storeservice.model.BaseRecommendations
    public boolean equals(Object obj) {
        if (!(obj instanceof AlbumRecommendations)) {
            return false;
        }
        AlbumRecommendations albumRecommendations = (AlbumRecommendations) obj;
        return super.equals(obj) && internalEqualityCheck(getAlbums(), albumRecommendations.getAlbums()) && internalEqualityCheck(getContentInfo(), albumRecommendations.getContentInfo());
    }

    public List<RecommendedAlbumItem> getAlbums() {
        return this.albums;
    }

    public ContentInfo getContentInfo() {
        return this.contentInfo;
    }

    @Override // com.amazon.music.storeservice.model.BaseRecommendations
    public int hashCode() {
        return internalHashCodeCompute(Integer.valueOf(super.hashCode()), Integer.valueOf(classNameHashCode), getAlbums(), getContentInfo());
    }

    public void setAlbums(List<RecommendedAlbumItem> list) {
        this.albums = list;
    }

    public void setContentInfo(ContentInfo contentInfo) {
        this.contentInfo = contentInfo;
    }
}
