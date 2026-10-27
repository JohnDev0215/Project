package com.android.systemui.statusbar.wm;

import android.content.Context;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.os.SystemProperties;
import android.provider.Settings;
import android.text.TextUtils;
import android.view.GestureManager;
import android.view.MotionEvent;
import com.android.systemui.Constants;
import com.android.systemui.Dependency;
import com.android.systemui.SysUiServiceProvider;
import com.android.systemui.base.GestureDialogImpl;
import com.android.systemui.statusbar.phone.StatusBar;
import com.android.systemui.util.AppUtil;
import com.android.systemui.util.GuideUtils;
import com.byd.card.bean.Constant;
import com.byd.profile.ProfileManager;
import com.bydux.api.common.tools.log.BydLogHelper;

/* JADX INFO: loaded from: classes.dex */
public class GestureControl extends BaseController {
    private static final String TAG = "GestureControl";
    private boolean cancelEventFlag;
    private H handler;
    private boolean horizontalDealing;
    private int mActivePointerId;
    private AirPanelController mAirPanelController;
    private final Context mContext;
    private GestureManager mGestureManager;
    public boolean mHasMoreThanOneFinger;
    private float mLastX;
    private float mLastY;
    private int mPointerCount;
    private boolean panelWorking;
    private boolean recentsWorking;
    private boolean verticalDealing;
    private boolean mCanDrag = false;
    private final int INVALID_POINTER = -1;
    private final int mPointerCountLimit = 3;
    protected final int what_dismiss = 1;
    protected final int what_dismiss_cancel_event = 2;
    private final int delayTime = 3000;
    private final GestureManager.GestureChangeListener mGestureChangeListener = new GestureManager.GestureChangeListener() { // from class: com.android.systemui.statusbar.wm.GestureControl.1
        public void onGestureChanged(final MotionEvent motionEvent) {
            String str = SystemProperties.get(Constants.SYS_GESTURE_SYSTEMUI_KEY, Constant.CarType.CAR_TYPE_FUEL);
            BydLogHelper.logD("recentsWorking:" + GestureControl.this.recentsWorking + " , panelWorking:" + GestureControl.this.panelWorking + " , gestureStatus:" + str);
            boolean zEquals = TextUtils.equals(str, Constant.CarType.CAR_TYPE_FUEL);
            if (GuideUtils.isProvisionFinished(GestureControl.this.mContext) && GuideUtils.isSetupFinished(GestureControl.this.mContext) && zEquals && !ProfileManager.getInstance(GestureControl.this.mContext).isCleanScreen() && !GestureControl.this.recentsWorking) {
                ((Handler) Dependency.get(Dependency.MAIN_HANDLER)).post(new Runnable() { // from class: com.android.systemui.statusbar.wm.GestureControl.1.1
                    @Override // java.lang.Runnable
                    public void run() {
                        GestureControl.this.onTouchEvent(motionEvent);
                    }
                });
            }
        }
    };
    private final int mTouchSlop = 106;

    private boolean needHandler(int count) {
        return count >= 3;
    }

    private boolean needHandlerOnUp(int count) {
        return count > 3;
    }

    public void setRecentsWorking(boolean recentsWorking) {
        this.recentsWorking = recentsWorking;
    }

    public void setPanelWorking(boolean panelWorking) {
        this.panelWorking = panelWorking;
    }

    public GestureControl(Context mContext) {
        this.mContext = mContext;
        BydLogHelper.logV("mTouchSlop:106");
    }

    @Override // com.android.systemui.statusbar.wm.BaseController
    public void startUp() {
        BydLogHelper.logD("startUp");
        this.mAirPanelController = (AirPanelController) Dependency.get(AirPanelController.class);
        this.handler = new H(Looper.getMainLooper());
        try {
            GestureManager gestureManager = (GestureManager) this.mContext.getSystemService("gesture");
            this.mGestureManager = gestureManager;
            if (gestureManager != null) {
                BydLogHelper.logD(TAG, "GestureManager init");
                this.mGestureManager.subscribeGestureChangeListener(this.mGestureChangeListener);
            }
        } catch (Exception e) {
            BydLogHelper.logE(TAG, "init", e.toString());
            e.printStackTrace();
        }
    }

