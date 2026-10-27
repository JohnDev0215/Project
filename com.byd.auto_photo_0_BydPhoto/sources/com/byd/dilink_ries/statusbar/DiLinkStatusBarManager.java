package com.byd.dilink_ries.statusbar;

import android.annotation.SuppressLint;
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
@Platform(versions = {DiLink.VERSION_NAME_RIES, DiLink.VERSION_NAME_RIES_5_0})
@ServiceImpl(service = IDiLinkStatusBarManager.class, singleton = true)
public class DiLinkStatusBarManager implements IDiLinkStatusBarManager {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private DiLink_ries_SplitScreenCallBacks f2652a = new DiLink_ries_SplitScreenCallBacks();

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private DiLink_ries_SceneModeStateCallbacks f2653b = new DiLink_ries_SceneModeStateCallbacks();

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private StatusBarManager f2654c;

    public static class DiLink_ries_SceneModeStateCallbacks extends DiLinkCallbackHolders<IDiLinkSceneModeStateCallback, ISceneModeStateCallback> {
        @Override // com.byd.dilink.callbackholder.DiLinkCallbackHolders
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public ISceneModeStateCallback createInnerCallback(final IDiLinkSceneModeStateCallback iDiLinkSceneModeStateCallback) {
            return new ISceneModeStateCallback.Stub() { // from class: com.byd.dilink_ries.statusbar.DiLinkStatusBarManager.DiLink_ries_SceneModeStateCallbacks.1
                @Keep
                public void onContent(String str) {
                    iDiLinkSceneModeStateCallback.onContent(str);
                }
            };
        }
    }

    public static class DiLink_ries_SplitScreenCallBacks extends DiLinkCallbackHolders<IDiLinkSplitScreenCallBack, ISplitScreenCallBack> {
        @Override // com.byd.dilink.callbackholder.DiLinkCallbackHolders
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public ISplitScreenCallBack createInnerCallback(final IDiLinkSplitScreenCallBack iDiLinkSplitScreenCallBack) {
            return new ISplitScreenCallBack.Stub() { // from class: com.byd.dilink_ries.statusbar.DiLinkStatusBarManager.DiLink_ries_SplitScreenCallBacks.1
                @Keep
                public void onDockedStackExistsChanged(boolean z) {
                    iDiLinkSplitScreenCallBack.onDockedStackExistsChanged(z);
                }
            };
        }
    }

