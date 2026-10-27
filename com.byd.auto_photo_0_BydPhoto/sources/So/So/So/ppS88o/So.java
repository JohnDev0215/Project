package So.So.So.ppS88o;

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
@Platform(versions = {DiLink.VERSION_NAME_FSE_RUI, DiLink.VERSION_NAME_FSE_5_0, DiLink.VERSION_NAME_FSE_5_1})
@ServiceImpl(service = IDiLinkStatusBarManager.class, singleton = true)
public class So implements IDiLinkStatusBarManager {

    /* JADX INFO: renamed from: a, reason: collision with root package name */
    private StatusBarManager f164a;

    @SuppressLint({"WrongConstant"})
    public So(Context context) {
        this.f164a = (StatusBarManager) context.getSystemService("statusbar");
    }

    @Override // com.byd.dilink.statusbar.IDiLinkStatusBarManager
    public boolean closeAppWhenMultiWindow(@NonNull String str, int i) {
        throw new UnsupportedTargetPlatformException("FSE 没有这个函数！");
    }

    @Override // com.byd.dilink.statusbar.IDiLinkStatusBarManager
    public void collapsePanels() {
        this.f164a.collapsePanels();
    }

    @Override // com.byd.dilink.statusbar.IDiLinkStatusBarManager
    public void enterSplitScreenModeByLauncher(String str, Intent intent) {
        throw new UnsupportedTargetPlatformException("FSE 没有 enterSplitScreenModeByLaucher 这个函数！");
    }

    @Override // com.byd.dilink.statusbar.IDiLinkStatusBarManager
    public void enterSplitScreenModeByLauncher(String str, String str2) {
        throw new UnsupportedTargetPlatformException("FSE 没有 enterSplitScreenModeByLaucher 这个函数！");
    }

    @Override // com.byd.dilink.statusbar.IDiLinkStatusBarManager
    public String getAppPkgByDirection(boolean z) {
        throw new UnsupportedTargetPlatformException("FSE 没有这个函数！");
    }

    @Override // com.byd.dilink.statusbar.IDiLinkStatusBarManager
    public int getSplitScreenStatus() {
        throw new UnsupportedTargetPlatformException("FSE 没有这个函数！");
    }

    @Override // com.byd.dilink.statusbar.IDiLinkStatusBarManager
    public void handleHomeButton() {
        throw new UnsupportedTargetPlatformException("FSE 没有 handleHomeButton 这个函数！");
    }

    @Override // com.byd.dilink.statusbar.IDiLinkStatusBarManager
    public int isAppSupportSplit(@NonNull String str) {
        throw new UnsupportedTargetPlatformException("FSE 没有这个函数！");
    }

    @Override // com.byd.dilink.statusbar.IDiLinkStatusBarManager
    public boolean isDockState() {
        throw new UnsupportedTargetPlatformException("FSE 没有这个函数！");
    }

    @Override // com.byd.dilink.statusbar.IDiLinkStatusBarManager
    public boolean isFocusInPrimary() {
        throw new UnsupportedTargetPlatformException("FSE 没有这个函数！");
    }

    @Override // com.byd.dilink.statusbar.IDiLinkStatusBarManager
    public boolean isRunningTaskSupportsMultiWindow() {
        throw new UnsupportedTargetPlatformException("FSE 没有这个函数！");
    }

    @Override // com.byd.dilink.statusbar.IDiLinkStatusBarManager
    public boolean isSwapSplitScreenUsable() {
        throw new UnsupportedTargetPlatformException("FSE 没有这个函数！");
    }

    @Override // com.byd.dilink.statusbar.IDiLinkStatusBarManager
    public boolean moveAppToFullScreenByPkg(@NonNull String str) {
        throw new UnsupportedTargetPlatformException("FSE 没有这个函数！");
    }

    @Override // com.byd.dilink.statusbar.IDiLinkStatusBarManager
    public void notifyDockedStackExistsChanged(boolean z) {
        throw new UnsupportedTargetPlatformException("FSE 没有这个函数！");
    }

    @Override // com.byd.dilink.statusbar.IDiLinkStatusBarManager
    public void onSteeringWheelRotationClick() {
        throw new UnsupportedTargetPlatformException("FSE 没有 onSteeringWheelRotationClick 这个函数！");
    }

    @Override // com.byd.dilink.statusbar.IDiLinkStatusBarManager
    public void registerCardListener(IDiLinkCardListener iDiLinkCardListener) {
        throw new UnsupportedTargetPlatformException("FSE 没有 registerCardListener 这个函数！");
    }

