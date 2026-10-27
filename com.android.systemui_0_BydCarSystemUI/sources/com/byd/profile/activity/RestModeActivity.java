package com.byd.profile.activity;

import android.content.Context;
import android.content.Intent;
import android.hardware.bydauto.ac.AbsBYDAutoAcListener;
import android.hardware.bydauto.ac.BYDAutoAcDevice;
import android.hardware.bydauto.properties.BYDAutoSysPropertiesDevice;
import android.hardware.bydauto.time.tsimpl.TimeManagerImpl;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.provider.Settings;
import android.view.GestureManager;
import android.view.KeyEvent;
import android.view.View;
import android.widget.TextView;
import com.byd.profile.Constants;
import com.byd.profile.ModeEvent;
import com.byd.profile.ProfileManager;
import com.byd.profile.R;
import com.byd.profile.ToastManager;
import com.byd.profile.aidl.IProfileService;
import com.byd.profile.dialog.AlarmDialog;
import com.byd.profile.utils.SharedPreferencesUtil;
import com.byd.profile.view.TimeView;
import com.bydux.api.common.tools.log.BydLogHelper;
import java.lang.ref.WeakReference;
import java.util.GregorianCalendar;
import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

/* JADX INFO: loaded from: classes2.dex */
public class RestModeActivity extends BaseActivity implements View.OnClickListener {
    private static final int DELAY_ALARM_MINUTES = 10;
    private static final long DELAY_RESUME = 8000;
    private static final long DELAY_UPDATE = 60000;
    private static final String GESTURE_SERVICE = "gesture";
    private static final long MILLIS_PER_SECOND = 1000;
    private static final long SECONDS_PER_MINUTE = 60;
    private static final int TIME_REMIND_SECONDS = 10;
    private boolean isAlarmRunning;
    private boolean isStopped;
    private AbsBYDAutoAcListener mAbsBYDAutoAcListener;
    private AlarmDialog mAlarmDialog;
    private BYDAutoAcDevice mBYDAutoAcDevice;
    private int mDuration;
    private GestureManager mGestureManager;
    private Handler mHandler;
    private long mPressStart;
    private IProfileService mService;
    private int mTemp;
    private TextView mTvDuration;
    private TextView mTvNoon;
    private TextView mTvTemp;
    private TimeView mTvTime;
    private TextView mTvWind;
    private int mWind;
    private TextView tv_voice_control_air_tips;
    private int mCount = 10;
    private GestureManager.MonitorStateListener mMonitorStateListener = new GestureManager.MonitorStateListener() { // from class: com.byd.profile.activity.RestModeActivity.1
        public void onMonitorState(int i) {
            BydLogHelper.logD("onMonitorState");
            RestModeActivity.this.runOnUiThread(new Runnable() { // from class: com.byd.profile.activity.RestModeActivity.1.1
                @Override // java.lang.Runnable
                public void run() {
                    RestModeActivity.this.mHandler.removeMessages(2);
                    int profileMode = ProfileManager.getInstance(RestModeActivity.this).getProfileMode();
                    BydLogHelper.logD("mode = " + profileMode);
                    if (profileMode == 2) {
                        RestModeActivity.this.mHandler.sendEmptyMessageDelayed(2, RestModeActivity.DELAY_RESUME);
                    }
                }
            });
        }
    };
    private final ProfileManager.OnModeChangeListener mOnModeChangeListener = new ProfileManager.OnModeChangeListener() { // from class: com.byd.profile.activity.RestModeActivity.2
        @Override // com.byd.profile.ProfileManager.OnModeChangeListener
        public void onModeChange(int mode, boolean isChecked) {
            if (mode != 2 || isChecked) {
                return;
            }
            RestModeActivity.this.finish();
        }
    };
    private AlarmDialog.IButtonClickListener mButtonClickListener = new AlarmDialog.IButtonClickListener() { // from class: com.byd.profile.activity.RestModeActivity.3
        @Override // com.byd.profile.dialog.AlarmDialog.IButtonClickListener
        public void onClockClosed() {
            RestModeActivity.this.mTvDuration.setText(RestModeActivity.this.getString(R.string.alarm_closed));
        }

        @Override // com.byd.profile.dialog.AlarmDialog.IButtonClickListener
        public void onClockDelay() {
            RestModeActivity.this.mTvDuration.setText(RestModeActivity.this.getString(R.string.rest_tip_alarm, new Object[]{Integer.valueOf(RestModeActivity.this.mDuration)}));
            RestModeActivity.this.mHandler.sendEmptyMessage(0);
            RestModeActivity.this.mHandler.sendEmptyMessageDelayed(1, ((((long) RestModeActivity.this.mDuration) * RestModeActivity.SECONDS_PER_MINUTE) - 10) * RestModeActivity.MILLIS_PER_SECOND);
        }
    };

