package com.amazon.layout.music.model;

import com.amazon.musicensembleservice.PlaymodeEligibility;
import java.util.Arrays;

/* JADX INFO: loaded from: classes3.dex */
public class UserPlaylistHint extends Hint {
    private static final int classNameHashCode = internalHashCodeCompute("com.amazon.layout.music.model.UserPlaylistHint");
    private String objectId;
    private String playlistImageUrl;
    private PlaymodeEligibility playmodeEligibility;
    private String recentlyPlayedEntityType;
    private Long timeStamp;
    private String title;

    private static boolean internalEqualityCheck(Object obj, Object obj2) {
        return obj == obj2 || (obj != null && obj.equals(obj2));
    }

    private static int internalHashCodeCompute(Object... objArr) {
        return Arrays.hashCode(objArr);
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.amazon.layout.music.model.Hint, java.lang.Comparable
    @Deprecated
    public int compareTo(@Deprecated Hint hint) {
        if (hint == null) {
            return -1;
        }
        if (hint == this) {
            return 0;
        }
        if (!(hint instanceof UserPlaylistHint)) {
            return 1;
        }
        UserPlaylistHint userPlaylistHint = (UserPlaylistHint) hint;
        String objectId = getObjectId();
        String objectId2 = userPlaylistHint.getObjectId();
        if (objectId != objectId2) {
            if (objectId == null) {
                return -1;
            }
            if (objectId2 == null) {
                return 1;
            }
            int iCompareTo = objectId.compareTo(objectId2);
            if (iCompareTo != 0) {
                return iCompareTo;
            }
        }
        String playlistImageUrl = getPlaylistImageUrl();
        String playlistImageUrl2 = userPlaylistHint.getPlaylistImageUrl();
        if (playlistImageUrl != playlistImageUrl2) {
            if (playlistImageUrl == null) {
                return -1;
            }
            if (playlistImageUrl2 == null) {
                return 1;
            }
            int iCompareTo2 = playlistImageUrl.compareTo(playlistImageUrl2);
            if (iCompareTo2 != 0) {
                return iCompareTo2;
            }
        }
        Long timeStamp = getTimeStamp();
        Long timeStamp2 = userPlaylistHint.getTimeStamp();
        if (timeStamp != timeStamp2) {
            if (timeStamp == null) {
                return -1;
            }
            if (timeStamp2 == null) {
                return 1;
            }
            int iCompareTo3 = timeStamp.compareTo(timeStamp2);
            if (iCompareTo3 != 0) {
                return iCompareTo3;
            }
        }
        PlaymodeEligibility playmodeEligibility = getPlaymodeEligibility();
        PlaymodeEligibility playmodeEligibility2 = userPlaylistHint.getPlaymodeEligibility();
        if (playmodeEligibility != playmodeEligibility2) {
            if (playmodeEligibility == null) {
                return -1;
            }
            if (playmodeEligibility2 == null) {
                return 1;
            }
            int iCompareTo4 = playmodeEligibility.compareTo(playmodeEligibility2);
            if (iCompareTo4 != 0) {
                return iCompareTo4;
            }
        }
        String title = getTitle();
        String title2 = userPlaylistHint.getTitle();
        if (title != title2) {
            if (title == null) {
                return -1;
            }
            if (title2 == null) {
                return 1;
            }
            int iCompareTo5 = title.compareTo(title2);
            if (iCompareTo5 != 0) {
                return iCompareTo5;
            }
        }
        String recentlyPlayedEntityType = getRecentlyPlayedEntityType();
        String recentlyPlayedEntityType2 = userPlaylistHint.getRecentlyPlayedEntityType();
        if (recentlyPlayedEntityType != recentlyPlayedEntityType2) {
            if (recentlyPlayedEntityType == null) {
                return -1;
            }
            if (recentlyPlayedEntityType2 == null) {
                return 1;
            }
            int iCompareTo6 = recentlyPlayedEntityType.compareTo(recentlyPlayedEntityType2);
            if (iCompareTo6 != 0) {
                return iCompareTo6;
            }
        }
        return super.compareTo(hint);
    }

    @Override // com.amazon.layout.music.model.Hint
    public boolean equals(Object obj) {
        if (!(obj instanceof UserPlaylistHint)) {
            return false;
        }
        UserPlaylistHint userPlaylistHint = (UserPlaylistHint) obj;
        return super.equals(obj) && internalEqualityCheck(getObjectId(), userPlaylistHint.getObjectId()) && internalEqualityCheck(getPlaylistImageUrl(), userPlaylistHint.getPlaylistImageUrl()) && internalEqualityCheck(getTimeStamp(), userPlaylistHint.getTimeStamp()) && internalEqualityCheck(getPlaymodeEligibility(), userPlaylistHint.getPlaymodeEligibility()) && internalEqualityCheck(getTitle(), userPlaylistHint.getTitle()) && internalEqualityCheck(getRecentlyPlayedEntityType(), userPlaylistHint.getRecentlyPlayedEntityType());
    }

    public String getObjectId() {
        return this.objectId;
    }

    public String getPlaylistImageUrl() {
        return this.playlistImageUrl;
    }

    public PlaymodeEligibility getPlaymodeEligibility() {
        return this.playmodeEligibility;
    }

    public String getRecentlyPlayedEntityType() {
        return this.recentlyPlayedEntityType;
    }

    public Long getTimeStamp() {
        return this.timeStamp;
    }

    public String getTitle() {
        return this.title;
    }

    @Override // com.amazon.layout.music.model.Hint
    public int hashCode() {
        return internalHashCodeCompute(Integer.valueOf(super.hashCode()), Integer.valueOf(classNameHashCode), getObjectId(), getPlaylistImageUrl(), getTimeStamp(), getPlaymodeEligibility(), getTitle(), getRecentlyPlayedEntityType());
    }

    public void setObjectId(String str) {
        this.objectId = str;
    }

    public void setPlaylistImageUrl(String str) {
        this.playlistImageUrl = str;
    }

    public void setPlaymodeEligibility(PlaymodeEligibility playmodeEligibility) {
        this.playmodeEligibility = playmodeEligibility;
    }

    public void setRecentlyPlayedEntityType(String str) {
        this.recentlyPlayedEntityType = str;
    }

    public void setTimeStamp(Long l) {
        this.timeStamp = l;
    }

    public void setTitle(String str) {
        this.title = str;
    }
}
