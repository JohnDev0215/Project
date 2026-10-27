package com.byd.profile.activity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.os.SystemProperties;
import android.view.GestureManager;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import com.byd.profile.ModeEvent;
import com.byd.profile.ProfileManager;
import com.byd.profile.R;
import com.byd.profile.ToastManager;
import com.bydux.api.common.tools.log.BydLogHelper;
import java.lang.ref.WeakReference;
import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

/* JADX INFO: loaded from: classes2.dex */
public class WipeActivity extends BaseActivity {
    private static final long DELAY_RESUME = 8000;
    private static final String GESTURE_SERVICE = "gesture";
    public static String SYS_BAR_SYSTEMUI_KEY = "sys.status.systemui_bar";
    public static String SYS_BAR_SYSTEMUI_VALUE_DISABLE = "0";
    public static String SYS_BAR_SYSTEMUI_VALUE_ENABLE = "1";
    private static final int WHAT_LONG_PRESS = 1;
    private static final int WHAT_RESUME = 2;
    private Button mBtnExit;
    private GestureManager mGestureManager;
    private long mPressStart;
    private TextView mTvTime;
    private int count = 3;
    private Handler mHandler = new TimerHandler(this);
    private GestureManager.MonitorStateListener mMonitorStateListener = new GestureManager.MonitorStateListener() { // from class: com.byd.profile.activity.WipeActivity.1
        public void onMonitorState(int i) {
            BydLogHelper.logD("onMonitorState");
            WipeActivity.this.runOnUiThread(new Runnable() { // from class: com.byd.profile.activity.WipeActivity.1.1
                @Override // java.lang.Runnable
                public void run() {
                    WipeActivity.this.mHandler.removeMessages(2);
                    int profileMode = ProfileManager.getInstance(WipeActivity.this).getProfileMode();
                    BydLogHelper.logD("mode = " + profileMode);
                    if (profileMode == 5) {
                        WipeActivity.this.mHandler.sendEmptyMessageDelayed(2, WipeActivity.DELAY_RESUME);
                    }
                }
            });
        }
    };
    private ProfileManager.OnModeChangeListener mOnModeChangeListener = new ProfileManager.OnModeChangeListener() { // from class: com.byd.profile.activity.WipeActivity.3
        @Override // com.byd.profile.ProfileManager.OnModeChangeListener
        public void onModeChange(int mode, boolean isChecked) {
            if (mode != 5 || isChecked) {
                return;
            }
            WipeActivity.this.finish();
        }
    };

    static /* synthetic */ int access$110(WipeActivity wipeActivity) {
        int i = wipeActivity.count;
        wipeActivity.count = i - 1;
        return i;
    }

    private void registerMonitor() {
        BydLogHelper.logD("registerMonitor");
        this.mHandler.sendEmptyMessageDelayed(2, DELAY_RESUME);
        GestureManager gestureManager = (GestureManager) getApplicationContext().getSystemService(GESTURE_SERVICE);
        this.mGestureManager = gestureManager;
        gestureManager.subscribeMonitorStateListener(this.mMonitorStateListener);
        this.mGestureManager.setMonitorMode(true);
    }

