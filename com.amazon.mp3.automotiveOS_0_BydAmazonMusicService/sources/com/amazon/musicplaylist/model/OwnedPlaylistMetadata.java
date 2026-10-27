package com.amazon.musicplaylist.model;

import java.util.Arrays;
import java.util.List;

/* JADX INFO: loaded from: classes5.dex */
public class OwnedPlaylistMetadata extends AbstractPlaylistMetadata {
    private static final int classNameHashCode = internalHashCodeCompute("com.amazon.musicplaylist.model.OwnedPlaylistMetadata");
    private List<String> internalTags;
    private ParentalControls parentalControls;
    private List<String> socialSharingErrors;

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
        if (!(abstractPlaylistMetadata instanceof OwnedPlaylistMetadata)) {
            return 1;
        }
        OwnedPlaylistMetadata ownedPlaylistMetadata = (OwnedPlaylistMetadata) abstractPlaylistMetadata;
        List<String> socialSharingErrors = getSocialSharingErrors();
        List<String> socialSharingErrors2 = ownedPlaylistMetadata.getSocialSharingErrors();
        if (socialSharingErrors != socialSharingErrors2) {
            if (socialSharingErrors == null) {
                return -1;
            }
            if (socialSharingErrors2 == null) {
                return 1;
            }
            if (socialSharingErrors instanceof Comparable) {
                int iCompareTo = ((Comparable) socialSharingErrors).compareTo(socialSharingErrors2);
                if (iCompareTo != 0) {
                    return iCompareTo;
                }
            } else if (!socialSharingErrors.equals(socialSharingErrors2)) {
                int iHashCode = socialSharingErrors.hashCode();
                int iHashCode2 = socialSharingErrors2.hashCode();
                if (iHashCode < iHashCode2) {
                    return -1;
                }
                if (iHashCode > iHashCode2) {
                    return 1;
                }
            }
        }
        List<String> internalTags = getInternalTags();
        List<String> internalTags2 = ownedPlaylistMetadata.getInternalTags();
        if (internalTags != internalTags2) {
            if (internalTags == null) {
                return -1;
            }
            if (internalTags2 == null) {
                return 1;
            }
            if (internalTags instanceof Comparable) {
                int iCompareTo2 = ((Comparable) internalTags).compareTo(internalTags2);
                if (iCompareTo2 != 0) {
                    return iCompareTo2;
                }
            } else if (!internalTags.equals(internalTags2)) {
                int iHashCode3 = internalTags.hashCode();
                int iHashCode4 = internalTags2.hashCode();
                if (iHashCode3 < iHashCode4) {
                    return -1;
                }
                if (iHashCode3 > iHashCode4) {
                    return 1;
                }
            }
        }
        ParentalControls parentalControls = getParentalControls();
        ParentalControls parentalControls2 = ownedPlaylistMetadata.getParentalControls();
        if (parentalControls != parentalControls2) {
            if (parentalControls == null) {
                return -1;
            }
            if (parentalControls2 == null) {
                return 1;
            }
            int iCompareTo3 = parentalControls.compareTo(parentalControls2);
            if (iCompareTo3 != 0) {
                return iCompareTo3;
            }
        }
        return super.compareTo(abstractPlaylistMetadata);
    }

    @Override // com.amazon.musicplaylist.model.AbstractPlaylistMetadata
    public boolean equals(Object obj) {
        if (!(obj instanceof OwnedPlaylistMetadata)) {
            return false;
        }
        OwnedPlaylistMetadata ownedPlaylistMetadata = (OwnedPlaylistMetadata) obj;
        return super.equals(obj) && internalEqualityCheck(getSocialSharingErrors(), ownedPlaylistMetadata.getSocialSharingErrors()) && internalEqualityCheck(getInternalTags(), ownedPlaylistMetadata.getInternalTags()) && internalEqualityCheck(getParentalControls(), ownedPlaylistMetadata.getParentalControls());
    }

    public List<String> getInternalTags() {
        return this.internalTags;
    }

    public ParentalControls getParentalControls() {
        return this.parentalControls;
    }

    public List<String> getSocialSharingErrors() {
        return this.socialSharingErrors;
    }

    @Override // com.amazon.musicplaylist.model.AbstractPlaylistMetadata
    public int hashCode() {
        return internalHashCodeCompute(Integer.valueOf(super.hashCode()), Integer.valueOf(classNameHashCode), getSocialSharingErrors(), getInternalTags(), getParentalControls());
    }

    public void setInternalTags(List<String> list) {
        this.internalTags = list;
    }

    public void setParentalControls(ParentalControls parentalControls) {
        this.parentalControls = parentalControls;
    }

    public void setSocialSharingErrors(List<String> list) {
        this.socialSharingErrors = list;
    }
}