    static /* synthetic */ int access$610(RestModeActivity restModeActivity) {
        int i = restModeActivity.mDuration;
        restModeActivity.mDuration = i - 1;
        return i;
    }

    static /* synthetic */ int access$710(RestModeActivity restModeActivity) {
        int i = restModeActivity.mCount;
        restModeActivity.mCount = i - 1;
        return i;
    }

    private void registerMonitor() {
        this.mHandler.sendEmptyMessageDelayed(2, DELAY_RESUME);
        GestureManager gestureManager = (GestureManager) getApplicationContext().getSystemService(GESTURE_SERVICE);
        this.mGestureManager = gestureManager;
        gestureManager.subscribeMonitorStateListener(this.mMonitorStateListener);
        this.mGestureManager.setMonitorMode(true);
    }

    @Override // com.byd.profile.activity.BaseActivity, androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    protected void onCreate(Bundle savedInstanceState) {
        int i;
        super.onCreate(savedInstanceState);
        int profileMode = ProfileManager.getInstance(this).getProfileMode();
        BydLogHelper.logD("onCreate mode = " + profileMode);
        if (profileMode != 2) {
            finish();
            return;
        }
        setContentView(R.layout.layout_rest_screen);
        if (savedInstanceState == null) {
            i = SharedPreferencesUtil.getInt(this, Constants.SP_KEY_DURATION_REST, 30);
        } else {
            i = savedInstanceState.getInt(Constants.SP_KEY_DURATION_REST);
        }
        this.mDuration = i;
        findViewById(R.id.btn_exit).setOnClickListener(this);
        TextView textView = (TextView) findViewById(R.id.tv_duration);
        this.mTvDuration = textView;
        textView.setText(getString(R.string.rest_tip_alarm, new Object[]{Integer.valueOf(this.mDuration)}));
        this.mTvTime = (TimeView) findViewById(R.id.tv_time);
        this.mTvNoon = (TextView) findViewById(R.id.tv_noon);
        this.mTvTemp = (TextView) findViewById(R.id.tv_temp);
        this.mTvWind = (TextView) findViewById(R.id.tv_wind);
        TimeHandler timeHandler = new TimeHandler(this);
        this.mHandler = timeHandler;
        timeHandler.sendEmptyMessage(0);
        this.mHandler.sendEmptyMessageDelayed(1, ((((long) this.mDuration) * SECONDS_PER_MINUTE) - 10) * MILLIS_PER_SECOND);
        initData();
        EventBus.getDefault().register(this);
        AlarmDialog alarmDialog = (AlarmDialog) getSupportFragmentManager().findFragmentByTag(AlarmDialog.class.getSimpleName());
        this.mAlarmDialog = alarmDialog;
        if (alarmDialog != null) {
            alarmDialog.setButtonClickListener(this.mButtonClickListener);
        }
        this.mAbsBYDAutoAcListener = new BYDAutoAcListener(this);
    }

    private void initData() {
        try {
            BYDAutoAcDevice bYDAutoAcDevice = BYDAutoAcDevice.getInstance(this);
            this.mBYDAutoAcDevice = bYDAutoAcDevice;
            this.mTemp = bYDAutoAcDevice.getTemprature(1);
            BydLogHelper.logD("temp " + this.mTemp);
            this.mWind = this.mBYDAutoAcDevice.getAcWindLevel();
            BydLogHelper.logD("wind " + this.mWind);
            this.mBYDAutoAcDevice.registerListener(this.mAbsBYDAutoAcListener);
        } catch (Throwable th) {
            th.printStackTrace();
        }
        int i = this.mTemp;
        if (i < 18) {
            i = 17;
        }
        this.mTemp = i;
        this.mTvTemp.setText(String.valueOf(i));
        this.mTvWind.setText(String.valueOf(this.mWind));
        ProfileManager.getInstance(this).registerOnModeChangeListener(this.mOnModeChangeListener);
    }

    private static class BYDAutoAcListener extends AbsBYDAutoAcListener {
        private WeakReference<RestModeActivity> mActivity;

        public BYDAutoAcListener(RestModeActivity activity) {
            this.mActivity = new WeakReference<>(activity);
        }

        @Override // android.hardware.bydauto.ac.AbsBYDAutoAcListener
        public void onAcWindLevelChanged(final int level) {
            super.onAcWindLevelChanged(level);
            final RestModeActivity restModeActivity = this.mActivity.get();
            if (restModeActivity != null) {
                restModeActivity.runOnUiThread(new Runnable() { // from class: com.byd.profile.activity.RestModeActivity.BYDAutoAcListener.1
                    @Override // java.lang.Runnable
                    public void run() {
                        restModeActivity.mWind = level;
                        restModeActivity.mTvWind.setText(String.valueOf(restModeActivity.mWind));
                    }
                });
            }
        }

        @Override // android.hardware.bydauto.ac.AbsBYDAutoAcListener
        public void onTemperatureChanged(int area, final int value) {
            super.onTemperatureChanged(area, value);
            final RestModeActivity restModeActivity = this.mActivity.get();
            if (restModeActivity != null) {
                restModeActivity.runOnUiThread(new Runnable() { // from class: com.byd.profile.activity.RestModeActivity.BYDAutoAcListener.2
                    @Override // java.lang.Runnable
                    public void run() {
                        restModeActivity.mTemp = value;
                        restModeActivity.mTvTemp.setText(String.valueOf(restModeActivity.mTemp));
                    }
                });
            }
        }
    }

    @Override // androidx.fragment.app.FragmentActivity, android.app.Activity
    protected void onResume() {
        super.onResume();
        BydLogHelper.logD("onResume");
        ProfileManager.getInstance(this).setNapResume(true);
        this.isStopped = false;
        GestureManager gestureManager = this.mGestureManager;
        if (gestureManager != null) {
            gestureManager.setMonitorMode(false);
        }
        if (is12Hour(this)) {
            this.mTvNoon.setVisibility(0);
            this.mTvTime.set12Hour(true);
            this.mTvNoon.setText(new GregorianCalendar().get(9) == 0 ? R.string.am : R.string.pm);
        } else {
            this.mTvNoon.setVisibility(8);
            this.mTvTime.set12Hour(false);
        }
        if (this.isAlarmRunning) {
            this.isAlarmRunning = false;
            showAlarmDialog();
        }
    }

    @Override // androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    protected void onStop() {
        super.onStop();
        this.isStopped = true;
        ProfileManager.getInstance(this).setNapResume(false);
    }

    @Override // androidx.appcompat.app.AppCompatActivity, androidx.activity.ComponentActivity, androidx.core.app.ComponentActivity, android.app.Activity
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt(Constants.SP_KEY_DURATION_REST, this.mDuration);
    }

    @Override // androidx.appcompat.app.AppCompatActivity, androidx.fragment.app.FragmentActivity, android.app.Activity
    protected void onDestroy() {
        BydLogHelper.logD("onDestroy");
        EventBus.getDefault().unregister(this);
        this.mHandler.removeMessages(2);
        AlarmDialog alarmDialog = this.mAlarmDialog;
        if (alarmDialog != null) {
            alarmDialog.setButtonClickListener(null);
        }
        GestureManager gestureManager = this.mGestureManager;
        if (gestureManager != null) {
            gestureManager.unSubscribeMonitorStateListener(this.mMonitorStateListener);
            this.mGestureManager.setMonitorMode(false);
        }
        BYDAutoAcDevice bYDAutoAcDevice = this.mBYDAutoAcDevice;
        if (bYDAutoAcDevice != null) {
            bYDAutoAcDevice.unregisterListener(this.mAbsBYDAutoAcListener);
        }
        Handler handler = this.mHandler;
        if (handler != null) {
            handler.removeCallbacksAndMessages(null);
        }
        if (this.mOnModeChangeListener != null) {
            ProfileManager.getInstance(this).unregisterOnModeChangeListener(this.mOnModeChangeListener);
        }
        super.onDestroy();
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View v) {
        if (v.getId() == R.id.btn_exit) {
            BydLogHelper.logD("exit");
            EventBus.getDefault().unregister(this);
            ProfileManager.getInstance(this).stopMode();
            finish();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void startAlarm() {
        if (this.isStopped) {
            this.isAlarmRunning = true;
        } else {
            showAlarmDialog();
        }
    }

    private void showAlarmDialog() {
        AlarmDialog alarmDialog = new AlarmDialog();
        this.mAlarmDialog = alarmDialog;
        alarmDialog.setButtonClickListener(this.mButtonClickListener);
        this.mAlarmDialog.show(getSupportFragmentManager(), AlarmDialog.class.getSimpleName());
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
            ToastManager.show(this, R.string.exit_rest_tip);
            return true;
        }
        return super.dispatchKeyEvent(event);
    }

    private static final class TimeHandler extends Handler {
        private static final int WHAT_RESUME = 2;
        private static final int WHAT_UPDATE_MINUTE = 0;
        private static final int WHAT_UPDATE_SECOND = 1;
        private WeakReference<RestModeActivity> mReference;

        public TimeHandler(RestModeActivity activity) {
            this.mReference = new WeakReference<>(activity);
        }

        @Override // android.os.Handler
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (this.mReference.get() != null) {
                RestModeActivity restModeActivity = this.mReference.get();
                int i = msg.what;
                BydLogHelper.logD("what = " + i);
                if (i == 0) {
                    BydLogHelper.logD("update minutes");
                    if (restModeActivity.mDuration > 0) {
                        restModeActivity.mTvDuration.setText(restModeActivity.getString(R.string.rest_tip_alarm, new Object[]{Integer.valueOf(restModeActivity.mDuration)}));
                        RestModeActivity.access$610(restModeActivity);
                        restModeActivity.mHandler.sendEmptyMessageDelayed(0, RestModeActivity.DELAY_UPDATE);
                        return;
                    }
                    restModeActivity.mDuration = 10;
                    return;
                }
                if (i != 1) {
                    if (i == 2) {
                        restModeActivity.startActivity(new Intent(restModeActivity, (Class<?>) RestModeActivity.class));
                        return;
                    }
                    return;
                }
                BydLogHelper.logD("update seconds");
                if (restModeActivity.mCount == 10) {
                    restModeActivity.startActivity(new Intent(restModeActivity, (Class<?>) RestModeActivity.class));
                }
                if (restModeActivity.mCount > 0) {
                    restModeActivity.mTvDuration.setText(restModeActivity.getString(R.string.rest_tip_alarm_second, new Object[]{Integer.valueOf(restModeActivity.mCount)}));
                    RestModeActivity.access$710(restModeActivity);
                    restModeActivity.mHandler.sendEmptyMessageDelayed(1, RestModeActivity.MILLIS_PER_SECOND);
                } else {
                    restModeActivity.mCount = 10;
                    restModeActivity.startAlarm();
                }
            }
        }
    }

    private boolean is12Hour(Context context) {
        return TimeManagerImpl.TIME_FORMAT_TWELVE.equals(Settings.System.getString(context.getContentResolver(), BYDAutoSysPropertiesDevice.TIME_12_24));
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onModeEvent(ModeEvent event) {
        BydLogHelper.logD("onModeEvent");
        if (event.mode == 2) {
            finish();
        }
    }

    @Override // androidx.fragment.app.FragmentActivity, android.app.Activity
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        BydLogHelper.logD("onNewIntent");
    }
}
