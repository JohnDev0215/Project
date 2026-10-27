package com.amazon.musicplayqueueservice.client.v2.queue;

import com.amazon.musicplayqueueservice.client.common.AbstractClientRequest;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

/* JADX INFO: loaded from: classes5.dex */
public abstract class AbstractCreateQueueRequest extends AbstractClientRequest {
    private static final int classNameHashCode = internalHashCodeCompute("com.amazon.musicplayqueueservice.client.v2.queue.AbstractCreateQueueRequest");
    private AdContext adContext;
    private Boolean isShuffled;
    private Map<String, Map<String, String>> metricsContexts;
    private String playMode;
    private List<PlayableEntity> playableEntityList;
    private StartAt startAt;
    private String targetId;

    private static boolean internalEqualityCheck(Object obj, Object obj2) {
        return obj == obj2 || (obj != null && obj.equals(obj2));
    }

    private static int internalHashCodeCompute(Object... objArr) {
        return Arrays.hashCode(objArr);
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // com.amazon.musicplayqueueservice.client.common.AbstractClientRequest, java.lang.Comparable
    @Deprecated
    public int compareTo(@Deprecated AbstractClientRequest abstractClientRequest) {
        if (abstractClientRequest == null) {
            return -1;
        }
        if (abstractClientRequest == this) {
            return 0;
        }
        if (!(abstractClientRequest instanceof AbstractCreateQueueRequest)) {
            return 1;
        }
        AbstractCreateQueueRequest abstractCreateQueueRequest = (AbstractCreateQueueRequest) abstractClientRequest;
        List<PlayableEntity> playableEntityList = getPlayableEntityList();
        List<PlayableEntity> playableEntityList2 = abstractCreateQueueRequest.getPlayableEntityList();
        if (playableEntityList != playableEntityList2) {
            if (playableEntityList == null) {
                return -1;
            }
            if (playableEntityList2 == null) {
                return 1;
            }
            if (playableEntityList instanceof Comparable) {
                int iCompareTo = ((Comparable) playableEntityList).compareTo(playableEntityList2);
                if (iCompareTo != 0) {
                    return iCompareTo;
                }
            } else if (!playableEntityList.equals(playableEntityList2)) {
                int iHashCode = playableEntityList.hashCode();
                int iHashCode2 = playableEntityList2.hashCode();
                if (iHashCode < iHashCode2) {
                    return -1;
                }
                if (iHashCode > iHashCode2) {
                    return 1;
                }
            }
        }
        Boolean boolIsIsShuffled = isIsShuffled();
        Boolean boolIsIsShuffled2 = abstractCreateQueueRequest.isIsShuffled();
        if (boolIsIsShuffled != boolIsIsShuffled2) {
            if (boolIsIsShuffled == null) {
                return -1;
            }
            if (boolIsIsShuffled2 == null) {
                return 1;
            }
            int iCompareTo2 = boolIsIsShuffled.compareTo(boolIsIsShuffled2);
            if (iCompareTo2 != 0) {
                return iCompareTo2;
            }
        }
        StartAt startAt = getStartAt();
        StartAt startAt2 = abstractCreateQueueRequest.getStartAt();
        if (startAt != startAt2) {
            if (startAt == null) {
                return -1;
            }
            if (startAt2 == null) {
                return 1;
            }
            int iCompareTo3 = startAt.compareTo(startAt2);
            if (iCompareTo3 != 0) {
                return iCompareTo3;
            }
        }
        AdContext adContext = getAdContext();
        AdContext adContext2 = abstractCreateQueueRequest.getAdContext();
        if (adContext != adContext2) {
            if (adContext == null) {
                return -1;
            }
            if (adContext2 == null) {
                return 1;
            }
            int iCompareTo4 = adContext.compareTo(adContext2);
            if (iCompareTo4 != 0) {
                return iCompareTo4;
            }
        }
        String playMode = getPlayMode();
        String playMode2 = abstractCreateQueueRequest.getPlayMode();
        if (playMode != playMode2) {
            if (playMode == null) {
                return -1;
            }
            if (playMode2 == null) {
                return 1;
            }
            int iCompareTo5 = playMode.compareTo(playMode2);
            if (iCompareTo5 != 0) {
                return iCompareTo5;
            }
        }
        String targetId = getTargetId();
        String targetId2 = abstractCreateQueueRequest.getTargetId();
        if (targetId != targetId2) {
            if (targetId == null) {
                return -1;
            }
            if (targetId2 == null) {
                return 1;
            }
            int iCompareTo6 = targetId.compareTo(targetId2);
            if (iCompareTo6 != 0) {
                return iCompareTo6;
            }
        }
        Map<String, Map<String, String>> metricsContexts = getMetricsContexts();
        Map<String, Map<String, String>> metricsContexts2 = abstractCreateQueueRequest.getMetricsContexts();
        if (metricsContexts != metricsContexts2) {
            if (metricsContexts == null) {
                return -1;
            }
            if (metricsContexts2 == null) {
                return 1;
            }
            if (metricsContexts instanceof Comparable) {
                int iCompareTo7 = ((Comparable) metricsContexts).compareTo(metricsContexts2);
                if (iCompareTo7 != 0) {
                    return iCompareTo7;
                }
            } else if (!metricsContexts.equals(metricsContexts2)) {
                int iHashCode3 = metricsContexts.hashCode();
                int iHashCode4 = metricsContexts2.hashCode();
                if (iHashCode3 < iHashCode4) {
                    return -1;
                }
                if (iHashCode3 > iHashCode4) {
                    return 1;
                }
            }
        }
        return super.compareTo(abstractClientRequest);
    }

    @Override // com.amazon.musicplayqueueservice.client.common.AbstractClientRequest
    public boolean equals(Object obj) {
        if (!(obj instanceof AbstractCreateQueueRequest)) {
            return false;
        }
        AbstractCreateQueueRequest abstractCreateQueueRequest = (AbstractCreateQueueRequest) obj;
        return super.equals(obj) && internalEqualityCheck(getPlayableEntityList(), abstractCreateQueueRequest.getPlayableEntityList()) && internalEqualityCheck(isIsShuffled(), abstractCreateQueueRequest.isIsShuffled()) && internalEqualityCheck(getStartAt(), abstractCreateQueueRequest.getStartAt()) && internalEqualityCheck(getAdContext(), abstractCreateQueueRequest.getAdContext()) && internalEqualityCheck(getPlayMode(), abstractCreateQueueRequest.getPlayMode()) && internalEqualityCheck(getTargetId(), abstractCreateQueueRequest.getTargetId()) && internalEqualityCheck(getMetricsContexts(), abstractCreateQueueRequest.getMetricsContexts());
    }

    public AdContext getAdContext() {
        return this.adContext;
    }

    public Map<String, Map<String, String>> getMetricsContexts() {
        return this.metricsContexts;
    }

    public String getPlayMode() {
        return this.playMode;
    }

    public List<PlayableEntity> getPlayableEntityList() {
        return this.playableEntityList;
    }

    public StartAt getStartAt() {
        return this.startAt;
    }

    public String getTargetId() {
        return this.targetId;
    }

    @Override // com.amazon.musicplayqueueservice.client.common.AbstractClientRequest
    public int hashCode() {
        return internalHashCodeCompute(Integer.valueOf(super.hashCode()), Integer.valueOf(classNameHashCode), getPlayableEntityList(), isIsShuffled(), getStartAt(), getAdContext(), getPlayMode(), getTargetId(), getMetricsContexts());
    }

    public Boolean isIsShuffled() {
        return this.isShuffled;
    }

    public void setAdContext(AdContext adContext) {
        this.adContext = adContext;
    }

    public void setIsShuffled(Boolean bool) {
        this.isShuffled = bool;
    }

    public void setPlayMode(String str) {
        this.playMode = str;
    }

    public void setPlayableEntityList(List<PlayableEntity> list) {
        this.playableEntityList = list;
    }

    public void setStartAt(StartAt startAt) {
        this.startAt = startAt;
    }
}
