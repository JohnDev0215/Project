package com.byd.dilink51.statusbar;

import android.annotation.SuppressLint;
import android.app.BydStatusBarManager;
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
@Platform(isDecoupled = true, versions = {DiLink.VERSION_NAME_5_1})
@ServiceImpl(service = IDiLinkStatusBarManager.class, singleton = true)
public class DiLinkStatusBarManager implements IDiLinkStatusBarManager {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private DiLink5_0_SplitScreenCallBacks f1682a = new DiLink5_0_SplitScreenCallBacks();

    /* JADX INFO: renamed from: b, reason: collision with root package name */
    private DiLink5_0_SceneModeStateCallbacks f1683b = new DiLink5_0_SceneModeStateCallbacks();

    /* JADX INFO: renamed from: c, reason: collision with root package name */
    private StatusBarManager f1684c;

    public static class DiLink5_0_SceneModeStateCallbacks extends DiLinkCallbackHolders<IDiLinkSceneModeStateCallback, ISceneModeStateCallback> {
        @Override // com.byd.dilink.callbackholder.DiLinkCallbackHolders
        /* JADX INFO: renamed from: a, reason: merged with bridge method [inline-methods] */
        public ISceneModeStateCallback createInnerCallback(final IDiLinkSceneModeStateCallback iDiLinkSceneModeStateCallback) {
            return new ISceneModeStateCallback.Stub() { // from class: com.byd.dilink51.statusbar.DiLinkStatusBarManager.DiLink5_0_SceneModeStateCallbacks.1
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
            return new ISplitScreenCallBack.Stub() { // from class: com.byd.dilink51.statusbar.DiLinkStatusBarManager.DiLink5_0_SplitScreenCallBacks.1
                @Keep
                public void onDockedStackExistsChanged(boolean z) {
                    iDiLinkSplitScreenCallBack.onDockedStackExistsChanged(z);
                }
            };
        }
    }

