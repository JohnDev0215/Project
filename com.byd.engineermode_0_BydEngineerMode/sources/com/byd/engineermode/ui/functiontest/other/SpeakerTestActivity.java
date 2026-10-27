package com.byd.engineermode.ui.functiontest.other;

import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import butterknife.R;
import com.byd.engineermode.ui.activity.BaseActivity;

/* JADX INFO: loaded from: classes.dex */
public class SpeakerTestActivity extends BaseActivity {
    private MediaPlayer v;
    private AudioManager w;
    private int x;
    private int y;

    private final class a implements MediaPlayer.OnPreparedListener {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        private int f2480a;

        public a(int i) {
            this.f2480a = i;
        }

        @Override // android.media.MediaPlayer.OnPreparedListener
        public void onPrepared(MediaPlayer mediaPlayer) {
            SpeakerTestActivity.this.v.start();
            if (this.f2480a > 0) {
                SpeakerTestActivity.this.v.seekTo(this.f2480a);
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

    @Override // com.byd.engineermode.ui.activity.BaseActivity
    public void K() {
        N(0);
        super.K();
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

    @Override // com.byd.engineermode.ui.activity.BaseActivity, androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.speaker_test);
        this.v = new MediaPlayer();
        AudioManager audioManager = (AudioManager) getSystemService("audio");
        this.w = audioManager;
        this.x = audioManager.getStreamMaxVolume(3);
        this.y = this.w.getStreamVolume(3);
        this.w.setStreamVolume(3, this.x / 2, 0);
        N(0);
    }

    @Override // androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    protected void onStop() {
        this.w.setStreamVolume(3, this.y, 0);
        this.w.setMode(0);
        MediaPlayer mediaPlayer = this.v;
        if (mediaPlayer != null) {
            mediaPlayer.release();
            this.v = null;
        }
        super.onStop();
    }
}
