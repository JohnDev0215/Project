package com.android.car.audio;

import android.util.SparseArray;
import java.util.HashSet;
import java.util.Set;

/* JADX INFO: loaded from: classes3.dex */
class CarAudioZonesValidator {
    CarAudioZonesValidator() {
    }

    static void validate(SparseArray<CarAudioZone> carAudioZones) {
        validateAtLeastOneZoneDefined(carAudioZones);
        validateVolumeGroupsForEachZone(carAudioZones);
        validateEachAddressAppearsAtMostOnce(carAudioZones);
    }

    private static void validateAtLeastOneZoneDefined(SparseArray<CarAudioZone> carAudioZones) {
        if (carAudioZones.size() == 0) {
            throw new RuntimeException("At least one zone should be defined");
        }
    }

    private static void validateVolumeGroupsForEachZone(SparseArray<CarAudioZone> carAudioZones) {
        for (int i = 0; i < carAudioZones.size(); i++) {
            CarAudioZone zone = carAudioZones.valueAt(i);
            if (!zone.validateVolumeGroups()) {
                throw new RuntimeException("Invalid volume groups configuration for zone " + zone.getId());
            }
        }
    }

    private static void validateEachAddressAppearsAtMostOnce(SparseArray<CarAudioZone> carAudioZones) {
        Set<String> addresses = new HashSet<>();
        for (int i = 0; i < carAudioZones.size(); i++) {
            CarAudioZone zone = carAudioZones.valueAt(i);
            for (CarVolumeGroup carVolumeGroup : zone.getVolumeGroups()) {
                for (String address : carVolumeGroup.getAddresses()) {
                    if (!addresses.add(address)) {
                        throw new RuntimeException("Device with address " + address + " appears in multiple volume groups or audio zones");
                    }
                }
            }
        }
    }
}
