package com.amazon.musicensembleservice.brush;

import com.amazon.musicprofileservice.ProfileInfo;
import com.amazon.musicprofileservice.Widget;
import com.amazon.musicrelationshipservice.model.Edge;
import java.util.Arrays;
import java.util.List;

/* JADX INFO: loaded from: classes2.dex */
public class CustomerProfile extends Entity {
    private static final int classNameHashCode = internalHashCodeCompute("com.amazon.musicensembleservice.brush.CustomerProfile");
    private CustomerProfileStats customerProfileStats;
    private Boolean hasFollowersNotification;
    private Boolean isNewFollower;
    private ProfileInfo profileDetails;
    private Edge relationship;
    private List<Widget> widgets;

    private static boolean internalEqualityCheck(Object obj, Object obj2) {
        return obj == obj2 || (obj != null && obj.equals(obj2));
    }

    private static int internalHashCodeCompute(Object... objArr) {
        return Arrays.hashCode(objArr);
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.amazon.musicensembleservice.brush.Entity, java.lang.Comparable
    @Deprecated
    public int compareTo(@Deprecated Entity entity) {
        if (entity == null) {
            return -1;
        }
        if (entity == this) {
            return 0;
        }
        if (!(entity instanceof CustomerProfile)) {
            return 1;
        }
        CustomerProfile customerProfile = (CustomerProfile) entity;
        Boolean boolIsIsNewFollower = isIsNewFollower();
        Boolean boolIsIsNewFollower2 = customerProfile.isIsNewFollower();
        if (boolIsIsNewFollower != boolIsIsNewFollower2) {
            if (boolIsIsNewFollower == null) {
                return -1;
            }
            if (boolIsIsNewFollower2 == null) {
                return 1;
            }
            int iCompareTo = boolIsIsNewFollower.compareTo(boolIsIsNewFollower2);
            if (iCompareTo != 0) {
                return iCompareTo;
            }
        }
        Boolean boolIsHasFollowersNotification = isHasFollowersNotification();
        Boolean boolIsHasFollowersNotification2 = customerProfile.isHasFollowersNotification();
        if (boolIsHasFollowersNotification != boolIsHasFollowersNotification2) {
            if (boolIsHasFollowersNotification == null) {
                return -1;
            }
            if (boolIsHasFollowersNotification2 == null) {
                return 1;
            }
            int iCompareTo2 = boolIsHasFollowersNotification.compareTo(boolIsHasFollowersNotification2);
            if (iCompareTo2 != 0) {
                return iCompareTo2;
            }
        }
        ProfileInfo profileDetails = getProfileDetails();
        ProfileInfo profileDetails2 = customerProfile.getProfileDetails();
        if (profileDetails != profileDetails2) {
            if (profileDetails == null) {
                return -1;
            }
            if (profileDetails2 == null) {
                return 1;
            }
            int iCompareTo3 = profileDetails.compareTo(profileDetails2);
            if (iCompareTo3 != 0) {
                return iCompareTo3;
            }
        }
        Edge relationship = getRelationship();
        Edge relationship2 = customerProfile.getRelationship();
        if (relationship != relationship2) {
            if (relationship == null) {
                return -1;
            }
            if (relationship2 == null) {
                return 1;
            }
            int iCompareTo4 = relationship.compareTo(relationship2);
            if (iCompareTo4 != 0) {
                return iCompareTo4;
            }
        }
        CustomerProfileStats customerProfileStats = getCustomerProfileStats();
        CustomerProfileStats customerProfileStats2 = customerProfile.getCustomerProfileStats();
        if (customerProfileStats != customerProfileStats2) {
            if (customerProfileStats == null) {
                return -1;
            }
            if (customerProfileStats2 == null) {
                return 1;
            }
            int iCompareTo5 = customerProfileStats.compareTo(customerProfileStats2);
            if (iCompareTo5 != 0) {
                return iCompareTo5;
            }
        }
        List<Widget> widgets = getWidgets();
        List<Widget> widgets2 = customerProfile.getWidgets();
        if (widgets != widgets2) {
            if (widgets == null) {
                return -1;
            }
            if (widgets2 == null) {
                return 1;
            }
            if (widgets instanceof Comparable) {
                int iCompareTo6 = ((Comparable) widgets).compareTo(widgets2);
                if (iCompareTo6 != 0) {
                    return iCompareTo6;
                }
            } else if (!widgets.equals(widgets2)) {
                int iHashCode = widgets.hashCode();
                int iHashCode2 = widgets2.hashCode();
                if (iHashCode < iHashCode2) {
                    return -1;
                }
                if (iHashCode > iHashCode2) {
                    return 1;
                }
            }
        }
        return super.compareTo(entity);
    }

    @Override // com.amazon.musicensembleservice.brush.Entity
    public boolean equals(Object obj) {
        if (!(obj instanceof CustomerProfile)) {
            return false;
        }
        CustomerProfile customerProfile = (CustomerProfile) obj;
        return super.equals(obj) && internalEqualityCheck(isIsNewFollower(), customerProfile.isIsNewFollower()) && internalEqualityCheck(isHasFollowersNotification(), customerProfile.isHasFollowersNotification()) && internalEqualityCheck(getProfileDetails(), customerProfile.getProfileDetails()) && internalEqualityCheck(getRelationship(), customerProfile.getRelationship()) && internalEqualityCheck(getCustomerProfileStats(), customerProfile.getCustomerProfileStats()) && internalEqualityCheck(getWidgets(), customerProfile.getWidgets());
    }

    public CustomerProfileStats getCustomerProfileStats() {
        return this.customerProfileStats;
    }

    public ProfileInfo getProfileDetails() {
        return this.profileDetails;
    }

    public Edge getRelationship() {
        return this.relationship;
    }

    public List<Widget> getWidgets() {
        return this.widgets;
    }

    @Override // com.amazon.musicensembleservice.brush.Entity
    public int hashCode() {
        return internalHashCodeCompute(Integer.valueOf(super.hashCode()), Integer.valueOf(classNameHashCode), isIsNewFollower(), isHasFollowersNotification(), getProfileDetails(), getRelationship(), getCustomerProfileStats(), getWidgets());
    }

    public Boolean isHasFollowersNotification() {
        return this.hasFollowersNotification;
    }

    public Boolean isIsNewFollower() {
        return this.isNewFollower;
    }

    public void setCustomerProfileStats(CustomerProfileStats customerProfileStats) {
        this.customerProfileStats = customerProfileStats;
    }

    public void setHasFollowersNotification(Boolean bool) {
        this.hasFollowersNotification = bool;
    }

    public void setIsNewFollower(Boolean bool) {
        this.isNewFollower = bool;
    }

    public void setProfileDetails(ProfileInfo profileInfo) {
        this.profileDetails = profileInfo;
    }

    public void setRelationship(Edge edge) {
        this.relationship = edge;
    }

    public void setWidgets(List<Widget> list) {
        this.widgets = list;
    }
}
