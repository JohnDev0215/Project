package com.android.car.vms;

import android.car.vms.IVmsClientCallback;
import android.car.vms.VmsAssociatedLayer;
import android.car.vms.VmsLayer;
import android.car.vms.VmsLayerDependency;
import android.car.vms.VmsLayersOffering;
import android.os.IBinder;
import android.util.ArraySet;
import android.util.SparseArray;
import android.util.SparseBooleanArray;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/* JADX INFO: loaded from: classes3.dex */
final class VmsClientInfo {
    private final IVmsClientCallback mCallback;
    private final IBinder.DeathRecipient mDeathRecipient;
    private final boolean mLegacyClient;
    private boolean mMonitoringEnabled;
    private final String mPackageName;
    private final int mUid;
    private final Object mLock = new Object();
    private final SparseBooleanArray mProviderIds = new SparseBooleanArray();
    private final SparseArray<Set<VmsLayerDependency>> mOfferings = new SparseArray<>();
    private final SparseArray<Set<VmsLayer>> mPotentialOfferings = new SparseArray<>();
    private Set<VmsLayer> mLayerSubscriptions = Collections.emptySet();
    private Map<VmsLayer, Set<Integer>> mLayerAndProviderSubscriptions = Collections.emptyMap();

    VmsClientInfo(int uid, String packageName, IVmsClientCallback callback, boolean legacyClient, IBinder.DeathRecipient deathRecipient) {
        this.mUid = uid;
        this.mPackageName = packageName;
        this.mCallback = callback;
        this.mLegacyClient = legacyClient;
        this.mDeathRecipient = deathRecipient;
    }

    int getUid() {
        return this.mUid;
    }

    String getPackageName() {
        return this.mPackageName;
    }

    IVmsClientCallback getCallback() {
        return this.mCallback;
    }

    boolean isLegacyClient() {
        return this.mLegacyClient;
    }

    IBinder.DeathRecipient getDeathRecipient() {
        return this.mDeathRecipient;
    }

    void addProviderId(int providerId) {
        synchronized (this.mLock) {
            this.mProviderIds.put(providerId, true);
        }
    }

    boolean hasProviderId(int providerId) {
        boolean z;
        synchronized (this.mLock) {
            z = this.mProviderIds.get(providerId);
        }
        return z;
    }

