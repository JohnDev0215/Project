package com.blankj.utilcode.util;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.Settings;
import android.util.Log;
import android.view.MotionEvent;
import androidx.core.content.ContextCompat;
import com.blankj.utilcode.constant.PermissionConstants;
import com.blankj.utilcode.util.Utils;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

/* JADX INFO: loaded from: classes4.dex */
public final class PermissionUtils {
    private static final List<String> PERMISSIONS = getPermissions();
    private static PermissionUtils sInstance;
    private static SimpleCallback sSimpleCallback4DrawOverlays;
    private static SimpleCallback sSimpleCallback4WriteSettings;
    private FullCallback mFullCallback;
    private OnRationaleListener mOnRationaleListener;
    private Set<String> mPermissions = new LinkedHashSet();
    private List<String> mPermissionsDenied;
    private List<String> mPermissionsDeniedForever;
    private List<String> mPermissionsGranted;
    private List<String> mPermissionsRequest;
    private SimpleCallback mSimpleCallback;
    private ThemeCallback mThemeCallback;

    public interface FullCallback {
        void onDenied(List<String> list, List<String> list2);

        void onGranted(List<String> list);
    }

    public interface OnRationaleListener {

        public interface ShouldRequest {
            void again(boolean z);
        }

        void rationale(ShouldRequest shouldRequest);
    }

    public interface SimpleCallback {
        void onDenied();

        void onGranted();
    }

    public interface ThemeCallback {
        void onActivityCreate(Activity activity);
    }

    public static List<String> getPermissions() {
        return getPermissions(Utils.getApp().getPackageName());
    }

    public static List<String> getPermissions(String str) {
        try {
            String[] strArr = Utils.getApp().getPackageManager().getPackageInfo(str, 4096).requestedPermissions;
            if (strArr == null) {
                return Collections.emptyList();
            }
            return Arrays.asList(strArr);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
            return Collections.emptyList();
        }
    }

    public static boolean isGranted(String... strArr) {
        for (String str : strArr) {
            if (!isGranted(str)) {
                return false;
            }
        }
        return true;
    }

    private static boolean isGranted(String str) {
        return Build.VERSION.SDK_INT < 23 || ContextCompat.checkSelfPermission(Utils.getApp(), str) == 0;
    }

    public static boolean isGrantedWriteSettings() {
        return Settings.System.canWrite(Utils.getApp());
    }

