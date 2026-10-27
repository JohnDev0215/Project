package com.byd.engineermode.ui.functiontest;

import android.annotation.SuppressLint;
import android.hardware.bydauto.key.AbsBYDAutoKeyListener;
import android.hardware.bydauto.key.BYDAutoKeyDevice;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.Button;
import butterknife.R;
import com.byd.engineermode.ui.activity.BaseActivity;
import java.lang.ref.WeakReference;

/* JADX INFO: loaded from: classes.dex */
public class SteeringWheelKeysTestActivity extends BaseActivity implements View.OnClickListener {
    BYDAutoKeyDevice H;
    b I;
    private Button J;
    private Button K;
    private Button L;
    private Button M;
    private Button N;
    private Button O;
    private Button P;
    private Button Q;
    private Button R;
    private Button S;
    private Button T;
    private Button U;
    private Button V;
    private boolean W;
    private boolean X;
    private boolean Y;
    private boolean Z;
    private boolean a0;
    private boolean b0;
    private boolean c0;
    private boolean d0;
    private boolean e0;
    private final String v = "SteeringWheelKeysTestActivity";
    private final int w = 1;
    private final int x = 2;
    private final int y = 7;
    private final int z = 8;
    private final int A = 9;
    private final int B = 10;
    private final int C = 11;
    private final int D = 12;
    private final int E = 13;
    private final int F = 17;
    private final int G = 18;
    private AbsBYDAutoKeyListener f0 = new a();

    class a extends AbsBYDAutoKeyListener {
        a() {
        }

        @Override // android.hardware.bydauto.key.AbsBYDAutoKeyListener
        public void onIdlerWheel(int i) {
            super.onIdlerWheel(i);
            com.byd.engineermode.utils.a.b(AbsBYDAutoKeyListener.TAG, "value == " + i);
        }

        @Override // android.hardware.bydauto.key.AbsBYDAutoKeyListener
        public void onKeyClick(int i, int i2) {
            super.onKeyClick(i, i2);
            com.byd.engineermode.utils.a.b(AbsBYDAutoKeyListener.TAG, "keyValue == " + i);
            Message message = new Message();
            message.arg1 = i;
            SteeringWheelKeysTestActivity.this.I.sendMessage(message);
        }
    }

    private class b extends Handler {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        WeakReference<SteeringWheelKeysTestActivity> f2417a;

        b(SteeringWheelKeysTestActivity steeringWheelKeysTestActivity) {
            this.f2417a = new WeakReference<>(steeringWheelKeysTestActivity);
        }

