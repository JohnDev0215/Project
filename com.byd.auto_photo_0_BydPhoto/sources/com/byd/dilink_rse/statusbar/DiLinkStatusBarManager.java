package com.byd.dilink_rse.statusbar;

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

/* JADX INFO: loaded from: classes2.dex */
@Platform(versions = {DiLink.VERSION_NAME_RSE_5_0, DiLink.VERSION_NAME_RSE_RUI})
@ServiceImpl(service = IDiLinkStatusBarManager.class, singleton = true)
public class DiLinkStatusBarManager implements IDiLinkStatusBarManager {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private DiLink_rse_SplitScreenCallBacks f2780a = new DiLink_rse_SplitScreenCallBacks();

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private DiLink_rse_SceneModeStateCallbacks f2781b = new DiLink_rse_SceneModeStateCallbacks();

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private StatusBarManager f2782c;

    public static class DiLink_rse_SceneModeStateCallbacks extends DiLinkCallbackHolders<IDiLinkSceneModeStateCallback, ISceneModeStateCallback> {

        public class a extends ISceneModeStateCallback.Stub {

            /* JADX INFO: renamed from: a, reason: collision with root package name */
            public final /* synthetic */ IDiLinkSceneModeStateCallback f2783a;

            public a(IDiLinkSceneModeStateCallback iDiLinkSceneModeStateCallback) {
                this.f2783a = iDiLinkSceneModeStateCallback;
            }

            public void onContent(String str) {
                this.f2783a.onContent(str);
            }
        }

        @Override // com.byd.dilink.callbackholder.DiLinkCallbackHolders
        @Keep
        public ISceneModeStateCallback createInnerCallback(IDiLinkSceneModeStateCallback iDiLinkSceneModeStateCallback) {
            return new a(iDiLinkSceneModeStateCallback);
        }
    }

    public static class DiLink_rse_SplitScreenCallBacks extends DiLinkCallbackHolders<IDiLinkSplitScreenCallBack, ISplitScreenCallBack> {
        @Override // com.byd.dilink.callbackholder.DiLinkCallbackHolders
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public ISplitScreenCallBack createInnerCallback(final IDiLinkSplitScreenCallBack iDiLinkSplitScreenCallBack) {
            return new ISplitScreenCallBack.Stub() { // from class: com.byd.dilink_rse.statusbar.DiLinkStatusBarManager.DiLink_rse_SplitScreenCallBacks.1
                @Keep
                public void onDockedStackExistsChanged(boolean z) {
                    iDiLinkSplitScreenCallBack.onDockedStackExistsChanged(z);
                }
            };
        }
    }

