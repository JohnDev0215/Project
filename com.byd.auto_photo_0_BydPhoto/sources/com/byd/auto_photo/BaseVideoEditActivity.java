package com.byd.auto_photo;

import android.annotation.SuppressLint;
import android.car.Car;
import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Rect;
import android.graphics.SurfaceTexture;
import android.media.AudioManager;
import android.media.MediaMetadataRetriever;
import android.media.MediaPlayer;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Message;
import android.util.Log;
import android.view.Surface;
import android.view.TextureView;
import android.view.View;
import android.view.ViewTreeObserver;
import android.view.WindowManager;
import android.widget.CheckBox;
import android.widget.CheckedTextView;
import android.widget.CompoundButton;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.TextView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import b.a.e.c;
import com.byd.bean.AudioFocusStateInfo;
import com.byd.bean.ImageEditFilterItem;
import com.byd.filter2.h.j;
import com.byd.fragment.AlertDialogFragment;
import com.byd.util.w;
import com.byd.util.x;
import com.byd.view.ListItemDecoration;
import com.byd.view.PositiveAndNegativeSeekBar;
import com.byd.view.VideoEditCropZoneView;
import com.byd.view.VideoEditTimeLineController;
import com.byd.view.VideoPreview;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

/* JADX INFO: loaded from: classes.dex */
public abstract class BaseVideoEditActivity extends SubBaseTaskActivity implements View.OnClickListener, com.byd.filter2.d {
    private View A;
    protected PositiveAndNegativeSeekBar B;
    protected TextView C;
    protected TextView D;
    private View E;
    private View F;
    protected CheckedTextView G;
    protected CheckedTextView H;
    protected CheckedTextView I;
    protected CheckedTextView J;
    protected CheckedTextView K;
    protected CheckedTextView L;
    protected CheckedTextView M;
    protected TextView N;
    protected RecyclerView O;
    private boolean P;
    private boolean Q;
    private boolean R;
    protected long S;
    private MediaPlayer T;
    private AudioManager U;
    private AudioFocusStateInfo V;
    protected boolean W;
    protected boolean X;
    private q Y;
    private int Z;
    private int a0;
    private int b0;
    private com.byd.filter2.h.j c0;
    protected b.a.e.h d0;
    private ImageEditFilterItem[] f0;
    private int g0;
    private int h0;
    private int i0;
    private Handler o0;
    private com.byd.filter2.h.h r;
    private TextView r0;
    private String s;
    private PopupWindow s0;
    protected VideoPreview t;
    private AlertDialogFragment t0;
    protected VideoEditCropZoneView u;
    protected VideoEditTimeLineController v;
    protected CheckBox w;
    protected ImageView x;
    private View y;
    private View z;
    protected int e0 = 10;
    private PositiveAndNegativeSeekBar.OnProgressChangeListener j0 = new k();
    private MediaPlayer.OnPreparedListener k0 = new a();
    private MediaPlayer.OnErrorListener l0 = new b();
    private MediaPlayer.OnVideoSizeChangedListener m0 = new c();
    private CompoundButton.OnCheckedChangeListener n0 = new d();
    private VideoEditTimeLineController.OnTimeLineListener p0 = new e();
    private AudioManager.OnAudioFocusChangeListener q0 = new f();

    class a implements MediaPlayer.OnPreparedListener {

        /* JADX INFO: renamed from: com.byd.auto_photo.BaseVideoEditActivity$a$a, reason: collision with other inner class name */
        class RunnableC0027a implements Runnable {
            RunnableC0027a() {
            }

            @Override // java.lang.Runnable
            public void run() {
                BaseVideoEditActivity.this.f();
            }
        }

        a() {
        }

        @Override // android.media.MediaPlayer.OnPreparedListener
        public void onPrepared(MediaPlayer mediaPlayer) {
            Log.e("m_tag", "===onPrepared===");
            BaseVideoEditActivity.this.P = true;
            BaseVideoEditActivity.this.Y.obtainMessage(2, Integer.valueOf(mediaPlayer.getDuration())).sendToTarget();
            mediaPlayer.start();
            BaseVideoEditActivity.this.Y.post(new RunnableC0027a());
            BaseVideoEditActivity.this.Y.sendEmptyMessageDelayed(1, 50L);
        }
    }

    class b implements MediaPlayer.OnErrorListener {
        b() {
        }

        @Override // android.media.MediaPlayer.OnErrorListener
        public boolean onError(MediaPlayer mediaPlayer, int i, int i2) {
            Log.e("m_tag", "===onError===" + i + " " + i2);
            BaseVideoEditActivity.this.n1();
            return true;
        }
    }

    class c implements MediaPlayer.OnVideoSizeChangedListener {

        class a implements Runnable {

            /* JADX INFO: renamed from: a, reason: collision with root package name */
            final /* synthetic */ int f1022a;

            /* JADX INFO: renamed from: b, reason: collision with root package name */
            final /* synthetic */ int f1023b;

            a(int i, int i2) {
                this.f1022a = i;
                this.f1023b = i2;
            }

            @Override // java.lang.Runnable
            public void run() {
                BaseVideoEditActivity.this.t.setVideoSize(this.f1022a, this.f1023b);
            }
        }

        c() {
        }

        @Override // android.media.MediaPlayer.OnVideoSizeChangedListener
        public void onVideoSizeChanged(MediaPlayer mediaPlayer, int i, int i2) {
            if (i > 0 && i2 > 0 && i == BaseVideoEditActivity.this.t.getVideoWidth() && i2 == BaseVideoEditActivity.this.t.getVideoHeight()) {
                Log.e("m_tag", "===onVideoSizeChanged=11==" + i + " " + i2);
                return;
            }
            BaseVideoEditActivity.this.g0 = i;
            BaseVideoEditActivity.this.h0 = i2;
            Log.e("m_tag", "===onVideoSizeChanged===" + i + " " + i2);
            BaseVideoEditActivity.this.runOnUiThread(new a(i, i2));
        }
    }

    class d implements CompoundButton.OnCheckedChangeListener {
        d() {
        }

        @Override // android.widget.CompoundButton.OnCheckedChangeListener
        public void onCheckedChanged(CompoundButton compoundButton, boolean z) {
            BaseVideoEditActivity baseVideoEditActivity = BaseVideoEditActivity.this;
            if (z) {
                baseVideoEditActivity.r1();
            } else {
                baseVideoEditActivity.X0(true);
            }
        }
    }

    class e implements VideoEditTimeLineController.OnTimeLineListener {
        e() {
        }

        @Override // com.byd.view.VideoEditTimeLineController.OnTimeLineListener
        public void onDraggingEnd(int i) {
            if (i == 3) {
                w.c("onDraggingEnd , seek");
                BaseVideoEditActivity baseVideoEditActivity = BaseVideoEditActivity.this;
                baseVideoEditActivity.h1(baseVideoEditActivity.v.getProgress());
            }
            if (BaseVideoEditActivity.this.T.isPlaying()) {
                BaseVideoEditActivity.this.g1();
            }
        }

