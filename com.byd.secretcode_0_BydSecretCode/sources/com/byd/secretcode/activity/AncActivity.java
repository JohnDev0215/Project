package com.byd.secretcode.activity;

import android.hardware.bydauto.audio.BYDAutoAudioDevice;
import android.hardware.bydauto.properties.CarSettings;
import android.hardware.bydauto.test.AbsBYDAutoTestListener;
import android.hardware.bydauto.test.BYDAutoTestDevice;
import android.hardware.bydauto.uistyleconfig.BYDAutoStudy;
import android.os.Bundle;
import android.os.IBinder;
import android.os.SystemProperties;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import com.byd.secretcode.R;
import com.bydux.api.common.tools.log.BydLogHelper;
import com.bydux.api.common.tools.util.ListUtils;
import defpackage.rg;
import java.util.ArrayList;

/* JADX INFO: loaded from: classes.dex */
public class AncActivity extends AppCompatActivity implements CompoundButton.OnCheckedChangeListener, View.OnClickListener {
    public CheckBox b;
    public CheckBox c;
    public EditText d;
    public EditText e;
    public EditText f;
    public EditText g;
    public TextView h;
    public TextView i;
    public LinearLayout j;
    public RelativeLayout k;
    public EditText l;
    public Button m;
    public AbsBYDAutoTestListener o;
    public BYDAutoTestDevice n = null;
    public BYDAutoStudy p = null;
    public BYDAutoAudioDevice q = null;
    public int r = 0;
    public int s = 0;
    public int t = 0;
    public int u = 0;
    public boolean v = false;
    public boolean w = false;
    public String x = "RU5DOTUwMjEwTkNU";
    public String y = "";

    public class a implements View.OnClickListener {
        public a() {
        }

        @Override // android.view.View.OnClickListener
        public void onClick(View view) {
            AncActivity ancActivity = AncActivity.this;
            ancActivity.y = rg.a(ancActivity.l.getText().toString());
            AncActivity ancActivity2 = AncActivity.this;
            if (ancActivity2.x.equals(ancActivity2.y)) {
                BydLogHelper.logE("AncActivity", "password is ok!");
                AncActivity.this.j.setVisibility(8);
                AncActivity.this.k.setVisibility(0);
            }
        }
    }

    public class b extends AbsBYDAutoTestListener {
        public b() {
        }

        @Override // android.hardware.bydauto.test.AbsBYDAutoTestListener
        public void onReadConfigurationWordInfo(short s, ArrayList<Byte> arrayList) {
            super.onReadConfigurationWordInfo(s, arrayList);
            BydLogHelper.logE(AbsBYDAutoTestListener.TAG, "did = " + ((int) s));
        }

        @Override // android.hardware.bydauto.test.AbsBYDAutoTestListener
        public void onWriteConfigurationWordInfo(short s, int i) {
            super.onWriteConfigurationWordInfo(s, i);
            BydLogHelper.logE(AbsBYDAutoTestListener.TAG, "did = " + ((int) s) + ", res = " + i);
        }
    }

    @Override // android.app.Activity, android.view.Window.Callback
    public boolean dispatchTouchEvent(MotionEvent motionEvent) {
        if (motionEvent.getAction() == 0) {
            View currentFocus = getCurrentFocus();
            if (i(currentFocus, motionEvent)) {
                h(currentFocus.getWindowToken());
            }
        }
        return super.dispatchTouchEvent(motionEvent);
    }

    public final String f() {
        StringBuffer stringBuffer = new StringBuffer();
        String str = SystemProperties.get("persist.vendor.byd.cfg.audio.anc.app.version");
        if (!"0".equals(str) && !"".equals(str)) {
            String[] strArrSplit = str.split(ListUtils.DEFAULT_JOIN_SEPARATOR);
            int length = strArrSplit.length;
            BydLogHelper.logE("AncActivity", "apps length = " + length);
            for (int i = 0; i < length - 2; i++) {
                if (i == length - 3) {
                    try {
                        stringBuffer.append(".");
                        stringBuffer.append(Integer.toHexString(Integer.parseInt(strArrSplit[i])).toUpperCase());
                    } catch (NumberFormatException unused) {
                    }
                } else if (i == 0) {
                    if (!"".equals(strArrSplit[i])) {
                        stringBuffer.append(Integer.toHexString(Integer.parseInt(strArrSplit[i])).toUpperCase());
                    }
                } else if (i == 1) {
                    if (!"".equals(strArrSplit[i])) {
                        String upperCase = Integer.toHexString(Integer.parseInt(strArrSplit[i])).toUpperCase();
                        if (upperCase.length() == 1) {
                            stringBuffer.append("0");
                        }
                        stringBuffer.append(upperCase);
                    }
                } else if (strArrSplit[i].length() == 1) {
                    stringBuffer.append("0");
                    stringBuffer.append(strArrSplit[i]);
                } else {
                    stringBuffer.append(strArrSplit[i]);
                }
            }
        }
        BydLogHelper.logE("AncActivity", "version = " + stringBuffer.toString());
        return stringBuffer.toString();
    }

