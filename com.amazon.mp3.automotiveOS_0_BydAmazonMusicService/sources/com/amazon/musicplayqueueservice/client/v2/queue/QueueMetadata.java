package com.amazon.musicplayqueueservice.client.v2.queue;

import java.util.Arrays;
import java.util.Map;

/* JADX INFO: loaded from: classes5.dex */
public class QueueMetadata implements Comparable<QueueMetadata> {
    private static final int classNameHashCode = internalHashCodeCompute("com.amazon.musicplayqueueservice.client.v2.queue.QueueMetadata");
    private Boolean hideUpcomingTracks;
    private Map<String, String> imageUrlMap;
    private int maximumNumberOfTracksThatCanBeRequestedPerPage;
    private int maximumTrackLookAhead;
    private int numberOfTracksRemainingBeforeReplenishing;
    private String serviceTier;
    private String title;

    private static boolean internalEqualityCheck(Object obj, Object obj2) {
        return obj == obj2 || (obj != null && obj.equals(obj2));
    }

    private static int internalHashCodeCompute(Object... objArr) {
        return Arrays.hashCode(objArr);
    }

    @Override // java.lang.Comparable
    @Deprecated
    public int compareTo(@Deprecated QueueMetadata queueMetadata) {
        if (queueMetadata == null) {
            return -1;
        }
        if (queueMetadata == this) {
            return 0;
        }
        if (getNumberOfTracksRemainingBeforeReplenishing() < queueMetadata.getNumberOfTracksRemainingBeforeReplenishing()) {
            return -1;
        }
        if (getNumberOfTracksRemainingBeforeReplenishing() > queueMetadata.getNumberOfTracksRemainingBeforeReplenishing()) {
            return 1;
        }
        Boolean boolIsHideUpcomingTracks = isHideUpcomingTracks();
        Boolean boolIsHideUpcomingTracks2 = queueMetadata.isHideUpcomingTracks();
        if (boolIsHideUpcomingTracks != boolIsHideUpcomingTracks2) {
            if (boolIsHideUpcomingTracks == null) {
                return -1;
            }
            if (boolIsHideUpcomingTracks2 == null) {
                return 1;
            }
            int iCompareTo = boolIsHideUpcomingTracks.compareTo(boolIsHideUpcomingTracks2);
            if (iCompareTo != 0) {
                return iCompareTo;
            }
        }
        Map<String, String> imageUrlMap = getImageUrlMap();
        Map<String, String> imageUrlMap2 = queueMetadata.getImageUrlMap();
        if (imageUrlMap != imageUrlMap2) {
            if (imageUrlMap == null) {
                return -1;
            }
            if (imageUrlMap2 == null) {
                return 1;
            }
            if (imageUrlMap instanceof Comparable) {
                int iCompareTo2 = ((Comparable) imageUrlMap).compareTo(imageUrlMap2);
                if (iCompareTo2 != 0) {
                    return iCompareTo2;
                }
            } else if (!imageUrlMap.equals(imageUrlMap2)) {
                int iHashCode = imageUrlMap.hashCode();
                int iHashCode2 = imageUrlMap2.hashCode();
                if (iHashCode < iHashCode2) {
                    return -1;
                }
                if (iHashCode > iHashCode2) {
                    return 1;
                }
            }
        }
        if (getMaximumNumberOfTracksThatCanBeRequestedPerPage() < queueMetadata.getMaximumNumberOfTracksThatCanBeRequestedPerPage()) {
            return -1;
        }
        if (getMaximumNumberOfTracksThatCanBeRequestedPerPage() > queueMetadata.getMaximumNumberOfTracksThatCanBeRequestedPerPage()) {
            return 1;
        }
        if (getMaximumTrackLookAhead() < queueMetadata.getMaximumTrackLookAhead()) {
            return -1;
        }
        if (getMaximumTrackLookAhead() > queueMetadata.getMaximumTrackLookAhead()) {
            return 1;
        }
        String title = getTitle();
        String title2 = queueMetadata.getTitle();
        if (title != title2) {
            if (title == null) {
                return -1;
            }
            if (title2 == null) {
                return 1;
            }
            int iCompareTo3 = title.compareTo(title2);
            if (iCompareTo3 != 0) {
                return iCompareTo3;
            }
        }
        String serviceTier = getServiceTier();
        String serviceTier2 = queueMetadata.getServiceTier();
        if (serviceTier != serviceTier2) {
            if (serviceTier == null) {
                return -1;
            }
            if (serviceTier2 == null) {
                return 1;
            }
            int iCompareTo4 = serviceTier.compareTo(serviceTier2);
            if (iCompareTo4 != 0) {
                return iCompareTo4;
            }
        }
        return 0;
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof QueueMetadata)) {
            return false;
        }
        QueueMetadata queueMetadata = (QueueMetadata) obj;
        return internalEqualityCheck(Integer.valueOf(getNumberOfTracksRemainingBeforeReplenishing()), Integer.valueOf(queueMetadata.getNumberOfTracksRemainingBeforeReplenishing())) && internalEqualityCheck(isHideUpcomingTracks(), queueMetadata.isHideUpcomingTracks()) && internalEqualityCheck(getImageUrlMap(), queueMetadata.getImageUrlMap()) && internalEqualityCheck(Integer.valueOf(getMaximumNumberOfTracksThatCanBeRequestedPerPage()), Integer.valueOf(queueMetadata.getMaximumNumberOfTracksThatCanBeRequestedPerPage())) && internalEqualityCheck(Integer.valueOf(getMaximumTrackLookAhead()), Integer.valueOf(queueMetadata.getMaximumTrackLookAhead())) && internalEqualityCheck(getTitle(), queueMetadata.getTitle()) && internalEqualityCheck(getServiceTier(), queueMetadata.getServiceTier());
    }

    public Map<String, String> getImageUrlMap() {
        return this.imageUrlMap;
    }

    public int getMaximumNumberOfTracksThatCanBeRequestedPerPage() {
        return this.maximumNumberOfTracksThatCanBeRequestedPerPage;
    }

    public int getMaximumTrackLookAhead() {
        return this.maximumTrackLookAhead;
    }

    public int getNumberOfTracksRemainingBeforeReplenishing() {
        return this.numberOfTracksRemainingBeforeReplenishing;
    }

    public String getServiceTier() {
        return this.serviceTier;
    }

    public String getTitle() {
        return this.title;
    }

    public int hashCode() {
        return internalHashCodeCompute(Integer.valueOf(classNameHashCode), Integer.valueOf(getNumberOfTracksRemainingBeforeReplenishing()), isHideUpcomingTracks(), getImageUrlMap(), Integer.valueOf(getMaximumNumberOfTracksThatCanBeRequestedPerPage()), Integer.valueOf(getMaximumTrackLookAhead()), getTitle(), getServiceTier());
    }

    public Boolean isHideUpcomingTracks() {
        return this.hideUpcomingTracks;
    }

    public void setHideUpcomingTracks(Boolean bool) {
        this.hideUpcomingTracks = bool;
    }

    public void setImageUrlMap(Map<String, String> map) {
        this.imageUrlMap = map;
    }

    public void setMaximumNumberOfTracksThatCanBeRequestedPerPage(int i) {
        this.maximumNumberOfTracksThatCanBeRequestedPerPage = i;
    }

    public void setMaximumTrackLookAhead(int i) {
        this.maximumTrackLookAhead = i;
    }

    public void setNumberOfTracksRemainingBeforeReplenishing(int i) {
        this.numberOfTracksRemainingBeforeReplenishing = i;
    }

    public void setServiceTier(String str) {
        this.serviceTier = str;
    }

    public void setTitle(String str) {
        this.title = str;
    }
}
