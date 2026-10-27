package h5;

import android.annotation.SuppressLint;
import android.content.Context;
import android.media.AudioAttributes;
import android.media.AudioFocusRequest;
import android.media.AudioManager;
import android.media.MediaPlayer;
import com.byd.uereport.bean.ReportBean;

/* JADX INFO: compiled from: AudioVoicePlayer.java */
/* JADX INFO: loaded from: classes2.dex */
public class a implements h5.d {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    public MediaPlayer f5349a;

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    public Context f5350b;

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    public AudioManager f5351c;

    /* JADX INFO: renamed from: d, reason: collision with root package name */
    public boolean f5352d;

    /* JADX INFO: renamed from: e, reason: collision with root package name */
    public final int f5353e = 18;

    /* JADX INFO: renamed from: f, reason: collision with root package name */
    public AudioFocusRequest f5354f;

    /* JADX INFO: renamed from: g, reason: collision with root package name */
    public AudioFocusRequest f5355g;

    /* JADX INFO: renamed from: h, reason: collision with root package name */
    public AudioAttributes f5356h;

    /* JADX INFO: renamed from: h5.a$a, reason: collision with other inner class name */
    /* JADX INFO: compiled from: AudioVoicePlayer.java */
    public class C0053a implements AudioManager.OnAudioFocusChangeListener {
        public C0053a() {
        }

        @Override // android.media.AudioManager.OnAudioFocusChangeListener
        public void onAudioFocusChange(int i8) {
        }
    }

    /* JADX INFO: compiled from: AudioVoicePlayer.java */
    public class b implements MediaPlayer.OnPreparedListener {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ h5.b f5358a;

        public b(h5.b bVar) {
            this.f5358a = bVar;
        }

        @Override // android.media.MediaPlayer.OnPreparedListener
        public void onPrepared(MediaPlayer mediaPlayer) {
            g5.a.a("UE onPrepared.");
            a aVar = a.this;
            aVar.f5354f = aVar.j(aVar.f5351c);
            if (a.this.f5352d) {
                a.this.f5349a.start();
                h5.b bVar = this.f5358a;
                if (bVar != null) {
                    bVar.b();
                }
                g5.a.i(" -------------- play start --------------");
                return;
            }
            h5.b bVar2 = this.f5358a;
            if (bVar2 != null) {
                bVar2.c();
            }
            a.this.a();
            g5.a.i(" -------------- audio request failed --------------");
        }
    }

    /* JADX INFO: compiled from: AudioVoicePlayer.java */
    public class c implements MediaPlayer.OnCompletionListener {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ h5.b f5360a;

        public c(h5.b bVar) {
            this.f5360a = bVar;
        }

        @Override // android.media.MediaPlayer.OnCompletionListener
        public void onCompletion(MediaPlayer mediaPlayer) {
            g5.a.i(" -------------- play end --------------");
            h5.b bVar = this.f5360a;
            if (bVar != null) {
                bVar.a();
            }
            a.this.a();
        }
    }

    /* JADX INFO: compiled from: AudioVoicePlayer.java */
    public class d implements MediaPlayer.OnErrorListener {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        public final /* synthetic */ h5.b f5362a;

        public d(h5.b bVar) {
            this.f5362a = bVar;
        }

        @Override // android.media.MediaPlayer.OnErrorListener
        public boolean onError(MediaPlayer mediaPlayer, int i8, int i9) {
            g5.a.i(" -------------- play error ----------,code:" + i8 + ",extra:" + i9);
            h5.b bVar = this.f5362a;
            if (bVar != null) {
                bVar.c();
            }
            a.this.a();
            return false;
        }
    }

    public a(Context context) {
        this.f5350b = context;
        this.f5351c = (AudioManager) context.getSystemService("audio");
        i();
    }

    @Override // h5.d
    public void a() {
        g5.a.i("audio voice player release.");
        MediaPlayer mediaPlayer = this.f5349a;
        if (mediaPlayer != null) {
            try {
                mediaPlayer.stop();
                this.f5349a.reset();
                this.f5349a.release();
                this.f5349a = null;
            } catch (Exception e8) {
                g5.a.c(e8.toString());
            }
        }
        this.f5352d = false;
        c(this.f5354f, this.f5351c);
    }

    @Override // h5.d
    public void b(ReportBean reportBean, h5.b bVar) {
        if (this.f5349a != null) {
            a();
        }
        if (reportBean == null || (reportBean.f3156e == null && reportBean.f3155d == -1)) {
            g5.a.i(" report item is null .");
            if (bVar != null) {
                bVar.c();
            }
        }
        g5.a.i(" -------------- play --------------");
        g5.a.i(" -------------- play text --------------" + reportBean.f3157f);
        this.f5349a = new MediaPlayer();
        this.f5349a.setAudioAttributes(new AudioAttributes.Builder().setUsage(18).build());
        try {
            if (reportBean.f3156e == null) {
                g5.a.i(" play apk res.");
                this.f5349a.setDataSource(this.f5350b.getResources().openRawResourceFd(reportBean.f3155d));
            } else {
                g5.a.i(" play local res.");
                this.f5349a.setDataSource(reportBean.f3156e);
            }
            this.f5349a.prepare();
            this.f5349a.setOnPreparedListener(new b(bVar));
            this.f5349a.setOnCompletionListener(new c(bVar));
            this.f5349a.setOnErrorListener(new d(bVar));
        } catch (Exception e8) {
            if (bVar != null) {
                bVar.c();
            }
            a();
            g5.a.c(" play encounter error:" + e8);
        }
    }

    @SuppressLint({"NewApi"})
    public final void c(AudioFocusRequest audioFocusRequest, AudioManager audioManager) {
        if (audioFocusRequest == null) {
            return;
        }
        g5.a.a("UE abandonAudioFocusRequest.");
        g5.a.a("UE abandonAudioFocusRequest result :" + audioManager.abandonAudioFocusRequest(audioFocusRequest));
    }

    public final void i() {
        this.f5356h = new AudioAttributes.Builder().setUsage(18).build();
        this.f5355g = new AudioFocusRequest.Builder(1).setAudioAttributes(this.f5356h).setOnAudioFocusChangeListener(new C0053a()).build();
    }

    @SuppressLint({"NewApi"})
    public final AudioFocusRequest j(AudioManager audioManager) {
        int iRequestAudioFocus = audioManager.requestAudioFocus(this.f5355g);
        if (iRequestAudioFocus == 0) {
            this.f5352d = false;
        } else if (iRequestAudioFocus == 1) {
            this.f5352d = true;
        }
        g5.a.i("request UE audio usage:" + this.f5356h.getUsage() + ",result:" + iRequestAudioFocus);
        return this.f5355g;
    }
}
