package com.android.systemui.statusbar.phone;

import android.content.Context;
import android.os.Handler;
import android.os.RemoteException;
import android.util.Log;
import android.view.IWindowManager;
import android.view.MotionEvent;
import com.android.systemui.statusbar.AutoHideUiElement;
import com.bydux.api.common.tools.log.BydLogHelper;
import javax.inject.Inject;

/* JADX INFO: loaded from: classes.dex */
public class AutoHideController {
    private static final long AUTO_HIDE_TIMEOUT_MS = 3000;
    private static final String TAG = "AutoHideController";
    private final Runnable mAutoHide = new Runnable() { // from class: com.android.systemui.statusbar.phone.-$$Lambda$AutoHideController$51yxTBOfPdmJcNGQJkzQf14VYd0
        @Override // java.lang.Runnable
        public final void run() {
            this.f$0.lambda$new$0$AutoHideController();
        }
    };
    private boolean mAutoHideSuspended;
    private int mDisplayId;
    private final Handler mHandler;
    private AutoHideUiElement mNavigationBar;
    private AutoHideUiElement mStatusBar;
    private final IWindowManager mWindowManagerService;

    public /* synthetic */ void lambda$new$0$AutoHideController() {
        if (isAnyTransientBarShown()) {
            hideTransientBars();
        }
    }

    @Inject
    public AutoHideController(Context context, Handler handler, IWindowManager iWindowManager) {
        this.mHandler = handler;
        this.mWindowManagerService = iWindowManager;
        this.mDisplayId = context.getDisplayId();
    }

    public void setStatusBar(AutoHideUiElement element) {
        this.mStatusBar = element;
    }

    public void setNavigationBar(AutoHideUiElement element) {
        this.mNavigationBar = element;
    }

    private void hideTransientBars() {
        try {
            this.mWindowManagerService.hideTransientBars(this.mDisplayId);
        } catch (RemoteException unused) {
            Log.w(TAG, "Cannot get WindowManager");
        }
        AutoHideUiElement autoHideUiElement = this.mStatusBar;
        if (autoHideUiElement != null) {
            autoHideUiElement.hide();
        }
        AutoHideUiElement autoHideUiElement2 = this.mNavigationBar;
        if (autoHideUiElement2 != null) {
            autoHideUiElement2.hide();
        }
    }

    void resumeSuspendedAutoHide() {
        if (this.mAutoHideSuspended) {
            BydLogHelper.logD();
            scheduleAutoHide();
            Runnable checkBarModesRunnable = getCheckBarModesRunnable();
            if (checkBarModesRunnable != null) {
                this.mHandler.postDelayed(checkBarModesRunnable, 200L);
            }
        }
    }

    void suspendAutoHide() {
        BydLogHelper.logD();
        this.mHandler.removeCallbacks(this.mAutoHide);
        Runnable checkBarModesRunnable = getCheckBarModesRunnable();
        if (checkBarModesRunnable != null) {
            this.mHandler.removeCallbacks(checkBarModesRunnable);
        }
        this.mAutoHideSuspended = isAnyTransientBarShown();
    }

    public void touchAutoHide() {
        if (isAnyTransientBarShown()) {
            scheduleAutoHide();
        } else {
            cancelAutoHide();
        }
    }

    private Runnable getCheckBarModesRunnable() {
        if (this.mStatusBar != null) {
            return new Runnable() { // from class: com.android.systemui.statusbar.phone.-$$Lambda$AutoHideController$0CJU6rijI6TTA9m1alRCadn6198
                @Override // java.lang.Runnable
                public final void run() {
                    this.f$0.lambda$getCheckBarModesRunnable$1$AutoHideController();
                }
            };
        }
        if (this.mNavigationBar != null) {
            return new Runnable() { // from class: com.android.systemui.statusbar.phone.-$$Lambda$AutoHideController$KUlOhDliIYqm-7CVFNH9UWFs3K0
                @Override // java.lang.Runnable
                public final void run() {
                    this.f$0.lambda$getCheckBarModesRunnable$2$AutoHideController();
                }
            };
        }
        return null;
    }

    public /* synthetic */ void lambda$getCheckBarModesRunnable$1$AutoHideController() {
        this.mStatusBar.synchronizeState();
    }

    public /* synthetic */ void lambda$getCheckBarModesRunnable$2$AutoHideController() {
        this.mNavigationBar.synchronizeState();
    }

    private void cancelAutoHide() {
        this.mAutoHideSuspended = false;
        this.mHandler.removeCallbacks(this.mAutoHide);
    }

    private void scheduleAutoHide() {
        cancelAutoHide();
        this.mHandler.postDelayed(this.mAutoHide, AUTO_HIDE_TIMEOUT_MS);
    }

    void checkUserAutoHide(MotionEvent event) {
        boolean zShouldHideOnTouch = isAnyTransientBarShown() && event.getAction() == 4 && event.getX() == 0.0f && event.getY() == 0.0f;
        AutoHideUiElement autoHideUiElement = this.mStatusBar;
        if (autoHideUiElement != null) {
            zShouldHideOnTouch &= autoHideUiElement.shouldHideOnTouch();
        }
        AutoHideUiElement autoHideUiElement2 = this.mNavigationBar;
        if (autoHideUiElement2 != null) {
            zShouldHideOnTouch &= autoHideUiElement2.shouldHideOnTouch();
        }
        if (zShouldHideOnTouch) {
            userAutoHide();
        }
    }

    private void userAutoHide() {
        cancelAutoHide();
        this.mHandler.postDelayed(this.mAutoHide, 350L);
    }

    private boolean isAnyTransientBarShown() {
        AutoHideUiElement autoHideUiElement = this.mStatusBar;
        if (autoHideUiElement != null && autoHideUiElement.isVisible()) {
            return true;
        }
        AutoHideUiElement autoHideUiElement2 = this.mNavigationBar;
        return autoHideUiElement2 != null && autoHideUiElement2.isVisible();
    }
}
