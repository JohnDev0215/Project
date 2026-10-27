package com.bugsnag.android;

import com.bugsnag.android.StateEvent;
import com.bugsnag.android.internal.StateObserver;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import kotlin.jvm.internal.DefaultConstructorMarker;

/* JADX INFO: compiled from: MetadataState.kt */
/* JADX INFO: loaded from: classes5.dex */
@kotlin.Metadata(bv = {}, d1 = {"\u0000<\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0010$\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0002\b\u0007\b\u0080\b\u0018\u00002\u00020\u00012\u00020\u0002B\u0011\u0012\b\b\u0002\u0010\u000f\u001a\u00020\u000e¢\u0006\u0004\b\u001a\u0010\u001bJ\u001a\u0010\u0007\u001a\u00020\u00062\u0006\u0010\u0004\u001a\u00020\u00032\b\u0010\u0005\u001a\u0004\u0018\u00010\u0003H\u0002J\"\u0010\t\u001a\u00020\u00062\u0006\u0010\u0004\u001a\u00020\u00032\u0006\u0010\u0005\u001a\u00020\u00032\b\u0010\b\u001a\u0004\u0018\u00010\u0002H\u0002J&\u0010\t\u001a\u00020\u00062\u0006\u0010\u0004\u001a\u00020\u00032\u0014\u0010\b\u001a\u0010\u0012\u0004\u0012\u00020\u0003\u0012\u0006\u0012\u0004\u0018\u00010\u00020\nH\u0002J&\u0010\u000b\u001a\u00020\u00062\u0006\u0010\u0004\u001a\u00020\u00032\u0014\u0010\b\u001a\u0010\u0012\u0004\u0012\u00020\u0003\u0012\u0006\u0012\u0004\u0018\u00010\u00020\nH\u0016J\"\u0010\u000b\u001a\u00020\u00062\u0006\u0010\u0004\u001a\u00020\u00032\u0006\u0010\u0005\u001a\u00020\u00032\b\u0010\b\u001a\u0004\u0018\u00010\u0002H\u0016J\u0010\u0010\f\u001a\u00020\u00062\u0006\u0010\u0004\u001a\u00020\u0003H\u0016J\u0018\u0010\f\u001a\u00020\u00062\u0006\u0010\u0004\u001a\u00020\u00032\u0006\u0010\u0005\u001a\u00020\u0003H\u0016J\u0006\u0010\r\u001a\u00020\u0006J\u0013\u0010\u0010\u001a\u00020\u00002\b\b\u0002\u0010\u000f\u001a\u00020\u000eHÆ\u0001J\t\u0010\u0011\u001a\u00020\u0003HÖ\u0001J\t\u0010\u0013\u001a\u00020\u0012HÖ\u0001J\u0013\u0010\u0016\u001a\u00020\u00152\b\u0010\u0014\u001a\u0004\u0018\u00010\u0002HÖ\u0003R\u0017\u0010\u000f\u001a\u00020\u000e8\u0006¢\u0006\f\n\u0004\b\u000f\u0010\u0017\u001a\u0004\b\u0018\u0010\u0019¨\u0006\u001c"}, d2 = {"Lcom/bugsnag/android/MetadataState;", "Lcom/bugsnag/android/BaseObservable;", "", "", "section", "key", "", "notifyClear", "value", "notifyMetadataAdded", "", "addMetadata", "clearMetadata", "emitObservableEvent", "Lcom/bugsnag/android/Metadata;", "metadata", "copy", "toString", "", "hashCode", "other", "", "equals", "Lcom/bugsnag/android/Metadata;", "getMetadata", "()Lcom/bugsnag/android/Metadata;", "<init>", "(Lcom/bugsnag/android/Metadata;)V", "bugsnag-android-core_release"}, k = 1, mv = {1, 4, 2})
public final /* data */ class MetadataState extends BaseObservable {
    private final Metadata metadata;

    public MetadataState() {
        this(null, 1, 0 == true ? 1 : 0);
    }

    public final MetadataState copy(Metadata metadata) {
        return new MetadataState(metadata);
    }

    public boolean equals(Object other) {
        if (this != other) {
            return (other instanceof MetadataState) && kotlin.jvm.internal.Intrinsics.areEqual(this.metadata, ((MetadataState) other).metadata);
        }
        return true;
    }

    public int hashCode() {
        Metadata metadata = this.metadata;
        if (metadata != null) {
            return metadata.hashCode();
        }
        return 0;
    }

    public String toString() {
        return "MetadataState(metadata=" + this.metadata + ")";
    }

    public /* synthetic */ MetadataState(Metadata metadata, int i, DefaultConstructorMarker defaultConstructorMarker) {
        this((i & 1) != 0 ? new Metadata(null, 1, null) : metadata);
    }

    public final Metadata getMetadata() {
        return this.metadata;
    }

    public MetadataState(Metadata metadata) {
        this.metadata = metadata;
    }

    public void addMetadata(String section, Map<String, ? extends Object> value) {
        this.metadata.addMetadata(section, value);
        notifyMetadataAdded(section, value);
    }

    public void addMetadata(String section, String key, Object value) {
        this.metadata.addMetadata(section, key, value);
        notifyMetadataAdded(section, key, value);
    }

    public void clearMetadata(String section) {
        this.metadata.clearMetadata(section);
        notifyClear(section, null);
    }

    public void clearMetadata(String section, String key) {
        this.metadata.clearMetadata(section, key);
        notifyClear(section, key);
    }

    private final void notifyClear(String section, String key) {
        if (key == null) {
            if (getObservers$bugsnag_android_core_release().isEmpty()) {
                return;
            }
            StateEvent.ClearMetadataSection clearMetadataSection = new StateEvent.ClearMetadataSection(section);
            Iterator<T> it = getObservers$bugsnag_android_core_release().iterator();
            while (it.hasNext()) {
                ((StateObserver) it.next()).onStateChange(clearMetadataSection);
            }
            return;
        }
        if (getObservers$bugsnag_android_core_release().isEmpty()) {
            return;
        }
        StateEvent.ClearMetadataValue clearMetadataValue = new StateEvent.ClearMetadataValue(section, key);
        Iterator<T> it2 = getObservers$bugsnag_android_core_release().iterator();
        while (it2.hasNext()) {
            ((StateObserver) it2.next()).onStateChange(clearMetadataValue);
        }
    }

    public final void emitObservableEvent() {
        Set<Map.Entry<String, Object>> setEntrySet;
        for (String str : this.metadata.getStore$bugsnag_android_core_release().keySet()) {
            Map<String, Object> metadata = this.metadata.getMetadata(str);
            if (metadata != null && (setEntrySet = metadata.entrySet()) != null) {
                Iterator<T> it = setEntrySet.iterator();
                while (it.hasNext()) {
                    Map.Entry entry = (Map.Entry) it.next();
                    notifyMetadataAdded(str, (String) entry.getKey(), entry.getValue());
                }
            }
        }
    }

    private final void notifyMetadataAdded(String section, String key, Object value) {
        if (value != null) {
            if (getObservers$bugsnag_android_core_release().isEmpty()) {
                return;
            }
            StateEvent.AddMetadata addMetadata = new StateEvent.AddMetadata(section, key, this.metadata.getMetadata(section, key));
            Iterator<T> it = getObservers$bugsnag_android_core_release().iterator();
            while (it.hasNext()) {
                ((StateObserver) it.next()).onStateChange(addMetadata);
            }
            return;
        }
        notifyClear(section, key);
    }

    private final void notifyMetadataAdded(String section, Map<String, ? extends Object> value) {
        Iterator<T> it = value.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry entry = (Map.Entry) it.next();
            if (!getObservers$bugsnag_android_core_release().isEmpty()) {
                StateEvent.AddMetadata addMetadata = new StateEvent.AddMetadata(section, (String) entry.getKey(), this.metadata.getMetadata(section, (String) entry.getKey()));
                Iterator<T> it2 = getObservers$bugsnag_android_core_release().iterator();
                while (it2.hasNext()) {
                    ((StateObserver) it2.next()).onStateChange(addMetadata);
                }
            }
        }
    }
}