    public final String g() {
        StringBuffer stringBuffer = new StringBuffer();
        String str = SystemProperties.get("persist.vendor.byd.cfg.audio.anc.data.version");
        if (!"0".equals(str) && !"".equals(str)) {
            String[] strArrSplit = str.split(ListUtils.DEFAULT_JOIN_SEPARATOR);
            int length = strArrSplit.length;
            BydLogHelper.logE("AncActivity", "datas length = " + length);
            for (int i = 0; i < length - 2; i++) {
                if (i == length - 3) {
                    try {
                        stringBuffer.append(".");
                        stringBuffer.append(Integer.toHexString(Integer.parseInt(strArrSplit[i])).toUpperCase());
                    } catch (NumberFormatException unused) {
                    }
                } else if (i == 0) {
                    if (!"".equals(strArrSplit[i])) {
                        stringBuffer.append(Integer.toHexString(Integer.parseInt(strArrSplit[i])).toUpperCase());
                    }
                } else if (i == 1) {
                    if (!"".equals(strArrSplit[i])) {
                        String upperCase = Integer.toHexString(Integer.parseInt(strArrSplit[i])).toUpperCase();
                        if (upperCase.length() == 1) {
                            stringBuffer.append("0");
                        }
                        stringBuffer.append(upperCase);
                    }
                } else if (strArrSplit[i].length() == 1) {
                    stringBuffer.append("0");
                    stringBuffer.append(strArrSplit[i]);
                } else {
                    stringBuffer.append(strArrSplit[i]);
                }
            }
        }
        BydLogHelper.logE("AncActivity", "version = " + stringBuffer.toString());
        return stringBuffer.toString();
    }

    public final void h(IBinder iBinder) {
        if (iBinder != null) {
            ((InputMethodManager) getSystemService("input_method")).hideSoftInputFromWindow(iBinder, 2);
        }
    }

    public final boolean i(View view, MotionEvent motionEvent) {
        if (view == null || !(view instanceof EditText)) {
            return false;
        }
        int[] iArr = {0, 0};
        view.getLocationInWindow(iArr);
        int i = iArr[0];
        int i2 = iArr[1];
        return motionEvent.getX() <= ((float) i) || motionEvent.getX() >= ((float) (view.getWidth() + i)) || motionEvent.getY() <= ((float) i2) || motionEvent.getY() >= ((float) (view.getHeight() + i2));
    }

    public final void initData() {
        this.n = BYDAutoTestDevice.getInstance(this);
        this.p = BYDAutoStudy.getInstance(this);
        this.q = BYDAutoAudioDevice.getInstance(this);
        BydLogHelper.logE("AncActivity", "app = " + SystemProperties.get("persist.vendor.byd.cfg.audio.anc.app.version") + ", data = " + SystemProperties.get("persist.vendor.byd.cfg.audio.anc.data.version") + ", String = " + getString(R.string.str_anc_app_version));
        TextView textView = this.h;
        StringBuilder sb = new StringBuilder();
        sb.append(getString(R.string.str_anc_app_version));
        sb.append(" ");
        sb.append(f());
        textView.setText(sb.toString());
        this.i.setText(getString(R.string.str_anc_data_version) + " " + g());
        int i = CarSettings.UserTableData.getInt(getContentResolver(), "ANC_1_state");
        int i2 = CarSettings.UserTableData.getInt(getContentResolver(), "ANC_2_state");
        this.r = CarSettings.UserTableData.getInt(getContentResolver(), "ANC_1_start");
        this.s = CarSettings.UserTableData.getInt(getContentResolver(), "ANC_1_end");
        this.t = CarSettings.UserTableData.getInt(getContentResolver(), "ANC_2_start");
        this.u = CarSettings.UserTableData.getInt(getContentResolver(), "ANC_2_end");
        if (i == 1) {
            this.v = true;
            this.b.setChecked(true);
        } else if (i == 0) {
            this.b.setChecked(false);
        }
        if (i2 == 1) {
            this.w = true;
            this.c.setChecked(true);
        } else if (i2 == 0) {
            this.c.setChecked(false);
        }
        if (this.r == -1) {
            this.d.setText("0");
        } else {
            this.d.setText(this.r + "");
        }
        if (this.s == -1) {
            this.e.setText("0");
        } else {
            this.e.setText(this.s + "");
        }
        if (this.t == -1) {
            this.f.setText("0");
        } else {
            this.f.setText(this.t + "");
        }
        if (this.u == -1) {
            this.g.setText("0");
        } else {
            this.g.setText(this.u + "");
        }
        this.o = new b();
        this.p.getAmplifierConfiguration();
    }

