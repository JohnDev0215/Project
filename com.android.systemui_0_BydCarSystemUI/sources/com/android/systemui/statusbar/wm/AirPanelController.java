package com.android.systemui.statusbar.wm;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.PopupWindow;
import android.widget.RadioButton;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import android.widget.TextView;
import com.android.systemui.Constants;
import com.android.systemui.Dependency;
import com.android.systemui.R;
import com.android.systemui.SysUiServiceProvider;
import com.android.systemui.base.GestureDialogImpl;
import com.android.systemui.statusbar.phone.StatusBar;
import com.android.systemui.statusbar.policy.CarController;
import com.android.systemui.statusbar.policy.HvacController;
import com.android.systemui.statusbar.wm.AirPanelController;
import com.android.systemui.util.BlurUtil;
import com.android.systemui.util.FastClickUtil;
import com.android.systemui.util.SharedPreferencesUtil;
import com.android.systemui.util.StyleUtil;
import com.android.systemui.util.SysUiTempUtils;
import com.android.systemui.util.ViewSoundEffectUtil;
import com.android.systemui.widget.BydImageView;
import com.android.systemui.widget.BydPopupWindow;
import com.android.systemui.widget.CommonDialog;
import com.android.systemui.widget.HorizontalWheelView;
import com.android.systemui.widget.WheelView;
import com.android.systemui.widget.WindLevelButton;
import com.byd.card.widget.BlurView;
import com.bydux.api.common.tools.log.BydLogHelper;
import com.google.android.material.tabs.TabLayout;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/* JADX INFO: loaded from: classes.dex */
public class AirPanelController extends BaseController implements View.OnClickListener {
    private static final float ALPHA_DISABLE = 0.45f;
    private static final float ALPHA_ENABLE = 1.0f;
    private static final String CUSTOM_TYPE = "system.ui.custom.type";
    public static final int CUSTOM_TYPE_CYCLE_MODE = 2;
    public static final int CUSTOM_TYPE_FRONT_DEFROST = 1;
    public static final int CUSTOM_TYPE_VENTILATION = 3;
    public static final int POPUP_WINDOW_TYPE_CUSTOM = 4;
    public static final int POPUP_WINDOW_TYPE_EAAC_TEMP = 5;
    public static final int POPUP_WINDOW_TYPE_LEFT_TEMP = 1;
    public static final int POPUP_WINDOW_TYPE_NONE = 0;
    public static final int POPUP_WINDOW_TYPE_RIGHT_TEMP = 2;
    public static final int POPUP_WINDOW_TYPE_WIND_LEVEL = 3;
    private static final int SPRING_BACK_TIME = 2000;
    private SeekBar eaac_pop_seekbar;
    private HvacController hvacController;
    private AirPanelControllerListener iAirPanelControllerListener;
    private BydImageView imgDefrost;
    private int indexTemp;
    private int indexWind;
    private BydImageView ivVentilation;
    private RelativeLayout iv_left_separate;
    private BydImageView iv_left_temp_reduction;
    private BydImageView iv_left_temp_rise;
    private BydImageView iv_loop_mode;
    private RelativeLayout iv_right_separate;
    private BydImageView iv_right_temp_reduction;
    private BydImageView iv_right_temp_rise;
    private BydImageView iv_set_eaac_temp_reduction;
    private BydImageView iv_set_eaac_temp_rise;
    private BydImageView iv_wind_dec;
    private BydImageView iv_wind_inc;
    private HorizontalWheelView leftWheelView;
    private CarController mCarController;
    private final Context mContext;
    private int mCurrCustomType;
    private CommonDialog mDialog;
    private GestureControl mGestureControl;
    private ArrayList<String> mPopTempData;
    private BydPopupWindow mPopupCustom;
    private BydPopupWindow mPopupEaacTemp;
    private int mPopupHeight;
    private BydPopupWindow mPopupTempLeft;
    private BydPopupWindow mPopupTempRight;
    private int mPopupWight;
    private BydPopupWindow mPopupWindLevel;
    private int mPopupX;
    private int mPopupY;
    private final Resources mResources;
    private WindLevelButton mSeekBar;
    private String[] mTempDatas;
    private View mViewCustom;
    private View mViewEaacTemp;
    private View mViewTempLeft;
    private View mViewTempRight;
    private View mViewWindLevel;
    private int mWindLevel;
    private RadioButton radioBtnCycleMode;
    private RadioButton radioBtnDefrost;
    private RadioButton radioBtnVentilation;
    private HorizontalWheelView rightWheelView;
    private RelativeLayout rlCycleMode;
    private RelativeLayout rlDefrost;
    private RelativeLayout rlVentilation;
    private StatusBar statusBar;
    private TextView tvDefrost;
    private BydImageView tvLeftDual;
    private BydImageView tvRightDual;
    private TextView tvVentilation;
    private TextView tv_loop_mode;
    private ArrayList<String> mTempData = new ArrayList<>();
    private List<TempData> mTempList = new ArrayList();
    private int mUnit = 1;
    private int mCurrPopupWindow = 0;
    private int mLastPopupWindow = 0;
    private int mAcType = 1;
    private int mMaxTemp = 33;
    private int mMinTemp = 17;
    private boolean isShowPopupWindow = false;
    private final int[] mWindResIds = {R.drawable.wind_level_pop_seekbar_background_0, R.drawable.wind_level_pop_seekbar_background_1, R.drawable.wind_level_pop_seekbar_background_2, R.drawable.wind_level_pop_seekbar_background_3, R.drawable.wind_level_pop_seekbar_background_4, R.drawable.wind_level_pop_seekbar_background_5, R.drawable.wind_level_pop_seekbar_background_6, R.drawable.wind_level_pop_seekbar_background_7};
    private final HvacController.Callback mHvacCallback = new AnonymousClass6();
    private final PopupWindow.OnDismissListener mDismissListener = new PopupWindow.OnDismissListener() { // from class: com.android.systemui.statusbar.wm.AirPanelController.7
        @Override // android.widget.PopupWindow.OnDismissListener
        public void onDismiss() {
            AirPanelController.this.mAirPanelControllerHandler.removeMessages(260);
            AirPanelController.this.mAirPanelControllerHandler.removeMessages(259);
            AirPanelController.this.mAirPanelControllerHandler.removeMessages(257);
            AirPanelController.this.mAirPanelControllerHandler.removeMessages(258);
            AirPanelController.this.isShowPopupWindow = false;
            BydLogHelper.logD("PopupWindow OnDismissListener onDismiss");
            AirPanelController airPanelController = AirPanelController.this;
            airPanelController.mLastPopupWindow = airPanelController.mCurrPopupWindow;
            AirPanelController.this.setCurrPopupWindow(0);
        }
    };
    private final SysUiTempUtils mSysUiTempUtils = SysUiTempUtils.getInstance();
    private final AirPanelControllerHandler mAirPanelControllerHandler = new AirPanelControllerHandler(Looper.getMainLooper());

    public interface AirPanelControllerListener {
        void onCustomTypeChanged(int type);

        void onTemperatureChanged(int area, int value);
    }

    @Override // com.android.systemui.statusbar.wm.BaseController
    public void reset() {
    }

    public void resetGesturePopup() {
    }

    public AirPanelController(Context context) {
        this.mContext = context;
        this.mResources = context.getResources();
    }

    @Override // com.android.systemui.statusbar.wm.BaseController
    public void startUp() {
        Log.i("AirPanelController", "startUp");
        this.hvacController = (HvacController) Dependency.get(HvacController.class);
        this.mCarController = (CarController) Dependency.get(CarController.class);
        this.mUnit = this.hvacController.getUnit();
        this.mAcType = this.hvacController.getAcType();
        BydLogHelper.logD("AirPanelController", "startUp mUnit : " + this.mUnit + ", mAcType : " + this.mAcType);
        this.mPopTempData = new ArrayList<>();
        this.mTempDatas = this.mSysUiTempUtils.getTemps(this.mUnit);
        this.mTempList = this.mSysUiTempUtils.getTempDatas(this.mUnit);
        this.mPopTempData.addAll(Arrays.asList(this.mTempDatas));
        this.statusBar = (StatusBar) SysUiServiceProvider.getComponent(this.mContext, StatusBar.class);
        int i = SharedPreferencesUtil.getInt(this.mContext, CUSTOM_TYPE, 1);
        this.mCurrCustomType = i;
        CarController carController = this.mCarController;
        if (carController != null) {
            carController.syncHvacIcon2Qnx(i);
        }
        HvacController hvacController = this.hvacController;
        if (hvacController != null) {
            hvacController.addCallback(this.mHvacCallback);
        }
        GestureControl gestureControl = (GestureControl) Dependency.get(GestureControl.class);
        this.mGestureControl = gestureControl;
        gestureControl.startUp();
    }

