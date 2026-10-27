package com.byd.engineermode.ui.functiontest;

import android.annotation.SuppressLint;
import android.hardware.bydauto.bodywork.AbsBYDAutoBodyworkListener;
import android.hardware.bydauto.bodywork.BYDAutoBodyworkDevice;
import android.media.AudioDeviceInfo;
import android.media.AudioFormat;
import android.media.AudioManager;
import android.media.AudioRecord;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import butterknife.R;
import com.byd.engineermode.ui.activity.BaseActivity;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

/* JADX INFO: loaded from: classes.dex */
public class MicTestActivity extends BaseActivity {
    private static int v = 16000;
    private static int w = 12;
    private static int x = 2;
    private AudioDeviceInfo[] B;
    private AudioManager C;
    private Button D;
    private Button E;
    private String F;
    private String G;
    private String H;
    private String I;
    BYDAutoBodyworkDevice R;
    AbsBYDAutoBodyworkListener S;
    private MediaPlayer Y;
    private int Z;
    private int a0;
    private String y;
    private AudioRecord z;
    private int A = 0;
    private boolean J = false;
    private boolean K = true;
    private boolean L = true;
    private boolean M = true;
    private boolean N = false;
    private boolean O = true;
    private boolean P = true;
    private boolean Q = true;
    private int T = 0;
    private int U = 1;
    private int V = 2;
    private int W = 3;
    private int X = 4;

    class a extends AbsBYDAutoBodyworkListener {
        a() {
        }

        @Override // android.hardware.bydauto.bodywork.AbsBYDAutoBodyworkListener
        public void onPowerLevelChanged(int i) {
            super.onPowerLevelChanged(i);
            Log.d("AbsBYDAutoBodyworkListener", "onPowerLevelChanged  level = " + i);
            if (i == MicTestActivity.this.U) {
                MicTestActivity.this.X();
            }
        }
    }

    class b implements MediaPlayer.OnCompletionListener {
        b() {
        }

        @Override // android.media.MediaPlayer.OnCompletionListener
        public void onCompletion(MediaPlayer mediaPlayer) {
            Log.i("MicTestActivity", "onCompletion : 已播放完成");
        }
    }

    public class c extends Thread {

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        private String f2405b;

        /* JADX INFO: renamed from: c, reason: collision with root package name */
        private String f2406c;

        public c(String str, String str2) {
            this.f2405b = str;
            this.f2406c = str2;
        }

        @Override // java.lang.Thread, java.lang.Runnable
        public void run() {
            super.run();
            MicTestActivity.this.Z(this.f2405b);
            MicTestActivity.this.R(this.f2405b, this.f2406c);
        }
    }

    private final class d implements MediaPlayer.OnPreparedListener {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        private int f2408a;

        public d(int i) {
            this.f2408a = i;
        }

        @Override // android.media.MediaPlayer.OnPreparedListener
        public void onPrepared(MediaPlayer mediaPlayer) {
            MicTestActivity.this.Y.start();
            if (this.f2408a > 0) {
                MicTestActivity.this.Y.seekTo(this.f2408a);
            }
        }
    }

    private void M(FileOutputStream fileOutputStream, long j, long j2, long j3, int i, long j4) throws IOException {
        fileOutputStream.write(new byte[]{82, 73, 70, 70, (byte) (j2 & 255), (byte) ((j2 >> 8) & 255), (byte) ((j2 >> 16) & 255), (byte) ((j2 >> 24) & 255), 87, 65, 86, 69, 102, 109, 116, 32, 16, 0, 0, 0, 1, 0, (byte) i, 0, (byte) (j3 & 255), (byte) ((j3 >> 8) & 255), (byte) ((j3 >> 16) & 255), (byte) ((j3 >> 24) & 255), (byte) (j4 & 255), (byte) ((j4 >> 8) & 255), (byte) ((j4 >> 16) & 255), (byte) ((j4 >> 24) & 255), 4, 0, 16, 0, 100, 97, 116, 97, (byte) (j & 255), (byte) ((j >> 8) & 255), (byte) ((j >> 16) & 255), (byte) ((j >> 24) & 255)}, 0, 44);
    }

