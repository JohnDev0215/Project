package android.view;

import android.app.IAssistDataReceiver;
import android.graphics.Bitmap;
import android.graphics.Insets;
import android.graphics.Point;
import android.graphics.Rect;
import android.graphics.Region;
import android.os.Binder;
import android.os.Bundle;
import android.os.IBinder;
import android.os.IInterface;
import android.os.IRemoteCallback;
import android.os.Parcel;
import android.os.RemoteException;
import android.text.TextUtils;
import android.view.DisplayCutout;
import android.view.IAppTransitionAnimationSpecsFuture;
import android.view.IDisplayFoldListener;
import android.view.IDisplayWindowInsetsController;
import android.view.IDisplayWindowListener;
import android.view.IDisplayWindowRotationController;
import android.view.IOnKeyguardExitResult;
import android.view.IPinnedStackListener;
import android.view.IRotationWatcher;
import android.view.IScrollCaptureController;
import android.view.ISystemGestureExclusionListener;
import android.view.IWallpaperVisibilityListener;
import android.view.IWindow;
import android.view.IWindowSession;
import android.view.IWindowSessionCallback;
import android.view.WindowManager;
import com.android.internal.os.IResultReceiver;
import com.android.internal.policy.IKeyguardDismissCallback;
import com.android.internal.policy.IShortcutService;

/* JADX INFO: loaded from: classes3.dex */
public interface IWindowManager extends IInterface {
    public static final int FIXED_TO_USER_ROTATION_DEFAULT = 0;
    public static final int FIXED_TO_USER_ROTATION_DISABLED = 1;
    public static final int FIXED_TO_USER_ROTATION_ENABLED = 2;

    public static class Default implements IWindowManager {
        @Override // android.view.IWindowManager
        public SurfaceControl addShellRoot(int i, IWindow iWindow, int i2) throws RemoteException {
            return null;
        }

        @Override // android.view.IWindowManager
        public void addWindowToken(IBinder iBinder, int i, int i2) throws RemoteException {
        }

        @Override // android.view.IWindowManager
        public int addWindowTokenWithOptions(IBinder iBinder, int i, int i2, Bundle bundle, String str) throws RemoteException {
            return 0;
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return null;
        }

        @Override // android.view.IWindowManager
        public void clearForcedDisplayDensityForUser(int i, int i2) throws RemoteException {
        }

        @Override // android.view.IWindowManager
        public void clearForcedDisplaySize(int i) throws RemoteException {
        }

        @Override // android.view.IWindowManager
        public boolean clearWindowContentFrameStats(IBinder iBinder) throws RemoteException {
            return false;
        }

        @Override // android.view.IWindowManager
        public void closeSystemDialogs(String str) throws RemoteException {
        }

        @Override // android.view.IWindowManager
        public void createInputConsumer(IBinder iBinder, String str, int i, InputChannel inputChannel) throws RemoteException {
        }

        @Override // android.view.IWindowManager
        public boolean destroyInputConsumer(String str, int i) throws RemoteException {
            return false;
        }

        @Override // android.view.IWindowManager
        public void disableKeyguard(IBinder iBinder, String str, int i) throws RemoteException {
        }

        @Override // android.view.IWindowManager
        public void dismissKeyguard(IKeyguardDismissCallback iKeyguardDismissCallback, CharSequence charSequence) throws RemoteException {
        }

        @Override // android.view.IWindowManager
        public void dontOverrideDisplayInfo(int i) throws RemoteException {
        }

        @Override // android.view.IWindowManager
        public void enableScreenIfNeeded() throws RemoteException {
        }

        @Override // android.view.IWindowManager
        public void endProlongedAnimations() throws RemoteException {
        }

        @Override // android.view.IWindowManager
        public void executeAppTransition() throws RemoteException {
        }

        @Override // android.view.IWindowManager
        public void exitKeyguardSecurely(IOnKeyguardExitResult iOnKeyguardExitResult) throws RemoteException {
        }

        @Override // android.view.IWindowManager
        public void freezeDisplayRotation(int i, int i2) throws RemoteException {
        }

        @Override // android.view.IWindowManager
        public void freezeRotation(int i) throws RemoteException {
        }

        @Override // android.view.IWindowManager
        public float getAnimationScale(int i) throws RemoteException {
            return 0.0f;
        }

        @Override // android.view.IWindowManager
        public float[] getAnimationScales() throws RemoteException {
            return null;
        }

        @Override // android.view.IWindowManager
        public int getBaseDisplayDensity(int i) throws RemoteException {
            return 0;
        }

        @Override // android.view.IWindowManager
        public void getBaseDisplaySize(int i, Point point) throws RemoteException {
        }

        @Override // android.view.IWindowManager
        public float getCurrentAnimatorScale() throws RemoteException {
            return 0.0f;
        }

        @Override // android.view.IWindowManager
        public Region getCurrentImeTouchRegion() throws RemoteException {
            return null;
        }

        @Override // android.view.IWindowManager
        public int getDefaultDisplayRotation() throws RemoteException {
            return 0;
        }

        @Override // android.view.IWindowManager
        public int getDockedStackSide() throws RemoteException {
            return 0;
        }

        @Override // android.view.IWindowManager
        public int getInitialDisplayDensity(int i) throws RemoteException {
            return 0;
        }

        @Override // android.view.IWindowManager
        public void getInitialDisplaySize(int i, Point point) throws RemoteException {
        }

        @Override // android.view.IWindowManager
        public int getNavBarPosition(int i) throws RemoteException {
            return 0;
        }

        @Override // android.view.IWindowManager
        public int getPreferredOptionsPanelGravity(int i) throws RemoteException {
            return 0;
        }

        @Override // android.view.IWindowManager
        public int getRemoveContentMode(int i) throws RemoteException {
            return 0;
        }

        @Override // android.view.IWindowManager
        public void getStableInsets(int i, Rect rect) throws RemoteException {
        }

        @Override // android.view.IWindowManager
        public WindowContentFrameStats getWindowContentFrameStats(IBinder iBinder) throws RemoteException {
            return null;
        }

        @Override // android.view.IWindowManager
        public boolean getWindowInsets(WindowManager.LayoutParams layoutParams, int i, Rect rect, Rect rect2, DisplayCutout.ParcelableWrapper parcelableWrapper, InsetsState insetsState) throws RemoteException {
            return false;
        }

        @Override // android.view.IWindowManager
        public int getWindowingMode(int i) throws RemoteException {
            return 0;
        }

        @Override // android.view.IWindowManager
        public boolean hasNavigationBar(int i) throws RemoteException {
            return false;
        }

        @Override // android.view.IWindowManager
        public void hideTransientBars(int i) throws RemoteException {
        }

        @Override // android.view.IWindowManager
        public boolean injectInputAfterTransactionsApplied(InputEvent inputEvent, int i) throws RemoteException {
            return false;
        }

        @Override // android.view.IWindowManager
        public boolean isDisplayRotationFrozen(int i) throws RemoteException {
            return false;
        }

        @Override // android.view.IWindowManager
        public boolean isKeyguardLocked() throws RemoteException {
            return false;
        }

        @Override // android.view.IWindowManager
        public boolean isKeyguardSecure(int i) throws RemoteException {
            return false;
        }

        @Override // android.view.IWindowManager
        public boolean isLayerTracing() throws RemoteException {
            return false;
        }

        @Override // android.view.IWindowManager
        public boolean isRotationFrozen() throws RemoteException {
            return false;
        }

        @Override // android.view.IWindowManager
        public boolean isSafeModeEnabled() throws RemoteException {
            return false;
        }

        @Override // android.view.IWindowManager
        public boolean isViewServerRunning() throws RemoteException {
            return false;
        }

        @Override // android.view.IWindowManager
        public boolean isWindowToken(IBinder iBinder) throws RemoteException {
            return false;
        }

        @Override // android.view.IWindowManager
        public boolean isWindowTraceEnabled() throws RemoteException {
            return false;
        }

        @Override // android.view.IWindowManager
        public void lockNow(Bundle bundle) throws RemoteException {
        }

        @Override // android.view.IWindowManager
        public boolean mirrorDisplay(int i, SurfaceControl surfaceControl) throws RemoteException {
            return false;
        }

        @Override // android.view.IWindowManager
        public void modifyDisplayWindowInsets(int i, InsetsState insetsState) throws RemoteException {
        }

        @Override // android.view.IWindowManager
        public IWindowSession openSession(IWindowSessionCallback iWindowSessionCallback) throws RemoteException {
            return null;
        }

        @Override // android.view.IWindowManager
        public void overridePendingAppTransitionMultiThumbFuture(IAppTransitionAnimationSpecsFuture iAppTransitionAnimationSpecsFuture, IRemoteCallback iRemoteCallback, boolean z, int i) throws RemoteException {
        }

        @Override // android.view.IWindowManager
        public void overridePendingAppTransitionRemote(RemoteAnimationAdapter remoteAnimationAdapter, int i) throws RemoteException {
        }

        @Override // android.view.IWindowManager
        public void prepareAppTransition(int i, boolean z) throws RemoteException {
        }

        @Override // android.view.IWindowManager
        public void reenableKeyguard(IBinder iBinder, int i) throws RemoteException {
        }

        @Override // android.view.IWindowManager
        public void refreshScreenCaptureDisabled(int i) throws RemoteException {
        }

        @Override // android.view.IWindowManager
        public void registerDisplayFoldListener(IDisplayFoldListener iDisplayFoldListener) throws RemoteException {
        }

        @Override // android.view.IWindowManager
        public void registerDisplayWindowListener(IDisplayWindowListener iDisplayWindowListener) throws RemoteException {
        }

        @Override // android.view.IWindowManager
        public void registerPinnedStackListener(int i, IPinnedStackListener iPinnedStackListener) throws RemoteException {
        }

        @Override // android.view.IWindowManager
        public void registerShortcutKey(long j, IShortcutService iShortcutService) throws RemoteException {
        }

        @Override // android.view.IWindowManager
        public void registerSystemGestureExclusionListener(ISystemGestureExclusionListener iSystemGestureExclusionListener, int i) throws RemoteException {
        }

        @Override // android.view.IWindowManager
        public boolean registerWallpaperVisibilityListener(IWallpaperVisibilityListener iWallpaperVisibilityListener, int i) throws RemoteException {
            return false;
        }

        @Override // android.view.IWindowManager
        public void removeRotationWatcher(IRotationWatcher iRotationWatcher) throws RemoteException {
        }

        @Override // android.view.IWindowManager
        public void removeWindowToken(IBinder iBinder, int i) throws RemoteException {
        }

        @Override // android.view.IWindowManager
        public void requestAppKeyboardShortcuts(IResultReceiver iResultReceiver, int i) throws RemoteException {
        }

        @Override // android.view.IWindowManager
        public boolean requestAssistScreenshot(IAssistDataReceiver iAssistDataReceiver) throws RemoteException {
            return false;
        }

        @Override // android.view.IWindowManager
        public void requestScrollCapture(int i, IBinder iBinder, int i2, IScrollCaptureController iScrollCaptureController) throws RemoteException {
        }

        @Override // android.view.IWindowManager
        public Bitmap screenshotWallpaper() throws RemoteException {
            return null;
        }

        @Override // android.view.IWindowManager
        public void setAnimationScale(int i, float f) throws RemoteException {
        }

        @Override // android.view.IWindowManager
        public void setAnimationScales(float[] fArr) throws RemoteException {
        }

        @Override // android.view.IWindowManager
        public void setDisplayWindowInsetsController(int i, IDisplayWindowInsetsController iDisplayWindowInsetsController) throws RemoteException {
        }

        @Override // android.view.IWindowManager
        public void setDisplayWindowRotationController(IDisplayWindowRotationController iDisplayWindowRotationController) throws RemoteException {
        }

        @Override // android.view.IWindowManager
        public void setDockedStackDividerTouchRegion(Rect rect) throws RemoteException {
        }

        @Override // android.view.IWindowManager
        public void setEventDispatching(boolean z) throws RemoteException {
        }

        @Override // android.view.IWindowManager
        public void setFixedToUserRotation(int i, int i2) throws RemoteException {
        }

        @Override // android.view.IWindowManager
        public void setForcedDisplayDensityForUser(int i, int i2, int i3) throws RemoteException {
        }

        @Override // android.view.IWindowManager
        public void setForcedDisplayScalingMode(int i, int i2) throws RemoteException {
        }

        @Override // android.view.IWindowManager
        public void setForcedDisplaySize(int i, int i2, int i3) throws RemoteException {
        }

