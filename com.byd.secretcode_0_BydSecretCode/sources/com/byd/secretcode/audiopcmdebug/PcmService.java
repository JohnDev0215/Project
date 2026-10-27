package com.byd.secretcode.audiopcmdebug;

import android.annotation.SuppressLint;
import android.app.Service;
import android.content.ContentResolver;
import android.content.Intent;
import android.content.res.AssetFileDescriptor;
import android.content.res.AssetManager;
import android.database.ContentObserver;
import android.graphics.Bitmap;
import android.hardware.bydauto.properties.CarSettings;
import android.media.AudioAttributes;
import android.media.AudioFocusRequest;
import android.media.AudioFormat;
import android.media.AudioManager;
import android.media.AudioRecord;
import android.media.AudioTrack;
import android.media.MediaMetadataRetriever;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Binder;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.SystemProperties;
import android.util.Log;
import com.bydux.api.common.tools.log.BydLogHelper;
import defpackage.dg;
import defpackage.mg;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.ByteBuffer;

/* JADX INFO: loaded from: classes.dex */
public class PcmService extends Service {
    public AudioManager b;
    public AudioRecord c;
    public AudioRecord d;
    public FileInputStream e;
    public AudioTrack f;
    public ContentResolver q;
    public g g = new g();
    public boolean h = false;
    public boolean i = false;
    public boolean j = false;
    public boolean k = false;
    public boolean l = false;
    public int m = 0;
    public MediaPlayer n = new MediaPlayer();
    public boolean o = false;
    public String p = "/storage/emulated/0/test/";

    @SuppressLint({"HandlerLeak"})
    public Handler r = new a();
    public AudioManager.OnAudioFocusChangeListener s = new f();

    public class a extends Handler {
        public float a = 1.0f;

        public a() {
        }

        @Override // android.os.Handler
        public void handleMessage(Message message) {
            int i = message.what;
            if (i == 0) {
                if (PcmService.this.o) {
                    PcmService.this.o = false;
                    this.a = 0.0f;
                    MediaPlayer mediaPlayer = PcmService.this.n;
                    float f = this.a;
                    mediaPlayer.setVolume(f, f);
                    return;
                }
                return;
            }
            if (i != 1) {
                return;
            }
            float f2 = this.a + 0.01f;
            this.a = f2;
            if (f2 < 1.0f) {
                PcmService.this.r.sendEmptyMessageDelayed(1, 20L);
            } else {
                this.a = 1.0f;
            }
            BydLogHelper.logD("PcmService", "mMediaplayerHandler mCurrentVolume = " + this.a);
            MediaPlayer mediaPlayer2 = PcmService.this.n;
            float f3 = this.a;
            mediaPlayer2.setVolume(f3, f3);
        }
    }

    public class b implements Runnable {
        public final /* synthetic */ int b;

        public b(int i) {
            this.b = i;
        }

