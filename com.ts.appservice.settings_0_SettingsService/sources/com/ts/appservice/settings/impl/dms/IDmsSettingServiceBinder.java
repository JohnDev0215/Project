package com.ts.appservice.settings.impl.dms;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.RemoteException;
import com.arcsoft.dmssocketserver.DmsSocketServer;
import com.ts.lib.settings.common.utilities.LogUtil;
import com.ts.lib.settings.dms.IDmsSettingsCallback;
import com.ts.lib.settings.dms.IDmsSettingsService;

/* JADX INFO: loaded from: classes.dex */
public class IDmsSettingServiceBinder extends IDmsSettingsService.Stub {
    private static final int BYD_DMS_ALGO_FAULT = 3;
    private static final int BYD_DMS_CAMERA_FAULT = 2;
    private static final int BYD_DMS_EYE_COVER_STATE = 1;
    private static final int BYD_DMS_FATIGUE_STATE_KKSS7 = 1;
    private static final int BYD_DMS_FATIGUE_STATE_KKSS8 = 2;
    private static final int BYD_DMS_FATIGUE_STATE_KMICROSLEEP = 3;
    private static final int BYD_DMS_FATIGUE_STATE_KSLEEP = 4;
    private static final int BYD_DMS_HEAD_COVER_STATE = 2;
    private static final int BYD_DMS_LENS_COVER_STATE = 3;
    private static final int BYD_DMS_LONG_DISTRACTION = 2;
    private static final int BYD_DMS_NO_AUTHORIZE = 1;
    private static final int BYD_DMS_NO_ERROR = 0;
    private static final int BYD_DMS_OFF = 0;
    private static final int BYD_DMS_ON = 1;
    private static final int BYD_DMS_OTHER_FAULT = 4;
    private static final int BYD_DMS_SHORT_DISTRACTION = 1;
    private static final int BYD_DMS_UN_KNOW = -1;
    private static final int DMS_ALGO_FAULT = 3;
    private static final int DMS_CAMERA_FAULT = 2;
    private static final int DMS_EYE_COVER_STATE = 4;
    private static final int DMS_FATIGUE_STATE_KKSS7 = 0;
    private static final int DMS_FATIGUE_STATE_KKSS8 = 1;
    private static final int DMS_FATIGUE_STATE_KMICROSLEEP = 2;
    private static final int DMS_FATIGUE_STATE_KSLEEP = 3;
    private static final int DMS_HEAD_COVER_STATE = 5;
    private static final int DMS_LENS_COVER_STATE = 6;
    private static final int DMS_NO_ERROR = 0;
    private static final int DMS_UNKNOWN_FAULT = 7;
    private static final int DMS_UN_AUTHORIZE = 1;
    private static final String TAG = IDmsSettingServiceBinder.class.getSimpleName();
    private Context mContext;
    private DmsSettingCallback mDmsSettingCallback;
    private DmsSocketServer mDmsSocketServer;
    private int mDmsServiceState = -1;
    private DmsSocketServer.MessageNotify messageNotify = new DmsSocketServer.MessageNotify() { // from class: com.ts.appservice.settings.impl.dms.IDmsSettingServiceBinder.1
        public void NotifyConnectState(DmsSocketServer.ConnectStatus connectStatus) {
            LogUtil.debug(IDmsSettingServiceBinder.TAG, "NotifyConnectState ConnectStatus is : " + connectStatus.name());
        }

        public void NotifyDmsSwitchStatus(DmsSocketServer.SwitchStatus switchStatus) {
            if (switchStatus == DmsSocketServer.SwitchStatus.kOn) {
                IDmsSettingServiceBinder.this.mDmsSettingCallback.onDmsSwitchChanged(1);
            } else if (switchStatus == DmsSocketServer.SwitchStatus.kOff) {
                IDmsSettingServiceBinder.this.mDmsSettingCallback.onDmsSwitchChanged(0);
            } else {
                IDmsSettingServiceBinder.this.mDmsSettingCallback.onDmsSwitchChanged(-1);
            }
        }

        public void NotifyDmsMode(DmsSocketServer.DmsMode dmsMode) {
            LogUtil.debug(IDmsSettingServiceBinder.TAG, "DmsSocketServer DmsMode is " + dmsMode.name());
            IDmsSettingServiceBinder.this.mDmsSettingCallback.onDmsModeChanged(dmsMode.ordinal());
        }

        public void NotifyFatigueSwitchStatus(DmsSocketServer.SwitchStatus switchStatus) {
            if (switchStatus == DmsSocketServer.SwitchStatus.kOn) {
                IDmsSettingServiceBinder.this.mDmsSettingCallback.onFatigueSwitchChanged(1);
            } else if (switchStatus == DmsSocketServer.SwitchStatus.kOff) {
                IDmsSettingServiceBinder.this.mDmsSettingCallback.onFatigueSwitchChanged(0);
            } else {
                IDmsSettingServiceBinder.this.mDmsSettingCallback.onFatigueSwitchChanged(-1);
            }
        }

        public void NotifyDistractionSwitchStatus(DmsSocketServer.SwitchStatus switchStatus) {
            if (switchStatus == DmsSocketServer.SwitchStatus.kOn) {
                IDmsSettingServiceBinder.this.mDmsSettingCallback.onAttentionSwitchChanged(1);
            } else if (switchStatus == DmsSocketServer.SwitchStatus.kOff) {
                IDmsSettingServiceBinder.this.mDmsSettingCallback.onAttentionSwitchChanged(0);
            } else {
                IDmsSettingServiceBinder.this.mDmsSettingCallback.onAttentionSwitchChanged(-1);
            }
        }

        public void NotifyDmsServerState(DmsSocketServer.DmsServerState dmsServerState) {
            int ordinal = dmsServerState.ordinal();
            switch (ordinal) {
                case 0:
                    if (IDmsSettingServiceBinder.this.mDmsServiceState != 0) {
                        IDmsSettingServiceBinder.this.mDmsServiceState = 0;
                        IDmsSettingServiceBinder.this.mDmsSettingCallback.onNotifyDmsServerState(0);
                    }
                    break;
                case 1:
                    if (IDmsSettingServiceBinder.this.mDmsServiceState != 1) {
                        IDmsSettingServiceBinder.this.mDmsServiceState = 1;
                        IDmsSettingServiceBinder.this.mDmsSettingCallback.onNotifyDmsServerState(1);
                    }
                    break;
                case 2:
                    if (IDmsSettingServiceBinder.this.mDmsServiceState != 2) {
                        IDmsSettingServiceBinder.this.mDmsServiceState = 2;
                        IDmsSettingServiceBinder.this.mDmsSettingCallback.onNotifyDmsServerState(2);
                    }
                    break;
                case 3:
                    if (IDmsSettingServiceBinder.this.mDmsServiceState != 3) {
                        IDmsSettingServiceBinder.this.mDmsServiceState = 3;
                        IDmsSettingServiceBinder.this.mDmsSettingCallback.onNotifyDmsServerState(3);
                    }
                    break;
                case 4:
                    IDmsSettingServiceBinder.this.mDmsSettingCallback.onBlockStatusChanged(1);
                    break;
                case 5:
                    IDmsSettingServiceBinder.this.mDmsSettingCallback.onBlockStatusChanged(2);
                    break;
                case 6:
                    IDmsSettingServiceBinder.this.mDmsSettingCallback.onBlockStatusChanged(3);
                    break;
                case 7:
                    if (IDmsSettingServiceBinder.this.mDmsServiceState != 4) {
                        IDmsSettingServiceBinder.this.mDmsServiceState = 4;
                        IDmsSettingServiceBinder.this.mDmsSettingCallback.onNotifyDmsServerState(4);
                    }
                    break;
            }
        }

        public void NotifyFatigueState(DmsSocketServer.FatigueState fatigueState) {
            int ordinal = fatigueState.ordinal();
            LogUtil.debug(IDmsSettingServiceBinder.TAG, "NotifyFatigueState DmsSocketServer FatigueState ordinal is " + ordinal + "fatigueState name is " + fatigueState.name());
            if (ordinal != 0) {
                if (ordinal == 1) {
                    IDmsSettingServiceBinder.this.mDmsSettingCallback.onFatigueDetectChanged(2);
                    return;
                } else if (ordinal == 2) {
                    IDmsSettingServiceBinder.this.mDmsSettingCallback.onFatigueDetectChanged(3);
                    return;
                } else {
                    if (ordinal == 3) {
                        IDmsSettingServiceBinder.this.mDmsSettingCallback.onFatigueDetectChanged(4);
                        return;
                    }
                    return;
                }
            }
            IDmsSettingServiceBinder.this.mDmsSettingCallback.onFatigueDetectChanged(1);
        }

        public void NotifyDistractionState(DmsSocketServer.DistractionState distractionState) {
            if (distractionState == DmsSocketServer.DistractionState.kShortDistraction) {
                IDmsSettingServiceBinder.this.mDmsSettingCallback.onAttentionDetectChanged(1);
            } else {
                IDmsSettingServiceBinder.this.mDmsSettingCallback.onAttentionDetectChanged(2);
            }
        }

        public void NotifyDriverNoResponse() {
            IDmsSettingServiceBinder.this.mDmsSettingCallback.onDriverNotResponse();
        }

        public void NotifyDmsFatigueAlarmRecovered() {
            IDmsSettingServiceBinder.this.mDmsSettingCallback.onRestoreFatigueDetect();
        }
    };

