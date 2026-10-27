package com.byd.logtools.activity;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import com.byd.logtools.R;
import com.byd.logtools.logservice.BYDLogService;
import com.byd.logtools.tools.BydCircleTextView;
import com.bydux.api.common.tools.log.BydLogHelper;
import java.lang.ref.WeakReference;

/* JADX INFO: loaded from: classes.dex */
public class ClearLogActivity extends BydBaseActivity implements com.byd.logtools.c.a {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private static BYDLogService f2969b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private static BydCircleTextView f2970c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    private static b f2971d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    private final ServiceConnection f2972e = new a();

    class a implements ServiceConnection {
        a() {
        }

        @Override // android.content.ServiceConnection
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            BYDLogService unused = ClearLogActivity.f2969b = ((BYDLogService.p) iBinder).a();
            ClearLogActivity.f2969b.z0(ClearLogActivity.this);
        }

        @Override // android.content.ServiceConnection
        public void onServiceDisconnected(ComponentName componentName) {
            if (ClearLogActivity.f2969b != null) {
                ClearLogActivity.f2969b.G0(ClearLogActivity.this);
                BYDLogService unused = ClearLogActivity.f2969b = null;
            }
        }
    }

    private static class b extends Handler {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        WeakReference<ClearLogActivity> f2974a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        ClearLogActivity f2975b;

        b(ClearLogActivity clearLogActivity) {
            this.f2974a = new WeakReference<>(clearLogActivity);
            this.f2975b = clearLogActivity;
        }

        @Override // android.os.Handler
        public void handleMessage(Message message) {
            if (this.f2974a.get() == null) {
                BydLogHelper.logE("outer is null");
            }
            switch (message.what) {
                case 1:
                    ClearLogActivity.f2970c.b(this.f2975b.getString(R.string.str_clear_run), -16711936);
                    break;
                case 2:
                    ClearLogActivity.f2970c.b(this.f2975b.getString(R.string.str_clear_finish), -16776961);
                    ClearLogActivity.f2971d.sendEmptyMessageDelayed(4, 1000L);
                    break;
                case 3:
                    ClearLogActivity.f2970c.b(this.f2975b.getString(R.string.str_clear_fail), -65536);
                    break;
                case 4:
                    this.f2975b.finish();
                    break;
                case 5:
                    if (ClearLogActivity.f2969b != null) {
                        BydLogHelper.logD("ClearLogActivity ClearAllLog");
                        ClearLogActivity.f2969b.a();
                    } else {
                        ClearLogActivity.f2971d.sendEmptyMessageDelayed(5, 1000L);
                    }
                    break;
                case 6:
                    BydLogHelper.logD("Clear log in background, please wait");
                    com.byd.logtools.tools.a.a().b(R.string.str_clear_Flag);
                    this.f2975b.finish();
                    break;
                default:
                    ClearLogActivity.f2970c.b(this.f2975b.getString(R.string.str_clear_ready), -1);
                    break;
            }
        }
    }

    @Override // com.byd.logtools.c.a
    public void b(int i) {
        BydLogHelper.logE("OnClearLogStatusChange status = " + i);
        f2971d.sendEmptyMessage(i);
    }

    @Override // com.byd.logtools.activity.BydBaseActivity, com.bydux.api.common.views.window.BydActivity, androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        if (i("ClearLogActivity")) {
            finish();
            return;
        }
        BydLogHelper.logD("onCreate");
        setContentView(R.layout.activity_clear_log);
        f2970c = (BydCircleTextView) findViewById(R.id.clear_progress);
        f2971d = new b(this);
        bindService(new Intent(getApplicationContext(), (Class<?>) BYDLogService.class), this.f2972e, 1);
        Message message = new Message();
        message.what = 5;
        f2971d.sendMessage(message);
        f2970c.b(getString(R.string.str_clear_ready), getColor(R.color.config_log_text_color));
    }

    @Override // com.byd.logtools.activity.BydBaseActivity, com.bydux.api.common.views.window.BydActivity, androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onDestroy() {
        super.onDestroy();
        if (i("ClearLogActivity")) {
            return;
        }
        unbindService(this.f2972e);
        BYDLogService bYDLogService = f2969b;
        if (bYDLogService != null) {
            bYDLogService.G0(this);
            f2969b = null;
        }
        if (f2970c != null) {
            f2970c = null;
        }
        if (f2971d != null) {
            f2971d = null;
        }
    }

    @Override // com.bydux.api.common.views.window.BydActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onPause() {
        super.onPause();
        if (i("ClearLogActivity")) {
            finish();
        }
    }

    @Override // com.bydux.api.common.views.window.BydActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onResume() {
        super.onResume();
        if (i("ClearLogActivity")) {
            finish();
        }
    }

    @Override // com.bydux.api.common.views.window.BydActivity, androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onStart() {
        super.onStart();
        if (i("ClearLogActivity")) {
            finish();
        }
    }

    @Override // com.bydux.api.common.views.window.BydActivity, androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onStop() {
        super.onStop();
        if (i("ClearLogActivity")) {
            finish();
        }
    }
}