        @Override // java.lang.Runnable
        public void run() {
            try {
                byte[] bArr = new byte[this.b];
                BydLogHelper.logD("PcmService", "start to play");
                while (PcmService.this.e.available() > 0) {
                    int i = PcmService.this.e.read(bArr);
                    if (i != -3 && i != -2 && i != 0 && i != -1 && PcmService.this.k) {
                        PcmService.this.f.write(bArr, 0, i);
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public class c implements Runnable {
        public c() {
        }

        @Override // java.lang.Runnable
        public void run() {
        }
    }

    public class d implements Runnable {
        public final /* synthetic */ File b;
        public final /* synthetic */ byte[] c;
        public final /* synthetic */ int d;

        public d(File file, byte[] bArr, int i) {
            this.b = file;
            this.c = bArr;
            this.d = i;
        }

        @Override // java.lang.Runnable
        public void run() {
            FileOutputStream fileOutputStream;
            try {
                fileOutputStream = new FileOutputStream(this.b);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
                fileOutputStream = null;
            }
            while (PcmService.this.i) {
                if (-3 != PcmService.this.d.read(this.c, 0, this.d)) {
                    try {
                        fileOutputStream.write(this.c);
                    } catch (IOException e2) {
                        e2.printStackTrace();
                    }
                }
            }
            try {
                BydLogHelper.logD("PcmService", "run: close file output stream !");
                fileOutputStream.close();
            } catch (IOException e3) {
                e3.printStackTrace();
            }
        }
    }

    public class e implements Runnable {
        public e() {
        }

        @Override // java.lang.Runnable
        public void run() {
            try {
                dg.b(new File("/data/vendor/diag_audiolog"), new File("/sdcard/test"), "AudioMdlog.zip");
                dg.a("/data/vendor/diag_audiolog");
            } catch (Exception e) {
                BydLogHelper.logD("PcmService", "catchAudioMdLog Exception = " + e);
            }
        }
    }

    public class f implements AudioManager.OnAudioFocusChangeListener {
        public f() {
        }

        @Override // android.media.AudioManager.OnAudioFocusChangeListener
        public void onAudioFocusChange(int i) {
        }
    }

    public class g extends Binder {
        public g() {
        }

        public PcmService a() {
            return PcmService.this;
        }
    }

    public final class h extends ContentObserver {
        public h(Handler handler) {
            super(handler);
        }

        public void onChange(boolean z, Uri uri, String str, int i) {
            super.onChange(z, uri, str, i);
            BydLogHelper.logD("PcmService", "ban onChange key / " + str + ", operationFlag / " + i);
            if (CarSettings.UserTableData.BYD_APP_THEME.equals(str) && i == 3) {
                BydLogHelper.logD("PcmService", "ban onChange BYD_APP_THEME uuid / " + CarSettings.UserTableData.getInt(PcmService.this.getContentResolver(), CarSettings.UserTableData.BYD_APP_THEME));
            }
        }
    }

    public void A(int i, int i2, int i3) {
        int minBufferSize = AudioRecord.getMinBufferSize(i, i2, i3);
        BydLogHelper.logD("PcmService", "startAudioRecord sample = " + i + " channel = " + i2 + " bit = " + i3 + " minBufferSize = " + minBufferSize);
        this.c = new AudioRecord(1, i, i2, i3, minBufferSize);
        byte[] bArr = new byte[minBufferSize];
        String str = i2 != 12 ? i2 != 204 ? i2 != 252 ? "CHANNEL_IN_MONO" : "CHANNEL_IN_5POINT1" : "CHANNEL_IN_QUAD" : "CHANNEL_IN_STEREO";
        File file = new File(this.p, str + "16000.pcm");
        if (!file.mkdirs()) {
            Log.e("PcmService", "Directory not created");
        }
        if (file.exists()) {
            file.delete();
        }
        File file2 = new File(this.p, "testsplitfront.pcm");
        File file3 = new File(this.p, "testsplitref.pcm");
        File file4 = new File(this.p, "testsplitback.pcm");
        if (file2.exists()) {
            file2.delete();
        }
        if (file3.exists()) {
            file3.delete();
        }
        if (file4.exists()) {
            file4.delete();
        }
        this.c.startRecording();
        BydLogHelper.logD("PcmService", "AudioRecord start recording");
        this.h = true;
        new Thread(new c()).start();
    }

    public void B(int i, int i2, int i3) {
        int minBufferSize = AudioRecord.getMinBufferSize(i, i2, i3);
        BydLogHelper.logD("PcmService", "startCoAudioRecord sample = " + i + " channel = " + i2 + " bit = " + i3 + " minBufferSize = " + minBufferSize);
        this.d = new AudioRecord(1, i, i2, i3, minBufferSize);
        byte[] bArr = new byte[minBufferSize];
        String str = i2 != 12 ? i2 != 16 ? "CHANNEL_IN_MONO" : "CO_CHANNEL_IN_MONO" : "CO_CHANNEL_IN_STEREO";
        File file = new File(this.p, str + "16000.pcm");
        if (!file.mkdirs()) {
            Log.e("PcmService", "Directory not created");
        }
        if (file.exists()) {
            file.delete();
        }
        this.d.startRecording();
        BydLogHelper.logD("PcmService", "mCoAudioRecord start recording");
        this.i = true;
        new Thread(new d(file, bArr, minBufferSize)).start();
    }

    public void C() {
        this.h = false;
        AudioRecord audioRecord = this.c;
        if (audioRecord != null) {
            audioRecord.stop();
            this.c.release();
            this.c = null;
            BydLogHelper.logD("PcmService", "stop Audio Record");
        }
    }

    public void D() {
        this.i = false;
        AudioRecord audioRecord = this.d;
        if (audioRecord != null) {
            audioRecord.stop();
            this.d.release();
            this.d = null;
            BydLogHelper.logD("PcmService", "stop Audio Record mCoAudioRecord");
        }
    }

    public void E() {
        if (this.f != null) {
            BydLogHelper.logD("PcmService", "stop Navi");
            this.k = false;
            this.f.stop();
            this.f.release();
            this.f = null;
        }
    }

    public final void j() {
        AudioFocusRequest audioFocusRequestBuild = new AudioFocusRequest.Builder(1).setAudioAttributes(new AudioAttributes.Builder().setUsage(1).build()).setOnAudioFocusChangeListener(this.s).build();
        int iRequestAudioFocus = this.b.requestAudioFocus(audioFocusRequestBuild);
        if (iRequestAudioFocus == 0) {
            BydLogHelper.logD("PcmService", "AUDIOFOCUS_REQUEST_FAILED");
        } else if (iRequestAudioFocus == 1) {
            BydLogHelper.logD("PcmService", "AUDIOFOCUS_REQUEST_GRANTED");
        } else if (iRequestAudioFocus == 2) {
            BydLogHelper.logD("PcmService", "AUDIOFOCUS_REQUEST_DELAYED");
        }
        this.b.abandonAudioFocusRequest(audioFocusRequestBuild);
    }

    public void k(boolean z) {
        if (z) {
            SystemProperties.set("persist.sys.diag.audio.enable", "1");
        } else {
            SystemProperties.set("persist.sys.diag.audio.enable", "0");
            new Thread(new e()).start();
        }
    }

    public void l() {
        BydLogHelper.logD("PcmService", "clearEnvironmentAudioData");
        w(2, "false");
        w(1, "false");
        File file = new File(this.p, "hwdb.pcm");
        File file2 = new File(this.p, "hwll.pcm");
        File file3 = new File(this.p, "hwng.pcm");
        File file4 = new File(this.p, "hr.pcm");
        if (file.exists()) {
            file.delete();
        }
        if (file2.exists()) {
            file2.delete();
        }
        if (file3.exists()) {
            file3.delete();
        }
        if (file4.exists()) {
            file4.delete();
        }
    }

    public boolean m() {
        return this.k;
    }

    public boolean n() {
        return this.h;
    }

    public boolean o() {
        return this.i;
    }

    @Override // android.app.Service
    public IBinder onBind(Intent intent) {
        return this.g;
    }

    @Override // android.app.Service
    public void onCreate() {
        super.onCreate();
        BydLogHelper.logD("PcmService", "onCreate");
        this.b = (AudioManager) getSystemService("audio");
        this.p = getExternalFilesDir(null).getPath() + "/";
        BydLogHelper.logD("PcmService", "onCreate AUDIO_SAVE_PATH = " + this.p);
        s();
    }

    @Override // android.app.Service
    public void onDestroy() {
        super.onDestroy();
    }

    @Override // android.app.Service
    public int onStartCommand(Intent intent, int i, int i2) {
        BydLogHelper.logD("PcmService", "onStartCommand");
        return super.onStartCommand(intent, i, i2);
    }

    public void p() {
        MediaPlayer mediaPlayer = this.n;
        if (mediaPlayer == null || !mediaPlayer.isPlaying()) {
            return;
        }
        this.n.pause();
    }

    public void q() {
        int minBufferSize = AudioTrack.getMinBufferSize(16000, 12, 2);
        BydLogHelper.logD("PcmService", "playNavi minBufferSize = " + minBufferSize);
        AudioTrack audioTrack = new AudioTrack(new AudioAttributes.Builder().setLegacyStreamType(15).build(), new AudioFormat.Builder().setSampleRate(16000).setEncoding(2).setChannelMask(12).build(), minBufferSize, 1, 0);
        this.f = audioTrack;
        audioTrack.play();
        this.k = true;
        try {
            this.e = new FileInputStream(new File(this.p, "CHANNEL_IN_STEREO16000.pcm"));
            new Thread(new b(minBufferSize)).start();
        } catch (IOException e2) {
            e2.printStackTrace();
        }
    }

    public void r() {
        j();
        AssetManager assets = getAssets();
        try {
            this.n.reset();
            AssetFileDescriptor assetFileDescriptorOpenFd = assets.openFd("123.mp3");
            this.n.setDataSource(assetFileDescriptorOpenFd.getFileDescriptor(), assetFileDescriptorOpenFd.getStartOffset(), assetFileDescriptorOpenFd.getLength());
            this.n.setAudioAttributes(new AudioAttributes.Builder().setLegacyStreamType(3).build());
            this.n.prepare();
            BydLogHelper.logD("PcmService", "playUseMediaPlayer before volume = ");
            this.o = true;
            this.n.start();
            BydLogHelper.logD("PcmService", "playUseMediaPlayer after volume = ");
        } catch (Exception e2) {
            BydLogHelper.logD("PcmService", "exception = " + e2);
        }
    }

    public final void s() {
        ContentResolver contentResolver = getContentResolver();
        this.q = contentResolver;
        contentResolver.registerContentObserver(CarSettings.UserTableData.CONTENT_URI, true, new h(null));
    }

    public void t(long j) {
        if (!new File(this.p + "123.mp4").exists()) {
            mg.b(getApplicationContext(), "资源文件不存在！");
            return;
        }
        MediaMetadataRetriever mediaMetadataRetriever = new MediaMetadataRetriever();
        mediaMetadataRetriever.setDataSource(this.p + "123.mp4");
        BydLogHelper.logD("PcmService", "begin to get bitmap");
        Bitmap frameAtTime = mediaMetadataRetriever.getFrameAtTime(1000000 * j);
        int byteCount = frameAtTime.getByteCount();
        byte[] bArr = new byte[byteCount];
        frameAtTime.copyPixelsToBuffer(ByteBuffer.wrap(bArr));
        BydLogHelper.logD("PcmService", "saveBitMapOnVideo timeMs = " + j + " size = " + byteCount + ",pixels = " + bArr);
        try {
            v(bArr, Long.toString(j) + "raw", this.p);
            u(frameAtTime, Long.toString(j) + ".png", this.p);
        } catch (Exception e2) {
            BydLogHelper.logD("PcmService", "E = " + e2);
        }
    }

    public final void u(Bitmap bitmap, String str, String str2) throws IOException {
        File file = new File(str2);
        if (!file.exists()) {
            file.mkdirs();
        }
        File file2 = new File(file, str);
        if (!file2.exists()) {
            file2.createNewFile();
        }
        BufferedOutputStream bufferedOutputStream = new BufferedOutputStream(new FileOutputStream(file2));
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, bufferedOutputStream);
        bufferedOutputStream.flush();
        bufferedOutputStream.close();
    }

    public final void v(byte[] bArr, String str, String str2) throws IOException {
        File file = new File(str2);
        if (!file.exists()) {
            file.mkdirs();
        }
        File file2 = new File(file, str);
        if (!file2.exists()) {
            file2.createNewFile();
        }
        FileOutputStream fileOutputStream = new FileOutputStream(file2);
        fileOutputStream.write(bArr);
        fileOutputStream.close();
    }

    public final void w(int i, String str) {
        if (i == 1) {
            SystemProperties.set("audio.pcm.debug.read", str);
        } else {
            if (i != 2) {
                return;
            }
            SystemProperties.set("audio.pcm.debug.write", str);
        }
    }

    public void x(int i) {
        BydLogHelper.logD("PcmService", "setDenoiseMode mode = " + i);
        BydLogHelper.logD("PcmService", "setDenoiseMode end mode = " + i);
    }

    public void y() {
        BydLogHelper.logD("PcmService", "setEnvironmentToCatchPlayPcm");
        w(1, "false");
        w(2, "true");
        File file = new File(this.p, "hwdb.pcm");
        File file2 = new File(this.p, "hwll.pcm");
        File file3 = new File(this.p, "hwng.pcm");
        try {
            if (!file.exists()) {
                file.createNewFile();
                file.setExecutable(true);
            }
            if (!file2.exists()) {
                file2.createNewFile();
                file2.setExecutable(true);
            }
            if (file3.exists()) {
                return;
            }
            file3.createNewFile();
            file3.setExecutable(true);
        } catch (Exception e2) {
            BydLogHelper.logD("PcmService", "create file e ;" + e2);
        }
    }

    public void z() {
        BydLogHelper.logD("PcmService", "setEnvironmentToCatchRecordPcm");
        w(1, "true");
        w(2, "false");
        File file = new File(this.p, "hr.pcm");
        try {
            if (file.exists()) {
                return;
            }
            file.createNewFile();
            file.setExecutable(true);
        } catch (Exception e2) {
            BydLogHelper.logD("PcmService", "create file e ;" + e2);
        }
    }
}
