package com.vivid.carcontroller.byd.dx;

import android.app.Application;
import android.hardware.bydauto.instrument.BYDAutoInstrumentDevice;
import java.util.HashSet;
import java.util.Set;

/* JADX INFO: compiled from: BYDDXClusterManager.java */
/* JADX INFO: loaded from: classes.dex */
public class a extends l3.b {
    public a(Application application, i3.m mVar) {
        super(application, mVar);
    }

    @Override // l3.b
    protected void f() {
    }

    @Override // l3.b
    protected void g() {
    }

    @Override // l3.b
    protected void h() {
    }

    @Override // l3.b
    protected void i(int i7) {
    }

    @Override // l3.b
    protected void j() {
    }

    @Override // l3.b
    protected void k() {
    }

    public Set<String> n() {
        HashSet hashSet = new HashSet();
        hashSet.add(BYDAutoInstrumentDevice.PERMISSION_BYDAUTO_INSTRUMENT_COMMON);
        return hashSet;
    }

    public boolean o() {
        return true;
    }
}
