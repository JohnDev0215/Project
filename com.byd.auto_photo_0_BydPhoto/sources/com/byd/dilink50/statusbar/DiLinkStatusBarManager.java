package com.byd.dilink50.statusbar;

import android.annotation.SuppressLint;
import android.app.ICardListener;
import android.app.ISceneModeStateCallback;
import android.app.ISplitScreenCallBack;
import android.app.StatusBarManager;
import android.content.Context;
import android.content.Intent;
import androidx.annotation.Keep;
import androidx.annotation.NonNull;
import com.byd.dilink.DiLink;
import com.byd.dilink.UnsupportedTargetPlatformException;
import com.byd.dilink.anotation.Platform;
import com.byd.dilink.callbackholder.DiLinkCallbackHolders;
import com.byd.dilink.callbackholder.IDiLinkCallbackHandler;
import com.byd.dilink.statusbar.IDiLinkCardListener;
import com.byd.dilink.statusbar.IDiLinkSceneModeStateCallback;
import com.byd.dilink.statusbar.IDiLinkSplitScreenCallBack;
import com.byd.dilink.statusbar.IDiLinkStatusBarManager;
import com.byd.foundation.annotation.ServiceImpl;

/* JADX INFO: loaded from: classes.dex */
@Platform(versions = {DiLink.VERSION_NAME_5_0})
@ServiceImpl(service = IDiLinkStatusBarManager.class, singleton = true)
public class DiLinkStatusBarManager implements IDiLinkStatusBarManager {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private DiLink5_0_SplitScreenCallBacks f1463a = new DiLink5_0_SplitScreenCallBacks();

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private DiLink5_0_SceneModeStateCallbacks f1464b = new DiLink5_0_SceneModeStateCallbacks();

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private DiLink5_0_CardListeners f1465c = new DiLink5_0_CardListeners();
    private StatusBarManager d;

    public static class DiLink5_0_CardListeners extends DiLinkCallbackHolders<IDiLinkCardListener, ICardListener> {
        @Override // com.byd.dilink.callbackholder.DiLinkCallbackHolders
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public ICardListener createInnerCallback(final IDiLinkCardListener iDiLinkCardListener) {
            return new ICardListener.Stub() { // from class: com.byd.dilink50.statusbar.DiLinkStatusBarManager.DiLink5_0_CardListeners.1
                @Keep
                public void onHideCard() {
                    iDiLinkCardListener.onHideCard();
                }

                @Keep
                public void onShowCard() {
                    iDiLinkCardListener.onShowCard();
                }
            };
        }
    }

    public static class DiLink5_0_SceneModeStateCallbacks extends DiLinkCallbackHolders<IDiLinkSceneModeStateCallback, ISceneModeStateCallback> {
        @Override // com.byd.dilink.callbackholder.DiLinkCallbackHolders
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public ISceneModeStateCallback createInnerCallback(final IDiLinkSceneModeStateCallback iDiLinkSceneModeStateCallback) {
            return new ISceneModeStateCallback.Stub() { // from class: com.byd.dilink50.statusbar.DiLinkStatusBarManager.DiLink5_0_SceneModeStateCallbacks.1
                @Keep
                public void onContent(String str) {
                    iDiLinkSceneModeStateCallback.onContent(str);
                }
            };
        }
    }

    public static class DiLink5_0_SplitScreenCallBacks extends DiLinkCallbackHolders<IDiLinkSplitScreenCallBack, ISplitScreenCallBack> {
        @Override // com.byd.dilink.callbackholder.DiLinkCallbackHolders
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public ISplitScreenCallBack createInnerCallback(final IDiLinkSplitScreenCallBack iDiLinkSplitScreenCallBack) {
            return new ISplitScreenCallBack.Stub() { // from class: com.byd.dilink50.statusbar.DiLinkStatusBarManager.DiLink5_0_SplitScreenCallBacks.1
                @Keep
                public void onDockedStackExistsChanged(boolean z) {
                    iDiLinkSplitScreenCallBack.onDockedStackExistsChanged(z);
                }
            };
        }
    }