    public IDmsSettingServiceBinder(Context context) {
        this.mContext = context;
        this.mDmsSettingCallback = new DmsSettingCallback(this.mContext);
        registerBroadcast();
        DmsSocketServer dmsSocketServer = new DmsSocketServer(this.mContext, this.messageNotify);
        this.mDmsSocketServer = dmsSocketServer;
        dmsSocketServer.Start();
    }

    private void registerBroadcast() {
        BroadcastReceiver receiver = new BroadcastReceiver() { // from class: com.ts.appservice.settings.impl.dms.IDmsSettingServiceBinder.2
            @Override // android.content.BroadcastReceiver
            public void onReceive(Context context, Intent intent) {
                String action = intent.getAction();
                if (((action.hashCode() == -1333202653 && action.equals("com.ts.test.dms.callback")) ? (byte) 0 : (byte) -1) == 0) {
                    IDmsSettingServiceBinder.this.mDmsSettingCallback.onAttentionDetectChanged(1);
                    IDmsSettingServiceBinder.this.mDmsSettingCallback.onDmsSwitchChanged(2);
                    IDmsSettingServiceBinder.this.mDmsSettingCallback.onFatigueSwitchChanged(3);
                    IDmsSettingServiceBinder.this.mDmsSettingCallback.onAttentionSwitchChanged(4);
                    IDmsSettingServiceBinder.this.mDmsSettingCallback.onFatigueDetectChanged(5);
                    IDmsSettingServiceBinder.this.mDmsSettingCallback.onBlockStatusChanged(6);
                    IDmsSettingServiceBinder.this.mDmsSettingCallback.onDriverNotResponse();
                    IDmsSettingServiceBinder.this.mDmsSettingCallback.onRestoreFatigueDetect();
                    IDmsSettingServiceBinder.this.mDmsSettingCallback.onNotifyDmsServerState(7);
                    IDmsSettingServiceBinder.this.mDmsSettingCallback.onDmsModeChanged(8);
                }
            }
        };
        IntentFilter filter = new IntentFilter();
        filter.addAction("com.ts.test.dms.callback");
        this.mContext.registerReceiver(receiver, filter);
    }

