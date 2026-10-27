package com.byd.data.common.h.b;

import a.a.c;
import android.hardware.bydauto.BYDAutoEventValue;
import android.hardware.bydauto.vehiclehealth.AbsBYDAutoVehicleHealthListener;

/* JADX INFO: compiled from: CollectVehicleHealth.java */
/* JADX INFO: loaded from: classes.dex */
public class a extends AbsBYDAutoVehicleHealthListener {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private com.byd.data.common.h.a.a f3399a;

    public a(com.byd.data.common.h.a.a aVar) {
        this.f3399a = aVar;
    }

    public void a() {
        this.f3399a = null;
    }

    @Override // android.hardware.bydauto.vehiclehealth.AbsBYDAutoVehicleHealthListener
    public void onDataChanged(c cVar) {
    }

    @Override // android.hardware.bydauto.vehiclehealth.AbsBYDAutoVehicleHealthListener
    public void onDataEventChanged(int i2, BYDAutoEventValue bYDAutoEventValue) {
    }

    @Override // android.hardware.bydauto.vehiclehealth.AbsBYDAutoVehicleHealthListener
    public void onError(int i2, String str) {
    }

    @Override // android.hardware.bydauto.vehiclehealth.AbsBYDAutoVehicleHealthListener
    public void onTransportModeChanged(int i2) {
        com.byd.data.common.h.a.a aVar = this.f3399a;
        if (aVar != null) {
            aVar.a(i2);
        }
    }
}