        @Override // android.view.IWindowManager
        public void setForwardedInsets(int i, Insets insets) throws RemoteException {
        }

        @Override // android.view.IWindowManager
        public void setInTouchMode(boolean z) throws RemoteException {
        }

        @Override // android.view.IWindowManager
        public void setLayerTracing(boolean z) throws RemoteException {
        }

        @Override // android.view.IWindowManager
        public void setLayerTracingFlags(int i) throws RemoteException {
        }

        @Override // android.view.IWindowManager
        public void setNavBarVirtualKeyHapticFeedbackEnabled(boolean z) throws RemoteException {
        }

        @Override // android.view.IWindowManager
        public void setPipVisibility(boolean z) throws RemoteException {
        }

        @Override // android.view.IWindowManager
        public void setRecentsVisibility(boolean z) throws RemoteException {
        }

        @Override // android.view.IWindowManager
        public void setRemoveContentMode(int i, int i2) throws RemoteException {
        }

        @Override // android.view.IWindowManager
        public void setShellRootAccessibilityWindow(int i, int i2, IWindow iWindow) throws RemoteException {
        }

        @Override // android.view.IWindowManager
        public void setShouldShowIme(int i, boolean z) throws RemoteException {
        }

        @Override // android.view.IWindowManager
        public void setShouldShowSystemDecors(int i, boolean z) throws RemoteException {
        }

        @Override // android.view.IWindowManager
        public void setShouldShowWithInsecureKeyguard(int i, boolean z) throws RemoteException {
        }

        @Override // android.view.IWindowManager
        public void setStrictModeVisualIndicatorPreference(String str) throws RemoteException {
        }

        @Override // android.view.IWindowManager
        public void setSwitchingUser(boolean z) throws RemoteException {
        }

        @Override // android.view.IWindowManager
        public void setWindowingMode(int i, int i2) throws RemoteException {
        }

        @Override // android.view.IWindowManager
        public boolean shouldShowIme(int i) throws RemoteException {
            return false;
        }

        @Override // android.view.IWindowManager
        public boolean shouldShowSystemDecors(int i) throws RemoteException {
            return false;
        }

        @Override // android.view.IWindowManager
        public boolean shouldShowWithInsecureKeyguard(int i) throws RemoteException {
            return false;
        }

        @Override // android.view.IWindowManager
        public void showGlobalActions() throws RemoteException {
        }

        @Override // android.view.IWindowManager
        public void showStrictModeViolation(boolean z) throws RemoteException {
        }

        @Override // android.view.IWindowManager
        public void startFreezingScreen(int i, int i2) throws RemoteException {
        }

        @Override // android.view.IWindowManager
        public boolean startViewServer(int i) throws RemoteException {
            return false;
        }

        @Override // android.view.IWindowManager
        public void startWindowTrace() throws RemoteException {
        }

        @Override // android.view.IWindowManager
        public void statusBarVisibilityChanged(int i, int i2) throws RemoteException {
        }

        @Override // android.view.IWindowManager
        public void stopFreezingScreen() throws RemoteException {
        }

        @Override // android.view.IWindowManager
        public boolean stopViewServer() throws RemoteException {
            return false;
        }

        @Override // android.view.IWindowManager
        public void stopWindowTrace() throws RemoteException {
        }

        @Override // android.view.IWindowManager
        public void syncInputTransactions() throws RemoteException {
        }

        @Override // android.view.IWindowManager
        public void thawDisplayRotation(int i) throws RemoteException {
        }

        @Override // android.view.IWindowManager
        public void thawRotation() throws RemoteException {
        }

        @Override // android.view.IWindowManager
        public void unregisterDisplayFoldListener(IDisplayFoldListener iDisplayFoldListener) throws RemoteException {
        }

        @Override // android.view.IWindowManager
        public void unregisterDisplayWindowListener(IDisplayWindowListener iDisplayWindowListener) throws RemoteException {
        }

        @Override // android.view.IWindowManager
        public void unregisterSystemGestureExclusionListener(ISystemGestureExclusionListener iSystemGestureExclusionListener, int i) throws RemoteException {
        }

        @Override // android.view.IWindowManager
        public void unregisterWallpaperVisibilityListener(IWallpaperVisibilityListener iWallpaperVisibilityListener, int i) throws RemoteException {
        }

        @Override // android.view.IWindowManager
        public void updateRotation(boolean z, boolean z2) throws RemoteException {
        }

        @Override // android.view.IWindowManager
        public boolean useBLAST() throws RemoteException {
            return false;
        }

        @Override // android.view.IWindowManager
        public int watchRotation(IRotationWatcher iRotationWatcher, int i) throws RemoteException {
            return 0;
        }
    }

    SurfaceControl addShellRoot(int i, IWindow iWindow, int i2) throws RemoteException;

    void addWindowToken(IBinder iBinder, int i, int i2) throws RemoteException;

    int addWindowTokenWithOptions(IBinder iBinder, int i, int i2, Bundle bundle, String str) throws RemoteException;

    void clearForcedDisplayDensityForUser(int i, int i2) throws RemoteException;

    void clearForcedDisplaySize(int i) throws RemoteException;

    boolean clearWindowContentFrameStats(IBinder iBinder) throws RemoteException;

    void closeSystemDialogs(String str) throws RemoteException;

    void createInputConsumer(IBinder iBinder, String str, int i, InputChannel inputChannel) throws RemoteException;

    boolean destroyInputConsumer(String str, int i) throws RemoteException;

    void disableKeyguard(IBinder iBinder, String str, int i) throws RemoteException;

    void dismissKeyguard(IKeyguardDismissCallback iKeyguardDismissCallback, CharSequence charSequence) throws RemoteException;

    void dontOverrideDisplayInfo(int i) throws RemoteException;

    void enableScreenIfNeeded() throws RemoteException;

    void endProlongedAnimations() throws RemoteException;

    void executeAppTransition() throws RemoteException;

    void exitKeyguardSecurely(IOnKeyguardExitResult iOnKeyguardExitResult) throws RemoteException;

    void freezeDisplayRotation(int i, int i2) throws RemoteException;

    void freezeRotation(int i) throws RemoteException;

    float getAnimationScale(int i) throws RemoteException;

    float[] getAnimationScales() throws RemoteException;

    int getBaseDisplayDensity(int i) throws RemoteException;

    void getBaseDisplaySize(int i, Point point) throws RemoteException;

    float getCurrentAnimatorScale() throws RemoteException;

    Region getCurrentImeTouchRegion() throws RemoteException;

    int getDefaultDisplayRotation() throws RemoteException;

    int getDockedStackSide() throws RemoteException;

    int getInitialDisplayDensity(int i) throws RemoteException;

    void getInitialDisplaySize(int i, Point point) throws RemoteException;

    int getNavBarPosition(int i) throws RemoteException;

    int getPreferredOptionsPanelGravity(int i) throws RemoteException;

    int getRemoveContentMode(int i) throws RemoteException;

    void getStableInsets(int i, Rect rect) throws RemoteException;

    WindowContentFrameStats getWindowContentFrameStats(IBinder iBinder) throws RemoteException;

    boolean getWindowInsets(WindowManager.LayoutParams layoutParams, int i, Rect rect, Rect rect2, DisplayCutout.ParcelableWrapper parcelableWrapper, InsetsState insetsState) throws RemoteException;

    int getWindowingMode(int i) throws RemoteException;

    boolean hasNavigationBar(int i) throws RemoteException;

    void hideTransientBars(int i) throws RemoteException;

    boolean injectInputAfterTransactionsApplied(InputEvent inputEvent, int i) throws RemoteException;

    boolean isDisplayRotationFrozen(int i) throws RemoteException;

    boolean isKeyguardLocked() throws RemoteException;

    boolean isKeyguardSecure(int i) throws RemoteException;

    boolean isLayerTracing() throws RemoteException;

    boolean isRotationFrozen() throws RemoteException;

    boolean isSafeModeEnabled() throws RemoteException;

    boolean isViewServerRunning() throws RemoteException;

    boolean isWindowToken(IBinder iBinder) throws RemoteException;

    boolean isWindowTraceEnabled() throws RemoteException;

    void lockNow(Bundle bundle) throws RemoteException;

    boolean mirrorDisplay(int i, SurfaceControl surfaceControl) throws RemoteException;

    void modifyDisplayWindowInsets(int i, InsetsState insetsState) throws RemoteException;

    IWindowSession openSession(IWindowSessionCallback iWindowSessionCallback) throws RemoteException;

    void overridePendingAppTransitionMultiThumbFuture(IAppTransitionAnimationSpecsFuture iAppTransitionAnimationSpecsFuture, IRemoteCallback iRemoteCallback, boolean z, int i) throws RemoteException;

    void overridePendingAppTransitionRemote(RemoteAnimationAdapter remoteAnimationAdapter, int i) throws RemoteException;

    void prepareAppTransition(int i, boolean z) throws RemoteException;

    void reenableKeyguard(IBinder iBinder, int i) throws RemoteException;

    void refreshScreenCaptureDisabled(int i) throws RemoteException;

    void registerDisplayFoldListener(IDisplayFoldListener iDisplayFoldListener) throws RemoteException;

    void registerDisplayWindowListener(IDisplayWindowListener iDisplayWindowListener) throws RemoteException;

    void registerPinnedStackListener(int i, IPinnedStackListener iPinnedStackListener) throws RemoteException;

    void registerShortcutKey(long j, IShortcutService iShortcutService) throws RemoteException;

    void registerSystemGestureExclusionListener(ISystemGestureExclusionListener iSystemGestureExclusionListener, int i) throws RemoteException;

    boolean registerWallpaperVisibilityListener(IWallpaperVisibilityListener iWallpaperVisibilityListener, int i) throws RemoteException;

    void removeRotationWatcher(IRotationWatcher iRotationWatcher) throws RemoteException;

    void removeWindowToken(IBinder iBinder, int i) throws RemoteException;

    void requestAppKeyboardShortcuts(IResultReceiver iResultReceiver, int i) throws RemoteException;

    boolean requestAssistScreenshot(IAssistDataReceiver iAssistDataReceiver) throws RemoteException;

    void requestScrollCapture(int i, IBinder iBinder, int i2, IScrollCaptureController iScrollCaptureController) throws RemoteException;

    Bitmap screenshotWallpaper() throws RemoteException;

    void setAnimationScale(int i, float f) throws RemoteException;

    void setAnimationScales(float[] fArr) throws RemoteException;

    void setDisplayWindowInsetsController(int i, IDisplayWindowInsetsController iDisplayWindowInsetsController) throws RemoteException;

    void setDisplayWindowRotationController(IDisplayWindowRotationController iDisplayWindowRotationController) throws RemoteException;

    void setDockedStackDividerTouchRegion(Rect rect) throws RemoteException;

    void setEventDispatching(boolean z) throws RemoteException;

    void setFixedToUserRotation(int i, int i2) throws RemoteException;

    void setForcedDisplayDensityForUser(int i, int i2, int i3) throws RemoteException;

    void setForcedDisplayScalingMode(int i, int i2) throws RemoteException;

    void setForcedDisplaySize(int i, int i2, int i3) throws RemoteException;

    void setForwardedInsets(int i, Insets insets) throws RemoteException;

    void setInTouchMode(boolean z) throws RemoteException;

    void setLayerTracing(boolean z) throws RemoteException;

    void setLayerTracingFlags(int i) throws RemoteException;

    void setNavBarVirtualKeyHapticFeedbackEnabled(boolean z) throws RemoteException;

    void setPipVisibility(boolean z) throws RemoteException;

    void setRecentsVisibility(boolean z) throws RemoteException;

    void setRemoveContentMode(int i, int i2) throws RemoteException;

    void setShellRootAccessibilityWindow(int i, int i2, IWindow iWindow) throws RemoteException;

    void setShouldShowIme(int i, boolean z) throws RemoteException;

    void setShouldShowSystemDecors(int i, boolean z) throws RemoteException;

    void setShouldShowWithInsecureKeyguard(int i, boolean z) throws RemoteException;

    void setStrictModeVisualIndicatorPreference(String str) throws RemoteException;

    void setSwitchingUser(boolean z) throws RemoteException;

    void setWindowingMode(int i, int i2) throws RemoteException;

    boolean shouldShowIme(int i) throws RemoteException;

    boolean shouldShowSystemDecors(int i) throws RemoteException;

    boolean shouldShowWithInsecureKeyguard(int i) throws RemoteException;

