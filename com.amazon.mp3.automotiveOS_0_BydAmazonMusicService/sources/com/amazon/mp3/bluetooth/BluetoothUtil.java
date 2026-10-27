package com.amazon.mp3.bluetooth;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import androidx.annotation.VisibleForTesting;
import com.amazon.identity.auth.device.api.MultipleAccountManager;
import com.amazon.mp3.activity.GrantPermissionActivity;
import com.amazon.mp3.automotiveOS.R;
import com.amazon.mp3.init.CarDetectionLaunchInitializationTask;
import com.amazon.mp3.init.InitializationHandler;
import com.amazon.mp3.util.extensions.ContextKt;
import kotlin.Metadata;
import kotlin.jvm.JvmStatic;
import kotlin.jvm.internal.Intrinsics;

/* JADX INFO: compiled from: BluetoothUtil.kt */
/* JADX INFO: loaded from: classes3.dex */
@Metadata(bv = {}, d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u000f\n\u0002\u0010\u000e\n\u0002\b\u0007\bÆ\u0002\u0018\u00002\u00020\u0001B\t\b\u0002¢\u0006\u0004\b#\u0010$J\u0018\u0010\u0007\u001a\u00020\u00062\u0006\u0010\u0003\u001a\u00020\u00022\u0006\u0010\u0005\u001a\u00020\u0004H\u0007J\u0018\u0010\n\u001a\u00020\u00062\u0006\u0010\u0003\u001a\u00020\u00022\u0006\u0010\t\u001a\u00020\bH\u0002J \u0010\u0010\u001a\u00020\u00062\u0006\u0010\f\u001a\u00020\u000b2\u0006\u0010\r\u001a\u00020\b2\b\b\u0002\u0010\u000f\u001a\u00020\u000eJ \u0010\u0011\u001a\u00020\u00062\u0006\u0010\f\u001a\u00020\u000b2\u0006\u0010\r\u001a\u00020\b2\b\b\u0002\u0010\u000f\u001a\u00020\u000eJ\u0006\u0010\u0012\u001a\u00020\u0006J\u0018\u0010\u0013\u001a\u00020\u00042\u0006\u0010\u0003\u001a\u00020\u00022\u0006\u0010\u000f\u001a\u00020\u000eH\u0007J\u0010\u0010\u0014\u001a\u00020\u00042\u0006\u0010\u0003\u001a\u00020\u0002H\u0007J\u0016\u0010\u0016\u001a\u00020\u00062\u0006\u0010\u0003\u001a\u00020\u00022\u0006\u0010\u0015\u001a\u00020\u0004J\u000e\u0010\u0017\u001a\u00020\u00042\u0006\u0010\u0003\u001a\u00020\u0002R\u001a\u0010\u0018\u001a\u00020\u000e8\u0006X\u0086D¢\u0006\f\n\u0004\b\u0018\u0010\u0019\u001a\u0004\b\u001a\u0010\u001bR\u001a\u0010\u001c\u001a\u00020\u000e8\u0006X\u0086D¢\u0006\f\n\u0004\b\u001c\u0010\u0019\u001a\u0004\b\u001d\u0010\u001bR\u0017\u0010\u001f\u001a\u00020\u001e8\u0006¢\u0006\f\n\u0004\b\u001f\u0010 \u001a\u0004\b!\u0010\"¨\u0006%"}, d2 = {"Lcom/amazon/mp3/bluetooth/BluetoothUtil;", "", "Landroid/content/Context;", "context", "", "enabled", "", "syncBTPermissionPref", "Lcom/amazon/mp3/activity/GrantPermissionActivity$PermissionResultReceiver;", "receiver", "startBluetoothPermissionsActivity", "Landroid/app/Activity;", MultipleAccountManager.SessionPackageMappingType.JSON_KEY_SESSION_PACKAGE_MAPPING_REMOVE_ACTIVITY_CLASS_NAME, "resultReceiver", "", "buildVersion", "initCarDetection", "enableAutoCarModeWithBTPermission", "startCarDetection", "shouldCheckPermissions", "shouldShowBTDialogOnStartup", "requested", "setBTPermissionFirstTimeRequested", "isFirstTimeRequestingPermission", "allGrantedResult", "I", "getAllGrantedResult", "()I", "notGrantedResult", "getNotGrantedResult", "", "SHARED_PREF_NAME", "Ljava/lang/String;", "getSHARED_PREF_NAME", "()Ljava/lang/String;", "<init>", "()V", "DigitalMusicAndroid3P_automotiveProdRelease"}, k = 1, mv = {1, 6, 0})
public final class BluetoothUtil {
    private static final int notGrantedResult = 0;
    public static final BluetoothUtil INSTANCE = new BluetoothUtil();
    private static final int allGrantedResult = 1;
    private static final String SHARED_PREF_NAME = Intrinsics.stringPlus("com.amazon.mp3_", BluetoothPermissionActivity.class.getSimpleName());

    private BluetoothUtil() {
    }

    public final int getAllGrantedResult() {
        return allGrantedResult;
    }

    public final int getNotGrantedResult() {
        return notGrantedResult;
    }

    @JvmStatic
    public static final void syncBTPermissionPref(Context context, boolean enabled) {
        Intrinsics.checkNotNullParameter(context, "context");
        SharedPreferences.Editor editorEdit = PreferenceManager.getDefaultSharedPreferences(context).edit();
        editorEdit.putBoolean(context.getString(R.string.setting_car_mode_launch_automatically), enabled);
        editorEdit.apply();
    }

    public final void initCarDetection(Activity activity, GrantPermissionActivity.PermissionResultReceiver resultReceiver, int buildVersion) {
        Intrinsics.checkNotNullParameter(activity, "activity");
        Intrinsics.checkNotNullParameter(resultReceiver, "resultReceiver");
        if (shouldCheckPermissions(activity, buildVersion)) {
            if (shouldShowBTDialogOnStartup(activity)) {
                syncBTPermissionPref(activity, false);
                startBluetoothPermissionsActivity(activity, resultReceiver);
                return;
            }
            return;
        }
        resultReceiver.send(allGrantedResult, new Bundle());
    }

    public final void enableAutoCarModeWithBTPermission(Activity activity, GrantPermissionActivity.PermissionResultReceiver resultReceiver, int buildVersion) {
        Intrinsics.checkNotNullParameter(activity, "activity");
        Intrinsics.checkNotNullParameter(resultReceiver, "resultReceiver");
        if (shouldCheckPermissions(activity, buildVersion)) {
            startBluetoothPermissionsActivity(activity, resultReceiver);
        } else {
            resultReceiver.send(allGrantedResult, new Bundle());
        }
    }

    public final void startCarDetection() {
        new InitializationHandler(new CarDetectionLaunchInitializationTask()).initializeOnNewThread();
    }

    private final void startBluetoothPermissionsActivity(Context context, GrantPermissionActivity.PermissionResultReceiver receiver) {
        Intent intent = new Intent(context, (Class<?>) BluetoothPermissionActivity.class);
        intent.putExtra(GrantPermissionActivity.INTENT_EXTRA_RESULT_CALLBACK, receiver);
        context.startActivity(intent);
    }

    @VisibleForTesting(otherwise = 2)
    public final boolean shouldCheckPermissions(Context context, int buildVersion) {
        Intrinsics.checkNotNullParameter(context, "context");
        return buildVersion >= 31 && !ContextKt.isPermissionGranted(context, "android.permission.BLUETOOTH_CONNECT");
    }

    @VisibleForTesting(otherwise = 2)
    public final boolean shouldShowBTDialogOnStartup(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        return isFirstTimeRequestingPermission(context);
    }

    public final void setBTPermissionFirstTimeRequested(Context context, boolean requested) {
        Intrinsics.checkNotNullParameter(context, "context");
        SharedPreferences.Editor editorEdit = context.getSharedPreferences(SHARED_PREF_NAME, 0).edit();
        editorEdit.putBoolean("bt_permission_requested", requested);
        editorEdit.apply();
    }

    public final boolean isFirstTimeRequestingPermission(Context context) {
        Intrinsics.checkNotNullParameter(context, "context");
        return context.getSharedPreferences(SHARED_PREF_NAME, 0).getBoolean("bt_permission_requested", true);
    }
}
