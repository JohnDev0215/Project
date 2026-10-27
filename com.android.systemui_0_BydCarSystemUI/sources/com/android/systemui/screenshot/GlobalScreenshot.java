package com.android.systemui.screenshot;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.app.ActivityManager;
import android.app.Notification;
import android.app.PendingIntent;
import android.content.ActivityNotFoundException;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Insets;
import android.graphics.Outline;
import android.graphics.PointF;
import android.graphics.Rect;
import android.graphics.Region;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.InsetDrawable;
import android.graphics.drawable.LayerDrawable;
import android.media.MediaActionSound;
import android.net.Uri;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.os.RemoteException;
import android.provider.Settings;
import android.util.DisplayMetrics;
import android.util.Log;
import android.util.MathUtils;
import android.view.Display;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.SurfaceControl;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewOutlineProvider;
import android.view.ViewTreeObserver;
import android.view.WindowInsets;
import android.view.WindowManager;
import android.view.accessibility.AccessibilityManager;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.AnimationUtils;
import android.view.animation.Interpolator;
import android.widget.FrameLayout;
import android.widget.HorizontalScrollView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.view.ViewCompat;
import com.android.internal.logging.UiEventLogger;
import com.android.systemui.R;
import com.android.systemui.shared.system.QuickStepContract;
import com.android.systemui.statusbar.wm.Util;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.function.Consumer;
import javax.inject.Inject;
import javax.inject.Singleton;

/* JADX INFO: loaded from: classes.dex */
@Singleton
public class GlobalScreenshot implements ViewTreeObserver.OnComputeInternalInsetsListener {
    static final String ACTION_TYPE_DELETE = "Delete";
    static final String ACTION_TYPE_EDIT = "Edit";
    static final String ACTION_TYPE_SHARE = "Share";
    static final String EXTRA_ACTION_INTENT = "android:screenshot_action_intent";
    static final String EXTRA_ACTION_TYPE = "android:screenshot_action_type";
    static final String EXTRA_CANCEL_NOTIFICATION = "android:screenshot_cancel_notification";
    static final String EXTRA_DISALLOW_ENTER_PIP = "android:screenshot_disallow_enter_pip";
    static final String EXTRA_ID = "android:screenshot_id";
    static final String EXTRA_SMART_ACTIONS_ENABLED = "android:smart_actions_enabled";
    private static final int MESSAGE_CORNER_TIMEOUT = 2;
    private static final float ROUNDED_CORNER_RADIUS = 0.05f;
    private static final long SCREENSHOT_ACTIONS_ALPHA_DURATION_MS = 100;
    private static final long SCREENSHOT_ACTIONS_EXPANSION_DURATION_MS = 400;
    private static final float SCREENSHOT_ACTIONS_START_SCALE_X = 0.7f;
    private static final int SCREENSHOT_CORNER_DEFAULT_TIMEOUT_MILLIS = 6000;
    private static final long SCREENSHOT_DISMISS_ALPHA_DURATION_MS = 183;
    private static final long SCREENSHOT_DISMISS_ALPHA_OFFSET_MS = 50;
    private static final long SCREENSHOT_DISMISS_Y_DURATION_MS = 350;
    private static final long SCREENSHOT_FLASH_IN_DURATION_MS = 133;
    private static final long SCREENSHOT_FLASH_OUT_DURATION_MS = 217;
    private static final long SCREENSHOT_STROKE_ZOOM_IN_DURATION_MS = 200;
    private static final long SCREENSHOT_TO_CORNER_DISMISS_DELAY_MS = 200;
    private static final long SCREENSHOT_TO_CORNER_SCALE_DURATION_MS = 234;
    private static final long SCREENSHOT_TO_CORNER_X_DURATION_MS = 234;
    private static final long SCREENSHOT_TO_CORNER_Y_DURATION_MS = 500;
    static final String SCREENSHOT_URI_ID = "android:screenshot_uri_id";
    private static final String SETTINGS_SECURE_USER_SETUP_COMPLETE = "user_setup_complete";
    private static final String TAG = "GlobalScreenshot";
    private HorizontalScrollView mActionsContainer;
    private ImageView mActionsContainerBackground;
    private LinearLayout mActionsView;
    private ImageView mBackgroundProtection;
    private MediaActionSound mCameraSound;
    private final Context mContext;
    private float mCornerSizeX;
    private boolean mDirectionLTR;
    private Animator mDismissAnimation;
    private FrameLayout mDismissButton;
    private float mDismissDeltaY;
    private final Display mDisplay;
    private final DisplayMetrics mDisplayMetrics;
    private final Interpolator mFastOutSlowIn;
    private boolean mInDarkMode;
    private int mLeftInset;
    private int mNavMode;
    private final ScreenshotNotificationsController mNotificationsController;
    private Runnable mOnCompleteRunnable;
    private boolean mOrientationPortrait;
    private int mRightInset;
    private SaveImageInBackgroundTask mSaveInBgTask;
    private Bitmap mScreenBitmap;
    private ImageView mScreenshotAnimatedView;
    private Animator mScreenshotAnimation;
    private ImageView mScreenshotFlash;
    private View mScreenshotLayout;
    private ImageView mScreenshotPreview;
    private ImageView mScreenshotPreviewStroke;
    private ScreenshotSelectorView mScreenshotSelectorView;
    private final ScreenshotSmartActions mScreenshotSmartActions;
    private final UiEventLogger mUiEventLogger;
    private final WindowManager.LayoutParams mWindowLayoutParams;
    private final WindowManager mWindowManager;
    private final Interpolator mAccelerateInterpolator = new AccelerateInterpolator();
    private final Handler mScreenshotHandler = new Handler(Looper.getMainLooper()) { // from class: com.android.systemui.screenshot.GlobalScreenshot.1
        @Override // android.os.Handler
        public void handleMessage(Message msg) {
            if (msg.what != 2) {
                return;
            }
            GlobalScreenshot.this.mUiEventLogger.log(ScreenshotEvent.SCREENSHOT_INTERACTION_TIMEOUT);
            GlobalScreenshot.this.dismissScreenshot("timeout", false);
            GlobalScreenshot.this.mOnCompleteRunnable.run();
        }
    };

    static class SaveImageInBackgroundData {
        public int errorMsgResId;
        public Consumer<Uri> finisher;
        public Bitmap image;
        public ActionsReadyListener mActionsReadyListener;

        SaveImageInBackgroundData() {
        }

        void clearImage() {
            this.image = null;
        }
    }

    static class SavedImageData {
        public Notification.Action deleteAction;
        public Notification.Action editAction;
        public Notification.Action shareAction;
        public List<Notification.Action> smartActions;
        public Uri uri;

        SavedImageData() {
        }

        public void reset() {
            this.uri = null;
            this.shareAction = null;
            this.editAction = null;
            this.deleteAction = null;
            this.smartActions = null;
        }
    }

    static abstract class ActionsReadyListener {
        abstract void onActionsReady(SavedImageData imageData);

        ActionsReadyListener() {
        }
    }

