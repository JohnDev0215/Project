package com.byd.secretcode.activity;

import android.content.DialogInterface;
import android.hardware.bydauto.bodywork.BYDAutoBodyworkDevice;
import android.hardware.bydauto.test.AbsBYDAutoTestListener;
import android.hardware.bydauto.test.BYDAutoTestDevice;
import android.hardware.bydauto.yun.ActivateInfo;
import android.hardware.bydauto.yun.ActivateInfoV2;
import android.hardware.bydauto.yun.BYDAutoYunDevice;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.text.TextUtils;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.byd.secretcode.R;
import com.byd.secretcode.view.BydSwitch;
import defpackage.k0;
import java.util.Calendar;

/* JADX INFO: loaded from: classes.dex */
public class YunLogActivity extends AppCompatActivity {
    public TextView b;
    public TextView c;
    public TextView d;
    public TextView e;
    public TextView f;
    public BydSwitch g;
    public LinearLayout h;
    public TextView i;
    public Button j;
    public Button k;
    public TextView l;
    public TextView m;
    public TextView n;
    public AbsBYDAutoTestListener o;

    public class a implements View.OnClickListener {
        public a() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            BYDAutoYunDevice.getInstance(YunLogActivity.this).setAppActivateInfo(new ActivateInfo(ActivateInfo.ProviderType.MAP, (byte) 2, ActivateInfo.ProviderName.NeuSoft, ActivateInfo.ActiveState.ATCIVE_FAILED, System.currentTimeMillis() / 1000, System.currentTimeMillis() / 1000, ActivateInfo.NationalRegion.AE, ActivateInfo.Region.America, "com.byd.secretcode"));
        }
    }

    public class b implements View.OnClickListener {
        public b() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            BYDAutoYunDevice.getInstance(YunLogActivity.this).setAppActivateInfo(new ActivateInfoV2(1, 2, 4, 2, System.currentTimeMillis() / 1000, System.currentTimeMillis() / 1000, 41, 2, "com.byd.secretcodeV2"));
        }
    }

    public class c implements Runnable {
        public c() {
        }

        @Override // java.lang.Runnable
        public void run() {
            YunLogActivity.this.j();
        }
    }

    public class d extends AbsBYDAutoTestListener {

        public class a implements Runnable {
            public final /* synthetic */ int b;

            public a(int i) {
                this.b = i;
            }

            @Override // java.lang.Runnable
            public void run() {
                int i = this.b;
                if (i == 1) {
                    YunLogActivity yunLogActivity = YunLogActivity.this;
                    yunLogActivity.f.setText(yunLogActivity.getString(R.string.yun_log_connect, new Object[]{yunLogActivity.getString(R.string.yun_connected)}));
                } else if (i == 2) {
                    YunLogActivity yunLogActivity2 = YunLogActivity.this;
                    yunLogActivity2.f.setText(yunLogActivity2.getString(R.string.yun_log_connect, new Object[]{yunLogActivity2.getString(R.string.yun_not_connected)}));
                }
            }
        }

        public d() {
        }

        @Override // android.hardware.bydauto.test.AbsBYDAutoTestListener
        public void onCloudServiceConnectStateChanged(int i) {
            super.onCloudServiceConnectStateChanged(i);
            Log.d("YunLogActivity", "onCloudServiceConnectStateChanged:" + i);
            YunLogActivity.this.runOnUiThread(new a(i));
        }
    }

    public class e implements BydSwitch.a {
        public e() {
        }

        @Override // com.byd.secretcode.view.BydSwitch.a
        public void a(View view, MotionEvent motionEvent, boolean z) {
            if (motionEvent.getAction() != 1) {
                return;
            }
            YunLogActivity.this.k(z);
        }
    }

    public class f implements CompoundButton.OnCheckedChangeListener {

        public class a implements Runnable {
            public a() {
            }

            @Override // java.lang.Runnable
            public void run() {
                YunLogActivity.this.j();
            }
        }

        public f() {
        }

        @Override // android.widget.CompoundButton.OnCheckedChangeListener
        public void onCheckedChanged(CompoundButton compoundButton, boolean z) {
            int i = 1;
            if (z) {
                YunLogActivity yunLogActivity = YunLogActivity.this;
                yunLogActivity.e.setText(yunLogActivity.getString(R.string.yun_log_ev, new Object[]{yunLogActivity.getString(R.string.yun_ev_office)}));
                i = 2;
            } else {
                YunLogActivity yunLogActivity2 = YunLogActivity.this;
                yunLogActivity2.e.setText(yunLogActivity2.getString(R.string.yun_log_ev, new Object[]{yunLogActivity2.getString(R.string.yun_ev_test)}));
            }
            Log.d("YunLogActivity", "result:" + BYDAutoTestDevice.getInstance(YunLogActivity.this).setCloudServieEv(i));
            YunLogActivity.this.g.postDelayed(new a(), 3000L);
        }
    }

    public class g implements DialogInterface.OnClickListener {
        public final /* synthetic */ EditText b;
        public final /* synthetic */ boolean c;

        public g(EditText editText, boolean z) {
            this.b = editText;
            this.c = z;
        }

        @Override // android.content.DialogInterface.OnClickListener
        public void onClick(DialogInterface dialogInterface, int i) {
            if (!YunLogActivity.this.i(this.b)) {
                YunLogActivity yunLogActivity = YunLogActivity.this;
                Toast.makeText(yunLogActivity, yunLogActivity.getString(R.string.yun_input_pwd_fail), 0).show();
            } else {
                YunLogActivity yunLogActivity2 = YunLogActivity.this;
                Toast.makeText(yunLogActivity2, yunLogActivity2.getString(R.string.yun_input_pwd_ok), 0).show();
                YunLogActivity.this.g.setChecked(this.c);
            }
        }
    }

    public final boolean i(EditText editText) {
        String string = editText.getText().toString();
        if (TextUtils.isEmpty(string)) {
            return false;
        }
        Calendar calendar = Calendar.getInstance();
        int i = calendar.get(1);
        int i2 = calendar.get(2) + 1;
        int i3 = calendar.get(5);
        Log.d("checkPwd", "year:" + i + ",month:" + i2 + ",day:" + i3);
        return String.valueOf(i + i2 + i3).equals(string);
    }

    public final void j() {
        this.b.setText(getString(R.string.yun_log_version_name, new Object[]{BYDAutoTestDevice.getInstance(this).geCloudServiceVersion()}));
        this.c.setText(getString(R.string.yun_log_vin, new Object[]{BYDAutoBodyworkDevice.getInstance(this).getRealAutoVIN()}));
        this.d.setText(getString(R.string.yun_log_vritual_vin, new Object[]{BYDAutoBodyworkDevice.getInstance(this).getAutoVIN()}));
        int cloudServieEv = BYDAutoTestDevice.getInstance(this).getCloudServieEv();
        if (cloudServieEv == 1) {
            this.e.setText(getString(R.string.yun_log_ev, new Object[]{getString(R.string.yun_ev_test)}));
            this.g.b(false, false);
        } else if (cloudServieEv == 2) {
            this.e.setText(getString(R.string.yun_log_ev, new Object[]{getString(R.string.yun_ev_office)}));
            this.g.b(true, false);
        } else {
            this.e.setText(getString(R.string.yun_log_ev, new Object[]{getString(R.string.yun_ev_not_get)}));
            this.g.b(false, false);
        }
        boolean zIsConnectTbox = BYDAutoYunDevice.getInstance(this).isConnectTbox();
        TextView textView = this.i;
        Object[] objArr = new Object[1];
        objArr[0] = zIsConnectTbox ? getString(R.string.yun_connected) : getString(R.string.yun_not_connected);
        textView.setText(getString(R.string.yun_log_ivi_tbox, objArr));
        int cloudServiceConnectState = BYDAutoTestDevice.getInstance(this).getCloudServiceConnectState();
        if (cloudServiceConnectState == 1) {
            this.f.setText(getString(R.string.yun_log_connect, new Object[]{getString(R.string.yun_connected)}));
            this.l.setVisibility(8);
        } else if (cloudServiceConnectState == 2) {
            this.f.setText(getString(R.string.yun_log_connect, new Object[]{getString(R.string.yun_not_connected)}));
            this.l.setVisibility(0);
        } else {
            this.f.setText(getString(R.string.yun_log_connect, new Object[]{getString(R.string.yun_connect_state_not_get)}));
            this.l.setVisibility(0);
        }
        int aPN1FailReson = BYDAutoTestDevice.getInstance(this).getAPN1FailReson();
        Log.d(toString(), "APN1FailReson:" + aPN1FailReson);
        switch (aPN1FailReson) {
            case 1:
                this.l.setText(getString(R.string.yun_log_connect_fail_reason, new Object[]{"0x01 APN1拨号失败"}));
                break;
            case 2:
                this.l.setText(getString(R.string.yun_log_connect_fail_reason, new Object[]{"0x02 未获取到VIN码"}));
                break;
            case 3:
                this.l.setText(getString(R.string.yun_log_connect_fail_reason, new Object[]{"0x03 UUID未写入"}));
                break;
            case 4:
                this.l.setText(getString(R.string.yun_log_connect_fail_reason, new Object[]{"0x04 DNS域名获取失败"}));
                break;
            case 5:
                this.l.setText(getString(R.string.yun_log_connect_fail_reason, new Object[]{"0x05 注册失败"}));
                break;
            case 6:
                this.l.setText(getString(R.string.yun_log_connect_fail_reason, new Object[]{"0x06 登录失败"}));
                break;
            case 7:
                this.l.setText(getString(R.string.yun_log_connect_fail_reason, new Object[]{"0x07 联网或登录等动作执行中"}));
                break;
            default:
                this.l.setText(getString(R.string.yun_log_connect_fail_reason, new Object[]{"-1 未知原因"}));
                break;
        }
        this.m.setText(getString(R.string.yun_apn1_ip, new Object[]{BYDAutoTestDevice.getInstance(this).getAPN1Address()}));
        this.n.setText(getString(R.string.yun_uuid, new Object[]{BYDAutoTestDevice.getInstance(this).getYunUUIDStr()}));
        this.g.setOnInterceptTouchListener(new e());
        this.g.setOnCheckedChangeListener(new f());
    }

    public final void k(boolean z) {
        EditText editText = new EditText(this);
        editText.setInputType(2);
        editText.setPadding(getResources().getDimensionPixelSize(R.dimen.dp_30), getResources().getDimensionPixelSize(R.dimen.dp_10), 0, getResources().getDimensionPixelSize(R.dimen.dp_10));
        k0.a aVar = new k0.a(this);
        aVar.i(getString(R.string.yun_input_pwd_tip)).j(editText);
        aVar.g(getString(R.string.ok), new g(editText, z));
        k0 k0VarA = aVar.a();
        Window window = k0VarA.getWindow();
        if (window != null) {
            WindowManager.LayoutParams attributes = window.getAttributes();
            attributes.gravity = 17;
            attributes.x = 0;
            attributes.y = -200;
            window.setAttributes(attributes);
        }
        k0VarA.show();
    }

    @Override // androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_yun_log);
        this.b = (TextView) findViewById(R.id.tv_yun_version);
        this.c = (TextView) findViewById(R.id.tv_vin);
        this.d = (TextView) findViewById(R.id.tv_virtul_vin);
        this.e = (TextView) findViewById(R.id.tv_yun_ev);
        this.f = (TextView) findViewById(R.id.tv_yun_connect_state);
        this.l = (TextView) findViewById(R.id.tv_yun_connect_fail_reason);
        this.m = (TextView) findViewById(R.id.tv_apn1_ip);
        this.n = (TextView) findViewById(R.id.tv_yun_uuid);
        this.i = (TextView) findViewById(R.id.tv_ivi_tbox);
        this.h = (LinearLayout) findViewById(R.id.ll_switch);
        this.g = (BydSwitch) findViewById(R.id.switch_yun_ev_change);
        this.j = (Button) findViewById(R.id.app_activate_test);
        this.k = (Button) findViewById(R.id.app_activate_test_v2);
        this.j.setOnClickListener(new a());
        this.k.setOnClickListener(new b());
        new Handler(Looper.getMainLooper()).postDelayed(new c(), 1000L);
        BYDAutoTestDevice bYDAutoTestDevice = BYDAutoTestDevice.getInstance(this);
        d dVar = new d();
        this.o = dVar;
        bYDAutoTestDevice.registerListener((AbsBYDAutoTestListener) dVar);
    }

    @Override // androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onDestroy() {
        super.onDestroy();
        BYDAutoTestDevice.getInstance(this).unregisterListener(this.o);
    }
}
