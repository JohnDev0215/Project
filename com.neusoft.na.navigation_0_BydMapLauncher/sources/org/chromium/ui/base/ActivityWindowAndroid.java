package org.chromium.ui.base;

import android.R;
import android.app.Activity;
import android.app.PendingIntent;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.content.IntentSender;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.content.pm.PermissionInfo;
import android.os.Handler;
import android.os.Process;
import android.text.TextUtils;
import android.util.SparseArray;
import android.view.View;
import java.lang.ref.WeakReference;
import org.chromium.base.ApiCompatibilityUtils;
import org.chromium.base.ApplicationStatus;
import org.chromium.base.Callback;
import org.chromium.base.ContextUtils;
import org.chromium.ui.UiUtils;
import org.chromium.ui.base.WindowAndroid;

/* JADX INFO: loaded from: classes4.dex */
public class ActivityWindowAndroid extends WindowAndroid implements ApplicationStatus.ActivityStateListener, View.OnLayoutChangeListener {
    static final /* synthetic */ boolean $assertionsDisabled = false;
    private static final String PERMISSION_QUERIED_KEY_PREFIX = "HasRequestedAndroidPermission::";
    private static final int REQUEST_CODE_PREFIX = 1000;
    private static final int REQUEST_CODE_RANGE_SIZE = 100;
    private final Handler mHandler;
    private int mNextRequestCode;
    private final SparseArray<WindowAndroid.PermissionCallback> mOutstandingPermissionRequests;

    public ActivityWindowAndroid(Context context) {
        this(context, true);
    }

    public ActivityWindowAndroid(Context context, boolean z) {
        super(context);
        this.mNextRequestCode = 0;
        Activity activityActivityFromContext = activityFromContext(context);
        if (activityActivityFromContext == null) {
            throw new IllegalArgumentException("Context is not and does not wrap an Activity");
        }
        this.mHandler = new Handler();
        this.mOutstandingPermissionRequests = new SparseArray<>();
        if (z) {
            ApplicationStatus.registerStateListenerForActivity(this, activityActivityFromContext);
        }
        setAndroidPermissionDelegate(new ActivityAndroidPermissionDelegate());
    }

    @Override // org.chromium.ui.base.WindowAndroid
    protected void registerKeyboardVisibilityCallbacks() {
        Activity activity = getActivity().get();
        if (activity == null) {
            return;
        }
        View viewFindViewById = activity.findViewById(R.id.content);
        this.mIsKeyboardShowing = UiUtils.isKeyboardShowing(getActivity().get(), viewFindViewById);
        viewFindViewById.addOnLayoutChangeListener(this);
    }

    @Override // org.chromium.ui.base.WindowAndroid
    protected void unregisterKeyboardVisibilityCallbacks() {
        Activity activity = getActivity().get();
        if (activity == null) {
            return;
        }
        activity.findViewById(R.id.content).removeOnLayoutChangeListener(this);
    }

    @Override // org.chromium.ui.base.WindowAndroid
    public int showCancelableIntent(PendingIntent pendingIntent, WindowAndroid.IntentCallback intentCallback, Integer num) {
        Activity activity = getActivity().get();
        if (activity == null) {
            return -1;
        }
        int iGenerateNextRequestCode = generateNextRequestCode();
        try {
            activity.startIntentSenderForResult(pendingIntent.getIntentSender(), iGenerateNextRequestCode, new Intent(), 0, 0, 0);
            storeCallbackData(iGenerateNextRequestCode, intentCallback, num);
            return iGenerateNextRequestCode;
        } catch (IntentSender.SendIntentException unused) {
            return -1;
        }
    }

    @Override // org.chromium.ui.base.WindowAndroid
    public int showCancelableIntent(Intent intent, WindowAndroid.IntentCallback intentCallback, Integer num) {
        Activity activity = getActivity().get();
        if (activity == null) {
            return -1;
        }
        int iGenerateNextRequestCode = generateNextRequestCode();
        try {
            activity.startActivityForResult(intent, iGenerateNextRequestCode);
            storeCallbackData(iGenerateNextRequestCode, intentCallback, num);
            return iGenerateNextRequestCode;
        } catch (ActivityNotFoundException unused) {
            return -1;
        }
    }

    @Override // org.chromium.ui.base.WindowAndroid
    public int showCancelableIntent(Callback<Integer> callback, WindowAndroid.IntentCallback intentCallback, Integer num) {
        if (getActivity().get() == null) {
            return -1;
        }
        int iGenerateNextRequestCode = generateNextRequestCode();
        callback.onResult(Integer.valueOf(iGenerateNextRequestCode));
        storeCallbackData(iGenerateNextRequestCode, intentCallback, num);
        return iGenerateNextRequestCode;
    }

    @Override // org.chromium.ui.base.WindowAndroid
    public void cancelIntent(int i) {
        Activity activity = getActivity().get();
        if (activity == null) {
            return;
        }
        activity.finishActivity(i);
    }

