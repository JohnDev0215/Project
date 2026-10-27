package ts.car.diag.manager;

import android.content.Context;
import android.media.MediaPlayer;
import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.VideoView;
import com.byd.mediacenter.api.media.R;

/* JADX INFO: compiled from: caradapter-bug */
/* JADX INFO: loaded from: classes3.dex */
public class w implements View.OnClickListener {

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private VideoView f1635c;
    private String e;
    private WindowManager.LayoutParams f;
    private WindowManager g;
    private View h;
    private e i;
    private boolean j = false;
    private Handler d = new Handler();

    /* JADX INFO: compiled from: caradapter-bug */
    class a implements MediaPlayer.OnCompletionListener {
        a(w wVar) {
        }

        @Override // android.media.MediaPlayer.OnCompletionListener
        public void onCompletion(MediaPlayer mediaPlayer) {
            q.h("VideoManager", " onCompletion ");
        }
    }

    /* JADX INFO: compiled from: caradapter-bug */
    class b implements MediaPlayer.OnErrorListener {
        b() {
        }

        @Override // android.media.MediaPlayer.OnErrorListener
        public boolean onError(MediaPlayer mediaPlayer, int i, int i2) {
            q.b("VideoManager", " onError ");
            w.this.j(false);
            return true;
        }
    }

    /* JADX INFO: compiled from: caradapter-bug */
    class c implements MediaPlayer.OnPreparedListener {
        c() {
        }

        @Override // android.media.MediaPlayer.OnPreparedListener
        public void onPrepared(MediaPlayer mediaPlayer) {
            q.h("VideoManager", "onPrepared: ");
            w.this.m(1000);
        }
    }

    /* JADX INFO: compiled from: caradapter-bug */
    class d implements Runnable {
        d() {
        }

        @Override // java.lang.Runnable
        public void run() {
            w.this.g();
        }
    }

    /* JADX INFO: compiled from: caradapter-bug */
    public interface e {
        void a(boolean z);
    }

    private WindowManager.LayoutParams d() {
        if (this.f == null) {
            WindowManager.LayoutParams layoutParams = new WindowManager.LayoutParams();
            this.f = layoutParams;
            layoutParams.flags = 768;
            layoutParams.width = -1;
            layoutParams.height = -1;
            layoutParams.type = 2038;
        }
        return this.f;
    }

    private void f(VideoView videoView) {
        this.f1635c = videoView;
        videoView.setOnCompletionListener(new a(this));
        this.f1635c.setOnErrorListener(new b());
        this.f1635c.setOnPreparedListener(new c());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void g() {
        try {
            if (this.j) {
                q.b("VideoManager", "logDuration: has send result");
                return;
            }
            if (this.f1635c != null) {
                boolean zIsPlaying = this.f1635c.isPlaying();
                q.h("VideoManager", "logDuration: playing " + zIsPlaying);
                if (zIsPlaying) {
                    j(true);
                }
            }
        } catch (Exception e2) {
            e2.printStackTrace();
            j(false);
        }
    }

    private void h() {
        i(this.e);
    }

    private void i(String str) {
        try {
            q.h("VideoManager", " playByPath " + str);
            this.j = false;
            this.e = str;
            if (this.f1635c != null) {
                this.f1635c.setVideoPath(str);
                this.f1635c.start();
                q.h("VideoManager", " playByPath start ");
                m(2500);
            }
            q.h("VideoManager", " playByPath end ");
        } catch (Exception e2) {
            e2.printStackTrace();
            q.b("VideoManager", " playByPath sendPlayResult false");
            j(false);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void j(boolean z) {
        q.h("VideoManager", "sendPlayResult: hasSendResult " + this.j + " isSuccess " + z);
        e eVar = this.i;
        if (eVar == null || this.j) {
            return;
        }
        this.j = true;
        eVar.a(z);
        o();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void m(int i) {
        this.d.postDelayed(new d(), i);
    }

    private void n() {
        try {
            q.h("VideoManager", " stop ");
            if (this.f1635c != null) {
                this.f1635c.stopPlayback();
            }
            o();
            q.h("VideoManager", " stop end");
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    private void o() {
        Handler handler = this.d;
        if (handler != null) {
            handler.removeCallbacksAndMessages(null);
        }
    }

    public boolean e() {
        try {
            q.h("VideoManager", "hide ");
            n();
            if (this.g == null || this.h == null) {
                return true;
            }
            this.g.removeView(this.h);
            this.h = null;
            return true;
        } catch (Exception e2) {
            e2.printStackTrace();
            return false;
        }
    }

    public void k(e eVar) {
        this.i = eVar;
    }

    public void l(Context context, String str) {
        q.h("VideoManager", "show " + str);
        if (this.h == null) {
            View viewInflate = LayoutInflater.from(context).inflate(R.layout.view_video, (ViewGroup) null, false);
            this.h = viewInflate;
            VideoView videoView = (VideoView) viewInflate.findViewById(R.id.videoView);
            this.h.findViewById(R.id.btn_exit).setOnClickListener(this);
            this.h.findViewById(R.id.btn_play).setOnClickListener(this);
            this.h.findViewById(R.id.btn_stop).setOnClickListener(this);
            f(videoView);
            WindowManager windowManager = (WindowManager) context.getSystemService("window");
            this.g = windowManager;
            windowManager.addView(this.h, d());
        }
        i(str);
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.btn_exit /* 2131296343 */:
                e();
                break;
            case R.id.btn_play /* 2131296344 */:
                h();
                break;
            case R.id.btn_stop /* 2131296345 */:
                n();
                break;
        }
    }
}
