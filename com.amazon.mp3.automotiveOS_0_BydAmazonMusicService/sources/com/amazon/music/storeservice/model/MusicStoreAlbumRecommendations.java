package com.amazon.music.storeservice.model;

import java.util.Arrays;
import java.util.List;

/* JADX INFO: loaded from: classes4.dex */
public class MusicStoreAlbumRecommendations extends BaseRecommendations {
    private static final int classNameHashCode = internalHashCodeCompute("com.amazon.music.storeservice.model.MusicStoreAlbumRecommendations");
    private List<MusicStoreRecommendedAlbumItem> albums;

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
        if (!(baseRecommendations instanceof MusicStoreAlbumRecommendations)) {
            return 1;
        }
        List<MusicStoreRecommendedAlbumItem> albums = getAlbums();
        List<MusicStoreRecommendedAlbumItem> albums2 = ((MusicStoreAlbumRecommendations) baseRecommendations).getAlbums();
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
        return super.compareTo(baseRecommendations);
    }

    @Override // com.amazon.music.storeservice.model.BaseRecommendations
    public boolean equals(Object obj) {
        if (obj instanceof MusicStoreAlbumRecommendations) {
            return super.equals(obj) && internalEqualityCheck(getAlbums(), ((MusicStoreAlbumRecommendations) obj).getAlbums());
        }
        return false;
    }

    public List<MusicStoreRecommendedAlbumItem> getAlbums() {
        return this.albums;
    }

    @Override // com.amazon.music.storeservice.model.BaseRecommendations
    public int hashCode() {
        return internalHashCodeCompute(Integer.valueOf(super.hashCode()), Integer.valueOf(classNameHashCode), getAlbums());
    }

    public void setAlbums(List<MusicStoreRecommendedAlbumItem> list) {
        this.albums = list;
    }
}
