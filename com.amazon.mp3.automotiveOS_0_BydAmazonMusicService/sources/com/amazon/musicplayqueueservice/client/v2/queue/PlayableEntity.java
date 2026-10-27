package com.amazon.musicplayqueueservice.client.v2.queue;

import com.amazon.musicplayqueueservice.client.v2.common.AbstractPlayableEntityIdentifier;
import java.util.Arrays;

/* JADX INFO: loaded from: classes5.dex */
public class PlayableEntity implements Comparable<PlayableEntity> {
    private static final int classNameHashCode = internalHashCodeCompute("com.amazon.musicplayqueueservice.client.v2.queue.PlayableEntity");
    private AbstractPlayableEntityIdentifier id;
    private String metricsContextIdentifier;
    private Integer shuffledIndex;

    private static boolean internalEqualityCheck(Object obj, Object obj2) {
        return obj == obj2 || (obj != null && obj.equals(obj2));
    }

    private static int internalHashCodeCompute(Object... objArr) {
        return Arrays.hashCode(objArr);
    }

    @Override // java.lang.Comparable
    @Deprecated
    public int compareTo(@Deprecated PlayableEntity playableEntity) {
        if (playableEntity == null) {
            return -1;
        }
        if (playableEntity == this) {
            return 0;
        }
        String metricsContextIdentifier = getMetricsContextIdentifier();
        String metricsContextIdentifier2 = playableEntity.getMetricsContextIdentifier();
        if (metricsContextIdentifier != metricsContextIdentifier2) {
            if (metricsContextIdentifier == null) {
                return -1;
            }
            if (metricsContextIdentifier2 == null) {
                return 1;
            }
            int iCompareTo = metricsContextIdentifier.compareTo(metricsContextIdentifier2);
            if (iCompareTo != 0) {
                return iCompareTo;
            }
        }
        AbstractPlayableEntityIdentifier id = getId();
        AbstractPlayableEntityIdentifier id2 = playableEntity.getId();
        if (id != id2) {
            if (id == null) {
                return -1;
            }
            if (id2 == null) {
                return 1;
            }
            int iCompareTo2 = id.compareTo(id2);
            if (iCompareTo2 != 0) {
                return iCompareTo2;
            }
        }
        Integer shuffledIndex = getShuffledIndex();
        Integer shuffledIndex2 = playableEntity.getShuffledIndex();
        if (shuffledIndex != shuffledIndex2) {
            if (shuffledIndex == null) {
                return -1;
            }
            if (shuffledIndex2 == null) {
                return 1;
            }
            int iCompareTo3 = shuffledIndex.compareTo(shuffledIndex2);
            if (iCompareTo3 != 0) {
                return iCompareTo3;
            }
        }
        return 0;
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof PlayableEntity)) {
            return false;
        }
        PlayableEntity playableEntity = (PlayableEntity) obj;
        return internalEqualityCheck(getMetricsContextIdentifier(), playableEntity.getMetricsContextIdentifier()) && internalEqualityCheck(getId(), playableEntity.getId()) && internalEqualityCheck(getShuffledIndex(), playableEntity.getShuffledIndex());
    }

    public AbstractPlayableEntityIdentifier getId() {
        return this.id;
    }

    public String getMetricsContextIdentifier() {
        return this.metricsContextIdentifier;
    }

    public Integer getShuffledIndex() {
        return this.shuffledIndex;
    }

    public int hashCode() {
        return internalHashCodeCompute(Integer.valueOf(classNameHashCode), getMetricsContextIdentifier(), getId(), getShuffledIndex());
    }

    public void setId(AbstractPlayableEntityIdentifier abstractPlayableEntityIdentifier) {
        this.id = abstractPlayableEntityIdentifier;
    }
}
