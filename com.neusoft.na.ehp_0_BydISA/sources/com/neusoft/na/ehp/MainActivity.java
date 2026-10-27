package com.neusoft.na.ehp;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.os.IBinder;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.Switch;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.neusoft.dlt.LogService;
import com.neusoft.na.ehp.manager.NativeManager;
import com.neusoft.na.ehp.utils.FileUtil;
import java.io.File;
import java.util.concurrent.Executors;
import org.java_websocket.extensions.ExtensionRequestData;

/* JADX INFO: loaded from: classes.dex */
public class MainActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";
    private ServiceConnection connection = new AnonymousClass1();
    private Switch mDbuSwitch;
    private NativeManager nativeManager;

    @Override // androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_main);
        requestPermission();
        findViewById(R.id.btn_permission).setOnClickListener(new View.OnClickListener() { // from class: com.neusoft.na.ehp.MainActivity$$ExternalSyntheticLambda0
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                this.f$0.m43lambda$onCreate$0$comneusoftnaehpMainActivity(view);
            }
        });
        findViewById(R.id.btn_start).setOnClickListener(new View.OnClickListener() { // from class: com.neusoft.na.ehp.MainActivity$$ExternalSyntheticLambda5
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                this.f$0.m44lambda$onCreate$1$comneusoftnaehpMainActivity(view);
            }
        });
        findViewById(R.id.btn_stop).setOnClickListener(new View.OnClickListener() { // from class: com.neusoft.na.ehp.MainActivity$$ExternalSyntheticLambda6
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                this.f$0.m46lambda$onCreate$2$comneusoftnaehpMainActivity(view);
            }
        });
        findViewById(R.id.btn_vp_open).setOnClickListener(new View.OnClickListener() { // from class: com.neusoft.na.ehp.MainActivity$$ExternalSyntheticLambda7
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                this.f$0.m47lambda$onCreate$3$comneusoftnaehpMainActivity(view);
            }
        });
        findViewById(R.id.btn_vp_close).setOnClickListener(new View.OnClickListener() { // from class: com.neusoft.na.ehp.MainActivity$$ExternalSyntheticLambda8
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                this.f$0.m48lambda$onCreate$4$comneusoftnaehpMainActivity(view);
            }
        });
        findViewById(R.id.btn_finish).setOnClickListener(new View.OnClickListener() { // from class: com.neusoft.na.ehp.MainActivity$$ExternalSyntheticLambda9
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                this.f$0.m49lambda$onCreate$5$comneusoftnaehpMainActivity(view);
            }
        });
        findViewById(R.id.btn_location).setOnClickListener(new View.OnClickListener() { // from class: com.neusoft.na.ehp.MainActivity$$ExternalSyntheticLambda10
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                this.f$0.m50lambda$onCreate$6$comneusoftnaehpMainActivity(view);
            }
        });
        findViewById(R.id.btn_toISA).setOnClickListener(new View.OnClickListener() { // from class: com.neusoft.na.ehp.MainActivity$$ExternalSyntheticLambda11
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                this.f$0.m51lambda$onCreate$7$comneusoftnaehpMainActivity(view);
            }
        });
        findViewById(R.id.btn_copy).setOnClickListener(new View.OnClickListener() { // from class: com.neusoft.na.ehp.MainActivity$$ExternalSyntheticLambda12
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                this.f$0.m52lambda$onCreate$8$comneusoftnaehpMainActivity(view);
            }
        });
        findViewById(R.id.btn_inputAdasCfg).setOnClickListener(new View.OnClickListener() { // from class: com.neusoft.na.ehp.MainActivity$$ExternalSyntheticLambda13
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                this.f$0.m53lambda$onCreate$9$comneusoftnaehpMainActivity(view);
            }
        });
        Switch r3 = (Switch) findViewById(R.id.switch_dbu);
        this.mDbuSwitch = r3;
        r3.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() { // from class: com.neusoft.na.ehp.MainActivity$$ExternalSyntheticLambda1
            @Override // android.widget.CompoundButton.OnCheckedChangeListener
            public final void onCheckedChanged(CompoundButton compoundButton, boolean z) {
                this.f$0.m45lambda$onCreate$10$comneusoftnaehpMainActivity(compoundButton, z);
            }
        });
        bindService(new Intent(this, (Class<?>) EhpService.class), this.connection, 1);
    }

    /* JADX INFO: renamed from: lambda$onCreate$0$com-neusoft-na-ehp-MainActivity, reason: not valid java name */
    /* synthetic */ void m43lambda$onCreate$0$comneusoftnaehpMainActivity(View view) {
        requestPermission();
    }

    /* JADX INFO: renamed from: lambda$onCreate$1$com-neusoft-na-ehp-MainActivity, reason: not valid java name */
    /* synthetic */ void m44lambda$onCreate$1$comneusoftnaehpMainActivity(View view) {
        if (Build.VERSION.SDK_INT >= 26) {
            startForegroundService(new Intent(this, (Class<?>) EhpService.class));
        } else {
            startService(new Intent(this, (Class<?>) EhpService.class));
        }
    }

    /* JADX INFO: renamed from: lambda$onCreate$2$com-neusoft-na-ehp-MainActivity, reason: not valid java name */
    /* synthetic */ void m46lambda$onCreate$2$comneusoftnaehpMainActivity(View view) {
        stopService(new Intent(this, (Class<?>) EhpService.class));
        stopService(new Intent(this, (Class<?>) LogService.class));
    }

    /* JADX INFO: renamed from: lambda$onCreate$3$com-neusoft-na-ehp-MainActivity, reason: not valid java name */
    /* synthetic */ void m47lambda$onCreate$3$comneusoftnaehpMainActivity(View view) {
        sendBroadcast(true);
    }

    /* JADX INFO: renamed from: lambda$onCreate$4$com-neusoft-na-ehp-MainActivity, reason: not valid java name */
    /* synthetic */ void m48lambda$onCreate$4$comneusoftnaehpMainActivity(View view) {
        sendBroadcast(false);
    }

    /* JADX INFO: renamed from: lambda$onCreate$5$com-neusoft-na-ehp-MainActivity, reason: not valid java name */
    /* synthetic */ void m49lambda$onCreate$5$comneusoftnaehpMainActivity(View view) {
        finish();
    }

    /* JADX INFO: renamed from: lambda$onCreate$6$com-neusoft-na-ehp-MainActivity, reason: not valid java name */
    /* synthetic */ void m50lambda$onCreate$6$comneusoftnaehpMainActivity(View view) {
        EditText editText = (EditText) findViewById(R.id.et_lat);
        EditText editText2 = (EditText) findViewById(R.id.et_lon);
        String string = editText.getText().toString();
        String string2 = editText2.getText().toString();
        if (TextUtils.isEmpty(string) || TextUtils.isEmpty(string2)) {
            Toast.makeText(this, "请输入数据", 0).show();
            return;
        }
        SharedPreferences.Editor editorEdit = getSharedPreferences("default_location", 0).edit();
        editorEdit.putFloat("lat", Float.parseFloat(string));
        editorEdit.putFloat("lon", Float.parseFloat(string2));
        editorEdit.commit();
        Toast.makeText(this, "设置成功", 0).show();
    }

    /* JADX INFO: renamed from: lambda$onCreate$7$com-neusoft-na-ehp-MainActivity, reason: not valid java name */
    /* synthetic */ void m51lambda$onCreate$7$comneusoftnaehpMainActivity(View view) {
        startActivity(new Intent(this, (Class<?>) ISABlankDoorActivity.class));
    }

    /* JADX INFO: renamed from: lambda$onCreate$8$com-neusoft-na-ehp-MainActivity, reason: not valid java name */
    /* synthetic */ void m52lambda$onCreate$8$comneusoftnaehpMainActivity(View view) {
        copyDltLog();
    }

    /* JADX INFO: renamed from: lambda$onCreate$9$com-neusoft-na-ehp-MainActivity, reason: not valid java name */
    /* synthetic */ void m53lambda$onCreate$9$comneusoftnaehpMainActivity(View view) {
        startActivity(new Intent(this, (Class<?>) InputCfgActivity.class));
    }

    /* JADX INFO: renamed from: lambda$onCreate$10$com-neusoft-na-ehp-MainActivity, reason: not valid java name */
    /* synthetic */ void m45lambda$onCreate$10$comneusoftnaehpMainActivity(CompoundButton compoundButton, boolean z) {
        Log.i(TAG, "onCheckedChanged: " + z);
        this.nativeManager.setChecked(z);
    }

    @Override // androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    protected void onDestroy() {
        super.onDestroy();
        Log.i(TAG, "onDestroy");
        unbindService(this.connection);
    }

    /* JADX INFO: renamed from: com.neusoft.na.ehp.MainActivity$1, reason: invalid class name */
    class AnonymousClass1 implements ServiceConnection {
        AnonymousClass1() {
        }

        @Override // android.content.ServiceConnection
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            Log.i(MainActivity.TAG, "onServiceConnected: " + iBinder);
            if (iBinder == null) {
                return;
            }
            MainActivity.this.nativeManager = (NativeManager) iBinder;
            MainActivity.this.nativeManager.setActiveChangedListener(new NativeManager.IActiveChangedListener() { // from class: com.neusoft.na.ehp.MainActivity$1$$ExternalSyntheticLambda0
                @Override // com.neusoft.na.ehp.manager.NativeManager.IActiveChangedListener
                public final void onChanged(int i) {
                    this.f$0.m56lambda$onServiceConnected$0$comneusoftnaehpMainActivity$1(i);
                }
            });
            MainActivity mainActivity = MainActivity.this;
            mainActivity.updateSwitch(mainActivity.nativeManager.getActiveStatus());
            MainActivity.this.mDbuSwitch.setChecked(MainActivity.this.nativeManager.getChecked());
        }

        /* JADX INFO: renamed from: lambda$onServiceConnected$0$com-neusoft-na-ehp-MainActivity$1, reason: not valid java name */
        /* synthetic */ void m56lambda$onServiceConnected$0$comneusoftnaehpMainActivity$1(int i) {
            MainActivity.this.updateSwitch(i);
        }

        @Override // android.content.ServiceConnection
        public void onServiceDisconnected(ComponentName componentName) {
            Log.i(MainActivity.TAG, "onServiceDisconnected");
            MainActivity.this.nativeManager = null;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void updateSwitch(final int i) {
        runOnUiThread(new Runnable() { // from class: com.neusoft.na.ehp.MainActivity$$ExternalSyntheticLambda3
            @Override // java.lang.Runnable
            public final void run() {
                this.f$0.m55lambda$updateSwitch$11$comneusoftnaehpMainActivity(i);
            }
        });
    }

    /* JADX INFO: renamed from: lambda$updateSwitch$11$com-neusoft-na-ehp-MainActivity, reason: not valid java name */
    /* synthetic */ void m55lambda$updateSwitch$11$comneusoftnaehpMainActivity(int i) {
        this.mDbuSwitch.setEnabled(i != 2);
    }

    private void sendBroadcast(boolean z) {
        if (z) {
            this.nativeManager.startVp();
        } else {
            this.nativeManager.stopVP();
        }
    }

    private void requestPermission() {
        if (checkSelfPermission("android.permission.ACCESS_COARSE_LOCATION") != 0) {
            if (!checkManageAppAllFilePermission() && Build.VERSION.SDK_INT >= 30) {
                Intent intent = new Intent();
                intent.setAction("android.settings.MANAGE_APP_ALL_FILES_ACCESS_PERMISSION");
                intent.setData(Uri.parse("package:" + getPackageName()));
                startActivity(intent);
            }
            requestPermissions(new String[]{"android.permission.ACCESS_COARSE_LOCATION", "android.permission.ACCESS_FINE_LOCATION", "android.permission.WRITE_EXTERNAL_STORAGE"}, 0);
        }
    }

    private boolean checkManageAppAllFilePermission() {
        return Build.VERSION.SDK_INT < 30 || Environment.isExternalStorageManager();
    }

    private void copyDltLog() {
        Log.d(ExtensionRequestData.EMPTY_VALUE, "copyDltLog");
        Executors.newSingleThreadExecutor().submit(new Runnable() { // from class: com.neusoft.na.ehp.MainActivity$$ExternalSyntheticLambda2
            @Override // java.lang.Runnable
            public final void run() {
                this.f$0.m42lambda$copyDltLog$12$comneusoftnaehpMainActivity();
            }
        });
    }

    /* JADX INFO: renamed from: lambda$copyDltLog$12$com-neusoft-na-ehp-MainActivity, reason: not valid java name */
    /* synthetic */ void m42lambda$copyDltLog$12$comneusoftnaehpMainActivity() {
        try {
            FileUtil fileUtil = new FileUtil();
            String dbPath = FileUtil.getDbPath(this);
            String dltLogPath = fileUtil.getDltLogPath(this);
            if (new File(dbPath).exists()) {
                fileUtil.copyFolder(dltLogPath, dbPath);
                showToast("success");
            } else {
                showToast(dbPath + " is not exists");
            }
        } catch (Exception e) {
            showToast(e.getMessage());
            e.printStackTrace();
        }
    }

    private void showToast(final String str) {
        runOnUiThread(new Runnable() { // from class: com.neusoft.na.ehp.MainActivity$$ExternalSyntheticLambda4
            @Override // java.lang.Runnable
            public final void run() {
                this.f$0.m54lambda$showToast$13$comneusoftnaehpMainActivity(str);
            }
        });
    }

    /* JADX INFO: renamed from: lambda$showToast$13$com-neusoft-na-ehp-MainActivity, reason: not valid java name */
    /* synthetic */ void m54lambda$showToast$13$comneusoftnaehpMainActivity(String str) {
        Toast.makeText(this, str, 1).show();
    }
}
