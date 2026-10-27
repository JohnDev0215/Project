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
public class SaveCurrentLogActivity extends BydBaseActivity implements com.byd.logtools.c.b {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private static BYDLogService f3021b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private static BydCircleTextView f3022c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private static c f3023d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private static com.byd.logtools.tools.a f3024e;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    private b f3025f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    private final ServiceConnection f3026g = new a();

    class a implements ServiceConnection {
        a() {
        }

        @Override // android.content.ServiceConnection
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            BYDLogService unused = SaveCurrentLogActivity.f3021b = ((BYDLogService.p) iBinder).a();
            SaveCurrentLogActivity.f3021b.A0(SaveCurrentLogActivity.this);
        }

        @Override // android.content.ServiceConnection
        public void onServiceDisconnected(ComponentName componentName) {
            if (SaveCurrentLogActivity.f3021b != null) {
                SaveCurrentLogActivity.f3021b.H0(SaveCurrentLogActivity.this);
                BYDLogService unused = SaveCurrentLogActivity.f3021b = null;
            }
        }
    }

    private class b extends BroadcastReceiver {
        private b() {
        }

        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            Log.d("SaveCurrentLogActivity", "UsbCheckReceiver usb action= " + action);
            if (!"android.hardware.usb.action.USB_DEVICE_DETACHED".equals(action) || SaveCurrentLogActivity.f3023d == null) {
                return;
            }
            BydLogHelper.logE("receive unMountSAVE_LOG_UPDATE_UI_STATE_FINISH = " + SaveCurrentLogActivity.f3023d.hasMessages(2) + ", SAVE_LOG_EVENT_SAVE_TO_U_DISK = " + SaveCurrentLogActivity.f3023d.hasMessages(8));
            if (SaveCurrentLogActivity.f3023d.hasMessages(8)) {
                SaveCurrentLogActivity.f3023d.removeMessages(8);
                SaveCurrentLogActivity.f3023d.sendEmptyMessage(8);
            }
        }

        /* synthetic */ b(SaveCurrentLogActivity saveCurrentLogActivity, a aVar) {
            this();
        }
    }

    private static class c extends Handler {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        WeakReference<SaveCurrentLogActivity> f3029a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        SaveCurrentLogActivity f3030b;

        c(SaveCurrentLogActivity saveCurrentLogActivity) {
            this.f3029a = new WeakReference<>(saveCurrentLogActivity);
            this.f3030b = saveCurrentLogActivity;
        }

        @Override // android.os.Handler
        public void handleMessage(Message message) {
            if (this.f3029a.get() == null) {
                BydLogHelper.logV("outer is null");
            }
            switch (message.what) {
                case 1:
                    SaveCurrentLogActivity.f3022c.b(this.f3030b.getString(R.string.str_save_run), -16711936);
                    break;
                case 2:
                    SaveCurrentLogActivity.f3023d.sendEmptyMessageDelayed(8, 10000L);
                    break;
                case 3:
                    SaveCurrentLogActivity.f3022c.b(this.f3030b.getString(R.string.str_save_fail), -65536);
                    break;
                case 4:
                    this.f3030b.finish();
                    break;
                case 5:
                    if (SaveCurrentLogActivity.f3021b != null) {
                        BydLogHelper.logV("SaveLogActivity saveLog");
                        SaveCurrentLogActivity.f3021b.r0(1001);
                    } else {
                        SaveCurrentLogActivity.f3023d.sendEmptyMessageDelayed(5, 1000L);
                    }
                    break;
                case 6:
                    BydLogHelper.logD("save log in background, please wait");
                    SaveCurrentLogActivity.f3024e.b(R.string.str_saveFlag);
                    this.f3030b.finish();
                    break;
                case 7:
                    BydLogHelper.logD("SAVE_LOG_EVENT_SAVE_TO_INTERNAL_STORAGE");
                    SaveCurrentLogActivity.f3024e.b(R.string.str_save_log_to_local);
                    this.f3030b.finish();
                    break;
                case 8:
                    BydLogHelper.logE("SAVE_LOG_EVENT_SAVE_TO_U_DISK");
                    SaveCurrentLogActivity.f3022c.b(this.f3030b.getString(R.string.str_save_finish), -16776961);
                    SaveCurrentLogActivity.f3024e.b(R.string.str_save_current_log_to_udisk);
                    SaveCurrentLogActivity.f3023d.sendEmptyMessageDelayed(4, 1000L);
                    break;
                case 9:
                    BydLogHelper.logD("SAVE_LOG_EVENT_NO_SPACE");
                    SaveCurrentLogActivity.f3024e.b(R.string.str_no_enough_space_save_logs);
                    break;
                case 10:
                    BydLogHelper.logD("SAVE_LOG_EVENT_NO_UDISK");
                    SaveCurrentLogActivity.f3024e.b(R.string.str_no_udisk_save_logs);
                    break;
                default:
                    SaveCurrentLogActivity.f3022c.b(this.f3030b.getString(R.string.str_save_ready), -1);
                    break;
            }
        }
    }

    private void o() {
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("android.hardware.usb.action.USB_DEVICE_ATTACHED");
        intentFilter.addAction("android.hardware.usb.action.USB_DEVICE_DETACHED");
        b bVar = new b(this, null);
        this.f3025f = bVar;
        registerReceiver(bVar, intentFilter);
    }

    @Override // com.byd.logtools.c.b
    public void f(int i) {
        BydLogHelper.logE("OnSaveLogStatusChange status = " + i);
        f3023d.sendEmptyMessage(i);
    }

    @Override // com.byd.logtools.activity.BydBaseActivity, com.bydux.api.common.views.window.BydActivity, androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        if (i("SaveCurrentLogActivity")) {
            finish();
            return;
        }
        BydLogHelper.logD("onCreate");
        setContentView(R.layout.activity_save_current_log);
        f3022c = (BydCircleTextView) findViewById(R.id.save_progress);
        f3023d = new c(this);
        bindService(new Intent(getApplicationContext(), (Class<?>) BYDLogService.class), this.f3026g, 1);
        Message message = new Message();
        message.what = 5;
        f3023d.sendMessage(message);
        f3022c.b(getString(R.string.str_save_ready), getColor(R.color.config_log_text_color));
        f3024e = com.byd.logtools.tools.a.a();
        o();
    }

    @Override // com.byd.logtools.activity.BydBaseActivity, com.bydux.api.common.views.window.BydActivity, androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onDestroy() {
        super.onDestroy();
        if (i("SaveCurrentLogActivity")) {
            return;
        }
        unbindService(this.f3026g);
        BYDLogService bYDLogService = f3021b;
        if (bYDLogService != null) {
            bYDLogService.H0(this);
            f3021b = null;
        }
        if (f3022c != null) {
            f3022c = null;
        }
        if (f3023d != null) {
            BydLogHelper.logD("onDestroy unMountSAVE_LOG_UPDATE_UI_STATE_FINISH = " + f3023d.hasMessages(2) + "\n, SAVE_LOG_EVENT_SAVE_TO_U_DISK = " + f3023d.hasMessages(8) + "\n, SAVE_LOG_UPDATE_UI_STATE_EXIT = " + f3023d.hasMessages(4));
            if (f3023d.hasMessages(2)) {
                f3023d.removeMessages(2);
            }
            if (f3023d.hasMessages(8)) {
                BydLogHelper.logE("onDestroy hasMessages, toast save to uDisk message.");
                f3024e.b(R.string.str_save_log_to_udisk);
                f3023d.removeMessages(8);
            }
            if (f3023d.hasMessages(4)) {
                f3023d.removeMessages(4);
            }
            f3023d = null;
        }
        if (f3024e != null) {
            f3024e = null;
        }
        b bVar = this.f3025f;
        if (bVar != null) {
            unregisterReceiver(bVar);
            this.f3025f = null;
        }
    }

    @Override // com.bydux.api.common.views.window.BydActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onPause() {
        super.onPause();
        if (i("SaveCurrentLogActivity")) {
            finish();
        }
    }

    @Override // com.bydux.api.common.views.window.BydActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onResume() {
        super.onResume();
        if (i("SaveCurrentLogActivity")) {
            finish();
        }
    }

    @Override // com.bydux.api.common.views.window.BydActivity, androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onStart() {
        super.onStart();
        if (i("SaveCurrentLogActivity")) {
            finish();
        }
    }

    @Override // com.bydux.api.common.views.window.BydActivity, androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onStop() {
        super.onStop();
        if (i("SaveCurrentLogActivity")) {
            finish();
        }
    }
}
