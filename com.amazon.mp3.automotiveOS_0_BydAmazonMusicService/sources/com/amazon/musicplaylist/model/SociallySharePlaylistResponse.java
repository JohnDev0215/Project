package com.amazon.musicplaylist.model;

import java.util.Arrays;
import java.util.List;

/* JADX INFO: loaded from: classes5.dex */
public class SociallySharePlaylistResponse implements Comparable<SociallySharePlaylistResponse> {
    private static final int classNameHashCode = internalHashCodeCompute("com.amazon.musicplaylist.model.SociallySharePlaylistResponse");
    private String collaborativeMode;
    private String curatedBy;
    private int numberOfShareableTracks;
    private int numberOfTracksInPlaylist;
    private String playlistVersion;
    private String profileId;
    private String shareBlurb;
    private List<PlaylistTrackEntry> shareableTracks;
    private String socialPlaylistId;
    private List<PlaylistTrackEntry> unshareableTracks;
    private String url;
    private String visibility;

    private static boolean internalEqualityCheck(Object obj, Object obj2) {
        return obj == obj2 || (obj != null && obj.equals(obj2));
    }

    private static int internalHashCodeCompute(Object... objArr) {
        return Arrays.hashCode(objArr);
    }

    @Override // java.lang.Comparable
    @Deprecated
    public int compareTo(@Deprecated SociallySharePlaylistResponse sociallySharePlaylistResponse) {
        if (sociallySharePlaylistResponse == null) {
            return -1;
        }
        if (sociallySharePlaylistResponse == this) {
            return 0;
        }
        List<PlaylistTrackEntry> unshareableTracks = getUnshareableTracks();
        List<PlaylistTrackEntry> unshareableTracks2 = sociallySharePlaylistResponse.getUnshareableTracks();
        if (unshareableTracks != unshareableTracks2) {
            if (unshareableTracks == null) {
                return -1;
            }
            if (unshareableTracks2 == null) {
                return 1;
            }
            if (unshareableTracks instanceof Comparable) {
                int iCompareTo = ((Comparable) unshareableTracks).compareTo(unshareableTracks2);
                if (iCompareTo != 0) {
                    return iCompareTo;
                }
            } else if (!unshareableTracks.equals(unshareableTracks2)) {
                int iHashCode = unshareableTracks.hashCode();
                int iHashCode2 = unshareableTracks2.hashCode();
                if (iHashCode < iHashCode2) {
                    return -1;
                }
                if (iHashCode > iHashCode2) {
                    return 1;
                }
            }
        }
        String shareBlurb = getShareBlurb();
        String shareBlurb2 = sociallySharePlaylistResponse.getShareBlurb();
        if (shareBlurb != shareBlurb2) {
            if (shareBlurb == null) {
                return -1;
            }
            if (shareBlurb2 == null) {
                return 1;
            }
            int iCompareTo2 = shareBlurb.compareTo(shareBlurb2);
            if (iCompareTo2 != 0) {
                return iCompareTo2;
            }
        }
        String collaborativeMode = getCollaborativeMode();
        String collaborativeMode2 = sociallySharePlaylistResponse.getCollaborativeMode();
        if (collaborativeMode != collaborativeMode2) {
            if (collaborativeMode == null) {
                return -1;
            }
            if (collaborativeMode2 == null) {
                return 1;
            }
            int iCompareTo3 = collaborativeMode.compareTo(collaborativeMode2);
            if (iCompareTo3 != 0) {
                return iCompareTo3;
            }
        }
        String url = getUrl();
        String url2 = sociallySharePlaylistResponse.getUrl();
        if (url != url2) {
            if (url == null) {
                return -1;
            }
            if (url2 == null) {
                return 1;
            }
            int iCompareTo4 = url.compareTo(url2);
            if (iCompareTo4 != 0) {
                return iCompareTo4;
            }
        }
        List<PlaylistTrackEntry> shareableTracks = getShareableTracks();
        List<PlaylistTrackEntry> shareableTracks2 = sociallySharePlaylistResponse.getShareableTracks();
        if (shareableTracks != shareableTracks2) {
            if (shareableTracks == null) {
                return -1;
            }
            if (shareableTracks2 == null) {
                return 1;
            }
            if (shareableTracks instanceof Comparable) {
                int iCompareTo5 = ((Comparable) shareableTracks).compareTo(shareableTracks2);
                if (iCompareTo5 != 0) {
                    return iCompareTo5;
                }
            } else if (!shareableTracks.equals(shareableTracks2)) {
                int iHashCode3 = shareableTracks.hashCode();
                int iHashCode4 = shareableTracks2.hashCode();
                if (iHashCode3 < iHashCode4) {
                    return -1;
                }
                if (iHashCode3 > iHashCode4) {
                    return 1;
                }
            }
        }
        String playlistVersion = getPlaylistVersion();
        String playlistVersion2 = sociallySharePlaylistResponse.getPlaylistVersion();
        if (playlistVersion != playlistVersion2) {
            if (playlistVersion == null) {
                return -1;
            }
            if (playlistVersion2 == null) {
                return 1;
            }
            int iCompareTo6 = playlistVersion.compareTo(playlistVersion2);
            if (iCompareTo6 != 0) {
                return iCompareTo6;
            }
        }
        String visibility = getVisibility();
        String visibility2 = sociallySharePlaylistResponse.getVisibility();
        if (visibility != visibility2) {
            if (visibility == null) {
                return -1;
            }
            if (visibility2 == null) {
                return 1;
            }
            int iCompareTo7 = visibility.compareTo(visibility2);
            if (iCompareTo7 != 0) {
                return iCompareTo7;
            }
        }
        String socialPlaylistId = getSocialPlaylistId();
        String socialPlaylistId2 = sociallySharePlaylistResponse.getSocialPlaylistId();
        if (socialPlaylistId != socialPlaylistId2) {
            if (socialPlaylistId == null) {
                return -1;
            }
            if (socialPlaylistId2 == null) {
                return 1;
            }
            int iCompareTo8 = socialPlaylistId.compareTo(socialPlaylistId2);
            if (iCompareTo8 != 0) {
                return iCompareTo8;
            }
        }
        String profileId = getProfileId();
        String profileId2 = sociallySharePlaylistResponse.getProfileId();
        if (profileId != profileId2) {
            if (profileId == null) {
                return -1;
            }
            if (profileId2 == null) {
                return 1;
            }
            int iCompareTo9 = profileId.compareTo(profileId2);
            if (iCompareTo9 != 0) {
                return iCompareTo9;
            }
        }
        if (getNumberOfTracksInPlaylist() < sociallySharePlaylistResponse.getNumberOfTracksInPlaylist()) {
            return -1;
        }
        if (getNumberOfTracksInPlaylist() > sociallySharePlaylistResponse.getNumberOfTracksInPlaylist()) {
            return 1;
        }
        String curatedBy = getCuratedBy();
        String curatedBy2 = sociallySharePlaylistResponse.getCuratedBy();
        if (curatedBy != curatedBy2) {
            if (curatedBy == null) {
                return -1;
            }
            if (curatedBy2 == null) {
                return 1;
            }
            int iCompareTo10 = curatedBy.compareTo(curatedBy2);
            if (iCompareTo10 != 0) {
                return iCompareTo10;
            }
        }
        if (getNumberOfShareableTracks() < sociallySharePlaylistResponse.getNumberOfShareableTracks()) {
            return -1;
        }
        return getNumberOfShareableTracks() > sociallySharePlaylistResponse.getNumberOfShareableTracks() ? 1 : 0;
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof SociallySharePlaylistResponse)) {
            return false;
        }
        SociallySharePlaylistResponse sociallySharePlaylistResponse = (SociallySharePlaylistResponse) obj;
        return internalEqualityCheck(getUnshareableTracks(), sociallySharePlaylistResponse.getUnshareableTracks()) && internalEqualityCheck(getShareBlurb(), sociallySharePlaylistResponse.getShareBlurb()) && internalEqualityCheck(getCollaborativeMode(), sociallySharePlaylistResponse.getCollaborativeMode()) && internalEqualityCheck(getUrl(), sociallySharePlaylistResponse.getUrl()) && internalEqualityCheck(getShareableTracks(), sociallySharePlaylistResponse.getShareableTracks()) && internalEqualityCheck(getPlaylistVersion(), sociallySharePlaylistResponse.getPlaylistVersion()) && internalEqualityCheck(getVisibility(), sociallySharePlaylistResponse.getVisibility()) && internalEqualityCheck(getSocialPlaylistId(), sociallySharePlaylistResponse.getSocialPlaylistId()) && internalEqualityCheck(getProfileId(), sociallySharePlaylistResponse.getProfileId()) && internalEqualityCheck(Integer.valueOf(getNumberOfTracksInPlaylist()), Integer.valueOf(sociallySharePlaylistResponse.getNumberOfTracksInPlaylist())) && internalEqualityCheck(getCuratedBy(), sociallySharePlaylistResponse.getCuratedBy()) && internalEqualityCheck(Integer.valueOf(getNumberOfShareableTracks()), Integer.valueOf(sociallySharePlaylistResponse.getNumberOfShareableTracks()));
    }

    public String getCollaborativeMode() {
        return this.collaborativeMode;
    }

    public String getCuratedBy() {
        return this.curatedBy;
    }

    public int getNumberOfShareableTracks() {
        return this.numberOfShareableTracks;
    }

    public int getNumberOfTracksInPlaylist() {
        return this.numberOfTracksInPlaylist;
    }

    public String getPlaylistVersion() {
        return this.playlistVersion;
    }

    public String getProfileId() {
        return this.profileId;
    }

    public String getShareBlurb() {
        return this.shareBlurb;
    }

    public List<PlaylistTrackEntry> getShareableTracks() {
        return this.shareableTracks;
    }

    public String getSocialPlaylistId() {
        return this.socialPlaylistId;
    }

    public List<PlaylistTrackEntry> getUnshareableTracks() {
        return this.unshareableTracks;
    }

    public String getUrl() {
        return this.url;
    }

    public String getVisibility() {
        return this.visibility;
    }

    public int hashCode() {
        return internalHashCodeCompute(Integer.valueOf(classNameHashCode), getUnshareableTracks(), getShareBlurb(), getCollaborativeMode(), getUrl(), getShareableTracks(), getPlaylistVersion(), getVisibility(), getSocialPlaylistId(), getProfileId(), Integer.valueOf(getNumberOfTracksInPlaylist()), getCuratedBy(), Integer.valueOf(getNumberOfShareableTracks()));
    }

    public void setCollaborativeMode(String str) {
        this.collaborativeMode = str;
    }

    public void setCuratedBy(String str) {
        this.curatedBy = str;
    }

    public void setNumberOfShareableTracks(int i) {
        this.numberOfShareableTracks = i;
    }

    public void setNumberOfTracksInPlaylist(int i) {
        this.numberOfTracksInPlaylist = i;
    }

    public void setPlaylistVersion(String str) {
        this.playlistVersion = str;
    }

    public void setProfileId(String str) {
        this.profileId = str;
    }

    public void setShareBlurb(String str) {
        this.shareBlurb = str;
    }

    public void setShareableTracks(List<PlaylistTrackEntry> list) {
        this.shareableTracks = list;
    }

    public void setSocialPlaylistId(String str) {
        this.socialPlaylistId = str;
    }

    public void setUnshareableTracks(List<PlaylistTrackEntry> list) {
        this.unshareableTracks = list;
    }

    public void setUrl(String str) {
        this.url = str;
    }

    public void setVisibility(String str) {
        this.visibility = str;
    }
}
