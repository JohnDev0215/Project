package com.byd.card.widget;

import android.app.UiStyleConfig;
import android.content.Context;
import android.content.res.Configuration;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.hardware.bydauto.bodywork.AbsBYDAutoBodyworkListener;
import android.hardware.bydauto.bodywork.BYDAutoBodyworkDevice;
import android.hardware.bydauto.energy.AbsBYDAutoEnergyListener;
import android.hardware.bydauto.energy.BYDAutoEnergyDevice;
import android.hardware.bydauto.light.BYDAutoLightDevice;
import android.hardware.bydauto.test.AbsBYDAutoTestListener;
import android.hardware.bydauto.test.BYDAutoTestDevice;
import android.hardware.bydauto.uistyleconfig.BYDAutoStudy;
import android.hardware.bydauto.uistyleconfig.BYDAutoStudyListener;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.os.SystemProperties;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.core.content.res.ResourcesCompat;
import com.android.systemui.R;
import com.android.systemui.widget.BydSwitch;
import com.byd.card.utils.AppUtils;
import com.byd.card.utils.DensityUtil;
import com.bydux.api.common.tools.log.BydLogHelper;
import com.google.android.material.badge.BadgeDrawable;

/* JADX INFO: loaded from: classes.dex */
public class RollBenchMode {
    private static final String IS_START_ENERGY = "persist.sys.start.energy.test";
    private static final int MSG_ENTER_ENERGY_SUCCESS = 2;
    private static final int MSG_EXIT_ENERGY_TEST = 3;
    private static final int MSG_HIDE_VIEW = 4;
    private static final int MSG_ROLL_BENCH_ENTERED = 6;
    private static final int MSG_ROLL_BENCH_EXITED = 5;
    private static final int START_ENERGY_TEST = 1;
    private static final String TAG = "RollBenchMode";
    private static RollBenchMode instance;
    private DisplayMetrics displayMetrics;
    private BYDAutoBodyworkDevice mBYDAutoBodyworkDevice;
    private BYDAutoEnergyDevice mBYDAutoEnergyDevice;
    private BYDAutoStudy mBYDAutoStudy;
    private BYDAutoTestDevice mBYDAutoTestDevice;
    private BydSwitch mBydSwitch;
    private int mCardHeight;
    private final Context mContext;
    private final WindowManager.LayoutParams mLayoutParams;
    private View mView;
    private LinearLayout mViewBg;
    private final WindowManager mWindowManager;
    private LinearLayout rollbench_view;
    private TextView tvRollBench;
    private TextView tvRollBenchStatus;
    private int uiMode;
    private final float[] lastX = {0.0f};
    private final float[] lastY = {0.0f};
    private final H mHandler = new H();
    private int lastPowerLevel = -1;
    private int mCurrentRollBenchStatus = -1;
    private int mCurrentTestChangeStatus = -1;
    private final AbsBYDAutoTestListener absBYDAutoTestListener = new AbsBYDAutoTestListener() { // from class: com.byd.card.widget.RollBenchMode.1
        @Override // android.hardware.bydauto.test.AbsBYDAutoTestListener
        public void onEnergyTestChange(int status) {
            super.onEnergyTestChange(status);
            BydLogHelper.logW("", "onEnergyTestChange status = " + status + " , mCurrentTestChangeStatus : " + RollBenchMode.this.mCurrentTestChangeStatus);
            if (status != RollBenchMode.this.mCurrentTestChangeStatus) {
                RollBenchMode.this.mCurrentTestChangeStatus = status;
                if (status == 0) {
                    RollBenchMode.this.mHandler.removeMessages(2);
                    RollBenchMode.this.mHandler.sendEmptyMessage(2);
                } else if (status == 1) {
                    RollBenchMode.this.mHandler.removeMessages(3);
                    RollBenchMode.this.mHandler.sendEmptyMessage(3);
                }
            }
        }
    };
    private final BYDAutoStudyListener autoStudyListener = new BYDAutoStudyListener() { // from class: com.byd.card.widget.RollBenchMode.2
        @Override // android.hardware.IBYDAutoListener
        public void isConnected(boolean isBinding) {
            super.isConnected(isBinding);
        }

        @Override // android.hardware.IBYDAutoListener
        public void isOptimizationApi(boolean isOptimizationApi) {
            BydLogHelper.logW("", "isOptimizationApi: " + isOptimizationApi);
            if (isOptimizationApi) {
                int powerLevel = RollBenchMode.this.mBYDAutoBodyworkDevice.getPowerLevel();
                BydLogHelper.logW("", "isConnected powerLevel: " + powerLevel);
                if (powerLevel == 2 || powerLevel == 3) {
                    String str = SystemProperties.get(RollBenchMode.IS_START_ENERGY);
                    BydLogHelper.logW("", "isOptimizationApi isConnected isStartEnergy : " + str);
                    if ("true".equals(str)) {
                        RollBenchMode.this.mHandler.removeMessages(1);
                        RollBenchMode.this.mHandler.sendEmptyMessage(1);
                    } else {
                        RollBenchMode.this.mHandler.removeMessages(4);
                        RollBenchMode.this.mHandler.sendEmptyMessage(4);
                    }
                }
            }
        }
    };
    private final AbsBYDAutoEnergyListener absBYDAutoEnergyListener = new AbsBYDAutoEnergyListener() { // from class: com.byd.card.widget.RollBenchMode.3
        @Override // android.hardware.bydauto.energy.AbsBYDAutoEnergyListener
        public void onRollBenchModeStatusChanged(int status) {
            super.onRollBenchModeStatusChanged(status);
            BydLogHelper.logW("", "onRollBenchModeStatusChanged status: " + status + " --- mCurrentRollBenchStatus: " + RollBenchMode.this.mCurrentRollBenchStatus);
            if (RollBenchMode.this.mCurrentRollBenchStatus != status) {
                RollBenchMode.this.mCurrentRollBenchStatus = status;
                if (status == 1) {
                    RollBenchMode.this.mHandler.removeMessages(6);
                    RollBenchMode.this.mHandler.sendEmptyMessage(6);
                } else if (status == 0) {
                    RollBenchMode.this.mHandler.removeMessages(5);
                    RollBenchMode.this.mHandler.sendEmptyMessage(5);
                }
            }
        }
    };
    private final AbsBYDAutoBodyworkListener mAutoBodyworkListener = new AbsBYDAutoBodyworkListener() { // from class: com.byd.card.widget.RollBenchMode.4
        @Override // android.hardware.bydauto.bodywork.AbsBYDAutoBodyworkListener
        public void onPowerLevelChanged(int level) {
            super.onPowerLevelChanged(level);
            BydLogHelper.logW("", "RollBenchMode onPowerLevelChanged level: " + level + " --- lastPowerLevel: " + RollBenchMode.this.lastPowerLevel);
            if (RollBenchMode.this.lastPowerLevel != level) {
                RollBenchMode.this.lastPowerLevel = level;
                if (level == 0) {
                    String str = SystemProperties.get(RollBenchMode.IS_START_ENERGY);
                    BydLogHelper.logW("", "onPowerLevelChanged OFF isStartEnergy : " + str);
                    if ("true".equals(str)) {
                        RollBenchMode.this.mHandler.removeMessages(4);
                        RollBenchMode.this.mHandler.sendEmptyMessage(4);
                        RollBenchMode.this.mBYDAutoTestDevice.setEnergyTest(1);
                        RollBenchMode.this.mCurrentRollBenchStatus = 0;
                        return;
                    }
                    RollBenchMode.this.mHandler.removeMessages(4);
                    RollBenchMode.this.mHandler.sendEmptyMessage(4);
                    return;
                }
                if (level == 2 || level == 3) {
                    String str2 = SystemProperties.get(RollBenchMode.IS_START_ENERGY);
                    BydLogHelper.logW("", "onPowerLevelChanged ON isStartEnergy : " + str2);
                    if ("true".equals(str2)) {
                        RollBenchMode.this.mHandler.removeMessages(1);
                        RollBenchMode.this.mHandler.sendEmptyMessageDelayed(1, 3000L);
                    } else {
                        RollBenchMode.this.mHandler.removeMessages(4);
                        RollBenchMode.this.mHandler.sendEmptyMessage(4);
                    }
                }
            }
        }
    };

