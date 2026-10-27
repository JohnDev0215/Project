package com.android.systemui.base;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.View;
import android.widget.ImageView;
import com.android.systemui.R;
import com.android.systemui.util.ViewSoundEffectUtil;
import com.android.systemui.widget.GestureRelativeLayout;
import com.android.systemui.widget.WheelView;
import com.bydux.api.common.tools.log.BydLogHelper;
import com.google.android.material.tabs.TabLayout;

/* JADX INFO: loaded from: classes.dex */
public class GestureDialogImpl extends GestureDialog implements DialogInterface.OnDismissListener, View.OnClickListener, GestureRelativeLayout.TempEventListener, GestureRelativeLayout.WindEventListener, DialogInterface.OnCancelListener, DialogInterface.OnShowListener {
    private static final String action = "com.byd.action.gesture.dialog.event";
    private static volatile GestureDialogImpl instance;
    private ImageView img_degree;
    private boolean isInitData;
    private TabLayout mTabLayoutGesture;
    private WheelView mWheelViewGesture;
    private View rl_root;
    private GestureRelativeLayout rl_temp;
    private GestureRelativeLayout rl_wind;

    public boolean isInitData() {
        return this.isInitData;
    }

    public void setInitData(boolean initData) {
        this.isInitData = initData;
    }

    public boolean isRlTempVisible() {
        return this.rl_temp.getVisibility() == 0;
    }

    public void setRlTempVisible(boolean isVisible) {
        BydLogHelper.logD("setRlTempVisible");
        this.rl_temp.setVisibility(isVisible ? 0 : 4);
    }

    public boolean isRlWindVisible() {
        return this.rl_wind.getVisibility() == 0;
    }

    public void setRlWindVisible(boolean isVisible) {
        BydLogHelper.logD("setRlWindVisible");
        this.rl_wind.setVisibility(isVisible ? 0 : 4);
    }

    private GestureDialogImpl(Context context) {
        this(context, R.style.dialog_style_gesture);
    }

    public GestureDialogImpl(Context context, int themeResId) {
        super(context, themeResId);
    }

    public static GestureDialogImpl getInstance(Context context) {
        if (instance == null) {
            synchronized (GestureDialogImpl.class) {
                if (instance == null) {
                    instance = new GestureDialogImpl(context);
                }
            }
        }
        return instance;
    }

    @Override // com.android.systemui.base.GestureDialog
    protected void onCreate() {
        super.onCreate();
        this.rl_root = findViewById(R.id.rl_root);
        this.rl_temp = (GestureRelativeLayout) findViewById(R.id.rl_temp);
        this.rl_wind = (GestureRelativeLayout) findViewById(R.id.rl_wind);
        this.mWheelViewGesture = (WheelView) findViewById(R.id.wheel_view);
        this.mTabLayoutGesture = (TabLayout) findViewById(R.id.tablayout);
        this.img_degree = (ImageView) findViewById(R.id.img_degree);
        this.rl_temp.setVisibility(4);
        this.rl_wind.setVisibility(4);
        this.rl_temp.setTempEventListener(this);
        this.rl_wind.setWindEventListener(this);
        this.rl_temp.setOnClickListener(this);
        this.rl_wind.setOnClickListener(this);
        this.rl_root.setOnClickListener(this);
        setOnDismissListener(this);
        setOnCancelListener(this);
        setOnShowListener(this);
        this.isInitData = false;
    }

    public WheelView getWheelViewGesture() {
        return this.mWheelViewGesture;
    }

    public TabLayout getTabLayoutGesture() {
        return this.mTabLayoutGesture;
    }

    public ImageView getImgDegree() {
        return this.img_degree;
    }

    public void updateDialogGesture() {
        BydLogHelper.logD("updateDialogGesture");
        onCreate();
    }

