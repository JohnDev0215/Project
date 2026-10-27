package com.byd.secretcode.activity;

import android.annotation.SuppressLint;
import android.hardware.bydauto.audio.BYDAutoAudioDevice;
import android.os.Bundle;
import android.text.InputFilter;
import android.text.Spanned;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.byd.secretcode.R;
import com.bydux.api.common.tools.log.BydLogHelper;

/* JADX INFO: loaded from: classes.dex */
public class SoundSourceActivity extends AppCompatActivity implements View.OnClickListener {
    public Spinner b;
    public EditText c;
    public TextView d;
    public Button e;
    public Button f;
    public Button g;
    public BYDAutoAudioDevice h;
    public int i = 0;
    public int j = 1;
    public int k = 2;
    public int l = 0;

    public class a implements InputFilter {
        public a() {
        }

        @Override // android.text.InputFilter
        public CharSequence filter(CharSequence charSequence, int i, int i2, Spanned spanned, int i3, int i4) {
            BydLogHelper.logD("setChimesVolGain source = " + charSequence.toString() + ", dest = " + ((Object) spanned));
            if (!TextUtils.isEmpty(charSequence) && spanned.length() >= 4) {
                Toast.makeText(SoundSourceActivity.this.getApplicationContext(), R.string.str_sound_source_out_range, 0).show();
                return "";
            }
            if (charSequence.equals(".") && spanned.toString().contains(".")) {
                return "";
            }
            if (charSequence.equals("-") && spanned.toString().equals("-")) {
                return "";
            }
            if (charSequence.toString().matches("[0-9-.]")) {
                return null;
            }
            Toast.makeText(SoundSourceActivity.this.getApplicationContext(), R.string.str_sound_source_input_number, 0).show();
            return "";
        }
    }

    public class b implements AdapterView.OnItemSelectedListener {
        public b() {
        }

        @Override // android.widget.AdapterView.OnItemSelectedListener
        public void onItemSelected(AdapterView<?> adapterView, View view, int i, long j) {
            BydLogHelper.logD("setOnItemSelectedListener selectedItem = " + adapterView.getItemAtPosition(i).toString() + ", type = " + i);
            SoundSourceActivity.this.l = i;
        }

        @Override // android.widget.AdapterView.OnItemSelectedListener
        public void onNothingSelected(AdapterView<?> adapterView) {
        }
    }

    public final void initView() {
        this.b = (Spinner) findViewById(R.id.spinner);
        this.c = (EditText) findViewById(R.id.et_vol_gain);
        this.d = (TextView) findViewById(R.id.tv_get_vol_gain);
        this.e = (Button) findViewById(R.id.bt_activate);
        this.f = (Button) findViewById(R.id.bt_exit);
        this.g = (Button) findViewById(R.id.bt_get_vol_gain);
        this.e.setOnClickListener(this);
        this.f.setOnClickListener(this);
        this.g.setOnClickListener(this);
        this.c.setFilters(new InputFilter[]{new a()});
        ArrayAdapter<CharSequence> arrayAdapterCreateFromResource = ArrayAdapter.createFromResource(this, R.array.spinner_items, android.R.layout.simple_spinner_item);
        arrayAdapterCreateFromResource.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        this.b.setAdapter((SpinnerAdapter) arrayAdapterCreateFromResource);
        this.b.setOnItemSelectedListener(new b());
    }

    @Override // android.view.View.OnClickListener
    @SuppressLint({"SetTextI18n", "NonConstantResourceId"})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.bt_activate /* 2131296351 */:
                if (!TextUtils.isEmpty(this.c.getText())) {
                    String string = this.c.getText().toString();
                    BydLogHelper.logD("setChimesVolGain mVolGain = " + string);
                    if ("-".equals(string) || ".".equals(string)) {
                        Toast.makeText(this, R.string.str_sound_source_input_correctly, 0).show();
                    } else {
                        float f = Float.parseFloat(string);
                        if (f < -40.0d || f > 40.0f) {
                            Toast.makeText(this, R.string.str_sound_source_input_correctly, 0).show();
                        } else if (this.h.setChimesVolGain(this.l, Float.parseFloat(string)) != 0) {
                            Toast.makeText(this, R.string.str_sound_source_set_failed, 0).show();
                        } else {
                            Toast.makeText(this, R.string.str_sound_source_set_successful, 0).show();
                        }
                    }
                }
                break;
            case R.id.bt_exit /* 2131296358 */:
                int chimesVolGain = this.h.setChimesVolGain(this.i, 0.0f);
                int chimesVolGain2 = this.h.setChimesVolGain(this.j, 0.0f);
                int chimesVolGain3 = this.h.setChimesVolGain(this.k, 0.0f);
                if (chimesVolGain == 0 && chimesVolGain2 == 0 && chimesVolGain3 == 0) {
                    Toast.makeText(this, R.string.str_sound_source_exit_successful, 0).show();
                    this.d.setText("");
                } else {
                    Toast.makeText(this, R.string.str_sound_source_exit_failed, 0).show();
                }
                break;
            case R.id.bt_get_vol_gain /* 2131296359 */:
                this.d.setText("当前设置的增益值，仪表音源：" + this.h.getChimesVolGain(this.i) + " ，雷达音源：" + this.h.getChimesVolGain(this.j) + " ，ADAS音源：" + this.h.getChimesVolGain(this.k));
                break;
        }
    }

    @Override // androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        getWindow().clearFlags(1024);
        super.onCreate(bundle);
        setContentView(R.layout.activity_sound);
        initView();
        this.h = BYDAutoAudioDevice.getInstance(getApplicationContext());
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
