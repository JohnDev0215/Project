package com.byd.engineermode.ui.functiontest.logs;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import butterknife.R;
import com.byd.engineermode.ui.activity.BaseActivity;
import com.byd.engineermode.ui.functiontest.logs.BYDLogService;
import java.lang.ref.WeakReference;

/* JADX INFO: loaded from: classes.dex */
public class SaveLogActivity extends BaseActivity implements c {
    private static BYDLogService v;
    private static BydCircleTextView w;
    private static b x;
    private final ServiceConnection y = new a();

    class a implements ServiceConnection {
        a() {
        }

        @Override // android.content.ServiceConnection
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            BYDLogService unused = SaveLogActivity.v = ((BYDLogService.b) iBinder).a();
            SaveLogActivity.v.k(SaveLogActivity.this);
        }

        @Override // android.content.ServiceConnection
        public void onServiceDisconnected(ComponentName componentName) {
            if (SaveLogActivity.v != null) {
                SaveLogActivity.v.l(SaveLogActivity.this);
                BYDLogService unused = SaveLogActivity.v = null;
            }
        }
    }

    private static class b extends Handler {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        WeakReference<SaveLogActivity> f2471a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        SaveLogActivity f2472b;

        b(SaveLogActivity saveLogActivity) {
            this.f2471a = new WeakReference<>(saveLogActivity);
            this.f2472b = saveLogActivity;
        }

        @Override // android.os.Handler
        public void handleMessage(Message message) {
            if (this.f2471a.get() == null) {
                com.byd.engineermode.utils.a.a("outer is null");
            }
            switch (message.what) {
                case 1:
                    SaveLogActivity.w.b(this.f2472b.getString(R.string.str_save_run), -16711936);
                    break;
                case 2:
                    SaveLogActivity.w.b(this.f2472b.getString(R.string.str_save_finish), -16776961);
                    SaveLogActivity.x.sendEmptyMessageDelayed(4, 1000L);
                    break;
                case 3:
                    SaveLogActivity.w.b(this.f2472b.getString(R.string.str_save_fail), -65536);
                    break;
                case 4:
                    this.f2472b.finish();
                    break;
                case 5:
                    if (SaveLogActivity.v != null) {
                        com.byd.engineermode.utils.a.a("SaveLogActivity saveLog");
                        SaveLogActivity.v.h();
                    } else {
                        com.byd.engineermode.utils.a.c("LocalService onCreate fail");
                        SaveLogActivity.x.sendEmptyMessageDelayed(5, 1000L);
                    }
                    break;
                case 6:
                    com.byd.engineermode.utils.a.a("save log in background, please wait");
                    com.byd.engineermode.ui.dialog.a.a(this.f2472b).b(R.string.str_saveFlag);
                    this.f2472b.finish();
                    break;
                case 7:
                    com.byd.engineermode.utils.a.a("SAVE_LOG_EVENT_SAVE_TO_INTERNAL_STORAGE");
                    com.byd.engineermode.ui.dialog.a.a(this.f2472b).b(R.string.str_save_log_to_local);
                    this.f2472b.finish();
                    break;
                case 8:
                    com.byd.engineermode.utils.a.a("SAVE_LOG_EVENT_SAVE_TO_U_DISK");
                    com.byd.engineermode.ui.dialog.a.a(this.f2472b).b(R.string.str_save_log_to_udisk);
                    this.f2472b.finish();
                    break;
                case 9:
                    com.byd.engineermode.utils.a.a("SAVE_LOG_EVENT_NO_SPACE");
                    com.byd.engineermode.ui.dialog.a.a(this.f2472b).b(R.string.str_no_enough_space_save_logs);
                    break;
                case 10:
                    com.byd.engineermode.utils.a.a("SAVE_LOG_EVENT_NO_UDISK");
                    com.byd.engineermode.ui.dialog.a.a(this.f2472b).b(R.string.str_no_udisk_save_logs);
                    break;
                default:
                    SaveLogActivity.w.b(this.f2472b.getString(R.string.str_save_ready), -1);
                    break;
            }
        }
    }

    @Override // com.byd.engineermode.ui.functiontest.logs.c
    public void j(int i) {
        com.byd.engineermode.utils.a.c("OnSaveLogStatusChange status = " + i);
        x.sendEmptyMessage(i);
    }

    @Override // com.byd.engineermode.ui.activity.BaseActivity, androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        com.byd.engineermode.utils.a.a("onCreate");
        setContentView(R.layout.activity_save_log);
        w = (BydCircleTextView) findViewById(R.id.save_progress);
        x = new b(this);
        bindService(new Intent(getApplicationContext(), (Class<?>) BYDLogService.class), this.y, 1);
        Message message = new Message();
        message.what = 5;
        x.sendMessage(message);
        w.b(getString(R.string.str_save_ready), getColor(R.color.config_log_text_color));
    }

    @Override // com.byd.engineermode.ui.activity.BaseActivity, androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onDestroy() {
        super.onDestroy();
        unbindService(this.y);
        BYDLogService bYDLogService = v;
        if (bYDLogService != null) {
            bYDLogService.l(this);
            v = null;
        }
        if (w != null) {
            w = null;
        }
    }
}
