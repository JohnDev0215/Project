package com.amazon.musicprofileservice;

import java.util.Arrays;

/* JADX INFO: loaded from: classes5.dex */
public class ProfileInfo implements Comparable<ProfileInfo> {
    private static final int classNameHashCode = internalHashCodeCompute("com.amazon.musicprofileservice.ProfileInfo");
    private String avatarLink;
    private String customerId;
    private String profileId;
    private String profileStatus;
    private String publicName;
    private String shareListeningHistory;

    private static boolean internalEqualityCheck(Object obj, Object obj2) {
        return obj == obj2 || (obj != null && obj.equals(obj2));
    }

    private static int internalHashCodeCompute(Object... objArr) {
        return Arrays.hashCode(objArr);
    }

    @Override // java.lang.Comparable
    @Deprecated
    public int compareTo(@Deprecated ProfileInfo profileInfo) {
        if (profileInfo == null) {
            return -1;
        }
        if (profileInfo == this) {
            return 0;
        }
        String profileStatus = getProfileStatus();
        String profileStatus2 = profileInfo.getProfileStatus();
        if (profileStatus != profileStatus2) {
            if (profileStatus == null) {
                return -1;
            }
            if (profileStatus2 == null) {
                return 1;
            }
            int iCompareTo = profileStatus.compareTo(profileStatus2);
            if (iCompareTo != 0) {
                return iCompareTo;
            }
        }
        String publicName = getPublicName();
        String publicName2 = profileInfo.getPublicName();
        if (publicName != publicName2) {
            if (publicName == null) {
                return -1;
            }
            if (publicName2 == null) {
                return 1;
            }
            int iCompareTo2 = publicName.compareTo(publicName2);
            if (iCompareTo2 != 0) {
                return iCompareTo2;
            }
        }
        String shareListeningHistory = getShareListeningHistory();
        String shareListeningHistory2 = profileInfo.getShareListeningHistory();
        if (shareListeningHistory != shareListeningHistory2) {
            if (shareListeningHistory == null) {
                return -1;
            }
            if (shareListeningHistory2 == null) {
                return 1;
            }
            int iCompareTo3 = shareListeningHistory.compareTo(shareListeningHistory2);
            if (iCompareTo3 != 0) {
                return iCompareTo3;
            }
        }
        String customerId = getCustomerId();
        String customerId2 = profileInfo.getCustomerId();
        if (customerId != customerId2) {
            if (customerId == null) {
                return -1;
            }
            if (customerId2 == null) {
                return 1;
            }
            int iCompareTo4 = customerId.compareTo(customerId2);
            if (iCompareTo4 != 0) {
                return iCompareTo4;
            }
        }
        String avatarLink = getAvatarLink();
        String avatarLink2 = profileInfo.getAvatarLink();
        if (avatarLink != avatarLink2) {
            if (avatarLink == null) {
                return -1;
            }
            if (avatarLink2 == null) {
                return 1;
            }
            int iCompareTo5 = avatarLink.compareTo(avatarLink2);
            if (iCompareTo5 != 0) {
                return iCompareTo5;
            }
        }
        String profileId = getProfileId();
        String profileId2 = profileInfo.getProfileId();
        if (profileId != profileId2) {
            if (profileId == null) {
                return -1;
            }
            if (profileId2 == null) {
                return 1;
            }
            int iCompareTo6 = profileId.compareTo(profileId2);
            if (iCompareTo6 != 0) {
                return iCompareTo6;
            }
        }
        return 0;
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof ProfileInfo)) {
            return false;
        }
        ProfileInfo profileInfo = (ProfileInfo) obj;
        return internalEqualityCheck(getProfileStatus(), profileInfo.getProfileStatus()) && internalEqualityCheck(getPublicName(), profileInfo.getPublicName()) && internalEqualityCheck(getShareListeningHistory(), profileInfo.getShareListeningHistory()) && internalEqualityCheck(getCustomerId(), profileInfo.getCustomerId()) && internalEqualityCheck(getAvatarLink(), profileInfo.getAvatarLink()) && internalEqualityCheck(getProfileId(), profileInfo.getProfileId());
    }

    public String getAvatarLink() {
        return this.avatarLink;
    }

    public String getCustomerId() {
        return this.customerId;
    }

    public String getProfileId() {
        return this.profileId;
    }

    public String getProfileStatus() {
        return this.profileStatus;
    }

    public String getPublicName() {
        return this.publicName;
    }

    public String getShareListeningHistory() {
        return this.shareListeningHistory;
    }

    public int hashCode() {
        return internalHashCodeCompute(Integer.valueOf(classNameHashCode), getProfileStatus(), getPublicName(), getShareListeningHistory(), getCustomerId(), getAvatarLink(), getProfileId());
    }

    public void setAvatarLink(String str) {
        this.avatarLink = str;
    }

    public void setCustomerId(String str) {
        this.customerId = str;
    }

    public void setProfileId(String str) {
        this.profileId = str;
    }

    public void setProfileStatus(String str) {
        this.profileStatus = str;
    }

    public void setPublicName(String str) {
        this.publicName = str;
    }

    public void setShareListeningHistory(String str) {
        this.shareListeningHistory = str;
    }
}
