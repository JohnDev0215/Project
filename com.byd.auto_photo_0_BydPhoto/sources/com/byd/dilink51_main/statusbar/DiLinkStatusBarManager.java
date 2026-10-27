package com.byd.dilink51_main.statusbar;

import android.annotation.SuppressLint;
import android.app.ISceneModeStateCallback;
import android.app.ISplitScreenCallBack;
import android.app.StatusBarManager;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
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
@Platform(versions = {DiLink.VERSION_NAME_5_1})
@ServiceImpl(service = IDiLinkStatusBarManager.class, singleton = true)
public class DiLinkStatusBarManager implements IDiLinkStatusBarManager {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private DiLink5_0_SplitScreenCallBacks f1890a = new DiLink5_0_SplitScreenCallBacks();

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private DiLink5_0_SceneModeStateCallbacks f1891b = new DiLink5_0_SceneModeStateCallbacks();

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private StatusBarManager f1892c;

    public static class DiLink5_0_SceneModeStateCallbacks extends DiLinkCallbackHolders<IDiLinkSceneModeStateCallback, ISceneModeStateCallback> {
        @Override // com.byd.dilink.callbackholder.DiLinkCallbackHolders
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public ISceneModeStateCallback createInnerCallback(final IDiLinkSceneModeStateCallback iDiLinkSceneModeStateCallback) {
            return new ISceneModeStateCallback.Stub() { // from class: com.byd.dilink51_main.statusbar.DiLinkStatusBarManager.DiLink5_0_SceneModeStateCallbacks.1
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
            return new ISplitScreenCallBack.Stub() { // from class: com.byd.dilink51_main.statusbar.DiLinkStatusBarManager.DiLink5_0_SplitScreenCallBacks.1
                @Keep
                public void onDockedStackExistsChanged(boolean z) {
                    iDiLinkSplitScreenCallBack.onDockedStackExistsChanged(z);
                }
            };
        }
    }

