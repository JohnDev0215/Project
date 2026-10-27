package RR9QRrr.RR9QRrr.RR9QRrr.RQR99rR;

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

/* JADX INFO: loaded from: classes.dex */
@Platform(versions = {DiLink.VERSION_NAME_RCS})
@ServiceImpl(service = IDiLinkStatusBarManager.class, singleton = true)
public class RR9QRrr implements IDiLinkStatusBarManager {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private StatusBarManager f105a;

    @SuppressLint({"WrongConstant"})
    public RR9QRrr(Context context) {
        this.f105a = (StatusBarManager) context.getSystemService("statusbar");
    }

    @Override // com.byd.dilink.statusbar.IDiLinkStatusBarManager
    public boolean closeAppWhenMultiWindow(String str, int i) {
        throw new UnsupportedTargetPlatformException("rcs 没有 closeAppWhenMultiWindow 这个函数！");
    }

    @Override // com.byd.dilink.statusbar.IDiLinkStatusBarManager
    public void collapsePanels() {
        this.f105a.collapsePanels();
    }

    @Override // com.byd.dilink.statusbar.IDiLinkStatusBarManager
    public void enterSplitScreenModeByLauncher(String str, Intent intent) {
        throw new UnsupportedTargetPlatformException("rcs 没有 enterSplitScreenModeByLauncher 这个函数！");
    }

    @Override // com.byd.dilink.statusbar.IDiLinkStatusBarManager
    public void enterSplitScreenModeByLauncher(String str, String str2) {
        throw new UnsupportedTargetPlatformException("rcs 没有 enterSplitScreenModeByLauncher 这个函数！");
    }

    @Override // com.byd.dilink.statusbar.IDiLinkStatusBarManager
    public String getAppPkgByDirection(boolean z) {
        throw new UnsupportedTargetPlatformException("rcs 没有 getAppPkgByDirection 这个函数！");
    }

    @Override // com.byd.dilink.statusbar.IDiLinkStatusBarManager
    public int getSplitScreenStatus() {
        throw new UnsupportedTargetPlatformException("rcs 没有 getSplitScreenStatus 这个函数！");
    }

    @Override // com.byd.dilink.statusbar.IDiLinkStatusBarManager
    public void handleHomeButton() {
        this.f105a.handleHomeButton();
    }

    @Override // com.byd.dilink.statusbar.IDiLinkStatusBarManager
    public int isAppSupportSplit(String str) {
        throw new UnsupportedTargetPlatformException("rcs 没有 isAppSupportSplit 这个函数！");
    }

    @Override // com.byd.dilink.statusbar.IDiLinkStatusBarManager
    public boolean isDockState() {
        throw new UnsupportedTargetPlatformException("rcs不存在isDockState()方法");
    }

    @Override // com.byd.dilink.statusbar.IDiLinkStatusBarManager
    public boolean isFocusInPrimary() {
        throw new UnsupportedTargetPlatformException("rcs 没有 isFocusInPrimary 这个函数！");
    }

    @Override // com.byd.dilink.statusbar.IDiLinkStatusBarManager
    public boolean isRunningTaskSupportsMultiWindow() {
        throw new UnsupportedTargetPlatformException("rcs 没有 isRunningTaskSupportsMultiWindow 这个函数！");
    }

    @Override // com.byd.dilink.statusbar.IDiLinkStatusBarManager
    public boolean isSwapSplitScreenUsable() {
        throw new UnsupportedTargetPlatformException("rcs 没有 isSwapSplitScreenUsable 这个函数！");
    }

    @Override // com.byd.dilink.statusbar.IDiLinkStatusBarManager
    public boolean moveAppToFullScreenByPkg(String str) {
        throw new UnsupportedTargetPlatformException("rcs 没有 moveAppToFullScreenByPkg 这个函数！");
    }

    @Override // com.byd.dilink.statusbar.IDiLinkStatusBarManager
    public void notifyDockedStackExistsChanged(boolean z) {
        throw new UnsupportedTargetPlatformException("rcs 没有 notifyDockedStackExistsChanged 这个函数！");
    }

    @Override // com.byd.dilink.statusbar.IDiLinkStatusBarManager
    public void onSteeringWheelRotationClick() {
        this.f105a.onSteeringWheelRotationClick();
    }

    @Override // com.byd.dilink.statusbar.IDiLinkStatusBarManager
    public void registerCardListener(IDiLinkCardListener iDiLinkCardListener) {
        throw new UnsupportedTargetPlatformException("rcs 没有 registerCardListener 这个函数！");
    }

