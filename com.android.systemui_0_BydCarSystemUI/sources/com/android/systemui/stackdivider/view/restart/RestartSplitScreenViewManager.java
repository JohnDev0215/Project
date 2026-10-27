package com.android.systemui.stackdivider.view.restart;

import android.app.UiModeManager;
import android.content.Context;
import android.content.res.Configuration;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Handler;
import android.os.Message;
import android.provider.Settings;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import androidx.core.view.GravityCompat;
import com.android.systemui.R;
import com.android.systemui.stackdivider.DividerUtils;
import com.android.systemui.statusbar.wm.Util;
import com.android.systemui.util.BlurUtil;
import com.bydux.api.common.tools.log.BydLogHelper;
import java.lang.ref.WeakReference;

/* JADX INFO: loaded from: classes.dex */
public class RestartSplitScreenViewManager {
    private static final int DELAY = 8000;
    private static RestartSplitScreenViewManager instance;
    private ImageView ivAdsorbBarLandscape;
    private ImageView ivAdsorbBarPortrait;
    private int landscapeRightLocation;
    private Context mContext;
    private DividerUtils mDividerUtils;
    private int mGestureSlideExitOrientation;
    private WindowManager.LayoutParams mLayoutParams;
    private View mRestartSplitScreenBgView;
    private View mRestartSplitScreenView;
    private RestartSplitScreenViewHandler mRestartSplitScreenViewHandler;
    private int mStartX;
    private int mStartY;
    private UiModeManager mUiModeManager;
    private WindowManager mWindowManager;
    private int portraitBottomLocation;
    private RelativeLayout rlLandscape;
    private RelativeLayout rlPortrait;
    private final float[] lastX = {0.0f};
    private final float[] lastY = {0.0f};
    private boolean isRestartSplitScreen = false;

    public static synchronized RestartSplitScreenViewManager getInstance() {
        if (instance == null) {
            instance = new RestartSplitScreenViewManager();
        }
        return instance;
    }

    private RestartSplitScreenViewManager() {
    }

    public void init(Context context) {
        this.mContext = context;
        initLocation();
        initView();
        initListener();
        this.mUiModeManager = (UiModeManager) this.mContext.getSystemService("uimode");
        this.mRestartSplitScreenViewHandler = new RestartSplitScreenViewHandler(this);
        this.mDividerUtils = DividerUtils.getInstance();
        addView();
    }

    private void initLocation() {
        int[] windowWidthAndHeight = Util.getWindowWidthAndHeight(this.mContext);
        int navigationBarHeight = Util.getNavigationBarHeight(this.mContext);
        int statusBarHeight = Util.getStatusBarHeight(this.mContext);
        int i = windowWidthAndHeight[1];
        int i2 = windowWidthAndHeight[0];
        this.landscapeRightLocation = i2 - ((int) this.mContext.getResources().getDimension(R.dimen.dp_64));
        this.portraitBottomLocation = ((i2 - navigationBarHeight) - statusBarHeight) - ((int) this.mContext.getResources().getDimension(R.dimen.dp_64));
        BydLogHelper.logD("initLocation landscapeRightLocation : " + this.landscapeRightLocation + " , portraitBottomLocation : " + this.portraitBottomLocation);
        if (this.mWindowManager == null) {
            this.mWindowManager = (WindowManager) this.mContext.getSystemService("window");
        }
        if (this.mLayoutParams == null) {
            this.mLayoutParams = new WindowManager.LayoutParams();
        }
        this.mLayoutParams.format = 1;
        this.mLayoutParams.flags = 262184;
        this.mLayoutParams.windowAnimations = 0;
        this.mLayoutParams.type = 2001;
        this.mLayoutParams.gravity = GravityCompat.START;
        this.mLayoutParams.width = (int) this.mContext.getResources().getDimension(R.dimen.dp_64);
        this.mLayoutParams.height = -2;
        this.mLayoutParams.setTitle("RestartSplitScreenView");
        BydLogHelper.logD("initLocation mLayoutParams : " + this.mLayoutParams);
        this.mLayoutParams.x = 0;
        this.mLayoutParams.y = 0;
    }