    @Override // com.android.systemui.widget.GestureRelativeLayout.TempEventListener
    public void handTempEvent(float moveY) {
        int currentSelectedIndex = this.mWheelViewGesture.getCurrentSelectedIndex();
        int itemCount = this.mWheelViewGesture.getItemCount();
        if (moveY > 0.0f) {
            BydLogHelper.logV("下滑 1");
            if (currentSelectedIndex <= 0 || itemCount <= 1) {
                return;
            }
            this.mWheelViewGesture.setSelected(currentSelectedIndex - 1);
            ViewSoundEffectUtil.playWheelSoundEffect(this.mWheelViewGesture);
            return;
        }
        BydLogHelper.logV("上滑 1");
        if (currentSelectedIndex >= itemCount - 1 || itemCount <= 1) {
            return;
        }
        this.mWheelViewGesture.setSelected(currentSelectedIndex + 1);
        ViewSoundEffectUtil.playWheelSoundEffect(this.mWheelViewGesture);
    }

    @Override // com.android.systemui.widget.GestureRelativeLayout.WindEventListener
    public void handWindEvent(float moveX) {
        int selectedTabPosition = this.mTabLayoutGesture.getSelectedTabPosition();
        int tabCount = this.mTabLayoutGesture.getTabCount();
        if (moveX > 0.0f) {
            BydLogHelper.logV("右滑 1");
            if (selectedTabPosition >= tabCount - 1 || tabCount <= 1) {
                return;
            }
            TabLayout tabLayout = this.mTabLayoutGesture;
            tabLayout.selectTab(tabLayout.getTabAt(selectedTabPosition + 1));
            ViewSoundEffectUtil.playWheelSoundEffect(this.mTabLayoutGesture);
            return;
        }
        BydLogHelper.logV("左滑 1");
        if (selectedTabPosition <= 0 || tabCount <= 1) {
            return;
        }
        TabLayout tabLayout2 = this.mTabLayoutGesture;
        tabLayout2.selectTab(tabLayout2.getTabAt(selectedTabPosition - 1));
        ViewSoundEffectUtil.playWheelSoundEffect(this.mTabLayoutGesture);
    }

    @Override // android.content.DialogInterface.OnShowListener
    public void onShow(DialogInterface dialog) {
        BydLogHelper.logD("onShow");
        sendBroadcast(1);
        if (this.gestureControl != null) {
            this.gestureControl.dismissDelay();
        }
    }

    @Override // android.content.DialogInterface.OnDismissListener
    public void onDismiss(DialogInterface dialog) {
        BydLogHelper.logD("onDismiss");
        if (this.gestureControl != null) {
            this.gestureControl.handDismiss();
        }
        this.rl_temp.setVisibility(4);
        this.rl_wind.setVisibility(4);
        sendBroadcast(0);
    }

    @Override // android.app.Dialog, android.view.Window.Callback
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        BydLogHelper.logD("onWindowFocusChanged hasFocus = " + hasFocus);
    }

    @Override // android.view.View.OnClickListener
    public void onClick(View v) {
        int id = v.getId();
        if (id == R.id.rl_root) {
            BydLogHelper.logD("click rl_root");
            handDismiss();
            return;
        }
        if (id == R.id.rl_temp) {
            if (this.rl_temp.getVisibility() != 0) {
                BydLogHelper.logD("click rl_temp");
                handDismiss();
                return;
            }
            return;
        }
        if (id == R.id.rl_wind && this.rl_wind.getVisibility() != 0) {
            BydLogHelper.logD("click rl_wind");
            handDismiss();
        }
    }

    private void handDismiss() {
        BydLogHelper.logD();
        this.gestureControl.handTouchOutsideCancel();
    }

    @Override // android.content.DialogInterface.OnCancelListener
    public void onCancel(DialogInterface dialog) {
        BydLogHelper.logD("onCancel");
        handDismiss();
    }

    private void sendBroadcast(int value) {
        BydLogHelper.logV("value:" + value);
        Intent intent = new Intent();
        intent.setAction(action);
        intent.putExtra("value", value);
        this.mContext.sendBroadcast(intent);
    }
}
