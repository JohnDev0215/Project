package com.android.systemui.stackdivider.view;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.TextView;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.android.systemui.Dependency;
import com.android.systemui.R;
import com.android.systemui.SysUiServiceProvider;
import com.android.systemui.stackdivider.Divider;
import com.android.systemui.stackdivider.DividerUtils;
import com.android.systemui.statusbar.phone.StatusBar;
import com.android.systemui.widget.BydPopupWindow;
import com.byd.card.bean.AppInfo;
import com.byd.card.view.activity.ActivityCollector;
import com.bydux.api.common.tools.log.BydLogHelper;
import com.ts.avm.sdk.impl.AvmManagerImpl;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

/* JADX INFO: loaded from: classes.dex */
public class SplitScreenSecondaryActivity extends Activity {
    private static final int APP_LIST_CHANGE = 2;
    private static final int SHOW_TOAST = 1;
    private Divider mDivider;
    private DividerUtils mDividerUtils;
    private BydPopupWindow mPopupToast;
    private SplitScreenAppAdapter mSplitScreenAppAdapter;
    private UpdateViewHandler mUpdateViewHandler;
    private View mViewPopupToast;
    private RecyclerView recyclerView;
    private TextView tvMessage;

    @Override // android.app.Activity
    protected void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        ActivityCollector.addActivity(this, SplitScreenSecondaryActivity.class);
        doBeforeParseLayout();
        setContentView(R.layout.activity_split_screen_secondary);
        this.mUpdateViewHandler = new UpdateViewHandler(this);
        this.mDivider = (Divider) SysUiServiceProvider.getComponent(this, Divider.class);
        this.mDividerUtils = DividerUtils.getInstance();
        this.recyclerView = (RecyclerView) findViewById(R.id.recycler_view);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 4);
        gridLayoutManager.setOrientation(1);
        this.recyclerView.setLayoutManager(gridLayoutManager);
        ArrayList<AppInfo> supportSplitScreenApps = this.mDividerUtils.getSupportSplitScreenApps();
        BydLogHelper.logD("onCreate mAppList size : " + supportSplitScreenApps.size());
        SplitScreenAppAdapter splitScreenAppAdapter = new SplitScreenAppAdapter(this, supportSplitScreenApps, false, this, this.recyclerView);
        this.mSplitScreenAppAdapter = splitScreenAppAdapter;
        this.recyclerView.setAdapter(splitScreenAppAdapter);
        EventBus.getDefault().register(this);
    }

    protected void doBeforeParseLayout() {
        Window window = getWindow();
        window.addFlags(Integer.MIN_VALUE);
        window.clearFlags(AvmManagerImpl.AVM_CALIBRATION_CODE);
        window.setStatusBarColor(0);
        window.getDecorView().setSystemUiVisibility(1280);
    }

    @Override // android.app.Activity
    protected void onStart() {
        super.onStart();
        BydLogHelper.logD("onStart");
    }

    @Override // android.app.Activity
    protected void onResume() {
        super.onResume();
        BydLogHelper.logD("onResume");
    }

    @Override // android.app.Activity
    public void onBackPressed() {
        super.onBackPressed();
        BydLogHelper.logD("onBackPressed");
        ((StatusBar) SysUiServiceProvider.getComponent(this, StatusBar.class)).exitSplit();
    }

    @Override // android.app.Activity, android.view.Window.Callback
    public void onAttachedToWindow() {
        BydLogHelper.logD("onAttachedToWindow");
        super.onAttachedToWindow();
    }

    @Override // android.app.Activity, android.view.Window.Callback
    public void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        BydLogHelper.logD("onDetachedFromWindow");
    }

    @Subscribe(threadMode = ThreadMode.MAIN)
    public void onEvent(SplitScreenEvent event) {
        int splitScreenEvent = event.getSplitScreenEvent();
        BydLogHelper.logD("onEvent splitScreenEvent : " + splitScreenEvent);
        if (splitScreenEvent == 4) {
            ((Handler) Dependency.get(Dependency.MAIN_HANDLER)).post(new Runnable() { // from class: com.android.systemui.stackdivider.view.-$$Lambda$SplitScreenSecondaryActivity$iVacWOD_C_g-Q_tRXs3pvG6HBqE
                @Override // java.lang.Runnable
                public final void run() {
                    this.f$0.finish();
                }
            });
            return;
        }
        if (splitScreenEvent != 1) {
            if (splitScreenEvent == 5) {
                this.mUpdateViewHandler.removeMessages(2);
                this.mUpdateViewHandler.sendEmptyMessage(2);
                return;
            }
            return;
        }
        Message message = new Message();
        message.what = 1;
        message.arg1 = R.string.app_is_opened_select_another_application;
        this.mUpdateViewHandler.removeMessages(1);
        this.mUpdateViewHandler.sendMessage(message);
    }

    @Override // android.app.Activity, android.view.Window.Callback
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
    }

    @Override // android.app.Activity
    protected void onStop() {
        super.onStop();
        BydLogHelper.logD("onStop");
    }

    @Override // android.app.Activity
    protected void onDestroy() {
        super.onDestroy();
        boolean zIsRegistered = EventBus.getDefault().isRegistered(this);
        BydLogHelper.logD("onDestroy isRegistered : " + zIsRegistered);
        if (zIsRegistered) {
            EventBus.getDefault().unregister(this);
        }
        UpdateViewHandler updateViewHandler = this.mUpdateViewHandler;
        if (updateViewHandler != null) {
            updateViewHandler.removeMessages(1);
            this.mUpdateViewHandler = null;
        }
        ActivityCollector.removeActivity(this);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void updateAppList() {
        this.mSplitScreenAppAdapter.refreshList(this.mDividerUtils.getSupportSplitScreenApps());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void showToast(int resId) {
        boolean zIsSplitActive = this.mDivider.isSplitActive();
        boolean zIsSwapping = this.mDivider.isSwapping();
        BydLogHelper.logD("showToast isSplitActive : " + zIsSplitActive + " , isSwapping : " + zIsSwapping);
        if (zIsSplitActive && !zIsSwapping) {
            if (this.mPopupToast == null) {
                this.mViewPopupToast = LayoutInflater.from(this).inflate(R.layout.split_screen_toast, (ViewGroup) null);
                BydPopupWindow bydPopupWindow = new BydPopupWindow(this.mViewPopupToast, -2, -2, true);
                this.mPopupToast = bydPopupWindow;
                bydPopupWindow.setDelayTime(1000);
                this.tvMessage = (TextView) this.mViewPopupToast.findViewById(R.id.tvMessage);
            }
            this.tvMessage.setText(getResources().getString(resId));
            showPopupWindow(this.recyclerView, this.mViewPopupToast, this.mPopupToast);
            return;
        }
        BydLogHelper.logD("no show Toast");
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
        int dimensionPixelSize = getResources().getDimensionPixelSize(R.dimen.dp_55);
        int i2 = (iArr[1] - measuredHeight) + dimensionPixelSize;
        BydLogHelper.logD("offY:" + i2 + " , popupHeight:" + measuredHeight + " , marginBottom:" + dimensionPixelSize);
        popupWindow.showAtLocation(parent, 0, i, i2);
    }

    public void updateForbidden(boolean isPGear) {
        this.mSplitScreenAppAdapter.updateForbidden(isPGear);
    }

    private static class UpdateViewHandler extends Handler {
        WeakReference<SplitScreenSecondaryActivity> mActivity;

        public UpdateViewHandler(SplitScreenSecondaryActivity activity) {
            this.mActivity = new WeakReference<>(activity);
        }

        @Override // android.os.Handler
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (this.mActivity.get() == null) {
                return;
            }
            BydLogHelper.logD("UpdateViewHandler handleMessage msg.what = " + msg.what);
            if (msg.what == 1) {
                this.mActivity.get().showToast(msg.arg1);
            } else if (msg.what == 2) {
                this.mActivity.get().updateAppList();
            }
        }
    }
}