    public static void requestWriteSettings(SimpleCallback simpleCallback) {
        if (!isGrantedWriteSettings()) {
            sSimpleCallback4WriteSettings = simpleCallback;
            PermissionActivityImpl.start(2);
        } else if (simpleCallback != null) {
            simpleCallback.onGranted();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void startWriteSettingsActivity(Activity activity, int i) {
        Intent intent = new Intent(Settings.ACTION_MANAGE_WRITE_SETTINGS);
        intent.setData(Uri.parse("package:" + Utils.getApp().getPackageName()));
        if (!isIntentAvailable(intent)) {
            launchAppDetailsSettings();
        } else {
            activity.startActivityForResult(intent, i);
        }
    }

    public static boolean isGrantedDrawOverlays() {
        return Settings.canDrawOverlays(Utils.getApp());
    }

    public static void requestDrawOverlays(SimpleCallback simpleCallback) {
        if (!isGrantedDrawOverlays()) {
            sSimpleCallback4DrawOverlays = simpleCallback;
            PermissionActivityImpl.start(3);
        } else if (simpleCallback != null) {
            simpleCallback.onGranted();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static void startOverlayPermissionActivity(Activity activity, int i) {
        Intent intent = new Intent(Settings.ACTION_MANAGE_OVERLAY_PERMISSION);
        intent.setData(Uri.parse("package:" + Utils.getApp().getPackageName()));
        if (!isIntentAvailable(intent)) {
            launchAppDetailsSettings();
        } else {
            activity.startActivityForResult(intent, i);
        }
    }

    public static void launchAppDetailsSettings() {
        Intent intent = new Intent(Settings.ACTION_APPLICATION_DETAILS_SETTINGS);
        intent.setData(Uri.parse("package:" + Utils.getApp().getPackageName()));
        if (isIntentAvailable(intent)) {
            Utils.getApp().startActivity(intent.addFlags(268435456));
        }
    }

    public static PermissionUtils permission(String... strArr) {
        return new PermissionUtils(strArr);
    }

    private static boolean isIntentAvailable(Intent intent) {
        return Utils.getApp().getPackageManager().queryIntentActivities(intent, 65536).size() > 0;
    }

    private PermissionUtils(String... strArr) {
        for (String str : strArr) {
            for (String str2 : PermissionConstants.getPermissions(str)) {
                if (PERMISSIONS.contains(str2)) {
                    this.mPermissions.add(str2);
                }
            }
        }
        sInstance = this;
    }

    public PermissionUtils rationale(OnRationaleListener onRationaleListener) {
        this.mOnRationaleListener = onRationaleListener;
        return this;
    }

    public PermissionUtils callback(SimpleCallback simpleCallback) {
        this.mSimpleCallback = simpleCallback;
        return this;
    }

    public PermissionUtils callback(FullCallback fullCallback) {
        this.mFullCallback = fullCallback;
        return this;
    }

    public PermissionUtils theme(ThemeCallback themeCallback) {
        this.mThemeCallback = themeCallback;
        return this;
    }

    public void request() {
        this.mPermissionsGranted = new ArrayList();
        this.mPermissionsRequest = new ArrayList();
        this.mPermissionsDenied = new ArrayList();
        this.mPermissionsDeniedForever = new ArrayList();
        if (Build.VERSION.SDK_INT < 23) {
            this.mPermissionsGranted.addAll(this.mPermissions);
            requestCallback();
            return;
        }
        for (String str : this.mPermissions) {
            if (isGranted(str)) {
                this.mPermissionsGranted.add(str);
            } else {
                this.mPermissionsRequest.add(str);
            }
        }
        if (this.mPermissionsRequest.isEmpty()) {
            requestCallback();
        } else {
            startPermissionActivity();
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void startPermissionActivity() {
        PermissionActivityImpl.start(1);
    }

    /* JADX INFO: Access modifiers changed from: private */
    public boolean rationale(final Activity activity) {
        boolean z = false;
        if (this.mOnRationaleListener != null) {
            Iterator<String> it = this.mPermissionsRequest.iterator();
            while (true) {
                if (!it.hasNext()) {
                    break;
                }
                if (activity.shouldShowRequestPermissionRationale(it.next())) {
                    getPermissionsStatus(activity);
                    this.mOnRationaleListener.rationale(new OnRationaleListener.ShouldRequest() { // from class: com.blankj.utilcode.util.PermissionUtils.1
                        @Override // com.blankj.utilcode.util.PermissionUtils.OnRationaleListener.ShouldRequest
                        public void again(boolean z2) {
                            activity.finish();
                            if (!z2) {
                                PermissionUtils.this.requestCallback();
                                return;
                            }
                            PermissionUtils.this.mPermissionsDenied = new ArrayList();
                            PermissionUtils.this.mPermissionsDeniedForever = new ArrayList();
                            PermissionUtils.this.startPermissionActivity();
                        }
                    });
                    z = true;
                    break;
                }
            }
            this.mOnRationaleListener = null;
        }
        return z;
    }

    private void getPermissionsStatus(Activity activity) {
        for (String str : this.mPermissionsRequest) {
            if (isGranted(str)) {
                this.mPermissionsGranted.add(str);
            } else {
                this.mPermissionsDenied.add(str);
                if (!activity.shouldShowRequestPermissionRationale(str)) {
                    this.mPermissionsDeniedForever.add(str);
                }
            }
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void requestCallback() {
        if (this.mSimpleCallback != null) {
            if (this.mPermissionsRequest.size() == 0 || this.mPermissions.size() == this.mPermissionsGranted.size()) {
                this.mSimpleCallback.onGranted();
            } else if (!this.mPermissionsDenied.isEmpty()) {
                this.mSimpleCallback.onDenied();
            }
            this.mSimpleCallback = null;
        }
        if (this.mFullCallback != null) {
            if (this.mPermissionsRequest.size() == 0 || this.mPermissionsGranted.size() > 0) {
                this.mFullCallback.onGranted(this.mPermissionsGranted);
            }
            if (!this.mPermissionsDenied.isEmpty()) {
                this.mFullCallback.onDenied(this.mPermissionsDeniedForever, this.mPermissionsDenied);
            }
            this.mFullCallback = null;
        }
        this.mOnRationaleListener = null;
        this.mThemeCallback = null;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void onRequestPermissionsResult(Activity activity) {
        getPermissionsStatus(activity);
        requestCallback();
    }

    static final class PermissionActivityImpl extends Utils.TransActivity.TransActivityDelegate {
        private static PermissionActivityImpl INSTANCE = new PermissionActivityImpl();
        private static final String TYPE = "TYPE";
        private static final int TYPE_DRAW_OVERLAYS = 3;
        private static final int TYPE_RUNTIME = 1;
        private static final int TYPE_WRITE_SETTINGS = 2;

        PermissionActivityImpl() {
        }

        public static void start(final int i) {
            Utils.TransActivity.start(new Utils.Func1<Void, Intent>() { // from class: com.blankj.utilcode.util.PermissionUtils.PermissionActivityImpl.1
                @Override // com.blankj.utilcode.util.Utils.Func1
                public Void call(Intent intent) {
                    intent.putExtra(PermissionActivityImpl.TYPE, i);
                    return null;
                }
            }, INSTANCE);
        }

        @Override // com.blankj.utilcode.util.Utils.TransActivity.TransActivityDelegate
        public void onCreated(Activity activity, Bundle bundle) {
            activity.getWindow().addFlags(262160);
            int intExtra = activity.getIntent().getIntExtra(TYPE, -1);
            if (intExtra != 1) {
                if (intExtra == 2) {
                    PermissionUtils.startWriteSettingsActivity(activity, 2);
                    return;
                } else if (intExtra == 3) {
                    PermissionUtils.startOverlayPermissionActivity(activity, 3);
                    return;
                } else {
                    activity.finish();
                    Log.e("PermissionUtils", "type is wrong.");
                    return;
                }
            }
            if (PermissionUtils.sInstance != null) {
                if (PermissionUtils.sInstance.mThemeCallback != null) {
                    PermissionUtils.sInstance.mThemeCallback.onActivityCreate(activity);
                }
                if (PermissionUtils.sInstance.rationale(activity) || PermissionUtils.sInstance.mPermissionsRequest == null) {
                    return;
                }
                int size = PermissionUtils.sInstance.mPermissionsRequest.size();
                if (size > 0) {
                    activity.requestPermissions((String[]) PermissionUtils.sInstance.mPermissionsRequest.toArray(new String[size]), 1);
                    return;
                } else {
                    activity.finish();
                    return;
                }
            }
            Log.e("PermissionUtils", "request permissions failed");
            activity.finish();
        }

        @Override // com.blankj.utilcode.util.Utils.TransActivity.TransActivityDelegate
        public void onRequestPermissionsResult(Activity activity, int i, String[] strArr, int[] iArr) {
            if (PermissionUtils.sInstance != null && PermissionUtils.sInstance.mPermissionsRequest != null) {
                PermissionUtils.sInstance.onRequestPermissionsResult(activity);
            }
            activity.finish();
        }

        @Override // com.blankj.utilcode.util.Utils.TransActivity.TransActivityDelegate
        public boolean dispatchTouchEvent(Activity activity, MotionEvent motionEvent) {
            activity.finish();
            return true;
        }

        @Override // com.blankj.utilcode.util.Utils.TransActivity.TransActivityDelegate
        public void onActivityResult(Activity activity, int i, int i2, Intent intent) {
            if (i == 2) {
                if (PermissionUtils.sSimpleCallback4WriteSettings == null) {
                    return;
                }
                if (PermissionUtils.isGrantedWriteSettings()) {
                    PermissionUtils.sSimpleCallback4WriteSettings.onGranted();
                } else {
                    PermissionUtils.sSimpleCallback4WriteSettings.onDenied();
                }
                SimpleCallback unused = PermissionUtils.sSimpleCallback4WriteSettings = null;
            } else if (i == 3) {
                if (PermissionUtils.sSimpleCallback4DrawOverlays == null) {
                    return;
                } else {
                    Utils.runOnUiThreadDelayed(new Runnable() { // from class: com.blankj.utilcode.util.PermissionUtils.PermissionActivityImpl.2
                        @Override // java.lang.Runnable
                        public void run() {
                            if (PermissionUtils.isGrantedDrawOverlays()) {
                                PermissionUtils.sSimpleCallback4DrawOverlays.onGranted();
                            } else {
                                PermissionUtils.sSimpleCallback4DrawOverlays.onDenied();
                            }
                            SimpleCallback unused2 = PermissionUtils.sSimpleCallback4DrawOverlays = null;
                        }
                    }, 100L);
                }
            }
            activity.finish();
        }
    }
}