    @SuppressLint({"WrongConstant"})
    public DiLinkStatusBarManager(Context context) {
        this.f2654c = (StatusBarManager) context.getSystemService("statusbar");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void a(ISceneModeStateCallback iSceneModeStateCallback) {
        this.f2654c.unregisterSceneModeCallback(iSceneModeStateCallback);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void b(ISplitScreenCallBack iSplitScreenCallBack) {
        this.f2654c.unregisterSplitScreenCallBack(iSplitScreenCallBack);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void g(ISceneModeStateCallback iSceneModeStateCallback) {
        this.f2654c.registerSceneModeCallback(iSceneModeStateCallback);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void h(ISplitScreenCallBack iSplitScreenCallBack) {
        this.f2654c.registerSplitScreenCallBack(iSplitScreenCallBack);
    }

    @Override // com.byd.dilink.statusbar.IDiLinkStatusBarManager
    public boolean closeAppWhenMultiWindow(String str, int i) {
        return this.f2654c.closeAppWhenMultiWindow(str, i);
    }

    @Override // com.byd.dilink.statusbar.IDiLinkStatusBarManager
    public void collapsePanels() {
        this.f2654c.collapsePanels();
    }

    @Override // com.byd.dilink.statusbar.IDiLinkStatusBarManager
    public void enterSplitScreenModeByLauncher(String str, Intent intent) {
        throw new UnsupportedTargetPlatformException("ries 没有 enterSplitScreenModeByLauncher 这个函数！");
    }

    @Override // com.byd.dilink.statusbar.IDiLinkStatusBarManager
    public void enterSplitScreenModeByLauncher(String str, String str2) {
        throw new UnsupportedTargetPlatformException("ries 没有 enterSplitScreenModeByLauncher 这个函数！");
    }

    @Override // com.byd.dilink.statusbar.IDiLinkStatusBarManager
    public String getAppPkgByDirection(boolean z) {
        return this.f2654c.getAppPkgByDirection(z);
    }

    @Override // com.byd.dilink.statusbar.IDiLinkStatusBarManager
    public int getSplitScreenStatus() {
        return this.f2654c.getSplitScreenStatus();
    }

    @Override // com.byd.dilink.statusbar.IDiLinkStatusBarManager
    public void handleHomeButton() {
        throw new UnsupportedTargetPlatformException("ries 没有 handleHomeButton 这个函数！");
    }

    @Override // com.byd.dilink.statusbar.IDiLinkStatusBarManager
    public int isAppSupportSplit(String str) {
        throw new UnsupportedTargetPlatformException("ries 没有 isAppSupportSplit 这个函数！");
    }

    @Override // com.byd.dilink.statusbar.IDiLinkStatusBarManager
    public boolean isDockState() {
        return this.f2654c.isDockState();
    }

    @Override // com.byd.dilink.statusbar.IDiLinkStatusBarManager
    public boolean isFocusInPrimary() {
        return this.f2654c.isFocusInPrimary();
    }

    @Override // com.byd.dilink.statusbar.IDiLinkStatusBarManager
    public boolean isRunningTaskSupportsMultiWindow() {
        return this.f2654c.isRunningTaskSupportsMultiWindow();
    }

    @Override // com.byd.dilink.statusbar.IDiLinkStatusBarManager
    public boolean isSwapSplitScreenUsable() {
        return this.f2654c.isSwapSplitScreenUsable();
    }

    @Override // com.byd.dilink.statusbar.IDiLinkStatusBarManager
    public boolean moveAppToFullScreenByPkg(String str) {
        return this.f2654c.moveAppToFullScreenByPkg(str);
    }

    @Override // com.byd.dilink.statusbar.IDiLinkStatusBarManager
    public void notifyDockedStackExistsChanged(boolean z) {
        this.f2654c.notifyDockedStackExistsChanged(z);
    }

    @Override // com.byd.dilink.statusbar.IDiLinkStatusBarManager
    public void onSteeringWheelRotationClick() {
        throw new UnsupportedTargetPlatformException("ries 没有 onSteeringWheelRotationClick 这个函数！");
    }

    @Override // com.byd.dilink.statusbar.IDiLinkStatusBarManager
    public void registerCardListener(IDiLinkCardListener iDiLinkCardListener) {
        throw new UnsupportedTargetPlatformException("ries 没有 registerCardListener 这个函数！");
    }

    @Override // com.byd.dilink.statusbar.IDiLinkStatusBarManager
    public void registerSceneModeCallback(IDiLinkSceneModeStateCallback iDiLinkSceneModeStateCallback) {
        this.f2653b.addCallbackIgnoreRemoteException(iDiLinkSceneModeStateCallback, new IDiLinkCallbackHandler() { // from class: com.byd.dilink_ries.statusbar.c
            @Override // com.byd.dilink.callbackholder.IDiLinkCallbackHandler
            public final void onResult(Object obj) {
                this.f2661a.g((ISceneModeStateCallback) obj);
            }
        });
    }

    @Override // com.byd.dilink.statusbar.IDiLinkStatusBarManager
    public void registerSplitScreenCallBack(IDiLinkSplitScreenCallBack iDiLinkSplitScreenCallBack) {
        this.f2652a.addCallbackIgnoreRemoteException(iDiLinkSplitScreenCallBack, new IDiLinkCallbackHandler() { // from class: com.byd.dilink_ries.statusbar.a
            @Override // com.byd.dilink.callbackholder.IDiLinkCallbackHandler
            public final void onResult(Object obj) {
                this.f2659a.h((ISplitScreenCallBack) obj);
            }
        });
    }

    @Override // com.byd.dilink.statusbar.IDiLinkStatusBarManager
    public void setActivityNameForStatusAndNavigationBar(String str) {
        throw new UnsupportedTargetPlatformException("ries 没有 setActivityNameForStatusAndNavigationBar(@NonNull String name) 这个函数！");
    }

    @Override // com.byd.dilink.statusbar.IDiLinkStatusBarManager
    public void setActivityNameForStatusAndNavigationBar(@NonNull String str, int i) {
        throw new UnsupportedTargetPlatformException("ries 没有 setActivityNameForStatusAndNavigationBar方法！");
    }

    @Override // com.byd.dilink.statusbar.IDiLinkStatusBarManager
    public void setMultiWindow() {
        throw new UnsupportedTargetPlatformException("ries 没有 setMultiWindow 这个函数！");
    }

    @Override // com.byd.dilink.statusbar.IDiLinkStatusBarManager
    public void setNewFocusStatusForDivider() {
        throw new UnsupportedTargetPlatformException("ries 没有 setNewFocusStatusForDivider 这个函数！");
    }

    @Override // com.byd.dilink.statusbar.IDiLinkStatusBarManager
    public void setPackageNameForStatusAndNavigationBar(String str) {
        this.f2654c.setPackageNameForStatusAndNaviationBar(str);
    }

    @Override // com.byd.dilink.statusbar.IDiLinkStatusBarManager
    public void setSceneMode(int i, int i2, int i3) {
        this.f2654c.setSceneMode(i, i2, i3);
    }

    @Override // com.byd.dilink.statusbar.IDiLinkStatusBarManager
    public void setSceneModeCallbackContent(String str) {
        this.f2654c.setSceneModeCallbackContent(str);
    }

    @Override // com.byd.dilink.statusbar.IDiLinkStatusBarManager
    public void setSplitScreenMode(boolean z) {
        this.f2654c.setSplitScreenMode(z);
    }

    @Override // com.byd.dilink.statusbar.IDiLinkStatusBarManager
    public int startActivityWhenMultiWindow(String str, Intent intent, int i) {
        return this.f2654c.startActivityWhenMultiWindow(str, intent, i);
    }

    @Override // com.byd.dilink.statusbar.IDiLinkStatusBarManager
    public void swapSplitScreenWindow() {
        this.f2654c.swapSplitScreenWindow();
    }

    @Override // com.byd.dilink.statusbar.IDiLinkStatusBarManager
    public void toggleSplitScreen() {
        throw new UnsupportedTargetPlatformException("ries 没有 moveAppToFullScreenByPkg 这个函数！");
    }

    @Override // com.byd.dilink.statusbar.IDiLinkStatusBarManager
    public void unregisterCardListener(IDiLinkCardListener iDiLinkCardListener) {
        throw new UnsupportedTargetPlatformException("ries 没有 unregisterCardListener 这个函数！");
    }

    @Override // com.byd.dilink.statusbar.IDiLinkStatusBarManager
    public void unregisterSceneModeCallback(IDiLinkSceneModeStateCallback iDiLinkSceneModeStateCallback) {
        this.f2653b.removeCallbackIgnoreRemoteException(iDiLinkSceneModeStateCallback, new IDiLinkCallbackHandler() { // from class: com.byd.dilink_ries.statusbar.d
            @Override // com.byd.dilink.callbackholder.IDiLinkCallbackHandler
            public final void onResult(Object obj) {
                this.f2662a.a((ISceneModeStateCallback) obj);
            }
        });
    }

    @Override // com.byd.dilink.statusbar.IDiLinkStatusBarManager
    public void unregisterSplitScreenCallBack(IDiLinkSplitScreenCallBack iDiLinkSplitScreenCallBack) {
        this.f2652a.removeCallbackIgnoreRemoteException(iDiLinkSplitScreenCallBack, new IDiLinkCallbackHandler() { // from class: com.byd.dilink_ries.statusbar.b
            @Override // com.byd.dilink.callbackholder.IDiLinkCallbackHandler
            public final void onResult(Object obj) {
                this.f2660a.b((ISplitScreenCallBack) obj);
            }
        });
    }

    @Override // com.byd.dilink.statusbar.IDiLinkStatusBarManager
    public void userActivityNoUpdateLocked() {
        this.f2654c.userActivityNoUpdateLocked();
    }
}
