package com.byd.systemsettings.screensaver;

import android.animation.Animator;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Typeface;
import android.os.Message;
import android.text.SpannableString;
import android.text.TextUtils;
import android.text.style.RelativeSizeSpan;
import android.view.ActionMode;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.SearchEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.view.accessibility.AccessibilityEvent;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import com.byd.mvp.common.BydLayoutDirection;
import com.byd.mvp.ui.widget.FrameAnimationView;
import com.byd.mvp.utils.AppConfig;
import com.byd.systemsettings.R;
import com.byd.systemsettings.screensaver.model.MediaPlayData;
import com.byd.systemsettings.screensaver.present.AudioScreenPresenter;
import com.byd.systemsettings.screensaver.view.NumberImgTime;
import com.byd.systemsettings.system.BydSystemSettings;
import com.byd.systemsettings.system.SystemSettingsModule;
import com.byd.systemsettings.system.model.BydDate;
import com.byd.systemsettings.system.model.ClockStyle;
import com.byd.systemsettings.system.utils.BydDateFormat;
import com.byd.systemsettings.system.utils.BydDateUtils;
import java.lang.ref.WeakReference;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;
import k4.e;
import o1.e;
import o1.g;
import o1.o;
import z3.a;
import z3.d;
import z3.f;

/* JADX INFO: loaded from: classes2.dex */
public class AudioScreenPager extends a<AudioScreenPresenter> implements View.OnClickListener, e, k4.a {
    private static final int ARG1_UPDATE_TIME = 33;
    private static final int DEF_INTERVAL = 6;
    private static final long DELAY_UPDATE_TIME = 1000;
    private static final String TAG = "AudioScreenPager";
    private View bgScreen;
    private FrameLayout clock3AnimParent;
    private FrameAnimationView clock3Animation;
    private ViewGroup content;
    private int curMusicSrc;
    private volatile ClockStyle curStyle;
    private FrameLayout flClock;
    private FrameLayout flPlayer;
    private volatile boolean isDetached;
    private ImageView ivPlayerLogo;
    private ImageView ivStopPlay;
    private int mDayNightMode;
    private g mLottieDrawable;
    private ImageView mLottieImageView;
    private int mOrientation;
    private NumberImgTime nitClock1Hour;
    private NumberImgTime nitClock1Minute;
    private ViewGroup playerLogoNameParent;
    private ViewGroup playerOperationParent;
    private volatile int preMusicSrc;
    private volatile int prePlayState;
    private StringBuffer sBuf;
    private volatile long timeActionup3Pointer;
    private TextView tvClock1Date;
    private TextView tvClock3Hour;
    private TextView tvClock3Minute;
    private TextView tvPlayerName;
    private TextView tvSingerName;
    private TextView tvSongName;
    private View viewClock1;
    private View viewClock2;
    private View viewClock3;
    private View viewPlayer;

