package com.byd.secretcode.activity;

import android.annotation.SuppressLint;
import android.hardware.bydauto.tbox.BYDAutoTBoxDevice;
import android.os.Bundle;
import android.os.SystemClock;
import android.widget.CompoundButton;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.byd.secretcode.R;
import com.byd.secretcode.study.SecretCodeApplication;
import com.byd.secretcode.view.BydSwitch;
import com.bydux.api.common.tools.log.BydLogHelper;

/* JADX INFO: loaded from: classes.dex */
public class TboxQxdmLogActivity extends AppCompatActivity implements CompoundButton.OnCheckedChangeListener {
    public BydSwitch c;
    public BydSwitch d;
    public BydSwitch e;
    public BydSwitch f;
    public BYDAutoTBoxDevice l;
    public SecretCodeApplication m;
    public final String b = "TboxQxdmLogActivity";
    public int g = 0;
    public int h = 1;
    public int i = 2;
    public int j = 3;
    public int k = 0;
    public long n = -1;

    public final void initView() {
        this.c = (BydSwitch) findViewById(R.id.sw_open_location);
        this.d = (BydSwitch) findViewById(R.id.sw_open_audio);
        this.e = (BydSwitch) findViewById(R.id.sw_open_network);
        this.f = (BydSwitch) findViewById(R.id.sw_open_ecall);
        this.c.b(this.m.a(this.g), true);
        this.d.b(this.m.a(this.h), true);
        this.e.b(this.m.a(this.i), true);
        this.f.b(this.m.a(this.j), true);
        this.c.setOnCheckedChangeListener(this);
        this.d.setOnCheckedChangeListener(this);
        this.e.setOnCheckedChangeListener(this);
        this.f.setOnCheckedChangeListener(this);
    }

    @Override // android.widget.CompoundButton.OnCheckedChangeListener
    @SuppressLint({"NonConstantResourceId"})
    public void onCheckedChanged(CompoundButton compoundButton, boolean z) {
        if (SystemClock.uptimeMillis() - this.n < 500) {
            Toast.makeText(this, R.string.str_qxdm_log_click, 0).show();
            return;
        }
        this.n = SystemClock.uptimeMillis();
        switch (compoundButton.getId()) {
            case R.id.sw_open_audio /* 2131296809 */:
                BydLogHelper.logI("TboxQxdmLogActivity", " sw_open_audio" + z);
                this.k = this.h;
                break;
            case R.id.sw_open_ecall /* 2131296810 */:
                BydLogHelper.logI("TboxQxdmLogActivity", " sw_open_ecall" + z);
                this.k = this.j;
                break;
            case R.id.sw_open_location /* 2131296811 */:
                BydLogHelper.logI("TboxQxdmLogActivity", " sw_open_location" + z);
                this.k = this.g;
                break;
            case R.id.sw_open_network /* 2131296812 */:
                BydLogHelper.logI("TboxQxdmLogActivity", " sw_open_network" + z);
                this.k = this.i;
                break;
        }
        BydLogHelper.logW("TboxQxdmLogActivity", "isChecked = " + z + ", logType = " + this.k);
        if (z) {
            if (this.l.setQxdmLogState(0, this.k) != 0) {
                Toast.makeText(this, R.string.str_qxdm_log_toast_fail, 0).show();
                return;
            } else {
                Toast.makeText(this, R.string.str_qxdm_log_toast_success, 0).show();
                this.m.b(true, this.k);
                return;
            }
        }
        if (this.l.setQxdmLogState(1, this.k) != 0) {
            Toast.makeText(this, R.string.str_qxdm_log_toast_closed_fail, 0).show();
        } else {
            Toast.makeText(this, R.string.str_qxdm_log_toast_closed, 0).show();
            this.m.b(false, this.k);
        }
    }

    @Override // androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        getWindow().clearFlags(1024);
        super.onCreate(bundle);
        setContentView(R.layout.activity_tbox_qxdm_log);
        this.l = BYDAutoTBoxDevice.getInstance(getApplicationContext());
        this.m = new SecretCodeApplication();
        initView();
    }

    @Override // androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onDestroy() {
        super.onDestroy();
    }

    @Override // androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onStart() {
        super.onStart();
    }

    @Override // androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onStop() {
        super.onStop();
    }
}
