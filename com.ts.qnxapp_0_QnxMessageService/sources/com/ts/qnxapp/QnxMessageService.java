package com.ts.qnxapp;

import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.os.IBinder;
import android.os.ServiceManager;
import com.ts.qnxapp.util.InteractionToBundle;
import com.ts.qnxapp.util.LogUtil;

/* JADX INFO: loaded from: classes.dex */
public class QnxMessageService extends Service {
    private static final int NUM_CALL_TIME = 123;
    private static final int NUM_EIGHT = 8;
    private static final int NUM_EIGHTTEEN = 18;
    private static final int NUM_EIGHT_TWO = 82;
    private static final int NUM_ELEVEN = 11;
    private static final int NUM_FIFTEEN = 15;
    private static final int NUM_FIRST = 1;
    private static final int NUM_FIVE = 5;
    private static final int NUM_FOUR = 4;
    private static final int NUM_FOURTEEN = 14;
    private static final int NUM_NINE = 9;
    private static final int NUM_NINETEEN = 19;
    private static final int NUM_ONE_HUNDRED = 100;
    private static final int NUM_SECOND = 2;
    private static final int NUM_SEVEN = 7;
    private static final int NUM_SEVENTEN = 17;
    private static final int NUM_SIX = 6;
    private static final int NUM_SIXTEEN = 16;
    private static final int NUM_TEN = 10;
    private static final int NUM_THIRTEEN = 13;
    private static final int NUM_THREE = 3;
    private static final int NUM_TWELVE = 12;
    private static final int NUM_TWENTY_FIVE = 25;
    private static final int NUM_TWENTY_ONE = 21;
    private static final int NUM_TWENTY_THREE = 23;
    private static final int NUM_TWENTY_TWO = 22;
    private static final int NUM_ZERO = 0;
    private static String TAG = "QnxMessageService";
    private QnxMessageServiceImpl mQnxMessageServiceImpl = null;

    @Override // android.app.Service
    public void onCreate() {
        super.onCreate();
        Context context = getApplicationContext();
        LogUtil.logE(TAG, "QnxMessageService onCreate !");
        QnxMessageServiceImpl qnxMessageServiceImpl = new QnxMessageServiceImpl();
        this.mQnxMessageServiceImpl = qnxMessageServiceImpl;
        qnxMessageServiceImpl.init();
        ServiceManager.addService("QnxMessage", this.mQnxMessageServiceImpl);
        LogUtil.logE(TAG, "QnxMessageService onCreate end");
        int orientationValue = context.getResources().getConfiguration().orientation;
        setScreenOrientationState(orientationValue);
        registerBroadcast();
    }

    private void setScreenOrientationState(int state) {
        Bundle bundle = new Bundle();
        if (state == 2) {
            bundle.putInt(InteractionToBundle.SCREEN_ORIENTATION_MODE, 0);
        } else if (state == 1) {
            bundle.putInt(InteractionToBundle.SCREEN_ORIENTATION_MODE, 1);
        }
        this.mQnxMessageServiceImpl.sendMessage(9, 17, null, bundle);
        LogUtil.logW(TAG, "setScreenOrientationState state :" + state);
    }