    public GlobalScreenshot(Context context, UiEventLogger uiEventLogger) {
        this.mContext = context;
        WindowManager windowManager = (WindowManager) context.getSystemService("window");
        this.mWindowManager = windowManager;
        this.mScreenshotSmartActions = new ScreenshotSmartActions();
        this.mNotificationsController = new ScreenshotNotificationsController(context, windowManager);
        this.mUiEventLogger = uiEventLogger;
        reloadAssets();
        Configuration configuration = context.getResources().getConfiguration();
        this.mInDarkMode = configuration.isNightModeActive();
        this.mDirectionLTR = configuration.getLayoutDirection() == 0;
        this.mOrientationPortrait = configuration.orientation == 1;
        WindowManager.LayoutParams layoutParams = new WindowManager.LayoutParams(-1, -1, 0, 0, 2036, 918816, -3);
        this.mWindowLayoutParams = layoutParams;
        layoutParams.setTitle("ScreenshotAnimation");
        layoutParams.layoutInDisplayCutoutMode = 3;
        layoutParams.setFitInsetsTypes(0);
        Display defaultDisplay = windowManager.getDefaultDisplay();
        this.mDisplay = defaultDisplay;
        DisplayMetrics displayMetrics = new DisplayMetrics();
        this.mDisplayMetrics = displayMetrics;
        defaultDisplay.getRealMetrics(displayMetrics);
        this.mCornerSizeX = context.getResources().getDimensionPixelSize(R.dimen.global_screenshot_x_scale);
        this.mDismissDeltaY = context.getResources().getDimensionPixelSize(R.dimen.screenshot_dismissal_height_delta);
        this.mFastOutSlowIn = AnimationUtils.loadInterpolator(context, 17563661);
        MediaActionSound mediaActionSound = new MediaActionSound();
        this.mCameraSound = mediaActionSound;
        mediaActionSound.load(0);
    }

    @Inject
    public GlobalScreenshot(Context context, Resources resources, ScreenshotSmartActions screenshotSmartActions, ScreenshotNotificationsController screenshotNotificationsController, UiEventLogger uiEventLogger) {
        this.mContext = context;
        this.mScreenshotSmartActions = screenshotSmartActions;
        this.mNotificationsController = screenshotNotificationsController;
        this.mUiEventLogger = uiEventLogger;
        reloadAssets();
        Configuration configuration = context.getResources().getConfiguration();
        this.mInDarkMode = configuration.isNightModeActive();
        this.mDirectionLTR = configuration.getLayoutDirection() == 0;
        this.mOrientationPortrait = configuration.orientation == 1;
        WindowManager.LayoutParams layoutParams = new WindowManager.LayoutParams(-1, -1, 0, 0, 2036, 918816, -3);
        this.mWindowLayoutParams = layoutParams;
        layoutParams.setTitle("ScreenshotAnimation");
        layoutParams.layoutInDisplayCutoutMode = 3;
        layoutParams.setFitInsetsTypes(0);
        WindowManager windowManager = (WindowManager) context.getSystemService("window");
        this.mWindowManager = windowManager;
        Display defaultDisplay = windowManager.getDefaultDisplay();
        this.mDisplay = defaultDisplay;
        DisplayMetrics displayMetrics = new DisplayMetrics();
        this.mDisplayMetrics = displayMetrics;
        defaultDisplay.getRealMetrics(displayMetrics);
        this.mCornerSizeX = resources.getDimensionPixelSize(R.dimen.global_screenshot_x_scale);
        this.mDismissDeltaY = resources.getDimensionPixelSize(R.dimen.screenshot_dismissal_height_delta);
        this.mFastOutSlowIn = AnimationUtils.loadInterpolator(context, 17563661);
        MediaActionSound mediaActionSound = new MediaActionSound();
        this.mCameraSound = mediaActionSound;
        mediaActionSound.load(0);
    }

    public void onComputeInternalInsets(ViewTreeObserver.InternalInsetsInfo inoutInfo) {
        inoutInfo.setTouchableInsets(3);
        Region region = new Region();
        Rect rect = new Rect();
        this.mScreenshotPreview.getBoundsOnScreen(rect);
        region.op(rect, Region.Op.UNION);
        Rect rect2 = new Rect();
        this.mActionsContainer.getBoundsOnScreen(rect2);
        region.op(rect2, Region.Op.UNION);
        Rect rect3 = new Rect();
        this.mDismissButton.getBoundsOnScreen(rect3);
        region.op(rect3, Region.Op.UNION);
        if (QuickStepContract.isGesturalMode(this.mNavMode)) {
            Rect rect4 = new Rect(0, 0, this.mLeftInset, this.mDisplayMetrics.heightPixels);
            region.op(rect4, Region.Op.UNION);
            rect4.set(this.mDisplayMetrics.widthPixels - this.mRightInset, 0, this.mDisplayMetrics.widthPixels, this.mDisplayMetrics.heightPixels);
            region.op(rect4, Region.Op.UNION);
        }
        inoutInfo.touchableRegion.set(region);
    }

    void takeScreenshotFullscreen(Consumer<Uri> finisher, Runnable onComplete) {
        this.mOnCompleteRunnable = onComplete;
        this.mDisplay.getRealMetrics(this.mDisplayMetrics);
        lambda$takeScreenshotPartial$0$GlobalScreenshot(finisher, new Rect(0, 0, this.mDisplayMetrics.widthPixels, this.mDisplayMetrics.heightPixels));
    }

    void handleImageAsScreenshot(Bitmap screenshot, Rect screenshotScreenBounds, Insets visibleInsets, int taskId, int userId, ComponentName topComponent, Consumer<Uri> finisher, Runnable onComplete) {
        this.mOnCompleteRunnable = onComplete;
        if (aspectRatiosMatch(screenshot, visibleInsets, screenshotScreenBounds)) {
            saveScreenshot(screenshot, finisher, screenshotScreenBounds, visibleInsets, false);
        } else {
            saveScreenshot(screenshot, finisher, new Rect(0, 0, screenshot.getWidth(), screenshot.getHeight()), Insets.NONE, true);
        }
    }

    void takeScreenshotPartial(final Consumer<Uri> finisher, Runnable onComplete) {
        dismissScreenshot("new screenshot requested", true);
        this.mOnCompleteRunnable = onComplete;
        this.mWindowManager.addView(this.mScreenshotLayout, this.mWindowLayoutParams);
        this.mScreenshotSelectorView.setOnTouchListener(new View.OnTouchListener() { // from class: com.android.systemui.screenshot.-$$Lambda$GlobalScreenshot$VGGXUpxAOOJBFJ21o72xzu6cgLA
            @Override // android.view.View.OnTouchListener
            public final boolean onTouch(View view, MotionEvent motionEvent) {
                return this.f$0.lambda$takeScreenshotPartial$1$GlobalScreenshot(finisher, view, motionEvent);
            }
        });
        this.mScreenshotLayout.post(new Runnable() { // from class: com.android.systemui.screenshot.-$$Lambda$GlobalScreenshot$LG6okkBHXSEdfMyAztakVxsGuDI
            @Override // java.lang.Runnable
            public final void run() {
                this.f$0.lambda$takeScreenshotPartial$2$GlobalScreenshot();
            }
        });
    }

    public /* synthetic */ boolean lambda$takeScreenshotPartial$1$GlobalScreenshot(final Consumer consumer, View view, MotionEvent motionEvent) {
        ScreenshotSelectorView screenshotSelectorView = (ScreenshotSelectorView) view;
        int action = motionEvent.getAction();
        if (action == 0) {
            screenshotSelectorView.startSelection((int) motionEvent.getX(), (int) motionEvent.getY());
            return true;
        }
        if (action != 1) {
            if (action != 2) {
                return false;
            }
            screenshotSelectorView.updateSelection((int) motionEvent.getX(), (int) motionEvent.getY());
            return true;
        }
        screenshotSelectorView.setVisibility(8);
        this.mWindowManager.removeView(this.mScreenshotLayout);
        final Rect selectionRect = screenshotSelectorView.getSelectionRect();
        if (selectionRect != null && selectionRect.width() != 0 && selectionRect.height() != 0) {
            this.mScreenshotLayout.post(new Runnable() { // from class: com.android.systemui.screenshot.-$$Lambda$GlobalScreenshot$PFXj0EYTJFqAQYQMfqoUplGErSo
                @Override // java.lang.Runnable
                public final void run() {
                    this.f$0.lambda$takeScreenshotPartial$0$GlobalScreenshot(consumer, selectionRect);
                }
            });
        }
        screenshotSelectorView.stopSelection();
        return true;
    }