    private void initView() {
        View viewInflate = LayoutInflater.from(this.mContext).inflate(R.layout.restart_split_screen_view, (ViewGroup) null);
        this.mRestartSplitScreenView = viewInflate;
        this.rlLandscape = (RelativeLayout) viewInflate.findViewById(R.id.rlLandscape);
        this.rlPortrait = (RelativeLayout) this.mRestartSplitScreenView.findViewById(R.id.rlPortrait);
        this.ivAdsorbBarLandscape = (ImageView) this.mRestartSplitScreenView.findViewById(R.id.ivAdsorbBarLandscape);
        this.ivAdsorbBarPortrait = (ImageView) this.mRestartSplitScreenView.findViewById(R.id.ivAdsorbBarPortrait);
        this.mRestartSplitScreenView.setVisibility(8);
    }

    public void showRestartSplitScreenBgView() {
        if (this.mRestartSplitScreenBgView == null) {
            int i = this.mContext.getResources().getConfiguration().uiMode;
            BydLogHelper.logD("RestartSplitScreenViewManager", "showRestartSplitScreenBgView");
            this.mRestartSplitScreenBgView = LayoutInflater.from(this.mContext).inflate(R.layout.restart_split_screen_view_bg_view, (ViewGroup) null);
            WindowManager.LayoutParams layoutParams = new WindowManager.LayoutParams();
            layoutParams.type = 2002;
            layoutParams.width = -1;
            layoutParams.height = -1;
            layoutParams.flags = 262184;
            layoutParams.format = 1;
            layoutParams.gravity = GravityCompat.START;
            this.mWindowManager.addView(this.mRestartSplitScreenBgView, layoutParams);
            RelativeLayout relativeLayout = (RelativeLayout) this.mRestartSplitScreenBgView.findViewById(R.id.bg_root_layout);
            Util.getNavigationBarHeight(this.mContext);
            int statusBarHeight = Util.getStatusBarHeight(this.mContext);
            Display defaultDisplay = ((WindowManager) this.mContext.getSystemService("window")).getDefaultDisplay();
            DisplayMetrics displayMetrics = this.mContext.getResources().getDisplayMetrics();
            BydLogHelper.logD("showRestartSplitScreenBgView rot = " + defaultDisplay.getRotation());
            BydLogHelper.logD("showRestartSplitScreenBgView heightPixels = " + displayMetrics.heightPixels + " , widthPixels : " + displayMetrics.widthPixels);
            int i2 = displayMetrics.heightPixels - statusBarHeight;
            int i3 = displayMetrics.widthPixels;
            BydLogHelper.logD("showRestartSplitScreenBgView left = 0 , top : " + statusBarHeight + " , width : " + i3 + " , height : " + i2);
            Bitmap screenshot = BlurUtil.getScreenshot(this.mContext, 0, statusBarHeight, i3, i2);
            if (screenshot != null) {
                relativeLayout.setBackground(new BitmapDrawable(screenshot));
            }
        }
    }

    public void hideBgView() {
        BydLogHelper.logD("RestartSplitScreenViewManager", "hideBgView mRestartSplitScreenBgView =" + this.mRestartSplitScreenBgView);
        View view = this.mRestartSplitScreenBgView;
        if (view != null) {
            this.mWindowManager.removeViewImmediate(view);
            this.mRestartSplitScreenBgView = null;
        }
    }

    /* JADX INFO: renamed from: com.android.systemui.stackdivider.view.restart.RestartSplitScreenViewManager$1, reason: invalid class name */
    class AnonymousClass1 implements View.OnTouchListener {
        AnonymousClass1() {
        }

        /* JADX WARN: Removed duplicated region for block: B:53:0x025a  */
        @Override // android.view.View.OnTouchListener
        /*
            Code decompiled incorrectly, please refer to instructions dump.
            To view partially-correct code enable 'Show inconsistent code' option in preferences
        */
        public boolean onTouch(android.view.View r12, android.view.MotionEvent r13) {
            /*
                Method dump skipped, instruction units count: 877
                To view this dump change 'Code comments level' option to 'DEBUG'
            */
            throw new UnsupportedOperationException("Method not decompiled: com.android.systemui.stackdivider.view.restart.RestartSplitScreenViewManager.AnonymousClass1.onTouch(android.view.View, android.view.MotionEvent):boolean");
        }

        public /* synthetic */ void lambda$onTouch$0$RestartSplitScreenViewManager$1() {
            RestartSplitScreenViewManager.this.hideBgView();
        }
    }