    @Override // com.byd.profile.activity.BaseActivity, androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        int profileMode = ProfileManager.getInstance(this).getProfileMode();
        BydLogHelper.logD("onCreate mode = " + profileMode);
        if (profileMode != 5) {
            finish();
            return;
        }
        setContentView(R.layout.layout_wipe_screen);
        this.mTvTime = (TextView) findViewById(R.id.tv_time);
        Button button = (Button) findViewById(R.id.btn_exit);
        this.mBtnExit = button;
        button.setOnTouchListener(new View.OnTouchListener() { // from class: com.byd.profile.activity.WipeActivity.2
            @Override // android.view.View.OnTouchListener
            public boolean onTouch(View v, MotionEvent event) {
                int action = event.getAction();
                BydLogHelper.logD("action = " + action);
                if (action == 0) {
                    WipeActivity.this.count = 3;
                    WipeActivity.this.mHandler.sendEmptyMessage(1);
                    return false;
                }
                if (action != 1 && action != 3 && action != 4) {
                    return false;
                }
                WipeActivity.this.mTvTime.setVisibility(4);
                WipeActivity.this.mHandler.removeMessages(1);
                return false;
            }
        });
        ProfileManager.getInstance(this).registerOnModeChangeListener(this.mOnModeChangeListener);
        EventBus.getDefault().register(this);
    }

    @Override // androidx.fragment.app.FragmentActivity, android.app.Activity
    protected void onResume() {
        super.onResume();
        BydLogHelper.logD("onResume");
        ProfileManager.getInstance(this).setCleanResume(true);
        GestureManager gestureManager = this.mGestureManager;
        if (gestureManager != null) {
            gestureManager.unSubscribeMonitorStateListener(this.mMonitorStateListener);
            this.mGestureManager.setMonitorMode(false);
        }
        SystemProperties.set(SYS_BAR_SYSTEMUI_KEY, SYS_BAR_SYSTEMUI_VALUE_DISABLE);
    }

    @Override // androidx.fragment.app.FragmentActivity, android.app.Activity
    protected void onPause() {
        super.onPause();
        SystemProperties.set(SYS_BAR_SYSTEMUI_KEY, SYS_BAR_SYSTEMUI_VALUE_ENABLE);
    }

    @Override // androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    protected void onStop() {
        super.onStop();
        BydLogHelper.logD("onStop");
        ProfileManager.getInstance(this).setCleanResume(false);
    }

    @Override // androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    protected void onDestroy() {
        super.onDestroy();
        this.mHandler.removeMessages(2);
        EventBus.getDefault().unregister(this);
        ProfileManager.getInstance(this).unregisterOnModeChangeListener(this.mOnModeChangeListener);
        GestureManager gestureManager = this.mGestureManager;
        if (gestureManager != null) {
            gestureManager.unSubscribeMonitorStateListener(this.mMonitorStateListener);
            this.mGestureManager.setMonitorMode(false);
        }
        SystemProperties.set(SYS_BAR_SYSTEMUI_KEY, SYS_BAR_SYSTEMUI_VALUE_ENABLE);
    }

    private static class TimerHandler extends Handler {
        private WeakReference<WipeActivity> mReference;

        public TimerHandler(WipeActivity activity) {
            this.mReference = new WeakReference<>(activity);
        }

        @Override // android.os.Handler
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (this.mReference.get() != null) {
                WipeActivity wipeActivity = this.mReference.get();
                if (msg.what == 1) {
                    if (wipeActivity.mBtnExit.isPressed()) {
                        if (wipeActivity.count > 0) {
                            wipeActivity.mTvTime.setVisibility(0);
                            wipeActivity.mTvTime.setText(String.valueOf(wipeActivity.count));
                            WipeActivity.access$110(wipeActivity);
                            sendEmptyMessageDelayed(1, 1000L);
                            return;
                        }
                        ProfileManager.getInstance(wipeActivity).stopMode();
                        wipeActivity.finish();
                        return;
                    }
                    wipeActivity.mTvTime.setVisibility(4);
                    return;
                }
                if (msg.what == 2) {
                    wipeActivity.startActivity(new Intent(wipeActivity, (Class<?>) WipeActivity.class));
                }
            }
        }
    }

    @Override // androidx.appcompat.app.AppCompatActivity, androidx.core.app.ComponentActivity, android.app.Activity, android.view.Window.Callback
    public boolean dispatchKeyEvent(KeyEvent event) {
        int action = event.getAction();
        int keyCode = event.getKeyCode();
        if (keyCode == 26) {
            if (action == 0) {
                this.mPressStart = System.currentTimeMillis();
            } else if (action == 1 && System.currentTimeMillis() - this.mPressStart < 500) {
                return true;
            }
        } else if (keyCode == 4 && action == 1) {
            ToastManager.show(this, R.string.exit_wipe_tip);
            return true;
        }
        return super.dispatchKeyEvent(event);
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onModeEvent(ModeEvent event) {
        if (event.mode == 5) {
            finish();
        }
    }

    @Override // androidx.fragment.app.FragmentActivity, android.app.Activity
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        BydLogHelper.logD("onNewIntent");
    }
}