    public /* synthetic */ void lambda$takeScreenshotPartial$2$GlobalScreenshot() {
        this.mScreenshotSelectorView.setVisibility(0);
        this.mScreenshotSelectorView.requestFocus();
    }

    void stopScreenshot() {
        if (this.mScreenshotSelectorView.getSelectionRect() != null) {
            this.mWindowManager.removeView(this.mScreenshotLayout);
            this.mScreenshotSelectorView.stopSelection();
        }
    }

    void dismissScreenshot(String reason, boolean immediate) {
        Log.v(TAG, "clearing screenshot: " + reason);
        this.mScreenshotHandler.removeMessages(2);
        this.mScreenshotLayout.getViewTreeObserver().removeOnComputeInternalInsetsListener(this);
        if (!immediate) {
            AnimatorSet animatorSetCreateScreenshotDismissAnimation = createScreenshotDismissAnimation();
            this.mDismissAnimation = animatorSetCreateScreenshotDismissAnimation;
            animatorSetCreateScreenshotDismissAnimation.addListener(new AnimatorListenerAdapter() { // from class: com.android.systemui.screenshot.GlobalScreenshot.2
                @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
                public void onAnimationEnd(Animator animation) {
                    super.onAnimationEnd(animation);
                    GlobalScreenshot.this.clearScreenshot();
                }
            });
            this.mDismissAnimation.start();
            return;
        }
        clearScreenshot();
    }

