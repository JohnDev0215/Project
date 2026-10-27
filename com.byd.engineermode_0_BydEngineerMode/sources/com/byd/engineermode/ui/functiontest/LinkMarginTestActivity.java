package com.byd.engineermode.ui.functiontest;

import android.annotation.SuppressLint;
import android.hardware.bydauto.tools.BYDAutoDiagnoseToolDevice;
import android.os.Bundle;
import android.os.SystemProperties;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import butterknife.R;
import com.byd.engineermode.ui.activity.BaseActivity;

/* JADX INFO: loaded from: classes.dex */
public class LinkMarginTestActivity extends BaseActivity {
    EditText A;
    private final int B = 6;
    TextView v;
    TextView w;
    BYDAutoDiagnoseToolDevice x;
    LinearLayout y;
    RelativeLayout z;

    class a implements Runnable {
        a() {
        }

        @Override // java.lang.Runnable
        public void run() {
            Log.w("ScreenTestActivity", "getDisplayForwardLinkMarginValue =  " + LinkMarginTestActivity.this.x.getDisplayForwardLinkMarginValue(1));
        }
    }

    class b implements Runnable {
        b() {
        }

        @Override // java.lang.Runnable
        public void run() {
            Log.w("ScreenTestActivity", "getDisplayReverseLinkMarginValue =  " + LinkMarginTestActivity.this.x.getDisplayReverseLinkMarginValue(1));
        }
    }

    private String M() {
        com.byd.engineermode.utils.a.i("getForwardChannel");
        return SystemProperties.get("persist.vendor.bosch.cfg.display.forward.link.margin.val", "");
    }

    private String N() {
        com.byd.engineermode.utils.a.i("getReverseChannel");
        return SystemProperties.get("persist.vendor.bosch.cfg.display.reverse.link.margin.val", "");
    }

    @SuppressLint({"SetTextI18n", "NonConstantResourceId"})
    public void OnButtonClick(View view) {
        Log.d("ScreenTestActivity", "view.getId()  = " + view.getId());
        switch (view.getId()) {
            case R.id.bt_get_forward_channel /* 2131296343 */:
                new Thread(new a()).start();
                break;
            case R.id.bt_get_reverse_channel /* 2131296344 */:
                new Thread(new b()).start();
                break;
            case R.id.ok_btn /* 2131296605 */:
                String upperCase = this.A.getText().toString().trim().toUpperCase();
                if (upperCase != null && upperCase.isEmpty()) {
                    com.byd.engineermode.ui.dialog.a.a(this).b(R.string.offline_config_password_err);
                } else if (upperCase.length() != 6) {
                    com.byd.engineermode.ui.dialog.a.a(this).b(R.string.offline_config_password_err);
                } else {
                    boolean zEquals = upperCase.equals("654321");
                    com.byd.engineermode.utils.a.b("ScreenTestActivity", "onTextChanged password =" + upperCase + ",check =" + zEquals);
                    if (!zEquals) {
                        com.byd.engineermode.ui.dialog.a.a(this).b(R.string.offline_config_password_err);
                    } else {
                        this.A.setText("");
                        this.y.setVisibility(8);
                        this.z.setVisibility(0);
                    }
                }
                break;
            case R.id.test_fail_btn /* 2131296722 */:
                I();
                break;
            case R.id.test_pass_btn /* 2131296726 */:
                J();
                break;
        }
    }

    @Override // com.byd.engineermode.ui.activity.BaseActivity, androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    @SuppressLint({"SetTextI18n"})
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_link_margin_test);
        this.x = BYDAutoDiagnoseToolDevice.getInstance(this);
        this.v = (TextView) findViewById(R.id.tv_get_forward_channel);
        this.w = (TextView) findViewById(R.id.tv_get_reverse_channel);
        this.v.setText(getResources().getString(R.string.str_forward_channel_result) + M());
        this.w.setText(getResources().getString(R.string.str_reverse_channel_result) + N());
        this.A = (EditText) findViewById(R.id.et_password);
        this.y = (LinearLayout) findViewById(R.id.ll_password);
        this.z = (RelativeLayout) findViewById(R.id.rl_view);
        this.y.setVisibility(0);
    }

    @Override // com.byd.engineermode.ui.activity.BaseActivity, androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    protected void onDestroy() {
        super.onDestroy();
    }
}
