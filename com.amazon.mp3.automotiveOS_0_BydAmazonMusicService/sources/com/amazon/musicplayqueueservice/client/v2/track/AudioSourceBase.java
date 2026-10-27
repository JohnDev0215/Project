package com.amazon.musicplayqueueservice.client.v2.track;

import java.util.Arrays;

/* JADX INFO: loaded from: classes5.dex */
public abstract class AudioSourceBase implements Comparable<AudioSourceBase> {
    private static final int classNameHashCode = internalHashCodeCompute("com.amazon.musicplayqueueservice.client.v2.track.AudioSourceBase");
    private String playerTarget;

    private static boolean internalEqualityCheck(Object obj, Object obj2) {
        return obj == obj2 || (obj != null && obj.equals(obj2));
    }

    private static int internalHashCodeCompute(Object... objArr) {
        return Arrays.hashCode(objArr);
    }

    /* JADX WARN: Can't rename method to resolve collision */
    @Override // java.lang.Comparable
    @Deprecated
    public int compareTo(@Deprecated AudioSourceBase audioSourceBase) {
        String playerTarget;
        String playerTarget2;
        if (audioSourceBase == null) {
            return -1;
        }
        if (audioSourceBase != this && (playerTarget = getPlayerTarget()) != (playerTarget2 = audioSourceBase.getPlayerTarget())) {
            if (playerTarget == null) {
                return -1;
            }
            if (playerTarget2 == null) {
                return 1;
            }
            int iCompareTo = playerTarget.compareTo(playerTarget2);
            if (iCompareTo != 0) {
                return iCompareTo;
            }
        }
        return 0;
    }

    public boolean equals(Object obj) {
        if (obj instanceof AudioSourceBase) {
            return internalEqualityCheck(getPlayerTarget(), ((AudioSourceBase) obj).getPlayerTarget());
        }
        return false;
    }

    public String getPlayerTarget() {
        return this.playerTarget;
    }

    public int hashCode() {
        return internalHashCodeCompute(Integer.valueOf(classNameHashCode), getPlayerTarget());
    }

    public void setPlayerTarget(String str) {
        this.playerTarget = str;
    }
}
