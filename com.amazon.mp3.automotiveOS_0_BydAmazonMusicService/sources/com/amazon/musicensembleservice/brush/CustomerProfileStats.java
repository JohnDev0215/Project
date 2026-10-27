package com.amazon.musicensembleservice.brush;

import java.util.Arrays;

/* JADX INFO: loaded from: classes2.dex */
public class CustomerProfileStats implements Comparable<CustomerProfileStats> {
    private static final int classNameHashCode = internalHashCodeCompute("com.amazon.musicensembleservice.brush.CustomerProfileStats");
    private Long followerCount;
    private Long followingCount;
    private Long publicPlaylistCount;

    private static boolean internalEqualityCheck(Object obj, Object obj2) {
        return obj == obj2 || (obj != null && obj.equals(obj2));
    }

    private static int internalHashCodeCompute(Object... objArr) {
        return Arrays.hashCode(objArr);
    }

    @Override // java.lang.Comparable
    @Deprecated
    public int compareTo(@Deprecated CustomerProfileStats customerProfileStats) {
        if (customerProfileStats == null) {
            return -1;
        }
        if (customerProfileStats == this) {
            return 0;
        }
        Long publicPlaylistCount = getPublicPlaylistCount();
        Long publicPlaylistCount2 = customerProfileStats.getPublicPlaylistCount();
        if (publicPlaylistCount != publicPlaylistCount2) {
            if (publicPlaylistCount == null) {
                return -1;
            }
            if (publicPlaylistCount2 == null) {
                return 1;
            }
            int iCompareTo = publicPlaylistCount.compareTo(publicPlaylistCount2);
            if (iCompareTo != 0) {
                return iCompareTo;
            }
        }
        Long followingCount = getFollowingCount();
        Long followingCount2 = customerProfileStats.getFollowingCount();
        if (followingCount != followingCount2) {
            if (followingCount == null) {
                return -1;
            }
            if (followingCount2 == null) {
                return 1;
            }
            int iCompareTo2 = followingCount.compareTo(followingCount2);
            if (iCompareTo2 != 0) {
                return iCompareTo2;
            }
        }
        Long followerCount = getFollowerCount();
        Long followerCount2 = customerProfileStats.getFollowerCount();
        if (followerCount != followerCount2) {
            if (followerCount == null) {
                return -1;
            }
            if (followerCount2 == null) {
                return 1;
            }
            int iCompareTo3 = followerCount.compareTo(followerCount2);
            if (iCompareTo3 != 0) {
                return iCompareTo3;
            }
        }
        return 0;
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof CustomerProfileStats)) {
            return false;
        }
        CustomerProfileStats customerProfileStats = (CustomerProfileStats) obj;
        return internalEqualityCheck(getPublicPlaylistCount(), customerProfileStats.getPublicPlaylistCount()) && internalEqualityCheck(getFollowingCount(), customerProfileStats.getFollowingCount()) && internalEqualityCheck(getFollowerCount(), customerProfileStats.getFollowerCount());
    }

    public Long getFollowerCount() {
        return this.followerCount;
    }

    public Long getFollowingCount() {
        return this.followingCount;
    }

    public Long getPublicPlaylistCount() {
        return this.publicPlaylistCount;
    }

    public int hashCode() {
        return internalHashCodeCompute(Integer.valueOf(classNameHashCode), getPublicPlaylistCount(), getFollowingCount(), getFollowerCount());
    }

    public void setFollowerCount(Long l) {
        this.followerCount = l;
    }

    public void setFollowingCount(Long l) {
        this.followingCount = l;
    }

    public void setPublicPlaylistCount(Long l) {
        this.publicPlaylistCount = l;
    }
}