    private void P() {
        this.C.setStreamVolume(3, this.a0, 0);
        this.C.setMode(0);
        MediaPlayer mediaPlayer = this.Y;
        if (mediaPlayer != null) {
            mediaPlayer.release();
            this.Y = null;
        }
    }

    private void Q() {
        if (this.z != null) {
            System.out.println("stopRecordVR");
            this.z.stop();
            this.z = null;
        }
    }

    @SuppressLint({"WrongConstant"})
    private void S() {
        this.Z = this.C.getStreamMaxVolume(3);
        this.a0 = this.C.getStreamVolume(3);
        this.C.setStreamVolume(3, this.Z, 0);
    }

    @SuppressLint({"WrongConstant"})
    private void T() {
        Log.d("MicTestActivity", "creatAudioRecord-->bufferSizeInBytes=" + this.A);
        this.A = AudioRecord.getMinBufferSize(v, w, x);
        if (checkSelfPermission("android.permission.RECORD_AUDIO") != 0) {
            requestPermissions(new String[]{"android.permission.RECORD_AUDIO"}, 1);
        }
        this.z = new AudioRecord.Builder().setAudioSource(0).setAudioFormat(new AudioFormat.Builder().setChannelIndexMask(255).setSampleRate(v).setEncoding(x).build()).setBufferSizeInBytes(this.A * 4).build();
    }

    private void U() {
        this.D = (Button) findViewById(R.id.btn_mic1_record);
        this.E = (Button) findViewById(R.id.btn_mic1_play);
        this.C = (AudioManager) getSystemService("audio");
        T();
        S();
        this.S = new a();
        BYDAutoBodyworkDevice bYDAutoBodyworkDevice = BYDAutoBodyworkDevice.getInstance(this);
        this.R = bYDAutoBodyworkDevice;
        bYDAutoBodyworkDevice.registerListener(this.S);
    }

