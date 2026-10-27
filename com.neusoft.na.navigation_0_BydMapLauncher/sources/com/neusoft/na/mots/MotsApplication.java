package com.neusoft.na.mots;

import android.content.Context;
import android.content.SharedPreferences;
import android.hardware.bydauto.BYDAutoFeatureIds;
import android.util.Log;
import android.webkit.WebView;
import android.widget.Toast;
import androidx.multidex.MultiDex;
import androidx.multidex.MultiDexApplication;
import com.neusoft.na.common.presentation.IMapPresentationComponent;
import com.neusoft.na.common.util.MyAppCrashHandler;
import com.neusoft.na.ocservice.util.CheckBoardKt;
import com.neusoft.slf4j.dlt.DLTSocketController;
import com.neusoft.slf4j.dlt.LogConfig;
import kotlin.Metadata;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: MotsApplication.kt */
/* JADX INFO: loaded from: classes2.dex */
@Metadata(d1 = {"\u0000D\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0006\n\u0002\u0010\u000e\n\u0002\b\u0007\u0018\u0000  2\u00020\u0001:\u0001 B\u0005¢\u0006\u0002\u0010\u0002J\u0006\u0010\f\u001a\u00020\rJ\u0006\u0010\u000e\u001a\u00020\rJ\u0012\u0010\u000f\u001a\u00020\r2\b\u0010\u0010\u001a\u0004\u0018\u00010\u0011H\u0014J\u0006\u0010\u0012\u001a\u00020\u0013J\u0006\u0010\u0014\u001a\u00020\u0013J\b\u0010\u0015\u001a\u00020\rH\u0003J\u0006\u0010\u0016\u001a\u00020\rJ\u0006\u0010\u0017\u001a\u00020\rJ\u0010\u0010\u0018\u001a\u00020\u00042\u0006\u0010\u0019\u001a\u00020\u001aH\u0002J\b\u0010\u001b\u001a\u0004\u0018\u00010\nJ\b\u0010\u001c\u001a\u00020\bH\u0002J\b\u0010\u001d\u001a\u00020\rH\u0016J\u0010\u0010\u001e\u001a\u00020\r2\b\u0010\t\u001a\u0004\u0018\u00010\nJ\u0006\u0010\u001f\u001a\u00020\rR\u000e\u0010\u0003\u001a\u00020\u0004X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0005\u001a\u0004\u0018\u00010\u0006X\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\u0007\u001a\u0004\u0018\u00010\bX\u0082\u000e¢\u0006\u0002\n\u0000R\u0010\u0010\t\u001a\u0004\u0018\u00010\nX\u0082\u000e¢\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\u0004X\u0082\u000e¢\u0006\u0002\n\u0000¨\u0006!"}, d2 = {"Lcom/neusoft/na/mots/MotsApplication;", "Landroidx/multidex/MultiDexApplication;", "()V", "createCount", "", "mMyAppCrashHandler", "Lcom/neusoft/na/common/util/MyAppCrashHandler;", "mToast", "Landroid/widget/Toast;", "mapPresentationComponent", "Lcom/neusoft/na/common/presentation/IMapPresentationComponent;", "startCount", "addCreateCount", "", "addStartCount", "attachBaseContext", "base", "Landroid/content/Context;", "cannotDestroyProcess", "", "cannotStopProcess", "createDemoConfig", "delCreateCount", "delStartCount", "getDimenId", "resName", "", "getMapPresentationComponent", "getToast", "onCreate", "setMapPresentationComponent", "showToast", "Companion", "BYD8155FNav-9.25.21.1a-G_NEU_release"}, k = 1, mv = {1, 7, 1}, xi = 48)
public final class MotsApplication extends MultiDexApplication {
    public static final String TAG = "MotsApplication";
    private int createCount;
    private MyAppCrashHandler mMyAppCrashHandler;
    private Toast mToast;
    private IMapPresentationComponent mapPresentationComponent;
    private int startCount;

