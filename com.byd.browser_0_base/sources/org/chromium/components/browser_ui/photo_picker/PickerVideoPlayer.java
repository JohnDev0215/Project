package org.chromium.components.browser_ui.photo_picker;

import android.animation.Animator;
import android.content.Context;
import android.content.res.Configuration;
import android.hardware.bydauto_test.TestVehicleHealthManagerImplCallback;
import android.media.MediaPlayer;
import android.util.AttributeSet;
import android.view.GestureDetector;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.VideoView;
import defpackage.ao3;
import defpackage.d92;
import defpackage.f52;
import defpackage.i12;
import defpackage.is0;
import defpackage.j12;
import defpackage.rh3;
import defpackage.uh3;
import defpackage.ve3;
import org.chromium.base.task.PostTask;

/* JADX INFO: loaded from: classes2.dex */
public class PickerVideoPlayer extends FrameLayout implements View.OnClickListener, SeekBar.OnSeekBarChangeListener, View.OnSystemUiVisibilityChangeListener {
    public static boolean E;
    public int A;
    public int B;
    public boolean C;
    public final is0 D;
    public Window e;
    public final Context f;
    public final ImageView g;
    public final TextView h;
    public final VideoView i;
    public MediaPlayer j;
    public final View k;
    public boolean l;
    public final View m;
    public final View n;
    public final ImageView o;
    public final ImageView p;
    public boolean q;
    public final ImageView r;
    public boolean s;
    public boolean t;
    public final TextView u;
    public final LinearLayout v;
    public final SeekBar w;
    public boolean x;
    public boolean y;
    public int z;

    public class a implements View.OnTouchListener {
        public a() {
        }

        @Override // android.view.View.OnTouchListener
        public final boolean onTouch(View view, MotionEvent motionEvent) {
            PickerVideoPlayer.this.D.a.a.onTouchEvent(motionEvent);
            return false;
        }
    }

    public class d extends GestureDetector.SimpleOnGestureListener {
        public d() {
        }

        @Override // android.view.GestureDetector.SimpleOnGestureListener, android.view.GestureDetector.OnDoubleTapListener
        public final boolean onDoubleTap(MotionEvent motionEvent) {
            float x = motionEvent.getX();
            PickerVideoPlayer pickerVideoPlayer = PickerVideoPlayer.this;
            int currentPosition = pickerVideoPlayer.j.getCurrentPosition();
            pickerVideoPlayer.j.getDuration();
            ImageView imageView = pickerVideoPlayer.o;
            pickerVideoPlayer.j.seekTo(currentPosition + (x > imageView.getX() + ((float) (imageView.getWidth() / 2)) ? TestVehicleHealthManagerImplCallback.SET_FRONT_WIPER_CHECK_STATE : -10000), 3);
            pickerVideoPlayer.h();
            pickerVideoPlayer.e(1, false);
            return true;
        }

        @Override // android.view.GestureDetector.SimpleOnGestureListener, android.view.GestureDetector.OnDoubleTapListener
        public final boolean onSingleTapConfirmed(MotionEvent motionEvent) {
            PickerVideoPlayer pickerVideoPlayer = PickerVideoPlayer.this;
            if (pickerVideoPlayer.l) {
                pickerVideoPlayer.c(3);
            } else {
                pickerVideoPlayer.e(2, true);
            }
            return true;
        }
    }

    public interface e {
    }

    public PickerVideoPlayer(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.q = true;
        this.f = context;
        LayoutInflater.from(context).inflate(R$layout.video_player, this);
        ImageView imageView = (ImageView) findViewById(R$id.back_button);
        this.g = imageView;
        this.h = (TextView) findViewById(R$id.video_file_name);
        this.i = (VideoView) findViewById(R$id.video_player);
        View viewFindViewById = findViewById(R$id.video_overlay_container);
        this.k = viewFindViewById;
        this.m = findViewById(R$id.video_controls);
        this.n = findViewById(R$id.video_controls_gradient);
        ImageView imageView2 = (ImageView) findViewById(R$id.video_player_play_button);
        this.o = imageView2;
        ImageView imageView3 = (ImageView) findViewById(R$id.mute);
        this.p = imageView3;
        imageView3.setImageResource(R$drawable.ic_volume_on_white_24dp);
        ImageView imageView4 = (ImageView) findViewById(R$id.fullscreen);
        this.r = imageView4;
        this.u = (TextView) findViewById(R$id.remaining_time);
        SeekBar seekBar = (SeekBar) findViewById(R$id.seek_bar);
        this.w = seekBar;
        this.v = (LinearLayout) findViewById(R$id.fast_forward_message);
        imageView.setOnClickListener(this);
        viewFindViewById.setOnClickListener(this);
        imageView2.setOnClickListener(this);
        imageView3.setOnClickListener(this);
        imageView4.setOnClickListener(this);
        seekBar.setOnSeekBarChangeListener(this);
        this.D = new is0(context, new d());
        viewFindViewById.setOnTouchListener(new a());
    }

