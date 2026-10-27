package com.amazon.music.platform.model;

import java.util.Arrays;
import java.util.List;

/* JADX INFO: loaded from: classes4.dex */
public class RelatedTrack implements Comparable<RelatedTrack> {
    private static final int classNameHashCode = internalHashCodeCompute("com.amazon.music.platform.model.RelatedTrack");
    private List<TrackArtist> artist;
    private String asin;

    private static boolean internalEqualityCheck(Object obj, Object obj2) {
        return obj == obj2 || (obj != null && obj.equals(obj2));
    }

    private static int internalHashCodeCompute(Object... objArr) {
        return Arrays.hashCode(objArr);
    }

    @Override // java.lang.Comparable
    @Deprecated
    public int compareTo(@Deprecated RelatedTrack relatedTrack) {
        if (relatedTrack == null) {
            return -1;
        }
        if (relatedTrack == this) {
            return 0;
        }
        List<TrackArtist> artist = getArtist();
        List<TrackArtist> artist2 = relatedTrack.getArtist();
        if (artist != artist2) {
            if (artist == null) {
                return -1;
            }
            if (artist2 == null) {
                return 1;
            }
            if (artist instanceof Comparable) {
                int iCompareTo = ((Comparable) artist).compareTo(artist2);
                if (iCompareTo != 0) {
                    return iCompareTo;
                }
            } else if (!artist.equals(artist2)) {
                int iHashCode = artist.hashCode();
                int iHashCode2 = artist2.hashCode();
                if (iHashCode < iHashCode2) {
                    return -1;
                }
                if (iHashCode > iHashCode2) {
                    return 1;
                }
            }
        }
        String asin = getAsin();
        String asin2 = relatedTrack.getAsin();
        if (asin != asin2) {
            if (asin == null) {
                return -1;
            }
            if (asin2 == null) {
                return 1;
            }
            int iCompareTo2 = asin.compareTo(asin2);
            if (iCompareTo2 != 0) {
                return iCompareTo2;
            }
        }
        return 0;
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof RelatedTrack)) {
            return false;
        }
        RelatedTrack relatedTrack = (RelatedTrack) obj;
        return internalEqualityCheck(getArtist(), relatedTrack.getArtist()) && internalEqualityCheck(getAsin(), relatedTrack.getAsin());
    }

    public List<TrackArtist> getArtist() {
        return this.artist;
    }

    public String getAsin() {
        return this.asin;
    }

    public int hashCode() {
        return internalHashCodeCompute(Integer.valueOf(classNameHashCode), getArtist(), getAsin());
    }

    public void setArtist(List<TrackArtist> list) {
        this.artist = list;
    }

    public void setAsin(String str) {
        this.asin = str;
    }
}
