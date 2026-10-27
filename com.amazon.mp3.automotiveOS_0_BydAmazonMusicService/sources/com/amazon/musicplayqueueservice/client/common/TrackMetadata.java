package com.amazon.musicplayqueueservice.client.common;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

/* JADX INFO: loaded from: classes5.dex */
public class TrackMetadata implements Comparable<TrackMetadata> {
    private static final int classNameHashCode = internalHashCodeCompute("com.amazon.musicplayqueueservice.client.common.TrackMetadata");
    private TrackAlbum album;
    private Map<String, String> artUrlMap;
    private String artistName;
    private List<TrackArtist> artists;
    private List<AssetQuality> assetQualities;
    private String assetType;
    private boolean canRateTrack;
    private boolean canScrubBackward;
    private boolean canScrubForward;
    private boolean canSkipBackward;
    private boolean canSkipForward;
    private CuePoints cuePoints;
    private double durationInSeconds;
    private boolean hasLyrics;
    private String identifier;
    private String identifierType;
    private boolean isNewQueue;
    private boolean isSample;
    private Map<String, String> metricsContext;
    private String name;
    private ParentalControls parentalControls;
    private String price;
    private ProgramMetadata programMetadata;
    private String rating;
    private String serviceTier;
    private String streamingURL;
    private String trackId;

    private static boolean internalEqualityCheck(Object obj, Object obj2) {
        return obj == obj2 || (obj != null && obj.equals(obj2));
    }

    private static int internalHashCodeCompute(Object... objArr) {
        return Arrays.hashCode(objArr);
    }

