package com.android.systemui.statusbar.phone;

import android.content.Context;
import android.content.res.Configuration;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.widget.FrameLayout;
import com.android.systemui.Dependency;
import com.android.systemui.R;
import com.android.systemui.SysUiServiceProvider;
import com.android.systemui.statusbar.policy.BluetoothController;
import com.android.systemui.statusbar.wm.AirPanelController;
import com.android.systemui.statusbar.wm.GestureControl;
import com.android.systemui.volume.VolumeComponent;
import com.bydux.api.common.tools.log.BydLogHelper;

/* JADX INFO: loaded from: classes.dex */
public class NavigationBarFrame extends FrameLayout {
    private static String TAG = "NavigationBarFrame";
    private int deviation;
    private boolean isCompleted;
    boolean isUp;
    private float leftBorder;
    private AirPanelController mAirPanelController;
    private BluetoothController mBluetoothController;
    private Context mContext;
    private GestureControl mGestureControl;
    private int mMinDistance;
    private float moveX;
    private float moveY;
    private float pressX;
    private float pressY;
    private float rightBorder;
    private StatusBar statusBar;
    private float upX;
    private float upY;
    private boolean work;

    private boolean isLimit(int count) {
        return count == 1;
    }

    public NavigationBarFrame(Context context) {
        super(context);
        this.mMinDistance = 40;
        this.work = false;
        this.isUp = false;
        this.mContext = context;
        this.statusBar = (StatusBar) SysUiServiceProvider.getComponent(context, StatusBar.class);
        this.deviation = this.mContext.getResources().getDimensionPixelSize(R.dimen.dp_8);
        initBorder();
        BydLogHelper.logD("deviation:" + this.deviation);
    }

    public NavigationBarFrame(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.mMinDistance = 40;
        this.work = false;
        this.isUp = false;
        this.mContext = context;
        this.statusBar = (StatusBar) SysUiServiceProvider.getComponent(context, StatusBar.class);
        this.deviation = this.mContext.getResources().getDimensionPixelSize(R.dimen.dp_8);
        initBorder();
        BydLogHelper.logD("deviation:" + this.deviation);
    }

    public NavigationBarFrame(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.mMinDistance = 40;
        this.work = false;
        this.isUp = false;
        this.mContext = context;
        this.statusBar = (StatusBar) SysUiServiceProvider.getComponent(context, StatusBar.class);
        this.deviation = this.mContext.getResources().getDimensionPixelSize(R.dimen.dp_8);
        initBorder();
        BydLogHelper.logD("deviation:" + this.deviation);
    }

    public void onBootCompleted() {
        if (this.mAirPanelController == null) {
            this.mAirPanelController = (AirPanelController) Dependency.get(AirPanelController.class);
        }
        this.mBluetoothController = (BluetoothController) Dependency.get(BluetoothController.class);
        this.mGestureControl = (GestureControl) Dependency.get(GestureControl.class);
    }

    public final boolean isLand(Context context) {
        int i = context.getResources().getConfiguration().orientation;
        Log.d(TAG, "orientation " + i);
        return i != 1;
    }

    @Override // android.view.ViewGroup
    public boolean onInterceptTouchEvent(MotionEvent event) {
        if (event.getAction() == 0) {
            int height = getHeight();
            this.pressX = event.getX();
            this.pressY = event.getY();
            this.work = true;
            BydLogHelper.logV("InterceptTouch", "pressY = " + this.pressY + " , pressX = " + this.pressX + " , work =" + this.work + " , height =" + height);
        }
        if (this.work) {
            return onTouchEvent(event);
        }
        return super.onInterceptTouchEvent(event);
    }

    private void initBorder() {
        if (isLand(this.mContext)) {
            this.leftBorder = this.mContext.getResources().getDimension(R.dimen.dp_476);
            this.rightBorder = this.mContext.getResources().getDimension(R.dimen.dp_804);
        } else {
            this.leftBorder = this.mContext.getResources().getDimension(R.dimen.dp_222);
            this.rightBorder = this.mContext.getResources().getDimension(R.dimen.dp_498);
        }
    }

    @Override // android.view.View
    protected void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        initBorder();
    }

    @Override // android.view.ViewGroup, android.view.View
    public boolean dispatchTouchEvent(MotionEvent event) {
        BydLogHelper.logD("dispatchTouchEvent action = " + event.getAction() + " pointerCount = " + event.getPointerCount());
        if (this.mGestureControl == null) {
            return true;
        }
        int rawY = (int) event.getRawY();
        if (rawY <= this.mContext.getResources().getDimensionPixelSize(R.dimen.status_bar_height)) {
            BydLogHelper.logD("dismiss volume dialog y = " + rawY);
            ((VolumeComponent) SysUiServiceProvider.getComponent(this.mContext, VolumeComponent.class)).dismiss();
        }
        int action = event.getAction();
        if (action == 1 || action == 3 || action == 4) {
            this.mGestureControl.setRecentsWorking(false);
        }
        return super.dispatchTouchEvent(event);
    }

    /* JADX WARN: Removed duplicated region for block: B:28:0x0049  */
    @Override // android.view.View
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    public boolean onTouchEvent(android.view.MotionEvent r7) {
        /*
            Method dump skipped, instruction units count: 426
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.systemui.statusbar.phone.NavigationBarFrame.onTouchEvent(android.view.MotionEvent):boolean");
    }
}
