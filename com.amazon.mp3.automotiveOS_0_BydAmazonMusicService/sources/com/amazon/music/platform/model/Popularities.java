package com.amazon.music.platform.model;

import java.util.Arrays;

/* JADX INFO: loaded from: classes4.dex */
public class Popularities implements Comparable<Popularities> {
    private static final int classNameHashCode = internalHashCodeCompute("com.amazon.music.platform.model.Popularities");
    private Float trackStreamLongWindowMusicSubscriptionNormalized;
    private Float trackStreamLongWindowPrimeNormalized;

    private static boolean internalEqualityCheck(Object obj, Object obj2) {
        return obj == obj2 || (obj != null && obj.equals(obj2));
    }

    private static int internalHashCodeCompute(Object... objArr) {
        return Arrays.hashCode(objArr);
    }

    @Override // java.lang.Comparable
    @Deprecated
    public int compareTo(@Deprecated Popularities popularities) {
        if (popularities == null) {
            return -1;
        }
        if (popularities == this) {
            return 0;
        }
        Float trackStreamLongWindowPrimeNormalized = getTrackStreamLongWindowPrimeNormalized();
        Float trackStreamLongWindowPrimeNormalized2 = popularities.getTrackStreamLongWindowPrimeNormalized();
        if (trackStreamLongWindowPrimeNormalized != trackStreamLongWindowPrimeNormalized2) {
            if (trackStreamLongWindowPrimeNormalized == null) {
                return -1;
            }
            if (trackStreamLongWindowPrimeNormalized2 == null) {
                return 1;
            }
            int iCompareTo = trackStreamLongWindowPrimeNormalized.compareTo(trackStreamLongWindowPrimeNormalized2);
            if (iCompareTo != 0) {
                return iCompareTo;
            }
        }
        Float trackStreamLongWindowMusicSubscriptionNormalized = getTrackStreamLongWindowMusicSubscriptionNormalized();
        Float trackStreamLongWindowMusicSubscriptionNormalized2 = popularities.getTrackStreamLongWindowMusicSubscriptionNormalized();
        if (trackStreamLongWindowMusicSubscriptionNormalized != trackStreamLongWindowMusicSubscriptionNormalized2) {
            if (trackStreamLongWindowMusicSubscriptionNormalized == null) {
                return -1;
            }
            if (trackStreamLongWindowMusicSubscriptionNormalized2 == null) {
                return 1;
            }
            int iCompareTo2 = trackStreamLongWindowMusicSubscriptionNormalized.compareTo(trackStreamLongWindowMusicSubscriptionNormalized2);
            if (iCompareTo2 != 0) {
                return iCompareTo2;
            }
        }
        return 0;
    }

    public boolean equals(Object obj) {
        if (!(obj instanceof Popularities)) {
            return false;
        }
        Popularities popularities = (Popularities) obj;
        return internalEqualityCheck(getTrackStreamLongWindowPrimeNormalized(), popularities.getTrackStreamLongWindowPrimeNormalized()) && internalEqualityCheck(getTrackStreamLongWindowMusicSubscriptionNormalized(), popularities.getTrackStreamLongWindowMusicSubscriptionNormalized());
    }

    public Float getTrackStreamLongWindowMusicSubscriptionNormalized() {
        return this.trackStreamLongWindowMusicSubscriptionNormalized;
    }

    public Float getTrackStreamLongWindowPrimeNormalized() {
        return this.trackStreamLongWindowPrimeNormalized;
    }

    public int hashCode() {
        return internalHashCodeCompute(Integer.valueOf(classNameHashCode), getTrackStreamLongWindowPrimeNormalized(), getTrackStreamLongWindowMusicSubscriptionNormalized());
    }

    public void setTrackStreamLongWindowMusicSubscriptionNormalized(Float f) {
        this.trackStreamLongWindowMusicSubscriptionNormalized = f;
    }

    public void setTrackStreamLongWindowPrimeNormalized(Float f) {
        this.trackStreamLongWindowPrimeNormalized = f;
    }
}
