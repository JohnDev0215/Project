package com.amazon.mp3.animation;

import android.content.Context;
import android.os.Handler;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.AnimationUtils;
import android.view.animation.ScaleAnimation;
import com.amazon.mp3.automotiveOS.R;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/* JADX INFO: loaded from: classes3.dex */
public class StationLoadingAnimation {
    private static final float[] RIPPLE_SCALE_VALUES = {0.85f, 0.52f, 0.32f, 0.12f};
    private Handler mHandler;
    private final View mParent;
    private View[] mRipples;
    private View mStationDot;
    private View mWings;
    private List<Listener> mListeners = new ArrayList();
    private boolean mIsFirstBeat = true;

    public interface Listener {
        void onAnimationEnd();
    }

    public StationLoadingAnimation(View parent) {
        this.mParent = parent;
        if (parent != null) {
            this.mHandler = new Handler();
            this.mRipples = new View[]{parent.findViewById(R.id.ripple1), parent.findViewById(R.id.ripple2), parent.findViewById(R.id.ripple3), parent.findViewById(R.id.ripple4)};
            this.mStationDot = parent.findViewById(R.id.station_dot);
            this.mWings = parent.findViewById(R.id.station_wings);
            View view = this.mStationDot;
            if (view == null) {
                return;
            }
            view.setAlpha(0.8f);
            this.mWings.setAlpha(0.8f);
        }
    }

    public void addListener(Listener listener) {
        this.mListeners.add(listener);
    }

    public void removeListener(Listener listener) {
        this.mListeners.remove(listener);
    }

    public void reset() {
        this.mIsFirstBeat = true;
    }

    public void startHeartBeat(final Context context) {
        if (context == null || this.mParent == null || this.mStationDot == null) {
            return;
        }
        if (this.mIsFirstBeat) {
            Animation animationLoadAnimation = AnimationUtils.loadAnimation(context, R.anim.station_dot_in);
            this.mStationDot.setVisibility(0);
            this.mStationDot.startAnimation(animationLoadAnimation);
            this.mHandler.postDelayed(new Runnable() { // from class: com.amazon.mp3.animation.StationLoadingAnimation.1
                @Override // java.lang.Runnable
                public void run() {
                    StationLoadingAnimation.this.setupHeartbeat(context);
                }
            }, ((long) context.getResources().getInteger(R.integer.station_dot_in_duration)) - 100);
            return;
        }
        setupHeartbeat(context);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setupHeartbeat(final Context context) {
        if (context == null || this.mParent == null || this.mStationDot == null) {
            return;
        }
        animateRipple(1, context).setAnimationListener(new AnimationAdapter() { // from class: com.amazon.mp3.animation.StationLoadingAnimation.2
            @Override // com.amazon.mp3.animation.AnimationAdapter, android.view.animation.Animation.AnimationListener
            public void onAnimationEnd(final Animation animation) {
                for (View view : StationLoadingAnimation.this.mRipples) {
                    view.setVisibility(8);
                }
                Iterator it = StationLoadingAnimation.this.mListeners.iterator();
                while (it.hasNext()) {
                    ((Listener) it.next()).onAnimationEnd();
                }
            }
        });
        this.mHandler.postDelayed(new Runnable() { // from class: com.amazon.mp3.animation.StationLoadingAnimation.3
            @Override // java.lang.Runnable
            public void run() {
                if (StationLoadingAnimation.this.mIsFirstBeat) {
                    StationLoadingAnimation.this.mIsFirstBeat = false;
                    Animation animationLoadAnimation = AnimationUtils.loadAnimation(context, R.anim.expand_and_settle_in);
                    animationLoadAnimation.setStartOffset(300L);
                    StationLoadingAnimation.this.mWings.setVisibility(0);
                    StationLoadingAnimation.this.mWings.startAnimation(animationLoadAnimation);
                } else {
                    Animation animationLoadAnimation2 = AnimationUtils.loadAnimation(context, R.anim.heartbeat);
                    StationLoadingAnimation.this.mWings.setVisibility(0);
                    StationLoadingAnimation.this.mWings.startAnimation(animationLoadAnimation2);
                    StationLoadingAnimation.this.mStationDot.startAnimation(AnimationUtils.loadAnimation(context, R.anim.heartbeat));
                }
                StationLoadingAnimation.this.animateRipple(0, context);
                StationLoadingAnimation.this.animateRipple(2, context);
                StationLoadingAnimation.this.animateRipple(3, context);
            }
        }, 100L);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public AnimationSet animateRipple(int index, Context context) {
        if (context == null || this.mParent == null || this.mStationDot == null) {
            return null;
        }
        View view = this.mRipples[index];
        float f = RIPPLE_SCALE_VALUES[index];
        view.setVisibility(0);
        AnimationSet animationSet = (AnimationSet) AnimationUtils.loadAnimation(context, R.anim.scale_up_fade_out);
        ScaleAnimation scaleAnimation = new ScaleAnimation(0.0f, f, 0.0f, f, 1, 0.5f, 1, 0.5f);
        long integer = context.getResources().getInteger(R.integer.station_ripple_duration);
        if (index != 1) {
            integer -= 100;
        }
        animationSet.setDuration(integer);
        animationSet.addAnimation(scaleAnimation);
        view.startAnimation(animationSet);
        return animationSet;
    }

    public boolean canShowStationLoadingAnimation(Context context) {
        return (context == null || this.mParent == null || this.mStationDot == null) ? false : true;
    }

    public void finish(Context context) {
        if (context == null || this.mParent == null || this.mStationDot == null) {
            return;
        }
        Animation animationLoadAnimation = AnimationUtils.loadAnimation(context, R.anim.scale_out);
        animationLoadAnimation.setAnimationListener(new AnimationAdapter() { // from class: com.amazon.mp3.animation.StationLoadingAnimation.4
            @Override // com.amazon.mp3.animation.AnimationAdapter, android.view.animation.Animation.AnimationListener
            public void onAnimationEnd(final Animation animation) {
                StationLoadingAnimation.this.mStationDot.setVisibility(8);
                StationLoadingAnimation.this.mWings.setVisibility(8);
            }
        });
        this.mStationDot.startAnimation(animationLoadAnimation);
        this.mWings.startAnimation(animationLoadAnimation);
    }
}