    public boolean onActivityResult(int i, int i2, Intent intent) {
        WindowAndroid.IntentCallback intentCallback = this.mOutstandingIntents.get(i);
        this.mOutstandingIntents.delete(i);
        String strRemove = this.mIntentErrors.remove(Integer.valueOf(i));
        if (intentCallback != null) {
            intentCallback.onIntentCompleted(this, i2, this.mApplicationContext.getContentResolver(), intent);
            return true;
        }
        if (strRemove == null) {
            return false;
        }
        showCallbackNonExistentError(strRemove);
        return true;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public String getHasRequestedPermissionKey(String str) {
        try {
            PermissionInfo permissionInfo = getApplicationContext().getPackageManager().getPermissionInfo(str, 128);
            if (!TextUtils.isEmpty(permissionInfo.group)) {
                str = permissionInfo.group;
            }
        } catch (PackageManager.NameNotFoundException unused) {
        }
        return PERMISSION_QUERIED_KEY_PREFIX + str;
    }

    public boolean onRequestPermissionsResult(int i, String[] strArr, int[] iArr) {
        getActivity().get();
        SharedPreferences.Editor editorEdit = ContextUtils.getAppSharedPreferences().edit();
        for (String str : strArr) {
            editorEdit.putBoolean(getHasRequestedPermissionKey(str), true);
        }
        editorEdit.apply();
        WindowAndroid.PermissionCallback permissionCallback = this.mOutstandingPermissionRequests.get(i);
        this.mOutstandingPermissionRequests.delete(i);
        if (permissionCallback == null) {
            return false;
        }
        permissionCallback.onRequestPermissionsResult(strArr, iArr);
        return true;
    }

    @Override // org.chromium.ui.base.WindowAndroid
    public WeakReference<Activity> getActivity() {
        return new WeakReference<>(activityFromContext(getContext().get()));
    }

    @Override // org.chromium.base.ApplicationStatus.ActivityStateListener
    public void onActivityStateChange(Activity activity, int i) {
        if (i == 5) {
            onActivityStopped();
        } else if (i == 2) {
            onActivityStarted();
        }
    }

    @Override // android.view.View.OnLayoutChangeListener
    public void onLayoutChange(View view, int i, int i2, int i3, int i4, int i5, int i6, int i7, int i8) {
        keyboardVisibilityPossiblyChanged(UiUtils.isKeyboardShowing(getActivity().get(), view));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public int generateNextRequestCode() {
        int i = this.mNextRequestCode;
        int i2 = i + 1000;
        this.mNextRequestCode = (i + 1) % 100;
        return i2;
    }

    private void storeCallbackData(int i, WindowAndroid.IntentCallback intentCallback, Integer num) {
        this.mOutstandingIntents.put(i, intentCallback);
        this.mIntentErrors.put(Integer.valueOf(i), num == null ? null : this.mApplicationContext.getString(num.intValue()));
    }

    private class ActivityAndroidPermissionDelegate implements AndroidPermissionDelegate {
        private ActivityAndroidPermissionDelegate() {
        }

        @Override // org.chromium.ui.base.AndroidPermissionDelegate
        public boolean hasPermission(String str) {
            return ApiCompatibilityUtils.checkPermission(ActivityWindowAndroid.this.mApplicationContext, str, Process.myPid(), Process.myUid()) == 0;
        }

        @Override // org.chromium.ui.base.AndroidPermissionDelegate
        public boolean canRequestPermission(String str) {
            Activity activity = ActivityWindowAndroid.this.getActivity().get();
            if (activity == null || isPermissionRevokedByPolicy(str)) {
                return false;
            }
            if (activity.shouldShowRequestPermissionRationale(str)) {
                return true;
            }
            return !ContextUtils.getAppSharedPreferences().getBoolean(ActivityWindowAndroid.this.getHasRequestedPermissionKey(str), false);
        }

        @Override // org.chromium.ui.base.AndroidPermissionDelegate
        public boolean isPermissionRevokedByPolicy(String str) {
            Activity activity = ActivityWindowAndroid.this.getActivity().get();
            if (activity == null) {
                return false;
            }
            return activity.getPackageManager().isPermissionRevokedByPolicy(str, activity.getPackageName());
        }

        @Override // org.chromium.ui.base.AndroidPermissionDelegate
        public void requestPermissions(final String[] strArr, final WindowAndroid.PermissionCallback permissionCallback) {
            if (requestPermissionsInternal(strArr, permissionCallback)) {
                return;
            }
            ActivityWindowAndroid.this.mHandler.post(new Runnable() { // from class: org.chromium.ui.base.ActivityWindowAndroid.ActivityAndroidPermissionDelegate.1
                @Override // java.lang.Runnable
                public void run() {
                    int[] iArr = new int[strArr.length];
                    int i = 0;
                    while (true) {
                        String[] strArr2 = strArr;
                        if (i < strArr2.length) {
                            iArr[i] = ActivityAndroidPermissionDelegate.this.hasPermission(strArr2[i]) ? 0 : -1;
                            i++;
                        } else {
                            permissionCallback.onRequestPermissionsResult(strArr2, iArr);
                            return;
                        }
                    }
                }
            });
        }

        private boolean requestPermissionsInternal(String[] strArr, WindowAndroid.PermissionCallback permissionCallback) {
            Activity activity = ActivityWindowAndroid.this.getActivity().get();
            if (activity == null) {
                return false;
            }
            int iGenerateNextRequestCode = ActivityWindowAndroid.this.generateNextRequestCode();
            ActivityWindowAndroid.this.mOutstandingPermissionRequests.put(iGenerateNextRequestCode, permissionCallback);
            activity.requestPermissions(strArr, iGenerateNextRequestCode);
            return true;
        }
    }
}
