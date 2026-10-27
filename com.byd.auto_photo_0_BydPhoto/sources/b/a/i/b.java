package b.a.i;

import android.annotation.SuppressLint;
import android.car.Car;
import android.content.Context;
import android.media.AudioAttributes;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Build;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.os.Message;
import android.view.Surface;
import android.widget.SeekBar;
import androidx.annotation.NonNull;
import com.byd.bean.AudioFocusStateInfo;
import com.byd.util.e;
import com.byd.util.w;
import java.io.FileDescriptor;
import java.lang.ref.WeakReference;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;

/* JADX INFO: loaded from: classes2.dex */
public class b implements MediaPlayer.OnPreparedListener, MediaPlayer.OnErrorListener, MediaPlayer.OnCompletionListener, MediaPlayer.OnVideoSizeChangedListener, SeekBar.OnSeekBarChangeListener {

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private int f510b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private MediaPlayer f511c;
    private String d;
    private FileDescriptor e;
    private Surface f;
    private int g;
    private int h;
    private SeekBar i;
    private int j;
    private int l;
    private int m;
    private byte n;
    private Context o;
    private AudioManager q;
    private AudioFocusStateInfo r;
    private Handler t;
    private b.a.i.a v;

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private int f509a = 100;
    private boolean p = true;
    private int s = -1;
    private AudioManager.OnAudioFocusChangeListener u = new C0018b();
    private Handler k = new c(this);

    class a extends Handler {
        a(Looper looper) {
            super(looper);
        }

        @Override // android.os.Handler
        public void handleMessage(@NonNull Message message) {
            int i = message.what;
            if (i == 1 || i == 2) {
                if (b.this.f511c == null) {
                    w.c("media player is null on getCurrentPosition");
                    return;
                }
                int currentPosition = b.this.f511c.getCurrentPosition();
                if (currentPosition < b.this.g) {
                    w.c("pos : " + currentPosition + " lastPos : " + b.this.g);
                    currentPosition = b.this.g;
                } else {
                    if (b.this.g == 0) {
                        currentPosition /= 2;
                    }
                    b.this.g = currentPosition;
                }
                b.this.k.obtainMessage(301, Integer.valueOf(currentPosition)).sendToTarget();
                if (message.what == 2) {
                    return;
                }
                sendEmptyMessageDelayed(1, b.this.f509a);
            }
        }
    }

    /* JADX INFO: renamed from: b.a.i.b$b, reason: collision with other inner class name */
    class C0018b implements AudioManager.OnAudioFocusChangeListener {
        C0018b() {
        }

        @Override // android.media.AudioManager.OnAudioFocusChangeListener
        public void onAudioFocusChange(int i) {
            String str;
            if (b.this.v == null || b.this.v.r()) {
                w.c(hashCode() + " XVideoView onAudioFocusChange:" + i);
                if (i == -3) {
                    if (b.this.x()) {
                        w.c("==AUDIOFOCUS_LOSS_TRANSIENT_CAN_DUCK==down volume");
                        b.this.f511c.setVolume(0.8f, 0.8f);
                        return;
                    }
                    return;
                }
                if (i == -2 || i == -1) {
                    b.this.z();
                    return;
                }
                if (i != 1 || b.this.f511c == null || b.this.f510b != 2) {
                    return;
                }
                if (b.this.n == 1) {
                    b.this.n = (byte) 0;
                    str = "can't start by pause state";
                } else {
                    w.c("==AUDIOFOCUS_GAIN==resume volume");
                    b.this.f511c.setVolume(1.0f, 1.0f);
                    if (!b.this.f511c.isPlaying()) {
                        b.this.P();
                        return;
                    }
                    str = "==is playing==";
                }
            } else {
                str = "can't play --> XVideoView onAudioFocusChange:" + i;
            }
            w.c(str);
        }
    }

    static class c extends Handler {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        private WeakReference<b> f514a;

        public c(b bVar) {
            this.f514a = new WeakReference<>(bVar);
        }

        @Override // android.os.Handler
        public void handleMessage(@NonNull Message message) {
            WeakReference<b> weakReference = this.f514a;
            if (weakReference == null || weakReference.get() == null) {
                return;
            }
            int i = message.what;
            if (i != 201) {
                if (i != 301) {
                    return;
                }
                this.f514a.get().O(((Integer) message.obj).intValue());
                return;
            }
            if (this.f514a.get().i != null) {
                this.f514a.get().i.setSecondaryProgress(((Integer) message.obj).intValue());
            }
        }
    }

