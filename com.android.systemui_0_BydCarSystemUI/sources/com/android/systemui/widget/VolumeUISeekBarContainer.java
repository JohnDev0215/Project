package com.android.systemui.widget;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import android.widget.TextView;
import com.android.systemui.R;
import com.android.systemui.util.StyleUtil;
import com.bydux.api.common.tools.log.BydLogHelper;

/* JADX INFO: loaded from: classes.dex */
public class VolumeUISeekBarContainer extends RelativeLayout {
    private final String TAG;
    protected final int UPDATE_PROGRESS;
    private int imgRes;
    private int[] imgResAll;
    private int imgResMute;
    private ProgressChangedCallback mCallback;
    private CustomVerticalSeekBar mCustomVerticalSeekBar;
    private Handler mHandler;
    private ImageView mSeekBarIcon;
    private TextView mSeekBarName;
    private TextView mSeekBarProgress;
    private int mUsage;
    private int max;
    private int min;
    private int value_0;
    private int value_100;
    private int value_25;
    private int value_50;
    private int value_75;
    protected final int what_dismiss;

    public interface ProgressChangedCallback {
        default void onIconClick(int usage) {
        }

        default void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser, int usage) {
        }

        default void onStartTrackingTouch(SeekBar seekBar) {
        }

        default void onStopTrackingTouch(SeekBar seekBar) {
        }
    }

    public VolumeUISeekBarContainer(Context context) {
        this(context, null);
    }

    public VolumeUISeekBarContainer(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public VolumeUISeekBarContainer(Context context, AttributeSet attrs, int defStyleAttr) {
        this(context, attrs, defStyleAttr, 0);
    }

    public VolumeUISeekBarContainer(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        this.TAG = getClass().getSimpleName();
        this.what_dismiss = 1;
        this.UPDATE_PROGRESS = 2;
        init(context);
    }

    private void init(Context context) {
        inflate(context, StyleUtil.getResLayout(R.layout.sysui_qs_volumeui_seekbar, "sysui_qs_volumeui_seekbar"), this);
        this.mHandler = new H(Looper.getMainLooper());
        this.mSeekBarName = (TextView) findViewById(R.id.tv_sysui_qs_name);
        this.mSeekBarIcon = (ImageView) findViewById(R.id.iv_sysui_qs_icon);
        this.mSeekBarProgress = (TextView) findViewById(R.id.tv_sysui_qs_progress);
        CustomVerticalSeekBar customVerticalSeekBar = (CustomVerticalSeekBar) findViewById(R.id.sysui_qs_custom_vertical_seekbar);
        this.mCustomVerticalSeekBar = customVerticalSeekBar;
        customVerticalSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() { // from class: com.android.systemui.widget.VolumeUISeekBarContainer.1
            @Override // android.widget.SeekBar.OnSeekBarChangeListener
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                Log.d(VolumeUISeekBarContainer.this.TAG, "onProgressChanged progress = " + progress + " , fromUser = " + fromUser);
                VolumeUISeekBarContainer.this.mSeekBarProgress.setText(String.valueOf(progress));
                if (fromUser) {
                    ProgressChangedCallback unused = VolumeUISeekBarContainer.this.mCallback;
                    if (VolumeUISeekBarContainer.this.imgResAll != null) {
                        VolumeUISeekBarContainer.this.setImageOnProgress(progress);
                        return;
                    }
                    if (VolumeUISeekBarContainer.this.imgResMute != 0) {
                        if (progress == VolumeUISeekBarContainer.this.mCustomVerticalSeekBar.getMin()) {
                            VolumeUISeekBarContainer volumeUISeekBarContainer = VolumeUISeekBarContainer.this;
                            volumeUISeekBarContainer.setBydThemeImg(volumeUISeekBarContainer.imgResMute);
                        } else {
                            VolumeUISeekBarContainer volumeUISeekBarContainer2 = VolumeUISeekBarContainer.this;
                            volumeUISeekBarContainer2.setBydThemeImg(volumeUISeekBarContainer2.imgRes);
                        }
                    }
                }
            }

            @Override // android.widget.SeekBar.OnSeekBarChangeListener
            public void onStartTrackingTouch(SeekBar seekBar) {
                Log.d(VolumeUISeekBarContainer.this.TAG, "onStartTrackingTouch");
                VolumeUISeekBarContainer.this.mHandler.removeMessages(1);
                VolumeUISeekBarContainer.this.mSeekBarProgress.setVisibility(0);
                if (VolumeUISeekBarContainer.this.mCallback != null) {
                    VolumeUISeekBarContainer.this.mCallback.onStartTrackingTouch(seekBar);
                }
                VolumeUISeekBarContainer.this.mHandler.removeMessages(2);
                VolumeUISeekBarContainer.this.mHandler.sendEmptyMessage(2);
            }

            @Override // android.widget.SeekBar.OnSeekBarChangeListener
            public void onStopTrackingTouch(SeekBar seekBar) {
                Log.d(VolumeUISeekBarContainer.this.TAG, "onStopTrackingTouch");
                if (VolumeUISeekBarContainer.this.mHandler != null) {
                    VolumeUISeekBarContainer.this.mHandler.removeMessages(2);
                }
                if (VolumeUISeekBarContainer.this.mCallback != null && VolumeUISeekBarContainer.this.mCustomVerticalSeekBar != null) {
                    VolumeUISeekBarContainer.this.mCallback.onProgressChanged(VolumeUISeekBarContainer.this.mCustomVerticalSeekBar, VolumeUISeekBarContainer.this.mCustomVerticalSeekBar.getProgress(), true, VolumeUISeekBarContainer.this.mUsage);
                }
                VolumeUISeekBarContainer.this.mHandler.sendEmptyMessageDelayed(1, 3000L);
                if (VolumeUISeekBarContainer.this.mCallback != null) {
                    VolumeUISeekBarContainer.this.mCallback.onStopTrackingTouch(seekBar);
                }
            }
        });
        this.mSeekBarProgress.setVisibility(8);
        this.mSeekBarIcon.setOnClickListener(new View.OnClickListener() { // from class: com.android.systemui.widget.VolumeUISeekBarContainer.2
            @Override // android.view.View.OnClickListener
            public void onClick(View v) {
                if (VolumeUISeekBarContainer.this.mCallback != null) {
                    VolumeUISeekBarContainer.this.mCallback.onIconClick(VolumeUISeekBarContainer.this.mUsage);
                }
            }
        });
        setBydThemeTv();
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        Handler handler = this.mHandler;
        if (handler != null) {
            handler.removeMessages(2);
        }
    }

    public final void setBydThemeTv() {
        TextView textView = this.mSeekBarName;
        if (textView != null) {
            textView.setTextColor(getContext().getResources().getColor(StyleUtil.getResColor(R.color.sys_volume_dialog_title_color, "sys_volume_dialog_title_color")));
        }
        TextView textView2 = this.mSeekBarProgress;
        if (textView2 != null) {
            textView2.setTextColor(getContext().getResources().getColor(StyleUtil.getResColor(R.color.sys_volume_dialog_text_color, "sys_volume_dialog_text_color")));
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setBydThemeImg(int resId) {
        ImageView imageView = this.mSeekBarIcon;
        if (imageView != null) {
            imageView.setImageResource(resId);
        }
    }

    public void initSeekBar(int min, int max, int name, int[] imgResAll, int usage) {
        this.mUsage = usage;
        this.min = min;
        this.max = max;
        this.imgResAll = imgResAll;
        this.imgRes = imgResAll[1];
        this.imgResMute = imgResAll[0];
        initRatioValue();
        this.mCustomVerticalSeekBar.setMin(min);
        this.mCustomVerticalSeekBar.setMax(max);
        this.mSeekBarName.setText(name);
        setBydThemeImg(this.imgRes);
    }

    private void initRatioValue() {
        this.value_0 = this.min;
        int i = this.max;
        this.value_25 = (int) (((double) i) * 0.25d);
        this.value_50 = (int) (((double) i) * 0.5d);
        this.value_75 = (int) (((double) i) * 0.75d);
        this.value_100 = i;
    }

    public void setSeekBarProgress(int progress) {
        try {
            this.mCustomVerticalSeekBar.setProgress(progress);
            if (this.imgResAll != null) {
                setImageOnProgress(progress);
            } else {
                setBydThemeImg(this.imgRes);
            }
            this.mHandler.removeMessages(1);
            this.mHandler.sendEmptyMessageDelayed(1, 3000L);
            this.mSeekBarProgress.setText(String.valueOf(progress));
            this.mSeekBarProgress.setVisibility(0);
        } catch (Exception e) {
            BydLogHelper.logE(e.toString());
            e.printStackTrace();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void setImageOnProgress(int progress) {
        if (progress >= this.value_100 || progress > this.value_75) {
            this.imgRes = this.imgResAll[4];
        } else if (progress > this.value_50) {
            this.imgRes = this.imgResAll[3];
        } else if (progress > this.value_25) {
            this.imgRes = this.imgResAll[2];
        } else if (progress > this.value_0) {
            this.imgRes = this.imgResAll[1];
        } else {
            this.imgRes = this.imgResAll[0];
        }
        setBydThemeImg(this.imgRes);
    }

    public void setSeekBarProgressMute(int progress) {
        this.mCustomVerticalSeekBar.setProgress(progress);
        int i = this.imgResMute;
        if (i != 0) {
            setBydThemeImg(i);
        }
    }

    public int getSeekBarProgress() {
        return this.mCustomVerticalSeekBar.getProgress();
    }

    @Override // android.view.ViewGroup
    public boolean onInterceptTouchEvent(MotionEvent e) {
        getParent().requestDisallowInterceptTouchEvent(true);
        return super.onInterceptTouchEvent(e);
    }

    public void setProgressCallback(ProgressChangedCallback mCallback) {
        this.mCallback = mCallback;
    }

    private class H extends Handler {
        private int mLastProgress;

        public H(Looper mainLooper) {
            super(mainLooper);
            this.mLastProgress = -1;
        }

        @Override // android.os.Handler
        public void handleMessage(Message msg) {
            int progress;
            super.handleMessage(msg);
            int i = msg.what;
            BydLogHelper.logV("what:" + i);
            if (i == 1) {
                VolumeUISeekBarContainer.this.mSeekBarProgress.setVisibility(8);
                return;
            }
            if (i != 2) {
                return;
            }
            if (VolumeUISeekBarContainer.this.mCallback != null && VolumeUISeekBarContainer.this.mCustomVerticalSeekBar != null && this.mLastProgress != (progress = VolumeUISeekBarContainer.this.mCustomVerticalSeekBar.getProgress())) {
                BydLogHelper.logD("onProgressChanged " + progress);
                this.mLastProgress = progress;
                VolumeUISeekBarContainer.this.mCallback.onProgressChanged(VolumeUISeekBarContainer.this.mCustomVerticalSeekBar, VolumeUISeekBarContainer.this.mCustomVerticalSeekBar.getProgress(), true, VolumeUISeekBarContainer.this.mUsage);
            }
            VolumeUISeekBarContainer.this.mHandler.sendEmptyMessageDelayed(2, 100L);
        }
    }
}
