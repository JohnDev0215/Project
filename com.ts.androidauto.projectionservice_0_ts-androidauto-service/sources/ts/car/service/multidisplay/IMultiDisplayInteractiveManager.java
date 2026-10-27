package ts.car.service.multidisplay;

import android.app.ActivityManager;
import android.content.Intent;
import android.os.Bundle;
import android.os.RemoteException;
import android.os.UserHandle;
import android.view.Surface;
import android.view.SurfaceControl;
import android.view.View;
import android.view.WindowManager;
import ts.car.service.IPlatformService;
import ts.car.service.multidisplay.DisplayConstant;

/* JADX INFO: loaded from: classes2.dex */
public interface IMultiDisplayInteractiveManager extends IPlatformService {
    void addMirrorViewStateCallback(IShareStateCallbackClient iShareStateCallbackClient) throws RemoteException;

    Surface createPersistenceSurface() throws RemoteException;

    int createVirtualDisplay(int i, int i2, int i3) throws RemoteException;

    boolean getConnectToServiceReady() throws RemoteException;

    Bundle getDisplayBundle(DisplayConstant.DisplayType displayType) throws RemoteException;

    DisplayConstant.DisplayType getDisplayTypeByDisplayId(int i);

    WindowManager getDisplayWindowManager(DisplayConstant.DisplayType displayType) throws RemoteException;

    MirrorViewClient getMirrorView(int i) throws RemoteException;

    boolean getMirrorViewShown(int i) throws RemoteException;

    ThumbnailData getThumbnailData(ActivityManager.RecentTaskInfo recentTaskInfo) throws RemoteException;

    boolean hideWindowFromDesignatedDisplay(DisplayConstant.DisplayType displayType, View view) throws RemoteException;

    void mainDisplayExit(String str, int i, int i2, int i3) throws RemoteException;

    boolean moveOneselfToDesignatedDisplay(int i, DisplayConstant.DisplayType displayType) throws RemoteException;

    boolean moveRecentTaskToDesignatedDisplay(ActivityManager.RecentTaskInfo recentTaskInfo, DisplayConstant.DisplayType displayType) throws RemoteException;

    boolean moveTopToDesignatedDisplay(int i, int i2) throws RemoteException;

    void registerShareWindowListener(IShareWindowListener iShareWindowListener);

    void relatedSurfaceControl(SurfaceControl surfaceControl, int i, int i2) throws RemoteException;

    void releaseRelated() throws RemoteException;

    void releaseSurface() throws RemoteException;

    void releaseVirtualDisplay(int i) throws RemoteException;

    void removeMirrorStateCallback(int i, IMirrorStateCallbackClient iMirrorStateCallbackClient) throws RemoteException;

    void removeMirrorViewStateCallback(IShareStateCallbackClient iShareStateCallbackClient) throws RemoteException;

    void setMirrorStateCallback(int i, IMirrorStateCallbackClient iMirrorStateCallbackClient) throws RemoteException;

    void setMultiDisplayCallback(IMultiDisplayCallback iMultiDisplayCallback) throws RemoteException;

    boolean shareOneselfToDev(int i, int i2, int i3, int i4) throws RemoteException;

    void shareWindow() throws RemoteException;

    boolean showWindowToDesignatedDisplay(DisplayConstant.DisplayType displayType, View view, WindowManager.LayoutParams layoutParams) throws RemoteException;

    boolean startActivityToDesignatedDisplay(DisplayConstant.DisplayType displayType, Intent intent);

    boolean startActivityToDesignatedDisplayAsUser(DisplayConstant.DisplayType displayType, Intent intent, UserHandle userHandle);

    void startMirrorDisplay(int i, int i2) throws RemoteException;

    void startShareApp(String str, String str2, int i) throws RemoteException;

    void unregisterShareWindowListener(IShareWindowListener iShareWindowListener);
}