    /* JADX INFO: renamed from: com.byd.systemsettings.screensaver.AudioScreenPager$5, reason: invalid class name */
    public static /* synthetic */ class AnonymousClass5 {
        public static final /* synthetic */ int[] $SwitchMap$com$byd$systemsettings$system$model$ClockStyle;

        static {
            int[] iArr = new int[ClockStyle.values().length];
            $SwitchMap$com$byd$systemsettings$system$model$ClockStyle = iArr;
            try {
                iArr[ClockStyle.CLOSE.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$byd$systemsettings$system$model$ClockStyle[ClockStyle.CLOCK_1.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$com$byd$systemsettings$system$model$ClockStyle[ClockStyle.CLOCK_2.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
            try {
                $SwitchMap$com$byd$systemsettings$system$model$ClockStyle[ClockStyle.CLOCK_3.ordinal()] = 4;
            } catch (NoSuchFieldError unused4) {
            }
        }
    }

    public AudioScreenPager(Context context) {
        super(context);
        this.timeActionup3Pointer = 0L;
        this.curMusicSrc = -1;
    }

    @SuppressLint({"NewApi"})
    private void applyFont(TextView textView, String str) {
        if (textView == null || str == null || str.isEmpty()) {
            return;
        }
        textView.setTypeface(new Typeface.Builder(str).build());
    }

    private boolean checkChildAndShow(ViewGroup viewGroup, Class<?> cls) {
        View child = getChild(viewGroup, cls);
        if (child != null) {
            child.setVisibility(0);
            if (child instanceof FrameAnimationView) {
                ((FrameAnimationView) child).m();
            }
        }
        return child != null;
    }

    private void clearLottieDrawable() {
        g gVar = this.mLottieDrawable;
        if (gVar == null || !gVar.E()) {
            return;
        }
        this.mLottieDrawable.e();
        this.mLottieDrawable.f();
    }

    private View getChild(ViewGroup viewGroup, Class<?> cls) {
        if (viewGroup != null && viewGroup.getChildCount() > 0 && cls != null) {
            for (int i8 = 0; i8 < viewGroup.getChildCount(); i8++) {
                View childAt = viewGroup.getChildAt(i8);
                if (cls.getName().equals(childAt.getClass().getName())) {
                    return childAt;
                }
            }
        }
        return null;
    }

    private int getClock3LayoutId() {
        x3.a.a(TAG, "---getClock3LayoutId---mDirection: " + this.mDirection);
        return this.mDirection == BydLayoutDirection.LTR_Rudder_R ? R.layout.view_clock_3_audio_screen_r : R.layout.view_clock_3_audio_screen;
    }

    private String getDoubleDigit(int i8) {
        if (this.sBuf == null) {
            this.sBuf = new StringBuffer();
        }
        this.sBuf.setLength(0);
        if (i8 < 10) {
            this.sBuf.append("0");
        }
        this.sBuf.append(i8);
        return this.sBuf.toString();
    }

    private String getFontName() {
        return null;
    }

    private void initPlayerView(View view) {
        this.ivPlayerLogo = (ImageView) view.findViewById(R.id.iv_player_logo_audio_screen);
        this.tvPlayerName = (TextView) view.findViewById(R.id.tv_player_name_audio_screen);
        this.tvSongName = (TextView) view.findViewById(R.id.tv_song_name_audio_screen);
        this.tvSingerName = (TextView) view.findViewById(R.id.tv_singer_name_audio_screen);
        this.ivStopPlay = (ImageView) view.findViewById(R.id.iv_stop_or_start_player_audio_screen);
        this.playerLogoNameParent = (ViewGroup) view.findViewById(R.id.ll_player_logo_name_audio_screen);
        this.playerOperationParent = (ViewGroup) view.findViewById(R.id.ll_operation_player_audio_screen);
        view.findViewById(R.id.iv_previous_song_player_audio_screen).setOnClickListener(this);
        view.findViewById(R.id.iv_next_song_player_audio_screen).setOnClickListener(this);
        this.ivStopPlay.setOnClickListener(this);
        this.ivPlayerLogo.setOnClickListener(this);
        this.tvPlayerName.setOnClickListener(this);
        this.tvSongName.setOnClickListener(this);
        this.tvSingerName.setOnClickListener(this);
        this.tvSongName.setSelected(true);
        this.tvSingerName.setSelected(true);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void onLeftToRightActionMove() {
        x3.a.a(TAG, "---onLeftToRightActionMove---");
        int i8 = ((AudioScreenPresenter) getP()).getClockStyle().value - 1;
        ClockStyle clockStyle = ClockStyle.CLOSE;
        if (i8 <= clockStyle.value) {
            return;
        }
        ClockStyle style = ClockStyle.getStyle(i8);
        x3.a.a(TAG, "---onLeftToRightActionMove---styleNew: " + style);
        if (style != clockStyle) {
            ((AudioScreenPresenter) getP()).setClockStyle(style);
            onClockStyleChanged(style);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void onRightToLeftActionMove() {
        x3.a.a(TAG, "---onRightToLeftActionMove---");
        int i8 = ((AudioScreenPresenter) getP()).getClockStyle().value + 1;
        if (i8 > ClockStyle.CLOCK_3.value) {
            return;
        }
        ClockStyle style = ClockStyle.getStyle(i8);
        x3.a.a(TAG, "---onRightToLeftActionMove---styleNew: " + style);
        if (style != ClockStyle.CLOSE) {
            ((AudioScreenPresenter) getP()).setClockStyle(style);
            onClockStyleChanged(style);
        }
    }

    private void setLottieAnim() {
        if (this.mLottieDrawable == null) {
            this.mLottieDrawable = new g();
        }
        clearLottieDrawable();
        boolean zC = AppConfig.c();
        x3.a.a(TAG, "---setLottieAnim---rightRudder：" + zC);
        e.b.a(getContext(), this.mOrientation != 2 ? AudioScreenConstant.NIGHT_ANIMATION_JSON_NAME : "screensaver/land_night_lottie_anim.json", new o() { // from class: com.byd.systemsettings.screensaver.AudioScreenPager.3
            @Override // o1.o
            public void onCompositionLoaded(o1.e eVar) {
                AudioScreenPager.this.mLottieDrawable.L(eVar);
                AudioScreenPager.this.mLottieDrawable.P(null);
                AudioScreenPager.this.mLottieDrawable.a0(-1);
                AudioScreenPager.this.mLottieDrawable.H();
            }
        });
        if (zC) {
            this.mLottieImageView.setScaleX(-1.0f);
        }
        this.mLottieImageView.setImageDrawable(this.mLottieDrawable);
    }

    private void showChild(ViewGroup viewGroup, Class<?> cls, boolean z7) {
        View child = getChild(viewGroup, cls);
        if (child != null) {
            child.setVisibility(z7 ? 0 : 8);
            if (child instanceof FrameAnimationView) {
                ((FrameAnimationView) child).n();
            }
        }
    }

    private void startPlayerApp() {
        ((AudioScreenPresenter) getP()).startPlayerApp(this.curMusicSrc);
        d dVar = this.mCallback;
        if (dVar != null) {
            dVar.onBackPress();
        }
    }

    private void updateClock1TimeView(NumberImgTime numberImgTime, NumberImgTime numberImgTime2, TextView textView, int i8, int i9, boolean z7) {
        boolean zIs24H = ((AudioScreenPresenter) getP()).is24H();
        if (numberImgTime != null) {
            if (!zIs24H) {
                if (i8 > 12) {
                    i8 -= 12;
                } else if (i8 == 0) {
                    i8 = 12;
                }
            }
            numberImgTime.setTime(i8);
        }
        if (numberImgTime2 != null) {
            numberImgTime2.setTime(i9);
        }
        if (textView != null) {
            String formatDate = BydDateFormat.getFormatDate();
            x3.a.a(TAG, "---updateClock1TimeView---showDate = " + formatDate);
            if (!zIs24H) {
                Resources resources = getContext().getResources();
                formatDate = formatDate + " " + (z7 ? resources.getString(R.string.clock_am) : resources.getString(R.string.clock_pm));
            }
            textView.setText(formatDate);
        }
        String.format(Locale.US, "英文星期的简称：%ta", new Date(System.currentTimeMillis()));
    }

    private void updateClock3TimeView(TextView textView, TextView textView2, BydDate bydDate) {
        boolean zIs24H = ((AudioScreenPresenter) getP()).is24H();
        if (!zIs24H) {
            int hour = bydDate.getHour();
            if (bydDate.getHour() > 12) {
                hour -= 12;
            } else if (hour == 0) {
                hour = 12;
            }
            bydDate.setHour(hour);
        }
        if (textView != null) {
            textView.setText(getDoubleDigit(bydDate.getHour()));
        }
        if (textView2 != null) {
            Resources resources = getContext().getResources();
            String doubleDigit = getDoubleDigit(bydDate.getMinute());
            String string = bydDate.isAm() ? resources.getString(R.string.clock_am) : resources.getString(R.string.clock_pm);
            SpannableString spannableString = new SpannableString(doubleDigit);
            if (!zIs24H) {
                spannableString = new SpannableString(doubleDigit + string);
            }
            spannableString.setSpan(new RelativeSizeSpan(0.281f), doubleDigit.length(), spannableString.length(), 33);
            textView2.setText(spannableString);
        }
    }

    private void updateClockTimeView(ClockStyle clockStyle) {
        BydDate curDateAmpm = BydDateUtils.getCurDateAmpm(getContext().getApplicationContext());
        x3.a.a(TAG, "updateClockTimeView", ", date = " + curDateAmpm);
        if (clockStyle == ClockStyle.CLOCK_1) {
            if (!curDateAmpm.isH24()) {
                curDateAmpm.isAm();
            }
            updateClock1TimeView(this.nitClock1Hour, this.nitClock1Minute, this.tvClock1Date, curDateAmpm.getHour(), curDateAmpm.getMinute(), curDateAmpm.isAm());
        } else if (clockStyle == ClockStyle.CLOCK_3) {
            updateClock3TimeView(this.tvClock3Hour, this.tvClock3Minute, curDateAmpm);
        }
    }

    private void updatePlayStateImg(ImageView imageView, int i8, int i9) {
        if (this.prePlayState == i8 && this.preMusicSrc == i9) {
            return;
        }
        this.prePlayState = i8;
        int i10 = R.drawable.icon_card_play_60;
        if (i8 == 1) {
            i10 = R.drawable.icon_card_suspend_60;
        }
        if (imageView != null) {
            imageView.setImageResource(i10);
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:26:0x0040  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private void updatePlayerName(android.widget.TextView r3, int r4) {
        /*
            r2 = this;
            if (r3 != 0) goto L3
            return
        L3:
            r0 = 1
            java.lang.String r1 = ""
            if (r4 == r0) goto L40
            r0 = 2
            if (r4 == r0) goto L40
            r0 = 3
            if (r4 == r0) goto L40
            r0 = 4
            if (r4 == r0) goto L40
            r0 = 6
            if (r4 == r0) goto L35
            r0 = 7
            if (r4 == r0) goto L40
            r0 = 30
            if (r4 == r0) goto L32
            r0 = 31
            if (r4 == r0) goto L2f
            switch(r4) {
                case 16: goto L24;
                case 17: goto L40;
                case 18: goto L40;
                default: goto L22;
            }
        L22:
            r4 = r1
            goto L4a
        L24:
            z3.f r4 = r2.getP()
            com.byd.systemsettings.screensaver.present.AudioScreenPresenter r4 = (com.byd.systemsettings.screensaver.present.AudioScreenPresenter) r4
            java.lang.String r4 = r4.getSpotifyAppName()
            goto L4a
        L2f:
            java.lang.String r4 = "Android Auto"
            goto L4a
        L32:
            java.lang.String r4 = "Apple CarPlay"
            goto L4a
        L35:
            z3.f r4 = r2.getP()
            com.byd.systemsettings.screensaver.present.AudioScreenPresenter r4 = (com.byd.systemsettings.screensaver.present.AudioScreenPresenter) r4
            java.lang.String r4 = r4.getConnectedBtName()
            goto L4a
        L40:
            z3.f r4 = r2.getP()
            com.byd.systemsettings.screensaver.present.AudioScreenPresenter r4 = (com.byd.systemsettings.screensaver.present.AudioScreenPresenter) r4
            java.lang.String r4 = r4.getLocalMediaAppName()
        L4a:
            boolean r0 = android.text.TextUtils.isEmpty(r4)
            if (r0 == 0) goto L51
            goto L52
        L51:
            r1 = r4
        L52:
            r3.setText(r1)
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.byd.systemsettings.screensaver.AudioScreenPager.updatePlayerName(android.widget.TextView, int):void");
    }

    private void updatePlayerSrcImg(ImageView imageView, int i8) {
        if (this.preMusicSrc == i8 || imageView == null) {
            return;
        }
        this.preMusicSrc = i8;
        if (i8 == 1) {
            imageView.setImageResource(R.drawable.icon_media_ss);
        }
        if (i8 == 2 || i8 == 3) {
            imageView.setImageResource(R.drawable.icon_widget_usb);
            return;
        }
        if (i8 == 4) {
            imageView.setImageResource(R.drawable.icon_widget_sd);
            return;
        }
        if (i8 == 6) {
            imageView.setImageResource(R.drawable.icon_music_ss);
            return;
        }
        if (i8 == 7) {
            imageView.setImageResource(R.drawable.icon_fm_ss);
            return;
        }
        if (i8 == 30) {
            imageView.setImageResource(R.drawable.icon_app_carplay_ss);
            return;
        }
        if (i8 == 31) {
            imageView.setImageResource(R.drawable.icon_app_android_auto_ss);
            return;
        }
        switch (i8) {
            case 16:
                imageView.setImageResource(R.drawable.icon_app_spotify_ss);
                break;
            case 17:
                imageView.setImageResource(R.drawable.icon_dab_ss);
                break;
            case 18:
                imageView.setImageResource(R.drawable.icon_am_ss);
                break;
        }
    }

    @Override // z3.a
    public void beforeDestroy() {
        super.beforeDestroy();
        if (this.clock3AnimParent != null) {
            x3.a.a(TAG, "beforeDestroy", "clock3Animation: " + this.clock3Animation);
            FrameAnimationView frameAnimationView = this.clock3Animation;
            if (frameAnimationView != null) {
                frameAnimationView.j();
                this.clock3Animation.setVisibility(8);
                this.clock3Animation = null;
            }
            this.clock3AnimParent.removeAllViews();
        }
        x3.a.a(TAG, "beforeDestroy");
    }

    @Override // z3.g
    public /* bridge */ /* synthetic */ void bindEvent() {
        super.bindEvent();
    }

    @Override // k4.e, android.view.Window.Callback
    public /* bridge */ /* synthetic */ boolean dispatchGenericMotionEvent(MotionEvent motionEvent) {
        return super.dispatchGenericMotionEvent(motionEvent);
    }

    @Override // k4.e, android.view.Window.Callback
    public /* bridge */ /* synthetic */ boolean dispatchKeyEvent(KeyEvent keyEvent) {
        return super.dispatchKeyEvent(keyEvent);
    }

    @Override // k4.e, android.view.Window.Callback
    public /* bridge */ /* synthetic */ boolean dispatchKeyShortcutEvent(KeyEvent keyEvent) {
        return super.dispatchKeyShortcutEvent(keyEvent);
    }

    @Override // k4.e, android.view.Window.Callback
    public /* bridge */ /* synthetic */ boolean dispatchPopulateAccessibilityEvent(AccessibilityEvent accessibilityEvent) {
        return super.dispatchPopulateAccessibilityEvent(accessibilityEvent);
    }

    @Override // k4.e, android.view.Window.Callback
    public /* bridge */ /* synthetic */ boolean dispatchTouchEvent(MotionEvent motionEvent) {
        return super.dispatchTouchEvent(motionEvent);
    }

    @Override // k4.e, android.view.Window.Callback
    public /* bridge */ /* synthetic */ boolean dispatchTrackballEvent(MotionEvent motionEvent) {
        return super.dispatchTrackballEvent(motionEvent);
    }

    public ClockStyle getCurClockStyle() {
        return this.curStyle;
    }

    @Override // z3.a
    public String getDialogTag() {
        return TAG;
    }

    @Override // z3.g
    public int getLayoutId() {
        return R.layout.activity_audio_screen;
    }

    @Override // z3.g
    public /* bridge */ /* synthetic */ Object getP() {
        return super.getP();
    }

    /* JADX WARN: Incorrect return type in method signature: ()TP; */
    @Override // z3.g
    public /* bridge */ /* synthetic */ f getP() {
        return super.getP();
    }

    @Override // z3.e
    public boolean hasEditText() {
        return false;
    }

    @Override // z3.e
    public void initData(Context context) {
        x3.a.a(TAG, "---initData---");
        updateTimeView();
        onMusicPlayerStateChanged(((AudioScreenPresenter) getP()).isMusicActive());
        postDelayed(new Runnable() { // from class: com.byd.systemsettings.screensaver.AudioScreenPager.4
            @Override // java.lang.Runnable
            public void run() {
                x3.a.a(AudioScreenPager.TAG, "---initData---initMediaInfo---");
                ((AudioScreenPresenter) AudioScreenPager.this.getP()).initMediaInfo();
            }
        }, 300L);
    }

    @Override // z3.e
    public void initDataBeforeLoadLayout(Context context) {
        super.initDataBeforeLoadLayout(context);
    }

    @Override // z3.e
    public boolean initHandler() {
        return true;
    }

    @Override // z3.a
    public int initHeight(Context context) {
        return getFullSrceenHeight(context);
    }

    @Override // z3.e
    public void initViews(Context context) {
        ((AudioScreenPresenter) getP()).init();
        x3.a.a(TAG, "---initViews---");
        this.content = (ViewGroup) this.mRootView.findViewById(R.id.rl_content_screen);
        this.flPlayer = (FrameLayout) this.mRootView.findViewById(R.id.fl_idle_and_music_player_widget);
        if (this.viewPlayer == null) {
            View viewInflate = LayoutInflater.from(context).inflate(R.layout.view_music_player_audio_screen, (ViewGroup) null);
            this.viewPlayer = viewInflate;
            initPlayerView(viewInflate);
        }
        this.flPlayer.addView(this.viewPlayer);
        this.flClock = (FrameLayout) this.mRootView.findViewById(R.id.fl_clock_widget);
        this.bgScreen = this.mRootView.findViewById(R.id.bg_audio_screen);
        this.mLottieImageView = (ImageView) this.mRootView.findViewById(R.id.time_screen_mode_lottie_image_view);
        FrameLayout frameLayout = (FrameLayout) this.mRootView.findViewById(R.id.iv_animation_audio_screen);
        this.clock3AnimParent = frameLayout;
        frameLayout.setVisibility(8);
        this.bgScreen.setOnClickListener(new View.OnClickListener() { // from class: com.byd.systemsettings.screensaver.AudioScreenPager.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                x3.a.a(AudioScreenPager.TAG, "---initViews---onClick--bgScreen--");
                if (AudioScreenPager.this.mCallback != null) {
                    AudioScreenPager.this.mCallback.onBackPress();
                }
            }
        });
        this.bgScreen.setOnTouchListener(new View.OnTouchListener() { // from class: com.byd.systemsettings.screensaver.AudioScreenPager.2
            private boolean actionMove = false;
            private boolean leftToRight = false;
            private float preX;
            private float preY;

            @Override // android.view.View.OnTouchListener
            public boolean onTouch(View view, MotionEvent motionEvent) {
                int pointerCount = motionEvent.getPointerCount();
                int action = motionEvent.getAction() & 255;
                if (action == 5 || action == 6) {
                    x3.a.a(AudioScreenPager.TAG, "---initViews--ACTION_POINTER_DOWN or ACTION_POINTER_UP--pointerCount: " + pointerCount);
                    if (pointerCount > 2) {
                        AudioScreenPager.this.timeActionup3Pointer = System.currentTimeMillis();
                        return false;
                    }
                }
                BigDecimal bigDecimalSubtract = new BigDecimal(System.currentTimeMillis()).subtract(new BigDecimal(AudioScreenPager.this.timeActionup3Pointer));
                x3.a.a(AudioScreenPager.TAG, "---initViews--onTouch--delta: " + bigDecimalSubtract.longValue());
                if (bigDecimalSubtract.longValue() < 200) {
                    return false;
                }
                int action2 = motionEvent.getAction();
                if (action2 == 0) {
                    this.preX = motionEvent.getX();
                    this.preY = motionEvent.getY();
                } else if (action2 != 1) {
                    if (action2 == 2) {
                        float x7 = motionEvent.getX();
                        float y7 = motionEvent.getY();
                        float f8 = x7 - this.preX;
                        if (Math.abs(f8) > Math.abs(y7 - this.preY)) {
                            this.actionMove = true;
                            this.leftToRight = f8 > 0.0f;
                            return true;
                        }
                    }
                } else if (this.actionMove) {
                    this.actionMove = false;
                    if (Math.abs(motionEvent.getX() - this.preX) > 20.0f) {
                        if (this.leftToRight) {
                            AudioScreenPager.this.onLeftToRightActionMove();
                        } else {
                            AudioScreenPager.this.onRightToLeftActionMove();
                        }
                        return true;
                    }
                }
                return false;
            }
        });
        this.curStyle = ((AudioScreenPresenter) getP()).getClockStyle();
        sendMessageDelay(33, 33, DELAY_UPDATE_TIME, null);
        this.mDayNightMode = getContext().getResources().getConfiguration().uiMode & 48;
        this.mOrientation = getContext().getResources().getConfiguration().orientation;
    }

    @Override // z3.a
    public int initWidth(Context context) {
        return getFullSrceenWidth(context);
    }

    @Override // z3.e
    public void loadUIStyleOnDenzaBrand(boolean z7) {
        super.loadUIStyleOnDenzaBrand(z7);
    }

    @Override // k4.e, android.view.Window.Callback
    public /* bridge */ /* synthetic */ void onActionModeFinished(ActionMode actionMode) {
        super.onActionModeFinished(actionMode);
    }

    @Override // k4.e, android.view.Window.Callback
    public /* bridge */ /* synthetic */ void onActionModeStarted(ActionMode actionMode) {
        super.onActionModeStarted(actionMode);
    }

    @Override // k4.a, android.animation.Animator.AnimatorListener
    public /* bridge */ /* synthetic */ void onAnimationCancel(Animator animator) {
        super.onAnimationCancel(animator);
    }

    @Override // k4.a, android.animation.Animator.AnimatorListener
    public void onAnimationEnd(Animator animator) {
        x3.a.a(TAG, "---onAnimationEnd---");
        if (this.isDetached) {
            return;
        }
        SystemSettingsModule.getInstance().hideAvm();
    }

    @Override // k4.a, android.animation.Animator.AnimatorListener
    public /* bridge */ /* synthetic */ void onAnimationRepeat(Animator animator) {
        super.onAnimationRepeat(animator);
    }

    @Override // k4.a, android.animation.Animator.AnimatorListener
    public void onAnimationStart(Animator animator) {
        x3.a.a(TAG, "---onAnimationStart---");
    }

    @Override // k4.e, android.view.Window.Callback
    public void onAttachedToWindow() {
        x3.a.a(TAG, "---onAttachedToWindow---");
        this.isDetached = false;
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        int id = view.getId();
        if (id == R.id.view_music_player_audio_screen) {
            return;
        }
        if (id == R.id.iv_previous_song_player_audio_screen) {
            ((AudioScreenPresenter) getP()).preSong();
            return;
        }
        if (id == R.id.iv_stop_or_start_player_audio_screen) {
            ((AudioScreenPresenter) getP()).startOrStopPlay();
            return;
        }
        if (id == R.id.iv_next_song_player_audio_screen) {
            ((AudioScreenPresenter) getP()).nextSong();
        } else if (id == R.id.iv_player_logo_audio_screen || id == R.id.tv_player_name_audio_screen || id == R.id.tv_song_name_audio_screen || id == R.id.tv_singer_name_audio_screen) {
            startPlayerApp();
        }
    }

    public void onClockStyleChanged(ClockStyle clockStyle) {
        x3.a.a(TAG, "onClockStyleChanged", ", style = " + clockStyle);
        WeakReference<Context> weakReference = this.ref;
        if (weakReference == null || weakReference.get() == null) {
            return;
        }
        this.curStyle = clockStyle;
        ((AudioScreenPresenter) getP()).setNavibarModeScreensaver(this.curStyle);
        LayoutInflater layoutInflaterFrom = LayoutInflater.from(this.ref.get());
        int i8 = AnonymousClass5.$SwitchMap$com$byd$systemsettings$system$model$ClockStyle[clockStyle.ordinal()];
        View view = null;
        if (i8 == 1) {
            this.flClock.setVisibility(4);
        } else if (i8 == 2) {
            if (this.viewClock1 == null) {
                View viewInflate = layoutInflaterFrom.inflate(R.layout.view_clock_1_audio_screen, (ViewGroup) null);
                this.viewClock1 = viewInflate;
                this.nitClock1Hour = (NumberImgTime) viewInflate.findViewById(R.id.nit_hour_clock_1_audio_screen);
                this.nitClock1Minute = (NumberImgTime) this.viewClock1.findViewById(R.id.nit_minute_clock_1_audio_screen);
                this.tvClock1Date = (TextView) this.viewClock1.findViewById(R.id.tv_date_clock_1_audio_screen);
            }
            this.bgScreen.setBackgroundResource(R.drawable.bg_screen);
            setLottieAnim();
            view = this.viewClock1;
            updateClockTimeView(clockStyle);
        } else if (i8 == 3) {
            if (this.viewClock2 == null) {
                this.viewClock2 = layoutInflaterFrom.inflate(R.layout.view_clock_2_audio_screen, (ViewGroup) null);
            }
            view = this.viewClock2;
            this.bgScreen.setBackgroundResource(R.drawable.bg_screen);
            clearLottieDrawable();
        } else if (i8 == 4) {
            if (this.viewClock3 == null) {
                View viewInflate2 = layoutInflaterFrom.inflate(getClock3LayoutId(), (ViewGroup) null);
                this.viewClock3 = viewInflate2;
                this.tvClock3Hour = (TextView) viewInflate2.findViewById(R.id.tv_hour_clock_3_audio_screen);
                this.tvClock3Minute = (TextView) this.viewClock3.findViewById(R.id.tv_minute_clock_3_audio_screen);
            }
            view = this.viewClock3;
            this.bgScreen.setBackgroundResource(R.drawable.bg_screen);
            updateClockTimeView(clockStyle);
            clearLottieDrawable();
        }
        if (view != null) {
            this.flClock.removeAllViews();
            this.flClock.setVisibility(0);
            this.flClock.addView(view);
            this.flPlayer.setVisibility(((AudioScreenPresenter) getP()).isMusicActive() ? 0 : 8);
        } else {
            this.flPlayer.setVisibility(8);
        }
        if (clockStyle != ClockStyle.CLOCK_3) {
            this.clock3AnimParent.setVisibility(8);
            showChild(this.clock3AnimParent, FrameAnimationView.class, true);
            return;
        }
        if (!checkChildAndShow(this.clock3AnimParent, FrameAnimationView.class)) {
            this.clock3Animation = new FrameAnimationView(this.clock3AnimParent.getContext());
            TypedArray typedArrayObtainTypedArray = this.clock3AnimParent.getResources().obtainTypedArray(R.array.imgs_screensaver_clock_3);
            int length = typedArrayObtainTypedArray.length();
            ArrayList arrayList = new ArrayList();
            for (int i9 = 0; i9 < length; i9++) {
                arrayList.add(Integer.valueOf(typedArrayObtainTypedArray.getResourceId(i9, 0)));
            }
            typedArrayObtainTypedArray.recycle();
            this.clock3Animation.setFrames(arrayList);
            this.clock3AnimParent.addView(this.clock3Animation);
        }
        this.clock3AnimParent.setVisibility(0);
    }

    @Override // k4.e, android.view.Window.Callback
    public /* bridge */ /* synthetic */ void onContentChanged() {
        super.onContentChanged();
    }

    @Override // k4.e, android.view.Window.Callback
    public /* bridge */ /* synthetic */ boolean onCreatePanelMenu(int i8, Menu menu) {
        return super.onCreatePanelMenu(i8, menu);
    }

    @Override // k4.e, android.view.Window.Callback
    public /* bridge */ /* synthetic */ View onCreatePanelView(int i8) {
        return super.onCreatePanelView(i8);
    }

    @Override // z3.a, z3.e
    public void onDestroy() {
        x3.a.a(TAG, "---onDestroy---");
        BydSystemSettings.Singleton.get().setNavibarModeScreensaver(getContext(), 1);
        super.onDestroy();
        clearLottieDrawable();
    }

    @Override // k4.e, android.view.Window.Callback
    public void onDetachedFromWindow() {
        this.isDetached = true;
        x3.a.a(TAG, "---onDetachedFromWindow---");
        FrameLayout frameLayout = this.clock3AnimParent;
        if (frameLayout != null) {
            frameLayout.removeAllViews();
        }
    }

    @Override // z3.e
    public void onHandlerUpdateUI(Message message) {
        int i8 = message.arg1;
        if (i8 == 33) {
            sendMessageDelay(33, 33, ((long) (60 - updateTimeView().getSecond())) * DELAY_UPDATE_TIME, null);
        } else if (i8 == 49) {
            onMusicDataChanged((MediaPlayData) message.obj);
        } else {
            if (i8 != 50) {
                return;
            }
            onClockStyleChanged((ClockStyle) message.obj);
        }
    }

    @Override // z3.e
    public void onLtrRudderR(boolean z7) {
        super.onLtrRudderR(z7);
        setViewGroupRtl(this.content, true);
        setViewGroupRtl(this.flPlayer, false);
        setViewGroupRtl(this.nitClock1Hour, false);
        setViewGroupRtl(this.nitClock1Minute, false);
    }

    @Override // k4.e, android.view.Window.Callback
    public /* bridge */ /* synthetic */ boolean onMenuItemSelected(int i8, MenuItem menuItem) {
        return super.onMenuItemSelected(i8, menuItem);
    }

    @Override // k4.e, android.view.Window.Callback
    public /* bridge */ /* synthetic */ boolean onMenuOpened(int i8, Menu menu) {
        return super.onMenuOpened(i8, menu);
    }

    public void onMusicDataChanged(MediaPlayData mediaPlayData) {
        onMusicPlayerStateChanged(((AudioScreenPresenter) getP()).isMusicActive());
        String musicName = mediaPlayData.getMusicName();
        String artistName = mediaPlayData.getArtistName();
        int playState = mediaPlayData.getPlayState();
        int musicSource = mediaPlayData.getMusicSource();
        this.curMusicSrc = musicSource;
        TextView textView = this.tvSingerName;
        if (textView != null) {
            if (TextUtils.isEmpty(artistName)) {
                artistName = "";
            }
            textView.setText(artistName);
        }
        TextView textView2 = this.tvSongName;
        if (textView2 != null) {
            if (TextUtils.isEmpty(musicName)) {
                musicName = "";
            }
            textView2.setText(musicName);
        }
        updatePlayStateImg(this.ivStopPlay, playState, musicSource);
        updatePlayerSrcImg(this.ivPlayerLogo, musicSource);
        updatePlayerName(this.tvPlayerName, musicSource);
        x3.a.a(TAG, "MediaPlayData:" + mediaPlayData);
    }

    public void onMusicPlayerStateChanged(boolean z7) {
        FrameLayout frameLayout = this.flPlayer;
        if (frameLayout != null) {
            frameLayout.setVisibility(z7 ? 0 : 8);
        }
    }

    @Override // k4.e, android.view.Window.Callback
    public /* bridge */ /* synthetic */ void onPanelClosed(int i8, Menu menu) {
        super.onPanelClosed(i8, menu);
    }

    @Override // k4.e, android.view.Window.Callback
    public /* bridge */ /* synthetic */ boolean onPreparePanel(int i8, View view, Menu menu) {
        return super.onPreparePanel(i8, view, menu);
    }

    @Override // z3.e
    public void onRtlRudderL(boolean z7) {
        super.onRtlRudderL(z7);
        setViewGroupRtl(this.content, false);
        setViewGroupRtl(this.viewPlayer, true);
        setViewGroupRtl(this.playerOperationParent, false);
    }

    @Override // k4.e, android.view.Window.Callback
    public /* bridge */ /* synthetic */ boolean onSearchRequested() {
        return super.onSearchRequested();
    }

    @Override // k4.e, android.view.Window.Callback
    public /* bridge */ /* synthetic */ boolean onSearchRequested(SearchEvent searchEvent) {
        return super.onSearchRequested(searchEvent);
    }

    @Override // k4.e, android.view.Window.Callback
    public /* bridge */ /* synthetic */ void onWindowAttributesChanged(WindowManager.LayoutParams layoutParams) {
        super.onWindowAttributesChanged(layoutParams);
    }

    @Override // k4.e, android.view.Window.Callback
    public void onWindowFocusChanged(boolean z7) {
        x3.a.a(TAG, "---onWindowFocusChanged---hasFocus: " + z7);
    }

    @Override // k4.e, android.view.Window.Callback
    public /* bridge */ /* synthetic */ ActionMode onWindowStartingActionMode(ActionMode.Callback callback) {
        return super.onWindowStartingActionMode(callback);
    }

    @Override // k4.e, android.view.Window.Callback
    public /* bridge */ /* synthetic */ ActionMode onWindowStartingActionMode(ActionMode.Callback callback, int i8) {
        return super.onWindowStartingActionMode(callback, i8);
    }

    @Override // z3.a, z3.e
    public void restoreOnViewsLoadCompleted() {
        super.restoreOnViewsLoadCompleted();
        onClockStyleChanged(this.curStyle);
    }

    @Override // z3.g
    public /* bridge */ /* synthetic */ void unbindP() {
        super.unbindP();
    }

    public BydDate updateTimeView() {
        x3.a.a(TAG, "updateClockTimeView", "---updateTimeView---");
        BydDate curDateAmpm = BydDateUtils.getCurDateAmpm(getContext().getApplicationContext());
        x3.a.a(TAG, "updateClockTimeView", ", updateTimeView--date = " + curDateAmpm);
        updateClockTimeView(this.curStyle);
        return curDateAmpm;
    }

    @Override // z3.g
    public AudioScreenPresenter newP() {
        return new AudioScreenPresenter();
    }
}
