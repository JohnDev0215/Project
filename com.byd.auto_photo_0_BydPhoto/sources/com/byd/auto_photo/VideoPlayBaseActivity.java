package com.byd.auto_photo;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.res.Configuration;
import android.graphics.Bitmap;
import android.graphics.drawable.ColorDrawable;
import android.hardware.bydauto.TsNormalNum;
import android.hardware.bydauto.gearbox.BYDAutoGearboxDevice;
import android.hardware.display.DisplayManager;
import android.media.MediaMetadata;
import android.media.session.MediaSession;
import android.media.session.PlaybackState;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.ParcelFileDescriptor;
import android.os.ResultReceiver;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.SurfaceView;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.view.Window;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.SeekBar;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.view.ViewCompat;
import com.abc.displaylib.DisplayListenUtil;
import com.byd.auto_photo.VideoControlReceiver;
import com.byd.bean.FileBean;
import com.byd.bean.VideoControllerInfo;
import com.byd.share.ShareItem;
import com.byd.util.GearboxUtil;
import com.byd.util.o;
import com.byd.util.p;
import com.byd.util.r;
import com.byd.util.w;
import com.byd.util.x;
import com.byd.view.VideoPlayerBaseView;
import java.io.File;
import java.io.FileDescriptor;
import java.io.FileFilter;
import java.lang.reflect.Method;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public class VideoPlayBaseActivity extends SubBaseTaskActivity implements View.OnClickListener, b.a.i.a {
    private List<FileBean> A;
    private DisplayListenUtil.OneKeyBackLightListener A0;
    private int B;
    protected CheckBox C;
    protected int D;
    protected View E;
    protected View F;
    protected View G;
    protected ImageView H;
    protected ImageView I;
    protected SeekBar J;
    protected ImageView K;
    protected boolean L;
    protected boolean M;
    protected int N;
    protected String O;
    protected p P;
    private ImageView Q;
    private View R;
    private View S;
    protected int T;
    private Uri U;
    protected View V;
    private boolean W;
    private m X;
    protected com.byd.util.c Y;
    protected boolean b0;
    private String c0;
    private int d0;
    protected b.a.i.c e0;
    private int f0;
    private int g0;
    private MediaSession i0;
    private PlaybackState.Builder j0;
    private MediaMetadata.Builder k0;
    private DisplayManager l0;
    protected int n0;
    protected boolean o0;
    private String q0;
    private int r0;
    private ParcelFileDescriptor s0;
    protected VideoPlayerBaseView t;
    protected TextView u;
    protected PopupWindow u0;
    protected TextView v;
    private View v0;
    protected TextView w;
    private n w0;
    protected TextView x;
    protected TextView y;
    private VideoControlReceiver y0;
    protected TextView z;
    protected int r = TsNormalNum.THOUSAND_FIVE;
    protected int s = 2000;
    protected boolean Z = true;
    protected int a0 = 0;
    protected int[] h0 = new int[2];
    protected boolean m0 = false;
    private View.OnTouchListener p0 = new e();
    private CompoundButton.OnCheckedChangeListener t0 = new g();
    private final MediaSession.Callback x0 = new k();
    private VideoControlReceiver.a z0 = new a();

    class a implements VideoControlReceiver.a {
        a() {
        }

        @Override // com.byd.auto_photo.VideoControlReceiver.a
        public void a(int i, boolean z) {
            VideoPlayBaseActivity.this.f1(i, z);
        }

        @Override // com.byd.auto_photo.VideoControlReceiver.a
        public void b(boolean z) {
            VideoPlayBaseActivity.this.q1(z);
        }
    }

    class b implements DisplayListenUtil.OnBackLightStatusChangeListener {
        b() {
        }

        @Override // com.abc.displaylib.DisplayListenUtil.OnBackLightStatusChangeListener
        public void onStatusChange(int i, int i2) {
            w.c("onStatusChange : " + i + " , " + i2 + " , " + VideoPlayBaseActivity.this.n0);
            VideoPlayBaseActivity videoPlayBaseActivity = VideoPlayBaseActivity.this;
            if (i != videoPlayBaseActivity.n0) {
                return;
            }
            videoPlayBaseActivity.V0(i, i2);
        }
    }

    class c implements ViewTreeObserver.OnPreDrawListener {
        c() {
        }

        @Override // android.view.ViewTreeObserver.OnPreDrawListener
        public boolean onPreDraw() {
            VideoPlayBaseActivity.this.C.getViewTreeObserver().removeOnPreDrawListener(this);
            VideoPlayBaseActivity.this.d1();
            return true;
        }
    }

    class d implements ViewTreeObserver.OnPreDrawListener {
        d() {
        }

        @Override // android.view.ViewTreeObserver.OnPreDrawListener
        public boolean onPreDraw() {
            VideoPlayBaseActivity.this.V.getViewTreeObserver().removeOnPreDrawListener(this);
            VideoPlayBaseActivity videoPlayBaseActivity = VideoPlayBaseActivity.this;
            videoPlayBaseActivity.f0 = videoPlayBaseActivity.V.getMeasuredWidth();
            VideoPlayBaseActivity videoPlayBaseActivity2 = VideoPlayBaseActivity.this;
            videoPlayBaseActivity2.g0 = videoPlayBaseActivity2.V.getMeasuredHeight();
            VideoPlayBaseActivity videoPlayBaseActivity3 = VideoPlayBaseActivity.this;
            videoPlayBaseActivity3.V.getLocationOnScreen(videoPlayBaseActivity3.h0);
            VideoPlayBaseActivity videoPlayBaseActivity4 = VideoPlayBaseActivity.this;
            videoPlayBaseActivity4.S0(videoPlayBaseActivity4.V);
            w.c("videoMainContainer pre draw touchZoneWidth : " + VideoPlayBaseActivity.this.f0 + " touchZoneHeight:" + VideoPlayBaseActivity.this.g0 + " pos in screen :" + VideoPlayBaseActivity.this.h0[0] + "," + VideoPlayBaseActivity.this.h0[1]);
            return true;
        }
    }

    class e implements View.OnTouchListener {
        e() {
        }

        @Override // android.view.View.OnTouchListener
        public boolean onTouch(View view, MotionEvent motionEvent) {
            return ((View) VideoPlayBaseActivity.this.t).onTouchEvent(motionEvent);
        }
    }

    class f implements ViewTreeObserver.OnPreDrawListener {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final /* synthetic */ int f1152a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        final /* synthetic */ int f1153b;

        f(int i, int i2) {
            this.f1152a = i;
            this.f1153b = i2;
        }

        @Override // android.view.ViewTreeObserver.OnPreDrawListener
        public boolean onPreDraw() {
            VideoPlayBaseActivity.this.Q.getViewTreeObserver().removeOnPreDrawListener(this);
            w.c("thumb view size : " + VideoPlayBaseActivity.this.Q.getMeasuredWidth() + " " + VideoPlayBaseActivity.this.Q.getMeasuredHeight());
            if (this.f1152a == VideoPlayBaseActivity.this.Q.getMeasuredWidth() && this.f1153b == VideoPlayBaseActivity.this.Q.getMeasuredHeight()) {
                return true;
            }
            VideoPlayBaseActivity.this.x0(this.f1152a, this.f1153b);
            return true;
        }
    }

    class g implements CompoundButton.OnCheckedChangeListener {
        g() {
        }

        @Override // android.widget.CompoundButton.OnCheckedChangeListener
        public void onCheckedChanged(CompoundButton compoundButton, boolean z) {
            w.c("onCheckedChanged:" + z);
            VideoPlayBaseActivity videoPlayBaseActivity = VideoPlayBaseActivity.this;
            if (z) {
                videoPlayBaseActivity.W0();
            } else if (!videoPlayBaseActivity.P0()) {
                VideoPlayBaseActivity.this.o1();
            } else {
                w.f(VideoPlayBaseActivity.this, R.string.request_focus_fail, 1);
                VideoPlayBaseActivity.this.c1();
            }
        }
    }

    class h extends com.byd.taskmanager.b.a<List<FileBean>> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final /* synthetic */ String f1156a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        final /* synthetic */ String f1157b;

        class a implements FileFilter {
            a() {
            }

            @Override // java.io.FileFilter
            public boolean accept(File file) {
                if (file.isFile()) {
                    return com.byd.util.m.d(com.byd.util.m.a(file.getName()));
                }
                return false;
            }
        }

        class b implements Comparator<File> {
            b() {
            }

            @Override // java.util.Comparator
            /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
            public int compare(File file, File file2) {
                return file.lastModified() - file2.lastModified() > 0 ? -1 : 1;
            }
        }

        h(String str, String str2) {
            this.f1156a = str;
            this.f1157b = str2;
        }

        /* JADX INFO: Access modifiers changed from: protected */
        @Override // com.byd.taskmanager.b.a
        public List<FileBean> doTask() {
            File file = new File(this.f1156a);
            if (!file.exists()) {
                return null;
            }
            File[] fileArrListFiles = file.listFiles(new a());
            if (fileArrListFiles == null || fileArrListFiles.length == 0) {
                w.c("folder children is 0");
                return null;
            }
            int length = fileArrListFiles.length;
            if (length > 1) {
                Arrays.sort(fileArrListFiles, new b());
            }
            ArrayList arrayList = new ArrayList(length);
            int i = 0;
            boolean z = false;
            for (File file2 : fileArrListFiles) {
                String path = file2.getPath();
                arrayList.add(new FileBean(file2.getName(), path, 2, 2, 0L));
                if (!z) {
                    if (path.equals(this.f1157b)) {
                        z = true;
                    } else {
                        i++;
                    }
                }
            }
            VideoPlayBaseActivity.this.B = i;
            return arrayList;
        }
    }

    class i extends com.byd.taskmanager.b.c<List<FileBean>> {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final /* synthetic */ String f1161a;

        /* JADX INFO: renamed from: b, reason: collision with root package name */
        final /* synthetic */ String f1162b;

        i(String str, String str2) {
            this.f1161a = str;
            this.f1162b = str2;
        }

        @Override // com.byd.taskmanager.b.b
        public void onComplete(String str, List<FileBean> list) {
            VideoPlayBaseActivity.this.A = list;
            if (VideoPlayBaseActivity.this.A == null || VideoPlayBaseActivity.this.A.size() == 0) {
                w.c("VideoPlayer load complete ,load nothing , finish : " + this.f1161a);
                File file = new File(this.f1162b);
                if (!file.exists()) {
                    w.f(VideoPlayBaseActivity.this, R.string.not_data_found, 1);
                    VideoPlayBaseActivity.this.finish();
                    return;
                } else {
                    if (VideoPlayBaseActivity.this.A == null) {
                        VideoPlayBaseActivity.this.A = new LinkedList();
                    }
                    VideoPlayBaseActivity.this.A.add(new FileBean(file.getName(), file.getAbsolutePath(), 2, 2, 0L));
                }
            }
            VideoPlayBaseActivity.this.C0();
        }

        @Override // com.byd.taskmanager.b.b
        public void onStart() {
        }
    }

    class j implements Runnable {

        /* JADX INFO: renamed from: a, reason: collision with root package name */
        final /* synthetic */ int f1164a;

        j(int i) {
            this.f1164a = i;
        }

        @Override // java.lang.Runnable
        public void run() {
            VideoPlayBaseActivity videoPlayBaseActivity = VideoPlayBaseActivity.this;
            if (!videoPlayBaseActivity.M) {
                w.e(videoPlayBaseActivity, this.f1164a);
            }
            VideoPlayBaseActivity.this.p1();
        }
    }

    class k extends MediaSession.Callback {
        k() {
        }

        @Override // android.media.session.MediaSession.Callback
        public void onCommand(@NonNull String str, @Nullable Bundle bundle, @Nullable ResultReceiver resultReceiver) {
            w.c("MediaSession.Callback -> onCommand:" + str + " " + bundle);
            super.onCommand(str, bundle, resultReceiver);
        }

        @Override // android.media.session.MediaSession.Callback
        public boolean onMediaButtonEvent(@NonNull Intent intent) {
            if (!"android.intent.action.MEDIA_BUTTON".equals(intent.getAction())) {
                return true;
            }
            KeyEvent keyEvent = (KeyEvent) intent.getParcelableExtra("android.intent.extra.KEY_EVENT");
            w.c("MediaSession.Callback -> onMediaButtonEvent:" + keyEvent);
            if (keyEvent == null || keyEvent.getAction() != 0) {
                return true;
            }
            int keyCode = keyEvent.getKeyCode();
            if (keyCode == 85) {
                w.c("MediaSession.Callback -> KEYCODE_MEDIA_PLAY_PAUSE");
                VideoPlayBaseActivity videoPlayBaseActivity = VideoPlayBaseActivity.this;
                if (videoPlayBaseActivity.M) {
                    return true;
                }
                videoPlayBaseActivity.r1();
                return true;
            }
            if (keyCode == 87) {
                onSkipToNext();
                return true;
            }
            if (keyCode == 88) {
                onSkipToPrevious();
                return true;
            }
            if (keyCode == 126) {
                onPlay();
                return true;
            }
            if (keyCode != 127) {
                return true;
            }
            onPause();
            return true;
        }

        @Override // android.media.session.MediaSession.Callback
        public void onPause() {
            w.c("MediaSession.Callback -> onPause");
            VideoPlayBaseActivity videoPlayBaseActivity = VideoPlayBaseActivity.this;
            if (videoPlayBaseActivity.M || videoPlayBaseActivity.t == null || !videoPlayBaseActivity.e0.h()) {
                return;
            }
            VideoPlayBaseActivity.this.W0();
        }

        @Override // android.media.session.MediaSession.Callback
        public void onPlay() {
            w.c("MediaSession.Callback -> onPlay");
            VideoPlayBaseActivity videoPlayBaseActivity = VideoPlayBaseActivity.this;
            if (videoPlayBaseActivity.M || videoPlayBaseActivity.t == null || videoPlayBaseActivity.e0.h()) {
                return;
            }
            VideoPlayBaseActivity.this.o1();
        }

        @Override // android.media.session.MediaSession.Callback
        public void onSkipToNext() {
            w.c("MediaSession.Callback -> onSkipToNext");
            VideoPlayBaseActivity videoPlayBaseActivity = VideoPlayBaseActivity.this;
            if (videoPlayBaseActivity.M) {
                return;
            }
            videoPlayBaseActivity.Y0();
        }

        @Override // android.media.session.MediaSession.Callback
        public void onSkipToPrevious() {
            w.c("MediaSession.Callback -> onSkipToPrevious");
            VideoPlayBaseActivity videoPlayBaseActivity = VideoPlayBaseActivity.this;
            if (videoPlayBaseActivity.M) {
                return;
            }
            videoPlayBaseActivity.Z0();
        }
    }

    private static class l extends b.a.d<VideoPlayBaseActivity> {
        public l(VideoPlayBaseActivity videoPlayBaseActivity) {
            super(videoPlayBaseActivity);
        }

        @Override // b.a.d
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public void handleMessage(VideoPlayBaseActivity videoPlayBaseActivity, Message message) {
            r.h().k(null);
            videoPlayBaseActivity.A = (List) message.obj;
            videoPlayBaseActivity.M0();
        }
    }

    private class m extends BroadcastReceiver {
        private m() {
        }

        /* synthetic */ m(VideoPlayBaseActivity videoPlayBaseActivity, c cVar) {
            this();
        }

        @Override // android.content.BroadcastReceiver
        public void onReceive(Context context, Intent intent) {
            if ("android.intent.action.CLOSE_SYSTEM_DIALOGS".equals(intent.getAction())) {
                String stringExtra = intent.getStringExtra("reason");
                if ("homekey".equals(stringExtra) || "recentapps".equals(stringExtra)) {
                    VideoPlayBaseActivity.this.R0(stringExtra);
                }
            }
        }
    }

    private static class n extends b.a.d<VideoPlayBaseActivity> {
        public n(VideoPlayBaseActivity videoPlayBaseActivity) {
            super(videoPlayBaseActivity);
        }

        @Override // b.a.d
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public void handleMessage(VideoPlayBaseActivity videoPlayBaseActivity, Message message) {
            int i = message.what;
            if (i == 1) {
                videoPlayBaseActivity.K0();
                return;
            }
            if (i == 2) {
                if (videoPlayBaseActivity.e0.h()) {
                    videoPlayBaseActivity.K.setVisibility(8);
                }
            } else {
                if (i == 4) {
                    videoPlayBaseActivity.D0(true);
                    return;
                }
                if (i == 5) {
                    videoPlayBaseActivity.F0();
                } else {
                    if (i != 6) {
                        return;
                    }
                    if (message.arg2 == 0) {
                        videoPlayBaseActivity.y0();
                    } else {
                        videoPlayBaseActivity.z0();
                    }
                }
            }
        }
    }

    private void A0() {
        if (this.s0 == null) {
            return;
        }
        w.c("==closeFileDescriptor==");
        try {
            this.s0.close();
        } catch (Exception e2) {
            e2.printStackTrace();
            w.c("closeFileDescriptor exception : " + e2.getMessage());
        }
        this.s0 = null;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void C0() {
        if (this.A.size() == 1) {
            if (com.byd.auto_photo.a.f1178a.intValue() != 3) {
                this.H.setVisibility(8);
            }
            this.I.setVisibility(8);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void D0(boolean z) {
        this.w0.removeMessages(4);
        com.byd.util.c cVar = this.Y;
        if (cVar == null) {
            return;
        }
        if (z) {
            cVar.a();
        } else {
            this.w0.sendEmptyMessageDelayed(4, 1000L);
        }
    }

    private void E0() {
        PopupWindow popupWindow = this.u0;
        if (popupWindow == null || !popupWindow.isShowing()) {
            return;
        }
        this.u0.dismiss();
    }

    private void G0(int i2) {
        runOnUiThread(new j(i2));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean M0() {
        int iB;
        List<FileBean> list = this.A;
        if (list == null || list.size() == 0) {
            w.f(this, R.string.not_data_found, 1);
        } else {
            this.B = this.b0 ? getIntent().getIntExtra("current_index", 0) : r.h().g();
            String path = this.A.get(this.B).getPath();
            File file = new File(path);
            if (file.exists()) {
                this.e0.p(path);
                n1(file.getName());
                this.O = path;
                if (!this.b0 && this.e0.f() == 0 && (iB = this.P.b(path)) > 0) {
                    this.e0.s(iB);
                    w.e(this, R.string.continue_last_playback);
                }
                C0();
                return false;
            }
            w.f(this, R.string.file_no_found, 1);
            w.c("video : " + path + " not exists");
        }
        finish();
        return true;
    }

    private void Q0(String str, String str2) {
        v(0, new h(str, str2), new i(str, str2), true).w(0);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void W0() {
        this.L = true;
        this.e0.i();
        m1();
    }

    private void X0() {
        this.D = 0;
        String path = this.A.get(this.B).getPath();
        this.e0.p(path);
        n1(this.A.get(this.B).getName());
        this.O = path;
        this.e0.j(path, null);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void Y0() {
        List<FileBean> list = this.A;
        if (list == null || list.size() <= 1) {
            return;
        }
        this.e0.w();
        w.c("playNextVideo before : " + this.B);
        this.B = this.B < this.A.size() - 1 ? this.B + 1 : 0;
        w.c("playNextVideo start : " + this.B);
        h1(10);
        X0();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void Z0() {
        List<FileBean> list = this.A;
        if (list == null || list.size() <= 1) {
            return;
        }
        this.e0.w();
        w.c("playPreVideo before : " + this.B);
        int size = this.B;
        if (size <= 0) {
            size = this.A.size();
        }
        this.B = size - 1;
        w.c("playPreVideo start : " + this.B);
        h1(9);
        X0();
    }

    private void a1() {
        A0();
        try {
            ParcelFileDescriptor parcelFileDescriptorOpenFileDescriptor = getContentResolver().openFileDescriptor(this.U, "r");
            this.s0 = parcelFileDescriptorOpenFileDescriptor;
            FileDescriptor fileDescriptor = parcelFileDescriptorOpenFileDescriptor.getFileDescriptor();
            if (!fileDescriptor.valid()) {
                w.c("openFileDescriptor fail:-1");
                w.f(this, R.string.not_data_found, 1);
                return;
            }
            this.e0.m(fileDescriptor);
            if (this.t.isSurfaceCreated()) {
                this.e0.j(null, fileDescriptor);
            }
            w.c("playWidthStreamUri : " + this.U);
        } catch (Exception e2) {
            e2.printStackTrace();
            w.c("openFileDescriptor exception:" + e2.getMessage());
            w.f(this, R.string.not_data_found, 1);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void c1() {
        if (!this.C.isChecked()) {
            this.C.setOnCheckedChangeListener(null);
            this.C.setChecked(true);
            this.C.setOnCheckedChangeListener(this.t0);
        }
        if (this.K.getVisibility() != 0) {
            this.K.setVisibility(0);
        }
        this.K.setImageResource(R.drawable.icon_video_play_big);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void f1(int i2, boolean z) {
        if (!this.e0.g() || this.D == 0) {
            w.c("video player not inited or duration is 0");
            return;
        }
        int iB = this.e0.b();
        if (z) {
            i2 += iB;
        }
        int i3 = this.D;
        if (i2 > i3) {
            i2 = i3;
        } else if (i2 < 0) {
            i2 = 0;
        }
        this.e0.l(i2);
    }

    private void g1() {
        com.byd.util.k.c(this).i(this.c0);
        com.byd.util.k.c(this).a();
    }

    private void h1(int i2) {
        if (this.i0 == null) {
            return;
        }
        float f2 = this.e0.h() ? 1.0f : 0.0f;
        this.j0.setState(i2, this.e0.b(), f2);
        this.i0.setPlaybackState(this.j0.build());
        w.c("setPlayBackState : " + i2 + " " + f2);
    }

    private void i1() {
        ImageView imageView;
        int i2;
        this.w0.removeMessages(2);
        ImageView imageView2 = this.K;
        if (imageView2 != null && imageView2.getVisibility() != 0) {
            this.K.setVisibility(0);
        }
        if (this.t == null) {
            return;
        }
        if (this.e0.h()) {
            imageView = this.K;
            i2 = R.drawable.icon_video_pause_big;
        } else {
            imageView = this.K;
            i2 = R.drawable.icon_video_play_big;
        }
        imageView.setImageResource(i2);
    }

    private void j1() {
        if (this.u0 == null) {
            View viewInflate = getLayoutInflater().inflate(R.layout.window_draw_progress, (ViewGroup) null);
            this.v0 = viewInflate;
            this.w = (TextView) viewInflate.findViewById(R.id.tv_drag_progress);
            this.x = (TextView) this.v0.findViewById(R.id.tv_drag_duration);
            PopupWindow popupWindow = new PopupWindow(this.v0, -2, -2);
            this.u0 = popupWindow;
            popupWindow.setOutsideTouchable(true);
            this.u0.setFocusable(false);
            this.u0.setBackgroundDrawable(new ColorDrawable());
        }
        U0();
    }

    private void k1(String str) {
        b.a.h.l.i(this).c(str).j(0, 0).k(false).h(1).e(Bitmap.Config.ARGB_8888).b(this.Q);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void o1() {
        w.c("start -> " + this.D + " " + this.e0.f());
        if (this.D > 0 && this.e0.f() >= this.D) {
            this.e0.v();
        }
        if (this.U == null || this.e0.c() != null) {
            this.e0.u();
        } else {
            a1();
        }
        this.K.setVisibility(8);
        this.w0.sendEmptyMessageDelayed(1, this.r);
        this.L = false;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void q1(boolean z) {
        if (this.e0.g()) {
            if (z) {
                if (this.E.getVisibility() != 0) {
                    return;
                }
                K0();
            } else {
                if (this.E.getVisibility() == 0) {
                    return;
                }
                m1();
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void x0(int i2, int i3) {
        ViewGroup.LayoutParams layoutParams = this.Q.getLayoutParams();
        if (layoutParams != null) {
            layoutParams.width = i2;
            layoutParams.height = i3;
            this.Q.setLayoutParams(layoutParams);
        }
    }

    protected void B0(int i2) {
        this.w0.removeMessages(5);
        if (i2 == 0) {
            this.w0.sendEmptyMessage(5);
        } else {
            this.w0.sendEmptyMessageDelayed(5, i2);
        }
    }

    protected void F0() {
        if (this.t == null || this.Q == null) {
            return;
        }
        w.c("VideoPlayActivity doConfigChange");
        N0();
        E0();
        D0(true);
    }

    protected int H0() {
        return super.H() + 10;
    }

    protected int I0() {
        return super.J() + 10;
    }

    protected int J0() {
        return R.layout.activity_video_play;
    }

    protected void K0() {
        String str;
        b.a.i.c cVar = this.e0;
        if (cVar == null || !cVar.g()) {
            str = "not player can't hide tools";
        } else {
            this.E.setVisibility(8);
            this.F.setVisibility(8);
            this.G.setVisibility(8);
            l1(false);
            if (this.e0.h()) {
                this.K.setVisibility(8);
            }
            View view = this.f;
            if (view != null) {
                view.setPadding(E(), 0, F(), 0);
            }
            T0();
            str = "VideoPlayer full screen";
        }
        w.c(str);
    }

    protected void L0() {
        if (this.Z && this.Y == null) {
            this.Y = new com.byd.util.c(this);
        }
    }

    @Override // com.byd.theme.ThemeActivity
    protected void N() {
        setContentView(J0());
        L0();
        this.R = findViewById(android.R.id.content);
        VideoPlayerBaseView videoPlayerBaseView = (VideoPlayerBaseView) x(R.id.video_view);
        this.t = videoPlayerBaseView;
        this.e0.t(videoPlayerBaseView);
        if (this.T == 0) {
            this.T = this.R.getSystemUiVisibility();
        }
        View viewFindViewById = findViewById(R.id.video_main_container);
        this.V = viewFindViewById;
        viewFindViewById.setOnTouchListener(this.p0);
        ((View) this.t).setOnClickListener(this);
        this.Q = (ImageView) findViewById(R.id.video_thumb);
        ImageView imageView = (ImageView) x(R.id.iv_video_play_big);
        this.K = imageView;
        imageView.setOnClickListener(this);
        this.E = findViewById(R.id.layout_tools);
        this.F = findViewById(R.id.view_bright_tips);
        this.G = findViewById(R.id.view_volume_tips);
        SeekBar seekBar = (SeekBar) x(R.id.seek_video_progress);
        this.J = seekBar;
        this.e0.q(seekBar);
        this.u = (TextView) x(R.id.tv_current_pos);
        this.v = (TextView) x(R.id.tv_duration);
        ImageView imageView2 = (ImageView) x(R.id.btn_pre);
        this.H = imageView2;
        imageView2.setOnClickListener(this);
        ImageView imageView3 = (ImageView) x(R.id.btn_next);
        this.I = imageView3;
        imageView3.setOnClickListener(this);
        CheckBox checkBox = (CheckBox) x(R.id.btn_play_or_pause);
        this.C = checkBox;
        checkBox.setOnCheckedChangeListener(this.t0);
        this.C.getViewTreeObserver().addOnPreDrawListener(new c());
        N0();
        this.S = findViewById(R.id.layout_control_tips);
        this.y = (TextView) findViewById(R.id.tv_slide_left_tips);
        this.z = (TextView) findViewById(R.id.tv_slide_right_tips);
        this.S.setVisibility(this.Z && com.byd.auto_photo.a.f1178a.intValue() >= 50 && p.d(this).g() ? 0 : 8);
        this.S.setOnClickListener(this);
    }

    protected void N0() {
        this.V.getViewTreeObserver().addOnPreDrawListener(new d());
    }

    public boolean O0() {
        return this.W;
    }

    protected boolean P0() {
        return com.byd.util.e.D();
    }

    @Override // com.byd.theme.ThemeActivity
    protected void R() {
        w.c("VideoPlayActivity onDeniedPermission");
        if (L(this, new String[]{Build.VERSION.SDK_INT >= 33 ? "android.permission.READ_MEDIA_VIDEO" : "android.permission.READ_EXTERNAL_STORAGE"})) {
            S();
        } else {
            w.e(this, R.string.permission_denied_and_finish);
            finish();
        }
    }

    protected void R0(String str) {
    }

    @Override // com.byd.theme.ThemeActivity
    protected void S() {
        int iB;
        String strDecode;
        super.S();
        com.byd.util.k.c(this).g("09");
        b.a.k.h.e(this).n();
        Intent intent = getIntent();
        boolean booleanExtra = intent.getBooleanExtra("more_dev_share", false);
        this.b0 = booleanExtra;
        if (booleanExtra) {
            this.A = intent.getParcelableArrayListExtra("share_items");
            if (intent.getBooleanExtra("from_service", false)) {
                ShareItem shareItem = (ShareItem) intent.getParcelableExtra("share_item");
                w.g(this, x.h(this, shareItem.getFromId(), shareItem.getDataType()));
            }
            this.c0 = "04";
            M0();
            return;
        }
        int intExtra = intent.getIntExtra("start_pos", 0);
        byte byteExtra = intent.getByteExtra("pause_state", (byte) 0);
        if (intExtra != 0) {
            this.e0.s(intExtra);
        }
        if (this.o0 && GearboxUtil.i(this.g).g() != 1) {
            w.c("no in GEARBOX_AUTO_MODE_P , can not play");
            byteExtra = 1;
        }
        w.c("onGrantedPermission play-> pauseState:" + ((int) byteExtra) + " action:" + intent.getAction() + " package:" + intent.getPackage());
        this.e0.o(byteExtra);
        if (byteExtra == 1) {
            this.L = true;
        }
        this.P = p.d(this);
        if (intent.getData() == null) {
            r.h().k(new l(this));
            if (r.h().i()) {
                r.h().k(null);
                this.A = r.h().f();
                if (M0()) {
                }
                return;
            }
            return;
        }
        Uri data = intent.getData();
        String strQ = com.byd.util.n.q(this, data);
        if (byteExtra == 1) {
            this.K.setImageResource(R.drawable.icon_video_play_big);
        }
        w.c("onGrantedPermission play:" + strQ + " pauseState:" + ((int) byteExtra) + " uri:" + data);
        if (strQ == null) {
            this.I.setVisibility(8);
            if (com.byd.auto_photo.a.f1178a.intValue() == 2) {
                this.H.setVisibility(8);
            }
            String string = data.toString();
            int iLastIndexOf = string.lastIndexOf("/");
            if (iLastIndexOf > -1) {
                try {
                    strDecode = URLDecoder.decode(string.substring(iLastIndexOf + 1), "utf-8");
                } catch (Exception e2) {
                    e2.printStackTrace();
                    strDecode = "";
                }
                n1(strDecode);
                l1(true);
            } else {
                l1(false);
            }
            this.U = data;
            this.c0 = "05";
            a1();
            return;
        }
        this.e0.p(strQ);
        if (byteExtra == 1) {
            k1(strQ);
        }
        this.O = strQ;
        if (intExtra == 0 && (iB = this.P.b(strQ)) > 0) {
            this.e0.s(iB);
            w.e(this, R.string.continue_last_playback);
        }
        if (this.t.isSurfaceCreated()) {
            this.e0.j(strQ, null);
        }
        if (com.byd.auto_photo.a.f1178a.intValue() != 2) {
            n1(new File(strQ).getName());
        }
        if (new File(strQ).exists()) {
            Q0(new File(strQ).getParent(), strQ);
        } else {
            w.f(this, R.string.file_no_found, 1);
            finish();
        }
    }

    protected void S0(View view) {
    }

    @Override // com.byd.theme.ThemeActivity
    protected void T(Configuration configuration) {
        super.T(configuration);
        if (this.S.getVisibility() == 0) {
            this.y.setText(R.string.slide_left_tips);
            this.z.setText(R.string.slide_right_tips);
        }
    }

    protected void T0() {
        this.R.setSystemUiVisibility(5895);
    }

    @Override // com.byd.theme.ThemeActivity
    protected void U(Configuration configuration) {
        w.c("VideoPlayActivity onNormalConfigurationChanged");
        B0(150);
    }

    protected void U0() {
        this.u0.showAtLocation((View) this.t, 17, 0, 0);
    }

    protected synchronized void V0(int i2, int i3) {
        this.w0.removeMessages(6);
        this.w0.obtainMessage(6, i2, i3).sendToTarget();
    }

    @Override // com.byd.theme.ThemeActivity
    protected String[] W() {
        String[] strArrR = com.byd.util.e.r(((PhotoBaseApp) getApplication()).c(), true);
        if (!this.o0) {
            return strArrR;
        }
        String[] strArr = new String[strArrR.length + 1];
        System.arraycopy(strArrR, 0, strArr, 0, strArrR.length);
        strArr[strArrR.length] = BYDAutoGearboxDevice.PERMISSION_BYDAUTO_GEARBOX_COMMON;
        return strArr;
    }

    @Override // b.a.i.a
    public void a() {
        this.r0 = 0;
        w.c("onDoubleTap videoView");
        E0();
        r1();
        D0(true);
    }

    @Override // b.a.i.a
    public void b() {
        if (this.D == 0) {
            return;
        }
        D0(false);
        if (this.Z && this.r0 != 1) {
            this.r0 = 0;
            return;
        }
        this.r0 = 0;
        int max = this.J.getMax();
        int progress = this.J.getProgress();
        w.c("onDragFinish:" + progress + " max:" + max);
        if (Math.abs(progress - this.e0.b()) > 50) {
            this.e0.l(progress);
            m(progress, true);
        }
        E0();
        this.w0.sendEmptyMessageDelayed(1, this.r);
    }

    protected void b1() {
        this.l0 = (DisplayManager) getSystemService("display");
        try {
            Class<?> cls = Class.forName("android.hardware.display.DisplayManager$OnekeyStatusListener");
            if (this.A0 == null) {
                this.A0 = DisplayListenUtil.getInstance().createOneKeyBackLightListener(new b());
            }
            Method method = Class.forName("android.hardware.display.DisplayManager").getMethod("registerOnekeyStatusListener", cls, Handler.class);
            method.setAccessible(true);
            method.invoke(this.l0, this.A0, this.w0);
        } catch (Exception e2) {
            e2.printStackTrace();
            w.c("registerOneKeyStatusListener exception : " + e2.getMessage());
        }
    }

    @Override // b.a.i.a
    public void c() {
        Z(R.string.video_play_error);
        if (this.Q.getVisibility() != 0) {
            this.Q.setImageDrawable(new ColorDrawable(ViewCompat.MEASURED_STATE_MASK));
            this.Q.setVisibility(0);
        }
        c1();
        this.D = 0;
    }

    @Override // b.a.i.a
    public void d(String str) {
        if (!this.b0) {
            this.e0.v();
            e1();
            if (this.Q.getVisibility() != 0) {
                if (this.e0.e() != null) {
                    k1(this.e0.e());
                }
                this.Q.setVisibility(0);
            }
            if (this.e0.c() != null) {
                A0();
                this.e0.m(null);
                w.c("setFileDescriptor null ");
            }
        }
        w.c("==onPlayComplete==");
        this.N = 0;
        w.e(this, R.string.play_complete);
        if (!this.b0) {
            this.P.k("", 0);
            return;
        }
        List<FileBean> list = this.A;
        if (list != null && list.size() > 1) {
            Y0();
        } else {
            this.e0.v();
            e1();
        }
    }

    protected void d1() {
        if (Build.VERSION.SDK_INT < 21) {
            getWindow().addFlags(67108864);
            getWindow().addFlags(134217728);
            return;
        }
        Window window = getWindow();
        View decorView = getWindow().getDecorView();
        Integer num = com.byd.auto_photo.a.f1178a;
        decorView.setSystemUiVisibility(num.intValue() != 2 ? 5381 : 1792);
        if (num.intValue() == 35) {
            window.setStatusBarColor(0);
            window.setNavigationBarColor(0);
        } else {
            window.setStatusBarColor(ViewCompat.MEASURED_STATE_MASK);
            window.setNavigationBarColor(ViewCompat.MEASURED_STATE_MASK);
        }
    }

    @Override // b.a.i.a
    public void e() {
        Z(R.string.request_focus_fail);
    }

    protected void e1() {
        this.L = false;
        m1();
        if (!this.C.isChecked()) {
            this.C.setOnCheckedChangeListener(null);
            this.C.setChecked(true);
            this.C.setOnCheckedChangeListener(this.t0);
        }
        this.J.setProgress(0);
        if (this.D == 0) {
            this.J.setEnabled(false);
        }
        E0();
        D0(true);
        this.u.setText(this.m0 ? "00:00" : "00:00:00");
    }

    @Override // b.a.i.a
    public void f() {
        w.c("VideoPlayActivity onVideoStart : " + this.L);
        h1(3);
        this.Q.setVisibility(8);
        if (this.K.getVisibility() == 0) {
            this.K.setImageResource(R.drawable.icon_video_pause_big);
            this.w0.sendEmptyMessageDelayed(2, this.s);
        }
        if (this.C.isChecked()) {
            this.C.setOnCheckedChangeListener(null);
            this.C.setChecked(false);
            this.C.setOnCheckedChangeListener(this.t0);
        }
        E0();
        d1();
        com.byd.util.k.c(this).l();
        ((View) this.t).setKeepScreenOn(true);
    }

    /* JADX WARN: Removed duplicated region for block: B:35:0x008c A[PHI: r3
  0x008c: PHI (r3v17 int) = (r3v13 int), (r3v24 int) binds: [B:38:0x0095, B:33:0x0089] A[DONT_GENERATE, DONT_INLINE]] */
    @Override // b.a.i.a
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public void g(int r3, int r4, int r5, int r6, float r7, float r8) {
        /*
            Method dump skipped, instruction units count: 342
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.byd.auto_photo.VideoPlayBaseActivity.g(int, int, int, int, float, float):void");
    }

    @Override // b.a.i.a
    public void h() {
        w.c("VideoPlayActivity onVideoPause : " + this.L);
        c1();
        g1();
        ((View) this.t).setKeepScreenOn(false);
        h1(2);
    }

    @Override // b.a.i.a
    public void i(int i2, int i3) {
        w.c("onVideoSizeChange : " + i2 + " x " + i3);
        this.t.setVideoSize(i2, i3);
    }

    @Override // b.a.i.a
    public void j() {
        w.c("onDragCancel!!!");
        this.r0 = 0;
        D0(true);
        E0();
    }

    @Override // b.a.i.a
    public void k(int i2) {
        boolean z = this.m0;
        this.q0 = i2 > 0 ? z ? o.c(i2) : o.a(i2) : z ? "--:--" : "--:--:--";
        this.v.setText(this.q0);
        this.D = i2;
        this.J.setEnabled(i2 > 0);
        w.c("onPreparePlay : " + i2);
        this.w0.sendEmptyMessageDelayed(1, (long) this.r);
        if (!"05".equals(this.c0) && !"04".equals(this.c0) && this.O != null) {
            int iD = com.byd.util.j.e(this).d(this.O);
            this.c0 = iD == 1 ? "03" : iD == 2 ? "02" : "01";
        }
        if (this.i0 != null) {
            this.k0.putLong("android.media.metadata.DURATION", i2);
            this.i0.setMetadata(this.k0.build());
        }
    }

    @Override // b.a.i.a
    public void l() {
        if (this.e0.h()) {
            this.w0.sendEmptyMessageDelayed(1, this.r);
        }
    }

    protected void l1(boolean z) {
    }

    @Override // b.a.i.a
    public void m(int i2, boolean z) {
        this.u.setText(this.m0 ? o.c(i2) : o.a(i2));
        int i3 = this.D;
        if (i3 <= 0 || i2 < i3) {
            this.N = i2;
            return;
        }
        if (!z) {
            this.e0.v();
            e1();
            if (this.Q.getVisibility() != 0) {
                if (this.e0.e() != null) {
                    k1(this.e0.e());
                }
                this.Q.setVisibility(0);
            }
            w.e(this, R.string.play_complete);
            if (this.e0.c() != null) {
                A0();
                this.e0.m(null);
                w.c("setFileDescriptor null ");
            }
            if (this.b0) {
                Y0();
            }
        }
        w.c("progress to end");
        this.N = 0;
    }

    protected void m1() {
        this.w0.removeMessages(1);
        i1();
        View view = this.E;
        if (view != null && view.getVisibility() != 0) {
            this.E.setVisibility(0);
            this.F.setVisibility(this.a0);
            this.G.setVisibility(this.a0);
            l1(true);
            this.w0.sendEmptyMessageDelayed(1, this.r);
        }
        View view2 = this.R;
        if (view2 != null) {
            view2.setSystemUiVisibility(this.T);
        }
        d1();
        if (this.f != null) {
            Y();
        }
        w.c("VideoPlayer normal screen");
    }

    @Override // b.a.i.a
    public void n(float f2, float f3, int i2, int i3) {
        int[] iArr = this.h0;
        if (iArr[0] > 0 && f2 > iArr[0]) {
            f2 -= iArr[0];
        }
        if (iArr[1] > 0 && f3 > iArr[1]) {
            f3 -= iArr[1];
        }
        w.c("onDragStart : " + f2 + " " + f3 + " " + this.f0 + " " + this.g0);
        int iI0 = I0();
        int iH0 = H0();
        if (f2 < this.f0 / 4.0f) {
            if (f3 > iI0 && f3 < this.k.y - iH0) {
                this.r0 = 2;
                return;
            }
        } else if (f2 <= (r2 * 3) / 4.0f) {
            this.r0 = 1;
            return;
        } else if (f3 > iI0 && f3 < this.k.y - iH0) {
            this.r0 = 3;
            return;
        }
        this.r0 = 0;
    }

    protected void n1(String str) {
        this.k0.putString("android.media.metadata.TITLE", str);
        this.k0.putString("android.media.metadata.DISPLAY_SUBTITLE", str);
    }

    @Override // androidx.activity.ComponentActivity, android.app.Activity
    public void onBackPressed() {
        w.c("Video play onBackPressed");
        p1();
    }

    /* JADX WARN: Can't fix incorrect switch cases order, some code will duplicate */
    public void onClick(View view) {
        if (P0()) {
            b.a.i.c cVar = this.e0;
            if (cVar != null && cVar.h()) {
                this.e0.i();
            }
            w.f(this, R.string.is_in_call, 1);
        }
        switch (view.getId()) {
            case R.id.btn_next /* 2131230809 */:
                if (!com.byd.util.e.y()) {
                    this.w0.removeMessages(1);
                    Y0();
                    this.w0.sendEmptyMessageDelayed(1, this.r);
                    E0();
                    D0(true);
                    break;
                }
                break;
            case R.id.btn_pre /* 2131230812 */:
                if (!com.byd.util.e.y()) {
                    this.w0.removeMessages(1);
                    Z0();
                    this.w0.sendEmptyMessageDelayed(1, this.r);
                    E0();
                    D0(true);
                    break;
                }
                break;
            case R.id.iv_video_play_big /* 2131230970 */:
                if (!com.byd.util.e.y()) {
                    r1();
                    E0();
                    D0(true);
                    break;
                }
                break;
            case R.id.layout_control_tips /* 2131230972 */:
                this.S.setVisibility(8);
                E0();
                D0(true);
                break;
            case R.id.video_view /* 2131231176 */:
                if (this.E.getVisibility() != 0) {
                    m1();
                } else if (this.e0.g()) {
                    K0();
                }
                E0();
                D0(true);
                break;
            default:
                E0();
                D0(true);
                break;
        }
    }

    @Override // com.byd.auto_photo.SubBaseTaskActivity, com.byd.theme.ThemeActivity, com.byd.ui.BaseActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    protected void onCreate(Bundle bundle) {
        this.w0 = new n(this);
        b.a.i.c cVar = new b.a.i.c(this);
        this.e0 = cVar;
        cVar.n(this);
        try {
            MediaSession mediaSession = new MediaSession(getApplicationContext(), "auto_photo");
            this.i0 = mediaSession;
            mediaSession.setCallback(this.x0);
        } catch (Exception e2) {
            e2.printStackTrace();
            w.c("create media session exception");
        }
        this.o0 = com.byd.util.e.G(this);
        this.j0 = new PlaybackState.Builder();
        this.k0 = new MediaMetadata.Builder();
        this.W = "android.intent.action.VIEW".equals(getIntent().getAction());
        super.onCreate(bundle);
        if (P0()) {
            w.f(this, R.string.request_focus_fail, 1);
            finish();
            return;
        }
        this.d0 = ViewConfiguration.get(this).getScaledMinimumFlingVelocity();
        m mVar = new m(this, null);
        this.X = mVar;
        registerReceiver(mVar, new IntentFilter("android.intent.action.CLOSE_SYSTEM_DIALOGS"));
        this.n0 = (Build.VERSION.SDK_INT >= 30 ? getDisplay() : getWindowManager().getDefaultDisplay()).getDisplayId();
        w.c("VideoPlayerActivity current display : " + this.n0);
        b1();
    }

    @Override // com.byd.auto_photo.SubBaseTaskActivity, com.byd.theme.ThemeActivity, com.byd.ui.BaseActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    protected void onDestroy() {
        sendBroadcast(new Intent("com.byd.auto_video_stop"));
        MediaSession mediaSession = this.i0;
        if (mediaSession != null) {
            mediaSession.setCallback(null);
            this.i0.release();
            this.i0 = null;
        }
        super.onDestroy();
        this.U = null;
        m mVar = this.X;
        if (mVar != null) {
            unregisterReceiver(mVar);
            this.X = null;
        }
        s1();
        n nVar = this.w0;
        if (nVar != null) {
            nVar.removeCallbacksAndMessages(null);
            this.w0.release();
        }
        com.byd.util.c cVar = this.Y;
        if (cVar != null) {
            cVar.h();
            this.Y = null;
        }
        this.e0.k();
        w.c("VideoPlayActivity onDestroy");
    }

    @b.a.f.b(threadMode = 1)
    public void onEventExit(String[] strArr) {
        w.c("==onEventExit==" + isInMultiWindowMode());
        finish();
    }

    @b.a.f.b(threadMode = 1)
    public void onEventMsg(VideoControllerInfo videoControllerInfo) {
        w.c("VideoPlayer onEventMsg : " + videoControllerInfo.toString());
        int opt = videoControllerInfo.getOpt();
        if (opt == 1) {
            q1(videoControllerInfo.isFullscreen());
        } else {
            if (opt != 2) {
                return;
            }
            f1(videoControllerInfo.getSeekTime(), videoControllerInfo.isBaseCurrent());
        }
    }

    @Override // com.byd.auto_photo.SubBaseTaskActivity, b.a.k.h.b
    public void onExternalEject(String str, boolean z) {
        String str2 = this.O;
        if (str2 != null && !"".equals(str2)) {
            w.c("VideoPlay onExternalEject : " + this.O + " path:" + str);
            if (this.O.startsWith(str)) {
                G0(R.string.external_file_not_exist);
                return;
            }
            List<FileBean> list = this.A;
            if (list != null) {
                int size = list.size();
                for (int i2 = 0; i2 < size; i2++) {
                    if (this.A.get(i2).getPath().startsWith(str)) {
                        G0(R.string.file_no_found);
                        return;
                    }
                }
                return;
            }
            return;
        }
        Uri uri = this.U;
        if (uri == null) {
            return;
        }
        String path = uri.getPath();
        String str3 = File.separator;
        String str4 = str.endsWith(str3) ? str : str + str3;
        if (path == null || !path.contains(str4)) {
            return;
        }
        w.c("VideoPlay onExternalEject : " + path + " path:" + str);
        G0(R.string.external_file_not_exist);
    }

    @Override // android.app.Activity, android.view.KeyEvent.Callback
    public boolean onKeyDown(int i2, KeyEvent keyEvent) {
        w.c("Video play onKeyDown : " + i2);
        if (keyEvent.getRepeatCount() == 0) {
            if (i2 == 85) {
                r1();
                return true;
            }
            if (i2 == 87) {
                Y0();
                return true;
            }
            if (i2 == 88) {
                Z0();
                return true;
            }
            if (i2 == 126) {
                if (this.t != null && !this.e0.h()) {
                    o1();
                }
                return true;
            }
            if (i2 == 127) {
                if (this.t != null && this.e0.h()) {
                    W0();
                }
                return true;
            }
        }
        return super.onKeyDown(i2, keyEvent);
    }

    @Override // android.app.Activity, android.view.KeyEvent.Callback
    public boolean onKeyUp(int i2, KeyEvent keyEvent) {
        if (i2 == 85 || i2 == 87 || i2 == 88 || i2 == 126 || i2 == 127) {
            return true;
        }
        return super.onKeyUp(i2, keyEvent);
    }

    @Override // androidx.fragment.app.FragmentActivity, android.app.Activity
    protected void onPause() {
        p pVar;
        int i2;
        super.onPause();
        w.c("VideoPlayActivity onPause " + hashCode());
        this.M = true;
        if (this.t != null && this.e0.h()) {
            this.e0.i();
            m1();
        }
        this.e0.a();
        if (this.b0 || (pVar = this.P) == null) {
            return;
        }
        String str = this.O;
        if (str == null || (i2 = this.N) <= 0) {
            pVar.k("", 0);
        } else {
            pVar.k(str, i2);
        }
    }

    @Override // android.app.Activity
    protected void onRestart() {
        super.onRestart();
        d1();
    }

    @Override // com.byd.theme.ThemeActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    protected void onResume() {
        int iB;
        super.onResume();
        w.c("VideoPlayActivity onResume " + hashCode());
        this.M = false;
        if (this.t != null) {
            if (this.O != null && !new File(this.O).exists()) {
                p1();
                return;
            }
            if (!this.L && this.D > 0) {
                if (this.e0.b() > 0) {
                    this.e0.u();
                } else if ((this.t instanceof SurfaceView) && (iB = this.P.b(this.O)) > 0) {
                    this.e0.s(iB);
                }
                View view = this.E;
                if (view != null && view.getVisibility() == 0) {
                    this.w0.sendEmptyMessageDelayed(1, this.r);
                    return;
                }
            }
        }
        m1();
    }

    @Override // b.a.i.a
    public void onSizeChange(int i2, int i3) {
        ViewGroup.LayoutParams layoutParams;
        w.c("VideoPlay onSizeChange " + i2 + " " + i3);
        ImageView imageView = this.Q;
        if (imageView == null || (layoutParams = imageView.getLayoutParams()) == null) {
            return;
        }
        layoutParams.width = i2;
        layoutParams.height = i3;
        this.Q.getViewTreeObserver().addOnPreDrawListener(new f(i2, i3));
        this.Q.setLayoutParams(layoutParams);
        w.c("VideoPlay thumb size update");
    }

    @Override // com.byd.theme.ThemeActivity, com.byd.ui.BaseActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    protected void onStart() {
        super.onStart();
        if (this.y0 == null) {
            this.y0 = new VideoControlReceiver(this.z0);
        }
        MediaSession mediaSession = this.i0;
        if (mediaSession != null) {
            mediaSession.setActive(true);
            w.c("mediaSession active : " + this.i0.isActive());
        }
        b.a.f.a.e().g(this);
        registerReceiver(this.y0, new IntentFilter("com.byd.ACTION_VIDEO_CONTROL"));
        if (!this.o0 || this.t == null || GearboxUtil.i(this.g).g() == 1) {
            return;
        }
        w.c("no in GEARBOX_AUTO_MODE_P onStart, can not play");
        if (this.e0.h()) {
            this.e0.i();
        } else if (this.e0.d() == 0) {
            this.e0.o((byte) 1);
        }
    }

    @Override // com.byd.theme.ThemeActivity, com.byd.ui.BaseActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    protected void onStop() {
        super.onStop();
        b.a.f.a.e().i(this);
        unregisterReceiver(this.y0);
        MediaSession mediaSession = this.i0;
        if (mediaSession != null) {
            mediaSession.setActive(false);
            w.c("mediaSession active : " + this.i0.isActive());
        }
    }

    @Override // android.app.Activity, android.view.Window.Callback
    public void onWindowFocusChanged(boolean z) {
        super.onWindowFocusChanged(z);
        if (z) {
            d1();
        }
    }

    protected void p1() {
        b.a.i.c cVar = this.e0;
        if (cVar != null) {
            cVar.v();
        }
        w.c("video play stopAndExit");
        finish();
    }

    @Override // b.a.i.a
    public void q() {
    }

    @Override // b.a.i.a
    public boolean r() {
        return (this.L || this.M) ? false : true;
    }

    protected void r1() {
        if (!this.e0.h()) {
            o1();
            return;
        }
        this.e0.i();
        this.L = true;
        this.K.setImageResource(R.drawable.icon_video_play_big);
    }

    @Override // b.a.i.a
    public void s() {
        this.w0.removeMessages(1);
    }

    protected void s1() {
        if (this.l0 != null && this.A0 != null) {
            try {
                Method method = Class.forName("android.hardware.display.DisplayManager").getMethod("unregisterOnekeyStatusListener", Class.forName("android.hardware.display.DisplayManager$OnekeyStatusListener"));
                method.setAccessible(true);
                method.invoke(this.l0, this.A0);
            } catch (Exception e2) {
                e2.printStackTrace();
                w.c("unRegisterOneKeyStatusListener exception : " + e2.getMessage());
            }
        }
        this.l0 = null;
        this.A0 = null;
    }

    @Override // b.a.i.a
    public void u() {
        w.c("VideoPlayActivity onVideoStop");
        A0();
        e1();
        g1();
        ((View) this.t).setKeepScreenOn(false);
        h1(1);
    }

    protected void y0() {
        if (this.t != null && this.e0.h()) {
            this.e0.i();
        }
    }

    protected void z0() {
        if (this.M) {
            w.c("ui pause on checkResumePlayWhenPause");
            return;
        }
        if (this.t == null || this.L || this.e0.h() || this.e0.b() <= 0) {
            return;
        }
        this.e0.u();
        View view = this.E;
        if (view == null || view.getVisibility() != 0) {
            return;
        }
        this.w0.sendEmptyMessageDelayed(1, this.r);
    }
}