    public final void initView() {
        this.b = (CheckBox) findViewById(R.id.checkBox_1);
        this.c = (CheckBox) findViewById(R.id.checkBox_2);
        this.b.setOnCheckedChangeListener(this);
        this.c.setOnCheckedChangeListener(this);
        this.d = (EditText) findViewById(R.id.et_start_speed_1);
        this.e = (EditText) findViewById(R.id.et_end_speed_2);
        this.f = (EditText) findViewById(R.id.et_start_speed_3);
        this.g = (EditText) findViewById(R.id.et_end_speed_4);
        this.h = (TextView) findViewById(R.id.tv_anc_app_version);
        this.i = (TextView) findViewById(R.id.tv_anc_data_version);
        this.j = (LinearLayout) findViewById(R.id.ll_layout);
        this.k = (RelativeLayout) findViewById(R.id.rl_layout);
        this.j.setVisibility(0);
        this.k.setVisibility(8);
        this.l = (EditText) findViewById(R.id.et_miyao);
        Button button = (Button) findViewById(R.id.ok_btn_anc);
        this.m = button;
        button.setOnClickListener(new a());
    }

    public final void j(int i, int i2, int i3, int i4) {
        if (i == 1) {
            CarSettings.UserTableData.putInt(getContentResolver(), "ANC_1_state", i2);
            CarSettings.UserTableData.putInt(getContentResolver(), "ANC_1_start", i3);
            CarSettings.UserTableData.putInt(getContentResolver(), "ANC_1_end", i4);
        } else {
            CarSettings.UserTableData.putInt(getContentResolver(), "ANC_2_state", i2);
            CarSettings.UserTableData.putInt(getContentResolver(), "ANC_2_start", i3);
            CarSettings.UserTableData.putInt(getContentResolver(), "ANC_2_end", i4);
        }
    }

    @Override // android.widget.CompoundButton.OnCheckedChangeListener
    public void onCheckedChanged(CompoundButton compoundButton, boolean z) {
        switch (compoundButton.getId()) {
            case R.id.checkBox_1 /* 2131296414 */:
                if (!"".equals(this.d.getText().toString())) {
                    this.r = Integer.parseInt(this.d.getText().toString());
                }
                if (!"".equals(this.e.getText().toString())) {
                    this.s = Integer.parseInt(this.e.getText().toString());
                }
                BydLogHelper.logE("AncActivity", "startSpeed_1 = " + this.r + ", endSpeed_1 = " + this.s);
                if (!z) {
                    this.q.setAncSwitch(1, 0, this.r, this.s);
                    j(1, 0, this.r, this.s);
                } else if (!this.v) {
                    this.q.setAncSwitch(1, 1, this.r, this.s);
                    j(1, 1, this.r, this.s);
                }
                this.v = false;
                break;
            case R.id.checkBox_2 /* 2131296415 */:
                if (!"".equals(this.f.getText().toString())) {
                    this.t = Integer.parseInt(this.f.getText().toString());
                }
                if (!"".equals(this.g.getText().toString())) {
                    this.u = Integer.parseInt(this.g.getText().toString());
                }
                BydLogHelper.logE("AncActivity", "startSpeed_2 = " + this.t + ", endSpeed_2 = " + this.u);
                if (!z) {
                    this.q.setAncSwitch(2, 0, this.t, this.u);
                    j(2, 0, this.t, this.u);
                } else if (!this.w) {
                    this.q.setAncSwitch(2, 1, this.t, this.u);
                    j(2, 1, this.t, this.u);
                }
                this.w = false;
                break;
        }
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
    }

    @Override // androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_anc);
        initView();
        initData();
    }

    @Override // androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onResume() {
        super.onResume();
        BYDAutoTestDevice bYDAutoTestDevice = this.n;
        if (bYDAutoTestDevice != null) {
            bYDAutoTestDevice.registerListener(this.o);
        }
    }

    @Override // androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    public void onStop() {
        super.onStop();
        BYDAutoTestDevice bYDAutoTestDevice = this.n;
        if (bYDAutoTestDevice != null) {
            bYDAutoTestDevice.unregisterListener(this.o);
        }
    }
}
