package com.bugsnag.android;

import com.bugsnag.android.StateEvent;
import com.bugsnag.android.internal.StateObserver;
import java.util.Iterator;
import java.util.List;
import kotlin.jvm.internal.DefaultConstructorMarker;

/* JADX INFO: compiled from: FeatureFlagState.kt */
/* JADX INFO: loaded from: classes5.dex */
@kotlin.Metadata(bv = {}, d1 = {"\u0000>\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\b\u0080\b\u0018\u00002\u00020\u00012\u00020\u0002B\u0011\u0012\b\b\u0002\u0010\u0014\u001a\u00020\u0013¢\u0006\u0004\b\u0018\u0010\u0019J\u001a\u0010\u0007\u001a\u00020\u00062\u0006\u0010\u0004\u001a\u00020\u00032\b\u0010\u0005\u001a\u0004\u0018\u00010\u0003H\u0016J\u0006\u0010\b\u001a\u00020\u0006J\f\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\n0\tJ\u0006\u0010\f\u001a\u00020\u0000J\t\u0010\r\u001a\u00020\u0003HÖ\u0001J\t\u0010\u000f\u001a\u00020\u000eHÖ\u0001J\u0013\u0010\u0012\u001a\u00020\u00112\b\u0010\u0010\u001a\u0004\u0018\u00010\u0002HÖ\u0003R\u0017\u0010\u0014\u001a\u00020\u00138\u0006¢\u0006\f\n\u0004\b\u0014\u0010\u0015\u001a\u0004\b\u0016\u0010\u0017¨\u0006\u001a"}, d2 = {"Lcom/bugsnag/android/FeatureFlagState;", "Lcom/bugsnag/android/BaseObservable;", "", "", "name", "variant", "", "addFeatureFlag", "emitObservableEvent", "", "Lcom/bugsnag/android/FeatureFlag;", "toList", "copy", "toString", "", "hashCode", "other", "", "equals", "Lcom/bugsnag/android/FeatureFlags;", "featureFlags", "Lcom/bugsnag/android/FeatureFlags;", "getFeatureFlags", "()Lcom/bugsnag/android/FeatureFlags;", "<init>", "(Lcom/bugsnag/android/FeatureFlags;)V", "bugsnag-android-core_release"}, k = 1, mv = {1, 4, 2})
public final /* data */ class FeatureFlagState extends BaseObservable {
    private final FeatureFlags featureFlags;

    public FeatureFlagState() {
        this(null, 1, 0 == true ? 1 : 0);
    }

    public boolean equals(Object other) {
        if (this != other) {
            return (other instanceof FeatureFlagState) && kotlin.jvm.internal.Intrinsics.areEqual(this.featureFlags, ((FeatureFlagState) other).featureFlags);
        }
        return true;
    }

    public int hashCode() {
        FeatureFlags featureFlags = this.featureFlags;
        if (featureFlags != null) {
            return featureFlags.hashCode();
        }
        return 0;
    }

    public String toString() {
        return "FeatureFlagState(featureFlags=" + this.featureFlags + ")";
    }

    public /* synthetic */ FeatureFlagState(FeatureFlags featureFlags, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this((i & 1) != 0 ? new FeatureFlags(null, 1, null) : featureFlags);
    }

    public final FeatureFlags getFeatureFlags() {
        return this.featureFlags;
    }

    public FeatureFlagState(FeatureFlags featureFlags) {
        this.featureFlags = featureFlags;
    }

    public void addFeatureFlag(String name, String variant) {
        this.featureFlags.addFeatureFlag(name, variant);
        if (getObservers$bugsnag_android_core_release().isEmpty()) {
            return;
        }
        StateEvent.AddFeatureFlag addFeatureFlag = new StateEvent.AddFeatureFlag(name, variant);
        Iterator<T> it = getObservers$bugsnag_android_core_release().iterator();
        while (it.hasNext()) {
            ((StateObserver) it.next()).onStateChange(addFeatureFlag);
        }
    }

    public final void emitObservableEvent() {
        for (FeatureFlag featureFlag : toList()) {
            String name = featureFlag.getKey();
            String value = featureFlag.getValue();
            if (!getObservers$bugsnag_android_core_release().isEmpty()) {
                kotlin.jvm.internal.Intrinsics.checkExpressionValueIsNotNull(name, "name");
                StateEvent.AddFeatureFlag addFeatureFlag = new StateEvent.AddFeatureFlag(name, value);
                Iterator<T> it = getObservers$bugsnag_android_core_release().iterator();
                while (it.hasNext()) {
                    ((StateObserver) it.next()).onStateChange(addFeatureFlag);
                }
            }
        }
    }

    public final List<FeatureFlag> toList() {
        return this.featureFlags.toList();
    }

    public final FeatureFlagState copy() {
        return new FeatureFlagState(this.featureFlags.copy());
    }
}