    private void initGestureView() {
        if (!GestureDialogImpl.getInstance(this.mContext).isInitData() || GestureDialogImpl.getInstance(this.mContext).getTabLayoutGesture().getTabCount() == 0 || GestureDialogImpl.getInstance(this.mContext).getWheelViewGesture().getItemCount() == 0) {
            if (this.hvacController == null) {
                this.hvacController = (HvacController) Dependency.get(HvacController.class);
            }
            initGestureTempData();
            int tempIndex = getTempIndex();
            BydLogHelper.logD("selectedIndex:" + tempIndex);
            GestureDialogImpl.getInstance(this.mContext).getWheelViewGesture().setIsLoop(false);
            GestureDialogImpl.getInstance(this.mContext).getWheelViewGesture().setCanScroll(false);
            GestureDialogImpl.getInstance(this.mContext).getWheelViewGesture().setSelected(tempIndex);
            GestureDialogImpl.getInstance(this.mContext).getWheelViewGesture().setOnSelectListener(new WheelView.OnSelectListener() { // from class: com.android.systemui.statusbar.wm.AirPanelController.1
                @Override // com.android.systemui.widget.WheelView.OnSelectListener
                public void onSelect(int index, String text) {
                    BydLogHelper.logD("onSelect  index:" + index + ",text:" + text);
                    if (text.contains(AirPanelController.this.mContext.getString(R.string.sys_ui_temp_hi))) {
                        GestureDialogImpl.getInstance(AirPanelController.this.mContext).getImgDegree().setVisibility(8);
                    } else if (text.contains(AirPanelController.this.mContext.getString(R.string.sys_ui_temp_low))) {
                        GestureDialogImpl.getInstance(AirPanelController.this.mContext).getImgDegree().setVisibility(8);
                    } else {
                        GestureDialogImpl.getInstance(AirPanelController.this.mContext).getImgDegree().setVisibility(0);
                    }
                }
            });
            int i = this.mUnit;
            if (i == 1) {
                GestureDialogImpl.getInstance(this.mContext).getImgDegree().setImageResource(StyleUtil.getResMipmap(R.mipmap.img_gesture_ac_temperature_unit, "img_gesture_ac_temperature_unit"));
            } else if (i == 0) {
                GestureDialogImpl.getInstance(this.mContext).getImgDegree().setImageResource(StyleUtil.getResMipmap(R.mipmap.img_gesture_ac_temperature_unit_fahrenheit, "img_gesture_ac_temperature_unit_fahrenheit"));
            }
            this.indexTemp = tempIndex;
            if (tempIndex == 0 || tempIndex == this.mTempList.size() - 1) {
                GestureDialogImpl.getInstance(this.mContext).getImgDegree().setVisibility(8);
            } else {
                GestureDialogImpl.getInstance(this.mContext).getImgDegree().setVisibility(0);
            }
            int acWindLevel = this.hvacController.getAcWindLevel();
            BydLogHelper.logD("level:" + acWindLevel);
            int i2 = acWindLevel - 1;
            this.indexWind = i2;
            if (i2 < 0) {
                BydLogHelper.logD("invalid indexWind:" + this.indexWind);
                this.indexWind = 0;
            }
            GestureDialogImpl.getInstance(this.mContext).getTabLayoutGesture().removeAllTabs();
            for (int i3 = 0; i3 < this.mSysUiTempUtils.getWindLevelSize(); i3++) {
                TabLayout.Tab customView = GestureDialogImpl.getInstance(this.mContext).getTabLayoutGesture().newTab().setCustomView(StyleUtil.getResLayout(R.layout.item_tab_gesture_wind, "item_tab_gesture_wind"));
                TextView textView = (TextView) customView.getCustomView().findViewById(R.id.tv_index);
                textView.setText(this.mSysUiTempUtils.getWindLevelStr(i3));
                if (this.indexWind == i3) {
                    textView.setTextAppearance(StyleUtil.getResStyle(R.style.TabLayoutTextSelected, "TabLayoutTextSelected"));
                } else {
                    textView.setTextAppearance(StyleUtil.getResStyle(R.style.TabLayoutTextUnSelected, "TabLayoutTextUnSelected"));
                }
                GestureDialogImpl.getInstance(this.mContext).getTabLayoutGesture().addTab(customView);
            }
            GestureDialogImpl.getInstance(this.mContext).getTabLayoutGesture().addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() { // from class: com.android.systemui.statusbar.wm.AirPanelController.2
                @Override // com.google.android.material.tabs.TabLayout.BaseOnTabSelectedListener
                public void onTabSelected(TabLayout.Tab tab) {
                    TextView textView2 = (TextView) tab.getCustomView().findViewById(R.id.tv_index);
                    textView2.setTextAppearance(StyleUtil.getResStyle(R.style.TabLayoutTextSelected, "TabLayoutTextSelected"));
                    BydLogHelper.logD("onTabSelected:" + textView2.getText().toString());
                }

                @Override // com.google.android.material.tabs.TabLayout.BaseOnTabSelectedListener
                public void onTabUnselected(TabLayout.Tab tab) {
                    ((TextView) tab.getCustomView().findViewById(R.id.tv_index)).setTextAppearance(StyleUtil.getResStyle(R.style.TabLayoutTextUnSelected, "TabLayoutTextUnSelected"));
                }

                @Override // com.google.android.material.tabs.TabLayout.BaseOnTabSelectedListener
                public void onTabReselected(TabLayout.Tab tab) {
                }
            });
            GestureDialogImpl.getInstance(this.mContext).setInitData(true);
        }
    }

    public void handlerGestureUpEvent() {
        handlerGestureUpEvent(true);
    }

    public void handlerGestureUpEvent(boolean isHandlerShow) {
        if (isHandlerShow) {
            if (GestureDialogImpl.getInstance(this.mContext) != null && GestureDialogImpl.getInstance(this.mContext).isShowing() && GestureDialogImpl.getInstance(this.mContext).isRlWindVisible()) {
                setWind();
                return;
            } else {
                if (GestureDialogImpl.getInstance(this.mContext) != null && GestureDialogImpl.getInstance(this.mContext).isShowing() && GestureDialogImpl.getInstance(this.mContext).isRlTempVisible()) {
                    setTemp();
                    return;
                }
                return;
            }
        }
        if (GestureDialogImpl.getInstance(this.mContext) != null && GestureDialogImpl.getInstance(this.mContext).isRlWindVisible()) {
            setWind();
        } else {
            if (GestureDialogImpl.getInstance(this.mContext) == null || !GestureDialogImpl.getInstance(this.mContext).isRlTempVisible()) {
                return;
            }
            setTemp();
        }
    }

    private void setWind() {
        int selectedTabPosition = GestureDialogImpl.getInstance(this.mContext).getTabLayoutGesture().getSelectedTabPosition() + 1;
        int acWindLevel = this.hvacController.getAcWindLevel();
        BydLogHelper.logD("onTabSelected:" + selectedTabPosition + " , levelCurr:" + acWindLevel);
        if (selectedTabPosition == acWindLevel || selectedTabPosition == 0) {
            return;
        }
        this.hvacController.setAcWindLevel(selectedTabPosition);
    }

    private void setTemp() {
        TempData currentSelectedData = GestureDialogImpl.getInstance(this.mContext).getWheelViewGesture().getCurrentSelectedData();
        if (currentSelectedData != null) {
            BydLogHelper.logD("setTemp key:" + currentSelectedData.getKey() + " , value:" + currentSelectedData.getValue());
            this.hvacController.setFrontLeftTempValue(currentSelectedData.getValue(), this.mUnit);
            if (currentSelectedData.getKey().contains(this.mContext.getString(R.string.sys_ui_temp_hi)) || currentSelectedData.getKey().contains(this.mContext.getString(R.string.sys_ui_temp_low))) {
                GestureDialogImpl.getInstance(this.mContext).getImgDegree().setVisibility(8);
            } else {
                GestureDialogImpl.getInstance(this.mContext).getImgDegree().setVisibility(0);
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void initGestureTempData() {
        BydLogHelper.logD("initGestureTempData unit = " + this.mUnit);
        this.mTempList = this.mSysUiTempUtils.getTempDatas(this.mUnit);
        GestureDialogImpl.getInstance(this.mContext).getWheelViewGesture().setItems(this.mTempList);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void initPopTempData() {
        ArrayList<String> arrayList = this.mPopTempData;
        if (arrayList == null) {
            this.mPopTempData = new ArrayList<>();
        } else {
            arrayList.clear();
        }
        String[] temps = this.mSysUiTempUtils.getTemps(this.mUnit);
        this.mTempDatas = temps;
        for (String str : temps) {
            this.mPopTempData.add(str + "");
        }
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View view) {
        BydLogHelper.logD("onClick", "powerLevel : " + this.hvacController.getPowerLevel());
        if (FastClickUtil.isFastDoubleClick(300L)) {
            BydLogHelper.logD("AirPanelController onClick is fast click");
            return;
        }
        if (view.getId() == R.id.rlDefrost) {
            BydLogHelper.logD("onClick", "rlDefrost");
            updateCustomType(1);
            AirPanelControllerListener airPanelControllerListener = this.iAirPanelControllerListener;
            if (airPanelControllerListener != null) {
                airPanelControllerListener.onCustomTypeChanged(1);
                return;
            }
            return;
        }
        if (view.getId() == R.id.rlCycleMode) {
            BydLogHelper.logD("onClick", "rlCycleMode");
            updateCustomType(2);
            AirPanelControllerListener airPanelControllerListener2 = this.iAirPanelControllerListener;
            if (airPanelControllerListener2 != null) {
                airPanelControllerListener2.onCustomTypeChanged(2);
                return;
            }
            return;
        }
        if (view.getId() == R.id.rlVentilation) {
            BydLogHelper.logD("onClick", "rlVentilation");
            updateCustomType(3);
            AirPanelControllerListener airPanelControllerListener3 = this.iAirPanelControllerListener;
            if (airPanelControllerListener3 != null) {
                airPanelControllerListener3.onCustomTypeChanged(3);
                return;
            }
            return;
        }
        if (view.getId() == R.id.iv_wind_dec) {
            decWindLevel();
            BydLogHelper.logD("onClick", "dec windLevel : " + this.mWindLevel);
            return;
        }
        if (view.getId() == R.id.iv_wind_inc) {
            incWindLevel();
            BydLogHelper.logD("onClick", "inc windLevel : " + this.mWindLevel);
            return;
        }
        if (view.getId() == R.id.iv_set_temp_reduction) {
            BydLogHelper.logD("onClick", "iv_set_temp_reduction");
            setNeedPlayWheelSoundEffect(false);
            reductionTemp();
            return;
        }
        if (view.getId() == R.id.iv_set_temp_rise) {
            BydLogHelper.logD("onClick", "iv_set_temp_rise");
            setNeedPlayWheelSoundEffect(false);
            riseTemp();
            return;
        }
        if (view.getId() == R.id.rl_separate) {
            boolean zIsHvacPower = this.hvacController.isHvacPower();
            int acMaxCoolingState = this.hvacController.getAcMaxCoolingState();
            int acVentilationState = this.hvacController.getAcVentilationState();
            boolean zIsFrontDefrostEnabled = this.hvacController.isFrontDefrostEnabled();
            BydLogHelper.logD("onClick", "img_separate isHvacPower : " + zIsHvacPower + " , acMaxCoolingState : " + acMaxCoolingState + " , acVentilationState : " + acVentilationState + " , isFrontDefrostEnabled : " + zIsFrontDefrostEnabled);
            if (!zIsHvacPower || zIsFrontDefrostEnabled || acMaxCoolingState == 1 || acVentilationState == 1) {
                BydLogHelper.logD("onClick img_separate return");
                return;
            }
            int acTemperatureControlMode = this.hvacController.getAcTemperatureControlMode();
            BydLogHelper.logD("onClick", "img_separate acTemperatureControlMode : " + acTemperatureControlMode);
            if (acTemperatureControlMode == 0) {
                this.hvacController.setAcTemperatureControlMode(1);
                updateAcTemperatureControlMode(1);
            } else {
                this.hvacController.setAcTemperatureControlMode(0);
                updateAcTemperatureControlMode(0);
            }
            this.mAirPanelControllerHandler.removeMessages(258);
            this.mAirPanelControllerHandler.sendEmptyMessageDelayed(258, 2000L);
        }
    }

    public void reductionTemp() {
        BydLogHelper.logD("showCarModelType : " + this.mCarController.getShowCarModelType());
        if (this.mCarController.getShowCarModelType() == 1) {
            showDialog(null, this.mContext.getResources().getString(R.string.in_display_mode_limit_ac_heating), 3);
        }
        BydLogHelper.logD("reductionTempmAcType : " + this.mAcType + ", mCurrPopupWindow : " + this.mCurrPopupWindow);
        int i = this.mAcType;
        if (i == 1) {
            int i2 = this.mCurrPopupWindow;
            if (i2 == 1) {
                BydLogHelper.logD("reductionTemp leftWheelView.getSelectedPosition : " + this.leftWheelView.getSelectedPosition());
                if (this.leftWheelView.getSelectedPosition() > 0) {
                    HorizontalWheelView horizontalWheelView = this.leftWheelView;
                    horizontalWheelView.selectIndex(horizontalWheelView.getSelectedPosition() - 1, true);
                }
                this.mPopupTempLeft.startTimer();
            } else if (i2 == 2) {
                BydLogHelper.logD("reductionTemp rightWheelView.getSelectedPosition : " + this.rightWheelView.getSelectedPosition());
                if (this.rightWheelView.getSelectedPosition() > 0) {
                    HorizontalWheelView horizontalWheelView2 = this.rightWheelView;
                    horizontalWheelView2.selectIndex(horizontalWheelView2.getSelectedPosition() - 1, true);
                }
                this.mPopupTempRight.startTimer();
            }
            setCenterIndex();
            return;
        }
        if (i == 2 && this.mCurrPopupWindow == 5) {
            SeekBar seekBar = this.eaac_pop_seekbar;
            seekBar.setProgress(seekBar.getProgress() - 1);
            this.eaac_pop_seekbar.setThumbTintList(ColorStateList.valueOf(this.mContext.getColor(Constants.TEMP_SCALE_COLOR[this.eaac_pop_seekbar.getProgress() - 17])));
            this.mPopupEaacTemp.startTimer();
        }
    }

    public void riseTemp() {
        BydLogHelper.logD("showCarModelType : " + this.mCarController.getShowCarModelType());
        if (this.mCarController.getShowCarModelType() == 1) {
            showDialog(null, this.mContext.getResources().getString(R.string.in_display_mode_limit_ac_heating), 3);
        }
        BydLogHelper.logD("riseTempmAcType : " + this.mAcType + ", mCurrPopupWindow : " + this.mCurrPopupWindow);
        int i = this.mAcType;
        if (i == 1) {
            int i2 = this.mCurrPopupWindow;
            if (i2 == 1) {
                BydLogHelper.logD("riseTemp leftWheelView.getSelectedPosition : " + this.leftWheelView.getSelectedPosition());
                BydLogHelper.logD("riseTemp leftWheelView.getSize : " + this.leftWheelView.getSize());
                if (this.leftWheelView.getSelectedPosition() < this.leftWheelView.getSize() - 1) {
                    HorizontalWheelView horizontalWheelView = this.leftWheelView;
                    horizontalWheelView.selectIndex(horizontalWheelView.getSelectedPosition() + 1, true);
                }
                this.mPopupTempLeft.startTimer();
            } else if (i2 == 2) {
                BydLogHelper.logD("riseTemp rightWheelView.getSelectedPosition : " + this.rightWheelView.getSelectedPosition());
                if (this.rightWheelView.getSelectedPosition() < this.rightWheelView.getSize() - 1) {
                    HorizontalWheelView horizontalWheelView2 = this.rightWheelView;
                    horizontalWheelView2.selectIndex(horizontalWheelView2.getSelectedPosition() + 1, true);
                }
                this.mPopupTempRight.startTimer();
            }
            setCenterIndex();
            return;
        }
        if (i == 2 && this.mCurrPopupWindow == 5) {
            SeekBar seekBar = this.eaac_pop_seekbar;
            seekBar.setProgress(seekBar.getProgress() + 1);
            this.eaac_pop_seekbar.setThumbTintList(ColorStateList.valueOf(this.mContext.getColor(Constants.TEMP_SCALE_COLOR[this.eaac_pop_seekbar.getProgress() - 17])));
            this.mPopupEaacTemp.startTimer();
        }
    }

    public void setNeedPlayWheelSoundEffect(boolean playWheelSoundEffect) {
        if (this.rightWheelView != null) {
            BydLogHelper.logD("setNeedPlayWheelSoundEffectrightWheelView setNeedPlayWheelSoundEffect : " + playWheelSoundEffect);
            this.rightWheelView.setNeedPlayWheelSoundEffect(playWheelSoundEffect);
        }
        if (this.leftWheelView != null) {
            BydLogHelper.logD("setNeedPlayWheelSoundEffectleftWheelView setNeedPlayWheelSoundEffect : " + playWheelSoundEffect);
            this.leftWheelView.setNeedPlayWheelSoundEffect(playWheelSoundEffect);
        }
    }

    public void setCenterIndex() {
        int i = this.mCurrPopupWindow;
        if (i == 1) {
            this.mPopupTempLeft.startTimer();
        } else if (i == 2) {
            this.mPopupTempRight.startTimer();
        }
    }

    public void decWindLevel() {
        this.mAirPanelControllerHandler.removeMessages(257);
        this.mAirPanelControllerHandler.sendEmptyMessageDelayed(257, 2000L);
        this.mPopupWindLevel.startTimer();
        int i = this.mWindLevel - 1;
        this.mWindLevel = i;
        if (i < 1) {
            this.mWindLevel = i + 1;
        } else if (i <= 7) {
            this.hvacController.setAcWindLevel(i);
            this.mSeekBar.setWindLevel(this.mWindLevel);
            ViewSoundEffectUtil.playWheelSoundEffect(this.mSeekBar);
        }
    }

    public void incWindLevel() {
        this.mAirPanelControllerHandler.removeMessages(257);
        this.mAirPanelControllerHandler.sendEmptyMessageDelayed(257, 2000L);
        this.mPopupWindLevel.startTimer();
        int i = this.mWindLevel + 1;
        this.mWindLevel = i;
        if (i > 7) {
            this.mWindLevel = i - 1;
        } else if (i >= 1) {
            this.hvacController.setAcWindLevel(i);
            this.mSeekBar.setWindLevel(this.mWindLevel);
            ViewSoundEffectUtil.playWheelSoundEffect(this.mSeekBar);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void updateCycleMode(int mode) {
        if (this.iv_loop_mode == null || this.tv_loop_mode == null) {
            return;
        }
        int autoType = this.mCarController.getAutoType();
        if (mode == 0) {
            this.iv_loop_mode.setImageResource(autoType == 143 ? R.mipmap.pa_icon_outside_loop_s : R.mipmap.icon_outside_loop_s);
            this.tv_loop_mode.setText(R.string.outside_loop);
        } else {
            this.iv_loop_mode.setImageResource(autoType == 143 ? R.mipmap.pa_icon_inner_loop_s : R.mipmap.icon_inner_loop_s);
            this.tv_loop_mode.setText(R.string.inner_loop);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void updateAcTemperatureControlMode(int value) {
        BydLogHelper.logD("updateAcTemperatureControlMode value = " + value);
        RelativeLayout relativeLayout = this.iv_left_separate;
        if (relativeLayout != null && this.tvLeftDual != null) {
            if (value == 3) {
                relativeLayout.setVisibility(8);
            } else {
                relativeLayout.setVisibility(0);
                if (value == 1) {
                    this.iv_left_separate.setSelected(true);
                    this.tvLeftDual.setSelectedStatus(true);
                } else if (value == 0) {
                    this.iv_left_separate.setSelected(false);
                    this.tvLeftDual.setSelectedStatus(false);
                }
            }
        }
        RelativeLayout relativeLayout2 = this.iv_right_separate;
        if (relativeLayout2 == null || this.tvRightDual == null) {
            return;
        }
        if (value == 3) {
            relativeLayout2.setVisibility(8);
            return;
        }
        relativeLayout2.setVisibility(0);
        if (value == 1) {
            this.iv_right_separate.setSelected(true);
            this.tvRightDual.setSelectedStatus(true);
        } else if (value == 0) {
            this.iv_right_separate.setSelected(false);
            this.tvRightDual.setSelectedStatus(false);
        }
    }

    private void updateCustomType(int type) {
        BydLogHelper.logD("updateCustomType mCurrPopupWindow = " + this.mCurrPopupWindow);
        if (this.mCurrPopupWindow != 4) {
            return;
        }
        this.radioBtnDefrost.setVisibility(8);
        this.radioBtnCycleMode.setVisibility(8);
        this.radioBtnVentilation.setVisibility(8);
        if (type == 1) {
            this.radioBtnDefrost.setVisibility(0);
        } else if (type == 2) {
            this.radioBtnCycleMode.setVisibility(0);
        } else if (type == 3) {
            this.radioBtnVentilation.setVisibility(0);
        }
        updateCycleMode(this.hvacController.getAcCycleMode());
        setCurrCustomType(type);
    }

    private void updateHvacPower(boolean enable) {
        BydLogHelper.logD("updateHvacPower", "enable : " + enable + ", mCurrPopupWindow : " + this.mCurrPopupWindow);
        int i = this.mCurrPopupWindow;
        if (i == 1) {
            this.iv_left_temp_reduction.setSelected(enable);
            this.iv_left_temp_rise.setSelected(enable);
            this.iv_left_separate.setSelected(enable);
            return;
        }
        if (i == 2) {
            this.iv_right_temp_reduction.setSelected(enable);
            this.iv_right_temp_rise.setSelected(enable);
            this.iv_right_separate.setSelected(enable);
            return;
        }
        if (i == 3) {
            this.iv_wind_dec.setSelected(enable);
            this.iv_wind_inc.setSelected(enable);
            return;
        }
        if (i != 4) {
            if (i == 5) {
                this.iv_set_eaac_temp_reduction.setSelected(enable);
                this.iv_set_eaac_temp_rise.setSelected(enable);
                this.eaac_pop_seekbar.setSelected(enable);
                return;
            }
            return;
        }
        this.imgDefrost.setSelected(enable);
        this.tvDefrost.setSelected(enable);
        this.radioBtnDefrost.setSelected(enable);
        this.iv_loop_mode.setSelected(enable);
        this.tv_loop_mode.setSelected(enable);
        this.radioBtnDefrost.setSelected(enable);
        this.ivVentilation.setSelected(enable);
        this.tvVentilation.setSelected(enable);
        this.radioBtnVentilation.setSelected(enable);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void updateHasMessage(boolean enable) {
        BydLogHelper.logD("updateHasMessage", "enable : " + enable + ", mCurrPopupWindow : " + this.mCurrPopupWindow);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void updateWindLevel(int level) {
        BydLogHelper.logD("updateWindLevel", "level : " + level);
        WindLevelButton windLevelButton = this.mSeekBar;
        if (windLevelButton != null) {
            windLevelButton.setWindLevel(level);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void updateEaacTemp(int value) {
        SeekBar seekBar = this.eaac_pop_seekbar;
        if (seekBar != null) {
            seekBar.setProgress(value);
            if (value < 17 || value > 33) {
                return;
            }
            this.eaac_pop_seekbar.setThumbTintList(ColorStateList.valueOf(this.mContext.getColor(Constants.TEMP_SCALE_COLOR[value - 17])));
        }
    }

    public void dismissDialogOnRecentVisible() {
        BydPopupWindow bydPopupWindow = this.mPopupTempLeft;
        if (bydPopupWindow != null && bydPopupWindow.isShowing()) {
            this.mPopupTempLeft.dismiss();
        }
        BydPopupWindow bydPopupWindow2 = this.mPopupTempRight;
        if (bydPopupWindow2 != null && bydPopupWindow2.isShowing()) {
            this.mPopupTempRight.dismiss();
        }
        BydPopupWindow bydPopupWindow3 = this.mPopupCustom;
        if (bydPopupWindow3 != null && bydPopupWindow3.isShowing()) {
            this.mPopupCustom.dismiss();
        }
        BydPopupWindow bydPopupWindow4 = this.mPopupEaacTemp;
        if (bydPopupWindow4 != null && bydPopupWindow4.isShowing()) {
            this.mPopupEaacTemp.dismiss();
        }
        BydPopupWindow bydPopupWindow5 = this.mPopupWindLevel;
        if (bydPopupWindow5 != null && bydPopupWindow5.isShowing()) {
            this.mPopupWindLevel.dismiss();
        }
        GestureDialogImpl.getInstance(this.mContext).dismiss();
    }

    public void showWindLevel(View parent) {
        BydPopupWindow bydPopupWindow = this.mPopupTempLeft;
        if (bydPopupWindow != null && bydPopupWindow.isShowing()) {
            this.mPopupTempLeft.dismiss();
        }
        BydPopupWindow bydPopupWindow2 = this.mPopupTempRight;
        if (bydPopupWindow2 != null && bydPopupWindow2.isShowing()) {
            this.mPopupTempRight.dismiss();
        }
        BydPopupWindow bydPopupWindow3 = this.mPopupCustom;
        if (bydPopupWindow3 != null && bydPopupWindow3.isShowing()) {
            this.mPopupCustom.dismiss();
        }
        BydPopupWindow bydPopupWindow4 = this.mPopupEaacTemp;
        if (bydPopupWindow4 != null && bydPopupWindow4.isShowing()) {
            this.mPopupEaacTemp.dismiss();
        }
        if (this.hvacController == null || this.mCarController == null) {
            BydLogHelper.logD("showWindLevel init hvacController or mCarController unsuccessful ,return !");
            return;
        }
        if (this.mPopupWindLevel == null) {
            this.mViewWindLevel = LayoutInflater.from(parent.getContext()).inflate(R.layout.view_wind_level_set, (ViewGroup) null);
            this.mPopupWindLevel = new BydPopupWindow(this.mViewWindLevel, (int) this.mContext.getResources().getDimension(R.dimen.dp_454), (int) this.mContext.getResources().getDimension(R.dimen.dp_90), true);
            this.iv_wind_dec = (BydImageView) this.mViewWindLevel.findViewById(R.id.iv_wind_dec);
            this.iv_wind_inc = (BydImageView) this.mViewWindLevel.findViewById(R.id.iv_wind_inc);
            this.mSeekBar = (WindLevelButton) this.mViewWindLevel.findViewById(R.id.wind_level_pop_seekbar);
            this.iv_wind_dec.setOnClickListener(this);
            this.iv_wind_inc.setOnClickListener(this);
            this.mSeekBar.setOnWindLevelViewChangeListener(new WindLevelButton.OnWindLevelChangeListener() { // from class: com.android.systemui.statusbar.wm.AirPanelController.3
                @Override // com.android.systemui.widget.WindLevelButton.OnWindLevelChangeListener
                public void onChanged(int i) {
                    BydLogHelper.logD("onChanged", "i:" + i);
                    ViewSoundEffectUtil.playWheelSoundEffect(AirPanelController.this.mSeekBar);
                }

                @Override // com.android.systemui.widget.WindLevelButton.OnWindLevelChangeListener
                public void onTouchDown(int i) {
                    BydLogHelper.logD("onTouchDown", "i:" + i);
                    ViewSoundEffectUtil.playWheelSoundEffect(AirPanelController.this.mSeekBar);
                }

                @Override // com.android.systemui.widget.WindLevelButton.OnWindLevelChangeListener
                public void onTouchUp(int i) {
                    BydLogHelper.logD("onTouchUp", "i:" + i);
                    AirPanelController.this.hvacController.setAcWindLevel(i);
                    AirPanelController.this.mAirPanelControllerHandler.removeMessages(257);
                    AirPanelController.this.mAirPanelControllerHandler.sendEmptyMessageDelayed(257, 2000L);
                }
            });
        }
        setCurrPopupWindow(3);
        this.mWindLevel = this.hvacController.getAcWindLevel();
        BydLogHelper.logD("showWindLevel", "mWindLevel = " + this.mWindLevel);
        updateWindLevel(this.mWindLevel);
        int iHasMessage = this.mCarController.hasMessage(59);
        BydLogHelper.logD("showWindLevel", "hasMessage = " + iHasMessage);
        updateHasMessage(iHasMessage == 1);
        showPopupWindow(parent, this.mViewWindLevel, this.mPopupWindLevel);
        Bitmap screenshot = BlurUtil.getScreenshot(this.mContext, this.mPopupX, this.mPopupY, this.mPopupWight, this.mPopupHeight);
        BlurView blurView = (BlurView) this.mViewWindLevel.findViewById(R.id.blurView);
        blurView.setBlurViewRadius(this.mContext.getResources().getDimensionPixelOffset(R.dimen.dp_12));
        blurView.setBlurView(screenshot);
    }

    public void showPopCustomView(View parent) {
        BydPopupWindow bydPopupWindow = this.mPopupTempLeft;
        if (bydPopupWindow != null && bydPopupWindow.isShowing()) {
            this.mPopupTempLeft.dismiss();
        }
        BydPopupWindow bydPopupWindow2 = this.mPopupTempRight;
        if (bydPopupWindow2 != null && bydPopupWindow2.isShowing()) {
            this.mPopupTempRight.dismiss();
        }
        BydPopupWindow bydPopupWindow3 = this.mPopupWindLevel;
        if (bydPopupWindow3 != null && bydPopupWindow3.isShowing()) {
            this.mPopupWindLevel.dismiss();
        }
        if (this.hvacController == null || this.mCarController == null) {
            BydLogHelper.logD("showPopCustomView init hvacController or mCarController unsuccessful ,return !");
            return;
        }
        if (this.mPopupCustom == null) {
            this.mViewCustom = LayoutInflater.from(parent.getContext()).inflate(R.layout.view_pop_custom, (ViewGroup) null);
            this.mPopupCustom = new BydPopupWindow(this.mViewCustom, -2, -2, true);
            this.rlDefrost = (RelativeLayout) this.mViewCustom.findViewById(R.id.rlDefrost);
            this.rlCycleMode = (RelativeLayout) this.mViewCustom.findViewById(R.id.rlCycleMode);
            this.rlVentilation = (RelativeLayout) this.mViewCustom.findViewById(R.id.rlVentilation);
            this.iv_loop_mode = (BydImageView) this.mViewCustom.findViewById(R.id.iv_loop_mode);
            this.tv_loop_mode = (TextView) this.mViewCustom.findViewById(R.id.tv_loop_mode);
            this.radioBtnCycleMode = (RadioButton) this.mViewCustom.findViewById(R.id.radioBtnCycleMode);
            this.tvDefrost = (TextView) this.mViewCustom.findViewById(R.id.tvDefrost);
            this.imgDefrost = (BydImageView) this.mViewCustom.findViewById(R.id.imgDefrost);
            this.radioBtnDefrost = (RadioButton) this.mViewCustom.findViewById(R.id.radioBtnDefrost);
            this.ivVentilation = (BydImageView) this.mViewCustom.findViewById(R.id.ivVentilation);
            this.tvVentilation = (TextView) this.mViewCustom.findViewById(R.id.tvVentilation);
            this.radioBtnVentilation = (RadioButton) this.mViewCustom.findViewById(R.id.radioBtnVentilation);
            this.rlDefrost.setOnClickListener(this);
            this.rlCycleMode.setOnClickListener(this);
            this.rlVentilation.setOnClickListener(this);
        }
        setCurrPopupWindow(4);
        this.mCurrCustomType = SharedPreferencesUtil.getInt(this.mContext, CUSTOM_TYPE, 1);
        BydLogHelper.logD("showPopCustomView", "mCurrCustomType = " + this.mCurrCustomType);
        updateCustomType(this.mCurrCustomType);
        int iHasMessage = this.mCarController.hasMessage(59);
        BydLogHelper.logD("showPopCustomView", "hasMessage = " + iHasMessage);
        updateHasMessage(iHasMessage == 1);
        showPopupWindow(parent, this.mViewCustom, this.mPopupCustom);
        Bitmap screenshot = BlurUtil.getScreenshot(this.mContext, this.mPopupX, this.mPopupY, this.mPopupWight, this.mPopupHeight);
        BlurView blurView = (BlurView) this.mViewCustom.findViewById(R.id.blurView);
        blurView.setBlurViewRadius(this.mContext.getResources().getDimensionPixelOffset(R.dimen.dp_12));
        blurView.setBlurView(screenshot);
    }

    public void showPopTemp(View parent, boolean isLeft) {
        BydPopupWindow bydPopupWindow = this.mPopupCustom;
        if (bydPopupWindow != null && bydPopupWindow.isShowing()) {
            this.mPopupCustom.dismiss();
        }
        BydPopupWindow bydPopupWindow2 = this.mPopupWindLevel;
        if (bydPopupWindow2 != null && bydPopupWindow2.isShowing()) {
            this.mPopupWindLevel.dismiss();
        }
        if (this.hvacController == null || this.mCarController == null) {
            BydLogHelper.logD("showPopTemp init hvacController or mCarController unsuccessful ,return !");
            return;
        }
        if (isLeft) {
            BydPopupWindow bydPopupWindow3 = this.mPopupTempRight;
            if (bydPopupWindow3 != null && bydPopupWindow3.isShowing()) {
                this.mPopupTempRight.dismiss();
            }
            if (this.mPopupTempLeft == null) {
                this.mViewTempLeft = LayoutInflater.from(parent.getContext()).inflate(R.layout.view_double_temp_set, (ViewGroup) null);
                this.mPopupTempLeft = new BydPopupWindow(this.mViewTempLeft, -2, -2, true);
                HorizontalWheelView horizontalWheelView = (HorizontalWheelView) this.mViewTempLeft.findViewById(R.id.wheel_view);
                this.leftWheelView = horizontalWheelView;
                setWheelViewListener(horizontalWheelView);
                this.iv_left_temp_reduction = (BydImageView) this.mViewTempLeft.findViewById(R.id.iv_set_temp_reduction);
                this.iv_left_temp_rise = (BydImageView) this.mViewTempLeft.findViewById(R.id.iv_set_temp_rise);
                this.tvLeftDual = (BydImageView) this.mViewTempLeft.findViewById(R.id.tvDual);
                this.iv_left_separate = (RelativeLayout) this.mViewTempLeft.findViewById(R.id.rl_separate);
                this.iv_left_temp_reduction.setOnClickListener(this);
                this.iv_left_temp_rise.setOnClickListener(this);
                this.iv_left_separate.setOnClickListener(this);
            }
            initPopTempData();
            resetTempViewTextSize(this.leftWheelView);
            this.leftWheelView.setItems(this.mPopTempData);
            setCurrPopupWindow(1);
            int frontLeftTempValue = this.hvacController.getFrontLeftTempValue();
            BydLogHelper.logD("showPopTemp", "leftTempValue = " + frontLeftTempValue);
            this.leftWheelView.selectIndex(getIndexByTemp(frontLeftTempValue), false);
            int acTemperatureControlMode = this.hvacController.getAcTemperatureControlMode();
            BydLogHelper.logD("showPopTemp", "acTemperatureControlMode = " + acTemperatureControlMode);
            updateAcTemperatureControlMode(acTemperatureControlMode);
            int iHasMessage = this.mCarController.hasMessage(59);
            BydLogHelper.logD("showPopTemp", "hasMessage = " + iHasMessage);
            updateHasMessage(iHasMessage == 1);
            showPopupWindow(parent, this.mViewTempLeft, this.mPopupTempLeft);
            Bitmap screenshot = BlurUtil.getScreenshot(this.mContext, this.mPopupX, this.mPopupY, this.mPopupWight, this.mPopupHeight);
            BlurView blurView = (BlurView) this.mViewTempLeft.findViewById(R.id.blurView);
            blurView.setBlurViewRadius(this.mContext.getResources().getDimensionPixelOffset(R.dimen.dp_12));
            blurView.setBlurView(screenshot);
            return;
        }
        BydPopupWindow bydPopupWindow4 = this.mPopupTempLeft;
        if (bydPopupWindow4 != null && bydPopupWindow4.isShowing()) {
            this.mPopupTempLeft.dismiss();
        }
        if (this.mPopupTempRight == null) {
            this.mViewTempRight = LayoutInflater.from(parent.getContext()).inflate(R.layout.view_double_temp_set, (ViewGroup) null);
            this.mPopupTempRight = new BydPopupWindow(this.mViewTempRight, -2, -2, true);
            HorizontalWheelView horizontalWheelView2 = (HorizontalWheelView) this.mViewTempRight.findViewById(R.id.wheel_view);
            this.rightWheelView = horizontalWheelView2;
            setWheelViewListener(horizontalWheelView2);
            this.iv_right_temp_reduction = (BydImageView) this.mViewTempRight.findViewById(R.id.iv_set_temp_reduction);
            this.iv_right_temp_rise = (BydImageView) this.mViewTempRight.findViewById(R.id.iv_set_temp_rise);
            this.tvRightDual = (BydImageView) this.mViewTempRight.findViewById(R.id.tvDual);
            this.iv_right_separate = (RelativeLayout) this.mViewTempRight.findViewById(R.id.rl_separate);
            this.iv_right_temp_reduction.setOnClickListener(this);
            this.iv_right_temp_rise.setOnClickListener(this);
            this.iv_right_separate.setOnClickListener(this);
        }
        initPopTempData();
        resetTempViewTextSize(this.rightWheelView);
        this.rightWheelView.setItems(this.mPopTempData);
        setCurrPopupWindow(2);
        int frontRightTempValue = this.hvacController.getFrontRightTempValue();
        BydLogHelper.logD("showPopTemp", "rightTempValue = " + frontRightTempValue);
        this.rightWheelView.selectIndex(getIndexByTemp(frontRightTempValue), false);
        int acTemperatureControlMode2 = this.hvacController.getAcTemperatureControlMode();
        BydLogHelper.logD("showPopTemp", "acTemperatureControlMode = " + acTemperatureControlMode2);
        updateAcTemperatureControlMode(acTemperatureControlMode2);
        int iHasMessage2 = this.mCarController.hasMessage(59);
        BydLogHelper.logD("showPopTemp", "hasMessage = " + iHasMessage2);
        updateHasMessage(iHasMessage2 == 1);
        showPopupWindow(parent, this.mViewTempRight, this.mPopupTempRight);
        Bitmap screenshot2 = BlurUtil.getScreenshot(this.mContext, this.mPopupX, this.mPopupY, this.mPopupWight, this.mPopupHeight);
        BlurView blurView2 = (BlurView) this.mViewTempRight.findViewById(R.id.blurView);
        blurView2.setBlurViewRadius(this.mContext.getResources().getDimensionPixelOffset(R.dimen.dp_12));
        blurView2.setBlurView(screenshot2);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void resetTempViewTextSize(HorizontalWheelView view) {
        CarController carController;
        if (view == null || (carController = this.mCarController) == null) {
            return;
        }
        if (carController.getTempRegulatingAccuracy() == 2) {
            view.setIntervalFactor(1.0f);
            view.setTempAccuracyType(2);
            view.setNormalTextSize(this.mResources.getDimensionPixelSize(R.dimen.dp_27));
            view.setCenterTextSize(this.mResources.getDimensionPixelSize(R.dimen.dp_45));
            return;
        }
        view.setIntervalFactor(1.2f);
        view.setTempAccuracyType(1);
        view.setNormalTextSize(this.mResources.getDimensionPixelSize(R.dimen.dp_32));
        view.setCenterTextSize(this.mResources.getDimensionPixelSize(R.dimen.dp_60));
    }

    public void showEAACPopTemp(View parent) {
        BydPopupWindow bydPopupWindow = this.mPopupWindLevel;
        if (bydPopupWindow != null && bydPopupWindow.isShowing()) {
            this.mPopupWindLevel.dismiss();
        }
        if (this.hvacController == null || this.mCarController == null) {
            BydLogHelper.logD("showEAACPopTemp init hvacController or mCarController unsuccessful ,return !");
            return;
        }
        if (this.mPopupEaacTemp == null) {
            this.mViewEaacTemp = LayoutInflater.from(parent.getContext()).inflate(R.layout.view_eaac_temp_set, (ViewGroup) null);
            this.mPopupEaacTemp = new BydPopupWindow(this.mViewEaacTemp, -2, -2, true);
            this.iv_set_eaac_temp_reduction = (BydImageView) this.mViewEaacTemp.findViewById(R.id.iv_set_temp_reduction);
            this.iv_set_eaac_temp_rise = (BydImageView) this.mViewEaacTemp.findViewById(R.id.iv_set_temp_rise);
            this.eaac_pop_seekbar = (SeekBar) this.mViewEaacTemp.findViewById(R.id.eaac_pop_seekbar);
            this.iv_set_eaac_temp_reduction.setOnClickListener(this);
            this.iv_set_eaac_temp_rise.setOnClickListener(this);
            this.eaac_pop_seekbar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() { // from class: com.android.systemui.statusbar.wm.AirPanelController.4
                @Override // android.widget.SeekBar.OnSeekBarChangeListener
                public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                    BydLogHelper.logD("onProgressChanged", "progress:" + progress + " , fromUser:" + fromUser + ", mCurrPopupWindow:" + AirPanelController.this.mCurrPopupWindow);
                    AirPanelController.this.eaac_pop_seekbar.setThumbTintList(ColorStateList.valueOf(AirPanelController.this.mContext.getColor(Constants.TEMP_SCALE_COLOR[progress - 17])));
                    int frontLeftTempValue = AirPanelController.this.hvacController.getFrontLeftTempValue();
                    BydLogHelper.logD("leftTempValue : " + frontLeftTempValue);
                    if (progress != frontLeftTempValue) {
                        AirPanelController.this.hvacController.setFrontLeftTempValue(progress, AirPanelController.this.mUnit);
                    }
                    if (AirPanelController.this.iAirPanelControllerListener != null) {
                        AirPanelController.this.iAirPanelControllerListener.onTemperatureChanged(1, progress);
                    }
                    AirPanelController.this.mAirPanelControllerHandler.removeMessages(259);
                    AirPanelController.this.mAirPanelControllerHandler.sendEmptyMessageDelayed(259, 2000L);
                }

                @Override // android.widget.SeekBar.OnSeekBarChangeListener
                public void onStartTrackingTouch(SeekBar seekBar) {
                    BydLogHelper.logD("onStartTrackingTouch", "progress:" + seekBar.getProgress());
                }

                @Override // android.widget.SeekBar.OnSeekBarChangeListener
                public void onStopTrackingTouch(SeekBar seekBar) {
                    BydLogHelper.logD("onStopTrackingTouch", "progress:" + seekBar.getProgress());
                    if (AirPanelController.this.mCurrPopupWindow == 5) {
                        AirPanelController.this.hvacController.setFrontLeftTempValue(seekBar.getProgress(), 1);
                        AirPanelController.this.mAirPanelControllerHandler.removeMessages(259);
                        AirPanelController.this.mAirPanelControllerHandler.sendEmptyMessageDelayed(259, 2000L);
                    }
                }
            });
        }
        setCurrPopupWindow(5);
        int frontLeftTempValue = this.hvacController.getFrontLeftTempValue();
        BydLogHelper.logD("showEAACPopTemp", "frontLeftTempValue = " + frontLeftTempValue);
        updateEaacTemp(frontLeftTempValue);
        int iHasMessage = this.mCarController.hasMessage(59);
        BydLogHelper.logD("showEAACPopTemp", "hasMessage = " + iHasMessage);
        updateHasMessage(iHasMessage == 1);
        showPopupWindow(parent, this.mViewEaacTemp, this.mPopupEaacTemp);
        Bitmap screenshot = BlurUtil.getScreenshot(this.mContext, this.mPopupX, this.mPopupY, this.mPopupWight, this.mPopupHeight);
        BlurView blurView = (BlurView) this.mViewEaacTemp.findViewById(R.id.blurView);
        blurView.setBlurViewRadius(this.mContext.getResources().getDimensionPixelOffset(R.dimen.dp_12));
        blurView.setBlurView(screenshot);
    }

    private void showPopupWindow(View parent, View view, BydPopupWindow popupWindow) {
        view.measure(0, 0);
        int measuredWidth = view.getMeasuredWidth();
        int measuredHeight = view.getMeasuredHeight();
        int[] iArr = new int[2];
        parent.getLocationOnScreen(iArr);
        int width = parent.getWidth();
        int i = (iArr[0] + (width / 2)) - (measuredWidth / 2);
        BydLogHelper.logD("offX:" + i + " , popupWidth:" + measuredWidth + " , parentWidth:" + width);
        int[] iArr2 = new int[2];
        this.statusBar.getNavigationBarView().getLocationOnScreen(iArr2);
        int dimensionPixelSize = this.mContext.getResources().getDimensionPixelSize(R.dimen.margin_bottom_navigation_bar_on_air_set);
        int i2 = (iArr2[1] - measuredHeight) - dimensionPixelSize;
        BydLogHelper.logD("offY:" + i2 + " , popupHeight:" + measuredHeight + " , marginBottom:" + dimensionPixelSize);
        popupWindow.showAtLocation(this.statusBar.getStatusBarWindow(), 0, i, i2);
        this.mPopupX = i;
        this.mPopupY = i2;
        this.mPopupWight = measuredWidth;
        this.mPopupHeight = measuredHeight;
        popupWindow.setOnDismissListener(this.mDismissListener);
        this.isShowPopupWindow = true;
    }

    public void destroyView() {
        if (this.mPopupTempLeft != null) {
            BydLogHelper.logD("destroyView mPopupTempLeft != null " + this.mPopupTempLeft.isShowing());
            this.mPopupTempLeft.dismiss();
            this.mPopupTempLeft = null;
        }
        if (this.mPopupTempRight != null) {
            BydLogHelper.logD("destroyView mPopupTempRight != null " + this.mPopupTempRight.isShowing());
            this.mPopupTempRight.dismiss();
            this.mPopupTempRight = null;
        }
        if (this.mPopupCustom != null) {
            BydLogHelper.logD("destroyView mPopupCustom != null " + this.mPopupCustom.isShowing());
            this.mPopupCustom.dismiss();
            this.mPopupCustom = null;
        }
        if (this.mPopupWindLevel != null) {
            BydLogHelper.logD("destroyView mPopupWindLevel != null " + this.mPopupWindLevel.isShowing());
            this.mPopupWindLevel.dismiss();
            this.mPopupWindLevel = null;
        }
        if (this.mPopupEaacTemp != null) {
            BydLogHelper.logD("destroyView mPopupEaacTemp != null " + this.mPopupEaacTemp.isShowing());
            this.mPopupEaacTemp.dismiss();
            this.mPopupEaacTemp = null;
        }
        if (this.mViewTempLeft != null) {
            this.mViewTempLeft = null;
        }
        if (this.mViewTempRight != null) {
            this.mViewTempRight = null;
        }
        if (this.mViewCustom != null) {
            this.mViewCustom = null;
        }
        if (this.mViewWindLevel != null) {
            this.mViewWindLevel = null;
        }
        if (this.mViewEaacTemp != null) {
            this.mViewEaacTemp = null;
        }
    }

    public int getCurrCustomType() {
        return this.mCurrCustomType;
    }

    public void setCurrCustomType(int customType) {
        this.mCurrCustomType = customType;
        SharedPreferencesUtil.putInt(this.mContext, CUSTOM_TYPE, customType);
    }

    public int getCurrPopupWindow() {
        return this.mCurrPopupWindow;
    }

    public void setCurrPopupWindow(int currPopupWindow) {
        this.mCurrPopupWindow = currPopupWindow;
    }

    public int getLastPopupWindow() {
        return this.mLastPopupWindow;
    }

    private int getTempIndex() {
        int frontLeftTempValue = this.hvacController.getFrontLeftTempValue();
        int[] tempsKeyReverse = SysUiTempUtils.getInstance().getTempsKeyReverse(this.mUnit);
        if (tempsKeyReverse == null || tempsKeyReverse.length <= 0) {
            return 0;
        }
        for (int i = 0; i < tempsKeyReverse.length; i++) {
            if (frontLeftTempValue == tempsKeyReverse[i]) {
                BydLogHelper.logD("getTempIndex: " + i);
                return i;
            }
        }
        BydLogHelper.logD("getTempIndex temp isn't in list");
        if (frontLeftTempValue > tempsKeyReverse[0]) {
            BydLogHelper.logD("getTempIndex: HI");
            return 0;
        }
        BydLogHelper.logD("getTempIndex: LO");
        return tempsKeyReverse.length - 1;
    }

    public void setGestureTempIndex(boolean isDown) {
        BydLogHelper.logD("setGestureTempIndex isDown = " + isDown);
        if (GestureDialogImpl.getInstance(this.mContext) != null && GestureDialogImpl.getInstance(this.mContext).isShowing() && GestureDialogImpl.getInstance(this.mContext).isRlTempVisible()) {
            if (isDown) {
                if (this.indexTemp >= this.mTempList.size() - 1) {
                    return;
                } else {
                    this.indexTemp++;
                }
            } else {
                int i = this.indexTemp;
                if (i <= 0) {
                    return;
                } else {
                    this.indexTemp = i - 1;
                }
            }
            if (GestureDialogImpl.getInstance(this.mContext).getWheelViewGesture().getItemCount() > this.indexTemp) {
                BydLogHelper.logD("setGestureTempIndex indexTemp = " + this.indexTemp);
                GestureDialogImpl.getInstance(this.mContext).getWheelViewGesture().setSelected(this.indexTemp);
                ViewSoundEffectUtil.playWheelSoundEffect(GestureDialogImpl.getInstance(this.mContext).getWheelViewGesture());
            }
        }
    }

    public void setGestureWindIndex(boolean isUp) {
        if (GestureDialogImpl.getInstance(this.mContext) != null && GestureDialogImpl.getInstance(this.mContext).isShowing() && GestureDialogImpl.getInstance(this.mContext).isRlWindVisible()) {
            if (isUp) {
                if (this.indexWind >= this.mSysUiTempUtils.getWindLevelSize() - 1) {
                    return;
                } else {
                    this.indexWind++;
                }
            } else {
                int i = this.indexWind;
                if (i <= 0) {
                    return;
                } else {
                    this.indexWind = i - 1;
                }
            }
            int tabCount = GestureDialogImpl.getInstance(this.mContext).getTabLayoutGesture().getTabCount();
            BydLogHelper.logD("count = " + tabCount + " ,indexWind = " + this.indexWind);
            if (tabCount <= this.indexWind || GestureDialogImpl.getInstance(this.mContext).getTabLayoutGesture().getTabAt(this.indexWind) == null) {
                return;
            }
            GestureDialogImpl.getInstance(this.mContext).getTabLayoutGesture().getTabAt(this.indexWind).select();
            ViewSoundEffectUtil.playWheelSoundEffect(GestureDialogImpl.getInstance(this.mContext).getTabLayoutGesture());
        }
    }

    public void showGestureTempView() {
        if (this.hvacController == null) {
            this.hvacController = (HvacController) Dependency.get(HvacController.class);
        }
        if (!this.hvacController.isHvacPower()) {
            this.hvacController.setHvacPower(true);
        }
        if (GestureDialogImpl.getInstance(this.mContext) == null) {
            BydLogHelper.logD("mGestureViewDialog is null");
            return;
        }
        if (!GestureDialogImpl.getInstance(this.mContext).isShowing()) {
            initGestureView();
            GestureDialogImpl.getInstance(this.mContext).show();
        }
        if (GestureDialogImpl.getInstance(this.mContext).isRlWindVisible()) {
            GestureDialogImpl.getInstance(this.mContext).setRlWindVisible(false);
        }
        if (GestureDialogImpl.getInstance(this.mContext).isRlTempVisible()) {
            return;
        }
        GestureDialogImpl.getInstance(this.mContext).setRlTempVisible(true);
        int tempIndex = getTempIndex();
        BydLogHelper.logD("selectedIndex:" + tempIndex);
        this.indexTemp = tempIndex;
        if (GestureDialogImpl.getInstance(this.mContext).getWheelViewGesture().getCurrentSelectedIndex() != this.indexTemp) {
            int itemCount = GestureDialogImpl.getInstance(this.mContext).getWheelViewGesture().getItemCount();
            int i = this.indexTemp;
            if (itemCount > i && i >= 0) {
                GestureDialogImpl.getInstance(this.mContext).getWheelViewGesture().setSelected(this.indexTemp);
            }
        }
        int i2 = this.mUnit;
        if (i2 == 1) {
            GestureDialogImpl.getInstance(this.mContext).getImgDegree().setImageResource(StyleUtil.getResMipmap(R.mipmap.img_gesture_ac_temperature_unit, "img_gesture_ac_temperature_unit"));
        } else if (i2 == 0) {
            GestureDialogImpl.getInstance(this.mContext).getImgDegree().setImageResource(StyleUtil.getResMipmap(R.mipmap.img_gesture_ac_temperature_unit_fahrenheit, "img_gesture_ac_temperature_unit_fahrenheit"));
        }
        if (tempIndex == 0 || tempIndex == this.mTempList.size() - 1) {
            GestureDialogImpl.getInstance(this.mContext).getImgDegree().setVisibility(8);
        } else {
            GestureDialogImpl.getInstance(this.mContext).getImgDegree().setVisibility(0);
        }
    }

    public void showGestureWindView() {
        if (this.hvacController == null) {
            this.hvacController = (HvacController) Dependency.get(HvacController.class);
        }
        if (!this.hvacController.isHvacPower()) {
            this.hvacController.setHvacPower(true);
        }
        if (GestureDialogImpl.getInstance(this.mContext) == null) {
            BydLogHelper.logD("mGestureViewDialog is null");
            return;
        }
        if (!GestureDialogImpl.getInstance(this.mContext).isShowing()) {
            initGestureView();
            GestureDialogImpl.getInstance(this.mContext).show();
        }
        if (GestureDialogImpl.getInstance(this.mContext).isRlTempVisible()) {
            GestureDialogImpl.getInstance(this.mContext).setRlTempVisible(false);
        }
        if (GestureDialogImpl.getInstance(this.mContext).isRlWindVisible()) {
            return;
        }
        GestureDialogImpl.getInstance(this.mContext).setRlWindVisible(true);
        int acWindLevel = this.hvacController.getAcWindLevel();
        BydLogHelper.logD("level:" + acWindLevel);
        int selectedTabPosition = GestureDialogImpl.getInstance(this.mContext).getTabLayoutGesture().getSelectedTabPosition();
        if (acWindLevel <= 0) {
            this.indexWind = 0;
            BydLogHelper.logD("showGestureWindView invalid indexWind:" + this.indexWind);
        } else {
            this.indexWind = acWindLevel - 1;
        }
        if (selectedTabPosition != this.indexWind) {
            int tabCount = GestureDialogImpl.getInstance(this.mContext).getTabLayoutGesture().getTabCount();
            int i = this.indexWind;
            if (tabCount <= i || i < 0) {
                return;
            }
            GestureDialogImpl.getInstance(this.mContext).getTabLayoutGesture().selectTab(GestureDialogImpl.getInstance(this.mContext).getTabLayoutGesture().getTabAt(this.indexWind));
        }
    }

    private void setWheelViewListener(HorizontalWheelView wheelView) {
        wheelView.setOnWheelItemSelectedListener(new HorizontalWheelView.OnWheelItemSelectedListener() { // from class: com.android.systemui.statusbar.wm.AirPanelController.5
            @Override // com.android.systemui.widget.HorizontalWheelView.OnWheelItemSelectedListener
            public void onWheelItemChanged(HorizontalWheelView horizontalWheelView, int position) {
                int tempByIndex = AirPanelController.this.getTempByIndex(position);
                BydLogHelper.logD("onWheelItemChanged", "position : " + position + ", value : " + tempByIndex);
                if (AirPanelController.this.mCurrPopupWindow == 1) {
                    int frontLeftTempValue = AirPanelController.this.hvacController.getFrontLeftTempValue();
                    BydLogHelper.logD("leftTempValue : " + frontLeftTempValue);
                    if (tempByIndex != frontLeftTempValue) {
                        AirPanelController.this.hvacController.setFrontLeftTempValue(tempByIndex, AirPanelController.this.mUnit);
                    }
                    if (AirPanelController.this.iAirPanelControllerListener != null) {
                        AirPanelController.this.iAirPanelControllerListener.onTemperatureChanged(1, tempByIndex);
                    }
                    AirPanelController.this.mAirPanelControllerHandler.removeMessages(259);
                    AirPanelController.this.mAirPanelControllerHandler.sendEmptyMessageDelayed(259, 2000L);
                    return;
                }
                if (AirPanelController.this.mCurrPopupWindow == 2) {
                    int frontRightTempValue = AirPanelController.this.hvacController.getFrontRightTempValue();
                    BydLogHelper.logD("rightTempValue : " + frontRightTempValue);
                    if (tempByIndex != frontRightTempValue) {
                        AirPanelController.this.hvacController.setFrontRightTempValue(tempByIndex, AirPanelController.this.mUnit);
                    }
                    if (AirPanelController.this.iAirPanelControllerListener != null) {
                        AirPanelController.this.iAirPanelControllerListener.onTemperatureChanged(2, tempByIndex);
                    }
                    AirPanelController.this.mAirPanelControllerHandler.removeMessages(260);
                    AirPanelController.this.mAirPanelControllerHandler.sendEmptyMessageDelayed(260, 2000L);
                }
            }

            @Override // com.android.systemui.widget.HorizontalWheelView.OnWheelItemSelectedListener
            public void onWheelItemSelected(HorizontalWheelView horizontalWheelView, int position) {
                BydLogHelper.logD("onWheelItemSelected", "position : " + position + ", value : " + AirPanelController.this.getTempByIndex(position));
            }

            @Override // com.android.systemui.widget.HorizontalWheelView.OnWheelItemSelectedListener
            public void onShowDialog() {
                BydLogHelper.logD("showCarModelType : " + AirPanelController.this.mCarController.getShowCarModelType());
                if (AirPanelController.this.mCarController.getShowCarModelType() == 1) {
                    AirPanelController airPanelController = AirPanelController.this;
                    airPanelController.showDialog(null, airPanelController.mContext.getResources().getString(R.string.in_display_mode_limit_ac_heating), 3);
                }
            }
        });
    }

    public int getIndexByTemp(int temp) {
        int[] tempsKey = this.mSysUiTempUtils.getTempsKey(this.mUnit);
        if (tempsKey != null) {
            for (int i = 0; i < tempsKey.length; i++) {
                if (temp == tempsKey[i]) {
                    BydLogHelper.logD("getTempByIndex  i : " + i + ", tempsKey[i] : " + tempsKey[i]);
                    return i;
                }
            }
        }
        BydLogHelper.logD("getTempByIndex  temp : " + temp);
        return 0;
    }

    public int getTempByIndex(int index) {
        int[] tempsKey = this.mSysUiTempUtils.getTempsKey(this.mUnit);
        int i = (tempsKey == null || tempsKey.length <= 0 || index >= tempsKey.length) ? 0 : tempsKey[index];
        BydLogHelper.logD("getTempByIndex  tempValue : " + i + ", index : " + index);
        return i;
    }

    /* JADX INFO: renamed from: com.android.systemui.statusbar.wm.AirPanelController$6, reason: invalid class name */
    class AnonymousClass6 implements HvacController.Callback {
        static /* synthetic */ void lambda$onMaxCoolingStateChanged$1() {
        }

        @Override // com.android.systemui.statusbar.policy.HvacController.Callback
        public void onPowerLevelChanged(int level) {
        }

        AnonymousClass6() {
        }

        @Override // com.android.systemui.statusbar.policy.HvacController.Callback
        public void onFrontLeftTempChanged(int value) {
            BydLogHelper.logD("onFrontLeftTempChanged", "value : " + value + ", mAcType : " + AirPanelController.this.mAcType);
            if (AirPanelController.this.mAcType != 2) {
                if (AirPanelController.this.mAcType == 1 && AirPanelController.this.leftWheelView != null && AirPanelController.this.leftWheelView.isShown()) {
                    AirPanelController.this.leftWheelView.setNeedPlayWheelSoundEffect(false);
                    if (AirPanelController.this.mAirPanelControllerHandler.hasMessages(259)) {
                        return;
                    }
                    AirPanelController.this.leftWheelView.selectIndex(AirPanelController.this.getIndexByTemp(value), false);
                    return;
                }
                return;
            }
            BydLogHelper.logD("onFrontLeftTempChanged", "updateEaacTemp");
            if (AirPanelController.this.mAirPanelControllerHandler.hasMessages(259)) {
                return;
            }
            AirPanelController.this.updateEaacTemp(value);
        }

        @Override // com.android.systemui.statusbar.policy.HvacController.Callback
        public void onFrontRightTempChanged(int value) {
            BydLogHelper.logD("onFrontRightTempChanged", "value : " + value);
            if (AirPanelController.this.rightWheelView == null || !AirPanelController.this.rightWheelView.isShown()) {
                return;
            }
            AirPanelController.this.rightWheelView.setNeedPlayWheelSoundEffect(false);
            if (AirPanelController.this.mAirPanelControllerHandler.hasMessages(260)) {
                return;
            }
            AirPanelController.this.rightWheelView.selectIndex(AirPanelController.this.getIndexByTemp(value), false);
        }

        @Override // com.android.systemui.statusbar.policy.HvacController.Callback
        public void onWindLevelChanged(int level) {
            BydLogHelper.logD("onWindLevelChanged", "level : " + level);
            if (AirPanelController.this.mAirPanelControllerHandler.hasMessages(257)) {
                return;
            }
            AirPanelController.this.mWindLevel = level;
            AirPanelController airPanelController = AirPanelController.this;
            airPanelController.updateWindLevel(airPanelController.mWindLevel);
            AirPanelController.this.mAirPanelControllerHandler.removeMessages(257);
        }

        @Override // com.android.systemui.statusbar.policy.HvacController.Callback
        public void onAcTemperatureControlModeChanged(final int value) {
            BydLogHelper.logD("onAcTemperatureControlModeChanged", "value : " + value);
            ((Handler) Dependency.get(Dependency.MAIN_HANDLER)).post(new Runnable() { // from class: com.android.systemui.statusbar.wm.AirPanelController.6.1
                @Override // java.lang.Runnable
                public void run() {
                    if (AirPanelController.this.mAcType == 1) {
                        AirPanelController.this.updateAcTemperatureControlMode(value);
                    }
                }
            });
            AirPanelController.this.mAirPanelControllerHandler.removeMessages(258);
        }

        @Override // com.android.systemui.statusbar.policy.HvacController.Callback
        public void onHvacPower(boolean enabled) {
            BydLogHelper.logD("onHvacPower", "enabled : " + enabled);
        }

        @Override // com.android.systemui.statusbar.policy.HvacController.Callback
        public void onTemperatureUnitChanged(int unit) {
            BydLogHelper.logD("onTemperatureUnitChanged", "unit = " + unit);
            AirPanelController.this.mUnit = unit;
            if (AirPanelController.this.mPopupTempLeft != null && AirPanelController.this.mPopupTempLeft.isShowing()) {
                AirPanelController.this.initPopTempData();
                AirPanelController airPanelController = AirPanelController.this;
                airPanelController.resetTempViewTextSize(airPanelController.leftWheelView);
                AirPanelController.this.leftWheelView.setItems(AirPanelController.this.mPopTempData);
            }
            if (AirPanelController.this.mPopupTempRight != null && AirPanelController.this.mPopupTempRight.isShowing()) {
                AirPanelController.this.initPopTempData();
                AirPanelController airPanelController2 = AirPanelController.this;
                airPanelController2.resetTempViewTextSize(airPanelController2.rightWheelView);
                AirPanelController.this.rightWheelView.setItems(AirPanelController.this.mPopTempData);
            }
            ((Handler) Dependency.get(Dependency.MAIN_HANDLER)).post(new Runnable() { // from class: com.android.systemui.statusbar.wm.AirPanelController.6.2
                @Override // java.lang.Runnable
                public void run() {
                    AirPanelController.this.initGestureTempData();
                    if (AirPanelController.this.mPopupTempLeft != null) {
                        BydLogHelper.logD("onTemperatureUnitChanged mPopupTempLeft != null " + AirPanelController.this.mPopupTempLeft.isShowing());
                        AirPanelController.this.mPopupTempLeft.dismiss();
                        AirPanelController.this.mPopupTempLeft = null;
                        AirPanelController.this.leftWheelView = null;
                    }
                    if (AirPanelController.this.mPopupTempRight != null) {
                        BydLogHelper.logD("onTemperatureUnitChanged mPopupTempRight != null " + AirPanelController.this.mPopupTempRight.isShowing());
                        AirPanelController.this.mPopupTempRight.dismiss();
                        AirPanelController.this.mPopupTempRight = null;
                        AirPanelController.this.rightWheelView = null;
                    }
                }
            });
        }

        @Override // com.android.systemui.statusbar.policy.HvacController.Callback
        public void onCycleModeChanged(final int mode) {
            BydLogHelper.logD("onCycleModeChanged mode = " + mode + " , mCurrPopupWindow : " + AirPanelController.this.mCurrPopupWindow);
            ((Handler) Dependency.get(Dependency.MAIN_HANDLER)).post(new Runnable() { // from class: com.android.systemui.statusbar.wm.-$$Lambda$AirPanelController$6$P43drfHaOZ8-Zvso88L2naQrmQE
                @Override // java.lang.Runnable
                public final void run() {
                    this.f$0.lambda$onCycleModeChanged$0$AirPanelController$6(mode);
                }
            });
        }

        public /* synthetic */ void lambda$onCycleModeChanged$0$AirPanelController$6(int i) {
            if (AirPanelController.this.mCurrPopupWindow == 4) {
                AirPanelController.this.updateCycleMode(i);
            }
        }

        @Override // com.android.systemui.statusbar.policy.HvacController.Callback
        public void onMaxCoolingStateChanged(int state) {
            BydLogHelper.logD("onMaxCoolingStateChanged state = " + state);
            ((Handler) Dependency.get(Dependency.MAIN_HANDLER)).post(new Runnable() { // from class: com.android.systemui.statusbar.wm.-$$Lambda$AirPanelController$6$Df6S4tKROK3UqDUTEE7UN1OVfRo
                @Override // java.lang.Runnable
                public final void run() {
                    AirPanelController.AnonymousClass6.lambda$onMaxCoolingStateChanged$1();
                }
            });
        }

        @Override // com.android.systemui.statusbar.policy.HvacController.Callback
        public void onMessage5sOnlineStateChanged(final int id, final int state) {
            BydLogHelper.logD("onMessage5sOnlineStateChanged id = " + id + " , state = " + state);
            ((Handler) Dependency.get(Dependency.MAIN_HANDLER)).post(new Runnable() { // from class: com.android.systemui.statusbar.wm.-$$Lambda$AirPanelController$6$L_80ps2zQ_juGF0Hb4nalOeKWWk
                @Override // java.lang.Runnable
                public final void run() {
                    this.f$0.lambda$onMessage5sOnlineStateChanged$2$AirPanelController$6(id, state);
                }
            });
        }

        public /* synthetic */ void lambda$onMessage5sOnlineStateChanged$2$AirPanelController$6(int i, int i2) {
            if (i == 59) {
                AirPanelController.this.updateHasMessage(i2 == 1);
            }
        }
    }

    public void addAirPanelControllerListener(AirPanelControllerListener listener) {
        this.iAirPanelControllerListener = listener;
    }

    public void removeListener() {
        this.iAirPanelControllerListener = null;
    }

    public boolean isShowPopupWindow() {
        return this.isShowPopupWindow;
    }

    public void setShowPopupWindow(boolean showPopupWindow) {
        this.isShowPopupWindow = showPopupWindow;
    }

    public void release() {
        CommonDialog commonDialog = this.mDialog;
        if (commonDialog != null) {
            commonDialog.dismiss();
            this.mDialog = null;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void showDialog(String title, String message, int bottomStyle) {
        if (this.mDialog == null) {
            this.mDialog = new CommonDialog(this.mContext);
        }
        this.mDialog.setMessage(message).setTitle(title).setCountTime(8).setBottomStyle(bottomStyle).setBtnLeft(this.mContext.getResources().getString(R.string.know)).setOnClickBottomListener(new CommonDialog.OnClickBottomListener() { // from class: com.android.systemui.statusbar.wm.AirPanelController.8
            @Override // com.android.systemui.widget.CommonDialog.OnClickBottomListener
            public void onPositiveClick() {
                BydLogHelper.logD("onPositiveClick");
                AirPanelController.this.mDialog.dismiss();
            }

            @Override // com.android.systemui.widget.CommonDialog.OnClickBottomListener
            public void onNegativeClick() {
                AirPanelController.this.mDialog.dismiss();
            }
        });
        if (this.mDialog.isShowing()) {
            return;
        }
        this.mDialog.show();
    }

    private final class AirPanelControllerHandler extends Handler {
        public static final int LEFT_TEMP = 259;
        public static final int RIGHT_TEMP = 260;
        public static final int SET_TEMP = 256;
        public static final int TEMPERATURE_CONTROL_MODE = 258;
        public static final int WIND_LEVEL = 257;

        private AirPanelControllerHandler(Looper looper) {
            super(looper);
        }

        @Override // android.os.Handler
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case 257:
                    AirPanelController airPanelController = AirPanelController.this;
                    airPanelController.mWindLevel = airPanelController.hvacController.getAcWindLevel();
                    BydLogHelper.logD("AirPanelControllerHandler", "mWindLevel = " + AirPanelController.this.mWindLevel);
                    AirPanelController airPanelController2 = AirPanelController.this;
                    airPanelController2.updateWindLevel(airPanelController2.mWindLevel);
                    break;
                case 258:
                    int acTemperatureControlMode = AirPanelController.this.hvacController.getAcTemperatureControlMode();
                    BydLogHelper.logD("AirPanelControllerHandler", "acTemperatureControlMode = " + acTemperatureControlMode);
                    AirPanelController.this.updateAcTemperatureControlMode(acTemperatureControlMode);
                    break;
                case 259:
                    BydLogHelper.logD("AirPanelControllerHandler", "LEFT_TEMP mAcType : " + AirPanelController.this.mAcType + " , mCurrPopupWindow : " + AirPanelController.this.mCurrPopupWindow);
                    if (AirPanelController.this.mAcType == 1) {
                        if (AirPanelController.this.mCurrPopupWindow == 1) {
                            int frontLeftTempValue = AirPanelController.this.hvacController.getFrontLeftTempValue();
                            BydLogHelper.logD("AirPanelControllerHandler", "AC_TYPE_AUTO leftTempValue = " + frontLeftTempValue);
                            AirPanelController.this.setNeedPlayWheelSoundEffect(false);
                            AirPanelController.this.leftWheelView.selectIndex(AirPanelController.this.getIndexByTemp(frontLeftTempValue), false);
                        }
                    } else if (AirPanelController.this.mAcType == 2) {
                        int frontLeftTempValue2 = AirPanelController.this.hvacController.getFrontLeftTempValue();
                        BydLogHelper.logD("AirPanelControllerHandler", "AC_TYPE_ELECTRIC leftTempValue = " + frontLeftTempValue2);
                        AirPanelController.this.updateEaacTemp(frontLeftTempValue2);
                    }
                    break;
                case 260:
                    BydLogHelper.logD("AirPanelControllerHandler", "RIGHT_TEMP mAcType : " + AirPanelController.this.mAcType + " , mCurrPopupWindow : " + AirPanelController.this.mCurrPopupWindow);
                    if (AirPanelController.this.mAcType == 1) {
                        if (AirPanelController.this.mCurrPopupWindow == 2) {
                            int frontRightTempValue = AirPanelController.this.hvacController.getFrontRightTempValue();
                            BydLogHelper.logD("AirPanelControllerHandler", "AC_TYPE_AUTO rightTempValue = " + frontRightTempValue);
                            AirPanelController.this.setNeedPlayWheelSoundEffect(false);
                            AirPanelController.this.rightWheelView.selectIndex(AirPanelController.this.getIndexByTemp(frontRightTempValue), false);
                        }
                    } else if (AirPanelController.this.mAcType == 2) {
                        int frontLeftTempValue3 = AirPanelController.this.hvacController.getFrontLeftTempValue();
                        BydLogHelper.logD("AirPanelControllerHandler", "AC_TYPE_ELECTRIC leftTempValue = " + frontLeftTempValue3);
                        AirPanelController.this.updateEaacTemp(frontLeftTempValue3);
                    }
                    break;
            }
        }
    }

    public void showOnePixelWindow(View parent) {
        dismissDialogOnRecentVisible();
        View viewInflate = LayoutInflater.from(parent.getContext()).inflate(R.layout.view_double_temp_set, (ViewGroup) null);
        BydPopupWindow bydPopupWindow = new BydPopupWindow(viewInflate, 1, 1, true);
        bydPopupWindow.setDelayTime(100);
        showPopupWindow(parent, viewInflate, bydPopupWindow);
    }
}
