package com.ecarx.xui.adaptapi.diminteraction;

import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.ServiceConnection;
import android.content.pm.ResolveInfo;
import android.os.IBinder;
import android.util.Log;
import com.ecarx.xui.adaptapi.diminteraction.IPhoneCallInteraction;
import com.ecarx.xui.adaptapi.diminteraction.IPhoneTransListener;
import java.util.List;

/* JADX INFO: loaded from: classes2.dex */
public class PhoneCallInteraction implements IPhoneCallInteraction {
    private String cTag = "diminteraction";
    private IPhoneCallInteraction.IPhoneCallInteractionCallback callback;
    public IPhoneTransListener phoneTransListener;
    private PhoneTransferListenerConnection phoneTransferListenerConnection;

    public PhoneCallInteraction(Context context) {
        this.phoneTransferListenerConnection = new PhoneTransferListenerConnection();
        Intent intent = new Intent();
        intent.setAction("com.ecarx.xui.adaptapi.diminteraction.PhoneCallService");
        context.getApplicationContext().bindService(new Intent(getExplicitIntent(context, intent)), this.phoneTransferListenerConnection, 1);
        context.registerReceiver(new ReqBroadcastReceiver(), new IntentFilter("com.neusoft.optimus.mmi.phoneInfoReq"));
    }

    private static Intent getExplicitIntent(Context context, Intent intent) {
        List<ResolveInfo> listQueryIntentServices = context.getPackageManager().queryIntentServices(intent, 0);
        if (listQueryIntentServices == null || listQueryIntentServices.size() != 1) {
            return null;
        }
        ResolveInfo resolveInfo = listQueryIntentServices.get(0);
        ComponentName componentName = new ComponentName(resolveInfo.serviceInfo.packageName, resolveInfo.serviceInfo.name);
        Intent intent2 = new Intent(intent);
        intent2.setComponent(componentName);
        return intent2;
    }

    @Override // com.ecarx.xui.adaptapi.diminteraction.IPhoneCallInteraction
    public void writeContact2DBCompleted() {
        Log.d(this.cTag, "PhoneCallInteraction  writeContact2DBCompleted");
        try {
            IPhoneTransListener iPhoneTransListener = this.phoneTransListener;
            if (iPhoneTransListener != null) {
                iPhoneTransListener.notifyDBState(0);
            }
        } catch (Exception e) {
            Log.d(this.cTag, "PhoneCallInteraction  writeContact2DBCompleted 出错" + Log.getStackTraceString(e));
        }
    }

    @Override // com.ecarx.xui.adaptapi.diminteraction.IPhoneCallInteraction
    public void writeCallLog2DBCompleted() {
        Log.d(this.cTag, "PhoneCallInteraction  writeCallLog2DBCompleted");
        try {
            IPhoneTransListener iPhoneTransListener = this.phoneTransListener;
            if (iPhoneTransListener != null) {
                iPhoneTransListener.notifyDBState(1);
            }
        } catch (Exception e) {
            Log.d(this.cTag, "PhoneCallInteraction writeCallLog2DBCompleted 出错" + Log.getStackTraceString(e));
        }
    }

    @Override // com.ecarx.xui.adaptapi.diminteraction.IPhoneCallInteraction
    public void updateConnectedMobileDeviceState(String str, int i, int i2) {
        try {
            IPhoneTransListener iPhoneTransListener = this.phoneTransListener;
            if (iPhoneTransListener != null) {
                iPhoneTransListener.notifyPhoneBasicInfo(str, i, i2);
            }
        } catch (Exception e) {
            Log.d(this.cTag, "PhoneCallInteraction  updateConnectedMobileDeviceState 出错" + Log.getStackTraceString(e));
        }
    }

    @Override // com.ecarx.xui.adaptapi.diminteraction.IPhoneCallInteraction
    public void registerPhoneCallback(IPhoneCallInteraction.IPhoneCallInteractionCallback iPhoneCallInteractionCallback) {
        Log.d(this.cTag, "PhoneCallInteraction  registerPhoneCallback ");
        this.callback = iPhoneCallInteractionCallback;
    }

    @Override // com.ecarx.xui.adaptapi.diminteraction.IPhoneCallInteraction
    public void unRegisterPhoneCallback(IPhoneCallInteraction.IPhoneCallInteractionCallback iPhoneCallInteractionCallback) {
        this.callback = iPhoneCallInteractionCallback;
    }

