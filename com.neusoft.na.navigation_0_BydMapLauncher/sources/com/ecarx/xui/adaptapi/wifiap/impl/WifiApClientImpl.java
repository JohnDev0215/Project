package com.ecarx.xui.adaptapi.wifiap.impl;

import android.util.Log;
import com.ecarx.xui.adaptapi.wifiap.IWifiApClient;
import java.lang.reflect.Field;

/* JADX INFO: loaded from: classes2.dex */
public class WifiApClientImpl implements IWifiApClient {
    private final String TAG = "WifiApClientImpl";
    public String devName;
    public String deviceAddress;
    public String ipAddress;
    private Object obj;

    public WifiApClientImpl(Object obj) {
        this.deviceAddress = "";
        this.ipAddress = "";
        this.devName = "";
        this.obj = obj;
        try {
            Class<?> cls = Class.forName("android.net.wifi.HotspotClient");
            Field field = cls.getField("deviceAddress");
            field.setAccessible(true);
            this.deviceAddress = (String) field.get(this.obj);
            Field field2 = cls.getField("ipAddress");
            field2.setAccessible(true);
            this.ipAddress = (String) field2.get(this.obj);
            Field field3 = cls.getField("devName");
            field3.setAccessible(true);
            this.devName = (String) field3.get(this.obj);
            Log.d("WifiApClientImpl", "deviceAddress:" + this.deviceAddress + "  ipAddress:" + this.ipAddress + "  devName:" + this.devName);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e2) {
            e2.printStackTrace();
        } catch (NoSuchFieldException e3) {
            e3.printStackTrace();
        }
    }

    @Override // com.ecarx.xui.adaptapi.wifiap.IWifiApClient
    public String getMac() {
        return this.deviceAddress;
    }

    @Override // com.ecarx.xui.adaptapi.wifiap.IWifiApClient
    public String getIP() {
        return this.ipAddress;
    }

    @Override // com.ecarx.xui.adaptapi.wifiap.IWifiApClient
    public String getName() {
        return this.devName;
    }
}