    @Override // com.byd.dilink.statusbar.IDiLinkStatusBarManager
    public void registerSceneModeCallback(@NonNull IDiLinkSceneModeStateCallback iDiLinkSceneModeStateCallback) {
        throw new UnsupportedTargetPlatformException("FSE 没有这个函数！");
    }

    @Override // com.byd.dilink.statusbar.IDiLinkStatusBarManager
    public void registerSplitScreenCallBack(@NonNull IDiLinkSplitScreenCallBack iDiLinkSplitScreenCallBack) {
        throw new UnsupportedTargetPlatformException("FSE 没有这个函数！");
    }

    @Override // com.byd.dilink.statusbar.IDiLinkStatusBarManager
    public void setActivityNameForStatusAndNavigationBar(@NonNull String str) {
        this.f164a.setActivityNameForStatusAndNavigaitonBar(str);
    }

    @Override // com.byd.dilink.statusbar.IDiLinkStatusBarManager
    public void setActivityNameForStatusAndNavigationBar(@NonNull String str, int i) {
        throw new UnsupportedTargetPlatformException("FSE 没有 setActivityNameForStatusAndNavigationBar(@NonNull String name, int displayId)方法！");
    }

    @Override // com.byd.dilink.statusbar.IDiLinkStatusBarManager
    public void setMultiWindow() {
        throw new UnsupportedTargetPlatformException("FSE 没有这个函数！");
    }

    @Override // com.byd.dilink.statusbar.IDiLinkStatusBarManager
    public void setNewFocusStatusForDivider() {
        throw new UnsupportedTargetPlatformException("FSE 没有这个函数！");
    }

    @Override // com.byd.dilink.statusbar.IDiLinkStatusBarManager
    public void setPackageNameForStatusAndNavigationBar(@NonNull String str) {
        this.f164a.setPackageNameForStatusAndNaviationBar(str);
    }

    @Override // com.byd.dilink.statusbar.IDiLinkStatusBarManager
    public void setSceneMode(int i, int i2, int i3) {
        this.f164a.setSceneMode(i, i2, i3);
    }

    @Override // com.byd.dilink.statusbar.IDiLinkStatusBarManager
    public void setSceneModeCallbackContent(String str) {
        this.f164a.setSceneModeCallbackContent(str);
    }

    @Override // com.byd.dilink.statusbar.IDiLinkStatusBarManager
    public void setSplitScreenMode(boolean z) {
        throw new UnsupportedTargetPlatformException("FSE 没有这个函数！");
    }

    @Override // com.byd.dilink.statusbar.IDiLinkStatusBarManager
    public int startActivityWhenMultiWindow(@NonNull String str, @NonNull Intent intent, int i) {
        throw new UnsupportedTargetPlatformException("FSE 没有这个函数！");
    }

    @Override // com.byd.dilink.statusbar.IDiLinkStatusBarManager
    public void swapSplitScreenWindow() {
        throw new UnsupportedTargetPlatformException("FSE 没有这个函数！");
    }

    @Override // com.byd.dilink.statusbar.IDiLinkStatusBarManager
    public void toggleSplitScreen() {
        throw new UnsupportedTargetPlatformException("FSE 没有 toggleSplitScreen 这个函数！");
    }

    @Override // com.byd.dilink.statusbar.IDiLinkStatusBarManager
    public void unregisterCardListener(IDiLinkCardListener iDiLinkCardListener) {
        throw new UnsupportedTargetPlatformException("FSE 没有 unregisterCardListener 这个函数！");
    }

    @Override // com.byd.dilink.statusbar.IDiLinkStatusBarManager
    public void unregisterSceneModeCallback(@NonNull IDiLinkSceneModeStateCallback iDiLinkSceneModeStateCallback) {
        throw new UnsupportedTargetPlatformException("FSE 没有这个函数！");
    }

    @Override // com.byd.dilink.statusbar.IDiLinkStatusBarManager
    public void unregisterSplitScreenCallBack(@NonNull IDiLinkSplitScreenCallBack iDiLinkSplitScreenCallBack) {
        throw new UnsupportedTargetPlatformException("FSE 没有这个函数！");
    }

    @Override // com.byd.dilink.statusbar.IDiLinkStatusBarManager
    public void userActivityNoUpdateLocked() {
        this.f164a.userActivityNoUpdateLocked();
    }
}