        @Override // android.os.Handler
        @SuppressLint({"SetTextI18n", "ResourceAsColor"})
        public void handleMessage(Message message) {
            if (this.f2417a.get() == null) {
            }
            int i = message.arg1;
            com.byd.engineermode.utils.a.i("MyHandler keyValue == " + i);
            if (i == 1) {
                SteeringWheelKeysTestActivity steeringWheelKeysTestActivity = SteeringWheelKeysTestActivity.this;
                steeringWheelKeysTestActivity.W = true ^ steeringWheelKeysTestActivity.W;
                if (SteeringWheelKeysTestActivity.this.W) {
                    SteeringWheelKeysTestActivity.this.J.setBackground(SteeringWheelKeysTestActivity.this.getDrawable(R.color.green));
                    return;
                } else {
                    SteeringWheelKeysTestActivity.this.J.setBackground(SteeringWheelKeysTestActivity.this.getDrawable(R.color.dark_grey));
                    return;
                }
            }
            if (i == 2) {
                SteeringWheelKeysTestActivity steeringWheelKeysTestActivity2 = SteeringWheelKeysTestActivity.this;
                steeringWheelKeysTestActivity2.Y = true ^ steeringWheelKeysTestActivity2.Y;
                if (SteeringWheelKeysTestActivity.this.Y) {
                    SteeringWheelKeysTestActivity.this.N.setBackground(SteeringWheelKeysTestActivity.this.getDrawable(R.color.green));
                    return;
                } else {
                    SteeringWheelKeysTestActivity.this.N.setBackground(SteeringWheelKeysTestActivity.this.getDrawable(R.color.dark_grey));
                    return;
                }
            }
            switch (i) {
                case 7:
                    SteeringWheelKeysTestActivity steeringWheelKeysTestActivity3 = SteeringWheelKeysTestActivity.this;
                    steeringWheelKeysTestActivity3.b0 = true ^ steeringWheelKeysTestActivity3.b0;
                    if (!SteeringWheelKeysTestActivity.this.b0) {
                        SteeringWheelKeysTestActivity.this.S.setBackground(SteeringWheelKeysTestActivity.this.getDrawable(R.color.dark_grey));
                    } else {
                        SteeringWheelKeysTestActivity.this.S.setBackground(SteeringWheelKeysTestActivity.this.getDrawable(R.color.green));
                    }
                    break;
                case 8:
                    SteeringWheelKeysTestActivity steeringWheelKeysTestActivity4 = SteeringWheelKeysTestActivity.this;
                    steeringWheelKeysTestActivity4.d0 = true ^ steeringWheelKeysTestActivity4.d0;
                    if (!SteeringWheelKeysTestActivity.this.d0) {
                        SteeringWheelKeysTestActivity.this.U.setBackground(SteeringWheelKeysTestActivity.this.getDrawable(R.color.dark_grey));
                    } else {
                        SteeringWheelKeysTestActivity.this.U.setBackground(SteeringWheelKeysTestActivity.this.getDrawable(R.color.green));
                    }
                    break;
                case 9:
                    SteeringWheelKeysTestActivity steeringWheelKeysTestActivity5 = SteeringWheelKeysTestActivity.this;
                    steeringWheelKeysTestActivity5.X = true ^ steeringWheelKeysTestActivity5.X;
                    if (!SteeringWheelKeysTestActivity.this.X) {
                        SteeringWheelKeysTestActivity.this.K.setBackground(SteeringWheelKeysTestActivity.this.getDrawable(R.color.dark_grey));
                    } else {
                        SteeringWheelKeysTestActivity.this.K.setBackground(SteeringWheelKeysTestActivity.this.getDrawable(R.color.green));
                    }
                    break;
                case 10:
                    SteeringWheelKeysTestActivity steeringWheelKeysTestActivity6 = SteeringWheelKeysTestActivity.this;
                    steeringWheelKeysTestActivity6.e0 = true ^ steeringWheelKeysTestActivity6.e0;
                    if (!SteeringWheelKeysTestActivity.this.e0) {
                        SteeringWheelKeysTestActivity.this.V.setBackground(SteeringWheelKeysTestActivity.this.getDrawable(R.color.dark_grey));
                    } else {
                        SteeringWheelKeysTestActivity.this.V.setBackground(SteeringWheelKeysTestActivity.this.getDrawable(R.color.green));
                    }
                    break;
                case 11:
                    SteeringWheelKeysTestActivity steeringWheelKeysTestActivity7 = SteeringWheelKeysTestActivity.this;
                    steeringWheelKeysTestActivity7.c0 = true ^ steeringWheelKeysTestActivity7.c0;
                    if (!SteeringWheelKeysTestActivity.this.c0) {
                        SteeringWheelKeysTestActivity.this.T.setBackground(SteeringWheelKeysTestActivity.this.getDrawable(R.color.dark_grey));
                    } else {
                        SteeringWheelKeysTestActivity.this.T.setBackground(SteeringWheelKeysTestActivity.this.getDrawable(R.color.green));
                    }
                    break;
                case 12:
                    SteeringWheelKeysTestActivity steeringWheelKeysTestActivity8 = SteeringWheelKeysTestActivity.this;
                    steeringWheelKeysTestActivity8.Z = true ^ steeringWheelKeysTestActivity8.Z;
                    if (!SteeringWheelKeysTestActivity.this.Z) {
                        SteeringWheelKeysTestActivity.this.Q.setBackground(SteeringWheelKeysTestActivity.this.getDrawable(R.color.dark_grey));
                    } else {
                        SteeringWheelKeysTestActivity.this.Q.setBackground(SteeringWheelKeysTestActivity.this.getDrawable(R.color.green));
                    }
                    break;
                case 13:
                    SteeringWheelKeysTestActivity steeringWheelKeysTestActivity9 = SteeringWheelKeysTestActivity.this;
                    steeringWheelKeysTestActivity9.a0 = true ^ steeringWheelKeysTestActivity9.a0;
                    if (!SteeringWheelKeysTestActivity.this.a0) {
                        SteeringWheelKeysTestActivity.this.R.setBackground(SteeringWheelKeysTestActivity.this.getDrawable(R.color.dark_grey));
                    } else {
                        SteeringWheelKeysTestActivity.this.R.setBackground(SteeringWheelKeysTestActivity.this.getDrawable(R.color.green));
                    }
                    break;
            }
        }
    }

    public void OnButtonClick(View view) {
        if (view.getId() == R.id.test_pass_btn) {
            J();
        } else if (view.getId() == R.id.test_retry_btn) {
            K();
        } else if (view.getId() == R.id.test_fail_btn) {
            I();
        }
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
    }

    @Override // com.byd.engineermode.ui.activity.BaseActivity, androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_steering_wheel_keys);
        this.J = (Button) findViewById(R.id.btn_avm);
        this.K = (Button) findViewById(R.id.btn_lane_departure_assist_cancel);
        this.L = (Button) findViewById(R.id.btn_adaptive_cruise_add);
        this.M = (Button) findViewById(R.id.btn_adaptive_cruise_subtract);
        this.N = (Button) findViewById(R.id.btn_screen_rotation);
        this.O = (Button) findViewById(R.id.btn_increase_vehicle_spacing);
        this.P = (Button) findViewById(R.id.btn_trolley_distance_reduction);
        this.Q = (Button) findViewById(R.id.btn_bt_phone);
        this.R = (Button) findViewById(R.id.btn_back_mute);
        this.S = (Button) findViewById(R.id.btn_mode);
        this.T = (Button) findViewById(R.id.btn_voice);
        this.U = (Button) findViewById(R.id.btn_last_song);
        this.V = (Button) findViewById(R.id.btn_nest_song);
        this.I = new b(this);
        BYDAutoKeyDevice bYDAutoKeyDevice = BYDAutoKeyDevice.getInstance(this);
        this.H = bYDAutoKeyDevice;
        if (bYDAutoKeyDevice != null) {
            bYDAutoKeyDevice.registerListener(this.f0);
        }
    }

    @Override // com.byd.engineermode.ui.activity.BaseActivity, androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    protected void onDestroy() {
        super.onDestroy();
        BYDAutoKeyDevice bYDAutoKeyDevice = this.H;
        if (bYDAutoKeyDevice != null) {
            bYDAutoKeyDevice.unregisterListener(this.f0);
        }
    }
}