    @SuppressLint({"WrongConstant"})
    public DiLinkStatusBarManager(Context context) {
        Log.d("DiLinkStatusBarManager", "DiLinkStatusBarManager: ");
        this.f1892c = (StatusBarManager) context.getSystemService("statusbar");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void a(ISceneModeStateCallback iSceneModeStateCallback) {
        this.f1892c.registerSceneModeCallback(iSceneModeStateCallback);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void b(ISplitScreenCallBack iSplitScreenCallBack) {
        this.f1892c.registerSplitScreenCallBack(iSplitScreenCallBack);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void c(ISceneModeStateCallback iSceneModeStateCallback) {
        this.f1892c.unregisterSceneModeCallback(iSceneModeStateCallback);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void d(ISplitScreenCallBack iSplitScreenCallBack) {
        this.f1892c.unregisterSplitScreenCallBack(iSplitScreenCallBack);
    }

    @Override // com.byd.dilink.statusbar.IDiLinkStatusBarManager
    public boolean closeAppWhenMultiWindow(@NonNull String str, int i) {
        return this.f1892c.closeAppWhenMultiWindow(str, i);
    }

    @Override // com.byd.dilink.statusbar.IDiLinkStatusBarManager
    public void collapsePanels() {
        this.f1892c.collapsePanels();
    }

    @Override // com.byd.dilink.statusbar.IDiLinkStatusBarManager
    public void enterSplitScreenModeByLauncher(String str, Intent intent) {
        this.f1892c.enterSplitScreenModeByLaucher(str, intent);
    }

    @Override // com.byd.dilink.statusbar.IDiLinkStatusBarManager
    public void enterSplitScreenModeByLauncher(String str, String str2) {
        this.f1892c.enterSplitScreenModeByLaucher(str, str2);
    }

    @Override // com.byd.dilink.statusbar.IDiLinkStatusBarManager
    public String getAppPkgByDirection(boolean z) {
        return this.f1892c.getAppPkgByDirection(z);
    }

    @Override // com.byd.dilink.statusbar.IDiLinkStatusBarManager
    public int getSplitScreenStatus() {
        return this.f1892c.getSplitScreenStatus();
    }

    @Override // com.byd.dilink.statusbar.IDiLinkStatusBarManager
    public void handleHomeButton() {
        throw new UnsupportedTargetPlatformException("Di5.1 没有 handleHomeButton 这个函数！");
    }

    @Override // com.byd.dilink.statusbar.IDiLinkStatusBarManager
    public int isAppSupportSplit(@NonNull String str) {
        return this.f1892c.isAppSuportSplit(str);
    }

    @Override // com.byd.dilink.statusbar.IDiLinkStatusBarManager
    public boolean isDockState() {
        return this.f1892c.isDockState();
    }

    @Override // com.byd.dilink.statusbar.IDiLinkStatusBarManager
    public boolean isFocusInPrimary() {
        return this.f1892c.isFocusInPrimary();
    }

    @Override // com.byd.dilink.statusbar.IDiLinkStatusBarManager
    public boolean isRunningTaskSupportsMultiWindow() {
        return this.f1892c.isRunningTaskSupportsMultiWindow();
    }

    @Override // com.byd.dilink.statusbar.IDiLinkStatusBarManager
    public boolean isSwapSplitScreenUsable() {
        return this.f1892c.isSwapSplitScreenUsable();
    }

    @Override // com.byd.dilink.statusbar.IDiLinkStatusBarManager
    public boolean moveAppToFullScreenByPkg(@NonNull String str) {
        return this.f1892c.moveAppToFullScreenByPkg(str);
    }

    @Override // com.byd.dilink.statusbar.IDiLinkStatusBarManager
    public void notifyDockedStackExistsChanged(boolean z) {
        this.f1892c.notifyDockedStackExistsChanged(z);
    }

    @Override // com.byd.dilink.statusbar.IDiLinkStatusBarManager
    public void onSteeringWheelRotationClick() {
        throw new UnsupportedTargetPlatformException("Di5.1 没有 onSteeringWheelRotationClick 这个函数！");
    }

    @Override // com.byd.dilink.statusbar.IDiLinkStatusBarManager
    public void registerCardListener(IDiLinkCardListener iDiLinkCardListener) {
        throw new UnsupportedTargetPlatformException("Di5.1 没有 registerCardListener 这个函数！");
    }

    @Override // com.byd.dilink.statusbar.IDiLinkStatusBarManager
    public void registerSceneModeCallback(@NonNull IDiLinkSceneModeStateCallback iDiLinkSceneModeStateCallback) {
        this.f1891b.addCallbackIgnoreRemoteException(iDiLinkSceneModeStateCallback, new IDiLinkCallbackHandler() { // from class: com.byd.dilink51_main.statusbar.d
            @Override // com.byd.dilink.callbackholder.IDiLinkCallbackHandler
            public final void onResult(Object obj) {
                this.f1900a.a((ISceneModeStateCallback) obj);
            }
        });
    }

    @Override // com.byd.dilink.statusbar.IDiLinkStatusBarManager
    public void registerSplitScreenCallBack(@NonNull IDiLinkSplitScreenCallBack iDiLinkSplitScreenCallBack) {
        this.f1890a.addCallbackIgnoreRemoteException(iDiLinkSplitScreenCallBack, new IDiLinkCallbackHandler() { // from class: com.byd.dilink51_main.statusbar.a
            @Override // com.byd.dilink.callbackholder.IDiLinkCallbackHandler
            public final void onResult(Object obj) {
                this.f1897a.b((ISplitScreenCallBack) obj);
            }
        });
    }

    @Override // com.byd.dilink.statusbar.IDiLinkStatusBarManager
    public void setActivityNameForStatusAndNavigationBar(@NonNull String str) {
        throw new UnsupportedTargetPlatformException("Di5.1 没有 setActivityNameForStatusAndNavigationBar(@NonNull String name)方法！");
    }

    @Override // com.byd.dilink.statusbar.IDiLinkStatusBarManager
    public void setActivityNameForStatusAndNavigationBar(@NonNull String str, int i) {
        this.f1892c.setActivityNameForStatusAndNavigaitonBar(str, i);
    }

    @Override // com.byd.dilink.statusbar.IDiLinkStatusBarManager
    public void setMultiWindow() {
        this.f1892c.setMulitWindow();
    }

    @Override // com.byd.dilink.statusbar.IDiLinkStatusBarManager
    public void setNewFocusStatusForDivider() {
        this.f1892c.setNewFocusStatusForDvider();
    }

    @Override // com.byd.dilink.statusbar.IDiLinkStatusBarManager
    public void setPackageNameForStatusAndNavigationBar(@NonNull String str) {
        this.f1892c.setPackageNameForStatusAndNaviationBar(str);
    }

    @Override // com.byd.dilink.statusbar.IDiLinkStatusBarManager
    public void setSceneMode(int i, int i2, int i3) {
        this.f1892c.setSceneMode(i, i2, i3);
    }

    @Override // com.byd.dilink.statusbar.IDiLinkStatusBarManager
    public void setSceneModeCallbackContent(String str) {
        this.f1892c.setSceneModeCallbackContent(str);
    }

    @Override // com.byd.dilink.statusbar.IDiLinkStatusBarManager
    public void setSplitScreenMode(boolean z) {
        this.f1892c.setSplitScreenMode(z);
    }

    @Override // com.byd.dilink.statusbar.IDiLinkStatusBarManager
    public int startActivityWhenMultiWindow(@NonNull String str, @NonNull Intent intent, int i) {
        return this.f1892c.startActivityWhenMultiWindow(str, intent, i);
    }

    @Override // com.byd.dilink.statusbar.IDiLinkStatusBarManager
    public void swapSplitScreenWindow() {
        this.f1892c.swapSplitScreenWindow();
    }

    @Override // com.byd.dilink.statusbar.IDiLinkStatusBarManager
    public void toggleSplitScreen() {
        throw new UnsupportedTargetPlatformException("Di5.1 没有 toggleSplitScreen 这个函数！");
    }

    @Override // com.byd.dilink.statusbar.IDiLinkStatusBarManager
    public void unregisterCardListener(IDiLinkCardListener iDiLinkCardListener) {
        throw new UnsupportedTargetPlatformException("Di5.1 没有 unregisterCardListener 这个函数！");
    }

    @Override // com.byd.dilink.statusbar.IDiLinkStatusBarManager
    public void unregisterSceneModeCallback(@NonNull IDiLinkSceneModeStateCallback iDiLinkSceneModeStateCallback) {
        this.f1891b.removeCallbackIgnoreRemoteException(iDiLinkSceneModeStateCallback, new IDiLinkCallbackHandler() { // from class: com.byd.dilink51_main.statusbar.c
            @Override // com.byd.dilink.callbackholder.IDiLinkCallbackHandler
            public final void onResult(Object obj) {
                this.f1899a.c((ISceneModeStateCallback) obj);
            }
        });
    }

    @Override // com.byd.dilink.statusbar.IDiLinkStatusBarManager
    public void unregisterSplitScreenCallBack(@NonNull IDiLinkSplitScreenCallBack iDiLinkSplitScreenCallBack) {
        this.f1890a.removeCallbackIgnoreRemoteException(iDiLinkSplitScreenCallBack, new IDiLinkCallbackHandler() { // from class: com.byd.dilink51_main.statusbar.b
            @Override // com.byd.dilink.callbackholder.IDiLinkCallbackHandler
            public final void onResult(Object obj) {
                this.f1898a.d((ISplitScreenCallBack) obj);
            }
        });
    }

    @Override // com.byd.dilink.statusbar.IDiLinkStatusBarManager
    public void userActivityNoUpdateLocked() {
        this.f1892c.userActivityNoUpdateLocked();
    }
}
