package com.byd.dipilot.model;

/* JADX INFO: loaded from: classes2.dex */
public class ActiveSafeStateModel extends ActiveSafeModel {
    private byte subState;

    public ActiveSafeStateModel(boolean z, boolean z2, int i, int i2, byte b2, byte b3) {
        super(z, z2, i, i2, b2);
        this.subState = b3;
    }

    public byte getSubState() {
        return this.subState;
    }

    public void setSubState(byte b2) {
        this.subState = b2;
    }

    public void updateState(byte b2, byte b3, int i) {
        this.pos = b2;
        this.subState = b3;
        this.assistPicId = i;
    }
}