    private void initListener() {
        this.mRestartSplitScreenView.setOnTouchListener(new AnonymousClass1());
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void reCoverSplitScreen(boolean leftToRight) {
        this.isRestartSplitScreen = true;
        BydLogHelper.logD("enter split screen");
        this.lastX[0] = 0.0f;
        this.lastY[0] = 0.0f;
        this.mDividerUtils.reStartSplitScreen(this.mContext, leftToRight);
    }

    public void addView() {
        if (this.mWindowManager != null) {
            BydLogHelper.logD("RestartSplitScreenViewManager addView");
            this.mWindowManager.addView(this.mRestartSplitScreenView, this.mLayoutParams);
        }
    }

    public void removeView() {
        WindowManager windowManager = this.mWindowManager;
        if (windowManager != null) {
            windowManager.removeView(this.mRestartSplitScreenView);
        }
    }

    public void hideView() {
        BydLogHelper.logD("RestartSplitScreenViewManager hideView : " + this.mRestartSplitScreenView.getVisibility());
        View view = this.mRestartSplitScreenView;
        if (view != null && view.getVisibility() == 0) {
            this.mRestartSplitScreenView.setVisibility(8);
            this.mRestartSplitScreenViewHandler.removeMessages(0);
            Settings.System.putInt(this.mContext.getContentResolver(), "is_show_split_screen_bar", 0);
        }
        hideBgView();
        this.mDividerUtils.setGestureSlideExitOrientation(0);
    }

    public void showView() {
        View view = this.mRestartSplitScreenView;
        if (view == null || view.getVisibility() == 0) {
            return;
        }
        this.mRestartSplitScreenView.refreshDrawableState();
        this.mRestartSplitScreenView.setVisibility(0);
        this.isRestartSplitScreen = false;
        Settings.System.putInt(this.mContext.getContentResolver(), "is_show_split_screen_bar", 1);
        this.mRestartSplitScreenViewHandler.removeMessages(0);
        this.mRestartSplitScreenViewHandler.sendEmptyMessageDelayed(0, 8000L);
        onUiModeConfigChange();
    }

    public void setLocation(int x, int y, int gravity) {
        BydLogHelper.logD("setLocation x : " + x + " , y : " + y + " , gravity : " + gravity);
        this.mLayoutParams.x = x;
        this.mLayoutParams.y = y;
        this.mStartX = x;
        this.mStartY = y;
        this.mLayoutParams.gravity = gravity;
        BydLogHelper.logD("setLocation isHorizontalDivision : " + this.mDividerUtils.isHorizontalDivision());
        if (this.mDividerUtils.isHorizontalDivision()) {
            this.rlLandscape.setVisibility(0);
            this.rlPortrait.setVisibility(8);
            this.mLayoutParams.width = (int) this.mContext.getResources().getDimension(R.dimen.dp_64);
            this.mLayoutParams.height = -2;
        } else {
            this.rlLandscape.setVisibility(8);
            this.rlPortrait.setVisibility(0);
            this.mLayoutParams.width = -2;
            this.mLayoutParams.height = (int) this.mContext.getResources().getDimension(R.dimen.dp_64);
        }
        this.mWindowManager.updateViewLayout(this.mRestartSplitScreenView, this.mLayoutParams);
    }

    public boolean isShow() {
        View view = this.mRestartSplitScreenView;
        return view != null && view.getVisibility() == 0;
    }

    private static class RestartSplitScreenViewHandler extends Handler {
        public static final int MSG_HIDE_VIEW = 0;
        WeakReference<RestartSplitScreenViewManager> mRestartSplitScreenViewManager;

        public RestartSplitScreenViewHandler(RestartSplitScreenViewManager restartSplitScreenViewManager) {
            this.mRestartSplitScreenViewManager = new WeakReference<>(restartSplitScreenViewManager);
        }

        @Override // android.os.Handler
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            if (this.mRestartSplitScreenViewManager.get() == null) {
                return;
            }
            BydLogHelper.logD("handleMessage msg.what =" + msg.what);
            if (msg.what == 0) {
                this.mRestartSplitScreenViewManager.get().hideView();
            }
        }
    }

    public void onDestroy() {
        removeView();
        if (this.mRestartSplitScreenViewHandler != null) {
            this.mRestartSplitScreenViewHandler = null;
        }
    }

