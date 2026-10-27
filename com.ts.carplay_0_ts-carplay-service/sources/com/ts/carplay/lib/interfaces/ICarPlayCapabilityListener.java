package com.ts.carplay.lib.interfaces;

/* JADX INFO: loaded from: classes.dex */
public interface ICarPlayCapabilityListener {
    default void onWirelessCarPlayAvailabilityChanged(String macAddress, boolean enabled) {
    }
}
