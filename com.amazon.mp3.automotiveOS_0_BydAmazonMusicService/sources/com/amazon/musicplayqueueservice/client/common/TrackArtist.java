package com.amazon.musicplayqueueservice.client.common;

import java.util.Arrays;
import java.util.List;

/* JADX INFO: loaded from: classes5.dex */
public class TrackArtist implements Comparable<TrackArtist> {
    private static final int classNameHashCode = internalHashCodeCompute("com.amazon.musicplayqueueservice.client.common.TrackArtist");
    private String asin;
    private String contributorAsin;
    private String dmid;
    private String marketplaceId;
    private String name;
    private List<ArtistRole> roles;

    private static boolean internalEqualityCheck(Object obj, Object obj2) {
        return obj == obj2 || (obj != null && obj.equals(obj2));
    }

    private static int internalHashCodeCompute(Object... objArr) {
        return Arrays.hashCode(objArr);
    }

    @Override // java.lang.Comparable
    @Deprecated
    public int compareTo(@Deprecated TrackArtist trackArtist) {
        if (trackArtist == null) {
            return -1;
        }
        if (trackArtist == this) {
            return 0;
        }
        String contributorAsin = getContributorAsin();
        String contributorAsin2 = trackArtist.getContributorAsin();
        if (contributorAsin != contributorAsin2) {
            if (contributorAsin == null) {
                return -1;
            }
            if (contributorAsin2 == null) {
                return 1;
            }
            int iCompareTo = contributorAsin.compareTo(contributorAsin2);
            if (iCompareTo != 0) {
                return iCompareTo;
            }
        }
        String dmid = getDmid();
        String dmid2 = trackArtist.getDmid();
        if (dmid != dmid2) {
            if (dmid == null) {
                return -1;
            }
            if (dmid2 == null) {
                return 1;
            }
            int iCompareTo2 = dmid.compareTo(dmid2);
            if (iCompareTo2 != 0) {
                return iCompareTo2;
            }
        }
        String name = getName();
        String name2 = trackArtist.getName();
        if (name != name2) {
            if (name == null) {
                return -1;
            }
            if (name2 == null) {
                return 1;
            }
            int iCompareTo3 = name.compareTo(name2);
            if (iCompareTo3 != 0) {
                return iCompareTo3;
            }
        }
        List<ArtistRole> roles = getRoles();
        List<ArtistRole> roles2 = trackArtist.getRoles();
        if (roles != roles2) {
            if (roles == null) {
                return -1;
            }
            if (roles2 == null) {
                return 1;
            }
            if (roles instanceof Comparable) {
                int iCompareTo4 = ((Comparable) roles).compareTo(roles2);
                if (iCompareTo4 != 0) {
                    return iCompareTo4;
                }
            } else if (!roles.equals(roles2)) {
                int iHashCode = roles.hashCode();
                int iHashCode2 = roles2.hashCode();
                if (iHashCode < iHashCode2) {
                    return -1;
                }
                if (iHashCode > iHashCode2) {
                    return 1;
                }
            }
        }
        String asin = getAsin();
        String asin2 = trackArtist.getAsin();
        if (asin != asin2) {
            if (asin == null) {
                return -1;
            }
            if (asin2 == null) {
                return 1;
            }
            int iCompareTo5 = asin.compareTo(asin2);
            if (iCompareTo5 != 0) {
                return iCompareTo5;
            }
        }
        String marketplaceId = getMarketplaceId();
        String marketplaceId2 = trackArtist.getMarketplaceId();
        if (marketplaceId != marketplaceId2) {
            if (marketplaceId == null) {
                return -1;
            }
            if (marketplaceId2 == null) {
                return 1;
            }
            int iCompareTo6 = marketplaceId.compareTo(marketplaceId2);
            if (iCompareTo6 != 0) {
                return iCompareTo6;
            }
        }
        return 0;
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof TrackArtist)) {
            return false;
        }
        TrackArtist trackArtist = (TrackArtist) obj;
        return internalEqualityCheck(getContributorAsin(), trackArtist.getContributorAsin()) && internalEqualityCheck(getDmid(), trackArtist.getDmid()) && internalEqualityCheck(getName(), trackArtist.getName()) && internalEqualityCheck(getRoles(), trackArtist.getRoles()) && internalEqualityCheck(getAsin(), trackArtist.getAsin()) && internalEqualityCheck(getMarketplaceId(), trackArtist.getMarketplaceId());
    }

    public String getAsin() {
        return this.asin;
    }

    public String getContributorAsin() {
        return this.contributorAsin;
    }

    public String getDmid() {
        return this.dmid;
    }

    public String getMarketplaceId() {
        return this.marketplaceId;
    }

    public String getName() {
        return this.name;
    }

    public List<ArtistRole> getRoles() {
        return this.roles;
    }

    public int hashCode() {
        return internalHashCodeCompute(Integer.valueOf(classNameHashCode), getContributorAsin(), getDmid(), getName(), getRoles(), getAsin(), getMarketplaceId());
    }

    public void setAsin(String str) {
        this.asin = str;
    }

    public void setContributorAsin(String str) {
        this.contributorAsin = str;
    }

    public void setDmid(String str) {
        this.dmid = str;
    }

    public void setMarketplaceId(String str) {
        this.marketplaceId = str;
    }

    public void setName(String str) {
        this.name = str;
    }

    public void setRoles(List<ArtistRole> list) {
        this.roles = list;
    }
}
