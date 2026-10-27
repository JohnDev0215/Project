package com.ts.car.dcm.wirelesscontroller;

import android.os.Looper;
import android.util.Log;
import com.ts.car.dcm.common.data.LinkDevice;
import com.ts.car.dcm.utils.statemachine.StateMachine;

/* JADX INFO: loaded from: classes.dex */
public abstract class WirelessConnection extends StateMachine {
    public static final int DCC_ACCEPT_CONNECT = 11;
    public static final int DCC_SET_PLATFORM_CAPABILITY = 10;
    public static final int MONITOR_DETECTION_TIME = 20000;
    public static final int MONITOR_DETECTION_TIMEOUT = 13;
    public static final int MONITOR_DEVICE_DETECTED = 12;
    private static final String TAG = WirelessConnection.class.getSimpleName();
    public boolean mDeviceApproved;
    public int mDeviceRealCapability;
    public int mDeviceType;
    public int mLastReportCapability;
    public LinkDevice mLinkDevice;
    public int mPlatformCapability;

    public abstract void notifyWirelessDeviceAttach();

    public abstract void notifyWirelessDeviceChanged(int i);

    public abstract void notifyWirelessDeviceDetach();

    public WirelessConnection(String name, Looper looper) {
        super(name, looper);
        this.mDeviceApproved = false;
        this.mDeviceType = 0;
    }

    public int getDeviceType() {
        return this.mDeviceType;
    }

    public void setDeviceType(int type) {
        this.mDeviceType = type;
    }

    public void updateLinkDeviceCapability(boolean addCapability, int capability) {
        int decisionCapability;
        LinkDevice linkDevice = this.mLinkDevice;
        if (linkDevice != null) {
            if (addCapability) {
                this.mDeviceRealCapability = linkDevice.getCapability() | capability;
            } else {
                this.mDeviceRealCapability = linkDevice.getCapability() & (~capability);
            }
            if ((this.mPlatformCapability & capability) > 0) {
                if (addCapability) {
                    decisionCapability = this.mLinkDevice.getCapability() | capability;
                } else {
                    decisionCapability = this.mLinkDevice.getCapability() & (~capability);
                }
                this.mLinkDevice.setCapability(decisionCapability);
            }
            Log.d(TAG, "updateLinkDeviceCapability, real capability:" + this.mDeviceRealCapability + ", capability:" + this.mLinkDevice.getCapability());
        }
    }

    public void processApproveConnect() {
        this.mDeviceApproved = true;
        notifyWirelessDeviceChanged(3);
    }
}