    public void queryDmsServiceStatus() {
        LogUtil.debug(TAG, "queryDmsServiceStatus is " + this.mDmsServiceState);
        this.mDmsSettingCallback.onNotifyDmsServerState(this.mDmsServiceState);
    }

    public void setDmsSwtichStatus(int state) {
        LogUtil.debug(TAG, "setDmsSwtichStatus is :" + state);
        if (state == 0) {
            this.mDmsSocketServer.SetDmsSwitchStatus(DmsSocketServer.SwitchStatus.kOff);
        } else if (state == 1) {
            this.mDmsSocketServer.SetDmsSwitchStatus(DmsSocketServer.SwitchStatus.kOn);
        } else {
            this.mDmsSocketServer.SetDmsSwitchStatus(DmsSocketServer.SwitchStatus.kUnknown);
        }
    }

    public void setPauseFatigueDetect() {
        LogUtil.debug(TAG, "setPauseFatigueDetect");
        this.mDmsSocketServer.PauseDmsFatigueAlarm();
    }

    public void setFatigueDetect(int state) {
        LogUtil.debug(TAG, "setFatigueDetect is :" + state);
        if (state == 0) {
            this.mDmsSocketServer.SetFatigueSwitchStatus(DmsSocketServer.SwitchStatus.kOff);
        } else if (state == 1) {
            this.mDmsSocketServer.SetFatigueSwitchStatus(DmsSocketServer.SwitchStatus.kOn);
        } else {
            this.mDmsSocketServer.SetFatigueSwitchStatus(DmsSocketServer.SwitchStatus.kUnknown);
        }
    }