    public static synchronized RollBenchMode getInstance(Context context) {
        if (instance == null) {
            instance = new RollBenchMode(context);
        }
        return instance;
    }

    private RollBenchMode(Context context) {
        this.mContext = context;
        this.uiMode = context.getResources().getConfiguration().uiMode;
        this.mWindowManager = (WindowManager) context.getSystemService("window");
        WindowManager.LayoutParams layoutParams = new WindowManager.LayoutParams();
        this.mLayoutParams = layoutParams;
        layoutParams.format = 1;
        layoutParams.flags = 262152;
        layoutParams.windowAnimations = 0;
        layoutParams.type = 2038;
        layoutParams.width = (int) context.getResources().getDimension(R.dimen.dp_240);
        layoutParams.height = (int) context.getResources().getDimension(R.dimen.dp_148);
        layoutParams.setTitle(TAG);
    }

    public void init() {
        initLocation();
        initView();
        this.mBYDAutoTestDevice = BYDAutoTestDevice.getInstance(this.mContext);
        this.mBYDAutoBodyworkDevice = BYDAutoBodyworkDevice.getInstance(this.mContext);
        this.mBYDAutoStudy = BYDAutoStudy.getInstance(this.mContext);
        this.mBYDAutoEnergyDevice = BYDAutoEnergyDevice.getInstance(this.mContext);
        this.mView.setVisibility(8);
        addView();
    }

