package com.byd.engineermode.ui.functiontest.logs;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.os.SystemProperties;
import android.util.Log;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import butterknife.R;
import com.byd.engineermode.ui.activity.BaseActivity;

/* JADX INFO: loaded from: classes.dex */
public class GPSLogLevelConfigActivity extends BaseActivity implements RadioGroup.OnCheckedChangeListener {
    RadioButton A;
    TextView v;
    RadioGroup w;
    RadioButton x;
    RadioButton y;
    RadioButton z;

    @Override // android.widget.RadioGroup.OnCheckedChangeListener
    @SuppressLint({"NonConstantResourceId"})
    public void onCheckedChanged(RadioGroup radioGroup, int i) {
        switch (i) {
            case R.id.log_level_all_log /* 2131296555 */:
                Log.i("ConfigurationLogActivity", "persist.vendor.byd.loca.log.level：" + this.A.getText().toString());
                SystemProperties.set("persist.vendor.byd.loca.log.level", "3");
                this.v.setText(this.A.getText().toString());
                break;
            case R.id.log_level_close_log /* 2131296556 */:
                Log.i("ConfigurationLogActivity", "persist.vendor.byd.loca.log.level:" + this.x.getText().toString());
                SystemProperties.set("persist.vendor.byd.loca.log.level", "0");
                this.v.setText(this.x.getText().toString());
                break;
            case R.id.log_level_debug_log /* 2131296557 */:
                Log.i("ConfigurationLogActivity", "persist.vendor.byd.loca.log.level：" + this.z.getText().toString());
                SystemProperties.set("persist.vendor.byd.loca.log.level", "2");
                this.v.setText(this.z.getText().toString());
                break;
            case R.id.log_level_main_log /* 2131296558 */:
                Log.i("ConfigurationLogActivity", "persist.vendor.byd.loca.log.level：" + this.y.getText().toString());
                SystemProperties.set("persist.vendor.byd.loca.log.level", "1");
                this.v.setText(this.y.getText().toString());
                break;
        }
    }

    @Override // com.byd.engineermode.ui.activity.BaseActivity, androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    @SuppressLint({"ResourceAsColor", "SetTextI18n"})
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_gps_log_level_config);
        this.v = (TextView) findViewById(R.id.tv_current_log_level);
        this.w = (RadioGroup) findViewById(R.id.radioGroup);
        this.x = (RadioButton) findViewById(R.id.log_level_close_log);
        this.y = (RadioButton) findViewById(R.id.log_level_main_log);
        this.z = (RadioButton) findViewById(R.id.log_level_debug_log);
        this.A = (RadioButton) findViewById(R.id.log_level_all_log);
        this.w.setOnCheckedChangeListener(this);
        String str = SystemProperties.get("persist.vendor.byd.loca.log.level");
        Log.i("ConfigurationLogActivity", "persist.vendor.byd.loca.log.level:" + str);
        if ("0".equalsIgnoreCase(str)) {
            this.x.setChecked(true);
            this.v.setText(R.string.log_level_close_log);
            return;
        }
        if ("1".equalsIgnoreCase(str)) {
            this.y.setChecked(true);
            this.v.setText(R.string.log_level_main_log);
        } else if ("2".equalsIgnoreCase(str)) {
            this.z.setChecked(true);
            this.v.setText(R.string.log_level_debug_log);
        } else if ("3".equalsIgnoreCase(str)) {
            this.A.setChecked(true);
            this.v.setText(R.string.log_level_all_log);
        } else {
            this.v.setText(R.string.log_level_unknown);
            this.w.clearCheck();
        }
    }
}