    @Override // com.ecarx.xui.adaptapi.diminteraction.IPhoneCallInteraction
    public void updateCallInfo(IPhoneCallInteraction.ICallInfo iCallInfo) {
        Log.d(this.cTag, "PhoneCallInteraction  updateCallInfo getActiveCallId:" + iCallInfo.getActiveCallId() + " isMicAvailable:" + iCallInfo.isMicOnVehicleMuted() + " isHandFree:" + iCallInfo.isHandFree() + " getCallId:" + iCallInfo.getCallId() + " getContactName:" + iCallInfo.getContactName() + " getContactNumber:" + iCallInfo.getContactNumber() + " getCallStatus:" + iCallInfo.getCallStatus() + " getCallDuration:" + iCallInfo.getCallDuration() + " getCallCount:" + iCallInfo.getCallCount());
        try {
            IPhoneTransListener iPhoneTransListener = this.phoneTransListener;
            if (iPhoneTransListener != null) {
                iPhoneTransListener.notifyLineInfo(iCallInfo.getActiveCallId(), iCallInfo.isMicOnVehicleMuted(), iCallInfo.isHandFree(), iCallInfo.getCallId(), iCallInfo.getContactName(), iCallInfo.getContactNumber(), iCallInfo.getCallStatus(), iCallInfo.getCallDuration(), iCallInfo.getCallCount());
            }
        } catch (Exception e) {
            Log.d(this.cTag, "PhoneCallInteraction updateCallInfo 出错" + Log.getStackTraceString(e));
        }
    }

    private class PhoneTransferListenerConnection implements ServiceConnection {
        private PhoneTransferListenerConnection() {
        }

        @Override // android.content.ServiceConnection
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            PhoneCallInteraction.this.phoneTransListener = IPhoneTransListener.Stub.asInterface(iBinder);
            Log.d(PhoneCallInteraction.this.cTag, "PhoneCallInteraction  phoneTransListener : " + PhoneCallInteraction.this.phoneTransListener);
        }

        @Override // android.content.ServiceConnection
        public void onServiceDisconnected(ComponentName componentName) {
            PhoneCallInteraction.this.phoneTransListener = null;
        }
    }

    private class ReqBroadcastReceiver extends BroadcastReceiver {
        private ReqBroadcastReceiver() {
        }

        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            if (PhoneCallInteraction.this.callback == null || intent == null) {
                return;
            }
            String stringExtra = intent.getStringExtra("reqValue");
            Log.d(PhoneCallInteraction.this.cTag, "PhoneCallInteraction  reqValue：" + stringExtra);
            if ("basicInfo".equals(stringExtra)) {
                PhoneCallInteraction.this.callback.onRequestConnectedMobileDeviceInfo();
                return;
            }
            if ("endCall".equals(stringExtra)) {
                int intExtra = intent.getIntExtra("WhichCall", -1);
                if (intExtra == 0) {
                    PhoneCallInteraction.this.callback.onEndCall(null);
                    return;
                } else {
                    if (intExtra == 1 || intExtra == 2) {
                        PhoneCallInteraction.this.callback.onAnswerAndEndCall(null);
                        return;
                    }
                    return;
                }
            }
            if ("onCall".equals(stringExtra)) {
                PhoneCallInteraction.this.callback.onAnswerCall(null);
                return;
            }
            if ("changeLine".equals(stringExtra)) {
                PhoneCallInteraction.this.callback.onSwitchCall();
                return;
            }
            if ("micMute".equals(stringExtra)) {
                int intExtra2 = intent.getIntExtra("muteStatus", -1);
                Log.d(PhoneCallInteraction.this.cTag, "PhoneCallInteraction  phoneNum：" + intExtra2);
                PhoneCallInteraction.this.callback.onSwitchMicMode(intExtra2);
            } else if ("toCall".equals(stringExtra)) {
                try {
                    String stringExtra2 = intent.getStringExtra("phoneNum");
                    Log.d(PhoneCallInteraction.this.cTag, "PhoneCallInteraction  phoneNum：" + stringExtra2);
                    PhoneCallInteraction.this.callback.placeCall(stringExtra2);
                } catch (Exception e) {
                    Log.d(PhoneCallInteraction.this.cTag, "PhoneCallInteraction placeCall 出错" + Log.getStackTraceString(e));
                }
            }
        }
    }
}
