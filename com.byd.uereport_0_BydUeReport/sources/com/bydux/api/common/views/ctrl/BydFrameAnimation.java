package com.bydux.api.common.views.ctrl;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.View;
import android.widget.TextView;
import com.bydux.api.R;
import com.bydux.api.common.views.ctrl.BydTimer;
import com.bydux.api.common.views.skin.loader.SkinManager;

/* JADX INFO: loaded from: classes2.dex */
@SuppressLint({"AppCompatCustomView"})
public class BydFrameAnimation extends TextView {
    private final String TAG;
    private Context mContext;
    private Drawable[] mFrameDrawable;
    private boolean mFrameManual;
    private String mFramePrefix;
    private int mFrameProgress;
    private boolean mFrameRepeat;
    private int mFrameSpeed;
    private int mFrameTotal;
    private boolean mIsPlaying;
    private BydFrameAnimationListener mListener;
    private BydTimer mTimer;

    public interface BydFrameAnimationListener {
        void onFrameAnimationAround(View view);

        void onFrameAnimationStart(View view);

        void onFrameAnimationStop(View view);
    }

    public BydFrameAnimation(Context context) {
        super(context);
        this.TAG = getClass().getSimpleName();
        this.mFramePrefix = null;
        this.mFrameTotal = 0;
        this.mFrameRepeat = true;
        this.mFrameManual = false;
        this.mFrameDrawable = null;
        this.mFrameSpeed = 40;
        this.mFrameProgress = 0;
        this.mIsPlaying = true;
        initView(context);
    }

    public static /* synthetic */ int access$008(BydFrameAnimation bydFrameAnimation) {
        int i8 = bydFrameAnimation.mFrameProgress;
        bydFrameAnimation.mFrameProgress = i8 + 1;
        return i8;
    }

    private void initView(Context context) {
        this.mContext = context;
        BydTimer bydTimer = new BydTimer(context);
        this.mTimer = bydTimer;
        bydTimer.setBydTimerListener(new BydTimer.BydTimerListener() { // from class: com.bydux.api.common.views.ctrl.BydFrameAnimation.1
            @Override // com.bydux.api.common.views.ctrl.BydTimer.BydTimerListener
            public void onTimeUp(View view) {
                BydFrameAnimation.access$008(BydFrameAnimation.this);
                if (BydFrameAnimation.this.mFrameProgress >= BydFrameAnimation.this.mFrameTotal) {
                    if (!BydFrameAnimation.this.mFrameRepeat) {
                        BydFrameAnimation.this.stopFrameAnimation();
                        return;
                    } else {
                        BydFrameAnimation.this.mFrameProgress = 0;
                        if (BydFrameAnimation.this.mListener != null) {
                            BydFrameAnimation.this.mListener.onFrameAnimationAround(BydFrameAnimation.this);
                        }
                    }
                }
                BydFrameAnimation.this.updateShow();
            }
        });
    }

