package ts.car.service.multidisplay;

import android.content.Intent;
import android.os.Bundle;
import android.os.RemoteException;
import android.view.View;
import android.view.WindowManager;
import ts.car.service.IPlatformService;
import ts.car.service.multidisplay.DisplayConstant;

/* JADX INFO: loaded from: classes2.dex */
public interface IMultiDisplayManager extends IPlatformService {
    Bundle getDisplayBundle(DisplayConstant.DisplayType displayType) throws RemoteException;

    WindowManager getDisplayWindowManager(DisplayConstant.DisplayType displayType) throws RemoteException;

    boolean hideWindowFromDesignatedDisplay(DisplayConstant.DisplayType displayType, View view) throws RemoteException;

    boolean showWindowToDesignatedDisplay(DisplayConstant.DisplayType displayType, View view, WindowManager.LayoutParams layoutParams) throws RemoteException;

    boolean startActivityToDesignatedDisplay(DisplayConstant.DisplayType displayType, Intent intent) throws RemoteException;
}