    void showGlobalActions() throws RemoteException;

    void showStrictModeViolation(boolean z) throws RemoteException;

    void startFreezingScreen(int i, int i2) throws RemoteException;

    boolean startViewServer(int i) throws RemoteException;

    void startWindowTrace() throws RemoteException;

    void statusBarVisibilityChanged(int i, int i2) throws RemoteException;

    void stopFreezingScreen() throws RemoteException;

    boolean stopViewServer() throws RemoteException;

    void stopWindowTrace() throws RemoteException;

    void syncInputTransactions() throws RemoteException;

    void thawDisplayRotation(int i) throws RemoteException;

    void thawRotation() throws RemoteException;

    void unregisterDisplayFoldListener(IDisplayFoldListener iDisplayFoldListener) throws RemoteException;

    void unregisterDisplayWindowListener(IDisplayWindowListener iDisplayWindowListener) throws RemoteException;

    void unregisterSystemGestureExclusionListener(ISystemGestureExclusionListener iSystemGestureExclusionListener, int i) throws RemoteException;

    void unregisterWallpaperVisibilityListener(IWallpaperVisibilityListener iWallpaperVisibilityListener, int i) throws RemoteException;

    void updateRotation(boolean z, boolean z2) throws RemoteException;

    boolean useBLAST() throws RemoteException;

    int watchRotation(IRotationWatcher iRotationWatcher, int i) throws RemoteException;

    public static abstract class Stub extends Binder implements IWindowManager {
        private static final String DESCRIPTOR = "android.view.IWindowManager";
        static final int TRANSACTION_addShellRoot = 22;
        static final int TRANSACTION_addWindowToken = 18;
        static final int TRANSACTION_addWindowTokenWithOptions = 17;
        static final int TRANSACTION_clearForcedDisplayDensityForUser = 13;
        static final int TRANSACTION_clearForcedDisplaySize = 9;
        static final int TRANSACTION_clearWindowContentFrameStats = 75;
        static final int TRANSACTION_closeSystemDialogs = 37;
        static final int TRANSACTION_createInputConsumer = 84;
        static final int TRANSACTION_destroyInputConsumer = 85;
        static final int TRANSACTION_disableKeyguard = 30;
        static final int TRANSACTION_dismissKeyguard = 35;
        static final int TRANSACTION_dontOverrideDisplayInfo = 94;
        static final int TRANSACTION_enableScreenIfNeeded = 74;
        static final int TRANSACTION_endProlongedAnimations = 27;
        static final int TRANSACTION_executeAppTransition = 26;
        static final int TRANSACTION_exitKeyguardSecurely = 32;
        static final int TRANSACTION_freezeDisplayRotation = 55;
        static final int TRANSACTION_freezeRotation = 52;
        static final int TRANSACTION_getAnimationScale = 38;
        static final int TRANSACTION_getAnimationScales = 39;
        static final int TRANSACTION_getBaseDisplayDensity = 11;
        static final int TRANSACTION_getBaseDisplaySize = 7;
        static final int TRANSACTION_getCurrentAnimatorScale = 42;
        static final int TRANSACTION_getCurrentImeTouchRegion = 86;
        static final int TRANSACTION_getDefaultDisplayRotation = 48;
        static final int TRANSACTION_getDockedStackSide = 77;
        static final int TRANSACTION_getInitialDisplayDensity = 10;
        static final int TRANSACTION_getInitialDisplaySize = 6;
        static final int TRANSACTION_getNavBarPosition = 71;
        static final int TRANSACTION_getPreferredOptionsPanelGravity = 51;
        static final int TRANSACTION_getRemoveContentMode = 97;
        static final int TRANSACTION_getStableInsets = 81;
        static final int TRANSACTION_getWindowContentFrameStats = 76;
        static final int TRANSACTION_getWindowInsets = 112;
        static final int TRANSACTION_getWindowingMode = 95;
        static final int TRANSACTION_hasNavigationBar = 70;
        static final int TRANSACTION_hideTransientBars = 66;
        static final int TRANSACTION_injectInputAfterTransactionsApplied = 105;
        static final int TRANSACTION_isDisplayRotationFrozen = 57;
        static final int TRANSACTION_isKeyguardLocked = 33;
        static final int TRANSACTION_isKeyguardSecure = 34;
        static final int TRANSACTION_isLayerTracing = 107;
        static final int TRANSACTION_isRotationFrozen = 54;
        static final int TRANSACTION_isSafeModeEnabled = 73;
        static final int TRANSACTION_isViewServerRunning = 3;
        static final int TRANSACTION_isWindowToken = 16;
        static final int TRANSACTION_isWindowTraceEnabled = 93;
        static final int TRANSACTION_lockNow = 72;
        static final int TRANSACTION_mirrorDisplay = 109;
        static final int TRANSACTION_modifyDisplayWindowInsets = 111;
        static final int TRANSACTION_openSession = 4;
        static final int TRANSACTION_overridePendingAppTransitionMultiThumbFuture = 24;
        static final int TRANSACTION_overridePendingAppTransitionRemote = 25;
        static final int TRANSACTION_prepareAppTransition = 20;
        static final int TRANSACTION_reenableKeyguard = 31;
        static final int TRANSACTION_refreshScreenCaptureDisabled = 46;
        static final int TRANSACTION_registerDisplayFoldListener = 87;
        static final int TRANSACTION_registerDisplayWindowListener = 89;
        static final int TRANSACTION_registerPinnedStackListener = 79;
        static final int TRANSACTION_registerShortcutKey = 83;
        static final int TRANSACTION_registerSystemGestureExclusionListener = 62;
        static final int TRANSACTION_registerWallpaperVisibilityListener = 60;
        static final int TRANSACTION_removeRotationWatcher = 50;
        static final int TRANSACTION_removeWindowToken = 19;
        static final int TRANSACTION_requestAppKeyboardShortcuts = 80;
        static final int TRANSACTION_requestAssistScreenshot = 64;
        static final int TRANSACTION_requestScrollCapture = 115;
        static final int TRANSACTION_screenshotWallpaper = 59;
        static final int TRANSACTION_setAnimationScale = 40;
        static final int TRANSACTION_setAnimationScales = 41;
        static final int TRANSACTION_setDisplayWindowInsetsController = 110;
        static final int TRANSACTION_setDisplayWindowRotationController = 21;
        static final int TRANSACTION_setDockedStackDividerTouchRegion = 78;
        static final int TRANSACTION_setEventDispatching = 15;
        static final int TRANSACTION_setFixedToUserRotation = 58;
        static final int TRANSACTION_setForcedDisplayDensityForUser = 12;
        static final int TRANSACTION_setForcedDisplayScalingMode = 14;
        static final int TRANSACTION_setForcedDisplaySize = 8;
        static final int TRANSACTION_setForwardedInsets = 82;
        static final int TRANSACTION_setInTouchMode = 43;
        static final int TRANSACTION_setLayerTracing = 108;
        static final int TRANSACTION_setLayerTracingFlags = 114;
        static final int TRANSACTION_setNavBarVirtualKeyHapticFeedbackEnabled = 69;
        static final int TRANSACTION_setPipVisibility = 68;
        static final int TRANSACTION_setRecentsVisibility = 67;
        static final int TRANSACTION_setRemoveContentMode = 98;
        static final int TRANSACTION_setShellRootAccessibilityWindow = 23;
        static final int TRANSACTION_setShouldShowIme = 104;
        static final int TRANSACTION_setShouldShowSystemDecors = 102;
        static final int TRANSACTION_setShouldShowWithInsecureKeyguard = 100;
        static final int TRANSACTION_setStrictModeVisualIndicatorPreference = 45;
        static final int TRANSACTION_setSwitchingUser = 36;
        static final int TRANSACTION_setWindowingMode = 96;
        static final int TRANSACTION_shouldShowIme = 103;
        static final int TRANSACTION_shouldShowSystemDecors = 101;
        static final int TRANSACTION_shouldShowWithInsecureKeyguard = 99;
        static final int TRANSACTION_showGlobalActions = 113;
        static final int TRANSACTION_showStrictModeViolation = 44;
        static final int TRANSACTION_startFreezingScreen = 28;
        static final int TRANSACTION_startViewServer = 1;
        static final int TRANSACTION_startWindowTrace = 91;
        static final int TRANSACTION_statusBarVisibilityChanged = 65;
        static final int TRANSACTION_stopFreezingScreen = 29;
        static final int TRANSACTION_stopViewServer = 2;
        static final int TRANSACTION_stopWindowTrace = 92;
        static final int TRANSACTION_syncInputTransactions = 106;
        static final int TRANSACTION_thawDisplayRotation = 56;
        static final int TRANSACTION_thawRotation = 53;
        static final int TRANSACTION_unregisterDisplayFoldListener = 88;
        static final int TRANSACTION_unregisterDisplayWindowListener = 90;
        static final int TRANSACTION_unregisterSystemGestureExclusionListener = 63;
        static final int TRANSACTION_unregisterWallpaperVisibilityListener = 61;
        static final int TRANSACTION_updateRotation = 47;
        static final int TRANSACTION_useBLAST = 5;
        static final int TRANSACTION_watchRotation = 49;

        public static String getDefaultTransactionName(int i) {
            switch (i) {
                case 1:
                    return "startViewServer";
                case 2:
                    return "stopViewServer";
                case 3:
                    return "isViewServerRunning";
                case 4:
                    return "openSession";
                case 5:
                    return "useBLAST";
                case 6:
                    return "getInitialDisplaySize";
                case 7:
                    return "getBaseDisplaySize";
                case 8:
                    return "setForcedDisplaySize";
                case 9:
                    return "clearForcedDisplaySize";
                case 10:
                    return "getInitialDisplayDensity";
                case 11:
                    return "getBaseDisplayDensity";
                case 12:
                    return "setForcedDisplayDensityForUser";
                case 13:
                    return "clearForcedDisplayDensityForUser";
                case 14:
                    return "setForcedDisplayScalingMode";
                case 15:
                    return "setEventDispatching";
                case 16:
                    return "isWindowToken";
                case 17:
                    return "addWindowTokenWithOptions";
                case 18:
                    return "addWindowToken";
                case 19:
                    return "removeWindowToken";
                case 20:
                    return "prepareAppTransition";
                case 21:
                    return "setDisplayWindowRotationController";
                case 22:
                    return "addShellRoot";
                case 23:
                    return "setShellRootAccessibilityWindow";
                case 24:
                    return "overridePendingAppTransitionMultiThumbFuture";
                case 25:
                    return "overridePendingAppTransitionRemote";
                case 26:
                    return "executeAppTransition";
                case 27:
                    return "endProlongedAnimations";
                case 28:
                    return "startFreezingScreen";
                case 29:
                    return "stopFreezingScreen";
                case 30:
                    return "disableKeyguard";
                case 31:
                    return "reenableKeyguard";
                case 32:
                    return "exitKeyguardSecurely";
                case 33:
                    return "isKeyguardLocked";
                case 34:
                    return "isKeyguardSecure";
                case 35:
                    return "dismissKeyguard";
                case 36:
                    return "setSwitchingUser";
                case 37:
                    return "closeSystemDialogs";
                case 38:
                    return "getAnimationScale";
                case 39:
                    return "getAnimationScales";
                case 40:
                    return "setAnimationScale";
                case 41:
                    return "setAnimationScales";
                case 42:
                    return "getCurrentAnimatorScale";
                case 43:
                    return "setInTouchMode";
                case 44:
                    return "showStrictModeViolation";
                case 45:
                    return "setStrictModeVisualIndicatorPreference";
                case 46:
                    return "refreshScreenCaptureDisabled";
                case 47:
                    return "updateRotation";
                case 48:
                    return "getDefaultDisplayRotation";
                case 49:
                    return "watchRotation";
                case 50:
                    return "removeRotationWatcher";
                case 51:
                    return "getPreferredOptionsPanelGravity";
                case 52:
                    return "freezeRotation";
                case 53:
                    return "thawRotation";
                case 54:
                    return "isRotationFrozen";
                case 55:
                    return "freezeDisplayRotation";
                case 56:
                    return "thawDisplayRotation";
                case 57:
                    return "isDisplayRotationFrozen";
                case 58:
                    return "setFixedToUserRotation";
                case 59:
                    return "screenshotWallpaper";
                case 60:
                    return "registerWallpaperVisibilityListener";
                case 61:
                    return "unregisterWallpaperVisibilityListener";
                case 62:
                    return "registerSystemGestureExclusionListener";
                case 63:
                    return "unregisterSystemGestureExclusionListener";
                case 64:
                    return "requestAssistScreenshot";
                case 65:
                    return "statusBarVisibilityChanged";
                case 66:
                    return "hideTransientBars";
                case 67:
                    return "setRecentsVisibility";
                case 68:
                    return "setPipVisibility";
                case 69:
                    return "setNavBarVirtualKeyHapticFeedbackEnabled";
                case 70:
                    return "hasNavigationBar";
                case 71:
                    return "getNavBarPosition";
                case 72:
                    return "lockNow";
                case 73:
                    return "isSafeModeEnabled";
                case 74:
                    return "enableScreenIfNeeded";
                case 75:
                    return "clearWindowContentFrameStats";
                case 76:
                    return "getWindowContentFrameStats";
                case 77:
                    return "getDockedStackSide";
                case 78:
                    return "setDockedStackDividerTouchRegion";
                case 79:
                    return "registerPinnedStackListener";
                case 80:
                    return "requestAppKeyboardShortcuts";
                case 81:
                    return "getStableInsets";
                case 82:
                    return "setForwardedInsets";
                case 83:
                    return "registerShortcutKey";
                case 84:
                    return "createInputConsumer";
                case 85:
                    return "destroyInputConsumer";
                case 86:
                    return "getCurrentImeTouchRegion";
                case 87:
                    return "registerDisplayFoldListener";
                case 88:
                    return "unregisterDisplayFoldListener";
                case 89:
                    return "registerDisplayWindowListener";
                case 90:
                    return "unregisterDisplayWindowListener";
                case 91:
                    return "startWindowTrace";
                case 92:
                    return "stopWindowTrace";
                case 93:
                    return "isWindowTraceEnabled";
                case 94:
                    return "dontOverrideDisplayInfo";
                case 95:
                    return "getWindowingMode";
                case 96:
                    return "setWindowingMode";
                case 97:
                    return "getRemoveContentMode";
                case 98:
                    return "setRemoveContentMode";
                case 99:
                    return "shouldShowWithInsecureKeyguard";
                case 100:
                    return "setShouldShowWithInsecureKeyguard";
                case 101:
                    return "shouldShowSystemDecors";
                case 102:
                    return "setShouldShowSystemDecors";
                case 103:
                    return "shouldShowIme";
                case 104:
                    return "setShouldShowIme";
                case 105:
                    return "injectInputAfterTransactionsApplied";
                case 106:
                    return "syncInputTransactions";
                case 107:
                    return "isLayerTracing";
                case 108:
                    return "setLayerTracing";
                case 109:
                    return "mirrorDisplay";
                case 110:
                    return "setDisplayWindowInsetsController";
                case 111:
                    return "modifyDisplayWindowInsets";
                case 112:
                    return "getWindowInsets";
                case 113:
                    return "showGlobalActions";
                case 114:
                    return "setLayerTracingFlags";
                case 115:
                    return "requestScrollCapture";
                default:
                    return null;
            }
        }

