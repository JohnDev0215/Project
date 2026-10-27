package com.byd.engineermode.ui.functiontest.other;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.media.AudioRecord;
import android.media.AudioTrack;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import butterknife.R;
import com.byd.engineermode.ui.activity.BaseActivity;

/* JADX INFO: loaded from: classes.dex */
public class AudioLoopbackActivity extends BaseActivity {
    int A;
    int B;
    AudioRecord C;
    AudioTrack D;
    private Thread v;
    private boolean w;
    private Button y;
    private TextView z;
    boolean x = false;
    private int E = 0;
    private int F = 0;
    private boolean G = false;
    private boolean H = false;
    private BroadcastReceiver I = new a();

    class a extends BroadcastReceiver {
        a() {
        }

        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            if (intent.hasExtra("state")) {
                if (intent.getIntExtra("state", 0) == 0) {
                    AudioLoopbackActivity.this.G = true;
                    if (AudioLoopbackActivity.this.G) {
                        AudioLoopbackActivity.P(AudioLoopbackActivity.this);
                        if (AudioLoopbackActivity.this.E >= 2 && AudioLoopbackActivity.this.G && AudioLoopbackActivity.this.H) {
                            AudioLoopbackActivity.this.y.setEnabled(true);
                        }
                    }
                    AudioLoopbackActivity.this.y.setEnabled(false);
                    AudioLoopbackActivity.this.z.setText(R.string.Earphone_no);
                    return;
                }
                if (intent.getIntExtra("state", 0) == 1) {
                    AudioLoopbackActivity.this.H = true;
                    if (AudioLoopbackActivity.this.H) {
                        AudioLoopbackActivity.V(AudioLoopbackActivity.this);
                        if (AudioLoopbackActivity.this.F >= 2 && AudioLoopbackActivity.this.G && AudioLoopbackActivity.this.H) {
                            AudioLoopbackActivity.this.y.setEnabled(true);
                        }
                    }
                    AudioLoopbackActivity.this.z.setText(R.string.Earphone_yes);
                    AudioLoopbackActivity.this.y.setEnabled(true);
                }
            }
        }
    }

    class b implements Runnable {
        b() {
        }

        @Override // java.lang.Runnable
        public void run() {
            try {
                AudioLoopbackActivity audioLoopbackActivity = AudioLoopbackActivity.this;
                byte[] bArr = new byte[audioLoopbackActivity.A];
                audioLoopbackActivity.C.startRecording();
                AudioLoopbackActivity.this.D.play();
                while (true) {
                    AudioLoopbackActivity audioLoopbackActivity2 = AudioLoopbackActivity.this;
                    if (!audioLoopbackActivity2.x) {
                        audioLoopbackActivity2.D.stop();
                        AudioLoopbackActivity.this.C.stop();
                        return;
                    } else {
                        int i = audioLoopbackActivity2.C.read(bArr, 0, audioLoopbackActivity2.A);
                        byte[] bArr2 = new byte[i];
                        System.arraycopy(bArr, 0, bArr2, 0, i);
                        AudioLoopbackActivity.this.D.write(bArr2, 0, i);
                    }
                }
            } catch (Throwable unused) {
            }
        }
    }

    static /* synthetic */ int P(AudioLoopbackActivity audioLoopbackActivity) {
        int i = audioLoopbackActivity.E;
        audioLoopbackActivity.E = i + 1;
        return i;
    }

    static /* synthetic */ int V(AudioLoopbackActivity audioLoopbackActivity) {
        int i = audioLoopbackActivity.F;
        audioLoopbackActivity.F = i + 1;
        return i;
    }

    private void W() {
        this.A = AudioRecord.getMinBufferSize(44100, 16, 2);
        this.B = AudioTrack.getMinBufferSize(44100, 4, 2);
        Log.i("TAG", "recBufSize = " + this.A + ", playBufSize = " + this.B);
        this.C = new AudioRecord(1, 44100, 16, 2, this.A);
        AudioTrack audioTrack = new AudioTrack(0, 44100, 4, 2, this.B, 1);
        this.D = audioTrack;
        audioTrack.setStereoVolume(0.5f, 0.5f);
    }

    @Override // com.byd.engineermode.ui.activity.BaseActivity
    public void I() {
        if (this.w) {
            this.w = false;
            this.x = false;
            AudioRecord audioRecord = this.C;
            if (audioRecord != null) {
                audioRecord.release();
                this.C = null;
            }
            AudioTrack audioTrack = this.D;
            if (audioTrack != null) {
                audioTrack.release();
                this.D = null;
            }
            this.v = null;
            super.I();
        }
    }

    @Override // com.byd.engineermode.ui.activity.BaseActivity
    public void J() {
        if (this.w) {
            this.w = false;
            this.x = false;
            AudioRecord audioRecord = this.C;
            if (audioRecord != null) {
                audioRecord.release();
                this.C = null;
            }
            AudioTrack audioTrack = this.D;
            if (audioTrack != null) {
                audioTrack.release();
                this.D = null;
            }
            this.v = null;
            super.J();
        }
    }

    @Override // com.byd.engineermode.ui.activity.BaseActivity
    public void K() {
        AudioRecord audioRecord = this.C;
        if (audioRecord != null) {
            audioRecord.stop();
            this.C.startRecording();
        }
        AudioTrack audioTrack = this.D;
        if (audioTrack != null) {
            audioTrack.stop();
            this.D.play();
        }
        this.E = 0;
        this.F = 0;
        this.G = false;
        this.H = false;
        this.y.setEnabled(false);
    }

    public void OnButtonClick(View view) {
        if (view.getId() == R.id.test_pass_btn) {
            J();
            return;
        }
        if (view.getId() == R.id.test_retry_btn) {
            androidx.core.app.a.k(this, new String[]{"android.permission.WRITE_EXTERNAL_STORAGE", "android.permission.READ_EXTERNAL_STORAGE", "android.permission.MOUNT_UNMOUNT_FILESYSTEMS", "android.permission.SYSTEM_ALERT_WINDOW", "android.permission.RECORD_AUDIO"}, 0);
            K();
        } else if (view.getId() == R.id.test_fail_btn) {
            I();
        }
    }

    @Override // com.byd.engineermode.ui.activity.BaseActivity, androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_audio_loopback);
        Button button = (Button) findViewById(R.id.test_pass_btn);
        this.y = button;
        button.setEnabled(false);
        this.z = (TextView) findViewById(R.id.hint);
        W();
        this.x = true;
        this.w = true;
        Thread thread = new Thread(new b());
        this.v = thread;
        thread.start();
    }

    @Override // com.byd.engineermode.ui.activity.BaseActivity, androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(this.I);
    }

    @Override // androidx.fragment.app.FragmentActivity, android.app.Activity
    protected void onResume() {
        super.onResume();
        IntentFilter intentFilter = new IntentFilter();
        intentFilter.addAction("android.intent.action.HEADSET_PLUG");
        registerReceiver(this.I, intentFilter);
    }

    @Override // androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    protected void onStop() {
        this.x = false;
        AudioRecord audioRecord = this.C;
        if (audioRecord != null) {
            audioRecord.release();
            this.C = null;
        }
        AudioTrack audioTrack = this.D;
        if (audioTrack != null) {
            audioTrack.release();
            this.D = null;
        }
        this.v = null;
        super.onStop();
    }
}
