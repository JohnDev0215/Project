package com.facebook.react.modules.network;

import androidx.annotation.Nullable;
import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.WritableArray;
import com.facebook.react.bridge.WritableMap;
import com.facebook.react.modules.core.DeviceEventManagerModule;
import java.net.SocketTimeoutException;

/* JADX INFO: loaded from: classes6.dex */
public class ResponseUtil {
    public static void onDataSend(@Nullable DeviceEventManagerModule.RCTDeviceEventEmitter rCTDeviceEventEmitter, int i, long j, long j2) {
        WritableArray writableArrayCreateArray = Arguments.createArray();
        writableArrayCreateArray.pushInt(i);
        writableArrayCreateArray.pushInt((int) j);
        writableArrayCreateArray.pushInt((int) j2);
        if (rCTDeviceEventEmitter != null) {
            rCTDeviceEventEmitter.emit("didSendNetworkData", writableArrayCreateArray);
        }
    }

    public static void onIncrementalDataReceived(@Nullable DeviceEventManagerModule.RCTDeviceEventEmitter rCTDeviceEventEmitter, int i, String str, long j, long j2) {
        WritableArray writableArrayCreateArray = Arguments.createArray();
        writableArrayCreateArray.pushInt(i);
        writableArrayCreateArray.pushString(str);
        writableArrayCreateArray.pushInt((int) j);
        writableArrayCreateArray.pushInt((int) j2);
        if (rCTDeviceEventEmitter != null) {
            rCTDeviceEventEmitter.emit("didReceiveNetworkIncrementalData", writableArrayCreateArray);
        }
    }

    public static void onDataReceivedProgress(@Nullable DeviceEventManagerModule.RCTDeviceEventEmitter rCTDeviceEventEmitter, int i, long j, long j2) {
        WritableArray writableArrayCreateArray = Arguments.createArray();
        writableArrayCreateArray.pushInt(i);
        writableArrayCreateArray.pushInt((int) j);
        writableArrayCreateArray.pushInt((int) j2);
        if (rCTDeviceEventEmitter != null) {
            rCTDeviceEventEmitter.emit("didReceiveNetworkDataProgress", writableArrayCreateArray);
        }
    }

    public static void onDataReceived(@Nullable DeviceEventManagerModule.RCTDeviceEventEmitter rCTDeviceEventEmitter, int i, String str) {
        WritableArray writableArrayCreateArray = Arguments.createArray();
        writableArrayCreateArray.pushInt(i);
        writableArrayCreateArray.pushString(str);
        if (rCTDeviceEventEmitter != null) {
            rCTDeviceEventEmitter.emit("didReceiveNetworkData", writableArrayCreateArray);
        }
    }

    public static void onDataReceived(@Nullable DeviceEventManagerModule.RCTDeviceEventEmitter rCTDeviceEventEmitter, int i, WritableMap writableMap) {
        WritableArray writableArrayCreateArray = Arguments.createArray();
        writableArrayCreateArray.pushInt(i);
        writableArrayCreateArray.pushMap(writableMap);
        if (rCTDeviceEventEmitter != null) {
            rCTDeviceEventEmitter.emit("didReceiveNetworkData", writableArrayCreateArray);
        }
    }

    public static void onRequestError(@Nullable DeviceEventManagerModule.RCTDeviceEventEmitter rCTDeviceEventEmitter, int i, String str, Throwable th) {
        WritableArray writableArrayCreateArray = Arguments.createArray();
        writableArrayCreateArray.pushInt(i);
        writableArrayCreateArray.pushString(str);
        if (th != null && th.getClass() == SocketTimeoutException.class) {
            writableArrayCreateArray.pushBoolean(true);
        }
        if (rCTDeviceEventEmitter != null) {
            rCTDeviceEventEmitter.emit("didCompleteNetworkResponse", writableArrayCreateArray);
        }
    }

    public static void onRequestSuccess(@Nullable DeviceEventManagerModule.RCTDeviceEventEmitter rCTDeviceEventEmitter, int i) {
        WritableArray writableArrayCreateArray = Arguments.createArray();
        writableArrayCreateArray.pushInt(i);
        writableArrayCreateArray.pushNull();
        if (rCTDeviceEventEmitter != null) {
            rCTDeviceEventEmitter.emit("didCompleteNetworkResponse", writableArrayCreateArray);
        }
    }

    public static void onResponseReceived(@Nullable DeviceEventManagerModule.RCTDeviceEventEmitter rCTDeviceEventEmitter, int i, int i2, WritableMap writableMap, String str) {
        WritableArray writableArrayCreateArray = Arguments.createArray();
        writableArrayCreateArray.pushInt(i);
        writableArrayCreateArray.pushInt(i2);
        writableArrayCreateArray.pushMap(writableMap);
        writableArrayCreateArray.pushString(str);
        if (rCTDeviceEventEmitter != null) {
            rCTDeviceEventEmitter.emit("didReceiveNetworkResponse", writableArrayCreateArray);
        }
    }
}