    @Override // com.byd.dilink.statusbar.IDiLinkStatusBarManager
    public void registerSceneModeCallback(IDiLinkSceneModeStateCallback iDiLinkSceneModeStateCallback) {
        throw new UnsupportedTargetPlatformException("rcs 没有 registerSceneModeCallback方法！");
    }

    @Override // com.byd.dilink.statusbar.IDiLinkStatusBarManager
    public void registerSplitScreenCallBack(IDiLinkSplitScreenCallBack iDiLinkSplitScreenCallBack) {
        throw new UnsupportedTargetPlatformException("rcs 没有 registerSplitScreenCallBack 这个函数！");
    }

    @Override // com.byd.dilink.statusbar.IDiLinkStatusBarManager
    public void setActivityNameForStatusAndNavigationBar(String str) {
        throw new UnsupportedTargetPlatformException("rcs 没有 setActivityNameForStatusAndNavigationBar(@NonNull String name) 这个函数！");
    }

    @Override // com.byd.dilink.statusbar.IDiLinkStatusBarManager
    public void setActivityNameForStatusAndNavigationBar(@NonNull String str, int i) {
        throw new UnsupportedTargetPlatformException("rcs 没有 setActivityNameForStatusAndNavigationBar方法！");
    }

    @Override // com.byd.dilink.statusbar.IDiLinkStatusBarManager
    public void setMultiWindow() {
        throw new UnsupportedTargetPlatformException("rcs 没有 setMultiWindow 这个函数！");
    }

    @Override // com.byd.dilink.statusbar.IDiLinkStatusBarManager
    public void setNewFocusStatusForDivider() {
        throw new UnsupportedTargetPlatformException("rcs 没有 setNewFocusStatusForDivider 这个函数！");
    }

    @Override // com.byd.dilink.statusbar.IDiLinkStatusBarManager
    public void setPackageNameForStatusAndNavigationBar(String str) {
        this.f105a.setPackageNameForStatusAndNaviationBar(str);
    }

    @Override // com.byd.dilink.statusbar.IDiLinkStatusBarManager
    public void setSceneMode(int i, int i2, int i3) {
        throw new UnsupportedTargetPlatformException("rcs 没有 setSceneMode(int modeType, int state, int from)方法");
    }

    @Override // com.byd.dilink.statusbar.IDiLinkStatusBarManager
    public void setSceneModeCallbackContent(String str) {
        throw new UnsupportedTargetPlatformException("rcs 没有 setSceneModeCallbackContent 这个函数！");
    }

    @Override // com.byd.dilink.statusbar.IDiLinkStatusBarManager
    public void setSplitScreenMode(boolean z) {
        throw new UnsupportedTargetPlatformException("rcs 没有 setSplitScreenMode 这个函数！");
    }

    @Override // com.byd.dilink.statusbar.IDiLinkStatusBarManager
    public int startActivityWhenMultiWindow(String str, Intent intent, int i) {
        throw new UnsupportedTargetPlatformException("rcs 没有 startActivityWhenMultiWindow 这个函数！");
    }

    @Override // com.byd.dilink.statusbar.IDiLinkStatusBarManager
    public void swapSplitScreenWindow() {
        throw new UnsupportedTargetPlatformException("rcs 没有 swapSplitScreenWindow 这个函数！");
    }

    @Override // com.byd.dilink.statusbar.IDiLinkStatusBarManager
    public void toggleSplitScreen() {
        throw new UnsupportedTargetPlatformException("rcs 没有 moveAppToFullScreenByPkg 这个函数！");
    }

    @Override // com.byd.dilink.statusbar.IDiLinkStatusBarManager
    public void unregisterCardListener(IDiLinkCardListener iDiLinkCardListener) {
        throw new UnsupportedTargetPlatformException("rcs 没有 unregisterCardListener 这个函数！");
    }

    @Override // com.byd.dilink.statusbar.IDiLinkStatusBarManager
    public void unregisterSceneModeCallback(IDiLinkSceneModeStateCallback iDiLinkSceneModeStateCallback) {
        throw new UnsupportedTargetPlatformException("rcs 没有 unregisterSceneModeCallback方法！");
    }

    @Override // com.byd.dilink.statusbar.IDiLinkStatusBarManager
    public void unregisterSplitScreenCallBack(IDiLinkSplitScreenCallBack iDiLinkSplitScreenCallBack) {
        throw new UnsupportedTargetPlatformException("rcs 没有 unregisterSplitScreenCallBack 这个函数！");
    }

    @Override // com.byd.dilink.statusbar.IDiLinkStatusBarManager
    public void userActivityNoUpdateLocked() {
        this.f105a.userActivityNoUpdateLocked();
    }
}