    private static class d extends Thread {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        private MediaPlayer f515a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        private int f516b;

        public d(MediaPlayer mediaPlayer, int i) {
            this.f515a = mediaPlayer;
            this.f516b = i;
        }

        @Override // java.lang.Thread, java.lang.Runnable
        public void run() {
            if (this.f515a == null) {
                return;
            }
            w.c("release MediaPlayer start " + this.f515a.hashCode());
            if (this.f516b == 2) {
                this.f515a.stop();
                try {
                    Thread.sleep(50L);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            this.f515a.release();
            w.c("release MediaPlayer finish");
        }
    }

    public b(Context context) {
        this.o = context;
        this.q = (AudioManager) context.getSystemService(Car.AUDIO_SERVICE);
        HandlerThread handlerThread = new HandlerThread("video_player");
        handlerThread.start();
        this.t = new a(handlerThread.getLooper());
    }

    private void C(MediaPlayer mediaPlayer, int i) {
        new d(mediaPlayer, i).start();
    }

    private void F(FileDescriptor fileDescriptor) {
        try {
            this.f511c.setDataSource(fileDescriptor);
            o();
            this.f511c.prepareAsync();
            w.c("after prepareAsync, XVideoView play ->FileDescriptor");
        } catch (Exception e) {
            e.printStackTrace();
            this.f510b = 0;
            w.c("prepareAsync exception : " + e.getMessage());
            this.f511c.reset();
            b.a.i.a aVar = this.v;
            if (aVar != null) {
                aVar.c();
            }
        }
    }

    private void G(String str) {
        try {
            this.f511c.setDataSource(str);
            o();
            this.f511c.prepareAsync();
            w.c("after prepareAsync, XVideoView play : " + str);
        } catch (Exception e) {
            e.printStackTrace();
            this.f510b = 0;
            this.f511c.reset();
            w.c("setDataAndPrepare exception : " + e.getMessage());
            b.a.i.a aVar = this.v;
            if (aVar != null) {
                aVar.c();
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void O(int i) {
        SeekBar seekBar = this.i;
        if (seekBar != null && this.h > 0) {
            seekBar.setProgress(i);
        }
        b.a.i.a aVar = this.v;
        if (aVar != null) {
            aVar.m(i, false);
        }
    }

    private void n() {
        int i = this.h / 1000;
        this.f509a = i < 10 ? 400 : i < 50 ? 250 : 100;
    }

    private void o() {
        int iP = com.byd.share.a.s(this.o).p();
        if (iP == 0 || iP == 1) {
            try {
                Field field = AudioAttributes.class.getField("FLAG_DEEP_BUFFER");
                field.setAccessible(true);
                if (Modifier.isStatic(field.getModifiers())) {
                    this.f511c.setAudioAttributes(new AudioAttributes.Builder().setFlags(((Integer) field.get(AudioAttributes.class)).intValue()).build());
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private void v() {
        this.f510b = 1;
        MediaPlayer mediaPlayer = this.f511c;
        if (mediaPlayer == null) {
            MediaPlayer mediaPlayer2 = new MediaPlayer();
            this.f511c = mediaPlayer2;
            mediaPlayer2.setOnPreparedListener(this);
            this.f511c.setOnErrorListener(this);
            this.f511c.setOnCompletionListener(this);
            this.f511c.setOnVideoSizeChangedListener(this);
            this.f511c.setAudioStreamType(3);
            this.f511c.setScreenOnWhilePlaying(true);
        } else {
            mediaPlayer.reset();
        }
        w.c("initMediaPlayer " + this.f511c.hashCode());
        this.f511c.setSurface(this.f);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void z() {
        b.a.i.a aVar = this.v;
        if (aVar != null) {
            aVar.q();
        }
        y();
    }

    public void A(String str, FileDescriptor fileDescriptor) {
        if (this.f == null) {
            w.c("XVideoView surface is null");
            this.f510b = 0;
            return;
        }
        int i = Build.VERSION.SDK_INT;
        this.r = e.L(this.o, this.u, 3, 1, true);
        w.c("play() -> requestAudioFocus : " + this.r);
        AudioFocusStateInfo audioFocusStateInfo = this.r;
        if (audioFocusStateInfo == null || audioFocusStateInfo.getState() != 1) {
            this.f510b = 0;
            this.h = 0;
            b.a.i.a aVar = this.v;
            if (aVar != null) {
                aVar.e();
                return;
            }
            return;
        }
        v();
        this.h = 0;
        if (str != null) {
            G(str);
        } else {
            F(fileDescriptor);
        }
    }

    public void B() {
        Handler handler = this.t;
        if (handler != null) {
            try {
                handler.getLooper().quit();
                w.c("VideoPlayer release thread");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void D() {
        this.t.sendEmptyMessageDelayed(1, this.f509a);
    }

    public void E(int i) {
        if (this.f511c != null) {
            p(i);
        } else {
            N(i);
        }
    }

    public void H(FileDescriptor fileDescriptor) {
        this.e = fileDescriptor;
    }

    public void I(b.a.i.a aVar) {
        this.v = aVar;
    }

    public void J(byte b2) {
        this.n = b2;
    }

    public void K(String str) {
        this.d = str;
    }

    public void L(SeekBar seekBar) {
        this.i = seekBar;
        seekBar.setOnSeekBarChangeListener(this);
    }

    public void M(int i) {
        this.s = i;
    }

    public void N(int i) {
        this.j = i;
        w.c("setSeekToPos : " + i);
    }

    public void P() {
        w.c("VideoPlayer start() " + this.f510b);
        MediaPlayer mediaPlayer = this.f511c;
        if ((mediaPlayer == null || this.h == 0) && this.f510b == 0) {
            A(this.d, this.e);
            return;
        }
        if (mediaPlayer == null || mediaPlayer.isPlaying() || this.f510b != 2) {
            if (this.f511c != null) {
                w.c("isPlaying : " + this.f511c.isPlaying());
            }
            w.c("duration : " + this.h + " state : " + this.f510b);
            return;
        }
        int i = Build.VERSION.SDK_INT;
        this.r = e.L(this.o, this.u, 3, 1, true);
        w.c("video play requestAudioFocus : " + this.r);
        AudioFocusStateInfo audioFocusStateInfo = this.r;
        if (audioFocusStateInfo == null || audioFocusStateInfo.getState() != 1) {
            b.a.i.a aVar = this.v;
            if (aVar != null) {
                aVar.e();
                return;
            }
            return;
        }
        this.f511c.start();
        this.t.sendEmptyMessage(1);
        b.a.i.a aVar2 = this.v;
        if (aVar2 != null) {
            aVar2.f();
        }
    }

    public void Q() {
        if (this.f511c != null) {
            this.t.removeCallbacksAndMessages(null);
            C(this.f511c, this.f510b);
            this.f511c = null;
            b.a.i.a aVar = this.v;
            if (aVar != null) {
                aVar.u();
            }
            this.n = (byte) 0;
            this.g = 0;
            this.h = 0;
        }
        this.j = 0;
        this.f510b = 0;
        a();
    }

    public void R() {
        this.t.removeMessages(1);
    }

    public void S(Surface surface) {
        this.f = surface;
        this.p = true;
        w.c("=======surfaceCreate in VideoPlayer=========" + this.f510b);
        String str = this.d;
        if (!(str == null && this.e == null) && this.f510b == 0) {
            A(str, this.e);
        }
    }

    public void T() {
        AudioFocusStateInfo audioFocusStateInfo;
        if (this.f511c != null) {
            this.t.removeCallbacksAndMessages(null);
            C(this.f511c, this.f510b);
            this.f511c = null;
            this.j = 0;
            SeekBar seekBar = this.i;
            if (seekBar != null) {
                seekBar.setProgress(0);
            }
            if (this.p && (audioFocusStateInfo = this.r) != null) {
                audioFocusStateInfo.abandonAudioFocus(this.q);
                w.c(hashCode() + " XVideoView abandonAudioFocus");
            }
            this.g = 0;
            this.f510b = 0;
        }
        w.c("VideoPlayer surface destroy");
        this.f = null;
    }

    public void a() {
        AudioFocusStateInfo audioFocusStateInfo = this.r;
        if (audioFocusStateInfo != null) {
            audioFocusStateInfo.abandonAudioFocus(this.q);
            w.c("VideoPlayer abandonAudioFocus");
        }
    }

    @Override // android.media.MediaPlayer.OnCompletionListener
    public void onCompletion(MediaPlayer mediaPlayer) {
        w.c("==onCompletion==");
        this.t.removeMessages(1);
        b.a.i.a aVar = this.v;
        if (aVar != null) {
            aVar.d(this.d);
        }
        this.j = 0;
        this.h = 0;
        this.f510b = 0;
    }

    @Override // android.media.MediaPlayer.OnErrorListener
    public boolean onError(MediaPlayer mediaPlayer, int i, int i2) {
        this.t.removeMessages(1);
        w.c("==onError==" + i + " " + i2);
        this.h = 0;
        this.f510b = 0;
        b.a.i.a aVar = this.v;
        if (aVar != null) {
            aVar.c();
        }
        return true;
    }

    @Override // android.media.MediaPlayer.OnPreparedListener
    public void onPrepared(MediaPlayer mediaPlayer) {
        this.f510b = 2;
        this.g = 0;
        this.h = mediaPlayer.getDuration();
        w.c("VideoPlayer onPrepared : " + this.h);
        if (this.h < 0) {
            this.h = 0;
        }
        n();
        b.a.i.a aVar = this.v;
        if (aVar != null) {
            aVar.k(this.h);
        }
        SeekBar seekBar = this.i;
        if (seekBar != null) {
            seekBar.setMax(this.h);
        }
        int i = this.j;
        if (i > 0) {
            if (this.h > 0) {
                p(i);
                if (this.n != 0) {
                    this.t.sendEmptyMessageDelayed(2, this.f509a);
                }
            }
            this.j = 0;
        }
        if (this.n == 0) {
            mediaPlayer.start();
            b.a.i.a aVar2 = this.v;
            if (aVar2 != null) {
                aVar2.f();
            }
            this.t.sendEmptyMessageDelayed(1, this.f509a);
            return;
        }
        this.n = (byte) 0;
        b.a.i.a aVar3 = this.v;
        if (aVar3 != null) {
            aVar3.h();
        }
    }

    @Override // android.widget.SeekBar.OnSeekBarChangeListener
    public void onProgressChanged(SeekBar seekBar, int i, boolean z) {
        b.a.i.a aVar;
        if (!z || (aVar = this.v) == null) {
            return;
        }
        aVar.m(i, true);
    }

    @Override // android.widget.SeekBar.OnSeekBarChangeListener
    public void onStartTrackingTouch(SeekBar seekBar) {
        w.c("--onStartTrackingTouch--");
        if (this.f511c != null) {
            R();
            b.a.i.a aVar = this.v;
            if (aVar != null) {
                aVar.s();
            }
        }
    }

    @Override // android.widget.SeekBar.OnSeekBarChangeListener
    public void onStopTrackingTouch(SeekBar seekBar) {
        w.c("--onStopTrackingTouch--");
        if (seekBar.getMax() > 0) {
            int progress = seekBar.getProgress();
            if (this.f511c == null) {
                N(progress);
                return;
            }
            p(progress);
            if (this.f511c.isPlaying()) {
                D();
            }
            b.a.i.a aVar = this.v;
            if (aVar != null) {
                aVar.l();
            }
        }
    }

    @Override // android.media.MediaPlayer.OnVideoSizeChangedListener
    public void onVideoSizeChanged(MediaPlayer mediaPlayer, int i, int i2) {
        this.l = i;
        this.m = i2;
        b.a.i.a aVar = this.v;
        if (aVar != null) {
            aVar.i(i, i2);
        }
    }

    @SuppressLint({"WrongConstant"})
    public void p(int i) {
        this.g = i;
        w.c("player seekTo , position : " + i + " duration : " + this.h + " mode : " + this.s);
        int i2 = this.s;
        if (i2 == -1 || Build.VERSION.SDK_INT < 26) {
            this.f511c.seekTo(i);
        } else {
            this.f511c.seekTo(i, i2);
        }
    }

    public int q() {
        MediaPlayer mediaPlayer = this.f511c;
        if (mediaPlayer != null) {
            return mediaPlayer.getCurrentPosition();
        }
        return 0;
    }

    public FileDescriptor r() {
        return this.e;
    }

    public byte s() {
        return this.n;
    }

    public String t() {
        return this.d;
    }

    public int u() {
        return this.j;
    }

    public boolean w() {
        return this.f511c != null;
    }

    public boolean x() {
        MediaPlayer mediaPlayer = this.f511c;
        return mediaPlayer != null && mediaPlayer.isPlaying();
    }

    public void y() {
        MediaPlayer mediaPlayer = this.f511c;
        if (mediaPlayer != null && mediaPlayer.isPlaying() && this.f510b == 2) {
            this.t.removeMessages(1);
            this.f511c.pause();
            b.a.i.a aVar = this.v;
            if (aVar != null) {
                aVar.h();
            }
        }
    }
}
