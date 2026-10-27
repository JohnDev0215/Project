package com.permissionx.guolindev.request;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.view.View;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import com.byd.mediacenter.api.media.MediaControl;
import com.permissionx.guolindev.callback.ExplainReasonCallback;
import com.permissionx.guolindev.callback.ExplainReasonCallbackWithBeforeParam;
import com.permissionx.guolindev.callback.ForwardToSettingsCallback;
import com.permissionx.guolindev.callback.RequestCallback;
import com.permissionx.guolindev.dialog.DefaultDialog;
import com.permissionx.guolindev.dialog.RationaleDialog;
import com.permissionx.guolindev.dialog.RationaleDialogFragment;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/* JADX INFO: loaded from: classes3.dex */
public class PermissionBuilder {
    private static final String FRAGMENT_TAG = "InvisibleFragment";
    FragmentActivity activity;
    Dialog currentDialog;
    ExplainReasonCallback explainReasonCallback;
    ExplainReasonCallbackWithBeforeParam explainReasonCallbackWithBeforeParam;
    ForwardToSettingsCallback forwardToSettingsCallback;
    Fragment fragment;
    Set<String> normalPermissions;
    Set<String> permissionsWontRequest;
    RequestCallback requestCallback;
    boolean requireBackgroundLocationPermission;
    boolean explainReasonBeforeRequest = false;
    boolean showDialogCalled = false;
    int lightColor = -1;
    int darkColor = -1;
    Set<String> grantedPermissions = new HashSet();
    Set<String> deniedPermissions = new HashSet();
    Set<String> permanentDeniedPermissions = new HashSet();
    Set<String> tempPermanentDeniedPermissions = new HashSet();
    Set<String> forwardPermissions = new HashSet();

    public PermissionBuilder(FragmentActivity fragmentActivity, Fragment fragment, Set<String> set, boolean z, Set<String> set2) {
        this.activity = fragmentActivity;
        this.fragment = fragment;
        if (fragmentActivity == null && fragment != null) {
            this.activity = fragment.getActivity();
        }
        this.normalPermissions = set;
        this.requireBackgroundLocationPermission = z;
        this.permissionsWontRequest = set2;
    }

    public PermissionBuilder onExplainRequestReason(ExplainReasonCallback explainReasonCallback) {
        this.explainReasonCallback = explainReasonCallback;
        return this;
    }

    public PermissionBuilder onExplainRequestReason(ExplainReasonCallbackWithBeforeParam explainReasonCallbackWithBeforeParam) {
        this.explainReasonCallbackWithBeforeParam = explainReasonCallbackWithBeforeParam;
        return this;
    }

    public PermissionBuilder onForwardToSettings(ForwardToSettingsCallback forwardToSettingsCallback) {
        this.forwardToSettingsCallback = forwardToSettingsCallback;
        return this;
    }

    public PermissionBuilder explainReasonBeforeRequest() {
        this.explainReasonBeforeRequest = true;
        return this;
    }

    public PermissionBuilder setDialogTintColor(int i, int i2) {
        this.lightColor = i;
        this.darkColor = i2;
        return this;
    }

    public void request(RequestCallback requestCallback) {
        this.requestCallback = requestCallback;
        RequestChain requestChain = new RequestChain();
        requestChain.addTaskToChain(new RequestNormalPermissions(this));
        requestChain.addTaskToChain(new RequestBackgroundLocationPermission(this));
        requestChain.runTask();
    }

    void showHandlePermissionDialog(ChainTask chainTask, boolean z, List<String> list, String str, String str2, String str3) {
        showHandlePermissionDialog(chainTask, z, new DefaultDialog(this.activity, list, str, str2, str3, this.lightColor, this.darkColor));
    }

