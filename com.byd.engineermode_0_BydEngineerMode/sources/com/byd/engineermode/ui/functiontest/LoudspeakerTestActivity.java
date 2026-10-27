package com.byd.engineermode.ui.functiontest;

import android.hardware.bydauto.audio.BYDAutoAudioDevice;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import butterknife.R;
import com.byd.engineermode.ui.activity.BaseActivity;

/* JADX INFO: loaded from: classes.dex */
public class LoudspeakerTestActivity extends BaseActivity implements MediaPlayer.OnCompletionListener {
    private boolean A = true;
    private Button B;
    private MediaPlayer v;
    private AudioManager w;
    private int x;
    private int y;
    private BYDAutoAudioDevice z;

    private final class a implements MediaPlayer.OnPreparedListener {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        private int f2401a;

        public a(int i) {
            this.f2401a = i;
        }

        @Override // android.media.MediaPlayer.OnPreparedListener
        public void onPrepared(MediaPlayer mediaPlayer) {
            LoudspeakerTestActivity.this.v.start();
            if (this.f2401a > 0) {
                LoudspeakerTestActivity.this.v.seekTo(this.f2401a);
            }
        }
    }

    private void N(int i) {
        try {
            this.v.reset();
            this.v.setDataSource(getAssets().openFd("ceshi.mp3").getFileDescriptor());
            this.v.prepareAsync();
            this.v.setOnPreparedListener(new a(i));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void OnButtonClick(View view) {
        Log.d("MicTestActivity", "view.getId()  = " + view.getId());
        int id = view.getId();
        if (id == R.id.test_fail_btn) {
            I();
        }
        if (id == R.id.test_pass_btn) {
            J();
            return;
        }
        switch (id) {
            case R.id.btn_Loudspeaker_RL /* 2131296345 */:
                this.z.setSoundField(-9, -9);
                this.B.setText(R.string.start_play);
                break;
            case R.id.btn_Loudspeaker_Rr /* 2131296346 */:
                this.z.setSoundField(9, -9);
                this.B.setText(R.string.start_play);
                break;
            case R.id.btn_Loudspeaker_all /* 2131296347 */:
                this.z.setSoundField(0, 0);
                this.B.setText(R.string.stop_play);
                break;
            case R.id.btn_Loudspeaker_fl /* 2131296348 */:
                this.z.setSoundField(-9, 9);
                this.B.setText(R.string.start_play);
                break;
            case R.id.btn_Loudspeaker_fr /* 2131296349 */:
                this.z.setSoundField(9, 9);
                this.B.setText(R.string.start_play);
                break;
        }
    }

    @Override // android.media.MediaPlayer.OnCompletionListener
    public void onCompletion(MediaPlayer mediaPlayer) {
        if (mediaPlayer != null) {
            N(0);
        }
    }

    @Override // com.byd.engineermode.ui.activity.BaseActivity, androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_loudspeaker_test);
        this.v = new MediaPlayer();
        AudioManager audioManager = (AudioManager) getSystemService("audio");
        this.w = audioManager;
        this.x = audioManager.getStreamMaxVolume(3);
        this.y = this.w.getStreamVolume(3);
        this.w.setStreamVolume(3, this.x, 0);
        this.z = BYDAutoAudioDevice.getInstance(getApplicationContext());
        this.B = (Button) findViewById(R.id.btn_Loudspeaker_all);
        N(0);
    }
}