        @Override // com.byd.view.VideoEditTimeLineController.OnTimeLineListener
        public void onDraggingStart(int i) {
            BaseVideoEditActivity.this.x1();
            if (i == 1 || i == 2) {
                BaseVideoEditActivity.this.X0(true);
            }
        }

        @Override // com.byd.view.VideoEditTimeLineController.OnTimeLineListener
        public void onProgressChanged(int i, int i2, int i3) {
            if ((i2 == 1 || i2 == 2) && i3 == 3) {
                w.c("onProgressChanged , seek");
                BaseVideoEditActivity baseVideoEditActivity = BaseVideoEditActivity.this;
                baseVideoEditActivity.h1(baseVideoEditActivity.v.getProgress());
            }
        }
    }

    class f implements AudioManager.OnAudioFocusChangeListener {
        f() {
        }

        @Override // android.media.AudioManager.OnAudioFocusChangeListener
        public void onAudioFocusChange(int i) {
            String str;
            BaseVideoEditActivity baseVideoEditActivity = BaseVideoEditActivity.this;
            if (baseVideoEditActivity.W || baseVideoEditActivity.X) {
                str = "can't play --> VideoEditActivity onAudioFocusChange:" + i;
            } else {
                w.c(hashCode() + " VideoEditActivity onAudioFocusChange:" + i);
                if (i == -3) {
                    if (BaseVideoEditActivity.this.V0()) {
                        w.c("==AUDIOFOCUS_LOSS_TRANSIENT_CAN_DUCK==down volume");
                        BaseVideoEditActivity.this.T.setVolume(0.8f, 0.8f);
                        return;
                    }
                    return;
                }
                if (i == -2 || i == -1) {
                    BaseVideoEditActivity.this.X0(false);
                    return;
                }
                if (i != 1 || BaseVideoEditActivity.this.T == null || !BaseVideoEditActivity.this.P) {
                    return;
                }
                w.c("==AUDIOFOCUS_GAIN==resume volume");
                BaseVideoEditActivity.this.T.setVolume(1.0f, 1.0f);
                if (!BaseVideoEditActivity.this.T.isPlaying()) {
                    BaseVideoEditActivity.this.r1();
                    return;
                }
                str = "==is playing==";
            }
            w.c(str);
        }
    }

    class g implements AlertDialogFragment.OnAlertDialogButtonClick {
        g() {
        }

        @Override // com.byd.fragment.AlertDialogFragment.OnAlertDialogButtonClick
        public boolean onClick(int i) {
            BaseVideoEditActivity.this.w1();
            BaseVideoEditActivity.this.finish();
            return true;
        }
    }

    class h implements c.b {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        int f1029a = -1;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        long f1030b;

        h() {
        }

        @Override // b.a.e.c.b
        public boolean o(int i) {
            return true;
        }

        @Override // b.a.e.c.b
        public boolean onItemClick(int i) {
            long jCurrentTimeMillis = System.currentTimeMillis();
            if (jCurrentTimeMillis - this.f1030b <= 500) {
                w.c("VideoEdit file item click too fast");
                return false;
            }
            this.f1030b = jCurrentTimeMillis;
            if (this.f1029a == i) {
                return false;
            }
            this.f1029a = i;
            BaseVideoEditActivity.this.N0(i);
            BaseVideoEditActivity.this.O.scrollToPosition(i);
            return true;
        }

        @Override // b.a.e.c.b
        public void p(int i) {
        }
    }

    class i implements TextureView.SurfaceTextureListener {
        i() {
        }

        @Override // android.view.TextureView.SurfaceTextureListener
        public void onSurfaceTextureAvailable(SurfaceTexture surfaceTexture, int i, int i2) {
            w.c("SurfaceTextureListener->onSurfaceTextureAvailable : " + i + " " + i2);
            BaseVideoEditActivity.this.r.u(new Surface(surfaceTexture));
            BaseVideoEditActivity.this.r.w(i, i2);
        }

        @Override // android.view.TextureView.SurfaceTextureListener
        public boolean onSurfaceTextureDestroyed(SurfaceTexture surfaceTexture) {
            BaseVideoEditActivity.this.r.v();
            w.c("SurfaceTextureListener->onSurfaceTextureDestroyed");
            return true;
        }

        @Override // android.view.TextureView.SurfaceTextureListener
        public void onSurfaceTextureSizeChanged(SurfaceTexture surfaceTexture, int i, int i2) {
            w.c("SurfaceTextureListener->onSurfaceTextureSizeChanged : " + i + " " + i2);
            BaseVideoEditActivity.this.r.w(i, i2);
        }

        @Override // android.view.TextureView.SurfaceTextureListener
        public void onSurfaceTextureUpdated(SurfaceTexture surfaceTexture) {
        }
    }

    class j implements VideoPreview.OnSizeChangeListener {
        j() {
        }

        @Override // com.byd.view.VideoPreview.OnSizeChangeListener
        public void onSizeChange(int i, int i2) {
            w.c("on video preview size change : " + i + " " + i2);
            FrameLayout.LayoutParams layoutParams = (FrameLayout.LayoutParams) BaseVideoEditActivity.this.u.getLayoutParams();
            layoutParams.width = i;
            layoutParams.height = i2;
            BaseVideoEditActivity.this.u.requestLayout();
        }
    }

    class k implements PositiveAndNegativeSeekBar.OnProgressChangeListener {
        k() {
        }

        @Override // com.byd.view.PositiveAndNegativeSeekBar.OnProgressChangeListener
        public void onProgressChange(PositiveAndNegativeSeekBar positiveAndNegativeSeekBar, int i, boolean z) {
            if (BaseVideoEditActivity.this.F0(i)) {
                BaseVideoEditActivity.this.r.n();
            }
        }

        @Override // com.byd.view.PositiveAndNegativeSeekBar.OnProgressChangeListener
        public void onStartTracking(PositiveAndNegativeSeekBar positiveAndNegativeSeekBar) {
        }

        @Override // com.byd.view.PositiveAndNegativeSeekBar.OnProgressChangeListener
        public void onStopTracking(PositiveAndNegativeSeekBar positiveAndNegativeSeekBar) {
            if (BaseVideoEditActivity.this.F0(positiveAndNegativeSeekBar.getProgress())) {
                BaseVideoEditActivity.this.J0();
            }
            BaseVideoEditActivity.this.G0();
        }
    }

    class l implements ViewTreeObserver.OnPreDrawListener {
        l() {
        }

        @Override // android.view.ViewTreeObserver.OnPreDrawListener
        public boolean onPreDraw() {
            BaseVideoEditActivity.this.O.getViewTreeObserver().removeOnPreDrawListener(this);
            BaseVideoEditActivity.this.D0();
            return true;
        }
    }