    @SuppressLint({"WrongConstant"})
    public DiLinkStatusBarManager(Context context) {
        this.f2782c = (StatusBarManager) context.getSystemService("statusbar");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void a(ISceneModeStateCallback iSceneModeStateCallback) {
        this.f2782c.registerSceneModeCallback(iSceneModeStateCallback);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void b(ISplitScreenCallBack iSplitScreenCallBack) {
        this.f2782c.registerSplitScreenCallBack(iSplitScreenCallBack);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void g(ISceneModeStateCallback iSceneModeStateCallback) {
        this.f2782c.unregisterSceneModeCallback(iSceneModeStateCallback);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public /* synthetic */ void h(ISplitScreenCallBack iSplitScreenCallBack) {
        this.f2782c.unregisterSplitScreenCallBack(iSplitScreenCallBack);
    }

    @Override // com.byd.dilink.statusbar.IDiLinkStatusBarManager
    public boolean closeAppWhenMultiWindow(String str, int i) {
        throw new UnsupportedTargetPlatformException("RSE 没有 closeAppWhenMultiWindow 这个函数！");
    }

    @Override // com.byd.dilink.statusbar.IDiLinkStatusBarManager
    public void collapsePanels() {
        this.f2782c.collapsePanels();
    }

    @Override // com.byd.dilink.statusbar.IDiLinkStatusBarManager
    public void enterSplitScreenModeByLauncher(String str, Intent intent) {
        throw new UnsupportedTargetPlatformException("RSE 没有 enterSplitScreenModeByLauncher 这个函数！");
    }

    @Override // com.byd.dilink.statusbar.IDiLinkStatusBarManager
    public void enterSplitScreenModeByLauncher(String str, String str2) {
        throw new UnsupportedTargetPlatformException("RSE 没有 enterSplitScreenModeByLauncher 这个函数！");
    }

    @Override // com.byd.dilink.statusbar.IDiLinkStatusBarManager
    public String getAppPkgByDirection(boolean z) {
        throw new UnsupportedTargetPlatformException("RSE 没有 getAppPkgByDirection 这个函数！");
    }

    @Override // com.byd.dilink.statusbar.IDiLinkStatusBarManager
    public int getSplitScreenStatus() {
        throw new UnsupportedTargetPlatformException("RSE 没有 getSplitScreenStatus 这个函数！");
    }

    @Override // com.byd.dilink.statusbar.IDiLinkStatusBarManager
    public void handleHomeButton() {
        this.f2782c.handleHomeButton();
    }

    @Override // com.byd.dilink.statusbar.IDiLinkStatusBarManager
    public int isAppSupportSplit(String str) {
        throw new UnsupportedTargetPlatformException("RSE 没有 isAppSupportSplit 这个函数！");
    }

    @Override // com.byd.dilink.statusbar.IDiLinkStatusBarManager
    public boolean isDockState() {
        return this.f2782c.isDockState();
    }

    @Override // com.byd.dilink.statusbar.IDiLinkStatusBarManager
    public boolean isFocusInPrimary() {
        throw new UnsupportedTargetPlatformException("RSE 没有 isFocusInPrimary 这个函数！");
    }

    @Override // com.byd.dilink.statusbar.IDiLinkStatusBarManager
    public boolean isRunningTaskSupportsMultiWindow() {
        throw new UnsupportedTargetPlatformException("RSE 没有 isRunningTaskSupportsMultiWindow 这个函数！");
    }

    @Override // com.byd.dilink.statusbar.IDiLinkStatusBarManager
    public boolean isSwapSplitScreenUsable() {
        throw new UnsupportedTargetPlatformException("RSE 没有 isSwapSplitScreenUsable 这个函数！");
    }

    @Override // com.byd.dilink.statusbar.IDiLinkStatusBarManager
    public boolean moveAppToFullScreenByPkg(String str) {
        throw new UnsupportedTargetPlatformException("RSE 没有 moveAppToFullScreenByPkg 这个函数！");
    }

    @Override // com.byd.dilink.statusbar.IDiLinkStatusBarManager
    public void notifyDockedStackExistsChanged(boolean z) {
        throw new UnsupportedTargetPlatformException("RSE 没有 notifyDockedStackExistsChanged 这个函数！");
    }

    @Override // com.byd.dilink.statusbar.IDiLinkStatusBarManager
    public void onSteeringWheelRotationClick() {
        this.f2782c.onSteeringWheelRotationClick();
    }

    @Override // com.byd.dilink.statusbar.IDiLinkStatusBarManager
    public void registerCardListener(IDiLinkCardListener iDiLinkCardListener) {
        throw new UnsupportedTargetPlatformException("RSE 没有 registerCardListener 这个函数！");
    }

    @Override // com.byd.dilink.statusbar.IDiLinkStatusBarManager
    public void registerSceneModeCallback(IDiLinkSceneModeStateCallback iDiLinkSceneModeStateCallback) {
        this.f2781b.addCallbackIgnoreRemoteException(iDiLinkSceneModeStateCallback, new IDiLinkCallbackHandler() { // from class: com.byd.dilink_rse.statusbar.a
            @Override // com.byd.dilink.callbackholder.IDiLinkCallbackHandler
            public final void onResult(Object obj) {
                this.f2787a.a((ISceneModeStateCallback) obj);
            }
        });
    }

    @Override // com.byd.dilink.statusbar.IDiLinkStatusBarManager
    public void registerSplitScreenCallBack(IDiLinkSplitScreenCallBack iDiLinkSplitScreenCallBack) {
        this.f2780a.addCallbackIgnoreRemoteException(iDiLinkSplitScreenCallBack, new IDiLinkCallbackHandler() { // from class: com.byd.dilink_rse.statusbar.c
            @Override // com.byd.dilink.callbackholder.IDiLinkCallbackHandler
            public final void onResult(Object obj) {
                this.f2789a.b((ISplitScreenCallBack) obj);
            }
        });
    }

    @Override // com.byd.dilink.statusbar.IDiLinkStatusBarManager
    public void setActivityNameForStatusAndNavigationBar(String str) {
        this.f2782c.setActivityNameForStatusAndNavigaitonBar(str);
    }

    @Override // com.byd.dilink.statusbar.IDiLinkStatusBarManager
    public void setActivityNameForStatusAndNavigationBar(@NonNull String str, int i) {
        throw new UnsupportedTargetPlatformException("RSE 没有 setActivityNameForStatusAndNavigationBar方法！");
    }

    @Override // com.byd.dilink.statusbar.IDiLinkStatusBarManager
    public void setMultiWindow() {
        throw new UnsupportedTargetPlatformException("RSE 没有 setMultiWindow 这个函数！");
    }

    @Override // com.byd.dilink.statusbar.IDiLinkStatusBarManager
    public void setNewFocusStatusForDivider() {
        throw new UnsupportedTargetPlatformException("RSE 没有 setNewFocusStatusForDivider 这个函数！");
    }

    @Override // com.byd.dilink.statusbar.IDiLinkStatusBarManager
    public void setPackageNameForStatusAndNavigationBar(String str) {
        this.f2782c.setPackageNameForStatusAndNaviationBar(str);
    }

    @Override // com.byd.dilink.statusbar.IDiLinkStatusBarManager
    public void setSceneMode(int i, int i2, int i3) {
        this.f2782c.setSceneMode(i, i2, i3);
    }

    @Override // com.byd.dilink.statusbar.IDiLinkStatusBarManager
    public void setSceneModeCallbackContent(String str) {
        this.f2782c.setSceneModeCallbackContent(str);
    }

    @Override // com.byd.dilink.statusbar.IDiLinkStatusBarManager
    public void setSplitScreenMode(boolean z) {
        throw new UnsupportedTargetPlatformException("RSE 没有 setSplitScreenMode 这个函数！");
    }

    @Override // com.byd.dilink.statusbar.IDiLinkStatusBarManager
    public int startActivityWhenMultiWindow(String str, Intent intent, int i) {
        throw new UnsupportedTargetPlatformException("RSE 没有 startActivityWhenMultiWindow 这个函数！");
    }

    @Override // com.byd.dilink.statusbar.IDiLinkStatusBarManager
    public void swapSplitScreenWindow() {
        throw new UnsupportedTargetPlatformException("RSE 没有 swapSplitScreenWindow 这个函数！");
    }

    @Override // com.byd.dilink.statusbar.IDiLinkStatusBarManager
    public void toggleSplitScreen() {
        throw new UnsupportedTargetPlatformException("RSE 没有 moveAppToFullScreenByPkg 这个函数！");
    }

    @Override // com.byd.dilink.statusbar.IDiLinkStatusBarManager
    public void unregisterCardListener(IDiLinkCardListener iDiLinkCardListener) {
        throw new UnsupportedTargetPlatformException("RSE 没有 unregisterCardListener 这个函数！");
    }

    @Override // com.byd.dilink.statusbar.IDiLinkStatusBarManager
    public void unregisterSceneModeCallback(IDiLinkSceneModeStateCallback iDiLinkSceneModeStateCallback) {
        this.f2781b.removeCallbackIgnoreRemoteException(iDiLinkSceneModeStateCallback, new IDiLinkCallbackHandler() { // from class: com.byd.dilink_rse.statusbar.d
            @Override // com.byd.dilink.callbackholder.IDiLinkCallbackHandler
            public final void onResult(Object obj) {
                this.f2790a.g((ISceneModeStateCallback) obj);
            }
        });
    }

    @Override // com.byd.dilink.statusbar.IDiLinkStatusBarManager
    public void unregisterSplitScreenCallBack(IDiLinkSplitScreenCallBack iDiLinkSplitScreenCallBack) {
        this.f2780a.removeCallbackIgnoreRemoteException(iDiLinkSplitScreenCallBack, new IDiLinkCallbackHandler() { // from class: com.byd.dilink_rse.statusbar.b
            @Override // com.byd.dilink.callbackholder.IDiLinkCallbackHandler
            public final void onResult(Object obj) {
                this.f2788a.h((ISplitScreenCallBack) obj);
            }
        });
    }

    @Override // com.byd.dilink.statusbar.IDiLinkStatusBarManager
    public void userActivityNoUpdateLocked() {
        this.f2782c.userActivityNoUpdateLocked();
    }
}