    private void loadAttrs(AttributeSet attributeSet) {
        if (attributeSet != null) {
            TypedArray typedArrayObtainStyledAttributes = this.mContext.obtainStyledAttributes(attributeSet, R.styleable.BydFrameAnimation);
            this.mFramePrefix = typedArrayObtainStyledAttributes.getString(R.styleable.BydFrameAnimation_byd_frame_prefix);
            this.mFrameTotal = typedArrayObtainStyledAttributes.getInteger(R.styleable.BydFrameAnimation_byd_frame_total, this.mFrameTotal);
            this.mFrameSpeed = typedArrayObtainStyledAttributes.getInteger(R.styleable.BydFrameAnimation_byd_frame_speed, this.mFrameSpeed);
            this.mFrameRepeat = typedArrayObtainStyledAttributes.getBoolean(R.styleable.BydFrameAnimation_byd_frame_repeat, this.mFrameRepeat);
            this.mFrameManual = typedArrayObtainStyledAttributes.getBoolean(R.styleable.BydFrameAnimation_byd_frame_manual, this.mFrameManual);
            typedArrayObtainStyledAttributes.recycle();
            setFramePrefix(this.mFramePrefix, this.mFrameTotal);
            setFrameSpeed(this.mFrameSpeed);
            setFrameRepeat(this.mFrameRepeat);
            if (this.mFrameManual) {
                this.mIsPlaying = false;
            } else {
                this.mIsPlaying = true;
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void updateShow() {
        int i8;
        Drawable[] drawableArr = this.mFrameDrawable;
        if (drawableArr == null || (i8 = this.mFrameProgress) >= drawableArr.length) {
            return;
        }
        setBackground(drawableArr[i8]);
    }

    public boolean isFrameAnimationWorking() {
        return this.mIsPlaying;
    }

    @Override // android.widget.TextView, android.view.View
    public void onAttachedToWindow() {
        BydTimer bydTimer;
        super.onAttachedToWindow();
        updateShow();
        if (!this.mIsPlaying || (bydTimer = this.mTimer) == null) {
            return;
        }
        bydTimer.startTimer(this.mFrameSpeed);
    }

    @Override // android.view.View
    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        BydTimer bydTimer = this.mTimer;
        if (bydTimer != null) {
            bydTimer.stopTimer();
        }
    }

    public void restartFrameAnimation() {
        this.mFrameProgress = 0;
        startFrameAnimation();
    }

    public void setBydFrameAnimationListener(BydFrameAnimationListener bydFrameAnimationListener) {
        this.mListener = bydFrameAnimationListener;
    }

    public void setFramePrefix(String str, int i8) {
        this.mFramePrefix = str;
        this.mFrameTotal = i8;
        if (str == null || i8 <= 0) {
            return;
        }
        this.mFrameDrawable = new Drawable[i8];
        for (int i9 = 0; i9 < this.mFrameTotal; i9++) {
            this.mFrameDrawable[i9] = SkinManager.getInstance().getDrawable(this.mFramePrefix + i9);
        }
    }

    public void setFrameRepeat(boolean z7) {
        this.mFrameRepeat = z7;
    }

    public void setFrameSpeed(int i8) {
        BydTimer bydTimer;
        if (i8 > 0 && this.mFrameSpeed != i8) {
            this.mFrameSpeed = i8;
            if (!this.mIsPlaying || (bydTimer = this.mTimer) == null) {
                return;
            }
            bydTimer.startTimer(i8);
        }
    }

    public void startFrameAnimation() {
        if (this.mFramePrefix == null || this.mFrameTotal <= 1) {
            return;
        }
        this.mIsPlaying = true;
        BydTimer bydTimer = this.mTimer;
        if (bydTimer != null) {
            bydTimer.startTimer(this.mFrameSpeed);
        }
        BydFrameAnimationListener bydFrameAnimationListener = this.mListener;
        if (bydFrameAnimationListener != null) {
            bydFrameAnimationListener.onFrameAnimationStart(this);
        }
    }

    public void stopFrameAnimation() {
        this.mIsPlaying = false;
        BydTimer bydTimer = this.mTimer;
        if (bydTimer != null) {
            bydTimer.stopTimer();
        }
        BydFrameAnimationListener bydFrameAnimationListener = this.mListener;
        if (bydFrameAnimationListener != null) {
            bydFrameAnimationListener.onFrameAnimationStop(this);
        }
    }

    public BydFrameAnimation(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        this.TAG = getClass().getSimpleName();
        this.mFramePrefix = null;
        this.mFrameTotal = 0;
        this.mFrameRepeat = true;
        this.mFrameManual = false;
        this.mFrameDrawable = null;
        this.mFrameSpeed = 40;
        this.mFrameProgress = 0;
        this.mIsPlaying = true;
        initView(context);
        loadAttrs(attributeSet);
    }
}
