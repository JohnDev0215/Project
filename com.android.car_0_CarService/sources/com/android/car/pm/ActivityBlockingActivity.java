package com.android.car.pm;

import android.app.Activity;
import android.app.ActivityManager;
import android.app.IActivityManager;
import android.car.Car;
import android.car.content.pm.CarPackageManager;
import android.car.drivingstate.CarUxRestrictions;
import android.car.drivingstate.CarUxRestrictionsManager;
import android.content.ComponentName;
import android.content.Intent;
import android.graphics.Insets;
import android.graphics.Rect;
import android.hardware.display.DisplayManager;
import android.opengl.GLSurfaceView;
import android.os.Build;
import android.os.Bundle;
import android.os.RemoteException;
import android.text.TextUtils;
import android.util.Log;
import android.view.DisplayInfo;
import android.view.View;
import android.view.ViewTreeObserver;
import android.view.WindowInsets;
import android.widget.Button;
import android.widget.TextView;
import com.android.car.CarLog;
import com.android.car.R;
import com.android.car.pm.blurredbackground.BlurredSurfaceRenderer;
import java.util.List;

/* JADX INFO: loaded from: classes3.dex */
public class ActivityBlockingActivity extends Activity {
    private static final int EGL_CONFIG_SIZE = 8;
    private static final int EGL_CONTEXT_VERSION = 3;
    private static final int INVALID_TASK_ID = -1;
    private IActivityManager mAm;
    private int mBlockedTaskId;
    private Car mCar;
    private CarPackageManager mCarPackageManager;
    private Button mExitButton;
    private GLSurfaceView mGLSurfaceView;
    private BlurredSurfaceRenderer mSurfaceRenderer;
    private Button mToggleDebug;
    private CarUxRestrictionsManager mUxRManager;
    private final Object mLock = new Object();
    private boolean mIsGLSurfaceSetup = false;
    private final View.OnClickListener mOnExitButtonClickedListener = new View.OnClickListener() { // from class: com.android.car.pm.-$$Lambda$ActivityBlockingActivity$qEkZzGxYr4SHKHzP8soe-ty_MSY
        @Override // android.view.View.OnClickListener
        public final void onClick(View view) {
            this.f$0.lambda$new$0$ActivityBlockingActivity(view);
        }
    };
    private final ViewTreeObserver.OnGlobalLayoutListener mOnGlobalLayoutListener = new ViewTreeObserver.OnGlobalLayoutListener() { // from class: com.android.car.pm.ActivityBlockingActivity.1
        @Override // android.view.ViewTreeObserver.OnGlobalLayoutListener
        public void onGlobalLayout() {
            ActivityBlockingActivity.this.mToggleDebug.getViewTreeObserver().removeOnGlobalLayoutListener(this);
            ActivityBlockingActivity.this.updateButtonWidths();
        }
    };

    public /* synthetic */ void lambda$new$0$ActivityBlockingActivity(View v) {
        if (isExitOptionCloseApplication()) {
            handleCloseApplication();
        } else {
            handleRestartingTask();
        }
    }

