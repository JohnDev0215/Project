package GGW.GGW.GGW.gg;

import android.annotation.SuppressLint;
import android.app.StatusBarManager;
import android.content.Context;
import android.content.Intent;
import androidx.annotation.NonNull;
import com.byd.dilink.UnsupportedTargetPlatformException;
import com.byd.dilink.anotation.Platform;
import com.byd.dilink.statusbar.IDiLinkCardListener;
import com.byd.dilink.statusbar.IDiLinkSceneModeStateCallback;
import com.byd.dilink.statusbar.IDiLinkSplitScreenCallBack;
import com.byd.dilink.statusbar.IDiLinkStatusBarManager;
import com.byd.foundation.annotation.ServiceImpl;

/* JADX INFO: loaded from: classes.dex */
@Platform(versions = {"RUI"})
@ServiceImpl(service = IDiLinkStatusBarManager.class, singleton = true)
public class Gg4HHGg44 implements IDiLinkStatusBarManager {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private StatusBarManager f56a;

    @SuppressLint({"WrongConstant"})
    public Gg4HHGg44(Context context) {
        this.f56a = (StatusBarManager) context.getSystemService("statusbar");
    }

    @Override // com.byd.dilink.statusbar.IDiLinkStatusBarManager
    public boolean closeAppWhenMultiWindow(@NonNull String str, int i) {
        throw new UnsupportedTargetPlatformException("Di6.0 没有这个函数！");
    }

    @Override // com.byd.dilink.statusbar.IDiLinkStatusBarManager
    public void collapsePanels() {
        this.f56a.collapsePanels();
    }

    @Override // com.byd.dilink.statusbar.IDiLinkStatusBarManager
    public void enterSplitScreenModeByLauncher(String str, Intent intent) {
        throw new UnsupportedTargetPlatformException("Di6.0 没有 enterSplitScreenModeByLaucher 这个函数！");
    }

    @Override // com.byd.dilink.statusbar.IDiLinkStatusBarManager
    public void enterSplitScreenModeByLauncher(String str, String str2) {
        throw new UnsupportedTargetPlatformException("Di6.0 没有 enterSplitScreenModeByLaucher 这个函数！");
    }

    @Override // com.byd.dilink.statusbar.IDiLinkStatusBarManager
    public String getAppPkgByDirection(boolean z) {
        throw new UnsupportedTargetPlatformException("Di6.0 没有这个函数！");
    }

    @Override // com.byd.dilink.statusbar.IDiLinkStatusBarManager
    public int getSplitScreenStatus() {
        throw new UnsupportedTargetPlatformException("Di6.0 没有这个函数！");
    }

    @Override // com.byd.dilink.statusbar.IDiLinkStatusBarManager
    public void handleHomeButton() {
        throw new UnsupportedTargetPlatformException("Di6.0 没有 handleHomeButton 这个函数！");
    }

    @Override // com.byd.dilink.statusbar.IDiLinkStatusBarManager
    public int isAppSupportSplit(@NonNull String str) {
        throw new UnsupportedTargetPlatformException("Di6.0 没有这个函数！");
    }

    @Override // com.byd.dilink.statusbar.IDiLinkStatusBarManager
    public boolean isDockState() {
        throw new UnsupportedTargetPlatformException("Di6.0 没有这个函数！");
    }

    @Override // com.byd.dilink.statusbar.IDiLinkStatusBarManager
    public boolean isFocusInPrimary() {
        throw new UnsupportedTargetPlatformException("Di6.0 没有这个函数！");
    }

    @Override // com.byd.dilink.statusbar.IDiLinkStatusBarManager
    public boolean isRunningTaskSupportsMultiWindow() {
        throw new UnsupportedTargetPlatformException("Di6.0 没有这个函数！");
    }

    @Override // com.byd.dilink.statusbar.IDiLinkStatusBarManager
    public boolean isSwapSplitScreenUsable() {
        throw new UnsupportedTargetPlatformException("Di6.0 没有这个函数！");
    }

    @Override // com.byd.dilink.statusbar.IDiLinkStatusBarManager
    public boolean moveAppToFullScreenByPkg(@NonNull String str) {
        throw new UnsupportedTargetPlatformException("Di6.0 没有这个函数！");
    }

    @Override // com.byd.dilink.statusbar.IDiLinkStatusBarManager
    public void notifyDockedStackExistsChanged(boolean z) {
        throw new UnsupportedTargetPlatformException("Di6.0 没有这个函数！");
    }

    @Override // com.byd.dilink.statusbar.IDiLinkStatusBarManager
    public void onSteeringWheelRotationClick() {
        throw new UnsupportedTargetPlatformException("Di6.0 没有 onSteeringWheelRotationClick 这个函数！");
    }