    private void registerBroadcast() {
        BroadcastReceiver receiver = new BroadcastReceiver() { // from class: com.ts.qnxapp.QnxMessageService.1
            /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
            /* JADX WARN: Removed duplicated region for block: B:164:0x0284  */
            @Override // android.content.BroadcastReceiver
            /*
                Code decompiled incorrectly, please refer to instructions dump.
                To view partially-correct code enable 'Show inconsistent code' option in preferences
            */
            public void onReceive(android.content.Context r17, android.content.Intent r18) {
                /*
                    Method dump skipped, instruction units count: 2422
                    To view this dump change 'Code comments level' option to 'DEBUG'
                */
                throw new UnsupportedOperationException("Method not decompiled: com.ts.qnxapp.QnxMessageService.AnonymousClass1.onReceive(android.content.Context, android.content.Intent):void");
            }
        };
        IntentFilter filter = new IntentFilter();
        filter.addAction("com.ts.test.fdbus.send");
        filter.addAction("com.ts.test.fdbus.get.log");
        filter.addAction("com.ts.test.fdbus.send.mcu");
        filter.addAction("com.ts.test.fdbus.send.ecu");
        filter.addAction("com.ts.test.fdbus.get.dsp.log");
        filter.addAction("com.ts.test.fdbus.get.ecu.log");
        filter.addAction("com.ts.test.fdbus.get.aq.progress");
        filter.addAction("com.ts.test.fdbus.get.muc.progress");
        filter.addAction("com.ts.test.fdbus.get.ecu.progress");
        filter.addAction("com.ts.test.fdbus.switch.ecu");
        filter.addAction("com.ts.test.set.ecuDidInfo");
        filter.addAction("com.ts.test.get.aq.slot");
        filter.addAction("com.ts.test.switch.aq.slot");
        filter.addAction("com.ts.test.set.appointment.time");
        filter.addAction("com.ts.test.set.update.flag");
        filter.addAction("com.ts.test.set.ota.state");
        filter.addAction("com.ts.test.set.heart.ota.state");
        filter.addAction("com.ts.test.set.params");
        filter.addAction("com.ts.test.get.ecu.dtc.info");
        filter.addAction("com.ts.test.clr.ecu.dtc.info");
        filter.addAction("com.ts.test.ecu.uds.common.diag");
        filter.addAction("com.ts.test.ota.master.uac.comm");
        filter.addAction("com.ts.test.net.control");
        filter.addAction("com.ts.test.ipb.test");
        filter.addAction("com.ts.test.fdbus.theme.setting");
        filter.addAction("com.ts.test.fdbus.theme.setting.deep");
        filter.addAction("com.ts.test.fdbus.navi.setting");
        filter.addAction("com.ts.test.fdbus.music");
        filter.addAction("com.ts.test.fdbus.media.progress");
        filter.addAction("com.ts.test.fdbus.phone");
        filter.addAction("com.ts.test.fdbus.navi");
        filter.addAction("com.ts.test.fdbus.language.setting");
        filter.addAction("com.ts.test.fdbus.reset.setting");
        filter.addAction("com.ts.test.fdbus.sound.type");
        filter.addAction("com.ts.test.fdbus.elevation");
        filter.addAction("com.ts.test.fdbus.slope");
        filter.addAction("com.ts.test.fdbus.clock");
        filter.addAction("com.ts.test.fdbus.vehicleThemeType");
        filter.addAction("com.ts.test.fdbus.vehicleThemeType.two");
        filter.addAction("com.ts.test.fdbus.vehicleNaviType");
        filter.addAction("com.ts.test.fdbus.vehicleAdasStat");
        filter.addAction("com.ts.test.fdbus.vehicleAdasStat.zero");
        filter.addAction("com.ts.test.fdbus.vehicleINSDisp");
        filter.addAction("com.ts.test.fdbus.vehicleINSDisp.zero");
        filter.addAction("com.ts.test.fdbus.startup.query");
        filter.addAction("com.ts.test.fdbus.mapFocus");
        filter.addAction("com.ts.test.fdbus.mapState");
        filter.addAction("com.ts.test.fdbus.mapState.full");
        filter.addAction("com.ts.test.fdbus.mapState.simple");
        filter.addAction("com.ts.test.fdbus.overseas.map");
        filter.addAction("com.ts.test.fdbus.ani.status");
        filter.addAction("com.ts.test.fdbus.cluster.test");
        filter.addAction("com.ts.test.fdbus.power.reboot");
        registerReceiver(receiver, filter);
    }

    @Override // android.app.Service
    public IBinder onBind(Intent intent) {
        LogUtil.logE(TAG, "QnxMessageService onBind !");
        return this.mQnxMessageServiceImpl;
    }

    @Override // android.app.Service
    public void onDestroy() {
        LogUtil.logE(TAG, "QnxMessageService onDestroy");
        super.onDestroy();
        this.mQnxMessageServiceImpl = null;
    }
}
