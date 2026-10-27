package com.byd.secretcode.audiopcmdebug;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.hardware.bydauto.engine.BYDAutoEngineDevice;
import android.os.Bundle;
import android.os.IBinder;
import android.os.SystemProperties;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import androidx.core.app.ActivityCompat;
import com.byd.secretcode.R;
import com.byd.secretcode.audiopcmdebug.PcmService;
import com.bydux.api.common.tools.log.BydLogHelper;
import defpackage.e7;

/* JADX INFO: loaded from: classes.dex */
public class MediaPlayActivity extends Activity implements View.OnClickListener, RadioGroup.OnCheckedChangeListener {
    public RadioGroup b;
    public Button c;
    public Button d;
    public Button e;
    public Button f;
    public Button g;
    public RadioGroup h;
    public RadioGroup i;
    public EditText j;
    public EditText k;
    public PcmService l;
    public RadioGroup m;
    public Button n;
    public Button o;
    public int p = 16000;
    public int q = 16;
    public int r = 2;
    public int s = 16000;
    public int t = 16;
    public int u = 2;
    public ServiceConnection v = new a();

    public class a implements ServiceConnection {
        public a() {
        }

        @Override // android.content.ServiceConnection
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            BydLogHelper.logD("MediaTestActivity", "onServiceConnected");
            MediaPlayActivity.this.l = ((PcmService.g) iBinder).a();
            BydLogHelper.logD("MediaTestActivity", "onServiceConnected mPlayService = " + MediaPlayActivity.this.l);
            if (MediaPlayActivity.this.l.m()) {
                MediaPlayActivity.this.g.setText(MediaPlayActivity.this.getString(R.string.stop_btn));
            } else {
                MediaPlayActivity.this.g.setText(MediaPlayActivity.this.getString(R.string.play_btn));
            }
            if (MediaPlayActivity.this.l.n()) {
                MediaPlayActivity.this.c.setText(MediaPlayActivity.this.getString(R.string.stop_btn));
            } else {
                MediaPlayActivity.this.c.setText(MediaPlayActivity.this.getString(R.string.start_btn));
            }
            if (MediaPlayActivity.this.l.o()) {
                MediaPlayActivity.this.d.setText(MediaPlayActivity.this.getString(R.string.stop_btn));
            } else {
                MediaPlayActivity.this.d.setText(MediaPlayActivity.this.getString(R.string.start_btn));
            }
            if (SystemProperties.getInt("persist.sys.diag.audio.enable", 0) == 0) {
                MediaPlayActivity.this.e.setText(MediaPlayActivity.this.getString(R.string.start_btn));
            } else {
                MediaPlayActivity.this.e.setText(MediaPlayActivity.this.getString(R.string.stop_btn));
            }
        }

