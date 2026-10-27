package com.byd.sing.view.a;

import android.content.res.TypedArray;
import android.media.MediaPlayer;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import com.byd.sing.R;
import com.byd.sing.SingApplication;
import java.util.ArrayList;
import java.util.List;

/* JADX INFO: compiled from: AtmosphereScreenFrame.java */
/* JADX INFO: loaded from: classes.dex */
public class b extends f {
    private static final int[] p = {R.raw.gift, R.raw.flower, R.raw.applause, R.raw.kiss};
    private ImageView q;
    private MediaPlayer r;
    private boolean s;
    private boolean t;
    private List<Integer> u;
    private int v;
    private final Handler w;

    /* JADX INFO: compiled from: AtmosphereScreenFrame.java */
    class a extends Handler {
        a(Looper looper) {
            super(looper);
        }

        @Override // android.os.Handler
        public void handleMessage(Message message) {
            super.handleMessage(message);
            if (b.this.v <= b.this.u.size() - 1) {
                b.this.q.setImageResource(((Integer) b.this.u.get(b.G(b.this))).intValue());
                sendEmptyMessageDelayed(17, 5L);
                return;
            }
            com.byd.sing.a.a("playImages finish");
            b.this.t = true;
            if (b.this.s) {
                b.this.a();
            }
        }
    }

    /* JADX INFO: renamed from: com.byd.sing.view.a.b$b, reason: collision with other inner class name */
    /* JADX INFO: compiled from: AtmosphereScreenFrame.java */
    class C0107b implements MediaPlayer.OnCompletionListener {
        C0107b() {
        }

        @Override // android.media.MediaPlayer.OnCompletionListener
        public void onCompletion(MediaPlayer mediaPlayer) {
            com.byd.sing.a.a("onCompletion");
            b.this.s = true;
            if (b.this.t) {
                b.this.a();
            }
        }
    }

    public b(int i) {
        super(i);
        this.s = false;
        this.t = false;
        this.v = 0;
        this.w = new a(Looper.getMainLooper());
    }

    static /* synthetic */ int G(b bVar) {
        int i = bVar.v;
        bVar.v = i + 1;
        return i;
    }

    private List<Integer> N() {
        com.byd.sing.a.a("mState = " + this.e);
        int i = this.e;
        TypedArray typedArrayObtainTypedArray = SingApplication.a().getResources().obtainTypedArray(i != 0 ? i != 1 ? i != 2 ? i != 3 ? 0 : R.array.kiss_anim : R.array.congratulate_anim : R.array.flower_anim : R.array.gift_anim);
        int length = typedArrayObtainTypedArray.length();
        ArrayList arrayList = new ArrayList();
        for (int i2 = 0; i2 < length; i2++) {
            arrayList.add(Integer.valueOf(typedArrayObtainTypedArray.getResourceId(i2, 0)));
        }
        return arrayList;
    }

    private void O() {
        List<Integer> listN = N();
        this.u = listN;
        this.v = 0;
        this.q.setImageResource(listN.get(0).intValue());
        this.w.sendEmptyMessage(17);
    }

    private void P() {
        com.byd.sing.a.a("play");
        try {
            MediaPlayer mediaPlayerCreate = MediaPlayer.create(SingApplication.a(), p[this.e]);
            this.r = mediaPlayerCreate;
            mediaPlayerCreate.setAudioStreamType(3);
            this.r.start();
            this.r.setOnCompletionListener(new C0107b());
        } catch (Throwable th) {
            com.byd.sing.a.c("play error", th);
        }
    }

    @Override // com.byd.sing.view.a.f, com.byd.sing.view.a.g
    public void a() {
        com.byd.sing.a.a("dismiss");
        MediaPlayer mediaPlayer = this.r;
        if (mediaPlayer != null) {
            try {
                mediaPlayer.stop();
                this.r.release();
                this.r = null;
            } catch (Throwable th) {
                com.byd.sing.a.c("stop error", th);
            }
        }
        Handler handler = this.w;
        if (handler != null) {
            handler.removeMessages(17);
        }
        super.a();
    }

    @Override // com.byd.sing.view.a.g
    protected void b(View view) {
        this.q = (ImageView) view.findViewById(R.id.iv_anim);
        O();
        P();
    }

    @Override // com.byd.sing.view.a.g
    protected int c() {
        return R.layout.layout_atmosphere_screen;
    }

    @Override // com.byd.sing.view.a.g
    protected int d() {
        return 0;
    }

    @Override // com.byd.sing.view.a.g
    protected int e() {
        return 0;
    }

    @Override // com.byd.sing.view.a.g
    protected int g() {
        return 0;
    }

    @Override // com.byd.sing.view.a.g
    protected void i(int[] iArr) {
    }

    @Override // com.byd.sing.view.a.f, com.byd.sing.view.a.g
    public void m() {
    }

    @Override // com.byd.sing.view.a.g
    protected boolean n() {
        return true;
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
    }

    @Override // com.byd.sing.view.a.f
    protected void q() {
    }

    @Override // com.byd.sing.view.a.f
    protected void t(int i, int i2) {
    }

    @Override // com.byd.sing.view.a.f
    protected void v(MotionEvent motionEvent) {
    }

    @Override // com.byd.sing.view.a.f
    protected void w() {
    }

    @Override // com.byd.sing.view.a.f
    protected boolean y() {
        return false;
    }
}