    private void W(String str) {
        MediaPlayer mediaPlayer = new MediaPlayer();
        this.Y = mediaPlayer;
        try {
            mediaPlayer.reset();
            this.Y.setDataSource(str);
            this.Y.prepareAsync();
            this.Y.setOnPreparedListener(new d(0));
            this.Y.setOnCompletionListener(new b());
            this.Y.start();
            this.Y.seekTo(0);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void OnButtonClick(View view) {
        Log.d("MicTestActivity", "view.getId()  = " + view.getId());
        switch (view.getId()) {
            case R.id.btn_mic1_play /* 2131296389 */:
                if (this.N) {
                    this.E.setText(R.string.start_play);
                    Y();
                } else {
                    this.E.setText(R.string.stop_play);
                    Y();
                    if (this.J) {
                        this.D.setText(R.string.start_record);
                        X();
                        this.J = false;
                    }
                    W(this.F);
                }
                this.N = !this.N;
                break;
            case R.id.btn_mic1_record /* 2131296390 */:
                if (this.J) {
                    this.D.setText(R.string.start_record);
                    X();
                } else {
                    this.D.setText(R.string.stop_record);
                    X();
                    if (this.N) {
                        this.E.setText(R.string.start_play);
                        Y();
                        this.N = false;
                    }
                    V(this.F);
                }
                this.J = !this.J;
                break;
            case R.id.test_fail_btn /* 2131296722 */:
                Y();
                X();
                I();
                break;
            case R.id.test_pass_btn /* 2131296726 */:
                Y();
                X();
                J();
                break;
            case R.id.test_retry_btn /* 2131296729 */:
                Y();
                this.E.setText(R.string.start_play);
                this.D.setText(R.string.start_record);
                X();
                break;
        }
    }

    public void R(String str, String str2) {
        FileInputStream fileInputStream;
        int i = v;
        long j = i;
        long j2 = ((i * 16) * 8) / 8;
        byte[] bArr = new byte[this.A];
        try {
            fileInputStream = new FileInputStream(str);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            fileInputStream = null;
        }
        FileInputStream fileInputStream2 = fileInputStream;
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(str2);
            long size = fileInputStream2.getChannel().size();
            try {
                M(fileOutputStream, size, size + 36, j, 8, j2);
                while (fileInputStream2.read(bArr) != -1) {
                    fileOutputStream.write(bArr);
                }
                fileInputStream2.close();
                fileOutputStream.close();
            } catch (FileNotFoundException e2) {
                e2.printStackTrace();
            } catch (IOException e3) {
                e3.printStackTrace();
            }
        } catch (FileNotFoundException e4) {
            e4.printStackTrace();
        } catch (IOException e5) {
            e5.printStackTrace();
        }
    }

    public void V(String str) {
        if (this.z == null) {
            T();
        }
        AudioDeviceInfo[] devices = this.C.getDevices(1);
        this.B = devices;
        for (AudioDeviceInfo audioDeviceInfo : devices) {
            String address = audioDeviceInfo.getAddress();
            Log.d("MicTestActivity", "audioDeviceInfo getAddress is : " + address);
            if (str != null && str.endsWith("/mic1mp3.mp3") && address.equals("bus14_vr_in")) {
                this.z.setPreferredDevice(audioDeviceInfo);
                Log.d("MicTestActivity", "bus14_vr_in is ok: " + audioDeviceInfo.getAddress());
            } else if (str != null && str.endsWith("/mic2mp3.mp3") && address.equals("bus14_vr_in")) {
                this.z.setPreferredDevice(audioDeviceInfo);
                Log.d("MicTestActivity", "bus14_vr_in is ok: " + audioDeviceInfo.getAddress());
            } else if (str != null && str.endsWith("/mic3mp3.mp3") && address.equals("bus14_vr_in")) {
                this.z.setPreferredDevice(audioDeviceInfo);
                Log.d("MicTestActivity", "bus14_vr_in is ok: " + audioDeviceInfo.getAddress());
            } else if (str != null && str.endsWith("/mic4mp3.mp3") && address.equals("bus14_vr_in")) {
                this.z.setPreferredDevice(audioDeviceInfo);
                Log.d("MicTestActivity", "bus14_vr_in is ok: " + audioDeviceInfo.getAddress());
            }
        }
        this.z.startRecording();
        new c(this.y, str).start();
    }

    public void X() {
        Q();
    }

    public void Y() {
        P();
    }

    public void Z(String str) {
        FileOutputStream fileOutputStream;
        byte[] bArr = new byte[this.A];
        File file = new File(str);
        if (file.exists()) {
            file.delete();
        }
        try {
            fileOutputStream = new FileOutputStream(file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            fileOutputStream = null;
        }
        while (this.z.getRecordingState() == 3) {
            int i = this.z.read(bArr, 0, this.A);
            Log.d("MicTestActivity", "readSize =" + i);
            if (-3 != i) {
                try {
                    fileOutputStream.write(bArr);
                } catch (IOException e2) {
                    e2.printStackTrace();
                }
            }
        }
        try {
            fileOutputStream.close();
        } catch (IOException e3) {
            e3.printStackTrace();
        }
    }

    @Override // com.byd.engineermode.ui.activity.BaseActivity, androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_mic_test);
        this.y = getExternalFilesDir(null).getAbsolutePath() + "/micrecord.wav";
        this.F = getExternalFilesDir(null).getAbsolutePath() + "/mic1mp3.mp3";
        this.G = getExternalFilesDir(null).getAbsolutePath() + "/mic2mp3.mp3";
        this.H = getExternalFilesDir(null).getAbsolutePath() + "/mic3mp3.mp3";
        this.I = getExternalFilesDir(null).getAbsolutePath() + "/mic4mp3.mp3";
        U();
    }

    @Override // com.byd.engineermode.ui.activity.BaseActivity, androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    protected void onDestroy() {
        AbsBYDAutoBodyworkListener absBYDAutoBodyworkListener;
        Q();
        super.onDestroy();
        BYDAutoBodyworkDevice bYDAutoBodyworkDevice = this.R;
        if (bYDAutoBodyworkDevice == null || (absBYDAutoBodyworkListener = this.S) == null) {
            return;
        }
        bYDAutoBodyworkDevice.unregisterListener(absBYDAutoBodyworkListener);
    }

    @Override // androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    protected void onStop() {
        P();
        Q();
        super.onStop();
    }
}