    @SuppressLint({"WrongConstant"})
    public DiLinkStatusBarManager(Context context) {
        this.f1684c = (StatusBarManager) context.getSystemService("statusbar");
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ void a(ISceneModeStateCallback iSceneModeStateCallback) {
        BydStatusBarManager.getInstance().unregisterSceneModeCallback(iSceneModeStateCallback);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ void b(ISplitScreenCallBack iSplitScreenCallBack) {
        BydStatusBarManager.getInstance().unregisterSplitScreenCallBack(iSplitScreenCallBack);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ void c(ISceneModeStateCallback iSceneModeStateCallback) {
        BydStatusBarManager.getInstance().registerSceneModeCallback(iSceneModeStateCallback);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static /* synthetic */ void d(ISplitScreenCallBack iSplitScreenCallBack) {
        BydStatusBarManager.getInstance().registerSplitScreenCallBack(iSplitScreenCallBack);
    }

    @Override // com.byd.dilink.statusbar.IDiLinkStatusBarManager
    public boolean closeAppWhenMultiWindow(@NonNull String str, int i) {
        return BydStatusBarManager.getInstance().closeAppWhenMultiWindow(str, i);
    }

    @Override // com.byd.dilink.statusbar.IDiLinkStatusBarManager
    public void collapsePanels() {
        this.f1684c.collapsePanels();
    }

    @Override // com.byd.dilink.statusbar.IDiLinkStatusBarManager
    public void enterSplitScreenModeByLauncher(String str, Intent intent) {
        BydStatusBarManager.getInstance().enterSplitScreenModeByLaucher(str, intent);
    }

    @Override // com.byd.dilink.statusbar.IDiLinkStatusBarManager
    public void enterSplitScreenModeByLauncher(String str, String str2) {
        BydStatusBarManager.getInstance().enterSplitScreenModeByLaucher(str, str2);
    }

    @Override // com.byd.dilink.statusbar.IDiLinkStatusBarManager
    public String getAppPkgByDirection(boolean z) {
        return BydStatusBarManager.getInstance().getAppPkgByDirection(z);
    }

    @Override // com.byd.dilink.statusbar.IDiLinkStatusBarManager
    public int getSplitScreenStatus() {
        return BydStatusBarManager.getInstance().getSplitScreenStatus();
    }

    @Override // com.byd.dilink.statusbar.IDiLinkStatusBarManager
    public void handleHomeButton() {
        throw new UnsupportedTargetPlatformException("Di5.1 没有 handleHomeButton 这个函数！");
    }

    @Override // com.byd.dilink.statusbar.IDiLinkStatusBarManager
    public int isAppSupportSplit(@NonNull String str) {
        return BydStatusBarManager.getInstance().isAppSuportSplit(str);
    }

    @Override // com.byd.dilink.statusbar.IDiLinkStatusBarManager
    public boolean isDockState() {
        return BydStatusBarManager.getInstance().isDockState();
    }

    @Override // com.byd.dilink.statusbar.IDiLinkStatusBarManager
    public boolean isFocusInPrimary() {
        return BydStatusBarManager.getInstance().isFocusInPrimary();
    }

    @Override // com.byd.dilink.statusbar.IDiLinkStatusBarManager
    public boolean isRunningTaskSupportsMultiWindow() {
        return BydStatusBarManager.getInstance().isRunningTaskSupportsMultiWindow();
    }

    @Override // com.byd.dilink.statusbar.IDiLinkStatusBarManager
    public boolean isSwapSplitScreenUsable() {
        return BydStatusBarManager.getInstance().isSwapSplitScreenUsable();
    }

    @Override // com.byd.dilink.statusbar.IDiLinkStatusBarManager
    public boolean moveAppToFullScreenByPkg(@NonNull String str) {
        return BydStatusBarManager.getInstance().moveAppToFullScreenByPkg(str);
    }

    @Override // com.byd.dilink.statusbar.IDiLinkStatusBarManager
    public void notifyDockedStackExistsChanged(boolean z) {
        BydStatusBarManager.getInstance().notifyDockedStackExistsChanged(z);
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
        this.f1683b.addCallbackIgnoreRemoteException(iDiLinkSceneModeStateCallback, new IDiLinkCallbackHandler() { // from class: com.byd.dilink51.statusbar.c
            @Override // com.byd.dilink.callbackholder.IDiLinkCallbackHandler
            public final void onResult(Object obj) {
                DiLinkStatusBarManager.c((ISceneModeStateCallback) obj);
            }
        });
    }

    @Override // com.byd.dilink.statusbar.IDiLinkStatusBarManager
    public void registerSplitScreenCallBack(@NonNull IDiLinkSplitScreenCallBack iDiLinkSplitScreenCallBack) {
        this.f1682a.addCallbackIgnoreRemoteException(iDiLinkSplitScreenCallBack, new IDiLinkCallbackHandler() { // from class: com.byd.dilink51.statusbar.b
            @Override // com.byd.dilink.callbackholder.IDiLinkCallbackHandler
            public final void onResult(Object obj) {
                DiLinkStatusBarManager.d((ISplitScreenCallBack) obj);
            }
        });
    }

    @Override // com.byd.dilink.statusbar.IDiLinkStatusBarManager
    public void setActivityNameForStatusAndNavigationBar(@NonNull String str) {
        throw new UnsupportedTargetPlatformException("Di5.1 没有setActivityNameForStatusAndNavigationBar(@NonNull String name) 方法！");
    }

    @Override // com.byd.dilink.statusbar.IDiLinkStatusBarManager
    public void setActivityNameForStatusAndNavigationBar(@NonNull String str, int i) {
        BydStatusBarManager.getInstance().setActivityNameForStatusAndNavigaitonBar(str, i);
    }

    @Override // com.byd.dilink.statusbar.IDiLinkStatusBarManager
    public void setMultiWindow() {
        BydStatusBarManager.getInstance().setMulitWindow();
    }

    @Override // com.byd.dilink.statusbar.IDiLinkStatusBarManager
    public void setNewFocusStatusForDivider() {
        BydStatusBarManager.getInstance().setNewFocusStatusForDvider();
    }

    @Override // com.byd.dilink.statusbar.IDiLinkStatusBarManager
    public void setPackageNameForStatusAndNavigationBar(@NonNull String str) {
        BydStatusBarManager.getInstance().setPackageNameForStatusAndNaviationBar(str);
    }

    @Override // com.byd.dilink.statusbar.IDiLinkStatusBarManager
    public void setSceneMode(int i, int i2, int i3) {
        BydStatusBarManager.getInstance().setSceneMode(i, i2, i3);
    }

    @Override // com.byd.dilink.statusbar.IDiLinkStatusBarManager
    public void setSceneModeCallbackContent(String str) {
        BydStatusBarManager.getInstance().setSceneModeCallbackContent(str);
    }

    @Override // com.byd.dilink.statusbar.IDiLinkStatusBarManager
    public void setSplitScreenMode(boolean z) {
        BydStatusBarManager.getInstance().setSplitScreenMode(z);
    }

    @Override // com.byd.dilink.statusbar.IDiLinkStatusBarManager
    public int startActivityWhenMultiWindow(@NonNull String str, @NonNull Intent intent, int i) {
        return BydStatusBarManager.getInstance().startActivityWhenMultiWindow(str, intent, i);
    }

    @Override // com.byd.dilink.statusbar.IDiLinkStatusBarManager
    public void swapSplitScreenWindow() {
        BydStatusBarManager.getInstance().swapSplitScreenWindow();
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
        this.f1683b.removeCallbackIgnoreRemoteException(iDiLinkSceneModeStateCallback, new IDiLinkCallbackHandler() { // from class: com.byd.dilink51.statusbar.a
            @Override // com.byd.dilink.callbackholder.IDiLinkCallbackHandler
            public final void onResult(Object obj) {
                DiLinkStatusBarManager.a((ISceneModeStateCallback) obj);
            }
        });
    }

    @Override // com.byd.dilink.statusbar.IDiLinkStatusBarManager
    public void unregisterSplitScreenCallBack(@NonNull IDiLinkSplitScreenCallBack iDiLinkSplitScreenCallBack) {
        this.f1682a.removeCallbackIgnoreRemoteException(iDiLinkSplitScreenCallBack, new IDiLinkCallbackHandler() { // from class: com.byd.dilink51.statusbar.d
            @Override // com.byd.dilink.callbackholder.IDiLinkCallbackHandler
            public final void onResult(Object obj) {
                DiLinkStatusBarManager.b((ISplitScreenCallBack) obj);
            }
        });
    }

    @Override // com.byd.dilink.statusbar.IDiLinkStatusBarManager
    public void userActivityNoUpdateLocked() {
        BydStatusBarManager.getInstance().userActivityNoUpdateLocked();
    }
}
