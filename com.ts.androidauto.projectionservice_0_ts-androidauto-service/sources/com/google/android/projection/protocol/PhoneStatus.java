package com.google.android.projection.protocol;

import android.util.Log;
import com.google.android.projection.proto.Protos;
import com.google.protobuf.InvalidProtocolBufferException;

/* JADX INFO: loaded from: classes.dex */
public class PhoneStatus implements CarServiceProvider, PhoneStatusCallbacks {
    private static final boolean DBG = true;
    private static final String TAG = "AHU.PHONE_STATUS";
    private final PhoneStatusListener mListener;
    private long mNativePhoneStatus;

    public interface PhoneStatusListener {
        void onPhoneStatus(Protos.PhoneStatus.Call[] callArr, int i);
    }

    private native int nativeInit(int i, long j) throws IllegalStateException;

    private native int nativeReportAction(String str, String str2, int i);

    private native void nativeShutdown();

    public PhoneStatus(PhoneStatusListener listener) {
        this.mListener = listener;
    }

    @Override // com.google.android.projection.protocol.CarServiceProvider
    public boolean create(int id, long nativeGalReceiver) {
        if (nativeInit(id, nativeGalReceiver) == 0) {
            return DBG;
        }
        return false;
    }

    @Override // com.google.android.projection.protocol.NativeObject
    public void destroy() {
        nativeShutdown();
    }

    @Override // com.google.android.projection.protocol.NativeObject
    public long getNativeInstance() {
        return this.mNativePhoneStatus;
    }

    public int input(String callerNumber, String callerId, int action) {
        return nativeReportAction(callerNumber, callerId, action);
    }

    @Override // com.google.android.projection.protocol.PhoneStatusCallbacks
    public int phoneStatusCallback(byte[] phoneStatus) {
        try {
            Protos.PhoneStatus phoneStatusPb = Protos.PhoneStatus.parseFrom(phoneStatus);
            this.mListener.onPhoneStatus((Protos.PhoneStatus.Call[]) phoneStatusPb.getCallsList().toArray(new Protos.PhoneStatus.Call[0]), phoneStatusPb.getSignalStrength());
            return Protos.MessageStatus.STATUS_SUCCESS.getNumber();
        } catch (InvalidProtocolBufferException e) {
            Log.w("AHU.PHONE_STATUS", "Error parsing protocol buffer in callback", e);
            return Protos.MessageStatus.STATUS_INVALID_INPUT.getNumber();
        }
    }
}
