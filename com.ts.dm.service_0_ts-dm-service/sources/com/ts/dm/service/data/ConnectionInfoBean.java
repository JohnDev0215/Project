package com.ts.dm.service.data;

import com.ts.car.sdk.dm.data.ConnectRequest;
import com.ts.car.sdk.dm.data.DmDevice;
import java.io.Serializable;

/* JADX INFO: loaded from: classes.dex */
public class ConnectionInfoBean implements Serializable {
    public static final String TAG = "ConnectionInfoBean";
    private ConnectRequest connectRequest;
    private boolean isIgnoreCheckSameDevice;
    private DmDevice onlineDevice;
    private int viewType;

    public ConnectionInfoBean(Builder builder) {
        this.connectRequest = builder.connectRequest;
    }

    public static class Builder {
        private ConnectRequest connectRequest;

        public Builder setConnectRequest(ConnectRequest request) {
            this.connectRequest = request;
            return this;
        }

        public ConnectionInfoBean build() {
            return new ConnectionInfoBean(this);
        }
    }

    public void setConnectRequest(ConnectRequest connectRequest) {
        this.connectRequest = connectRequest;
    }

    public void setViewType(int viewType) {
        this.viewType = viewType;
    }

    public ConnectRequest getConnectRequest() {
        return this.connectRequest;
    }

    public DmDevice getOnlineDevice() {
        return this.onlineDevice;
    }

    public int getViewType() {
        return this.viewType;
    }

    public boolean isIgnoreCheckSameDevice() {
        return this.isIgnoreCheckSameDevice;
    }

    public void setIgnoreCheckSameDevice(boolean ignoreCheckSameDevice) {
        this.isIgnoreCheckSameDevice = ignoreCheckSameDevice;
    }

    public void setOnlineDevice(DmDevice onlineDevice) {
        this.onlineDevice = onlineDevice;
    }

    public ConnectionInfoBean copy() {
        ConnectionInfoBean connectionInfoBean = new Builder().setConnectRequest(this.connectRequest).build();
        connectionInfoBean.setOnlineDevice(this.onlineDevice);
        connectionInfoBean.setIgnoreCheckSameDevice(this.isIgnoreCheckSameDevice);
        connectionInfoBean.setOnlineDevice(this.onlineDevice);
        return connectionInfoBean;
    }

    public boolean isSameDevice(DmDevice compareDevices) {
        if (isIgnoreCheckSameDevice()) {
            return true;
        }
        return this.connectRequest.getDevice().equals(compareDevices);
    }

    public String toString() {
        return "ConnectionInfoBean{onlineDevice=" + this.onlineDevice + ", connectRequest=" + this.connectRequest + ", viewType=" + this.viewType + ", isIgnoreCheckSameDevice=" + this.isIgnoreCheckSameDevice + '}';
    }
}
