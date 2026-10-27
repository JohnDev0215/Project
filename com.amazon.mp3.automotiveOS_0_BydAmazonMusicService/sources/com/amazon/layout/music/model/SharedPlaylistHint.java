package com.amazon.layout.music.model;

import java.util.Arrays;

/* JADX INFO: loaded from: classes3.dex */
public class SharedPlaylistHint extends UserPlaylistHint {
    private static final int classNameHashCode = internalHashCodeCompute("com.amazon.layout.music.model.SharedPlaylistHint");
    private String curatedBy;
    private Boolean isCustomerFollowing;
    private String shareBlurb;
    private String sharingUrl;
    private String version;

    private static boolean internalEqualityCheck(Object obj, Object obj2) {
        return obj == obj2 || (obj != null && obj.equals(obj2));
    }

    private static int internalHashCodeCompute(Object... objArr) {
        return Arrays.hashCode(objArr);
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.amazon.layout.music.model.UserPlaylistHint, com.amazon.layout.music.model.Hint, java.lang.Comparable
    @Deprecated
    public int compareTo(@Deprecated Hint hint) {
        if (hint == null) {
            return -1;
        }
        if (hint == this) {
            return 0;
        }
        if (!(hint instanceof SharedPlaylistHint)) {
            return 1;
        }
        SharedPlaylistHint sharedPlaylistHint = (SharedPlaylistHint) hint;
        Boolean boolIsIsCustomerFollowing = isIsCustomerFollowing();
        Boolean boolIsIsCustomerFollowing2 = sharedPlaylistHint.isIsCustomerFollowing();
        if (boolIsIsCustomerFollowing != boolIsIsCustomerFollowing2) {
            if (boolIsIsCustomerFollowing == null) {
                return -1;
            }
            if (boolIsIsCustomerFollowing2 == null) {
                return 1;
            }
            int iCompareTo = boolIsIsCustomerFollowing.compareTo(boolIsIsCustomerFollowing2);
            if (iCompareTo != 0) {
                return iCompareTo;
            }
        }
        String shareBlurb = getShareBlurb();
        String shareBlurb2 = sharedPlaylistHint.getShareBlurb();
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
        String sharingUrl = getSharingUrl();
        String sharingUrl2 = sharedPlaylistHint.getSharingUrl();
        if (sharingUrl != sharingUrl2) {
            if (sharingUrl == null) {
                return -1;
            }
            if (sharingUrl2 == null) {
                return 1;
            }
            int iCompareTo3 = sharingUrl.compareTo(sharingUrl2);
            if (iCompareTo3 != 0) {
                return iCompareTo3;
            }
        }
        String version = getVersion();
        String version2 = sharedPlaylistHint.getVersion();
        if (version != version2) {
            if (version == null) {
                return -1;
            }
            if (version2 == null) {
                return 1;
            }
            int iCompareTo4 = version.compareTo(version2);
            if (iCompareTo4 != 0) {
                return iCompareTo4;
            }
        }
        String curatedBy = getCuratedBy();
        String curatedBy2 = sharedPlaylistHint.getCuratedBy();
        if (curatedBy != curatedBy2) {
            if (curatedBy == null) {
                return -1;
            }
            if (curatedBy2 == null) {
                return 1;
            }
            int iCompareTo5 = curatedBy.compareTo(curatedBy2);
            if (iCompareTo5 != 0) {
                return iCompareTo5;
            }
        }
        return super.compareTo(hint);
    }

    @Override // com.amazon.layout.music.model.UserPlaylistHint, com.amazon.layout.music.model.Hint
    public boolean equals(Object obj) {
        if (!(obj instanceof SharedPlaylistHint)) {
            return false;
        }
        SharedPlaylistHint sharedPlaylistHint = (SharedPlaylistHint) obj;
        return super.equals(obj) && internalEqualityCheck(isIsCustomerFollowing(), sharedPlaylistHint.isIsCustomerFollowing()) && internalEqualityCheck(getShareBlurb(), sharedPlaylistHint.getShareBlurb()) && internalEqualityCheck(getSharingUrl(), sharedPlaylistHint.getSharingUrl()) && internalEqualityCheck(getVersion(), sharedPlaylistHint.getVersion()) && internalEqualityCheck(getCuratedBy(), sharedPlaylistHint.getCuratedBy());
    }

    public String getCuratedBy() {
        return this.curatedBy;
    }

    public String getShareBlurb() {
        return this.shareBlurb;
    }

    public String getSharingUrl() {
        return this.sharingUrl;
    }

    public String getVersion() {
        return this.version;
    }

    @Override // com.amazon.layout.music.model.UserPlaylistHint, com.amazon.layout.music.model.Hint
    public int hashCode() {
        return internalHashCodeCompute(Integer.valueOf(super.hashCode()), Integer.valueOf(classNameHashCode), isIsCustomerFollowing(), getShareBlurb(), getSharingUrl(), getVersion(), getCuratedBy());
    }

    public Boolean isIsCustomerFollowing() {
        return this.isCustomerFollowing;
    }

    public void setCuratedBy(String str) {
        this.curatedBy = str;
    }

    public void setIsCustomerFollowing(Boolean bool) {
        this.isCustomerFollowing = bool;
    }

    public void setShareBlurb(String str) {
        this.shareBlurb = str;
    }

    public void setSharingUrl(String str) {
        this.sharingUrl = str;
    }

    public void setVersion(String str) {
        this.version = str;
    }
}
