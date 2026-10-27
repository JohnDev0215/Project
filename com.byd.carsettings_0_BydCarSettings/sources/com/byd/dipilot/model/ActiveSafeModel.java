package com.byd.dipilot.model;

import androidx.databinding.Bindable;
import androidx.databinding.library.baseAdapters.BR;

/* JADX INFO: loaded from: classes2.dex */
public class ActiveSafeModel extends DriverAssistModel {
    public ActiveSafeModel(boolean z, boolean z2, int i, int i2, byte b2) {
        super(z, z2, i, i2, b2);
    }

    @Bindable
    public byte getPos() {
        return this.pos;
    }

    public byte getState() {
        return this.pos;
    }

    public void setPos(byte b2) {
        this.pos = b2;
        notifyPropertyChanged(BR.pos);
    }

    public ActiveSafeModel(boolean z, boolean z2, int i, byte b2) {
        super(z, z2, i, 0, b2);
    }

    public ActiveSafeModel(int i, int i2, byte b2) {
        super(true, true, i, i2, b2);
    }
}