    class m extends com.byd.taskmanager.b.a<Long> {
        m() {
        }

        /* JADX INFO: Access modifiers changed from: protected */
        /* JADX WARN: Code restructure failed: missing block: B:10:0x002b, code lost:
        
            r0.selectTrack(r3);
            r5 = r4.getLong("durationUs");
            r8.f1036a.i0 = r4.getInteger("frame-rate");
         */
        @Override // com.byd.taskmanager.b.a
        /* JADX INFO: renamed from: b, reason: merged with bridge method [inline-methods] */
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        public java.lang.Long doTask() throws java.io.IOException {
            /*
                r8 = this;
                android.media.MediaExtractor r0 = new android.media.MediaExtractor
                r0.<init>()
                r1 = 0
                com.byd.auto_photo.BaseVideoEditActivity r3 = com.byd.auto_photo.BaseVideoEditActivity.this     // Catch: java.lang.Throwable -> L48 java.lang.Exception -> L4a
                java.lang.String r3 = com.byd.auto_photo.BaseVideoEditActivity.x0(r3)     // Catch: java.lang.Throwable -> L48 java.lang.Exception -> L4a
                r0.setDataSource(r3)     // Catch: java.lang.Throwable -> L48 java.lang.Exception -> L4a
                r3 = 0
            L11:
                int r4 = r0.getTrackCount()     // Catch: java.lang.Throwable -> L48 java.lang.Exception -> L4a
                if (r3 >= r4) goto L43
                android.media.MediaFormat r4 = r0.getTrackFormat(r3)     // Catch: java.lang.Throwable -> L48 java.lang.Exception -> L4a
                java.lang.String r5 = "mime"
                java.lang.String r5 = r4.getString(r5)     // Catch: java.lang.Throwable -> L48 java.lang.Exception -> L4a
                if (r5 == 0) goto L40
                java.lang.String r6 = "video/"
                boolean r5 = r5.startsWith(r6)     // Catch: java.lang.Throwable -> L48 java.lang.Exception -> L4a
                if (r5 == 0) goto L40
                r0.selectTrack(r3)     // Catch: java.lang.Throwable -> L48 java.lang.Exception -> L4a
                java.lang.String r3 = "durationUs"
                long r5 = r4.getLong(r3)     // Catch: java.lang.Throwable -> L48 java.lang.Exception -> L4a
                com.byd.auto_photo.BaseVideoEditActivity r3 = com.byd.auto_photo.BaseVideoEditActivity.this     // Catch: java.lang.Throwable -> L48 java.lang.Exception -> L4a
                java.lang.String r7 = "frame-rate"
                int r4 = r4.getInteger(r7)     // Catch: java.lang.Throwable -> L48 java.lang.Exception -> L4a
                com.byd.auto_photo.BaseVideoEditActivity.y0(r3, r4)     // Catch: java.lang.Throwable -> L48 java.lang.Exception -> L4a
                goto L44
            L40:
                int r3 = r3 + 1
                goto L11
            L43:
                r5 = r1
            L44:
                r0.release()
                goto L69
            L48:
                r1 = move-exception
                goto L7e
            L4a:
                r3 = move-exception
                r3.printStackTrace()     // Catch: java.lang.Throwable -> L48
                r5 = -1
                java.lang.StringBuilder r4 = new java.lang.StringBuilder     // Catch: java.lang.Throwable -> L48
                r4.<init>()     // Catch: java.lang.Throwable -> L48
                java.lang.String r7 = "decode duration exception : "
                r4.append(r7)     // Catch: java.lang.Throwable -> L48
                java.lang.String r3 = r3.getMessage()     // Catch: java.lang.Throwable -> L48
                r4.append(r3)     // Catch: java.lang.Throwable -> L48
                java.lang.String r3 = r4.toString()     // Catch: java.lang.Throwable -> L48
                com.byd.util.w.c(r3)     // Catch: java.lang.Throwable -> L48
                goto L44
            L69:
                com.byd.auto_photo.BaseVideoEditActivity r0 = com.byd.auto_photo.BaseVideoEditActivity.this
                r0.S = r5
                com.byd.auto_photo.BaseVideoEditActivity.z0(r0)
                int r0 = (r5 > r1 ? 1 : (r5 == r1 ? 0 : -1))
                if (r0 <= 0) goto L79
                com.byd.auto_photo.BaseVideoEditActivity r0 = com.byd.auto_photo.BaseVideoEditActivity.this
                com.byd.auto_photo.BaseVideoEditActivity.A0(r0, r5)
            L79:
                java.lang.Long r0 = java.lang.Long.valueOf(r5)
                return r0
            L7e:
                r0.release()
                throw r1
            */
            throw new UnsupportedOperationException("Method not decompiled: com.byd.auto_photo.BaseVideoEditActivity.m.doTask():java.lang.Long");
        }
    }

    class n extends com.byd.taskmanager.b.c<Long> {
        n() {
        }

        @Override // com.byd.taskmanager.b.b
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public void onComplete(String str, Long l) {
            if (l == null || l.longValue() != -1) {
                return;
            }
            BaseVideoEditActivity.this.n1();
        }

        @Override // com.byd.taskmanager.b.b
        public void onStart() {
        }
    }

    class o implements Runnable {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final /* synthetic */ List f1038a;

        o(List list) {
            this.f1038a = list;
        }

        @Override // java.lang.Runnable
        public void run() {
            BaseVideoEditActivity.this.v.showVideoFrames(this.f1038a);
        }
    }

    class p implements SurfaceTexture.OnFrameAvailableListener {
        p() {
        }

        @Override // android.graphics.SurfaceTexture.OnFrameAvailableListener
        public void onFrameAvailable(SurfaceTexture surfaceTexture) {
            BaseVideoEditActivity.this.r.o(BaseVideoEditActivity.this.T.getCurrentPosition() * 1000);
        }
    }

    private static class q extends b.a.d<BaseVideoEditActivity> {
        public q(BaseVideoEditActivity baseVideoEditActivity) {
            super(baseVideoEditActivity);
        }

        @Override // b.a.d
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public void handleMessage(BaseVideoEditActivity baseVideoEditActivity, Message message) {
            int i = message.what;
            if (i == 1) {
                baseVideoEditActivity.P0();
            } else if (i == 2) {
                baseVideoEditActivity.O0(((Integer) message.obj).intValue());
            } else {
                if (i != 4) {
                    return;
                }
                baseVideoEditActivity.R0();
            }
        }
    }

    private void C0(List<Bitmap> list) {
        runOnUiThread(new o(list));
    }

