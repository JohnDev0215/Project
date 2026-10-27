package com.byd.ota.view;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.hardware.bydauto.ota.AbsMessageCallback;
import android.hardware.bydauto.ota.BYDAutoOtaDevice;
import android.hardware.bydauto.ota.IUpdateListener;
import android.hardware.bydauto.ota.tsimpl.OtaUpgradeManager;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.os.SystemProperties;
import android.text.TextUtils;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.animation.LinearInterpolator;
import android.widget.EditText;
import android.widget.TextView;
import com.byd.ota.R;

/* JADX INFO: loaded from: classes.dex */
public class OtaMasterActivity extends BaseActivity {
    public OtaUpgradeManager S3;
    public j T3;
    public i U3;
    public EditText Z3;
    public TextView a4;
    public TextView b4;
    public TextView c4;
    public TextView d4;
    public TextView e4;
    public TextView f4;
    public View g4;
    public int h4;
    public BYDAutoOtaDevice i4;
    public boolean j4;
    public boolean k4;
    public String R3 = "MainActivity";
    public int V3 = 1;
    public int W3 = 0;
    public int X3 = 1;
    public int Y3 = 0;

    public class a implements View.OnClickListener {
        public a() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            Log.i("MainActivity", "button onClick ................" + hashCode());
            OtaMasterActivity otaMasterActivity = OtaMasterActivity.this;
            otaMasterActivity.S3.registerListener(otaMasterActivity.T3);
        }
    }

    public class b implements View.OnClickListener {
        public b() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            Log.i("MainActivity", "button2 onClick ................");
            OtaMasterActivity otaMasterActivity = OtaMasterActivity.this;
            otaMasterActivity.S3.unregisterCallback(otaMasterActivity.T3);
            OtaMasterActivity.this.G();
        }
    }

    public class c implements View.OnClickListener {
        public c() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            String string = OtaMasterActivity.this.Z3.getText().toString();
            OtaMasterActivity otaMasterActivity = OtaMasterActivity.this;
            otaMasterActivity.W3 = 0;
            otaMasterActivity.Y3 = 0;
            if (!TextUtils.isEmpty(string)) {
                OtaMasterActivity.this.X3 = Integer.valueOf(string).intValue();
            }
            int i2 = OtaMasterActivity.this.X3;
            if (i2 != 1) {
                SystemProperties.set("persist.sys.otamaster.test.count", String.valueOf(i2));
            }
            Log.i("MainActivity", "button3 onClick ............. " + OtaMasterActivity.this.X3 + " " + string);
            OtaMasterActivity otaMasterActivity2 = OtaMasterActivity.this;
            otaMasterActivity2.S3.registerListener(otaMasterActivity2.T3);
            OtaMasterActivity.this.U3.sendEmptyMessage(1);
        }
    }

    public class d implements View.OnClickListener {
        public d() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            Log.i("MainActivity", "button4 onClick ................mMaxCount:" + OtaMasterActivity.this.X3);
            SystemProperties.set("persist.sys.otamaster.test.count", String.valueOf(OtaMasterActivity.this.X3));
            OtaMasterActivity.this.X3 = 0;
            Bundle bundle = new Bundle();
            bundle.putInt("stopUpdate", 1);
            OtaMasterActivity.this.S3.executeCMD(bundle);
        }
    }

    public class e implements View.OnClickListener {
        public e() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            Log.i("MainActivity", "button5 onClick ................");
            OtaMasterActivity.this.G();
        }
    }

    public class f implements Runnable {
        public f() {
        }

        @Override // java.lang.Runnable
        public void run() {
            Log.i("MainActivity", "ObjectAnimator ................");
            ObjectAnimator objectAnimatorOfFloat = ObjectAnimator.ofFloat(OtaMasterActivity.this.g4, "scaleX", 1.0f, 1.2f);
            ObjectAnimator objectAnimatorOfFloat2 = ObjectAnimator.ofFloat(OtaMasterActivity.this.g4, "scaleY", 1.0f, 1.2f);
            AnimatorSet animatorSet = new AnimatorSet();
            animatorSet.playTogether(objectAnimatorOfFloat, objectAnimatorOfFloat2);
            animatorSet.setInterpolator(new LinearInterpolator());
            animatorSet.setDuration(1000L);
            objectAnimatorOfFloat.setRepeatMode(2);
            objectAnimatorOfFloat2.setRepeatMode(2);
            objectAnimatorOfFloat.setRepeatCount(-1);
            objectAnimatorOfFloat2.setRepeatCount(-1);
            animatorSet.start();
        }
    }

    public class g implements Runnable {
        public g() {
        }

        @Override // java.lang.Runnable
        public void run() {
            Log.i("MainActivity", "mMaxCount > 1 MSG_UPDATE ................");
            OtaMasterActivity otaMasterActivity = OtaMasterActivity.this;
            otaMasterActivity.W3 = 0;
            otaMasterActivity.Y3 = 0;
            otaMasterActivity.S3.registerListener(otaMasterActivity.T3);
            OtaMasterActivity.this.U3.sendEmptyMessage(1);
        }
    }

    public class h extends AbsMessageCallback {
        public h() {
        }

        @Override // android.hardware.bydauto.ota.AbsMessageCallback
        public void onUpdateFlag(int i2) {
            Log.i("MainActivity", "exitOtaModel onUpdateFlag resultCode:" + i2);
        }
    }

    public class i extends Handler {

        public class a extends AbsMessageCallback {
            public a() {
            }

            @Override // android.hardware.bydauto.ota.AbsMessageCallback
            public void onUpdateFlag(int i2) {
                Log.i("MainActivity", "handleMessage  resultCode:" + i2);
            }
        }

        public i(Looper looper) {
            super(looper);
        }

        @Override // android.os.Handler
        public void handleMessage(Message message) {
            Bundle bundle = message.getData().getBundle("notifyBundle");
            int i2 = message.what;
            if (i2 == 1) {
                OtaMasterActivity.this.b4.setText("进度: 0");
                OtaMasterActivity.this.c4.setText("升级次数: " + OtaMasterActivity.this.V3);
                OtaMasterActivity.this.d4.setText("失败个数: " + String.valueOf(OtaMasterActivity.this.Y3));
                OtaMasterActivity otaMasterActivity = OtaMasterActivity.this;
                if (!otaMasterActivity.j4) {
                    otaMasterActivity.S3.startUpdate("");
                    return;
                }
                otaMasterActivity.i4.setUpdateFlag(1, new a());
                Bundle bundle2 = new Bundle();
                bundle2.putInt("otaModel", 1);
                Log.i("MainActivity", "handleMessage otaModel 1 ");
                OtaMasterActivity.this.S3.executeCMD(bundle2);
                try {
                    Thread.sleep(1000L);
                    Log.i("MainActivity", "handleMessage otaModel 1 result:" + OtaMasterActivity.this.i4.setOtaPower(1));
                    Thread.sleep(1000L);
                    boolean otaPower = OtaMasterActivity.this.i4.setOtaPower(2);
                    Thread.sleep(1000L);
                    Log.i("MainActivity", "handleMessage otaModel 2 result:" + otaPower);
                    OtaMasterActivity.this.S3.startUpdate("");
                    return;
                } catch (InterruptedException e2) {
                    e2.printStackTrace();
                    return;
                }
            }
            if (i2 != 2) {
                if (i2 != 4) {
                    return;
                }
                OtaMasterActivity.this.f4.setText("总进度:" + String.valueOf(message.arg1));
                return;
            }
            int i3 = bundle.getInt("type", 0);
            Log.i("MainActivity", "handleMessage MSG_NOTIFY type:" + i3);
            String string = bundle.getString("ecuId", "111");
            if (i3 == 1) {
                String string2 = bundle.getString("progress", "0");
                OtaMasterActivity.this.b4.setText("进度: " + string2);
                return;
            }
            if (i3 == 2) {
                if (bundle.getInt("ret", -1) == 0) {
                    OtaMasterActivity.this.Y3++;
                }
                Log.i("MainActivity", "handleMessage MSG_NOTIFY mFailedCount:" + OtaMasterActivity.this.Y3);
                OtaMasterActivity.this.d4.setText("失败个数: " + String.valueOf(OtaMasterActivity.this.Y3));
                return;
            }
            if (i3 == 3) {
                OtaMasterActivity.this.h4 = bundle.getInt("ecuCount", 0);
                Log.i("MainActivity", "handleMessage MSG_NOTIFY_COUNT :" + OtaMasterActivity.this.h4);
                return;
            }
            if (i3 != 4) {
                return;
            }
            OtaMasterActivity.this.a4.setText("id: " + string);
            OtaMasterActivity.this.b4.setText("进度: 0");
            TextView textView = OtaMasterActivity.this.e4;
            StringBuilder sb = new StringBuilder();
            sb.append("本次升级ecu个数: ");
            sb.append(OtaMasterActivity.this.h4);
            sb.append(" 当前升级第 ");
            OtaMasterActivity otaMasterActivity2 = OtaMasterActivity.this;
            int i4 = otaMasterActivity2.W3 + 1;
            otaMasterActivity2.W3 = i4;
            sb.append(String.valueOf(i4));
            sb.append(" 个");
            textView.setText(sb.toString());
        }
    }

    public class j implements IUpdateListener {
        public j() {
        }

        @Override // android.hardware.bydauto.ota.IUpdateListener
        public void onDownloadBackupFail(int i2) {
            Log.i("MainActivity", "UpdateListerImpl onDownloadBackupFail ................ " + i2);
        }

        @Override // android.hardware.bydauto.ota.IUpdateListener
        public void onDownloadBackupProgress(int i2) {
            Log.i("MainActivity", "UpdateListerImpl onDownloadBackupProgress ................ " + i2);
        }

        @Override // android.hardware.bydauto.ota.IUpdateListener
        public void onDownloadBackupSuccess() {
            Log.i("MainActivity", "UpdateListerImpl onDownloadBackupSuccess ................ ");
        }

        @Override // android.hardware.bydauto.ota.IUpdateListener
        public void onNotify(Bundle bundle) {
            Log.i("MainActivity", "UpdateListerImpl onNotify ................ ");
            Message messageObtain = Message.obtain();
            Bundle data = messageObtain.getData();
            messageObtain.what = 2;
            data.putBundle("notifyBundle", bundle);
            OtaMasterActivity.this.U3.sendMessage(messageObtain);
        }

        @Override // android.hardware.bydauto.ota.IUpdateListener
        public void onTBOXConnectState(int i2) {
            Log.i("MainActivity", "UpdateListerImpl onTBOXConnectState ................ " + i2);
        }

        @Override // android.hardware.bydauto.ota.IUpdateListener
        public void onTboxExportLogProgress(int i2) {
        }

        @Override // android.hardware.bydauto.ota.IUpdateListener
        public void onTboxExportLogResult(int i2) {
        }

        @Override // android.hardware.bydauto.ota.IUpdateListener
        public void onTboxUpdateProgress(int i2) {
            Log.i("MainActivity", "UpdateListerImpl onTboxUpdateProgress ................ " + i2);
        }

        @Override // android.hardware.bydauto.ota.IUpdateListener
        public void onTboxUpdateResult(int i2) {
            Log.d("MainActivity", "UpdateListerImpl onTboxUpdateResult ................ " + i2);
        }

        @Override // android.hardware.bydauto.ota.IUpdateListener
        public void onUpdateFail(int i2) {
            OtaMasterActivity otaMasterActivity = OtaMasterActivity.this;
            int i3 = otaMasterActivity.X3 - 1;
            otaMasterActivity.X3 = i3;
            SystemProperties.set("persist.sys.otamaster.test.count", String.valueOf(i3));
            OtaMasterActivity otaMasterActivity2 = OtaMasterActivity.this;
            otaMasterActivity2.W3 = 0;
            otaMasterActivity2.Y3 = 0;
            Log.i("MainActivity", "UpdateListerImpl onUpdateFail mCount:" + OtaMasterActivity.this.V3);
            OtaMasterActivity otaMasterActivity3 = OtaMasterActivity.this;
            if (otaMasterActivity3.X3 > 0) {
                otaMasterActivity3.V3++;
                otaMasterActivity3.U3.sendEmptyMessageDelayed(1, 5000L);
            } else {
                otaMasterActivity3.V3 = 1;
                otaMasterActivity3.j4 = false;
                otaMasterActivity3.S3.unregisterCallback(otaMasterActivity3.T3);
                OtaMasterActivity.this.G();
            }
        }

        @Override // android.hardware.bydauto.ota.IUpdateListener
        public void onUpdateProgress(int i2) {
            Log.i("MainActivity", "UpdateListerImpl onUpdateProgress ................ " + i2);
            Message messageObtain = Message.obtain();
            messageObtain.what = 4;
            messageObtain.arg1 = i2;
            OtaMasterActivity.this.U3.sendMessage(messageObtain);
        }

        @Override // android.hardware.bydauto.ota.IUpdateListener
        public void onUpdateSuccess() {
            OtaMasterActivity otaMasterActivity = OtaMasterActivity.this;
            int i2 = otaMasterActivity.X3 - 1;
            otaMasterActivity.X3 = i2;
            SystemProperties.set("persist.sys.otamaster.test.count", String.valueOf(i2));
            OtaMasterActivity otaMasterActivity2 = OtaMasterActivity.this;
            otaMasterActivity2.W3 = 0;
            otaMasterActivity2.Y3 = 0;
            Log.i("MainActivity", "UpdateListerImpl onUpdateSuccess mCount:" + OtaMasterActivity.this.V3);
            OtaMasterActivity otaMasterActivity3 = OtaMasterActivity.this;
            if (otaMasterActivity3.X3 > 0) {
                otaMasterActivity3.V3++;
                otaMasterActivity3.U3.sendEmptyMessageDelayed(1, 5000L);
            } else {
                otaMasterActivity3.V3 = 1;
                otaMasterActivity3.j4 = false;
                otaMasterActivity3.S3.unregisterCallback(otaMasterActivity3.T3);
                OtaMasterActivity.this.G();
            }
        }
    }

    public void G() {
        Log.i("MainActivity", "exitOtaModel  ................");
        this.k4 = true;
        Bundle bundle = new Bundle();
        bundle.putInt("otaModel", 0);
        this.S3.executeCMD(bundle);
        try {
            Thread.sleep(1000L);
        } catch (InterruptedException e2) {
            e2.printStackTrace();
        }
        this.i4.setHighVoltageCmd(7);
        this.i4.setUpdateFlag(0, new h());
    }

    @Override // com.byd.ota.view.BaseActivity, androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_ota_master);
        this.k4 = false;
        this.j4 = getIntent().getBooleanExtra("otaModel", false);
        String str = SystemProperties.get("persist.sys.otamaster.test.count", "-1");
        if (!str.equals("-1")) {
            this.X3 = Integer.valueOf(str).intValue();
        }
        Log.i("MainActivity", " 33 55 1111 mOtaModel:" + this.j4 + " count:" + str);
        this.i4 = BYDAutoOtaDevice.getInstance(this);
        this.S3 = OtaUpgradeManager.getInstance(this);
        this.T3 = new j();
        findViewById(R.id.button).setOnClickListener(new a());
        findViewById(R.id.button2).setOnClickListener(new b());
        findViewById(R.id.button3).setOnClickListener(new c());
        findViewById(R.id.button4).setOnClickListener(new d());
        findViewById(R.id.button5).setOnClickListener(new e());
        this.U3 = new i(Looper.getMainLooper());
        this.Z3 = (EditText) findViewById(R.id.editText);
        this.a4 = (TextView) findViewById(R.id.ecu_id);
        this.c4 = (TextView) findViewById(R.id.updateCount);
        this.d4 = (TextView) findViewById(R.id.failedCount);
        this.b4 = (TextView) findViewById(R.id.progress);
        this.f4 = (TextView) findViewById(R.id.all_progress);
        this.e4 = (TextView) findViewById(R.id.ecuCount);
        this.g4 = findViewById(R.id.warn_view);
        this.U3.post(new f());
        if (this.X3 > 1) {
            this.U3.postDelayed(new g(), 3000L);
        }
    }

    @Override // androidx.appcompat.app.AppCompatActivity, android.app.Activity, android.view.KeyEvent.Callback
    public boolean onKeyDown(int i2, KeyEvent keyEvent) {
        Log.w("MainActivity", "onKeyDown  " + i2 + " " + this.k4);
        if (i2 != 4 || this.k4) {
            return super.onKeyDown(i2, keyEvent);
        }
        return true;
    }
}
