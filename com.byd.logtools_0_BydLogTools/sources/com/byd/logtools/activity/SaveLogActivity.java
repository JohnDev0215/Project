package com.byd.logtools.activity;

import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.util.Log;
import com.byd.logtools.R;
import com.byd.logtools.logservice.BYDLogService;
import com.byd.logtools.tools.BydCircleTextView;
import com.bydux.api.common.tools.log.BydLogHelper;
import java.lang.ref.WeakReference;

/* JADX INFO: loaded from: classes.dex */
public class SaveLogActivity extends BydBaseActivity implements com.byd.logtools.c.b {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private static BYDLogService f3031b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private static BydCircleTextView f3032c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private static c f3033d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private static com.byd.logtools.tools.a f3034e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    private b f3035f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    private final ServiceConnection f3036g = new a();

    class a implements ServiceConnection {
        a() {
        }

        @Override // android.content.ServiceConnection
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            BYDLogService unused = SaveLogActivity.f3031b = ((BYDLogService.p) iBinder).a();
            SaveLogActivity.f3031b.A0(SaveLogActivity.this);
        }

        @Override // android.content.ServiceConnection
        public void onServiceDisconnected(ComponentName componentName) {
            if (SaveLogActivity.f3031b != null) {
                SaveLogActivity.f3031b.H0(SaveLogActivity.this);
                BYDLogService unused = SaveLogActivity.f3031b = null;
            }
        }
    }

    private class b extends BroadcastReceiver {
        private b() {
        }

        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            Log.d("SaveLogActivity", "UsbCheckReceiver usb action= " + action);
            if (!"android.hardware.usb.action.USB_DEVICE_DETACHED".equals(action) || SaveLogActivity.f3033d == null) {
                return;
            }
            BydLogHelper.logE("receive unMountSAVE_LOG_UPDATE_UI_STATE_FINISH = " + SaveLogActivity.f3033d.hasMessages(2) + ", SAVE_LOG_EVENT_SAVE_TO_U_DISK = " + SaveLogActivity.f3033d.hasMessages(8));
            if (SaveLogActivity.f3033d.hasMessages(8)) {
                SaveLogActivity.f3033d.removeMessages(8);
                SaveLogActivity.f3033d.sendEmptyMessage(8);
            }
        }

        /* synthetic */ b(SaveLogActivity saveLogActivity, a aVar) {
            this();
        }
    }

    private static class c extends Handler {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        WeakReference<SaveLogActivity> f3039a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        SaveLogActivity f3040b;

        c(SaveLogActivity saveLogActivity) {
            this.f3039a = new WeakReference<>(saveLogActivity);
            this.f3040b = saveLogActivity;
        }

        @Override // android.os.Handler
        public void handleMessage(Message message) {
            if (this.f3039a.get() == null) {
                BydLogHelper.logV("outer is null");
            }
            switch (message.what) {
                case 1:
                    SaveLogActivity.f3032c.b(this.f3040b.getString(R.string.str_save_run), -16711936);
                    break;
                case 2:
                    SaveLogActivity.f3033d.sendEmptyMessageDelayed(8, 10000L);
                    break;
                case 3:
                    SaveLogActivity.f3032c.b(this.f3040b.getString(R.string.str_save_fail), -65536);
                    break;
                case 4:
                    this.f3040b.finish();
                    break;
                case 5:
                    if (SaveLogActivity.f3031b != null) {
                        BydLogHelper.logV("SaveLogActivity saveLog");
                        SaveLogActivity.f3031b.r0(1002);
                    } else {
                        SaveLogActivity.f3033d.sendEmptyMessageDelayed(5, 1000L);
                    }
                    break;
                case 6:
                    BydLogHelper.logD("save log in background, please wait");
                    SaveLogActivity.f3034e.b(R.string.str_saveFlag);
                    this.f3040b.finish();
                    break;
                case 7:
                    BydLogHelper.logD("SAVE_LOG_EVENT_SAVE_TO_INTERNAL_STORAGE");
                    SaveLogActivity.f3034e.b(R.string.str_save_log_to_local);
                    this.f3040b.finish();
                    break;
                case 8:
                    BydLogHelper.logE("SAVE_LOG_EVENT_SAVE_TO_U_DISK");
                    SaveLogActivity.f3032c.b(this.f3040b.getString(R.string.str_save_finish), -16776961);
                    SaveLogActivity.f3034e.b(R.string.str_save_log_to_udisk);
                    SaveLogActivity.f3033d.sendEmptyMessageDelayed(4, 1000L);
                    break;
                case 9:
                    BydLogHelper.logD("SAVE_LOG_EVENT_NO_SPACE");
                    SaveLogActivity.f3034e.b(R.string.str_no_enough_space_save_logs);
                    break;
                case 10:
                    BydLogHelper.logD("SAVE_LOG_EVENT_NO_UDISK");
                    SaveLogActivity.f3034e.b(R.string.str_no_udisk_save_logs);
                    break;
                default:
                    SaveLogActivity.f3032c.b(this.f3040b.getString(R.string.str_save_ready), -1);
                    break;
            }
        }
    }

    private void o() {
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("android.hardware.usb.action.USB_DEVICE_ATTACHED");
        intentFilter.addAction("android.hardware.usb.action.USB_DEVICE_DETACHED");
        b bVar = new b(this, null);
        this.f3035f = bVar;
        registerReceiver(bVar, intentFilter);
    }

    @Override // com.byd.logtools.c.b
    public void f(int i) {
        BydLogHelper.logE("OnSaveLogStatusChange status = " + i);
        f3033d.sendEmptyMessage(i);
    }

    @Override // com.byd.logtools.activity.BydBaseActivity, com.bydux.api.common.views.window.BydActivity, androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        if (i("SaveLogActivity")) {
            finish();
            return;
        }
        BydLogHelper.logD("onCreate");
        setContentView(R.layout.activity_save_log);
        f3032c = (BydCircleTextView) findViewById(R.id.save_progress);
        f3033d = new c(this);
        bindService(new Intent(getApplicationContext(), (Class<?>) BYDLogService.class), this.f3036g, 1);
        Message message = new Message();
        message.what = 5;
        f3033d.sendMessage(message);
        f3032c.b(getString(R.string.str_save_ready), getColor(R.color.config_log_text_color));
        f3034e = com.byd.logtools.tools.a.a();
        o();
    }

    @Override // com.byd.logtools.activity.BydBaseActivity, com.bydux.api.common.views.window.BydActivity, androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onDestroy() {
        super.onDestroy();
        if (i("SaveLogActivity")) {
            return;
        }
        unbindService(this.f3036g);
        BYDLogService bYDLogService = f3031b;
        if (bYDLogService != null) {
            bYDLogService.H0(this);
            f3031b = null;
        }
        if (f3032c != null) {
            f3032c = null;
        }
        if (f3033d != null) {
            BydLogHelper.logD("onDestroy unMountSAVE_LOG_UPDATE_UI_STATE_FINISH = " + f3033d.hasMessages(2) + "\n, SAVE_LOG_EVENT_SAVE_TO_U_DISK = " + f3033d.hasMessages(8) + "\n, SAVE_LOG_UPDATE_UI_STATE_EXIT = " + f3033d.hasMessages(4));
            if (f3033d.hasMessages(2)) {
                f3033d.removeMessages(2);
            }
            if (f3033d.hasMessages(8)) {
                BydLogHelper.logE("onDestroy hasMessages, toast save to uDisk message.");
                f3034e.b(R.string.str_save_log_to_udisk);
                f3033d.removeMessages(8);
            }
            if (f3033d.hasMessages(4)) {
                f3033d.removeMessages(4);
            }
            f3033d = null;
        }
        if (f3034e != null) {
            f3034e = null;
        }
        b bVar = this.f3035f;
        if (bVar != null) {
            unregisterReceiver(bVar);
            this.f3035f = null;
        }
    }

    @Override // com.bydux.api.common.views.window.BydActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onPause() {
        super.onPause();
        if (i("SaveLogActivity")) {
            finish();
        }
    }

    @Override // com.bydux.api.common.views.window.BydActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onResume() {
        super.onResume();
        if (i("SaveLogActivity")) {
            finish();
        }
    }

    @Override // com.bydux.api.common.views.window.BydActivity, androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onStart() {
        super.onStart();
        if (i("SaveLogActivity")) {
            finish();
        }
    }

    @Override // com.bydux.api.common.views.window.BydActivity, androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onStop() {
        super.onStop();
        if (i("SaveLogActivity")) {
            finish();
        }
    }
}
