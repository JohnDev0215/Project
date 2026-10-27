package com.byd.dipilot.model;

import androidx.databinding.Bindable;
import androidx.databinding.library.baseAdapters.BR;

/* JADX INFO: loaded from: classes2.dex */
public class DriverAssistModel extends BaseViewModel {
    public int assistPicId;
    public byte pos;

    public DriverAssistModel() {
    }

    public int getAssistPicId() {
        return this.assistPicId;
    }

    @Bindable
    public int getTag() {
        return this.tag;
    }

    public boolean isDefaultChecked(int i) {
        if (1 == i || 2 == i) {
            return true;
        }
        if (i == 0) {
            return false;
        }
        return this.assistState;
    }

    public void setAssistPicId(int i) {
        this.assistPicId = i;
    }

    public void setBtnEnable(boolean z, boolean z2) {
        setAssistState(z);
        setEnable(z2);
    }

    public void setTag(int i) {
        this.tag = i;
        notifyPropertyChanged(BR.tag);
    }

    public void updateTagSubCheck(boolean z, int i, int i2) {
        setAssistState(z);
        this.tag = i;
        this.assistPicId = i2;
    }

    public void updateTagSubState(int i, int i2) {
        this.tag = i;
        this.assistPicId = i2;
    }

    public DriverAssistModel(boolean z, boolean z2, int i, int i2) {
        this.assistState = z;
        this.enable = z2;
        this.tag = i;
        this.assistPicId = i2;
    }

    public DriverAssistModel(boolean z, boolean z2, int i, int i2, byte b2) {
        this.assistState = z;
        this.enable = z2;
        this.tag = i;
        this.assistPicId = i2;
        this.pos = b2;
    }

    public DriverAssistModel(int i) {
        this.tag = i;
    }

    public DriverAssistModel(int i, int i2) {
        this.assistState = isDefaultChecked(i);
        this.enable = i != 3;
        this.tag = i2;
        this.pos = (byte) i;
    }
}
