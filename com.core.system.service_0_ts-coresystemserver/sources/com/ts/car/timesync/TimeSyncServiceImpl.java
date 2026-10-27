package com.ts.car.timesync;

import android.content.Context;
import android.database.ContentObserver;
import android.os.Bundle;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.IBinder;
import android.os.Message;
import android.os.Parcel;
import android.os.RemoteCallbackList;
import android.os.RemoteException;
import android.provider.Settings;
import com.core.system.foundationinterface.ICoreAdminService;
import com.core.system.foundationinterface.ICoreService;
import com.ts.car.input.KeycodeUtil;
import ts.car.timesync.ITimeSyncManager;
import ts.car.timesync.ITimeUpdateListener;

/* JADX INFO: loaded from: classes.dex */
public class TimeSyncServiceImpl extends ITimeSyncManager.Stub implements TimeUpdateListener, ICoreService {
    private static final int AUTO_TIME_OPEN = 1;
    private static final int AUTO_TIME_SET_DELAY = 2000;
    private static final String COER_SERVICE_NAME = "time_sync_service";
    private static final String DESCRIPTOR = "ts.car.timesync.ITimeSyncManager";
    private static final int EVENT_ID_CONFIRM_AUTO_TIME_STATUS = 3;
    private static final int EVENT_ID_INIT_DATA = 1;
    private static final int EVENT_ID_TIME_UPDATE = 2;
    private static final int MAX_CONFIRM_AUTO_TIME_COUNT = 3;
    private static final String TAG = "TimeSyncServiceImpl";
    private static final int TRANSACTION_SETTIME = 100;
    private Context mContext;
    private Handler mHandler;
    private HandlerThread mHandlerThread;
    private int mConfirmedAutoTimeStatusTimes = 0;
    private RemoteCallbackList<ITimeUpdateListener> mCallBackList = new RemoteCallbackList<>();

    /* JADX INFO: Access modifiers changed from: private */
    public void handleThreadMessage(Message msg) throws Settings.SettingNotFoundException {
        int i;
        if (msg == null) {
            return;
        }
        int id = msg.what;
        if (id == 1) {
            LogUtils.debug(TAG, "ID_INIT_DATA");
            TimeSyncJudgment.getInstance(this.mContext).init();
            TimeSyncJudgment.getInstance(this.mContext).setTimeUpdateListener(this);
            return;
        }
        if (id != 2) {
            if (id == 3 && (i = this.mConfirmedAutoTimeStatusTimes) < 3) {
                this.mConfirmedAutoTimeStatusTimes = i + 1;
                keepAutoTimeSwitchOpen();
                this.mHandler.sendEmptyMessageDelayed(3, KeycodeUtil.LONG_PRESS_TIMEOUT_DEFAULT);
                return;
            }
            return;
        }
        RemoteCallbackList<ITimeUpdateListener> remoteCallbackList = this.mCallBackList;
        if (remoteCallbackList == null || remoteCallbackList.getRegisteredCallbackCount() <= 0) {
            LogUtils.info(TAG, "Callback = null");
            return;
        }
        Bundle bundle = msg.getData();
        String timeZone = bundle.getString("timezone");
        long time = bundle.getLong("time");
        int count = this.mCallBackList.beginBroadcast();
        for (int i2 = 0; i2 < count; i2++) {
            try {
                this.mCallBackList.getBroadcastItem(i2).onTimeUpdate(time, timeZone);
            } catch (RemoteException exception) {
                exception.printStackTrace();
            }
        }
        this.mCallBackList.finishBroadcast();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void keepAutoTimeSwitchOpen() throws Settings.SettingNotFoundException {
        int autoTime = 0;
        try {
            autoTime = Settings.Global.getInt(this.mContext.getContentResolver(), "auto_time");
        } catch (Settings.SettingNotFoundException e) {
            LogUtils.err(TAG, "SettingNotFoundException AUTO_TIME");
        }
        LogUtils.info(TAG, "currentAutoTime:" + autoTime);
        if (autoTime != 1) {
            Settings.Global.putInt(this.mContext.getContentResolver(), "auto_time", 1);
            LogUtils.info(TAG, "set AUTO_TIME: open");
        }
    }

    public boolean onTransact(int code, Parcel data, Parcel reply, int flags) throws RemoteException {
        if (code == TRANSACTION_SETTIME) {
            data.enforceInterface(DESCRIPTOR);
            long time = data.readLong();
            String timeZone = data.readString();
            int type = data.readInt();
            setTime(time, timeZone, type);
            reply.writeNoException();
            return true;
        }
        return super.onTransact(code, data, reply, flags);
    }

    public void setTime(long millis, String timeZone, int type) {
        LogUtils.debug(TAG, "setTime start. time is " + millis + " timeZone is " + timeZone + " type is " + type);
        TimeSyncJudgment.getInstance(this.mContext).setTime(millis, type, timeZone);
    }

    public void registerTimeUpdate(ITimeUpdateListener listener) {
        this.mCallBackList.register(listener);
    }

    public void unregisterTimeUpdate(ITimeUpdateListener listener) {
        this.mCallBackList.unregister(listener);
    }

    @Override // com.ts.car.timesync.TimeUpdateListener
    public void onTimeUpdate(long time, String timeZone) {
        Message msg = Message.obtain();
        msg.what = 2;
        Bundle bundle = new Bundle();
        bundle.putString("timezone", timeZone);
        bundle.putLong("time", time);
        msg.setData(bundle);
        Handler handler = this.mHandler;
        if (handler != null) {
            handler.sendMessage(msg);
        } else {
            LogUtils.err(TAG, "mHandler is null!!!");
        }
    }

    @Override // com.core.system.foundationinterface.ICoreService
    public String serviceName() {
        return COER_SERVICE_NAME;
    }

    /* JADX WARN: Multi-variable type inference failed */
    @Override // com.core.system.foundationinterface.ICoreService
    public IBinder getServiceBinder() {
        return this;
    }

    @Override // com.core.system.foundationinterface.ICoreService
    public void init(Context context, ICoreAdminService coreAdminService) {
        this.mContext = context;
        init();
        if (coreAdminService != null) {
            coreAdminService.notifyServiceReady(this);
        }
    }

    private void init() {
        HandlerThread handlerThread = new HandlerThread("time_sync_service_thread");
        this.mHandlerThread = handlerThread;
        handlerThread.start();
        this.mHandler = new Handler(this.mHandlerThread.getLooper()) { // from class: com.ts.car.timesync.TimeSyncServiceImpl.1
            @Override // android.os.Handler
            public void handleMessage(Message msg) throws Settings.SettingNotFoundException {
                TimeSyncServiceImpl.this.handleThreadMessage(msg);
            }
        };
        registAutoTimeObserver();
        this.mHandler.sendEmptyMessage(1);
        this.mHandler.sendEmptyMessage(3);
    }

    private void registAutoTimeObserver() {
        this.mContext.getContentResolver().registerContentObserver(Settings.Global.getUriFor("auto_time"), true, new ContentObserver(this.mHandler) { // from class: com.ts.car.timesync.TimeSyncServiceImpl.2
            @Override // android.database.ContentObserver
            public void onChange(boolean selfChange) throws Settings.SettingNotFoundException {
                TimeSyncServiceImpl.this.keepAutoTimeSwitchOpen();
            }
        });
    }

    @Override // com.core.system.foundationinterface.ICoreService
    public void release(Context context, ICoreAdminService coreAdminService) {
        release();
    }

    private void release() {
        TimeSyncJudgment.getInstance(this.mContext).release();
    }
}
