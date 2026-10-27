package jViVV55i.jViVV55i.jViVV55i.JV;

import android.annotation.SuppressLint;
import android.app.StatusBarManager;
import android.content.Context;
import android.content.Intent;
import androidx.annotation.NonNull;
import com.byd.dilink.DiLink;
import com.byd.dilink.UnsupportedTargetPlatformException;
import com.byd.dilink.anotation.Platform;
import com.byd.dilink.statusbar.IDiLinkCardListener;
import com.byd.dilink.statusbar.IDiLinkSceneModeStateCallback;
import com.byd.dilink.statusbar.IDiLinkSplitScreenCallBack;
import com.byd.dilink.statusbar.IDiLinkStatusBarManager;
import com.byd.foundation.annotation.ServiceImpl;

/* JADX INFO: loaded from: classes2.dex */
@Platform(versions = {DiLink.VERSION_NAME_3_0})
@ServiceImpl(service = IDiLinkStatusBarManager.class, singleton = true)
public class jViVV55i implements IDiLinkStatusBarManager {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private StatusBarManager f3053a;

    @SuppressLint({"WrongConstant"})
    public jViVV55i(Context context) {
        this.f3053a = (StatusBarManager) context.getSystemService("statusbar");
    }

    @Override // com.byd.dilink.statusbar.IDiLinkStatusBarManager
    public boolean closeAppWhenMultiWindow(String str, int i) {
        return this.f3053a.closeAppWhenMultiWindow(str, i);
    }

    @Override // com.byd.dilink.statusbar.IDiLinkStatusBarManager
    public void collapsePanels() {
        this.f3053a.collapsePanels();
    }

    @Override // com.byd.dilink.statusbar.IDiLinkStatusBarManager
    public void enterSplitScreenModeByLauncher(String str, Intent intent) {
        throw new UnsupportedTargetPlatformException("Di3.0 没有 enterSplitScreenModeByLauncher 这个函数！");
    }

    @Override // com.byd.dilink.statusbar.IDiLinkStatusBarManager
    public void enterSplitScreenModeByLauncher(String str, String str2) {
        throw new UnsupportedTargetPlatformException("Di3.0 没有 enterSplitScreenModeByLauncher 这个函数！");
    }

    @Override // com.byd.dilink.statusbar.IDiLinkStatusBarManager
    public String getAppPkgByDirection(boolean z) {
        return this.f3053a.getAppPkgByDirection(z);
    }

    @Override // com.byd.dilink.statusbar.IDiLinkStatusBarManager
    public int getSplitScreenStatus() {
        return this.f3053a.getSplitScreenStatus();
    }

    @Override // com.byd.dilink.statusbar.IDiLinkStatusBarManager
    public void handleHomeButton() {
        this.f3053a.handleHomeButton();
    }

    @Override // com.byd.dilink.statusbar.IDiLinkStatusBarManager
    public int isAppSupportSplit(String str) {
        return this.f3053a.isAppSuportSplit(str);
    }

    @Override // com.byd.dilink.statusbar.IDiLinkStatusBarManager
    public boolean isDockState() {
        return this.f3053a.isDockState();
    }

    @Override // com.byd.dilink.statusbar.IDiLinkStatusBarManager
    public boolean isFocusInPrimary() {
        return this.f3053a.isFocusInPrimary();
    }

    @Override // com.byd.dilink.statusbar.IDiLinkStatusBarManager
    public boolean isRunningTaskSupportsMultiWindow() {
        return this.f3053a.isRunningTaskSupportsMultiWindow();
    }

    @Override // com.byd.dilink.statusbar.IDiLinkStatusBarManager
    public boolean isSwapSplitScreenUsable() {
        return this.f3053a.isSwapSplitScreenUsable();
    }

    @Override // com.byd.dilink.statusbar.IDiLinkStatusBarManager
    public boolean moveAppToFullScreenByPkg(String str) {
        return this.f3053a.moveAppToFullScreenByPkg(str);
    }

    @Override // com.byd.dilink.statusbar.IDiLinkStatusBarManager
    public void notifyDockedStackExistsChanged(boolean z) {
        throw new UnsupportedTargetPlatformException("Di3.0 没有 notifyDockedStackExistsChanged 这个函数！");
    }

    @Override // com.byd.dilink.statusbar.IDiLinkStatusBarManager
    public void onSteeringWheelRotationClick() {
        this.f3053a.onSteeringWheelRotationClick();
    }

    @Override // com.byd.dilink.statusbar.IDiLinkStatusBarManager
    public void registerCardListener(IDiLinkCardListener iDiLinkCardListener) {
        throw new UnsupportedTargetPlatformException("Di3.0 没有 registerCardListener 这个函数！");
    }

