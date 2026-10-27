package com.amazon.music.nautilus;

/* JADX INFO: loaded from: classes4.dex */
public class SubscriptionOffers {
    private final boolean hasFreeTrial;

    public SubscriptionOffers(boolean z) {
        this.hasFreeTrial = z;
    }

    public boolean hasFreeTrial() {
        return this.hasFreeTrial;
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        return equals(Boolean.valueOf(this.hasFreeTrial), Boolean.valueOf(((SubscriptionOffers) obj).hasFreeTrial));
    }

    public int hashCode() {
        return hash(Boolean.valueOf(this.hasFreeTrial));
    }

    private int hash(Object... objArr) {
        int length = objArr.length;
        int iHashCode = 1;
        for (int i = 0; i < length; i++) {
            Object obj = objArr[i];
            iHashCode = (iHashCode * 31) + (obj == null ? 0 : obj.hashCode());
        }
        return iHashCode;
    }

    private boolean equals(Object obj, Object obj2) {
        if (obj == null && obj2 == null) {
            return true;
        }
        if (obj == null || obj2 == null) {
            return false;
        }
        return obj.equals(obj2);
    }
}
