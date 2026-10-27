package com.amazon.musicplaylist.model;

import java.util.Arrays;
import java.util.List;

/* JADX INFO: loaded from: classes5.dex */
public class SharedPlaylistMetadata extends AbstractPlaylistMetadata {
    private static final int classNameHashCode = internalHashCodeCompute("com.amazon.musicplaylist.model.SharedPlaylistMetadata");
    private String collaborativeMode;
    private String curatedBy;
    private List<String> internalTags;
    private boolean isCustomerCurator;
    private boolean isCustomerFollowing;
    private String marketplaceId;
    private int numFollowers;
    private ParentalControls parentalControls;
    private String profileId;
    private String shareBlurb;

    private static boolean internalEqualityCheck(Object obj, Object obj2) {
        return obj == obj2 || (obj != null && obj.equals(obj2));
    }

    private static int internalHashCodeCompute(Object... objArr) {
        return Arrays.hashCode(objArr);
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.amazon.musicplaylist.model.AbstractPlaylistMetadata, java.lang.Comparable
    @Deprecated
    public int compareTo(@Deprecated AbstractPlaylistMetadata abstractPlaylistMetadata) {
        if (abstractPlaylistMetadata == null) {
            return -1;
        }
        if (abstractPlaylistMetadata == this) {
            return 0;
        }
        if (!(abstractPlaylistMetadata instanceof SharedPlaylistMetadata)) {
            return 1;
        }
        SharedPlaylistMetadata sharedPlaylistMetadata = (SharedPlaylistMetadata) abstractPlaylistMetadata;
        if (!isIsCustomerCurator() && sharedPlaylistMetadata.isIsCustomerCurator()) {
            return -1;
        }
        if (isIsCustomerCurator() && !sharedPlaylistMetadata.isIsCustomerCurator()) {
            return 1;
        }
        String collaborativeMode = getCollaborativeMode();
        String collaborativeMode2 = sharedPlaylistMetadata.getCollaborativeMode();
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
        if (!isIsCustomerFollowing() && sharedPlaylistMetadata.isIsCustomerFollowing()) {
            return -1;
        }
        if (isIsCustomerFollowing() && !sharedPlaylistMetadata.isIsCustomerFollowing()) {
            return 1;
        }
        String shareBlurb = getShareBlurb();
        String shareBlurb2 = sharedPlaylistMetadata.getShareBlurb();
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
        String curatedBy = getCuratedBy();
        String curatedBy2 = sharedPlaylistMetadata.getCuratedBy();
        if (curatedBy != curatedBy2) {
            if (curatedBy == null) {
                return -1;
            }
            if (curatedBy2 == null) {
                return 1;
            }
            int iCompareTo3 = curatedBy.compareTo(curatedBy2);
            if (iCompareTo3 != 0) {
                return iCompareTo3;
            }
        }
        if (getNumFollowers() < sharedPlaylistMetadata.getNumFollowers()) {
            return -1;
        }
        if (getNumFollowers() > sharedPlaylistMetadata.getNumFollowers()) {
            return 1;
        }
        String profileId = getProfileId();
        String profileId2 = sharedPlaylistMetadata.getProfileId();
        if (profileId != profileId2) {
            if (profileId == null) {
                return -1;
            }
            if (profileId2 == null) {
                return 1;
            }
            int iCompareTo4 = profileId.compareTo(profileId2);
            if (iCompareTo4 != 0) {
                return iCompareTo4;
            }
        }
        String marketplaceId = getMarketplaceId();
        String marketplaceId2 = sharedPlaylistMetadata.getMarketplaceId();
        if (marketplaceId != marketplaceId2) {
            if (marketplaceId == null) {
                return -1;
            }
            if (marketplaceId2 == null) {
                return 1;
            }
            int iCompareTo5 = marketplaceId.compareTo(marketplaceId2);
            if (iCompareTo5 != 0) {
                return iCompareTo5;
            }
        }
        List<String> internalTags = getInternalTags();
        List<String> internalTags2 = sharedPlaylistMetadata.getInternalTags();
        if (internalTags != internalTags2) {
            if (internalTags == null) {
                return -1;
            }
            if (internalTags2 == null) {
                return 1;
            }
            if (internalTags instanceof Comparable) {
                int iCompareTo6 = ((Comparable) internalTags).compareTo(internalTags2);
                if (iCompareTo6 != 0) {
                    return iCompareTo6;
                }
            } else if (!internalTags.equals(internalTags2)) {
                int iHashCode = internalTags.hashCode();
                int iHashCode2 = internalTags2.hashCode();
                if (iHashCode < iHashCode2) {
                    return -1;
                }
                if (iHashCode > iHashCode2) {
                    return 1;
                }
            }
        }
        ParentalControls parentalControls = getParentalControls();
        ParentalControls parentalControls2 = sharedPlaylistMetadata.getParentalControls();
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
        return super.compareTo(abstractPlaylistMetadata);
    }

    @Override // com.amazon.musicplaylist.model.AbstractPlaylistMetadata
    public boolean equals(Object obj) {
        if (!(obj instanceof SharedPlaylistMetadata)) {
            return false;
        }
        SharedPlaylistMetadata sharedPlaylistMetadata = (SharedPlaylistMetadata) obj;
        return super.equals(obj) && internalEqualityCheck(Boolean.valueOf(isIsCustomerCurator()), Boolean.valueOf(sharedPlaylistMetadata.isIsCustomerCurator())) && internalEqualityCheck(getCollaborativeMode(), sharedPlaylistMetadata.getCollaborativeMode()) && internalEqualityCheck(Boolean.valueOf(isIsCustomerFollowing()), Boolean.valueOf(sharedPlaylistMetadata.isIsCustomerFollowing())) && internalEqualityCheck(getShareBlurb(), sharedPlaylistMetadata.getShareBlurb()) && internalEqualityCheck(getCuratedBy(), sharedPlaylistMetadata.getCuratedBy()) && internalEqualityCheck(Integer.valueOf(getNumFollowers()), Integer.valueOf(sharedPlaylistMetadata.getNumFollowers())) && internalEqualityCheck(getProfileId(), sharedPlaylistMetadata.getProfileId()) && internalEqualityCheck(getMarketplaceId(), sharedPlaylistMetadata.getMarketplaceId()) && internalEqualityCheck(getInternalTags(), sharedPlaylistMetadata.getInternalTags()) && internalEqualityCheck(getParentalControls(), sharedPlaylistMetadata.getParentalControls());
    }

    public String getCollaborativeMode() {
        return this.collaborativeMode;
    }

    public String getCuratedBy() {
        return this.curatedBy;
    }

    public List<String> getInternalTags() {
        return this.internalTags;
    }

    public String getMarketplaceId() {
        return this.marketplaceId;
    }

    public int getNumFollowers() {
        return this.numFollowers;
    }

    public ParentalControls getParentalControls() {
        return this.parentalControls;
    }

    public String getProfileId() {
        return this.profileId;
    }

    public String getShareBlurb() {
        return this.shareBlurb;
    }

    @Override // com.amazon.musicplaylist.model.AbstractPlaylistMetadata
    public int hashCode() {
        return internalHashCodeCompute(Integer.valueOf(super.hashCode()), Integer.valueOf(classNameHashCode), Boolean.valueOf(isIsCustomerCurator()), getCollaborativeMode(), Boolean.valueOf(isIsCustomerFollowing()), getShareBlurb(), getCuratedBy(), Integer.valueOf(getNumFollowers()), getProfileId(), getMarketplaceId(), getInternalTags(), getParentalControls());
    }

    public boolean isIsCustomerCurator() {
        return this.isCustomerCurator;
    }

    public boolean isIsCustomerFollowing() {
        return this.isCustomerFollowing;
    }

    public void setCollaborativeMode(String str) {
        this.collaborativeMode = str;
    }

    public void setCuratedBy(String str) {
        this.curatedBy = str;
    }

    public void setInternalTags(List<String> list) {
        this.internalTags = list;
    }

    public void setIsCustomerCurator(boolean z) {
        this.isCustomerCurator = z;
    }

    public void setIsCustomerFollowing(boolean z) {
        this.isCustomerFollowing = z;
    }

    public void setMarketplaceId(String str) {
        this.marketplaceId = str;
    }

    public void setNumFollowers(int i) {
        this.numFollowers = i;
    }

    public void setParentalControls(ParentalControls parentalControls) {
        this.parentalControls = parentalControls;
    }

    public void setProfileId(String str) {
        this.profileId = str;
    }

    public void setShareBlurb(String str) {
        this.shareBlurb = str;
    }
}