    public static int d(int i) {
        return E ? i / 10 : i;
    }

    public static void setShortAnimationTimesForTesting(boolean z) {
        E = z;
    }

    public final void a() {
        MediaPlayer mediaPlayer = this.j;
        if (mediaPlayer == null || mediaPlayer.getVideoWidth() == 0 || this.j.getVideoHeight() == 0) {
            return;
        }
        float videoWidth = this.j.getVideoWidth() / this.j.getVideoHeight();
        boolean z = this.f.getResources().getConfiguration().orientation == 2;
        int iMax = z ? Math.max(getWidth(), getHeight()) : Math.min(getWidth(), getHeight());
        int iMin = z ? Math.min(getWidth(), getHeight()) : Math.max(getWidth(), getHeight());
        VideoView videoView = this.i;
        ViewGroup.LayoutParams layoutParams = videoView.getLayoutParams();
        TextView textView = this.h;
        ImageView imageView = this.g;
        if (z) {
            int iRound = Math.round(iMin * videoWidth);
            layoutParams.width = iRound;
            layoutParams.height = iMin;
            if (iRound > iMax) {
                layoutParams.width = iMax;
                layoutParams.height = Math.round(iMax / videoWidth);
            }
            imageView.setVisibility(8);
            textView.setVisibility(8);
        } else {
            int iRound2 = Math.round(iMax / videoWidth);
            layoutParams.height = iRound2;
            layoutParams.width = iMax;
            if (iRound2 > iMin) {
                layoutParams.height = iMin;
                layoutParams.width = Math.round(iMin * videoWidth);
            }
            imageView.setVisibility(0);
            textView.setVisibility(0);
        }
        videoView.setLayoutParams(layoutParams);
        ao3.e(videoView, "PickerVideoPlayer.adjustVideoLayoutParamsToOrientation mVideoView");
        View view = this.m;
        view.setLayoutParams(layoutParams);
        ao3.e(view, "PickerVideoPlayer.adjustVideoLayoutParamsToOrientation mVideoControls");
    }

    public final boolean b() {
        if (getVisibility() != 0) {
            return false;
        }
        setVisibility(8);
        this.y = false;
        this.j.pause();
        int i = R$drawable.ic_play_circle_filled_white_24dp;
        ImageView imageView = this.o;
        imageView.setImageResource(i);
        imageView.setContentDescription(this.f.getResources().getString(R$string.accessibility_play_video));
        e(0, false);
        this.i.setMediaController(null);
        this.p.setImageResource(R$drawable.ic_volume_on_white_24dp);
        g(false);
        return true;
    }

    public final void c(int i) {
        if (i == 0) {
            return;
        }
        View view = this.m;
        view.animate().cancel();
        View view2 = this.n;
        view2.animate().cancel();
        ImageView imageView = this.o;
        imageView.animate().cancel();
        int i2 = 0;
        int i3 = i != 3 ? 2500 : 0;
        view2.animate().alpha(0.0f).setStartDelay(d(i3)).setDuration(d(1000));
        view.animate().alpha(0.0f).setStartDelay(d(i3)).setDuration(d(750)).setListener(new b());
        if (i != 3) {
            i2 = i == 1 ? 250 : 2500;
        }
        imageView.animate().alpha(0.0f).setStartDelay(d(i2)).setDuration(d(750)).setListener(new c());
    }

