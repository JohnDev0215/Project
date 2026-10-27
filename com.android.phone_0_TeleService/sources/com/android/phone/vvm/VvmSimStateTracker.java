package com.android.phone.vvm;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.os.SystemProperties;
import android.telecom.PhoneAccountHandle;
import android.telecom.TelecomManager;
import android.telephony.PhoneStateListener;
import android.telephony.ServiceState;
import android.telephony.SubscriptionManager;
import android.telephony.TelephonyManager;
import android.util.ArrayMap;
import android.util.ArraySet;
import com.android.phone.PhoneUtils;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

/* JADX INFO: loaded from: classes.dex */
public class VvmSimStateTracker extends BroadcastReceiver {
    private static Map<PhoneAccountHandle, ServiceStateListener> sListeners = new ArrayMap();
    private static Set<PhoneAccountHandle> sPreBootHandles = new ArraySet();

    private class ServiceStateListener extends PhoneStateListener {
        private final Context mContext;
        private final PhoneAccountHandle mPhoneAccountHandle;

        public ServiceStateListener(Context context, PhoneAccountHandle phoneAccountHandle) {
            this.mContext = context;
            this.mPhoneAccountHandle = phoneAccountHandle;
        }

        public void listen() {
            TelephonyManager telephonyManager = VvmSimStateTracker.getTelephonyManager(this.mContext, this.mPhoneAccountHandle);
            if (telephonyManager == null) {
                VvmLog.e("VvmSimStateTracker", "Cannot create TelephonyManager from " + this.mPhoneAccountHandle);
                return;
            }
            telephonyManager.listen(this, 1);
        }

        public void unlisten() {
            ((TelephonyManager) this.mContext.getSystemService(TelephonyManager.class)).listen(this, 0);
            VvmSimStateTracker.sListeners.put(this.mPhoneAccountHandle, null);
        }