    /* JADX WARN: Removed duplicated region for block: B:34:0x0045  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private void onConfigChanged(android.content.res.Configuration r5) {
        /*
            r4 = this;
            boolean r0 = r5.isNightModeActive()
            r1 = 0
            r2 = 1
            if (r0 == 0) goto Lf
            boolean r0 = r4.mInDarkMode
            if (r0 != 0) goto L17
            r4.mInDarkMode = r2
            goto L15
        Lf:
            boolean r0 = r4.mInDarkMode
            if (r0 == 0) goto L17
            r4.mInDarkMode = r1
        L15:
            r0 = r2
            goto L18
        L17:
            r0 = r1
        L18:
            int r3 = r5.getLayoutDirection()
            if (r3 == 0) goto L28
            if (r3 == r2) goto L21
            goto L2f
        L21:
            boolean r3 = r4.mDirectionLTR
            if (r3 == 0) goto L2f
            r4.mDirectionLTR = r1
            goto L2e
        L28:
            boolean r3 = r4.mDirectionLTR
            if (r3 != 0) goto L2f
            r4.mDirectionLTR = r2
        L2e:
            r0 = r2
        L2f:
            int r5 = r5.orientation
            if (r5 == r2) goto L3e
            r3 = 2
            if (r5 == r3) goto L37
            goto L45
        L37:
            boolean r5 = r4.mOrientationPortrait
            if (r5 == 0) goto L45
            r4.mOrientationPortrait = r1
            goto L46
        L3e:
            boolean r5 = r4.mOrientationPortrait
            if (r5 != 0) goto L45
            r4.mOrientationPortrait = r2
            goto L46
        L45:
            r2 = r0
        L46:
            if (r2 == 0) goto L4b
            r4.reloadAssets()
        L4b:
            android.content.Context r5 = r4.mContext
            android.content.res.Resources r5 = r5.getResources()
            r0 = 17694851(0x10e0083, float:2.6081648E-38)
            int r5 = r5.getInteger(r0)
            r4.mNavMode = r5
            return
        */
        throw new UnsupportedOperationException("Method not decompiled: com.android.systemui.screenshot.GlobalScreenshot.onConfigChanged(android.content.res.Configuration):void");
    }

    private void reloadAssets() {
        View view = this.mScreenshotLayout;
        boolean z = view != null && view.isAttachedToWindow();
        if (z) {
            this.mWindowManager.removeView(this.mScreenshotLayout);
        }
        View viewInflate = LayoutInflater.from(this.mContext).inflate(R.layout.global_screenshot, (ViewGroup) null);
        this.mScreenshotLayout = viewInflate;
        viewInflate.setOnTouchListener(new View.OnTouchListener() { // from class: com.android.systemui.screenshot.-$$Lambda$GlobalScreenshot$J9DsRS6FoLzonWY3TapinYd7aS8
            @Override // android.view.View.OnTouchListener
            public final boolean onTouch(View view2, MotionEvent motionEvent) {
                return this.f$0.lambda$reloadAssets$3$GlobalScreenshot(view2, motionEvent);
            }
        });
        this.mScreenshotLayout.setOnApplyWindowInsetsListener(new View.OnApplyWindowInsetsListener() { // from class: com.android.systemui.screenshot.-$$Lambda$GlobalScreenshot$2Ex1WK9y8WPYqSMVJcR0b1rWrlE
            @Override // android.view.View.OnApplyWindowInsetsListener
            public final WindowInsets onApplyWindowInsets(View view2, WindowInsets windowInsets) {
                return this.f$0.lambda$reloadAssets$4$GlobalScreenshot(view2, windowInsets);
            }
        });
        this.mScreenshotLayout.setOnKeyListener(new View.OnKeyListener() { // from class: com.android.systemui.screenshot.-$$Lambda$GlobalScreenshot$gkQL4jgLH8ZJFU9I0pSSIr8kwbg
            @Override // android.view.View.OnKeyListener
            public final boolean onKey(View view2, int i, KeyEvent keyEvent) {
                return this.f$0.lambda$reloadAssets$5$GlobalScreenshot(view2, i, keyEvent);
            }
        });
        this.mScreenshotLayout.setFocusableInTouchMode(true);
        this.mScreenshotLayout.requestFocus();
        ImageView imageView = (ImageView) this.mScreenshotLayout.findViewById(R.id.global_screenshot_animated_view);
        this.mScreenshotAnimatedView = imageView;
        imageView.setClipToOutline(true);
        this.mScreenshotAnimatedView.setOutlineProvider(new ViewOutlineProvider() { // from class: com.android.systemui.screenshot.GlobalScreenshot.3
            @Override // android.view.ViewOutlineProvider
            public void getOutline(View view2, Outline outline) {
                outline.setRoundRect(new Rect(0, 0, view2.getWidth(), view2.getHeight()), view2.getWidth() * GlobalScreenshot.ROUNDED_CORNER_RADIUS);
            }
        });
        this.mScreenshotPreviewStroke = (ImageView) this.mScreenshotLayout.findViewById(R.id.global_screenshot_preview_stroke);
        ImageView imageView2 = (ImageView) this.mScreenshotLayout.findViewById(R.id.global_screenshot_preview);
        this.mScreenshotPreview = imageView2;
        imageView2.setClipToOutline(true);
        this.mScreenshotPreview.setOutlineProvider(new ViewOutlineProvider() { // from class: com.android.systemui.screenshot.GlobalScreenshot.4
            @Override // android.view.ViewOutlineProvider
            public void getOutline(View view2, Outline outline) {
                outline.setRoundRect(new Rect(0, 0, view2.getWidth(), view2.getHeight()), view2.getWidth() * GlobalScreenshot.ROUNDED_CORNER_RADIUS);
            }
        });
        this.mActionsContainerBackground = (ImageView) this.mScreenshotLayout.findViewById(R.id.global_screenshot_actions_container_background);
        this.mActionsContainer = (HorizontalScrollView) this.mScreenshotLayout.findViewById(R.id.global_screenshot_actions_container);
        this.mActionsView = (LinearLayout) this.mScreenshotLayout.findViewById(R.id.global_screenshot_actions);
        this.mBackgroundProtection = (ImageView) this.mScreenshotLayout.findViewById(R.id.global_screenshot_actions_background);
        FrameLayout frameLayout = (FrameLayout) this.mScreenshotLayout.findViewById(R.id.global_screenshot_dismiss_button);
        this.mDismissButton = frameLayout;
        frameLayout.setOnClickListener(new View.OnClickListener() { // from class: com.android.systemui.screenshot.-$$Lambda$GlobalScreenshot$1IQU_xi7NG4tv615_KePojiR1Aw
            @Override // android.view.View.OnClickListener
            public final void onClick(View view2) {
                this.f$0.lambda$reloadAssets$6$GlobalScreenshot(view2);
            }
        });
        this.mScreenshotFlash = (ImageView) this.mScreenshotLayout.findViewById(R.id.global_screenshot_flash);
        this.mScreenshotSelectorView = (ScreenshotSelectorView) this.mScreenshotLayout.findViewById(R.id.global_screenshot_selector);
        this.mScreenshotLayout.setFocusable(true);
        this.mScreenshotSelectorView.setFocusable(true);
        this.mScreenshotSelectorView.setFocusableInTouchMode(true);
        this.mScreenshotAnimatedView.setPivotX(0.0f);
        this.mScreenshotAnimatedView.setPivotY(0.0f);
        this.mActionsContainer.setScrollX(0);
        if (z) {
            this.mWindowManager.addView(this.mScreenshotLayout, this.mWindowLayoutParams);
        }
    }

    public /* synthetic */ boolean lambda$reloadAssets$3$GlobalScreenshot(View view, MotionEvent motionEvent) {
        if (motionEvent.getActionMasked() == 4) {
            setWindowFocusable(false);
        }
        return false;
    }

    public /* synthetic */ WindowInsets lambda$reloadAssets$4$GlobalScreenshot(View view, WindowInsets windowInsets) {
        if (QuickStepContract.isGesturalMode(this.mNavMode)) {
            Insets insets = windowInsets.getInsets(WindowInsets.Type.systemGestures());
            this.mLeftInset = insets.left;
            this.mRightInset = insets.right;
        } else {
            this.mRightInset = 0;
            this.mLeftInset = 0;
        }
        return this.mScreenshotLayout.onApplyWindowInsets(windowInsets);
    }

    public /* synthetic */ boolean lambda$reloadAssets$5$GlobalScreenshot(View view, int i, KeyEvent keyEvent) {
        if (i != 4) {
            return false;
        }
        dismissScreenshot("back pressed", false);
        return true;
    }

    public /* synthetic */ void lambda$reloadAssets$6$GlobalScreenshot(View view) {
        this.mUiEventLogger.log(ScreenshotEvent.SCREENSHOT_EXPLICIT_DISMISSAL);
        dismissScreenshot("dismiss_button", false);
        this.mOnCompleteRunnable.run();
    }

    /* JADX INFO: Access modifiers changed from: private */
    /* JADX INFO: renamed from: takeScreenshotInternal, reason: merged with bridge method [inline-methods] */
    public void lambda$takeScreenshotPartial$0$GlobalScreenshot(Consumer<Uri> finisher, Rect crop) {
        saveScreenshot(SurfaceControl.screenshot(crop, crop.width(), crop.height(), this.mDisplay.getRotation()), finisher, new Rect(crop), Insets.NONE, true);
    }

    private void saveScreenshot(Bitmap screenshot, Consumer<Uri> finisher, Rect screenRect, Insets screenInsets, boolean showFlash) {
        if (this.mScreenshotLayout.isAttachedToWindow()) {
            Animator animator = this.mDismissAnimation;
            if (animator == null || !animator.isRunning()) {
                this.mUiEventLogger.log(ScreenshotEvent.SCREENSHOT_REENTERED);
            }
            dismissScreenshot("new screenshot requested", true);
        }
        this.mScreenBitmap = screenshot;
        if (screenshot == null) {
            this.mNotificationsController.notifyScreenshotError(R.string.screenshot_failed_to_capture_text);
            finisher.accept(null);
            this.mOnCompleteRunnable.run();
        } else {
            if (!isUserSetupComplete()) {
                saveScreenshotAndToast(finisher);
                return;
            }
            this.mScreenBitmap.setHasAlpha(false);
            this.mScreenBitmap.prepareToDraw();
            onConfigChanged(this.mContext.getResources().getConfiguration());
            Animator animator2 = this.mDismissAnimation;
            if (animator2 != null && animator2.isRunning()) {
                this.mDismissAnimation.cancel();
            }
            setWindowFocusable(true);
            startAnimation(finisher, screenRect, screenInsets, showFlash);
        }
    }

    private void saveScreenshotAndToast(Consumer<Uri> finisher) {
        this.mScreenshotHandler.post(new Runnable() { // from class: com.android.systemui.screenshot.-$$Lambda$GlobalScreenshot$LiwHfnNt04BRYFWueA4HNuUNXkQ
            @Override // java.lang.Runnable
            public final void run() {
                this.f$0.lambda$saveScreenshotAndToast$7$GlobalScreenshot();
            }
        });
        saveScreenshotInWorkerThread(finisher, new AnonymousClass5(finisher));
    }

    public /* synthetic */ void lambda$saveScreenshotAndToast$7$GlobalScreenshot() {
        this.mCameraSound.play(0);
    }

    /* JADX INFO: renamed from: com.android.systemui.screenshot.GlobalScreenshot$5, reason: invalid class name */
    class AnonymousClass5 extends ActionsReadyListener {
        final /* synthetic */ Consumer val$finisher;

        AnonymousClass5(final Consumer val$finisher) {
            this.val$finisher = val$finisher;
        }

        @Override // com.android.systemui.screenshot.GlobalScreenshot.ActionsReadyListener
        void onActionsReady(SavedImageData imageData) {
            this.val$finisher.accept(imageData.uri);
            if (imageData.uri == null) {
                GlobalScreenshot.this.mUiEventLogger.log(ScreenshotEvent.SCREENSHOT_NOT_SAVED);
                GlobalScreenshot.this.mNotificationsController.notifyScreenshotError(R.string.screenshot_failed_to_capture_text);
            } else {
                GlobalScreenshot.this.mUiEventLogger.log(ScreenshotEvent.SCREENSHOT_SAVED);
                GlobalScreenshot.this.mScreenshotHandler.post(new Runnable() { // from class: com.android.systemui.screenshot.-$$Lambda$GlobalScreenshot$5$Eyefed6UZezIGZOfqoIfgr5035I
                    @Override // java.lang.Runnable
                    public final void run() {
                        this.f$0.lambda$onActionsReady$0$GlobalScreenshot$5();
                    }
                });
            }
        }

        public /* synthetic */ void lambda$onActionsReady$0$GlobalScreenshot$5() {
            Toast.makeText(GlobalScreenshot.this.mContext, R.string.screenshot_saved_title, 0).show();
        }
    }

    private void startAnimation(final Consumer<Uri> finisher, final Rect screenRect, final Insets screenInsets, final boolean showFlash) {
        this.mScreenshotHandler.post(new Runnable() { // from class: com.android.systemui.screenshot.-$$Lambda$GlobalScreenshot$Aiey0DyHXEyHs-hB5BOaTqxDfgk
            @Override // java.lang.Runnable
            public final void run() {
                this.f$0.lambda$startAnimation$9$GlobalScreenshot(screenInsets, screenRect, showFlash, finisher);
            }
        });
    }

    public /* synthetic */ void lambda$startAnimation$9$GlobalScreenshot(Insets insets, final Rect rect, final boolean z, final Consumer consumer) {
        if (!this.mScreenshotLayout.isAttachedToWindow()) {
            this.mWindowManager.addView(this.mScreenshotLayout, this.mWindowLayoutParams);
        }
        this.mScreenshotAnimatedView.setImageDrawable(createScreenDrawable(this.mScreenBitmap, insets));
        setAnimatedViewSize(rect.width(), rect.height());
        this.mScreenshotAnimatedView.setVisibility(8);
        this.mScreenshotPreview.setImageDrawable(createScreenDrawable(this.mScreenBitmap, insets));
        this.mScreenshotPreview.setVisibility(4);
        this.mScreenshotPreviewStroke.setVisibility(4);
        this.mScreenshotHandler.post(new Runnable() { // from class: com.android.systemui.screenshot.-$$Lambda$GlobalScreenshot$eECWY1vBlQkjWdDud7etlMBH4Hk
            @Override // java.lang.Runnable
            public final void run() {
                this.f$0.lambda$startAnimation$8$GlobalScreenshot(rect, z, consumer);
            }
        });
    }

    public /* synthetic */ void lambda$startAnimation$8$GlobalScreenshot(Rect rect, boolean z, Consumer consumer) {
        this.mScreenshotLayout.getViewTreeObserver().addOnComputeInternalInsetsListener(this);
        this.mScreenshotAnimation = createScreenshotDropInAnimation(rect, z);
        saveScreenshotInWorkerThread(consumer, new ActionsReadyListener() { // from class: com.android.systemui.screenshot.GlobalScreenshot.6
            @Override // com.android.systemui.screenshot.GlobalScreenshot.ActionsReadyListener
            void onActionsReady(SavedImageData imageData) {
                GlobalScreenshot.this.showUiOnActionsReady(imageData);
            }
        });
        if (Util.isSoundScreenShot(this.mContext)) {
            this.mCameraSound.play(0);
        }
        this.mScreenshotPreview.setLayerType(2, null);
        this.mScreenshotPreview.buildLayer();
        this.mScreenshotAnimation.start();
    }

    private void saveScreenshotInWorkerThread(Consumer<Uri> finisher, ActionsReadyListener actionsReadyListener) {
        SaveImageInBackgroundData saveImageInBackgroundData = new SaveImageInBackgroundData();
        saveImageInBackgroundData.image = this.mScreenBitmap;
        saveImageInBackgroundData.finisher = finisher;
        saveImageInBackgroundData.mActionsReadyListener = actionsReadyListener;
        SaveImageInBackgroundTask saveImageInBackgroundTask = this.mSaveInBgTask;
        if (saveImageInBackgroundTask != null) {
            saveImageInBackgroundTask.setActionsReadyListener(new ActionsReadyListener() { // from class: com.android.systemui.screenshot.GlobalScreenshot.7
                @Override // com.android.systemui.screenshot.GlobalScreenshot.ActionsReadyListener
                void onActionsReady(SavedImageData imageData) {
                    GlobalScreenshot.this.logSuccessOnActionsReady(imageData);
                }
            });
        }
        SaveImageInBackgroundTask saveImageInBackgroundTask2 = new SaveImageInBackgroundTask(this.mContext, this.mScreenshotSmartActions, saveImageInBackgroundData);
        this.mSaveInBgTask = saveImageInBackgroundTask2;
        saveImageInBackgroundTask2.execute(new Void[0]);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void showUiOnActionsReady(final SavedImageData imageData) {
        logSuccessOnActionsReady(imageData);
        long recommendedTimeoutMillis = ((AccessibilityManager) this.mContext.getSystemService("accessibility")).getRecommendedTimeoutMillis(6000, 4);
        this.mScreenshotHandler.removeMessages(2);
        Handler handler = this.mScreenshotHandler;
        handler.sendMessageDelayed(handler.obtainMessage(2), recommendedTimeoutMillis);
        if (imageData.uri != null) {
            this.mScreenshotHandler.post(new Runnable() { // from class: com.android.systemui.screenshot.-$$Lambda$GlobalScreenshot$OHHT96FbjhOTBSgu-6gOYyFtyHg
                @Override // java.lang.Runnable
                public final void run() {
                    this.f$0.lambda$showUiOnActionsReady$10$GlobalScreenshot(imageData);
                }
            });
        }
    }

    public /* synthetic */ void lambda$showUiOnActionsReady$10$GlobalScreenshot(final SavedImageData savedImageData) {
        Animator animator = this.mScreenshotAnimation;
        if (animator != null && animator.isRunning()) {
            this.mScreenshotAnimation.addListener(new AnimatorListenerAdapter() { // from class: com.android.systemui.screenshot.GlobalScreenshot.8
                @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
                public void onAnimationEnd(Animator animation) {
                    super.onAnimationEnd(animation);
                    GlobalScreenshot.this.createScreenshotActions(savedImageData);
                }
            });
        } else {
            createScreenshotActions(savedImageData);
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void createScreenshotActions(final SavedImageData imageData) {
        this.mScreenshotPreview.setOnClickListener(new View.OnClickListener() { // from class: com.android.systemui.screenshot.-$$Lambda$GlobalScreenshot$YGLMm9LR45bgPRSmtwZiC2hRCyk
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                this.f$0.lambda$createScreenshotActions$11$GlobalScreenshot(imageData, view);
            }
        });
    }

    public /* synthetic */ void lambda$createScreenshotActions$11$GlobalScreenshot(SavedImageData savedImageData, View view) {
        try {
            openFile(savedImageData.uri);
            this.mUiEventLogger.log(ScreenshotEvent.SCREENSHOT_PREVIEW_TAPPED);
            dismissScreenshot("screenshot preview tapped", false);
            this.mOnCompleteRunnable.run();
        } catch (Exception e) {
            Log.e(TAG, "Intent cancelled", e);
        }
    }

    private void openFile(Uri uri) {
        try {
            Log.i(TAG, "openFile");
            Intent intent = new Intent("android.intent.action.VIEW");
            intent.addFlags(1);
            intent.setDataAndType(uri, "image/png");
            intent.addFlags(268435456);
            this.mContext.startActivity(intent);
        } catch (ActivityNotFoundException e) {
            e.printStackTrace();
        } catch (Exception e2) {
            e2.printStackTrace();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void logSuccessOnActionsReady(SavedImageData imageData) {
        if (imageData.uri == null) {
            this.mUiEventLogger.log(ScreenshotEvent.SCREENSHOT_NOT_SAVED);
            this.mNotificationsController.notifyScreenshotError(R.string.screenshot_failed_to_capture_text);
        } else {
            this.mUiEventLogger.log(ScreenshotEvent.SCREENSHOT_SAVED);
        }
    }

    private AnimatorSet createScreenshotDropInAnimation(final Rect bounds, boolean showFlash) {
        this.mScreenshotPreview.getBoundsOnScreen(new Rect());
        final float fWidth = this.mCornerSizeX / (this.mOrientationPortrait ? bounds.width() : bounds.height());
        this.mScreenshotAnimatedView.setScaleX(1.0f);
        this.mScreenshotAnimatedView.setScaleY(1.0f);
        this.mDismissButton.setAlpha(0.0f);
        this.mDismissButton.setVisibility(0);
        AnimatorSet animatorSet = new AnimatorSet();
        ValueAnimator valueAnimatorOfFloat = ValueAnimator.ofFloat(0.0f, 1.0f);
        valueAnimatorOfFloat.setDuration(SCREENSHOT_FLASH_IN_DURATION_MS);
        valueAnimatorOfFloat.setInterpolator(this.mFastOutSlowIn);
        valueAnimatorOfFloat.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() { // from class: com.android.systemui.screenshot.-$$Lambda$GlobalScreenshot$_Tw2fFgCyW7By4-XqokddmWDheY
            @Override // android.animation.ValueAnimator.AnimatorUpdateListener
            public final void onAnimationUpdate(ValueAnimator valueAnimator) {
                this.f$0.lambda$createScreenshotDropInAnimation$12$GlobalScreenshot(valueAnimator);
            }
        });
        ValueAnimator valueAnimatorOfFloat2 = ValueAnimator.ofFloat(1.0f, 0.0f);
        valueAnimatorOfFloat2.setDuration(SCREENSHOT_FLASH_OUT_DURATION_MS);
        valueAnimatorOfFloat2.setInterpolator(this.mFastOutSlowIn);
        valueAnimatorOfFloat2.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() { // from class: com.android.systemui.screenshot.-$$Lambda$GlobalScreenshot$VrllgXH8m3CKxD56hJrPowIm1hE
            @Override // android.animation.ValueAnimator.AnimatorUpdateListener
            public final void onAnimationUpdate(ValueAnimator valueAnimator) {
                this.f$0.lambda$createScreenshotDropInAnimation$13$GlobalScreenshot(valueAnimator);
            }
        });
        final PointF pointF = new PointF(bounds.centerX(), bounds.centerY());
        final PointF pointF2 = new PointF(r0.centerX(), r0.centerY());
        ValueAnimator valueAnimatorOfFloat3 = ValueAnimator.ofFloat(0.0f, 1.0f);
        valueAnimatorOfFloat3.setDuration(500L);
        final float f = 0.468f;
        final float f2 = 0.4f;
        final float f3 = 0.468f;
        valueAnimatorOfFloat3.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() { // from class: com.android.systemui.screenshot.-$$Lambda$GlobalScreenshot$ZczOywTu4Xt1JPCC1-wHjFsLU7A
            @Override // android.animation.ValueAnimator.AnimatorUpdateListener
            public final void onAnimationUpdate(ValueAnimator valueAnimator) {
                this.f$0.lambda$createScreenshotDropInAnimation$14$GlobalScreenshot(f3, fWidth, f, pointF, pointF2, bounds, f2, valueAnimator);
            }
        });
        valueAnimatorOfFloat3.addListener(new AnimatorListenerAdapter() { // from class: com.android.systemui.screenshot.GlobalScreenshot.9
            @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
            public void onAnimationStart(Animator animation) {
                super.onAnimationStart(animation);
                GlobalScreenshot.this.mScreenshotAnimatedView.setVisibility(0);
            }
        });
        this.mScreenshotFlash.setAlpha(0.0f);
        this.mScreenshotFlash.setVisibility(8);
        if (showFlash) {
            animatorSet.play(valueAnimatorOfFloat2).after(valueAnimatorOfFloat);
            animatorSet.play(valueAnimatorOfFloat2).with(valueAnimatorOfFloat3);
        } else {
            animatorSet.play(valueAnimatorOfFloat3);
        }
        animatorSet.addListener(new AnimatorListenerAdapter() { // from class: com.android.systemui.screenshot.GlobalScreenshot.10
            @Override // android.animation.AnimatorListenerAdapter, android.animation.Animator.AnimatorListener
            public void onAnimationEnd(Animator animation) {
                float fWidth2;
                super.onAnimationEnd(animation);
                GlobalScreenshot.this.mDismissButton.setAlpha(1.0f);
                float width = GlobalScreenshot.this.mDismissButton.getWidth() / 2.0f;
                if (GlobalScreenshot.this.mDirectionLTR) {
                    fWidth2 = (pointF2.x - width) + ((bounds.width() * fWidth) / 2.0f);
                } else {
                    fWidth2 = (pointF2.x - width) - ((bounds.width() * fWidth) / 2.0f);
                }
                GlobalScreenshot.this.mDismissButton.setX(fWidth2);
                GlobalScreenshot.this.mDismissButton.setY((pointF2.y - width) - ((bounds.height() * fWidth) / 2.0f));
                GlobalScreenshot.this.mScreenshotAnimatedView.setScaleX(1.0f);
                GlobalScreenshot.this.mScreenshotAnimatedView.setScaleY(1.0f);
                GlobalScreenshot.this.mScreenshotAnimatedView.setX(pointF2.x - ((bounds.width() * fWidth) / 2.0f));
                GlobalScreenshot.this.mScreenshotAnimatedView.setY(pointF2.y - ((bounds.height() * fWidth) / 2.0f));
                GlobalScreenshot.this.mScreenshotAnimatedView.setVisibility(8);
                GlobalScreenshot.this.mScreenshotPreview.setVisibility(0);
                GlobalScreenshot.this.mScreenshotLayout.forceLayout();
                ConstraintLayout.LayoutParams layoutParams = (ConstraintLayout.LayoutParams) GlobalScreenshot.this.mScreenshotPreviewStroke.getLayoutParams();
                GlobalScreenshot.this.mScreenshotPreviewStroke.setVisibility(0);
                float dimension = GlobalScreenshot.this.mContext.getResources().getDimension(R.dimen.dp_3);
                float f4 = 2.0f * dimension;
                layoutParams.width = Math.round(GlobalScreenshot.this.mScreenshotPreview.getWidth() + f4);
                layoutParams.height = Math.round(GlobalScreenshot.this.mScreenshotPreview.getHeight() + f4);
                layoutParams.setMarginStart(Math.round(GlobalScreenshot.this.mContext.getResources().getDimension(R.dimen.screenshot_offset_x) - dimension));
                layoutParams.bottomMargin = Math.round(GlobalScreenshot.this.mContext.getResources().getDimension(R.dimen.screenshot_offset_y) - dimension);
                GlobalScreenshot.this.mScreenshotPreviewStroke.setLayoutParams(layoutParams);
                ObjectAnimator.ofFloat(GlobalScreenshot.this.mScreenshotPreviewStroke, "alpha", 0.0f, 1.0f).setDuration(200L).start();
            }
        });
        return animatorSet;
    }

    public /* synthetic */ void lambda$createScreenshotDropInAnimation$12$GlobalScreenshot(ValueAnimator valueAnimator) {
        this.mScreenshotFlash.setAlpha(((Float) valueAnimator.getAnimatedValue()).floatValue());
    }

    public /* synthetic */ void lambda$createScreenshotDropInAnimation$13$GlobalScreenshot(ValueAnimator valueAnimator) {
        this.mScreenshotFlash.setAlpha(((Float) valueAnimator.getAnimatedValue()).floatValue());
    }

    public /* synthetic */ void lambda$createScreenshotDropInAnimation$14$GlobalScreenshot(float f, float f2, float f3, PointF pointF, PointF pointF2, Rect rect, float f4, ValueAnimator valueAnimator) {
        float animatedFraction = valueAnimator.getAnimatedFraction();
        if (animatedFraction < f) {
            float fLerp = MathUtils.lerp(1.0f, f2, this.mFastOutSlowIn.getInterpolation(animatedFraction / f));
            this.mScreenshotAnimatedView.setScaleX(fLerp);
            this.mScreenshotAnimatedView.setScaleY(fLerp);
        } else {
            this.mScreenshotAnimatedView.setScaleX(f2);
            this.mScreenshotAnimatedView.setScaleY(f2);
        }
        float scaleX = this.mScreenshotAnimatedView.getScaleX();
        float scaleY = this.mScreenshotAnimatedView.getScaleY();
        if (animatedFraction < f3) {
            this.mScreenshotAnimatedView.setX(MathUtils.lerp(pointF.x, pointF2.x, this.mFastOutSlowIn.getInterpolation(animatedFraction / f3)) - ((rect.width() * scaleX) / 2.0f));
        } else {
            this.mScreenshotAnimatedView.setX(pointF2.x - ((rect.width() * scaleX) / 2.0f));
        }
        this.mScreenshotAnimatedView.setY(MathUtils.lerp(pointF.y, pointF2.y, this.mFastOutSlowIn.getInterpolation(animatedFraction)) - ((rect.height() * scaleY) / 2.0f));
        if (animatedFraction >= f4) {
            this.mDismissButton.setAlpha((animatedFraction - f4) / (1.0f - f4));
            float x = this.mScreenshotAnimatedView.getX();
            float y = this.mScreenshotAnimatedView.getY();
            this.mDismissButton.setY(y - (r9.getHeight() / 2.0f));
            if (this.mDirectionLTR) {
                this.mDismissButton.setX((x + (rect.width() * scaleX)) - (this.mDismissButton.getWidth() / 2.0f));
            } else {
                this.mDismissButton.setX(x - (r6.getWidth() / 2.0f));
            }
        }
    }

    private ValueAnimator createScreenshotActionsShadeAnimation(final SavedImageData imageData) {
        LayoutInflater layoutInflaterFrom = LayoutInflater.from(this.mContext);
        this.mActionsView.removeAllViews();
        this.mScreenshotLayout.invalidate();
        this.mScreenshotLayout.requestLayout();
        this.mScreenshotLayout.getViewTreeObserver().dispatchOnGlobalLayout();
        try {
            ActivityManager.getService().resumeAppSwitches();
        } catch (RemoteException unused) {
        }
        final ArrayList arrayList = new ArrayList();
        for (Notification.Action action : imageData.smartActions) {
            ScreenshotActionChip screenshotActionChip = (ScreenshotActionChip) layoutInflaterFrom.inflate(R.layout.global_screenshot_action_chip, (ViewGroup) this.mActionsView, false);
            screenshotActionChip.setText(action.title);
            screenshotActionChip.setIcon(action.getIcon(), false);
            screenshotActionChip.setPendingIntent(action.actionIntent, new Runnable() { // from class: com.android.systemui.screenshot.-$$Lambda$GlobalScreenshot$mWxerc3JcIWkBh2cwbJ_yKTsMYM
                @Override // java.lang.Runnable
                public final void run() {
                    this.f$0.lambda$createScreenshotActionsShadeAnimation$15$GlobalScreenshot();
                }
            });
            this.mActionsView.addView(screenshotActionChip);
            arrayList.add(screenshotActionChip);
        }
        ScreenshotActionChip screenshotActionChip2 = (ScreenshotActionChip) layoutInflaterFrom.inflate(R.layout.global_screenshot_action_chip, (ViewGroup) this.mActionsView, false);
        screenshotActionChip2.setText(imageData.shareAction.title);
        screenshotActionChip2.setIcon(imageData.shareAction.getIcon(), true);
        screenshotActionChip2.setPendingIntent(imageData.shareAction.actionIntent, new Runnable() { // from class: com.android.systemui.screenshot.-$$Lambda$GlobalScreenshot$8ab5x81Xz2dDNS4Li67F26xOOOo
            @Override // java.lang.Runnable
            public final void run() {
                this.f$0.lambda$createScreenshotActionsShadeAnimation$16$GlobalScreenshot();
            }
        });
        this.mActionsView.addView(screenshotActionChip2);
        arrayList.add(screenshotActionChip2);
        ScreenshotActionChip screenshotActionChip3 = (ScreenshotActionChip) layoutInflaterFrom.inflate(R.layout.global_screenshot_action_chip, (ViewGroup) this.mActionsView, false);
        screenshotActionChip3.setText(imageData.editAction.title);
        screenshotActionChip3.setIcon(imageData.editAction.getIcon(), true);
        screenshotActionChip3.setPendingIntent(imageData.editAction.actionIntent, new Runnable() { // from class: com.android.systemui.screenshot.-$$Lambda$GlobalScreenshot$7DPm9EEsbCN3ZRIvUUMleq8UK-8
            @Override // java.lang.Runnable
            public final void run() {
                this.f$0.lambda$createScreenshotActionsShadeAnimation$17$GlobalScreenshot();
            }
        });
        this.mActionsView.addView(screenshotActionChip3);
        arrayList.add(screenshotActionChip3);
        this.mScreenshotPreview.setOnClickListener(new View.OnClickListener() { // from class: com.android.systemui.screenshot.-$$Lambda$GlobalScreenshot$gKziwcjUXgDjhtDwzOMVfqlReRk
            @Override // android.view.View.OnClickListener
            public final void onClick(View view) {
                this.f$0.lambda$createScreenshotActionsShadeAnimation$18$GlobalScreenshot(imageData, view);
            }
        });
        this.mScreenshotPreview.setContentDescription(imageData.editAction.title);
        LinearLayout linearLayout = this.mActionsView;
        ((LinearLayout.LayoutParams) linearLayout.getChildAt(linearLayout.getChildCount() - 1).getLayoutParams()).setMarginEnd(0);
        ValueAnimator valueAnimatorOfFloat = ValueAnimator.ofFloat(0.0f, 1.0f);
        valueAnimatorOfFloat.setDuration(SCREENSHOT_ACTIONS_EXPANSION_DURATION_MS);
        final float f = 0.25f;
        this.mActionsContainer.setAlpha(0.0f);
        this.mActionsContainerBackground.setAlpha(0.0f);
        this.mActionsContainer.setVisibility(0);
        this.mActionsContainerBackground.setVisibility(0);
        valueAnimatorOfFloat.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() { // from class: com.android.systemui.screenshot.-$$Lambda$GlobalScreenshot$rgvA5egOqwD13x_ZyPHP5bv6y4Y
            @Override // android.animation.ValueAnimator.AnimatorUpdateListener
            public final void onAnimationUpdate(ValueAnimator valueAnimator) {
                this.f$0.lambda$createScreenshotActionsShadeAnimation$19$GlobalScreenshot(f, arrayList, valueAnimator);
            }
        });
        return valueAnimatorOfFloat;
    }

    public /* synthetic */ void lambda$createScreenshotActionsShadeAnimation$15$GlobalScreenshot() {
        this.mUiEventLogger.log(ScreenshotEvent.SCREENSHOT_SMART_ACTION_TAPPED);
        dismissScreenshot("chip tapped", false);
        this.mOnCompleteRunnable.run();
    }

    public /* synthetic */ void lambda$createScreenshotActionsShadeAnimation$16$GlobalScreenshot() {
        this.mUiEventLogger.log(ScreenshotEvent.SCREENSHOT_SHARE_TAPPED);
        dismissScreenshot("chip tapped", false);
        this.mOnCompleteRunnable.run();
    }

    public /* synthetic */ void lambda$createScreenshotActionsShadeAnimation$17$GlobalScreenshot() {
        this.mUiEventLogger.log(ScreenshotEvent.SCREENSHOT_EDIT_TAPPED);
        dismissScreenshot("chip tapped", false);
        this.mOnCompleteRunnable.run();
    }

    public /* synthetic */ void lambda$createScreenshotActionsShadeAnimation$18$GlobalScreenshot(SavedImageData savedImageData, View view) {
        try {
            savedImageData.editAction.actionIntent.send();
            this.mUiEventLogger.log(ScreenshotEvent.SCREENSHOT_PREVIEW_TAPPED);
            dismissScreenshot("screenshot preview tapped", false);
            this.mOnCompleteRunnable.run();
        } catch (PendingIntent.CanceledException e) {
            Log.e(TAG, "Intent cancelled", e);
        }
    }

    public /* synthetic */ void lambda$createScreenshotActionsShadeAnimation$19$GlobalScreenshot(float f, ArrayList arrayList, ValueAnimator valueAnimator) {
        float animatedFraction = valueAnimator.getAnimatedFraction();
        this.mBackgroundProtection.setAlpha(animatedFraction);
        float f2 = animatedFraction < f ? animatedFraction / f : 1.0f;
        this.mActionsContainer.setAlpha(f2);
        this.mActionsContainerBackground.setAlpha(f2);
        float f3 = (0.3f * animatedFraction) + 0.7f;
        this.mActionsContainer.setScaleX(f3);
        this.mActionsContainerBackground.setScaleX(f3);
        Iterator it = arrayList.iterator();
        while (it.hasNext()) {
            ScreenshotActionChip screenshotActionChip = (ScreenshotActionChip) it.next();
            screenshotActionChip.setAlpha(animatedFraction);
            screenshotActionChip.setScaleX(1.0f / f3);
        }
        HorizontalScrollView horizontalScrollView = this.mActionsContainer;
        horizontalScrollView.setScrollX(this.mDirectionLTR ? 0 : horizontalScrollView.getWidth());
        this.mActionsContainer.setPivotX(this.mDirectionLTR ? 0.0f : r4.getWidth());
        this.mActionsContainerBackground.setPivotX(this.mDirectionLTR ? 0.0f : r4.getWidth());
    }

    private AnimatorSet createScreenshotDismissAnimation() {
        ValueAnimator valueAnimatorOfFloat = ValueAnimator.ofFloat(0.0f, 1.0f);
        valueAnimatorOfFloat.setStartDelay(SCREENSHOT_DISMISS_ALPHA_OFFSET_MS);
        valueAnimatorOfFloat.setDuration(SCREENSHOT_DISMISS_ALPHA_DURATION_MS);
        valueAnimatorOfFloat.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() { // from class: com.android.systemui.screenshot.-$$Lambda$GlobalScreenshot$vPxTSMpFL916ZPXUGfVmeaGNSWI
            @Override // android.animation.ValueAnimator.AnimatorUpdateListener
            public final void onAnimationUpdate(ValueAnimator valueAnimator) {
                this.f$0.lambda$createScreenshotDismissAnimation$20$GlobalScreenshot(valueAnimator);
            }
        });
        ValueAnimator valueAnimatorOfFloat2 = ValueAnimator.ofFloat(0.0f, 1.0f);
        valueAnimatorOfFloat2.setInterpolator(this.mAccelerateInterpolator);
        valueAnimatorOfFloat2.setDuration(SCREENSHOT_DISMISS_Y_DURATION_MS);
        final float translationY = this.mScreenshotPreview.getTranslationY();
        final float translationY2 = this.mDismissButton.getTranslationY();
        valueAnimatorOfFloat2.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() { // from class: com.android.systemui.screenshot.-$$Lambda$GlobalScreenshot$87CGMBmUjyz9TMjQ43yUgklPXa0
            @Override // android.animation.ValueAnimator.AnimatorUpdateListener
            public final void onAnimationUpdate(ValueAnimator valueAnimator) {
                this.f$0.lambda$createScreenshotDismissAnimation$21$GlobalScreenshot(translationY, translationY2, valueAnimator);
            }
        });
        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.play(valueAnimatorOfFloat2).with(valueAnimatorOfFloat);
        return animatorSet;
    }

    public /* synthetic */ void lambda$createScreenshotDismissAnimation$20$GlobalScreenshot(ValueAnimator valueAnimator) {
        this.mScreenshotLayout.setAlpha(1.0f - valueAnimator.getAnimatedFraction());
    }

    public /* synthetic */ void lambda$createScreenshotDismissAnimation$21$GlobalScreenshot(float f, float f2, ValueAnimator valueAnimator) {
        float fLerp = MathUtils.lerp(0.0f, this.mDismissDeltaY, valueAnimator.getAnimatedFraction());
        float f3 = f + fLerp;
        this.mScreenshotPreview.setTranslationY(f3);
        this.mScreenshotPreviewStroke.setTranslationY(f3);
        this.mDismissButton.setTranslationY(f2 + fLerp);
        this.mActionsContainer.setTranslationY(fLerp);
        this.mActionsContainerBackground.setTranslationY(fLerp);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void clearScreenshot() {
        if (this.mScreenshotLayout.isAttachedToWindow()) {
            this.mWindowManager.removeView(this.mScreenshotLayout);
        }
        this.mScreenshotPreview.setImageDrawable(null);
        this.mScreenshotAnimatedView.setImageDrawable(null);
        this.mScreenshotAnimatedView.setVisibility(8);
        this.mActionsContainerBackground.setVisibility(8);
        this.mActionsContainer.setVisibility(8);
        this.mBackgroundProtection.setAlpha(0.0f);
        this.mDismissButton.setVisibility(8);
        this.mScreenshotPreview.setVisibility(8);
        this.mScreenshotPreview.setLayerType(0, null);
        this.mScreenshotPreview.setContentDescription(this.mContext.getResources().getString(R.string.screenshot_preview_description));
        this.mScreenshotPreview.setOnClickListener(null);
        this.mScreenshotLayout.setAlpha(1.0f);
        this.mDismissButton.setTranslationY(0.0f);
        this.mActionsContainer.setTranslationY(0.0f);
        this.mActionsContainerBackground.setTranslationY(0.0f);
        this.mScreenshotPreview.setTranslationY(0.0f);
        this.mScreenshotPreviewStroke.setVisibility(8);
        this.mScreenshotPreviewStroke.setTranslationY(0.0f);
    }

    private void setAnimatedViewSize(int width, int height) {
        ViewGroup.LayoutParams layoutParams = this.mScreenshotAnimatedView.getLayoutParams();
        layoutParams.width = width;
        layoutParams.height = height;
        this.mScreenshotAnimatedView.setLayoutParams(layoutParams);
    }

    private void setWindowFocusable(boolean focusable) {
        if (focusable) {
            this.mWindowLayoutParams.flags &= -9;
        } else {
            this.mWindowLayoutParams.flags |= 8;
        }
        if (this.mScreenshotLayout.isAttachedToWindow()) {
            this.mWindowManager.updateViewLayout(this.mScreenshotLayout, this.mWindowLayoutParams);
        }
    }

    private boolean isUserSetupComplete() {
        return Settings.Secure.getInt(this.mContext.getContentResolver(), SETTINGS_SECURE_USER_SETUP_COMPLETE, 0) == 1;
    }

    private boolean aspectRatiosMatch(Bitmap bitmap, Insets bitmapInsets, Rect screenBounds) {
        int width = (bitmap.getWidth() - bitmapInsets.left) - bitmapInsets.right;
        int height = (bitmap.getHeight() - bitmapInsets.top) - bitmapInsets.bottom;
        if (height == 0 || width == 0 || bitmap.getWidth() == 0 || bitmap.getHeight() == 0) {
            Log.e(TAG, String.format("Provided bitmap and insets create degenerate region: %dx%d %s", Integer.valueOf(bitmap.getWidth()), Integer.valueOf(bitmap.getHeight()), bitmapInsets));
            return false;
        }
        float f = width / height;
        float fWidth = screenBounds.width() / screenBounds.height();
        boolean z = Math.abs(f - fWidth) < 0.1f;
        if (!z) {
            Log.d(TAG, String.format("aspectRatiosMatch: don't match bitmap: %f, bounds: %f", Float.valueOf(f), Float.valueOf(fWidth)));
        }
        return z;
    }

    private Drawable createScreenDrawable(Bitmap bitmap, Insets insets) {
        int width = (bitmap.getWidth() - insets.left) - insets.right;
        int height = (bitmap.getHeight() - insets.top) - insets.bottom;
        BitmapDrawable bitmapDrawable = new BitmapDrawable(this.mContext.getResources(), bitmap);
        if (height == 0 || width == 0 || bitmap.getWidth() == 0 || bitmap.getHeight() == 0) {
            Log.e(TAG, String.format("Can't create insetted drawable, using 0 insets bitmap and insets create degenerate region: %dx%d %s", Integer.valueOf(bitmap.getWidth()), Integer.valueOf(bitmap.getHeight()), insets));
            return bitmapDrawable;
        }
        float f = width;
        float f2 = height;
        InsetDrawable insetDrawable = new InsetDrawable(bitmapDrawable, (insets.left * (-1.0f)) / f, (insets.top * (-1.0f)) / f2, (insets.right * (-1.0f)) / f, (insets.bottom * (-1.0f)) / f2);
        return (insets.left < 0 || insets.top < 0 || insets.right < 0 || insets.bottom < 0) ? new LayerDrawable(new Drawable[]{new ColorDrawable(ViewCompat.MEASURED_STATE_MASK), insetDrawable}) : insetDrawable;
    }
}
