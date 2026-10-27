package com.android.car.vms;

import android.car.vms.VmsAssociatedLayer;
import android.car.vms.VmsAvailableLayers;
import android.car.vms.VmsLayer;
import android.car.vms.VmsLayerDependency;
import android.car.vms.VmsLayersOffering;
import android.util.Log;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

/* JADX INFO: loaded from: classes3.dex */
class VmsLayerAvailability {
    private static final boolean DBG = false;
    private static final String TAG = VmsLayerAvailability.class.getSimpleName();
    private final Object mLock = new Object();
    private final Map<VmsLayer, Set<Set<VmsLayer>>> mPotentialLayersAndDependencies = new HashMap();
    private Set<VmsAssociatedLayer> mAvailableAssociatedLayers = Collections.emptySet();
    private Map<VmsLayer, Set<Integer>> mPotentialLayersAndPublishers = new HashMap();
    private int mSeq = 0;

    VmsLayerAvailability() {
    }

    void setPublishersOffering(Collection<VmsLayersOffering> publishersLayersOfferings) {
        synchronized (this.mLock) {
            reset();
            for (VmsLayersOffering offering : publishersLayersOfferings) {
                for (VmsLayerDependency dependency : offering.getDependencies()) {
                    VmsLayer layer = dependency.getLayer();
                    this.mPotentialLayersAndPublishers.computeIfAbsent(layer, new Function() { // from class: com.android.car.vms.-$$Lambda$VmsLayerAvailability$E4yo98SRjE71wPfLbvHV94kySWg
                        @Override // java.util.function.Function
                        public final Object apply(Object obj) {
                            return VmsLayerAvailability.lambda$setPublishersOffering$0((VmsLayer) obj);
                        }
                    }).add(Integer.valueOf(offering.getPublisherId()));
                    this.mPotentialLayersAndDependencies.computeIfAbsent(layer, new Function() { // from class: com.android.car.vms.-$$Lambda$VmsLayerAvailability$iw3RLt4AasiqdNLF0hBHcZiaeGs
                        @Override // java.util.function.Function
                        public final Object apply(Object obj) {
                            return VmsLayerAvailability.lambda$setPublishersOffering$1((VmsLayer) obj);
                        }
                    }).add(dependency.getDependencies());
                }
            }
            calculateLayers();
        }
    }

    static /* synthetic */ Set lambda$setPublishersOffering$0(VmsLayer k) {
        return new HashSet();
    }

    static /* synthetic */ Set lambda$setPublishersOffering$1(VmsLayer k) {
        return new HashSet();
    }

    VmsAvailableLayers getAvailableLayers() {
        VmsAvailableLayers vmsAvailableLayers;
        synchronized (this.mLock) {
            vmsAvailableLayers = new VmsAvailableLayers(this.mAvailableAssociatedLayers, this.mSeq);
        }
        return vmsAvailableLayers;
    }

    private void reset() {
        synchronized (this.mLock) {
            this.mPotentialLayersAndDependencies.clear();
            this.mPotentialLayersAndPublishers.clear();
            this.mAvailableAssociatedLayers = Collections.emptySet();
            this.mSeq++;
        }
    }

    private void calculateLayers() {
        synchronized (this.mLock) {
            Set<VmsLayer> availableLayersSet = new HashSet<>();
            Set<VmsLayer> cyclicAvoidanceAuxiliarySet = new HashSet<>();
            for (VmsLayer layer : this.mPotentialLayersAndDependencies.keySet()) {
                addLayerToAvailabilityCalculationLocked(layer, availableLayersSet, cyclicAvoidanceAuxiliarySet);
            }
            this.mAvailableAssociatedLayers = Collections.unmodifiableSet((Set) availableLayersSet.stream().map(new Function() { // from class: com.android.car.vms.-$$Lambda$VmsLayerAvailability$EsDJ57lpfiLO6v5h9Jv9Jc0ZDHk
                @Override // java.util.function.Function
                public final Object apply(Object obj) {
                    return this.f$0.lambda$calculateLayers$2$VmsLayerAvailability((VmsLayer) obj);
                }
            }).collect(Collectors.toSet()));
        }
    }

    public /* synthetic */ VmsAssociatedLayer lambda$calculateLayers$2$VmsLayerAvailability(VmsLayer l) {
        return new VmsAssociatedLayer(l, this.mPotentialLayersAndPublishers.get(l));
    }

    private void addLayerToAvailabilityCalculationLocked(VmsLayer layer, Set<VmsLayer> currentAvailableLayers, Set<VmsLayer> cyclicAvoidanceSet) {
        if (currentAvailableLayers.contains(layer) || !this.mPotentialLayersAndDependencies.containsKey(layer)) {
            return;
        }
        if (cyclicAvoidanceSet.contains(layer)) {
            Log.e(TAG, "Detected a cyclic dependency: " + cyclicAvoidanceSet + " -> " + layer);
            return;
        }
        for (Set<VmsLayer> dependencies : this.mPotentialLayersAndDependencies.get(layer)) {
            if (dependencies == null || dependencies.isEmpty()) {
                currentAvailableLayers.add(layer);
                return;
            }
            cyclicAvoidanceSet.add(layer);
            boolean isSupported = true;
            Iterator<VmsLayer> it = dependencies.iterator();
            while (true) {
                if (!it.hasNext()) {
                    break;
                }
                VmsLayer dependency = it.next();
                addLayerToAvailabilityCalculationLocked(dependency, currentAvailableLayers, cyclicAvoidanceSet);
                if (!currentAvailableLayers.contains(dependency)) {
                    isSupported = false;
                    break;
                }
            }
            cyclicAvoidanceSet.remove(layer);
            if (isSupported) {
                currentAvailableLayers.add(layer);
                return;
            }
        }
    }
}