    @Override // com.android.systemui.statusbar.wm.BaseController
    public void reset() {
        try {
            GestureManager gestureManager = this.mGestureManager;
            if (gestureManager != null) {
                gestureManager.unSubscribeGestureChangeListener(this.mGestureChangeListener);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void handlerDown(MotionEvent event) {
        this.mLastX = event.getX();
        this.mLastY = event.getY();
        H h = this.handler;
        if (h != null) {
            h.removeMessages(1);
            this.handler.removeMessages(2);
        }
    }

    public void handlerMove(MotionEvent event) {
        float x = event.getX();
        float y = event.getY();
        float f = x - this.mLastX;
        float f2 = y - this.mLastY;
        if (Math.abs(f) >= Math.abs(f2)) {
            if (Math.abs(f) > this.mTouchSlop) {
                this.mLastX = x;
                this.mLastY = y;
                if (this.verticalDealing) {
                    BydLogHelper.logD("verticalDealing is true");
                    return;
                }
                AirPanelController airPanelController = this.mAirPanelController;
                if (airPanelController != null) {
                    this.horizontalDealing = true;
                    airPanelController.showGestureWindView();
                    if (f > 0.0f) {
                        BydLogHelper.logD("右滑");
                        this.mAirPanelController.setGestureWindIndex(true);
                        return;
                    } else {
                        BydLogHelper.logD("左滑");
                        this.mAirPanelController.setGestureWindIndex(false);
                        return;
                    }
                }
                return;
            }
            return;
        }
        if (Math.abs(f2) > this.mTouchSlop) {
            this.mLastX = x;
            this.mLastY = y;
            if (this.horizontalDealing) {
                BydLogHelper.logD("horizontalDealing is true");
                return;
            }
            AirPanelController airPanelController2 = this.mAirPanelController;
            if (airPanelController2 != null) {
                this.verticalDealing = true;
                airPanelController2.showGestureTempView();
                if (f2 > 0.0f) {
                    BydLogHelper.logD("下滑");
                    this.mAirPanelController.setGestureTempIndex(false);
                } else {
                    BydLogHelper.logD("上滑");
                    this.mAirPanelController.setGestureTempIndex(true);
                }
            }
        }
    }

    public boolean onTouchEvent(MotionEvent event) {
        int actionIndex = event.getActionIndex();
        int pointerCount = event.getPointerCount();
        int actionMasked = event.getActionMasked();
        this.mPointerCount = pointerCount;
        BydLogHelper.logD("action:" + actionMasked + " , pointerCount:" + pointerCount + " , actionIndex:" + actionIndex);
        switch (actionMasked) {
            case 1:
                this.mHasMoreThanOneFinger = false;
                this.mActivePointerId = -1;
                this.mCanDrag = false;
                this.verticalDealing = false;
                this.horizontalDealing = false;
                this.mPointerCount = 0;
                handlerUp();
                break;
            case 2:
                if (this.mActivePointerId != -1) {
                    if (finishScreenSaver()) {
                        this.mCanDrag = false;
                        return false;
                    }
                    if (this.mCanDrag && event.findPointerIndex(this.mActivePointerId) != -1) {
                        handlerMove(event);
                    }
                }
                break;
            case 3:
            case 4:
                ((StatusBar) SysUiServiceProvider.getComponent(this.mContext, StatusBar.class)).setThreeFingers(false);
                handlerCancel();
                this.mPointerCount = 0;
                this.mHasMoreThanOneFinger = false;
                break;
            case 5:
                this.mHasMoreThanOneFinger = true;
                if (needHandler(pointerCount)) {
                    ((StatusBar) SysUiServiceProvider.getComponent(this.mContext, StatusBar.class)).setThreeFingers(true);
                    this.mActivePointerId = event.getPointerId(actionIndex);
                    this.mCanDrag = true;
                    this.cancelEventFlag = false;
                    handlerDown(event);
                }
                break;
            case 6:
                if (!needHandlerOnUp(pointerCount)) {
                    this.mActivePointerId = -1;
                    this.mCanDrag = false;
                }
                if (pointerCount < 3) {
                    ((StatusBar) SysUiServiceProvider.getComponent(this.mContext, StatusBar.class)).setThreeFingers(false);
                }
                if (this.mActivePointerId == event.getPointerId(actionIndex)) {
                    this.mActivePointerId = event.getPointerId(actionIndex != 0 ? actionIndex - 1 : 1);
                }
                break;
        }
        return this.mCanDrag;
    }

    private void handlerCancel() {
        this.cancelEventFlag = true;
        GestureDialogImpl.getInstance(this.mContext).setCanceledOnTouchOutside(true);
        H h = this.handler;
        if (h != null) {
            h.sendEmptyMessageDelayed(2, 3000L);
        }
    }

    public void cancelDismissMessage() {
        H h = this.handler;
        if (h != null) {
            h.removeMessages(1);
            this.handler.removeMessages(2);
        }
    }

    public void handTouchOutsideCancel() {
        cancelDismissMessage();
        resetData();
    }

    public void handDismiss() {
        AirPanelController airPanelController = this.mAirPanelController;
        if (airPanelController != null) {
            airPanelController.handlerGestureUpEvent(false);
        }
    }

    public void handlerUp() {
        this.cancelEventFlag = true;
        GestureDialogImpl.getInstance(this.mContext).setCanceledOnTouchOutside(true);
        AirPanelController airPanelController = this.mAirPanelController;
        if (airPanelController != null) {
            airPanelController.handlerGestureUpEvent();
        }
        dismissDelay();
    }

    public void dismissDelay() {
        if (this.handler != null) {
            BydLogHelper.logD("dismissDelay");
            this.handler.removeMessages(1);
            this.handler.sendEmptyMessageDelayed(2, 3000L);
        }
    }

    public int getPointerCount() {
        return this.mPointerCount;
    }

    public void setPointerCount(int mPointerCount) {
        this.mPointerCount = mPointerCount;
    }

    private class H extends Handler {
        public H(Looper mainLooper) {
            super(mainLooper);
        }

        @Override // android.os.Handler
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            int i = msg.what;
            BydLogHelper.logD("what:" + i);
            if (i == 1) {
                GestureControl.this.resetData();
            } else {
                if (i != 2) {
                    return;
                }
                BydLogHelper.logD("cancelEventFlag:" + GestureControl.this.cancelEventFlag);
                if (GestureControl.this.cancelEventFlag) {
                    GestureControl.this.resetData();
                }
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void resetData() {
        BydLogHelper.logD("resetData dismiss");
        GestureDialogImpl.getInstance(this.mContext).dismiss();
        this.mCanDrag = false;
        this.verticalDealing = false;
        this.horizontalDealing = false;
        this.mLastX = 0.0f;
        this.mLastY = 0.0f;
    }

    public boolean canDrag() {
        BydLogHelper.logD("mCanDrag" + this.mCanDrag);
        return this.mCanDrag;
    }

    private boolean finishScreenSaver() {
        if (Settings.Global.getInt(this.mContext.getContentResolver(), "navibar_mode_on_screensaver", 0) == 1) {
            return false;
        }
        BydLogHelper.logD(TAG, "finishScreenSaver");
        AppUtil.finishScreenSaver();
        return true;
    }
}