    public void onOrientationChanged(Configuration newConfig) {
        BydLogHelper.logD("onOrientationChanged newConfig : " + newConfig);
        if (isShow()) {
            int i = newConfig.orientation;
            BydLogHelper.logD("onOrientationChanged mOrientation : " + i);
            BydLogHelper.logD("onOrientationChanged landscapeRightLocation : " + this.landscapeRightLocation + " , portraitBottomLocation : " + this.portraitBottomLocation);
            boolean z = i == 2;
            BydLogHelper.logD("onOrientationChanged landscape : " + z);
            int gestureSlideExitOrientation = this.mDividerUtils.getGestureSlideExitOrientation();
            BydLogHelper.logD("onOrientationChanged orientation : " + gestureSlideExitOrientation);
            if (z) {
                if (gestureSlideExitOrientation == 3) {
                    this.mDividerUtils.setGestureSlideExitOrientation(1);
                } else if (gestureSlideExitOrientation == 4) {
                    this.mDividerUtils.setGestureSlideExitOrientation(2);
                }
                WindowManager.LayoutParams layoutParams = this.mLayoutParams;
                layoutParams.x = layoutParams.y;
                if (this.mLayoutParams.y == this.portraitBottomLocation) {
                    this.mLayoutParams.x = this.landscapeRightLocation;
                }
                this.mLayoutParams.y = 0;
                this.mLayoutParams.gravity = GravityCompat.START;
                this.rlLandscape.setVisibility(0);
                this.rlPortrait.setVisibility(8);
                this.mLayoutParams.width = (int) this.mContext.getResources().getDimension(R.dimen.dp_64);
                this.mLayoutParams.height = -2;
                BydLogHelper.logD("onOrientationChanged mStartY : " + this.mStartY);
                if (this.mStartY == 0) {
                    this.mStartX = 0;
                } else {
                    this.mStartX = this.landscapeRightLocation;
                    BydLogHelper.logD("landscape onOrientationChanged mStartX : " + this.mStartX);
                }
            } else {
                if (gestureSlideExitOrientation == 1) {
                    gestureSlideExitOrientation = 3;
                } else if (gestureSlideExitOrientation == 2) {
                    gestureSlideExitOrientation = 4;
                }
                this.mDividerUtils.setGestureSlideExitOrientation(gestureSlideExitOrientation);
                WindowManager.LayoutParams layoutParams2 = this.mLayoutParams;
                layoutParams2.y = layoutParams2.x;
                BydLogHelper.logD("onOrientationChanged mLayoutParams.x : " + this.mLayoutParams.x);
                if (this.mLayoutParams.x == this.landscapeRightLocation) {
                    this.mLayoutParams.y = this.portraitBottomLocation;
                }
                this.mLayoutParams.x = 0;
                this.mLayoutParams.gravity = 48;
                this.rlLandscape.setVisibility(8);
                this.rlPortrait.setVisibility(0);
                this.mLayoutParams.width = -2;
                this.mLayoutParams.height = (int) this.mContext.getResources().getDimension(R.dimen.dp_64);
                BydLogHelper.logD("onOrientationChanged mStartX : " + this.mStartX);
                if (this.mStartX == 0) {
                    this.mStartY = 0;
                } else {
                    this.mStartY = this.portraitBottomLocation;
                }
            }
            this.mWindowManager.updateViewLayout(this.mRestartSplitScreenView, this.mLayoutParams);
        }
    }

    public void onUiModeConfigChange() {
        boolean zIsShow = isShow();
        BydLogHelper.logD("onUiModeConfigChange isShow : " + zIsShow);
        if (zIsShow) {
            int nightMode = this.mUiModeManager.getNightMode();
            BydLogHelper.logD("onUiModeConfigChange type : " + nightMode);
            if (nightMode == 1) {
                this.ivAdsorbBarLandscape.setImageResource(R.mipmap.ic_split_screen_adsorb_bar_landscape_light);
                this.ivAdsorbBarPortrait.setImageResource(R.mipmap.ic_split_screen_adsorb_bar_portrait_light);
            } else if (nightMode == 2) {
                this.ivAdsorbBarLandscape.setImageResource(R.mipmap.ic_split_screen_adsorb_bar_landscape_dark);
                this.ivAdsorbBarPortrait.setImageResource(R.mipmap.ic_split_screen_adsorb_bar_portrait_dark);
            }
        }
    }
}
