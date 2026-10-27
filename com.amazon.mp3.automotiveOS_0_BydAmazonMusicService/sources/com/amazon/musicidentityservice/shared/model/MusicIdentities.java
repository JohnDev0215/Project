package com.amazon.musicidentityservice.shared.model;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

/* JADX INFO: loaded from: classes3.dex */
public class MusicIdentities implements Comparable<MusicIdentities> {
    private static final int classNameHashCode = internalHashCodeCompute("com.amazon.musicidentityservice.shared.model.MusicIdentities");
    private List<String> libraryOwnerCids;
    private String musicAccountCid;
    private Map<String, String> musicConsumerCids;
    private List<String> recentsCids;
    private String subscriptionOwnerCid;

    private static boolean internalEqualityCheck(Object obj, Object obj2) {
        return obj == obj2 || (obj != null && obj.equals(obj2));
    }

    private static int internalHashCodeCompute(Object... objArr) {
        return Arrays.hashCode(objArr);
    }

    @Override // java.lang.Comparable
    @Deprecated
    public int compareTo(@Deprecated MusicIdentities musicIdentities) {
        if (musicIdentities == null) {
            return -1;
        }
        if (musicIdentities == this) {
            return 0;
        }
        Map<String, String> musicConsumerCids = getMusicConsumerCids();
        Map<String, String> musicConsumerCids2 = musicIdentities.getMusicConsumerCids();
        if (musicConsumerCids != musicConsumerCids2) {
            if (musicConsumerCids == null) {
                return -1;
            }
            if (musicConsumerCids2 == null) {
                return 1;
            }
            if (musicConsumerCids instanceof Comparable) {
                int iCompareTo = ((Comparable) musicConsumerCids).compareTo(musicConsumerCids2);
                if (iCompareTo != 0) {
                    return iCompareTo;
                }
            } else if (!musicConsumerCids.equals(musicConsumerCids2)) {
                int iHashCode = musicConsumerCids.hashCode();
                int iHashCode2 = musicConsumerCids2.hashCode();
                if (iHashCode < iHashCode2) {
                    return -1;
                }
                if (iHashCode > iHashCode2) {
                    return 1;
                }
            }
        }
        List<String> libraryOwnerCids = getLibraryOwnerCids();
        List<String> libraryOwnerCids2 = musicIdentities.getLibraryOwnerCids();
        if (libraryOwnerCids != libraryOwnerCids2) {
            if (libraryOwnerCids == null) {
                return -1;
            }
            if (libraryOwnerCids2 == null) {
                return 1;
            }
            if (libraryOwnerCids instanceof Comparable) {
                int iCompareTo2 = ((Comparable) libraryOwnerCids).compareTo(libraryOwnerCids2);
                if (iCompareTo2 != 0) {
                    return iCompareTo2;
                }
            } else if (!libraryOwnerCids.equals(libraryOwnerCids2)) {
                int iHashCode3 = libraryOwnerCids.hashCode();
                int iHashCode4 = libraryOwnerCids2.hashCode();
                if (iHashCode3 < iHashCode4) {
                    return -1;
                }
                if (iHashCode3 > iHashCode4) {
                    return 1;
                }
            }
        }
        String musicAccountCid = getMusicAccountCid();
        String musicAccountCid2 = musicIdentities.getMusicAccountCid();
        if (musicAccountCid != musicAccountCid2) {
            if (musicAccountCid == null) {
                return -1;
            }
            if (musicAccountCid2 == null) {
                return 1;
            }
            int iCompareTo3 = musicAccountCid.compareTo(musicAccountCid2);
            if (iCompareTo3 != 0) {
                return iCompareTo3;
            }
        }
        String subscriptionOwnerCid = getSubscriptionOwnerCid();
        String subscriptionOwnerCid2 = musicIdentities.getSubscriptionOwnerCid();
        if (subscriptionOwnerCid != subscriptionOwnerCid2) {
            if (subscriptionOwnerCid == null) {
                return -1;
            }
            if (subscriptionOwnerCid2 == null) {
                return 1;
            }
            int iCompareTo4 = subscriptionOwnerCid.compareTo(subscriptionOwnerCid2);
            if (iCompareTo4 != 0) {
                return iCompareTo4;
            }
        }
        List<String> recentsCids = getRecentsCids();
        List<String> recentsCids2 = musicIdentities.getRecentsCids();
        if (recentsCids != recentsCids2) {
            if (recentsCids == null) {
                return -1;
            }
            if (recentsCids2 == null) {
                return 1;
            }
            if (recentsCids instanceof Comparable) {
                int iCompareTo5 = ((Comparable) recentsCids).compareTo(recentsCids2);
                if (iCompareTo5 != 0) {
                    return iCompareTo5;
                }
            } else if (!recentsCids.equals(recentsCids2)) {
                int iHashCode5 = recentsCids.hashCode();
                int iHashCode6 = recentsCids2.hashCode();
                if (iHashCode5 < iHashCode6) {
                    return -1;
                }
                if (iHashCode5 > iHashCode6) {
                    return 1;
                }
            }
        }
        return 0;
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof MusicIdentities)) {
            return false;
        }
        MusicIdentities musicIdentities = (MusicIdentities) obj;
        return internalEqualityCheck(getMusicConsumerCids(), musicIdentities.getMusicConsumerCids()) && internalEqualityCheck(getLibraryOwnerCids(), musicIdentities.getLibraryOwnerCids()) && internalEqualityCheck(getMusicAccountCid(), musicIdentities.getMusicAccountCid()) && internalEqualityCheck(getSubscriptionOwnerCid(), musicIdentities.getSubscriptionOwnerCid()) && internalEqualityCheck(getRecentsCids(), musicIdentities.getRecentsCids());
    }

    public List<String> getLibraryOwnerCids() {
        return this.libraryOwnerCids;
    }

    public String getMusicAccountCid() {
        return this.musicAccountCid;
    }

    public Map<String, String> getMusicConsumerCids() {
        return this.musicConsumerCids;
    }

    public List<String> getRecentsCids() {
        return this.recentsCids;
    }

    public String getSubscriptionOwnerCid() {
        return this.subscriptionOwnerCid;
    }

    public int hashCode() {
        return internalHashCodeCompute(Integer.valueOf(classNameHashCode), getMusicConsumerCids(), getLibraryOwnerCids(), getMusicAccountCid(), getSubscriptionOwnerCid(), getRecentsCids());
    }

    public void setLibraryOwnerCids(List<String> list) {
        this.libraryOwnerCids = list;
    }

    public void setMusicAccountCid(String str) {
        this.musicAccountCid = str;
    }

    public void setMusicConsumerCids(Map<String, String> map) {
        this.musicConsumerCids = map;
    }

    public void setRecentsCids(List<String> list) {
        this.recentsCids = list;
    }

    public void setSubscriptionOwnerCid(String str) {
        this.subscriptionOwnerCid = str;
    }
}
