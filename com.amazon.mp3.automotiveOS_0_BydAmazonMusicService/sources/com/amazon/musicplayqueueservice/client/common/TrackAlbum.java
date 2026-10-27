package com.amazon.musicplayqueueservice.client.common;

import java.util.Arrays;
import java.util.List;

/* JADX INFO: loaded from: classes5.dex */
public class TrackAlbum implements Comparable<TrackAlbum> {
    private static final int classNameHashCode = internalHashCodeCompute("com.amazon.musicplayqueueservice.client.common.TrackAlbum");
    private String artistName;
    private List<TrackArtist> artists;
    private String asin;
    private String name;

    private static boolean internalEqualityCheck(Object obj, Object obj2) {
        return obj == obj2 || (obj != null && obj.equals(obj2));
    }

    private static int internalHashCodeCompute(Object... objArr) {
        return Arrays.hashCode(objArr);
    }

    @Override // java.lang.Comparable
    @Deprecated
    public int compareTo(@Deprecated TrackAlbum trackAlbum) {
        if (trackAlbum == null) {
            return -1;
        }
        if (trackAlbum == this) {
            return 0;
        }
        String asin = getAsin();
        String asin2 = trackAlbum.getAsin();
        if (asin != asin2) {
            if (asin == null) {
                return -1;
            }
            if (asin2 == null) {
                return 1;
            }
            int iCompareTo = asin.compareTo(asin2);
            if (iCompareTo != 0) {
                return iCompareTo;
            }
        }
        String artistName = getArtistName();
        String artistName2 = trackAlbum.getArtistName();
        if (artistName != artistName2) {
            if (artistName == null) {
                return -1;
            }
            if (artistName2 == null) {
                return 1;
            }
            int iCompareTo2 = artistName.compareTo(artistName2);
            if (iCompareTo2 != 0) {
                return iCompareTo2;
            }
        }
        List<TrackArtist> artists = getArtists();
        List<TrackArtist> artists2 = trackAlbum.getArtists();
        if (artists != artists2) {
            if (artists == null) {
                return -1;
            }
            if (artists2 == null) {
                return 1;
            }
            if (artists instanceof Comparable) {
                int iCompareTo3 = ((Comparable) artists).compareTo(artists2);
                if (iCompareTo3 != 0) {
                    return iCompareTo3;
                }
            } else if (!artists.equals(artists2)) {
                int iHashCode = artists.hashCode();
                int iHashCode2 = artists2.hashCode();
                if (iHashCode < iHashCode2) {
                    return -1;
                }
                if (iHashCode > iHashCode2) {
                    return 1;
                }
            }
        }
        String name = getName();
        String name2 = trackAlbum.getName();
        if (name != name2) {
            if (name == null) {
                return -1;
            }
            if (name2 == null) {
                return 1;
            }
            int iCompareTo4 = name.compareTo(name2);
            if (iCompareTo4 != 0) {
                return iCompareTo4;
            }
        }
        return 0;
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof TrackAlbum)) {
            return false;
        }
        TrackAlbum trackAlbum = (TrackAlbum) obj;
        return internalEqualityCheck(getAsin(), trackAlbum.getAsin()) && internalEqualityCheck(getArtistName(), trackAlbum.getArtistName()) && internalEqualityCheck(getArtists(), trackAlbum.getArtists()) && internalEqualityCheck(getName(), trackAlbum.getName());
    }

    public String getArtistName() {
        return this.artistName;
    }

    public List<TrackArtist> getArtists() {
        return this.artists;
    }

    public String getAsin() {
        return this.asin;
    }

    public String getName() {
        return this.name;
    }

    public int hashCode() {
        return internalHashCodeCompute(Integer.valueOf(classNameHashCode), getAsin(), getArtistName(), getArtists(), getName());
    }

    public void setArtistName(String str) {
        this.artistName = str;
    }

    public void setArtists(List<TrackArtist> list) {
        this.artists = list;
    }

    public void setAsin(String str) {
        this.asin = str;
    }

    public void setName(String str) {
        this.name = str;
    }
}