    @Override // androidx.multidex.MultiDexApplication, android.content.ContextWrapper
    protected void attachBaseContext(Context base) {
        super.attachBaseContext(base);
        MultiDex.install(this);
        try {
            if (CheckBoardKt.isGreatwallV61()) {
                String processName = MultiDexApplication.getProcessName();
                if (processName == null) {
                    processName = "";
                }
                if (Intrinsics.areEqual(processName, getPackageName())) {
                    return;
                }
                WebView.setDataDirectorySuffix(processName);
                Log.i(MotsMainBinder.TAG, "WebView: set name done");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public final void addCreateCount() {
        this.createCount++;
        Log.i(TAG, "addCreateCount: " + this.createCount);
    }

    public final void delCreateCount() {
        this.createCount--;
        Log.i(TAG, "delCreateCount: " + this.createCount);
    }

    public final boolean cannotDestroyProcess() {
        Log.i(TAG, "cannotDestroyProcess: " + this.createCount);
        return this.createCount > 0;
    }

    public final void addStartCount() {
        this.startCount++;
        Log.i(TAG, "addStartCount: " + this.startCount);
    }

    public final void delStartCount() {
        this.startCount--;
        Log.i(TAG, "delStartCount: " + this.startCount);
    }

    public final boolean cannotStopProcess() {
        Log.i(TAG, "cannotStopProcess: " + this.startCount);
        return this.startCount > 0;
    }

    public final void setMapPresentationComponent(IMapPresentationComponent mapPresentationComponent) {
        Log.i(TAG, "[setMapPresentationComponent] mapPresentationComponent == " + mapPresentationComponent);
        if (mapPresentationComponent != null) {
            Log.i(TAG, "mapPresentationComponent hashCode == " + mapPresentationComponent.hashCode());
        }
        this.mapPresentationComponent = mapPresentationComponent;
    }

    public final IMapPresentationComponent getMapPresentationComponent() {
        Log.i(TAG, "[getMapPresentationComponent] mapPresentationComponent == " + this.mapPresentationComponent);
        IMapPresentationComponent iMapPresentationComponent = this.mapPresentationComponent;
        if (iMapPresentationComponent != null) {
            Log.i(TAG, "mapPresentationComponent hashCode == " + iMapPresentationComponent.hashCode());
        }
        return this.mapPresentationComponent;
    }

    @Override // android.app.Application
    public void onCreate() {
        super.onCreate();
        Log.i(TAG, "onCreate");
        if (CheckBoardKt.isSmart() || CheckBoardKt.isZeekr()) {
            createDemoConfig();
        }
        if (CheckBoardKt.isByd8155f()) {
            this.mMyAppCrashHandler = new MyAppCrashHandler(this);
        } else {
            LogConfig.init(getResources().getXml(com.neusoft.na.navigation.R.xml.log_config));
            DLTSocketController.init("127.0.0.1", BYDAutoFeatureIds.SETTING_VEHICLE_REFRIGERATOR_DELAY_POWER_OUTAGE, "AND ", "MOTS", "CTXT");
        }
    }

    private final void createDemoConfig() {
        SharedPreferences sharedPreferences = getSharedPreferences("SmartDemoConfig", 0);
        if (sharedPreferences.getInt("demoFlag", -1) == -1) {
            sharedPreferences.edit().putInt("demoFlag", 0).commit();
        }
    }

    private final Toast getToast() {
        String string = getString(getResources().getIdentifier("launcher_change", "string", getPackageName()));
        Intrinsics.checkNotNullExpressionValue(string, "getString(identifier)");
        Toast toast = Toast.makeText(this, string, 0);
        Intrinsics.checkNotNullExpressionValue(toast, "toast");
        return toast;
    }

    public final void showToast() {
        Toast toast = this.mToast;
        if (toast != null) {
            toast.cancel();
        }
        Toast toast2 = getToast();
        this.mToast = toast2;
        if (toast2 != null) {
            toast2.show();
        }
    }

    private final int getDimenId(String resName) {
        return getResources().getIdentifier(resName, "dimen", getPackageName());
    }
}