    private void initView() {
        if (AppUtils.isLandSpace(this.mContext)) {
            this.mCardHeight = (int) this.mContext.getResources().getDimension(R.dimen.dp_265);
        } else {
            this.mCardHeight = (int) this.mContext.getResources().getDimension(R.dimen.dp_320);
        }
        int uiStyle = UiStyleConfig.getInstance(this.mContext).getUiStyle();
        BydLogHelper.logW("", TAG, "initView style : " + uiStyle);
        if (uiStyle == 1) {
            this.mView = LayoutInflater.from(this.mContext).inflate(R.layout.denza_rollbench_mode, (ViewGroup) null);
        } else if (uiStyle != 2) {
            this.mView = LayoutInflater.from(this.mContext).inflate(R.layout.rollbench_mode, (ViewGroup) null);
        }
        this.mViewBg = (LinearLayout) this.mView.findViewById(R.id.rollbench_mode_view);
        this.rollbench_view = (LinearLayout) this.mView.findViewById(R.id.rollbench_view);
        this.tvRollBenchStatus = (TextView) this.mView.findViewById(R.id.tvRollBenchStatus);
        this.tvRollBench = (TextView) this.mView.findViewById(R.id.tvRollBench);
        BydSwitch bydSwitch = (BydSwitch) this.mView.findViewById(R.id.rollbench_switch);
        this.mBydSwitch = bydSwitch;
        bydSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() { // from class: com.byd.card.widget.RollBenchMode.5
            @Override // android.widget.CompoundButton.OnCheckedChangeListener
            public void onCheckedChanged(CompoundButton compoundButton, boolean z) {
                Log.i(RollBenchMode.TAG, "onCheckedChanged isChecked = " + z);
                BydLogHelper.logI(RollBenchMode.TAG, "onCheckedChanged sw_open_energy isChecked : " + z);
                RollBenchMode.this.mBYDAutoTestDevice.setEnergyTest(!z ? 1 : 0);
                RollBenchMode.this.updateStatus(z);
                SystemProperties.set(RollBenchMode.IS_START_ENERGY, z ? "true" : "false");
            }
        });
        BydLogHelper.logW("", TAG, "SystemProperties.get(IS_START_ENERGY) : " + SystemProperties.get(IS_START_ENERGY));
        if ("true".equals(SystemProperties.get(IS_START_ENERGY))) {
            updateBydSwitch(true);
            updateRollBenchStatus(true);
        } else {
            updateRollBenchStatus(false);
            updateBydSwitch(false);
        }
        this.mView.setOnTouchListener(new View.OnTouchListener() { // from class: com.byd.card.widget.RollBenchMode.6
            @Override // android.view.View.OnTouchListener
            public boolean onTouch(View v, MotionEvent event) {
                int action = event.getAction();
                if (action == 1) {
                    RollBenchMode.this.lastX[0] = 0.0f;
                    RollBenchMode.this.lastY[0] = 0.0f;
                } else if (action == 2) {
                    if (RollBenchMode.this.lastX[0] == 0.0f || RollBenchMode.this.lastY[0] == 0.0f) {
                        RollBenchMode.this.lastX[0] = event.getRawX();
                        RollBenchMode.this.lastY[0] = event.getRawY();
                    }
                    float rawX = event.getRawX();
                    float rawY = event.getRawY();
                    int i = (int) (rawX - RollBenchMode.this.lastX[0]);
                    int i2 = (int) (rawY - RollBenchMode.this.lastY[0]);
                    RollBenchMode.this.mLayoutParams.x += i;
                    RollBenchMode.this.mLayoutParams.y += i2;
                    if (RollBenchMode.this.mLayoutParams.y > (RollBenchMode.this.displayMetrics.heightPixels - RollBenchMode.this.mCardHeight) - DensityUtil.getNavigationBarHeight(RollBenchMode.this.mContext)) {
                        RollBenchMode.this.mLayoutParams.y = (RollBenchMode.this.displayMetrics.heightPixels - RollBenchMode.this.mCardHeight) - DensityUtil.getNavigationBarHeight(RollBenchMode.this.mContext);
                    }
                    RollBenchMode.this.mWindowManager.updateViewLayout(RollBenchMode.this.mView, RollBenchMode.this.mLayoutParams);
                    RollBenchMode.this.lastX[0] = rawX;
                    RollBenchMode.this.lastY[0] = rawY;
                }
                return true;
            }
        });
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void updateBydSwitch(boolean isChecked) {
        BydLogHelper.logW("", "updateBydSwitch isChecked : " + isChecked);
        this.mBydSwitch.setEverTouch(true);
        this.mBydSwitch.setChecked(isChecked, false);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void updateRollBenchStatus(boolean isChecked) {
        BydLogHelper.logW("", "updateRollBenchStatus isChecked : " + isChecked);
        if (isChecked) {
            this.tvRollBenchStatus.setText(this.mContext.getResources().getString(R.string.str_rollbench_mode_active));
        } else {
            this.tvRollBenchStatus.setText(this.mContext.getResources().getString(R.string.str_rollbench_mode_exited));
        }
    }

    private void initLocation() {
        this.mLayoutParams.gravity = BadgeDrawable.TOP_START;
        this.displayMetrics = this.mContext.getResources().getDisplayMetrics();
        this.mLayoutParams.x = (int) (r0.widthPixels - this.mContext.getResources().getDimension(R.dimen.dp_200));
        this.mLayoutParams.y = 0;
    }

    public void onConfigurationChanged(Configuration newConfig) {
        if (this.mView == null) {
            initView();
            initLocation();
            addView();
            return;
        }
        if (this.uiMode != newConfig.uiMode) {
            Drawable drawable = ResourcesCompat.getDrawable(this.mContext.getResources(), R.drawable.transport_mode_bg, null);
            if (drawable instanceof GradientDrawable) {
                GradientDrawable gradientDrawable = (GradientDrawable) drawable;
                gradientDrawable.setColors(new int[]{this.mContext.getResources().getColor(R.color.transport_mode_start_bg), this.mContext.getResources().getColor(R.color.transport_mode_end_bg)});
                gradientDrawable.setGradientType(0);
                gradientDrawable.setOrientation(GradientDrawable.Orientation.TOP_BOTTOM);
                gradientDrawable.setGradientCenter(0.0f, 0.0f);
                gradientDrawable.setGradientRadius(180.0f);
                this.mViewBg.setBackground(gradientDrawable);
            } else {
                this.mViewBg.setBackgroundResource(R.drawable.rollbench_mode_bg);
                this.rollbench_view.setBackgroundResource(R.drawable.rollbench_mode_btn_bg);
            }
            this.tvRollBench.setTextColor(this.mContext.getResources().getColor(R.color.dialog_carplay_title));
            this.uiMode = newConfig.uiMode;
        }
        BydLogHelper.logW("", TAG, "onConfigurationChanged SystemProperties.get(IS_START_ENERGY) : " + SystemProperties.get(IS_START_ENERGY));
        if ("true".equals(SystemProperties.get(IS_START_ENERGY))) {
            updateBydSwitch(true);
            updateRollBenchStatus(true);
        } else {
            updateRollBenchStatus(false);
            updateBydSwitch(false);
        }
        this.tvRollBench.setText(this.mContext.getResources().getString(R.string.str_rollbench_mode));
    }

    public void addView() {
        WindowManager windowManager = this.mWindowManager;
        if (windowManager != null) {
            windowManager.addView(this.mView, this.mLayoutParams);
        }
    }

    public void removeView() {
        WindowManager windowManager = this.mWindowManager;
        if (windowManager != null) {
            windowManager.removeViewImmediate(this.mView);
        }
    }

    public void hideView() {
        BydLogHelper.logW("", "hideView " + this.mView.getVisibility());
        View view = this.mView;
        if (view == null || view.getVisibility() != 0) {
            return;
        }
        this.mView.setVisibility(8);
        BydLogHelper.logW("", "hideView");
    }

    public void showView() {
        BydLogHelper.logW("", "showView " + this.mView.getVisibility());
        View view = this.mView;
        if (view == null || view.getVisibility() == 0) {
            return;
        }
        this.mView.setVisibility(0);
    }

    public boolean isShow() {
        return this.mView.getVisibility() == 0;
    }

    private final class H extends Handler {
        public H() {
            super(Looper.getMainLooper());
        }

        @Override // android.os.Handler
        public void handleMessage(Message msg) {
            if (msg.what == 2) {
                BydLogHelper.logW("", "handleMessage MSG_ENTER_ENERGY_SUCCESS");
                RollBenchMode.this.updateBydSwitch(true);
                RollBenchMode.this.updateRollBenchStatus(true);
                BydLogHelper.logW("", "set true");
                SystemProperties.set(RollBenchMode.IS_START_ENERGY, "true");
                RollBenchMode.this.showView();
                return;
            }
            if (msg.what == 3) {
                BydLogHelper.logW("", "handleMessage MSG_EXIT_ENERGY_TEST");
                int powerLevel = RollBenchMode.this.mBYDAutoBodyworkDevice.getPowerLevel();
                BydLogHelper.logW("", "handleMessage MSG_EXIT_ENERGY_TEST powerLevel :" + powerLevel);
                if (powerLevel == 2 || powerLevel == 3) {
                    RollBenchMode.this.updateBydSwitch(false);
                    RollBenchMode.this.updateRollBenchStatus(false);
                    BydLogHelper.logW("", "set false");
                    SystemProperties.set(RollBenchMode.IS_START_ENERGY, "false");
                    return;
                }
                return;
            }
            if (msg.what == 4) {
                BydLogHelper.logW("", "handleMessage MSG_HIDE_VIEW");
                RollBenchMode.this.hideView();
                return;
            }
            if (msg.what == 1) {
                BydLogHelper.logW("", "handleMessage START_ENERGY_TEST");
                RollBenchMode.this.mBYDAutoTestDevice.setEnergyTest(0);
                return;
            }
            if (msg.what == 5) {
                BydLogHelper.logW("", "handleMessage MSG_ROLL_BENCH_EXITED");
                BydLogHelper.logW("", "handleMessage MSG_ROLL_BENCH_EXITED powerLevel :" + RollBenchMode.this.mBYDAutoBodyworkDevice.getPowerLevel());
                RollBenchMode.this.mBYDAutoTestDevice.setEnergyTest(1);
                BydLogHelper.logW("", "set false");
                SystemProperties.set(RollBenchMode.IS_START_ENERGY, "false");
                RollBenchMode.this.updateRollBenchStatus(false);
                RollBenchMode.this.updateBydSwitch(false);
                return;
            }
            if (msg.what == 6) {
                BydLogHelper.logW("", "handleMessage MSG_ROLL_BENCH_ENTERED setHeadlightControlMode 1");
                BYDAutoLightDevice.getInstance(RollBenchMode.this.mContext).setHeadlightControlMode(1);
                BydLogHelper.logW("", "handleMessage MSG_ROLL_BENCH_ENTERED setDayTimeLightState 1");
                BYDAutoLightDevice.getInstance(RollBenchMode.this.mContext).setDayTimeLightState(1);
            }
        }
    }

    public void updateStatus(boolean b) {
        updateBydSwitch(b);
        updateRollBenchStatus(b);
    }
}
