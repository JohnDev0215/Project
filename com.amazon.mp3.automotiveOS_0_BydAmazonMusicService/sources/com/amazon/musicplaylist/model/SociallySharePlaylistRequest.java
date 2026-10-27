package com.amazon.musicplaylist.model;

import java.util.Arrays;

/* JADX INFO: loaded from: classes5.dex */
public class SociallySharePlaylistRequest extends AuthorizationRequiredRequest {
    private static final int classNameHashCode = internalHashCodeCompute("com.amazon.musicplaylist.model.SociallySharePlaylistRequest");
    private String collaborativeMode;
    private String playlistId;
    private String playlistVersion;
    private boolean rejectForUnshareableTracks;
    private String visibility;

    private static boolean internalEqualityCheck(Object obj, Object obj2) {
        return obj == obj2 || (obj != null && obj.equals(obj2));
    }

    private static int internalHashCodeCompute(Object... objArr) {
        return Arrays.hashCode(objArr);
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.amazon.musicplaylist.model.AuthorizationRequiredRequest, java.lang.Comparable
    @Deprecated
    public int compareTo(@Deprecated AuthorizationRequiredRequest authorizationRequiredRequest) {
        if (authorizationRequiredRequest == null) {
            return -1;
        }
        if (authorizationRequiredRequest == this) {
            return 0;
        }
        if (!(authorizationRequiredRequest instanceof SociallySharePlaylistRequest)) {
            return 1;
        }
        SociallySharePlaylistRequest sociallySharePlaylistRequest = (SociallySharePlaylistRequest) authorizationRequiredRequest;
        if (!isRejectForUnshareableTracks() && sociallySharePlaylistRequest.isRejectForUnshareableTracks()) {
            return -1;
        }
        if (isRejectForUnshareableTracks() && !sociallySharePlaylistRequest.isRejectForUnshareableTracks()) {
            return 1;
        }
        String collaborativeMode = getCollaborativeMode();
        String collaborativeMode2 = sociallySharePlaylistRequest.getCollaborativeMode();
        if (collaborativeMode != collaborativeMode2) {
            if (collaborativeMode == null) {
                return -1;
            }
            if (collaborativeMode2 == null) {
                return 1;
            }
            int iCompareTo = collaborativeMode.compareTo(collaborativeMode2);
            if (iCompareTo != 0) {
                return iCompareTo;
            }
        }
        String playlistId = getPlaylistId();
        String playlistId2 = sociallySharePlaylistRequest.getPlaylistId();
        if (playlistId != playlistId2) {
            if (playlistId == null) {
                return -1;
            }
            if (playlistId2 == null) {
                return 1;
            }
            int iCompareTo2 = playlistId.compareTo(playlistId2);
            if (iCompareTo2 != 0) {
                return iCompareTo2;
            }
        }
        String playlistVersion = getPlaylistVersion();
        String playlistVersion2 = sociallySharePlaylistRequest.getPlaylistVersion();
        if (playlistVersion != playlistVersion2) {
            if (playlistVersion == null) {
                return -1;
            }
            if (playlistVersion2 == null) {
                return 1;
            }
            int iCompareTo3 = playlistVersion.compareTo(playlistVersion2);
            if (iCompareTo3 != 0) {
                return iCompareTo3;
            }
        }
        String visibility = getVisibility();
        String visibility2 = sociallySharePlaylistRequest.getVisibility();
        if (visibility != visibility2) {
            if (visibility == null) {
                return -1;
            }
            if (visibility2 == null) {
                return 1;
            }
            int iCompareTo4 = visibility.compareTo(visibility2);
            if (iCompareTo4 != 0) {
                return iCompareTo4;
            }
        }
        return super.compareTo(authorizationRequiredRequest);
    }

    @Override // com.amazon.musicplaylist.model.AuthorizationRequiredRequest
    public boolean equals(Object obj) {
        if (!(obj instanceof SociallySharePlaylistRequest)) {
            return false;
        }
        SociallySharePlaylistRequest sociallySharePlaylistRequest = (SociallySharePlaylistRequest) obj;
        return super.equals(obj) && internalEqualityCheck(Boolean.valueOf(isRejectForUnshareableTracks()), Boolean.valueOf(sociallySharePlaylistRequest.isRejectForUnshareableTracks())) && internalEqualityCheck(getCollaborativeMode(), sociallySharePlaylistRequest.getCollaborativeMode()) && internalEqualityCheck(getPlaylistId(), sociallySharePlaylistRequest.getPlaylistId()) && internalEqualityCheck(getPlaylistVersion(), sociallySharePlaylistRequest.getPlaylistVersion()) && internalEqualityCheck(getVisibility(), sociallySharePlaylistRequest.getVisibility());
    }

    public String getCollaborativeMode() {
        return this.collaborativeMode;
    }

    public String getPlaylistId() {
        return this.playlistId;
    }

    public String getPlaylistVersion() {
        return this.playlistVersion;
    }

    public String getVisibility() {
        return this.visibility;
    }

    @Override // com.amazon.musicplaylist.model.AuthorizationRequiredRequest
    public int hashCode() {
        return internalHashCodeCompute(Integer.valueOf(super.hashCode()), Integer.valueOf(classNameHashCode), Boolean.valueOf(isRejectForUnshareableTracks()), getCollaborativeMode(), getPlaylistId(), getPlaylistVersion(), getVisibility());
    }

    public boolean isRejectForUnshareableTracks() {
        return this.rejectForUnshareableTracks;
    }

    public void setPlaylistId(String str) {
        this.playlistId = str;
    }

    public void setPlaylistVersion(String str) {
        this.playlistVersion = str;
    }

    public void setRejectForUnshareableTracks(boolean z) {
        this.rejectForUnshareableTracks = z;
    }
}
