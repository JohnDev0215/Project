package com.byd.digitalkey.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.RadioButton;
import b.b.j.c;
import com.byd.digitalkey.R$id;
import com.byd.digitalkey.R$layout;
import com.bydux.api.common.views.window.BydActivity;

/* JADX INFO: loaded from: classes2.dex */
public class TestDigitalDeviceApiActivity extends BydActivity implements CompoundButton.OnCheckedChangeListener {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public c f3535a;

    public class a implements View.OnClickListener {
        public a() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            int iH = TestDigitalDeviceApiActivity.this.f3535a.h();
            String str = iH == 1 ? "30s" : iH == 2 ? "10min" : "无法获取";
            b.b.a.g.a.d(TestDigitalDeviceApiActivity.this.getApplicationContext(), "免钥匙时间:" + str);
        }
    }

    @Override // android.widget.CompoundButton.OnCheckedChangeListener
    public void onCheckedChanged(CompoundButton compoundButton, boolean z) {
        int id = compoundButton.getId();
        if (id == R$id.radio_query_owner_key) {
            this.f3535a.u(1);
            return;
        }
        if (id == R$id.radio_find_key) {
            this.f3535a.u(2);
            return;
        }
        if (id == R$id.radio_30_s) {
            this.f3535a.y(1);
            return;
        }
        if (id == R$id.radio_10_min) {
            this.f3535a.y(2);
        } else if (id == R$id.radio_start_paring) {
            this.f3535a.z(1);
        } else if (id == R$id.radio_cancel_paring) {
            this.f3535a.z(2);
        }
    }

    @Override // com.bydux.api.common.views.window.BydActivity, androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R$layout.activity_test_digital_device_api);
        c cVarI = c.i(this);
        this.f3535a = cVarI;
        int iK = cVarI.k();
        RadioButton radioButton = (RadioButton) findViewById(R$id.radio_dan);
        RadioButton radioButton2 = (RadioButton) findViewById(R$id.radio_shuan);
        if (iK == 0) {
            radioButton.setChecked(true);
        } else if (iK == 1) {
            radioButton2.setChecked(true);
        }
        ((RadioButton) findViewById(R$id.radio_query_owner_key)).setOnCheckedChangeListener(this);
        ((RadioButton) findViewById(R$id.radio_find_key)).setOnCheckedChangeListener(this);
        ((RadioButton) findViewById(R$id.radio_30_s)).setOnCheckedChangeListener(this);
        ((RadioButton) findViewById(R$id.radio_10_min)).setOnCheckedChangeListener(this);
        ((RadioButton) findViewById(R$id.radio_start_paring)).setOnCheckedChangeListener(this);
        ((RadioButton) findViewById(R$id.radio_cancel_paring)).setOnCheckedChangeListener(this);
        findViewById(R$id.btn_get_time_ti).setOnClickListener(new a());
    }
}
