package com.ts.androidauto.aap.common;

import com.ts.androidauto.fsdk.data.AndroidAutoDevice;

/* JADX INFO: loaded from: classes.dex */
public class ConnectionInfo {
    private AndroidAutoDevice androidAutoDevice;
    private boolean forceProjection;
    private boolean hasAvailableCapability;
    private boolean isFirstConnect;
    private int linkType;

    public AndroidAutoDevice getAndroidAutoDevice() {
        return this.androidAutoDevice;
    }

    public void setAndroidAutoDevice(AndroidAutoDevice androidAutoDevice) {
        this.androidAutoDevice = androidAutoDevice;
    }

    public int getLinkType() {
        return this.linkType;
    }

    public void setLinkType(int linkType) {
        this.linkType = linkType;
    }

    public boolean isHasAvailableCapability() {
        return this.hasAvailableCapability;
    }

    public void setHasAvailableCapability(boolean hasAvailableCapability) {
        this.hasAvailableCapability = hasAvailableCapability;
    }

    public boolean isFirstConnect() {
        return this.isFirstConnect;
    }

    public void setFirstConnect(boolean firstConnect) {
        this.isFirstConnect = firstConnect;
    }

    public boolean isForceProjection() {
        return this.forceProjection;
    }

    public void setForceProjection(boolean forceProjection) {
        this.forceProjection = forceProjection;
    }

    public String toString() {
        StringBuffer sb = new StringBuffer();
        sb.append("androidAutoDevice=");
        sb.append(this.androidAutoDevice.toString());
        sb.append(" linkType=");
        sb.append(this.linkType);
        sb.append(" hasAvailableCapability=");
        sb.append(this.hasAvailableCapability);
        sb.append(" isFirstConnect=");
        sb.append(this.isFirstConnect);
        sb.append(" forceProjection=");
        sb.append(this.forceProjection);
        return sb.toString();
    }
}