    @SuppressLint({"WrongConstant"})
    public DiLinkStatusBarManager(Context context) {
        this.d = (StatusBarManager) context.getSystemService("statusbar");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void a(ICardListener iCardListener) {
        this.d.registerCardListener(iCardListener);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void b(ISceneModeStateCallback iSceneModeStateCallback) {
        this.d.registerSceneModeCallback(iSceneModeStateCallback);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void c(ISplitScreenCallBack iSplitScreenCallBack) {
        this.d.registerSplitScreenCallBack(iSplitScreenCallBack);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void d(ICardListener iCardListener) {
        this.d.unregisterCardListener(iCardListener);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void e(ISceneModeStateCallback iSceneModeStateCallback) {
        this.d.unregisterSceneModeCallback(iSceneModeStateCallback);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void f(ISplitScreenCallBack iSplitScreenCallBack) {
        this.d.unregisterSplitScreenCallBack(iSplitScreenCallBack);
    }

    @Override // com.byd.dilink.statusbar.IDiLinkStatusBarManager
    public boolean closeAppWhenMultiWindow(String str, int i) {
        return this.d.closeAppWhenMultiWindow(str, i);
    }

    @Override // com.byd.dilink.statusbar.IDiLinkStatusBarManager
    public void collapsePanels() {
        this.d.collapsePanels();
    }

    @Override // com.byd.dilink.statusbar.IDiLinkStatusBarManager
    public void enterSplitScreenModeByLauncher(String str, Intent intent) {
        this.d.enterSplitScreenModeByLaucher(str, intent);
    }

    @Override // com.byd.dilink.statusbar.IDiLinkStatusBarManager
    public void enterSplitScreenModeByLauncher(String str, String str2) {
        this.d.enterSplitScreenModeByLaucher(str, str2);
    }

    @Override // com.byd.dilink.statusbar.IDiLinkStatusBarManager
    public String getAppPkgByDirection(boolean z) {
        return this.d.getAppPkgByDirection(z);
    }

    @Override // com.byd.dilink.statusbar.IDiLinkStatusBarManager
    public int getSplitScreenStatus() {
        return this.d.getSplitScreenStatus();
    }

    @Override // com.byd.dilink.statusbar.IDiLinkStatusBarManager
    public void handleHomeButton() {
        this.d.handleHomeButton();
    }

    @Override // com.byd.dilink.statusbar.IDiLinkStatusBarManager
    public int isAppSupportSplit(String str) {
        return this.d.isAppSuportSplit(str);
    }

    @Override // com.byd.dilink.statusbar.IDiLinkStatusBarManager
    public boolean isDockState() {
        return this.d.isDockState();
    }

    @Override // com.byd.dilink.statusbar.IDiLinkStatusBarManager
    public boolean isFocusInPrimary() {
        return this.d.isFocusInPrimary();
    }

    @Override // com.byd.dilink.statusbar.IDiLinkStatusBarManager
    public boolean isRunningTaskSupportsMultiWindow() {
        return this.d.isRunningTaskSupportsMultiWindow();
    }

    @Override // com.byd.dilink.statusbar.IDiLinkStatusBarManager
    public boolean isSwapSplitScreenUsable() {
        return this.d.isSwapSplitScreenUsable();
    }

    @Override // com.byd.dilink.statusbar.IDiLinkStatusBarManager
    public boolean moveAppToFullScreenByPkg(String str) {
        return this.d.moveAppToFullScreenByPkg(str);
    }

    @Override // com.byd.dilink.statusbar.IDiLinkStatusBarManager
    public void notifyDockedStackExistsChanged(boolean z) {
        this.d.notifyDockedStackExistsChanged(z);
    }

    @Override // com.byd.dilink.statusbar.IDiLinkStatusBarManager
    public void onSteeringWheelRotationClick() {
        this.d.onSteeringWheelRotationClick();
    }

    @Override // com.byd.dilink.statusbar.IDiLinkStatusBarManager
    public void registerCardListener(IDiLinkCardListener iDiLinkCardListener) {
        this.f1465c.addCallbackIgnoreRemoteException(iDiLinkCardListener, new IDiLinkCallbackHandler() { // from class: com.byd.dilink50.statusbar.b
            @Override // com.byd.dilink.callbackholder.IDiLinkCallbackHandler
            public final void onResult(Object obj) {
                this.f1473a.a((ICardListener) obj);
            }
        });
    }

    @Override // com.byd.dilink.statusbar.IDiLinkStatusBarManager
    public void registerSceneModeCallback(IDiLinkSceneModeStateCallback iDiLinkSceneModeStateCallback) {
        this.f1464b.addCallbackIgnoreRemoteException(iDiLinkSceneModeStateCallback, new IDiLinkCallbackHandler() { // from class: com.byd.dilink50.statusbar.f
            @Override // com.byd.dilink.callbackholder.IDiLinkCallbackHandler
            public final void onResult(Object obj) {
                this.f1477a.b((ISceneModeStateCallback) obj);
            }
        });
    }

    @Override // com.byd.dilink.statusbar.IDiLinkStatusBarManager
    public void registerSplitScreenCallBack(IDiLinkSplitScreenCallBack iDiLinkSplitScreenCallBack) {
        this.f1463a.addCallbackIgnoreRemoteException(iDiLinkSplitScreenCallBack, new IDiLinkCallbackHandler() { // from class: com.byd.dilink50.statusbar.a
            @Override // com.byd.dilink.callbackholder.IDiLinkCallbackHandler
            public final void onResult(Object obj) {
                this.f1472a.c((ISplitScreenCallBack) obj);
            }
        });
    }

    @Override // com.byd.dilink.statusbar.IDiLinkStatusBarManager
    public void setActivityNameForStatusAndNavigationBar(String str) {
        this.d.setActivityNameForStatusAndNavigaitonBar(str);
    }

    @Override // com.byd.dilink.statusbar.IDiLinkStatusBarManager
    public void setActivityNameForStatusAndNavigationBar(@NonNull String str, int i) {
        throw new UnsupportedTargetPlatformException("Di5.0 没有 setActivityNameForStatusAndNavigationBar方法！");
    }

    @Override // com.byd.dilink.statusbar.IDiLinkStatusBarManager
    public void setMultiWindow() {
        throw new UnsupportedTargetPlatformException("Di5.0 没有 setMultiWindow 这个函数！");
    }

    @Override // com.byd.dilink.statusbar.IDiLinkStatusBarManager
    public void setNewFocusStatusForDivider() {
        this.d.setNewFocusStatusForDvider();
    }

    @Override // com.byd.dilink.statusbar.IDiLinkStatusBarManager
    public void setPackageNameForStatusAndNavigationBar(String str) {
        this.d.setPackageNameForStatusAndNaviationBar(str);
    }

    @Override // com.byd.dilink.statusbar.IDiLinkStatusBarManager
    public void setSceneMode(int i, int i2, int i3) {
        this.d.setSceneMode(i, i2, i3);
    }

    @Override // com.byd.dilink.statusbar.IDiLinkStatusBarManager
    public void setSceneModeCallbackContent(String str) {
        this.d.setSceneModeCallbackContent(str);
    }

    @Override // com.byd.dilink.statusbar.IDiLinkStatusBarManager
    public void setSplitScreenMode(boolean z) {
        this.d.setSplitScreenMode(z);
    }

    @Override // com.byd.dilink.statusbar.IDiLinkStatusBarManager
    public int startActivityWhenMultiWindow(String str, Intent intent, int i) {
        return this.d.startActivityWhenMultiWindow(str, intent, i);
    }

    @Override // com.byd.dilink.statusbar.IDiLinkStatusBarManager
    public void swapSplitScreenWindow() {
        this.d.swapSplitScreenWindow();
    }

    @Override // com.byd.dilink.statusbar.IDiLinkStatusBarManager
    public void toggleSplitScreen() {
        this.d.toggleSplitScreen();
    }

    @Override // com.byd.dilink.statusbar.IDiLinkStatusBarManager
    public void unregisterCardListener(IDiLinkCardListener iDiLinkCardListener) {
        this.f1465c.removeCallbackIgnoreRemoteException(iDiLinkCardListener, new IDiLinkCallbackHandler() { // from class: com.byd.dilink50.statusbar.e
            @Override // com.byd.dilink.callbackholder.IDiLinkCallbackHandler
            public final void onResult(Object obj) {
                this.f1476a.d((ICardListener) obj);
            }
        });
    }

    @Override // com.byd.dilink.statusbar.IDiLinkStatusBarManager
    public void unregisterSceneModeCallback(IDiLinkSceneModeStateCallback iDiLinkSceneModeStateCallback) {
        this.f1464b.removeCallbackIgnoreRemoteException(iDiLinkSceneModeStateCallback, new IDiLinkCallbackHandler() { // from class: com.byd.dilink50.statusbar.c
            @Override // com.byd.dilink.callbackholder.IDiLinkCallbackHandler
            public final void onResult(Object obj) {
                this.f1474a.e((ISceneModeStateCallback) obj);
            }
        });
    }

    @Override // com.byd.dilink.statusbar.IDiLinkStatusBarManager
    public void unregisterSplitScreenCallBack(IDiLinkSplitScreenCallBack iDiLinkSplitScreenCallBack) {
        this.f1463a.removeCallbackIgnoreRemoteException(iDiLinkSplitScreenCallBack, new IDiLinkCallbackHandler() { // from class: com.byd.dilink50.statusbar.d
            @Override // com.byd.dilink.callbackholder.IDiLinkCallbackHandler
            public final void onResult(Object obj) {
                this.f1475a.f((ISplitScreenCallBack) obj);
            }
        });
    }

    @Override // com.byd.dilink.statusbar.IDiLinkStatusBarManager
    public void userActivityNoUpdateLocked() {
        this.d.userActivityNoUpdateLocked();
    }
}