    @Override // com.byd.dilink.statusbar.IDiLinkStatusBarManager
    public void registerSceneModeCallback(IDiLinkSceneModeStateCallback iDiLinkSceneModeStateCallback) {
        throw new UnsupportedTargetPlatformException("Di3.0 没有 registerSceneModeCallback 这个函数！");
    }

    @Override // com.byd.dilink.statusbar.IDiLinkStatusBarManager
    public void registerSplitScreenCallBack(IDiLinkSplitScreenCallBack iDiLinkSplitScreenCallBack) {
        throw new UnsupportedTargetPlatformException("Di3.0 没有 registerSplitScreenCallBack 这个函数！");
    }

    @Override // com.byd.dilink.statusbar.IDiLinkStatusBarManager
    public void setActivityNameForStatusAndNavigationBar(String str) {
        this.f3053a.setActivityNameForStatusAndNavigaitonBar(str);
    }

    @Override // com.byd.dilink.statusbar.IDiLinkStatusBarManager
    public void setActivityNameForStatusAndNavigationBar(@NonNull String str, int i) {
        throw new UnsupportedTargetPlatformException("Di3.0 没有 setActivityNameForStatusAndNavigationBar方法！");
    }

    @Override // com.byd.dilink.statusbar.IDiLinkStatusBarManager
    public void setMultiWindow() {
        throw new UnsupportedTargetPlatformException("Di3.0 没有 setMultiWindow 这个函数！");
    }

    @Override // com.byd.dilink.statusbar.IDiLinkStatusBarManager
    public void setNewFocusStatusForDivider() {
        this.f3053a.setNewFocusStatusForDvider();
    }

    @Override // com.byd.dilink.statusbar.IDiLinkStatusBarManager
    public void setPackageNameForStatusAndNavigationBar(String str) {
        this.f3053a.setPackageNameForStatusAndNaviationBar(str);
    }

    @Override // com.byd.dilink.statusbar.IDiLinkStatusBarManager
    public void setSceneMode(int i, int i2, int i3) {
        throw new UnsupportedTargetPlatformException("Di3.0 没有 setSceneMode 这个函数！");
    }

    @Override // com.byd.dilink.statusbar.IDiLinkStatusBarManager
    public void setSceneModeCallbackContent(String str) {
        throw new UnsupportedTargetPlatformException("Di3.0 没有 setSceneModeCallbackContent 这个函数！");
    }

    @Override // com.byd.dilink.statusbar.IDiLinkStatusBarManager
    public void setSplitScreenMode(boolean z) {
        this.f3053a.setSplitScreenMode(z);
    }

    @Override // com.byd.dilink.statusbar.IDiLinkStatusBarManager
    public int startActivityWhenMultiWindow(String str, Intent intent, int i) {
        return this.f3053a.startActivityWhenMultiWindow(str, intent, i);
    }

    @Override // com.byd.dilink.statusbar.IDiLinkStatusBarManager
    public void swapSplitScreenWindow() {
        this.f3053a.swapSplitScreenWindow();
    }

    @Override // com.byd.dilink.statusbar.IDiLinkStatusBarManager
    public void toggleSplitScreen() {
        throw new UnsupportedTargetPlatformException("Di3.0 没有 toggleSplitScreen 这个函数！");
    }

    @Override // com.byd.dilink.statusbar.IDiLinkStatusBarManager
    public void unregisterCardListener(IDiLinkCardListener iDiLinkCardListener) {
        throw new UnsupportedTargetPlatformException("Di3.0 没有 unregisterCardListener 这个函数！");
    }

    @Override // com.byd.dilink.statusbar.IDiLinkStatusBarManager
    public void unregisterSceneModeCallback(IDiLinkSceneModeStateCallback iDiLinkSceneModeStateCallback) {
        throw new UnsupportedTargetPlatformException("Di3.0 没有 unregisterSceneModeCallback 这个函数！");
    }

    @Override // com.byd.dilink.statusbar.IDiLinkStatusBarManager
    public void unregisterSplitScreenCallBack(IDiLinkSplitScreenCallBack iDiLinkSplitScreenCallBack) {
        throw new UnsupportedTargetPlatformException("Di3.0 没有 registerSplitScreenCallBack 这个函数！");
    }

    @Override // com.byd.dilink.statusbar.IDiLinkStatusBarManager
    public void userActivityNoUpdateLocked() {
        this.f3053a.userActivityNoUpdateLocked();
    }
}
