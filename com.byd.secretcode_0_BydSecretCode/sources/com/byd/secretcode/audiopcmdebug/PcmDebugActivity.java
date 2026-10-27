package com.byd.secretcode.audiopcmdebug;

import android.app.Activity;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.os.SystemProperties;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import androidx.core.app.ActivityCompat;
import com.byd.secretcode.R;
import com.byd.secretcode.audiopcmdebug.PcmService;
import com.bydux.api.common.tools.log.BydLogHelper;

/* JADX INFO: loaded from: classes.dex */
public class PcmDebugActivity extends Activity implements View.OnClickListener, RadioGroup.OnCheckedChangeListener {
    public RadioGroup b;
    public RadioButton c;
    public Button d;
    public Button e;
    public Button f;
    public PcmService g;
    public RadioButton h;
    public RadioButton i;
    public ServiceConnection j = new a();

    public class a implements ServiceConnection {
        public a() {
        }

        @Override // android.content.ServiceConnection
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            BydLogHelper.logD("PcmDebugActivity", "MediaTestActivity onServiceConnected");
            PcmDebugActivity.this.g = ((PcmService.g) iBinder).a();
            BydLogHelper.logD("PcmDebugActivity", "onServiceConnected mPlayService = " + PcmDebugActivity.this.g + ", " + SystemProperties.getBoolean("audio.pcm.debug.write", false) + ", " + SystemProperties.getBoolean("audio.pcm.debug.read", false));
            if (SystemProperties.getBoolean("audio.pcm.debug.write", false)) {
                PcmDebugActivity.this.h.setChecked(true);
            } else if (SystemProperties.getBoolean("audio.pcm.debug.read", false)) {
                PcmDebugActivity.this.i.setChecked(true);
            } else {
                PcmDebugActivity.this.c.setChecked(true);
            }
        }

        @Override // android.content.ServiceConnection
        public void onServiceDisconnected(ComponentName componentName) {
            BydLogHelper.logD("PcmDebugActivity", "MediaTestActivity onServiceDisconnected");
        }
    }

    @Override // android.widget.RadioGroup.OnCheckedChangeListener
    public void onCheckedChanged(RadioGroup radioGroup, int i) {
        switch (i) {
            case R.id.catch_play_btn /* 2131296401 */:
                PcmService pcmService = this.g;
                if (pcmService != null) {
                    pcmService.y();
                }
                this.d.setText("Can Catch Playback PCM");
                break;
            case R.id.catch_record_btn /* 2131296402 */:
                PcmService pcmService2 = this.g;
                if (pcmService2 != null) {
                    pcmService2.z();
                }
                this.d.setText("Can Catch Record PCM");
                break;
            case R.id.clear_pcm_btn /* 2131296424 */:
                PcmService pcmService3 = this.g;
                if (pcmService3 != null) {
                    pcmService3.l();
                }
                this.d.setText("Clear Remain AudioData");
                break;
        }
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.enter_mediatest_btn /* 2131296503 */:
                Intent intent = new Intent();
                BydLogHelper.logD("PcmDebugActivity", "press enter_mediatest_btn");
                intent.setComponent(new ComponentName(this, (Class<?>) MediaPlayActivity.class));
                startActivity(intent);
                break;
            case R.id.enter_mictest_btn /* 2131296504 */:
                Intent intent2 = new Intent();
                BydLogHelper.logD("PcmDebugActivity", "press enter_mictest_btn");
                intent2.setComponent(new ComponentName(this, (Class<?>) TestMicActivity.class));
                startActivity(intent2);
                break;
        }
    }

    @Override // android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_pcm_debug);
        Button button = (Button) findViewById(R.id.enter_mediatest_btn);
        this.e = button;
        button.setOnClickListener(this);
        Button button2 = (Button) findViewById(R.id.enter_mictest_btn);
        this.f = button2;
        button2.setOnClickListener(this);
        RadioGroup radioGroup = (RadioGroup) findViewById(R.id.catch_pcm_group);
        this.b = radioGroup;
        radioGroup.setOnCheckedChangeListener(this);
        this.h = (RadioButton) findViewById(R.id.catch_play_btn);
        this.i = (RadioButton) findViewById(R.id.catch_record_btn);
        this.c = (RadioButton) findViewById(R.id.clear_pcm_btn);
        this.d = (Button) findViewById(R.id.debug_pcm_btn);
        bindService(new Intent(this, (Class<?>) PcmService.class), this.j, 1);
        ActivityCompat.m(this, new String[]{"android.permission.WRITE_EXTERNAL_STORAGE", "android.permission.READ_EXTERNAL_STORAGE", "android.permission.MOUNT_UNMOUNT_FILESYSTEMS", "android.permission.SYSTEM_ALERT_WINDOW", "android.permission.RECORD_AUDIO"}, 0);
    }

    @Override // android.app.Activity
    public void onDestroy() {
        super.onDestroy();
        ServiceConnection serviceConnection = this.j;
        if (serviceConnection != null) {
            unbindService(serviceConnection);
        }
    }
}