        @Override // android.content.ServiceConnection
        public void onServiceDisconnected(ComponentName componentName) {
            BydLogHelper.logD("MediaTestActivity", "onServiceDisconnected");
        }
    }

    @Override // android.widget.RadioGroup.OnCheckedChangeListener
    public void onCheckedChanged(RadioGroup radioGroup, int i) {
        BydLogHelper.logD("MediaTestActivity", "onCheckedChanged *********************************");
        switch (i) {
            case R.id.bit16_btn /* 2131296345 */:
                this.r = 2;
                break;
            case R.id.bit8_btn /* 2131296346 */:
                this.r = 3;
                break;
            case R.id.channel5point1_btn /* 2131296408 */:
                this.q = 252;
                break;
            case R.id.channelequad_btn /* 2131296410 */:
                this.q = 204;
                break;
            case R.id.channelmono_btn /* 2131296411 */:
                this.q = 16;
                break;
            case R.id.channelstereo_btn /* 2131296412 */:
                this.q = 12;
                break;
            case R.id.co_channelmono_btn /* 2131296430 */:
                this.t = 16;
                break;
            case R.id.co_channelstereo_btn /* 2131296431 */:
                this.t = 12;
                break;
            case R.id.sample16_btn /* 2131296743 */:
                this.p = 16000;
                break;
            case R.id.sample48_btn /* 2131296744 */:
                this.p = 48000;
                break;
            case R.id.sample8_btn /* 2131296745 */:
                this.p = BYDAutoEngineDevice.ENGINE_SPEED_MAX;
                break;
        }
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.audiorecord_btn /* 2131296335 */:
                Button button = (Button) view;
                String string = button.getText().toString();
                BydLogHelper.logD("MediaTestActivity", "string Record = " + string);
                if (!string.equals(getString(R.string.start_btn))) {
                    button.setText(getString(R.string.start_btn));
                    this.l.C();
                } else {
                    button.setText(getString(R.string.stop_btn));
                    this.l.A(this.p, this.q, this.r);
                }
                break;
            case R.id.btn_mp_mp3 /* 2131296384 */:
                Button button2 = (Button) view;
                String string2 = button2.getText().toString();
                BydLogHelper.logD("MediaTestActivity", "mptext = " + string2);
                if (!string2.equals(getString(R.string.play_btn))) {
                    button2.setText(getString(R.string.play_btn));
                    this.l.p();
                } else {
                    button2.setText(getString(R.string.pause_btn));
                    this.l.r();
                }
                break;
            case R.id.btn_play /* 2131296385 */:
                Button button3 = (Button) view;
                String string3 = button3.getText().toString();
                BydLogHelper.logD("MediaTestActivity", "string = " + string3);
                if (!string3.equals(getString(R.string.play_btn))) {
                    button3.setText(getString(R.string.play_btn));
                    this.l.E();
                } else {
                    button3.setText(getString(R.string.stop_btn));
                    this.l.q();
                }
                break;
            case R.id.btn_setdenoise_mode /* 2131296386 */:
                String string4 = this.j.getText().toString();
                if (TextUtils.isEmpty(string4)) {
                    string4 = "-1";
                }
                this.l.x(Integer.parseInt(string4));
                break;
            case R.id.co_audiorecord_btn /* 2131296428 */:
                Button button4 = (Button) view;
                String string5 = button4.getText().toString();
                BydLogHelper.logD("MediaTestActivity", "co_string Record = " + string5);
                if (!string5.equals(getString(R.string.start_btn))) {
                    button4.setText(getString(R.string.start_btn));
                    this.l.D();
                } else {
                    button4.setText(getString(R.string.stop_btn));
                    this.l.B(this.s, this.t, this.u);
                }
                break;
            case R.id.mdlog_btn /* 2131296592 */:
                Button button5 = (Button) view;
                String string6 = button5.getText().toString();
                BydLogHelper.logD("MediaTestActivity", "string = " + string6);
                if (!string6.equals(getString(R.string.start_btn))) {
                    button5.setText(getString(R.string.start_btn));
                    this.l.k(false);
                } else {
                    button5.setText(getString(R.string.stop_btn));
                    this.l.k(true);
                }
                break;
            case R.id.save_bitmap /* 2131296747 */:
                String string7 = this.k.getText().toString();
                if (TextUtils.isEmpty(string7)) {
                    string7 = "1";
                }
                this.l.t(Long.parseLong(string7));
                break;
        }
    }

    @Override // android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_media_test);
        Button button = (Button) findViewById(R.id.btn_play);
        this.g = button;
        button.setOnClickListener(this);
        RadioGroup radioGroup = (RadioGroup) findViewById(R.id.sample_group);
        this.m = radioGroup;
        radioGroup.check(R.id.sample16_btn);
        this.m.setOnCheckedChangeListener(this);
        RadioGroup radioGroup2 = (RadioGroup) findViewById(R.id.channel_group);
        this.h = radioGroup2;
        radioGroup2.check(R.id.channelmono_btn);
        this.h.setOnCheckedChangeListener(this);
        RadioGroup radioGroup3 = (RadioGroup) findViewById(R.id.bit_group);
        this.b = radioGroup3;
        radioGroup3.check(R.id.bit16_btn);
        this.b.setOnCheckedChangeListener(this);
        Button button2 = (Button) findViewById(R.id.audiorecord_btn);
        this.c = button2;
        button2.setOnClickListener(this);
        Button button3 = (Button) findViewById(R.id.btn_setdenoise_mode);
        this.o = button3;
        button3.setOnClickListener(this);
        this.j = (EditText) findViewById(R.id.mode_edit);
        Button button4 = (Button) findViewById(R.id.btn_mp_mp3);
        this.f = button4;
        button4.setOnClickListener(this);
        bindService(new Intent(this, (Class<?>) PcmService.class), this.v, 1);
        this.k = (EditText) findViewById(R.id.frame_time);
        Button button5 = (Button) findViewById(R.id.save_bitmap);
        this.n = button5;
        button5.setOnClickListener(this);
        Button button6 = (Button) findViewById(R.id.co_audiorecord_btn);
        this.d = button6;
        button6.setOnClickListener(this);
        RadioGroup radioGroup4 = (RadioGroup) findViewById(R.id.co_channel_group);
        this.i = radioGroup4;
        radioGroup4.check(R.id.channelmono_btn);
        this.i.setOnCheckedChangeListener(this);
        Button button7 = (Button) findViewById(R.id.mdlog_btn);
        this.e = button7;
        button7.setOnClickListener(this);
        if (e7.a(this, "android.permission.WRITE_EXTERNAL_STORAGE") != 0) {
            ActivityCompat.m(this, new String[]{"android.permission.WRITE_EXTERNAL_STORAGE", "android.permission.READ_EXTERNAL_STORAGE", "android.permission.RECORD_AUDIO"}, 1);
        }
    }

    @Override // android.app.Activity
    public void onDestroy() {
        super.onDestroy();
        ServiceConnection serviceConnection = this.v;
        if (serviceConnection != null) {
            unbindService(serviceConnection);
        }
    }

    @Override // android.app.Activity
    public void onResume() {
        super.onResume();
    }
}
