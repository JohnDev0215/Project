package com.bugsnag.android;

import com.bugsnag.android.StateEvent;
import com.bugsnag.android.internal.StateObserver;
import java.util.Iterator;

/* JADX INFO: compiled from: MemoryTrimState.kt */
/* JADX INFO: loaded from: classes5.dex */
@kotlin.Metadata(bv = {}, d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0011\b\u0000\u0018\u00002\u00020\u0001B\u0007¢\u0006\u0004\b\u001a\u0010\u001bJ\u0019\u0010\u0005\u001a\u00020\u00042\b\u0010\u0003\u001a\u0004\u0018\u00010\u0002H\u0002¢\u0006\u0004\b\u0005\u0010\u0006J\u0017\u0010\t\u001a\u00020\b2\b\u0010\u0007\u001a\u0004\u0018\u00010\u0002¢\u0006\u0004\b\t\u0010\nJ\u0006\u0010\f\u001a\u00020\u000bR\"\u0010\r\u001a\u00020\b8\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\b\r\u0010\u000e\u001a\u0004\b\r\u0010\u000f\"\u0004\b\u0010\u0010\u0011R$\u0010\u0003\u001a\u0004\u0018\u00010\u00028\u0006@\u0006X\u0086\u000e¢\u0006\u0012\n\u0004\b\u0003\u0010\u0012\u001a\u0004\b\u0013\u0010\u0014\"\u0004\b\u0015\u0010\u0016R\u0011\u0010\u0019\u001a\u00020\u00048F¢\u0006\u0006\u001a\u0004\b\u0017\u0010\u0018¨\u0006\u001c"}, d2 = {"Lcom/bugsnag/android/MemoryTrimState;", "Lcom/bugsnag/android/BaseObservable;", "", "memoryTrimLevel", "", "descriptionFor", "(Ljava/lang/Integer;)Ljava/lang/String;", "newTrimLevel", "", "updateMemoryTrimLevel", "(Ljava/lang/Integer;)Z", "", "emitObservableEvent", "isLowMemory", "Z", "()Z", "setLowMemory", "(Z)V", "Ljava/lang/Integer;", "getMemoryTrimLevel", "()Ljava/lang/Integer;", "setMemoryTrimLevel", "(Ljava/lang/Integer;)V", "getTrimLevelDescription", "()Ljava/lang/String;", "trimLevelDescription", "<init>", "()V", "bugsnag-android-core_release"}, k = 1, mv = {1, 4, 2})
public final class MemoryTrimState extends BaseObservable {
    private boolean isLowMemory;
    private Integer memoryTrimLevel;

    /* JADX INFO: renamed from: isLowMemory, reason: from getter */
    public final boolean getIsLowMemory() {
        return this.isLowMemory;
    }

    public final void setLowMemory(boolean z) {
        this.isLowMemory = z;
    }

    public final String getTrimLevelDescription() {
        return descriptionFor(this.memoryTrimLevel);
    }

    public final boolean updateMemoryTrimLevel(Integer newTrimLevel) {
        if (kotlin.jvm.internal.Intrinsics.areEqual(this.memoryTrimLevel, newTrimLevel)) {
            return false;
        }
        this.memoryTrimLevel = newTrimLevel;
        return true;
    }

    public final void emitObservableEvent() {
        if (getObservers$bugsnag_android_core_release().isEmpty()) {
            return;
        }
        StateEvent.UpdateMemoryTrimEvent updateMemoryTrimEvent = new StateEvent.UpdateMemoryTrimEvent(this.isLowMemory, this.memoryTrimLevel, getTrimLevelDescription());
        Iterator<T> it = getObservers$bugsnag_android_core_release().iterator();
        while (it.hasNext()) {
            ((StateObserver) it.next()).onStateChange(updateMemoryTrimEvent);
        }
    }

    private final String descriptionFor(Integer memoryTrimLevel) {
        if (memoryTrimLevel == null) {
            return "None";
        }
        if (memoryTrimLevel.intValue() == 80) {
            return "Complete";
        }
        if (memoryTrimLevel.intValue() == 60) {
            return "Moderate";
        }
        if (memoryTrimLevel.intValue() == 40) {
            return "Background";
        }
        if (memoryTrimLevel.intValue() == 20) {
            return "UI hidden";
        }
        if (memoryTrimLevel.intValue() == 15) {
            return "Running critical";
        }
        if (memoryTrimLevel.intValue() == 10) {
            return "Running low";
        }
        if (memoryTrimLevel.intValue() == 5) {
            return "Running moderate";
        }
        return "Unknown (" + memoryTrimLevel + ')';
    }
}
