package com.byd.music.view;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.os.SystemClock;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;
import com.byd.music.OnLineMusic;
import com.byd.music.R;
import com.byd.music.beans.RadioEpgDBBean;
import com.byd.music.service.RadioServiceWrapper;

/* JADX INFO: loaded from: classes5.dex */
public class AlarmView {
    private View alarmView;
    private View epgCountView;
    private boolean isShowing;
    private Context mContext;
    private View trafficView;
    private WindowManager wmAlarm;
    private WindowManager wmEPG;
    private WindowManager wmTraffic;

    static /* synthetic */ void lambda$initAlarm$1(View view) {
    }

    static /* synthetic */ void lambda$initTraffic$3(View view) {
    }

    public AlarmView(Context context) {
        this.mContext = context;
    }

    public void initAlarm() {
        this.wmAlarm = (WindowManager) this.mContext.getSystemService(Context.WINDOW_SERVICE);
        View viewInflate = LayoutInflater.from(this.mContext).inflate(R.layout.gloal_window_layout, (ViewGroup) null, false);
        this.alarmView = viewInflate;
        this.wmAlarm.addView(viewInflate, getLayoutParams());
        this.alarmView.setOnClickListener(new View.OnClickListener() { // from class: com.byd.music.view.-$$Lambda$AlarmView$D1iBhWZnb_5vlt6_ULFnU9jowfA
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                this.f$0.lambda$initAlarm$0$AlarmView(view);
            }
        });
        this.alarmView.findViewById(R.id.global_window_child).setOnClickListener(new View.OnClickListener() { // from class: com.byd.music.view.-$$Lambda$AlarmView$a1HMiZmjgxHi94umrrgyBXeOZrM
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                AlarmView.lambda$initAlarm$1(view);
            }
        });
    }

    public /* synthetic */ void lambda$initAlarm$0$AlarmView(View view) {
        hideGlobalWindow();
    }

    public void initTraffic() {
        this.wmTraffic = (WindowManager) this.mContext.getSystemService(Context.WINDOW_SERVICE);
        View viewInflate = LayoutInflater.from(this.mContext).inflate(R.layout.gloal_window_traffic_layout, (ViewGroup) null, false);
        this.trafficView = viewInflate;
        this.wmTraffic.addView(viewInflate, getLayoutParams());
        this.trafficView.setOnClickListener(new View.OnClickListener() { // from class: com.byd.music.view.-$$Lambda$AlarmView$C-JSaJ8_xFBGJ0rSPEL-pQJlXo4
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                this.f$0.lambda$initTraffic$2$AlarmView(view);
            }
        });
        this.trafficView.findViewById(R.id.global_traffic_window_child).setOnClickListener(new View.OnClickListener() { // from class: com.byd.music.view.-$$Lambda$AlarmView$B4ADSqLcyy4YgLa3ZUbEuP-LTY0
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                AlarmView.lambda$initTraffic$3(view);
            }
        });
        this.trafficView.findViewById(R.id.traffic_btn).setOnClickListener(new View.OnClickListener() { // from class: com.byd.music.view.-$$Lambda$AlarmView$PYd6HaOSgAkKGxIc3ZS-IpR1LCk
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                this.f$0.lambda$initTraffic$4$AlarmView(view);
            }
        });
    }

    public /* synthetic */ void lambda$initTraffic$2$AlarmView(View view) {
        hideGlobalWindow();
    }

    public /* synthetic */ void lambda$initTraffic$4$AlarmView(View view) {
        hideGlobalWindow();
    }

    public void initEPG() {
        this.wmEPG = (WindowManager) this.mContext.getSystemService(Context.WINDOW_SERVICE);
        View viewInflate = LayoutInflater.from(this.mContext).inflate(R.layout.gloal_window_dab_epg, (ViewGroup) null, false);
        this.epgCountView = viewInflate;
        this.wmEPG.addView(viewInflate, getLayoutParams());
        this.epgCountView.setOnClickListener(new View.OnClickListener() { // from class: com.byd.music.view.-$$Lambda$AlarmView$CxLhzhZED9TaJJ1b1luH4lkyhlY
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                this.f$0.lambda$initEPG$7$AlarmView(view);
            }
        });
        this.epgCountView.findViewById(R.id.switch_now).setOnClickListener(new View.OnClickListener() { // from class: com.byd.music.view.-$$Lambda$AlarmView$YvUGbbudZBo8kuxNoJ73YYiQkHw
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                this.f$0.lambda$initEPG$9$AlarmView(view);
            }
        });
        this.epgCountView.findViewById(R.id.cancel).setOnClickListener(new View.OnClickListener() { // from class: com.byd.music.view.-$$Lambda$AlarmView$TmMs4rDR_mLDuJiuBbGxb9hT5WU
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                this.f$0.lambda$initEPG$10$AlarmView(view);
            }
        });
    }

    public /* synthetic */ void lambda$initEPG$7$AlarmView(View view) {
        hideGlobalWindow();
        new Handler(Looper.getMainLooper()).postAtTime(new Runnable() { // from class: com.byd.music.view.-$$Lambda$AlarmView$yflW12kqJRFbaaS80aHMvpPcba8
            @Override // java.lang.Runnable
            public final void run() {
                AlarmView.lambda$initEPG$6();
            }
        }, ((RadioServiceWrapper.getInstance().radioSubscribeEpgList.get(0).getEpgStartTime() * 1000) - System.currentTimeMillis()) + SystemClock.uptimeMillis());
    }

    static /* synthetic */ void lambda$initEPG$6() {
        OnLineMusic.setMusicEngine(9);
        RadioServiceWrapper.getInstance().setRadioPlayState(true);
        new Handler(Looper.getMainLooper()).postDelayed(new Runnable() { // from class: com.byd.music.view.-$$Lambda$AlarmView$ck3Pybp8Fo5Rhy58pp5oDVwT9J8
            @Override // java.lang.Runnable
            public final void run() {
                RadioServiceWrapper.getInstance().restartEpgCountDown();
            }
        }, 200L);
    }

    public /* synthetic */ void lambda$initEPG$9$AlarmView(View view) {
        hideGlobalWindow();
        OnLineMusic.setMusicEngine(9);
        RadioServiceWrapper.getInstance().setRadioPlayState(true);
        new Handler(Looper.getMainLooper()).postDelayed(new Runnable() { // from class: com.byd.music.view.-$$Lambda$AlarmView$QvchRsH8XLKXrLuyhn4VgMQiRwk
            @Override // java.lang.Runnable
            public final void run() {
                RadioServiceWrapper.getInstance().restartEpgCountDown();
            }
        }, 200L);
    }

    public /* synthetic */ void lambda$initEPG$10$AlarmView(View view) {
        hideGlobalWindow();
    }

    private WindowManager.LayoutParams getLayoutParams() {
        return new WindowManager.LayoutParams(-1, -1, 2038, 327944, -3);
    }

    public void showGlobalWindow() {
        View view = this.alarmView;
        if (view != null) {
            view.setVisibility(0);
            this.isShowing = true;
        }
        View view2 = this.trafficView;
        if (view2 != null) {
            view2.setVisibility(0);
            this.isShowing = true;
        }
        if (this.epgCountView != null) {
            if (RadioServiceWrapper.getInstance().radioSubscribeEpgList.size() == 0 || RadioServiceWrapper.getInstance().radioSubscribeEpgList.get(0).getDabSid() == RadioServiceWrapper.getInstance().mCurrentRadio.getValue().getDAB_SID()) {
                hideGlobalWindow();
                return;
            }
            RadioEpgDBBean radioEpgDBBean = RadioServiceWrapper.getInstance().radioSubscribeEpgList.get(0);
            this.epgCountView.setVisibility(0);
            ((TextView) this.epgCountView.findViewById(R.id.global_dab_count)).setText(String.format(this.mContext.getResources().getString(R.string.hint_epg_switch), radioEpgDBBean.getDesc()));
            new Handler(Looper.getMainLooper()).postDelayed(new AnonymousClass1(radioEpgDBBean), 300L);
            this.isShowing = true;
        }
    }

    /* JADX INFO: renamed from: com.byd.music.view.AlarmView$1, reason: invalid class name */
    class AnonymousClass1 implements Runnable {
        final /* synthetic */ RadioEpgDBBean val$firstRadioEpgDBBean;

        AnonymousClass1(RadioEpgDBBean radioEpgDBBean) {
            this.val$firstRadioEpgDBBean = radioEpgDBBean;
        }

        @Override // java.lang.Runnable
        public void run() {
            if (AlarmView.this.epgCountView != null && AlarmView.this.epgCountView.getVisibility() == 0 && this.val$firstRadioEpgDBBean.getEpgStartTime() * 1000 > System.currentTimeMillis()) {
                ((Button) AlarmView.this.epgCountView.findViewById(R.id.switch_now)).setText(String.format(AlarmView.this.mContext.getResources().getString(R.string.switch_now), Long.valueOf(((this.val$firstRadioEpgDBBean.getEpgStartTime() * 1000) - System.currentTimeMillis()) / 1000)));
                new Handler(Looper.getMainLooper()).postDelayed(new Runnable() { // from class: com.byd.music.view.-$$Lambda$FvR6zmwT-mtP409QRQQFS9iS-Zo
                    @Override // java.lang.Runnable
                    public final void run() {
                        this.f$0.run();
                    }
                }, 300L);
            } else if (this.val$firstRadioEpgDBBean.getEpgStartTime() * 1000 <= System.currentTimeMillis()) {
                AlarmView.this.hideGlobalWindow();
                OnLineMusic.setMusicEngine(9);
                RadioServiceWrapper.getInstance().setRadioPlayState(true);
                new Handler(Looper.getMainLooper()).postDelayed(new Runnable() { // from class: com.byd.music.view.-$$Lambda$AlarmView$1$XGQweXvN69hHzEtXHn9hndRTnuk
                    @Override // java.lang.Runnable
                    public final void run() {
                        RadioServiceWrapper.getInstance().restartEpgCountDown();
                    }
                }, 200L);
            }
        }
    }

    public void hideGlobalWindow() {
        View view = this.alarmView;
        if (view != null) {
            view.setVisibility(8);
            this.isShowing = false;
        }
        View view2 = this.trafficView;
        if (view2 != null) {
            view2.setVisibility(8);
            this.isShowing = false;
        }
        View view3 = this.epgCountView;
        if (view3 != null) {
            view3.setVisibility(8);
            this.isShowing = false;
        }
    }

    public void updateGlobalWindow(String str) {
        View view = this.alarmView;
        if (view != null) {
            ((TextView) view.findViewById(R.id.textView_glb)).setText(str);
        }
    }

    public boolean getShowing() {
        return this.isShowing;
    }
}
