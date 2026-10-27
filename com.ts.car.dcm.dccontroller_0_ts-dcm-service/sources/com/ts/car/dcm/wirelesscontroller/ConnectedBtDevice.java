package com.ts.car.dcm.wirelesscontroller;

/* JADX INFO: loaded from: classes.dex */
public class ConnectedBtDevice {
    private String address;
    private boolean isA2dpSinkConnected = false;
    private boolean isHfpClientConnected = false;
    private boolean isOtherProfileConnected = false;

    public String getAddress() {
        return this.address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public boolean isA2dpSinkConnected() {
        return this.isA2dpSinkConnected;
    }

    public void setA2dpSinkConnected(boolean a2dpSinkConnected) {
        this.isA2dpSinkConnected = a2dpSinkConnected;
    }

    public boolean isHfpClientConnected() {
        return this.isHfpClientConnected;
    }

    public void setHfpClientConnected(boolean hfpClientConnected) {
        this.isHfpClientConnected = hfpClientConnected;
    }

    public boolean isOtherProfileConnected() {
        return this.isOtherProfileConnected;
    }

    public void setOtherProfileConnected(boolean otherProfileConnected) {
        this.isOtherProfileConnected = otherProfileConnected;
    }
}