    @Override // com.byd.dilink.statusbar.IDiLinkStatusBarManager
    public void registerCardListener(IDiLinkCardListener iDiLinkCardListener) {
        throw new UnsupportedTargetPlatformException("Di6.0 没有 registerCardListener 这个函数！");
    }

    @Override // com.byd.dilink.statusbar.IDiLinkStatusBarManager
    public void registerSceneModeCallback(@NonNull IDiLinkSceneModeStateCallback iDiLinkSceneModeStateCallback) {
        throw new UnsupportedTargetPlatformException("Di6.0 没有这个函数！");
    }

    @Override // com.byd.dilink.statusbar.IDiLinkStatusBarManager
    public void registerSplitScreenCallBack(@NonNull IDiLinkSplitScreenCallBack iDiLinkSplitScreenCallBack) {
        throw new UnsupportedTargetPlatformException("Di6.0 没有这个函数！");
    }

    @Override // com.byd.dilink.statusbar.IDiLinkStatusBarManager
    public void setActivityNameForStatusAndNavigationBar(@NonNull String str) {
        this.f56a.setActivityNameForStatusAndNavigaitonBar(str);
    }

    @Override // com.byd.dilink.statusbar.IDiLinkStatusBarManager
    public void setActivityNameForStatusAndNavigationBar(@NonNull String str, int i) {
        throw new UnsupportedTargetPlatformException("Di6.0 没有 setActivityNameForStatusAndNavigationBar(@NonNull String name, int displayId)方法！");
    }

    @Override // com.byd.dilink.statusbar.IDiLinkStatusBarManager
    public void setMultiWindow() {
        throw new UnsupportedTargetPlatformException("Di6.0 没有这个函数！");
    }

    @Override // com.byd.dilink.statusbar.IDiLinkStatusBarManager
    public void setNewFocusStatusForDivider() {
        throw new UnsupportedTargetPlatformException("Di6.0 没有这个函数！");
    }

    @Override // com.byd.dilink.statusbar.IDiLinkStatusBarManager
    public void setPackageNameForStatusAndNavigationBar(@NonNull String str) {
        this.f56a.setPackageNameForStatusAndNaviationBar(str);
    }

    @Override // com.byd.dilink.statusbar.IDiLinkStatusBarManager
    public void setSceneMode(int i, int i2, int i3) {
        throw new UnsupportedTargetPlatformException("Di6.0 没有这个函数！");
    }

    @Override // com.byd.dilink.statusbar.IDiLinkStatusBarManager
    public void setSceneModeCallbackContent(String str) {
        throw new UnsupportedTargetPlatformException("Di6.0 没有这个函数！");
    }

    @Override // com.byd.dilink.statusbar.IDiLinkStatusBarManager
    public void setSplitScreenMode(boolean z) {
        throw new UnsupportedTargetPlatformException("Di6.0 没有这个函数！");
    }

    @Override // com.byd.dilink.statusbar.IDiLinkStatusBarManager
    public int startActivityWhenMultiWindow(@NonNull String str, @NonNull Intent intent, int i) {
        throw new UnsupportedTargetPlatformException("Di6.0 没有这个函数！");
    }

    @Override // com.byd.dilink.statusbar.IDiLinkStatusBarManager
    public void swapSplitScreenWindow() {
        throw new UnsupportedTargetPlatformException("Di6.0 没有这个函数！");
    }

    @Override // com.byd.dilink.statusbar.IDiLinkStatusBarManager
    public void toggleSplitScreen() {
        throw new UnsupportedTargetPlatformException("Di6.0 没有 toggleSplitScreen 这个函数！");
    }

    @Override // com.byd.dilink.statusbar.IDiLinkStatusBarManager
    public void unregisterCardListener(IDiLinkCardListener iDiLinkCardListener) {
        throw new UnsupportedTargetPlatformException("Di6.0 没有 unregisterCardListener 这个函数！");
    }

    @Override // com.byd.dilink.statusbar.IDiLinkStatusBarManager
    public void unregisterSceneModeCallback(@NonNull IDiLinkSceneModeStateCallback iDiLinkSceneModeStateCallback) {
        throw new UnsupportedTargetPlatformException("Di6.0 没有这个函数！");
    }

    @Override // com.byd.dilink.statusbar.IDiLinkStatusBarManager
    public void unregisterSplitScreenCallBack(@NonNull IDiLinkSplitScreenCallBack iDiLinkSplitScreenCallBack) {
        throw new UnsupportedTargetPlatformException("Di6.0 没有这个函数！");
    }

    @Override // com.byd.dilink.statusbar.IDiLinkStatusBarManager
    public void userActivityNoUpdateLocked() {
        this.f56a.userActivityNoUpdateLocked();
    }
}
