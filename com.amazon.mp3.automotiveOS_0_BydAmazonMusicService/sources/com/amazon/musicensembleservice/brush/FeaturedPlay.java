package com.amazon.musicensembleservice.brush;

import java.util.Arrays;
import java.util.List;

/* JADX INFO: loaded from: classes2.dex */
public class FeaturedPlay extends MusicEntity {
    private static final int classNameHashCode = internalHashCodeCompute("com.amazon.musicensembleservice.brush.FeaturedPlay");
    private String altDescription;
    private String callToActionTitle;
    private Entity entity;
    private String header;
    private List<Image> images;
    private String pageToken;
    private String pausedStateTitle;
    private String playingStateTitle;
    private String queueId;
    private Boolean startNewQueue;
    private String stationInstanceId;
    private String trackArtistsTitle;
    private List<String> trackAsins;
    private Integer trackOffsetIndex;

    private static boolean internalEqualityCheck(Object obj, Object obj2) {
        return obj == obj2 || (obj != null && obj.equals(obj2));
    }

    private static int internalHashCodeCompute(Object... objArr) {
        return Arrays.hashCode(objArr);
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.amazon.musicensembleservice.brush.MusicEntity, com.amazon.musicensembleservice.brush.Entity, java.lang.Comparable
    @Deprecated
    public int compareTo(@Deprecated Entity entity) {
        if (entity == null) {
            return -1;
        }
        if (entity == this) {
            return 0;
        }
        if (!(entity instanceof FeaturedPlay)) {
            return 1;
        }
        FeaturedPlay featuredPlay = (FeaturedPlay) entity;
        String queueId = getQueueId();
        String queueId2 = featuredPlay.getQueueId();
        if (queueId != queueId2) {
            if (queueId == null) {
                return -1;
            }
            if (queueId2 == null) {
                return 1;
            }
            int iCompareTo = queueId.compareTo(queueId2);
            if (iCompareTo != 0) {
                return iCompareTo;
            }
        }
        Integer trackOffsetIndex = getTrackOffsetIndex();
        Integer trackOffsetIndex2 = featuredPlay.getTrackOffsetIndex();
        if (trackOffsetIndex != trackOffsetIndex2) {
            if (trackOffsetIndex == null) {
                return -1;
            }
            if (trackOffsetIndex2 == null) {
                return 1;
            }
            int iCompareTo2 = trackOffsetIndex.compareTo(trackOffsetIndex2);
            if (iCompareTo2 != 0) {
                return iCompareTo2;
            }
        }
        String altDescription = getAltDescription();
        String altDescription2 = featuredPlay.getAltDescription();
        if (altDescription != altDescription2) {
            if (altDescription == null) {
                return -1;
            }
            if (altDescription2 == null) {
                return 1;
            }
            int iCompareTo3 = altDescription.compareTo(altDescription2);
            if (iCompareTo3 != 0) {
                return iCompareTo3;
            }
        }
        String stationInstanceId = getStationInstanceId();
        String stationInstanceId2 = featuredPlay.getStationInstanceId();
        if (stationInstanceId != stationInstanceId2) {
            if (stationInstanceId == null) {
                return -1;
            }
            if (stationInstanceId2 == null) {
                return 1;
            }
            int iCompareTo4 = stationInstanceId.compareTo(stationInstanceId2);
            if (iCompareTo4 != 0) {
                return iCompareTo4;
            }
        }
        String playingStateTitle = getPlayingStateTitle();
        String playingStateTitle2 = featuredPlay.getPlayingStateTitle();
        if (playingStateTitle != playingStateTitle2) {
            if (playingStateTitle == null) {
                return -1;
            }
            if (playingStateTitle2 == null) {
                return 1;
            }
            int iCompareTo5 = playingStateTitle.compareTo(playingStateTitle2);
            if (iCompareTo5 != 0) {
                return iCompareTo5;
            }
        }
        List<String> trackAsins = getTrackAsins();
        List<String> trackAsins2 = featuredPlay.getTrackAsins();
        if (trackAsins != trackAsins2) {
            if (trackAsins == null) {
                return -1;
            }
            if (trackAsins2 == null) {
                return 1;
            }
            if (trackAsins instanceof Comparable) {
                int iCompareTo6 = ((Comparable) trackAsins).compareTo(trackAsins2);
                if (iCompareTo6 != 0) {
                    return iCompareTo6;
                }
            } else if (!trackAsins.equals(trackAsins2)) {
                int iHashCode = trackAsins.hashCode();
                int iHashCode2 = trackAsins2.hashCode();
                if (iHashCode < iHashCode2) {
                    return -1;
                }
                if (iHashCode > iHashCode2) {
                    return 1;
                }
            }
        }
        List<Image> images = getImages();
        List<Image> images2 = featuredPlay.getImages();
        if (images != images2) {
            if (images == null) {
                return -1;
            }
            if (images2 == null) {
                return 1;
            }
            if (images instanceof Comparable) {
                int iCompareTo7 = ((Comparable) images).compareTo(images2);
                if (iCompareTo7 != 0) {
                    return iCompareTo7;
                }
            } else if (!images.equals(images2)) {
                int iHashCode3 = images.hashCode();
                int iHashCode4 = images2.hashCode();
                if (iHashCode3 < iHashCode4) {
                    return -1;
                }
                if (iHashCode3 > iHashCode4) {
                    return 1;
                }
            }
        }
        Boolean boolIsStartNewQueue = isStartNewQueue();
        Boolean boolIsStartNewQueue2 = featuredPlay.isStartNewQueue();
        if (boolIsStartNewQueue != boolIsStartNewQueue2) {
            if (boolIsStartNewQueue == null) {
                return -1;
            }
            if (boolIsStartNewQueue2 == null) {
                return 1;
            }
            int iCompareTo8 = boolIsStartNewQueue.compareTo(boolIsStartNewQueue2);
            if (iCompareTo8 != 0) {
                return iCompareTo8;
            }
        }
        String header = getHeader();
        String header2 = featuredPlay.getHeader();
        if (header != header2) {
            if (header == null) {
                return -1;
            }
            if (header2 == null) {
                return 1;
            }
            int iCompareTo9 = header.compareTo(header2);
            if (iCompareTo9 != 0) {
                return iCompareTo9;
            }
        }
        String pageToken = getPageToken();
        String pageToken2 = featuredPlay.getPageToken();
        if (pageToken != pageToken2) {
            if (pageToken == null) {
                return -1;
            }
            if (pageToken2 == null) {
                return 1;
            }
            int iCompareTo10 = pageToken.compareTo(pageToken2);
            if (iCompareTo10 != 0) {
                return iCompareTo10;
            }
        }
        String callToActionTitle = getCallToActionTitle();
        String callToActionTitle2 = featuredPlay.getCallToActionTitle();
        if (callToActionTitle != callToActionTitle2) {
            if (callToActionTitle == null) {
                return -1;
            }
            if (callToActionTitle2 == null) {
                return 1;
            }
            int iCompareTo11 = callToActionTitle.compareTo(callToActionTitle2);
            if (iCompareTo11 != 0) {
                return iCompareTo11;
            }
        }
        String trackArtistsTitle = getTrackArtistsTitle();
        String trackArtistsTitle2 = featuredPlay.getTrackArtistsTitle();
        if (trackArtistsTitle != trackArtistsTitle2) {
            if (trackArtistsTitle == null) {
                return -1;
            }
            if (trackArtistsTitle2 == null) {
                return 1;
            }
            int iCompareTo12 = trackArtistsTitle.compareTo(trackArtistsTitle2);
            if (iCompareTo12 != 0) {
                return iCompareTo12;
            }
        }
        Entity entity2 = getEntity();
        Entity entity3 = featuredPlay.getEntity();
        if (entity2 != entity3) {
            if (entity2 == null) {
                return -1;
            }
            if (entity3 == null) {
                return 1;
            }
            int iCompareTo13 = entity2.compareTo(entity3);
            if (iCompareTo13 != 0) {
                return iCompareTo13;
            }
        }
        String pausedStateTitle = getPausedStateTitle();
        String pausedStateTitle2 = featuredPlay.getPausedStateTitle();
        if (pausedStateTitle != pausedStateTitle2) {
            if (pausedStateTitle == null) {
                return -1;
            }
            if (pausedStateTitle2 == null) {
                return 1;
            }
            int iCompareTo14 = pausedStateTitle.compareTo(pausedStateTitle2);
            if (iCompareTo14 != 0) {
                return iCompareTo14;
            }
        }
        return super.compareTo(entity);
    }

    @Override // com.amazon.musicensembleservice.brush.MusicEntity, com.amazon.musicensembleservice.brush.Entity
    public boolean equals(Object obj) {
        if (!(obj instanceof FeaturedPlay)) {
            return false;
        }
        FeaturedPlay featuredPlay = (FeaturedPlay) obj;
        return super.equals(obj) && internalEqualityCheck(getQueueId(), featuredPlay.getQueueId()) && internalEqualityCheck(getTrackOffsetIndex(), featuredPlay.getTrackOffsetIndex()) && internalEqualityCheck(getAltDescription(), featuredPlay.getAltDescription()) && internalEqualityCheck(getStationInstanceId(), featuredPlay.getStationInstanceId()) && internalEqualityCheck(getPlayingStateTitle(), featuredPlay.getPlayingStateTitle()) && internalEqualityCheck(getTrackAsins(), featuredPlay.getTrackAsins()) && internalEqualityCheck(getImages(), featuredPlay.getImages()) && internalEqualityCheck(isStartNewQueue(), featuredPlay.isStartNewQueue()) && internalEqualityCheck(getHeader(), featuredPlay.getHeader()) && internalEqualityCheck(getPageToken(), featuredPlay.getPageToken()) && internalEqualityCheck(getCallToActionTitle(), featuredPlay.getCallToActionTitle()) && internalEqualityCheck(getTrackArtistsTitle(), featuredPlay.getTrackArtistsTitle()) && internalEqualityCheck(getEntity(), featuredPlay.getEntity()) && internalEqualityCheck(getPausedStateTitle(), featuredPlay.getPausedStateTitle());
    }

    public String getAltDescription() {
        return this.altDescription;
    }

    public String getCallToActionTitle() {
        return this.callToActionTitle;
    }

    public Entity getEntity() {
        return this.entity;
    }

    public String getHeader() {
        return this.header;
    }

    public List<Image> getImages() {
        return this.images;
    }

    public String getPageToken() {
        return this.pageToken;
    }

    public String getPausedStateTitle() {
        return this.pausedStateTitle;
    }

    public String getPlayingStateTitle() {
        return this.playingStateTitle;
    }

    public String getQueueId() {
        return this.queueId;
    }

    public String getStationInstanceId() {
        return this.stationInstanceId;
    }

    public String getTrackArtistsTitle() {
        return this.trackArtistsTitle;
    }

    public List<String> getTrackAsins() {
        return this.trackAsins;
    }

    public Integer getTrackOffsetIndex() {
        return this.trackOffsetIndex;
    }

    @Override // com.amazon.musicensembleservice.brush.MusicEntity, com.amazon.musicensembleservice.brush.Entity
    public int hashCode() {
        return internalHashCodeCompute(Integer.valueOf(super.hashCode()), Integer.valueOf(classNameHashCode), getQueueId(), getTrackOffsetIndex(), getAltDescription(), getStationInstanceId(), getPlayingStateTitle(), getTrackAsins(), getImages(), isStartNewQueue(), getHeader(), getPageToken(), getCallToActionTitle(), getTrackArtistsTitle(), getEntity(), getPausedStateTitle());
    }

    public Boolean isStartNewQueue() {
        return this.startNewQueue;
    }

    public void setAltDescription(String str) {
        this.altDescription = str;
    }

    public void setCallToActionTitle(String str) {
        this.callToActionTitle = str;
    }

    public void setEntity(Entity entity) {
        this.entity = entity;
    }

    public void setHeader(String str) {
        this.header = str;
    }

    public void setImages(List<Image> list) {
        this.images = list;
    }

    public void setPageToken(String str) {
        this.pageToken = str;
    }

    public void setPausedStateTitle(String str) {
        this.pausedStateTitle = str;
    }

    public void setPlayingStateTitle(String str) {
        this.playingStateTitle = str;
    }

    public void setQueueId(String str) {
        this.queueId = str;
    }

    public void setStartNewQueue(Boolean bool) {
        this.startNewQueue = bool;
    }

    public void setStationInstanceId(String str) {
        this.stationInstanceId = str;
    }

    public void setTrackArtistsTitle(String str) {
        this.trackArtistsTitle = str;
    }

    public void setTrackAsins(List<String> list) {
        this.trackAsins = list;
    }

    public void setTrackOffsetIndex(Integer num) {
        this.trackOffsetIndex = num;
    }
}