    @Override // java.lang.Comparable
    @Deprecated
    public int compareTo(@Deprecated TrackMetadata trackMetadata) {
        if (trackMetadata == null) {
            return -1;
        }
        if (trackMetadata == this) {
            return 0;
        }
        Map<String, String> artUrlMap = getArtUrlMap();
        Map<String, String> artUrlMap2 = trackMetadata.getArtUrlMap();
        if (artUrlMap != artUrlMap2) {
            if (artUrlMap == null) {
                return -1;
            }
            if (artUrlMap2 == null) {
                return 1;
            }
            if (artUrlMap instanceof Comparable) {
                int iCompareTo = ((Comparable) artUrlMap).compareTo(artUrlMap2);
                if (iCompareTo != 0) {
                    return iCompareTo;
                }
            } else if (!artUrlMap.equals(artUrlMap2)) {
                int iHashCode = artUrlMap.hashCode();
                int iHashCode2 = artUrlMap2.hashCode();
                if (iHashCode < iHashCode2) {
                    return -1;
                }
                if (iHashCode > iHashCode2) {
                    return 1;
                }
            }
        }
        TrackAlbum album = getAlbum();
        TrackAlbum album2 = trackMetadata.getAlbum();
        if (album != album2) {
            if (album == null) {
                return -1;
            }
            if (album2 == null) {
                return 1;
            }
            int iCompareTo2 = album.compareTo(album2);
            if (iCompareTo2 != 0) {
                return iCompareTo2;
            }
        }
        String identifierType = getIdentifierType();
        String identifierType2 = trackMetadata.getIdentifierType();
        if (identifierType != identifierType2) {
            if (identifierType == null) {
                return -1;
            }
            if (identifierType2 == null) {
                return 1;
            }
            int iCompareTo3 = identifierType.compareTo(identifierType2);
            if (iCompareTo3 != 0) {
                return iCompareTo3;
            }
        }
        String rating = getRating();
        String rating2 = trackMetadata.getRating();
        if (rating != rating2) {
            if (rating == null) {
                return -1;
            }
            if (rating2 == null) {
                return 1;
            }
            int iCompareTo4 = rating.compareTo(rating2);
            if (iCompareTo4 != 0) {
                return iCompareTo4;
            }
        }
        if (!isCanScrubBackward() && trackMetadata.isCanScrubBackward()) {
            return -1;
        }
        if (isCanScrubBackward() && !trackMetadata.isCanScrubBackward()) {
            return 1;
        }
        ProgramMetadata programMetadata = getProgramMetadata();
        ProgramMetadata programMetadata2 = trackMetadata.getProgramMetadata();
        if (programMetadata != programMetadata2) {
            if (programMetadata == null) {
                return -1;
            }
            if (programMetadata2 == null) {
                return 1;
            }
            int iCompareTo5 = programMetadata.compareTo(programMetadata2);
            if (iCompareTo5 != 0) {
                return iCompareTo5;
            }
        }
        List<AssetQuality> assetQualities = getAssetQualities();
        List<AssetQuality> assetQualities2 = trackMetadata.getAssetQualities();
        if (assetQualities != assetQualities2) {
            if (assetQualities == null) {
                return -1;
            }
            if (assetQualities2 == null) {
                return 1;
            }
            if (assetQualities instanceof Comparable) {
                int iCompareTo6 = ((Comparable) assetQualities).compareTo(assetQualities2);
                if (iCompareTo6 != 0) {
                    return iCompareTo6;
                }
            } else if (!assetQualities.equals(assetQualities2)) {
                int iHashCode3 = assetQualities.hashCode();
                int iHashCode4 = assetQualities2.hashCode();
                if (iHashCode3 < iHashCode4) {
                    return -1;
                }
                if (iHashCode3 > iHashCode4) {
                    return 1;
                }
            }
        }
        Map<String, String> metricsContext = getMetricsContext();
        Map<String, String> metricsContext2 = trackMetadata.getMetricsContext();
        if (metricsContext != metricsContext2) {
            if (metricsContext == null) {
                return -1;
            }
            if (metricsContext2 == null) {
                return 1;
            }
            if (metricsContext instanceof Comparable) {
                int iCompareTo7 = ((Comparable) metricsContext).compareTo(metricsContext2);
                if (iCompareTo7 != 0) {
                    return iCompareTo7;
                }
            } else if (!metricsContext.equals(metricsContext2)) {
                int iHashCode5 = metricsContext.hashCode();
                int iHashCode6 = metricsContext2.hashCode();
                if (iHashCode5 < iHashCode6) {
                    return -1;
                }
                if (iHashCode5 > iHashCode6) {
                    return 1;
                }
            }
        }
        String trackId = getTrackId();
        String trackId2 = trackMetadata.getTrackId();
        if (trackId != trackId2) {
            if (trackId == null) {
                return -1;
            }
            if (trackId2 == null) {
                return 1;
            }
            int iCompareTo8 = trackId.compareTo(trackId2);
            if (iCompareTo8 != 0) {
                return iCompareTo8;
            }
        }
        if (getDurationInSeconds() < trackMetadata.getDurationInSeconds()) {
            return -1;
        }
        if (getDurationInSeconds() > trackMetadata.getDurationInSeconds()) {
            return 1;
        }
        if (!isHasLyrics() && trackMetadata.isHasLyrics()) {
            return -1;
        }
        if (isHasLyrics() && !trackMetadata.isHasLyrics()) {
            return 1;
        }
        if (!isCanSkipForward() && trackMetadata.isCanSkipForward()) {
            return -1;
        }
        if (isCanSkipForward() && !trackMetadata.isCanSkipForward()) {
            return 1;
        }
        String identifier = getIdentifier();
        String identifier2 = trackMetadata.getIdentifier();
        if (identifier != identifier2) {
            if (identifier == null) {
                return -1;
            }
            if (identifier2 == null) {
                return 1;
            }
            int iCompareTo9 = identifier.compareTo(identifier2);
            if (iCompareTo9 != 0) {
                return iCompareTo9;
            }
        }
        String streamingURL = getStreamingURL();
        String streamingURL2 = trackMetadata.getStreamingURL();
        if (streamingURL != streamingURL2) {
            if (streamingURL == null) {
                return -1;
            }
            if (streamingURL2 == null) {
                return 1;
            }
            int iCompareTo10 = streamingURL.compareTo(streamingURL2);
            if (iCompareTo10 != 0) {
                return iCompareTo10;
            }
        }
        CuePoints cuePoints = getCuePoints();
        CuePoints cuePoints2 = trackMetadata.getCuePoints();
        if (cuePoints != cuePoints2) {
            if (cuePoints == null) {
                return -1;
            }
            if (cuePoints2 == null) {
                return 1;
            }
            int iCompareTo11 = cuePoints.compareTo(cuePoints2);
            if (iCompareTo11 != 0) {
                return iCompareTo11;
            }
        }
        if (!isIsNewQueue() && trackMetadata.isIsNewQueue()) {
            return -1;
        }
        if (isIsNewQueue() && !trackMetadata.isIsNewQueue()) {
            return 1;
        }
        String name = getName();
        String name2 = trackMetadata.getName();
        if (name != name2) {
            if (name == null) {
                return -1;
            }
            if (name2 == null) {
                return 1;
            }
            int iCompareTo12 = name.compareTo(name2);
            if (iCompareTo12 != 0) {
                return iCompareTo12;
            }
        }
        ParentalControls parentalControls = getParentalControls();
        ParentalControls parentalControls2 = trackMetadata.getParentalControls();
        if (parentalControls != parentalControls2) {
            if (parentalControls == null) {
                return -1;
            }
            if (parentalControls2 == null) {
                return 1;
            }
            int iCompareTo13 = parentalControls.compareTo(parentalControls2);
            if (iCompareTo13 != 0) {
                return iCompareTo13;
            }
        }
        if (!isCanSkipBackward() && trackMetadata.isCanSkipBackward()) {
            return -1;
        }
        if (isCanSkipBackward() && !trackMetadata.isCanSkipBackward()) {
            return 1;
        }
        String serviceTier = getServiceTier();
        String serviceTier2 = trackMetadata.getServiceTier();
        if (serviceTier != serviceTier2) {
            if (serviceTier == null) {
                return -1;
            }
            if (serviceTier2 == null) {
                return 1;
            }
            int iCompareTo14 = serviceTier.compareTo(serviceTier2);
            if (iCompareTo14 != 0) {
                return iCompareTo14;
            }
        }
        String artistName = getArtistName();
        String artistName2 = trackMetadata.getArtistName();
        if (artistName != artistName2) {
            if (artistName == null) {
                return -1;
            }
            if (artistName2 == null) {
                return 1;
            }
            int iCompareTo15 = artistName.compareTo(artistName2);
            if (iCompareTo15 != 0) {
                return iCompareTo15;
            }
        }
        String price = getPrice();
        String price2 = trackMetadata.getPrice();
        if (price != price2) {
            if (price == null) {
                return -1;
            }
            if (price2 == null) {
                return 1;
            }
            int iCompareTo16 = price.compareTo(price2);
            if (iCompareTo16 != 0) {
                return iCompareTo16;
            }
        }
        List<TrackArtist> artists = getArtists();
        List<TrackArtist> artists2 = trackMetadata.getArtists();
        if (artists != artists2) {
            if (artists == null) {
                return -1;
            }
            if (artists2 == null) {
                return 1;
            }
            if (artists instanceof Comparable) {
                int iCompareTo17 = ((Comparable) artists).compareTo(artists2);
                if (iCompareTo17 != 0) {
                    return iCompareTo17;
                }
            } else if (!artists.equals(artists2)) {
                int iHashCode7 = artists.hashCode();
                int iHashCode8 = artists2.hashCode();
                if (iHashCode7 < iHashCode8) {
                    return -1;
                }
                if (iHashCode7 > iHashCode8) {
                    return 1;
                }
            }
        }
        if (!isCanRateTrack() && trackMetadata.isCanRateTrack()) {
            return -1;
        }
        if (isCanRateTrack() && !trackMetadata.isCanRateTrack()) {
            return 1;
        }
        if (!isIsSample() && trackMetadata.isIsSample()) {
            return -1;
        }
        if (isIsSample() && !trackMetadata.isIsSample()) {
            return 1;
        }
        if (!isCanScrubForward() && trackMetadata.isCanScrubForward()) {
            return -1;
        }
        if (isCanScrubForward() && !trackMetadata.isCanScrubForward()) {
            return 1;
        }
        String assetType = getAssetType();
        String assetType2 = trackMetadata.getAssetType();
        if (assetType != assetType2) {
            if (assetType == null) {
                return -1;
            }
            if (assetType2 == null) {
                return 1;
            }
            int iCompareTo18 = assetType.compareTo(assetType2);
            if (iCompareTo18 != 0) {
                return iCompareTo18;
            }
        }
        return 0;
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof TrackMetadata)) {
            return false;
        }
        TrackMetadata trackMetadata = (TrackMetadata) obj;
        return internalEqualityCheck(getArtUrlMap(), trackMetadata.getArtUrlMap()) && internalEqualityCheck(getAlbum(), trackMetadata.getAlbum()) && internalEqualityCheck(getIdentifierType(), trackMetadata.getIdentifierType()) && internalEqualityCheck(getRating(), trackMetadata.getRating()) && internalEqualityCheck(Boolean.valueOf(isCanScrubBackward()), Boolean.valueOf(trackMetadata.isCanScrubBackward())) && internalEqualityCheck(getProgramMetadata(), trackMetadata.getProgramMetadata()) && internalEqualityCheck(getAssetQualities(), trackMetadata.getAssetQualities()) && internalEqualityCheck(getMetricsContext(), trackMetadata.getMetricsContext()) && internalEqualityCheck(getTrackId(), trackMetadata.getTrackId()) && internalEqualityCheck(Double.valueOf(getDurationInSeconds()), Double.valueOf(trackMetadata.getDurationInSeconds())) && internalEqualityCheck(Boolean.valueOf(isHasLyrics()), Boolean.valueOf(trackMetadata.isHasLyrics())) && internalEqualityCheck(Boolean.valueOf(isCanSkipForward()), Boolean.valueOf(trackMetadata.isCanSkipForward())) && internalEqualityCheck(getIdentifier(), trackMetadata.getIdentifier()) && internalEqualityCheck(getStreamingURL(), trackMetadata.getStreamingURL()) && internalEqualityCheck(getCuePoints(), trackMetadata.getCuePoints()) && internalEqualityCheck(Boolean.valueOf(isIsNewQueue()), Boolean.valueOf(trackMetadata.isIsNewQueue())) && internalEqualityCheck(getName(), trackMetadata.getName()) && internalEqualityCheck(getParentalControls(), trackMetadata.getParentalControls()) && internalEqualityCheck(Boolean.valueOf(isCanSkipBackward()), Boolean.valueOf(trackMetadata.isCanSkipBackward())) && internalEqualityCheck(getServiceTier(), trackMetadata.getServiceTier()) && internalEqualityCheck(getArtistName(), trackMetadata.getArtistName()) && internalEqualityCheck(getPrice(), trackMetadata.getPrice()) && internalEqualityCheck(getArtists(), trackMetadata.getArtists()) && internalEqualityCheck(Boolean.valueOf(isCanRateTrack()), Boolean.valueOf(trackMetadata.isCanRateTrack())) && internalEqualityCheck(Boolean.valueOf(isIsSample()), Boolean.valueOf(trackMetadata.isIsSample())) && internalEqualityCheck(Boolean.valueOf(isCanScrubForward()), Boolean.valueOf(trackMetadata.isCanScrubForward())) && internalEqualityCheck(getAssetType(), trackMetadata.getAssetType());
    }

    public TrackAlbum getAlbum() {
        return this.album;
    }

    public Map<String, String> getArtUrlMap() {
        return this.artUrlMap;
    }

    public String getArtistName() {
        return this.artistName;
    }

    public List<TrackArtist> getArtists() {
        return this.artists;
    }

    public List<AssetQuality> getAssetQualities() {
        return this.assetQualities;
    }

    public String getAssetType() {
        return this.assetType;
    }

    public CuePoints getCuePoints() {
        return this.cuePoints;
    }

    public double getDurationInSeconds() {
        return this.durationInSeconds;
    }

    public String getIdentifier() {
        return this.identifier;
    }

    public String getIdentifierType() {
        return this.identifierType;
    }

    public Map<String, String> getMetricsContext() {
        return this.metricsContext;
    }

    public String getName() {
        return this.name;
    }

    public ParentalControls getParentalControls() {
        return this.parentalControls;
    }

    public String getPrice() {
        return this.price;
    }

    public ProgramMetadata getProgramMetadata() {
        return this.programMetadata;
    }

    public String getRating() {
        return this.rating;
    }

    public String getServiceTier() {
        return this.serviceTier;
    }

    public String getStreamingURL() {
        return this.streamingURL;
    }

    public String getTrackId() {
        return this.trackId;
    }

    public int hashCode() {
        return internalHashCodeCompute(Integer.valueOf(classNameHashCode), getArtUrlMap(), getAlbum(), getIdentifierType(), getRating(), Boolean.valueOf(isCanScrubBackward()), getProgramMetadata(), getAssetQualities(), getMetricsContext(), getTrackId(), Double.valueOf(getDurationInSeconds()), Boolean.valueOf(isHasLyrics()), Boolean.valueOf(isCanSkipForward()), getIdentifier(), getStreamingURL(), getCuePoints(), Boolean.valueOf(isIsNewQueue()), getName(), getParentalControls(), Boolean.valueOf(isCanSkipBackward()), getServiceTier(), getArtistName(), getPrice(), getArtists(), Boolean.valueOf(isCanRateTrack()), Boolean.valueOf(isIsSample()), Boolean.valueOf(isCanScrubForward()), getAssetType());
    }

    public boolean isCanRateTrack() {
        return this.canRateTrack;
    }

    public boolean isCanScrubBackward() {
        return this.canScrubBackward;
    }

    public boolean isCanScrubForward() {
        return this.canScrubForward;
    }

    public boolean isCanSkipBackward() {
        return this.canSkipBackward;
    }

    public boolean isCanSkipForward() {
        return this.canSkipForward;
    }

    public boolean isHasLyrics() {
        return this.hasLyrics;
    }

    public boolean isIsNewQueue() {
        return this.isNewQueue;
    }

    public boolean isIsSample() {
        return this.isSample;
    }

    public void setAlbum(TrackAlbum trackAlbum) {
        this.album = trackAlbum;
    }

    public void setArtUrlMap(Map<String, String> map) {
        this.artUrlMap = map;
    }

    public void setArtistName(String str) {
        this.artistName = str;
    }

    public void setArtists(List<TrackArtist> list) {
        this.artists = list;
    }

    public void setAssetQualities(List<AssetQuality> list) {
        this.assetQualities = list;
    }

    public void setAssetType(String str) {
        this.assetType = str;
    }

    public void setCanRateTrack(boolean z) {
        this.canRateTrack = z;
    }

    public void setCanScrubBackward(boolean z) {
        this.canScrubBackward = z;
    }

    public void setCanScrubForward(boolean z) {
        this.canScrubForward = z;
    }

    public void setCanSkipBackward(boolean z) {
        this.canSkipBackward = z;
    }

    public void setCanSkipForward(boolean z) {
        this.canSkipForward = z;
    }

    public void setCuePoints(CuePoints cuePoints) {
        this.cuePoints = cuePoints;
    }

    public void setDurationInSeconds(double d) {
        this.durationInSeconds = d;
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

    public void setIsNewQueue(boolean z) {
        this.isNewQueue = z;
    }

    public void setIsSample(boolean z) {
        this.isSample = z;
    }

    public void setMetricsContext(Map<String, String> map) {
        this.metricsContext = map;
    }

    public void setName(String str) {
        this.name = str;
    }

    public void setParentalControls(ParentalControls parentalControls) {
        this.parentalControls = parentalControls;
    }

    public void setPrice(String str) {
        this.price = str;
    }

    public void setProgramMetadata(ProgramMetadata programMetadata) {
        this.programMetadata = programMetadata;
    }

    public void setRating(String str) {
        this.rating = str;
    }

    public void setServiceTier(String str) {
        this.serviceTier = str;
    }

    public void setStreamingURL(String str) {
        this.streamingURL = str;
    }

    public void setTrackId(String str) {
        this.trackId = str;
    }
}
