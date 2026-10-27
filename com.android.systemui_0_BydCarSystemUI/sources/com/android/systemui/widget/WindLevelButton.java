package com.android.systemui.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import com.android.systemui.R;
import com.android.systemui.util.ViewSoundEffectUtil;
import com.bydux.api.common.tools.log.BydLogHelper;
import java.math.BigDecimal;

/* JADX INFO: loaded from: classes.dex */
public class WindLevelButton extends ImageView implements View.OnTouchListener {
    public static final float ALPHA_GRAY = 0.45f;
    public static final float ALPHA_NORMAL = 1.0f;
    public static final float ALPHA_PRESS = 0.65f;
    public static final int WIND_LEVEL_MAX = 7;
    private boolean isGray;
    private int mBackgroundHeight;
    private int mBackgroundWidth;
    private boolean mChanging;
    private int mCurrentWindLevel;
    private int mMin;
    private OnWindLevelChangeListener mOnWindLevelChangeListener;
    private int mPadding;
    private final int[] mWindLevelId;
    private int mWindPerlevelBgWidth;

    public interface OnWindLevelChangeListener {
        void onChanged(int i);

        void onTouchDown(int i);

        void onTouchUp(int i);
    }

    public WindLevelButton(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.mCurrentWindLevel = 0;
        this.mChanging = false;
        this.mWindPerlevelBgWidth = 0;
        this.mBackgroundWidth = 0;
        this.mBackgroundHeight = 0;
        this.mMin = 1;
        this.isGray = false;
        this.mWindLevelId = new int[]{R.mipmap.img_air_quick_control_of_air_volume_bg, R.mipmap.img_air_quick_control_of_air_volume_1, R.mipmap.img_air_quick_control_of_air_volume_2, R.mipmap.img_air_quick_control_of_air_volume_3, R.mipmap.img_air_quick_control_of_air_volume_4, R.mipmap.img_air_quick_control_of_air_volume_5, R.mipmap.img_air_quick_control_of_air_volume_6, R.mipmap.img_air_quick_control_of_air_volume_7};
        init(attrs);
    }

    public WindLevelButton(Context context) {
        this(context, null);
    }

    private void init(AttributeSet attrs) {
        getContext().obtainStyledAttributes(attrs, R.styleable.WindLevelButton);
        setOnTouchListener(this);
    }

    @Override // android.widget.ImageView, android.view.View
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        this.mBackgroundWidth = getMeasuredWidth();
        this.mBackgroundHeight = getMeasuredHeight();
        int i = this.mBackgroundWidth;
        int i2 = i / 100;
        this.mPadding = i2;
        this.mWindPerlevelBgWidth = (i - i2) / 7;
    }

    public void setMin(int min) {
        this.mMin = min;
    }

    @Override // android.view.View.OnTouchListener
    public boolean onTouch(View v, MotionEvent event) {
        if (isEnabled()) {
            int action = event.getAction();
            if (action == 0) {
                if (event.getX() >= 0.0f && event.getX() < this.mBackgroundWidth) {
                    BigDecimal scale = new BigDecimal((event.getX() - this.mPadding) / this.mWindPerlevelBgWidth).setScale(0, 0);
                    if (scale.intValue() >= this.mMin && this.mCurrentWindLevel != scale.intValue()) {
                        OnWindLevelChangeListener onWindLevelChangeListener = this.mOnWindLevelChangeListener;
                        if (onWindLevelChangeListener != null) {
                            onWindLevelChangeListener.onTouchDown(scale.intValue());
                        }
                        this.mCurrentWindLevel = scale.intValue();
                        setWindLevelBackgroundGray(false);
                    }
                }
                this.mChanging = true;
            } else if (action == 1) {
                OnWindLevelChangeListener onWindLevelChangeListener2 = this.mOnWindLevelChangeListener;
                if (onWindLevelChangeListener2 != null) {
                    onWindLevelChangeListener2.onTouchUp(this.mCurrentWindLevel);
                }
                this.mChanging = false;
            } else if (action == 2 && event.getX() >= 0.0f && event.getX() < this.mBackgroundWidth) {
                BigDecimal scale2 = new BigDecimal((event.getX() - this.mPadding) / this.mWindPerlevelBgWidth).setScale(0, 4);
                if (scale2.intValue() >= this.mMin && this.mCurrentWindLevel != scale2.intValue()) {
                    OnWindLevelChangeListener onWindLevelChangeListener3 = this.mOnWindLevelChangeListener;
                    if (onWindLevelChangeListener3 != null) {
                        onWindLevelChangeListener3.onChanged(scale2.intValue());
                    }
                    this.mCurrentWindLevel = scale2.intValue();
                    setWindLevelBackgroundGray(false);
                }
            }
        }
        return true;
    }

    public void setOnWindLevelViewChangeListener(OnWindLevelChangeListener onWindLevelChangeListener) {
        this.mOnWindLevelChangeListener = onWindLevelChangeListener;
    }

    public void setWindLevel(int bWindLevelValue) {
        BydLogHelper.logD("setWindLevel bWindLevelValue : " + bWindLevelValue);
        if (bWindLevelValue < 0 || bWindLevelValue > 7 || this.mChanging) {
            return;
        }
        this.mCurrentWindLevel = bWindLevelValue;
        setImageResource(this.mWindLevelId[bWindLevelValue]);
    }

    public void setWindLevelPlayWheelSoundEffect(int bWindLevelValue) {
        BydLogHelper.logD("setWindLevelPlayWheelSoundEffect bWindLevelValue : " + bWindLevelValue);
        if (bWindLevelValue < 0 || bWindLevelValue > 7 || this.mChanging) {
            return;
        }
        this.mCurrentWindLevel = bWindLevelValue;
        setImageResource(this.mWindLevelId[bWindLevelValue]);
        ViewSoundEffectUtil.playWheelSoundEffect(this);
    }

    public void setWindLevelBackgroundGray(boolean gray) {
        this.isGray = gray;
        if (gray) {
            setAlpha(0.45f);
            setImageResource(this.mWindLevelId[8]);
            return;
        }
        setAlpha(1.0f);
        int i = this.mCurrentWindLevel;
        if (i < 0 || i > 7) {
            return;
        }
        BydLogHelper.logD("setWindLevelBackgroundGray mCurrentWindLevel : " + this.mCurrentWindLevel);
        setImageResource(this.mWindLevelId[this.mCurrentWindLevel]);
    }
}
