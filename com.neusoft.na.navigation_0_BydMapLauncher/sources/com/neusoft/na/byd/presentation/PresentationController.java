package com.neusoft.na.byd.presentation;

import android.content.Context;
import android.graphics.SurfaceTexture;
import android.hardware.display.DisplayManager;
import android.util.Log;
import android.view.Display;
import androidx.core.hardware.display.DisplayManagerCompat;
import cerence.ark.assistant.controller.domain.hmi.HMIRequestsEnum;
import com.neusoft.na.common.presentation.IMapPresentationComponent;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: PresentationController.kt */
/* JADX INFO: loaded from: classes.dex */
@Metadata(d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\n\u0018\u0000 \u001c2\u00020\u0001:\u0001\u001cB\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003¢\u0006\u0002\u0010\u0004J\b\u0010\u0007\u001a\u00020\bH\u0016J\n\u0010\t\u001a\u0004\u0018\u00010\nH\u0016J\b\u0010\u000b\u001a\u00020\bH\u0016J\b\u0010\f\u001a\u00020\bH\u0016J\b\u0010\r\u001a\u00020\bH\u0002J\b\u0010\u000e\u001a\u00020\bH\u0016J\b\u0010\u000f\u001a\u00020\u0010H\u0016J\u0010\u0010\u0011\u001a\u00020\b2\u0006\u0010\u0012\u001a\u00020\u0013H\u0016J\u0010\u0010\u0014\u001a\u00020\b2\u0006\u0010\u0015\u001a\u00020\u0013H\u0016J\u0018\u0010\u0016\u001a\u00020\b2\u0006\u0010\u0017\u001a\u00020\u00102\u0006\u0010\u0018\u001a\u00020\u0010H\u0016J\u0010\u0010\u0019\u001a\u00020\b2\u0006\u0010\u001a\u001a\u00020\u0013H\u0016J\b\u0010\u001b\u001a\u00020\bH\u0016R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004¢\u0006\u0002\n\u0000R\u0010\u0010\u0005\u001a\u0004\u0018\u00010\u0006X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006\u001d"}, d2 = {"Lcom/neusoft/na/byd/presentation/PresentationController;", "Lcom/neusoft/na/common/presentation/IMapPresentationComponent;", "context", "Landroid/content/Context;", "(Landroid/content/Context;)V", "mapPresentationView", "Lcom/neusoft/na/byd/presentation/MapPresentationView;", "closePresentation", "", "getSurfaceTexture", "Landroid/graphics/SurfaceTexture;", "hidePresentation", "init", "initPresentation", "initSystemInterface", "isShowingPresentation", "", "onTestINSNavDisplayMode", "screen", "", "onTestMapFocusChanged", "mapType", "setActivationPrivacyPolicyStatus", "activationStatus", "privacyPolicyStatus", "setDayNightModeChange", "dayNightMode", "showPresentation", "Companion", "byd8155f_release"}, k = 1, mv = {1, 7, 1}, xi = 48)
public final class PresentationController implements IMapPresentationComponent {
    private static final String TAG = "PresentationController";
    private final Context context;
    private MapPresentationView mapPresentationView;

    public PresentationController(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        this.context = context;
    }

    private final void initPresentation() {
        Log.i(TAG, "[initPresentation]");
        Object systemService = this.context.getSystemService(HMIRequestsEnum.DISPLAY);
        Intrinsics.checkNotNull(systemService, "null cannot be cast to non-null type android.hardware.display.DisplayManager");
        Display[] displays = ((DisplayManager) systemService).getDisplays(DisplayManagerCompat.DISPLAY_CATEGORY_PRESENTATION);
        int length = displays != null ? displays.length : 0;
        Log.i(TAG, "displays size == " + length);
        if (length > 0) {
            if (this.mapPresentationView == null) {
                Context context = this.context;
                Display display = displays[0];
                Intrinsics.checkNotNullExpressionValue(display, "displays[0]");
                this.mapPresentationView = new MapPresentationView(context, display);
                StringBuilder sb = new StringBuilder("MapPresentationView instance. ");
                MapPresentationView mapPresentationView = this.mapPresentationView;
                Intrinsics.checkNotNull(mapPresentationView);
                Log.i(TAG, sb.append(mapPresentationView.hashCode()).toString());
                MapPresentationView mapPresentationView2 = this.mapPresentationView;
                if (mapPresentationView2 != null) {
                    mapPresentationView2.initSystemInterface();
                    return;
                }
                return;
            }
            Log.i(TAG, "mapPresentationView init already, do not init again!");
            return;
        }
        Log.e(TAG, "displays size <= 0, can not show map presentation!");
    }

    @Override // com.neusoft.na.common.presentation.IMapPresentationComponent
    public void initSystemInterface() {
        Log.i(TAG, "[initSystemInterface]");
        initPresentation();
    }

    @Override // com.neusoft.na.common.presentation.IMapPresentationComponent
    public void init() {
        Log.i(TAG, "[init]");
        MapPresentationView mapPresentationView = this.mapPresentationView;
        if (mapPresentationView != null) {
            mapPresentationView.init();
        }
    }

    @Override // com.neusoft.na.common.presentation.IMapPresentationComponent
    public void showPresentation() {
        Log.i(TAG, "[showPresentation]");
        MapPresentationView mapPresentationView = this.mapPresentationView;
        if (mapPresentationView != null) {
            mapPresentationView.showPresentation();
        }
    }

    @Override // com.neusoft.na.common.presentation.IMapPresentationComponent
    public void hidePresentation() {
        Log.i(TAG, "[hidePresentation]");
        MapPresentationView mapPresentationView = this.mapPresentationView;
        if (mapPresentationView != null) {
            mapPresentationView.hidePresentation();
        }
    }

    @Override // com.neusoft.na.common.presentation.IMapPresentationComponent
    public boolean isShowingPresentation() {
        Log.i(TAG, "[isShowingPresentation]");
        MapPresentationView mapPresentationView = this.mapPresentationView;
        if (mapPresentationView != null) {
            return mapPresentationView.isShowing();
        }
        return false;
    }

    @Override // com.neusoft.na.common.presentation.IMapPresentationComponent
    public SurfaceTexture getSurfaceTexture() {
        Log.i(TAG, "[getSurfaceTexture]");
        MapPresentationView mapPresentationView = this.mapPresentationView;
        if (mapPresentationView != null) {
            return mapPresentationView.getSurfaceTexture();
        }
        return null;
    }

    @Override // com.neusoft.na.common.presentation.IMapPresentationComponent
    public void setDayNightModeChange(int dayNightMode) {
        Log.i(TAG, "[setDayNightModeChange] --> dayNightMode == " + dayNightMode);
        MapPresentationView mapPresentationView = this.mapPresentationView;
        if (mapPresentationView != null) {
            mapPresentationView.setDayNightModeChange(dayNightMode);
        }
    }

    @Override // com.neusoft.na.common.presentation.IMapPresentationComponent
    public void setActivationPrivacyPolicyStatus(boolean activationStatus, boolean privacyPolicyStatus) {
        Log.i(TAG, "[setActivationPrivacyPolicyStatus] --> activationStatus == " + activationStatus + ", privacyPolicyStatus == " + privacyPolicyStatus);
        MapPresentationView mapPresentationView = this.mapPresentationView;
        if (mapPresentationView != null) {
            mapPresentationView.setActivationPrivacyPolicyStatus(activationStatus, privacyPolicyStatus);
        }
    }

    @Override // com.neusoft.na.common.presentation.IMapPresentationComponent
    public void closePresentation() {
        Log.i(TAG, "[closePresentation]");
        MapPresentationView mapPresentationView = this.mapPresentationView;
        if (mapPresentationView != null) {
            mapPresentationView.closePresentation();
        }
    }

    @Override // com.neusoft.na.common.presentation.IMapPresentationComponent
    public void onTestINSNavDisplayMode(int screen) {
        Log.i(TAG, "[onTestINSNavDisplayMode] --> screen == " + screen);
        MapPresentationView mapPresentationView = this.mapPresentationView;
        if (mapPresentationView != null) {
            mapPresentationView.onINSNavDisplayMode(screen);
        }
    }

    @Override // com.neusoft.na.common.presentation.IMapPresentationComponent
    public void onTestMapFocusChanged(int mapType) {
        Log.i(TAG, "[onTestMapFocusChanged] --> mapType == " + mapType);
        MapPresentationView mapPresentationView = this.mapPresentationView;
        if (mapPresentationView != null) {
            mapPresentationView.onTestMapFocusChanged(mapType);
        }
    }
}