        @Override // android.os.IInterface
        public IBinder asBinder() {
            return this;
        }

        public Stub() {
            attachInterface(this, DESCRIPTOR);
        }

        public static IWindowManager asInterface(IBinder iBinder) {
            if (iBinder == null) {
                return null;
            }
            IInterface iInterfaceQueryLocalInterface = iBinder.queryLocalInterface(DESCRIPTOR);
            if (iInterfaceQueryLocalInterface != null && (iInterfaceQueryLocalInterface instanceof IWindowManager)) {
                return (IWindowManager) iInterfaceQueryLocalInterface;
            }
            return new Proxy(iBinder);
        }

        @Override // android.os.Binder
        public String getTransactionName(int i) {
            return getDefaultTransactionName(i);
        }

        @Override // android.os.Binder
        public boolean onTransact(int i, Parcel parcel, Parcel parcel2, int i2) throws RemoteException {
            if (i == 1598968902) {
                parcel2.writeString(DESCRIPTOR);
                return true;
            }
            switch (i) {
                case 1:
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean zStartViewServer = startViewServer(parcel.readInt());
                    parcel2.writeNoException();
                    parcel2.writeInt(zStartViewServer ? 1 : 0);
                    return true;
                case 2:
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean zStopViewServer = stopViewServer();
                    parcel2.writeNoException();
                    parcel2.writeInt(zStopViewServer ? 1 : 0);
                    return true;
                case 3:
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean zIsViewServerRunning = isViewServerRunning();
                    parcel2.writeNoException();
                    parcel2.writeInt(zIsViewServerRunning ? 1 : 0);
                    return true;
                case 4:
                    parcel.enforceInterface(DESCRIPTOR);
                    IWindowSession iWindowSessionOpenSession = openSession(IWindowSessionCallback.Stub.asInterface(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    parcel2.writeStrongBinder(iWindowSessionOpenSession != null ? iWindowSessionOpenSession.asBinder() : null);
                    return true;
                case 5:
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean zUseBLAST = useBLAST();
                    parcel2.writeNoException();
                    parcel2.writeInt(zUseBLAST ? 1 : 0);
                    return true;
                case 6:
                    parcel.enforceInterface(DESCRIPTOR);
                    int i3 = parcel.readInt();
                    Point point = new Point();
                    getInitialDisplaySize(i3, point);
                    parcel2.writeNoException();
                    parcel2.writeInt(1);
                    point.writeToParcel(parcel2, 1);
                    return true;
                case 7:
                    parcel.enforceInterface(DESCRIPTOR);
                    int i4 = parcel.readInt();
                    Point point2 = new Point();
                    getBaseDisplaySize(i4, point2);
                    parcel2.writeNoException();
                    parcel2.writeInt(1);
                    point2.writeToParcel(parcel2, 1);
                    return true;
                case 8:
                    parcel.enforceInterface(DESCRIPTOR);
                    setForcedDisplaySize(parcel.readInt(), parcel.readInt(), parcel.readInt());
                    parcel2.writeNoException();
                    return true;
                case 9:
                    parcel.enforceInterface(DESCRIPTOR);
                    clearForcedDisplaySize(parcel.readInt());
                    parcel2.writeNoException();
                    return true;
                case 10:
                    parcel.enforceInterface(DESCRIPTOR);
                    int initialDisplayDensity = getInitialDisplayDensity(parcel.readInt());
                    parcel2.writeNoException();
                    parcel2.writeInt(initialDisplayDensity);
                    return true;
                case 11:
                    parcel.enforceInterface(DESCRIPTOR);
                    int baseDisplayDensity = getBaseDisplayDensity(parcel.readInt());
                    parcel2.writeNoException();
                    parcel2.writeInt(baseDisplayDensity);
                    return true;
                case 12:
                    parcel.enforceInterface(DESCRIPTOR);
                    setForcedDisplayDensityForUser(parcel.readInt(), parcel.readInt(), parcel.readInt());
                    parcel2.writeNoException();
                    return true;
                case 13:
                    parcel.enforceInterface(DESCRIPTOR);
                    clearForcedDisplayDensityForUser(parcel.readInt(), parcel.readInt());
                    parcel2.writeNoException();
                    return true;
                case 14:
                    parcel.enforceInterface(DESCRIPTOR);
                    setForcedDisplayScalingMode(parcel.readInt(), parcel.readInt());
                    parcel2.writeNoException();
                    return true;
                case 15:
                    parcel.enforceInterface(DESCRIPTOR);
                    setEventDispatching(parcel.readInt() != 0);
                    parcel2.writeNoException();
                    return true;
                case 16:
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean zIsWindowToken = isWindowToken(parcel.readStrongBinder());
                    parcel2.writeNoException();
                    parcel2.writeInt(zIsWindowToken ? 1 : 0);
                    return true;
                case 17:
                    parcel.enforceInterface(DESCRIPTOR);
                    int iAddWindowTokenWithOptions = addWindowTokenWithOptions(parcel.readStrongBinder(), parcel.readInt(), parcel.readInt(), parcel.readInt() != 0 ? Bundle.CREATOR.createFromParcel(parcel) : null, parcel.readString());
                    parcel2.writeNoException();
                    parcel2.writeInt(iAddWindowTokenWithOptions);
                    return true;
                case 18:
                    parcel.enforceInterface(DESCRIPTOR);
                    addWindowToken(parcel.readStrongBinder(), parcel.readInt(), parcel.readInt());
                    parcel2.writeNoException();
                    return true;
                case 19:
                    parcel.enforceInterface(DESCRIPTOR);
                    removeWindowToken(parcel.readStrongBinder(), parcel.readInt());
                    parcel2.writeNoException();
                    return true;
                case 20:
                    parcel.enforceInterface(DESCRIPTOR);
                    prepareAppTransition(parcel.readInt(), parcel.readInt() != 0);
                    parcel2.writeNoException();
                    return true;
                case 21:
                    parcel.enforceInterface(DESCRIPTOR);
                    setDisplayWindowRotationController(IDisplayWindowRotationController.Stub.asInterface(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    return true;
                case 22:
                    parcel.enforceInterface(DESCRIPTOR);
                    SurfaceControl surfaceControlAddShellRoot = addShellRoot(parcel.readInt(), IWindow.Stub.asInterface(parcel.readStrongBinder()), parcel.readInt());
                    parcel2.writeNoException();
                    if (surfaceControlAddShellRoot != null) {
                        parcel2.writeInt(1);
                        surfaceControlAddShellRoot.writeToParcel(parcel2, 1);
                    } else {
                        parcel2.writeInt(0);
                    }
                    return true;
                case 23:
                    parcel.enforceInterface(DESCRIPTOR);
                    setShellRootAccessibilityWindow(parcel.readInt(), parcel.readInt(), IWindow.Stub.asInterface(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    return true;
                case 24:
                    parcel.enforceInterface(DESCRIPTOR);
                    overridePendingAppTransitionMultiThumbFuture(IAppTransitionAnimationSpecsFuture.Stub.asInterface(parcel.readStrongBinder()), IRemoteCallback.Stub.asInterface(parcel.readStrongBinder()), parcel.readInt() != 0, parcel.readInt());
                    parcel2.writeNoException();
                    return true;
                case 25:
                    parcel.enforceInterface(DESCRIPTOR);
                    overridePendingAppTransitionRemote(parcel.readInt() != 0 ? RemoteAnimationAdapter.CREATOR.createFromParcel(parcel) : null, parcel.readInt());
                    parcel2.writeNoException();
                    return true;
                case 26:
                    parcel.enforceInterface(DESCRIPTOR);
                    executeAppTransition();
                    parcel2.writeNoException();
                    return true;
                case 27:
                    parcel.enforceInterface(DESCRIPTOR);
                    endProlongedAnimations();
                    parcel2.writeNoException();
                    return true;
                case 28:
                    parcel.enforceInterface(DESCRIPTOR);
                    startFreezingScreen(parcel.readInt(), parcel.readInt());
                    parcel2.writeNoException();
                    return true;
                case 29:
                    parcel.enforceInterface(DESCRIPTOR);
                    stopFreezingScreen();
                    parcel2.writeNoException();
                    return true;
                case 30:
                    parcel.enforceInterface(DESCRIPTOR);
                    disableKeyguard(parcel.readStrongBinder(), parcel.readString(), parcel.readInt());
                    parcel2.writeNoException();
                    return true;
                case 31:
                    parcel.enforceInterface(DESCRIPTOR);
                    reenableKeyguard(parcel.readStrongBinder(), parcel.readInt());
                    parcel2.writeNoException();
                    return true;
                case 32:
                    parcel.enforceInterface(DESCRIPTOR);
                    exitKeyguardSecurely(IOnKeyguardExitResult.Stub.asInterface(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    return true;
                case 33:
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean zIsKeyguardLocked = isKeyguardLocked();
                    parcel2.writeNoException();
                    parcel2.writeInt(zIsKeyguardLocked ? 1 : 0);
                    return true;
                case 34:
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean zIsKeyguardSecure = isKeyguardSecure(parcel.readInt());
                    parcel2.writeNoException();
                    parcel2.writeInt(zIsKeyguardSecure ? 1 : 0);
                    return true;
                case 35:
                    parcel.enforceInterface(DESCRIPTOR);
                    dismissKeyguard(IKeyguardDismissCallback.Stub.asInterface(parcel.readStrongBinder()), parcel.readInt() != 0 ? TextUtils.CHAR_SEQUENCE_CREATOR.createFromParcel(parcel) : null);
                    parcel2.writeNoException();
                    return true;
                case 36:
                    parcel.enforceInterface(DESCRIPTOR);
                    setSwitchingUser(parcel.readInt() != 0);
                    parcel2.writeNoException();
                    return true;
                case 37:
                    parcel.enforceInterface(DESCRIPTOR);
                    closeSystemDialogs(parcel.readString());
                    parcel2.writeNoException();
                    return true;
                case 38:
                    parcel.enforceInterface(DESCRIPTOR);
                    float animationScale = getAnimationScale(parcel.readInt());
                    parcel2.writeNoException();
                    parcel2.writeFloat(animationScale);
                    return true;
                case 39:
                    parcel.enforceInterface(DESCRIPTOR);
                    float[] animationScales = getAnimationScales();
                    parcel2.writeNoException();
                    parcel2.writeFloatArray(animationScales);
                    return true;
                case 40:
                    parcel.enforceInterface(DESCRIPTOR);
                    setAnimationScale(parcel.readInt(), parcel.readFloat());
                    parcel2.writeNoException();
                    return true;
                case 41:
                    parcel.enforceInterface(DESCRIPTOR);
                    setAnimationScales(parcel.createFloatArray());
                    parcel2.writeNoException();
                    return true;
                case 42:
                    parcel.enforceInterface(DESCRIPTOR);
                    float currentAnimatorScale = getCurrentAnimatorScale();
                    parcel2.writeNoException();
                    parcel2.writeFloat(currentAnimatorScale);
                    return true;
                case 43:
                    parcel.enforceInterface(DESCRIPTOR);
                    setInTouchMode(parcel.readInt() != 0);
                    parcel2.writeNoException();
                    return true;
                case 44:
                    parcel.enforceInterface(DESCRIPTOR);
                    showStrictModeViolation(parcel.readInt() != 0);
                    parcel2.writeNoException();
                    return true;
                case 45:
                    parcel.enforceInterface(DESCRIPTOR);
                    setStrictModeVisualIndicatorPreference(parcel.readString());
                    parcel2.writeNoException();
                    return true;
                case 46:
                    parcel.enforceInterface(DESCRIPTOR);
                    refreshScreenCaptureDisabled(parcel.readInt());
                    parcel2.writeNoException();
                    return true;
                case 47:
                    parcel.enforceInterface(DESCRIPTOR);
                    updateRotation(parcel.readInt() != 0, parcel.readInt() != 0);
                    parcel2.writeNoException();
                    return true;
                case 48:
                    parcel.enforceInterface(DESCRIPTOR);
                    int defaultDisplayRotation = getDefaultDisplayRotation();
                    parcel2.writeNoException();
                    parcel2.writeInt(defaultDisplayRotation);
                    return true;
                case 49:
                    parcel.enforceInterface(DESCRIPTOR);
                    int iWatchRotation = watchRotation(IRotationWatcher.Stub.asInterface(parcel.readStrongBinder()), parcel.readInt());
                    parcel2.writeNoException();
                    parcel2.writeInt(iWatchRotation);
                    return true;
                case 50:
                    parcel.enforceInterface(DESCRIPTOR);
                    removeRotationWatcher(IRotationWatcher.Stub.asInterface(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    return true;
                case 51:
                    parcel.enforceInterface(DESCRIPTOR);
                    int preferredOptionsPanelGravity = getPreferredOptionsPanelGravity(parcel.readInt());
                    parcel2.writeNoException();
                    parcel2.writeInt(preferredOptionsPanelGravity);
                    return true;
                case 52:
                    parcel.enforceInterface(DESCRIPTOR);
                    freezeRotation(parcel.readInt());
                    parcel2.writeNoException();
                    return true;
                case 53:
                    parcel.enforceInterface(DESCRIPTOR);
                    thawRotation();
                    parcel2.writeNoException();
                    return true;
                case 54:
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean zIsRotationFrozen = isRotationFrozen();
                    parcel2.writeNoException();
                    parcel2.writeInt(zIsRotationFrozen ? 1 : 0);
                    return true;
                case 55:
                    parcel.enforceInterface(DESCRIPTOR);
                    freezeDisplayRotation(parcel.readInt(), parcel.readInt());
                    parcel2.writeNoException();
                    return true;
                case 56:
                    parcel.enforceInterface(DESCRIPTOR);
                    thawDisplayRotation(parcel.readInt());
                    parcel2.writeNoException();
                    return true;
                case 57:
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean zIsDisplayRotationFrozen = isDisplayRotationFrozen(parcel.readInt());
                    parcel2.writeNoException();
                    parcel2.writeInt(zIsDisplayRotationFrozen ? 1 : 0);
                    return true;
                case 58:
                    parcel.enforceInterface(DESCRIPTOR);
                    setFixedToUserRotation(parcel.readInt(), parcel.readInt());
                    parcel2.writeNoException();
                    return true;
                case 59:
                    parcel.enforceInterface(DESCRIPTOR);
                    Bitmap bitmapScreenshotWallpaper = screenshotWallpaper();
                    parcel2.writeNoException();
                    if (bitmapScreenshotWallpaper != null) {
                        parcel2.writeInt(1);
                        bitmapScreenshotWallpaper.writeToParcel(parcel2, 1);
                    } else {
                        parcel2.writeInt(0);
                    }
                    return true;
                case 60:
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean zRegisterWallpaperVisibilityListener = registerWallpaperVisibilityListener(IWallpaperVisibilityListener.Stub.asInterface(parcel.readStrongBinder()), parcel.readInt());
                    parcel2.writeNoException();
                    parcel2.writeInt(zRegisterWallpaperVisibilityListener ? 1 : 0);
                    return true;
                case 61:
                    parcel.enforceInterface(DESCRIPTOR);
                    unregisterWallpaperVisibilityListener(IWallpaperVisibilityListener.Stub.asInterface(parcel.readStrongBinder()), parcel.readInt());
                    parcel2.writeNoException();
                    return true;
                case 62:
                    parcel.enforceInterface(DESCRIPTOR);
                    registerSystemGestureExclusionListener(ISystemGestureExclusionListener.Stub.asInterface(parcel.readStrongBinder()), parcel.readInt());
                    parcel2.writeNoException();
                    return true;
                case 63:
                    parcel.enforceInterface(DESCRIPTOR);
                    unregisterSystemGestureExclusionListener(ISystemGestureExclusionListener.Stub.asInterface(parcel.readStrongBinder()), parcel.readInt());
                    parcel2.writeNoException();
                    return true;
                case 64:
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean zRequestAssistScreenshot = requestAssistScreenshot(IAssistDataReceiver.Stub.asInterface(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    parcel2.writeInt(zRequestAssistScreenshot ? 1 : 0);
                    return true;
                case 65:
                    parcel.enforceInterface(DESCRIPTOR);
                    statusBarVisibilityChanged(parcel.readInt(), parcel.readInt());
                    return true;
                case 66:
                    parcel.enforceInterface(DESCRIPTOR);
                    hideTransientBars(parcel.readInt());
                    return true;
                case 67:
                    parcel.enforceInterface(DESCRIPTOR);
                    setRecentsVisibility(parcel.readInt() != 0);
                    return true;
                case 68:
                    parcel.enforceInterface(DESCRIPTOR);
                    setPipVisibility(parcel.readInt() != 0);
                    return true;
                case 69:
                    parcel.enforceInterface(DESCRIPTOR);
                    setNavBarVirtualKeyHapticFeedbackEnabled(parcel.readInt() != 0);
                    parcel2.writeNoException();
                    return true;
                case 70:
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean zHasNavigationBar = hasNavigationBar(parcel.readInt());
                    parcel2.writeNoException();
                    parcel2.writeInt(zHasNavigationBar ? 1 : 0);
                    return true;
                case 71:
                    parcel.enforceInterface(DESCRIPTOR);
                    int navBarPosition = getNavBarPosition(parcel.readInt());
                    parcel2.writeNoException();
                    parcel2.writeInt(navBarPosition);
                    return true;
                case 72:
                    parcel.enforceInterface(DESCRIPTOR);
                    lockNow(parcel.readInt() != 0 ? Bundle.CREATOR.createFromParcel(parcel) : null);
                    parcel2.writeNoException();
                    return true;
                case 73:
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean zIsSafeModeEnabled = isSafeModeEnabled();
                    parcel2.writeNoException();
                    parcel2.writeInt(zIsSafeModeEnabled ? 1 : 0);
                    return true;
                case 74:
                    parcel.enforceInterface(DESCRIPTOR);
                    enableScreenIfNeeded();
                    parcel2.writeNoException();
                    return true;
                case 75:
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean zClearWindowContentFrameStats = clearWindowContentFrameStats(parcel.readStrongBinder());
                    parcel2.writeNoException();
                    parcel2.writeInt(zClearWindowContentFrameStats ? 1 : 0);
                    return true;
                case 76:
                    parcel.enforceInterface(DESCRIPTOR);
                    WindowContentFrameStats windowContentFrameStats = getWindowContentFrameStats(parcel.readStrongBinder());
                    parcel2.writeNoException();
                    if (windowContentFrameStats != null) {
                        parcel2.writeInt(1);
                        windowContentFrameStats.writeToParcel(parcel2, 1);
                    } else {
                        parcel2.writeInt(0);
                    }
                    return true;
                case 77:
                    parcel.enforceInterface(DESCRIPTOR);
                    int dockedStackSide = getDockedStackSide();
                    parcel2.writeNoException();
                    parcel2.writeInt(dockedStackSide);
                    return true;
                case 78:
                    parcel.enforceInterface(DESCRIPTOR);
                    setDockedStackDividerTouchRegion(parcel.readInt() != 0 ? Rect.CREATOR.createFromParcel(parcel) : null);
                    parcel2.writeNoException();
                    return true;
                case 79:
                    parcel.enforceInterface(DESCRIPTOR);
                    registerPinnedStackListener(parcel.readInt(), IPinnedStackListener.Stub.asInterface(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    return true;
                case 80:
                    parcel.enforceInterface(DESCRIPTOR);
                    requestAppKeyboardShortcuts(IResultReceiver.Stub.asInterface(parcel.readStrongBinder()), parcel.readInt());
                    parcel2.writeNoException();
                    return true;
                case 81:
                    parcel.enforceInterface(DESCRIPTOR);
                    int i5 = parcel.readInt();
                    Rect rect = new Rect();
                    getStableInsets(i5, rect);
                    parcel2.writeNoException();
                    parcel2.writeInt(1);
                    rect.writeToParcel(parcel2, 1);
                    return true;
                case 82:
                    parcel.enforceInterface(DESCRIPTOR);
                    setForwardedInsets(parcel.readInt(), parcel.readInt() != 0 ? Insets.CREATOR.createFromParcel(parcel) : null);
                    parcel2.writeNoException();
                    return true;
                case 83:
                    parcel.enforceInterface(DESCRIPTOR);
                    registerShortcutKey(parcel.readLong(), IShortcutService.Stub.asInterface(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    return true;
                case 84:
                    parcel.enforceInterface(DESCRIPTOR);
                    IBinder strongBinder = parcel.readStrongBinder();
                    String string = parcel.readString();
                    int i6 = parcel.readInt();
                    InputChannel inputChannel = new InputChannel();
                    createInputConsumer(strongBinder, string, i6, inputChannel);
                    parcel2.writeNoException();
                    parcel2.writeInt(1);
                    inputChannel.writeToParcel(parcel2, 1);
                    return true;
                case 85:
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean zDestroyInputConsumer = destroyInputConsumer(parcel.readString(), parcel.readInt());
                    parcel2.writeNoException();
                    parcel2.writeInt(zDestroyInputConsumer ? 1 : 0);
                    return true;
                case 86:
                    parcel.enforceInterface(DESCRIPTOR);
                    Region currentImeTouchRegion = getCurrentImeTouchRegion();
                    parcel2.writeNoException();
                    if (currentImeTouchRegion != null) {
                        parcel2.writeInt(1);
                        currentImeTouchRegion.writeToParcel(parcel2, 1);
                    } else {
                        parcel2.writeInt(0);
                    }
                    return true;
                case 87:
                    parcel.enforceInterface(DESCRIPTOR);
                    registerDisplayFoldListener(IDisplayFoldListener.Stub.asInterface(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    return true;
                case 88:
                    parcel.enforceInterface(DESCRIPTOR);
                    unregisterDisplayFoldListener(IDisplayFoldListener.Stub.asInterface(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    return true;
                case 89:
                    parcel.enforceInterface(DESCRIPTOR);
                    registerDisplayWindowListener(IDisplayWindowListener.Stub.asInterface(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    return true;
                case 90:
                    parcel.enforceInterface(DESCRIPTOR);
                    unregisterDisplayWindowListener(IDisplayWindowListener.Stub.asInterface(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    return true;
                case 91:
                    parcel.enforceInterface(DESCRIPTOR);
                    startWindowTrace();
                    parcel2.writeNoException();
                    return true;
                case 92:
                    parcel.enforceInterface(DESCRIPTOR);
                    stopWindowTrace();
                    parcel2.writeNoException();
                    return true;
                case 93:
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean zIsWindowTraceEnabled = isWindowTraceEnabled();
                    parcel2.writeNoException();
                    parcel2.writeInt(zIsWindowTraceEnabled ? 1 : 0);
                    return true;
                case 94:
                    parcel.enforceInterface(DESCRIPTOR);
                    dontOverrideDisplayInfo(parcel.readInt());
                    parcel2.writeNoException();
                    return true;
                case 95:
                    parcel.enforceInterface(DESCRIPTOR);
                    int windowingMode = getWindowingMode(parcel.readInt());
                    parcel2.writeNoException();
                    parcel2.writeInt(windowingMode);
                    return true;
                case 96:
                    parcel.enforceInterface(DESCRIPTOR);
                    setWindowingMode(parcel.readInt(), parcel.readInt());
                    parcel2.writeNoException();
                    return true;
                case 97:
                    parcel.enforceInterface(DESCRIPTOR);
                    int removeContentMode = getRemoveContentMode(parcel.readInt());
                    parcel2.writeNoException();
                    parcel2.writeInt(removeContentMode);
                    return true;
                case 98:
                    parcel.enforceInterface(DESCRIPTOR);
                    setRemoveContentMode(parcel.readInt(), parcel.readInt());
                    parcel2.writeNoException();
                    return true;
                case 99:
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean zShouldShowWithInsecureKeyguard = shouldShowWithInsecureKeyguard(parcel.readInt());
                    parcel2.writeNoException();
                    parcel2.writeInt(zShouldShowWithInsecureKeyguard ? 1 : 0);
                    return true;
                case 100:
                    parcel.enforceInterface(DESCRIPTOR);
                    setShouldShowWithInsecureKeyguard(parcel.readInt(), parcel.readInt() != 0);
                    parcel2.writeNoException();
                    return true;
                case 101:
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean zShouldShowSystemDecors = shouldShowSystemDecors(parcel.readInt());
                    parcel2.writeNoException();
                    parcel2.writeInt(zShouldShowSystemDecors ? 1 : 0);
                    return true;
                case 102:
                    parcel.enforceInterface(DESCRIPTOR);
                    setShouldShowSystemDecors(parcel.readInt(), parcel.readInt() != 0);
                    parcel2.writeNoException();
                    return true;
                case 103:
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean zShouldShowIme = shouldShowIme(parcel.readInt());
                    parcel2.writeNoException();
                    parcel2.writeInt(zShouldShowIme ? 1 : 0);
                    return true;
                case 104:
                    parcel.enforceInterface(DESCRIPTOR);
                    setShouldShowIme(parcel.readInt(), parcel.readInt() != 0);
                    parcel2.writeNoException();
                    return true;
                case 105:
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean zInjectInputAfterTransactionsApplied = injectInputAfterTransactionsApplied(parcel.readInt() != 0 ? InputEvent.CREATOR.createFromParcel(parcel) : null, parcel.readInt());
                    parcel2.writeNoException();
                    parcel2.writeInt(zInjectInputAfterTransactionsApplied ? 1 : 0);
                    return true;
                case 106:
                    parcel.enforceInterface(DESCRIPTOR);
                    syncInputTransactions();
                    parcel2.writeNoException();
                    return true;
                case 107:
                    parcel.enforceInterface(DESCRIPTOR);
                    boolean zIsLayerTracing = isLayerTracing();
                    parcel2.writeNoException();
                    parcel2.writeInt(zIsLayerTracing ? 1 : 0);
                    return true;
                case 108:
                    parcel.enforceInterface(DESCRIPTOR);
                    setLayerTracing(parcel.readInt() != 0);
                    parcel2.writeNoException();
                    return true;
                case 109:
                    parcel.enforceInterface(DESCRIPTOR);
                    int i7 = parcel.readInt();
                    SurfaceControl surfaceControl = new SurfaceControl();
                    boolean zMirrorDisplay = mirrorDisplay(i7, surfaceControl);
                    parcel2.writeNoException();
                    parcel2.writeInt(zMirrorDisplay ? 1 : 0);
                    parcel2.writeInt(1);
                    surfaceControl.writeToParcel(parcel2, 1);
                    return true;
                case 110:
                    parcel.enforceInterface(DESCRIPTOR);
                    setDisplayWindowInsetsController(parcel.readInt(), IDisplayWindowInsetsController.Stub.asInterface(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    return true;
                case 111:
                    parcel.enforceInterface(DESCRIPTOR);
                    modifyDisplayWindowInsets(parcel.readInt(), parcel.readInt() != 0 ? InsetsState.CREATOR.createFromParcel(parcel) : null);
                    parcel2.writeNoException();
                    return true;
                case 112:
                    parcel.enforceInterface(DESCRIPTOR);
                    WindowManager.LayoutParams layoutParamsCreateFromParcel = parcel.readInt() != 0 ? WindowManager.LayoutParams.CREATOR.createFromParcel(parcel) : null;
                    int i8 = parcel.readInt();
                    Rect rect2 = new Rect();
                    Rect rect3 = new Rect();
                    DisplayCutout.ParcelableWrapper parcelableWrapper = new DisplayCutout.ParcelableWrapper();
                    InsetsState insetsState = new InsetsState();
                    boolean windowInsets = getWindowInsets(layoutParamsCreateFromParcel, i8, rect2, rect3, parcelableWrapper, insetsState);
                    parcel2.writeNoException();
                    parcel2.writeInt(windowInsets ? 1 : 0);
                    parcel2.writeInt(1);
                    rect2.writeToParcel(parcel2, 1);
                    parcel2.writeInt(1);
                    rect3.writeToParcel(parcel2, 1);
                    parcel2.writeInt(1);
                    parcelableWrapper.writeToParcel(parcel2, 1);
                    parcel2.writeInt(1);
                    insetsState.writeToParcel(parcel2, 1);
                    return true;
                case 113:
                    parcel.enforceInterface(DESCRIPTOR);
                    showGlobalActions();
                    parcel2.writeNoException();
                    return true;
                case 114:
                    parcel.enforceInterface(DESCRIPTOR);
                    setLayerTracingFlags(parcel.readInt());
                    parcel2.writeNoException();
                    return true;
                case 115:
                    parcel.enforceInterface(DESCRIPTOR);
                    requestScrollCapture(parcel.readInt(), parcel.readStrongBinder(), parcel.readInt(), IScrollCaptureController.Stub.asInterface(parcel.readStrongBinder()));
                    parcel2.writeNoException();
                    return true;
                default:
                    return super.onTransact(i, parcel, parcel2, i2);
            }
        }

        private static class Proxy implements IWindowManager {
            public static IWindowManager sDefaultImpl;
            private IBinder mRemote;

            public String getInterfaceDescriptor() {
                return Stub.DESCRIPTOR;
            }

            Proxy(IBinder iBinder) {
                this.mRemote = iBinder;
            }

            @Override // android.os.IInterface
            public IBinder asBinder() {
                return this.mRemote;
            }

            @Override // android.view.IWindowManager
            public boolean startViewServer(int i) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    if (!this.mRemote.transact(1, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().startViewServer(i);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.view.IWindowManager
            public boolean stopViewServer() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(2, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().stopViewServer();
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.view.IWindowManager
            public boolean isViewServerRunning() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(3, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().isViewServerRunning();
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.view.IWindowManager
            public IWindowSession openSession(IWindowSessionCallback iWindowSessionCallback) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeStrongBinder(iWindowSessionCallback != null ? iWindowSessionCallback.asBinder() : null);
                    if (!this.mRemote.transact(4, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().openSession(iWindowSessionCallback);
                    }
                    parcelObtain2.readException();
                    return IWindowSession.Stub.asInterface(parcelObtain2.readStrongBinder());
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.view.IWindowManager
            public boolean useBLAST() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(5, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().useBLAST();
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.view.IWindowManager
            public void getInitialDisplaySize(int i, Point point) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    if (!this.mRemote.transact(6, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().getInitialDisplaySize(i, point);
                        return;
                    }
                    parcelObtain2.readException();
                    if (parcelObtain2.readInt() != 0) {
                        point.readFromParcel(parcelObtain2);
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.view.IWindowManager
            public void getBaseDisplaySize(int i, Point point) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    if (!this.mRemote.transact(7, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().getBaseDisplaySize(i, point);
                        return;
                    }
                    parcelObtain2.readException();
                    if (parcelObtain2.readInt() != 0) {
                        point.readFromParcel(parcelObtain2);
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.view.IWindowManager
            public void setForcedDisplaySize(int i, int i2, int i3) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    parcelObtain.writeInt(i2);
                    parcelObtain.writeInt(i3);
                    if (!this.mRemote.transact(8, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().setForcedDisplaySize(i, i2, i3);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.view.IWindowManager
            public void clearForcedDisplaySize(int i) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    if (!this.mRemote.transact(9, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().clearForcedDisplaySize(i);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.view.IWindowManager
            public int getInitialDisplayDensity(int i) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    if (!this.mRemote.transact(10, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getInitialDisplayDensity(i);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.view.IWindowManager
            public int getBaseDisplayDensity(int i) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    if (!this.mRemote.transact(11, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getBaseDisplayDensity(i);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.view.IWindowManager
            public void setForcedDisplayDensityForUser(int i, int i2, int i3) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    parcelObtain.writeInt(i2);
                    parcelObtain.writeInt(i3);
                    if (!this.mRemote.transact(12, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().setForcedDisplayDensityForUser(i, i2, i3);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.view.IWindowManager
            public void clearForcedDisplayDensityForUser(int i, int i2) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    parcelObtain.writeInt(i2);
                    if (!this.mRemote.transact(13, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().clearForcedDisplayDensityForUser(i, i2);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.view.IWindowManager
            public void setForcedDisplayScalingMode(int i, int i2) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    parcelObtain.writeInt(i2);
                    if (!this.mRemote.transact(14, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().setForcedDisplayScalingMode(i, i2);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.view.IWindowManager
            public void setEventDispatching(boolean z) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(z ? 1 : 0);
                    if (!this.mRemote.transact(15, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().setEventDispatching(z);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.view.IWindowManager
            public boolean isWindowToken(IBinder iBinder) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeStrongBinder(iBinder);
                    if (!this.mRemote.transact(16, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().isWindowToken(iBinder);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.view.IWindowManager
            public int addWindowTokenWithOptions(IBinder iBinder, int i, int i2, Bundle bundle, String str) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeStrongBinder(iBinder);
                    parcelObtain.writeInt(i);
                    parcelObtain.writeInt(i2);
                    if (bundle != null) {
                        parcelObtain.writeInt(1);
                        bundle.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    parcelObtain.writeString(str);
                    if (!this.mRemote.transact(17, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().addWindowTokenWithOptions(iBinder, i, i2, bundle, str);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.view.IWindowManager
            public void addWindowToken(IBinder iBinder, int i, int i2) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeStrongBinder(iBinder);
                    parcelObtain.writeInt(i);
                    parcelObtain.writeInt(i2);
                    if (!this.mRemote.transact(18, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().addWindowToken(iBinder, i, i2);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.view.IWindowManager
            public void removeWindowToken(IBinder iBinder, int i) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeStrongBinder(iBinder);
                    parcelObtain.writeInt(i);
                    if (!this.mRemote.transact(19, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().removeWindowToken(iBinder, i);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.view.IWindowManager
            public void prepareAppTransition(int i, boolean z) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    parcelObtain.writeInt(z ? 1 : 0);
                    if (!this.mRemote.transact(20, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().prepareAppTransition(i, z);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.view.IWindowManager
            public void setDisplayWindowRotationController(IDisplayWindowRotationController iDisplayWindowRotationController) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeStrongBinder(iDisplayWindowRotationController != null ? iDisplayWindowRotationController.asBinder() : null);
                    if (!this.mRemote.transact(21, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().setDisplayWindowRotationController(iDisplayWindowRotationController);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.view.IWindowManager
            public SurfaceControl addShellRoot(int i, IWindow iWindow, int i2) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    parcelObtain.writeStrongBinder(iWindow != null ? iWindow.asBinder() : null);
                    parcelObtain.writeInt(i2);
                    if (!this.mRemote.transact(22, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().addShellRoot(i, iWindow, i2);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0 ? SurfaceControl.CREATOR.createFromParcel(parcelObtain2) : null;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.view.IWindowManager
            public void setShellRootAccessibilityWindow(int i, int i2, IWindow iWindow) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    parcelObtain.writeInt(i2);
                    parcelObtain.writeStrongBinder(iWindow != null ? iWindow.asBinder() : null);
                    if (!this.mRemote.transact(23, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().setShellRootAccessibilityWindow(i, i2, iWindow);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.view.IWindowManager
            public void overridePendingAppTransitionMultiThumbFuture(IAppTransitionAnimationSpecsFuture iAppTransitionAnimationSpecsFuture, IRemoteCallback iRemoteCallback, boolean z, int i) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeStrongBinder(iAppTransitionAnimationSpecsFuture != null ? iAppTransitionAnimationSpecsFuture.asBinder() : null);
                    parcelObtain.writeStrongBinder(iRemoteCallback != null ? iRemoteCallback.asBinder() : null);
                    parcelObtain.writeInt(z ? 1 : 0);
                    parcelObtain.writeInt(i);
                    if (!this.mRemote.transact(24, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().overridePendingAppTransitionMultiThumbFuture(iAppTransitionAnimationSpecsFuture, iRemoteCallback, z, i);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.view.IWindowManager
            public void overridePendingAppTransitionRemote(RemoteAnimationAdapter remoteAnimationAdapter, int i) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (remoteAnimationAdapter != null) {
                        parcelObtain.writeInt(1);
                        remoteAnimationAdapter.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    parcelObtain.writeInt(i);
                    if (!this.mRemote.transact(25, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().overridePendingAppTransitionRemote(remoteAnimationAdapter, i);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.view.IWindowManager
            public void executeAppTransition() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(26, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().executeAppTransition();
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.view.IWindowManager
            public void endProlongedAnimations() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(27, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().endProlongedAnimations();
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.view.IWindowManager
            public void startFreezingScreen(int i, int i2) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    parcelObtain.writeInt(i2);
                    if (!this.mRemote.transact(28, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().startFreezingScreen(i, i2);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.view.IWindowManager
            public void stopFreezingScreen() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(29, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().stopFreezingScreen();
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.view.IWindowManager
            public void disableKeyguard(IBinder iBinder, String str, int i) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeStrongBinder(iBinder);
                    parcelObtain.writeString(str);
                    parcelObtain.writeInt(i);
                    if (!this.mRemote.transact(30, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().disableKeyguard(iBinder, str, i);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.view.IWindowManager
            public void reenableKeyguard(IBinder iBinder, int i) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeStrongBinder(iBinder);
                    parcelObtain.writeInt(i);
                    if (!this.mRemote.transact(31, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().reenableKeyguard(iBinder, i);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.view.IWindowManager
            public void exitKeyguardSecurely(IOnKeyguardExitResult iOnKeyguardExitResult) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeStrongBinder(iOnKeyguardExitResult != null ? iOnKeyguardExitResult.asBinder() : null);
                    if (!this.mRemote.transact(32, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().exitKeyguardSecurely(iOnKeyguardExitResult);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.view.IWindowManager
            public boolean isKeyguardLocked() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(33, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().isKeyguardLocked();
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.view.IWindowManager
            public boolean isKeyguardSecure(int i) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    if (!this.mRemote.transact(34, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().isKeyguardSecure(i);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.view.IWindowManager
            public void dismissKeyguard(IKeyguardDismissCallback iKeyguardDismissCallback, CharSequence charSequence) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeStrongBinder(iKeyguardDismissCallback != null ? iKeyguardDismissCallback.asBinder() : null);
                    if (charSequence != null) {
                        parcelObtain.writeInt(1);
                        TextUtils.writeToParcel(charSequence, parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    if (!this.mRemote.transact(35, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().dismissKeyguard(iKeyguardDismissCallback, charSequence);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.view.IWindowManager
            public void setSwitchingUser(boolean z) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(z ? 1 : 0);
                    if (!this.mRemote.transact(36, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().setSwitchingUser(z);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.view.IWindowManager
            public void closeSystemDialogs(String str) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeString(str);
                    if (!this.mRemote.transact(37, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().closeSystemDialogs(str);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.view.IWindowManager
            public float getAnimationScale(int i) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    if (!this.mRemote.transact(38, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getAnimationScale(i);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readFloat();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.view.IWindowManager
            public float[] getAnimationScales() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(39, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getAnimationScales();
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.createFloatArray();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.view.IWindowManager
            public void setAnimationScale(int i, float f) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    parcelObtain.writeFloat(f);
                    if (!this.mRemote.transact(40, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().setAnimationScale(i, f);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.view.IWindowManager
            public void setAnimationScales(float[] fArr) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeFloatArray(fArr);
                    if (!this.mRemote.transact(41, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().setAnimationScales(fArr);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.view.IWindowManager
            public float getCurrentAnimatorScale() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(42, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getCurrentAnimatorScale();
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readFloat();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.view.IWindowManager
            public void setInTouchMode(boolean z) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(z ? 1 : 0);
                    if (!this.mRemote.transact(43, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().setInTouchMode(z);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.view.IWindowManager
            public void showStrictModeViolation(boolean z) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(z ? 1 : 0);
                    if (!this.mRemote.transact(44, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().showStrictModeViolation(z);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.view.IWindowManager
            public void setStrictModeVisualIndicatorPreference(String str) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeString(str);
                    if (!this.mRemote.transact(45, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().setStrictModeVisualIndicatorPreference(str);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.view.IWindowManager
            public void refreshScreenCaptureDisabled(int i) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    if (!this.mRemote.transact(46, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().refreshScreenCaptureDisabled(i);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.view.IWindowManager
            public void updateRotation(boolean z, boolean z2) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    int i = 1;
                    parcelObtain.writeInt(z ? 1 : 0);
                    if (!z2) {
                        i = 0;
                    }
                    parcelObtain.writeInt(i);
                    if (!this.mRemote.transact(47, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().updateRotation(z, z2);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.view.IWindowManager
            public int getDefaultDisplayRotation() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(48, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getDefaultDisplayRotation();
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.view.IWindowManager
            public int watchRotation(IRotationWatcher iRotationWatcher, int i) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeStrongBinder(iRotationWatcher != null ? iRotationWatcher.asBinder() : null);
                    parcelObtain.writeInt(i);
                    if (!this.mRemote.transact(49, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().watchRotation(iRotationWatcher, i);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.view.IWindowManager
            public void removeRotationWatcher(IRotationWatcher iRotationWatcher) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeStrongBinder(iRotationWatcher != null ? iRotationWatcher.asBinder() : null);
                    if (!this.mRemote.transact(50, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().removeRotationWatcher(iRotationWatcher);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.view.IWindowManager
            public int getPreferredOptionsPanelGravity(int i) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    if (!this.mRemote.transact(51, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getPreferredOptionsPanelGravity(i);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.view.IWindowManager
            public void freezeRotation(int i) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    if (!this.mRemote.transact(52, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().freezeRotation(i);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.view.IWindowManager
            public void thawRotation() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(53, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().thawRotation();
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.view.IWindowManager
            public boolean isRotationFrozen() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(54, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().isRotationFrozen();
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.view.IWindowManager
            public void freezeDisplayRotation(int i, int i2) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    parcelObtain.writeInt(i2);
                    if (!this.mRemote.transact(55, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().freezeDisplayRotation(i, i2);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.view.IWindowManager
            public void thawDisplayRotation(int i) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    if (!this.mRemote.transact(56, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().thawDisplayRotation(i);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.view.IWindowManager
            public boolean isDisplayRotationFrozen(int i) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    if (!this.mRemote.transact(57, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().isDisplayRotationFrozen(i);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.view.IWindowManager
            public void setFixedToUserRotation(int i, int i2) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    parcelObtain.writeInt(i2);
                    if (!this.mRemote.transact(58, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().setFixedToUserRotation(i, i2);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.view.IWindowManager
            public Bitmap screenshotWallpaper() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(59, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().screenshotWallpaper();
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0 ? Bitmap.CREATOR.createFromParcel(parcelObtain2) : null;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.view.IWindowManager
            public boolean registerWallpaperVisibilityListener(IWallpaperVisibilityListener iWallpaperVisibilityListener, int i) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeStrongBinder(iWallpaperVisibilityListener != null ? iWallpaperVisibilityListener.asBinder() : null);
                    parcelObtain.writeInt(i);
                    if (!this.mRemote.transact(60, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().registerWallpaperVisibilityListener(iWallpaperVisibilityListener, i);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.view.IWindowManager
            public void unregisterWallpaperVisibilityListener(IWallpaperVisibilityListener iWallpaperVisibilityListener, int i) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeStrongBinder(iWallpaperVisibilityListener != null ? iWallpaperVisibilityListener.asBinder() : null);
                    parcelObtain.writeInt(i);
                    if (!this.mRemote.transact(61, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().unregisterWallpaperVisibilityListener(iWallpaperVisibilityListener, i);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.view.IWindowManager
            public void registerSystemGestureExclusionListener(ISystemGestureExclusionListener iSystemGestureExclusionListener, int i) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeStrongBinder(iSystemGestureExclusionListener != null ? iSystemGestureExclusionListener.asBinder() : null);
                    parcelObtain.writeInt(i);
                    if (!this.mRemote.transact(62, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().registerSystemGestureExclusionListener(iSystemGestureExclusionListener, i);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.view.IWindowManager
            public void unregisterSystemGestureExclusionListener(ISystemGestureExclusionListener iSystemGestureExclusionListener, int i) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeStrongBinder(iSystemGestureExclusionListener != null ? iSystemGestureExclusionListener.asBinder() : null);
                    parcelObtain.writeInt(i);
                    if (!this.mRemote.transact(63, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().unregisterSystemGestureExclusionListener(iSystemGestureExclusionListener, i);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.view.IWindowManager
            public boolean requestAssistScreenshot(IAssistDataReceiver iAssistDataReceiver) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeStrongBinder(iAssistDataReceiver != null ? iAssistDataReceiver.asBinder() : null);
                    if (!this.mRemote.transact(64, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().requestAssistScreenshot(iAssistDataReceiver);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.view.IWindowManager
            public void statusBarVisibilityChanged(int i, int i2) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    parcelObtain.writeInt(i2);
                    if (this.mRemote.transact(65, parcelObtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().statusBarVisibilityChanged(i, i2);
                } finally {
                    parcelObtain.recycle();
                }
            }

            @Override // android.view.IWindowManager
            public void hideTransientBars(int i) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    if (this.mRemote.transact(66, parcelObtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().hideTransientBars(i);
                } finally {
                    parcelObtain.recycle();
                }
            }

            @Override // android.view.IWindowManager
            public void setRecentsVisibility(boolean z) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(z ? 1 : 0);
                    if (this.mRemote.transact(67, parcelObtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().setRecentsVisibility(z);
                } finally {
                    parcelObtain.recycle();
                }
            }

            @Override // android.view.IWindowManager
            public void setPipVisibility(boolean z) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(z ? 1 : 0);
                    if (this.mRemote.transact(68, parcelObtain, null, 1) || Stub.getDefaultImpl() == null) {
                        return;
                    }
                    Stub.getDefaultImpl().setPipVisibility(z);
                } finally {
                    parcelObtain.recycle();
                }
            }

            @Override // android.view.IWindowManager
            public void setNavBarVirtualKeyHapticFeedbackEnabled(boolean z) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(z ? 1 : 0);
                    if (!this.mRemote.transact(69, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().setNavBarVirtualKeyHapticFeedbackEnabled(z);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.view.IWindowManager
            public boolean hasNavigationBar(int i) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    if (!this.mRemote.transact(70, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().hasNavigationBar(i);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.view.IWindowManager
            public int getNavBarPosition(int i) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    if (!this.mRemote.transact(71, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getNavBarPosition(i);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.view.IWindowManager
            public void lockNow(Bundle bundle) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (bundle != null) {
                        parcelObtain.writeInt(1);
                        bundle.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    if (!this.mRemote.transact(72, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().lockNow(bundle);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.view.IWindowManager
            public boolean isSafeModeEnabled() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(73, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().isSafeModeEnabled();
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.view.IWindowManager
            public void enableScreenIfNeeded() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(74, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().enableScreenIfNeeded();
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.view.IWindowManager
            public boolean clearWindowContentFrameStats(IBinder iBinder) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeStrongBinder(iBinder);
                    if (!this.mRemote.transact(75, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().clearWindowContentFrameStats(iBinder);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.view.IWindowManager
            public WindowContentFrameStats getWindowContentFrameStats(IBinder iBinder) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeStrongBinder(iBinder);
                    if (!this.mRemote.transact(76, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getWindowContentFrameStats(iBinder);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0 ? WindowContentFrameStats.CREATOR.createFromParcel(parcelObtain2) : null;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.view.IWindowManager
            public int getDockedStackSide() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(77, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getDockedStackSide();
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.view.IWindowManager
            public void setDockedStackDividerTouchRegion(Rect rect) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (rect != null) {
                        parcelObtain.writeInt(1);
                        rect.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    if (!this.mRemote.transact(78, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().setDockedStackDividerTouchRegion(rect);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.view.IWindowManager
            public void registerPinnedStackListener(int i, IPinnedStackListener iPinnedStackListener) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    parcelObtain.writeStrongBinder(iPinnedStackListener != null ? iPinnedStackListener.asBinder() : null);
                    if (!this.mRemote.transact(79, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().registerPinnedStackListener(i, iPinnedStackListener);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.view.IWindowManager
            public void requestAppKeyboardShortcuts(IResultReceiver iResultReceiver, int i) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeStrongBinder(iResultReceiver != null ? iResultReceiver.asBinder() : null);
                    parcelObtain.writeInt(i);
                    if (!this.mRemote.transact(80, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().requestAppKeyboardShortcuts(iResultReceiver, i);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.view.IWindowManager
            public void getStableInsets(int i, Rect rect) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    if (!this.mRemote.transact(81, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().getStableInsets(i, rect);
                        return;
                    }
                    parcelObtain2.readException();
                    if (parcelObtain2.readInt() != 0) {
                        rect.readFromParcel(parcelObtain2);
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.view.IWindowManager
            public void setForwardedInsets(int i, Insets insets) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    if (insets != null) {
                        parcelObtain.writeInt(1);
                        insets.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    if (!this.mRemote.transact(82, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().setForwardedInsets(i, insets);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.view.IWindowManager
            public void registerShortcutKey(long j, IShortcutService iShortcutService) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeLong(j);
                    parcelObtain.writeStrongBinder(iShortcutService != null ? iShortcutService.asBinder() : null);
                    if (!this.mRemote.transact(83, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().registerShortcutKey(j, iShortcutService);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.view.IWindowManager
            public void createInputConsumer(IBinder iBinder, String str, int i, InputChannel inputChannel) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeStrongBinder(iBinder);
                    parcelObtain.writeString(str);
                    parcelObtain.writeInt(i);
                    if (!this.mRemote.transact(84, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().createInputConsumer(iBinder, str, i, inputChannel);
                        return;
                    }
                    parcelObtain2.readException();
                    if (parcelObtain2.readInt() != 0) {
                        inputChannel.readFromParcel(parcelObtain2);
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.view.IWindowManager
            public boolean destroyInputConsumer(String str, int i) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeString(str);
                    parcelObtain.writeInt(i);
                    if (!this.mRemote.transact(85, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().destroyInputConsumer(str, i);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.view.IWindowManager
            public Region getCurrentImeTouchRegion() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(86, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getCurrentImeTouchRegion();
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0 ? Region.CREATOR.createFromParcel(parcelObtain2) : null;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.view.IWindowManager
            public void registerDisplayFoldListener(IDisplayFoldListener iDisplayFoldListener) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeStrongBinder(iDisplayFoldListener != null ? iDisplayFoldListener.asBinder() : null);
                    if (!this.mRemote.transact(87, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().registerDisplayFoldListener(iDisplayFoldListener);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.view.IWindowManager
            public void unregisterDisplayFoldListener(IDisplayFoldListener iDisplayFoldListener) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeStrongBinder(iDisplayFoldListener != null ? iDisplayFoldListener.asBinder() : null);
                    if (!this.mRemote.transact(88, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().unregisterDisplayFoldListener(iDisplayFoldListener);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.view.IWindowManager
            public void registerDisplayWindowListener(IDisplayWindowListener iDisplayWindowListener) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeStrongBinder(iDisplayWindowListener != null ? iDisplayWindowListener.asBinder() : null);
                    if (!this.mRemote.transact(89, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().registerDisplayWindowListener(iDisplayWindowListener);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.view.IWindowManager
            public void unregisterDisplayWindowListener(IDisplayWindowListener iDisplayWindowListener) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeStrongBinder(iDisplayWindowListener != null ? iDisplayWindowListener.asBinder() : null);
                    if (!this.mRemote.transact(90, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().unregisterDisplayWindowListener(iDisplayWindowListener);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.view.IWindowManager
            public void startWindowTrace() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(91, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().startWindowTrace();
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.view.IWindowManager
            public void stopWindowTrace() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(92, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().stopWindowTrace();
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.view.IWindowManager
            public boolean isWindowTraceEnabled() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(93, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().isWindowTraceEnabled();
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.view.IWindowManager
            public void dontOverrideDisplayInfo(int i) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    if (!this.mRemote.transact(94, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().dontOverrideDisplayInfo(i);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.view.IWindowManager
            public int getWindowingMode(int i) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    if (!this.mRemote.transact(95, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getWindowingMode(i);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.view.IWindowManager
            public void setWindowingMode(int i, int i2) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    parcelObtain.writeInt(i2);
                    if (!this.mRemote.transact(96, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().setWindowingMode(i, i2);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.view.IWindowManager
            public int getRemoveContentMode(int i) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    if (!this.mRemote.transact(97, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().getRemoveContentMode(i);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt();
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.view.IWindowManager
            public void setRemoveContentMode(int i, int i2) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    parcelObtain.writeInt(i2);
                    if (!this.mRemote.transact(98, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().setRemoveContentMode(i, i2);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.view.IWindowManager
            public boolean shouldShowWithInsecureKeyguard(int i) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    if (!this.mRemote.transact(99, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().shouldShowWithInsecureKeyguard(i);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.view.IWindowManager
            public void setShouldShowWithInsecureKeyguard(int i, boolean z) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    parcelObtain.writeInt(z ? 1 : 0);
                    if (!this.mRemote.transact(100, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().setShouldShowWithInsecureKeyguard(i, z);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.view.IWindowManager
            public boolean shouldShowSystemDecors(int i) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    if (!this.mRemote.transact(101, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().shouldShowSystemDecors(i);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.view.IWindowManager
            public void setShouldShowSystemDecors(int i, boolean z) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    parcelObtain.writeInt(z ? 1 : 0);
                    if (!this.mRemote.transact(102, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().setShouldShowSystemDecors(i, z);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.view.IWindowManager
            public boolean shouldShowIme(int i) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    if (!this.mRemote.transact(103, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().shouldShowIme(i);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.view.IWindowManager
            public void setShouldShowIme(int i, boolean z) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    parcelObtain.writeInt(z ? 1 : 0);
                    if (!this.mRemote.transact(104, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().setShouldShowIme(i, z);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.view.IWindowManager
            public boolean injectInputAfterTransactionsApplied(InputEvent inputEvent, int i) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (inputEvent != null) {
                        parcelObtain.writeInt(1);
                        inputEvent.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    parcelObtain.writeInt(i);
                    if (!this.mRemote.transact(105, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().injectInputAfterTransactionsApplied(inputEvent, i);
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.view.IWindowManager
            public void syncInputTransactions() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(106, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().syncInputTransactions();
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.view.IWindowManager
            public boolean isLayerTracing() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(107, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().isLayerTracing();
                    }
                    parcelObtain2.readException();
                    return parcelObtain2.readInt() != 0;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.view.IWindowManager
            public void setLayerTracing(boolean z) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(z ? 1 : 0);
                    if (!this.mRemote.transact(108, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().setLayerTracing(z);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.view.IWindowManager
            public boolean mirrorDisplay(int i, SurfaceControl surfaceControl) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    if (!this.mRemote.transact(109, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        return Stub.getDefaultImpl().mirrorDisplay(i, surfaceControl);
                    }
                    parcelObtain2.readException();
                    boolean z = parcelObtain2.readInt() != 0;
                    if (parcelObtain2.readInt() != 0) {
                        surfaceControl.readFromParcel(parcelObtain2);
                    }
                    return z;
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.view.IWindowManager
            public void setDisplayWindowInsetsController(int i, IDisplayWindowInsetsController iDisplayWindowInsetsController) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    parcelObtain.writeStrongBinder(iDisplayWindowInsetsController != null ? iDisplayWindowInsetsController.asBinder() : null);
                    if (!this.mRemote.transact(110, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().setDisplayWindowInsetsController(i, iDisplayWindowInsetsController);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.view.IWindowManager
            public void modifyDisplayWindowInsets(int i, InsetsState insetsState) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    if (insetsState != null) {
                        parcelObtain.writeInt(1);
                        insetsState.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    if (!this.mRemote.transact(111, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().modifyDisplayWindowInsets(i, insetsState);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.view.IWindowManager
            public boolean getWindowInsets(WindowManager.LayoutParams layoutParams, int i, Rect rect, Rect rect2, DisplayCutout.ParcelableWrapper parcelableWrapper, InsetsState insetsState) throws Throwable {
                boolean z;
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    z = true;
                    if (layoutParams != null) {
                        parcelObtain.writeInt(1);
                        layoutParams.writeToParcel(parcelObtain, 0);
                    } else {
                        parcelObtain.writeInt(0);
                    }
                    parcelObtain.writeInt(i);
                } catch (Throwable th) {
                    th = th;
                }
                try {
                    if (!this.mRemote.transact(112, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        boolean windowInsets = Stub.getDefaultImpl().getWindowInsets(layoutParams, i, rect, rect2, parcelableWrapper, insetsState);
                        parcelObtain2.recycle();
                        parcelObtain.recycle();
                        return windowInsets;
                    }
                    parcelObtain2.readException();
                    if (parcelObtain2.readInt() == 0) {
                        z = false;
                    }
                    if (parcelObtain2.readInt() != 0) {
                        rect.readFromParcel(parcelObtain2);
                    }
                    if (parcelObtain2.readInt() != 0) {
                        rect2.readFromParcel(parcelObtain2);
                    }
                    if (parcelObtain2.readInt() != 0) {
                        parcelableWrapper.readFromParcel(parcelObtain2);
                    }
                    if (parcelObtain2.readInt() != 0) {
                        insetsState.readFromParcel(parcelObtain2);
                    }
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                    return z;
                } catch (Throwable th2) {
                    th = th2;
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                    throw th;
                }
            }

            @Override // android.view.IWindowManager
            public void showGlobalActions() throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    if (!this.mRemote.transact(113, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().showGlobalActions();
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.view.IWindowManager
            public void setLayerTracingFlags(int i) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    if (!this.mRemote.transact(114, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().setLayerTracingFlags(i);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }

            @Override // android.view.IWindowManager
            public void requestScrollCapture(int i, IBinder iBinder, int i2, IScrollCaptureController iScrollCaptureController) throws RemoteException {
                Parcel parcelObtain = Parcel.obtain();
                Parcel parcelObtain2 = Parcel.obtain();
                try {
                    parcelObtain.writeInterfaceToken(Stub.DESCRIPTOR);
                    parcelObtain.writeInt(i);
                    parcelObtain.writeStrongBinder(iBinder);
                    parcelObtain.writeInt(i2);
                    parcelObtain.writeStrongBinder(iScrollCaptureController != null ? iScrollCaptureController.asBinder() : null);
                    if (!this.mRemote.transact(115, parcelObtain, parcelObtain2, 0) && Stub.getDefaultImpl() != null) {
                        Stub.getDefaultImpl().requestScrollCapture(i, iBinder, i2, iScrollCaptureController);
                    } else {
                        parcelObtain2.readException();
                    }
                } finally {
                    parcelObtain2.recycle();
                    parcelObtain.recycle();
                }
            }
        }

        public static boolean setDefaultImpl(IWindowManager iWindowManager) {
            if (Proxy.sDefaultImpl != null) {
                throw new IllegalStateException("setDefaultImpl() called twice");
            }
            if (iWindowManager == null) {
                return false;
            }
            Proxy.sDefaultImpl = iWindowManager;
            return true;
        }

        public static IWindowManager getDefaultImpl() {
            return Proxy.sDefaultImpl;
        }
    }
}