    public final void e(int i, boolean z) {
        View view = this.m;
        view.animate().cancel();
        View view2 = this.n;
        view2.animate().cancel();
        ImageView imageView = this.o;
        imageView.animate().cancel();
        if (this.i.isPlaying()) {
            this.y = true;
            PostTask.b(rh3.a, new f52(25, this), 250L);
        }
        this.l = true;
        if (z) {
            view2.animate().alpha(1.0f).setStartDelay(0L).setDuration(d(250));
            view.animate().alpha(1.0f).setStartDelay(0L).setDuration(d(500)).setListener(new i12(this, i));
            imageView.animate().alpha(1.0f).setStartDelay(0L).setDuration(d(250)).setListener(new j12(this));
            return;
        }
        view.setAlpha(1.0f);
        view2.setAlpha(1.0f);
        imageView.setAlpha(1.0f);
        this.p.setClickable(true);
        this.r.setClickable(true);
        imageView.setClickable(true);
        c(i);
    }

    public final void f() {
        this.j.start();
        int i = R$drawable.ic_pause_circle_outline_white_24dp;
        ImageView imageView = this.o;
        imageView.setImageResource(i);
        imageView.setContentDescription(this.f.getResources().getString(R$string.accessibility_pause_video));
        e(1, false);
    }

    public final void g(boolean z) {
        if (z) {
            if (this.C) {
                return;
            }
            this.A = this.e.getNavigationBarColor();
            this.B = this.e.getNavigationBarDividerColor();
        }
        this.e.setNavigationBarColor(z ? -16777216 : this.A);
        this.e.setNavigationBarDividerColor(z ? -16777216 : this.B);
        uh3.m(this.e.getDecorView().getRootView(), !z);
        this.C = z;
    }

    public final void h() {
        VideoView videoView = this.i;
        try {
            String strO = org.chromium.components.browser_ui.photo_picker.b.o(Long.valueOf(videoView.getCurrentPosition()));
            String strO2 = org.chromium.components.browser_ui.photo_picker.b.o(Long.valueOf(videoView.getDuration()));
            Context context = this.f;
            String string = context.getResources().getString(R$string.photo_picker_video_duration, strO, strO2);
            TextView textView = this.u;
            textView.setText(string);
            textView.setContentDescription(context.getResources().getString(R$string.accessibility_playback_time, strO, strO2));
            this.w.setProgress(videoView.getDuration() != 0 ? (videoView.getCurrentPosition() * 100) / videoView.getDuration() : 0);
            if (videoView.isPlaying() && this.y) {
                this.y = true;
                PostTask.b(rh3.a, new f52(25, this), 250L);
            }
        } catch (IllegalStateException unused) {
        }
    }

    @Override // android.view.View.OnClickListener
    public final void onClick(View view) {
        int id = view.getId();
        int i = R$id.video_player_play_button;
        Context context = this.f;
        if (id == i) {
            if (!this.i.isPlaying()) {
                f();
                return;
            }
            this.y = false;
            this.j.pause();
            int i2 = R$drawable.ic_play_circle_filled_white_24dp;
            ImageView imageView = this.o;
            imageView.setImageResource(i2);
            imageView.setContentDescription(context.getResources().getString(R$string.accessibility_play_video));
            e(0, false);
            return;
        }
        if (id == R$id.back_button) {
            b();
            return;
        }
        if (id == R$id.mute) {
            boolean z = !this.q;
            this.q = z;
            ImageView imageView2 = this.p;
            if (z) {
                this.j.setVolume(1.0f, 1.0f);
                imageView2.setImageResource(R$drawable.ic_volume_on_white_24dp);
                imageView2.setContentDescription(context.getResources().getString(R$string.accessibility_mute_video));
                return;
            } else {
                this.j.setVolume(0.0f, 0.0f);
                imageView2.setImageResource(R$drawable.ic_volume_off_white_24dp);
                imageView2.setContentDescription(context.getResources().getString(R$string.accessibility_unmute_video));
                return;
            }
        }
        if (id == R$id.fullscreen) {
            this.t = true;
            View decorView = this.e.getDecorView();
            if (this.s) {
                decorView.setSystemUiVisibility(this.z);
                return;
            }
            decorView.setOnSystemUiVisibilityChangeListener(this);
            int systemUiVisibility = decorView.getSystemUiVisibility();
            this.z = systemUiVisibility;
            decorView.setSystemUiVisibility(systemUiVisibility | 2048 | 4 | 2 | 1024 | 1);
        }
    }

    @Override // android.view.View
    public final void onConfigurationChanged(Configuration configuration) {
        getHandler().post(new ve3(9, this));
        super.onConfigurationChanged(configuration);
    }

    @Override // android.widget.SeekBar.OnSeekBarChangeListener
    public final void onProgressChanged(SeekBar seekBar, int i, boolean z) {
        if (z) {
            this.j.seekTo(Math.round((i / 100.0f) * this.i.getDuration()), 3);
            h();
        }
    }