    private void E0() {
        boolean z;
        j.a aVarH = this.c0.h();
        com.byd.filter2.g.d dVarG = this.c0.g();
        if (aVarH == null) {
            this.v.setStartProgress(0);
            VideoEditTimeLineController videoEditTimeLineController = this.v;
            videoEditTimeLineController.setEndProgress(videoEditTimeLineController.getMax());
            boolean z2 = true;
            if (dVarG != null) {
                dVarG.S(0, 0, 0);
                z = true;
            } else {
                z = false;
            }
            if (this.r.i() != null) {
                this.r.m(null);
                this.d0.n(0);
            } else {
                z2 = z;
            }
            this.u.resetCrop();
            if (!z2) {
                return;
            }
        } else {
            this.v.setStartProgress(aVarH.g());
            int iD = aVarH.d();
            VideoEditTimeLineController videoEditTimeLineController2 = this.v;
            if (iD == 0) {
                iD = videoEditTimeLineController2.getMax();
            }
            videoEditTimeLineController2.setEndProgress(iD);
            if (dVarG != null) {
                dVarG.S(aVarH.f(), aVarH.a(), aVarH.b());
            }
            com.byd.filter2.e.b bVarE = aVarH.e();
            this.r.m(bVarE);
            this.d0.n(bVarE != null ? bVarE.i() : 0);
            if (aVarH.c() != null) {
                this.u.setClipBound(aVarH.c());
            } else {
                this.u.resetCrop();
            }
        }
        J0();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean F0(int i2) {
        this.C.setText(String.valueOf(i2));
        com.byd.filter2.g.d dVarG = this.c0.g();
        if (dVarG == null) {
            return false;
        }
        if (this.K.isChecked()) {
            this.Z = i2;
            dVarG.V(i2);
            return true;
        }
        if (this.L.isChecked()) {
            this.a0 = i2;
            dVarG.U(i2);
            return true;
        }
        if (!this.M.isChecked()) {
            return false;
        }
        this.b0 = i2;
        dVarG.W(i2);
        return true;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void G0() {
        this.N.setEnabled((this.Z == 0 && this.a0 == 0 && this.b0 == 0) ? false : true);
    }

    private boolean H0() {
        if (!this.G.isChecked()) {
            return false;
        }
        if (this.c0.h() == null && this.v.getStartProgress() == 0 && this.v.getEndProgress() == this.v.getMax()) {
            return true;
        }
        this.c0.e(this.v.getStartProgress(), this.v.getEndProgress());
        return true;
    }

    private boolean I0() {
        if (!this.J.isChecked()) {
            return false;
        }
        if (this.c0.h() == null && this.u.isCropZoneNoChange()) {
            return true;
        }
        this.c0.c(this.u.getClipBound());
        return true;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void J0() {
        if (V0()) {
            return;
        }
        for (int i2 = 0; i2 < 10; i2++) {
            this.r.n();
        }
    }

    private synchronized void K0() {
        if (!this.Q) {
            w.c("check play fail -> gl surface not create");
        } else if (this.R) {
            Y0();
        } else {
            w.c("check play fail -> permission not granted");
        }
    }

    private void M0() {
        if (z(1)) {
            w.c("video edit decode info task exist");
        } else {
            v(1, new m(), new n(), true).w(1);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    public void N0(int i2) {
        Resources resources;
        ImageEditFilterItem imageEditFilterItem;
        ImageEditFilterItem imageEditFilterItem2;
        com.byd.filter2.e.b hVar;
        switch (i2) {
            case 0:
                this.r.m(null);
                J0();
                resources = getResources();
                imageEditFilterItem = this.f0[i2];
                break;
            case 1:
                if (this.f0[i2].getFilter() == null) {
                    imageEditFilterItem2 = this.f0[i2];
                    hVar = new com.byd.filter2.g.h(getResources());
                    imageEditFilterItem2.setFilter(hVar);
                }
                this.r.m(this.f0[i2].getFilter().x(0L, this.S).A(i2));
                J0();
                resources = getResources();
                imageEditFilterItem = this.f0[i2];
                break;
            case 2:
                if (this.f0[i2].getFilter() == null) {
                    imageEditFilterItem2 = this.f0[i2];
                    hVar = new com.byd.filter2.g.c(getResources());
                    imageEditFilterItem2.setFilter(hVar);
                }
                this.r.m(this.f0[i2].getFilter().x(0L, this.S).A(i2));
                J0();
                resources = getResources();
                imageEditFilterItem = this.f0[i2];
                break;
            case 3:
                if (this.f0[i2].getFilter() == null) {
                    imageEditFilterItem2 = this.f0[i2];
                    hVar = new com.byd.filter2.g.b(getResources());
                    imageEditFilterItem2.setFilter(hVar);
                }
                this.r.m(this.f0[i2].getFilter().x(0L, this.S).A(i2));
                J0();
                resources = getResources();
                imageEditFilterItem = this.f0[i2];
                break;
            case 4:
                if (this.f0[i2].getFilter() == null) {
                    imageEditFilterItem2 = this.f0[i2];
                    hVar = new com.byd.filter2.g.f(getResources());
                    imageEditFilterItem2.setFilter(hVar);
                }
                this.r.m(this.f0[i2].getFilter().x(0L, this.S).A(i2));
                J0();
                resources = getResources();
                imageEditFilterItem = this.f0[i2];
                break;
            case 5:
                if (this.f0[i2].getFilter() == null) {
                    imageEditFilterItem2 = this.f0[i2];
                    hVar = new com.byd.filter2.g.j(getResources());
                    imageEditFilterItem2.setFilter(hVar);
                }
                this.r.m(this.f0[i2].getFilter().x(0L, this.S).A(i2));
                J0();
                resources = getResources();
                imageEditFilterItem = this.f0[i2];
                break;
            case 6:
                if (this.f0[i2].getFilter() == null) {
                    imageEditFilterItem2 = this.f0[i2];
                    hVar = new com.byd.filter2.g.g(getResources());
                    imageEditFilterItem2.setFilter(hVar);
                }
                this.r.m(this.f0[i2].getFilter().x(0L, this.S).A(i2));
                J0();
                resources = getResources();
                imageEditFilterItem = this.f0[i2];
                break;
            case 7:
                if (this.f0[i2].getFilter() == null) {
                    imageEditFilterItem2 = this.f0[i2];
                    hVar = new com.byd.filter2.g.m(getResources());
                    imageEditFilterItem2.setFilter(hVar);
                }
                this.r.m(this.f0[i2].getFilter().x(0L, this.S).A(i2));
                J0();
                resources = getResources();
                imageEditFilterItem = this.f0[i2];
                break;
            case 8:
                if (this.f0[i2].getFilter() == null) {
                    imageEditFilterItem2 = this.f0[i2];
                    hVar = new com.byd.filter2.g.i(getResources());
                    imageEditFilterItem2.setFilter(hVar);
                }
                this.r.m(this.f0[i2].getFilter().x(0L, this.S).A(i2));
                J0();
                resources = getResources();
                imageEditFilterItem = this.f0[i2];
                break;
            case 9:
                if (this.f0[i2].getFilter() == null) {
                    imageEditFilterItem2 = this.f0[i2];
                    hVar = new com.byd.filter2.g.a(getResources());
                    imageEditFilterItem2.setFilter(hVar);
                }
                this.r.m(this.f0[i2].getFilter().x(0L, this.S).A(i2));
                J0();
                resources = getResources();
                imageEditFilterItem = this.f0[i2];
                break;
            case 10:
                if (this.f0[i2].getFilter() == null) {
                    imageEditFilterItem2 = this.f0[i2];
                    hVar = new com.byd.filter2.g.l(getResources());
                    imageEditFilterItem2.setFilter(hVar);
                }
                this.r.m(this.f0[i2].getFilter().x(0L, this.S).A(i2));
                J0();
                resources = getResources();
                imageEditFilterItem = this.f0[i2];
                break;
            case 11:
                if (this.f0[i2].getFilter() == null) {
                    imageEditFilterItem2 = this.f0[i2];
                    hVar = new com.byd.filter2.g.k(getResources());
                    imageEditFilterItem2.setFilter(hVar);
                }
                this.r.m(this.f0[i2].getFilter().x(0L, this.S).A(i2));
                J0();
                resources = getResources();
                imageEditFilterItem = this.f0[i2];
                break;
            default:
                this.r.m(this.f0[i2].getFilter().x(0L, this.S).A(i2));
                J0();
                resources = getResources();
                imageEditFilterItem = this.f0[i2];
                break;
        }
        o1(resources.getString(imageEditFilterItem.getNameRes()));
    }

    private void Q0() {
        this.y.setVisibility(4);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void R0() {
        PopupWindow popupWindow = this.s0;
        if (popupWindow == null || !popupWindow.isShowing()) {
            return;
        }
        this.s0.dismiss();
    }

    private void S0() {
        com.byd.filter2.g.d dVarG = this.c0.g();
        if (dVarG == null) {
            w.c("initAdjustValue fail -> filter is null");
            return;
        }
        this.b0 = dVarG.R();
        this.a0 = dVarG.P();
        this.Z = dVarG.Q();
    }

    private void T0() {
        ImageEditFilterItem[] imageEditFilterItemArr = new ImageEditFilterItem[12];
        this.f0 = imageEditFilterItemArr;
        imageEditFilterItemArr[0] = new ImageEditFilterItem(R.drawable.icon_filter_none);
        this.f0[0].setNameRes(R.string.filter_original);
        this.f0[1] = new ImageEditFilterItem(R.drawable.icon_filter_lomo);
        this.f0[1].setNameRes(R.string.filter_lomo);
        this.f0[2] = new ImageEditFilterItem(R.drawable.icon_filter_classify);
        this.f0[2].setNameRes(R.string.filter_classical);
        this.f0[3] = new ImageEditFilterItem(R.drawable.icon_filter_freshness);
        this.f0[3].setNameRes(R.string.filter_freshness);
        this.f0[4] = new ImageEditFilterItem(R.drawable.icon_filter_gorgeous);
        this.f0[4].setNameRes(R.string.filter_gorgeous);
        this.f0[5] = new ImageEditFilterItem(R.drawable.icon_filter_romance);
        this.f0[5].setNameRes(R.string.filter_romantic);
        this.f0[6] = new ImageEditFilterItem(R.drawable.icon_filter_height_cold);
        this.f0[6].setNameRes(R.string.filter_reserved);
        this.f0[7] = new ImageEditFilterItem(R.drawable.icon_filter_dream);
        this.f0[7].setNameRes(R.string.filter_dream);
        this.f0[8] = new ImageEditFilterItem(R.drawable.icon_filter_nostalgic);
        this.f0[8].setNameRes(R.string.filter_retro);
        this.f0[9] = new ImageEditFilterItem(R.drawable.icon_filter_black_wite);
        this.f0[9].setNameRes(R.string.filter_gray);
        this.f0[10] = new ImageEditFilterItem(R.drawable.icon_filter_sketch);
        this.f0[10].setNameRes(R.string.filter_sketch);
        this.f0[11] = new ImageEditFilterItem(R.drawable.icon_filter_simple_and_elegant);
        this.f0[11].setNameRes(R.string.filter_elegant);
        this.d0.q(this.f0);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void U0() {
        String str;
        long j2 = this.S;
        if (j2 == 0) {
            str = "init filters fail -> duration is 0";
        } else {
            this.c0.b(this.r, 0L, j2);
            str = "init filters OK";
        }
        w.c(str);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void X0(boolean z) {
        this.Y.removeMessages(1);
        MediaPlayer mediaPlayer = this.T;
        if (mediaPlayer != null && mediaPlayer.isPlaying()) {
            this.T.pause();
            h();
        }
        this.W = z;
    }

    private void Y0() {
        if (!Z0()) {
            f1();
            return;
        }
        MediaPlayer mediaPlayer = this.T;
        if (mediaPlayer == null) {
            s1();
            MediaPlayer mediaPlayer2 = new MediaPlayer();
            this.T = mediaPlayer2;
            mediaPlayer2.setOnPreparedListener(this.k0);
            this.T.setOnErrorListener(this.l0);
            this.T.setOnVideoSizeChangedListener(this.m0);
            this.T.setLooping(true);
            this.T.setAudioStreamType(3);
            SurfaceTexture surfaceTextureJ = this.r.j();
            surfaceTextureJ.setOnFrameAvailableListener(new p(), this.o0);
            this.T.setSurface(new Surface(surfaceTextureJ));
        } else {
            mediaPlayer.reset();
        }
        try {
            this.T.setDataSource(this.s);
            this.T.prepareAsync();
            Log.e("m_tag", "play prepareAsync");
        } catch (Exception e2) {
            e2.printStackTrace();
            Log.e("m_tag", "play exception : " + e2.getMessage());
        }
    }

    private boolean Z0() {
        int i2 = Build.VERSION.SDK_INT;
        this.V = com.byd.util.e.L(this, this.q0, 3, 1, true);
        w.c("VideoEditActivity -> requestAudioFocus : " + this.V);
        AudioFocusStateInfo audioFocusStateInfo = this.V;
        return audioFocusStateInfo != null && audioFocusStateInfo.getState() == 1;
    }

    private void a1() {
        this.b0 = 0;
        this.Z = 0;
        this.a0 = 0;
        Q0();
        this.K.setChecked(false);
        this.L.setChecked(false);
        this.M.setChecked(false);
        com.byd.filter2.g.d dVarG = this.c0.g();
        if (dVarG != null) {
            dVarG.S(0, 0, 0);
            J0();
        }
    }

    private void b1() {
        if (H0()) {
            d1();
        }
        if (I0()) {
            c1();
        }
    }

    private void c1() {
        this.J.setChecked(false);
        this.u.setCropEnable(false);
    }

    private void d1() {
        this.G.setChecked(false);
        this.v.setClipEditMode(false);
    }

    private void e0() {
        AudioFocusStateInfo audioFocusStateInfo = this.V;
        if (audioFocusStateInfo != null) {
            audioFocusStateInfo.abandonAudioFocus(this.U);
            w.c("VideoEditActivity abandonAudioFocus");
        }
    }

    private void e1(View view) {
        if (!this.M.equals(view) && this.M.isChecked()) {
            this.M.setChecked(false);
        }
        if (!this.L.equals(view) && this.L.isChecked()) {
            this.L.setChecked(false);
        }
        if (this.K.equals(view) || !this.K.isChecked()) {
            return;
        }
        this.K.setChecked(false);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void g1() {
        this.Y.sendEmptyMessageDelayed(1, 50L);
    }

    /* JADX INFO: Access modifiers changed from: private */
    @SuppressLint({"WrongConstant"})
    public void h1(int i2) {
        if (Build.VERSION.SDK_INT >= 26) {
            this.T.seekTo(i2, 3);
        } else {
            this.T.seekTo(i2);
        }
    }

    private void i1(int i2) {
        j1(i2, true);
    }

    private void j1(int i2, boolean z) {
        this.B.setOnProgressChangeListener(null);
        this.B.setProgress(i2);
        this.C.setText(String.valueOf(i2));
        if (z && this.y.getVisibility() != 0) {
            this.y.setVisibility(0);
        }
        this.B.setOnProgressChangeListener(this.j0);
    }

    private void k1(boolean z) {
        if (this.w.isChecked() == z) {
            return;
        }
        this.w.setOnCheckedChangeListener(null);
        this.w.setChecked(z);
        this.w.setOnCheckedChangeListener(this.n0);
    }

    private void l1(boolean z) {
        int i2;
        ImageView imageView;
        if (!z) {
            i2 = 8;
            if (this.x.getVisibility() == 8) {
                return;
            } else {
                imageView = this.x;
            }
        } else {
            if (this.x.getVisibility() == 0) {
                return;
            }
            imageView = this.x;
            i2 = 0;
        }
        imageView.setVisibility(i2);
    }

    private void m1() {
        if (this.A.getVisibility() == 0) {
            return;
        }
        this.z.setVisibility(4);
        this.A.setVisibility(0);
        W0(true);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public synchronized void n1() {
        if (this.t0 == null) {
            AlertDialogFragment alertDialogFragmentNewInstance = AlertDialogFragment.newInstance(R.string.video_edit_decode_error_title, R.string.video_edit_decode_error_content);
            this.t0 = alertDialogFragmentNewInstance;
            alertDialogFragmentNewInstance.setBtnConfig(1);
            this.t0.setOnAlertDialogButtonClick(new g());
        }
        if (this.t0.isShowing()) {
            return;
        }
        this.t0.show(getSupportFragmentManager(), "decode_error");
    }

    private void o1(String str) {
        this.Y.removeMessages(4);
        if (this.s0 == null) {
            TextView textView = new TextView(this);
            this.r0 = textView;
            textView.setBackgroundResource(R.drawable.bg_filter_name_drawable);
            this.r0.setTextColor(-1);
            this.r0.setLayoutParams(new WindowManager.LayoutParams(-2, -2));
            PopupWindow popupWindow = new PopupWindow(this.r0, -2, -2);
            this.s0 = popupWindow;
            popupWindow.setOutsideTouchable(false);
            this.s0.setFocusable(false);
        }
        this.r0.setText(str);
        this.r0.setGravity(17);
        if (!this.s0.isShowing()) {
            this.s0.showAtLocation(getWindow().getDecorView(), 49, 0, 100);
        }
        this.Y.sendEmptyMessageDelayed(4, 1500L);
    }

    private void p1() {
        if (this.O.getVisibility() == 0) {
            return;
        }
        this.O.getViewTreeObserver().addOnPreDrawListener(new l());
        this.z.setVisibility(4);
        this.O.setVisibility(0);
        W0(true);
    }

    private void q1() {
        this.z.setVisibility(0);
        this.A.setVisibility(8);
        this.y.setVisibility(4);
        this.O.setVisibility(8);
        W0(false);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void r1() {
        MediaPlayer mediaPlayer = this.T;
        if (mediaPlayer == null) {
            Y0();
            return;
        }
        if (mediaPlayer.isPlaying()) {
            return;
        }
        if (!Z0()) {
            w.c("VideoEditActivity requestAudioFocus fail!!");
            f1();
            return;
        }
        if (this.T.getCurrentPosition() >= this.v.getEndProgress()) {
            int startProgress = this.v.getStartProgress();
            h1(startProgress);
            this.v.setProgress(startProgress);
        }
        this.T.start();
        f();
        this.Y.sendEmptyMessageDelayed(1, 50L);
    }

    private void s1() {
        if (this.o0 == null) {
            HandlerThread handlerThread = new HandlerThread("videoFrame");
            handlerThread.start();
            this.o0 = new Handler(handlerThread.getLooper());
        }
    }

    private void t1() {
        Intent intent = new Intent(this, (Class<?>) VideoEditExportActivity.class);
        intent.putExtra("path", this.s);
        if (!this.u.isCropZoneNoChange()) {
            Rect rect = new Rect(this.u.getClipBound());
            int measuredWidth = this.u.getMeasuredWidth();
            int measuredHeight = this.u.getMeasuredHeight();
            int i2 = this.g0;
            int i3 = this.h0;
            z1(rect, measuredWidth / i2, measuredHeight / i3, i2, i3);
            intent.putExtra("crop_rect", rect);
        }
        intent.putExtra("from_pos", this.v.getStartProgress());
        intent.putExtra("end_pos", this.v.getEndProgress());
        intent.putExtra("video_width", this.g0);
        intent.putExtra("video_height", this.h0);
        intent.putExtra("frame_rate", this.i0);
        intent.putExtra("duration_us", this.S);
        startActivity(intent);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void u1(long j2) throws IOException {
        int i2 = ((int) ((j2 / 1000) / 1000)) + 1;
        w.c("showVideoFrames -> decode frame count : " + i2);
        MediaMetadataRetriever mediaMetadataRetriever = new MediaMetadataRetriever();
        try {
            try {
                mediaMetadataRetriever.setDataSource(new FileInputStream(this.s).getFD());
                LinkedList linkedList = new LinkedList();
                long j3 = 1000000;
                for (int i3 = 1; i3 <= i2; i3++) {
                    linkedList.add(mediaMetadataRetriever.getScaledFrameAtTime(j3, 0, 120, 120));
                    if (i3 % 5 == 0) {
                        C0(linkedList);
                        linkedList = new LinkedList();
                    }
                    j3 += 1000000;
                }
                if (linkedList.size() > 0) {
                    C0(linkedList);
                }
            } catch (Exception e2) {
                e2.printStackTrace();
                w.c("get frame exception : " + e2.getMessage());
            }
        } finally {
            mediaMetadataRetriever.release();
        }
    }

    private void v1() {
        Handler handler = this.o0;
        if (handler != null) {
            handler.getLooper().quitSafely();
            this.o0 = null;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void w1() {
        this.r.j().setOnFrameAvailableListener(null);
        MediaPlayer mediaPlayer = this.T;
        if (mediaPlayer != null) {
            mediaPlayer.stop();
            this.T.release();
            this.T = null;
        }
        v1();
        this.r.l();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void x1() {
        this.Y.removeMessages(1);
    }

    private void y1() {
        if (V0()) {
            X0(true);
        } else {
            r1();
        }
    }

    private void z1(Rect rect, float f2, float f3, int i2, int i3) {
        int i4 = (int) (rect.left / f2);
        int i5 = (int) (rect.top / f3);
        int i6 = (int) (rect.right / f2);
        int i7 = (int) (rect.bottom / f3);
        if (i4 < 0) {
            i4 = 0;
        }
        if (i5 < 0) {
            i5 = 0;
        }
        if (i6 <= i2) {
            i2 = i6;
        }
        if (i7 <= i3) {
            i3 = i7;
        }
        rect.set(i4, i5, i2, i3);
    }

    protected void D0() {
        int measuredHeight = this.O.getMeasuredHeight();
        this.d0.A(this.O.getMeasuredWidth(), measuredHeight, this.e0);
    }

    protected void L0() {
        this.d0.t(1);
        this.O.setLayoutManager(new LinearLayoutManager(this, 0, false));
        this.O.addItemDecoration(new ListItemDecoration(this.e0, true));
    }

    @Override // com.byd.theme.ThemeActivity
    public void N() {
        setContentView(R.layout.activity_video_edit);
        this.t = (VideoPreview) x(R.id.video_view);
        this.x = (ImageView) x(R.id.btn_play);
        this.D = (TextView) findViewById(R.id.btn_export);
        this.E = findViewById(R.id.btn_cancel);
        this.F = findViewById(R.id.btn_sure);
        this.u = (VideoEditCropZoneView) findViewById(R.id.video_crop_zone_view);
        this.v = (VideoEditTimeLineController) findViewById(R.id.video_edit_time_line_controller);
        CheckBox checkBox = (CheckBox) findViewById(R.id.ch_play_or_pause);
        this.w = checkBox;
        checkBox.setOnCheckedChangeListener(this.n0);
        this.y = findViewById(R.id.container_adjust_seek);
        this.z = findViewById(R.id.container_menu_main);
        this.A = findViewById(R.id.container_menu_adjust);
        this.B = (PositiveAndNegativeSeekBar) findViewById(R.id.seek_video_edit_adjust);
        this.C = (TextView) findViewById(R.id.tv_video_edit_adjust_progress);
        this.G = (CheckedTextView) findViewById(R.id.item_video_clip);
        this.H = (CheckedTextView) findViewById(R.id.item_video_adjust);
        this.I = (CheckedTextView) findViewById(R.id.item_video_filter);
        this.J = (CheckedTextView) findViewById(R.id.item_video_crop);
        this.N = (TextView) findViewById(R.id.item_adjust_reset);
        this.K = (CheckedTextView) findViewById(R.id.item_adjust_contrast);
        this.L = (CheckedTextView) findViewById(R.id.item_adjust_brightness);
        this.M = (CheckedTextView) findViewById(R.id.item_adjust_saturation);
        this.O = (RecyclerView) findViewById(R.id.recycler_filter);
        this.G.setOnClickListener(this);
        this.H.setOnClickListener(this);
        this.I.setOnClickListener(this);
        this.J.setOnClickListener(this);
        this.N.setOnClickListener(this);
        this.K.setOnClickListener(this);
        this.L.setOnClickListener(this);
        this.M.setOnClickListener(this);
        this.t.setOnClickListener(this);
        this.E.setOnClickListener(this);
        this.D.setOnClickListener(this);
        this.F.setOnClickListener(this);
        this.x.setOnClickListener(this);
        b.a.e.h hVar = this.d0;
        if (hVar == null) {
            b.a.e.h hVar2 = new b.a.e.h(this);
            this.d0 = hVar2;
            hVar2.w(true);
            this.d0.v(true);
            this.d0.n(0);
            T0();
            this.d0.s(new h());
        } else {
            hVar.k();
        }
        this.O.setAdapter(this.d0);
        L0();
        this.v.setOnTimeLineListener(this.p0);
        this.t.setSurfaceTextureListener(new i());
        this.t.setOnSizeChangeListener(new j());
        G0();
    }

    protected void O0(int i2) {
        w.c("set max : " + i2);
        this.v.setMax(i2);
    }

    protected void P0() {
        if (V0()) {
            int currentPosition = this.T.getCurrentPosition();
            if (currentPosition < this.v.getStartProgress() || currentPosition >= this.v.getEndProgress()) {
                h1(this.v.getStartProgress());
                if (currentPosition < this.v.getStartProgress()) {
                    VideoEditTimeLineController videoEditTimeLineController = this.v;
                    videoEditTimeLineController.setProgress(videoEditTimeLineController.getStartProgress());
                }
            } else {
                this.v.setProgress(currentPosition);
            }
            this.Y.sendEmptyMessageDelayed(1, 50L);
        }
    }

    @Override // com.byd.theme.ThemeActivity
    protected void S() {
        super.S();
        M0();
        this.R = true;
        K0();
    }

    @Override // com.byd.theme.ThemeActivity
    protected void T(Configuration configuration) {
        this.G.setText(R.string.video_edit_clip);
        this.H.setText(R.string.video_edit_adjust);
        this.I.setText(R.string.filter);
        this.J.setText(R.string.crop);
        this.N.setText(R.string.reset);
        this.K.setText(R.string.video_edit_contrast);
        this.L.setText(R.string.video_edit_brightness);
        this.M.setText(R.string.video_edit_saturation);
        this.D.setText(R.string.video_edit_export);
        b.a.e.h hVar = this.d0;
        if (hVar != null) {
            hVar.notifyDataSetChanged();
        }
    }

    @Override // com.byd.theme.ThemeActivity
    protected void U(Configuration configuration) {
    }

    protected boolean V0() {
        MediaPlayer mediaPlayer = this.T;
        return mediaPlayer != null && mediaPlayer.isPlaying();
    }

    @Override // com.byd.theme.ThemeActivity
    protected String[] W() {
        return x.e((PhotoBaseApp) getApplication());
    }

    protected void W0(boolean z) {
        View view;
        w.c("onSureBtn state change -> " + z);
        int i2 = 0;
        if (z) {
            this.D.setVisibility(4);
            view = this.F;
        } else {
            this.D.setVisibility(0);
            view = this.F;
            i2 = 8;
        }
        view.setVisibility(i2);
    }

    protected void f() {
        k1(true);
        l1(false);
    }

    protected void f1() {
        k1(false);
        l1(true);
    }

    protected void h() {
        k1(false);
        l1(true);
    }

    @Override // androidx.activity.ComponentActivity, android.app.Activity
    public void onBackPressed() {
        super.onBackPressed();
        w1();
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        CheckedTextView checkedTextView;
        int i2;
        CheckedTextView checkedTextView2;
        switch (view.getId()) {
            case R.id.btn_cancel /* 2131230794 */:
                if (this.G.isChecked() || this.H.isChecked() || this.G.isChecked() || this.I.isChecked()) {
                    E0();
                    d1();
                    c1();
                    q1();
                    this.H.setChecked(false);
                    this.I.setChecked(false);
                    W0(false);
                } else {
                    w1();
                    finish();
                }
                break;
            case R.id.btn_export /* 2131230804 */:
                t1();
                break;
            case R.id.btn_play /* 2131230810 */:
                r1();
                break;
            case R.id.btn_sure /* 2131230822 */:
                if (H0()) {
                    d1();
                    W0(false);
                } else if (this.H.isChecked()) {
                    this.H.setChecked(false);
                    this.c0.a(this.Z, this.a0, this.b0);
                    W0(false);
                    q1();
                } else if (this.I.isChecked()) {
                    this.I.setChecked(false);
                    this.c0.d(this.r.i());
                    W0(false);
                    q1();
                } else if (I0()) {
                    c1();
                    W0(false);
                }
                break;
            case R.id.item_adjust_brightness /* 2131230908 */:
                e1(view);
                if (!this.L.isChecked()) {
                    this.L.setChecked(true);
                    i2 = this.a0;
                    i1(i2);
                } else {
                    Q0();
                    checkedTextView = this.L;
                    checkedTextView.setChecked(false);
                }
                break;
            case R.id.item_adjust_contrast /* 2131230909 */:
                e1(view);
                if (!this.K.isChecked()) {
                    this.K.setChecked(true);
                    i2 = this.Z;
                    i1(i2);
                } else {
                    Q0();
                    checkedTextView = this.K;
                    checkedTextView.setChecked(false);
                }
                break;
            case R.id.item_adjust_reset /* 2131230910 */:
                this.N.setEnabled(false);
                a1();
                break;
            case R.id.item_adjust_saturation /* 2131230911 */:
                e1(view);
                if (!this.M.isChecked()) {
                    this.M.setChecked(true);
                    i2 = this.b0;
                    i1(i2);
                } else {
                    Q0();
                    checkedTextView = this.M;
                    checkedTextView.setChecked(false);
                }
                break;
            case R.id.item_video_adjust /* 2131230943 */:
                if (this.S != 0) {
                    b1();
                    this.H.setChecked(true);
                    S0();
                    m1();
                } else {
                    w.c("adjust -> not available video duration");
                }
                break;
            case R.id.item_video_clip /* 2131230944 */:
                if (I0()) {
                    c1();
                }
                H0();
                this.G.toggle();
                this.v.setClipEditMode(this.G.isChecked());
                checkedTextView2 = this.G;
                W0(checkedTextView2.isChecked());
                break;
            case R.id.item_video_crop /* 2131230945 */:
                if (H0()) {
                    d1();
                }
                I0();
                this.J.toggle();
                if (this.u.getVisibility() != 0) {
                    this.u.setVisibility(0);
                }
                this.u.setCropEnable(this.J.isChecked());
                checkedTextView2 = this.J;
                W0(checkedTextView2.isChecked());
                break;
            case R.id.item_video_filter /* 2131230947 */:
                if (this.S != 0) {
                    if (this.r.i() != null && this.r.i().d() == 0) {
                        this.r.i().x(0L, this.S);
                    }
                    b1();
                    this.I.setChecked(true);
                    p1();
                } else {
                    w.c("filter -> not available video duration");
                }
                break;
            case R.id.video_view /* 2131231176 */:
                y1();
                break;
        }
    }

    @Override // com.byd.theme.ThemeActivity, androidx.fragment.app.FragmentActivity, android.app.Activity, android.content.ComponentCallbacks
    public void onConfigurationChanged(Configuration configuration) {
        R0();
        super.onConfigurationChanged(configuration);
    }

    @Override // com.byd.auto_photo.SubBaseTaskActivity, com.byd.theme.ThemeActivity, com.byd.ui.BaseActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    protected void onCreate(Bundle bundle) {
        this.s = getIntent().getStringExtra("photo_intent_data");
        this.Y = new q(this);
        com.byd.filter2.h.h hVar = new com.byd.filter2.h.h(this);
        this.r = hVar;
        hVar.q(this);
        this.r.r();
        this.U = (AudioManager) getSystemService(Car.AUDIO_SERVICE);
        this.c0 = com.byd.filter2.h.j.i(this);
        super.onCreate(bundle);
        b.a.f.a.e().g(this);
    }

    @Override // com.byd.auto_photo.SubBaseTaskActivity, com.byd.theme.ThemeActivity, com.byd.ui.BaseActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    protected void onDestroy() {
        super.onDestroy();
        q qVar = this.Y;
        if (qVar != null) {
            qVar.removeCallbacksAndMessages(null);
            this.Y.release();
        }
        if (this.T != null) {
            w1();
        }
        PopupWindow popupWindow = this.s0;
        if (popupWindow != null) {
            if (popupWindow.isShowing()) {
                this.s0.dismiss();
            }
            this.s0 = null;
        }
        b.a.f.a.e().i(this);
        this.c0.j();
        this.c0 = null;
        this.Y = null;
    }

    @b.a.f.b(threadMode = 1)
    public void onExportResult(com.byd.filter2.h.a aVar) {
        w.c("onExportResult : " + aVar);
        if (!aVar.c()) {
            if (aVar.b() == 2) {
                return;
            }
            Z(R.string.video_edit_export_error);
        } else {
            Intent intent = new Intent();
            intent.putExtra("photo_intent_data", aVar.a());
            setResult(-1, intent);
            finish();
        }
    }

    @Override // androidx.fragment.app.FragmentActivity, android.app.Activity
    protected void onPause() {
        this.X = true;
        super.onPause();
        if (V0()) {
            X0(false);
            f1();
        }
        e0();
    }

    @Override // com.byd.theme.ThemeActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    protected void onResume() {
        super.onResume();
        this.X = false;
        if (this.T == null || !this.P || this.W) {
            return;
        }
        r1();
    }

    @Override // com.byd.filter2.d
    public void onSurfaceCreated(GL10 gl10, EGLConfig eGLConfig) {
        w.c("==on glSurface created==");
        this.Q = true;
        K0();
    }

    @Override // com.byd.filter2.d
    public void t() {
    }
}