    boolean setProviderOfferings(int providerId, Collection<VmsLayerDependency> offerings) {
        synchronized (this.mLock) {
            Set<VmsLayerDependency> providerOfferings = this.mOfferings.get(providerId);
            if (providerOfferings != null && providerOfferings.size() == offerings.size() && providerOfferings.containsAll(offerings)) {
                return false;
            }
            this.mOfferings.put(providerId, new ArraySet(offerings));
            this.mPotentialOfferings.put(providerId, (Set) offerings.stream().map(new Function() { // from class: com.android.car.vms.-$$Lambda$XopV3trArTgbRNlskBjvWb0Vacw
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return ((VmsLayerDependency) obj).getLayer();
                }
            }).collect(Collectors.toSet()));
            return true;
        }
    }

    Collection<VmsLayersOffering> getAllOfferings() {
        List<VmsLayersOffering> result = new ArrayList<>(this.mOfferings.size());
        synchronized (this.mLock) {
            for (int i = 0; i < this.mOfferings.size(); i++) {
                int providerId = this.mOfferings.keyAt(i);
                Set<VmsLayerDependency> providerOfferings = this.mOfferings.valueAt(i);
                result.add(new VmsLayersOffering(new ArraySet(providerOfferings), providerId));
            }
        }
        return result;
    }

    boolean hasOffering(int providerId, VmsLayer layer) {
        boolean zContains;
        synchronized (this.mLock) {
            zContains = this.mPotentialOfferings.get(providerId, Collections.emptySet()).contains(layer);
        }
        return zContains;
    }

    void setSubscriptions(List<VmsAssociatedLayer> layers) {
        synchronized (this.mLock) {
            this.mLayerSubscriptions = (Set) layers.stream().filter(new Predicate() { // from class: com.android.car.vms.-$$Lambda$VmsClientInfo$xChzDBXxWoQRCJ793Mc6ORBz9P4
                @Override // java.util.function.Predicate
                public final boolean test(Object obj) {
                    return ((VmsAssociatedLayer) obj).getProviderIds().isEmpty();
                }
            }).map(new Function() { // from class: com.android.car.vms.-$$Lambda$r6eJQPsViThumvRn1GeTuhCaRh8
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return ((VmsAssociatedLayer) obj).getVmsLayer();
                }
            }).collect(Collectors.toSet());
            this.mLayerAndProviderSubscriptions = (Map) layers.stream().filter(new Predicate() { // from class: com.android.car.vms.-$$Lambda$VmsClientInfo$jlJ3e5VMPhkPGEmS97okbQ2pH9w
                @Override // java.util.function.Predicate
                public final boolean test(Object obj) {
                    return VmsClientInfo.lambda$setSubscriptions$1((VmsAssociatedLayer) obj);
                }
            }).collect(Collectors.toMap(new Function() { // from class: com.android.car.vms.-$$Lambda$r6eJQPsViThumvRn1GeTuhCaRh8
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return ((VmsAssociatedLayer) obj).getVmsLayer();
                }
            }, new Function() { // from class: com.android.car.vms.-$$Lambda$VmsClientInfo$Oh-axaEPyG8MdKKTxcn9SnaC2k0
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return VmsClientInfo.lambda$setSubscriptions$2((VmsAssociatedLayer) obj);
                }
            }));
        }
    }

    static /* synthetic */ boolean lambda$setSubscriptions$1(VmsAssociatedLayer associatedLayer) {
        return !associatedLayer.getProviderIds().isEmpty();
    }

    static /* synthetic */ Set lambda$setSubscriptions$2(VmsAssociatedLayer associatedLayer) {
        return new ArraySet(associatedLayer.getProviderIds());
    }

    Set<VmsLayer> getLayerSubscriptions() {
        ArraySet arraySet;
        synchronized (this.mLock) {
            arraySet = new ArraySet(this.mLayerSubscriptions);
        }
        return arraySet;
    }

    Map<VmsLayer, Set<Integer>> getLayerAndProviderSubscriptions() {
        Map<VmsLayer, Set<Integer>> mapDeepCopy;
        synchronized (this.mLock) {
            mapDeepCopy = deepCopy(this.mLayerAndProviderSubscriptions);
        }
        return mapDeepCopy;
    }

    void setMonitoringEnabled(boolean enabled) {
        synchronized (this.mLock) {
            this.mMonitoringEnabled = enabled;
        }
    }

    boolean isSubscribed(int providerId, VmsLayer layer) {
        boolean z;
        synchronized (this.mLock) {
            z = this.mMonitoringEnabled || this.mLayerSubscriptions.contains(layer) || this.mLayerAndProviderSubscriptions.getOrDefault(layer, Collections.emptySet()).contains(Integer.valueOf(providerId));
        }
        return z;
    }

    void dump(PrintWriter writer, String indent) {
        synchronized (this.mLock) {
            writer.println(indent + "VmsClient [" + this.mPackageName + "]");
            StringBuilder sb = new StringBuilder();
            sb.append(indent);
            sb.append("  ");
            String prefix = sb.toString();
            writer.println(prefix + "UID: " + this.mUid);
            writer.println(prefix + "Legacy Client: " + this.mLegacyClient);
            writer.println(prefix + "Monitoring: " + this.mMonitoringEnabled);
            if (this.mProviderIds.size() > 0) {
                writer.println(prefix + "Offerings:");
                for (int i = 0; i < this.mProviderIds.size(); i++) {
                    int providerId = this.mProviderIds.keyAt(i);
                    writer.println((indent + "    ") + "Provider [" + providerId + "]");
                    for (VmsLayerDependency layerOffering : this.mOfferings.get(providerId, Collections.emptySet())) {
                        writer.println((indent + "      ") + layerOffering.getLayer());
                        if (!layerOffering.getDependencies().isEmpty()) {
                            writer.println((indent + "        ") + "Dependencies: " + layerOffering.getDependencies());
                        }
                    }
                }
            }
            if (!this.mLayerSubscriptions.isEmpty() || !this.mLayerAndProviderSubscriptions.isEmpty()) {
                writer.println((indent + "  ") + "Subscriptions:");
                String prefix2 = indent + "    ";
                for (VmsLayer layer : this.mLayerSubscriptions) {
                    writer.println(prefix2 + layer);
                }
                for (Map.Entry<VmsLayer, Set<Integer>> layerEntry : this.mLayerAndProviderSubscriptions.entrySet()) {
                    writer.println(prefix2 + layerEntry.getKey() + ": " + layerEntry.getValue());
                }
            }
        }
    }

    private static <K, V> Map<K, Set<V>> deepCopy(Map<K, Set<V>> original) {
        return (Map) original.entrySet().stream().collect(Collectors.toMap(new Function() { // from class: com.android.car.vms.-$$Lambda$hFUlwGe7_RidLzlAlfUmdwML3h4
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return ((Map.Entry) obj).getKey();
            }
        }, new Function() { // from class: com.android.car.vms.-$$Lambda$VmsClientInfo$3HyGJBpvF7q0FfBhrSkwZdUPvC8
            @Override // java.util.function.Function
            public final Object apply(Object obj) {
                return VmsClientInfo.lambda$deepCopy$3((Map.Entry) obj);
            }
        }));
    }

    static /* synthetic */ Set lambda$deepCopy$3(Map.Entry entry) {
        return new ArraySet((Collection) entry.getValue());
    }
}
