package com.amazon.musicplayqueueservice.client.v2.track;

import com.amazon.musicplayqueueservice.client.common.ParentalControls;
import com.amazon.musicplayqueueservice.client.common.TrackAlbum;
import com.amazon.musicplayqueueservice.client.common.TrackArtist;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

/* JADX INFO: loaded from: classes5.dex */
public class SongMetadata extends QueuedEntityMetadataBase {
    private static final int classNameHashCode = internalHashCodeCompute("com.amazon.musicplayqueueservice.client.v2.track.SongMetadata");
    private TrackAlbum album;
    private Map<String, String> albumArt;
    private String artistName;
    private List<TrackArtist> artists;
    private boolean hasLyrics;
    private String identifier;
    private String identifierType;
    private boolean isSample;
    private String licenseKeyToken;
    private String name;
    private ParentalControls parentalControls;
    private String rating;
    private String serviceTier;

    private static boolean internalEqualityCheck(Object obj, Object obj2) {
        return obj == obj2 || (obj != null && obj.equals(obj2));
    }

    private static int internalHashCodeCompute(Object... objArr) {
        return Arrays.hashCode(objArr);
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.amazon.musicplayqueueservice.client.v2.track.QueuedEntityMetadataBase, java.lang.Comparable
    @Deprecated
    public int compareTo(@Deprecated QueuedEntityMetadataBase queuedEntityMetadataBase) {
        if (queuedEntityMetadataBase == null) {
            return -1;
        }
        if (queuedEntityMetadataBase == this) {
            return 0;
        }
        if (!(queuedEntityMetadataBase instanceof SongMetadata)) {
            return 1;
        }
        SongMetadata songMetadata = (SongMetadata) queuedEntityMetadataBase;
        String identifierType = getIdentifierType();
        String identifierType2 = songMetadata.getIdentifierType();
        if (identifierType != identifierType2) {
            if (identifierType == null) {
                return -1;
            }
            if (identifierType2 == null) {
                return 1;
            }
            int iCompareTo = identifierType.compareTo(identifierType2);
            if (iCompareTo != 0) {
                return iCompareTo;
            }
        }
        String serviceTier = getServiceTier();
        String serviceTier2 = songMetadata.getServiceTier();
        if (serviceTier != serviceTier2) {
            if (serviceTier == null) {
                return -1;
            }
            if (serviceTier2 == null) {
                return 1;
            }
            int iCompareTo2 = serviceTier.compareTo(serviceTier2);
            if (iCompareTo2 != 0) {
                return iCompareTo2;
            }
        }
        if (!isHasLyrics() && songMetadata.isHasLyrics()) {
            return -1;
        }
        if (isHasLyrics() && !songMetadata.isHasLyrics()) {
            return 1;
        }
        List<TrackArtist> artists = getArtists();
        List<TrackArtist> artists2 = songMetadata.getArtists();
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
        String name2 = songMetadata.getName();
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
        String artistName = getArtistName();
        String artistName2 = songMetadata.getArtistName();
        if (artistName != artistName2) {
            if (artistName == null) {
                return -1;
            }
            if (artistName2 == null) {
                return 1;
            }
            int iCompareTo5 = artistName.compareTo(artistName2);
            if (iCompareTo5 != 0) {
                return iCompareTo5;
            }
        }
        String identifier = getIdentifier();
        String identifier2 = songMetadata.getIdentifier();
        if (identifier != identifier2) {
            if (identifier == null) {
                return -1;
            }
            if (identifier2 == null) {
                return 1;
            }
            int iCompareTo6 = identifier.compareTo(identifier2);
            if (iCompareTo6 != 0) {
                return iCompareTo6;
            }
        }
        ParentalControls parentalControls = getParentalControls();
        ParentalControls parentalControls2 = songMetadata.getParentalControls();
        if (parentalControls != parentalControls2) {
            if (parentalControls == null) {
                return -1;
            }
            if (parentalControls2 == null) {
                return 1;
            }
            int iCompareTo7 = parentalControls.compareTo(parentalControls2);
            if (iCompareTo7 != 0) {
                return iCompareTo7;
            }
        }
        if (!isIsSample() && songMetadata.isIsSample()) {
            return -1;
        }
        if (isIsSample() && !songMetadata.isIsSample()) {
            return 1;
        }
        Map<String, String> albumArt = getAlbumArt();
        Map<String, String> albumArt2 = songMetadata.getAlbumArt();
        if (albumArt != albumArt2) {
            if (albumArt == null) {
                return -1;
            }
            if (albumArt2 == null) {
                return 1;
            }
            if (albumArt instanceof Comparable) {
                int iCompareTo8 = ((Comparable) albumArt).compareTo(albumArt2);
                if (iCompareTo8 != 0) {
                    return iCompareTo8;
                }
            } else if (!albumArt.equals(albumArt2)) {
                int iHashCode3 = albumArt.hashCode();
                int iHashCode4 = albumArt2.hashCode();
                if (iHashCode3 < iHashCode4) {
                    return -1;
                }
                if (iHashCode3 > iHashCode4) {
                    return 1;
                }
            }
        }
        TrackAlbum album = getAlbum();
        TrackAlbum album2 = songMetadata.getAlbum();
        if (album != album2) {
            if (album == null) {
                return -1;
            }
            if (album2 == null) {
                return 1;
            }
            int iCompareTo9 = album.compareTo(album2);
            if (iCompareTo9 != 0) {
                return iCompareTo9;
            }
        }
        String rating = getRating();
        String rating2 = songMetadata.getRating();
        if (rating != rating2) {
            if (rating == null) {
                return -1;
            }
            if (rating2 == null) {
                return 1;
            }
            int iCompareTo10 = rating.compareTo(rating2);
            if (iCompareTo10 != 0) {
                return iCompareTo10;
            }
        }
        String licenseKeyToken = getLicenseKeyToken();
        String licenseKeyToken2 = songMetadata.getLicenseKeyToken();
        if (licenseKeyToken != licenseKeyToken2) {
            if (licenseKeyToken == null) {
                return -1;
            }
            if (licenseKeyToken2 == null) {
                return 1;
            }
            int iCompareTo11 = licenseKeyToken.compareTo(licenseKeyToken2);
            if (iCompareTo11 != 0) {
                return iCompareTo11;
            }
        }
        return super.compareTo(queuedEntityMetadataBase);
    }

    @Override // com.amazon.musicplayqueueservice.client.v2.track.QueuedEntityMetadataBase
    public boolean equals(Object obj) {
        if (!(obj instanceof SongMetadata)) {
            return false;
        }
        SongMetadata songMetadata = (SongMetadata) obj;
        return super.equals(obj) && internalEqualityCheck(getIdentifierType(), songMetadata.getIdentifierType()) && internalEqualityCheck(getServiceTier(), songMetadata.getServiceTier()) && internalEqualityCheck(Boolean.valueOf(isHasLyrics()), Boolean.valueOf(songMetadata.isHasLyrics())) && internalEqualityCheck(getArtists(), songMetadata.getArtists()) && internalEqualityCheck(getName(), songMetadata.getName()) && internalEqualityCheck(getArtistName(), songMetadata.getArtistName()) && internalEqualityCheck(getIdentifier(), songMetadata.getIdentifier()) && internalEqualityCheck(getParentalControls(), songMetadata.getParentalControls()) && internalEqualityCheck(Boolean.valueOf(isIsSample()), Boolean.valueOf(songMetadata.isIsSample())) && internalEqualityCheck(getAlbumArt(), songMetadata.getAlbumArt()) && internalEqualityCheck(getAlbum(), songMetadata.getAlbum()) && internalEqualityCheck(getRating(), songMetadata.getRating()) && internalEqualityCheck(getLicenseKeyToken(), songMetadata.getLicenseKeyToken());
    }

    public TrackAlbum getAlbum() {
        return this.album;
    }

    public Map<String, String> getAlbumArt() {
        return this.albumArt;
    }

    public String getArtistName() {
        return this.artistName;
    }

    public List<TrackArtist> getArtists() {
        return this.artists;
    }

    public String getIdentifier() {
        return this.identifier;
    }

    public String getIdentifierType() {
        return this.identifierType;
    }

    public String getLicenseKeyToken() {
        return this.licenseKeyToken;
    }

    public String getName() {
        return this.name;
    }

    public ParentalControls getParentalControls() {
        return this.parentalControls;
    }

    public String getRating() {
        return this.rating;
    }

    public String getServiceTier() {
        return this.serviceTier;
    }

    @Override // com.amazon.musicplayqueueservice.client.v2.track.QueuedEntityMetadataBase
    public int hashCode() {
        return internalHashCodeCompute(Integer.valueOf(super.hashCode()), Integer.valueOf(classNameHashCode), getIdentifierType(), getServiceTier(), Boolean.valueOf(isHasLyrics()), getArtists(), getName(), getArtistName(), getIdentifier(), getParentalControls(), Boolean.valueOf(isIsSample()), getAlbumArt(), getAlbum(), getRating(), getLicenseKeyToken());
    }

    public boolean isHasLyrics() {
        return this.hasLyrics;
    }

    public boolean isIsSample() {
        return this.isSample;
    }

    public void setAlbum(TrackAlbum trackAlbum) {
        this.album = trackAlbum;
    }

    public void setAlbumArt(Map<String, String> map) {
        this.albumArt = map;
    }

    public void setArtistName(String str) {
        this.artistName = str;
    }

    public void setArtists(List<TrackArtist> list) {
        this.artists = list;
    }

    public void setHasLyrics(boolean z) {
        this.hasLyrics = z;
    }

    public void setIdentifier(String str) {
        this.identifier = str;
    }

    public void setIdentifierType(String str) {
        this.identifierType = str;
    }

    public void setIsSample(boolean z) {
        this.isSample = z;
    }

    public void setLicenseKeyToken(String str) {
        this.licenseKeyToken = str;
    }

    public void setName(String str) {
        this.name = str;
    }

    public void setParentalControls(ParentalControls parentalControls) {
        this.parentalControls = parentalControls;
    }

    public void setRating(String str) {
        this.rating = str;
    }

    public void setServiceTier(String str) {
        this.serviceTier = str;
    }
}