        @Override // android.telephony.PhoneStateListener
        public void onServiceStateChanged(ServiceState serviceState) {
            if (serviceState.getState() == 0) {
                VvmLog.i("VvmSimStateTracker", "in service");
                VvmSimStateTracker.this.sendConnected(this.mContext, this.mPhoneAccountHandle);
                unlisten();
            }
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:22:0x0046  */
    @Override // android.content.BroadcastReceiver
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void onReceive(android.content.Context r8, android.content.Intent r9) {
        /*
            r7 = this;
            java.lang.String r0 = r9.getAction()
            java.lang.String r1 = "VvmSimStateTracker"
            if (r0 != 0) goto Le
            java.lang.String r7 = "Null action for intent."
            com.android.phone.vvm.VvmLog.w(r1, r7)
            return
        Le:
            com.android.phone.vvm.VvmLog.i(r1, r0)
            int r2 = r0.hashCode()
            r3 = -1138588223(0xffffffffbc2285c1, float:-0.009919585)
            r4 = 2
            r5 = 1
            r6 = -1
            if (r2 == r3) goto L3c
            r3 = -229777127(0xfffffffff24de119, float:-4.0778594E30)
            if (r2 == r3) goto L32
            r3 = 798292259(0x2f94f923, float:2.7098065E-10)
            if (r2 == r3) goto L28
            goto L46
        L28:
            java.lang.String r2 = "android.intent.action.BOOT_COMPLETED"
            boolean r0 = r0.equals(r2)
            if (r0 == 0) goto L46
            r0 = 0
            goto L47
        L32:
            java.lang.String r2 = "android.intent.action.SIM_STATE_CHANGED"
            boolean r0 = r0.equals(r2)
            if (r0 == 0) goto L46
            r0 = r5
            goto L47
        L3c:
            java.lang.String r2 = "android.telephony.action.CARRIER_CONFIG_CHANGED"
            boolean r0 = r0.equals(r2)
            if (r0 == 0) goto L46
            r0 = r4
            goto L47
        L46:
            r0 = r6
        L47:
            if (r0 == 0) goto L8f
            if (r0 == r5) goto L7d
            if (r0 == r4) goto L4e
            goto L92
        L4e:
            java.lang.String r0 = "subscription"
            int r9 = r9.getIntExtra(r0, r6)
            boolean r0 = android.telephony.SubscriptionManager.isValidSubscriptionId(r9)
            if (r0 != 0) goto L63
            java.lang.String r9 = "Received SIM change for invalid subscription id."
            com.android.phone.vvm.VvmLog.i(r1, r9)
            r7.checkRemovedSim(r8)
            return
        L63:
            android.telecom.PhoneAccountHandle r9 = com.android.phone.vvm.PhoneAccountHandleConverter.fromSubId(r9)
            java.lang.String r0 = r9.getId()
            java.lang.String r2 = "null"
            boolean r0 = r2.equals(r0)
            if (r0 == 0) goto L79
            java.lang.String r7 = "null phone account handle ID, possible modem crash. Ignoring carrier config changed event"
            com.android.phone.vvm.VvmLog.e(r1, r7)
            return
        L79:
            r7.onCarrierConfigChanged(r8, r9)
            goto L92
        L7d:
            java.lang.String r0 = "ss"
            java.lang.String r9 = r9.getStringExtra(r0)
            java.lang.String r0 = "ABSENT"
            boolean r9 = r0.equals(r9)
            if (r9 == 0) goto L92
            r7.checkRemovedSim(r8)
            goto L92
        L8f:
            r7.onBootCompleted(r8)
        L92:
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.phone.vvm.VvmSimStateTracker.onReceive(android.content.Context, android.content.Intent):void");
    }

    private void onBootCompleted(Context context) {
        for (PhoneAccountHandle phoneAccountHandle : sPreBootHandles) {
            TelephonyManager telephonyManager = getTelephonyManager(context, phoneAccountHandle);
            if (telephonyManager != null) {
                if (telephonyManager.getServiceState().getState() == 0) {
                    sListeners.put(phoneAccountHandle, null);
                    sendConnected(context, phoneAccountHandle);
                } else {
                    listenToAccount(context, phoneAccountHandle);
                }
            }
        }
        sPreBootHandles.clear();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void sendConnected(Context context, PhoneAccountHandle phoneAccountHandle) {
        VvmLog.i("VvmSimStateTracker", "Service connected on " + phoneAccountHandle);
        RemoteVvmTaskManager.startCellServiceConnected(context, phoneAccountHandle);
    }

    private void checkRemovedSim(Context context) {
        SubscriptionManager subscriptionManagerFrom = SubscriptionManager.from(context);
        if (!isBootCompleted()) {
            for (PhoneAccountHandle phoneAccountHandle : sPreBootHandles) {
                if (!PhoneUtils.isPhoneAccountActive(subscriptionManagerFrom, phoneAccountHandle)) {
                    sPreBootHandles.remove(phoneAccountHandle);
                }
            }
            return;
        }
        ArraySet arraySet = new ArraySet();
        for (PhoneAccountHandle phoneAccountHandle2 : sListeners.keySet()) {
            if (!PhoneUtils.isPhoneAccountActive(subscriptionManagerFrom, phoneAccountHandle2)) {
                arraySet.add(phoneAccountHandle2);
                ServiceStateListener serviceStateListener = sListeners.get(phoneAccountHandle2);
                if (serviceStateListener != null) {
                    serviceStateListener.unlisten();
                }
                sendSimRemoved(context, phoneAccountHandle2);
            }
        }
        Iterator it = arraySet.iterator();
        while (it.hasNext()) {
            sListeners.remove((PhoneAccountHandle) it.next());
        }
    }

    private boolean isBootCompleted() {
        return SystemProperties.getBoolean("sys.boot_completed", false);
    }

    private void sendSimRemoved(Context context, PhoneAccountHandle phoneAccountHandle) {
        VvmLog.i("VvmSimStateTracker", "Sim removed on " + phoneAccountHandle);
        RemoteVvmTaskManager.startSimRemoved(context, phoneAccountHandle);
    }

    private void onCarrierConfigChanged(Context context, PhoneAccountHandle phoneAccountHandle) {
        if (!isBootCompleted()) {
            sPreBootHandles.add(phoneAccountHandle);
            return;
        }
        TelephonyManager telephonyManager = getTelephonyManager(context, phoneAccountHandle);
        if (telephonyManager == null) {
            VvmLog.e("VvmSimStateTracker", "Cannot create TelephonyManager from " + phoneAccountHandle + ", subId=" + ((TelephonyManager) context.getSystemService(TelephonyManager.class)).getSubIdForPhoneAccount(((TelecomManager) context.getSystemService(TelecomManager.class)).getPhoneAccount(phoneAccountHandle)));
            return;
        }
        if (telephonyManager.getServiceState().getState() == 0) {
            sendConnected(context, phoneAccountHandle);
            sListeners.put(phoneAccountHandle, null);
        } else {
            listenToAccount(context, phoneAccountHandle);
        }
    }

    private void listenToAccount(Context context, PhoneAccountHandle phoneAccountHandle) {
        ServiceStateListener serviceStateListener = new ServiceStateListener(context, phoneAccountHandle);
        serviceStateListener.listen();
        sListeners.put(phoneAccountHandle, serviceStateListener);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static TelephonyManager getTelephonyManager(Context context, PhoneAccountHandle phoneAccountHandle) {
        return ((TelephonyManager) context.getSystemService(TelephonyManager.class)).createForPhoneAccountHandle(phoneAccountHandle);
    }
}
