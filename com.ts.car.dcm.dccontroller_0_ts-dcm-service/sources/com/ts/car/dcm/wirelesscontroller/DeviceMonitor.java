package com.ts.car.dcm.wirelesscontroller;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import com.ts.car.dcm.common.data.LinkDevice;

/* JADX INFO: loaded from: classes.dex */
public abstract class DeviceMonitor extends Handler {
    public abstract WirelessDeviceDetection getWirelessDeviceDetection();

    public abstract void init(Context context, int i);

    public abstract boolean isMonitorReady();

    public abstract void processAcceptDevice(Bundle bundle);

    public abstract void processMonitorStart();

    public abstract void processMonitorStop();

    public abstract void processSetCapability(int i);

    public abstract void processWirelessDeviceAttach(LinkDevice linkDevice);

    public abstract void processWirelessDeviceChange(LinkDevice linkDevice, int i);

    public abstract void processWirelessDeviceDetach(LinkDevice linkDevice);

    public DeviceMonitor(Looper looper) {
        super(looper);
    }

    @Override // android.os.Handler
    public void handleMessage(Message msg) {
        super.handleMessage(msg);
        int i = msg.what;
        if (i == 101) {
            processMonitorStart();
        }
        if (i == 102) {
            processMonitorStop();
            return;
        }
        if (i == 200) {
            int capability = msg.arg1;
            processSetCapability(capability);
            return;
        }
        if (i == 201) {
            Bundle data = msg.getData();
            processAcceptDevice(data);
            return;
        }
        switch (i) {
            case WirelessMonitorConstants.EVENT_WIRELESS_DEVICE_ATTACH /* 400 */:
                LinkDevice linkDevice = (LinkDevice) msg.obj;
                processWirelessDeviceAttach(linkDevice);
                break;
            case WirelessMonitorConstants.EVENT_WIRELESS_DEVICE_CHANGE /* 401 */:
                int reason = msg.arg1;
                LinkDevice linkDevice2 = (LinkDevice) msg.obj;
                processWirelessDeviceChange(linkDevice2, reason);
                break;
            case WirelessMonitorConstants.EVENT_WIRELESS_DEVICE_DETACH /* 402 */:
                LinkDevice linkDevice3 = (LinkDevice) msg.obj;
                processWirelessDeviceDetach(linkDevice3);
                break;
        }
    }
}
