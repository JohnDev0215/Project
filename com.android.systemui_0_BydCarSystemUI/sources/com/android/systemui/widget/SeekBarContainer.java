package com.android.systemui.widget;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import android.widget.TextView;
import com.android.systemui.R;
import com.android.systemui.util.StyleUtil;
import com.bydux.api.common.tools.log.BydLogHelper;

/* JADX INFO: loaded from: classes.dex */
public class SeekBarContainer extends RelativeLayout {
    private final String TAG;
    protected final int UPDATE_PROGRESS;
    protected boolean hasMessageUpdateProgress;
    private int imgRes;
    private int[] imgResAll;
    private int imgResMute;
    private ProgressChangedCallback mCallback;
    private CustomVerticalSeekBar mCustomVerticalSeekBar;
    private Handler mHandler;
    private ImageView mSeekBarIcon;
    private TextView mSeekBarName;
    private TextView mSeekBarProgress;
    private int max;
    private int min;
    private int value_0;
    private int value_100;
    private int value_25;
    private int value_50;
    private int value_75;
    protected final int what_dismiss;

    public interface ProgressChangedCallback {
        default void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
        }

        default void onStartTrackingTouch(SeekBar seekBar) {
        }

        default void onStopTrackingTouch(SeekBar seekBar) {
        }
    }

    public SeekBarContainer(Context context) {
        this(context, null);
    }

    public SeekBarContainer(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public SeekBarContainer(Context context, AttributeSet attrs, int defStyleAttr) {
        this(context, attrs, defStyleAttr, 0);
    }

    public SeekBarContainer(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        this.TAG = getClass().getSimpleName();
        this.what_dismiss = 1;
        this.UPDATE_PROGRESS = 2;
        this.hasMessageUpdateProgress = false;
        init(context);
    }

    private void init(Context context) {
        inflate(context, StyleUtil.getResLayout(R.layout.sysui_qs_panel_seekbar, "sysui_qs_panel_seekbar"), this);
        this.mHandler = new H(Looper.getMainLooper());
        this.mSeekBarName = (TextView) findViewById(R.id.tv_sysui_qs_name);
        this.mSeekBarIcon = (ImageView) findViewById(R.id.iv_sysui_qs_icon);
        this.mSeekBarProgress = (TextView) findViewById(R.id.tv_sysui_qs_progress);
        CustomVerticalSeekBar customVerticalSeekBar = (CustomVerticalSeekBar) findViewById(R.id.sysui_qs_custom_vertical_seekbar);
        this.mCustomVerticalSeekBar = customVerticalSeekBar;
        customVerticalSeekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() { // from class: com.android.systemui.widget.SeekBarContainer.1
            @Override // android.widget.SeekBar.OnSeekBarChangeListener
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                BydLogHelper.logW(SeekBarContainer.this.TAG, "onProgressChanged progress = " + progress + " , fromUser = " + fromUser);
                if (SeekBarContainer.this.mSeekBarName.getText().toString().equals(SeekBarContainer.this.getResources().getString(R.string.dashboard))) {
                    SeekBarContainer.this.mSeekBarProgress.setText(String.valueOf((progress - 1) * 10));
                } else if (!SeekBarContainer.this.mSeekBarName.getText().toString().equals(SeekBarContainer.this.getResources().getString(R.string.center_control))) {
                    SeekBarContainer.this.mSeekBarProgress.setText(String.valueOf(progress));
                }
                if (fromUser) {
                    BydLogHelper.logW(SeekBarContainer.this.TAG, "handleMessage  hasMessageUpdateProgress:" + SeekBarContainer.this.hasMessageUpdateProgress);
                    if (!SeekBarContainer.this.hasMessageUpdateProgress) {
                        SeekBarContainer.this.mHandler.sendEmptyMessage(2);
                        SeekBarContainer.this.hasMessageUpdateProgress = true;
                    }
                    if (SeekBarContainer.this.imgResAll != null) {
                        SeekBarContainer.this.setImageOnProgress(progress);
                    } else if (SeekBarContainer.this.imgResMute != 0) {
                        if (progress == SeekBarContainer.this.mCustomVerticalSeekBar.getMin()) {
                            SeekBarContainer.this.mSeekBarIcon.setImageResource(SeekBarContainer.this.imgResMute);
                        } else {
                            SeekBarContainer.this.mSeekBarIcon.setImageResource(SeekBarContainer.this.imgRes);
                        }
                    }
                }
            }

            @Override // android.widget.SeekBar.OnSeekBarChangeListener
            public void onStartTrackingTouch(SeekBar seekBar) {
                BydLogHelper.logW(SeekBarContainer.this.TAG, "onStartTrackingTouch");
                SeekBarContainer.this.mHandler.removeMessages(1);
                SeekBarContainer.this.mSeekBarProgress.setVisibility(0);
                if (SeekBarContainer.this.mCallback != null) {
                    SeekBarContainer.this.mCallback.onStartTrackingTouch(seekBar);
                }
            }

            @Override // android.widget.SeekBar.OnSeekBarChangeListener
            public void onStopTrackingTouch(SeekBar seekBar) {
                BydLogHelper.logW(SeekBarContainer.this.TAG, "onStopTrackingTouch");
                if (SeekBarContainer.this.mHandler != null) {
                    SeekBarContainer.this.mHandler.removeMessages(2);
                    SeekBarContainer.this.hasMessageUpdateProgress = false;
                }
                if (SeekBarContainer.this.mCallback != null) {
                    SeekBarContainer.this.mCallback.onProgressChanged(SeekBarContainer.this.mCustomVerticalSeekBar, SeekBarContainer.this.mCustomVerticalSeekBar.getProgress(), true);
                }
                SeekBarContainer.this.mHandler.sendEmptyMessageDelayed(1, 3000L);
                if (SeekBarContainer.this.mCallback != null) {
                    SeekBarContainer.this.mCallback.onStopTrackingTouch(seekBar);
                }
            }
        });
        this.mSeekBarProgress.setVisibility(8);
    }

    @Override // android.view.ViewGroup, android.view.View
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        BydLogHelper.logW(this.TAG, "onDetachedFromWindow ");
        removeMessageUpdateProgress();
    }

    public void initSeekBar(int min, int max, int name, int imgRes) {
        this.min = min;
        this.max = max;
        this.imgRes = imgRes;
        this.mCustomVerticalSeekBar.setMin(min);
        this.mCustomVerticalSeekBar.setMax(max);
        this.mSeekBarName.setText(name);
        this.mSeekBarIcon.setImageResource(imgRes);
    }

    public void initSeekBar(int min, int max, int name, int imgRes, int imgResMute) {
        this.min = min;
        this.max = max;
        this.imgRes = imgRes;
        this.imgResMute = imgResMute;
        this.mCustomVerticalSeekBar.setMin(min);
        this.mCustomVerticalSeekBar.setMax(max);
        this.mSeekBarName.setText(name);
        this.mSeekBarIcon.setImageResource(imgRes);
    }

    public void initSeekBar(int min, int max, int name, int[] imgResAll) {
        this.min = min;
        this.max = max;
        this.imgResAll = imgResAll;
        this.imgRes = imgResAll[1];
        this.imgResMute = imgResAll[0];
        initRatioValue();
        this.mCustomVerticalSeekBar.setMin(min);
        this.mCustomVerticalSeekBar.setMax(max);
        this.mSeekBarName.setText(name);
        this.mSeekBarIcon.setImageResource(this.imgRes);
    }

    public void setImgResAll(int[] imgResAll) {
        this.imgResAll = imgResAll;
        int i = imgResAll[1];
        this.imgRes = i;
        this.imgResMute = imgResAll[0];
        this.mSeekBarIcon.setImageResource(i);
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
            if (!this.mSeekBarName.getText().toString().equals(getResources().getString(R.string.center_control))) {
                if (this.mSeekBarName.getText().toString().equals(getResources().getString(R.string.dashboard))) {
                    this.mSeekBarProgress.setText(String.valueOf((progress - 1) * 10));
                } else {
                    this.mSeekBarProgress.setText(String.valueOf(progress));
                }
            }
            if (this.imgResAll != null) {
                setImageOnProgress(progress);
            } else {
                this.mSeekBarIcon.setImageResource(this.imgRes);
            }
        } catch (Exception e) {
            BydLogHelper.logE(e.toString());
            e.printStackTrace();
        }
    }

    public void setProgressViewVisible(int visibility) {
        TextView textView = this.mSeekBarProgress;
        if (textView != null) {
            textView.setVisibility(visibility);
        }
    }

    public void setSeekBarProgressValueVisible() {
        TextView textView = this.mSeekBarProgress;
        if (textView != null) {
            textView.setVisibility(0);
            this.mHandler.removeMessages(1);
            this.mHandler.sendEmptyMessageDelayed(1, 3000L);
        }
    }

    public void setSeekBarText(int progress) {
        this.mSeekBarProgress.setText(String.valueOf(progress));
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
        this.mSeekBarIcon.setImageResource(this.imgRes);
    }

    public void setSeekBarProgressMute(int progress) {
        this.mCustomVerticalSeekBar.setProgress(progress);
        int i = this.imgResMute;
        if (i != 0) {
            this.mSeekBarIcon.setImageResource(i);
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

    public void removeMessageUpdateProgress() {
        BydLogHelper.logW(this.TAG, "removeMessageUpdateProgress  ");
        Handler handler = this.mHandler;
        if (handler != null) {
            handler.removeMessages(2);
        }
        this.hasMessageUpdateProgress = false;
    }

    private class H extends Handler {
        private int mLastProgress;

        public H(Looper mainLooper) {
            super(mainLooper);
            this.mLastProgress = -1;
        }

        @Override // android.os.Handler
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            int i = msg.what;
            BydLogHelper.logD("what:" + i);
            if (i == 1) {
                if (SeekBarContainer.this.mSeekBarProgress != null) {
                    SeekBarContainer.this.mSeekBarProgress.setVisibility(8);
                }
            } else {
                if (i != 2) {
                    return;
                }
                BydLogHelper.logW(SeekBarContainer.this.TAG, "handleMessage  mCallback:" + SeekBarContainer.this.mCallback + "  ---  mCustomVerticalSeekBar:" + SeekBarContainer.this.mCustomVerticalSeekBar);
                if (SeekBarContainer.this.mCallback != null && SeekBarContainer.this.mCustomVerticalSeekBar != null) {
                    int progress = SeekBarContainer.this.mCustomVerticalSeekBar.getProgress();
                    BydLogHelper.logW(SeekBarContainer.this.TAG, "handleMessage  mLastProgress:" + this.mLastProgress + "  ---  progress:" + progress);
                    if (this.mLastProgress != progress) {
                        this.mLastProgress = progress;
                        BydLogHelper.logD("onProgressChanged " + progress);
                        SeekBarContainer.this.mCallback.onProgressChanged(SeekBarContainer.this.mCustomVerticalSeekBar, SeekBarContainer.this.mCustomVerticalSeekBar.getProgress(), true);
                    }
                }
                SeekBarContainer.this.mHandler.sendEmptyMessageDelayed(2, 100L);
            }
        }
    }
}