    @Override // android.widget.SeekBar.OnSeekBarChangeListener
    public final void onStartTrackingTouch(SeekBar seekBar) {
        e(0, false);
        boolean zIsPlaying = this.i.isPlaying();
        ImageView imageView = this.o;
        if (zIsPlaying) {
            this.y = false;
            this.j.pause();
            imageView.setImageResource(R$drawable.ic_play_circle_filled_white_24dp);
            imageView.setContentDescription(this.f.getResources().getString(R$string.accessibility_play_video));
            e(0, false);
            this.x = true;
        }
        this.v.setVisibility(0);
        imageView.setVisibility(8);
    }

    @Override // android.widget.SeekBar.OnSeekBarChangeListener
    public final void onStopTrackingTouch(SeekBar seekBar) {
        c(this.x ? 1 : 2);
        if (this.x) {
            f();
            this.x = false;
        }
        this.v.setVisibility(8);
        this.o.setVisibility(0);
    }

    @Override // android.view.View.OnSystemUiVisibilityChangeListener
    public final void onSystemUiVisibilityChange(int i) {
        int i2 = i & 4;
        Context context = this.f;
        ImageView imageView = this.r;
        if (i2 == 0) {
            this.e.getDecorView().setOnSystemUiVisibilityChangeListener(null);
            imageView.setImageResource(R$drawable.ic_full_screen_white_24dp);
            imageView.setContentDescription(context.getResources().getString(R$string.accessibility_full_screen));
            this.s = false;
            if (!this.t) {
                getHandler().post(new d92(25, this));
                return;
            }
        } else {
            imageView.setImageResource(R$drawable.ic_full_screen_exit_white_24dp);
            imageView.setContentDescription(context.getResources().getString(R$string.accessibility_exit_full_screen));
            this.s = true;
        }
        a();
        this.t = false;
    }

    public class b implements Animator.AnimatorListener {
        public b() {
        }

        @Override // android.animation.Animator.AnimatorListener
        public final void onAnimationCancel(Animator animator) {
            PickerVideoPlayer pickerVideoPlayer = PickerVideoPlayer.this;
            View view = pickerVideoPlayer.m;
            pickerVideoPlayer.getClass();
        }

        @Override // android.animation.Animator.AnimatorListener
        public final void onAnimationEnd(Animator animator) {
            boolean z = PickerVideoPlayer.E;
            PickerVideoPlayer pickerVideoPlayer = PickerVideoPlayer.this;
            pickerVideoPlayer.p.setClickable(false);
            pickerVideoPlayer.r.setClickable(false);
            pickerVideoPlayer.y = false;
        }

        @Override // android.animation.Animator.AnimatorListener
        public final void onAnimationStart(Animator animator) {
            PickerVideoPlayer pickerVideoPlayer = PickerVideoPlayer.this;
            View view = pickerVideoPlayer.m;
            pickerVideoPlayer.getClass();
        }

        @Override // android.animation.Animator.AnimatorListener
        public final void onAnimationRepeat(Animator animator) {
        }
    }

    public class c implements Animator.AnimatorListener {
        public c() {
        }

        @Override // android.animation.Animator.AnimatorListener
        public final void onAnimationCancel(Animator animator) {
            PickerVideoPlayer pickerVideoPlayer = PickerVideoPlayer.this;
            ImageView imageView = pickerVideoPlayer.o;
            pickerVideoPlayer.getClass();
        }

        @Override // android.animation.Animator.AnimatorListener
        public final void onAnimationEnd(Animator animator) {
            PickerVideoPlayer pickerVideoPlayer = PickerVideoPlayer.this;
            pickerVideoPlayer.o.setClickable(false);
            ImageView imageView = pickerVideoPlayer.o;
            pickerVideoPlayer.getClass();
        }

        @Override // android.animation.Animator.AnimatorListener
        public final void onAnimationStart(Animator animator) {
            PickerVideoPlayer pickerVideoPlayer = PickerVideoPlayer.this;
            pickerVideoPlayer.l = false;
            ImageView imageView = pickerVideoPlayer.o;
            pickerVideoPlayer.getClass();
        }

        @Override // android.animation.Animator.AnimatorListener
        public final void onAnimationRepeat(Animator animator) {
        }
    }

    public static void setProgressCallback(e eVar) {
    }
}