    public void setAttentionDetect(int state) {
        LogUtil.debug(TAG, "setAttentionDetect is :" + state);
        if (state == 0) {
            this.mDmsSocketServer.SetDistractionSwitchStatus(DmsSocketServer.SwitchStatus.kOff);
        } else if (state == 1) {
            this.mDmsSocketServer.SetDistractionSwitchStatus(DmsSocketServer.SwitchStatus.kOn);
        } else {
            this.mDmsSocketServer.SetDistractionSwitchStatus(DmsSocketServer.SwitchStatus.kUnknown);
        }
    }

    public void getDmsSwitchChanged() {
        this.mDmsSocketServer.GetDmsSwitchStatus();
        LogUtil.debug(TAG, "getDmsSwitchChanged end");
    }

    public void queryFatigueDetect() {
        LogUtil.debug(TAG, "queryFatigueDetect is execute");
        this.mDmsSocketServer.GetFatigueSwitchStatus();
    }

    public void queryAttentionDetect() {
        LogUtil.debug(TAG, "queryAttentionDetect is execute");
        this.mDmsSocketServer.GetDistractionSwitchStatus();
    }

    public void setDmsMode(int mode) {
        LogUtil.debug(TAG, "setDmsMode mode is " + mode);
        if (mode == 0) {
            this.mDmsSocketServer.SetDmsMode(DmsSocketServer.DmsMode.kNormal);
        } else if (mode == 1) {
            this.mDmsSocketServer.SetDmsMode(DmsSocketServer.DmsMode.kSensitive);
        } else {
            this.mDmsSocketServer.SetDmsMode(DmsSocketServer.DmsMode.kFactory);
        }
    }

    public void queryDmsMode() {
        LogUtil.debug(TAG, "queryDmsMode is execute");
        this.mDmsSocketServer.GetDmsMode();
    }

    public void subscribeDmsCallback(IDmsSettingsCallback callback) throws RemoteException {
        if (callback != null) {
            this.mDmsSettingCallback.register(callback);
        }
    }

    public void unsubscribeDmsCallback(IDmsSettingsCallback callback) throws RemoteException {
        if (callback != null) {
            this.mDmsSettingCallback.unregister(callback);
        }
    }

    public void destoryDmsBinder() {
        DmsSettingCallback dmsSettingCallback = this.mDmsSettingCallback;
        if (dmsSettingCallback != null) {
            dmsSettingCallback.kill();
        }
        this.mDmsSocketServer.Stop();
    }
}
