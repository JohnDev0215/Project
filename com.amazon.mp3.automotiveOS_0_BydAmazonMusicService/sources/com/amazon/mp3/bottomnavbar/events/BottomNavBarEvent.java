package com.amazon.mp3.bottomnavbar.events;

import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: BottomNavBarEvent.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(bv = {}, d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\b\u0086\b\u0018\u00002\u00020\u0001B\u000f\u0012\u0006\u0010\n\u001a\u00020\t¢\u0006\u0004\b\u000e\u0010\u000fJ\t\u0010\u0003\u001a\u00020\u0002HÖ\u0001J\t\u0010\u0005\u001a\u00020\u0004HÖ\u0001J\u0013\u0010\b\u001a\u00020\u00072\b\u0010\u0006\u001a\u0004\u0018\u00010\u0001HÖ\u0003R\u0017\u0010\n\u001a\u00020\t8\u0006¢\u0006\f\n\u0004\b\n\u0010\u000b\u001a\u0004\b\f\u0010\r¨\u0006\u0010"}, d2 = {"Lcom/amazon/mp3/bottomnavbar/events/BottomNavBarEvent;", "", "", "toString", "", "hashCode", "other", "", "equals", "Lcom/amazon/mp3/bottomnavbar/events/BottomNavBarEventType;", "bottomNavBarEventType", "Lcom/amazon/mp3/bottomnavbar/events/BottomNavBarEventType;", "getBottomNavBarEventType", "()Lcom/amazon/mp3/bottomnavbar/events/BottomNavBarEventType;", "<init>", "(Lcom/amazon/mp3/bottomnavbar/events/BottomNavBarEventType;)V", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0})
public final /* data */ class BottomNavBarEvent {
    private final BottomNavBarEventType bottomNavBarEventType;

    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        return (other instanceof BottomNavBarEvent) && this.bottomNavBarEventType == ((BottomNavBarEvent) other).bottomNavBarEventType;
    }

    public int hashCode() {
        return this.bottomNavBarEventType.hashCode();
    }

    public String toString() {
        return "BottomNavBarEvent(bottomNavBarEventType=" + this.bottomNavBarEventType + ')';
    }

    public BottomNavBarEvent(BottomNavBarEventType bottomNavBarEventType) {
        Intrinsics.checkNotNullParameter(bottomNavBarEventType, "bottomNavBarEventType");
        this.bottomNavBarEventType = bottomNavBarEventType;
    }

    public final BottomNavBarEventType getBottomNavBarEventType() {
        return this.bottomNavBarEventType;
    }
}
