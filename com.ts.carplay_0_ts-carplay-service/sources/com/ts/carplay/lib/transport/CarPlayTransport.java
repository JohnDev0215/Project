package com.ts.carplay.lib.transport;

import com.ts.carplay.common.util.LogUtil;
import com.ts.carplay.lib.CarPlayManager;
import java.io.InputStream;
import java.io.OutputStream;

/* JADX INFO: loaded from: classes.dex */
public class CarPlayTransport {
    public static final int CARPLAY_TRANSPORT_BT = 1;
    public static final int CARPLAY_TRANSPORT_NONE = 0;
    public static final int CARPLAY_TRANSPORT_WIFI = 2;
    private static final String TAG = "CarPlayTransport";
    static CarPlayTransport sCarPlayTransport = null;
    private CarPlayBtTransport mBtTransport = new CarPlayBtTransport(this);
    private CarPlayWiFiTransport mWiFiTransport = new CarPlayWiFiTransport(this);

    public CarPlayTransport() {
        sCarPlayTransport = this;
    }

    public static CarPlayTransport getInstance() {
        CarPlayTransport carPlayTransport = sCarPlayTransport;
        if (carPlayTransport == null) {
            throw new IllegalStateException("Attempted to retrieve a CarPlayTransport before construct was called.");
        }
        return carPlayTransport;
    }

    public int init(CarPlayManager manager) {
        LogUtil.info(TAG, "init");
        this.mBtTransport.init(manager);
        this.mWiFiTransport.init();
        return 0;
    }

    public int sendMsgToNative(int type, byte[] msg) {
        if (type == 2) {
            return this.mWiFiTransport.sendMsgToNative(msg);
        }
        return 0;
    }

    public int open(int type) {
        LogUtil.info(TAG, "open type is " + type);
        if (type == 1) {
            return this.mBtTransport.open();
        }
        if (type == 2) {
            return this.mWiFiTransport.open();
        }
        return 0;
    }

    public int close(int type) {
        LogUtil.info(TAG, "close type" + type);
        if (type == 1) {
            this.mBtTransport.close();
            return 0;
        }
        if (type == 2) {
            this.mWiFiTransport.close();
            return 0;
        }
        return 0;
    }

    public int deinit(int type) {
        LogUtil.info(TAG, "deinit type" + type);
        return 0;
    }

    public void updateWirelessSocket(InputStream input, OutputStream output, String rfcommMac) {
        LogUtil.info(TAG, "updateWirelessSocket");
        this.mBtTransport.setmRfcommInOutStream(input, output, rfcommMac);
    }
}
