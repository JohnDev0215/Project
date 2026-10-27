package com.byd.vrassistant.service;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.os.RemoteException;
import cerence.ark.assistant.controller.enumeration.VehicleAreaEnum;
import com.byd.vrassistant.BYDVMUtils;
import com.byd.vrassistant.BydVoiceApplication;
import com.byd.vrassistant.common.ICommonSuccessInterface;
import com.byd.vrassistant.manager.SystemSettingManager;
import com.byd.vrassistant.manager.VoiceAssistantManager;
import com.byd.vrassistant.service.LocalVRSettingService;
import com.byd.vrassistant.utils.ForegroundAppUtil;
import com.byd.vrassistant.utils.RLog;
import com.ts.lib.settings.vradapter.ILocalVrSettingsCallback;
import com.ts.lib.settings.vradapter.ILocalVrSettingsService;

/* JADX INFO: loaded from: classes2.dex */
public class LocalVRSettingService extends Service {
    static String TAG = "NEU_LocalVRSettingService";
    ILocalVrSettingsService.Stub stub = new AnonymousClass1();

    /* JADX INFO: renamed from: com.byd.vrassistant.service.LocalVRSettingService$1, reason: invalid class name */
    class AnonymousClass1 extends ILocalVrSettingsService.Stub {
        AnonymousClass1() {
        }

        @Override // com.ts.lib.settings.vradapter.ILocalVrSettingsService
        public int getVrState() throws RemoteException {
            int vRState = BYDVMUtils.getInstance().getBYDVMViewModel().getVRState();
            RLog.e(LocalVRSettingService.TAG, "getVrState==" + vRState);
            return vRState;
        }

        @Override // com.ts.lib.settings.vradapter.ILocalVrSettingsService
        public void doStartVr(final ILocalVrSettingsCallback iLocalVrSettingsCallback) throws RemoteException {
            RLog.e(LocalVRSettingService.TAG, "doStartVr");
            if (!VoiceAssistantManager.get().getIsVoiceAssistantIniting()) {
                RLog.i(LocalVRSettingService.TAG, "getIsVoiceAssistantIniting = false");
                if (VoiceAssistantManager.get().getShowGuideResult() && !ForegroundAppUtil.isForegroundApp(BydVoiceApplication.getContext(), "com.byd.vrsettings")) {
                    SystemSettingManager.get().openScreenByName("com.byd.vrsettings", "com.byd.vrsettings.MainActivity");
                }
            }
            VoiceAssistantManager.get().sendPTTEvent(SystemSettingManager.get().isRightDriver() ? VehicleAreaEnum.SEAT_ROW_1_RIGHT : VehicleAreaEnum.SEAT_ROW_1_LEFT, new ICommonSuccessInterface() { // from class: com.byd.vrassistant.service.LocalVRSettingService.1.1
                @Override // com.byd.vrassistant.common.ICommonSuccessInterface
                public void isSuccess(boolean z) {
                    try {
                        int i = 1;
                        VoiceAssistantManager.get().setHardKeyPTTEvent(true);
                        VoiceAssistantManager.get().setVirtualKeyPTTEvent(false);
                        ILocalVrSettingsCallback iLocalVrSettingsCallback2 = iLocalVrSettingsCallback;
                        if (!z) {
                            i = 0;
                        }
                        iLocalVrSettingsCallback2.onLocalVrAppStateChanged(i);
                    } catch (RemoteException e) {
                        e.printStackTrace();
                    }
                }
            });
        }

        @Override // com.ts.lib.settings.vradapter.ILocalVrSettingsService
        public void doStopVr(final ILocalVrSettingsCallback iLocalVrSettingsCallback) throws RemoteException {
            RLog.e(LocalVRSettingService.TAG, "doStopVr");
            VoiceAssistantManager.get().sendCancelPTTEventCallBack(new ICommonSuccessInterface() { // from class: com.byd.vrassistant.service.LocalVRSettingService$1$$ExternalSyntheticLambda0
                @Override // com.byd.vrassistant.common.ICommonSuccessInterface
                public final void isSuccess(boolean z) {
                    LocalVRSettingService.AnonymousClass1.lambda$doStopVr$0(iLocalVrSettingsCallback, z);
                }
            });
        }

        static /* synthetic */ void lambda$doStopVr$0(ILocalVrSettingsCallback iLocalVrSettingsCallback, boolean z) {
            try {
                iLocalVrSettingsCallback.onLocalVrAppStateChanged(z ? 1 : 0);
            } catch (RemoteException e) {
                RLog.e(LocalVRSettingService.TAG, "callback.onLocalVrAppStateChanged: ", e);
            }
        }
    }

    @Override // android.app.Service
    public IBinder onBind(Intent intent) {
        RLog.e(TAG, "onBind intent : " + intent.getPackage());
        return this.stub;
    }
}
