package com.android.inputmethod.latin.permissions;

import android.app.Activity;
import android.content.Context;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;

/* JADX INFO: loaded from: classes2.dex */
public class PermissionsManager {
    private static PermissionsManager sInstance;
    private final Context mContext;
    private int mRequestCodeId;
    private final Map<Integer, PermissionsResultCallback> mRequestIdToCallback = new HashMap();

    public interface PermissionsResultCallback {
        void onRequestPermissionsResult(boolean allGranted);
    }

    public PermissionsManager(Context context) {
        this.mContext = context;
    }

    @Nonnull
    public static synchronized PermissionsManager get(@Nonnull Context context) {
        if (sInstance == null) {
            sInstance = new PermissionsManager(context);
        }
        return sInstance;
    }

    private synchronized int getNextRequestId() {
        int i;
        i = this.mRequestCodeId + 1;
        this.mRequestCodeId = i;
        return i;
    }

    public synchronized void requestPermissions(@Nonnull PermissionsResultCallback callback, @Nullable Activity activity, String... permissionsToRequest) {
        List<String> deniedPermissions = PermissionsUtil.getDeniedPermissions(this.mContext, permissionsToRequest);
        if (deniedPermissions.isEmpty()) {
            return;
        }
        int nextRequestId = getNextRequestId();
        String[] strArr = (String[]) deniedPermissions.toArray(new String[deniedPermissions.size()]);
        this.mRequestIdToCallback.put(Integer.valueOf(nextRequestId), callback);
        if (activity != null) {
            PermissionsUtil.requestPermissions(activity, nextRequestId, strArr);
        } else {
            PermissionsActivity.run(this.mContext, nextRequestId, strArr);
        }
    }

    public synchronized void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        PermissionsResultCallback permissionsResultCallback = this.mRequestIdToCallback.get(Integer.valueOf(requestCode));
        this.mRequestIdToCallback.remove(Integer.valueOf(requestCode));
        permissionsResultCallback.onRequestPermissionsResult(PermissionsUtil.allGranted(grantResults));
    }
}