    void showHandlePermissionDialog(final ChainTask chainTask, final boolean z, final RationaleDialog rationaleDialog) {
        this.showDialogCalled = true;
        final List<String> permissionsToRequest = rationaleDialog.getPermissionsToRequest();
        if (permissionsToRequest.isEmpty()) {
            chainTask.finish();
            return;
        }
        this.currentDialog = rationaleDialog;
        rationaleDialog.show();
        View positiveButton = rationaleDialog.getPositiveButton();
        View negativeButton = rationaleDialog.getNegativeButton();
        rationaleDialog.setCancelable(false);
        rationaleDialog.setCanceledOnTouchOutside(false);
        positiveButton.setClickable(true);
        positiveButton.setOnClickListener(new View.OnClickListener() { // from class: com.permissionx.guolindev.request.PermissionBuilder.1
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                rationaleDialog.dismiss();
                if (!z) {
                    PermissionBuilder.this.forwardToSettings(permissionsToRequest);
                } else {
                    chainTask.requestAgain(permissionsToRequest);
                }
            }
        });
        if (negativeButton != null) {
            negativeButton.setClickable(true);
            negativeButton.setOnClickListener(new View.OnClickListener() { // from class: com.permissionx.guolindev.request.PermissionBuilder.2
                @Override // android.view.View.OnClickListener
                public void onClick(View view) {
                    rationaleDialog.dismiss();
                    chainTask.finish();
                }
            });
        }
        this.currentDialog.setOnDismissListener(new DialogInterface.OnDismissListener() { // from class: com.permissionx.guolindev.request.PermissionBuilder.3
            @Override // android.content.DialogInterface.OnDismissListener
            public void onDismiss(DialogInterface dialogInterface) {
                PermissionBuilder.this.currentDialog = null;
            }
        });
    }

    void showHandlePermissionDialog(final ChainTask chainTask, final boolean z, final RationaleDialogFragment rationaleDialogFragment) {
        this.showDialogCalled = true;
        final List<String> permissionsToRequest = rationaleDialogFragment.getPermissionsToRequest();
        if (permissionsToRequest.isEmpty()) {
            chainTask.finish();
            return;
        }
        rationaleDialogFragment.showNow(getFragmentManager(), "PermissionXRationaleDialogFragment");
        View positiveButton = rationaleDialogFragment.getPositiveButton();
        View negativeButton = rationaleDialogFragment.getNegativeButton();
        rationaleDialogFragment.setCancelable(false);
        positiveButton.setClickable(true);
        positiveButton.setOnClickListener(new View.OnClickListener() { // from class: com.permissionx.guolindev.request.PermissionBuilder.4
            @Override // android.view.View.OnClickListener
            public void onClick(View view) {
                rationaleDialogFragment.dismiss();
                if (!z) {
                    PermissionBuilder.this.forwardToSettings(permissionsToRequest);
                } else {
                    chainTask.requestAgain(permissionsToRequest);
                }
            }
        });
        if (negativeButton != null) {
            negativeButton.setClickable(true);
            negativeButton.setOnClickListener(new View.OnClickListener() { // from class: com.permissionx.guolindev.request.PermissionBuilder.5
                @Override // android.view.View.OnClickListener
                public void onClick(View view) {
                    rationaleDialogFragment.dismiss();
                    chainTask.finish();
                }
            });
        }
    }

    void requestNow(Set<String> set, ChainTask chainTask) {
        getInvisibleFragment().requestNow(this, set, chainTask);
    }

    void requestAccessBackgroundLocationNow(ChainTask chainTask) {
        getInvisibleFragment().requestAccessBackgroundLocationNow(this, chainTask);
    }

    FragmentManager getFragmentManager() {
        Fragment fragment = this.fragment;
        if (fragment != null) {
            return fragment.getChildFragmentManager();
        }
        return this.activity.getSupportFragmentManager();
    }

    private InvisibleFragment getInvisibleFragment() {
        FragmentManager fragmentManager = getFragmentManager();
        Fragment fragmentFindFragmentByTag = fragmentManager.findFragmentByTag(FRAGMENT_TAG);
        if (fragmentFindFragmentByTag != null) {
            return (InvisibleFragment) fragmentFindFragmentByTag;
        }
        InvisibleFragment invisibleFragment = new InvisibleFragment();
        fragmentManager.beginTransaction().add(invisibleFragment, FRAGMENT_TAG).commitNowAllowingStateLoss();
        return invisibleFragment;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public void forwardToSettings(List<String> list) {
        this.forwardPermissions.clear();
        this.forwardPermissions.addAll(list);
        Intent intent = new Intent("android.settings.APPLICATION_DETAILS_SETTINGS");
        intent.setData(Uri.fromParts(MediaControl.PARAM_PACKAGE, this.activity.getPackageName(), null));
        getInvisibleFragment().startActivityForResult(intent, 2);
    }
}
