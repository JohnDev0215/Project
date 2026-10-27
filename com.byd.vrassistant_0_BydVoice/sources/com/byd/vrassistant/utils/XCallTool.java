package com.byd.vrassistant.utils;

import android.content.Context;
import android.hardware.bydauto.xcall.AbsBYDXCallListener;
import android.hardware.bydauto.xcall.BYDAutoXCallDevice;
import com.byd.vrassistant.manager.VoiceAssistantManager;

/* JADX INFO: loaded from: classes2.dex */
public class XCallTool {
    private static final String TAG = "NEU_XCallTool";
    private BYDAutoXCallDevice mCall;
    private AbsBYDXCallListener mListener;

    private XCallTool() {
    }

    public static XCallTool getInstance() {
        return SingletonHolder.single;
    }

    private static class SingletonHolder {
        private static final XCallTool single = new XCallTool();

        private SingletonHolder() {
        }
    }

    public void registerCloseVoice(Context context) {
        this.mCall = BYDAutoXCallDevice.getInstance(context);
        RLog.i(TAG, "registerCloseVoice: ");
        AbsBYDXCallListener absBYDXCallListener = new AbsBYDXCallListener() { // from class: com.byd.vrassistant.utils.XCallTool.1
            @Override // android.hardware.bydauto.xcall.AbsBYDXCallListener
            public void onCallBackModeChanged(int i) {
            }

            @Override // android.hardware.bydauto.xcall.AbsBYDXCallListener
            public void onCallEvent(int i) {
            }

            @Override // android.hardware.bydauto.xcall.AbsBYDXCallListener
            public void onCallStatusChanged(int i) {
                RLog.i(XCallTool.TAG, "registerCloseVoice: i" + i);
                if (i == 1 || i == 2) {
                    VoiceAssistantManager.get().sendCancelPTTEvent();
                    VoiceAssistantManager.get().stopVoiceAssistant();
                } else if (i == 4 || i == 8) {
                    VoiceAssistantManager.get().startVoiceAssistant();
                }
            }
        };
        this.mListener = absBYDXCallListener;
        this.mCall.registerListener(absBYDXCallListener);
    }

    public void unRegisterCloseVoice() {
        this.mCall.unregisterListener(this.mListener);
    }
}