    @Override // android.app.Activity
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_blocking);
        this.mExitButton = (Button) findViewById(R.id.exit_button);
        this.mAm = ActivityManager.getService();
        this.mCar = Car.createCar(this, null, -1L, new Car.CarServiceLifecycleListener() { // from class: com.android.car.pm.-$$Lambda$ActivityBlockingActivity$NDZGx4jpYENhpiDhFD2vDm24fDk
            @Override // android.car.Car.CarServiceLifecycleListener
            public final void onLifecycleChanged(Car car, boolean z) {
                this.f$0.lambda$onCreate$1$ActivityBlockingActivity(car, z);
            }
        });
        setupGLSurface();
    }

    public /* synthetic */ void lambda$onCreate$1$ActivityBlockingActivity(Car car, boolean ready) {
        if (!ready) {
            return;
        }
        this.mCarPackageManager = (CarPackageManager) car.getCarManager("package");
        CarUxRestrictionsManager carUxRestrictionsManager = (CarUxRestrictionsManager) car.getCarManager("uxrestriction");
        this.mUxRManager = carUxRestrictionsManager;
        handleUxRChange(carUxRestrictionsManager.getCurrentCarUxRestrictions());
        this.mUxRManager.registerListener(new CarUxRestrictionsManager.OnUxRestrictionsChangedListener() { // from class: com.android.car.pm.-$$Lambda$ActivityBlockingActivity$2aOjBy7l2ri8zXpoth3lxHlv12A
            @Override // android.car.drivingstate.CarUxRestrictionsManager.OnUxRestrictionsChangedListener
            public final void onUxRestrictionsChanged(CarUxRestrictions carUxRestrictions) {
                this.f$0.handleUxRChange(carUxRestrictions);
            }
        });
    }

    @Override // android.app.Activity
    protected void onStart() {
        super.onStart();
        if (this.mIsGLSurfaceSetup) {
            this.mGLSurfaceView.onResume();
        }
    }

    @Override // android.app.Activity
    protected void onResume() {
        super.onResume();
        this.mBlockedTaskId = getIntent().getIntExtra(CarPackageManagerService.BLOCKING_INTENT_EXTRA_BLOCKED_TASK_ID, -1);
        String blockedActivity = getIntent().getStringExtra(CarPackageManagerService.BLOCKING_INTENT_EXTRA_BLOCKED_ACTIVITY_NAME);
        if (!TextUtils.isEmpty(blockedActivity)) {
            if (isTopActivityBehindAbaDistractionOptimized()) {
                Log.e(CarLog.TAG_AM, "Top activity is already DO, so finishing");
                finish();
                return;
            } else if (Log.isLoggable(CarLog.TAG_AM, 3)) {
                Log.d(CarLog.TAG_AM, "Blocking activity " + blockedActivity);
            }
        }
        displayExitButton();
        if (Build.IS_ENG || Build.IS_USERDEBUG) {
            displayDebugInfo();
        }
    }

    @Override // android.app.Activity
    protected void onStop() {
        super.onStop();
        if (this.mIsGLSurfaceSetup) {
            this.mGLSurfaceView.queueEvent(new Runnable() { // from class: com.android.car.pm.-$$Lambda$ActivityBlockingActivity$3J-l3Imb1Oqv_1-WDLUbSYpWGlU
                @Override // java.lang.Runnable
                public final void run() {
                    this.f$0.lambda$onStop$2$ActivityBlockingActivity();
                }
            });
            this.mGLSurfaceView.onPause();
        }
        finish();
    }

    public /* synthetic */ void lambda$onStop$2$ActivityBlockingActivity() {
        this.mSurfaceRenderer.onPause();
    }

    private void setupGLSurface() {
        DisplayManager displayManager = (DisplayManager) getApplicationContext().getSystemService("display");
        DisplayInfo displayInfo = new DisplayInfo();
        int displayId = getDisplayId();
        displayManager.getDisplay(displayId).getDisplayInfo(displayInfo);
        Rect windowRect = getAppWindowRect();
        boolean shouldRenderBlurred = getDisplayId() == 0;
        this.mSurfaceRenderer = new BlurredSurfaceRenderer(this, windowRect, shouldRenderBlurred);
        GLSurfaceView gLSurfaceView = (GLSurfaceView) findViewById(R.id.blurred_surface_view);
        this.mGLSurfaceView = gLSurfaceView;
        gLSurfaceView.setEGLContextClientVersion(3);
        this.mGLSurfaceView.getHolder().setFormat(-3);
        this.mGLSurfaceView.setEGLConfigChooser(8, 8, 8, 8, 8, 8);
        this.mGLSurfaceView.setRenderer(this.mSurfaceRenderer);
        this.mGLSurfaceView.setRenderMode(0);
        this.mIsGLSurfaceSetup = true;
    }

    private Rect getAppWindowRect() {
        Insets systemBarInsets = getWindowManager().getCurrentWindowMetrics().getWindowInsets().getInsets(WindowInsets.Type.systemBars());
        Rect displayBounds = getWindowManager().getCurrentWindowMetrics().getBounds();
        int leftX = systemBarInsets.left;
        int rightX = displayBounds.width() - systemBarInsets.right;
        int topY = systemBarInsets.top;
        int bottomY = displayBounds.height() - systemBarInsets.bottom;
        return new Rect(leftX, topY, rightX, bottomY);
    }

    private void displayExitButton() {
        String exitButtonText = getExitButtonText();
        this.mExitButton.setText(exitButtonText);
        this.mExitButton.setOnClickListener(this.mOnExitButtonClickedListener);
    }

    private boolean isExitOptionCloseApplication() {
        boolean isRootDO = getIntent().getBooleanExtra(CarPackageManagerService.BLOCKING_INTENT_EXTRA_IS_ROOT_ACTIVITY_DO, false);
        return this.mBlockedTaskId == -1 || !isRootDO;
    }

    private String getExitButtonText() {
        return isExitOptionCloseApplication() ? getString(R.string.exit_button_close_application) : getString(R.string.exit_button_go_back);
    }

    private boolean isTopActivityBehindAbaDistractionOptimized() {
        try {
            List<ActivityManager.StackInfo> stackInfos = this.mAm.getAllStackInfos();
            ActivityManager.StackInfo topStackBehindAba = null;
            for (ActivityManager.StackInfo stackInfo : stackInfos) {
                if (stackInfo.displayId == getDisplayId() && !getComponentName().equals(stackInfo.topActivity) && stackInfo.visible && (topStackBehindAba == null || topStackBehindAba.position < stackInfo.position)) {
                    topStackBehindAba = stackInfo;
                }
            }
            if (Log.isLoggable(CarLog.TAG_AM, 3)) {
                Log.d(CarLog.TAG_AM, String.format("Top stack behind ABA is: %s", topStackBehindAba));
            }
            if (topStackBehindAba == null || topStackBehindAba.topActivity == null) {
                return false;
            }
            boolean isDo = this.mCarPackageManager.isActivityDistractionOptimized(topStackBehindAba.topActivity.getPackageName(), topStackBehindAba.topActivity.getClassName());
            if (Log.isLoggable(CarLog.TAG_AM, 3)) {
                Log.d(CarLog.TAG_AM, String.format("Top activity (%s) is DO: %s", topStackBehindAba.topActivity, Boolean.valueOf(isDo)));
            }
            return isDo;
        } catch (RemoteException e) {
            Log.e(CarLog.TAG_AM, "Unable to get stack info from ActivityManager");
            return false;
        }
    }

    private void displayDebugInfo() {
        String blockedActivity = getIntent().getStringExtra(CarPackageManagerService.BLOCKING_INTENT_EXTRA_BLOCKED_ACTIVITY_NAME);
        String rootActivity = getIntent().getStringExtra(CarPackageManagerService.BLOCKING_INTENT_EXTRA_ROOT_ACTIVITY_NAME);
        final TextView debugInfo = (TextView) findViewById(R.id.debug_info);
        debugInfo.setText(getDebugInfo(blockedActivity, rootActivity));
        Button button = (Button) findViewById(R.id.toggle_debug_info);
        this.mToggleDebug = button;
        button.setVisibility(0);
        this.mToggleDebug.setOnClickListener(new View.OnClickListener() { // from class: com.android.car.pm.-$$Lambda$ActivityBlockingActivity$zYqRU9Ia_6OFPEjK2Y_7RX1uU1Q
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                ActivityBlockingActivity.lambda$displayDebugInfo$3(debugInfo, view);
            }
        });
        this.mToggleDebug.getViewTreeObserver().addOnGlobalLayoutListener(this.mOnGlobalLayoutListener);
    }

    static /* synthetic */ void lambda$displayDebugInfo$3(TextView debugInfo, View v) {
        boolean isDebugVisible = debugInfo.getVisibility() == 0;
        debugInfo.setVisibility(isDebugVisible ? 8 : 0);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void updateButtonWidths() {
        Button debugButton = (Button) findViewById(R.id.toggle_debug_info);
        int exitButtonWidth = this.mExitButton.getWidth();
        int debugButtonWidth = debugButton.getWidth();
        if (exitButtonWidth > debugButtonWidth) {
            debugButton.setWidth(exitButtonWidth);
        } else {
            this.mExitButton.setWidth(debugButtonWidth);
        }
    }

    private String getDebugInfo(String blockedActivity, String rootActivity) {
        StringBuilder debug = new StringBuilder();
        ComponentName blocked = ComponentName.unflattenFromString(blockedActivity);
        debug.append("Blocked activity is ");
        debug.append(blocked.getShortClassName());
        debug.append("\nBlocked activity package is ");
        debug.append(blocked.getPackageName());
        if (rootActivity != null) {
            ComponentName root = ComponentName.unflattenFromString(rootActivity);
            if (!root.equals(blocked)) {
                debug.append("\n\nRoot activity is ");
                debug.append(root.getShortClassName());
            }
            if (!root.getPackageName().equals(blocked.getPackageName())) {
                debug.append("\nRoot activity package is ");
                debug.append(root.getPackageName());
            }
        }
        return debug.toString();
    }

    @Override // android.app.Activity
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        setIntent(intent);
    }

    @Override // android.app.Activity
    protected void onDestroy() {
        super.onDestroy();
        this.mCar.disconnect();
        this.mUxRManager.unregisterListener();
        Button button = this.mToggleDebug;
        if (button != null) {
            button.getViewTreeObserver().removeOnGlobalLayoutListener(this.mOnGlobalLayoutListener);
        }
        this.mCar.disconnect();
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void handleUxRChange(CarUxRestrictions restrictions) {
        if (restrictions != null && !restrictions.isRequiresDistractionOptimization()) {
            finish();
        }
    }

    private void handleCloseApplication() {
        if (isFinishing()) {
            return;
        }
        Intent startMain = new Intent("android.intent.action.MAIN");
        startMain.addCategory("android.intent.category.HOME");
        startMain.setFlags(268435456);
        startActivity(startMain);
        finish();
    }

    private void handleRestartingTask() {
        synchronized (this.mLock) {
            if (isFinishing()) {
                return;
            }
            if (Log.isLoggable(CarLog.TAG_AM, 4)) {
                Log.i(CarLog.TAG_AM, "Restarting task " + this.mBlockedTaskId);
            }
            this.mCarPackageManager.restartTask(this.mBlockedTaskId);
            finish();
        }
    }
}
