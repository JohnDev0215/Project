package com.permissionx.guolindev.request;

import android.content.Intent;
import android.util.Log;
import androidx.fragment.app.Fragment;
import com.permissionx.guolindev.PermissionX;
import java.util.ArrayList;
import java.util.Set;

/* JADX INFO: loaded from: classes3.dex */
public class InvisibleFragment extends Fragment {
    public static final int FORWARD_TO_SETTINGS = 2;
    public static final int REQUEST_BACKGROUND_LOCATION_PERMISSION = 2;
    public static final int REQUEST_NORMAL_PERMISSIONS = 1;
    private PermissionBuilder pb;
    private ChainTask task;

    void requestNow(PermissionBuilder permissionBuilder, Set<String> set, ChainTask chainTask) {
        this.pb = permissionBuilder;
        this.task = chainTask;
        requestPermissions((String[]) set.toArray(new String[0]), 1);
    }

    void requestAccessBackgroundLocationNow(PermissionBuilder permissionBuilder, ChainTask chainTask) {
        this.pb = permissionBuilder;
        this.task = chainTask;
        requestPermissions(new String[]{RequestBackgroundLocationPermission.ACCESS_BACKGROUND_LOCATION}, 2);
    }

    @Override // androidx.fragment.app.Fragment
    public void onRequestPermissionsResult(int i, String[] strArr, int[] iArr) {
        if (i == 1) {
            onRequestNormalPermissionsResult(strArr, iArr);
        } else if (i == 2) {
            onRequestBackgroundLocationPermissionResult();
        }
    }

    @Override // androidx.fragment.app.Fragment
    public void onActivityResult(int i, int i2, Intent intent) {
        super.onActivityResult(i, i2, intent);
        if (i == 2 && checkForGC()) {
            this.task.requestAgain(new ArrayList(this.pb.forwardPermissions));
        }
    }

    @Override // androidx.fragment.app.Fragment
    public void onDestroy() {
        super.onDestroy();
        if (checkForGC() && this.pb.currentDialog != null && this.pb.currentDialog.isShowing()) {
            this.pb.currentDialog.dismiss();
        }
    }

    /* JADX WARN: Removed duplicated region for block: B:49:0x0145  */
    /*
        Code decompiled incorrectly, please refer to instructions dump.
        To view partially-correct code enable 'Show inconsistent code' option in preferences
    */
    private void onRequestNormalPermissionsResult(java.lang.String[] r7, int[] r8) {
        /*
            Method dump skipped, instruction units count: 335
            To view this dump change 'Code comments level' option to 'DEBUG'
        */
        throw new UnsupportedOperationException("Method not decompiled: com.permissionx.guolindev.request.InvisibleFragment.onRequestNormalPermissionsResult(java.lang.String[], int[]):void");
    }

    private void onRequestBackgroundLocationPermissionResult() {
        if (checkForGC()) {
            if (PermissionX.isGranted(getContext(), RequestBackgroundLocationPermission.ACCESS_BACKGROUND_LOCATION)) {
                this.pb.grantedPermissions.add(RequestBackgroundLocationPermission.ACCESS_BACKGROUND_LOCATION);
                this.pb.deniedPermissions.remove(RequestBackgroundLocationPermission.ACCESS_BACKGROUND_LOCATION);
                this.pb.permanentDeniedPermissions.remove(RequestBackgroundLocationPermission.ACCESS_BACKGROUND_LOCATION);
                this.task.finish();
                return;
            }
            boolean zShouldShowRequestPermissionRationale = shouldShowRequestPermissionRationale(RequestBackgroundLocationPermission.ACCESS_BACKGROUND_LOCATION);
            boolean z = false;
            if ((this.pb.explainReasonCallback != null || this.pb.explainReasonCallbackWithBeforeParam != null) && zShouldShowRequestPermissionRationale) {
                ArrayList arrayList = new ArrayList();
                arrayList.add(RequestBackgroundLocationPermission.ACCESS_BACKGROUND_LOCATION);
                if (this.pb.explainReasonCallbackWithBeforeParam != null) {
                    this.pb.explainReasonCallbackWithBeforeParam.onExplainReason(this.task.getExplainScope(), arrayList, false);
                } else {
                    this.pb.explainReasonCallback.onExplainReason(this.task.getExplainScope(), arrayList);
                }
            } else if (this.pb.forwardToSettingsCallback == null || zShouldShowRequestPermissionRationale) {
                z = true;
            } else {
                ArrayList arrayList2 = new ArrayList();
                arrayList2.add(RequestBackgroundLocationPermission.ACCESS_BACKGROUND_LOCATION);
                this.pb.forwardToSettingsCallback.onForwardToSettings(this.task.getForwardScope(), arrayList2);
            }
            if (z || !this.pb.showDialogCalled) {
                this.task.finish();
            }
        }
    }

    private boolean checkForGC() {
        if (this.pb != null && this.task != null) {
            return true;
        }
        Log.w("PermissionX", "PermissionBuilder and ChainTask should not be null at this time, so we can do nothing in this case.");
        return false;
    }
}
